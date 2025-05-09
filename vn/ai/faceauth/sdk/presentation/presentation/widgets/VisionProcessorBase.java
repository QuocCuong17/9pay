package vn.ai.faceauth.sdk.presentation.presentation.widgets;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;
import androidx.camera.core.ImageProxy;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.gms.tasks.Tasks;
import com.google.android.odml.image.BitmapMlImageBuilder;
import com.google.android.odml.image.ByteBufferMlImageBuilder;
import com.google.android.odml.image.MediaMlImageBuilder;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.common.InputImage;
import java.nio.ByteBuffer;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.text.StringsKt;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u0000 A*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001AB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000 2\u0006\u0010!\u001a\u00020\"H\u0014J\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000 2\u0006\u0010!\u001a\u00020#H$J\u0012\u0010$\u001a\u00020\u00102\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0014J\u0014\u0010%\u001a\u00020&2\n\u0010'\u001a\u00060(j\u0002`)H$J\u001d\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00028\u00002\u0006\u0010,\u001a\u00020-H$¢\u0006\u0002\u0010.J\u001a\u0010/\u001a\u00020&2\b\u00100\u001a\u0004\u0018\u0001012\u0006\u0010,\u001a\u00020-H\u0016J$\u00102\u001a\u00020&2\b\u00103\u001a\u0004\u0018\u00010\u00122\b\u00104\u001a\u0004\u0018\u00010\u00142\u0006\u0010,\u001a\u00020-H\u0016J \u00105\u001a\u00020&2\u0006\u00103\u001a\u00020\u00122\u0006\u00104\u001a\u00020\u00142\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u00106\u001a\u00020&2\u0006\u0010!\u001a\u0002072\u0006\u0010,\u001a\u00020-H\u0017J\u0010\u00108\u001a\u00020&2\u0006\u0010,\u001a\u00020-H\u0002J8\u00109\u001a\b\u0012\u0004\u0012\u00028\u00000 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010,\u001a\u00020-2\b\u0010:\u001a\u0004\u0018\u0001012\u0006\u0010;\u001a\u00020\u00102\u0006\u0010<\u001a\u00020\u0016H\u0002J8\u00109\u001a\b\u0012\u0004\u0012\u00028\u00000 2\u0006\u0010!\u001a\u00020#2\u0006\u0010,\u001a\u00020-2\b\u0010:\u001a\u0004\u0018\u0001012\u0006\u0010;\u001a\u00020\u00102\u0006\u0010<\u001a\u00020\u0016H\u0002J\b\u0010=\u001a\u00020&H\u0002J>\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000 2\f\u0010?\u001a\b\u0012\u0004\u0012\u00028\u00000 2\u0006\u0010,\u001a\u00020-2\b\u0010:\u001a\u0004\u0018\u0001012\u0006\u0010;\u001a\u00020\u00102\u0006\u0010<\u001a\u00020\u0016H\u0002J\b\u0010@\u001a\u00020&H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/widgets/VisionProcessorBase;", "T", "Lvn/ai/faceauth/sdk/presentation/presentation/widgets/VisionImageProcessor;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "activityManager", "Landroid/app/ActivityManager;", "executor", "Lvn/ai/faceauth/sdk/presentation/presentation/widgets/ScopedExecutor;", "fpsTimer", "Ljava/util/Timer;", "frameProcessedInOneSecondInterval", "", "framesPerSecond", "isShutdown", "", "latestImage", "Ljava/nio/ByteBuffer;", "latestImageMetaData", "Lvn/ai/faceauth/sdk/presentation/presentation/widgets/FrameMetadata;", "maxDetectorMs", "", "maxFrameMs", "minDetectorMs", "minFrameMs", "numRuns", "processingImage", "processingMetaData", "totalDetectorMs", "totalFrameMs", "detectInImage", "Lcom/google/android/gms/tasks/Task;", "image", "Lcom/google/android/odml/image/MlImage;", "Lcom/google/mlkit/vision/common/InputImage;", "isMlImageEnabled", "onFailure", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "results", "graphicOverlay", "Lvn/ai/faceauth/sdk/presentation/presentation/widgets/GraphicOverlay;", "(Ljava/lang/Object;Lvn/ai/faceauth/sdk/presentation/presentation/widgets/GraphicOverlay;)V", "processBitmap", "bitmap", "Landroid/graphics/Bitmap;", "processByteBuffer", "data", "frameMetadata", "processImage", "processImageProxy", "Landroidx/camera/core/ImageProxy;", "processLatestImage", "requestDetectInImage", "originalCameraImage", "shouldShowFps", "frameStartMs", "resetLatencyStats", "setUpListener", "task", "stop", "Companion", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public abstract class VisionProcessorBase<T> implements VisionImageProcessor {
    public static String MANUAL_TESTING_LOG;
    private static String TAG;
    private ActivityManager activityManager;
    private final ScopedExecutor executor;
    private final Timer fpsTimer;
    private int frameProcessedInOneSecondInterval;
    private int framesPerSecond;
    private boolean isShutdown;
    private ByteBuffer latestImage;
    private FrameMetadata latestImageMetaData;
    private long maxDetectorMs;
    private long maxFrameMs;
    private long minDetectorMs;
    private long minFrameMs;
    private int numRuns;
    private ByteBuffer processingImage;
    private FrameMetadata processingMetaData;
    private long totalDetectorMs;
    private long totalFrameMs;

    static {
        tfwhgw.rnl(VisionProcessorBase.class, 509, TypedValues.PositionType.TYPE_POSITION_TYPE);
        INSTANCE = new Companion(null);
    }

    public VisionProcessorBase(Context context) {
        Object systemService = context.getSystemService(tfwhgw.rnigpa(97));
        if (systemService == null) {
            throw new NullPointerException(tfwhgw.rnigpa(98));
        }
        this.activityManager = (ActivityManager) systemService;
        Timer timer = new Timer();
        this.fpsTimer = timer;
        this.executor = new ScopedExecutor(TaskExecutors.MAIN_THREAD);
        this.minFrameMs = Long.MAX_VALUE;
        this.minDetectorMs = Long.MAX_VALUE;
        timer.scheduleAtFixedRate(new TimerTask(this) { // from class: vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionProcessorBase.1
            final /* synthetic */ VisionProcessorBase<T> this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                VisionProcessorBase<T> visionProcessorBase = this.this$0;
                ((VisionProcessorBase) visionProcessorBase).framesPerSecond = ((VisionProcessorBase) visionProcessorBase).frameProcessedInOneSecondInterval;
                ((VisionProcessorBase) this.this$0).frameProcessedInOneSecondInterval = 0;
            }
        }, 0L, 1000L);
    }

    private final void processImage(ByteBuffer data, FrameMetadata frameMetadata, final GraphicOverlay graphicOverlay) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Bitmap bitmap = PreferenceUtils.isCameraLiveViewportEnabled(graphicOverlay.getContext()) ? null : BitmapUtils.getBitmap(data, frameMetadata);
        if (!isMlImageEnabled(graphicOverlay.getContext())) {
            requestDetectInImage(InputImage.fromByteBuffer(data, frameMetadata.getWidth(), frameMetadata.getHeight(), frameMetadata.getRotation(), 17), graphicOverlay, bitmap, true, elapsedRealtime).addOnSuccessListener(this.executor, new OnSuccessListener() { // from class: vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionProcessorBase$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    VisionProcessorBase.this.processLatestImage(graphicOverlay);
                }
            });
            return;
        }
        MlImage build = new ByteBufferMlImageBuilder(data, frameMetadata.getWidth(), frameMetadata.getHeight(), 4).setRotation(frameMetadata.getRotation()).build();
        requestDetectInImage(build, graphicOverlay, bitmap, true, elapsedRealtime).addOnSuccessListener(this.executor, new OnSuccessListener() { // from class: vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionProcessorBase$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                VisionProcessorBase.this.processLatestImage(graphicOverlay);
            }
        });
        build.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processLatestImage(GraphicOverlay graphicOverlay) {
        synchronized (this) {
            ByteBuffer byteBuffer = this.latestImage;
            this.processingImage = byteBuffer;
            FrameMetadata frameMetadata = this.latestImageMetaData;
            this.processingMetaData = frameMetadata;
            this.latestImage = null;
            this.latestImageMetaData = null;
            if (byteBuffer != null && frameMetadata != null && !this.isShutdown) {
                processImage(byteBuffer, frameMetadata, graphicOverlay);
            }
        }
    }

    private final Task<T> requestDetectInImage(MlImage image, GraphicOverlay graphicOverlay, Bitmap originalCameraImage, boolean shouldShowFps, long frameStartMs) {
        return setUpListener(detectInImage(image), graphicOverlay, originalCameraImage, shouldShowFps, frameStartMs);
    }

    private final Task<T> requestDetectInImage(InputImage image, GraphicOverlay graphicOverlay, Bitmap originalCameraImage, boolean shouldShowFps, long frameStartMs) {
        return setUpListener(detectInImage(image), graphicOverlay, originalCameraImage, shouldShowFps, frameStartMs);
    }

    private final void resetLatencyStats() {
        this.numRuns = 0;
        this.totalFrameMs = 0L;
        this.maxFrameMs = 0L;
        this.minFrameMs = Long.MAX_VALUE;
        this.totalDetectorMs = 0L;
        this.maxDetectorMs = 0L;
        this.minDetectorMs = Long.MAX_VALUE;
    }

    private final Task<T> setUpListener(Task<T> task, final GraphicOverlay graphicOverlay, final Bitmap originalCameraImage, final boolean shouldShowFps, final long frameStartMs) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        return task.addOnSuccessListener(this.executor, new OnSuccessListener() { // from class: vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionProcessorBase$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                VisionProcessorBase.m2998setUpListener$lambda4(frameStartMs, elapsedRealtime, this, graphicOverlay, originalCameraImage, shouldShowFps, obj);
            }
        }).addOnFailureListener(this.executor, new OnFailureListener() { // from class: vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionProcessorBase$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                VisionProcessorBase.m2999setUpListener$lambda5(GraphicOverlay.this, this, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setUpListener$lambda-4, reason: not valid java name */
    public static final void m2998setUpListener$lambda4(long j, long j2, VisionProcessorBase visionProcessorBase, GraphicOverlay graphicOverlay, Bitmap bitmap, boolean z, Object obj) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j3 = elapsedRealtime - j;
        long j4 = elapsedRealtime - j2;
        if (visionProcessorBase.numRuns >= 500) {
            visionProcessorBase.resetLatencyStats();
        }
        visionProcessorBase.numRuns++;
        visionProcessorBase.frameProcessedInOneSecondInterval++;
        visionProcessorBase.totalFrameMs += j3;
        visionProcessorBase.maxFrameMs = Math.max(j3, visionProcessorBase.maxFrameMs);
        visionProcessorBase.minFrameMs = Math.min(j3, visionProcessorBase.minFrameMs);
        visionProcessorBase.totalDetectorMs += j4;
        visionProcessorBase.maxDetectorMs = Math.max(j4, visionProcessorBase.maxDetectorMs);
        visionProcessorBase.minDetectorMs = Math.min(j4, visionProcessorBase.minDetectorMs);
        if (visionProcessorBase.frameProcessedInOneSecondInterval == 1) {
            String str = tfwhgw.rnigpa(99) + visionProcessorBase.numRuns;
            String rnigpa = tfwhgw.rnigpa(100);
            Log.d(rnigpa, str);
            StringBuilder sb = new StringBuilder(tfwhgw.rnigpa(101));
            sb.append(visionProcessorBase.maxFrameMs);
            String rnigpa2 = tfwhgw.rnigpa(102);
            sb.append(rnigpa2);
            sb.append(visionProcessorBase.minFrameMs);
            String rnigpa3 = tfwhgw.rnigpa(103);
            sb.append(rnigpa3);
            sb.append(visionProcessorBase.totalFrameMs / visionProcessorBase.numRuns);
            Log.d(rnigpa, sb.toString());
            Log.d(rnigpa, tfwhgw.rnigpa(104) + visionProcessorBase.maxDetectorMs + rnigpa2 + visionProcessorBase.minDetectorMs + rnigpa3 + (visionProcessorBase.totalDetectorMs / visionProcessorBase.numRuns));
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            visionProcessorBase.activityManager.getMemoryInfo(memoryInfo);
            Log.d(rnigpa, tfwhgw.rnigpa(105) + (memoryInfo.availMem / 1048576) + tfwhgw.rnigpa(106));
        }
        graphicOverlay.clear();
        if (bitmap != null) {
            graphicOverlay.add(new CameraImageGraphic(graphicOverlay, bitmap));
        }
        visionProcessorBase.onSuccess(obj, graphicOverlay);
        if (!PreferenceUtils.shouldHideDetectionInfo(graphicOverlay.getContext())) {
            graphicOverlay.add(new InferenceInfoGraphic(graphicOverlay, j3, j4, z ? Integer.valueOf(visionProcessorBase.framesPerSecond) : null));
        }
        graphicOverlay.postInvalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setUpListener$lambda-5, reason: not valid java name */
    public static final void m2999setUpListener$lambda5(GraphicOverlay graphicOverlay, VisionProcessorBase visionProcessorBase, Exception exc) {
        graphicOverlay.clear();
        graphicOverlay.postInvalidate();
        String str = tfwhgw.rnigpa(107) + exc.getLocalizedMessage();
        Context context = graphicOverlay.getContext();
        String rnigpa = tfwhgw.rnigpa(108);
        Toast.makeText(context, StringsKt.trimIndent(rnigpa + str + tfwhgw.rnigpa(109) + exc.getCause() + rnigpa), 0).show();
        Log.d(tfwhgw.rnigpa(110), str);
        exc.printStackTrace();
        visionProcessorBase.onFailure(exc);
    }

    public Task<T> detectInImage(MlImage image) {
        return Tasks.forException(new MlKitException(tfwhgw.rnigpa(111), 3));
    }

    public abstract Task<T> detectInImage(InputImage image);

    public boolean isMlImageEnabled(Context context) {
        return false;
    }

    public abstract void onFailure(Exception e);

    public abstract void onSuccess(T results, GraphicOverlay graphicOverlay);

    @Override // vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionImageProcessor
    public void processBitmap(Bitmap bitmap, GraphicOverlay graphicOverlay) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (!isMlImageEnabled(graphicOverlay.getContext())) {
            requestDetectInImage(InputImage.fromBitmap(bitmap, 0), graphicOverlay, (Bitmap) null, false, elapsedRealtime);
            return;
        }
        MlImage build = new BitmapMlImageBuilder(bitmap).build();
        requestDetectInImage(build, graphicOverlay, (Bitmap) null, false, elapsedRealtime);
        build.close();
    }

    @Override // vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionImageProcessor
    public void processByteBuffer(ByteBuffer data, FrameMetadata frameMetadata, GraphicOverlay graphicOverlay) {
        synchronized (this) {
            this.latestImage = data;
            this.latestImageMetaData = frameMetadata;
            if (this.processingImage == null && this.processingMetaData == null) {
                processLatestImage(graphicOverlay);
            }
        }
    }

    @Override // vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionImageProcessor
    public void processImageProxy(final ImageProxy image, GraphicOverlay graphicOverlay) {
        Task<T> requestDetectInImage;
        OnCompleteListener<T> onCompleteListener;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.isShutdown) {
            return;
        }
        Bitmap bitmap = !PreferenceUtils.isCameraLiveViewportEnabled(graphicOverlay.getContext()) ? BitmapUtils.getBitmap(image) : null;
        if (isMlImageEnabled(graphicOverlay.getContext())) {
            requestDetectInImage = requestDetectInImage(new MediaMlImageBuilder(image.getImage()).setRotation(image.getImageInfo().getRotationDegrees()).build(), graphicOverlay, bitmap, true, elapsedRealtime);
            onCompleteListener = new OnCompleteListener() { // from class: vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionProcessorBase$$ExternalSyntheticLambda4
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    ImageProxy.this.close();
                }
            };
        } else {
            requestDetectInImage = requestDetectInImage(InputImage.fromMediaImage(image.getImage(), image.getImageInfo().getRotationDegrees()), graphicOverlay, bitmap, true, elapsedRealtime);
            onCompleteListener = new OnCompleteListener() { // from class: vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionProcessorBase$$ExternalSyntheticLambda5
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    ImageProxy.this.close();
                }
            };
        }
        requestDetectInImage.addOnCompleteListener(onCompleteListener);
    }

    @Override // vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionImageProcessor
    public void stop() {
        this.executor.shutdown();
        this.isShutdown = true;
        resetLatencyStats();
        this.fpsTimer.cancel();
    }
}
