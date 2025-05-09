package co.hyperverge.hyperkyc.data.network;

import co.hyperverge.hyperkyc.utils.extensions.CoroutineExtsKt;
import java.io.File;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NetworkHelpers.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\u001ar\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fH\u0080@ø\u0001\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001af\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fH\u0080@ø\u0001\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001az\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fH\u0080@ø\u0001\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0017\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"GET", "", "POST", "apiGet", "Lkotlin/Result;", "Lokhttp3/Response;", "url", "headers", "", "body", "cacheDir", "Ljava/io/File;", "cacheExpiryInSeconds", "", "onFailure", "Lkotlin/Function1;", "", "", "(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/io/File;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "apiPost", "(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/io/File;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "makeApiCall", "method", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/io/File;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NetworkHelpersKt {
    private static final String GET = "GET";
    private static final String POST = "POST";

    /* JADX WARN: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object apiPost(String str, Map map, String str2, File file, Function1 function1, Continuation continuation) {
        NetworkHelpersKt$apiPost$1 networkHelpersKt$apiPost$1;
        int i;
        if (continuation instanceof NetworkHelpersKt$apiPost$1) {
            networkHelpersKt$apiPost$1 = (NetworkHelpersKt$apiPost$1) continuation;
            if ((networkHelpersKt$apiPost$1.label & Integer.MIN_VALUE) != 0) {
                networkHelpersKt$apiPost$1.label -= Integer.MIN_VALUE;
                NetworkHelpersKt$apiPost$1 networkHelpersKt$apiPost$12 = networkHelpersKt$apiPost$1;
                Object obj = networkHelpersKt$apiPost$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = networkHelpersKt$apiPost$12.label;
                if (i == 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return ((Result) obj).getValue();
                }
                ResultKt.throwOnFailure(obj);
                networkHelpersKt$apiPost$12.label = 1;
                Object makeApiCall$default = makeApiCall$default("POST", str, map, str2, file, null, function1, networkHelpersKt$apiPost$12, 32, null);
                return makeApiCall$default == coroutine_suspended ? coroutine_suspended : makeApiCall$default;
            }
        }
        networkHelpersKt$apiPost$1 = new NetworkHelpersKt$apiPost$1(continuation);
        NetworkHelpersKt$apiPost$1 networkHelpersKt$apiPost$122 = networkHelpersKt$apiPost$1;
        Object obj2 = networkHelpersKt$apiPost$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = networkHelpersKt$apiPost$122.label;
        if (i == 0) {
        }
    }

    public static /* synthetic */ Object apiPost$default(String str, Map map, String str2, File file, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            map = MapsKt.emptyMap();
        }
        Map map2 = map;
        String str3 = (i & 4) != 0 ? null : str2;
        File file2 = (i & 8) != 0 ? null : file;
        if ((i & 16) != 0) {
            function1 = new Function1<Throwable, Unit>() { // from class: co.hyperverge.hyperkyc.data.network.NetworkHelpersKt$apiPost$2
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
        return apiPost(str, map2, str3, file2, function1, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object apiGet(String str, Map map, String str2, File file, Integer num, Function1 function1, Continuation continuation) {
        NetworkHelpersKt$apiGet$1 networkHelpersKt$apiGet$1;
        int i;
        if (continuation instanceof NetworkHelpersKt$apiGet$1) {
            networkHelpersKt$apiGet$1 = (NetworkHelpersKt$apiGet$1) continuation;
            if ((networkHelpersKt$apiGet$1.label & Integer.MIN_VALUE) != 0) {
                networkHelpersKt$apiGet$1.label -= Integer.MIN_VALUE;
                NetworkHelpersKt$apiGet$1 networkHelpersKt$apiGet$12 = networkHelpersKt$apiGet$1;
                Object obj = networkHelpersKt$apiGet$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = networkHelpersKt$apiGet$12.label;
                if (i == 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return ((Result) obj).getValue();
                }
                ResultKt.throwOnFailure(obj);
                networkHelpersKt$apiGet$12.label = 1;
                Object makeApiCall = makeApiCall("GET", str, map, str2, file, num, function1, networkHelpersKt$apiGet$12);
                return makeApiCall == coroutine_suspended ? coroutine_suspended : makeApiCall;
            }
        }
        networkHelpersKt$apiGet$1 = new NetworkHelpersKt$apiGet$1(continuation);
        NetworkHelpersKt$apiGet$1 networkHelpersKt$apiGet$122 = networkHelpersKt$apiGet$1;
        Object obj2 = networkHelpersKt$apiGet$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = networkHelpersKt$apiGet$122.label;
        if (i == 0) {
        }
    }

    public static /* synthetic */ Object apiGet$default(String str, Map map, String str2, File file, Integer num, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            map = MapsKt.emptyMap();
        }
        Map map2 = map;
        String str3 = (i & 4) != 0 ? null : str2;
        File file2 = (i & 8) != 0 ? null : file;
        Integer num2 = (i & 16) != 0 ? null : num;
        if ((i & 32) != 0) {
            function1 = new Function1<Throwable, Unit>() { // from class: co.hyperverge.hyperkyc.data.network.NetworkHelpersKt$apiGet$2
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
        return apiGet(str, map2, str3, file2, num2, function1, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object makeApiCall(String str, String str2, Map map, String str3, File file, Integer num, Function1 function1, Continuation continuation) {
        NetworkHelpersKt$makeApiCall$1 networkHelpersKt$makeApiCall$1;
        int i;
        if (continuation instanceof NetworkHelpersKt$makeApiCall$1) {
            networkHelpersKt$makeApiCall$1 = (NetworkHelpersKt$makeApiCall$1) continuation;
            if ((networkHelpersKt$makeApiCall$1.label & Integer.MIN_VALUE) != 0) {
                networkHelpersKt$makeApiCall$1.label -= Integer.MIN_VALUE;
                Object obj = networkHelpersKt$makeApiCall$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = networkHelpersKt$makeApiCall$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    NetworkHelpersKt$makeApiCall$3 networkHelpersKt$makeApiCall$3 = new NetworkHelpersKt$makeApiCall$3(str2, map, str, str3, file, num, function1, null);
                    networkHelpersKt$makeApiCall$1.label = 1;
                    obj = CoroutineExtsKt.onIO$default(null, networkHelpersKt$makeApiCall$3, networkHelpersKt$makeApiCall$1, 1, null);
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
        networkHelpersKt$makeApiCall$1 = new NetworkHelpersKt$makeApiCall$1(continuation);
        Object obj2 = networkHelpersKt$makeApiCall$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = networkHelpersKt$makeApiCall$1.label;
        if (i != 0) {
        }
        return ((Result) obj2).getValue();
    }

    public static /* synthetic */ Object makeApiCall$default(String str, String str2, Map map, String str3, File file, Integer num, Function1 function1, Continuation continuation, int i, Object obj) {
        return makeApiCall(str, str2, (i & 4) != 0 ? MapsKt.emptyMap() : map, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : file, (i & 32) != 0 ? null : num, (i & 64) != 0 ? new Function1<Throwable, Unit>() { // from class: co.hyperverge.hyperkyc.data.network.NetworkHelpersKt$makeApiCall$2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }
        } : function1, continuation);
    }
}
