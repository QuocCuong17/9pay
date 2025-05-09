package vn.ai.faceauth.sdk.presentation.domain.mapper;

import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.presentation.domain.mapper.Result;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a`\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00032@\u0010\u0004\u001a<\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u001b\u0012\u0019\u0018\u00010\nj\u0004\u0018\u0001`\u000b¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00010\u0005H\u0086\bø\u0001\u0000\u001aA\u0010\r\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00032!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00010\u000eH\u0086\bø\u0001\u0000\u001at\u0010\u0010\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u000e2@\u0010\u0012\u001a<\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u001b\u0012\u0019\u0018\u00010\nj\u0004\u0018\u0001`\u000b¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00010\u0005H\u0086\bø\u0001\u0000\u001aR\u0010\u0013\u001a\u0004\u0018\u0001H\u0002\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00032+\u0010\u0014\u001a'\u0012\u001b\u0012\u0019\u0018\u00010\nj\u0004\u0018\u0001`\u000b¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u000eH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\"\u0010\u0016\u001a\u0004\u0018\u0001H\u0002\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086\b¢\u0006\u0002\u0010\u0017\u001a@\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00190\u0003\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0019\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00190\u000eH\u0086\bø\u0001\u0000\u001a0\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u001c\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u001dH\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001e"}, d2 = {"doIfFailure", "", "T", "Lvn/ai/faceauth/sdk/presentation/domain/mapper/Result;", "callback", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "message", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "doIfSuccess", "Lkotlin/Function1;", "value", "fold", "success", "failure", "getOrElse", "fallback", "(Lvn/ai/faceauth/sdk/presentation/domain/mapper/Result;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "getOrNull", "(Lvn/ai/faceauth/sdk/presentation/domain/mapper/Result;)Ljava/lang/Object;", "map", "R", "transform", "withDefault", "Lvn/ai/faceauth/sdk/presentation/domain/mapper/Result$Success;", "Lkotlin/Function0;", "trueface_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ResultExtensionKt {
    public static final /* synthetic */ <T> void doIfFailure(Result<? extends T> result, Function2<? super String, ? super Exception, Unit> function2) {
        Intrinsics.checkNotNullParameter(result, tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_GRAY_RESPONSE_UNIT));
        Intrinsics.checkNotNullParameter(function2, tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_GRAY_RESPONSE_CURVE));
        if (result instanceof Result.Error) {
            Result.Error error = (Result.Error) result;
            function2.invoke(error.getMessage(), error.getException());
        }
    }

    public static final /* synthetic */ <T> void doIfSuccess(Result<? extends T> result, Function1<? super T, Unit> function1) {
        Intrinsics.checkNotNullParameter(result, tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_T4_OPTIONS));
        Intrinsics.checkNotNullParameter(function1, tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_T6_OPTIONS));
        if (result instanceof Result.Success) {
            function1.invoke((Object) ((Result.Success) result).getData());
        }
    }

    public static final /* synthetic */ <T> void fold(Result<? extends T> result, Function1<? super T, Unit> function1, Function2<? super String, ? super Exception, Unit> function2) {
        Intrinsics.checkNotNullParameter(result, tfwhgw.rnigpa(294));
        Intrinsics.checkNotNullParameter(function1, tfwhgw.rnigpa(295));
        Intrinsics.checkNotNullParameter(function2, tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_RESOLUTION_UNIT));
        if (result instanceof Result.Success) {
            function1.invoke((Object) ((Result.Success) result).getData());
        } else if (result instanceof Result.Error) {
            Result.Error error = (Result.Error) result;
            function2.invoke(error.getMessage(), error.getException());
        }
    }

    public static final /* synthetic */ <T> T getOrElse(Result<? extends T> result, Function1<? super Exception, ? extends T> function1) {
        Intrinsics.checkNotNullParameter(result, tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_PAGE_NUMBER));
        Intrinsics.checkNotNullParameter(function1, tfwhgw.rnigpa(298));
        if (result instanceof Result.Success) {
            return (T) ((Result.Success) result).getData();
        }
        if (result instanceof Result.Error) {
            return function1.invoke(((Result.Error) result).getException());
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final /* synthetic */ <T> T getOrNull(Result<? extends T> result) {
        Intrinsics.checkNotNullParameter(result, tfwhgw.rnigpa(299));
        if (result instanceof Result.Success) {
            return (T) ((Result.Success) result).getData();
        }
        if (!(result instanceof Result.Error)) {
            throw new NoWhenBranchMatchedException();
        }
        ((Result.Error) result).getException();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final /* synthetic */ <T, R> Result<R> map(Result<? extends T> result, Function1<? super T, ? extends R> function1) {
        Intrinsics.checkNotNullParameter(result, tfwhgw.rnigpa(300));
        Intrinsics.checkNotNullParameter(function1, tfwhgw.rnigpa(301));
        try {
            if (result instanceof Result.Success) {
                return new Result.Success(function1.invoke((Object) ((Result.Success) result).getData()));
            }
            if (result instanceof Result.Error) {
                return result;
            }
            throw new NoWhenBranchMatchedException();
        } catch (Exception e) {
            return new Result.Error(e.getMessage(), e);
        }
    }

    public static final <T> Result.Success<T> withDefault(Result<? extends T> result, Function0<? extends T> function0) {
        Intrinsics.checkNotNullParameter(result, tfwhgw.rnigpa(302));
        Intrinsics.checkNotNullParameter(function0, tfwhgw.rnigpa(303));
        if (result instanceof Result.Success) {
            return (Result.Success) result;
        }
        if (result instanceof Result.Error) {
            return new Result.Success<>(function0.invoke());
        }
        throw new NoWhenBranchMatchedException();
    }
}
