package org.apache.pdfbox.filter.ccitt;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import com.otaliastudios.transcoder.internal.utils.AvcSpsUtils;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.io.encoding.Base64;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;
import okio.Utf8;
import org.bouncycastle.crypto.signers.PSSSigner;

/* loaded from: classes5.dex */
public final class FillOrderChangeInputStream extends FilterInputStream {
    private static final byte[] FLIP_TABLE = {0, Byte.MIN_VALUE, SignedBytes.MAX_POWER_OF_TWO, ISO7816.INS_GET_RESPONSE, 32, ISOFileInfo.A0, 96, ISO7816.INS_CREATE_FILE, 16, -112, 80, ISO7816.INS_WRITE_BINARY, ISO7816.INS_DECREASE, ISO7816.INS_READ_BINARY, ISO7816.INS_MANAGE_CHANNEL, -16, 8, -120, 72, -56, 40, -88, 104, -24, Ascii.CAN, -104, AvcSpsUtils.PROFILE_IDC_EXTENDED, ISO7816.INS_LOAD_KEY_FILE, 56, -72, 120, -8, 4, -124, ISO7816.INS_REHABILITATE_CHV, -60, ISO7816.INS_CHANGE_CHV, -92, 100, ISO7816.INS_DELETE_FILE, Ascii.DC4, -108, 84, -44, ISO7816.INS_DECREASE_STAMPED, ISO7816.INS_READ_BINARY_STAMPED, 116, -12, 12, ISOFileInfo.SECURITY_ATTR_COMPACT, 76, -52, ISO7816.INS_UNBLOCK_CHV, -84, 108, -20, 28, -100, 92, ISO7816.INS_UPDATE_RECORD, 60, PSSSigner.TRAILER_IMPLICIT, 124, -4, 2, -126, 66, ISO7816.INS_ENVELOPE, ISO7816.INS_MSE, -94, ISOFileInfo.FCP_BYTE, ISO7816.INS_APPEND_RECORD, Ascii.DC2, -110, 82, ISO7816.INS_WRITE_RECORD, ISO7816.INS_INCREASE, -78, 114, -14, 10, ISOFileInfo.LCS_BYTE, 74, ISO7816.INS_GET_DATA, ISO7816.INS_PSO, -86, 106, -22, Ascii.SUB, -102, 90, ISO7816.INS_PUT_DATA, 58, -70, 122, -6, 6, -122, 70, -58, 38, -90, 102, -26, Ascii.SYN, -106, 86, ISO7816.INS_UPDATE_BINARY, 54, ISO7816.INS_READ_RECORD_STAMPED, 118, -10, 14, ISOFileInfo.CHANNEL_SECURITY, 78, -50, 46, -82, 110, -18, 30, -98, 94, -34, 62, -66, 126, -2, 1, ISOFileInfo.DATA_BYTES2, 65, -63, 33, ISOFileInfo.A1, 97, -31, 17, -111, 81, -47, 49, ISO7816.INS_READ_BINARY2, 113, -15, 9, -119, 73, -55, 41, -87, 105, -23, Ascii.EM, -103, 89, -39, 57, -71, 121, -7, 5, ISOFileInfo.PROP_INFO, 69, -59, 37, ISOFileInfo.A5, 101, -27, Ascii.NAK, -107, 85, -43, 53, -75, 117, -11, 13, ISOFileInfo.ENV_TEMP_EF, AvcSpsUtils.PROFILE_IDC_MAIN, -51, 45, -83, 109, -19, 29, -99, 93, -35, Base64.padSymbol, -67, 125, -3, 3, ISOFileInfo.FILE_IDENTIFIER, 67, -61, 35, -93, 99, -29, 19, -109, 83, -45, 51, ISO7816.INS_READ_RECORD2, 115, -13, 11, ISOFileInfo.SECURITY_ATTR_EXP, 75, -53, 43, ISOFileInfo.AB, 107, -21, Ascii.ESC, -101, 91, -37, 59, ByteSourceJsonBootstrapper.UTF8_BOM_2, 123, -5, 7, ISOFileInfo.FCI_EXT, 71, -57, 39, -89, 103, -25, Ascii.ETB, -105, 87, -41, 55, -73, 119, -9, 15, -113, 79, -49, 47, -81, ISOFileInfo.FCI_BYTE, ByteSourceJsonBootstrapper.UTF8_BOM_1, Ascii.US, -97, 95, -33, Utf8.REPLACEMENT_BYTE, ByteSourceJsonBootstrapper.UTF8_BOM_3, Byte.MAX_VALUE, -1};

    public FillOrderChangeInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = super.read(bArr, i, i2);
        if (read > 0) {
            int i3 = i + read;
            while (i < i3) {
                bArr[i] = FLIP_TABLE[bArr[i] & 255];
                i++;
            }
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = super.read();
        return read < 0 ? read : FLIP_TABLE[read] & 255;
    }
}
