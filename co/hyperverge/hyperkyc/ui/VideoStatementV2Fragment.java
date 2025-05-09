package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
import co.hyperverge.hyperkyc.data.models.VideoStatementV2Config;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.databinding.HkFragmentVideoStatementV2Binding;
import co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder;
import co.hyperverge.hyperkyc.hvsessionrecorder.VideoStatementV2LowStorageExceptionListener;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegate;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegateKt;
import co.hyperverge.hyperkyc.ui.custom.shimmeringViews.Shimmer;
import co.hyperverge.hyperkyc.ui.custom.shimmeringViews.ShimmerTextView;
import co.hyperverge.hyperkyc.ui.custom.shimmeringViews.ShimmeringMaterialButton;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM;
import co.hyperverge.hyperkyc.utils.PermDialogCallback;
import co.hyperverge.hyperkyc.utils.PermissionHandler;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.UIExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import co.hyperverge.hypersnapsdk.components.camera.HVFacePreview;
import co.hyperverge.hypersnapsdk.components.camera.model.HVCamConfig;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.android.material.button.MaterialButton;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.apache.commons.io.FilenameUtils;

/* compiled from: VideoStatementV2Fragment.kt */
@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u0000 g2\u00020\u0001:\u0002ghB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010F\u001a\u00020\u00162\u0006\u0010G\u001a\u00020\u00062\u0006\u0010H\u001a\u00020>H\u0002J\b\u0010I\u001a\u00020\u0006H\u0002J\u0010\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\u0006H\u0002J\b\u0010M\u001a\u00020KH\u0002J\b\u0010N\u001a\u00020KH\u0002J\b\u0010O\u001a\u00020KH\u0002J\u0010\u0010P\u001a\u00020K2\u0006\u0010Q\u001a\u00020RH\u0002J\u0010\u0010S\u001a\u00020K2\u0006\u0010Q\u001a\u00020RH\u0002J\b\u0010T\u001a\u00020KH\u0002J\u0010\u0010U\u001a\u00020K2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010V\u001a\u00020KH\u0016J\b\u0010W\u001a\u00020KH\u0016J\b\u0010X\u001a\u00020KH\u0016J\u001a\u0010Y\u001a\u00020K2\u0006\u0010Z\u001a\u00020[2\b\u0010\\\u001a\u0004\u0018\u00010]H\u0016J\b\u0010^\u001a\u00020KH\u0002J\u0018\u0010_\u001a\u00020K2\u0006\u0010`\u001a\u00020>2\u0006\u0010a\u001a\u00020\u0006H\u0002J\b\u0010b\u001a\u00020KH\u0002J\u0010\u0010c\u001a\u00020K2\u0006\u0010d\u001a\u00020\u0012H\u0002J\b\u0010e\u001a\u00020KH\u0002J\b\u0010f\u001a\u00020KH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010#\u001a\u00020$8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b%\u0010&R(\u0010)\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0006 ,*\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010+0+0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010/\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b2\u0010(\u001a\u0004\b0\u00101R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R'\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006098BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b<\u0010(\u001a\u0004\b:\u0010;R\u000e\u0010=\u001a\u00020>X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010?\u001a\u0004\u0018\u00010@X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010A\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020CX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020EX\u0082.¢\u0006\u0002\n\u0000¨\u0006i"}, d2 = {"Lco/hyperverge/hyperkyc/ui/VideoStatementV2Fragment;", "Lco/hyperverge/hyperkyc/ui/BaseFragment;", "uiState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$VideoStatementV2;", "(Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$VideoStatementV2;)V", "audioUri", "", "binding", "Lco/hyperverge/hyperkyc/databinding/HkFragmentVideoStatementV2Binding;", "getBinding", "()Lco/hyperverge/hyperkyc/databinding/HkFragmentVideoStatementV2Binding;", "binding$delegate", "Lco/hyperverge/hyperkyc/ui/custom/delegates/FragmentViewBindingDelegate;", "currentState", "Lco/hyperverge/hyperkyc/ui/VideoStatementV2Fragment$VideoStatementState;", "editor", "Landroid/content/SharedPreferences$Editor;", "endTimestamp", "", "faceDetectorListenerJob", "Lkotlinx/coroutines/Job;", "faceFoundText", "Landroid/text/Spanned;", "faceNotFoundText", "facePreview", "Lco/hyperverge/hypersnapsdk/components/camera/HVFacePreview;", HVRetakeActivity.IMAGE_URI_TAG, "isCameraRunning", "", "isFaceDetectionCheckFailed", "isFaceFoundOnce", "isFaceNotDetected", "isFirstRecording", "isPermissionsGranted", "isSelfieCaptured", "mainVM", "Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "getMainVM", "()Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "mainVM$delegate", "Lkotlin/Lazy;", "requestMultiplePermissionsLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "shimmer", "Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/Shimmer;", "showBackButton", "getShowBackButton", "()Z", "showBackButton$delegate", "sp", "Landroid/content/SharedPreferences;", "startTimestamp", "statementHelpTextString", "statementText", "textConfigs", "", "getTextConfigs", "()Ljava/util/Map;", "textConfigs$delegate", "timeOutOfFrameMs", "", WorkflowModule.Properties.Section.Component.Type.TIMER, "Landroid/os/CountDownTimer;", "videoUri", "vsConfigV2", "Lco/hyperverge/hyperkyc/data/models/VideoStatementV2Config;", "vsV2VM", "Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementV2VM;", "getFormattedText", "text", "color", "getParsedStatementText", "handlePermissionDialog", "", "permission", "initViewsForPreRecording", "initViewsForProcessing", "initViewsForRecording", "initViewsForRerecordScreen", "error", "Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementV2VM$Check;", "moveToReRecordScreen", "moveToRecordingScreen", "observeUIState", "onDestroyView", "onStart", "onStop", "onViewCreated", ViewHierarchyConstants.VIEW_KEY, "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "requestPermission", "setErrorText", "resId", "key", "startProcessing", "startTimer", "duration", "stopRecording", "updateVideoUploadText", "Companion", "VideoStatementState", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VideoStatementV2Fragment extends BaseFragment {
    public static final /* synthetic */ String ARG_KEY_TEXT_CONFIGS = "textConfigs";
    public static final /* synthetic */ String ARG_SHOW_BACK_BUTTON = "showBackButton";
    private String audioUri;

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final FragmentViewBindingDelegate binding;
    private VideoStatementState currentState;
    private SharedPreferences.Editor editor;
    private long endTimestamp;
    private Job faceDetectorListenerJob;
    private Spanned faceFoundText;
    private Spanned faceNotFoundText;
    private HVFacePreview facePreview;
    private String imageUri;
    private boolean isCameraRunning;
    private boolean isFaceDetectionCheckFailed;
    private boolean isFaceFoundOnce;
    private boolean isFaceNotDetected;
    private boolean isFirstRecording;
    private boolean isPermissionsGranted;
    private boolean isSelfieCaptured;

    /* renamed from: mainVM$delegate, reason: from kotlin metadata */
    private final Lazy mainVM;
    private final ActivityResultLauncher<String[]> requestMultiplePermissionsLauncher;
    private final Shimmer shimmer;

    /* renamed from: showBackButton$delegate, reason: from kotlin metadata */
    private final Lazy showBackButton;
    private SharedPreferences sp;
    private long startTimestamp;
    private String statementHelpTextString;
    private String statementText;

    /* renamed from: textConfigs$delegate, reason: from kotlin metadata */
    private final Lazy textConfigs;
    private int timeOutOfFrameMs;
    private CountDownTimer timer;
    private final WorkflowUIState.VideoStatementV2 uiState;
    private String videoUri;
    private final VideoStatementV2Config vsConfigV2;
    private VideoStatementV2VM vsV2VM;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(VideoStatementV2Fragment.class, "binding", "getBinding()Lco/hyperverge/hyperkyc/databinding/HkFragmentVideoStatementV2Binding;", 0))};

    /* compiled from: VideoStatementV2Fragment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lco/hyperverge/hyperkyc/ui/VideoStatementV2Fragment$VideoStatementState;", "", "(Ljava/lang/String;I)V", "PRE_RECORDING", "RECORDING", "PROCESSING", "REVIEW", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public enum VideoStatementState {
        PRE_RECORDING,
        RECORDING,
        PROCESSING,
        REVIEW
    }

    /* compiled from: VideoStatementV2Fragment.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VideoStatementV2VM.Check.values().length];
            try {
                iArr[VideoStatementV2VM.Check.FACE_DETECTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[VideoStatementV2VM.Check.LIVENESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[VideoStatementV2VM.Check.FACE_MATCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[VideoStatementV2VM.Check.SPEECH_TO_TEXT_MATCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementV2Fragment(WorkflowUIState.VideoStatementV2 uiState) {
        super(R.layout.hk_fragment_video_statement_v2);
        Intrinsics.checkNotNullParameter(uiState, "uiState");
        this.uiState = uiState;
        final VideoStatementV2Fragment videoStatementV2Fragment = this;
        this.mainVM = FragmentViewModelLazyKt.createViewModelLazy(videoStatementV2Fragment, Reflection.getOrCreateKotlinClass(MainVM.class), new Function0<ViewModelStore>() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$special$$inlined$activityViewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
        this.binding = FragmentViewBindingDelegateKt.viewBinding(videoStatementV2Fragment, VideoStatementV2Fragment$binding$2.INSTANCE);
        this.showBackButton = LazyKt.lazy(new Function0<Boolean>() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$showBackButton$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                Bundle arguments = VideoStatementV2Fragment.this.getArguments();
                Boolean valueOf = arguments != null ? Boolean.valueOf(arguments.getBoolean("showBackButton")) : null;
                Intrinsics.checkNotNull(valueOf);
                return valueOf;
            }
        });
        ActivityResultLauncher<String[]> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$$ExternalSyntheticLambda2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                VideoStatementV2Fragment.requestMultiplePermissionsLauncher$lambda$0(VideoStatementV2Fragment.this, (Map) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…sions[0])\n        }\n    }");
        this.requestMultiplePermissionsLauncher = registerForActivityResult;
        this.textConfigs = LazyKt.lazy(new Function0<Map<String, ? extends String>>() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$textConfigs$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Map<String, ? extends String> invoke() {
                Bundle arguments = VideoStatementV2Fragment.this.getArguments();
                Object obj = arguments != null ? arguments.get("textConfigs") : null;
                Map map = obj instanceof Map ? (Map) obj : null;
                Map<String, ? extends String> map2 = map != null ? MapsKt.toMap(map) : null;
                Intrinsics.checkNotNull(map2);
                return map2;
            }
        });
        this.vsConfigV2 = uiState.getVsConfigV2();
        this.shimmer = new Shimmer();
        this.currentState = VideoStatementState.PRE_RECORDING;
        this.statementText = "";
        this.statementHelpTextString = "";
        this.startTimestamp = System.currentTimeMillis();
        this.endTimestamp = System.currentTimeMillis();
        this.isFirstRecording = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MainVM getMainVM() {
        return (MainVM) this.mainVM.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HkFragmentVideoStatementV2Binding getBinding() {
        return (HkFragmentVideoStatementV2Binding) this.binding.getValue2((Fragment) this, $$delegatedProperties[0]);
    }

    private final boolean getShowBackButton() {
        return ((Boolean) this.showBackButton.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestMultiplePermissionsLauncher$lambda$0(VideoStatementV2Fragment this$0, Map permissions) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Intrinsics.checkNotNullExpressionValue(permissions, "permissions");
        for (Map.Entry entry : permissions.entrySet()) {
            String str = (String) entry.getKey();
            if (((Boolean) entry.getValue()).booleanValue()) {
                arrayList.add(str);
            } else {
                arrayList2.add(str);
            }
        }
        if (arrayList2.isEmpty()) {
            this$0.initViewsForPreRecording();
        } else {
            this$0.handlePermissionDialog((String) arrayList2.get(0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<String, String> getTextConfigs() {
        return (Map) this.textConfigs.getValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x0144, code lost:
    
        if (r0 != 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0154, code lost:
    
        if (r0 == 0) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0158, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0167, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0169, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0174, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x017a, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x017d, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0184, code lost:
    
        r0 = new java.lang.StringBuilder();
        r2 = "onViewCreated() called with: view = " + r18 + ", savedInstanceState = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x019e, code lost:
    
        if (r2 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01a0, code lost:
    
        r2 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01a2, code lost:
    
        r0.append(r2);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0157, code lost:
    
        r8 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v28, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v43 */
    @Override // co.hyperverge.hyperkyc.ui.BaseFragment, androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String canonicalName;
        Object m1202constructorimpl;
        SharedPreferences sharedPreferences;
        ?? r0;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str2 = "onViewCreated() called with: view = " + view + ", savedInstanceState = " + savedInstanceState;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                        sharedPreferences = null;
                    } else {
                        sharedPreferences = null;
                        r0 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls2 = getClass();
                    r0 = cls2 != null ? cls2.getCanonicalName() : sharedPreferences;
                }
            }
        }
        sharedPreferences = null;
        FragmentActivity activity = getActivity();
        SharedPreferences sharedPreferences2 = activity != null ? activity.getSharedPreferences("HyperKycSP", 0) : sharedPreferences;
        this.sp = sharedPreferences2;
        this.editor = sharedPreferences2 != null ? sharedPreferences2.edit() : sharedPreferences;
        this.vsV2VM = new VideoStatementV2VM(getMainVM(), this.vsConfigV2, this.uiState, this.sp, getMainVM().getCurrentModuleId$hyperkyc_release() + '_' + getMainVM().getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release());
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        super.onStart();
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("onStart() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "onStart() called ");
                }
            }
        }
        if (this.isPermissionsGranted) {
            return;
        }
        if (ContextCompat.checkSelfPermission(requireActivity(), "android.permission.CAMERA") != 0 || ContextCompat.checkSelfPermission(requireActivity(), "android.permission.RECORD_AUDIO") != 0) {
            requestPermission();
        } else {
            this.isPermissionsGranted = true;
            getBinding().camView.post(new Runnable() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    VideoStatementV2Fragment.onStart$lambda$8(VideoStatementV2Fragment.this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onStart$lambda$8(VideoStatementV2Fragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.initViewsForPreRecording();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViewsForPreRecording$lambda$15$lambda$10(VideoStatementV2Fragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed$hyperkyc_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void moveToRecordingScreen$lambda$20(VideoStatementV2Fragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseFragment.finishWithResult$default(this$0, "error", null, 118, this$0.getString(R.string.hk_storage_error), false, false, 50, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViewsForRerecordScreen$lambda$39$lambda$37(MediaController mc, HkFragmentVideoStatementV2Binding this_with, VideoStatementV2Fragment this$0, View view) {
        Intrinsics.checkNotNullParameter(mc, "$mc");
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        mc.hide();
        this_with.vvReview.stopPlayback();
        this$0.moveToRecordingScreen();
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0127, code lost:
    
        if (r0 == null) goto L52;
     */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onDestroyView() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        super.onDestroyView();
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("onDestroyView() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "onDestroyView() called ");
                }
            }
        }
        if ((this.facePreview != null) & this.isCameraRunning) {
            BuildersKt__Builders_commonKt.launch$default(getLifecycleScope(), Dispatchers.getDefault(), null, new VideoStatementV2Fragment$onDestroyView$3(this, null), 2, null);
        }
        HVSessionRecorder.INSTANCE.getInstance().stop$hyperkyc_release(new Function1<File, Unit>() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$onDestroyView$4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(File file) {
                invoke2(file);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(File file) {
                HVSessionRecorder.INSTANCE.deleteInstance();
            }
        });
        this.shimmer.cancel();
        Job job = this.faceDetectorListenerJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        HKMainActivity hKMainActivity = (HKMainActivity) getActivity();
        if (hKMainActivity == null) {
            return;
        }
        hKMainActivity.setIgnoreBackPress$hyperkyc_release(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestPermission() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("requestPermission() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "requestPermission() called ");
                }
            }
        }
        this.requestMultiplePermissionsLauncher.launch(new String[]{"android.permission.RECORD_AUDIO", "android.permission.CAMERA"});
    }

    /* JADX WARN: Can't wrap try/catch for region: R(23:1|(3:148|(1:150)(1:153)|(1:152))|7|(1:9)|10|(1:14)|15|(1:17)|18|(6:111|112|113|(1:115)|116|(14:118|(20:120|(3:139|(1:141)(1:144)|(1:143))|126|(1:128)|129|(1:133)|134|(1:136)|137|22|(1:24)(1:110)|25|26|(1:28)(1:107)|29|30|31|32|33|(15:35|(3:96|(1:98)(1:101)|(1:100))|41|(1:43)|44|(1:95)(1:48)|49|(1:51)|52|53|54|55|(1:57)|58|(2:60|(13:62|(1:89)(2:66|(9:68|69|(1:71)|72|(1:76)|77|(1:79)|80|81))|83|(1:85)(1:88)|(1:87)|69|(0)|72|(2:74|76)|77|(0)|80|81)(1:90))(1:91))(1:102))|21|22|(0)(0)|25|26|(0)(0)|29|30|31|32|33|(0)(0)))|20|21|22|(0)(0)|25|26|(0)(0)|29|30|31|32|33|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0226, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0233, code lost:
    
        r2 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0228, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0229, code lost:
    
        r13 = "null cannot be cast to non-null type android.app.Application";
        r26 = "getInitialApplication";
        r15 = " - ";
        r27 = "this as java.lang.String…ing(startIndex, endIndex)";
        r14 = "";
        r1 = "replaceAll(\"\")";
        r25 = r20;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01da A[Catch: all -> 0x0228, TryCatch #2 {all -> 0x0228, blocks: (B:26:0x01c0, B:28:0x01da, B:29:0x01df), top: B:25:0x01c0 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x037b  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x03a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void handlePermissionDialog(final String permission) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String canonicalName2;
        String className;
        String str2;
        Throwable m1205exceptionOrNullimpl;
        String canonicalName3;
        String str3;
        Object obj;
        String str4;
        Matcher matcher;
        String localizedMessage;
        String className2;
        String className3;
        String className4;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str5 = "N/A";
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher2.find()) {
            canonicalName = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str6 = "handlePermissionDialog() called with: permission = " + permission;
        if (str6 == null) {
            str6 = "null ";
        }
        sb.append(str6);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                str = "packageName";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = "N/A";
                        }
                    }
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher3.find()) {
                        canonicalName2 = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str7 = "handlePermissionDialog() called with: permission = " + permission;
                    if (str7 == null) {
                        str7 = "null ";
                    }
                    sb2.append(str7);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                    final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    objectRef.element = "Camera";
                    if (Intrinsics.areEqual(permission, "android.permission.RECORD_AUDIO")) {
                        objectRef.element = "Mic";
                        str2 = "mic_permission";
                    } else {
                        str2 = "camera_permission";
                    }
                    Result.Companion companion4 = Result.INSTANCE;
                    VideoStatementV2Fragment videoStatementV2Fragment = this;
                    PermissionHandler permissionHandler = new PermissionHandler();
                    Map<String, Object> map = getMainVM().getTextConfigData().get(str2);
                    Map<String, Object> map2 = map instanceof Map ? map : null;
                    FragmentActivity requireActivity = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                    String str8 = str;
                    String str9 = "null cannot be cast to non-null type android.app.Application";
                    String str10 = " - ";
                    String str11 = "getInitialApplication";
                    String str12 = "this as java.lang.String…ing(startIndex, endIndex)";
                    String str13 = "";
                    String str14 = "replaceAll(\"\")";
                    PermissionHandler.showPermissionBS$default(permissionHandler, permission, map2, requireActivity, new PermDialogCallback() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$handlePermissionDialog$2$1
                        @Override // co.hyperverge.hyperkyc.utils.PermDialogCallback
                        public void onActionClick() {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(VideoStatementV2Fragment.this.requireActivity(), permission)) {
                                VideoStatementV2Fragment.this.requestPermission();
                                return;
                            }
                            VideoStatementV2Fragment.this.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + VideoStatementV2Fragment.this.requireActivity().getApplicationContext().getPackageName())));
                        }

                        @Override // co.hyperverge.hyperkyc.utils.PermDialogCallback
                        public void onCancel() {
                            BaseFragment.finishWithResult$default(VideoStatementV2Fragment.this, "error", null, 106, objectRef.element + " permission denied", false, false, 50, null);
                        }
                    }, getMainVM().shouldShowBranding(), false, 32, null);
                    Object m1202constructorimpl2 = Result.m1202constructorimpl(Unit.INSTANCE);
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl2);
                    if (m1205exceptionOrNullimpl == null) {
                        HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                        HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb3 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls3 = getClass();
                            canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                            if (canonicalName3 == null) {
                                canonicalName3 = "N/A";
                            }
                        }
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                        if (matcher4.find()) {
                            canonicalName3 = matcher4.replaceAll(str13);
                            Intrinsics.checkNotNullExpressionValue(canonicalName3, str14);
                        }
                        if (canonicalName3.length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str3 = str12;
                        } else {
                            canonicalName3 = canonicalName3.substring(0, 23);
                            str3 = str12;
                            Intrinsics.checkNotNullExpressionValue(canonicalName3, str3);
                        }
                        sb3.append(canonicalName3);
                        sb3.append(str10);
                        String localizedMessage2 = m1205exceptionOrNullimpl.getLocalizedMessage();
                        if (localizedMessage2 == null) {
                            localizedMessage2 = "null ";
                        }
                        sb3.append(localizedMessage2);
                        sb3.append(' ');
                        sb3.append(str13);
                        companion5.log(level2, sb3.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion6 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod(str11, new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, str9);
                            obj = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th2) {
                            Result.Companion companion7 = Result.INSTANCE;
                            obj = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                        }
                        Object obj2 = obj;
                        if (Result.m1208isFailureimpl(obj2)) {
                            obj2 = str13;
                        }
                        String str15 = (String) obj2;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(str15, str8);
                            if (StringsKt.contains$default((CharSequence) str15, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                    str4 = null;
                                } else {
                                    str4 = null;
                                    String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default != null) {
                                        str5 = substringAfterLast$default;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                        if (matcher.find()) {
                                            str5 = matcher.replaceAll(str13);
                                            Intrinsics.checkNotNullExpressionValue(str5, str14);
                                        }
                                        if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            str5 = str5.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str5, str3);
                                        }
                                        StringBuilder sb4 = new StringBuilder();
                                        localizedMessage = m1205exceptionOrNullimpl.getLocalizedMessage();
                                        if (localizedMessage == null) {
                                            localizedMessage = "null ";
                                        }
                                        sb4.append(localizedMessage);
                                        sb4.append(' ');
                                        sb4.append(str13);
                                        Log.println(6, str5, sb4.toString());
                                        return;
                                    }
                                }
                                Class<?> cls4 = getClass();
                                String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str4;
                                if (canonicalName4 != null) {
                                    str5 = canonicalName4;
                                }
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                if (matcher.find()) {
                                }
                                if (str5.length() > 23) {
                                    str5 = str5.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str5, str3);
                                }
                                StringBuilder sb42 = new StringBuilder();
                                localizedMessage = m1205exceptionOrNullimpl.getLocalizedMessage();
                                if (localizedMessage == null) {
                                }
                                sb42.append(localizedMessage);
                                sb42.append(' ');
                                sb42.append(str13);
                                Log.println(6, str5, sb42.toString());
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                final Ref.ObjectRef<String> objectRef2 = new Ref.ObjectRef();
                objectRef2.element = "Camera";
                if (Intrinsics.areEqual(permission, "android.permission.RECORD_AUDIO")) {
                }
                Result.Companion companion42 = Result.INSTANCE;
                VideoStatementV2Fragment videoStatementV2Fragment2 = this;
                PermissionHandler permissionHandler2 = new PermissionHandler();
                Map<String, Object> map3 = getMainVM().getTextConfigData().get(str2);
                if (map3 instanceof Map) {
                }
                FragmentActivity requireActivity2 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                String str82 = str;
                String str92 = "null cannot be cast to non-null type android.app.Application";
                String str102 = " - ";
                String str112 = "getInitialApplication";
                String str122 = "this as java.lang.String…ing(startIndex, endIndex)";
                String str132 = "";
                String str142 = "replaceAll(\"\")";
                PermissionHandler.showPermissionBS$default(permissionHandler2, permission, map2, requireActivity2, new PermDialogCallback() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$handlePermissionDialog$2$1
                    @Override // co.hyperverge.hyperkyc.utils.PermDialogCallback
                    public void onActionClick() {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(VideoStatementV2Fragment.this.requireActivity(), permission)) {
                            VideoStatementV2Fragment.this.requestPermission();
                            return;
                        }
                        VideoStatementV2Fragment.this.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + VideoStatementV2Fragment.this.requireActivity().getApplicationContext().getPackageName())));
                    }

                    @Override // co.hyperverge.hyperkyc.utils.PermDialogCallback
                    public void onCancel() {
                        BaseFragment.finishWithResult$default(VideoStatementV2Fragment.this, "error", null, 106, objectRef2.element + " permission denied", false, false, 50, null);
                    }
                }, getMainVM().shouldShowBranding(), false, 32, null);
                Object m1202constructorimpl22 = Result.m1202constructorimpl(Unit.INSTANCE);
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl22);
                if (m1205exceptionOrNullimpl == null) {
                }
            }
        }
        str = "packageName";
        final Ref.ObjectRef<String> objectRef22 = new Ref.ObjectRef();
        objectRef22.element = "Camera";
        if (Intrinsics.areEqual(permission, "android.permission.RECORD_AUDIO")) {
        }
        Result.Companion companion422 = Result.INSTANCE;
        VideoStatementV2Fragment videoStatementV2Fragment22 = this;
        PermissionHandler permissionHandler22 = new PermissionHandler();
        Map<String, Object> map32 = getMainVM().getTextConfigData().get(str2);
        if (map32 instanceof Map) {
        }
        FragmentActivity requireActivity22 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity22, "requireActivity()");
        String str822 = str;
        String str922 = "null cannot be cast to non-null type android.app.Application";
        String str1022 = " - ";
        String str1122 = "getInitialApplication";
        String str1222 = "this as java.lang.String…ing(startIndex, endIndex)";
        String str1322 = "";
        String str1422 = "replaceAll(\"\")";
        PermissionHandler.showPermissionBS$default(permissionHandler22, permission, map2, requireActivity22, new PermDialogCallback() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$handlePermissionDialog$2$1
            @Override // co.hyperverge.hyperkyc.utils.PermDialogCallback
            public void onActionClick() {
                if (ActivityCompat.shouldShowRequestPermissionRationale(VideoStatementV2Fragment.this.requireActivity(), permission)) {
                    VideoStatementV2Fragment.this.requestPermission();
                    return;
                }
                VideoStatementV2Fragment.this.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + VideoStatementV2Fragment.this.requireActivity().getApplicationContext().getPackageName())));
            }

            @Override // co.hyperverge.hyperkyc.utils.PermDialogCallback
            public void onCancel() {
                BaseFragment.finishWithResult$default(VideoStatementV2Fragment.this, "error", null, 106, objectRef22.element + " permission denied", false, false, 50, null);
            }
        }, getMainVM().shouldShowBranding(), false, 32, null);
        Object m1202constructorimpl222 = Result.m1202constructorimpl(Unit.INSTANCE);
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl222);
        if (m1205exceptionOrNullimpl == null) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:79:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void initViewsForPreRecording() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("initViewsForPreRecording() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "initViewsForPreRecording() called ");
                }
            }
        }
        HkFragmentVideoStatementV2Binding binding = getBinding();
        LinearLayout linearLayout = binding.includeBranding.llBrandingRoot;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "includeBranding.llBrandingRoot");
        linearLayout.setVisibility(8);
        LinearLayout linearLayout2 = binding.cardLayoutBranding.llBrandingRoot;
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "cardLayoutBranding.llBrandingRoot");
        linearLayout2.setVisibility(getMainVM().shouldShowBranding() ? 0 : 8);
        ImageView imageView = binding.hkLayoutTopBar.ivBack;
        Intrinsics.checkNotNullExpressionValue(imageView, "hkLayoutTopBar.ivBack");
        imageView.setVisibility(getShowBackButton() ? 0 : 8);
        binding.hkLayoutTopBar.ivBack.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoStatementV2Fragment.initViewsForPreRecording$lambda$15$lambda$10(VideoStatementV2Fragment.this, view);
            }
        });
        binding.btnPrimary.setOnClickListener(null);
        String nullIfBlank = CoreExtsKt.nullIfBlank(getTextConfigs().get("statementHelperText"));
        if (nullIfBlank != null) {
            binding.statementHelpText.setText(UIExtsKt.fromHTML(nullIfBlank));
        }
        String nullIfBlank2 = CoreExtsKt.nullIfBlank(getTextConfigs().get("startRecordingButton"));
        if (nullIfBlank2 != null) {
            binding.btnPrimary.setText(UIExtsKt.fromHTML(nullIfBlank2));
        }
        String string = getString(R.string.hk_vsV2_status_text);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.hk_vsV2_status_text)");
        this.faceFoundText = UIExtsKt.fromHTML(string);
        String nullIfBlank3 = CoreExtsKt.nullIfBlank(getTextConfigs().get("faceFound"));
        if (nullIfBlank3 != null) {
            this.faceFoundText = UIExtsKt.fromHTML(nullIfBlank3);
        }
        String string2 = getString(R.string.hk_vsV2_status_text_error);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.hk_vsV2_status_text_error)");
        this.faceNotFoundText = UIExtsKt.fromHTML(string2);
        String nullIfBlank4 = CoreExtsKt.nullIfBlank(getTextConfigs().get("faceNotFound"));
        if (nullIfBlank4 != null) {
            this.faceNotFoundText = UIExtsKt.fromHTML(nullIfBlank4);
        }
        ShimmerTextView shimmerTextView = binding.statusText;
        Spanned spanned = this.faceNotFoundText;
        if (spanned == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceNotFoundText");
            spanned = null;
        }
        shimmerTextView.setText(spanned);
        binding.statementText.setText(getParsedStatementText());
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.facePreview = new HVFacePreview(requireContext, new HVCamConfig(false, false, false, 0.0f, 0.0f, false, false, binding.camView.getWidth(), 127, null), getLifecycleScope());
        this.isCameraRunning = true;
        ConstraintLayout constraintLayout = binding.camView;
        HVFacePreview hVFacePreview = this.facePreview;
        if (hVFacePreview == null) {
            Intrinsics.throwUninitializedPropertyAccessException("facePreview");
            hVFacePreview = null;
        }
        constraintLayout.addView(hVFacePreview);
        HVFacePreview hVFacePreview2 = this.facePreview;
        if (hVFacePreview2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("facePreview");
            hVFacePreview2 = null;
        }
        observeUIState(hVFacePreview2);
        HyperSnapBridgeKt.getUiConfigUtil().customiseBackButtonIcon(binding.hkLayoutTopBar.ivBack);
        HyperSnapBridgeKt.getUiConfigUtil().customiseClientLogo(binding.hkLayoutTopBar.hkClientLogo);
        HyperSnapBridgeKt.getUiConfigUtil().customisePrimaryButton(binding.btnPrimary);
        HyperSnapBridgeKt.getUiConfigUtil().customiseSecondaryButton((Button) binding.btnSecondary);
        HyperSnapBridgeKt.getUiConfigUtil().customiseStatusTextView(binding.statusText);
        HyperSnapBridgeKt.getUiConfigUtil().customiseStatementHelperTextView(binding.statementHelpText);
        HyperSnapBridgeKt.getUiConfigUtil().customiseStatementTextView(binding.statementText);
        HyperSnapBridgeKt.getUiConfigUtil().customiseLoaderTextView(binding.tvLoadingMsg);
        HyperSnapBridgeKt.getUiConfigUtil().customiseRetakeMessageTextView(binding.tvError);
        binding.btnPrimary.setIcon(null);
        binding.statementHelpText.setAlpha(0.4f);
        binding.statementText.setAlpha(0.4f);
        binding.statementHelpText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.hk_speaking_head, 0, 0, 0);
        HSUIConfig uiConfigData = getMainVM().getUiConfigData();
        loadBackground$hyperkyc_release(uiConfigData != null ? uiConfigData.getBackgroundImageUrl() : null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String getParsedStatementText() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("getParsedStatementText() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "getParsedStatementText() called ");
                }
            }
        }
        String str2 = getTextConfigs().get("statementText");
        if (str2 == null) {
            str2 = "{{otp}}";
        }
        String str3 = str2;
        for (Map.Entry<String, String> entry : this.vsConfigV2.getUserData().entrySet()) {
            str3 = StringsKt.replace$default(str3, "{{" + entry.getKey() + "}}", entry.getValue(), false, 4, (Object) null);
        }
        this.statementText = str3;
        WorkflowModule.Properties.StatementV2 statement = this.vsConfigV2.getStatement();
        return Intrinsics.areEqual(statement != null ? statement.getType() : null, "code") ? CollectionsKt.joinToString$default(StringsKt.toList(str3), " - ", null, null, 0, null, null, 62, null) : str3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x013d, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void observeUIState(HVFacePreview facePreview) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        Job launch$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str2 = "observeUIState() called with: facePreview = " + facePreview;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "observeUIState() called with: facePreview = " + facePreview;
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(getLifecycleScope(), null, null, new VideoStatementV2Fragment$observeUIState$2(facePreview, this, null), 3, null);
        this.faceDetectorListenerJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void moveToRecordingScreen() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("moveToRecordingScreen() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "moveToRecordingScreen() called ");
                }
            }
        }
        this.isSelfieCaptured = false;
        this.imageUri = null;
        this.videoUri = null;
        this.audioUri = null;
        this.timeOutOfFrameMs = 0;
        this.isFaceDetectionCheckFailed = false;
        this.isFaceNotDetected = false;
        this.isFaceFoundOnce = false;
        this.startTimestamp = System.currentTimeMillis();
        this.endTimestamp = System.currentTimeMillis();
        this.shimmer.cancel();
        Job job = this.faceDetectorListenerJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.currentState = VideoStatementState.RECORDING;
        initViewsForRecording();
        HVSessionRecorder companion4 = HVSessionRecorder.INSTANCE.getInstance();
        Context applicationContext = requireActivity().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "requireActivity().applicationContext");
        Window window = requireActivity().getWindow();
        Intrinsics.checkNotNullExpressionValue(window, "requireActivity().window");
        companion4.startRecordingCameraPreview$hyperkyc_release(applicationContext, window, true, getLifecycleScope(), new VideoStatementV2LowStorageExceptionListener() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$$ExternalSyntheticLambda3
            @Override // co.hyperverge.hyperkyc.hvsessionrecorder.VideoStatementV2LowStorageExceptionListener
            public final void invoke() {
                VideoStatementV2Fragment.moveToRecordingScreen$lambda$20(VideoStatementV2Fragment.this);
            }
        });
        HVSessionRecorder.INSTANCE.getInstance().resume$hyperkyc_release();
        this.startTimestamp = System.currentTimeMillis();
        startTimer(this.vsConfigV2.getStatement() != null ? r0.getDuration() : 5000L);
    }

    /* JADX WARN: Code restructure failed: missing block: B:71:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void initViewsForRecording() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("initViewsForRecording() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "initViewsForRecording() called ");
                }
            }
        }
        HkFragmentVideoStatementV2Binding binding = getBinding();
        if (!this.isFirstRecording) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            this.facePreview = new HVFacePreview(requireContext, new HVCamConfig(false, false, false, 0.0f, 0.0f, false, false, binding.camView.getWidth(), 127, null), getLifecycleScope());
            this.isCameraRunning = true;
            ConstraintLayout constraintLayout = binding.camView;
            HVFacePreview hVFacePreview = this.facePreview;
            if (hVFacePreview == null) {
                Intrinsics.throwUninitializedPropertyAccessException("facePreview");
                hVFacePreview = null;
            }
            constraintLayout.addView(hVFacePreview);
        }
        HVFacePreview hVFacePreview2 = this.facePreview;
        if (hVFacePreview2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("facePreview");
            hVFacePreview2 = null;
        }
        observeUIState(hVFacePreview2);
        ShimmerTextView statusText = binding.statusText;
        Intrinsics.checkNotNullExpressionValue(statusText, "statusText");
        statusText.setVisibility(0);
        ConstraintLayout camView = binding.camView;
        Intrinsics.checkNotNullExpressionValue(camView, "camView");
        camView.setVisibility(0);
        ConstraintLayout bottomContainer = binding.bottomContainer;
        Intrinsics.checkNotNullExpressionValue(bottomContainer, "bottomContainer");
        bottomContainer.setVisibility(0);
        ShimmerTextView statementHelpText = binding.statementHelpText;
        Intrinsics.checkNotNullExpressionValue(statementHelpText, "statementHelpText");
        statementHelpText.setVisibility(0);
        TextView statementText = binding.statementText;
        Intrinsics.checkNotNullExpressionValue(statementText, "statementText");
        statementText.setVisibility(0);
        LinearLayout recIndicator = binding.recIndicator;
        Intrinsics.checkNotNullExpressionValue(recIndicator, "recIndicator");
        recIndicator.setVisibility(0);
        MaterialButton btnSecondary = binding.btnSecondary;
        Intrinsics.checkNotNullExpressionValue(btnSecondary, "btnSecondary");
        btnSecondary.setVisibility(0);
        ConstraintLayout videoContainer = binding.videoContainer;
        Intrinsics.checkNotNullExpressionValue(videoContainer, "videoContainer");
        videoContainer.setVisibility(8);
        ShimmeringMaterialButton btnPrimary = binding.btnPrimary;
        Intrinsics.checkNotNullExpressionValue(btnPrimary, "btnPrimary");
        btnPrimary.setVisibility(8);
        ImageView imageView = binding.hkLayoutTopBar.ivBack;
        Intrinsics.checkNotNullExpressionValue(imageView, "hkLayoutTopBar.ivBack");
        imageView.setVisibility(4);
        if (this.statementHelpTextString.length() == 0) {
            this.statementHelpTextString = binding.statementHelpText.getText().toString();
        }
        binding.btnSecondary.setClickable(false);
        binding.btnSecondary.getBackground().setAlpha(102);
        binding.statusText.setAlpha(0.4f);
        binding.statementHelpText.setAlpha(1.0f);
        binding.statementText.setAlpha(1.0f);
        binding.btnPrimary.getBackground().setAlpha(255);
        binding.btnSecondary.setText(getString(R.string.hk_vsV2_btn_cta_done));
        String nullIfBlank = CoreExtsKt.nullIfBlank(getTextConfigs().get("stopRecordingButton"));
        if (nullIfBlank != null) {
            binding.btnSecondary.setText(UIExtsKt.fromHTML(nullIfBlank));
        }
        WorkflowModule.Properties.StatementV2 statement = this.vsConfigV2.getStatement();
        if (Intrinsics.areEqual(statement != null ? statement.getType() : null, "code")) {
            binding.statementText.setText(getFormattedText(binding.statementText.getText().toString(), binding.statementText.getCurrentTextColor()));
        }
    }

    private final Spanned getFormattedText(String text, int color) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        int alphaComponent;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str2 = "getFormattedText() called with: text = " + text + ", color = " + color;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "getFormattedText() called with: text = " + text + ", color = " + color;
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        alphaComponent = ColorUtils.setAlphaComponent(color, 66);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String str4 = text;
        for (int i = 0; i < str4.length(); i++) {
            char charAt = str4.charAt(i);
            SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(String.valueOf(charAt));
            if (charAt == '-') {
                spannableStringBuilder2.setSpan(new ForegroundColorSpan(alphaComponent), 0, spannableStringBuilder2.length(), 0);
            } else {
                spannableStringBuilder2.setSpan(new ForegroundColorSpan(color), 0, spannableStringBuilder2.length(), 0);
            }
            spannableStringBuilder.append((CharSequence) spannableStringBuilder2);
        }
        return spannableStringBuilder;
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x0131, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0141, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0145, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0154, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0156, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0161, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0165, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0168, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x016f, code lost:
    
        r0 = new java.lang.StringBuilder();
        r1 = "startTimer() called with: duration = " + r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0183, code lost:
    
        if (r1 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0185, code lost:
    
        r1 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0187, code lost:
    
        r0.append(r1);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0144, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void startTimer(final long duration) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        Integer minimumDuration;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str3 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str4 = "startTimer() called with: duration = " + duration;
        if (str4 == null) {
            str4 = "null ";
        }
        sb.append(str4);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                        str = null;
                    } else {
                        str = null;
                        str2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls2 = getClass();
                    str2 = cls2 != null ? cls2.getCanonicalName() : str;
                }
            }
        }
        WorkflowModule.Properties.StatementV2 statement = this.vsConfigV2.getStatement();
        final long intValue = (statement == null || (minimumDuration = statement.getMinimumDuration()) == null) ? 1000L : minimumDuration.intValue();
        CountDownTimer countDownTimer = new CountDownTimer(duration, this, intValue) { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$startTimer$2
            final /* synthetic */ long $doneButtonEnableTimeout;
            final /* synthetic */ long $duration;
            final /* synthetic */ VideoStatementV2Fragment this$0;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(duration, 1000L);
                this.$duration = duration;
                this.this$0 = this;
                this.$doneButtonEnableTimeout = intValue;
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                boolean z;
                boolean z2;
                int i;
                MainVM mainVM;
                VideoStatementV2Config videoStatementV2Config;
                VideoStatementV2Config videoStatementV2Config2;
                WorkflowModule.Properties.StatementV2.ChecksV2 checks;
                WorkflowModule.Properties.StatementV2.ChecksV2.CheckV2 faceDetection;
                int i2;
                WorkflowModule.Properties.StatementV2.ChecksV2 checks2;
                WorkflowModule.Properties.StatementV2.ChecksV2.CheckV2 faceDetection2;
                z = this.this$0.isFaceNotDetected;
                if (z) {
                    VideoStatementV2Fragment videoStatementV2Fragment = this.this$0;
                    i = videoStatementV2Fragment.timeOutOfFrameMs;
                    videoStatementV2Fragment.timeOutOfFrameMs = i + 1;
                    mainVM = this.this$0.getMainVM();
                    videoStatementV2Config = this.this$0.vsConfigV2;
                    WorkflowModule.Properties.StatementV2 statement2 = videoStatementV2Config.getStatement();
                    boolean z3 = false;
                    if (Intrinsics.areEqual((Object) mainVM.asBoolean$hyperkyc_release((statement2 == null || (checks2 = statement2.getChecks()) == null || (faceDetection2 = checks2.getFaceDetection()) == null) ? null : faceDetection2.getEnable(), false), (Object) true)) {
                        videoStatementV2Config2 = this.this$0.vsConfigV2;
                        WorkflowModule.Properties.StatementV2 statement3 = videoStatementV2Config2.getStatement();
                        if (statement3 != null && (checks = statement3.getChecks()) != null && (faceDetection = checks.getFaceDetection()) != null) {
                            i2 = this.this$0.timeOutOfFrameMs;
                            if (i2 * 1000 == faceDetection.getMaxOutOfFrameTime()) {
                                z3 = true;
                            }
                        }
                        if (z3) {
                            this.this$0.isFaceDetectionCheckFailed = true;
                            this.this$0.stopRecording();
                        }
                    }
                }
                if (this.$duration - millisUntilFinished >= this.$doneButtonEnableTimeout) {
                    z2 = this.this$0.isFaceFoundOnce;
                    if (z2) {
                        BuildersKt__Builders_commonKt.launch$default(this.this$0.getLifecycleScope(), Dispatchers.getMain(), null, new VideoStatementV2Fragment$startTimer$2$onTick$1(this.this$0, null), 2, null);
                    }
                }
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                this.this$0.stopRecording();
            }
        };
        this.timer = countDownTimer;
        countDownTimer.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void stopRecording() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("stopRecording() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "stopRecording() called ");
                }
            }
        }
        HKMainActivity hKMainActivity = (HKMainActivity) getActivity();
        if (hKMainActivity != null) {
            hKMainActivity.setIgnoreBackPress$hyperkyc_release(true);
        }
        this.isFirstRecording = false;
        this.shimmer.cancel();
        Job job = this.faceDetectorListenerJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (this.facePreview != null && this.isCameraRunning) {
            BuildersKt__Builders_commonKt.launch$default(getLifecycleScope(), Dispatchers.getDefault(), null, new VideoStatementV2Fragment$stopRecording$3(this, null), 2, null);
        }
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.endTimestamp = System.currentTimeMillis();
        HVSessionRecorder.INSTANCE.getInstance().stop$hyperkyc_release(new Function1<File, Unit>() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$stopRecording$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(File file) {
                invoke2(file);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(File file) {
                VideoStatementV2Fragment.this.videoUri = file != null ? file.getAbsolutePath() : null;
                VideoStatementV2Fragment.this.audioUri = HVSessionRecorder.INSTANCE.getInstance().getAudioPath();
                BuildersKt__Builders_commonKt.launch$default(VideoStatementV2Fragment.this.getLifecycleScope(), Dispatchers.getMain(), null, new AnonymousClass1(VideoStatementV2Fragment.this, null), 2, null);
                HVSessionRecorder.INSTANCE.deleteInstance();
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: VideoStatementV2Fragment.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$stopRecording$4$1", f = "VideoStatementV2Fragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$stopRecording$4$1, reason: invalid class name */
            /* loaded from: classes2.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;
                final /* synthetic */ VideoStatementV2Fragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(VideoStatementV2Fragment videoStatementV2Fragment, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = videoStatementV2Fragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.startProcessing();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0120, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void startProcessing() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("startProcessing() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "startProcessing() called ");
                }
            }
        }
        initViewsForProcessing();
        BuildersKt__Builders_commonKt.launch$default(getLifecycleScope(), null, null, new VideoStatementV2Fragment$startProcessing$2(this, null), 3, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x0120, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void initViewsForProcessing() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        HVFacePreview hVFacePreview = null;
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("initViewsForProcessing() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "initViewsForProcessing() called ");
                }
            }
        }
        HkFragmentVideoStatementV2Binding binding = getBinding();
        ConstraintLayout constraintLayout = binding.camView;
        HVFacePreview hVFacePreview2 = this.facePreview;
        if (hVFacePreview2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("facePreview");
        } else {
            hVFacePreview = hVFacePreview2;
        }
        constraintLayout.removeView(hVFacePreview);
        ConstraintLayout bottomContainer = binding.bottomContainer;
        Intrinsics.checkNotNullExpressionValue(bottomContainer, "bottomContainer");
        bottomContainer.setVisibility(4);
        ShimmerTextView statusText = binding.statusText;
        Intrinsics.checkNotNullExpressionValue(statusText, "statusText");
        statusText.setVisibility(4);
        LinearLayout recIndicator = binding.recIndicator;
        Intrinsics.checkNotNullExpressionValue(recIndicator, "recIndicator");
        recIndicator.setVisibility(8);
        ImageView progressSpinnerImageView = binding.progressSpinnerImageView;
        Intrinsics.checkNotNullExpressionValue(progressSpinnerImageView, "progressSpinnerImageView");
        progressSpinnerImageView.setVisibility(0);
        TextView tvLoadingMsg = binding.tvLoadingMsg;
        Intrinsics.checkNotNullExpressionValue(tvLoadingMsg, "tvLoadingMsg");
        tvLoadingMsg.setVisibility(0);
        LinearLayout linearLayout = binding.includeBranding.llBrandingRoot;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "includeBranding.llBrandingRoot");
        linearLayout.setVisibility(getMainVM().shouldShowBranding() ? 0 : 8);
        ImageView progressSpinnerImageView2 = binding.progressSpinnerImageView;
        Intrinsics.checkNotNullExpressionValue(progressSpinnerImageView2, "progressSpinnerImageView");
        ViewExtsKt.startAnimation(progressSpinnerImageView2);
        String nullIfBlank = CoreExtsKt.nullIfBlank(getTextConfigs().get("checksLoaderText"));
        if (nullIfBlank != null) {
            binding.tvLoadingMsg.setText(UIExtsKt.fromHTML(nullIfBlank));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateVideoUploadText() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("updateVideoUploadText() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "updateVideoUploadText() called ");
                }
            }
        }
        HkFragmentVideoStatementV2Binding binding = getBinding();
        binding.tvLoadingMsg.setText(getString(R.string.hk_vsV2_processing_text_upload));
        String nullIfBlank = CoreExtsKt.nullIfBlank(getTextConfigs().get("uploadVideoLoaderText"));
        if (nullIfBlank != null) {
            binding.tvLoadingMsg.setText(UIExtsKt.fromHTML(nullIfBlank));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveToReRecordScreen(VideoStatementV2VM.Check error) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str2 = "moveToReRecordScreen() called with: error = " + error;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "moveToReRecordScreen() called with: error = " + error;
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        initViewsForRerecordScreen(error);
    }

    private final void initViewsForRerecordScreen(VideoStatementV2VM.Check error) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str2 = "initViewsForRerecordScreen() called with: error = " + error;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "initViewsForRerecordScreen() called with: error = " + error;
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        HKMainActivity hKMainActivity = (HKMainActivity) getActivity();
        if (hKMainActivity != null) {
            hKMainActivity.setIgnoreBackPress$hyperkyc_release(false);
        }
        final HkFragmentVideoStatementV2Binding binding = getBinding();
        ImageView progressSpinnerImageView = binding.progressSpinnerImageView;
        Intrinsics.checkNotNullExpressionValue(progressSpinnerImageView, "progressSpinnerImageView");
        ViewExtsKt.stopAnimation(progressSpinnerImageView);
        ShimmerTextView statusText = binding.statusText;
        Intrinsics.checkNotNullExpressionValue(statusText, "statusText");
        statusText.setVisibility(8);
        ConstraintLayout camView = binding.camView;
        Intrinsics.checkNotNullExpressionValue(camView, "camView");
        camView.setVisibility(8);
        ImageView progressSpinnerImageView2 = binding.progressSpinnerImageView;
        Intrinsics.checkNotNullExpressionValue(progressSpinnerImageView2, "progressSpinnerImageView");
        progressSpinnerImageView2.setVisibility(8);
        TextView tvLoadingMsg = binding.tvLoadingMsg;
        Intrinsics.checkNotNullExpressionValue(tvLoadingMsg, "tvLoadingMsg");
        tvLoadingMsg.setVisibility(8);
        ShimmerTextView statementHelpText = binding.statementHelpText;
        Intrinsics.checkNotNullExpressionValue(statementHelpText, "statementHelpText");
        statementHelpText.setVisibility(8);
        TextView statementText = binding.statementText;
        Intrinsics.checkNotNullExpressionValue(statementText, "statementText");
        statementText.setVisibility(8);
        LinearLayout linearLayout = binding.includeBranding.llBrandingRoot;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "includeBranding.llBrandingRoot");
        linearLayout.setVisibility(8);
        ConstraintLayout videoContainer = binding.videoContainer;
        Intrinsics.checkNotNullExpressionValue(videoContainer, "videoContainer");
        videoContainer.setVisibility(0);
        ConstraintLayout bottomContainer = binding.bottomContainer;
        Intrinsics.checkNotNullExpressionValue(bottomContainer, "bottomContainer");
        bottomContainer.setVisibility(0);
        MaterialButton btnSecondary = binding.btnSecondary;
        Intrinsics.checkNotNullExpressionValue(btnSecondary, "btnSecondary");
        btnSecondary.setVisibility(8);
        ShimmeringMaterialButton btnPrimary = binding.btnPrimary;
        Intrinsics.checkNotNullExpressionValue(btnPrimary, "btnPrimary");
        btnPrimary.setVisibility(0);
        final MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(binding.vvReview);
        binding.vvReview.setMediaController(mediaController);
        VideoView videoView = binding.vvReview;
        String str4 = this.videoUri;
        videoView.setVideoURI(Uri.parse(str4 != null ? str4 : ""));
        binding.vvReview.setBackgroundColor(0);
        binding.vvReview.start();
        binding.btnPrimary.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoStatementV2Fragment.initViewsForRerecordScreen$lambda$39$lambda$37(mediaController, binding, this, view);
            }
        });
        binding.btnPrimary.setText(getString(R.string.hk_vsV2_btn_cta_re_record));
        String nullIfBlank = CoreExtsKt.nullIfBlank(getTextConfigs().get("reRecordButton"));
        if (nullIfBlank != null) {
            binding.btnPrimary.setText(UIExtsKt.fromHTML(nullIfBlank));
        }
        int i = WhenMappings.$EnumSwitchMapping$0[error.ordinal()];
        if (i == 1) {
            setErrorText(R.string.hk_vsV2_error_text_fd, "faceDetectionFailedErrorText");
            return;
        }
        if (i == 2) {
            setErrorText(R.string.hk_vsV2_error_text_liv, "livenessFailedErrorText");
            return;
        }
        if (i == 3) {
            setErrorText(R.string.hk_vsV2_error_text_fm, "faceMatchFailedErrorText");
        } else if (i == 4) {
            setErrorText(R.string.hk_vsV2_error_text_stt, "speechToTextFailedErrorText");
        } else {
            setErrorText(R.string.hk_vsV2_error_text, "genericErrorText");
        }
    }

    private final void setErrorText(int resId, String key) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str2 = "setErrorText() called with: resId = " + resId + ", key = " + key;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "setErrorText() called with: resId = " + resId + ", key = " + key;
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        HkFragmentVideoStatementV2Binding binding = getBinding();
        binding.tvError.setText(getString(resId));
        String nullIfBlank = CoreExtsKt.nullIfBlank(getTextConfigs().get(key));
        if (nullIfBlank != null) {
            binding.tvError.setText(UIExtsKt.fromHTML(nullIfBlank));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("onStop() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "onStop() called ");
                }
            }
        }
        this.shimmer.cancel();
        super.onStop();
    }
}
