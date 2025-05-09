package vn.ai.faceauth.sdk.presentation.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import androidx.media3.exoplayer.upstream.CmcdData;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import lmlf.ayxnhy.tfwhgw;
import org.json.JSONException;
import vn.ai.faceauth.sdk.presentation.domain.configs.CameraSettings;
import vn.ai.faceauth.sdk.presentation.domain.configs.LivenessConfig;
import vn.ai.faceauth.sdk.presentation.domain.configs.SDKConfig;
import vn.ai.faceauth.sdk.presentation.navigation.SDKCallback;
import vn.ai.faceauth.sdk.presentation.presentation.LivenessCameraXActivity;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020'H\u0007J\u0010\u0010(\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u0018H\u0007J\u000f\u0010*\u001a\u0004\u0018\u00010\u0018H\u0000¢\u0006\u0002\b+J\u001a\u0010,\u001a\u00020-2\u0006\u0010&\u001a\u00020'2\b\u0010.\u001a\u0004\u0018\u00010\u0016H\u0002J\u0010\u0010/\u001a\u00020\u00162\u0006\u00100\u001a\u00020'H\u0007J\u000e\u00101\u001a\u00020\u00112\u0006\u00102\u001a\u00020\u0016J\u0010\u00103\u001a\u00020\u00112\u0006\u0010&\u001a\u00020'H\u0002J\u0018\u00104\u001a\u00020\u00112\u0006\u0010&\u001a\u00020'2\u0006\u0010.\u001a\u00020\u0016H\u0002J\u0006\u00105\u001a\u00020\u000bJ\u0010\u00106\u001a\u00020\u000b2\u0006\u00107\u001a\u00020\u0016H\u0007J\u0010\u00108\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u001d\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020'H\u0007J\u0010\u0010#\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020'H\u0007J\u0018\u00109\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020'2\u0006\u0010:\u001a\u00020\u0004H\u0007J\u0010\u0010;\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\u0018H\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR*\u0010\u001d\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\"\u0010#\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\r\"\u0004\b%\u0010\u000f¨\u0006<"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/navigation/AuthenticationID;", "", "()V", "callbackResult", "Lvn/ai/faceauth/sdk/presentation/navigation/SDKCallback;", "getCallbackResult$trueface_release", "()Lvn/ai/faceauth/sdk/presentation/navigation/SDKCallback;", "setCallbackResult$trueface_release", "(Lvn/ai/faceauth/sdk/presentation/navigation/SDKCallback;)V", "closeSDK", "Lkotlin/Function0;", "", "getCloseSDK$trueface_release", "()Lkotlin/jvm/functions/Function0;", "setCloseSDK$trueface_release", "(Lkotlin/jvm/functions/Function0;)V", "isSendCallback", "", "isStart", "livenessConfig", "Lvn/ai/faceauth/sdk/presentation/domain/configs/LivenessConfig;", "mLanguage", "", "sdkConfig", "Lvn/ai/faceauth/sdk/presentation/domain/configs/SDKConfig;", "getSdkConfig$trueface_release", "()Lvn/ai/faceauth/sdk/presentation/domain/configs/SDKConfig;", "setSdkConfig$trueface_release", "(Lvn/ai/faceauth/sdk/presentation/domain/configs/SDKConfig;)V", "showError", "Lkotlin/Function1;", "getShowError$trueface_release", "()Lkotlin/jvm/functions/Function1;", "setShowError$trueface_release", "(Lkotlin/jvm/functions/Function1;)V", "showSuccess", "getShowSuccess$trueface_release", "setShowSuccess$trueface_release", "context", "Landroid/content/Context;", "configure", "obj", "getConfig", "getConfig$trueface_release", "getFileSizeInAssets", "", "fileName", "info", "activity", "isBase64", "input", "isDebuggable", "listFilesInAssets", "removeAction", "setLanguage", CmcdData.Factory.STREAMING_FORMAT_SS, "setLivenessConfig", "startFaceAuthen", "callback", "validConfig", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class AuthenticationID {
    public static final AuthenticationID INSTANCE = new AuthenticationID();
    private static SDKCallback callbackResult;
    private static Function0<Unit> closeSDK;
    private static boolean isSendCallback;
    private static boolean isStart;
    private static LivenessConfig livenessConfig;
    private static String mLanguage;
    private static SDKConfig sdkConfig;
    private static Function1<? super String, Unit> showError;
    private static Function0<Unit> showSuccess;

    private AuthenticationID() {
    }

    @JvmStatic
    public static final void closeSDK(Context context) {
        Function0<Unit> function0 = closeSDK;
        if (function0 != null) {
            function0.invoke();
        }
    }

    @JvmStatic
    public static final void configure(SDKConfig obj) {
        try {
            AuthenticationID authenticationID = INSTANCE;
            if (authenticationID.validConfig(obj)) {
                sdkConfig = obj;
            }
            authenticationID.setLivenessConfig(new LivenessConfig(0.0f, 0.0f, 0.0f, 0.0f, 2, null));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private final long getFileSizeInAssets(Context context, String fileName) {
        InputStream open = context.getAssets().open(fileName);
        try {
            long available = open.available();
            CloseableKt.closeFinally(open, null);
            return available;
        } finally {
        }
    }

    @JvmStatic
    public static final String info(Context activity) {
        long j;
        AuthenticationID authenticationID = INSTANCE;
        String rnigpa = tfwhgw.rnigpa(57);
        boolean listFilesInAssets = authenticationID.listFilesInAssets(activity, rnigpa);
        String rnigpa2 = tfwhgw.rnigpa(58);
        if (listFilesInAssets) {
            j = authenticationID.getFileSizeInAssets(activity, rnigpa);
        } else {
            Log.e(rnigpa2, tfwhgw.rnigpa(59));
            j = 0;
        }
        String str = tfwhgw.rnigpa(60) + authenticationID.isDebuggable(activity) + tfwhgw.rnigpa(61) + j;
        Log.e(rnigpa2, tfwhgw.rnigpa(62) + str);
        return str;
    }

    private final boolean isDebuggable(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    private final boolean listFilesInAssets(Context context, String fileName) {
        try {
            String[] list = context.getAssets().list("");
            if (list == null) {
                return false;
            }
            Iterator it = ArrayIteratorKt.iterator(list);
            while (it.hasNext()) {
                if (Intrinsics.areEqual((String) it.next(), fileName)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            Log.e(tfwhgw.rnigpa(63), tfwhgw.rnigpa(64), e);
            return false;
        }
    }

    @JvmStatic
    public static final void setLanguage(String s) {
        mLanguage = s;
    }

    private final void setLivenessConfig(LivenessConfig livenessConfig2) {
        livenessConfig = livenessConfig2;
    }

    @JvmStatic
    public static final void showError(Context context) {
        Function1<? super String, Unit> function1 = showError;
        if (function1 != null) {
            function1.invoke("");
        }
    }

    @JvmStatic
    public static final void showSuccess(Context context) {
        Function0<Unit> function0 = showSuccess;
        if (function0 != null) {
            function0.invoke();
        }
    }

    @JvmStatic
    public static final void startFaceAuthen(Context context, SDKCallback callback) {
        int i;
        info(context);
        if (!isStart) {
            isStart = true;
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.navigation.AuthenticationID$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AuthenticationID.isStart = false;
            }
        }, 1500L);
        SDKConfig sDKConfig = sdkConfig;
        int i2 = 0;
        if (sDKConfig == null || !INSTANCE.isBase64(sDKConfig.getNonce())) {
            SDKConfig sDKConfig2 = sdkConfig;
            if (sDKConfig2 != null && !INSTANCE.isBase64(sDKConfig2.getNonce())) {
                SDKCallback.DefaultImpls.complete$default(callback, 311, tfwhgw.rnigpa(69), "", "", "", null, 32, null);
                return;
            } else {
                i2 = 311;
                i = 70;
            }
        } else {
            if (Build.VERSION.SDK_INT >= 22) {
                Intent intent = new Intent(context, (Class<?>) LivenessCameraXActivity.class);
                intent.putExtra(tfwhgw.rnigpa(65), new CameraSettings(null, null, 3, null));
                intent.putExtra(tfwhgw.rnigpa(66), mLanguage);
                intent.putExtra(tfwhgw.rnigpa(67), livenessConfig);
                context.startActivity(intent);
                callbackResult = callback;
                isSendCallback = false;
                return;
            }
            i = 68;
        }
        SDKCallback.DefaultImpls.complete$default(callback, i2, tfwhgw.rnigpa(i), "", "", "", null, 32, null);
    }

    private final boolean validConfig(SDKConfig obj) {
        int i;
        boolean z = obj.getPrimaryColor().length() == 0;
        String rnigpa = tfwhgw.rnigpa(71);
        if (z) {
            i = 72;
        } else {
            if (obj.getSecondaryColor().length() == 0) {
                i = 73;
            } else {
                if (obj.getErrorColor().length() == 0) {
                    i = 74;
                } else {
                    if (obj.getCloseColor().length() == 0) {
                        i = 75;
                    } else {
                        if (obj.getTextColor().length() == 0) {
                            i = 76;
                        } else {
                            if (obj.getBackgroundColor().length() == 0) {
                                i = 77;
                            } else {
                                if (!(obj.getNonce().length() == 0)) {
                                    return true;
                                }
                                i = 78;
                            }
                        }
                    }
                }
            }
        }
        Log.e(rnigpa, tfwhgw.rnigpa(i));
        return false;
    }

    public final SDKCallback getCallbackResult$trueface_release() {
        return callbackResult;
    }

    public final Function0<Unit> getCloseSDK$trueface_release() {
        return closeSDK;
    }

    public final SDKConfig getConfig$trueface_release() {
        return sdkConfig;
    }

    public final SDKConfig getSdkConfig$trueface_release() {
        return sdkConfig;
    }

    public final Function1<String, Unit> getShowError$trueface_release() {
        return showError;
    }

    public final Function0<Unit> getShowSuccess$trueface_release() {
        return showSuccess;
    }

    public final boolean isBase64(String input) {
        if (!new Regex(tfwhgw.rnigpa(79)).matches(input)) {
            return false;
        }
        if (input.length() == 0) {
            return false;
        }
        if (!StringsKt.isBlank(input)) {
            try {
                if (Base64.decode(input, 0).length < 32) {
                    return false;
                }
            } catch (IllegalArgumentException unused) {
                return false;
            }
        }
        return true;
    }

    public final void removeAction() {
        showSuccess = null;
        closeSDK = null;
        showError = null;
    }

    public final void setCallbackResult$trueface_release(SDKCallback sDKCallback) {
        callbackResult = sDKCallback;
    }

    public final void setCloseSDK$trueface_release(Function0<Unit> function0) {
        closeSDK = function0;
    }

    public final void setSdkConfig$trueface_release(SDKConfig sDKConfig) {
        sdkConfig = sDKConfig;
    }

    public final void setShowError$trueface_release(Function1<? super String, Unit> function1) {
        showError = function1;
    }

    public final void setShowSuccess$trueface_release(Function0<Unit> function0) {
        showSuccess = function0;
    }
}
