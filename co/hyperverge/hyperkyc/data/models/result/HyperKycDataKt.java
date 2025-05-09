package co.hyperverge.hyperkyc.data.models.result;

import kotlin.Metadata;

/* compiled from: HyperKycData.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0002H\u0000¨\u0006\u0003"}, d2 = {"isNotEmpty", "", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HyperKycDataKt {
    public static final boolean isNotEmpty(HyperKycData hyperKycData) {
        return hyperKycData != null && ((hyperKycData.docResultList().isEmpty() ^ true) || hyperKycData.faceResult() != null || (hyperKycData.apiResultList().isEmpty() ^ true) || (hyperKycData.formResultList().isEmpty() ^ true) || (hyperKycData.webviewResultList().isEmpty() ^ true) || (hyperKycData.barcodeResultList().isEmpty() ^ true) || (hyperKycData.sessionResultList().isEmpty() ^ true) || (hyperKycData.videoStatementResultList().isEmpty() ^ true) || (hyperKycData.videoStatementV2ResultList().isEmpty() ^ true));
    }
}
