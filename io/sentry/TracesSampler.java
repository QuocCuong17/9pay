package io.sentry;

import io.sentry.util.Objects;
import java.security.SecureRandom;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class TracesSampler {
    private static final Double DEFAULT_TRACES_SAMPLE_RATE = Double.valueOf(1.0d);
    private final SentryOptions options;
    private final SecureRandom random;

    public TracesSampler(SentryOptions sentryOptions) {
        this((SentryOptions) Objects.requireNonNull(sentryOptions, "options are required"), new SecureRandom());
    }

    TracesSampler(SentryOptions sentryOptions, SecureRandom secureRandom) {
        this.options = sentryOptions;
        this.random = secureRandom;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0082 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x004e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public TracesSamplingDecision sample(SamplingContext samplingContext) {
        Double sample;
        Double d;
        TracesSamplingDecision parentSamplingDecision;
        TracesSamplingDecision samplingDecision = samplingContext.getTransactionContext().getSamplingDecision();
        if (samplingDecision != null) {
            return samplingDecision;
        }
        if (this.options.getProfilesSampler() != null) {
            try {
                sample = this.options.getProfilesSampler().sample(samplingContext);
            } catch (Throwable th) {
                this.options.getLogger().log(SentryLevel.ERROR, "Error in the 'ProfilesSamplerCallback' callback.", th);
            }
            if (sample == null) {
                sample = this.options.getProfilesSampleRate();
            }
            Boolean valueOf = Boolean.valueOf(sample == null && sample(sample));
            if (this.options.getTracesSampler() != null) {
                try {
                    d = this.options.getTracesSampler().sample(samplingContext);
                } catch (Throwable th2) {
                    this.options.getLogger().log(SentryLevel.ERROR, "Error in the 'TracesSamplerCallback' callback.", th2);
                    d = null;
                }
                if (d != null) {
                    return new TracesSamplingDecision(Boolean.valueOf(sample(d)), d, valueOf, sample);
                }
            }
            parentSamplingDecision = samplingContext.getTransactionContext().getParentSamplingDecision();
            if (parentSamplingDecision == null) {
                return parentSamplingDecision;
            }
            Double tracesSampleRate = this.options.getTracesSampleRate();
            Double d2 = Boolean.TRUE.equals(this.options.getEnableTracing()) ? DEFAULT_TRACES_SAMPLE_RATE : null;
            if (tracesSampleRate == null) {
                tracesSampleRate = d2;
            }
            if (tracesSampleRate != null) {
                return new TracesSamplingDecision(Boolean.valueOf(sample(tracesSampleRate)), tracesSampleRate, valueOf, sample);
            }
            return new TracesSamplingDecision(false, null, false, null);
        }
        sample = null;
        if (sample == null) {
        }
        Boolean valueOf2 = Boolean.valueOf(sample == null && sample(sample));
        if (this.options.getTracesSampler() != null) {
        }
        parentSamplingDecision = samplingContext.getTransactionContext().getParentSamplingDecision();
        if (parentSamplingDecision == null) {
        }
    }

    private boolean sample(Double d) {
        return d.doubleValue() >= this.random.nextDouble();
    }
}
