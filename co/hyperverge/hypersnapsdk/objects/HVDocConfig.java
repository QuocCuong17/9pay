package co.hyperverge.hypersnapsdk.objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import androidx.media3.exoplayer.DefaultLoadControl;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.model.HVJSONObject;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HVDocConfig extends HVBaseConfig implements Serializable {
    public static final String KEY = "hvDocConfig";
    private static final String TAG = "co.hyperverge.hypersnapsdk.objects.HVDocConfig";
    String customUIStrings;
    private int descTypeface;
    String docCaptureDescription;
    String docCaptureSubText;
    String docCaptureSubtitle;
    String docCaptureTitle;
    String docReviewDescription;
    String docReviewSubtitle;
    String docReviewTitle;
    private byte[] documentCaptureOverlay;
    private int hintTypeface;
    private String moduleId;
    public String ocrEndpoint;
    public String ocrHeaders;
    public String ocrParams;
    private byte[] readBarcodeOverlay;
    private int reviewScreenConfirmButtonTypeface;
    private int reviewScreenDescTypeface;
    private int reviewScreenRetakeButtonTypeface;
    private int reviewScreenSubtitleTypeface;
    private int reviewScreenTitleTypeface;
    boolean shouldEnableRetries;
    private int subtitleTypeface;
    private int titleTypeface;
    public float padding = 0.0f;
    Document document = Document.CARD;
    boolean shouldShowReviewScreen = false;
    boolean shouldShowInstructionPage = false;
    boolean shouldShowFlashIcon = false;
    boolean shouldSetPadding = true;
    boolean shouldAllowPhoneTilt = true;
    boolean shouldExportPDF = false;
    boolean shouldDoOCR = false;
    boolean shouldReadNIDQR = false;
    boolean shouldReadBarcode = false;
    boolean enableDocumentUpload = false;
    private boolean showModuleBackButton = true;
    List<String> uploadFileTypes = null;
    boolean shouldDoIpToGeo = false;
    DocumentSide documentSide = DocumentSide.FRONT;
    int allowedTiltRoll = 10;
    int allowedTiltPitch = 10;
    private boolean shouldAutoCapture = false;
    private int autoCaptureDuration = 1000;
    private int documentCaptureOverlayDuration = 3000;
    private int readBarcodeTimeout = DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS;
    private boolean disableBarcodeSkip = false;
    private boolean enableOverlay = true;

    /* loaded from: classes2.dex */
    public enum DocumentSide {
        FRONT,
        BACK
    }

    public void setModuleId(String str) {
        this.moduleId = str;
    }

    public String getModuleId() {
        return this.moduleId;
    }

    public boolean isOverlayEnabled() {
        return this.enableOverlay;
    }

    public void setShouldEnableOverlay(boolean z) {
        this.enableOverlay = z;
    }

    public void setDocumentCaptureOverlay(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        bitmap.recycle();
        this.documentCaptureOverlay = byteArray;
    }

    public Bitmap getDocumentCaptureOverlay() {
        byte[] bArr = this.documentCaptureOverlay;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public void setDocumentCaptureOverlayDuration(int i) {
        this.documentCaptureOverlayDuration = i;
    }

    public int getDocumentCaptureOverlayDuration() {
        return this.documentCaptureOverlayDuration;
    }

    public void setReadBarcodeOverlay(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        bitmap.recycle();
        this.readBarcodeOverlay = byteArray;
    }

    public Bitmap getReadBarcodeOverlay() {
        byte[] bArr = this.readBarcodeOverlay;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public void setReadBarcodeTimeout(int i) {
        this.readBarcodeTimeout = i;
    }

    public int getReadBarcodeTimeout() {
        return this.readBarcodeTimeout;
    }

    public void setDisableBarcodeSkip(boolean z) {
        this.disableBarcodeSkip = z;
    }

    public boolean isBarcodeSkipDisabled() {
        return this.disableBarcodeSkip;
    }

    public void setShouldAutoCapture(boolean z) {
        this.shouldAutoCapture = z;
    }

    public boolean isShouldAutoCapture() {
        return this.shouldAutoCapture;
    }

    public void setAutoCaptureDuration(int i) {
        if (i > this.autoCaptureDuration) {
            this.autoCaptureDuration = i;
        }
    }

    public int getAutoCaptureDuration() {
        return this.autoCaptureDuration;
    }

    public DocumentSide getDocumentSide() {
        return this.documentSide;
    }

    public boolean isShouldDoOCR() {
        return this.shouldDoOCR;
    }

    public boolean isShouldDoIpToGeo() {
        return this.shouldDoIpToGeo;
    }

    public boolean isDocumentUploadEnabled() {
        return this.enableDocumentUpload;
    }

    public void setEnableDocumentUpload(boolean z) {
        this.enableDocumentUpload = z;
    }

    public boolean shouldShowModuleBackButton() {
        return this.showModuleBackButton;
    }

    public void setShowModuleBackButton(boolean z) {
        this.showModuleBackButton = z;
    }

    public List<String> getUploadFileTypes() {
        return this.uploadFileTypes;
    }

    public void setUploadFileTypes(List<String> list) {
        this.uploadFileTypes = list;
    }

    public boolean isShouldReadNIDQR() {
        return this.shouldReadNIDQR;
    }

    public void setShouldReadNIDQR(boolean z) {
        this.shouldReadNIDQR = z;
    }

    @Deprecated
    public boolean isShouldReadQR() {
        return this.shouldReadNIDQR;
    }

    @Deprecated
    public void setShouldReadQR(boolean z) {
        this.shouldReadNIDQR = this.shouldReadNIDQR;
    }

    public boolean isShouldReadBarcode() {
        return this.shouldReadBarcode;
    }

    public void setShouldReadBarcode(boolean z) {
        this.shouldReadBarcode = z;
    }

    public boolean isShouldExportPDF() {
        return this.shouldExportPDF;
    }

    public void setShouldExportPDF(boolean z) {
        this.shouldExportPDF = z;
    }

    public HVJSONObject getCustomUIStrings() {
        HVJSONObject hVJSONObject = new HVJSONObject();
        if (this.customUIStrings == null) {
            return hVJSONObject;
        }
        try {
            return new HVJSONObject(this.customUIStrings);
        } catch (JSONException e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return hVJSONObject;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return hVJSONObject;
        }
    }

    public void setCustomUIStrings(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.customUIStrings = jSONObject.toString();
        }
    }

    public JSONObject getOcrParams() {
        try {
            if (this.ocrParams != null) {
                return new JSONObject(this.ocrParams);
            }
            return new JSONObject();
        } catch (Exception e) {
            Log.e(TAG, "getOcrParams: " + Utils.getErrorMessage(e));
            return new JSONObject();
        }
    }

    public String getSuffixForDocument() {
        return this.documentSide == DocumentSide.FRONT ? "front" : "back";
    }

    public JSONObject getOcrHeaders() {
        try {
            if (this.ocrHeaders != null) {
                return new JSONObject(this.ocrHeaders);
            }
            return new JSONObject();
        } catch (Exception unused) {
            return new JSONObject();
        }
    }

    public void setOCRDetails(String str, DocumentSide documentSide, String str2, String str3) {
        this.ocrEndpoint = str;
        this.ocrParams = str2;
        this.ocrHeaders = str3;
        this.documentSide = documentSide;
        this.shouldEnableRetries = true;
        this.shouldDoOCR = true;
    }

    public boolean isShouldEnableRetries() {
        return this.shouldEnableRetries;
    }

    public void setShouldEnableRetries(boolean z) {
        this.shouldEnableRetries = z;
    }

    public boolean isShouldSetPadding() {
        return this.shouldSetPadding;
    }

    public void setShouldAddPadding(boolean z) {
        this.shouldSetPadding = z;
        if (z) {
            setPadding(0.2f);
        }
    }

    public void setPadding(float f) {
        this.shouldSetPadding = true;
        this.padding = f;
    }

    public boolean isShouldShowFlashIcon() {
        return this.shouldShowFlashIcon;
    }

    public void setShouldShowFlashIcon(boolean z) {
        this.shouldShowFlashIcon = z;
    }

    public String getDocCaptureSubText() {
        return this.docCaptureSubText;
    }

    public void setDocCaptureSubText(String str) {
        this.docCaptureSubText = str;
    }

    public String getDocCaptureDescription() {
        return this.docCaptureDescription;
    }

    public void setDocCaptureDescription(String str) {
        this.docCaptureDescription = str;
    }

    public boolean isShouldShowInstructionPage() {
        return this.shouldShowInstructionPage;
    }

    public void setShouldShowInstructionPage(boolean z) {
        this.shouldShowInstructionPage = z;
    }

    public boolean shouldShowReviewScreen() {
        return this.shouldShowReviewScreen;
    }

    public void setShouldShowReviewScreen(boolean z) {
        this.shouldShowReviewScreen = z;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocumentType(Document document) {
        this.document = document;
    }

    public String getCapturePageTitleText() {
        return this.docCaptureTitle;
    }

    public void setDocCaptureTitle(String str) {
        this.docCaptureTitle = str;
    }

    @Deprecated
    public String getCapturePageSubtitleText() {
        return this.docCaptureSubtitle;
    }

    @Deprecated
    public void setDocCaptureSubtitle(String str) {
        this.docCaptureSubtitle = str;
    }

    public String getDocReviewTitle() {
        return this.docReviewTitle;
    }

    public void setDocReviewTitle(String str) {
        this.docReviewTitle = str;
    }

    @Deprecated
    public String getDocReviewSubtitle() {
        return this.docReviewSubtitle;
    }

    @Deprecated
    public void setDocReviewSubtitle(String str) {
        this.docReviewSubtitle = str;
    }

    public String getDocReviewDescription() {
        return this.docReviewDescription;
    }

    public void setDocReviewDescription(String str) {
        this.docReviewDescription = str;
    }

    public boolean isShouldAllowPhoneTilt() {
        return this.shouldAllowPhoneTilt;
    }

    public void setShouldAllowPhoneTilt(boolean z) {
        this.shouldAllowPhoneTilt = z;
    }

    public int getAllowedTiltRoll() {
        return this.allowedTiltRoll;
    }

    public int getAllowedTiltPitch() {
        return this.allowedTiltPitch;
    }

    public void setPhoneTiltConstraints(int i, int i2) {
        this.allowedTiltRoll = i;
        this.allowedTiltPitch = i2;
    }

    public void setTitleTypeFace(int i) {
        this.titleTypeface = i;
    }

    public int getTitleTypeface() {
        return this.titleTypeface;
    }

    @Deprecated
    public void setSubtitleTypeFace(int i) {
        this.subtitleTypeface = i;
    }

    @Deprecated
    public int getSubtitleTypeface() {
        return this.subtitleTypeface;
    }

    public void setDescTypeFace(int i) {
        this.descTypeface = i;
    }

    public int getDescTypeface() {
        return this.descTypeface;
    }

    public int getHintTypeface() {
        return this.hintTypeface;
    }

    public void setHintTypeface(int i) {
        this.hintTypeface = i;
    }

    public int getReviewScreenTitleTypeface() {
        return this.reviewScreenTitleTypeface;
    }

    public void setReviewScreenTitleTypeface(int i) {
        this.reviewScreenTitleTypeface = i;
    }

    @Deprecated
    public int getReviewScreenSubtitleTypeface() {
        return this.reviewScreenSubtitleTypeface;
    }

    @Deprecated
    public void setReviewScreenSubtitleTypeface(int i) {
        this.reviewScreenSubtitleTypeface = i;
    }

    public int getReviewScreenDescTypeface() {
        return this.reviewScreenDescTypeface;
    }

    public void setReviewScreenDescTypeface(int i) {
        this.reviewScreenDescTypeface = i;
    }

    public int getReviewScreenRetakeButtonTypeface() {
        return this.reviewScreenRetakeButtonTypeface;
    }

    public void setReviewScreenRetakeButtonTypeface(int i) {
        this.reviewScreenRetakeButtonTypeface = i;
    }

    public int getReviewScreenConfirmButtonTypeface() {
        return this.reviewScreenConfirmButtonTypeface;
    }

    public void setReviewScreenConfirmButtonTypeface(int i) {
        this.reviewScreenConfirmButtonTypeface = i;
    }

    /* loaded from: classes2.dex */
    public enum Document {
        CARD(0.625f) { // from class: co.hyperverge.hypersnapsdk.objects.HVDocConfig.Document.1
            @Override // co.hyperverge.hypersnapsdk.objects.HVDocConfig.Document
            public void setAspectRatio(float f) {
            }
        },
        A4(1.5f) { // from class: co.hyperverge.hypersnapsdk.objects.HVDocConfig.Document.2
            @Override // co.hyperverge.hypersnapsdk.objects.HVDocConfig.Document
            public void setAspectRatio(float f) {
            }
        },
        PASSPORT(0.6666667f) { // from class: co.hyperverge.hypersnapsdk.objects.HVDocConfig.Document.3
            @Override // co.hyperverge.hypersnapsdk.objects.HVDocConfig.Document
            public void setAspectRatio(float f) {
            }
        },
        OTHER(0.5f) { // from class: co.hyperverge.hypersnapsdk.objects.HVDocConfig.Document.4
            @Override // co.hyperverge.hypersnapsdk.objects.HVDocConfig.Document
            public void setAspectRatio(float f) {
                this.aspectRatio = f;
            }
        };

        float aspectRatio;

        public abstract void setAspectRatio(float f);

        Document(float f) {
            this.aspectRatio = f;
        }

        public float getAspectRatio() {
            return this.aspectRatio;
        }
    }
}
