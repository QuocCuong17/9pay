package com.facebook.appevents;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.eventdeactivation.EventDeactivationManager;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.facebook.appevents.integrity.IntegrityManager;
import com.facebook.appevents.integrity.RedactedEventsManager;
import com.facebook.appevents.restrictivedatafilter.RestrictiveDataManager;
import com.facebook.internal.Logger;
import io.flutter.plugins.firebase.analytics.Constants;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.Regex;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AppEvent.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 -2\u00020\u0001:\u0002-.BQ\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010B'\b\u0012\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\n\u0012\u0006\u0010\u0014\u001a\u00020\n¢\u0006\u0002\u0010\u0015J\u0006\u0010 \u001a\u00020\nJ\u0006\u0010!\u001a\u00020\u0018J;\u0010\"\u001a\u00020\u00182\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0002\u0010#J\u0006\u0010$\u001a\u00020\u0018J\u0010\u0010$\u001a\u0004\u0018\u00010\u00182\u0006\u0010%\u001a\u00020&J\b\u0010'\u001a\u00020\u0003H\u0016J(\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030)2\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010*\u001a\u00020\nH\u0002J\b\u0010+\u001a\u00020,H\u0002R\u000e\u0010\u0014\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0013\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001a¨\u0006/"}, d2 = {"Lcom/facebook/appevents/AppEvent;", "Ljava/io/Serializable;", "contextName", "", Constants.EVENT_NAME, "valueToSum", "", "parameters", "Landroid/os/Bundle;", "isImplicitlyLogged", "", "isInBackground", "currentSessionId", "Ljava/util/UUID;", "operationalParameters", "Lcom/facebook/appevents/OperationalData;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Landroid/os/Bundle;ZZLjava/util/UUID;Lcom/facebook/appevents/OperationalData;)V", "jsonString", "operationalJsonString", "isImplicit", "inBackground", "(Ljava/lang/String;Ljava/lang/String;ZZ)V", "()Z", "jsonObject", "Lorg/json/JSONObject;", "getJsonObject", "()Lorg/json/JSONObject;", "name", "getName", "()Ljava/lang/String;", "operationalJsonObject", "getOperationalJsonObject", "getIsImplicit", "getJSONObject", "getJSONObjectForAppEvent", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Landroid/os/Bundle;Ljava/util/UUID;)Lorg/json/JSONObject;", "getOperationalJSONObject", "type", "Lcom/facebook/appevents/OperationalDataEnum;", InAppPurchaseConstants.METHOD_TO_STRING, "validateParameters", "", "isOperational", "writeReplace", "", "Companion", "SerializationProxyV2", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AppEvent implements Serializable {
    private static final int MAX_IDENTIFIER_LENGTH = 40;
    private static final long serialVersionUID = 1;
    private final boolean inBackground;
    private final boolean isImplicit;
    private final JSONObject jsonObject;
    private final String name;
    private final JSONObject operationalJsonObject;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final HashSet<String> validatedIdentifiers = new HashSet<>();

    public /* synthetic */ AppEvent(String str, String str2, boolean z, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, z, z2);
    }

    public final JSONObject getJsonObject() {
        return this.jsonObject;
    }

    public final JSONObject getOperationalJsonObject() {
        return this.operationalJsonObject;
    }

    public final boolean isImplicit() {
        return this.isImplicit;
    }

    public final String getName() {
        return this.name;
    }

    public /* synthetic */ AppEvent(String str, String str2, Double d, Bundle bundle, boolean z, boolean z2, UUID uuid, OperationalData operationalData, int i, DefaultConstructorMarker defaultConstructorMarker) throws JSONException, FacebookException {
        this(str, str2, d, bundle, z, z2, uuid, (i & 128) != 0 ? null : operationalData);
    }

    public AppEvent(String contextName, String eventName, Double d, Bundle bundle, boolean z, boolean z2, UUID uuid, OperationalData operationalData) throws JSONException, FacebookException {
        JSONObject json;
        Intrinsics.checkNotNullParameter(contextName, "contextName");
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        this.isImplicit = z;
        this.inBackground = z2;
        this.name = eventName;
        this.operationalJsonObject = (operationalData == null || (json = operationalData.toJSON()) == null) ? new JSONObject() : json;
        this.jsonObject = getJSONObjectForAppEvent(contextName, eventName, d, bundle, uuid);
    }

    private AppEvent(String str, String str2, boolean z, boolean z2) {
        JSONObject jSONObject = new JSONObject(str);
        this.jsonObject = jSONObject;
        this.operationalJsonObject = new JSONObject(str2);
        this.isImplicit = z;
        String optString = jSONObject.optString(com.facebook.appevents.internal.Constants.EVENT_NAME_EVENT_KEY);
        Intrinsics.checkNotNullExpressionValue(optString, "jsonObject.optString(Con…nts.EVENT_NAME_EVENT_KEY)");
        this.name = optString;
        this.inBackground = z2;
    }

    public final boolean getIsImplicit() {
        return this.isImplicit;
    }

    /* renamed from: getJSONObject, reason: from getter */
    public final JSONObject getJsonObject() {
        return this.jsonObject;
    }

    /* renamed from: getOperationalJSONObject, reason: from getter */
    public final JSONObject getOperationalJsonObject() {
        return this.operationalJsonObject;
    }

    public final JSONObject getOperationalJSONObject(OperationalDataEnum type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return this.operationalJsonObject.optJSONObject(type.getValue());
    }

    private final JSONObject getJSONObjectForAppEvent(String contextName, String eventName, Double valueToSum, Bundle parameters, UUID currentSessionId) {
        INSTANCE.validateIdentifier(eventName);
        JSONObject jSONObject = new JSONObject();
        String processEvent = RestrictiveDataManager.processEvent(eventName);
        if (Intrinsics.areEqual(processEvent, eventName)) {
            processEvent = RedactedEventsManager.processEventsRedaction(eventName);
        }
        jSONObject.put(com.facebook.appevents.internal.Constants.EVENT_NAME_EVENT_KEY, processEvent);
        jSONObject.put(com.facebook.appevents.internal.Constants.LOG_TIME_APP_EVENT_KEY, System.currentTimeMillis() / 1000);
        jSONObject.put("_ui", contextName);
        if (currentSessionId != null) {
            jSONObject.put("_session_id", currentSessionId);
        }
        if (parameters != null) {
            Map validateParameters$default = validateParameters$default(this, parameters, false, 2, null);
            for (String str : validateParameters$default.keySet()) {
                jSONObject.put(str, validateParameters$default.get(str));
            }
        }
        if (valueToSum != null) {
            jSONObject.put(AppEventsConstants.EVENT_PARAM_VALUE_TO_SUM, valueToSum.doubleValue());
        }
        if (this.inBackground) {
            jSONObject.put("_inBackground", "1");
        }
        if (this.isImplicit) {
            jSONObject.put("_implicitlyLogged", "1");
        } else {
            Logger.Companion companion = Logger.INSTANCE;
            LoggingBehavior loggingBehavior = LoggingBehavior.APP_EVENTS;
            String jSONObject2 = jSONObject.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject2, "eventObject.toString()");
            companion.log(loggingBehavior, "AppEvents", "Created app event '%s'", jSONObject2);
        }
        return jSONObject;
    }

    static /* synthetic */ Map validateParameters$default(AppEvent appEvent, Bundle bundle, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return appEvent.validateParameters(bundle, z);
    }

    private final Map<String, String> validateParameters(Bundle parameters, boolean isOperational) {
        HashMap hashMap = new HashMap();
        for (String key : parameters.keySet()) {
            Companion companion = INSTANCE;
            Intrinsics.checkNotNullExpressionValue(key, "key");
            companion.validateIdentifier(key);
            Object obj = parameters.get(key);
            if (!(obj instanceof String) && !(obj instanceof Number)) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("Parameter value '%s' for key '%s' should be a string or a numeric type.", Arrays.copyOf(new Object[]{obj, key}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                throw new FacebookException(format);
            }
            hashMap.put(key, obj.toString());
        }
        if (!isOperational) {
            IntegrityManager.processParameters(hashMap);
            RestrictiveDataManager.processParameters(TypeIntrinsics.asMutableMap(hashMap), this.name);
            EventDeactivationManager.processDeprecatedParameters(TypeIntrinsics.asMutableMap(hashMap), this.name);
        }
        return hashMap;
    }

    /* compiled from: AppEvent.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/facebook/appevents/AppEvent$SerializationProxyV2;", "Ljava/io/Serializable;", "jsonString", "", "operationalJsonString", "isImplicit", "", "inBackground", "(Ljava/lang/String;Ljava/lang/String;ZZ)V", "readResolve", "", "Companion", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class SerializationProxyV2 implements Serializable {
        private static final long serialVersionUID = 20160803001L;
        private final boolean inBackground;
        private final boolean isImplicit;
        private final String jsonString;
        private final String operationalJsonString;

        public SerializationProxyV2(String jsonString, String operationalJsonString, boolean z, boolean z2) {
            Intrinsics.checkNotNullParameter(jsonString, "jsonString");
            Intrinsics.checkNotNullParameter(operationalJsonString, "operationalJsonString");
            this.jsonString = jsonString;
            this.operationalJsonString = operationalJsonString;
            this.isImplicit = z;
            this.inBackground = z2;
        }

        private final Object readResolve() throws JSONException, ObjectStreamException {
            return new AppEvent(this.jsonString, this.operationalJsonString, this.isImplicit, this.inBackground, null);
        }
    }

    private final Object writeReplace() throws ObjectStreamException {
        String jSONObject = this.jsonObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "jsonObject.toString()");
        String jSONObject2 = this.operationalJsonObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "operationalJsonObject.toString()");
        return new SerializationProxyV2(jSONObject, jSONObject2, this.isImplicit, this.inBackground);
    }

    public String toString() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("\"%s\", implicit: %b, json: %s", Arrays.copyOf(new Object[]{this.jsonObject.optString(com.facebook.appevents.internal.Constants.EVENT_NAME_EVENT_KEY), Boolean.valueOf(this.isImplicit), this.jsonObject.toString()}, 3));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    /* compiled from: AppEvent.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/facebook/appevents/AppEvent$Companion;", "", "()V", "MAX_IDENTIFIER_LENGTH", "", "serialVersionUID", "", "validatedIdentifiers", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "validateIdentifier", "", "identifier", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void validateIdentifier(String identifier) {
            boolean contains;
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            String str = identifier;
            if (!(str.length() == 0) && identifier.length() <= 40) {
                synchronized (AppEvent.validatedIdentifiers) {
                    contains = AppEvent.validatedIdentifiers.contains(identifier);
                    Unit unit = Unit.INSTANCE;
                }
                if (contains) {
                    return;
                }
                if (new Regex("^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$").matches(str)) {
                    synchronized (AppEvent.validatedIdentifiers) {
                        AppEvent.validatedIdentifiers.add(identifier);
                    }
                    return;
                } else {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format("Skipping event named '%s' due to illegal name - must be under 40 chars and alphanumeric, _, - or space, and not start with a space or hyphen.", Arrays.copyOf(new Object[]{identifier}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    throw new FacebookException(format);
                }
            }
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String format2 = String.format(Locale.ROOT, "Identifier '%s' must be less than %d characters", Arrays.copyOf(new Object[]{identifier, 40}, 2));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            throw new FacebookException(format2);
        }
    }
}
