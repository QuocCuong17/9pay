package io.flutter.plugins.firebase.dynamiclinks;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.dynamiclinks.ShortDynamicLink;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.firebase.core.FlutterFirebasePlugin;
import io.flutter.plugins.firebase.core.FlutterFirebasePluginRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
public class FlutterFirebaseDynamicLinksPlugin implements FlutterFirebasePlugin, FlutterPlugin, ActivityAware, MethodChannel.MethodCallHandler, PluginRegistry.NewIntentListener {
    private static final String METHOD_CHANNEL_NAME = "plugins.flutter.io/firebase_dynamic_links";
    private static final String TAG = "FLTFirebaseDynamicLinks";
    private final AtomicReference<Activity> activity = new AtomicReference<>(null);
    private Map<String, Object> cachedDynamicLinkData;
    private Map<String, Object> cachedDynamicLinkException;
    private MethodChannel channel;

    private void initInstance(BinaryMessenger binaryMessenger) {
        MethodChannel methodChannel = new MethodChannel(binaryMessenger, METHOD_CHANNEL_NAME);
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
        FlutterFirebasePluginRegistry.registerPlugin(METHOD_CHANNEL_NAME, this);
        checkForCachedData();
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        initInstance(flutterPluginBinding.getBinaryMessenger());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.channel.setMethodCallHandler(null);
        this.channel = null;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        this.activity.set(activityPluginBinding.getActivity());
        activityPluginBinding.addOnNewIntentListener(this);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        detachToActivity();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        this.activity.set(activityPluginBinding.getActivity());
        activityPluginBinding.addOnNewIntentListener(this);
    }

    private void detachToActivity() {
        this.activity.set(null);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        detachToActivity();
    }

    static FirebaseDynamicLinks getDynamicLinkInstance(Map<String, Object> map) {
        String str;
        if (map != null && (str = (String) map.get("appName")) != null) {
            return FirebaseDynamicLinks.getInstance(FirebaseApp.getInstance(str));
        }
        return FirebaseDynamicLinks.getInstance();
    }

    @Override // io.flutter.plugin.common.PluginRegistry.NewIntentListener
    public boolean onNewIntent(Intent intent) {
        getDynamicLinkInstance(null).getDynamicLink(intent).addOnSuccessListener(new OnSuccessListener() { // from class: io.flutter.plugins.firebase.dynamiclinks.FlutterFirebaseDynamicLinksPlugin$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                FlutterFirebaseDynamicLinksPlugin.this.m1065x79fca916((PendingDynamicLinkData) obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: io.flutter.plugins.firebase.dynamiclinks.FlutterFirebaseDynamicLinksPlugin$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                FlutterFirebaseDynamicLinksPlugin.this.m1066xbc13d675(exc);
            }
        });
        return false;
    }

    /* renamed from: lambda$onNewIntent$0$io-flutter-plugins-firebase-dynamiclinks-FlutterFirebaseDynamicLinksPlugin, reason: not valid java name */
    public /* synthetic */ void m1065x79fca916(PendingDynamicLinkData pendingDynamicLinkData) {
        Map<String, Object> mapFromPendingDynamicLinkData = Utils.getMapFromPendingDynamicLinkData(pendingDynamicLinkData);
        if (mapFromPendingDynamicLinkData != null) {
            MethodChannel methodChannel = this.channel;
            if (methodChannel != null) {
                methodChannel.invokeMethod("FirebaseDynamicLink#onLinkSuccess", mapFromPendingDynamicLinkData);
            } else {
                this.cachedDynamicLinkData = mapFromPendingDynamicLinkData;
            }
        }
    }

    /* renamed from: lambda$onNewIntent$1$io-flutter-plugins-firebase-dynamiclinks-FlutterFirebaseDynamicLinksPlugin, reason: not valid java name */
    public /* synthetic */ void m1066xbc13d675(Exception exc) {
        Map<String, Object> exceptionDetails = Utils.getExceptionDetails(exc);
        MethodChannel methodChannel = this.channel;
        if (methodChannel != null) {
            methodChannel.invokeMethod("FirebaseDynamicLink#onLinkError", exceptionDetails);
        } else {
            this.cachedDynamicLinkException = exceptionDetails;
        }
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, final MethodChannel.Result result) {
        Task<Map<String, Object>> dynamicLink;
        FirebaseDynamicLinks dynamicLinkInstance = getDynamicLinkInstance((Map) methodCall.arguments());
        String str = methodCall.method;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2071055899:
                if (str.equals("FirebaseDynamicLinks#getDynamicLink")) {
                    c = 0;
                    break;
                }
                break;
            case -1769644534:
                if (str.equals("FirebaseDynamicLinks#buildShortLink")) {
                    c = 1;
                    break;
                }
                break;
            case -579002774:
                if (str.equals("FirebaseDynamicLinks#getInitialLink")) {
                    c = 2;
                    break;
                }
                break;
            case 1658258438:
                if (str.equals("FirebaseDynamicLinks#buildLink")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 2:
                dynamicLink = getDynamicLink(dynamicLinkInstance, (String) methodCall.argument("url"));
                break;
            case 1:
                Map<String, Object> map = (Map) methodCall.arguments();
                Objects.requireNonNull(map);
                dynamicLink = buildShortLink(map);
                break;
            case 3:
                result.success(buildLink((Map) methodCall.arguments()));
                return;
            default:
                result.notImplemented();
                return;
        }
        dynamicLink.addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.dynamiclinks.FlutterFirebaseDynamicLinksPlugin$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FlutterFirebaseDynamicLinksPlugin.lambda$onMethodCall$2(MethodChannel.Result.this, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onMethodCall$2(MethodChannel.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success(task.getResult());
        } else {
            Exception exception = task.getException();
            result.error(Constants.DEFAULT_ERROR_CODE, exception != null ? exception.getMessage() : null, Utils.getExceptionDetails(exception));
        }
    }

    private void checkForCachedData() {
        Map<String, Object> map = this.cachedDynamicLinkData;
        if (map != null) {
            this.channel.invokeMethod("FirebaseDynamicLink#onLinkSuccess", map);
            this.cachedDynamicLinkData = null;
        }
        Map<String, Object> map2 = this.cachedDynamicLinkException;
        if (map2 != null) {
            this.channel.invokeMethod("FirebaseDynamicLink#onLinkError", map2);
            this.cachedDynamicLinkException = null;
        }
    }

    private String buildLink(Map<String, Object> map) {
        return setupParameters(map).buildDynamicLink().getUri().toString();
    }

    private Task<Map<String, Object>> buildShortLink(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.dynamiclinks.FlutterFirebaseDynamicLinksPlugin$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseDynamicLinksPlugin.this.m1063x1cfda2ee(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$buildShortLink$3$io-flutter-plugins-firebase-dynamiclinks-FlutterFirebaseDynamicLinksPlugin, reason: not valid java name */
    public /* synthetic */ void m1063x1cfda2ee(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            DynamicLink.Builder builder = setupParameters(map);
            String str = (String) map.get("longDynamicLink");
            if (str != null) {
                builder.setLongLink(Uri.parse(str));
            }
            Integer num = 1;
            Integer num2 = (Integer) map.get("shortLinkType");
            if (num2 != null) {
                int intValue = num2.intValue();
                if (intValue == 0) {
                    num = 1;
                } else if (intValue == 1) {
                    num = 2;
                }
            }
            HashMap hashMap = new HashMap();
            ShortDynamicLink shortDynamicLink = (ShortDynamicLink) Tasks.await(builder.buildShortDynamicLink(num.intValue()));
            ArrayList arrayList = new ArrayList();
            Iterator<? extends ShortDynamicLink.Warning> it = shortDynamicLink.getWarnings().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getMessage());
            }
            hashMap.put("url", shortDynamicLink.getShortLink().toString());
            hashMap.put("warnings", arrayList);
            hashMap.put("previewLink", shortDynamicLink.getPreviewLink().toString());
            taskCompletionSource.setResult(hashMap);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> getDynamicLink(final FirebaseDynamicLinks firebaseDynamicLinks, final String str) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.dynamiclinks.FlutterFirebaseDynamicLinksPlugin$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseDynamicLinksPlugin.this.m1064x3873b052(str, firebaseDynamicLinks, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$getDynamicLink$4$io-flutter-plugins-firebase-dynamiclinks-FlutterFirebaseDynamicLinksPlugin, reason: not valid java name */
    public /* synthetic */ void m1064x3873b052(String str, FirebaseDynamicLinks firebaseDynamicLinks, TaskCompletionSource taskCompletionSource) {
        PendingDynamicLinkData pendingDynamicLinkData;
        try {
            if (str != null) {
                pendingDynamicLinkData = (PendingDynamicLinkData) Tasks.await(firebaseDynamicLinks.getDynamicLink(Uri.parse(str)));
            } else {
                if (this.activity.get() != null && this.activity.get().getIntent() != null && !this.activity.get().getIntent().getBooleanExtra("flutterfire-used-link", false)) {
                    this.activity.get().getIntent().putExtra("flutterfire-used-link", true);
                    pendingDynamicLinkData = (PendingDynamicLinkData) Tasks.await(firebaseDynamicLinks.getDynamicLink(this.activity.get().getIntent()));
                }
                taskCompletionSource.setResult(null);
                return;
            }
            taskCompletionSource.setResult(Utils.getMapFromPendingDynamicLinkData(pendingDynamicLinkData));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private DynamicLink.Builder setupParameters(Map<String, Object> map) {
        DynamicLink.Builder createDynamicLink = getDynamicLinkInstance(map).createDynamicLink();
        Object obj = map.get("uriPrefix");
        Objects.requireNonNull(obj);
        String str = (String) map.get("link");
        createDynamicLink.setDomainUriPrefix((String) obj);
        createDynamicLink.setLink(Uri.parse(str));
        Map map2 = (Map) map.get("androidParameters");
        if (map2 != null) {
            String str2 = (String) valueFor("packageName", map2);
            String str3 = (String) valueFor("fallbackUrl", map2);
            Integer num = (Integer) valueFor("minimumVersion", map2);
            DynamicLink.AndroidParameters.Builder builder = new DynamicLink.AndroidParameters.Builder(str2);
            if (str3 != null) {
                builder.setFallbackUrl(Uri.parse(str3));
            }
            if (num != null) {
                builder.setMinimumVersion(num.intValue());
            }
            createDynamicLink.setAndroidParameters(builder.build());
        }
        Map map3 = (Map) map.get("googleAnalyticsParameters");
        if (map3 != null) {
            String str4 = (String) valueFor("campaign", map3);
            String str5 = (String) valueFor(FirebaseAnalytics.Param.CONTENT, map3);
            String str6 = (String) valueFor("medium", map3);
            String str7 = (String) valueFor("source", map3);
            String str8 = (String) valueFor(FirebaseAnalytics.Param.TERM, map3);
            DynamicLink.GoogleAnalyticsParameters.Builder builder2 = new DynamicLink.GoogleAnalyticsParameters.Builder();
            if (str4 != null) {
                builder2.setCampaign(str4);
            }
            if (str5 != null) {
                builder2.setContent(str5);
            }
            if (str6 != null) {
                builder2.setMedium(str6);
            }
            if (str7 != null) {
                builder2.setSource(str7);
            }
            if (str8 != null) {
                builder2.setTerm(str8);
            }
            createDynamicLink.setGoogleAnalyticsParameters(builder2.build());
        }
        Map map4 = (Map) map.get("iosParameters");
        if (map4 != null) {
            String str9 = (String) valueFor("bundleId", map4);
            String str10 = (String) valueFor("appStoreId", map4);
            String str11 = (String) valueFor("customScheme", map4);
            String str12 = (String) valueFor("fallbackUrl", map4);
            String str13 = (String) valueFor("ipadBundleId", map4);
            String str14 = (String) valueFor("ipadFallbackUrl", map4);
            String str15 = (String) valueFor("minimumVersion", map4);
            DynamicLink.IosParameters.Builder builder3 = new DynamicLink.IosParameters.Builder(str9);
            if (str10 != null) {
                builder3.setAppStoreId(str10);
            }
            if (str11 != null) {
                builder3.setCustomScheme(str11);
            }
            if (str12 != null) {
                builder3.setFallbackUrl(Uri.parse(str12));
            }
            if (str13 != null) {
                builder3.setIpadBundleId(str13);
            }
            if (str14 != null) {
                builder3.setIpadFallbackUrl(Uri.parse(str14));
            }
            if (str15 != null) {
                builder3.setMinimumVersion(str15);
            }
            createDynamicLink.setIosParameters(builder3.build());
        }
        Map map5 = (Map) map.get("itunesConnectAnalyticsParameters");
        if (map5 != null) {
            String str16 = (String) valueFor("affiliateToken", map5);
            String str17 = (String) valueFor("campaignToken", map5);
            String str18 = (String) valueFor("providerToken", map5);
            DynamicLink.ItunesConnectAnalyticsParameters.Builder builder4 = new DynamicLink.ItunesConnectAnalyticsParameters.Builder();
            if (str16 != null) {
                builder4.setAffiliateToken(str16);
            }
            if (str17 != null) {
                builder4.setCampaignToken(str17);
            }
            if (str18 != null) {
                builder4.setProviderToken(str18);
            }
            createDynamicLink.setItunesConnectAnalyticsParameters(builder4.build());
        }
        Map map6 = (Map) map.get("navigationInfoParameters");
        if (map6 != null) {
            Boolean bool = (Boolean) valueFor("forcedRedirectEnabled", map6);
            DynamicLink.NavigationInfoParameters.Builder builder5 = new DynamicLink.NavigationInfoParameters.Builder();
            if (bool != null) {
                builder5.setForcedRedirectEnabled(bool.booleanValue());
            }
            createDynamicLink.setNavigationInfoParameters(builder5.build());
        }
        Map map7 = (Map) map.get("socialMetaTagParameters");
        if (map7 != null) {
            String str19 = (String) valueFor("description", map7);
            String str20 = (String) valueFor("imageUrl", map7);
            String str21 = (String) valueFor("title", map7);
            DynamicLink.SocialMetaTagParameters.Builder builder6 = new DynamicLink.SocialMetaTagParameters.Builder();
            if (str19 != null) {
                builder6.setDescription(str19);
            }
            if (str20 != null) {
                builder6.setImageUrl(Uri.parse(str20));
            }
            if (str21 != null) {
                builder6.setTitle(str21);
            }
            createDynamicLink.setSocialMetaTagParameters(builder6.build());
        }
        return createDynamicLink;
    }

    private static <T> T valueFor(String str, Map<String, Object> map) {
        return (T) map.get(str);
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Map<String, Object>> getPluginConstantsForFirebaseApp(FirebaseApp firebaseApp) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.dynamiclinks.FlutterFirebaseDynamicLinksPlugin$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                TaskCompletionSource.this.setResult(null);
            }
        });
        return taskCompletionSource.getTask();
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Void> didReinitializeFirebaseCore() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.dynamiclinks.FlutterFirebaseDynamicLinksPlugin$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                TaskCompletionSource.this.setResult(null);
            }
        });
        return taskCompletionSource.getTask();
    }
}
