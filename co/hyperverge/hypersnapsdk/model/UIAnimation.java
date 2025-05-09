package co.hyperverge.hypersnapsdk.model;

import android.content.Context;
import co.hyperverge.hypersnapsdk.utils.StringUtils;
import com.airbnb.lottie.LottieCompositionFactory;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class UIAnimation implements Serializable {
    private String documentFailureLottie;
    private String documentInstructionBackSideLottie;
    private String documentInstructionFrontSideLottie;

    @Deprecated
    private String documentInstructionLottie;
    private String documentProcessingLottie;
    private String documentSuccessLottie;
    private String endStateFailure;
    private String endStateProcessing;
    private String endStateSuccess;
    private String faceFailureLottie;
    private String faceInstructionLottie;
    private String faceProcessingLottie;
    private String faceSuccessLottie;
    private String loaderLottie;
    private String nfcInstructionAndroid;
    private String nfcLottie;
    private String qrInstruction;
    private String uploadFailure;

    protected boolean canEqual(Object obj) {
        return obj instanceof UIAnimation;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UIAnimation)) {
            return false;
        }
        UIAnimation uIAnimation = (UIAnimation) obj;
        if (!uIAnimation.canEqual(this)) {
            return false;
        }
        String documentInstructionLottie = getDocumentInstructionLottie();
        String documentInstructionLottie2 = uIAnimation.getDocumentInstructionLottie();
        if (documentInstructionLottie != null ? !documentInstructionLottie.equals(documentInstructionLottie2) : documentInstructionLottie2 != null) {
            return false;
        }
        String documentInstructionFrontSideLottie = getDocumentInstructionFrontSideLottie();
        String documentInstructionFrontSideLottie2 = uIAnimation.getDocumentInstructionFrontSideLottie();
        if (documentInstructionFrontSideLottie != null ? !documentInstructionFrontSideLottie.equals(documentInstructionFrontSideLottie2) : documentInstructionFrontSideLottie2 != null) {
            return false;
        }
        String documentInstructionBackSideLottie = getDocumentInstructionBackSideLottie();
        String documentInstructionBackSideLottie2 = uIAnimation.getDocumentInstructionBackSideLottie();
        if (documentInstructionBackSideLottie != null ? !documentInstructionBackSideLottie.equals(documentInstructionBackSideLottie2) : documentInstructionBackSideLottie2 != null) {
            return false;
        }
        String documentProcessingLottie = getDocumentProcessingLottie();
        String documentProcessingLottie2 = uIAnimation.getDocumentProcessingLottie();
        if (documentProcessingLottie != null ? !documentProcessingLottie.equals(documentProcessingLottie2) : documentProcessingLottie2 != null) {
            return false;
        }
        String documentSuccessLottie = getDocumentSuccessLottie();
        String documentSuccessLottie2 = uIAnimation.getDocumentSuccessLottie();
        if (documentSuccessLottie != null ? !documentSuccessLottie.equals(documentSuccessLottie2) : documentSuccessLottie2 != null) {
            return false;
        }
        String documentFailureLottie = getDocumentFailureLottie();
        String documentFailureLottie2 = uIAnimation.getDocumentFailureLottie();
        if (documentFailureLottie != null ? !documentFailureLottie.equals(documentFailureLottie2) : documentFailureLottie2 != null) {
            return false;
        }
        String faceInstructionLottie = getFaceInstructionLottie();
        String faceInstructionLottie2 = uIAnimation.getFaceInstructionLottie();
        if (faceInstructionLottie != null ? !faceInstructionLottie.equals(faceInstructionLottie2) : faceInstructionLottie2 != null) {
            return false;
        }
        String faceProcessingLottie = getFaceProcessingLottie();
        String faceProcessingLottie2 = uIAnimation.getFaceProcessingLottie();
        if (faceProcessingLottie != null ? !faceProcessingLottie.equals(faceProcessingLottie2) : faceProcessingLottie2 != null) {
            return false;
        }
        String faceSuccessLottie = getFaceSuccessLottie();
        String faceSuccessLottie2 = uIAnimation.getFaceSuccessLottie();
        if (faceSuccessLottie != null ? !faceSuccessLottie.equals(faceSuccessLottie2) : faceSuccessLottie2 != null) {
            return false;
        }
        String faceFailureLottie = getFaceFailureLottie();
        String faceFailureLottie2 = uIAnimation.getFaceFailureLottie();
        if (faceFailureLottie != null ? !faceFailureLottie.equals(faceFailureLottie2) : faceFailureLottie2 != null) {
            return false;
        }
        String endStateProcessing = getEndStateProcessing();
        String endStateProcessing2 = uIAnimation.getEndStateProcessing();
        if (endStateProcessing != null ? !endStateProcessing.equals(endStateProcessing2) : endStateProcessing2 != null) {
            return false;
        }
        String endStateSuccess = getEndStateSuccess();
        String endStateSuccess2 = uIAnimation.getEndStateSuccess();
        if (endStateSuccess != null ? !endStateSuccess.equals(endStateSuccess2) : endStateSuccess2 != null) {
            return false;
        }
        String endStateFailure = getEndStateFailure();
        String endStateFailure2 = uIAnimation.getEndStateFailure();
        if (endStateFailure != null ? !endStateFailure.equals(endStateFailure2) : endStateFailure2 != null) {
            return false;
        }
        String uploadFailure = getUploadFailure();
        String uploadFailure2 = uIAnimation.getUploadFailure();
        if (uploadFailure != null ? !uploadFailure.equals(uploadFailure2) : uploadFailure2 != null) {
            return false;
        }
        String qrInstruction = getQrInstruction();
        String qrInstruction2 = uIAnimation.getQrInstruction();
        if (qrInstruction != null ? !qrInstruction.equals(qrInstruction2) : qrInstruction2 != null) {
            return false;
        }
        String loaderLottie = getLoaderLottie();
        String loaderLottie2 = uIAnimation.getLoaderLottie();
        if (loaderLottie != null ? !loaderLottie.equals(loaderLottie2) : loaderLottie2 != null) {
            return false;
        }
        String nfcLottie = getNfcLottie();
        String nfcLottie2 = uIAnimation.getNfcLottie();
        if (nfcLottie != null ? !nfcLottie.equals(nfcLottie2) : nfcLottie2 != null) {
            return false;
        }
        String nfcInstructionAndroid = getNfcInstructionAndroid();
        String nfcInstructionAndroid2 = uIAnimation.getNfcInstructionAndroid();
        return nfcInstructionAndroid != null ? nfcInstructionAndroid.equals(nfcInstructionAndroid2) : nfcInstructionAndroid2 == null;
    }

    public int hashCode() {
        String documentInstructionLottie = getDocumentInstructionLottie();
        int hashCode = documentInstructionLottie == null ? 43 : documentInstructionLottie.hashCode();
        String documentInstructionFrontSideLottie = getDocumentInstructionFrontSideLottie();
        int hashCode2 = ((hashCode + 59) * 59) + (documentInstructionFrontSideLottie == null ? 43 : documentInstructionFrontSideLottie.hashCode());
        String documentInstructionBackSideLottie = getDocumentInstructionBackSideLottie();
        int hashCode3 = (hashCode2 * 59) + (documentInstructionBackSideLottie == null ? 43 : documentInstructionBackSideLottie.hashCode());
        String documentProcessingLottie = getDocumentProcessingLottie();
        int hashCode4 = (hashCode3 * 59) + (documentProcessingLottie == null ? 43 : documentProcessingLottie.hashCode());
        String documentSuccessLottie = getDocumentSuccessLottie();
        int hashCode5 = (hashCode4 * 59) + (documentSuccessLottie == null ? 43 : documentSuccessLottie.hashCode());
        String documentFailureLottie = getDocumentFailureLottie();
        int hashCode6 = (hashCode5 * 59) + (documentFailureLottie == null ? 43 : documentFailureLottie.hashCode());
        String faceInstructionLottie = getFaceInstructionLottie();
        int hashCode7 = (hashCode6 * 59) + (faceInstructionLottie == null ? 43 : faceInstructionLottie.hashCode());
        String faceProcessingLottie = getFaceProcessingLottie();
        int hashCode8 = (hashCode7 * 59) + (faceProcessingLottie == null ? 43 : faceProcessingLottie.hashCode());
        String faceSuccessLottie = getFaceSuccessLottie();
        int hashCode9 = (hashCode8 * 59) + (faceSuccessLottie == null ? 43 : faceSuccessLottie.hashCode());
        String faceFailureLottie = getFaceFailureLottie();
        int hashCode10 = (hashCode9 * 59) + (faceFailureLottie == null ? 43 : faceFailureLottie.hashCode());
        String endStateProcessing = getEndStateProcessing();
        int hashCode11 = (hashCode10 * 59) + (endStateProcessing == null ? 43 : endStateProcessing.hashCode());
        String endStateSuccess = getEndStateSuccess();
        int hashCode12 = (hashCode11 * 59) + (endStateSuccess == null ? 43 : endStateSuccess.hashCode());
        String endStateFailure = getEndStateFailure();
        int hashCode13 = (hashCode12 * 59) + (endStateFailure == null ? 43 : endStateFailure.hashCode());
        String uploadFailure = getUploadFailure();
        int hashCode14 = (hashCode13 * 59) + (uploadFailure == null ? 43 : uploadFailure.hashCode());
        String qrInstruction = getQrInstruction();
        int hashCode15 = (hashCode14 * 59) + (qrInstruction == null ? 43 : qrInstruction.hashCode());
        String loaderLottie = getLoaderLottie();
        int hashCode16 = (hashCode15 * 59) + (loaderLottie == null ? 43 : loaderLottie.hashCode());
        String nfcLottie = getNfcLottie();
        int hashCode17 = (hashCode16 * 59) + (nfcLottie == null ? 43 : nfcLottie.hashCode());
        String nfcInstructionAndroid = getNfcInstructionAndroid();
        return (hashCode17 * 59) + (nfcInstructionAndroid != null ? nfcInstructionAndroid.hashCode() : 43);
    }

    public void setDocumentFailureLottie(String str) {
        this.documentFailureLottie = str;
    }

    public void setDocumentInstructionBackSideLottie(String str) {
        this.documentInstructionBackSideLottie = str;
    }

    public void setDocumentInstructionFrontSideLottie(String str) {
        this.documentInstructionFrontSideLottie = str;
    }

    @Deprecated
    public void setDocumentInstructionLottie(String str) {
        this.documentInstructionLottie = str;
    }

    public void setDocumentProcessingLottie(String str) {
        this.documentProcessingLottie = str;
    }

    public void setDocumentSuccessLottie(String str) {
        this.documentSuccessLottie = str;
    }

    public void setEndStateFailure(String str) {
        this.endStateFailure = str;
    }

    public void setEndStateProcessing(String str) {
        this.endStateProcessing = str;
    }

    public void setEndStateSuccess(String str) {
        this.endStateSuccess = str;
    }

    public void setFaceFailureLottie(String str) {
        this.faceFailureLottie = str;
    }

    public void setFaceInstructionLottie(String str) {
        this.faceInstructionLottie = str;
    }

    public void setFaceProcessingLottie(String str) {
        this.faceProcessingLottie = str;
    }

    public void setFaceSuccessLottie(String str) {
        this.faceSuccessLottie = str;
    }

    public void setLoaderLottie(String str) {
        this.loaderLottie = str;
    }

    public void setNfcInstructionAndroid(String str) {
        this.nfcInstructionAndroid = str;
    }

    public void setNfcLottie(String str) {
        this.nfcLottie = str;
    }

    public void setQrInstruction(String str) {
        this.qrInstruction = str;
    }

    public void setUploadFailure(String str) {
        this.uploadFailure = str;
    }

    public String toString() {
        return "UIAnimation(documentInstructionLottie=" + getDocumentInstructionLottie() + ", documentInstructionFrontSideLottie=" + getDocumentInstructionFrontSideLottie() + ", documentInstructionBackSideLottie=" + getDocumentInstructionBackSideLottie() + ", documentProcessingLottie=" + getDocumentProcessingLottie() + ", documentSuccessLottie=" + getDocumentSuccessLottie() + ", documentFailureLottie=" + getDocumentFailureLottie() + ", faceInstructionLottie=" + getFaceInstructionLottie() + ", faceProcessingLottie=" + getFaceProcessingLottie() + ", faceSuccessLottie=" + getFaceSuccessLottie() + ", faceFailureLottie=" + getFaceFailureLottie() + ", endStateProcessing=" + getEndStateProcessing() + ", endStateSuccess=" + getEndStateSuccess() + ", endStateFailure=" + getEndStateFailure() + ", uploadFailure=" + getUploadFailure() + ", qrInstruction=" + getQrInstruction() + ", loaderLottie=" + getLoaderLottie() + ", nfcLottie=" + getNfcLottie() + ", nfcInstructionAndroid=" + getNfcInstructionAndroid() + ")";
    }

    @Deprecated
    public String getDocumentInstructionLottie() {
        return this.documentInstructionLottie;
    }

    public String getDocumentInstructionFrontSideLottie() {
        return this.documentInstructionFrontSideLottie;
    }

    public String getDocumentInstructionBackSideLottie() {
        return this.documentInstructionBackSideLottie;
    }

    public String getDocumentProcessingLottie() {
        return this.documentProcessingLottie;
    }

    public String getDocumentSuccessLottie() {
        return this.documentSuccessLottie;
    }

    public String getDocumentFailureLottie() {
        return this.documentFailureLottie;
    }

    public String getFaceInstructionLottie() {
        return this.faceInstructionLottie;
    }

    public String getFaceProcessingLottie() {
        return this.faceProcessingLottie;
    }

    public String getFaceSuccessLottie() {
        return this.faceSuccessLottie;
    }

    public String getFaceFailureLottie() {
        return this.faceFailureLottie;
    }

    public String getEndStateProcessing() {
        return this.endStateProcessing;
    }

    public String getEndStateSuccess() {
        return this.endStateSuccess;
    }

    public String getEndStateFailure() {
        return this.endStateFailure;
    }

    public String getUploadFailure() {
        return this.uploadFailure;
    }

    public String getQrInstruction() {
        return this.qrInstruction;
    }

    public String getLoaderLottie() {
        return this.loaderLottie;
    }

    public String getNfcLottie() {
        return this.nfcLottie;
    }

    public String getNfcInstructionAndroid() {
        return this.nfcInstructionAndroid;
    }

    public void preloadAnimation(Context context) {
        if (StringUtils.isURL(this.documentInstructionLottie)) {
            LottieCompositionFactory.fromUrl(context, this.documentInstructionLottie);
        }
        if (StringUtils.isURL(this.documentInstructionFrontSideLottie)) {
            LottieCompositionFactory.fromUrl(context, this.documentInstructionFrontSideLottie);
        }
        if (StringUtils.isURL(this.documentInstructionBackSideLottie)) {
            LottieCompositionFactory.fromUrl(context, this.documentInstructionBackSideLottie);
        }
        if (StringUtils.isURL(this.documentProcessingLottie)) {
            LottieCompositionFactory.fromUrl(context, this.documentProcessingLottie);
        }
        if (StringUtils.isURL(this.documentSuccessLottie)) {
            LottieCompositionFactory.fromUrl(context, this.documentSuccessLottie);
        }
        if (StringUtils.isURL(this.documentFailureLottie)) {
            LottieCompositionFactory.fromUrl(context, this.documentFailureLottie);
        }
        if (StringUtils.isURL(this.faceInstructionLottie)) {
            LottieCompositionFactory.fromUrl(context, this.faceInstructionLottie);
        }
        if (StringUtils.isURL(this.faceProcessingLottie)) {
            LottieCompositionFactory.fromUrl(context, this.faceProcessingLottie);
        }
        if (StringUtils.isURL(this.faceSuccessLottie)) {
            LottieCompositionFactory.fromUrl(context, this.faceSuccessLottie);
        }
        if (StringUtils.isURL(this.faceFailureLottie)) {
            LottieCompositionFactory.fromUrl(context, this.faceFailureLottie);
        }
        if (StringUtils.isURL(this.endStateProcessing)) {
            LottieCompositionFactory.fromUrl(context, this.endStateProcessing);
        }
        if (StringUtils.isURL(this.endStateSuccess)) {
            LottieCompositionFactory.fromUrl(context, this.endStateSuccess);
        }
        if (StringUtils.isURL(this.endStateFailure)) {
            LottieCompositionFactory.fromUrl(context, this.endStateFailure);
        }
        if (StringUtils.isURL(this.uploadFailure)) {
            LottieCompositionFactory.fromUrl(context, this.uploadFailure);
        }
        if (StringUtils.isURL(this.qrInstruction)) {
            LottieCompositionFactory.fromUrl(context, this.qrInstruction);
        }
        if (StringUtils.isURL(this.loaderLottie)) {
            LottieCompositionFactory.fromUrl(context, this.loaderLottie);
        }
        if (StringUtils.isURL(this.nfcLottie)) {
            LottieCompositionFactory.fromUrl(context, this.nfcLottie);
        }
        if (StringUtils.isURL(this.nfcInstructionAndroid)) {
            LottieCompositionFactory.fromUrl(context, this.nfcInstructionAndroid);
        }
    }
}
