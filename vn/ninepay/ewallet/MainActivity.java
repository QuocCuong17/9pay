package vn.ninepay.ewallet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.WindowManager;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.Credentials;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.gson.Gson;
import com.pichillilorenzo.flutter_inappwebview_android.credential_database.URLCredentialContract;
import io.flutter.embedding.android.FlutterActivityLaunchConfigs;
import io.flutter.embedding.android.FlutterFragmentActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import vn.ai.faceauth.sdk.presentation.domain.configs.SDKConfig;
import vn.ai.faceauth.sdk.presentation.navigation.AuthenticationID;
import vn.ai.faceauth.sdk.presentation.navigation.SDKCallback;
import vn.ninepay.ewallet.biometric.BiometricUtils;
import vn.ninepay.ewallet.biometric.FingerprintBottomSheetFragment;

/* loaded from: classes6.dex */
public class MainActivity extends FlutterFragmentActivity {
    private final int CONTACT_PICKER_RESULT = 1001;
    private BiometricUtils biometricUtils;
    boolean checkBiometric;
    private FingerprintBottomSheetFragment fingerprintDialogFragment;
    String reason;
    MethodChannel.Result resultBiometric;
    MethodChannel.Result resultConfig;
    MethodChannel.Result resultVneid;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.flutter.embedding.android.FlutterFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.biometricUtils = BiometricUtils.getInstance(this);
        FingerprintBottomSheetFragment fingerprintBottomSheetFragment = new FingerprintBottomSheetFragment();
        this.fingerprintDialogFragment = fingerprintBottomSheetFragment;
        fingerprintBottomSheetFragment.setCancelable(true);
        setCallbackFinger();
        setupBiometric();
    }

    @Override // io.flutter.embedding.android.FlutterFragmentActivity
    protected FlutterActivityLaunchConfigs.BackgroundMode getBackgroundMode() {
        return FlutterActivityLaunchConfigs.BackgroundMode.transparent;
    }

    @Override // io.flutter.embedding.android.FlutterFragmentActivity, io.flutter.embedding.android.FlutterEngineConfigurator
    public void configureFlutterEngine(FlutterEngine flutterEngine) {
        MethodChannel methodChannel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), "com.9payjsc.vn/biometric");
        MethodChannel methodChannel2 = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), "com.9payjsc.vn/config");
        MethodChannel methodChannel3 = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), "com.9payjsc.vn/vneid");
        methodChannel2.setMethodCallHandler(new MethodChannel.MethodCallHandler() { // from class: vn.ninepay.ewallet.MainActivity$$ExternalSyntheticLambda2
            @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
            public final void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
                MainActivity.this.m3000lambda$configureFlutterEngine$0$vnninepayewalletMainActivity(methodCall, result);
            }
        });
        methodChannel.setMethodCallHandler(new MethodChannel.MethodCallHandler() { // from class: vn.ninepay.ewallet.MainActivity$$ExternalSyntheticLambda3
            @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
            public final void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
                MainActivity.this.m3001lambda$configureFlutterEngine$1$vnninepayewalletMainActivity(methodCall, result);
            }
        });
        methodChannel3.setMethodCallHandler(new MethodChannel.MethodCallHandler() { // from class: vn.ninepay.ewallet.MainActivity$$ExternalSyntheticLambda4
            @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
            public final void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
                MainActivity.this.m3003lambda$configureFlutterEngine$3$vnninepayewalletMainActivity(methodCall, result);
            }
        });
        GeneratedPluginRegistrant.registerWith(flutterEngine);
    }

    /* renamed from: lambda$configureFlutterEngine$0$vn-ninepay-ewallet-MainActivity, reason: not valid java name */
    public /* synthetic */ void m3000lambda$configureFlutterEngine$0$vnninepayewalletMainActivity(MethodCall methodCall, MethodChannel.Result result) {
        this.resultConfig = result;
        String str = methodCall.method;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1822101157:
                if (str.equals("open_setting")) {
                    c = 0;
                    break;
                }
                break;
            case -1499145147:
                if (str.equals("access_contact")) {
                    c = 1;
                    break;
                }
                break;
            case -1249338390:
                if (str.equals("get_st")) {
                    c = 2;
                    break;
                }
                break;
            case -903340183:
                if (str.equals("shield")) {
                    c = 3;
                    break;
                }
                break;
            case 691282604:
                if (str.equals("open_store")) {
                    c = 4;
                    break;
                }
                break;
            case 891576777:
                if (str.equals("isNeedUpdate")) {
                    c = 5;
                    break;
                }
                break;
            case 1302914615:
                if (str.equals("request_hint")) {
                    c = 6;
                    break;
                }
                break;
            case 1524124993:
                if (str.equals("reset_brightness")) {
                    c = 7;
                    break;
                }
                break;
            case 1804460828:
                if (str.equals("get_contacts")) {
                    c = '\b';
                    break;
                }
                break;
            case 1951568974:
                if (str.equals("set_brightness")) {
                    c = '\t';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                startActivity(new Intent("android.settings.SETTINGS"));
                result.success(null);
                return;
            case 1:
                startActivityForResult(new Intent("android.intent.action.PICK", ContactsContract.CommonDataKinds.Phone.CONTENT_URI), 1001);
                return;
            case 2:
                result.success(getSt(this));
                break;
            case 3:
                break;
            case 4:
                String str2 = (String) methodCall.argument("appPackageName");
                Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(str2);
                if (launchIntentForPackage == null) {
                    launchIntentForPackage = new Intent("android.intent.action.VIEW");
                    launchIntentForPackage.setData(Uri.parse("market://details?id=" + str2));
                }
                launchIntentForPackage.addFlags(268435456);
                startActivity(launchIntentForPackage);
                result.success("");
                return;
            case 5:
                checkUpdate(this, result);
                return;
            case 6:
                try {
                    requestHint();
                    return;
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                    this.resultConfig.success("");
                    return;
                }
            case 7:
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.screenBrightness = -1.0f;
                getWindow().setAttributes(attributes);
                result.success(null);
                return;
            case '\b':
                result.success(new Gson().toJson(readContacts()));
                return;
            case '\t':
                double doubleValue = ((Double) methodCall.argument("brightness")).doubleValue();
                WindowManager.LayoutParams attributes2 = getWindow().getAttributes();
                attributes2.screenBrightness = (float) doubleValue;
                getWindow().setAttributes(attributes2);
                result.success(null);
                return;
            default:
                result.notImplemented();
                return;
        }
        result.success(get(this, (String) methodCall.argument("pkg"), "SHA256"));
    }

    /* renamed from: lambda$configureFlutterEngine$1$vn-ninepay-ewallet-MainActivity, reason: not valid java name */
    public /* synthetic */ void m3001lambda$configureFlutterEngine$1$vnninepayewalletMainActivity(MethodCall methodCall, MethodChannel.Result result) {
        this.resultBiometric = result;
        String str = methodCall.method;
        str.hashCode();
        if (str.equals("get_biometric")) {
            boolean booleanValue = ((Boolean) methodCall.argument("check_available")).booleanValue();
            this.checkBiometric = booleanValue;
            if (!booleanValue) {
                this.reason = (String) methodCall.argument("reason");
            }
            this.biometricUtils.checkStatusBiometric(this);
            return;
        }
        if (str.equals("remove_domain")) {
            this.biometricUtils.resetBiometric();
            result.success("");
        } else {
            result.notImplemented();
        }
    }

    /* renamed from: lambda$configureFlutterEngine$3$vn-ninepay-ewallet-MainActivity, reason: not valid java name */
    public /* synthetic */ void m3003lambda$configureFlutterEngine$3$vnninepayewalletMainActivity(MethodCall methodCall, final MethodChannel.Result result) {
        this.resultVneid = result;
        String str = methodCall.method;
        str.hashCode();
        if (!str.equals("initVneid")) {
            if (str.equals("startFaceAuthen")) {
                AuthenticationID.startFaceAuthen(this, new SDKCallback() { // from class: vn.ninepay.ewallet.MainActivity$$ExternalSyntheticLambda5
                    @Override // vn.ai.faceauth.sdk.presentation.navigation.SDKCallback
                    public final void complete(int i, String str2, String str3, String str4, String str5, String str6) {
                        MainActivity.this.m3002lambda$configureFlutterEngine$2$vnninepayewalletMainActivity(result, i, str2, str3, str4, str5, str6);
                    }
                });
                return;
            } else {
                result.notImplemented();
                return;
            }
        }
        String str2 = (String) methodCall.argument("nonce");
        if (str2 == null) {
            result.success(false);
        } else {
            init(str2);
            result.success(true);
        }
    }

    /* renamed from: lambda$configureFlutterEngine$2$vn-ninepay-ewallet-MainActivity, reason: not valid java name */
    public /* synthetic */ void m3002lambda$configureFlutterEngine$2$vnninepayewalletMainActivity(MethodChannel.Result result, int i, String str, String str2, String str3, String str4, String str5) {
        HashMap hashMap = new HashMap();
        hashMap.put("code", i + "");
        hashMap.put("firstFace", str2);
        hashMap.put("lastFace", str3);
        hashMap.put("encryptedFaceImages", str5);
        hashMap.put("signature", str4);
        hashMap.put("erorMessage", str);
        AuthenticationID.closeSDK(this);
        result.success(hashMap);
    }

    public void init(String str) {
        AuthenticationID.configure(new SDKConfig("#26B840", "#42526E", "#253858", "#FF6234", "#253858", "#253858", "#ffffff", "#ffffff", true, false, true, str));
        AuthenticationID.setLanguage("vi");
    }

    private void checkUpdate(Activity activity, final MethodChannel.Result result) {
        try {
            AppUpdateManagerFactory.create(activity).getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener() { // from class: vn.ninepay.ewallet.MainActivity$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    MainActivity.lambda$checkUpdate$4(MethodChannel.Result.this, (AppUpdateInfo) obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: vn.ninepay.ewallet.MainActivity$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    MethodChannel.Result.this.success(false);
                }
            });
        } catch (Exception unused) {
            result.success(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$checkUpdate$4(MethodChannel.Result result, AppUpdateInfo appUpdateInfo) {
        if (appUpdateInfo.updateAvailability() == 2 && appUpdateInfo.isUpdateTypeAllowed(1)) {
            result.success(true);
        } else {
            result.success(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.flutter.embedding.android.FlutterFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            if (i == 99) {
                this.resultConfig.success("");
                return;
            }
            return;
        }
        if (i == 1001) {
            Cursor query = getContentResolver().query(intent.getData(), null, null, null, null);
            query.moveToFirst();
            String string = query.getString(query.getColumnIndex("data1"));
            String string2 = query.getString(query.getColumnIndex("display_name"));
            HashMap hashMap = new HashMap();
            hashMap.put("contact_name", string2);
            hashMap.put("phone_number", string);
            this.resultConfig.success(hashMap);
        }
        if (i == 99) {
            Credential credential = (Credential) intent.getParcelableExtra(Credential.EXTRA_KEY);
            Log.d(URLCredentialContract.FeedEntry.TABLE_NAME, "check--" + credential);
            if (credential != null) {
                this.resultConfig.success(credential.getId());
            } else {
                this.resultConfig.success("");
            }
        }
    }

    private void requestHint() throws IntentSender.SendIntentException {
        startIntentSenderForResult(Credentials.getClient((Activity) this).getHintPickerIntent(new HintRequest.Builder().setPhoneNumberIdentifierSupported(true).build()).getIntentSender(), 99, null, 0, 0, 0);
    }

    private ArrayList<ContactModel> readContacts() {
        Uri uri;
        ArrayList<ContactModel> arrayList = new ArrayList<>();
        String[] strArr = {"display_name", "photo_uri", "data1"};
        String[] strArr2 = {"vnd.android.cursor.item/phone_v2"};
        if (Build.VERSION.SDK_INT >= 18) {
            uri = ContactsContract.CommonDataKinds.Contactables.CONTENT_URI;
        } else {
            uri = ContactsContract.Data.CONTENT_URI;
        }
        Cursor query = getContentResolver().query(uri, strArr, "mimetype in (?, ?) AND has_phone_number = '1'", strArr2, "sort_key_alt");
        if (query != null) {
            int columnIndex = query.getColumnIndex("display_name");
            int columnIndex2 = query.getColumnIndex("data1");
            int columnIndex3 = query.getColumnIndex("photo_uri");
            while (query.moveToNext()) {
                try {
                    String string = query.getString(columnIndex3);
                    String string2 = query.getString(columnIndex);
                    String string3 = query.getString(columnIndex2);
                    if (!isNullOrBlank(string3) && string3.length() <= 15 && string3.length() > 9) {
                        arrayList.add(new ContactModel(string, string2, string3));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    private void setCallbackFinger() {
        this.fingerprintDialogFragment.setCallback(new FingerprintBottomSheetFragment.BiometricAuthCallback() { // from class: vn.ninepay.ewallet.MainActivity.1
            @Override // vn.ninepay.ewallet.biometric.FingerprintBottomSheetFragment.BiometricAuthCallback
            public void onAuthenticated() {
                MainActivity.this.resultBiometric.success(1);
            }

            @Override // vn.ninepay.ewallet.biometric.FingerprintBottomSheetFragment.BiometricAuthCallback
            public void onAuthError() {
                MainActivity.this.resultBiometric.success(5);
            }
        });
    }

    private void setupBiometric() {
        this.biometricUtils.setCallback(new BiometricUtils.BiometricCallback() { // from class: vn.ninepay.ewallet.MainActivity.2
            @Override // vn.ninepay.ewallet.biometric.BiometricUtils.BiometricCallback
            public void canAuthenticate() {
                if (!MainActivity.this.checkBiometric) {
                    if (MainActivity.this.fingerprintDialogFragment.isAdded()) {
                        return;
                    }
                    MainActivity.this.fingerprintDialogFragment.show(MainActivity.this.getSupportFragmentManager(), "");
                    return;
                }
                MainActivity.this.resultBiometric.success(10);
            }

            @Override // vn.ninepay.ewallet.biometric.BiometricUtils.BiometricCallback
            public void hardwareNoSupport() {
                MainActivity.this.resultBiometric.success(3);
            }

            @Override // vn.ninepay.ewallet.biometric.BiometricUtils.BiometricCallback
            public void noEnrolled() {
                MainActivity.this.resultBiometric.success(4);
            }

            @Override // vn.ninepay.ewallet.biometric.BiometricUtils.BiometricCallback
            public void biometricChange() {
                MainActivity.this.resultBiometric.success(7);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        try {
            if (this.fingerprintDialogFragment.isAdded()) {
                this.fingerprintDialogFragment.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean isNullOrBlank(String str) {
        return str == null || str.isEmpty() || str.equals("null");
    }

    public String getPathFromURI(Uri uri) {
        Cursor query = getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
        if (query != null) {
            r1 = query.moveToFirst() ? query.getString(query.getColumnIndexOrThrow("_data")) : null;
            query.close();
        }
        return r1;
    }

    public String getSt(Context context) {
        return verifyInstallerId(context) ? "0" : "1";
    }

    public boolean verifyInstallerId(Context context) {
        ArrayList arrayList = new ArrayList(Arrays.asList("com.android.vending", "com.google.android.feedback"));
        String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
        return installerPackageName != null && arrayList.contains(installerPackageName);
    }

    private String get(Context context, String str, String str2) {
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(BuildConfig.APPLICATION_ID, 64).signatures;
            if (signatureArr.length <= 0) {
                return "null";
            }
            Signature signature = signatureArr[0];
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(signature.toByteArray());
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                if (i != 0) {
                    sb.append(":");
                }
                String hexString = Integer.toHexString(digest[i] & 255);
                if (hexString.length() == 1) {
                    sb.append("0");
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("name not found", e.toString());
            return "null";
        } catch (NoSuchAlgorithmException e2) {
            Log.e("no such an algorithm", e2.toString());
            return "null";
        } catch (Exception e3) {
            Log.e("exception", e3.toString());
            return "null";
        }
    }
}
