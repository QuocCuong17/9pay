package com.facebook.appevents.integrity;

import android.os.Build;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import co.hyperverge.hyperkyc.data.models.Properties;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.facebook.FacebookSdk;
import com.facebook.appevents.UserDataStore;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.HttpUrl;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: MACARuleMatchingManager.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0007J\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0007J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0012\u0010\u0014\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007J&\u0010\u0015\u001a\u0016\u0012\u0004\u0012\u00020\t\u0018\u00010\u0016j\n\u0012\u0004\u0012\u00020\t\u0018\u0001`\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004H\u0007J\u001c\u0010\u0019\u001a\u00020\u00062\b\u0010\u001a\u001a\u0004\u0018\u00010\t2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000fH\u0007J\b\u0010\u001c\u001a\u00020\fH\u0002J\u001a\u0010\u001d\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0007J\u0010\u0010\u001e\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\"\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\u000fH\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\n¨\u0006\""}, d2 = {"Lcom/facebook/appevents/integrity/MACARuleMatchingManager;", "", "()V", "MACARules", "Lorg/json/JSONArray;", "enabled", "", UserMetadata.KEYDATA_FILENAME, "", "", "[Ljava/lang/String;", "enable", "", "generateInfo", "params", "Landroid/os/Bundle;", NotificationCompat.CATEGORY_EVENT, "getKey", "logic", "Lorg/json/JSONObject;", "getMatchPropertyIDs", "getStringArrayList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "jsonArray", "isMatchCCRule", "ruleString", "data", "loadMACARules", "processParameters", "removeGeneratedInfo", "stringComparison", "variable", "values", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MACARuleMatchingManager {
    private static JSONArray MACARules;
    private static boolean enabled;
    public static final MACARuleMatchingManager INSTANCE = new MACARuleMatchingManager();
    private static String[] keys = {NotificationCompat.CATEGORY_EVENT, "_locale", "_appVersion", "_deviceOS", "_platform", "_deviceModel", "_nativeAppID", "_nativeAppShortVersion", "_timezone", "_carrier", "_deviceOSTypeName", "_deviceOSVersion", "_remainingDiskGB"};

    private MACARuleMatchingManager() {
    }

    @JvmStatic
    public static final void enable() {
        if (CrashShieldHandler.isObjectCrashing(MACARuleMatchingManager.class)) {
            return;
        }
        try {
            INSTANCE.loadMACARules();
            if (MACARules != null) {
                enabled = true;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, MACARuleMatchingManager.class);
        }
    }

    private final void loadMACARules() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false);
            if (queryAppSettings == null) {
                return;
            }
            MACARules = queryAppSettings.getMACARuleMatchingSetting();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    @JvmStatic
    public static final String getKey(JSONObject logic) {
        if (CrashShieldHandler.isObjectCrashing(MACARuleMatchingManager.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(logic, "logic");
            Iterator<String> keys2 = logic.keys();
            if (keys2.hasNext()) {
                return keys2.next();
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, MACARuleMatchingManager.class);
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0054, code lost:
    
        if (r7 == null) goto L19;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:23:0x0067. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:103:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:111:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:119:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:131:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0344 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0345 A[Catch: all -> 0x037a, TryCatch #0 {all -> 0x037a, blocks: (B:6:0x000a, B:9:0x001b, B:12:0x0036, B:20:0x0047, B:22:0x0062, B:23:0x0067, B:26:0x006c, B:30:0x0076, B:32:0x0092, B:36:0x009c, B:42:0x00ac, B:48:0x0222, B:50:0x0228, B:53:0x0232, B:54:0x0236, B:56:0x023c, B:63:0x00b6, B:67:0x00c0, B:69:0x00e0, B:75:0x026e, B:77:0x0274, B:81:0x027f, B:82:0x0283, B:84:0x0289, B:91:0x00ea, B:95:0x00f4, B:97:0x0110, B:101:0x01be, B:104:0x011a, B:108:0x01a3, B:112:0x0124, B:116:0x017e, B:120:0x012e, B:124:0x0138, B:128:0x0204, B:132:0x0142, B:136:0x014c, B:142:0x0345, B:144:0x0156, B:148:0x01d5, B:152:0x0160, B:156:0x016a, B:160:0x01f0, B:162:0x0174, B:166:0x018f, B:170:0x0199, B:174:0x01b4, B:178:0x01cb, B:182:0x01e6, B:186:0x01fa, B:190:0x0215, B:194:0x0261, B:198:0x02ad, B:202:0x02b7, B:206:0x02d5, B:210:0x02df, B:212:0x02ed, B:218:0x0330, B:220:0x02f7, B:224:0x0301, B:226:0x0312, B:230:0x031b, B:232:0x0324, B:236:0x0339, B:240:0x034e, B:244:0x0357, B:248:0x0058), top: B:5:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:151:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:217:0x032f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0330 A[Catch: all -> 0x037a, TryCatch #0 {all -> 0x037a, blocks: (B:6:0x000a, B:9:0x001b, B:12:0x0036, B:20:0x0047, B:22:0x0062, B:23:0x0067, B:26:0x006c, B:30:0x0076, B:32:0x0092, B:36:0x009c, B:42:0x00ac, B:48:0x0222, B:50:0x0228, B:53:0x0232, B:54:0x0236, B:56:0x023c, B:63:0x00b6, B:67:0x00c0, B:69:0x00e0, B:75:0x026e, B:77:0x0274, B:81:0x027f, B:82:0x0283, B:84:0x0289, B:91:0x00ea, B:95:0x00f4, B:97:0x0110, B:101:0x01be, B:104:0x011a, B:108:0x01a3, B:112:0x0124, B:116:0x017e, B:120:0x012e, B:124:0x0138, B:128:0x0204, B:132:0x0142, B:136:0x014c, B:142:0x0345, B:144:0x0156, B:148:0x01d5, B:152:0x0160, B:156:0x016a, B:160:0x01f0, B:162:0x0174, B:166:0x018f, B:170:0x0199, B:174:0x01b4, B:178:0x01cb, B:182:0x01e6, B:186:0x01fa, B:190:0x0215, B:194:0x0261, B:198:0x02ad, B:202:0x02b7, B:206:0x02d5, B:210:0x02df, B:212:0x02ed, B:218:0x0330, B:220:0x02f7, B:224:0x0301, B:226:0x0312, B:230:0x031b, B:232:0x0324, B:236:0x0339, B:240:0x034e, B:244:0x0357, B:248:0x0058), top: B:5:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0221 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0222 A[Catch: all -> 0x037a, TryCatch #0 {all -> 0x037a, blocks: (B:6:0x000a, B:9:0x001b, B:12:0x0036, B:20:0x0047, B:22:0x0062, B:23:0x0067, B:26:0x006c, B:30:0x0076, B:32:0x0092, B:36:0x009c, B:42:0x00ac, B:48:0x0222, B:50:0x0228, B:53:0x0232, B:54:0x0236, B:56:0x023c, B:63:0x00b6, B:67:0x00c0, B:69:0x00e0, B:75:0x026e, B:77:0x0274, B:81:0x027f, B:82:0x0283, B:84:0x0289, B:91:0x00ea, B:95:0x00f4, B:97:0x0110, B:101:0x01be, B:104:0x011a, B:108:0x01a3, B:112:0x0124, B:116:0x017e, B:120:0x012e, B:124:0x0138, B:128:0x0204, B:132:0x0142, B:136:0x014c, B:142:0x0345, B:144:0x0156, B:148:0x01d5, B:152:0x0160, B:156:0x016a, B:160:0x01f0, B:162:0x0174, B:166:0x018f, B:170:0x0199, B:174:0x01b4, B:178:0x01cb, B:182:0x01e6, B:186:0x01fa, B:190:0x0215, B:194:0x0261, B:198:0x02ad, B:202:0x02b7, B:206:0x02d5, B:210:0x02df, B:212:0x02ed, B:218:0x0330, B:220:0x02f7, B:224:0x0301, B:226:0x0312, B:230:0x031b, B:232:0x0324, B:236:0x0339, B:240:0x034e, B:244:0x0357, B:248:0x0058), top: B:5:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x026d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x026e A[Catch: all -> 0x037a, TryCatch #0 {all -> 0x037a, blocks: (B:6:0x000a, B:9:0x001b, B:12:0x0036, B:20:0x0047, B:22:0x0062, B:23:0x0067, B:26:0x006c, B:30:0x0076, B:32:0x0092, B:36:0x009c, B:42:0x00ac, B:48:0x0222, B:50:0x0228, B:53:0x0232, B:54:0x0236, B:56:0x023c, B:63:0x00b6, B:67:0x00c0, B:69:0x00e0, B:75:0x026e, B:77:0x0274, B:81:0x027f, B:82:0x0283, B:84:0x0289, B:91:0x00ea, B:95:0x00f4, B:97:0x0110, B:101:0x01be, B:104:0x011a, B:108:0x01a3, B:112:0x0124, B:116:0x017e, B:120:0x012e, B:124:0x0138, B:128:0x0204, B:132:0x0142, B:136:0x014c, B:142:0x0345, B:144:0x0156, B:148:0x01d5, B:152:0x0160, B:156:0x016a, B:160:0x01f0, B:162:0x0174, B:166:0x018f, B:170:0x0199, B:174:0x01b4, B:178:0x01cb, B:182:0x01e6, B:186:0x01fa, B:190:0x0215, B:194:0x0261, B:198:0x02ad, B:202:0x02b7, B:206:0x02d5, B:210:0x02df, B:212:0x02ed, B:218:0x0330, B:220:0x02f7, B:224:0x0301, B:226:0x0312, B:230:0x031b, B:232:0x0324, B:236:0x0339, B:240:0x034e, B:244:0x0357, B:248:0x0058), top: B:5:0x000a }] */
    @JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final boolean stringComparison(String variable, JSONObject values, Bundle data) {
        Object obj;
        if (CrashShieldHandler.isObjectCrashing(MACARuleMatchingManager.class)) {
            return false;
        }
        try {
            Intrinsics.checkNotNullParameter(variable, "variable");
            Intrinsics.checkNotNullParameter(values, "values");
            String key = getKey(values);
            if (key == null) {
                return false;
            }
            String obj2 = values.get(key).toString();
            ArrayList<String> stringArrayList = getStringArrayList(values.optJSONArray(key));
            if (Intrinsics.areEqual(key, "exists")) {
                return data != null && data.containsKey(variable) == Boolean.parseBoolean(obj2);
            }
            if (data != null) {
                String lowerCase = variable.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                obj = data.get(lowerCase);
            }
            obj = data != null ? data.get(variable) : null;
            if (obj == null) {
                return false;
            }
            switch (key.hashCode()) {
                case -1729128927:
                    if (!key.equals("i_not_contains")) {
                        return false;
                    }
                    String lowerCase2 = obj.toString().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    String lowerCase3 = obj2.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    if (StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) lowerCase3, false, 2, (Object) null)) {
                        return false;
                    }
                case -1179774633:
                    if (!key.equals("is_any")) {
                        return false;
                    }
                    if (stringArrayList != null) {
                        return false;
                    }
                    return stringArrayList.contains(obj.toString());
                case -1039699439:
                    if (!key.equals("not_in")) {
                        return false;
                    }
                    if (stringArrayList != null) {
                        return false;
                    }
                    return stringArrayList.contains(obj.toString());
                case -969266188:
                    if (key.equals("starts_with")) {
                        return StringsKt.startsWith$default(obj.toString(), obj2, false, 2, (Object) null);
                    }
                    return false;
                case -966353971:
                    if (key.equals("regex_match")) {
                        return new Regex(obj2).matches(obj.toString());
                    }
                    return false;
                case -665609109:
                    if (!key.equals("is_not_any")) {
                        return false;
                    }
                    if (stringArrayList != null) {
                    }
                    break;
                case -567445985:
                    if (key.equals("contains")) {
                        return StringsKt.contains$default((CharSequence) obj.toString(), (CharSequence) obj2, false, 2, (Object) null);
                    }
                    return false;
                case -327990090:
                    if (!key.equals("i_str_neq")) {
                        return false;
                    }
                    String lowerCase4 = obj.toString().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    String lowerCase5 = obj2.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase5, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    return !Intrinsics.areEqual(lowerCase4, lowerCase5);
                case -159812115:
                    if (!key.equals("i_is_any")) {
                        return false;
                    }
                    if (stringArrayList != null) {
                        return false;
                    }
                    ArrayList<String> arrayList = stringArrayList;
                    if ((arrayList instanceof Collection) && arrayList.isEmpty()) {
                        return false;
                    }
                    Iterator<T> it = arrayList.iterator();
                    while (it.hasNext()) {
                        String lowerCase6 = ((String) it.next()).toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase6, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        String lowerCase7 = obj.toString().toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase7, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        if (Intrinsics.areEqual(lowerCase6, lowerCase7)) {
                        }
                    }
                    return false;
                case -92753547:
                    if (!key.equals("i_str_not_in")) {
                        return false;
                    }
                    if (stringArrayList != null) {
                        return false;
                    }
                    ArrayList<String> arrayList2 = stringArrayList;
                    if (!(arrayList2 instanceof Collection) || !arrayList2.isEmpty()) {
                        Iterator<T> it2 = arrayList2.iterator();
                        while (it2.hasNext()) {
                            Intrinsics.checkNotNullExpressionValue(((String) it2.next()).toLowerCase(Locale.ROOT), "this as java.lang.String).toLowerCase(Locale.ROOT)");
                            Intrinsics.checkNotNullExpressionValue(obj.toString().toLowerCase(Locale.ROOT), "this as java.lang.String).toLowerCase(Locale.ROOT)");
                            if (!(!Intrinsics.areEqual(r9, r10))) {
                                return false;
                            }
                        }
                    }
                case 60:
                    if (!key.equals("<")) {
                        return false;
                    }
                    if (Double.parseDouble(obj.toString()) < Double.parseDouble(obj2)) {
                        return false;
                    }
                case 61:
                    if (!key.equals("=")) {
                        return false;
                    }
                    return Intrinsics.areEqual(obj.toString(), obj2);
                case 62:
                    if (!key.equals(">")) {
                        return false;
                    }
                    if (Double.parseDouble(obj.toString()) > Double.parseDouble(obj2)) {
                        return false;
                    }
                case 1084:
                    if (!key.equals("!=")) {
                        return false;
                    }
                    if (Intrinsics.areEqual(obj.toString(), obj2)) {
                        return false;
                    }
                case 1921:
                    if (!key.equals("<=")) {
                        return false;
                    }
                    if (Double.parseDouble(obj.toString()) > Double.parseDouble(obj2)) {
                        return false;
                    }
                case 1952:
                    if (!key.equals("==")) {
                        return false;
                    }
                    return Intrinsics.areEqual(obj.toString(), obj2);
                case 1983:
                    if (!key.equals(">=")) {
                        return false;
                    }
                    if (Double.parseDouble(obj.toString()) < Double.parseDouble(obj2)) {
                        return false;
                    }
                case 3244:
                    if (!key.equals("eq")) {
                        return false;
                    }
                    return Intrinsics.areEqual(obj.toString(), obj2);
                case 3294:
                    if (!key.equals(UserDataStore.GENDER)) {
                        return false;
                    }
                    if (Double.parseDouble(obj.toString()) < Double.parseDouble(obj2)) {
                    }
                    break;
                case 3309:
                    if (!key.equals("gt")) {
                        return false;
                    }
                    if (Double.parseDouble(obj.toString()) > Double.parseDouble(obj2)) {
                    }
                    break;
                case 3365:
                    if (!key.equals("in")) {
                        return false;
                    }
                    if (stringArrayList != null) {
                    }
                    break;
                case 3449:
                    if (!key.equals("le")) {
                        return false;
                    }
                    if (Double.parseDouble(obj.toString()) > Double.parseDouble(obj2)) {
                    }
                    break;
                case 3464:
                    if (!key.equals("lt")) {
                        return false;
                    }
                    if (Double.parseDouble(obj.toString()) < Double.parseDouble(obj2)) {
                    }
                    break;
                case 3511:
                    if (!key.equals("ne")) {
                        return false;
                    }
                    if (Intrinsics.areEqual(obj.toString(), obj2)) {
                    }
                case 102680:
                    if (!key.equals("gte")) {
                        return false;
                    }
                    if (Double.parseDouble(obj.toString()) < Double.parseDouble(obj2)) {
                    }
                    break;
                case 107485:
                    if (!key.equals("lte")) {
                        return false;
                    }
                    if (Double.parseDouble(obj.toString()) > Double.parseDouble(obj2)) {
                    }
                    break;
                case 108954:
                    if (!key.equals("neq")) {
                        return false;
                    }
                    if (Intrinsics.areEqual(obj.toString(), obj2)) {
                    }
                case 127966736:
                    if (!key.equals("i_str_eq")) {
                        return false;
                    }
                    String lowerCase8 = obj.toString().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase8, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    String lowerCase9 = obj2.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase9, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    return Intrinsics.areEqual(lowerCase8, lowerCase9);
                case 127966857:
                    if (!key.equals("i_str_in")) {
                        return false;
                    }
                    if (stringArrayList != null) {
                    }
                    break;
                case 363990325:
                    if (!key.equals("i_contains")) {
                        return false;
                    }
                    String lowerCase10 = obj.toString().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase10, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    String lowerCase11 = obj2.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase11, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    return StringsKt.contains$default((CharSequence) lowerCase10, (CharSequence) lowerCase11, false, 2, (Object) null);
                case 1091487233:
                    if (!key.equals("i_is_not_any")) {
                        return false;
                    }
                    if (stringArrayList != null) {
                    }
                    break;
                case 1918401035:
                    if (!key.equals("not_contains") || StringsKt.contains$default((CharSequence) obj.toString(), (CharSequence) obj2, false, 2, (Object) null)) {
                        return false;
                    }
                case 1961112862:
                    if (!key.equals("i_starts_with")) {
                        return false;
                    }
                    String lowerCase12 = obj.toString().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase12, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    String lowerCase13 = obj2.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase13, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    return StringsKt.startsWith$default(lowerCase12, lowerCase13, false, 2, (Object) null);
                default:
                    return false;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, MACARuleMatchingManager.class);
            return false;
        }
    }

    @JvmStatic
    public static final ArrayList<String> getStringArrayList(JSONArray jsonArray) {
        if (CrashShieldHandler.isObjectCrashing(MACARuleMatchingManager.class) || jsonArray == null) {
            return null;
        }
        try {
            ArrayList<String> arrayList = new ArrayList<>();
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                arrayList.add(jsonArray.get(i).toString());
            }
            return arrayList;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, MACARuleMatchingManager.class);
            return null;
        }
    }

    @JvmStatic
    public static final boolean isMatchCCRule(String ruleString, Bundle data) {
        if (!CrashShieldHandler.isObjectCrashing(MACARuleMatchingManager.class) && ruleString != null && data != null) {
            try {
                JSONObject jSONObject = new JSONObject(ruleString);
                String key = getKey(jSONObject);
                if (key == null) {
                    return false;
                }
                Object obj = jSONObject.get(key);
                int hashCode = key.hashCode();
                if (hashCode != 3555) {
                    if (hashCode != 96727) {
                        if (hashCode == 109267 && key.equals("not")) {
                            return !isMatchCCRule(obj.toString(), data);
                        }
                    } else if (key.equals("and")) {
                        JSONArray jSONArray = (JSONArray) obj;
                        if (jSONArray == null) {
                            return false;
                        }
                        int length = jSONArray.length();
                        for (int i = 0; i < length; i++) {
                            if (!isMatchCCRule(jSONArray.get(i).toString(), data)) {
                                return false;
                            }
                        }
                        return true;
                    }
                } else if (key.equals("or")) {
                    JSONArray jSONArray2 = (JSONArray) obj;
                    if (jSONArray2 == null) {
                        return false;
                    }
                    int length2 = jSONArray2.length();
                    for (int i2 = 0; i2 < length2; i2++) {
                        if (isMatchCCRule(jSONArray2.get(i2).toString(), data)) {
                            return true;
                        }
                    }
                    return false;
                }
                JSONObject jSONObject2 = (JSONObject) obj;
                if (jSONObject2 == null) {
                    return false;
                }
                return stringComparison(key, jSONObject2, data);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, MACARuleMatchingManager.class);
            }
        }
        return false;
    }

    @JvmStatic
    public static final String getMatchPropertyIDs(Bundle params) {
        String optString;
        if (CrashShieldHandler.isObjectCrashing(MACARuleMatchingManager.class)) {
            return null;
        }
        try {
            JSONArray jSONArray = MACARules;
            if (jSONArray == null) {
                return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
            }
            if (jSONArray != null && jSONArray.length() == 0) {
                return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
            }
            JSONArray jSONArray2 = MACARules;
            Intrinsics.checkNotNull(jSONArray2, "null cannot be cast to non-null type org.json.JSONArray");
            ArrayList arrayList = new ArrayList();
            int length = jSONArray2.length();
            for (int i = 0; i < length; i++) {
                String optString2 = jSONArray2.optString(i);
                if (optString2 != null) {
                    JSONObject jSONObject = new JSONObject(optString2);
                    long optLong = jSONObject.optLong("id");
                    if (optLong != 0 && (optString = jSONObject.optString(WorkflowModule.Properties.Section.Component.Validation.Type.RULE)) != null && isMatchCCRule(optString, params)) {
                        arrayList.add(Long.valueOf(optLong));
                    }
                }
            }
            String jSONArray3 = new JSONArray((Collection) arrayList).toString();
            Intrinsics.checkNotNullExpressionValue(jSONArray3, "JSONArray(res).toString()");
            return jSONArray3;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, MACARuleMatchingManager.class);
            return null;
        }
    }

    @JvmStatic
    public static final void processParameters(Bundle params, String event) {
        if (CrashShieldHandler.isObjectCrashing(MACARuleMatchingManager.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(event, "event");
            if (enabled && params != null) {
                try {
                    generateInfo(params, event);
                    params.putString("_audiencePropertyIds", getMatchPropertyIDs(params));
                    params.putString("cs_maca", "1");
                    removeGeneratedInfo(params);
                } catch (Exception unused) {
                }
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, MACARuleMatchingManager.class);
        }
    }

    @JvmStatic
    public static final void generateInfo(Bundle params, String event) {
        if (CrashShieldHandler.isObjectCrashing(MACARuleMatchingManager.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(params, "params");
            Intrinsics.checkNotNullParameter(event, "event");
            params.putString(NotificationCompat.CATEGORY_EVENT, event);
            StringBuilder sb = new StringBuilder();
            Locale locale = Utility.INSTANCE.getLocale();
            String language = locale != null ? locale.getLanguage() : null;
            String str = "";
            if (language == null) {
                language = "";
            }
            sb.append(language);
            sb.append('_');
            Locale locale2 = Utility.INSTANCE.getLocale();
            String country = locale2 != null ? locale2.getCountry() : null;
            if (country == null) {
                country = "";
            }
            sb.append(country);
            params.putString("_locale", sb.toString());
            String versionName = Utility.INSTANCE.getVersionName();
            if (versionName == null) {
                versionName = "";
            }
            params.putString("_appVersion", versionName);
            params.putString("_deviceOS", "ANDROID");
            params.putString("_platform", Properties.SDK_VERSION_MOBILE_KEY);
            String str2 = Build.MODEL;
            if (str2 == null) {
                str2 = "";
            }
            params.putString("_deviceModel", str2);
            params.putString("_nativeAppID", FacebookSdk.getApplicationId());
            String versionName2 = Utility.INSTANCE.getVersionName();
            if (versionName2 != null) {
                str = versionName2;
            }
            params.putString("_nativeAppShortVersion", str);
            params.putString("_timezone", Utility.INSTANCE.getDeviceTimeZoneName());
            params.putString("_carrier", Utility.INSTANCE.getCarrierName());
            params.putString("_deviceOSTypeName", "ANDROID");
            params.putString("_deviceOSVersion", Build.VERSION.RELEASE);
            params.putLong("_remainingDiskGB", Utility.INSTANCE.getAvailableExternalStorageGB());
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, MACARuleMatchingManager.class);
        }
    }

    @JvmStatic
    public static final void removeGeneratedInfo(Bundle params) {
        if (CrashShieldHandler.isObjectCrashing(MACARuleMatchingManager.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(params, "params");
            for (String str : keys) {
                params.remove(str);
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, MACARuleMatchingManager.class);
        }
    }
}
