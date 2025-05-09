package co.hyperverge.hypersnapsdk.utils;

import android.util.Log;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: FileExtensions.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a<\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0000¨\u0006\u000b"}, d2 = {"saveSDKResultToFile", "", "Ljava/io/File;", "moduleId", "", "hvError", "Lco/hyperverge/hypersnapsdk/objects/HVError;", "hvResponse", "Lco/hyperverge/hypersnapsdk/objects/HVResponse;", "qrResult", "Lorg/json/JSONObject;", "hypersnapsdk_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class FileExtensionsKt {
    public static /* synthetic */ void saveSDKResultToFile$default(File file, String str, HVError hVError, HVResponse hVResponse, JSONObject jSONObject, int i, Object obj) {
        if ((i & 2) != 0) {
            hVError = null;
        }
        if ((i & 4) != 0) {
            hVResponse = null;
        }
        if ((i & 8) != 0) {
            jSONObject = null;
        }
        saveSDKResultToFile(file, str, hVError, hVResponse, jSONObject);
    }

    public static final void saveSDKResultToFile(File file, String str, HVError hVError, HVResponse hVResponse, JSONObject jSONObject) {
        FileWriter fileWriter;
        Log.d("FileExtensions", "saveSDKResultToFile() called with: context = [" + file + "], hvError = [" + hVError + "], hvResponse = [" + hVResponse + "], qrResult = [" + jSONObject + ']');
        if (file == null) {
            Log.e("FileExtensions", "saveSDKResultToFile: context is null");
            return;
        }
        String json = new Gson().toJson(new Triple(hVError, hVResponse, jSONObject));
        FileWriter fileWriter2 = null;
        try {
            try {
                try {
                    File file2 = new File(file, "hv/sdkResult");
                    if (!file2.exists()) {
                        file2.mkdirs();
                    }
                    if (str == null) {
                        str = "hv_data";
                    }
                    fileWriter = new FileWriter(new File(file2, Intrinsics.stringPlus(str, ".json")), false);
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                fileWriter.write(json);
                fileWriter.close();
            } catch (Exception e2) {
                e = e2;
                fileWriter2 = fileWriter;
                HVLogUtils.e("FileExtensions", Intrinsics.stringPlus("saveSDKResultToFile: exception writing file: ", Utils.getErrorMessage(e)));
                if (fileWriter2 != null) {
                    fileWriter2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                fileWriter2 = fileWriter;
                if (fileWriter2 != null) {
                    try {
                        fileWriter2.close();
                    } catch (IOException e3) {
                        HVLogUtils.e("FileExtensions", Intrinsics.stringPlus("saveSDKResultToFile: exception closing writer: ", Utils.getErrorMessage(e3)));
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            HVLogUtils.e("FileExtensions", Intrinsics.stringPlus("saveSDKResultToFile: exception closing writer: ", Utils.getErrorMessage(e4)));
        }
    }
}
