package org.bouncycastle.crypto.engines;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import com.otaliastudios.transcoder.internal.utils.AvcSpsUtils;
import kotlin.io.encoding.Base64;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;
import okio.Utf8;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class DSTU7624Engine implements BlockCipher {
    private static final int ROUNDS_128 = 10;
    private static final int ROUNDS_256 = 14;
    private static final int ROUNDS_512 = 18;
    private static final byte[] S0 = {-88, 67, 95, 6, 107, 117, 108, 89, 113, -33, ISOFileInfo.FCI_EXT, -107, Ascii.ETB, -16, ISO7816.INS_LOAD_KEY_FILE, 9, 109, -13, 29, -53, -55, AvcSpsUtils.PROFILE_IDC_MAIN, ISO7816.INS_UNBLOCK_CHV, -81, 121, ISO7816.INS_CREATE_FILE, -105, -3, ISOFileInfo.FCI_BYTE, 75, 69, 57, 62, -35, -93, 79, ISO7816.INS_READ_BINARY_STAMPED, ISO7816.INS_READ_RECORD_STAMPED, -102, 14, Ascii.US, ByteSourceJsonBootstrapper.UTF8_BOM_3, Ascii.NAK, -31, 73, ISO7816.INS_WRITE_RECORD, -109, -58, -110, 114, -98, 97, -47, 99, -6, -18, -12, Ascii.EM, -43, -83, AvcSpsUtils.PROFILE_IDC_EXTENDED, -92, ByteSourceJsonBootstrapper.UTF8_BOM_2, ISOFileInfo.A1, ISO7816.INS_UPDATE_RECORD, -14, ISOFileInfo.FILE_IDENTIFIER, 55, 66, ISO7816.INS_DELETE_FILE, 122, ISO7816.INS_INCREASE, -100, -52, ISOFileInfo.AB, 74, -113, 110, 4, 39, 46, -25, ISO7816.INS_APPEND_RECORD, 90, -106, Ascii.SYN, 35, 43, ISO7816.INS_ENVELOPE, 101, 102, 15, PSSSigner.TRAILER_IMPLICIT, -87, 71, 65, ISO7816.INS_DECREASE_STAMPED, 72, -4, -73, 106, -120, ISOFileInfo.A5, 83, -122, -7, 91, -37, 56, 123, -61, 30, ISO7816.INS_MSE, 51, ISO7816.INS_CHANGE_CHV, 40, 54, -57, -78, 59, ISOFileInfo.CHANNEL_SECURITY, 119, -70, -11, Ascii.DC4, -97, 8, 85, -101, 76, -2, 96, 92, ISO7816.INS_PUT_DATA, Ascii.CAN, 70, -51, 125, 33, ISO7816.INS_READ_BINARY, Utf8.REPLACEMENT_BYTE, Ascii.ESC, -119, -1, -21, -124, 105, 58, -99, -41, -45, ISO7816.INS_MANAGE_CHANNEL, 103, SignedBytes.MAX_POWER_OF_TWO, -75, -34, 93, ISO7816.INS_DECREASE, -111, ISO7816.INS_READ_BINARY2, 120, 17, 1, -27, 0, 104, -104, ISOFileInfo.A0, -59, 2, -90, 116, 45, 11, -94, 118, ISO7816.INS_READ_RECORD2, -66, -50, -67, -82, -23, ISOFileInfo.LCS_BYTE, 49, 28, -20, -15, -103, -108, -86, -10, 38, 47, ByteSourceJsonBootstrapper.UTF8_BOM_1, -24, ISOFileInfo.SECURITY_ATTR_COMPACT, 53, 3, -44, Byte.MAX_VALUE, -5, 5, -63, 94, -112, 32, Base64.padSymbol, -126, -9, -22, 10, 13, 126, -8, 80, Ascii.SUB, -60, 7, 87, -72, 60, ISOFileInfo.FCP_BYTE, -29, -56, -84, 82, 100, 16, ISO7816.INS_WRITE_BINARY, -39, 19, 12, Ascii.DC2, 41, 81, -71, -49, ISO7816.INS_UPDATE_BINARY, 115, ISOFileInfo.ENV_TEMP_EF, ISOFileInfo.DATA_BYTES2, 84, ISO7816.INS_GET_RESPONSE, -19, 78, ISO7816.INS_REHABILITATE_CHV, -89, ISO7816.INS_PSO, ISOFileInfo.PROP_INFO, 37, -26, ISO7816.INS_GET_DATA, 124, ISOFileInfo.SECURITY_ATTR_EXP, 86, Byte.MIN_VALUE};
    private static final byte[] S1 = {-50, ByteSourceJsonBootstrapper.UTF8_BOM_2, -21, -110, -22, -53, 19, -63, -23, 58, ISO7816.INS_UPDATE_BINARY, -78, ISO7816.INS_WRITE_RECORD, -112, Ascii.ETB, -8, 66, Ascii.NAK, 86, ISO7816.INS_READ_BINARY_STAMPED, 101, 28, -120, 67, -59, 92, 54, -70, -11, 87, 103, ISOFileInfo.ENV_TEMP_EF, 49, -10, 100, AvcSpsUtils.PROFILE_IDC_EXTENDED, -98, -12, ISO7816.INS_MSE, -86, 117, 15, 2, ISO7816.INS_READ_BINARY2, -33, 109, 115, AvcSpsUtils.PROFILE_IDC_MAIN, 124, 38, 46, -9, 8, 93, ISO7816.INS_REHABILITATE_CHV, 62, -97, Ascii.DC4, -56, -82, 84, 16, ISO7816.INS_LOAD_KEY_FILE, PSSSigner.TRAILER_IMPLICIT, Ascii.SUB, 107, 105, -13, -67, 51, ISOFileInfo.AB, -6, -47, -101, 104, 78, Ascii.SYN, -107, -111, -18, 76, 99, ISOFileInfo.CHANNEL_SECURITY, 91, -52, 60, Ascii.EM, ISOFileInfo.A1, ISOFileInfo.DATA_BYTES2, 73, 123, -39, ISOFileInfo.FCI_BYTE, 55, 96, ISO7816.INS_GET_DATA, -25, 43, 72, -3, -106, 69, -4, 65, Ascii.DC2, 13, 121, -27, -119, ISOFileInfo.SECURITY_ATTR_COMPACT, -29, 32, ISO7816.INS_DECREASE, ISO7816.INS_UPDATE_RECORD, -73, 108, 74, -75, Utf8.REPLACEMENT_BYTE, -105, -44, ISOFileInfo.FCP_BYTE, 45, 6, -92, ISOFileInfo.A5, ISOFileInfo.FILE_IDENTIFIER, 95, ISO7816.INS_PSO, ISO7816.INS_PUT_DATA, -55, 0, 126, -94, 85, ByteSourceJsonBootstrapper.UTF8_BOM_3, 17, -43, -100, -49, 14, 10, Base64.padSymbol, 81, 125, -109, Ascii.ESC, -2, -60, 71, 9, -122, 11, -113, -99, 106, 7, -71, ISO7816.INS_READ_BINARY, -104, Ascii.CAN, ISO7816.INS_INCREASE, 113, 75, ByteSourceJsonBootstrapper.UTF8_BOM_1, 59, ISO7816.INS_MANAGE_CHANNEL, ISOFileInfo.A0, ISO7816.INS_DELETE_FILE, SignedBytes.MAX_POWER_OF_TWO, -1, -61, -87, -26, 120, -7, ISOFileInfo.SECURITY_ATTR_EXP, 70, Byte.MIN_VALUE, 30, 56, -31, -72, -88, ISO7816.INS_CREATE_FILE, 12, 35, 118, 29, 37, ISO7816.INS_CHANGE_CHV, 5, -15, 110, -108, 40, -102, -124, -24, -93, 79, 119, -45, ISOFileInfo.PROP_INFO, ISO7816.INS_APPEND_RECORD, 82, -14, -126, 80, 122, 47, 116, 83, ISO7816.INS_READ_RECORD2, 97, -81, 57, 53, -34, -51, Ascii.US, -103, -84, -83, 114, ISO7816.INS_UNBLOCK_CHV, -35, ISO7816.INS_WRITE_BINARY, ISOFileInfo.FCI_EXT, -66, 94, -90, -20, 4, -58, 3, ISO7816.INS_DECREASE_STAMPED, -5, -37, 89, ISO7816.INS_READ_RECORD_STAMPED, ISO7816.INS_ENVELOPE, 1, -16, 90, -19, -89, 102, 33, Byte.MAX_VALUE, ISOFileInfo.LCS_BYTE, 39, -57, ISO7816.INS_GET_RESPONSE, 41, -41};
    private static final byte[] S2 = {-109, -39, -102, -75, -104, ISO7816.INS_MSE, 69, -4, -70, 106, -33, 2, -97, ISO7816.INS_UPDATE_RECORD, 81, 89, 74, Ascii.ETB, 43, ISO7816.INS_ENVELOPE, -108, -12, ByteSourceJsonBootstrapper.UTF8_BOM_2, -93, ISOFileInfo.FCP_BYTE, ISO7816.INS_DELETE_FILE, 113, -44, -51, ISO7816.INS_MANAGE_CHANNEL, Ascii.SYN, -31, 73, 60, ISO7816.INS_GET_RESPONSE, ISO7816.INS_LOAD_KEY_FILE, 92, -101, -83, ISOFileInfo.PROP_INFO, 83, ISOFileInfo.A1, 122, -56, 45, ISO7816.INS_CREATE_FILE, -47, 114, -90, ISO7816.INS_UNBLOCK_CHV, -60, -29, 118, 120, -73, ISO7816.INS_READ_BINARY_STAMPED, 9, 59, 14, 65, 76, -34, -78, -112, 37, ISOFileInfo.A5, -41, 3, 17, 0, -61, 46, -110, ByteSourceJsonBootstrapper.UTF8_BOM_1, 78, Ascii.DC2, -99, 125, -53, 53, 16, -43, 79, -98, AvcSpsUtils.PROFILE_IDC_MAIN, -87, 85, -58, ISO7816.INS_WRITE_BINARY, 123, Ascii.CAN, -105, -45, 54, -26, 72, 86, ISOFileInfo.DATA_BYTES2, -113, 119, -52, -100, -71, ISO7816.INS_APPEND_RECORD, -84, -72, 47, Ascii.NAK, -92, 124, ISO7816.INS_PUT_DATA, 56, 30, 11, 5, ISO7816.INS_UPDATE_BINARY, Ascii.DC4, 110, 108, 126, 102, -3, ISO7816.INS_READ_BINARY2, -27, 96, -81, 94, 51, ISOFileInfo.FCI_EXT, -55, -16, 93, 109, Utf8.REPLACEMENT_BYTE, -120, ISOFileInfo.ENV_TEMP_EF, -57, -9, 29, -23, -20, -19, Byte.MIN_VALUE, 41, 39, -49, -103, -88, 80, 15, 55, ISO7816.INS_CHANGE_CHV, 40, ISO7816.INS_DECREASE, -107, ISO7816.INS_WRITE_RECORD, 62, 91, SignedBytes.MAX_POWER_OF_TWO, ISOFileInfo.FILE_IDENTIFIER, ISO7816.INS_READ_RECORD2, 105, 87, Ascii.US, 7, 28, ISOFileInfo.LCS_BYTE, PSSSigner.TRAILER_IMPLICIT, 32, -21, -50, ISOFileInfo.CHANNEL_SECURITY, ISOFileInfo.AB, -18, 49, -94, 115, -7, ISO7816.INS_GET_DATA, 58, Ascii.SUB, -5, 13, -63, -2, -6, -14, ISOFileInfo.FCI_BYTE, -67, -106, -35, 67, 82, ISO7816.INS_READ_RECORD_STAMPED, 8, -13, -82, -66, Ascii.EM, -119, ISO7816.INS_INCREASE, 38, ISO7816.INS_READ_BINARY, -22, 75, 100, -124, -126, 107, -11, 121, ByteSourceJsonBootstrapper.UTF8_BOM_3, 1, 95, 117, 99, Ascii.ESC, 35, Base64.padSymbol, 104, ISO7816.INS_PSO, 101, -24, -111, -10, -1, 19, AvcSpsUtils.PROFILE_IDC_EXTENDED, -15, 71, 10, Byte.MAX_VALUE, -59, -89, -25, 97, 90, 6, 70, ISO7816.INS_REHABILITATE_CHV, 66, 4, ISOFileInfo.A0, -37, 57, -122, 84, -86, ISOFileInfo.SECURITY_ATTR_COMPACT, ISO7816.INS_DECREASE_STAMPED, 33, ISOFileInfo.SECURITY_ATTR_EXP, -8, 12, 116, 103};
    private static final byte[] S3 = {104, ISOFileInfo.ENV_TEMP_EF, ISO7816.INS_GET_DATA, AvcSpsUtils.PROFILE_IDC_MAIN, 115, 75, 78, ISO7816.INS_PSO, -44, 82, 38, ISO7816.INS_READ_RECORD2, 84, 30, Ascii.EM, Ascii.US, ISO7816.INS_MSE, 3, 70, Base64.padSymbol, 45, 74, 83, ISOFileInfo.FILE_IDENTIFIER, 19, ISOFileInfo.LCS_BYTE, -73, -43, 37, 121, -11, -67, AvcSpsUtils.PROFILE_IDC_EXTENDED, 47, 13, 2, -19, 81, -98, 17, -14, 62, 85, 94, -47, Ascii.SYN, 60, 102, ISO7816.INS_MANAGE_CHANNEL, 93, -13, 69, SignedBytes.MAX_POWER_OF_TWO, -52, -24, -108, 86, 8, -50, Ascii.SUB, 58, ISO7816.INS_WRITE_RECORD, -31, -33, -75, 56, 110, 14, -27, -12, -7, -122, -23, 79, ISO7816.INS_UPDATE_BINARY, ISOFileInfo.PROP_INFO, 35, -49, ISO7816.INS_INCREASE, -103, 49, Ascii.DC4, -82, -18, -56, 72, -45, ISO7816.INS_DECREASE, ISOFileInfo.A1, -110, 65, ISO7816.INS_READ_BINARY2, Ascii.CAN, -60, ISO7816.INS_UNBLOCK_CHV, 113, 114, ISO7816.INS_REHABILITATE_CHV, Ascii.NAK, -3, 55, -66, 95, -86, -101, -120, ISO7816.INS_LOAD_KEY_FILE, ISOFileInfo.AB, -119, -100, -6, 96, -22, PSSSigner.TRAILER_IMPLICIT, ISOFileInfo.FCP_BYTE, 12, ISO7816.INS_CHANGE_CHV, -90, -88, -20, 103, 32, -37, 124, 40, -35, -84, 91, ISO7816.INS_DECREASE_STAMPED, 126, 16, -15, 123, -113, 99, ISOFileInfo.A0, 5, -102, 67, 119, 33, ByteSourceJsonBootstrapper.UTF8_BOM_3, 39, 9, -61, -97, ISO7816.INS_READ_RECORD_STAMPED, -41, 41, ISO7816.INS_ENVELOPE, -21, ISO7816.INS_GET_RESPONSE, -92, ISOFileInfo.SECURITY_ATTR_EXP, ISOFileInfo.SECURITY_ATTR_COMPACT, 29, -5, -1, -63, -78, -105, 46, -8, 101, -10, 117, 7, 4, 73, 51, ISO7816.INS_DELETE_FILE, -39, -71, ISO7816.INS_WRITE_BINARY, 66, -57, 108, -112, 0, ISOFileInfo.CHANNEL_SECURITY, ISOFileInfo.FCI_BYTE, 80, 1, -59, ISO7816.INS_PUT_DATA, 71, Utf8.REPLACEMENT_BYTE, -51, 105, -94, ISO7816.INS_APPEND_RECORD, 122, -89, -58, -109, 15, 10, 6, -26, 43, -106, -93, 28, -81, 106, Ascii.DC2, -124, 57, -25, ISO7816.INS_READ_BINARY, -126, -9, -2, -99, ISOFileInfo.FCI_EXT, 92, ISOFileInfo.DATA_BYTES2, 53, -34, ISO7816.INS_READ_BINARY_STAMPED, ISOFileInfo.A5, -4, Byte.MIN_VALUE, ByteSourceJsonBootstrapper.UTF8_BOM_1, -53, ByteSourceJsonBootstrapper.UTF8_BOM_2, 107, 118, -70, 90, 125, 120, 11, -107, -29, -83, 116, -104, 59, 54, 100, 109, ISO7816.INS_UPDATE_RECORD, -16, 89, -87, 76, Ascii.ETB, Byte.MAX_VALUE, -111, -72, -55, 87, Ascii.ESC, ISO7816.INS_CREATE_FILE, 97};
    private static final byte[] T0 = {-92, -94, -87, -59, 78, -55, 3, -39, 126, 15, ISO7816.INS_WRITE_RECORD, -83, -25, -45, 39, 91, -29, ISOFileInfo.A1, -24, -26, 124, ISO7816.INS_PSO, 85, 12, -122, 57, -41, ISOFileInfo.ENV_TEMP_EF, -72, Ascii.DC2, ISOFileInfo.FCI_BYTE, 40, -51, ISOFileInfo.LCS_BYTE, ISO7816.INS_MANAGE_CHANNEL, 86, 114, -7, ByteSourceJsonBootstrapper.UTF8_BOM_3, 79, 115, -23, -9, 87, Ascii.SYN, -84, 80, ISO7816.INS_GET_RESPONSE, -99, -73, 71, 113, 96, -60, 116, 67, 108, Ascii.US, -109, 119, ISO7816.INS_UPDATE_RECORD, -50, 32, ISOFileInfo.SECURITY_ATTR_COMPACT, -103, 95, ISO7816.INS_REHABILITATE_CHV, 1, -11, 30, ISOFileInfo.FCI_EXT, 94, 97, ISO7816.INS_UNBLOCK_CHV, 75, 29, ISOFileInfo.DATA_BYTES2, Ascii.NAK, -12, 35, ISO7816.INS_UPDATE_BINARY, -22, -31, 103, -15, Byte.MAX_VALUE, -2, ISO7816.INS_PUT_DATA, 60, 7, 83, 106, -124, -100, -53, 2, ISOFileInfo.FILE_IDENTIFIER, 51, -35, 53, ISO7816.INS_APPEND_RECORD, 89, 90, -104, ISOFileInfo.A5, -110, 100, 4, 6, 16, AvcSpsUtils.PROFILE_IDC_MAIN, 28, -105, 8, 49, -18, ISOFileInfo.AB, 5, -81, 121, ISOFileInfo.A0, Ascii.CAN, 70, 109, -4, -119, -44, -57, -1, -16, -49, 66, -111, -8, 104, 10, 101, ISOFileInfo.CHANNEL_SECURITY, ISO7816.INS_READ_RECORD_STAMPED, -3, -61, ByteSourceJsonBootstrapper.UTF8_BOM_1, 120, 76, -52, -98, ISO7816.INS_DECREASE, 46, PSSSigner.TRAILER_IMPLICIT, 11, 84, Ascii.SUB, -90, ByteSourceJsonBootstrapper.UTF8_BOM_2, 38, Byte.MIN_VALUE, 72, -108, ISO7816.INS_INCREASE, 125, -89, Utf8.REPLACEMENT_BYTE, -82, ISO7816.INS_MSE, Base64.padSymbol, 102, -86, -10, 0, 93, -67, 74, ISO7816.INS_CREATE_FILE, 59, ISO7816.INS_READ_BINARY_STAMPED, Ascii.ETB, ISOFileInfo.SECURITY_ATTR_EXP, -97, 118, ISO7816.INS_READ_BINARY, ISO7816.INS_CHANGE_CHV, -102, 37, 99, -37, -21, 122, 62, 92, ISO7816.INS_READ_RECORD2, ISO7816.INS_READ_BINARY2, 41, -14, ISO7816.INS_GET_DATA, AvcSpsUtils.PROFILE_IDC_EXTENDED, 110, ISO7816.INS_LOAD_KEY_FILE, -88, 47, 117, -33, Ascii.DC4, -5, 19, 73, -120, -78, -20, ISO7816.INS_DELETE_FILE, ISO7816.INS_DECREASE_STAMPED, 45, -106, -58, 58, -19, -107, 14, -27, ISOFileInfo.PROP_INFO, 107, SignedBytes.MAX_POWER_OF_TWO, 33, -101, 9, Ascii.EM, 43, 82, -34, 69, -93, -6, 81, ISO7816.INS_ENVELOPE, -75, -47, -112, -71, -13, 55, -63, 13, -70, 65, 17, 56, 123, -66, ISO7816.INS_WRITE_BINARY, -43, 105, 54, -56, ISOFileInfo.FCP_BYTE, Ascii.ESC, -126, -113};
    private static final byte[] T1 = {ISOFileInfo.FILE_IDENTIFIER, -14, ISO7816.INS_PSO, -21, -23, ByteSourceJsonBootstrapper.UTF8_BOM_3, 123, -100, ISO7816.INS_DECREASE_STAMPED, -106, ISOFileInfo.ENV_TEMP_EF, -104, -71, 105, ISOFileInfo.SECURITY_ATTR_COMPACT, 41, Base64.padSymbol, -120, 104, 6, 57, 17, 76, 14, ISOFileInfo.A0, 86, SignedBytes.MAX_POWER_OF_TWO, -110, Ascii.NAK, PSSSigner.TRAILER_IMPLICIT, ISO7816.INS_READ_RECORD2, ISO7816.INS_UPDATE_RECORD, ISOFileInfo.FCI_BYTE, -8, 38, -70, -66, -67, 49, -5, -61, -2, Byte.MIN_VALUE, 97, -31, 122, ISO7816.INS_INCREASE, ISO7816.INS_WRITE_RECORD, ISO7816.INS_MANAGE_CHANNEL, 32, ISOFileInfo.A1, 69, -20, -39, Ascii.SUB, 93, ISO7816.INS_READ_BINARY_STAMPED, ISO7816.INS_LOAD_KEY_FILE, 9, ISOFileInfo.A5, 85, ISOFileInfo.CHANNEL_SECURITY, 55, 118, -87, 103, 16, Ascii.ETB, 54, 101, ISO7816.INS_READ_BINARY2, -107, ISOFileInfo.FCP_BYTE, 89, 116, -93, 80, 47, 75, -56, ISO7816.INS_WRITE_BINARY, -113, -51, -44, 60, -122, Ascii.DC2, 29, 35, ByteSourceJsonBootstrapper.UTF8_BOM_1, -12, 83, Ascii.EM, 53, -26, Byte.MAX_VALUE, 94, ISO7816.INS_UPDATE_BINARY, 121, 81, ISO7816.INS_MSE, Ascii.DC4, -9, 30, 74, 66, -101, 65, 115, 45, -63, 92, -90, -94, ISO7816.INS_CREATE_FILE, 46, -45, 40, ByteSourceJsonBootstrapper.UTF8_BOM_2, -55, -82, 106, -47, 90, ISO7816.INS_DECREASE, -112, -124, -7, -78, AvcSpsUtils.PROFILE_IDC_EXTENDED, -49, 126, -59, -53, -105, ISO7816.INS_DELETE_FILE, Ascii.SYN, 108, -6, ISO7816.INS_READ_BINARY, 109, Ascii.US, 82, -103, 13, 78, 3, -111, ISO7816.INS_ENVELOPE, AvcSpsUtils.PROFILE_IDC_MAIN, 100, 119, -97, -35, -60, 73, ISOFileInfo.LCS_BYTE, -102, ISO7816.INS_CHANGE_CHV, 56, -89, 87, ISOFileInfo.PROP_INFO, -57, 124, 125, -25, -10, -73, -84, 39, 70, -34, -33, 59, -41, -98, 43, 11, -43, 19, 117, -16, 114, ISO7816.INS_READ_RECORD_STAMPED, -99, Ascii.ESC, 1, Utf8.REPLACEMENT_BYTE, ISO7816.INS_REHABILITATE_CHV, -27, ISOFileInfo.FCI_EXT, -3, 7, -15, ISOFileInfo.AB, -108, Ascii.CAN, -22, -4, 58, -126, 95, 5, 84, -37, 0, ISOFileInfo.SECURITY_ATTR_EXP, -29, 72, 12, ISO7816.INS_GET_DATA, 120, -119, 10, -1, 62, 91, ISOFileInfo.DATA_BYTES2, -18, 113, ISO7816.INS_APPEND_RECORD, ISO7816.INS_PUT_DATA, ISO7816.INS_UNBLOCK_CHV, -72, -75, -52, 110, -88, 107, -83, 96, -58, 8, 4, 2, -24, -11, 79, -92, -13, ISO7816.INS_GET_RESPONSE, -50, 67, 37, 28, 33, 51, 15, -81, 71, -19, 102, 99, -109, -86};
    private static final byte[] T2 = {69, -44, 11, 67, -15, 114, -19, -92, ISO7816.INS_ENVELOPE, 56, -26, 113, -3, ISO7816.INS_READ_RECORD_STAMPED, 58, -107, 80, ISO7816.INS_REHABILITATE_CHV, 75, ISO7816.INS_APPEND_RECORD, 116, 107, 30, 17, 90, -58, ISO7816.INS_READ_BINARY_STAMPED, ISO7816.INS_LOAD_KEY_FILE, ISOFileInfo.A5, ISOFileInfo.LCS_BYTE, ISO7816.INS_MANAGE_CHANNEL, -93, -88, -6, 5, -39, -105, SignedBytes.MAX_POWER_OF_TWO, -55, -112, -104, -113, ISO7816.INS_UPDATE_RECORD, Ascii.DC2, 49, ISO7816.INS_UNBLOCK_CHV, 71, 106, -103, -82, -56, Byte.MAX_VALUE, -7, 79, 93, -106, ISOFileInfo.FCI_BYTE, -12, ISO7816.INS_READ_RECORD2, 57, 33, ISO7816.INS_PUT_DATA, -100, ISOFileInfo.PROP_INFO, -98, 59, -16, ByteSourceJsonBootstrapper.UTF8_BOM_3, ByteSourceJsonBootstrapper.UTF8_BOM_1, 6, -18, -27, 95, 32, 16, -52, 60, 84, 74, 82, -108, 14, ISO7816.INS_GET_RESPONSE, 40, -10, 86, 96, -94, -29, 15, -20, -99, ISO7816.INS_CHANGE_CHV, ISOFileInfo.FILE_IDENTIFIER, 126, -43, 124, -21, Ascii.CAN, -41, -51, -35, 120, -1, -37, ISOFileInfo.A1, 9, ISO7816.INS_WRITE_BINARY, 118, -124, 117, ByteSourceJsonBootstrapper.UTF8_BOM_2, 29, Ascii.SUB, 47, ISO7816.INS_READ_BINARY, -2, ISO7816.INS_UPDATE_BINARY, ISO7816.INS_DECREASE_STAMPED, 99, 53, ISO7816.INS_WRITE_RECORD, ISO7816.INS_PSO, 89, 109, AvcSpsUtils.PROFILE_IDC_MAIN, 119, -25, ISOFileInfo.CHANNEL_SECURITY, 97, -49, -97, -50, 39, -11, Byte.MIN_VALUE, -122, -57, -90, -5, -8, ISOFileInfo.FCI_EXT, ISOFileInfo.AB, ISOFileInfo.FCP_BYTE, Utf8.REPLACEMENT_BYTE, -33, 72, 0, Ascii.DC4, -102, -67, 91, 4, -110, 2, 37, 101, 76, 83, 12, -14, 41, -81, Ascii.ETB, 108, 65, ISO7816.INS_DECREASE, -23, -109, 85, -9, -84, 104, 38, -60, 125, ISO7816.INS_GET_DATA, 122, 62, ISOFileInfo.A0, 55, 3, -63, 54, 105, 102, 8, Ascii.SYN, -89, PSSSigner.TRAILER_IMPLICIT, -59, -45, ISO7816.INS_MSE, -73, 19, 70, ISO7816.INS_INCREASE, -24, 87, -120, 43, ISOFileInfo.DATA_BYTES2, -78, 78, 100, 28, -86, -111, AvcSpsUtils.PROFILE_IDC_EXTENDED, 46, -101, 92, Ascii.ESC, 81, 115, 66, 35, 1, 110, -13, 13, -66, Base64.padSymbol, 10, 45, Ascii.US, 103, 51, Ascii.EM, 123, 94, -22, -34, ISOFileInfo.SECURITY_ATTR_EXP, -53, -87, ISOFileInfo.SECURITY_ATTR_COMPACT, ISOFileInfo.ENV_TEMP_EF, -83, 73, -126, ISO7816.INS_DELETE_FILE, -70, -61, Ascii.NAK, -47, ISO7816.INS_CREATE_FILE, -119, -4, ISO7816.INS_READ_BINARY2, -71, -75, 7, 121, -72, -31};
    private static final byte[] T3 = {-78, ISO7816.INS_READ_RECORD_STAMPED, 35, 17, -89, -120, -59, -90, 57, -113, -60, -24, 115, ISO7816.INS_MSE, 67, -61, -126, 39, -51, Ascii.CAN, 81, ISOFileInfo.FCP_BYTE, 45, -9, 92, 14, 59, -3, ISO7816.INS_GET_DATA, -101, 13, 15, 121, ISOFileInfo.SECURITY_ATTR_COMPACT, 16, 76, 116, 28, 10, ISOFileInfo.CHANNEL_SECURITY, 124, -108, 7, -57, 94, Ascii.DC4, ISOFileInfo.A1, 33, 87, 80, 78, -87, Byte.MIN_VALUE, -39, ByteSourceJsonBootstrapper.UTF8_BOM_1, 100, 65, -49, 60, -18, 46, 19, 41, -70, ISO7816.INS_DECREASE_STAMPED, 90, -82, ISOFileInfo.LCS_BYTE, 97, 51, Ascii.DC2, -71, 85, -88, Ascii.NAK, 5, -10, 3, 6, 73, -75, 37, 9, Ascii.SYN, 12, ISO7816.INS_PSO, 56, -4, 32, -12, -27, Byte.MAX_VALUE, -41, 49, 43, 102, ISOFileInfo.FCI_BYTE, -1, 114, -122, -16, -93, 47, 120, 0, PSSSigner.TRAILER_IMPLICIT, -52, ISO7816.INS_APPEND_RECORD, ISO7816.INS_READ_BINARY, -15, 66, ISO7816.INS_READ_BINARY_STAMPED, ISO7816.INS_DECREASE, 95, 96, 4, -20, ISOFileInfo.A5, -29, ISOFileInfo.SECURITY_ATTR_EXP, -25, 29, ByteSourceJsonBootstrapper.UTF8_BOM_3, -124, 123, -26, ISOFileInfo.DATA_BYTES2, -8, -34, ISO7816.INS_LOAD_KEY_FILE, ISO7816.INS_WRITE_RECORD, Ascii.ETB, -50, 75, 71, ISO7816.INS_UPDATE_BINARY, 105, 108, Ascii.EM, -103, -102, 1, ISO7816.INS_READ_RECORD2, ISOFileInfo.PROP_INFO, ISO7816.INS_READ_BINARY2, -7, 89, ISO7816.INS_ENVELOPE, 55, -23, -56, ISOFileInfo.A0, -19, 79, -119, 104, 109, -43, 38, -111, ISOFileInfo.FCI_EXT, AvcSpsUtils.PROFILE_IDC_EXTENDED, -67, -55, -104, ISO7816.INS_UPDATE_RECORD, 117, ISO7816.INS_GET_RESPONSE, 118, -11, 103, 107, 126, -21, 82, -53, -47, 91, -97, 11, -37, SignedBytes.MAX_POWER_OF_TWO, -110, Ascii.SUB, -6, -84, ISO7816.INS_DELETE_FILE, -31, 113, Ascii.US, 101, ISOFileInfo.ENV_TEMP_EF, -105, -98, -107, -112, 93, -73, -63, -81, 84, -5, 2, ISO7816.INS_CREATE_FILE, 53, ByteSourceJsonBootstrapper.UTF8_BOM_2, 58, AvcSpsUtils.PROFILE_IDC_MAIN, -83, ISO7816.INS_UNBLOCK_CHV, Base64.padSymbol, 86, 8, Ascii.ESC, 74, -109, 106, ISOFileInfo.AB, -72, 122, -14, 125, ISO7816.INS_PUT_DATA, Utf8.REPLACEMENT_BYTE, -2, 62, -66, -22, -86, ISO7816.INS_REHABILITATE_CHV, -58, ISO7816.INS_WRITE_BINARY, 54, 72, ISO7816.INS_MANAGE_CHANNEL, -106, 119, ISO7816.INS_CHANGE_CHV, 83, -33, -13, ISOFileInfo.FILE_IDENTIFIER, 40, ISO7816.INS_INCREASE, 69, 30, -92, -45, -94, 70, 110, -100, -35, 99, -44, -99};
    private boolean forEncryption;
    private long[] internalState;
    private long[][] roundKeys;
    private int roundsAmount;
    private int wordsInBlock;
    private int wordsInKey;
    private long[] workingKey;

    public DSTU7624Engine(int i) throws IllegalArgumentException {
        if (i != 128 && i != 256 && i != 512) {
            throw new IllegalArgumentException("unsupported block length: only 128/256/512 are allowed");
        }
        int i2 = i >>> 6;
        this.wordsInBlock = i2;
        this.internalState = new long[i2];
    }

    private void addRoundKey(int i) {
        long[] jArr = this.roundKeys[i];
        for (int i2 = 0; i2 < this.wordsInBlock; i2++) {
            long[] jArr2 = this.internalState;
            jArr2[i2] = jArr2[i2] + jArr[i2];
        }
    }

    private void decryptBlock_128(byte[] bArr, int i, byte[] bArr2, int i2) {
        long littleEndianToLong = Pack.littleEndianToLong(bArr, i);
        long littleEndianToLong2 = Pack.littleEndianToLong(bArr, i + 8);
        long[][] jArr = this.roundKeys;
        int i3 = this.roundsAmount;
        long[] jArr2 = jArr[i3];
        long j = littleEndianToLong - jArr2[0];
        long j2 = littleEndianToLong2 - jArr2[1];
        while (true) {
            long mixColumnInv = mixColumnInv(j);
            long mixColumnInv2 = mixColumnInv(j2);
            int i4 = (int) mixColumnInv;
            int i5 = (int) (mixColumnInv >>> 32);
            int i6 = (int) mixColumnInv2;
            int i7 = (int) (mixColumnInv2 >>> 32);
            byte[] bArr3 = T0;
            byte b = bArr3[i4 & 255];
            byte[] bArr4 = T1;
            byte b2 = bArr4[(i4 >>> 8) & 255];
            byte[] bArr5 = T2;
            byte b3 = bArr5[(i4 >>> 16) & 255];
            byte[] bArr6 = T3;
            int i8 = (bArr6[i4 >>> 24] << Ascii.CAN) | ((b3 & 255) << 16) | (b & 255) | ((b2 & 255) << 8);
            byte b4 = bArr3[i7 & 255];
            byte b5 = bArr4[(i7 >>> 8) & 255];
            byte b6 = bArr5[(i7 >>> 16) & 255];
            long j3 = (((bArr6[i7 >>> 24] << Ascii.CAN) | (((b4 & 255) | ((b5 & 255) << 8)) | ((b6 & 255) << 16))) << 32) | (i8 & 4294967295L);
            int i9 = (bArr6[i6 >>> 24] << Ascii.CAN) | (bArr3[i6 & 255] & 255) | ((bArr4[(i6 >>> 8) & 255] & 255) << 8) | ((bArr5[(i6 >>> 16) & 255] & 255) << 16);
            byte b7 = bArr3[i5 & 255];
            byte b8 = bArr4[(i5 >>> 8) & 255];
            byte b9 = bArr5[(i5 >>> 16) & 255];
            long j4 = (i9 & 4294967295L) | (((bArr6[i5 >>> 24] << Ascii.CAN) | (((b7 & 255) | ((b8 & 255) << 8)) | ((b9 & 255) << 16))) << 32);
            i3--;
            if (i3 == 0) {
                long[] jArr3 = this.roundKeys[0];
                long j5 = j3 - jArr3[0];
                long j6 = j4 - jArr3[1];
                Pack.longToLittleEndian(j5, bArr2, i2);
                Pack.longToLittleEndian(j6, bArr2, i2 + 8);
                return;
            }
            long[] jArr4 = this.roundKeys[i3];
            long j7 = j3 ^ jArr4[0];
            j2 = j4 ^ jArr4[1];
            j = j7;
        }
    }

    private void encryptBlock_128(byte[] bArr, int i, byte[] bArr2, int i2) {
        long littleEndianToLong = Pack.littleEndianToLong(bArr, i);
        long littleEndianToLong2 = Pack.littleEndianToLong(bArr, i + 8);
        long[] jArr = this.roundKeys[0];
        long j = littleEndianToLong + jArr[0];
        long j2 = littleEndianToLong2 + jArr[1];
        int i3 = 0;
        while (true) {
            int i4 = (int) j;
            int i5 = (int) (j >>> 32);
            int i6 = (int) j2;
            int i7 = (int) (j2 >>> 32);
            byte[] bArr3 = S0;
            byte b = bArr3[i4 & 255];
            byte[] bArr4 = S1;
            byte b2 = bArr4[(i4 >>> 8) & 255];
            byte[] bArr5 = S2;
            byte b3 = bArr5[(i4 >>> 16) & 255];
            byte[] bArr6 = S3;
            int i8 = ((b3 & 255) << 16) | (b & 255) | ((b2 & 255) << 8) | (bArr6[i4 >>> 24] << Ascii.CAN);
            byte b4 = bArr3[i7 & 255];
            byte b5 = bArr4[(i7 >>> 8) & 255];
            byte b6 = bArr5[(i7 >>> 16) & 255];
            long j3 = (((bArr6[i7 >>> 24] << Ascii.CAN) | (((b4 & 255) | ((b5 & 255) << 8)) | ((b6 & 255) << 16))) << 32) | (i8 & 4294967295L);
            int i9 = (bArr6[i6 >>> 24] << Ascii.CAN) | (bArr3[i6 & 255] & 255) | ((bArr4[(i6 >>> 8) & 255] & 255) << 8) | ((bArr5[(i6 >>> 16) & 255] & 255) << 16);
            byte b7 = bArr3[i5 & 255];
            byte b8 = bArr4[(i5 >>> 8) & 255];
            byte b9 = bArr5[(i5 >>> 16) & 255];
            int i10 = bArr6[i5 >>> 24] << Ascii.CAN;
            long mixColumn = mixColumn(j3);
            long mixColumn2 = mixColumn((i9 & 4294967295L) | ((i10 | (((b7 & 255) | ((b8 & 255) << 8)) | ((b9 & 255) << 16))) << 32));
            i3++;
            int i11 = this.roundsAmount;
            if (i3 == i11) {
                long[] jArr2 = this.roundKeys[i11];
                long j4 = mixColumn + jArr2[0];
                long j5 = mixColumn2 + jArr2[1];
                Pack.longToLittleEndian(j4, bArr2, i2);
                Pack.longToLittleEndian(j5, bArr2, i2 + 8);
                return;
            }
            long[] jArr3 = this.roundKeys[i3];
            long j6 = mixColumn ^ jArr3[0];
            j2 = mixColumn2 ^ jArr3[1];
            j = j6;
        }
    }

    private void invShiftRows() {
        int i = this.wordsInBlock;
        if (i == 2) {
            long[] jArr = this.internalState;
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = (-4294967296L) & (j ^ j2);
            jArr[0] = j ^ j3;
            jArr[1] = j3 ^ j2;
            return;
        }
        if (i == 4) {
            long[] jArr2 = this.internalState;
            long j4 = jArr2[0];
            long j5 = jArr2[1];
            long j6 = jArr2[2];
            long j7 = jArr2[3];
            long j8 = (j4 ^ j5) & (-281470681808896L);
            long j9 = j4 ^ j8;
            long j10 = j5 ^ j8;
            long j11 = (j6 ^ j7) & (-281470681808896L);
            long j12 = j6 ^ j11;
            long j13 = j7 ^ j11;
            long j14 = (j9 ^ j12) & (-4294967296L);
            long j15 = j9 ^ j14;
            long j16 = (j10 ^ j13) & 281474976645120L;
            jArr2[0] = j15;
            jArr2[1] = j10 ^ j16;
            jArr2[2] = j12 ^ j14;
            jArr2[3] = j16 ^ j13;
            return;
        }
        if (i != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        }
        long[] jArr3 = this.internalState;
        long j17 = jArr3[0];
        long j18 = jArr3[1];
        long j19 = jArr3[2];
        long j20 = jArr3[3];
        long j21 = jArr3[4];
        long j22 = jArr3[5];
        long j23 = jArr3[6];
        long j24 = jArr3[7];
        long j25 = (j17 ^ j18) & (-71777214294589696L);
        long j26 = j17 ^ j25;
        long j27 = j18 ^ j25;
        long j28 = (j19 ^ j20) & (-71777214294589696L);
        long j29 = j19 ^ j28;
        long j30 = j20 ^ j28;
        long j31 = (j21 ^ j22) & (-71777214294589696L);
        long j32 = j21 ^ j31;
        long j33 = j22 ^ j31;
        long j34 = (j23 ^ j24) & (-71777214294589696L);
        long j35 = j23 ^ j34;
        long j36 = j24 ^ j34;
        long j37 = (j26 ^ j29) & (-281470681808896L);
        long j38 = j26 ^ j37;
        long j39 = j29 ^ j37;
        long j40 = (j27 ^ j30) & 72056494543077120L;
        long j41 = j27 ^ j40;
        long j42 = j30 ^ j40;
        long j43 = (j32 ^ j35) & (-281470681808896L);
        long j44 = j32 ^ j43;
        long j45 = j35 ^ j43;
        long j46 = (j33 ^ j36) & 72056494543077120L;
        long j47 = j33 ^ j46;
        long j48 = j36 ^ j46;
        long j49 = (j38 ^ j44) & (-4294967296L);
        long j50 = j38 ^ j49;
        long j51 = j44 ^ j49;
        long j52 = (j41 ^ j47) & 72057594021150720L;
        long j53 = j41 ^ j52;
        long j54 = (j39 ^ j45) & 281474976645120L;
        long j55 = j39 ^ j54;
        long j56 = j54 ^ j45;
        long j57 = (j42 ^ j48) & 1099511627520L;
        jArr3[0] = j50;
        jArr3[1] = j53;
        jArr3[2] = j55;
        jArr3[3] = j42 ^ j57;
        jArr3[4] = j51;
        jArr3[5] = j47 ^ j52;
        jArr3[6] = j56;
        jArr3[7] = j48 ^ j57;
    }

    private void invSubBytes() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            long j = jArr[i];
            int i2 = (int) j;
            int i3 = (int) (j >>> 32);
            byte[] bArr = T0;
            byte b = bArr[i2 & 255];
            byte[] bArr2 = T1;
            byte b2 = bArr2[(i2 >>> 8) & 255];
            byte[] bArr3 = T2;
            byte b3 = bArr3[(i2 >>> 16) & 255];
            int i4 = (T3[i2 >>> 24] << Ascii.CAN) | (b & 255) | ((b2 & 255) << 8) | ((b3 & 255) << 16);
            byte b4 = bArr[i3 & 255];
            byte b5 = bArr2[(i3 >>> 8) & 255];
            byte b6 = bArr3[(i3 >>> 16) & 255];
            jArr[i] = (i4 & 4294967295L) | (((r11[i3 >>> 24] << Ascii.CAN) | (((b4 & 255) | ((b5 & 255) << 8)) | ((b6 & 255) << 16))) << 32);
        }
    }

    private static long mixColumn(long j) {
        long mulX = mulX(j);
        long rotate = rotate(8, j) ^ j;
        long rotate2 = (rotate ^ rotate(16, rotate)) ^ rotate(48, j);
        return ((rotate(32, mulX2((j ^ rotate2) ^ mulX)) ^ rotate2) ^ rotate(40, mulX)) ^ rotate(48, mulX);
    }

    private static long mixColumnInv(long j) {
        long rotate = rotate(8, j) ^ j;
        long rotate2 = (rotate ^ rotate(32, rotate)) ^ rotate(48, j);
        long j2 = rotate2 ^ j;
        long rotate3 = rotate(48, j);
        long rotate4 = rotate(56, j);
        long mulX = mulX(j2 ^ rotate4) ^ rotate(56, j2);
        long mulX2 = mulX(rotate(40, mulX(mulX) ^ j) ^ (rotate(16, j2) ^ j)) ^ (j2 ^ rotate3);
        return mulX(rotate(40, ((j ^ rotate(32, j2)) ^ rotate4) ^ mulX(((rotate3 ^ (rotate(24, j) ^ j2)) ^ rotate4) ^ mulX(mulX(mulX2) ^ rotate(16, rotate2))))) ^ rotate2;
    }

    private void mixColumns() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            jArr[i] = mixColumn(jArr[i]);
        }
    }

    private void mixColumnsInv() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            jArr[i] = mixColumnInv(jArr[i]);
        }
    }

    private static long mulX(long j) {
        return (((j & (-9187201950435737472L)) >>> 7) * 29) ^ ((9187201950435737471L & j) << 1);
    }

    private static long mulX2(long j) {
        return (((j & 4629771061636907072L) >>> 6) * 29) ^ (((4557430888798830399L & j) << 2) ^ ((((-9187201950435737472L) & j) >>> 6) * 29));
    }

    private static long rotate(int i, long j) {
        return (j << (-i)) | (j >>> i);
    }

    private void rotateLeft(long[] jArr, long[] jArr2) {
        int i = this.wordsInBlock;
        if (i == 2) {
            long j = jArr[0];
            long j2 = jArr[1];
            jArr2[0] = (j >>> 56) | (j2 << 8);
            jArr2[1] = (j << 8) | (j2 >>> 56);
            return;
        }
        if (i == 4) {
            long j3 = jArr[0];
            long j4 = jArr[1];
            long j5 = jArr[2];
            long j6 = jArr[3];
            jArr2[0] = (j4 >>> 24) | (j5 << 40);
            jArr2[1] = (j5 >>> 24) | (j6 << 40);
            jArr2[2] = (j6 >>> 24) | (j3 << 40);
            jArr2[3] = (j3 >>> 24) | (j4 << 40);
            return;
        }
        if (i != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        }
        long j7 = jArr[0];
        long j8 = jArr[1];
        long j9 = jArr[2];
        long j10 = jArr[3];
        long j11 = jArr[4];
        long j12 = jArr[5];
        long j13 = jArr[6];
        long j14 = jArr[7];
        jArr2[0] = (j9 >>> 24) | (j10 << 40);
        jArr2[1] = (j10 >>> 24) | (j11 << 40);
        jArr2[2] = (j11 >>> 24) | (j12 << 40);
        jArr2[3] = (j12 >>> 24) | (j13 << 40);
        jArr2[4] = (j13 >>> 24) | (j14 << 40);
        jArr2[5] = (j14 >>> 24) | (j7 << 40);
        jArr2[6] = (j7 >>> 24) | (j8 << 40);
        jArr2[7] = (j8 >>> 24) | (j9 << 40);
    }

    private void shiftRows() {
        int i = this.wordsInBlock;
        if (i == 2) {
            long[] jArr = this.internalState;
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = (-4294967296L) & (j ^ j2);
            jArr[0] = j ^ j3;
            jArr[1] = j3 ^ j2;
            return;
        }
        if (i == 4) {
            long[] jArr2 = this.internalState;
            long j4 = jArr2[0];
            long j5 = jArr2[1];
            long j6 = jArr2[2];
            long j7 = jArr2[3];
            long j8 = (j4 ^ j6) & (-4294967296L);
            long j9 = j4 ^ j8;
            long j10 = j6 ^ j8;
            long j11 = (j5 ^ j7) & 281474976645120L;
            long j12 = j5 ^ j11;
            long j13 = j7 ^ j11;
            long j14 = (j9 ^ j12) & (-281470681808896L);
            long j15 = (j10 ^ j13) & (-281470681808896L);
            jArr2[0] = j9 ^ j14;
            jArr2[1] = j12 ^ j14;
            jArr2[2] = j10 ^ j15;
            jArr2[3] = j13 ^ j15;
            return;
        }
        if (i != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        }
        long[] jArr3 = this.internalState;
        long j16 = jArr3[0];
        long j17 = jArr3[1];
        long j18 = jArr3[2];
        long j19 = jArr3[3];
        long j20 = jArr3[4];
        long j21 = jArr3[5];
        long j22 = jArr3[6];
        long j23 = jArr3[7];
        long j24 = (j16 ^ j20) & (-4294967296L);
        long j25 = j16 ^ j24;
        long j26 = j20 ^ j24;
        long j27 = (j17 ^ j21) & 72057594021150720L;
        long j28 = j17 ^ j27;
        long j29 = j21 ^ j27;
        long j30 = (j18 ^ j22) & 281474976645120L;
        long j31 = j18 ^ j30;
        long j32 = j22 ^ j30;
        long j33 = (j19 ^ j23) & 1099511627520L;
        long j34 = j19 ^ j33;
        long j35 = j23 ^ j33;
        long j36 = (j25 ^ j31) & (-281470681808896L);
        long j37 = j25 ^ j36;
        long j38 = j31 ^ j36;
        long j39 = (j28 ^ j34) & 72056494543077120L;
        long j40 = j28 ^ j39;
        long j41 = j34 ^ j39;
        long j42 = (j26 ^ j32) & (-281470681808896L);
        long j43 = j26 ^ j42;
        long j44 = j32 ^ j42;
        long j45 = (j29 ^ j35) & 72056494543077120L;
        long j46 = j29 ^ j45;
        long j47 = j35 ^ j45;
        long j48 = (j37 ^ j40) & (-71777214294589696L);
        long j49 = j37 ^ j48;
        long j50 = j40 ^ j48;
        long j51 = (j38 ^ j41) & (-71777214294589696L);
        long j52 = j38 ^ j51;
        long j53 = j41 ^ j51;
        long j54 = (j43 ^ j46) & (-71777214294589696L);
        long j55 = j43 ^ j54;
        long j56 = j46 ^ j54;
        long j57 = (j44 ^ j47) & (-71777214294589696L);
        jArr3[0] = j49;
        jArr3[1] = j50;
        jArr3[2] = j52;
        jArr3[3] = j53;
        jArr3[4] = j55;
        jArr3[5] = j56;
        jArr3[6] = j44 ^ j57;
        jArr3[7] = j47 ^ j57;
    }

    private void subBytes() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            long j = jArr[i];
            int i2 = (int) j;
            int i3 = (int) (j >>> 32);
            byte[] bArr = S0;
            byte b = bArr[i2 & 255];
            byte[] bArr2 = S1;
            byte b2 = bArr2[(i2 >>> 8) & 255];
            byte[] bArr3 = S2;
            byte b3 = bArr3[(i2 >>> 16) & 255];
            int i4 = (S3[i2 >>> 24] << Ascii.CAN) | (b & 255) | ((b2 & 255) << 8) | ((b3 & 255) << 16);
            byte b4 = bArr[i3 & 255];
            byte b5 = bArr2[(i3 >>> 8) & 255];
            byte b6 = bArr3[(i3 >>> 16) & 255];
            jArr[i] = (i4 & 4294967295L) | (((r11[i3 >>> 24] << Ascii.CAN) | (((b4 & 255) | ((b5 & 255) << 8)) | ((b6 & 255) << 16))) << 32);
        }
    }

    private void subRoundKey(int i) {
        long[] jArr = this.roundKeys[i];
        for (int i2 = 0; i2 < this.wordsInBlock; i2++) {
            long[] jArr2 = this.internalState;
            jArr2[i2] = jArr2[i2] - jArr[i2];
        }
    }

    private void workingKeyExpandEven(long[] jArr, long[] jArr2) {
        int i;
        int i2;
        int i3 = this.wordsInKey;
        long[] jArr3 = new long[i3];
        long[] jArr4 = new long[this.wordsInBlock];
        System.arraycopy(jArr, 0, jArr3, 0, i3);
        long j = 281479271743489L;
        int i4 = 0;
        while (true) {
            for (int i5 = 0; i5 < this.wordsInBlock; i5++) {
                jArr4[i5] = jArr2[i5] + j;
            }
            for (int i6 = 0; i6 < this.wordsInBlock; i6++) {
                this.internalState[i6] = jArr3[i6] + jArr4[i6];
            }
            subBytes();
            shiftRows();
            mixColumns();
            for (int i7 = 0; i7 < this.wordsInBlock; i7++) {
                long[] jArr5 = this.internalState;
                jArr5[i7] = jArr5[i7] ^ jArr4[i7];
            }
            subBytes();
            shiftRows();
            mixColumns();
            int i8 = 0;
            while (true) {
                i = this.wordsInBlock;
                if (i8 >= i) {
                    break;
                }
                long[] jArr6 = this.internalState;
                jArr6[i8] = jArr6[i8] + jArr4[i8];
                i8++;
            }
            System.arraycopy(this.internalState, 0, this.roundKeys[i4], 0, i);
            if (this.roundsAmount == i4) {
                return;
            }
            if (this.wordsInBlock != this.wordsInKey) {
                i4 += 2;
                j <<= 1;
                for (int i9 = 0; i9 < this.wordsInBlock; i9++) {
                    jArr4[i9] = jArr2[i9] + j;
                }
                int i10 = 0;
                while (true) {
                    int i11 = this.wordsInBlock;
                    if (i10 >= i11) {
                        break;
                    }
                    this.internalState[i10] = jArr3[i11 + i10] + jArr4[i10];
                    i10++;
                }
                subBytes();
                shiftRows();
                mixColumns();
                for (int i12 = 0; i12 < this.wordsInBlock; i12++) {
                    long[] jArr7 = this.internalState;
                    jArr7[i12] = jArr7[i12] ^ jArr4[i12];
                }
                subBytes();
                shiftRows();
                mixColumns();
                int i13 = 0;
                while (true) {
                    i2 = this.wordsInBlock;
                    if (i13 >= i2) {
                        break;
                    }
                    long[] jArr8 = this.internalState;
                    jArr8[i13] = jArr8[i13] + jArr4[i13];
                    i13++;
                }
                System.arraycopy(this.internalState, 0, this.roundKeys[i4], 0, i2);
                if (this.roundsAmount == i4) {
                    return;
                }
            }
            i4 += 2;
            j <<= 1;
            long j2 = jArr3[0];
            for (int i14 = 1; i14 < i3; i14++) {
                jArr3[i14 - 1] = jArr3[i14];
            }
            jArr3[i3 - 1] = j2;
        }
    }

    private void workingKeyExpandKT(long[] jArr, long[] jArr2) {
        int i = this.wordsInBlock;
        long[] jArr3 = new long[i];
        long[] jArr4 = new long[i];
        long[] jArr5 = new long[i];
        this.internalState = jArr5;
        long j = jArr5[0];
        int i2 = this.wordsInKey;
        jArr5[0] = j + i + i2 + 1;
        System.arraycopy(jArr, 0, jArr3, 0, i);
        if (i == i2) {
            System.arraycopy(jArr, 0, jArr4, 0, i);
        } else {
            int i3 = this.wordsInBlock;
            System.arraycopy(jArr, i3, jArr4, 0, i3);
        }
        int i4 = 0;
        while (true) {
            long[] jArr6 = this.internalState;
            if (i4 >= jArr6.length) {
                break;
            }
            jArr6[i4] = jArr6[i4] + jArr3[i4];
            i4++;
        }
        subBytes();
        shiftRows();
        mixColumns();
        int i5 = 0;
        while (true) {
            long[] jArr7 = this.internalState;
            if (i5 >= jArr7.length) {
                break;
            }
            jArr7[i5] = jArr7[i5] ^ jArr4[i5];
            i5++;
        }
        subBytes();
        shiftRows();
        mixColumns();
        int i6 = 0;
        while (true) {
            long[] jArr8 = this.internalState;
            if (i6 >= jArr8.length) {
                subBytes();
                shiftRows();
                mixColumns();
                System.arraycopy(this.internalState, 0, jArr2, 0, this.wordsInBlock);
                return;
            }
            jArr8[i6] = jArr8[i6] + jArr3[i6];
            i6++;
        }
    }

    private void workingKeyExpandOdd() {
        for (int i = 1; i < this.roundsAmount; i += 2) {
            long[][] jArr = this.roundKeys;
            rotateLeft(jArr[i - 1], jArr[i]);
        }
    }

    private void xorRoundKey(int i) {
        long[] jArr = this.roundKeys[i];
        for (int i2 = 0; i2 < this.wordsInBlock; i2++) {
            long[] jArr2 = this.internalState;
            jArr2[i2] = jArr2[i2] ^ jArr[i2];
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "DSTU7624";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.wordsInBlock << 3;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005c A[LOOP:0: B:21:0x0057->B:23:0x005c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0065 A[EDGE_INSN: B:24:0x0065->B:25:0x0065 BREAK  A[LOOP:0: B:21:0x0057->B:23:0x005c], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0085  */
    @Override // org.bouncycastle.crypto.BlockCipher
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        int i;
        int i2;
        long[][] jArr;
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Invalid parameter passed to DSTU7624Engine init");
        }
        this.forEncryption = z;
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        int length = key.length << 3;
        int i3 = this.wordsInBlock << 6;
        if (length != 128 && length != 256 && length != 512) {
            throw new IllegalArgumentException("unsupported key length: only 128/256/512 are allowed");
        }
        if (length != i3 && length != i3 * 2) {
            throw new IllegalArgumentException("Unsupported key length");
        }
        if (length == 128) {
            i = 10;
        } else {
            if (length != 256) {
                if (length == 512) {
                    i = 18;
                }
                this.wordsInKey = length >>> 6;
                this.roundKeys = new long[this.roundsAmount + 1];
                i2 = 0;
                while (true) {
                    jArr = this.roundKeys;
                    if (i2 < jArr.length) {
                        break;
                    }
                    jArr[i2] = new long[this.wordsInBlock];
                    i2++;
                }
                long[] jArr2 = new long[this.wordsInKey];
                this.workingKey = jArr2;
                if (key.length == (length >>> 3)) {
                    throw new IllegalArgumentException("Invalid key parameter passed to DSTU7624Engine init");
                }
                Pack.littleEndianToLong(key, 0, jArr2);
                long[] jArr3 = new long[this.wordsInBlock];
                workingKeyExpandKT(this.workingKey, jArr3);
                workingKeyExpandEven(this.workingKey, jArr3);
                workingKeyExpandOdd();
                return;
            }
            i = 14;
        }
        this.roundsAmount = i;
        this.wordsInKey = length >>> 6;
        this.roundKeys = new long[this.roundsAmount + 1];
        i2 = 0;
        while (true) {
            jArr = this.roundKeys;
            if (i2 < jArr.length) {
            }
            jArr[i2] = new long[this.wordsInBlock];
            i2++;
        }
        long[] jArr22 = new long[this.wordsInKey];
        this.workingKey = jArr22;
        if (key.length == (length >>> 3)) {
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3;
        if (this.workingKey == null) {
            throw new IllegalStateException("DSTU7624Engine not initialised");
        }
        if (getBlockSize() + i > bArr.length) {
            throw new DataLengthException("Input buffer too short");
        }
        if (getBlockSize() + i2 > bArr2.length) {
            throw new OutputLengthException("Output buffer too short");
        }
        int i4 = 0;
        if (this.forEncryption) {
            if (this.wordsInBlock != 2) {
                Pack.littleEndianToLong(bArr, i, this.internalState);
                addRoundKey(0);
                while (true) {
                    subBytes();
                    shiftRows();
                    mixColumns();
                    i4++;
                    i3 = this.roundsAmount;
                    if (i4 == i3) {
                        break;
                    }
                    xorRoundKey(i4);
                }
                addRoundKey(i3);
                Pack.longToLittleEndian(this.internalState, bArr2, i2);
            } else {
                encryptBlock_128(bArr, i, bArr2, i2);
            }
        } else if (this.wordsInBlock != 2) {
            Pack.littleEndianToLong(bArr, i, this.internalState);
            subRoundKey(this.roundsAmount);
            int i5 = this.roundsAmount;
            while (true) {
                mixColumnsInv();
                invShiftRows();
                invSubBytes();
                i5--;
                if (i5 == 0) {
                    break;
                }
                xorRoundKey(i5);
            }
            subRoundKey(0);
            Pack.longToLittleEndian(this.internalState, bArr2, i2);
        } else {
            decryptBlock_128(bArr, i, bArr2, i2);
        }
        return getBlockSize();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        Arrays.fill(this.internalState, 0L);
    }
}
