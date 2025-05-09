package co.hyperverge.hypersnapsdk.data;

import android.content.Context;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.data.DataSource;
import co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource;
import co.hyperverge.hypersnapsdk.helpers.NetworkHelper;
import co.hyperverge.hypersnapsdk.listeners.APICompletionCallback;
import co.hyperverge.hypersnapsdk.objects.HVDocConfig;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.objects.HyperSnapParams;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class DataRepository {
    private static DataRepository dataRepository;
    private final String TAG = getClass().getSimpleName();
    private final DataSource remoteDataSource = RemoteDataSource.getInstance();

    public static synchronized DataRepository getInstance() {
        DataRepository dataRepository2;
        synchronized (DataRepository.class) {
            if (dataRepository == null) {
                dataRepository = new DataRepository();
            }
            dataRepository2 = dataRepository;
        }
        return dataRepository2;
    }

    public void getLivenessTextureScore(Context context, String str, String str2, String str3, List<Integer> list, HVFaceConfig hVFaceConfig, DataSource.NetworkCallback networkCallback) {
        HVLogUtils.d(this.TAG, "getLivenessTextureScore() called with: context = [" + context + "], filePath = [" + str + "], croppedFacePath = [" + str2 + "], videoUri = [" + str3 + "], faceCoords = [" + list + "], hvFaceConfig = [" + hVFaceConfig + "], callback = [" + networkCallback + "]");
        if (context == null) {
            networkCallback.onFailure(2, "context cannot be null");
        } else if (!NetworkHelper.isNetworkAvailable(context)) {
            networkCallback.onNetworkFailure();
        } else {
            this.remoteDataSource.getLivenessTextureScore(str, str2, str3, list, hVFaceConfig, networkCallback);
        }
    }

    public void verifyPair(Context context, String str, String str2, DataSource.NetworkCallback networkCallback) {
        HVLogUtils.d(this.TAG, "verifyPair() called with: context = [" + context + "], imageFilePath1 = [" + str + "], imageFilePath2 = [" + str2 + "], callback = [" + networkCallback + "]");
        if (context == null) {
            networkCallback.onFailure(6, "context cannot be null");
        } else if (!NetworkHelper.isNetworkAvailable(context)) {
            networkCallback.onNetworkFailure();
        } else {
            this.remoteDataSource.verifyPair(str, str2, networkCallback);
        }
    }

    public void makeOcrCall(Context context, String str, String str2, String str3, HVDocConfig hVDocConfig, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        HVLogUtils.d(this.TAG, "makeOcrCall() called with: context = [" + context + "], url = [" + str + "], documentUri = [" + str2 + "], qrCodeCroppedImageUri = [" + str3 + "], hvDocConfig = [" + hVDocConfig + "], parameters = [" + jSONObject + "], headers = [" + jSONObject2 + "], allowedStatusCodes = [" + list + "], callback = [" + aPICompletionCallback + "]");
        if (!NetworkHelper.isNetworkAvailable(context)) {
            aPICompletionCallback.onResult(new HVError(12, context.getString(R.string.network_error)), null);
        } else {
            this.remoteDataSource.makeOcrCall(context, str, str2, str3, hVDocConfig, jSONObject, jSONObject2, list, aPICompletionCallback);
        }
    }

    public void makeFaceMatchCall(Context context, String str, String str2, String str3, HyperSnapParams.FaceMatchMode faceMatchMode, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        HVLogUtils.d(this.TAG, "makeFaceMatchCall() called with: context = [" + context + "], url = [" + str + "], image1 = [" + str2 + "], image2 = [" + str3 + "], hyperSnapFaceMatchMode = [" + faceMatchMode + "], parameters = [" + jSONObject + "], headers = [" + jSONObject2 + "], allowedStatusCodes = [" + list + "], callback = [" + aPICompletionCallback + "]");
        this.remoteDataSource.makeFaceMatchCall(context, str, str2, str3, faceMatchMode, jSONObject, jSONObject2, list, aPICompletionCallback);
    }

    public void makeVerifyDocAlignmentCall(Context context, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        HVLogUtils.d(this.TAG, "makeVerifyDocAlignmentCall() called with: context = [" + context + "], url = [" + str + "], documentUri = [" + str2 + "], parameters = [" + jSONObject + "], headers = [" + jSONObject2 + "], allowedStatusCodes = [" + list + "], callback = [" + aPICompletionCallback + "]");
        this.remoteDataSource.makeVerifyDocAlignmentCall(context, str, str2, jSONObject, jSONObject2, list, aPICompletionCallback);
    }

    public void makeTextMatchAPICall(Context context, String str, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        HVLogUtils.d(this.TAG, "makeTextMatchAPICall() called with: context = [" + context + "], url = [" + str + "], parameters = [" + jSONObject + "], headers = [" + jSONObject2 + "], allowedStatusCodes = [" + list + "], callback = [" + aPICompletionCallback + "]");
        this.remoteDataSource.makeTextMatchAPICall(context, str, jSONObject, jSONObject2, list, aPICompletionCallback);
    }

    public void makeValidationAPICall(Context context, String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, Boolean bool, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        HVLogUtils.d(this.TAG, "makeValidationAPICall() called with: context = [" + context + "], endpoint = [" + str + "], userInput = [" + jSONObject + "], ocrResultFront = [" + jSONObject2 + "], ocrResultBack = [" + jSONObject3 + "], qr = [" + jSONObject4 + "], checkDatabase = [" + bool + "], allowedStatusCodes = [" + list + "], callback = [" + aPICompletionCallback + "]");
        this.remoteDataSource.makeValidationAPICall(context, str, jSONObject, jSONObject2, jSONObject3, jSONObject4, bool, list, aPICompletionCallback);
    }

    public void postSensorDataFiles(Context context, String str, File file) {
        HVLogUtils.d(this.TAG, "postSensorDataFiles() called with: context = [" + context + "], endpoint = [" + str + "], sensorDataZipFile = [" + file + "]");
        this.remoteDataSource.postSensorDataFiles(context, str, file);
    }

    public void postAnalyticsData(Context context, String str, Map<String, Object> map) {
        HVLogUtils.d(this.TAG, "postAnalyticsData() called with: context = [" + context + "], endpoint = [" + str + "], analyticsData = [" + map + "]");
        this.remoteDataSource.postAnalyticsData(context, str, map);
    }

    public void ipAddressToGeo(APICompletionCallback aPICompletionCallback) {
        HVLogUtils.d(this.TAG, "ipAddressToGeo() called with: callback = [" + aPICompletionCallback + "]");
        this.remoteDataSource.ipAddressToGeo(aPICompletionCallback);
    }

    public void makeFaceDedupeAPICall(Context context, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        HVLogUtils.d(this.TAG, "makeFaceDedupeAPICall() called with: context = [" + context + "], endpoint = [" + str + "], selfieImageURL = [" + str2 + "], parameters = [" + jSONObject + "], headers = [" + jSONObject2 + "], allowedStatusCodes = [" + list + "], completionCallback = [" + aPICompletionCallback + "]");
        this.remoteDataSource.makeFaceDedupeAPICall(context, str, str2, jSONObject, jSONObject2, list, aPICompletionCallback);
    }

    public void makeFaceAuthAPICall(Context context, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        HVLogUtils.d(this.TAG, "makeFaceAuthAPICall() called with: context = [" + context + "], endpoint = [" + str + "], selfieImageURL = [" + str2 + "], parameters = [" + jSONObject + "], headers = [" + jSONObject2 + "], allowedStatusCodes = [" + list + "], completionCallback = [" + aPICompletionCallback + "]");
        this.remoteDataSource.makeFaceAuthAPICall(context, str, str2, jSONObject, jSONObject2, list, aPICompletionCallback);
    }
}
