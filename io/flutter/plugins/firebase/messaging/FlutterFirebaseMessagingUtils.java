package io.flutter.plugins.firebase.messaging;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class FlutterFirebaseMessagingUtils {
    static final String ACTION_REMOTE_MESSAGE = "io.flutter.plugins.firebase.messaging.NOTIFICATION";
    static final String ACTION_TOKEN = "io.flutter.plugins.firebase.messaging.TOKEN";
    static final String EXTRA_REMOTE_MESSAGE = "notification";
    static final String EXTRA_TOKEN = "token";
    static final String IS_AUTO_INIT_ENABLED = "isAutoInitEnabled";
    static final int JOB_ID = 2020;
    private static final String KEY_COLLAPSE_KEY = "collapseKey";
    private static final String KEY_DATA = "data";
    private static final String KEY_FROM = "from";
    private static final String KEY_MESSAGE_ID = "messageId";
    private static final String KEY_MESSAGE_TYPE = "messageType";
    private static final String KEY_SENT_TIME = "sentTime";
    private static final String KEY_TO = "to";
    private static final String KEY_TTL = "ttl";
    static final String SHARED_PREFERENCES_KEY = "io.flutter.firebase.messaging.callback";

    FlutterFirebaseMessagingUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> remoteMessageToMap(RemoteMessage remoteMessage) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        if (remoteMessage.getCollapseKey() != null) {
            hashMap.put(KEY_COLLAPSE_KEY, remoteMessage.getCollapseKey());
        }
        if (remoteMessage.getFrom() != null) {
            hashMap.put("from", remoteMessage.getFrom());
        }
        if (remoteMessage.getTo() != null) {
            hashMap.put("to", remoteMessage.getTo());
        }
        if (remoteMessage.getMessageId() != null) {
            hashMap.put(KEY_MESSAGE_ID, remoteMessage.getMessageId());
        }
        if (remoteMessage.getMessageType() != null) {
            hashMap.put(KEY_MESSAGE_TYPE, remoteMessage.getMessageType());
        }
        if (remoteMessage.getData().size() > 0) {
            for (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) {
                hashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        hashMap.put("data", hashMap2);
        hashMap.put(KEY_TTL, Integer.valueOf(remoteMessage.getTtl()));
        hashMap.put(KEY_SENT_TIME, Long.valueOf(remoteMessage.getSentTime()));
        if (remoteMessage.getNotification() != null) {
            hashMap.put(EXTRA_REMOTE_MESSAGE, remoteMessageNotificationToMap(remoteMessage.getNotification()));
        }
        return hashMap;
    }

    private static Map<String, Object> remoteMessageNotificationToMap(RemoteMessage.Notification notification) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        if (notification.getTitle() != null) {
            hashMap.put("title", notification.getTitle());
        }
        if (notification.getTitleLocalizationKey() != null) {
            hashMap.put("titleLocKey", notification.getTitleLocalizationKey());
        }
        if (notification.getTitleLocalizationArgs() != null) {
            hashMap.put("titleLocArgs", Arrays.asList(notification.getTitleLocalizationArgs()));
        }
        if (notification.getBody() != null) {
            hashMap.put("body", notification.getBody());
        }
        if (notification.getBodyLocalizationKey() != null) {
            hashMap.put("bodyLocKey", notification.getBodyLocalizationKey());
        }
        if (notification.getBodyLocalizationArgs() != null) {
            hashMap.put("bodyLocArgs", Arrays.asList(notification.getBodyLocalizationArgs()));
        }
        if (notification.getChannelId() != null) {
            hashMap2.put("channelId", notification.getChannelId());
        }
        if (notification.getClickAction() != null) {
            hashMap2.put("clickAction", notification.getClickAction());
        }
        if (notification.getColor() != null) {
            hashMap2.put("color", notification.getColor());
        }
        if (notification.getIcon() != null) {
            hashMap2.put("smallIcon", notification.getIcon());
        }
        if (notification.getImageUrl() != null) {
            hashMap2.put("imageUrl", notification.getImageUrl().toString());
        }
        if (notification.getLink() != null) {
            hashMap2.put("link", notification.getLink().toString());
        }
        if (notification.getNotificationCount() != null) {
            hashMap2.put("count", notification.getNotificationCount());
        }
        if (notification.getNotificationPriority() != null) {
            hashMap2.put("priority", notification.getNotificationPriority());
        }
        if (notification.getSound() != null) {
            hashMap2.put("sound", notification.getSound());
        }
        if (notification.getTicker() != null) {
            hashMap2.put("ticker", notification.getTicker());
        }
        if (notification.getVisibility() != null) {
            hashMap2.put("visibility", notification.getVisibility());
        }
        if (notification.getTag() != null) {
            hashMap2.put("tag", notification.getTag());
        }
        hashMap.put("android", hashMap2);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isApplicationForeground(Context context) {
        ActivityManager activityManager;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        if ((keyguardManager != null && keyguardManager.isKeyguardLocked()) || (activityManager = (ActivityManager) context.getSystemService("activity")) == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
            return false;
        }
        String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FirebaseMessaging getFirebaseMessagingForArguments(Map<String, Object> map) {
        return FirebaseMessaging.getInstance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RemoteMessage getRemoteMessageForArguments(Map<String, Object> map) {
        Object obj = map.get("message");
        Objects.requireNonNull(obj);
        Map map2 = (Map) obj;
        Object obj2 = map2.get("to");
        Objects.requireNonNull(obj2);
        RemoteMessage.Builder builder = new RemoteMessage.Builder((String) obj2);
        String str = (String) map2.get(KEY_COLLAPSE_KEY);
        String str2 = (String) map2.get(KEY_MESSAGE_ID);
        String str3 = (String) map2.get(KEY_MESSAGE_TYPE);
        Integer num = (Integer) map2.get(KEY_TTL);
        Map<String, String> map3 = (Map) map2.get("data");
        if (str != null) {
            builder.setCollapseKey(str);
        }
        if (str3 != null) {
            builder.setMessageType(str3);
        }
        if (str2 != null) {
            builder.setMessageId(str2);
        }
        if (num != null) {
            builder.setTtl(num.intValue());
        }
        if (map3 != null) {
            builder.setData(map3);
        }
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> getRemoteMessageNotificationForArguments(Map<String, Object> map) {
        Object obj = map.get("message");
        Objects.requireNonNull(obj);
        Map map2 = (Map) obj;
        if (map2.get(EXTRA_REMOTE_MESSAGE) == null) {
            return null;
        }
        return (Map) map2.get(EXTRA_REMOTE_MESSAGE);
    }
}
