package com.dexterous.flutterlocalnotifications;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.app.AlarmManagerCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.Person;
import androidx.core.app.RemoteInput;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.media.app.NotificationCompat;
import androidx.media3.common.C;
import com.dexterous.flutterlocalnotifications.isolate.IsolatePreferences;
import com.dexterous.flutterlocalnotifications.models.BitmapSource;
import com.dexterous.flutterlocalnotifications.models.DateTimeComponents;
import com.dexterous.flutterlocalnotifications.models.IconSource;
import com.dexterous.flutterlocalnotifications.models.MessageDetails;
import com.dexterous.flutterlocalnotifications.models.NotificationAction;
import com.dexterous.flutterlocalnotifications.models.NotificationChannelAction;
import com.dexterous.flutterlocalnotifications.models.NotificationChannelDetails;
import com.dexterous.flutterlocalnotifications.models.NotificationChannelGroupDetails;
import com.dexterous.flutterlocalnotifications.models.NotificationDetails;
import com.dexterous.flutterlocalnotifications.models.NotificationStyle;
import com.dexterous.flutterlocalnotifications.models.PersonDetails;
import com.dexterous.flutterlocalnotifications.models.RepeatInterval;
import com.dexterous.flutterlocalnotifications.models.ScheduleMode;
import com.dexterous.flutterlocalnotifications.models.ScheduledNotificationRepeatFrequency;
import com.dexterous.flutterlocalnotifications.models.SoundSource;
import com.dexterous.flutterlocalnotifications.models.styles.BigPictureStyleInformation;
import com.dexterous.flutterlocalnotifications.models.styles.BigTextStyleInformation;
import com.dexterous.flutterlocalnotifications.models.styles.DefaultStyleInformation;
import com.dexterous.flutterlocalnotifications.models.styles.InboxStyleInformation;
import com.dexterous.flutterlocalnotifications.models.styles.MessagingStyleInformation;
import com.dexterous.flutterlocalnotifications.models.styles.StyleInformation;
import com.dexterous.flutterlocalnotifications.utils.BooleanUtils;
import com.dexterous.flutterlocalnotifications.utils.LongUtils;
import com.dexterous.flutterlocalnotifications.utils.StringUtils;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.flutter.FlutterInjector;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class FlutterLocalNotificationsPlugin implements MethodChannel.MethodCallHandler, PluginRegistry.NewIntentListener, PluginRegistry.RequestPermissionsResultListener, PluginRegistry.ActivityResultListener, FlutterPlugin, ActivityAware {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String ACTION_ID = "actionId";
    private static final String ARE_NOTIFICATIONS_ENABLED_METHOD = "areNotificationsEnabled";
    private static final String CALLBACK_HANDLE = "callback_handle";
    private static final String CANCEL_ALL_METHOD = "cancelAll";
    private static final String CANCEL_ID = "id";
    private static final String CANCEL_METHOD = "cancel";
    static final String CANCEL_NOTIFICATION = "cancelNotification";
    private static final String CANCEL_TAG = "tag";
    private static final String CAN_SCHEDULE_EXACT_NOTIFICATIONS_METHOD = "canScheduleExactNotifications";
    private static final String CREATE_NOTIFICATION_CHANNEL_GROUP_METHOD = "createNotificationChannelGroup";
    private static final String CREATE_NOTIFICATION_CHANNEL_METHOD = "createNotificationChannel";
    private static final String DEFAULT_ICON = "defaultIcon";
    private static final String DELETE_NOTIFICATION_CHANNEL_GROUP_METHOD = "deleteNotificationChannelGroup";
    private static final String DELETE_NOTIFICATION_CHANNEL_METHOD = "deleteNotificationChannel";
    private static final String DISPATCHER_HANDLE = "dispatcher_handle";
    private static final String DRAWABLE = "drawable";
    private static final String EXACT_ALARMS_PERMISSION_ERROR_CODE = "exact_alarms_not_permitted";
    static final int EXACT_ALARM_PERMISSION_REQUEST_CODE = 2;
    static final int FULL_SCREEN_INTENT_PERMISSION_REQUEST_CODE = 3;
    private static final String GET_ACTIVE_NOTIFICATIONS_ERROR_MESSAGE = "Android version must be 6.0 or newer to use getActiveNotifications";
    private static final String GET_ACTIVE_NOTIFICATIONS_METHOD = "getActiveNotifications";
    private static final String GET_ACTIVE_NOTIFICATION_MESSAGING_STYLE_ERROR_CODE = "getActiveNotificationMessagingStyleError";
    private static final String GET_ACTIVE_NOTIFICATION_MESSAGING_STYLE_METHOD = "getActiveNotificationMessagingStyle";
    private static final String GET_CALLBACK_HANDLE_METHOD = "getCallbackHandle";
    private static final String GET_NOTIFICATION_APP_LAUNCH_DETAILS_METHOD = "getNotificationAppLaunchDetails";
    private static final String GET_NOTIFICATION_CHANNELS_ERROR_CODE = "getNotificationChannelsError";
    private static final String GET_NOTIFICATION_CHANNELS_METHOD = "getNotificationChannels";
    private static final String INITIALIZE_METHOD = "initialize";
    private static final String INPUT = "input";
    private static final String INPUT_RESULT = "FlutterLocalNotificationsPluginInputResult";
    private static final String INVALID_BIG_PICTURE_ERROR_CODE = "invalid_big_picture";
    private static final String INVALID_DRAWABLE_RESOURCE_ERROR_MESSAGE = "The resource %s could not be found. Please make sure it has been added as a drawable resource to your Android head project.";
    private static final String INVALID_ICON_ERROR_CODE = "invalid_icon";
    private static final String INVALID_LARGE_ICON_ERROR_CODE = "invalid_large_icon";
    private static final String INVALID_LED_DETAILS_ERROR_CODE = "invalid_led_details";
    private static final String INVALID_LED_DETAILS_ERROR_MESSAGE = "Must specify both ledOnMs and ledOffMs to configure the blink cycle on older versions of Android before Oreo";
    private static final String INVALID_RAW_RESOURCE_ERROR_MESSAGE = "The resource %s could not be found. Please make sure it has been added as a raw resource to your Android head project.";
    private static final String INVALID_SOUND_ERROR_CODE = "invalid_sound";
    private static final String METHOD_CHANNEL = "dexterous.com/flutter/local_notifications";
    static String NOTIFICATION_DETAILS = "notificationDetails";
    static final String NOTIFICATION_ID = "notificationId";
    private static final String NOTIFICATION_LAUNCHED_APP = "notificationLaunchedApp";
    static final int NOTIFICATION_PERMISSION_REQUEST_CODE = 1;
    private static final String NOTIFICATION_RESPONSE_TYPE = "notificationResponseType";
    static final String PAYLOAD = "payload";
    private static final String PENDING_NOTIFICATION_REQUESTS_METHOD = "pendingNotificationRequests";
    private static final String PERIODICALLY_SHOW_METHOD = "periodicallyShow";
    private static final String PERIODICALLY_SHOW_WITH_DURATION = "periodicallyShowWithDuration";
    private static final String PERMISSION_REQUEST_IN_PROGRESS_ERROR_CODE = "permissionRequestInProgress";
    private static final String PERMISSION_REQUEST_IN_PROGRESS_ERROR_MESSAGE = "Another permission request is already in progress";
    private static final String REQUEST_EXACT_ALARMS_PERMISSION_METHOD = "requestExactAlarmsPermission";
    private static final String REQUEST_FULL_SCREEN_INTENT_PERMISSION_METHOD = "requestFullScreenIntentPermission";
    private static final String REQUEST_NOTIFICATIONS_PERMISSION_METHOD = "requestNotificationsPermission";
    private static final String SCHEDULED_NOTIFICATIONS = "scheduled_notifications";
    private static final String SELECT_FOREGROUND_NOTIFICATION_ACTION = "SELECT_FOREGROUND_NOTIFICATION";
    private static final String SELECT_NOTIFICATION = "SELECT_NOTIFICATION";
    private static final String SHARED_PREFERENCES_KEY = "notification_plugin_cache";
    private static final String SHOW_METHOD = "show";
    private static final String START_FOREGROUND_SERVICE = "startForegroundService";
    private static final String STOP_FOREGROUND_SERVICE = "stopForegroundService";
    private static final String TAG = "FLTLocalNotifPlugin";
    private static final String UNSUPPORTED_OS_VERSION_ERROR_CODE = "unsupported_os_version";
    private static final String ZONED_SCHEDULE_METHOD = "zonedSchedule";
    static Gson gson;
    private Context applicationContext;
    private PermissionRequestListener callback;
    private MethodChannel channel;
    private Activity mainActivity;
    private PermissionRequestProgress permissionRequestProgress = PermissionRequestProgress.None;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public enum PermissionRequestProgress {
        None,
        RequestingNotificationPermission,
        RequestingExactAlarmsPermission,
        RequestingFullScreenIntentPermission
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void rescheduleNotifications(Context context) {
        Iterator<NotificationDetails> it = loadScheduledNotifications(context).iterator();
        while (it.hasNext()) {
            NotificationDetails next = it.next();
            try {
            } catch (ExactAlarmPermissionException e) {
                Log.e(TAG, e.getMessage());
                removeNotificationFromCache(context, next.f63id);
            }
            if (next.repeatInterval == null && next.repeatIntervalMilliseconds == null) {
                if (next.timeZoneName != null) {
                    zonedScheduleNotification(context, next, false);
                } else {
                    scheduleNotification(context, next, false);
                }
            }
            repeatNotification(context, next, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void scheduleNextNotification(Context context, NotificationDetails notificationDetails) {
        try {
            if (notificationDetails.scheduledNotificationRepeatFrequency != null) {
                zonedScheduleNextNotification(context, notificationDetails);
            } else if (notificationDetails.matchDateTimeComponents != null) {
                zonedScheduleNextNotificationMatchingDateComponents(context, notificationDetails);
            } else {
                if (notificationDetails.repeatInterval == null && notificationDetails.repeatIntervalMilliseconds == null) {
                    removeNotificationFromCache(context, notificationDetails.f63id);
                }
                scheduleNextRepeatingNotification(context, notificationDetails);
            }
        } catch (ExactAlarmPermissionException e) {
            Log.e(TAG, e.getMessage());
            removeNotificationFromCache(context, notificationDetails.f63id);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Notification createNotification(Context context, NotificationDetails notificationDetails) {
        CharSequence charSequence;
        CharSequence charSequence2;
        Intent intent;
        int i;
        int i2;
        PendingIntent broadcast;
        NotificationChannelDetails fromNotificationDetails = NotificationChannelDetails.fromNotificationDetails(notificationDetails);
        if (canCreateNotificationChannel(context, fromNotificationDetails).booleanValue()) {
            setupNotificationChannel(context, fromNotificationDetails);
        }
        Intent launchIntent = getLaunchIntent(context);
        launchIntent.setAction(SELECT_NOTIFICATION);
        launchIntent.putExtra(NOTIFICATION_ID, notificationDetails.f63id);
        launchIntent.putExtra("payload", notificationDetails.payload);
        int i3 = 23;
        PendingIntent activity = PendingIntent.getActivity(context, notificationDetails.f63id.intValue(), launchIntent, Build.VERSION.SDK_INT >= 23 ? 201326592 : C.BUFFER_FLAG_FIRST_SAMPLE);
        DefaultStyleInformation defaultStyleInformation = (DefaultStyleInformation) notificationDetails.styleInformation;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, notificationDetails.channelId);
        if (defaultStyleInformation.htmlFormatTitle.booleanValue()) {
            charSequence = fromHtml(notificationDetails.title);
        } else {
            charSequence = notificationDetails.title;
        }
        NotificationCompat.Builder contentTitle = builder.setContentTitle(charSequence);
        if (defaultStyleInformation.htmlFormatBody.booleanValue()) {
            charSequence2 = fromHtml(notificationDetails.body);
        } else {
            charSequence2 = notificationDetails.body;
        }
        NotificationCompat.Builder onlyAlertOnce = contentTitle.setContentText(charSequence2).setTicker(notificationDetails.ticker).setAutoCancel(BooleanUtils.getValue(notificationDetails.autoCancel)).setContentIntent(activity).setPriority(notificationDetails.priority.intValue()).setOngoing(BooleanUtils.getValue(notificationDetails.ongoing)).setSilent(BooleanUtils.getValue(notificationDetails.silent)).setOnlyAlertOnce(BooleanUtils.getValue(notificationDetails.onlyAlertOnce));
        if (notificationDetails.actions != null) {
            int intValue = notificationDetails.f63id.intValue() * 16;
            for (NotificationAction notificationAction : notificationDetails.actions) {
                IconCompat iconCompat = null;
                if (!TextUtils.isEmpty(notificationAction.icon) && notificationAction.iconSource != null) {
                    iconCompat = getIconFromSource(context, notificationAction.icon, notificationAction.iconSource);
                }
                if (notificationAction.showsUserInterface != null && notificationAction.showsUserInterface.booleanValue()) {
                    intent = getLaunchIntent(context);
                    intent.setAction(SELECT_FOREGROUND_NOTIFICATION_ACTION);
                } else {
                    intent = new Intent(context, (Class<?>) ActionBroadcastReceiver.class);
                    intent.setAction(ActionBroadcastReceiver.ACTION_TAPPED);
                }
                intent.putExtra(NOTIFICATION_ID, notificationDetails.f63id).putExtra(ACTION_ID, notificationAction.f60id).putExtra(CANCEL_NOTIFICATION, notificationAction.cancelNotification).putExtra("payload", notificationDetails.payload);
                if (notificationAction.actionInputs == null || notificationAction.actionInputs.isEmpty()) {
                    if (Build.VERSION.SDK_INT >= i3) {
                        i = 201326592;
                    }
                    i = C.BUFFER_FLAG_FIRST_SAMPLE;
                } else {
                    if (Build.VERSION.SDK_INT >= 31) {
                        i = 167772160;
                    }
                    i = C.BUFFER_FLAG_FIRST_SAMPLE;
                }
                if (notificationAction.showsUserInterface != null && notificationAction.showsUserInterface.booleanValue()) {
                    i2 = intValue + 1;
                    broadcast = PendingIntent.getActivity(context, intValue, intent, i);
                } else {
                    i2 = intValue + 1;
                    broadcast = PendingIntent.getBroadcast(context, intValue, intent, i);
                }
                intValue = i2;
                SpannableString spannableString = new SpannableString(notificationAction.title);
                if (notificationAction.titleColor != null) {
                    spannableString.setSpan(new ForegroundColorSpan(notificationAction.titleColor.intValue()), 0, spannableString.length(), 0);
                }
                NotificationCompat.Action.Builder builder2 = new NotificationCompat.Action.Builder(iconCompat, spannableString, broadcast);
                if (notificationAction.contextual != null) {
                    builder2.setContextual(notificationAction.contextual.booleanValue());
                }
                if (notificationAction.showsUserInterface != null) {
                    builder2.setShowsUserInterface(notificationAction.showsUserInterface.booleanValue());
                }
                if (notificationAction.allowGeneratedReplies != null) {
                    builder2.setAllowGeneratedReplies(notificationAction.allowGeneratedReplies.booleanValue());
                }
                if (notificationAction.actionInputs != null) {
                    for (NotificationAction.NotificationActionInput notificationActionInput : notificationAction.actionInputs) {
                        RemoteInput.Builder label = new RemoteInput.Builder(INPUT_RESULT).setLabel(notificationActionInput.label);
                        if (notificationActionInput.allowFreeFormInput != null) {
                            label.setAllowFreeFormInput(notificationActionInput.allowFreeFormInput.booleanValue());
                        }
                        if (notificationActionInput.allowedMimeTypes != null) {
                            Iterator<String> it = notificationActionInput.allowedMimeTypes.iterator();
                            while (it.hasNext()) {
                                label.setAllowDataType(it.next(), true);
                            }
                        }
                        if (notificationActionInput.choices != null) {
                            label.setChoices((CharSequence[]) notificationActionInput.choices.toArray(new CharSequence[0]));
                        }
                        builder2.addRemoteInput(label.build());
                    }
                }
                onlyAlertOnce.addAction(builder2.build());
                i3 = 23;
            }
        }
        setSmallIcon(context, notificationDetails, onlyAlertOnce);
        onlyAlertOnce.setLargeIcon(getBitmapFromSource(context, notificationDetails.largeIcon, notificationDetails.largeIconBitmapSource));
        if (notificationDetails.color != null) {
            onlyAlertOnce.setColor(notificationDetails.color.intValue());
        }
        if (notificationDetails.colorized != null) {
            onlyAlertOnce.setColorized(notificationDetails.colorized.booleanValue());
        }
        if (notificationDetails.showWhen != null) {
            onlyAlertOnce.setShowWhen(BooleanUtils.getValue(notificationDetails.showWhen));
        }
        if (notificationDetails.when != null) {
            onlyAlertOnce.setWhen(notificationDetails.when.longValue());
        }
        if (notificationDetails.usesChronometer != null) {
            onlyAlertOnce.setUsesChronometer(notificationDetails.usesChronometer.booleanValue());
        }
        if (notificationDetails.chronometerCountDown != null && Build.VERSION.SDK_INT >= 24) {
            onlyAlertOnce.setChronometerCountDown(notificationDetails.chronometerCountDown.booleanValue());
        }
        if (BooleanUtils.getValue(notificationDetails.fullScreenIntent)) {
            onlyAlertOnce.setFullScreenIntent(activity, true);
        }
        if (!StringUtils.isNullOrEmpty(notificationDetails.shortcutId).booleanValue()) {
            onlyAlertOnce.setShortcutId(notificationDetails.shortcutId);
        }
        if (!StringUtils.isNullOrEmpty(notificationDetails.subText).booleanValue()) {
            onlyAlertOnce.setSubText(notificationDetails.subText);
        }
        if (notificationDetails.number != null) {
            onlyAlertOnce.setNumber(notificationDetails.number.intValue());
        }
        setVisibility(notificationDetails, onlyAlertOnce);
        applyGrouping(notificationDetails, onlyAlertOnce);
        setSound(context, notificationDetails, onlyAlertOnce);
        setVibrationPattern(notificationDetails, onlyAlertOnce);
        setLights(notificationDetails, onlyAlertOnce);
        setStyle(context, notificationDetails, onlyAlertOnce);
        setProgress(notificationDetails, onlyAlertOnce);
        setCategory(notificationDetails, onlyAlertOnce);
        setTimeoutAfter(notificationDetails, onlyAlertOnce);
        Notification build = onlyAlertOnce.build();
        if (notificationDetails.additionalFlags != null && notificationDetails.additionalFlags.length > 0) {
            for (int i4 : notificationDetails.additionalFlags) {
                build.flags = i4 | build.flags;
            }
        }
        return build;
    }

    private static Boolean canCreateNotificationChannel(Context context, NotificationChannelDetails notificationChannelDetails) {
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = ((NotificationManager) context.getSystemService("notification")).getNotificationChannel(notificationChannelDetails.f61id);
            if ((notificationChannel == null && (notificationChannelDetails.channelAction == null || notificationChannelDetails.channelAction == NotificationChannelAction.CreateIfNotExists)) || (notificationChannel != null && notificationChannelDetails.channelAction == NotificationChannelAction.Update)) {
                z = true;
            }
            return Boolean.valueOf(z);
        }
        return false;
    }

    private static void setSmallIcon(Context context, NotificationDetails notificationDetails, NotificationCompat.Builder builder) {
        if (!StringUtils.isNullOrEmpty(notificationDetails.icon).booleanValue()) {
            builder.setSmallIcon(getDrawableResourceId(context, notificationDetails.icon));
            return;
        }
        String string = context.getSharedPreferences(SHARED_PREFERENCES_KEY, 0).getString(DEFAULT_ICON, null);
        if (StringUtils.isNullOrEmpty(string).booleanValue()) {
            builder.setSmallIcon(notificationDetails.iconResourceId.intValue());
        } else {
            builder.setSmallIcon(getDrawableResourceId(context, string));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Gson buildGson() {
        if (gson == null) {
            gson = new GsonBuilder().registerTypeAdapter(ScheduleMode.class, new ScheduleMode.Deserializer()).registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(StyleInformation.class).registerSubtype(DefaultStyleInformation.class).registerSubtype(BigTextStyleInformation.class).registerSubtype(BigPictureStyleInformation.class).registerSubtype(InboxStyleInformation.class).registerSubtype(MessagingStyleInformation.class)).create();
        }
        return gson;
    }

    private static ArrayList<NotificationDetails> loadScheduledNotifications(Context context) {
        ArrayList<NotificationDetails> arrayList = new ArrayList<>();
        String string = context.getSharedPreferences(SCHEDULED_NOTIFICATIONS, 0).getString(SCHEDULED_NOTIFICATIONS, null);
        return string != null ? (ArrayList) buildGson().fromJson(string, new TypeToken<ArrayList<NotificationDetails>>() { // from class: com.dexterous.flutterlocalnotifications.FlutterLocalNotificationsPlugin.1
        }.getType()) : arrayList;
    }

    private static void saveScheduledNotifications(Context context, ArrayList<NotificationDetails> arrayList) {
        context.getSharedPreferences(SCHEDULED_NOTIFICATIONS, 0).edit().putString(SCHEDULED_NOTIFICATIONS, buildGson().toJson(arrayList)).apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeNotificationFromCache(Context context, Integer num) {
        ArrayList<NotificationDetails> loadScheduledNotifications = loadScheduledNotifications(context);
        Iterator<NotificationDetails> it = loadScheduledNotifications.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().f63id.equals(num)) {
                it.remove();
                break;
            }
        }
        saveScheduledNotifications(context, loadScheduledNotifications);
    }

    private static Spanned fromHtml(String str) {
        if (str == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return Html.fromHtml(str, 0);
        }
        return Html.fromHtml(str);
    }

    private static void scheduleNotification(Context context, NotificationDetails notificationDetails, Boolean bool) {
        String json = buildGson().toJson(notificationDetails);
        Intent intent = new Intent(context, (Class<?>) ScheduledNotificationReceiver.class);
        intent.putExtra(NOTIFICATION_DETAILS, json);
        setupAlarm(notificationDetails, getAlarmManager(context), notificationDetails.millisecondsSinceEpoch.longValue(), getBroadcastPendingIntent(context, notificationDetails.f63id.intValue(), intent));
        if (bool.booleanValue()) {
            saveScheduledNotification(context, notificationDetails);
        }
    }

    private static void zonedScheduleNotification(Context context, NotificationDetails notificationDetails, Boolean bool) {
        String json = buildGson().toJson(notificationDetails);
        Intent intent = new Intent(context, (Class<?>) ScheduledNotificationReceiver.class);
        intent.putExtra(NOTIFICATION_DETAILS, json);
        setupAlarm(notificationDetails, getAlarmManager(context), ZonedDateTime.of(LocalDateTime.parse(notificationDetails.scheduledDateTime), ZoneId.of(notificationDetails.timeZoneName)).toInstant().toEpochMilli(), getBroadcastPendingIntent(context, notificationDetails.f63id.intValue(), intent));
        if (bool.booleanValue()) {
            saveScheduledNotification(context, notificationDetails);
        }
    }

    private static void scheduleNextRepeatingNotification(Context context, NotificationDetails notificationDetails) {
        long calculateNextNotificationTrigger = calculateNextNotificationTrigger(notificationDetails.calledAt.longValue(), calculateRepeatIntervalMilliseconds(notificationDetails));
        String json = buildGson().toJson(notificationDetails);
        Intent intent = new Intent(context, (Class<?>) ScheduledNotificationReceiver.class);
        intent.putExtra(NOTIFICATION_DETAILS, json);
        PendingIntent broadcastPendingIntent = getBroadcastPendingIntent(context, notificationDetails.f63id.intValue(), intent);
        AlarmManager alarmManager = getAlarmManager(context);
        if (notificationDetails.scheduleMode == null) {
            notificationDetails.scheduleMode = ScheduleMode.exactAllowWhileIdle;
        }
        setupAllowWhileIdleAlarm(notificationDetails, alarmManager, calculateNextNotificationTrigger, broadcastPendingIntent);
        saveScheduledNotification(context, notificationDetails);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> extractNotificationResponseMap(Intent intent) {
        int intExtra = intent.getIntExtra(NOTIFICATION_ID, 0);
        HashMap hashMap = new HashMap();
        hashMap.put(NOTIFICATION_ID, Integer.valueOf(intExtra));
        hashMap.put(ACTION_ID, intent.getStringExtra(ACTION_ID));
        hashMap.put("payload", intent.getStringExtra("payload"));
        Bundle resultsFromIntent = RemoteInput.getResultsFromIntent(intent);
        if (resultsFromIntent != null) {
            hashMap.put(INPUT, resultsFromIntent.getString(INPUT_RESULT));
        }
        if (SELECT_NOTIFICATION.equals(intent.getAction())) {
            hashMap.put(NOTIFICATION_RESPONSE_TYPE, 0);
        }
        if (SELECT_FOREGROUND_NOTIFICATION_ACTION.equals(intent.getAction())) {
            hashMap.put(NOTIFICATION_RESPONSE_TYPE, 1);
        }
        return hashMap;
    }

    private static PendingIntent getBroadcastPendingIntent(Context context, int i, Intent intent) {
        return PendingIntent.getBroadcast(context, i, intent, Build.VERSION.SDK_INT >= 23 ? 201326592 : C.BUFFER_FLAG_FIRST_SAMPLE);
    }

    private static void repeatNotification(Context context, NotificationDetails notificationDetails, Boolean bool) {
        long calculateRepeatIntervalMilliseconds = calculateRepeatIntervalMilliseconds(notificationDetails);
        long longValue = notificationDetails.calledAt.longValue();
        if (notificationDetails.repeatTime != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(11, notificationDetails.repeatTime.hour.intValue());
            calendar.set(12, notificationDetails.repeatTime.minute.intValue());
            calendar.set(13, notificationDetails.repeatTime.second.intValue());
            if (notificationDetails.day != null) {
                calendar.set(7, notificationDetails.day.intValue());
            }
            longValue = calendar.getTimeInMillis();
        }
        long calculateNextNotificationTrigger = calculateNextNotificationTrigger(longValue, calculateRepeatIntervalMilliseconds);
        String json = buildGson().toJson(notificationDetails);
        Intent intent = new Intent(context, (Class<?>) ScheduledNotificationReceiver.class);
        intent.putExtra(NOTIFICATION_DETAILS, json);
        PendingIntent broadcastPendingIntent = getBroadcastPendingIntent(context, notificationDetails.f63id.intValue(), intent);
        AlarmManager alarmManager = getAlarmManager(context);
        if (notificationDetails.scheduleMode == null) {
            notificationDetails.scheduleMode = ScheduleMode.inexact;
        }
        if (notificationDetails.scheduleMode.useAllowWhileIdle()) {
            setupAllowWhileIdleAlarm(notificationDetails, alarmManager, calculateNextNotificationTrigger, broadcastPendingIntent);
        } else {
            alarmManager.setInexactRepeating(0, calculateNextNotificationTrigger, calculateRepeatIntervalMilliseconds, broadcastPendingIntent);
        }
        if (bool.booleanValue()) {
            saveScheduledNotification(context, notificationDetails);
        }
    }

    private static void setupAlarm(NotificationDetails notificationDetails, AlarmManager alarmManager, long j, PendingIntent pendingIntent) {
        if (notificationDetails.scheduleMode == null) {
            notificationDetails.scheduleMode = ScheduleMode.exact;
        }
        if (notificationDetails.scheduleMode.useAllowWhileIdle()) {
            setupAllowWhileIdleAlarm(notificationDetails, alarmManager, j, pendingIntent);
            return;
        }
        if (notificationDetails.scheduleMode.useExactAlarm()) {
            checkCanScheduleExactAlarms(alarmManager);
            AlarmManagerCompat.setExact(alarmManager, 0, j, pendingIntent);
        } else if (notificationDetails.scheduleMode.useAlarmClock()) {
            checkCanScheduleExactAlarms(alarmManager);
            AlarmManagerCompat.setAlarmClock(alarmManager, j, pendingIntent, pendingIntent);
        } else {
            alarmManager.set(0, j, pendingIntent);
        }
    }

    private static void setupAllowWhileIdleAlarm(NotificationDetails notificationDetails, AlarmManager alarmManager, long j, PendingIntent pendingIntent) {
        if (notificationDetails.scheduleMode.useExactAlarm()) {
            checkCanScheduleExactAlarms(alarmManager);
            AlarmManagerCompat.setExactAndAllowWhileIdle(alarmManager, 0, j, pendingIntent);
        } else if (notificationDetails.scheduleMode.useAlarmClock()) {
            checkCanScheduleExactAlarms(alarmManager);
            AlarmManagerCompat.setAlarmClock(alarmManager, j, pendingIntent, pendingIntent);
        } else {
            AlarmManagerCompat.setAndAllowWhileIdle(alarmManager, 0, j, pendingIntent);
        }
    }

    private static void checkCanScheduleExactAlarms(AlarmManager alarmManager) {
        if (Build.VERSION.SDK_INT >= 31 && !alarmManager.canScheduleExactAlarms()) {
            throw new ExactAlarmPermissionException();
        }
    }

    private static long calculateNextNotificationTrigger(long j, long j2) {
        while (j < System.currentTimeMillis()) {
            j += j2;
        }
        return j;
    }

    private static long calculateRepeatIntervalMilliseconds(NotificationDetails notificationDetails) {
        if (notificationDetails.repeatIntervalMilliseconds != null) {
            return notificationDetails.repeatIntervalMilliseconds.intValue();
        }
        int i = AnonymousClass5.$SwitchMap$com$dexterous$flutterlocalnotifications$models$RepeatInterval[notificationDetails.repeatInterval.ordinal()];
        if (i == 1) {
            return 60000L;
        }
        if (i == 2) {
            return 3600000L;
        }
        if (i != 3) {
            return i != 4 ? 0L : 604800000L;
        }
        return 86400000L;
    }

    private static void saveScheduledNotification(Context context, NotificationDetails notificationDetails) {
        ArrayList<NotificationDetails> loadScheduledNotifications = loadScheduledNotifications(context);
        ArrayList arrayList = new ArrayList();
        Iterator<NotificationDetails> it = loadScheduledNotifications.iterator();
        while (it.hasNext()) {
            NotificationDetails next = it.next();
            if (!next.f63id.equals(notificationDetails.f63id)) {
                arrayList.add(next);
            }
        }
        arrayList.add(notificationDetails);
        saveScheduledNotifications(context, arrayList);
    }

    private static int getDrawableResourceId(Context context, String str) {
        return context.getResources().getIdentifier(str, DRAWABLE, context.getPackageName());
    }

    private static byte[] castObjectToByteArray(Object obj) {
        if (obj instanceof ArrayList) {
            ArrayList arrayList = (ArrayList) obj;
            byte[] bArr = new byte[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                bArr[i] = (byte) ((Double) arrayList.get(i)).intValue();
            }
            return bArr;
        }
        return (byte[]) obj;
    }

    private static Bitmap getBitmapFromSource(Context context, Object obj, BitmapSource bitmapSource) {
        if (bitmapSource == BitmapSource.DrawableResource) {
            return BitmapFactory.decodeResource(context.getResources(), getDrawableResourceId(context, (String) obj));
        }
        if (bitmapSource == BitmapSource.FilePath) {
            return BitmapFactory.decodeFile((String) obj);
        }
        if (bitmapSource != BitmapSource.ByteArray) {
            return null;
        }
        byte[] castObjectToByteArray = castObjectToByteArray(obj);
        return BitmapFactory.decodeByteArray(castObjectToByteArray, 0, castObjectToByteArray.length);
    }

    private static IconCompat getIconFromSource(Context context, Object obj, IconSource iconSource) {
        int i = AnonymousClass5.$SwitchMap$com$dexterous$flutterlocalnotifications$models$IconSource[iconSource.ordinal()];
        if (i == 1) {
            return IconCompat.createWithResource(context, getDrawableResourceId(context, (String) obj));
        }
        if (i == 2) {
            return IconCompat.createWithBitmap(BitmapFactory.decodeFile((String) obj));
        }
        if (i == 3) {
            return IconCompat.createWithContentUri((String) obj);
        }
        if (i != 4) {
            if (i != 5) {
                return null;
            }
            byte[] castObjectToByteArray = castObjectToByteArray(obj);
            return IconCompat.createWithData(castObjectToByteArray, 0, castObjectToByteArray.length);
        }
        try {
            AssetFileDescriptor openFd = context.getAssets().openFd(FlutterInjector.instance().flutterLoader().getLookupKeyForAsset((String) obj));
            FileInputStream createInputStream = openFd.createInputStream();
            IconCompat createWithBitmap = IconCompat.createWithBitmap(BitmapFactory.decodeStream(createInputStream));
            createInputStream.close();
            openFd.close();
            return createWithBitmap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setVisibility(NotificationDetails notificationDetails, NotificationCompat.Builder builder) {
        if (notificationDetails.visibility == null) {
            return;
        }
        int intValue = notificationDetails.visibility.intValue();
        int i = 1;
        if (intValue == 0) {
            i = 0;
        } else if (intValue != 1) {
            if (intValue != 2) {
                throw new IllegalArgumentException("Unknown index: " + notificationDetails.visibility);
            }
            i = -1;
        }
        builder.setVisibility(i);
    }

    private static void applyGrouping(NotificationDetails notificationDetails, NotificationCompat.Builder builder) {
        boolean z;
        if (StringUtils.isNullOrEmpty(notificationDetails.groupKey).booleanValue()) {
            z = false;
        } else {
            builder.setGroup(notificationDetails.groupKey);
            z = true;
        }
        if (z) {
            if (BooleanUtils.getValue(notificationDetails.setAsGroupSummary)) {
                builder.setGroupSummary(true);
            }
            builder.setGroupAlertBehavior(notificationDetails.groupAlertBehavior.intValue());
        }
    }

    private static void setVibrationPattern(NotificationDetails notificationDetails, NotificationCompat.Builder builder) {
        if (BooleanUtils.getValue(notificationDetails.enableVibration)) {
            if (notificationDetails.vibrationPattern == null || notificationDetails.vibrationPattern.length <= 0) {
                return;
            }
            builder.setVibrate(notificationDetails.vibrationPattern);
            return;
        }
        builder.setVibrate(new long[]{0});
    }

    private static void setLights(NotificationDetails notificationDetails, NotificationCompat.Builder builder) {
        if (!BooleanUtils.getValue(notificationDetails.enableLights) || notificationDetails.ledOnMs == null || notificationDetails.ledOffMs == null) {
            return;
        }
        builder.setLights(notificationDetails.ledColor.intValue(), notificationDetails.ledOnMs.intValue(), notificationDetails.ledOffMs.intValue());
    }

    private static void setSound(Context context, NotificationDetails notificationDetails, NotificationCompat.Builder builder) {
        if (BooleanUtils.getValue(notificationDetails.playSound)) {
            builder.setSound(retrieveSoundResourceUri(context, notificationDetails.sound, notificationDetails.soundSource));
        } else {
            builder.setSound(null);
        }
    }

    private static void setCategory(NotificationDetails notificationDetails, NotificationCompat.Builder builder) {
        if (notificationDetails.category == null) {
            return;
        }
        builder.setCategory(notificationDetails.category);
    }

    private static void setTimeoutAfter(NotificationDetails notificationDetails, NotificationCompat.Builder builder) {
        if (notificationDetails.timeoutAfter == null) {
            return;
        }
        builder.setTimeoutAfter(notificationDetails.timeoutAfter.longValue());
    }

    private static Intent getLaunchIntent(Context context) {
        return context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.dexterous.flutterlocalnotifications.FlutterLocalNotificationsPlugin$5, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$dexterous$flutterlocalnotifications$models$IconSource;
        static final /* synthetic */ int[] $SwitchMap$com$dexterous$flutterlocalnotifications$models$NotificationStyle;
        static final /* synthetic */ int[] $SwitchMap$com$dexterous$flutterlocalnotifications$models$RepeatInterval;

        static {
            int[] iArr = new int[NotificationStyle.values().length];
            $SwitchMap$com$dexterous$flutterlocalnotifications$models$NotificationStyle = iArr;
            try {
                iArr[NotificationStyle.BigPicture.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$dexterous$flutterlocalnotifications$models$NotificationStyle[NotificationStyle.BigText.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$dexterous$flutterlocalnotifications$models$NotificationStyle[NotificationStyle.Inbox.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$dexterous$flutterlocalnotifications$models$NotificationStyle[NotificationStyle.Messaging.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$dexterous$flutterlocalnotifications$models$NotificationStyle[NotificationStyle.Media.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[IconSource.values().length];
            $SwitchMap$com$dexterous$flutterlocalnotifications$models$IconSource = iArr2;
            try {
                iArr2[IconSource.DrawableResource.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$dexterous$flutterlocalnotifications$models$IconSource[IconSource.BitmapFilePath.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$dexterous$flutterlocalnotifications$models$IconSource[IconSource.ContentUri.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$dexterous$flutterlocalnotifications$models$IconSource[IconSource.FlutterBitmapAsset.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$dexterous$flutterlocalnotifications$models$IconSource[IconSource.ByteArray.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            int[] iArr3 = new int[RepeatInterval.values().length];
            $SwitchMap$com$dexterous$flutterlocalnotifications$models$RepeatInterval = iArr3;
            try {
                iArr3[RepeatInterval.EveryMinute.ordinal()] = 1;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$dexterous$flutterlocalnotifications$models$RepeatInterval[RepeatInterval.Hourly.ordinal()] = 2;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$dexterous$flutterlocalnotifications$models$RepeatInterval[RepeatInterval.Daily.ordinal()] = 3;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$dexterous$flutterlocalnotifications$models$RepeatInterval[RepeatInterval.Weekly.ordinal()] = 4;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    private static void setStyle(Context context, NotificationDetails notificationDetails, NotificationCompat.Builder builder) {
        int i = AnonymousClass5.$SwitchMap$com$dexterous$flutterlocalnotifications$models$NotificationStyle[notificationDetails.style.ordinal()];
        if (i == 1) {
            setBigPictureStyle(context, notificationDetails, builder);
            return;
        }
        if (i == 2) {
            setBigTextStyle(notificationDetails, builder);
            return;
        }
        if (i == 3) {
            setInboxStyle(notificationDetails, builder);
        } else if (i == 4) {
            setMessagingStyle(context, notificationDetails, builder);
        } else {
            if (i != 5) {
                return;
            }
            setMediaStyle(builder);
        }
    }

    private static void setProgress(NotificationDetails notificationDetails, NotificationCompat.Builder builder) {
        if (BooleanUtils.getValue(notificationDetails.showProgress)) {
            builder.setProgress(notificationDetails.maxProgress.intValue(), notificationDetails.progress.intValue(), notificationDetails.indeterminate.booleanValue());
        }
    }

    private static void setBigPictureStyle(Context context, NotificationDetails notificationDetails, NotificationCompat.Builder builder) {
        CharSequence charSequence;
        CharSequence charSequence2;
        BigPictureStyleInformation bigPictureStyleInformation = (BigPictureStyleInformation) notificationDetails.styleInformation;
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        if (bigPictureStyleInformation.contentTitle != null) {
            if (bigPictureStyleInformation.htmlFormatContentTitle.booleanValue()) {
                charSequence2 = fromHtml(bigPictureStyleInformation.contentTitle);
            } else {
                charSequence2 = bigPictureStyleInformation.contentTitle;
            }
            bigPictureStyle.setBigContentTitle(charSequence2);
        }
        if (bigPictureStyleInformation.summaryText != null) {
            if (bigPictureStyleInformation.htmlFormatSummaryText.booleanValue()) {
                charSequence = fromHtml(bigPictureStyleInformation.summaryText);
            } else {
                charSequence = bigPictureStyleInformation.summaryText;
            }
            bigPictureStyle.setSummaryText(charSequence);
        }
        if (bigPictureStyleInformation.hideExpandedLargeIcon.booleanValue()) {
            bigPictureStyle.bigLargeIcon((Bitmap) null);
        } else if (bigPictureStyleInformation.largeIcon != null) {
            bigPictureStyle.bigLargeIcon(getBitmapFromSource(context, bigPictureStyleInformation.largeIcon, bigPictureStyleInformation.largeIconBitmapSource));
        }
        bigPictureStyle.bigPicture(getBitmapFromSource(context, bigPictureStyleInformation.bigPicture, bigPictureStyleInformation.bigPictureBitmapSource));
        builder.setStyle(bigPictureStyle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.core.app.NotificationCompat$InboxStyle, androidx.core.app.NotificationCompat$Style] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r2v4, types: [android.text.Spanned] */
    /* JADX WARN: Type inference failed for: r5v0, types: [androidx.core.app.NotificationCompat$Builder] */
    private static void setInboxStyle(NotificationDetails notificationDetails, NotificationCompat.Builder builder) {
        CharSequence charSequence;
        CharSequence charSequence2;
        InboxStyleInformation inboxStyleInformation = (InboxStyleInformation) notificationDetails.styleInformation;
        ?? inboxStyle = new NotificationCompat.InboxStyle();
        if (inboxStyleInformation.contentTitle != null) {
            if (inboxStyleInformation.htmlFormatContentTitle.booleanValue()) {
                charSequence2 = fromHtml(inboxStyleInformation.contentTitle);
            } else {
                charSequence2 = inboxStyleInformation.contentTitle;
            }
            inboxStyle.setBigContentTitle(charSequence2);
        }
        if (inboxStyleInformation.summaryText != null) {
            if (inboxStyleInformation.htmlFormatSummaryText.booleanValue()) {
                charSequence = fromHtml(inboxStyleInformation.summaryText);
            } else {
                charSequence = inboxStyleInformation.summaryText;
            }
            inboxStyle.setSummaryText(charSequence);
        }
        if (inboxStyleInformation.lines != null) {
            Iterator<String> it = inboxStyleInformation.lines.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (inboxStyleInformation.htmlFormatLines.booleanValue()) {
                    next = fromHtml(next);
                }
                inboxStyle.addLine(next);
            }
        }
        builder.setStyle(inboxStyle);
    }

    private static void setMediaStyle(NotificationCompat.Builder builder) {
        builder.setStyle(new NotificationCompat.MediaStyle());
    }

    private static void setMessagingStyle(Context context, NotificationDetails notificationDetails, NotificationCompat.Builder builder) {
        MessagingStyleInformation messagingStyleInformation = (MessagingStyleInformation) notificationDetails.styleInformation;
        NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle(buildPerson(context, messagingStyleInformation.person));
        messagingStyle.setGroupConversation(BooleanUtils.getValue(messagingStyleInformation.groupConversation));
        if (messagingStyleInformation.conversationTitle != null) {
            messagingStyle.setConversationTitle(messagingStyleInformation.conversationTitle);
        }
        if (messagingStyleInformation.messages != null && !messagingStyleInformation.messages.isEmpty()) {
            Iterator<MessageDetails> it = messagingStyleInformation.messages.iterator();
            while (it.hasNext()) {
                messagingStyle.addMessage(createMessage(context, it.next()));
            }
        }
        builder.setStyle(messagingStyle);
    }

    private static NotificationCompat.MessagingStyle.Message createMessage(Context context, MessageDetails messageDetails) {
        NotificationCompat.MessagingStyle.Message message = new NotificationCompat.MessagingStyle.Message(messageDetails.text, messageDetails.timestamp.longValue(), buildPerson(context, messageDetails.person));
        if (messageDetails.dataUri != null && messageDetails.dataMimeType != null) {
            message.setData(messageDetails.dataMimeType, Uri.parse(messageDetails.dataUri));
        }
        return message;
    }

    private static Person buildPerson(Context context, PersonDetails personDetails) {
        if (personDetails == null) {
            return null;
        }
        Person.Builder builder = new Person.Builder();
        builder.setBot(BooleanUtils.getValue(personDetails.bot));
        if (personDetails.icon != null && personDetails.iconBitmapSource != null) {
            builder.setIcon(getIconFromSource(context, personDetails.icon, personDetails.iconBitmapSource));
        }
        builder.setImportant(BooleanUtils.getValue(personDetails.important));
        if (personDetails.key != null) {
            builder.setKey(personDetails.key);
        }
        if (personDetails.name != null) {
            builder.setName(personDetails.name);
        }
        if (personDetails.uri != null) {
            builder.setUri(personDetails.uri);
        }
        return builder.build();
    }

    private static void setBigTextStyle(NotificationDetails notificationDetails, NotificationCompat.Builder builder) {
        CharSequence charSequence;
        CharSequence charSequence2;
        CharSequence charSequence3;
        BigTextStyleInformation bigTextStyleInformation = (BigTextStyleInformation) notificationDetails.styleInformation;
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        if (bigTextStyleInformation.bigText != null) {
            if (bigTextStyleInformation.htmlFormatBigText.booleanValue()) {
                charSequence3 = fromHtml(bigTextStyleInformation.bigText);
            } else {
                charSequence3 = bigTextStyleInformation.bigText;
            }
            bigTextStyle.bigText(charSequence3);
        }
        if (bigTextStyleInformation.contentTitle != null) {
            if (bigTextStyleInformation.htmlFormatContentTitle.booleanValue()) {
                charSequence2 = fromHtml(bigTextStyleInformation.contentTitle);
            } else {
                charSequence2 = bigTextStyleInformation.contentTitle;
            }
            bigTextStyle.setBigContentTitle(charSequence2);
        }
        if (bigTextStyleInformation.summaryText != null) {
            if (bigTextStyleInformation.htmlFormatSummaryText.booleanValue()) {
                charSequence = fromHtml(bigTextStyleInformation.summaryText);
            } else {
                charSequence = bigTextStyleInformation.summaryText;
            }
            bigTextStyle.setSummaryText(charSequence);
        }
        builder.setStyle(bigTextStyle);
    }

    private static void setupNotificationChannel(Context context, NotificationChannelDetails notificationChannelDetails) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            NotificationChannel notificationChannel = new NotificationChannel(notificationChannelDetails.f61id, notificationChannelDetails.name, notificationChannelDetails.importance.intValue());
            notificationChannel.setDescription(notificationChannelDetails.description);
            notificationChannel.setGroup(notificationChannelDetails.groupId);
            if (notificationChannelDetails.playSound.booleanValue()) {
                notificationChannel.setSound(retrieveSoundResourceUri(context, notificationChannelDetails.sound, notificationChannelDetails.soundSource), new AudioAttributes.Builder().setUsage(Integer.valueOf(notificationChannelDetails.audioAttributesUsage != null ? notificationChannelDetails.audioAttributesUsage.intValue() : 5).intValue()).build());
            } else {
                notificationChannel.setSound(null, null);
            }
            notificationChannel.enableVibration(BooleanUtils.getValue(notificationChannelDetails.enableVibration));
            if (notificationChannelDetails.vibrationPattern != null && notificationChannelDetails.vibrationPattern.length > 0) {
                notificationChannel.setVibrationPattern(notificationChannelDetails.vibrationPattern);
            }
            boolean value = BooleanUtils.getValue(notificationChannelDetails.enableLights);
            notificationChannel.enableLights(value);
            if (value && notificationChannelDetails.ledColor != null) {
                notificationChannel.setLightColor(notificationChannelDetails.ledColor.intValue());
            }
            notificationChannel.setShowBadge(BooleanUtils.getValue(notificationChannelDetails.showBadge));
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private static Uri retrieveSoundResourceUri(Context context, String str, SoundSource soundSource) {
        if (StringUtils.isNullOrEmpty(str).booleanValue()) {
            return RingtoneManager.getDefaultUri(2);
        }
        if (soundSource == null || soundSource == SoundSource.RawResource) {
            return Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + str);
        }
        if (soundSource == SoundSource.Uri) {
            return Uri.parse(str);
        }
        return null;
    }

    private static AlarmManager getAlarmManager(Context context) {
        return (AlarmManager) context.getSystemService(androidx.core.app.NotificationCompat.CATEGORY_ALARM);
    }

    private static boolean isValidDrawableResource(Context context, String str, MethodChannel.Result result, String str2) {
        if (context.getResources().getIdentifier(str, DRAWABLE, context.getPackageName()) != 0) {
            return true;
        }
        result.error(str2, String.format(INVALID_DRAWABLE_RESOURCE_ERROR_MESSAGE, str), null);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void showNotification(Context context, NotificationDetails notificationDetails) {
        Notification createNotification = createNotification(context, notificationDetails);
        NotificationManagerCompat notificationManager = getNotificationManager(context);
        if (notificationDetails.tag != null) {
            notificationManager.notify(notificationDetails.tag, notificationDetails.f63id.intValue(), createNotification);
        } else {
            notificationManager.notify(notificationDetails.f63id.intValue(), createNotification);
        }
    }

    private static void zonedScheduleNextNotification(Context context, NotificationDetails notificationDetails) {
        String nextFireDate = getNextFireDate(notificationDetails);
        if (nextFireDate == null) {
            return;
        }
        notificationDetails.scheduledDateTime = nextFireDate;
        zonedScheduleNotification(context, notificationDetails, true);
    }

    private static void zonedScheduleNextNotificationMatchingDateComponents(Context context, NotificationDetails notificationDetails) {
        String nextFireDateMatchingDateTimeComponents = getNextFireDateMatchingDateTimeComponents(notificationDetails);
        if (nextFireDateMatchingDateTimeComponents == null) {
            return;
        }
        notificationDetails.scheduledDateTime = nextFireDateMatchingDateTimeComponents;
        zonedScheduleNotification(context, notificationDetails, true);
    }

    private static String getNextFireDate(NotificationDetails notificationDetails) {
        if (notificationDetails.scheduledNotificationRepeatFrequency == ScheduledNotificationRepeatFrequency.Daily) {
            return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.parse(notificationDetails.scheduledDateTime).plusDays(1L));
        }
        if (notificationDetails.scheduledNotificationRepeatFrequency != ScheduledNotificationRepeatFrequency.Weekly) {
            return null;
        }
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.parse(notificationDetails.scheduledDateTime).plusWeeks(1L));
    }

    private static String getNextFireDateMatchingDateTimeComponents(NotificationDetails notificationDetails) {
        ZoneId of = ZoneId.of(notificationDetails.timeZoneName);
        ZonedDateTime of2 = ZonedDateTime.of(LocalDateTime.parse(notificationDetails.scheduledDateTime), of);
        ZonedDateTime now = ZonedDateTime.now(of);
        ZonedDateTime of3 = ZonedDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), of2.getHour(), of2.getMinute(), of2.getSecond(), of2.getNano(), of);
        while (of3.isBefore(now)) {
            of3 = of3.plusDays(1L);
        }
        if (notificationDetails.matchDateTimeComponents == DateTimeComponents.Time) {
            return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(of3);
        }
        if (notificationDetails.matchDateTimeComponents == DateTimeComponents.DayOfWeekAndTime) {
            while (of3.getDayOfWeek() != of2.getDayOfWeek()) {
                of3 = of3.plusDays(1L);
            }
            return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(of3);
        }
        if (notificationDetails.matchDateTimeComponents == DateTimeComponents.DayOfMonthAndTime) {
            while (of3.getDayOfMonth() != of2.getDayOfMonth()) {
                of3 = of3.plusDays(1L);
            }
            return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(of3);
        }
        if (notificationDetails.matchDateTimeComponents != DateTimeComponents.DateAndTime) {
            return null;
        }
        while (true) {
            if (of3.getMonthValue() != of2.getMonthValue() || of3.getDayOfMonth() != of2.getDayOfMonth()) {
                of3 = of3.plusDays(1L);
            } else {
                return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(of3);
            }
        }
    }

    private static NotificationManagerCompat getNotificationManager(Context context) {
        return NotificationManagerCompat.from(context);
    }

    private static boolean launchedActivityFromHistory(Intent intent) {
        return intent != null && (intent.getFlags() & 1048576) == 1048576;
    }

    private void setActivity(Activity activity) {
        this.mainActivity = activity;
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.applicationContext = flutterPluginBinding.getApplicationContext();
        MethodChannel methodChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), METHOD_CHANNEL);
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.channel.setMethodCallHandler(null);
        this.channel = null;
        this.applicationContext = null;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        activityPluginBinding.addOnNewIntentListener(this);
        activityPluginBinding.addRequestPermissionsResultListener(this);
        activityPluginBinding.addActivityResultListener(this);
        Activity activity = activityPluginBinding.getActivity();
        this.mainActivity = activity;
        Intent intent = activity.getIntent();
        if (launchedActivityFromHistory(intent) || !SELECT_FOREGROUND_NOTIFICATION_ACTION.equals(intent.getAction())) {
            return;
        }
        processForegroundNotificationAction(intent, extractNotificationResponseMap(intent));
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        this.mainActivity = null;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        activityPluginBinding.addOnNewIntentListener(this);
        activityPluginBinding.addRequestPermissionsResultListener(this);
        activityPluginBinding.addActivityResultListener(this);
        this.mainActivity = activityPluginBinding.getActivity();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        this.mainActivity = null;
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, final MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2096263152:
                if (str.equals(STOP_FOREGROUND_SERVICE)) {
                    c = 0;
                    break;
                }
                break;
            case -2041662895:
                if (str.equals(GET_NOTIFICATION_CHANNELS_METHOD)) {
                    c = 1;
                    break;
                }
                break;
            case -1873731438:
                if (str.equals(DELETE_NOTIFICATION_CHANNEL_GROUP_METHOD)) {
                    c = 2;
                    break;
                }
                break;
            case -1785484984:
                if (str.equals(REQUEST_NOTIFICATIONS_PERMISSION_METHOD)) {
                    c = 3;
                    break;
                }
                break;
            case -1367724422:
                if (str.equals("cancel")) {
                    c = 4;
                    break;
                }
                break;
            case -1108601471:
                if (str.equals(REQUEST_EXACT_ALARMS_PERMISSION_METHOD)) {
                    c = 5;
                    break;
                }
                break;
            case -950516363:
                if (str.equals(REQUEST_FULL_SCREEN_INTENT_PERMISSION_METHOD)) {
                    c = 6;
                    break;
                }
                break;
            case -799130106:
                if (str.equals(PENDING_NOTIFICATION_REQUESTS_METHOD)) {
                    c = 7;
                    break;
                }
                break;
            case -208611345:
                if (str.equals(GET_NOTIFICATION_APP_LAUNCH_DETAILS_METHOD)) {
                    c = '\b';
                    break;
                }
                break;
            case 3529469:
                if (str.equals(SHOW_METHOD)) {
                    c = '\t';
                    break;
                }
                break;
            case 6625712:
                if (str.equals(PERIODICALLY_SHOW_METHOD)) {
                    c = '\n';
                    break;
                }
                break;
            case 116003316:
                if (str.equals(GET_ACTIVE_NOTIFICATION_MESSAGING_STYLE_METHOD)) {
                    c = 11;
                    break;
                }
                break;
            case 476547271:
                if (str.equals(CANCEL_ALL_METHOD)) {
                    c = '\f';
                    break;
                }
                break;
            case 548573423:
                if (str.equals(ZONED_SCHEDULE_METHOD)) {
                    c = '\r';
                    break;
                }
                break;
            case 767006947:
                if (str.equals(CREATE_NOTIFICATION_CHANNEL_GROUP_METHOD)) {
                    c = 14;
                    break;
                }
                break;
            case 825311171:
                if (str.equals(GET_CALLBACK_HANDLE_METHOD)) {
                    c = 15;
                    break;
                }
                break;
            case 871091088:
                if (str.equals(INITIALIZE_METHOD)) {
                    c = 16;
                    break;
                }
                break;
            case 891942317:
                if (str.equals(ARE_NOTIFICATIONS_ENABLED_METHOD)) {
                    c = 17;
                    break;
                }
                break;
            case 972029712:
                if (str.equals(CAN_SCHEDULE_EXACT_NOTIFICATIONS_METHOD)) {
                    c = 18;
                    break;
                }
                break;
            case 1008472557:
                if (str.equals(DELETE_NOTIFICATION_CHANNEL_METHOD)) {
                    c = 19;
                    break;
                }
                break;
            case 1207771056:
                if (str.equals(START_FOREGROUND_SERVICE)) {
                    c = 20;
                    break;
                }
                break;
            case 1594833996:
                if (str.equals(GET_ACTIVE_NOTIFICATIONS_METHOD)) {
                    c = 21;
                    break;
                }
                break;
            case 1653467900:
                if (str.equals(CREATE_NOTIFICATION_CHANNEL_METHOD)) {
                    c = 22;
                    break;
                }
                break;
            case 2147197514:
                if (str.equals(PERIODICALLY_SHOW_WITH_DURATION)) {
                    c = 23;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                stopForegroundService(result);
                return;
            case 1:
                getNotificationChannels(result);
                return;
            case 2:
                deleteNotificationChannelGroup(methodCall, result);
                return;
            case 3:
                requestNotificationsPermission(new PermissionRequestListener() { // from class: com.dexterous.flutterlocalnotifications.FlutterLocalNotificationsPlugin.2
                    @Override // com.dexterous.flutterlocalnotifications.PermissionRequestListener
                    public void complete(boolean z) {
                        result.success(Boolean.valueOf(z));
                    }

                    @Override // com.dexterous.flutterlocalnotifications.PermissionRequestListener
                    public void fail(String str2) {
                        result.error(FlutterLocalNotificationsPlugin.PERMISSION_REQUEST_IN_PROGRESS_ERROR_CODE, str2, null);
                    }
                });
                return;
            case 4:
                cancel(methodCall, result);
                return;
            case 5:
                requestExactAlarmsPermission(new PermissionRequestListener() { // from class: com.dexterous.flutterlocalnotifications.FlutterLocalNotificationsPlugin.3
                    @Override // com.dexterous.flutterlocalnotifications.PermissionRequestListener
                    public void complete(boolean z) {
                        result.success(Boolean.valueOf(z));
                    }

                    @Override // com.dexterous.flutterlocalnotifications.PermissionRequestListener
                    public void fail(String str2) {
                        result.error(FlutterLocalNotificationsPlugin.PERMISSION_REQUEST_IN_PROGRESS_ERROR_CODE, str2, null);
                    }
                });
                return;
            case 6:
                requestFullScreenIntentPermission(new PermissionRequestListener() { // from class: com.dexterous.flutterlocalnotifications.FlutterLocalNotificationsPlugin.4
                    @Override // com.dexterous.flutterlocalnotifications.PermissionRequestListener
                    public void complete(boolean z) {
                        result.success(Boolean.valueOf(z));
                    }

                    @Override // com.dexterous.flutterlocalnotifications.PermissionRequestListener
                    public void fail(String str2) {
                        result.error(FlutterLocalNotificationsPlugin.PERMISSION_REQUEST_IN_PROGRESS_ERROR_CODE, str2, null);
                    }
                });
                return;
            case 7:
                pendingNotificationRequests(result);
                return;
            case '\b':
                getNotificationAppLaunchDetails(result);
                return;
            case '\t':
                show(methodCall, result);
                return;
            case '\n':
                repeat(methodCall, result);
                return;
            case 11:
                getActiveNotificationMessagingStyle(methodCall, result);
                return;
            case '\f':
                cancelAllNotifications(result);
                return;
            case '\r':
                zonedSchedule(methodCall, result);
                return;
            case 14:
                createNotificationChannelGroup(methodCall, result);
                return;
            case 15:
                getCallbackHandle(result);
                return;
            case 16:
                initialize(methodCall, result);
                return;
            case 17:
                areNotificationsEnabled(result);
                return;
            case 18:
                setCanScheduleExactNotifications(result);
                return;
            case 19:
                deleteNotificationChannel(methodCall, result);
                return;
            case 20:
                startForegroundService(methodCall, result);
                return;
            case 21:
                getActiveNotifications(result);
                return;
            case 22:
                createNotificationChannel(methodCall, result);
                return;
            case 23:
                repeat(methodCall, result);
                return;
            default:
                result.notImplemented();
                return;
        }
    }

    private void pendingNotificationRequests(MethodChannel.Result result) {
        ArrayList<NotificationDetails> loadScheduledNotifications = loadScheduledNotifications(this.applicationContext);
        ArrayList arrayList = new ArrayList();
        Iterator<NotificationDetails> it = loadScheduledNotifications.iterator();
        while (it.hasNext()) {
            NotificationDetails next = it.next();
            HashMap hashMap = new HashMap();
            hashMap.put("id", next.f63id);
            hashMap.put("title", next.title);
            hashMap.put("body", next.body);
            hashMap.put("payload", next.payload);
            arrayList.add(hashMap);
        }
        result.success(arrayList);
    }

    private void getActiveNotifications(MethodChannel.Result result) {
        if (Build.VERSION.SDK_INT < 23) {
            result.error(UNSUPPORTED_OS_VERSION_ERROR_CODE, GET_ACTIVE_NOTIFICATIONS_ERROR_MESSAGE, null);
            return;
        }
        try {
            StatusBarNotification[] activeNotifications = ((NotificationManager) this.applicationContext.getSystemService("notification")).getActiveNotifications();
            ArrayList arrayList = new ArrayList();
            for (StatusBarNotification statusBarNotification : activeNotifications) {
                HashMap hashMap = new HashMap();
                hashMap.put("id", Integer.valueOf(statusBarNotification.getId()));
                Notification notification = statusBarNotification.getNotification();
                if (Build.VERSION.SDK_INT >= 26) {
                    hashMap.put("channelId", notification.getChannelId());
                }
                hashMap.put("tag", statusBarNotification.getTag());
                hashMap.put("groupKey", notification.getGroup());
                hashMap.put("title", notification.extras.getCharSequence(androidx.core.app.NotificationCompat.EXTRA_TITLE));
                hashMap.put("body", notification.extras.getCharSequence(androidx.core.app.NotificationCompat.EXTRA_TEXT));
                hashMap.put("bigText", notification.extras.getCharSequence(androidx.core.app.NotificationCompat.EXTRA_BIG_TEXT));
                arrayList.add(hashMap);
            }
            result.success(arrayList);
        } catch (Throwable th) {
            result.error(UNSUPPORTED_OS_VERSION_ERROR_CODE, th.getMessage(), Log.getStackTraceString(th));
        }
    }

    private void cancel(MethodCall methodCall, MethodChannel.Result result) {
        Map map = (Map) methodCall.arguments();
        cancelNotification((Integer) map.get("id"), (String) map.get("tag"));
        result.success(null);
    }

    private void repeat(MethodCall methodCall, MethodChannel.Result result) {
        NotificationDetails extractNotificationDetails = extractNotificationDetails(result, (Map) methodCall.arguments());
        if (extractNotificationDetails != null) {
            try {
                repeatNotification(this.applicationContext, extractNotificationDetails, true);
                result.success(null);
            } catch (PluginException e) {
                result.error(e.code, e.getMessage(), null);
            }
        }
    }

    private void zonedSchedule(MethodCall methodCall, MethodChannel.Result result) {
        NotificationDetails extractNotificationDetails = extractNotificationDetails(result, (Map) methodCall.arguments());
        if (extractNotificationDetails != null) {
            if (extractNotificationDetails.matchDateTimeComponents != null) {
                extractNotificationDetails.scheduledDateTime = getNextFireDateMatchingDateTimeComponents(extractNotificationDetails);
            }
            try {
                zonedScheduleNotification(this.applicationContext, extractNotificationDetails, true);
                result.success(null);
            } catch (PluginException e) {
                result.error(e.code, e.getMessage(), null);
            }
        }
    }

    private void show(MethodCall methodCall, MethodChannel.Result result) {
        NotificationDetails extractNotificationDetails = extractNotificationDetails(result, (Map) methodCall.arguments());
        if (extractNotificationDetails != null) {
            showNotification(this.applicationContext, extractNotificationDetails);
            result.success(null);
        }
    }

    private void getNotificationAppLaunchDetails(MethodChannel.Result result) {
        HashMap hashMap = new HashMap();
        boolean z = false;
        Boolean bool = false;
        Activity activity = this.mainActivity;
        if (activity != null) {
            Intent intent = activity.getIntent();
            if (intent != null && ((SELECT_NOTIFICATION.equals(intent.getAction()) || SELECT_FOREGROUND_NOTIFICATION_ACTION.equals(intent.getAction())) && !launchedActivityFromHistory(intent))) {
                z = true;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (valueOf.booleanValue()) {
                hashMap.put("notificationResponse", extractNotificationResponseMap(intent));
            }
            bool = valueOf;
        }
        hashMap.put(NOTIFICATION_LAUNCHED_APP, bool);
        result.success(hashMap);
    }

    private void initialize(MethodCall methodCall, MethodChannel.Result result) {
        String str = (String) ((Map) methodCall.arguments()).get(DEFAULT_ICON);
        if (isValidDrawableResource(this.applicationContext, str, result, INVALID_ICON_ERROR_CODE)) {
            Long parseLong = LongUtils.parseLong(methodCall.argument(DISPATCHER_HANDLE));
            Long parseLong2 = LongUtils.parseLong(methodCall.argument(CALLBACK_HANDLE));
            if (parseLong != null && parseLong2 != null) {
                new IsolatePreferences(this.applicationContext).saveCallbackKeys(parseLong, parseLong2);
            }
            this.applicationContext.getSharedPreferences(SHARED_PREFERENCES_KEY, 0).edit().putString(DEFAULT_ICON, str).apply();
            result.success(true);
        }
    }

    private void getCallbackHandle(MethodChannel.Result result) {
        result.success(new IsolatePreferences(this.applicationContext).getCallbackHandle());
    }

    private NotificationDetails extractNotificationDetails(MethodChannel.Result result, Map<String, Object> map) {
        NotificationDetails from = NotificationDetails.from(map);
        if (hasInvalidIcon(result, from.icon) || hasInvalidLargeIcon(result, from.largeIcon, from.largeIconBitmapSource) || hasInvalidBigPictureResources(result, from) || hasInvalidRawSoundResource(result, from) || hasInvalidLedDetails(result, from)) {
            return null;
        }
        return from;
    }

    private boolean hasInvalidLedDetails(MethodChannel.Result result, NotificationDetails notificationDetails) {
        if (notificationDetails.ledColor == null) {
            return false;
        }
        if (notificationDetails.ledOnMs != null && notificationDetails.ledOffMs != null) {
            return false;
        }
        result.error(INVALID_LED_DETAILS_ERROR_CODE, INVALID_LED_DETAILS_ERROR_MESSAGE, null);
        return true;
    }

    private boolean hasInvalidRawSoundResource(MethodChannel.Result result, NotificationDetails notificationDetails) {
        if (StringUtils.isNullOrEmpty(notificationDetails.sound).booleanValue() || !((notificationDetails.soundSource == null || notificationDetails.soundSource == SoundSource.RawResource) && this.applicationContext.getResources().getIdentifier(notificationDetails.sound, "raw", this.applicationContext.getPackageName()) == 0)) {
            return false;
        }
        result.error(INVALID_SOUND_ERROR_CODE, String.format(INVALID_RAW_RESOURCE_ERROR_MESSAGE, notificationDetails.sound), null);
        return true;
    }

    private boolean hasInvalidBigPictureResources(MethodChannel.Result result, NotificationDetails notificationDetails) {
        if (notificationDetails.style != NotificationStyle.BigPicture) {
            return false;
        }
        BigPictureStyleInformation bigPictureStyleInformation = (BigPictureStyleInformation) notificationDetails.styleInformation;
        if (hasInvalidLargeIcon(result, bigPictureStyleInformation.largeIcon, bigPictureStyleInformation.largeIconBitmapSource)) {
            return true;
        }
        if (bigPictureStyleInformation.bigPictureBitmapSource == BitmapSource.DrawableResource) {
            String str = (String) bigPictureStyleInformation.bigPicture;
            return StringUtils.isNullOrEmpty(str).booleanValue() && !isValidDrawableResource(this.applicationContext, str, result, INVALID_BIG_PICTURE_ERROR_CODE);
        }
        if (bigPictureStyleInformation.bigPictureBitmapSource == BitmapSource.FilePath) {
            return StringUtils.isNullOrEmpty((String) bigPictureStyleInformation.bigPicture).booleanValue();
        }
        if (bigPictureStyleInformation.bigPictureBitmapSource != BitmapSource.ByteArray) {
            return false;
        }
        byte[] bArr = (byte[]) bigPictureStyleInformation.bigPicture;
        return bArr == null || bArr.length == 0;
    }

    private boolean hasInvalidLargeIcon(MethodChannel.Result result, Object obj, BitmapSource bitmapSource) {
        if (bitmapSource != BitmapSource.DrawableResource && bitmapSource != BitmapSource.FilePath) {
            return bitmapSource == BitmapSource.ByteArray && ((byte[]) obj).length == 0;
        }
        String str = (String) obj;
        return (StringUtils.isNullOrEmpty(str).booleanValue() || bitmapSource != BitmapSource.DrawableResource || isValidDrawableResource(this.applicationContext, str, result, INVALID_LARGE_ICON_ERROR_CODE)) ? false : true;
    }

    private boolean hasInvalidIcon(MethodChannel.Result result, String str) {
        return (StringUtils.isNullOrEmpty(str).booleanValue() || isValidDrawableResource(this.applicationContext, str, result, INVALID_ICON_ERROR_CODE)) ? false : true;
    }

    private void cancelNotification(Integer num, String str) {
        getAlarmManager(this.applicationContext).cancel(getBroadcastPendingIntent(this.applicationContext, num.intValue(), new Intent(this.applicationContext, (Class<?>) ScheduledNotificationReceiver.class)));
        NotificationManagerCompat notificationManager = getNotificationManager(this.applicationContext);
        if (str == null) {
            notificationManager.cancel(num.intValue());
        } else {
            notificationManager.cancel(str, num.intValue());
        }
        removeNotificationFromCache(this.applicationContext, num);
    }

    private void cancelAllNotifications(MethodChannel.Result result) {
        getNotificationManager(this.applicationContext).cancelAll();
        ArrayList<NotificationDetails> loadScheduledNotifications = loadScheduledNotifications(this.applicationContext);
        if (loadScheduledNotifications == null || loadScheduledNotifications.isEmpty()) {
            result.success(null);
            return;
        }
        Intent intent = new Intent(this.applicationContext, (Class<?>) ScheduledNotificationReceiver.class);
        Iterator<NotificationDetails> it = loadScheduledNotifications.iterator();
        while (it.hasNext()) {
            getAlarmManager(this.applicationContext).cancel(getBroadcastPendingIntent(this.applicationContext, it.next().f63id.intValue(), intent));
        }
        saveScheduledNotifications(this.applicationContext, new ArrayList());
        result.success(null);
    }

    public void requestNotificationsPermission(PermissionRequestListener permissionRequestListener) {
        if (this.permissionRequestProgress != PermissionRequestProgress.None) {
            permissionRequestListener.fail(PERMISSION_REQUEST_IN_PROGRESS_ERROR_MESSAGE);
            return;
        }
        this.callback = permissionRequestListener;
        if (Build.VERSION.SDK_INT >= 33) {
            if (!(ContextCompat.checkSelfPermission(this.mainActivity, "android.permission.POST_NOTIFICATIONS") == 0)) {
                this.permissionRequestProgress = PermissionRequestProgress.RequestingNotificationPermission;
                ActivityCompat.requestPermissions(this.mainActivity, new String[]{"android.permission.POST_NOTIFICATIONS"}, 1);
                return;
            } else {
                this.callback.complete(true);
                this.permissionRequestProgress = PermissionRequestProgress.None;
                return;
            }
        }
        this.callback.complete(NotificationManagerCompat.from(this.mainActivity).areNotificationsEnabled());
    }

    public void requestExactAlarmsPermission(PermissionRequestListener permissionRequestListener) {
        if (this.permissionRequestProgress != PermissionRequestProgress.None) {
            permissionRequestListener.fail(PERMISSION_REQUEST_IN_PROGRESS_ERROR_MESSAGE);
            return;
        }
        this.callback = permissionRequestListener;
        if (Build.VERSION.SDK_INT >= 31) {
            if (!getAlarmManager(this.applicationContext).canScheduleExactAlarms()) {
                this.permissionRequestProgress = PermissionRequestProgress.RequestingExactAlarmsPermission;
                this.mainActivity.startActivityForResult(new Intent("android.settings.REQUEST_SCHEDULE_EXACT_ALARM", Uri.parse("package:" + this.applicationContext.getPackageName())), 2);
                return;
            }
            this.callback.complete(true);
            this.permissionRequestProgress = PermissionRequestProgress.None;
            return;
        }
        this.callback.complete(true);
    }

    public void requestFullScreenIntentPermission(PermissionRequestListener permissionRequestListener) {
        if (this.permissionRequestProgress != PermissionRequestProgress.None) {
            permissionRequestListener.fail(PERMISSION_REQUEST_IN_PROGRESS_ERROR_MESSAGE);
            return;
        }
        this.callback = permissionRequestListener;
        if (Build.VERSION.SDK_INT >= 34) {
            NotificationManager notificationManager = (NotificationManager) this.applicationContext.getSystemService("notification");
            getAlarmManager(this.applicationContext);
            if (!notificationManager.canUseFullScreenIntent()) {
                this.permissionRequestProgress = PermissionRequestProgress.RequestingFullScreenIntentPermission;
                this.mainActivity.startActivityForResult(new Intent("android.settings.MANAGE_APP_USE_FULL_SCREEN_INTENT", Uri.parse("package:" + this.applicationContext.getPackageName())), 3);
                return;
            }
            this.callback.complete(true);
            this.permissionRequestProgress = PermissionRequestProgress.None;
            return;
        }
        this.callback.complete(true);
    }

    @Override // io.flutter.plugin.common.PluginRegistry.RequestPermissionsResultListener
    public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        boolean z = false;
        if (this.permissionRequestProgress == PermissionRequestProgress.RequestingNotificationPermission && i == 1) {
            if (iArr.length > 0 && iArr[0] == 0) {
                z = true;
            }
            this.callback.complete(z);
            this.permissionRequestProgress = PermissionRequestProgress.None;
        }
        return z;
    }

    @Override // io.flutter.plugin.common.PluginRegistry.NewIntentListener
    public boolean onNewIntent(Intent intent) {
        Activity activity;
        boolean booleanValue = sendNotificationPayloadMessage(intent).booleanValue();
        if (booleanValue && (activity = this.mainActivity) != null) {
            activity.setIntent(intent);
        }
        return booleanValue;
    }

    private Boolean sendNotificationPayloadMessage(Intent intent) {
        if (SELECT_NOTIFICATION.equals(intent.getAction()) || SELECT_FOREGROUND_NOTIFICATION_ACTION.equals(intent.getAction())) {
            Map<String, Object> extractNotificationResponseMap = extractNotificationResponseMap(intent);
            if (SELECT_FOREGROUND_NOTIFICATION_ACTION.equals(intent.getAction())) {
                processForegroundNotificationAction(intent, extractNotificationResponseMap);
            }
            this.channel.invokeMethod("didReceiveNotificationResponse", extractNotificationResponseMap);
            return true;
        }
        return false;
    }

    private void processForegroundNotificationAction(Intent intent, Map<String, Object> map) {
        if (intent.getBooleanExtra(CANCEL_NOTIFICATION, false)) {
            NotificationManagerCompat.from(this.applicationContext).cancel(((Integer) map.get(NOTIFICATION_ID)).intValue());
        }
    }

    private void createNotificationChannelGroup(MethodCall methodCall, MethodChannel.Result result) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannelGroupDetails from = NotificationChannelGroupDetails.from((Map) methodCall.arguments());
            NotificationManager notificationManager = (NotificationManager) this.applicationContext.getSystemService("notification");
            NotificationChannelGroup notificationChannelGroup = new NotificationChannelGroup(from.f62id, from.name);
            if (Build.VERSION.SDK_INT >= 28) {
                notificationChannelGroup.setDescription(from.description);
            }
            notificationManager.createNotificationChannelGroup(notificationChannelGroup);
        }
        result.success(null);
    }

    private void deleteNotificationChannelGroup(MethodCall methodCall, MethodChannel.Result result) {
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) this.applicationContext.getSystemService("notification")).deleteNotificationChannelGroup((String) methodCall.arguments());
        }
        result.success(null);
    }

    private void createNotificationChannel(MethodCall methodCall, MethodChannel.Result result) {
        setupNotificationChannel(this.applicationContext, NotificationChannelDetails.from((Map) methodCall.arguments()));
        result.success(null);
    }

    private void deleteNotificationChannel(MethodCall methodCall, MethodChannel.Result result) {
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) this.applicationContext.getSystemService("notification")).deleteNotificationChannel((String) methodCall.arguments());
        }
        result.success(null);
    }

    private void getActiveNotificationMessagingStyle(MethodCall methodCall, MethodChannel.Result result) {
        Notification notification;
        if (Build.VERSION.SDK_INT < 23) {
            result.error(UNSUPPORTED_OS_VERSION_ERROR_CODE, "Android version must be 6.0 or newer to use getActiveNotificationMessagingStyle", null);
            return;
        }
        NotificationManager notificationManager = (NotificationManager) this.applicationContext.getSystemService("notification");
        try {
            Map map = (Map) methodCall.arguments();
            int intValue = ((Integer) map.get("id")).intValue();
            String str = (String) map.get("tag");
            for (StatusBarNotification statusBarNotification : notificationManager.getActiveNotifications()) {
                if (statusBarNotification.getId() == intValue && (str == null || str.equals(statusBarNotification.getTag()))) {
                    notification = statusBarNotification.getNotification();
                    break;
                }
            }
            notification = null;
            if (notification == null) {
                result.success(null);
                return;
            }
            NotificationCompat.MessagingStyle extractMessagingStyleFromNotification = NotificationCompat.MessagingStyle.extractMessagingStyleFromNotification(notification);
            if (extractMessagingStyleFromNotification == null) {
                result.success(null);
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("groupConversation", Boolean.valueOf(extractMessagingStyleFromNotification.isGroupConversation()));
            hashMap.put("person", describePerson(extractMessagingStyleFromNotification.getUser()));
            hashMap.put("conversationTitle", extractMessagingStyleFromNotification.getConversationTitle());
            ArrayList arrayList = new ArrayList();
            for (NotificationCompat.MessagingStyle.Message message : extractMessagingStyleFromNotification.getMessages()) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("text", message.getText());
                hashMap2.put("timestamp", Long.valueOf(message.getTimestamp()));
                hashMap2.put("person", describePerson(message.getPerson()));
                arrayList.add(hashMap2);
            }
            hashMap.put("messages", arrayList);
            result.success(hashMap);
        } catch (Throwable th) {
            result.error(GET_ACTIVE_NOTIFICATION_MESSAGING_STYLE_ERROR_CODE, th.getMessage(), Log.getStackTraceString(th));
        }
    }

    private Map<String, Object> describePerson(Person person) {
        if (person == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("key", person.getKey());
        hashMap.put("name", person.getName());
        hashMap.put(ShareConstants.MEDIA_URI, person.getUri());
        hashMap.put("bot", Boolean.valueOf(person.isBot()));
        hashMap.put("important", Boolean.valueOf(person.isImportant()));
        hashMap.put("icon", describeIcon(person.getIcon()));
        return hashMap;
    }

    private Map<String, Object> describeIcon(IconCompat iconCompat) {
        IconSource iconSource;
        String resourceEntryName;
        if (iconCompat == null) {
            return null;
        }
        int type = iconCompat.getType();
        if (type == 2) {
            iconSource = IconSource.DrawableResource;
            resourceEntryName = this.applicationContext.getResources().getResourceEntryName(iconCompat.getResId());
        } else {
            if (type != 4) {
                return null;
            }
            iconSource = IconSource.ContentUri;
            resourceEntryName = iconCompat.getUri().toString();
        }
        HashMap hashMap = new HashMap();
        hashMap.put("source", Integer.valueOf(iconSource.ordinal()));
        hashMap.put("data", resourceEntryName);
        return hashMap;
    }

    private void getNotificationChannels(MethodChannel.Result result) {
        try {
            List<NotificationChannel> notificationChannels = getNotificationManager(this.applicationContext).getNotificationChannels();
            ArrayList arrayList = new ArrayList();
            Iterator<NotificationChannel> it = notificationChannels.iterator();
            while (it.hasNext()) {
                arrayList.add(getMappedNotificationChannel(it.next()));
            }
            result.success(arrayList);
        } catch (Throwable th) {
            result.error(GET_NOTIFICATION_CHANNELS_ERROR_CODE, th.getMessage(), Log.getStackTraceString(th));
        }
    }

    private HashMap<String, Object> getMappedNotificationChannel(NotificationChannel notificationChannel) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (Build.VERSION.SDK_INT >= 26) {
            hashMap.put("id", notificationChannel.getId());
            hashMap.put("name", notificationChannel.getName());
            hashMap.put("description", notificationChannel.getDescription());
            hashMap.put("groupId", notificationChannel.getGroup());
            hashMap.put("showBadge", Boolean.valueOf(notificationChannel.canShowBadge()));
            hashMap.put("importance", Integer.valueOf(notificationChannel.getImportance()));
            Uri sound = notificationChannel.getSound();
            if (sound == null) {
                hashMap.put("sound", null);
                hashMap.put("playSound", false);
            } else {
                hashMap.put("playSound", true);
                List asList = Arrays.asList(SoundSource.values());
                if (sound.getScheme().equals("android.resource")) {
                    String[] split = sound.toString().split(RemoteSettings.FORWARD_SLASH_STRING);
                    String str = split[split.length - 1];
                    Integer tryParseInt = tryParseInt(str);
                    if (tryParseInt == null) {
                        hashMap.put("soundSource", Integer.valueOf(asList.indexOf(SoundSource.RawResource)));
                        hashMap.put("sound", str);
                    } else {
                        String resourceEntryName = this.applicationContext.getResources().getResourceEntryName(tryParseInt.intValue());
                        if (resourceEntryName != null) {
                            hashMap.put("soundSource", Integer.valueOf(asList.indexOf(SoundSource.RawResource)));
                            hashMap.put("sound", resourceEntryName);
                        }
                    }
                } else {
                    hashMap.put("soundSource", Integer.valueOf(asList.indexOf(SoundSource.Uri)));
                    hashMap.put("sound", sound.toString());
                }
            }
            hashMap.put("enableVibration", Boolean.valueOf(notificationChannel.shouldVibrate()));
            hashMap.put("vibrationPattern", notificationChannel.getVibrationPattern());
            hashMap.put("enableLights", Boolean.valueOf(notificationChannel.shouldShowLights()));
            hashMap.put("ledColor", Integer.valueOf(notificationChannel.getLightColor()));
            AudioAttributes audioAttributes = notificationChannel.getAudioAttributes();
            hashMap.put("audioAttributesUsage", Integer.valueOf(audioAttributes == null ? 5 : audioAttributes.getUsage()));
        }
        return hashMap;
    }

    private Integer tryParseInt(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private void startForegroundService(MethodCall methodCall, MethodChannel.Result result) {
        Map<String, Object> map = (Map) methodCall.argument("notificationData");
        Integer num = (Integer) methodCall.argument("startType");
        ArrayList arrayList = (ArrayList) methodCall.argument("foregroundServiceTypes");
        if (arrayList != null && arrayList.size() == 0) {
            result.error("ARGUMENT_ERROR", "If foregroundServiceTypes is non-null it must not be empty!", null);
            return;
        }
        if (map != null && num != null) {
            NotificationDetails extractNotificationDetails = extractNotificationDetails(result, map);
            if (extractNotificationDetails != null) {
                if (extractNotificationDetails.f63id.intValue() != 0) {
                    ForegroundServiceStartParameter foregroundServiceStartParameter = new ForegroundServiceStartParameter(extractNotificationDetails, num.intValue(), arrayList);
                    Intent intent = new Intent(this.applicationContext, (Class<?>) ForegroundService.class);
                    intent.putExtra(ForegroundServiceStartParameter.EXTRA, foregroundServiceStartParameter);
                    ContextCompat.startForegroundService(this.applicationContext, intent);
                    result.success(null);
                    return;
                }
                result.error("ARGUMENT_ERROR", "The id of the notification for a foreground service must not be 0!", null);
                return;
            }
            return;
        }
        result.error("ARGUMENT_ERROR", "An argument passed to startForegroundService was null!", null);
    }

    private void stopForegroundService(MethodChannel.Result result) {
        this.applicationContext.stopService(new Intent(this.applicationContext, (Class<?>) ForegroundService.class));
        result.success(null);
    }

    private void areNotificationsEnabled(MethodChannel.Result result) {
        result.success(Boolean.valueOf(getNotificationManager(this.applicationContext).areNotificationsEnabled()));
    }

    private void setCanScheduleExactNotifications(MethodChannel.Result result) {
        if (Build.VERSION.SDK_INT < 31) {
            result.success(true);
        } else {
            result.success(Boolean.valueOf(getAlarmManager(this.applicationContext).canScheduleExactAlarms()));
        }
    }

    @Override // io.flutter.plugin.common.PluginRegistry.ActivityResultListener
    public boolean onActivityResult(int i, int i2, Intent intent) {
        if (i != 1 && i != 2 && i != 3) {
            return false;
        }
        if (this.permissionRequestProgress == PermissionRequestProgress.RequestingExactAlarmsPermission && i == 2 && Build.VERSION.SDK_INT >= 31) {
            this.callback.complete(getAlarmManager(this.applicationContext).canScheduleExactAlarms());
            this.permissionRequestProgress = PermissionRequestProgress.None;
        }
        if (this.permissionRequestProgress == PermissionRequestProgress.RequestingFullScreenIntentPermission && i == 3 && Build.VERSION.SDK_INT >= 34) {
            this.callback.complete(((NotificationManager) this.applicationContext.getSystemService("notification")).canUseFullScreenIntent());
            this.permissionRequestProgress = PermissionRequestProgress.None;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class PluginException extends RuntimeException {
        public final String code;

        PluginException(String str, String str2) {
            super(str2);
            this.code = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ExactAlarmPermissionException extends PluginException {
        public ExactAlarmPermissionException() {
            super(FlutterLocalNotificationsPlugin.EXACT_ALARMS_PERMISSION_ERROR_CODE, "Exact alarms are not permitted");
        }
    }
}
