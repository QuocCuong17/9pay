package co.hyperverge.hypersnapsdk.data;

import android.content.Context;
import co.hyperverge.hypersnapsdk.listeners.APICompletionCallback;
import co.hyperverge.hypersnapsdk.model.BaseResponse;
import co.hyperverge.hypersnapsdk.objects.HVDocConfig;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.objects.HyperSnapParams;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class DataSource {

    /* loaded from: classes2.dex */
    public interface NetworkCallback {
        void onFailure(int i, String str);

        void onNetworkFailure();

        void onSuccess(BaseResponse baseResponse);
    }

    public abstract void getLivenessTextureScore(String str, String str2, String str3, List<Integer> list, HVFaceConfig hVFaceConfig, NetworkCallback networkCallback);

    public abstract void ipAddressToGeo(APICompletionCallback aPICompletionCallback);

    public abstract void makeFaceAuthAPICall(Context context, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback);

    public abstract void makeFaceDedupeAPICall(Context context, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback);

    public abstract void makeFaceMatchCall(Context context, String str, String str2, String str3, HyperSnapParams.FaceMatchMode faceMatchMode, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback);

    public abstract void makeOcrCall(Context context, String str, String str2, String str3, HVDocConfig hVDocConfig, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback);

    public abstract void makeTextMatchAPICall(Context context, String str, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback);

    public abstract void makeValidationAPICall(Context context, String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, Boolean bool, List<Integer> list, APICompletionCallback aPICompletionCallback);

    public abstract void makeVerifyDocAlignmentCall(Context context, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback);

    public abstract void postAnalyticsData(Context context, String str, Map<String, Object> map);

    public abstract void postSensorDataFiles(Context context, String str, File file);

    public abstract void verifyPair(String str, String str2, NetworkCallback networkCallback);
}
