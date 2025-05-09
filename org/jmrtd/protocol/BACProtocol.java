package org.jmrtd.protocol;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Random;
import javax.crypto.SecretKey;
import kotlin.text.Typography;
import net.sf.scuba.smartcards.CardServiceException;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.jmrtd.APDULevelBACCapable;
import org.jmrtd.AccessKeySpec;
import org.jmrtd.BACKeySpec;
import org.jmrtd.Util;

/* loaded from: classes6.dex */
public class BACProtocol {
    private int maxTranceiveLength;
    private Random random = new SecureRandom();
    private APDULevelBACCapable service;
    private boolean shouldCheckMAC;

    public BACProtocol(APDULevelBACCapable aPDULevelBACCapable, int i, boolean z) {
        this.service = aPDULevelBACCapable;
        this.maxTranceiveLength = i;
        this.shouldCheckMAC = z;
    }

    public BACResult doBAC(AccessKeySpec accessKeySpec) throws CardServiceException {
        try {
            byte[] key = accessKeySpec.getKey();
            return new BACResult(accessKeySpec, doBACStep(Util.deriveKey(key, 1), Util.deriveKey(key, 2)));
        } catch (GeneralSecurityException e) {
            throw new CardServiceException("Error during BAC", e);
        }
    }

    public BACResult doBAC(SecretKey secretKey, SecretKey secretKey2) throws CardServiceException, GeneralSecurityException {
        return new BACResult(doBACStep(secretKey, secretKey2));
    }

    private SecureMessagingWrapper doBACStep(SecretKey secretKey, SecretKey secretKey2) throws CardServiceException, GeneralSecurityException {
        byte[] sendGetChallenge = this.service.sendGetChallenge();
        byte[] bArr = new byte[8];
        this.random.nextBytes(bArr);
        byte[] bArr2 = new byte[16];
        this.random.nextBytes(bArr2);
        byte[] bArr3 = new byte[16];
        System.arraycopy(this.service.sendMutualAuth(bArr, sendGetChallenge, bArr2, secretKey, secretKey2), 16, bArr3, 0, 16);
        byte[] bArr4 = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr4[i] = (byte) ((bArr2[i] & 255) ^ (bArr3[i] & 255));
        }
        return new DESedeSecureMessagingWrapper(Util.deriveKey(bArr4, 1), Util.deriveKey(bArr4, 2), this.maxTranceiveLength, this.shouldCheckMAC, computeSendSequenceCounter(sendGetChallenge, bArr));
    }

    public static byte[] computeKeySeedForBAC(BACKeySpec bACKeySpec) throws GeneralSecurityException {
        String documentNumber = bACKeySpec.getDocumentNumber();
        String dateOfBirth = bACKeySpec.getDateOfBirth();
        String dateOfExpiry = bACKeySpec.getDateOfExpiry();
        if (dateOfBirth == null || dateOfBirth.length() != 6) {
            throw new IllegalArgumentException("Wrong date format used for date of birth. Expected yyMMdd, found " + dateOfBirth);
        }
        if (dateOfExpiry == null || dateOfExpiry.length() != 6) {
            throw new IllegalArgumentException("Wrong date format used for date of expiry. Expected yyMMdd, found " + dateOfExpiry);
        }
        if (documentNumber == null) {
            throw new IllegalArgumentException("Wrong document number. Found " + documentNumber);
        }
        return computeKeySeedForBAC(fixDocumentNumber(documentNumber), dateOfBirth, dateOfExpiry);
    }

    public static long computeSendSequenceCounter(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length != 8 || bArr2 == null || bArr2.length != 8) {
            throw new IllegalStateException("Wrong length input");
        }
        long j = 0;
        for (int i = 4; i < 8; i++) {
            j = (j << 8) + (bArr[i] & 255);
        }
        for (int i2 = 4; i2 < 8; i2++) {
            j = (j << 8) + (bArr2[i2] & 255);
        }
        return j;
    }

    private static byte[] computeKeySeedForBAC(String str, String str2, String str3) throws GeneralSecurityException {
        return Util.computeKeySeed(str, str2, str3, McElieceCCA2KeyGenParameterSpec.SHA1, true);
    }

    private static String fixDocumentNumber(String str) {
        StringBuilder sb = new StringBuilder(str == null ? "" : str.replace(Typography.less, ' ').trim().replace(' ', Typography.less));
        while (sb.length() < 9) {
            sb.append(Typography.less);
        }
        return sb.toString();
    }
}
