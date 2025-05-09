package co.hyperverge.hyperkyc.ui;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwnerKt;
import co.hyperverge.hyperkyc.BuildConfig;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import co.hyperverge.hyperkyc.core.hv.HSBridgeException;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import co.hyperverge.hyperkyc.core.hv.models.HSRemoteConfig;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
import co.hyperverge.hyperkyc.data.models.KycCountry;
import co.hyperverge.hyperkyc.data.models.Properties;
import co.hyperverge.hyperkyc.data.models.SessionRecordingConfig;
import co.hyperverge.hyperkyc.data.models.WorkflowConfig;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.data.models.result.HyperKycError;
import co.hyperverge.hyperkyc.data.models.result.HyperKycStatus;
import co.hyperverge.hyperkyc.data.models.state.TransactionStateResponse;
import co.hyperverge.hyperkyc.databinding.HkActivityMainBinding;
import co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment;
import co.hyperverge.hyperkyc.ui.nfc.NFCReaderInstructionFragment;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.utils.FormWebViewDriver;
import co.hyperverge.hyperkyc.utils.HSStateHandler;
import co.hyperverge.hyperkyc.utils.HSStateHandler$retrieveState$3$type$1;
import co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.ContextExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoroutineExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.PicassoExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import com.airbnb.lottie.LottieAnimationView;
import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import io.sentry.HttpStatusCodeRange;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.regex.Matcher;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KClass;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import okhttp3.Response;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;

/* compiled from: HKMainActivity.kt */
@Metadata(d1 = {"\u0000 \u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020*H\u0002J\b\u0010,\u001a\u00020\u0017H\u0002J\u0010\u0010-\u001a\u00020*2\u0006\u0010.\u001a\u00020/H\u0002J3\u00100\u001a\u00020*2\u0006\u0010.\u001a\u00020/2\b\u00101\u001a\u0004\u0018\u00010 2\b\u00102\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u00103\u001a\u00020\u0019H\u0002¢\u0006\u0002\u00104J\u0010\u00105\u001a\u00020*2\u0006\u00106\u001a\u000207H\u0002J\u0010\u00108\u001a\u00020*2\u0006\u00109\u001a\u00020:H\u0002J.\u0010;\u001a\u00020*2\u001c\u0010<\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0>\u0012\u0006\u0012\u0004\u0018\u00010?0=H\u0002ø\u0001\u0000¢\u0006\u0002\u0010@J\u0011\u0010A\u001a\u00020*H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010BJ\b\u0010C\u001a\u00020*H\u0002J\u0019\u0010D\u001a\u00020*2\n\b\u0002\u0010E\u001a\u0004\u0018\u00010\u0017H\u0000¢\u0006\u0002\bFJ\u0017\u0010G\u001a\b\u0012\u0004\u0012\u00020I0HH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010BJ\u0011\u0010J\u001a\u00020KH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010BJ\u0011\u0010L\u001a\u00020MH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010BJ)\u0010N\u001a\u001a\u0012\u0004\u0012\u00020\u0017\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020?0O0OH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010BJ\u0013\u0010P\u001a\u0004\u0018\u00010QH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010BJ\u0011\u0010R\u001a\u00020'H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010BJ\b\u0010S\u001a\u00020*H\u0002J\b\u0010T\u001a\u00020*H\u0002J\b\u0010U\u001a\u00020*H\u0017J\u0010\u0010V\u001a\u00020*2\u0006\u0010W\u001a\u00020XH\u0016J\u0012\u0010Y\u001a\u00020*2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0014J\b\u0010\\\u001a\u00020*H\u0014J\u0012\u0010]\u001a\u00020*2\b\u0010^\u001a\u0004\u0018\u00010\u000bH\u0014J\u0010\u0010_\u001a\u00020*2\u0006\u0010`\u001a\u00020[H\u0014J5\u0010a\u001a\u00020*2\u0006\u0010b\u001a\u00020\u00172\u0006\u0010c\u001a\u00020d2\n\b\u0002\u00101\u001a\u0004\u0018\u00010 2\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u0017H\u0002¢\u0006\u0002\u0010eJC\u0010f\u001a\u00020*2\u0006\u0010g\u001a\u00020\u00172\b\u0010h\u001a\u0004\u0018\u00010i2\b\u00101\u001a\u0004\u0018\u00010 2\b\u0010j\u001a\u0004\u0018\u00010\u00172\u0006\u0010k\u001a\u00020\u00192\u0006\u0010l\u001a\u00020\u0019H\u0014¢\u0006\u0002\u0010mJ\b\u0010n\u001a\u00020*H\u0002JK\u0010o\u001a\u00020*2\u0006\u0010p\u001a\u00020\u00192\n\b\u0002\u00101\u001a\u0004\u0018\u00010 2\n\b\u0003\u0010q\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010r\u001a\u0004\u0018\u00010\u00172\u0010\b\u0002\u0010s\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010tH\u0002¢\u0006\u0002\u0010uJK\u0010v\u001a\u00020*2\u0006\u0010p\u001a\u00020\u00192\n\b\u0002\u00101\u001a\u0004\u0018\u00010 2\n\b\u0003\u0010q\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010r\u001a\u0004\u0018\u00010\u00172\u0010\b\u0002\u0010s\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010tH\u0002¢\u0006\u0002\u0010uJ4\u0010w\u001a\b\u0012\u0004\u0012\u00020y0x2\u0006\u0010.\u001a\u00020/2\b\b\u0002\u0010z\u001a\u00020\u0019H\u0082@ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b{\u0010|J,\u0010}\u001a\b\u0012\u0004\u0012\u00020*0x2\u0006\u0010~\u001a\u00020\u007fH\u0082@ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000ø\u0001\u0000¢\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001J-\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020*0x2\u0006\u00106\u001a\u000207H\u0082@ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000ø\u0001\u0000¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J-\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020*0x2\u0006\u00109\u001a\u00020:H\u0082@ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000ø\u0001\u0000¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J\u0012\u0010\u0088\u0001\u001a\u00020*H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010BJ\u0013\u0010\u0089\u0001\u001a\u00020*2\b\u0010\u008a\u0001\u001a\u00030\u008b\u0001H\u0002J\u0013\u0010\u008c\u0001\u001a\u00020*2\b\u0010\u008d\u0001\u001a\u00030\u008e\u0001H\u0002J\u0013\u0010\u008f\u0001\u001a\u00020*2\b\u0010\u0090\u0001\u001a\u00030\u0091\u0001H\u0002J\u0013\u0010\u0092\u0001\u001a\u00020*2\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001H\u0002J\u0013\u0010\u0095\u0001\u001a\u00020*2\b\u0010\u0096\u0001\u001a\u00030\u0097\u0001H\u0002J\u0012\u0010\u0098\u0001\u001a\u00020*H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010BJ\u0013\u0010\u0099\u0001\u001a\u00020*2\b\u0010\u009a\u0001\u001a\u00030\u009b\u0001H\u0002J\u0012\u0010\u009c\u0001\u001a\u00020*H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010BJD\u0010\u009d\u0001\u001a\u00020*2\u0007\u0010\u009e\u0001\u001a\u00020\u00192\u000b\b\u0002\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u00192\t\b\u0002\u0010 \u0001\u001a\u00020\u00192\u000f\b\u0002\u0010¡\u0001\u001a\b\u0012\u0004\u0012\u00020*0tH\u0000¢\u0006\u0006\b¢\u0001\u0010£\u0001J\t\u0010¤\u0001\u001a\u00020*H\u0002J\u0013\u0010¥\u0001\u001a\u00020\u00192\b\u0010¦\u0001\u001a\u00030§\u0001H\u0002J\u0013\u0010¨\u0001\u001a\u00020\u00192\b\u0010¦\u0001\u001a\u00030§\u0001H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\u0019X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006©\u0001"}, d2 = {"Lco/hyperverge/hyperkyc/ui/HKMainActivity;", "Lco/hyperverge/hyperkyc/ui/BaseActivity;", "()V", "binding", "Lco/hyperverge/hyperkyc/databinding/HkActivityMainBinding;", "getBinding$hyperkyc_release", "()Lco/hyperverge/hyperkyc/databinding/HkActivityMainBinding;", "binding$delegate", "Lkotlin/Lazy;", "browserLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "formWebViewDriver", "Lco/hyperverge/hyperkyc/utils/FormWebViewDriver;", "getFormWebViewDriver$hyperkyc_release", "()Lco/hyperverge/hyperkyc/utils/FormWebViewDriver;", "setFormWebViewDriver$hyperkyc_release", "(Lco/hyperverge/hyperkyc/utils/FormWebViewDriver;)V", "gson", "Lcom/google/gson/Gson;", "getGson", "()Lcom/google/gson/Gson;", "hvFacePreviewContentDescription", "", "ignoreBackPress", "", "getIgnoreBackPress$hyperkyc_release", "()Z", "setIgnoreBackPress$hyperkyc_release", "(Z)V", "isAppInBackground", "jsonErrorRetryCount", "", "loadingUIStateCollectJob", "Lkotlinx/coroutines/Job;", "openActivityCount", TypedValues.AttributesType.S_TARGET, "Lcom/squareup/picasso/Target;", "uiColorConfigData", "Lco/hyperverge/hyperkyc/core/hv/models/HSUIConfig;", "useWebForm", "customiseRetryButton", "", "flowForwardOrFinish", "getNetworkErrorMessage", "handleAPIModuleFailure", "apiFlowUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$ApiCall;", "handleCustomApiExceptions", "errorCode", "errorMsg", "isNetworkError", "(Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$ApiCall;Ljava/lang/Integer;Ljava/lang/String;Z)V", "handleDocModuleFailure", "docFlowUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$DocCapture;", "handleFaceModuleFailure", "faceFlowUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$FaceCapture;", "handleModuleFailure", "retryAction", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function1;)V", "initHyperSnap", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initViews", "loadBackground", "url", "loadBackground$hyperkyc_release", "loadCountries", "", "Lco/hyperverge/hyperkyc/data/models/KycCountry;", "loadDefaultRemoteConfig", "Ljava/io/Serializable;", "loadRemoteConfig", "Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig;", "loadTextConfigs", "", "loadTransactionState", "Lco/hyperverge/hyperkyc/data/models/state/TransactionStateResponse;", "loadUIConfigs", "observeFinishWithResultState", "observeUiState", "onBackPressed", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNewIntent", SDKConstants.PARAM_INTENT, "onSaveInstanceState", "outState", "processHVBridgeError", "method", "t", "", "(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/Integer;Ljava/lang/String;)V", "retryFT", "status", "data", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;", "errorMessage", "performReviewFinish", "performStatePush", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;Ljava/lang/Integer;Ljava/lang/String;ZZ)V", "setupActivityCallbacks", "showJsonExceptionRetry", "show", "retryTitleRes", "retryError", "onRetry", "Lkotlin/Function0;", "(ZLjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lkotlin/jvm/functions/Function0;)V", "showRetry", "startApiFlow", "Lkotlin/Result;", "Lokhttp3/Response;", "isRetry", "startApiFlow-0E7RQCE", "(Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$ApiCall;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startBarcodeFlow", "barcodeFlowUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$BarcodeCapture;", "startBarcodeFlow-gIAlu-s", "(Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$BarcodeCapture;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startDocFlow", "startDocFlow-gIAlu-s", "(Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$DocCapture;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startFaceFlow", "startFaceFlow-gIAlu-s", "(Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$FaceCapture;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startFlow", "startNFCFlow", "nfcFlowUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$NFCReader;", "startSessionFlow", "startSessionUiState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$StartSessionRecording;", "startVideoStatementFlow", "videoStatementUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$VideoStatement;", "startVideoStatementV2Flow", "videoStatementV2UIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$VideoStatementV2;", "startWebViewFlow", "webViewUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$WebView;", "startWorkflow", "stopSessionFlow", "stopSessionFlowUiState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$StopSessionRecording;", "storeUIConfig", "updateEndState", "isLoading", "isSuccess", "isAPISuccess", "onFailAction", "updateEndState$hyperkyc_release", "(ZLjava/lang/Boolean;ZLkotlin/jvm/functions/Function0;)V", "updateStepTitle", "validateInputs", HVRetakeActivity.CONFIG_TAG, "Lco/hyperverge/hyperkyc/data/models/WorkflowConfig;", "validateSdkVersion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HKMainActivity extends BaseActivity {
    private ActivityResultLauncher<Intent> browserLauncher;
    public FormWebViewDriver formWebViewDriver;
    private boolean ignoreBackPress;
    private boolean isAppInBackground;
    private int jsonErrorRetryCount;
    private Job loadingUIStateCollectJob;
    private Target target;
    private HSUIConfig uiColorConfigData;
    private boolean useWebForm;
    private String hvFacePreviewContentDescription = "hvFacePreview";

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final Lazy binding = LazyKt.lazy(new Function0<HkActivityMainBinding>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$binding$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final HkActivityMainBinding invoke() {
            return HkActivityMainBinding.inflate(HKMainActivity.this.getLayoutInflater());
        }
    });
    private int openActivityCount = 1;

    private final void updateStepTitle() {
    }

    public HKMainActivity() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$$ExternalSyntheticLambda5
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                HKMainActivity.browserLauncher$lambda$0(HKMainActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…        }\n        }\n    }");
        this.browserLauncher = registerForActivityResult;
    }

    public final HkActivityMainBinding getBinding$hyperkyc_release() {
        return (HkActivityMainBinding) this.binding.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Gson getGson() {
        return getMainVM().getGson();
    }

    public final FormWebViewDriver getFormWebViewDriver$hyperkyc_release() {
        FormWebViewDriver formWebViewDriver = this.formWebViewDriver;
        if (formWebViewDriver != null) {
            return formWebViewDriver;
        }
        Intrinsics.throwUninitializedPropertyAccessException("formWebViewDriver");
        return null;
    }

    public final void setFormWebViewDriver$hyperkyc_release(FormWebViewDriver formWebViewDriver) {
        Intrinsics.checkNotNullParameter(formWebViewDriver, "<set-?>");
        this.formWebViewDriver = formWebViewDriver;
    }

    /* renamed from: getIgnoreBackPress$hyperkyc_release, reason: from getter */
    public final boolean getIgnoreBackPress() {
        return this.ignoreBackPress;
    }

    public final void setIgnoreBackPress$hyperkyc_release(boolean z) {
        this.ignoreBackPress = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(20:21|(1:23)(1:193)|24|(1:26)(2:189|(1:191)(1:192))|27|28|(2:(1:188)(1:185)|(1:187))|34|(1:36)|37|(1:41)|42|(1:44)|45|(1:47)(9:139|140|141|142|143|144|(1:146)|147|(6:149|(9:151|(2:(1:175)(1:172)|(1:174))|157|(1:159)|160|(1:164)|165|(1:167)|168)|49|50|51|(18:53|(2:(1:130)(1:126)|(1:128)(1:129))|59|(1:61)|62|(1:66)|67|(1:69)|70|(1:72)(1:122)|(1:74)(1:121)|75|76|77|78|(1:80)|81|(2:83|(15:85|(1:117)(1:89)|(1:115)(1:94)|(11:96|97|(1:99)|100|(1:104)|105|(1:107)|108|(1:110)(1:114)|(1:112)|113)|116|97|(0)|100|(2:102|104)|105|(0)|108|(0)(0)|(0)|113)))))|48|49|50|51|(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0282, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x0283, code lost:
    
        r5 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x03d6, code lost:
    
        if (r0 != null) goto L178;
     */
    /* JADX WARN: Removed duplicated region for block: B:107:0x043e  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x044a  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x044f  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x048f  */
    /* JADX WARN: Removed duplicated region for block: B:135:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x046f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x03ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void browserLauncher$lambda$0(HKMainActivity this$0, ActivityResult activityResult) {
        String stringExtra;
        String str;
        HyperKycData.WebviewData webviewData;
        File hsExceptionFile;
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        String str2;
        CharSequence charSequence;
        String canonicalName2;
        Class<?> cls2;
        String className;
        Throwable m1205exceptionOrNullimpl;
        String str3;
        Class<?> cls3;
        String str4;
        Object m1202constructorimpl2;
        String str5;
        String str6;
        String str7;
        Matcher matcher;
        String str8;
        String localizedMessage;
        Class<?> cls4;
        String className2;
        String className3;
        String className4;
        Intent data;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int resultCode = activityResult.getResultCode();
        if (resultCode != -1) {
            if (resultCode == 0) {
                if (this$0.getMainVM().getHsStateHandler().getIsActivityRecreated()) {
                    return;
                }
                this$0.onBackPressed();
                return;
            } else if (resultCode != 2) {
                return;
            }
        }
        Intent data2 = activityResult.getData();
        String stringExtra2 = data2 != null ? data2.getStringExtra("moduleId") : null;
        int resultCode2 = activityResult.getResultCode();
        if (resultCode2 == -1) {
            Intent data3 = activityResult.getData();
            if (data3 != null) {
                stringExtra = data3.getStringExtra("response");
                HyperKycData.WebviewData webviewData2 = new HyperKycData.WebviewData(stringExtra);
                HSStateHandler hsStateHandler = this$0.getMainVM().getHsStateHandler();
                if (this$0.getMainVM().getHsStateHandler().getIsActivityRecreated()) {
                }
                MainVM.updateWebviewData$hyperkyc_release$default(this$0.getMainVM(), str, webviewData, false, 4, null);
                if (this$0.getMainVM().getHsStateHandler().getIsActivityRecreated()) {
                }
            }
            stringExtra = null;
            HyperKycData.WebviewData webviewData22 = new HyperKycData.WebviewData(stringExtra);
            HSStateHandler hsStateHandler2 = this$0.getMainVM().getHsStateHandler();
            if (this$0.getMainVM().getHsStateHandler().getIsActivityRecreated()) {
            }
            MainVM.updateWebviewData$hyperkyc_release$default(this$0.getMainVM(), str, webviewData, false, 4, null);
            if (this$0.getMainVM().getHsStateHandler().getIsActivityRecreated()) {
            }
        } else {
            if (resultCode2 == 2 && (data = activityResult.getData()) != null) {
                stringExtra = data.getStringExtra("error");
                HyperKycData.WebviewData webviewData222 = new HyperKycData.WebviewData(stringExtra);
                HSStateHandler hsStateHandler22 = this$0.getMainVM().getHsStateHandler();
                if (this$0.getMainVM().getHsStateHandler().getIsActivityRecreated()) {
                    str = stringExtra2;
                    webviewData = webviewData222;
                } else {
                    boolean z = webviewData222 instanceof HSBridgeException;
                    String jsonData = hsStateHandler22.getGson().toJson(webviewData222, z ? HSBridgeException.class : HyperKycData.WebviewData.class);
                    if (z) {
                        hsExceptionFile = hsStateHandler22.getHsBridgeExceptionFile();
                    } else {
                        hsExceptionFile = webviewData222 instanceof Throwable ? hsStateHandler22.getHsExceptionFile() : hsStateHandler22.getHsResponseFile();
                    }
                    File file = hsExceptionFile;
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        canonicalName = (hsStateHandler22 == null || (cls = hsStateHandler22.getClass()) == null) ? null : cls.getCanonicalName();
                        if (canonicalName == null) {
                            canonicalName = "N/A";
                        }
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
                    String str9 = "";
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
                    String str10 = "storeState() called for " + HyperKycData.WebviewData.class.getCanonicalName();
                    if (str10 == null) {
                        str10 = "null ";
                    }
                    sb.append(str10);
                    sb.append(' ');
                    sb.append("");
                    companion.log(level, sb.toString());
                    if (CoreExtsKt.isRelease()) {
                        str = stringExtra2;
                        webviewData = webviewData222;
                    } else {
                        try {
                            Result.Companion companion2 = Result.INSTANCE;
                            str = stringExtra2;
                            webviewData = webviewData222;
                            try {
                                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                            } catch (Throwable th) {
                                th = th;
                                Result.Companion companion3 = Result.INSTANCE;
                                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                                }
                                String packageName = (String) m1202constructorimpl;
                                if (CoreExtsKt.isDebug()) {
                                }
                                str2 = "packageName";
                                charSequence = "co.hyperverge";
                                Result.Companion companion4 = Result.INSTANCE;
                                Intrinsics.checkNotNullExpressionValue(jsonData, "jsonData");
                                byte[] bytes = jsonData.getBytes(Charsets.UTF_8);
                                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                                FilesKt.writeBytes(file, bytes);
                                Object m1202constructorimpl3 = Result.m1202constructorimpl(Unit.INSTANCE);
                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                                if (m1205exceptionOrNullimpl != null) {
                                }
                                MainVM.updateWebviewData$hyperkyc_release$default(this$0.getMainVM(), str, webviewData, false, 4, null);
                                if (this$0.getMainVM().getHsStateHandler().getIsActivityRecreated()) {
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            str = stringExtra2;
                            webviewData = webviewData222;
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                            m1202constructorimpl = "";
                        }
                        String packageName2 = (String) m1202constructorimpl;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                            str2 = "packageName";
                            charSequence = "co.hyperverge";
                            if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    canonicalName2 = (hsStateHandler22 == null || (cls2 = hsStateHandler22.getClass()) == null) ? null : cls2.getCanonicalName();
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
                                String str11 = "storeState() called for " + HyperKycData.WebviewData.class.getCanonicalName();
                                if (str11 == null) {
                                    str11 = "null ";
                                }
                                sb2.append(str11);
                                sb2.append(' ');
                                sb2.append("");
                                Log.println(3, canonicalName2, sb2.toString());
                            }
                            Result.Companion companion42 = Result.INSTANCE;
                            Intrinsics.checkNotNullExpressionValue(jsonData, "jsonData");
                            byte[] bytes2 = jsonData.getBytes(Charsets.UTF_8);
                            Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
                            FilesKt.writeBytes(file, bytes2);
                            Object m1202constructorimpl32 = Result.m1202constructorimpl(Unit.INSTANCE);
                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl32);
                            if (m1205exceptionOrNullimpl != null) {
                                HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                                HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                                StringBuilder sb3 = new StringBuilder();
                                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                                if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    String canonicalName3 = (hsStateHandler22 == null || (cls3 = hsStateHandler22.getClass()) == null) ? null : cls3.getCanonicalName();
                                    str3 = canonicalName3 == null ? "N/A" : canonicalName3;
                                }
                                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                                if (matcher4.find()) {
                                    str3 = matcher4.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                                }
                                if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str3 = str3.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                sb3.append(str3);
                                sb3.append(" - ");
                                String str12 = "failed saving " + HyperKycData.WebviewData.class.getCanonicalName() + " state json to file";
                                if (str12 == null) {
                                    str12 = "null ";
                                }
                                sb3.append(str12);
                                sb3.append(' ');
                                String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                                if (localizedMessage2 != null) {
                                    str4 = '\n' + localizedMessage2;
                                } else {
                                    str4 = "";
                                }
                                sb3.append(str4);
                                companion5.log(level2, sb3.toString());
                                CoreExtsKt.isRelease();
                                try {
                                    Result.Companion companion6 = Result.INSTANCE;
                                    Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                    Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                                    m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                } catch (Throwable th3) {
                                    Result.Companion companion7 = Result.INSTANCE;
                                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                                }
                                if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                    m1202constructorimpl2 = "";
                                }
                                String str13 = (String) m1202constructorimpl2;
                                if (CoreExtsKt.isDebug()) {
                                    Intrinsics.checkNotNullExpressionValue(str13, str2);
                                    if (StringsKt.contains$default((CharSequence) str13, charSequence, false, 2, (Object) null)) {
                                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                        Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                        if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                            str5 = null;
                                        } else {
                                            str5 = null;
                                            str6 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        }
                                        str6 = (hsStateHandler22 == null || (cls4 = hsStateHandler22.getClass()) == null) ? str5 : cls4.getCanonicalName();
                                        if (str6 == null) {
                                            str7 = "N/A";
                                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                                            if (matcher.find()) {
                                                str7 = matcher.replaceAll("");
                                                Intrinsics.checkNotNullExpressionValue(str7, "replaceAll(\"\")");
                                            }
                                            if (str7.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                str7 = str7.substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                                            }
                                            StringBuilder sb4 = new StringBuilder();
                                            str8 = "failed saving " + HyperKycData.WebviewData.class.getCanonicalName() + " state json to file";
                                            if (str8 == null) {
                                                str8 = "null ";
                                            }
                                            sb4.append(str8);
                                            sb4.append(' ');
                                            localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str5;
                                            if (localizedMessage != null) {
                                                str9 = '\n' + localizedMessage;
                                            }
                                            sb4.append(str9);
                                            Log.println(6, str7, sb4.toString());
                                        }
                                        str7 = str6;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                                        if (matcher.find()) {
                                        }
                                        if (str7.length() > 23) {
                                            str7 = str7.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb42 = new StringBuilder();
                                        str8 = "failed saving " + HyperKycData.WebviewData.class.getCanonicalName() + " state json to file";
                                        if (str8 == null) {
                                        }
                                        sb42.append(str8);
                                        sb42.append(' ');
                                        if (m1205exceptionOrNullimpl == null) {
                                        }
                                        if (localizedMessage != null) {
                                        }
                                        sb42.append(str9);
                                        Log.println(6, str7, sb42.toString());
                                    }
                                }
                            }
                        }
                    }
                    str2 = "packageName";
                    charSequence = "co.hyperverge";
                    Result.Companion companion422 = Result.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(jsonData, "jsonData");
                    byte[] bytes22 = jsonData.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes22, "this as java.lang.String).getBytes(charset)");
                    FilesKt.writeBytes(file, bytes22);
                    Object m1202constructorimpl322 = Result.m1202constructorimpl(Unit.INSTANCE);
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl322);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                }
                MainVM.updateWebviewData$hyperkyc_release$default(this$0.getMainVM(), str, webviewData, false, 4, null);
                if (this$0.getMainVM().getHsStateHandler().getIsActivityRecreated()) {
                    return;
                }
                this$0.getMainVM().flowForward();
                return;
            }
            stringExtra = null;
            HyperKycData.WebviewData webviewData2222 = new HyperKycData.WebviewData(stringExtra);
            HSStateHandler hsStateHandler222 = this$0.getMainVM().getHsStateHandler();
            if (this$0.getMainVM().getHsStateHandler().getIsActivityRecreated()) {
            }
            MainVM.updateWebviewData$hyperkyc_release$default(this$0.getMainVM(), str, webviewData, false, 4, null);
            if (this$0.getMainVM().getHsStateHandler().getIsActivityRecreated()) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        if (!getMainVM().isAtFirstModule$hyperkyc_release()) {
            MainVM.saveStateLocally$hyperkyc_release$default(getMainVM(), true, null, false, 6, null);
        }
        super.onSaveInstanceState(outState);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0140, code lost:
    
        if (r0 == null) goto L55;
     */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onCreate(Bundle savedInstanceState) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        super.onCreate(savedInstanceState);
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
        String str2 = "onCreate() called with: savedInstanceState = " + savedInstanceState;
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
                    String str3 = "onCreate() called with: savedInstanceState = " + savedInstanceState;
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
        setContentView(getBinding$hyperkyc_release().getRoot());
        getWindow().getDecorView().getRootView().setTag(this.hvFacePreviewContentDescription);
        getMainVM().setJourneyId$hyperkyc_release(getJourneyId());
        getMainVM().setHyperKycConfig$hyperkyc_release(getHyperKycConfig());
        setupActivityCallbacks();
        Properties properties = getHyperKycConfig().getWorkflowConfig$hyperkyc_release().getProperties();
        this.useWebForm = properties != null ? properties.getUseWebForm() : false;
        if (savedInstanceState != null) {
            getMainVM().getHsStateHandler().setActivityRecreated(true);
            if (this.useWebForm && getHyperKycConfig().getWorkflowConfigJson() != null && FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().getWorkflowConfig$hyperkyc_release() == null) {
                FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().setWorkflowConfig$hyperkyc_release(getHyperKycConfig().getWorkflowConfigJson());
            }
        }
        HKMainActivity hKMainActivity = this;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(hKMainActivity), Dispatchers.getIO(), null, new HKMainActivity$onCreate$2(this, null), 2, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(hKMainActivity), null, null, new HKMainActivity$onCreate$3(this, null), 3, null);
        initViews();
        if (this.useWebForm) {
            return;
        }
        observeUiState();
        observeFinishWithResultState();
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x015e, code lost:
    
        if (r0 == null) goto L62;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void setupActivityCallbacks() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        HKMainActivity hKMainActivity = this;
        ActivityExtsKt.unregisterActivityCallbacks(hKMainActivity);
        Properties properties = getHyperKycConfig().getWorkflowConfig$hyperkyc_release().getProperties();
        if (properties != null && properties.getSecure()) {
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
            String str2 = "setupSecureCheck() setting secure flag for " + getLocalClassName();
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
                        String str3 = "setupSecureCheck() setting secure flag for " + getLocalClassName();
                        sb2.append(str3 != null ? str3 : "null ");
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str, sb2.toString());
                    }
                }
            }
            ActivityExtsKt.makeSecure$default(this, false, 1, null);
        }
        ActivityExtsKt.registerActivityCallbacks(hKMainActivity, new Function1<Activity, Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$setupActivityCallbacks$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Activity activity) {
                invoke2(activity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Activity it) {
                String canonicalName3;
                Class<?> cls3;
                Object m1202constructorimpl2;
                Class<?> cls4;
                String className3;
                String substringAfterLast$default;
                String className4;
                Intrinsics.checkNotNullParameter(it, "it");
                Properties properties2 = HKMainActivity.this.getHyperKycConfig().getWorkflowConfig$hyperkyc_release().getProperties();
                if (properties2 != null && properties2.getSecure()) {
                    HKMainActivity hKMainActivity2 = HKMainActivity.this;
                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                    HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    String str4 = "N/A";
                    if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        canonicalName3 = (hKMainActivity2 == null || (cls3 = hKMainActivity2.getClass()) == null) ? null : cls3.getCanonicalName();
                        if (canonicalName3 == null) {
                            canonicalName3 = "N/A";
                        }
                    }
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                    if (matcher3.find()) {
                        canonicalName3 = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                    }
                    if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName3 = canonicalName3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb3.append(canonicalName3);
                    sb3.append(" - ");
                    String str5 = "setupSecureCheck() setting secure flag for " + it.getLocalClassName();
                    if (str5 == null) {
                        str5 = "null ";
                    }
                    sb3.append(str5);
                    sb3.append(' ');
                    sb3.append("");
                    companion4.log(level2, sb3.toString());
                    if (!CoreExtsKt.isRelease()) {
                        try {
                            Result.Companion companion5 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th2) {
                            Result.Companion companion6 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                            m1202constructorimpl2 = "";
                        }
                        String packageName2 = (String) m1202constructorimpl2;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    String canonicalName4 = (hKMainActivity2 == null || (cls4 = hKMainActivity2.getClass()) == null) ? null : cls4.getCanonicalName();
                                    if (canonicalName4 != null) {
                                        str4 = canonicalName4;
                                    }
                                } else {
                                    str4 = substringAfterLast$default;
                                }
                                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                                if (matcher4.find()) {
                                    str4 = matcher4.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                                }
                                if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str4 = str4.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb4 = new StringBuilder();
                                String str6 = "setupSecureCheck() setting secure flag for " + it.getLocalClassName();
                                sb4.append(str6 != null ? str6 : "null ");
                                sb4.append(' ');
                                sb4.append("");
                                Log.println(3, str4, sb4.toString());
                            }
                        }
                    }
                    ActivityExtsKt.makeSecure$default(it, false, 1, null);
                }
            }
        }, new Function1<Activity, Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$setupActivityCallbacks$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Activity activity) {
                invoke2(activity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Activity it) {
                int i;
                int i2;
                boolean z;
                Map map;
                Intrinsics.checkNotNullParameter(it, "it");
                i = HKMainActivity.this.openActivityCount;
                if (i == 0) {
                    z = HKMainActivity.this.isAppInBackground;
                    if (z) {
                        HKMainActivity.this.isAppInBackground = false;
                        AnalyticsLogger analyticsLogger = AnalyticsLogger.INSTANCE;
                        map = HKMainActivity.setupActivityCallbacks$getEventProps(HKMainActivity.this, AnalyticsLogger.Events.APP_FOREGROUND);
                        analyticsLogger.logAppForegroundEvent$hyperkyc_release(map);
                    }
                }
                HKMainActivity hKMainActivity2 = HKMainActivity.this;
                i2 = hKMainActivity2.openActivityCount;
                hKMainActivity2.openActivityCount = i2 + 1;
            }
        }, new Function1<Activity, Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$setupActivityCallbacks$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Activity activity) {
                invoke2(activity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Activity it) {
                int i;
                int i2;
                Map map;
                Intrinsics.checkNotNullParameter(it, "it");
                HKMainActivity hKMainActivity2 = HKMainActivity.this;
                i = hKMainActivity2.openActivityCount;
                hKMainActivity2.openActivityCount = i - 1;
                i2 = HKMainActivity.this.openActivityCount;
                if (i2 == 0) {
                    HKMainActivity.this.isAppInBackground = true;
                    AnalyticsLogger analyticsLogger = AnalyticsLogger.INSTANCE;
                    map = HKMainActivity.setupActivityCallbacks$getEventProps(HKMainActivity.this, AnalyticsLogger.Events.APP_BACKGROUND);
                    analyticsLogger.logAppBackgroundEvent$hyperkyc_release(map);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0181, code lost:
    
        if (r0 != null) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0193, code lost:
    
        if (r0 == null) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0197, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x01a6, code lost:
    
        if (r0.find() == false) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01a8, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01b5, code lost:
    
        if (r8.length() <= 23) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01bb, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01be, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01c5, code lost:
    
        r0 = new java.lang.StringBuilder();
        r3 = "getEventProps " + r18.getMainVM().hashCode() + ": failed getting analytics properties";
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01e4, code lost:
    
        if (r3 != null) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01e6, code lost:
    
        r3 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01e8, code lost:
    
        r0.append(r3);
        r0.append(' ');
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01f0, code lost:
    
        if (r2 == null) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01f2, code lost:
    
        r11 = r2.getLocalizedMessage();
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01f8, code lost:
    
        if (r11 == null) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01fa, code lost:
    
        r15 = '\n' + r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0209, code lost:
    
        r0.append(r15);
        android.util.Log.println(6, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x01f7, code lost:
    
        r11 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0196, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Map<String, Object> setupActivityCallbacks$getEventProps(HKMainActivity hKMainActivity, String str) {
        Object m1202constructorimpl;
        String canonicalName;
        Class<?> cls;
        String str2;
        Object m1202constructorimpl2;
        String str3;
        String str4;
        Class<?> cls2;
        String className;
        String className2;
        try {
            Result.Companion companion = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(hKMainActivity.getMainVM().getAnalyticsForModule$hyperkyc_release(hKMainActivity.getMainVM().getCurrentModuleId$hyperkyc_release(), str));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        Object obj = m1202constructorimpl;
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
        if (m1205exceptionOrNullimpl != null) {
            HyperLogger.Level level = HyperLogger.Level.ERROR;
            HyperLogger companion3 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str5 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                canonicalName = (hKMainActivity == null || (cls = hKMainActivity.getClass()) == null) ? null : cls.getCanonicalName();
                if (canonicalName == null) {
                    canonicalName = "N/A";
                }
            }
            Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
            String str6 = "";
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
            String str7 = "getEventProps " + hKMainActivity.getMainVM().hashCode() + ": failed getting analytics properties";
            if (str7 == null) {
                str7 = "null ";
            }
            sb.append(str7);
            sb.append(' ');
            String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
            if (localizedMessage != null) {
                str2 = '\n' + localizedMessage;
            } else {
                str2 = "";
            }
            sb.append(str2);
            companion3.log(level, sb.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion4 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th2) {
                Result.Companion companion5 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                m1202constructorimpl2 = "";
            }
            String packageName = (String) m1202constructorimpl2;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                        str3 = null;
                    } else {
                        str3 = null;
                        str4 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    str4 = (hKMainActivity == null || (cls2 = hKMainActivity.getClass()) == null) ? str3 : cls2.getCanonicalName();
                }
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (Result.m1208isFailureimpl(obj)) {
            obj = linkedHashMap;
        }
        return (Map) obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (this.formWebViewDriver != null) {
            getFormWebViewDriver$hyperkyc_release().destroyWebView$hyperkyc_release();
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0179, code lost:
    
        if (r0 != null) goto L55;
     */
    @Override // co.hyperverge.hyperkyc.ui.BaseActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void retryFT(final String status, final HyperKycData data, final Integer errorCode, final String errorMessage, final boolean performReviewFinish, final boolean performStatePush) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(status, "status");
        super.retryFT(status, data, errorCode, errorMessage, performReviewFinish, performStatePush);
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
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
        String str3 = "retryFT() called with: status = " + status + ", data = " + data + ", errorCode = " + errorCode + ", errorMessage = " + errorMessage + ", performReviewFinish = " + performReviewFinish + ", performStatePush = " + performStatePush;
        if (str3 == null) {
            str3 = "null ";
        }
        sb.append(str3);
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
                    if (str2 == null) {
                        str2 = "N/A";
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher2.find()) {
                        str2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str4 = "retryFT() called with: status = " + status + ", data = " + data + ", errorCode = " + errorCode + ", errorMessage = " + errorMessage + ", performReviewFinish = " + performReviewFinish + ", performStatePush = " + performStatePush;
                    if (str4 == null) {
                        str4 = "null ";
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str2, sb2.toString());
                    FrameLayout frameLayout = getBinding$hyperkyc_release().flContent;
                    Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.flContent");
                    frameLayout.setVisibility(8);
                    showRetry$default(this, true, 111, null, getString(R.string.hk_api_error_message), new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$retryFT$2
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            if (ContextExtsKt.isOnline(HKMainActivity.this)) {
                                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(HKMainActivity.this), null, null, new AnonymousClass1(HKMainActivity.this, status, data, errorCode, errorMessage, performReviewFinish, performStatePush, null), 3, null);
                                HKMainActivity.showRetry$default(HKMainActivity.this, false, null, null, null, null, 30, null);
                                FrameLayout frameLayout2 = HKMainActivity.this.getBinding$hyperkyc_release().flContent;
                                Intrinsics.checkNotNullExpressionValue(frameLayout2, "binding.flContent");
                                frameLayout2.setVisibility(0);
                                HKMainActivity.this.setIgnoreBackPress$hyperkyc_release(false);
                            }
                        }

                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* compiled from: HKMainActivity.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                        @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$retryFT$2$1", f = "HKMainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                        /* renamed from: co.hyperverge.hyperkyc.ui.HKMainActivity$retryFT$2$1, reason: invalid class name */
                        /* loaded from: classes2.dex */
                        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ HyperKycData $data;
                            final /* synthetic */ Integer $errorCode;
                            final /* synthetic */ String $errorMessage;
                            final /* synthetic */ boolean $performReviewFinish;
                            final /* synthetic */ boolean $performStatePush;
                            final /* synthetic */ String $status;
                            int label;
                            final /* synthetic */ HKMainActivity this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass1(HKMainActivity hKMainActivity, String str, HyperKycData hyperKycData, Integer num, String str2, boolean z, boolean z2, Continuation<? super AnonymousClass1> continuation) {
                                super(2, continuation);
                                this.this$0 = hKMainActivity;
                                this.$status = str;
                                this.$data = hyperKycData;
                                this.$errorCode = num;
                                this.$errorMessage = str2;
                                this.$performReviewFinish = z;
                                this.$performStatePush = z2;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass1(this.this$0, this.$status, this.$data, this.$errorCode, this.$errorMessage, this.$performReviewFinish, this.$performStatePush, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                if (this.label != 0) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                                this.this$0.finishWithResult(this.$status, this.$data, this.$errorCode, this.$errorMessage, this.$performReviewFinish, this.$performStatePush);
                                return Unit.INSTANCE;
                            }
                        }
                    }, 4, null);
                }
            }
        }
        FrameLayout frameLayout2 = getBinding$hyperkyc_release().flContent;
        Intrinsics.checkNotNullExpressionValue(frameLayout2, "binding.flContent");
        frameLayout2.setVisibility(8);
        showRetry$default(this, true, 111, null, getString(R.string.hk_api_error_message), new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$retryFT$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                if (ContextExtsKt.isOnline(HKMainActivity.this)) {
                    BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(HKMainActivity.this), null, null, new AnonymousClass1(HKMainActivity.this, status, data, errorCode, errorMessage, performReviewFinish, performStatePush, null), 3, null);
                    HKMainActivity.showRetry$default(HKMainActivity.this, false, null, null, null, null, 30, null);
                    FrameLayout frameLayout22 = HKMainActivity.this.getBinding$hyperkyc_release().flContent;
                    Intrinsics.checkNotNullExpressionValue(frameLayout22, "binding.flContent");
                    frameLayout22.setVisibility(0);
                    HKMainActivity.this.setIgnoreBackPress$hyperkyc_release(false);
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: HKMainActivity.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$retryFT$2$1", f = "HKMainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: co.hyperverge.hyperkyc.ui.HKMainActivity$retryFT$2$1, reason: invalid class name */
            /* loaded from: classes2.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ HyperKycData $data;
                final /* synthetic */ Integer $errorCode;
                final /* synthetic */ String $errorMessage;
                final /* synthetic */ boolean $performReviewFinish;
                final /* synthetic */ boolean $performStatePush;
                final /* synthetic */ String $status;
                int label;
                final /* synthetic */ HKMainActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(HKMainActivity hKMainActivity, String str, HyperKycData hyperKycData, Integer num, String str2, boolean z, boolean z2, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = hKMainActivity;
                    this.$status = str;
                    this.$data = hyperKycData;
                    this.$errorCode = num;
                    this.$errorMessage = str2;
                    this.$performReviewFinish = z;
                    this.$performStatePush = z2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.this$0, this.$status, this.$data, this.$errorCode, this.$errorMessage, this.$performReviewFinish, this.$performStatePush, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    this.this$0.finishWithResult(this.$status, this.$data, this.$errorCode, this.$errorMessage, this.$performReviewFinish, this.$performStatePush);
                    return Unit.INSTANCE;
                }
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0172, code lost:
    
        if (r6 != null) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0182, code lost:
    
        if (r6 == null) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0186, code lost:
    
        r0.element = r11;
        r6 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher((java.lang.CharSequence) r0.element);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0198, code lost:
    
        if (r6.find() == false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x019a, code lost:
    
        r6 = r6.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, "replaceAll(\"\")");
        r0.element = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01ab, code lost:
    
        if (((java.lang.String) r0.element).length() <= 23) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x01af, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x01b2, code lost:
    
        r0 = ((java.lang.String) r0.element).substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01c2, code lost:
    
        android.util.Log.println(3, r0, "initHyperSnap() called ");
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01be, code lost:
    
        r0 = (java.lang.String) r0.element;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0185, code lost:
    
        r11 = r6;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2, types: [T] */
    /* JADX WARN: Type inference failed for: r6v17, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v10, types: [T] */
    /* JADX WARN: Type inference failed for: r9v20, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v22, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object initHyperSnap(Continuation<? super Unit> continuation) {
        HKMainActivity$initHyperSnap$1 hKMainActivity$initHyperSnap$1;
        int i;
        ?? canonicalName;
        String str;
        Object m1202constructorimpl;
        String str2;
        String str3;
        String className;
        boolean isInitialised;
        HKMainActivity hKMainActivity;
        String className2;
        HVError hVError;
        if (continuation instanceof HKMainActivity$initHyperSnap$1) {
            hKMainActivity$initHyperSnap$1 = (HKMainActivity$initHyperSnap$1) continuation;
            if ((hKMainActivity$initHyperSnap$1.label & Integer.MIN_VALUE) != 0) {
                hKMainActivity$initHyperSnap$1.label -= Integer.MIN_VALUE;
                Object obj = hKMainActivity$initHyperSnap$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = hKMainActivity$initHyperSnap$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    ?? r11 = "N/A";
                    if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                        Class<?> cls = getClass();
                        canonicalName = cls != null ? cls.getCanonicalName() : 0;
                        if (canonicalName == 0) {
                            canonicalName = "N/A";
                        }
                    }
                    objectRef.element = canonicalName;
                    Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                    if (matcher.find()) {
                        ?? replaceAll = matcher.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                        objectRef.element = replaceAll;
                    }
                    if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str = (String) objectRef.element;
                    } else {
                        str = ((String) objectRef.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(str);
                    sb.append(" - ");
                    sb.append("initHyperSnap() called");
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
                                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                                    str2 = null;
                                } else {
                                    str2 = null;
                                    str3 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                }
                                Class<?> cls2 = getClass();
                                str3 = cls2 != null ? cls2.getCanonicalName() : str2;
                            }
                        }
                    }
                    isInitialised = HyperSnapSDK.isInitialised();
                    if (!isInitialised) {
                        Context applicationContext = getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                        HyperKycConfig hyperKycConfig = getHyperKycConfig();
                        boolean useLocation = getHyperKycConfig().getUseLocation();
                        hKMainActivity$initHyperSnap$1.L$0 = this;
                        hKMainActivity$initHyperSnap$1.label = 1;
                        obj = HyperSnapBridgeKt.initHyperSnapSDK(applicationContext, hyperKycConfig, useLocation, hKMainActivity$initHyperSnap$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        hKMainActivity = this;
                    } else {
                        HyperSnapBridgeKt.updateHyperSnapSDKConfig(getHyperKycConfig());
                        return Unit.INSTANCE;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    hKMainActivity = (HKMainActivity) hKMainActivity$initHyperSnap$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                hVError = (HVError) obj;
                if (hVError != null) {
                    BaseActivity.finishWithResult$default(hKMainActivity, "error", null, Boxing.boxInt(106), hVError.getErrorMessage(), false, false, 48, null);
                }
                return Unit.INSTANCE;
            }
        }
        hKMainActivity$initHyperSnap$1 = new HKMainActivity$initHyperSnap$1(this, continuation);
        Object obj2 = hKMainActivity$initHyperSnap$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = hKMainActivity$initHyperSnap$1.label;
        if (i != 0) {
        }
        hVError = (HVError) obj2;
        if (hVError != null) {
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object storeUIConfig(Continuation<? super Unit> continuation) {
        Object onIO$default = CoroutineExtsKt.onIO$default(null, new HKMainActivity$storeUIConfig$2(this, null), continuation, 1, null);
        return onIO$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? onIO$default : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$13$lambda$12(HKMainActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), null, null, new HKMainActivity$initViews$2$2$1(this$0, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void customiseRetryButton() {
        HyperSnapBridgeKt.getUiConfigUtil().customisePrimaryButton(getBinding$hyperkyc_release().btnRetry);
        getBinding$hyperkyc_release().btnRetry.setIcon(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object startWorkflow(Continuation<? super Unit> continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new HKMainActivity$startWorkflow$2(this, null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object startFlow(Continuation<? super Unit> continuation) {
        HKMainActivity$startFlow$1 hKMainActivity$startFlow$1;
        int i;
        HKMainActivity hKMainActivity;
        boolean booleanValue;
        if (continuation instanceof HKMainActivity$startFlow$1) {
            hKMainActivity$startFlow$1 = (HKMainActivity$startFlow$1) continuation;
            if ((hKMainActivity$startFlow$1.label & Integer.MIN_VALUE) != 0) {
                hKMainActivity$startFlow$1.label -= Integer.MIN_VALUE;
                Object obj = hKMainActivity$startFlow$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = hKMainActivity$startFlow$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    MainVM mainVM = getMainVM();
                    HyperKycConfig hyperKycConfig = getHyperKycConfig();
                    hKMainActivity$startFlow$1.L$0 = this;
                    hKMainActivity$startFlow$1.label = 1;
                    obj = mainVM.startWorkFlow(hyperKycConfig, hKMainActivity$startFlow$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    hKMainActivity = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    hKMainActivity = (HKMainActivity) hKMainActivity$startFlow$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                Pair pair = (Pair) obj;
                booleanValue = ((Boolean) pair.component1()).booleanValue();
                String str = (String) pair.component2();
                if (!booleanValue) {
                    BaseActivity.finishWithResult$default(hKMainActivity, "error", null, Boxing.boxInt(104), str, false, false, 50, null);
                }
                return Unit.INSTANCE;
            }
        }
        hKMainActivity$startFlow$1 = new HKMainActivity$startFlow$1(this, continuation);
        Object obj2 = hKMainActivity$startFlow$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = hKMainActivity$startFlow$1.label;
        if (i != 0) {
        }
        Pair pair2 = (Pair) obj2;
        booleanValue = ((Boolean) pair2.component1()).booleanValue();
        String str2 = (String) pair2.component2();
        if (!booleanValue) {
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void loadBackground$hyperkyc_release$default(HKMainActivity hKMainActivity, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        hKMainActivity.loadBackground$hyperkyc_release(str);
    }

    public final void loadBackground$hyperkyc_release(String url) {
        this.target = new Target() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$loadBackground$1
            @Override // com.squareup.picasso.Target
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                String canonicalName;
                Object m1202constructorimpl;
                String className;
                String substringAfterLast$default;
                String className2;
                Intrinsics.checkNotNullParameter(bitmap, "bitmap");
                Intrinsics.checkNotNullParameter(from, "from");
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
                sb.append("Background image loaded");
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
                            Log.println(3, str, "Background image loaded ");
                        }
                    }
                }
                HKMainActivity.this.getBinding$hyperkyc_release().HKMainActivityContent.setBackground(new BitmapDrawable(HKMainActivity.this.getResources(), bitmap));
                HKMainActivity.this.getBinding$hyperkyc_release().flContent.setBackground(new BitmapDrawable(HKMainActivity.this.getResources(), bitmap));
            }

            /* JADX WARN: Code restructure failed: missing block: B:48:0x0124, code lost:
            
                if (r0 == null) goto L52;
             */
            @Override // com.squareup.picasso.Target
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
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
                sb.append("Background image load failed");
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
                            Log.println(3, str, "Background image load failed ");
                        }
                    }
                }
                HKMainActivity.this.getBinding$hyperkyc_release().HKMainActivityContent.setBackground(null);
                HKMainActivity.this.getBinding$hyperkyc_release().flContent.setBackground(null);
            }

            @Override // com.squareup.picasso.Target
            public void onPrepareLoad(Drawable placeHolderDrawable) {
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
                sb.append("Background image loading");
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                if (CoreExtsKt.isRelease()) {
                    return;
                }
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
                        Log.println(3, str, "Background image loading ");
                    }
                }
            }
        };
        String str = url;
        Target target = null;
        if (!(str == null || str.length() == 0)) {
            ConstraintLayout constraintLayout = getBinding$hyperkyc_release().HKMainActivityContent;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.HKMainActivityContent");
            Target target2 = this.target;
            if (target2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(TypedValues.AttributesType.S_TARGET);
                target2 = null;
            }
            PicassoExtsKt.loadBackgroundImage(constraintLayout, url, target2);
            FrameLayout frameLayout = getBinding$hyperkyc_release().flContent;
            Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.flContent");
            Target target3 = this.target;
            if (target3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(TypedValues.AttributesType.S_TARGET);
            } else {
                target = target3;
            }
            PicassoExtsKt.loadBackgroundImage(frameLayout, url, target);
            return;
        }
        getBinding$hyperkyc_release().HKMainActivityContent.setBackground(null);
        getBinding$hyperkyc_release().flContent.setBackground(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object loadCountries(Continuation<? super List<KycCountry>> continuation) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        SafeContinuation safeContinuation2 = safeContinuation;
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
        String str2 = "loadCountries() called with: continuation = " + safeContinuation2;
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
                    String str3 = "loadCountries() called with: continuation = " + safeContinuation2;
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
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new HKMainActivity$loadCountries$2$2$1(this, getBinding$hyperkyc_release(), safeContinuation2, null), 3, null);
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object loadTextConfigs(Continuation<? super Map<String, ? extends Map<String, ? extends Object>>> continuation) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        SafeContinuation safeContinuation2 = safeContinuation;
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
        String str2 = "loadTextConfigs() called with: continuation = " + safeContinuation2;
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
                    String str3 = "loadTextConfigs() called with: continuation = " + safeContinuation2;
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
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new HKMainActivity$loadTextConfigs$2$2$1(this, getBinding$hyperkyc_release(), safeContinuation2, null), 3, null);
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object loadUIConfigs(Continuation<? super HSUIConfig> continuation) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        SafeContinuation safeContinuation2 = safeContinuation;
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
        String str2 = "loadUIConfigs() called with: continuation = " + safeContinuation2;
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
                    String str3 = "loadUIConfigs() called with: continuation = " + safeContinuation2;
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
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new HKMainActivity$loadUIConfigs$2$2$1(this, getBinding$hyperkyc_release(), safeContinuation2, null), 3, null);
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object loadRemoteConfig(Continuation<? super HSRemoteConfig> continuation) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        SafeContinuation safeContinuation2 = safeContinuation;
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
        String str2 = "loadRemoteConfig() called with: continuation = " + safeContinuation2;
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
                    String str3 = "loadRemoteConfig() called with: continuation = " + safeContinuation2;
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
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new HKMainActivity$loadRemoteConfig$2$2$1(this, getBinding$hyperkyc_release(), safeContinuation2, null), 3, null);
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object loadDefaultRemoteConfig(Continuation<? super Serializable> continuation) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        SafeContinuation safeContinuation2 = safeContinuation;
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
        String str2 = "loadDefaultRemoteConfig() called with: continuation = " + safeContinuation2;
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
                    String str3 = "loadDefaultRemoteConfig() called with: continuation = " + safeContinuation2;
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
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new HKMainActivity$loadDefaultRemoteConfig$2$2$1(this, getBinding$hyperkyc_release(), safeContinuation2, null), 3, null);
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object loadTransactionState(Continuation<? super TransactionStateResponse> continuation) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        SafeContinuation safeContinuation2 = safeContinuation;
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
        String str2 = "loadTransactionState() called with: continuation = " + safeContinuation2;
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
                    String str3 = "loadTransactionState() called with: continuation = " + safeContinuation2;
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
        LifecycleOwnerKt.getLifecycleScope(this).launchWhenCreated(new HKMainActivity$loadTransactionState$2$2$1(this, getBinding$hyperkyc_release(), safeContinuation2, null));
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }

    private static final int validateSdkVersion$lambda$35$toIntVersion(String str) {
        int i = 0;
        List take = CollectionsKt.take(StringsKt.split$default((CharSequence) str, new char[]{FilenameUtils.EXTENSION_SEPARATOR}, false, 0, 6, (Object) null), 2);
        ArrayList arrayList = new ArrayList();
        Iterator it = take.iterator();
        while (it.hasNext()) {
            Integer intOrNull = StringsKt.toIntOrNull((String) it.next());
            if (intOrNull != null) {
                arrayList.add(intOrNull);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            i = (i * 1000) + ((Number) it2.next()).intValue();
        }
        return i;
    }

    static /* synthetic */ void showJsonExceptionRetry$default(HKMainActivity hKMainActivity, boolean z, Integer num, Integer num2, String str, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            num = 0;
        }
        hKMainActivity.showJsonExceptionRetry(z, num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showJsonExceptionRetry$lambda$50$lambda$47(HKMainActivity this$0, Integer num, String str, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        showRetry$default(this$0, false, null, null, null, null, 30, null);
        FrameLayout frameLayout = this$0.getBinding$hyperkyc_release().flContent;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.flContent");
        frameLayout.setVisibility(0);
        HKMainActivity hKMainActivity = this$0;
        this$0.finishWithResult("error", this$0.getMainVM().getHyperKycData(), num, str, ContextExtsKt.isOnline(hKMainActivity), ContextExtsKt.isOnline(hKMainActivity));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void showRetry$default(HKMainActivity hKMainActivity, boolean z, Integer num, Integer num2, String str, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            num = 0;
        }
        hKMainActivity.showRetry(z, num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showRetry$lambda$56$lambda$52(Ref.IntRef retryCount, boolean z, HkActivityMainBinding this_with, Function0 function0, HKMainActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(retryCount, "$retryCount");
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        retryCount.element++;
        if (retryCount.element >= 3 && z) {
            MaterialButton btnCancel = this_with.btnCancel;
            Intrinsics.checkNotNullExpressionValue(btnCancel, "btnCancel");
            btnCancel.setVisibility(0);
        }
        if (function0 == null) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), null, null, new HKMainActivity$showRetry$2$1$1(this$0, null), 3, null);
        } else {
            function0.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showRetry$lambda$56$lambda$53(HKMainActivity this$0, Integer num, String str, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FrameLayout frameLayout = this$0.getBinding$hyperkyc_release().flContent;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.flContent");
        frameLayout.setVisibility(0);
        HKMainActivity hKMainActivity = this$0;
        this$0.finishWithResult("error", this$0.getMainVM().getHyperKycData(), num, str, ContextExtsKt.isOnline(hKMainActivity), ContextExtsKt.isOnline(hKMainActivity));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Fragment obtainFragmentByTag = ActivityExtsKt.obtainFragmentByTag(this, "nfcFragment");
        if (obtainFragmentByTag == null || !(obtainFragmentByTag instanceof NFCReaderFragment) || intent == null) {
            return;
        }
        ((NFCReaderFragment) obtainFragmentByTag).parseData$hyperkyc_release(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(21:421|(1:544)(4:425|426|427|(18:429|430|(1:432)|433|(1:438)|439|(1:441)(1:534)|(1:443)(1:533)|444|445|446|447|448|449|450|(1:452)|453|(3:455|456|(12:458|459|460|(2:(1:516)(1:513)|(1:515))(1:466)|467|(1:469)|470|(1:475)|476|(1:478)(1:509)|(1:480)(1:508)|481)(1:520))(1:521)))|(1:541)(1:538)|(1:540)|430|(0)|433|(2:435|438)|439|(0)(0)|(0)(0)|444|445|446|447|448|449|450|(0)|453|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(23:15|(1:17)(1:193)|18|(1:20)(2:189|(1:191)(1:192))|21|(1:188)(1:25)|(1:33)(1:30)|(1:32)|34|(1:36)|37|(1:41)|42|(1:44)|45|(6:151|152|153|(1:155)|156|(8:158|(14:160|(2:(1:184)(1:181)|(1:183))|166|(1:168)|169|(1:173)|174|(1:176)|177|49|50|51|(20:53|(1:132)(1:57)|(1:66)(1:62)|(1:64)(1:65)|67|(1:69)|70|(1:74)|75|(1:77)|78|(1:80)(1:131)|(1:82)(1:130)|83|84|85|86|(1:88)|89|(2:91|(15:93|(1:126)(1:97)|(1:124)(1:102)|(11:104|105|(1:107)|108|(1:112)|113|(1:115)(1:123)|116|(1:118)(1:122)|(1:120)|121)|125|105|(0)|108|(2:110|112)|113|(0)(0)|116|(0)(0)|(0)|121)))|133)|48|49|50|51|(0)|133))|47|48|49|50|51|(0)|133) */
    /* JADX WARN: Can't wrap try/catch for region: R(25:390|391|392|393|(3:625|626|(22:628|629|630|(17:632|402|403|(1:405)|406|407|(1:412)|548|(1:550)|551|(1:553)(9:559|560|561|562|563|564|(1:566)|567|(9:569|570|571|(12:573|574|575|576|(2:(1:602)(1:599)|(1:601))(1:582)|583|(1:585)|586|(1:591)|592|(1:594)|595)(1:604)|555|419|(21:421|(1:544)(4:425|426|427|(18:429|430|(1:432)|433|(1:438)|439|(1:441)(1:534)|(1:443)(1:533)|444|445|446|447|448|449|450|(1:452)|453|(3:455|456|(12:458|459|460|(2:(1:516)(1:513)|(1:515))(1:466)|467|(1:469)|470|(1:475)|476|(1:478)(1:509)|(1:480)(1:508)|481)(1:520))(1:521)))|(1:541)(1:538)|(1:540)|430|(0)|433|(2:435|438)|439|(0)(0)|(0)(0)|444|445|446|447|448|449|450|(0)|453|(0)(0))(1:545)|482|(4:(1:487)|488|(1:490)(2:499|(1:501)(2:502|(2:504|505)))|(3:492|(1:494)(1:496)|495)(2:497|498))(2:506|507))(1:607))|554|555|419|(0)(0)|482|(0)(0))|(1:624)(1:399)|(1:401)(1:623)|402|403|(0)|406|407|(2:409|412)|548|(0)|551|(0)(0)|554|555|419|(0)(0)|482|(0)(0)))|395|(1:397)|624|(0)(0)|402|403|(0)|406|407|(0)|548|(0)|551|(0)(0)|554|555|419|(0)(0)|482|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0d42, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0d43, code lost:
    
        r5 = kotlin.Result.INSTANCE;
        r1 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0ba6, code lost:
    
        if (r2 != null) goto L574;
     */
    /* JADX WARN: Code restructure failed: missing block: B:526:0x072e, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:527:0x0733, code lost:
    
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:528:0x0734, code lost:
    
        r3 = kotlin.Result.INSTANCE;
        r1 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r1));
     */
    /* JADX WARN: Code restructure failed: missing block: B:530:0x0730, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:531:0x0731, code lost:
    
        r4 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0d8b, code lost:
    
        if (r5 != null) goto L651;
     */
    /* JADX WARN: Code restructure failed: missing block: B:621:0x061f, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:622:0x0620, code lost:
    
        r12 = r22;
        r4 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0eaa, code lost:
    
        if (r2 != null) goto L697;
     */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0ed3  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0f16  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0f24  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0f2c  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0f29  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0f19  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0b38  */
    /* JADX WARN: Removed duplicated region for block: B:195:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:209:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0100 A[Catch: all -> 0x00df, TRY_ENTER, TRY_LEAVE, TryCatch #43 {all -> 0x00df, blocks: (B:775:0x00b9, B:213:0x0100, B:217:0x0111, B:220:0x0118, B:205:0x00d4, B:207:0x00da), top: B:774:0x00b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0111 A[Catch: all -> 0x00df, TRY_ENTER, TryCatch #43 {all -> 0x00df, blocks: (B:775:0x00b9, B:213:0x0100, B:217:0x0111, B:220:0x0118, B:205:0x00d4, B:207:0x00da), top: B:774:0x00b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x027f A[Catch: all -> 0x021e, TRY_ENTER, TRY_LEAVE, TryCatch #41 {all -> 0x021e, blocks: (B:234:0x027f, B:733:0x01a2, B:735:0x01af, B:737:0x01b5, B:740:0x01d2, B:742:0x01e3, B:743:0x01ea, B:745:0x01f4, B:748:0x01fb, B:749:0x0203, B:751:0x01c2, B:753:0x01c8), top: B:732:0x01a2 }] */
    /* JADX WARN: Removed duplicated region for block: B:244:0x02cb A[Catch: all -> 0x02a9, TRY_ENTER, TRY_LEAVE, TryCatch #7 {all -> 0x02a9, blocks: (B:238:0x028f, B:244:0x02cb, B:248:0x02dc, B:251:0x02e3, B:701:0x029e, B:703:0x02a4), top: B:237:0x028f }] */
    /* JADX WARN: Removed duplicated region for block: B:248:0x02dc A[Catch: all -> 0x02a9, TRY_ENTER, TryCatch #7 {all -> 0x02a9, blocks: (B:238:0x028f, B:244:0x02cb, B:248:0x02dc, B:251:0x02e3, B:701:0x029e, B:703:0x02a4), top: B:237:0x028f }] */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x08fb  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x095d  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0972 A[Catch: all -> 0x0952, TRY_ENTER, TRY_LEAVE, TryCatch #34 {all -> 0x0952, blocks: (B:377:0x0933, B:278:0x0972, B:282:0x0983, B:285:0x098a, B:270:0x0947, B:272:0x094d), top: B:376:0x0933 }] */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0983 A[Catch: all -> 0x0952, TRY_ENTER, TryCatch #34 {all -> 0x0952, blocks: (B:377:0x0933, B:278:0x0972, B:282:0x0983, B:285:0x098a, B:270:0x0947, B:272:0x094d), top: B:376:0x0933 }] */
    /* JADX WARN: Removed duplicated region for block: B:292:0x09ad  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0ac9  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x09c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0a07  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x0a10  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x0960  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x0433 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:401:0x04a6  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x04bb A[Catch: all -> 0x049c, TRY_ENTER, TRY_LEAVE, TryCatch #38 {all -> 0x049c, blocks: (B:630:0x047d, B:405:0x04bb, B:409:0x04cc, B:412:0x04d3, B:397:0x0491, B:399:0x0497), top: B:629:0x047d }] */
    /* JADX WARN: Removed duplicated region for block: B:409:0x04cc A[Catch: all -> 0x049c, TRY_ENTER, TryCatch #38 {all -> 0x049c, blocks: (B:630:0x047d, B:405:0x04bb, B:409:0x04cc, B:412:0x04d3, B:397:0x0491, B:399:0x0497), top: B:629:0x047d }] */
    /* JADX WARN: Removed duplicated region for block: B:421:0x0641 A[Catch: all -> 0x08f2, TryCatch #17 {all -> 0x08f2, blocks: (B:419:0x063b, B:421:0x0641, B:423:0x0666, B:460:0x076c, B:462:0x0779, B:464:0x077f, B:467:0x079c, B:469:0x07ad, B:470:0x07b4, B:472:0x07be, B:475:0x07c5, B:476:0x07cd, B:478:0x07de, B:480:0x07e6, B:481:0x07f9, B:482:0x080f, B:488:0x0837, B:490:0x0849, B:492:0x087f, B:494:0x089a, B:496:0x08a7, B:497:0x08b6, B:498:0x08bd, B:499:0x0857, B:501:0x0863, B:502:0x0871, B:504:0x08be, B:505:0x08eb, B:506:0x08ec, B:507:0x08f1, B:511:0x078c, B:513:0x0792, B:418:0x0631), top: B:417:0x0631 }] */
    /* JADX WARN: Removed duplicated region for block: B:432:0x06b0 A[Catch: all -> 0x0690, TryCatch #33 {all -> 0x0690, blocks: (B:427:0x0676, B:430:0x069f, B:432:0x06b0, B:433:0x06b7, B:435:0x06c1, B:438:0x06c8, B:439:0x06d0, B:441:0x06e2, B:443:0x06ea, B:444:0x06fd, B:536:0x0685, B:538:0x068b), top: B:426:0x0676 }] */
    /* JADX WARN: Removed duplicated region for block: B:441:0x06e2 A[Catch: all -> 0x0690, TryCatch #33 {all -> 0x0690, blocks: (B:427:0x0676, B:430:0x069f, B:432:0x06b0, B:433:0x06b7, B:435:0x06c1, B:438:0x06c8, B:439:0x06d0, B:441:0x06e2, B:443:0x06ea, B:444:0x06fd, B:536:0x0685, B:538:0x068b), top: B:426:0x0676 }] */
    /* JADX WARN: Removed duplicated region for block: B:443:0x06ea A[Catch: all -> 0x0690, TryCatch #33 {all -> 0x0690, blocks: (B:427:0x0676, B:430:0x069f, B:432:0x06b0, B:433:0x06b7, B:435:0x06c1, B:438:0x06c8, B:439:0x06d0, B:441:0x06e2, B:443:0x06ea, B:444:0x06fd, B:536:0x0685, B:538:0x068b), top: B:426:0x0676 }] */
    /* JADX WARN: Removed duplicated region for block: B:452:0x0744  */
    /* JADX WARN: Removed duplicated region for block: B:455:0x074d  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x082d  */
    /* JADX WARN: Removed duplicated region for block: B:506:0x08ec A[Catch: all -> 0x08f2, TryCatch #17 {all -> 0x08f2, blocks: (B:419:0x063b, B:421:0x0641, B:423:0x0666, B:460:0x076c, B:462:0x0779, B:464:0x077f, B:467:0x079c, B:469:0x07ad, B:470:0x07b4, B:472:0x07be, B:475:0x07c5, B:476:0x07cd, B:478:0x07de, B:480:0x07e6, B:481:0x07f9, B:482:0x080f, B:488:0x0837, B:490:0x0849, B:492:0x087f, B:494:0x089a, B:496:0x08a7, B:497:0x08b6, B:498:0x08bd, B:499:0x0857, B:501:0x0863, B:502:0x0871, B:504:0x08be, B:505:0x08eb, B:506:0x08ec, B:507:0x08f1, B:511:0x078c, B:513:0x0792, B:418:0x0631), top: B:417:0x0631 }] */
    /* JADX WARN: Removed duplicated region for block: B:521:0x0808  */
    /* JADX WARN: Removed duplicated region for block: B:533:0x06fc  */
    /* JADX WARN: Removed duplicated region for block: B:534:0x06e7  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0d54  */
    /* JADX WARN: Removed duplicated region for block: B:545:0x080d  */
    /* JADX WARN: Removed duplicated region for block: B:550:0x04f4  */
    /* JADX WARN: Removed duplicated region for block: B:553:0x050e  */
    /* JADX WARN: Removed duplicated region for block: B:559:0x0516 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:566:0x0550  */
    /* JADX WARN: Removed duplicated region for block: B:569:0x0559  */
    /* JADX WARN: Removed duplicated region for block: B:607:0x060e  */
    /* JADX WARN: Removed duplicated region for block: B:623:0x04a9  */
    /* JADX WARN: Removed duplicated region for block: B:642:0x0328 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:649:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:652:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:701:0x029e A[Catch: all -> 0x02a9, TryCatch #7 {all -> 0x02a9, blocks: (B:238:0x028f, B:244:0x02cb, B:248:0x02dc, B:251:0x02e3, B:701:0x029e, B:703:0x02a4), top: B:237:0x028f }] */
    /* JADX WARN: Removed duplicated region for block: B:705:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:706:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:715:0x0258 A[Catch: all -> 0x0af8, TryCatch #19 {all -> 0x0af8, blocks: (B:229:0x0235, B:232:0x025c, B:715:0x0258), top: B:228:0x0235 }] */
    /* JADX WARN: Removed duplicated region for block: B:719:0x0146 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:768:0x00ee  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void startWebViewFlow(WorkflowUIState.WebView webViewUIState) {
        Map emptyMap;
        CharSequence charSequence;
        String str;
        String str2;
        String str3;
        Throwable th;
        Object m1202constructorimpl;
        Throwable m1205exceptionOrNullimpl;
        Throwable th2;
        File hsExceptionFile;
        String str4;
        String str5;
        File file;
        String str6;
        Object m1202constructorimpl2;
        String str7;
        String str8;
        String canonicalName;
        Class<?> cls;
        String className;
        Throwable m1205exceptionOrNullimpl2;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        Object m1202constructorimpl3;
        String str14;
        String str15;
        String str16;
        Matcher matcher;
        String localizedMessage;
        Class<?> cls2;
        String className2;
        Class<?> cls3;
        String className3;
        Class<?> cls4;
        String className4;
        String str17;
        String substringAfterLast$default;
        Matcher matcher2;
        Object m1202constructorimpl4;
        String str18;
        String str19;
        String canonicalName2;
        Class<?> cls5;
        String className5;
        File file2;
        StackTraceElement stackTraceElement;
        String str20;
        String str21;
        Class<?> cls6;
        Matcher matcher3;
        String str22;
        Object m1202constructorimpl5;
        String str23;
        String canonicalName3;
        Class<?> cls7;
        String className6;
        String str24;
        Throwable th3;
        Object obj;
        Object obj2;
        Throwable m1205exceptionOrNullimpl3;
        Object obj3;
        HVError hVError;
        Object obj4;
        Object obj5;
        String str25;
        String canonicalName4;
        Class<?> cls8;
        Matcher matcher4;
        String localizedMessage2;
        String str26;
        Object m1202constructorimpl6;
        String canonicalName5;
        Class<?> cls9;
        String str27;
        String className7;
        String className8;
        HyperLogger.Level level;
        HyperLogger companion;
        StringBuilder sb;
        StackTraceElement stackTraceElement2;
        String className9;
        String str28;
        String str29;
        String substringAfterLast$default2;
        Matcher matcher5;
        String str30;
        Object m1202constructorimpl7;
        String canonicalName6;
        Class<?> cls10;
        String className10;
        Class<?> cls11;
        String str31;
        String substringAfterLast$default3;
        Matcher matcher6;
        String str32;
        Object m1202constructorimpl8;
        String canonicalName7;
        Class<?> cls12;
        String className11;
        Class<?> cls13;
        Class<?> cls14;
        String str33 = "getInitialApplication";
        String str34 = "";
        getWindow().setSoftInputMode(16);
        if (webViewUIState.getOpenInAppBrowser()) {
            Intent intent = new Intent(this, (Class<?>) HKBrowserActivity.class);
            intent.putExtra("data", webViewUIState.getData());
            intent.putExtra("url", webViewUIState.getUrl());
            intent.putExtra("moduleId", webViewUIState.getTag());
            try {
                Result.Companion companion2 = Result.INSTANCE;
                HKMainActivity hKMainActivity = this;
                if (getMainVM().getHsStateHandler().getIsActivityRecreated()) {
                    try {
                        getMainVM().getHsStateHandler().setActivityRecreated(false);
                        HSStateHandler hsStateHandler = getMainVM().getHsStateHandler();
                        String tag = webViewUIState.getTag();
                        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                        HyperLogger companion3 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb2 = new StringBuilder();
                        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                        try {
                            try {
                                try {
                                    if (stackTraceElement3 != null) {
                                        try {
                                            String className12 = stackTraceElement3.getClassName();
                                            if (className12 != null) {
                                                charSequence = "co.hyperverge";
                                                str = "packageName";
                                                str17 = "Throwable().stackTrace";
                                                try {
                                                    substringAfterLast$default = StringsKt.substringAfterLast$default(className12, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                    if (substringAfterLast$default != null) {
                                                        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                                                        if (matcher2.find()) {
                                                            substringAfterLast$default = matcher2.replaceAll("");
                                                            Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "replaceAll(\"\")");
                                                        }
                                                        Unit unit = Unit.INSTANCE;
                                                        if (substringAfterLast$default.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                            substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                                                            Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "this as java.lang.String…ing(startIndex, endIndex)");
                                                        }
                                                        sb2.append(substringAfterLast$default);
                                                        sb2.append(" - ");
                                                        sb2.append("retrieveState() called");
                                                        sb2.append(' ');
                                                        sb2.append("");
                                                        companion3.log(level2, sb2.toString());
                                                        if (!CoreExtsKt.isRelease()) {
                                                            try {
                                                                Result.Companion companion4 = Result.INSTANCE;
                                                                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                                                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                                                                m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                                                            } catch (Throwable th4) {
                                                                Result.Companion companion5 = Result.INSTANCE;
                                                                m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th4));
                                                            }
                                                            if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                                                m1202constructorimpl4 = "";
                                                            }
                                                            String str35 = (String) m1202constructorimpl4;
                                                            if (CoreExtsKt.isDebug()) {
                                                                str18 = str;
                                                                try {
                                                                    Intrinsics.checkNotNullExpressionValue(str35, str18);
                                                                    if (StringsKt.contains$default((CharSequence) str35, charSequence, false, 2, (Object) null)) {
                                                                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                                                        str19 = str17;
                                                                        try {
                                                                            Intrinsics.checkNotNullExpressionValue(stackTrace2, str19);
                                                                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                                                            if (stackTraceElement4 == null || (className5 = stackTraceElement4.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                                                                canonicalName2 = (hsStateHandler == null || (cls5 = hsStateHandler.getClass()) == null) ? null : cls5.getCanonicalName();
                                                                                if (canonicalName2 == null) {
                                                                                    canonicalName2 = "N/A";
                                                                                }
                                                                            }
                                                                            Matcher matcher7 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                                                                            if (matcher7.find()) {
                                                                                canonicalName2 = matcher7.replaceAll("");
                                                                                Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                                                                            }
                                                                            Unit unit2 = Unit.INSTANCE;
                                                                            if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                canonicalName2 = canonicalName2.substring(0, 23);
                                                                                Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                            }
                                                                            Log.println(3, canonicalName2, "retrieveState() called ");
                                                                        } catch (Throwable th5) {
                                                                            str = str18;
                                                                            str2 = str19;
                                                                            th = th5;
                                                                            str3 = "null cannot be cast to non-null type android.app.Application";
                                                                            Result.Companion companion6 = Result.INSTANCE;
                                                                            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                                            if (m1205exceptionOrNullimpl == null) {
                                                                            }
                                                                        }
                                                                    } else {
                                                                        str19 = str17;
                                                                    }
                                                                    file2 = new File(hsStateHandler.getResultDataDir(), tag + ".json");
                                                                    if (file2.exists()) {
                                                                        file2 = hsStateHandler.getHsDataFile();
                                                                    }
                                                                    HyperLogger.Level level3 = HyperLogger.Level.DEBUG;
                                                                    HyperLogger companion7 = HyperLogger.INSTANCE.getInstance();
                                                                    StringBuilder sb3 = new StringBuilder();
                                                                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                                                                    Intrinsics.checkNotNullExpressionValue(stackTrace3, str19);
                                                                    stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                                                                    if (stackTraceElement != null) {
                                                                        String className13 = stackTraceElement.getClassName();
                                                                        if (className13 != null) {
                                                                            str = str18;
                                                                            str20 = "null cannot be cast to non-null type android.app.Application";
                                                                            str17 = str19;
                                                                            try {
                                                                                str21 = StringsKt.substringAfterLast$default(className13, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                                                if (str21 != null) {
                                                                                    matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str21);
                                                                                    if (matcher3.find()) {
                                                                                        str21 = matcher3.replaceAll("");
                                                                                        Intrinsics.checkNotNullExpressionValue(str21, "replaceAll(\"\")");
                                                                                    }
                                                                                    Unit unit3 = Unit.INSTANCE;
                                                                                    if (str21.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                        str21 = str21.substring(0, 23);
                                                                                        Intrinsics.checkNotNullExpressionValue(str21, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                    }
                                                                                    sb3.append(str21);
                                                                                    sb3.append(" - ");
                                                                                    str22 = "retrieveState: state targetFile exists : " + file2.exists();
                                                                                    if (str22 == null) {
                                                                                        str22 = "null ";
                                                                                    }
                                                                                    sb3.append(str22);
                                                                                    sb3.append(' ');
                                                                                    sb3.append("");
                                                                                    companion7.log(level3, sb3.toString());
                                                                                    if (CoreExtsKt.isRelease()) {
                                                                                        try {
                                                                                            Result.Companion companion8 = Result.INSTANCE;
                                                                                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                                                                            str3 = str20;
                                                                                            try {
                                                                                                Intrinsics.checkNotNull(invoke2, str3);
                                                                                                m1202constructorimpl5 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                                                                            } catch (Throwable th6) {
                                                                                                th = th6;
                                                                                                Throwable th7 = th;
                                                                                                try {
                                                                                                    Result.Companion companion9 = Result.INSTANCE;
                                                                                                    m1202constructorimpl5 = Result.m1202constructorimpl(ResultKt.createFailure(th7));
                                                                                                    if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                                                                                                    }
                                                                                                    String str36 = (String) m1202constructorimpl5;
                                                                                                    if (CoreExtsKt.isDebug()) {
                                                                                                    }
                                                                                                    str2 = str17;
                                                                                                    if (file2.exists()) {
                                                                                                    }
                                                                                                    throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                                                                                } catch (Throwable th8) {
                                                                                                    th = th8;
                                                                                                    str2 = str17;
                                                                                                    th = th;
                                                                                                    Result.Companion companion62 = Result.INSTANCE;
                                                                                                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                                                                    if (m1205exceptionOrNullimpl == null) {
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        } catch (Throwable th9) {
                                                                                            th = th9;
                                                                                            str3 = str20;
                                                                                        }
                                                                                        if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                                                                                            m1202constructorimpl5 = "";
                                                                                        }
                                                                                        String str362 = (String) m1202constructorimpl5;
                                                                                        if (CoreExtsKt.isDebug()) {
                                                                                            str23 = str;
                                                                                            try {
                                                                                                Intrinsics.checkNotNullExpressionValue(str362, str23);
                                                                                                str = str23;
                                                                                            } catch (Throwable th10) {
                                                                                                th = th10;
                                                                                                str = str23;
                                                                                                str2 = str17;
                                                                                                Result.Companion companion622 = Result.INSTANCE;
                                                                                                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                                                                if (m1205exceptionOrNullimpl == null) {
                                                                                                }
                                                                                            }
                                                                                            try {
                                                                                                if (StringsKt.contains$default((CharSequence) str362, charSequence, false, 2, (Object) null)) {
                                                                                                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                                                                                    str2 = str17;
                                                                                                    try {
                                                                                                        Intrinsics.checkNotNullExpressionValue(stackTrace4, str2);
                                                                                                        StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                                                                                        if (stackTraceElement5 == null || (className6 = stackTraceElement5.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                                                                                            canonicalName3 = (hsStateHandler == null || (cls7 = hsStateHandler.getClass()) == null) ? null : cls7.getCanonicalName();
                                                                                                            if (canonicalName3 == null) {
                                                                                                                canonicalName3 = "N/A";
                                                                                                            }
                                                                                                        }
                                                                                                        Matcher matcher8 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                                                                                                        if (matcher8.find()) {
                                                                                                            canonicalName3 = matcher8.replaceAll("");
                                                                                                            Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                                                                                                        }
                                                                                                        Unit unit4 = Unit.INSTANCE;
                                                                                                        if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                                            canonicalName3 = canonicalName3.substring(0, 23);
                                                                                                            Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                        }
                                                                                                        StringBuilder sb4 = new StringBuilder();
                                                                                                        String str37 = "retrieveState: state targetFile exists : " + file2.exists();
                                                                                                        if (str37 == null) {
                                                                                                            str37 = "null ";
                                                                                                        }
                                                                                                        sb4.append(str37);
                                                                                                        sb4.append(' ');
                                                                                                        sb4.append("");
                                                                                                        Log.println(3, canonicalName3, sb4.toString());
                                                                                                    } catch (Throwable th11) {
                                                                                                        th = th11;
                                                                                                        th = th;
                                                                                                        Result.Companion companion6222 = Result.INSTANCE;
                                                                                                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                                                                        if (m1205exceptionOrNullimpl == null) {
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            } catch (Throwable th12) {
                                                                                                th = th12;
                                                                                                str2 = str17;
                                                                                                Result.Companion companion62222 = Result.INSTANCE;
                                                                                                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                                                                if (m1205exceptionOrNullimpl == null) {
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                        str2 = str17;
                                                                                    } else {
                                                                                        str2 = str17;
                                                                                        str3 = str20;
                                                                                    }
                                                                                    if (file2.exists()) {
                                                                                        try {
                                                                                            Result.Companion companion10 = Result.INSTANCE;
                                                                                            obj = hsStateHandler.getGson().fromJson(FilesKt.readText$default(file2, null, 1, null), new HSStateHandler$retrieveState$3$type$1().getType());
                                                                                            try {
                                                                                                level = HyperLogger.Level.DEBUG;
                                                                                                companion = HyperLogger.INSTANCE.getInstance();
                                                                                                sb = new StringBuilder();
                                                                                                StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                                                                                                Intrinsics.checkNotNullExpressionValue(stackTrace5, str2);
                                                                                                stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                                                                                            } catch (Throwable th13) {
                                                                                                th = th13;
                                                                                                str24 = str2;
                                                                                            }
                                                                                        } catch (Throwable th14) {
                                                                                            str24 = str2;
                                                                                            str23 = str;
                                                                                            th3 = th14;
                                                                                            obj = null;
                                                                                        }
                                                                                        if (stackTraceElement2 != null) {
                                                                                            try {
                                                                                                className9 = stackTraceElement2.getClassName();
                                                                                            } catch (Throwable th15) {
                                                                                                th3 = th15;
                                                                                                str24 = str2;
                                                                                                str23 = str;
                                                                                                try {
                                                                                                    Result.Companion companion11 = Result.INSTANCE;
                                                                                                    obj2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                                                                                                    m1205exceptionOrNullimpl3 = Result.m1205exceptionOrNullimpl(obj2);
                                                                                                    if (m1205exceptionOrNullimpl3 != null) {
                                                                                                    }
                                                                                                    Object obj6 = obj3;
                                                                                                    Intrinsics.checkNotNull(obj6, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                                    Triple triple = (Triple) obj6;
                                                                                                    hVError = (HVError) triple.component1();
                                                                                                    HVResponse hVResponse = (HVResponse) triple.component2();
                                                                                                    Object obj7 = (JSONObject) triple.component3();
                                                                                                    if (hVError == null) {
                                                                                                    }
                                                                                                } catch (Throwable th16) {
                                                                                                    th = th16;
                                                                                                    str = str23;
                                                                                                    str2 = str24;
                                                                                                    Result.Companion companion622222 = Result.INSTANCE;
                                                                                                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                                                                    if (m1205exceptionOrNullimpl == null) {
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            if (className9 != null) {
                                                                                                str28 = str3;
                                                                                                str29 = str2;
                                                                                                try {
                                                                                                    substringAfterLast$default2 = StringsKt.substringAfterLast$default(className9, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                                                                } catch (Throwable th17) {
                                                                                                    th3 = th17;
                                                                                                    str24 = str29;
                                                                                                    str3 = str28;
                                                                                                    str23 = str;
                                                                                                    Result.Companion companion112 = Result.INSTANCE;
                                                                                                    obj2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                                                                                                    m1205exceptionOrNullimpl3 = Result.m1205exceptionOrNullimpl(obj2);
                                                                                                    if (m1205exceptionOrNullimpl3 != null) {
                                                                                                    }
                                                                                                    Object obj62 = obj3;
                                                                                                    Intrinsics.checkNotNull(obj62, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                                    Triple triple2 = (Triple) obj62;
                                                                                                    hVError = (HVError) triple2.component1();
                                                                                                    HVResponse hVResponse2 = (HVResponse) triple2.component2();
                                                                                                    Object obj72 = (JSONObject) triple2.component3();
                                                                                                    if (hVError == null) {
                                                                                                    }
                                                                                                }
                                                                                                if (substringAfterLast$default2 != null) {
                                                                                                    matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default2);
                                                                                                    if (matcher5.find()) {
                                                                                                        substringAfterLast$default2 = matcher5.replaceAll("");
                                                                                                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "replaceAll(\"\")");
                                                                                                    }
                                                                                                    Unit unit5 = Unit.INSTANCE;
                                                                                                    if (substringAfterLast$default2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                                        substringAfterLast$default2 = substringAfterLast$default2.substring(0, 23);
                                                                                                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                    }
                                                                                                    sb.append(substringAfterLast$default2);
                                                                                                    sb.append(" - ");
                                                                                                    str30 = "retrieveState: hvData from file: " + obj;
                                                                                                    if (str30 == null) {
                                                                                                        str30 = "null ";
                                                                                                    }
                                                                                                    sb.append(str30);
                                                                                                    sb.append(' ');
                                                                                                    sb.append("");
                                                                                                    companion.log(level, sb.toString());
                                                                                                    if (CoreExtsKt.isRelease()) {
                                                                                                        str24 = str29;
                                                                                                        str3 = str28;
                                                                                                    } else {
                                                                                                        try {
                                                                                                            Result.Companion companion12 = Result.INSTANCE;
                                                                                                            Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                                                                                            str3 = str28;
                                                                                                            try {
                                                                                                                Intrinsics.checkNotNull(invoke3, str3);
                                                                                                                m1202constructorimpl7 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                                                                                                            } catch (Throwable th18) {
                                                                                                                th = th18;
                                                                                                                Throwable th19 = th;
                                                                                                                try {
                                                                                                                    Result.Companion companion13 = Result.INSTANCE;
                                                                                                                    m1202constructorimpl7 = Result.m1202constructorimpl(ResultKt.createFailure(th19));
                                                                                                                    if (Result.m1208isFailureimpl(m1202constructorimpl7)) {
                                                                                                                    }
                                                                                                                    String str38 = (String) m1202constructorimpl7;
                                                                                                                    if (CoreExtsKt.isDebug()) {
                                                                                                                    }
                                                                                                                } catch (Throwable th20) {
                                                                                                                    th = th20;
                                                                                                                    str24 = str29;
                                                                                                                    str23 = str;
                                                                                                                    th3 = th;
                                                                                                                    Result.Companion companion1122 = Result.INSTANCE;
                                                                                                                    obj2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                                                                                                                    m1205exceptionOrNullimpl3 = Result.m1205exceptionOrNullimpl(obj2);
                                                                                                                    if (m1205exceptionOrNullimpl3 != null) {
                                                                                                                    }
                                                                                                                    Object obj622 = obj3;
                                                                                                                    Intrinsics.checkNotNull(obj622, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                                                    Triple triple22 = (Triple) obj622;
                                                                                                                    hVError = (HVError) triple22.component1();
                                                                                                                    HVResponse hVResponse22 = (HVResponse) triple22.component2();
                                                                                                                    Object obj722 = (JSONObject) triple22.component3();
                                                                                                                    if (hVError == null) {
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        } catch (Throwable th21) {
                                                                                                            th = th21;
                                                                                                            str3 = str28;
                                                                                                        }
                                                                                                        if (Result.m1208isFailureimpl(m1202constructorimpl7)) {
                                                                                                            m1202constructorimpl7 = "";
                                                                                                        }
                                                                                                        String str382 = (String) m1202constructorimpl7;
                                                                                                        if (CoreExtsKt.isDebug()) {
                                                                                                            str24 = str29;
                                                                                                        } else {
                                                                                                            str23 = str;
                                                                                                            try {
                                                                                                                Intrinsics.checkNotNullExpressionValue(str382, str23);
                                                                                                                if (StringsKt.contains$default((CharSequence) str382, charSequence, false, 2, (Object) null)) {
                                                                                                                    StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                                                                                                    str24 = str29;
                                                                                                                    try {
                                                                                                                        Intrinsics.checkNotNullExpressionValue(stackTrace6, str24);
                                                                                                                        StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                                                                                                        if (stackTraceElement6 == null || (className10 = stackTraceElement6.getClassName()) == null || (canonicalName6 = StringsKt.substringAfterLast$default(className10, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                                                                                                            canonicalName6 = (hsStateHandler == null || (cls10 = hsStateHandler.getClass()) == null) ? null : cls10.getCanonicalName();
                                                                                                                            if (canonicalName6 == null) {
                                                                                                                                canonicalName6 = "N/A";
                                                                                                                            }
                                                                                                                        }
                                                                                                                        Matcher matcher9 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName6);
                                                                                                                        if (matcher9.find()) {
                                                                                                                            canonicalName6 = matcher9.replaceAll("");
                                                                                                                            Intrinsics.checkNotNullExpressionValue(canonicalName6, "replaceAll(\"\")");
                                                                                                                        }
                                                                                                                        Unit unit6 = Unit.INSTANCE;
                                                                                                                        if (canonicalName6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                                                            canonicalName6 = canonicalName6.substring(0, 23);
                                                                                                                            Intrinsics.checkNotNullExpressionValue(canonicalName6, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                                        }
                                                                                                                        StringBuilder sb5 = new StringBuilder();
                                                                                                                        String str39 = "retrieveState: hvData from file: " + obj;
                                                                                                                        if (str39 == null) {
                                                                                                                            str39 = "null ";
                                                                                                                        }
                                                                                                                        sb5.append(str39);
                                                                                                                        sb5.append(' ');
                                                                                                                        sb5.append("");
                                                                                                                        Log.println(3, canonicalName6, sb5.toString());
                                                                                                                    } catch (Throwable th22) {
                                                                                                                        th = th22;
                                                                                                                        th3 = th;
                                                                                                                        Result.Companion companion11222 = Result.INSTANCE;
                                                                                                                        obj2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                                                                                                                        m1205exceptionOrNullimpl3 = Result.m1205exceptionOrNullimpl(obj2);
                                                                                                                        if (m1205exceptionOrNullimpl3 != null) {
                                                                                                                        }
                                                                                                                        Object obj6222 = obj3;
                                                                                                                        Intrinsics.checkNotNull(obj6222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                                                        Triple triple222 = (Triple) obj6222;
                                                                                                                        hVError = (HVError) triple222.component1();
                                                                                                                        HVResponse hVResponse222 = (HVResponse) triple222.component2();
                                                                                                                        Object obj7222 = (JSONObject) triple222.component3();
                                                                                                                        if (hVError == null) {
                                                                                                                        }
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    str24 = str29;
                                                                                                                }
                                                                                                                obj2 = Result.m1202constructorimpl(Unit.INSTANCE);
                                                                                                            } catch (Throwable th23) {
                                                                                                                th = th23;
                                                                                                                str24 = str29;
                                                                                                                th3 = th;
                                                                                                                Result.Companion companion112222 = Result.INSTANCE;
                                                                                                                obj2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                                                                                                                m1205exceptionOrNullimpl3 = Result.m1205exceptionOrNullimpl(obj2);
                                                                                                                if (m1205exceptionOrNullimpl3 != null) {
                                                                                                                }
                                                                                                                Object obj62222 = obj3;
                                                                                                                Intrinsics.checkNotNull(obj62222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                                                Triple triple2222 = (Triple) obj62222;
                                                                                                                hVError = (HVError) triple2222.component1();
                                                                                                                HVResponse hVResponse2222 = (HVResponse) triple2222.component2();
                                                                                                                Object obj72222 = (JSONObject) triple2222.component3();
                                                                                                                if (hVError == null) {
                                                                                                                }
                                                                                                            }
                                                                                                            m1205exceptionOrNullimpl3 = Result.m1205exceptionOrNullimpl(obj2);
                                                                                                            if (m1205exceptionOrNullimpl3 != null) {
                                                                                                                HyperLogger.Level level4 = HyperLogger.Level.ERROR;
                                                                                                                HyperLogger companion14 = HyperLogger.INSTANCE.getInstance();
                                                                                                                StringBuilder sb6 = new StringBuilder();
                                                                                                                obj3 = obj;
                                                                                                                StackTraceElement[] stackTrace7 = new Throwable().getStackTrace();
                                                                                                                Intrinsics.checkNotNullExpressionValue(stackTrace7, str24);
                                                                                                                StackTraceElement stackTraceElement7 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace7);
                                                                                                                if (stackTraceElement7 == null || (className8 = stackTraceElement7.getClassName()) == null) {
                                                                                                                    str25 = str3;
                                                                                                                    str = str23;
                                                                                                                    str17 = str24;
                                                                                                                } else {
                                                                                                                    str25 = str3;
                                                                                                                    str = str23;
                                                                                                                    str17 = str24;
                                                                                                                    try {
                                                                                                                        canonicalName4 = StringsKt.substringAfterLast$default(className8, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                                                                                        if (canonicalName4 != null) {
                                                                                                                            matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName4);
                                                                                                                            if (matcher4.find()) {
                                                                                                                                canonicalName4 = matcher4.replaceAll("");
                                                                                                                                Intrinsics.checkNotNullExpressionValue(canonicalName4, "replaceAll(\"\")");
                                                                                                                            }
                                                                                                                            Unit unit7 = Unit.INSTANCE;
                                                                                                                            if (canonicalName4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                                                                canonicalName4 = canonicalName4.substring(0, 23);
                                                                                                                                Intrinsics.checkNotNullExpressionValue(canonicalName4, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                                            }
                                                                                                                            sb6.append(canonicalName4);
                                                                                                                            sb6.append(" - ");
                                                                                                                            sb6.append("unable to retrieve hv_data state json from file");
                                                                                                                            sb6.append(' ');
                                                                                                                            localizedMessage2 = m1205exceptionOrNullimpl3 == null ? m1205exceptionOrNullimpl3.getLocalizedMessage() : null;
                                                                                                                            if (localizedMessage2 == null) {
                                                                                                                                str26 = '\n' + localizedMessage2;
                                                                                                                            } else {
                                                                                                                                str26 = "";
                                                                                                                            }
                                                                                                                            sb6.append(str26);
                                                                                                                            companion14.log(level4, sb6.toString());
                                                                                                                            CoreExtsKt.isRelease();
                                                                                                                            Result.Companion companion15 = Result.INSTANCE;
                                                                                                                            Object invoke4 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                                                                                                            str3 = str25;
                                                                                                                            Intrinsics.checkNotNull(invoke4, str3);
                                                                                                                            m1202constructorimpl6 = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
                                                                                                                            if (Result.m1208isFailureimpl(m1202constructorimpl6)) {
                                                                                                                                m1202constructorimpl6 = "";
                                                                                                                            }
                                                                                                                            String str40 = (String) m1202constructorimpl6;
                                                                                                                            if (CoreExtsKt.isDebug()) {
                                                                                                                                str23 = str;
                                                                                                                                Intrinsics.checkNotNullExpressionValue(str40, str23);
                                                                                                                                if (StringsKt.contains$default((CharSequence) str40, charSequence, false, 2, (Object) null)) {
                                                                                                                                    StackTraceElement[] stackTrace8 = new Throwable().getStackTrace();
                                                                                                                                    str24 = str17;
                                                                                                                                    Intrinsics.checkNotNullExpressionValue(stackTrace8, str24);
                                                                                                                                    StackTraceElement stackTraceElement8 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace8);
                                                                                                                                    if (stackTraceElement8 == null || (className7 = stackTraceElement8.getClassName()) == null || (canonicalName5 = StringsKt.substringAfterLast$default(className7, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                                                                                                                        canonicalName5 = (hsStateHandler == null || (cls9 = hsStateHandler.getClass()) == null) ? null : cls9.getCanonicalName();
                                                                                                                                        if (canonicalName5 == null) {
                                                                                                                                            canonicalName5 = "N/A";
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    Matcher matcher10 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName5);
                                                                                                                                    if (matcher10.find()) {
                                                                                                                                        canonicalName5 = matcher10.replaceAll("");
                                                                                                                                        Intrinsics.checkNotNullExpressionValue(canonicalName5, "replaceAll(\"\")");
                                                                                                                                    }
                                                                                                                                    Unit unit8 = Unit.INSTANCE;
                                                                                                                                    if (canonicalName5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                                                                        canonicalName5 = canonicalName5.substring(0, 23);
                                                                                                                                        Intrinsics.checkNotNullExpressionValue(canonicalName5, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                                                    }
                                                                                                                                    StringBuilder sb7 = new StringBuilder();
                                                                                                                                    sb7.append("unable to retrieve hv_data state json from file");
                                                                                                                                    sb7.append(' ');
                                                                                                                                    String localizedMessage3 = m1205exceptionOrNullimpl3 != null ? m1205exceptionOrNullimpl3.getLocalizedMessage() : null;
                                                                                                                                    if (localizedMessage3 != null) {
                                                                                                                                        str27 = '\n' + localizedMessage3;
                                                                                                                                    } else {
                                                                                                                                        str27 = "";
                                                                                                                                    }
                                                                                                                                    sb7.append(str27);
                                                                                                                                    Log.println(6, canonicalName5, sb7.toString());
                                                                                                                                } else {
                                                                                                                                    str24 = str17;
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                str24 = str17;
                                                                                                                                str23 = str;
                                                                                                                            }
                                                                                                                        }
                                                                                                                    } catch (Throwable th24) {
                                                                                                                        th = th24;
                                                                                                                        str2 = str17;
                                                                                                                        str3 = str25;
                                                                                                                        Result.Companion companion6222222 = Result.INSTANCE;
                                                                                                                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                                                                                        if (m1205exceptionOrNullimpl == null) {
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                                canonicalName4 = (hsStateHandler == null || (cls8 = hsStateHandler.getClass()) == null) ? null : cls8.getCanonicalName();
                                                                                                                if (canonicalName4 == null) {
                                                                                                                    canonicalName4 = "N/A";
                                                                                                                }
                                                                                                                matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName4);
                                                                                                                if (matcher4.find()) {
                                                                                                                }
                                                                                                                Unit unit72 = Unit.INSTANCE;
                                                                                                                if (canonicalName4.length() > 23) {
                                                                                                                    canonicalName4 = canonicalName4.substring(0, 23);
                                                                                                                    Intrinsics.checkNotNullExpressionValue(canonicalName4, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                                }
                                                                                                                sb6.append(canonicalName4);
                                                                                                                sb6.append(" - ");
                                                                                                                sb6.append("unable to retrieve hv_data state json from file");
                                                                                                                sb6.append(' ');
                                                                                                                if (m1205exceptionOrNullimpl3 == null) {
                                                                                                                }
                                                                                                                if (localizedMessage2 == null) {
                                                                                                                }
                                                                                                                sb6.append(str26);
                                                                                                                companion14.log(level4, sb6.toString());
                                                                                                                CoreExtsKt.isRelease();
                                                                                                                Result.Companion companion152 = Result.INSTANCE;
                                                                                                                Object invoke42 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                                                                                                str3 = str25;
                                                                                                                Intrinsics.checkNotNull(invoke42, str3);
                                                                                                                m1202constructorimpl6 = Result.m1202constructorimpl(((Application) invoke42).getPackageName());
                                                                                                                if (Result.m1208isFailureimpl(m1202constructorimpl6)) {
                                                                                                                }
                                                                                                                String str402 = (String) m1202constructorimpl6;
                                                                                                                if (CoreExtsKt.isDebug()) {
                                                                                                                }
                                                                                                            } else {
                                                                                                                obj3 = obj;
                                                                                                            }
                                                                                                            Object obj622222 = obj3;
                                                                                                            Intrinsics.checkNotNull(obj622222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                                            Triple triple22222 = (Triple) obj622222;
                                                                                                            hVError = (HVError) triple22222.component1();
                                                                                                            HVResponse hVResponse22222 = (HVResponse) triple22222.component2();
                                                                                                            Object obj722222 = (JSONObject) triple22222.component3();
                                                                                                            if (hVError == null) {
                                                                                                                throw new HSBridgeException(hVError, hVResponse22222);
                                                                                                            }
                                                                                                            if (hVResponse22222 == null && obj722222 == null) {
                                                                                                                str = str23;
                                                                                                                str2 = str24;
                                                                                                            }
                                                                                                            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(HyperKycData.WebviewData.class);
                                                                                                            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(HyperKycData.FaceData.class))) {
                                                                                                                HyperKycData.FaceData.Companion companion16 = HyperKycData.FaceData.INSTANCE;
                                                                                                                Gson gson = hsStateHandler.getGson();
                                                                                                                Intrinsics.checkNotNull(hVResponse22222);
                                                                                                                obj722222 = companion16.from$hyperkyc_release(gson, hVResponse22222);
                                                                                                            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(HyperKycData.DocData.class))) {
                                                                                                                HyperKycData.DocData.Companion companion17 = HyperKycData.DocData.INSTANCE;
                                                                                                                Gson gson2 = hsStateHandler.getGson();
                                                                                                                Intrinsics.checkNotNull(hVResponse22222);
                                                                                                                obj722222 = companion17.from$hyperkyc_release(gson2, "", hVResponse22222);
                                                                                                            } else if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                                                                                                                throw new NotImplementedError("An operation is not implemented: " + ("Not supported - " + HyperKycData.WebviewData.class.getCanonicalName()));
                                                                                                            }
                                                                                                            if (obj722222 != null) {
                                                                                                                HyperKycData.WebviewData webviewData = (HyperKycData.WebviewData) obj722222;
                                                                                                                MainVM.updateWebviewData$hyperkyc_release$default(getMainVM(), webViewUIState.getTag(), webviewData, false, 4, null);
                                                                                                                if (webviewData.getRawData$hyperkyc_release() != null) {
                                                                                                                    obj4 = Boolean.valueOf(getMainVM().flowForward());
                                                                                                                } else {
                                                                                                                    onBackPressed();
                                                                                                                    Unit unit9 = Unit.INSTANCE;
                                                                                                                    obj4 = Unit.INSTANCE;
                                                                                                                }
                                                                                                                obj5 = obj4;
                                                                                                                str = str23;
                                                                                                                str2 = str24;
                                                                                                            } else {
                                                                                                                throw new NullPointerException("null cannot be cast to non-null type co.hyperverge.hyperkyc.data.models.result.HyperKycData.WebviewData");
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                    str23 = str;
                                                                                                    obj2 = Result.m1202constructorimpl(Unit.INSTANCE);
                                                                                                    m1205exceptionOrNullimpl3 = Result.m1205exceptionOrNullimpl(obj2);
                                                                                                    if (m1205exceptionOrNullimpl3 != null) {
                                                                                                    }
                                                                                                    Object obj6222222 = obj3;
                                                                                                    Intrinsics.checkNotNull(obj6222222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                                    Triple triple222222 = (Triple) obj6222222;
                                                                                                    hVError = (HVError) triple222222.component1();
                                                                                                    HVResponse hVResponse222222 = (HVResponse) triple222222.component2();
                                                                                                    Object obj7222222 = (JSONObject) triple222222.component3();
                                                                                                    if (hVError == null) {
                                                                                                    }
                                                                                                }
                                                                                                String canonicalName8 = (hsStateHandler != null || (cls11 = hsStateHandler.getClass()) == null) ? null : cls11.getCanonicalName();
                                                                                                substringAfterLast$default2 = canonicalName8 != null ? "N/A" : canonicalName8;
                                                                                                matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default2);
                                                                                                if (matcher5.find()) {
                                                                                                }
                                                                                                Unit unit52 = Unit.INSTANCE;
                                                                                                if (substringAfterLast$default2.length() > 23) {
                                                                                                    substringAfterLast$default2 = substringAfterLast$default2.substring(0, 23);
                                                                                                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default2, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                }
                                                                                                sb.append(substringAfterLast$default2);
                                                                                                sb.append(" - ");
                                                                                                str30 = "retrieveState: hvData from file: " + obj;
                                                                                                if (str30 == null) {
                                                                                                }
                                                                                                sb.append(str30);
                                                                                                sb.append(' ');
                                                                                                sb.append("");
                                                                                                companion.log(level, sb.toString());
                                                                                                if (CoreExtsKt.isRelease()) {
                                                                                                }
                                                                                                str23 = str;
                                                                                                obj2 = Result.m1202constructorimpl(Unit.INSTANCE);
                                                                                                m1205exceptionOrNullimpl3 = Result.m1205exceptionOrNullimpl(obj2);
                                                                                                if (m1205exceptionOrNullimpl3 != null) {
                                                                                                }
                                                                                                Object obj62222222 = obj3;
                                                                                                Intrinsics.checkNotNull(obj62222222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                                Triple triple2222222 = (Triple) obj62222222;
                                                                                                hVError = (HVError) triple2222222.component1();
                                                                                                HVResponse hVResponse2222222 = (HVResponse) triple2222222.component2();
                                                                                                Object obj72222222 = (JSONObject) triple2222222.component3();
                                                                                                if (hVError == null) {
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                        str28 = str3;
                                                                                        str29 = str2;
                                                                                        if (hsStateHandler != null) {
                                                                                        }
                                                                                        if (canonicalName8 != null) {
                                                                                        }
                                                                                        matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default2);
                                                                                        if (matcher5.find()) {
                                                                                        }
                                                                                        Unit unit522 = Unit.INSTANCE;
                                                                                        if (substringAfterLast$default2.length() > 23) {
                                                                                        }
                                                                                        sb.append(substringAfterLast$default2);
                                                                                        sb.append(" - ");
                                                                                        str30 = "retrieveState: hvData from file: " + obj;
                                                                                        if (str30 == null) {
                                                                                        }
                                                                                        sb.append(str30);
                                                                                        sb.append(' ');
                                                                                        sb.append("");
                                                                                        companion.log(level, sb.toString());
                                                                                        if (CoreExtsKt.isRelease()) {
                                                                                        }
                                                                                        str23 = str;
                                                                                        obj2 = Result.m1202constructorimpl(Unit.INSTANCE);
                                                                                        m1205exceptionOrNullimpl3 = Result.m1205exceptionOrNullimpl(obj2);
                                                                                        if (m1205exceptionOrNullimpl3 != null) {
                                                                                        }
                                                                                        Object obj622222222 = obj3;
                                                                                        Intrinsics.checkNotNull(obj622222222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                        Triple triple22222222 = (Triple) obj622222222;
                                                                                        hVError = (HVError) triple22222222.component1();
                                                                                        HVResponse hVResponse22222222 = (HVResponse) triple22222222.component2();
                                                                                        Object obj722222222 = (JSONObject) triple22222222.component3();
                                                                                        if (hVError == null) {
                                                                                        }
                                                                                    } else {
                                                                                        str24 = str2;
                                                                                        String str41 = str;
                                                                                        try {
                                                                                            HyperLogger.Level level5 = HyperLogger.Level.DEBUG;
                                                                                            HyperLogger companion18 = HyperLogger.INSTANCE.getInstance();
                                                                                            StringBuilder sb8 = new StringBuilder();
                                                                                            str = str41;
                                                                                            try {
                                                                                                StackTraceElement[] stackTrace9 = new Throwable().getStackTrace();
                                                                                                Intrinsics.checkNotNullExpressionValue(stackTrace9, str24);
                                                                                                StackTraceElement stackTraceElement9 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace9);
                                                                                                try {
                                                                                                    if (stackTraceElement9 != null) {
                                                                                                        try {
                                                                                                            String className14 = stackTraceElement9.getClassName();
                                                                                                            if (className14 != null) {
                                                                                                                str20 = str3;
                                                                                                                str31 = "getInitialApplication";
                                                                                                                str17 = str24;
                                                                                                                try {
                                                                                                                    substringAfterLast$default3 = StringsKt.substringAfterLast$default(className14, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                                                                                    if (substringAfterLast$default3 != null) {
                                                                                                                        matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default3);
                                                                                                                        if (matcher6.find()) {
                                                                                                                            substringAfterLast$default3 = matcher6.replaceAll("");
                                                                                                                            Intrinsics.checkNotNullExpressionValue(substringAfterLast$default3, "replaceAll(\"\")");
                                                                                                                        }
                                                                                                                        Unit unit10 = Unit.INSTANCE;
                                                                                                                        if (substringAfterLast$default3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                                                            substringAfterLast$default3 = substringAfterLast$default3.substring(0, 23);
                                                                                                                            Intrinsics.checkNotNullExpressionValue(substringAfterLast$default3, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                                        }
                                                                                                                        sb8.append(substringAfterLast$default3);
                                                                                                                        sb8.append(" - ");
                                                                                                                        str32 = "retrieveState: state targetFile exists : " + file2.exists();
                                                                                                                        if (str32 == null) {
                                                                                                                            str32 = "null ";
                                                                                                                        }
                                                                                                                        sb8.append(str32);
                                                                                                                        sb8.append(' ');
                                                                                                                        sb8.append("");
                                                                                                                        companion18.log(level5, sb8.toString());
                                                                                                                        if (CoreExtsKt.isRelease()) {
                                                                                                                            str33 = str31;
                                                                                                                            str2 = str17;
                                                                                                                            str3 = str20;
                                                                                                                        } else {
                                                                                                                            try {
                                                                                                                                Result.Companion companion19 = Result.INSTANCE;
                                                                                                                                str33 = str31;
                                                                                                                                try {
                                                                                                                                    Object invoke5 = Class.forName("android.app.AppGlobals").getMethod(str33, new Class[0]).invoke(null, new Object[0]);
                                                                                                                                    str3 = str20;
                                                                                                                                    try {
                                                                                                                                        Intrinsics.checkNotNull(invoke5, str3);
                                                                                                                                        m1202constructorimpl8 = Result.m1202constructorimpl(((Application) invoke5).getPackageName());
                                                                                                                                    } catch (Throwable th25) {
                                                                                                                                        th = th25;
                                                                                                                                        Throwable th26 = th;
                                                                                                                                        Result.Companion companion20 = Result.INSTANCE;
                                                                                                                                        m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th26));
                                                                                                                                        if (Result.m1208isFailureimpl(m1202constructorimpl8)) {
                                                                                                                                        }
                                                                                                                                        String str42 = (String) m1202constructorimpl8;
                                                                                                                                        if (CoreExtsKt.isDebug()) {
                                                                                                                                        }
                                                                                                                                        str2 = str17;
                                                                                                                                        throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                                                                                                                    }
                                                                                                                                } catch (Throwable th27) {
                                                                                                                                    th = th27;
                                                                                                                                    str3 = str20;
                                                                                                                                    Throwable th262 = th;
                                                                                                                                    Result.Companion companion202 = Result.INSTANCE;
                                                                                                                                    m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th262));
                                                                                                                                    if (Result.m1208isFailureimpl(m1202constructorimpl8)) {
                                                                                                                                    }
                                                                                                                                    String str422 = (String) m1202constructorimpl8;
                                                                                                                                    if (CoreExtsKt.isDebug()) {
                                                                                                                                    }
                                                                                                                                    str2 = str17;
                                                                                                                                    throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                                                                                                                }
                                                                                                                            } catch (Throwable th28) {
                                                                                                                                th = th28;
                                                                                                                                str33 = str31;
                                                                                                                            }
                                                                                                                            if (Result.m1208isFailureimpl(m1202constructorimpl8)) {
                                                                                                                                m1202constructorimpl8 = "";
                                                                                                                            }
                                                                                                                            String str4222 = (String) m1202constructorimpl8;
                                                                                                                            if (CoreExtsKt.isDebug()) {
                                                                                                                                try {
                                                                                                                                    Intrinsics.checkNotNullExpressionValue(str4222, str);
                                                                                                                                    str = str;
                                                                                                                                    if (StringsKt.contains$default((CharSequence) str4222, charSequence, false, 2, (Object) null)) {
                                                                                                                                        StackTraceElement[] stackTrace10 = new Throwable().getStackTrace();
                                                                                                                                        str2 = str17;
                                                                                                                                        Intrinsics.checkNotNullExpressionValue(stackTrace10, str2);
                                                                                                                                        StackTraceElement stackTraceElement10 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace10);
                                                                                                                                        if (stackTraceElement10 == null || (className11 = stackTraceElement10.getClassName()) == null || (canonicalName7 = StringsKt.substringAfterLast$default(className11, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                                                                                                                            canonicalName7 = (hsStateHandler == null || (cls12 = hsStateHandler.getClass()) == null) ? null : cls12.getCanonicalName();
                                                                                                                                            if (canonicalName7 == null) {
                                                                                                                                                canonicalName7 = "N/A";
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        Matcher matcher11 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName7);
                                                                                                                                        if (matcher11.find()) {
                                                                                                                                            canonicalName7 = matcher11.replaceAll("");
                                                                                                                                            Intrinsics.checkNotNullExpressionValue(canonicalName7, "replaceAll(\"\")");
                                                                                                                                        }
                                                                                                                                        Unit unit11 = Unit.INSTANCE;
                                                                                                                                        if (canonicalName7.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                                                                            canonicalName7 = canonicalName7.substring(0, 23);
                                                                                                                                            Intrinsics.checkNotNullExpressionValue(canonicalName7, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                                                        }
                                                                                                                                        StringBuilder sb9 = new StringBuilder();
                                                                                                                                        String str43 = "retrieveState: state targetFile exists : " + file2.exists();
                                                                                                                                        if (str43 == null) {
                                                                                                                                            str43 = "null ";
                                                                                                                                        }
                                                                                                                                        sb9.append(str43);
                                                                                                                                        sb9.append(' ');
                                                                                                                                        sb9.append("");
                                                                                                                                        Log.println(3, canonicalName7, sb9.toString());
                                                                                                                                    }
                                                                                                                                } catch (Throwable th29) {
                                                                                                                                    th = th29;
                                                                                                                                    str = str;
                                                                                                                                    str2 = str17;
                                                                                                                                    th = th;
                                                                                                                                    Result.Companion companion62222222 = Result.INSTANCE;
                                                                                                                                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                                                                                                    if (m1205exceptionOrNullimpl == null) {
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                            str2 = str17;
                                                                                                                        }
                                                                                                                    }
                                                                                                                    String canonicalName9 = (hsStateHandler != null || (cls13 = hsStateHandler.getClass()) == null) ? null : cls13.getCanonicalName();
                                                                                                                    substringAfterLast$default3 = canonicalName9 != null ? "N/A" : canonicalName9;
                                                                                                                    matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default3);
                                                                                                                    if (matcher6.find()) {
                                                                                                                    }
                                                                                                                    Unit unit102 = Unit.INSTANCE;
                                                                                                                    if (substringAfterLast$default3.length() > 23) {
                                                                                                                        substringAfterLast$default3 = substringAfterLast$default3.substring(0, 23);
                                                                                                                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default3, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                                    }
                                                                                                                    sb8.append(substringAfterLast$default3);
                                                                                                                    sb8.append(" - ");
                                                                                                                    str32 = "retrieveState: state targetFile exists : " + file2.exists();
                                                                                                                    if (str32 == null) {
                                                                                                                    }
                                                                                                                    sb8.append(str32);
                                                                                                                    sb8.append(' ');
                                                                                                                    sb8.append("");
                                                                                                                    companion18.log(level5, sb8.toString());
                                                                                                                    if (CoreExtsKt.isRelease()) {
                                                                                                                    }
                                                                                                                } catch (Throwable th30) {
                                                                                                                    th = th30;
                                                                                                                    str33 = str31;
                                                                                                                    str2 = str17;
                                                                                                                    str3 = str20;
                                                                                                                    Result.Companion companion622222222 = Result.INSTANCE;
                                                                                                                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                                                                                    if (m1205exceptionOrNullimpl == null) {
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        } catch (Throwable th31) {
                                                                                                            th = th31;
                                                                                                            str2 = str24;
                                                                                                            Result.Companion companion6222222222 = Result.INSTANCE;
                                                                                                            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                                                                            if (m1205exceptionOrNullimpl == null) {
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                    matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default3);
                                                                                                    if (matcher6.find()) {
                                                                                                    }
                                                                                                    Unit unit1022 = Unit.INSTANCE;
                                                                                                    if (substringAfterLast$default3.length() > 23) {
                                                                                                    }
                                                                                                    sb8.append(substringAfterLast$default3);
                                                                                                    sb8.append(" - ");
                                                                                                    str32 = "retrieveState: state targetFile exists : " + file2.exists();
                                                                                                    if (str32 == null) {
                                                                                                    }
                                                                                                    sb8.append(str32);
                                                                                                    sb8.append(' ');
                                                                                                    sb8.append("");
                                                                                                    companion18.log(level5, sb8.toString());
                                                                                                    if (CoreExtsKt.isRelease()) {
                                                                                                    }
                                                                                                } catch (Throwable th32) {
                                                                                                    th = th32;
                                                                                                    str33 = str31;
                                                                                                    str2 = str17;
                                                                                                    str3 = str20;
                                                                                                    th = th;
                                                                                                    Result.Companion companion62222222222 = Result.INSTANCE;
                                                                                                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                                                                    if (m1205exceptionOrNullimpl == null) {
                                                                                                    }
                                                                                                }
                                                                                                str20 = str3;
                                                                                                str31 = "getInitialApplication";
                                                                                                str17 = str24;
                                                                                                if (hsStateHandler != null) {
                                                                                                }
                                                                                                if (canonicalName9 != null) {
                                                                                                }
                                                                                            } catch (Throwable th33) {
                                                                                                th = th33;
                                                                                                str2 = str24;
                                                                                                th = th;
                                                                                                Result.Companion companion622222222222 = Result.INSTANCE;
                                                                                                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                                                                if (m1205exceptionOrNullimpl == null) {
                                                                                                }
                                                                                            }
                                                                                        } catch (Throwable th34) {
                                                                                            th = th34;
                                                                                            str = str41;
                                                                                        }
                                                                                    }
                                                                                    throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                                                                }
                                                                                String canonicalName10 = (hsStateHandler != null || (cls6 = hsStateHandler.getClass()) == null) ? null : cls6.getCanonicalName();
                                                                                str21 = canonicalName10 == null ? "N/A" : canonicalName10;
                                                                                matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str21);
                                                                                if (matcher3.find()) {
                                                                                }
                                                                                Unit unit32 = Unit.INSTANCE;
                                                                                if (str21.length() > 23) {
                                                                                    str21 = str21.substring(0, 23);
                                                                                    Intrinsics.checkNotNullExpressionValue(str21, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                }
                                                                                sb3.append(str21);
                                                                                sb3.append(" - ");
                                                                                str22 = "retrieveState: state targetFile exists : " + file2.exists();
                                                                                if (str22 == null) {
                                                                                }
                                                                                sb3.append(str22);
                                                                                sb3.append(' ');
                                                                                sb3.append("");
                                                                                companion7.log(level3, sb3.toString());
                                                                                if (CoreExtsKt.isRelease()) {
                                                                                }
                                                                                if (file2.exists()) {
                                                                                }
                                                                                throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                                                            } catch (Throwable th35) {
                                                                                th = th35;
                                                                                str2 = str17;
                                                                                str3 = str20;
                                                                                Result.Companion companion6222222222222 = Result.INSTANCE;
                                                                                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                                                if (m1205exceptionOrNullimpl == null) {
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    str = str18;
                                                                    str20 = "null cannot be cast to non-null type android.app.Application";
                                                                    str17 = str19;
                                                                    if (hsStateHandler != null) {
                                                                    }
                                                                    if (canonicalName10 == null) {
                                                                    }
                                                                    matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str21);
                                                                    if (matcher3.find()) {
                                                                    }
                                                                    Unit unit322 = Unit.INSTANCE;
                                                                    if (str21.length() > 23) {
                                                                    }
                                                                    sb3.append(str21);
                                                                    sb3.append(" - ");
                                                                    str22 = "retrieveState: state targetFile exists : " + file2.exists();
                                                                    if (str22 == null) {
                                                                    }
                                                                    sb3.append(str22);
                                                                    sb3.append(' ');
                                                                    sb3.append("");
                                                                    companion7.log(level3, sb3.toString());
                                                                    if (CoreExtsKt.isRelease()) {
                                                                    }
                                                                    if (file2.exists()) {
                                                                    }
                                                                    throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                                                } catch (Throwable th36) {
                                                                    th = th36;
                                                                    str = str18;
                                                                    str3 = "null cannot be cast to non-null type android.app.Application";
                                                                    str2 = str17;
                                                                    th = th;
                                                                    Result.Companion companion62222222222222 = Result.INSTANCE;
                                                                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                                    if (m1205exceptionOrNullimpl == null) {
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        str19 = str17;
                                                        str18 = str;
                                                        file2 = new File(hsStateHandler.getResultDataDir(), tag + ".json");
                                                        if (file2.exists()) {
                                                        }
                                                        HyperLogger.Level level32 = HyperLogger.Level.DEBUG;
                                                        HyperLogger companion72 = HyperLogger.INSTANCE.getInstance();
                                                        StringBuilder sb32 = new StringBuilder();
                                                        StackTraceElement[] stackTrace32 = new Throwable().getStackTrace();
                                                        Intrinsics.checkNotNullExpressionValue(stackTrace32, str19);
                                                        stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace32);
                                                        if (stackTraceElement != null) {
                                                        }
                                                        str = str18;
                                                        str20 = "null cannot be cast to non-null type android.app.Application";
                                                        str17 = str19;
                                                        if (hsStateHandler != null) {
                                                        }
                                                        if (canonicalName10 == null) {
                                                        }
                                                        matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str21);
                                                        if (matcher3.find()) {
                                                        }
                                                        Unit unit3222 = Unit.INSTANCE;
                                                        if (str21.length() > 23) {
                                                        }
                                                        sb32.append(str21);
                                                        sb32.append(" - ");
                                                        str22 = "retrieveState: state targetFile exists : " + file2.exists();
                                                        if (str22 == null) {
                                                        }
                                                        sb32.append(str22);
                                                        sb32.append(' ');
                                                        sb32.append("");
                                                        companion72.log(level32, sb32.toString());
                                                        if (CoreExtsKt.isRelease()) {
                                                        }
                                                        if (file2.exists()) {
                                                        }
                                                        throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                                    }
                                                    String canonicalName11 = (hsStateHandler != null || (cls14 = hsStateHandler.getClass()) == null) ? null : cls14.getCanonicalName();
                                                    substringAfterLast$default = canonicalName11 != null ? "N/A" : canonicalName11;
                                                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                                                    if (matcher2.find()) {
                                                    }
                                                    Unit unit12 = Unit.INSTANCE;
                                                    if (substringAfterLast$default.length() > 23) {
                                                        substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                                                        Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "this as java.lang.String…ing(startIndex, endIndex)");
                                                    }
                                                    sb2.append(substringAfterLast$default);
                                                    sb2.append(" - ");
                                                    sb2.append("retrieveState() called");
                                                    sb2.append(' ');
                                                    sb2.append("");
                                                    companion3.log(level2, sb2.toString());
                                                    if (!CoreExtsKt.isRelease()) {
                                                    }
                                                    str19 = str17;
                                                    str18 = str;
                                                    file2 = new File(hsStateHandler.getResultDataDir(), tag + ".json");
                                                    if (file2.exists()) {
                                                    }
                                                    HyperLogger.Level level322 = HyperLogger.Level.DEBUG;
                                                    HyperLogger companion722 = HyperLogger.INSTANCE.getInstance();
                                                    StringBuilder sb322 = new StringBuilder();
                                                    StackTraceElement[] stackTrace322 = new Throwable().getStackTrace();
                                                    Intrinsics.checkNotNullExpressionValue(stackTrace322, str19);
                                                    stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace322);
                                                    if (stackTraceElement != null) {
                                                    }
                                                    str = str18;
                                                    str20 = "null cannot be cast to non-null type android.app.Application";
                                                    str17 = str19;
                                                    if (hsStateHandler != null) {
                                                    }
                                                    if (canonicalName10 == null) {
                                                    }
                                                    matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str21);
                                                    if (matcher3.find()) {
                                                    }
                                                    Unit unit32222 = Unit.INSTANCE;
                                                    if (str21.length() > 23) {
                                                    }
                                                    sb322.append(str21);
                                                    sb322.append(" - ");
                                                    str22 = "retrieveState: state targetFile exists : " + file2.exists();
                                                    if (str22 == null) {
                                                    }
                                                    sb322.append(str22);
                                                    sb322.append(' ');
                                                    sb322.append("");
                                                    companion722.log(level322, sb322.toString());
                                                    if (CoreExtsKt.isRelease()) {
                                                    }
                                                    if (file2.exists()) {
                                                    }
                                                    throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                                } catch (Throwable th37) {
                                                    th = th37;
                                                    str3 = "null cannot be cast to non-null type android.app.Application";
                                                    str2 = str17;
                                                    Result.Companion companion622222222222222 = Result.INSTANCE;
                                                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                                    if (m1205exceptionOrNullimpl == null) {
                                                    }
                                                }
                                            }
                                        } catch (Throwable th38) {
                                            charSequence = "co.hyperverge";
                                            th = th38;
                                            str3 = "null cannot be cast to non-null type android.app.Application";
                                            str = "packageName";
                                            str2 = "Throwable().stackTrace";
                                            Result.Companion companion6222222222222222 = Result.INSTANCE;
                                            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                            if (m1205exceptionOrNullimpl == null) {
                                            }
                                        }
                                    }
                                    matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str21);
                                    if (matcher3.find()) {
                                    }
                                    Unit unit322222 = Unit.INSTANCE;
                                    if (str21.length() > 23) {
                                    }
                                    sb322.append(str21);
                                    sb322.append(" - ");
                                    str22 = "retrieveState: state targetFile exists : " + file2.exists();
                                    if (str22 == null) {
                                    }
                                    sb322.append(str22);
                                    sb322.append(' ');
                                    sb322.append("");
                                    companion722.log(level322, sb322.toString());
                                    if (CoreExtsKt.isRelease()) {
                                    }
                                    if (file2.exists()) {
                                    }
                                    throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                } catch (Throwable th39) {
                                    th = th39;
                                }
                                file2 = new File(hsStateHandler.getResultDataDir(), tag + ".json");
                                if (file2.exists()) {
                                }
                                HyperLogger.Level level3222 = HyperLogger.Level.DEBUG;
                                HyperLogger companion7222 = HyperLogger.INSTANCE.getInstance();
                                StringBuilder sb3222 = new StringBuilder();
                                StackTraceElement[] stackTrace3222 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace3222, str19);
                                stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3222);
                                if (stackTraceElement != null) {
                                }
                                str = str18;
                                str20 = "null cannot be cast to non-null type android.app.Application";
                                str17 = str19;
                                if (hsStateHandler != null) {
                                }
                                if (canonicalName10 == null) {
                                }
                            } catch (Throwable th40) {
                                th = th40;
                                str = str18;
                                str2 = str19;
                                str3 = "null cannot be cast to non-null type android.app.Application";
                                th = th;
                                Result.Companion companion62222222222222222 = Result.INSTANCE;
                                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                                if (m1205exceptionOrNullimpl == null) {
                                }
                            }
                            matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                            if (matcher2.find()) {
                            }
                            Unit unit122 = Unit.INSTANCE;
                            if (substringAfterLast$default.length() > 23) {
                            }
                            sb2.append(substringAfterLast$default);
                            sb2.append(" - ");
                            sb2.append("retrieveState() called");
                            sb2.append(' ');
                            sb2.append("");
                            companion3.log(level2, sb2.toString());
                            if (!CoreExtsKt.isRelease()) {
                            }
                            str19 = str17;
                            str18 = str;
                        } catch (Throwable th41) {
                            th = th41;
                            str3 = "null cannot be cast to non-null type android.app.Application";
                            str2 = str17;
                            th = th;
                            Result.Companion companion622222222222222222 = Result.INSTANCE;
                            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                            if (m1205exceptionOrNullimpl == null) {
                            }
                        }
                        charSequence = "co.hyperverge";
                        str = "packageName";
                        str17 = "Throwable().stackTrace";
                        if (hsStateHandler != null) {
                        }
                        if (canonicalName11 != null) {
                        }
                    } catch (Throwable th42) {
                        th = th42;
                        charSequence = "co.hyperverge";
                        str = "packageName";
                        str2 = "Throwable().stackTrace";
                    }
                } else {
                    charSequence = "co.hyperverge";
                    str = "packageName";
                    str2 = "Throwable().stackTrace";
                    str3 = "null cannot be cast to non-null type android.app.Application";
                    try {
                        this.browserLauncher.launch(intent);
                        obj5 = Unit.INSTANCE;
                    } catch (Throwable th43) {
                        th = th43;
                        th = th;
                        Result.Companion companion6222222222222222222 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                        if (m1205exceptionOrNullimpl == null) {
                        }
                    }
                }
                m1202constructorimpl = Result.m1202constructorimpl(obj5);
            } catch (Throwable th44) {
                th = th44;
                charSequence = "co.hyperverge";
                str = "packageName";
                str2 = "Throwable().stackTrace";
                str3 = "null cannot be cast to non-null type android.app.Application";
            }
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
            if (m1205exceptionOrNullimpl == null) {
                HSStateHandler hsStateHandler2 = getMainVM().getHsStateHandler();
                if (isDestroyed()) {
                    boolean z = m1205exceptionOrNullimpl instanceof HSBridgeException;
                    String json = hsStateHandler2.getGson().toJson(m1205exceptionOrNullimpl, z ? HSBridgeException.class : Throwable.class);
                    if (z) {
                        hsExceptionFile = hsStateHandler2.getHsBridgeExceptionFile();
                    } else {
                        hsExceptionFile = m1205exceptionOrNullimpl instanceof Throwable ? hsStateHandler2.getHsExceptionFile() : hsStateHandler2.getHsResponseFile();
                    }
                    HyperLogger.Level level6 = HyperLogger.Level.DEBUG;
                    HyperLogger companion21 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb10 = new StringBuilder();
                    StackTraceElement[] stackTrace11 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace11, str2);
                    StackTraceElement stackTraceElement11 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace11);
                    if (stackTraceElement11 == null || (className4 = stackTraceElement11.getClassName()) == null) {
                        str4 = str2;
                        str5 = json;
                        file = hsExceptionFile;
                    } else {
                        str4 = str2;
                        str5 = json;
                        file = hsExceptionFile;
                        str6 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    str6 = (hsStateHandler2 == null || (cls4 = hsStateHandler2.getClass()) == null) ? null : cls4.getCanonicalName();
                    if (str6 == null) {
                        str6 = "N/A";
                    }
                    Matcher matcher12 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                    if (matcher12.find()) {
                        str6 = matcher12.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
                    }
                    Unit unit13 = Unit.INSTANCE;
                    if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str6 = str6.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb10.append(str6);
                    sb10.append(" - ");
                    String str44 = "storeState() called for " + Throwable.class.getCanonicalName();
                    if (str44 == null) {
                        str44 = "null ";
                    }
                    sb10.append(str44);
                    sb10.append(' ');
                    sb10.append("");
                    companion21.log(level6, sb10.toString());
                    if (!CoreExtsKt.isRelease()) {
                        try {
                            Result.Companion companion22 = Result.INSTANCE;
                            Object invoke6 = Class.forName("android.app.AppGlobals").getMethod(str33, new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke6, str3);
                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke6).getPackageName());
                        } catch (Throwable th45) {
                            Result.Companion companion23 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th45));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                            m1202constructorimpl2 = "";
                        }
                        String str45 = (String) m1202constructorimpl2;
                        if (CoreExtsKt.isDebug()) {
                            str7 = str;
                            Intrinsics.checkNotNullExpressionValue(str45, str7);
                            if (StringsKt.contains$default((CharSequence) str45, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace12 = new Throwable().getStackTrace();
                                str8 = str4;
                                Intrinsics.checkNotNullExpressionValue(stackTrace12, str8);
                                StackTraceElement stackTraceElement12 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace12);
                                if (stackTraceElement12 == null || (className = stackTraceElement12.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    canonicalName = (hsStateHandler2 == null || (cls = hsStateHandler2.getClass()) == null) ? null : cls.getCanonicalName();
                                    if (canonicalName == null) {
                                        canonicalName = "N/A";
                                    }
                                }
                                Matcher matcher13 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
                                if (matcher13.find()) {
                                    canonicalName = matcher13.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
                                }
                                Unit unit14 = Unit.INSTANCE;
                                if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    canonicalName = canonicalName.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb11 = new StringBuilder();
                                String str46 = "storeState() called for " + Throwable.class.getCanonicalName();
                                if (str46 == null) {
                                    str46 = "null ";
                                }
                                sb11.append(str46);
                                sb11.append(' ');
                                sb11.append("");
                                Log.println(3, canonicalName, sb11.toString());
                                Result.Companion companion24 = Result.INSTANCE;
                                String jsonData = str5;
                                Intrinsics.checkNotNullExpressionValue(jsonData, "jsonData");
                                byte[] bytes = jsonData.getBytes(Charsets.UTF_8);
                                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                                FilesKt.writeBytes(file, bytes);
                                Object m1202constructorimpl9 = Result.m1202constructorimpl(Unit.INSTANCE);
                                m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl9);
                                if (m1205exceptionOrNullimpl2 != null) {
                                    HyperLogger.Level level7 = HyperLogger.Level.ERROR;
                                    HyperLogger companion25 = HyperLogger.INSTANCE.getInstance();
                                    StringBuilder sb12 = new StringBuilder();
                                    StackTraceElement[] stackTrace13 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace13, str8);
                                    StackTraceElement stackTraceElement13 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace13);
                                    if (stackTraceElement13 == null || (className3 = stackTraceElement13.getClassName()) == null) {
                                        str9 = str7;
                                        str10 = str8;
                                        str11 = str3;
                                    } else {
                                        str9 = str7;
                                        str10 = str8;
                                        str11 = str3;
                                        str12 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    }
                                    String canonicalName12 = (hsStateHandler2 == null || (cls3 = hsStateHandler2.getClass()) == null) ? null : cls3.getCanonicalName();
                                    str12 = canonicalName12 == null ? "N/A" : canonicalName12;
                                    Matcher matcher14 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str12);
                                    if (matcher14.find()) {
                                        str12 = matcher14.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str12, "replaceAll(\"\")");
                                    }
                                    Unit unit15 = Unit.INSTANCE;
                                    if (str12.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str12 = str12.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str12, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    sb12.append(str12);
                                    sb12.append(" - ");
                                    String str47 = "failed saving " + Throwable.class.getCanonicalName() + " state json to file";
                                    if (str47 == null) {
                                        str47 = "null ";
                                    }
                                    sb12.append(str47);
                                    sb12.append(' ');
                                    String localizedMessage4 = m1205exceptionOrNullimpl2 != null ? m1205exceptionOrNullimpl2.getLocalizedMessage() : null;
                                    if (localizedMessage4 != null) {
                                        str13 = '\n' + localizedMessage4;
                                    } else {
                                        str13 = "";
                                    }
                                    sb12.append(str13);
                                    companion25.log(level7, sb12.toString());
                                    CoreExtsKt.isRelease();
                                    try {
                                        Result.Companion companion26 = Result.INSTANCE;
                                        Object invoke7 = Class.forName("android.app.AppGlobals").getMethod(str33, new Class[0]).invoke(null, new Object[0]);
                                        Intrinsics.checkNotNull(invoke7, str11);
                                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke7).getPackageName());
                                    } catch (Throwable th46) {
                                        Result.Companion companion27 = Result.INSTANCE;
                                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th46));
                                    }
                                    if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                        m1202constructorimpl3 = "";
                                    }
                                    String str48 = (String) m1202constructorimpl3;
                                    if (CoreExtsKt.isDebug()) {
                                        Intrinsics.checkNotNullExpressionValue(str48, str9);
                                        if (StringsKt.contains$default((CharSequence) str48, charSequence, false, 2, (Object) null)) {
                                            StackTraceElement[] stackTrace14 = new Throwable().getStackTrace();
                                            Intrinsics.checkNotNullExpressionValue(stackTrace14, str10);
                                            StackTraceElement stackTraceElement14 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace14);
                                            if (stackTraceElement14 == null || (className2 = stackTraceElement14.getClassName()) == null) {
                                                str14 = null;
                                            } else {
                                                str14 = null;
                                                str15 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                            }
                                            str15 = (hsStateHandler2 == null || (cls2 = hsStateHandler2.getClass()) == null) ? str14 : cls2.getCanonicalName();
                                            if (str15 == null) {
                                                str16 = "N/A";
                                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str16);
                                                if (matcher.find()) {
                                                    str16 = matcher.replaceAll("");
                                                    Intrinsics.checkNotNullExpressionValue(str16, "replaceAll(\"\")");
                                                }
                                                Unit unit16 = Unit.INSTANCE;
                                                if (str16.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                    str16 = str16.substring(0, 23);
                                                    Intrinsics.checkNotNullExpressionValue(str16, "this as java.lang.String…ing(startIndex, endIndex)");
                                                }
                                                StringBuilder sb13 = new StringBuilder();
                                                String str49 = "failed saving " + Throwable.class.getCanonicalName() + " state json to file";
                                                sb13.append(str49 != null ? "null " : str49);
                                                sb13.append(' ');
                                                localizedMessage = m1205exceptionOrNullimpl2 == null ? m1205exceptionOrNullimpl2.getLocalizedMessage() : str14;
                                                if (localizedMessage != null) {
                                                    str34 = '\n' + localizedMessage;
                                                }
                                                sb13.append(str34);
                                                Log.println(6, str16, sb13.toString());
                                            }
                                            str16 = str15;
                                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str16);
                                            if (matcher.find()) {
                                            }
                                            Unit unit162 = Unit.INSTANCE;
                                            if (str16.length() > 23) {
                                                str16 = str16.substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(str16, "this as java.lang.String…ing(startIndex, endIndex)");
                                            }
                                            StringBuilder sb132 = new StringBuilder();
                                            String str492 = "failed saving " + Throwable.class.getCanonicalName() + " state json to file";
                                            sb132.append(str492 != null ? "null " : str492);
                                            sb132.append(' ');
                                            if (m1205exceptionOrNullimpl2 == null) {
                                            }
                                            if (localizedMessage != null) {
                                            }
                                            sb132.append(str34);
                                            Log.println(6, str16, sb132.toString());
                                        }
                                    }
                                }
                                th2 = m1205exceptionOrNullimpl;
                            }
                            str8 = str4;
                            Result.Companion companion242 = Result.INSTANCE;
                            String jsonData2 = str5;
                            Intrinsics.checkNotNullExpressionValue(jsonData2, "jsonData");
                            byte[] bytes2 = jsonData2.getBytes(Charsets.UTF_8);
                            Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
                            FilesKt.writeBytes(file, bytes2);
                            Object m1202constructorimpl92 = Result.m1202constructorimpl(Unit.INSTANCE);
                            m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl92);
                            if (m1205exceptionOrNullimpl2 != null) {
                            }
                            th2 = m1205exceptionOrNullimpl;
                        }
                    }
                    str7 = str;
                    str8 = str4;
                    Result.Companion companion2422 = Result.INSTANCE;
                    String jsonData22 = str5;
                    Intrinsics.checkNotNullExpressionValue(jsonData22, "jsonData");
                    byte[] bytes22 = jsonData22.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes22, "this as java.lang.String).getBytes(charset)");
                    FilesKt.writeBytes(file, bytes22);
                    Object m1202constructorimpl922 = Result.m1202constructorimpl(Unit.INSTANCE);
                    m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl922);
                    if (m1205exceptionOrNullimpl2 != null) {
                    }
                    th2 = m1205exceptionOrNullimpl;
                } else {
                    th2 = m1205exceptionOrNullimpl;
                }
                if (th2 instanceof HSBridgeException) {
                    HVError hvError = ((HSBridgeException) th2).getHvError();
                    if (hvError != null && hvError.getErrorCode() == 117) {
                        getMainVM().flowBack();
                        return;
                    }
                }
                BaseActivity.finishWithResult$default(this, "error", null, 104, th2.getMessage(), false, false, 50, null);
                return;
            }
            return;
        }
        HKMainActivity hKMainActivity2 = this;
        WebViewFragment webViewFragment = new WebViewFragment();
        Pair[] pairArr = new Pair[7];
        pairArr[0] = TuplesKt.to("moduleId", webViewUIState.getTag());
        pairArr[1] = TuplesKt.to(WebViewFragment.ARG_SUB_TYPE, webViewUIState.getSubType());
        pairArr[2] = TuplesKt.to("url", webViewUIState.getUrl());
        pairArr[3] = TuplesKt.to("data", webViewUIState.getData());
        pairArr[4] = TuplesKt.to("showBackButton", Boolean.valueOf(webViewUIState.getShowBackButton()));
        pairArr[5] = TuplesKt.to(WebViewFragment.ARG_RELOAD_ON_REDIRECT_FAILURE, Boolean.valueOf(webViewUIState.getReloadOnRedirectFailure()));
        Map<String, Object> textConfigs = webViewUIState.getTextConfigs();
        if (textConfigs == null || (emptyMap = MapsKt.toMap(textConfigs)) == null) {
            emptyMap = MapsKt.emptyMap();
        }
        pairArr[6] = TuplesKt.to("textConfigs", emptyMap);
        ActivityExtsKt.replaceContent$default(hKMainActivity2, webViewFragment, BundleKt.bundleOf(pairArr), false, null, 0, 28, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: startDocFlow-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m412startDocFlowgIAlus(WorkflowUIState.DocCapture docCapture, Continuation<? super Result<Unit>> continuation) {
        HKMainActivity$startDocFlow$1 hKMainActivity$startDocFlow$1;
        int i;
        if (continuation instanceof HKMainActivity$startDocFlow$1) {
            hKMainActivity$startDocFlow$1 = (HKMainActivity$startDocFlow$1) continuation;
            if ((hKMainActivity$startDocFlow$1.label & Integer.MIN_VALUE) != 0) {
                hKMainActivity$startDocFlow$1.label -= Integer.MIN_VALUE;
                Object obj = hKMainActivity$startDocFlow$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = hKMainActivity$startDocFlow$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HKMainActivity$startDocFlow$2 hKMainActivity$startDocFlow$2 = new HKMainActivity$startDocFlow$2(this, docCapture, null);
                    hKMainActivity$startDocFlow$1.label = 1;
                    obj = CoroutineScopeKt.coroutineScope(hKMainActivity$startDocFlow$2, hKMainActivity$startDocFlow$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return ((Result) obj).getValue();
            }
        }
        hKMainActivity$startDocFlow$1 = new HKMainActivity$startDocFlow$1(this, continuation);
        Object obj2 = hKMainActivity$startDocFlow$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = hKMainActivity$startDocFlow$1.label;
        if (i != 0) {
        }
        return ((Result) obj2).getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: startBarcodeFlow-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m411startBarcodeFlowgIAlus(WorkflowUIState.BarcodeCapture barcodeCapture, Continuation<? super Result<Unit>> continuation) {
        HKMainActivity$startBarcodeFlow$1 hKMainActivity$startBarcodeFlow$1;
        int i;
        if (continuation instanceof HKMainActivity$startBarcodeFlow$1) {
            hKMainActivity$startBarcodeFlow$1 = (HKMainActivity$startBarcodeFlow$1) continuation;
            if ((hKMainActivity$startBarcodeFlow$1.label & Integer.MIN_VALUE) != 0) {
                hKMainActivity$startBarcodeFlow$1.label -= Integer.MIN_VALUE;
                Object obj = hKMainActivity$startBarcodeFlow$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = hKMainActivity$startBarcodeFlow$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HKMainActivity$startBarcodeFlow$2 hKMainActivity$startBarcodeFlow$2 = new HKMainActivity$startBarcodeFlow$2(this, barcodeCapture, null);
                    hKMainActivity$startBarcodeFlow$1.label = 1;
                    obj = CoroutineScopeKt.coroutineScope(hKMainActivity$startBarcodeFlow$2, hKMainActivity$startBarcodeFlow$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return ((Result) obj).getValue();
            }
        }
        hKMainActivity$startBarcodeFlow$1 = new HKMainActivity$startBarcodeFlow$1(this, continuation);
        Object obj2 = hKMainActivity$startBarcodeFlow$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = hKMainActivity$startBarcodeFlow$1.label;
        if (i != 0) {
        }
        return ((Result) obj2).getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: startFaceFlow-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m413startFaceFlowgIAlus(WorkflowUIState.FaceCapture faceCapture, Continuation<? super Result<Unit>> continuation) {
        HKMainActivity$startFaceFlow$1 hKMainActivity$startFaceFlow$1;
        int i;
        if (continuation instanceof HKMainActivity$startFaceFlow$1) {
            hKMainActivity$startFaceFlow$1 = (HKMainActivity$startFaceFlow$1) continuation;
            if ((hKMainActivity$startFaceFlow$1.label & Integer.MIN_VALUE) != 0) {
                hKMainActivity$startFaceFlow$1.label -= Integer.MIN_VALUE;
                Object obj = hKMainActivity$startFaceFlow$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = hKMainActivity$startFaceFlow$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HKMainActivity$startFaceFlow$2 hKMainActivity$startFaceFlow$2 = new HKMainActivity$startFaceFlow$2(this, faceCapture, null);
                    hKMainActivity$startFaceFlow$1.label = 1;
                    obj = CoroutineScopeKt.coroutineScope(hKMainActivity$startFaceFlow$2, hKMainActivity$startFaceFlow$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return ((Result) obj).getValue();
            }
        }
        hKMainActivity$startFaceFlow$1 = new HKMainActivity$startFaceFlow$1(this, continuation);
        Object obj2 = hKMainActivity$startFaceFlow$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = hKMainActivity$startFaceFlow$1.label;
        if (i != 0) {
        }
        return ((Result) obj2).getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: startApiFlow-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m409startApiFlow0E7RQCE(WorkflowUIState.ApiCall apiCall, boolean z, Continuation<? super Result<Response>> continuation) {
        HKMainActivity$startApiFlow$1 hKMainActivity$startApiFlow$1;
        int i;
        if (continuation instanceof HKMainActivity$startApiFlow$1) {
            hKMainActivity$startApiFlow$1 = (HKMainActivity$startApiFlow$1) continuation;
            if ((hKMainActivity$startApiFlow$1.label & Integer.MIN_VALUE) != 0) {
                hKMainActivity$startApiFlow$1.label -= Integer.MIN_VALUE;
                Object obj = hKMainActivity$startApiFlow$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = hKMainActivity$startApiFlow$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HKMainActivity$startApiFlow$2 hKMainActivity$startApiFlow$2 = new HKMainActivity$startApiFlow$2(z, apiCall, this, null);
                    hKMainActivity$startApiFlow$1.label = 1;
                    obj = CoroutineScopeKt.coroutineScope(hKMainActivity$startApiFlow$2, hKMainActivity$startApiFlow$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return ((Result) obj).getValue();
            }
        }
        hKMainActivity$startApiFlow$1 = new HKMainActivity$startApiFlow$1(this, continuation);
        Object obj2 = hKMainActivity$startApiFlow$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = hKMainActivity$startApiFlow$1.label;
        if (i != 0) {
        }
        return ((Result) obj2).getValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: startApiFlow-0E7RQCE$default, reason: not valid java name */
    public static /* synthetic */ Object m410startApiFlow0E7RQCE$default(HKMainActivity hKMainActivity, WorkflowUIState.ApiCall apiCall, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return hKMainActivity.m409startApiFlow0E7RQCE(apiCall, z, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void handleCustomApiExceptions$default(HKMainActivity hKMainActivity, WorkflowUIState.ApiCall apiCall, Integer num, String str, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        hKMainActivity.handleCustomApiExceptions(apiCall, num, str, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateEndState$hyperkyc_release$default(HKMainActivity hKMainActivity, boolean z, Boolean bool, boolean z2, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            bool = null;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        if ((i & 8) != 0) {
            function0 = new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$updateEndState$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        hKMainActivity.updateEndState$hyperkyc_release(z, bool, z2, function0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void processHVBridgeError$default(HKMainActivity hKMainActivity, String str, Throwable th, Integer num, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            num = null;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        hKMainActivity.processHVBridgeError(str, th, num, str2);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
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
        String str2 = "onConfigurationChanged: newConfig = " + newConfig;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            return;
        }
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
                String str3 = "onConfigurationChanged: newConfig = " + newConfig;
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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void observeFinishWithResultState() {
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
        sb.append("observeFinishWithResultState() called");
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
                    Log.println(3, str, "observeFinishWithResultState() called ");
                }
            }
        }
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new HKMainActivity$observeFinishWithResultState$2(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0120, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String getNetworkErrorMessage() {
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
        sb.append("getNetworkErrorMessage() called");
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
                    Log.println(3, str, "getNetworkErrorMessage() called ");
                }
            }
        }
        Map<String, Object> map = getMainVM().getTextConfigData().get("commons");
        String nullIfBlank = CoreExtsKt.nullIfBlank(map != null ? CoreExtsKt.getStringValue(map, "hk_api_network_error_text") : null);
        if (nullIfBlank != null) {
            return nullIfBlank;
        }
        String string = getString(R.string.hk_api_error_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.hk_api_error_message)");
        return string;
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void initViews() {
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
        sb.append("initViews() called");
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
                    Log.println(3, str, "initViews() called ");
                }
            }
        }
        HkActivityMainBinding binding$hyperkyc_release = getBinding$hyperkyc_release();
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new HKMainActivity$initViews$2$1$1(this, binding$hyperkyc_release.lavLoader, null), 3, null);
        binding$hyperkyc_release.btnRetry.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HKMainActivity.initViews$lambda$13$lambda$12(HKMainActivity.this, view);
            }
        });
        HyperSnapBridgeKt.getUiConfigUtil().customisePrimaryButton(binding$hyperkyc_release.btnRetry);
        HyperSnapBridgeKt.getUiConfigUtil().customiseSecondaryButton((Button) binding$hyperkyc_release.btnCancel);
        binding$hyperkyc_release.btnRetry.setIcon(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean validateSdkVersion(WorkflowConfig config) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        HashMap<String, Properties.SDKVersion> sdkVersions;
        Properties.SDKVersion sDKVersion;
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
        String str2 = "validateSdkVersion() called with: config = " + config;
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
                    String str3 = "validateSdkVersion() called with: config = " + config;
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
        Properties properties = config.getProperties();
        if (properties != null && (sdkVersions = properties.getSdkVersions()) != null && (sDKVersion = sdkVersions.get(Properties.SDK_VERSION_MOBILE_KEY)) != null) {
            int validateSdkVersion$lambda$35$toIntVersion = validateSdkVersion$lambda$35$toIntVersion(sDKVersion.getMinimum());
            int validateSdkVersion$lambda$35$toIntVersion2 = validateSdkVersion$lambda$35$toIntVersion(sDKVersion.getMaximum());
            int validateSdkVersion$lambda$35$toIntVersion3 = validateSdkVersion$lambda$35$toIntVersion(BuildConfig.HYPERKYC_VERSION_NAME);
            if (!(validateSdkVersion$lambda$35$toIntVersion <= validateSdkVersion$lambda$35$toIntVersion3 && validateSdkVersion$lambda$35$toIntVersion3 <= validateSdkVersion$lambda$35$toIntVersion2)) {
                BaseActivity.finishWithResult$default(this, "error", null, 105, "workflow " + getHyperKycConfig().getWorkflowId$hyperkyc_release() + " does not support the current SDK version - 0.33.0, please use SDK from " + sDKVersion.getMinimum() + " to " + sDKVersion.getMaximum(), false, false, 50, null);
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x013d, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean validateInputs(WorkflowConfig config) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        ArrayList arrayList;
        HashMap<String, String> inputsRequired;
        boolean z;
        Object m1202constructorimpl2;
        Object m1202constructorimpl3;
        HashMap<String, String> inputsRequired2;
        Set<String> keySet;
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
        String str2 = "validateInputs() called with: config = " + config;
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
                    String str3 = "validateInputs() called with: config = " + config;
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
        Properties properties = config.getProperties();
        if (properties == null || (inputsRequired2 = properties.getInputsRequired()) == null || (keySet = inputsRequired2.keySet()) == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : keySet) {
                if (!getHyperKycConfig().getInputs().keySet().contains((String) obj)) {
                    arrayList2.add(obj);
                }
            }
            arrayList = arrayList2;
        }
        ArrayList arrayList3 = arrayList;
        if (!(arrayList3 == null || arrayList3.isEmpty())) {
            validateInputs$finishWithError(this, CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, null, 63, null) + " not found in inputs");
            return false;
        }
        Properties properties2 = config.getProperties();
        if (properties2 != null && (inputsRequired = properties2.getInputsRequired()) != null) {
            for (Map.Entry<String, String> entry : inputsRequired.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Object obj2 = getHyperKycConfig().getInputs().get(key);
                if (obj2 == null ? true : obj2 instanceof String) {
                    int hashCode = value.hashCode();
                    if (hashCode != -891985903) {
                        if (hashCode != 64711720) {
                            if (hashCode == 100313435 && value.equals("image")) {
                                try {
                                    Result.Companion companion4 = Result.INSTANCE;
                                    HKMainActivity hKMainActivity = this;
                                    Intrinsics.checkNotNull(obj2);
                                    m1202constructorimpl3 = Result.m1202constructorimpl(Boolean.valueOf(new File((String) obj2).exists()));
                                } catch (Throwable th2) {
                                    Result.Companion companion5 = Result.INSTANCE;
                                    m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                                }
                                Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                                if (m1205exceptionOrNullimpl != null && (m1205exceptionOrNullimpl instanceof SecurityException)) {
                                    validateInputs$finishWithError(this, "File " + obj2 + " is not accessible, please ensure necessary permissions are granted");
                                    return false;
                                }
                                if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                    m1202constructorimpl3 = false;
                                }
                                Boolean bool = (Boolean) m1202constructorimpl3;
                                if (!bool.booleanValue()) {
                                    validateInputs$finishWithError(this, "File " + obj2 + " does not exist");
                                    return false;
                                }
                                z = bool.booleanValue();
                            }
                            throw new NotImplementedError("An operation is not implemented: " + ("Unsupported type: " + value));
                        }
                        if (!value.equals("boolean")) {
                            throw new NotImplementedError("An operation is not implemented: " + ("Unsupported type: " + value));
                        }
                        try {
                            Result.Companion companion6 = Result.INSTANCE;
                            HKMainActivity hKMainActivity2 = this;
                            String str4 = (String) obj2;
                            m1202constructorimpl2 = Result.m1202constructorimpl(str4 != null ? Boolean.valueOf(StringsKt.toBooleanStrict(str4)) : null);
                        } catch (Throwable th3) {
                            Result.Companion companion7 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                        }
                        z = Result.m1209isSuccessimpl(m1202constructorimpl2);
                    } else {
                        if (!value.equals("string")) {
                            throw new NotImplementedError("An operation is not implemented: " + ("Unsupported type: " + value));
                        }
                        CharSequence charSequence = (CharSequence) obj2;
                        if (charSequence == null || StringsKt.isBlank(charSequence)) {
                            z = false;
                        }
                        z = true;
                    }
                } else {
                    if (Intrinsics.areEqual(value, "number")) {
                        z = obj2 instanceof Number;
                    }
                    z = true;
                }
                if (!z) {
                    validateInputs$finishWithError(this, "value \"" + obj2 + "\" for key \"" + key + "\" is not of the expected type \"" + value + '\"');
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x0155, code lost:
    
        if (r0 != null) goto L56;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x02a9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:49:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x021c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void showJsonExceptionRetry(final boolean show, Integer errorCode, Integer retryTitleRes, final String retryError, final Function0<Unit> onRetry) {
        String canonicalName;
        Object m1202constructorimpl;
        final Integer num;
        String str;
        String str2;
        String className;
        String nullIfBlank;
        String string;
        String nullIfBlank2;
        String string2;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
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
        String str3 = "showJsonExceptionRetry() called with: show = " + show + ", retryTitleRes = " + retryTitleRes + ", retryError = " + retryError + ", errorCode = " + errorCode;
        if (str3 == null) {
            str3 = "null ";
        }
        sb.append(str3);
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
                if (!StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    num = errorCode;
                    str = null;
                    if (show) {
                    }
                    final HkActivityMainBinding binding$hyperkyc_release = getBinding$hyperkyc_release();
                    MaterialButton btnCancel = binding$hyperkyc_release.btnCancel;
                    Intrinsics.checkNotNullExpressionValue(btnCancel, "btnCancel");
                    btnCancel.setVisibility(8);
                    binding$hyperkyc_release.btnRetry.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            HKMainActivity.showJsonExceptionRetry$lambda$50$lambda$46(HkActivityMainBinding.this, this, onRetry, view);
                        }
                    });
                    MaterialButton materialButton = binding$hyperkyc_release.btnRetry;
                    Map<String, Object> map = getMainVM().getTextConfigData().get("commons");
                    nullIfBlank = CoreExtsKt.nullIfBlank(map != null ? CoreExtsKt.getStringValue(map, "hk_retry_button_text") : str);
                    if (nullIfBlank != null) {
                    }
                    materialButton.setText(string);
                    MaterialButton materialButton2 = binding$hyperkyc_release.btnCancel;
                    Map<String, Object> map2 = getMainVM().getTextConfigData().get("commons");
                    nullIfBlank2 = CoreExtsKt.nullIfBlank(map2 != null ? CoreExtsKt.getStringValue(map2, "hk_cancel_button_text") : str);
                    if (nullIfBlank2 != null) {
                    }
                    materialButton2.setText(string2);
                    binding$hyperkyc_release.btnCancel.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$$ExternalSyntheticLambda3
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            HKMainActivity.showJsonExceptionRetry$lambda$50$lambda$47(HKMainActivity.this, num, retryError, view);
                        }
                    });
                    if (retryError != null) {
                    }
                    if (retryTitleRes != null) {
                    }
                    MaterialTextView tvRetryTitle = binding$hyperkyc_release.tvRetryTitle;
                    Intrinsics.checkNotNullExpressionValue(tvRetryTitle, "tvRetryTitle");
                    MaterialButton btnRetry = binding$hyperkyc_release.btnRetry;
                    Intrinsics.checkNotNullExpressionValue(btnRetry, "btnRetry");
                    CoreExtsKt.withViews(new View[]{tvRetryTitle, btnRetry}, new Function1<View, Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$showJsonExceptionRetry$2$5
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(View view) {
                            invoke2(view);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(View withViews) {
                            Intrinsics.checkNotNullParameter(withViews, "$this$withViews");
                            withViews.setVisibility(show ? 0 : 8);
                        }
                    });
                    if (this.jsonErrorRetryCount < 3) {
                        return;
                    } else {
                        return;
                    }
                }
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
                if (str2 == null) {
                    str2 = "N/A";
                }
                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                if (matcher2.find()) {
                    str2 = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                }
                if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str2 = str2.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                StringBuilder sb2 = new StringBuilder();
                StringBuilder sb3 = new StringBuilder();
                sb3.append("showJsonExceptionRetry() called with: show = ");
                sb3.append(show);
                sb3.append(", retryTitleRes = ");
                sb3.append(retryTitleRes);
                sb3.append(", retryError = ");
                sb3.append(retryError);
                sb3.append(", errorCode = ");
                num = errorCode;
                sb3.append(num);
                String sb4 = sb3.toString();
                if (sb4 == null) {
                    sb4 = "null ";
                }
                sb2.append(sb4);
                sb2.append(' ');
                sb2.append("");
                Log.println(3, str2, sb2.toString());
                if (show) {
                    this.ignoreBackPress = true;
                }
                final HkActivityMainBinding binding$hyperkyc_release2 = getBinding$hyperkyc_release();
                MaterialButton btnCancel2 = binding$hyperkyc_release2.btnCancel;
                Intrinsics.checkNotNullExpressionValue(btnCancel2, "btnCancel");
                btnCancel2.setVisibility(8);
                binding$hyperkyc_release2.btnRetry.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        HKMainActivity.showJsonExceptionRetry$lambda$50$lambda$46(HkActivityMainBinding.this, this, onRetry, view);
                    }
                });
                MaterialButton materialButton3 = binding$hyperkyc_release2.btnRetry;
                Map<String, Object> map3 = getMainVM().getTextConfigData().get("commons");
                nullIfBlank = CoreExtsKt.nullIfBlank(map3 != null ? CoreExtsKt.getStringValue(map3, "hk_retry_button_text") : str);
                if (nullIfBlank != null) {
                    string = nullIfBlank;
                } else {
                    string = getString(R.string.hk_retry);
                }
                materialButton3.setText(string);
                MaterialButton materialButton22 = binding$hyperkyc_release2.btnCancel;
                Map<String, Object> map22 = getMainVM().getTextConfigData().get("commons");
                nullIfBlank2 = CoreExtsKt.nullIfBlank(map22 != null ? CoreExtsKt.getStringValue(map22, "hk_cancel_button_text") : str);
                if (nullIfBlank2 != null) {
                    string2 = nullIfBlank2;
                } else {
                    string2 = getString(R.string.hk_cancel);
                }
                materialButton22.setText(string2);
                binding$hyperkyc_release2.btnCancel.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$$ExternalSyntheticLambda3
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        HKMainActivity.showJsonExceptionRetry$lambda$50$lambda$47(HKMainActivity.this, num, retryError, view);
                    }
                });
                if (retryError != null) {
                    binding$hyperkyc_release2.tvRetryTitle.setText(retryError);
                }
                if (retryTitleRes != null) {
                    binding$hyperkyc_release2.tvRetryTitle.setText(retryTitleRes.intValue());
                }
                MaterialTextView tvRetryTitle2 = binding$hyperkyc_release2.tvRetryTitle;
                Intrinsics.checkNotNullExpressionValue(tvRetryTitle2, "tvRetryTitle");
                MaterialButton btnRetry2 = binding$hyperkyc_release2.btnRetry;
                Intrinsics.checkNotNullExpressionValue(btnRetry2, "btnRetry");
                CoreExtsKt.withViews(new View[]{tvRetryTitle2, btnRetry2}, new Function1<View, Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$showJsonExceptionRetry$2$5
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(View view) {
                        invoke2(view);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(View withViews) {
                        Intrinsics.checkNotNullParameter(withViews, "$this$withViews");
                        withViews.setVisibility(show ? 0 : 8);
                    }
                });
                if (this.jsonErrorRetryCount < 3 || !show) {
                    return;
                }
                MaterialButton btnCancel3 = binding$hyperkyc_release2.btnCancel;
                Intrinsics.checkNotNullExpressionValue(btnCancel3, "btnCancel");
                btnCancel3.setVisibility(0);
                return;
            }
        }
        num = errorCode;
        str = null;
        if (show) {
        }
        final HkActivityMainBinding binding$hyperkyc_release22 = getBinding$hyperkyc_release();
        MaterialButton btnCancel22 = binding$hyperkyc_release22.btnCancel;
        Intrinsics.checkNotNullExpressionValue(btnCancel22, "btnCancel");
        btnCancel22.setVisibility(8);
        binding$hyperkyc_release22.btnRetry.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HKMainActivity.showJsonExceptionRetry$lambda$50$lambda$46(HkActivityMainBinding.this, this, onRetry, view);
            }
        });
        MaterialButton materialButton32 = binding$hyperkyc_release22.btnRetry;
        Map<String, Object> map32 = getMainVM().getTextConfigData().get("commons");
        nullIfBlank = CoreExtsKt.nullIfBlank(map32 != null ? CoreExtsKt.getStringValue(map32, "hk_retry_button_text") : str);
        if (nullIfBlank != null) {
        }
        materialButton32.setText(string);
        MaterialButton materialButton222 = binding$hyperkyc_release22.btnCancel;
        Map<String, Object> map222 = getMainVM().getTextConfigData().get("commons");
        nullIfBlank2 = CoreExtsKt.nullIfBlank(map222 != null ? CoreExtsKt.getStringValue(map222, "hk_cancel_button_text") : str);
        if (nullIfBlank2 != null) {
        }
        materialButton222.setText(string2);
        binding$hyperkyc_release22.btnCancel.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HKMainActivity.showJsonExceptionRetry$lambda$50$lambda$47(HKMainActivity.this, num, retryError, view);
            }
        });
        if (retryError != null) {
        }
        if (retryTitleRes != null) {
        }
        MaterialTextView tvRetryTitle22 = binding$hyperkyc_release22.tvRetryTitle;
        Intrinsics.checkNotNullExpressionValue(tvRetryTitle22, "tvRetryTitle");
        MaterialButton btnRetry22 = binding$hyperkyc_release22.btnRetry;
        Intrinsics.checkNotNullExpressionValue(btnRetry22, "btnRetry");
        CoreExtsKt.withViews(new View[]{tvRetryTitle22, btnRetry22}, new Function1<View, Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$showJsonExceptionRetry$2$5
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View withViews) {
                Intrinsics.checkNotNullParameter(withViews, "$this$withViews");
                withViews.setVisibility(show ? 0 : 8);
            }
        });
        if (this.jsonErrorRetryCount < 3) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x014a, code lost:
    
        if (r0 != null) goto L56;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0210  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void showRetry(final boolean show, final Integer errorCode, Integer retryTitleRes, final String retryError, final Function0<Unit> onRetry) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String nullIfBlank;
        String string;
        String nullIfBlank2;
        String string2;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
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
        String str3 = "showRetry() called with: show = " + show + ", retryTitleRes = " + retryTitleRes + ", retryError = " + retryError;
        if (str3 == null) {
            str3 = "null ";
        }
        sb.append(str3);
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
                    String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : str;
                    str2 = canonicalName2 == null ? "N/A" : canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher2.find()) {
                        str2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str4 = "showRetry() called with: show = " + show + ", retryTitleRes = " + retryTitleRes + ", retryError = " + retryError;
                    if (str4 == null) {
                        str4 = "null ";
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str2, sb2.toString());
                    final Ref.IntRef intRef = new Ref.IntRef();
                    if (show) {
                        this.ignoreBackPress = true;
                    }
                    final HkActivityMainBinding binding$hyperkyc_release = getBinding$hyperkyc_release();
                    MaterialButton btnCancel = binding$hyperkyc_release.btnCancel;
                    Intrinsics.checkNotNullExpressionValue(btnCancel, "btnCancel");
                    btnCancel.setVisibility(8);
                    String str5 = str;
                    binding$hyperkyc_release.btnRetry.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$$ExternalSyntheticLambda4
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            HKMainActivity.showRetry$lambda$56$lambda$52(Ref.IntRef.this, show, binding$hyperkyc_release, onRetry, this, view);
                        }
                    });
                    MaterialButton materialButton = binding$hyperkyc_release.btnRetry;
                    Map<String, Object> map = getMainVM().getTextConfigData().get("commons");
                    nullIfBlank = CoreExtsKt.nullIfBlank(map == null ? CoreExtsKt.getStringValue(map, "hk_retry_button_text") : str5);
                    if (nullIfBlank == null) {
                        string = nullIfBlank;
                    } else {
                        string = getString(R.string.hk_retry);
                    }
                    materialButton.setText(string);
                    MaterialButton materialButton2 = binding$hyperkyc_release.btnCancel;
                    Map<String, Object> map2 = getMainVM().getTextConfigData().get("commons");
                    nullIfBlank2 = CoreExtsKt.nullIfBlank(map2 == null ? CoreExtsKt.getStringValue(map2, "hk_cancel_button_text") : str5);
                    if (nullIfBlank2 == null) {
                        string2 = nullIfBlank2;
                    } else {
                        string2 = getString(R.string.hk_cancel);
                    }
                    materialButton2.setText(string2);
                    binding$hyperkyc_release.btnCancel.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$$ExternalSyntheticLambda2
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            HKMainActivity.showRetry$lambda$56$lambda$53(HKMainActivity.this, errorCode, retryError, view);
                        }
                    });
                    if (retryError != null) {
                        binding$hyperkyc_release.tvRetryTitle.setText(retryError);
                    }
                    if (retryTitleRes != null) {
                        binding$hyperkyc_release.tvRetryTitle.setText(retryTitleRes.intValue());
                    }
                    MaterialTextView tvRetryTitle = binding$hyperkyc_release.tvRetryTitle;
                    Intrinsics.checkNotNullExpressionValue(tvRetryTitle, "tvRetryTitle");
                    MaterialButton btnRetry = binding$hyperkyc_release.btnRetry;
                    Intrinsics.checkNotNullExpressionValue(btnRetry, "btnRetry");
                    CoreExtsKt.withViews(new View[]{tvRetryTitle, btnRetry}, new Function1<View, Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$showRetry$2$5
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(View view) {
                            invoke2(view);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(View withViews) {
                            Intrinsics.checkNotNullParameter(withViews, "$this$withViews");
                            withViews.setVisibility(show ? 0 : 8);
                        }
                    });
                }
            }
        }
        str = null;
        final Ref.IntRef intRef2 = new Ref.IntRef();
        if (show) {
        }
        final HkActivityMainBinding binding$hyperkyc_release2 = getBinding$hyperkyc_release();
        MaterialButton btnCancel2 = binding$hyperkyc_release2.btnCancel;
        Intrinsics.checkNotNullExpressionValue(btnCancel2, "btnCancel");
        btnCancel2.setVisibility(8);
        String str52 = str;
        binding$hyperkyc_release2.btnRetry.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HKMainActivity.showRetry$lambda$56$lambda$52(Ref.IntRef.this, show, binding$hyperkyc_release2, onRetry, this, view);
            }
        });
        MaterialButton materialButton3 = binding$hyperkyc_release2.btnRetry;
        Map<String, Object> map3 = getMainVM().getTextConfigData().get("commons");
        nullIfBlank = CoreExtsKt.nullIfBlank(map3 == null ? CoreExtsKt.getStringValue(map3, "hk_retry_button_text") : str52);
        if (nullIfBlank == null) {
        }
        materialButton3.setText(string);
        MaterialButton materialButton22 = binding$hyperkyc_release2.btnCancel;
        Map<String, Object> map22 = getMainVM().getTextConfigData().get("commons");
        nullIfBlank2 = CoreExtsKt.nullIfBlank(map22 == null ? CoreExtsKt.getStringValue(map22, "hk_cancel_button_text") : str52);
        if (nullIfBlank2 == null) {
        }
        materialButton22.setText(string2);
        binding$hyperkyc_release2.btnCancel.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HKMainActivity.showRetry$lambda$56$lambda$53(HKMainActivity.this, errorCode, retryError, view);
            }
        });
        if (retryError != null) {
        }
        if (retryTitleRes != null) {
        }
        MaterialTextView tvRetryTitle2 = binding$hyperkyc_release2.tvRetryTitle;
        Intrinsics.checkNotNullExpressionValue(tvRetryTitle2, "tvRetryTitle");
        MaterialButton btnRetry2 = binding$hyperkyc_release2.btnRetry;
        Intrinsics.checkNotNullExpressionValue(btnRetry2, "btnRetry");
        CoreExtsKt.withViews(new View[]{tvRetryTitle2, btnRetry2}, new Function1<View, Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$showRetry$2$5
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View withViews) {
                Intrinsics.checkNotNullParameter(withViews, "$this$withViews");
                withViews.setVisibility(show ? 0 : 8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void observeUiState() {
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
        sb.append("observeUiState() called");
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
                    Log.println(3, str, "observeUiState() called ");
                }
            }
        }
        LifecycleOwnerKt.getLifecycleScope(this).launchWhenCreated(new HKMainActivity$observeUiState$2(this, null));
    }

    /* JADX WARN: Removed duplicated region for block: B:126:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01ad A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01ae  */
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    @Deprecated(message = "Deprecated in Java")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onBackPressed() {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String canonicalName2;
        String className;
        String canonicalName3;
        Object m1202constructorimpl2;
        String str2;
        String str3;
        Matcher matcher;
        String className2;
        String className3;
        String str4;
        Object m1202constructorimpl3;
        String str5;
        String className4;
        String substringAfterLast$default;
        String className5;
        String className6;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className6 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
        String str6 = "onBackPressed() called with ignoreBackPress = " + this.ignoreBackPress;
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
                str = "N/A";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = str;
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
                    String str7 = "onBackPressed() called with ignoreBackPress = " + this.ignoreBackPress;
                    sb2.append(str7 == null ? "null " : str7);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                }
                if (this.ignoreBackPress) {
                    if (getOnBackPressedDispatcher().getHasEnabledCallbacks()) {
                        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb3 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className5 = stackTraceElement3.getClassName()) == null || (str4 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls3 = getClass();
                            String canonicalName4 = cls3 != null ? cls3.getCanonicalName() : null;
                            str4 = canonicalName4 == null ? str : canonicalName4;
                        }
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                        if (matcher4.find()) {
                            str4 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                        }
                        if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str4 = str4.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb3.append(str4);
                        sb3.append(" - ");
                        sb3.append("onBackPressed() called has callbacks");
                        sb3.append(' ');
                        sb3.append("");
                        companion4.log(level2, sb3.toString());
                        if (!CoreExtsKt.isRelease()) {
                            try {
                                Result.Companion companion5 = Result.INSTANCE;
                                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                                m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                            } catch (Throwable th2) {
                                Result.Companion companion6 = Result.INSTANCE;
                                m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                m1202constructorimpl3 = "";
                            }
                            String packageName2 = (String) m1202constructorimpl3;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                                if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                    if (stackTraceElement4 == null || (className4 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                        Class<?> cls4 = getClass();
                                        String canonicalName5 = cls4 != null ? cls4.getCanonicalName() : null;
                                        str5 = canonicalName5 == null ? str : canonicalName5;
                                    } else {
                                        str5 = substringAfterLast$default;
                                    }
                                    Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                    if (matcher5.find()) {
                                        str5 = matcher5.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                                    }
                                    if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str5 = str5.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    Log.println(4, str5, "onBackPressed() called has callbacks ");
                                }
                            }
                        }
                        getOnBackPressedDispatcher().onBackPressed();
                        return;
                    }
                    HyperLogger.Level level3 = HyperLogger.Level.DEBUG;
                    HyperLogger companion7 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb4 = new StringBuilder();
                    StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                    if (stackTraceElement5 == null || (className3 = stackTraceElement5.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls5 = getClass();
                        canonicalName3 = cls5 != null ? cls5.getCanonicalName() : null;
                        if (canonicalName3 == null) {
                            canonicalName3 = str;
                        }
                    }
                    Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                    if (matcher6.find()) {
                        canonicalName3 = matcher6.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                    }
                    if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName3 = canonicalName3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb4.append(canonicalName3);
                    sb4.append(" - ");
                    sb4.append("onBackPressed() called has no callbacks");
                    sb4.append(' ');
                    sb4.append("");
                    companion7.log(level3, sb4.toString());
                    if (!CoreExtsKt.isRelease()) {
                        try {
                            Result.Companion companion8 = Result.INSTANCE;
                            Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                        } catch (Throwable th3) {
                            Result.Companion companion9 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                            m1202constructorimpl2 = "";
                        }
                        String packageName3 = (String) m1202constructorimpl2;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName3, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace6, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                if (stackTraceElement6 == null || (className2 = stackTraceElement6.getClassName()) == null) {
                                    str2 = null;
                                } else {
                                    str2 = null;
                                    String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default2 != null) {
                                        str3 = substringAfterLast$default2;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                                        if (matcher.find()) {
                                            str3 = matcher.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                                        }
                                        if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            str3 = str3.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        Log.println(4, str3, "onBackPressed() called has no callbacks ");
                                    }
                                }
                                Class<?> cls6 = getClass();
                                String canonicalName6 = cls6 != null ? cls6.getCanonicalName() : str2;
                                str3 = canonicalName6 == null ? str : canonicalName6;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                                if (matcher.find()) {
                                }
                                if (str3.length() > 23) {
                                    str3 = str3.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                Log.println(4, str3, "onBackPressed() called has no callbacks ");
                            }
                        }
                    }
                    if (getMainVM().flowBack()) {
                        return;
                    }
                    BaseActivity.finishWithResult$default(this, HyperKycStatus.USER_CANCELLED, null, 103, null, false, false, 58, null);
                    return;
                }
                return;
            }
        }
        str = "N/A";
        if (this.ignoreBackPress) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startVideoStatementV2Flow(WorkflowUIState.VideoStatementV2 videoStatementV2UIState) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        Map emptyMap;
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
        String str2 = "startVideoStatementV2Flow() called with: videoStatementV2UIState = " + videoStatementV2UIState;
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
                    String str3 = "startVideoStatementV2Flow() called with: videoStatementV2UIState = " + videoStatementV2UIState;
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
        HKMainActivity hKMainActivity = this;
        VideoStatementV2Fragment videoStatementV2Fragment = new VideoStatementV2Fragment(videoStatementV2UIState);
        Pair[] pairArr = new Pair[2];
        Map<String, Object> textConfigs = videoStatementV2UIState.getTextConfigs();
        if (textConfigs == null || (emptyMap = MapsKt.toMap(textConfigs)) == null) {
            emptyMap = MapsKt.emptyMap();
        }
        pairArr[0] = TuplesKt.to("textConfigs", emptyMap);
        pairArr[1] = TuplesKt.to("showBackButton", Boolean.valueOf(videoStatementV2UIState.getShowBackButton()));
        ActivityExtsKt.replaceContent$default(hKMainActivity, videoStatementV2Fragment, BundleKt.bundleOf(pairArr), false, null, 0, 28, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startNFCFlow(WorkflowUIState.NFCReader nfcFlowUIState) {
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
        String str2 = "startNFCFlow() called with: nfcUIState = " + nfcFlowUIState;
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
                    String str3 = "startNFCFlow() called with: nfcUIState = " + nfcFlowUIState;
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
        if (nfcFlowUIState.getShowInstruction()) {
            ActivityExtsKt.replaceContent$default(this, new NFCReaderInstructionFragment(), BundleKt.bundleOf(TuplesKt.to(NFCReaderInstructionFragment.ARG_KEY_NFC_READER_UI_STATE, nfcFlowUIState)), false, null, 0, 28, null);
        } else {
            ActivityExtsKt.replaceContent$default(this, new NFCReaderFragment(), BundleKt.bundleOf(TuplesKt.to("nfcUIState", nfcFlowUIState)), false, "nfcFragment", 0, 20, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startVideoStatementFlow(WorkflowUIState.VideoStatement videoStatementUIState) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        Map emptyMap;
        Map emptyMap2;
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
        String str2 = "startVideoStatementFlow() called with: videoStatementUIState = " + videoStatementUIState;
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
                    String str3 = "startVideoStatementFlow() called with: videoStatementUIState = " + videoStatementUIState;
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
        long currentTimeMillis = System.currentTimeMillis();
        if (videoStatementUIState.getVsConfig().getShowInstruction()) {
            HKMainActivity hKMainActivity = this;
            VideoStatementInstructionFragment videoStatementInstructionFragment = new VideoStatementInstructionFragment(videoStatementUIState);
            Pair[] pairArr = new Pair[3];
            Map<String, Object> textConfigs = videoStatementUIState.getTextConfigs();
            if (textConfigs == null || (emptyMap2 = MapsKt.toMap(textConfigs)) == null) {
                emptyMap2 = MapsKt.emptyMap();
            }
            pairArr[0] = TuplesKt.to("textConfigs", emptyMap2);
            pairArr[1] = TuplesKt.to("startTimestamp", Long.valueOf(currentTimeMillis));
            pairArr[2] = TuplesKt.to("showBackButton", Boolean.valueOf(videoStatementUIState.getShowBackButton()));
            ActivityExtsKt.replaceContent$default(hKMainActivity, videoStatementInstructionFragment, BundleKt.bundleOf(pairArr), false, null, 0, 28, null);
            return;
        }
        HKMainActivity hKMainActivity2 = this;
        VideoStatementFragment videoStatementFragment = new VideoStatementFragment(videoStatementUIState);
        Pair[] pairArr2 = new Pair[3];
        Map<String, Object> textConfigs2 = videoStatementUIState.getTextConfigs();
        if (textConfigs2 == null || (emptyMap = MapsKt.toMap(textConfigs2)) == null) {
            emptyMap = MapsKt.emptyMap();
        }
        pairArr2[0] = TuplesKt.to("textConfigs", emptyMap);
        pairArr2[1] = TuplesKt.to("startTimestamp", Long.valueOf(currentTimeMillis));
        pairArr2[2] = TuplesKt.to("showBackButton", Boolean.valueOf(videoStatementUIState.getShowBackButton()));
        ActivityExtsKt.replaceContent$default(hKMainActivity2, videoStatementFragment, BundleKt.bundleOf(pairArr2), false, null, 0, 28, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0139, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void startSessionFlow(WorkflowUIState.StartSessionRecording startSessionUiState) {
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
        String str2 = "startSessionFlow() called with: startSessionFlowUiState = " + startSessionUiState;
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
                    String str3 = "startSessionFlow() called with: startSessionFlowUiState = " + startSessionUiState;
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
        boolean recordAudio = startSessionUiState.getRecordAudio();
        boolean showConsentScreen = startSessionUiState.getShowConsentScreen();
        boolean uploadSession = startSessionUiState.getUploadSession();
        String uploadUrl = startSessionUiState.getUploadUrl();
        String stopModuleId = startSessionUiState.getStopModuleId();
        Map<String, Object> textConfigs = startSessionUiState.getTextConfigs();
        Map map = textConfigs != null ? MapsKt.toMap(textConfigs) : null;
        Map map2 = map instanceof Map ? map : null;
        ActivityExtsKt.replaceContent$default(this, new SessionConsentFragment(new SessionRecordingConfig(recordAudio, showConsentScreen, uploadSession, uploadUrl, stopModuleId, map2 == null ? MapsKt.emptyMap() : map2)), BundleKt.bundleOf(TuplesKt.to("showBackButton", Boolean.valueOf(startSessionUiState.getShowBackButton()))), false, null, 0, 28, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopSessionFlow(WorkflowUIState.StopSessionRecording stopSessionFlowUiState) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        Map emptyMap;
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
        String str2 = "stopSessionFlow() called with: stopSessionFlowUiState = " + stopSessionFlowUiState;
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
                    String str3 = "stopSessionFlow() called with: stopSessionFlowUiState = " + stopSessionFlowUiState;
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
        String uploadUrl = HVSessionRecorder.INSTANCE.getInstance().getUploadUrl();
        String filePath = HVSessionRecorder.INSTANCE.getInstance().getFilePath();
        if (stopSessionFlowUiState.getShowRecordingReview()) {
            HKMainActivity hKMainActivity = this;
            SessionReviewFragment sessionReviewFragment = new SessionReviewFragment(stopSessionFlowUiState);
            Pair[] pairArr = new Pair[4];
            Map<String, Object> textConfigs = stopSessionFlowUiState.getTextConfigs();
            if (textConfigs == null || (emptyMap = MapsKt.toMap(textConfigs)) == null) {
                emptyMap = MapsKt.emptyMap();
            }
            pairArr[0] = TuplesKt.to("textConfigs", emptyMap);
            pairArr[1] = TuplesKt.to("uploadUrl", uploadUrl);
            pairArr[2] = TuplesKt.to("filePath", filePath);
            pairArr[3] = TuplesKt.to("showBackButton", Boolean.valueOf(stopSessionFlowUiState.getShowBackButton()));
            ActivityExtsKt.replaceContent$default(hKMainActivity, sessionReviewFragment, BundleKt.bundleOf(pairArr), false, null, 0, 28, null);
            return;
        }
        if (HVSessionRecorder.INSTANCE.getInstance().getShouldUpload()) {
            BaseActivity.finishSessionUpload$default(this, stopSessionFlowUiState, getMainVM().getCurrentModuleId$hyperkyc_release(), uploadUrl, filePath, null, 16, null);
        } else {
            addSessionRecordingVideoUriToResult(stopSessionFlowUiState, filePath, "yes");
            HVSessionRecorder.INSTANCE.getInstance().stop$hyperkyc_release(new Function1<File, Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$stopSessionFlow$2
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
                    HKMainActivity.this.getMainVM().flowForward();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x013d, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void handleCustomApiExceptions(final WorkflowUIState.ApiCall apiFlowUIState, Integer errorCode, String errorMsg, boolean isNetworkError) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String str;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str2 = "N/A";
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
        boolean z = false;
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str3 = "handleCustomApiExceptions() called with: apiFlowUIState = " + apiFlowUIState;
        if (str3 == null) {
            str3 = "null ";
        }
        sb.append(str3);
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
                    str2 = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher2.find()) {
                        str2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str4 = "handleCustomApiExceptions() called with: apiFlowUIState = " + apiFlowUIState;
                    if (str4 == null) {
                        str4 = "null ";
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str2, sb2.toString());
                }
            }
        }
        this.ignoreBackPress = true;
        FrameLayout frameLayout = getBinding$hyperkyc_release().flContent;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.flContent");
        frameLayout.setVisibility(8);
        IntRange intRange = new IntRange(500, HttpStatusCodeRange.DEFAULT_MAX);
        if (errorCode != null && intRange.contains(errorCode.intValue())) {
            z = true;
        }
        if (z || isNetworkError) {
            if (errorMsg != null) {
                String str5 = true ^ StringsKt.isBlank(errorMsg) ? errorMsg : null;
                if (str5 != null) {
                    str = str5;
                    showJsonExceptionRetry$default(this, true, errorCode, null, str, new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$handleCustomApiExceptions$3
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            if (ContextExtsKt.isOnline(HKMainActivity.this)) {
                                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(HKMainActivity.this), null, null, new AnonymousClass1(HKMainActivity.this, apiFlowUIState, null), 3, null);
                                HKMainActivity.showRetry$default(HKMainActivity.this, false, null, null, null, null, 30, null);
                                HKMainActivity.this.setIgnoreBackPress$hyperkyc_release(false);
                            }
                        }

                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* compiled from: HKMainActivity.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                        @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$handleCustomApiExceptions$3$1", f = "HKMainActivity.kt", i = {}, l = {1859}, m = "invokeSuspend", n = {}, s = {})
                        /* renamed from: co.hyperverge.hyperkyc.ui.HKMainActivity$handleCustomApiExceptions$3$1, reason: invalid class name */
                        /* loaded from: classes2.dex */
                        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ WorkflowUIState.ApiCall $apiFlowUIState;
                            int label;
                            final /* synthetic */ HKMainActivity this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass1(HKMainActivity hKMainActivity, WorkflowUIState.ApiCall apiCall, Continuation<? super AnonymousClass1> continuation) {
                                super(2, continuation);
                                this.this$0 = hKMainActivity;
                                this.$apiFlowUIState = apiCall;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass1(this.this$0, this.$apiFlowUIState, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                Object m409startApiFlow0E7RQCE;
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    this.label = 1;
                                    m409startApiFlow0E7RQCE = this.this$0.m409startApiFlow0E7RQCE(this.$apiFlowUIState, true, this);
                                    if (m409startApiFlow0E7RQCE == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                } else {
                                    if (i != 1) {
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    ResultKt.throwOnFailure(obj);
                                    ((Result) obj).getValue();
                                }
                                return Unit.INSTANCE;
                            }
                        }
                    }, 4, null);
                    LottieAnimationView lavLoader = getBinding$hyperkyc_release().lavLoader;
                    Intrinsics.checkNotNullExpressionValue(lavLoader, "lavLoader");
                    lavLoader.setVisibility(8);
                    return;
                }
            }
            str = "Something is wrong with the API response";
            showJsonExceptionRetry$default(this, true, errorCode, null, str, new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$handleCustomApiExceptions$3
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    if (ContextExtsKt.isOnline(HKMainActivity.this)) {
                        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(HKMainActivity.this), null, null, new AnonymousClass1(HKMainActivity.this, apiFlowUIState, null), 3, null);
                        HKMainActivity.showRetry$default(HKMainActivity.this, false, null, null, null, null, 30, null);
                        HKMainActivity.this.setIgnoreBackPress$hyperkyc_release(false);
                    }
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: HKMainActivity.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$handleCustomApiExceptions$3$1", f = "HKMainActivity.kt", i = {}, l = {1859}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: co.hyperverge.hyperkyc.ui.HKMainActivity$handleCustomApiExceptions$3$1, reason: invalid class name */
                /* loaded from: classes2.dex */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ WorkflowUIState.ApiCall $apiFlowUIState;
                    int label;
                    final /* synthetic */ HKMainActivity this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(HKMainActivity hKMainActivity, WorkflowUIState.ApiCall apiCall, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.this$0 = hKMainActivity;
                        this.$apiFlowUIState = apiCall;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.this$0, this.$apiFlowUIState, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object m409startApiFlow0E7RQCE;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.label = 1;
                            m409startApiFlow0E7RQCE = this.this$0.m409startApiFlow0E7RQCE(this.$apiFlowUIState, true, this);
                            if (m409startApiFlow0E7RQCE == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                            ((Result) obj).getValue();
                        }
                        return Unit.INSTANCE;
                    }
                }
            }, 4, null);
            LottieAnimationView lavLoader2 = getBinding$hyperkyc_release().lavLoader;
            Intrinsics.checkNotNullExpressionValue(lavLoader2, "lavLoader");
            lavLoader2.setVisibility(8);
            return;
        }
        HKMainActivity hKMainActivity = this;
        finishWithResult("error", getMainVM().getHyperKycData(), errorCode, errorMsg, ContextExtsKt.isOnline(hKMainActivity), ContextExtsKt.isOnline(hKMainActivity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleAPIModuleFailure(final WorkflowUIState.ApiCall apiFlowUIState) {
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
        String str2 = "handleAPIModuleFailure() called with: apiFlowUIState = " + apiFlowUIState;
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
                    String str3 = "handleAPIModuleFailure() called with: apiFlowUIState = " + apiFlowUIState;
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
        this.ignoreBackPress = true;
        FrameLayout frameLayout = getBinding$hyperkyc_release().flContent;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.flContent");
        frameLayout.setVisibility(8);
        showRetry$default(this, true, 111, null, getNetworkErrorMessage(), new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$handleAPIModuleFailure$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                if (ContextExtsKt.isOnline(HKMainActivity.this)) {
                    BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(HKMainActivity.this), null, null, new AnonymousClass1(HKMainActivity.this, apiFlowUIState, null), 3, null);
                    HKMainActivity.showRetry$default(HKMainActivity.this, false, null, null, null, null, 30, null);
                    HKMainActivity.this.setIgnoreBackPress$hyperkyc_release(false);
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: HKMainActivity.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$handleAPIModuleFailure$2$1", f = "HKMainActivity.kt", i = {}, l = {1908}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: co.hyperverge.hyperkyc.ui.HKMainActivity$handleAPIModuleFailure$2$1, reason: invalid class name */
            /* loaded from: classes2.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ WorkflowUIState.ApiCall $apiFlowUIState;
                int label;
                final /* synthetic */ HKMainActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(HKMainActivity hKMainActivity, WorkflowUIState.ApiCall apiCall, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = hKMainActivity;
                    this.$apiFlowUIState = apiCall;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.this$0, this.$apiFlowUIState, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object m409startApiFlow0E7RQCE;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        m409startApiFlow0E7RQCE = this.this$0.m409startApiFlow0E7RQCE(this.$apiFlowUIState, true, this);
                        if (m409startApiFlow0E7RQCE == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        ((Result) obj).getValue();
                    }
                    return Unit.INSTANCE;
                }
            }
        }, 4, null);
    }

    private final void handleModuleFailure(final Function1<? super Continuation<? super Unit>, ? extends Object> retryAction) {
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
        sb.append("handleModuleFailure() called");
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
                    Log.println(3, str, "handleModuleFailure() called ");
                }
            }
        }
        this.ignoreBackPress = true;
        FrameLayout frameLayout = getBinding$hyperkyc_release().flContent;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.flContent");
        frameLayout.setVisibility(8);
        showRetry$default(this, true, 111, null, getNetworkErrorMessage(), new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$handleModuleFailure$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
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
                if (ContextExtsKt.isOnline(HKMainActivity.this)) {
                    BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(HKMainActivity.this), null, null, new AnonymousClass1(retryAction, null), 3, null);
                    HKMainActivity.showRetry$default(HKMainActivity.this, false, null, null, null, null, 30, null);
                    FrameLayout frameLayout2 = HKMainActivity.this.getBinding$hyperkyc_release().flContent;
                    Intrinsics.checkNotNullExpressionValue(frameLayout2, "binding.flContent");
                    frameLayout2.setVisibility(0);
                    HKMainActivity.this.setIgnoreBackPress$hyperkyc_release(false);
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: HKMainActivity.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$handleModuleFailure$2$1", f = "HKMainActivity.kt", i = {}, l = {1940}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: co.hyperverge.hyperkyc.ui.HKMainActivity$handleModuleFailure$2$1, reason: invalid class name */
            /* loaded from: classes2.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Function1<Continuation<? super Unit>, Object> $retryAction;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass1(Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$retryAction = function1;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.$retryAction, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Function1<Continuation<? super Unit>, Object> function1 = this.$retryAction;
                        this.label = 1;
                        if (function1.invoke(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x013d, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void handleFaceModuleFailure(WorkflowUIState.FaceCapture faceFlowUIState) {
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
        String str2 = "handleFaceModuleFailure() called with faceFlowUIState = " + faceFlowUIState;
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
                    String str3 = "handleFaceModuleFailure() called with faceFlowUIState = " + faceFlowUIState;
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
        handleModuleFailure(new HKMainActivity$handleFaceModuleFailure$2(this, faceFlowUIState, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x013d, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void handleDocModuleFailure(WorkflowUIState.DocCapture docFlowUIState) {
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
        String str2 = "handleDocModuleFailure() called with docFlowUIState = " + docFlowUIState;
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
                    String str3 = "handleDocModuleFailure() called with docFlowUIState = " + docFlowUIState;
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
        handleModuleFailure(new HKMainActivity$handleDocModuleFailure$2(this, docFlowUIState, null));
    }

    public final void updateEndState$hyperkyc_release(boolean isLoading, Boolean isSuccess, boolean isAPISuccess, Function0<Unit> onFailAction) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(onFailAction, "onFailAction");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
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
        String str2 = "updateEndState() called with: isLoading = " + isLoading + ", isSuccess = " + isSuccess + ", isAPISuccess = " + isAPISuccess + ", onFailAction = " + onFailAction;
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (str = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        str = canonicalName2 == null ? "N/A" : canonicalName2;
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
                    String str3 = "updateEndState() called with: isLoading = " + isLoading + ", isSuccess = " + isSuccess + ", isAPISuccess = " + isAPISuccess + ", onFailAction = " + onFailAction;
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
        Job job = this.loadingUIStateCollectJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.loadingUIStateCollectJob = LifecycleOwnerKt.getLifecycleScope(this).launchWhenCreated(new HKMainActivity$updateEndState$3(isLoading, this, isSuccess, isAPISuccess, onFailAction, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void flowForwardOrFinish() {
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
        sb.append("flowForwardOrFinish() called");
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
                    Log.println(3, str, "flowForwardOrFinish() called ");
                }
            }
        }
        getMainVM().flowForward();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x0603, code lost:
    
        if (r13 != null) goto L256;
     */
    /* JADX WARN: Code restructure failed: missing block: B:231:0x070a, code lost:
    
        if (r0 != null) goto L301;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x041d, code lost:
    
        if (r12 != null) goto L176;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0430  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x042d  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0445  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0486  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x059e  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x05b3  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x05b9  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x05a1  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x04a2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:172:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x05c7  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0428  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void processHVBridgeError(String method, Throwable t, Integer errorCode, String errorMsg) {
        String canonicalName;
        String str;
        String str2;
        Object m1202constructorimpl;
        String str3;
        String str4;
        String canonicalName2;
        String className;
        String str5;
        String str6;
        String str7;
        Object m1202constructorimpl2;
        String str8;
        String str9;
        String className2;
        String className3;
        String str10;
        String str11;
        Object m1202constructorimpl3;
        String str12;
        boolean z;
        StackTraceElement stackTraceElement;
        String str13;
        String str14;
        Matcher matcher;
        String str15;
        Object m1202constructorimpl4;
        String str16;
        String className4;
        String substringAfterLast$default;
        String className5;
        String canonicalName3;
        String className6;
        String className7;
        String className8;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement2 == null || (className8 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className8, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        String str17 = "";
        if (matcher2.find()) {
            canonicalName = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        Unit unit = Unit.INSTANCE;
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str18 = "processHVBridgeError() called with: method = " + method + ", t = " + t + ", errorCode = " + errorCode + ", errorMsg = " + errorMsg;
        if (str18 == null) {
            str18 = "null ";
        }
        sb.append(str18);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            str3 = "packageName";
            str4 = "null cannot be cast to non-null type android.app.Application";
            str = "N/A";
            str2 = "null ";
        } else {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                str = "N/A";
                str2 = "null ";
            } catch (Throwable th) {
                th = th;
                str = "N/A";
                str2 = "null ";
            }
            try {
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th2) {
                th = th2;
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                }
                String packageName = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                }
                if (!(t instanceof HSBridgeException)) {
                }
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName2 = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                str3 = "packageName";
                str4 = "null cannot be cast to non-null type android.app.Application";
            } else {
                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                str3 = "packageName";
                str4 = "null cannot be cast to non-null type android.app.Application";
                if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement3 == null || (className = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = str;
                        }
                    }
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher3.find()) {
                        canonicalName2 = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    Unit unit2 = Unit.INSTANCE;
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str19 = "processHVBridgeError() called with: method = " + method + ", t = " + t + ", errorCode = " + errorCode + ", errorMsg = " + errorMsg;
                    if (str19 == null) {
                        str19 = str2;
                    }
                    sb2.append(str19);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                }
            }
        }
        if (!(t instanceof HSBridgeException)) {
            HyperLogger.Level level2 = HyperLogger.Level.ERROR;
            HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb3 = new StringBuilder();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement4 == null || (className7 = stackTraceElement4.getClassName()) == null || (str10 = StringsKt.substringAfterLast$default(className7, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls3 = getClass();
                String canonicalName4 = cls3 != null ? cls3.getCanonicalName() : null;
                str10 = canonicalName4 == null ? str : canonicalName4;
            }
            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str10);
            if (matcher4.find()) {
                str10 = matcher4.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str10, "replaceAll(\"\")");
            }
            Unit unit3 = Unit.INSTANCE;
            if (str10.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str10 = str10.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str10, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb3.append(str10);
            sb3.append(" - ");
            StringBuilder sb4 = new StringBuilder();
            sb4.append("processHVBridgeError: ");
            sb4.append(method);
            sb4.append(": hvError: ");
            HSBridgeException hSBridgeException = (HSBridgeException) t;
            HVError hvError = hSBridgeException.getHvError();
            sb4.append(hvError != null ? hvError.getErrorMessage() : null);
            sb4.append(", hvResponse: ");
            sb4.append(hSBridgeException.getHvResponse());
            String sb5 = sb4.toString();
            if (sb5 == null) {
                sb5 = str2;
            }
            sb3.append(sb5);
            sb3.append(' ');
            sb3.append("");
            companion4.log(level2, sb3.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion5 = Result.INSTANCE;
                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                str11 = str4;
                try {
                    Intrinsics.checkNotNull(invoke2, str11);
                    m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                } catch (Throwable th3) {
                    th = th3;
                    Result.Companion companion6 = Result.INSTANCE;
                    m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                    }
                    String str20 = (String) m1202constructorimpl3;
                    if (CoreExtsKt.isDebug()) {
                    }
                    HVError hvError2 = hSBridgeException.getHvError();
                    if (hvError2 == null) {
                    }
                    boolean flowBack = z ? getMainVM().flowBack() : true;
                    HyperLogger.Level level3 = HyperLogger.Level.DEBUG;
                    HyperLogger companion7 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb6 = new StringBuilder();
                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                    stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                    if (stackTraceElement != null) {
                    }
                    str13 = "Throwable().stackTrace";
                    Class<?> cls4 = getClass();
                    if (cls4 == null) {
                    }
                    if (r4 != null) {
                    }
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str14);
                    if (matcher.find()) {
                    }
                    Unit unit4 = Unit.INSTANCE;
                    if (str14.length() > 23) {
                        str14 = str14.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str14, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb6.append(str14);
                    sb6.append(" - ");
                    str15 = "processHVBridgeError() isUserCancellation: " + z + ", flowBackHandled: " + flowBack;
                    if (str15 == null) {
                    }
                    sb6.append(str15);
                    sb6.append(' ');
                    sb6.append("");
                    companion7.log(level3, sb6.toString());
                    if (!CoreExtsKt.isRelease()) {
                    }
                    if (z) {
                    }
                    BaseActivity.finishWithResult$default(this, !z ? HyperKycStatus.USER_CANCELLED : "error", null, Integer.valueOf(HyperKycError.INSTANCE.mapIfRequired(errorCode)), errorMsg != null ? t.getMessage() : errorMsg, false, false, 50, null);
                    return;
                }
            } catch (Throwable th4) {
                th = th4;
                str11 = str4;
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                m1202constructorimpl3 = "";
            }
            String str202 = (String) m1202constructorimpl3;
            if (CoreExtsKt.isDebug()) {
                str12 = str3;
            } else {
                str12 = str3;
                Intrinsics.checkNotNullExpressionValue(str202, str12);
                if (StringsKt.contains$default((CharSequence) str202, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                    if (stackTraceElement5 == null || (className6 = stackTraceElement5.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls5 = getClass();
                        canonicalName3 = cls5 != null ? cls5.getCanonicalName() : null;
                        if (canonicalName3 == null) {
                            canonicalName3 = str;
                        }
                    }
                    Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                    if (matcher5.find()) {
                        canonicalName3 = matcher5.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                    }
                    Unit unit5 = Unit.INSTANCE;
                    if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName3 = canonicalName3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb7 = new StringBuilder();
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append("processHVBridgeError: ");
                    sb8.append(method);
                    sb8.append(": hvError: ");
                    HVError hvError3 = hSBridgeException.getHvError();
                    sb8.append(hvError3 != null ? hvError3.getErrorMessage() : null);
                    sb8.append(", hvResponse: ");
                    sb8.append(hSBridgeException.getHvResponse());
                    String sb9 = sb8.toString();
                    if (sb9 == null) {
                        sb9 = str2;
                    }
                    sb7.append(sb9);
                    sb7.append(' ');
                    sb7.append("");
                    Log.println(6, canonicalName3, sb7.toString());
                }
            }
            HVError hvError22 = hSBridgeException.getHvError();
            z = hvError22 == null && hvError22.getErrorCode() == 3;
            boolean flowBack2 = z ? getMainVM().flowBack() : true;
            HyperLogger.Level level32 = HyperLogger.Level.DEBUG;
            HyperLogger companion72 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb62 = new StringBuilder();
            StackTraceElement[] stackTrace42 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace42, "Throwable().stackTrace");
            stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace42);
            if (stackTraceElement != null || (className5 = stackTraceElement.getClassName()) == null) {
                str13 = "Throwable().stackTrace";
            } else {
                str13 = "Throwable().stackTrace";
                str14 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            Class<?> cls42 = getClass();
            String canonicalName5 = cls42 == null ? cls42.getCanonicalName() : null;
            str14 = canonicalName5 != null ? str : canonicalName5;
            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str14);
            if (matcher.find()) {
                str14 = matcher.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str14, "replaceAll(\"\")");
            }
            Unit unit42 = Unit.INSTANCE;
            if (str14.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str14 = str14.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str14, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb62.append(str14);
            sb62.append(" - ");
            str15 = "processHVBridgeError() isUserCancellation: " + z + ", flowBackHandled: " + flowBack2;
            if (str15 == null) {
                str15 = str2;
            }
            sb62.append(str15);
            sb62.append(' ');
            sb62.append("");
            companion72.log(level32, sb62.toString());
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion8 = Result.INSTANCE;
                    Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke3, str11);
                    m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                } catch (Throwable th5) {
                    Result.Companion companion9 = Result.INSTANCE;
                    m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th5));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                    m1202constructorimpl4 = "";
                }
                String str21 = (String) m1202constructorimpl4;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(str21, str12);
                    if (StringsKt.contains$default((CharSequence) str21, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace6, str13);
                        StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                        if (stackTraceElement6 == null || (className4 = stackTraceElement6.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls6 = getClass();
                            String canonicalName6 = cls6 != null ? cls6.getCanonicalName() : null;
                            str16 = canonicalName6 == null ? str : canonicalName6;
                        } else {
                            str16 = substringAfterLast$default;
                        }
                        Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str16);
                        if (matcher6.find()) {
                            str16 = matcher6.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str16, "replaceAll(\"\")");
                        }
                        Unit unit6 = Unit.INSTANCE;
                        if (str16.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str16 = str16.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str16, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb10 = new StringBuilder();
                        String str22 = "processHVBridgeError() isUserCancellation: " + z + ", flowBackHandled: " + flowBack2;
                        sb10.append(str22 == null ? str2 : str22);
                        sb10.append(' ');
                        sb10.append("");
                        Log.println(4, str16, sb10.toString());
                    }
                }
            }
            if (z || !flowBack2) {
                BaseActivity.finishWithResult$default(this, !z ? HyperKycStatus.USER_CANCELLED : "error", null, Integer.valueOf(HyperKycError.INSTANCE.mapIfRequired(errorCode)), errorMsg != null ? t.getMessage() : errorMsg, false, false, 50, null);
                return;
            }
            return;
        }
        String str23 = str3;
        String str24 = str4;
        HyperLogger.Level level4 = HyperLogger.Level.ERROR;
        HyperLogger companion10 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb11 = new StringBuilder();
        StackTraceElement[] stackTrace7 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace7, "Throwable().stackTrace");
        StackTraceElement stackTraceElement7 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace7);
        if (stackTraceElement7 == null || (className3 = stackTraceElement7.getClassName()) == null) {
            str5 = "Throwable().stackTrace";
        } else {
            str5 = "Throwable().stackTrace";
            str6 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        }
        Class<?> cls7 = getClass();
        String canonicalName7 = cls7 != null ? cls7.getCanonicalName() : null;
        str6 = canonicalName7 == null ? str : canonicalName7;
        Matcher matcher7 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
        if (matcher7.find()) {
            str6 = matcher7.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
        }
        Unit unit7 = Unit.INSTANCE;
        if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
            str6 = str6.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb11.append(str6);
        sb11.append(" - ");
        String str25 = "processHVBridgeError: " + method;
        if (str25 == null) {
            str25 = str2;
        }
        sb11.append(str25);
        sb11.append(' ');
        String localizedMessage = t != null ? t.getLocalizedMessage() : null;
        if (localizedMessage != null) {
            str7 = '\n' + localizedMessage;
        } else {
            str7 = "";
        }
        sb11.append(str7);
        companion10.log(level4, sb11.toString());
        CoreExtsKt.isRelease();
        try {
            Result.Companion companion11 = Result.INSTANCE;
            Object invoke4 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            Intrinsics.checkNotNull(invoke4, str24);
            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
        } catch (Throwable th6) {
            Result.Companion companion12 = Result.INSTANCE;
            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th6));
        }
        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
            m1202constructorimpl2 = "";
        }
        String str26 = (String) m1202constructorimpl2;
        if (CoreExtsKt.isDebug()) {
            Intrinsics.checkNotNullExpressionValue(str26, str23);
            if (StringsKt.contains$default((CharSequence) str26, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                StackTraceElement[] stackTrace8 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace8, str5);
                StackTraceElement stackTraceElement8 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace8);
                if (stackTraceElement8 == null || (className2 = stackTraceElement8.getClassName()) == null) {
                    str8 = null;
                } else {
                    str8 = null;
                    str9 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                }
                Class<?> cls8 = getClass();
                str9 = cls8 != null ? cls8.getCanonicalName() : str8;
                if (str9 == null) {
                    str9 = str;
                }
                Matcher matcher8 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str9);
                if (matcher8.find()) {
                    str9 = matcher8.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str9, "replaceAll(\"\")");
                }
                Unit unit8 = Unit.INSTANCE;
                if (str9.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str9 = str9.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                StringBuilder sb12 = new StringBuilder();
                String str27 = "processHVBridgeError: " + method;
                sb12.append(str27 == null ? str2 : str27);
                sb12.append(' ');
                String localizedMessage2 = t != null ? t.getLocalizedMessage() : str8;
                if (localizedMessage2 != null) {
                    str17 = '\n' + localizedMessage2;
                }
                sb12.append(str17);
                Log.println(6, str9, sb12.toString());
            }
        }
        BaseActivity.finishWithResult$default(this, "error", null, errorCode, t.getMessage(), false, false, 50, null);
    }

    private static final void validateInputs$finishWithError(HKMainActivity hKMainActivity, String str) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        Class<?> cls2;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str2 = "N/A";
        String str3 = null;
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (hKMainActivity == null || (cls = hKMainActivity.getClass()) == null) ? null : cls.getCanonicalName();
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
        String str4 = "finishWithError() called with: errorMsg = " + str;
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        if (hKMainActivity != null && (cls2 = hKMainActivity.getClass()) != null) {
                            str3 = cls2.getCanonicalName();
                        }
                        if (str3 != null) {
                            str2 = str3;
                        }
                    } else {
                        str2 = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher2.find()) {
                        str2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str5 = "finishWithError() called with: errorMsg = " + str;
                    if (str5 == null) {
                        str5 = "null ";
                    }
                    sb2.append(str5);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str2, sb2.toString());
                }
            }
        }
        if (hKMainActivity.isFinishing()) {
            return;
        }
        BaseActivity.finishWithResult$default(hKMainActivity, "error", null, 102, str, false, false, 16, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0148, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void showJsonExceptionRetry$lambda$50$lambda$46(HkActivityMainBinding this_with, HKMainActivity this$0, Function0 function0, View view) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = this_with.getClass();
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
        String str2 = "showJsonExceptionRetry: onRetry clicked " + this$0.jsonErrorRetryCount;
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
                        Class<?> cls2 = this_with.getClass();
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
                    String str3 = "showJsonExceptionRetry: onRetry clicked " + this$0.jsonErrorRetryCount;
                    sb2.append(str3 != null ? str3 : "null ");
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        this$0.jsonErrorRetryCount++;
        if (function0 == null) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), null, null, new HKMainActivity$showJsonExceptionRetry$2$1$2(this$0, null), 3, null);
        } else {
            function0.invoke();
        }
    }
}
