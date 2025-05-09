package co.hyperverge.hyperkyc.utils.extensions;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

/* compiled from: CoroutineExts.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aJ\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032'\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001aJ\u0010\u000b\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032'\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001aJ\u0010\f\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032'\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"onDefault", "T", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onIO", "onUI", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CoroutineExtsKt {
    public static /* synthetic */ Object onUI$default(CoroutineDispatcher coroutineDispatcher, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineDispatcher = Dispatchers.getMain();
        }
        return onUI(coroutineDispatcher, function2, continuation);
    }

    public static final /* synthetic */ Object onUI(CoroutineDispatcher coroutineDispatcher, Function2 function2, Continuation continuation) {
        return BuildersKt.withContext(coroutineDispatcher, new CoroutineExtsKt$onUI$2(function2, null), continuation);
    }

    public static /* synthetic */ Object onIO$default(CoroutineDispatcher coroutineDispatcher, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineDispatcher = Dispatchers.getIO();
        }
        return onIO(coroutineDispatcher, function2, continuation);
    }

    public static final /* synthetic */ Object onIO(CoroutineDispatcher coroutineDispatcher, Function2 function2, Continuation continuation) {
        return BuildersKt.withContext(coroutineDispatcher, new CoroutineExtsKt$onIO$2(function2, null), continuation);
    }

    public static /* synthetic */ Object onDefault$default(CoroutineDispatcher coroutineDispatcher, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineDispatcher = Dispatchers.getDefault();
        }
        return onDefault(coroutineDispatcher, function2, continuation);
    }

    public static final /* synthetic */ Object onDefault(CoroutineDispatcher coroutineDispatcher, Function2 function2, Continuation continuation) {
        return BuildersKt.withContext(coroutineDispatcher, new CoroutineExtsKt$onDefault$2(function2, null), continuation);
    }
}
