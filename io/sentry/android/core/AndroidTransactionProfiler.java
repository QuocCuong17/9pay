package io.sentry.android.core;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import android.os.SystemClock;
import io.sentry.CpuCollectionData;
import io.sentry.HubAdapter;
import io.sentry.IHub;
import io.sentry.ITransaction;
import io.sentry.ITransactionProfiler;
import io.sentry.MemoryCollectionData;
import io.sentry.PerformanceCollectionData;
import io.sentry.ProfilingTraceData;
import io.sentry.ProfilingTransactionData;
import io.sentry.SentryLevel;
import io.sentry.android.core.internal.util.CpuInfoUtils;
import io.sentry.android.core.internal.util.SentryFrameMetricsCollector;
import io.sentry.profilemeasurements.ProfileMeasurement;
import io.sentry.profilemeasurements.ProfileMeasurementValue;
import io.sentry.util.Objects;
import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class AndroidTransactionProfiler implements ITransactionProfiler {
    private static final int BUFFER_SIZE_BYTES = 3000000;
    private static final int PROFILING_TIMEOUT_MILLIS = 30000;
    private final BuildInfoProvider buildInfoProvider;
    private final Context context;
    private ProfilingTransactionData currentProfilingTransactionData;
    private ITransaction currentTransaction;
    private final SentryFrameMetricsCollector frameMetricsCollector;
    private String frameMetricsCollectorId;
    private final ArrayDeque<ProfileMeasurementValue> frozenFrameRenderMeasurements;
    private final IHub hub;
    private int intervalUs;
    private boolean isInitialized;
    private final Map<String, ProfileMeasurement> measurementsMap;
    private final SentryAndroidOptions options;
    private long profileStartCpuMillis;
    private Future<?> scheduledFinish;
    private final ArrayDeque<ProfileMeasurementValue> screenFrameRateMeasurements;
    private final ArrayDeque<ProfileMeasurementValue> slowFrameRenderMeasurements;
    private volatile ProfilingTraceData timedOutProfilingData;
    private File traceFile;
    private File traceFilesDir;
    private long transactionStartNanos;
    private int transactionsCounter;

    public AndroidTransactionProfiler(Context context, SentryAndroidOptions sentryAndroidOptions, BuildInfoProvider buildInfoProvider, SentryFrameMetricsCollector sentryFrameMetricsCollector) {
        this(context, sentryAndroidOptions, buildInfoProvider, sentryFrameMetricsCollector, HubAdapter.getInstance());
    }

    public AndroidTransactionProfiler(Context context, SentryAndroidOptions sentryAndroidOptions, BuildInfoProvider buildInfoProvider, SentryFrameMetricsCollector sentryFrameMetricsCollector, IHub iHub) {
        this.traceFile = null;
        this.traceFilesDir = null;
        this.scheduledFinish = null;
        this.timedOutProfilingData = null;
        this.transactionStartNanos = 0L;
        this.profileStartCpuMillis = 0L;
        this.isInitialized = false;
        this.transactionsCounter = 0;
        this.screenFrameRateMeasurements = new ArrayDeque<>();
        this.slowFrameRenderMeasurements = new ArrayDeque<>();
        this.frozenFrameRenderMeasurements = new ArrayDeque<>();
        this.measurementsMap = new HashMap();
        this.currentTransaction = null;
        this.context = (Context) Objects.requireNonNull(context, "The application context is required");
        this.options = (SentryAndroidOptions) Objects.requireNonNull(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.hub = (IHub) Objects.requireNonNull(iHub, "Hub is required");
        this.frameMetricsCollector = (SentryFrameMetricsCollector) Objects.requireNonNull(sentryFrameMetricsCollector, "SentryFrameMetricsCollector is required");
        this.buildInfoProvider = (BuildInfoProvider) Objects.requireNonNull(buildInfoProvider, "The BuildInfoProvider is required.");
    }

    private void init() {
        if (this.isInitialized) {
            return;
        }
        this.isInitialized = true;
        String profilingTracesDirPath = this.options.getProfilingTracesDirPath();
        if (!this.options.isProfilingEnabled()) {
            this.options.getLogger().log(SentryLevel.INFO, "Profiling is disabled in options.", new Object[0]);
            return;
        }
        if (profilingTracesDirPath == null) {
            this.options.getLogger().log(SentryLevel.WARNING, "Disabling profiling because no profiling traces dir path is defined in options.", new Object[0]);
            return;
        }
        int profilingTracesHz = this.options.getProfilingTracesHz();
        if (profilingTracesHz <= 0) {
            this.options.getLogger().log(SentryLevel.WARNING, "Disabling profiling because trace rate is set to %d", Integer.valueOf(profilingTracesHz));
        } else {
            this.intervalUs = ((int) TimeUnit.SECONDS.toMicros(1L)) / profilingTracesHz;
            this.traceFilesDir = new File(profilingTracesDirPath);
        }
    }

    @Override // io.sentry.ITransactionProfiler
    public synchronized void onTransactionStart(ITransaction iTransaction) {
        if (this.buildInfoProvider.getSdkInfoVersion() < 21) {
            return;
        }
        init();
        if (this.traceFilesDir != null && this.intervalUs != 0) {
            int i = this.transactionsCounter + 1;
            this.transactionsCounter = i;
            if (i == 1) {
                if (onFirstTransactionStarted(iTransaction)) {
                    this.options.getLogger().log(SentryLevel.DEBUG, "Transaction %s (%s) started and being profiled.", iTransaction.getName(), iTransaction.getSpanContext().getTraceId().toString());
                }
            } else {
                this.transactionsCounter = i - 1;
                this.options.getLogger().log(SentryLevel.WARNING, "A transaction is already being profiled. Transaction %s (%s) will be ignored.", iTransaction.getName(), iTransaction.getSpanContext().getTraceId().toString());
            }
        }
    }

    private boolean onFirstTransactionStarted(final ITransaction iTransaction) {
        this.traceFile = new File(this.traceFilesDir, UUID.randomUUID() + ".trace");
        this.measurementsMap.clear();
        this.screenFrameRateMeasurements.clear();
        this.slowFrameRenderMeasurements.clear();
        this.frozenFrameRenderMeasurements.clear();
        this.frameMetricsCollectorId = this.frameMetricsCollector.startCollection(new SentryFrameMetricsCollector.FrameMetricsCollectorListener() { // from class: io.sentry.android.core.AndroidTransactionProfiler.1
            final long nanosInSecond = TimeUnit.SECONDS.toNanos(1);
            final long frozenFrameThresholdNanos = TimeUnit.MILLISECONDS.toNanos(700);
            float lastRefreshRate = 0.0f;

            @Override // io.sentry.android.core.internal.util.SentryFrameMetricsCollector.FrameMetricsCollectorListener
            public void onFrameMetricCollected(long j, long j2, float f) {
                long nanoTime = ((j - System.nanoTime()) + SystemClock.elapsedRealtimeNanos()) - AndroidTransactionProfiler.this.transactionStartNanos;
                if (nanoTime < 0) {
                    return;
                }
                boolean z = ((float) j2) > ((float) this.nanosInSecond) / (f - 1.0f);
                float f2 = ((int) (f * 100.0f)) / 100.0f;
                if (j2 > this.frozenFrameThresholdNanos) {
                    AndroidTransactionProfiler.this.frozenFrameRenderMeasurements.addLast(new ProfileMeasurementValue(Long.valueOf(nanoTime), Long.valueOf(j2)));
                } else if (z) {
                    AndroidTransactionProfiler.this.slowFrameRenderMeasurements.addLast(new ProfileMeasurementValue(Long.valueOf(nanoTime), Long.valueOf(j2)));
                }
                if (f2 != this.lastRefreshRate) {
                    this.lastRefreshRate = f2;
                    AndroidTransactionProfiler.this.screenFrameRateMeasurements.addLast(new ProfileMeasurementValue(Long.valueOf(nanoTime), Float.valueOf(f2)));
                }
            }
        });
        this.currentTransaction = iTransaction;
        try {
            this.scheduledFinish = this.options.getExecutorService().schedule(new Runnable() { // from class: io.sentry.android.core.AndroidTransactionProfiler$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AndroidTransactionProfiler.this.m1155x2f1d1cc0(iTransaction);
                }
            }, 30000L);
        } catch (RejectedExecutionException e) {
            this.options.getLogger().log(SentryLevel.ERROR, "Failed to call the executor. Profiling will not be automatically finished. Did you call Sentry.close()?", e);
        }
        this.transactionStartNanos = SystemClock.elapsedRealtimeNanos();
        this.profileStartCpuMillis = Process.getElapsedCpuTime();
        this.currentProfilingTransactionData = new ProfilingTransactionData(iTransaction, Long.valueOf(this.transactionStartNanos), Long.valueOf(this.profileStartCpuMillis));
        try {
            Debug.startMethodTracingSampling(this.traceFile.getPath(), BUFFER_SIZE_BYTES, this.intervalUs);
            return true;
        } catch (Throwable th) {
            onTransactionFinish(iTransaction, null);
            this.options.getLogger().log(SentryLevel.ERROR, "Unable to start a profile: ", th);
            return false;
        }
    }

    /* renamed from: lambda$onFirstTransactionStarted$0$io-sentry-android-core-AndroidTransactionProfiler, reason: not valid java name */
    public /* synthetic */ void m1155x2f1d1cc0(ITransaction iTransaction) {
        this.timedOutProfilingData = onTransactionFinish(iTransaction, true, null);
    }

    @Override // io.sentry.ITransactionProfiler
    public synchronized ProfilingTraceData onTransactionFinish(ITransaction iTransaction, List<PerformanceCollectionData> list) {
        return onTransactionFinish(iTransaction, false, list);
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x01d3, code lost:
    
        if (r0.getTransactionId().equals(r32.getEventId().toString()) == false) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01d5, code lost:
    
        r31.timedOutProfilingData = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01d8, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01d9, code lost:
    
        r31.options.getLogger().log(io.sentry.SentryLevel.INFO, "A timed out profiling data exists, but the finishing transaction %s (%s) is not part of it", r32.getName(), r32.getSpanContext().getTraceId().toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01fe, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private synchronized ProfilingTraceData onTransactionFinish(ITransaction iTransaction, boolean z, List<PerformanceCollectionData> list) {
        if (this.buildInfoProvider.getSdkInfoVersion() < 21) {
            return null;
        }
        ProfilingTraceData profilingTraceData = this.timedOutProfilingData;
        ProfilingTransactionData profilingTransactionData = this.currentProfilingTransactionData;
        if (profilingTransactionData != null && profilingTransactionData.getId().equals(iTransaction.getEventId().toString())) {
            int i = this.transactionsCounter;
            if (i > 0) {
                this.transactionsCounter = i - 1;
            }
            this.options.getLogger().log(SentryLevel.DEBUG, "Transaction %s (%s) finished.", iTransaction.getName(), iTransaction.getSpanContext().getTraceId().toString());
            if (this.transactionsCounter != 0 && !z) {
                ProfilingTransactionData profilingTransactionData2 = this.currentProfilingTransactionData;
                if (profilingTransactionData2 != null) {
                    profilingTransactionData2.notifyFinish(Long.valueOf(SystemClock.elapsedRealtimeNanos()), Long.valueOf(this.transactionStartNanos), Long.valueOf(Process.getElapsedCpuTime()), Long.valueOf(this.profileStartCpuMillis));
                }
                return null;
            }
            try {
                Debug.stopMethodTracing();
            } catch (Throwable th) {
                this.options.getLogger().log(SentryLevel.ERROR, "Error while stopping profiling: ", th);
            }
            this.frameMetricsCollector.stopCollection(this.frameMetricsCollectorId);
            long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            long elapsedCpuTime = Process.getElapsedCpuTime();
            long j = elapsedRealtimeNanos - this.transactionStartNanos;
            ArrayList arrayList = new ArrayList(1);
            ProfilingTransactionData profilingTransactionData3 = this.currentProfilingTransactionData;
            if (profilingTransactionData3 != null) {
                arrayList.add(profilingTransactionData3);
            }
            this.currentProfilingTransactionData = null;
            this.transactionsCounter = 0;
            this.currentTransaction = null;
            Future<?> future = this.scheduledFinish;
            if (future != null) {
                future.cancel(true);
                this.scheduledFinish = null;
            }
            if (this.traceFile == null) {
                this.options.getLogger().log(SentryLevel.ERROR, "Trace file does not exists", new Object[0]);
                return null;
            }
            ActivityManager.MemoryInfo memInfo = getMemInfo();
            String l = memInfo != null ? Long.toString(memInfo.totalMem) : "0";
            String[] strArr = Build.SUPPORTED_ABIS;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((ProfilingTransactionData) it.next()).notifyFinish(Long.valueOf(elapsedRealtimeNanos), Long.valueOf(this.transactionStartNanos), Long.valueOf(elapsedCpuTime), Long.valueOf(this.profileStartCpuMillis));
                elapsedRealtimeNanos = elapsedRealtimeNanos;
            }
            if (!this.slowFrameRenderMeasurements.isEmpty()) {
                this.measurementsMap.put(ProfileMeasurement.ID_SLOW_FRAME_RENDERS, new ProfileMeasurement(ProfileMeasurement.UNIT_NANOSECONDS, this.slowFrameRenderMeasurements));
            }
            if (!this.frozenFrameRenderMeasurements.isEmpty()) {
                this.measurementsMap.put(ProfileMeasurement.ID_FROZEN_FRAME_RENDERS, new ProfileMeasurement(ProfileMeasurement.UNIT_NANOSECONDS, this.frozenFrameRenderMeasurements));
            }
            if (!this.screenFrameRateMeasurements.isEmpty()) {
                this.measurementsMap.put(ProfileMeasurement.ID_SCREEN_FRAME_RATES, new ProfileMeasurement(ProfileMeasurement.UNIT_HZ, this.screenFrameRateMeasurements));
            }
            putPerformanceCollectionDataInMeasurements(list);
            return new ProfilingTraceData(this.traceFile, arrayList, iTransaction, Long.toString(j), this.buildInfoProvider.getSdkInfoVersion(), (strArr == null || strArr.length <= 0) ? "" : strArr[0], new Callable() { // from class: io.sentry.android.core.AndroidTransactionProfiler$$ExternalSyntheticLambda1
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    List readMaxFrequencies;
                    readMaxFrequencies = CpuInfoUtils.getInstance().readMaxFrequencies();
                    return readMaxFrequencies;
                }
            }, this.buildInfoProvider.getManufacturer(), this.buildInfoProvider.getModel(), this.buildInfoProvider.getVersionRelease(), this.buildInfoProvider.isEmulator(), l, this.options.getProguardUuid(), this.options.getRelease(), this.options.getEnvironment(), z ? ProfilingTraceData.TRUNCATION_REASON_TIMEOUT : ProfilingTraceData.TRUNCATION_REASON_NORMAL, this.measurementsMap);
        }
        this.options.getLogger().log(SentryLevel.INFO, "Transaction %s (%s) finished, but was not currently being profiled. Skipping", iTransaction.getName(), iTransaction.getSpanContext().getTraceId().toString());
        return null;
    }

    private void putPerformanceCollectionDataInMeasurements(List<PerformanceCollectionData> list) {
        if (this.buildInfoProvider.getSdkInfoVersion() < 21) {
            return;
        }
        long elapsedRealtimeNanos = (SystemClock.elapsedRealtimeNanos() - this.transactionStartNanos) - TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
        if (list != null) {
            ArrayDeque arrayDeque = new ArrayDeque(list.size());
            ArrayDeque arrayDeque2 = new ArrayDeque(list.size());
            ArrayDeque arrayDeque3 = new ArrayDeque(list.size());
            for (PerformanceCollectionData performanceCollectionData : list) {
                CpuCollectionData cpuData = performanceCollectionData.getCpuData();
                MemoryCollectionData memoryData = performanceCollectionData.getMemoryData();
                if (cpuData != null) {
                    arrayDeque3.add(new ProfileMeasurementValue(Long.valueOf(TimeUnit.MILLISECONDS.toNanos(cpuData.getTimestampMillis()) + elapsedRealtimeNanos), Double.valueOf(cpuData.getCpuUsagePercentage())));
                }
                if (memoryData != null && memoryData.getUsedHeapMemory() > -1) {
                    arrayDeque.add(new ProfileMeasurementValue(Long.valueOf(TimeUnit.MILLISECONDS.toNanos(memoryData.getTimestampMillis()) + elapsedRealtimeNanos), Long.valueOf(memoryData.getUsedHeapMemory())));
                }
                if (memoryData != null && memoryData.getUsedNativeMemory() > -1) {
                    arrayDeque2.add(new ProfileMeasurementValue(Long.valueOf(TimeUnit.MILLISECONDS.toNanos(memoryData.getTimestampMillis()) + elapsedRealtimeNanos), Long.valueOf(memoryData.getUsedNativeMemory())));
                }
            }
            if (!arrayDeque3.isEmpty()) {
                this.measurementsMap.put(ProfileMeasurement.ID_CPU_USAGE, new ProfileMeasurement(ProfileMeasurement.UNIT_PERCENT, arrayDeque3));
            }
            if (!arrayDeque.isEmpty()) {
                this.measurementsMap.put(ProfileMeasurement.ID_MEMORY_FOOTPRINT, new ProfileMeasurement(ProfileMeasurement.UNIT_BYTES, arrayDeque));
            }
            if (arrayDeque2.isEmpty()) {
                return;
            }
            this.measurementsMap.put(ProfileMeasurement.ID_MEMORY_NATIVE_FOOTPRINT, new ProfileMeasurement(ProfileMeasurement.UNIT_BYTES, arrayDeque2));
        }
    }

    @Override // io.sentry.ITransactionProfiler
    public void close() {
        Future<?> future = this.scheduledFinish;
        if (future != null) {
            future.cancel(true);
            this.scheduledFinish = null;
        }
        ITransaction iTransaction = this.currentTransaction;
        if (iTransaction != null) {
            onTransactionFinish(iTransaction, true, null);
        }
    }

    private ActivityManager.MemoryInfo getMemInfo() {
        try {
            ActivityManager activityManager = (ActivityManager) this.context.getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            if (activityManager != null) {
                activityManager.getMemoryInfo(memoryInfo);
                return memoryInfo;
            }
            this.options.getLogger().log(SentryLevel.INFO, "Error getting MemoryInfo.", new Object[0]);
            return null;
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, "Error getting MemoryInfo.", th);
            return null;
        }
    }

    ITransaction getCurrentTransaction() {
        return this.currentTransaction;
    }
}
