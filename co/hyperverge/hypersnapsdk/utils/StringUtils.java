package co.hyperverge.hypersnapsdk.utils;

import android.text.Spanned;
import android.webkit.URLUtil;
import androidx.core.text.HtmlCompat;

/* loaded from: classes2.dex */
public class StringUtils {
    private StringUtils() {
    }

    public static boolean isEmptyOrNull(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isURL(String str) {
        return URLUtil.isNetworkUrl(str);
    }

    public static Spanned getHTML(String str) {
        return HtmlCompat.fromHtml(str, 0);
    }

    public static String capitalize(String str, char... cArr) {
        int length = cArr == null ? -1 : cArr.length;
        if (isEmptyOrNull(str) || length == 0) {
            return str;
        }
        char[] charArray = str.toCharArray();
        boolean z = true;
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (isDelimiter(c, cArr)) {
                z = true;
            } else if (z) {
                charArray[i] = Character.toTitleCase(c);
                z = false;
            }
        }
        return new String(charArray);
    }

    private static boolean isDelimiter(char c, char[] cArr) {
        return cArr == null ? Character.isWhitespace(c) : contains(cArr, c);
    }

    public static boolean contains(char[] cArr, char c) {
        return indexOf(cArr, c) != -1;
    }

    public static int indexOf(char[] cArr, char c) {
        if (cArr == null) {
            return -1;
        }
        for (int i = 0; i < cArr.length; i++) {
            if (c == cArr[i]) {
                return i;
            }
        }
        return -1;
    }
}
