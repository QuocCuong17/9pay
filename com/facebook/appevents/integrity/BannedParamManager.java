package com.facebook.appevents.integrity;

import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.json.JSONArray;

/* compiled from: BannedParamManager.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0007J\b\u0010\u000b\u001a\u00020\nH\u0007J\b\u0010\f\u001a\u00020\nH\u0002J\"\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J\u0012\u0010\u0010\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/facebook/appevents/integrity/BannedParamManager;", "", "()V", "bannedParamsConfig", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "enabled", "", "disable", "", "enable", "loadConfigs", "loadSet", "paramValues", "Lorg/json/JSONArray;", "processFilterBannedParams", "parameters", "Landroid/os/Bundle;", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BannedParamManager {
    public static final BannedParamManager INSTANCE = new BannedParamManager();
    private static HashSet<String> bannedParamsConfig = new HashSet<>();
    private static boolean enabled;

    private BannedParamManager() {
    }

    @JvmStatic
    public static final void enable() {
        if (CrashShieldHandler.isObjectCrashing(BannedParamManager.class)) {
            return;
        }
        try {
            if (enabled) {
                return;
            }
            INSTANCE.loadConfigs();
            enabled = !bannedParamsConfig.isEmpty();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, BannedParamManager.class);
        }
    }

    @JvmStatic
    public static final void disable() {
        if (CrashShieldHandler.isObjectCrashing(BannedParamManager.class)) {
            return;
        }
        try {
            enabled = false;
            bannedParamsConfig = new HashSet<>();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, BannedParamManager.class);
        }
    }

    private final void loadConfigs() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false);
            if (queryAppSettings == null) {
                return;
            }
            bannedParamsConfig = loadSet(queryAppSettings.getBannedParams());
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    private final HashSet<String> loadSet(JSONArray paramValues) {
        HashSet<String> hashSet;
        try {
            if (CrashShieldHandler.isObjectCrashing(this)) {
                return null;
            }
            try {
                hashSet = Utility.convertJSONArrayToHashSet(paramValues);
                if (hashSet == null) {
                    hashSet = new HashSet<>();
                }
            } catch (Exception unused) {
                hashSet = new HashSet<>();
            }
            return hashSet;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    @JvmStatic
    public static final void processFilterBannedParams(Bundle parameters) {
        if (CrashShieldHandler.isObjectCrashing(BannedParamManager.class)) {
            return;
        }
        try {
            if (enabled && parameters != null) {
                for (String str : bannedParamsConfig) {
                    if (parameters != null) {
                        parameters.remove(str);
                    }
                }
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, BannedParamManager.class);
        }
    }
}
