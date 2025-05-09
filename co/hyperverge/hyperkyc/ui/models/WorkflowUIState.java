package co.hyperverge.hyperkyc.ui.models;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.browser.trusted.sharing.ShareTarget;
import co.hyperverge.hvqrmodule.objects.HVQRConfig;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import co.hyperverge.hyperkyc.data.models.KycCountry;
import co.hyperverge.hyperkyc.data.models.KycDocument;
import co.hyperverge.hyperkyc.data.models.VideoStatementConfig;
import co.hyperverge.hyperkyc.data.models.VideoStatementV2Config;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import co.hyperverge.hyperkyc.ui.UploadingFragment;
import co.hyperverge.hyperkyc.ui.WebViewFragment;
import co.hyperverge.hyperkyc.ui.form.FormFragment;
import co.hyperverge.hyperkyc.utils.extensions.FileExtsKt;
import co.hyperverge.hypersnapsdk.objects.HVDocConfig;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.google.gson.annotations.SerializedName;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import org.json.JSONObject;

/* compiled from: WorkflowUIState.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u000f\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017B\u0007\b\u0004¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006\u0082\u0001\u000e\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%¨\u0006&"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "", "()V", WebViewFragment.ARG_SUB_TYPE, "", "getSubType", "()Ljava/lang/String;", "tag", "getTag", "ApiCall", "BarcodeCapture", "Child", "DocCapture", "End", "FaceCapture", StandardStructureTypes.FORM, "NFCReader", "PickCountry", "PickDocument", "StartSessionRecording", "StopSessionRecording", "VideoStatement", "VideoStatementV2", "WebView", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$ApiCall;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$BarcodeCapture;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$DocCapture;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$End;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$FaceCapture;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$Form;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$NFCReader;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$PickCountry;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$PickDocument;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$StartSessionRecording;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$StopSessionRecording;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$VideoStatement;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$VideoStatementV2;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$WebView;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class WorkflowUIState {
    private final String subType;

    public /* synthetic */ WorkflowUIState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract String getTag();

    private WorkflowUIState() {
        this.subType = "";
    }

    public String getSubType() {
        return this.subType;
    }

    /* compiled from: WorkflowUIState.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u0017\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\u000bHÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u000b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001f\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001f"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$PickCountry;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "tag", "", "textConfigs", "", "", "countries", "", "Lco/hyperverge/hyperkyc/data/models/KycCountry;", "showBackButton", "", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/List;Z)V", "getCountries", "()Ljava/util/List;", "getShowBackButton", "()Z", "getTag", "()Ljava/lang/String;", "getTextConfigs", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class PickCountry extends WorkflowUIState {
        private final List<KycCountry> countries;
        private final boolean showBackButton;

        @SerializedName("module_id")
        private final String tag;
        private final Map<String, Object> textConfigs;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ PickCountry copy$default(PickCountry pickCountry, String str, Map map, List list, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = pickCountry.getTag();
            }
            if ((i & 2) != 0) {
                map = pickCountry.textConfigs;
            }
            if ((i & 4) != 0) {
                list = pickCountry.countries;
            }
            if ((i & 8) != 0) {
                z = pickCountry.showBackButton;
            }
            return pickCountry.copy(str, map, list, z);
        }

        public final String component1() {
            return getTag();
        }

        public final Map<String, Object> component2() {
            return this.textConfigs;
        }

        public final List<KycCountry> component3() {
            return this.countries;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        public final PickCountry copy(String tag, Map<String, ? extends Object> textConfigs, List<KycCountry> countries, boolean showBackButton) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(countries, "countries");
            return new PickCountry(tag, textConfigs, countries, showBackButton);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PickCountry)) {
                return false;
            }
            PickCountry pickCountry = (PickCountry) other;
            return Intrinsics.areEqual(getTag(), pickCountry.getTag()) && Intrinsics.areEqual(this.textConfigs, pickCountry.textConfigs) && Intrinsics.areEqual(this.countries, pickCountry.countries) && this.showBackButton == pickCountry.showBackButton;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = getTag().hashCode() * 31;
            Map<String, Object> map = this.textConfigs;
            int hashCode2 = (((hashCode + (map == null ? 0 : map.hashCode())) * 31) + this.countries.hashCode()) * 31;
            boolean z = this.showBackButton;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return hashCode2 + i;
        }

        public String toString() {
            return "PickCountry(tag=" + getTag() + ", textConfigs=" + this.textConfigs + ", countries=" + this.countries + ", showBackButton=" + this.showBackButton + ')';
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getTag() {
            return this.tag;
        }

        public /* synthetic */ PickCountry(String str, Map map, List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? MapsKt.emptyMap() : map, list, z);
        }

        public final Map<String, Object> getTextConfigs() {
            return this.textConfigs;
        }

        public final List<KycCountry> getCountries() {
            return this.countries;
        }

        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PickCountry(String tag, Map<String, ? extends Object> map, List<KycCountry> countries, boolean z) {
            super(null);
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(countries, "countries");
            this.tag = tag;
            this.textConfigs = map;
            this.countries = countries;
            this.showBackButton = z;
        }
    }

    /* compiled from: WorkflowUIState.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\bG\b\u0086\b\u0018\u00002\u00020\u0001BÁ\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0006\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u000b\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0010\u0012\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017\u0012\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0017\u0012\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0017\u0012\"\b\u0002\u0010\u001b\u001a\u001c\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001c0\u0017\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0003\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010!\u001a\u00020\u000b¢\u0006\u0002\u0010\"J\t\u0010E\u001a\u00020\u0003HÆ\u0003J\u000f\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00100\u0006HÆ\u0003J\t\u0010G\u001a\u00020\u000bHÆ\u0003J\t\u0010H\u001a\u00020\u0010HÆ\u0003J\t\u0010I\u001a\u00020\u000bHÆ\u0003J\t\u0010J\u001a\u00020\u000bHÆ\u0003J\u0010\u0010K\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010*J\u0017\u0010L\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017HÆ\u0003J\u0015\u0010M\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0017HÆ\u0003J\u0015\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0017HÆ\u0003J#\u0010O\u001a\u001c\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001c0\u0017\u0018\u00010\u0017HÆ\u0003J\t\u0010P\u001a\u00020\u0003HÆ\u0003J\t\u0010Q\u001a\u00020\u000bHÆ\u0003J\t\u0010R\u001a\u00020\u000bHÆ\u0003J\t\u0010S\u001a\u00020\u0003HÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010U\u001a\u00020\u000bHÆ\u0003J\u000f\u0010V\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\t\u0010W\u001a\u00020\u0003HÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010Y\u001a\u00020\u000bHÆ\u0003J\t\u0010Z\u001a\u00020\u000bHÆ\u0003J\t\u0010[\u001a\u00020\u000bHÆ\u0003J\u0011\u0010\\\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006HÆ\u0003JØ\u0002\u0010]\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00062\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u000b2\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00102\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00172\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00172\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00172\"\b\u0002\u0010\u001b\u001a\u001c\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001c0\u0017\u0018\u00010\u00172\b\b\u0002\u0010\u001d\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u00032\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010!\u001a\u00020\u000bHÆ\u0001¢\u0006\u0002\u0010^J\u0013\u0010_\u001a\u00020\u000b2\b\u0010`\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010a\u001a\u00020\u0010HÖ\u0001J\t\u0010b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0012\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0015\u0010\u0015\u001a\u0004\u0018\u00010\u0010¢\u0006\n\n\u0002\u0010+\u001a\u0004\b)\u0010*R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010\u0014\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b.\u0010$R\u0011\u0010\u0013\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b/\u0010$R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b0\u0010&R4\u0010\u001b\u001a\u001c\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001c0\u0017\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u0011\u0010\u001d\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b5\u0010$R\u0013\u0010 \u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010-R&\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00102\"\u0004\b7\u00104R&\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00102\"\u0004\b9\u00104R\u0011\u0010\u0011\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b:\u0010$R\u0011\u0010!\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b;\u0010$R\u0011\u0010\u001f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b<\u0010-R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b=\u0010$R\u0011\u0010\r\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b>\u0010$R\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u0010-R\u0019\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b@\u0010&R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010-R\u001f\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\bB\u00102R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bC\u0010-R\u0011\u0010\u001e\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\bD\u0010$¨\u0006c"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$PickDocument;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "tag", "", WebViewFragment.ARG_SUB_TYPE, "documents", "", "Lco/hyperverge/hyperkyc/data/models/KycDocument;", AnalyticsLogger.Keys.COUNTRY_ID, "url", "allowUpload", "", "showInstruction", "showReview", "supportedUploadFileTypes", "allowedStatusCodes", "", "shouldAutoCapture", "autoCaptureDuration", "disableOCR", "disableBarcodeSkip", "barcodeSkipDelay", "textConfigs", "", "", "ocrParams", "ocrHeaders", "documentsOverride", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$DocumentsOverride;", "enableOverlay", "validateSignature", "showEndState", "isSuccess", "showBackButton", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZZZLjava/util/List;Ljava/util/List;ZIZZLjava/lang/Integer;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;ZZLjava/lang/String;Ljava/lang/String;Z)V", "getAllowUpload", "()Z", "getAllowedStatusCodes", "()Ljava/util/List;", "getAutoCaptureDuration", "()I", "getBarcodeSkipDelay", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCountryId", "()Ljava/lang/String;", "getDisableBarcodeSkip", "getDisableOCR", "getDocuments", "getDocumentsOverride", "()Ljava/util/Map;", "setDocumentsOverride", "(Ljava/util/Map;)V", "getEnableOverlay", "getOcrHeaders", "setOcrHeaders", "getOcrParams", "setOcrParams", "getShouldAutoCapture", "getShowBackButton", "getShowEndState", "getShowInstruction", "getShowReview", "getSubType", "getSupportedUploadFileTypes", "getTag", "getTextConfigs", "getUrl", "getValidateSignature", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZZZLjava/util/List;Ljava/util/List;ZIZZLjava/lang/Integer;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;ZZLjava/lang/String;Ljava/lang/String;Z)Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$PickDocument;", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class PickDocument extends WorkflowUIState {
        private final boolean allowUpload;
        private final List<Integer> allowedStatusCodes;
        private final int autoCaptureDuration;
        private final Integer barcodeSkipDelay;
        private final String countryId;
        private final boolean disableBarcodeSkip;
        private final boolean disableOCR;
        private final List<KycDocument> documents;
        private Map<String, ? extends Map<String, WorkflowModule.Properties.DocumentsOverride>> documentsOverride;
        private final boolean enableOverlay;
        private final String isSuccess;
        private Map<String, String> ocrHeaders;
        private Map<String, String> ocrParams;
        private final boolean shouldAutoCapture;
        private final boolean showBackButton;
        private final String showEndState;
        private final boolean showInstruction;
        private final boolean showReview;

        @SerializedName("module_subType")
        private final String subType;
        private final List<String> supportedUploadFileTypes;

        @SerializedName("module_id")
        private final String tag;
        private final Map<String, Object> textConfigs;
        private final String url;
        private final boolean validateSignature;

        public final String component1() {
            return getTag();
        }

        public final List<Integer> component10() {
            return this.allowedStatusCodes;
        }

        /* renamed from: component11, reason: from getter */
        public final boolean getShouldAutoCapture() {
            return this.shouldAutoCapture;
        }

        /* renamed from: component12, reason: from getter */
        public final int getAutoCaptureDuration() {
            return this.autoCaptureDuration;
        }

        /* renamed from: component13, reason: from getter */
        public final boolean getDisableOCR() {
            return this.disableOCR;
        }

        /* renamed from: component14, reason: from getter */
        public final boolean getDisableBarcodeSkip() {
            return this.disableBarcodeSkip;
        }

        /* renamed from: component15, reason: from getter */
        public final Integer getBarcodeSkipDelay() {
            return this.barcodeSkipDelay;
        }

        public final Map<String, Object> component16() {
            return this.textConfigs;
        }

        public final Map<String, String> component17() {
            return this.ocrParams;
        }

        public final Map<String, String> component18() {
            return this.ocrHeaders;
        }

        public final Map<String, Map<String, WorkflowModule.Properties.DocumentsOverride>> component19() {
            return this.documentsOverride;
        }

        public final String component2() {
            return getSubType();
        }

        /* renamed from: component20, reason: from getter */
        public final boolean getEnableOverlay() {
            return this.enableOverlay;
        }

        /* renamed from: component21, reason: from getter */
        public final boolean getValidateSignature() {
            return this.validateSignature;
        }

        /* renamed from: component22, reason: from getter */
        public final String getShowEndState() {
            return this.showEndState;
        }

        /* renamed from: component23, reason: from getter */
        public final String getIsSuccess() {
            return this.isSuccess;
        }

        /* renamed from: component24, reason: from getter */
        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        public final List<KycDocument> component3() {
            return this.documents;
        }

        /* renamed from: component4, reason: from getter */
        public final String getCountryId() {
            return this.countryId;
        }

        /* renamed from: component5, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getAllowUpload() {
            return this.allowUpload;
        }

        /* renamed from: component7, reason: from getter */
        public final boolean getShowInstruction() {
            return this.showInstruction;
        }

        /* renamed from: component8, reason: from getter */
        public final boolean getShowReview() {
            return this.showReview;
        }

        public final List<String> component9() {
            return this.supportedUploadFileTypes;
        }

        public final PickDocument copy(String tag, String subType, List<KycDocument> documents, String countryId, String url, boolean allowUpload, boolean showInstruction, boolean showReview, List<String> supportedUploadFileTypes, List<Integer> allowedStatusCodes, boolean shouldAutoCapture, int autoCaptureDuration, boolean disableOCR, boolean disableBarcodeSkip, Integer barcodeSkipDelay, Map<String, ? extends Object> textConfigs, Map<String, String> ocrParams, Map<String, String> ocrHeaders, Map<String, ? extends Map<String, WorkflowModule.Properties.DocumentsOverride>> documentsOverride, boolean enableOverlay, boolean validateSignature, String showEndState, String isSuccess, boolean showBackButton) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(subType, "subType");
            Intrinsics.checkNotNullParameter(documents, "documents");
            Intrinsics.checkNotNullParameter(countryId, "countryId");
            Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
            Intrinsics.checkNotNullParameter(ocrParams, "ocrParams");
            Intrinsics.checkNotNullParameter(ocrHeaders, "ocrHeaders");
            Intrinsics.checkNotNullParameter(showEndState, "showEndState");
            return new PickDocument(tag, subType, documents, countryId, url, allowUpload, showInstruction, showReview, supportedUploadFileTypes, allowedStatusCodes, shouldAutoCapture, autoCaptureDuration, disableOCR, disableBarcodeSkip, barcodeSkipDelay, textConfigs, ocrParams, ocrHeaders, documentsOverride, enableOverlay, validateSignature, showEndState, isSuccess, showBackButton);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PickDocument)) {
                return false;
            }
            PickDocument pickDocument = (PickDocument) other;
            return Intrinsics.areEqual(getTag(), pickDocument.getTag()) && Intrinsics.areEqual(getSubType(), pickDocument.getSubType()) && Intrinsics.areEqual(this.documents, pickDocument.documents) && Intrinsics.areEqual(this.countryId, pickDocument.countryId) && Intrinsics.areEqual(this.url, pickDocument.url) && this.allowUpload == pickDocument.allowUpload && this.showInstruction == pickDocument.showInstruction && this.showReview == pickDocument.showReview && Intrinsics.areEqual(this.supportedUploadFileTypes, pickDocument.supportedUploadFileTypes) && Intrinsics.areEqual(this.allowedStatusCodes, pickDocument.allowedStatusCodes) && this.shouldAutoCapture == pickDocument.shouldAutoCapture && this.autoCaptureDuration == pickDocument.autoCaptureDuration && this.disableOCR == pickDocument.disableOCR && this.disableBarcodeSkip == pickDocument.disableBarcodeSkip && Intrinsics.areEqual(this.barcodeSkipDelay, pickDocument.barcodeSkipDelay) && Intrinsics.areEqual(this.textConfigs, pickDocument.textConfigs) && Intrinsics.areEqual(this.ocrParams, pickDocument.ocrParams) && Intrinsics.areEqual(this.ocrHeaders, pickDocument.ocrHeaders) && Intrinsics.areEqual(this.documentsOverride, pickDocument.documentsOverride) && this.enableOverlay == pickDocument.enableOverlay && this.validateSignature == pickDocument.validateSignature && Intrinsics.areEqual(this.showEndState, pickDocument.showEndState) && Intrinsics.areEqual(this.isSuccess, pickDocument.isSuccess) && this.showBackButton == pickDocument.showBackButton;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = ((((((getTag().hashCode() * 31) + getSubType().hashCode()) * 31) + this.documents.hashCode()) * 31) + this.countryId.hashCode()) * 31;
            String str = this.url;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            boolean z = this.allowUpload;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (hashCode2 + i) * 31;
            boolean z2 = this.showInstruction;
            int i3 = z2;
            if (z2 != 0) {
                i3 = 1;
            }
            int i4 = (i2 + i3) * 31;
            boolean z3 = this.showReview;
            int i5 = z3;
            if (z3 != 0) {
                i5 = 1;
            }
            int i6 = (i4 + i5) * 31;
            List<String> list = this.supportedUploadFileTypes;
            int hashCode3 = (((i6 + (list == null ? 0 : list.hashCode())) * 31) + this.allowedStatusCodes.hashCode()) * 31;
            boolean z4 = this.shouldAutoCapture;
            int i7 = z4;
            if (z4 != 0) {
                i7 = 1;
            }
            int i8 = (((hashCode3 + i7) * 31) + this.autoCaptureDuration) * 31;
            boolean z5 = this.disableOCR;
            int i9 = z5;
            if (z5 != 0) {
                i9 = 1;
            }
            int i10 = (i8 + i9) * 31;
            boolean z6 = this.disableBarcodeSkip;
            int i11 = z6;
            if (z6 != 0) {
                i11 = 1;
            }
            int i12 = (i10 + i11) * 31;
            Integer num = this.barcodeSkipDelay;
            int hashCode4 = (i12 + (num == null ? 0 : num.hashCode())) * 31;
            Map<String, Object> map = this.textConfigs;
            int hashCode5 = (((((hashCode4 + (map == null ? 0 : map.hashCode())) * 31) + this.ocrParams.hashCode()) * 31) + this.ocrHeaders.hashCode()) * 31;
            Map<String, ? extends Map<String, WorkflowModule.Properties.DocumentsOverride>> map2 = this.documentsOverride;
            int hashCode6 = (hashCode5 + (map2 == null ? 0 : map2.hashCode())) * 31;
            boolean z7 = this.enableOverlay;
            int i13 = z7;
            if (z7 != 0) {
                i13 = 1;
            }
            int i14 = (hashCode6 + i13) * 31;
            boolean z8 = this.validateSignature;
            int i15 = z8;
            if (z8 != 0) {
                i15 = 1;
            }
            int hashCode7 = (((i14 + i15) * 31) + this.showEndState.hashCode()) * 31;
            String str2 = this.isSuccess;
            int hashCode8 = (hashCode7 + (str2 != null ? str2.hashCode() : 0)) * 31;
            boolean z9 = this.showBackButton;
            return hashCode8 + (z9 ? 1 : z9 ? 1 : 0);
        }

        public String toString() {
            return "PickDocument(tag=" + getTag() + ", subType=" + getSubType() + ", documents=" + this.documents + ", countryId=" + this.countryId + ", url=" + this.url + ", allowUpload=" + this.allowUpload + ", showInstruction=" + this.showInstruction + ", showReview=" + this.showReview + ", supportedUploadFileTypes=" + this.supportedUploadFileTypes + ", allowedStatusCodes=" + this.allowedStatusCodes + ", shouldAutoCapture=" + this.shouldAutoCapture + ", autoCaptureDuration=" + this.autoCaptureDuration + ", disableOCR=" + this.disableOCR + ", disableBarcodeSkip=" + this.disableBarcodeSkip + ", barcodeSkipDelay=" + this.barcodeSkipDelay + ", textConfigs=" + this.textConfigs + ", ocrParams=" + this.ocrParams + ", ocrHeaders=" + this.ocrHeaders + ", documentsOverride=" + this.documentsOverride + ", enableOverlay=" + this.enableOverlay + ", validateSignature=" + this.validateSignature + ", showEndState=" + this.showEndState + ", isSuccess=" + this.isSuccess + ", showBackButton=" + this.showBackButton + ')';
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getTag() {
            return this.tag;
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getSubType() {
            return this.subType;
        }

        public final List<KycDocument> getDocuments() {
            return this.documents;
        }

        public final String getCountryId() {
            return this.countryId;
        }

        public final String getUrl() {
            return this.url;
        }

        public final boolean getAllowUpload() {
            return this.allowUpload;
        }

        public final boolean getShowInstruction() {
            return this.showInstruction;
        }

        public final boolean getShowReview() {
            return this.showReview;
        }

        public final List<String> getSupportedUploadFileTypes() {
            return this.supportedUploadFileTypes;
        }

        public final List<Integer> getAllowedStatusCodes() {
            return this.allowedStatusCodes;
        }

        public final boolean getShouldAutoCapture() {
            return this.shouldAutoCapture;
        }

        public final int getAutoCaptureDuration() {
            return this.autoCaptureDuration;
        }

        public final boolean getDisableOCR() {
            return this.disableOCR;
        }

        public final boolean getDisableBarcodeSkip() {
            return this.disableBarcodeSkip;
        }

        public final Integer getBarcodeSkipDelay() {
            return this.barcodeSkipDelay;
        }

        public /* synthetic */ PickDocument(String str, String str2, List list, String str3, String str4, boolean z, boolean z2, boolean z3, List list2, List list3, boolean z4, int i, boolean z5, boolean z6, Integer num, Map map, Map map2, Map map3, Map map4, boolean z7, boolean z8, String str5, String str6, boolean z9, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, list, str3, str4, (i2 & 32) != 0 ? false : z, (i2 & 64) != 0 ? true : z2, (i2 & 128) != 0 ? false : z3, (i2 & 256) != 0 ? null : list2, list3, (i2 & 1024) != 0 ? false : z4, (i2 & 2048) != 0 ? 0 : i, (i2 & 4096) != 0 ? false : z5, (i2 & 8192) != 0 ? false : z6, (i2 & 16384) != 0 ? null : num, (32768 & i2) != 0 ? MapsKt.emptyMap() : map, (65536 & i2) != 0 ? MapsKt.emptyMap() : map2, (131072 & i2) != 0 ? MapsKt.emptyMap() : map3, (262144 & i2) != 0 ? null : map4, (524288 & i2) != 0 ? true : z7, (1048576 & i2) != 0 ? false : z8, (2097152 & i2) != 0 ? "no" : str5, (i2 & 4194304) != 0 ? null : str6, z9);
        }

        public final Map<String, Object> getTextConfigs() {
            return this.textConfigs;
        }

        public final Map<String, String> getOcrParams() {
            return this.ocrParams;
        }

        public final void setOcrParams(Map<String, String> map) {
            Intrinsics.checkNotNullParameter(map, "<set-?>");
            this.ocrParams = map;
        }

        public final Map<String, String> getOcrHeaders() {
            return this.ocrHeaders;
        }

        public final void setOcrHeaders(Map<String, String> map) {
            Intrinsics.checkNotNullParameter(map, "<set-?>");
            this.ocrHeaders = map;
        }

        public final Map<String, Map<String, WorkflowModule.Properties.DocumentsOverride>> getDocumentsOverride() {
            return this.documentsOverride;
        }

        public final void setDocumentsOverride(Map<String, ? extends Map<String, WorkflowModule.Properties.DocumentsOverride>> map) {
            this.documentsOverride = map;
        }

        public final boolean getEnableOverlay() {
            return this.enableOverlay;
        }

        public final boolean getValidateSignature() {
            return this.validateSignature;
        }

        public final String getShowEndState() {
            return this.showEndState;
        }

        public final String isSuccess() {
            return this.isSuccess;
        }

        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PickDocument(String tag, String subType, List<KycDocument> documents, String countryId, String str, boolean z, boolean z2, boolean z3, List<String> list, List<Integer> allowedStatusCodes, boolean z4, int i, boolean z5, boolean z6, Integer num, Map<String, ? extends Object> map, Map<String, String> ocrParams, Map<String, String> ocrHeaders, Map<String, ? extends Map<String, WorkflowModule.Properties.DocumentsOverride>> map2, boolean z7, boolean z8, String showEndState, String str2, boolean z9) {
            super(null);
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(subType, "subType");
            Intrinsics.checkNotNullParameter(documents, "documents");
            Intrinsics.checkNotNullParameter(countryId, "countryId");
            Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
            Intrinsics.checkNotNullParameter(ocrParams, "ocrParams");
            Intrinsics.checkNotNullParameter(ocrHeaders, "ocrHeaders");
            Intrinsics.checkNotNullParameter(showEndState, "showEndState");
            this.tag = tag;
            this.subType = subType;
            this.documents = documents;
            this.countryId = countryId;
            this.url = str;
            this.allowUpload = z;
            this.showInstruction = z2;
            this.showReview = z3;
            this.supportedUploadFileTypes = list;
            this.allowedStatusCodes = allowedStatusCodes;
            this.shouldAutoCapture = z4;
            this.autoCaptureDuration = i;
            this.disableOCR = z5;
            this.disableBarcodeSkip = z6;
            this.barcodeSkipDelay = num;
            this.textConfigs = map;
            this.ocrParams = ocrParams;
            this.ocrHeaders = ocrHeaders;
            this.documentsOverride = map2;
            this.enableOverlay = z7;
            this.validateSignature = z8;
            this.showEndState = showEndState;
            this.isSuccess = str2;
            this.showBackButton = z9;
        }
    }

    /* compiled from: WorkflowUIState.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b2\b\u0086\b\u0018\u00002\u00020\u0001BÅ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012\u0012\u0014\b\u0002\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0012\u0012\u0014\b\u0002\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0012\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u001aJ\t\u00100\u001a\u00020\u0003HÆ\u0003J\u0017\u00101\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012HÆ\u0003J\u0015\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0012HÆ\u0003J\u0015\u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0012HÆ\u0003J\t\u00104\u001a\u00020\u0010HÆ\u0003J\t\u00105\u001a\u00020\u0010HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0006HÆ\u0003J\t\u0010:\u001a\u00020\bHÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\t\u0010<\u001a\u00020\u0003HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\t\u0010?\u001a\u00020\u0010HÆ\u0003JÙ\u0001\u0010@\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u0014\b\u0002\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00122\u0014\b\u0002\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u00102\b\b\u0002\u0010\u0017\u001a\u00020\u00102\b\b\u0002\u0010\u0018\u001a\u00020\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010A\u001a\u00020\u00102\b\u0010B\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010C\u001a\u00020\u000eHÖ\u0001J\t\u0010D\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0016\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b%\u0010 R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001eR\u001d\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0012¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u001d\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0012¢\u0006\b\n\u0000\u001a\u0004\b(\u0010'R\u0011\u0010\u0018\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001eR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001eR\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001eR\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001eR\u001f\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b-\u0010'R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001eR\u0011\u0010\u0017\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b/\u0010 ¨\u0006E"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$DocCapture;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "tag", "", WebViewFragment.ARG_SUB_TYPE, WorkflowModule.TYPE_DOCUMENT, "Lco/hyperverge/hyperkyc/data/models/KycDocument;", "docConfig", "Lco/hyperverge/hypersnapsdk/objects/HVDocConfig;", "side", AnalyticsLogger.Keys.COUNTRY_ID, "url", "allowedStatusCodes", "", "", "disableOCR", "", "textConfigs", "", "", "ocrParams", "ocrHeaders", "enableOverlay", "validateSignature", "showEndState", "isSuccess", "(Ljava/lang/String;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/KycDocument;Lco/hyperverge/hypersnapsdk/objects/HVDocConfig;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZLjava/util/Map;Ljava/util/Map;Ljava/util/Map;ZZLjava/lang/String;Ljava/lang/String;)V", "getAllowedStatusCodes", "()Ljava/util/List;", "getCountryId", "()Ljava/lang/String;", "getDisableOCR", "()Z", "getDocConfig", "()Lco/hyperverge/hypersnapsdk/objects/HVDocConfig;", "getDocument", "()Lco/hyperverge/hyperkyc/data/models/KycDocument;", "getEnableOverlay", "getOcrHeaders", "()Ljava/util/Map;", "getOcrParams", "getShowEndState", "getSide", "getSubType", "getTag", "getTextConfigs", "getUrl", "getValidateSignature", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class DocCapture extends WorkflowUIState {
        private final List<Integer> allowedStatusCodes;
        private final String countryId;
        private final boolean disableOCR;
        private final HVDocConfig docConfig;
        private final KycDocument document;
        private final boolean enableOverlay;
        private final String isSuccess;
        private final Map<String, String> ocrHeaders;
        private final Map<String, String> ocrParams;
        private final String showEndState;
        private final String side;

        @SerializedName("module_subType")
        private final String subType;

        @SerializedName("module_id")
        private final String tag;
        private final Map<String, Object> textConfigs;
        private final String url;
        private final boolean validateSignature;

        public final String component1() {
            return getTag();
        }

        public final Map<String, Object> component10() {
            return this.textConfigs;
        }

        public final Map<String, String> component11() {
            return this.ocrParams;
        }

        public final Map<String, String> component12() {
            return this.ocrHeaders;
        }

        /* renamed from: component13, reason: from getter */
        public final boolean getEnableOverlay() {
            return this.enableOverlay;
        }

        /* renamed from: component14, reason: from getter */
        public final boolean getValidateSignature() {
            return this.validateSignature;
        }

        /* renamed from: component15, reason: from getter */
        public final String getShowEndState() {
            return this.showEndState;
        }

        /* renamed from: component16, reason: from getter */
        public final String getIsSuccess() {
            return this.isSuccess;
        }

        public final String component2() {
            return getSubType();
        }

        /* renamed from: component3, reason: from getter */
        public final KycDocument getDocument() {
            return this.document;
        }

        /* renamed from: component4, reason: from getter */
        public final HVDocConfig getDocConfig() {
            return this.docConfig;
        }

        /* renamed from: component5, reason: from getter */
        public final String getSide() {
            return this.side;
        }

        /* renamed from: component6, reason: from getter */
        public final String getCountryId() {
            return this.countryId;
        }

        /* renamed from: component7, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        public final List<Integer> component8() {
            return this.allowedStatusCodes;
        }

        /* renamed from: component9, reason: from getter */
        public final boolean getDisableOCR() {
            return this.disableOCR;
        }

        public final DocCapture copy(String tag, String subType, KycDocument document, HVDocConfig docConfig, String side, String countryId, String url, List<Integer> allowedStatusCodes, boolean disableOCR, Map<String, ? extends Object> textConfigs, Map<String, String> ocrParams, Map<String, String> ocrHeaders, boolean enableOverlay, boolean validateSignature, String showEndState, String isSuccess) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(subType, "subType");
            Intrinsics.checkNotNullParameter(document, "document");
            Intrinsics.checkNotNullParameter(docConfig, "docConfig");
            Intrinsics.checkNotNullParameter(side, "side");
            Intrinsics.checkNotNullParameter(countryId, "countryId");
            Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
            Intrinsics.checkNotNullParameter(ocrParams, "ocrParams");
            Intrinsics.checkNotNullParameter(ocrHeaders, "ocrHeaders");
            Intrinsics.checkNotNullParameter(showEndState, "showEndState");
            return new DocCapture(tag, subType, document, docConfig, side, countryId, url, allowedStatusCodes, disableOCR, textConfigs, ocrParams, ocrHeaders, enableOverlay, validateSignature, showEndState, isSuccess);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DocCapture)) {
                return false;
            }
            DocCapture docCapture = (DocCapture) other;
            return Intrinsics.areEqual(getTag(), docCapture.getTag()) && Intrinsics.areEqual(getSubType(), docCapture.getSubType()) && Intrinsics.areEqual(this.document, docCapture.document) && Intrinsics.areEqual(this.docConfig, docCapture.docConfig) && Intrinsics.areEqual(this.side, docCapture.side) && Intrinsics.areEqual(this.countryId, docCapture.countryId) && Intrinsics.areEqual(this.url, docCapture.url) && Intrinsics.areEqual(this.allowedStatusCodes, docCapture.allowedStatusCodes) && this.disableOCR == docCapture.disableOCR && Intrinsics.areEqual(this.textConfigs, docCapture.textConfigs) && Intrinsics.areEqual(this.ocrParams, docCapture.ocrParams) && Intrinsics.areEqual(this.ocrHeaders, docCapture.ocrHeaders) && this.enableOverlay == docCapture.enableOverlay && this.validateSignature == docCapture.validateSignature && Intrinsics.areEqual(this.showEndState, docCapture.showEndState) && Intrinsics.areEqual(this.isSuccess, docCapture.isSuccess);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = ((((((((((getTag().hashCode() * 31) + getSubType().hashCode()) * 31) + this.document.hashCode()) * 31) + this.docConfig.hashCode()) * 31) + this.side.hashCode()) * 31) + this.countryId.hashCode()) * 31;
            String str = this.url;
            int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.allowedStatusCodes.hashCode()) * 31;
            boolean z = this.disableOCR;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (hashCode2 + i) * 31;
            Map<String, Object> map = this.textConfigs;
            int hashCode3 = (((((i2 + (map == null ? 0 : map.hashCode())) * 31) + this.ocrParams.hashCode()) * 31) + this.ocrHeaders.hashCode()) * 31;
            boolean z2 = this.enableOverlay;
            int i3 = z2;
            if (z2 != 0) {
                i3 = 1;
            }
            int i4 = (hashCode3 + i3) * 31;
            boolean z3 = this.validateSignature;
            int hashCode4 = (((i4 + (z3 ? 1 : z3 ? 1 : 0)) * 31) + this.showEndState.hashCode()) * 31;
            String str2 = this.isSuccess;
            return hashCode4 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "DocCapture(tag=" + getTag() + ", subType=" + getSubType() + ", document=" + this.document + ", docConfig=" + this.docConfig + ", side=" + this.side + ", countryId=" + this.countryId + ", url=" + this.url + ", allowedStatusCodes=" + this.allowedStatusCodes + ", disableOCR=" + this.disableOCR + ", textConfigs=" + this.textConfigs + ", ocrParams=" + this.ocrParams + ", ocrHeaders=" + this.ocrHeaders + ", enableOverlay=" + this.enableOverlay + ", validateSignature=" + this.validateSignature + ", showEndState=" + this.showEndState + ", isSuccess=" + this.isSuccess + ')';
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getTag() {
            return this.tag;
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getSubType() {
            return this.subType;
        }

        public final KycDocument getDocument() {
            return this.document;
        }

        public final HVDocConfig getDocConfig() {
            return this.docConfig;
        }

        public final String getSide() {
            return this.side;
        }

        public final String getCountryId() {
            return this.countryId;
        }

        public final String getUrl() {
            return this.url;
        }

        public final List<Integer> getAllowedStatusCodes() {
            return this.allowedStatusCodes;
        }

        public final boolean getDisableOCR() {
            return this.disableOCR;
        }

        public /* synthetic */ DocCapture(String str, String str2, KycDocument kycDocument, HVDocConfig hVDocConfig, String str3, String str4, String str5, List list, boolean z, Map map, Map map2, Map map3, boolean z2, boolean z3, String str6, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, kycDocument, hVDocConfig, str3, str4, str5, list, (i & 256) != 0 ? false : z, (i & 512) != 0 ? MapsKt.emptyMap() : map, (i & 1024) != 0 ? MapsKt.emptyMap() : map2, (i & 2048) != 0 ? MapsKt.emptyMap() : map3, (i & 4096) != 0 ? true : z2, (i & 8192) != 0 ? false : z3, (i & 16384) != 0 ? "no" : str6, (i & 32768) != 0 ? null : str7);
        }

        public final Map<String, Object> getTextConfigs() {
            return this.textConfigs;
        }

        public final Map<String, String> getOcrParams() {
            return this.ocrParams;
        }

        public final Map<String, String> getOcrHeaders() {
            return this.ocrHeaders;
        }

        public final boolean getEnableOverlay() {
            return this.enableOverlay;
        }

        public final boolean getValidateSignature() {
            return this.validateSignature;
        }

        public final String getShowEndState() {
            return this.showEndState;
        }

        public final String isSuccess() {
            return this.isSuccess;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DocCapture(String tag, String subType, KycDocument document, HVDocConfig docConfig, String side, String countryId, String str, List<Integer> allowedStatusCodes, boolean z, Map<String, ? extends Object> map, Map<String, String> ocrParams, Map<String, String> ocrHeaders, boolean z2, boolean z3, String showEndState, String str2) {
            super(null);
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(subType, "subType");
            Intrinsics.checkNotNullParameter(document, "document");
            Intrinsics.checkNotNullParameter(docConfig, "docConfig");
            Intrinsics.checkNotNullParameter(side, "side");
            Intrinsics.checkNotNullParameter(countryId, "countryId");
            Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
            Intrinsics.checkNotNullParameter(ocrParams, "ocrParams");
            Intrinsics.checkNotNullParameter(ocrHeaders, "ocrHeaders");
            Intrinsics.checkNotNullParameter(showEndState, "showEndState");
            this.tag = tag;
            this.subType = subType;
            this.document = document;
            this.docConfig = docConfig;
            this.side = side;
            this.countryId = countryId;
            this.url = str;
            this.allowedStatusCodes = allowedStatusCodes;
            this.disableOCR = z;
            this.textConfigs = map;
            this.ocrParams = ocrParams;
            this.ocrHeaders = ocrHeaders;
            this.enableOverlay = z2;
            this.validateSignature = z3;
            this.showEndState = showEndState;
            this.isSuccess = str2;
        }
    }

    /* compiled from: WorkflowUIState.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b3\b\u0086\b\u0018\u00002\u00020\u0001BÉ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f\u0012\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000f\u0012\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000f\u0012\b\b\u0002\u0010\u0013\u001a\u00020\t\u0012\b\b\u0002\u0010\u0014\u001a\u00020\t\u0012\b\b\u0002\u0010\u0015\u001a\u00020\t\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0018\u001a\u00020\t¢\u0006\u0002\u0010\u0019J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\u0015\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000fHÆ\u0003J\t\u00100\u001a\u00020\tHÆ\u0003J\t\u00101\u001a\u00020\tHÆ\u0003J\t\u00102\u001a\u00020\tHÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00105\u001a\u00020\tHÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0006HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00109\u001a\u00020\tHÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010;\u001a\b\u0012\u0004\u0012\u00020\r0\fHÆ\u0003J\u0017\u0010<\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fHÆ\u0003J\u0015\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000fHÆ\u0003JÝ\u0001\u0010>\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000f2\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\t2\b\b\u0002\u0010\u0015\u001a\u00020\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0018\u001a\u00020\tHÆ\u0001J\u0013\u0010?\u001a\u00020\t2\b\u0010@\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010A\u001a\u00020\rHÖ\u0001J\t\u0010B\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0015\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\u0013\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u001dR\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000f¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001d\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000f¢\u0006\b\n\u0000\u001a\u0004\b&\u0010%R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001dR\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001dR\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001dR\u001f\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b*\u0010%R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001dR\u0011\u0010\u0014\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001fR\u0011\u0010\u0018\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001f¨\u0006C"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$FaceCapture;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "tag", "", WebViewFragment.ARG_SUB_TYPE, "faceConfig", "Lco/hyperverge/hypersnapsdk/objects/HVFaceConfig;", "url", "disableLiveness", "", "defaultCamera", "allowedStatusCodes", "", "", "textConfigs", "", "", "livenessParams", "livenessHeaders", "enableOverlay", "validateSignature", "enableLookStraight", "showEndState", "isSuccess", "zoomByDefault", "(Ljava/lang/String;Ljava/lang/String;Lco/hyperverge/hypersnapsdk/objects/HVFaceConfig;Ljava/lang/String;ZLjava/lang/String;Ljava/util/List;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;ZZZLjava/lang/String;Ljava/lang/String;Z)V", "getAllowedStatusCodes", "()Ljava/util/List;", "getDefaultCamera", "()Ljava/lang/String;", "getDisableLiveness", "()Z", "getEnableLookStraight", "getEnableOverlay", "getFaceConfig", "()Lco/hyperverge/hypersnapsdk/objects/HVFaceConfig;", "getLivenessHeaders", "()Ljava/util/Map;", "getLivenessParams", "getShowEndState", "getSubType", "getTag", "getTextConfigs", "getUrl", "getValidateSignature", "getZoomByDefault", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class FaceCapture extends WorkflowUIState {
        private final List<Integer> allowedStatusCodes;
        private final String defaultCamera;
        private final boolean disableLiveness;
        private final boolean enableLookStraight;
        private final boolean enableOverlay;
        private final HVFaceConfig faceConfig;
        private final String isSuccess;
        private final Map<String, String> livenessHeaders;
        private final Map<String, String> livenessParams;
        private final String showEndState;

        @SerializedName("module_subType")
        private final String subType;

        @SerializedName("module_id")
        private final String tag;
        private final Map<String, Object> textConfigs;
        private final String url;
        private final boolean validateSignature;
        private final boolean zoomByDefault;

        public final String component1() {
            return getTag();
        }

        public final Map<String, String> component10() {
            return this.livenessHeaders;
        }

        /* renamed from: component11, reason: from getter */
        public final boolean getEnableOverlay() {
            return this.enableOverlay;
        }

        /* renamed from: component12, reason: from getter */
        public final boolean getValidateSignature() {
            return this.validateSignature;
        }

        /* renamed from: component13, reason: from getter */
        public final boolean getEnableLookStraight() {
            return this.enableLookStraight;
        }

        /* renamed from: component14, reason: from getter */
        public final String getShowEndState() {
            return this.showEndState;
        }

        /* renamed from: component15, reason: from getter */
        public final String getIsSuccess() {
            return this.isSuccess;
        }

        /* renamed from: component16, reason: from getter */
        public final boolean getZoomByDefault() {
            return this.zoomByDefault;
        }

        public final String component2() {
            return getSubType();
        }

        /* renamed from: component3, reason: from getter */
        public final HVFaceConfig getFaceConfig() {
            return this.faceConfig;
        }

        /* renamed from: component4, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getDisableLiveness() {
            return this.disableLiveness;
        }

        /* renamed from: component6, reason: from getter */
        public final String getDefaultCamera() {
            return this.defaultCamera;
        }

        public final List<Integer> component7() {
            return this.allowedStatusCodes;
        }

        public final Map<String, Object> component8() {
            return this.textConfigs;
        }

        public final Map<String, String> component9() {
            return this.livenessParams;
        }

        public final FaceCapture copy(String tag, String subType, HVFaceConfig faceConfig, String url, boolean disableLiveness, String defaultCamera, List<Integer> allowedStatusCodes, Map<String, ? extends Object> textConfigs, Map<String, String> livenessParams, Map<String, String> livenessHeaders, boolean enableOverlay, boolean validateSignature, boolean enableLookStraight, String showEndState, String isSuccess, boolean zoomByDefault) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(subType, "subType");
            Intrinsics.checkNotNullParameter(faceConfig, "faceConfig");
            Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
            Intrinsics.checkNotNullParameter(livenessParams, "livenessParams");
            Intrinsics.checkNotNullParameter(livenessHeaders, "livenessHeaders");
            return new FaceCapture(tag, subType, faceConfig, url, disableLiveness, defaultCamera, allowedStatusCodes, textConfigs, livenessParams, livenessHeaders, enableOverlay, validateSignature, enableLookStraight, showEndState, isSuccess, zoomByDefault);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FaceCapture)) {
                return false;
            }
            FaceCapture faceCapture = (FaceCapture) other;
            return Intrinsics.areEqual(getTag(), faceCapture.getTag()) && Intrinsics.areEqual(getSubType(), faceCapture.getSubType()) && Intrinsics.areEqual(this.faceConfig, faceCapture.faceConfig) && Intrinsics.areEqual(this.url, faceCapture.url) && this.disableLiveness == faceCapture.disableLiveness && Intrinsics.areEqual(this.defaultCamera, faceCapture.defaultCamera) && Intrinsics.areEqual(this.allowedStatusCodes, faceCapture.allowedStatusCodes) && Intrinsics.areEqual(this.textConfigs, faceCapture.textConfigs) && Intrinsics.areEqual(this.livenessParams, faceCapture.livenessParams) && Intrinsics.areEqual(this.livenessHeaders, faceCapture.livenessHeaders) && this.enableOverlay == faceCapture.enableOverlay && this.validateSignature == faceCapture.validateSignature && this.enableLookStraight == faceCapture.enableLookStraight && Intrinsics.areEqual(this.showEndState, faceCapture.showEndState) && Intrinsics.areEqual(this.isSuccess, faceCapture.isSuccess) && this.zoomByDefault == faceCapture.zoomByDefault;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = ((((getTag().hashCode() * 31) + getSubType().hashCode()) * 31) + this.faceConfig.hashCode()) * 31;
            String str = this.url;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            boolean z = this.disableLiveness;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (hashCode2 + i) * 31;
            String str2 = this.defaultCamera;
            int hashCode3 = (((i2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.allowedStatusCodes.hashCode()) * 31;
            Map<String, Object> map = this.textConfigs;
            int hashCode4 = (((((hashCode3 + (map == null ? 0 : map.hashCode())) * 31) + this.livenessParams.hashCode()) * 31) + this.livenessHeaders.hashCode()) * 31;
            boolean z2 = this.enableOverlay;
            int i3 = z2;
            if (z2 != 0) {
                i3 = 1;
            }
            int i4 = (hashCode4 + i3) * 31;
            boolean z3 = this.validateSignature;
            int i5 = z3;
            if (z3 != 0) {
                i5 = 1;
            }
            int i6 = (i4 + i5) * 31;
            boolean z4 = this.enableLookStraight;
            int i7 = z4;
            if (z4 != 0) {
                i7 = 1;
            }
            int i8 = (i6 + i7) * 31;
            String str3 = this.showEndState;
            int hashCode5 = (i8 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.isSuccess;
            int hashCode6 = (hashCode5 + (str4 != null ? str4.hashCode() : 0)) * 31;
            boolean z5 = this.zoomByDefault;
            return hashCode6 + (z5 ? 1 : z5 ? 1 : 0);
        }

        public String toString() {
            return "FaceCapture(tag=" + getTag() + ", subType=" + getSubType() + ", faceConfig=" + this.faceConfig + ", url=" + this.url + ", disableLiveness=" + this.disableLiveness + ", defaultCamera=" + this.defaultCamera + ", allowedStatusCodes=" + this.allowedStatusCodes + ", textConfigs=" + this.textConfigs + ", livenessParams=" + this.livenessParams + ", livenessHeaders=" + this.livenessHeaders + ", enableOverlay=" + this.enableOverlay + ", validateSignature=" + this.validateSignature + ", enableLookStraight=" + this.enableLookStraight + ", showEndState=" + this.showEndState + ", isSuccess=" + this.isSuccess + ", zoomByDefault=" + this.zoomByDefault + ')';
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getTag() {
            return this.tag;
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getSubType() {
            return this.subType;
        }

        public final HVFaceConfig getFaceConfig() {
            return this.faceConfig;
        }

        public final String getUrl() {
            return this.url;
        }

        public final boolean getDisableLiveness() {
            return this.disableLiveness;
        }

        public final String getDefaultCamera() {
            return this.defaultCamera;
        }

        public final List<Integer> getAllowedStatusCodes() {
            return this.allowedStatusCodes;
        }

        public /* synthetic */ FaceCapture(String str, String str2, HVFaceConfig hVFaceConfig, String str3, boolean z, String str4, List list, Map map, Map map2, Map map3, boolean z2, boolean z3, boolean z4, String str5, String str6, boolean z5, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, hVFaceConfig, str3, z, str4, list, (i & 128) != 0 ? MapsKt.emptyMap() : map, (i & 256) != 0 ? MapsKt.emptyMap() : map2, (i & 512) != 0 ? MapsKt.emptyMap() : map3, (i & 1024) != 0 ? true : z2, (i & 2048) != 0 ? false : z3, (i & 4096) != 0 ? false : z4, (i & 8192) != 0 ? "no" : str5, (i & 16384) != 0 ? null : str6, z5);
        }

        public final Map<String, Object> getTextConfigs() {
            return this.textConfigs;
        }

        public final Map<String, String> getLivenessParams() {
            return this.livenessParams;
        }

        public final Map<String, String> getLivenessHeaders() {
            return this.livenessHeaders;
        }

        public final boolean getEnableOverlay() {
            return this.enableOverlay;
        }

        public final boolean getValidateSignature() {
            return this.validateSignature;
        }

        public final boolean getEnableLookStraight() {
            return this.enableLookStraight;
        }

        public final String getShowEndState() {
            return this.showEndState;
        }

        public final String isSuccess() {
            return this.isSuccess;
        }

        public final boolean getZoomByDefault() {
            return this.zoomByDefault;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FaceCapture(String tag, String subType, HVFaceConfig faceConfig, String str, boolean z, String str2, List<Integer> allowedStatusCodes, Map<String, ? extends Object> map, Map<String, String> livenessParams, Map<String, String> livenessHeaders, boolean z2, boolean z3, boolean z4, String str3, String str4, boolean z5) {
            super(null);
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(subType, "subType");
            Intrinsics.checkNotNullParameter(faceConfig, "faceConfig");
            Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
            Intrinsics.checkNotNullParameter(livenessParams, "livenessParams");
            Intrinsics.checkNotNullParameter(livenessHeaders, "livenessHeaders");
            this.tag = tag;
            this.subType = subType;
            this.faceConfig = faceConfig;
            this.url = str;
            this.disableLiveness = z;
            this.defaultCamera = str2;
            this.allowedStatusCodes = allowedStatusCodes;
            this.textConfigs = map;
            this.livenessParams = livenessParams;
            this.livenessHeaders = livenessHeaders;
            this.enableOverlay = z2;
            this.validateSignature = z3;
            this.enableLookStraight = z4;
            this.showEndState = str3;
            this.isSuccess = str4;
            this.zoomByDefault = z5;
        }
    }

    /* compiled from: WorkflowUIState.kt */
    @Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002Bá\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e\u0012$\b\u0002\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0011j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\u0012\u0012\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000b\u0012(\b\u0002\u0010\u0015\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f\u0018\u0001`\u0012\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u001aJ\t\u00108\u001a\u00020\u0004HÆ\u0003J\u0011\u00109\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000bHÆ\u0003J)\u0010:\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f\u0018\u0001`\u0012HÆ\u0003J\t\u0010;\u001a\u00020\u0017HÆ\u0003J\t\u0010<\u001a\u00020\u0004HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010>\u001a\u00020\u0004HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010@\u001a\u00020\u0004HÆ\u0003J\t\u0010A\u001a\u00020\u0004HÆ\u0003J\t\u0010B\u001a\u00020\u0004HÆ\u0003J\u000f\u0010C\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J\u0017\u0010D\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eHÆ\u0003J%\u0010E\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0011j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\u0012HÆ\u0003Jñ\u0001\u0010F\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2$\b\u0002\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0011j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\u00122\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000b2(\b\u0002\u0010\u0015\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f\u0018\u0001`\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00042\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010G\u001a\u00020\u00172\b\u0010H\u001a\u0004\u0018\u00010\u000fHÖ\u0003J#\u0010!\u001a\u0004\u0018\u00010I2\u0019\u0010J\u001a\u0015\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000f0K¢\u0006\u0002\bLJ\t\u0010M\u001a\u00020\fHÖ\u0001J\t\u0010N\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR-\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0011j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001fR\u0011\u0010\b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR1\u0010\u0015\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f\u0018\u0001`\u0012¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u001a\u0010\"\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0019\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001cR\u001a\u0010)\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010%\"\u0004\b+\u0010'R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001fR\u0011\u0010\u0018\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001fR\u0016\u0010\u0005\u001a\u00020\u00048\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001fR\u0016\u0010\u0003\u001a\u00020\u00048\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001fR-\u00100\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0011j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\u0012¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001eR\u001f\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001fR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u001fR\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b6\u00107¨\u0006O"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$ApiCall;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$Child;", "tag", "", WebViewFragment.ARG_SUB_TYPE, "uiStyle", "url", "method", "requestType", "allowedStatusCodes", "", "", "textConfigs", "", "", "headers", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "requestParams", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$RequestParam;", "requestBody", "validateSignature", "", "showEndState", "isSuccess", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/Map;Ljava/util/HashMap;Ljava/util/List;Ljava/util/HashMap;ZLjava/lang/String;Ljava/lang/String;)V", "getAllowedStatusCodes", "()Ljava/util/List;", "getHeaders", "()Ljava/util/HashMap;", "()Ljava/lang/String;", "getMethod", "getRequestBody", "requestBodyJSON", "Lorg/json/JSONObject;", "getRequestBodyJSON", "()Lorg/json/JSONObject;", "setRequestBodyJSON", "(Lorg/json/JSONObject;)V", "getRequestParams", "requestQueryJSON", "getRequestQueryJSON", "setRequestQueryJSON", "getRequestType", "getShowEndState", "getSubType", "getTag", "tagFileUriMap", "getTagFileUriMap", "getTextConfigs", "()Ljava/util/Map;", "getUiStyle", "getUrl", "getValidateSignature", "()Z", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "Lokhttp3/RequestBody;", "injector", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class ApiCall extends WorkflowUIState implements Child {
        private final List<Integer> allowedStatusCodes;
        private final HashMap<String, String> headers;
        private final String isSuccess;
        private final String method;
        private final HashMap<String, Object> requestBody;
        private JSONObject requestBodyJSON;
        private final List<WorkflowModule.Properties.RequestParam> requestParams;
        private JSONObject requestQueryJSON;
        private final String requestType;
        private final String showEndState;

        @SerializedName("module_subType")
        private final String subType;

        @SerializedName("module_id")
        private final String tag;
        private final HashMap<String, String> tagFileUriMap;
        private final Map<String, Object> textConfigs;
        private final String uiStyle;
        private final String url;
        private final boolean validateSignature;

        public final String component1() {
            return getTag();
        }

        public final List<WorkflowModule.Properties.RequestParam> component10() {
            return this.requestParams;
        }

        public final HashMap<String, Object> component11() {
            return this.requestBody;
        }

        /* renamed from: component12, reason: from getter */
        public final boolean getValidateSignature() {
            return this.validateSignature;
        }

        /* renamed from: component13, reason: from getter */
        public final String getShowEndState() {
            return this.showEndState;
        }

        /* renamed from: component14, reason: from getter */
        public final String getIsSuccess() {
            return this.isSuccess;
        }

        public final String component2() {
            return getSubType();
        }

        public final String component3() {
            return getUiStyle();
        }

        /* renamed from: component4, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* renamed from: component5, reason: from getter */
        public final String getMethod() {
            return this.method;
        }

        /* renamed from: component6, reason: from getter */
        public final String getRequestType() {
            return this.requestType;
        }

        public final List<Integer> component7() {
            return this.allowedStatusCodes;
        }

        public final Map<String, Object> component8() {
            return this.textConfigs;
        }

        public final HashMap<String, String> component9() {
            return this.headers;
        }

        public final ApiCall copy(String tag, String subType, String uiStyle, String url, String method, String requestType, List<Integer> allowedStatusCodes, Map<String, ? extends Object> textConfigs, HashMap<String, String> headers, List<WorkflowModule.Properties.RequestParam> requestParams, HashMap<String, Object> requestBody, boolean validateSignature, String showEndState, String isSuccess) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(subType, "subType");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(method, "method");
            Intrinsics.checkNotNullParameter(requestType, "requestType");
            Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
            Intrinsics.checkNotNullParameter(headers, "headers");
            Intrinsics.checkNotNullParameter(showEndState, "showEndState");
            return new ApiCall(tag, subType, uiStyle, url, method, requestType, allowedStatusCodes, textConfigs, headers, requestParams, requestBody, validateSignature, showEndState, isSuccess);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ApiCall)) {
                return false;
            }
            ApiCall apiCall = (ApiCall) other;
            return Intrinsics.areEqual(getTag(), apiCall.getTag()) && Intrinsics.areEqual(getSubType(), apiCall.getSubType()) && Intrinsics.areEqual(getUiStyle(), apiCall.getUiStyle()) && Intrinsics.areEqual(this.url, apiCall.url) && Intrinsics.areEqual(this.method, apiCall.method) && Intrinsics.areEqual(this.requestType, apiCall.requestType) && Intrinsics.areEqual(this.allowedStatusCodes, apiCall.allowedStatusCodes) && Intrinsics.areEqual(this.textConfigs, apiCall.textConfigs) && Intrinsics.areEqual(this.headers, apiCall.headers) && Intrinsics.areEqual(this.requestParams, apiCall.requestParams) && Intrinsics.areEqual(this.requestBody, apiCall.requestBody) && this.validateSignature == apiCall.validateSignature && Intrinsics.areEqual(this.showEndState, apiCall.showEndState) && Intrinsics.areEqual(this.isSuccess, apiCall.isSuccess);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = ((((((((((((getTag().hashCode() * 31) + getSubType().hashCode()) * 31) + (getUiStyle() == null ? 0 : getUiStyle().hashCode())) * 31) + this.url.hashCode()) * 31) + this.method.hashCode()) * 31) + this.requestType.hashCode()) * 31) + this.allowedStatusCodes.hashCode()) * 31;
            Map<String, Object> map = this.textConfigs;
            int hashCode2 = (((hashCode + (map == null ? 0 : map.hashCode())) * 31) + this.headers.hashCode()) * 31;
            List<WorkflowModule.Properties.RequestParam> list = this.requestParams;
            int hashCode3 = (hashCode2 + (list == null ? 0 : list.hashCode())) * 31;
            HashMap<String, Object> hashMap = this.requestBody;
            int hashCode4 = (hashCode3 + (hashMap == null ? 0 : hashMap.hashCode())) * 31;
            boolean z = this.validateSignature;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int hashCode5 = (((hashCode4 + i) * 31) + this.showEndState.hashCode()) * 31;
            String str = this.isSuccess;
            return hashCode5 + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "ApiCall(tag=" + getTag() + ", subType=" + getSubType() + ", uiStyle=" + getUiStyle() + ", url=" + this.url + ", method=" + this.method + ", requestType=" + this.requestType + ", allowedStatusCodes=" + this.allowedStatusCodes + ", textConfigs=" + this.textConfigs + ", headers=" + this.headers + ", requestParams=" + this.requestParams + ", requestBody=" + this.requestBody + ", validateSignature=" + this.validateSignature + ", showEndState=" + this.showEndState + ", isSuccess=" + this.isSuccess + ')';
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState.Child
        public boolean isChild() {
            return Child.DefaultImpls.isChild(this);
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getTag() {
            return this.tag;
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getSubType() {
            return this.subType;
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState.Child
        public String getUiStyle() {
            return this.uiStyle;
        }

        public final String getUrl() {
            return this.url;
        }

        public final String getMethod() {
            return this.method;
        }

        public final String getRequestType() {
            return this.requestType;
        }

        public final List<Integer> getAllowedStatusCodes() {
            return this.allowedStatusCodes;
        }

        public /* synthetic */ ApiCall(String str, String str2, String str3, String str4, String str5, String str6, List list, Map map, HashMap hashMap, List list2, HashMap hashMap2, boolean z, String str7, String str8, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i & 4) != 0 ? null : str3, str4, str5, str6, list, (i & 128) != 0 ? MapsKt.emptyMap() : map, (i & 256) != 0 ? new HashMap() : hashMap, (i & 512) != 0 ? null : list2, (i & 1024) != 0 ? null : hashMap2, (i & 2048) != 0 ? false : z, (i & 4096) != 0 ? "no" : str7, (i & 8192) != 0 ? null : str8);
        }

        public final Map<String, Object> getTextConfigs() {
            return this.textConfigs;
        }

        public final HashMap<String, String> getHeaders() {
            return this.headers;
        }

        public final List<WorkflowModule.Properties.RequestParam> getRequestParams() {
            return this.requestParams;
        }

        public final HashMap<String, Object> getRequestBody() {
            return this.requestBody;
        }

        public final boolean getValidateSignature() {
            return this.validateSignature;
        }

        public final String getShowEndState() {
            return this.showEndState;
        }

        public final String isSuccess() {
            return this.isSuccess;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ApiCall(String tag, String subType, String str, String url, String method, String requestType, List<Integer> allowedStatusCodes, Map<String, ? extends Object> map, HashMap<String, String> headers, List<WorkflowModule.Properties.RequestParam> list, HashMap<String, Object> hashMap, boolean z, String showEndState, String str2) {
            super(null);
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(subType, "subType");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(method, "method");
            Intrinsics.checkNotNullParameter(requestType, "requestType");
            Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
            Intrinsics.checkNotNullParameter(headers, "headers");
            Intrinsics.checkNotNullParameter(showEndState, "showEndState");
            this.tag = tag;
            this.subType = subType;
            this.uiStyle = str;
            this.url = url;
            this.method = method;
            this.requestType = requestType;
            this.allowedStatusCodes = allowedStatusCodes;
            this.textConfigs = map;
            this.headers = headers;
            this.requestParams = list;
            this.requestBody = hashMap;
            this.validateSignature = z;
            this.showEndState = showEndState;
            this.isSuccess = str2;
            this.requestQueryJSON = new JSONObject();
            this.requestBodyJSON = new JSONObject();
            this.tagFileUriMap = new HashMap<>();
        }

        public final JSONObject getRequestQueryJSON() {
            return this.requestQueryJSON;
        }

        public final void setRequestQueryJSON(JSONObject jSONObject) {
            Intrinsics.checkNotNullParameter(jSONObject, "<set-?>");
            this.requestQueryJSON = jSONObject;
        }

        public final JSONObject getRequestBodyJSON() {
            return this.requestBodyJSON;
        }

        public final void setRequestBodyJSON(JSONObject jSONObject) {
            Intrinsics.checkNotNullParameter(jSONObject, "<set-?>");
            this.requestBodyJSON = jSONObject;
        }

        public final HashMap<String, String> getTagFileUriMap() {
            return this.tagFileUriMap;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:13:0x0071. Please report as an issue. */
        public final RequestBody getRequestBody(Function1<? super String, ? extends Object> injector) {
            String str;
            String str2;
            Object put;
            Intrinsics.checkNotNullParameter(injector, "injector");
            String str3 = null;
            if (Intrinsics.areEqual(this.method, WorkflowRequestType.Method.GET)) {
                return null;
            }
            if (!Intrinsics.areEqual(this.requestType, WorkflowRequestType.MULTIPART)) {
                if (!Intrinsics.areEqual(this.requestType, WorkflowRequestType.JSON)) {
                    throw new NotImplementedError("An operation is not implemented: " + (this.requestType + " not supported yet"));
                }
                this.headers.put("Content-Type", "application/json");
                List<WorkflowModule.Properties.RequestParam> list = this.requestParams;
                if (list == null || list.isEmpty()) {
                    HashMap<String, Object> hashMap = this.requestBody;
                    if (hashMap == null || hashMap.isEmpty()) {
                        str = "";
                    } else {
                        Object requestBody$anyInject = getRequestBody$anyInject(this.requestBody, injector);
                        Intrinsics.checkNotNull(requestBody$anyInject, "null cannot be cast to non-null type java.util.HashMap<kotlin.String, kotlin.Any>{ kotlin.collections.TypeAliasesKt.HashMap<kotlin.String, kotlin.Any> }");
                        str = new JSONObject(MapsKt.toMap((HashMap) requestBody$anyInject)).toString();
                        Intrinsics.checkNotNullExpressionValue(str, "JSONObject(finalReqBody).toString()");
                        this.requestBodyJSON = new JSONObject(str);
                    }
                } else {
                    JSONObject jSONObject = new JSONObject();
                    for (WorkflowModule.Properties.RequestParam requestParam : this.requestParams) {
                        jSONObject.put(requestParam.getName(), requestParam.getValue());
                    }
                    str = jSONObject.toString();
                    Intrinsics.checkNotNullExpressionValue(str, "JSONObject().apply {\n   …             }.toString()");
                    this.requestQueryJSON = new JSONObject(str);
                }
                return RequestBody.INSTANCE.create(str, MediaType.INSTANCE.get("application/json"));
            }
            this.headers.put("Content-Type", ShareTarget.ENCODING_TYPE_MULTIPART);
            MultipartBody.Builder type = new MultipartBody.Builder(null, 1, null).setType(MultipartBody.FORM);
            List<WorkflowModule.Properties.RequestParam> list2 = this.requestParams;
            if (list2 != null) {
                List<WorkflowModule.Properties.RequestParam> list3 = list2;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
                for (WorkflowModule.Properties.RequestParam requestParam2 : list3) {
                    String name = requestParam2.getName();
                    String value = requestParam2.getValue();
                    String type2 = requestParam2.getType();
                    if (type2 != null) {
                        int i = 2;
                        switch (type2.hashCode()) {
                            case -891985903:
                                str2 = str3;
                                if (!type2.equals("string")) {
                                    break;
                                } else {
                                    type.addFormDataPart(name, value);
                                    put = this.requestQueryJSON.put(name, value);
                                    arrayList.add(put);
                                    str3 = str2;
                                }
                            case 110834:
                                if (!type2.equals("pdf")) {
                                    break;
                                }
                                this.tagFileUriMap.put(name, value);
                                File file = new File(value);
                                str2 = null;
                                String substringAfterLast$default = StringsKt.substringAfterLast$default(value, RemoteSettings.FORWARD_SLASH_STRING, (String) null, 2, (Object) null);
                                RequestBody.Companion companion = RequestBody.INSTANCE;
                                MediaType.Companion companion2 = MediaType.INSTANCE;
                                String mimeType = FileExtsKt.mimeType(file);
                                Intrinsics.checkNotNull(mimeType);
                                put = type.addFormDataPart(name, substringAfterLast$default, companion.create(file, companion2.get(mimeType)));
                                arrayList.add(put);
                                str3 = str2;
                            case 97434231:
                                if (!type2.equals("files")) {
                                    break;
                                } else {
                                    int i2 = 0;
                                    for (Object obj : StringsKt.split$default((CharSequence) value, new String[]{","}, false, 0, 6, (Object) null)) {
                                        int i3 = i2 + 1;
                                        if (i2 < 0) {
                                            CollectionsKt.throwIndexOverflow();
                                        }
                                        String str4 = (String) obj;
                                        File file2 = new File(str4);
                                        String str5 = name + '_' + i2;
                                        String substringAfterLast$default2 = StringsKt.substringAfterLast$default(str4, RemoteSettings.FORWARD_SLASH_STRING, (String) null, i, (Object) null);
                                        this.tagFileUriMap.put(str5, str4);
                                        RequestBody.Companion companion3 = RequestBody.INSTANCE;
                                        MediaType.Companion companion4 = MediaType.INSTANCE;
                                        String mimeType2 = FileExtsKt.mimeType(file2);
                                        Intrinsics.checkNotNull(mimeType2);
                                        type.addFormDataPart(str5, substringAfterLast$default2, companion3.create(file2, companion4.get(mimeType2)));
                                        i2 = i3;
                                        i = 2;
                                    }
                                    put = Unit.INSTANCE;
                                    str2 = null;
                                    arrayList.add(put);
                                    str3 = str2;
                                }
                            case 100313435:
                                if (!type2.equals("image")) {
                                    break;
                                }
                                this.tagFileUriMap.put(name, value);
                                File file3 = new File(value);
                                str2 = null;
                                String substringAfterLast$default3 = StringsKt.substringAfterLast$default(value, RemoteSettings.FORWARD_SLASH_STRING, (String) null, 2, (Object) null);
                                RequestBody.Companion companion5 = RequestBody.INSTANCE;
                                MediaType.Companion companion22 = MediaType.INSTANCE;
                                String mimeType3 = FileExtsKt.mimeType(file3);
                                Intrinsics.checkNotNull(mimeType3);
                                put = type.addFormDataPart(name, substringAfterLast$default3, companion5.create(file3, companion22.get(mimeType3)));
                                arrayList.add(put);
                                str3 = str2;
                            case 112202875:
                                if (!type2.equals("video")) {
                                    break;
                                } else {
                                    this.tagFileUriMap.put(name, value);
                                    put = type.addFormDataPart(name, StringsKt.substringAfterLast$default(value, RemoteSettings.FORWARD_SLASH_STRING, str3, 2, str3), RequestBody.INSTANCE.create(new File(value), MediaType.INSTANCE.get(ShareTarget.ENCODING_TYPE_MULTIPART)));
                                    str2 = str3;
                                    arrayList.add(put);
                                    str3 = str2;
                                }
                        }
                    }
                    throw new NotImplementedError("An operation is not implemented: " + ("unknown request param type " + type2));
                }
            }
            return type.build();
        }

        private static final Object getRequestBody$anyInject(Object obj, Function1<? super String, ? extends Object> function1) {
            if (obj instanceof String) {
                return function1.invoke(obj);
            }
            if (!(obj instanceof Map)) {
                if (!(obj instanceof List)) {
                    if ((obj instanceof Number) || (obj instanceof Boolean)) {
                        return obj;
                    }
                    throw new NotImplementedError("An operation is not implemented: " + (obj.getClass() + " not supported"));
                }
                ArrayList arrayList = new ArrayList();
                Iterator it = ((Iterable) obj).iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    Object requestBody$anyInject = next != null ? getRequestBody$anyInject(next, function1) : null;
                    if (requestBody$anyInject != null) {
                        arrayList.add(requestBody$anyInject);
                    }
                }
                return arrayList;
            }
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any>");
            Map map = (Map) obj;
            LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
            for (Map.Entry entry : map.entrySet()) {
                linkedHashMap.put(entry.getKey(), getRequestBody$anyInject(entry.getValue(), function1));
            }
            return linkedHashMap;
        }
    }

    /* compiled from: WorkflowUIState.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002Ba\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e\u0012\u0006\u0010\u0010\u001a\u00020\f¢\u0006\u0002\u0010\u0011J\t\u0010\u001e\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0004HÆ\u0003J\t\u0010 \u001a\u00020\u0004HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0011\u0010\"\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003J\t\u0010#\u001a\u00020\fHÆ\u0003J\u0017\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eHÆ\u0003J\t\u0010%\u001a\u00020\fHÆ\u0003Jq\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\fHÆ\u0001J\u0013\u0010'\u001a\u00020\f2\b\u0010(\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020\u0004HÖ\u0001R\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0010\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0006\u001a\u00020\u00048\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0003\u001a\u00020\u00048\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u001f\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015¨\u0006,"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$Form;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$Child;", "tag", "", "type", WebViewFragment.ARG_SUB_TYPE, "uiStyle", FormFragment.ARG_SECTIONS, "", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section;", "useWebForm", "", "textConfigs", "", "", "showBackButton", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZLjava/util/Map;Z)V", "getSections", "()Ljava/util/List;", "getShowBackButton", "()Z", "getSubType", "()Ljava/lang/String;", "getTag", "getTextConfigs", "()Ljava/util/Map;", "getType", "getUiStyle", "getUseWebForm", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class Form extends WorkflowUIState implements Child {
        private final List<WorkflowModule.Properties.Section> sections;
        private final boolean showBackButton;

        @SerializedName("module_subType")
        private final String subType;

        @SerializedName("module_id")
        private final String tag;
        private final Map<String, Object> textConfigs;
        private final String type;
        private final String uiStyle;
        private final boolean useWebForm;

        public final String component1() {
            return getTag();
        }

        /* renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        public final String component3() {
            return getSubType();
        }

        public final String component4() {
            return getUiStyle();
        }

        public final List<WorkflowModule.Properties.Section> component5() {
            return this.sections;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getUseWebForm() {
            return this.useWebForm;
        }

        public final Map<String, Object> component7() {
            return this.textConfigs;
        }

        /* renamed from: component8, reason: from getter */
        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        public final Form copy(String tag, String type, String subType, String uiStyle, List<WorkflowModule.Properties.Section> sections, boolean useWebForm, Map<String, ? extends Object> textConfigs, boolean showBackButton) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(subType, "subType");
            return new Form(tag, type, subType, uiStyle, sections, useWebForm, textConfigs, showBackButton);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Form)) {
                return false;
            }
            Form form = (Form) other;
            return Intrinsics.areEqual(getTag(), form.getTag()) && Intrinsics.areEqual(this.type, form.type) && Intrinsics.areEqual(getSubType(), form.getSubType()) && Intrinsics.areEqual(getUiStyle(), form.getUiStyle()) && Intrinsics.areEqual(this.sections, form.sections) && this.useWebForm == form.useWebForm && Intrinsics.areEqual(this.textConfigs, form.textConfigs) && this.showBackButton == form.showBackButton;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = ((((((getTag().hashCode() * 31) + this.type.hashCode()) * 31) + getSubType().hashCode()) * 31) + (getUiStyle() == null ? 0 : getUiStyle().hashCode())) * 31;
            List<WorkflowModule.Properties.Section> list = this.sections;
            int hashCode2 = (hashCode + (list == null ? 0 : list.hashCode())) * 31;
            boolean z = this.useWebForm;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (hashCode2 + i) * 31;
            Map<String, Object> map = this.textConfigs;
            int hashCode3 = (i2 + (map != null ? map.hashCode() : 0)) * 31;
            boolean z2 = this.showBackButton;
            return hashCode3 + (z2 ? 1 : z2 ? 1 : 0);
        }

        public String toString() {
            return "Form(tag=" + getTag() + ", type=" + this.type + ", subType=" + getSubType() + ", uiStyle=" + getUiStyle() + ", sections=" + this.sections + ", useWebForm=" + this.useWebForm + ", textConfigs=" + this.textConfigs + ", showBackButton=" + this.showBackButton + ')';
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState.Child
        public boolean isChild() {
            return Child.DefaultImpls.isChild(this);
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getTag() {
            return this.tag;
        }

        public final String getType() {
            return this.type;
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getSubType() {
            return this.subType;
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState.Child
        public String getUiStyle() {
            return this.uiStyle;
        }

        public final List<WorkflowModule.Properties.Section> getSections() {
            return this.sections;
        }

        public final boolean getUseWebForm() {
            return this.useWebForm;
        }

        public /* synthetic */ Form(String str, String str2, String str3, String str4, List list, boolean z, Map map, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, (i & 8) != 0 ? null : str4, list, z, (i & 64) != 0 ? MapsKt.emptyMap() : map, z2);
        }

        public final Map<String, Object> getTextConfigs() {
            return this.textConfigs;
        }

        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Form(String tag, String type, String subType, String str, List<WorkflowModule.Properties.Section> list, boolean z, Map<String, ? extends Object> map, boolean z2) {
            super(null);
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(subType, "subType");
            this.tag = tag;
            this.type = type;
            this.subType = subType;
            this.uiStyle = str;
            this.sections = list;
            this.useWebForm = z;
            this.textConfigs = map;
            this.showBackButton = z2;
        }
    }

    /* compiled from: WorkflowUIState.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r¢\u0006\u0002\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010 \u001a\u00020\tHÆ\u0003J\t\u0010!\u001a\u00020\tHÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\u0017\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÆ\u0003Ji\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÆ\u0001J\u0013\u0010%\u001a\u00020\t2\b\u0010&\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u001f\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017¨\u0006*"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$WebView;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "tag", "", WebViewFragment.ARG_SUB_TYPE, "url", "data", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Data;", "showBackButton", "", WebViewFragment.ARG_RELOAD_ON_REDIRECT_FAILURE, "openInAppBrowser", "textConfigs", "", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Data;ZZZLjava/util/Map;)V", "getData", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Data;", "getOpenInAppBrowser", "()Z", "getReloadOnRedirectFailure", "getShowBackButton", "getSubType", "()Ljava/lang/String;", "getTag", "getTextConfigs", "()Ljava/util/Map;", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class WebView extends WorkflowUIState {
        private final WorkflowModule.Properties.Data data;
        private final boolean openInAppBrowser;
        private final boolean reloadOnRedirectFailure;
        private final boolean showBackButton;

        @SerializedName("module_subType")
        private final String subType;

        @SerializedName("module_id")
        private final String tag;
        private final Map<String, Object> textConfigs;
        private final String url;

        public final String component1() {
            return getTag();
        }

        public final String component2() {
            return getSubType();
        }

        /* renamed from: component3, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* renamed from: component4, reason: from getter */
        public final WorkflowModule.Properties.Data getData() {
            return this.data;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getReloadOnRedirectFailure() {
            return this.reloadOnRedirectFailure;
        }

        /* renamed from: component7, reason: from getter */
        public final boolean getOpenInAppBrowser() {
            return this.openInAppBrowser;
        }

        public final Map<String, Object> component8() {
            return this.textConfigs;
        }

        public final WebView copy(String tag, String subType, String url, WorkflowModule.Properties.Data data, boolean showBackButton, boolean reloadOnRedirectFailure, boolean openInAppBrowser, Map<String, ? extends Object> textConfigs) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(subType, "subType");
            Intrinsics.checkNotNullParameter(url, "url");
            return new WebView(tag, subType, url, data, showBackButton, reloadOnRedirectFailure, openInAppBrowser, textConfigs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WebView)) {
                return false;
            }
            WebView webView = (WebView) other;
            return Intrinsics.areEqual(getTag(), webView.getTag()) && Intrinsics.areEqual(getSubType(), webView.getSubType()) && Intrinsics.areEqual(this.url, webView.url) && Intrinsics.areEqual(this.data, webView.data) && this.showBackButton == webView.showBackButton && this.reloadOnRedirectFailure == webView.reloadOnRedirectFailure && this.openInAppBrowser == webView.openInAppBrowser && Intrinsics.areEqual(this.textConfigs, webView.textConfigs);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = ((((getTag().hashCode() * 31) + getSubType().hashCode()) * 31) + this.url.hashCode()) * 31;
            WorkflowModule.Properties.Data data = this.data;
            int hashCode2 = (hashCode + (data == null ? 0 : data.hashCode())) * 31;
            boolean z = this.showBackButton;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (hashCode2 + i) * 31;
            boolean z2 = this.reloadOnRedirectFailure;
            int i3 = z2;
            if (z2 != 0) {
                i3 = 1;
            }
            int i4 = (i2 + i3) * 31;
            boolean z3 = this.openInAppBrowser;
            int i5 = (i4 + (z3 ? 1 : z3 ? 1 : 0)) * 31;
            Map<String, Object> map = this.textConfigs;
            return i5 + (map != null ? map.hashCode() : 0);
        }

        public String toString() {
            return "WebView(tag=" + getTag() + ", subType=" + getSubType() + ", url=" + this.url + ", data=" + this.data + ", showBackButton=" + this.showBackButton + ", reloadOnRedirectFailure=" + this.reloadOnRedirectFailure + ", openInAppBrowser=" + this.openInAppBrowser + ", textConfigs=" + this.textConfigs + ')';
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getTag() {
            return this.tag;
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getSubType() {
            return this.subType;
        }

        public final String getUrl() {
            return this.url;
        }

        public final WorkflowModule.Properties.Data getData() {
            return this.data;
        }

        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        public final boolean getReloadOnRedirectFailure() {
            return this.reloadOnRedirectFailure;
        }

        public final boolean getOpenInAppBrowser() {
            return this.openInAppBrowser;
        }

        public /* synthetic */ WebView(String str, String str2, String str3, WorkflowModule.Properties.Data data, boolean z, boolean z2, boolean z3, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, data, z, z2, z3, (i & 128) != 0 ? MapsKt.emptyMap() : map);
        }

        public final Map<String, Object> getTextConfigs() {
            return this.textConfigs;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WebView(String tag, String subType, String url, WorkflowModule.Properties.Data data, boolean z, boolean z2, boolean z3, Map<String, ? extends Object> map) {
            super(null);
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(subType, "subType");
            Intrinsics.checkNotNullParameter(url, "url");
            this.tag = tag;
            this.subType = subType;
            this.url = url;
            this.data = data;
            this.showBackButton = z;
            this.reloadOnRedirectFailure = z2;
            this.openInAppBrowser = z3;
            this.textConfigs = map;
        }
    }

    /* compiled from: WorkflowUIState.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u0017\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J5\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$BarcodeCapture;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "tag", "", "qrConfig", "Lco/hyperverge/hvqrmodule/objects/HVQRConfig;", "textConfigs", "", "", "(Ljava/lang/String;Lco/hyperverge/hvqrmodule/objects/HVQRConfig;Ljava/util/Map;)V", "getQrConfig", "()Lco/hyperverge/hvqrmodule/objects/HVQRConfig;", "setQrConfig", "(Lco/hyperverge/hvqrmodule/objects/HVQRConfig;)V", "getTag", "()Ljava/lang/String;", "getTextConfigs", "()Ljava/util/Map;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class BarcodeCapture extends WorkflowUIState {
        private HVQRConfig qrConfig;

        @SerializedName("module_id")
        private final String tag;
        private final Map<String, Object> textConfigs;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BarcodeCapture copy$default(BarcodeCapture barcodeCapture, String str, HVQRConfig hVQRConfig, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = barcodeCapture.getTag();
            }
            if ((i & 2) != 0) {
                hVQRConfig = barcodeCapture.qrConfig;
            }
            if ((i & 4) != 0) {
                map = barcodeCapture.textConfigs;
            }
            return barcodeCapture.copy(str, hVQRConfig, map);
        }

        public final String component1() {
            return getTag();
        }

        /* renamed from: component2, reason: from getter */
        public final HVQRConfig getQrConfig() {
            return this.qrConfig;
        }

        public final Map<String, Object> component3() {
            return this.textConfigs;
        }

        public final BarcodeCapture copy(String tag, HVQRConfig qrConfig, Map<String, ? extends Object> textConfigs) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(qrConfig, "qrConfig");
            return new BarcodeCapture(tag, qrConfig, textConfigs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BarcodeCapture)) {
                return false;
            }
            BarcodeCapture barcodeCapture = (BarcodeCapture) other;
            return Intrinsics.areEqual(getTag(), barcodeCapture.getTag()) && Intrinsics.areEqual(this.qrConfig, barcodeCapture.qrConfig) && Intrinsics.areEqual(this.textConfigs, barcodeCapture.textConfigs);
        }

        public int hashCode() {
            int hashCode = ((getTag().hashCode() * 31) + this.qrConfig.hashCode()) * 31;
            Map<String, Object> map = this.textConfigs;
            return hashCode + (map == null ? 0 : map.hashCode());
        }

        public String toString() {
            return "BarcodeCapture(tag=" + getTag() + ", qrConfig=" + this.qrConfig + ", textConfigs=" + this.textConfigs + ')';
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getTag() {
            return this.tag;
        }

        public final HVQRConfig getQrConfig() {
            return this.qrConfig;
        }

        public final void setQrConfig(HVQRConfig hVQRConfig) {
            Intrinsics.checkNotNullParameter(hVQRConfig, "<set-?>");
            this.qrConfig = hVQRConfig;
        }

        public /* synthetic */ BarcodeCapture(String str, HVQRConfig hVQRConfig, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, hVQRConfig, (i & 4) != 0 ? MapsKt.emptyMap() : map);
        }

        public final Map<String, Object> getTextConfigs() {
            return this.textConfigs;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BarcodeCapture(String tag, HVQRConfig qrConfig, Map<String, ? extends Object> map) {
            super(null);
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(qrConfig, "qrConfig");
            this.tag = tag;
            this.qrConfig = qrConfig;
            this.textConfigs = map;
        }
    }

    /* compiled from: WorkflowUIState.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002Bn\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\u001b\b\u0002\u0010\u000f\u001a\u0015\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0010\u0018\u00010\n¢\u0006\u0002\b\u0011¢\u0006\u0002\u0010\u0012J\t\u0010!\u001a\u00020\u0004HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\t\u0010#\u001a\u00020\u0006HÆ\u0003J\t\u0010$\u001a\u00020\u0006HÆ\u0003J\u0017\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\nHÆ\u0003J\u0010\u0010&\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u0016J\t\u0010'\u001a\u00020\u000eHÆ\u0003J\u001c\u0010(\u001a\u0015\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0010\u0018\u00010\n¢\u0006\u0002\b\u0011HÆ\u0003J\u0081\u0001\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u001b\b\u0002\u0010\u000f\u001a\u0015\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0010\u0018\u00010\n¢\u0006\u0002\b\u0011HÆ\u0001¢\u0006\u0002\u0010*J\t\u0010+\u001a\u00020\u000eHÖ\u0001J\u0013\u0010,\u001a\u00020\u00062\b\u0010-\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010.\u001a\u00020\u000eHÖ\u0001J\t\u0010/\u001a\u00020\u0004HÖ\u0001J\u0019\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u000eHÖ\u0001R\u001f\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0016\u0010\u0003\u001a\u00020\u00048\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR$\u0010\u000f\u001a\u0015\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0010\u0018\u00010\n¢\u0006\u0002\b\u0011¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014¨\u00065"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$NFCReader;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "Landroid/os/Parcelable;", "tag", "", "showInstruction", "", "showSkipButton", "showBackButton", "authentication", "", "nfcSkipDelay", "", "retryOnNFCError", "", "textConfigs", "", "Lkotlinx/parcelize/RawValue;", "(Ljava/lang/String;ZZZLjava/util/Map;Ljava/lang/Long;ILjava/util/Map;)V", "getAuthentication", "()Ljava/util/Map;", "getNfcSkipDelay", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getRetryOnNFCError", "()I", "getShowBackButton", "()Z", "getShowInstruction", "getShowSkipButton", "getTag", "()Ljava/lang/String;", "getTextConfigs", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;ZZZLjava/util/Map;Ljava/lang/Long;ILjava/util/Map;)Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$NFCReader;", "describeContents", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class NFCReader extends WorkflowUIState implements Parcelable {
        public static final Parcelable.Creator<NFCReader> CREATOR = new Creator();
        private final Map<String, String> authentication;
        private final Long nfcSkipDelay;
        private final int retryOnNFCError;
        private final boolean showBackButton;
        private final boolean showInstruction;
        private final boolean showSkipButton;

        @SerializedName("module_id")
        private final String tag;
        private final Map<String, Object> textConfigs;

        /* compiled from: WorkflowUIState.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<NFCReader> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final NFCReader createFromParcel(Parcel parcel) {
                LinkedHashMap linkedHashMap;
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String readString = parcel.readString();
                boolean z = parcel.readInt() != 0;
                boolean z2 = parcel.readInt() != 0;
                boolean z3 = parcel.readInt() != 0;
                LinkedHashMap linkedHashMap2 = null;
                if (parcel.readInt() == 0) {
                    linkedHashMap = null;
                } else {
                    int readInt = parcel.readInt();
                    linkedHashMap = new LinkedHashMap(readInt);
                    for (int i = 0; i != readInt; i++) {
                        linkedHashMap.put(parcel.readString(), parcel.readString());
                    }
                }
                LinkedHashMap linkedHashMap3 = linkedHashMap;
                Long valueOf = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
                int readInt2 = parcel.readInt();
                if (parcel.readInt() != 0) {
                    int readInt3 = parcel.readInt();
                    linkedHashMap2 = new LinkedHashMap(readInt3);
                    for (int i2 = 0; i2 != readInt3; i2++) {
                        linkedHashMap2.put(parcel.readString(), parcel.readValue(NFCReader.class.getClassLoader()));
                    }
                }
                return new NFCReader(readString, z, z2, z3, linkedHashMap3, valueOf, readInt2, linkedHashMap2);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final NFCReader[] newArray(int i) {
                return new NFCReader[i];
            }
        }

        public final String component1() {
            return getTag();
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getShowInstruction() {
            return this.showInstruction;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getShowSkipButton() {
            return this.showSkipButton;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        public final Map<String, String> component5() {
            return this.authentication;
        }

        /* renamed from: component6, reason: from getter */
        public final Long getNfcSkipDelay() {
            return this.nfcSkipDelay;
        }

        /* renamed from: component7, reason: from getter */
        public final int getRetryOnNFCError() {
            return this.retryOnNFCError;
        }

        public final Map<String, Object> component8() {
            return this.textConfigs;
        }

        public final NFCReader copy(String tag, boolean showInstruction, boolean showSkipButton, boolean showBackButton, Map<String, String> authentication, Long nfcSkipDelay, int retryOnNFCError, Map<String, ? extends Object> textConfigs) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            return new NFCReader(tag, showInstruction, showSkipButton, showBackButton, authentication, nfcSkipDelay, retryOnNFCError, textConfigs);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NFCReader)) {
                return false;
            }
            NFCReader nFCReader = (NFCReader) other;
            return Intrinsics.areEqual(getTag(), nFCReader.getTag()) && this.showInstruction == nFCReader.showInstruction && this.showSkipButton == nFCReader.showSkipButton && this.showBackButton == nFCReader.showBackButton && Intrinsics.areEqual(this.authentication, nFCReader.authentication) && Intrinsics.areEqual(this.nfcSkipDelay, nFCReader.nfcSkipDelay) && this.retryOnNFCError == nFCReader.retryOnNFCError && Intrinsics.areEqual(this.textConfigs, nFCReader.textConfigs);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = getTag().hashCode() * 31;
            boolean z = this.showInstruction;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (hashCode + i) * 31;
            boolean z2 = this.showSkipButton;
            int i3 = z2;
            if (z2 != 0) {
                i3 = 1;
            }
            int i4 = (i2 + i3) * 31;
            boolean z3 = this.showBackButton;
            int i5 = (i4 + (z3 ? 1 : z3 ? 1 : 0)) * 31;
            Map<String, String> map = this.authentication;
            int hashCode2 = (i5 + (map == null ? 0 : map.hashCode())) * 31;
            Long l = this.nfcSkipDelay;
            int hashCode3 = (((hashCode2 + (l == null ? 0 : l.hashCode())) * 31) + this.retryOnNFCError) * 31;
            Map<String, Object> map2 = this.textConfigs;
            return hashCode3 + (map2 != null ? map2.hashCode() : 0);
        }

        public String toString() {
            return "NFCReader(tag=" + getTag() + ", showInstruction=" + this.showInstruction + ", showSkipButton=" + this.showSkipButton + ", showBackButton=" + this.showBackButton + ", authentication=" + this.authentication + ", nfcSkipDelay=" + this.nfcSkipDelay + ", retryOnNFCError=" + this.retryOnNFCError + ", textConfigs=" + this.textConfigs + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.tag);
            parcel.writeInt(this.showInstruction ? 1 : 0);
            parcel.writeInt(this.showSkipButton ? 1 : 0);
            parcel.writeInt(this.showBackButton ? 1 : 0);
            Map<String, String> map = this.authentication;
            if (map == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcel.writeInt(map.size());
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    parcel.writeString(entry.getKey());
                    parcel.writeString(entry.getValue());
                }
            }
            Long l = this.nfcSkipDelay;
            if (l == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcel.writeLong(l.longValue());
            }
            parcel.writeInt(this.retryOnNFCError);
            Map<String, Object> map2 = this.textConfigs;
            if (map2 == null) {
                parcel.writeInt(0);
                return;
            }
            parcel.writeInt(1);
            parcel.writeInt(map2.size());
            for (Map.Entry<String, Object> entry2 : map2.entrySet()) {
                parcel.writeString(entry2.getKey());
                parcel.writeValue(entry2.getValue());
            }
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getTag() {
            return this.tag;
        }

        public final boolean getShowInstruction() {
            return this.showInstruction;
        }

        public final boolean getShowSkipButton() {
            return this.showSkipButton;
        }

        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        public /* synthetic */ NFCReader(String str, boolean z, boolean z2, boolean z3, Map map, Long l, int i, Map map2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, z, z2, z3, (i2 & 16) != 0 ? MapsKt.emptyMap() : map, l, (i2 & 64) != 0 ? 3 : i, (i2 & 128) != 0 ? MapsKt.emptyMap() : map2);
        }

        public final Map<String, String> getAuthentication() {
            return this.authentication;
        }

        public final Long getNfcSkipDelay() {
            return this.nfcSkipDelay;
        }

        public final int getRetryOnNFCError() {
            return this.retryOnNFCError;
        }

        public final Map<String, Object> getTextConfigs() {
            return this.textConfigs;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NFCReader(String tag, boolean z, boolean z2, boolean z3, Map<String, String> map, Long l, int i, Map<String, ? extends Object> map2) {
            super(null);
            Intrinsics.checkNotNullParameter(tag, "tag");
            this.tag = tag;
            this.showInstruction = z;
            this.showSkipButton = z2;
            this.showBackButton = z3;
            this.authentication = map;
            this.nfcSkipDelay = l;
            this.retryOnNFCError = i;
            this.textConfigs = map2;
        }
    }

    /* compiled from: WorkflowUIState.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$End;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "tag", "", "(Ljava/lang/String;)V", "getTag", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class End extends WorkflowUIState {

        @SerializedName("module_id")
        private final String tag;

        public static /* synthetic */ End copy$default(End end, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = end.getTag();
            }
            return end.copy(str);
        }

        public final String component1() {
            return getTag();
        }

        public final End copy(String tag) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            return new End(tag);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof End) && Intrinsics.areEqual(getTag(), ((End) other).getTag());
        }

        public int hashCode() {
            return getTag().hashCode();
        }

        public String toString() {
            return "End(tag=" + getTag() + ')';
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getTag() {
            return this.tag;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public End(String tag) {
            super(null);
            Intrinsics.checkNotNullParameter(tag, "tag");
            this.tag = tag;
        }
    }

    /* compiled from: WorkflowUIState.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\u0006\u0010\r\u001a\u00020\u0005¢\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u0017\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003Ji\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\b\b\u0002\u0010\r\u001a\u00020\u0005HÆ\u0001J\u0013\u0010#\u001a\u00020\u00052\b\u0010$\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u001f\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014¨\u0006("}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$StartSessionRecording;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "tag", "", "recordAudio", "", "showConsentScreen", "uploadSession", "uploadUrl", UploadingFragment.ARG_KEY_STOP_MODULE_ID, "textConfigs", "", "", "showBackButton", "(Ljava/lang/String;ZZZLjava/lang/String;Ljava/lang/String;Ljava/util/Map;Z)V", "getRecordAudio", "()Z", "getShowBackButton", "getShowConsentScreen", "getStopModuleId", "()Ljava/lang/String;", "getTag", "getTextConfigs", "()Ljava/util/Map;", "getUploadSession", "getUploadUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class StartSessionRecording extends WorkflowUIState {
        private final boolean recordAudio;
        private final boolean showBackButton;
        private final boolean showConsentScreen;
        private final String stopModuleId;

        @SerializedName("module_id")
        private final String tag;
        private final Map<String, Object> textConfigs;
        private final boolean uploadSession;
        private final String uploadUrl;

        public final String component1() {
            return getTag();
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getRecordAudio() {
            return this.recordAudio;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getShowConsentScreen() {
            return this.showConsentScreen;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getUploadSession() {
            return this.uploadSession;
        }

        /* renamed from: component5, reason: from getter */
        public final String getUploadUrl() {
            return this.uploadUrl;
        }

        /* renamed from: component6, reason: from getter */
        public final String getStopModuleId() {
            return this.stopModuleId;
        }

        public final Map<String, Object> component7() {
            return this.textConfigs;
        }

        /* renamed from: component8, reason: from getter */
        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        public final StartSessionRecording copy(String tag, boolean recordAudio, boolean showConsentScreen, boolean uploadSession, String uploadUrl, String stopModuleId, Map<String, ? extends Object> textConfigs, boolean showBackButton) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(stopModuleId, "stopModuleId");
            return new StartSessionRecording(tag, recordAudio, showConsentScreen, uploadSession, uploadUrl, stopModuleId, textConfigs, showBackButton);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StartSessionRecording)) {
                return false;
            }
            StartSessionRecording startSessionRecording = (StartSessionRecording) other;
            return Intrinsics.areEqual(getTag(), startSessionRecording.getTag()) && this.recordAudio == startSessionRecording.recordAudio && this.showConsentScreen == startSessionRecording.showConsentScreen && this.uploadSession == startSessionRecording.uploadSession && Intrinsics.areEqual(this.uploadUrl, startSessionRecording.uploadUrl) && Intrinsics.areEqual(this.stopModuleId, startSessionRecording.stopModuleId) && Intrinsics.areEqual(this.textConfigs, startSessionRecording.textConfigs) && this.showBackButton == startSessionRecording.showBackButton;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = getTag().hashCode() * 31;
            boolean z = this.recordAudio;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (hashCode + i) * 31;
            boolean z2 = this.showConsentScreen;
            int i3 = z2;
            if (z2 != 0) {
                i3 = 1;
            }
            int i4 = (i2 + i3) * 31;
            boolean z3 = this.uploadSession;
            int i5 = z3;
            if (z3 != 0) {
                i5 = 1;
            }
            int i6 = (i4 + i5) * 31;
            String str = this.uploadUrl;
            int hashCode2 = (((i6 + (str == null ? 0 : str.hashCode())) * 31) + this.stopModuleId.hashCode()) * 31;
            Map<String, Object> map = this.textConfigs;
            int hashCode3 = (hashCode2 + (map != null ? map.hashCode() : 0)) * 31;
            boolean z4 = this.showBackButton;
            return hashCode3 + (z4 ? 1 : z4 ? 1 : 0);
        }

        public String toString() {
            return "StartSessionRecording(tag=" + getTag() + ", recordAudio=" + this.recordAudio + ", showConsentScreen=" + this.showConsentScreen + ", uploadSession=" + this.uploadSession + ", uploadUrl=" + this.uploadUrl + ", stopModuleId=" + this.stopModuleId + ", textConfigs=" + this.textConfigs + ", showBackButton=" + this.showBackButton + ')';
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getTag() {
            return this.tag;
        }

        public final boolean getRecordAudio() {
            return this.recordAudio;
        }

        public final boolean getShowConsentScreen() {
            return this.showConsentScreen;
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

        public /* synthetic */ StartSessionRecording(String str, boolean z, boolean z2, boolean z3, String str2, String str3, Map map, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? true : z, (i & 4) != 0 ? true : z2, (i & 8) != 0 ? false : z3, str2, str3, (i & 64) != 0 ? MapsKt.emptyMap() : map, z4);
        }

        public final Map<String, Object> getTextConfigs() {
            return this.textConfigs;
        }

        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StartSessionRecording(String tag, boolean z, boolean z2, boolean z3, String str, String stopModuleId, Map<String, ? extends Object> map, boolean z4) {
            super(null);
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(stopModuleId, "stopModuleId");
            this.tag = tag;
            this.recordAudio = z;
            this.showConsentScreen = z2;
            this.uploadSession = z3;
            this.uploadUrl = str;
            this.stopModuleId = stopModuleId;
            this.textConfigs = map;
            this.showBackButton = z4;
        }
    }

    /* compiled from: WorkflowUIState.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u0017\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J?\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$StopSessionRecording;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "tag", "", "showRecordingReview", "", "textConfigs", "", "", "showBackButton", "(Ljava/lang/String;ZLjava/util/Map;Z)V", "getShowBackButton", "()Z", "getShowRecordingReview", "getTag", "()Ljava/lang/String;", "getTextConfigs", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class StopSessionRecording extends WorkflowUIState {
        private final boolean showBackButton;
        private final boolean showRecordingReview;

        @SerializedName("module_id")
        private final String tag;
        private final Map<String, Object> textConfigs;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ StopSessionRecording copy$default(StopSessionRecording stopSessionRecording, String str, boolean z, Map map, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = stopSessionRecording.getTag();
            }
            if ((i & 2) != 0) {
                z = stopSessionRecording.showRecordingReview;
            }
            if ((i & 4) != 0) {
                map = stopSessionRecording.textConfigs;
            }
            if ((i & 8) != 0) {
                z2 = stopSessionRecording.showBackButton;
            }
            return stopSessionRecording.copy(str, z, map, z2);
        }

        public final String component1() {
            return getTag();
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getShowRecordingReview() {
            return this.showRecordingReview;
        }

        public final Map<String, Object> component3() {
            return this.textConfigs;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        public final StopSessionRecording copy(String tag, boolean showRecordingReview, Map<String, ? extends Object> textConfigs, boolean showBackButton) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            return new StopSessionRecording(tag, showRecordingReview, textConfigs, showBackButton);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StopSessionRecording)) {
                return false;
            }
            StopSessionRecording stopSessionRecording = (StopSessionRecording) other;
            return Intrinsics.areEqual(getTag(), stopSessionRecording.getTag()) && this.showRecordingReview == stopSessionRecording.showRecordingReview && Intrinsics.areEqual(this.textConfigs, stopSessionRecording.textConfigs) && this.showBackButton == stopSessionRecording.showBackButton;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = getTag().hashCode() * 31;
            boolean z = this.showRecordingReview;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (hashCode + i) * 31;
            Map<String, Object> map = this.textConfigs;
            int hashCode2 = (i2 + (map == null ? 0 : map.hashCode())) * 31;
            boolean z2 = this.showBackButton;
            return hashCode2 + (z2 ? 1 : z2 ? 1 : 0);
        }

        public String toString() {
            return "StopSessionRecording(tag=" + getTag() + ", showRecordingReview=" + this.showRecordingReview + ", textConfigs=" + this.textConfigs + ", showBackButton=" + this.showBackButton + ')';
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getTag() {
            return this.tag;
        }

        public final boolean getShowRecordingReview() {
            return this.showRecordingReview;
        }

        public /* synthetic */ StopSessionRecording(String str, boolean z, Map map, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? false : z, (i & 4) != 0 ? MapsKt.emptyMap() : map, (i & 8) != 0 ? true : z2);
        }

        public final Map<String, Object> getTextConfigs() {
            return this.textConfigs;
        }

        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StopSessionRecording(String tag, boolean z, Map<String, ? extends Object> map, boolean z2) {
            super(null);
            Intrinsics.checkNotNullParameter(tag, "tag");
            this.tag = tag;
            this.showRecordingReview = z;
            this.textConfigs = map;
            this.showBackButton = z2;
        }
    }

    /* compiled from: WorkflowUIState.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u0017\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\nHÆ\u0003J?\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001e"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$VideoStatement;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "tag", "", "vsConfig", "Lco/hyperverge/hyperkyc/data/models/VideoStatementConfig;", "textConfigs", "", "", "showBackButton", "", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/VideoStatementConfig;Ljava/util/Map;Z)V", "getShowBackButton", "()Z", "getTag", "()Ljava/lang/String;", "getTextConfigs", "()Ljava/util/Map;", "getVsConfig", "()Lco/hyperverge/hyperkyc/data/models/VideoStatementConfig;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class VideoStatement extends WorkflowUIState {
        private final boolean showBackButton;

        @SerializedName("module_id")
        private final String tag;
        private final Map<String, Object> textConfigs;
        private final VideoStatementConfig vsConfig;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ VideoStatement copy$default(VideoStatement videoStatement, String str, VideoStatementConfig videoStatementConfig, Map map, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = videoStatement.getTag();
            }
            if ((i & 2) != 0) {
                videoStatementConfig = videoStatement.vsConfig;
            }
            if ((i & 4) != 0) {
                map = videoStatement.textConfigs;
            }
            if ((i & 8) != 0) {
                z = videoStatement.showBackButton;
            }
            return videoStatement.copy(str, videoStatementConfig, map, z);
        }

        public final String component1() {
            return getTag();
        }

        /* renamed from: component2, reason: from getter */
        public final VideoStatementConfig getVsConfig() {
            return this.vsConfig;
        }

        public final Map<String, Object> component3() {
            return this.textConfigs;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        public final VideoStatement copy(String tag, VideoStatementConfig vsConfig, Map<String, ? extends Object> textConfigs, boolean showBackButton) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(vsConfig, "vsConfig");
            return new VideoStatement(tag, vsConfig, textConfigs, showBackButton);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoStatement)) {
                return false;
            }
            VideoStatement videoStatement = (VideoStatement) other;
            return Intrinsics.areEqual(getTag(), videoStatement.getTag()) && Intrinsics.areEqual(this.vsConfig, videoStatement.vsConfig) && Intrinsics.areEqual(this.textConfigs, videoStatement.textConfigs) && this.showBackButton == videoStatement.showBackButton;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = ((getTag().hashCode() * 31) + this.vsConfig.hashCode()) * 31;
            Map<String, Object> map = this.textConfigs;
            int hashCode2 = (hashCode + (map == null ? 0 : map.hashCode())) * 31;
            boolean z = this.showBackButton;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return hashCode2 + i;
        }

        public String toString() {
            return "VideoStatement(tag=" + getTag() + ", vsConfig=" + this.vsConfig + ", textConfigs=" + this.textConfigs + ", showBackButton=" + this.showBackButton + ')';
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getTag() {
            return this.tag;
        }

        public final VideoStatementConfig getVsConfig() {
            return this.vsConfig;
        }

        public /* synthetic */ VideoStatement(String str, VideoStatementConfig videoStatementConfig, Map map, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, videoStatementConfig, (i & 4) != 0 ? MapsKt.emptyMap() : map, z);
        }

        public final Map<String, Object> getTextConfigs() {
            return this.textConfigs;
        }

        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public VideoStatement(String tag, VideoStatementConfig vsConfig, Map<String, ? extends Object> map, boolean z) {
            super(null);
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(vsConfig, "vsConfig");
            this.tag = tag;
            this.vsConfig = vsConfig;
            this.textConfigs = map;
            this.showBackButton = z;
        }
    }

    /* compiled from: WorkflowUIState.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003J\t\u0010\u001c\u001a\u00020\fHÆ\u0003JU\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010\nHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u001f\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006#"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$VideoStatementV2;", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "tag", "", "vsConfigV2", "Lco/hyperverge/hyperkyc/data/models/VideoStatementV2Config;", "showEndState", "isSuccess", "textConfigs", "", "", "showBackButton", "", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/VideoStatementV2Config;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Z)V", "()Ljava/lang/String;", "getShowBackButton", "()Z", "getShowEndState", "getTag", "getTextConfigs", "()Ljava/util/Map;", "getVsConfigV2", "()Lco/hyperverge/hyperkyc/data/models/VideoStatementV2Config;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class VideoStatementV2 extends WorkflowUIState {
        private final String isSuccess;
        private final boolean showBackButton;
        private final String showEndState;

        @SerializedName("module_id")
        private final String tag;
        private final Map<String, Object> textConfigs;
        private final VideoStatementV2Config vsConfigV2;

        public static /* synthetic */ VideoStatementV2 copy$default(VideoStatementV2 videoStatementV2, String str, VideoStatementV2Config videoStatementV2Config, String str2, String str3, Map map, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = videoStatementV2.getTag();
            }
            if ((i & 2) != 0) {
                videoStatementV2Config = videoStatementV2.vsConfigV2;
            }
            VideoStatementV2Config videoStatementV2Config2 = videoStatementV2Config;
            if ((i & 4) != 0) {
                str2 = videoStatementV2.showEndState;
            }
            String str4 = str2;
            if ((i & 8) != 0) {
                str3 = videoStatementV2.isSuccess;
            }
            String str5 = str3;
            if ((i & 16) != 0) {
                map = videoStatementV2.textConfigs;
            }
            Map map2 = map;
            if ((i & 32) != 0) {
                z = videoStatementV2.showBackButton;
            }
            return videoStatementV2.copy(str, videoStatementV2Config2, str4, str5, map2, z);
        }

        public final String component1() {
            return getTag();
        }

        /* renamed from: component2, reason: from getter */
        public final VideoStatementV2Config getVsConfigV2() {
            return this.vsConfigV2;
        }

        /* renamed from: component3, reason: from getter */
        public final String getShowEndState() {
            return this.showEndState;
        }

        /* renamed from: component4, reason: from getter */
        public final String getIsSuccess() {
            return this.isSuccess;
        }

        public final Map<String, Object> component5() {
            return this.textConfigs;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        public final VideoStatementV2 copy(String tag, VideoStatementV2Config vsConfigV2, String showEndState, String isSuccess, Map<String, ? extends Object> textConfigs, boolean showBackButton) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(vsConfigV2, "vsConfigV2");
            Intrinsics.checkNotNullParameter(showEndState, "showEndState");
            return new VideoStatementV2(tag, vsConfigV2, showEndState, isSuccess, textConfigs, showBackButton);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoStatementV2)) {
                return false;
            }
            VideoStatementV2 videoStatementV2 = (VideoStatementV2) other;
            return Intrinsics.areEqual(getTag(), videoStatementV2.getTag()) && Intrinsics.areEqual(this.vsConfigV2, videoStatementV2.vsConfigV2) && Intrinsics.areEqual(this.showEndState, videoStatementV2.showEndState) && Intrinsics.areEqual(this.isSuccess, videoStatementV2.isSuccess) && Intrinsics.areEqual(this.textConfigs, videoStatementV2.textConfigs) && this.showBackButton == videoStatementV2.showBackButton;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = ((((getTag().hashCode() * 31) + this.vsConfigV2.hashCode()) * 31) + this.showEndState.hashCode()) * 31;
            String str = this.isSuccess;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            Map<String, Object> map = this.textConfigs;
            int hashCode3 = (hashCode2 + (map != null ? map.hashCode() : 0)) * 31;
            boolean z = this.showBackButton;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return hashCode3 + i;
        }

        public String toString() {
            return "VideoStatementV2(tag=" + getTag() + ", vsConfigV2=" + this.vsConfigV2 + ", showEndState=" + this.showEndState + ", isSuccess=" + this.isSuccess + ", textConfigs=" + this.textConfigs + ", showBackButton=" + this.showBackButton + ')';
        }

        @Override // co.hyperverge.hyperkyc.ui.models.WorkflowUIState
        public String getTag() {
            return this.tag;
        }

        public final VideoStatementV2Config getVsConfigV2() {
            return this.vsConfigV2;
        }

        public final String getShowEndState() {
            return this.showEndState;
        }

        public final String isSuccess() {
            return this.isSuccess;
        }

        public /* synthetic */ VideoStatementV2(String str, VideoStatementV2Config videoStatementV2Config, String str2, String str3, Map map, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, videoStatementV2Config, (i & 4) != 0 ? "no" : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? MapsKt.emptyMap() : map, z);
        }

        public final Map<String, Object> getTextConfigs() {
            return this.textConfigs;
        }

        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public VideoStatementV2(String tag, VideoStatementV2Config vsConfigV2, String showEndState, String str, Map<String, ? extends Object> map, boolean z) {
            super(null);
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(vsConfigV2, "vsConfigV2");
            Intrinsics.checkNotNullParameter(showEndState, "showEndState");
            this.tag = tag;
            this.vsConfigV2 = vsConfigV2;
            this.showEndState = showEndState;
            this.isSuccess = str;
            this.textConfigs = map;
            this.showBackButton = z;
        }
    }

    /* compiled from: WorkflowUIState.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$Child;", "", "isChild", "", "()Z", "uiStyle", "", "getUiStyle", "()Ljava/lang/String;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface Child {
        String getUiStyle();

        boolean isChild();

        /* compiled from: WorkflowUIState.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class DefaultImpls {
            public static boolean isChild(Child child) {
                String uiStyle = child.getUiStyle();
                return !(uiStyle == null || StringsKt.isBlank(uiStyle));
            }
        }
    }
}
