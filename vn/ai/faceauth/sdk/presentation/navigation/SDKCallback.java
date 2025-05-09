package vn.ai.faceauth.sdk.presentation.navigation;

import kotlin.Metadata;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001JD\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u0007H&¨\u0006\f"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/navigation/SDKCallback;", "", "complete", "", "code", "", "errorMessage", "", "firstFace", "lastFace", "signature", "encryptedFaceImages", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface SDKCallback {

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void complete$default(SDKCallback sDKCallback, int i, String str, String str2, String str3, String str4, String str5, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException(tfwhgw.rnigpa(56));
            }
            if ((i2 & 1) != 0) {
                i = 0;
            }
            sDKCallback.complete(i, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? "" : str2, (i2 & 8) != 0 ? "" : str3, (i2 & 16) != 0 ? "" : str4, (i2 & 32) == 0 ? str5 : "");
        }
    }

    void complete(int code, String errorMessage, String firstFace, String lastFace, String signature, String encryptedFaceImages);
}
