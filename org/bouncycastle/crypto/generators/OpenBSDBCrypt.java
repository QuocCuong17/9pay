package org.bouncycastle.crypto.generators;

import com.otaliastudios.transcoder.internal.utils.AvcSpsUtils;
import java.io.ByteArrayOutputStream;
import java.util.HashSet;
import java.util.Set;
import kotlin.text.Typography;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* loaded from: classes5.dex */
public class OpenBSDBCrypt {
    private static final Set<String> allowedVersions;
    private static final String defaultVersion = "2y";
    private static final byte[] encodingTable = {46, 47, 65, 66, 67, ISO7816.INS_REHABILITATE_CHV, 69, 70, 71, 72, 73, 74, 75, 76, AvcSpsUtils.PROFILE_IDC_MAIN, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, AvcSpsUtils.PROFILE_IDC_EXTENDED, 89, 90, 97, ISOFileInfo.FCP_BYTE, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, ISOFileInfo.FCI_BYTE, ISO7816.INS_MANAGE_CHANNEL, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, ISO7816.INS_DECREASE, 49, ISO7816.INS_INCREASE, 51, ISO7816.INS_DECREASE_STAMPED, 53, 54, 55, 56, 57};
    private static final byte[] decodingTable = new byte[128];

    static {
        HashSet hashSet = new HashSet();
        allowedVersions = hashSet;
        hashSet.add("2a");
        hashSet.add(defaultVersion);
        hashSet.add("2b");
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = decodingTable;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        while (true) {
            byte[] bArr2 = encodingTable;
            if (i >= bArr2.length) {
                return;
            }
            decodingTable[bArr2[i]] = (byte) i;
            i++;
        }
    }

    public static boolean checkPassword(String str, char[] cArr) {
        if (str.length() != 60) {
            throw new DataLengthException("Bcrypt String length: " + str.length() + ", 60 required.");
        }
        if (str.charAt(0) != '$' || str.charAt(3) != '$' || str.charAt(6) != '$') {
            throw new IllegalArgumentException("Invalid Bcrypt String format.");
        }
        String substring = str.substring(1, 3);
        if (!allowedVersions.contains(substring)) {
            throw new IllegalArgumentException("Bcrypt version '" + substring + "' is not supported by this implementation");
        }
        String substring2 = str.substring(4, 6);
        try {
            int parseInt = Integer.parseInt(substring2);
            if (parseInt >= 4 && parseInt <= 31) {
                if (cArr != null) {
                    return str.equals(generate(substring, cArr, decodeSaltString(str.substring(str.lastIndexOf(36) + 1, str.length() - 31)), parseInt));
                }
                throw new IllegalArgumentException("Missing password.");
            }
            throw new IllegalArgumentException("Invalid cost factor: " + parseInt + ", 4 < cost < 31 expected.");
        } catch (NumberFormatException unused) {
            throw new IllegalArgumentException("Invalid cost factor: " + substring2);
        }
    }

    private static String createBcryptString(String str, byte[] bArr, byte[] bArr2, int i) {
        String num;
        if (!allowedVersions.contains(str)) {
            throw new IllegalArgumentException("Version " + str + " is not accepted by this implementation.");
        }
        StringBuffer stringBuffer = new StringBuffer(60);
        stringBuffer.append(Typography.dollar);
        stringBuffer.append(str);
        stringBuffer.append(Typography.dollar);
        if (i < 10) {
            num = "0" + i;
        } else {
            num = Integer.toString(i);
        }
        stringBuffer.append(num);
        stringBuffer.append(Typography.dollar);
        stringBuffer.append(encodeData(bArr2));
        stringBuffer.append(encodeData(BCrypt.generate(bArr, bArr2, i)));
        return stringBuffer.toString();
    }

    private static byte[] decodeSaltString(String str) {
        char[] charArray = str.toCharArray();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16);
        if (charArray.length != 22) {
            throw new DataLengthException("Invalid base64 salt length: " + charArray.length + " , 22 required.");
        }
        for (char c : charArray) {
            if (c > 'z' || c < '.' || (c > '9' && c < 'A')) {
                throw new IllegalArgumentException("Salt string contains invalid character: " + ((int) c));
            }
        }
        char[] cArr = new char[24];
        System.arraycopy(charArray, 0, cArr, 0, charArray.length);
        for (int i = 0; i < 24; i += 4) {
            byte[] bArr = decodingTable;
            byte b = bArr[cArr[i]];
            byte b2 = bArr[cArr[i + 1]];
            byte b3 = bArr[cArr[i + 2]];
            byte b4 = bArr[cArr[i + 3]];
            byteArrayOutputStream.write((b << 2) | (b2 >> 4));
            byteArrayOutputStream.write((b2 << 4) | (b3 >> 2));
            byteArrayOutputStream.write(b4 | (b3 << 6));
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byte[] bArr2 = new byte[16];
        System.arraycopy(byteArray, 0, bArr2, 0, 16);
        return bArr2;
    }

    private static String encodeData(byte[] bArr) {
        boolean z;
        if (bArr.length != 24 && bArr.length != 16) {
            throw new DataLengthException("Invalid length: " + bArr.length + ", 24 for key or 16 for salt expected");
        }
        if (bArr.length == 16) {
            byte[] bArr2 = new byte[18];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            bArr = bArr2;
            z = true;
        } else {
            bArr[bArr.length - 1] = 0;
            z = false;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = bArr.length;
        for (int i = 0; i < length; i += 3) {
            int i2 = bArr[i] & 255;
            int i3 = bArr[i + 1] & 255;
            int i4 = bArr[i + 2] & 255;
            byte[] bArr3 = encodingTable;
            byteArrayOutputStream.write(bArr3[(i2 >>> 2) & 63]);
            byteArrayOutputStream.write(bArr3[((i2 << 4) | (i3 >>> 4)) & 63]);
            byteArrayOutputStream.write(bArr3[((i3 << 2) | (i4 >>> 6)) & 63]);
            byteArrayOutputStream.write(bArr3[i4 & 63]);
        }
        String fromByteArray = Strings.fromByteArray(byteArrayOutputStream.toByteArray());
        return fromByteArray.substring(0, z ? 22 : fromByteArray.length() - 1);
    }

    public static String generate(String str, char[] cArr, byte[] bArr, int i) {
        if (!allowedVersions.contains(str)) {
            throw new IllegalArgumentException("Version " + str + " is not accepted by this implementation.");
        }
        if (cArr == null) {
            throw new IllegalArgumentException("Password required.");
        }
        if (bArr == null) {
            throw new IllegalArgumentException("Salt required.");
        }
        if (bArr.length != 16) {
            throw new DataLengthException("16 byte salt required: " + bArr.length);
        }
        if (i < 4 || i > 31) {
            throw new IllegalArgumentException("Invalid cost factor.");
        }
        byte[] uTF8ByteArray = Strings.toUTF8ByteArray(cArr);
        int length = uTF8ByteArray.length < 72 ? uTF8ByteArray.length + 1 : 72;
        byte[] bArr2 = new byte[length];
        if (length > uTF8ByteArray.length) {
            length = uTF8ByteArray.length;
        }
        System.arraycopy(uTF8ByteArray, 0, bArr2, 0, length);
        Arrays.fill(uTF8ByteArray, (byte) 0);
        String createBcryptString = createBcryptString(str, bArr2, bArr, i);
        Arrays.fill(bArr2, (byte) 0);
        return createBcryptString;
    }

    public static String generate(char[] cArr, byte[] bArr, int i) {
        return generate(defaultVersion, cArr, bArr, i);
    }
}
