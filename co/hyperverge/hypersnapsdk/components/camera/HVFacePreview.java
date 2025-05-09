package co.hyperverge.hypersnapsdk.components.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import co.hyperverge.hvcamera.HVMagicView;
import co.hyperverge.hvcamera.magicfilter.camera.CameraEngine;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import co.hyperverge.hypersnapsdk.components.camera.model.FaceStateUIFlow;
import co.hyperverge.hypersnapsdk.components.camera.model.HVCamConfig;
import co.hyperverge.hypersnapsdk.helpers.HVCameraHelper;
import co.hyperverge.hypersnapsdk.helpers.face.FaceConstants;
import co.hyperverge.hypersnapsdk.helpers.face.FaceDetectionListener;
import co.hyperverge.hypersnapsdk.helpers.face.FaceViewListener;
import co.hyperverge.hypersnapsdk.helpers.face.MLKitFaceHelper;
import co.hyperverge.hypersnapsdk.model.CameraProperties;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.views.CircularProgressBar;
import co.hyperverge.hypersnapsdk.views.CrossHairView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: HVFacePreview.kt */
@Metadata(d1 = {"\u0000«\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000*\u0001 \u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u001f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010*\u001a\u00020+2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010,\u001a\u00020+H\u0002J\u0006\u0010-\u001a\u00020+J\u0012\u0010.\u001a\u00020+2\b\u0010/\u001a\u0004\u0018\u000100H\u0014J\u000e\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e02J\b\u00103\u001a\u00020\u0010H\u0016J\b\u00104\u001a\u00020%H\u0016J\b\u00105\u001a\u00020%H\u0016J\b\u00106\u001a\u00020%H\u0016J\u0010\u00107\u001a\u00020+2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u00108\u001a\u00020+H\u0014J\u0010\u00109\u001a\u00020+2\u0006\u0010:\u001a\u00020;H\u0016J\u0010\u0010<\u001a\u00020+2\u0006\u0010:\u001a\u00020;H\u0016J\u0010\u0010=\u001a\u00020+2\u0006\u0010>\u001a\u00020?H\u0002J\u0012\u0010@\u001a\u00020+2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0006\u0010A\u001a\u00020+J\u0011\u0010B\u001a\u00020#H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010CJ\b\u0010D\u001a\u00020+H\u0002J\u000e\u0010E\u001a\u0004\u0018\u00010;*\u00020FH\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010!R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006G"}, d2 = {"Lco/hyperverge/hypersnapsdk/components/camera/HVFacePreview;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "Lco/hyperverge/hypersnapsdk/helpers/face/FaceViewListener;", "Lco/hyperverge/hypersnapsdk/helpers/face/FaceDetectionListener;", "context", "Landroid/content/Context;", HVRetakeActivity.CONFIG_TAG, "Lco/hyperverge/hypersnapsdk/components/camera/model/HVCamConfig;", "lifeCycleScope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroid/content/Context;Lco/hyperverge/hypersnapsdk/components/camera/model/HVCamConfig;Lkotlinx/coroutines/CoroutineScope;)V", "TAG", "", "camConfig", "camViewHeight", "", "camViewWidth", "cameraCheckDelay", "", "cameraView", "Lco/hyperverge/hvcamera/HVMagicView;", "clipPath", "Landroid/graphics/Path;", "crossHairView", "Lco/hyperverge/hypersnapsdk/views/CrossHairView;", "faceDetectionState", "Lco/hyperverge/hypersnapsdk/helpers/face/FaceConstants$FaceDetectionState;", "faceUIStateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lco/hyperverge/hypersnapsdk/components/camera/model/FaceStateUIFlow;", "hvCamHost", "co/hyperverge/hypersnapsdk/components/camera/HVFacePreview$hvCamHost$1", "Lco/hyperverge/hypersnapsdk/components/camera/HVFacePreview$hvCamHost$1;", "isCheckingCamera", "", "lastTouchX", "", "lastTouchY", "progressBar", "Lco/hyperverge/hypersnapsdk/views/CircularProgressBar;", "safeToTakePicture", "addCrossHairView", "", "adjustCrossHairView", "clickPicture", "dispatchDraw", "canvas", "Landroid/graphics/Canvas;", "getUiStateFlow", "Lkotlinx/coroutines/flow/StateFlow;", "getViewRadius", "getViewX", "getViewY", "getViewYCenter", "initialize", "onAttachedToWindow", "onPause", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onResume", "processFrame", "properties", "Lco/hyperverge/hypersnapsdk/model/CameraProperties;", "setFaceDetectionState", "startCamera", "stopCamera", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "waitForCameraClose", "findViewTreeLifecycleOwner", "Landroid/view/View;", "hypersnapsdk_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class HVFacePreview extends ConstraintLayout implements DefaultLifecycleObserver, FaceViewListener, FaceDetectionListener {
    private final String TAG;
    private HVCamConfig camConfig;
    private int camViewHeight;
    private int camViewWidth;
    private final long cameraCheckDelay;
    private HVMagicView cameraView;
    private Path clipPath;
    private CrossHairView crossHairView;
    private FaceConstants.FaceDetectionState faceDetectionState;
    private final MutableStateFlow<FaceStateUIFlow> faceUIStateFlow;
    private final HVFacePreview$hvCamHost$1 hvCamHost;
    private boolean isCheckingCamera;
    private float lastTouchX;
    private float lastTouchY;
    private CoroutineScope lifeCycleScope;
    private CircularProgressBar progressBar;
    private boolean safeToTakePicture;

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onDestroy(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onStart(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onStop(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    public final StateFlow<FaceStateUIFlow> getUiStateFlow() {
        return this.faceUIStateFlow;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HVFacePreview(Context context, HVCamConfig config, CoroutineScope lifeCycleScope) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(lifeCycleScope, "lifeCycleScope");
        this.TAG = "HVFacePreview";
        this.safeToTakePicture = true;
        this.cameraCheckDelay = 20L;
        this.faceDetectionState = FaceConstants.FaceDetectionState.FACE_NOT_DETECTED;
        this.faceUIStateFlow = StateFlowKt.MutableStateFlow(null);
        this.hvCamHost = new HVFacePreview$hvCamHost$1(this);
        this.camConfig = config;
        setId(R.id.hv_face_preview);
        this.lifeCycleScope = lifeCycleScope;
        MLKitFaceHelper.get().setConfig(new HVFaceConfig(), this, this);
        invalidate();
        setWillNotDraw(false);
        initialize(context);
    }

    private final void initialize(Context context) {
        Lifecycle lifecycle;
        HVLogUtils.d(this.TAG, Intrinsics.stringPlus("initialize() called with: context = ", context));
        HVCamConfig hVCamConfig = this.camConfig;
        HVMagicView hVMagicView = null;
        if (hVCamConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camConfig");
            hVCamConfig = null;
        }
        HVCameraHelper.enableCameraParameters(context, hVCamConfig.getUseBackCamera());
        CameraEngine.setPreviewCallback(true);
        CameraEngine.setCaptureMode(true);
        ConstraintSet constraintSet = new ConstraintSet();
        HVFacePreview hVFacePreview = this;
        constraintSet.clone(hVFacePreview);
        CircularProgressBar circularProgressBar = new CircularProgressBar(context);
        circularProgressBar.setId(R.id.hv_circular_bar);
        HVCamConfig hVCamConfig2 = this.camConfig;
        if (hVCamConfig2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camConfig");
            hVCamConfig2 = null;
        }
        circularProgressBar.setDiameter(hVCamConfig2.getDiameter());
        circularProgressBar.setProgress(100);
        circularProgressBar.setMaxProgress(100);
        Unit unit = Unit.INSTANCE;
        this.progressBar = circularProgressBar;
        HVFacePreview$hvCamHost$1 hVFacePreview$hvCamHost$1 = this.hvCamHost;
        HVCamConfig hVCamConfig3 = this.camConfig;
        if (hVCamConfig3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camConfig");
            hVCamConfig3 = null;
        }
        HVMagicView hVMagicView2 = HVMagicView.getInstance(context, hVFacePreview$hvCamHost$1, true ^ hVCamConfig3.getUseBackCamera());
        hVMagicView2.disableRotation();
        hVMagicView2.setId(R.id.hv_camera_view);
        hVMagicView2.setContentDescription("hvFacePreview");
        hVMagicView2.setSensorCallback(new HVMagicView.SensorCallback() { // from class: co.hyperverge.hypersnapsdk.components.camera.HVFacePreview$$ExternalSyntheticLambda1
            @Override // co.hyperverge.hvcamera.HVMagicView.SensorCallback
            public final void onSensorCallback() {
                HVFacePreview.m509initialize$lambda2$lambda1(HVFacePreview.this);
            }
        });
        Unit unit2 = Unit.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(hVMagicView2, "getInstance(context, hvC…          }\n            }");
        this.cameraView = hVMagicView2;
        if (hVMagicView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraView");
            hVMagicView2 = null;
        }
        addView(hVMagicView2);
        HVMagicView hVMagicView3 = this.cameraView;
        if (hVMagicView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraView");
            hVMagicView3 = null;
        }
        int id2 = hVMagicView3.getId();
        HVCamConfig hVCamConfig4 = this.camConfig;
        if (hVCamConfig4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camConfig");
            hVCamConfig4 = null;
        }
        constraintSet.constrainWidth(id2, hVCamConfig4.getDiameter());
        HVMagicView hVMagicView4 = this.cameraView;
        if (hVMagicView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraView");
            hVMagicView4 = null;
        }
        int id3 = hVMagicView4.getId();
        HVCamConfig hVCamConfig5 = this.camConfig;
        if (hVCamConfig5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camConfig");
            hVCamConfig5 = null;
        }
        constraintSet.constrainHeight(id3, hVCamConfig5.getDiameter());
        constraintSet.applyTo(hVFacePreview);
        HVMagicView hVMagicView5 = this.cameraView;
        if (hVMagicView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraView");
            hVMagicView5 = null;
        }
        hVMagicView5.bringToFront();
        addCrossHairView(context);
        adjustCrossHairView();
        HVMagicView hVMagicView6 = this.cameraView;
        if (hVMagicView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraView");
        } else {
            hVMagicView = hVMagicView6;
        }
        hVMagicView.onResume();
        LifecycleOwner findViewTreeLifecycleOwner = findViewTreeLifecycleOwner(this);
        if (findViewTreeLifecycleOwner != null && (lifecycle = findViewTreeLifecycleOwner.getLifecycle()) != null) {
            lifecycle.addObserver(this);
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initialize$lambda-2$lambda-1, reason: not valid java name */
    public static final void m509initialize$lambda2$lambda1(HVFacePreview this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.crossHairView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("crossHairView");
        }
        CrossHairView crossHairView = this$0.crossHairView;
        if (crossHairView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("crossHairView");
            crossHairView = null;
        }
        crossHairView.showCrosshair(this$0.camViewWidth / 2, this$0.camViewHeight / 2, false);
    }

    public final void startCamera() {
        HVLogUtils.d(this.TAG, "startCamera() called");
        HVMagicView hVMagicView = this.cameraView;
        if (hVMagicView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraView");
            hVMagicView = null;
        }
        hVMagicView.onResume();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        HVLogUtils.d(this.TAG, "onAttachedToWindow() called");
        if (getParent() == null) {
            return;
        }
        CircularProgressBar circularProgressBar = this.progressBar;
        CircularProgressBar circularProgressBar2 = null;
        if (circularProgressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            circularProgressBar = null;
        }
        addView(circularProgressBar);
        CircularProgressBar circularProgressBar3 = this.progressBar;
        if (circularProgressBar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
        } else {
            circularProgressBar2 = circularProgressBar3;
        }
        circularProgressBar2.invalidate();
    }

    public final void clickPicture() {
        HVLogUtils.d(this.TAG, "clickPicture() called");
        HVMagicView hVMagicView = this.cameraView;
        if (hVMagicView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraView");
            hVMagicView = null;
        }
        hVMagicView.takePicture(null);
    }

    public final Object stopCamera(Continuation<? super Boolean> continuation) {
        HVLogUtils.d(this.TAG, "stopCamera() called");
        HVMagicView hVMagicView = this.cameraView;
        HVMagicView hVMagicView2 = null;
        if (hVMagicView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraView");
            hVMagicView = null;
        }
        hVMagicView.onDestroy();
        HVMagicView hVMagicView3 = this.cameraView;
        if (hVMagicView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraView");
        } else {
            hVMagicView2 = hVMagicView3;
        }
        hVMagicView2.onPause();
        waitForCameraClose();
        return Boxing.boxBoolean(true);
    }

    private final void waitForCameraClose() {
        HVLogUtils.d(this.TAG, "waitForCameraClose() called");
        if (this.isCheckingCamera) {
            HVLogUtils.d(this.TAG, "waitForCameraClose: already checking");
            return;
        }
        if (CameraEngine.isCameraReleased()) {
            HVLogUtils.d(this.TAG, "waitForCameraClose: camera is released");
            this.isCheckingCamera = true;
            MLKitFaceHelper.get().destroy();
            HVMagicView hVMagicView = this.cameraView;
            if (hVMagicView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cameraView");
                hVMagicView = null;
            }
            hVMagicView.clearHandlers();
            return;
        }
        HVLogUtils.d(this.TAG, "waitForCameraClose: camera is not released");
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: co.hyperverge.hypersnapsdk.components.camera.HVFacePreview$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                HVFacePreview.m510waitForCameraClose$lambda4(HVFacePreview.this);
            }
        }, this.cameraCheckDelay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: waitForCameraClose$lambda-4, reason: not valid java name */
    public static final void m510waitForCameraClose$lambda4(HVFacePreview this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.waitForCameraClose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processFrame(CameraProperties properties) {
        MLKitFaceHelper.get().processFrame(properties);
    }

    private final LifecycleOwner findViewTreeLifecycleOwner(View view) {
        return ViewTreeLifecycleOwner.get(view);
    }

    private final void addCrossHairView(Context context) {
        HVLogUtils.d(this.TAG, Intrinsics.stringPlus("addCrossHairView() called with: context = ", context));
        CrossHairView crossHairView = new CrossHairView(context);
        crossHairView.setId(R.id.hv_crosshair);
        Unit unit = Unit.INSTANCE;
        this.crossHairView = crossHairView;
        removeView(crossHairView);
        CrossHairView crossHairView2 = this.crossHairView;
        HVCamConfig hVCamConfig = null;
        if (crossHairView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("crossHairView");
            crossHairView2 = null;
        }
        addView(crossHairView2);
        CrossHairView crossHairView3 = this.crossHairView;
        if (crossHairView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("crossHairView");
            crossHairView3 = null;
        }
        HVCamConfig hVCamConfig2 = this.camConfig;
        if (hVCamConfig2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camConfig");
        } else {
            hVCamConfig = hVCamConfig2;
        }
        crossHairView3.setVisibility(hVCamConfig.getUseBackCamera() ? 0 : 8);
        setOnTouchListener(new View.OnTouchListener() { // from class: co.hyperverge.hypersnapsdk.components.camera.HVFacePreview$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean m508addCrossHairView$lambda6;
                m508addCrossHairView$lambda6 = HVFacePreview.m508addCrossHairView$lambda6(HVFacePreview.this, view, motionEvent);
                return m508addCrossHairView$lambda6;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addCrossHairView$lambda-6, reason: not valid java name */
    public static final boolean m508addCrossHairView$lambda6(HVFacePreview this$0, View view, MotionEvent event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(event, "event");
        int actionMasked = event.getActionMasked();
        if (actionMasked == 0) {
            this$0.lastTouchX = event.getX();
            this$0.lastTouchY = event.getY();
        } else if (actionMasked == 1 && Math.abs(event.getX() - this$0.lastTouchX) < 20.0f && Math.abs(event.getY() - this$0.lastTouchY) < 20.0f) {
            CrossHairView crossHairView = this$0.crossHairView;
            if (crossHairView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("crossHairView");
                crossHairView = null;
            }
            crossHairView.showCrosshair(event.getX(), event.getY(), false);
            if (this$0.cameraView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cameraView");
            }
            HVMagicView hVMagicView = this$0.cameraView;
            if (hVMagicView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cameraView");
                hVMagicView = null;
            }
            hVMagicView.onTouchToFocus(event.getX() / this$0.camViewWidth, event.getY() / this$0.camViewHeight, null);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        HVLogUtils.d(this.TAG, Intrinsics.stringPlus("dispatchDraw() called with: canvas = ", canvas));
        Path path = new Path();
        float width = getWidth() / 2;
        float height = getHeight() / 2;
        HVCamConfig hVCamConfig = this.camConfig;
        Path path2 = null;
        if (hVCamConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camConfig");
            hVCamConfig = null;
        }
        path.addCircle(width, height, hVCamConfig.getDiameter() / 2, Path.Direction.CW);
        Unit unit = Unit.INSTANCE;
        this.clipPath = path;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        if (canvas != null) {
            Path path3 = this.clipPath;
            if (path3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("clipPath");
                path3 = null;
            }
            canvas.clipPath(path3);
        }
        if (canvas != null) {
            Path path4 = this.clipPath;
            if (path4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("clipPath");
            } else {
                path2 = path4;
            }
            canvas.drawPath(path2, paint);
        }
        super.dispatchDraw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void adjustCrossHairView() {
        HVLogUtils.d(this.TAG, "adjustCrossHairView() called");
        HVCamConfig hVCamConfig = this.camConfig;
        HVMagicView hVMagicView = null;
        if (hVCamConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camConfig");
            hVCamConfig = null;
        }
        if (hVCamConfig.getUseBackCamera()) {
            CrossHairView crossHairView = this.crossHairView;
            if (crossHairView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("crossHairView");
                crossHairView = null;
            }
            crossHairView.getLayoutParams().height = this.camViewHeight;
            crossHairView.getLayoutParams().width = this.camViewWidth;
            HVMagicView hVMagicView2 = this.cameraView;
            if (hVMagicView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cameraView");
                hVMagicView2 = null;
            }
            crossHairView.setX(hVMagicView2.getX());
            HVMagicView hVMagicView3 = this.cameraView;
            if (hVMagicView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cameraView");
            } else {
                hVMagicView = hVMagicView3;
            }
            crossHairView.setY(hVMagicView.getY());
            crossHairView.requestLayout();
            requestLayout();
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onResume(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        HVLogUtils.d(this.TAG, Intrinsics.stringPlus("onResume() called with: owner = ", owner));
        HVMagicView hVMagicView = this.cameraView;
        if (hVMagicView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraView");
            hVMagicView = null;
        }
        hVMagicView.onResume();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onPause(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        HVLogUtils.d(this.TAG, Intrinsics.stringPlus("onPause() called with: owner = ", owner));
        HVMagicView hVMagicView = this.cameraView;
        if (hVMagicView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraView");
            hVMagicView = null;
        }
        hVMagicView.onPause();
    }

    @Override // co.hyperverge.hypersnapsdk.helpers.face.FaceViewListener
    public float getViewY() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        float f = iArr[1];
        HVLogUtils.d(this.TAG, Intrinsics.stringPlus("getViewY() returned: ", Float.valueOf(f)));
        return f;
    }

    @Override // co.hyperverge.hypersnapsdk.helpers.face.FaceViewListener
    public float getViewX() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        float f = iArr[0];
        HVLogUtils.d(this.TAG, Intrinsics.stringPlus("getViewX() returned: ", Float.valueOf(f)));
        return f;
    }

    @Override // co.hyperverge.hypersnapsdk.helpers.face.FaceViewListener
    public float getViewYCenter() {
        float viewY = getViewY();
        HVCamConfig hVCamConfig = this.camConfig;
        if (hVCamConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camConfig");
            hVCamConfig = null;
        }
        float diameter = viewY + (hVCamConfig.getDiameter() / 2);
        HVLogUtils.d(this.TAG, Intrinsics.stringPlus("getViewYCenter() returned: ", Float.valueOf(diameter)));
        return diameter;
    }

    @Override // co.hyperverge.hypersnapsdk.helpers.face.FaceViewListener
    public int getViewRadius() {
        HVCamConfig hVCamConfig = this.camConfig;
        if (hVCamConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camConfig");
            hVCamConfig = null;
        }
        int diameter = hVCamConfig.getDiameter() / 2;
        HVLogUtils.d(this.TAG, Intrinsics.stringPlus("getViewRadius() returned: ", Integer.valueOf(diameter)));
        return diameter;
    }

    @Override // co.hyperverge.hypersnapsdk.helpers.face.FaceDetectionListener
    public void setFaceDetectionState(FaceConstants.FaceDetectionState faceDetectionState) {
        BuildersKt__Builders_commonKt.launch$default(this.lifeCycleScope, null, null, new HVFacePreview$setFaceDetectionState$1(faceDetectionState, this, null), 3, null);
    }
}
