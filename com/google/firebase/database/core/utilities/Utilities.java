package com.google.firebase.database.core.utilities;

import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.RepoInfo;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Map;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* loaded from: classes4.dex */
public class Utilities {
    private static final char[] HEX_CHARACTERS = "0123456789abcdef".toCharArray();

    public static int compareInts(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public static int compareLongs(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }

    public static ParsedUrl parseUrl(String str) throws DatabaseException {
        try {
            Uri parse = Uri.parse(str);
            String scheme = parse.getScheme();
            if (scheme == null) {
                throw new IllegalArgumentException("Database URL does not specify a URL scheme");
            }
            String host = parse.getHost();
            if (host == null) {
                throw new IllegalArgumentException("Database URL does not specify a valid host");
            }
            String queryParameter = parse.getQueryParameter("ns");
            if (queryParameter == null) {
                queryParameter = host.split("\\.", -1)[0].toLowerCase(Locale.US);
            }
            RepoInfo repoInfo = new RepoInfo();
            repoInfo.host = host.toLowerCase(Locale.US);
            int port = parse.getPort();
            if (port != -1) {
                repoInfo.secure = scheme.equals("https") || scheme.equals("wss");
                repoInfo.host += ":" + port;
            } else {
                repoInfo.secure = true;
            }
            repoInfo.internalHost = repoInfo.host;
            repoInfo.namespace = queryParameter;
            String replace = extractPathString(str).replace("+", " ");
            Validation.validateRootPathString(replace);
            ParsedUrl parsedUrl = new ParsedUrl();
            parsedUrl.path = new Path(replace);
            parsedUrl.repoInfo = repoInfo;
            return parsedUrl;
        } catch (Exception e) {
            throw new DatabaseException("Invalid Firebase Database url specified: " + str, e);
        }
    }

    private static String extractPathString(String str) {
        int indexOf = str.indexOf("//");
        if (indexOf == -1) {
            throw new DatabaseException("Firebase Database URL is missing URL scheme");
        }
        String substring = str.substring(indexOf + 2);
        int indexOf2 = substring.indexOf(RemoteSettings.FORWARD_SLASH_STRING);
        if (indexOf2 == -1) {
            return "";
        }
        int indexOf3 = substring.indexOf("?");
        if (indexOf3 != -1) {
            return substring.substring(indexOf2 + 1, indexOf3);
        }
        return substring.substring(indexOf2 + 1);
    }

    public static String sha1HexDigest(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA1);
            messageDigest.update(str.getBytes("UTF-8"));
            return Base64.encodeToString(messageDigest.digest(), 2);
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("UTF-8 encoding is required for Firebase Database to run!");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Missing SHA-1 MessageDigest provider.", e);
        }
    }

    public static String stringHashV2Representation(String str) {
        String replace = str.indexOf(92) != -1 ? str.replace("\\", "\\\\") : str;
        if (str.indexOf(34) != -1) {
            replace = replace.replace("\"", "\\\"");
        }
        return '\"' + replace + '\"';
    }

    public static String doubleToHashString(double d) {
        StringBuilder sb = new StringBuilder(16);
        long doubleToLongBits = Double.doubleToLongBits(d);
        for (int i = 7; i >= 0; i--) {
            int i2 = (int) ((doubleToLongBits >>> (i * 8)) & 255);
            char[] cArr = HEX_CHARACTERS;
            sb.append(cArr[(i2 >> 4) & 15]);
            sb.append(cArr[i2 & 15]);
        }
        return sb.toString();
    }

    public static Integer tryParseInt(String str) {
        if (str.length() > 11 || str.length() == 0) {
            return null;
        }
        int i = 0;
        boolean z = true;
        if (str.charAt(0) != '-') {
            z = false;
        } else {
            if (str.length() == 1) {
                return null;
            }
            i = 1;
        }
        long j = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return null;
            }
            j = (j * 10) + (charAt - '0');
            i++;
        }
        if (!z) {
            if (j > 2147483647L) {
                return null;
            }
            return Integer.valueOf((int) j);
        }
        long j2 = -j;
        if (j2 < -2147483648L) {
            return null;
        }
        return Integer.valueOf((int) j2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <C> C castOrNull(Object obj, Class<C> cls) {
        if (cls.isAssignableFrom(obj.getClass())) {
            return obj;
        }
        return null;
    }

    public static <C> C getOrNull(Object obj, String str, Class<C> cls) {
        Object obj2;
        if (obj == null || (obj2 = ((Map) castOrNull(obj, Map.class)).get(str)) == null) {
            return null;
        }
        return (C) castOrNull(obj2, cls);
    }

    public static void hardAssert(boolean z) {
        hardAssert(z, "");
    }

    public static void hardAssert(boolean z, String str) {
        if (z) {
            return;
        }
        Log.w("FirebaseDatabase", "Assertion failed: " + str);
    }

    public static Pair<Task<Void>, DatabaseReference.CompletionListener> wrapOnComplete(DatabaseReference.CompletionListener completionListener) {
        if (completionListener == null) {
            final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            return new Pair<>(taskCompletionSource.getTask(), new DatabaseReference.CompletionListener() { // from class: com.google.firebase.database.core.utilities.Utilities.1
                @Override // com.google.firebase.database.DatabaseReference.CompletionListener
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        TaskCompletionSource.this.setException(databaseError.toException());
                    } else {
                        TaskCompletionSource.this.setResult(null);
                    }
                }
            });
        }
        return new Pair<>(null, completionListener);
    }

    public static boolean equals(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }
}
