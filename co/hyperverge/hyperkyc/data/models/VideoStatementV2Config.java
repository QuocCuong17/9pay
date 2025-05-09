package co.hyperverge.hyperkyc.data.models;

import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoStatementV2Config.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0000\u0018\u00002\u00020\u0001Bw\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0006\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0013\u0010\r\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u001a\u0010\u0011\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0018\"\u0004\b\u001e\u0010\u001aR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0017¨\u0006'"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/VideoStatementV2Config;", "", "allowedAttempts", "", "userData", "", "", "validateSignature", "", "livenessAPI", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;", "faceMatchAPI", "sttAPI", "logVideoStatementV2API", "videoUploadAPI", "statement", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2;", "showEndState", "isSuccess", "(ILjava/util/Map;ZLco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2;Ljava/lang/String;Ljava/lang/String;)V", "getAllowedAttempts", "()I", "getFaceMatchAPI", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;", "()Ljava/lang/String;", "setSuccess", "(Ljava/lang/String;)V", "getLivenessAPI", "getLogVideoStatementV2API", "getShowEndState", "setShowEndState", "getStatement", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2;", "getSttAPI", "getUserData", "()Ljava/util/Map;", "getValidateSignature", "()Z", "getVideoUploadAPI", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VideoStatementV2Config {
    private final int allowedAttempts;
    private final WorkflowModule.Properties.VideoStatementV2API faceMatchAPI;
    private String isSuccess;
    private final WorkflowModule.Properties.VideoStatementV2API livenessAPI;
    private final WorkflowModule.Properties.VideoStatementV2API logVideoStatementV2API;
    private String showEndState;
    private final WorkflowModule.Properties.StatementV2 statement;
    private final WorkflowModule.Properties.VideoStatementV2API sttAPI;
    private final Map<String, String> userData;
    private final boolean validateSignature;
    private final WorkflowModule.Properties.VideoStatementV2API videoUploadAPI;

    public VideoStatementV2Config(int i, Map<String, String> userData, boolean z, WorkflowModule.Properties.VideoStatementV2API videoStatementV2API, WorkflowModule.Properties.VideoStatementV2API videoStatementV2API2, WorkflowModule.Properties.VideoStatementV2API videoStatementV2API3, WorkflowModule.Properties.VideoStatementV2API videoStatementV2API4, WorkflowModule.Properties.VideoStatementV2API videoStatementV2API5, WorkflowModule.Properties.StatementV2 statementV2, String showEndState, String str) {
        Intrinsics.checkNotNullParameter(userData, "userData");
        Intrinsics.checkNotNullParameter(showEndState, "showEndState");
        this.allowedAttempts = i;
        this.userData = userData;
        this.validateSignature = z;
        this.livenessAPI = videoStatementV2API;
        this.faceMatchAPI = videoStatementV2API2;
        this.sttAPI = videoStatementV2API3;
        this.logVideoStatementV2API = videoStatementV2API4;
        this.videoUploadAPI = videoStatementV2API5;
        this.statement = statementV2;
        this.showEndState = showEndState;
        this.isSuccess = str;
    }

    public final int getAllowedAttempts() {
        return this.allowedAttempts;
    }

    public final Map<String, String> getUserData() {
        return this.userData;
    }

    public final boolean getValidateSignature() {
        return this.validateSignature;
    }

    public final WorkflowModule.Properties.VideoStatementV2API getLivenessAPI() {
        return this.livenessAPI;
    }

    public final WorkflowModule.Properties.VideoStatementV2API getFaceMatchAPI() {
        return this.faceMatchAPI;
    }

    public final WorkflowModule.Properties.VideoStatementV2API getSttAPI() {
        return this.sttAPI;
    }

    public final WorkflowModule.Properties.VideoStatementV2API getLogVideoStatementV2API() {
        return this.logVideoStatementV2API;
    }

    public final WorkflowModule.Properties.VideoStatementV2API getVideoUploadAPI() {
        return this.videoUploadAPI;
    }

    public final WorkflowModule.Properties.StatementV2 getStatement() {
        return this.statement;
    }

    public final String getShowEndState() {
        return this.showEndState;
    }

    public final void setShowEndState(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.showEndState = str;
    }

    /* renamed from: isSuccess, reason: from getter */
    public final String getIsSuccess() {
        return this.isSuccess;
    }

    public final void setSuccess(String str) {
        this.isSuccess = str;
    }
}
