package net.touchcapture.qr.flutterqr;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;
import com.journeyapps.barcodescanner.camera.CameraSettings;
import com.tekartik.sqflite.Constant;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.platform.PlatformView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QRView.kt */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\r\u0018\u0000 L2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001LBA\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\"\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r`\u000e¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J(\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#2\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010&\u001a\u00020\u001eH\u0002J\b\u0010'\u001a\u00020\u001eH\u0016J\u0010\u0010(\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J&\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010*2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010-\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010.\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010/\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u00020\u0016H\u0003J\b\u00103\u001a\u00020\u0016H\u0002J\b\u00104\u001a\u00020\u0016H\u0002J\u0010\u00105\u001a\u00020\u00162\u0006\u00106\u001a\u00020\fH\u0002J\b\u00107\u001a\u00020\u0011H\u0002J\u0018\u00108\u001a\u00020\u001e2\u0006\u00109\u001a\u00020:2\u0006\u0010\u001f\u001a\u00020 H\u0016J-\u0010;\u001a\u00020\u00162\u0006\u0010<\u001a\u00020\t2\u000e\u0010=\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0>2\u0006\u0010?\u001a\u00020@H\u0016¢\u0006\u0002\u0010AJ\u0010\u0010B\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010C\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010D\u001a\u00020\u001e2\u0006\u0010E\u001a\u00020\u0016H\u0002J \u0010F\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010G\u001a\u00020#H\u0002J \u0010H\u001a\u00020\u001e2\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010*2\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010I\u001a\u00020\u001eH\u0002J\u0010\u0010J\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\f\u0010K\u001a\u00020\t*\u00020#H\u0002R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"Lnet/touchcapture/qr/flutterqr/QRView;", "Lio/flutter/plugin/platform/PlatformView;", "Lio/flutter/plugin/common/MethodChannel$MethodCallHandler;", "Lio/flutter/plugin/common/PluginRegistry$RequestPermissionsResultListener;", "context", "Landroid/content/Context;", "messenger", "Lio/flutter/plugin/common/BinaryMessenger;", "id", "", "params", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "(Landroid/content/Context;Lio/flutter/plugin/common/BinaryMessenger;ILjava/util/HashMap;)V", "barcodeView", "Lnet/touchcapture/qr/flutterqr/CustomFramingRectBarcodeView;", "cameraRequestCode", "channel", "Lio/flutter/plugin/common/MethodChannel;", "hasCameraPermission", "", "getHasCameraPermission", "()Z", "isPaused", "isTorchOn", "unRegisterLifecycleCallback", "Lnet/touchcapture/qr/flutterqr/UnRegisterLifecycleCallback;", "barCodeViewNotSet", "", Constant.PARAM_RESULT, "Lio/flutter/plugin/common/MethodChannel$Result;", "changeScanArea", "dpScanAreaWidth", "", "dpScanAreaHeight", "cutOutBottomOffset", "checkAndRequestPermission", "dispose", "flipCamera", "getAllowedBarcodeTypes", "", "Lcom/google/zxing/BarcodeFormat;", Constant.PARAM_SQL_ARGUMENTS, "getCameraInfo", "getFlashInfo", "getSystemFeatures", "getView", "Landroid/view/View;", "hasBackCamera", "hasFlash", "hasFrontCamera", "hasSystemFeature", "feature", "initBarCodeView", "onMethodCall", NotificationCompat.CATEGORY_CALL, "Lio/flutter/plugin/common/MethodCall;", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)Z", "pauseCamera", "resumeCamera", "setInvertScan", "isInvert", "setScanAreaSize", "dpCutOutBottomOffset", "startScan", "stopScan", "toggleFlash", "convertDpToPixels", "Companion", "qr_code_scanner_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class QRView implements PlatformView, MethodChannel.MethodCallHandler, PluginRegistry.RequestPermissionsResultListener {
    private static final String CHANNEL_METHOD_ON_PERMISSION_SET = "onPermissionSet";
    private static final String CHANNEL_METHOD_ON_RECOGNIZE_QR = "onRecognizeQR";
    private static final String ERROR_CODE_NOT_SET = "404";
    private static final String ERROR_MESSAGE_FLASH_NOT_FOUND = "This device doesn't support flash";
    private static final String ERROR_MESSAGE_NOT_SET = "No barcode view found";
    private static final String PARAMS_CAMERA_FACING = "cameraFacing";
    private CustomFramingRectBarcodeView barcodeView;
    private final int cameraRequestCode;
    private final MethodChannel channel;
    private final Context context;
    private final int id;
    private boolean isPaused;
    private boolean isTorchOn;
    private final HashMap<String, Object> params;
    private UnRegisterLifecycleCallback unRegisterLifecycleCallback;

    @Override // io.flutter.plugin.platform.PlatformView
    public /* synthetic */ void onFlutterViewAttached(View view) {
        PlatformView.CC.$default$onFlutterViewAttached(this, view);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public /* synthetic */ void onFlutterViewDetached() {
        PlatformView.CC.$default$onFlutterViewDetached(this);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public /* synthetic */ void onInputConnectionLocked() {
        PlatformView.CC.$default$onInputConnectionLocked(this);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public /* synthetic */ void onInputConnectionUnlocked() {
        PlatformView.CC.$default$onInputConnectionUnlocked(this);
    }

    public QRView(Context context, BinaryMessenger messenger, int i, HashMap<String, Object> params) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(messenger, "messenger");
        Intrinsics.checkNotNullParameter(params, "params");
        this.context = context;
        this.id = i;
        this.params = params;
        MethodChannel methodChannel = new MethodChannel(messenger, "net.touchcapture.qr.flutterqr/qrview_" + i);
        this.channel = methodChannel;
        this.cameraRequestCode = i + QrShared.CAMERA_REQUEST_ID;
        ActivityPluginBinding binding = QrShared.INSTANCE.getBinding();
        if (binding != null) {
            binding.addRequestPermissionsResultListener(this);
        }
        methodChannel.setMethodCallHandler(this);
        Activity activity = QrShared.INSTANCE.getActivity();
        this.unRegisterLifecycleCallback = activity != null ? QrActivityLifecycleCallbacksKt.registerLifecycleCallbacks(activity, new Function0<Unit>() { // from class: net.touchcapture.qr.flutterqr.QRView.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                CustomFramingRectBarcodeView customFramingRectBarcodeView;
                if (QRView.this.isPaused || !QRView.this.getHasCameraPermission() || (customFramingRectBarcodeView = QRView.this.barcodeView) == null) {
                    return;
                }
                customFramingRectBarcodeView.pause();
            }
        }, new Function0<Unit>() { // from class: net.touchcapture.qr.flutterqr.QRView.2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                CustomFramingRectBarcodeView customFramingRectBarcodeView;
                if (!QRView.this.getHasCameraPermission()) {
                    QRView.this.checkAndRequestPermission();
                } else {
                    if (QRView.this.isPaused || !QRView.this.getHasCameraPermission() || (customFramingRectBarcodeView = QRView.this.barcodeView) == null) {
                        return;
                    }
                    customFramingRectBarcodeView.resume();
                }
            }
        }) : null;
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public void dispose() {
        UnRegisterLifecycleCallback unRegisterLifecycleCallback = this.unRegisterLifecycleCallback;
        if (unRegisterLifecycleCallback != null) {
            unRegisterLifecycleCallback.invoke();
        }
        ActivityPluginBinding binding = QrShared.INSTANCE.getBinding();
        if (binding != null) {
            binding.removeRequestPermissionsResultListener(this);
        }
        CustomFramingRectBarcodeView customFramingRectBarcodeView = this.barcodeView;
        if (customFramingRectBarcodeView != null) {
            customFramingRectBarcodeView.pause();
        }
        this.barcodeView = null;
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public View getView() {
        return initBarCodeView();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0012. Please report as an issue. */
    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(result, "result");
        String str = call.method;
        if (str != null) {
            switch (str.hashCode()) {
                case -2129330689:
                    if (str.equals("startScan")) {
                        Object obj = call.arguments;
                        startScan(obj instanceof List ? (List) obj : null, result);
                        return;
                    }
                    break;
                case -2110134142:
                    if (str.equals("getSystemFeatures")) {
                        getSystemFeatures(result);
                        return;
                    }
                    break;
                case -1824838201:
                    if (str.equals("stopCamera")) {
                        pauseCamera(result);
                        return;
                    }
                    break;
                case -1176613766:
                    if (str.equals("changeScanArea")) {
                        Object argument = call.argument("scanAreaWidth");
                        if (argument == null) {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                        Intrinsics.checkNotNullExpressionValue(argument, "requireNotNull(call.argu…Double>(\"scanAreaWidth\"))");
                        double doubleValue = ((Number) argument).doubleValue();
                        Object argument2 = call.argument("scanAreaHeight");
                        if (argument2 == null) {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                        Intrinsics.checkNotNullExpressionValue(argument2, "requireNotNull(call.argu…ouble>(\"scanAreaHeight\"))");
                        double doubleValue2 = ((Number) argument2).doubleValue();
                        Object argument3 = call.argument("cutOutBottomOffset");
                        if (argument3 == null) {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                        Intrinsics.checkNotNullExpressionValue(argument3, "requireNotNull(call.argu…e>(\"cutOutBottomOffset\"))");
                        changeScanArea(doubleValue, doubleValue2, ((Number) argument3).doubleValue(), result);
                        return;
                    }
                    break;
                case -1157185016:
                    if (str.equals("getFlashInfo")) {
                        getFlashInfo(result);
                        return;
                    }
                    break;
                case -668845828:
                    if (str.equals("toggleFlash")) {
                        toggleFlash(result);
                        return;
                    }
                    break;
                case 437643762:
                    if (str.equals("flipCamera")) {
                        flipCamera(result);
                        return;
                    }
                    break;
                case 1026482610:
                    if (str.equals("resumeCamera")) {
                        resumeCamera(result);
                        return;
                    }
                    break;
                case 1669188213:
                    if (str.equals("requestPermissions")) {
                        checkAndRequestPermission();
                        return;
                    }
                    break;
                case 1714778527:
                    if (str.equals("stopScan")) {
                        stopScan();
                        return;
                    }
                    break;
                case 1899593587:
                    if (str.equals("invertScan")) {
                        Boolean bool = (Boolean) call.argument("isInvertScan");
                        if (bool == null) {
                            bool = false;
                        }
                        setInvertScan(bool.booleanValue());
                        return;
                    }
                    break;
                case 1984772457:
                    if (str.equals("getCameraInfo")) {
                        getCameraInfo(result);
                        return;
                    }
                    break;
                case 2013529275:
                    if (str.equals("pauseCamera")) {
                        pauseCamera(result);
                        return;
                    }
                    break;
            }
        }
        result.notImplemented();
    }

    private final CustomFramingRectBarcodeView initBarCodeView() {
        CameraSettings cameraSettings;
        CustomFramingRectBarcodeView customFramingRectBarcodeView = this.barcodeView;
        if (customFramingRectBarcodeView == null) {
            customFramingRectBarcodeView = new CustomFramingRectBarcodeView(QrShared.INSTANCE.getActivity());
            this.barcodeView = customFramingRectBarcodeView;
            customFramingRectBarcodeView.setDecoderFactory(new DefaultDecoderFactory(null, null, null, 2));
            Object obj = this.params.get(PARAMS_CAMERA_FACING);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            if (((Integer) obj).intValue() == 1 && (cameraSettings = customFramingRectBarcodeView.getCameraSettings()) != null) {
                cameraSettings.setRequestedCameraId(1);
            }
        } else if (!this.isPaused) {
            customFramingRectBarcodeView.resume();
        }
        return customFramingRectBarcodeView;
    }

    private final void getCameraInfo(MethodChannel.Result result) {
        CustomFramingRectBarcodeView customFramingRectBarcodeView = this.barcodeView;
        if (customFramingRectBarcodeView == null) {
            barCodeViewNotSet(result);
        } else {
            result.success(Integer.valueOf(customFramingRectBarcodeView.getCameraSettings().getRequestedCameraId()));
        }
    }

    private final void getFlashInfo(MethodChannel.Result result) {
        if (this.barcodeView == null) {
            barCodeViewNotSet(result);
        } else {
            result.success(Boolean.valueOf(this.isTorchOn));
        }
    }

    private final boolean hasFlash() {
        return hasSystemFeature("android.hardware.camera.flash");
    }

    private final boolean hasBackCamera() {
        return hasSystemFeature("android.hardware.camera");
    }

    private final boolean hasFrontCamera() {
        return hasSystemFeature("android.hardware.camera.front");
    }

    private final boolean hasSystemFeature(String feature) {
        return this.context.getPackageManager().hasSystemFeature(feature);
    }

    private final void getSystemFeatures(MethodChannel.Result result) {
        CameraSettings cameraSettings;
        try {
            Pair[] pairArr = new Pair[4];
            pairArr[0] = TuplesKt.to("hasFrontCamera", Boolean.valueOf(hasFrontCamera()));
            pairArr[1] = TuplesKt.to("hasBackCamera", Boolean.valueOf(hasBackCamera()));
            pairArr[2] = TuplesKt.to("hasFlash", Boolean.valueOf(hasFlash()));
            CustomFramingRectBarcodeView customFramingRectBarcodeView = this.barcodeView;
            pairArr[3] = TuplesKt.to("activeCamera", (customFramingRectBarcodeView == null || (cameraSettings = customFramingRectBarcodeView.getCameraSettings()) == null) ? null : Integer.valueOf(cameraSettings.getRequestedCameraId()));
            result.success(MapsKt.mapOf(pairArr));
        } catch (Exception e) {
            result.error("", e.getMessage(), null);
        }
    }

    private final void flipCamera(MethodChannel.Result result) {
        CustomFramingRectBarcodeView customFramingRectBarcodeView = this.barcodeView;
        if (customFramingRectBarcodeView == null) {
            barCodeViewNotSet(result);
            return;
        }
        customFramingRectBarcodeView.pause();
        CameraSettings cameraSettings = customFramingRectBarcodeView.getCameraSettings();
        if (cameraSettings.getRequestedCameraId() == 1) {
            cameraSettings.setRequestedCameraId(0);
        } else {
            cameraSettings.setRequestedCameraId(1);
        }
        customFramingRectBarcodeView.resume();
        result.success(Integer.valueOf(cameraSettings.getRequestedCameraId()));
    }

    private final void toggleFlash(MethodChannel.Result result) {
        CustomFramingRectBarcodeView customFramingRectBarcodeView = this.barcodeView;
        if (customFramingRectBarcodeView == null) {
            barCodeViewNotSet(result);
            return;
        }
        if (hasFlash()) {
            customFramingRectBarcodeView.setTorch(!this.isTorchOn);
            boolean z = !this.isTorchOn;
            this.isTorchOn = z;
            result.success(Boolean.valueOf(z));
            return;
        }
        result.error(ERROR_CODE_NOT_SET, ERROR_MESSAGE_FLASH_NOT_FOUND, null);
    }

    private final void pauseCamera(MethodChannel.Result result) {
        CustomFramingRectBarcodeView customFramingRectBarcodeView = this.barcodeView;
        if (customFramingRectBarcodeView == null) {
            barCodeViewNotSet(result);
            return;
        }
        if (customFramingRectBarcodeView.isPreviewActive()) {
            this.isPaused = true;
            customFramingRectBarcodeView.pause();
        }
        result.success(true);
    }

    private final void resumeCamera(MethodChannel.Result result) {
        CustomFramingRectBarcodeView customFramingRectBarcodeView = this.barcodeView;
        if (customFramingRectBarcodeView == null) {
            barCodeViewNotSet(result);
            return;
        }
        if (!customFramingRectBarcodeView.isPreviewActive()) {
            this.isPaused = false;
            customFramingRectBarcodeView.resume();
        }
        result.success(true);
    }

    private final void startScan(List<Integer> arguments, MethodChannel.Result result) {
        checkAndRequestPermission();
        final List<BarcodeFormat> allowedBarcodeTypes = getAllowedBarcodeTypes(arguments, result);
        CustomFramingRectBarcodeView customFramingRectBarcodeView = this.barcodeView;
        if (customFramingRectBarcodeView != null) {
            customFramingRectBarcodeView.decodeContinuous(new BarcodeCallback() { // from class: net.touchcapture.qr.flutterqr.QRView$startScan$1
                @Override // com.journeyapps.barcodescanner.BarcodeCallback
                public void possibleResultPoints(List<? extends ResultPoint> resultPoints) {
                    Intrinsics.checkNotNullParameter(resultPoints, "resultPoints");
                }

                @Override // com.journeyapps.barcodescanner.BarcodeCallback
                public void barcodeResult(BarcodeResult result2) {
                    MethodChannel methodChannel;
                    Intrinsics.checkNotNullParameter(result2, "result");
                    if (allowedBarcodeTypes.isEmpty() || allowedBarcodeTypes.contains(result2.getBarcodeFormat())) {
                        Map mapOf = MapsKt.mapOf(TuplesKt.to("code", result2.getText()), TuplesKt.to("type", result2.getBarcodeFormat().name()), TuplesKt.to("rawBytes", result2.getRawBytes()));
                        methodChannel = this.channel;
                        methodChannel.invokeMethod("onRecognizeQR", mapOf);
                    }
                }
            });
        }
    }

    private final void stopScan() {
        CustomFramingRectBarcodeView customFramingRectBarcodeView = this.barcodeView;
        if (customFramingRectBarcodeView != null) {
            customFramingRectBarcodeView.stopDecoding();
        }
    }

    private final void setInvertScan(boolean isInvert) {
        CustomFramingRectBarcodeView customFramingRectBarcodeView = this.barcodeView;
        if (customFramingRectBarcodeView == null) {
            return;
        }
        customFramingRectBarcodeView.pause();
        customFramingRectBarcodeView.getCameraSettings().setScanInverted(isInvert);
        customFramingRectBarcodeView.resume();
    }

    private final void changeScanArea(double dpScanAreaWidth, double dpScanAreaHeight, double cutOutBottomOffset, MethodChannel.Result result) {
        setScanAreaSize(dpScanAreaWidth, dpScanAreaHeight, cutOutBottomOffset);
        result.success(true);
    }

    private final void setScanAreaSize(double dpScanAreaWidth, double dpScanAreaHeight, double dpCutOutBottomOffset) {
        CustomFramingRectBarcodeView customFramingRectBarcodeView = this.barcodeView;
        if (customFramingRectBarcodeView != null) {
            customFramingRectBarcodeView.setFramingRect(convertDpToPixels(dpScanAreaWidth), convertDpToPixels(dpScanAreaHeight), convertDpToPixels(dpCutOutBottomOffset));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasCameraPermission() {
        return Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(this.context, "android.permission.CAMERA") == 0;
    }

    @Override // io.flutter.plugin.common.PluginRegistry.RequestPermissionsResultListener
    public boolean onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        boolean z = false;
        if (requestCode != this.cameraRequestCode) {
            return false;
        }
        Integer firstOrNull = ArraysKt.firstOrNull(grantResults);
        if (firstOrNull != null && firstOrNull.intValue() == 0) {
            z = true;
        }
        this.channel.invokeMethod(CHANNEL_METHOD_ON_PERMISSION_SET, Boolean.valueOf(z));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkAndRequestPermission() {
        Activity activity;
        if (getHasCameraPermission()) {
            this.channel.invokeMethod(CHANNEL_METHOD_ON_PERMISSION_SET, true);
        } else {
            if (Build.VERSION.SDK_INT < 23 || (activity = QrShared.INSTANCE.getActivity()) == null) {
                return;
            }
            activity.requestPermissions(new String[]{"android.permission.CAMERA"}, this.cameraRequestCode);
        }
    }

    private final List<BarcodeFormat> getAllowedBarcodeTypes(List<Integer> arguments, MethodChannel.Result result) {
        ArrayList arrayList;
        if (arguments != null) {
            try {
                List<Integer> list = arguments;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList2.add(BarcodeFormat.values()[((Number) it.next()).intValue()]);
                }
                arrayList = arrayList2;
            } catch (Exception e) {
                result.error("", e.getMessage(), null);
                return CollectionsKt.emptyList();
            }
        } else {
            arrayList = null;
        }
        if (arrayList == null) {
            arrayList = CollectionsKt.emptyList();
        }
        return arrayList;
    }

    private final void barCodeViewNotSet(MethodChannel.Result result) {
        result.error(ERROR_CODE_NOT_SET, ERROR_MESSAGE_NOT_SET, null);
    }

    private final int convertDpToPixels(double d) {
        return (int) (d * this.context.getResources().getDisplayMetrics().density);
    }
}
