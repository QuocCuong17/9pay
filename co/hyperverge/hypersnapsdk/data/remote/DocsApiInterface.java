package co.hyperverge.hypersnapsdk.data.remote;

import co.hyperverge.hypersnapsdk.model.GestureResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/* loaded from: classes2.dex */
public interface DocsApiInterface {
    @POST
    @Multipart
    Call<GestureResponse> verifyPair(@Url String str, @Header("appId") String str2, @Header("appKey") String str3, @Part MultipartBody.Part part, @Part MultipartBody.Part part2, @Part("type") RequestBody requestBody);
}
