package vn.ai.faceauth.sdk.presentation.presentation.utils;

import android.util.Base64;
import android.util.Log;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import lmlf.ayxnhy.hrn;
import lmlf.ayxnhy.tfwhgw;

/* loaded from: classes6.dex */
public class BShield {
    public static HashMap<String, String> shieldData(List<String> list, String str) {
        byte[] shwerg = new hrn(list, str).shwerg();
        if (shwerg == null || shwerg.length < 32) {
            Log.d(tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_Y_POSITION), tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_FREE_OFFSETS));
            return null;
        }
        byte[] copyOfRange = Arrays.copyOfRange(shwerg, 0, 32);
        byte[] copyOfRange2 = Arrays.copyOfRange(shwerg, 32, shwerg.length);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_PAGE_NAME), Base64.encodeToString(copyOfRange2, 2));
        hashMap.put(tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_X_POSITION), Base64.encodeToString(copyOfRange, 2));
        return hashMap;
    }
}
