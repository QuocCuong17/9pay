package co.hyperverge.hypersnapsdk.data.remote;

import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Url;

/* loaded from: classes2.dex */
public interface SensorDataInterface {
    @PUT
    @Multipart
    Call<ResponseBody> postSensorData(@Url String str, @HeaderMap Map<String, String> map, @Part MultipartBody.Part part);
}
