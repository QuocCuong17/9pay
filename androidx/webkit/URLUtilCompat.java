package androidx.webkit;

import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public final class URLUtilCompat {
    private static final Pattern DISPOSITION_PATTERN = Pattern.compile("\\s*(\\S+?) # Group 1: parameter name\n\\s*=\\s* # Match equals sign\n(?: # non-capturing group of options\n   '( (?: [^'\\\\] | \\\\. )* )' # Group 2: single-quoted\n | \"( (?: [^\"\\\\] | \\\\. )*  )\" # Group 3: double-quoted\n | ( [^'\"][^;\\s]* ) # Group 4: un-quoted parameter\n)\\s*;? # Optional end semicolon", 4);

    private URLUtilCompat() {
    }

    public static String guessFileName(String str, String str2, String str3) {
        String filenameSuggestion = getFilenameSuggestion(str, str2);
        String suggestExtensionFromMimeType = suggestExtensionFromMimeType(str3);
        if (filenameSuggestion.indexOf(46) < 0) {
            return filenameSuggestion + suggestExtensionFromMimeType;
        }
        if (str3 == null || !extensionDifferentFromMimeType(filenameSuggestion, str3)) {
            return filenameSuggestion;
        }
        return filenameSuggestion + suggestExtensionFromMimeType;
    }

    private static String getFilenameSuggestion(String str, String str2) {
        String lastPathSegment;
        String filenameFromContentDisposition;
        if (str2 != null && (filenameFromContentDisposition = getFilenameFromContentDisposition(str2)) != null) {
            return replacePathSeparators(filenameFromContentDisposition);
        }
        Uri parse = Uri.parse(str);
        return (parse == null || (lastPathSegment = parse.getLastPathSegment()) == null) ? "downloadfile" : replacePathSeparators(lastPathSegment);
    }

    private static String replacePathSeparators(String str) {
        return str.replaceAll(RemoteSettings.FORWARD_SLASH_STRING, "_");
    }

    private static boolean extensionDifferentFromMimeType(String str, String str2) {
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str.substring(str.lastIndexOf(46) + 1));
        return (mimeTypeFromExtension == null || mimeTypeFromExtension.equalsIgnoreCase(str2)) ? false : true;
    }

    private static String suggestExtensionFromMimeType(String str) {
        if (str == null) {
            return ".bin";
        }
        String extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(str);
        if (extensionFromMimeType == null) {
            return str.equalsIgnoreCase("text/html") ? ".html" : str.toLowerCase(Locale.ROOT).startsWith("text/") ? ".txt" : ".bin";
        }
        return "." + extensionFromMimeType;
    }

    public static String getFilenameFromContentDisposition(String str) {
        String group;
        String[] split = str.trim().split(";", 2);
        String str2 = null;
        if (split.length < 2 || "inline".equalsIgnoreCase(split[0].trim())) {
            return null;
        }
        Matcher matcher = DISPOSITION_PATTERN.matcher(split[1]);
        String str3 = null;
        while (matcher.find()) {
            String group2 = matcher.group(1);
            if (matcher.group(2) != null) {
                group = removeSlashEscapes(matcher.group(2));
            } else if (matcher.group(3) != null) {
                group = removeSlashEscapes(matcher.group(3));
            } else {
                group = matcher.group(4);
            }
            if (group2 != null && group != null) {
                if ("filename*".equalsIgnoreCase(group2)) {
                    str2 = parseExtValueString(group);
                } else if ("filename".equalsIgnoreCase(group2)) {
                    str3 = group;
                }
            }
        }
        return str2 != null ? str2 : str3;
    }

    private static String removeSlashEscapes(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("\\\\(.)", "$1");
    }

    private static String parseExtValueString(String str) {
        String[] split = str.split("'", 3);
        if (split.length < 3) {
            return null;
        }
        String str2 = split[0];
        try {
            return URLDecoder.decode(encodePlusCharacters(split[2], str2), str2);
        } catch (UnsupportedEncodingException | RuntimeException unused) {
            return null;
        }
    }

    private static String encodePlusCharacters(String str, String str2) {
        Charset forName = Charset.forName(str2);
        StringBuilder sb = new StringBuilder();
        for (byte b : forName.encode("+").array()) {
            sb.append(String.format("%02x", Byte.valueOf(b)));
        }
        return str.replaceAll("\\+", sb.toString());
    }
}
