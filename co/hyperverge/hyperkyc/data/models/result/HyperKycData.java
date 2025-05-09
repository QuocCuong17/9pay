package co.hyperverge.hyperkyc.data.models.result;

import android.app.Application;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import co.hyperverge.hyperkyc.data.models.WorkflowAPIParams;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.data.network.ApiAction;
import co.hyperverge.hyperkyc.data.network.BaseResponse;
import co.hyperverge.hyperkyc.data.network.DocCaptureApiDetail;
import co.hyperverge.hyperkyc.data.network.FaceCaptureApiDetail;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.JSONExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.URLExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.analytics.mixpanel.Keys;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.firebase.messaging.Constants;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.tekartik.sqflite.Constant;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: HyperKycData.kt */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0087\b\u0018\u0000 l2\u00020\u0001:\u0014hijklmnopqrstuvwxyz{B\u0087\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0018\b\u0002\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0005j\b\u0012\u0004\u0012\u00020\u000b`\u0007\u0012\u0018\b\u0002\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\r0\u0005j\b\u0012\u0004\u0012\u00020\r`\u0007\u0012\u0018\b\u0002\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u0005j\b\u0012\u0004\u0012\u00020\u000f`\u0007\u0012\u0018\b\u0002\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0005j\b\u0012\u0004\u0012\u00020\u0011`\u0007\u0012\u0018\b\u0002\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0005j\b\u0012\u0004\u0012\u00020\u0013`\u0007\u0012\u0018\b\u0002\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0005j\b\u0012\u0004\u0012\u00020\u0015`\u0007\u0012\u0018\b\u0002\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00170\u0005j\b\u0012\u0004\u0012\u00020\u0017`\u0007\u0012\u0018\b\u0002\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0005j\b\u0012\u0004\u0012\u00020\u0019`\u0007¢\u0006\u0002\u0010\u001aJ\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u001107J\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u001707J\u0014\u00108\u001a\t\u0018\u000109¢\u0006\u0002\b:H\u0000¢\u0006\u0002\b;J\u0010\u0010<\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b=J\u001e\u0010>\u001a\u0012\u0012\u0004\u0012\u00020\u00170\u0005j\b\u0012\u0004\u0012\u00020\u0017`\u0007HÀ\u0003¢\u0006\u0002\b?J\u001e\u0010@\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0005j\b\u0012\u0004\u0012\u00020\u0019`\u0007HÀ\u0003¢\u0006\u0002\bAJ\u001e\u0010B\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÀ\u0003¢\u0006\u0002\bCJ\u0010\u0010D\u001a\u0004\u0018\u00010\tHÀ\u0003¢\u0006\u0002\bEJ\u001e\u0010F\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0005j\b\u0012\u0004\u0012\u00020\u000b`\u0007HÀ\u0003¢\u0006\u0002\bGJ\u001e\u0010H\u001a\u0012\u0012\u0004\u0012\u00020\r0\u0005j\b\u0012\u0004\u0012\u00020\r`\u0007HÀ\u0003¢\u0006\u0002\bIJ\u001e\u0010J\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u0005j\b\u0012\u0004\u0012\u00020\u000f`\u0007HÀ\u0003¢\u0006\u0002\bKJ\u001e\u0010L\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0005j\b\u0012\u0004\u0012\u00020\u0011`\u0007HÀ\u0003¢\u0006\u0002\bMJ\u001e\u0010N\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0005j\b\u0012\u0004\u0012\u00020\u0013`\u0007HÀ\u0003¢\u0006\u0002\bOJ\u001e\u0010P\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0005j\b\u0012\u0004\u0012\u00020\u0015`\u0007HÀ\u0003¢\u0006\u0002\bQJ\u008b\u0002\u0010R\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0018\b\u0002\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0005j\b\u0012\u0004\u0012\u00020\u000b`\u00072\u0018\b\u0002\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\r0\u0005j\b\u0012\u0004\u0012\u00020\r`\u00072\u0018\b\u0002\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u0005j\b\u0012\u0004\u0012\u00020\u000f`\u00072\u0018\b\u0002\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0005j\b\u0012\u0004\u0012\u00020\u0011`\u00072\u0018\b\u0002\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0005j\b\u0012\u0004\u0012\u00020\u0013`\u00072\u0018\b\u0002\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0005j\b\u0012\u0004\u0012\u00020\u0015`\u00072\u0018\b\u0002\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00170\u0005j\b\u0012\u0004\u0012\u00020\u0017`\u00072\u0018\b\u0002\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0005j\b\u0012\u0004\u0012\u00020\u0019`\u0007HÆ\u0001J\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003J\t\u0010S\u001a\u00020THÖ\u0001J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000607J\u0013\u0010U\u001a\u00020V2\b\u0010W\u001a\u0004\u0018\u000109HÖ\u0003J\b\u0010\b\u001a\u0004\u0018\u00010\tJ\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u001307J\u0013\u0010X\u001a\b\u0012\u0004\u0012\u00020Z0YH\u0000¢\u0006\u0002\b[J\t\u0010\\\u001a\u00020THÖ\u0001J\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001907J\b\u0010]\u001a\u0004\u0018\u00010^J\b\u0010_\u001a\u0004\u0018\u00010^J\u0014\u0010`\u001a\t\u0018\u000109¢\u0006\u0002\b:H\u0000¢\u0006\u0002\baJ\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b07J\t\u0010b\u001a\u00020^HÖ\u0001J\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r07J\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f07J\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u001507J\u0019\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020f2\u0006\u0010g\u001a\u00020THÖ\u0001R*\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0005j\b\u0012\u0004\u0012\u00020\u0011`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR*\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00170\u0005j\b\u0012\u0004\u0012\u00020\u0017`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001c\"\u0004\b \u0010\u001eR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R*\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001c\"\u0004\b&\u0010\u001eR\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R*\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0005j\b\u0012\u0004\u0012\u00020\u0013`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001c\"\u0004\b,\u0010\u001eR*\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0005j\b\u0012\u0004\u0012\u00020\u0019`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001c\"\u0004\b.\u0010\u001eR*\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0005j\b\u0012\u0004\u0012\u00020\u000b`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u001c\"\u0004\b0\u0010\u001eR*\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\r0\u0005j\b\u0012\u0004\u0012\u00020\r`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u001c\"\u0004\b2\u0010\u001eR*\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u0005j\b\u0012\u0004\u0012\u00020\u000f`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u001c\"\u0004\b4\u0010\u001eR*\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0005j\b\u0012\u0004\u0012\u00020\u0015`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u001c\"\u0004\b6\u0010\u001e¨\u0006|"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;", "Landroid/os/Parcelable;", "countryResult", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$CountryResult;", "docResultList", "Ljava/util/ArrayList;", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$DocResult;", "Lkotlin/collections/ArrayList;", "faceResult", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$FaceResult;", "sessionResultList", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$SessionResult;", "videoStatementResultList", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementResult;", "videoStatementV2ResultList", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementV2Result;", "apiResultList", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$APIResult;", "formResultList", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$FormResult;", "webviewResultList", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$WebviewResult;", "barcodeResultList", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$BarcodeResult;", "nfcResultList", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$NFCResult;", "(Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$CountryResult;Ljava/util/ArrayList;Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$FaceResult;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "getApiResultList$hyperkyc_release", "()Ljava/util/ArrayList;", "setApiResultList$hyperkyc_release", "(Ljava/util/ArrayList;)V", "getBarcodeResultList$hyperkyc_release", "setBarcodeResultList$hyperkyc_release", "getCountryResult$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$CountryResult;", "setCountryResult$hyperkyc_release", "(Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$CountryResult;)V", "getDocResultList$hyperkyc_release", "setDocResultList$hyperkyc_release", "getFaceResult$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$FaceResult;", "setFaceResult$hyperkyc_release", "(Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$FaceResult;)V", "getFormResultList$hyperkyc_release", "setFormResultList$hyperkyc_release", "getNfcResultList$hyperkyc_release", "setNfcResultList$hyperkyc_release", "getSessionResultList$hyperkyc_release", "setSessionResultList$hyperkyc_release", "getVideoStatementResultList$hyperkyc_release", "setVideoStatementResultList$hyperkyc_release", "getVideoStatementV2ResultList$hyperkyc_release", "setVideoStatementV2ResultList$hyperkyc_release", "getWebviewResultList$hyperkyc_release", "setWebviewResultList$hyperkyc_release", "", "baseUrl", "", "Lkotlinx/parcelize/RawValue;", "baseUrl$hyperkyc_release", "component1", "component1$hyperkyc_release", "component10", "component10$hyperkyc_release", "component11", "component11$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "component4", "component4$hyperkyc_release", "component5", "component5$hyperkyc_release", "component6", "component6$hyperkyc_release", "component7", "component7$hyperkyc_release", "component8", "component8$hyperkyc_release", "component9", "component9$hyperkyc_release", "copy", "describeContents", "", "equals", "", "other", "getApiFlags", "", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$ApiFlags;", "getApiFlags$hyperkyc_release", "hashCode", "selectedCountryId", "", "selectedCountryName", "selectedRegion", "selectedRegion$hyperkyc_release", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "APIData", "APIResult", "ApiFlags", "BarcodeResult", "Companion", "CountryResult", "DocData", "DocResult", "FaceData", "FaceResult", "FormResult", "NFCResult", "SessionData", "SessionResult", "VideoStatementData", "VideoStatementResult", "VideoStatementV2Data", "VideoStatementV2Result", "WebviewData", "WebviewResult", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class HyperKycData implements Parcelable {
    public static final /* synthetic */ String ARG_KEY = "hyperKycData";
    private ArrayList<APIResult> apiResultList;
    private ArrayList<BarcodeResult> barcodeResultList;
    private CountryResult countryResult;
    private ArrayList<DocResult> docResultList;
    private FaceResult faceResult;
    private ArrayList<FormResult> formResultList;
    private ArrayList<NFCResult> nfcResultList;
    private ArrayList<SessionResult> sessionResultList;
    private ArrayList<VideoStatementResult> videoStatementResultList;
    private ArrayList<VideoStatementV2Result> videoStatementV2ResultList;
    private ArrayList<WebviewResult> webviewResultList;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final Parcelable.Creator<HyperKycData> CREATOR = new Creator();

    /* compiled from: HyperKycData.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Creator implements Parcelable.Creator<HyperKycData> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final HyperKycData createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            CountryResult createFromParcel = parcel.readInt() == 0 ? null : CountryResult.CREATOR.createFromParcel(parcel);
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            for (int i = 0; i != readInt; i++) {
                arrayList.add(DocResult.CREATOR.createFromParcel(parcel));
            }
            FaceResult createFromParcel2 = parcel.readInt() != 0 ? FaceResult.CREATOR.createFromParcel(parcel) : null;
            int readInt2 = parcel.readInt();
            ArrayList arrayList2 = new ArrayList(readInt2);
            for (int i2 = 0; i2 != readInt2; i2++) {
                arrayList2.add(SessionResult.CREATOR.createFromParcel(parcel));
            }
            int readInt3 = parcel.readInt();
            ArrayList arrayList3 = new ArrayList(readInt3);
            for (int i3 = 0; i3 != readInt3; i3++) {
                arrayList3.add(VideoStatementResult.CREATOR.createFromParcel(parcel));
            }
            int readInt4 = parcel.readInt();
            ArrayList arrayList4 = new ArrayList(readInt4);
            for (int i4 = 0; i4 != readInt4; i4++) {
                arrayList4.add(VideoStatementV2Result.CREATOR.createFromParcel(parcel));
            }
            int readInt5 = parcel.readInt();
            ArrayList arrayList5 = new ArrayList(readInt5);
            for (int i5 = 0; i5 != readInt5; i5++) {
                arrayList5.add(APIResult.CREATOR.createFromParcel(parcel));
            }
            int readInt6 = parcel.readInt();
            ArrayList arrayList6 = new ArrayList(readInt6);
            for (int i6 = 0; i6 != readInt6; i6++) {
                arrayList6.add(FormResult.CREATOR.createFromParcel(parcel));
            }
            int readInt7 = parcel.readInt();
            ArrayList arrayList7 = new ArrayList(readInt7);
            for (int i7 = 0; i7 != readInt7; i7++) {
                arrayList7.add(WebviewResult.CREATOR.createFromParcel(parcel));
            }
            int readInt8 = parcel.readInt();
            ArrayList arrayList8 = new ArrayList(readInt8);
            for (int i8 = 0; i8 != readInt8; i8++) {
                arrayList8.add(BarcodeResult.CREATOR.createFromParcel(parcel));
            }
            int readInt9 = parcel.readInt();
            ArrayList arrayList9 = new ArrayList(readInt9);
            for (int i9 = 0; i9 != readInt9; i9++) {
                arrayList9.add(NFCResult.CREATOR.createFromParcel(parcel));
            }
            return new HyperKycData(createFromParcel, arrayList, createFromParcel2, arrayList2, arrayList3, arrayList4, arrayList5, arrayList6, arrayList7, arrayList8, arrayList9);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final HyperKycData[] newArray(int i) {
            return new HyperKycData[i];
        }
    }

    public HyperKycData() {
        this(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
    }

    /* renamed from: component1$hyperkyc_release, reason: from getter */
    public final CountryResult getCountryResult() {
        return this.countryResult;
    }

    public final ArrayList<BarcodeResult> component10$hyperkyc_release() {
        return this.barcodeResultList;
    }

    public final ArrayList<NFCResult> component11$hyperkyc_release() {
        return this.nfcResultList;
    }

    public final ArrayList<DocResult> component2$hyperkyc_release() {
        return this.docResultList;
    }

    /* renamed from: component3$hyperkyc_release, reason: from getter */
    public final FaceResult getFaceResult() {
        return this.faceResult;
    }

    public final ArrayList<SessionResult> component4$hyperkyc_release() {
        return this.sessionResultList;
    }

    public final ArrayList<VideoStatementResult> component5$hyperkyc_release() {
        return this.videoStatementResultList;
    }

    public final ArrayList<VideoStatementV2Result> component6$hyperkyc_release() {
        return this.videoStatementV2ResultList;
    }

    public final ArrayList<APIResult> component7$hyperkyc_release() {
        return this.apiResultList;
    }

    public final ArrayList<FormResult> component8$hyperkyc_release() {
        return this.formResultList;
    }

    public final ArrayList<WebviewResult> component9$hyperkyc_release() {
        return this.webviewResultList;
    }

    public final HyperKycData copy(CountryResult countryResult, ArrayList<DocResult> docResultList, FaceResult faceResult, ArrayList<SessionResult> sessionResultList, ArrayList<VideoStatementResult> videoStatementResultList, ArrayList<VideoStatementV2Result> videoStatementV2ResultList, ArrayList<APIResult> apiResultList, ArrayList<FormResult> formResultList, ArrayList<WebviewResult> webviewResultList, ArrayList<BarcodeResult> barcodeResultList, ArrayList<NFCResult> nfcResultList) {
        Intrinsics.checkNotNullParameter(docResultList, "docResultList");
        Intrinsics.checkNotNullParameter(sessionResultList, "sessionResultList");
        Intrinsics.checkNotNullParameter(videoStatementResultList, "videoStatementResultList");
        Intrinsics.checkNotNullParameter(videoStatementV2ResultList, "videoStatementV2ResultList");
        Intrinsics.checkNotNullParameter(apiResultList, "apiResultList");
        Intrinsics.checkNotNullParameter(formResultList, "formResultList");
        Intrinsics.checkNotNullParameter(webviewResultList, "webviewResultList");
        Intrinsics.checkNotNullParameter(barcodeResultList, "barcodeResultList");
        Intrinsics.checkNotNullParameter(nfcResultList, "nfcResultList");
        return new HyperKycData(countryResult, docResultList, faceResult, sessionResultList, videoStatementResultList, videoStatementV2ResultList, apiResultList, formResultList, webviewResultList, barcodeResultList, nfcResultList);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HyperKycData)) {
            return false;
        }
        HyperKycData hyperKycData = (HyperKycData) other;
        return Intrinsics.areEqual(this.countryResult, hyperKycData.countryResult) && Intrinsics.areEqual(this.docResultList, hyperKycData.docResultList) && Intrinsics.areEqual(this.faceResult, hyperKycData.faceResult) && Intrinsics.areEqual(this.sessionResultList, hyperKycData.sessionResultList) && Intrinsics.areEqual(this.videoStatementResultList, hyperKycData.videoStatementResultList) && Intrinsics.areEqual(this.videoStatementV2ResultList, hyperKycData.videoStatementV2ResultList) && Intrinsics.areEqual(this.apiResultList, hyperKycData.apiResultList) && Intrinsics.areEqual(this.formResultList, hyperKycData.formResultList) && Intrinsics.areEqual(this.webviewResultList, hyperKycData.webviewResultList) && Intrinsics.areEqual(this.barcodeResultList, hyperKycData.barcodeResultList) && Intrinsics.areEqual(this.nfcResultList, hyperKycData.nfcResultList);
    }

    public int hashCode() {
        CountryResult countryResult = this.countryResult;
        int hashCode = (((countryResult == null ? 0 : countryResult.hashCode()) * 31) + this.docResultList.hashCode()) * 31;
        FaceResult faceResult = this.faceResult;
        return ((((((((((((((((hashCode + (faceResult != null ? faceResult.hashCode() : 0)) * 31) + this.sessionResultList.hashCode()) * 31) + this.videoStatementResultList.hashCode()) * 31) + this.videoStatementV2ResultList.hashCode()) * 31) + this.apiResultList.hashCode()) * 31) + this.formResultList.hashCode()) * 31) + this.webviewResultList.hashCode()) * 31) + this.barcodeResultList.hashCode()) * 31) + this.nfcResultList.hashCode();
    }

    public String toString() {
        return "HyperKycData(countryResult=" + this.countryResult + ", docResultList=" + this.docResultList + ", faceResult=" + this.faceResult + ", sessionResultList=" + this.sessionResultList + ", videoStatementResultList=" + this.videoStatementResultList + ", videoStatementV2ResultList=" + this.videoStatementV2ResultList + ", apiResultList=" + this.apiResultList + ", formResultList=" + this.formResultList + ", webviewResultList=" + this.webviewResultList + ", barcodeResultList=" + this.barcodeResultList + ", nfcResultList=" + this.nfcResultList + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        CountryResult countryResult = this.countryResult;
        if (countryResult == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            countryResult.writeToParcel(parcel, flags);
        }
        ArrayList<DocResult> arrayList = this.docResultList;
        parcel.writeInt(arrayList.size());
        Iterator<DocResult> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().writeToParcel(parcel, flags);
        }
        FaceResult faceResult = this.faceResult;
        if (faceResult == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            faceResult.writeToParcel(parcel, flags);
        }
        ArrayList<SessionResult> arrayList2 = this.sessionResultList;
        parcel.writeInt(arrayList2.size());
        Iterator<SessionResult> it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            it2.next().writeToParcel(parcel, flags);
        }
        ArrayList<VideoStatementResult> arrayList3 = this.videoStatementResultList;
        parcel.writeInt(arrayList3.size());
        Iterator<VideoStatementResult> it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            it3.next().writeToParcel(parcel, flags);
        }
        ArrayList<VideoStatementV2Result> arrayList4 = this.videoStatementV2ResultList;
        parcel.writeInt(arrayList4.size());
        Iterator<VideoStatementV2Result> it4 = arrayList4.iterator();
        while (it4.hasNext()) {
            it4.next().writeToParcel(parcel, flags);
        }
        ArrayList<APIResult> arrayList5 = this.apiResultList;
        parcel.writeInt(arrayList5.size());
        Iterator<APIResult> it5 = arrayList5.iterator();
        while (it5.hasNext()) {
            it5.next().writeToParcel(parcel, flags);
        }
        ArrayList<FormResult> arrayList6 = this.formResultList;
        parcel.writeInt(arrayList6.size());
        Iterator<FormResult> it6 = arrayList6.iterator();
        while (it6.hasNext()) {
            it6.next().writeToParcel(parcel, flags);
        }
        ArrayList<WebviewResult> arrayList7 = this.webviewResultList;
        parcel.writeInt(arrayList7.size());
        Iterator<WebviewResult> it7 = arrayList7.iterator();
        while (it7.hasNext()) {
            it7.next().writeToParcel(parcel, flags);
        }
        ArrayList<BarcodeResult> arrayList8 = this.barcodeResultList;
        parcel.writeInt(arrayList8.size());
        Iterator<BarcodeResult> it8 = arrayList8.iterator();
        while (it8.hasNext()) {
            it8.next().writeToParcel(parcel, flags);
        }
        ArrayList<NFCResult> arrayList9 = this.nfcResultList;
        parcel.writeInt(arrayList9.size());
        Iterator<NFCResult> it9 = arrayList9.iterator();
        while (it9.hasNext()) {
            it9.next().writeToParcel(parcel, flags);
        }
    }

    public HyperKycData(CountryResult countryResult, ArrayList<DocResult> docResultList, FaceResult faceResult, ArrayList<SessionResult> sessionResultList, ArrayList<VideoStatementResult> videoStatementResultList, ArrayList<VideoStatementV2Result> videoStatementV2ResultList, ArrayList<APIResult> apiResultList, ArrayList<FormResult> formResultList, ArrayList<WebviewResult> webviewResultList, ArrayList<BarcodeResult> barcodeResultList, ArrayList<NFCResult> nfcResultList) {
        Intrinsics.checkNotNullParameter(docResultList, "docResultList");
        Intrinsics.checkNotNullParameter(sessionResultList, "sessionResultList");
        Intrinsics.checkNotNullParameter(videoStatementResultList, "videoStatementResultList");
        Intrinsics.checkNotNullParameter(videoStatementV2ResultList, "videoStatementV2ResultList");
        Intrinsics.checkNotNullParameter(apiResultList, "apiResultList");
        Intrinsics.checkNotNullParameter(formResultList, "formResultList");
        Intrinsics.checkNotNullParameter(webviewResultList, "webviewResultList");
        Intrinsics.checkNotNullParameter(barcodeResultList, "barcodeResultList");
        Intrinsics.checkNotNullParameter(nfcResultList, "nfcResultList");
        this.countryResult = countryResult;
        this.docResultList = docResultList;
        this.faceResult = faceResult;
        this.sessionResultList = sessionResultList;
        this.videoStatementResultList = videoStatementResultList;
        this.videoStatementV2ResultList = videoStatementV2ResultList;
        this.apiResultList = apiResultList;
        this.formResultList = formResultList;
        this.webviewResultList = webviewResultList;
        this.barcodeResultList = barcodeResultList;
        this.nfcResultList = nfcResultList;
    }

    public final /* synthetic */ CountryResult getCountryResult$hyperkyc_release() {
        return this.countryResult;
    }

    public final /* synthetic */ void setCountryResult$hyperkyc_release(CountryResult countryResult) {
        this.countryResult = countryResult;
    }

    public /* synthetic */ HyperKycData(CountryResult countryResult, ArrayList arrayList, FaceResult faceResult, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4, ArrayList arrayList5, ArrayList arrayList6, ArrayList arrayList7, ArrayList arrayList8, ArrayList arrayList9, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : countryResult, (i & 2) != 0 ? new ArrayList() : arrayList, (i & 4) == 0 ? faceResult : null, (i & 8) != 0 ? new ArrayList() : arrayList2, (i & 16) != 0 ? new ArrayList() : arrayList3, (i & 32) != 0 ? new ArrayList() : arrayList4, (i & 64) != 0 ? new ArrayList() : arrayList5, (i & 128) != 0 ? new ArrayList() : arrayList6, (i & 256) != 0 ? new ArrayList() : arrayList7, (i & 512) != 0 ? new ArrayList() : arrayList8, (i & 1024) != 0 ? new ArrayList() : arrayList9);
    }

    /* renamed from: getDocResultList$hyperkyc_release, reason: from getter */
    public final /* synthetic */ ArrayList getDocResultList() {
        return this.docResultList;
    }

    public final /* synthetic */ void setDocResultList$hyperkyc_release(ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.docResultList = arrayList;
    }

    public final /* synthetic */ FaceResult getFaceResult$hyperkyc_release() {
        return this.faceResult;
    }

    public final /* synthetic */ void setFaceResult$hyperkyc_release(FaceResult faceResult) {
        this.faceResult = faceResult;
    }

    /* renamed from: getSessionResultList$hyperkyc_release, reason: from getter */
    public final /* synthetic */ ArrayList getSessionResultList() {
        return this.sessionResultList;
    }

    public final /* synthetic */ void setSessionResultList$hyperkyc_release(ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.sessionResultList = arrayList;
    }

    /* renamed from: getVideoStatementResultList$hyperkyc_release, reason: from getter */
    public final /* synthetic */ ArrayList getVideoStatementResultList() {
        return this.videoStatementResultList;
    }

    public final /* synthetic */ void setVideoStatementResultList$hyperkyc_release(ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.videoStatementResultList = arrayList;
    }

    /* renamed from: getVideoStatementV2ResultList$hyperkyc_release, reason: from getter */
    public final /* synthetic */ ArrayList getVideoStatementV2ResultList() {
        return this.videoStatementV2ResultList;
    }

    public final /* synthetic */ void setVideoStatementV2ResultList$hyperkyc_release(ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.videoStatementV2ResultList = arrayList;
    }

    /* renamed from: getApiResultList$hyperkyc_release, reason: from getter */
    public final /* synthetic */ ArrayList getApiResultList() {
        return this.apiResultList;
    }

    public final /* synthetic */ void setApiResultList$hyperkyc_release(ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.apiResultList = arrayList;
    }

    /* renamed from: getFormResultList$hyperkyc_release, reason: from getter */
    public final /* synthetic */ ArrayList getFormResultList() {
        return this.formResultList;
    }

    public final /* synthetic */ void setFormResultList$hyperkyc_release(ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.formResultList = arrayList;
    }

    /* renamed from: getWebviewResultList$hyperkyc_release, reason: from getter */
    public final /* synthetic */ ArrayList getWebviewResultList() {
        return this.webviewResultList;
    }

    public final /* synthetic */ void setWebviewResultList$hyperkyc_release(ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.webviewResultList = arrayList;
    }

    /* renamed from: getBarcodeResultList$hyperkyc_release, reason: from getter */
    public final /* synthetic */ ArrayList getBarcodeResultList() {
        return this.barcodeResultList;
    }

    public final /* synthetic */ void setBarcodeResultList$hyperkyc_release(ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.barcodeResultList = arrayList;
    }

    /* renamed from: getNfcResultList$hyperkyc_release, reason: from getter */
    public final /* synthetic */ ArrayList getNfcResultList() {
        return this.nfcResultList;
    }

    public final /* synthetic */ void setNfcResultList$hyperkyc_release(ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.nfcResultList = arrayList;
    }

    public final String selectedCountryId() {
        HashMap variables;
        String id$hyperkyc_release;
        CountryResult countryResult = this.countryResult;
        if (countryResult != null && (id$hyperkyc_release = countryResult.getId$hyperkyc_release()) != null) {
            return id$hyperkyc_release;
        }
        CountryResult countryResult2 = this.countryResult;
        Object obj = (countryResult2 == null || (variables = countryResult2.getVariables()) == null) ? null : variables.get(AnalyticsLogger.Keys.COUNTRY_ID);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public final String selectedCountryName() {
        HashMap variables;
        String name$hyperkyc_release;
        CountryResult countryResult = this.countryResult;
        if (countryResult != null && (name$hyperkyc_release = countryResult.getName$hyperkyc_release()) != null) {
            return name$hyperkyc_release;
        }
        CountryResult countryResult2 = this.countryResult;
        Object obj = (countryResult2 == null || (variables = countryResult2.getVariables()) == null) ? null : variables.get("name");
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public final /* synthetic */ Object selectedRegion$hyperkyc_release() {
        HashMap variables;
        String region$hyperkyc_release;
        CountryResult countryResult = this.countryResult;
        if (countryResult != null && (region$hyperkyc_release = countryResult.getRegion$hyperkyc_release()) != null) {
            return region$hyperkyc_release;
        }
        CountryResult countryResult2 = this.countryResult;
        if (countryResult2 == null || (variables = countryResult2.getVariables()) == null) {
            return null;
        }
        return variables.get("region");
    }

    public final /* synthetic */ Object baseUrl$hyperkyc_release() {
        HashMap variables;
        String baseUrl$hyperkyc_release;
        CountryResult countryResult = this.countryResult;
        if (countryResult != null && (baseUrl$hyperkyc_release = countryResult.getBaseUrl$hyperkyc_release()) != null) {
            return baseUrl$hyperkyc_release;
        }
        CountryResult countryResult2 = this.countryResult;
        if (countryResult2 == null || (variables = countryResult2.getVariables()) == null) {
            return null;
        }
        return variables.get("baseUrl");
    }

    public final List<SessionResult> sessionResultList() {
        return this.sessionResultList;
    }

    public final List<VideoStatementResult> videoStatementResultList() {
        return this.videoStatementResultList;
    }

    public final List<VideoStatementV2Result> videoStatementV2ResultList() {
        return this.videoStatementV2ResultList;
    }

    public final CountryResult countryResult() {
        return this.countryResult;
    }

    public final List<DocResult> docResultList() {
        return this.docResultList;
    }

    public final FaceResult faceResult() {
        return this.faceResult;
    }

    public final List<APIResult> apiResultList() {
        return this.apiResultList;
    }

    public final List<FormResult> formResultList() {
        return this.formResultList;
    }

    public final List<WebviewResult> webviewResultList() {
        return this.webviewResultList;
    }

    public final List<BarcodeResult> barcodeResultList() {
        return this.barcodeResultList;
    }

    public final List<NFCResult> nfcResultList() {
        return this.nfcResultList;
    }

    public final List<ApiFlags> getApiFlags$hyperkyc_release() {
        ApiFlags apiFlags;
        ArrayList arrayList = new ArrayList();
        ArrayList<DocResult> arrayList2 = this.docResultList;
        ArrayList arrayList3 = new ArrayList();
        for (DocResult docResult : arrayList2) {
            String tag = docResult.getTag();
            ArrayList<DocData> component3$hyperkyc_release = docResult.component3$hyperkyc_release();
            ArrayList arrayList4 = new ArrayList();
            Iterator<T> it = component3$hyperkyc_release.iterator();
            while (it.hasNext()) {
                ApiFlags apiFlags2 = ((DocData) it.next()).getApiFlags(tag);
                if (apiFlags2 != null) {
                    arrayList4.add(apiFlags2);
                }
            }
            CollectionsKt.addAll(arrayList3, arrayList4);
        }
        arrayList.addAll(arrayList3);
        FaceResult faceResult = this.faceResult;
        if (faceResult != null && (apiFlags = faceResult.getFaceData$hyperkyc_release().getApiFlags(faceResult.tag())) != null) {
            arrayList.add(apiFlags);
        }
        ArrayList<APIResult> arrayList5 = this.apiResultList;
        ArrayList arrayList6 = new ArrayList();
        for (APIResult aPIResult : arrayList5) {
            String tag2 = aPIResult.getTag();
            APIData component2$hyperkyc_release = aPIResult.component2$hyperkyc_release();
            ApiFlags apiFlags3 = component2$hyperkyc_release != null ? component2$hyperkyc_release.getApiFlags(tag2) : null;
            if (apiFlags3 != null) {
                arrayList6.add(apiFlags3);
            }
        }
        arrayList.addAll(arrayList6);
        return arrayList;
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J]\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0002\b\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$Companion;", "", "()V", "ARG_KEY", "", "extractApiFlags", "", "moduleId", "responseHeaders", "responseBodyRaw", Keys.STATUS_CODE, "statusMessage", "extractApiFlags$hyperkyc_release", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't wrap try/catch for region: R(38:1|(3:429|(1:431)(1:434)|(1:433))|7|(1:9)|10|(1:14)|15|(1:17)|18|(1:20)(9:385|386|387|388|389|390|(1:392)|393|(2:395|(33:397|(3:416|(1:418)(1:421)|(1:420))|403|(1:405)(1:415)|406|(1:410)|411|(1:413)|414|22|23|(1:25)(1:382)|26|(3:375|(1:377)(1:381)|(1:379)(1:380))(1:32)|33|(1:35)|36|(1:41)|42|43|44|45|(1:47)|48|(1:50)(9:319|320|321|322|323|324|(1:326)|327|(4:329|330|331|(9:333|(3:353|(1:355)(1:358)|(1:357))(1:339)|340|(1:342)|343|(1:348)|349|(1:351)|352))(1:359))|51|(4:53|(3:55|(16:57|(1:221)(4:61|62|63|(12:65|66|(1:68)|69|(1:74)|75|(1:77)|78|(6:84|85|86|(1:88)|89|(2:91|(13:93|94|95|(3:115|(1:117)(1:120)|(1:119))(1:101)|102|(1:104)|105|(1:110)|111|(1:113)|114|81|82)))|80|81|82))|124|(1:126)(1:129)|(1:128)|66|(0)|69|(2:71|74)|75|(0)|78|(0)|80|81|82)(2:222|223)|83)|227|228)(13:231|(3:311|(1:313)(1:317)|(1:315)(1:316))(1:237)|238|(1:240)|241|(1:246)|247|(6:276|277|278|(1:280)|281|(2:283|(7:285|(3:302|(1:304)(1:307)|(1:306))(1:291)|292|(1:294)|295|(1:300)|301)))|249|(6:251|(1:253)(1:262)|254|(1:256)(1:261)|257|(1:259)(1:260))|263|(2:265|(1:274))|275)|229|134|(21:136|(1:219)(1:140)|142|(1:144)(1:148)|(1:146)(1:147)|149|(1:151)|152|(1:156)|157|(1:159)|160|(1:162)(1:218)|(1:164)(1:217)|165|166|167|168|(1:170)|171|(2:173|(18:175|(1:213)(1:179)|181|(1:183)(1:211)|(13:185|186|(1:188)|189|(1:193)|194|(1:196)|197|(1:199)(1:210)|(1:201)|202|203|(2:205|206)(2:208|209))|212|186|(0)|189|(2:191|193)|194|(0)|197|(0)(0)|(0)|202|203|(0)(0))))|220|203|(0)(0)))(1:422))|21|22|23|(0)(0)|26|(1:28)|375|(0)(0)|(0)(0)|33|(0)|36|(2:38|41)|42|43|44|45|(0)|48|(0)(0)|51|(0)(0)|229|134|(0)|220|203|(0)(0)) */
        /* JADX WARN: Code restructure failed: missing block: B:141:0x080f, code lost:
        
            if (r12 != null) goto L351;
         */
        /* JADX WARN: Code restructure failed: missing block: B:180:0x091a, code lost:
        
            if (r0 != null) goto L396;
         */
        /* JADX WARN: Code restructure failed: missing block: B:318:0x07ba, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:371:0x07c1, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:372:0x07c2, code lost:
        
            r3 = "Throwable().stackTrace";
         */
        /* JADX WARN: Code restructure failed: missing block: B:373:0x07c8, code lost:
        
            r5 = r22;
            r4 = r23;
         */
        /* JADX WARN: Code restructure failed: missing block: B:383:0x07c4, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:384:0x07c5, code lost:
        
            r3 = "Throwable().stackTrace";
            r1 = r21;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:136:0x07dc  */
        /* JADX WARN: Removed duplicated region for block: B:188:0x0941  */
        /* JADX WARN: Removed duplicated region for block: B:196:0x0979  */
        /* JADX WARN: Removed duplicated region for block: B:199:0x0985  */
        /* JADX WARN: Removed duplicated region for block: B:201:0x098d  */
        /* JADX WARN: Removed duplicated region for block: B:205:0x09b7  */
        /* JADX WARN: Removed duplicated region for block: B:208:0x09b9  */
        /* JADX WARN: Removed duplicated region for block: B:210:0x098a  */
        /* JADX WARN: Removed duplicated region for block: B:231:0x05cc A[Catch: all -> 0x07ba, TryCatch #10 {all -> 0x07ba, blocks: (B:81:0x059b, B:83:0x05bb, B:95:0x050a, B:97:0x0517, B:99:0x051d, B:102:0x0538, B:104:0x0549, B:105:0x0550, B:107:0x055a, B:110:0x0561, B:111:0x0569, B:114:0x0588, B:115:0x0528, B:117:0x052e, B:229:0x07b3, B:231:0x05cc, B:233:0x05f0, B:235:0x05f6, B:238:0x0613, B:240:0x0624, B:241:0x062b, B:243:0x0635, B:246:0x063c, B:247:0x0644, B:251:0x073c, B:253:0x0746, B:254:0x074e, B:256:0x0758, B:257:0x0760, B:260:0x0767, B:265:0x076f, B:268:0x0782, B:271:0x0795, B:274:0x07a8, B:275:0x07ac, B:278:0x0695, B:281:0x069c, B:283:0x06a4, B:285:0x06b6, B:287:0x06cc, B:289:0x06d2, B:292:0x06ed, B:294:0x06fe, B:295:0x0705, B:297:0x070f, B:300:0x0716, B:301:0x071e, B:302:0x06dd, B:304:0x06e3, B:310:0x068b, B:311:0x0601, B:313:0x0607, B:277:0x0668), top: B:51:0x03de, inners: #9 }] */
        /* JADX WARN: Removed duplicated region for block: B:25:0x022e A[Catch: all -> 0x07c4, TryCatch #5 {all -> 0x07c4, blocks: (B:23:0x022a, B:25:0x022e, B:26:0x023b, B:28:0x0261, B:30:0x0267, B:33:0x0284, B:35:0x0295, B:36:0x029c, B:38:0x02a6, B:41:0x02ad, B:42:0x02b5, B:375:0x0272, B:377:0x0278), top: B:22:0x022a }] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0261 A[Catch: all -> 0x07c4, TryCatch #5 {all -> 0x07c4, blocks: (B:23:0x022a, B:25:0x022e, B:26:0x023b, B:28:0x0261, B:30:0x0267, B:33:0x0284, B:35:0x0295, B:36:0x029c, B:38:0x02a6, B:41:0x02ad, B:42:0x02b5, B:375:0x0272, B:377:0x0278), top: B:22:0x022a }] */
        /* JADX WARN: Removed duplicated region for block: B:319:0x02f0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:326:0x0329  */
        /* JADX WARN: Removed duplicated region for block: B:329:0x0332  */
        /* JADX WARN: Removed duplicated region for block: B:359:0x03dc  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0295 A[Catch: all -> 0x07c4, TryCatch #5 {all -> 0x07c4, blocks: (B:23:0x022a, B:25:0x022e, B:26:0x023b, B:28:0x0261, B:30:0x0267, B:33:0x0284, B:35:0x0295, B:36:0x029c, B:38:0x02a6, B:41:0x02ad, B:42:0x02b5, B:375:0x0272, B:377:0x0278), top: B:22:0x022a }] */
        /* JADX WARN: Removed duplicated region for block: B:377:0x0278 A[Catch: all -> 0x07c4, TryCatch #5 {all -> 0x07c4, blocks: (B:23:0x022a, B:25:0x022e, B:26:0x023b, B:28:0x0261, B:30:0x0267, B:33:0x0284, B:35:0x0295, B:36:0x029c, B:38:0x02a6, B:41:0x02ad, B:42:0x02b5, B:375:0x0272, B:377:0x0278), top: B:22:0x022a }] */
        /* JADX WARN: Removed duplicated region for block: B:379:0x0280  */
        /* JADX WARN: Removed duplicated region for block: B:380:0x0283  */
        /* JADX WARN: Removed duplicated region for block: B:381:0x027d  */
        /* JADX WARN: Removed duplicated region for block: B:382:0x023a  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x02a6 A[Catch: all -> 0x07c4, TryCatch #5 {all -> 0x07c4, blocks: (B:23:0x022a, B:25:0x022e, B:26:0x023b, B:28:0x0261, B:30:0x0267, B:33:0x0284, B:35:0x0295, B:36:0x029c, B:38:0x02a6, B:41:0x02ad, B:42:0x02b5, B:375:0x0272, B:377:0x0278), top: B:22:0x022a }] */
        /* JADX WARN: Removed duplicated region for block: B:392:0x0147  */
        /* JADX WARN: Removed duplicated region for block: B:395:0x0150  */
        /* JADX WARN: Removed duplicated region for block: B:422:0x0224  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x02d0  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x02ea  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x03e0 A[Catch: all -> 0x05c8, TryCatch #8 {all -> 0x05c8, blocks: (B:53:0x03e0, B:55:0x03e7, B:57:0x03ed, B:59:0x0417, B:331:0x0334, B:333:0x0346, B:335:0x035c, B:337:0x0362, B:340:0x037d, B:342:0x038e, B:343:0x0395, B:345:0x039f, B:348:0x03a6, B:349:0x03ae, B:352:0x03c8, B:353:0x036d, B:355:0x0373), top: B:330:0x0334 }] */
        /* JADX WARN: Removed duplicated region for block: B:68:0x0455 A[Catch: all -> 0x05af, TryCatch #12 {all -> 0x05af, blocks: (B:63:0x0427, B:66:0x0444, B:68:0x0455, B:69:0x045c, B:71:0x0466, B:74:0x046d, B:75:0x0475, B:78:0x0495, B:86:0x04de, B:89:0x04e5, B:91:0x04ed, B:93:0x04ff, B:123:0x04d4, B:124:0x0434, B:126:0x043a, B:85:0x04b1), top: B:62:0x0427, inners: #3 }] */
        /* JADX WARN: Removed duplicated region for block: B:77:0x0493  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x04b1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r14v2, types: [java.util.Map<java.lang.String, java.lang.String>] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Map<String, String> extractApiFlags$hyperkyc_release(String moduleId, Map<String, String> responseHeaders, String responseBodyRaw, String statusCode, String statusMessage) {
            String canonicalName;
            String str;
            String str2;
            Object m1202constructorimpl;
            String str3;
            String str4;
            String canonicalName2;
            String str5;
            String className;
            String str6;
            String str7;
            Object obj;
            Throwable m1205exceptionOrNullimpl;
            String str8;
            String str9;
            String str10;
            String str11;
            Object m1202constructorimpl2;
            String str12;
            String str13;
            Matcher matcher;
            String str14;
            String localizedMessage;
            String className2;
            String className3;
            JSONArray jSONArray;
            StackTraceElement stackTraceElement;
            String str15;
            Matcher matcher2;
            String str16;
            Object m1202constructorimpl3;
            String canonicalName3;
            String className4;
            String str17;
            Object m1202constructorimpl4;
            String canonicalName4;
            String className5;
            String className6;
            int i;
            int i2;
            JSONArray jSONArray2;
            String str18;
            String str19;
            String canonicalName5;
            Matcher matcher3;
            String str20;
            Object m1202constructorimpl5;
            String canonicalName6;
            String className7;
            String className8;
            String className9;
            String className10;
            String str21 = responseBodyRaw;
            Intrinsics.checkNotNullParameter(moduleId, "moduleId");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            String str22 = "Throwable().stackTrace";
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement2 == null || (className10 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className10, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls = linkedHashMap.getClass();
                canonicalName = cls != null ? cls.getCanonicalName() : null;
                if (canonicalName == null) {
                    canonicalName = "N/A";
                }
            }
            Matcher matcher4 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName);
            String str23 = "";
            if (matcher4.find()) {
                canonicalName = matcher4.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
            }
            Unit unit = Unit.INSTANCE;
            if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                canonicalName = canonicalName.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(canonicalName);
            sb.append(" - ");
            String str24 = "extractApiFlags() called with: moduleId = [" + moduleId + "], responseBodyRaw = [" + str21 + "], statusCode = [" + statusCode + "], statusMessage = [" + statusMessage + "], responseHeaders = [" + responseHeaders + "],";
            if (str24 == null) {
                str24 = "null ";
            }
            sb.append(str24);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
                str3 = "packageName";
                str4 = "null cannot be cast to non-null type android.app.Application";
                str = " - ";
                str2 = "null ";
            } else {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    str = " - ";
                    str2 = "null ";
                    try {
                        Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th) {
                        th = th;
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        }
                        String packageName = (String) m1202constructorimpl;
                        if (CoreExtsKt.isDebug()) {
                        }
                        str5 = "replaceAll(\"\")";
                        Result.Companion companion4 = Result.INSTANCE;
                        jSONArray = (JSONArray) (str21 != null ? JSONExtsKt.recursiveGet(new JSONObject(str21), "result.summary.details") : null);
                        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                        HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb2 = new StringBuilder();
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement != null) {
                        }
                        Class<?> cls2 = linkedHashMap.getClass();
                        if (cls2 != null) {
                        }
                        if (r1 == null) {
                        }
                        matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str15);
                        if (matcher2.find()) {
                        }
                        Unit unit2 = Unit.INSTANCE;
                        if (str15.length() > 23) {
                        }
                        sb2.append(str15);
                        String str25 = str;
                        sb2.append(str25);
                        str16 = "extractApiFlags: summaryDetails: " + jSONArray;
                        if (str16 == null) {
                        }
                        sb2.append(str16);
                        sb2.append(' ');
                        sb2.append("");
                        companion5.log(level2, sb2.toString());
                        if (CoreExtsKt.isRelease()) {
                        }
                        if (jSONArray == null) {
                        }
                        obj = Result.m1202constructorimpl(Unit.INSTANCE);
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                        if (m1205exceptionOrNullimpl != null) {
                        }
                        str8 = null;
                        Map<String, String> map = MapsKt.toMap(linkedHashMap);
                        if (map.isEmpty() ^ true) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    str = " - ";
                    str2 = "null ";
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName2 = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    str3 = "packageName";
                    str4 = "null cannot be cast to non-null type android.app.Application";
                } else {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    str3 = "packageName";
                    str4 = "null cannot be cast to non-null type android.app.Application";
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls3 = linkedHashMap.getClass();
                            canonicalName2 = cls3 != null ? cls3.getCanonicalName() : null;
                            if (canonicalName2 == null) {
                                canonicalName2 = "N/A";
                            }
                        }
                        Matcher matcher5 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName2);
                        if (matcher5.find()) {
                            canonicalName2 = matcher5.replaceAll("");
                            str5 = "replaceAll(\"\")";
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, str5);
                        } else {
                            str5 = "replaceAll(\"\")";
                        }
                        Unit unit3 = Unit.INSTANCE;
                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName2 = canonicalName2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb3 = new StringBuilder();
                        String str26 = "extractApiFlags() called with: moduleId = [" + moduleId + "], responseBodyRaw = [" + str21 + "], statusCode = [" + statusCode + "], statusMessage = [" + statusMessage + "], responseHeaders = [" + responseHeaders + "],";
                        if (str26 == null) {
                            str26 = str2;
                        }
                        sb3.append(str26);
                        sb3.append(' ');
                        sb3.append("");
                        Log.println(3, canonicalName2, sb3.toString());
                        Result.Companion companion42 = Result.INSTANCE;
                        jSONArray = (JSONArray) (str21 != null ? JSONExtsKt.recursiveGet(new JSONObject(str21), "result.summary.details") : null);
                        HyperLogger.Level level22 = HyperLogger.Level.DEBUG;
                        HyperLogger companion52 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb22 = new StringBuilder();
                        StackTraceElement[] stackTrace22 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace22, "Throwable().stackTrace");
                        stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace22);
                        if (stackTraceElement != null || (className9 = stackTraceElement.getClassName()) == null || (str15 = StringsKt.substringAfterLast$default(className9, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls22 = linkedHashMap.getClass();
                            String canonicalName7 = cls22 != null ? cls22.getCanonicalName() : null;
                            str15 = canonicalName7 == null ? "N/A" : canonicalName7;
                        }
                        matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str15);
                        if (matcher2.find()) {
                            str15 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str15, str5);
                        }
                        Unit unit22 = Unit.INSTANCE;
                        if (str15.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str15 = str15.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str15, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb22.append(str15);
                        String str252 = str;
                        sb22.append(str252);
                        str16 = "extractApiFlags: summaryDetails: " + jSONArray;
                        if (str16 == null) {
                            str16 = str2;
                        }
                        sb22.append(str16);
                        sb22.append(' ');
                        sb22.append("");
                        companion52.log(level22, sb22.toString());
                        if (CoreExtsKt.isRelease()) {
                            str6 = str3;
                            str7 = str4;
                        } else {
                            try {
                                Result.Companion companion6 = Result.INSTANCE;
                                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                str7 = str4;
                                try {
                                    Intrinsics.checkNotNull(invoke2, str7);
                                    m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                } catch (Throwable th3) {
                                    th = th3;
                                    try {
                                        Result.Companion companion7 = Result.INSTANCE;
                                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                        }
                                        String str27 = (String) m1202constructorimpl3;
                                        if (CoreExtsKt.isDebug()) {
                                        }
                                        if (jSONArray == null) {
                                        }
                                        obj = Result.m1202constructorimpl(Unit.INSTANCE);
                                    } catch (Throwable th4) {
                                        th = th4;
                                        str21 = "Throwable().stackTrace";
                                        str6 = str3;
                                        Result.Companion companion8 = Result.INSTANCE;
                                        obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                                        if (m1205exceptionOrNullimpl != null) {
                                        }
                                        str8 = null;
                                        Map<String, String> map2 = MapsKt.toMap(linkedHashMap);
                                        if (map2.isEmpty() ^ true) {
                                        }
                                    }
                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                                    if (m1205exceptionOrNullimpl != null) {
                                    }
                                    str8 = null;
                                    Map<String, String> map22 = MapsKt.toMap(linkedHashMap);
                                    if (map22.isEmpty() ^ true) {
                                    }
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                str7 = str4;
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                m1202constructorimpl3 = "";
                            }
                            String str272 = (String) m1202constructorimpl3;
                            if (CoreExtsKt.isDebug()) {
                                str6 = str3;
                            } else {
                                str6 = str3;
                                try {
                                    Intrinsics.checkNotNullExpressionValue(str272, str6);
                                    str21 = null;
                                    if (StringsKt.contains$default((CharSequence) str272, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                        Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                        if (stackTraceElement4 == null || (className4 = stackTraceElement4.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                            Class<?> cls4 = linkedHashMap.getClass();
                                            canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                                            if (canonicalName3 == null) {
                                                canonicalName3 = "N/A";
                                            }
                                        }
                                        Matcher matcher6 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName3);
                                        if (matcher6.find()) {
                                            canonicalName3 = matcher6.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(canonicalName3, str5);
                                        }
                                        Unit unit4 = Unit.INSTANCE;
                                        if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            canonicalName3 = canonicalName3.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb4 = new StringBuilder();
                                        String str28 = "extractApiFlags: summaryDetails: " + jSONArray;
                                        if (str28 == null) {
                                            str28 = str2;
                                        }
                                        sb4.append(str28);
                                        sb4.append(' ');
                                        sb4.append("");
                                        str21 = sb4.toString();
                                        Log.println(3, canonicalName3, str21);
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                    str21 = str22;
                                    Result.Companion companion82 = Result.INSTANCE;
                                    obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                                    if (m1205exceptionOrNullimpl != null) {
                                    }
                                    str8 = null;
                                    Map<String, String> map222 = MapsKt.toMap(linkedHashMap);
                                    if (map222.isEmpty() ^ true) {
                                    }
                                }
                            }
                        }
                        if (jSONArray == null) {
                            int length = jSONArray.length();
                            int i3 = 0;
                            while (i3 < length) {
                                JSONObject optJSONObject = jSONArray.optJSONObject(i3);
                                if (optJSONObject != null) {
                                    Intrinsics.checkNotNullExpressionValue(optJSONObject, "optJSONObject(i)");
                                    HyperLogger.Level level3 = HyperLogger.Level.DEBUG;
                                    HyperLogger companion9 = HyperLogger.INSTANCE.getInstance();
                                    StringBuilder sb5 = new StringBuilder();
                                    i = length;
                                    StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace5, str22);
                                    StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                                    if (stackTraceElement5 == null || (className8 = stackTraceElement5.getClassName()) == null) {
                                        i2 = i3;
                                        jSONArray2 = jSONArray;
                                        str19 = str22;
                                    } else {
                                        i2 = i3;
                                        jSONArray2 = jSONArray;
                                        str19 = str22;
                                        try {
                                            canonicalName5 = StringsKt.substringAfterLast$default(className8, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                            if (canonicalName5 != null) {
                                                matcher3 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName5);
                                                if (matcher3.find()) {
                                                    canonicalName5 = matcher3.replaceAll("");
                                                    Intrinsics.checkNotNullExpressionValue(canonicalName5, str5);
                                                }
                                                Unit unit5 = Unit.INSTANCE;
                                                if (canonicalName5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                    canonicalName5 = canonicalName5.substring(0, 23);
                                                    Intrinsics.checkNotNullExpressionValue(canonicalName5, "this as java.lang.String…ing(startIndex, endIndex)");
                                                }
                                                sb5.append(canonicalName5);
                                                sb5.append(str252);
                                                str20 = "extractApiFlags: adding: " + optJSONObject + " to flags";
                                                if (str20 == null) {
                                                    str20 = str2;
                                                }
                                                sb5.append(str20);
                                                sb5.append(' ');
                                                sb5.append("");
                                                companion9.log(level3, sb5.toString());
                                                if (!CoreExtsKt.isRelease()) {
                                                    try {
                                                        Result.Companion companion10 = Result.INSTANCE;
                                                        Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                                        Intrinsics.checkNotNull(invoke3, str7);
                                                        m1202constructorimpl5 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                                                    } catch (Throwable th7) {
                                                        Result.Companion companion11 = Result.INSTANCE;
                                                        m1202constructorimpl5 = Result.m1202constructorimpl(ResultKt.createFailure(th7));
                                                    }
                                                    if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                                                        m1202constructorimpl5 = "";
                                                    }
                                                    String str29 = (String) m1202constructorimpl5;
                                                    if (CoreExtsKt.isDebug()) {
                                                        Intrinsics.checkNotNullExpressionValue(str29, str6);
                                                        if (StringsKt.contains$default((CharSequence) str29, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                                            StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                                            str18 = str19;
                                                            Intrinsics.checkNotNullExpressionValue(stackTrace6, str18);
                                                            StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                                            if (stackTraceElement6 == null || (className7 = stackTraceElement6.getClassName()) == null || (canonicalName6 = StringsKt.substringAfterLast$default(className7, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                                                Class<?> cls5 = linkedHashMap.getClass();
                                                                canonicalName6 = cls5 != null ? cls5.getCanonicalName() : null;
                                                                if (canonicalName6 == null) {
                                                                    canonicalName6 = "N/A";
                                                                }
                                                            }
                                                            Matcher matcher7 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName6);
                                                            if (matcher7.find()) {
                                                                canonicalName6 = matcher7.replaceAll("");
                                                                Intrinsics.checkNotNullExpressionValue(canonicalName6, str5);
                                                            }
                                                            Unit unit6 = Unit.INSTANCE;
                                                            if (canonicalName6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                canonicalName6 = canonicalName6.substring(0, 23);
                                                                Intrinsics.checkNotNullExpressionValue(canonicalName6, "this as java.lang.String…ing(startIndex, endIndex)");
                                                            }
                                                            StringBuilder sb6 = new StringBuilder();
                                                            String str30 = "extractApiFlags: adding: " + optJSONObject + " to flags";
                                                            if (str30 == null) {
                                                                str30 = str2;
                                                            }
                                                            sb6.append(str30);
                                                            sb6.append(' ');
                                                            sb6.append("");
                                                            Log.println(3, canonicalName6, sb6.toString());
                                                            Boolean.valueOf(CoreExtsKt.putIfNotNull(linkedHashMap, optJSONObject.getString("code"), optJSONObject.getString("message")));
                                                        }
                                                    }
                                                }
                                                str18 = str19;
                                                Boolean.valueOf(CoreExtsKt.putIfNotNull(linkedHashMap, optJSONObject.getString("code"), optJSONObject.getString("message")));
                                            }
                                        } catch (Throwable th8) {
                                            th = th8;
                                            str21 = str19;
                                            Result.Companion companion822 = Result.INSTANCE;
                                            obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                                            if (m1205exceptionOrNullimpl != null) {
                                            }
                                            str8 = null;
                                            Map<String, String> map2222 = MapsKt.toMap(linkedHashMap);
                                            if (map2222.isEmpty() ^ true) {
                                            }
                                        }
                                    }
                                    Class<?> cls6 = linkedHashMap.getClass();
                                    canonicalName5 = cls6 != null ? cls6.getCanonicalName() : null;
                                    if (canonicalName5 == null) {
                                        canonicalName5 = "N/A";
                                    }
                                    matcher3 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName5);
                                    if (matcher3.find()) {
                                    }
                                    Unit unit52 = Unit.INSTANCE;
                                    if (canonicalName5.length() > 23) {
                                        canonicalName5 = canonicalName5.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(canonicalName5, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    sb5.append(canonicalName5);
                                    sb5.append(str252);
                                    str20 = "extractApiFlags: adding: " + optJSONObject + " to flags";
                                    if (str20 == null) {
                                    }
                                    sb5.append(str20);
                                    sb5.append(' ');
                                    sb5.append("");
                                    companion9.log(level3, sb5.toString());
                                    if (!CoreExtsKt.isRelease()) {
                                    }
                                    str18 = str19;
                                    Boolean.valueOf(CoreExtsKt.putIfNotNull(linkedHashMap, optJSONObject.getString("code"), optJSONObject.getString("message")));
                                } else {
                                    i = length;
                                    i2 = i3;
                                    jSONArray2 = jSONArray;
                                    str18 = str22;
                                }
                                length = i;
                                str22 = str18;
                                jSONArray = jSONArray2;
                                i3 = i2 + 1;
                            }
                            str21 = str22;
                        } else {
                            str21 = "Throwable().stackTrace";
                            HyperLogger.Level level4 = HyperLogger.Level.DEBUG;
                            HyperLogger companion12 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb7 = new StringBuilder();
                            StackTraceElement[] stackTrace7 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace7, str21);
                            StackTraceElement stackTraceElement7 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace7);
                            if (stackTraceElement7 == null || (className6 = stackTraceElement7.getClassName()) == null || (str17 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls7 = linkedHashMap.getClass();
                                String canonicalName8 = cls7 != null ? cls7.getCanonicalName() : null;
                                str17 = canonicalName8 == null ? "N/A" : canonicalName8;
                            }
                            Matcher matcher8 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str17);
                            if (matcher8.find()) {
                                str17 = matcher8.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str17, str5);
                            }
                            Unit unit7 = Unit.INSTANCE;
                            if (str17.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str17 = str17.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str17, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            sb7.append(str17);
                            sb7.append(str252);
                            sb7.append("extractApiFlags: no summaryDetails, adding from responseHeaders and responseBody");
                            sb7.append(' ');
                            sb7.append("");
                            companion12.log(level4, sb7.toString());
                            if (!CoreExtsKt.isRelease()) {
                                try {
                                    Result.Companion companion13 = Result.INSTANCE;
                                    Object invoke4 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                    Intrinsics.checkNotNull(invoke4, str7);
                                    m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
                                } catch (Throwable th9) {
                                    Result.Companion companion14 = Result.INSTANCE;
                                    m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th9));
                                }
                                if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                    m1202constructorimpl4 = "";
                                }
                                String str31 = (String) m1202constructorimpl4;
                                if (CoreExtsKt.isDebug()) {
                                    Intrinsics.checkNotNullExpressionValue(str31, str6);
                                    if (StringsKt.contains$default((CharSequence) str31, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                        StackTraceElement[] stackTrace8 = new Throwable().getStackTrace();
                                        Intrinsics.checkNotNullExpressionValue(stackTrace8, str21);
                                        StackTraceElement stackTraceElement8 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace8);
                                        if (stackTraceElement8 == null || (className5 = stackTraceElement8.getClassName()) == null || (canonicalName4 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                            Class<?> cls8 = linkedHashMap.getClass();
                                            canonicalName4 = cls8 != null ? cls8.getCanonicalName() : null;
                                            if (canonicalName4 == null) {
                                                canonicalName4 = "N/A";
                                            }
                                        }
                                        Matcher matcher9 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName4);
                                        if (matcher9.find()) {
                                            canonicalName4 = matcher9.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(canonicalName4, str5);
                                        }
                                        Unit unit8 = Unit.INSTANCE;
                                        if (canonicalName4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            canonicalName4 = canonicalName4.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(canonicalName4, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        Log.println(3, canonicalName4, "extractApiFlags: no summaryDetails, adding from responseHeaders and responseBody ");
                                    }
                                }
                            }
                            if (responseHeaders != null) {
                                String str32 = responseHeaders.get("code");
                                String urlDecode$default = str32 != null ? URLExtsKt.urlDecode$default(str32, null, 1, null) : null;
                                String str33 = responseHeaders.get("message");
                                if (!CoreExtsKt.putIfNotNull(linkedHashMap, urlDecode$default, str33 != null ? URLExtsKt.urlDecode$default(str33, null, 1, null) : null)) {
                                    Unit unit9 = Unit.INSTANCE;
                                    Unit unit10 = Unit.INSTANCE;
                                }
                            }
                            if (responseBodyRaw != null) {
                                if (!CoreExtsKt.putIfNotNull(linkedHashMap, JSONExtsKt.extractJsonValue(responseBodyRaw, "result.error.code"), JSONExtsKt.extractJsonValue(responseBodyRaw, "result.error.message")) && !CoreExtsKt.putIfNotNull(linkedHashMap, JSONExtsKt.extractJsonValue(responseBodyRaw, "error.code"), JSONExtsKt.extractJsonValue(responseBodyRaw, "error.message")) && !CoreExtsKt.putIfNotNull(linkedHashMap, JSONExtsKt.extractJsonValue(responseBodyRaw, Keys.STATUS_CODE), JSONExtsKt.extractJsonValue(responseBodyRaw, "result.error"))) {
                                    Unit unit11 = Unit.INSTANCE;
                                    Unit unit12 = Unit.INSTANCE;
                                }
                            }
                            CoreExtsKt.putIfNotNull(linkedHashMap, statusCode, statusMessage);
                        }
                        obj = Result.m1202constructorimpl(Unit.INSTANCE);
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
                        if (m1205exceptionOrNullimpl != null) {
                            HyperLogger.Level level5 = HyperLogger.Level.ERROR;
                            HyperLogger companion15 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb8 = new StringBuilder();
                            StackTraceElement[] stackTrace9 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace9, str21);
                            StackTraceElement stackTraceElement9 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace9);
                            if (stackTraceElement9 == null || (className3 = stackTraceElement9.getClassName()) == null) {
                                str9 = str21;
                            } else {
                                str9 = str21;
                                str10 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            }
                            Class<?> cls9 = linkedHashMap.getClass();
                            String canonicalName9 = cls9 != null ? cls9.getCanonicalName() : null;
                            str10 = canonicalName9 == null ? "N/A" : canonicalName9;
                            Matcher matcher10 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str10);
                            if (matcher10.find()) {
                                str10 = matcher10.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str10, str5);
                            }
                            Unit unit13 = Unit.INSTANCE;
                            if (str10.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str10 = str10.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str10, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            sb8.append(str10);
                            sb8.append(str252);
                            String str34 = "Failed creating api flags for " + moduleId;
                            if (str34 == null) {
                                str34 = str2;
                            }
                            sb8.append(str34);
                            sb8.append(' ');
                            String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                            if (localizedMessage2 != null) {
                                str11 = '\n' + localizedMessage2;
                            } else {
                                str11 = "";
                            }
                            sb8.append(str11);
                            companion15.log(level5, sb8.toString());
                            CoreExtsKt.isRelease();
                            try {
                                Result.Companion companion16 = Result.INSTANCE;
                                Object invoke5 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke5, str7);
                                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke5).getPackageName());
                            } catch (Throwable th10) {
                                Result.Companion companion17 = Result.INSTANCE;
                                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th10));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                m1202constructorimpl2 = "";
                            }
                            String str35 = (String) m1202constructorimpl2;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(str35, str6);
                                if (StringsKt.contains$default((CharSequence) str35, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                    StackTraceElement[] stackTrace10 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace10, str9);
                                    StackTraceElement stackTraceElement10 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace10);
                                    if (stackTraceElement10 == null || (className2 = stackTraceElement10.getClassName()) == null) {
                                        str8 = null;
                                    } else {
                                        str8 = null;
                                        str12 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    }
                                    Class<?> cls10 = linkedHashMap.getClass();
                                    str12 = cls10 != null ? cls10.getCanonicalName() : str8;
                                    if (str12 == null) {
                                        str13 = "N/A";
                                        matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str13);
                                        if (matcher.find()) {
                                            str13 = matcher.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(str13, str5);
                                        }
                                        Unit unit14 = Unit.INSTANCE;
                                        if (str13.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            str13 = str13.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str13, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb9 = new StringBuilder();
                                        str14 = "Failed creating api flags for " + moduleId;
                                        if (str14 == null) {
                                            str14 = str2;
                                        }
                                        sb9.append(str14);
                                        sb9.append(' ');
                                        localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str8;
                                        if (localizedMessage != null) {
                                            str23 = '\n' + localizedMessage;
                                        }
                                        sb9.append(str23);
                                        Log.println(6, str13, sb9.toString());
                                        Map<String, String> map22222 = MapsKt.toMap(linkedHashMap);
                                        return map22222.isEmpty() ^ true ? map22222 : str8;
                                    }
                                    str13 = str12;
                                    matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str13);
                                    if (matcher.find()) {
                                    }
                                    Unit unit142 = Unit.INSTANCE;
                                    if (str13.length() > 23) {
                                        str13 = str13.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str13, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb92 = new StringBuilder();
                                    str14 = "Failed creating api flags for " + moduleId;
                                    if (str14 == null) {
                                    }
                                    sb92.append(str14);
                                    sb92.append(' ');
                                    if (m1205exceptionOrNullimpl == null) {
                                    }
                                    if (localizedMessage != null) {
                                    }
                                    sb92.append(str23);
                                    Log.println(6, str13, sb92.toString());
                                    Map<String, String> map222222 = MapsKt.toMap(linkedHashMap);
                                    if (map222222.isEmpty() ^ true) {
                                    }
                                }
                            }
                        }
                        str8 = null;
                        Map<String, String> map2222222 = MapsKt.toMap(linkedHashMap);
                        if (map2222222.isEmpty() ^ true) {
                        }
                    }
                }
            }
            str5 = "replaceAll(\"\")";
            Result.Companion companion422 = Result.INSTANCE;
            jSONArray = (JSONArray) (str21 != null ? JSONExtsKt.recursiveGet(new JSONObject(str21), "result.summary.details") : null);
            HyperLogger.Level level222 = HyperLogger.Level.DEBUG;
            HyperLogger companion522 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb222 = new StringBuilder();
            StackTraceElement[] stackTrace222 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace222, "Throwable().stackTrace");
            stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace222);
            if (stackTraceElement != null) {
            }
            Class<?> cls222 = linkedHashMap.getClass();
            if (cls222 != null) {
            }
            if (canonicalName7 == null) {
            }
            matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str15);
            if (matcher2.find()) {
            }
            Unit unit222 = Unit.INSTANCE;
            if (str15.length() > 23) {
                str15 = str15.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str15, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb222.append(str15);
            String str2522 = str;
            sb222.append(str2522);
            str16 = "extractApiFlags: summaryDetails: " + jSONArray;
            if (str16 == null) {
            }
            sb222.append(str16);
            sb222.append(' ');
            sb222.append("");
            companion522.log(level222, sb222.toString());
            if (CoreExtsKt.isRelease()) {
            }
            if (jSONArray == null) {
            }
            obj = Result.m1202constructorimpl(Unit.INSTANCE);
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
            if (m1205exceptionOrNullimpl != null) {
            }
            str8 = null;
            Map<String, String> map22222222 = MapsKt.toMap(linkedHashMap);
            if (map22222222.isEmpty() ^ true) {
            }
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B{\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u00122\b\u0002\u0010\n\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\f¢\u0006\u0002\b\r0\u000bj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\f¢\u0006\u0002\b\r`\u000e¢\u0006\u0002\u0010\u000fJ\u0006\u0010\b\u001a\u00020\u0003J\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003J\u000e\u0010#\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b$J\u0010\u0010%\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b&J\u0010\u0010'\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b(J\u0010\u0010)\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b*J\u0010\u0010+\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b,J\u000e\u0010-\u001a\u00020\tHÀ\u0003¢\u0006\u0002\b.J8\u0010/\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\f¢\u0006\u0002\b\r0\u000bj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\f¢\u0006\u0002\b\r`\u000eHÀ\u0003¢\u0006\u0002\b0J\u0081\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\t22\b\u0002\u0010\n\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\f¢\u0006\u0002\b\r0\u000bj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\f¢\u0006\u0002\b\r`\u000eHÆ\u0001J\b\u00102\u001a\u0004\u0018\u00010\u0003J\b\u00103\u001a\u0004\u0018\u00010\u0003J\t\u00104\u001a\u00020\tHÖ\u0001J\u0013\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u00108\u001a\u00020\tHÖ\u0001J\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003J\u0006\u0010\u0002\u001a\u00020\u0003J\t\u00109\u001a\u00020\u0003HÖ\u0001J\u0019\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\tHÖ\u0001R\u001a\u0010\b\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015RD\u0010\n\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\f¢\u0006\u0002\b\r0\u000bj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\f¢\u0006\u0002\b\r`\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u0006?"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$CountryResult;", "Landroid/os/Parcelable;", "tag", "", "id", "name", "region", "baseUrl", AppConstants.ATTEMPTS_KEY, "", "variables", "Ljava/util/HashMap;", "", "Lkotlinx/parcelize/RawValue;", "Lkotlin/collections/HashMap;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/HashMap;)V", "getAttempts$hyperkyc_release", "()I", "setAttempts$hyperkyc_release", "(I)V", "getBaseUrl$hyperkyc_release", "()Ljava/lang/String;", "setBaseUrl$hyperkyc_release", "(Ljava/lang/String;)V", "getId$hyperkyc_release", "setId$hyperkyc_release", "getName$hyperkyc_release", "setName$hyperkyc_release", "getRegion$hyperkyc_release", "setRegion$hyperkyc_release", "getTag$hyperkyc_release", "getVariables$hyperkyc_release", "()Ljava/util/HashMap;", "setVariables$hyperkyc_release", "(Ljava/util/HashMap;)V", "component1", "component1$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "component4", "component4$hyperkyc_release", "component5", "component5$hyperkyc_release", "component6", "component6$hyperkyc_release", "component7", "component7$hyperkyc_release", "copy", AnalyticsLogger.Keys.COUNTRY_ID, WorkflowAPIParams.COUNTRY_NAME, "describeContents", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class CountryResult implements Parcelable {
        public static final Parcelable.Creator<CountryResult> CREATOR = new Creator();
        private int attempts;
        private String baseUrl;
        private String id;
        private String name;
        private String region;

        @SerializedName("moduleId")
        private final String tag;
        private HashMap<String, Object> variables;

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<CountryResult> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final CountryResult createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                String readString3 = parcel.readString();
                String readString4 = parcel.readString();
                String readString5 = parcel.readString();
                int readInt = parcel.readInt();
                int readInt2 = parcel.readInt();
                HashMap hashMap = new HashMap(readInt2);
                for (int i = 0; i != readInt2; i++) {
                    hashMap.put(parcel.readString(), parcel.readValue(CountryResult.class.getClassLoader()));
                }
                return new CountryResult(readString, readString2, readString3, readString4, readString5, readInt, hashMap);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final CountryResult[] newArray(int i) {
                return new CountryResult[i];
            }
        }

        public static /* synthetic */ CountryResult copy$default(CountryResult countryResult, String str, String str2, String str3, String str4, String str5, int i, HashMap hashMap, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = countryResult.tag;
            }
            if ((i2 & 2) != 0) {
                str2 = countryResult.id;
            }
            String str6 = str2;
            if ((i2 & 4) != 0) {
                str3 = countryResult.name;
            }
            String str7 = str3;
            if ((i2 & 8) != 0) {
                str4 = countryResult.region;
            }
            String str8 = str4;
            if ((i2 & 16) != 0) {
                str5 = countryResult.baseUrl;
            }
            String str9 = str5;
            if ((i2 & 32) != 0) {
                i = countryResult.attempts;
            }
            int i3 = i;
            if ((i2 & 64) != 0) {
                hashMap = countryResult.variables;
            }
            return countryResult.copy(str, str6, str7, str8, str9, i3, hashMap);
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final String getTag() {
            return this.tag;
        }

        /* renamed from: component2$hyperkyc_release, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component3$hyperkyc_release, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component4$hyperkyc_release, reason: from getter */
        public final String getRegion() {
            return this.region;
        }

        public final String component5$hyperkyc_release() {
            return this.baseUrl;
        }

        /* renamed from: component6$hyperkyc_release, reason: from getter */
        public final int getAttempts() {
            return this.attempts;
        }

        public final HashMap<String, Object> component7$hyperkyc_release() {
            return this.variables;
        }

        public final CountryResult copy(String tag, String id2, String name, String region, String baseUrl, int attempts, HashMap<String, Object> variables) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(variables, "variables");
            return new CountryResult(tag, id2, name, region, baseUrl, attempts, variables);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CountryResult)) {
                return false;
            }
            CountryResult countryResult = (CountryResult) other;
            return Intrinsics.areEqual(this.tag, countryResult.tag) && Intrinsics.areEqual(this.id, countryResult.id) && Intrinsics.areEqual(this.name, countryResult.name) && Intrinsics.areEqual(this.region, countryResult.region) && Intrinsics.areEqual(this.baseUrl, countryResult.baseUrl) && this.attempts == countryResult.attempts && Intrinsics.areEqual(this.variables, countryResult.variables);
        }

        public int hashCode() {
            int hashCode = this.tag.hashCode() * 31;
            String str = this.id;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.name;
            int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.region;
            int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.baseUrl;
            return ((((hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31) + this.attempts) * 31) + this.variables.hashCode();
        }

        public String toString() {
            return "CountryResult(tag=" + this.tag + ", id=" + this.id + ", name=" + this.name + ", region=" + this.region + ", baseUrl=" + this.baseUrl + ", attempts=" + this.attempts + ", variables=" + this.variables + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.tag);
            parcel.writeString(this.id);
            parcel.writeString(this.name);
            parcel.writeString(this.region);
            parcel.writeString(this.baseUrl);
            parcel.writeInt(this.attempts);
            HashMap<String, Object> hashMap = this.variables;
            parcel.writeInt(hashMap.size());
            for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeValue(entry.getValue());
            }
        }

        public CountryResult(String tag, String str, String str2, String str3, String str4, int i, HashMap<String, Object> variables) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(variables, "variables");
            this.tag = tag;
            this.id = str;
            this.name = str2;
            this.region = str3;
            this.baseUrl = str4;
            this.attempts = i;
            this.variables = variables;
        }

        public final /* synthetic */ String getTag$hyperkyc_release() {
            return this.tag;
        }

        public final /* synthetic */ String getId$hyperkyc_release() {
            return this.id;
        }

        public final /* synthetic */ void setId$hyperkyc_release(String str) {
            this.id = str;
        }

        public final /* synthetic */ String getName$hyperkyc_release() {
            return this.name;
        }

        public final /* synthetic */ void setName$hyperkyc_release(String str) {
            this.name = str;
        }

        public final /* synthetic */ String getRegion$hyperkyc_release() {
            return this.region;
        }

        public final /* synthetic */ void setRegion$hyperkyc_release(String str) {
            this.region = str;
        }

        public final /* synthetic */ String getBaseUrl$hyperkyc_release() {
            return this.baseUrl;
        }

        public final /* synthetic */ void setBaseUrl$hyperkyc_release(String str) {
            this.baseUrl = str;
        }

        public final /* synthetic */ int getAttempts$hyperkyc_release() {
            return this.attempts;
        }

        public final /* synthetic */ void setAttempts$hyperkyc_release(int i) {
            this.attempts = i;
        }

        public /* synthetic */ CountryResult(String str, String str2, String str3, String str4, String str5, int i, HashMap hashMap, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) == 0 ? str5 : null, (i2 & 32) != 0 ? 0 : i, (i2 & 64) != 0 ? new HashMap() : hashMap);
        }

        /* renamed from: getVariables$hyperkyc_release, reason: from getter */
        public final /* synthetic */ HashMap getVariables() {
            return this.variables;
        }

        public final /* synthetic */ void setVariables$hyperkyc_release(HashMap hashMap) {
            Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
            this.variables = hashMap;
        }

        public final String tag() {
            return this.tag;
        }

        public final String countryId() {
            return this.id;
        }

        public final String countryName() {
            return this.name;
        }

        public final String region() {
            return this.region;
        }

        /* renamed from: baseUrl, reason: from getter */
        public final String getBaseUrl() {
            return this.baseUrl;
        }

        public final String attempts() {
            return String.valueOf(this.attempts);
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u008f\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u00122\b\u0002\u0010\u000b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\r¢\u0006\u0002\b\u000e0\fj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\r¢\u0006\u0002\b\u000e`\u000f\u0012\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011\u0012\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0011¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0011H\u0000¢\u0006\u0002\b(J\u0006\u0010\t\u001a\u00020\u0003J\u000e\u0010)\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b*J\u000e\u0010+\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b,J\u001e\u0010-\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bHÀ\u0003¢\u0006\u0002\b.J\u000e\u0010/\u001a\u00020\nHÀ\u0003¢\u0006\u0002\b0J8\u00101\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\r¢\u0006\u0002\b\u000e0\fj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\r¢\u0006\u0002\b\u000e`\u000fHÀ\u0003¢\u0006\u0002\b2J\u0016\u00103\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011HÀ\u0003¢\u0006\u0002\b4J\u0016\u00105\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0011HÀ\u0003¢\u0006\u0002\b6J\u0099\u0001\u00107\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0018\b\u0002\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b2\b\b\u0002\u0010\t\u001a\u00020\n22\b\u0002\u0010\u000b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\r¢\u0006\u0002\b\u000e0\fj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\r¢\u0006\u0002\b\u000e`\u000f2\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00112\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0011HÆ\u0001J\t\u00108\u001a\u00020\nHÖ\u0001J\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011J\u0006\u0010\u0004\u001a\u00020\u0003J\u0013\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010<\u001a\u00020\nHÖ\u0001J\u0013\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011H\u0000¢\u0006\u0002\b=J\u0006\u0010\u0002\u001a\u00020\u0003J\t\u0010>\u001a\u00020\u0003HÖ\u0001J\u0019\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020\nHÖ\u0001R\"\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0011X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\t\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0004\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\"\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0016\"\u0004\b\"\u0010\u0018R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010 RD\u0010\u000b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\r¢\u0006\u0002\b\u000e0\fj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\r¢\u0006\u0002\b\u000e`\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006D"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$DocResult;", "Landroid/os/Parcelable;", "tag", "", AnalyticsLogger.Keys.DOCUMENT_ID, "docDataList", "Ljava/util/ArrayList;", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$DocData;", "Lkotlin/collections/ArrayList;", AppConstants.ATTEMPTS_KEY, "", "variables", "Ljava/util/HashMap;", "", "Lkotlinx/parcelize/RawValue;", "Lkotlin/collections/HashMap;", "requestIds", "", "apiFlags", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$ApiFlags;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;ILjava/util/HashMap;Ljava/util/List;Ljava/util/List;)V", "getApiFlags$hyperkyc_release", "()Ljava/util/List;", "setApiFlags$hyperkyc_release", "(Ljava/util/List;)V", "getAttempts$hyperkyc_release", "()I", "setAttempts$hyperkyc_release", "(I)V", "getDocDataList$hyperkyc_release", "()Ljava/util/ArrayList;", "getDocumentId$hyperkyc_release", "()Ljava/lang/String;", "getRequestIds$hyperkyc_release", "setRequestIds$hyperkyc_release", "getTag$hyperkyc_release", "getVariables$hyperkyc_release", "()Ljava/util/HashMap;", "setVariables$hyperkyc_release", "(Ljava/util/HashMap;)V", "apiFlags$hyperkyc_release", "component1", "component1$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "component4", "component4$hyperkyc_release", "component5", "component5$hyperkyc_release", "component6", "component6$hyperkyc_release", "component7", "component7$hyperkyc_release", "copy", "describeContents", "equals", "", "other", "hashCode", "requestIds$hyperkyc_release", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class DocResult implements Parcelable {
        public static final Parcelable.Creator<DocResult> CREATOR = new Creator();
        private List<ApiFlags> apiFlags;
        private int attempts;
        private final ArrayList<DocData> docDataList;
        private final String documentId;
        private List<String> requestIds;

        @SerializedName("moduleId")
        private final String tag;
        private HashMap<String, Object> variables;

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<DocResult> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final DocResult createFromParcel(Parcel parcel) {
                ArrayList arrayList;
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                int readInt = parcel.readInt();
                ArrayList arrayList2 = new ArrayList(readInt);
                for (int i = 0; i != readInt; i++) {
                    arrayList2.add(DocData.CREATOR.createFromParcel(parcel));
                }
                int readInt2 = parcel.readInt();
                int readInt3 = parcel.readInt();
                HashMap hashMap = new HashMap(readInt3);
                for (int i2 = 0; i2 != readInt3; i2++) {
                    hashMap.put(parcel.readString(), parcel.readValue(DocResult.class.getClassLoader()));
                }
                ArrayList<String> createStringArrayList = parcel.createStringArrayList();
                if (parcel.readInt() == 0) {
                    arrayList = null;
                } else {
                    int readInt4 = parcel.readInt();
                    ArrayList arrayList3 = new ArrayList(readInt4);
                    for (int i3 = 0; i3 != readInt4; i3++) {
                        arrayList3.add(ApiFlags.CREATOR.createFromParcel(parcel));
                    }
                    arrayList = arrayList3;
                }
                return new DocResult(readString, readString2, arrayList2, readInt2, hashMap, createStringArrayList, arrayList);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final DocResult[] newArray(int i) {
                return new DocResult[i];
            }
        }

        public static /* synthetic */ DocResult copy$default(DocResult docResult, String str, String str2, ArrayList arrayList, int i, HashMap hashMap, List list, List list2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = docResult.tag;
            }
            if ((i2 & 2) != 0) {
                str2 = docResult.documentId;
            }
            String str3 = str2;
            if ((i2 & 4) != 0) {
                arrayList = docResult.docDataList;
            }
            ArrayList arrayList2 = arrayList;
            if ((i2 & 8) != 0) {
                i = docResult.attempts;
            }
            int i3 = i;
            if ((i2 & 16) != 0) {
                hashMap = docResult.variables;
            }
            HashMap hashMap2 = hashMap;
            if ((i2 & 32) != 0) {
                list = docResult.requestIds;
            }
            List list3 = list;
            if ((i2 & 64) != 0) {
                list2 = docResult.apiFlags;
            }
            return docResult.copy(str, str3, arrayList2, i3, hashMap2, list3, list2);
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final String getTag() {
            return this.tag;
        }

        /* renamed from: component2$hyperkyc_release, reason: from getter */
        public final String getDocumentId() {
            return this.documentId;
        }

        public final ArrayList<DocData> component3$hyperkyc_release() {
            return this.docDataList;
        }

        /* renamed from: component4$hyperkyc_release, reason: from getter */
        public final int getAttempts() {
            return this.attempts;
        }

        public final HashMap<String, Object> component5$hyperkyc_release() {
            return this.variables;
        }

        public final List<String> component6$hyperkyc_release() {
            return this.requestIds;
        }

        public final List<ApiFlags> component7$hyperkyc_release() {
            return this.apiFlags;
        }

        public final DocResult copy(String tag, String documentId, ArrayList<DocData> docDataList, int attempts, HashMap<String, Object> variables, List<String> requestIds, List<ApiFlags> apiFlags) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(documentId, "documentId");
            Intrinsics.checkNotNullParameter(docDataList, "docDataList");
            Intrinsics.checkNotNullParameter(variables, "variables");
            return new DocResult(tag, documentId, docDataList, attempts, variables, requestIds, apiFlags);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DocResult)) {
                return false;
            }
            DocResult docResult = (DocResult) other;
            return Intrinsics.areEqual(this.tag, docResult.tag) && Intrinsics.areEqual(this.documentId, docResult.documentId) && Intrinsics.areEqual(this.docDataList, docResult.docDataList) && this.attempts == docResult.attempts && Intrinsics.areEqual(this.variables, docResult.variables) && Intrinsics.areEqual(this.requestIds, docResult.requestIds) && Intrinsics.areEqual(this.apiFlags, docResult.apiFlags);
        }

        public int hashCode() {
            int hashCode = ((((((((this.tag.hashCode() * 31) + this.documentId.hashCode()) * 31) + this.docDataList.hashCode()) * 31) + this.attempts) * 31) + this.variables.hashCode()) * 31;
            List<String> list = this.requestIds;
            int hashCode2 = (hashCode + (list == null ? 0 : list.hashCode())) * 31;
            List<ApiFlags> list2 = this.apiFlags;
            return hashCode2 + (list2 != null ? list2.hashCode() : 0);
        }

        public String toString() {
            return "DocResult(tag=" + this.tag + ", documentId=" + this.documentId + ", docDataList=" + this.docDataList + ", attempts=" + this.attempts + ", variables=" + this.variables + ", requestIds=" + this.requestIds + ", apiFlags=" + this.apiFlags + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.tag);
            parcel.writeString(this.documentId);
            ArrayList<DocData> arrayList = this.docDataList;
            parcel.writeInt(arrayList.size());
            Iterator<DocData> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().writeToParcel(parcel, flags);
            }
            parcel.writeInt(this.attempts);
            HashMap<String, Object> hashMap = this.variables;
            parcel.writeInt(hashMap.size());
            for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeValue(entry.getValue());
            }
            parcel.writeStringList(this.requestIds);
            List<ApiFlags> list = this.apiFlags;
            if (list == null) {
                parcel.writeInt(0);
                return;
            }
            parcel.writeInt(1);
            parcel.writeInt(list.size());
            Iterator<ApiFlags> it2 = list.iterator();
            while (it2.hasNext()) {
                it2.next().writeToParcel(parcel, flags);
            }
        }

        public DocResult(String tag, String documentId, ArrayList<DocData> docDataList, int i, HashMap<String, Object> variables, List<String> list, List<ApiFlags> list2) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(documentId, "documentId");
            Intrinsics.checkNotNullParameter(docDataList, "docDataList");
            Intrinsics.checkNotNullParameter(variables, "variables");
            this.tag = tag;
            this.documentId = documentId;
            this.docDataList = docDataList;
            this.attempts = i;
            this.variables = variables;
            this.requestIds = list;
            this.apiFlags = list2;
        }

        public final /* synthetic */ String getTag$hyperkyc_release() {
            return this.tag;
        }

        public final /* synthetic */ String getDocumentId$hyperkyc_release() {
            return this.documentId;
        }

        /* renamed from: getDocDataList$hyperkyc_release, reason: from getter */
        public final /* synthetic */ ArrayList getDocDataList() {
            return this.docDataList;
        }

        public final /* synthetic */ int getAttempts$hyperkyc_release() {
            return this.attempts;
        }

        public final void setAttempts$hyperkyc_release(int i) {
            this.attempts = i;
        }

        public /* synthetic */ DocResult(String str, String str2, ArrayList arrayList, int i, HashMap hashMap, List list, List list2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, arrayList, (i2 & 8) != 0 ? 0 : i, (i2 & 16) != 0 ? new HashMap() : hashMap, (i2 & 32) != 0 ? null : list, (i2 & 64) != 0 ? null : list2);
        }

        /* renamed from: getVariables$hyperkyc_release, reason: from getter */
        public final /* synthetic */ HashMap getVariables() {
            return this.variables;
        }

        public final void setVariables$hyperkyc_release(HashMap<String, Object> hashMap) {
            Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
            this.variables = hashMap;
        }

        /* renamed from: getRequestIds$hyperkyc_release, reason: from getter */
        public final /* synthetic */ List getRequestIds() {
            return this.requestIds;
        }

        public final /* synthetic */ void setRequestIds$hyperkyc_release(List list) {
            this.requestIds = list;
        }

        /* renamed from: getApiFlags$hyperkyc_release, reason: from getter */
        public final /* synthetic */ List getApiFlags() {
            return this.apiFlags;
        }

        public final /* synthetic */ void setApiFlags$hyperkyc_release(List list) {
            this.apiFlags = list;
        }

        public final String tag() {
            return this.tag;
        }

        public final String documentId() {
            return this.documentId;
        }

        public final List<DocData> docDataList() {
            return this.docDataList;
        }

        public final String attempts() {
            return String.valueOf(this.attempts);
        }

        public final /* synthetic */ List requestIds$hyperkyc_release() {
            List<String> list = this.requestIds;
            if (list != null) {
                return list;
            }
            ArrayList<DocData> arrayList = this.docDataList;
            ArrayList arrayList2 = new ArrayList();
            Iterator<T> it = arrayList.iterator();
            while (it.hasNext()) {
                String requestId = ((DocData) it.next()).getRequestId();
                if (requestId != null) {
                    arrayList2.add(requestId);
                }
            }
            return arrayList2;
        }

        public final /* synthetic */ List apiFlags$hyperkyc_release() {
            List<ApiFlags> list = this.apiFlags;
            if (list != null) {
                return list;
            }
            ArrayList<DocData> arrayList = this.docDataList;
            ArrayList arrayList2 = new ArrayList();
            Iterator<T> it = arrayList.iterator();
            while (it.hasNext()) {
                ApiFlags apiFlags = ((DocData) it.next()).getApiFlags(this.tag);
                if (apiFlags != null) {
                    arrayList2.add(apiFlags);
                }
            }
            return arrayList2;
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\bC\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 j2\u00020\u0001:\u0001jB¥\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010\u0015J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003J\u0006\u0010\u0006\u001a\u00020\u0003J\u0010\u0010<\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b=J\u0012\u0010>\u001a\u0004\u0018\u00010\u0011HÀ\u0003¢\u0006\u0004\b?\u0010!J\u0012\u0010@\u001a\u0004\u0018\u00010\u0011HÀ\u0003¢\u0006\u0004\bA\u0010!J\u0012\u0010B\u001a\u0004\u0018\u00010\u0014HÀ\u0003¢\u0006\u0004\bC\u00108J\u0010\u0010D\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\bEJ\u0010\u0010F\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\bGJ\u000e\u0010H\u001a\u00020\u0007HÀ\u0003¢\u0006\u0002\bIJ\u001c\u0010J\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tHÀ\u0003¢\u0006\u0002\bKJ\u0016\u0010L\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÀ\u0003¢\u0006\u0002\bMJ\u0010\u0010N\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\bOJ\u0010\u0010P\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\bQJ\u0010\u0010R\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\bSJ®\u0001\u0010T\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÆ\u0001¢\u0006\u0002\u0010UJ\t\u0010V\u001a\u00020\u0007HÖ\u0001J\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003J\u0013\u0010W\u001a\u00020X2\b\u0010Y\u001a\u0004\u0018\u00010ZHÖ\u0003J\u0010\u0010[\u001a\u0004\u0018\u00010\\2\u0006\u0010]\u001a\u00020\u0003J\b\u0010^\u001a\u0004\u0018\u00010\u0003J\t\u0010_\u001a\u00020\u0007HÖ\u0001J\u0006\u0010`\u001a\u00020XJ\u0014\u0010a\u001a\u00020X2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00070cJ\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003J\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003J\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bJ\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tJ\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003J\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003J\t\u0010d\u001a\u00020\u0003HÖ\u0001J\u0019\u0010e\u001a\u00020f2\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020\u0007HÖ\u0001R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0006\u001a\u00020\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0080\u000e¢\u0006\u0010\n\u0002\u0010$\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u0011X\u0080\u000e¢\u0006\u0010\n\u0002\u0010$\u001a\u0004\b%\u0010!\"\u0004\b&\u0010#R\"\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0017\"\u0004\b,\u0010\u0019R(\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0017\"\u0004\b2\u0010\u0019R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0017\"\u0004\b4\u0010\u0019R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0017\"\u0004\b6\u0010\u0019R\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0080\u000e¢\u0006\u0010\n\u0002\u0010;\u001a\u0004\b7\u00108\"\u0004\b9\u0010:¨\u0006k"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$DocData;", "Landroid/os/Parcelable;", "side", "", "docImagePath", "action", "attemptsCount", "", "responseHeaders", "", "responseBody", "Lco/hyperverge/hyperkyc/data/network/BaseResponse;", "Lco/hyperverge/hyperkyc/data/network/DocCaptureApiDetail;", "responseBodyRaw", Keys.STATUS_CODE, "statusMessage", "latitude", "", "longitude", "submittedTimestamp", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/Map;Lco/hyperverge/hyperkyc/data/network/BaseResponse;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Long;)V", "getAction$hyperkyc_release", "()Ljava/lang/String;", "setAction$hyperkyc_release", "(Ljava/lang/String;)V", "getAttemptsCount$hyperkyc_release", "()I", "setAttemptsCount$hyperkyc_release", "(I)V", "getDocImagePath$hyperkyc_release", "setDocImagePath$hyperkyc_release", "getLatitude$hyperkyc_release", "()Ljava/lang/Double;", "setLatitude$hyperkyc_release", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "getLongitude$hyperkyc_release", "setLongitude$hyperkyc_release", "getResponseBody$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/network/BaseResponse;", "setResponseBody$hyperkyc_release", "(Lco/hyperverge/hyperkyc/data/network/BaseResponse;)V", "getResponseBodyRaw$hyperkyc_release", "setResponseBodyRaw$hyperkyc_release", "getResponseHeaders$hyperkyc_release", "()Ljava/util/Map;", "setResponseHeaders$hyperkyc_release", "(Ljava/util/Map;)V", "getSide$hyperkyc_release", "setSide$hyperkyc_release", "getStatusCode$hyperkyc_release", "setStatusCode$hyperkyc_release", "getStatusMessage$hyperkyc_release", "setStatusMessage$hyperkyc_release", "getSubmittedTimestamp$hyperkyc_release", "()Ljava/lang/Long;", "setSubmittedTimestamp$hyperkyc_release", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "component1", "component1$hyperkyc_release", "component10", "component10$hyperkyc_release", "component11", "component11$hyperkyc_release", "component12", "component12$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "component4", "component4$hyperkyc_release", "component5", "component5$hyperkyc_release", "component6", "component6$hyperkyc_release", "component7", "component7$hyperkyc_release", "component8", "component8$hyperkyc_release", "component9", "component9$hyperkyc_release", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/Map;Lco/hyperverge/hyperkyc/data/network/BaseResponse;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Long;)Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$DocData;", "describeContents", "equals", "", "other", "", "getApiFlags", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$ApiFlags;", "moduleId", "getRequestId", "hashCode", "isOCRSuccess", "isSuccess", "allowedStatusCodes", "", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class DocData implements Parcelable {
        private String action;
        private int attemptsCount;
        private String docImagePath;
        private Double latitude;
        private Double longitude;
        private BaseResponse<DocCaptureApiDetail> responseBody;
        private String responseBodyRaw;
        private Map<String, String> responseHeaders;
        private String side;
        private String statusCode;
        private String statusMessage;
        private Long submittedTimestamp;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final Parcelable.Creator<DocData> CREATOR = new Creator();

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<DocData> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final DocData createFromParcel(Parcel parcel) {
                LinkedHashMap linkedHashMap;
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                String readString3 = parcel.readString();
                int readInt = parcel.readInt();
                if (parcel.readInt() == 0) {
                    linkedHashMap = null;
                } else {
                    int readInt2 = parcel.readInt();
                    linkedHashMap = new LinkedHashMap(readInt2);
                    for (int i = 0; i != readInt2; i++) {
                        linkedHashMap.put(parcel.readString(), parcel.readString());
                    }
                }
                return new DocData(readString, readString2, readString3, readInt, linkedHashMap, parcel.readInt() == 0 ? null : BaseResponse.CREATOR.createFromParcel(parcel), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? null : Double.valueOf(parcel.readDouble()), parcel.readInt() == 0 ? null : Double.valueOf(parcel.readDouble()), parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong()));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final DocData[] newArray(int i) {
                return new DocData[i];
            }
        }

        public DocData() {
            this(null, null, null, 0, null, null, null, null, null, null, null, null, 4095, null);
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final String getSide() {
            return this.side;
        }

        /* renamed from: component10$hyperkyc_release, reason: from getter */
        public final Double getLatitude() {
            return this.latitude;
        }

        /* renamed from: component11$hyperkyc_release, reason: from getter */
        public final Double getLongitude() {
            return this.longitude;
        }

        /* renamed from: component12$hyperkyc_release, reason: from getter */
        public final Long getSubmittedTimestamp() {
            return this.submittedTimestamp;
        }

        /* renamed from: component2$hyperkyc_release, reason: from getter */
        public final String getDocImagePath() {
            return this.docImagePath;
        }

        public final String component3$hyperkyc_release() {
            return this.action;
        }

        /* renamed from: component4$hyperkyc_release, reason: from getter */
        public final int getAttemptsCount() {
            return this.attemptsCount;
        }

        public final Map<String, String> component5$hyperkyc_release() {
            return this.responseHeaders;
        }

        public final BaseResponse<DocCaptureApiDetail> component6$hyperkyc_release() {
            return this.responseBody;
        }

        /* renamed from: component7$hyperkyc_release, reason: from getter */
        public final String getResponseBodyRaw() {
            return this.responseBodyRaw;
        }

        /* renamed from: component8$hyperkyc_release, reason: from getter */
        public final String getStatusCode() {
            return this.statusCode;
        }

        /* renamed from: component9$hyperkyc_release, reason: from getter */
        public final String getStatusMessage() {
            return this.statusMessage;
        }

        public final DocData copy(String side, String docImagePath, String action, int attemptsCount, Map<String, String> responseHeaders, BaseResponse<DocCaptureApiDetail> responseBody, String responseBodyRaw, String statusCode, String statusMessage, Double latitude, Double longitude, Long submittedTimestamp) {
            return new DocData(side, docImagePath, action, attemptsCount, responseHeaders, responseBody, responseBodyRaw, statusCode, statusMessage, latitude, longitude, submittedTimestamp);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DocData)) {
                return false;
            }
            DocData docData = (DocData) other;
            return Intrinsics.areEqual(this.side, docData.side) && Intrinsics.areEqual(this.docImagePath, docData.docImagePath) && Intrinsics.areEqual(this.action, docData.action) && this.attemptsCount == docData.attemptsCount && Intrinsics.areEqual(this.responseHeaders, docData.responseHeaders) && Intrinsics.areEqual(this.responseBody, docData.responseBody) && Intrinsics.areEqual(this.responseBodyRaw, docData.responseBodyRaw) && Intrinsics.areEqual(this.statusCode, docData.statusCode) && Intrinsics.areEqual(this.statusMessage, docData.statusMessage) && Intrinsics.areEqual((Object) this.latitude, (Object) docData.latitude) && Intrinsics.areEqual((Object) this.longitude, (Object) docData.longitude) && Intrinsics.areEqual(this.submittedTimestamp, docData.submittedTimestamp);
        }

        public int hashCode() {
            String str = this.side;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.docImagePath;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.action;
            int hashCode3 = (((hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.attemptsCount) * 31;
            Map<String, String> map = this.responseHeaders;
            int hashCode4 = (hashCode3 + (map == null ? 0 : map.hashCode())) * 31;
            BaseResponse<DocCaptureApiDetail> baseResponse = this.responseBody;
            int hashCode5 = (hashCode4 + (baseResponse == null ? 0 : baseResponse.hashCode())) * 31;
            String str4 = this.responseBodyRaw;
            int hashCode6 = (hashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.statusCode;
            int hashCode7 = (hashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.statusMessage;
            int hashCode8 = (hashCode7 + (str6 == null ? 0 : str6.hashCode())) * 31;
            Double d = this.latitude;
            int hashCode9 = (hashCode8 + (d == null ? 0 : d.hashCode())) * 31;
            Double d2 = this.longitude;
            int hashCode10 = (hashCode9 + (d2 == null ? 0 : d2.hashCode())) * 31;
            Long l = this.submittedTimestamp;
            return hashCode10 + (l != null ? l.hashCode() : 0);
        }

        public String toString() {
            return "DocData(side=" + this.side + ", docImagePath=" + this.docImagePath + ", action=" + this.action + ", attemptsCount=" + this.attemptsCount + ", responseHeaders=" + this.responseHeaders + ", responseBody=" + this.responseBody + ", responseBodyRaw=" + this.responseBodyRaw + ", statusCode=" + this.statusCode + ", statusMessage=" + this.statusMessage + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", submittedTimestamp=" + this.submittedTimestamp + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.side);
            parcel.writeString(this.docImagePath);
            parcel.writeString(this.action);
            parcel.writeInt(this.attemptsCount);
            Map<String, String> map = this.responseHeaders;
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
            BaseResponse<DocCaptureApiDetail> baseResponse = this.responseBody;
            if (baseResponse == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                baseResponse.writeToParcel(parcel, flags);
            }
            parcel.writeString(this.responseBodyRaw);
            parcel.writeString(this.statusCode);
            parcel.writeString(this.statusMessage);
            Double d = this.latitude;
            if (d == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcel.writeDouble(d.doubleValue());
            }
            Double d2 = this.longitude;
            if (d2 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcel.writeDouble(d2.doubleValue());
            }
            Long l = this.submittedTimestamp;
            if (l == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcel.writeLong(l.longValue());
            }
        }

        public DocData(String str, String str2, String str3, int i, Map<String, String> map, BaseResponse<DocCaptureApiDetail> baseResponse, String str4, String str5, String str6, Double d, Double d2, Long l) {
            this.side = str;
            this.docImagePath = str2;
            this.action = str3;
            this.attemptsCount = i;
            this.responseHeaders = map;
            this.responseBody = baseResponse;
            this.responseBodyRaw = str4;
            this.statusCode = str5;
            this.statusMessage = str6;
            this.latitude = d;
            this.longitude = d2;
            this.submittedTimestamp = l;
        }

        public /* synthetic */ DocData(String str, String str2, String str3, int i, Map map, BaseResponse baseResponse, String str4, String str5, String str6, Double d, Double d2, Long l, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? 0 : i, (i2 & 16) != 0 ? null : map, (i2 & 32) != 0 ? null : baseResponse, (i2 & 64) != 0 ? null : str4, (i2 & 128) != 0 ? null : str5, (i2 & 256) != 0 ? null : str6, (i2 & 512) != 0 ? null : d, (i2 & 1024) != 0 ? null : d2, (i2 & 2048) == 0 ? l : null);
        }

        public final /* synthetic */ String getSide$hyperkyc_release() {
            return this.side;
        }

        public final /* synthetic */ void setSide$hyperkyc_release(String str) {
            this.side = str;
        }

        public final /* synthetic */ String getDocImagePath$hyperkyc_release() {
            return this.docImagePath;
        }

        public final /* synthetic */ void setDocImagePath$hyperkyc_release(String str) {
            this.docImagePath = str;
        }

        public final /* synthetic */ String getAction$hyperkyc_release() {
            return this.action;
        }

        public final /* synthetic */ void setAction$hyperkyc_release(String str) {
            this.action = str;
        }

        public final /* synthetic */ int getAttemptsCount$hyperkyc_release() {
            return this.attemptsCount;
        }

        public final /* synthetic */ void setAttemptsCount$hyperkyc_release(int i) {
            this.attemptsCount = i;
        }

        /* renamed from: getResponseHeaders$hyperkyc_release, reason: from getter */
        public final /* synthetic */ Map getResponseHeaders() {
            return this.responseHeaders;
        }

        public final /* synthetic */ void setResponseHeaders$hyperkyc_release(Map map) {
            this.responseHeaders = map;
        }

        /* renamed from: getResponseBody$hyperkyc_release, reason: from getter */
        public final /* synthetic */ BaseResponse getResponseBody() {
            return this.responseBody;
        }

        public final /* synthetic */ void setResponseBody$hyperkyc_release(BaseResponse baseResponse) {
            this.responseBody = baseResponse;
        }

        public final /* synthetic */ String getResponseBodyRaw$hyperkyc_release() {
            return this.responseBodyRaw;
        }

        public final /* synthetic */ void setResponseBodyRaw$hyperkyc_release(String str) {
            this.responseBodyRaw = str;
        }

        public final /* synthetic */ String getStatusCode$hyperkyc_release() {
            return this.statusCode;
        }

        public final /* synthetic */ void setStatusCode$hyperkyc_release(String str) {
            this.statusCode = str;
        }

        public final /* synthetic */ String getStatusMessage$hyperkyc_release() {
            return this.statusMessage;
        }

        public final /* synthetic */ void setStatusMessage$hyperkyc_release(String str) {
            this.statusMessage = str;
        }

        public final /* synthetic */ Double getLatitude$hyperkyc_release() {
            return this.latitude;
        }

        public final /* synthetic */ void setLatitude$hyperkyc_release(Double d) {
            this.latitude = d;
        }

        public final /* synthetic */ Double getLongitude$hyperkyc_release() {
            return this.longitude;
        }

        public final /* synthetic */ void setLongitude$hyperkyc_release(Double d) {
            this.longitude = d;
        }

        public final /* synthetic */ Long getSubmittedTimestamp$hyperkyc_release() {
            return this.submittedTimestamp;
        }

        public final /* synthetic */ void setSubmittedTimestamp$hyperkyc_release(Long l) {
            this.submittedTimestamp = l;
        }

        public final boolean isSuccess(List<Integer> allowedStatusCodes) {
            Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
            BaseResponse<DocCaptureApiDetail> baseResponse = this.responseBody;
            if (baseResponse != null) {
                return baseResponse.isSuccess(allowedStatusCodes);
            }
            return false;
        }

        public final boolean isOCRSuccess() {
            BaseResponse.Result<DocCaptureApiDetail> result;
            List<DocCaptureApiDetail> details;
            DocCaptureApiDetail docCaptureApiDetail;
            BaseResponse<DocCaptureApiDetail> baseResponse = this.responseBody;
            return ((baseResponse == null || (result = baseResponse.getResult()) == null || (details = result.getDetails()) == null || (docCaptureApiDetail = (DocCaptureApiDetail) CollectionsKt.firstOrNull((List) details)) == null) ? null : docCaptureApiDetail.getFieldsExtracted()) != null;
        }

        public final String getRequestId() {
            BaseResponse.Metadata metadata;
            String requestId;
            BaseResponse<DocCaptureApiDetail> baseResponse = this.responseBody;
            if (baseResponse == null || (metadata = baseResponse.getMetadata()) == null || (requestId = metadata.getRequestId()) == null) {
                return null;
            }
            return CoreExtsKt.nullIfBlank(requestId);
        }

        public final ApiFlags getApiFlags(String moduleId) {
            Intrinsics.checkNotNullParameter(moduleId, "moduleId");
            Map<String, String> extractApiFlags$hyperkyc_release = HyperKycData.INSTANCE.extractApiFlags$hyperkyc_release(moduleId, this.responseHeaders, this.responseBodyRaw, this.statusCode, this.statusMessage);
            if (extractApiFlags$hyperkyc_release == null || extractApiFlags$hyperkyc_release.isEmpty()) {
                return null;
            }
            return new ApiFlags(moduleId, this.side, extractApiFlags$hyperkyc_release);
        }

        public final String side() {
            return this.side;
        }

        public final String docImagePath() {
            return this.docImagePath;
        }

        /* renamed from: action, reason: from getter */
        public final String getAction() {
            return this.action;
        }

        public final Map<String, String> responseHeaders() {
            return this.responseHeaders;
        }

        public final BaseResponse<DocCaptureApiDetail> responseBody() {
            return this.responseBody;
        }

        public final String latitude() {
            Double d = this.latitude;
            if (d != null) {
                return d.toString();
            }
            return null;
        }

        public final String longitude() {
            Double d = this.longitude;
            if (d != null) {
                return d.toString();
            }
            return null;
        }

        public final String submittedTimestamp() {
            Long l = this.submittedTimestamp;
            if (l != null) {
                return l.toString();
            }
            return null;
        }

        public final String attemptsCount() {
            return String.valueOf(this.attemptsCount);
        }

        /* compiled from: HyperKycData.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J'\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000b¨\u0006\f"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$DocData$Companion;", "", "()V", "from", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$DocData;", "gson", "Lcom/google/gson/Gson;", "side", "", "hvResponse", "Lco/hyperverge/hypersnapsdk/objects/HVResponse;", "from$hyperkyc_release", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX WARN: Can't wrap try/catch for region: R(40:1|(3:238|(1:240)(1:243)|(1:242))|7|(1:9)|10|(1:14)|15|(1:17)|18|(1:20)(9:194|195|196|197|198|199|(1:201)|202|(2:204|(9:206|(3:224|(1:226)(1:229)|(1:228))|212|(1:214)|215|(1:219)|220|(1:222)|223)(1:230))(1:231))|21|(3:187|(1:189)(1:193)|(1:191)(1:192))|27|(1:29)|30|(1:34)|35|(1:37)|38|(6:151|152|153|(1:155)|156|(20:158|(9:160|(3:178|(1:180)(1:183)|(1:182))|166|(1:168)|169|(1:173)|174|(1:176)|177)|41|(1:43)(1:150)|44|(1:46)|47|(1:49)(1:149)|50|51|52|53|(21:55|(1:144)(1:59)|61|(1:63)(1:67)|(1:65)(1:66)|68|(1:70)|71|(1:75)|76|(1:78)|79|(1:81)(1:143)|(1:83)(1:142)|84|85|86|87|(1:89)|90|(2:92|(22:94|(1:137)(1:98)|100|(1:102)(1:135)|(17:104|105|(1:107)|108|(1:112)|113|(1:115)(1:134)|116|(1:118)(1:133)|(1:120)|121|122|(1:124)(1:132)|125|(1:127)(1:131)|128|129)|136|105|(0)|108|(2:110|112)|113|(0)(0)|116|(0)(0)|(0)|121|122|(0)(0)|125|(0)(0)|128|129)))(1:145)|138|122|(0)(0)|125|(0)(0)|128|129))|40|41|(0)(0)|44|(0)|47|(0)(0)|50|51|52|53|(0)(0)|138|122|(0)(0)|125|(0)(0)|128|129|(1:(0))) */
            /* JADX WARN: Code restructure failed: missing block: B:147:0x0439, code lost:
            
                r0 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:148:0x043a, code lost:
            
                r2 = kotlin.Result.INSTANCE;
                r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
             */
            /* JADX WARN: Code restructure failed: missing block: B:60:0x0480, code lost:
            
                if (r11 != null) goto L174;
             */
            /* JADX WARN: Code restructure failed: missing block: B:99:0x058f, code lost:
            
                if (r0 != 0) goto L219;
             */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:107:0x05b6  */
            /* JADX WARN: Removed duplicated region for block: B:115:0x05f2  */
            /* JADX WARN: Removed duplicated region for block: B:118:0x0600  */
            /* JADX WARN: Removed duplicated region for block: B:120:0x0608  */
            /* JADX WARN: Removed duplicated region for block: B:124:0x062e  */
            /* JADX WARN: Removed duplicated region for block: B:127:0x063d  */
            /* JADX WARN: Removed duplicated region for block: B:131:0x0642  */
            /* JADX WARN: Removed duplicated region for block: B:132:0x0630  */
            /* JADX WARN: Removed duplicated region for block: B:133:0x0605  */
            /* JADX WARN: Removed duplicated region for block: B:134:0x05f5  */
            /* JADX WARN: Removed duplicated region for block: B:145:0x0625  */
            /* JADX WARN: Removed duplicated region for block: B:149:0x041d  */
            /* JADX WARN: Removed duplicated region for block: B:150:0x03bd  */
            /* JADX WARN: Removed duplicated region for block: B:151:0x02ca A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:189:0x0254  */
            /* JADX WARN: Removed duplicated region for block: B:191:0x025c  */
            /* JADX WARN: Removed duplicated region for block: B:192:0x025f  */
            /* JADX WARN: Removed duplicated region for block: B:193:0x0259  */
            /* JADX WARN: Removed duplicated region for block: B:201:0x0129  */
            /* JADX WARN: Removed duplicated region for block: B:204:0x0132  */
            /* JADX WARN: Removed duplicated region for block: B:231:0x01f1  */
            /* JADX WARN: Removed duplicated region for block: B:29:0x0271  */
            /* JADX WARN: Removed duplicated region for block: B:37:0x02ac  */
            /* JADX WARN: Removed duplicated region for block: B:43:0x03b8  */
            /* JADX WARN: Removed duplicated region for block: B:46:0x03f8  */
            /* JADX WARN: Removed duplicated region for block: B:49:0x0418  */
            /* JADX WARN: Removed duplicated region for block: B:55:0x044b  */
            /* JADX WARN: Type inference failed for: r0v114 */
            /* JADX WARN: Type inference failed for: r0v123 */
            /* JADX WARN: Type inference failed for: r0v124 */
            /* JADX WARN: Type inference failed for: r0v127, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r0v138, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r0v143 */
            /* JADX WARN: Type inference failed for: r0v92, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r1v31 */
            /* JADX WARN: Type inference failed for: r1v32, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r1v42 */
            /* JADX WARN: Type inference failed for: r2v9, types: [java.lang.StringBuilder] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final /* synthetic */ DocData from$hyperkyc_release(Gson gson, String side, HVResponse hvResponse) {
                String canonicalName;
                String str;
                Object m1202constructorimpl;
                HVResponse hVResponse;
                CharSequence charSequence;
                String str2;
                String canonicalName2;
                String className;
                DocData docData;
                StackTraceElement stackTraceElement;
                String str3;
                Matcher matcher;
                String str4;
                Object m1202constructorimpl2;
                String str5;
                String canonicalName3;
                String className2;
                Map map;
                Throwable m1205exceptionOrNullimpl;
                BaseResponse baseResponse;
                BaseResponse baseResponse2;
                String str6;
                String str7;
                String str8;
                Object m1202constructorimpl3;
                ?? r0;
                String str9;
                Matcher matcher2;
                ?? localizedMessage;
                String className3;
                String className4;
                String className5;
                String className6;
                Intrinsics.checkNotNullParameter(gson, "gson");
                Intrinsics.checkNotNullParameter(hvResponse, "hvResponse");
                HyperLogger.Level level = HyperLogger.Level.DEBUG;
                HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                if (stackTraceElement2 == null || (className6 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls = getClass();
                    canonicalName = cls != null ? cls.getCanonicalName() : null;
                    if (canonicalName == null) {
                        canonicalName = "N/A";
                    }
                }
                Matcher matcher3 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName);
                String str10 = "";
                if (matcher3.find()) {
                    canonicalName = matcher3.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
                }
                Unit unit = Unit.INSTANCE;
                if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    canonicalName = canonicalName.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb.append(canonicalName);
                sb.append(" - ");
                String str11 = "from() called with: gson = [" + gson + "], side = [" + side + "], hvResponse = [" + hvResponse + ']';
                if (str11 == null) {
                    str11 = "null ";
                }
                sb.append(str11);
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                if (CoreExtsKt.isRelease()) {
                    charSequence = "co.hyperverge";
                    str = " - ";
                    str2 = "packageName";
                    hVResponse = hvResponse;
                } else {
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        str = " - ";
                        try {
                            Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                        } catch (Throwable th) {
                            th = th;
                            Result.Companion companion3 = Result.INSTANCE;
                            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                            }
                            String packageName = (String) m1202constructorimpl;
                            if (CoreExtsKt.isDebug()) {
                            }
                            docData = new DocData(null, null, null, 0, null, null, null, null, null, null, null, null, 4095, null);
                            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                            HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb2 = new StringBuilder();
                            StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                            stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                            if (stackTraceElement != null) {
                            }
                            Class<?> cls2 = docData.getClass();
                            if (cls2 == null) {
                            }
                            if (r1 != null) {
                            }
                            matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str3);
                            if (matcher.find()) {
                            }
                            Unit unit2 = Unit.INSTANCE;
                            if (str3.length() > 23) {
                                str3 = str3.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            sb2.append(str3);
                            String str12 = str;
                            sb2.append(str12);
                            str4 = "get() called, hvResponse: " + hVResponse;
                            if (str4 == null) {
                            }
                            sb2.append(str4);
                            sb2.append(' ');
                            sb2.append("");
                            companion4.log(level2, sb2.toString());
                            if (!CoreExtsKt.isRelease()) {
                            }
                            str5 = str2;
                            docData.setSide$hyperkyc_release(side);
                            Integer statusCode = hvResponse.getStatusCode();
                            docData.setStatusCode$hyperkyc_release(statusCode != null ? String.valueOf(statusCode) : null);
                            docData.setStatusMessage$hyperkyc_release(hvResponse.getStatusMessage());
                            docData.setLatitude$hyperkyc_release(hvResponse.getLatitude());
                            docData.setLongitude$hyperkyc_release(hvResponse.getLongitude());
                            docData.setSubmittedTimestamp$hyperkyc_release(hvResponse.getSubmittedTimestamp());
                            docData.setDocImagePath$hyperkyc_release(hvResponse.getImageURI());
                            docData.setAttemptsCount$hyperkyc_release(hvResponse.getAttemptsCount());
                            map = JSONExtsKt.toMap(hvResponse.getApiHeaders());
                            if (!(map instanceof Map)) {
                            }
                            docData.setResponseHeaders$hyperkyc_release(map);
                            Type type = TypeToken.getParameterized(BaseResponse.class, DocCaptureApiDetail.class).getType();
                            JSONObject apiResult = hvResponse.getApiResult();
                            docData.setResponseBodyRaw$hyperkyc_release(apiResult != null ? apiResult.toString() : null);
                            Result.Companion companion5 = Result.INSTANCE;
                            Object fromJson = gson.fromJson(docData.getResponseBodyRaw$hyperkyc_release(), type);
                            Intrinsics.checkNotNullExpressionValue(fromJson, "gson.fromJson(responseBodyRaw, responseType)");
                            BaseResponse baseResponse3 = Result.m1202constructorimpl((BaseResponse) fromJson);
                            BaseResponse baseResponse4 = baseResponse3;
                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(baseResponse4);
                            if (m1205exceptionOrNullimpl != null) {
                            }
                            baseResponse2 = null;
                            docData.setResponseBody$hyperkyc_release(Result.m1208isFailureimpl(baseResponse) ? baseResponse2 : baseResponse);
                            BaseResponse responseBody = docData.getResponseBody();
                            docData.setAction$hyperkyc_release(responseBody != null ? responseBody.action() : baseResponse2);
                            return docData;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        str = " - ";
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        m1202constructorimpl = "";
                    }
                    String packageName2 = (String) m1202constructorimpl;
                    if (CoreExtsKt.isDebug()) {
                        hVResponse = hvResponse;
                        charSequence = "co.hyperverge";
                        str2 = "packageName";
                    } else {
                        Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                        charSequence = "co.hyperverge";
                        str2 = "packageName";
                        if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                            if (stackTraceElement3 == null || (className = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls3 = getClass();
                                canonicalName2 = cls3 != null ? cls3.getCanonicalName() : null;
                                if (canonicalName2 == null) {
                                    canonicalName2 = "N/A";
                                }
                            }
                            Matcher matcher4 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName2);
                            if (matcher4.find()) {
                                canonicalName2 = matcher4.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                            }
                            Unit unit3 = Unit.INSTANCE;
                            if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                canonicalName2 = canonicalName2.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb3 = new StringBuilder();
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append("from() called with: gson = [");
                            sb4.append(gson);
                            sb4.append("], side = [");
                            sb4.append(side);
                            sb4.append("], hvResponse = [");
                            hVResponse = hvResponse;
                            sb4.append(hVResponse);
                            sb4.append(']');
                            String sb5 = sb4.toString();
                            if (sb5 == null) {
                                sb5 = "null ";
                            }
                            sb3.append(sb5);
                            sb3.append(' ');
                            sb3.append("");
                            Log.println(2, canonicalName2, sb3.toString());
                        } else {
                            hVResponse = hvResponse;
                        }
                    }
                }
                docData = new DocData(null, null, null, 0, null, null, null, null, null, null, null, null, 4095, null);
                HyperLogger.Level level22 = HyperLogger.Level.DEBUG;
                HyperLogger companion42 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb22 = new StringBuilder();
                StackTraceElement[] stackTrace22 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace22, "Throwable().stackTrace");
                stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace22);
                if (stackTraceElement != null || (className5 = stackTraceElement.getClassName()) == null || (str3 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls22 = docData.getClass();
                    String canonicalName4 = cls22 == null ? cls22.getCanonicalName() : null;
                    str3 = canonicalName4 != null ? "N/A" : canonicalName4;
                }
                matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str3);
                if (matcher.find()) {
                    str3 = matcher.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                }
                Unit unit22 = Unit.INSTANCE;
                if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str3 = str3.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb22.append(str3);
                String str122 = str;
                sb22.append(str122);
                str4 = "get() called, hvResponse: " + hVResponse;
                if (str4 == null) {
                    str4 = "null ";
                }
                sb22.append(str4);
                sb22.append(' ');
                sb22.append("");
                companion42.log(level22, sb22.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion6 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th3) {
                        Result.Companion companion7 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String str13 = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        str5 = str2;
                        Intrinsics.checkNotNullExpressionValue(str13, str5);
                        if (StringsKt.contains$default((CharSequence) str13, charSequence, false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls4 = docData.getClass();
                                canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                                if (canonicalName3 == null) {
                                    canonicalName3 = "N/A";
                                }
                            }
                            Matcher matcher5 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName3);
                            if (matcher5.find()) {
                                canonicalName3 = matcher5.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                            }
                            Unit unit4 = Unit.INSTANCE;
                            if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                canonicalName3 = canonicalName3.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb6 = new StringBuilder();
                            String str14 = "get() called, hvResponse: " + hVResponse;
                            if (str14 == null) {
                                str14 = "null ";
                            }
                            sb6.append(str14);
                            sb6.append(' ');
                            sb6.append("");
                            Log.println(2, canonicalName3, sb6.toString());
                        }
                        docData.setSide$hyperkyc_release(side);
                        Integer statusCode2 = hvResponse.getStatusCode();
                        docData.setStatusCode$hyperkyc_release(statusCode2 != null ? String.valueOf(statusCode2) : null);
                        docData.setStatusMessage$hyperkyc_release(hvResponse.getStatusMessage());
                        docData.setLatitude$hyperkyc_release(hvResponse.getLatitude());
                        docData.setLongitude$hyperkyc_release(hvResponse.getLongitude());
                        docData.setSubmittedTimestamp$hyperkyc_release(hvResponse.getSubmittedTimestamp());
                        docData.setDocImagePath$hyperkyc_release(hvResponse.getImageURI());
                        docData.setAttemptsCount$hyperkyc_release(hvResponse.getAttemptsCount());
                        map = JSONExtsKt.toMap(hvResponse.getApiHeaders());
                        if (!(map instanceof Map)) {
                            map = null;
                        }
                        docData.setResponseHeaders$hyperkyc_release(map);
                        Type type2 = TypeToken.getParameterized(BaseResponse.class, DocCaptureApiDetail.class).getType();
                        JSONObject apiResult2 = hvResponse.getApiResult();
                        docData.setResponseBodyRaw$hyperkyc_release(apiResult2 != null ? apiResult2.toString() : null);
                        Result.Companion companion52 = Result.INSTANCE;
                        Object fromJson2 = gson.fromJson(docData.getResponseBodyRaw$hyperkyc_release(), type2);
                        Intrinsics.checkNotNullExpressionValue(fromJson2, "gson.fromJson(responseBodyRaw, responseType)");
                        BaseResponse baseResponse32 = Result.m1202constructorimpl((BaseResponse) fromJson2);
                        BaseResponse baseResponse42 = baseResponse32;
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(baseResponse42);
                        if (m1205exceptionOrNullimpl != null) {
                            HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                            HyperLogger companion8 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb7 = new StringBuilder();
                            StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                            if (stackTraceElement5 == null || (className4 = stackTraceElement5.getClassName()) == null) {
                                baseResponse = baseResponse42;
                                str6 = "Throwable().stackTrace";
                            } else {
                                baseResponse = baseResponse42;
                                str6 = "Throwable().stackTrace";
                                str7 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            }
                            Class<?> cls5 = docData.getClass();
                            String canonicalName5 = cls5 != null ? cls5.getCanonicalName() : null;
                            str7 = canonicalName5 == null ? "N/A" : canonicalName5;
                            Matcher matcher6 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str7);
                            if (matcher6.find()) {
                                str7 = matcher6.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str7, "replaceAll(\"\")");
                            }
                            Unit unit5 = Unit.INSTANCE;
                            if (str7.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str7 = str7.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            sb7.append(str7);
                            sb7.append(str122);
                            String str15 = "Failed to parse responseBodyRaw: " + docData.getResponseBodyRaw$hyperkyc_release();
                            if (str15 == null) {
                                str15 = "null ";
                            }
                            sb7.append(str15);
                            sb7.append(' ');
                            String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                            if (localizedMessage2 != null) {
                                str8 = '\n' + localizedMessage2;
                            } else {
                                str8 = "";
                            }
                            sb7.append(str8);
                            companion8.log(level3, sb7.toString());
                            CoreExtsKt.isRelease();
                            try {
                                Result.Companion companion9 = Result.INSTANCE;
                                Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type android.app.Application");
                                m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                            } catch (Throwable th4) {
                                Result.Companion companion10 = Result.INSTANCE;
                                m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th4));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                m1202constructorimpl3 = "";
                            }
                            String str16 = (String) m1202constructorimpl3;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(str16, str5);
                                if (StringsKt.contains$default((CharSequence) str16, charSequence, false, 2, (Object) null)) {
                                    StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace6, str6);
                                    StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                    if (stackTraceElement6 == null || (className3 = stackTraceElement6.getClassName()) == null) {
                                        baseResponse2 = null;
                                    } else {
                                        baseResponse2 = null;
                                        r0 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    }
                                    Class<?> cls6 = docData.getClass();
                                    r0 = cls6 != null ? cls6.getCanonicalName() : baseResponse2;
                                    if (r0 == 0) {
                                        str9 = "N/A";
                                        matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str9);
                                        if (matcher2.find()) {
                                            str9 = matcher2.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(str9, "replaceAll(\"\")");
                                        }
                                        Unit unit6 = Unit.INSTANCE;
                                        if (str9.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            str9 = str9.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb8 = new StringBuilder();
                                        String str17 = "Failed to parse responseBodyRaw: " + docData.getResponseBodyRaw$hyperkyc_release();
                                        sb8.append(str17 != null ? "null " : str17);
                                        sb8.append(' ');
                                        localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : baseResponse2;
                                        if (localizedMessage != 0) {
                                            ?? sb9 = new StringBuilder();
                                            sb9.append('\n');
                                            sb9.append(localizedMessage);
                                            str10 = sb9.toString();
                                        }
                                        sb8.append(str10);
                                        Log.println(6, str9, sb8.toString());
                                        docData.setResponseBody$hyperkyc_release(Result.m1208isFailureimpl(baseResponse) ? baseResponse2 : baseResponse);
                                        BaseResponse responseBody2 = docData.getResponseBody();
                                        docData.setAction$hyperkyc_release(responseBody2 != null ? responseBody2.action() : baseResponse2);
                                        return docData;
                                    }
                                    str9 = r0;
                                    matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str9);
                                    if (matcher2.find()) {
                                    }
                                    Unit unit62 = Unit.INSTANCE;
                                    if (str9.length() > 23) {
                                        str9 = str9.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb82 = new StringBuilder();
                                    String str172 = "Failed to parse responseBodyRaw: " + docData.getResponseBodyRaw$hyperkyc_release();
                                    sb82.append(str172 != null ? "null " : str172);
                                    sb82.append(' ');
                                    if (m1205exceptionOrNullimpl == null) {
                                    }
                                    if (localizedMessage != 0) {
                                    }
                                    sb82.append(str10);
                                    Log.println(6, str9, sb82.toString());
                                    docData.setResponseBody$hyperkyc_release(Result.m1208isFailureimpl(baseResponse) ? baseResponse2 : baseResponse);
                                    BaseResponse responseBody22 = docData.getResponseBody();
                                    docData.setAction$hyperkyc_release(responseBody22 != null ? responseBody22.action() : baseResponse2);
                                    return docData;
                                }
                            }
                        } else {
                            baseResponse = baseResponse42;
                        }
                        baseResponse2 = null;
                        docData.setResponseBody$hyperkyc_release(Result.m1208isFailureimpl(baseResponse) ? baseResponse2 : baseResponse);
                        BaseResponse responseBody222 = docData.getResponseBody();
                        docData.setAction$hyperkyc_release(responseBody222 != null ? responseBody222.action() : baseResponse2);
                        return docData;
                    }
                }
                str5 = str2;
                docData.setSide$hyperkyc_release(side);
                Integer statusCode22 = hvResponse.getStatusCode();
                docData.setStatusCode$hyperkyc_release(statusCode22 != null ? String.valueOf(statusCode22) : null);
                docData.setStatusMessage$hyperkyc_release(hvResponse.getStatusMessage());
                docData.setLatitude$hyperkyc_release(hvResponse.getLatitude());
                docData.setLongitude$hyperkyc_release(hvResponse.getLongitude());
                docData.setSubmittedTimestamp$hyperkyc_release(hvResponse.getSubmittedTimestamp());
                docData.setDocImagePath$hyperkyc_release(hvResponse.getImageURI());
                docData.setAttemptsCount$hyperkyc_release(hvResponse.getAttemptsCount());
                map = JSONExtsKt.toMap(hvResponse.getApiHeaders());
                if (!(map instanceof Map)) {
                }
                docData.setResponseHeaders$hyperkyc_release(map);
                Type type22 = TypeToken.getParameterized(BaseResponse.class, DocCaptureApiDetail.class).getType();
                JSONObject apiResult22 = hvResponse.getApiResult();
                docData.setResponseBodyRaw$hyperkyc_release(apiResult22 != null ? apiResult22.toString() : null);
                Result.Companion companion522 = Result.INSTANCE;
                Object fromJson22 = gson.fromJson(docData.getResponseBodyRaw$hyperkyc_release(), type22);
                Intrinsics.checkNotNullExpressionValue(fromJson22, "gson.fromJson(responseBodyRaw, responseType)");
                BaseResponse baseResponse322 = Result.m1202constructorimpl((BaseResponse) fromJson22);
                BaseResponse baseResponse422 = baseResponse322;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(baseResponse422);
                if (m1205exceptionOrNullimpl != null) {
                }
                baseResponse2 = null;
                docData.setResponseBody$hyperkyc_release(Result.m1208isFailureimpl(baseResponse) ? baseResponse2 : baseResponse);
                BaseResponse responseBody2222 = docData.getResponseBody();
                docData.setAction$hyperkyc_release(responseBody2222 != null ? responseBody2222.action() : baseResponse2);
                return docData;
            }
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bw\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00122\b\u0002\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\f\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000e¢\u0006\u0002\u0010\u0011J\u0015\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000eH\u0000¢\u0006\u0002\b$J\u0006\u0010\u0006\u001a\u00020\u0003J\u000e\u0010%\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b&J\u000e\u0010'\u001a\u00020\u0005HÀ\u0003¢\u0006\u0002\b(J\u000e\u0010)\u001a\u00020\u0007HÀ\u0003¢\u0006\u0002\b*J8\u0010+\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\fHÀ\u0003¢\u0006\u0002\b,J\u0016\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÀ\u0003¢\u0006\u0002\b.J\u0016\u0010/\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000eHÀ\u0003¢\u0006\u0002\b0J\u007f\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u000722\b\u0002\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\f2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000eHÆ\u0001J\t\u00102\u001a\u00020\u0007HÖ\u0001J\u0013\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\nHÖ\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\t\u00106\u001a\u00020\u0007HÖ\u0001J\u0015\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eH\u0000¢\u0006\u0002\b7J\u0006\u0010\u0002\u001a\u00020\u0003J\t\u00108\u001a\u00020\u0003HÖ\u0001J\u0019\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u0007HÖ\u0001R\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0006\u001a\u00020\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fRD\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006>"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$FaceResult;", "Landroid/os/Parcelable;", "tag", "", "faceData", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$FaceData;", AppConstants.ATTEMPTS_KEY, "", "variables", "Ljava/util/HashMap;", "", "Lkotlinx/parcelize/RawValue;", "Lkotlin/collections/HashMap;", "requestIds", "", "apiFlags", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$ApiFlags;", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$FaceData;ILjava/util/HashMap;Ljava/util/List;Ljava/util/List;)V", "getApiFlags$hyperkyc_release", "()Ljava/util/List;", "setApiFlags$hyperkyc_release", "(Ljava/util/List;)V", "getAttempts$hyperkyc_release", "()I", "setAttempts$hyperkyc_release", "(I)V", "getFaceData$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$FaceData;", "getRequestIds$hyperkyc_release", "setRequestIds$hyperkyc_release", "getTag$hyperkyc_release", "()Ljava/lang/String;", "getVariables$hyperkyc_release", "()Ljava/util/HashMap;", "setVariables$hyperkyc_release", "(Ljava/util/HashMap;)V", "apiFlags$hyperkyc_release", "component1", "component1$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "component4", "component4$hyperkyc_release", "component5", "component5$hyperkyc_release", "component6", "component6$hyperkyc_release", "copy", "describeContents", "equals", "", "other", "hashCode", "requestIds$hyperkyc_release", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class FaceResult implements Parcelable {
        public static final Parcelable.Creator<FaceResult> CREATOR = new Creator();
        private List<ApiFlags> apiFlags;
        private int attempts;
        private final FaceData faceData;
        private List<String> requestIds;

        @SerializedName("moduleId")
        private final String tag;
        private HashMap<String, Object> variables;

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<FaceResult> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final FaceResult createFromParcel(Parcel parcel) {
                ArrayList arrayList;
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String readString = parcel.readString();
                FaceData createFromParcel = FaceData.CREATOR.createFromParcel(parcel);
                int readInt = parcel.readInt();
                int readInt2 = parcel.readInt();
                HashMap hashMap = new HashMap(readInt2);
                for (int i = 0; i != readInt2; i++) {
                    hashMap.put(parcel.readString(), parcel.readValue(FaceResult.class.getClassLoader()));
                }
                ArrayList<String> createStringArrayList = parcel.createStringArrayList();
                if (parcel.readInt() == 0) {
                    arrayList = null;
                } else {
                    int readInt3 = parcel.readInt();
                    ArrayList arrayList2 = new ArrayList(readInt3);
                    for (int i2 = 0; i2 != readInt3; i2++) {
                        arrayList2.add(ApiFlags.CREATOR.createFromParcel(parcel));
                    }
                    arrayList = arrayList2;
                }
                return new FaceResult(readString, createFromParcel, readInt, hashMap, createStringArrayList, arrayList);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final FaceResult[] newArray(int i) {
                return new FaceResult[i];
            }
        }

        public static /* synthetic */ FaceResult copy$default(FaceResult faceResult, String str, FaceData faceData, int i, HashMap hashMap, List list, List list2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = faceResult.tag;
            }
            if ((i2 & 2) != 0) {
                faceData = faceResult.faceData;
            }
            FaceData faceData2 = faceData;
            if ((i2 & 4) != 0) {
                i = faceResult.attempts;
            }
            int i3 = i;
            if ((i2 & 8) != 0) {
                hashMap = faceResult.variables;
            }
            HashMap hashMap2 = hashMap;
            if ((i2 & 16) != 0) {
                list = faceResult.requestIds;
            }
            List list3 = list;
            if ((i2 & 32) != 0) {
                list2 = faceResult.apiFlags;
            }
            return faceResult.copy(str, faceData2, i3, hashMap2, list3, list2);
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final String getTag() {
            return this.tag;
        }

        /* renamed from: component2$hyperkyc_release, reason: from getter */
        public final FaceData getFaceData() {
            return this.faceData;
        }

        /* renamed from: component3$hyperkyc_release, reason: from getter */
        public final int getAttempts() {
            return this.attempts;
        }

        public final HashMap<String, Object> component4$hyperkyc_release() {
            return this.variables;
        }

        public final List<String> component5$hyperkyc_release() {
            return this.requestIds;
        }

        public final List<ApiFlags> component6$hyperkyc_release() {
            return this.apiFlags;
        }

        public final FaceResult copy(String tag, FaceData faceData, int attempts, HashMap<String, Object> variables, List<String> requestIds, List<ApiFlags> apiFlags) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(faceData, "faceData");
            Intrinsics.checkNotNullParameter(variables, "variables");
            return new FaceResult(tag, faceData, attempts, variables, requestIds, apiFlags);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FaceResult)) {
                return false;
            }
            FaceResult faceResult = (FaceResult) other;
            return Intrinsics.areEqual(this.tag, faceResult.tag) && Intrinsics.areEqual(this.faceData, faceResult.faceData) && this.attempts == faceResult.attempts && Intrinsics.areEqual(this.variables, faceResult.variables) && Intrinsics.areEqual(this.requestIds, faceResult.requestIds) && Intrinsics.areEqual(this.apiFlags, faceResult.apiFlags);
        }

        public int hashCode() {
            int hashCode = ((((((this.tag.hashCode() * 31) + this.faceData.hashCode()) * 31) + this.attempts) * 31) + this.variables.hashCode()) * 31;
            List<String> list = this.requestIds;
            int hashCode2 = (hashCode + (list == null ? 0 : list.hashCode())) * 31;
            List<ApiFlags> list2 = this.apiFlags;
            return hashCode2 + (list2 != null ? list2.hashCode() : 0);
        }

        public String toString() {
            return "FaceResult(tag=" + this.tag + ", faceData=" + this.faceData + ", attempts=" + this.attempts + ", variables=" + this.variables + ", requestIds=" + this.requestIds + ", apiFlags=" + this.apiFlags + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.tag);
            this.faceData.writeToParcel(parcel, flags);
            parcel.writeInt(this.attempts);
            HashMap<String, Object> hashMap = this.variables;
            parcel.writeInt(hashMap.size());
            for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeValue(entry.getValue());
            }
            parcel.writeStringList(this.requestIds);
            List<ApiFlags> list = this.apiFlags;
            if (list == null) {
                parcel.writeInt(0);
                return;
            }
            parcel.writeInt(1);
            parcel.writeInt(list.size());
            Iterator<ApiFlags> it = list.iterator();
            while (it.hasNext()) {
                it.next().writeToParcel(parcel, flags);
            }
        }

        public FaceResult(String tag, FaceData faceData, int i, HashMap<String, Object> variables, List<String> list, List<ApiFlags> list2) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(faceData, "faceData");
            Intrinsics.checkNotNullParameter(variables, "variables");
            this.tag = tag;
            this.faceData = faceData;
            this.attempts = i;
            this.variables = variables;
            this.requestIds = list;
            this.apiFlags = list2;
        }

        public final /* synthetic */ String getTag$hyperkyc_release() {
            return this.tag;
        }

        public final /* synthetic */ FaceData getFaceData$hyperkyc_release() {
            return this.faceData;
        }

        public final /* synthetic */ int getAttempts$hyperkyc_release() {
            return this.attempts;
        }

        public final void setAttempts$hyperkyc_release(int i) {
            this.attempts = i;
        }

        public /* synthetic */ FaceResult(String str, FaceData faceData, int i, HashMap hashMap, List list, List list2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, faceData, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? new HashMap() : hashMap, (i2 & 16) != 0 ? null : list, (i2 & 32) != 0 ? null : list2);
        }

        /* renamed from: getVariables$hyperkyc_release, reason: from getter */
        public final /* synthetic */ HashMap getVariables() {
            return this.variables;
        }

        public final void setVariables$hyperkyc_release(HashMap<String, Object> hashMap) {
            Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
            this.variables = hashMap;
        }

        /* renamed from: getRequestIds$hyperkyc_release, reason: from getter */
        public final /* synthetic */ List getRequestIds() {
            return this.requestIds;
        }

        public final /* synthetic */ void setRequestIds$hyperkyc_release(List list) {
            this.requestIds = list;
        }

        /* renamed from: getApiFlags$hyperkyc_release, reason: from getter */
        public final /* synthetic */ List getApiFlags() {
            return this.apiFlags;
        }

        public final /* synthetic */ void setApiFlags$hyperkyc_release(List list) {
            this.apiFlags = list;
        }

        public final String tag() {
            return this.tag;
        }

        public final FaceData faceData() {
            return this.faceData;
        }

        public final String attempts() {
            return String.valueOf(this.attempts);
        }

        public final /* synthetic */ List requestIds$hyperkyc_release() {
            List<String> list = this.requestIds;
            if (list != null) {
                return list;
            }
            String requestId = this.faceData.getRequestId();
            if (requestId != null) {
                return CollectionsKt.listOf(requestId);
            }
            return null;
        }

        public final /* synthetic */ List apiFlags$hyperkyc_release() {
            List<ApiFlags> list = this.apiFlags;
            if (list != null) {
                return list;
            }
            ApiFlags apiFlags = this.faceData.getApiFlags(this.tag);
            if (apiFlags != null) {
                return CollectionsKt.listOf(apiFlags);
            }
            return null;
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\bG\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 q2\u00020\u0001:\u0001qB±\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\u0002\u0010\u0016J\b\u0010\b\u001a\u0004\u0018\u00010\u0003J\u0006\u0010\u0006\u001a\u00020\u0003J\u0010\u0010?\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b@J\u0010\u0010A\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\bBJ\u0012\u0010C\u001a\u0004\u0018\u00010\u0012HÀ\u0003¢\u0006\u0004\bD\u0010$J\u0012\u0010E\u001a\u0004\u0018\u00010\u0012HÀ\u0003¢\u0006\u0004\bF\u0010$J\u0012\u0010G\u001a\u0004\u0018\u00010\u0015HÀ\u0003¢\u0006\u0004\bH\u00109J\u0010\u0010I\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\bJJ\u0010\u0010K\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\bLJ\u000e\u0010M\u001a\u00020\u0007HÀ\u0003¢\u0006\u0002\bNJ\u0010\u0010O\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\bPJ\u001c\u0010Q\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nHÀ\u0003¢\u0006\u0002\bRJ\u0016\u0010S\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fHÀ\u0003¢\u0006\u0002\bTJ\u0010\u0010U\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\bVJ\u0010\u0010W\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\bXJº\u0001\u0010Y\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÆ\u0001¢\u0006\u0002\u0010ZJ\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003J\t\u0010[\u001a\u00020\u0007HÖ\u0001J\u0013\u0010\\\u001a\u00020]2\b\u0010^\u001a\u0004\u0018\u00010_HÖ\u0003J\u0014\u0010`\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010a\u001a\u0004\u0018\u00010\u0003J\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003J\u0010\u0010b\u001a\u0004\u0018\u00010c2\u0006\u0010d\u001a\u00020\u0003J\b\u0010e\u001a\u0004\u0018\u00010\u0003J\t\u0010f\u001a\u00020\u0007HÖ\u0001J\u0006\u0010g\u001a\u00020]J\u0014\u0010h\u001a\u00020]2\f\u0010i\u001a\b\u0012\u0004\u0012\u00020\u00070jJ\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003J\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003J\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fJ\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nJ\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003J\t\u0010k\u001a\u00020\u0003HÖ\u0001J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003J\u0019\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020o2\u0006\u0010p\u001a\u00020\u0007HÖ\u0001R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0006\u001a\u00020\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001aR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0018\"\u0004\b\"\u0010\u001aR\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0080\u000e¢\u0006\u0010\n\u0002\u0010'\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u0012X\u0080\u000e¢\u0006\u0010\n\u0002\u0010'\u001a\u0004\b(\u0010$\"\u0004\b)\u0010&R\"\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0018\"\u0004\b/\u0010\u001aR(\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0018\"\u0004\b5\u0010\u001aR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0018\"\u0004\b7\u0010\u001aR\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0080\u000e¢\u0006\u0010\n\u0002\u0010<\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0018\"\u0004\b>\u0010\u001a¨\u0006r"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$FaceData;", "Landroid/os/Parcelable;", "croppedFaceImagePath", "", "fullFaceImagePath", "videoPath", "attemptsCount", "", "action", "responseHeaders", "", "responseBody", "Lco/hyperverge/hyperkyc/data/network/BaseResponse;", "Lco/hyperverge/hyperkyc/data/network/FaceCaptureApiDetail;", "responseBodyRaw", Keys.STATUS_CODE, "statusMessage", "latitude", "", "longitude", "submittedTimestamp", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;Lco/hyperverge/hyperkyc/data/network/BaseResponse;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Long;)V", "getAction$hyperkyc_release", "()Ljava/lang/String;", "setAction$hyperkyc_release", "(Ljava/lang/String;)V", "getAttemptsCount$hyperkyc_release", "()I", "setAttemptsCount$hyperkyc_release", "(I)V", "getCroppedFaceImagePath$hyperkyc_release", "setCroppedFaceImagePath$hyperkyc_release", "getFullFaceImagePath$hyperkyc_release", "setFullFaceImagePath$hyperkyc_release", "getLatitude$hyperkyc_release", "()Ljava/lang/Double;", "setLatitude$hyperkyc_release", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "getLongitude$hyperkyc_release", "setLongitude$hyperkyc_release", "getResponseBody$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/network/BaseResponse;", "setResponseBody$hyperkyc_release", "(Lco/hyperverge/hyperkyc/data/network/BaseResponse;)V", "getResponseBodyRaw$hyperkyc_release", "setResponseBodyRaw$hyperkyc_release", "getResponseHeaders$hyperkyc_release", "()Ljava/util/Map;", "setResponseHeaders$hyperkyc_release", "(Ljava/util/Map;)V", "getStatusCode$hyperkyc_release", "setStatusCode$hyperkyc_release", "getStatusMessage$hyperkyc_release", "setStatusMessage$hyperkyc_release", "getSubmittedTimestamp$hyperkyc_release", "()Ljava/lang/Long;", "setSubmittedTimestamp$hyperkyc_release", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getVideoPath$hyperkyc_release", "setVideoPath$hyperkyc_release", "component1", "component1$hyperkyc_release", "component10", "component10$hyperkyc_release", "component11", "component11$hyperkyc_release", "component12", "component12$hyperkyc_release", "component13", "component13$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "component4", "component4$hyperkyc_release", "component5", "component5$hyperkyc_release", "component6", "component6$hyperkyc_release", "component7", "component7$hyperkyc_release", "component8", "component8$hyperkyc_release", "component9", "component9$hyperkyc_release", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;Lco/hyperverge/hyperkyc/data/network/BaseResponse;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Long;)Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$FaceData;", "describeContents", "equals", "", "other", "", "errorMessage", "defaultErrorMsg", "getApiFlags", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$ApiFlags;", "moduleId", "getRequestId", "hashCode", "isFaceLive", "isSuccess", "allowedStatusCodes", "", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class FaceData implements Parcelable {
        private String action;
        private int attemptsCount;
        private String croppedFaceImagePath;
        private String fullFaceImagePath;
        private Double latitude;
        private Double longitude;
        private BaseResponse<FaceCaptureApiDetail> responseBody;
        private String responseBodyRaw;
        private Map<String, String> responseHeaders;
        private String statusCode;
        private String statusMessage;
        private Long submittedTimestamp;
        private String videoPath;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final Parcelable.Creator<FaceData> CREATOR = new Creator();

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<FaceData> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final FaceData createFromParcel(Parcel parcel) {
                LinkedHashMap linkedHashMap;
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                String readString3 = parcel.readString();
                int readInt = parcel.readInt();
                String readString4 = parcel.readString();
                if (parcel.readInt() == 0) {
                    linkedHashMap = null;
                } else {
                    int readInt2 = parcel.readInt();
                    linkedHashMap = new LinkedHashMap(readInt2);
                    for (int i = 0; i != readInt2; i++) {
                        linkedHashMap.put(parcel.readString(), parcel.readString());
                    }
                }
                return new FaceData(readString, readString2, readString3, readInt, readString4, linkedHashMap, parcel.readInt() == 0 ? null : BaseResponse.CREATOR.createFromParcel(parcel), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? null : Double.valueOf(parcel.readDouble()), parcel.readInt() == 0 ? null : Double.valueOf(parcel.readDouble()), parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong()));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final FaceData[] newArray(int i) {
                return new FaceData[i];
            }
        }

        public FaceData() {
            this(null, null, null, 0, null, null, null, null, null, null, null, null, null, 8191, null);
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final String getCroppedFaceImagePath() {
            return this.croppedFaceImagePath;
        }

        /* renamed from: component10$hyperkyc_release, reason: from getter */
        public final String getStatusMessage() {
            return this.statusMessage;
        }

        /* renamed from: component11$hyperkyc_release, reason: from getter */
        public final Double getLatitude() {
            return this.latitude;
        }

        /* renamed from: component12$hyperkyc_release, reason: from getter */
        public final Double getLongitude() {
            return this.longitude;
        }

        /* renamed from: component13$hyperkyc_release, reason: from getter */
        public final Long getSubmittedTimestamp() {
            return this.submittedTimestamp;
        }

        /* renamed from: component2$hyperkyc_release, reason: from getter */
        public final String getFullFaceImagePath() {
            return this.fullFaceImagePath;
        }

        /* renamed from: component3$hyperkyc_release, reason: from getter */
        public final String getVideoPath() {
            return this.videoPath;
        }

        /* renamed from: component4$hyperkyc_release, reason: from getter */
        public final int getAttemptsCount() {
            return this.attemptsCount;
        }

        public final String component5$hyperkyc_release() {
            return this.action;
        }

        public final Map<String, String> component6$hyperkyc_release() {
            return this.responseHeaders;
        }

        public final BaseResponse<FaceCaptureApiDetail> component7$hyperkyc_release() {
            return this.responseBody;
        }

        /* renamed from: component8$hyperkyc_release, reason: from getter */
        public final String getResponseBodyRaw() {
            return this.responseBodyRaw;
        }

        /* renamed from: component9$hyperkyc_release, reason: from getter */
        public final String getStatusCode() {
            return this.statusCode;
        }

        public final FaceData copy(String croppedFaceImagePath, String fullFaceImagePath, String videoPath, int attemptsCount, String action, Map<String, String> responseHeaders, BaseResponse<FaceCaptureApiDetail> responseBody, String responseBodyRaw, String statusCode, String statusMessage, Double latitude, Double longitude, Long submittedTimestamp) {
            return new FaceData(croppedFaceImagePath, fullFaceImagePath, videoPath, attemptsCount, action, responseHeaders, responseBody, responseBodyRaw, statusCode, statusMessage, latitude, longitude, submittedTimestamp);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FaceData)) {
                return false;
            }
            FaceData faceData = (FaceData) other;
            return Intrinsics.areEqual(this.croppedFaceImagePath, faceData.croppedFaceImagePath) && Intrinsics.areEqual(this.fullFaceImagePath, faceData.fullFaceImagePath) && Intrinsics.areEqual(this.videoPath, faceData.videoPath) && this.attemptsCount == faceData.attemptsCount && Intrinsics.areEqual(this.action, faceData.action) && Intrinsics.areEqual(this.responseHeaders, faceData.responseHeaders) && Intrinsics.areEqual(this.responseBody, faceData.responseBody) && Intrinsics.areEqual(this.responseBodyRaw, faceData.responseBodyRaw) && Intrinsics.areEqual(this.statusCode, faceData.statusCode) && Intrinsics.areEqual(this.statusMessage, faceData.statusMessage) && Intrinsics.areEqual((Object) this.latitude, (Object) faceData.latitude) && Intrinsics.areEqual((Object) this.longitude, (Object) faceData.longitude) && Intrinsics.areEqual(this.submittedTimestamp, faceData.submittedTimestamp);
        }

        public int hashCode() {
            String str = this.croppedFaceImagePath;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.fullFaceImagePath;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.videoPath;
            int hashCode3 = (((hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.attemptsCount) * 31;
            String str4 = this.action;
            int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Map<String, String> map = this.responseHeaders;
            int hashCode5 = (hashCode4 + (map == null ? 0 : map.hashCode())) * 31;
            BaseResponse<FaceCaptureApiDetail> baseResponse = this.responseBody;
            int hashCode6 = (hashCode5 + (baseResponse == null ? 0 : baseResponse.hashCode())) * 31;
            String str5 = this.responseBodyRaw;
            int hashCode7 = (hashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.statusCode;
            int hashCode8 = (hashCode7 + (str6 == null ? 0 : str6.hashCode())) * 31;
            String str7 = this.statusMessage;
            int hashCode9 = (hashCode8 + (str7 == null ? 0 : str7.hashCode())) * 31;
            Double d = this.latitude;
            int hashCode10 = (hashCode9 + (d == null ? 0 : d.hashCode())) * 31;
            Double d2 = this.longitude;
            int hashCode11 = (hashCode10 + (d2 == null ? 0 : d2.hashCode())) * 31;
            Long l = this.submittedTimestamp;
            return hashCode11 + (l != null ? l.hashCode() : 0);
        }

        public String toString() {
            return "FaceData(croppedFaceImagePath=" + this.croppedFaceImagePath + ", fullFaceImagePath=" + this.fullFaceImagePath + ", videoPath=" + this.videoPath + ", attemptsCount=" + this.attemptsCount + ", action=" + this.action + ", responseHeaders=" + this.responseHeaders + ", responseBody=" + this.responseBody + ", responseBodyRaw=" + this.responseBodyRaw + ", statusCode=" + this.statusCode + ", statusMessage=" + this.statusMessage + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", submittedTimestamp=" + this.submittedTimestamp + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.croppedFaceImagePath);
            parcel.writeString(this.fullFaceImagePath);
            parcel.writeString(this.videoPath);
            parcel.writeInt(this.attemptsCount);
            parcel.writeString(this.action);
            Map<String, String> map = this.responseHeaders;
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
            BaseResponse<FaceCaptureApiDetail> baseResponse = this.responseBody;
            if (baseResponse == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                baseResponse.writeToParcel(parcel, flags);
            }
            parcel.writeString(this.responseBodyRaw);
            parcel.writeString(this.statusCode);
            parcel.writeString(this.statusMessage);
            Double d = this.latitude;
            if (d == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcel.writeDouble(d.doubleValue());
            }
            Double d2 = this.longitude;
            if (d2 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcel.writeDouble(d2.doubleValue());
            }
            Long l = this.submittedTimestamp;
            if (l == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcel.writeLong(l.longValue());
            }
        }

        public FaceData(String str, String str2, String str3, int i, String str4, Map<String, String> map, BaseResponse<FaceCaptureApiDetail> baseResponse, String str5, String str6, String str7, Double d, Double d2, Long l) {
            this.croppedFaceImagePath = str;
            this.fullFaceImagePath = str2;
            this.videoPath = str3;
            this.attemptsCount = i;
            this.action = str4;
            this.responseHeaders = map;
            this.responseBody = baseResponse;
            this.responseBodyRaw = str5;
            this.statusCode = str6;
            this.statusMessage = str7;
            this.latitude = d;
            this.longitude = d2;
            this.submittedTimestamp = l;
        }

        public /* synthetic */ FaceData(String str, String str2, String str3, int i, String str4, Map map, BaseResponse baseResponse, String str5, String str6, String str7, Double d, Double d2, Long l, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? 0 : i, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : map, (i2 & 64) != 0 ? null : baseResponse, (i2 & 128) != 0 ? null : str5, (i2 & 256) != 0 ? null : str6, (i2 & 512) != 0 ? null : str7, (i2 & 1024) != 0 ? null : d, (i2 & 2048) != 0 ? null : d2, (i2 & 4096) == 0 ? l : null);
        }

        public final /* synthetic */ String getCroppedFaceImagePath$hyperkyc_release() {
            return this.croppedFaceImagePath;
        }

        public final /* synthetic */ void setCroppedFaceImagePath$hyperkyc_release(String str) {
            this.croppedFaceImagePath = str;
        }

        public final /* synthetic */ String getFullFaceImagePath$hyperkyc_release() {
            return this.fullFaceImagePath;
        }

        public final /* synthetic */ void setFullFaceImagePath$hyperkyc_release(String str) {
            this.fullFaceImagePath = str;
        }

        public final /* synthetic */ String getVideoPath$hyperkyc_release() {
            return this.videoPath;
        }

        public final /* synthetic */ void setVideoPath$hyperkyc_release(String str) {
            this.videoPath = str;
        }

        public final /* synthetic */ int getAttemptsCount$hyperkyc_release() {
            return this.attemptsCount;
        }

        public final /* synthetic */ void setAttemptsCount$hyperkyc_release(int i) {
            this.attemptsCount = i;
        }

        public final /* synthetic */ String getAction$hyperkyc_release() {
            return this.action;
        }

        public final /* synthetic */ void setAction$hyperkyc_release(String str) {
            this.action = str;
        }

        /* renamed from: getResponseHeaders$hyperkyc_release, reason: from getter */
        public final /* synthetic */ Map getResponseHeaders() {
            return this.responseHeaders;
        }

        public final /* synthetic */ void setResponseHeaders$hyperkyc_release(Map map) {
            this.responseHeaders = map;
        }

        /* renamed from: getResponseBody$hyperkyc_release, reason: from getter */
        public final /* synthetic */ BaseResponse getResponseBody() {
            return this.responseBody;
        }

        public final /* synthetic */ void setResponseBody$hyperkyc_release(BaseResponse baseResponse) {
            this.responseBody = baseResponse;
        }

        public final /* synthetic */ String getResponseBodyRaw$hyperkyc_release() {
            return this.responseBodyRaw;
        }

        public final /* synthetic */ void setResponseBodyRaw$hyperkyc_release(String str) {
            this.responseBodyRaw = str;
        }

        public final /* synthetic */ String getStatusCode$hyperkyc_release() {
            return this.statusCode;
        }

        public final /* synthetic */ void setStatusCode$hyperkyc_release(String str) {
            this.statusCode = str;
        }

        public final /* synthetic */ String getStatusMessage$hyperkyc_release() {
            return this.statusMessage;
        }

        public final /* synthetic */ void setStatusMessage$hyperkyc_release(String str) {
            this.statusMessage = str;
        }

        public final /* synthetic */ Double getLatitude$hyperkyc_release() {
            return this.latitude;
        }

        public final /* synthetic */ void setLatitude$hyperkyc_release(Double d) {
            this.latitude = d;
        }

        public final /* synthetic */ Double getLongitude$hyperkyc_release() {
            return this.longitude;
        }

        public final /* synthetic */ void setLongitude$hyperkyc_release(Double d) {
            this.longitude = d;
        }

        public final /* synthetic */ Long getSubmittedTimestamp$hyperkyc_release() {
            return this.submittedTimestamp;
        }

        public final /* synthetic */ void setSubmittedTimestamp$hyperkyc_release(Long l) {
            this.submittedTimestamp = l;
        }

        public final boolean isSuccess(List<Integer> allowedStatusCodes) {
            Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
            BaseResponse<FaceCaptureApiDetail> baseResponse = this.responseBody;
            if (baseResponse != null) {
                return baseResponse.isSuccess(allowedStatusCodes);
            }
            return false;
        }

        public final boolean isFaceLive() {
            BaseResponse.Result<FaceCaptureApiDetail> result;
            List<FaceCaptureApiDetail> details;
            FaceCaptureApiDetail faceCaptureApiDetail;
            BaseResponse<FaceCaptureApiDetail> baseResponse = this.responseBody;
            return (baseResponse == null || (result = baseResponse.getResult()) == null || (details = result.getDetails()) == null || (faceCaptureApiDetail = (FaceCaptureApiDetail) CollectionsKt.firstOrNull((List) details)) == null || !faceCaptureApiDetail.isFaceLive()) ? false : true;
        }

        public final String getRequestId() {
            BaseResponse.Metadata metadata;
            String requestId;
            BaseResponse<FaceCaptureApiDetail> baseResponse = this.responseBody;
            if (baseResponse == null || (metadata = baseResponse.getMetadata()) == null || (requestId = metadata.getRequestId()) == null) {
                return null;
            }
            return CoreExtsKt.nullIfBlank(requestId);
        }

        public final ApiFlags getApiFlags(String moduleId) {
            Intrinsics.checkNotNullParameter(moduleId, "moduleId");
            Companion companion = HyperKycData.INSTANCE;
            Map<String, String> map = this.responseHeaders;
            String str = this.responseBodyRaw;
            String str2 = this.statusCode;
            String str3 = this.statusMessage;
            if (str3 == null) {
                str3 = JSONExtsKt.extractJsonValue(str, "error");
            }
            Map<String, String> extractApiFlags$hyperkyc_release = companion.extractApiFlags$hyperkyc_release(moduleId, map, str, str2, str3);
            if (extractApiFlags$hyperkyc_release == null || extractApiFlags$hyperkyc_release.isEmpty()) {
                return null;
            }
            return new ApiFlags(moduleId, null, extractApiFlags$hyperkyc_release, 2, null);
        }

        public static /* synthetic */ String errorMessage$default(FaceData faceData, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = null;
            }
            return faceData.errorMessage(str);
        }

        public final String errorMessage(String defaultErrorMsg) {
            List<FaceCaptureApiDetail> details;
            FaceCaptureApiDetail faceCaptureApiDetail;
            BaseResponse<FaceCaptureApiDetail> baseResponse = this.responseBody;
            String str = null;
            if (baseResponse == null) {
                return null;
            }
            String errorMessage = baseResponse.errorMessage();
            if (errorMessage == null) {
                BaseResponse.Result<FaceCaptureApiDetail> result = baseResponse.getResult();
                if (result != null && (details = result.getDetails()) != null && (faceCaptureApiDetail = (FaceCaptureApiDetail) CollectionsKt.firstOrNull((List) details)) != null) {
                    str = faceCaptureApiDetail.getError();
                }
                if (str != null) {
                    defaultErrorMsg = str;
                } else if (defaultErrorMsg == null) {
                    defaultErrorMsg = "Face capture failed!";
                }
            } else {
                defaultErrorMsg = errorMessage;
            }
            return defaultErrorMsg;
        }

        public final String croppedFaceImagePath() {
            return this.croppedFaceImagePath;
        }

        public final String fullFaceImagePath() {
            return this.fullFaceImagePath;
        }

        public final String videoPath() {
            return this.videoPath;
        }

        /* renamed from: action, reason: from getter */
        public final String getAction() {
            return this.action;
        }

        public final Map<String, String> responseHeaders() {
            return this.responseHeaders;
        }

        public final BaseResponse<FaceCaptureApiDetail> responseBody() {
            return this.responseBody;
        }

        public final String latitude() {
            Double d = this.latitude;
            if (d != null) {
                return d.toString();
            }
            return null;
        }

        public final String longitude() {
            Double d = this.longitude;
            if (d != null) {
                return d.toString();
            }
            return null;
        }

        public final String submittedTimestamp() {
            Long l = this.submittedTimestamp;
            if (l != null) {
                return l.toString();
            }
            return null;
        }

        public final String attemptsCount() {
            return String.valueOf(this.attemptsCount);
        }

        /* compiled from: HyperKycData.kt */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$FaceData$Companion;", "", "()V", "from", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$FaceData;", "gson", "Lcom/google/gson/Gson;", "hvResponse", "Lco/hyperverge/hypersnapsdk/objects/HVResponse;", "from$hyperkyc_release", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final /* synthetic */ FaceData from$hyperkyc_release(Gson gson, HVResponse hvResponse) {
                Intrinsics.checkNotNullParameter(gson, "gson");
                Intrinsics.checkNotNullParameter(hvResponse, "hvResponse");
                FaceData faceData = new FaceData(null, null, null, 0, null, null, null, null, null, null, null, null, null, 8191, null);
                Integer statusCode = hvResponse.getStatusCode();
                faceData.setStatusCode$hyperkyc_release(statusCode != null ? String.valueOf(statusCode) : null);
                faceData.setStatusMessage$hyperkyc_release(hvResponse.getStatusMessage());
                faceData.setLatitude$hyperkyc_release(hvResponse.getLatitude());
                faceData.setLongitude$hyperkyc_release(hvResponse.getLongitude());
                faceData.setSubmittedTimestamp$hyperkyc_release(hvResponse.getSubmittedTimestamp());
                faceData.setCroppedFaceImagePath$hyperkyc_release(hvResponse.getImageURI());
                faceData.setFullFaceImagePath$hyperkyc_release(hvResponse.getFullImageURI());
                faceData.setVideoPath$hyperkyc_release(hvResponse.getVideoUri());
                Map map = JSONExtsKt.toMap(hvResponse.getApiHeaders());
                if (!(map instanceof Map)) {
                    map = null;
                }
                faceData.setResponseHeaders$hyperkyc_release(map);
                faceData.setAttemptsCount$hyperkyc_release(hvResponse.getAttemptsCount());
                Type type = TypeToken.getParameterized(BaseResponse.class, FaceCaptureApiDetail.class).getType();
                faceData.setResponseBodyRaw$hyperkyc_release(String.valueOf(hvResponse.getApiResult()));
                faceData.setResponseBody$hyperkyc_release((BaseResponse) gson.fromJson(faceData.getResponseBodyRaw$hyperkyc_release(), type));
                BaseResponse responseBody = faceData.getResponseBody();
                faceData.setAction$hyperkyc_release(responseBody != null ? responseBody.action() : null);
                return faceData;
            }
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 >2\u00020\u0001:\u0001>B{\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00122\b\u0002\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\f\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000e¢\u0006\u0002\u0010\u0011J\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005J\u0015\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000eH\u0000¢\u0006\u0002\b$J\u0006\u0010\u0006\u001a\u00020\u0003J\u000e\u0010%\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b&J\u0010\u0010'\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b(J\u000e\u0010)\u001a\u00020\u0007HÀ\u0003¢\u0006\u0002\b*J8\u0010+\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\fHÀ\u0003¢\u0006\u0002\b,J\u0016\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÀ\u0003¢\u0006\u0002\b.J\u0016\u0010/\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000eHÀ\u0003¢\u0006\u0002\b0J\u0081\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u000722\b\u0002\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\f2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000eHÆ\u0001J\t\u00102\u001a\u00020\u0007HÖ\u0001J\u0013\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\nHÖ\u0003J\t\u00106\u001a\u00020\u0007HÖ\u0001J\u0015\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eH\u0000¢\u0006\u0002\b7J\u0006\u0010\u0002\u001a\u00020\u0003J\t\u00108\u001a\u00020\u0003HÖ\u0001J\u0019\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u0007HÖ\u0001R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0006\u001a\u00020\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fRD\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006?"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$APIResult;", "Landroid/os/Parcelable;", "tag", "", "apiData", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$APIData;", AppConstants.ATTEMPTS_KEY, "", "variables", "Ljava/util/HashMap;", "", "Lkotlinx/parcelize/RawValue;", "Lkotlin/collections/HashMap;", "requestIds", "", "apiFlags", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$ApiFlags;", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$APIData;ILjava/util/HashMap;Ljava/util/List;Ljava/util/List;)V", "getApiData$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$APIData;", "getApiFlags$hyperkyc_release", "()Ljava/util/List;", "setApiFlags$hyperkyc_release", "(Ljava/util/List;)V", "getAttempts$hyperkyc_release", "()I", "setAttempts$hyperkyc_release", "(I)V", "getRequestIds$hyperkyc_release", "setRequestIds$hyperkyc_release", "getTag$hyperkyc_release", "()Ljava/lang/String;", "getVariables$hyperkyc_release", "()Ljava/util/HashMap;", "setVariables$hyperkyc_release", "(Ljava/util/HashMap;)V", "apiFlags$hyperkyc_release", "component1", "component1$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "component4", "component4$hyperkyc_release", "component5", "component5$hyperkyc_release", "component6", "component6$hyperkyc_release", "copy", "describeContents", "equals", "", "other", "hashCode", "requestIds$hyperkyc_release", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class APIResult implements Parcelable {
        public static final String STATE_FAILURE = "failure";
        public static final String STATE_PROCESSING = "processing";
        public static final String STATE_SUCCESS = "success";
        private final APIData apiData;
        private List<ApiFlags> apiFlags;
        private int attempts;
        private List<String> requestIds;

        @SerializedName("moduleId")
        private final String tag;
        private HashMap<String, Object> variables;
        public static final Parcelable.Creator<APIResult> CREATOR = new Creator();

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<APIResult> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final APIResult createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String readString = parcel.readString();
                ArrayList arrayList = null;
                APIData createFromParcel = parcel.readInt() == 0 ? null : APIData.CREATOR.createFromParcel(parcel);
                int readInt = parcel.readInt();
                int readInt2 = parcel.readInt();
                HashMap hashMap = new HashMap(readInt2);
                for (int i = 0; i != readInt2; i++) {
                    hashMap.put(parcel.readString(), parcel.readValue(APIResult.class.getClassLoader()));
                }
                ArrayList<String> createStringArrayList = parcel.createStringArrayList();
                if (parcel.readInt() != 0) {
                    int readInt3 = parcel.readInt();
                    ArrayList arrayList2 = new ArrayList(readInt3);
                    for (int i2 = 0; i2 != readInt3; i2++) {
                        arrayList2.add(ApiFlags.CREATOR.createFromParcel(parcel));
                    }
                    arrayList = arrayList2;
                }
                return new APIResult(readString, createFromParcel, readInt, hashMap, createStringArrayList, arrayList);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final APIResult[] newArray(int i) {
                return new APIResult[i];
            }
        }

        public static /* synthetic */ APIResult copy$default(APIResult aPIResult, String str, APIData aPIData, int i, HashMap hashMap, List list, List list2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = aPIResult.tag;
            }
            if ((i2 & 2) != 0) {
                aPIData = aPIResult.apiData;
            }
            APIData aPIData2 = aPIData;
            if ((i2 & 4) != 0) {
                i = aPIResult.attempts;
            }
            int i3 = i;
            if ((i2 & 8) != 0) {
                hashMap = aPIResult.variables;
            }
            HashMap hashMap2 = hashMap;
            if ((i2 & 16) != 0) {
                list = aPIResult.requestIds;
            }
            List list3 = list;
            if ((i2 & 32) != 0) {
                list2 = aPIResult.apiFlags;
            }
            return aPIResult.copy(str, aPIData2, i3, hashMap2, list3, list2);
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final String getTag() {
            return this.tag;
        }

        public final APIData component2$hyperkyc_release() {
            return this.apiData;
        }

        /* renamed from: component3$hyperkyc_release, reason: from getter */
        public final int getAttempts() {
            return this.attempts;
        }

        public final HashMap<String, Object> component4$hyperkyc_release() {
            return this.variables;
        }

        public final List<String> component5$hyperkyc_release() {
            return this.requestIds;
        }

        public final List<ApiFlags> component6$hyperkyc_release() {
            return this.apiFlags;
        }

        public final APIResult copy(String tag, APIData apiData, int attempts, HashMap<String, Object> variables, List<String> requestIds, List<ApiFlags> apiFlags) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(variables, "variables");
            return new APIResult(tag, apiData, attempts, variables, requestIds, apiFlags);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof APIResult)) {
                return false;
            }
            APIResult aPIResult = (APIResult) other;
            return Intrinsics.areEqual(this.tag, aPIResult.tag) && Intrinsics.areEqual(this.apiData, aPIResult.apiData) && this.attempts == aPIResult.attempts && Intrinsics.areEqual(this.variables, aPIResult.variables) && Intrinsics.areEqual(this.requestIds, aPIResult.requestIds) && Intrinsics.areEqual(this.apiFlags, aPIResult.apiFlags);
        }

        public int hashCode() {
            int hashCode = this.tag.hashCode() * 31;
            APIData aPIData = this.apiData;
            int hashCode2 = (((((hashCode + (aPIData == null ? 0 : aPIData.hashCode())) * 31) + this.attempts) * 31) + this.variables.hashCode()) * 31;
            List<String> list = this.requestIds;
            int hashCode3 = (hashCode2 + (list == null ? 0 : list.hashCode())) * 31;
            List<ApiFlags> list2 = this.apiFlags;
            return hashCode3 + (list2 != null ? list2.hashCode() : 0);
        }

        public String toString() {
            return "APIResult(tag=" + this.tag + ", apiData=" + this.apiData + ", attempts=" + this.attempts + ", variables=" + this.variables + ", requestIds=" + this.requestIds + ", apiFlags=" + this.apiFlags + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.tag);
            APIData aPIData = this.apiData;
            if (aPIData == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                aPIData.writeToParcel(parcel, flags);
            }
            parcel.writeInt(this.attempts);
            HashMap<String, Object> hashMap = this.variables;
            parcel.writeInt(hashMap.size());
            for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeValue(entry.getValue());
            }
            parcel.writeStringList(this.requestIds);
            List<ApiFlags> list = this.apiFlags;
            if (list == null) {
                parcel.writeInt(0);
                return;
            }
            parcel.writeInt(1);
            parcel.writeInt(list.size());
            Iterator<ApiFlags> it = list.iterator();
            while (it.hasNext()) {
                it.next().writeToParcel(parcel, flags);
            }
        }

        public APIResult(String tag, APIData aPIData, int i, HashMap<String, Object> variables, List<String> list, List<ApiFlags> list2) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(variables, "variables");
            this.tag = tag;
            this.apiData = aPIData;
            this.attempts = i;
            this.variables = variables;
            this.requestIds = list;
            this.apiFlags = list2;
        }

        public final /* synthetic */ String getTag$hyperkyc_release() {
            return this.tag;
        }

        public final /* synthetic */ APIData getApiData$hyperkyc_release() {
            return this.apiData;
        }

        public final /* synthetic */ int getAttempts$hyperkyc_release() {
            return this.attempts;
        }

        public final void setAttempts$hyperkyc_release(int i) {
            this.attempts = i;
        }

        public /* synthetic */ APIResult(String str, APIData aPIData, int i, HashMap hashMap, List list, List list2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i2 & 2) != 0 ? null : aPIData, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? new HashMap() : hashMap, (i2 & 16) != 0 ? null : list, (i2 & 32) == 0 ? list2 : null);
        }

        /* renamed from: getVariables$hyperkyc_release, reason: from getter */
        public final /* synthetic */ HashMap getVariables() {
            return this.variables;
        }

        public final void setVariables$hyperkyc_release(HashMap<String, Object> hashMap) {
            Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
            this.variables = hashMap;
        }

        /* renamed from: getRequestIds$hyperkyc_release, reason: from getter */
        public final /* synthetic */ List getRequestIds() {
            return this.requestIds;
        }

        public final /* synthetic */ void setRequestIds$hyperkyc_release(List list) {
            this.requestIds = list;
        }

        /* renamed from: getApiFlags$hyperkyc_release, reason: from getter */
        public final /* synthetic */ List getApiFlags() {
            return this.apiFlags;
        }

        public final /* synthetic */ void setApiFlags$hyperkyc_release(List list) {
            this.apiFlags = list;
        }

        public final String tag() {
            return this.tag;
        }

        /* renamed from: apiData, reason: from getter */
        public final APIData getApiData() {
            return this.apiData;
        }

        public final String attempts() {
            return String.valueOf(this.attempts);
        }

        public final /* synthetic */ List requestIds$hyperkyc_release() {
            String requestId;
            List<String> list = this.requestIds;
            if (list != null) {
                return list;
            }
            APIData aPIData = this.apiData;
            if (aPIData == null || (requestId = aPIData.getRequestId()) == null) {
                return null;
            }
            return CollectionsKt.listOf(requestId);
        }

        public final /* synthetic */ List apiFlags$hyperkyc_release() {
            ApiFlags apiFlags;
            List<ApiFlags> list = this.apiFlags;
            if (list != null) {
                return list;
            }
            APIData aPIData = this.apiData;
            if (aPIData == null || (apiFlags = aPIData.getApiFlags(this.tag)) == null) {
                return null;
            }
            return CollectionsKt.listOf(apiFlags);
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 92\u00020\u0001:\u00019BG\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u001c\b\u0002\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t\u0018\u00010\b¢\u0006\u0002\u0010\nJ\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0004\b\u001b\u0010\u0010J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b\u001dJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b\u001fJ\"\u0010 \u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t\u0018\u00010\bHÀ\u0003¢\u0006\u0002\b!JP\u0010\"\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001c\b\u0002\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010#J\t\u0010$\u001a\u00020\u0003HÖ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(HÖ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010+\u001a\u00020\u0005J\b\u0010,\u001a\u0004\u0018\u00010\u0005J\t\u0010-\u001a\u00020\u0003HÖ\u0001J\u001e\u0010.\u001a\u00020&2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u00100\u001a\u00020&J\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\u001a\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t\u0018\u00010\bJ\b\u00101\u001a\u0004\u0018\u00010\u0005J\b\u00102\u001a\u0004\u0018\u00010\u0005J\t\u00103\u001a\u00020\u0005HÖ\u0001J\u0019\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R.\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t\u0018\u00010\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\f\"\u0004\b\u0019\u0010\u000e¨\u0006:"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$APIData;", "Landroid/os/Parcelable;", "responseCode", "", "responseMessage", "", "responseBodyRaw", "responseHeaders", "", "", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getResponseBodyRaw$hyperkyc_release", "()Ljava/lang/String;", "setResponseBodyRaw$hyperkyc_release", "(Ljava/lang/String;)V", "getResponseCode$hyperkyc_release", "()Ljava/lang/Integer;", "setResponseCode$hyperkyc_release", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getResponseHeaders$hyperkyc_release", "()Ljava/util/Map;", "setResponseHeaders$hyperkyc_release", "(Ljava/util/Map;)V", "getResponseMessage$hyperkyc_release", "setResponseMessage$hyperkyc_release", "component1", "component1$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "component4", "component4$hyperkyc_release", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$APIData;", "describeContents", "equals", "", "other", "", "getApiFlags", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$ApiFlags;", "moduleId", "getRequestId", "hashCode", "isSuccess", "allowedStatusCodes", "shouldCheckResponseCode", Keys.STATUS_CODE, "statusMessage", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class APIData implements Parcelable {
        private String responseBodyRaw;
        private Integer responseCode;
        private Map<String, ? extends List<String>> responseHeaders;
        private String responseMessage;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final Parcelable.Creator<APIData> CREATOR = new Creator();

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<APIData> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final APIData createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                LinkedHashMap linkedHashMap = null;
                Integer valueOf = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                if (parcel.readInt() != 0) {
                    int readInt = parcel.readInt();
                    LinkedHashMap linkedHashMap2 = new LinkedHashMap(readInt);
                    for (int i = 0; i != readInt; i++) {
                        linkedHashMap2.put(parcel.readString(), parcel.createStringArrayList());
                    }
                    linkedHashMap = linkedHashMap2;
                }
                return new APIData(valueOf, readString, readString2, linkedHashMap);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final APIData[] newArray(int i) {
                return new APIData[i];
            }
        }

        public APIData() {
            this(null, null, null, null, 15, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ APIData copy$default(APIData aPIData, Integer num, String str, String str2, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                num = aPIData.responseCode;
            }
            if ((i & 2) != 0) {
                str = aPIData.responseMessage;
            }
            if ((i & 4) != 0) {
                str2 = aPIData.responseBodyRaw;
            }
            if ((i & 8) != 0) {
                map = aPIData.responseHeaders;
            }
            return aPIData.copy(num, str, str2, map);
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final Integer getResponseCode() {
            return this.responseCode;
        }

        /* renamed from: component2$hyperkyc_release, reason: from getter */
        public final String getResponseMessage() {
            return this.responseMessage;
        }

        /* renamed from: component3$hyperkyc_release, reason: from getter */
        public final String getResponseBodyRaw() {
            return this.responseBodyRaw;
        }

        public final Map<String, List<String>> component4$hyperkyc_release() {
            return this.responseHeaders;
        }

        public final APIData copy(Integer responseCode, String responseMessage, String responseBodyRaw, Map<String, ? extends List<String>> responseHeaders) {
            return new APIData(responseCode, responseMessage, responseBodyRaw, responseHeaders);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof APIData)) {
                return false;
            }
            APIData aPIData = (APIData) other;
            return Intrinsics.areEqual(this.responseCode, aPIData.responseCode) && Intrinsics.areEqual(this.responseMessage, aPIData.responseMessage) && Intrinsics.areEqual(this.responseBodyRaw, aPIData.responseBodyRaw) && Intrinsics.areEqual(this.responseHeaders, aPIData.responseHeaders);
        }

        public int hashCode() {
            Integer num = this.responseCode;
            int hashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.responseMessage;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.responseBodyRaw;
            int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Map<String, ? extends List<String>> map = this.responseHeaders;
            return hashCode3 + (map != null ? map.hashCode() : 0);
        }

        public String toString() {
            return "APIData(responseCode=" + this.responseCode + ", responseMessage=" + this.responseMessage + ", responseBodyRaw=" + this.responseBodyRaw + ", responseHeaders=" + this.responseHeaders + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            Integer num = this.responseCode;
            if (num == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcel.writeInt(num.intValue());
            }
            parcel.writeString(this.responseMessage);
            parcel.writeString(this.responseBodyRaw);
            Map<String, ? extends List<String>> map = this.responseHeaders;
            if (map == null) {
                parcel.writeInt(0);
                return;
            }
            parcel.writeInt(1);
            parcel.writeInt(map.size());
            for (Map.Entry<String, ? extends List<String>> entry : map.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeStringList(entry.getValue());
            }
        }

        public APIData(Integer num, String str, String str2, Map<String, ? extends List<String>> map) {
            this.responseCode = num;
            this.responseMessage = str;
            this.responseBodyRaw = str2;
            this.responseHeaders = map;
        }

        public /* synthetic */ APIData(Integer num, String str, String str2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : map);
        }

        public final /* synthetic */ Integer getResponseCode$hyperkyc_release() {
            return this.responseCode;
        }

        public final /* synthetic */ void setResponseCode$hyperkyc_release(Integer num) {
            this.responseCode = num;
        }

        public final /* synthetic */ String getResponseMessage$hyperkyc_release() {
            return this.responseMessage;
        }

        public final /* synthetic */ void setResponseMessage$hyperkyc_release(String str) {
            this.responseMessage = str;
        }

        public final /* synthetic */ String getResponseBodyRaw$hyperkyc_release() {
            return this.responseBodyRaw;
        }

        public final /* synthetic */ void setResponseBodyRaw$hyperkyc_release(String str) {
            this.responseBodyRaw = str;
        }

        /* renamed from: getResponseHeaders$hyperkyc_release, reason: from getter */
        public final /* synthetic */ Map getResponseHeaders() {
            return this.responseHeaders;
        }

        public final /* synthetic */ void setResponseHeaders$hyperkyc_release(Map map) {
            this.responseHeaders = map;
        }

        public static /* synthetic */ boolean isSuccess$default(APIData aPIData, List list, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            return aPIData.isSuccess(list, z);
        }

        public final boolean isSuccess(List<Integer> allowedStatusCodes, boolean shouldCheckResponseCode) {
            Object m1202constructorimpl;
            JSONObject optJSONObject;
            Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
            try {
                Result.Companion companion = Result.INSTANCE;
                APIData aPIData = this;
                String str = this.responseBodyRaw;
                Intrinsics.checkNotNull(str);
                JSONObject jSONObject = new JSONObject(str);
                JSONObject optJSONObject2 = jSONObject.optJSONObject(Constant.PARAM_RESULT);
                String optString = (optJSONObject2 == null || (optJSONObject = optJSONObject2.optJSONObject("summary")) == null) ? null : optJSONObject.optString("action");
                String statusString = jSONObject.optString(Keys.STATUS_CODE);
                Intrinsics.checkNotNullExpressionValue(statusString, "statusString");
                boolean z = true;
                if ((statusString.length() == 0) && shouldCheckResponseCode) {
                    z = CollectionsKt.contains(allowedStatusCodes, this.responseCode);
                } else {
                    String optString2 = jSONObject.optString(Keys.STATUS_CODE);
                    Intrinsics.checkNotNullExpressionValue(optString2, "response.optString(\"statusCode\")");
                    int parseInt = Integer.parseInt(optString2);
                    if (optString == null && !allowedStatusCodes.contains(Integer.valueOf(parseInt))) {
                        z = false;
                    }
                }
                m1202constructorimpl = Result.m1202constructorimpl(Boolean.valueOf(z));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = false;
            }
            return ((Boolean) m1202constructorimpl).booleanValue();
        }

        public final String getRequestId() {
            String str;
            Map<String, ? extends List<String>> map = this.responseHeaders;
            if (map != null) {
                List<String> list = map.get("x-request-id");
                if (list == null) {
                    list = map.get(AppConstants.HV_REQUEST_ID);
                }
                if (list != null && (str = (String) CollectionsKt.firstOrNull((List) list)) != null) {
                    return CoreExtsKt.nullIfBlank(str);
                }
            }
            return null;
        }

        public final ApiFlags getApiFlags(String moduleId) {
            LinkedHashMap linkedHashMap;
            Object obj;
            Intrinsics.checkNotNullParameter(moduleId, "moduleId");
            Companion companion = HyperKycData.INSTANCE;
            Map<String, ? extends List<String>> map = this.responseHeaders;
            if (map != null) {
                LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
                Iterator<T> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    linkedHashMap2.put(entry.getKey(), (String) CollectionsKt.first((List) entry.getValue()));
                }
                linkedHashMap = linkedHashMap2;
            } else {
                linkedHashMap = null;
            }
            String str = this.responseBodyRaw;
            String statusCode = statusCode();
            try {
                Result.Companion companion2 = Result.INSTANCE;
                APIData aPIData = this;
                obj = Result.m1202constructorimpl(JSONExtsKt.extractJsonValue(this.responseBodyRaw, "error"));
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                obj = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            boolean m1208isFailureimpl = Result.m1208isFailureimpl(obj);
            Object obj2 = obj;
            if (m1208isFailureimpl) {
                obj2 = null;
            }
            Map<String, String> extractApiFlags$hyperkyc_release = companion.extractApiFlags$hyperkyc_release(moduleId, linkedHashMap, str, statusCode, (String) obj2);
            if (extractApiFlags$hyperkyc_release == null || extractApiFlags$hyperkyc_release.isEmpty()) {
                return null;
            }
            return new ApiFlags(moduleId, null, extractApiFlags$hyperkyc_release, 2, null);
        }

        public final String statusCode() {
            Integer num = this.responseCode;
            if (num != null) {
                return num.toString();
            }
            return null;
        }

        public final String statusMessage() {
            return this.responseMessage;
        }

        public final String responseBodyRaw() {
            return this.responseBodyRaw;
        }

        public final Map<String, List<String>> responseHeaders() {
            return this.responseHeaders;
        }

        /* compiled from: HyperKycData.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$APIData$Companion;", "", "()V", "from", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$APIData;", "response", "Lokhttp3/Response;", "from$hyperkyc_release", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final /* synthetic */ APIData from$hyperkyc_release(Response response) {
                Intrinsics.checkNotNullParameter(response, "response");
                APIData aPIData = new APIData(null, null, null, null, 15, null);
                aPIData.setResponseCode$hyperkyc_release(Integer.valueOf(response.code()));
                aPIData.setResponseMessage$hyperkyc_release(response.message());
                ResponseBody body = response.body();
                aPIData.setResponseBodyRaw$hyperkyc_release(body != null ? body.string() : null);
                aPIData.setResponseHeaders$hyperkyc_release(response.headers().toMultimap());
                return aPIData;
            }
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00122\b\u0002\u0010\u0004\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\u0006¢\u0006\u0002\b\u00070\u0005j\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\u0006¢\u0006\u0002\b\u0007`\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0006\u0010\t\u001a\u00020\u0003J\u000e\u0010\u0016\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b\u0017J8\u0010\u0018\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\u0006¢\u0006\u0002\b\u00070\u0005j\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\u0006¢\u0006\u0002\b\u0007`\bHÀ\u0003¢\u0006\u0002\b\u0019J\u000e\u0010\u001a\u001a\u00020\nHÀ\u0003¢\u0006\u0002\b\u001bJQ\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u000322\b\u0002\u0010\u0004\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\u0006¢\u0006\u0002\b\u00070\u0005j\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\u0006¢\u0006\u0002\b\u0007`\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\t\u0010\u001d\u001a\u00020\nHÖ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010!\u001a\u00020\nHÖ\u0001J\u0006\u0010\u0002\u001a\u00020\u0003J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\u0019\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\nHÖ\u0001R\u001a\u0010\t\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011RD\u0010\u0004\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\u0006¢\u0006\u0002\b\u00070\u0005j\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\u0006¢\u0006\u0002\b\u0007`\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006("}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$FormResult;", "Landroid/os/Parcelable;", "tag", "", "variables", "Ljava/util/HashMap;", "", "Lkotlinx/parcelize/RawValue;", "Lkotlin/collections/HashMap;", AppConstants.ATTEMPTS_KEY, "", "(Ljava/lang/String;Ljava/util/HashMap;I)V", "getAttempts$hyperkyc_release", "()I", "setAttempts$hyperkyc_release", "(I)V", "getTag$hyperkyc_release", "()Ljava/lang/String;", "getVariables$hyperkyc_release", "()Ljava/util/HashMap;", "setVariables$hyperkyc_release", "(Ljava/util/HashMap;)V", "component1", "component1$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "copy", "describeContents", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class FormResult implements Parcelable {
        public static final Parcelable.Creator<FormResult> CREATOR = new Creator();
        private int attempts;

        @SerializedName("moduleId")
        private final String tag;
        private HashMap<String, Object> variables;

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<FormResult> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final FormResult createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String readString = parcel.readString();
                int readInt = parcel.readInt();
                HashMap hashMap = new HashMap(readInt);
                for (int i = 0; i != readInt; i++) {
                    hashMap.put(parcel.readString(), parcel.readValue(FormResult.class.getClassLoader()));
                }
                return new FormResult(readString, hashMap, parcel.readInt());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final FormResult[] newArray(int i) {
                return new FormResult[i];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FormResult copy$default(FormResult formResult, String str, HashMap hashMap, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = formResult.tag;
            }
            if ((i2 & 2) != 0) {
                hashMap = formResult.variables;
            }
            if ((i2 & 4) != 0) {
                i = formResult.attempts;
            }
            return formResult.copy(str, hashMap, i);
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final String getTag() {
            return this.tag;
        }

        public final HashMap<String, Object> component2$hyperkyc_release() {
            return this.variables;
        }

        /* renamed from: component3$hyperkyc_release, reason: from getter */
        public final int getAttempts() {
            return this.attempts;
        }

        public final FormResult copy(String tag, HashMap<String, Object> variables, int attempts) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(variables, "variables");
            return new FormResult(tag, variables, attempts);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FormResult)) {
                return false;
            }
            FormResult formResult = (FormResult) other;
            return Intrinsics.areEqual(this.tag, formResult.tag) && Intrinsics.areEqual(this.variables, formResult.variables) && this.attempts == formResult.attempts;
        }

        public int hashCode() {
            return (((this.tag.hashCode() * 31) + this.variables.hashCode()) * 31) + this.attempts;
        }

        public String toString() {
            return "FormResult(tag=" + this.tag + ", variables=" + this.variables + ", attempts=" + this.attempts + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.tag);
            HashMap<String, Object> hashMap = this.variables;
            parcel.writeInt(hashMap.size());
            for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeValue(entry.getValue());
            }
            parcel.writeInt(this.attempts);
        }

        public FormResult(String tag, HashMap<String, Object> variables, int i) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(variables, "variables");
            this.tag = tag;
            this.variables = variables;
            this.attempts = i;
        }

        public final /* synthetic */ String getTag$hyperkyc_release() {
            return this.tag;
        }

        public /* synthetic */ FormResult(String str, HashMap hashMap, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i2 & 2) != 0 ? new HashMap() : hashMap, (i2 & 4) != 0 ? 0 : i);
        }

        /* renamed from: getVariables$hyperkyc_release, reason: from getter */
        public final /* synthetic */ HashMap getVariables() {
            return this.variables;
        }

        public final void setVariables$hyperkyc_release(HashMap<String, Object> hashMap) {
            Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
            this.variables = hashMap;
        }

        public final /* synthetic */ int getAttempts$hyperkyc_release() {
            return this.attempts;
        }

        public final void setAttempts$hyperkyc_release(int i) {
            this.attempts = i;
        }

        public final String tag() {
            return this.tag;
        }

        public final String attempts() {
            return String.valueOf(this.attempts);
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00122\b\u0002\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\f¢\u0006\u0002\u0010\rJ\u0006\u0010\u0006\u001a\u00020\u0003J\u000e\u0010\u001a\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b\u001bJ\u000e\u0010\u001c\u001a\u00020\u0005HÀ\u0003¢\u0006\u0002\b\u001dJ\u000e\u0010\u001e\u001a\u00020\u0007HÀ\u0003¢\u0006\u0002\b\u001fJ8\u0010 \u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\fHÀ\u0003¢\u0006\u0002\b!J[\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u000722\b\u0002\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\fHÆ\u0001J\t\u0010#\u001a\u00020\u0007HÖ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\nHÖ\u0003J\t\u0010'\u001a\u00020\u0007HÖ\u0001J\u0006\u0010\u0002\u001a\u00020\u0003J\t\u0010(\u001a\u00020\u0003HÖ\u0001J\u0006\u0010\u0004\u001a\u00020\u0005J\u0019\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0007HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013RD\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006."}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$WebviewResult;", "Landroid/os/Parcelable;", "tag", "", "webviewData", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$WebviewData;", AppConstants.ATTEMPTS_KEY, "", "variables", "Ljava/util/HashMap;", "", "Lkotlinx/parcelize/RawValue;", "Lkotlin/collections/HashMap;", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$WebviewData;ILjava/util/HashMap;)V", "getAttempts$hyperkyc_release", "()I", "setAttempts$hyperkyc_release", "(I)V", "getTag$hyperkyc_release", "()Ljava/lang/String;", "getVariables$hyperkyc_release", "()Ljava/util/HashMap;", "setVariables$hyperkyc_release", "(Ljava/util/HashMap;)V", "getWebviewData$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$WebviewData;", "component1", "component1$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "component4", "component4$hyperkyc_release", "copy", "describeContents", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class WebviewResult implements Parcelable {
        public static final Parcelable.Creator<WebviewResult> CREATOR = new Creator();
        private int attempts;

        @SerializedName("moduleId")
        private final String tag;
        private HashMap<String, Object> variables;
        private final WebviewData webviewData;

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<WebviewResult> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final WebviewResult createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String readString = parcel.readString();
                WebviewData createFromParcel = WebviewData.CREATOR.createFromParcel(parcel);
                int readInt = parcel.readInt();
                int readInt2 = parcel.readInt();
                HashMap hashMap = new HashMap(readInt2);
                for (int i = 0; i != readInt2; i++) {
                    hashMap.put(parcel.readString(), parcel.readValue(WebviewResult.class.getClassLoader()));
                }
                return new WebviewResult(readString, createFromParcel, readInt, hashMap);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final WebviewResult[] newArray(int i) {
                return new WebviewResult[i];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ WebviewResult copy$default(WebviewResult webviewResult, String str, WebviewData webviewData, int i, HashMap hashMap, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = webviewResult.tag;
            }
            if ((i2 & 2) != 0) {
                webviewData = webviewResult.webviewData;
            }
            if ((i2 & 4) != 0) {
                i = webviewResult.attempts;
            }
            if ((i2 & 8) != 0) {
                hashMap = webviewResult.variables;
            }
            return webviewResult.copy(str, webviewData, i, hashMap);
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final String getTag() {
            return this.tag;
        }

        /* renamed from: component2$hyperkyc_release, reason: from getter */
        public final WebviewData getWebviewData() {
            return this.webviewData;
        }

        /* renamed from: component3$hyperkyc_release, reason: from getter */
        public final int getAttempts() {
            return this.attempts;
        }

        public final HashMap<String, Object> component4$hyperkyc_release() {
            return this.variables;
        }

        public final WebviewResult copy(String tag, WebviewData webviewData, int attempts, HashMap<String, Object> variables) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(webviewData, "webviewData");
            Intrinsics.checkNotNullParameter(variables, "variables");
            return new WebviewResult(tag, webviewData, attempts, variables);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WebviewResult)) {
                return false;
            }
            WebviewResult webviewResult = (WebviewResult) other;
            return Intrinsics.areEqual(this.tag, webviewResult.tag) && Intrinsics.areEqual(this.webviewData, webviewResult.webviewData) && this.attempts == webviewResult.attempts && Intrinsics.areEqual(this.variables, webviewResult.variables);
        }

        public int hashCode() {
            return (((((this.tag.hashCode() * 31) + this.webviewData.hashCode()) * 31) + this.attempts) * 31) + this.variables.hashCode();
        }

        public String toString() {
            return "WebviewResult(tag=" + this.tag + ", webviewData=" + this.webviewData + ", attempts=" + this.attempts + ", variables=" + this.variables + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.tag);
            this.webviewData.writeToParcel(parcel, flags);
            parcel.writeInt(this.attempts);
            HashMap<String, Object> hashMap = this.variables;
            parcel.writeInt(hashMap.size());
            for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeValue(entry.getValue());
            }
        }

        public WebviewResult(String tag, WebviewData webviewData, int i, HashMap<String, Object> variables) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(webviewData, "webviewData");
            Intrinsics.checkNotNullParameter(variables, "variables");
            this.tag = tag;
            this.webviewData = webviewData;
            this.attempts = i;
            this.variables = variables;
        }

        public final /* synthetic */ String getTag$hyperkyc_release() {
            return this.tag;
        }

        public final /* synthetic */ WebviewData getWebviewData$hyperkyc_release() {
            return this.webviewData;
        }

        public final /* synthetic */ int getAttempts$hyperkyc_release() {
            return this.attempts;
        }

        public final void setAttempts$hyperkyc_release(int i) {
            this.attempts = i;
        }

        public /* synthetic */ WebviewResult(String str, WebviewData webviewData, int i, HashMap hashMap, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, webviewData, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? new HashMap() : hashMap);
        }

        /* renamed from: getVariables$hyperkyc_release, reason: from getter */
        public final /* synthetic */ HashMap getVariables() {
            return this.variables;
        }

        public final void setVariables$hyperkyc_release(HashMap<String, Object> hashMap) {
            Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
            this.variables = hashMap;
        }

        public final String tag() {
            return this.tag;
        }

        public final WebviewData webviewData() {
            return this.webviewData;
        }

        public final String attempts() {
            return String.valueOf(this.attempts);
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b\tJ\u0015\u0010\n\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\fHÖ\u0001J\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\fHÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0018"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$WebviewData;", "Landroid/os/Parcelable;", Constants.MessagePayloadKeys.RAW_DATA, "", "(Ljava/lang/String;)V", "getRawData$hyperkyc_release", "()Ljava/lang/String;", "setRawData$hyperkyc_release", "component1", "component1$hyperkyc_release", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class WebviewData implements Parcelable {
        public static final Parcelable.Creator<WebviewData> CREATOR = new Creator();
        private String rawData;

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<WebviewData> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final WebviewData createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new WebviewData(parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final WebviewData[] newArray(int i) {
                return new WebviewData[i];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public WebviewData() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ WebviewData copy$default(WebviewData webviewData, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = webviewData.rawData;
            }
            return webviewData.copy(str);
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final String getRawData() {
            return this.rawData;
        }

        public final WebviewData copy(String rawData) {
            return new WebviewData(rawData);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof WebviewData) && Intrinsics.areEqual(this.rawData, ((WebviewData) other).rawData);
        }

        public int hashCode() {
            String str = this.rawData;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "WebviewData(rawData=" + this.rawData + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.rawData);
        }

        public WebviewData(String str) {
            this.rawData = str;
        }

        public /* synthetic */ WebviewData(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str);
        }

        public final /* synthetic */ String getRawData$hyperkyc_release() {
            return this.rawData;
        }

        public final /* synthetic */ void setRawData$hyperkyc_release(String str) {
            this.rawData = str;
        }

        public final String rawData() {
            return this.rawData;
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00122\b\u0002\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\f¢\u0006\u0002\u0010\rJ\u0006\u0010\u0006\u001a\u00020\u0003J\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003J\u000e\u0010\u001d\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b\u001eJ\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b J\u0010\u0010!\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b\"J\u000e\u0010#\u001a\u00020\u0007HÀ\u0003¢\u0006\u0002\b$J8\u0010%\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\fHÀ\u0003¢\u0006\u0002\b&Ji\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u000722\b\u0002\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\fHÆ\u0001J\t\u0010(\u001a\u00020\u0007HÖ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\nHÖ\u0003J\t\u0010,\u001a\u00020\u0007HÖ\u0001J\u0006\u0010\u0002\u001a\u00020\u0003J\t\u0010-\u001a\u00020\u0003HÖ\u0001J\u0019\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u0007HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013RD\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u00063"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$BarcodeResult;", "Landroid/os/Parcelable;", "tag", "", "barcodeData", "barcodeStatus", AppConstants.ATTEMPTS_KEY, "", "variables", "Ljava/util/HashMap;", "", "Lkotlinx/parcelize/RawValue;", "Lkotlin/collections/HashMap;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/HashMap;)V", "getAttempts$hyperkyc_release", "()I", "setAttempts$hyperkyc_release", "(I)V", "getBarcodeData$hyperkyc_release", "()Ljava/lang/String;", "setBarcodeData$hyperkyc_release", "(Ljava/lang/String;)V", "getBarcodeStatus$hyperkyc_release", "setBarcodeStatus$hyperkyc_release", "getTag$hyperkyc_release", "getVariables$hyperkyc_release", "()Ljava/util/HashMap;", "setVariables$hyperkyc_release", "(Ljava/util/HashMap;)V", "component1", "component1$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "component4", "component4$hyperkyc_release", "component5", "component5$hyperkyc_release", "copy", "describeContents", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class BarcodeResult implements Parcelable {
        public static final Parcelable.Creator<BarcodeResult> CREATOR = new Creator();
        private int attempts;
        private String barcodeData;
        private String barcodeStatus;

        @SerializedName("moduleId")
        private final String tag;
        private HashMap<String, Object> variables;

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<BarcodeResult> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final BarcodeResult createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                String readString3 = parcel.readString();
                int readInt = parcel.readInt();
                int readInt2 = parcel.readInt();
                HashMap hashMap = new HashMap(readInt2);
                for (int i = 0; i != readInt2; i++) {
                    hashMap.put(parcel.readString(), parcel.readValue(BarcodeResult.class.getClassLoader()));
                }
                return new BarcodeResult(readString, readString2, readString3, readInt, hashMap);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final BarcodeResult[] newArray(int i) {
                return new BarcodeResult[i];
            }
        }

        public static /* synthetic */ BarcodeResult copy$default(BarcodeResult barcodeResult, String str, String str2, String str3, int i, HashMap hashMap, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = barcodeResult.tag;
            }
            if ((i2 & 2) != 0) {
                str2 = barcodeResult.barcodeData;
            }
            String str4 = str2;
            if ((i2 & 4) != 0) {
                str3 = barcodeResult.barcodeStatus;
            }
            String str5 = str3;
            if ((i2 & 8) != 0) {
                i = barcodeResult.attempts;
            }
            int i3 = i;
            if ((i2 & 16) != 0) {
                hashMap = barcodeResult.variables;
            }
            return barcodeResult.copy(str, str4, str5, i3, hashMap);
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final String getTag() {
            return this.tag;
        }

        public final String component2$hyperkyc_release() {
            return this.barcodeData;
        }

        /* renamed from: component3$hyperkyc_release, reason: from getter */
        public final String getBarcodeStatus() {
            return this.barcodeStatus;
        }

        /* renamed from: component4$hyperkyc_release, reason: from getter */
        public final int getAttempts() {
            return this.attempts;
        }

        public final HashMap<String, Object> component5$hyperkyc_release() {
            return this.variables;
        }

        public final BarcodeResult copy(String tag, String barcodeData, String barcodeStatus, int attempts, HashMap<String, Object> variables) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(variables, "variables");
            return new BarcodeResult(tag, barcodeData, barcodeStatus, attempts, variables);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BarcodeResult)) {
                return false;
            }
            BarcodeResult barcodeResult = (BarcodeResult) other;
            return Intrinsics.areEqual(this.tag, barcodeResult.tag) && Intrinsics.areEqual(this.barcodeData, barcodeResult.barcodeData) && Intrinsics.areEqual(this.barcodeStatus, barcodeResult.barcodeStatus) && this.attempts == barcodeResult.attempts && Intrinsics.areEqual(this.variables, barcodeResult.variables);
        }

        public int hashCode() {
            int hashCode = this.tag.hashCode() * 31;
            String str = this.barcodeData;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.barcodeStatus;
            return ((((hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.attempts) * 31) + this.variables.hashCode();
        }

        public String toString() {
            return "BarcodeResult(tag=" + this.tag + ", barcodeData=" + this.barcodeData + ", barcodeStatus=" + this.barcodeStatus + ", attempts=" + this.attempts + ", variables=" + this.variables + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.tag);
            parcel.writeString(this.barcodeData);
            parcel.writeString(this.barcodeStatus);
            parcel.writeInt(this.attempts);
            HashMap<String, Object> hashMap = this.variables;
            parcel.writeInt(hashMap.size());
            for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeValue(entry.getValue());
            }
        }

        public BarcodeResult(String tag, String str, String str2, int i, HashMap<String, Object> variables) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(variables, "variables");
            this.tag = tag;
            this.barcodeData = str;
            this.barcodeStatus = str2;
            this.attempts = i;
            this.variables = variables;
        }

        public final /* synthetic */ String getTag$hyperkyc_release() {
            return this.tag;
        }

        public final /* synthetic */ String getBarcodeData$hyperkyc_release() {
            return this.barcodeData;
        }

        public final /* synthetic */ void setBarcodeData$hyperkyc_release(String str) {
            this.barcodeData = str;
        }

        public final /* synthetic */ String getBarcodeStatus$hyperkyc_release() {
            return this.barcodeStatus;
        }

        public final /* synthetic */ void setBarcodeStatus$hyperkyc_release(String str) {
            this.barcodeStatus = str;
        }

        public final /* synthetic */ int getAttempts$hyperkyc_release() {
            return this.attempts;
        }

        public final /* synthetic */ void setAttempts$hyperkyc_release(int i) {
            this.attempts = i;
        }

        public /* synthetic */ BarcodeResult(String str, String str2, String str3, int i, HashMap hashMap, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? 0 : i, (i2 & 16) != 0 ? new HashMap() : hashMap);
        }

        /* renamed from: getVariables$hyperkyc_release, reason: from getter */
        public final /* synthetic */ HashMap getVariables() {
            return this.variables;
        }

        public final void setVariables$hyperkyc_release(HashMap<String, Object> hashMap) {
            Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
            this.variables = hashMap;
        }

        public final String tag() {
            return this.tag;
        }

        /* renamed from: barcodeData, reason: from getter */
        public final String getBarcodeData() {
            return this.barcodeData;
        }

        public final String attempts() {
            return String.valueOf(this.attempts);
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u00122\b\u0002\u0010\u000b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\r¢\u0006\u0002\b\u000e0\fj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\r¢\u0006\u0002\b\u000e`\u000f¢\u0006\u0002\u0010\u0010J\u0006\u0010\n\u001a\u00020\u0003J\u000e\u0010)\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b*J\u0010\u0010+\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b,J\u0010\u0010-\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b.J\u0010\u0010/\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b0J\u0012\u00101\u001a\u0004\u0018\u00010\bHÀ\u0003¢\u0006\u0004\b2\u0010\u001aJ\u0010\u00103\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b4J\u000e\u00105\u001a\u00020\bHÀ\u0003¢\u0006\u0002\b6J8\u00107\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\r¢\u0006\u0002\b\u000e0\fj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\r¢\u0006\u0002\b\u000e`\u000fHÀ\u0003¢\u0006\u0002\b8J\u0092\u0001\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\n\u001a\u00020\b22\b\u0002\u0010\u000b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\r¢\u0006\u0002\b\u000e0\fj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\r¢\u0006\u0002\b\u000e`\u000fHÆ\u0001¢\u0006\u0002\u0010:J\t\u0010;\u001a\u00020\bHÖ\u0001J\u0013\u0010<\u001a\u00020=2\b\u0010>\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010?\u001a\u00020\bHÖ\u0001J\u0006\u0010\u0002\u001a\u00020\u0003J\t\u0010@\u001a\u00020\u0003HÖ\u0001J\u0019\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020\bHÖ\u0001R\u001a\u0010\n\u001a\u00020\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0016\"\u0004\b\u001f\u0010\u0018R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0016\"\u0004\b!\u0010\u0018R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0016\"\u0004\b#\u0010\u0018R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0016RD\u0010\u000b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\r¢\u0006\u0002\b\u000e0\fj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\r¢\u0006\u0002\b\u000e`\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u0006F"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$NFCResult;", "Landroid/os/Parcelable;", "tag", "", "nfcStatus", "nfcData", "nfcErrorMessage", "nfcErrorCode", "", "nfcLastStep", AppConstants.ATTEMPTS_KEY, "variables", "Ljava/util/HashMap;", "", "Lkotlinx/parcelize/RawValue;", "Lkotlin/collections/HashMap;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;ILjava/util/HashMap;)V", "getAttempts$hyperkyc_release", "()I", "setAttempts$hyperkyc_release", "(I)V", "getNfcData$hyperkyc_release", "()Ljava/lang/String;", "setNfcData$hyperkyc_release", "(Ljava/lang/String;)V", "getNfcErrorCode$hyperkyc_release", "()Ljava/lang/Integer;", "setNfcErrorCode$hyperkyc_release", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getNfcErrorMessage$hyperkyc_release", "setNfcErrorMessage$hyperkyc_release", "getNfcLastStep$hyperkyc_release", "setNfcLastStep$hyperkyc_release", "getNfcStatus$hyperkyc_release", "setNfcStatus$hyperkyc_release", "getTag$hyperkyc_release", "getVariables$hyperkyc_release", "()Ljava/util/HashMap;", "setVariables$hyperkyc_release", "(Ljava/util/HashMap;)V", "component1", "component1$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "component4", "component4$hyperkyc_release", "component5", "component5$hyperkyc_release", "component6", "component6$hyperkyc_release", "component7", "component7$hyperkyc_release", "component8", "component8$hyperkyc_release", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;ILjava/util/HashMap;)Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$NFCResult;", "describeContents", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class NFCResult implements Parcelable {
        public static final Parcelable.Creator<NFCResult> CREATOR = new Creator();
        private int attempts;
        private String nfcData;
        private Integer nfcErrorCode;
        private String nfcErrorMessage;
        private String nfcLastStep;
        private String nfcStatus;

        @SerializedName("moduleId")
        private final String tag;
        private HashMap<String, Object> variables;

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<NFCResult> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final NFCResult createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                String readString3 = parcel.readString();
                String readString4 = parcel.readString();
                Integer valueOf = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
                String readString5 = parcel.readString();
                int readInt = parcel.readInt();
                int readInt2 = parcel.readInt();
                HashMap hashMap = new HashMap(readInt2);
                for (int i = 0; i != readInt2; i++) {
                    hashMap.put(parcel.readString(), parcel.readValue(NFCResult.class.getClassLoader()));
                }
                return new NFCResult(readString, readString2, readString3, readString4, valueOf, readString5, readInt, hashMap);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final NFCResult[] newArray(int i) {
                return new NFCResult[i];
            }
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final String getTag() {
            return this.tag;
        }

        /* renamed from: component2$hyperkyc_release, reason: from getter */
        public final String getNfcStatus() {
            return this.nfcStatus;
        }

        /* renamed from: component3$hyperkyc_release, reason: from getter */
        public final String getNfcData() {
            return this.nfcData;
        }

        /* renamed from: component4$hyperkyc_release, reason: from getter */
        public final String getNfcErrorMessage() {
            return this.nfcErrorMessage;
        }

        /* renamed from: component5$hyperkyc_release, reason: from getter */
        public final Integer getNfcErrorCode() {
            return this.nfcErrorCode;
        }

        /* renamed from: component6$hyperkyc_release, reason: from getter */
        public final String getNfcLastStep() {
            return this.nfcLastStep;
        }

        /* renamed from: component7$hyperkyc_release, reason: from getter */
        public final int getAttempts() {
            return this.attempts;
        }

        public final HashMap<String, Object> component8$hyperkyc_release() {
            return this.variables;
        }

        public final NFCResult copy(String tag, String nfcStatus, String nfcData, String nfcErrorMessage, Integer nfcErrorCode, String nfcLastStep, int attempts, HashMap<String, Object> variables) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(variables, "variables");
            return new NFCResult(tag, nfcStatus, nfcData, nfcErrorMessage, nfcErrorCode, nfcLastStep, attempts, variables);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NFCResult)) {
                return false;
            }
            NFCResult nFCResult = (NFCResult) other;
            return Intrinsics.areEqual(this.tag, nFCResult.tag) && Intrinsics.areEqual(this.nfcStatus, nFCResult.nfcStatus) && Intrinsics.areEqual(this.nfcData, nFCResult.nfcData) && Intrinsics.areEqual(this.nfcErrorMessage, nFCResult.nfcErrorMessage) && Intrinsics.areEqual(this.nfcErrorCode, nFCResult.nfcErrorCode) && Intrinsics.areEqual(this.nfcLastStep, nFCResult.nfcLastStep) && this.attempts == nFCResult.attempts && Intrinsics.areEqual(this.variables, nFCResult.variables);
        }

        public int hashCode() {
            int hashCode = this.tag.hashCode() * 31;
            String str = this.nfcStatus;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.nfcData;
            int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.nfcErrorMessage;
            int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            Integer num = this.nfcErrorCode;
            int hashCode5 = (hashCode4 + (num == null ? 0 : num.hashCode())) * 31;
            String str4 = this.nfcLastStep;
            return ((((hashCode5 + (str4 != null ? str4.hashCode() : 0)) * 31) + this.attempts) * 31) + this.variables.hashCode();
        }

        public String toString() {
            return "NFCResult(tag=" + this.tag + ", nfcStatus=" + this.nfcStatus + ", nfcData=" + this.nfcData + ", nfcErrorMessage=" + this.nfcErrorMessage + ", nfcErrorCode=" + this.nfcErrorCode + ", nfcLastStep=" + this.nfcLastStep + ", attempts=" + this.attempts + ", variables=" + this.variables + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            int intValue;
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.tag);
            parcel.writeString(this.nfcStatus);
            parcel.writeString(this.nfcData);
            parcel.writeString(this.nfcErrorMessage);
            Integer num = this.nfcErrorCode;
            if (num == null) {
                intValue = 0;
            } else {
                parcel.writeInt(1);
                intValue = num.intValue();
            }
            parcel.writeInt(intValue);
            parcel.writeString(this.nfcLastStep);
            parcel.writeInt(this.attempts);
            HashMap<String, Object> hashMap = this.variables;
            parcel.writeInt(hashMap.size());
            for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeValue(entry.getValue());
            }
        }

        public NFCResult(String tag, String str, String str2, String str3, Integer num, String str4, int i, HashMap<String, Object> variables) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(variables, "variables");
            this.tag = tag;
            this.nfcStatus = str;
            this.nfcData = str2;
            this.nfcErrorMessage = str3;
            this.nfcErrorCode = num;
            this.nfcLastStep = str4;
            this.attempts = i;
            this.variables = variables;
        }

        public final /* synthetic */ String getTag$hyperkyc_release() {
            return this.tag;
        }

        public final /* synthetic */ String getNfcStatus$hyperkyc_release() {
            return this.nfcStatus;
        }

        public final /* synthetic */ void setNfcStatus$hyperkyc_release(String str) {
            this.nfcStatus = str;
        }

        public final /* synthetic */ String getNfcData$hyperkyc_release() {
            return this.nfcData;
        }

        public final /* synthetic */ void setNfcData$hyperkyc_release(String str) {
            this.nfcData = str;
        }

        public final /* synthetic */ String getNfcErrorMessage$hyperkyc_release() {
            return this.nfcErrorMessage;
        }

        public final /* synthetic */ void setNfcErrorMessage$hyperkyc_release(String str) {
            this.nfcErrorMessage = str;
        }

        public final /* synthetic */ Integer getNfcErrorCode$hyperkyc_release() {
            return this.nfcErrorCode;
        }

        public final /* synthetic */ void setNfcErrorCode$hyperkyc_release(Integer num) {
            this.nfcErrorCode = num;
        }

        public final /* synthetic */ String getNfcLastStep$hyperkyc_release() {
            return this.nfcLastStep;
        }

        public final /* synthetic */ void setNfcLastStep$hyperkyc_release(String str) {
            this.nfcLastStep = str;
        }

        public final /* synthetic */ int getAttempts$hyperkyc_release() {
            return this.attempts;
        }

        public final /* synthetic */ void setAttempts$hyperkyc_release(int i) {
            this.attempts = i;
        }

        public /* synthetic */ NFCResult(String str, String str2, String str3, String str4, Integer num, String str5, int i, HashMap hashMap, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : num, (i2 & 32) == 0 ? str5 : null, (i2 & 64) != 0 ? 0 : i, (i2 & 128) != 0 ? new HashMap() : hashMap);
        }

        /* renamed from: getVariables$hyperkyc_release, reason: from getter */
        public final /* synthetic */ HashMap getVariables() {
            return this.variables;
        }

        public final void setVariables$hyperkyc_release(HashMap<String, Object> hashMap) {
            Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
            this.variables = hashMap;
        }

        public final String tag() {
            return this.tag;
        }

        public final String attempts() {
            return String.valueOf(this.attempts);
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00122\b\u0002\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\f\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\u0006\u0010\u0006\u001a\u00020\u0003J\u000e\u0010 \u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b!J\u000e\u0010\"\u001a\u00020\u0005HÀ\u0003¢\u0006\u0002\b#J\u000e\u0010$\u001a\u00020\u0007HÀ\u0003¢\u0006\u0002\b%J8\u0010&\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\fHÀ\u0003¢\u0006\u0002\b'J\u0016\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÀ\u0003¢\u0006\u0002\b)Jm\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u000722\b\u0002\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\f2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÆ\u0001J\t\u0010+\u001a\u00020\u0007HÖ\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\nHÖ\u0003J\t\u0010/\u001a\u00020\u0007HÖ\u0001J\u0015\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eH\u0000¢\u0006\u0002\b0J\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\u0002\u001a\u00020\u0003J\t\u00101\u001a\u00020\u0003HÖ\u0001J\u0019\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u0007HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bRD\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u00067"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$SessionResult;", "Landroid/os/Parcelable;", "tag", "", "sessionData", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$SessionData;", AppConstants.ATTEMPTS_KEY, "", "variables", "Ljava/util/HashMap;", "", "Lkotlinx/parcelize/RawValue;", "Lkotlin/collections/HashMap;", "requestIds", "", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$SessionData;ILjava/util/HashMap;Ljava/util/List;)V", "getAttempts$hyperkyc_release", "()I", "setAttempts$hyperkyc_release", "(I)V", "getRequestIds$hyperkyc_release", "()Ljava/util/List;", "setRequestIds$hyperkyc_release", "(Ljava/util/List;)V", "getSessionData$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$SessionData;", "getTag$hyperkyc_release", "()Ljava/lang/String;", "getVariables$hyperkyc_release", "()Ljava/util/HashMap;", "setVariables$hyperkyc_release", "(Ljava/util/HashMap;)V", "component1", "component1$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "component4", "component4$hyperkyc_release", "component5", "component5$hyperkyc_release", "copy", "describeContents", "equals", "", "other", "hashCode", "requestIds$hyperkyc_release", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class SessionResult implements Parcelable {
        public static final Parcelable.Creator<SessionResult> CREATOR = new Creator();
        private int attempts;
        private List<String> requestIds;
        private final SessionData sessionData;

        @SerializedName("moduleId")
        private final String tag;
        private HashMap<String, Object> variables;

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<SessionResult> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final SessionResult createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String readString = parcel.readString();
                SessionData createFromParcel = SessionData.CREATOR.createFromParcel(parcel);
                int readInt = parcel.readInt();
                int readInt2 = parcel.readInt();
                HashMap hashMap = new HashMap(readInt2);
                for (int i = 0; i != readInt2; i++) {
                    hashMap.put(parcel.readString(), parcel.readValue(SessionResult.class.getClassLoader()));
                }
                return new SessionResult(readString, createFromParcel, readInt, hashMap, parcel.createStringArrayList());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final SessionResult[] newArray(int i) {
                return new SessionResult[i];
            }
        }

        public static /* synthetic */ SessionResult copy$default(SessionResult sessionResult, String str, SessionData sessionData, int i, HashMap hashMap, List list, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = sessionResult.tag;
            }
            if ((i2 & 2) != 0) {
                sessionData = sessionResult.sessionData;
            }
            SessionData sessionData2 = sessionData;
            if ((i2 & 4) != 0) {
                i = sessionResult.attempts;
            }
            int i3 = i;
            if ((i2 & 8) != 0) {
                hashMap = sessionResult.variables;
            }
            HashMap hashMap2 = hashMap;
            if ((i2 & 16) != 0) {
                list = sessionResult.requestIds;
            }
            return sessionResult.copy(str, sessionData2, i3, hashMap2, list);
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final String getTag() {
            return this.tag;
        }

        /* renamed from: component2$hyperkyc_release, reason: from getter */
        public final SessionData getSessionData() {
            return this.sessionData;
        }

        /* renamed from: component3$hyperkyc_release, reason: from getter */
        public final int getAttempts() {
            return this.attempts;
        }

        public final HashMap<String, Object> component4$hyperkyc_release() {
            return this.variables;
        }

        public final List<String> component5$hyperkyc_release() {
            return this.requestIds;
        }

        public final SessionResult copy(String tag, SessionData sessionData, int attempts, HashMap<String, Object> variables, List<String> requestIds) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(sessionData, "sessionData");
            Intrinsics.checkNotNullParameter(variables, "variables");
            return new SessionResult(tag, sessionData, attempts, variables, requestIds);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SessionResult)) {
                return false;
            }
            SessionResult sessionResult = (SessionResult) other;
            return Intrinsics.areEqual(this.tag, sessionResult.tag) && Intrinsics.areEqual(this.sessionData, sessionResult.sessionData) && this.attempts == sessionResult.attempts && Intrinsics.areEqual(this.variables, sessionResult.variables) && Intrinsics.areEqual(this.requestIds, sessionResult.requestIds);
        }

        public int hashCode() {
            int hashCode = ((((((this.tag.hashCode() * 31) + this.sessionData.hashCode()) * 31) + this.attempts) * 31) + this.variables.hashCode()) * 31;
            List<String> list = this.requestIds;
            return hashCode + (list == null ? 0 : list.hashCode());
        }

        public String toString() {
            return "SessionResult(tag=" + this.tag + ", sessionData=" + this.sessionData + ", attempts=" + this.attempts + ", variables=" + this.variables + ", requestIds=" + this.requestIds + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.tag);
            this.sessionData.writeToParcel(parcel, flags);
            parcel.writeInt(this.attempts);
            HashMap<String, Object> hashMap = this.variables;
            parcel.writeInt(hashMap.size());
            for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeValue(entry.getValue());
            }
            parcel.writeStringList(this.requestIds);
        }

        public SessionResult(String tag, SessionData sessionData, int i, HashMap<String, Object> variables, List<String> list) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(sessionData, "sessionData");
            Intrinsics.checkNotNullParameter(variables, "variables");
            this.tag = tag;
            this.sessionData = sessionData;
            this.attempts = i;
            this.variables = variables;
            this.requestIds = list;
        }

        public final /* synthetic */ String getTag$hyperkyc_release() {
            return this.tag;
        }

        public final /* synthetic */ SessionData getSessionData$hyperkyc_release() {
            return this.sessionData;
        }

        public final /* synthetic */ int getAttempts$hyperkyc_release() {
            return this.attempts;
        }

        public final void setAttempts$hyperkyc_release(int i) {
            this.attempts = i;
        }

        public /* synthetic */ SessionResult(String str, SessionData sessionData, int i, HashMap hashMap, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, sessionData, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? new HashMap() : hashMap, (i2 & 16) != 0 ? null : list);
        }

        /* renamed from: getVariables$hyperkyc_release, reason: from getter */
        public final /* synthetic */ HashMap getVariables() {
            return this.variables;
        }

        public final void setVariables$hyperkyc_release(HashMap<String, Object> hashMap) {
            Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
            this.variables = hashMap;
        }

        /* renamed from: getRequestIds$hyperkyc_release, reason: from getter */
        public final /* synthetic */ List getRequestIds() {
            return this.requestIds;
        }

        public final /* synthetic */ void setRequestIds$hyperkyc_release(List list) {
            this.requestIds = list;
        }

        public final String tag() {
            return this.tag;
        }

        public final SessionData sessionData() {
            return this.sessionData;
        }

        public final String attempts() {
            return String.valueOf(this.attempts);
        }

        public final /* synthetic */ List requestIds$hyperkyc_release() {
            List<String> list = this.requestIds;
            if (list != null) {
                return list;
            }
            String requestId = this.sessionData.getRequestId();
            if (requestId != null) {
                return CollectionsKt.listOf(requestId);
            }
            return null;
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b$\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 >2\u00020\u0001:\u0001>B_\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u001c\b\u0002\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b\u0018\u00010\n¢\u0006\u0002\u0010\fJ\u0012\u0010 \u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0004\b!\u0010\u0014J\u0010\u0010\"\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b#J\u0010\u0010$\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b%J\u0010\u0010&\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b'J\u0010\u0010(\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b)J\"\u0010*\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b\u0018\u00010\nHÀ\u0003¢\u0006\u0002\b+Jh\u0010,\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\u001c\b\u0002\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010-J\t\u0010.\u001a\u00020\u0003HÖ\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u000102HÖ\u0003J\b\u00103\u001a\u0004\u0018\u00010\u0005J\t\u00104\u001a\u00020\u0003HÖ\u0001J\u0014\u00105\u001a\u0002002\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bJ\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005J\u001a\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b\u0018\u00010\nJ\r\u00107\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0014J\t\u00108\u001a\u00020\u0005HÖ\u0001J\u0019\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R.\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b\u0018\u00010\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000e\"\u0004\b\u001d\u0010\u0010R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000e\"\u0004\b\u001f\u0010\u0010¨\u0006?"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$SessionData;", "Landroid/os/Parcelable;", "responseCode", "", "responseBodyRaw", "", "videoPath", WorkflowModule.Properties.Section.Component.State.STATE_COMPLETED, "videoUrl", "responseHeaders", "", "", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getCompleted$hyperkyc_release", "()Ljava/lang/String;", "setCompleted$hyperkyc_release", "(Ljava/lang/String;)V", "getResponseBodyRaw$hyperkyc_release", "setResponseBodyRaw$hyperkyc_release", "getResponseCode$hyperkyc_release", "()Ljava/lang/Integer;", "setResponseCode$hyperkyc_release", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getResponseHeaders$hyperkyc_release", "()Ljava/util/Map;", "setResponseHeaders$hyperkyc_release", "(Ljava/util/Map;)V", "getVideoPath$hyperkyc_release", "setVideoPath$hyperkyc_release", "getVideoUrl$hyperkyc_release", "setVideoUrl$hyperkyc_release", "component1", "component1$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "component4", "component4$hyperkyc_release", "component5", "component5$hyperkyc_release", "component6", "component6$hyperkyc_release", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$SessionData;", "describeContents", "equals", "", "other", "", "getRequestId", "hashCode", "isSuccess", "allowedStatusCodes", Keys.STATUS_CODE, InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class SessionData implements Parcelable {
        private String completed;
        private String responseBodyRaw;
        private Integer responseCode;
        private Map<String, ? extends List<String>> responseHeaders;
        private String videoPath;
        private String videoUrl;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final Parcelable.Creator<SessionData> CREATOR = new Creator();

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<SessionData> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final SessionData createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                LinkedHashMap linkedHashMap = null;
                Integer valueOf = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                String readString3 = parcel.readString();
                String readString4 = parcel.readString();
                if (parcel.readInt() != 0) {
                    int readInt = parcel.readInt();
                    linkedHashMap = new LinkedHashMap(readInt);
                    for (int i = 0; i != readInt; i++) {
                        linkedHashMap.put(parcel.readString(), parcel.createStringArrayList());
                    }
                }
                return new SessionData(valueOf, readString, readString2, readString3, readString4, linkedHashMap);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final SessionData[] newArray(int i) {
                return new SessionData[i];
            }
        }

        public SessionData() {
            this(null, null, null, null, null, null, 63, null);
        }

        public static /* synthetic */ SessionData copy$default(SessionData sessionData, Integer num, String str, String str2, String str3, String str4, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                num = sessionData.responseCode;
            }
            if ((i & 2) != 0) {
                str = sessionData.responseBodyRaw;
            }
            String str5 = str;
            if ((i & 4) != 0) {
                str2 = sessionData.videoPath;
            }
            String str6 = str2;
            if ((i & 8) != 0) {
                str3 = sessionData.completed;
            }
            String str7 = str3;
            if ((i & 16) != 0) {
                str4 = sessionData.videoUrl;
            }
            String str8 = str4;
            if ((i & 32) != 0) {
                map = sessionData.responseHeaders;
            }
            return sessionData.copy(num, str5, str6, str7, str8, map);
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final Integer getResponseCode() {
            return this.responseCode;
        }

        /* renamed from: component2$hyperkyc_release, reason: from getter */
        public final String getResponseBodyRaw() {
            return this.responseBodyRaw;
        }

        /* renamed from: component3$hyperkyc_release, reason: from getter */
        public final String getVideoPath() {
            return this.videoPath;
        }

        /* renamed from: component4$hyperkyc_release, reason: from getter */
        public final String getCompleted() {
            return this.completed;
        }

        /* renamed from: component5$hyperkyc_release, reason: from getter */
        public final String getVideoUrl() {
            return this.videoUrl;
        }

        public final Map<String, List<String>> component6$hyperkyc_release() {
            return this.responseHeaders;
        }

        public final SessionData copy(Integer responseCode, String responseBodyRaw, String videoPath, String completed, String videoUrl, Map<String, ? extends List<String>> responseHeaders) {
            return new SessionData(responseCode, responseBodyRaw, videoPath, completed, videoUrl, responseHeaders);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SessionData)) {
                return false;
            }
            SessionData sessionData = (SessionData) other;
            return Intrinsics.areEqual(this.responseCode, sessionData.responseCode) && Intrinsics.areEqual(this.responseBodyRaw, sessionData.responseBodyRaw) && Intrinsics.areEqual(this.videoPath, sessionData.videoPath) && Intrinsics.areEqual(this.completed, sessionData.completed) && Intrinsics.areEqual(this.videoUrl, sessionData.videoUrl) && Intrinsics.areEqual(this.responseHeaders, sessionData.responseHeaders);
        }

        public int hashCode() {
            Integer num = this.responseCode;
            int hashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.responseBodyRaw;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.videoPath;
            int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.completed;
            int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.videoUrl;
            int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Map<String, ? extends List<String>> map = this.responseHeaders;
            return hashCode5 + (map != null ? map.hashCode() : 0);
        }

        public String toString() {
            return "SessionData(responseCode=" + this.responseCode + ", responseBodyRaw=" + this.responseBodyRaw + ", videoPath=" + this.videoPath + ", completed=" + this.completed + ", videoUrl=" + this.videoUrl + ", responseHeaders=" + this.responseHeaders + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            Integer num = this.responseCode;
            if (num == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcel.writeInt(num.intValue());
            }
            parcel.writeString(this.responseBodyRaw);
            parcel.writeString(this.videoPath);
            parcel.writeString(this.completed);
            parcel.writeString(this.videoUrl);
            Map<String, ? extends List<String>> map = this.responseHeaders;
            if (map == null) {
                parcel.writeInt(0);
                return;
            }
            parcel.writeInt(1);
            parcel.writeInt(map.size());
            for (Map.Entry<String, ? extends List<String>> entry : map.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeStringList(entry.getValue());
            }
        }

        public SessionData(Integer num, String str, String str2, String str3, String str4, Map<String, ? extends List<String>> map) {
            this.responseCode = num;
            this.responseBodyRaw = str;
            this.videoPath = str2;
            this.completed = str3;
            this.videoUrl = str4;
            this.responseHeaders = map;
        }

        public /* synthetic */ SessionData(Integer num, String str, String str2, String str3, String str4, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : map);
        }

        public final /* synthetic */ Integer getResponseCode$hyperkyc_release() {
            return this.responseCode;
        }

        public final /* synthetic */ void setResponseCode$hyperkyc_release(Integer num) {
            this.responseCode = num;
        }

        public final /* synthetic */ String getResponseBodyRaw$hyperkyc_release() {
            return this.responseBodyRaw;
        }

        public final /* synthetic */ void setResponseBodyRaw$hyperkyc_release(String str) {
            this.responseBodyRaw = str;
        }

        public final /* synthetic */ String getVideoPath$hyperkyc_release() {
            return this.videoPath;
        }

        public final /* synthetic */ void setVideoPath$hyperkyc_release(String str) {
            this.videoPath = str;
        }

        public final /* synthetic */ String getCompleted$hyperkyc_release() {
            return this.completed;
        }

        public final /* synthetic */ void setCompleted$hyperkyc_release(String str) {
            this.completed = str;
        }

        public final /* synthetic */ String getVideoUrl$hyperkyc_release() {
            return this.videoUrl;
        }

        public final /* synthetic */ void setVideoUrl$hyperkyc_release(String str) {
            this.videoUrl = str;
        }

        /* renamed from: getResponseHeaders$hyperkyc_release, reason: from getter */
        public final /* synthetic */ Map getResponseHeaders() {
            return this.responseHeaders;
        }

        public final /* synthetic */ void setResponseHeaders$hyperkyc_release(Map map) {
            this.responseHeaders = map;
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x006a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final boolean isSuccess(List<Integer> allowedStatusCodes) {
            Object m1202constructorimpl;
            String optString;
            int parseInt;
            boolean z;
            JSONObject optJSONObject;
            Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
            try {
                Result.Companion companion = Result.INSTANCE;
                SessionData sessionData = this;
                String str = this.responseBodyRaw;
                Intrinsics.checkNotNull(str);
                JSONObject jSONObject = new JSONObject(str);
                JSONObject optJSONObject2 = jSONObject.optJSONObject(Constant.PARAM_RESULT);
                optString = (optJSONObject2 == null || (optJSONObject = optJSONObject2.optJSONObject("summary")) == null) ? null : optJSONObject.optString("action");
                String optString2 = jSONObject.optString(Keys.STATUS_CODE);
                Intrinsics.checkNotNullExpressionValue(optString2, "response.optString(\"statusCode\")");
                parseInt = Integer.parseInt(optString2);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (optString == null && !allowedStatusCodes.contains(Integer.valueOf(parseInt))) {
                z = false;
                m1202constructorimpl = Result.m1202constructorimpl(Boolean.valueOf(z));
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = false;
                }
                return ((Boolean) m1202constructorimpl).booleanValue();
            }
            z = true;
            m1202constructorimpl = Result.m1202constructorimpl(Boolean.valueOf(z));
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
            }
            return ((Boolean) m1202constructorimpl).booleanValue();
        }

        public final String getRequestId() {
            String str;
            Map<String, ? extends List<String>> map = this.responseHeaders;
            if (map != null) {
                List<String> list = map.get("x-request-id");
                if (list == null) {
                    list = map.get(AppConstants.HV_REQUEST_ID);
                }
                if (list != null && (str = (String) CollectionsKt.firstOrNull((List) list)) != null) {
                    return CoreExtsKt.nullIfBlank(str);
                }
            }
            return null;
        }

        public final Integer statusCode() {
            return this.responseCode;
        }

        public final String responseBodyRaw() {
            return this.responseBodyRaw;
        }

        public final Map<String, List<String>> responseHeaders() {
            return this.responseHeaders;
        }

        /* compiled from: HyperKycData.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$SessionData$Companion;", "", "()V", "from", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$SessionData;", "response", "Lokhttp3/Response;", "from$hyperkyc_release", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final /* synthetic */ SessionData from$hyperkyc_release(Response response) {
                Intrinsics.checkNotNullParameter(response, "response");
                SessionData sessionData = new SessionData(null, null, null, null, null, null, 63, null);
                sessionData.setResponseCode$hyperkyc_release(Integer.valueOf(response.code()));
                ResponseBody body = response.body();
                sessionData.setResponseBodyRaw$hyperkyc_release(body != null ? body.string() : null);
                sessionData.setResponseHeaders$hyperkyc_release(response.headers().toMultimap());
                return sessionData;
            }
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00122\b\u0002\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\f\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\u0006\u0010\u0006\u001a\u00020\u0003J\u000e\u0010 \u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b!J\u000e\u0010\"\u001a\u00020\u0005HÀ\u0003¢\u0006\u0002\b#J\u000e\u0010$\u001a\u00020\u0007HÀ\u0003¢\u0006\u0002\b%J8\u0010&\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\fHÀ\u0003¢\u0006\u0002\b'J\u0016\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÀ\u0003¢\u0006\u0002\b)Jm\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u000722\b\u0002\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\f2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÆ\u0001J\t\u0010+\u001a\u00020\u0007HÖ\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\nHÖ\u0003J\t\u0010/\u001a\u00020\u0007HÖ\u0001J\u0015\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eH\u0000¢\u0006\u0002\b0J\u0006\u0010\u0002\u001a\u00020\u0003J\t\u00101\u001a\u00020\u0003HÖ\u0001J\u0006\u0010\u0004\u001a\u00020\u0005J\u0019\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u0007HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019RD\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u00067"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementResult;", "Landroid/os/Parcelable;", "tag", "", "videoStatementData", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementData;", AppConstants.ATTEMPTS_KEY, "", "variables", "Ljava/util/HashMap;", "", "Lkotlinx/parcelize/RawValue;", "Lkotlin/collections/HashMap;", "requestIds", "", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementData;ILjava/util/HashMap;Ljava/util/List;)V", "getAttempts$hyperkyc_release", "()I", "setAttempts$hyperkyc_release", "(I)V", "getRequestIds$hyperkyc_release", "()Ljava/util/List;", "setRequestIds$hyperkyc_release", "(Ljava/util/List;)V", "getTag$hyperkyc_release", "()Ljava/lang/String;", "getVariables$hyperkyc_release", "()Ljava/util/HashMap;", "setVariables$hyperkyc_release", "(Ljava/util/HashMap;)V", "getVideoStatementData$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementData;", "component1", "component1$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "component4", "component4$hyperkyc_release", "component5", "component5$hyperkyc_release", "copy", "describeContents", "equals", "", "other", "hashCode", "requestIds$hyperkyc_release", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class VideoStatementResult implements Parcelable {
        public static final Parcelable.Creator<VideoStatementResult> CREATOR = new Creator();
        private int attempts;
        private List<String> requestIds;

        @SerializedName("moduleId")
        private final String tag;
        private HashMap<String, Object> variables;
        private final VideoStatementData videoStatementData;

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<VideoStatementResult> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VideoStatementResult createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String readString = parcel.readString();
                VideoStatementData createFromParcel = VideoStatementData.CREATOR.createFromParcel(parcel);
                int readInt = parcel.readInt();
                int readInt2 = parcel.readInt();
                HashMap hashMap = new HashMap(readInt2);
                for (int i = 0; i != readInt2; i++) {
                    hashMap.put(parcel.readString(), parcel.readValue(VideoStatementResult.class.getClassLoader()));
                }
                return new VideoStatementResult(readString, createFromParcel, readInt, hashMap, parcel.createStringArrayList());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VideoStatementResult[] newArray(int i) {
                return new VideoStatementResult[i];
            }
        }

        public static /* synthetic */ VideoStatementResult copy$default(VideoStatementResult videoStatementResult, String str, VideoStatementData videoStatementData, int i, HashMap hashMap, List list, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = videoStatementResult.tag;
            }
            if ((i2 & 2) != 0) {
                videoStatementData = videoStatementResult.videoStatementData;
            }
            VideoStatementData videoStatementData2 = videoStatementData;
            if ((i2 & 4) != 0) {
                i = videoStatementResult.attempts;
            }
            int i3 = i;
            if ((i2 & 8) != 0) {
                hashMap = videoStatementResult.variables;
            }
            HashMap hashMap2 = hashMap;
            if ((i2 & 16) != 0) {
                list = videoStatementResult.requestIds;
            }
            return videoStatementResult.copy(str, videoStatementData2, i3, hashMap2, list);
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final String getTag() {
            return this.tag;
        }

        /* renamed from: component2$hyperkyc_release, reason: from getter */
        public final VideoStatementData getVideoStatementData() {
            return this.videoStatementData;
        }

        /* renamed from: component3$hyperkyc_release, reason: from getter */
        public final int getAttempts() {
            return this.attempts;
        }

        public final HashMap<String, Object> component4$hyperkyc_release() {
            return this.variables;
        }

        public final List<String> component5$hyperkyc_release() {
            return this.requestIds;
        }

        public final VideoStatementResult copy(String tag, VideoStatementData videoStatementData, int attempts, HashMap<String, Object> variables, List<String> requestIds) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(videoStatementData, "videoStatementData");
            Intrinsics.checkNotNullParameter(variables, "variables");
            return new VideoStatementResult(tag, videoStatementData, attempts, variables, requestIds);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoStatementResult)) {
                return false;
            }
            VideoStatementResult videoStatementResult = (VideoStatementResult) other;
            return Intrinsics.areEqual(this.tag, videoStatementResult.tag) && Intrinsics.areEqual(this.videoStatementData, videoStatementResult.videoStatementData) && this.attempts == videoStatementResult.attempts && Intrinsics.areEqual(this.variables, videoStatementResult.variables) && Intrinsics.areEqual(this.requestIds, videoStatementResult.requestIds);
        }

        public int hashCode() {
            int hashCode = ((((((this.tag.hashCode() * 31) + this.videoStatementData.hashCode()) * 31) + this.attempts) * 31) + this.variables.hashCode()) * 31;
            List<String> list = this.requestIds;
            return hashCode + (list == null ? 0 : list.hashCode());
        }

        public String toString() {
            return "VideoStatementResult(tag=" + this.tag + ", videoStatementData=" + this.videoStatementData + ", attempts=" + this.attempts + ", variables=" + this.variables + ", requestIds=" + this.requestIds + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.tag);
            this.videoStatementData.writeToParcel(parcel, flags);
            parcel.writeInt(this.attempts);
            HashMap<String, Object> hashMap = this.variables;
            parcel.writeInt(hashMap.size());
            for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeValue(entry.getValue());
            }
            parcel.writeStringList(this.requestIds);
        }

        public VideoStatementResult(String tag, VideoStatementData videoStatementData, int i, HashMap<String, Object> variables, List<String> list) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(videoStatementData, "videoStatementData");
            Intrinsics.checkNotNullParameter(variables, "variables");
            this.tag = tag;
            this.videoStatementData = videoStatementData;
            this.attempts = i;
            this.variables = variables;
            this.requestIds = list;
        }

        public final /* synthetic */ String getTag$hyperkyc_release() {
            return this.tag;
        }

        public final /* synthetic */ VideoStatementData getVideoStatementData$hyperkyc_release() {
            return this.videoStatementData;
        }

        public final /* synthetic */ int getAttempts$hyperkyc_release() {
            return this.attempts;
        }

        public final void setAttempts$hyperkyc_release(int i) {
            this.attempts = i;
        }

        public /* synthetic */ VideoStatementResult(String str, VideoStatementData videoStatementData, int i, HashMap hashMap, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, videoStatementData, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? new HashMap() : hashMap, (i2 & 16) != 0 ? null : list);
        }

        /* renamed from: getVariables$hyperkyc_release, reason: from getter */
        public final /* synthetic */ HashMap getVariables() {
            return this.variables;
        }

        public final void setVariables$hyperkyc_release(HashMap<String, Object> hashMap) {
            Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
            this.variables = hashMap;
        }

        /* renamed from: getRequestIds$hyperkyc_release, reason: from getter */
        public final /* synthetic */ List getRequestIds() {
            return this.requestIds;
        }

        public final /* synthetic */ void setRequestIds$hyperkyc_release(List list) {
            this.requestIds = list;
        }

        public final String tag() {
            return this.tag;
        }

        public final VideoStatementData videoStatementData() {
            return this.videoStatementData;
        }

        public final String attempts() {
            return String.valueOf(this.attempts);
        }

        public final /* synthetic */ List requestIds$hyperkyc_release() {
            List<String> list = this.requestIds;
            if (list != null) {
                return list;
            }
            String requestId = this.videoStatementData.getRequestId();
            if (requestId != null) {
                return CollectionsKt.listOf(requestId);
            }
            return null;
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b'\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 >2\u00020\u0001:\u0001>B_\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u001c\b\u0002\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\fJ\u0012\u0010 \u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0004\b!\u0010\u0016J\u0010\u0010\"\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b#J\"\u0010$\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b\u0018\u00010\u0007HÀ\u0003¢\u0006\u0002\b%J\u0010\u0010&\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b'J\u0010\u0010(\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b)J\u0010\u0010*\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b+Jh\u0010,\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u001c\b\u0002\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010-J\t\u0010.\u001a\u00020\u0003HÖ\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u000102HÖ\u0003J\b\u00103\u001a\u0004\u0018\u00010\u0005J\t\u00104\u001a\u00020\u0003HÖ\u0001J\u0014\u00105\u001a\u0002002\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00030\bJ\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005J\u001a\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b\u0018\u00010\u0007J\r\u00107\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0016J\t\u00108\u001a\u00020\u0005HÖ\u0001J\u0019\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R.\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b\u0018\u00010\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000e\"\u0004\b\u001f\u0010\u0010¨\u0006?"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementData;", "Landroid/os/Parcelable;", "responseCode", "", "responseBodyRaw", "", "responseHeaders", "", "", ApiAction.PASS, "image", "statements", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getImage$hyperkyc_release", "()Ljava/lang/String;", "setImage$hyperkyc_release", "(Ljava/lang/String;)V", "getPass$hyperkyc_release", "setPass$hyperkyc_release", "getResponseBodyRaw$hyperkyc_release", "setResponseBodyRaw$hyperkyc_release", "getResponseCode$hyperkyc_release", "()Ljava/lang/Integer;", "setResponseCode$hyperkyc_release", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getResponseHeaders$hyperkyc_release", "()Ljava/util/Map;", "setResponseHeaders$hyperkyc_release", "(Ljava/util/Map;)V", "getStatements$hyperkyc_release", "setStatements$hyperkyc_release", "component1", "component1$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "component4", "component4$hyperkyc_release", "component5", "component5$hyperkyc_release", "component6", "component6$hyperkyc_release", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementData;", "describeContents", "equals", "", "other", "", "getRequestId", "hashCode", "isSuccess", "allowedStatusCodes", Keys.STATUS_CODE, InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class VideoStatementData implements Parcelable {
        private String image;
        private String pass;
        private String responseBodyRaw;
        private Integer responseCode;
        private Map<String, ? extends List<String>> responseHeaders;
        private String statements;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final Parcelable.Creator<VideoStatementData> CREATOR = new Creator();

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<VideoStatementData> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VideoStatementData createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                LinkedHashMap linkedHashMap = null;
                Integer valueOf = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
                String readString = parcel.readString();
                if (parcel.readInt() != 0) {
                    int readInt = parcel.readInt();
                    linkedHashMap = new LinkedHashMap(readInt);
                    for (int i = 0; i != readInt; i++) {
                        linkedHashMap.put(parcel.readString(), parcel.createStringArrayList());
                    }
                }
                return new VideoStatementData(valueOf, readString, linkedHashMap, parcel.readString(), parcel.readString(), parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VideoStatementData[] newArray(int i) {
                return new VideoStatementData[i];
            }
        }

        public VideoStatementData() {
            this(null, null, null, null, null, null, 63, null);
        }

        public static /* synthetic */ VideoStatementData copy$default(VideoStatementData videoStatementData, Integer num, String str, Map map, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                num = videoStatementData.responseCode;
            }
            if ((i & 2) != 0) {
                str = videoStatementData.responseBodyRaw;
            }
            String str5 = str;
            if ((i & 4) != 0) {
                map = videoStatementData.responseHeaders;
            }
            Map map2 = map;
            if ((i & 8) != 0) {
                str2 = videoStatementData.pass;
            }
            String str6 = str2;
            if ((i & 16) != 0) {
                str3 = videoStatementData.image;
            }
            String str7 = str3;
            if ((i & 32) != 0) {
                str4 = videoStatementData.statements;
            }
            return videoStatementData.copy(num, str5, map2, str6, str7, str4);
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final Integer getResponseCode() {
            return this.responseCode;
        }

        /* renamed from: component2$hyperkyc_release, reason: from getter */
        public final String getResponseBodyRaw() {
            return this.responseBodyRaw;
        }

        public final Map<String, List<String>> component3$hyperkyc_release() {
            return this.responseHeaders;
        }

        /* renamed from: component4$hyperkyc_release, reason: from getter */
        public final String getPass() {
            return this.pass;
        }

        /* renamed from: component5$hyperkyc_release, reason: from getter */
        public final String getImage() {
            return this.image;
        }

        /* renamed from: component6$hyperkyc_release, reason: from getter */
        public final String getStatements() {
            return this.statements;
        }

        public final VideoStatementData copy(Integer responseCode, String responseBodyRaw, Map<String, ? extends List<String>> responseHeaders, String pass, String image, String statements) {
            return new VideoStatementData(responseCode, responseBodyRaw, responseHeaders, pass, image, statements);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoStatementData)) {
                return false;
            }
            VideoStatementData videoStatementData = (VideoStatementData) other;
            return Intrinsics.areEqual(this.responseCode, videoStatementData.responseCode) && Intrinsics.areEqual(this.responseBodyRaw, videoStatementData.responseBodyRaw) && Intrinsics.areEqual(this.responseHeaders, videoStatementData.responseHeaders) && Intrinsics.areEqual(this.pass, videoStatementData.pass) && Intrinsics.areEqual(this.image, videoStatementData.image) && Intrinsics.areEqual(this.statements, videoStatementData.statements);
        }

        public int hashCode() {
            Integer num = this.responseCode;
            int hashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.responseBodyRaw;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            Map<String, ? extends List<String>> map = this.responseHeaders;
            int hashCode3 = (hashCode2 + (map == null ? 0 : map.hashCode())) * 31;
            String str2 = this.pass;
            int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.image;
            int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.statements;
            return hashCode5 + (str4 != null ? str4.hashCode() : 0);
        }

        public String toString() {
            return "VideoStatementData(responseCode=" + this.responseCode + ", responseBodyRaw=" + this.responseBodyRaw + ", responseHeaders=" + this.responseHeaders + ", pass=" + this.pass + ", image=" + this.image + ", statements=" + this.statements + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            Integer num = this.responseCode;
            if (num == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcel.writeInt(num.intValue());
            }
            parcel.writeString(this.responseBodyRaw);
            Map<String, ? extends List<String>> map = this.responseHeaders;
            if (map == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcel.writeInt(map.size());
                for (Map.Entry<String, ? extends List<String>> entry : map.entrySet()) {
                    parcel.writeString(entry.getKey());
                    parcel.writeStringList(entry.getValue());
                }
            }
            parcel.writeString(this.pass);
            parcel.writeString(this.image);
            parcel.writeString(this.statements);
        }

        public VideoStatementData(Integer num, String str, Map<String, ? extends List<String>> map, String str2, String str3, String str4) {
            this.responseCode = num;
            this.responseBodyRaw = str;
            this.responseHeaders = map;
            this.pass = str2;
            this.image = str3;
            this.statements = str4;
        }

        public /* synthetic */ VideoStatementData(Integer num, String str, Map map, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : map, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4);
        }

        public final /* synthetic */ Integer getResponseCode$hyperkyc_release() {
            return this.responseCode;
        }

        public final /* synthetic */ void setResponseCode$hyperkyc_release(Integer num) {
            this.responseCode = num;
        }

        public final /* synthetic */ String getResponseBodyRaw$hyperkyc_release() {
            return this.responseBodyRaw;
        }

        public final /* synthetic */ void setResponseBodyRaw$hyperkyc_release(String str) {
            this.responseBodyRaw = str;
        }

        /* renamed from: getResponseHeaders$hyperkyc_release, reason: from getter */
        public final /* synthetic */ Map getResponseHeaders() {
            return this.responseHeaders;
        }

        public final /* synthetic */ void setResponseHeaders$hyperkyc_release(Map map) {
            this.responseHeaders = map;
        }

        public final /* synthetic */ String getPass$hyperkyc_release() {
            return this.pass;
        }

        public final /* synthetic */ void setPass$hyperkyc_release(String str) {
            this.pass = str;
        }

        public final /* synthetic */ String getImage$hyperkyc_release() {
            return this.image;
        }

        public final /* synthetic */ void setImage$hyperkyc_release(String str) {
            this.image = str;
        }

        public final /* synthetic */ String getStatements$hyperkyc_release() {
            return this.statements;
        }

        public final /* synthetic */ void setStatements$hyperkyc_release(String str) {
            this.statements = str;
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x006a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final boolean isSuccess(List<Integer> allowedStatusCodes) {
            Object m1202constructorimpl;
            String optString;
            int parseInt;
            boolean z;
            JSONObject optJSONObject;
            Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
            try {
                Result.Companion companion = Result.INSTANCE;
                VideoStatementData videoStatementData = this;
                String str = this.responseBodyRaw;
                Intrinsics.checkNotNull(str);
                JSONObject jSONObject = new JSONObject(str);
                JSONObject optJSONObject2 = jSONObject.optJSONObject(Constant.PARAM_RESULT);
                optString = (optJSONObject2 == null || (optJSONObject = optJSONObject2.optJSONObject("summary")) == null) ? null : optJSONObject.optString("action");
                String optString2 = jSONObject.optString(Keys.STATUS_CODE);
                Intrinsics.checkNotNullExpressionValue(optString2, "response.optString(\"statusCode\")");
                parseInt = Integer.parseInt(optString2);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (optString == null && !allowedStatusCodes.contains(Integer.valueOf(parseInt))) {
                z = false;
                m1202constructorimpl = Result.m1202constructorimpl(Boolean.valueOf(z));
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = false;
                }
                return ((Boolean) m1202constructorimpl).booleanValue();
            }
            z = true;
            m1202constructorimpl = Result.m1202constructorimpl(Boolean.valueOf(z));
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
            }
            return ((Boolean) m1202constructorimpl).booleanValue();
        }

        public final String getRequestId() {
            String str;
            Map<String, ? extends List<String>> map = this.responseHeaders;
            if (map != null) {
                List<String> list = map.get("x-request-id");
                if (list == null) {
                    list = map.get(AppConstants.HV_REQUEST_ID);
                }
                if (list != null && (str = (String) CollectionsKt.firstOrNull((List) list)) != null) {
                    return CoreExtsKt.nullIfBlank(str);
                }
            }
            return null;
        }

        public final Integer statusCode() {
            return this.responseCode;
        }

        public final String responseBodyRaw() {
            return this.responseBodyRaw;
        }

        public final Map<String, List<String>> responseHeaders() {
            return this.responseHeaders;
        }

        /* compiled from: HyperKycData.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementData$Companion;", "", "()V", "from", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementData;", "response", "Lokhttp3/Response;", "from$hyperkyc_release", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final /* synthetic */ VideoStatementData from$hyperkyc_release(Response response) {
                Intrinsics.checkNotNullParameter(response, "response");
                VideoStatementData videoStatementData = new VideoStatementData(null, null, null, null, null, null, 63, null);
                videoStatementData.setResponseCode$hyperkyc_release(Integer.valueOf(response.code()));
                ResponseBody body = response.body();
                videoStatementData.setResponseBodyRaw$hyperkyc_release(body != null ? body.string() : null);
                videoStatementData.setResponseHeaders$hyperkyc_release(response.headers().toMultimap());
                return videoStatementData;
            }
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00122\b\u0002\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\f\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\u0006\u0010\u0006\u001a\u00020\u0003J\u000e\u0010 \u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b!J\u000e\u0010\"\u001a\u00020\u0005HÀ\u0003¢\u0006\u0002\b#J\u000e\u0010$\u001a\u00020\u0007HÀ\u0003¢\u0006\u0002\b%J8\u0010&\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\fHÀ\u0003¢\u0006\u0002\b'J\u0016\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÀ\u0003¢\u0006\u0002\b)Jm\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u000722\b\u0002\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\f2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÆ\u0001J\t\u0010+\u001a\u00020\u0007HÖ\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\nHÖ\u0003J\t\u0010/\u001a\u00020\u0007HÖ\u0001J\u0015\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eH\u0000¢\u0006\u0002\b0J\u0006\u0010\u0002\u001a\u00020\u0003J\t\u00101\u001a\u00020\u0003HÖ\u0001J\u0006\u0010\u0004\u001a\u00020\u0005J\u0019\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u0007HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019RD\u0010\b\u001a,\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b0\tj\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u000b\u0012\t\u0018\u00010\n¢\u0006\u0002\b\u000b`\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u00067"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementV2Result;", "Landroid/os/Parcelable;", "tag", "", "videoStatementV2Data", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementV2Data;", AppConstants.ATTEMPTS_KEY, "", "variables", "Ljava/util/HashMap;", "", "Lkotlinx/parcelize/RawValue;", "Lkotlin/collections/HashMap;", "requestIds", "", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementV2Data;ILjava/util/HashMap;Ljava/util/List;)V", "getAttempts$hyperkyc_release", "()I", "setAttempts$hyperkyc_release", "(I)V", "getRequestIds$hyperkyc_release", "()Ljava/util/List;", "setRequestIds$hyperkyc_release", "(Ljava/util/List;)V", "getTag$hyperkyc_release", "()Ljava/lang/String;", "getVariables$hyperkyc_release", "()Ljava/util/HashMap;", "setVariables$hyperkyc_release", "(Ljava/util/HashMap;)V", "getVideoStatementV2Data$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementV2Data;", "component1", "component1$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "component4", "component4$hyperkyc_release", "component5", "component5$hyperkyc_release", "copy", "describeContents", "equals", "", "other", "hashCode", "requestIds$hyperkyc_release", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class VideoStatementV2Result implements Parcelable {
        public static final Parcelable.Creator<VideoStatementV2Result> CREATOR = new Creator();
        private int attempts;
        private List<String> requestIds;

        @SerializedName("moduleId")
        private final String tag;
        private HashMap<String, Object> variables;
        private final VideoStatementV2Data videoStatementV2Data;

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<VideoStatementV2Result> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VideoStatementV2Result createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String readString = parcel.readString();
                VideoStatementV2Data createFromParcel = VideoStatementV2Data.CREATOR.createFromParcel(parcel);
                int readInt = parcel.readInt();
                int readInt2 = parcel.readInt();
                HashMap hashMap = new HashMap(readInt2);
                for (int i = 0; i != readInt2; i++) {
                    hashMap.put(parcel.readString(), parcel.readValue(VideoStatementV2Result.class.getClassLoader()));
                }
                return new VideoStatementV2Result(readString, createFromParcel, readInt, hashMap, parcel.createStringArrayList());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VideoStatementV2Result[] newArray(int i) {
                return new VideoStatementV2Result[i];
            }
        }

        public static /* synthetic */ VideoStatementV2Result copy$default(VideoStatementV2Result videoStatementV2Result, String str, VideoStatementV2Data videoStatementV2Data, int i, HashMap hashMap, List list, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = videoStatementV2Result.tag;
            }
            if ((i2 & 2) != 0) {
                videoStatementV2Data = videoStatementV2Result.videoStatementV2Data;
            }
            VideoStatementV2Data videoStatementV2Data2 = videoStatementV2Data;
            if ((i2 & 4) != 0) {
                i = videoStatementV2Result.attempts;
            }
            int i3 = i;
            if ((i2 & 8) != 0) {
                hashMap = videoStatementV2Result.variables;
            }
            HashMap hashMap2 = hashMap;
            if ((i2 & 16) != 0) {
                list = videoStatementV2Result.requestIds;
            }
            return videoStatementV2Result.copy(str, videoStatementV2Data2, i3, hashMap2, list);
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final String getTag() {
            return this.tag;
        }

        /* renamed from: component2$hyperkyc_release, reason: from getter */
        public final VideoStatementV2Data getVideoStatementV2Data() {
            return this.videoStatementV2Data;
        }

        /* renamed from: component3$hyperkyc_release, reason: from getter */
        public final int getAttempts() {
            return this.attempts;
        }

        public final HashMap<String, Object> component4$hyperkyc_release() {
            return this.variables;
        }

        public final List<String> component5$hyperkyc_release() {
            return this.requestIds;
        }

        public final VideoStatementV2Result copy(String tag, VideoStatementV2Data videoStatementV2Data, int attempts, HashMap<String, Object> variables, List<String> requestIds) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(videoStatementV2Data, "videoStatementV2Data");
            Intrinsics.checkNotNullParameter(variables, "variables");
            return new VideoStatementV2Result(tag, videoStatementV2Data, attempts, variables, requestIds);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoStatementV2Result)) {
                return false;
            }
            VideoStatementV2Result videoStatementV2Result = (VideoStatementV2Result) other;
            return Intrinsics.areEqual(this.tag, videoStatementV2Result.tag) && Intrinsics.areEqual(this.videoStatementV2Data, videoStatementV2Result.videoStatementV2Data) && this.attempts == videoStatementV2Result.attempts && Intrinsics.areEqual(this.variables, videoStatementV2Result.variables) && Intrinsics.areEqual(this.requestIds, videoStatementV2Result.requestIds);
        }

        public int hashCode() {
            int hashCode = ((((((this.tag.hashCode() * 31) + this.videoStatementV2Data.hashCode()) * 31) + this.attempts) * 31) + this.variables.hashCode()) * 31;
            List<String> list = this.requestIds;
            return hashCode + (list == null ? 0 : list.hashCode());
        }

        public String toString() {
            return "VideoStatementV2Result(tag=" + this.tag + ", videoStatementV2Data=" + this.videoStatementV2Data + ", attempts=" + this.attempts + ", variables=" + this.variables + ", requestIds=" + this.requestIds + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.tag);
            this.videoStatementV2Data.writeToParcel(parcel, flags);
            parcel.writeInt(this.attempts);
            HashMap<String, Object> hashMap = this.variables;
            parcel.writeInt(hashMap.size());
            for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeValue(entry.getValue());
            }
            parcel.writeStringList(this.requestIds);
        }

        public VideoStatementV2Result(String tag, VideoStatementV2Data videoStatementV2Data, int i, HashMap<String, Object> variables, List<String> list) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(videoStatementV2Data, "videoStatementV2Data");
            Intrinsics.checkNotNullParameter(variables, "variables");
            this.tag = tag;
            this.videoStatementV2Data = videoStatementV2Data;
            this.attempts = i;
            this.variables = variables;
            this.requestIds = list;
        }

        public final /* synthetic */ String getTag$hyperkyc_release() {
            return this.tag;
        }

        public final /* synthetic */ VideoStatementV2Data getVideoStatementV2Data$hyperkyc_release() {
            return this.videoStatementV2Data;
        }

        public final /* synthetic */ int getAttempts$hyperkyc_release() {
            return this.attempts;
        }

        public final void setAttempts$hyperkyc_release(int i) {
            this.attempts = i;
        }

        public /* synthetic */ VideoStatementV2Result(String str, VideoStatementV2Data videoStatementV2Data, int i, HashMap hashMap, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, videoStatementV2Data, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? new HashMap() : hashMap, (i2 & 16) != 0 ? null : list);
        }

        /* renamed from: getVariables$hyperkyc_release, reason: from getter */
        public final /* synthetic */ HashMap getVariables() {
            return this.variables;
        }

        public final void setVariables$hyperkyc_release(HashMap<String, Object> hashMap) {
            Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
            this.variables = hashMap;
        }

        /* renamed from: getRequestIds$hyperkyc_release, reason: from getter */
        public final /* synthetic */ List getRequestIds() {
            return this.requestIds;
        }

        public final /* synthetic */ void setRequestIds$hyperkyc_release(List list) {
            this.requestIds = list;
        }

        public final String tag() {
            return this.tag;
        }

        public final VideoStatementV2Data videoStatementV2Data() {
            return this.videoStatementV2Data;
        }

        public final String attempts() {
            return String.valueOf(this.attempts);
        }

        public final /* synthetic */ List requestIds$hyperkyc_release() {
            List<String> list = this.requestIds;
            if (list != null) {
                return list;
            }
            String requestId = this.videoStatementV2Data.getRequestId();
            if (requestId != null) {
                return CollectionsKt.listOf(requestId);
            }
            return null;
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b,\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 H2\u00020\u0001:\u0001HBw\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\u001c\b\u0002\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r\u0018\u00010\f¢\u0006\u0002\u0010\u000eJ\u0012\u0010&\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0004\b'\u0010\u0018J\u0010\u0010(\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b)J\u0010\u0010*\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b+J\u0010\u0010,\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b-J\u0010\u0010.\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b/J\u0010\u00100\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b1J\u0010\u00102\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b3J\"\u00104\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r\u0018\u00010\fHÀ\u0003¢\u0006\u0002\b5J\u0080\u0001\u00106\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\u001c\b\u0002\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u00107J\t\u00108\u001a\u00020\u0003HÖ\u0001J\u0013\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010<HÖ\u0003J\b\u0010=\u001a\u0004\u0018\u00010\u0005J\t\u0010>\u001a\u00020\u0003HÖ\u0001J\u0014\u0010?\u001a\u00020:2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00030\rJ\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005J\u001a\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r\u0018\u00010\fJ\r\u0010A\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0018J\t\u0010B\u001a\u00020\u0005HÖ\u0001J\u0019\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR.\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r\u0018\u00010\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0010\"\u0004\b!\u0010\u0012R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0010\"\u0004\b#\u0010\u0012R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0010\"\u0004\b%\u0010\u0012¨\u0006I"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementV2Data;", "Landroid/os/Parcelable;", "responseCode", "", "responseBodyRaw", "", "imagePath", "videoPath", "statement", "videoUrl", "attemptsCount", "responseHeaders", "", "", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getAttemptsCount$hyperkyc_release", "()Ljava/lang/String;", "setAttemptsCount$hyperkyc_release", "(Ljava/lang/String;)V", "getImagePath$hyperkyc_release", "setImagePath$hyperkyc_release", "getResponseBodyRaw$hyperkyc_release", "setResponseBodyRaw$hyperkyc_release", "getResponseCode$hyperkyc_release", "()Ljava/lang/Integer;", "setResponseCode$hyperkyc_release", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getResponseHeaders$hyperkyc_release", "()Ljava/util/Map;", "setResponseHeaders$hyperkyc_release", "(Ljava/util/Map;)V", "getStatement$hyperkyc_release", "setStatement$hyperkyc_release", "getVideoPath$hyperkyc_release", "setVideoPath$hyperkyc_release", "getVideoUrl$hyperkyc_release", "setVideoUrl$hyperkyc_release", "component1", "component1$hyperkyc_release", "component2", "component2$hyperkyc_release", "component3", "component3$hyperkyc_release", "component4", "component4$hyperkyc_release", "component5", "component5$hyperkyc_release", "component6", "component6$hyperkyc_release", "component7", "component7$hyperkyc_release", "component8", "component8$hyperkyc_release", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementV2Data;", "describeContents", "equals", "", "other", "", "getRequestId", "hashCode", "isSuccess", "allowedStatusCodes", Keys.STATUS_CODE, InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class VideoStatementV2Data implements Parcelable {
        private String attemptsCount;
        private String imagePath;
        private String responseBodyRaw;
        private Integer responseCode;
        private Map<String, ? extends List<String>> responseHeaders;
        private String statement;
        private String videoPath;
        private String videoUrl;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final Parcelable.Creator<VideoStatementV2Data> CREATOR = new Creator();

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<VideoStatementV2Data> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VideoStatementV2Data createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                LinkedHashMap linkedHashMap = null;
                Integer valueOf = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                String readString3 = parcel.readString();
                String readString4 = parcel.readString();
                String readString5 = parcel.readString();
                String readString6 = parcel.readString();
                if (parcel.readInt() != 0) {
                    int readInt = parcel.readInt();
                    linkedHashMap = new LinkedHashMap(readInt);
                    for (int i = 0; i != readInt; i++) {
                        linkedHashMap.put(parcel.readString(), parcel.createStringArrayList());
                    }
                }
                return new VideoStatementV2Data(valueOf, readString, readString2, readString3, readString4, readString5, readString6, linkedHashMap);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VideoStatementV2Data[] newArray(int i) {
                return new VideoStatementV2Data[i];
            }
        }

        public VideoStatementV2Data() {
            this(null, null, null, null, null, null, null, null, 255, null);
        }

        /* renamed from: component1$hyperkyc_release, reason: from getter */
        public final Integer getResponseCode() {
            return this.responseCode;
        }

        /* renamed from: component2$hyperkyc_release, reason: from getter */
        public final String getResponseBodyRaw() {
            return this.responseBodyRaw;
        }

        /* renamed from: component3$hyperkyc_release, reason: from getter */
        public final String getImagePath() {
            return this.imagePath;
        }

        /* renamed from: component4$hyperkyc_release, reason: from getter */
        public final String getVideoPath() {
            return this.videoPath;
        }

        /* renamed from: component5$hyperkyc_release, reason: from getter */
        public final String getStatement() {
            return this.statement;
        }

        /* renamed from: component6$hyperkyc_release, reason: from getter */
        public final String getVideoUrl() {
            return this.videoUrl;
        }

        /* renamed from: component7$hyperkyc_release, reason: from getter */
        public final String getAttemptsCount() {
            return this.attemptsCount;
        }

        public final Map<String, List<String>> component8$hyperkyc_release() {
            return this.responseHeaders;
        }

        public final VideoStatementV2Data copy(Integer responseCode, String responseBodyRaw, String imagePath, String videoPath, String statement, String videoUrl, String attemptsCount, Map<String, ? extends List<String>> responseHeaders) {
            return new VideoStatementV2Data(responseCode, responseBodyRaw, imagePath, videoPath, statement, videoUrl, attemptsCount, responseHeaders);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoStatementV2Data)) {
                return false;
            }
            VideoStatementV2Data videoStatementV2Data = (VideoStatementV2Data) other;
            return Intrinsics.areEqual(this.responseCode, videoStatementV2Data.responseCode) && Intrinsics.areEqual(this.responseBodyRaw, videoStatementV2Data.responseBodyRaw) && Intrinsics.areEqual(this.imagePath, videoStatementV2Data.imagePath) && Intrinsics.areEqual(this.videoPath, videoStatementV2Data.videoPath) && Intrinsics.areEqual(this.statement, videoStatementV2Data.statement) && Intrinsics.areEqual(this.videoUrl, videoStatementV2Data.videoUrl) && Intrinsics.areEqual(this.attemptsCount, videoStatementV2Data.attemptsCount) && Intrinsics.areEqual(this.responseHeaders, videoStatementV2Data.responseHeaders);
        }

        public int hashCode() {
            Integer num = this.responseCode;
            int hashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.responseBodyRaw;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.imagePath;
            int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.videoPath;
            int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.statement;
            int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.videoUrl;
            int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.attemptsCount;
            int hashCode7 = (hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
            Map<String, ? extends List<String>> map = this.responseHeaders;
            return hashCode7 + (map != null ? map.hashCode() : 0);
        }

        public String toString() {
            return "VideoStatementV2Data(responseCode=" + this.responseCode + ", responseBodyRaw=" + this.responseBodyRaw + ", imagePath=" + this.imagePath + ", videoPath=" + this.videoPath + ", statement=" + this.statement + ", videoUrl=" + this.videoUrl + ", attemptsCount=" + this.attemptsCount + ", responseHeaders=" + this.responseHeaders + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            Integer num = this.responseCode;
            if (num == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcel.writeInt(num.intValue());
            }
            parcel.writeString(this.responseBodyRaw);
            parcel.writeString(this.imagePath);
            parcel.writeString(this.videoPath);
            parcel.writeString(this.statement);
            parcel.writeString(this.videoUrl);
            parcel.writeString(this.attemptsCount);
            Map<String, ? extends List<String>> map = this.responseHeaders;
            if (map == null) {
                parcel.writeInt(0);
                return;
            }
            parcel.writeInt(1);
            parcel.writeInt(map.size());
            for (Map.Entry<String, ? extends List<String>> entry : map.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeStringList(entry.getValue());
            }
        }

        public VideoStatementV2Data(Integer num, String str, String str2, String str3, String str4, String str5, String str6, Map<String, ? extends List<String>> map) {
            this.responseCode = num;
            this.responseBodyRaw = str;
            this.imagePath = str2;
            this.videoPath = str3;
            this.statement = str4;
            this.videoUrl = str5;
            this.attemptsCount = str6;
            this.responseHeaders = map;
        }

        public /* synthetic */ VideoStatementV2Data(Integer num, String str, String str2, String str3, String str4, String str5, String str6, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : str5, (i & 64) != 0 ? null : str6, (i & 128) == 0 ? map : null);
        }

        public final /* synthetic */ Integer getResponseCode$hyperkyc_release() {
            return this.responseCode;
        }

        public final /* synthetic */ void setResponseCode$hyperkyc_release(Integer num) {
            this.responseCode = num;
        }

        public final /* synthetic */ String getResponseBodyRaw$hyperkyc_release() {
            return this.responseBodyRaw;
        }

        public final /* synthetic */ void setResponseBodyRaw$hyperkyc_release(String str) {
            this.responseBodyRaw = str;
        }

        public final /* synthetic */ String getImagePath$hyperkyc_release() {
            return this.imagePath;
        }

        public final /* synthetic */ void setImagePath$hyperkyc_release(String str) {
            this.imagePath = str;
        }

        public final /* synthetic */ String getVideoPath$hyperkyc_release() {
            return this.videoPath;
        }

        public final /* synthetic */ void setVideoPath$hyperkyc_release(String str) {
            this.videoPath = str;
        }

        public final /* synthetic */ String getStatement$hyperkyc_release() {
            return this.statement;
        }

        public final /* synthetic */ void setStatement$hyperkyc_release(String str) {
            this.statement = str;
        }

        public final /* synthetic */ String getVideoUrl$hyperkyc_release() {
            return this.videoUrl;
        }

        public final /* synthetic */ void setVideoUrl$hyperkyc_release(String str) {
            this.videoUrl = str;
        }

        public final /* synthetic */ String getAttemptsCount$hyperkyc_release() {
            return this.attemptsCount;
        }

        public final /* synthetic */ void setAttemptsCount$hyperkyc_release(String str) {
            this.attemptsCount = str;
        }

        /* renamed from: getResponseHeaders$hyperkyc_release, reason: from getter */
        public final /* synthetic */ Map getResponseHeaders() {
            return this.responseHeaders;
        }

        public final /* synthetic */ void setResponseHeaders$hyperkyc_release(Map map) {
            this.responseHeaders = map;
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x006a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final boolean isSuccess(List<Integer> allowedStatusCodes) {
            Object m1202constructorimpl;
            String optString;
            int parseInt;
            boolean z;
            JSONObject optJSONObject;
            Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
            try {
                Result.Companion companion = Result.INSTANCE;
                VideoStatementV2Data videoStatementV2Data = this;
                String str = this.responseBodyRaw;
                Intrinsics.checkNotNull(str);
                JSONObject jSONObject = new JSONObject(str);
                JSONObject optJSONObject2 = jSONObject.optJSONObject(Constant.PARAM_RESULT);
                optString = (optJSONObject2 == null || (optJSONObject = optJSONObject2.optJSONObject("summary")) == null) ? null : optJSONObject.optString("action");
                String optString2 = jSONObject.optString(Keys.STATUS_CODE);
                Intrinsics.checkNotNullExpressionValue(optString2, "response.optString(\"statusCode\")");
                parseInt = Integer.parseInt(optString2);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (optString == null && !allowedStatusCodes.contains(Integer.valueOf(parseInt))) {
                z = false;
                m1202constructorimpl = Result.m1202constructorimpl(Boolean.valueOf(z));
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = false;
                }
                return ((Boolean) m1202constructorimpl).booleanValue();
            }
            z = true;
            m1202constructorimpl = Result.m1202constructorimpl(Boolean.valueOf(z));
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
            }
            return ((Boolean) m1202constructorimpl).booleanValue();
        }

        public final String getRequestId() {
            String str;
            Map<String, ? extends List<String>> map = this.responseHeaders;
            if (map != null) {
                List<String> list = map.get("x-request-id");
                if (list == null) {
                    list = map.get(AppConstants.HV_REQUEST_ID);
                }
                if (list != null && (str = (String) CollectionsKt.firstOrNull((List) list)) != null) {
                    return CoreExtsKt.nullIfBlank(str);
                }
            }
            return null;
        }

        public final Integer statusCode() {
            return this.responseCode;
        }

        public final String responseBodyRaw() {
            return this.responseBodyRaw;
        }

        public final Map<String, List<String>> responseHeaders() {
            return this.responseHeaders;
        }

        /* compiled from: HyperKycData.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementV2Data$Companion;", "", "()V", "from", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementData;", "response", "Lokhttp3/Response;", "from$hyperkyc_release", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final /* synthetic */ VideoStatementData from$hyperkyc_release(Response response) {
                Intrinsics.checkNotNullParameter(response, "response");
                VideoStatementData videoStatementData = new VideoStatementData(null, null, null, null, null, null, 63, null);
                videoStatementData.setResponseCode$hyperkyc_release(Integer.valueOf(response.code()));
                ResponseBody body = response.body();
                videoStatementData.setResponseBodyRaw$hyperkyc_release(body != null ? body.string() : null);
                videoStatementData.setResponseHeaders$hyperkyc_release(response.headers().toMultimap());
                return videoStatementData;
            }
        }
    }

    /* compiled from: HyperKycData.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0018\b\u0002\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0019\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0006HÆ\u0003J9\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0018\b\u0002\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0006HÆ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0012HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR&\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001e"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$ApiFlags;", "Landroid/os/Parcelable;", "source", "", "subSource", "values", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getSource", "()Ljava/lang/String;", "getSubSource", "getValues", "()Ljava/util/Map;", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class ApiFlags implements Parcelable {
        public static final Parcelable.Creator<ApiFlags> CREATOR = new Creator();

        @SerializedName("source")
        private final String source;

        @SerializedName("subSource")
        private final String subSource;

        @SerializedName("values")
        private final Map<String, String> values;

        /* compiled from: HyperKycData.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<ApiFlags> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final ApiFlags createFromParcel(Parcel parcel) {
                LinkedHashMap linkedHashMap;
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                if (parcel.readInt() == 0) {
                    linkedHashMap = null;
                } else {
                    int readInt = parcel.readInt();
                    LinkedHashMap linkedHashMap2 = new LinkedHashMap(readInt);
                    for (int i = 0; i != readInt; i++) {
                        linkedHashMap2.put(parcel.readString(), parcel.readString());
                    }
                    linkedHashMap = linkedHashMap2;
                }
                return new ApiFlags(readString, readString2, linkedHashMap);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final ApiFlags[] newArray(int i) {
                return new ApiFlags[i];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiFlags copy$default(ApiFlags apiFlags, String str, String str2, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = apiFlags.source;
            }
            if ((i & 2) != 0) {
                str2 = apiFlags.subSource;
            }
            if ((i & 4) != 0) {
                map = apiFlags.values;
            }
            return apiFlags.copy(str, str2, map);
        }

        /* renamed from: component1, reason: from getter */
        public final String getSource() {
            return this.source;
        }

        /* renamed from: component2, reason: from getter */
        public final String getSubSource() {
            return this.subSource;
        }

        public final Map<String, String> component3() {
            return this.values;
        }

        public final ApiFlags copy(String source, String subSource, Map<String, String> values) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new ApiFlags(source, subSource, values);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ApiFlags)) {
                return false;
            }
            ApiFlags apiFlags = (ApiFlags) other;
            return Intrinsics.areEqual(this.source, apiFlags.source) && Intrinsics.areEqual(this.subSource, apiFlags.subSource) && Intrinsics.areEqual(this.values, apiFlags.values);
        }

        public int hashCode() {
            int hashCode = this.source.hashCode() * 31;
            String str = this.subSource;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            Map<String, String> map = this.values;
            return hashCode2 + (map != null ? map.hashCode() : 0);
        }

        public String toString() {
            return "ApiFlags(source=" + this.source + ", subSource=" + this.subSource + ", values=" + this.values + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.source);
            parcel.writeString(this.subSource);
            Map<String, String> map = this.values;
            if (map == null) {
                parcel.writeInt(0);
                return;
            }
            parcel.writeInt(1);
            parcel.writeInt(map.size());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeString(entry.getValue());
            }
        }

        public ApiFlags(String source, String str, Map<String, String> map) {
            Intrinsics.checkNotNullParameter(source, "source");
            this.source = source;
            this.subSource = str;
            this.values = map;
        }

        public /* synthetic */ ApiFlags(String str, String str2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : map);
        }

        public final /* synthetic */ String getSource() {
            return this.source;
        }

        public final /* synthetic */ String getSubSource() {
            return this.subSource;
        }

        public final /* synthetic */ Map getValues() {
            return this.values;
        }
    }
}
