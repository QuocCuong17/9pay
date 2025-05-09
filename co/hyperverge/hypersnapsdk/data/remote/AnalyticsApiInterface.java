package co.hyperverge.hypersnapsdk.data.remote;

import java.util.Map;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;

/* loaded from: classes2.dex */
public interface AnalyticsApiInterface {
    @POST
    Call<ResponseBody> postAnalyticsData(@Url String str, @HeaderMap Map<String, String> map, @Body RequestBody requestBody);
}
