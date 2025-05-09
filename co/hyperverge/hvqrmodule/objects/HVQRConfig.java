package co.hyperverge.hvqrmodule.objects;

import android.util.Log;
import androidx.media3.exoplayer.DefaultLoadControl;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.model.HVJSONObject;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
/* loaded from: classes2.dex */
public class HVQRConfig implements Serializable {
    private String customUIStrings;
    private String moduleId;
    private String qrCaptureDescText;
    private String qrCaptureSkipText;
    private String qrCaptureSubText;
    private String qrCaptureTitleText;
    private String qrInstructionTitleText;
    private String qrInstructionsDescText;
    private String qrInstructionsProceedText;
    private HVBarcodeType hvBarcodeType = HVBarcodeType.QR;
    private boolean showInstructions = false;
    private boolean maskAadhaar = true;
    private int skipButtonDelay = DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS;
    private boolean showModuleBackButton = true;

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'QR' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:293)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:266)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* loaded from: classes2.dex */
    public static abstract class HVBarcodeType {
        public static final HVBarcodeType AZTEC;
        public static final HVBarcodeType DATA_MATRIX;
        public static final HVBarcodeType QR;
        float aspectRatio;
        public static final HVBarcodeType PDF417 = new HVBarcodeType("PDF417", 3, 0.6f) { // from class: co.hyperverge.hvqrmodule.objects.HVQRConfig.HVBarcodeType.4
            @Override // co.hyperverge.hvqrmodule.objects.HVQRConfig.HVBarcodeType
            public void setAspectRatio(float f) {
            }
        };
        private static final /* synthetic */ HVBarcodeType[] $VALUES = $values();

        public abstract void setAspectRatio(float f);

        private static /* synthetic */ HVBarcodeType[] $values() {
            return new HVBarcodeType[]{QR, AZTEC, DATA_MATRIX, PDF417};
        }

        public static HVBarcodeType valueOf(String str) {
            return (HVBarcodeType) Enum.valueOf(HVBarcodeType.class, str);
        }

        public static HVBarcodeType[] values() {
            return (HVBarcodeType[]) $VALUES.clone();
        }

        static {
            float f = 1.0f;
            QR = new HVBarcodeType("QR", 0, f) { // from class: co.hyperverge.hvqrmodule.objects.HVQRConfig.HVBarcodeType.1
                @Override // co.hyperverge.hvqrmodule.objects.HVQRConfig.HVBarcodeType
                public void setAspectRatio(float f2) {
                }
            };
            AZTEC = new HVBarcodeType("AZTEC", 1, f) { // from class: co.hyperverge.hvqrmodule.objects.HVQRConfig.HVBarcodeType.2
                @Override // co.hyperverge.hvqrmodule.objects.HVQRConfig.HVBarcodeType
                public void setAspectRatio(float f2) {
                }
            };
            DATA_MATRIX = new HVBarcodeType("DATA_MATRIX", 2, f) { // from class: co.hyperverge.hvqrmodule.objects.HVQRConfig.HVBarcodeType.3
                @Override // co.hyperverge.hvqrmodule.objects.HVQRConfig.HVBarcodeType
                public void setAspectRatio(float f2) {
                }
            };
        }

        private HVBarcodeType(String str, int i, float f) {
            this.aspectRatio = f;
        }

        public float getAspectRatio() {
            return this.aspectRatio;
        }
    }

    public void setModuleId(String str) {
        this.moduleId = str;
    }

    public String getModuleId() {
        return this.moduleId;
    }

    public HVJSONObject getCustomUIStrings() {
        HVJSONObject hVJSONObject = new HVJSONObject();
        if (this.customUIStrings == null) {
            return hVJSONObject;
        }
        try {
            return new HVJSONObject(this.customUIStrings);
        } catch (JSONException e) {
            Log.e("TAG", Utils.getErrorMessage(e));
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

    public int getSkipButtonDelay() {
        return this.skipButtonDelay;
    }

    public void setSkipButtonDelay(int i) {
        this.skipButtonDelay = i;
    }

    public void setHVBarcodeType(HVBarcodeType hVBarcodeType) {
        this.hvBarcodeType = hVBarcodeType;
    }

    public void setShowInstructions(boolean z) {
        this.showInstructions = z;
    }

    public boolean shouldShowInstructions() {
        return this.showInstructions;
    }

    public void setMaskAadhaar(boolean z) {
        this.maskAadhaar = z;
    }

    public boolean shouldMaskAadhaar() {
        return this.maskAadhaar;
    }

    public void setQrCaptureSubText(String str) {
        this.qrCaptureSubText = str;
    }

    public void setQrCaptureTitleText(String str) {
        this.qrCaptureTitleText = str;
    }

    public void setQrCaptureDescText(String str) {
        this.qrCaptureDescText = str;
    }

    public void setQrCaptureSkipText(String str) {
        this.qrCaptureSkipText = str;
    }

    public void setQrInstructionsProceedText(String str) {
        this.qrInstructionsProceedText = str;
    }

    public void setQrInstructionsDescText(String str) {
        this.qrInstructionsDescText = str;
    }

    public void setQrInstructionTitleText(String str) {
        this.qrInstructionTitleText = str;
    }

    public HVBarcodeType getHVBarcodeType() {
        return this.hvBarcodeType;
    }

    public String getQrCaptureSubText() {
        return this.qrCaptureSubText;
    }

    public String getQrCaptureTitleText() {
        return this.qrCaptureTitleText;
    }

    public String getQrCaptureDescText() {
        return this.qrCaptureDescText;
    }

    public String getQrInstructionsProceedText() {
        return this.qrInstructionsProceedText;
    }

    public String getQrInstructionsDescText() {
        return this.qrInstructionsDescText;
    }

    public String getQrInstructionTitleText() {
        return this.qrInstructionTitleText;
    }

    public String getQrCaptureSkipText() {
        return this.qrCaptureSkipText;
    }

    public boolean shouldShowModuleBackButton() {
        return this.showModuleBackButton;
    }

    public void setShowModuleBackButton(boolean z) {
        this.showModuleBackButton = z;
    }
}
