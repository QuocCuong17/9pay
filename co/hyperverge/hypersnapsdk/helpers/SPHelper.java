package co.hyperverge.hypersnapsdk.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.Size;
import co.hyperverge.hypersnapsdk.data.models.FeatureConfig;
import co.hyperverge.hypersnapsdk.listeners.SessionStatusCallback;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.IPAddress;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class SPHelper {
    private static final String TAG = "co.hyperverge.hypersnapsdk.helpers.SPHelper";
    private static SharedPreferences.Editor editor = null;
    private static SharedPreferences sp = null;
    private static final String spHyperSnap = "HyperSnapSP";

    private static SharedPreferences.Editor getEditor() {
        if (editor == null) {
            editor = getSP().edit();
        }
        return editor;
    }

    public static void init(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(spHyperSnap, 0);
            sp = sharedPreferences;
            editor = sharedPreferences.edit();
        }
    }

    private static SharedPreferences getSP() {
        return sp;
    }

    public static void setLastUsedResolution(int i, int i2) {
        HVLogUtils.d(TAG, "setLastUsedResolution() called with: width = [" + i + "], height = [" + i2 + "]");
        if (getSP() == null) {
            return;
        }
        getEditor().putInt(AppConstants.LAST_USED_WIDTH, i);
        getEditor().putInt(AppConstants.LAST_USED_HEIGHT, i2);
        getEditor().commit();
    }

    public static Size getLastUsedCameraResolution() {
        HVLogUtils.d(TAG, "getLastUsedCameraResolution() called");
        if (getSP() == null) {
            return null;
        }
        return new Size(getSP().contains(AppConstants.LAST_USED_WIDTH) ? getSP().getInt(AppConstants.LAST_USED_WIDTH, -1) : -1, getSP().contains(AppConstants.LAST_USED_HEIGHT) ? getSP().getInt(AppConstants.LAST_USED_HEIGHT, -1) : -1);
    }

    public static boolean shouldFetchFromS3() {
        HVLogUtils.d(TAG, "shouldFetchFromS3() called");
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences == null) {
            return true;
        }
        return ((int) ((System.currentTimeMillis() - getFeatureConfigLastModified()) / 1000)) > sharedPreferences.getInt(AppConstants.FEATURE_CONFIG_EXPIRY, 3600);
    }

    public static void setFeatureConfigLastModified(long j) {
        HVLogUtils.d(TAG, "setFeatureConfigLastModified() called with: lastModified = [" + j + "]");
        if (sp != null) {
            getEditor().putLong(AppConstants.FEATURE_CONFIG_LAST_MODIFIED, j);
            getEditor().commit();
        }
    }

    public static long getFeatureConfigLastModified() {
        HVLogUtils.d(TAG, "getFeatureConfigLastModified() called");
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences != null) {
            return sharedPreferences.getLong(AppConstants.FEATURE_CONFIG_LAST_MODIFIED, 0L);
        }
        return 0L;
    }

    public static Map<String, FeatureConfig> getFeatureConfigMap() {
        HVLogUtils.d(TAG, "getFeatureConfigMap() called");
        try {
            SharedPreferences sharedPreferences = sp;
            if (sharedPreferences != null) {
                return (Map) new Gson().fromJson(sharedPreferences.getString(AppConstants.FEATURE_CONFIG, ""), new TypeToken<HashMap<String, Boolean>>() { // from class: co.hyperverge.hypersnapsdk.helpers.SPHelper.1
                }.getType());
            }
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
        }
        return HVFeatureConfigHelper.getDefaultFeatureMap();
    }

    @Deprecated
    public static boolean generateRandomTransactionId(SessionStatusCallback sessionStatusCallback) {
        HVLogUtils.d(TAG, "generateRandomTransactionId() called with: callback = [" + sessionStatusCallback + "]");
        return setTransactionID(String.valueOf(System.currentTimeMillis()) + UUID.randomUUID().toString().substring(1, 5), sessionStatusCallback);
    }

    public static HVError generateRandomTransactionId() {
        HVLogUtils.d(TAG, "generateRandomTransactionId() called");
        return setTransactionID(String.valueOf(System.currentTimeMillis()) + UUID.randomUUID().toString().substring(1, 5));
    }

    @Deprecated
    public static boolean setTransactionID(String str, SessionStatusCallback sessionStatusCallback) {
        HVLogUtils.d(TAG, "setTransactionID() called with: transactionID = [" + str + "], callback = [" + sessionStatusCallback + "]");
        if (getSP() == null) {
            return false;
        }
        if (getSP().contains("transactionId")) {
            if (sessionStatusCallback != null) {
                sessionStatusCallback.onFailure(getSessionError());
            }
            return false;
        }
        if (Objects.equals(str, "transactionId")) {
            if (sessionStatusCallback != null) {
                sessionStatusCallback.onFailure(getInvalidTransactionIdError());
            }
            return false;
        }
        getEditor().putString("transactionId", str);
        getEditor().commit();
        return true;
    }

    public static HVError setTransactionID(String str) {
        HVLogUtils.d(TAG, "setTransactionID() called with: transactionID = [" + str + "]");
        if (getSP().contains("transactionId")) {
            return getSessionError();
        }
        if (Objects.equals(str, "transactionId")) {
            return getInvalidTransactionIdError();
        }
        getEditor().putString("transactionId", str);
        getEditor().commit();
        return null;
    }

    public static String getTransactionID() {
        HVLogUtils.d(TAG, "getTransactionID() called");
        return getSP() != null ? getSP().getString("transactionId", "") : "";
    }

    public static void closeTransactionId() {
        HVLogUtils.d(TAG, "closeTransactionId() called");
        getEditor().remove(getTransactionID());
        getEditor().remove("transactionId");
        getEditor().clear();
        getEditor().commit();
    }

    public static String setMapperForUrl(String str, String str2) {
        HVLogUtils.d(TAG, "setMapperForUrl() called with: url = [" + str + "], suffix = [" + str2 + "]");
        if (getTransactionID().trim().isEmpty()) {
            return null;
        }
        HashMap<String, Integer> hashMapFromSharedPref = getHashMapFromSharedPref();
        getMapper(hashMapFromSharedPref, getKeyName(str, str2));
        String json = new Gson().toJson(hashMapFromSharedPref);
        if (sp != null) {
            getEditor().putString(getTransactionID(), json);
            getEditor().commit();
        }
        return json;
    }

    public static HashMap<String, Integer> getMapper(HashMap<String, Integer> hashMap, String str) {
        HVLogUtils.d(TAG, "getMapper() called with: hashMap = [" + hashMap + "], keyName = [" + str + "]");
        if (hashMap.containsKey(str)) {
            hashMap.put(str, Integer.valueOf(hashMap.get(str).intValue() + 1));
        } else {
            hashMap.put(str, 1);
        }
        if (hashMap.containsKey(AppConstants.TOTAL_ATTEMPTS)) {
            hashMap.put(AppConstants.TOTAL_ATTEMPTS, Integer.valueOf(hashMap.get(AppConstants.TOTAL_ATTEMPTS).intValue() + 1));
        } else {
            hashMap.put(AppConstants.TOTAL_ATTEMPTS, 1);
        }
        return hashMap;
    }

    public static HashMap<String, Integer> getHashMapFromSharedPref() {
        HVLogUtils.d(TAG, "getHashMapFromSharedPref() called");
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = sp;
        HashMap<String, Integer> hashMap = (HashMap) gson.fromJson(sharedPreferences != null ? sharedPreferences.getString(getTransactionID(), "") : "", new TypeToken<HashMap<String, Integer>>() { // from class: co.hyperverge.hypersnapsdk.helpers.SPHelper.2
        }.getType());
        return hashMap == null ? new HashMap<>() : hashMap;
    }

    public static JSONObject getRetryCountForKey(String str, String str2) {
        HVLogUtils.d(TAG, "getRetryCountForKey() called with: url = [" + str + "], suffix = [" + str2 + "]");
        HashMap<String, Integer> hashMapFromSharedPref = getHashMapFromSharedPref();
        JSONObject jSONObject = new JSONObject();
        String keyName = getKeyName(str, str2);
        int intValue = hashMapFromSharedPref.containsKey(AppConstants.TOTAL_ATTEMPTS) ? hashMapFromSharedPref.get(AppConstants.TOTAL_ATTEMPTS).intValue() + 1 : 1;
        try {
            jSONObject.put(AppConstants.ATTEMPTS_KEY, hashMapFromSharedPref.containsKey(keyName) ? 1 + hashMapFromSharedPref.get(keyName).intValue() : 1);
            jSONObject.put(AppConstants.TOTAL_ATTEMPTS, intValue);
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
        }
        return jSONObject;
    }

    public static String getKeyName(String str, String str2) {
        HVLogUtils.d(TAG, "getKeyName() called with: url = [" + str + "], suffix = [" + str2 + "]");
        String splitURLIntoKey = splitURLIntoKey(str);
        if (str2 == null || str2.trim().isEmpty()) {
            return splitURLIntoKey;
        }
        return splitURLIntoKey + "_" + str2;
    }

    public static String splitURLIntoKey(String str) {
        HVLogUtils.d(TAG, "splitURLIntoKey() called with: url = [" + str + "]");
        return str.split(RemoteSettings.FORWARD_SLASH_STRING)[r3.length - 1];
    }

    public static HVError getSessionError() {
        return new HVError(16, "An active session already exists. Please call endUserSession before starting a new session");
    }

    public static HVError getInvalidTransactionIdError() {
        return new HVError(6, String.format("Cannot set \"%s\" as the transaction ID value", "transactionId"));
    }

    public static int getAttemptsCountForImage(String str, String str2) {
        HVLogUtils.d(TAG, "getAttemptsCountForImage() called with: url = [" + str + "], suffix = [" + str2 + "]");
        try {
            String keyName = getKeyName(str, str2);
            HashMap<String, Integer> hashMapFromSharedPref = getHashMapFromSharedPref();
            if (hashMapFromSharedPref == null || !hashMapFromSharedPref.containsKey(keyName)) {
                return 0;
            }
            return hashMapFromSharedPref.get(keyName).intValue();
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            return 0;
        }
    }

    public static int getAttemptsCountForFaceNotPresent() {
        HVLogUtils.d(TAG, "getAttemptsCountForFaceNotPresent() called");
        try {
            return sp.getInt(AppConstants.spFaceNotPresentKey, 0);
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            return -1;
        }
    }

    public static void setAttemptsCountForFaceNotPresent(int i) {
        HVLogUtils.d(TAG, "setAttemptsCountForFaceNotPresent() called with: attemptsCount = [" + i + "]");
        try {
            getEditor().putInt(AppConstants.spFaceNotPresentKey, i).apply();
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
        }
    }

    public static void clearAttemptsCountForFaceNotPresent() {
        HVLogUtils.d(TAG, "clearAttemptsCountForFaceNotPresent() called");
        try {
            getEditor().remove(AppConstants.spFaceNotPresentKey).apply();
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
        }
    }

    public static int getAttemptsCountForImage(String str, String str2, String str3) {
        try {
            String keyName = getKeyName(str, str2);
            HashMap<String, Integer> hashMapFromSharedPref = getHashMapFromSharedPref();
            if (hashMapFromSharedPref == null || !hashMapFromSharedPref.containsKey(keyName)) {
                return 0;
            }
            return hashMapFromSharedPref.get(keyName).intValue();
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            return 0;
        }
    }

    public static void saveIpToGeoData(IPAddress iPAddress) {
        HVLogUtils.d(TAG, "saveIpToGeoData() called with: ipAddress = [" + iPAddress + "]");
        sp.edit().putString("ipAddressData", new Gson().toJson(iPAddress)).apply();
    }

    public static IPAddress getIpToGeoData() {
        HVLogUtils.d(TAG, "getIpToGeoData() called");
        return (IPAddress) new Gson().fromJson(sp.getString("ipAddressData", new JSONObject().toString()), IPAddress.class);
    }

    public static void saveUserRandomNumber(int i) {
        HVLogUtils.d(TAG, "saveUserRandomNumber() called with: number = [" + i + "]");
        getEditor().putInt(AppConstants.USER_RANDOM_NUMBER, i).apply();
    }

    public static int getUserRandomNumber() {
        HVLogUtils.d(TAG, "getUserRandomNumber() called");
        return getSP().getInt(AppConstants.USER_RANDOM_NUMBER, 1000);
    }
}
