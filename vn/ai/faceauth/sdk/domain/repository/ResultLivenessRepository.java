package vn.ai.faceauth.sdk.domain.repository;

import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H&J:\u0010\b\u001a\u00020\u00042\u001a\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nj\n\u0012\u0004\u0012\u00020\u000b\u0018\u0001`\f2\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000eH&¨\u0006\u000f"}, d2 = {"Lvn/ai/faceauth/sdk/domain/repository/ResultLivenessRepository;", "RESULT", "", "error", "", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "finished", "rawImage", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "successLivenessResponse", "", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface ResultLivenessRepository<RESULT> {
    void error(Exception exception);

    void finished(ArrayList<String> rawImage, Map<String, ? extends Object> successLivenessResponse);
}
