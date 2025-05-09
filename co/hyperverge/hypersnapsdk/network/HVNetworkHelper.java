package co.hyperverge.hypersnapsdk.network;

import android.content.Context;
import co.hyperverge.hypersnapsdk.data.DataRepository;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.listeners.APICompletionCallback;
import co.hyperverge.hypersnapsdk.objects.HVBaseConfig;
import co.hyperverge.hypersnapsdk.objects.HVDocConfig;
import co.hyperverge.hypersnapsdk.objects.HyperSnapParams;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HVNetworkHelper {
    public static void makeOCRCallWithQR(Context context, String str, String str2, String str3, HVDocConfig hVDocConfig, JSONObject jSONObject, JSONObject jSONObject2, APICompletionCallback aPICompletionCallback) {
        makeOCRAPICall(context, str, str2, str3, hVDocConfig, jSONObject, jSONObject2, hVDocConfig.getAllowedStatusCodes(), aPICompletionCallback);
    }

    @Deprecated
    public static void makeOCRCall(Context context, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, APICompletionCallback aPICompletionCallback) {
        makeOCRAPICall(context, str, str2, null, null, jSONObject, jSONObject2, new HVBaseConfig().getAllowedStatusCodes(), aPICompletionCallback);
    }

    public static void makeOCRCall(Context context, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        makeOCRAPICall(context, str, str2, null, null, jSONObject, jSONObject2, list, aPICompletionCallback);
    }

    private static void makeOCRAPICall(Context context, String str, String str2, String str3, HVDocConfig hVDocConfig, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        DataRepository.getInstance().makeOcrCall(context, str, str2, str3, hVDocConfig, jSONObject, jSONObject2, list, aPICompletionCallback);
    }

    @Deprecated
    public static void makeFaceMatchCall(Context context, String str, String str2, String str3, JSONObject jSONObject, JSONObject jSONObject2, APICompletionCallback aPICompletionCallback) {
        makeFaceMatchCall(context, str, str2, str3, jSONObject, jSONObject2, (List<Integer>) Collections.emptyList(), aPICompletionCallback);
    }

    public static void makeFaceMatchCall(Context context, String str, String str2, String str3, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        makeFaceMatchAPICall(context, str, str2, str3, HyperSnapParams.FaceMatchMode.FACE_ID, jSONObject, jSONObject2, list, aPICompletionCallback);
    }

    @Deprecated
    public static void makeFaceMatchCall(Context context, HyperSnapParams.Region region, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, APICompletionCallback aPICompletionCallback) {
        makeFaceMatchCall(context, region, str, str2, jSONObject, jSONObject2, (List<Integer>) Collections.emptyList(), aPICompletionCallback);
    }

    public static void makeFaceMatchCall(Context context, HyperSnapParams.Region region, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        String str3;
        if (region == HyperSnapParams.Region.INDIA || region == HyperSnapParams.Region.India) {
            str3 = SDKInternalConfig.getInstance().getIndiaFaceBaseUrl() + SDKInternalConfig.getInstance().getVerifyPairUri();
        } else if (region == HyperSnapParams.Region.ASIA_PACIFIC || region == HyperSnapParams.Region.AsiaPacific) {
            str3 = SDKInternalConfig.getInstance().getApacFaceMatchBaseUrl() + SDKInternalConfig.getInstance().getVerifyPairUri();
        } else if (region == HyperSnapParams.Region.AFRICA) {
            str3 = SDKInternalConfig.getInstance().getAfricaFaceMatchBaseUrl() + SDKInternalConfig.getInstance().getVerifyPairUri();
        } else {
            str3 = "";
        }
        makeFaceMatchAPICall(context, str3, str, str2, HyperSnapParams.FaceMatchMode.FACE_ID, jSONObject, jSONObject2, list, aPICompletionCallback);
    }

    @Deprecated
    public static void makeFaceMatchCall(Context context, String str, String str2, String str3, HyperSnapParams.FaceMatchMode faceMatchMode, JSONObject jSONObject, JSONObject jSONObject2, APICompletionCallback aPICompletionCallback) {
        makeFaceMatchCall(context, str, str2, str3, faceMatchMode, jSONObject, jSONObject2, Collections.emptyList(), aPICompletionCallback);
    }

    public static void makeFaceMatchCall(Context context, String str, String str2, String str3, HyperSnapParams.FaceMatchMode faceMatchMode, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        makeFaceMatchAPICall(context, str, str2, str3, faceMatchMode, jSONObject, jSONObject2, list, aPICompletionCallback);
    }

    private static void makeFaceMatchAPICall(Context context, String str, String str2, String str3, HyperSnapParams.FaceMatchMode faceMatchMode, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        DataRepository.getInstance().makeFaceMatchCall(context, str, str2, str3, faceMatchMode, jSONObject, jSONObject2, list, aPICompletionCallback);
    }

    @Deprecated
    public static void makeVerifyDocAlignmentCall(Context context, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, APICompletionCallback aPICompletionCallback) {
        makeVerifyDocAlignmentCall(context, str, str2, jSONObject, jSONObject2, Collections.emptyList(), aPICompletionCallback);
    }

    public static void makeVerifyDocAlignmentCall(Context context, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        DataRepository.getInstance().makeVerifyDocAlignmentCall(context, str, str2, jSONObject, jSONObject2, list, aPICompletionCallback);
    }

    @Deprecated
    public static void makeTextMatchAPICall(Context context, String str, JSONObject jSONObject, JSONObject jSONObject2, APICompletionCallback aPICompletionCallback) {
        makeTextMatchAPICall(context, str, jSONObject, jSONObject2, Collections.emptyList(), aPICompletionCallback);
    }

    public static void makeTextMatchAPICall(Context context, String str, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        DataRepository.getInstance().makeTextMatchAPICall(context, str, jSONObject, jSONObject2, list, aPICompletionCallback);
    }

    @Deprecated
    public static void makeValidationAPICall(Context context, String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, Boolean bool, APICompletionCallback aPICompletionCallback) {
        makeValidationAPICall(context, str, jSONObject, jSONObject2, jSONObject3, jSONObject4, bool, Collections.emptyList(), aPICompletionCallback);
    }

    public static void makeValidationAPICall(Context context, String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, Boolean bool, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        DataRepository.getInstance().makeValidationAPICall(context, str, jSONObject, jSONObject2, jSONObject3, jSONObject4, bool, list, aPICompletionCallback);
    }

    @Deprecated
    public static void makeFaceDedupeAPICall(Context context, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, APICompletionCallback aPICompletionCallback) {
        makeFaceDedupeAPICall(context, str, str2, jSONObject, jSONObject2, Collections.emptyList(), aPICompletionCallback);
    }

    public static void makeFaceDedupeAPICall(Context context, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        DataRepository.getInstance().makeFaceDedupeAPICall(context, str, str2, jSONObject, jSONObject2, list, aPICompletionCallback);
    }

    @Deprecated
    public static void makeFaceAuthAPICall(Context context, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, APICompletionCallback aPICompletionCallback) {
        makeFaceAuthAPICall(context, str, str2, jSONObject, jSONObject2, Collections.emptyList(), aPICompletionCallback);
    }

    public static void makeFaceAuthAPICall(Context context, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        DataRepository.getInstance().makeFaceAuthAPICall(context, str, str2, jSONObject, jSONObject2, list, aPICompletionCallback);
    }
}
