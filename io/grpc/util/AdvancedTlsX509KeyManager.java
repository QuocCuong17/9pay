package io.grpc.util;

import com.google.common.base.Preconditions;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedKeyManager;

/* loaded from: classes5.dex */
public final class AdvancedTlsX509KeyManager extends X509ExtendedKeyManager {
    private static final Logger log = Logger.getLogger(AdvancedTlsX509KeyManager.class.getName());
    private volatile KeyInfo keyInfo;

    /* loaded from: classes5.dex */
    public interface Closeable extends java.io.Closeable {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        void close();
    }

    @Override // javax.net.ssl.X509KeyManager
    public String chooseClientAlias(String[] strArr, Principal[] principalArr, Socket socket) {
        return "default";
    }

    @Override // javax.net.ssl.X509ExtendedKeyManager
    public String chooseEngineClientAlias(String[] strArr, Principal[] principalArr, SSLEngine sSLEngine) {
        return "default";
    }

    @Override // javax.net.ssl.X509ExtendedKeyManager
    public String chooseEngineServerAlias(String str, Principal[] principalArr, SSLEngine sSLEngine) {
        return "default";
    }

    @Override // javax.net.ssl.X509KeyManager
    public String chooseServerAlias(String str, Principal[] principalArr, Socket socket) {
        return "default";
    }

    @Override // javax.net.ssl.X509KeyManager
    public PrivateKey getPrivateKey(String str) {
        if (str.equals("default")) {
            return this.keyInfo.key;
        }
        return null;
    }

    @Override // javax.net.ssl.X509KeyManager
    public X509Certificate[] getCertificateChain(String str) {
        if (str.equals("default")) {
            return (X509Certificate[]) Arrays.copyOf(this.keyInfo.certs, this.keyInfo.certs.length);
        }
        return null;
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getClientAliases(String str, Principal[] principalArr) {
        return new String[]{"default"};
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getServerAliases(String str, Principal[] principalArr) {
        return new String[]{"default"};
    }

    public void updateIdentityCredentials(PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
        this.keyInfo = new KeyInfo((PrivateKey) Preconditions.checkNotNull(privateKey, "key"), (X509Certificate[]) Preconditions.checkNotNull(x509CertificateArr, "certs"));
    }

    public Closeable updateIdentityCredentialsFromFile(File file, File file2, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) throws IOException, GeneralSecurityException {
        if (!readAndUpdate(file, file2, 0L, 0L).success) {
            throw new GeneralSecurityException("Files were unmodified before their initial update. Probably a bug.");
        }
        final ScheduledFuture<?> scheduleWithFixedDelay = scheduledExecutorService.scheduleWithFixedDelay(new LoadFilePathExecution(file, file2), j, j, timeUnit);
        return new Closeable() { // from class: io.grpc.util.AdvancedTlsX509KeyManager.1
            @Override // io.grpc.util.AdvancedTlsX509KeyManager.Closeable, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                scheduleWithFixedDelay.cancel(false);
            }
        };
    }

    public void updateIdentityCredentialsFromFile(File file, File file2) throws IOException, GeneralSecurityException {
        if (!readAndUpdate(file, file2, 0L, 0L).success) {
            throw new GeneralSecurityException("Files were unmodified before their initial update. Probably a bug.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class KeyInfo {
        final X509Certificate[] certs;
        final PrivateKey key;

        public KeyInfo(PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
            this.key = privateKey;
            this.certs = x509CertificateArr;
        }
    }

    /* loaded from: classes5.dex */
    private class LoadFilePathExecution implements Runnable {
        File certFile;
        File keyFile;
        long currentKeyTime = 0;
        long currentCertTime = 0;

        public LoadFilePathExecution(File file, File file2) {
            this.keyFile = file;
            this.certFile = file2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                UpdateResult readAndUpdate = AdvancedTlsX509KeyManager.this.readAndUpdate(this.keyFile, this.certFile, this.currentKeyTime, this.currentCertTime);
                if (readAndUpdate.success) {
                    this.currentKeyTime = readAndUpdate.keyTime;
                    this.currentCertTime = readAndUpdate.certTime;
                }
            } catch (IOException | GeneralSecurityException e) {
                AdvancedTlsX509KeyManager.log.log(Level.SEVERE, "Failed refreshing private key and certificate chain from files. Using previous ones", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class UpdateResult {
        long certTime;
        long keyTime;
        boolean success;

        public UpdateResult(boolean z, long j, long j2) {
            this.success = z;
            this.keyTime = j;
            this.certTime = j2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UpdateResult readAndUpdate(File file, File file2, long j, long j2) throws IOException, GeneralSecurityException {
        long lastModified = file.lastModified();
        long lastModified2 = file2.lastModified();
        if (lastModified != j && lastModified2 != j2) {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                try {
                    PrivateKey privateKey = CertificateUtils.getPrivateKey(fileInputStream);
                    FileInputStream fileInputStream2 = new FileInputStream(file2);
                    try {
                        try {
                            updateIdentityCredentials(privateKey, CertificateUtils.getX509Certificates(fileInputStream2));
                            UpdateResult updateResult = new UpdateResult(true, lastModified, lastModified2);
                            fileInputStream2.close();
                            fileInputStream.close();
                            return updateResult;
                        } catch (Throwable th) {
                            th = th;
                            fileInputStream2.close();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream.close();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                fileInputStream.close();
                throw th;
            }
        } else {
            return new UpdateResult(false, j, j2);
        }
    }
}
