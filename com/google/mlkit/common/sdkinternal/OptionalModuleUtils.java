package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstall;
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.mlkit_common.zzao;
import com.google.android.gms.internal.mlkit_common.zzaq;
import com.google.android.gms.internal.mlkit_common.zzar;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Tasks;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes4.dex */
public class OptionalModuleUtils {
    public static final String BARCODE = "barcode";
    public static final String BARCODE_MODULE_ID = "com.google.android.gms.vision.barcode";
    public static final String CUSTOM_ICA = "custom_ica";
    public static final String CUSTOM_ICA_MODULE_ID = "com.google.android.gms.vision.custom.ica";
    public static final String DEPRECATED_DYNAMITE_MODULE_ID = "com.google.android.gms.vision.dynamite";
    public static final Feature[] EMPTY_FEATURES = new Feature[0];
    public static final String FACE = "face";
    public static final String FACE_MODULE_ID = "com.google.android.gms.vision.face";
    public static final Feature FEATURE_BARCODE;
    public static final Feature FEATURE_CUSTOM_ICA;
    public static final Feature FEATURE_FACE;
    public static final Feature FEATURE_ICA;
    public static final Feature FEATURE_LANGID;
    public static final Feature FEATURE_MLKIT_BARCODE_UI;
    public static final Feature FEATURE_NLCLASSIFIER;
    public static final Feature FEATURE_OCR;
    public static final Feature FEATURE_SMART_REPLY;
    public static final Feature FEATURE_TFLITE_DYNAMITE;
    public static final String ICA = "ica";
    public static final String ICA_MODULE_ID = "com.google.android.gms.vision.ica";
    public static final String LANGID = "langid";
    public static final String LANGID_MODULE_ID = "com.google.android.gms.mlkit.langid";
    public static final String MLKIT_BARCODE_UI = "barcode_ui";
    public static final String NLCLASSIFIER = "nlclassifier";
    public static final String NLCLASSIFIER_MODULE_ID = "com.google.android.gms.mlkit.nlclassifier";
    public static final String OCR = "ocr";
    public static final String OCR_MODULE_ID = "com.google.android.gms.vision.ocr";
    public static final String SMART_REPLY = "smart_reply";
    public static final String SMART_REPLY_MODULE_ID = "com.google.android.gms.mlkit_smartreply";
    public static final String TFLITE_DYNAMITE = "tflite_dynamite";
    public static final String TFLITE_DYNAMITE_MODULE_ID = "com.google.android.gms.tflite_dynamite";
    private static final zzar zza;
    private static final zzar zzb;

    static {
        Feature feature = new Feature("vision.barcode", 1L);
        FEATURE_BARCODE = feature;
        Feature feature2 = new Feature("vision.custom.ica", 1L);
        FEATURE_CUSTOM_ICA = feature2;
        Feature feature3 = new Feature("vision.face", 1L);
        FEATURE_FACE = feature3;
        Feature feature4 = new Feature("vision.ica", 1L);
        FEATURE_ICA = feature4;
        Feature feature5 = new Feature("vision.ocr", 1L);
        FEATURE_OCR = feature5;
        Feature feature6 = new Feature("mlkit.langid", 1L);
        FEATURE_LANGID = feature6;
        Feature feature7 = new Feature("mlkit.nlclassifier", 1L);
        FEATURE_NLCLASSIFIER = feature7;
        Feature feature8 = new Feature(TFLITE_DYNAMITE, 1L);
        FEATURE_TFLITE_DYNAMITE = feature8;
        Feature feature9 = new Feature("mlkit.barcode.ui", 1L);
        FEATURE_MLKIT_BARCODE_UI = feature9;
        Feature feature10 = new Feature("mlkit.smartreply", 1L);
        FEATURE_SMART_REPLY = feature10;
        zzaq zzaqVar = new zzaq();
        zzaqVar.zza("barcode", feature);
        zzaqVar.zza(CUSTOM_ICA, feature2);
        zzaqVar.zza("face", feature3);
        zzaqVar.zza(ICA, feature4);
        zzaqVar.zza(OCR, feature5);
        zzaqVar.zza(LANGID, feature6);
        zzaqVar.zza(NLCLASSIFIER, feature7);
        zzaqVar.zza(TFLITE_DYNAMITE, feature8);
        zzaqVar.zza(MLKIT_BARCODE_UI, feature9);
        zzaqVar.zza(SMART_REPLY, feature10);
        zza = zzaqVar.zzb();
        zzaq zzaqVar2 = new zzaq();
        zzaqVar2.zza(BARCODE_MODULE_ID, feature);
        zzaqVar2.zza(CUSTOM_ICA_MODULE_ID, feature2);
        zzaqVar2.zza(FACE_MODULE_ID, feature3);
        zzaqVar2.zza(ICA_MODULE_ID, feature4);
        zzaqVar2.zza(OCR_MODULE_ID, feature5);
        zzaqVar2.zza(LANGID_MODULE_ID, feature6);
        zzaqVar2.zza(NLCLASSIFIER_MODULE_ID, feature7);
        zzaqVar2.zza(TFLITE_DYNAMITE_MODULE_ID, feature8);
        zzaqVar2.zza(SMART_REPLY_MODULE_ID, feature10);
        zzb = zzaqVar2.zzb();
    }

    private OptionalModuleUtils() {
    }

    public static boolean areAllRequiredModulesAvailable(Context context, List<String> list) {
        if (GoogleApiAvailabilityLight.getInstance().getApkVersion(context) >= 221500000) {
            return areAllRequiredModulesAvailable(context, zza(zzb, list));
        }
        try {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, it.next());
            }
            return true;
        } catch (DynamiteModule.LoadingException unused) {
            return false;
        }
    }

    public static void requestDownload(Context context, String str) {
        requestDownload(context, zzao.zzj(str));
    }

    private static Feature[] zza(Map map, List list) {
        Feature[] featureArr = new Feature[list.size()];
        for (int i = 0; i < list.size(); i++) {
            featureArr[i] = (Feature) Preconditions.checkNotNull((Feature) map.get(list.get(i)));
        }
        return featureArr;
    }

    public static void requestDownload(Context context, List<String> list) {
        if (GoogleApiAvailabilityLight.getInstance().getApkVersion(context) >= 221500000) {
            final Feature[] zza2 = zza(zza, list);
            ModuleInstall.getClient(context).installModules(ModuleInstallRequest.newBuilder().addApi(new OptionalModuleApi() { // from class: com.google.mlkit.common.sdkinternal.zzq
                @Override // com.google.android.gms.common.api.OptionalModuleApi
                public final Feature[] getOptionalFeatures() {
                    Feature[] featureArr = zza2;
                    Feature[] featureArr2 = OptionalModuleUtils.EMPTY_FEATURES;
                    return featureArr;
                }
            }).build()).addOnFailureListener(new OnFailureListener() { // from class: com.google.mlkit.common.sdkinternal.zzr
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    Log.e("OptionalModuleUtils", "Failed to request modules install request", exc);
                }
            });
        } else {
            Intent intent = new Intent();
            intent.setClassName("com.google.android.gms", "com.google.android.gms.vision.DependencyBroadcastReceiverProxy");
            intent.setAction("com.google.android.gms.vision.DEPENDENCY");
            intent.putExtra("com.google.android.gms.vision.DEPENDENCIES", TextUtils.join(",", list));
            intent.putExtra("requester_app_package", context.getApplicationInfo().packageName);
            context.sendBroadcast(intent);
        }
    }

    private static boolean areAllRequiredModulesAvailable(Context context, final Feature[] featureArr) {
        try {
            return ((ModuleAvailabilityResponse) Tasks.await(ModuleInstall.getClient(context).areModulesAvailable(new OptionalModuleApi() { // from class: com.google.mlkit.common.sdkinternal.zzo
                @Override // com.google.android.gms.common.api.OptionalModuleApi
                public final Feature[] getOptionalFeatures() {
                    Feature[] featureArr2 = featureArr;
                    Feature[] featureArr3 = OptionalModuleUtils.EMPTY_FEATURES;
                    return featureArr2;
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.google.mlkit.common.sdkinternal.zzp
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    Log.e("OptionalModuleUtils", "Failed to check feature availability", exc);
                }
            }))).areModulesAvailable();
        } catch (InterruptedException | ExecutionException e) {
            Log.e("OptionalModuleUtils", "Failed to complete the task of features availability check", e);
            return false;
        }
    }
}
