package co.hyperverge.hyperkyc.data.network;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import co.hyperverge.hyperkyc.HyperKyc;
import co.hyperverge.hyperkyc.core.hv.models.HSDefaultRemoteConfig;
import co.hyperverge.hyperkyc.core.hv.models.HSRemoteConfig;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
import co.hyperverge.hyperkyc.data.models.FinishReview;
import co.hyperverge.hyperkyc.data.models.KycCountry;
import co.hyperverge.hyperkyc.data.models.StartTransaction;
import co.hyperverge.hyperkyc.data.models.WorkflowConfig;
import co.hyperverge.hyperkyc.data.models.state.TransactionState;
import co.hyperverge.hyperkyc.data.models.state.TransactionStateRequest;
import co.hyperverge.hyperkyc.data.models.state.TransactionStateResponse;
import co.hyperverge.hyperkyc.ui.models.NetworkUIState;
import co.hyperverge.hyperkyc.utils.FormWebViewDriver;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoroutineExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Closeable;
import java.io.File;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import javax.net.SocketFactory;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;

/* compiled from: NetworkRepo.kt */
@Metadata(d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J9\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0011H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ1\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0011H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ-\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010 Ja\u0010!\u001a\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u00100\u00102\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0011H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010%JA\u0010&\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010'\u001a\u00020\u00112\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0011H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010)JA\u0010*\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010'\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0011H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010,Jr\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\u0006\u00100\u001a\u00020\u00112\u0006\u00101\u001a\u00020\u00112\u0014\b\u0002\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u00102\n\b\u0002\u00103\u001a\u0004\u0018\u0001042\b\b\u0002\u00105\u001a\u00020\u00042\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u00108\u001a\u00020\u0006H\u0080@ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b9\u0010:J/\u0010;\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0011H\u0080@ø\u0001\u0000¢\u0006\u0004\b<\u0010=J'\u0010>\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0011H\u0080@ø\u0001\u0000¢\u0006\u0004\b?\u0010=J#\u0010@\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0080@ø\u0001\u0000¢\u0006\u0004\bA\u0010BJW\u0010C\u001a\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u00100\u00102\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0011H\u0080@ø\u0001\u0000¢\u0006\u0004\bD\u0010EJ9\u0010F\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020I0Hj\u0002`J0G2\u0006\u0010K\u001a\u00020L2\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u0010H\u0000¢\u0006\u0002\bMJ7\u0010N\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010'\u001a\u00020\u00112\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0011H\u0080@ø\u0001\u0000¢\u0006\u0004\bO\u0010PJ7\u0010Q\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010'\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0011H\u0080@ø\u0001\u0000¢\u0006\u0004\bR\u0010SJ\\\u0010T\u001a\b\u0012\u0004\u0012\u00020/0.2\u0006\u00100\u001a\u00020\u00112\u0006\u0010U\u001a\u00020V2\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u00102\u0014\b\u0002\u0010W\u001a\u000e\u0012\u0004\u0012\u00020Y\u0012\u0004\u0012\u00020Z0XH\u0080@ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b[\u0010\\J#\u0010]\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0006\u0010\u0017\u001a\u00020\u0018H\u0080@ø\u0001\u0000¢\u0006\u0004\b^\u0010_J\u001d\u0010`\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0080@ø\u0001\u0000¢\u0006\u0004\ba\u0010_JC\u0010b\u001a\u00020Z2\u0006\u0010c\u001a\u00020d2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010'\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020\u00112\b\b\u0002\u0010e\u001a\u00020f2\b\b\u0002\u0010g\u001a\u00020fH\u0000¢\u0006\u0002\bhJ%\u0010i\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0080@ø\u0001\u0000¢\u0006\u0004\bj\u0010BJM\u0010k\u001a\u001c\u0012\u0004\u0012\u00020\u0011\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u0010\u0018\u00010\u00102\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0080@ø\u0001\u0000¢\u0006\u0004\bl\u0010mJ-\u0010n\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010'\u001a\u00020\u0011H\u0080@ø\u0001\u0000¢\u0006\u0004\bo\u0010pJ\u0018\u0010q\u001a\u00020Z2\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020fH\u0002J-\u0010r\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010'\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0080@ø\u0001\u0000¢\u0006\u0004\bs\u0010tJ9\u0010u\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020I0Hj\u0002`v0G2\u0006\u0010w\u001a\u00020x2\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u0010H\u0000¢\u0006\u0002\byJ\r\u0010z\u001a\u00020ZH\u0000¢\u0006\u0002\b{JG\u0010|\u001a\b\u0012\u0004\u0012\u00020/0.2\u0006\u00100\u001a\u00020\u00112\u0006\u0010}\u001a\u00020~2\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u0010H\u0080@ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000ø\u0001\u0000¢\u0006\u0005\b\u007f\u0010\u0080\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R0\u0010\u000f\u001a$\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u0011\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u0010\u0018\u00010\u0010\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0012\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0014\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u0081\u0001"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/NetworkRepo;", "", "()V", "DEBUG_DELAY_MILLIS", "", "PREFETCH_UI_CONFIG_CACHE_EXPIRY_IN_SECONDS", "", "prefetchCountriesDeferred", "Lkotlinx/coroutines/Deferred;", "", "Lco/hyperverge/hyperkyc/data/models/KycCountry;", "prefetchDefaultRemoteConfigDeferred", "Lco/hyperverge/hyperkyc/core/hv/models/HSDefaultRemoteConfig;", "prefetchRemoteConfigDeferred", "Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig;", "prefetchTextConfigDeferred", "", "", "prefetchUIConfigDeferred", "Lco/hyperverge/hyperkyc/core/hv/models/HSUIConfig;", "prefetchWorkflowConfigDeferred", "Lco/hyperverge/hyperkyc/data/models/WorkflowConfig;", "callCountriesFetchAPI", "cacheDir", "Ljava/io/File;", "cacheExpiryInSeconds", "countriesJson", "(Ljava/io/File;Ljava/lang/Integer;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callDefaultRemoteConfigFetchAPI", "defaultRemoteConfigJson", "callRemoteConfigFetchAPI", "appId", "(Ljava/lang/String;Ljava/io/File;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callTextConfigFetchAPI", "languageCode", "source", "textConfigJson", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/io/File;Ljava/lang/Integer;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callUIConfigFetchAPI", "workflowId", "uiColorConfigJson", "(Ljava/lang/String;Ljava/io/File;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callWorkflowConfigFetchAPI", "workflowConfigJson", "(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;Ljava/lang/Integer;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "customApiCall", "Lkotlin/Result;", "Lokhttp3/Response;", "url", "method", "headers", "body", "Lokhttp3/RequestBody;", "timeoutSeconds", "socketFactory", "Ljavax/net/SocketFactory;", "maxRetries", "customApiCall-eH_QyT8$hyperkyc_release", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lokhttp3/RequestBody;JLjavax/net/SocketFactory;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchCountries", "fetchCountries$hyperkyc_release", "(Ljava/io/File;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchDefaultRemoteConfig", "fetchDefaultRemoteConfig$hyperkyc_release", "fetchRemoteConfig", "fetchRemoteConfig$hyperkyc_release", "(Ljava/lang/String;Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchTextConfig", "fetchTextConfig$hyperkyc_release", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/io/File;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchTransactionState", "Lkotlinx/coroutines/flow/Flow;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "Lco/hyperverge/hyperkyc/data/models/state/TransactionStateResponse;", "Lco/hyperverge/hyperkyc/ui/custom/FetchStateUIState;", "tStateRequest", "Lco/hyperverge/hyperkyc/data/models/state/TransactionStateRequest;", "fetchTransactionState$hyperkyc_release", "fetchUIColorConfig", "fetchUIColorConfig$hyperkyc_release", "(Ljava/lang/String;Ljava/io/File;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchWorkflowConfig", "fetchWorkflowConfig$hyperkyc_release", "(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performReviewFinish", "finishReview", "Lco/hyperverge/hyperkyc/data/models/FinishReview;", "onFailure", "Lkotlin/Function1;", "", "", "performReviewFinish-yxL6bBk$hyperkyc_release", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/FinishReview;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prefetchCountries", "prefetchCountries$hyperkyc_release", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prefetchDefaultRemoteConfig", "prefetchDefaultRemoteConfig$hyperkyc_release", "prefetchInternal", "context", "Landroid/content/Context;", "shouldPreLoadWebSDK", "", "preLoadAll", "prefetchInternal$hyperkyc_release", "prefetchRemoteConfig", "prefetchRemoteConfig$hyperkyc_release", "prefetchTextConfig", "prefetchTextConfig$hyperkyc_release", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prefetchUIColorConfig", "prefetchUIColorConfig$hyperkyc_release", "(Ljava/lang/String;Ljava/io/File;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prefetchWebSDK", "prefetchWorkflowConfig", "prefetchWorkflowConfig$hyperkyc_release", "(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pushTransactionState", "Lco/hyperverge/hyperkyc/ui/custom/SaveStateUIState;", "transactionState", "Lco/hyperverge/hyperkyc/data/models/state/TransactionState;", "pushTransactionState$hyperkyc_release", "resetPrefetchDataAndRawConfigJSONs", "resetPrefetchDataAndRawConfigJSONs$hyperkyc_release", "startTransaction", "startTransactionData", "Lco/hyperverge/hyperkyc/data/models/StartTransaction;", "startTransaction-BWLJW6A$hyperkyc_release", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/StartTransaction;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NetworkRepo {
    private static final long DEBUG_DELAY_MILLIS = 2000;
    public static final NetworkRepo INSTANCE = new NetworkRepo();
    private static final int PREFETCH_UI_CONFIG_CACHE_EXPIRY_IN_SECONDS = 3600;
    private static Deferred<? extends List<KycCountry>> prefetchCountriesDeferred;
    private static Deferred<HSDefaultRemoteConfig> prefetchDefaultRemoteConfigDeferred;
    private static Deferred<HSRemoteConfig> prefetchRemoteConfigDeferred;
    private static Deferred<? extends Map<String, ? extends Map<String, ? extends Object>>> prefetchTextConfigDeferred;
    private static Deferred<HSUIConfig> prefetchUIConfigDeferred;
    private static Deferred<WorkflowConfig> prefetchWorkflowConfigDeferred;

    private NetworkRepo() {
    }

    public static /* synthetic */ void prefetchInternal$hyperkyc_release$default(NetworkRepo networkRepo, Context context, String str, String str2, String str3, boolean z, boolean z2, int i, Object obj) {
        if ((i & 8) != 0) {
            str3 = "en";
        }
        String str4 = str3;
        if ((i & 16) != 0) {
            z = true;
        }
        boolean z3 = z;
        if ((i & 32) != 0) {
            z2 = false;
        }
        networkRepo.prefetchInternal$hyperkyc_release(context, str, str2, str4, z3, z2);
    }

    public static /* synthetic */ Object fetchCountries$hyperkyc_release$default(NetworkRepo networkRepo, File file, String str, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return networkRepo.fetchCountries$hyperkyc_release(file, str, continuation);
    }

    public final Object fetchCountries$hyperkyc_release(File file, String str, Continuation<? super List<KycCountry>> continuation) {
        return CoroutineExtsKt.onIO$default(null, new NetworkRepo$fetchCountries$2(file, str, null), continuation, 1, null);
    }

    public final Object prefetchCountries$hyperkyc_release(File file, Continuation<? super List<KycCountry>> continuation) {
        return CoroutineExtsKt.onIO$default(null, new NetworkRepo$prefetchCountries$2(file, null), continuation, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01b7, code lost:
    
        if (r2 != null) goto L72;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x02b9 A[Catch: all -> 0x02c2, TRY_LEAVE, TryCatch #0 {all -> 0x02c2, blocks: (B:16:0x02b0, B:18:0x02b9), top: B:15:0x02b0 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0309  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x02c8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x024b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x02a1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0033  */
    /* JADX WARN: Type inference failed for: r14v10, types: [T] */
    /* JADX WARN: Type inference failed for: r14v15, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v16 */
    /* JADX WARN: Type inference failed for: r14v6 */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r2v29, types: [T] */
    /* JADX WARN: Type inference failed for: r2v39, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v41 */
    /* JADX WARN: Type inference failed for: r2v42 */
    /* JADX WARN: Type inference failed for: r2v56, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v64 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object callCountriesFetchAPI(File file, Integer num, String str, Continuation<? super List<KycCountry>> continuation) {
        NetworkRepo$callCountriesFetchAPI$1 networkRepo$callCountriesFetchAPI$1;
        int i;
        ?? canonicalName;
        String str2;
        Object m1202constructorimpl;
        Object obj;
        String str3;
        String str4;
        String str5;
        String className;
        Object obj2;
        Object apiGet$default;
        String className2;
        List mutableList;
        Closeable closeable;
        ?? r1;
        String str6;
        String str7 = str;
        if (continuation instanceof NetworkRepo$callCountriesFetchAPI$1) {
            networkRepo$callCountriesFetchAPI$1 = (NetworkRepo$callCountriesFetchAPI$1) continuation;
            if ((networkRepo$callCountriesFetchAPI$1.label & Integer.MIN_VALUE) != 0) {
                networkRepo$callCountriesFetchAPI$1.label -= Integer.MIN_VALUE;
                NetworkRepo$callCountriesFetchAPI$1 networkRepo$callCountriesFetchAPI$12 = networkRepo$callCountriesFetchAPI$1;
                Object obj3 = networkRepo$callCountriesFetchAPI$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = networkRepo$callCountriesFetchAPI$12.label;
                if (i == 0) {
                    if (i == 1) {
                        str7 = (String) networkRepo$callCountriesFetchAPI$12.L$0;
                        ResultKt.throwOnFailure(obj3);
                        str3 = null;
                        KycCountry[] kycCountryArr = (KycCountry[]) new Gson().fromJson(new JSONArray(str7).toString(), KycCountry[].class);
                        if (kycCountryArr != null) {
                        }
                    }
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj3);
                    apiGet$default = ((Result) obj3).getValue();
                    str3 = null;
                    Object obj4 = !Result.m1208isFailureimpl(apiGet$default) ? str3 : apiGet$default;
                    Intrinsics.checkNotNull(obj4);
                    closeable = (Closeable) obj4;
                    try {
                        ResponseBody body = ((Response) closeable).body();
                        str6 = body == null ? body.string() : str3;
                        r1 = str3;
                    } catch (Throwable th) {
                        r1 = th;
                        str6 = str3;
                    }
                    if (closeable != null) {
                        try {
                            closeable.close();
                        } catch (Throwable th2) {
                            if (r1 == 0) {
                                r1 = th2;
                            } else {
                                ExceptionsKt.addSuppressed(r1, th2);
                            }
                        }
                    }
                    if (r1 == 0) {
                        throw r1;
                    }
                    Intrinsics.checkNotNull(str6);
                    str7 = str6;
                    KycCountry[] kycCountryArr2 = (KycCountry[]) new Gson().fromJson(new JSONArray(str7).toString(), KycCountry[].class);
                    return (kycCountryArr2 != null || (mutableList = ArraysKt.toMutableList(kycCountryArr2)) == null) ? str3 : CollectionsKt.sortedWith(mutableList, new Comparator() { // from class: co.hyperverge.hyperkyc.data.network.NetworkRepo$callCountriesFetchAPI$$inlined$sortedBy$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt.compareValues(((KycCountry) t).getName(), ((KycCountry) t2).getName());
                        }
                    });
                }
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
                    str2 = (String) objectRef.element;
                } else {
                    str2 = ((String) objectRef.element).substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb.append(str2);
                sb.append(" - ");
                String str8 = "callCountriesFetchAPI() called with: cacheDir = " + file + ", cacheExpiryInSeconds = " + num + ", countriesJson = " + str7;
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
                    } catch (Throwable th3) {
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th3));
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
                                str3 = null;
                            } else {
                                str3 = null;
                                String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                str4 = substringAfterLast$default;
                            }
                            Class<?> cls2 = getClass();
                            String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : str3;
                            str4 = canonicalName2 == null ? "N/A" : canonicalName2;
                            objectRef2.element = str4;
                            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                            if (matcher2.find()) {
                                ?? replaceAll2 = matcher2.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                objectRef2.element = replaceAll2;
                            }
                            if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                str5 = (String) objectRef2.element;
                            } else {
                                str5 = ((String) objectRef2.element).substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb2 = new StringBuilder();
                            String str9 = "callCountriesFetchAPI() called with: cacheDir = " + file + ", cacheExpiryInSeconds = " + num + ", countriesJson = " + str7;
                            if (str9 == null) {
                                str9 = "null ";
                            }
                            sb2.append(str9);
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(3, str5, sb2.toString());
                            if (!CoreExtsKt.isDebug() && str7 != null) {
                                networkRepo$callCountriesFetchAPI$12.L$0 = str7;
                                networkRepo$callCountriesFetchAPI$12.label = 1;
                                Object obj5 = obj;
                                if (DelayKt.delay(2000L, networkRepo$callCountriesFetchAPI$12) == obj5) {
                                    return obj5;
                                }
                                KycCountry[] kycCountryArr22 = (KycCountry[]) new Gson().fromJson(new JSONArray(str7).toString(), KycCountry[].class);
                                if (kycCountryArr22 != null) {
                                }
                            }
                            obj2 = obj;
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("countries");
                            sb3.append(CoreExtsKt.isDebug() ? "_debug" : "");
                            sb3.append(".json");
                            String str10 = "https://s3-ap-south-1.amazonaws.com/hv-central-config/gsdk-country-doc-list/" + sb3.toString();
                            networkRepo$callCountriesFetchAPI$12.label = 2;
                            apiGet$default = NetworkHelpersKt.apiGet$default(str10, null, null, file, num, null, networkRepo$callCountriesFetchAPI$12, 38, null);
                            if (apiGet$default == obj2) {
                                return obj2;
                            }
                            if (!Result.m1208isFailureimpl(apiGet$default)) {
                            }
                            Intrinsics.checkNotNull(obj4);
                            closeable = (Closeable) obj4;
                            ResponseBody body2 = ((Response) closeable).body();
                            str6 = body2 == null ? body2.string() : str3;
                            r1 = str3;
                            if (closeable != null) {
                            }
                            if (r1 == 0) {
                            }
                        }
                        str3 = null;
                        if (!CoreExtsKt.isDebug()) {
                        }
                        obj2 = obj;
                        StringBuilder sb32 = new StringBuilder();
                        sb32.append("countries");
                        sb32.append(CoreExtsKt.isDebug() ? "_debug" : "");
                        sb32.append(".json");
                        String str102 = "https://s3-ap-south-1.amazonaws.com/hv-central-config/gsdk-country-doc-list/" + sb32.toString();
                        networkRepo$callCountriesFetchAPI$12.label = 2;
                        apiGet$default = NetworkHelpersKt.apiGet$default(str102, null, null, file, num, null, networkRepo$callCountriesFetchAPI$12, 38, null);
                        if (apiGet$default == obj2) {
                        }
                        if (!Result.m1208isFailureimpl(apiGet$default)) {
                        }
                        Intrinsics.checkNotNull(obj4);
                        closeable = (Closeable) obj4;
                        ResponseBody body22 = ((Response) closeable).body();
                        str6 = body22 == null ? body22.string() : str3;
                        r1 = str3;
                        if (closeable != null) {
                        }
                        if (r1 == 0) {
                        }
                    }
                }
                obj = coroutine_suspended;
                str3 = null;
                if (!CoreExtsKt.isDebug()) {
                }
                obj2 = obj;
                StringBuilder sb322 = new StringBuilder();
                sb322.append("countries");
                sb322.append(CoreExtsKt.isDebug() ? "_debug" : "");
                sb322.append(".json");
                String str1022 = "https://s3-ap-south-1.amazonaws.com/hv-central-config/gsdk-country-doc-list/" + sb322.toString();
                networkRepo$callCountriesFetchAPI$12.label = 2;
                apiGet$default = NetworkHelpersKt.apiGet$default(str1022, null, null, file, num, null, networkRepo$callCountriesFetchAPI$12, 38, null);
                if (apiGet$default == obj2) {
                }
                if (!Result.m1208isFailureimpl(apiGet$default)) {
                }
                Intrinsics.checkNotNull(obj4);
                closeable = (Closeable) obj4;
                ResponseBody body222 = ((Response) closeable).body();
                str6 = body222 == null ? body222.string() : str3;
                r1 = str3;
                if (closeable != null) {
                }
                if (r1 == 0) {
                }
            }
        }
        networkRepo$callCountriesFetchAPI$1 = new NetworkRepo$callCountriesFetchAPI$1(this, continuation);
        NetworkRepo$callCountriesFetchAPI$1 networkRepo$callCountriesFetchAPI$122 = networkRepo$callCountriesFetchAPI$1;
        Object obj32 = networkRepo$callCountriesFetchAPI$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = networkRepo$callCountriesFetchAPI$122.label;
        if (i == 0) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object callCountriesFetchAPI$default(NetworkRepo networkRepo, File file, Integer num, String str, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        if ((i & 4) != 0) {
            str = null;
        }
        return networkRepo.callCountriesFetchAPI(file, num, str, continuation);
    }

    public final Object fetchRemoteConfig$hyperkyc_release(String str, File file, Continuation<? super HSRemoteConfig> continuation) {
        return CoroutineExtsKt.onIO$default(null, new NetworkRepo$fetchRemoteConfig$2(str, file, null), continuation, 1, null);
    }

    public final Object prefetchRemoteConfig$hyperkyc_release(String str, File file, Continuation<? super HSRemoteConfig> continuation) {
        return CoroutineExtsKt.onIO$default(null, new NetworkRepo$prefetchRemoteConfig$2(str, file, null), continuation, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(15:1|(2:3|(13:5|6|(1:(1:9)(2:39|40))(13:41|(3:104|(1:106)(1:109)|(1:108))|47|(1:49)|50|(1:103)(1:54)|55|(1:57)|58|(6:64|65|66|(1:68)|69|(2:71|(15:73|(1:99)(2:77|(10:79|80|(1:82)|83|(1:92)(1:87)|88|(1:90)|91|61|(1:63)))|93|(1:95)(1:98)|(1:97)|80|(0)|83|(1:85)|92|88|(0)|91|61|(0))))|60|61|(0))|10|(1:12)(1:38)|13|14|15|(1:17)(1:35)|18|19|(2:26|27)|(2:22|23)(1:25)))|110|6|(0)(0)|10|(0)(0)|13|14|15|(0)(0)|18|19|(0)|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0278, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0279, code lost:
    
        r1 = r12;
        r12 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0268 A[Catch: all -> 0x0278, TryCatch #2 {all -> 0x0278, blocks: (B:15:0x025a, B:17:0x0268, B:18:0x026e), top: B:14:0x025a }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x027e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x024b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x020d  */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v11, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v13, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r12v16 */
    /* JADX WARN: Type inference failed for: r14v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2, types: [T] */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r2v39, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v52, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v10, types: [T] */
    /* JADX WARN: Type inference failed for: r9v16, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object callRemoteConfigFetchAPI(String str, File file, Integer num, Continuation<? super HSRemoteConfig> continuation) {
        NetworkRepo$callRemoteConfigFetchAPI$1 networkRepo$callRemoteConfigFetchAPI$1;
        int i;
        ?? canonicalName;
        String str2;
        Object m1202constructorimpl;
        String str3;
        Matcher matcher;
        String str4;
        String str5;
        String className;
        String str6;
        Object apiGet$default;
        String className2;
        Closeable closeable;
        ?? r12;
        if (continuation instanceof NetworkRepo$callRemoteConfigFetchAPI$1) {
            networkRepo$callRemoteConfigFetchAPI$1 = (NetworkRepo$callRemoteConfigFetchAPI$1) continuation;
            if ((networkRepo$callRemoteConfigFetchAPI$1.label & Integer.MIN_VALUE) != 0) {
                networkRepo$callRemoteConfigFetchAPI$1.label -= Integer.MIN_VALUE;
                NetworkRepo$callRemoteConfigFetchAPI$1 networkRepo$callRemoteConfigFetchAPI$12 = networkRepo$callRemoteConfigFetchAPI$1;
                Object obj = networkRepo$callRemoteConfigFetchAPI$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = networkRepo$callRemoteConfigFetchAPI$12.label;
                String str7 = null;
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
                        str2 = (String) objectRef.element;
                    } else {
                        str2 = ((String) objectRef.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(str2);
                    sb.append(" - ");
                    String str8 = "callRemoteConfigFetchAPI() called with: appId = " + str + ", cacheDir = " + file;
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
                            if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                                    str3 = null;
                                } else {
                                    str3 = null;
                                    str3 = null;
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
                                            str4 = (String) objectRef2.element;
                                        } else {
                                            str4 = ((String) objectRef2.element).substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb2 = new StringBuilder();
                                        str5 = "callRemoteConfigFetchAPI() called with: appId = " + str + ", cacheDir = " + file;
                                        if (str5 == null) {
                                            str5 = "null ";
                                        }
                                        sb2.append(str5);
                                        sb2.append(' ');
                                        sb2.append("");
                                        Log.println(3, str4, sb2.toString());
                                        str6 = str3;
                                        networkRepo$callRemoteConfigFetchAPI$12.label = 1;
                                        apiGet$default = NetworkHelpersKt.apiGet$default("https://s3-ap-south-1.amazonaws.com/hv-central-config/sdk-client-config/hypersnapsdk/v1/" + str + ".json", null, null, file, num, null, networkRepo$callRemoteConfigFetchAPI$12, 38, null);
                                        str7 = str6;
                                        if (apiGet$default == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    }
                                }
                                Class<?> cls2 = getClass();
                                String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : str3;
                                if (canonicalName2 != null) {
                                    r14 = canonicalName2;
                                }
                                objectRef2.element = r14;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                if (matcher.find()) {
                                }
                                if (((String) objectRef2.element).length() > 23) {
                                }
                                str4 = (String) objectRef2.element;
                                StringBuilder sb22 = new StringBuilder();
                                str5 = "callRemoteConfigFetchAPI() called with: appId = " + str + ", cacheDir = " + file;
                                if (str5 == null) {
                                }
                                sb22.append(str5);
                                sb22.append(' ');
                                sb22.append("");
                                Log.println(3, str4, sb22.toString());
                                str6 = str3;
                                networkRepo$callRemoteConfigFetchAPI$12.label = 1;
                                apiGet$default = NetworkHelpersKt.apiGet$default("https://s3-ap-south-1.amazonaws.com/hv-central-config/sdk-client-config/hypersnapsdk/v1/" + str + ".json", null, null, file, num, null, networkRepo$callRemoteConfigFetchAPI$12, 38, null);
                                str7 = str6;
                                if (apiGet$default == coroutine_suspended) {
                                }
                            }
                        }
                    }
                    str6 = null;
                    networkRepo$callRemoteConfigFetchAPI$12.label = 1;
                    apiGet$default = NetworkHelpersKt.apiGet$default("https://s3-ap-south-1.amazonaws.com/hv-central-config/sdk-client-config/hypersnapsdk/v1/" + str + ".json", null, null, file, num, null, networkRepo$callRemoteConfigFetchAPI$12, 38, null);
                    str7 = str6;
                    if (apiGet$default == coroutine_suspended) {
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    apiGet$default = ((Result) obj).getValue();
                }
                Object obj2 = !Result.m1208isFailureimpl(apiGet$default) ? str7 : apiGet$default;
                Intrinsics.checkNotNull(obj2);
                closeable = (Closeable) obj2;
                Gson gson = new Gson();
                ResponseBody body = ((Response) closeable).body();
                Object obj3 = (HSRemoteConfig) gson.fromJson(body == null ? body.string() : str7, HSRemoteConfig.class);
                r12 = str7;
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (Throwable th2) {
                        if (r12 == 0) {
                            r12 = th2;
                        } else {
                            ExceptionsKt.addSuppressed(r12, th2);
                        }
                    }
                }
                if (r12 == 0) {
                    throw r12;
                }
                Intrinsics.checkNotNull(obj3);
                Intrinsics.checkNotNullExpressionValue(obj3, "apiGet(\n            \"htt…a\n            )\n        }");
                return obj3;
            }
        }
        networkRepo$callRemoteConfigFetchAPI$1 = new NetworkRepo$callRemoteConfigFetchAPI$1(this, continuation);
        NetworkRepo$callRemoteConfigFetchAPI$1 networkRepo$callRemoteConfigFetchAPI$122 = networkRepo$callRemoteConfigFetchAPI$1;
        Object obj4 = networkRepo$callRemoteConfigFetchAPI$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = networkRepo$callRemoteConfigFetchAPI$122.label;
        String str72 = null;
        if (i != 0) {
        }
        if (!Result.m1208isFailureimpl(apiGet$default)) {
        }
        Intrinsics.checkNotNull(obj2);
        closeable = (Closeable) obj2;
        Gson gson2 = new Gson();
        ResponseBody body2 = ((Response) closeable).body();
        Object obj32 = (HSRemoteConfig) gson2.fromJson(body2 == null ? body2.string() : str72, HSRemoteConfig.class);
        r12 = str72;
        if (closeable != null) {
        }
        if (r12 == 0) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object callRemoteConfigFetchAPI$default(NetworkRepo networkRepo, String str, File file, Integer num, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            num = null;
        }
        return networkRepo.callRemoteConfigFetchAPI(str, file, num, continuation);
    }

    public static /* synthetic */ Object fetchDefaultRemoteConfig$hyperkyc_release$default(NetworkRepo networkRepo, File file, String str, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return networkRepo.fetchDefaultRemoteConfig$hyperkyc_release(file, str, continuation);
    }

    public final Object fetchDefaultRemoteConfig$hyperkyc_release(File file, String str, Continuation<? super HSDefaultRemoteConfig> continuation) {
        return CoroutineExtsKt.onIO$default(null, new NetworkRepo$fetchDefaultRemoteConfig$2(file, str, null), continuation, 1, null);
    }

    public final Object prefetchDefaultRemoteConfig$hyperkyc_release(File file, Continuation<? super HSDefaultRemoteConfig> continuation) {
        return CoroutineExtsKt.onIO$default(null, new NetworkRepo$prefetchDefaultRemoteConfig$2(file, null), continuation, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x026e A[Catch: all -> 0x0276, TRY_LEAVE, TryCatch #2 {all -> 0x0276, blocks: (B:16:0x0265, B:18:0x026e), top: B:15:0x0265 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x027c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0224 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0256 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x020a  */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v11, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v13, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r12v16 */
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
    /* JADX WARN: Type inference failed for: r2v39, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v53, types: [T, java.lang.Object, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object callDefaultRemoteConfigFetchAPI(File file, Integer num, String str, Continuation<? super HSDefaultRemoteConfig> continuation) {
        NetworkRepo$callDefaultRemoteConfigFetchAPI$1 networkRepo$callDefaultRemoteConfigFetchAPI$1;
        int i;
        ?? canonicalName;
        String str2;
        Object m1202constructorimpl;
        String str3;
        Matcher matcher;
        String str4;
        String className;
        String str5;
        Object apiGet$default;
        String className2;
        Closeable closeable;
        ?? r12;
        String str6 = str;
        if (continuation instanceof NetworkRepo$callDefaultRemoteConfigFetchAPI$1) {
            networkRepo$callDefaultRemoteConfigFetchAPI$1 = (NetworkRepo$callDefaultRemoteConfigFetchAPI$1) continuation;
            if ((networkRepo$callDefaultRemoteConfigFetchAPI$1.label & Integer.MIN_VALUE) != 0) {
                networkRepo$callDefaultRemoteConfigFetchAPI$1.label -= Integer.MIN_VALUE;
                NetworkRepo$callDefaultRemoteConfigFetchAPI$1 networkRepo$callDefaultRemoteConfigFetchAPI$12 = networkRepo$callDefaultRemoteConfigFetchAPI$1;
                Object obj = networkRepo$callDefaultRemoteConfigFetchAPI$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = networkRepo$callDefaultRemoteConfigFetchAPI$12.label;
                String str7 = null;
                if (i == 0) {
                    if (i == 1) {
                        str6 = (String) networkRepo$callDefaultRemoteConfigFetchAPI$12.L$0;
                        ResultKt.throwOnFailure(obj);
                        Object fromJson = new Gson().fromJson(str6, (Class<Object>) HSDefaultRemoteConfig.class);
                        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(body, HS…RemoteConfig::class.java)");
                        return fromJson;
                    }
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    apiGet$default = ((Result) obj).getValue();
                    Object obj2 = !Result.m1208isFailureimpl(apiGet$default) ? str7 : apiGet$default;
                    Intrinsics.checkNotNull(obj2);
                    closeable = (Closeable) obj2;
                    try {
                        ResponseBody body = ((Response) closeable).body();
                        str6 = body == null ? body.string() : str7;
                        r12 = str7;
                    } catch (Throwable th) {
                        str6 = str7;
                        r12 = th;
                    }
                    if (closeable != null) {
                        try {
                            closeable.close();
                        } catch (Throwable th2) {
                            if (r12 == 0) {
                                r12 = th2;
                            } else {
                                ExceptionsKt.addSuppressed(r12, th2);
                            }
                        }
                    }
                    if (r12 == 0) {
                        throw r12;
                    }
                    Intrinsics.checkNotNull(str6);
                    Object fromJson2 = new Gson().fromJson(str6, (Class<Object>) HSDefaultRemoteConfig.class);
                    Intrinsics.checkNotNullExpressionValue(fromJson2, "Gson().fromJson(body, HS…RemoteConfig::class.java)");
                    return fromJson2;
                }
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
                    str2 = (String) objectRef.element;
                } else {
                    str2 = ((String) objectRef.element).substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb.append(str2);
                sb.append(" - ");
                String str8 = "callDefaultRemoteConfigFetchAPI() called with cacheDir = " + file;
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
                    } catch (Throwable th3) {
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th3));
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
                                str3 = null;
                            } else {
                                str3 = null;
                                str3 = null;
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
                                        str4 = (String) objectRef2.element;
                                    } else {
                                        str4 = ((String) objectRef2.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb2 = new StringBuilder();
                                    String str9 = "callDefaultRemoteConfigFetchAPI() called with cacheDir = " + file;
                                    sb2.append(str9 != null ? str9 : "null ");
                                    sb2.append(' ');
                                    sb2.append("");
                                    Log.println(3, str4, sb2.toString());
                                    str5 = str3;
                                    if (!CoreExtsKt.isDebug() && str6 != null) {
                                        networkRepo$callDefaultRemoteConfigFetchAPI$12.L$0 = str6;
                                        networkRepo$callDefaultRemoteConfigFetchAPI$12.label = 1;
                                        if (DelayKt.delay(2000L, networkRepo$callDefaultRemoteConfigFetchAPI$12) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        Object fromJson22 = new Gson().fromJson(str6, (Class<Object>) HSDefaultRemoteConfig.class);
                                        Intrinsics.checkNotNullExpressionValue(fromJson22, "Gson().fromJson(body, HS…RemoteConfig::class.java)");
                                        return fromJson22;
                                    }
                                    networkRepo$callDefaultRemoteConfigFetchAPI$12.label = 2;
                                    apiGet$default = NetworkHelpersKt.apiGet$default("https://hv-central-config.s3.ap-south-1.amazonaws.com/sdk-client-config/hypersnapsdk/v1/default.json", null, null, file, num, null, networkRepo$callDefaultRemoteConfigFetchAPI$12, 38, null);
                                    str7 = str5;
                                    if (apiGet$default == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    if (!Result.m1208isFailureimpl(apiGet$default)) {
                                    }
                                    Intrinsics.checkNotNull(obj2);
                                    closeable = (Closeable) obj2;
                                    ResponseBody body2 = ((Response) closeable).body();
                                    str6 = body2 == null ? body2.string() : str7;
                                    r12 = str7;
                                    if (closeable != null) {
                                    }
                                    if (r12 == 0) {
                                    }
                                }
                            }
                            Class<?> cls2 = getClass();
                            String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : str3;
                            if (canonicalName2 != null) {
                                r15 = canonicalName2;
                            }
                            objectRef2.element = r15;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                            if (matcher.find()) {
                            }
                            if (((String) objectRef2.element).length() > 23) {
                            }
                            str4 = (String) objectRef2.element;
                            StringBuilder sb22 = new StringBuilder();
                            String str92 = "callDefaultRemoteConfigFetchAPI() called with cacheDir = " + file;
                            sb22.append(str92 != null ? str92 : "null ");
                            sb22.append(' ');
                            sb22.append("");
                            Log.println(3, str4, sb22.toString());
                            str5 = str3;
                            if (!CoreExtsKt.isDebug()) {
                            }
                            networkRepo$callDefaultRemoteConfigFetchAPI$12.label = 2;
                            apiGet$default = NetworkHelpersKt.apiGet$default("https://hv-central-config.s3.ap-south-1.amazonaws.com/sdk-client-config/hypersnapsdk/v1/default.json", null, null, file, num, null, networkRepo$callDefaultRemoteConfigFetchAPI$12, 38, null);
                            str7 = str5;
                            if (apiGet$default == coroutine_suspended) {
                            }
                            if (!Result.m1208isFailureimpl(apiGet$default)) {
                            }
                            Intrinsics.checkNotNull(obj2);
                            closeable = (Closeable) obj2;
                            ResponseBody body22 = ((Response) closeable).body();
                            str6 = body22 == null ? body22.string() : str7;
                            r12 = str7;
                            if (closeable != null) {
                            }
                            if (r12 == 0) {
                            }
                        }
                    }
                }
                str5 = null;
                if (!CoreExtsKt.isDebug()) {
                }
                networkRepo$callDefaultRemoteConfigFetchAPI$12.label = 2;
                apiGet$default = NetworkHelpersKt.apiGet$default("https://hv-central-config.s3.ap-south-1.amazonaws.com/sdk-client-config/hypersnapsdk/v1/default.json", null, null, file, num, null, networkRepo$callDefaultRemoteConfigFetchAPI$12, 38, null);
                str7 = str5;
                if (apiGet$default == coroutine_suspended) {
                }
                if (!Result.m1208isFailureimpl(apiGet$default)) {
                }
                Intrinsics.checkNotNull(obj2);
                closeable = (Closeable) obj2;
                ResponseBody body222 = ((Response) closeable).body();
                str6 = body222 == null ? body222.string() : str7;
                r12 = str7;
                if (closeable != null) {
                }
                if (r12 == 0) {
                }
            }
        }
        networkRepo$callDefaultRemoteConfigFetchAPI$1 = new NetworkRepo$callDefaultRemoteConfigFetchAPI$1(this, continuation);
        NetworkRepo$callDefaultRemoteConfigFetchAPI$1 networkRepo$callDefaultRemoteConfigFetchAPI$122 = networkRepo$callDefaultRemoteConfigFetchAPI$1;
        Object obj3 = networkRepo$callDefaultRemoteConfigFetchAPI$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = networkRepo$callDefaultRemoteConfigFetchAPI$122.label;
        String str72 = null;
        if (i == 0) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object callDefaultRemoteConfigFetchAPI$default(NetworkRepo networkRepo, File file, Integer num, String str, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        if ((i & 4) != 0) {
            str = null;
        }
        return networkRepo.callDefaultRemoteConfigFetchAPI(file, num, str, continuation);
    }

    public static /* synthetic */ Object fetchWorkflowConfig$hyperkyc_release$default(NetworkRepo networkRepo, String str, String str2, File file, String str3, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            str3 = null;
        }
        return networkRepo.fetchWorkflowConfig$hyperkyc_release(str, str2, file, str3, continuation);
    }

    public final Object fetchWorkflowConfig$hyperkyc_release(String str, String str2, File file, String str3, Continuation<? super WorkflowConfig> continuation) {
        return CoroutineExtsKt.onIO$default(null, new NetworkRepo$fetchWorkflowConfig$2(str, str2, file, str3, null), continuation, 1, null);
    }

    public final Object prefetchWorkflowConfig$hyperkyc_release(String str, String str2, File file, Continuation<? super WorkflowConfig> continuation) {
        return CoroutineExtsKt.onIO$default(null, new NetworkRepo$prefetchWorkflowConfig$2(str, str2, file, null), continuation, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x02e6 A[Catch: all -> 0x02ee, TRY_LEAVE, TryCatch #2 {all -> 0x02ee, blocks: (B:13:0x02dd, B:15:0x02e6), top: B:12:0x02dd }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x02f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0263 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x02d6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0243  */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r15v10, types: [T] */
    /* JADX WARN: Type inference failed for: r15v15, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r15v16 */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v7 */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r3v17, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v47, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v7, types: [T] */
    /* JADX WARN: Type inference failed for: r9v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object callWorkflowConfigFetchAPI(String str, String str2, File file, Integer num, String str3, Continuation<? super WorkflowConfig> continuation) {
        NetworkRepo$callWorkflowConfigFetchAPI$1 networkRepo$callWorkflowConfigFetchAPI$1;
        int i;
        ?? canonicalName;
        String str4;
        Object m1202constructorimpl;
        String str5;
        String str6;
        ?? canonicalName2;
        Matcher matcher;
        String str7;
        String sb;
        String className;
        Object apiGet$default;
        String str8;
        String className2;
        Closeable closeable;
        ?? r12;
        String str9;
        if (continuation instanceof NetworkRepo$callWorkflowConfigFetchAPI$1) {
            networkRepo$callWorkflowConfigFetchAPI$1 = (NetworkRepo$callWorkflowConfigFetchAPI$1) continuation;
            if ((networkRepo$callWorkflowConfigFetchAPI$1.label & Integer.MIN_VALUE) != 0) {
                networkRepo$callWorkflowConfigFetchAPI$1.label -= Integer.MIN_VALUE;
                NetworkRepo$callWorkflowConfigFetchAPI$1 networkRepo$callWorkflowConfigFetchAPI$12 = networkRepo$callWorkflowConfigFetchAPI$1;
                Object obj = networkRepo$callWorkflowConfigFetchAPI$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = networkRepo$callWorkflowConfigFetchAPI$12.label;
                if (i == 0) {
                    if (i == 1) {
                        str8 = (String) networkRepo$callWorkflowConfigFetchAPI$12.L$0;
                        ResultKt.throwOnFailure(obj);
                        FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().setWorkflowConfig$hyperkyc_release(str8);
                        Object fromJson = new Gson().fromJson(str8, (Class<Object>) WorkflowConfig.class);
                        Intrinsics.checkNotNull(str8);
                        ((WorkflowConfig) fromJson).setHash(CoreExtsKt.toMD5Hash(str8));
                        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(body, Wo…de().toString()\n        }");
                        return fromJson;
                    }
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    apiGet$default = ((Result) obj).getValue();
                    str6 = null;
                    ResultKt.throwOnFailure(apiGet$default);
                    closeable = (Closeable) apiGet$default;
                    try {
                        ResponseBody body = ((Response) closeable).body();
                        str9 = body != null ? body.string() : str6;
                        r12 = str6;
                    } catch (Throwable th) {
                        r12 = th;
                        str9 = str6;
                    }
                    if (closeable != null) {
                        try {
                            closeable.close();
                        } catch (Throwable th2) {
                            if (r12 == 0) {
                                r12 = th2;
                            } else {
                                ExceptionsKt.addSuppressed(r12, th2);
                            }
                        }
                    }
                    if (r12 != 0) {
                        throw r12;
                    }
                    Intrinsics.checkNotNull(str9);
                    str8 = str9;
                    FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().setWorkflowConfig$hyperkyc_release(str8);
                    Object fromJson2 = new Gson().fromJson(str8, (Class<Object>) WorkflowConfig.class);
                    Intrinsics.checkNotNull(str8);
                    ((WorkflowConfig) fromJson2).setHash(CoreExtsKt.toMD5Hash(str8));
                    Intrinsics.checkNotNullExpressionValue(fromJson2, "Gson().fromJson(body, Wo…de().toString()\n        }");
                    return fromJson2;
                }
                ResultKt.throwOnFailure(obj);
                HyperLogger.Level level = HyperLogger.Level.DEBUG;
                HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb2 = new StringBuilder();
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
                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                if (matcher2.find()) {
                    ?? replaceAll = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                    objectRef.element = replaceAll;
                }
                if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                    str4 = (String) objectRef.element;
                } else {
                    str4 = ((String) objectRef.element).substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb2.append(str4);
                sb2.append(" - ");
                String str10 = "callWorkflowConfigFetchAPI() called with: appId = " + str + ", workflowId = " + str2 + ", cacheDir = " + file + ", workflowConfigJson = " + str3;
                if (str10 == null) {
                    str10 = "null ";
                }
                sb2.append(str10);
                sb2.append(' ');
                sb2.append("");
                companion.log(level, sb2.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th3) {
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        m1202constructorimpl = "";
                    }
                    String packageName = (String) m1202constructorimpl;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                        if (!StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            str5 = str3;
                            str6 = null;
                            if (!CoreExtsKt.isDebug()) {
                            }
                            String url = new URL("https://hv-central-config.s3.ap-south-1.amazonaws.com/audit-portal/prod/workflow-builder/workflows/appIds/" + str + '/' + (str2 + ".json")).toString();
                            Intrinsics.checkNotNullExpressionValue(url, "url.toString()");
                            networkRepo$callWorkflowConfigFetchAPI$12.label = 2;
                            apiGet$default = NetworkHelpersKt.apiGet$default(url, null, null, file, num, null, networkRepo$callWorkflowConfigFetchAPI$12, 38, null);
                            if (apiGet$default == coroutine_suspended) {
                            }
                            ResultKt.throwOnFailure(apiGet$default);
                            closeable = (Closeable) apiGet$default;
                            ResponseBody body2 = ((Response) closeable).body();
                            if (body2 != null) {
                            }
                            r12 = str6;
                            if (closeable != null) {
                            }
                            if (r12 != 0) {
                            }
                        } else {
                            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                            StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                            if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                                str6 = null;
                            } else {
                                str6 = null;
                                String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default != null) {
                                    canonicalName2 = substringAfterLast$default;
                                    objectRef2.element = canonicalName2;
                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                    if (matcher.find()) {
                                        ?? replaceAll2 = matcher.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                        objectRef2.element = replaceAll2;
                                    }
                                    if (((String) objectRef2.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                        str7 = (String) objectRef2.element;
                                    } else {
                                        str7 = ((String) objectRef2.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb3 = new StringBuilder();
                                    StringBuilder sb4 = new StringBuilder();
                                    sb4.append("callWorkflowConfigFetchAPI() called with: appId = ");
                                    sb4.append(str);
                                    sb4.append(", workflowId = ");
                                    sb4.append(str2);
                                    sb4.append(", cacheDir = ");
                                    sb4.append(file);
                                    sb4.append(", workflowConfigJson = ");
                                    str5 = str3;
                                    sb4.append(str5);
                                    sb = sb4.toString();
                                    if (sb == null) {
                                        sb = "null ";
                                    }
                                    sb3.append(sb);
                                    sb3.append(' ');
                                    sb3.append("");
                                    Log.println(3, str7, sb3.toString());
                                    if (!CoreExtsKt.isDebug() && str5 != null) {
                                        networkRepo$callWorkflowConfigFetchAPI$12.L$0 = str5;
                                        networkRepo$callWorkflowConfigFetchAPI$12.label = 1;
                                        if (DelayKt.delay(2000L, networkRepo$callWorkflowConfigFetchAPI$12) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        str8 = str5;
                                        FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().setWorkflowConfig$hyperkyc_release(str8);
                                        Object fromJson22 = new Gson().fromJson(str8, (Class<Object>) WorkflowConfig.class);
                                        Intrinsics.checkNotNull(str8);
                                        ((WorkflowConfig) fromJson22).setHash(CoreExtsKt.toMD5Hash(str8));
                                        Intrinsics.checkNotNullExpressionValue(fromJson22, "Gson().fromJson(body, Wo…de().toString()\n        }");
                                        return fromJson22;
                                    }
                                    String url2 = new URL("https://hv-central-config.s3.ap-south-1.amazonaws.com/audit-portal/prod/workflow-builder/workflows/appIds/" + str + '/' + (str2 + ".json")).toString();
                                    Intrinsics.checkNotNullExpressionValue(url2, "url.toString()");
                                    networkRepo$callWorkflowConfigFetchAPI$12.label = 2;
                                    apiGet$default = NetworkHelpersKt.apiGet$default(url2, null, null, file, num, null, networkRepo$callWorkflowConfigFetchAPI$12, 38, null);
                                    if (apiGet$default == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    ResultKt.throwOnFailure(apiGet$default);
                                    closeable = (Closeable) apiGet$default;
                                    ResponseBody body22 = ((Response) closeable).body();
                                    if (body22 != null) {
                                    }
                                    r12 = str6;
                                    if (closeable != null) {
                                    }
                                    if (r12 != 0) {
                                    }
                                }
                            }
                            Class<?> cls2 = getClass();
                            canonicalName2 = cls2 != null ? cls2.getCanonicalName() : str6;
                            if (canonicalName2 == 0) {
                                canonicalName2 = "N/A";
                            }
                            objectRef2.element = canonicalName2;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                            if (matcher.find()) {
                            }
                            if (((String) objectRef2.element).length() > 23) {
                            }
                            str7 = (String) objectRef2.element;
                            StringBuilder sb32 = new StringBuilder();
                            StringBuilder sb42 = new StringBuilder();
                            sb42.append("callWorkflowConfigFetchAPI() called with: appId = ");
                            sb42.append(str);
                            sb42.append(", workflowId = ");
                            sb42.append(str2);
                            sb42.append(", cacheDir = ");
                            sb42.append(file);
                            sb42.append(", workflowConfigJson = ");
                            str5 = str3;
                            sb42.append(str5);
                            sb = sb42.toString();
                            if (sb == null) {
                            }
                            sb32.append(sb);
                            sb32.append(' ');
                            sb32.append("");
                            Log.println(3, str7, sb32.toString());
                            if (!CoreExtsKt.isDebug()) {
                            }
                            String url22 = new URL("https://hv-central-config.s3.ap-south-1.amazonaws.com/audit-portal/prod/workflow-builder/workflows/appIds/" + str + '/' + (str2 + ".json")).toString();
                            Intrinsics.checkNotNullExpressionValue(url22, "url.toString()");
                            networkRepo$callWorkflowConfigFetchAPI$12.label = 2;
                            apiGet$default = NetworkHelpersKt.apiGet$default(url22, null, null, file, num, null, networkRepo$callWorkflowConfigFetchAPI$12, 38, null);
                            if (apiGet$default == coroutine_suspended) {
                            }
                            ResultKt.throwOnFailure(apiGet$default);
                            closeable = (Closeable) apiGet$default;
                            ResponseBody body222 = ((Response) closeable).body();
                            if (body222 != null) {
                            }
                            r12 = str6;
                            if (closeable != null) {
                            }
                            if (r12 != 0) {
                            }
                        }
                    }
                }
                str5 = str3;
                str6 = null;
                if (!CoreExtsKt.isDebug()) {
                }
                String url222 = new URL("https://hv-central-config.s3.ap-south-1.amazonaws.com/audit-portal/prod/workflow-builder/workflows/appIds/" + str + '/' + (str2 + ".json")).toString();
                Intrinsics.checkNotNullExpressionValue(url222, "url.toString()");
                networkRepo$callWorkflowConfigFetchAPI$12.label = 2;
                apiGet$default = NetworkHelpersKt.apiGet$default(url222, null, null, file, num, null, networkRepo$callWorkflowConfigFetchAPI$12, 38, null);
                if (apiGet$default == coroutine_suspended) {
                }
                ResultKt.throwOnFailure(apiGet$default);
                closeable = (Closeable) apiGet$default;
                ResponseBody body2222 = ((Response) closeable).body();
                if (body2222 != null) {
                }
                r12 = str6;
                if (closeable != null) {
                }
                if (r12 != 0) {
                }
            }
        }
        networkRepo$callWorkflowConfigFetchAPI$1 = new NetworkRepo$callWorkflowConfigFetchAPI$1(this, continuation);
        NetworkRepo$callWorkflowConfigFetchAPI$1 networkRepo$callWorkflowConfigFetchAPI$122 = networkRepo$callWorkflowConfigFetchAPI$1;
        Object obj2 = networkRepo$callWorkflowConfigFetchAPI$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = networkRepo$callWorkflowConfigFetchAPI$122.label;
        if (i == 0) {
        }
    }

    public static /* synthetic */ Object fetchTextConfig$hyperkyc_release$default(NetworkRepo networkRepo, String str, String str2, String str3, File file, String str4, Continuation continuation, int i, Object obj) {
        if ((i & 16) != 0) {
            str4 = null;
        }
        return networkRepo.fetchTextConfig$hyperkyc_release(str, str2, str3, file, str4, continuation);
    }

    public final Object fetchTextConfig$hyperkyc_release(String str, String str2, String str3, File file, String str4, Continuation<? super Map<String, ? extends Map<String, ? extends Object>>> continuation) {
        return CoroutineExtsKt.onIO$default(null, new NetworkRepo$fetchTextConfig$2(str, str2, str3, file, str4, null), continuation, 1, null);
    }

    public final Object prefetchTextConfig$hyperkyc_release(String str, String str2, String str3, File file, Continuation<? super Map<String, ? extends Map<String, ? extends Object>>> continuation) {
        return CoroutineExtsKt.onIO$default(null, new NetworkRepo$prefetchTextConfig$2(str, str2, str3, file, null), continuation, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x00c4, code lost:
    
        if (r1 != 0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x0201, code lost:
    
        if (r1 != 0) goto L74;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x02b6 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x036b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x036c  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0389 A[Catch: all -> 0x0392, TRY_LEAVE, TryCatch #0 {all -> 0x0392, blocks: (B:18:0x0380, B:20:0x0389), top: B:17:0x0380 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x03a7  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x040b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0420  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0455  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0471 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0519  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x055a  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x055d  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0434  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x03f5  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x058a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0398 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v30, types: [T] */
    /* JADX WARN: Type inference failed for: r1v40, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v42 */
    /* JADX WARN: Type inference failed for: r1v43 */
    /* JADX WARN: Type inference failed for: r1v44 */
    /* JADX WARN: Type inference failed for: r1v47, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v5, types: [T] */
    /* JADX WARN: Type inference failed for: r1v72, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v74 */
    /* JADX WARN: Type inference failed for: r1v75 */
    /* JADX WARN: Type inference failed for: r1v76 */
    /* JADX WARN: Type inference failed for: r1v79, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v86 */
    /* JADX WARN: Type inference failed for: r1v87 */
    /* JADX WARN: Type inference failed for: r4v12, types: [T] */
    /* JADX WARN: Type inference failed for: r4v21, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r9v12, types: [T] */
    /* JADX WARN: Type inference failed for: r9v21, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v23, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object callTextConfigFetchAPI(String str, String str2, String str3, File file, Integer num, String str4, Continuation<? super Map<String, ? extends Map<String, ? extends Object>>> continuation) {
        NetworkRepo$callTextConfigFetchAPI$1 networkRepo$callTextConfigFetchAPI$1;
        int i;
        NetworkRepo$callTextConfigFetchAPI$1 networkRepo$callTextConfigFetchAPI$12;
        String str5;
        HyperLogger hyperLogger;
        ?? r1;
        String str6;
        String str7;
        Object m1202constructorimpl;
        String str8;
        char c;
        ?? r12;
        String str9;
        String sb;
        String className;
        Type type;
        String str10;
        String str11;
        int i2;
        String str12;
        Object apiGet$default;
        NetworkRepo networkRepo;
        Type type2;
        Object obj;
        NetworkRepo networkRepo2;
        String className2;
        Ref.ObjectRef objectRef;
        StackTraceElement stackTraceElement;
        ?? canonicalName;
        Class<?> cls;
        Matcher matcher;
        int i3;
        String str13;
        String str14;
        Object m1202constructorimpl2;
        Object obj2;
        ?? canonicalName2;
        Class<?> cls2;
        Matcher matcher2;
        String str15;
        String className3;
        String className4;
        Closeable closeable;
        Throwable th;
        String str16;
        if (continuation instanceof NetworkRepo$callTextConfigFetchAPI$1) {
            networkRepo$callTextConfigFetchAPI$1 = (NetworkRepo$callTextConfigFetchAPI$1) continuation;
            if ((networkRepo$callTextConfigFetchAPI$1.label & Integer.MIN_VALUE) != 0) {
                networkRepo$callTextConfigFetchAPI$1.label -= Integer.MIN_VALUE;
                NetworkRepo$callTextConfigFetchAPI$1 networkRepo$callTextConfigFetchAPI$13 = networkRepo$callTextConfigFetchAPI$1;
                Object obj3 = networkRepo$callTextConfigFetchAPI$13.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = networkRepo$callTextConfigFetchAPI$13.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj3);
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb2 = new StringBuilder();
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    if (stackTraceElement2 == null || (className2 = stackTraceElement2.getClassName()) == null) {
                        networkRepo$callTextConfigFetchAPI$12 = networkRepo$callTextConfigFetchAPI$13;
                        str5 = "Throwable().stackTrace";
                        hyperLogger = companion;
                    } else {
                        networkRepo$callTextConfigFetchAPI$12 = networkRepo$callTextConfigFetchAPI$13;
                        str5 = "Throwable().stackTrace";
                        hyperLogger = companion;
                        r1 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls3 = getClass();
                    r1 = cls3 != null ? cls3.getCanonicalName() : 0;
                    if (r1 == 0) {
                        r1 = "N/A";
                    }
                    objectRef2.element = r1;
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                    if (matcher3.find()) {
                        ?? replaceAll = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                        objectRef2.element = replaceAll;
                    }
                    if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str6 = (String) objectRef2.element;
                    } else {
                        str6 = ((String) objectRef2.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb2.append(str6);
                    sb2.append(" - ");
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("callTextConfigFetchAPI() called with: appId = ");
                    sb3.append(str);
                    sb3.append(", languageCode = ");
                    sb3.append(str2);
                    sb3.append(", source = ");
                    sb3.append(str3);
                    sb3.append(", cacheDir = ");
                    sb3.append(file);
                    str7 = " - ";
                    sb3.append(", textConfigJson = ");
                    sb3.append(str4);
                    String sb4 = sb3.toString();
                    if (sb4 == null) {
                        sb4 = "null ";
                    }
                    sb2.append(sb4);
                    sb2.append(' ');
                    sb2.append("");
                    hyperLogger.log(level, sb2.toString());
                    if (CoreExtsKt.isRelease()) {
                        str8 = str4;
                    } else {
                        try {
                            Result.Companion companion2 = Result.INSTANCE;
                            Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                        } catch (Throwable th2) {
                            Result.Companion companion3 = Result.INSTANCE;
                            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                            m1202constructorimpl = "";
                        }
                        String packageName = (String) m1202constructorimpl;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                String str17 = str5;
                                Intrinsics.checkNotNullExpressionValue(stackTrace2, str17);
                                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                if (stackTraceElement3 == null || (className = stackTraceElement3.getClassName()) == null) {
                                    str5 = str17;
                                    c = FilenameUtils.EXTENSION_SEPARATOR;
                                } else {
                                    str5 = str17;
                                    c = FilenameUtils.EXTENSION_SEPARATOR;
                                    r12 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                }
                                Class<?> cls4 = getClass();
                                r12 = cls4 != null ? cls4.getCanonicalName() : 0;
                                if (r12 == 0) {
                                    r12 = "N/A";
                                }
                                objectRef3.element = r12;
                                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                                if (matcher4.find()) {
                                    ?? replaceAll2 = matcher4.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                    objectRef3.element = replaceAll2;
                                }
                                if (((String) objectRef3.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str9 = ((String) objectRef3.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
                                    StringBuilder sb5 = new StringBuilder();
                                    StringBuilder sb6 = new StringBuilder();
                                    sb6.append("callTextConfigFetchAPI() called with: appId = ");
                                    sb6.append(str);
                                    sb6.append(", languageCode = ");
                                    sb6.append(str2);
                                    sb6.append(", source = ");
                                    sb6.append(str3);
                                    sb6.append(", cacheDir = ");
                                    sb6.append(file);
                                    sb6.append(", textConfigJson = ");
                                    str8 = str4;
                                    sb6.append(str8);
                                    sb = sb6.toString();
                                    if (sb == null) {
                                        sb = "null ";
                                    }
                                    sb5.append(sb);
                                    sb5.append(' ');
                                    sb5.append("");
                                    Log.println(3, str9, sb5.toString());
                                    type = new TypeToken<Map<String, ? extends Map<String, ? extends Object>>>() { // from class: co.hyperverge.hyperkyc.data.network.NetworkRepo$callTextConfigFetchAPI$textConfigTypeToken$1
                                    }.getType();
                                    if (!CoreExtsKt.isDebug() && str8 != null) {
                                        NetworkRepo$callTextConfigFetchAPI$1 networkRepo$callTextConfigFetchAPI$14 = networkRepo$callTextConfigFetchAPI$12;
                                        networkRepo$callTextConfigFetchAPI$14.L$0 = this;
                                        networkRepo$callTextConfigFetchAPI$14.L$1 = str8;
                                        networkRepo$callTextConfigFetchAPI$14.L$2 = type;
                                        networkRepo$callTextConfigFetchAPI$14.label = 1;
                                        if (DelayKt.delay(2000L, networkRepo$callTextConfigFetchAPI$14) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        type2 = type;
                                        networkRepo2 = this;
                                        str11 = "";
                                        str12 = str5;
                                        i2 = 2;
                                    } else {
                                        NetworkRepo$callTextConfigFetchAPI$1 networkRepo$callTextConfigFetchAPI$15 = networkRepo$callTextConfigFetchAPI$12;
                                        String str18 = str3 + '_' + str2 + "_text_config.json";
                                        if (StringsKt.equals(str3, "default", false)) {
                                            str10 = "https://s3-ap-south-1.amazonaws.com/hv-central-config/audit-portal/prod/workflow-builder/text_ui_configs/default/" + str18;
                                        } else {
                                            str10 = "https://s3-ap-south-1.amazonaws.com/hv-central-config/audit-portal/prod/workflow-builder/text_ui_configs/appIds/" + str + '/' + str18;
                                        }
                                        String str19 = str10;
                                        networkRepo$callTextConfigFetchAPI$15.L$0 = this;
                                        networkRepo$callTextConfigFetchAPI$15.L$1 = type;
                                        networkRepo$callTextConfigFetchAPI$15.label = 2;
                                        str11 = "";
                                        i2 = 2;
                                        str12 = str5;
                                        apiGet$default = NetworkHelpersKt.apiGet$default(str19, null, null, file, num, null, networkRepo$callTextConfigFetchAPI$15, 38, null);
                                        if (apiGet$default == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        networkRepo = this;
                                        type2 = type;
                                        obj = apiGet$default;
                                        if (Result.m1208isFailureimpl(obj)) {
                                        }
                                        Intrinsics.checkNotNull(obj);
                                        closeable = (Closeable) obj;
                                        ResponseBody body = ((Response) closeable).body();
                                        str16 = body == null ? body.string() : null;
                                        th = null;
                                        if (closeable != null) {
                                        }
                                        if (th == null) {
                                        }
                                    }
                                }
                                str9 = (String) objectRef3.element;
                                StringBuilder sb52 = new StringBuilder();
                                StringBuilder sb62 = new StringBuilder();
                                sb62.append("callTextConfigFetchAPI() called with: appId = ");
                                sb62.append(str);
                                sb62.append(", languageCode = ");
                                sb62.append(str2);
                                sb62.append(", source = ");
                                sb62.append(str3);
                                sb62.append(", cacheDir = ");
                                sb62.append(file);
                                sb62.append(", textConfigJson = ");
                                str8 = str4;
                                sb62.append(str8);
                                sb = sb62.toString();
                                if (sb == null) {
                                }
                                sb52.append(sb);
                                sb52.append(' ');
                                sb52.append("");
                                Log.println(3, str9, sb52.toString());
                                type = new TypeToken<Map<String, ? extends Map<String, ? extends Object>>>() { // from class: co.hyperverge.hyperkyc.data.network.NetworkRepo$callTextConfigFetchAPI$textConfigTypeToken$1
                                }.getType();
                                if (!CoreExtsKt.isDebug()) {
                                }
                                NetworkRepo$callTextConfigFetchAPI$1 networkRepo$callTextConfigFetchAPI$152 = networkRepo$callTextConfigFetchAPI$12;
                                String str182 = str3 + '_' + str2 + "_text_config.json";
                                if (StringsKt.equals(str3, "default", false)) {
                                }
                                String str192 = str10;
                                networkRepo$callTextConfigFetchAPI$152.L$0 = this;
                                networkRepo$callTextConfigFetchAPI$152.L$1 = type;
                                networkRepo$callTextConfigFetchAPI$152.label = 2;
                                str11 = "";
                                i2 = 2;
                                str12 = str5;
                                apiGet$default = NetworkHelpersKt.apiGet$default(str192, null, null, file, num, null, networkRepo$callTextConfigFetchAPI$152, 38, null);
                                if (apiGet$default == coroutine_suspended) {
                                }
                            }
                        }
                        str8 = str4;
                    }
                    c = FilenameUtils.EXTENSION_SEPARATOR;
                    type = new TypeToken<Map<String, ? extends Map<String, ? extends Object>>>() { // from class: co.hyperverge.hyperkyc.data.network.NetworkRepo$callTextConfigFetchAPI$textConfigTypeToken$1
                    }.getType();
                    if (!CoreExtsKt.isDebug()) {
                    }
                    NetworkRepo$callTextConfigFetchAPI$1 networkRepo$callTextConfigFetchAPI$1522 = networkRepo$callTextConfigFetchAPI$12;
                    String str1822 = str3 + '_' + str2 + "_text_config.json";
                    if (StringsKt.equals(str3, "default", false)) {
                    }
                    String str1922 = str10;
                    networkRepo$callTextConfigFetchAPI$1522.L$0 = this;
                    networkRepo$callTextConfigFetchAPI$1522.L$1 = type;
                    networkRepo$callTextConfigFetchAPI$1522.label = 2;
                    str11 = "";
                    i2 = 2;
                    str12 = str5;
                    apiGet$default = NetworkHelpersKt.apiGet$default(str1922, null, null, file, num, null, networkRepo$callTextConfigFetchAPI$1522, 38, null);
                    if (apiGet$default == coroutine_suspended) {
                    }
                } else if (i == 1) {
                    type2 = (Type) networkRepo$callTextConfigFetchAPI$13.L$2;
                    String str20 = (String) networkRepo$callTextConfigFetchAPI$13.L$1;
                    networkRepo2 = (NetworkRepo) networkRepo$callTextConfigFetchAPI$13.L$0;
                    ResultKt.throwOnFailure(obj3);
                    str8 = str20;
                    str5 = "Throwable().stackTrace";
                    str7 = " - ";
                    str11 = "";
                    str12 = str5;
                    i2 = 2;
                } else {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    type2 = (Type) networkRepo$callTextConfigFetchAPI$13.L$1;
                    networkRepo = (NetworkRepo) networkRepo$callTextConfigFetchAPI$13.L$0;
                    ResultKt.throwOnFailure(obj3);
                    obj = ((Result) obj3).getValue();
                    str11 = "";
                    str7 = " - ";
                    i2 = 2;
                    str12 = "Throwable().stackTrace";
                    if (Result.m1208isFailureimpl(obj)) {
                        obj = null;
                    }
                    Intrinsics.checkNotNull(obj);
                    closeable = (Closeable) obj;
                    try {
                        ResponseBody body2 = ((Response) closeable).body();
                        str16 = body2 == null ? body2.string() : null;
                        th = null;
                    } catch (Throwable th3) {
                        th = th3;
                        str16 = null;
                    }
                    if (closeable != null) {
                        try {
                            closeable.close();
                        } catch (Throwable th4) {
                            if (th == null) {
                                th = th4;
                            } else {
                                ExceptionsKt.addSuppressed(th, th4);
                            }
                        }
                    }
                    if (th == null) {
                        throw th;
                    }
                    Intrinsics.checkNotNull(str16);
                    networkRepo2 = networkRepo;
                    str8 = str16;
                }
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb7 = new StringBuilder();
                objectRef = new Ref.ObjectRef();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, str12);
                stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                if (stackTraceElement != null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, i2, (Object) null)) == 0) {
                    canonicalName = (networkRepo2 != null || (cls = networkRepo2.getClass()) == null) ? 0 : cls.getCanonicalName();
                    if (canonicalName == 0) {
                        canonicalName = "N/A";
                    }
                }
                objectRef.element = canonicalName;
                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                if (matcher.find()) {
                    ?? replaceAll3 = matcher.replaceAll(str11);
                    Intrinsics.checkNotNullExpressionValue(replaceAll3, "replaceAll(\"\")");
                    objectRef.element = replaceAll3;
                }
                if (((String) objectRef.element).length() <= 23) {
                    i3 = 26;
                    if (Build.VERSION.SDK_INT < 26) {
                        str13 = ((String) objectRef.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str13, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb7.append(str13);
                        sb7.append(str7);
                        str14 = "fetchTextConfig() body : " + str8;
                        if (str14 == null) {
                            str14 = "null ";
                        }
                        sb7.append(str14);
                        sb7.append(' ');
                        sb7.append(str11);
                        companion4.log(level2, sb7.toString());
                        if (!CoreExtsKt.isRelease()) {
                            try {
                                Result.Companion companion5 = Result.INSTANCE;
                                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                            } catch (Throwable th5) {
                                Result.Companion companion6 = Result.INSTANCE;
                                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th5));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                m1202constructorimpl2 = str11;
                            }
                            String packageName2 = (String) m1202constructorimpl2;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                                if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, i2, (Object) null)) {
                                    Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace4, str12);
                                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                    if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null) {
                                        obj2 = null;
                                    } else {
                                        obj2 = null;
                                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, i2, (Object) null);
                                        if (substringAfterLast$default != null) {
                                            canonicalName2 = substringAfterLast$default;
                                            objectRef4.element = canonicalName2;
                                            matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                            if (matcher2.find()) {
                                                ?? replaceAll4 = matcher2.replaceAll(str11);
                                                Intrinsics.checkNotNullExpressionValue(replaceAll4, "replaceAll(\"\")");
                                                objectRef4.element = replaceAll4;
                                            }
                                            if (((String) objectRef4.element).length() > 23 || Build.VERSION.SDK_INT >= i3) {
                                                str15 = (String) objectRef4.element;
                                            } else {
                                                str15 = ((String) objectRef4.element).substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(str15, "this as java.lang.String…ing(startIndex, endIndex)");
                                            }
                                            StringBuilder sb8 = new StringBuilder();
                                            String str21 = "fetchTextConfig() body : " + str8;
                                            sb8.append(str21 != null ? "null " : str21);
                                            sb8.append(' ');
                                            sb8.append(str11);
                                            Log.println(4, str15, sb8.toString());
                                        }
                                    }
                                    canonicalName2 = (networkRepo2 == null || (cls2 = networkRepo2.getClass()) == null) ? obj2 : cls2.getCanonicalName();
                                    if (canonicalName2 == 0) {
                                        canonicalName2 = "N/A";
                                    }
                                    objectRef4.element = canonicalName2;
                                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                    if (matcher2.find()) {
                                    }
                                    if (((String) objectRef4.element).length() > 23) {
                                    }
                                    str15 = (String) objectRef4.element;
                                    StringBuilder sb82 = new StringBuilder();
                                    String str212 = "fetchTextConfig() body : " + str8;
                                    sb82.append(str212 != null ? "null " : str212);
                                    sb82.append(' ');
                                    sb82.append(str11);
                                    Log.println(4, str15, sb82.toString());
                                }
                            }
                        }
                        FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().setTextConfig$hyperkyc_release(str8);
                        Map fromJson = (Map) new Gson().fromJson(str8, type2);
                        Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson");
                        return fromJson;
                    }
                } else {
                    i3 = 26;
                }
                str13 = (String) objectRef.element;
                sb7.append(str13);
                sb7.append(str7);
                str14 = "fetchTextConfig() body : " + str8;
                if (str14 == null) {
                }
                sb7.append(str14);
                sb7.append(' ');
                sb7.append(str11);
                companion4.log(level2, sb7.toString());
                if (!CoreExtsKt.isRelease()) {
                }
                FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().setTextConfig$hyperkyc_release(str8);
                Map fromJson2 = (Map) new Gson().fromJson(str8, type2);
                Intrinsics.checkNotNullExpressionValue(fromJson2, "fromJson");
                return fromJson2;
            }
        }
        networkRepo$callTextConfigFetchAPI$1 = new NetworkRepo$callTextConfigFetchAPI$1(this, continuation);
        NetworkRepo$callTextConfigFetchAPI$1 networkRepo$callTextConfigFetchAPI$132 = networkRepo$callTextConfigFetchAPI$1;
        Object obj32 = networkRepo$callTextConfigFetchAPI$132.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = networkRepo$callTextConfigFetchAPI$132.label;
        if (i != 0) {
        }
        HyperLogger.Level level22 = HyperLogger.Level.DEBUG;
        HyperLogger companion42 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb72 = new StringBuilder();
        objectRef = new Ref.ObjectRef();
        StackTraceElement[] stackTrace32 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace32, str12);
        stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace32);
        if (stackTraceElement != null) {
        }
        if (networkRepo2 != null) {
        }
        if (canonicalName == 0) {
        }
        objectRef.element = canonicalName;
        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
        if (matcher.find()) {
        }
        if (((String) objectRef.element).length() <= 23) {
        }
        str13 = (String) objectRef.element;
        sb72.append(str13);
        sb72.append(str7);
        str14 = "fetchTextConfig() body : " + str8;
        if (str14 == null) {
        }
        sb72.append(str14);
        sb72.append(' ');
        sb72.append(str11);
        companion42.log(level22, sb72.toString());
        if (!CoreExtsKt.isRelease()) {
        }
        FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().setTextConfig$hyperkyc_release(str8);
        Map fromJson22 = (Map) new Gson().fromJson(str8, type2);
        Intrinsics.checkNotNullExpressionValue(fromJson22, "fromJson");
        return fromJson22;
    }

    public static /* synthetic */ Object fetchUIColorConfig$hyperkyc_release$default(NetworkRepo networkRepo, String str, File file, String str2, String str3, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            str3 = null;
        }
        return networkRepo.fetchUIColorConfig$hyperkyc_release(str, file, str2, str3, continuation);
    }

    public final Object fetchUIColorConfig$hyperkyc_release(String str, File file, String str2, String str3, Continuation<? super HSUIConfig> continuation) {
        return CoroutineExtsKt.onIO$default(null, new NetworkRepo$fetchUIColorConfig$2(str, file, str2, str3, null), continuation, 1, null);
    }

    public final Object prefetchUIColorConfig$hyperkyc_release(String str, File file, String str2, Continuation<? super HSUIConfig> continuation) {
        return CoroutineExtsKt.onIO$default(null, new NetworkRepo$prefetchUIColorConfig$2(str, file, str2, null), continuation, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x02fb A[Catch: all -> 0x0304, TRY_LEAVE, TryCatch #1 {all -> 0x0304, blocks: (B:16:0x02f2, B:18:0x02fb), top: B:15:0x02f2 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0309 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0274 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02de A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01f4  */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r15v10, types: [T] */
    /* JADX WARN: Type inference failed for: r15v15, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r15v16 */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v7 */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r3v17, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v47, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v7, types: [T] */
    /* JADX WARN: Type inference failed for: r9v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object callUIConfigFetchAPI(String str, File file, String str2, Integer num, String str3, Continuation<? super HSUIConfig> continuation) {
        NetworkRepo$callUIConfigFetchAPI$1 networkRepo$callUIConfigFetchAPI$1;
        int i;
        ?? canonicalName;
        String str4;
        Object m1202constructorimpl;
        String str5;
        String str6;
        ?? canonicalName2;
        Matcher matcher;
        String str7;
        String sb;
        String className;
        Type type;
        Object apiGet$default;
        Type type2;
        Object obj;
        String str8;
        String className2;
        Closeable closeable;
        ?? r12;
        if (continuation instanceof NetworkRepo$callUIConfigFetchAPI$1) {
            networkRepo$callUIConfigFetchAPI$1 = (NetworkRepo$callUIConfigFetchAPI$1) continuation;
            if ((networkRepo$callUIConfigFetchAPI$1.label & Integer.MIN_VALUE) != 0) {
                networkRepo$callUIConfigFetchAPI$1.label -= Integer.MIN_VALUE;
                NetworkRepo$callUIConfigFetchAPI$1 networkRepo$callUIConfigFetchAPI$12 = networkRepo$callUIConfigFetchAPI$1;
                Object obj2 = networkRepo$callUIConfigFetchAPI$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = networkRepo$callUIConfigFetchAPI$12.label;
                if (i == 0) {
                    if (i == 1) {
                        type2 = (Type) networkRepo$callUIConfigFetchAPI$12.L$1;
                        str8 = (String) networkRepo$callUIConfigFetchAPI$12.L$0;
                        ResultKt.throwOnFailure(obj2);
                        HSUIConfig fromJson = (HSUIConfig) new Gson().fromJson(str8, type2);
                        fromJson.setUIConfigJSON(str8);
                        FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().setUiConfig$hyperkyc_release(str8);
                        Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson");
                        return fromJson;
                    }
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    type2 = (Type) networkRepo$callUIConfigFetchAPI$12.L$0;
                    ResultKt.throwOnFailure(obj2);
                    obj = ((Result) obj2).getValue();
                    str6 = null;
                    Object obj3 = !Result.m1208isFailureimpl(obj) ? str6 : obj;
                    Intrinsics.checkNotNull(obj3);
                    closeable = (Closeable) obj3;
                    try {
                        ResponseBody body = ((Response) closeable).body();
                        r12 = str6;
                        str6 = body == null ? body.string() : str6;
                    } catch (Throwable th) {
                        r12 = th;
                    }
                    if (closeable != null) {
                        try {
                            closeable.close();
                        } catch (Throwable th2) {
                            if (r12 == 0) {
                                r12 = th2;
                            } else {
                                ExceptionsKt.addSuppressed(r12, th2);
                            }
                        }
                    }
                    if (r12 == 0) {
                        throw r12;
                    }
                    Intrinsics.checkNotNull(str6);
                    str8 = str6;
                    HSUIConfig fromJson2 = (HSUIConfig) new Gson().fromJson(str8, type2);
                    fromJson2.setUIConfigJSON(str8);
                    FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().setUiConfig$hyperkyc_release(str8);
                    Intrinsics.checkNotNullExpressionValue(fromJson2, "fromJson");
                    return fromJson2;
                }
                ResultKt.throwOnFailure(obj2);
                HyperLogger.Level level = HyperLogger.Level.DEBUG;
                HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb2 = new StringBuilder();
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
                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                if (matcher2.find()) {
                    ?? replaceAll = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                    objectRef.element = replaceAll;
                }
                if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                    str4 = (String) objectRef.element;
                } else {
                    str4 = ((String) objectRef.element).substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb2.append(str4);
                sb2.append(" - ");
                String str9 = "callUIConfigFetchAPI() called with: appId = " + str + ", cacheDir = " + file + ", workflowId = " + str2 + ", uiColorConfigJson = " + str3;
                if (str9 == null) {
                    str9 = "null ";
                }
                sb2.append(str9);
                sb2.append(' ');
                sb2.append("");
                companion.log(level, sb2.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th3) {
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        m1202constructorimpl = "";
                    }
                    String packageName = (String) m1202constructorimpl;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                        if (!StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            str5 = str3;
                            str6 = null;
                            type = new TypeToken<HSUIConfig>() { // from class: co.hyperverge.hyperkyc.data.network.NetworkRepo$callUIConfigFetchAPI$uiColorConfigTypeToken$1
                            }.getType();
                            if (!CoreExtsKt.isDebug()) {
                            }
                            networkRepo$callUIConfigFetchAPI$12.L$0 = type;
                            networkRepo$callUIConfigFetchAPI$12.label = 2;
                            apiGet$default = NetworkHelpersKt.apiGet$default("https://s3-ap-south-1.amazonaws.com/hv-central-config/audit-portal/prod/workflow-builder/text_ui_configs/appIds/" + str + '/' + (str2 + "_ui_config.json"), null, null, file, num, null, networkRepo$callUIConfigFetchAPI$12, 38, null);
                            if (apiGet$default != coroutine_suspended) {
                            }
                        } else {
                            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                            StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                            if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                                str6 = null;
                            } else {
                                str6 = null;
                                String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default != null) {
                                    canonicalName2 = substringAfterLast$default;
                                    objectRef2.element = canonicalName2;
                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                    if (matcher.find()) {
                                        ?? replaceAll2 = matcher.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                        objectRef2.element = replaceAll2;
                                    }
                                    if (((String) objectRef2.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                        str7 = (String) objectRef2.element;
                                    } else {
                                        str7 = ((String) objectRef2.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb3 = new StringBuilder();
                                    StringBuilder sb4 = new StringBuilder();
                                    sb4.append("callUIConfigFetchAPI() called with: appId = ");
                                    sb4.append(str);
                                    sb4.append(", cacheDir = ");
                                    sb4.append(file);
                                    sb4.append(", workflowId = ");
                                    sb4.append(str2);
                                    sb4.append(", uiColorConfigJson = ");
                                    str5 = str3;
                                    sb4.append(str5);
                                    sb = sb4.toString();
                                    if (sb == null) {
                                        sb = "null ";
                                    }
                                    sb3.append(sb);
                                    sb3.append(' ');
                                    sb3.append("");
                                    Log.println(3, str7, sb3.toString());
                                    type = new TypeToken<HSUIConfig>() { // from class: co.hyperverge.hyperkyc.data.network.NetworkRepo$callUIConfigFetchAPI$uiColorConfigTypeToken$1
                                    }.getType();
                                    if (!CoreExtsKt.isDebug() && str5 != null) {
                                        networkRepo$callUIConfigFetchAPI$12.L$0 = str5;
                                        networkRepo$callUIConfigFetchAPI$12.L$1 = type;
                                        networkRepo$callUIConfigFetchAPI$12.label = 1;
                                        if (DelayKt.delay(2000L, networkRepo$callUIConfigFetchAPI$12) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        type2 = type;
                                        str8 = str5;
                                        HSUIConfig fromJson22 = (HSUIConfig) new Gson().fromJson(str8, type2);
                                        fromJson22.setUIConfigJSON(str8);
                                        FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().setUiConfig$hyperkyc_release(str8);
                                        Intrinsics.checkNotNullExpressionValue(fromJson22, "fromJson");
                                        return fromJson22;
                                    }
                                    networkRepo$callUIConfigFetchAPI$12.L$0 = type;
                                    networkRepo$callUIConfigFetchAPI$12.label = 2;
                                    apiGet$default = NetworkHelpersKt.apiGet$default("https://s3-ap-south-1.amazonaws.com/hv-central-config/audit-portal/prod/workflow-builder/text_ui_configs/appIds/" + str + '/' + (str2 + "_ui_config.json"), null, null, file, num, null, networkRepo$callUIConfigFetchAPI$12, 38, null);
                                    if (apiGet$default != coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    type2 = type;
                                    obj = apiGet$default;
                                    if (!Result.m1208isFailureimpl(obj)) {
                                    }
                                    Intrinsics.checkNotNull(obj3);
                                    closeable = (Closeable) obj3;
                                    ResponseBody body2 = ((Response) closeable).body();
                                    r12 = str6;
                                    str6 = body2 == null ? body2.string() : str6;
                                    if (closeable != null) {
                                    }
                                    if (r12 == 0) {
                                    }
                                }
                            }
                            Class<?> cls2 = getClass();
                            canonicalName2 = cls2 != null ? cls2.getCanonicalName() : str6;
                            if (canonicalName2 == 0) {
                                canonicalName2 = "N/A";
                            }
                            objectRef2.element = canonicalName2;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                            if (matcher.find()) {
                            }
                            if (((String) objectRef2.element).length() > 23) {
                            }
                            str7 = (String) objectRef2.element;
                            StringBuilder sb32 = new StringBuilder();
                            StringBuilder sb42 = new StringBuilder();
                            sb42.append("callUIConfigFetchAPI() called with: appId = ");
                            sb42.append(str);
                            sb42.append(", cacheDir = ");
                            sb42.append(file);
                            sb42.append(", workflowId = ");
                            sb42.append(str2);
                            sb42.append(", uiColorConfigJson = ");
                            str5 = str3;
                            sb42.append(str5);
                            sb = sb42.toString();
                            if (sb == null) {
                            }
                            sb32.append(sb);
                            sb32.append(' ');
                            sb32.append("");
                            Log.println(3, str7, sb32.toString());
                            type = new TypeToken<HSUIConfig>() { // from class: co.hyperverge.hyperkyc.data.network.NetworkRepo$callUIConfigFetchAPI$uiColorConfigTypeToken$1
                            }.getType();
                            if (!CoreExtsKt.isDebug()) {
                            }
                            networkRepo$callUIConfigFetchAPI$12.L$0 = type;
                            networkRepo$callUIConfigFetchAPI$12.label = 2;
                            apiGet$default = NetworkHelpersKt.apiGet$default("https://s3-ap-south-1.amazonaws.com/hv-central-config/audit-portal/prod/workflow-builder/text_ui_configs/appIds/" + str + '/' + (str2 + "_ui_config.json"), null, null, file, num, null, networkRepo$callUIConfigFetchAPI$12, 38, null);
                            if (apiGet$default != coroutine_suspended) {
                            }
                        }
                    }
                }
                str5 = str3;
                str6 = null;
                type = new TypeToken<HSUIConfig>() { // from class: co.hyperverge.hyperkyc.data.network.NetworkRepo$callUIConfigFetchAPI$uiColorConfigTypeToken$1
                }.getType();
                if (!CoreExtsKt.isDebug()) {
                }
                networkRepo$callUIConfigFetchAPI$12.L$0 = type;
                networkRepo$callUIConfigFetchAPI$12.label = 2;
                apiGet$default = NetworkHelpersKt.apiGet$default("https://s3-ap-south-1.amazonaws.com/hv-central-config/audit-portal/prod/workflow-builder/text_ui_configs/appIds/" + str + '/' + (str2 + "_ui_config.json"), null, null, file, num, null, networkRepo$callUIConfigFetchAPI$12, 38, null);
                if (apiGet$default != coroutine_suspended) {
                }
            }
        }
        networkRepo$callUIConfigFetchAPI$1 = new NetworkRepo$callUIConfigFetchAPI$1(this, continuation);
        NetworkRepo$callUIConfigFetchAPI$1 networkRepo$callUIConfigFetchAPI$122 = networkRepo$callUIConfigFetchAPI$1;
        Object obj22 = networkRepo$callUIConfigFetchAPI$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = networkRepo$callUIConfigFetchAPI$122.label;
        if (i == 0) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: performReviewFinish-yxL6bBk$hyperkyc_release, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m399performReviewFinishyxL6bBk$hyperkyc_release(String str, FinishReview finishReview, Map<String, String> map, Function1<? super Throwable, Unit> function1, Continuation<? super Result<Response>> continuation) {
        NetworkRepo$performReviewFinish$1 networkRepo$performReviewFinish$1;
        int i;
        if (continuation instanceof NetworkRepo$performReviewFinish$1) {
            networkRepo$performReviewFinish$1 = (NetworkRepo$performReviewFinish$1) continuation;
            if ((networkRepo$performReviewFinish$1.label & Integer.MIN_VALUE) != 0) {
                networkRepo$performReviewFinish$1.label -= Integer.MIN_VALUE;
                Object obj = networkRepo$performReviewFinish$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = networkRepo$performReviewFinish$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    NetworkRepo$performReviewFinish$3 networkRepo$performReviewFinish$3 = new NetworkRepo$performReviewFinish$3(str, map, finishReview, function1, null);
                    networkRepo$performReviewFinish$1.label = 1;
                    obj = CoroutineExtsKt.onIO$default(null, networkRepo$performReviewFinish$3, networkRepo$performReviewFinish$1, 1, null);
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
        networkRepo$performReviewFinish$1 = new NetworkRepo$performReviewFinish$1(this, continuation);
        Object obj2 = networkRepo$performReviewFinish$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = networkRepo$performReviewFinish$1.label;
        if (i != 0) {
        }
        return ((Result) obj2).getValue();
    }

    /* renamed from: performReviewFinish-yxL6bBk$hyperkyc_release$default, reason: not valid java name */
    public static /* synthetic */ Object m397performReviewFinishyxL6bBk$hyperkyc_release$default(NetworkRepo networkRepo, String str, FinishReview finishReview, Map map, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            function1 = new Function1<Throwable, Unit>() { // from class: co.hyperverge.hyperkyc.data.network.NetworkRepo$performReviewFinish$2
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }
            };
        }
        return networkRepo.m399performReviewFinishyxL6bBk$hyperkyc_release(str, finishReview, map, function1, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: startTransaction-BWLJW6A$hyperkyc_release, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m400startTransactionBWLJW6A$hyperkyc_release(String str, StartTransaction startTransaction, Map<String, String> map, Continuation<? super Result<Response>> continuation) {
        NetworkRepo$startTransaction$1 networkRepo$startTransaction$1;
        int i;
        if (continuation instanceof NetworkRepo$startTransaction$1) {
            networkRepo$startTransaction$1 = (NetworkRepo$startTransaction$1) continuation;
            if ((networkRepo$startTransaction$1.label & Integer.MIN_VALUE) != 0) {
                networkRepo$startTransaction$1.label -= Integer.MIN_VALUE;
                Object obj = networkRepo$startTransaction$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = networkRepo$startTransaction$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    NetworkRepo$startTransaction$2 networkRepo$startTransaction$2 = new NetworkRepo$startTransaction$2(str, map, startTransaction, null);
                    networkRepo$startTransaction$1.label = 1;
                    obj = CoroutineExtsKt.onIO$default(null, networkRepo$startTransaction$2, networkRepo$startTransaction$1, 1, null);
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
        networkRepo$startTransaction$1 = new NetworkRepo$startTransaction$1(this, continuation);
        Object obj2 = networkRepo$startTransaction$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = networkRepo$startTransaction$1.label;
        if (i != 0) {
        }
        return ((Result) obj2).getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* renamed from: customApiCall-eH_QyT8$hyperkyc_release, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m398customApiCalleH_QyT8$hyperkyc_release(String str, String str2, Map<String, String> map, RequestBody requestBody, long j, SocketFactory socketFactory, int i, Continuation<? super Result<Response>> continuation) {
        NetworkRepo$customApiCall$1 networkRepo$customApiCall$1;
        int i2;
        if (continuation instanceof NetworkRepo$customApiCall$1) {
            networkRepo$customApiCall$1 = (NetworkRepo$customApiCall$1) continuation;
            if ((networkRepo$customApiCall$1.label & Integer.MIN_VALUE) != 0) {
                networkRepo$customApiCall$1.label -= Integer.MIN_VALUE;
                Object obj = networkRepo$customApiCall$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = networkRepo$customApiCall$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    NetworkRepo$customApiCall$2 networkRepo$customApiCall$2 = new NetworkRepo$customApiCall$2(j, socketFactory, str, map, str2, requestBody, i, null);
                    networkRepo$customApiCall$1.label = 1;
                    obj = CoroutineExtsKt.onIO$default(null, networkRepo$customApiCall$2, networkRepo$customApiCall$1, 1, null);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return ((Result) obj).getValue();
            }
        }
        networkRepo$customApiCall$1 = new NetworkRepo$customApiCall$1(this, continuation);
        Object obj2 = networkRepo$customApiCall$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = networkRepo$customApiCall$1.label;
        if (i2 != 0) {
        }
        return ((Result) obj2).getValue();
    }

    /* renamed from: customApiCall-eH_QyT8$hyperkyc_release$default, reason: not valid java name */
    public static /* synthetic */ Object m396customApiCalleH_QyT8$hyperkyc_release$default(NetworkRepo networkRepo, String str, String str2, Map map, RequestBody requestBody, long j, SocketFactory socketFactory, int i, Continuation continuation, int i2, Object obj) {
        SocketFactory socketFactory2;
        Map emptyMap = (i2 & 4) != 0 ? MapsKt.emptyMap() : map;
        RequestBody requestBody2 = (i2 & 8) != 0 ? null : requestBody;
        long j2 = (i2 & 16) != 0 ? 60L : j;
        if ((i2 & 32) != 0) {
            SocketFactory socketFactory3 = SocketFactory.getDefault();
            Intrinsics.checkNotNullExpressionValue(socketFactory3, "getDefault()");
            socketFactory2 = socketFactory3;
        } else {
            socketFactory2 = socketFactory;
        }
        return networkRepo.m398customApiCalleH_QyT8$hyperkyc_release(str, str2, emptyMap, requestBody2, j2, socketFactory2, (i2 & 64) != 0 ? 2 : i, continuation);
    }

    public final Flow<NetworkUIState<TransactionStateResponse>> fetchTransactionState$hyperkyc_release(TransactionStateRequest tStateRequest, Map<String, String> headers) {
        Intrinsics.checkNotNullParameter(tStateRequest, "tStateRequest");
        Intrinsics.checkNotNullParameter(headers, "headers");
        return FlowKt.flowOn(FlowKt.flow(new NetworkRepo$fetchTransactionState$1(headers, tStateRequest, null)), Dispatchers.getIO());
    }

    public final Flow<NetworkUIState<TransactionStateResponse>> pushTransactionState$hyperkyc_release(TransactionState transactionState, Map<String, String> headers) {
        Intrinsics.checkNotNullParameter(transactionState, "transactionState");
        Intrinsics.checkNotNullParameter(headers, "headers");
        return FlowKt.flow(new NetworkRepo$pushTransactionState$1(transactionState, headers, null));
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0174, code lost:
    
        if (r0 != null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void prefetchInternal$hyperkyc_release(Context context, String appId, String workflowId, String languageCode, boolean shouldPreLoadWebSDK, boolean preLoadAll) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(workflowId, "workflowId");
        Intrinsics.checkNotNullParameter(languageCode, "languageCode");
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
        String str3 = "prefetchInternal() called with: context = " + context + ", appId = " + appId + ", workflowId = " + workflowId + ", languageCode = " + languageCode + ", shouldPreLoadWebSDK = " + shouldPreLoadWebSDK;
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
                    String str4 = "prefetchInternal() called with: context = " + context + ", appId = " + appId + ", workflowId = " + workflowId + ", languageCode = " + languageCode + ", shouldPreLoadWebSDK = " + shouldPreLoadWebSDK;
                    if (str4 == null) {
                        str4 = "null ";
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str2, sb2.toString());
                }
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new NetworkRepo$prefetchInternal$2(preLoadAll, context, appId, workflowId, languageCode, shouldPreLoadWebSDK, null), 3, null);
            }
        }
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new NetworkRepo$prefetchInternal$2(preLoadAll, context, appId, workflowId, languageCode, shouldPreLoadWebSDK, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x034f, code lost:
    
        if (r0 != null) goto L142;
     */
    /* JADX WARN: Removed duplicated region for block: B:113:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01c5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x03c2  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x03bf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void prefetchWebSDK(Context context, boolean shouldPreLoadWebSDK) {
        String canonicalName;
        String str;
        Object m1202constructorimpl;
        CharSequence charSequence;
        String str2;
        String canonicalName2;
        String className;
        Object m1202constructorimpl2;
        String str3;
        Class<?> cls;
        String str4;
        Object m1202constructorimpl3;
        String str5;
        String str6;
        String str7;
        Matcher matcher;
        String str8;
        String localizedMessage;
        Class<?> cls2;
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
            Class<?> cls3 = getClass();
            canonicalName = cls3 != null ? cls3.getCanonicalName() : null;
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
        String str10 = "prefetchWebSDK() called with: context = " + context + ", shouldPreLoadWebSDK = " + shouldPreLoadWebSDK;
        if (str10 == null) {
            str10 = "null ";
        }
        sb.append(str10);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            charSequence = "co.hyperverge";
            str = "N/A";
        } else {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                str = "N/A";
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
                    if (!CoreExtsKt.isDebug()) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                str = "N/A";
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName2 = (String) m1202constructorimpl;
            if (!CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                charSequence = "co.hyperverge";
                str2 = "packageName";
                if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls4 = getClass();
                        canonicalName2 = cls4 != null ? cls4.getCanonicalName() : null;
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
                    String str11 = "prefetchWebSDK() called with: context = " + context + ", shouldPreLoadWebSDK = " + shouldPreLoadWebSDK;
                    if (str11 == null) {
                        str11 = "null ";
                    }
                    sb2.append(str11);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                }
                if (shouldPreLoadWebSDK) {
                    return;
                }
                try {
                    Result.Companion companion4 = Result.INSTANCE;
                    NetworkRepo networkRepo = this;
                    WebView webView = new WebView(context);
                    WebSettings settings = webView.getSettings();
                    webView.setWebViewClient(new WebViewClient() { // from class: co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchWebSDK$2$1$1
                        @Override // android.webkit.WebViewClient
                        public void onPageFinished(WebView view, String url) {
                            String canonicalName3;
                            Object m1202constructorimpl4;
                            String className5;
                            String substringAfterLast$default;
                            String className6;
                            super.onPageFinished(view, url);
                            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                            HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb3 = new StringBuilder();
                            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                            String str12 = "N/A";
                            if (stackTraceElement3 == null || (className6 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls5 = getClass();
                                canonicalName3 = cls5 != null ? cls5.getCanonicalName() : null;
                                if (canonicalName3 == null) {
                                    canonicalName3 = "N/A";
                                }
                            }
                            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                            if (matcher4.find()) {
                                canonicalName3 = matcher4.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                            }
                            if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                canonicalName3 = canonicalName3.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            sb3.append(canonicalName3);
                            sb3.append(" - ");
                            sb3.append("prefetchWebSDK successful");
                            sb3.append(' ');
                            sb3.append("");
                            companion5.log(level2, sb3.toString());
                            if (CoreExtsKt.isRelease()) {
                                return;
                            }
                            try {
                                Result.Companion companion6 = Result.INSTANCE;
                                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                                m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                            } catch (Throwable th3) {
                                Result.Companion companion7 = Result.INSTANCE;
                                m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                m1202constructorimpl4 = "";
                            }
                            String packageName3 = (String) m1202constructorimpl4;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                                if (StringsKt.contains$default((CharSequence) packageName3, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                    if (stackTraceElement4 == null || (className5 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                        Class<?> cls6 = getClass();
                                        String canonicalName4 = cls6 != null ? cls6.getCanonicalName() : null;
                                        if (canonicalName4 != null) {
                                            str12 = canonicalName4;
                                        }
                                    } else {
                                        str12 = substringAfterLast$default;
                                    }
                                    Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str12);
                                    if (matcher5.find()) {
                                        str12 = matcher5.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str12, "replaceAll(\"\")");
                                    }
                                    if (str12.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str12 = str12.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str12, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    Log.println(3, str12, "prefetchWebSDK successful ");
                                }
                            }
                        }
                    });
                    settings.setJavaScriptCanOpenWindowsAutomatically(true);
                    settings.setMediaPlaybackRequiresUserGesture(false);
                    settings.setDomStorageEnabled(true);
                    settings.setLoadWithOverviewMode(true);
                    settings.setAllowFileAccess(true);
                    settings.setAllowContentAccess(true);
                    settings.setDatabaseEnabled(true);
                    settings.setCacheMode(1);
                    webView.loadUrl(HyperKyc.FORM_WEB_VIEW_URL);
                    m1202constructorimpl2 = Result.m1202constructorimpl(Unit.INSTANCE);
                } catch (Throwable th3) {
                    Result.Companion companion5 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                }
                Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl2);
                if (m1205exceptionOrNullimpl != null) {
                    NetworkRepo networkRepo2 = INSTANCE;
                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                    HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        String canonicalName3 = (networkRepo2 == null || (cls = networkRepo2.getClass()) == null) ? null : cls.getCanonicalName();
                        str3 = canonicalName3 == null ? str : canonicalName3;
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
                    String str12 = "prefetchWebSDK failed: " + m1205exceptionOrNullimpl;
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
                    companion6.log(level2, sb3.toString());
                    CoreExtsKt.isRelease();
                    try {
                        Result.Companion companion7 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th4) {
                        Result.Companion companion8 = Result.INSTANCE;
                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th4));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                        m1202constructorimpl3 = "";
                    }
                    String str13 = (String) m1202constructorimpl3;
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
                            str6 = (networkRepo2 == null || (cls2 = networkRepo2.getClass()) == null) ? str5 : cls2.getCanonicalName();
                            if (str6 == null) {
                                str7 = str;
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
                                str8 = "prefetchWebSDK failed: " + m1205exceptionOrNullimpl;
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
                                return;
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
                            str8 = "prefetchWebSDK failed: " + m1205exceptionOrNullimpl;
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
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            charSequence = "co.hyperverge";
        }
        str2 = "packageName";
        if (shouldPreLoadWebSDK) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0120, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void resetPrefetchDataAndRawConfigJSONs$hyperkyc_release() {
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
        sb.append("resetPrefetchDataAndRawConfigJSONs() called");
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
                    Log.println(3, str, "resetPrefetchDataAndRawConfigJSONs() called ");
                }
            }
        }
        prefetchCountriesDeferred = null;
        prefetchRemoteConfigDeferred = null;
        prefetchWorkflowConfigDeferred = null;
        prefetchTextConfigDeferred = null;
        prefetchUIConfigDeferred = null;
        FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().setWorkflowConfig$hyperkyc_release(null);
        FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().setTextConfig$hyperkyc_release(null);
        FormWebViewDriver.INSTANCE.getJsonConfigStore$hyperkyc_release().setUiConfig$hyperkyc_release(null);
    }
}
