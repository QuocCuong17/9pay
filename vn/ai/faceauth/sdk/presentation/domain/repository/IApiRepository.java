package vn.ai.faceauth.sdk.presentation.domain.repository;

import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import vn.ai.faceauth.sdk.presentation.domain.mapper.Result;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001JS\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00042\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H¦@ø\u0001\u0000¢\u0006\u0002\u0010\tJS\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00042\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H¦@ø\u0001\u0000¢\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/repository/IApiRepository;", "", "accessToken", "Lvn/ai/faceauth/sdk/presentation/domain/mapper/Result;", "", "", "url", "headers", "body", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "liveNess", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface IApiRepository {
    Object accessToken(String str, Map<String, ? extends Object> map, Map<String, ? extends Object> map2, Continuation<? super Result<? extends Map<String, ? extends Object>>> continuation);

    Object liveNess(String str, Map<String, ? extends Object> map, Map<String, ? extends Object> map2, Continuation<? super Result<? extends Map<String, ? extends Object>>> continuation);
}
