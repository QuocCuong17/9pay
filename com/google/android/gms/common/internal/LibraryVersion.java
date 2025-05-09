package com.google.android.gms.common.internal;

import com.google.android.gms.common.util.IOUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
@Deprecated
/* loaded from: classes3.dex */
public class LibraryVersion {
    private static final GmsLogger zza = new GmsLogger("LibraryVersion", "");
    private static LibraryVersion zzb = new LibraryVersion();
    private ConcurrentHashMap zzc = new ConcurrentHashMap();

    protected LibraryVersion() {
    }

    public static LibraryVersion getInstance() {
        return zzb;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0099  */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    @Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String getVersion(String str) {
        Object obj;
        InputStream resourceAsStream;
        Preconditions.checkNotEmpty(str, "Please provide a valid libraryName");
        if (this.zzc.containsKey(str)) {
            return (String) this.zzc.get(str);
        }
        Properties properties = new Properties();
        ?? r4 = 0;
        r4 = 0;
        r4 = 0;
        InputStream inputStream = null;
        try {
            try {
                resourceAsStream = LibraryVersion.class.getResourceAsStream(String.format("/%s.properties", str));
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
            obj = null;
        }
        try {
            if (resourceAsStream != null) {
                properties.load(resourceAsStream);
                String property = properties.getProperty("version", null);
                zza.v("LibraryVersion", str + " version is " + property);
                r4 = property;
            } else {
                zza.w("LibraryVersion", "Failed to get app version for libraryName: " + str);
            }
            if (resourceAsStream != null) {
                IOUtils.closeQuietly(resourceAsStream);
            }
        } catch (IOException e2) {
            e = e2;
            Object obj2 = r4;
            inputStream = resourceAsStream;
            obj = obj2;
            zza.e("LibraryVersion", "Failed to get app version for libraryName: " + str, e);
            if (inputStream != null) {
                IOUtils.closeQuietly(inputStream);
            }
            r4 = obj;
            if (r4 == 0) {
            }
            this.zzc.put(str, r4);
            return r4;
        } catch (Throwable th2) {
            th = th2;
            r4 = resourceAsStream;
            if (r4 != 0) {
                IOUtils.closeQuietly((Closeable) r4);
            }
            throw th;
        }
        if (r4 == 0) {
            zza.d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used");
            r4 = "UNKNOWN";
        }
        this.zzc.put(str, r4);
        return r4;
    }
}
