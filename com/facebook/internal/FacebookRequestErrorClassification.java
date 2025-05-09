package com.facebook.internal;

import com.facebook.FacebookRequestError;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: FacebookRequestErrorClassification.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u007f\b\u0000\u0012\u001c\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0005\u0018\u00010\u0003\u0012\u001c\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0005\u0018\u00010\u0003\u0012\u001c\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0005\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\fJ\u001e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\t2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0012R'\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0005\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0005\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0005\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0010\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/facebook/internal/FacebookRequestErrorClassification;", "", "otherErrors", "", "", "", "transientErrors", "loginRecoverableErrors", "otherRecoveryMessage", "", "transientRecoveryMessage", "loginRecoverableRecoveryMessage", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLoginRecoverableErrors", "()Ljava/util/Map;", "getOtherErrors", "getTransientErrors", "classify", "Lcom/facebook/FacebookRequestError$Category;", "errorCode", "errorSubCode", "isTransient", "", "getRecoveryMessage", "category", "Companion", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FacebookRequestErrorClassification {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int EC_APP_NOT_INSTALLED = 412;
    public static final int EC_APP_TOO_MANY_CALLS = 4;
    public static final int EC_INVALID_SESSION = 102;
    public static final int EC_INVALID_TOKEN = 190;
    public static final int EC_RATE = 9;
    public static final int EC_SERVICE_UNAVAILABLE = 2;
    public static final int EC_TOO_MANY_USER_ACTION_CALLS = 341;
    public static final int EC_USER_TOO_MANY_CALLS = 17;
    public static final int ESC_APP_INACTIVE = 493;
    public static final int ESC_APP_NOT_INSTALLED = 458;
    public static final String KEY_LOGIN_RECOVERABLE = "login_recoverable";
    public static final String KEY_NAME = "name";
    public static final String KEY_OTHER = "other";
    public static final String KEY_RECOVERY_MESSAGE = "recovery_message";
    public static final String KEY_TRANSIENT = "transient";
    private static FacebookRequestErrorClassification defaultInstance;
    private final Map<Integer, Set<Integer>> loginRecoverableErrors;
    private final String loginRecoverableRecoveryMessage;
    private final Map<Integer, Set<Integer>> otherErrors;
    private final String otherRecoveryMessage;
    private final Map<Integer, Set<Integer>> transientErrors;
    private final String transientRecoveryMessage;

    /* compiled from: FacebookRequestErrorClassification.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FacebookRequestError.Category.values().length];
            try {
                iArr[FacebookRequestError.Category.OTHER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FacebookRequestError.Category.LOGIN_RECOVERABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FacebookRequestError.Category.TRANSIENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static final FacebookRequestErrorClassification createFromJSON(JSONArray jSONArray) {
        return INSTANCE.createFromJSON(jSONArray);
    }

    public static final synchronized FacebookRequestErrorClassification getDefaultErrorClassification() {
        FacebookRequestErrorClassification defaultErrorClassification;
        synchronized (FacebookRequestErrorClassification.class) {
            defaultErrorClassification = INSTANCE.getDefaultErrorClassification();
        }
        return defaultErrorClassification;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FacebookRequestErrorClassification(Map<Integer, ? extends Set<Integer>> map, Map<Integer, ? extends Set<Integer>> map2, Map<Integer, ? extends Set<Integer>> map3, String str, String str2, String str3) {
        this.otherErrors = map;
        this.transientErrors = map2;
        this.loginRecoverableErrors = map3;
        this.otherRecoveryMessage = str;
        this.transientRecoveryMessage = str2;
        this.loginRecoverableRecoveryMessage = str3;
    }

    public final Map<Integer, Set<Integer>> getOtherErrors() {
        return this.otherErrors;
    }

    public final Map<Integer, Set<Integer>> getTransientErrors() {
        return this.transientErrors;
    }

    public final Map<Integer, Set<Integer>> getLoginRecoverableErrors() {
        return this.loginRecoverableErrors;
    }

    public final String getRecoveryMessage(FacebookRequestError.Category category) {
        int i = category == null ? -1 : WhenMappings.$EnumSwitchMapping$0[category.ordinal()];
        if (i == 1) {
            return this.otherRecoveryMessage;
        }
        if (i == 2) {
            return this.loginRecoverableRecoveryMessage;
        }
        if (i != 3) {
            return null;
        }
        return this.transientRecoveryMessage;
    }

    public final FacebookRequestError.Category classify(int errorCode, int errorSubCode, boolean isTransient) {
        Set<Integer> set;
        Set<Integer> set2;
        Set<Integer> set3;
        if (isTransient) {
            return FacebookRequestError.Category.TRANSIENT;
        }
        Map<Integer, Set<Integer>> map = this.otherErrors;
        if (map != null && map.containsKey(Integer.valueOf(errorCode)) && ((set3 = this.otherErrors.get(Integer.valueOf(errorCode))) == null || set3.contains(Integer.valueOf(errorSubCode)))) {
            return FacebookRequestError.Category.OTHER;
        }
        Map<Integer, Set<Integer>> map2 = this.loginRecoverableErrors;
        if (map2 != null && map2.containsKey(Integer.valueOf(errorCode)) && ((set2 = this.loginRecoverableErrors.get(Integer.valueOf(errorCode))) == null || set2.contains(Integer.valueOf(errorSubCode)))) {
            return FacebookRequestError.Category.LOGIN_RECOVERABLE;
        }
        Map<Integer, Set<Integer>> map3 = this.transientErrors;
        if (map3 != null && map3.containsKey(Integer.valueOf(errorCode)) && ((set = this.transientErrors.get(Integer.valueOf(errorCode))) == null || set.contains(Integer.valueOf(errorSubCode)))) {
            return FacebookRequestError.Category.TRANSIENT;
        }
        return FacebookRequestError.Category.OTHER;
    }

    /* compiled from: FacebookRequestErrorClassification.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u00152\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0007J&\u0010\u001f\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010!\u0018\u00010 2\u0006\u0010\"\u001a\u00020#H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u00158FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\u0002\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/facebook/internal/FacebookRequestErrorClassification$Companion;", "", "()V", "EC_APP_NOT_INSTALLED", "", "EC_APP_TOO_MANY_CALLS", "EC_INVALID_SESSION", "EC_INVALID_TOKEN", "EC_RATE", "EC_SERVICE_UNAVAILABLE", "EC_TOO_MANY_USER_ACTION_CALLS", "EC_USER_TOO_MANY_CALLS", "ESC_APP_INACTIVE", "ESC_APP_NOT_INSTALLED", "KEY_LOGIN_RECOVERABLE", "", "KEY_NAME", "KEY_OTHER", "KEY_RECOVERY_MESSAGE", "KEY_TRANSIENT", "defaultErrorClassification", "Lcom/facebook/internal/FacebookRequestErrorClassification;", "getDefaultErrorClassification$annotations", "getDefaultErrorClassification", "()Lcom/facebook/internal/FacebookRequestErrorClassification;", "defaultErrorClassificationImpl", "getDefaultErrorClassificationImpl", "defaultInstance", "createFromJSON", "jsonArray", "Lorg/json/JSONArray;", "parseJSONDefinition", "", "", "definition", "Lorg/json/JSONObject;", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getDefaultErrorClassification$annotations() {
        }

        private Companion() {
        }

        public final synchronized FacebookRequestErrorClassification getDefaultErrorClassification() {
            FacebookRequestErrorClassification facebookRequestErrorClassification;
            if (FacebookRequestErrorClassification.defaultInstance == null) {
                FacebookRequestErrorClassification.defaultInstance = getDefaultErrorClassificationImpl();
            }
            facebookRequestErrorClassification = FacebookRequestErrorClassification.defaultInstance;
            Intrinsics.checkNotNull(facebookRequestErrorClassification, "null cannot be cast to non-null type com.facebook.internal.FacebookRequestErrorClassification");
            return facebookRequestErrorClassification;
        }

        private final FacebookRequestErrorClassification getDefaultErrorClassificationImpl() {
            return new FacebookRequestErrorClassification(null, MapsKt.hashMapOf(TuplesKt.to(2, null), TuplesKt.to(4, null), TuplesKt.to(9, null), TuplesKt.to(17, null), TuplesKt.to(341, null)), MapsKt.hashMapOf(TuplesKt.to(102, null), TuplesKt.to(190, null), TuplesKt.to(Integer.valueOf(FacebookRequestErrorClassification.EC_APP_NOT_INSTALLED), null)), null, null, null);
        }

        private final Map<Integer, Set<Integer>> parseJSONDefinition(JSONObject definition) {
            int optInt;
            HashSet hashSet;
            JSONArray optJSONArray = definition.optJSONArray(FirebaseAnalytics.Param.ITEMS);
            if (optJSONArray == null || optJSONArray.length() == 0) {
                return null;
            }
            HashMap hashMap = new HashMap();
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null && (optInt = optJSONObject.optInt("code")) != 0) {
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("subcodes");
                    if (optJSONArray2 == null || optJSONArray2.length() <= 0) {
                        hashSet = null;
                    } else {
                        hashSet = new HashSet();
                        int length2 = optJSONArray2.length();
                        for (int i2 = 0; i2 < length2; i2++) {
                            int optInt2 = optJSONArray2.optInt(i2);
                            if (optInt2 != 0) {
                                hashSet.add(Integer.valueOf(optInt2));
                            }
                        }
                    }
                    hashMap.put(Integer.valueOf(optInt), hashSet);
                }
            }
            return hashMap;
        }

        @JvmStatic
        public final FacebookRequestErrorClassification createFromJSON(JSONArray jsonArray) {
            String optString;
            if (jsonArray == null) {
                return null;
            }
            int length = jsonArray.length();
            Map<Integer, Set<Integer>> map = null;
            Map<Integer, Set<Integer>> map2 = null;
            Map<Integer, Set<Integer>> map3 = null;
            String str = null;
            String str2 = null;
            String str3 = null;
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = jsonArray.optJSONObject(i);
                if (optJSONObject != null && (optString = optJSONObject.optString("name")) != null) {
                    if (StringsKt.equals(optString, "other", true)) {
                        str = optJSONObject.optString(FacebookRequestErrorClassification.KEY_RECOVERY_MESSAGE, null);
                        map = parseJSONDefinition(optJSONObject);
                    } else if (StringsKt.equals(optString, FacebookRequestErrorClassification.KEY_TRANSIENT, true)) {
                        str2 = optJSONObject.optString(FacebookRequestErrorClassification.KEY_RECOVERY_MESSAGE, null);
                        map2 = parseJSONDefinition(optJSONObject);
                    } else if (StringsKt.equals(optString, FacebookRequestErrorClassification.KEY_LOGIN_RECOVERABLE, true)) {
                        str3 = optJSONObject.optString(FacebookRequestErrorClassification.KEY_RECOVERY_MESSAGE, null);
                        map3 = parseJSONDefinition(optJSONObject);
                    }
                }
            }
            return new FacebookRequestErrorClassification(map, map2, map3, str, str2, str3);
        }
    }
}
