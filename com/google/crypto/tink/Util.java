package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.proto.OutputPrefixType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.Iterator;

/* loaded from: classes4.dex */
final class Util {
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    public static KeysetInfo getKeysetInfo(Keyset keyset) {
        KeysetInfo.Builder primaryKeyId = KeysetInfo.newBuilder().setPrimaryKeyId(keyset.getPrimaryKeyId());
        Iterator<Keyset.Key> it = keyset.getKeyList().iterator();
        while (it.hasNext()) {
            primaryKeyId.addKeyInfo(getKeyInfo(it.next()));
        }
        return (KeysetInfo) primaryKeyId.build();
    }

    public static KeysetInfo.KeyInfo getKeyInfo(Keyset.Key key) {
        return (KeysetInfo.KeyInfo) KeysetInfo.KeyInfo.newBuilder().setTypeUrl(key.getKeyData().getTypeUrl()).setStatus(key.getStatus()).setOutputPrefixType(key.getOutputPrefixType()).setKeyId(key.getKeyId()).build();
    }

    public static void validateKey(Keyset.Key key) throws GeneralSecurityException {
        if (!key.hasKeyData()) {
            throw new GeneralSecurityException(String.format("key %d has no key data", Integer.valueOf(key.getKeyId())));
        }
        if (key.getOutputPrefixType() == OutputPrefixType.UNKNOWN_PREFIX) {
            throw new GeneralSecurityException(String.format("key %d has unknown prefix", Integer.valueOf(key.getKeyId())));
        }
        if (key.getStatus() == KeyStatusType.UNKNOWN_STATUS) {
            throw new GeneralSecurityException(String.format("key %d has unknown status", Integer.valueOf(key.getKeyId())));
        }
    }

    public static void validateKeyset(Keyset keyset) throws GeneralSecurityException {
        int primaryKeyId = keyset.getPrimaryKeyId();
        boolean z = true;
        int i = 0;
        boolean z2 = false;
        for (Keyset.Key key : keyset.getKeyList()) {
            if (key.getStatus() == KeyStatusType.ENABLED) {
                validateKey(key);
                if (key.getKeyId() == primaryKeyId) {
                    if (z2) {
                        throw new GeneralSecurityException("keyset contains multiple primary keys");
                    }
                    z2 = true;
                }
                if (key.getKeyData().getKeyMaterialType() != KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC) {
                    z = false;
                }
                i++;
            }
        }
        if (i == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        }
        if (!z2 && !z) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }

    public static byte[] readAll(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    private Util() {
    }
}
