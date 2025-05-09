package co.hyperverge.hypersnapsdk.service.security;

import android.util.Base64;
import android.util.Log;
import androidx.media3.extractor.text.ttml.TtmlNode;
import co.hyperverge.hypersnapsdk.data.remote.SignatureHelper;
import co.hyperverge.hypersnapsdk.service.HVSignatureService;
import co.hyperverge.hypersnapsdk.service.security.HVSecurity;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class GKYCSignatureVerify {
    private static final String TAG = "co.hyperverge.hypersnapsdk.service.security.GKYCSignatureVerify";
    private JSONObject requestBody;
    private JSONObject requestHeaders;
    private JSONObject requestQuery;
    private JSONObject responseBody;
    private JSONObject responseHeaders;
    private HashMap<String, String> tagFileUriMap;

    /* loaded from: classes2.dex */
    public static class GKYCSignatureVerifyBuilder {
        private JSONObject requestBody;
        private JSONObject requestHeaders;
        private JSONObject requestQuery;
        private JSONObject responseBody;
        private JSONObject responseHeaders;
        private HashMap<String, String> tagFileUriMap;

        GKYCSignatureVerifyBuilder() {
        }

        public GKYCSignatureVerify build() {
            return new GKYCSignatureVerify(this.requestQuery, this.tagFileUriMap, this.requestBody, this.requestHeaders, this.responseBody, this.responseHeaders);
        }

        public GKYCSignatureVerifyBuilder requestBody(JSONObject jSONObject) {
            this.requestBody = jSONObject;
            return this;
        }

        public GKYCSignatureVerifyBuilder requestHeaders(JSONObject jSONObject) {
            this.requestHeaders = jSONObject;
            return this;
        }

        public GKYCSignatureVerifyBuilder requestQuery(JSONObject jSONObject) {
            this.requestQuery = jSONObject;
            return this;
        }

        public GKYCSignatureVerifyBuilder responseBody(JSONObject jSONObject) {
            this.responseBody = jSONObject;
            return this;
        }

        public GKYCSignatureVerifyBuilder responseHeaders(JSONObject jSONObject) {
            this.responseHeaders = jSONObject;
            return this;
        }

        public GKYCSignatureVerifyBuilder tagFileUriMap(HashMap<String, String> hashMap) {
            this.tagFileUriMap = hashMap;
            return this;
        }

        public String toString() {
            return "GKYCSignatureVerify.GKYCSignatureVerifyBuilder(requestQuery=" + this.requestQuery + ", tagFileUriMap=" + this.tagFileUriMap + ", requestBody=" + this.requestBody + ", requestHeaders=" + this.requestHeaders + ", responseBody=" + this.responseBody + ", responseHeaders=" + this.responseHeaders + ")";
        }
    }

    public static GKYCSignatureVerifyBuilder builder() {
        return new GKYCSignatureVerifyBuilder();
    }

    public GKYCSignatureVerify(JSONObject jSONObject, HashMap<String, String> hashMap, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, JSONObject jSONObject5) {
        this.requestQuery = jSONObject;
        this.tagFileUriMap = hashMap;
        this.requestBody = jSONObject2;
        this.requestHeaders = jSONObject3;
        this.responseBody = jSONObject4;
        this.responseHeaders = jSONObject5;
    }

    public boolean verify(String str) {
        HVLogUtils.d(TAG, "verify() called with: serverHash = [" + str + "]");
        try {
            return HVSecurity.builder().publicKey(getPublicKey()).signatureAlgorithm(HVSecurity.SignatureAlgorithm.SHA256withRSA).data(createClientHmacHash(createHashInput(), getSecret())).build().verifySignature(str);
        } catch (HVSecurityException | InvalidKeyException | NoSuchAlgorithmException | SignatureException e) {
            String str2 = TAG;
            HVLogUtils.e(str2, "verify() with: serverHash = [" + str + "]: exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str2, Utils.getErrorMessage(e));
            return false;
        }
    }

    private PublicKey getPublicKey() {
        HVLogUtils.d(TAG, "getPublicKey() called");
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA7hhIXqpCbIpfdilqLKVQR4wC/T7yUPDsn+/SgCcbv+ANi+zAHR4sQh/q4SvQhOdlPk81uk93Slu+2fr9jG4P3toVYygKM92mYxnXia/NpFLviJg+0iHoIywqGjpa5YGU/7x4IfklkO0/BdCerq3+PrzepD9FI0LnVxKaoki3QpgTb+HSg9IIgWd+alj8YFcRklvwzySZN0ACGrKfyOsb8cxJXn9+n5mR/EDOrG9ET0yjW4d9eonP7R+3yx0h0Ihb0EBoLAUI0u0C9oL07j23+ZArbLjQH4dKHiXwPlmTdjTyQk4UyXHiJgUPmeCQw6YjTOj+ZsgIyEQSSmiaETPG81voIAuGMaWvRF4gTmzCF9cpb1JOubk/2Kp/39ow9av8NxxeI4XmlUVV8ogaC6WnLTytTATRZSqoyHV36R391vO6tQ3KC7/EAin+0RmyFkKbjBWXMS5I+GhSRkvXz9gMgGsfCgAsId+aE85chReUK92TX+/Q22p7a7DOwS+4mQqndV45GjgSJiTeSkqEcMre6VtG+NdgDfV0WMvOAr+jI8WpKXpuOOBIhswiG0wcroT3Bya2FLvGzMfKWm5Q8IE4gfhEzZmJTLYg7deHGjMV+3o/nM+BMswNpVPgnmjGy00VfzH1O5pq2QiXTJrFAqAAY70Ri/xxTdmnXvh+W83NH8MCAwEAAQ==", 0)));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            String str = TAG;
            HVLogUtils.e(str, "getPublicKey(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
            return null;
        }
    }

    private String createClientHmacHash(String str, String str2) {
        HVLogUtils.d(TAG, "createClientHmacHash() called with: hashInput = [" + str + "], secret = [" + str2 + "]");
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(str2.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            return SignatureHelper.bytesToHex(mac.doFinal(str.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            String str3 = TAG;
            HVLogUtils.e(str3, "createClientHmacHash(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str3, Utils.getErrorMessage(e));
            return null;
        }
    }

    private String getSecret() {
        String str = "";
        HVLogUtils.d(TAG, "getSecret() called");
        try {
            JSONObject jSONObject = this.requestHeaders;
            if (jSONObject != null) {
                if (jSONObject.has("transactionId")) {
                    str = this.requestHeaders.getString("transactionId");
                }
            } else {
                JSONObject jSONObject2 = this.responseHeaders;
                if (jSONObject2 != null) {
                    if (jSONObject2.has(AppConstants.HV_REQUEST_ID)) {
                        str = this.responseHeaders.getString(AppConstants.HV_REQUEST_ID);
                    } else if (this.responseHeaders.has("x-hv-request-id")) {
                        str = this.responseHeaders.getString("x-hv-request-id");
                    }
                } else if (this.responseBody.has(TtmlNode.TAG_METADATA)) {
                    JSONObject jSONObject3 = this.responseBody.getJSONObject(TtmlNode.TAG_METADATA);
                    if (jSONObject3.has("transactionId")) {
                        str = jSONObject3.getString("transactionId");
                    } else {
                        str = jSONObject3.getString("requestId");
                    }
                }
            }
        } catch (JSONException e) {
            String str2 = TAG;
            HVLogUtils.e(str2, "getSecret(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str2, Utils.getErrorMessage(e));
        }
        return str;
    }

    private String createHashInput() {
        HVLogUtils.d(TAG, "createHashInput() called");
        String sortJsonKeys = sortJsonKeys(this.requestQuery);
        return sortedHashOfImages() + sortJsonKeys + sortJsonKeys(this.requestBody) + sortJsonKeys(this.responseBody);
    }

    private String sortedHashOfImages() {
        HVLogUtils.d(TAG, "sortedHashOfImages() called");
        TreeMap treeMap = new TreeMap();
        for (String str : this.tagFileUriMap.keySet()) {
            treeMap.put(str, SignatureHelper.getHashOfImage(this.tagFileUriMap.get(str), "MD5"));
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = treeMap.values().iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
        }
        return sb.toString();
    }

    private String sortJsonKeys(JSONObject jSONObject) {
        HVLogUtils.d(TAG, "sortJsonKeys() called with: jsonObject = [" + jSONObject + "]");
        if (jSONObject == null || jSONObject.length() == 0) {
            return "";
        }
        try {
            return HVSignatureService.sortJSONKeysAlphabetically(HVSignatureService.convertJSONObjToMap(jSONObject));
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            return "";
        }
    }
}
