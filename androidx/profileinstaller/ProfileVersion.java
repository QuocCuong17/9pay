package androidx.profileinstaller;

import java.util.Arrays;
import net.sf.scuba.smartcards.ISO7816;

/* loaded from: classes.dex */
public class ProfileVersion {
    public static final int MAX_SUPPORTED_SDK = 34;
    public static final int MIN_SUPPORTED_SDK = 24;
    static final byte[] V015_S = {ISO7816.INS_DECREASE, 49, 53, 0};
    static final byte[] V010_P = {ISO7816.INS_DECREASE, 49, ISO7816.INS_DECREASE, 0};
    static final byte[] V009_O_MR1 = {ISO7816.INS_DECREASE, ISO7816.INS_DECREASE, 57, 0};
    static final byte[] V005_O = {ISO7816.INS_DECREASE, ISO7816.INS_DECREASE, 53, 0};
    static final byte[] V001_N = {ISO7816.INS_DECREASE, ISO7816.INS_DECREASE, 49, 0};
    static final byte[] METADATA_V001_N = {ISO7816.INS_DECREASE, ISO7816.INS_DECREASE, 49, 0};
    static final byte[] METADATA_V002 = {ISO7816.INS_DECREASE, ISO7816.INS_DECREASE, ISO7816.INS_INCREASE, 0};

    private ProfileVersion() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String dexKeySeparator(byte[] bArr) {
        return (Arrays.equals(bArr, V001_N) || Arrays.equals(bArr, V005_O)) ? ":" : "!";
    }
}
