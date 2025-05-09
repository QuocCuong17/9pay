package co.hyperverge.hypersnapsdk.analytics.mixpanel.network;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

@Deprecated
/* loaded from: classes2.dex */
public interface MixPanelApiInterface {
    @Headers({"Accept: text/plain", "Content-Type: application/x-www-form-urlencoded"})
    @POST("#past-events-batch")
    Call<Object> postEvents(@Query("data") String str);
}
