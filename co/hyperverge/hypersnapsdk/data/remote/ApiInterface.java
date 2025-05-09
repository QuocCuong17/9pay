package co.hyperverge.hypersnapsdk.data.remote;

import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

/* loaded from: classes2.dex */
public interface ApiInterface {
    @GET
    Call<ResponseBody> ipAddressToGeo(@Url String str, @HeaderMap Map<String, String> map);

    @POST
    Call<ResponseBody> matchingAPI(@Url String str, @HeaderMap Map<String, String> map, @Body RequestBody requestBody);

    @POST
    @Multipart
    Call<ResponseBody> ocrCall(@Url String str, @HeaderMap Map<String, String> map, @Part MultipartBody.Part part, @PartMap Map<String, RequestBody> map2);

    @POST
    @Multipart
    Call<ResponseBody> ocrPlusQRCall(@Url String str, @HeaderMap Map<String, String> map, @Part MultipartBody.Part part, @Part MultipartBody.Part part2, @PartMap Map<String, RequestBody> map2);

    @POST
    @Multipart
    Call<ResponseBody> singleImageUpload(@Url String str, @HeaderMap Map<String, String> map, @Part MultipartBody.Part part, @PartMap Map<String, RequestBody> map2);

    @POST
    @Multipart
    Call<ResponseBody> uploadImage(@Url String str, @HeaderMap Map<String, String> map, @Part MultipartBody.Part part, @PartMap Map<String, RequestBody> map2);

    @POST
    @Multipart
    Call<ResponseBody> uploadImageAndVideo(@Url String str, @HeaderMap Map<String, String> map, @Part MultipartBody.Part part, @Part MultipartBody.Part part2, @PartMap Map<String, RequestBody> map2);

    @POST
    Call<ResponseBody> validationAPICall(@Url String str, @HeaderMap Map<String, String> map, @Body RequestBody requestBody);

    @POST
    @Multipart
    Call<ResponseBody> verifyAlignment(@Url String str, @HeaderMap Map<String, String> map, @Part MultipartBody.Part part, @PartMap Map<String, RequestBody> map2);

    @POST
    @Multipart
    Call<ResponseBody> verifyPair(@Url String str, @HeaderMap Map<String, String> map, @Part MultipartBody.Part part, @Part MultipartBody.Part part2, @PartMap Map<String, RequestBody> map2);
}
