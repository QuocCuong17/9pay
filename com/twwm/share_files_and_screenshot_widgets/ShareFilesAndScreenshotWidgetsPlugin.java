package com.twwm.share_files_and_screenshot_widgets;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import androidx.core.content.FileProvider;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class ShareFilesAndScreenshotWidgetsPlugin implements FlutterPlugin, MethodChannel.MethodCallHandler, ActivityAware {
    private final String PROVIDER_AUTH_EXT = ".fileprovider.share_files_and_screenshot_widgets";
    Context activeContext;
    MethodChannel channel;

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        if (methodCall.method.equals("text")) {
            text(methodCall.arguments);
        }
        if (methodCall.method.equals("file")) {
            file(methodCall.arguments);
        }
        if (methodCall.method.equals("files")) {
            files(methodCall.arguments);
        }
    }

    private void text(Object obj) {
        HashMap hashMap = (HashMap) obj;
        String str = (String) hashMap.get("title");
        String str2 = (String) hashMap.get("text");
        String str3 = (String) hashMap.get("mimeType");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.addFlags(2);
        intent.addFlags(2);
        intent.setType(str3);
        intent.putExtra("android.intent.extra.TEXT", str2);
        this.activeContext.startActivity(Intent.createChooser(intent, str));
    }

    private void file(Object obj) {
        HashMap hashMap = (HashMap) obj;
        String str = (String) hashMap.get("title");
        String str2 = (String) hashMap.get("name");
        String str3 = (String) hashMap.get("mimeType");
        String str4 = (String) hashMap.get("text");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(str3);
        Uri uriForFile = FileProvider.getUriForFile(this.activeContext, this.activeContext.getPackageName() + ".fileprovider.share_files_and_screenshot_widgets", new File(this.activeContext.getCacheDir(), str2));
        intent.addFlags(2);
        intent.addFlags(2);
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        if (!str4.isEmpty()) {
            intent.putExtra("android.intent.extra.TEXT", str4);
        }
        this.activeContext.startActivity(Intent.createChooser(intent, str));
    }

    private void files(Object obj) {
        HashMap hashMap = (HashMap) obj;
        String str = (String) hashMap.get("title");
        ArrayList arrayList = (ArrayList) hashMap.get("names");
        String str2 = (String) hashMap.get("mimeType");
        String str3 = (String) hashMap.get("text");
        Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
        intent.addFlags(2);
        intent.addFlags(2);
        intent.setType(str2);
        ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(FileProvider.getUriForFile(this.activeContext, this.activeContext.getPackageName() + ".fileprovider.share_files_and_screenshot_widgets", new File(this.activeContext.getCacheDir(), (String) it.next())));
        }
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList2);
        if (!str3.isEmpty()) {
            intent.putExtra("android.intent.extra.TEXT", str3);
        }
        this.activeContext.startActivity(Intent.createChooser(intent, str));
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.activeContext = flutterPluginBinding.getApplicationContext();
        MethodChannel methodChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "channel:share_files_and_screenshot_widgets");
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.channel.setMethodCallHandler(null);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        this.activeContext = activityPluginBinding.getActivity();
    }
}
