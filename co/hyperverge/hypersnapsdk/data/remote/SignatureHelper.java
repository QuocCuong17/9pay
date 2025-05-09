package co.hyperverge.hypersnapsdk.data.remote;

import android.util.Base64;
import android.util.Log;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.helpers.TimingUtils;
import co.hyperverge.hypersnapsdk.service.HVSignatureService;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.TreeMap;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.Headers;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class SignatureHelper {
    private static final String TAG = "co.hyperverge.hypersnapsdk.data.remote.SignatureHelper";
    private static final char[] hexArray = "0123456789abcdef".toCharArray();

    static String fetchChecksumFromHeader(Headers headers) {
        HVLogUtils.d(TAG, "fetchChecksumFromHeader() called with: headers = [" + headers + "]");
        if (headers == null) {
            return null;
        }
        return headers.get("X-Response-Signature");
    }

    static String fetchChecksumFromHeader(JSONObject jSONObject) {
        String string;
        HVLogUtils.d(TAG, "fetchChecksumFromHeader() called with: headers = [" + jSONObject + "]");
        String str = null;
        if (jSONObject == null) {
            return null;
        }
        try {
        } catch (JSONException e) {
            String str2 = TAG;
            HVLogUtils.e(str2, "fetchChecksumFromHeader(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str2, Utils.getErrorMessage(e));
        }
        if (jSONObject.has("X-Response-Signature")) {
            string = jSONObject.getString("X-Response-Signature");
        } else {
            if (jSONObject.has("X-HV-Response-Signature")) {
                string = jSONObject.getString("X-HV-Response-Signature");
            }
            HVLogUtils.d(TAG, "fetchChecksumFromHeader() returned: " + str);
            return str;
        }
        str = string;
        HVLogUtils.d(TAG, "fetchChecksumFromHeader() returned: " + str);
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean validateSignatureIfNeededAPAC(JSONObject jSONObject, JSONObject jSONObject2, String str) {
        HVLogUtils.d(TAG, "validateSignatureIfNeededAPAC() called with: responseObject = [" + jSONObject + "], headers = [" + jSONObject2 + "], uuid = [" + str + "]");
        String sortJSONKeysAlphabetically = HVSignatureService.sortJSONKeysAlphabetically(HVSignatureService.convertJSONObjToMap(jSONObject));
        if (!HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature() || str == null) {
            return true;
        }
        return validateSignature(sortJSONKeysAlphabetically, fetchChecksumFromHeader(jSONObject2), str);
    }

    static boolean validateSignatureIfNeededAPAC(String str, Headers headers, String str2) {
        TreeMap<String, Object> treeMap;
        HVLogUtils.d(TAG, "validateSignatureIfNeededAPAC() called with: responseString = [" + str + "], headers = [" + headers + "], uuid = [" + str2 + "]");
        try {
            treeMap = HVSignatureService.convertJSONObjToMap(new JSONObject(str));
        } catch (JSONException e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
            treeMap = null;
        }
        String sortJSONKeysAlphabetically = HVSignatureService.sortJSONKeysAlphabetically(treeMap);
        if (sortJSONKeysAlphabetically != null) {
            str = sortJSONKeysAlphabetically;
        }
        if (!HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature() || str2 == null) {
            return true;
        }
        boolean validateSignature = validateSignature(str, fetchChecksumFromHeader(headers), str2);
        HVLogUtils.d(TAG, "validateSignatureIfNeededAPAC() returned: " + validateSignature);
        return validateSignature;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean validateSignatureIfNeeded(JSONObject jSONObject, JSONObject jSONObject2, String str) {
        String str2 = TAG;
        HVLogUtils.d(str2, "validateSignatureIfNeeded() called with: responseString = [" + jSONObject + "], headers = [" + jSONObject2 + "], uuid = [" + str + "]");
        if (!HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature() || str == null) {
            return true;
        }
        boolean validateSignature = validateSignature(jSONObject.toString(), fetchChecksumFromHeader(jSONObject2), str);
        HVLogUtils.d(str2, "validateSignatureIfNeededAPAC() returned: " + validateSignature);
        return validateSignature;
    }

    static boolean validateSignatureIfNeeded(String str, Headers headers, String str2) {
        String str3 = TAG;
        HVLogUtils.d(str3, "validateSignatureIfNeeded() called with: responseString = [" + str + "], headers = [" + headers + "], uuid = [" + str2 + "]");
        if (!HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature() || str2 == null) {
            return true;
        }
        boolean validateSignature = validateSignature(str, fetchChecksumFromHeader(headers), str2);
        HVLogUtils.d(str3, "validateSignatureIfNeededAPAC() returned: " + validateSignature);
        return validateSignature;
    }

    static boolean validateSignature(String str, String str2, String str3) {
        String str4 = TAG;
        HVLogUtils.d(str4, "validateSignature() called with: responseString = [" + str + "], checksum = [" + str2 + "], uuid = [" + str3 + "]");
        boolean z = false;
        if (str != null && str2 != null) {
            String decryptUsingRSA = decryptUsingRSA(str2);
            String computeHMAC256Hashing = computeHMAC256Hashing(str3, str);
            if (decryptUsingRSA != null && computeHMAC256Hashing != null && decryptUsingRSA.equals(computeHMAC256Hashing)) {
                z = true;
            }
            HVLogUtils.d(str4, "validateSignature() returned: " + z);
        }
        return z;
    }

    static String decryptUsingRSA(String str) {
        HVLogUtils.d(TAG, "decryptUsingRSA() called with: checksum = [" + str + "]");
        try {
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFusF4/wCAVrq6m0uomaGHD9O2YpwBZulbyaSb5s8WMyyy/xT4zMGrghJEsQV8REAH9pAqZk06YvkT01fMP8mTr9uUwW3CngVdjgrxGKfL1YZACS93SfvAXXX95w/EYkUiDr3sby7YV7NaqlcmTeRFDzJLFRPkDLxzAj+l3QCdkQIDAQAB", 0)));
            byte[] decode = Base64.decode(str, 0);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(2, generatePublic);
            return new String(cipher.doFinal(decode));
        } catch (Exception e) {
            String str2 = TAG;
            HVLogUtils.e(str2, "initErrorMonitoring(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str2, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        }
    }

    static String computeHMAC256Hashing(String str, String str2) {
        HVLogUtils.d(TAG, "computeHMAC256Hashing() called with: uuid = [" + str + "], responseString = [" + str2 + "]");
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(str.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            return bytesToHex(mac.doFinal(str2.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            String str3 = TAG;
            HVLogUtils.e(str3, "computeHMAC256Hashing(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str3, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        }
    }

    public static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = hexArray;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    public static String getUniqueIdentifierIfNeeded(String str, String str2, JSONObject jSONObject) {
        HVLogUtils.d(TAG, "getUniqueIdentifierIfNeeded() called with: file1 = [" + str + "], file2 = [" + str2 + "], headers = [" + jSONObject + "]");
        if (!HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature()) {
            return null;
        }
        if (jSONObject.has("uuid")) {
            try {
                return jSONObject.getString("uuid");
            } catch (JSONException e) {
                String str3 = TAG;
                HVLogUtils.e(str3, "getUniqueIdentifierIfNeeded(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(str3, Utils.getErrorMessage(e));
            }
        }
        String hashOfImage = getHashOfImage(str, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getSignatureMethod());
        String hashOfImage2 = getHashOfImage(str2, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getSignatureMethod());
        if (hashOfImage == null || hashOfImage2 == null) {
            return null;
        }
        return hashOfImage + hashOfImage2;
    }

    public static String getUniqueIdentifierIfNeeded(String str, JSONObject jSONObject) {
        HVLogUtils.d(TAG, "getUniqueIdentifierIfNeeded() called with: file = [" + str + "], headers = [" + jSONObject + "]");
        if (!HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature()) {
            return null;
        }
        if (jSONObject.has("uuid")) {
            try {
                return jSONObject.getString("uuid");
            } catch (JSONException e) {
                String str2 = TAG;
                HVLogUtils.e(str2, "getUniqueIdentifierIfNeeded(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(str2, Utils.getErrorMessage(e));
            }
        }
        return getHashOfImage(str, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getSignatureMethod());
    }

    public static String getUniqueIdentifierForReqIfNeeded(String str) {
        HVLogUtils.d(TAG, "getUniqueIdentifierForReqIfNeeded() called with: request = [" + str + "]");
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature()) {
            return getSha1HashOfRequest(str);
        }
        return null;
    }

    public static String getSha256Hash(byte[] bArr) throws NoSuchAlgorithmException {
        HVLogUtils.d(TAG, "getSha256Hash() called with: bytes = [" + bArr + "]");
        return new String(MessageDigest.getInstance("SHA-256").digest(bArr), StandardCharsets.UTF_8);
    }

    public static String getHashOfImage(String str, String str2) {
        InputStream fileInputStream;
        HVLogUtils.d(TAG, "getHashOfImage() called with: file = [" + str + "], algo = [" + str2 + "]");
        MessageDigest messageDigest = getMessageDigest(str2);
        if (messageDigest == null || (fileInputStream = getFileInputStream(str)) == null) {
            return null;
        }
        byte[] bArr = new byte[8192];
        while (true) {
            try {
                try {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    messageDigest.update(bArr, 0, read);
                } catch (Exception e) {
                    String str3 = TAG;
                    HVLogUtils.e(str3, "getHashOfImage(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                    Log.e(str3, "Exception on closing SHA_256 input stream", e);
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                    }
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        HVLogUtils.e(TAG, "getHashOfImage(): exception = [" + Utils.getErrorMessage(e2) + "]", e2);
                        Log.e(TAG, "Exception on closing SHA_256 input stream", e2);
                        if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    HVLogUtils.e(TAG, "getHashOfImage(): exception = [" + Utils.getErrorMessage(e3) + "]", e3);
                    Log.e(TAG, "Exception on closing SHA_256 input stream", e3);
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e3);
                    }
                }
                throw th;
            }
        }
        String sb = getHexRepresentation(messageDigest.digest()).toString();
        try {
            fileInputStream.close();
        } catch (IOException e4) {
            HVLogUtils.e(TAG, "getHashOfImage(): exception = [" + Utils.getErrorMessage(e4) + "]", e4);
            Log.e(TAG, "Exception on closing SHA_256 input stream", e4);
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e4);
            }
        }
        return sb;
    }

    private static StringBuilder getHexRepresentation(byte[] bArr) {
        HVLogUtils.d(TAG, "getHexRepresentation() called with: sha256Sum = [" + bArr + "]");
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb;
    }

    private static MessageDigest getMessageDigest(String str) {
        HVLogUtils.d(TAG, "getMessageDigest() called with: algo = [" + str + "]");
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            HVLogUtils.e(TAG, "getMessageDigest(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, "Exception while getting digest", e);
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        }
    }

    private static InputStream getFileInputStream(String str) {
        HVLogUtils.d(TAG, "getFileInputStream() called with: file = [" + str + "]");
        try {
            return new FileInputStream(str);
        } catch (FileNotFoundException e) {
            String str2 = TAG;
            HVLogUtils.e(str2, "getFileInputStream(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str2, "Exception while getting FileInputStream", e);
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        }
    }

    public static String getSha1HashOfRequest(String str) {
        TreeMap<String, Object> treeMap;
        HVLogUtils.d(TAG, "getSha1HashOfRequest() called with: requestStr = [" + str + "]");
        new TimingUtils();
        try {
            treeMap = HVSignatureService.convertJSONObjToMap(new JSONObject(str));
        } catch (JSONException e) {
            String str2 = TAG;
            HVLogUtils.e(str2, "getSha1HashOfRequest(): convertJSONObjToMap exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str2, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
            treeMap = null;
        }
        String sortJSONKeysAlphabetically = HVSignatureService.sortJSONKeysAlphabetically(treeMap);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(sortJSONKeysAlphabetically.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                while (hexString.length() < 2) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e2) {
            String str3 = TAG;
            HVLogUtils.e(str3, "getSha1HashOfRequest(): exception = [" + Utils.getErrorMessage(e2) + "]", e2);
            Log.e(str3, Utils.getErrorMessage(e2));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return "";
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
            return "";
        }
    }
}
