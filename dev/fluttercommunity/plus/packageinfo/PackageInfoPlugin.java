package dev.fluttercommunity.plus.packageinfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.media3.common.C;
import com.tekartik.sqflite.Constant;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PackageInfoPlugin.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \"2\u00020\u00012\u00020\u0002:\u0001\"B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u000f\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0002\u0010\u0011J\n\u0010\u0012\u001a\u0004\u0018\u00010\tH\u0002J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\u000bH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Ldev/fluttercommunity/plus/packageinfo/PackageInfoPlugin;", "Lio/flutter/plugin/common/MethodChannel$MethodCallHandler;", "Lio/flutter/embedding/engine/plugins/FlutterPlugin;", "()V", "applicationContext", "Landroid/content/Context;", "methodChannel", "Lio/flutter/plugin/common/MethodChannel;", "bytesToHex", "", "bytes", "", "getBuildSignature", "pm", "Landroid/content/pm/PackageManager;", "getInstallTimeMillis", "", "()Ljava/lang/Long;", "getInstallerPackageName", "getLongVersionCode", "info", "Landroid/content/pm/PackageInfo;", "onAttachedToEngine", "", "binding", "Lio/flutter/embedding/engine/plugins/FlutterPlugin$FlutterPluginBinding;", "onDetachedFromEngine", "onMethodCall", NotificationCompat.CATEGORY_CALL, "Lio/flutter/plugin/common/MethodCall;", Constant.PARAM_RESULT, "Lio/flutter/plugin/common/MethodChannel$Result;", "signatureToSha256", "sig", "Companion", "package_info_plus_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class PackageInfoPlugin implements MethodChannel.MethodCallHandler, FlutterPlugin {
    private static final String CHANNEL_NAME = "dev.fluttercommunity.plus/package_info";
    private Context applicationContext;
    private MethodChannel methodChannel;

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        this.applicationContext = binding.getApplicationContext();
        MethodChannel methodChannel = new MethodChannel(binding.getBinaryMessenger(), CHANNEL_NAME);
        this.methodChannel = methodChannel;
        Intrinsics.checkNotNull(methodChannel);
        methodChannel.setMethodCallHandler(this);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        this.applicationContext = null;
        MethodChannel methodChannel = this.methodChannel;
        Intrinsics.checkNotNull(methodChannel);
        methodChannel.setMethodCallHandler(null);
        this.methodChannel = null;
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        String str;
        CharSequence loadLabel;
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(result, "result");
        try {
            if (Intrinsics.areEqual(call.method, "getAll")) {
                Context context = this.applicationContext;
                Intrinsics.checkNotNull(context);
                PackageManager packageManager = context.getPackageManager();
                Context context2 = this.applicationContext;
                Intrinsics.checkNotNull(context2);
                PackageInfo info = packageManager.getPackageInfo(context2.getPackageName(), 0);
                Intrinsics.checkNotNullExpressionValue(packageManager, "packageManager");
                String buildSignature = getBuildSignature(packageManager);
                String installerPackageName = getInstallerPackageName();
                Long installTimeMillis = getInstallTimeMillis();
                HashMap hashMap = new HashMap();
                ApplicationInfo applicationInfo = info.applicationInfo;
                String str2 = "";
                if (applicationInfo == null || (loadLabel = applicationInfo.loadLabel(packageManager)) == null || (str = loadLabel.toString()) == null) {
                    str = "";
                }
                hashMap.put("appName", str);
                Context context3 = this.applicationContext;
                Intrinsics.checkNotNull(context3);
                hashMap.put("packageName", context3.getPackageName());
                String str3 = info.versionName;
                if (str3 != null) {
                    Intrinsics.checkNotNullExpressionValue(str3, "info.versionName ?: \"\"");
                    str2 = str3;
                }
                hashMap.put("version", str2);
                Intrinsics.checkNotNullExpressionValue(info, "info");
                hashMap.put("buildNumber", String.valueOf(getLongVersionCode(info)));
                if (buildSignature != null) {
                    hashMap.put("buildSignature", buildSignature);
                }
                if (installerPackageName != null) {
                    hashMap.put("installerStore", installerPackageName);
                }
                if (installTimeMillis != null) {
                    hashMap.put("installTime", installTimeMillis.toString());
                }
                result.success(hashMap);
                return;
            }
            result.notImplemented();
        } catch (PackageManager.NameNotFoundException e) {
            result.error("Name not found", e.getMessage(), null);
        }
    }

    private final String getInstallerPackageName() {
        Context context = this.applicationContext;
        Intrinsics.checkNotNull(context);
        PackageManager packageManager = context.getPackageManager();
        Context context2 = this.applicationContext;
        Intrinsics.checkNotNull(context2);
        String packageName = context2.getPackageName();
        if (Build.VERSION.SDK_INT >= 30) {
            return packageManager.getInstallSourceInfo(packageName).getInitiatingPackageName();
        }
        return packageManager.getInstallerPackageName(packageName);
    }

    private final Long getInstallTimeMillis() {
        PackageInfo packageInfo;
        try {
            Context context = this.applicationContext;
            Intrinsics.checkNotNull(context);
            PackageManager packageManager = context.getPackageManager();
            Context context2 = this.applicationContext;
            Intrinsics.checkNotNull(context2);
            String packageName = context2.getPackageName();
            if (Build.VERSION.SDK_INT >= 33) {
                packageInfo = packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0L));
            } else {
                packageInfo = packageManager.getPackageInfo(packageName, 0);
            }
            return Long.valueOf(packageInfo.firstInstallTime);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private final long getLongVersionCode(PackageInfo info) {
        if (Build.VERSION.SDK_INT >= 28) {
            return info.getLongVersionCode();
        }
        return info.versionCode;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x007a, code lost:
    
        if ((r5.length == 0) != false) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String getBuildSignature(PackageManager pm) {
        String signatureToSha256;
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                Context context = this.applicationContext;
                Intrinsics.checkNotNull(context);
                SigningInfo signingInfo = pm.getPackageInfo(context.getPackageName(), C.BUFFER_FLAG_FIRST_SAMPLE).signingInfo;
                if (signingInfo == null) {
                    return null;
                }
                if (signingInfo.hasMultipleSigners()) {
                    Signature[] apkContentsSigners = signingInfo.getApkContentsSigners();
                    Intrinsics.checkNotNullExpressionValue(apkContentsSigners, "signingInfo.apkContentsSigners");
                    byte[] byteArray = ((Signature) ArraysKt.first(apkContentsSigners)).toByteArray();
                    Intrinsics.checkNotNullExpressionValue(byteArray, "signingInfo.apkContentsS…ers.first().toByteArray()");
                    signatureToSha256 = signatureToSha256(byteArray);
                } else {
                    Signature[] signingCertificateHistory = signingInfo.getSigningCertificateHistory();
                    Intrinsics.checkNotNullExpressionValue(signingCertificateHistory, "signingInfo.signingCertificateHistory");
                    byte[] byteArray2 = ((Signature) ArraysKt.first(signingCertificateHistory)).toByteArray();
                    Intrinsics.checkNotNullExpressionValue(byteArray2, "signingInfo.signingCerti…ory.first().toByteArray()");
                    signatureToSha256 = signatureToSha256(byteArray2);
                }
            } else {
                Context context2 = this.applicationContext;
                Intrinsics.checkNotNull(context2);
                Signature[] signatures = pm.getPackageInfo(context2.getPackageName(), 64).signatures;
                boolean z = false;
                if (signatures != null) {
                }
                z = true;
                if (z) {
                    return null;
                }
                Intrinsics.checkNotNullExpressionValue(signatures, "signatures");
                if (ArraysKt.first(signatures) == null) {
                    return null;
                }
                byte[] byteArray3 = ((Signature) ArraysKt.first(signatures)).toByteArray();
                Intrinsics.checkNotNullExpressionValue(byteArray3, "signatures.first().toByteArray()");
                signatureToSha256 = signatureToSha256(byteArray3);
            }
            return signatureToSha256;
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException unused) {
            return null;
        }
    }

    private final String signatureToSha256(byte[] sig) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(sig);
        byte[] hashText = messageDigest.digest();
        Intrinsics.checkNotNullExpressionValue(hashText, "hashText");
        return bytesToHex(hashText);
    }

    private final String bytesToHex(byte[] bytes) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bytes.length * 2];
        int length = bytes.length;
        for (int i = 0; i < length; i++) {
            int i2 = bytes[i] & 255;
            int i3 = i * 2;
            cArr2[i3] = cArr[i2 >>> 4];
            cArr2[i3 + 1] = cArr[i2 & 15];
        }
        return new String(cArr2);
    }
}
