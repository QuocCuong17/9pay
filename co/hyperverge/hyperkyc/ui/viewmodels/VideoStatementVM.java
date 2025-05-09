package co.hyperverge.hyperkyc.ui.viewmodels;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import androidx.browser.trusted.sharing.ShareTarget;
import androidx.media3.exoplayer.ExoPlayer;
import co.hyperverge.hyperkyc.core.RuleEvaluatorKt;
import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
import co.hyperverge.hyperkyc.data.models.KycCountry;
import co.hyperverge.hyperkyc.data.models.VideoStatementConfig;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.data.network.NetworkRepo;
import co.hyperverge.hyperkyc.hvsessionrecorder.HVAudioRecorder;
import co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.JSONExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import co.hyperverge.hypersnapsdk.components.camera.HVFacePreview;
import co.hyperverge.hypersnapsdk.components.camera.model.FaceStateUIFlow;
import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.google.gson.Gson;
import com.tekartik.sqflite.Constant;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.NotImplementedError;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: VideoStatementVM.kt */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0000\u0018\u0000 z2\u00020\u0001:\u0003yz{B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010&\u001a\u00020'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00100\u00142\u0006\u0010)\u001a\u00020*H\u0002J$\u0010+\u001a\u00020\u00102\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010-2\u0006\u0010.\u001a\u00020\rH\u0002J\u0012\u0010/\u001a\u00020'2\b\u00100\u001a\u0004\u0018\u00010\bH\u0002J$\u00101\u001a\u00020\u00102\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010-2\u0006\u0010.\u001a\u00020\rH\u0002J,\u00102\u001a\u00020\u00102\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010-2\u0006\u0010.\u001a\u00020\r2\u0006\u00103\u001a\u00020\bH\u0002J\"\u00104\u001a\u00020'2\b\b\u0002\u0010.\u001a\u00020\r2\u0006\u00105\u001a\u00020\b2\u0006\u00106\u001a\u00020\u0001H\u0002J\u0006\u00107\u001a\u00020\u0010J\u0006\u00108\u001a\u00020\u0010J\b\u00109\u001a\u00020'H\u0002J\u0011\u0010:\u001a\u00020\u0010H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010;J\b\u0010<\u001a\u00020\bH\u0002J\b\u0010=\u001a\u0004\u0018\u00010\nJ\u0006\u0010>\u001a\u00020\bJ\b\u0010?\u001a\u00020#H\u0002J\u0006\u0010@\u001a\u00020\u0016J\u001c\u0010A\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0-2\u0006\u0010)\u001a\u00020*H\u0002J\u0006\u0010B\u001a\u00020\u0010J\u0006\u0010C\u001a\u00020#J\b\u0010D\u001a\u00020\bH\u0002J\"\u0010E\u001a\u00020'2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020\b2\b\u0010I\u001a\u0004\u0018\u00010\bH\u0002J\u0006\u0010J\u001a\u00020\u0010J\b\u0010K\u001a\u00020'H\u0002J\u0012\u0010L\u001a\u0004\u0018\u00010\b2\b\u0010M\u001a\u0004\u0018\u00010\bJ\b\u0010N\u001a\u00020\u0010H\u0002J\b\u0010O\u001a\u00020\u0010H\u0002J\b\u0010P\u001a\u00020\u0010H\u0002J\b\u0010Q\u001a\u00020\u0010H\u0002J\u0010\u0010R\u001a\u00020'2\u0006\u0010S\u001a\u00020\bH\u0002J\u0010\u0010T\u001a\u00020'2\u0006\u0010S\u001a\u00020\bH\u0002J\u0011\u0010U\u001a\u00020\u0010H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010;J!\u0010V\u001a\u00020'2\u0006\u0010W\u001a\u00020\b2\u0006\u00103\u001a\u00020\bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010XJ\u0006\u0010Y\u001a\u00020'J\u000e\u0010Z\u001a\u00020'2\u0006\u0010[\u001a\u00020\\J-\u0010]\u001a\u00020\u00102\b\u00100\u001a\u0004\u0018\u00010\b2\b\u0010^\u001a\u0004\u0018\u00010_2\u0006\u0010)\u001a\u00020*H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010`J\u000e\u0010a\u001a\u00020'2\u0006\u00103\u001a\u00020\bJ\u0010\u0010b\u001a\u00020'2\u0006\u0010c\u001a\u00020\u0010H\u0002J\u000e\u0010d\u001a\u00020'2\u0006\u0010e\u001a\u00020#J\u0010\u0010f\u001a\u00020'2\u0006\u0010g\u001a\u00020hH\u0002J!\u0010i\u001a\u00020'2\u0006\u0010j\u001a\u00020k2\u0006\u0010[\u001a\u00020\\H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010lJ\u0010\u0010m\u001a\u00020\b2\u0006\u0010g\u001a\u00020hH\u0002J\u0010\u0010n\u001a\u00020\b2\u0006\u0010o\u001a\u00020\bH\u0002J\u0011\u0010p\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010;J\u0019\u0010q\u001a\u00020\u00102\u0006\u0010r\u001a\u00020\u0016H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010sJ\u0016\u0010t\u001a\u00020\u0010*\u0004\u0018\u00010\b2\u0006\u0010u\u001a\u00020\u0010H\u0002J\f\u0010v\u001a\u00020\b*\u00020\u0010H\u0002J\u0016\u0010w\u001a\u00020\b*\u00020\b2\b\b\u0002\u0010x\u001a\u00020\u0010H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00140\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00140\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010!\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00140\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006|"}, d2 = {"Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementVM;", "", "videoStatementUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$VideoStatement;", "mainVM", "Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "(Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$VideoStatement;Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;)V", "currentAnswerText", "", "currentStatement", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Statement;", "currentStatementId", "currentStatementIndex", "", "faceDetectionResults", "", "", "faceDetectionTimer", "Ljava/util/Timer;", "faceMatchCallsDeferred", "Lkotlinx/coroutines/Deferred;", "failedCheck", "Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementVM$Check;", "finalLogMap", "isFaceDetectionPass", "isFaceDetectionTimedOut", "isFirstStatement", "isMaximumRestartsExceeded", "isSelfieCaptured", "livenessCallsDeferred", "noOfRestarts", "refImageUri", "shouldListenForFaceDetection", "speechToTextMatchCallsDeferred", "startTimestamp", "", "videoStatementConfig", "Lco/hyperverge/hyperkyc/data/models/VideoStatementConfig;", "addDeferred", "", "deferred", "requestType", "Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementVM$RequestType;", "addFaceMatchDataToLog", "responseMap", "", FirebaseAnalytics.Param.INDEX, "addImage", "url", "addLivenessDataToLog", "addSpeechToTextMatchDataToLog", "text", "addValueInLogMap", "key", "value", "checkIfStatementEnabled", "checkIsEndOfStatements", "endStatement", "evaluate", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "formatSecondsToHHMMSS", "getCurrentStatement", "getCurrentStatementId", "getFaceDetectionTimeout", "getFailedCheck", "getHeaders", "getShouldDisplayTimer", "getStatementDuration", "getStatementIdWithRestartCount", "handleLogVideoStatementResult", "response", "Lokhttp3/Response;", "isPass", "responseRaw", "handleRetry", "handleSelfieNotCaptured", "injectValuesFromDataMap", SDKConstants.PARAM_UPDATE_TEMPLATE, "isFaceDetectionEnabled", "isFaceMatchCheckEnabled", "isLivenessCheckEnabled", "isSpeechToTextMatchEnabled", "makeFaceMatchAPICall", HVRetakeActivity.IMAGE_URI_TAG, "makeLivenessAPICall", "makeLogVideoStatementAPICall", "makeSpeechToTextMatchAPICall", "audioUri", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "moveToNextStatement", "observeUIState", "facePreview", "Lco/hyperverge/hypersnapsdk/components/camera/HVFacePreview;", "sendRequest", "requestBody", "Lokhttp3/RequestBody;", "(Ljava/lang/String;Lokhttp3/RequestBody;Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementVM$RequestType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setCurrentAnswerText", "setIsFaceDetectionPass", "isPassed", "setStartTimestamp", "timeStamp", "startAudioRecording", "filesDir", "Ljava/io/File;", "startStatement", "context", "Landroid/content/Context;", "(Landroid/content/Context;Lco/hyperverge/hypersnapsdk/components/camera/HVFacePreview;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopAudioRecording", "toBase64", ShareConstants.MEDIA_URI, "verifyAsyncChecks", "verifySyncCheck", "check", "(Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementVM$Check;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "asBoolean", "default", "asString", "injectFromVariables", "addQuotesIfEmpty", "Check", "Companion", "RequestType", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VideoStatementVM {
    public static final String END_OF_STATEMENTS = "END_OF_STATEMENTS";
    public static final String base64Header = "data:image/jpeg;base64,";
    private String currentAnswerText;
    private WorkflowModule.Properties.Statement currentStatement;
    private String currentStatementId;
    private int currentStatementIndex;
    private Map<String, Boolean> faceDetectionResults;
    private Timer faceDetectionTimer;
    private Map<String, Deferred<Boolean>> faceMatchCallsDeferred;
    private Check failedCheck;
    private Map<String, Object> finalLogMap;
    private boolean isFaceDetectionPass;
    private boolean isFaceDetectionTimedOut;
    private boolean isFirstStatement;
    private boolean isMaximumRestartsExceeded;
    private boolean isSelfieCaptured;
    private Map<String, Deferred<Boolean>> livenessCallsDeferred;
    private final MainVM mainVM;
    private int noOfRestarts;
    private String refImageUri;
    private boolean shouldListenForFaceDetection;
    private Map<String, Deferred<Boolean>> speechToTextMatchCallsDeferred;
    private long startTimestamp;
    private final VideoStatementConfig videoStatementConfig;
    private final WorkflowUIState.VideoStatement videoStatementUIState;

    /* compiled from: VideoStatementVM.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementVM$Check;", "", "(Ljava/lang/String;I)V", "LIVENESS", "FACE_MATCH", "SPEECH_TO_TEXT_MATCH", "FACE_DETECTION", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public enum Check {
        LIVENESS,
        FACE_MATCH,
        SPEECH_TO_TEXT_MATCH,
        FACE_DETECTION
    }

    /* compiled from: VideoStatementVM.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementVM$RequestType;", "", "(Ljava/lang/String;I)V", "LIVENESS", "FACE_MATCH", "SPEECH_TO_TEXT_MATCH", "LOG_VIDEO_STATEMENT", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public enum RequestType {
        LIVENESS,
        FACE_MATCH,
        SPEECH_TO_TEXT_MATCH,
        LOG_VIDEO_STATEMENT
    }

    /* compiled from: VideoStatementVM.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Check.values().length];
            try {
                iArr[Check.FACE_DETECTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Check.LIVENESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Check.FACE_MATCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Check.SPEECH_TO_TEXT_MATCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[RequestType.values().length];
            try {
                iArr2[RequestType.LIVENESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[RequestType.FACE_MATCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[RequestType.SPEECH_TO_TEXT_MATCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[RequestType.LOG_VIDEO_STATEMENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public VideoStatementVM(WorkflowUIState.VideoStatement videoStatementUIState, MainVM mainVM) {
        Intrinsics.checkNotNullParameter(videoStatementUIState, "videoStatementUIState");
        Intrinsics.checkNotNullParameter(mainVM, "mainVM");
        this.videoStatementUIState = videoStatementUIState;
        this.mainVM = mainVM;
        VideoStatementConfig vsConfig = videoStatementUIState.getVsConfig();
        this.videoStatementConfig = vsConfig;
        this.currentStatementId = vsConfig.getStart();
        Map<String, WorkflowModule.Properties.Statement> statements = vsConfig.getStatements();
        this.currentStatement = statements != null ? statements.get(this.currentStatementId) : null;
        this.startTimestamp = System.currentTimeMillis();
        this.currentAnswerText = "";
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("videoRef", mainVM.getJourneyId$hyperkyc_release());
        linkedHashMap.put("transactionId", mainVM.getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release());
        linkedHashMap.put("statements", new ArrayList());
        linkedHashMap.put("images", new LinkedHashMap());
        this.finalLogMap = linkedHashMap;
        this.isFaceDetectionPass = true;
        this.isFirstStatement = true;
        this.faceDetectionResults = new LinkedHashMap();
        this.livenessCallsDeferred = new LinkedHashMap();
        this.faceMatchCallsDeferred = new LinkedHashMap();
        this.speechToTextMatchCallsDeferred = new LinkedHashMap();
        this.refImageUri = "";
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x01ae, code lost:
    
        if (r1 != null) goto L72;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x02b3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0288 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0031  */
    /* JADX WARN: Type inference failed for: r1v30, types: [T] */
    /* JADX WARN: Type inference failed for: r1v40, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v42 */
    /* JADX WARN: Type inference failed for: r1v43 */
    /* JADX WARN: Type inference failed for: r1v57, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v65 */
    /* JADX WARN: Type inference failed for: r5v10, types: [T] */
    /* JADX WARN: Type inference failed for: r5v16, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object startStatement(Context context, HVFacePreview hVFacePreview, Continuation<? super Unit> continuation) {
        VideoStatementVM$startStatement$1 videoStatementVM$startStatement$1;
        VideoStatementVM$startStatement$1 videoStatementVM$startStatement$12;
        int i;
        ?? canonicalName;
        String str;
        Object m1202constructorimpl;
        Object obj;
        String str2;
        String str3;
        String str4;
        String className;
        long statementDuration;
        Object obj2;
        VideoStatementVM videoStatementVM;
        String className2;
        String stopAudioRecording;
        String str5;
        Context context2 = context;
        if (continuation instanceof VideoStatementVM$startStatement$1) {
            videoStatementVM$startStatement$1 = (VideoStatementVM$startStatement$1) continuation;
            if ((videoStatementVM$startStatement$1.label & Integer.MIN_VALUE) != 0) {
                videoStatementVM$startStatement$1.label -= Integer.MIN_VALUE;
                videoStatementVM$startStatement$12 = videoStatementVM$startStatement$1;
                Object obj3 = videoStatementVM$startStatement$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = videoStatementVM$startStatement$12.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj3);
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
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
                    String str6 = "startStatement() called with: context = " + context2 + ", facePreview = " + hVFacePreview;
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
                            obj = coroutine_suspended;
                            if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                                    str2 = null;
                                } else {
                                    str2 = null;
                                    String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    str3 = substringAfterLast$default;
                                }
                                Class<?> cls2 = getClass();
                                String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : str2;
                                str3 = canonicalName2 == null ? "N/A" : canonicalName2;
                                objectRef2.element = str3;
                                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                if (matcher2.find()) {
                                    ?? replaceAll2 = matcher2.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                    objectRef2.element = replaceAll2;
                                }
                                if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                    str4 = (String) objectRef2.element;
                                } else {
                                    str4 = ((String) objectRef2.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb2 = new StringBuilder();
                                String str7 = "startStatement() called with: context = " + context2 + ", facePreview = " + hVFacePreview;
                                if (str7 == null) {
                                    str7 = "null ";
                                }
                                sb2.append(str7);
                                sb2.append(' ');
                                sb2.append("");
                                Log.println(3, str4, sb2.toString());
                                addValueInLogMap$default(this, 0, "statementId", getStatementIdWithRestartCount(), 1, null);
                                addValueInLogMap$default(this, 0, "startTimestamp", formatSecondsToHHMMSS(), 1, null);
                                File filesDir = context.getFilesDir();
                                Intrinsics.checkNotNullExpressionValue(filesDir, "context.filesDir");
                                startAudioRecording(filesDir);
                                this.shouldListenForFaceDetection = true;
                                this.isFaceDetectionPass = false;
                                this.isSelfieCaptured = false;
                                this.isFaceDetectionTimedOut = false;
                                if (Intrinsics.areEqual(hVFacePreview.getUiStateFlow().getValue(), FaceStateUIFlow.Detected.INSTANCE)) {
                                    this.isFaceDetectionPass = true;
                                    hVFacePreview.clickPicture();
                                }
                                statementDuration = getStatementDuration();
                                videoStatementVM$startStatement$12.L$0 = this;
                                videoStatementVM$startStatement$12.L$1 = context2;
                                videoStatementVM$startStatement$12.label = 1;
                                obj2 = obj;
                                if (DelayKt.delay(statementDuration, videoStatementVM$startStatement$12) == obj2) {
                                    return obj2;
                                }
                                videoStatementVM = this;
                            }
                            str2 = null;
                            addValueInLogMap$default(this, 0, "statementId", getStatementIdWithRestartCount(), 1, null);
                            addValueInLogMap$default(this, 0, "startTimestamp", formatSecondsToHHMMSS(), 1, null);
                            File filesDir2 = context.getFilesDir();
                            Intrinsics.checkNotNullExpressionValue(filesDir2, "context.filesDir");
                            startAudioRecording(filesDir2);
                            this.shouldListenForFaceDetection = true;
                            this.isFaceDetectionPass = false;
                            this.isSelfieCaptured = false;
                            this.isFaceDetectionTimedOut = false;
                            if (Intrinsics.areEqual(hVFacePreview.getUiStateFlow().getValue(), FaceStateUIFlow.Detected.INSTANCE)) {
                            }
                            statementDuration = getStatementDuration();
                            videoStatementVM$startStatement$12.L$0 = this;
                            videoStatementVM$startStatement$12.L$1 = context2;
                            videoStatementVM$startStatement$12.label = 1;
                            obj2 = obj;
                            if (DelayKt.delay(statementDuration, videoStatementVM$startStatement$12) == obj2) {
                            }
                        }
                    }
                    obj = coroutine_suspended;
                    str2 = null;
                    addValueInLogMap$default(this, 0, "statementId", getStatementIdWithRestartCount(), 1, null);
                    addValueInLogMap$default(this, 0, "startTimestamp", formatSecondsToHHMMSS(), 1, null);
                    File filesDir22 = context.getFilesDir();
                    Intrinsics.checkNotNullExpressionValue(filesDir22, "context.filesDir");
                    startAudioRecording(filesDir22);
                    this.shouldListenForFaceDetection = true;
                    this.isFaceDetectionPass = false;
                    this.isSelfieCaptured = false;
                    this.isFaceDetectionTimedOut = false;
                    if (Intrinsics.areEqual(hVFacePreview.getUiStateFlow().getValue(), FaceStateUIFlow.Detected.INSTANCE)) {
                    }
                    statementDuration = getStatementDuration();
                    videoStatementVM$startStatement$12.L$0 = this;
                    videoStatementVM$startStatement$12.L$1 = context2;
                    videoStatementVM$startStatement$12.label = 1;
                    obj2 = obj;
                    if (DelayKt.delay(statementDuration, videoStatementVM$startStatement$12) == obj2) {
                    }
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj3);
                        return Unit.INSTANCE;
                    }
                    Context context3 = (Context) videoStatementVM$startStatement$12.L$1;
                    videoStatementVM = (VideoStatementVM) videoStatementVM$startStatement$12.L$0;
                    ResultKt.throwOnFailure(obj3);
                    context2 = context3;
                    obj2 = coroutine_suspended;
                    str2 = null;
                }
                if (!videoStatementVM.isSelfieCaptured) {
                    videoStatementVM.handleSelfieNotCaptured();
                }
                videoStatementVM.shouldListenForFaceDetection = false;
                videoStatementVM.setIsFaceDetectionPass(videoStatementVM.isFaceDetectionPass);
                File filesDir3 = context2.getFilesDir();
                Intrinsics.checkNotNullExpressionValue(filesDir3, "context.filesDir");
                stopAudioRecording = videoStatementVM.stopAudioRecording(filesDir3);
                str5 = videoStatementVM.currentAnswerText;
                videoStatementVM$startStatement$12.L$0 = str2;
                videoStatementVM$startStatement$12.L$1 = str2;
                videoStatementVM$startStatement$12.label = 2;
                if (videoStatementVM.makeSpeechToTextMatchAPICall(stopAudioRecording, str5, videoStatementVM$startStatement$12) == obj2) {
                    return obj2;
                }
                return Unit.INSTANCE;
            }
        }
        videoStatementVM$startStatement$1 = new VideoStatementVM$startStatement$1(this, continuation);
        videoStatementVM$startStatement$12 = videoStatementVM$startStatement$1;
        Object obj32 = videoStatementVM$startStatement$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = videoStatementVM$startStatement$12.label;
        if (i != 0) {
        }
        if (!videoStatementVM.isSelfieCaptured) {
        }
        videoStatementVM.shouldListenForFaceDetection = false;
        videoStatementVM.setIsFaceDetectionPass(videoStatementVM.isFaceDetectionPass);
        File filesDir32 = context2.getFilesDir();
        Intrinsics.checkNotNullExpressionValue(filesDir32, "context.filesDir");
        stopAudioRecording = videoStatementVM.stopAudioRecording(filesDir32);
        str5 = videoStatementVM.currentAnswerText;
        videoStatementVM$startStatement$12.L$0 = str2;
        videoStatementVM$startStatement$12.L$1 = str2;
        videoStatementVM$startStatement$12.label = 2;
        if (videoStatementVM.makeSpeechToTextMatchAPICall(stopAudioRecording, str5, videoStatementVM$startStatement$12) == obj2) {
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01c5  */
    /* JADX WARN: Type inference failed for: r13v10, types: [T] */
    /* JADX WARN: Type inference failed for: r13v15, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r13v16 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r15v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v2, types: [T] */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r4v24, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v16, types: [T, java.lang.Object, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object evaluate(Continuation<? super Boolean> continuation) {
        VideoStatementVM$evaluate$1 videoStatementVM$evaluate$1;
        Object obj;
        int i;
        ?? canonicalName;
        String str;
        Object m1202constructorimpl;
        String str2;
        Matcher matcher;
        String str3;
        String className;
        VideoStatementVM videoStatementVM;
        String className2;
        VideoStatementVM videoStatementVM2;
        if (continuation instanceof VideoStatementVM$evaluate$1) {
            videoStatementVM$evaluate$1 = (VideoStatementVM$evaluate$1) continuation;
            if ((videoStatementVM$evaluate$1.label & Integer.MIN_VALUE) != 0) {
                videoStatementVM$evaluate$1.label -= Integer.MIN_VALUE;
                obj = videoStatementVM$evaluate$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = videoStatementVM$evaluate$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    ?? r15 = "N/A";
                    if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                        Class<?> cls = getClass();
                        canonicalName = cls != null ? cls.getCanonicalName() : 0;
                        if (canonicalName == 0) {
                            canonicalName = "N/A";
                        }
                    }
                    objectRef.element = canonicalName;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                    if (matcher2.find()) {
                        ?? replaceAll = matcher2.replaceAll("");
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
                    sb.append("evaluate() called");
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
                                    String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default != null) {
                                        r15 = substringAfterLast$default;
                                        objectRef2.element = r15;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                        if (matcher.find()) {
                                            ?? replaceAll2 = matcher.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                            objectRef2.element = replaceAll2;
                                        }
                                        if (((String) objectRef2.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                            str3 = (String) objectRef2.element;
                                        } else {
                                            str3 = ((String) objectRef2.element).substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        Log.println(3, str3, "evaluate() called ");
                                    }
                                }
                                Class<?> cls2 = getClass();
                                if (cls2 != null) {
                                    str2 = cls2.getCanonicalName();
                                }
                                if (str2 != null) {
                                    r15 = str2;
                                }
                                objectRef2.element = r15;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                if (matcher.find()) {
                                }
                                if (((String) objectRef2.element).length() > 23) {
                                }
                                str3 = (String) objectRef2.element;
                                Log.println(3, str3, "evaluate() called ");
                            }
                        }
                    }
                    if (checkIsEndOfStatements()) {
                        videoStatementVM$evaluate$1.label = 1;
                        obj = verifyAsyncChecks(videoStatementVM$evaluate$1);
                        return obj == coroutine_suspended ? coroutine_suspended : obj;
                    }
                    Check check = Check.FACE_DETECTION;
                    videoStatementVM$evaluate$1.L$0 = this;
                    videoStatementVM$evaluate$1.label = 2;
                    obj = verifySyncCheck(check, videoStatementVM$evaluate$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    videoStatementVM = this;
                    if (((Boolean) obj).booleanValue()) {
                    }
                } else {
                    if (i == 1) {
                        ResultKt.throwOnFailure(obj);
                    }
                    if (i == 2) {
                        videoStatementVM = (VideoStatementVM) videoStatementVM$evaluate$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        if (((Boolean) obj).booleanValue()) {
                            return Boxing.boxBoolean(false);
                        }
                        Check check2 = Check.LIVENESS;
                        videoStatementVM$evaluate$1.L$0 = videoStatementVM;
                        videoStatementVM$evaluate$1.label = 3;
                        obj = videoStatementVM.verifySyncCheck(check2, videoStatementVM$evaluate$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i == 3) {
                        videoStatementVM = (VideoStatementVM) videoStatementVM$evaluate$1.L$0;
                        ResultKt.throwOnFailure(obj);
                    } else {
                        if (i != 4) {
                            if (i != 5) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            videoStatementVM2 = (VideoStatementVM) videoStatementVM$evaluate$1.L$0;
                            ResultKt.throwOnFailure(obj);
                            if (((Boolean) obj).booleanValue()) {
                                return Boxing.boxBoolean(false);
                            }
                            videoStatementVM2.endStatement();
                            videoStatementVM2.isFirstStatement = false;
                            return Boxing.boxBoolean(true);
                        }
                        videoStatementVM = (VideoStatementVM) videoStatementVM$evaluate$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        if (((Boolean) obj).booleanValue()) {
                            return Boxing.boxBoolean(false);
                        }
                        Check check3 = Check.SPEECH_TO_TEXT_MATCH;
                        videoStatementVM$evaluate$1.L$0 = videoStatementVM;
                        videoStatementVM$evaluate$1.label = 5;
                        obj = videoStatementVM.verifySyncCheck(check3, videoStatementVM$evaluate$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        videoStatementVM2 = videoStatementVM;
                        if (((Boolean) obj).booleanValue()) {
                        }
                    }
                }
                if (((Boolean) obj).booleanValue()) {
                    return Boxing.boxBoolean(false);
                }
                Check check4 = Check.FACE_MATCH;
                videoStatementVM$evaluate$1.L$0 = videoStatementVM;
                videoStatementVM$evaluate$1.label = 4;
                obj = videoStatementVM.verifySyncCheck(check4, videoStatementVM$evaluate$1);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                if (((Boolean) obj).booleanValue()) {
                }
            }
        }
        videoStatementVM$evaluate$1 = new VideoStatementVM$evaluate$1(this, continuation);
        obj = videoStatementVM$evaluate$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = videoStatementVM$evaluate$1.label;
        if (i != 0) {
        }
        if (((Boolean) obj).booleanValue()) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x03ca  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x03b3  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x03af  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x03bc  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x03cf  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x03c1  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x03c4  */
    /* JADX WARN: Type inference failed for: r12v10, types: [T] */
    /* JADX WARN: Type inference failed for: r12v15, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v38 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r14v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2, types: [T] */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r4v23, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v16, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:94:0x03a2 -> B:12:0x03a7). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:97:0x03c4 -> B:26:0x0248). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object verifyAsyncChecks(Continuation<? super Boolean> continuation) {
        VideoStatementVM$verifyAsyncChecks$1 videoStatementVM$verifyAsyncChecks$1;
        int i;
        ?? canonicalName;
        String str;
        Object m1202constructorimpl;
        String str2;
        String className;
        String substringAfterLast$default;
        WorkflowModule.Properties.Statement statement;
        String str3;
        VideoStatementVM videoStatementVM;
        Iterator<Map.Entry<String, WorkflowModule.Properties.Statement>> it;
        String className2;
        WorkflowModule.Properties.Statement statement2;
        String str4;
        Map.Entry<String, WorkflowModule.Properties.Statement> entry;
        int i2;
        boolean z;
        boolean z2;
        int i3;
        WorkflowModule.Properties.Checks checks;
        boolean z3;
        WorkflowModule.Properties.Check speechToTextMatching;
        WorkflowModule.Properties.Checks checks2;
        WorkflowModule.Properties.Check faceMatch;
        boolean z4;
        WorkflowModule.Properties.Check liveness;
        WorkflowModule.Properties.Check faceDetection;
        if (continuation instanceof VideoStatementVM$verifyAsyncChecks$1) {
            videoStatementVM$verifyAsyncChecks$1 = (VideoStatementVM$verifyAsyncChecks$1) continuation;
            if ((videoStatementVM$verifyAsyncChecks$1.label & Integer.MIN_VALUE) != 0) {
                videoStatementVM$verifyAsyncChecks$1.label -= Integer.MIN_VALUE;
                Object obj = videoStatementVM$verifyAsyncChecks$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = videoStatementVM$verifyAsyncChecks$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    ?? r14 = "N/A";
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
                    sb.append("verifyAsyncChecks() called");
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
                                if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls2 = getClass();
                                    String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                                    if (canonicalName2 != null) {
                                        r14 = canonicalName2;
                                    }
                                } else {
                                    r14 = substringAfterLast$default;
                                }
                                objectRef2.element = r14;
                                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                if (matcher2.find()) {
                                    ?? replaceAll2 = matcher2.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                    objectRef2.element = replaceAll2;
                                }
                                if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                    str2 = (String) objectRef2.element;
                                } else {
                                    str2 = ((String) objectRef2.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                Log.println(3, str2, "verifyAsyncChecks() called ");
                            }
                        }
                    }
                    statement = this.currentStatement;
                    str3 = this.currentStatementId;
                    Map<String, WorkflowModule.Properties.Statement> statements = this.videoStatementConfig.getStatements();
                    if (statements == null) {
                        statements = MapsKt.emptyMap();
                    }
                    videoStatementVM = this;
                    it = statements.entrySet().iterator();
                    if (it.hasNext()) {
                    }
                    if (i3 == 0) {
                    }
                    return Boxing.boxBoolean(i3 != 0 ? z : false);
                }
                if (i == 1) {
                    i3 = videoStatementVM$verifyAsyncChecks$1.I$0;
                    entry = (Map.Entry) videoStatementVM$verifyAsyncChecks$1.L$4;
                    it = (Iterator) videoStatementVM$verifyAsyncChecks$1.L$3;
                    str4 = (String) videoStatementVM$verifyAsyncChecks$1.L$2;
                    statement2 = (WorkflowModule.Properties.Statement) videoStatementVM$verifyAsyncChecks$1.L$1;
                    videoStatementVM = (VideoStatementVM) videoStatementVM$verifyAsyncChecks$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    z = true;
                    if (((Boolean) obj).booleanValue()) {
                    }
                    statement = statement2;
                    if (z4) {
                    }
                } else {
                    if (i != 2) {
                        if (i != 3) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        i3 = videoStatementVM$verifyAsyncChecks$1.I$0;
                        Iterator<Map.Entry<String, WorkflowModule.Properties.Statement>> it2 = (Iterator) videoStatementVM$verifyAsyncChecks$1.L$3;
                        String str5 = (String) videoStatementVM$verifyAsyncChecks$1.L$2;
                        WorkflowModule.Properties.Statement statement3 = (WorkflowModule.Properties.Statement) videoStatementVM$verifyAsyncChecks$1.L$1;
                        VideoStatementVM videoStatementVM2 = (VideoStatementVM) videoStatementVM$verifyAsyncChecks$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        z = true;
                        WorkflowModule.Properties.Statement statement4 = statement3;
                        str4 = str5;
                        it = it2;
                        if (((Boolean) obj).booleanValue()) {
                            statement = statement4;
                            videoStatementVM = videoStatementVM2;
                            z3 = z;
                        } else {
                            statement = statement4;
                            z3 = false;
                            videoStatementVM = videoStatementVM2;
                        }
                        if (z3) {
                            videoStatementVM.failedCheck = Check.SPEECH_TO_TEXT_MATCH;
                            if (i3 == 0) {
                                videoStatementVM.currentStatementId = str4;
                                videoStatementVM.currentStatement = statement;
                            }
                            return Boxing.boxBoolean(i3 != 0 ? z : false);
                        }
                        str3 = str4;
                        if (it.hasNext()) {
                            entry = it.next();
                            str4 = entry.getKey();
                            statement2 = entry.getValue();
                            WorkflowModule.Properties.Checks checks3 = entry.getValue().getChecks();
                            if (((checks3 == null || (faceDetection = checks3.getFaceDetection()) == null || faceDetection.isSync()) ? false : true) && Intrinsics.areEqual(videoStatementVM.faceDetectionResults.get(entry.getKey()), Boxing.boxBoolean(false))) {
                                videoStatementVM.failedCheck = Check.FACE_DETECTION;
                                i3 = 0;
                                statement = statement2;
                                z = true;
                            } else {
                                WorkflowModule.Properties.Checks checks4 = entry.getValue().getChecks();
                                if ((checks4 == null || (liveness = checks4.getLiveness()) == null || liveness.isSync()) ? false : true) {
                                    Deferred<Boolean> deferred = videoStatementVM.livenessCallsDeferred.get(entry.getKey());
                                    if (deferred != null) {
                                        videoStatementVM$verifyAsyncChecks$1.L$0 = videoStatementVM;
                                        videoStatementVM$verifyAsyncChecks$1.L$1 = statement2;
                                        videoStatementVM$verifyAsyncChecks$1.L$2 = str4;
                                        videoStatementVM$verifyAsyncChecks$1.L$3 = it;
                                        videoStatementVM$verifyAsyncChecks$1.L$4 = entry;
                                        videoStatementVM$verifyAsyncChecks$1.I$0 = 0;
                                        z = true;
                                        videoStatementVM$verifyAsyncChecks$1.label = 1;
                                        obj = deferred.await(videoStatementVM$verifyAsyncChecks$1);
                                        if (obj == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        i3 = 0;
                                        z4 = ((Boolean) obj).booleanValue() ? z : false;
                                        statement = statement2;
                                        if (z4) {
                                            videoStatementVM.failedCheck = Check.LIVENESS;
                                        } else {
                                            statement2 = statement;
                                            checks2 = entry.getValue().getChecks();
                                            if ((checks2 != null || (faceMatch = checks2.getFaceMatch()) == null || faceMatch.isSync()) ? false : z) {
                                            }
                                        }
                                    } else {
                                        z = true;
                                        i3 = 0;
                                        z4 = false;
                                        statement = statement2;
                                        if (z4) {
                                        }
                                    }
                                } else {
                                    z = true;
                                    i3 = 0;
                                    checks2 = entry.getValue().getChecks();
                                    if ((checks2 != null || (faceMatch = checks2.getFaceMatch()) == null || faceMatch.isSync()) ? false : z) {
                                        Deferred<Boolean> deferred2 = videoStatementVM.faceMatchCallsDeferred.get(entry.getKey());
                                        if (deferred2 != null) {
                                            videoStatementVM$verifyAsyncChecks$1.L$0 = videoStatementVM;
                                            videoStatementVM$verifyAsyncChecks$1.L$1 = statement2;
                                            videoStatementVM$verifyAsyncChecks$1.L$2 = str4;
                                            videoStatementVM$verifyAsyncChecks$1.L$3 = it;
                                            videoStatementVM$verifyAsyncChecks$1.L$4 = entry;
                                            videoStatementVM$verifyAsyncChecks$1.I$0 = i3;
                                            videoStatementVM$verifyAsyncChecks$1.label = 2;
                                            obj = deferred2.await(videoStatementVM$verifyAsyncChecks$1);
                                            if (obj == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            i2 = i3;
                                            if (!((Boolean) obj).booleanValue()) {
                                                z2 = z;
                                                String str6 = str4;
                                                statement = statement2;
                                                if (!z2) {
                                                    videoStatementVM.failedCheck = Check.FACE_MATCH;
                                                    str4 = str6;
                                                    i3 = i2;
                                                } else {
                                                    str4 = str6;
                                                    i3 = i2;
                                                    videoStatementVM2 = videoStatementVM;
                                                    checks = entry.getValue().getChecks();
                                                    if ((checks != null || (speechToTextMatching = checks.getSpeechToTextMatching()) == null || speechToTextMatching.isSync()) ? false : z) {
                                                    }
                                                }
                                            }
                                            z2 = false;
                                            String str62 = str4;
                                            statement = statement2;
                                            if (!z2) {
                                            }
                                        } else {
                                            i2 = i3;
                                            z2 = false;
                                            String str622 = str4;
                                            statement = statement2;
                                            if (!z2) {
                                            }
                                        }
                                    } else {
                                        statement = statement2;
                                        videoStatementVM2 = videoStatementVM;
                                        checks = entry.getValue().getChecks();
                                        if ((checks != null || (speechToTextMatching = checks.getSpeechToTextMatching()) == null || speechToTextMatching.isSync()) ? false : z) {
                                            Deferred<Boolean> deferred3 = videoStatementVM2.speechToTextMatchCallsDeferred.get(entry.getKey());
                                            if (deferred3 != null) {
                                                videoStatementVM$verifyAsyncChecks$1.L$0 = videoStatementVM2;
                                                videoStatementVM$verifyAsyncChecks$1.L$1 = statement;
                                                videoStatementVM$verifyAsyncChecks$1.L$2 = str4;
                                                videoStatementVM$verifyAsyncChecks$1.L$3 = it;
                                                videoStatementVM$verifyAsyncChecks$1.L$4 = null;
                                                videoStatementVM$verifyAsyncChecks$1.I$0 = i3;
                                                videoStatementVM$verifyAsyncChecks$1.label = 3;
                                                Object await = deferred3.await(videoStatementVM$verifyAsyncChecks$1);
                                                if (await == coroutine_suspended) {
                                                    return coroutine_suspended;
                                                }
                                                statement4 = statement;
                                                obj = await;
                                                if (((Boolean) obj).booleanValue()) {
                                                }
                                                if (z3) {
                                                }
                                            } else {
                                                z3 = false;
                                                videoStatementVM = videoStatementVM2;
                                                if (z3) {
                                                }
                                            }
                                        } else {
                                            str3 = str4;
                                            videoStatementVM = videoStatementVM2;
                                            if (it.hasNext()) {
                                                z = true;
                                                str4 = str3;
                                                i3 = 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (i3 == 0) {
                        }
                        return Boxing.boxBoolean(i3 != 0 ? z : false);
                    }
                    i3 = videoStatementVM$verifyAsyncChecks$1.I$0;
                    entry = (Map.Entry) videoStatementVM$verifyAsyncChecks$1.L$4;
                    it = (Iterator) videoStatementVM$verifyAsyncChecks$1.L$3;
                    str4 = (String) videoStatementVM$verifyAsyncChecks$1.L$2;
                    statement2 = (WorkflowModule.Properties.Statement) videoStatementVM$verifyAsyncChecks$1.L$1;
                    videoStatementVM = (VideoStatementVM) videoStatementVM$verifyAsyncChecks$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    z = true;
                    i2 = i3;
                    if (!((Boolean) obj).booleanValue()) {
                    }
                    z2 = false;
                    String str6222 = str4;
                    statement = statement2;
                    if (!z2) {
                    }
                }
            }
        }
        videoStatementVM$verifyAsyncChecks$1 = new VideoStatementVM$verifyAsyncChecks$1(this, continuation);
        Object obj2 = videoStatementVM$verifyAsyncChecks$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = videoStatementVM$verifyAsyncChecks$1.label;
        if (i != 0) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0281, code lost:
    
        if (r7.isSync() == true) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x029d, code lost:
    
        if (r7.isSync() == true) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x02b9, code lost:
    
        if (r7.isSync() == true) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x025c, code lost:
    
        if (r7.isSync() == true) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x025e, code lost:
    
        r7 = true;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x02be A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /* JADX WARN: Type inference failed for: r12v10, types: [T] */
    /* JADX WARN: Type inference failed for: r12v15, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v16 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r14v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2, types: [T] */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r5v24, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v35, types: [T, java.lang.Object, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object verifySyncCheck(Check check, Continuation<? super Boolean> continuation) {
        VideoStatementVM$verifySyncCheck$1 videoStatementVM$verifySyncCheck$1;
        int i;
        ?? canonicalName;
        String str;
        Object m1202constructorimpl;
        String str2;
        Matcher matcher;
        boolean z;
        String str3;
        String str4;
        String className;
        int i2;
        boolean isFaceDetectionEnabled;
        boolean z2;
        boolean booleanValue;
        VideoStatementVM videoStatementVM;
        String className2;
        Check check2 = check;
        if (continuation instanceof VideoStatementVM$verifySyncCheck$1) {
            videoStatementVM$verifySyncCheck$1 = (VideoStatementVM$verifySyncCheck$1) continuation;
            if ((videoStatementVM$verifySyncCheck$1.label & Integer.MIN_VALUE) != 0) {
                videoStatementVM$verifySyncCheck$1.label -= Integer.MIN_VALUE;
                Object obj = videoStatementVM$verifySyncCheck$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = videoStatementVM$verifySyncCheck$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    ?? r14 = "N/A";
                    if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                        Class<?> cls = getClass();
                        canonicalName = cls != null ? cls.getCanonicalName() : 0;
                        if (canonicalName == 0) {
                            canonicalName = "N/A";
                        }
                    }
                    objectRef.element = canonicalName;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                    if (matcher2.find()) {
                        ?? replaceAll = matcher2.replaceAll("");
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
                    String str5 = "verifySyncCheck() called with: check = " + check2;
                    if (str5 == null) {
                        str5 = "null ";
                    }
                    sb.append(str5);
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
                                    String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default != null) {
                                        r14 = substringAfterLast$default;
                                        objectRef2.element = r14;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                        if (matcher.find()) {
                                            ?? replaceAll2 = matcher.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                            objectRef2.element = replaceAll2;
                                        }
                                        if (((String) objectRef2.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                            z = false;
                                            str3 = (String) objectRef2.element;
                                        } else {
                                            z = false;
                                            str3 = ((String) objectRef2.element).substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb2 = new StringBuilder();
                                        str4 = "verifySyncCheck() called with: check = " + check2;
                                        if (str4 == null) {
                                            str4 = "null ";
                                        }
                                        sb2.append(str4);
                                        sb2.append(' ');
                                        sb2.append("");
                                        Log.println(3, str3, sb2.toString());
                                        i2 = WhenMappings.$EnumSwitchMapping$0[check.ordinal()];
                                        if (i2 == 1) {
                                            isFaceDetectionEnabled = isFaceDetectionEnabled();
                                            WorkflowModule.Properties.Statement statement = this.currentStatement;
                                            if (statement != null) {
                                                WorkflowModule.Properties.Checks checks = statement.getChecks();
                                                if (checks != null) {
                                                    WorkflowModule.Properties.Check faceDetection = checks.getFaceDetection();
                                                    if (faceDetection != null) {
                                                    }
                                                }
                                            }
                                            z2 = z;
                                        } else if (i2 == 2) {
                                            isFaceDetectionEnabled = isLivenessCheckEnabled();
                                            WorkflowModule.Properties.Statement statement2 = this.currentStatement;
                                            if (statement2 != null) {
                                                WorkflowModule.Properties.Checks checks2 = statement2.getChecks();
                                                if (checks2 != null) {
                                                    WorkflowModule.Properties.Check liveness = checks2.getLiveness();
                                                    if (liveness != null) {
                                                    }
                                                }
                                            }
                                            z2 = z;
                                        } else if (i2 == 3) {
                                            isFaceDetectionEnabled = isFaceMatchCheckEnabled();
                                            WorkflowModule.Properties.Statement statement3 = this.currentStatement;
                                            if (statement3 != null) {
                                                WorkflowModule.Properties.Checks checks3 = statement3.getChecks();
                                                if (checks3 != null) {
                                                    WorkflowModule.Properties.Check faceMatch = checks3.getFaceMatch();
                                                    if (faceMatch != null) {
                                                    }
                                                }
                                            }
                                            z2 = z;
                                        } else {
                                            if (i2 != 4) {
                                                throw new NoWhenBranchMatchedException();
                                            }
                                            isFaceDetectionEnabled = isSpeechToTextMatchEnabled();
                                            WorkflowModule.Properties.Statement statement4 = this.currentStatement;
                                            if (statement4 != null) {
                                                WorkflowModule.Properties.Checks checks4 = statement4.getChecks();
                                                if (checks4 != null) {
                                                    WorkflowModule.Properties.Check speechToTextMatching = checks4.getSpeechToTextMatching();
                                                    if (speechToTextMatching != null) {
                                                    }
                                                }
                                            }
                                            z2 = z;
                                        }
                                        if (!isFaceDetectionEnabled && z2) {
                                            int i3 = WhenMappings.$EnumSwitchMapping$0[check.ordinal()];
                                            if (i3 == 1) {
                                                Boolean bool = this.faceDetectionResults.get(this.currentStatementId);
                                                if (bool != null) {
                                                    booleanValue = bool.booleanValue();
                                                    videoStatementVM = this;
                                                }
                                            } else if (i3 == 2) {
                                                Deferred<Boolean> deferred = this.livenessCallsDeferred.get(this.currentStatementId);
                                                if (deferred != null) {
                                                    videoStatementVM$verifySyncCheck$1.L$0 = this;
                                                    videoStatementVM$verifySyncCheck$1.L$1 = check2;
                                                    videoStatementVM$verifySyncCheck$1.label = 1;
                                                    obj = deferred.await(videoStatementVM$verifySyncCheck$1);
                                                    if (obj == coroutine_suspended) {
                                                        return coroutine_suspended;
                                                    }
                                                    videoStatementVM = this;
                                                    booleanValue = ((Boolean) obj).booleanValue();
                                                }
                                            } else if (i3 == 3) {
                                                Deferred<Boolean> deferred2 = this.faceMatchCallsDeferred.get(this.currentStatementId);
                                                if (deferred2 != null) {
                                                    videoStatementVM$verifySyncCheck$1.L$0 = this;
                                                    videoStatementVM$verifySyncCheck$1.L$1 = check2;
                                                    videoStatementVM$verifySyncCheck$1.label = 2;
                                                    obj = deferred2.await(videoStatementVM$verifySyncCheck$1);
                                                    if (obj == coroutine_suspended) {
                                                        return coroutine_suspended;
                                                    }
                                                    videoStatementVM = this;
                                                    booleanValue = ((Boolean) obj).booleanValue();
                                                }
                                            } else if (i3 == 4) {
                                                Deferred<Boolean> deferred3 = this.speechToTextMatchCallsDeferred.get(this.currentStatementId);
                                                if (deferred3 != null) {
                                                    videoStatementVM$verifySyncCheck$1.L$0 = this;
                                                    videoStatementVM$verifySyncCheck$1.L$1 = check2;
                                                    videoStatementVM$verifySyncCheck$1.label = 3;
                                                    obj = deferred3.await(videoStatementVM$verifySyncCheck$1);
                                                    if (obj == coroutine_suspended) {
                                                        return coroutine_suspended;
                                                    }
                                                    videoStatementVM = this;
                                                    booleanValue = ((Boolean) obj).booleanValue();
                                                }
                                            } else {
                                                throw new NoWhenBranchMatchedException();
                                            }
                                            videoStatementVM = this;
                                            booleanValue = z;
                                        } else {
                                            return Boxing.boxBoolean(true);
                                        }
                                    }
                                }
                                Class<?> cls2 = getClass();
                                String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : str2;
                                if (canonicalName2 != null) {
                                    r14 = canonicalName2;
                                }
                                objectRef2.element = r14;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                if (matcher.find()) {
                                }
                                if (((String) objectRef2.element).length() > 23) {
                                }
                                z = false;
                                str3 = (String) objectRef2.element;
                                StringBuilder sb22 = new StringBuilder();
                                str4 = "verifySyncCheck() called with: check = " + check2;
                                if (str4 == null) {
                                }
                                sb22.append(str4);
                                sb22.append(' ');
                                sb22.append("");
                                Log.println(3, str3, sb22.toString());
                                i2 = WhenMappings.$EnumSwitchMapping$0[check.ordinal()];
                                if (i2 == 1) {
                                }
                                if (!isFaceDetectionEnabled) {
                                }
                                return Boxing.boxBoolean(true);
                            }
                        }
                    }
                    z = false;
                    i2 = WhenMappings.$EnumSwitchMapping$0[check.ordinal()];
                    if (i2 == 1) {
                    }
                    if (!isFaceDetectionEnabled) {
                    }
                    return Boxing.boxBoolean(true);
                }
                if (i == 1) {
                    check2 = (Check) videoStatementVM$verifySyncCheck$1.L$1;
                    videoStatementVM = (VideoStatementVM) videoStatementVM$verifySyncCheck$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    booleanValue = ((Boolean) obj).booleanValue();
                } else if (i == 2) {
                    check2 = (Check) videoStatementVM$verifySyncCheck$1.L$1;
                    videoStatementVM = (VideoStatementVM) videoStatementVM$verifySyncCheck$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    booleanValue = ((Boolean) obj).booleanValue();
                } else {
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    check2 = (Check) videoStatementVM$verifySyncCheck$1.L$1;
                    videoStatementVM = (VideoStatementVM) videoStatementVM$verifySyncCheck$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    booleanValue = ((Boolean) obj).booleanValue();
                }
                if (!booleanValue) {
                    videoStatementVM.failedCheck = check2;
                    videoStatementVM.endStatement();
                }
                return Boxing.boxBoolean(booleanValue);
            }
        }
        videoStatementVM$verifySyncCheck$1 = new VideoStatementVM$verifySyncCheck$1(this, continuation);
        Object obj2 = videoStatementVM$verifySyncCheck$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = videoStatementVM$verifySyncCheck$1.label;
        if (i != 0) {
        }
        if (!booleanValue) {
        }
        return Boxing.boxBoolean(booleanValue);
    }

    static /* synthetic */ void addValueInLogMap$default(VideoStatementVM videoStatementVM, int i, String str, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            i = videoStatementVM.currentStatementIndex;
        }
        videoStatementVM.addValueInLogMap(i, str, obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x0636, code lost:
    
        if (r12 != 0) goto L248;
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x074e, code lost:
    
        if (r7 != null) goto L294;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x03a4, code lost:
    
        if (r13 != 0) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:314:0x00d7, code lost:
    
        if (r1 != 0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:377:0x0204, code lost:
    
        if (r2 != 0) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x086b, code lost:
    
        if (r7 != null) goto L341;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0a4d  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0a7f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0a4f  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0a80 A[PHI: r0
      0x0a80: PHI (r0v130 java.lang.Object) = (r0v32 java.lang.Object), (r0v1 java.lang.Object) binds: [B:135:0x0a7d, B:12:0x0050] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0a5a  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x05c9  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x048c  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0495  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x035b  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x055c  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0704  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x070d  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x07e7  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0808  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x080a  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0803  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0813  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x0a60  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0566  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0586  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x082d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x004a  */
    /* JADX WARN: Type inference failed for: r0v132, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v27 */
    /* JADX WARN: Type inference failed for: r12v28 */
    /* JADX WARN: Type inference failed for: r12v29 */
    /* JADX WARN: Type inference failed for: r12v31, types: [T] */
    /* JADX WARN: Type inference failed for: r12v35 */
    /* JADX WARN: Type inference failed for: r12v42, types: [T] */
    /* JADX WARN: Type inference failed for: r12v58 */
    /* JADX WARN: Type inference failed for: r12v59 */
    /* JADX WARN: Type inference failed for: r12v60 */
    /* JADX WARN: Type inference failed for: r12v63, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v67 */
    /* JADX WARN: Type inference failed for: r12v68 */
    /* JADX WARN: Type inference failed for: r13v15, types: [T] */
    /* JADX WARN: Type inference failed for: r13v21 */
    /* JADX WARN: Type inference failed for: r13v22 */
    /* JADX WARN: Type inference failed for: r13v23 */
    /* JADX WARN: Type inference failed for: r13v27, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r13v28 */
    /* JADX WARN: Type inference failed for: r1v127, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v136 */
    /* JADX WARN: Type inference failed for: r1v137 */
    /* JADX WARN: Type inference failed for: r1v138 */
    /* JADX WARN: Type inference failed for: r1v141, types: [T] */
    /* JADX WARN: Type inference failed for: r1v151, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v153, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v164 */
    /* JADX WARN: Type inference failed for: r1v165 */
    /* JADX WARN: Type inference failed for: r1v33, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r1v36 */
    /* JADX WARN: Type inference failed for: r1v37 */
    /* JADX WARN: Type inference failed for: r1v40, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v5, types: [T] */
    /* JADX WARN: Type inference failed for: r1v84, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v97, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r23v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v17, types: [T] */
    /* JADX WARN: Type inference failed for: r2v29, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r2v36, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v41, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v61 */
    /* JADX WARN: Type inference failed for: r3v35, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v78, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r7v35, types: [T] */
    /* JADX WARN: Type inference failed for: r7v46 */
    /* JADX WARN: Type inference failed for: r7v47 */
    /* JADX WARN: Type inference failed for: r7v72 */
    /* JADX WARN: Type inference failed for: r7v73 */
    /* JADX WARN: Type inference failed for: r7v9, types: [T] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object sendRequest(String str, RequestBody requestBody, RequestType requestType, Continuation<? super Boolean> continuation) {
        VideoStatementVM$sendRequest$1 videoStatementVM$sendRequest$1;
        Object obj;
        int i;
        VideoStatementVM$sendRequest$1 videoStatementVM$sendRequest$12;
        String str2;
        String str3;
        String str4;
        ?? r1;
        String str5;
        String str6;
        Object m1202constructorimpl;
        String str7;
        String str8;
        VideoStatementVM videoStatementVM;
        ?? r2;
        String str9;
        String str10;
        String className;
        String str11;
        CompletableDeferred completableDeferred;
        String str12;
        String str13;
        String str14;
        String str15;
        String str16;
        String str17;
        int i2;
        Object obj2;
        String str18;
        VideoStatementVM videoStatementVM2;
        CompletableDeferred completableDeferred2;
        Response response;
        int i3;
        String str19;
        RequestType requestType2;
        WorkflowModule.Properties.Statement statement;
        WorkflowModule.Properties.Checks checks;
        WorkflowModule.Properties.Check speechToTextMatching;
        String className2;
        Response response2;
        String str20;
        String str21;
        String str22;
        String str23;
        String str24;
        CompletableDeferred completableDeferred3;
        String str25;
        Throwable m1205exceptionOrNullimpl;
        CompletableDeferred completableDeferred4;
        String str26;
        String str27;
        String str28;
        String str29;
        String str30;
        String str31;
        Object m1202constructorimpl2;
        boolean z;
        ?? canonicalName;
        Class<?> cls;
        String str32;
        String str33;
        String className3;
        String substringAfterLast$default;
        Class<?> cls2;
        String className4;
        CompletableDeferred completableDeferred5;
        String str34;
        ?? r12;
        String str35;
        Object m1202constructorimpl3;
        boolean z2;
        String str36;
        String str37;
        Class<?> cls3;
        String className5;
        Object invoke;
        Class<?> cls4;
        String className6;
        int i4;
        String str38;
        Object obj3;
        ?? r13;
        String str39;
        Object m1202constructorimpl4;
        String str40;
        ?? canonicalName2;
        Class<?> cls5;
        String str41;
        int i5;
        String className7;
        ResponseBody body;
        String str42;
        int i6;
        Class<?> cls6;
        String className8;
        if (continuation instanceof VideoStatementVM$sendRequest$1) {
            videoStatementVM$sendRequest$1 = (VideoStatementVM$sendRequest$1) continuation;
            if ((videoStatementVM$sendRequest$1.label & Integer.MIN_VALUE) != 0) {
                videoStatementVM$sendRequest$1.label -= Integer.MIN_VALUE;
                VideoStatementVM$sendRequest$1 videoStatementVM$sendRequest$13 = videoStatementVM$sendRequest$1;
                obj = videoStatementVM$sendRequest$13.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = videoStatementVM$sendRequest$13.label;
                Object obj4 = coroutine_suspended;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    videoStatementVM$sendRequest$12 = videoStatementVM$sendRequest$13;
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null) {
                        str2 = "Throwable().stackTrace";
                        str3 = "packageName";
                        str4 = "null cannot be cast to non-null type android.app.Application";
                    } else {
                        str2 = "Throwable().stackTrace";
                        str3 = "packageName";
                        str4 = "null cannot be cast to non-null type android.app.Application";
                        r1 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls7 = getClass();
                    r1 = cls7 != null ? cls7.getCanonicalName() : 0;
                    if (r1 == 0) {
                        r1 = "N/A";
                    }
                    objectRef.element = r1;
                    Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                    if (matcher.find()) {
                        ?? replaceAll = matcher.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                        objectRef.element = replaceAll;
                    }
                    if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str5 = (String) objectRef.element;
                    } else {
                        str5 = ((String) objectRef.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(str5);
                    sb.append(" - ");
                    String str43 = "sendRequest() called with: url = " + str + ", requestBody = " + requestBody + ", requestType = " + requestType;
                    if (str43 == null) {
                        str43 = "null ";
                    }
                    sb.append(str43);
                    sb.append(' ');
                    sb.append("");
                    companion.log(level, sb.toString());
                    if (CoreExtsKt.isRelease()) {
                        videoStatementVM = this;
                        str7 = str2;
                        str8 = str3;
                        str6 = str4;
                    } else {
                        try {
                            Result.Companion companion2 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            str6 = str4;
                            try {
                                Intrinsics.checkNotNull(invoke2, str6);
                                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                            } catch (Throwable th) {
                                th = th;
                                Result.Companion companion3 = Result.INSTANCE;
                                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                                }
                                String str44 = (String) m1202constructorimpl;
                                if (CoreExtsKt.isDebug()) {
                                }
                                videoStatementVM = this;
                                int i7 = videoStatementVM.currentStatementIndex;
                                String str45 = videoStatementVM.currentAnswerText;
                                CompletableDeferred CompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
                                if (requestType != RequestType.SPEECH_TO_TEXT_MATCH) {
                                }
                                str11 = str;
                                if (str11 != null || str11.length() == 0) {
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            str6 = str4;
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                            m1202constructorimpl = "";
                        }
                        String str442 = (String) m1202constructorimpl;
                        if (CoreExtsKt.isDebug()) {
                            str7 = str2;
                            str8 = str3;
                        } else {
                            str8 = str3;
                            Intrinsics.checkNotNullExpressionValue(str442, str8);
                            if (StringsKt.contains$default((CharSequence) str442, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                str7 = str2;
                                Intrinsics.checkNotNullExpressionValue(stackTrace2, str7);
                                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                if (stackTraceElement2 != null && (className = stackTraceElement2.getClassName()) != null) {
                                    r2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                }
                                Class<?> cls8 = getClass();
                                r2 = cls8 != null ? cls8.getCanonicalName() : 0;
                                if (r2 == 0) {
                                    r2 = "N/A";
                                }
                                objectRef2.element = r2;
                                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                if (matcher2.find()) {
                                    ?? replaceAll2 = matcher2.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                    objectRef2.element = replaceAll2;
                                }
                                if (((String) objectRef2.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str9 = ((String) objectRef2.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
                                    StringBuilder sb2 = new StringBuilder();
                                    str10 = "sendRequest() called with: url = " + str + ", requestBody = " + requestBody + ", requestType = " + requestType;
                                    if (str10 == null) {
                                        str10 = "null ";
                                    }
                                    sb2.append(str10);
                                    sb2.append(' ');
                                    sb2.append("");
                                    Log.println(3, str9, sb2.toString());
                                    videoStatementVM = this;
                                }
                                str9 = (String) objectRef2.element;
                                StringBuilder sb22 = new StringBuilder();
                                str10 = "sendRequest() called with: url = " + str + ", requestBody = " + requestBody + ", requestType = " + requestType;
                                if (str10 == null) {
                                }
                                sb22.append(str10);
                                sb22.append(' ');
                                sb22.append("");
                                Log.println(3, str9, sb22.toString());
                                videoStatementVM = this;
                            } else {
                                str7 = str2;
                            }
                        }
                        videoStatementVM = this;
                    }
                    int i72 = videoStatementVM.currentStatementIndex;
                    String str452 = videoStatementVM.currentAnswerText;
                    CompletableDeferred CompletableDeferred$default2 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
                    int i8 = ((requestType != RequestType.SPEECH_TO_TEXT_MATCH) || (statement = videoStatementVM.currentStatement) == null || (checks = statement.getChecks()) == null || (speechToTextMatching = checks.getSpeechToTextMatching()) == null) ? 0 : speechToTextMatching.getAllowIfCheckFailed() ? 1 : 0;
                    str11 = str;
                    if (str11 != null || str11.length() == 0) {
                        NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                        Map<String, String> headers = videoStatementVM.getHeaders(requestType);
                        videoStatementVM$sendRequest$12.L$0 = videoStatementVM;
                        videoStatementVM$sendRequest$12.L$1 = requestType;
                        videoStatementVM$sendRequest$12.L$2 = str452;
                        videoStatementVM$sendRequest$12.L$3 = CompletableDeferred$default2;
                        videoStatementVM$sendRequest$12.I$0 = i72;
                        videoStatementVM$sendRequest$12.I$1 = i8;
                        videoStatementVM$sendRequest$12.label = 1;
                        str12 = "this as java.lang.String…ing(startIndex, endIndex)";
                        str13 = " - ";
                        str14 = str7;
                        str15 = "getInitialApplication";
                        videoStatementVM$sendRequest$12 = videoStatementVM$sendRequest$12;
                        str16 = str8;
                        str17 = "";
                        i2 = i8;
                        obj2 = obj4;
                        str18 = "replaceAll(\"\")";
                        ?? m396customApiCalleH_QyT8$hyperkyc_release$default = NetworkRepo.m396customApiCalleH_QyT8$hyperkyc_release$default(networkRepo, str, "post", headers, requestBody, 0L, null, 0, videoStatementVM$sendRequest$12, 112, null);
                        if (m396customApiCalleH_QyT8$hyperkyc_release$default == obj2) {
                            return obj2;
                        }
                        videoStatementVM2 = this;
                        completableDeferred2 = CompletableDeferred$default2;
                        response = m396customApiCalleH_QyT8$hyperkyc_release$default;
                        i3 = i72;
                        str19 = str452;
                        requestType2 = requestType;
                    } else {
                        CompletableDeferred$default2.complete(Boxing.boxBoolean(i8));
                        completableDeferred = CompletableDeferred$default2;
                        VideoStatementVM$sendRequest$1 videoStatementVM$sendRequest$14 = videoStatementVM$sendRequest$12;
                        videoStatementVM$sendRequest$14.L$0 = null;
                        videoStatementVM$sendRequest$14.L$1 = null;
                        videoStatementVM$sendRequest$14.L$2 = null;
                        videoStatementVM$sendRequest$14.L$3 = null;
                        videoStatementVM$sendRequest$14.label = 2;
                        obj = completableDeferred.await(videoStatementVM$sendRequest$14);
                        Object obj5 = obj4;
                        return obj == obj5 ? obj5 : obj;
                    }
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            ResultKt.throwOnFailure(obj);
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    int i9 = videoStatementVM$sendRequest$13.I$1;
                    int i10 = videoStatementVM$sendRequest$13.I$0;
                    completableDeferred2 = (CompletableDeferred) videoStatementVM$sendRequest$13.L$3;
                    str19 = (String) videoStatementVM$sendRequest$13.L$2;
                    RequestType requestType3 = (RequestType) videoStatementVM$sendRequest$13.L$1;
                    VideoStatementVM videoStatementVM3 = (VideoStatementVM) videoStatementVM$sendRequest$13.L$0;
                    ResultKt.throwOnFailure(obj);
                    i2 = i9;
                    str17 = "";
                    str12 = "this as java.lang.String…ing(startIndex, endIndex)";
                    str13 = " - ";
                    str14 = "Throwable().stackTrace";
                    str15 = "getInitialApplication";
                    str16 = "packageName";
                    str6 = "null cannot be cast to non-null type android.app.Application";
                    videoStatementVM$sendRequest$12 = videoStatementVM$sendRequest$13;
                    videoStatementVM2 = videoStatementVM3;
                    i3 = i10;
                    str18 = "replaceAll(\"\")";
                    requestType2 = requestType3;
                    obj2 = obj4;
                    response = ((Result) obj).getValue();
                }
                if (Result.m1209isSuccessimpl(response)) {
                    response2 = response;
                    obj4 = obj2;
                    str20 = str12;
                    str21 = str13;
                    str22 = str14;
                    str23 = str15;
                    str24 = str17;
                    completableDeferred3 = completableDeferred2;
                    str25 = " API failed: ";
                } else {
                    Response response3 = response;
                    if (response3.isSuccessful()) {
                        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb3 = new StringBuilder();
                        Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        obj4 = obj2;
                        String str46 = str14;
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, str46);
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className8 = stackTraceElement3.getClassName()) == null) {
                            response2 = response;
                            i4 = i3;
                            str38 = " API failed: ";
                            obj3 = null;
                        } else {
                            response2 = response;
                            i4 = i3;
                            str38 = " API failed: ";
                            obj3 = null;
                            r13 = StringsKt.substringAfterLast$default(className8, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        r13 = (videoStatementVM2 == null || (cls6 = videoStatementVM2.getClass()) == null) ? obj3 : cls6.getCanonicalName();
                        if (r13 == 0) {
                            r13 = "N/A";
                        }
                        objectRef3.element = r13;
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                        if (matcher3.find()) {
                            str24 = str17;
                            ?? replaceAll3 = matcher3.replaceAll(str24);
                            Intrinsics.checkNotNullExpressionValue(replaceAll3, str18);
                            objectRef3.element = replaceAll3;
                        } else {
                            str24 = str17;
                        }
                        if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str20 = str12;
                            str39 = (String) objectRef3.element;
                        } else {
                            String substring = ((String) objectRef3.element).substring(0, 23);
                            str20 = str12;
                            Intrinsics.checkNotNullExpressionValue(substring, str20);
                            str39 = substring;
                        }
                        sb3.append(str39);
                        str21 = str13;
                        sb3.append(str21);
                        String str47 = requestType2.name() + " API call successful";
                        if (str47 == null) {
                            str47 = "null ";
                        }
                        sb3.append(str47);
                        sb3.append(' ');
                        sb3.append(str24);
                        companion4.log(level2, sb3.toString());
                        if (CoreExtsKt.isRelease()) {
                            str40 = str6;
                            str23 = str15;
                        } else {
                            try {
                                Result.Companion companion5 = Result.INSTANCE;
                                str23 = str15;
                                try {
                                    Object invoke3 = Class.forName("android.app.AppGlobals").getMethod(str23, new Class[0]).invoke(null, new Object[0]);
                                    Intrinsics.checkNotNull(invoke3, str6);
                                    m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                                } catch (Throwable th3) {
                                    th = th3;
                                    Result.Companion companion6 = Result.INSTANCE;
                                    m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                    if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                    }
                                    String str48 = (String) m1202constructorimpl4;
                                    if (CoreExtsKt.isDebug()) {
                                    }
                                    i5 = 3;
                                    body = response3.body();
                                    if (body != null) {
                                    }
                                    str42 = str24;
                                    Map<String, ? extends Object> map = JSONExtsKt.toMap(new JSONObject(str42));
                                    Intrinsics.checkNotNull(map, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any>");
                                    i6 = WhenMappings.$EnumSwitchMapping$1[requestType2.ordinal()];
                                    if (i6 != 1) {
                                    }
                                    completableDeferred2.complete(Boxing.boxBoolean(false));
                                    completableDeferred3 = completableDeferred2;
                                    str22 = str46;
                                    str6 = str40;
                                    str25 = str38;
                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(response2);
                                    if (m1205exceptionOrNullimpl == null) {
                                    }
                                    completableDeferred = completableDeferred4;
                                    VideoStatementVM$sendRequest$1 videoStatementVM$sendRequest$142 = videoStatementVM$sendRequest$12;
                                    videoStatementVM$sendRequest$142.L$0 = null;
                                    videoStatementVM$sendRequest$142.L$1 = null;
                                    videoStatementVM$sendRequest$142.L$2 = null;
                                    videoStatementVM$sendRequest$142.L$3 = null;
                                    videoStatementVM$sendRequest$142.label = 2;
                                    obj = completableDeferred.await(videoStatementVM$sendRequest$142);
                                    Object obj52 = obj4;
                                    if (obj == obj52) {
                                    }
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                str23 = str15;
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                m1202constructorimpl4 = str24;
                            }
                            String str482 = (String) m1202constructorimpl4;
                            if (CoreExtsKt.isDebug()) {
                                str40 = str6;
                            } else {
                                Intrinsics.checkNotNullExpressionValue(str482, str16);
                                str40 = str6;
                                if (StringsKt.contains$default((CharSequence) str482, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                    Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace4, str46);
                                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                    if (stackTraceElement4 == null || (className7 = stackTraceElement4.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className7, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                                        canonicalName2 = (videoStatementVM2 == null || (cls5 = videoStatementVM2.getClass()) == null) ? 0 : cls5.getCanonicalName();
                                        if (canonicalName2 == 0) {
                                            canonicalName2 = "N/A";
                                        }
                                    }
                                    objectRef4.element = canonicalName2;
                                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                    if (matcher4.find()) {
                                        ?? replaceAll4 = matcher4.replaceAll(str24);
                                        Intrinsics.checkNotNullExpressionValue(replaceAll4, str18);
                                        objectRef4.element = replaceAll4;
                                    }
                                    if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                        str41 = (String) objectRef4.element;
                                    } else {
                                        str41 = ((String) objectRef4.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str41, str20);
                                    }
                                    StringBuilder sb4 = new StringBuilder();
                                    String str49 = requestType2.name() + " API call successful";
                                    if (str49 == null) {
                                        str49 = "null ";
                                    }
                                    sb4.append(str49);
                                    sb4.append(' ');
                                    sb4.append(str24);
                                    i5 = 3;
                                    Log.println(3, str41, sb4.toString());
                                    body = response3.body();
                                    if (body != null || (str42 = body.string()) == null) {
                                        str42 = str24;
                                    }
                                    Map<String, ? extends Object> map2 = JSONExtsKt.toMap(new JSONObject(str42));
                                    Intrinsics.checkNotNull(map2, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any>");
                                    i6 = WhenMappings.$EnumSwitchMapping$1[requestType2.ordinal()];
                                    if (i6 != 1) {
                                        if (i6 != 2) {
                                            if (i6 != i5) {
                                                if (i6 == 4) {
                                                    videoStatementVM2.handleLogVideoStatementResult(response3, videoStatementVM2.isMaximumRestartsExceeded ? "no" : "yes", str42);
                                                    completableDeferred2.complete(Boxing.boxBoolean(true));
                                                }
                                            } else if (videoStatementVM2.addSpeechToTextMatchDataToLog(map2, i4, str19) || i2 != 0) {
                                                completableDeferred2.complete(Boxing.boxBoolean(true));
                                            }
                                        } else if (videoStatementVM2.addFaceMatchDataToLog(map2, i4)) {
                                            completableDeferred2.complete(Boxing.boxBoolean(true));
                                        }
                                    } else if (videoStatementVM2.addLivenessDataToLog(map2, i4)) {
                                        completableDeferred2.complete(Boxing.boxBoolean(true));
                                    }
                                    completableDeferred2.complete(Boxing.boxBoolean(false));
                                    completableDeferred3 = completableDeferred2;
                                    str22 = str46;
                                    str6 = str40;
                                    str25 = str38;
                                }
                            }
                        }
                        i5 = 3;
                        body = response3.body();
                        if (body != null) {
                        }
                        str42 = str24;
                        Map<String, ? extends Object> map22 = JSONExtsKt.toMap(new JSONObject(str42));
                        Intrinsics.checkNotNull(map22, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any>");
                        i6 = WhenMappings.$EnumSwitchMapping$1[requestType2.ordinal()];
                        if (i6 != 1) {
                        }
                        completableDeferred2.complete(Boxing.boxBoolean(false));
                        completableDeferred3 = completableDeferred2;
                        str22 = str46;
                        str6 = str40;
                        str25 = str38;
                    } else {
                        String str50 = str6;
                        response2 = response;
                        obj4 = obj2;
                        str20 = str12;
                        str21 = str13;
                        String str51 = str14;
                        str23 = str15;
                        str24 = str17;
                        HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                        HyperLogger companion7 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb5 = new StringBuilder();
                        Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace5, str51);
                        StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                        if (stackTraceElement5 == null || (className6 = stackTraceElement5.getClassName()) == null) {
                            completableDeferred5 = completableDeferred2;
                            str34 = str51;
                        } else {
                            completableDeferred5 = completableDeferred2;
                            str34 = str51;
                            r12 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        r12 = (videoStatementVM2 == null || (cls4 = videoStatementVM2.getClass()) == null) ? 0 : cls4.getCanonicalName();
                        if (r12 == 0) {
                            r12 = "N/A";
                        }
                        objectRef5.element = r12;
                        Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef5.element);
                        if (matcher5.find()) {
                            ?? replaceAll5 = matcher5.replaceAll(str24);
                            Intrinsics.checkNotNullExpressionValue(replaceAll5, str18);
                            objectRef5.element = replaceAll5;
                        }
                        if (((String) objectRef5.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str35 = (String) objectRef5.element;
                        } else {
                            str35 = ((String) objectRef5.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str35, str20);
                        }
                        sb5.append(str35);
                        sb5.append(str21);
                        StringBuilder sb6 = new StringBuilder();
                        sb6.append(requestType2.name());
                        str25 = " API failed: ";
                        sb6.append(str25);
                        sb6.append(response3.message());
                        String sb7 = sb6.toString();
                        if (sb7 == null) {
                            sb7 = "null ";
                        }
                        sb5.append(sb7);
                        sb5.append(' ');
                        sb5.append(str24);
                        companion7.log(level3, sb5.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion8 = Result.INSTANCE;
                            invoke = Class.forName("android.app.AppGlobals").getMethod(str23, new Class[0]).invoke(null, new Object[0]);
                            str6 = str50;
                        } catch (Throwable th5) {
                            th = th5;
                            str6 = str50;
                        }
                        try {
                            Intrinsics.checkNotNull(invoke, str6);
                            m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                        } catch (Throwable th6) {
                            th = th6;
                            Result.Companion companion9 = Result.INSTANCE;
                            m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                            }
                            String str52 = (String) m1202constructorimpl3;
                            if (CoreExtsKt.isDebug()) {
                            }
                            str22 = str34;
                            if (requestType2 == RequestType.LOG_VIDEO_STATEMENT) {
                            }
                            completableDeferred3.complete(Boxing.boxBoolean(i2 != 0 ? z2 : false));
                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(response2);
                            if (m1205exceptionOrNullimpl == null) {
                            }
                            completableDeferred = completableDeferred4;
                            VideoStatementVM$sendRequest$1 videoStatementVM$sendRequest$1422 = videoStatementVM$sendRequest$12;
                            videoStatementVM$sendRequest$1422.L$0 = null;
                            videoStatementVM$sendRequest$1422.L$1 = null;
                            videoStatementVM$sendRequest$1422.L$2 = null;
                            videoStatementVM$sendRequest$1422.L$3 = null;
                            videoStatementVM$sendRequest$1422.label = 2;
                            obj = completableDeferred.await(videoStatementVM$sendRequest$1422);
                            Object obj522 = obj4;
                            if (obj == obj522) {
                            }
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                            m1202constructorimpl3 = str24;
                        }
                        String str522 = (String) m1202constructorimpl3;
                        if (CoreExtsKt.isDebug()) {
                            String str53 = str16;
                            Intrinsics.checkNotNullExpressionValue(str522, str53);
                            if (StringsKt.contains$default((CharSequence) str522, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                str22 = str34;
                                Intrinsics.checkNotNullExpressionValue(stackTrace6, str22);
                                StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                if (stackTraceElement6 == null || (className5 = stackTraceElement6.getClassName()) == null) {
                                    str16 = str53;
                                } else {
                                    str16 = str53;
                                    String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    str36 = substringAfterLast$default2;
                                }
                                String canonicalName3 = (videoStatementVM2 == null || (cls3 = videoStatementVM2.getClass()) == null) ? null : cls3.getCanonicalName();
                                str36 = canonicalName3 == null ? "N/A" : canonicalName3;
                                objectRef6.element = str36;
                                Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef6.element);
                                if (matcher6.find()) {
                                    ?? replaceAll6 = matcher6.replaceAll(str24);
                                    Intrinsics.checkNotNullExpressionValue(replaceAll6, str18);
                                    objectRef6.element = replaceAll6;
                                }
                                if (((String) objectRef6.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                    str37 = (String) objectRef6.element;
                                } else {
                                    str37 = ((String) objectRef6.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str37, str20);
                                }
                                StringBuilder sb8 = new StringBuilder();
                                String str54 = requestType2.name() + str25 + response3.message();
                                if (str54 == null) {
                                    str54 = "null ";
                                }
                                sb8.append(str54);
                                sb8.append(' ');
                                sb8.append(str24);
                                Log.println(6, str37, sb8.toString());
                                if (requestType2 == RequestType.LOG_VIDEO_STATEMENT) {
                                    ResponseBody body2 = response3.body();
                                    videoStatementVM2.handleLogVideoStatementResult(response3, "no", body2 != null ? body2.string() : null);
                                    z2 = true;
                                    completableDeferred3 = completableDeferred5;
                                    completableDeferred3.complete(Boxing.boxBoolean(true));
                                } else {
                                    completableDeferred3 = completableDeferred5;
                                    z2 = true;
                                }
                                completableDeferred3.complete(Boxing.boxBoolean(i2 != 0 ? z2 : false));
                            } else {
                                str16 = str53;
                            }
                        }
                        str22 = str34;
                        if (requestType2 == RequestType.LOG_VIDEO_STATEMENT) {
                        }
                        completableDeferred3.complete(Boxing.boxBoolean(i2 != 0 ? z2 : false));
                    }
                }
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(response2);
                if (m1205exceptionOrNullimpl == null) {
                    HyperLogger.Level level4 = HyperLogger.Level.ERROR;
                    HyperLogger companion10 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb9 = new StringBuilder();
                    Ref.ObjectRef objectRef7 = new Ref.ObjectRef();
                    CompletableDeferred completableDeferred6 = completableDeferred3;
                    StackTraceElement[] stackTrace7 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace7, str22);
                    StackTraceElement stackTraceElement7 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace7);
                    if (stackTraceElement7 == null || (className4 = stackTraceElement7.getClassName()) == null) {
                        str26 = str6;
                        str27 = str22;
                        str28 = str23;
                    } else {
                        str26 = str6;
                        str27 = str22;
                        str28 = str23;
                        String substringAfterLast$default3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        str29 = substringAfterLast$default3;
                    }
                    String canonicalName4 = (videoStatementVM2 == null || (cls2 = videoStatementVM2.getClass()) == null) ? null : cls2.getCanonicalName();
                    str29 = canonicalName4 == null ? "N/A" : canonicalName4;
                    objectRef7.element = str29;
                    Matcher matcher7 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef7.element);
                    if (matcher7.find()) {
                        ?? replaceAll7 = matcher7.replaceAll(str24);
                        Intrinsics.checkNotNullExpressionValue(replaceAll7, str18);
                        objectRef7.element = replaceAll7;
                    }
                    if (((String) objectRef7.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str30 = (String) objectRef7.element;
                    } else {
                        str30 = ((String) objectRef7.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str30, str20);
                    }
                    sb9.append(str30);
                    sb9.append(str21);
                    String str55 = requestType2.name() + str25 + m1205exceptionOrNullimpl.getMessage();
                    if (str55 == null) {
                        str55 = "null ";
                    }
                    sb9.append(str55);
                    sb9.append(' ');
                    String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                    if (localizedMessage != null) {
                        str31 = '\n' + localizedMessage;
                    } else {
                        str31 = str24;
                    }
                    sb9.append(str31);
                    companion10.log(level4, sb9.toString());
                    CoreExtsKt.isRelease();
                    try {
                        Result.Companion companion11 = Result.INSTANCE;
                        Object invoke4 = Class.forName("android.app.AppGlobals").getMethod(str28, new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke4, str26);
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
                    } catch (Throwable th7) {
                        Result.Companion companion12 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th7));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = str24;
                    }
                    String str56 = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(str56, str16);
                        if (StringsKt.contains$default((CharSequence) str56, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            Ref.ObjectRef objectRef8 = new Ref.ObjectRef();
                            StackTraceElement[] stackTrace8 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace8, str27);
                            StackTraceElement stackTraceElement8 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace8);
                            if (stackTraceElement8 == null || (className3 = stackTraceElement8.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                canonicalName = (videoStatementVM2 == null || (cls = videoStatementVM2.getClass()) == null) ? 0 : cls.getCanonicalName();
                                if (canonicalName == 0) {
                                    canonicalName = "N/A";
                                }
                            } else {
                                canonicalName = substringAfterLast$default;
                            }
                            objectRef8.element = canonicalName;
                            Matcher matcher8 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef8.element);
                            if (matcher8.find()) {
                                ?? replaceAll8 = matcher8.replaceAll(str24);
                                Intrinsics.checkNotNullExpressionValue(replaceAll8, str18);
                                objectRef8.element = replaceAll8;
                            }
                            if (((String) objectRef8.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                z = false;
                                str32 = (String) objectRef8.element;
                            } else {
                                z = false;
                                str32 = ((String) objectRef8.element).substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str32, str20);
                            }
                            StringBuilder sb10 = new StringBuilder();
                            String str57 = requestType2.name() + str25 + m1205exceptionOrNullimpl.getMessage();
                            if (str57 == null) {
                                str57 = "null ";
                            }
                            sb10.append(str57);
                            sb10.append(' ');
                            String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                            if (localizedMessage2 != null) {
                                str33 = '\n' + localizedMessage2;
                            } else {
                                str33 = str24;
                            }
                            sb10.append(str33);
                            Log.println(6, str32, sb10.toString());
                            completableDeferred4 = completableDeferred6;
                            completableDeferred4.complete(Boxing.boxBoolean(i2 == 0 ? true : z));
                        }
                    }
                    z = false;
                    completableDeferred4 = completableDeferred6;
                    completableDeferred4.complete(Boxing.boxBoolean(i2 == 0 ? true : z));
                } else {
                    completableDeferred4 = completableDeferred3;
                }
                completableDeferred = completableDeferred4;
                VideoStatementVM$sendRequest$1 videoStatementVM$sendRequest$14222 = videoStatementVM$sendRequest$12;
                videoStatementVM$sendRequest$14222.L$0 = null;
                videoStatementVM$sendRequest$14222.L$1 = null;
                videoStatementVM$sendRequest$14222.L$2 = null;
                videoStatementVM$sendRequest$14222.L$3 = null;
                videoStatementVM$sendRequest$14222.label = 2;
                obj = completableDeferred.await(videoStatementVM$sendRequest$14222);
                Object obj5222 = obj4;
                if (obj == obj5222) {
                }
            }
        }
        videoStatementVM$sendRequest$1 = new VideoStatementVM$sendRequest$1(this, continuation);
        VideoStatementVM$sendRequest$1 videoStatementVM$sendRequest$132 = videoStatementVM$sendRequest$1;
        obj = videoStatementVM$sendRequest$132.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = videoStatementVM$sendRequest$132.label;
        Object obj42 = coroutine_suspended2;
        if (i != 0) {
        }
        if (Result.m1209isSuccessimpl(response)) {
        }
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(response2);
        if (m1205exceptionOrNullimpl == null) {
        }
        completableDeferred = completableDeferred4;
        VideoStatementVM$sendRequest$1 videoStatementVM$sendRequest$142222 = videoStatementVM$sendRequest$12;
        videoStatementVM$sendRequest$142222.L$0 = null;
        videoStatementVM$sendRequest$142222.L$1 = null;
        videoStatementVM$sendRequest$142222.L$2 = null;
        videoStatementVM$sendRequest$142222.L$3 = null;
        videoStatementVM$sendRequest$142222.label = 2;
        obj = completableDeferred.await(videoStatementVM$sendRequest$142222);
        Object obj52222 = obj42;
        if (obj == obj52222) {
        }
    }

    private final String asString(boolean z) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Boolean valueOf = Boolean.valueOf(z);
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = valueOf.getClass();
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
        sb.append("asString() called");
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
                        Class<?> cls2 = valueOf.getClass();
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
                    Log.println(3, str, "asString() called ");
                }
            }
        }
        return z ? "yes" : "no";
    }

    static /* synthetic */ String injectFromVariables$default(VideoStatementVM videoStatementVM, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return videoStatementVM.injectFromVariables(str, z);
    }

    public final String getCurrentStatementId() {
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
        sb.append("getCurrentStatementId() called");
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
                    Log.println(3, str, "getCurrentStatementId() called ");
                }
            }
        }
        return this.currentStatementId;
    }

    public final WorkflowModule.Properties.Statement getCurrentStatement() {
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
        sb.append("getCurrentStatement() called");
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
                    Log.println(3, str, "getCurrentStatement() called ");
                }
            }
        }
        return this.currentStatement;
    }

    public final boolean checkIsEndOfStatements() {
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
        sb.append("checkIsEndOfStatements() called");
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
                    Log.println(3, str, "checkIsEndOfStatements() called ");
                }
            }
        }
        return Intrinsics.areEqual(this.currentStatementId, END_OF_STATEMENTS);
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void moveToNextStatement() {
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
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("moveToNextStatement() called");
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
                    Log.println(3, str2, "moveToNextStatement() called ");
                }
            }
        }
        WorkflowModule.Properties.Statement statement = this.currentStatement;
        if (statement == null || (str = statement.getNext()) == null) {
            str = END_OF_STATEMENTS;
        }
        this.currentStatementId = str;
        Map<String, WorkflowModule.Properties.Statement> statements = this.videoStatementConfig.getStatements();
        this.currentStatement = statements != null ? statements.get(this.currentStatementId) : null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean checkIfStatementEnabled() {
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
        sb.append("checkIfStatementEnabled() called");
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
                    Log.println(3, str, "checkIfStatementEnabled() called ");
                }
            }
        }
        WorkflowModule.Properties.Statement statement = this.currentStatement;
        return asBoolean(statement != null ? statement.getEnable() : null, true);
    }

    public final long getStatementDuration() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        Integer duration;
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
        sb.append("getStatementDuration() called");
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
                    Log.println(3, str, "getStatementDuration() called ");
                }
            }
        }
        WorkflowModule.Properties.Statement statement = this.currentStatement;
        if (statement == null || (duration = statement.getDuration()) == null) {
            return 5000L;
        }
        return duration.intValue();
    }

    public final boolean getShouldDisplayTimer() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        Boolean displayTimer;
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
        sb.append("getShouldDisplayTimer() called");
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
                    Log.println(3, str, "getShouldDisplayTimer() called ");
                }
            }
        }
        WorkflowModule.Properties.Statement statement = this.currentStatement;
        if (statement == null || (displayTimer = statement.getDisplayTimer()) == null) {
            return true;
        }
        return displayTimer.booleanValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean handleRetry() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String str;
        WorkflowModule.Properties.Statement statement;
        WorkflowModule.Properties.Checks checks;
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
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("handleRetry() called");
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
                    Log.println(3, str2, "handleRetry() called ");
                }
            }
        }
        if (this.noOfRestarts == this.videoStatementConfig.getAllowedRestarts()) {
            this.isMaximumRestartsExceeded = true;
            return false;
        }
        this.noOfRestarts++;
        Map<String, WorkflowModule.Properties.Statement> statements = this.videoStatementConfig.getStatements();
        if (statements == null || (statement = statements.get(this.currentStatementId)) == null || (checks = statement.getChecks()) == null || (str = checks.getRestartFrom()) == null) {
            str = this.currentStatementId;
        }
        this.currentStatementId = str;
        Map<String, WorkflowModule.Properties.Statement> statements2 = this.videoStatementConfig.getStatements();
        this.currentStatement = statements2 != null ? statements2.get(this.currentStatementId) : null;
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x013e, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void observeUIState(HVFacePreview facePreview) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(facePreview, "facePreview");
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
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new VideoStatementVM$observeUIState$2(facePreview, this, null), 3, null);
    }

    private final void endStatement() {
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
        sb.append("endStatement() called");
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
                    Log.println(3, str, "endStatement() called ");
                }
            }
        }
        if (!checkIsEndOfStatements()) {
            addValueInLogMap$default(this, 0, "endTimestamp", formatSecondsToHHMMSS(), 1, null);
        }
        this.currentStatementIndex++;
    }

    public final void setCurrentAnswerText(String text) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(text, "text");
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
        String str2 = "setCurrentAnswerText() called with: text = " + text;
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
                    String str3 = "setCurrentAnswerText() called with: text = " + text;
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
        this.currentAnswerText = text;
    }

    public final void setStartTimestamp(long timeStamp) {
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
        String str2 = "setStartTimestamp() called with: timeStamp = " + timeStamp;
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
                    String str3 = "setStartTimestamp() called with: timeStamp = " + timeStamp;
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
        this.startTimestamp = timeStamp;
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x0122, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Check getFailedCheck() {
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
        sb.append("getFailedCheck() called");
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
                    Log.println(3, str, "getFailedCheck() called ");
                }
            }
        }
        Check check = this.failedCheck;
        if (check != null) {
            return check;
        }
        Intrinsics.throwUninitializedPropertyAccessException("failedCheck");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isFaceDetectionEnabled() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        WorkflowModule.Properties.Checks checks;
        WorkflowModule.Properties.Check faceDetection;
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
        sb.append("isFaceDetectionEnabled() called");
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
                    Log.println(3, str, "isFaceDetectionEnabled() called ");
                }
            }
        }
        WorkflowModule.Properties.Statement statement = this.currentStatement;
        if (statement == null || (checks = statement.getChecks()) == null || (faceDetection = checks.getFaceDetection()) == null) {
            return false;
        }
        return faceDetection.getEnable();
    }

    private final boolean isLivenessCheckEnabled() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        WorkflowModule.Properties.Checks checks;
        WorkflowModule.Properties.Check liveness;
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
        sb.append("isLivenessCheckEnabled() called");
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
                    Log.println(3, str, "isLivenessCheckEnabled() called ");
                }
            }
        }
        WorkflowModule.Properties.Statement statement = this.currentStatement;
        if (statement == null || (checks = statement.getChecks()) == null || (liveness = checks.getLiveness()) == null) {
            return false;
        }
        return liveness.getEnable();
    }

    private final boolean isFaceMatchCheckEnabled() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        WorkflowModule.Properties.Checks checks;
        WorkflowModule.Properties.Check faceMatch;
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
        sb.append("isFaceMatchCheckEnabled() called");
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
                    Log.println(3, str, "isFaceMatchCheckEnabled() called ");
                }
            }
        }
        WorkflowModule.Properties.Statement statement = this.currentStatement;
        if (statement == null || (checks = statement.getChecks()) == null || (faceMatch = checks.getFaceMatch()) == null) {
            return false;
        }
        return faceMatch.getEnable();
    }

    private final boolean isSpeechToTextMatchEnabled() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        WorkflowModule.Properties.Checks checks;
        WorkflowModule.Properties.Check speechToTextMatching;
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
        sb.append("isSpeechToTextMatchEnabled() called");
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
                    Log.println(3, str, "isSpeechToTextMatchEnabled() called ");
                }
            }
        }
        WorkflowModule.Properties.Statement statement = this.currentStatement;
        if (statement == null || (checks = statement.getChecks()) == null || (speechToTextMatching = checks.getSpeechToTextMatching()) == null) {
            return false;
        }
        return speechToTextMatching.getEnable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long getFaceDetectionTimeout() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        WorkflowModule.Properties.Checks checks;
        WorkflowModule.Properties.Check faceDetection;
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
        sb.append("getFaceDetectionTimeout() called");
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
                    Log.println(3, str, "getFaceDetectionTimeout() called ");
                }
            }
        }
        WorkflowModule.Properties.Statement statement = this.currentStatement;
        return (statement == null || (checks = statement.getChecks()) == null || (faceDetection = checks.getFaceDetection()) == null) ? ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS : faceDetection.getMaxOutOfFrameTime();
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0145, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0155, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0159, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0168, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x016a, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0177, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x017d, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0180, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0187, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "addValueInLogMap() called with: index = " + r18 + ", key = " + r19 + ", value = " + r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01a7, code lost:
    
        if (r4 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01a9, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01ab, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0158, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void addValueInLogMap(int index, String key, Object value) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
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
        String str4 = "addValueInLogMap() called with: index = " + index + ", key = " + key + ", value = " + value;
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
        Object obj = this.finalLogMap.get("statements");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type java.util.ArrayList<kotlin.collections.MutableMap<kotlin.String, kotlin.Any>>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.collections.MutableMap<kotlin.String, kotlin.Any>> }");
        ArrayList arrayList = (ArrayList) obj;
        if (arrayList.size() < index + 1) {
            arrayList.add(new LinkedHashMap());
        }
        Object obj2 = arrayList.get(index);
        Intrinsics.checkNotNullExpressionValue(obj2, "statementsLog[index]");
        ((Map) obj2).put(key, value);
    }

    private final void setIsFaceDetectionPass(boolean isPassed) {
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
        String str2 = "setIsFaceDetectionPass() called with: isPassed = " + isPassed;
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
                    String str3 = "setIsFaceDetectionPass() called with: isPassed = " + isPassed;
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
        if (isFaceDetectionEnabled()) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            linkedHashMap2.put("inFrame", asString(isPassed));
            Unit unit = Unit.INSTANCE;
            linkedHashMap.put("results", linkedHashMap2);
            Unit unit2 = Unit.INSTANCE;
            addValueInLogMap$default(this, 0, "faceDetection", linkedHashMap, 1, null);
            this.faceDetectionResults.put(this.currentStatementId, Boolean.valueOf(isPassed));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addImage(String url) {
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
        String str2 = "addImage() called with: url = " + url;
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
                    String str3 = "addImage() called with: url = " + url;
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
        String base64 = url != null ? toBase64(url) : "";
        Object obj = this.finalLogMap.get("images");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.String>");
        TypeIntrinsics.asMutableMap(obj).put(getStatementIdWithRestartCount() + "_IMAGE", base64Header + base64);
    }

    private final String formatSecondsToHHMMSS() {
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
        sb.append("formatSecondsToHHMMSS() called");
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
                    Log.println(3, str, "formatSecondsToHHMMSS() called ");
                }
            }
        }
        long currentTimeMillis = (System.currentTimeMillis() - this.startTimestamp) / 1000;
        long j = 3600;
        long j2 = currentTimeMillis / j;
        long j3 = 60;
        long j4 = (currentTimeMillis % j) / j3;
        long j5 = currentTimeMillis % j3;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%02d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j2), Long.valueOf(j4), Long.valueOf(j5)}, 3));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    private final String getStatementIdWithRestartCount() {
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
        sb.append("getStatementIdWithRestartCount() called");
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
                    Log.println(3, str, "getStatementIdWithRestartCount() called ");
                }
            }
        }
        return this.currentStatementId + '+' + this.noOfRestarts;
    }

    private final void handleSelfieNotCaptured() {
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
        sb.append("handleSelfieNotCaptured() called");
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
                        if (canonicalName2 == null) {
                            canonicalName2 = "N/A";
                        }
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher2.find()) {
                        canonicalName2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, canonicalName2, "handleSelfieNotCaptured() called ");
                }
            }
        }
        addImage(null);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("live", "no");
        Unit unit = Unit.INSTANCE;
        linkedHashMap.put("results", linkedHashMap2);
        linkedHashMap.put("apiResponse", new LinkedHashMap());
        addValueInLogMap$default(this, 0, "liveness", linkedHashMap, 1, null);
        addDeferred(CompletableDeferredKt.CompletableDeferred(false), RequestType.FACE_MATCH);
        if (isFaceMatchCheckEnabled()) {
            addDeferred(CompletableDeferredKt.CompletableDeferred(false), RequestType.LIVENESS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0139, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void makeLivenessAPICall(String imageUri) {
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
        String str2 = "makeLivenessAPICall() called with: imageUri = " + imageUri;
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
                    String str3 = "makeLivenessAPICall() called with: imageUri = " + imageUri;
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
        if (isLivenessCheckEnabled()) {
            File file = new File(imageUri);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), Dispatchers.getIO(), null, new VideoStatementVM$makeLivenessAPICall$2(this, new MultipartBody.Builder(null, 1, null).setType(MultipartBody.FORM).addFormDataPart("image", file.getName(), RequestBody.INSTANCE.create(file, MediaType.INSTANCE.get(ShareTarget.ENCODING_TYPE_MULTIPART))).build(), null), 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x013d, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void makeFaceMatchAPICall(String imageUri) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        Iterator it;
        String str;
        MultipartBody.Builder addFormDataPart;
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
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str3 = "makeFaceMatchAPICall() called with: imageUri = " + imageUri;
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
                    String str4 = "makeFaceMatchAPICall() called with: imageUri = " + imageUri;
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
        if (isFaceMatchCheckEnabled()) {
            Map mapOf = MapsKt.mapOf(TuplesKt.to("image", "image/jpeg"));
            MultipartBody.Builder builder = new MultipartBody.Builder(null, 1, null);
            List<WorkflowModule.Properties.RequestParam> faceMatchParams = this.videoStatementConfig.getFaceMatchParams();
            String str5 = base64Header;
            if (faceMatchParams != null) {
                List<WorkflowModule.Properties.RequestParam> faceMatchParams2 = this.videoStatementConfig.getFaceMatchParams();
                if (faceMatchParams2 != null) {
                    List<WorkflowModule.Properties.RequestParam> list = faceMatchParams2;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    Iterator it2 = list.iterator();
                    while (it2.hasNext()) {
                        WorkflowModule.Properties.RequestParam requestParam = (WorkflowModule.Properties.RequestParam) it2.next();
                        String name = requestParam.getName();
                        String value = requestParam.getValue();
                        String type = requestParam.getType();
                        builder.setType(MultipartBody.FORM);
                        if (Intrinsics.areEqual(type, "string")) {
                            addFormDataPart = builder.addFormDataPart(name, value);
                            it = it2;
                            str = str5;
                        } else if (Intrinsics.areEqual(type, "image")) {
                            if (value.length() == 0) {
                                value = imageUri;
                            } else if (this.isFirstStatement) {
                                Object obj = this.finalLogMap.get("images");
                                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.String>");
                                Map asMutableMap = TypeIntrinsics.asMutableMap(obj);
                                it = it2;
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(str5);
                                str = str5;
                                sb3.append(toBase64(value));
                                asMutableMap.put("REFERENCE_IMAGE", sb3.toString());
                                String substringAfterLast$default = StringsKt.substringAfterLast$default(value, RemoteSettings.FORWARD_SLASH_STRING, (String) null, 2, (Object) null);
                                RequestBody.Companion companion4 = RequestBody.INSTANCE;
                                File file = new File(value);
                                MediaType.Companion companion5 = MediaType.INSTANCE;
                                Object obj2 = mapOf.get(type);
                                Intrinsics.checkNotNull(obj2);
                                addFormDataPart = builder.addFormDataPart(name, substringAfterLast$default, companion4.create(file, companion5.get((String) obj2)));
                            }
                            it = it2;
                            str = str5;
                            String substringAfterLast$default2 = StringsKt.substringAfterLast$default(value, RemoteSettings.FORWARD_SLASH_STRING, (String) null, 2, (Object) null);
                            RequestBody.Companion companion42 = RequestBody.INSTANCE;
                            File file2 = new File(value);
                            MediaType.Companion companion52 = MediaType.INSTANCE;
                            Object obj22 = mapOf.get(type);
                            Intrinsics.checkNotNull(obj22);
                            addFormDataPart = builder.addFormDataPart(name, substringAfterLast$default2, companion42.create(file2, companion52.get((String) obj22)));
                        } else {
                            throw new NotImplementedError("An operation is not implemented: " + ("unknown request param type " + type));
                        }
                        arrayList.add(addFormDataPart);
                        it2 = it;
                        str5 = str;
                    }
                }
            } else {
                builder.addFormDataPart("type", "selfie");
                String substringAfterLast$default3 = StringsKt.substringAfterLast$default(imageUri, RemoteSettings.FORWARD_SLASH_STRING, (String) null, 2, (Object) null);
                RequestBody.Companion companion6 = RequestBody.INSTANCE;
                File file3 = new File(imageUri);
                MediaType.Companion companion7 = MediaType.INSTANCE;
                Object obj3 = mapOf.get("image");
                Intrinsics.checkNotNull(obj3);
                builder.addFormDataPart("image1", substringAfterLast$default3, companion6.create(file3, companion7.get((String) obj3)));
                String substringAfterLast$default4 = StringsKt.substringAfterLast$default(this.refImageUri, RemoteSettings.FORWARD_SLASH_STRING, (String) null, 2, (Object) null);
                RequestBody.Companion companion8 = RequestBody.INSTANCE;
                File file4 = new File(this.refImageUri);
                MediaType.Companion companion9 = MediaType.INSTANCE;
                Object obj4 = mapOf.get("image");
                Intrinsics.checkNotNull(obj4);
                builder.addFormDataPart("image2", substringAfterLast$default4, companion8.create(file4, companion9.get((String) obj4)));
                if (this.isFirstStatement) {
                    Object obj5 = this.finalLogMap.get("images");
                    Intrinsics.checkNotNull(obj5, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.String>");
                    TypeIntrinsics.asMutableMap(obj5).put("REFERENCE_IMAGE", base64Header + toBase64(this.refImageUri));
                }
            }
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), Dispatchers.getIO(), null, new VideoStatementVM$makeFaceMatchAPICall$2(this, builder.build(), null), 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object makeSpeechToTextMatchAPICall(String str, String str2, Continuation<? super Unit> continuation) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String id2;
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
        String str4 = "";
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
        String str5 = "makeSpeechToTextMatchAPICall() called with: audioUri = " + str + ", text = " + str2;
        if (str5 == null) {
            str5 = "null ";
        }
        sb.append(str5);
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
                            str3 = canonicalName2;
                        }
                    } else {
                        str3 = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                    if (matcher2.find()) {
                        str3 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                    }
                    if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str3 = str3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str6 = "makeSpeechToTextMatchAPICall() called with: audioUri = " + str + ", text = " + str2;
                    if (str6 == null) {
                        str6 = "null ";
                    }
                    sb2.append(str6);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str3, sb2.toString());
                }
            }
        }
        if (!isSpeechToTextMatchEnabled()) {
            return Unit.INSTANCE;
        }
        String base64 = toBase64(str);
        MediaType mediaType = MediaType.INSTANCE.get("application/json");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("audio", base64);
        MainVM mainVM = this.mainVM;
        linkedHashMap.put(WorkflowModule.Properties.VideoStatementV2API.TEXT_LANGUAGE_CODE, mainVM.getLanguageToBeUsed$hyperkyc_release(mainVM.getHyperKycConfig$hyperkyc_release()));
        KycCountry selectedCountry = this.mainVM.getSelectedCountry();
        if (selectedCountry != null && (id2 = selectedCountry.getId()) != null) {
            str4 = id2;
        }
        linkedHashMap.put(WorkflowModule.Properties.VideoStatementV2API.COUNTRY_CODE, str4);
        linkedHashMap.put("groundTruth", str2);
        linkedHashMap.put(WorkflowModule.Properties.VideoStatementV2API.PERFORM_TEXT_MATCH, "yes");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), Dispatchers.getIO(), null, new VideoStatementVM$makeSpeechToTextMatchAPICall$3(this, RequestBody.INSTANCE.create(new Gson().toJson(linkedHashMap).toString(), mediaType), null), 2, null);
        return Unit.INSTANCE;
    }

    public final Object makeLogVideoStatementAPICall(Continuation<? super Boolean> continuation) {
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
        sb.append("makeLogVideoStatementAPICall() called");
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
                    Log.println(3, str, "makeLogVideoStatementAPICall() called ");
                }
            }
        }
        MediaType mediaType = MediaType.INSTANCE.get("application/json");
        String json = new Gson().toJson(this.finalLogMap);
        RequestBody.Companion companion4 = RequestBody.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(json, "json");
        return sendRequest(this.videoStatementConfig.getLogVideoStatementUrl(), companion4.create(json, mediaType), RequestType.LOG_VIDEO_STATEMENT, continuation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0149, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void handleLogVideoStatementResult(Response response, String isPass, String responseRaw) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String str3;
        Matcher matcher;
        String str4;
        String className;
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
        String str5 = "handleLogVideoStatementResult() called with: response = " + response + ", isPass = " + isPass + ", responseRaw = " + responseRaw;
        if (str5 == null) {
            str5 = "null ";
        }
        sb.append(str5);
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
                        str3 = "N/A";
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                        if (matcher.find()) {
                            str3 = matcher.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                        }
                        if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str3 = str3.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        str4 = "handleLogVideoStatementResult() called with: response = " + response + ", isPass = " + isPass + ", responseRaw = " + responseRaw;
                        if (str4 == null) {
                            str4 = "null ";
                        }
                        sb2.append(str4);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str3, sb2.toString());
                    }
                    str3 = str2;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                    if (matcher.find()) {
                    }
                    if (str3.length() > 23) {
                        str3 = str3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb22 = new StringBuilder();
                    str4 = "handleLogVideoStatementResult() called with: response = " + response + ", isPass = " + isPass + ", responseRaw = " + responseRaw;
                    if (str4 == null) {
                    }
                    sb22.append(str4);
                    sb22.append(' ');
                    sb22.append("");
                    Log.println(3, str3, sb22.toString());
                }
            }
        }
        JSONObject jSONObject = new JSONObject(responseRaw != null ? responseRaw : "");
        jSONObject.getJSONObject("details").remove("images");
        MainVM.updateVideoStatementData$hyperkyc_release$default(this.mainVM, this.videoStatementUIState, new HyperKycData.VideoStatementData(Integer.valueOf(response.code()), jSONObject.toString(), response.headers().toMultimap(), isPass, this.refImageUri, new JSONArray(JSONExtsKt.extractJsonValue(responseRaw, "details.statements")).toString()), false, 4, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x013b, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x014b, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x014f, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x015e, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0160, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x016b, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0171, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0174, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x017b, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "addLivenessDataToLog() called with: responseMap = " + r18 + ", index = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0195, code lost:
    
        if (r4 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0197, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0199, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x014e, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean addLivenessDataToLog(Map<String, ? extends Object> responseMap, int index) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String str3;
        Map map;
        Map map2;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str4 = "N/A";
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
        String str5 = "addLivenessDataToLog() called with: responseMap = " + responseMap + ", index = " + index;
        if (str5 == null) {
            str5 = "null ";
        }
        sb.append(str5);
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
        Intrinsics.checkNotNull(responseMap, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.collections.Map<kotlin.String, kotlin.collections.Map<kotlin.String, kotlin.collections.Map<kotlin.String, kotlin.String>>>>");
        Map map3 = (Map) responseMap.get(Constant.PARAM_RESULT);
        if (map3 == null || (map = (Map) map3.get("details")) == null || (map2 = (Map) map.get("liveFace")) == null || (str3 = (String) map2.get("value")) == null) {
            str3 = "no";
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("live", str3);
        linkedHashMap.put("results", linkedHashMap2);
        linkedHashMap.put("apiResponse", responseMap);
        linkedHashMap.put("image", getStatementIdWithRestartCount() + "_IMAGE");
        addValueInLogMap(index, "liveness", linkedHashMap);
        return asBoolean(str3, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x013b, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x014b, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x014f, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x015e, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0160, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x016b, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0171, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0174, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x017b, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "addFaceMatchDataToLog() called with: responseMap = " + r18 + ", index = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0195, code lost:
    
        if (r4 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0197, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0199, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x014e, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean addFaceMatchDataToLog(Map<String, ? extends Object> responseMap, int index) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String str3;
        Map map;
        Map map2;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str4 = "N/A";
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
        String str5 = "addFaceMatchDataToLog() called with: responseMap = " + responseMap + ", index = " + index;
        if (str5 == null) {
            str5 = "null ";
        }
        sb.append(str5);
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
        Intrinsics.checkNotNull(responseMap, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.collections.Map<kotlin.String, kotlin.collections.Map<kotlin.String, kotlin.collections.Map<kotlin.String, kotlin.String>>>>");
        Map map3 = (Map) responseMap.get(Constant.PARAM_RESULT);
        if (map3 == null || (map = (Map) map3.get("details")) == null || (map2 = (Map) map.get("match")) == null || (str3 = (String) map2.get("value")) == null) {
            str3 = "no";
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("match", str3);
        linkedHashMap.put("results", linkedHashMap2);
        linkedHashMap.put("apiResponse", responseMap);
        linkedHashMap.put("image", getStatementIdWithRestartCount() + "_IMAGE");
        linkedHashMap.put("image2", "REFERENCE_IMAGE");
        addValueInLogMap(index, "faceMatch", linkedHashMap);
        return asBoolean(str3, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x014b, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01b4  */
    /* JADX WARN: Type inference failed for: r0v39 */
    /* JADX WARN: Type inference failed for: r0v40, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v47 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r18v0, types: [co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean addSpeechToTextMatchDataToLog(Map<String, ? extends Object> responseMap, int index, String text) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String str3;
        Matcher matcher;
        boolean z;
        String str4;
        String className;
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
        String str5 = "addSpeechToTextMatchDataToLog() called with: responseMap = " + responseMap + ", index = " + index + ", text = " + text;
        if (str5 == null) {
            str5 = "null ";
        }
        sb.append(str5);
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
                        str3 = "N/A";
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                        if (matcher.find()) {
                            str3 = matcher.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                        }
                        if (str3.length() > 23 || Build.VERSION.SDK_INT >= 26) {
                            z = false;
                        } else {
                            z = false;
                            str3 = str3.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        str4 = "addSpeechToTextMatchDataToLog() called with: responseMap = " + responseMap + ", index = " + index + ", text = " + text;
                        if (str4 == null) {
                            str4 = "null ";
                        }
                        sb2.append(str4);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str3, sb2.toString());
                        Object obj = responseMap.get("matchResult");
                        ?? r0 = obj instanceof Map ? (Map) obj : str;
                        Object obj2 = r0 != 0 ? r0.get("match") : str;
                        ?? r12 = obj2 instanceof Boolean ? (Boolean) obj2 : str;
                        boolean booleanValue = r12 != 0 ? r12.booleanValue() : z;
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                        linkedHashMap2.put("match", asString(booleanValue));
                        linkedHashMap.put("results", linkedHashMap2);
                        linkedHashMap.put("apiResponse", responseMap);
                        addValueInLogMap(index, "statementText", text);
                        addValueInLogMap(index, "speechToText", String.valueOf(responseMap.get("sttOutput")));
                        addValueInLogMap(index, "speechToTextMatching", linkedHashMap);
                        return booleanValue;
                    }
                    str3 = str2;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                    if (matcher.find()) {
                    }
                    if (str3.length() > 23) {
                    }
                    z = false;
                    StringBuilder sb22 = new StringBuilder();
                    str4 = "addSpeechToTextMatchDataToLog() called with: responseMap = " + responseMap + ", index = " + index + ", text = " + text;
                    if (str4 == null) {
                    }
                    sb22.append(str4);
                    sb22.append(' ');
                    sb22.append("");
                    Log.println(3, str3, sb22.toString());
                    Object obj3 = responseMap.get("matchResult");
                    if (obj3 instanceof Map) {
                    }
                    if (r0 != 0) {
                    }
                    if (obj2 instanceof Boolean) {
                    }
                    if (r12 != 0) {
                    }
                    LinkedHashMap linkedHashMap3 = new LinkedHashMap();
                    LinkedHashMap linkedHashMap22 = new LinkedHashMap();
                    linkedHashMap22.put("match", asString(booleanValue));
                    linkedHashMap3.put("results", linkedHashMap22);
                    linkedHashMap3.put("apiResponse", responseMap);
                    addValueInLogMap(index, "statementText", text);
                    addValueInLogMap(index, "speechToText", String.valueOf(responseMap.get("sttOutput")));
                    addValueInLogMap(index, "speechToTextMatching", linkedHashMap3);
                    return booleanValue;
                }
            }
        }
        str = null;
        z = false;
        Object obj32 = responseMap.get("matchResult");
        if (obj32 instanceof Map) {
        }
        if (r0 != 0) {
        }
        if (obj2 instanceof Boolean) {
        }
        if (r12 != 0) {
        }
        LinkedHashMap linkedHashMap32 = new LinkedHashMap();
        LinkedHashMap linkedHashMap222 = new LinkedHashMap();
        linkedHashMap222.put("match", asString(booleanValue));
        linkedHashMap32.put("results", linkedHashMap222);
        linkedHashMap32.put("apiResponse", responseMap);
        addValueInLogMap(index, "statementText", text);
        addValueInLogMap(index, "speechToText", String.valueOf(responseMap.get("sttOutput")));
        addValueInLogMap(index, "speechToTextMatching", linkedHashMap32);
        return booleanValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x013b, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x014b, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x014f, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x015e, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0160, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x016b, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0171, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0174, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x017b, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "addDeferred() called with: deferred = " + r18 + ", requestType = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0195, code lost:
    
        if (r4 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0197, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0199, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x014e, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void addDeferred(Deferred<Boolean> deferred, RequestType requestType) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
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
        String str4 = "addDeferred() called with: deferred = " + deferred + ", requestType = " + requestType;
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
        int i = WhenMappings.$EnumSwitchMapping$1[requestType.ordinal()];
        if (i == 1) {
            this.livenessCallsDeferred.put(this.currentStatementId, deferred);
        } else if (i == 2) {
            this.faceMatchCallsDeferred.put(this.currentStatementId, deferred);
        } else {
            if (i != 3) {
                return;
            }
            this.speechToTextMatchCallsDeferred.put(this.currentStatementId, deferred);
        }
    }

    private final Map<String, String> getHeaders(RequestType requestType) {
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
        String str2 = "getHeaders() called with: requestType = " + requestType;
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
                    String str3 = "getHeaders() called with: requestType = " + requestType;
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
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("transactionId", this.mainVM.getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release());
        linkedHashMap.put("journeyId", this.mainVM.getJourneyId$hyperkyc_release());
        linkedHashMap.put("moduleId", this.mainVM.getCurrentModuleId$hyperkyc_release());
        MainVM mainVM = this.mainVM;
        linkedHashMap.putAll(mainVM.getDefaultHeaders$hyperkyc_release(mainVM.getCurrentModuleId$hyperkyc_release()));
        if (requestType == RequestType.SPEECH_TO_TEXT_MATCH || requestType == RequestType.LOG_VIDEO_STATEMENT) {
            linkedHashMap.put("Content-Type", "application/json");
        }
        String accessToken = this.mainVM.getHyperKycConfig$hyperkyc_release().getAccessToken();
        if (!(accessToken == null || StringsKt.isBlank(accessToken))) {
            String accessToken2 = this.mainVM.getHyperKycConfig$hyperkyc_release().getAccessToken();
            Intrinsics.checkNotNull(accessToken2);
            linkedHashMap.put("Authorization", accessToken2);
        } else {
            String appId = this.mainVM.getHyperKycConfig$hyperkyc_release().getAppId();
            Intrinsics.checkNotNull(appId);
            linkedHashMap.put("appId", appId);
            String appKey = this.mainVM.getHyperKycConfig$hyperkyc_release().getAppKey();
            Intrinsics.checkNotNull(appKey);
            linkedHashMap.put(HyperKycConfig.APP_KEY, appKey);
        }
        return linkedHashMap;
    }

    private final void startAudioRecording(File filesDir) {
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
        String str2 = "startAudioRecording() called with: filesDir = " + filesDir;
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
                    String str3 = "startAudioRecording() called with: filesDir = " + filesDir;
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
        File file = new File(filesDir, this.currentStatementId + "_raw.pcm");
        HVAudioRecorder hvAudioRecorder = HVSessionRecorder.INSTANCE.getInstance().getHvAudioRecorder();
        if (hvAudioRecorder != null) {
            hvAudioRecorder.startPartialAudioRecord$hyperkyc_release(file);
        }
    }

    private final String stopAudioRecording(File filesDir) {
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
        String str2 = "stopAudioRecording() called with: filesDir = " + filesDir;
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
                    String str3 = "stopAudioRecording() called with: filesDir = " + filesDir;
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
        File file = new File(filesDir, this.currentStatementId + "_raw.pcm");
        File file2 = new File(filesDir, this.currentStatementId + "-converted.wav");
        HVAudioRecorder hvAudioRecorder = HVSessionRecorder.INSTANCE.getInstance().getHvAudioRecorder();
        if (hvAudioRecorder != null) {
            hvAudioRecorder.stopPartialAudioRecord$hyperkyc_release(file, file2);
        }
        String absolutePath = file2.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "encodedFile.absolutePath");
        return absolutePath;
    }

    private final String toBase64(String uri) {
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
        String str2 = "toBase64() called with: uri = " + uri;
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
                    String str3 = "toBase64() called with: uri = " + uri;
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
        File file = new File(uri);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[(int) file.length()];
        fileInputStream.read(bArr);
        fileInputStream.close();
        String encodeToString = Base64.encodeToString(bArr, 2);
        Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(data, Base64.NO_WRAP)");
        return encodeToString;
    }

    /* JADX WARN: Code restructure failed: missing block: B:72:0x0141, code lost:
    
        if (r0 == null) goto L57;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean asBoolean(String str, boolean z) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        String canonicalName2;
        Class<?> cls2;
        String className;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str2 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (str == null || (cls = str.getClass()) == null) ? null : cls.getCanonicalName();
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
        String str3 = "asBoolean() called with: default = " + z;
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
                        canonicalName2 = (str == null || (cls2 = str.getClass()) == null) ? null : cls2.getCanonicalName();
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
                    String str4 = "asBoolean() called with: default = " + z;
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
        if (Intrinsics.areEqual(str, "yes")) {
            return true;
        }
        if (Intrinsics.areEqual(str, "no")) {
            return false;
        }
        String str5 = str;
        return str5 == null || StringsKt.isBlank(str5) ? z : RuleEvaluatorKt.eval$default(injectFromVariables(str, true), false, 1, null);
    }

    private final String injectFromVariables(String str, boolean z) {
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
            canonicalName = (str == null || (cls = str.getClass()) == null) ? null : cls.getCanonicalName();
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
        String str4 = "injectFromVariables() called with: addQuotesIfEmpty = " + z;
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
                        if (str != null && (cls2 = str.getClass()) != null) {
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
                    String str5 = "injectFromVariables() called with: addQuotesIfEmpty = " + z;
                    if (str5 == null) {
                        str5 = "null ";
                    }
                    sb2.append(str5);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(2, str2, sb2.toString());
                }
            }
        }
        return this.mainVM.stringInjectFromVariables$hyperkyc_release(str, z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x013b, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String injectValuesFromDataMap(String template) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        Map.Entry<String, String> next;
        String className2;
        String str = template;
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
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str3 = "injectValuesFromDataMap() called with: template = " + str;
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
                    String str4 = "injectValuesFromDataMap() called with: template = " + str;
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
        Map<String, String> userData = this.videoStatementConfig.getUserData();
        if (userData == null) {
            return str;
        }
        Iterator<Map.Entry<String, String>> it = userData.entrySet().iterator();
        while (true) {
            String str5 = str;
            while (it.hasNext()) {
                next = it.next();
                if (str5 != null) {
                    break;
                }
                str5 = null;
            }
            return str5;
            str = StringsKt.replace$default(str5, "{{" + next.getKey() + "}}", next.getValue(), false, 4, (Object) null);
        }
    }
}
