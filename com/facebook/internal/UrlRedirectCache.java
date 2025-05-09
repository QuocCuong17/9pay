package com.facebook.internal;

import android.net.Uri;
import com.facebook.LoggingBehavior;
import com.facebook.internal.FileLruCache;
import com.facebook.share.internal.ShareConstants;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;

/* compiled from: UrlRedirectCache.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0007J\b\u0010\r\u001a\u00020\tH\u0007J\b\u0010\u000e\u001a\u00020\u0007H\u0007J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/facebook/internal/UrlRedirectCache;", "", "()V", "redirectContentTag", "", "tag", "urlRedirectFileLruCache", "Lcom/facebook/internal/FileLruCache;", "cacheUriRedirect", "", "fromUri", "Landroid/net/Uri;", "toUri", "clearCache", "getCache", "getRedirectedUri", ShareConstants.MEDIA_URI, "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UrlRedirectCache {
    public static final UrlRedirectCache INSTANCE = new UrlRedirectCache();
    private static final String redirectContentTag;
    private static final String tag;
    private static FileLruCache urlRedirectFileLruCache;

    private UrlRedirectCache() {
    }

    static {
        String simpleName = Reflection.getOrCreateKotlinClass(UrlRedirectCache.class).getSimpleName();
        if (simpleName == null) {
            simpleName = "UrlRedirectCache";
        }
        tag = simpleName;
        redirectContentTag = simpleName + "_Redirect";
    }

    @JvmStatic
    public static final synchronized FileLruCache getCache() throws IOException {
        FileLruCache fileLruCache;
        synchronized (UrlRedirectCache.class) {
            fileLruCache = urlRedirectFileLruCache;
            if (fileLruCache == null) {
                fileLruCache = new FileLruCache(tag, new FileLruCache.Limits());
            }
            urlRedirectFileLruCache = fileLruCache;
        }
        return fileLruCache;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x005a, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r3, r11) == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005c, code lost:
    
        r5 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005e, code lost:
    
        com.facebook.internal.Logger.INSTANCE.log(com.facebook.LoggingBehavior.CACHE, 6, com.facebook.internal.UrlRedirectCache.tag, "A loop detected in UrlRedirectCache");
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x006a, code lost:
    
        com.facebook.internal.Utility.closeQuietly(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x006f, code lost:
    
        return null;
     */
    @JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Uri getRedirectedUri(Uri uri) {
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2;
        InputStreamReader inputStreamReader3 = null;
        if (uri == null) {
            return null;
        }
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "uri.toString()");
        HashSet hashSet = new HashSet();
        hashSet.add(uri2);
        try {
            FileLruCache cache = getCache();
            InputStream inputStream = cache.get(uri2, redirectContentTag);
            inputStreamReader = null;
            boolean z = false;
            while (true) {
                if (inputStream == null) {
                    break;
                }
                z = true;
                try {
                    try {
                        inputStreamReader2 = new InputStreamReader(inputStream);
                    } catch (IOException e) {
                        e = e;
                    }
                } catch (Throwable th) {
                    th = th;
                    inputStreamReader3 = inputStreamReader;
                }
                try {
                    char[] cArr = new char[128];
                    StringBuilder sb = new StringBuilder();
                    for (int read = inputStreamReader2.read(cArr, 0, 128); read > 0; read = inputStreamReader2.read(cArr, 0, 128)) {
                        sb.append(cArr, 0, read);
                    }
                    Utility.closeQuietly(inputStreamReader2);
                    String sb2 = sb.toString();
                    Intrinsics.checkNotNullExpressionValue(sb2, "urlBuilder.toString()");
                    if (hashSet.contains(sb2)) {
                        break;
                    }
                    hashSet.add(sb2);
                    inputStreamReader = inputStreamReader2;
                    inputStream = cache.get(sb2, redirectContentTag);
                    uri2 = sb2;
                } catch (IOException e2) {
                    e = e2;
                    inputStreamReader = inputStreamReader2;
                    Logger.INSTANCE.log(LoggingBehavior.CACHE, 4, tag, "IOException when accessing cache: " + e.getMessage());
                    Utility.closeQuietly(inputStreamReader);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    inputStreamReader3 = inputStreamReader2;
                    Utility.closeQuietly(inputStreamReader3);
                    throw th;
                }
            }
            if (z) {
                Uri parse = Uri.parse(uri2);
                Utility.closeQuietly(inputStreamReader);
                return parse;
            }
        } catch (IOException e3) {
            e = e3;
            inputStreamReader = null;
        } catch (Throwable th3) {
            th = th3;
        }
        Utility.closeQuietly(inputStreamReader);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r0v7 */
    @JvmStatic
    public static final void cacheUriRedirect(Uri fromUri, Uri toUri) {
        Object obj;
        if (fromUri == null || toUri == null) {
            return;
        }
        ?? r0 = 0;
        r0 = 0;
        try {
            try {
                FileLruCache cache = getCache();
                String uri = fromUri.toString();
                Intrinsics.checkNotNullExpressionValue(uri, "fromUri.toString()");
                r0 = cache.openPutStream(uri, redirectContentTag);
                String uri2 = toUri.toString();
                Intrinsics.checkNotNullExpressionValue(uri2, "toUri.toString()");
                byte[] bytes = uri2.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                r0.write(bytes);
                obj = r0;
            } catch (IOException e) {
                Logger.INSTANCE.log(LoggingBehavior.CACHE, 4, tag, "IOException when accessing cache: " + e.getMessage());
                obj = r0;
            }
        } finally {
            Utility.closeQuietly((Closeable) r0);
        }
    }

    @JvmStatic
    public static final void clearCache() {
        try {
            getCache().clearCache();
        } catch (IOException e) {
            Logger.INSTANCE.log(LoggingBehavior.CACHE, 5, tag, "clearCache failed " + e.getMessage());
        }
    }
}
