package vn.ai.faceauth.sdk.presentation.presentation.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.text.Spanned;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.google.gson.Gson;
import com.tekartik.sqflite.Constant;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import lmlf.ayxnhy.tfwhgw;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;
import org.bouncycastle.crypto.tls.CipherSuite;
import vn.ai.faceauth.sdk.R;
import vn.ai.faceauth.sdk.camera.CameraX;
import vn.ai.faceauth.sdk.camera.core.callback.CameraXCallback;
import vn.ai.faceauth.sdk.camera.core.callback.CameraXCallbackFactory;
import vn.ai.faceauth.sdk.camera.navigation.CameraXNavigation;
import vn.ai.faceauth.sdk.core.extensions.ActivityExtensionsKt;
import vn.ai.faceauth.sdk.core.extensions.ImageExtensionsKt;
import vn.ai.faceauth.sdk.core.extensions.PrimitiveExtensionsKt;
import vn.ai.faceauth.sdk.databinding.FacesdkFragmentCameraxBinding;
import vn.ai.faceauth.sdk.domain.model.CameraSettingsDomain;
import vn.ai.faceauth.sdk.domain.model.PhotoResultDomain;
import vn.ai.faceauth.sdk.domain.model.StateFaceWithOval;
import vn.ai.faceauth.sdk.opencv.VFaceLib;
import vn.ai.faceauth.sdk.presentation.domain.configs.CameraSettings;
import vn.ai.faceauth.sdk.presentation.domain.configs.CameraSettingsKt;
import vn.ai.faceauth.sdk.presentation.domain.configs.LivenessConfig;
import vn.ai.faceauth.sdk.presentation.domain.configs.SDKConfig;
import vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness;
import vn.ai.faceauth.sdk.presentation.navigation.AuthenticationID;
import vn.ai.faceauth.sdk.presentation.navigation.SDKCallback;
import vn.ai.faceauth.sdk.presentation.presentation.opencv.Point;
import vn.ai.faceauth.sdk.presentation.presentation.utils.BShield;
import vn.ai.faceauth.sdk.presentation.presentation.utils.EnumPosition;
import vn.ai.faceauth.sdk.presentation.presentation.utils.UtilsKt;
import vn.ai.faceauth.sdk.presentation.presentation.viewmodel.LivenessViewModel;
import vn.ai.faceauth.sdk.presentation.presentation.viewmodel.LivenessViewModelFactory;
import vn.ai.faceauth.sdk.presentation.presentation.viewmodel.LivenessViewState;
import vn.ai.faceauth.sdk.presentation.presentation.widgets.FaceDetectorProcessor;
import vn.ai.faceauth.sdk.presentation.presentation.widgets.OnCallbackOverlayListener;
import vn.ai.faceauth.sdk.presentation.presentation.widgets.OverlayView;
import vn.ai.faceauth.sdk.presentation.presentation.widgets.VisionImageProcessor;

@Metadata(d1 = {"\u0000þ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\"\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010X\u001a\u00020YH\u0002J\b\u0010Z\u001a\u00020YH\u0002J\b\u0010[\u001a\u00020YH\u0002J\u0014\u0010\\\u001a\u0004\u0018\u00010]2\b\u0010^\u001a\u0004\u0018\u00010\u0005H\u0002J\u000e\u0010_\u001a\u00020\u00052\u0006\u0010`\u001a\u00020aJ\u0018\u0010b\u001a\u0004\u0018\u0001002\f\u0010c\u001a\b\u0012\u0004\u0012\u0002000/H\u0002J$\u0010d\u001a\u00020\u001f2\f\u0010e\u001a\b\u0012\u0004\u0012\u0002000/2\f\u0010f\u001a\b\u0012\u0004\u0012\u0002000/H\u0002J\u0010\u0010g\u001a\u00020Y2\u0006\u0010h\u001a\u00020)H\u0002J\u0010\u0010i\u001a\u00020Y2\u0006\u0010h\u001a\u00020)H\u0002J\b\u0010j\u001a\u00020YH\u0002J \u0010k\u001a\u00020a2\u0006\u0010`\u001a\u00020a2\u0006\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020AH\u0002J\u0016\u0010o\u001a\u00020a2\u0006\u0010`\u001a\u00020a2\u0006\u0010p\u001a\u00020qJ\u0012\u0010r\u001a\u00020Y2\b\u0010s\u001a\u0004\u0018\u00010EH\u0002J\b\u0010t\u001a\u00020YH\u0002J\b\u0010u\u001a\u00020YH\u0002J\b\u0010v\u001a\u00020YH\u0002J(\u0010w\u001a\u00020a2\u0006\u0010x\u001a\u00020a2\u0016\u0010y\u001a\u0012\u0012\u0004\u0012\u0002000Dj\b\u0012\u0004\u0012\u000200`FH\u0002J\u0006\u0010z\u001a\u00020YJ\u0006\u0010{\u001a\u00020YJ\u0006\u0010|\u001a\u00020YJ\b\u0010}\u001a\u00020YH\u0002J\b\u0010~\u001a\u00020YH\u0002J\t\u0010\u007f\u001a\u00030\u0080\u0001H\u0002J\u001c\u0010\u0081\u0001\u001a\u00020Y2\u0007\u0010\u0082\u0001\u001a\u00020)2\b\u0010\u0083\u0001\u001a\u00030\u0084\u0001H\u0002J\t\u0010\u0085\u0001\u001a\u00020YH\u0002J\u0013\u0010\u0086\u0001\u001a\u00020Y2\b\u0010h\u001a\u0004\u0018\u00010EH\u0002J\t\u0010\u0087\u0001\u001a\u00020YH\u0002J\t\u0010\u0088\u0001\u001a\u00020)H\u0002J\u001a\u0010\u0089\u0001\u001a\u00020)2\u0006\u0010`\u001a\u00020a2\t\b\u0002\u0010\u008a\u0001\u001a\u00020\u001fJ\t\u0010\u008b\u0001\u001a\u00020)H\u0002J!\u0010\u008c\u0001\u001a\u00020)2\u0016\u0010y\u001a\u0012\u0012\u0004\u0012\u0002000Dj\b\u0012\u0004\u0012\u000200`FH\u0002J\t\u0010\u008d\u0001\u001a\u00020)H\u0002J0\u0010\u008e\u0001\u001a\u00020\u00052\u0007\u0010\u008f\u0001\u001a\u00020q2\u0006\u0010`\u001a\u00020a2\u0016\u0010c\u001a\u0012\u0012\u0004\u0012\u0002000Dj\b\u0012\u0004\u0012\u000200`FJ\u0007\u0010\u0090\u0001\u001a\u00020\u0005J\t\u0010\u0091\u0001\u001a\u00020YH\u0002J\u0013\u0010\u0092\u0001\u001a\u00020Y2\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001H\u0016J\u0015\u0010\u0095\u0001\u001a\u00020Y2\n\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0097\u0001H\u0016J,\u0010\u0098\u0001\u001a\u00030\u0084\u00012\b\u0010\u0099\u0001\u001a\u00030\u009a\u00012\n\u0010\u009b\u0001\u001a\u0005\u0018\u00010\u009c\u00012\n\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0097\u0001H\u0016J\t\u0010\u009d\u0001\u001a\u00020YH\u0016J\t\u0010\u009e\u0001\u001a\u00020YH\u0016J\t\u0010\u009f\u0001\u001a\u00020YH\u0016J\t\u0010 \u0001\u001a\u00020YH\u0016J\u001f\u0010¡\u0001\u001a\u00020Y2\b\u0010¢\u0001\u001a\u00030\u0084\u00012\n\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0097\u0001H\u0016J\t\u0010£\u0001\u001a\u00020YH\u0016J\t\u0010¤\u0001\u001a\u00020YH\u0016J\t\u0010¥\u0001\u001a\u00020YH\u0002J$\u0010¦\u0001\u001a\u00020a2\u0007\u0010§\u0001\u001a\u00020a2\u0007\u0010¨\u0001\u001a\u00020A2\u0007\u0010©\u0001\u001a\u00020AH\u0002J\u0007\u0010ª\u0001\u001a\u00020YJ\t\u0010«\u0001\u001a\u00020YH\u0002J\t\u0010¬\u0001\u001a\u00020YH\u0002J\t\u0010\u00ad\u0001\u001a\u00020YH\u0002J\u0018\u0010®\u0001\u001a\u00020Y2\r\u0010¯\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050/H\u0002J\t\u0010°\u0001\u001a\u00020YH\u0002J\t\u0010±\u0001\u001a\u00020YH\u0002J\u0011\u0010²\u0001\u001a\u00020Y2\u0006\u0010h\u001a\u00020EH\u0002J\t\u0010³\u0001\u001a\u00020YH\u0002J\t\u0010´\u0001\u001a\u00020YH\u0002J\t\u0010µ\u0001\u001a\u00020YH\u0002J\t\u0010¶\u0001\u001a\u00020YH\u0002J\t\u0010·\u0001\u001a\u00020YH\u0002J\t\u0010¸\u0001\u001a\u00020YH\u0002J0\u0010¹\u0001\u001a\u00020Y*\u00030\u0084\u00012\u0007\u0010º\u0001\u001a\u00020A2\u0007\u0010»\u0001\u001a\u00020A2\u0007\u0010¼\u0001\u001a\u00020A2\u0007\u0010½\u0001\u001a\u00020AR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u00050\u00050\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020%X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000/0.X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u00102\u001a\u0002038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\u0016\u001a\u0004\b4\u00105R\u001b\u00107\u001a\u0002088BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b;\u0010\u0016\u001a\u0004\b9\u0010:R\u001d\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050=¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u000e\u0010@\u001a\u00020AX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020AX\u0082D¢\u0006\u0002\n\u0000R*\u0010C\u001a\u0012\u0012\u0004\u0012\u00020E0Dj\b\u0012\u0004\u0012\u00020E`FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001a\u0010K\u001a\u00020AX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u001a\u0010P\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u0010\u0010U\u001a\u0004\u0018\u00010VX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010W\u001a\u0004\u0018\u00010VX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006¾\u0001"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/fragment/CameraXFragment;", "Lvn/ai/faceauth/sdk/presentation/presentation/fragment/BaseFragment;", "Lvn/ai/faceauth/sdk/presentation/presentation/widgets/OnCallbackOverlayListener;", "()V", "TAG", "", "_binding", "Lvn/ai/faceauth/sdk/databinding/FacesdkFragmentCameraxBinding;", "binding", "getBinding", "()Lvn/ai/faceauth/sdk/databinding/FacesdkFragmentCameraxBinding;", "buttonAcceptColor", "", "cameraManifest", "cameraPermission", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "cameraSettings", "Lvn/ai/faceauth/sdk/presentation/domain/configs/CameraSettings;", "getCameraSettings", "()Lvn/ai/faceauth/sdk/presentation/domain/configs/CameraSettings;", "cameraSettings$delegate", "Lkotlin/Lazy;", "cameraX", "Lvn/ai/faceauth/sdk/camera/CameraX;", "getCameraX", "()Lvn/ai/faceauth/sdk/camera/CameraX;", "cameraX$delegate", "cameraXCallback", "Lvn/ai/faceauth/sdk/camera/core/callback/CameraXCallback;", "currThreshold", "", "getCurrThreshold", "()D", "setCurrThreshold", "(D)V", "debounceDelay", "", "imageProcessor", "Lvn/ai/faceauth/sdk/presentation/presentation/widgets/VisionImageProcessor;", "isCountDownFinished", "", "isCountDownRunning", "isCountDownZoomInStepRunning", "isEnableButtonProceed", "landmarksHistory", "", "", "Lvn/ai/faceauth/sdk/presentation/presentation/opencv/Point;", "lastHandledTime", "livenessConfig", "Lvn/ai/faceauth/sdk/presentation/domain/configs/LivenessConfig;", "getLivenessConfig", "()Lvn/ai/faceauth/sdk/presentation/domain/configs/LivenessConfig;", "livenessConfig$delegate", "livenessViewModel", "Lvn/ai/faceauth/sdk/presentation/presentation/viewmodel/LivenessViewModel;", "getLivenessViewModel", "()Lvn/ai/faceauth/sdk/presentation/presentation/viewmodel/LivenessViewModel;", "livenessViewModel$delegate", "mapDataUploadAPI", "", "getMapDataUploadAPI", "()Ljava/util/Map;", "maxFrames", "", "movementThreshold", "rawImage", "Ljava/util/ArrayList;", "Lvn/ai/faceauth/sdk/domain/model/PhotoResultDomain;", "Lkotlin/collections/ArrayList;", "getRawImage", "()Ljava/util/ArrayList;", "setRawImage", "(Ljava/util/ArrayList;)V", "retrycount", "getRetrycount", "()I", "setRetrycount", "(I)V", "timeStart", "getTimeStart", "()J", "setTimeStart", "(J)V", "timerFirstStep", "Landroid/os/CountDownTimer;", "timerZoomIn", "actionFailedUpdate", "", "actionRetry", "activeProcess", "bindHtmlToString", "Landroid/text/Spanned;", "string", "bitmapToBase64", "bitmap", "Landroid/graphics/Bitmap;", "calculateBoundingBoxCenter", "points", "calculateLandmarkMovement", "from", "to", "checkFirst", "it", "checkZoomIn", "clearImageProcessor", "compressBitmap", "format", "Landroid/graphics/Bitmap$CompressFormat;", "quality", "cropBitmapWithFace", "rectFace", "Landroid/graphics/Rect;", "debounceHandlePicture", Constant.PARAM_RESULT, "deleteAllPictureOnCache", "disableButton", "enableButton", "faceAlignment", "finalBitmap", "landmarkPoints", "forceCloseSDK", "forceShowError", "forceShowSuccess", "forceToRetryCapture", "forceToRetryStep", "getConfig", "Lvn/ai/faceauth/sdk/presentation/domain/configs/SDKConfig;", "handleCameraPermission", "granted", "parentView", "Landroid/view/View;", "handleFinished", "handlePictureSuccess", "initFaceDetector", "isAutoProcess", "isBitmapBlurry", "threshold", "isCancelable", "isFaceStable", "isLivenessConfirmed", "jsonObjectToBase64", "rect", "jsonTrackingToBase64", AnalyticsLogger.Keys.NEXTSTEP, "onAttach", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onDestroy", "onDestroyView", "onPause", "onStop", "onViewCreated", ViewHierarchyConstants.VIEW_KEY, "onZoomOutAnimationEnd", "onZoomOutAnimationStart", "permissionIsGranted", "scaleBitmapWithCanvas", "original", "newWidth", "newHeight", "setupActionFaceSDK", "setupConfig", "setupStatusView", "setupViewAfterDrawOval", "startCallApi", "filesPath", "startCamera", "startObservers", "tryReloadAndDetectInImage", "updateLayoutSuccessScan", "updateUIFailed", "updateUILiveFailed", "updateUINetworkFailed", "updateUIToZoomOut", "validDataAndCallApi", "setMargin", CmcdData.Factory.STREAM_TYPE_LIVE, "t", PDPageLabelRange.STYLE_ROMAN_LOWER, "b", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class CameraXFragment extends BaseFragment implements OnCallbackOverlayListener {
    private final String TAG;
    private FacesdkFragmentCameraxBinding _binding;
    private Object buttonAcceptColor;
    private final String cameraManifest;
    private final ActivityResultLauncher<String> cameraPermission;

    /* renamed from: cameraSettings$delegate, reason: from kotlin metadata */
    private final Lazy cameraSettings;

    /* renamed from: cameraX$delegate, reason: from kotlin metadata */
    private final Lazy cameraX;
    private final CameraXCallback cameraXCallback;
    private double currThreshold;
    private final long debounceDelay;
    private VisionImageProcessor imageProcessor;
    private boolean isCountDownFinished;
    private boolean isCountDownRunning;
    private boolean isCountDownZoomInStepRunning;
    private boolean isEnableButtonProceed;
    private final List<List<Point>> landmarksHistory;
    private long lastHandledTime;

    /* renamed from: livenessConfig$delegate, reason: from kotlin metadata */
    private final Lazy livenessConfig;

    /* renamed from: livenessViewModel$delegate, reason: from kotlin metadata */
    private final Lazy livenessViewModel;
    private final Map<String, String> mapDataUploadAPI;
    private final int maxFrames;
    private final int movementThreshold;
    private ArrayList<PhotoResultDomain> rawImage;
    private int retrycount;
    private long timeStart;
    private CountDownTimer timerFirstStep;
    private CountDownTimer timerZoomIn;

    public CameraXFragment() {
        super(R.layout.facesdk_fragment_camerax);
        this.TAG = tfwhgw.rnigpa(RendererCapabilities.DECODER_SUPPORT_MASK);
        this.cameraSettings = LazyKt.lazy(new Function0<CameraSettings>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$cameraSettings$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final CameraSettings invoke() {
                Intent intent;
                Bundle extras;
                FragmentActivity activity = CameraXFragment.this.getActivity();
                CameraSettings cameraSettings = (activity == null || (intent = activity.getIntent()) == null || (extras = intent.getExtras()) == null) ? null : (CameraSettings) extras.getParcelable(tfwhgw.rnigpa(CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384));
                return cameraSettings == null ? new CameraSettings(null, null, 3, null) : cameraSettings;
            }
        });
        this.livenessConfig = LazyKt.lazy(new Function0<LivenessConfig>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$livenessConfig$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final LivenessConfig invoke() {
                Intent intent;
                Bundle extras;
                FragmentActivity activity = CameraXFragment.this.getActivity();
                LivenessConfig livenessConfig = (activity == null || (intent = activity.getIntent()) == null || (extras = intent.getExtras()) == null) ? null : (LivenessConfig) extras.getParcelable(tfwhgw.rnigpa(113));
                return livenessConfig == null ? new LivenessConfig(0.0f, 0.0f, 0.0f, 0.0f, 15, null) : livenessConfig;
            }
        });
        Function0 function0 = new Function0<ViewModelProvider.Factory>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$livenessViewModel$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return new LivenessViewModelFactory(null, null, null, 7, null);
            }
        };
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        final Function0 function03 = null;
        this.livenessViewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(LivenessViewModel.class), new Function0<ViewModelStore>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$special$$inlined$viewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStoreOwner m238viewModels$lambda1;
                m238viewModels$lambda1 = FragmentViewModelLazyKt.m238viewModels$lambda1(Lazy.this);
                return m238viewModels$lambda1.getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$special$$inlined$viewModels$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                ViewModelStoreOwner m238viewModels$lambda1;
                CreationExtras creationExtras;
                Function0 function04 = Function0.this;
                if (function04 != null && (creationExtras = (CreationExtras) function04.invoke()) != null) {
                    return creationExtras;
                }
                m238viewModels$lambda1 = FragmentViewModelLazyKt.m238viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = m238viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) m238viewModels$lambda1 : null;
                CreationExtras defaultViewModelCreationExtras = hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : null;
                return defaultViewModelCreationExtras == null ? CreationExtras.Empty.INSTANCE : defaultViewModelCreationExtras;
            }
        }, function0 == null ? new Function0<ViewModelProvider.Factory>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$special$$inlined$viewModels$default$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelStoreOwner m238viewModels$lambda1;
                ViewModelProvider.Factory defaultViewModelProviderFactory;
                m238viewModels$lambda1 = FragmentViewModelLazyKt.m238viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = m238viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) m238viewModels$lambda1 : null;
                return (hasDefaultViewModelProviderFactory == null || (defaultViewModelProviderFactory = hasDefaultViewModelProviderFactory.getDefaultViewModelProviderFactory()) == null) ? Fragment.this.getDefaultViewModelProviderFactory() : defaultViewModelProviderFactory;
            }
        } : function0);
        CameraXCallbackFactory cameraXCallbackFactory = CameraXCallbackFactory.INSTANCE;
        cameraXCallbackFactory.setOnImageSavedAction(new Function1<PhotoResultDomain, Unit>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$cameraXCallback$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PhotoResultDomain photoResultDomain) {
                invoke2(photoResultDomain);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PhotoResultDomain photoResultDomain) {
                String str;
                str = CameraXFragment.this.TAG;
                Log.e(str, tfwhgw.rnigpa(315));
                CameraXFragment.this.debounceHandlePicture(photoResultDomain);
            }
        });
        this.cameraXCallback = cameraXCallbackFactory.create();
        this.debounceDelay = 50L;
        this.cameraX = LazyKt.lazy(new Function0<CameraX>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$cameraX$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final CameraX invoke() {
                CameraSettings cameraSettings;
                CameraXCallback cameraXCallback;
                CameraXNavigation cameraXNavigation = new CameraXNavigation(CameraXFragment.this);
                cameraSettings = CameraXFragment.this.getCameraSettings();
                CameraSettingsDomain domain = CameraSettingsKt.toDomain(cameraSettings);
                cameraXCallback = CameraXFragment.this.cameraXCallback;
                return cameraXNavigation.provideCameraXModule(domain, cameraXCallback);
            }
        });
        this.cameraManifest = tfwhgw.rnigpa(385);
        this.cameraPermission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda3
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                CameraXFragment.m2956cameraPermission$lambda1(CameraXFragment.this, (Boolean) obj);
            }
        });
        this.mapDataUploadAPI = new LinkedHashMap();
        this.currThreshold = 15.0d;
        this.landmarksHistory = new ArrayList();
        this.maxFrames = 10;
        this.movementThreshold = 20;
        this.rawImage = new ArrayList<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void actionFailedUpdate() {
        requireActivity().runOnUiThread(new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CameraXFragment.m2955actionFailedUpdate$lambda20(CameraXFragment.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: actionFailedUpdate$lambda-20, reason: not valid java name */
    public static final void m2955actionFailedUpdate$lambda20(CameraXFragment cameraXFragment) {
        cameraXFragment.getLivenessViewModel().reset();
        cameraXFragment.get_binding().btnTryAgain.setVisibility(0);
        cameraXFragment.get_binding().overlayView.changeColorOvalFailed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void actionRetry() {
        get_binding().btnAccept.setVisibility(0);
        get_binding().toolbarText.setVisibility(0);
        get_binding().tvFailedText.setVisibility(8);
        get_binding().btnTryAgain.setVisibility(8);
        this.isCountDownZoomInStepRunning = false;
        get_binding().tvStatusText.setText(bindHtmlToString(getResources().getString(R.string.facesdk_face_not_found)));
        disableButton();
        getLivenessViewModel().reDraw();
        setupStatusView();
        getLivenessViewModel().setFinished(false);
        this.rawImage.clear();
        getCameraX().deleteAllPictures();
        getLivenessViewModel().resetState();
        getLivenessViewModel().getRequestedSteps().clear();
        getLivenessViewModel().setupSteps(getCameraSettings().getLivenessStepList());
        CountDownTimer countDownTimer = this.timerFirstStep;
        if (countDownTimer != null) {
            countDownTimer.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void activeProcess() {
        get_binding().tvStatusText.setText(getResources().getString(R.string.facesdk_face_not_found));
        get_binding().tvFailedText.setVisibility(8);
        get_binding().btnAccept.setVisibility(4);
        get_binding().toolbarText.setVisibility(4);
        get_binding().btnTryAgain.setVisibility(4);
        CountDownTimer countDownTimer = this.timerFirstStep;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        getLivenessViewModel().removeCurrentStep();
        this.rawImage.clear();
        getCameraX().deleteAllPictures();
        Iterator it = CollectionsKt.toList(this.mapDataUploadAPI.keySet()).iterator();
        while (it.hasNext()) {
            this.mapDataUploadAPI.remove((String) it.next());
        }
        getLivenessViewModel().reset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Spanned bindHtmlToString(String string) {
        try {
            return Build.VERSION.SDK_INT >= 24 ? Html.fromHtml(string, 0) : Html.fromHtml(string);
        } catch (Exception unused) {
            return null;
        }
    }

    private final Point calculateBoundingBoxCenter(List<? extends Point> points) {
        if (points.isEmpty()) {
            return null;
        }
        Iterator<T> it = points.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        double d = ((Point) it.next()).x;
        while (it.hasNext()) {
            d = Math.min(d, ((Point) it.next()).x);
        }
        Iterator<T> it2 = points.iterator();
        if (!it2.hasNext()) {
            throw new NoSuchElementException();
        }
        double d2 = ((Point) it2.next()).x;
        while (it2.hasNext()) {
            d2 = Math.max(d2, ((Point) it2.next()).x);
        }
        Iterator<T> it3 = points.iterator();
        if (!it3.hasNext()) {
            throw new NoSuchElementException();
        }
        double d3 = ((Point) it3.next()).y;
        while (it3.hasNext()) {
            d3 = Math.min(d3, ((Point) it3.next()).y);
        }
        Iterator<T> it4 = points.iterator();
        if (!it4.hasNext()) {
            throw new NoSuchElementException();
        }
        double d4 = ((Point) it4.next()).y;
        while (it4.hasNext()) {
            d4 = Math.max(d4, ((Point) it4.next()).y);
        }
        double d5 = 2;
        Double.isNaN(d5);
        Double.isNaN(d5);
        return new Point((d + d2) / d5, (d3 + d4) / d5);
    }

    private final double calculateLandmarkMovement(List<? extends Point> from, List<? extends Point> to) {
        Point calculateBoundingBoxCenter = calculateBoundingBoxCenter(from);
        Point calculateBoundingBoxCenter2 = calculateBoundingBoxCenter(to);
        if (calculateBoundingBoxCenter == null || calculateBoundingBoxCenter2 == null) {
            return Double.MAX_VALUE;
        }
        return Math.hypot(calculateBoundingBoxCenter.x - calculateBoundingBoxCenter2.x, calculateBoundingBoxCenter.y - calculateBoundingBoxCenter2.y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: cameraPermission$lambda-1, reason: not valid java name */
    public static final void m2956cameraPermission$lambda1(CameraXFragment cameraXFragment, Boolean bool) {
        cameraXFragment.handleCameraPermission(PrimitiveExtensionsKt.orFalse(bool), cameraXFragment.get_binding().clRoot);
    }

    private final void checkFirst(boolean it) {
        if (isAutoProcess()) {
            activeProcess();
            return;
        }
        if (it) {
            this.isEnableButtonProceed = true;
            enableButton();
            Spanned bindHtmlToString = bindHtmlToString(getString(R.string.facesdk_scan_status_default));
            if (bindHtmlToString != null) {
                get_binding().tvStatusText.setText(bindHtmlToString);
                return;
            }
            return;
        }
        if (this.isCountDownRunning) {
            return;
        }
        CountDownTimer countDownTimer = this.timerFirstStep;
        if (countDownTimer != null) {
            countDownTimer.start();
        }
        this.isCountDownRunning = true;
        if (this.isCountDownFinished) {
            return;
        }
        get_binding().tvStatusText.setText(getResources().getString(R.string.facesdk_face_not_found));
    }

    private final void checkZoomIn(boolean it) {
        if (it) {
            if (this.isCountDownZoomInStepRunning) {
                return;
            }
            this.isCountDownZoomInStepRunning = true;
            CountDownTimer countDownTimer = this.timerZoomIn;
            if (countDownTimer != null) {
                countDownTimer.start();
            }
            get_binding().tvStatusText.setText("");
            return;
        }
        if (this.isCountDownZoomInStepRunning) {
            this.isCountDownZoomInStepRunning = false;
            CountDownTimer countDownTimer2 = this.timerZoomIn;
            if (countDownTimer2 != null) {
                countDownTimer2.cancel();
            }
        }
    }

    private final void clearImageProcessor() {
        VisionImageProcessor visionImageProcessor;
        if (getActivity() == null || (visionImageProcessor = this.imageProcessor) == null) {
            return;
        }
        visionImageProcessor.stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap compressBitmap(Bitmap bitmap, Bitmap.CompressFormat format, int quality) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(format, quality, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void debounceHandlePicture(PhotoResultDomain result) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastHandledTime >= this.debounceDelay) {
            this.lastHandledTime = currentTimeMillis;
            handlePictureSuccess(result);
        }
    }

    private final void deleteAllPictureOnCache() {
        Log.e(this.TAG, tfwhgw.rnigpa(386));
        getCameraX().deleteAllPictures();
    }

    private final void disableButton() {
        get_binding().btnAccept.setEnabled(false);
        ImageExtensionsKt.setColorFilter(-3355444, get_binding().btnAccept);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enableButton() {
        get_binding().btnAccept.setEnabled(true);
        try {
            Object obj = this.buttonAcceptColor;
            String rnigpa = tfwhgw.rnigpa(387);
            if (obj == null) {
                throw new NullPointerException(rnigpa);
            }
            ImageExtensionsKt.setColorFilter(Color.parseColor((String) obj), get_binding().btnAccept);
            Object obj2 = this.buttonAcceptColor;
            if (obj2 == null) {
                throw new NullPointerException(rnigpa);
            }
            ImageExtensionsKt.setColorFilter(Color.parseColor((String) obj2), get_binding().btnTryAgain);
        } catch (Exception e) {
            System.out.println((Object) e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap faceAlignment(Bitmap finalBitmap, ArrayList<Point> landmarkPoints) {
        Bitmap createBitmap = Bitmap.createBitmap(112, 112, Bitmap.Config.ARGB_8888);
        Point point = new Point(38.2946d, 51.6963d);
        Point point2 = new Point(73.5318d, 51.5014d);
        Point point3 = new Point(56.0252d, 71.7366d);
        Point point4 = new Point(41.5493d, 92.3655d);
        Point point5 = new Point(70.7299d, 92.2041d);
        ArrayList arrayList = new ArrayList();
        for (Point point6 : landmarkPoints) {
            CollectionsKt.addAll(arrayList, CollectionsKt.listOf((Object[]) new Float[]{Float.valueOf((float) point6.x), Float.valueOf((float) point6.y)}));
        }
        float[] floatArray = CollectionsKt.toFloatArray(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < 5; i++) {
            Point point7 = new Point[]{point, point2, point3, point4, point5}[i];
            CollectionsKt.addAll(arrayList2, CollectionsKt.listOf((Object[]) new Float[]{Float.valueOf((float) point7.x), Float.valueOf((float) point7.y)}));
        }
        VFaceLib.INSTANCE.faceAlignment(createBitmap, finalBitmap, floatArray, CollectionsKt.toFloatArray(arrayList2));
        Log.e(this.TAG, tfwhgw.rnigpa(388));
        return createBitmap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: forceShowSuccess$lambda-43, reason: not valid java name */
    public static final void m2958forceShowSuccess$lambda43(CameraXFragment cameraXFragment) {
        cameraXFragment.requireActivity().finish();
    }

    private final void forceToRetryCapture() {
        Log.e(this.TAG, tfwhgw.rnigpa(389));
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                CameraXFragment.m2959forceToRetryCapture$lambda35(CameraXFragment.this);
            }
        }, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: forceToRetryCapture$lambda-35, reason: not valid java name */
    public static final void m2959forceToRetryCapture$lambda35(CameraXFragment cameraXFragment) {
        if (cameraXFragment.rawImage.size() == 0) {
            Log.e(cameraXFragment.TAG, tfwhgw.rnigpa(390));
            CameraX.DefaultImpls.takePicture$default(cameraXFragment.getCameraX(), null, 1, null);
        } else if (cameraXFragment.rawImage.size() == 2) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new CameraXFragment$forceToRetryCapture$1$1(cameraXFragment, null), 3, null);
        }
    }

    private final void forceToRetryStep() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getBinding, reason: from getter */
    public final FacesdkFragmentCameraxBinding get_binding() {
        return this._binding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CameraSettings getCameraSettings() {
        return (CameraSettings) this.cameraSettings.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CameraX getCameraX() {
        return (CameraX) this.cameraX.getValue();
    }

    private final SDKConfig getConfig() {
        return AuthenticationID.INSTANCE.getSdkConfig$trueface_release();
    }

    private final LivenessConfig getLivenessConfig() {
        return (LivenessConfig) this.livenessConfig.getValue();
    }

    private final LivenessViewModel getLivenessViewModel() {
        return (LivenessViewModel) this.livenessViewModel.getValue();
    }

    private final void handleCameraPermission(boolean granted, View parentView) {
        if (granted) {
            permissionIsGranted();
            return;
        }
        SDKCallback callbackResult$trueface_release = AuthenticationID.INSTANCE.getCallbackResult$trueface_release();
        if (callbackResult$trueface_release != null) {
            SDKCallback.DefaultImpls.complete$default(callbackResult$trueface_release, 306, tfwhgw.rnigpa(391), null, null, null, null, 60, null);
        }
        requireActivity().finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleFinished() {
        this.timeStart = System.currentTimeMillis();
        get_binding().overlayView.showProcess();
        get_binding().tvStatusText.setText(bindHtmlToString(getResources().getString(R.string.facesdk_checking)));
    }

    private final void handlePictureSuccess(PhotoResultDomain it) {
        Log.e(this.TAG, tfwhgw.rnigpa(392));
        if ((it != null ? it.getBitMap() : null) == null || this.rawImage.size() >= 2) {
            return;
        }
        clearImageProcessor();
        initFaceDetector();
        tryReloadAndDetectInImage(it);
    }

    private final void initFaceDetector() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            this.imageProcessor = new FaceDetectorProcessor(activity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isAutoProcess() {
        return getConfig().isAutoProcess();
    }

    public static /* synthetic */ boolean isBitmapBlurry$default(CameraXFragment cameraXFragment, Bitmap bitmap, double d, int i, Object obj) {
        if ((i & 2) != 0) {
            d = 15.0d;
        }
        return cameraXFragment.isBitmapBlurry(bitmap, d);
    }

    private final boolean isCancelable() {
        return getConfig().isCancelable();
    }

    private final boolean isFaceStable(ArrayList<Point> landmarkPoints) {
        Log.d(this.TAG, tfwhgw.rnigpa(393));
        if (this.landmarksHistory.size() >= this.maxFrames) {
            this.landmarksHistory.remove(0);
        }
        this.landmarksHistory.add(landmarkPoints);
        if (this.landmarksHistory.size() < this.maxFrames - 5) {
            return false;
        }
        int size = this.landmarksHistory.size();
        double d = 0.0d;
        for (int i = 0; i < size; i++) {
            int size2 = this.landmarksHistory.size();
            for (int i2 = 0; i2 < size2; i2++) {
                if (i != i2) {
                    d = Math.max(d, calculateLandmarkMovement(this.landmarksHistory.get(i), this.landmarksHistory.get(i2)));
                }
            }
        }
        Log.d(this.TAG, tfwhgw.rnigpa(394) + d);
        return d <= ((double) this.movementThreshold);
    }

    private final boolean isLivenessConfirmed() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void nextStep() {
        Log.e(this.TAG, tfwhgw.rnigpa(395) + this.rawImage.size());
        deleteAllPictureOnCache();
        if (this.rawImage.size() == 1) {
            updateUIToZoomOut();
        } else if (this.rawImage.size() == 2) {
            getLivenessViewModel().callFinish();
            validDataAndCallApi();
        }
    }

    private final void permissionIsGranted() {
        startCamera();
        startObservers();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap scaleBitmapWithCanvas(Bitmap original, int newWidth, int newHeight) {
        Bitmap createBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.RGB_565);
        new Canvas(createBitmap).drawBitmap(original, new Rect(0, 0, original.getWidth(), original.getHeight()), new Rect(0, 0, newWidth, newHeight), (Paint) null);
        return createBitmap;
    }

    private final void setupConfig() {
        try {
            get_binding().btnBack.setColorFilter(Color.parseColor(getConfig().getCloseColor()), PorterDuff.Mode.SRC_IN);
            String obj = EnumPosition.LEFT.toString();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            if (Intrinsics.areEqual(obj, EnumPosition.RIGHT.toString())) {
                layoutParams.addRule(11);
                get_binding().btnBack.setLayoutParams(layoutParams);
            }
            if (Intrinsics.areEqual(obj, EnumPosition.NONE.toString())) {
                get_binding().btnBack.setVisibility(4);
                get_binding().btnBack.setEnabled(false);
            }
            get_binding().btnBack.setEnabled(isCancelable());
            get_binding().btnBack.setVisibility(isCancelable() ? 0 : 4);
            this.buttonAcceptColor = getConfig().getPrimaryColor();
            get_binding().btnAccept.setTextColor(Color.parseColor(getConfig().getTextButtonColor()));
            UtilsKt.updateFont(get_binding().btnAccept, getConfig().getFontName(), getConfig().getTextSizeButton());
            UtilsKt.updateFont(get_binding().btnTryAgain, getConfig().getFontName(), getConfig().getTextSizeButton());
            get_binding().clRoot.setBackgroundColor(Color.parseColor(getConfig().getBackgroundColor()));
            get_binding().tvFailedText.setTextColor(Color.parseColor(getConfig().getErrorColor()));
            UtilsKt.updateFont(get_binding().tvFailedText, getConfig().getFontName(), getConfig().getTextSize());
            UtilsKt.updateFont(get_binding().tvSuccessText, getConfig().getFontName(), getConfig().getTextSize());
            get_binding().tvStatusText.setTextColor(Color.parseColor(getConfig().getTextColor()));
            UtilsKt.updateFont(get_binding().tvStatusText, getConfig().getFontName(), getConfig().getTextSize());
            get_binding().toolbarText.setTextColor(Color.parseColor(getConfig().getTextColor()));
            UtilsKt.updateFont(get_binding().toolbarText, getConfig().getFontName(), getConfig().getTextSizeTitle());
            if (isAutoProcess()) {
                activeProcess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void setupStatusView() {
        get_binding().tvStatusText.setVisibility(8);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                CameraXFragment.m2960setupStatusView$lambda40(CameraXFragment.this);
            }
        }, 400L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupStatusView$lambda-40, reason: not valid java name */
    public static final void m2960setupStatusView$lambda40(final CameraXFragment cameraXFragment) {
        cameraXFragment.get_binding().overlayView.post(new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda25
            @Override // java.lang.Runnable
            public final void run() {
                CameraXFragment.m2961setupStatusView$lambda40$lambda39(CameraXFragment.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupStatusView$lambda-40$lambda-39, reason: not valid java name */
    public static final void m2961setupStatusView$lambda40$lambda39(CameraXFragment cameraXFragment) {
        int height = (int) (r0.getHeight() - cameraXFragment.get_binding().overlayView.getRectZoomIn().top);
        ViewGroup.LayoutParams layoutParams = cameraXFragment.get_binding().layoutStatus.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException(tfwhgw.rnigpa(396));
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.setMargins(0, 0, 0, height);
        cameraXFragment.get_binding().layoutStatus.setLayoutParams(marginLayoutParams);
        cameraXFragment.get_binding().tvStatusText.setVisibility(0);
        cameraXFragment.get_binding().layoutStatus.invalidate();
    }

    private final void setupViewAfterDrawOval() {
        Spanned bindHtmlToString = bindHtmlToString(getString(isAutoProcess() ? R.string.facesdk_face_not_found : R.string.facesdk_scan_status_default));
        if (bindHtmlToString != null) {
            get_binding().tvStatusText.setText(bindHtmlToString);
        }
    }

    private final void startCallApi(List<String> filesPath) {
        if (isLivenessConfirmed()) {
            get_binding().tvStatusText.setText(bindHtmlToString(getResources().getString(R.string.facesdk_uploading_encrypt)));
            getLivenessViewModel().showLoading();
        }
        HashMap<String, String> shieldData = BShield.shieldData(filesPath, getConfig().getNonce());
        if (shieldData == null) {
            Log.e(this.TAG, tfwhgw.rnigpa(397));
            SDKCallback callbackResult$trueface_release = AuthenticationID.INSTANCE.getCallbackResult$trueface_release();
            if (callbackResult$trueface_release != null) {
                SDKCallback.DefaultImpls.complete$default(callbackResult$trueface_release, 311, tfwhgw.rnigpa(398), null, null, null, null, 60, null);
                return;
            }
            return;
        }
        SDKCallback callbackResult$trueface_release2 = AuthenticationID.INSTANCE.getCallbackResult$trueface_release();
        if (callbackResult$trueface_release2 != null) {
            String rnigpa = tfwhgw.rnigpa(399);
            String fileBase64 = this.rawImage.get(0).getFileBase64();
            String fileBase642 = this.rawImage.get(1).getFileBase64();
            String str = shieldData.get(tfwhgw.rnigpa(400));
            String str2 = str == null ? "" : str;
            String str3 = shieldData.get(tfwhgw.rnigpa(TypedValues.CycleType.TYPE_CURVE_FIT));
            callbackResult$trueface_release2.complete(1, rnigpa, fileBase64, fileBase642, str2, str3 == null ? "" : str3);
        }
    }

    private final void startCamera() {
        getCameraX().startCamera(get_binding().viewFinder);
        OverlayView overlayView = get_binding().overlayView;
        OverlayView.init$default(overlayView, 0, 0, 0, 0, 15, null);
        overlayView.setConfigColor();
        overlayView.invalidate();
        overlayView.setVisibility(0);
        setupStatusView();
        setupViewAfterDrawOval();
        get_binding().layoutStatus.setVisibility(0);
        get_binding().overlayView.updateBackgroundColor(getConfig().getBackgroundColor().toString());
        get_binding().btnBack.setOnClickListener(new View.OnClickListener() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda22
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraXFragment.m2962startCamera$lambda25(CameraXFragment.this, view);
            }
        });
        get_binding().btnAccept.setOnClickListener(new View.OnClickListener() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda23
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraXFragment.this.activeProcess();
            }
        });
        get_binding().btnTryAgain.setOnClickListener(new View.OnClickListener() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda24
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraXFragment.this.actionRetry();
            }
        });
        disableButton();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startCamera$lambda-25, reason: not valid java name */
    public static final void m2962startCamera$lambda25(CameraXFragment cameraXFragment, View view) {
        SDKCallback callbackResult$trueface_release = AuthenticationID.INSTANCE.getCallbackResult$trueface_release();
        if (callbackResult$trueface_release != null) {
            SDKCallback.DefaultImpls.complete$default(callbackResult$trueface_release, 0, tfwhgw.rnigpa(TypedValues.CycleType.TYPE_VISIBILITY), null, null, null, null, 60, null);
        }
        cameraXFragment.requireActivity().finish();
    }

    private final void startObservers() {
        getLifecycle().addObserver(getCameraX().getLifecycleObserver());
        getLivenessViewModel().getState().observe(getViewLifecycleOwner(), new Observer() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda13
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CameraXFragment.m2977startObservers$lambda4(CameraXFragment.this, (LivenessViewState) obj);
            }
        });
        LivenessViewModel livenessViewModel = getLivenessViewModel();
        CountDownTimer countDownTimer = this.timerFirstStep;
        if (countDownTimer != null) {
            countDownTimer.start();
        }
        livenessViewModel.observeFacesDetection(getCameraX().observeFaceList());
        ActivityExtensionsKt.observeOnce(livenessViewModel.getHasFistCheck(), getViewLifecycleOwner(), new Observer() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda14
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CameraXFragment.m2972startObservers$lambda17$lambda5(CameraXFragment.this, (Boolean) obj);
            }
        });
        ActivityExtensionsKt.observeOnce(livenessViewModel.getHasZoomIn(), getViewLifecycleOwner(), new Observer() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda15
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CameraXFragment.m2973startObservers$lambda17$lambda6(CameraXFragment.this, (Boolean) obj);
            }
        });
        ActivityExtensionsKt.observeOnce(livenessViewModel.getHasZoomOut(), getViewLifecycleOwner(), new Observer() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda16
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CameraXFragment.m2974startObservers$lambda17$lambda7(CameraXFragment.this, (Boolean) obj);
            }
        });
        ActivityExtensionsKt.observeOnce(livenessViewModel.getHasHeadMovedCenter(), getViewLifecycleOwner(), new Observer() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda17
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CameraXFragment.m2975startObservers$lambda17$lambda8((Boolean) obj);
            }
        });
        ActivityExtensionsKt.observeOnce(livenessViewModel.getCallBackCompleted(), getViewLifecycleOwner(), new Observer() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda18
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CameraXFragment.m2976startObservers$lambda17$lambda9(CameraXFragment.this, (String) obj);
            }
        });
        ActivityExtensionsKt.observeOnce(livenessViewModel.getHandleSuccessLiveness(), getViewLifecycleOwner(), new Observer() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda19
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CameraXFragment.m2965startObservers$lambda17$lambda12(CameraXFragment.this, (Map) obj);
            }
        });
        ActivityExtensionsKt.observeOnce(livenessViewModel.getHandleErrorLiveness(), getViewLifecycleOwner(), new Observer() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda20
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CameraXFragment.m2968startObservers$lambda17$lambda15(CameraXFragment.this, (String) obj);
            }
        });
        ActivityExtensionsKt.observeOnce(livenessViewModel.getUpdateStateStepLiveness(), getViewLifecycleOwner(), new Observer() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda21
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CameraXFragment.m2971startObservers$lambda17$lambda16((StateFaceWithOval) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startObservers$lambda-17$lambda-12, reason: not valid java name */
    public static final void m2965startObservers$lambda17$lambda12(final CameraXFragment cameraXFragment, Map map) {
        Handler handler;
        Runnable runnable;
        long j;
        Map map2;
        if (cameraXFragment.isLivenessConfirmed()) {
            Double d = (Double) map.get(tfwhgw.rnigpa(TypedValues.CycleType.TYPE_ALPHA));
            String rnigpa = tfwhgw.rnigpa(404);
            if (map.containsKey(rnigpa)) {
                Object obj = map.get(rnigpa);
                if (obj == null) {
                    throw new NullPointerException(tfwhgw.rnigpa(405));
                }
                map2 = (Map) obj;
            } else {
                map2 = null;
            }
            if ((d != null && ((int) d.doubleValue()) == 1) && map2 != null) {
                Object obj2 = map2.get(tfwhgw.rnigpa(406));
                if (obj2 == null) {
                    throw new NullPointerException(tfwhgw.rnigpa(407));
                }
                if (((int) ((Double) obj2).doubleValue()) == 1) {
                    cameraXFragment.updateLayoutSuccessScan();
                }
            }
            handler = new Handler(Looper.getMainLooper());
            runnable = new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda11
                @Override // java.lang.Runnable
                public final void run() {
                    CameraXFragment.m2966startObservers$lambda17$lambda12$lambda10(CameraXFragment.this);
                }
            };
            j = 500;
        } else {
            handler = new Handler(Looper.getMainLooper());
            runnable = new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda12
                @Override // java.lang.Runnable
                public final void run() {
                    CameraXFragment.m2967startObservers$lambda17$lambda12$lambda11(CameraXFragment.this);
                }
            };
            j = 50;
        }
        handler.postDelayed(runnable, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startObservers$lambda-17$lambda-12$lambda-10, reason: not valid java name */
    public static final void m2966startObservers$lambda17$lambda12$lambda10(CameraXFragment cameraXFragment) {
        cameraXFragment.requireActivity().finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startObservers$lambda-17$lambda-12$lambda-11, reason: not valid java name */
    public static final void m2967startObservers$lambda17$lambda12$lambda11(CameraXFragment cameraXFragment) {
        cameraXFragment.requireActivity().finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startObservers$lambda-17$lambda-15, reason: not valid java name */
    public static final void m2968startObservers$lambda17$lambda15(final CameraXFragment cameraXFragment, String str) {
        cameraXFragment.updateUIFailed();
        if (!(str == null || str.length() == 0)) {
            if (StringsKt.startsWith$default(str, tfwhgw.rnigpa(408), false, 2, (Object) null)) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        CameraXFragment.m2969startObservers$lambda17$lambda15$lambda13(CameraXFragment.this);
                    }
                }, 50L);
            } else if (StringsKt.startsWith$default(str, tfwhgw.rnigpa(409), false, 2, (Object) null)) {
                cameraXFragment.updateUINetworkFailed();
            } else {
                cameraXFragment.updateUILiveFailed();
            }
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                CameraXFragment.this.actionFailedUpdate();
            }
        }, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startObservers$lambda-17$lambda-15$lambda-13, reason: not valid java name */
    public static final void m2969startObservers$lambda17$lambda15$lambda13(CameraXFragment cameraXFragment) {
        cameraXFragment.requireActivity().finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startObservers$lambda-17$lambda-16, reason: not valid java name */
    public static final void m2971startObservers$lambda17$lambda16(StateFaceWithOval stateFaceWithOval) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startObservers$lambda-17$lambda-5, reason: not valid java name */
    public static final void m2972startObservers$lambda17$lambda5(CameraXFragment cameraXFragment, Boolean bool) {
        cameraXFragment.checkFirst(bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startObservers$lambda-17$lambda-6, reason: not valid java name */
    public static final void m2973startObservers$lambda17$lambda6(CameraXFragment cameraXFragment, Boolean bool) {
        cameraXFragment.checkZoomIn(bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startObservers$lambda-17$lambda-7, reason: not valid java name */
    public static final void m2974startObservers$lambda17$lambda7(CameraXFragment cameraXFragment, Boolean bool) {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new CameraXFragment$startObservers$2$3$1(cameraXFragment, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startObservers$lambda-17$lambda-8, reason: not valid java name */
    public static final void m2975startObservers$lambda17$lambda8(Boolean bool) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startObservers$lambda-17$lambda-9, reason: not valid java name */
    public static final void m2976startObservers$lambda17$lambda9(CameraXFragment cameraXFragment, String str) {
        cameraXFragment.getCameraX().finishStep();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startObservers$lambda-4, reason: not valid java name */
    public static final void m2977startObservers$lambda4(CameraXFragment cameraXFragment, LivenessViewState livenessViewState) {
        Spanned bindHtmlToString;
        if (livenessViewState.getMessageLiveness().length() == 0) {
            return;
        }
        if ((livenessViewState.getStepLiveness() == StepLiveness.STEP_ZOOM_IN || livenessViewState.getStepLiveness() == StepLiveness.STEP_ZOOM_OUT) && (bindHtmlToString = cameraXFragment.bindHtmlToString(livenessViewState.getMessageLiveness())) != null) {
            cameraXFragment.get_binding().tvStatusText.setText(bindHtmlToString);
        }
    }

    private final void tryReloadAndDetectInImage(PhotoResultDomain it) {
        if (it.getBitMap() != null) {
            Log.e(this.TAG, tfwhgw.rnigpa(410) + it.getCreatedAt());
            get_binding().graphicOverlay.clear();
            get_binding().graphicOverlay.setImageSourceInfo(it.getBitMap().getWidth(), it.getBitMap().getHeight(), false);
            this.imageProcessor.processBitmap(it.getBitMap(), get_binding().graphicOverlay);
            this.imageProcessor.addProcessSuccees(new CameraXFragment$tryReloadAndDetectInImage$1(this, it));
        }
    }

    private final void updateLayoutSuccessScan() {
        requireActivity().runOnUiThread(new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                CameraXFragment.m2978updateLayoutSuccessScan$lambda19(CameraXFragment.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateLayoutSuccessScan$lambda-19, reason: not valid java name */
    public static final void m2978updateLayoutSuccessScan$lambda19(CameraXFragment cameraXFragment) {
        cameraXFragment.get_binding().btnBack.setVisibility(8);
        cameraXFragment.get_binding().btnAccept.setVisibility(8);
        cameraXFragment.get_binding().btnTryAgain.setVisibility(8);
        cameraXFragment.get_binding().viewFinder.setVisibility(8);
        cameraXFragment.get_binding().graphicOverlay.setVisibility(8);
        cameraXFragment.get_binding().overlayView.setVisibility(8);
        cameraXFragment.get_binding().layoutSuccess.setVisibility(0);
    }

    private final void updateUIFailed() {
        requireActivity().runOnUiThread(new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                CameraXFragment.m2979updateUIFailed$lambda22(CameraXFragment.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateUIFailed$lambda-22, reason: not valid java name */
    public static final void m2979updateUIFailed$lambda22(CameraXFragment cameraXFragment) {
        cameraXFragment.get_binding().tvFailedText.setVisibility(0);
        cameraXFragment.get_binding().tvFailedText.setText(cameraXFragment.bindHtmlToString(cameraXFragment.getResources().getString(R.string.facesdk_scan_fail_title)));
        cameraXFragment.get_binding().tvStatusText.setText(cameraXFragment.bindHtmlToString(cameraXFragment.getResources().getString(R.string.facesdk_scan_fail)));
        OverlayView overlayView = cameraXFragment.get_binding().overlayView;
        overlayView.hideTextCenter();
        overlayView.hideBlur();
    }

    private final void updateUILiveFailed() {
        get_binding().tvFailedText.setVisibility(0);
        get_binding().tvFailedText.setText(bindHtmlToString(getResources().getString(R.string.facesdk_scan_fail_title)));
        get_binding().tvStatusText.setText(bindHtmlToString(getResources().getString(R.string.facesdk_scan_fail)));
    }

    private final void updateUINetworkFailed() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                CameraXFragment.m2980updateUINetworkFailed$lambda23(CameraXFragment.this);
            }
        }, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateUINetworkFailed$lambda-23, reason: not valid java name */
    public static final void m2980updateUINetworkFailed$lambda23(CameraXFragment cameraXFragment) {
        cameraXFragment.requireActivity().finish();
    }

    private final void updateUIToZoomOut() {
        Log.e(this.TAG, tfwhgw.rnigpa(411));
        get_binding().overlayView.hideProcess();
        getLivenessViewModel();
        get_binding().overlayView.zoomOut();
    }

    private final void validDataAndCallApi() {
        Log.e(this.TAG, tfwhgw.rnigpa(FacebookRequestErrorClassification.EC_APP_NOT_INSTALLED));
        if (this.mapDataUploadAPI.size() == 4 && this.rawImage.size() == 2) {
            String createdAt = this.rawImage.get(0).getCreatedAt();
            String createdAt2 = this.rawImage.get(1).getCreatedAt();
            String jsonTrackingToBase64 = jsonTrackingToBase64();
            Map<String, String> map = this.mapDataUploadAPI;
            String rnigpa = tfwhgw.rnigpa(413);
            String str = map.get(rnigpa + createdAt);
            String str2 = this.mapDataUploadAPI.get(rnigpa + createdAt2);
            Map<String, String> map2 = this.mapDataUploadAPI;
            String rnigpa2 = tfwhgw.rnigpa(414);
            startCallApi(CollectionsKt.listOf((Object[]) new String[]{jsonTrackingToBase64, str, str2, map2.get(rnigpa2 + createdAt), this.mapDataUploadAPI.get(rnigpa2 + createdAt2)}));
        }
    }

    public final String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    public final Bitmap cropBitmapWithFace(Bitmap bitmap, Rect rectFace) {
        return Bitmap.createBitmap(bitmap, rectFace.left, rectFace.top, rectFace.width(), rectFace.height());
    }

    public final void forceCloseSDK() {
        Log.e(this.TAG, tfwhgw.rnigpa(415));
        requireActivity().finish();
    }

    public final void forceShowError() {
        getLivenessViewModel().hideLoading();
        updateUIFailed();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                CameraXFragment.this.actionFailedUpdate();
            }
        }, 500L);
    }

    public final void forceShowSuccess() {
        updateLayoutSuccessScan();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                CameraXFragment.m2958forceShowSuccess$lambda43(CameraXFragment.this);
            }
        }, 100L);
    }

    public final double getCurrThreshold() {
        return this.currThreshold;
    }

    public final Map<String, String> getMapDataUploadAPI() {
        return this.mapDataUploadAPI;
    }

    public final ArrayList<PhotoResultDomain> getRawImage() {
        return this.rawImage;
    }

    public final int getRetrycount() {
        return this.retrycount;
    }

    public final long getTimeStart() {
        return this.timeStart;
    }

    public final boolean isBitmapBlurry(Bitmap bitmap, double threshold) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        double d = 0.0d;
        int i = 0;
        for (int i2 = 1; i2 < height - 1; i2++) {
            int i3 = 1;
            while (i3 < width - 1) {
                int i4 = iArr[((i2 - 1) * width) + i3];
                int i5 = iArr[((i2 + 1) * width) + i3];
                int i6 = i2 * width;
                int i7 = iArr[(i3 - 1) + i6];
                i3++;
                double d2 = (iArr[i6 + i3] & 255) - (i7 & 255);
                double d3 = (i5 & 255) - (i4 & 255);
                Double.isNaN(d2);
                Double.isNaN(d2);
                Double.isNaN(d3);
                Double.isNaN(d3);
                d += Math.sqrt((d3 * d3) + (d2 * d2));
                i++;
            }
        }
        double d4 = i;
        Double.isNaN(d4);
        return d / d4 < threshold;
    }

    public final String jsonObjectToBase64(Rect rect, Bitmap bitmap, ArrayList<Point> points) {
        byte[] bytes = new Gson().toJson(MapsKt.mapOf(TuplesKt.to(tfwhgw.rnigpa(TypedValues.CycleType.TYPE_PATH_ROTATE), new Integer[]{Integer.valueOf(rect.left), Integer.valueOf(rect.top), Integer.valueOf(rect.width()), Integer.valueOf(rect.height())}), TuplesKt.to(tfwhgw.rnigpa(417), new Integer[][]{new Integer[]{Integer.valueOf((int) points.get(0).x), Integer.valueOf((int) points.get(0).y)}, new Integer[]{Integer.valueOf((int) points.get(1).x), Integer.valueOf((int) points.get(1).y)}, new Integer[]{Integer.valueOf((int) points.get(2).x), Integer.valueOf((int) points.get(2).y)}, new Integer[]{Integer.valueOf((int) points.get(3).x), Integer.valueOf((int) points.get(3).y)}, new Integer[]{Integer.valueOf((int) points.get(4).x), Integer.valueOf((int) points.get(4).y)}}), TuplesKt.to(tfwhgw.rnigpa(418), bitmapToBase64(bitmap)))).getBytes(Charsets.UTF_8);
        Log.e(this.TAG, tfwhgw.rnigpa(419));
        return Base64.encodeToString(bytes, 2);
    }

    public final String jsonTrackingToBase64() {
        return Base64.encodeToString(new Gson().toJson(MapsKt.mapOf(TuplesKt.to(tfwhgw.rnigpa(TypedValues.CycleType.TYPE_EASING), tfwhgw.rnigpa(421)), TuplesKt.to(tfwhgw.rnigpa(TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE), 2), TuplesKt.to(tfwhgw.rnigpa(TypedValues.CycleType.TYPE_WAVE_PERIOD), Integer.valueOf(this.retrycount)), TuplesKt.to(tfwhgw.rnigpa(TypedValues.CycleType.TYPE_WAVE_OFFSET), 0), TuplesKt.to(tfwhgw.rnigpa(TypedValues.CycleType.TYPE_WAVE_PHASE), 0), TuplesKt.to(tfwhgw.rnigpa(426), Integer.valueOf((int) (System.currentTimeMillis() - this.timeStart))), TuplesKt.to(tfwhgw.rnigpa(427), Build.MANUFACTURER + '-' + Build.MODEL), TuplesKt.to(tfwhgw.rnigpa(428), tfwhgw.rnigpa(429)), TuplesKt.to(tfwhgw.rnigpa(430), String.valueOf(Build.VERSION.SDK_INT)))).getBytes(Charsets.UTF_8), 2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        VFaceLib.INSTANCE.initialize(requireActivity());
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.timerFirstStep = new CountDownTimer() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$onCreate$1
            {
                super(1000L, 100L);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                boolean isAutoProcess;
                Spanned bindHtmlToString;
                FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding;
                CameraXFragment.this.isEnableButtonProceed = true;
                CameraXFragment.this.enableButton();
                CameraXFragment cameraXFragment = CameraXFragment.this;
                isAutoProcess = cameraXFragment.isAutoProcess();
                bindHtmlToString = cameraXFragment.bindHtmlToString(cameraXFragment.getString(isAutoProcess ? R.string.facesdk_face_not_found : R.string.facesdk_scan_status_default));
                if (bindHtmlToString != null) {
                    facesdkFragmentCameraxBinding = CameraXFragment.this.get_binding();
                    facesdkFragmentCameraxBinding.tvStatusText.setText(bindHtmlToString);
                }
                CameraXFragment.this.isCountDownFinished = true;
                CameraXFragment.this.isCountDownRunning = false;
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                CameraXFragment.this.isCountDownFinished = false;
            }
        };
        this.timerZoomIn = new CountDownTimer() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$onCreate$2
            {
                super(100L, 100L);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                String str;
                str = CameraXFragment.this.TAG;
                Log.e(str, tfwhgw.rnigpa(366));
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new CameraXFragment$onCreate$2$onFinish$1(CameraXFragment.this, null), 3, null);
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding;
                facesdkFragmentCameraxBinding = CameraXFragment.this.get_binding();
                facesdkFragmentCameraxBinding.overlayView.showProcess();
                CameraXFragment.this.isCountDownZoomInStepRunning = true;
            }
        };
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this._binding = FacesdkFragmentCameraxBinding.inflate(inflater, container, false);
        get_binding().overlayView.setOnCallbackOverlayListener(this);
        return get_binding().getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        clearImageProcessor();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        VFaceLib.INSTANCE.nativeDestroy();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        clearImageProcessor();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (!requireActivity().isFinishing()) {
            SDKCallback callbackResult$trueface_release = AuthenticationID.INSTANCE.getCallbackResult$trueface_release();
            if (callbackResult$trueface_release != null) {
                SDKCallback.DefaultImpls.complete$default(callbackResult$trueface_release, 304, tfwhgw.rnigpa(431), null, null, null, null, 60, null);
            }
            requireActivity().finish();
        }
        CountDownTimer countDownTimer = this.timerFirstStep;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = this.timerZoomIn;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getLivenessViewModel().setupLivenessConfig(getLivenessConfig());
        getLivenessViewModel().setupSteps(getCameraSettings().getLivenessStepList());
        getLivenessViewModel().setupView(get_binding());
        this.cameraPermission.launch(this.cameraManifest);
        setupConfig();
        setupActionFaceSDK();
    }

    @Override // vn.ai.faceauth.sdk.presentation.presentation.widgets.OnCallbackOverlayListener
    public void onZoomOutAnimationEnd() {
        getLivenessViewModel().removeCurrentStep();
        setupStatusView();
        get_binding().tvStatusText.setText("");
    }

    @Override // vn.ai.faceauth.sdk.presentation.presentation.widgets.OnCallbackOverlayListener
    public void onZoomOutAnimationStart() {
        get_binding().tvStatusText.setVisibility(4);
    }

    public final void setCurrThreshold(double d) {
        this.currThreshold = d;
    }

    public final void setMargin(View view, int i, int i2, int i3, int i4) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException(tfwhgw.rnigpa(432));
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.setMargins(i, i2, i3, i4);
        view.setLayoutParams(marginLayoutParams);
    }

    public final void setRawImage(ArrayList<PhotoResultDomain> arrayList) {
        this.rawImage = arrayList;
    }

    public final void setRetrycount(int i) {
        this.retrycount = i;
    }

    public final void setTimeStart(long j) {
        this.timeStart = j;
    }

    public final void setupActionFaceSDK() {
        AuthenticationID authenticationID = AuthenticationID.INSTANCE;
        authenticationID.setShowSuccess$trueface_release(new Function0<Unit>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$setupActionFaceSDK$1
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
                CameraXFragment.this.forceShowSuccess();
            }
        });
        authenticationID.setShowError$trueface_release(new Function1<String, Unit>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$setupActionFaceSDK$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String str) {
                CameraXFragment.this.forceShowError();
            }
        });
        authenticationID.setCloseSDK$trueface_release(new Function0<Unit>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$setupActionFaceSDK$3
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
                CameraXFragment.this.forceCloseSDK();
            }
        });
    }
}
