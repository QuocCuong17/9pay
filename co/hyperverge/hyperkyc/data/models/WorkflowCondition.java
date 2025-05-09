package co.hyperverge.hyperkyc.data.models;

import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkflowConfig.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0081\b\u0018\u00002\u00020\u0001:\u0001\u001eB5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0007HÆ\u0003J?\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001f"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowCondition;", "Ljava/io/Serializable;", "ifTrue", "", "ifFalse", WorkflowModule.Properties.Section.Component.Validation.Type.RULE, "ifTrueConfigs", "Lco/hyperverge/hyperkyc/data/models/WorkflowCondition$EvalResultConfigs;", "ifFalseConfigs", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/WorkflowCondition$EvalResultConfigs;Lco/hyperverge/hyperkyc/data/models/WorkflowCondition$EvalResultConfigs;)V", "getIfFalse", "()Ljava/lang/String;", "getIfFalseConfigs", "()Lco/hyperverge/hyperkyc/data/models/WorkflowCondition$EvalResultConfigs;", "getIfTrue", "getIfTrueConfigs", "getRule", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "EvalResultConfigs", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class WorkflowCondition implements Serializable {

    @SerializedName("if_false")
    private final String ifFalse;

    @SerializedName("ifFalseConfigs")
    private final EvalResultConfigs ifFalseConfigs;

    @SerializedName("if_true")
    private final String ifTrue;

    @SerializedName("ifTrueConfigs")
    private final EvalResultConfigs ifTrueConfigs;

    @SerializedName(WorkflowModule.Properties.Section.Component.Validation.Type.RULE)
    private final String rule;

    public static /* synthetic */ WorkflowCondition copy$default(WorkflowCondition workflowCondition, String str, String str2, String str3, EvalResultConfigs evalResultConfigs, EvalResultConfigs evalResultConfigs2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = workflowCondition.ifTrue;
        }
        if ((i & 2) != 0) {
            str2 = workflowCondition.ifFalse;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = workflowCondition.rule;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            evalResultConfigs = workflowCondition.ifTrueConfigs;
        }
        EvalResultConfigs evalResultConfigs3 = evalResultConfigs;
        if ((i & 16) != 0) {
            evalResultConfigs2 = workflowCondition.ifFalseConfigs;
        }
        return workflowCondition.copy(str, str4, str5, evalResultConfigs3, evalResultConfigs2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getIfTrue() {
        return this.ifTrue;
    }

    /* renamed from: component2, reason: from getter */
    public final String getIfFalse() {
        return this.ifFalse;
    }

    /* renamed from: component3, reason: from getter */
    public final String getRule() {
        return this.rule;
    }

    /* renamed from: component4, reason: from getter */
    public final EvalResultConfigs getIfTrueConfigs() {
        return this.ifTrueConfigs;
    }

    /* renamed from: component5, reason: from getter */
    public final EvalResultConfigs getIfFalseConfigs() {
        return this.ifFalseConfigs;
    }

    public final WorkflowCondition copy(String ifTrue, String ifFalse, String rule, EvalResultConfigs ifTrueConfigs, EvalResultConfigs ifFalseConfigs) {
        Intrinsics.checkNotNullParameter(ifTrue, "ifTrue");
        Intrinsics.checkNotNullParameter(ifFalse, "ifFalse");
        Intrinsics.checkNotNullParameter(rule, "rule");
        return new WorkflowCondition(ifTrue, ifFalse, rule, ifTrueConfigs, ifFalseConfigs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WorkflowCondition)) {
            return false;
        }
        WorkflowCondition workflowCondition = (WorkflowCondition) other;
        return Intrinsics.areEqual(this.ifTrue, workflowCondition.ifTrue) && Intrinsics.areEqual(this.ifFalse, workflowCondition.ifFalse) && Intrinsics.areEqual(this.rule, workflowCondition.rule) && Intrinsics.areEqual(this.ifTrueConfigs, workflowCondition.ifTrueConfigs) && Intrinsics.areEqual(this.ifFalseConfigs, workflowCondition.ifFalseConfigs);
    }

    public int hashCode() {
        int hashCode = ((((this.ifTrue.hashCode() * 31) + this.ifFalse.hashCode()) * 31) + this.rule.hashCode()) * 31;
        EvalResultConfigs evalResultConfigs = this.ifTrueConfigs;
        int hashCode2 = (hashCode + (evalResultConfigs == null ? 0 : evalResultConfigs.hashCode())) * 31;
        EvalResultConfigs evalResultConfigs2 = this.ifFalseConfigs;
        return hashCode2 + (evalResultConfigs2 != null ? evalResultConfigs2.hashCode() : 0);
    }

    public String toString() {
        return "WorkflowCondition(ifTrue=" + this.ifTrue + ", ifFalse=" + this.ifFalse + ", rule=" + this.rule + ", ifTrueConfigs=" + this.ifTrueConfigs + ", ifFalseConfigs=" + this.ifFalseConfigs + ')';
    }

    public WorkflowCondition(String ifTrue, String ifFalse, String rule, EvalResultConfigs evalResultConfigs, EvalResultConfigs evalResultConfigs2) {
        Intrinsics.checkNotNullParameter(ifTrue, "ifTrue");
        Intrinsics.checkNotNullParameter(ifFalse, "ifFalse");
        Intrinsics.checkNotNullParameter(rule, "rule");
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
        this.rule = rule;
        this.ifTrueConfigs = evalResultConfigs;
        this.ifFalseConfigs = evalResultConfigs2;
    }

    public /* synthetic */ WorkflowCondition(String str, String str2, String str3, EvalResultConfigs evalResultConfigs, EvalResultConfigs evalResultConfigs2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? null : evalResultConfigs, (i & 16) != 0 ? null : evalResultConfigs2);
    }

    public final String getIfTrue() {
        return this.ifTrue;
    }

    public final String getIfFalse() {
        return this.ifFalse;
    }

    public final String getRule() {
        return this.rule;
    }

    public final EvalResultConfigs getIfTrueConfigs() {
        return this.ifTrueConfigs;
    }

    public final EvalResultConfigs getIfFalseConfigs() {
        return this.ifFalseConfigs;
    }

    /* compiled from: WorkflowConfig.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowCondition$EvalResultConfigs;", "Ljava/io/Serializable;", "resumeFrom", "", "reason", "(Ljava/lang/String;Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "getResumeFrom", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class EvalResultConfigs implements Serializable {

        @SerializedName("reason")
        private final String reason;

        @SerializedName("resumeFrom")
        private final String resumeFrom;

        /* JADX WARN: Multi-variable type inference failed */
        public EvalResultConfigs() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ EvalResultConfigs copy$default(EvalResultConfigs evalResultConfigs, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = evalResultConfigs.resumeFrom;
            }
            if ((i & 2) != 0) {
                str2 = evalResultConfigs.reason;
            }
            return evalResultConfigs.copy(str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getResumeFrom() {
            return this.resumeFrom;
        }

        /* renamed from: component2, reason: from getter */
        public final String getReason() {
            return this.reason;
        }

        public final EvalResultConfigs copy(String resumeFrom, String reason) {
            return new EvalResultConfigs(resumeFrom, reason);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EvalResultConfigs)) {
                return false;
            }
            EvalResultConfigs evalResultConfigs = (EvalResultConfigs) other;
            return Intrinsics.areEqual(this.resumeFrom, evalResultConfigs.resumeFrom) && Intrinsics.areEqual(this.reason, evalResultConfigs.reason);
        }

        public int hashCode() {
            String str = this.resumeFrom;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.reason;
            return hashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "EvalResultConfigs(resumeFrom=" + this.resumeFrom + ", reason=" + this.reason + ')';
        }

        public EvalResultConfigs(String str, String str2) {
            this.resumeFrom = str;
            this.reason = str2;
        }

        public /* synthetic */ EvalResultConfigs(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
        }

        public final String getResumeFrom() {
            return this.resumeFrom;
        }

        public final String getReason() {
            return this.reason;
        }
    }
}
