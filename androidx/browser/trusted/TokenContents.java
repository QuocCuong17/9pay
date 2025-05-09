package androidx.browser.trusted;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
final class TokenContents {
    private final byte[] mContents;
    private List<byte[]> mFingerprints;
    private String mPackageName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TokenContents deserialize(byte[] bArr) {
        return new TokenContents(bArr);
    }

    private TokenContents(byte[] bArr) {
        this.mContents = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TokenContents create(String str, List<byte[]> list) throws IOException {
        return new TokenContents(createToken(str, list), str, list);
    }

    private TokenContents(byte[] bArr, String str, List<byte[]> list) {
        this.mContents = bArr;
        this.mPackageName = str;
        this.mFingerprints = new ArrayList(list.size());
        for (byte[] bArr2 : list) {
            this.mFingerprints.add(Arrays.copyOf(bArr2, bArr2.length));
        }
    }

    public String getPackageName() throws IOException {
        parseIfNeeded();
        String str = this.mPackageName;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException();
    }

    public int getFingerprintCount() throws IOException {
        parseIfNeeded();
        List<byte[]> list = this.mFingerprints;
        if (list == null) {
            throw new IllegalStateException();
        }
        return list.size();
    }

    public byte[] getFingerprint(int i) throws IOException {
        parseIfNeeded();
        List<byte[]> list = this.mFingerprints;
        if (list == null) {
            throw new IllegalStateException();
        }
        return Arrays.copyOf(list.get(i), this.mFingerprints.get(i).length);
    }

    public byte[] serialize() {
        byte[] bArr = this.mContents;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.mContents, ((TokenContents) obj).mContents);
    }

    public int hashCode() {
        return Arrays.hashCode(this.mContents);
    }

    private static byte[] createToken(String str, List<byte[]> list) throws IOException {
        Collections.sort(list, new Comparator() { // from class: androidx.browser.trusted.TokenContents$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compareByteArrays;
                compareByteArrays = TokenContents.compareByteArrays((byte[]) obj, (byte[]) obj2);
                return compareByteArrays;
            }
        });
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(str);
        dataOutputStream.writeInt(list.size());
        for (byte[] bArr : list) {
            dataOutputStream.writeInt(bArr.length);
            dataOutputStream.write(bArr);
        }
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    /* JADX INFO: Access modifiers changed from: private */
    public static int compareByteArrays(byte[] r4, byte[] r5) {
        /*
            r0 = 0
            if (r4 != r5) goto L4
            return r0
        L4:
            if (r4 != 0) goto L8
            r4 = -1
            return r4
        L8:
            if (r5 != 0) goto Lc
            r4 = 1
            return r4
        Lc:
            r1 = r0
        Ld:
            int r2 = r4.length
            int r3 = r5.length
            int r2 = java.lang.Math.min(r2, r3)
            if (r1 >= r2) goto L24
            r2 = r4[r1]
            r3 = r5[r1]
            if (r2 == r3) goto L21
            r4 = r4[r1]
            r5 = r5[r1]
        L1f:
            int r4 = r4 - r5
            return r4
        L21:
            int r1 = r1 + 1
            goto Ld
        L24:
            int r1 = r4.length
            int r2 = r5.length
            if (r1 == r2) goto L2b
            int r4 = r4.length
            int r5 = r5.length
            goto L1f
        L2b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.browser.trusted.TokenContents.compareByteArrays(byte[], byte[]):int");
    }

    private void parseIfNeeded() throws IOException {
        if (this.mPackageName != null) {
            return;
        }
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(this.mContents));
        this.mPackageName = dataInputStream.readUTF();
        int readInt = dataInputStream.readInt();
        this.mFingerprints = new ArrayList(readInt);
        for (int i = 0; i < readInt; i++) {
            int readInt2 = dataInputStream.readInt();
            byte[] bArr = new byte[readInt2];
            if (dataInputStream.read(bArr) != readInt2) {
                throw new IllegalStateException("Could not read fingerprint");
            }
            this.mFingerprints.add(bArr);
        }
    }
}
