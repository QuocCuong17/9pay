package com.it_nomads.fluttersecurestorage.ciphers;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import java.math.BigInteger;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.util.Calendar;
import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.security.auth.x500.X500Principal;

/* loaded from: classes4.dex */
public class RSACipherOAEPImplementation extends RSACipher18Implementation {
    @Override // com.it_nomads.fluttersecurestorage.ciphers.RSACipher18Implementation, com.it_nomads.fluttersecurestorage.ciphers.KeyCipher
    public /* bridge */ /* synthetic */ Key unwrap(byte[] bArr, String str) throws Exception {
        return super.unwrap(bArr, str);
    }

    @Override // com.it_nomads.fluttersecurestorage.ciphers.RSACipher18Implementation, com.it_nomads.fluttersecurestorage.ciphers.KeyCipher
    public /* bridge */ /* synthetic */ byte[] wrap(Key key) throws Exception {
        return super.wrap(key);
    }

    public RSACipherOAEPImplementation(Context context) throws Exception {
        super(context);
    }

    @Override // com.it_nomads.fluttersecurestorage.ciphers.RSACipher18Implementation
    protected String createKeyAlias() {
        return this.context.getPackageName() + ".FlutterSecureStoragePluginKeyOAEP";
    }

    @Override // com.it_nomads.fluttersecurestorage.ciphers.RSACipher18Implementation
    protected AlgorithmParameterSpec makeAlgorithmParameterSpec(Context context, Calendar calendar, Calendar calendar2) {
        return new KeyGenParameterSpec.Builder(this.keyAlias, 3).setCertificateSubject(new X500Principal("CN=" + this.keyAlias)).setDigests("SHA-256").setBlockModes("ECB").setEncryptionPaddings("OAEPPadding").setCertificateSerialNumber(BigInteger.valueOf(1L)).setCertificateNotBefore(calendar.getTime()).setCertificateNotAfter(calendar2.getTime()).build();
    }

    @Override // com.it_nomads.fluttersecurestorage.ciphers.RSACipher18Implementation
    protected Cipher getRSACipher() throws Exception {
        return Cipher.getInstance("RSA/ECB/OAEPPadding", "AndroidKeyStoreBCWorkaround");
    }

    @Override // com.it_nomads.fluttersecurestorage.ciphers.RSACipher18Implementation
    protected AlgorithmParameterSpec getAlgorithmParameterSpec() {
        return new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT);
    }
}
