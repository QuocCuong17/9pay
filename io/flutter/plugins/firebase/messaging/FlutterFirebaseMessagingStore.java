package io.flutter.plugins.firebase.messaging;

import android.content.SharedPreferences;
import com.google.firebase.messaging.RemoteMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class FlutterFirebaseMessagingStore {
    private static final String KEY_NOTIFICATION_IDS = "notification_ids";
    private static final int MAX_SIZE_NOTIFICATIONS = 20;
    private static final String PREFERENCES_FILE = "io.flutter.plugins.firebase.messaging";
    private static FlutterFirebaseMessagingStore instance;
    private final String DELIMITER = ",";
    private SharedPreferences preferences;

    public static FlutterFirebaseMessagingStore getInstance() {
        if (instance == null) {
            instance = new FlutterFirebaseMessagingStore();
        }
        return instance;
    }

    private SharedPreferences getPreferences() {
        if (this.preferences == null) {
            this.preferences = ContextHolder.getApplicationContext().getSharedPreferences("io.flutter.plugins.firebase.messaging", 0);
        }
        return this.preferences;
    }

    public void setPreferencesStringValue(String str, String str2) {
        getPreferences().edit().putString(str, str2).apply();
    }

    public String getPreferencesStringValue(String str, String str2) {
        return getPreferences().getString(str, str2);
    }

    public void storeFirebaseMessage(RemoteMessage remoteMessage) {
        setPreferencesStringValue(remoteMessage.getMessageId(), new JSONObject(FlutterFirebaseMessagingUtils.remoteMessageToMap(remoteMessage)).toString());
        String str = getPreferencesStringValue(KEY_NOTIFICATION_IDS, "") + remoteMessage.getMessageId() + ",";
        ArrayList arrayList = new ArrayList(Arrays.asList(str.split(",")));
        if (arrayList.size() > 20) {
            String str2 = (String) arrayList.get(0);
            getPreferences().edit().remove(str2).apply();
            str = str.replace(str2 + ",", "");
        }
        setPreferencesStringValue(KEY_NOTIFICATION_IDS, str);
    }

    public Map<String, Object> getFirebaseMessageMap(String str) {
        String preferencesStringValue = getPreferencesStringValue(str, null);
        if (preferencesStringValue != null) {
            try {
                HashMap hashMap = new HashMap(1);
                Map<String, Object> jsonObjectToMap = jsonObjectToMap(new JSONObject(preferencesStringValue));
                jsonObjectToMap.put("to", str);
                hashMap.put("message", jsonObjectToMap);
                return hashMap;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void removeFirebaseMessage(String str) {
        getPreferences().edit().remove(str).apply();
        String preferencesStringValue = getPreferencesStringValue(KEY_NOTIFICATION_IDS, "");
        if (preferencesStringValue.isEmpty()) {
            return;
        }
        setPreferencesStringValue(KEY_NOTIFICATION_IDS, preferencesStringValue.replace(str + ",", ""));
    }

    private Map<String, Object> jsonObjectToMap(JSONObject jSONObject) throws JSONException {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONArray) {
                obj = jsonArrayToList((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = jsonObjectToMap((JSONObject) obj);
            }
            hashMap.put(next, obj);
        }
        return hashMap;
    }

    public List<Object> jsonArrayToList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = jsonArrayToList((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = jsonObjectToMap((JSONObject) obj);
            }
            arrayList.add(obj);
        }
        return arrayList;
    }
}
