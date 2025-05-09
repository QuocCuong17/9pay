package co.hyperverge.hyperkyc.data.models;

import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoStatementConfig.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0000\u0018\u00002\u00020\u0001B\u0089\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011\u0012\u0014\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0011¢\u0006\u0002\u0010\u0014R\u0011\u0010\u000f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001dR\u001f\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001f\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b&\u0010%¨\u0006'"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/VideoStatementConfig;", "", "livenessUrl", "", "faceMatchUrl", "speechToTextMatchUrl", "logVideoStatementUrl", "faceMatchParams", "", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$RequestParam;", "start", "showInstruction", "", "allowedRestarts", "", "allowedReAttempts", "userData", "", "statements", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Statement;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ZIILjava/util/Map;Ljava/util/Map;)V", "getAllowedReAttempts", "()I", "getAllowedRestarts", "getFaceMatchParams", "()Ljava/util/List;", "setFaceMatchParams", "(Ljava/util/List;)V", "getFaceMatchUrl", "()Ljava/lang/String;", "getLivenessUrl", "getLogVideoStatementUrl", "getShowInstruction", "()Z", "getSpeechToTextMatchUrl", "getStart", "getStatements", "()Ljava/util/Map;", "getUserData", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VideoStatementConfig {
    private final int allowedReAttempts;
    private final int allowedRestarts;
    private List<WorkflowModule.Properties.RequestParam> faceMatchParams;
    private final String faceMatchUrl;
    private final String livenessUrl;
    private final String logVideoStatementUrl;
    private final boolean showInstruction;
    private final String speechToTextMatchUrl;
    private final String start;
    private final Map<String, WorkflowModule.Properties.Statement> statements;
    private final Map<String, String> userData;

    public VideoStatementConfig(String str, String str2, String str3, String str4, List<WorkflowModule.Properties.RequestParam> list, String start, boolean z, int i, int i2, Map<String, String> map, Map<String, WorkflowModule.Properties.Statement> map2) {
        Intrinsics.checkNotNullParameter(start, "start");
        this.livenessUrl = str;
        this.faceMatchUrl = str2;
        this.speechToTextMatchUrl = str3;
        this.logVideoStatementUrl = str4;
        this.faceMatchParams = list;
        this.start = start;
        this.showInstruction = z;
        this.allowedRestarts = i;
        this.allowedReAttempts = i2;
        this.userData = map;
        this.statements = map2;
    }

    public final String getLivenessUrl() {
        return this.livenessUrl;
    }

    public final String getFaceMatchUrl() {
        return this.faceMatchUrl;
    }

    public final String getSpeechToTextMatchUrl() {
        return this.speechToTextMatchUrl;
    }

    public final String getLogVideoStatementUrl() {
        return this.logVideoStatementUrl;
    }

    public final List<WorkflowModule.Properties.RequestParam> getFaceMatchParams() {
        return this.faceMatchParams;
    }

    public final void setFaceMatchParams(List<WorkflowModule.Properties.RequestParam> list) {
        this.faceMatchParams = list;
    }

    public final String getStart() {
        return this.start;
    }

    public final boolean getShowInstruction() {
        return this.showInstruction;
    }

    public final int getAllowedRestarts() {
        return this.allowedRestarts;
    }

    public final int getAllowedReAttempts() {
        return this.allowedReAttempts;
    }

    public final Map<String, String> getUserData() {
        return this.userData;
    }

    public final Map<String, WorkflowModule.Properties.Statement> getStatements() {
        return this.statements;
    }
}
