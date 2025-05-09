package co.hyperverge.hyperkyc.data.models.state;

import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
import co.hyperverge.hyperkyc.data.models.KycCountry;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkflowState.kt */
@Deprecated(message = "use TransactionState")
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0081\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0011¢\u0006\u0002\u0010\u0012J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\u0019\u0010#\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tHÆ\u0003J\t\u0010$\u001a\u00020\u000bHÆ\u0003J\t\u0010%\u001a\u00020\rHÆ\u0003J\t\u0010&\u001a\u00020\u000fHÆ\u0003J\u0015\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0011HÆ\u0003Jk\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0011HÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020\u0003HÖ\u0001J\t\u0010-\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR!\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006."}, d2 = {"Lco/hyperverge/hyperkyc/data/models/state/WorkflowState;", "", "previousFlowPos", "", "journeyId", "", "workflowUIStateList", "Ljava/util/ArrayList;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "Lkotlin/collections/ArrayList;", "selectedCountry", "Lco/hyperverge/hyperkyc/data/models/KycCountry;", HyperKycData.ARG_KEY, "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;", HyperKycConfig.ARG_KEY, "Lco/hyperverge/hyperkyc/data/models/HyperKycConfig;", "resultDetailsMap", "", "(ILjava/lang/String;Ljava/util/ArrayList;Lco/hyperverge/hyperkyc/data/models/KycCountry;Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;Lco/hyperverge/hyperkyc/data/models/HyperKycConfig;Ljava/util/Map;)V", "getHyperKycConfig", "()Lco/hyperverge/hyperkyc/data/models/HyperKycConfig;", "getHyperKycData", "()Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;", "getJourneyId", "()Ljava/lang/String;", "getPreviousFlowPos", "()I", "getResultDetailsMap", "()Ljava/util/Map;", "getSelectedCountry", "()Lco/hyperverge/hyperkyc/data/models/KycCountry;", "getWorkflowUIStateList", "()Ljava/util/ArrayList;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class WorkflowState {
    private final HyperKycConfig hyperKycConfig;
    private final HyperKycData hyperKycData;
    private final String journeyId;
    private final int previousFlowPos;
    private final Map<String, String> resultDetailsMap;
    private final KycCountry selectedCountry;
    private final ArrayList<WorkflowUIState> workflowUIStateList;

    public static /* synthetic */ WorkflowState copy$default(WorkflowState workflowState, int i, String str, ArrayList arrayList, KycCountry kycCountry, HyperKycData hyperKycData, HyperKycConfig hyperKycConfig, Map map, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = workflowState.previousFlowPos;
        }
        if ((i2 & 2) != 0) {
            str = workflowState.journeyId;
        }
        String str2 = str;
        if ((i2 & 4) != 0) {
            arrayList = workflowState.workflowUIStateList;
        }
        ArrayList arrayList2 = arrayList;
        if ((i2 & 8) != 0) {
            kycCountry = workflowState.selectedCountry;
        }
        KycCountry kycCountry2 = kycCountry;
        if ((i2 & 16) != 0) {
            hyperKycData = workflowState.hyperKycData;
        }
        HyperKycData hyperKycData2 = hyperKycData;
        if ((i2 & 32) != 0) {
            hyperKycConfig = workflowState.hyperKycConfig;
        }
        HyperKycConfig hyperKycConfig2 = hyperKycConfig;
        if ((i2 & 64) != 0) {
            map = workflowState.resultDetailsMap;
        }
        return workflowState.copy(i, str2, arrayList2, kycCountry2, hyperKycData2, hyperKycConfig2, map);
    }

    /* renamed from: component1, reason: from getter */
    public final int getPreviousFlowPos() {
        return this.previousFlowPos;
    }

    /* renamed from: component2, reason: from getter */
    public final String getJourneyId() {
        return this.journeyId;
    }

    public final ArrayList<WorkflowUIState> component3() {
        return this.workflowUIStateList;
    }

    /* renamed from: component4, reason: from getter */
    public final KycCountry getSelectedCountry() {
        return this.selectedCountry;
    }

    /* renamed from: component5, reason: from getter */
    public final HyperKycData getHyperKycData() {
        return this.hyperKycData;
    }

    /* renamed from: component6, reason: from getter */
    public final HyperKycConfig getHyperKycConfig() {
        return this.hyperKycConfig;
    }

    public final Map<String, String> component7() {
        return this.resultDetailsMap;
    }

    public final WorkflowState copy(int previousFlowPos, String journeyId, ArrayList<WorkflowUIState> workflowUIStateList, KycCountry selectedCountry, HyperKycData hyperKycData, HyperKycConfig hyperKycConfig, Map<String, String> resultDetailsMap) {
        Intrinsics.checkNotNullParameter(journeyId, "journeyId");
        Intrinsics.checkNotNullParameter(workflowUIStateList, "workflowUIStateList");
        Intrinsics.checkNotNullParameter(selectedCountry, "selectedCountry");
        Intrinsics.checkNotNullParameter(hyperKycData, "hyperKycData");
        Intrinsics.checkNotNullParameter(hyperKycConfig, "hyperKycConfig");
        Intrinsics.checkNotNullParameter(resultDetailsMap, "resultDetailsMap");
        return new WorkflowState(previousFlowPos, journeyId, workflowUIStateList, selectedCountry, hyperKycData, hyperKycConfig, resultDetailsMap);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WorkflowState)) {
            return false;
        }
        WorkflowState workflowState = (WorkflowState) other;
        return this.previousFlowPos == workflowState.previousFlowPos && Intrinsics.areEqual(this.journeyId, workflowState.journeyId) && Intrinsics.areEqual(this.workflowUIStateList, workflowState.workflowUIStateList) && Intrinsics.areEqual(this.selectedCountry, workflowState.selectedCountry) && Intrinsics.areEqual(this.hyperKycData, workflowState.hyperKycData) && Intrinsics.areEqual(this.hyperKycConfig, workflowState.hyperKycConfig) && Intrinsics.areEqual(this.resultDetailsMap, workflowState.resultDetailsMap);
    }

    public int hashCode() {
        return (((((((((((this.previousFlowPos * 31) + this.journeyId.hashCode()) * 31) + this.workflowUIStateList.hashCode()) * 31) + this.selectedCountry.hashCode()) * 31) + this.hyperKycData.hashCode()) * 31) + this.hyperKycConfig.hashCode()) * 31) + this.resultDetailsMap.hashCode();
    }

    public String toString() {
        return "WorkflowState(previousFlowPos=" + this.previousFlowPos + ", journeyId=" + this.journeyId + ", workflowUIStateList=" + this.workflowUIStateList + ", selectedCountry=" + this.selectedCountry + ", hyperKycData=" + this.hyperKycData + ", hyperKycConfig=" + this.hyperKycConfig + ", resultDetailsMap=" + this.resultDetailsMap + ')';
    }

    public WorkflowState(int i, String journeyId, ArrayList<WorkflowUIState> workflowUIStateList, KycCountry selectedCountry, HyperKycData hyperKycData, HyperKycConfig hyperKycConfig, Map<String, String> resultDetailsMap) {
        Intrinsics.checkNotNullParameter(journeyId, "journeyId");
        Intrinsics.checkNotNullParameter(workflowUIStateList, "workflowUIStateList");
        Intrinsics.checkNotNullParameter(selectedCountry, "selectedCountry");
        Intrinsics.checkNotNullParameter(hyperKycData, "hyperKycData");
        Intrinsics.checkNotNullParameter(hyperKycConfig, "hyperKycConfig");
        Intrinsics.checkNotNullParameter(resultDetailsMap, "resultDetailsMap");
        this.previousFlowPos = i;
        this.journeyId = journeyId;
        this.workflowUIStateList = workflowUIStateList;
        this.selectedCountry = selectedCountry;
        this.hyperKycData = hyperKycData;
        this.hyperKycConfig = hyperKycConfig;
        this.resultDetailsMap = resultDetailsMap;
    }

    public final int getPreviousFlowPos() {
        return this.previousFlowPos;
    }

    public final String getJourneyId() {
        return this.journeyId;
    }

    public final ArrayList<WorkflowUIState> getWorkflowUIStateList() {
        return this.workflowUIStateList;
    }

    public final KycCountry getSelectedCountry() {
        return this.selectedCountry;
    }

    public final HyperKycData getHyperKycData() {
        return this.hyperKycData;
    }

    public final HyperKycConfig getHyperKycConfig() {
        return this.hyperKycConfig;
    }

    public final Map<String, String> getResultDetailsMap() {
        return this.resultDetailsMap;
    }
}
