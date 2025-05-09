package vn.ai.faceauth.sdk.presentation.presentation.viewmodel;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.widget.ImageView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.Flow;
import lmlf.ayxnhy.tfwhgw;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.jmrtd.cbeff.ISO781611;
import vn.ai.faceauth.sdk.R;
import vn.ai.faceauth.sdk.camera.domain.model.FaceResult;
import vn.ai.faceauth.sdk.core.extensions.PrimitiveExtensionsKt;
import vn.ai.faceauth.sdk.core.resourceprovider.ResourcesProvider;
import vn.ai.faceauth.sdk.core.viewmodel.StateViewModel;
import vn.ai.faceauth.sdk.databinding.FacesdkFragmentCameraxBinding;
import vn.ai.faceauth.sdk.domain.model.StateFaceWithOval;
import vn.ai.faceauth.sdk.domain.repository.LivenessRepository;
import vn.ai.faceauth.sdk.presentation.domain.configs.LivenessConfig;
import vn.ai.faceauth.sdk.presentation.domain.model.Constants;
import vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness;
import vn.ai.faceauth.sdk.presentation.domain.usecase.GetStatusMessageUseCase;
import vn.ai.faceauth.sdk.presentation.presentation.utils.Logger;
import vn.ai.faceauth.sdk.presentation.presentation.utils.UtilsKt;
import vn.ai.faceauth.sdk.presentation.presentation.widgets.OverlayView;

@Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010I\u001a\u00020JJ\u0006\u0010K\u001a\u00020JJ\u0006\u0010L\u001a\u00020JJ\u0006\u0010M\u001a\u00020JJ\u0018\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020QH\u0002J\u0010\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020QH\u0002J\u0006\u0010V\u001a\u00020JJ\u0010\u0010W\u001a\u00020&2\u0006\u0010X\u001a\u00020\u0007H\u0002J\u0010\u0010Y\u001a\u00020J2\u0006\u0010X\u001a\u00020\u0007H\u0002J\u0010\u0010Z\u001a\u00020&2\u0006\u0010X\u001a\u00020\u0007H\u0002J\u0010\u0010[\u001a\u00020&2\u0006\u0010X\u001a\u00020\u0007H\u0002J\u0010\u0010\\\u001a\u00020&2\u0006\u0010X\u001a\u00020\u0007H\u0002J\"\u0010]\u001a\u00020&2\b\u0010^\u001a\u0004\u0018\u00010_2\u0006\u0010X\u001a\u00020\u00072\u0006\u0010`\u001a\u00020\u001dH\u0002J\u0010\u0010a\u001a\u00020\f2\u0006\u0010b\u001a\u00020\u0015H\u0002J\u0016\u0010c\u001a\u00020J2\f\u0010d\u001a\b\u0012\u0004\u0012\u00020\u00070$H\u0002J\u0006\u0010e\u001a\u00020JJ\u0010\u0010f\u001a\u00020&2\u0006\u0010g\u001a\u00020\"H\u0002J\u001a\u0010h\u001a\u00020J2\u0012\u0010i\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070$0jJ\u0006\u0010k\u001a\u00020JJ\u0006\u0010l\u001a\u00020JJ\u0006\u0010m\u001a\u00020JJ\u0006\u0010n\u001a\u00020JJ \u0010o\u001a\u00020\"2\u0006\u0010X\u001a\u00020\u00072\u0006\u0010p\u001a\u00020>2\u0006\u0010q\u001a\u00020>H\u0002J\u000e\u0010r\u001a\u00020J2\u0006\u0010;\u001a\u00020<J\u0014\u0010s\u001a\u00020J2\f\u0010t\u001a\b\u0012\u0004\u0012\u00020\u001d0$J\u000e\u0010u\u001a\u00020J2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010v\u001a\u00020J2\u0006\u0010X\u001a\u00020_J\u0006\u0010w\u001a\u00020JJ\b\u0010x\u001a\u00020JH\u0002J\b\u0010y\u001a\u00020JH\u0002J\b\u0010z\u001a\u00020JH\u0002J\u0010\u0010{\u001a\u00020J2\u0006\u0010|\u001a\u00020\fH\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00120\u00110\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00070$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u00198F¢\u0006\u0006\u001a\u0004\b(\u0010\u001bR#\u0010)\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00120\u00110\u00198F¢\u0006\u0006\u001a\u0004\b*\u0010\u001bR\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020&0\u0019¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001bR\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020&0\u0019¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001bR\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020&0\u0019¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001bR\u0014\u00101\u001a\b\u0012\u0004\u0012\u00020&0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020&0\u0019¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001bR\u0014\u00104\u001a\b\u0012\u0004\u0012\u00020&0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00105\u001a\b\u0012\u0004\u0012\u00020&0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00107\u001a\u00020&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u000e\u0010;\u001a\u00020<X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020>X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020>X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010@\u001a\b\u0012\u0004\u0012\u00020\u001d0AX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010B\u001a\b\u0012\u0004\u0012\u00020\u001d0AX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010G\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u00198F¢\u0006\u0006\u001a\u0004\bH\u0010\u001b¨\u0006}"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/viewmodel/LivenessViewModel;", "Lvn/ai/faceauth/sdk/core/viewmodel/StateViewModel;", "Lvn/ai/faceauth/sdk/presentation/presentation/viewmodel/LivenessViewState;", "resourcesProvider", "Lvn/ai/faceauth/sdk/core/resourceprovider/ResourcesProvider;", "livenessRepository", "Lvn/ai/faceauth/sdk/domain/repository/LivenessRepository;", "Lvn/ai/faceauth/sdk/camera/domain/model/FaceResult;", "getStatusMessageUseCase", "Lvn/ai/faceauth/sdk/presentation/domain/usecase/GetStatusMessageUseCase;", "(Lvn/ai/faceauth/sdk/core/resourceprovider/ResourcesProvider;Lvn/ai/faceauth/sdk/domain/repository/LivenessRepository;Lvn/ai/faceauth/sdk/presentation/domain/usecase/GetStatusMessageUseCase;)V", "TAG", "", "_callBackCompleted", "Landroidx/lifecycle/MutableLiveData;", "_handleErrorLivenessMutable", "_handleSuccessLivenessMutable", "", "", "_state", "_updateStateStepLivenessMutable", "Lvn/ai/faceauth/sdk/domain/model/StateFaceWithOval;", "binding", "Lvn/ai/faceauth/sdk/databinding/FacesdkFragmentCameraxBinding;", "callBackCompleted", "Landroidx/lifecycle/LiveData;", "getCallBackCompleted", "()Landroidx/lifecycle/LiveData;", "currentStep", "Lvn/ai/faceauth/sdk/presentation/domain/model/StepLiveness;", "currentTime", "", "faceHistory", "", "Landroid/graphics/Rect;", "facesMutable", "", "firstCheckMutable", "", "handleErrorLiveness", "getHandleErrorLiveness", "handleSuccessLiveness", "getHandleSuccessLiveness", "hasFistCheck", "getHasFistCheck", "hasHeadMovedCenter", "getHasHeadMovedCenter", "hasZoomIn", "getHasZoomIn", "hasZoomInMutable", "hasZoomOut", "getHasZoomOut", "hasZoomOutMutable", "headMovementCenterMutable", "isFacesDetectedCorrect", "isFinished", "()Z", "setFinished", "(Z)V", "livenessConfig", "Lvn/ai/faceauth/sdk/presentation/domain/configs/LivenessConfig;", "maxFrames", "", "movementThreshold", "originalRequestedSteps", "Ljava/util/LinkedList;", "requestedSteps", "getRequestedSteps", "()Ljava/util/LinkedList;", "setRequestedSteps", "(Ljava/util/LinkedList;)V", "updateStateStepLiveness", "getUpdateStateStepLiveness", "alertBlurImage", "", "alertFaceInOval", "alertStateEyesClose", "alertStateHeadPose", "calculateFaceMovement", "", "from", "Lvn/ai/faceauth/sdk/domain/model/Rect;", "to", "calculateRectCenter", "Landroid/graphics/PointF;", "bounds", "callFinish", "checkEyesClosed", "face", "checkFaceLiveness", "checkLuminosity", "checkPose", "checkSmile", "checkStepWithRect", "rectF", "Landroid/graphics/RectF;", "stepZoom", "getStatusMessage", "stateFaceWithOval", "handleFaces", "listFaceResult", "hideLoading", "isFaceStable", "currentBounds", "observeFacesDetection", "facesFlowable", "Lkotlinx/coroutines/flow/Flow;", "reDraw", "removeCurrentStep", "reset", "resetState", "scaleBoundingBox", "screenWidth", "screenHeight", "setupLivenessConfig", "setupSteps", "validateRequested", "setupView", "showFace", "showLoading", "showStateNoFace", "showSuccess", "stopBySystem", "updateCompleted", "newData", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class LivenessViewModel extends StateViewModel<LivenessViewState> {
    private final String TAG;
    private final MutableLiveData<String> _callBackCompleted;
    private final MutableLiveData<String> _handleErrorLivenessMutable;
    private final MutableLiveData<Map<String, Object>> _handleSuccessLivenessMutable;
    private final LivenessViewState _state;
    private final MutableLiveData<StateFaceWithOval> _updateStateStepLivenessMutable;
    private FacesdkFragmentCameraxBinding binding;
    private StepLiveness currentStep;
    private long currentTime;
    private final List<Rect> faceHistory;
    private List<FaceResult> facesMutable;
    private final MutableLiveData<Boolean> firstCheckMutable;
    private final GetStatusMessageUseCase getStatusMessageUseCase;
    private final LiveData<Boolean> hasFistCheck;
    private final LiveData<Boolean> hasHeadMovedCenter;
    private final LiveData<Boolean> hasZoomIn;
    private final MutableLiveData<Boolean> hasZoomInMutable;
    private final LiveData<Boolean> hasZoomOut;
    private final MutableLiveData<Boolean> hasZoomOutMutable;
    private final MutableLiveData<Boolean> headMovementCenterMutable;
    private boolean isFacesDetectedCorrect;
    private boolean isFinished;
    private LivenessConfig livenessConfig;
    private final LivenessRepository<FaceResult> livenessRepository;
    private final int maxFrames;
    private final int movementThreshold;
    private LinkedList<StepLiveness> originalRequestedSteps;
    private LinkedList<StepLiveness> requestedSteps;
    private final ResourcesProvider resourcesProvider;

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StepLiveness.values().length];
            iArr[StepLiveness.STEP_ZOOM_IN.ordinal()] = 1;
            iArr[StepLiveness.STEP_ZOOM_OUT.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LivenessViewModel(ResourcesProvider resourcesProvider, LivenessRepository<FaceResult> livenessRepository, GetStatusMessageUseCase getStatusMessageUseCase) {
        super(new LivenessViewState(null, false, null, 7, null));
        this.resourcesProvider = resourcesProvider;
        this.livenessRepository = livenessRepository;
        this.getStatusMessageUseCase = getStatusMessageUseCase;
        this.TAG = tfwhgw.rnigpa(114);
        this.currentStep = StepLiveness.STEP_FIRST_CHECK;
        this._state = new LivenessViewState(null, false, null, 7, null);
        this.originalRequestedSteps = new LinkedList<>();
        this.requestedSteps = new LinkedList<>();
        this.facesMutable = CollectionsKt.emptyList();
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        this.hasZoomInMutable = mutableLiveData;
        this.hasZoomIn = mutableLiveData;
        MutableLiveData<Boolean> mutableLiveData2 = new MutableLiveData<>();
        this.hasZoomOutMutable = mutableLiveData2;
        this.hasZoomOut = mutableLiveData2;
        MutableLiveData<Boolean> mutableLiveData3 = new MutableLiveData<>();
        this.headMovementCenterMutable = mutableLiveData3;
        this.hasHeadMovedCenter = mutableLiveData3;
        MutableLiveData<Boolean> mutableLiveData4 = new MutableLiveData<>();
        this.firstCheckMutable = mutableLiveData4;
        this.hasFistCheck = mutableLiveData4;
        this._callBackCompleted = new MutableLiveData<>();
        this._handleSuccessLivenessMutable = new MutableLiveData<>();
        this._handleErrorLivenessMutable = new MutableLiveData<>();
        this._updateStateStepLivenessMutable = new MutableLiveData<>();
        this.faceHistory = new ArrayList();
        this.maxFrames = 10;
        this.movementThreshold = 25;
    }

    private final double calculateFaceMovement(vn.ai.faceauth.sdk.domain.model.Rect from, vn.ai.faceauth.sdk.domain.model.Rect to) {
        PointF calculateRectCenter = calculateRectCenter(from);
        PointF calculateRectCenter2 = calculateRectCenter(to);
        if (calculateRectCenter == null || calculateRectCenter2 == null) {
            return Double.MAX_VALUE;
        }
        return Math.hypot(calculateRectCenter.x - calculateRectCenter2.x, calculateRectCenter.y - calculateRectCenter2.y);
    }

    private final PointF calculateRectCenter(vn.ai.faceauth.sdk.domain.model.Rect bounds) {
        float f = 2;
        return new PointF((bounds.getRight() + bounds.getLeft()) / f, (bounds.getBottom() + bounds.getTop()) / f);
    }

    private final boolean checkEyesClosed(FaceResult face) {
        Float leftEyeOpenProbability = face.getLeftEyeOpenProbability();
        if (leftEyeOpenProbability != null) {
            float floatValue = leftEyeOpenProbability.floatValue();
            Float rightEyeOpenProbability = face.getRightEyeOpenProbability();
            if (rightEyeOpenProbability != null) {
                float floatValue2 = rightEyeOpenProbability.floatValue();
                Log.d(this.TAG, tfwhgw.rnigpa(115) + face.getLeftEyeOpenProbability());
                Log.d(this.TAG, tfwhgw.rnigpa(116) + face.getRightEyeOpenProbability());
                Constants.Companion companion = Constants.INSTANCE;
                if (floatValue < companion.getEYE_OPENED_PROBABILITY() && floatValue2 < companion.getEYE_OPENED_PROBABILITY()) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:111:0x01b6, code lost:
    
        if (r7 != null) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0228, code lost:
    
        r7.hideProcess();
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x01df, code lost:
    
        if (r7 != null) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x0208, code lost:
    
        if (r7 != null) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0226, code lost:
    
        if (r7 != null) goto L120;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void checkFaceLiveness(FaceResult face) {
        ImageView imageView;
        ImageView imageView2;
        OverlayView overlayView;
        OverlayView overlayView2;
        OverlayView overlayView3;
        ImageView imageView3;
        OverlayView overlayView4;
        OverlayView overlayView5;
        OverlayView overlayView6;
        OverlayView overlayView7;
        OverlayView overlayView8;
        OverlayView overlayView9;
        OverlayView overlayView10;
        if (PrimitiveExtensionsKt.orFalse(Boolean.valueOf(!this.requestedSteps.isEmpty()))) {
            Logger.INSTANCE.addStep(this.requestedSteps.getFirst());
            StepLiveness first = this.requestedSteps.getFirst();
            int i = first == null ? -1 : WhenMappings.$EnumSwitchMapping$0[first.ordinal()];
            if (i != -1) {
                String rnigpa = tfwhgw.rnigpa(117);
                if (i == 1) {
                    Log.d(this.TAG, tfwhgw.rnigpa(123));
                    FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding = this.binding;
                    RectF rectZoomIn = (facesdkFragmentCameraxBinding == null || (overlayView3 = facesdkFragmentCameraxBinding.overlayView) == null) ? null : overlayView3.getRectZoomIn();
                    StepLiveness stepLiveness = StepLiveness.STEP_ZOOM_IN;
                    if (!checkStepWithRect(rectZoomIn, face, stepLiveness)) {
                        Log.d(this.TAG, tfwhgw.rnigpa(124));
                        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding2 = this.binding;
                        if (facesdkFragmentCameraxBinding2 != null) {
                            overlayView2 = facesdkFragmentCameraxBinding2.overlayView;
                        }
                        this.hasZoomInMutable.setValue(Boolean.FALSE);
                        return;
                    }
                    if (!checkPose(face)) {
                        setState(this._state.livenessMessage(getStatusMessage(StateFaceWithOval.FACE_NOT_ALIGN), stepLiveness));
                        Log.d(this.TAG, tfwhgw.rnigpa(125));
                        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding3 = this.binding;
                        if (facesdkFragmentCameraxBinding3 != null) {
                            overlayView2 = facesdkFragmentCameraxBinding3.overlayView;
                        }
                        this.hasZoomInMutable.setValue(Boolean.FALSE);
                        return;
                    }
                    if (!checkSmile(face)) {
                        setState(this._state.livenessMessage(getStatusMessage(StateFaceWithOval.NO_SMILE), stepLiveness));
                        Log.d(this.TAG, tfwhgw.rnigpa(126));
                        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding4 = this.binding;
                        if (facesdkFragmentCameraxBinding4 != null) {
                            overlayView2 = facesdkFragmentCameraxBinding4.overlayView;
                        }
                        this.hasZoomInMutable.setValue(Boolean.FALSE);
                        return;
                    }
                    if (checkEyesClosed(face)) {
                        setState(this._state.livenessMessage(getStatusMessage(StateFaceWithOval.EYES_CLOSED), stepLiveness));
                        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding5 = this.binding;
                        if (facesdkFragmentCameraxBinding5 != null) {
                            overlayView2 = facesdkFragmentCameraxBinding5.overlayView;
                        }
                    } else if (isFaceStable(face.getBounds())) {
                        Log.d(this.TAG, tfwhgw.rnigpa(127) + this.hasZoomInMutable.getValue());
                        this.hasZoomInMutable.setValue(Boolean.TRUE);
                        if (this.isFinished) {
                            return;
                        }
                        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding6 = this.binding;
                        if (facesdkFragmentCameraxBinding6 != null) {
                            facesdkFragmentCameraxBinding6.overlayView.scale(true);
                        }
                        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding7 = this.binding;
                        if (!((facesdkFragmentCameraxBinding7 == null || (imageView2 = facesdkFragmentCameraxBinding7.btnBack) == null || !imageView2.isEnabled()) ? false : true)) {
                            return;
                        }
                        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding8 = this.binding;
                        imageView = facesdkFragmentCameraxBinding8 != null ? facesdkFragmentCameraxBinding8.btnBack : null;
                        if (imageView == null) {
                            return;
                        }
                    } else {
                        Log.d(this.TAG, rnigpa);
                        setState(this._state.livenessMessage(getStatusMessage(StateFaceWithOval.KEEP_HOLD_ON), stepLiveness));
                        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding9 = this.binding;
                        if (facesdkFragmentCameraxBinding9 != null && (overlayView = facesdkFragmentCameraxBinding9.overlayView) != null) {
                            overlayView.showProcess();
                        }
                    }
                    this.hasZoomInMutable.setValue(Boolean.FALSE);
                    return;
                }
                if (i != 2) {
                    return;
                }
                Log.d(this.TAG, tfwhgw.rnigpa(118));
                FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding10 = this.binding;
                RectF rectZoomOut = (facesdkFragmentCameraxBinding10 == null || (overlayView10 = facesdkFragmentCameraxBinding10.overlayView) == null) ? null : overlayView10.getRectZoomOut();
                StepLiveness stepLiveness2 = StepLiveness.STEP_ZOOM_OUT;
                if (!checkStepWithRect(rectZoomOut, face, stepLiveness2)) {
                    Log.d(this.TAG, tfwhgw.rnigpa(119));
                    FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding11 = this.binding;
                    if (facesdkFragmentCameraxBinding11 == null || (overlayView9 = facesdkFragmentCameraxBinding11.overlayView) == null) {
                        return;
                    }
                    overlayView9.hideProcess();
                    return;
                }
                if (!checkPose(face)) {
                    setState(this._state.livenessMessage(getStatusMessage(StateFaceWithOval.FACE_NOT_ALIGN), StepLiveness.STEP_ZOOM_IN));
                    Log.d(this.TAG, tfwhgw.rnigpa(120));
                    FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding12 = this.binding;
                    if (facesdkFragmentCameraxBinding12 == null || (overlayView8 = facesdkFragmentCameraxBinding12.overlayView) == null) {
                        return;
                    }
                    overlayView8.hideProcess();
                    return;
                }
                if (!checkSmile(face)) {
                    setState(this._state.livenessMessage(getStatusMessage(StateFaceWithOval.NO_SMILE), StepLiveness.STEP_ZOOM_IN));
                    Log.d(this.TAG, tfwhgw.rnigpa(121));
                    FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding13 = this.binding;
                    if (facesdkFragmentCameraxBinding13 == null || (overlayView7 = facesdkFragmentCameraxBinding13.overlayView) == null) {
                        return;
                    }
                    overlayView7.hideProcess();
                    return;
                }
                if (checkEyesClosed(face)) {
                    setState(this._state.livenessMessage(getStatusMessage(StateFaceWithOval.EYES_CLOSED), StepLiveness.STEP_ZOOM_IN));
                    FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding14 = this.binding;
                    if (facesdkFragmentCameraxBinding14 == null || (overlayView6 = facesdkFragmentCameraxBinding14.overlayView) == null) {
                        return;
                    }
                    overlayView6.hideProcess();
                    return;
                }
                if (!checkLuminosity(face)) {
                    setState(this._state.livenessMessage(getStatusMessage(StateFaceWithOval.LUMINOSITY), StepLiveness.STEP_ZOOM_IN));
                    FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding15 = this.binding;
                    if (facesdkFragmentCameraxBinding15 == null || (overlayView5 = facesdkFragmentCameraxBinding15.overlayView) == null) {
                        return;
                    }
                    overlayView5.hideProcess();
                    return;
                }
                if (!isFaceStable(face.getBounds())) {
                    Log.d(this.TAG, rnigpa);
                    setState(this._state.livenessMessage(getStatusMessage(StateFaceWithOval.KEEP_HOLD_ON), stepLiveness2));
                    FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding16 = this.binding;
                    if (facesdkFragmentCameraxBinding16 == null || (overlayView4 = facesdkFragmentCameraxBinding16.overlayView) == null) {
                        return;
                    }
                    overlayView4.showProcess();
                    return;
                }
                Log.d(this.TAG, tfwhgw.rnigpa(122));
                this.hasZoomOutMutable.setValue(Boolean.TRUE);
                if (this.isFinished) {
                    return;
                }
                FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding17 = this.binding;
                if (facesdkFragmentCameraxBinding17 != null) {
                    facesdkFragmentCameraxBinding17.overlayView.scale(true);
                }
                FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding18 = this.binding;
                if (!((facesdkFragmentCameraxBinding18 == null || (imageView3 = facesdkFragmentCameraxBinding18.btnBack) == null || !imageView3.isEnabled()) ? false : true)) {
                    return;
                }
                FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding19 = this.binding;
                imageView = facesdkFragmentCameraxBinding19 != null ? facesdkFragmentCameraxBinding19.btnBack : null;
                if (imageView == null) {
                    return;
                }
                imageView.setVisibility(0);
                return;
            }
        }
        updateCompleted("");
    }

    private final boolean checkLuminosity(FaceResult face) {
        Float luminosity = face.getLuminosity();
        Boolean bool = null;
        LivenessConfig livenessConfig = null;
        if (luminosity != null) {
            float floatValue = luminosity.floatValue();
            LivenessConfig livenessConfig2 = this.livenessConfig;
            if (livenessConfig2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(tfwhgw.rnigpa(128));
            } else {
                livenessConfig = livenessConfig2;
            }
            bool = Boolean.valueOf(floatValue > livenessConfig.getLuminosityProbability() * ((float) 255));
        }
        return PrimitiveExtensionsKt.orFalse(bool);
    }

    private final boolean checkPose(FaceResult face) {
        float headEulerAngleX = face.getHeadEulerAngleX();
        if (-12.0f <= headEulerAngleX && headEulerAngleX <= 12.0f) {
            float headEulerAngleY = face.getHeadEulerAngleY();
            if (-8.0f <= headEulerAngleY && headEulerAngleY <= 5.0f) {
                float headEulerAngleZ = face.getHeadEulerAngleZ();
                if (-8.0f <= headEulerAngleZ && headEulerAngleZ <= 8.0f) {
                    return true;
                }
            }
        }
        Log.e(this.TAG, tfwhgw.rnigpa(129));
        Log.e(this.TAG, tfwhgw.rnigpa(130) + face.getHeadEulerAngleX());
        Log.e(this.TAG, tfwhgw.rnigpa(ISO781611.CREATION_DATE_AND_TIME_TAG) + face.getHeadEulerAngleY());
        Log.e(this.TAG, tfwhgw.rnigpa(CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA) + face.getHeadEulerAngleZ());
        return false;
    }

    private final boolean checkSmile(FaceResult face) {
        Float smilingProbability = face.getSmilingProbability();
        if (smilingProbability != null) {
            float floatValue = smilingProbability.floatValue();
            LivenessConfig livenessConfig = this.livenessConfig;
            String rnigpa = tfwhgw.rnigpa(133);
            LivenessConfig livenessConfig2 = null;
            if (livenessConfig == null) {
                Intrinsics.throwUninitializedPropertyAccessException(rnigpa);
                livenessConfig = null;
            }
            if (!(livenessConfig.getSmileProbability() == 0.0f)) {
                LivenessConfig livenessConfig3 = this.livenessConfig;
                if (livenessConfig3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(rnigpa);
                } else {
                    livenessConfig2 = livenessConfig3;
                }
                if (!(floatValue <= livenessConfig2.getSmileProbability())) {
                    Log.d(this.TAG, tfwhgw.rnigpa(134));
                    return false;
                }
            }
        }
        return true;
    }

    private final boolean checkStepWithRect(RectF rectF, FaceResult face, StepLiveness stepZoom) {
        this.currentStep = stepZoom;
        if (rectF == null) {
            Log.e(this.TAG, tfwhgw.rnigpa(CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA));
            return false;
        }
        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding = this.binding;
        int width = (facesdkFragmentCameraxBinding != null ? facesdkFragmentCameraxBinding.overlayView : null).getWidth();
        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding2 = this.binding;
        Rect scaleBoundingBox = scaleBoundingBox(face, width, (facesdkFragmentCameraxBinding2 != null ? facesdkFragmentCameraxBinding2.overlayView : null).getHeight());
        float width2 = (scaleBoundingBox.width() * scaleBoundingBox.height()) / (rectF.width() * rectF.height());
        Log.d(this.TAG, tfwhgw.rnigpa(135) + width2);
        double d = (double) width2;
        if (d < 0.48d) {
            Log.d(this.TAG, tfwhgw.rnigpa(136));
            setState(this._state.livenessMessage(getStatusMessage(StateFaceWithOval.MOVE_CLOSER), stepZoom));
            return false;
        }
        if (d > 1.05d) {
            Log.d(this.TAG, tfwhgw.rnigpa(CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA));
            setState(this._state.livenessMessage(getStatusMessage(StateFaceWithOval.MOVE_AWAY), stepZoom));
            return false;
        }
        if (new RectF(rectF.left - (rectF.width() * 0.1f), rectF.top - (rectF.height() * 0.1f), (rectF.width() * 0.1f) + rectF.right, (rectF.height() * 0.1f) + rectF.bottom).contains(new RectF(scaleBoundingBox))) {
            Log.d(this.TAG, tfwhgw.rnigpa(139));
            return true;
        }
        Log.d(this.TAG, tfwhgw.rnigpa(138));
        setState(this._state.livenessMessage(getStatusMessage(StateFaceWithOval.FRAME_YOUR_FACE_IN_OVAL), stepZoom));
        return false;
    }

    private final String getStatusMessage(StateFaceWithOval stateFaceWithOval) {
        return this.getStatusMessageUseCase.invoke(stateFaceWithOval);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleFaces(List<FaceResult> listFaceResult) {
        this.facesMutable = listFaceResult;
        Log.d(this.TAG, tfwhgw.rnigpa(CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA) + this.facesMutable.size());
        if (System.currentTimeMillis() - this.currentTime > 200) {
            boolean isFacesDetectedCorrect = this.livenessRepository.isFacesDetectedCorrect(listFaceResult);
            this.isFacesDetectedCorrect = isFacesDetectedCorrect;
            if (isFacesDetectedCorrect || !(!listFaceResult.isEmpty())) {
                if (listFaceResult.isEmpty()) {
                    Log.d(this.TAG, tfwhgw.rnigpa(142));
                    showStateNoFace();
                    return;
                }
                return;
            }
            FaceResult faceResult = (FaceResult) CollectionsKt.first((List) listFaceResult);
            for (FaceResult faceResult2 : listFaceResult) {
                if (faceResult2.getBounds().height() * faceResult2.getBounds().width() > faceResult.getBounds().height() * faceResult.getBounds().width()) {
                    faceResult = faceResult2;
                }
            }
            this.currentTime = System.currentTimeMillis();
            checkFaceLiveness(faceResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: hideLoading$lambda-14, reason: not valid java name */
    public static final void m2988hideLoading$lambda14(LivenessViewModel livenessViewModel) {
        ImageView imageView;
        OverlayView overlayView;
        OverlayView overlayView2;
        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding = livenessViewModel.binding;
        if (facesdkFragmentCameraxBinding != null && (overlayView2 = facesdkFragmentCameraxBinding.overlayView) != null) {
            overlayView2.hideTextCenter();
        }
        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding2 = livenessViewModel.binding;
        if (facesdkFragmentCameraxBinding2 != null && (overlayView = facesdkFragmentCameraxBinding2.overlayView) != null) {
            overlayView.hideBlur();
        }
        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding3 = livenessViewModel.binding;
        if ((facesdkFragmentCameraxBinding3 == null || (imageView = facesdkFragmentCameraxBinding3.btnBack) == null || !imageView.isEnabled()) ? false : true) {
            FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding4 = livenessViewModel.binding;
            ImageView imageView2 = facesdkFragmentCameraxBinding4 != null ? facesdkFragmentCameraxBinding4.btnBack : null;
            if (imageView2 == null) {
                return;
            }
            imageView2.setVisibility(0);
        }
    }

    private final boolean isFaceStable(Rect currentBounds) {
        Log.d(this.TAG, tfwhgw.rnigpa(CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA));
        if (this.faceHistory.size() >= this.maxFrames) {
            this.faceHistory.remove(0);
        }
        this.faceHistory.add(currentBounds);
        if (this.faceHistory.size() < this.maxFrames - 5) {
            return false;
        }
        int size = this.faceHistory.size();
        double d = 0.0d;
        for (int i = 0; i < size; i++) {
            int size2 = this.faceHistory.size();
            for (int i2 = 0; i2 < size2; i2++) {
                if (i != i2) {
                    d = Math.max(d, calculateFaceMovement(UtilsKt.toDomainRect(this.faceHistory.get(i)), UtilsKt.toDomainRect(this.faceHistory.get(i2))));
                }
            }
        }
        Log.d(this.TAG, tfwhgw.rnigpa(CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA) + d);
        return d <= ((double) this.movementThreshold);
    }

    private final Rect scaleBoundingBox(FaceResult face, int screenWidth, int screenHeight) {
        float f = screenWidth;
        float intValue = (face.getImageRect() != null ? Integer.valueOf(r0.height()) : null).intValue() / f;
        float intValue2 = (face.getImageRect() != null ? Integer.valueOf(r2.width()) : null).intValue() / screenHeight;
        float f2 = f / 2;
        return new Rect((int) ((f2 - ((int) (face.getBounds().right / intValue))) + f2), (int) (face.getBounds().top / intValue2), (int) (f2 - (((int) (face.getBounds().left / intValue)) - f2)), (int) (face.getBounds().bottom / intValue2));
    }

    private final void showStateNoFace() {
        Log.d(this.TAG, tfwhgw.rnigpa(CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA));
        setState(this._state.livenessMessage(getStatusMessage(StateFaceWithOval.FRAME_YOUR_FACE_IN_OVAL), this.currentStep));
        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding = this.binding;
        if (facesdkFragmentCameraxBinding != null) {
            facesdkFragmentCameraxBinding.overlayView.hideProcess();
        }
    }

    private final void showSuccess() {
        OverlayView overlayView;
        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding = this.binding;
        if (facesdkFragmentCameraxBinding != null && (overlayView = facesdkFragmentCameraxBinding.overlayView) != null) {
            overlayView.setFinished();
        }
        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding2 = this.binding;
        ImageView imageView = facesdkFragmentCameraxBinding2 != null ? facesdkFragmentCameraxBinding2.btnBack : null;
        if (imageView == null) {
            return;
        }
        imageView.setVisibility(8);
    }

    private final void stopBySystem() {
        OverlayView overlayView;
        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding = this.binding;
        if (facesdkFragmentCameraxBinding == null || (overlayView = facesdkFragmentCameraxBinding.overlayView) == null) {
            return;
        }
        overlayView.setSystemStop();
    }

    private final void updateCompleted(String newData) {
        this._callBackCompleted.setValue(newData);
    }

    public final void alertBlurImage() {
        Log.d(this.TAG, tfwhgw.rnigpa(CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA));
        setState(this._state.livenessMessage(getStatusMessage(StateFaceWithOval.KEEP_HOLD_ON), this.currentStep));
    }

    public final void alertFaceInOval() {
        Log.d(this.TAG, tfwhgw.rnigpa(CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA));
        setState(this._state.livenessMessage(getStatusMessage(StateFaceWithOval.FRAME_YOUR_FACE_IN_OVAL), this.currentStep));
    }

    public final void alertStateEyesClose() {
        Log.d(this.TAG, tfwhgw.rnigpa(CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA));
        setState(this._state.livenessMessage(getStatusMessage(StateFaceWithOval.EYES_CLOSED), this.currentStep));
    }

    public final void alertStateHeadPose() {
        Log.d(this.TAG, tfwhgw.rnigpa(CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA));
        setState(this._state.livenessMessage(getStatusMessage(StateFaceWithOval.FACE_NOT_ALIGN), this.currentStep));
    }

    public final void callFinish() {
        Log.e(this.TAG, tfwhgw.rnigpa(CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA) + this.isFinished);
        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding = this.binding;
        if (facesdkFragmentCameraxBinding != null) {
            OverlayView overlayView = facesdkFragmentCameraxBinding.overlayView;
            overlayView.stopAnimation();
            overlayView.setFinished();
            this.isFinished = true;
            overlayView.showProcess();
            updateCompleted("");
            facesdkFragmentCameraxBinding.btnBack.setVisibility(8);
        }
    }

    public final LiveData<String> getCallBackCompleted() {
        return this._callBackCompleted;
    }

    public final LiveData<String> getHandleErrorLiveness() {
        return this._handleErrorLivenessMutable;
    }

    public final LiveData<Map<String, Object>> getHandleSuccessLiveness() {
        return this._handleSuccessLivenessMutable;
    }

    public final LiveData<Boolean> getHasFistCheck() {
        return this.hasFistCheck;
    }

    public final LiveData<Boolean> getHasHeadMovedCenter() {
        return this.hasHeadMovedCenter;
    }

    public final LiveData<Boolean> getHasZoomIn() {
        return this.hasZoomIn;
    }

    public final LiveData<Boolean> getHasZoomOut() {
        return this.hasZoomOut;
    }

    public final LinkedList<StepLiveness> getRequestedSteps() {
        return this.requestedSteps;
    }

    public final LiveData<StateFaceWithOval> getUpdateStateStepLiveness() {
        return this._updateStateStepLivenessMutable;
    }

    public final void hideLoading() {
        OverlayView overlayView;
        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding = this.binding;
        if (facesdkFragmentCameraxBinding == null || (overlayView = facesdkFragmentCameraxBinding.overlayView) == null) {
            return;
        }
        overlayView.post(new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.presentation.viewmodel.LivenessViewModel$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LivenessViewModel.m2988hideLoading$lambda14(LivenessViewModel.this);
            }
        });
    }

    /* renamed from: isFinished, reason: from getter */
    public final boolean getIsFinished() {
        return this.isFinished;
    }

    public final void observeFacesDetection(Flow<? extends List<FaceResult>> facesFlowable) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new LivenessViewModel$observeFacesDetection$1(facesFlowable, this, null), 3, null);
    }

    public final void reDraw() {
        OverlayView overlayView;
        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding = this.binding;
        if (facesdkFragmentCameraxBinding == null || (overlayView = facesdkFragmentCameraxBinding.overlayView) == null) {
            return;
        }
        overlayView.reset();
    }

    public final void removeCurrentStep() {
        this.requestedSteps.pop();
    }

    public final void reset() {
        OverlayView overlayView;
        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding = this.binding;
        if (facesdkFragmentCameraxBinding == null || (overlayView = facesdkFragmentCameraxBinding.overlayView) == null) {
            return;
        }
        hideLoading();
        overlayView.scale(false);
    }

    public final void resetState() {
        Log.d(this.TAG, tfwhgw.rnigpa(151));
        LivenessViewState livenessViewState = this._state;
        String statusMessage = getStatusMessage(StateFaceWithOval.FRAME_YOUR_FACE_IN_OVAL);
        StepLiveness stepLiveness = StepLiveness.STEP_FIRST_CHECK;
        setState(livenessViewState.livenessMessage(statusMessage, stepLiveness));
        this.currentStep = stepLiveness;
    }

    public final void setFinished(boolean z) {
        this.isFinished = z;
    }

    public final void setRequestedSteps(LinkedList<StepLiveness> linkedList) {
        this.requestedSteps = linkedList;
    }

    public final void setupLivenessConfig(LivenessConfig livenessConfig) {
        this.livenessConfig = livenessConfig;
    }

    public final void setupSteps(List<? extends StepLiveness> validateRequested) {
        LinkedList<StepLiveness> linkedList = new LinkedList<>();
        linkedList.addAll(validateRequested);
        this.requestedSteps = linkedList;
        LinkedList<StepLiveness> linkedList2 = new LinkedList<>();
        linkedList2.addAll(validateRequested);
        this.originalRequestedSteps = linkedList2;
    }

    public final void setupView(FacesdkFragmentCameraxBinding binding) {
        this.binding = binding;
    }

    public final void showFace(RectF face) {
    }

    public final void showLoading() {
        OverlayView overlayView;
        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding = this.binding;
        if (facesdkFragmentCameraxBinding != null && (overlayView = facesdkFragmentCameraxBinding.overlayView) != null) {
            overlayView.drawTextCenter(ResourcesProvider.DefaultImpls.getString$default(this.resourcesProvider, R.string.facesdk_uploading_encrypt, null, 2, null));
        }
        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding2 = this.binding;
        ImageView imageView = facesdkFragmentCameraxBinding2 != null ? facesdkFragmentCameraxBinding2.btnBack : null;
        if (imageView == null) {
            return;
        }
        imageView.setVisibility(8);
    }
}
