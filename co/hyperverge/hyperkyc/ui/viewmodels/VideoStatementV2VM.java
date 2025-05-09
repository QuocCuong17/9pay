package co.hyperverge.hyperkyc.ui.viewmodels;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
import co.hyperverge.hyperkyc.data.models.VideoStatementV2Config;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.data.network.NetworkRepo;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoroutineExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.JSONExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import co.hyperverge.hypersnapsdk.service.security.GKYCSignatureVerify;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import com.facebook.share.internal.ShareConstants;
import com.tekartik.sqflite.Constant;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Pair;
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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: VideoStatementV2VM.kt */
@Metadata(d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001:\u0002^_B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010!\u001a\u00020\"H\u0002J\u001c\u0010#\u001a\u00020\u00172\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010%H\u0002J\u001c\u0010&\u001a\u00020\u00172\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010%H\u0002J$\u0010'\u001a\u00020\u00172\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010%2\u0006\u0010(\u001a\u00020\u000bH\u0002J\u0018\u0010)\u001a\u00020\"2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020\u0001H\u0002JT\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u000b2(\b\u0002\u00100\u001a\"\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013j\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0001\u0018\u0001`\u00142\u0010\b\u0002\u00101\u001a\n\u0012\u0004\u0012\u000203\u0018\u000102H\u0002J$\u00104\u001a\u00020\u00172\b\u00105\u001a\u0004\u0018\u00010\u000b2\b\u00106\u001a\u0004\u0018\u00010\u000b2\u0006\u00107\u001a\u00020\u000bH\u0002J\u0010\u00108\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\r\u00109\u001a\u00020:H\u0000¢\u0006\u0002\b;J@\u0010<\u001a\u00020\"2\u0006\u0010=\u001a\u00020>2\b\u00105\u001a\u0004\u0018\u00010\u000b2\b\u00106\u001a\u0004\u0018\u00010\u000b2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010%2\u0006\u0010?\u001a\u00020@H\u0002J\u001a\u0010A\u001a\u00020\"2\u0006\u0010=\u001a\u00020>2\b\u0010B\u001a\u0004\u0018\u00010\u000bH\u0002J\u0012\u0010C\u001a\u00020\"2\b\u0010B\u001a\u0004\u0018\u00010\u000bH\u0002J^\u0010D\u001a\u00020\"2K\u0010E\u001aG\u0012\u0013\u0012\u00110\u000b¢\u0006\f\bG\u0012\b\bH\u0012\u0004\b\b(I\u0012\u0013\u0012\u00110:¢\u0006\f\bG\u0012\b\bH\u0012\u0004\b\b(J\u0012\u0013\u0012\u00110\u000b¢\u0006\f\bG\u0012\b\bH\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\"0FH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010LJ^\u0010M\u001a\u00020\"2K\u0010E\u001aG\u0012\u0013\u0012\u00110\u000b¢\u0006\f\bG\u0012\b\bH\u0012\u0004\b\b(I\u0012\u0013\u0012\u00110:¢\u0006\f\bG\u0012\b\bH\u0012\u0004\b\b(J\u0012\u0013\u0012\u00110\u000b¢\u0006\f\bG\u0012\b\bH\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\"0FH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010LJ\u0010\u0010N\u001a\u00020\"2\u0006\u0010O\u001a\u00020:H\u0002J¬\u0001\u0010P\u001a\u00020Q2\b\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010 \u001a\u0004\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010R\u001a\b\u0012\u0004\u0012\u00020\"0S2K\u0010E\u001aG\u0012\u0013\u0012\u00110\u000b¢\u0006\f\bG\u0012\b\bH\u0012\u0004\b\b(I\u0012\u0013\u0012\u00110:¢\u0006\f\bG\u0012\b\bH\u0012\u0004\b\b(J\u0012\u0013\u0012\u00110\u000b¢\u0006\f\bG\u0012\b\bH\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\"0FH\u0080@ø\u0001\u0000¢\u0006\u0004\bT\u0010UJ\u0010\u0010V\u001a\u00020\u000b2\u0006\u0010W\u001a\u00020\u000bH\u0002Jn\u0010X\u001a\u00020\"2\u0006\u0010Y\u001a\u00020,2\u0006\u0010?\u001a\u00020@2K\u0010E\u001aG\u0012\u0013\u0012\u00110\u000b¢\u0006\f\bG\u0012\b\bH\u0012\u0004\b\b(I\u0012\u0013\u0012\u00110:¢\u0006\f\bG\u0012\b\bH\u0012\u0004\b\b(J\u0012\u0013\u0012\u00110\u000b¢\u0006\f\bG\u0012\b\bH\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\"0FH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010ZJ\u0016\u0010[\u001a\u00020\u0017*\u0004\u0018\u00010\u000b2\u0006\u0010\\\u001a\u00020\u0017H\u0002J\f\u0010]\u001a\u00020\u000b*\u00020\u0017H\u0002R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\u0013j\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0001`\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006`"}, d2 = {"Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementV2VM;", "", "mainVM", "Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "vsConfigV2", "Lco/hyperverge/hyperkyc/data/models/VideoStatementV2Config;", "uiState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$VideoStatementV2;", "sp", "Landroid/content/SharedPreferences;", "key", "", "(Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;Lco/hyperverge/hyperkyc/data/models/VideoStatementV2Config;Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$VideoStatementV2;Landroid/content/SharedPreferences;Ljava/lang/String;)V", "audioUri", "editor", "Landroid/content/SharedPreferences$Editor;", "endTimestamp", "", "finalLogMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", HVRetakeActivity.IMAGE_URI_TAG, "isFaceDetectionCheckFailed", "", "isFaceMatchCheckFailed", "isGenericFailure", "isLivenessCheckFailed", "isSTTCheckFailed", "startTimestamp", "statementText", "videoStatementV2Data", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$VideoStatementV2Data;", "videoUri", "addFaceDetectionDataToLog", "", "addFaceMatchDataToLog", "responseMap", "", "addLivenessDataToLog", "addSpeechToTextMatchDataToLog", "text", "addValueInLogMap", "value", "createAPICallState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$ApiCall;", "videoStatementV2API", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;", "type", "body", "params", "", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$RequestParam;", "evaluateRules", "responseJson", "responseHeaderJson", WorkflowModule.Properties.Section.Component.Validation.Type.RULE, "formatSecondsToHHMMSS", "getAttemptsCount", "", "getAttemptsCount$hyperkyc_release", "handleApiResults", "response", "Lokhttp3/Response;", "requestType", "Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementV2VM$RequestType;", "handleLogVideoStatementResult", "responseRaw", "handleVideoUploadResult", "makeCheckCalls", "finishWithErrorCallback", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "error", "errorCode", "errorMessage", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "makeStandardAPICalls", "setAttemptsCount", "attemptsCount", "startProcessing", "Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementV2VM$Check;", "processingTextChangeCallback", "Lkotlin/Function0;", "startProcessing$hyperkyc_release", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toBase64", ShareConstants.MEDIA_URI, "validateCheck", "apiCallState", "(Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$ApiCall;Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementV2VM$RequestType;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "asBoolean", "default", "asString", "Check", "RequestType", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VideoStatementV2VM {
    private String audioUri;
    private SharedPreferences.Editor editor;
    private long endTimestamp;
    private HashMap<String, Object> finalLogMap;
    private String imageUri;
    private boolean isFaceDetectionCheckFailed;
    private boolean isFaceMatchCheckFailed;
    private boolean isGenericFailure;
    private boolean isLivenessCheckFailed;
    private boolean isSTTCheckFailed;
    private final String key;
    private final MainVM mainVM;
    private final SharedPreferences sp;
    private long startTimestamp;
    private String statementText;
    private final WorkflowUIState.VideoStatementV2 uiState;
    private HyperKycData.VideoStatementV2Data videoStatementV2Data;
    private String videoUri;
    private final VideoStatementV2Config vsConfigV2;

    /* compiled from: VideoStatementV2VM.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementV2VM$Check;", "", "(Ljava/lang/String;I)V", "LIVENESS", "FACE_MATCH", "SPEECH_TO_TEXT_MATCH", "FACE_DETECTION", "GENERIC", "NONE", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public enum Check {
        LIVENESS,
        FACE_MATCH,
        SPEECH_TO_TEXT_MATCH,
        FACE_DETECTION,
        GENERIC,
        NONE
    }

    /* compiled from: VideoStatementV2VM.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lco/hyperverge/hyperkyc/ui/viewmodels/VideoStatementV2VM$RequestType;", "", "(Ljava/lang/String;I)V", "LIVENESS", "FACE_MATCH", "SPEECH_TO_TEXT_MATCH", "LOG_VIDEO_STATEMENT", "VIDEO_UPLOAD", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public enum RequestType {
        LIVENESS,
        FACE_MATCH,
        SPEECH_TO_TEXT_MATCH,
        LOG_VIDEO_STATEMENT,
        VIDEO_UPLOAD
    }

    /* compiled from: VideoStatementV2VM.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RequestType.values().length];
            try {
                iArr[RequestType.LIVENESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[RequestType.FACE_MATCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[RequestType.SPEECH_TO_TEXT_MATCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[RequestType.LOG_VIDEO_STATEMENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[RequestType.VIDEO_UPLOAD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public VideoStatementV2VM(MainVM mainVM, VideoStatementV2Config vsConfigV2, WorkflowUIState.VideoStatementV2 uiState, SharedPreferences sharedPreferences, String key) {
        Intrinsics.checkNotNullParameter(mainVM, "mainVM");
        Intrinsics.checkNotNullParameter(vsConfigV2, "vsConfigV2");
        Intrinsics.checkNotNullParameter(uiState, "uiState");
        Intrinsics.checkNotNullParameter(key, "key");
        this.mainVM = mainVM;
        this.vsConfigV2 = vsConfigV2;
        this.uiState = uiState;
        this.sp = sharedPreferences;
        this.key = key;
        this.statementText = "";
        this.finalLogMap = new HashMap<>();
        this.videoStatementV2Data = new HyperKycData.VideoStatementV2Data(null, null, null, null, null, null, null, null, 255, null);
        this.editor = sharedPreferences != null ? sharedPreferences.edit() : null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x003c  */
    /* JADX WARN: Type inference failed for: r10v21, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9, types: [T] */
    /* JADX WARN: Type inference failed for: r11v13 */
    /* JADX WARN: Type inference failed for: r11v18 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v8, types: [T] */
    /* JADX WARN: Type inference failed for: r2v28, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v23, types: [T, java.lang.Object, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object startProcessing$hyperkyc_release(String str, String str2, String str3, String str4, long j, long j2, boolean z, Function0<Unit> function0, Function3<? super String, ? super Integer, ? super String, Unit> function3, Continuation<? super Check> continuation) {
        VideoStatementV2VM$startProcessing$1 videoStatementV2VM$startProcessing$1;
        int i;
        ?? canonicalName;
        String str5;
        Object m1202constructorimpl;
        String str6;
        long j3;
        boolean z2;
        VideoStatementV2VM videoStatementV2VM;
        ?? canonicalName2;
        String str7;
        String className;
        String substringAfterLast$default;
        VideoStatementV2VM videoStatementV2VM2;
        String className2;
        if (continuation instanceof VideoStatementV2VM$startProcessing$1) {
            videoStatementV2VM$startProcessing$1 = (VideoStatementV2VM$startProcessing$1) continuation;
            if ((videoStatementV2VM$startProcessing$1.label & Integer.MIN_VALUE) != 0) {
                videoStatementV2VM$startProcessing$1.label -= Integer.MIN_VALUE;
                Object obj = videoStatementV2VM$startProcessing$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = videoStatementV2VM$startProcessing$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    VideoStatementV2VM$startProcessing$1 videoStatementV2VM$startProcessing$12 = videoStatementV2VM$startProcessing$1;
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
                        str5 = (String) objectRef.element;
                    } else {
                        str5 = ((String) objectRef.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(str5);
                    sb.append(" - ");
                    String str8 = "startProcessing() called with: imageUri = " + str + ", videoUri = " + str2 + ", audioUri = " + str3 + ", statementText = " + str4 + ", startTimestamp = " + j + ", endTimestamp = " + j2 + ", isFaceDetectionCheckFailed = " + z + ", processingTextChangeCallback = " + function0 + ", finishWithErrorCallback = " + function3;
                    if (str8 == null) {
                        str8 = "null ";
                    }
                    sb.append(str8);
                    sb.append(' ');
                    sb.append("");
                    companion.log(level, sb.toString());
                    if (CoreExtsKt.isRelease()) {
                        videoStatementV2VM = this;
                        str6 = str3;
                        z2 = z;
                        j3 = j;
                    } else {
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
                                    canonicalName2 = cls2 != null ? cls2.getCanonicalName() : 0;
                                    if (canonicalName2 == 0) {
                                        canonicalName2 = "N/A";
                                    }
                                } else {
                                    canonicalName2 = substringAfterLast$default;
                                }
                                objectRef2.element = canonicalName2;
                                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                if (matcher2.find()) {
                                    ?? replaceAll2 = matcher2.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                    objectRef2.element = replaceAll2;
                                }
                                if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                    str7 = (String) objectRef2.element;
                                } else {
                                    str7 = ((String) objectRef2.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb2 = new StringBuilder();
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append("startProcessing() called with: imageUri = ");
                                sb3.append(str);
                                sb3.append(", videoUri = ");
                                sb3.append(str2);
                                sb3.append(", audioUri = ");
                                str6 = str3;
                                sb3.append(str6);
                                sb3.append(", statementText = ");
                                sb3.append(str4);
                                sb3.append(", startTimestamp = ");
                                j3 = j;
                                sb3.append(j3);
                                sb3.append(", endTimestamp = ");
                                sb3.append(j2);
                                sb3.append(", isFaceDetectionCheckFailed = ");
                                z2 = z;
                                sb3.append(z2);
                                sb3.append(", processingTextChangeCallback = ");
                                sb3.append(function0);
                                sb3.append(", finishWithErrorCallback = ");
                                sb3.append(function3);
                                String sb4 = sb3.toString();
                                if (sb4 == null) {
                                    sb4 = "null ";
                                }
                                sb2.append(sb4);
                                sb2.append(' ');
                                sb2.append("");
                                Log.println(3, str7, sb2.toString());
                                videoStatementV2VM = this;
                            }
                        }
                        str6 = str3;
                        j3 = j;
                        z2 = z;
                        videoStatementV2VM = this;
                    }
                    videoStatementV2VM.imageUri = str;
                    videoStatementV2VM.audioUri = str6;
                    videoStatementV2VM.videoUri = str2;
                    videoStatementV2VM.statementText = str4;
                    videoStatementV2VM.startTimestamp = j3;
                    videoStatementV2VM.endTimestamp = j2;
                    videoStatementV2VM.isFaceDetectionCheckFailed = z2;
                    videoStatementV2VM.isLivenessCheckFailed = false;
                    videoStatementV2VM.isFaceMatchCheckFailed = false;
                    videoStatementV2VM.isSTTCheckFailed = false;
                    videoStatementV2VM.isGenericFailure = false;
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("videoRef", "");
                    hashMap.put("transactionId", videoStatementV2VM.mainVM.getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release());
                    hashMap.put("statements", new ArrayList());
                    hashMap.put("images", new LinkedHashMap());
                    videoStatementV2VM.finalLogMap = hashMap;
                    Object obj2 = hashMap.get("statements");
                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type java.util.ArrayList<kotlin.collections.MutableMap<kotlin.String, kotlin.Any>>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.collections.MutableMap<kotlin.String, kotlin.Any>> }");
                    ((ArrayList) obj2).add(new LinkedHashMap());
                    VideoStatementV2VM videoStatementV2VM3 = videoStatementV2VM;
                    VideoStatementV2VM$startProcessing$4 videoStatementV2VM$startProcessing$4 = new VideoStatementV2VM$startProcessing$4(this, z, function3, str, str2, str4, function0, null);
                    videoStatementV2VM$startProcessing$12.L$0 = videoStatementV2VM3;
                    videoStatementV2VM$startProcessing$12.Z$0 = z2;
                    videoStatementV2VM$startProcessing$12.label = 1;
                    if (CoroutineExtsKt.onIO$default(null, videoStatementV2VM$startProcessing$4, videoStatementV2VM$startProcessing$12, 1, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    videoStatementV2VM2 = videoStatementV2VM3;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    boolean z3 = videoStatementV2VM$startProcessing$1.Z$0;
                    videoStatementV2VM2 = (VideoStatementV2VM) videoStatementV2VM$startProcessing$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    z2 = z3;
                }
                if (z2) {
                    return videoStatementV2VM2.isLivenessCheckFailed ? Check.LIVENESS : videoStatementV2VM2.isFaceMatchCheckFailed ? Check.FACE_MATCH : videoStatementV2VM2.isSTTCheckFailed ? Check.SPEECH_TO_TEXT_MATCH : videoStatementV2VM2.isGenericFailure ? Check.GENERIC : Check.NONE;
                }
                return Check.FACE_DETECTION;
            }
        }
        videoStatementV2VM$startProcessing$1 = new VideoStatementV2VM$startProcessing$1(this, continuation);
        Object obj3 = videoStatementV2VM$startProcessing$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = videoStatementV2VM$startProcessing$1.label;
        if (i != 0) {
        }
        if (z2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object makeCheckCalls(Function3<? super String, ? super Integer, ? super String, Unit> function3, Continuation<? super Unit> continuation) {
        Object onIO$default = CoroutineExtsKt.onIO$default(null, new VideoStatementV2VM$makeCheckCalls$2(this, function3, null), continuation, 1, null);
        return onIO$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? onIO$default : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object makeStandardAPICalls(Function3<? super String, ? super Integer, ? super String, Unit> function3, Continuation<? super Unit> continuation) {
        Object onIO$default = CoroutineExtsKt.onIO$default(null, new VideoStatementV2VM$makeStandardAPICalls$2(function3, this, null), continuation, 1, null);
        return onIO$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? onIO$default : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ WorkflowUIState.ApiCall createAPICallState$default(VideoStatementV2VM videoStatementV2VM, WorkflowModule.Properties.VideoStatementV2API videoStatementV2API, String str, HashMap hashMap, List list, int i, Object obj) {
        if ((i & 4) != 0) {
            hashMap = null;
        }
        if ((i & 8) != 0) {
            list = null;
        }
        return videoStatementV2VM.createAPICallState(videoStatementV2API, str, hashMap, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x049c, code lost:
    
        if (r14 != 0) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x05b9, code lost:
    
        if (r4 != null) goto L210;
     */
    /* JADX WARN: Code restructure failed: missing block: B:235:0x0107, code lost:
    
        if (r1 != 0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:278:0x0230, code lost:
    
        if (r6 != 0) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x06a2, code lost:
    
        if (r6 != null) goto L246;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x07cd, code lost:
    
        if (r2 != null) goto L294;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:128:0x042a  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0571  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x057a  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x063f  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0650  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x033e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:260:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x040b  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0666  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x004b  */
    /* JADX WARN: Type inference failed for: r0v30, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v38, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v60, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v97, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v17 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v5, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v18, types: [T] */
    /* JADX WARN: Type inference failed for: r14v24 */
    /* JADX WARN: Type inference failed for: r14v25 */
    /* JADX WARN: Type inference failed for: r14v26 */
    /* JADX WARN: Type inference failed for: r14v30, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v35 */
    /* JADX WARN: Type inference failed for: r1v35, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v37 */
    /* JADX WARN: Type inference failed for: r1v38 */
    /* JADX WARN: Type inference failed for: r1v39 */
    /* JADX WARN: Type inference failed for: r1v42, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v5, types: [T] */
    /* JADX WARN: Type inference failed for: r1v52 */
    /* JADX WARN: Type inference failed for: r2v22, types: [T] */
    /* JADX WARN: Type inference failed for: r2v37, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v38 */
    /* JADX WARN: Type inference failed for: r2v39 */
    /* JADX WARN: Type inference failed for: r2v65, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v83 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v25, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v31 */
    /* JADX WARN: Type inference failed for: r4v39 */
    /* JADX WARN: Type inference failed for: r4v40 */
    /* JADX WARN: Type inference failed for: r4v42, types: [T] */
    /* JADX WARN: Type inference failed for: r4v52, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v59, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v60 */
    /* JADX WARN: Type inference failed for: r6v21, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v23 */
    /* JADX WARN: Type inference failed for: r6v24 */
    /* JADX WARN: Type inference failed for: r6v25 */
    /* JADX WARN: Type inference failed for: r6v28, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v37, types: [T] */
    /* JADX WARN: Type inference failed for: r6v47 */
    /* JADX WARN: Type inference failed for: r6v48 */
    /* JADX WARN: Type inference failed for: r6v59 */
    /* JADX WARN: Type inference failed for: r6v60 */
    /* JADX WARN: Type inference failed for: r6v9, types: [T] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object validateCheck(WorkflowUIState.ApiCall apiCall, RequestType requestType, Function3<? super String, ? super Integer, ? super String, Unit> function3, Continuation<? super Unit> continuation) {
        VideoStatementV2VM$validateCheck$1 videoStatementV2VM$validateCheck$1;
        int i;
        String str;
        String str2;
        String str3;
        ?? r1;
        String str4;
        String str5;
        Object m1202constructorimpl;
        String str6;
        String str7;
        Object obj;
        ?? r6;
        String str8;
        String str9;
        String className;
        String str10;
        String str11;
        String str12;
        Object obj2;
        VideoStatementV2VM$validateCheck$1 videoStatementV2VM$validateCheck$12;
        String str13;
        String str14;
        String str15;
        String str16;
        ?? m396customApiCalleH_QyT8$hyperkyc_release$default;
        Object obj3;
        VideoStatementV2VM videoStatementV2VM;
        String className2;
        Response response;
        String str17;
        String str18;
        String str19;
        Object obj4;
        String str20;
        String str21;
        String str22;
        String str23;
        int code;
        ?? r11;
        int i2;
        boolean z;
        Function3<? super String, ? super Integer, ? super String, Unit> function32;
        Response response2;
        ?? r4;
        Response response3;
        Throwable m1205exceptionOrNullimpl;
        Function3<? super String, ? super Integer, ? super String, Unit> function33;
        Object obj5;
        String str24;
        String str25;
        String str26;
        String str27;
        String str28;
        String str29;
        String str30;
        String str31;
        String str32;
        Class<?> cls;
        String className3;
        Class<?> cls2;
        String className4;
        boolean z2;
        ?? r14;
        String str33;
        Object m1202constructorimpl2;
        Response response4;
        String str34;
        Class<?> cls3;
        String str35;
        String className5;
        Class<?> cls4;
        String className6;
        List list;
        WorkflowUIState.ApiCall apiCall2 = apiCall;
        RequestType requestType2 = requestType;
        Function3<? super String, ? super Integer, ? super String, Unit> function34 = function3;
        if (continuation instanceof VideoStatementV2VM$validateCheck$1) {
            videoStatementV2VM$validateCheck$1 = (VideoStatementV2VM$validateCheck$1) continuation;
            if ((videoStatementV2VM$validateCheck$1.label & Integer.MIN_VALUE) != 0) {
                videoStatementV2VM$validateCheck$1.label -= Integer.MIN_VALUE;
                Object obj6 = videoStatementV2VM$validateCheck$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = videoStatementV2VM$validateCheck$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj6);
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    VideoStatementV2VM$validateCheck$1 videoStatementV2VM$validateCheck$13 = videoStatementV2VM$validateCheck$1;
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null) {
                        str = "Throwable().stackTrace";
                        str2 = "packageName";
                        str3 = "null cannot be cast to non-null type android.app.Application";
                    } else {
                        str = "Throwable().stackTrace";
                        str2 = "packageName";
                        str3 = "null cannot be cast to non-null type android.app.Application";
                        r1 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls5 = getClass();
                    r1 = cls5 != null ? cls5.getCanonicalName() : 0;
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
                        str4 = (String) objectRef.element;
                    } else {
                        str4 = ((String) objectRef.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(str4);
                    sb.append(" - ");
                    String str36 = "validateCheck() called with: apiCallState = " + apiCall2 + ", requestType = " + requestType2 + ", finishWithErrorCallback = " + function34;
                    if (str36 == null) {
                        str36 = "null ";
                    }
                    sb.append(str36);
                    sb.append(' ');
                    sb.append("");
                    companion.log(level, sb.toString());
                    if (CoreExtsKt.isRelease()) {
                        str6 = str;
                        str7 = str2;
                        str5 = str3;
                    } else {
                        try {
                            Result.Companion companion2 = Result.INSTANCE;
                            Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            str5 = str3;
                            try {
                                Intrinsics.checkNotNull(invoke, str5);
                                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                            } catch (Throwable th) {
                                th = th;
                                Result.Companion companion3 = Result.INSTANCE;
                                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                                }
                                String str37 = (String) m1202constructorimpl;
                                if (CoreExtsKt.isDebug()) {
                                }
                                NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                                String url = apiCall.getUrl();
                                String method = apiCall.getMethod();
                                HashMap<String, String> headers = apiCall.getHeaders();
                                RequestBody requestBody = apiCall2.getRequestBody(new Function1<String, Object>() { // from class: co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM$validateCheck$3
                                    /* JADX INFO: Access modifiers changed from: package-private */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(String getRequestBody) {
                                        MainVM mainVM;
                                        Intrinsics.checkNotNullParameter(getRequestBody, "$this$getRequestBody");
                                        mainVM = VideoStatementV2VM.this.mainVM;
                                        return mainVM.injectFromVariables$hyperkyc_release(getRequestBody);
                                    }
                                });
                                videoStatementV2VM$validateCheck$13.L$0 = this;
                                videoStatementV2VM$validateCheck$13.L$1 = apiCall2;
                                videoStatementV2VM$validateCheck$13.L$2 = requestType2;
                                videoStatementV2VM$validateCheck$13.L$3 = function34;
                                String str38 = str5;
                                videoStatementV2VM$validateCheck$13.label = 1;
                                str10 = "this as java.lang.String…ing(startIndex, endIndex)";
                                str11 = "replaceAll(\"\")";
                                str12 = str6;
                                obj2 = "error";
                                videoStatementV2VM$validateCheck$12 = videoStatementV2VM$validateCheck$13;
                                str13 = " - ";
                                str14 = "getInitialApplication";
                                str15 = str38;
                                str16 = "";
                                m396customApiCalleH_QyT8$hyperkyc_release$default = NetworkRepo.m396customApiCalleH_QyT8$hyperkyc_release$default(networkRepo, url, method, headers, requestBody, 0L, null, 0, videoStatementV2VM$validateCheck$12, 112, null);
                                obj3 = coroutine_suspended;
                                if (m396customApiCalleH_QyT8$hyperkyc_release$default != obj3) {
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            str5 = str3;
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                            m1202constructorimpl = "";
                        }
                        String str372 = (String) m1202constructorimpl;
                        if (CoreExtsKt.isDebug()) {
                            str6 = str;
                            str7 = str2;
                        } else {
                            str7 = str2;
                            Intrinsics.checkNotNullExpressionValue(str372, str7);
                            if (StringsKt.contains$default((CharSequence) str372, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                str6 = str;
                                Intrinsics.checkNotNullExpressionValue(stackTrace2, str6);
                                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                                    obj = null;
                                } else {
                                    obj = null;
                                    r6 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                }
                                Class<?> cls6 = getClass();
                                r6 = cls6 != null ? cls6.getCanonicalName() : obj;
                                if (r6 == 0) {
                                    r6 = "N/A";
                                }
                                objectRef2.element = r6;
                                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                if (matcher2.find()) {
                                    ?? replaceAll2 = matcher2.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                    objectRef2.element = replaceAll2;
                                }
                                if (((String) objectRef2.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str8 = ((String) objectRef2.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str8, "this as java.lang.String…ing(startIndex, endIndex)");
                                    StringBuilder sb2 = new StringBuilder();
                                    str9 = "validateCheck() called with: apiCallState = " + apiCall2 + ", requestType = " + requestType2 + ", finishWithErrorCallback = " + function34;
                                    if (str9 == null) {
                                        str9 = "null ";
                                    }
                                    sb2.append(str9);
                                    sb2.append(' ');
                                    sb2.append("");
                                    Log.println(3, str8, sb2.toString());
                                    NetworkRepo networkRepo2 = NetworkRepo.INSTANCE;
                                    String url2 = apiCall.getUrl();
                                    String method2 = apiCall.getMethod();
                                    HashMap<String, String> headers2 = apiCall.getHeaders();
                                    RequestBody requestBody2 = apiCall2.getRequestBody(new Function1<String, Object>() { // from class: co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM$validateCheck$3
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(String getRequestBody) {
                                            MainVM mainVM;
                                            Intrinsics.checkNotNullParameter(getRequestBody, "$this$getRequestBody");
                                            mainVM = VideoStatementV2VM.this.mainVM;
                                            return mainVM.injectFromVariables$hyperkyc_release(getRequestBody);
                                        }
                                    });
                                    videoStatementV2VM$validateCheck$13.L$0 = this;
                                    videoStatementV2VM$validateCheck$13.L$1 = apiCall2;
                                    videoStatementV2VM$validateCheck$13.L$2 = requestType2;
                                    videoStatementV2VM$validateCheck$13.L$3 = function34;
                                    String str382 = str5;
                                    videoStatementV2VM$validateCheck$13.label = 1;
                                    str10 = "this as java.lang.String…ing(startIndex, endIndex)";
                                    str11 = "replaceAll(\"\")";
                                    str12 = str6;
                                    obj2 = "error";
                                    videoStatementV2VM$validateCheck$12 = videoStatementV2VM$validateCheck$13;
                                    str13 = " - ";
                                    str14 = "getInitialApplication";
                                    str15 = str382;
                                    str16 = "";
                                    m396customApiCalleH_QyT8$hyperkyc_release$default = NetworkRepo.m396customApiCalleH_QyT8$hyperkyc_release$default(networkRepo2, url2, method2, headers2, requestBody2, 0L, null, 0, videoStatementV2VM$validateCheck$12, 112, null);
                                    obj3 = coroutine_suspended;
                                    if (m396customApiCalleH_QyT8$hyperkyc_release$default != obj3) {
                                        return obj3;
                                    }
                                    videoStatementV2VM = this;
                                    response = m396customApiCalleH_QyT8$hyperkyc_release$default;
                                }
                                str8 = (String) objectRef2.element;
                                StringBuilder sb22 = new StringBuilder();
                                str9 = "validateCheck() called with: apiCallState = " + apiCall2 + ", requestType = " + requestType2 + ", finishWithErrorCallback = " + function34;
                                if (str9 == null) {
                                }
                                sb22.append(str9);
                                sb22.append(' ');
                                sb22.append("");
                                Log.println(3, str8, sb22.toString());
                                NetworkRepo networkRepo22 = NetworkRepo.INSTANCE;
                                String url22 = apiCall.getUrl();
                                String method22 = apiCall.getMethod();
                                HashMap<String, String> headers22 = apiCall.getHeaders();
                                RequestBody requestBody22 = apiCall2.getRequestBody(new Function1<String, Object>() { // from class: co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM$validateCheck$3
                                    /* JADX INFO: Access modifiers changed from: package-private */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(String getRequestBody) {
                                        MainVM mainVM;
                                        Intrinsics.checkNotNullParameter(getRequestBody, "$this$getRequestBody");
                                        mainVM = VideoStatementV2VM.this.mainVM;
                                        return mainVM.injectFromVariables$hyperkyc_release(getRequestBody);
                                    }
                                });
                                videoStatementV2VM$validateCheck$13.L$0 = this;
                                videoStatementV2VM$validateCheck$13.L$1 = apiCall2;
                                videoStatementV2VM$validateCheck$13.L$2 = requestType2;
                                videoStatementV2VM$validateCheck$13.L$3 = function34;
                                String str3822 = str5;
                                videoStatementV2VM$validateCheck$13.label = 1;
                                str10 = "this as java.lang.String…ing(startIndex, endIndex)";
                                str11 = "replaceAll(\"\")";
                                str12 = str6;
                                obj2 = "error";
                                videoStatementV2VM$validateCheck$12 = videoStatementV2VM$validateCheck$13;
                                str13 = " - ";
                                str14 = "getInitialApplication";
                                str15 = str3822;
                                str16 = "";
                                m396customApiCalleH_QyT8$hyperkyc_release$default = NetworkRepo.m396customApiCalleH_QyT8$hyperkyc_release$default(networkRepo22, url22, method22, headers22, requestBody22, 0L, null, 0, videoStatementV2VM$validateCheck$12, 112, null);
                                obj3 = coroutine_suspended;
                                if (m396customApiCalleH_QyT8$hyperkyc_release$default != obj3) {
                                }
                            } else {
                                str6 = str;
                            }
                        }
                    }
                    NetworkRepo networkRepo222 = NetworkRepo.INSTANCE;
                    String url222 = apiCall.getUrl();
                    String method222 = apiCall.getMethod();
                    HashMap<String, String> headers222 = apiCall.getHeaders();
                    RequestBody requestBody222 = apiCall2.getRequestBody(new Function1<String, Object>() { // from class: co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM$validateCheck$3
                        /* JADX INFO: Access modifiers changed from: package-private */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(String getRequestBody) {
                            MainVM mainVM;
                            Intrinsics.checkNotNullParameter(getRequestBody, "$this$getRequestBody");
                            mainVM = VideoStatementV2VM.this.mainVM;
                            return mainVM.injectFromVariables$hyperkyc_release(getRequestBody);
                        }
                    });
                    videoStatementV2VM$validateCheck$13.L$0 = this;
                    videoStatementV2VM$validateCheck$13.L$1 = apiCall2;
                    videoStatementV2VM$validateCheck$13.L$2 = requestType2;
                    videoStatementV2VM$validateCheck$13.L$3 = function34;
                    String str38222 = str5;
                    videoStatementV2VM$validateCheck$13.label = 1;
                    str10 = "this as java.lang.String…ing(startIndex, endIndex)";
                    str11 = "replaceAll(\"\")";
                    str12 = str6;
                    obj2 = "error";
                    videoStatementV2VM$validateCheck$12 = videoStatementV2VM$validateCheck$13;
                    str13 = " - ";
                    str14 = "getInitialApplication";
                    str15 = str38222;
                    str16 = "";
                    m396customApiCalleH_QyT8$hyperkyc_release$default = NetworkRepo.m396customApiCalleH_QyT8$hyperkyc_release$default(networkRepo222, url222, method222, headers222, requestBody222, 0L, null, 0, videoStatementV2VM$validateCheck$12, 112, null);
                    obj3 = coroutine_suspended;
                    if (m396customApiCalleH_QyT8$hyperkyc_release$default != obj3) {
                    }
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        int i3 = videoStatementV2VM$validateCheck$1.I$0;
                        Response response5 = (Response) videoStatementV2VM$validateCheck$1.L$5;
                        r4 = videoStatementV2VM$validateCheck$1.L$4;
                        function32 = (Function3) videoStatementV2VM$validateCheck$1.L$3;
                        RequestType requestType3 = (RequestType) videoStatementV2VM$validateCheck$1.L$2;
                        apiCall2 = (WorkflowUIState.ApiCall) videoStatementV2VM$validateCheck$1.L$1;
                        VideoStatementV2VM videoStatementV2VM2 = (VideoStatementV2VM) videoStatementV2VM$validateCheck$1.L$0;
                        ResultKt.throwOnFailure(obj6);
                        str10 = "this as java.lang.String…ing(startIndex, endIndex)";
                        str11 = "replaceAll(\"\")";
                        str12 = "Throwable().stackTrace";
                        str14 = "getInitialApplication";
                        str13 = " - ";
                        str15 = "null cannot be cast to non-null type android.app.Application";
                        str16 = "";
                        code = i3;
                        obj2 = "error";
                        r11 = 0;
                        z = true;
                        response2 = response5;
                        videoStatementV2VM = videoStatementV2VM2;
                        requestType2 = requestType3;
                        str7 = "packageName";
                        i2 = 2;
                        HyperKycData.APIData aPIData = (HyperKycData.APIData) obj6;
                        Map responseHeaders = aPIData.getResponseHeaders();
                        String str39 = (responseHeaders != null || (list = (List) responseHeaders.get("x-hv-signature")) == null) ? r11 : (String) CollectionsKt.first(list);
                        if (apiCall2.getValidateSignature() || !apiCall2.getAllowedStatusCodes().contains(Boxing.boxInt(code)) || requestType2 == RequestType.VIDEO_UPLOAD) {
                            z2 = z;
                        } else {
                            GKYCSignatureVerify.GKYCSignatureVerifyBuilder requestHeaders = GKYCSignatureVerify.builder().requestQuery(apiCall2.getRequestQueryJSON()).requestBody(apiCall2.getRequestBodyJSON()).requestHeaders(JSONExtsKt.toJSONObject(apiCall2.getHeaders()));
                            String responseBodyRaw$hyperkyc_release = aPIData.getResponseBodyRaw$hyperkyc_release();
                            GKYCSignatureVerify.GKYCSignatureVerifyBuilder responseBody = requestHeaders.responseBody(responseBodyRaw$hyperkyc_release != null ? new JSONObject(responseBodyRaw$hyperkyc_release) : r11);
                            Map responseHeaders2 = aPIData.getResponseHeaders();
                            z2 = responseBody.responseHeaders(responseHeaders2 != null ? new JSONObject(responseHeaders2) : r11).tagFileUriMap(apiCall2.getTagFileUriMap()).build().verify(str39);
                        }
                        if (requestType2 != RequestType.SPEECH_TO_TEXT_MATCH && requestType2 != RequestType.LOG_VIDEO_STATEMENT) {
                            z = false;
                        }
                        if (z2) {
                            obj4 = obj2;
                            function32.invoke(obj4, Boxing.boxInt(112), AppConstants.SIGNATURE_ERROR);
                        } else {
                            obj4 = obj2;
                            if (aPIData.isSuccess(apiCall2.getAllowedStatusCodes(), z)) {
                                String responseBodyRaw$hyperkyc_release2 = aPIData.getResponseBodyRaw$hyperkyc_release();
                                String jSONString = JSONExtsKt.toJSONString(MapsKt.toMap(response2.headers()));
                                Map<String, ? extends Object> map = responseBodyRaw$hyperkyc_release2 != null ? JSONExtsKt.toMap(new JSONObject(responseBodyRaw$hyperkyc_release2)) : r11;
                                Intrinsics.checkNotNull(map, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any>");
                                videoStatementV2VM.handleApiResults(response2, responseBodyRaw$hyperkyc_release2, jSONString, map, requestType2);
                            } else {
                                HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                                StringBuilder sb3 = new StringBuilder();
                                Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                                str19 = str12;
                                Intrinsics.checkNotNullExpressionValue(stackTrace3, str19);
                                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                                if (stackTraceElement3 != null && (className6 = stackTraceElement3.getClassName()) != null) {
                                    r14 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) r11, i2, (Object) r11);
                                }
                                r14 = (videoStatementV2VM == null || (cls4 = videoStatementV2VM.getClass()) == null) ? r11 : cls4.getCanonicalName();
                                if (r14 == 0) {
                                    r14 = "N/A";
                                }
                                objectRef3.element = r14;
                                Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                                if (matcher3.find()) {
                                    str23 = str16;
                                    ?? replaceAll3 = matcher3.replaceAll(str23);
                                    str18 = str11;
                                    Intrinsics.checkNotNullExpressionValue(replaceAll3, str18);
                                    objectRef3.element = replaceAll3;
                                } else {
                                    str18 = str11;
                                    str23 = str16;
                                }
                                if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                    str33 = (String) objectRef3.element;
                                } else {
                                    str33 = ((String) objectRef3.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str33, str10);
                                }
                                sb3.append(str33);
                                str21 = str13;
                                sb3.append(str21);
                                String str40 = "videoStatementV2: error making api call : " + code;
                                if (str40 == null) {
                                    str40 = "null ";
                                }
                                sb3.append(str40);
                                sb3.append(' ');
                                sb3.append(str23);
                                companion4.log(level2, sb3.toString());
                                CoreExtsKt.isRelease();
                                try {
                                    Result.Companion companion5 = Result.INSTANCE;
                                    str20 = str14;
                                    try {
                                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod(str20, new Class[0]).invoke(null, new Object[0]);
                                        str22 = str15;
                                        try {
                                            Intrinsics.checkNotNull(invoke2, str22);
                                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                        } catch (Throwable th3) {
                                            th = th3;
                                            Result.Companion companion6 = Result.INSTANCE;
                                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                            }
                                            String str41 = (String) m1202constructorimpl2;
                                            if (CoreExtsKt.isDebug()) {
                                            }
                                            function32.invoke(obj4, Boxing.boxInt(code), "API call failed!");
                                            response3 = response4;
                                            function34 = function32;
                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(response3);
                                            if (m1205exceptionOrNullimpl != null) {
                                            }
                                            return Unit.INSTANCE;
                                        }
                                    } catch (Throwable th4) {
                                        th = th4;
                                        str22 = str15;
                                        Result.Companion companion62 = Result.INSTANCE;
                                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                        }
                                        String str412 = (String) m1202constructorimpl2;
                                        if (CoreExtsKt.isDebug()) {
                                        }
                                        function32.invoke(obj4, Boxing.boxInt(code), "API call failed!");
                                        response3 = response4;
                                        function34 = function32;
                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(response3);
                                        if (m1205exceptionOrNullimpl != null) {
                                        }
                                        return Unit.INSTANCE;
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                    str20 = str14;
                                }
                                if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                    m1202constructorimpl2 = str23;
                                }
                                String str4122 = (String) m1202constructorimpl2;
                                if (CoreExtsKt.isDebug()) {
                                    Intrinsics.checkNotNullExpressionValue(str4122, str7);
                                    response4 = r4;
                                    str17 = str7;
                                    if (StringsKt.contains$default((CharSequence) str4122, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                        Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                        Intrinsics.checkNotNullExpressionValue(stackTrace4, str19);
                                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                        if (stackTraceElement4 != null && (className5 = stackTraceElement4.getClassName()) != null) {
                                            String substringAfterLast$default = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                            str34 = substringAfterLast$default;
                                        }
                                        String canonicalName = (videoStatementV2VM == null || (cls3 = videoStatementV2VM.getClass()) == null) ? null : cls3.getCanonicalName();
                                        str34 = canonicalName == null ? "N/A" : canonicalName;
                                        objectRef4.element = str34;
                                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                        if (matcher4.find()) {
                                            ?? replaceAll4 = matcher4.replaceAll(str23);
                                            Intrinsics.checkNotNullExpressionValue(replaceAll4, str18);
                                            objectRef4.element = replaceAll4;
                                        }
                                        if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                            str35 = (String) objectRef4.element;
                                        } else {
                                            str35 = ((String) objectRef4.element).substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str35, str10);
                                        }
                                        StringBuilder sb4 = new StringBuilder();
                                        String str42 = "videoStatementV2: error making api call : " + code;
                                        if (str42 == null) {
                                            str42 = "null ";
                                        }
                                        sb4.append(str42);
                                        sb4.append(' ');
                                        sb4.append(str23);
                                        Log.println(6, str35, sb4.toString());
                                    }
                                } else {
                                    response4 = r4;
                                    str17 = str7;
                                }
                                function32.invoke(obj4, Boxing.boxInt(code), "API call failed!");
                                response3 = response4;
                                function34 = function32;
                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(response3);
                                if (m1205exceptionOrNullimpl != null) {
                                    HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                                    HyperLogger companion7 = HyperLogger.INSTANCE.getInstance();
                                    StringBuilder sb5 = new StringBuilder();
                                    Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
                                    StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace5, str19);
                                    StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                                    if (stackTraceElement5 == null || (className4 = stackTraceElement5.getClassName()) == null) {
                                        function33 = function34;
                                        obj5 = obj4;
                                        str24 = str19;
                                    } else {
                                        function33 = function34;
                                        obj5 = obj4;
                                        str24 = str19;
                                        String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        str25 = substringAfterLast$default2;
                                    }
                                    String canonicalName2 = (videoStatementV2VM == null || (cls2 = videoStatementV2VM.getClass()) == null) ? null : cls2.getCanonicalName();
                                    str25 = canonicalName2 == null ? "N/A" : canonicalName2;
                                    objectRef5.element = str25;
                                    Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef5.element);
                                    if (matcher5.find()) {
                                        ?? replaceAll5 = matcher5.replaceAll(str23);
                                        Intrinsics.checkNotNullExpressionValue(replaceAll5, str18);
                                        objectRef5.element = replaceAll5;
                                    }
                                    if (((String) objectRef5.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                        str26 = (String) objectRef5.element;
                                    } else {
                                        str26 = ((String) objectRef5.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str26, str10);
                                    }
                                    sb5.append(str26);
                                    sb5.append(str21);
                                    String str43 = "videoStatementV2: API call failed " + m1205exceptionOrNullimpl.getMessage();
                                    if (str43 == null) {
                                        str43 = "null ";
                                    }
                                    sb5.append(str43);
                                    sb5.append(' ');
                                    String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                                    if (localizedMessage != null) {
                                        str27 = '\n' + localizedMessage;
                                    } else {
                                        str27 = str23;
                                    }
                                    sb5.append(str27);
                                    companion7.log(level3, sb5.toString());
                                    CoreExtsKt.isRelease();
                                    try {
                                        Result.Companion companion8 = Result.INSTANCE;
                                        Object invoke3 = Class.forName("android.app.AppGlobals").getMethod(str20, new Class[0]).invoke(null, new Object[0]);
                                        Intrinsics.checkNotNull(invoke3, str22);
                                        str28 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                                    } catch (Throwable th6) {
                                        Result.Companion companion9 = Result.INSTANCE;
                                        str28 = Result.m1202constructorimpl(ResultKt.createFailure(th6));
                                    }
                                    String str44 = str28;
                                    if (Result.m1208isFailureimpl(str44)) {
                                        str44 = str23;
                                    }
                                    String str45 = str44;
                                    if (CoreExtsKt.isDebug()) {
                                        Intrinsics.checkNotNullExpressionValue(str45, str17);
                                        if (StringsKt.contains$default((CharSequence) str45, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                            Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
                                            StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                            Intrinsics.checkNotNullExpressionValue(stackTrace6, str24);
                                            StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                            if (stackTraceElement6 == null || (className3 = stackTraceElement6.getClassName()) == null) {
                                                str29 = null;
                                            } else {
                                                str29 = null;
                                                String substringAfterLast$default3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                str30 = substringAfterLast$default3;
                                            }
                                            String canonicalName3 = (videoStatementV2VM == null || (cls = videoStatementV2VM.getClass()) == null) ? str29 : cls.getCanonicalName();
                                            str30 = canonicalName3 == null ? "N/A" : canonicalName3;
                                            objectRef6.element = str30;
                                            Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef6.element);
                                            if (matcher6.find()) {
                                                ?? replaceAll6 = matcher6.replaceAll(str23);
                                                Intrinsics.checkNotNullExpressionValue(replaceAll6, str18);
                                                objectRef6.element = replaceAll6;
                                            }
                                            if (((String) objectRef6.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                                str31 = (String) objectRef6.element;
                                            } else {
                                                str31 = ((String) objectRef6.element).substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(str31, str10);
                                            }
                                            StringBuilder sb6 = new StringBuilder();
                                            String str46 = "videoStatementV2: API call failed " + m1205exceptionOrNullimpl.getMessage();
                                            if (str46 == null) {
                                                str46 = "null ";
                                            }
                                            sb6.append(str46);
                                            sb6.append(' ');
                                            String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str29;
                                            if (localizedMessage2 != null) {
                                                str32 = '\n' + localizedMessage2;
                                            } else {
                                                str32 = str23;
                                            }
                                            sb6.append(str32);
                                            Log.println(6, str31, sb6.toString());
                                        }
                                    }
                                    function33.invoke(obj5, Boxing.boxInt(104), "API call failed! - " + m1205exceptionOrNullimpl.getMessage());
                                }
                                return Unit.INSTANCE;
                            }
                        }
                        response4 = r4;
                        str17 = str7;
                        str18 = str11;
                        str19 = str12;
                        str20 = str14;
                        str21 = str13;
                        str22 = str15;
                        str23 = str16;
                        response3 = response4;
                        function34 = function32;
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(response3);
                        if (m1205exceptionOrNullimpl != null) {
                        }
                        return Unit.INSTANCE;
                    }
                    Function3<? super String, ? super Integer, ? super String, Unit> function35 = (Function3) videoStatementV2VM$validateCheck$1.L$3;
                    requestType2 = (RequestType) videoStatementV2VM$validateCheck$1.L$2;
                    WorkflowUIState.ApiCall apiCall3 = (WorkflowUIState.ApiCall) videoStatementV2VM$validateCheck$1.L$1;
                    VideoStatementV2VM videoStatementV2VM3 = (VideoStatementV2VM) videoStatementV2VM$validateCheck$1.L$0;
                    ResultKt.throwOnFailure(obj6);
                    videoStatementV2VM$validateCheck$12 = videoStatementV2VM$validateCheck$1;
                    str10 = "this as java.lang.String…ing(startIndex, endIndex)";
                    str11 = "replaceAll(\"\")";
                    str12 = "Throwable().stackTrace";
                    str14 = "getInitialApplication";
                    str13 = " - ";
                    str7 = "packageName";
                    str15 = "null cannot be cast to non-null type android.app.Application";
                    str16 = "";
                    obj2 = "error";
                    videoStatementV2VM = videoStatementV2VM3;
                    obj3 = coroutine_suspended;
                    function34 = function35;
                    apiCall2 = apiCall3;
                    response = ((Result) obj6).getValue();
                }
                if (!Result.m1209isSuccessimpl(response)) {
                    Response response6 = response;
                    code = response6.code();
                    r11 = 0;
                    VideoStatementV2VM$validateCheck$4$apiData$1 videoStatementV2VM$validateCheck$4$apiData$1 = new VideoStatementV2VM$validateCheck$4$apiData$1(response6, null);
                    VideoStatementV2VM$validateCheck$1 videoStatementV2VM$validateCheck$14 = videoStatementV2VM$validateCheck$12;
                    videoStatementV2VM$validateCheck$14.L$0 = videoStatementV2VM;
                    videoStatementV2VM$validateCheck$14.L$1 = apiCall2;
                    videoStatementV2VM$validateCheck$14.L$2 = requestType2;
                    videoStatementV2VM$validateCheck$14.L$3 = function34;
                    videoStatementV2VM$validateCheck$14.L$4 = response;
                    videoStatementV2VM$validateCheck$14.L$5 = response6;
                    videoStatementV2VM$validateCheck$14.I$0 = code;
                    i2 = 2;
                    videoStatementV2VM$validateCheck$14.label = 2;
                    z = true;
                    Object onIO$default = CoroutineExtsKt.onIO$default(null, videoStatementV2VM$validateCheck$4$apiData$1, videoStatementV2VM$validateCheck$14, 1, null);
                    if (onIO$default == obj3) {
                        return obj3;
                    }
                    function32 = function34;
                    response2 = response6;
                    r4 = response;
                    obj6 = onIO$default;
                    HyperKycData.APIData aPIData2 = (HyperKycData.APIData) obj6;
                    Map responseHeaders3 = aPIData2.getResponseHeaders();
                    if (responseHeaders3 != null) {
                    }
                    if (apiCall2.getValidateSignature()) {
                    }
                    z2 = z;
                    if (requestType2 != RequestType.SPEECH_TO_TEXT_MATCH) {
                        z = false;
                    }
                    if (z2) {
                    }
                    response4 = r4;
                    str17 = str7;
                    str18 = str11;
                    str19 = str12;
                    str20 = str14;
                    str21 = str13;
                    str22 = str15;
                    str23 = str16;
                    response3 = response4;
                    function34 = function32;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(response3);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    return Unit.INSTANCE;
                }
                str17 = str7;
                str18 = str11;
                str19 = str12;
                obj4 = obj2;
                str20 = str14;
                str21 = str13;
                str22 = str15;
                str23 = str16;
                response3 = response;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(response3);
                if (m1205exceptionOrNullimpl != null) {
                }
                return Unit.INSTANCE;
            }
        }
        videoStatementV2VM$validateCheck$1 = new VideoStatementV2VM$validateCheck$1(this, continuation);
        Object obj62 = videoStatementV2VM$validateCheck$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = videoStatementV2VM$validateCheck$1.label;
        if (i != 0) {
        }
        if (!Result.m1209isSuccessimpl(response)) {
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

    public final int getAttemptsCount$hyperkyc_release() {
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
        sb.append("getAttemptsCount() called");
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
                    Log.println(3, str, "getAttemptsCount() called ");
                }
            }
        }
        SharedPreferences sharedPreferences = this.sp;
        if (sharedPreferences != null) {
            return sharedPreferences.getInt(this.key, 0);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAttemptsCount(int attemptsCount) {
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
        String str2 = "setAttemptsCount() called with: attemptsCount = " + attemptsCount;
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
                    String str3 = "setAttemptsCount() called with: attemptsCount = " + attemptsCount;
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
        SharedPreferences.Editor editor = this.editor;
        if (editor != null) {
            editor.putInt(this.key, attemptsCount);
        }
        SharedPreferences.Editor editor2 = this.editor;
        if (editor2 != null) {
            editor2.commit();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0155, code lost:
    
        if (r0 != null) goto L56;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x025f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final WorkflowUIState.ApiCall createAPICallState(WorkflowModule.Properties.VideoStatementV2API videoStatementV2API, String type, HashMap<String, Object> body, List<WorkflowModule.Properties.RequestParam> params) {
        String canonicalName;
        Object m1202constructorimpl;
        List<WorkflowModule.Properties.RequestParam> list;
        String str;
        String str2;
        boolean z;
        String className;
        String accessToken;
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
        String str3 = "createAPICallState() called with: videoStatementV2API = " + videoStatementV2API + ", type = " + type + ", body = " + body + ", params = " + params;
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
                    list = params;
                    z = false;
                    HashMap<String, String> headers = videoStatementV2API.getHeaders();
                    MainVM mainVM = this.mainVM;
                    headers.putAll(mainVM.getDefaultHeaders$hyperkyc_release(mainVM.getCurrentModuleId$hyperkyc_release()));
                    HashMap<String, String> headers2 = videoStatementV2API.getHeaders();
                    headers2.put("transactionId", this.mainVM.getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release());
                    headers2.put("journeyId", this.mainVM.getJourneyId$hyperkyc_release() + '_' + getAttemptsCount$hyperkyc_release());
                    headers2.put("moduleId", this.mainVM.getCurrentModuleId$hyperkyc_release());
                    accessToken = this.mainVM.getHyperKycConfig$hyperkyc_release().getAccessToken();
                    if (!((accessToken != null || StringsKt.isBlank(accessToken)) ? true : z)) {
                    }
                    String str4 = null;
                    String url = videoStatementV2API.getUrl();
                    List<Integer> allowedStatusCodes = videoStatementV2API.getAllowedStatusCodes();
                    Map map = null;
                    HashMap<String, String> headers3 = videoStatementV2API.getHeaders();
                    if (list == null) {
                    }
                    return new WorkflowUIState.ApiCall("", "", str4, url, "post", type, allowedStatusCodes, map, headers3, list, body, this.vsConfigV2.getValidateSignature(), null, null, 12420, null);
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
                if (str2.length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                    z = false;
                } else {
                    z = false;
                    str2 = str2.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                StringBuilder sb2 = new StringBuilder();
                StringBuilder sb3 = new StringBuilder();
                sb3.append("createAPICallState() called with: videoStatementV2API = ");
                sb3.append(videoStatementV2API);
                sb3.append(", type = ");
                sb3.append(type);
                sb3.append(", body = ");
                sb3.append(body);
                sb3.append(", params = ");
                list = params;
                sb3.append(list);
                String sb4 = sb3.toString();
                if (sb4 == null) {
                    sb4 = "null ";
                }
                sb2.append(sb4);
                sb2.append(' ');
                sb2.append("");
                Log.println(3, str2, sb2.toString());
                HashMap<String, String> headers4 = videoStatementV2API.getHeaders();
                MainVM mainVM2 = this.mainVM;
                headers4.putAll(mainVM2.getDefaultHeaders$hyperkyc_release(mainVM2.getCurrentModuleId$hyperkyc_release()));
                HashMap<String, String> headers22 = videoStatementV2API.getHeaders();
                headers22.put("transactionId", this.mainVM.getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release());
                headers22.put("journeyId", this.mainVM.getJourneyId$hyperkyc_release() + '_' + getAttemptsCount$hyperkyc_release());
                headers22.put("moduleId", this.mainVM.getCurrentModuleId$hyperkyc_release());
                accessToken = this.mainVM.getHyperKycConfig$hyperkyc_release().getAccessToken();
                if (!((accessToken != null || StringsKt.isBlank(accessToken)) ? true : z)) {
                    String accessToken2 = this.mainVM.getHyperKycConfig$hyperkyc_release().getAccessToken();
                    Intrinsics.checkNotNull(accessToken2);
                    headers22.put("Authorization", accessToken2);
                } else {
                    String appId = this.mainVM.getHyperKycConfig$hyperkyc_release().getAppId();
                    Intrinsics.checkNotNull(appId);
                    headers22.put("appId", appId);
                    String appKey = this.mainVM.getHyperKycConfig$hyperkyc_release().getAppKey();
                    Intrinsics.checkNotNull(appKey);
                    headers22.put(HyperKycConfig.APP_KEY, appKey);
                }
                String str42 = null;
                String url2 = videoStatementV2API.getUrl();
                List<Integer> allowedStatusCodes2 = videoStatementV2API.getAllowedStatusCodes();
                Map map2 = null;
                HashMap<String, String> headers32 = videoStatementV2API.getHeaders();
                if (list == null) {
                    list = videoStatementV2API.getParameters();
                }
                return new WorkflowUIState.ApiCall("", "", str42, url2, "post", type, allowedStatusCodes2, map2, headers32, list, body, this.vsConfigV2.getValidateSignature(), null, null, 12420, null);
            }
        }
        list = params;
        z = false;
        HashMap<String, String> headers42 = videoStatementV2API.getHeaders();
        MainVM mainVM22 = this.mainVM;
        headers42.putAll(mainVM22.getDefaultHeaders$hyperkyc_release(mainVM22.getCurrentModuleId$hyperkyc_release()));
        HashMap<String, String> headers222 = videoStatementV2API.getHeaders();
        headers222.put("transactionId", this.mainVM.getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release());
        headers222.put("journeyId", this.mainVM.getJourneyId$hyperkyc_release() + '_' + getAttemptsCount$hyperkyc_release());
        headers222.put("moduleId", this.mainVM.getCurrentModuleId$hyperkyc_release());
        accessToken = this.mainVM.getHyperKycConfig$hyperkyc_release().getAccessToken();
        if (!((accessToken != null || StringsKt.isBlank(accessToken)) ? true : z)) {
        }
        String str422 = null;
        String url22 = videoStatementV2API.getUrl();
        List<Integer> allowedStatusCodes22 = videoStatementV2API.getAllowedStatusCodes();
        Map map22 = null;
        HashMap<String, String> headers322 = videoStatementV2API.getHeaders();
        if (list == null) {
        }
        return new WorkflowUIState.ApiCall("", "", str422, url22, "post", type, allowedStatusCodes22, map22, headers322, list, body, this.vsConfigV2.getValidateSignature(), null, null, 12420, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:125:0x0164, code lost:
    
        if (r0 != null) goto L56;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x02bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void handleApiResults(Response response, String responseJson, String responseHeaderJson, Map<String, ? extends Object> responseMap, RequestType requestType) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String str3;
        boolean z;
        String className;
        int i;
        WorkflowModule.Properties.StatementV2.ChecksV2 checks;
        WorkflowModule.Properties.StatementV2.ChecksV2.CheckV2 liveness;
        WorkflowModule.Properties.StatementV2.ChecksV2 checks2;
        WorkflowModule.Properties.StatementV2.ChecksV2.CheckV2 liveness2;
        String rule;
        WorkflowModule.Properties.StatementV2.ChecksV2 checks3;
        WorkflowModule.Properties.StatementV2.ChecksV2.CheckV2 faceMatch;
        WorkflowModule.Properties.StatementV2.ChecksV2 checks4;
        WorkflowModule.Properties.StatementV2.ChecksV2.CheckV2 faceMatch2;
        String rule2;
        WorkflowModule.Properties.StatementV2.ChecksV2 checks5;
        WorkflowModule.Properties.StatementV2.ChecksV2.CheckV2 speechToTextMatch;
        WorkflowModule.Properties.StatementV2.ChecksV2 checks6;
        WorkflowModule.Properties.StatementV2.ChecksV2.CheckV2 speechToTextMatch2;
        String rule3;
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
        String str5 = "handleApiResults() called with: response = " + response + ", responseJson = " + responseJson + ", responseHeaderJson = " + responseHeaderJson + ", responseMap = " + responseMap + ", requestType = " + requestType;
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
                if (!StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    str = responseHeaderJson;
                    z = false;
                    str2 = null;
                    i = WhenMappings.$EnumSwitchMapping$0[requestType.ordinal()];
                    if (i == 1) {
                    }
                } else {
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
                    if (str3 == null) {
                        str3 = "N/A";
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                    if (matcher2.find()) {
                        str3 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                    }
                    if (str3.length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        z = false;
                    } else {
                        z = false;
                        str3 = str3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("handleApiResults() called with: response = ");
                    sb3.append(response);
                    sb3.append(", responseJson = ");
                    sb3.append(responseJson);
                    sb3.append(", responseHeaderJson = ");
                    str = responseHeaderJson;
                    sb3.append(str);
                    sb3.append(", responseMap = ");
                    sb3.append(responseMap);
                    sb3.append(", requestType = ");
                    sb3.append(requestType);
                    String sb4 = sb3.toString();
                    if (sb4 == null) {
                        sb4 = "null ";
                    }
                    sb2.append(sb4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str3, sb2.toString());
                    i = WhenMappings.$EnumSwitchMapping$0[requestType.ordinal()];
                    if (i == 1) {
                        boolean z2 = z;
                        WorkflowModule.Properties.StatementV2 statement = this.vsConfigV2.getStatement();
                        if (statement != null && (checks2 = statement.getChecks()) != null && (liveness2 = checks2.getLiveness()) != null && (rule = liveness2.getRule()) != null) {
                            str4 = rule;
                        }
                        if (!evaluateRules(responseJson, str, str4)) {
                            WorkflowModule.Properties.StatementV2 statement2 = this.vsConfigV2.getStatement();
                            if (!asBoolean((statement2 == null || (checks = statement2.getChecks()) == null || (liveness = checks.getLiveness()) == null) ? str2 : liveness.getAllowIfCheckFailed(), z2)) {
                                this.isLivenessCheckFailed = true;
                            }
                        }
                        addLivenessDataToLog(responseMap);
                        return;
                    }
                    if (i == 2) {
                        boolean z3 = z;
                        WorkflowModule.Properties.StatementV2 statement3 = this.vsConfigV2.getStatement();
                        if (statement3 != null && (checks4 = statement3.getChecks()) != null && (faceMatch2 = checks4.getFaceMatch()) != null && (rule2 = faceMatch2.getRule()) != null) {
                            str4 = rule2;
                        }
                        if (!evaluateRules(responseJson, str, str4)) {
                            WorkflowModule.Properties.StatementV2 statement4 = this.vsConfigV2.getStatement();
                            if (!asBoolean((statement4 == null || (checks3 = statement4.getChecks()) == null || (faceMatch = checks3.getFaceMatch()) == null) ? str2 : faceMatch.getAllowIfCheckFailed(), z3)) {
                                this.isFaceMatchCheckFailed = true;
                            }
                        }
                        addFaceMatchDataToLog(responseMap);
                        return;
                    }
                    if (i != 3) {
                        if (i == 4) {
                            handleLogVideoStatementResult(response, responseJson);
                            return;
                        } else {
                            if (i != 5) {
                                return;
                            }
                            handleVideoUploadResult(responseJson);
                            return;
                        }
                    }
                    boolean z4 = z;
                    WorkflowModule.Properties.StatementV2 statement5 = this.vsConfigV2.getStatement();
                    if (statement5 != null && (checks6 = statement5.getChecks()) != null && (speechToTextMatch2 = checks6.getSpeechToTextMatch()) != null && (rule3 = speechToTextMatch2.getRule()) != null) {
                        str4 = rule3;
                    }
                    if (!evaluateRules(responseJson, str, str4)) {
                        WorkflowModule.Properties.StatementV2 statement6 = this.vsConfigV2.getStatement();
                        if (!asBoolean((statement6 == null || (checks5 = statement6.getChecks()) == null || (speechToTextMatch = checks5.getSpeechToTextMatch()) == null) ? str2 : speechToTextMatch.getAllowIfCheckFailed(), z4)) {
                            this.isSTTCheckFailed = true;
                        }
                    }
                    addSpeechToTextMatchDataToLog(responseMap, this.statementText);
                    return;
                }
            }
        }
        str = responseHeaderJson;
        z = false;
        str2 = null;
        i = WhenMappings.$EnumSwitchMapping$0[requestType.ordinal()];
        if (i == 1) {
        }
    }

    private final boolean evaluateRules(String responseJson, String responseHeaderJson, String rule) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String className;
        String substringAfterLast$default;
        String str2;
        String className2;
        String str3 = rule;
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
        String str4 = "evaluateRules() called with: responseJson = " + responseJson + ", responseHeaderJson = " + responseHeaderJson + ", rule = " + str3;
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
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        str = canonicalName2 == null ? "N/A" : canonicalName2;
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
                    String str5 = "evaluateRules() called with: responseJson = " + responseJson + ", responseHeaderJson = " + responseHeaderJson + ", rule = " + str3;
                    if (str5 == null) {
                        str5 = "null ";
                    }
                    sb2.append(str5);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (MatchResult matchResult : Regex.findAll$default(new Regex("response|headers"), str3, 0, 2, null)) {
            int first = matchResult.getRange().getFirst();
            char charAt = str3.charAt(first);
            String str6 = "";
            while (true) {
                if (!Character.isLetterOrDigit(charAt) && charAt != '[' && charAt != ']') {
                    if (charAt != '.') {
                        break;
                    }
                }
                str6 = str6 + charAt;
                first++;
                if (first >= rule.length()) {
                    break;
                }
                charAt = str3.charAt(first);
            }
            Pair pair = new Pair(Integer.valueOf(matchResult.getRange().getFirst()), Integer.valueOf(matchResult.getRange().getFirst() + str6.length()));
            Object responseValue$hyperkyc_release = this.mainVM.getResponseValue$hyperkyc_release(responseJson, responseHeaderJson, str6, str6);
            if (responseValue$hyperkyc_release == null || (str2 = responseValue$hyperkyc_release.toString()) == null) {
                str2 = "";
            }
            linkedHashMap.put(pair, str2);
        }
        int i = 0;
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            str3 = StringsKt.replaceRange((CharSequence) str3, ((Number) ((Pair) entry.getKey()).getFirst()).intValue() + i, ((Number) ((Pair) entry.getKey()).getSecond()).intValue() + i, (CharSequence) entry.getValue()).toString();
            i = (i - (((Number) ((Pair) entry.getKey()).getSecond()).intValue() - ((Number) ((Pair) entry.getKey()).getFirst()).intValue())) + ((String) entry.getValue()).length();
        }
        return this.mainVM.processCheckRule$hyperkyc_release(str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addValueInLogMap(String key, Object value) {
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
        String str2 = "addValueInLogMap() called with: key = " + key + ", value = " + value;
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
                    String str3 = "addValueInLogMap() called with: key = " + key + ", value = " + value;
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
        Object obj = this.finalLogMap.get("statements");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type java.util.ArrayList<kotlin.collections.MutableMap<kotlin.String, kotlin.Any>>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.collections.MutableMap<kotlin.String, kotlin.Any>> }");
        Object obj2 = ((ArrayList) obj).get(0);
        Intrinsics.checkNotNullExpressionValue(obj2, "statementsLog[0]");
        ((Map) obj2).put(key, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void addFaceDetectionDataToLog() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        WorkflowModule.Properties.StatementV2.ChecksV2 checks;
        WorkflowModule.Properties.StatementV2.ChecksV2.CheckV2 faceDetection;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        String str2 = null;
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
        sb.append("addFaceDetectionDataToLog() called");
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
                    Log.println(3, str, "addFaceDetectionDataToLog() called ");
                }
            }
        }
        WorkflowModule.Properties.StatementV2 statement = this.vsConfigV2.getStatement();
        if (statement != null && (checks = statement.getChecks()) != null && (faceDetection = checks.getFaceDetection()) != null) {
            str2 = faceDetection.getEnable();
        }
        if (asBoolean(str2, false)) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            linkedHashMap2.put("inFrame", asString(!this.isFaceDetectionCheckFailed));
            Unit unit = Unit.INSTANCE;
            linkedHashMap.put("results", linkedHashMap2);
            Unit unit2 = Unit.INSTANCE;
            addValueInLogMap("faceDetection", linkedHashMap);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:80:0x013d, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean addLivenessDataToLog(Map<String, ? extends Object> responseMap) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String str;
        Map map;
        Map map2;
        String str2;
        Map map3;
        Map map4;
        Map map5;
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
        String str5 = "addLivenessDataToLog() called with: responseMap = " + responseMap;
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str3 = canonicalName2;
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
                    String str6 = "addLivenessDataToLog() called with: responseMap = " + responseMap;
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
        boolean z = responseMap instanceof Map;
        Map<String, ? extends Object> map6 = z ? responseMap : null;
        Map<String, ? extends Object> map7 = z ? responseMap : null;
        if (map7 == null || (map3 = (Map) map7.get(Constant.PARAM_RESULT)) == null || (map4 = (Map) map3.get("details")) == null || (map5 = (Map) map4.get("liveFace")) == null || (str = (String) map5.get("value")) == null) {
            str = "no";
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("live", str);
        linkedHashMap.put("results", linkedHashMap2);
        linkedHashMap.put("apiResponse", responseMap);
        if (map6 != null && (map = (Map) map6.get(Constant.PARAM_RESULT)) != null && (map2 = (Map) map.get("inputImageUrls")) != null && (str2 = (String) map2.get("image")) != null) {
            str4 = str2;
        }
        linkedHashMap.put("image", str4);
        addValueInLogMap("liveness", linkedHashMap);
        return asBoolean(str, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:89:0x013d, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean addFaceMatchDataToLog(Map<String, ? extends Object> responseMap) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String str;
        String str2;
        Map map;
        Map map2;
        String str3;
        Map map3;
        Map map4;
        Map map5;
        Map map6;
        Map map7;
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
        String str5 = "";
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
        String str6 = "addFaceMatchDataToLog() called with: responseMap = " + responseMap;
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
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str4 = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                    if (matcher2.find()) {
                        str4 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                    }
                    if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str4 = str4.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str7 = "addFaceMatchDataToLog() called with: responseMap = " + responseMap;
                    if (str7 == null) {
                        str7 = "null ";
                    }
                    sb2.append(str7);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str4, sb2.toString());
                }
            }
        }
        boolean z = responseMap instanceof Map;
        Map<String, ? extends Object> map8 = z ? responseMap : null;
        Map<String, ? extends Object> map9 = z ? responseMap : null;
        if (map9 == null || (map5 = (Map) map9.get(Constant.PARAM_RESULT)) == null || (map6 = (Map) map5.get("details")) == null || (map7 = (Map) map6.get("match")) == null || (str = (String) map7.get("value")) == null) {
            str = "no";
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("results", MapsKt.mutableMapOf(TuplesKt.to("match", str)));
        linkedHashMap.put("apiResponse", responseMap);
        if (map8 == null || (map3 = (Map) map8.get(Constant.PARAM_RESULT)) == null || (map4 = (Map) map3.get("inputImageUrls")) == null || (str2 = (String) map4.get("image1")) == null) {
            str2 = "";
        }
        linkedHashMap.put("image", str2);
        if (map8 != null && (map = (Map) map8.get(Constant.PARAM_RESULT)) != null && (map2 = (Map) map.get("inputImageUrls")) != null && (str3 = (String) map2.get("image2")) != null) {
            str5 = str3;
        }
        linkedHashMap.put("image2", str5);
        addValueInLogMap("faceMatch", linkedHashMap);
        return asBoolean(str, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x013c, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x014c, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0150, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x015f, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0161, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x016c, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0172, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0175, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x017c, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "addSpeechToTextMatchDataToLog() called with: responseMap = " + r18 + ", text = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0196, code lost:
    
        if (r4 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0198, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x019a, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x014f, code lost:
    
        r8 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v39 */
    /* JADX WARN: Type inference failed for: r0v40, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v43 */
    /* JADX WARN: Type inference failed for: r0v44, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r0v50 */
    /* JADX WARN: Type inference failed for: r0v51 */
    /* JADX WARN: Type inference failed for: r17v0, types: [co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean addSpeechToTextMatchDataToLog(Map<String, ? extends Object> responseMap, String text) {
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
        String str4 = "addSpeechToTextMatchDataToLog() called with: responseMap = " + responseMap + ", text = " + text;
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
        str = null;
        Object obj = responseMap.get("matchResult");
        ?? r0 = obj instanceof Map ? (Map) obj : str;
        Object obj2 = r0 != 0 ? r0.get("match") : str;
        ?? r02 = obj2 instanceof Boolean ? (Boolean) obj2 : str;
        boolean booleanValue = r02 != 0 ? r02.booleanValue() : false;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("match", asString(booleanValue));
        linkedHashMap.put("results", linkedHashMap2);
        linkedHashMap.put("apiResponse", responseMap);
        addValueInLogMap("statementText", text);
        addValueInLogMap("speechToText", String.valueOf(responseMap.get("sttOutput")));
        addValueInLogMap("speechToTextMatching", linkedHashMap);
        return booleanValue;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x013b, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x014b, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x014f, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x015e, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0160, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x016b, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0171, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0174, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x017b, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "handleLogVideoStatementResult() called with: response = " + r18 + ", responseRaw = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0195, code lost:
    
        if (r4 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0197, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0199, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x014e, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void handleLogVideoStatementResult(Response response, String responseRaw) {
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
        String str4 = "handleLogVideoStatementResult() called with: response = " + response + ", responseRaw = " + responseRaw;
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
        this.videoStatementV2Data.setResponseBodyRaw$hyperkyc_release(responseRaw);
        this.videoStatementV2Data.setResponseHeaders$hyperkyc_release(response.headers().toMultimap());
        this.videoStatementV2Data.setResponseCode$hyperkyc_release(Integer.valueOf(response.code()));
        if (responseRaw == null) {
            this.isGenericFailure = true;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:1|(3:139|(1:141)(1:144)|(1:143))|7|(1:9)|10|(1:14)|15|(1:17)|18|(6:103|104|105|(1:107)|108|(6:110|(9:112|(3:130|(1:132)(1:135)|(1:134))|118|(1:120)|121|(1:125)|126|(1:128)|129)|21|22|23|(19:25|(3:92|(1:94)(1:98)|(1:96)(1:97))|31|(1:33)|34|(1:38)|39|(1:41)(1:91)|42|(1:44)(1:90)|45|46|47|48|(1:50)|51|(2:53|(14:55|(1:83)(1:59)|61|(1:63)(1:81)|(9:65|66|(1:68)|69|(1:73)|74|(1:76)(1:80)|(1:78)|79)|82|66|(0)|69|(2:71|73)|74|(0)(0)|(0)|79))|84|85)(1:99)))|20|21|22|23|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x01ce, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01cf, code lost:
    
        r2 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0300, code lost:
    
        if (r0 != null) goto L131;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:99:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void handleVideoUploadResult(String responseRaw) {
        String canonicalName;
        Object m1202constructorimpl;
        CharSequence charSequence;
        String str;
        String canonicalName2;
        String className;
        Throwable m1205exceptionOrNullimpl;
        String str2;
        String str3;
        Object m1202constructorimpl2;
        String str4;
        String str5;
        String str6;
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
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        String str7 = "";
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
        String str8 = "handleVideoUploadResult() called with: responseRaw = " + responseRaw;
        if (str8 == null) {
            str8 = "null ";
        }
        sb.append(str8);
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
                charSequence = "co.hyperverge";
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
                    String str9 = "handleVideoUploadResult() called with: responseRaw = " + responseRaw;
                    if (str9 == null) {
                        str9 = "null ";
                    }
                    sb2.append(str9);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                }
                Result.Companion companion4 = Result.INSTANCE;
                VideoStatementV2VM videoStatementV2VM = this;
                this.videoStatementV2Data.setVideoUrl$hyperkyc_release(new JSONArray(JSONExtsKt.extractJsonValue(responseRaw, "result.details.uploadUrls")).get(0).toString());
                Object m1202constructorimpl3 = Result.m1202constructorimpl(Unit.INSTANCE);
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                if (m1205exceptionOrNullimpl == null) {
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                    HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (str2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls3 = getClass();
                        String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                        str2 = canonicalName3 == null ? str : canonicalName3;
                    }
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher4.find()) {
                        str2 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb3.append(str2);
                    sb3.append(" - ");
                    sb3.append("handleVideoUploadResult: failed");
                    sb3.append(' ');
                    String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                    if (localizedMessage2 != null) {
                        str3 = '\n' + localizedMessage2;
                    } else {
                        str3 = "";
                    }
                    sb3.append(str3);
                    companion5.log(level2, sb3.toString());
                    CoreExtsKt.isRelease();
                    try {
                        Result.Companion companion6 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th2) {
                        Result.Companion companion7 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String packageName2 = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                str4 = null;
                            } else {
                                str4 = null;
                                str5 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            }
                            Class<?> cls4 = getClass();
                            str5 = cls4 != null ? cls4.getCanonicalName() : str4;
                            if (str5 == null) {
                                str6 = str;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                                if (matcher.find()) {
                                    str6 = matcher.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
                                }
                                if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str6 = str6.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append("handleVideoUploadResult: failed");
                                sb4.append(' ');
                                localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str4;
                                if (localizedMessage != null) {
                                    str7 = '\n' + localizedMessage;
                                }
                                sb4.append(str7);
                                Log.println(6, str6, sb4.toString());
                            }
                            str6 = str5;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                            if (matcher.find()) {
                            }
                            if (str6.length() > 23) {
                                str6 = str6.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb42 = new StringBuilder();
                            sb42.append("handleVideoUploadResult: failed");
                            sb42.append(' ');
                            if (m1205exceptionOrNullimpl == null) {
                            }
                            if (localizedMessage != null) {
                            }
                            sb42.append(str7);
                            Log.println(6, str6, sb42.toString());
                        }
                    }
                    this.isGenericFailure = true;
                    return;
                }
                return;
            }
        }
        charSequence = "co.hyperverge";
        str = "N/A";
        Result.Companion companion42 = Result.INSTANCE;
        VideoStatementV2VM videoStatementV2VM2 = this;
        this.videoStatementV2Data.setVideoUrl$hyperkyc_release(new JSONArray(JSONExtsKt.extractJsonValue(responseRaw, "result.details.uploadUrls")).get(0).toString());
        Object m1202constructorimpl32 = Result.m1202constructorimpl(Unit.INSTANCE);
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl32);
        if (m1205exceptionOrNullimpl == null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean asBoolean(String str, boolean z) {
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
        String str4 = "asBoolean() called with: default = " + z;
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
                    String str5 = "asBoolean() called with: default = " + z;
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
        if (Intrinsics.areEqual(str, "yes")) {
            return true;
        }
        if (Intrinsics.areEqual(str, "no")) {
            return false;
        }
        String str6 = str;
        if (str6 != null) {
            StringsKt.isBlank(str6);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String toBase64(String uri) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public final String formatSecondsToHHMMSS(long endTimestamp) {
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
        String str2 = "formatSecondsToHHMMSS() called with: endTimestamp = " + endTimestamp;
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
                    String str3 = "formatSecondsToHHMMSS() called with: endTimestamp = " + endTimestamp;
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
        long j = (endTimestamp - this.startTimestamp) / 1000;
        long j2 = 3600;
        long j3 = 60;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%02d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j / j2), Long.valueOf((j % j2) / j3), Long.valueOf(j % j3)}, 3));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }
}
