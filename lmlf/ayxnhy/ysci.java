package lmlf.ayxnhy;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes5.dex */
public class ysci implements Interceptor {
    public klcf bjjyrm;

    public ysci(klcf klcfVar) {
        this.bjjyrm = klcfVar;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        this.bjjyrm.smysce(request.url().host());
        return chain.proceed(request);
    }
}
