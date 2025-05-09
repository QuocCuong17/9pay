package vn.ai.faceauth.sdk.presentation.domain.repository;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "vn.ai.faceauth.sdk.presentation.domain.repository.BaseRepository", f = "BaseRepository.kt", i = {}, l = {11}, m = "safeCall", n = {}, s = {})
/* loaded from: classes6.dex */
public final class BaseRepository$safeCall$1<T> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BaseRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseRepository$safeCall$1(BaseRepository baseRepository, Continuation<? super BaseRepository$safeCall$1> continuation) {
        super(continuation);
        this.this$0 = baseRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.safeCall(null, this);
    }
}
