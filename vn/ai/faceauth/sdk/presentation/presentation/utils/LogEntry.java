package vn.ai.faceauth.sdk.presentation.presentation.utils;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;
import org.bouncycastle.crypto.tls.CipherSuite;
import vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/utils/LogEntry;", "", "stepLiveness", "Lvn/ai/faceauth/sdk/presentation/domain/model/StepLiveness;", "timestamp", "", "(Lvn/ai/faceauth/sdk/presentation/domain/model/StepLiveness;Ljava/lang/String;)V", "getStepLiveness", "()Lvn/ai/faceauth/sdk/presentation/domain/model/StepLiveness;", "getTimestamp", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class LogEntry {
    private final StepLiveness stepLiveness;
    private final String timestamp;

    public LogEntry(StepLiveness stepLiveness, String str) {
        this.stepLiveness = stepLiveness;
        this.timestamp = str;
    }

    public static /* synthetic */ LogEntry copy$default(LogEntry logEntry, StepLiveness stepLiveness, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            stepLiveness = logEntry.stepLiveness;
        }
        if ((i & 2) != 0) {
            str = logEntry.timestamp;
        }
        return logEntry.copy(stepLiveness, str);
    }

    /* renamed from: component1, reason: from getter */
    public final StepLiveness getStepLiveness() {
        return this.stepLiveness;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTimestamp() {
        return this.timestamp;
    }

    public final LogEntry copy(StepLiveness stepLiveness, String timestamp) {
        return new LogEntry(stepLiveness, timestamp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LogEntry)) {
            return false;
        }
        LogEntry logEntry = (LogEntry) other;
        return this.stepLiveness == logEntry.stepLiveness && Intrinsics.areEqual(this.timestamp, logEntry.timestamp);
    }

    public final StepLiveness getStepLiveness() {
        return this.stepLiveness;
    }

    public final String getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        return this.timestamp.hashCode() + (this.stepLiveness.hashCode() * 31);
    }

    public String toString() {
        return tfwhgw.rnigpa(CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256) + this.stepLiveness + tfwhgw.rnigpa(CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256) + this.timestamp + ')';
    }
}
