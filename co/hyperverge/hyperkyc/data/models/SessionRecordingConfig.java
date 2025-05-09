package co.hyperverge.hyperkyc.data.models;

import co.hyperverge.hyperkyc.ui.UploadingFragment;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SessionRecordingConfig.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\u0015\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\nHÆ\u0003JS\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\nHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u00032\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010¨\u0006!"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/SessionRecordingConfig;", "", "recordAudio", "", "showConsent", "uploadSession", "uploadUrl", "", UploadingFragment.ARG_KEY_STOP_MODULE_ID, "textConfigs", "", "(ZZZLjava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getRecordAudio", "()Z", "getShowConsent", "getStopModuleId", "()Ljava/lang/String;", "getTextConfigs", "()Ljava/util/Map;", "getUploadSession", "getUploadUrl", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class SessionRecordingConfig {
    private final boolean recordAudio;
    private final boolean showConsent;
    private final String stopModuleId;
    private final Map<String, String> textConfigs;
    private final boolean uploadSession;
    private final String uploadUrl;

    public static /* synthetic */ SessionRecordingConfig copy$default(SessionRecordingConfig sessionRecordingConfig, boolean z, boolean z2, boolean z3, String str, String str2, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            z = sessionRecordingConfig.recordAudio;
        }
        if ((i & 2) != 0) {
            z2 = sessionRecordingConfig.showConsent;
        }
        boolean z4 = z2;
        if ((i & 4) != 0) {
            z3 = sessionRecordingConfig.uploadSession;
        }
        boolean z5 = z3;
        if ((i & 8) != 0) {
            str = sessionRecordingConfig.uploadUrl;
        }
        String str3 = str;
        if ((i & 16) != 0) {
            str2 = sessionRecordingConfig.stopModuleId;
        }
        String str4 = str2;
        if ((i & 32) != 0) {
            map = sessionRecordingConfig.textConfigs;
        }
        return sessionRecordingConfig.copy(z, z4, z5, str3, str4, map);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getRecordAudio() {
        return this.recordAudio;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getShowConsent() {
        return this.showConsent;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getUploadSession() {
        return this.uploadSession;
    }

    /* renamed from: component4, reason: from getter */
    public final String getUploadUrl() {
        return this.uploadUrl;
    }

    /* renamed from: component5, reason: from getter */
    public final String getStopModuleId() {
        return this.stopModuleId;
    }

    public final Map<String, String> component6() {
        return this.textConfigs;
    }

    public final SessionRecordingConfig copy(boolean recordAudio, boolean showConsent, boolean uploadSession, String uploadUrl, String stopModuleId, Map<String, String> textConfigs) {
        Intrinsics.checkNotNullParameter(stopModuleId, "stopModuleId");
        Intrinsics.checkNotNullParameter(textConfigs, "textConfigs");
        return new SessionRecordingConfig(recordAudio, showConsent, uploadSession, uploadUrl, stopModuleId, textConfigs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SessionRecordingConfig)) {
            return false;
        }
        SessionRecordingConfig sessionRecordingConfig = (SessionRecordingConfig) other;
        return this.recordAudio == sessionRecordingConfig.recordAudio && this.showConsent == sessionRecordingConfig.showConsent && this.uploadSession == sessionRecordingConfig.uploadSession && Intrinsics.areEqual(this.uploadUrl, sessionRecordingConfig.uploadUrl) && Intrinsics.areEqual(this.stopModuleId, sessionRecordingConfig.stopModuleId) && Intrinsics.areEqual(this.textConfigs, sessionRecordingConfig.textConfigs);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
    public int hashCode() {
        boolean z = this.recordAudio;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        ?? r2 = this.showConsent;
        int i2 = r2;
        if (r2 != 0) {
            i2 = 1;
        }
        int i3 = (i + i2) * 31;
        boolean z2 = this.uploadSession;
        int i4 = (i3 + (z2 ? 1 : z2 ? 1 : 0)) * 31;
        String str = this.uploadUrl;
        return ((((i4 + (str == null ? 0 : str.hashCode())) * 31) + this.stopModuleId.hashCode()) * 31) + this.textConfigs.hashCode();
    }

    public String toString() {
        return "SessionRecordingConfig(recordAudio=" + this.recordAudio + ", showConsent=" + this.showConsent + ", uploadSession=" + this.uploadSession + ", uploadUrl=" + this.uploadUrl + ", stopModuleId=" + this.stopModuleId + ", textConfigs=" + this.textConfigs + ')';
    }

    public SessionRecordingConfig(boolean z, boolean z2, boolean z3, String str, String stopModuleId, Map<String, String> textConfigs) {
        Intrinsics.checkNotNullParameter(stopModuleId, "stopModuleId");
        Intrinsics.checkNotNullParameter(textConfigs, "textConfigs");
        this.recordAudio = z;
        this.showConsent = z2;
        this.uploadSession = z3;
        this.uploadUrl = str;
        this.stopModuleId = stopModuleId;
        this.textConfigs = textConfigs;
    }

    public final boolean getRecordAudio() {
        return this.recordAudio;
    }

    public final boolean getShowConsent() {
        return this.showConsent;
    }

    public final boolean getUploadSession() {
        return this.uploadSession;
    }

    public final String getUploadUrl() {
        return this.uploadUrl;
    }

    public final String getStopModuleId() {
        return this.stopModuleId;
    }

    public final Map<String, String> getTextConfigs() {
        return this.textConfigs;
    }
}
