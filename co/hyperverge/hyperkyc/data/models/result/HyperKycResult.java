package co.hyperverge.hyperkyc.data.models.result;

import android.app.Application;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.Gson;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: HyperKycResult.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 F2\u00020\u0001:\u0001FB\u007f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\u0010\u0010*\u001a\u0004\u0018\u00010\u000fHÀ\u0003¢\u0006\u0002\b+J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\u0015\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0016J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00101\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b2J\u0010\u00103\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b4J\u0010\u00105\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0002\b6J\u008c\u0001\u00107\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÆ\u0001¢\u0006\u0002\u00108J\t\u00109\u001a\u00020\bHÖ\u0001J\u0013\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010=HÖ\u0003J\b\u0010>\u001a\u0004\u0018\u00010\u0003J\t\u0010?\u001a\u00020\bHÖ\u0001J\t\u0010@\u001a\u00020\u0003HÖ\u0001J\u0019\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020\bHÖ\u0001R&\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0019\"\u0004\b\u001f\u0010 R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0019\"\u0004\b\"\u0010 R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0019\"\u0004\b$\u0010 R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0019\"\u0004\b&\u0010 R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0019¨\u0006G"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycResult;", "Landroid/os/Parcelable;", "status", "", "transactionId", "details", "", "errorCode", "", "errorMessage", "latestModule", "latestCondition", "latestRule", "latestRuleRaw", HyperKycData.ARG_KEY, "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;)V", "getDetails", "()Ljava/util/Map;", "setDetails", "(Ljava/util/Map;)V", "getErrorCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getErrorMessage", "()Ljava/lang/String;", "getHyperKycData$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;", "setHyperKycData$hyperkyc_release", "(Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;)V", "getLatestCondition$hyperkyc_release", "setLatestCondition$hyperkyc_release", "(Ljava/lang/String;)V", "getLatestModule", "setLatestModule", "getLatestRule$hyperkyc_release", "setLatestRule$hyperkyc_release", "getLatestRuleRaw$hyperkyc_release", "setLatestRuleRaw$hyperkyc_release", "getStatus", "getTransactionId", "component1", "component10", "component10$hyperkyc_release", "component2", "component3", "component4", "component5", "component6", "component7", "component7$hyperkyc_release", "component8", "component8$hyperkyc_release", "component9", "component9$hyperkyc_release", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;)Lco/hyperverge/hyperkyc/data/models/result/HyperKycResult;", "describeContents", "equals", "", "other", "", "getRawDataJsonString", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class HyperKycResult implements Parcelable {
    public static final /* synthetic */ String ARG_CACHE_FILE_PATH_KEY = "hyperKycResult_cacheFilePath";
    public static final /* synthetic */ String ARG_KEY = "hyperKycResult";
    private Map<String, String> details;
    private final Integer errorCode;
    private final String errorMessage;
    private HyperKycData hyperKycData;
    private String latestCondition;
    private String latestModule;
    private String latestRule;
    private String latestRuleRaw;
    private final String status;
    private final String transactionId;
    public static final Parcelable.Creator<HyperKycResult> CREATOR = new Creator();

    /* compiled from: HyperKycResult.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Creator implements Parcelable.Creator<HyperKycResult> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final HyperKycResult createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            int readInt = parcel.readInt();
            LinkedHashMap linkedHashMap = new LinkedHashMap(readInt);
            for (int i = 0; i != readInt; i++) {
                linkedHashMap.put(parcel.readString(), parcel.readString());
            }
            return new HyperKycResult(readString, readString2, linkedHashMap, parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? HyperKycData.CREATOR.createFromParcel(parcel) : null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final HyperKycResult[] newArray(int i) {
            return new HyperKycResult[i];
        }
    }

    /* renamed from: component1, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* renamed from: component10$hyperkyc_release, reason: from getter */
    public final HyperKycData getHyperKycData() {
        return this.hyperKycData;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTransactionId() {
        return this.transactionId;
    }

    public final Map<String, String> component3() {
        return this.details;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getErrorCode() {
        return this.errorCode;
    }

    /* renamed from: component5, reason: from getter */
    public final String getErrorMessage() {
        return this.errorMessage;
    }

    /* renamed from: component6, reason: from getter */
    public final String getLatestModule() {
        return this.latestModule;
    }

    /* renamed from: component7$hyperkyc_release, reason: from getter */
    public final String getLatestCondition() {
        return this.latestCondition;
    }

    /* renamed from: component8$hyperkyc_release, reason: from getter */
    public final String getLatestRule() {
        return this.latestRule;
    }

    /* renamed from: component9$hyperkyc_release, reason: from getter */
    public final String getLatestRuleRaw() {
        return this.latestRuleRaw;
    }

    public final HyperKycResult copy(String status, String transactionId, Map<String, String> details, Integer errorCode, String errorMessage, String latestModule, String latestCondition, String latestRule, String latestRuleRaw, HyperKycData hyperKycData) {
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(details, "details");
        return new HyperKycResult(status, transactionId, details, errorCode, errorMessage, latestModule, latestCondition, latestRule, latestRuleRaw, hyperKycData);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HyperKycResult)) {
            return false;
        }
        HyperKycResult hyperKycResult = (HyperKycResult) other;
        return Intrinsics.areEqual(this.status, hyperKycResult.status) && Intrinsics.areEqual(this.transactionId, hyperKycResult.transactionId) && Intrinsics.areEqual(this.details, hyperKycResult.details) && Intrinsics.areEqual(this.errorCode, hyperKycResult.errorCode) && Intrinsics.areEqual(this.errorMessage, hyperKycResult.errorMessage) && Intrinsics.areEqual(this.latestModule, hyperKycResult.latestModule) && Intrinsics.areEqual(this.latestCondition, hyperKycResult.latestCondition) && Intrinsics.areEqual(this.latestRule, hyperKycResult.latestRule) && Intrinsics.areEqual(this.latestRuleRaw, hyperKycResult.latestRuleRaw) && Intrinsics.areEqual(this.hyperKycData, hyperKycResult.hyperKycData);
    }

    public int hashCode() {
        int hashCode = ((((this.status.hashCode() * 31) + this.transactionId.hashCode()) * 31) + this.details.hashCode()) * 31;
        Integer num = this.errorCode;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.errorMessage;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.latestModule;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.latestCondition;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.latestRule;
        int hashCode6 = (hashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.latestRuleRaw;
        int hashCode7 = (hashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
        HyperKycData hyperKycData = this.hyperKycData;
        return hashCode7 + (hyperKycData != null ? hyperKycData.hashCode() : 0);
    }

    public String toString() {
        return "HyperKycResult(status=" + this.status + ", transactionId=" + this.transactionId + ", details=" + this.details + ", errorCode=" + this.errorCode + ", errorMessage=" + this.errorMessage + ", latestModule=" + this.latestModule + ", latestCondition=" + this.latestCondition + ", latestRule=" + this.latestRule + ", latestRuleRaw=" + this.latestRuleRaw + ", hyperKycData=" + this.hyperKycData + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.status);
        parcel.writeString(this.transactionId);
        Map<String, String> map = this.details;
        parcel.writeInt(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            parcel.writeString(entry.getKey());
            parcel.writeString(entry.getValue());
        }
        Integer num = this.errorCode;
        if (num == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(num.intValue());
        }
        parcel.writeString(this.errorMessage);
        parcel.writeString(this.latestModule);
        parcel.writeString(this.latestCondition);
        parcel.writeString(this.latestRule);
        parcel.writeString(this.latestRuleRaw);
        HyperKycData hyperKycData = this.hyperKycData;
        if (hyperKycData == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            hyperKycData.writeToParcel(parcel, flags);
        }
    }

    public HyperKycResult(String status, String transactionId, Map<String, String> details, Integer num, String str, String str2, String str3, String str4, String str5, HyperKycData hyperKycData) {
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(details, "details");
        this.status = status;
        this.transactionId = transactionId;
        this.details = details;
        this.errorCode = num;
        this.errorMessage = str;
        this.latestModule = str2;
        this.latestCondition = str3;
        this.latestRule = str4;
        this.latestRuleRaw = str5;
        this.hyperKycData = hyperKycData;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getTransactionId() {
        return this.transactionId;
    }

    public /* synthetic */ HyperKycResult(String str, String str2, Map map, Integer num, String str3, String str4, String str5, String str6, String str7, HyperKycData hyperKycData, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? MapsKt.emptyMap() : map, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? null : str7, (i & 512) != 0 ? null : hyperKycData);
    }

    public final Map<String, String> getDetails() {
        return this.details;
    }

    public final void setDetails(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.details = map;
    }

    public final Integer getErrorCode() {
        return this.errorCode;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final String getLatestModule() {
        return this.latestModule;
    }

    public final void setLatestModule(String str) {
        this.latestModule = str;
    }

    public final String getLatestCondition$hyperkyc_release() {
        return this.latestCondition;
    }

    public final void setLatestCondition$hyperkyc_release(String str) {
        this.latestCondition = str;
    }

    public final String getLatestRule$hyperkyc_release() {
        return this.latestRule;
    }

    public final void setLatestRule$hyperkyc_release(String str) {
        this.latestRule = str;
    }

    public final String getLatestRuleRaw$hyperkyc_release() {
        return this.latestRuleRaw;
    }

    public final void setLatestRuleRaw$hyperkyc_release(String str) {
        this.latestRuleRaw = str;
    }

    public final HyperKycData getHyperKycData$hyperkyc_release() {
        return this.hyperKycData;
    }

    public final void setHyperKycData$hyperkyc_release(HyperKycData hyperKycData) {
        this.hyperKycData = hyperKycData;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String getRawDataJsonString() {
        String canonicalName;
        Object m1202constructorimpl;
        Object m1202constructorimpl2;
        String canonicalName2;
        String className;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("getRawDataJsonString() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                m1202constructorimpl2 = "";
            }
            String packageName = (String) m1202constructorimpl2;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "getRawDataJsonString() called ");
                }
            }
        }
        try {
            Result.Companion companion4 = Result.INSTANCE;
            HyperKycResult hyperKycResult = this;
            m1202constructorimpl = Result.m1202constructorimpl(new Gson().toJson(this.hyperKycData));
        } catch (Throwable th2) {
            Result.Companion companion5 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th2));
        }
        return (String) (Result.m1208isFailureimpl(m1202constructorimpl) ? null : m1202constructorimpl);
    }
}
