package com.facebook.internal;

import android.net.Uri;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: FetchedAppSettings.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b-\u0018\u0000 X2\u00020\u0001:\u0002XYB\u008d\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u001e\u0010\f\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r0\r\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\u0006\u0010\u0013\u001a\u00020\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0005\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0003\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0017\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0017\u0012\u0014\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0017\u0012\b\u0010\"\u001a\u0004\u0018\u00010\u0017\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0017\u0012\b\u0010$\u001a\u0004\u0018\u00010\u0017\u0012\b\u0010%\u001a\u0004\u0018\u00010\u0017\u0012\u000e\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010'\u0012\u000e\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010'\u0012 \u0010)\u001a\u001c\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050'0*\u0018\u00010'\u0012 \u0010+\u001a\u001c\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050'0*\u0018\u00010'\u0012\b\u0010,\u001a\u0004\u0018\u00010-¢\u0006\u0002\u0010.J\u0006\u0010\u0002\u001a\u00020\u0003R\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0013\u0010%\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b3\u00100R\u0013\u0010!\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b4\u00100R\u0011\u0010\u0015\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u00102R\u0019\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010'¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0015\u0010,\u001a\u0004\u0018\u00010-¢\u0006\n\n\u0002\u0010:\u001a\u0004\b8\u00109R)\u0010\f\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r0\r¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b?\u00100R\u0011\u0010\u0014\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b@\u00102R\u001f\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\bA\u0010<R\u0011\u0010\u001a\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bB\u00102R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bE\u00102R+\u0010)\u001a\u001c\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050'0*\u0018\u00010'¢\u0006\b\n\u0000\u001a\u0004\bF\u00107R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\bG\u00100R\u0019\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010'¢\u0006\b\n\u0000\u001a\u0004\bH\u00107R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bI\u0010DR\u0013\u0010\"\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\bJ\u00100R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bK\u0010DR\u0013\u0010$\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\bL\u00100R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bM\u0010DR\u0013\u0010#\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\bN\u00100R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\bO\u0010PR\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010DR\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bR\u0010DR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\bS\u0010TR\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bU\u0010DR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010+\u001a\u001c\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050'0*\u0018\u00010'¢\u0006\b\n\u0000\u001a\u0004\bV\u00107R\u0011\u0010\u0019\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bW\u00102¨\u0006Z"}, d2 = {"Lcom/facebook/internal/FetchedAppSettings;", "", "supportsImplicitLogging", "", "nuxContent", "", "nuxEnabled", "sessionTimeoutInSeconds", "", "smartLoginOptions", "Ljava/util/EnumSet;", "Lcom/facebook/internal/SmartLoginOption;", "dialogConfigurations", "", "Lcom/facebook/internal/FetchedAppSettings$DialogFeatureConfig;", "automaticLoggingEnabled", "errorClassification", "Lcom/facebook/internal/FacebookRequestErrorClassification;", "smartLoginBookmarkIconURL", "smartLoginMenuIconURL", "iAPAutomaticLoggingEnabled", "codelessEventsEnabled", "eventBindings", "Lorg/json/JSONArray;", "sdkUpdateMessage", "trackUninstallEnabled", "monitorViaDialogEnabled", "rawAamRules", "suggestedEventsSetting", "restrictiveDataSetting", "protectedModeStandardParamsSetting", "MACARuleMatchingSetting", "migratedAutoLogValues", "blocklistEvents", "redactedEvents", "sensitiveParams", "schemaRestrictions", "bannedParams", "currencyDedupeParameters", "", "purchaseValueDedupeParameters", "prodDedupeParameters", "Lkotlin/Pair;", "testDedupeParameters", "dedupeWindow", "", "(ZLjava/lang/String;ZILjava/util/EnumSet;Ljava/util/Map;ZLcom/facebook/internal/FacebookRequestErrorClassification;Ljava/lang/String;Ljava/lang/String;ZZLorg/json/JSONArray;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONArray;Lorg/json/JSONArray;Ljava/util/Map;Lorg/json/JSONArray;Lorg/json/JSONArray;Lorg/json/JSONArray;Lorg/json/JSONArray;Lorg/json/JSONArray;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Long;)V", "getMACARuleMatchingSetting", "()Lorg/json/JSONArray;", "getAutomaticLoggingEnabled", "()Z", "getBannedParams", "getBlocklistEvents", "getCodelessEventsEnabled", "getCurrencyDedupeParameters", "()Ljava/util/List;", "getDedupeWindow", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getDialogConfigurations", "()Ljava/util/Map;", "getErrorClassification", "()Lcom/facebook/internal/FacebookRequestErrorClassification;", "getEventBindings", "getIAPAutomaticLoggingEnabled", "getMigratedAutoLogValues", "getMonitorViaDialogEnabled", "getNuxContent", "()Ljava/lang/String;", "getNuxEnabled", "getProdDedupeParameters", "getProtectedModeStandardParamsSetting", "getPurchaseValueDedupeParameters", "getRawAamRules", "getRedactedEvents", "getRestrictiveDataSetting", "getSchemaRestrictions", "getSdkUpdateMessage", "getSensitiveParams", "getSessionTimeoutInSeconds", "()I", "getSmartLoginBookmarkIconURL", "getSmartLoginMenuIconURL", "getSmartLoginOptions", "()Ljava/util/EnumSet;", "getSuggestedEventsSetting", "getTestDedupeParameters", "getTrackUninstallEnabled", "Companion", "DialogFeatureConfig", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FetchedAppSettings {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final JSONArray MACARuleMatchingSetting;
    private final boolean automaticLoggingEnabled;
    private final JSONArray bannedParams;
    private final JSONArray blocklistEvents;
    private final boolean codelessEventsEnabled;
    private final List<String> currencyDedupeParameters;
    private final Long dedupeWindow;
    private final Map<String, Map<String, DialogFeatureConfig>> dialogConfigurations;
    private final FacebookRequestErrorClassification errorClassification;
    private final JSONArray eventBindings;
    private final boolean iAPAutomaticLoggingEnabled;
    private final Map<String, Boolean> migratedAutoLogValues;
    private final boolean monitorViaDialogEnabled;
    private final String nuxContent;
    private final boolean nuxEnabled;
    private final List<Pair<String, List<String>>> prodDedupeParameters;
    private final JSONArray protectedModeStandardParamsSetting;
    private final List<String> purchaseValueDedupeParameters;
    private final String rawAamRules;
    private final JSONArray redactedEvents;
    private final String restrictiveDataSetting;
    private final JSONArray schemaRestrictions;
    private final String sdkUpdateMessage;
    private final JSONArray sensitiveParams;
    private final int sessionTimeoutInSeconds;
    private final String smartLoginBookmarkIconURL;
    private final String smartLoginMenuIconURL;
    private final EnumSet<SmartLoginOption> smartLoginOptions;
    private final String suggestedEventsSetting;
    private final boolean supportsImplicitLogging;
    private final List<Pair<String, List<String>>> testDedupeParameters;
    private final boolean trackUninstallEnabled;

    @JvmStatic
    public static final DialogFeatureConfig getDialogFeatureConfig(String str, String str2, String str3) {
        return INSTANCE.getDialogFeatureConfig(str, str2, str3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FetchedAppSettings(boolean z, String nuxContent, boolean z2, int i, EnumSet<SmartLoginOption> smartLoginOptions, Map<String, ? extends Map<String, DialogFeatureConfig>> dialogConfigurations, boolean z3, FacebookRequestErrorClassification errorClassification, String smartLoginBookmarkIconURL, String smartLoginMenuIconURL, boolean z4, boolean z5, JSONArray jSONArray, String sdkUpdateMessage, boolean z6, boolean z7, String str, String str2, String str3, JSONArray jSONArray2, JSONArray jSONArray3, Map<String, Boolean> map, JSONArray jSONArray4, JSONArray jSONArray5, JSONArray jSONArray6, JSONArray jSONArray7, JSONArray jSONArray8, List<String> list, List<String> list2, List<? extends Pair<String, ? extends List<String>>> list3, List<? extends Pair<String, ? extends List<String>>> list4, Long l) {
        Intrinsics.checkNotNullParameter(nuxContent, "nuxContent");
        Intrinsics.checkNotNullParameter(smartLoginOptions, "smartLoginOptions");
        Intrinsics.checkNotNullParameter(dialogConfigurations, "dialogConfigurations");
        Intrinsics.checkNotNullParameter(errorClassification, "errorClassification");
        Intrinsics.checkNotNullParameter(smartLoginBookmarkIconURL, "smartLoginBookmarkIconURL");
        Intrinsics.checkNotNullParameter(smartLoginMenuIconURL, "smartLoginMenuIconURL");
        Intrinsics.checkNotNullParameter(sdkUpdateMessage, "sdkUpdateMessage");
        this.supportsImplicitLogging = z;
        this.nuxContent = nuxContent;
        this.nuxEnabled = z2;
        this.sessionTimeoutInSeconds = i;
        this.smartLoginOptions = smartLoginOptions;
        this.dialogConfigurations = dialogConfigurations;
        this.automaticLoggingEnabled = z3;
        this.errorClassification = errorClassification;
        this.smartLoginBookmarkIconURL = smartLoginBookmarkIconURL;
        this.smartLoginMenuIconURL = smartLoginMenuIconURL;
        this.iAPAutomaticLoggingEnabled = z4;
        this.codelessEventsEnabled = z5;
        this.eventBindings = jSONArray;
        this.sdkUpdateMessage = sdkUpdateMessage;
        this.trackUninstallEnabled = z6;
        this.monitorViaDialogEnabled = z7;
        this.rawAamRules = str;
        this.suggestedEventsSetting = str2;
        this.restrictiveDataSetting = str3;
        this.protectedModeStandardParamsSetting = jSONArray2;
        this.MACARuleMatchingSetting = jSONArray3;
        this.migratedAutoLogValues = map;
        this.blocklistEvents = jSONArray4;
        this.redactedEvents = jSONArray5;
        this.sensitiveParams = jSONArray6;
        this.schemaRestrictions = jSONArray7;
        this.bannedParams = jSONArray8;
        this.currencyDedupeParameters = list;
        this.purchaseValueDedupeParameters = list2;
        this.prodDedupeParameters = list3;
        this.testDedupeParameters = list4;
        this.dedupeWindow = l;
    }

    public final String getNuxContent() {
        return this.nuxContent;
    }

    public final boolean getNuxEnabled() {
        return this.nuxEnabled;
    }

    public final int getSessionTimeoutInSeconds() {
        return this.sessionTimeoutInSeconds;
    }

    public final EnumSet<SmartLoginOption> getSmartLoginOptions() {
        return this.smartLoginOptions;
    }

    public final Map<String, Map<String, DialogFeatureConfig>> getDialogConfigurations() {
        return this.dialogConfigurations;
    }

    public final boolean getAutomaticLoggingEnabled() {
        return this.automaticLoggingEnabled;
    }

    public final FacebookRequestErrorClassification getErrorClassification() {
        return this.errorClassification;
    }

    public final String getSmartLoginBookmarkIconURL() {
        return this.smartLoginBookmarkIconURL;
    }

    public final String getSmartLoginMenuIconURL() {
        return this.smartLoginMenuIconURL;
    }

    public final boolean getIAPAutomaticLoggingEnabled() {
        return this.iAPAutomaticLoggingEnabled;
    }

    public final boolean getCodelessEventsEnabled() {
        return this.codelessEventsEnabled;
    }

    public final JSONArray getEventBindings() {
        return this.eventBindings;
    }

    public final String getSdkUpdateMessage() {
        return this.sdkUpdateMessage;
    }

    public final boolean getTrackUninstallEnabled() {
        return this.trackUninstallEnabled;
    }

    public final boolean getMonitorViaDialogEnabled() {
        return this.monitorViaDialogEnabled;
    }

    public final String getRawAamRules() {
        return this.rawAamRules;
    }

    public final String getSuggestedEventsSetting() {
        return this.suggestedEventsSetting;
    }

    public final String getRestrictiveDataSetting() {
        return this.restrictiveDataSetting;
    }

    public final JSONArray getProtectedModeStandardParamsSetting() {
        return this.protectedModeStandardParamsSetting;
    }

    public final JSONArray getMACARuleMatchingSetting() {
        return this.MACARuleMatchingSetting;
    }

    public final Map<String, Boolean> getMigratedAutoLogValues() {
        return this.migratedAutoLogValues;
    }

    public final JSONArray getBlocklistEvents() {
        return this.blocklistEvents;
    }

    public final JSONArray getRedactedEvents() {
        return this.redactedEvents;
    }

    public final JSONArray getSensitiveParams() {
        return this.sensitiveParams;
    }

    public final JSONArray getSchemaRestrictions() {
        return this.schemaRestrictions;
    }

    public final JSONArray getBannedParams() {
        return this.bannedParams;
    }

    public final List<String> getCurrencyDedupeParameters() {
        return this.currencyDedupeParameters;
    }

    public final List<String> getPurchaseValueDedupeParameters() {
        return this.purchaseValueDedupeParameters;
    }

    public final List<Pair<String, List<String>>> getProdDedupeParameters() {
        return this.prodDedupeParameters;
    }

    public final List<Pair<String, List<String>>> getTestDedupeParameters() {
        return this.testDedupeParameters;
    }

    public final Long getDedupeWindow() {
        return this.dedupeWindow;
    }

    /* renamed from: supportsImplicitLogging, reason: from getter */
    public final boolean getSupportsImplicitLogging() {
        return this.supportsImplicitLogging;
    }

    /* compiled from: FetchedAppSettings.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\n\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B+\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lcom/facebook/internal/FetchedAppSettings$DialogFeatureConfig;", "", "dialogName", "", "featureName", "fallbackUrl", "Landroid/net/Uri;", "versionSpec", "", "(Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;[I)V", "getDialogName", "()Ljava/lang/String;", "getFallbackUrl", "()Landroid/net/Uri;", "getFeatureName", "getVersionSpec", "()[I", "Companion", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class DialogFeatureConfig {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final String DIALOG_CONFIG_DIALOG_NAME_FEATURE_NAME_SEPARATOR = "|";
        private static final String DIALOG_CONFIG_NAME_KEY = "name";
        private static final String DIALOG_CONFIG_URL_KEY = "url";
        private static final String DIALOG_CONFIG_VERSIONS_KEY = "versions";
        private final String dialogName;
        private final Uri fallbackUrl;
        private final String featureName;
        private final int[] versionSpec;

        public /* synthetic */ DialogFeatureConfig(String str, String str2, Uri uri, int[] iArr, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, uri, iArr);
        }

        private DialogFeatureConfig(String str, String str2, Uri uri, int[] iArr) {
            this.dialogName = str;
            this.featureName = str2;
            this.fallbackUrl = uri;
            this.versionSpec = iArr;
        }

        public final String getDialogName() {
            return this.dialogName;
        }

        public final String getFeatureName() {
            return this.featureName;
        }

        public final Uri getFallbackUrl() {
            return this.fallbackUrl;
        }

        public final int[] getVersionSpec() {
            return this.versionSpec;
        }

        /* compiled from: FetchedAppSettings.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0014\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/facebook/internal/FetchedAppSettings$DialogFeatureConfig$Companion;", "", "()V", "DIALOG_CONFIG_DIALOG_NAME_FEATURE_NAME_SEPARATOR", "", "DIALOG_CONFIG_NAME_KEY", "DIALOG_CONFIG_URL_KEY", "DIALOG_CONFIG_VERSIONS_KEY", "parseDialogConfig", "Lcom/facebook/internal/FetchedAppSettings$DialogFeatureConfig;", "dialogConfigJSON", "Lorg/json/JSONObject;", "parseVersionSpec", "", "versionsJSON", "Lorg/json/JSONArray;", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final DialogFeatureConfig parseDialogConfig(JSONObject dialogConfigJSON) {
                Intrinsics.checkNotNullParameter(dialogConfigJSON, "dialogConfigJSON");
                String dialogNameWithFeature = dialogConfigJSON.optString("name");
                if (Utility.isNullOrEmpty(dialogNameWithFeature)) {
                    return null;
                }
                Intrinsics.checkNotNullExpressionValue(dialogNameWithFeature, "dialogNameWithFeature");
                List split$default = StringsKt.split$default((CharSequence) dialogNameWithFeature, new String[]{DialogFeatureConfig.DIALOG_CONFIG_DIALOG_NAME_FEATURE_NAME_SEPARATOR}, false, 0, 6, (Object) null);
                if (split$default.size() != 2) {
                    return null;
                }
                String str = (String) CollectionsKt.first(split$default);
                String str2 = (String) CollectionsKt.last(split$default);
                if (Utility.isNullOrEmpty(str) || Utility.isNullOrEmpty(str2)) {
                    return null;
                }
                String optString = dialogConfigJSON.optString("url");
                return new DialogFeatureConfig(str, str2, Utility.isNullOrEmpty(optString) ? null : Uri.parse(optString), parseVersionSpec(dialogConfigJSON.optJSONArray(DialogFeatureConfig.DIALOG_CONFIG_VERSIONS_KEY)), null);
            }

            private final int[] parseVersionSpec(JSONArray versionsJSON) {
                if (versionsJSON == null) {
                    return null;
                }
                int length = versionsJSON.length();
                int[] iArr = new int[length];
                for (int i = 0; i < length; i++) {
                    int i2 = -1;
                    int optInt = versionsJSON.optInt(i, -1);
                    if (optInt == -1) {
                        String versionString = versionsJSON.optString(i);
                        if (!Utility.isNullOrEmpty(versionString)) {
                            try {
                                Intrinsics.checkNotNullExpressionValue(versionString, "versionString");
                                i2 = Integer.parseInt(versionString);
                            } catch (NumberFormatException e) {
                                Utility.logd(Utility.LOG_TAG, e);
                            }
                            optInt = i2;
                        }
                    }
                    iArr[i] = optInt;
                }
                return iArr;
            }
        }
    }

    /* compiled from: FetchedAppSettings.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0007¨\u0006\t"}, d2 = {"Lcom/facebook/internal/FetchedAppSettings$Companion;", "", "()V", "getDialogFeatureConfig", "Lcom/facebook/internal/FetchedAppSettings$DialogFeatureConfig;", "applicationId", "", "actionName", "featureName", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final DialogFeatureConfig getDialogFeatureConfig(String applicationId, String actionName, String featureName) {
            Map<String, Map<String, DialogFeatureConfig>> dialogConfigurations;
            Intrinsics.checkNotNullParameter(applicationId, "applicationId");
            Intrinsics.checkNotNullParameter(actionName, "actionName");
            Intrinsics.checkNotNullParameter(featureName, "featureName");
            if (!(actionName.length() == 0)) {
                if (!(featureName.length() == 0)) {
                    FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(applicationId);
                    Map<String, DialogFeatureConfig> map = (appSettingsWithoutQuery == null || (dialogConfigurations = appSettingsWithoutQuery.getDialogConfigurations()) == null) ? null : dialogConfigurations.get(actionName);
                    if (map != null) {
                        return map.get(featureName);
                    }
                }
            }
            return null;
        }
    }
}
