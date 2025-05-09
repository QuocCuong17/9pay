package com.facebook.appevents.ml;

import android.text.TextUtils;
import com.facebook.FacebookSdk;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Utils.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004J\u001e\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\u0006H\u0007J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/facebook/appevents/ml/Utils;", "", "()V", "DIR_NAME", "", "getMlDir", "Ljava/io/File;", "normalizeString", "str", "parseModelWeights", "", "Lcom/facebook/appevents/ml/MTensor;", "file", "vectorize", "", "texts", "maxLen", "", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Utils {
    private static final String DIR_NAME = "facebook_ml/";
    public static final Utils INSTANCE = new Utils();

    private Utils() {
    }

    public final int[] vectorize(String texts, int maxLen) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(texts, "texts");
            int[] iArr = new int[maxLen];
            String normalizeString = normalizeString(texts);
            Charset forName = Charset.forName("UTF-8");
            Intrinsics.checkNotNullExpressionValue(forName, "forName(\"UTF-8\")");
            byte[] bytes = normalizeString.getBytes(forName);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            for (int i = 0; i < maxLen; i++) {
                if (i < bytes.length) {
                    iArr[i] = bytes[i] & 255;
                } else {
                    iArr[i] = 0;
                }
            }
            return iArr;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    @JvmStatic
    public static final File getMlDir() {
        if (CrashShieldHandler.isObjectCrashing(Utils.class)) {
            return null;
        }
        try {
            File file = new File(FacebookSdk.getApplicationContext().getFilesDir(), DIR_NAME);
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    return null;
                }
            }
            return file;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Utils.class);
            return null;
        }
    }

    @JvmStatic
    public static final Map<String, MTensor> parseModelWeights(File file) {
        int i;
        if (CrashShieldHandler.isObjectCrashing(Utils.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(file, "file");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                int available = fileInputStream.available();
                DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                byte[] bArr = new byte[available];
                dataInputStream.readFully(bArr);
                dataInputStream.close();
                if (available < 4) {
                    return null;
                }
                int i2 = 0;
                ByteBuffer wrap = ByteBuffer.wrap(bArr, 0, 4);
                wrap.order(ByteOrder.LITTLE_ENDIAN);
                int i3 = wrap.getInt();
                int i4 = i3 + 4;
                if (available < i4) {
                    return null;
                }
                JSONObject jSONObject = new JSONObject(new String(bArr, 4, i3, Charsets.UTF_8));
                JSONArray names = jSONObject.names();
                int length = names.length();
                String[] strArr = new String[length];
                for (int i5 = 0; i5 < length; i5++) {
                    strArr[i5] = names.getString(i5);
                }
                ArraysKt.sort((Object[]) strArr);
                HashMap hashMap = new HashMap();
                int i6 = 0;
                while (i6 < length) {
                    String str = strArr[i6];
                    if (str == null) {
                        i = i2;
                    } else {
                        JSONArray jSONArray = jSONObject.getJSONArray(str);
                        int length2 = jSONArray.length();
                        int[] iArr = new int[length2];
                        int i7 = 1;
                        while (i2 < length2) {
                            iArr[i2] = jSONArray.getInt(i2);
                            i7 *= iArr[i2];
                            i2++;
                        }
                        int i8 = i7 * 4;
                        int i9 = i4 + i8;
                        if (i9 > available) {
                            return null;
                        }
                        ByteBuffer wrap2 = ByteBuffer.wrap(bArr, i4, i8);
                        wrap2.order(ByteOrder.LITTLE_ENDIAN);
                        MTensor mTensor = new MTensor(iArr);
                        i = 0;
                        wrap2.asFloatBuffer().get(mTensor.getData(), 0, i7);
                        hashMap.put(str, mTensor);
                        i4 = i9;
                    }
                    i6++;
                    i2 = i;
                }
                return hashMap;
            } catch (Exception unused) {
                return null;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Utils.class);
            return null;
        }
    }

    public final String normalizeString(String str) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(str, "str");
            String str2 = str;
            int length = str2.length() - 1;
            int i = 0;
            boolean z = false;
            while (i <= length) {
                boolean z2 = Intrinsics.compare((int) str2.charAt(!z ? i : length), 32) <= 0;
                if (z) {
                    if (!z2) {
                        break;
                    }
                    length--;
                } else if (z2) {
                    i++;
                } else {
                    z = true;
                }
            }
            String join = TextUtils.join(" ", (String[]) new Regex("\\s+").split(str2.subSequence(i, length + 1).toString(), 0).toArray(new String[0]));
            Intrinsics.checkNotNullExpressionValue(join, "join(\" \", strArray)");
            return join;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }
}
