package co.hyperverge.hypersnapsdk.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.objects.ExternalConfigs;
import co.hyperverge.hypersnapsdk.objects.HVDocConfig;
import java.io.File;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class InternalToolUtils {
    private static final String SDK_MODE_BUILDER = "builder";
    private static final String SDK_MODE_PROD = "prod";
    private static final String SDK_MODE_TEST = "test";
    private static final String TAG = "InternalToolUtils";

    public static String getSdkMode(Context context) {
        String str;
        ExternalConfigs externalConfigs = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getExternalConfigs();
        if (externalConfigs == null) {
            return "prod";
        }
        HashMap<String, String> metadataMap = externalConfigs.getMetadataMap();
        if (metadataMap.containsKey("sdk-mode") && (str = metadataMap.get("sdk-mode")) != null && context != null) {
            if (context.getPackageName().equals("co.hyperverge.hyperkyc_flutter_example") && str.equalsIgnoreCase(SDK_MODE_TEST)) {
                return SDK_MODE_TEST;
            }
            if (context.getPackageName().equals("co.hyperverge.hyperkyc.demo") && str.equalsIgnoreCase(SDK_MODE_BUILDER)) {
                return SDK_MODE_BUILDER;
            }
        }
        return "prod";
    }

    public static boolean isTestMode(Context context) {
        return getSdkMode(context).equalsIgnoreCase(SDK_MODE_TEST);
    }

    public static boolean isBuilderMode(Context context) {
        return getSdkMode(context).equalsIgnoreCase(SDK_MODE_BUILDER);
    }

    public static File getDocumentImageFile(HVDocConfig.DocumentSide documentSide) {
        String str;
        ExternalConfigs externalConfigs = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getExternalConfigs();
        if (externalConfigs == null) {
            return null;
        }
        HashMap<String, String> metadataMap = externalConfigs.getMetadataMap();
        String str2 = documentSide == HVDocConfig.DocumentSide.FRONT ? "docFrontImagePath" : "docBackImagePath";
        if (metadataMap.containsKey(str2) && (str = metadataMap.get(str2)) != null) {
            return new File(str);
        }
        return null;
    }

    public static Bitmap getDocumentImageBitmap(HVDocConfig.DocumentSide documentSide) {
        File documentImageFile = getDocumentImageFile(documentSide);
        if (documentImageFile == null) {
            return null;
        }
        try {
            return BitmapFactory.decodeFile(documentImageFile.getAbsolutePath());
        } catch (Exception e) {
            Log.e(TAG, "getDocumentImageBitmap: exception - " + Utils.getErrorMessage(e));
            return null;
        }
    }

    public static Bitmap getSelfieImageBitmap() {
        String str;
        ExternalConfigs externalConfigs = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getExternalConfigs();
        if (externalConfigs == null) {
            return null;
        }
        HashMap<String, String> metadataMap = externalConfigs.getMetadataMap();
        if (!metadataMap.containsKey("selfieImagePath") || (str = metadataMap.get("selfieImagePath")) == null) {
            return null;
        }
        try {
            return BitmapFactory.decodeFile(str);
        } catch (Exception e) {
            Log.e(TAG, "getSelfieImageBitmap: exception - " + Utils.getErrorMessage(e));
            return null;
        }
    }

    public static JSONObject getSelfieLivenessResult() {
        String str;
        ExternalConfigs externalConfigs = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getExternalConfigs();
        if (externalConfigs == null) {
            return null;
        }
        HashMap<String, String> metadataMap = externalConfigs.getMetadataMap();
        if (!metadataMap.containsKey("selfieLivenessResult") || (str = metadataMap.get("selfieLivenessResult")) == null) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static Bitmap getBarcodeImageBitmap() {
        String str;
        ExternalConfigs externalConfigs = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getExternalConfigs();
        if (externalConfigs == null) {
            return null;
        }
        HashMap<String, String> metadataMap = externalConfigs.getMetadataMap();
        if (!metadataMap.containsKey("barcodeImagePath") || (str = metadataMap.get("barcodeImagePath")) == null) {
            return null;
        }
        try {
            return BitmapFactory.decodeFile(str);
        } catch (Exception e) {
            Log.e(TAG, "getBarcodeImageBitmap: exception - " + Utils.getErrorMessage(e));
            return null;
        }
    }

    public static File getDynamicFormFile(String str) {
        ExternalConfigs externalConfigs = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getExternalConfigs();
        if (externalConfigs == null) {
            return null;
        }
        String str2 = externalConfigs.getMetadataMap().get(WorkflowModule.TYPE_DYNAMIC_FORM + str.substring(0, 1).toUpperCase() + str.substring(1, str.length() - 1) + "File");
        if (str2 == null) {
            return null;
        }
        return new File(str2);
    }

    public static String getFileUploadIntentType() {
        ExternalConfigs externalConfigs = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getExternalConfigs();
        if (externalConfigs == null) {
            return null;
        }
        HashMap<String, String> metadataMap = externalConfigs.getMetadataMap();
        if (metadataMap.containsKey("fileUploadIntentType")) {
            return metadataMap.get("fileUploadIntentType");
        }
        return null;
    }
}
