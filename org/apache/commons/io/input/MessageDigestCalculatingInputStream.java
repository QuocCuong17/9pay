package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.io.input.ObservableInputStream;

/* loaded from: classes5.dex */
public class MessageDigestCalculatingInputStream extends ObservableInputStream {
    private final MessageDigest messageDigest;

    /* loaded from: classes5.dex */
    public static class MessageDigestMaintainingObserver extends ObservableInputStream.Observer {
        private final MessageDigest messageDigest;

        public MessageDigestMaintainingObserver(MessageDigest messageDigest) {
            this.messageDigest = messageDigest;
        }

        @Override // org.apache.commons.io.input.ObservableInputStream.Observer
        public void data(int i) throws IOException {
            this.messageDigest.update((byte) i);
        }

        @Override // org.apache.commons.io.input.ObservableInputStream.Observer
        public void data(byte[] bArr, int i, int i2) throws IOException {
            this.messageDigest.update(bArr, i, i2);
        }
    }

    public MessageDigestCalculatingInputStream(InputStream inputStream, MessageDigest messageDigest) {
        super(inputStream);
        this.messageDigest = messageDigest;
        add(new MessageDigestMaintainingObserver(messageDigest));
    }

    public MessageDigestCalculatingInputStream(InputStream inputStream, String str) throws NoSuchAlgorithmException {
        this(inputStream, MessageDigest.getInstance(str));
    }

    public MessageDigestCalculatingInputStream(InputStream inputStream) throws NoSuchAlgorithmException {
        this(inputStream, MessageDigest.getInstance("MD5"));
    }

    public MessageDigest getMessageDigest() {
        return this.messageDigest;
    }
}
