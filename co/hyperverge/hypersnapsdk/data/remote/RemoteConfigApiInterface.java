package co.hyperverge.hypersnapsdk.data.remote;

import co.hyperverge.hypersnapsdk.data.DefaultRemoteConfig;
import co.hyperverge.hypersnapsdk.data.RemoteConfig;
import co.hyperverge.hypersnapsdk.data.models.FeatureConfigResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/* loaded from: classes2.dex */
public interface RemoteConfigApiInterface {
    @GET
    Call<DefaultRemoteConfig> getDefaultRemoteConfig(@Url String str);

    @GET
    Call<FeatureConfigResponse> getFeatureRemoteConfig(@Url String str);

    @GET
    Call<RemoteConfig> getRemoteConfig(@Url String str);
}
