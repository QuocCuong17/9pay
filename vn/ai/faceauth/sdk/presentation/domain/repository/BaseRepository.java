package vn.ai.faceauth.sdk.presentation.domain.repository;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.presentation.domain.mapper.Result;
import vn.ai.faceauth.sdk.presentation.domain.network.ApiException;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0006\u001a\u0002H\u0005H\u0002¢\u0006\u0002\u0010\u0007J;\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u001c\u0010\t\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\nH\u0084@ø\u0001\u0000¢\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/repository/BaseRepository;", "", "()V", "mapResponseToResult", "Lvn/ai/faceauth/sdk/presentation/domain/mapper/Result;", "T", "response", "(Ljava/lang/Object;)Lvn/ai/faceauth/sdk/presentation/domain/mapper/Result;", "safeCall", NotificationCompat.CATEGORY_CALL, "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public class BaseRepository {
    private final <T> Result<T> mapResponseToResult(T response) {
        if (!(response instanceof Map)) {
            return new Result.Success(response);
        }
        Map map = (Map) response;
        Double d = (Double) map.get(tfwhgw.rnigpa(17));
        String str = (String) map.get(tfwhgw.rnigpa(18));
        if (d == null || ((int) d.doubleValue()) != 0) {
            return new Result.Success(response);
        }
        int doubleValue = (int) d.doubleValue();
        if (str == null) {
            str = "";
        }
        throw new ApiException(doubleValue, str, response);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final <T> Object safeCall(Function1<? super Continuation<? super T>, ? extends Object> function1, Continuation<? super Result<? extends T>> continuation) {
        BaseRepository$safeCall$1 baseRepository$safeCall$1;
        int i;
        BaseRepository baseRepository;
        try {
            if (continuation instanceof BaseRepository$safeCall$1) {
                baseRepository$safeCall$1 = (BaseRepository$safeCall$1) continuation;
                int i2 = baseRepository$safeCall$1.label;
                if ((i2 & Integer.MIN_VALUE) != 0) {
                    baseRepository$safeCall$1.label = i2 - 2147483648;
                    Object obj = baseRepository$safeCall$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = baseRepository$safeCall$1.label;
                    if (i != 0) {
                        ResultKt.throwOnFailure(obj);
                        baseRepository$safeCall$1.L$0 = this;
                        baseRepository$safeCall$1.label = 1;
                        obj = function1.invoke(baseRepository$safeCall$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        baseRepository = this;
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException(tfwhgw.rnigpa(19));
                        }
                        BaseRepository baseRepository2 = (BaseRepository) baseRepository$safeCall$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        baseRepository = baseRepository2;
                    }
                    return baseRepository.mapResponseToResult(obj);
                }
            }
            if (i != 0) {
            }
            return baseRepository.mapResponseToResult(obj);
        } catch (Exception e) {
            Log.e(tfwhgw.rnigpa(20), e.toString());
            String message = e.getMessage();
            if (message == null) {
                message = tfwhgw.rnigpa(21);
            }
            return new Result.Error(message, e);
        }
        baseRepository$safeCall$1 = new BaseRepository$safeCall$1(this, continuation);
        Object obj2 = baseRepository$safeCall$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = baseRepository$safeCall$1.label;
    }
}
