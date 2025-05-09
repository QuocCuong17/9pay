package co.hyperverge.hypersnapsdk.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.media.MediaMuxer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import androidx.core.view.InputDeviceCompat;
import androidx.fragment.app.Fragment;
import co.hyperverge.hvcamera.magicfilter.utils.Exif;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.data.remote.SignatureHelper;
import co.hyperverge.hypersnapsdk.helpers.FileHelper;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.helpers.xmlparser.XmlToJson;
import co.hyperverge.hypersnapsdk.model.HVFaceObj;
import co.hyperverge.hypersnapsdk.objects.HVDocConfig;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.utils.Utils;
import co.hyperverge.hypersnapsdk.utils.threading.ThreadExecutor;
import com.google.android.gms.common.ConnectionResult;
import com.google.firebase.firestore.BuildConfig;
import com.google.mlkit.vision.face.Face;
import io.sentry.protocol.Device;
import io.sentry.protocol.Geo;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Utils {
    public static int JPEG_COMPRESSION_QUALITY = 95;
    private static final String TAG = "co.hyperverge.hypersnapsdk.utils.Utils";
    private static final ArrayList<String> requiredPermissions = new ArrayList<>(Arrays.asList("android.permission.CAMERA"));

    /* loaded from: classes2.dex */
    public interface FileOperationCallback {
        void onSaved(File file);
    }

    public static String getInfoLevelHardwareMapper(int i) {
        if (i == 0) {
            return AppConstants.LEVEL_LIMITED;
        }
        if (i == 1) {
            return AppConstants.LEVEL_FULL;
        }
        if (i == 2) {
            return AppConstants.LEVEL_LEGACY;
        }
        if (i != 3) {
            return null;
        }
        return AppConstants.LEVEL_3;
    }

    public static boolean canDismissLoader(Activity activity) {
        return isActivityActive(activity);
    }

    public static boolean isActivityActive(Activity activity) {
        return (activity == null || activity.isFinishing() || activity.isDestroyed()) ? false : true;
    }

    public static boolean canDismissLoader(Fragment fragment) {
        return isFragmentActive(fragment);
    }

    public static boolean isFragmentActive(Fragment fragment) {
        return (fragment == null || fragment.isDetached()) ? false : true;
    }

    public static boolean hasNavBar(Context context) {
        return getAppUsableScreenSize(context).y < getRealScreenSize(context).y;
    }

    public static Point getAppUsableScreenSize(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point;
    }

    public static String getInfoHardwareLevel(Context context, boolean z) {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                CameraManager cameraManager = (CameraManager) context.getSystemService("camera");
                String cameraId = z ? getCameraId(cameraManager, 1) : null;
                if (cameraId == null || !z) {
                    cameraId = getCameraId(cameraManager, 0);
                }
                if (cameraId == null) {
                    return null;
                }
                CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraId);
                AppConstants.cameraLevel = getInfoLevelHardwareMapper(((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue());
                AppConstants.sensorOrientation = String.valueOf(cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION));
                return getInfoLevelHardwareMapper(((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue());
            }
        } catch (AssertionError | Exception e) {
            HVLogUtils.e(TAG, getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        return null;
    }

    private static String getCameraId(CameraManager cameraManager, int i) {
        try {
            for (String str : cameraManager.getCameraIdList()) {
                Integer num = (Integer) cameraManager.getCameraCharacteristics(str).get(CameraCharacteristics.LENS_FACING);
                if (num != null && num.intValue() == i) {
                    return str;
                }
            }
            return null;
        } catch (AssertionError | Exception e) {
            HVLogUtils.e(TAG, getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        }
    }

    public static ArrayList<Float> getFaceCoordinates(ArrayList<ArrayList<Float>> arrayList) {
        ArrayList<Float> largestMatrix = getLargestMatrix(arrayList);
        if (largestMatrix == null) {
            return null;
        }
        ArrayList<Float> arrayList2 = new ArrayList<>();
        float floatValue = largestMatrix.get(4).floatValue() - largestMatrix.get(0).floatValue();
        float floatValue2 = largestMatrix.get(5).floatValue() - largestMatrix.get(1).floatValue();
        float f = floatValue * 0.35f;
        float floatValue3 = (largestMatrix.get(0).floatValue() - f) * 100.0f < 0.0f ? 0.0f : (largestMatrix.get(0).floatValue() - f) * 100.0f;
        float f2 = floatValue2 * 0.45f;
        float floatValue4 = (largestMatrix.get(1).floatValue() - f2) * 100.0f >= 0.0f ? (largestMatrix.get(1).floatValue() - f2) * 100.0f : 0.0f;
        float floatValue5 = (largestMatrix.get(4).floatValue() + f) * 100.0f > 100.0f ? 100.0f : (largestMatrix.get(4).floatValue() + f) * 100.0f;
        float floatValue6 = (largestMatrix.get(5).floatValue() + f2) * 100.0f <= 100.0f ? 100.0f * (largestMatrix.get(5).floatValue() + f2) : 100.0f;
        arrayList2.add(Float.valueOf(floatValue3));
        arrayList2.add(Float.valueOf(floatValue4));
        arrayList2.add(Float.valueOf(floatValue5));
        arrayList2.add(Float.valueOf(floatValue6));
        return arrayList2;
    }

    public static List<Integer> getHVFaceObj(Face face, Bitmap bitmap) {
        ArrayList arrayList = new ArrayList();
        if (face != null) {
            new HVFaceObj();
            face.getBoundingBox().width();
            face.getBoundingBox().height();
            int i = face.getBoundingBox().left;
            int i2 = face.getBoundingBox().top;
            int i3 = face.getBoundingBox().right;
            int i4 = face.getBoundingBox().bottom;
            arrayList.add(Integer.valueOf(i));
            arrayList.add(Integer.valueOf(i2));
            arrayList.add(Integer.valueOf(i3));
            arrayList.add(Integer.valueOf(i4));
        }
        return arrayList;
    }

    public static ArrayList<Float> getLargestMatrix(ArrayList<ArrayList<Float>> arrayList) {
        ArrayList<Float> arrayList2 = arrayList.get(0);
        float floatValue = (arrayList2.get(4).floatValue() - arrayList2.get(0).floatValue()) * (arrayList2.get(3).floatValue() - arrayList2.get(1).floatValue());
        Iterator<ArrayList<Float>> it = arrayList.iterator();
        while (it.hasNext()) {
            ArrayList<Float> next = it.next();
            if ((next.get(4).floatValue() - next.get(0).floatValue()) * (next.get(3).floatValue() - next.get(1).floatValue()) > floatValue) {
                floatValue = (next.get(4).floatValue() - next.get(0).floatValue()) * (next.get(3).floatValue() - next.get(1).floatValue());
                arrayList2 = next;
            }
        }
        return arrayList2;
    }

    public static Point getRealScreenSize(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else if (Build.VERSION.SDK_INT >= 14) {
            try {
                point.x = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                point.y = ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                HVLogUtils.e(TAG, getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
        return point;
    }

    public static int getBuildVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static ArrayList<String> checkIfPermissionsGranted(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> it = requiredPermissions.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (context.checkCallingOrSelfPermission(next) != 0) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public static String getPermissionNotGrantedErrorMessage(ArrayList<String> arrayList) {
        String str = arrayList.get(0);
        for (int i = 1; i < arrayList.size() - 1; i++) {
            str = str + ", " + arrayList.get(i);
        }
        if (arrayList.size() <= 1) {
            return str;
        }
        return str + " & " + arrayList.get(arrayList.size() - 1);
    }

    public static void copyFile(String str, String str2) {
        try {
            File parentFile = new File(str2).getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            HVLogUtils.e(TAG, getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    public static String getStringMode(HVFaceConfig.LivenessMode livenessMode) {
        return livenessMode == HVFaceConfig.LivenessMode.NONE ? "NONE" : livenessMode == HVFaceConfig.LivenessMode.TEXTURELIVENESS ? "TEXTURELIVENESS" : "";
    }

    public static String getStringDocument(HVDocConfig.Document document) {
        return document == HVDocConfig.Document.CARD ? "CARD" : document == HVDocConfig.Document.PASSPORT ? "PASSPORT" : document == HVDocConfig.Document.A4 ? "A4" : document == HVDocConfig.Document.OTHER ? "OTHER" : "";
    }

    public static String XMLToJSON(String str) {
        try {
            return new XmlToJson.Builder(str).build().toJson().toString();
        } catch (Exception e) {
            HVLogUtils.e(TAG, getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
            return str;
        }
    }

    public static String secureQRDataToJSON(String str) throws IOException, JSONException {
        byte[] decompressByteArray = decompressByteArray(new BigInteger(str).toByteArray());
        int[] locateDelimiters = locateDelimiters(decompressByteArray);
        String[] strArr = new String[16];
        String valueInRange = getValueInRange(decompressByteArray, 0, 1);
        int i = 0;
        while (i < 16) {
            int i2 = i + 1;
            strArr[i] = getValueInRange(decompressByteArray, locateDelimiters[i] + 1, locateDelimiters[i2]);
            i = i2;
        }
        if (strArr[0].length() > 1) {
            String[] strArr2 = new String[16];
            System.arraycopy(strArr, 0, strArr2, 1, 15);
            strArr2[0] = "";
            strArr = strArr2;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("aadhaar", strArr[1].substring(0, 4));
        jSONObject.put("name", strArr[2]);
        String formatDate = formatDate(strArr[3]);
        jSONObject.put("dob", formatDate);
        jSONObject.put("gender", strArr[4]);
        jSONObject.put("care_of", strArr[5]);
        jSONObject.put("district", strArr[6]);
        jSONObject.put("landmark", strArr[7]);
        jSONObject.put("house_number", strArr[8]);
        jSONObject.put("locality", strArr[9]);
        jSONObject.put("pin", strArr[10]);
        jSONObject.put("po", strArr[11]);
        jSONObject.put("state", strArr[12]);
        jSONObject.put("street", strArr[13]);
        jSONObject.put("subdist", strArr[14]);
        jSONObject.put(Geo.JsonKeys.CITY, strArr[15]);
        jSONObject.put("yob", formatDate.substring(0, 4));
        jSONObject.put("value", str);
        try {
            addBonusDataToResult(decompressByteArray, decompressByteArray.length, jSONObject, Integer.parseInt(valueInRange), locateDelimiters);
        } catch (NumberFormatException e) {
            HVLogUtils.e(TAG, getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        return jSONObject.toString();
    }

    public static byte[] decompressByteArray(byte[] bArr) throws IOException {
        GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr2 = new byte[1024];
        int i = 0;
        while (i >= 0) {
            i = gZIPInputStream.read(bArr2, 0, 1024);
            if (i > 0) {
                byteArrayOutputStream.write(bArr2, 0, i);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static int[] locateDelimiters(byte[] bArr) {
        int[] iArr = new int[17];
        int i = 0;
        for (int i2 = 0; i2 <= 16; i2++) {
            int nextDelimiterIndex = getNextDelimiterIndex(bArr, i);
            iArr[i2] = nextDelimiterIndex;
            i = nextDelimiterIndex + 1;
        }
        return iArr;
    }

    private static int getNextDelimiterIndex(byte[] bArr, int i) {
        while (i < bArr.length && bArr[i] != -1) {
            i++;
        }
        return i;
    }

    private static String getValueInRange(byte[] bArr, int i, int i2) {
        return new String(Arrays.copyOfRange(bArr, i, i2), StandardCharsets.ISO_8859_1);
    }

    private static String formatDate(String str) {
        if (str.equals("")) {
            return "";
        }
        String[] strArr = {"dd-MM-yyyy", "dd/MM/yyyy"};
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = null;
        ParseException e = null;
        for (int i = 0; i < 2; i++) {
            try {
                date = new SimpleDateFormat(strArr[i], Locale.getDefault()).parse(str);
                break;
            } catch (ParseException e2) {
                e = e2;
            }
        }
        if (date != null) {
            return simpleDateFormat.format(date);
        }
        if (e != null) {
            return str;
        }
        throw new AssertionError("This code is unreachable");
    }

    public static int checkForOrientationCorrection(int i) {
        if (i == 0 && SDKInternalConfig.getInstance() != null && SDKInternalConfig.getInstance().shouldCorrectOrientation()) {
            return 6;
        }
        return i;
    }

    private static JSONObject addBonusDataToResult(byte[] bArr, int i, JSONObject jSONObject, int i2, int[] iArr) {
        int i3;
        if (i > 256) {
            int i4 = i - 1;
            int i5 = i4 + InputDeviceCompat.SOURCE_ANY;
            try {
                jSONObject.put("signature", Base64.encodeToString(reverseBytes(Arrays.copyOfRange(bArr, i5, i4)), 0));
                if (i2 != 0) {
                    if ((i2 == 3 || i2 == 1) && i > 288) {
                        int i6 = (i5 - 1) - 32;
                        jSONObject.put("mobile_hash", SignatureHelper.bytesToHex(reverseBytes(Arrays.copyOfRange(bArr, i6, i6 + 32))));
                    }
                    if (i2 == 3 || i2 == 2) {
                        if (i > ((i2 == 2 ? 1 : 2) * 32) + 256) {
                            int i7 = (i5 - 1) - ((i2 == 2 ? 1 : 2) * 32);
                            jSONObject.put("email_hash", SignatureHelper.bytesToHex(reverseBytes(Arrays.copyOfRange(bArr, i7, i7 + 32))));
                        }
                    }
                }
                if (i2 == 3) {
                    i3 = i4 - 64;
                } else {
                    i3 = i4 - (i2 == 0 ? 0 : 32);
                }
                jSONObject.put("photo_jp2000", Base64.encodeToString(Arrays.copyOfRange(bArr, iArr[15] + 1, i3), 0));
            } catch (Exception e) {
                HVLogUtils.e(TAG, getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
        return jSONObject;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01e5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String secureQRDataToJSON_FAULTY(String str) throws IOException, JSONException {
        int i;
        String str2;
        boolean z;
        boolean z2;
        String str3;
        byte[] decompressByteArray = decompressByteArray(new BigInteger(str).toByteArray());
        int length = decompressByteArray.length;
        String[] strArr = new String[16];
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            if (decompressByteArray[i2] != -1) {
                arrayList.add(Byte.valueOf(decompressByteArray[i2]));
            }
            if (i2 != length - 1) {
                int i5 = i2 + 1;
                if (decompressByteArray[i5] == -1) {
                    int size = arrayList.size();
                    byte[] bArr = new byte[size];
                    for (int i6 = 0; i6 < arrayList.size(); i6++) {
                        bArr[i6] = ((Byte) arrayList.get(i6)).byteValue();
                    }
                    strArr[i3] = new String(bArr, 0, size, StandardCharsets.ISO_8859_1);
                    arrayList.clear();
                    if (i2 != length - 2 && decompressByteArray[i2 + 2] != -1) {
                        i2 = i5;
                    }
                    i3++;
                    i4 = i2;
                }
            }
            i2++;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            i = Integer.parseInt(strArr[0]);
        } catch (Exception e) {
            e = e;
            i = 0;
        }
        try {
            jSONObject.put("aadhaar", strArr[1].substring(0, 4));
            jSONObject.put("name", strArr[2]);
            String formatDate = formatDate(strArr[3]);
            jSONObject.put("dob", formatDate);
            jSONObject.put("yob", formatDate.substring(0, 4));
            jSONObject.put("gender", strArr[4]);
            jSONObject.put("care_of", strArr[5]);
            jSONObject.put("district", strArr[6]);
            jSONObject.put("landmark", strArr[7]);
            jSONObject.put("house_number", strArr[8]);
            jSONObject.put("locality", strArr[9]);
            jSONObject.put("pin", strArr[10]);
            jSONObject.put("po", strArr[11]);
            jSONObject.put("state", strArr[12]);
            jSONObject.put("street", strArr[13]);
            jSONObject.put("subdist", strArr[14]);
            jSONObject.put(Geo.JsonKeys.CITY, strArr[15]);
        } catch (Exception e2) {
            e = e2;
            HVLogUtils.e(TAG, "secureQRDataToJSON: params mapping failed: " + getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
            if (length <= 256) {
            }
            jSONObject.put("value", str);
            z = false;
            String sha256Hash = SignatureHelper.getSha256Hash(Arrays.copyOfRange(decompressByteArray, 0, (str.length() - 1) - 256));
            if (str2 == null) {
            }
            z2 = sha256Hash.contentEquals(str2);
            jSONObject.put("is_valid_data", z2);
            return jSONObject.toString();
        }
        if (length <= 256) {
            int i7 = length - 1;
            int i8 = i7 + InputDeviceCompat.SOURCE_ANY;
            try {
                str3 = new String(reverseBytes(Arrays.copyOfRange(decompressByteArray, i8, i7)), StandardCharsets.UTF_8);
            } catch (Exception e3) {
                e = e3;
                str3 = null;
            }
            try {
                jSONObject.put("signature", str3);
                int i9 = 32;
                if (i != 0) {
                    if ((i == 3 || i == 1) && length > 288) {
                        int i10 = (i8 - 1) - 32;
                        jSONObject.put("mobile_hash", SignatureHelper.bytesToHex(reverseBytes(Arrays.copyOfRange(decompressByteArray, i10, i10 + 32))));
                    }
                    if (i == 3 || i == 2) {
                        if (length > ((i == 2 ? 1 : 2) * 32) + 256) {
                            int i11 = (i8 - 1) - ((i == 2 ? 1 : 2) * 32);
                            jSONObject.put("email_hash", SignatureHelper.bytesToHex(reverseBytes(Arrays.copyOfRange(decompressByteArray, i11, i11 + 32))));
                        }
                    }
                }
                int i12 = i8 - 1;
                if (i == 3) {
                    i9 = 64;
                } else if (i == 0) {
                    i9 = 0;
                }
                jSONObject.put("photo_jp2000", new String(Arrays.copyOfRange(decompressByteArray, i4, i12 - i9)));
            } catch (Exception e4) {
                e = e4;
                HVLogUtils.e(TAG, getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
                jSONObject.put("photo_jp2000", (Object) null);
                str2 = str3;
                jSONObject.put("value", str);
                z = false;
                String sha256Hash2 = SignatureHelper.getSha256Hash(Arrays.copyOfRange(decompressByteArray, 0, (str.length() - 1) - 256));
                if (str2 == null) {
                }
                z2 = sha256Hash2.contentEquals(str2);
                jSONObject.put("is_valid_data", z2);
                return jSONObject.toString();
            }
            str2 = str3;
        } else {
            str2 = null;
        }
        jSONObject.put("value", str);
        try {
            z = false;
        } catch (Exception e5) {
            e = e5;
            z = false;
        }
        try {
            String sha256Hash22 = SignatureHelper.getSha256Hash(Arrays.copyOfRange(decompressByteArray, 0, (str.length() - 1) - 256));
            if (str2 == null) {
                str2 = "";
            }
            z2 = sha256Hash22.contentEquals(str2);
        } catch (Exception e6) {
            e = e6;
            HVLogUtils.e(TAG, getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
            z2 = z;
            jSONObject.put("is_valid_data", z2);
            return jSONObject.toString();
        }
        jSONObject.put("is_valid_data", z2);
        return jSONObject.toString();
    }

    public static byte[] reverseBytes(byte[] bArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            byte b = bArr[i];
            bArr[i] = bArr[(bArr.length - i) - 1];
            bArr[(bArr.length - i) - 1] = b;
        }
        return bArr;
    }

    public static String getDeviceAbi() {
        if (Build.VERSION.SDK_INT >= 21) {
            return Build.SUPPORTED_ABIS[0];
        }
        return Build.CPU_ABI;
    }

    public static String getIPAddress(boolean z) {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                for (InetAddress inetAddress : Collections.list(((NetworkInterface) it.next()).getInetAddresses())) {
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        boolean z2 = hostAddress.indexOf(58) < 0;
                        if (z) {
                            if (z2) {
                                return hostAddress;
                            }
                        } else if (!z2) {
                            int indexOf = hostAddress.indexOf(37);
                            return indexOf < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, indexOf).toUpperCase();
                        }
                    }
                }
            }
            return "";
        } catch (Exception e) {
            HVLogUtils.e(TAG, getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return "";
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return "";
        }
    }

    public static boolean isReleaseBuild(Context context) {
        return !((context.getApplicationContext().getApplicationInfo().flags & 2) != 0);
    }

    public static long getVideoDuration(String str, Context context, MediaMetadataRetriever mediaMetadataRetriever) {
        String extractMetadata;
        if (str != null) {
            try {
                mediaMetadataRetriever.setDataSource(context, Uri.fromFile(new File(str)));
                extractMetadata = mediaMetadataRetriever.extractMetadata(9);
            } catch (Exception e) {
                HVLogUtils.e(TAG, "videoTime: " + getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
                return 0L;
            }
        } else {
            HVLogUtils.d(TAG, "videoTime: no time");
            extractMetadata = null;
        }
        if (extractMetadata != null) {
            return Long.parseLong(extractMetadata);
        }
        return 0L;
    }

    public static void trimVideo(String str, String str2, long j) throws IOException {
        MediaExtractor mediaExtractor = new MediaExtractor();
        MediaMuxer mediaMuxer = new MediaMuxer(str2, 0);
        try {
            try {
                mediaExtractor.setDataSource(str);
                int selectTrack = selectTrack(mediaExtractor, "video/");
                if (selectTrack >= 0) {
                    mediaExtractor.selectTrack(selectTrack);
                    MediaFormat trackFormat = mediaExtractor.getTrackFormat(selectTrack);
                    long j2 = trackFormat.getLong("durationUs") - (j * 1000000);
                    mediaMuxer.addTrack(trackFormat);
                    mediaMuxer.start();
                    ByteBuffer allocate = ByteBuffer.allocate(1048576);
                    MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                    mediaExtractor.seekTo(j2, 2);
                    while (true) {
                        int readSampleData = mediaExtractor.readSampleData(allocate, 0);
                        if (readSampleData < 0) {
                            break;
                        }
                        bufferInfo.size = readSampleData;
                        bufferInfo.offset = 0;
                        bufferInfo.flags = mediaExtractor.getSampleFlags();
                        bufferInfo.presentationTimeUs = mediaExtractor.getSampleTime();
                        mediaMuxer.writeSampleData(selectTrack, allocate, bufferInfo);
                        mediaExtractor.advance();
                    }
                    mediaMuxer.stop();
                } else {
                    HVLogUtils.e(TAG, "trimVideo() - No video track found in the input video.");
                }
            } catch (IOException e) {
                HVLogUtils.e(TAG, "trimVideo() - Error trimming video: " + getErrorMessage(e));
            }
        } finally {
            mediaExtractor.release();
            mediaMuxer.release();
        }
    }

    private static int selectTrack(MediaExtractor mediaExtractor, String str) {
        int trackCount = mediaExtractor.getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            if (mediaExtractor.getTrackFormat(i).getString("mime").startsWith(str)) {
                return i;
            }
        }
        return -1;
    }

    public static void writeIntoZipFile(Context context, List<String> list, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
                try {
                    Iterator<String> it = list.iterator();
                    while (it.hasNext()) {
                        File file2 = new File(it.next());
                        try {
                            FileInputStream fileInputStream = new FileInputStream(file2);
                            try {
                                zipOutputStream.putNextEntry(new ZipEntry(file2.getName()));
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int read = fileInputStream.read(bArr);
                                    if (read < 0) {
                                        break;
                                    } else {
                                        zipOutputStream.write(bArr, 0, read);
                                    }
                                }
                                fileInputStream.close();
                            } catch (Throwable th) {
                                try {
                                    fileInputStream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                                break;
                            }
                        } catch (Exception e) {
                            HVLogUtils.e(TAG, getErrorMessage(e));
                            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                            }
                        }
                    }
                    zipOutputStream.close();
                    fileOutputStream.close();
                } finally {
                }
            } finally {
            }
        } catch (Exception e2) {
            HVLogUtils.e(TAG, "writeIntoZipFile error: " + getErrorMessage(e2));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
            }
        }
    }

    public static byte[] convertFileToBytes(Context context, File file) {
        int length = (int) file.length();
        byte[] bArr = new byte[length];
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            bufferedInputStream.read(bArr, 0, length);
            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            HVLogUtils.e(TAG, "convertFileToBytes: " + getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
            return null;
        } catch (IOException e2) {
            HVLogUtils.e(TAG, "convertFileToBytes: " + getErrorMessage(e2));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
            }
            return null;
        } catch (Exception e3) {
            HVLogUtils.e(TAG, "convertFileToBytes: " + getErrorMessage(e3));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e3);
            }
        }
        return bArr;
    }

    private static byte[] getBytesArrayFromURI(ContentResolver contentResolver, Uri uri) {
        HVLogUtils.d(TAG, "getBytesArrayFromURI() called with: contentResolver = [" + contentResolver + "], uri = [" + uri + "]");
        try {
            InputStream openInputStream = contentResolver.openInputStream(uri);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = openInputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (Exception e) {
            HVLogUtils.e(TAG, String.format("getBytesArrayFromURI: Error getting bytes from uri: %s,\nerror: %s", uri.toString(), getErrorMessage(e)));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        } catch (OutOfMemoryError e2) {
            HVLogUtils.e(TAG, String.format("getBytesArrayFromURI: Out of memory error: %s,\nerror: %s", uri.toString(), getErrorMessage(e2)));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
            return null;
        }
    }

    public static Bitmap resizeBitmap(Bitmap bitmap) {
        double d;
        double d2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        double d3 = 1.0d;
        if (width > height) {
            if (width > 1500) {
                d = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED * 1.0d;
                d2 = width;
                d3 = d / d2;
            }
        } else if (height > 1500) {
            d = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED * 1.0d;
            d2 = height;
            d3 = d / d2;
        }
        return Bitmap.createScaledBitmap(bitmap, (int) (width * d3), (int) (height * d3), true);
    }

    public static String getFileExtension(boolean z, ContentResolver contentResolver, Uri uri) {
        HVLogUtils.d(TAG, "getFileExtension() called with: contentResolver = [" + contentResolver + "], uri = [" + uri + "]");
        if (z) {
            return uri.toString().substring(uri.toString().lastIndexOf(".") + 1);
        }
        String type = contentResolver.getType(uri);
        if (type == null) {
            return null;
        }
        String extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(type);
        if (extensionFromMimeType != null) {
            return extensionFromMimeType;
        }
        if (type.startsWith("image/")) {
            return MimeTypeMap.getSingleton().getExtensionFromMimeType("image/jpeg");
        }
        return null;
    }

    public static String getFileMimeType(boolean z, ContentResolver contentResolver, Uri uri) {
        String fileExtensionFromUrl;
        HVLogUtils.d(TAG, "getFileMimeType() called with: contentResolver = [" + contentResolver + "], uri = [" + uri + "]");
        if (z && (fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(uri.toString())) != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        return contentResolver.getType(uri);
    }

    public static String extractFileExtension(String str) {
        HVLogUtils.d(TAG, "extractFileExtension() called with: filePath = [" + str + "]");
        try {
            return str.substring(str.lastIndexOf(".") + 1);
        } catch (Exception e) {
            HVLogUtils.e(TAG, "extractFileExtension: ", e);
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        }
    }

    public static void saveDocToFilesDir(final boolean z, final ContentResolver contentResolver, final Uri uri, final File file, final FileOperationCallback fileOperationCallback) {
        HVLogUtils.d(TAG, "saveDocToFilesDir() called with: contentResolver = [" + contentResolver + "], uri = [" + uri + "], hvDir = [" + file + "]");
        ThreadExecutor.getInstance().execute(new Runnable() { // from class: co.hyperverge.hypersnapsdk.utils.Utils$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                Utils.lambda$saveDocToFilesDir$0(z, contentResolver, uri, fileOperationCallback, file);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$saveDocToFilesDir$0(boolean z, ContentResolver contentResolver, Uri uri, FileOperationCallback fileOperationCallback, File file) {
        String fileExtension = getFileExtension(z, contentResolver, uri);
        if (fileExtension == null) {
            HVLogUtils.e(TAG, "saveDocToFilesDir: getFileExtension has returned null => 'extension' is null");
            handleFileOperationCallback(fileOperationCallback, null);
            return;
        }
        File file2 = new File(file, System.currentTimeMillis() + "." + fileExtension);
        try {
            byte[] bytesArrayFromURI = getBytesArrayFromURI(contentResolver, uri);
            if (bytesArrayFromURI == null) {
                HVLogUtils.e(TAG, "saveDocToFilesDir: getBytesArrayFromURI has returned null => 'bytes' is null");
                handleFileOperationCallback(fileOperationCallback, null);
                return;
            }
            if (fileExtension.equals("pdf")) {
                new FileOutputStream(file2).write(bytesArrayFromURI);
            } else {
                Bitmap resizeBitmap = resizeBitmap(FileHelper.fixRotation(MediaStore.Images.Media.getBitmap(contentResolver, uri), Exif.getOrientation(bytesArrayFromURI)));
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                if (resizeBitmap == null) {
                    HVLogUtils.e(TAG, "saveDocToFilesDir: cannot create bitmap");
                    handleFileOperationCallback(fileOperationCallback, null);
                    return;
                } else {
                    resizeBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                    resizeBitmap.recycle();
                }
            }
            handleFileOperationCallback(fileOperationCallback, file2);
        } catch (Exception e) {
            HVLogUtils.e(TAG, String.format("saveDocToFilesDir: Error writing bytes to file: %s,\nerror: %s", file2.getPath(), getErrorMessage(e)));
            handleFileOperationCallback(fileOperationCallback, null);
            fileOperationCallback.onSaved(null);
        } catch (OutOfMemoryError e2) {
            HVLogUtils.e(TAG, String.format("saveDocToFilesDir: Out of memory error: %s,\nerror: %s", file2.getPath(), getErrorMessage(e2)));
            handleFileOperationCallback(fileOperationCallback, null);
        }
    }

    private static void handleFileOperationCallback(final FileOperationCallback fileOperationCallback, final File file) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.utils.Utils$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Utils.FileOperationCallback.this.onSaved(file);
            }
        });
    }

    public static boolean containsSubstring(List<String> list, String str) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().toLowerCase().contains(str)) {
                return true;
            }
        }
        return false;
    }

    public static long timeDifferenceInMinutes(Date date) {
        return (new Date().getTime() - date.getTime()) / 60000;
    }

    public static boolean isEmulator() {
        return (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.HARDWARE.contains("goldfish") || Build.HARDWARE.contains("ranchu") || Build.HARDWARE.contains("qemu") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MODEL.contains("Android SDK built for arm64") || Build.MODEL.contains("vbox86") || Build.MANUFACTURER.contains("Genymotion") || Build.MANUFACTURER.contains("unknown") || Build.PRODUCT.contains("sdk_google") || Build.PRODUCT.contains("google_sdk") || Build.PRODUCT.contains("sdk") || Build.PRODUCT.contains("sdk_x86") || Build.PRODUCT.contains("sdk_gphone64_arm64") || Build.PRODUCT.contains("sdk_gphone64_x86_64") || Build.PRODUCT.contains("vbox86p") || Build.PRODUCT.contains(BuildConfig.TARGET_BACKEND) || Build.PRODUCT.contains(Device.JsonKeys.SIMULATOR) || Build.PRODUCT.contains("Andy") || Build.PRODUCT.contains("Droid4X") || Build.PRODUCT.contains("nox") || Build.PRODUCT.contains("nox_vm") || Build.PRODUCT.contains("msf") || Build.PRODUCT.contains("samsung_emulator") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith(BuildConfig.TARGET_BACKEND)) || Build.DEVICE.contains(BuildConfig.TARGET_BACKEND) || Build.DEVICE.contains(Device.JsonKeys.SIMULATOR);
    }

    public static Bitmap cropQR(Bitmap bitmap) {
        try {
            return Bitmap.createBitmap(bitmap, (int) (bitmap.getWidth() * 0.6666666666666667d), 0, (int) (bitmap.getWidth() * 0.3333333333333333d), (int) (bitmap.getHeight() * 0.5d));
        } catch (Exception | OutOfMemoryError e) {
            HVLogUtils.e(TAG, "cropQR : " + getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        }
    }

    public static String getSensorDataZipFileName(String str) {
        StringBuilder sb = new StringBuilder("hvsdk_android_");
        String appId = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId();
        String valueOf = String.valueOf(System.currentTimeMillis());
        sb.append(co.hyperverge.hypersnapsdk.BuildConfig.HYPERSNAP_VERSION_NAME);
        sb.append("_");
        sb.append(appId);
        sb.append("_");
        sb.append(valueOf);
        sb.append("_");
        sb.append(str);
        sb.append(".zip");
        return sb.toString();
    }

    public static int generateRandomInteger(int i) {
        return new Random().nextInt(i);
    }

    public static String getErrorMessage(Throwable th) {
        if (th == null) {
            return "Exception is null";
        }
        String message = th.getMessage();
        return (message == null || message.isEmpty()) ? "Exception has no message" : message;
    }

    public static String getLocalizedErrorMessage(Throwable th, String str) {
        String localizedMessage = th != null ? th.getLocalizedMessage() : null;
        return (localizedMessage == null || localizedMessage.isEmpty()) ? str : localizedMessage;
    }

    public static void printLargeErrorMessageOnLogcat(String str, String str2) {
        if (str2.length() > 4000) {
            Log.e(str, "sb.length = " + str2.length());
            int length = str2.length() / 4000;
            int i = 0;
            while (i <= length) {
                int i2 = i + 1;
                int i3 = i2 * 4000;
                if (i3 >= str2.length()) {
                    Log.e(str, "chunk " + i + " of " + length + ": \n" + str2.substring(i * 4000));
                } else {
                    Log.e(str, "chunk " + i + " of " + length + ": \n" + str2.substring(i * 4000, i3));
                }
                i = i2;
            }
            return;
        }
        Log.e(str, str2);
    }
}
