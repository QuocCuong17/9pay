package co.hyperverge.hyperkyc.ui.nfc;

import co.hyperverge.hyperkyc.R;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NFCUIFlowModel.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0081\b\u0018\u0000 \u001f2\u00020\u0001:\u0003\u001f !B'\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J+\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\""}, d2 = {"Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel;", "", "uiState", "Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIState;", "status", "Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIStatus;", "counter", "", "(Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIState;Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIStatus;I)V", "getCounter", "()I", "setCounter", "(I)V", "getStatus", "()Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIStatus;", "setStatus", "(Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIStatus;)V", "getUiState", "()Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIState;", "setUiState", "(Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIState;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "NFCTextConfigs", "NFCUIState", "NFCUIStatus", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class NFCUIFlowModel {
    public static final String NFC_CONNECT_CHIP = "nfcConnectChip";
    public static final String NFC_TOUCH_ID = "nfcTouchId";
    public static final String NFC_TURN_ON = "nfcTurnOn";
    public static final String TEXT_CONFIG_NFC_CONNECT_CHIP = "nfc_connectChip";
    public static final String TEXT_CONFIG_NFC_TOUCH_ID = "nfc_touchId";
    public static final String TEXT_CONFIG_NFC_TURN_ON = "nfc_turnOn";
    private int counter;
    private NFCUIStatus status;
    private NFCUIState uiState;

    /* compiled from: NFCUIFlowModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIStatus;", "", "(Ljava/lang/String;I)V", "Success", "Failure", "Processing", "Disabled", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public enum NFCUIStatus {
        Success,
        Failure,
        Processing,
        Disabled
    }

    public NFCUIFlowModel() {
        this(null, null, 0, 7, null);
    }

    public static /* synthetic */ NFCUIFlowModel copy$default(NFCUIFlowModel nFCUIFlowModel, NFCUIState nFCUIState, NFCUIStatus nFCUIStatus, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            nFCUIState = nFCUIFlowModel.uiState;
        }
        if ((i2 & 2) != 0) {
            nFCUIStatus = nFCUIFlowModel.status;
        }
        if ((i2 & 4) != 0) {
            i = nFCUIFlowModel.counter;
        }
        return nFCUIFlowModel.copy(nFCUIState, nFCUIStatus, i);
    }

    /* renamed from: component1, reason: from getter */
    public final NFCUIState getUiState() {
        return this.uiState;
    }

    /* renamed from: component2, reason: from getter */
    public final NFCUIStatus getStatus() {
        return this.status;
    }

    /* renamed from: component3, reason: from getter */
    public final int getCounter() {
        return this.counter;
    }

    public final NFCUIFlowModel copy(NFCUIState uiState, NFCUIStatus status, int counter) {
        return new NFCUIFlowModel(uiState, status, counter);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NFCUIFlowModel)) {
            return false;
        }
        NFCUIFlowModel nFCUIFlowModel = (NFCUIFlowModel) other;
        return Intrinsics.areEqual(this.uiState, nFCUIFlowModel.uiState) && this.status == nFCUIFlowModel.status && this.counter == nFCUIFlowModel.counter;
    }

    public int hashCode() {
        NFCUIState nFCUIState = this.uiState;
        int hashCode = (nFCUIState == null ? 0 : nFCUIState.hashCode()) * 31;
        NFCUIStatus nFCUIStatus = this.status;
        return ((hashCode + (nFCUIStatus != null ? nFCUIStatus.hashCode() : 0)) * 31) + this.counter;
    }

    public String toString() {
        return "NFCUIFlowModel(uiState=" + this.uiState + ", status=" + this.status + ", counter=" + this.counter + ')';
    }

    public NFCUIFlowModel(NFCUIState nFCUIState, NFCUIStatus nFCUIStatus, int i) {
        this.uiState = nFCUIState;
        this.status = nFCUIStatus;
        this.counter = i;
    }

    public /* synthetic */ NFCUIFlowModel(NFCUIState nFCUIState, NFCUIStatus nFCUIStatus, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : nFCUIState, (i2 & 2) != 0 ? null : nFCUIStatus, (i2 & 4) != 0 ? 0 : i);
    }

    public final NFCUIState getUiState() {
        return this.uiState;
    }

    public final void setUiState(NFCUIState nFCUIState) {
        this.uiState = nFCUIState;
    }

    public final NFCUIStatus getStatus() {
        return this.status;
    }

    public final void setStatus(NFCUIStatus nFCUIStatus) {
        this.status = nFCUIStatus;
    }

    public final int getCounter() {
        return this.counter;
    }

    public final void setCounter(int i) {
        this.counter = i;
    }

    /* compiled from: NFCUIFlowModel.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0017\u0018\u0019BA\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010\u0082\u0001\u0003\u001a\u001b\u001c¨\u0006\u001d"}, d2 = {"Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIState;", "", "disabledMessageId", "", "successMessageId", "failureMessageId", "processingMessageId", "textConfigKey", "", "nfcStepId", "rightIconDrawable", "(IIIILjava/lang/String;Ljava/lang/String;I)V", "getDisabledMessageId", "()I", "getFailureMessageId", "getNfcStepId", "()Ljava/lang/String;", "getProcessingMessageId", "getRightIconDrawable", "setRightIconDrawable", "(I)V", "getSuccessMessageId", "getTextConfigKey", "ConnectChip", "TapChipCard", "TurnOnNFC", "Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIState$ConnectChip;", "Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIState$TapChipCard;", "Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIState$TurnOnNFC;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static abstract class NFCUIState {
        private final int disabledMessageId;
        private final int failureMessageId;
        private final String nfcStepId;
        private final int processingMessageId;
        private int rightIconDrawable;
        private final int successMessageId;
        private final String textConfigKey;

        public /* synthetic */ NFCUIState(int i, int i2, int i3, int i4, String str, String str2, int i5, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, i3, i4, str, str2, i5);
        }

        private NFCUIState(int i, int i2, int i3, int i4, String str, String str2, int i5) {
            this.disabledMessageId = i;
            this.successMessageId = i2;
            this.failureMessageId = i3;
            this.processingMessageId = i4;
            this.textConfigKey = str;
            this.nfcStepId = str2;
            this.rightIconDrawable = i5;
        }

        public /* synthetic */ NFCUIState(int i, int i2, int i3, int i4, String str, String str2, int i5, int i6, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, i3, i4, str, str2, (i6 & 64) != 0 ? -1 : i5, null);
        }

        public final int getDisabledMessageId() {
            return this.disabledMessageId;
        }

        public final int getSuccessMessageId() {
            return this.successMessageId;
        }

        public final int getFailureMessageId() {
            return this.failureMessageId;
        }

        public final int getProcessingMessageId() {
            return this.processingMessageId;
        }

        public final String getTextConfigKey() {
            return this.textConfigKey;
        }

        public final String getNfcStepId() {
            return this.nfcStepId;
        }

        public final int getRightIconDrawable() {
            return this.rightIconDrawable;
        }

        public final void setRightIconDrawable(int i) {
            this.rightIconDrawable = i;
        }

        /* compiled from: NFCUIFlowModel.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIState$TurnOnNFC;", "Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIState;", "()V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class TurnOnNFC extends NFCUIState {
            public static final TurnOnNFC INSTANCE = new TurnOnNFC();

            private TurnOnNFC() {
                super(R.string.hk_turn_on, R.string.hk_turn_on_success, R.string.hk_turn_on_fail, R.string.hk_turn_on, NFCUIFlowModel.TEXT_CONFIG_NFC_TURN_ON, NFCUIFlowModel.NFC_TURN_ON, 0, 64, null);
            }
        }

        /* compiled from: NFCUIFlowModel.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIState$TapChipCard;", "Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIState;", "()V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class TapChipCard extends NFCUIState {
            public static final TapChipCard INSTANCE = new TapChipCard();

            private TapChipCard() {
                super(R.string.hk_touchId, R.string.hk_touchId_success, R.string.hk_touchId_fail, R.string.hk_touchId, NFCUIFlowModel.TEXT_CONFIG_NFC_TOUCH_ID, NFCUIFlowModel.NFC_TOUCH_ID, 0, 64, null);
            }
        }

        /* compiled from: NFCUIFlowModel.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIState$ConnectChip;", "Lco/hyperverge/hyperkyc/ui/nfc/NFCUIFlowModel$NFCUIState;", "()V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class ConnectChip extends NFCUIState {
            public static final ConnectChip INSTANCE = new ConnectChip();

            private ConnectChip() {
                super(R.string.hk_connect_chip, R.string.hk_connect_chip_success, R.string.hk_connect_chip_fail, R.string.hk_connect_chip_processing, NFCUIFlowModel.TEXT_CONFIG_NFC_CONNECT_CHIP, NFCUIFlowModel.NFC_CONNECT_CHIP, 0, 64, null);
            }
        }
    }
}
