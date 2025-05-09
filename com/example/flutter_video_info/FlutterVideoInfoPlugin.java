package com.example.flutter_video_info;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.firebase.database.Constants;
import io.sentry.protocol.Device;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FlutterVideoInfoPlugin implements FlutterPlugin, MethodChannel.MethodCallHandler {
    public static Context context;
    private String chName = "flutter_video_info";

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_video_info").setMethodCallHandler(new FlutterVideoInfoPlugin());
        context = flutterPluginBinding.getApplicationContext();
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        new MethodChannel(registrar.messenger(), "flutter_video_info").setMethodCallHandler(new FlutterVideoInfoPlugin());
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        if (methodCall.method.equals("getVidInfo")) {
            result.success(getVidInfo((String) methodCall.argument(Constants.PATH)));
        } else {
            result.notImplemented();
        }
    }

    String getVidInfo(String str) {
        double d;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        File file = new File(str);
        boolean exists = file.exists();
        String str10 = "";
        if (exists) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                mediaMetadataRetriever.setDataSource(context, Uri.fromFile(file));
            } catch (Exception e) {
                e.printStackTrace();
            }
            str3 = getData(3, mediaMetadataRetriever);
            str4 = getData(5, mediaMetadataRetriever);
            try {
                str4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new SimpleDateFormat("yyyyMMdd'T'HHmmss.SSS", Locale.getDefault()).parse(str4));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            String data = getData(12, mediaMetadataRetriever);
            str5 = getData(23, mediaMetadataRetriever);
            str6 = getData(25, mediaMetadataRetriever);
            str7 = getData(9, mediaMetadataRetriever);
            str8 = getData(18, mediaMetadataRetriever);
            str9 = getData(19, mediaMetadataRetriever);
            d = file.length();
            str2 = Build.VERSION.SDK_INT >= 17 ? getData(24, mediaMetadataRetriever) : null;
            try {
                mediaMetadataRetriever.release();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            str10 = data;
        } else {
            d = 0.0d;
            str2 = "";
            str3 = str2;
            str4 = str3;
            str5 = str4;
            str6 = str5;
            str7 = str6;
            str8 = str7;
            str9 = str8;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.PATH, str);
            jSONObject.put("mimetype", str10);
            jSONObject.put("author", str3);
            jSONObject.put(WorkflowModule.Properties.Section.Component.Type.DATE, str4);
            jSONObject.put("width", str8);
            jSONObject.put("height", str9);
            jSONObject.put(FirebaseAnalytics.Param.LOCATION, str5);
            jSONObject.put("framerate", str6);
            jSONObject.put("duration", str7);
            jSONObject.put("filesize", d);
            jSONObject.put(Device.JsonKeys.ORIENTATION, str2);
            jSONObject.put("isfileexist", exists);
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return jSONObject.toString();
    }

    String getData(int i, MediaMetadataRetriever mediaMetadataRetriever) {
        try {
            return mediaMetadataRetriever.extractMetadata(i);
        } catch (Exception unused) {
            return null;
        }
    }
}
