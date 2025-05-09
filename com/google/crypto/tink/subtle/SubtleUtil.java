package com.google.crypto.tink.subtle;

import com.google.crypto.tink.internal.BigIntegerEncoding;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.subtle.Enums;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* loaded from: classes4.dex */
public final class SubtleUtil {
    public static String toEcdsaAlgo(Enums.HashType hash) throws GeneralSecurityException {
        Validators.validateSignatureHash(hash);
        return hash + "withECDSA";
    }

    public static String toRsaSsaPkcs1Algo(Enums.HashType hash) throws GeneralSecurityException {
        Validators.validateSignatureHash(hash);
        return hash + "withRSA";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.subtle.SubtleUtil$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType;

        static {
            int[] iArr = new int[Enums.HashType.values().length];
            $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType = iArr;
            try {
                iArr[Enums.HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[Enums.HashType.SHA224.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[Enums.HashType.SHA256.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[Enums.HashType.SHA384.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[Enums.HashType.SHA512.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static String toDigestAlgo(Enums.HashType hash) throws GeneralSecurityException {
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[hash.ordinal()];
        if (i == 1) {
            return McElieceCCA2KeyGenParameterSpec.SHA1;
        }
        if (i == 2) {
            return McElieceCCA2KeyGenParameterSpec.SHA224;
        }
        if (i == 3) {
            return "SHA-256";
        }
        if (i == 4) {
            return McElieceCCA2KeyGenParameterSpec.SHA384;
        }
        if (i == 5) {
            return "SHA-512";
        }
        throw new GeneralSecurityException("Unsupported hash " + hash);
    }

    public static boolean isAndroid() {
        return "The Android Project".equals(System.getProperty("java.vendor"));
    }

    @Deprecated
    public static int androidApiLevel() {
        Integer androidApiLevel = Util.getAndroidApiLevel();
        if (androidApiLevel != null) {
            return androidApiLevel.intValue();
        }
        return -1;
    }

    public static BigInteger bytes2Integer(byte[] bs) {
        return BigIntegerEncoding.fromUnsignedBigEndianBytes(bs);
    }

    public static byte[] integer2Bytes(BigInteger num, int intendedLength) throws GeneralSecurityException {
        return BigIntegerEncoding.toBigEndianBytesOfFixedLength(num, intendedLength);
    }

    public static byte[] mgf1(byte[] mgfSeed, int maskLen, Enums.HashType mgfHash) throws GeneralSecurityException {
        MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance(toDigestAlgo(mgfHash));
        int digestLength = engineFactory.getDigestLength();
        byte[] bArr = new byte[maskLen];
        int i = 0;
        for (int i2 = 0; i2 <= (maskLen - 1) / digestLength; i2++) {
            engineFactory.reset();
            engineFactory.update(mgfSeed);
            engineFactory.update(integer2Bytes(BigInteger.valueOf(i2), 4));
            byte[] digest = engineFactory.digest();
            System.arraycopy(digest, 0, bArr, i, Math.min(digest.length, maskLen - i));
            i += digest.length;
        }
        return bArr;
    }

    public static void putAsUnsigedInt(ByteBuffer buffer, long value) throws GeneralSecurityException {
        if (0 > value || value >= 4294967296L) {
            throw new GeneralSecurityException("Index out of range");
        }
        buffer.putInt((int) value);
    }

    private SubtleUtil() {
    }
}
