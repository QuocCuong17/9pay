package com.cygnati.social_share_plugin;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.internal.ShareConstants;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.tekartik.sqflite.Constant;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.firebase.database.Constants;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: SocialSharePlugin.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 02\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u00010B\u0005¢\u0006\u0002\u0010\u0005J\u001c\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0002J\u001c\u0010\u0011\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000fH\u0002J\u001c\u0010\u0014\u001a\u00020\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000fH\u0002J\"\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\rH\u0016J\b\u0010$\u001a\u00020\rH\u0016J\u0010\u0010%\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\"H\u0016J\u0018\u0010&\u001a\u00020\r2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010,\u001a\u00020\r2\u0006\u0010-\u001a\u00020\u000fH\u0002J\u001c\u0010.\u001a\u00020\r2\b\u0010/\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000fH\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/cygnati/social_share_plugin/SocialSharePlugin;", "Lio/flutter/embedding/engine/plugins/FlutterPlugin;", "Lio/flutter/embedding/engine/plugins/activity/ActivityAware;", "Lio/flutter/plugin/common/MethodChannel$MethodCallHandler;", "Lio/flutter/plugin/common/PluginRegistry$ActivityResultListener;", "()V", "activity", "Landroid/app/Activity;", "callbackManager", "Lcom/facebook/CallbackManager;", "channel", "Lio/flutter/plugin/common/MethodChannel;", "facebookShare", "", ShareConstants.FEED_CAPTION_PARAM, "", "mediaPath", "facebookShareLink", ShareConstants.WEB_DIALOG_PARAM_QUOTE, "url", "instagramShare", "type", "imagePath", "onActivityResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onAttachedToActivity", "binding", "Lio/flutter/embedding/engine/plugins/activity/ActivityPluginBinding;", "onAttachedToEngine", "Lio/flutter/embedding/engine/plugins/FlutterPlugin$FlutterPluginBinding;", "onDetachedFromActivity", "onDetachedFromActivityForConfigChanges", "onDetachedFromEngine", "onMethodCall", NotificationCompat.CATEGORY_CALL, "Lio/flutter/plugin/common/MethodCall;", Constant.PARAM_RESULT, "Lio/flutter/plugin/common/MethodChannel$Result;", "onReattachedToActivityForConfigChanges", "openPlayStore", "packageName", "twitterShareLink", "text", "Companion", "social_share_plugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SocialSharePlugin implements FlutterPlugin, ActivityAware, MethodChannel.MethodCallHandler, PluginRegistry.ActivityResultListener {
    private static final String FACEBOOK_PACKAGE_NAME = "com.facebook.katana";
    private static final String INSTAGRAM_PACKAGE_NAME = "com.instagram.android";
    private static final int INSTAGRAM_REQUEST_CODE = 49347;
    private static final String TWITTER_PACKAGE_NAME = "com.twitter.android";
    private static final int TWITTER_REQUEST_CODE = 49358;
    private Activity activity;
    private final CallbackManager callbackManager = CallbackManager.Factory.create();
    private MethodChannel channel;

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        MethodChannel methodChannel = new MethodChannel(binding.getBinaryMessenger(), "social_share_plugin");
        this.channel = methodChannel;
        Intrinsics.checkNotNull(methodChannel);
        methodChannel.setMethodCallHandler(this);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        binding.addActivityResultListener(this);
        this.activity = binding.getActivity();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        binding.addActivityResultListener(this);
        this.activity = binding.getActivity();
    }

    @Override // io.flutter.plugin.common.PluginRegistry.ActivityResultListener
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TWITTER_REQUEST_CODE) {
            if (resultCode == -1) {
                Log.d("SocialSharePlugin", "Twitter share done.");
                MethodChannel methodChannel = this.channel;
                Intrinsics.checkNotNull(methodChannel);
                methodChannel.invokeMethod("onSuccess", null);
            } else if (resultCode == 0) {
                Log.d("SocialSharePlugin", "Twitter cancelled.");
                MethodChannel methodChannel2 = this.channel;
                Intrinsics.checkNotNull(methodChannel2);
                methodChannel2.invokeMethod("onCancel", null);
            }
            return true;
        }
        if (requestCode != INSTAGRAM_REQUEST_CODE) {
            return this.callbackManager.onActivityResult(requestCode, resultCode, data);
        }
        if (resultCode == -1) {
            Log.d("SocialSharePlugin", "Instagram share done.");
            MethodChannel methodChannel3 = this.channel;
            Intrinsics.checkNotNull(methodChannel3);
            methodChannel3.invokeMethod("onSuccess", null);
        } else {
            Log.d("SocialSharePlugin", "Instagram share failed.");
            MethodChannel methodChannel4 = this.channel;
            Intrinsics.checkNotNull(methodChannel4);
            methodChannel4.invokeMethod("onCancel", null);
        }
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0027. Please report as an issue. */
    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(result, "result");
        Activity activity = this.activity;
        Intrinsics.checkNotNull(activity);
        PackageManager packageManager = activity.getPackageManager();
        String str = call.method;
        if (str != null) {
            switch (str.hashCode()) {
                case -2092608930:
                    if (str.equals("shareToFeedFacebook")) {
                        try {
                            packageManager.getPackageInfo("com.facebook.katana", 1);
                            facebookShare((String) call.argument(ShareConstants.FEED_CAPTION_PARAM), (String) call.argument(Constants.PATH));
                            result.success(true);
                            return;
                        } catch (PackageManager.NameNotFoundException unused) {
                            openPlayStore("com.facebook.katana");
                            result.success(false);
                            return;
                        }
                    }
                    break;
                case -509798536:
                    if (str.equals("shareToFeedFacebookLink")) {
                        try {
                            packageManager.getPackageInfo("com.facebook.katana", 1);
                            facebookShareLink((String) call.argument(ShareConstants.WEB_DIALOG_PARAM_QUOTE), (String) call.argument("url"));
                            result.success(true);
                            return;
                        } catch (PackageManager.NameNotFoundException unused2) {
                            openPlayStore("com.facebook.katana");
                            result.success(false);
                            return;
                        }
                    }
                    break;
                case 979996147:
                    if (str.equals("shareToTwitterLink")) {
                        try {
                            packageManager.getPackageInfo(TWITTER_PACKAGE_NAME, 1);
                            twitterShareLink((String) call.argument("text"), (String) call.argument("url"));
                            result.success(true);
                            return;
                        } catch (PackageManager.NameNotFoundException unused3) {
                            openPlayStore(TWITTER_PACKAGE_NAME);
                            result.success(false);
                            return;
                        }
                    }
                    break;
                case 1351369498:
                    if (str.equals("shareToFeedInstagram")) {
                        try {
                            packageManager.getPackageInfo(INSTAGRAM_PACKAGE_NAME, 1);
                            instagramShare((String) call.argument("type"), (String) call.argument(Constants.PATH));
                            result.success(true);
                            return;
                        } catch (PackageManager.NameNotFoundException unused4) {
                            openPlayStore(INSTAGRAM_PACKAGE_NAME);
                            result.success(false);
                            return;
                        }
                    }
                    break;
                case 1385449135:
                    if (str.equals(Constant.METHOD_GET_PLATFORM_VERSION)) {
                        result.success("Android " + Build.VERSION.RELEASE);
                        return;
                    }
                    break;
            }
        }
        result.notImplemented();
    }

    private final void openPlayStore(String packageName) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName));
            Activity activity = this.activity;
            Intrinsics.checkNotNull(activity);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + packageName));
            Activity activity2 = this.activity;
            Intrinsics.checkNotNull(activity2);
            activity2.startActivity(intent2);
        }
    }

    private final void instagramShare(String type, String imagePath) {
        Intrinsics.checkNotNull(imagePath);
        File file = new File(imagePath);
        Activity activity = this.activity;
        Intrinsics.checkNotNull(activity);
        StringBuilder sb = new StringBuilder();
        Activity activity2 = this.activity;
        Intrinsics.checkNotNull(activity2);
        sb.append(activity2.getPackageName());
        sb.append(".social.share.fileprovider");
        Uri uriForFile = FileProvider.getUriForFile(activity, sb.toString(), file);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(type);
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        intent.setPackage(INSTAGRAM_PACKAGE_NAME);
        intent.addFlags(1);
        Intent createChooser = Intent.createChooser(intent, "Share to");
        Activity activity3 = this.activity;
        Intrinsics.checkNotNull(activity3);
        List<ResolveInfo> queryIntentActivities = activity3.getPackageManager().queryIntentActivities(createChooser, 65536);
        Intrinsics.checkNotNullExpressionValue(queryIntentActivities, "activity!!.packageManage…er.MATCH_DEFAULT_ONLY\n\t\t)");
        Iterator<ResolveInfo> it = queryIntentActivities.iterator();
        while (it.hasNext()) {
            String str = it.next().activityInfo.packageName;
            Activity activity4 = this.activity;
            Intrinsics.checkNotNull(activity4);
            activity4.grantUriPermission(str, uriForFile, 1);
        }
        Activity activity5 = this.activity;
        Intrinsics.checkNotNull(activity5);
        activity5.startActivityForResult(createChooser, INSTAGRAM_REQUEST_CODE);
    }

    private final void facebookShare(String caption, String mediaPath) {
        Intrinsics.checkNotNull(mediaPath);
        File file = new File(mediaPath);
        Activity activity = this.activity;
        Intrinsics.checkNotNull(activity);
        StringBuilder sb = new StringBuilder();
        Activity activity2 = this.activity;
        Intrinsics.checkNotNull(activity2);
        sb.append(activity2.getPackageName());
        sb.append(".social.share.fileprovider");
        SharePhotoContent build = new SharePhotoContent.Builder().addPhoto(new SharePhoto.Builder().setImageUrl(FileProvider.getUriForFile(activity, sb.toString(), file)).setCaption(caption).build()).build();
        Activity activity3 = this.activity;
        Intrinsics.checkNotNull(activity3);
        ShareDialog shareDialog = new ShareDialog(activity3);
        shareDialog.registerCallback(this.callbackManager, new FacebookCallback<Sharer.Result>() { // from class: com.cygnati.social_share_plugin.SocialSharePlugin$facebookShare$1
            @Override // com.facebook.FacebookCallback
            public void onSuccess(Sharer.Result result) {
                MethodChannel methodChannel;
                Intrinsics.checkNotNullParameter(result, "result");
                methodChannel = SocialSharePlugin.this.channel;
                Intrinsics.checkNotNull(methodChannel);
                methodChannel.invokeMethod("onSuccess", null);
                Log.d("SocialSharePlugin", "Sharing successfully done.");
            }

            @Override // com.facebook.FacebookCallback
            public void onCancel() {
                MethodChannel methodChannel;
                methodChannel = SocialSharePlugin.this.channel;
                Intrinsics.checkNotNull(methodChannel);
                methodChannel.invokeMethod("onCancel", null);
                Log.d("SocialSharePlugin", "Sharing cancelled.");
            }

            @Override // com.facebook.FacebookCallback
            public void onError(FacebookException error) {
                MethodChannel methodChannel;
                Intrinsics.checkNotNullParameter(error, "error");
                methodChannel = SocialSharePlugin.this.channel;
                Intrinsics.checkNotNull(methodChannel);
                methodChannel.invokeMethod("onError", error.getMessage());
                Log.d("SocialSharePlugin", "Sharing error occurred.");
            }
        });
        if (ShareDialog.INSTANCE.canShow(SharePhotoContent.class)) {
            shareDialog.show(build);
        }
    }

    private final void facebookShareLink(String quote, String url) {
        ShareLinkContent build = new ShareLinkContent.Builder().setContentUrl(Uri.parse(url)).setQuote(quote).build();
        Activity activity = this.activity;
        Intrinsics.checkNotNull(activity);
        ShareDialog shareDialog = new ShareDialog(activity);
        shareDialog.registerCallback(this.callbackManager, new FacebookCallback<Sharer.Result>() { // from class: com.cygnati.social_share_plugin.SocialSharePlugin$facebookShareLink$1
            @Override // com.facebook.FacebookCallback
            public void onCancel() {
                MethodChannel methodChannel;
                methodChannel = SocialSharePlugin.this.channel;
                Intrinsics.checkNotNull(methodChannel);
                methodChannel.invokeMethod("onCancel", null);
                Log.d("SocialSharePlugin", "Sharing cancelled.");
            }

            @Override // com.facebook.FacebookCallback
            public void onError(FacebookException error) {
                MethodChannel methodChannel;
                Intrinsics.checkNotNullParameter(error, "error");
                methodChannel = SocialSharePlugin.this.channel;
                Intrinsics.checkNotNull(methodChannel);
                methodChannel.invokeMethod("onError", error.getMessage());
                Log.d("SocialSharePlugin", "Sharing error occurred.");
            }

            @Override // com.facebook.FacebookCallback
            public void onSuccess(Sharer.Result result) {
                MethodChannel methodChannel;
                Intrinsics.checkNotNullParameter(result, "result");
                methodChannel = SocialSharePlugin.this.channel;
                Intrinsics.checkNotNull(methodChannel);
                methodChannel.invokeMethod("onSuccess", null);
                Log.d("SocialSharePlugin", "Sharing successfully done.");
            }
        });
        if (ShareDialog.INSTANCE.canShow(ShareLinkContent.class)) {
            shareDialog.show(build);
        }
    }

    private final void twitterShareLink(String text, String url) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("https://twitter.com/intent/tweet?text=%s&url=%s", Arrays.copyOf(new Object[]{text, url}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        Uri parse = Uri.parse(format);
        Activity activity = this.activity;
        Intrinsics.checkNotNull(activity);
        activity.startActivityForResult(new Intent("android.intent.action.VIEW", parse), TWITTER_REQUEST_CODE);
    }
}
