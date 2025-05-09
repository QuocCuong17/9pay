package io.flutter.embedding.engine.systemchannels;

import androidx.webkit.internal.AssetHelper;
import io.flutter.Log;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.JSONMethodCodec;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class PlatformChannel {
    private static final String TAG = "PlatformChannel";
    public final MethodChannel channel;
    final MethodChannel.MethodCallHandler parsingMethodCallHandler;
    private PlatformMessageHandler platformMessageHandler;

    /* loaded from: classes5.dex */
    public interface PlatformMessageHandler {

        /* renamed from: io.flutter.embedding.engine.systemchannels.PlatformChannel$PlatformMessageHandler$-CC, reason: invalid class name */
        /* loaded from: classes5.dex */
        public final /* synthetic */ class CC {
            public static void $default$setFrameworkHandlesBack(PlatformMessageHandler _this, boolean z) {
            }
        }

        boolean clipboardHasStrings();

        CharSequence getClipboardData(ClipboardContentFormat clipboardContentFormat);

        void playSystemSound(SoundType soundType);

        void popSystemNavigator();

        void restoreSystemUiOverlays();

        void setApplicationSwitcherDescription(AppSwitcherDescription appSwitcherDescription);

        void setClipboardData(String str);

        void setFrameworkHandlesBack(boolean z);

        void setPreferredOrientations(int i);

        void setSystemUiChangeListener();

        void setSystemUiOverlayStyle(SystemChromeStyle systemChromeStyle);

        void share(String str);

        void showSystemOverlays(List<SystemUiOverlay> list);

        void showSystemUiMode(SystemUiMode systemUiMode);

        void vibrateHapticFeedback(HapticFeedbackType hapticFeedbackType);
    }

    public PlatformChannel(DartExecutor dartExecutor) {
        MethodChannel.MethodCallHandler methodCallHandler = new MethodChannel.MethodCallHandler() { // from class: io.flutter.embedding.engine.systemchannels.PlatformChannel.1
            /* JADX WARN: Removed duplicated region for block: B:25:0x0148 A[Catch: JSONException -> 0x0276, TryCatch #7 {JSONException -> 0x0276, blocks: (B:7:0x002c, B:8:0x0030, B:12:0x00db, B:14:0x00e0, B:16:0x00f0, B:18:0x010a, B:20:0x011e, B:30:0x0122, B:23:0x013c, B:25:0x0148, B:27:0x0155, B:32:0x0127, B:33:0x015a, B:35:0x0168, B:37:0x019e, B:39:0x01ac, B:56:0x023e, B:42:0x025a, B:81:0x0195, B:67:0x01d3, B:74:0x01f5, B:53:0x0215, B:88:0x0236, B:60:0x0252, B:46:0x026e, B:90:0x0035, B:93:0x0040, B:96:0x004b, B:99:0x0057, B:102:0x0063, B:105:0x006e, B:108:0x0079, B:111:0x0083, B:114:0x008d, B:117:0x0097, B:120:0x00a1, B:123:0x00ab, B:126:0x00b6, B:129:0x00c1, B:132:0x00cc, B:49:0x01fe), top: B:6:0x002c, inners: #0, #1, #3, #10 }] */
            /* JADX WARN: Removed duplicated region for block: B:27:0x0155 A[Catch: JSONException -> 0x0276, TryCatch #7 {JSONException -> 0x0276, blocks: (B:7:0x002c, B:8:0x0030, B:12:0x00db, B:14:0x00e0, B:16:0x00f0, B:18:0x010a, B:20:0x011e, B:30:0x0122, B:23:0x013c, B:25:0x0148, B:27:0x0155, B:32:0x0127, B:33:0x015a, B:35:0x0168, B:37:0x019e, B:39:0x01ac, B:56:0x023e, B:42:0x025a, B:81:0x0195, B:67:0x01d3, B:74:0x01f5, B:53:0x0215, B:88:0x0236, B:60:0x0252, B:46:0x026e, B:90:0x0035, B:93:0x0040, B:96:0x004b, B:99:0x0057, B:102:0x0063, B:105:0x006e, B:108:0x0079, B:111:0x0083, B:114:0x008d, B:117:0x0097, B:120:0x00a1, B:123:0x00ab, B:126:0x00b6, B:129:0x00c1, B:132:0x00cc, B:49:0x01fe), top: B:6:0x002c, inners: #0, #1, #3, #10 }] */
            @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
                ClipboardContentFormat fromValue;
                CharSequence clipboardData;
                if (PlatformChannel.this.platformMessageHandler == null) {
                    return;
                }
                String str = methodCall.method;
                Object obj = methodCall.arguments;
                Log.v(PlatformChannel.TAG, "Received '" + str + "' message.");
                char c = 65535;
                try {
                    switch (str.hashCode()) {
                        case -1501580720:
                            if (str.equals("SystemNavigator.setFrameworkHandlesBack")) {
                                c = '\t';
                                break;
                            }
                            break;
                        case -931781241:
                            if (str.equals("Share.invoke")) {
                                c = 14;
                                break;
                            }
                            break;
                        case -766342101:
                            if (str.equals("SystemNavigator.pop")) {
                                c = '\n';
                                break;
                            }
                            break;
                        case -720677196:
                            if (str.equals("Clipboard.setData")) {
                                c = '\f';
                                break;
                            }
                            break;
                        case -577225884:
                            if (str.equals("SystemChrome.setSystemUIChangeListener")) {
                                c = 6;
                                break;
                            }
                            break;
                        case -548468504:
                            if (str.equals("SystemChrome.setApplicationSwitcherDescription")) {
                                c = 3;
                                break;
                            }
                            break;
                        case -247230243:
                            if (str.equals("HapticFeedback.vibrate")) {
                                c = 1;
                                break;
                            }
                            break;
                        case -215273374:
                            if (str.equals("SystemSound.play")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 241845679:
                            if (str.equals("SystemChrome.restoreSystemUIOverlays")) {
                                c = 7;
                                break;
                            }
                            break;
                        case 875995648:
                            if (str.equals("Clipboard.hasStrings")) {
                                c = '\r';
                                break;
                            }
                            break;
                        case 1128339786:
                            if (str.equals("SystemChrome.setEnabledSystemUIMode")) {
                                c = 5;
                                break;
                            }
                            break;
                        case 1390477857:
                            if (str.equals("SystemChrome.setSystemUIOverlayStyle")) {
                                c = '\b';
                                break;
                            }
                            break;
                        case 1514180520:
                            if (str.equals("Clipboard.getData")) {
                                c = 11;
                                break;
                            }
                            break;
                        case 1674312266:
                            if (str.equals("SystemChrome.setEnabledSystemUIOverlays")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 2119655719:
                            if (str.equals("SystemChrome.setPreferredOrientations")) {
                                c = 2;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            try {
                                PlatformChannel.this.platformMessageHandler.playSystemSound(SoundType.fromValue((String) obj));
                                result.success(null);
                                return;
                            } catch (NoSuchFieldException e) {
                                result.error("error", e.getMessage(), null);
                                return;
                            }
                        case 1:
                            try {
                                PlatformChannel.this.platformMessageHandler.vibrateHapticFeedback(HapticFeedbackType.fromValue((String) obj));
                                result.success(null);
                                return;
                            } catch (NoSuchFieldException e2) {
                                result.error("error", e2.getMessage(), null);
                                return;
                            }
                        case 2:
                            try {
                                PlatformChannel.this.platformMessageHandler.setPreferredOrientations(PlatformChannel.this.decodeOrientations((JSONArray) obj));
                                result.success(null);
                                return;
                            } catch (NoSuchFieldException | JSONException e3) {
                                result.error("error", e3.getMessage(), null);
                                return;
                            }
                        case 3:
                            try {
                                PlatformChannel.this.platformMessageHandler.setApplicationSwitcherDescription(PlatformChannel.this.decodeAppSwitcherDescription((JSONObject) obj));
                                result.success(null);
                                return;
                            } catch (JSONException e4) {
                                result.error("error", e4.getMessage(), null);
                                return;
                            }
                        case 4:
                            try {
                                PlatformChannel.this.platformMessageHandler.showSystemOverlays(PlatformChannel.this.decodeSystemUiOverlays((JSONArray) obj));
                                result.success(null);
                                return;
                            } catch (NoSuchFieldException | JSONException e5) {
                                result.error("error", e5.getMessage(), null);
                                return;
                            }
                        case 5:
                            try {
                                PlatformChannel.this.platformMessageHandler.showSystemUiMode(PlatformChannel.this.decodeSystemUiMode((String) obj));
                                result.success(null);
                                return;
                            } catch (NoSuchFieldException | JSONException e6) {
                                result.error("error", e6.getMessage(), null);
                                return;
                            }
                        case 6:
                            PlatformChannel.this.platformMessageHandler.setSystemUiChangeListener();
                            result.success(null);
                            return;
                        case 7:
                            PlatformChannel.this.platformMessageHandler.restoreSystemUiOverlays();
                            result.success(null);
                            return;
                        case '\b':
                            try {
                                PlatformChannel.this.platformMessageHandler.setSystemUiOverlayStyle(PlatformChannel.this.decodeSystemChromeStyle((JSONObject) obj));
                                result.success(null);
                                return;
                            } catch (NoSuchFieldException | JSONException e7) {
                                result.error("error", e7.getMessage(), null);
                                return;
                            }
                        case '\t':
                            PlatformChannel.this.platformMessageHandler.setFrameworkHandlesBack(((Boolean) obj).booleanValue());
                            result.success(null);
                            return;
                        case '\n':
                            PlatformChannel.this.platformMessageHandler.popSystemNavigator();
                            result.success(null);
                            return;
                        case 11:
                            String str2 = (String) obj;
                            if (str2 != null) {
                                try {
                                    fromValue = ClipboardContentFormat.fromValue(str2);
                                } catch (NoSuchFieldException unused) {
                                    result.error("error", "No such clipboard content format: " + str2, null);
                                }
                                clipboardData = PlatformChannel.this.platformMessageHandler.getClipboardData(fromValue);
                                if (clipboardData == null) {
                                    JSONObject jSONObject = new JSONObject();
                                    jSONObject.put("text", clipboardData);
                                    result.success(jSONObject);
                                    return;
                                }
                                result.success(null);
                                return;
                            }
                            fromValue = null;
                            clipboardData = PlatformChannel.this.platformMessageHandler.getClipboardData(fromValue);
                            if (clipboardData == null) {
                            }
                        case '\f':
                            PlatformChannel.this.platformMessageHandler.setClipboardData(((JSONObject) obj).getString("text"));
                            result.success(null);
                            return;
                        case '\r':
                            boolean clipboardHasStrings = PlatformChannel.this.platformMessageHandler.clipboardHasStrings();
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("value", clipboardHasStrings);
                            result.success(jSONObject2);
                            return;
                        case 14:
                            PlatformChannel.this.platformMessageHandler.share((String) obj);
                            result.success(null);
                            return;
                        default:
                            result.notImplemented();
                            return;
                    }
                } catch (JSONException e8) {
                    result.error("error", "JSON error: " + e8.getMessage(), null);
                }
                result.error("error", "JSON error: " + e8.getMessage(), null);
            }
        };
        this.parsingMethodCallHandler = methodCallHandler;
        MethodChannel methodChannel = new MethodChannel(dartExecutor, "flutter/platform", JSONMethodCodec.INSTANCE);
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(methodCallHandler);
    }

    public void setPlatformMessageHandler(PlatformMessageHandler platformMessageHandler) {
        this.platformMessageHandler = platformMessageHandler;
    }

    public void systemChromeChanged(boolean z) {
        Log.v(TAG, "Sending 'systemUIChange' message.");
        this.channel.invokeMethod("SystemChrome.systemUIChange", Arrays.asList(Boolean.valueOf(z)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:22:0x003e. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0053 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int decodeOrientations(JSONArray jSONArray) throws JSONException, NoSuchFieldException {
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            int i4 = AnonymousClass2.$SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$DeviceOrientation[DeviceOrientation.fromValue(jSONArray.getString(i3)).ordinal()];
            if (i4 == 1) {
                i |= 1;
            } else if (i4 == 2) {
                i |= 4;
            } else if (i4 == 3) {
                i |= 2;
            } else if (i4 == 4) {
                i |= 8;
            }
            if (i2 == 0) {
                i2 = i;
            }
        }
        if (i == 0) {
            return -1;
        }
        switch (i) {
            case 2:
                return 0;
            case 3:
            case 6:
            case 7:
            case 9:
            case 12:
            case 13:
            case 14:
                if (i2 != 2) {
                    if (i2 != 4) {
                        return i2 != 8 ? 1 : 8;
                    }
                    return 9;
                }
                return 0;
            case 4:
                return 9;
            case 5:
                return 12;
            case 8:
                return 8;
            case 10:
                return 11;
            case 11:
                return 2;
            case 15:
                return 13;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AppSwitcherDescription decodeAppSwitcherDescription(JSONObject jSONObject) throws JSONException {
        int i = jSONObject.getInt("primaryColor");
        if (i != 0) {
            i |= -16777216;
        }
        return new AppSwitcherDescription(i, jSONObject.getString("label"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<SystemUiOverlay> decodeSystemUiOverlays(JSONArray jSONArray) throws JSONException, NoSuchFieldException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            int i2 = AnonymousClass2.$SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiOverlay[SystemUiOverlay.fromValue(jSONArray.getString(i)).ordinal()];
            if (i2 == 1) {
                arrayList.add(SystemUiOverlay.TOP_OVERLAYS);
            } else if (i2 == 2) {
                arrayList.add(SystemUiOverlay.BOTTOM_OVERLAYS);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.flutter.embedding.engine.systemchannels.PlatformChannel$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$DeviceOrientation;
        static final /* synthetic */ int[] $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiMode;
        static final /* synthetic */ int[] $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiOverlay;

        static {
            int[] iArr = new int[SystemUiMode.values().length];
            $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiMode = iArr;
            try {
                iArr[SystemUiMode.LEAN_BACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiMode[SystemUiMode.IMMERSIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiMode[SystemUiMode.IMMERSIVE_STICKY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiMode[SystemUiMode.EDGE_TO_EDGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[SystemUiOverlay.values().length];
            $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiOverlay = iArr2;
            try {
                iArr2[SystemUiOverlay.TOP_OVERLAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiOverlay[SystemUiOverlay.BOTTOM_OVERLAYS.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr3 = new int[DeviceOrientation.values().length];
            $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$DeviceOrientation = iArr3;
            try {
                iArr3[DeviceOrientation.PORTRAIT_UP.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$DeviceOrientation[DeviceOrientation.PORTRAIT_DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$DeviceOrientation[DeviceOrientation.LANDSCAPE_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$DeviceOrientation[DeviceOrientation.LANDSCAPE_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SystemUiMode decodeSystemUiMode(String str) throws JSONException, NoSuchFieldException {
        int i = AnonymousClass2.$SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiMode[SystemUiMode.fromValue(str).ordinal()];
        if (i == 1) {
            return SystemUiMode.LEAN_BACK;
        }
        if (i == 2) {
            return SystemUiMode.IMMERSIVE;
        }
        if (i == 3) {
            return SystemUiMode.IMMERSIVE_STICKY;
        }
        if (i == 4) {
            return SystemUiMode.EDGE_TO_EDGE;
        }
        return SystemUiMode.EDGE_TO_EDGE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SystemChromeStyle decodeSystemChromeStyle(JSONObject jSONObject) throws JSONException, NoSuchFieldException {
        return new SystemChromeStyle(!jSONObject.isNull("statusBarColor") ? Integer.valueOf(jSONObject.getInt("statusBarColor")) : null, !jSONObject.isNull("statusBarIconBrightness") ? Brightness.fromValue(jSONObject.getString("statusBarIconBrightness")) : null, !jSONObject.isNull("systemStatusBarContrastEnforced") ? Boolean.valueOf(jSONObject.getBoolean("systemStatusBarContrastEnforced")) : null, !jSONObject.isNull("systemNavigationBarColor") ? Integer.valueOf(jSONObject.getInt("systemNavigationBarColor")) : null, !jSONObject.isNull("systemNavigationBarIconBrightness") ? Brightness.fromValue(jSONObject.getString("systemNavigationBarIconBrightness")) : null, !jSONObject.isNull("systemNavigationBarDividerColor") ? Integer.valueOf(jSONObject.getInt("systemNavigationBarDividerColor")) : null, jSONObject.isNull("systemNavigationBarContrastEnforced") ? null : Boolean.valueOf(jSONObject.getBoolean("systemNavigationBarContrastEnforced")));
    }

    /* loaded from: classes5.dex */
    public enum SoundType {
        CLICK("SystemSoundType.click"),
        ALERT("SystemSoundType.alert");

        private final String encodedName;

        static SoundType fromValue(String str) throws NoSuchFieldException {
            for (SoundType soundType : values()) {
                if (soundType.encodedName.equals(str)) {
                    return soundType;
                }
            }
            throw new NoSuchFieldException("No such SoundType: " + str);
        }

        SoundType(String str) {
            this.encodedName = str;
        }
    }

    /* loaded from: classes5.dex */
    public enum HapticFeedbackType {
        STANDARD(null),
        LIGHT_IMPACT("HapticFeedbackType.lightImpact"),
        MEDIUM_IMPACT("HapticFeedbackType.mediumImpact"),
        HEAVY_IMPACT("HapticFeedbackType.heavyImpact"),
        SELECTION_CLICK("HapticFeedbackType.selectionClick");

        private final String encodedName;

        static HapticFeedbackType fromValue(String str) throws NoSuchFieldException {
            for (HapticFeedbackType hapticFeedbackType : values()) {
                String str2 = hapticFeedbackType.encodedName;
                if ((str2 == null && str == null) || (str2 != null && str2.equals(str))) {
                    return hapticFeedbackType;
                }
            }
            throw new NoSuchFieldException("No such HapticFeedbackType: " + str);
        }

        HapticFeedbackType(String str) {
            this.encodedName = str;
        }
    }

    /* loaded from: classes5.dex */
    public enum DeviceOrientation {
        PORTRAIT_UP("DeviceOrientation.portraitUp"),
        PORTRAIT_DOWN("DeviceOrientation.portraitDown"),
        LANDSCAPE_LEFT("DeviceOrientation.landscapeLeft"),
        LANDSCAPE_RIGHT("DeviceOrientation.landscapeRight");

        private String encodedName;

        static DeviceOrientation fromValue(String str) throws NoSuchFieldException {
            for (DeviceOrientation deviceOrientation : values()) {
                if (deviceOrientation.encodedName.equals(str)) {
                    return deviceOrientation;
                }
            }
            throw new NoSuchFieldException("No such DeviceOrientation: " + str);
        }

        DeviceOrientation(String str) {
            this.encodedName = str;
        }
    }

    /* loaded from: classes5.dex */
    public enum SystemUiOverlay {
        TOP_OVERLAYS("SystemUiOverlay.top"),
        BOTTOM_OVERLAYS("SystemUiOverlay.bottom");

        private String encodedName;

        static SystemUiOverlay fromValue(String str) throws NoSuchFieldException {
            for (SystemUiOverlay systemUiOverlay : values()) {
                if (systemUiOverlay.encodedName.equals(str)) {
                    return systemUiOverlay;
                }
            }
            throw new NoSuchFieldException("No such SystemUiOverlay: " + str);
        }

        SystemUiOverlay(String str) {
            this.encodedName = str;
        }
    }

    /* loaded from: classes5.dex */
    public enum SystemUiMode {
        LEAN_BACK("SystemUiMode.leanBack"),
        IMMERSIVE("SystemUiMode.immersive"),
        IMMERSIVE_STICKY("SystemUiMode.immersiveSticky"),
        EDGE_TO_EDGE("SystemUiMode.edgeToEdge");

        private String encodedName;

        static SystemUiMode fromValue(String str) throws NoSuchFieldException {
            for (SystemUiMode systemUiMode : values()) {
                if (systemUiMode.encodedName.equals(str)) {
                    return systemUiMode;
                }
            }
            throw new NoSuchFieldException("No such SystemUiMode: " + str);
        }

        SystemUiMode(String str) {
            this.encodedName = str;
        }
    }

    /* loaded from: classes5.dex */
    public static class AppSwitcherDescription {
        public final int color;
        public final String label;

        public AppSwitcherDescription(int i, String str) {
            this.color = i;
            this.label = str;
        }
    }

    /* loaded from: classes5.dex */
    public static class SystemChromeStyle {
        public final Integer statusBarColor;
        public final Brightness statusBarIconBrightness;
        public final Integer systemNavigationBarColor;
        public final Boolean systemNavigationBarContrastEnforced;
        public final Integer systemNavigationBarDividerColor;
        public final Brightness systemNavigationBarIconBrightness;
        public final Boolean systemStatusBarContrastEnforced;

        public SystemChromeStyle(Integer num, Brightness brightness, Boolean bool, Integer num2, Brightness brightness2, Integer num3, Boolean bool2) {
            this.statusBarColor = num;
            this.statusBarIconBrightness = brightness;
            this.systemStatusBarContrastEnforced = bool;
            this.systemNavigationBarColor = num2;
            this.systemNavigationBarIconBrightness = brightness2;
            this.systemNavigationBarDividerColor = num3;
            this.systemNavigationBarContrastEnforced = bool2;
        }
    }

    /* loaded from: classes5.dex */
    public enum Brightness {
        LIGHT("Brightness.light"),
        DARK("Brightness.dark");

        private String encodedName;

        static Brightness fromValue(String str) throws NoSuchFieldException {
            for (Brightness brightness : values()) {
                if (brightness.encodedName.equals(str)) {
                    return brightness;
                }
            }
            throw new NoSuchFieldException("No such Brightness: " + str);
        }

        Brightness(String str) {
            this.encodedName = str;
        }
    }

    /* loaded from: classes5.dex */
    public enum ClipboardContentFormat {
        PLAIN_TEXT(AssetHelper.DEFAULT_MIME_TYPE);

        private String encodedName;

        static ClipboardContentFormat fromValue(String str) throws NoSuchFieldException {
            for (ClipboardContentFormat clipboardContentFormat : values()) {
                if (clipboardContentFormat.encodedName.equals(str)) {
                    return clipboardContentFormat;
                }
            }
            throw new NoSuchFieldException("No such ClipboardContentFormat: " + str);
        }

        ClipboardContentFormat(String str) {
            this.encodedName = str;
        }
    }
}
