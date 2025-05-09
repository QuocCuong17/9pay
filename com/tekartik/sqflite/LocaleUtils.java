package com.tekartik.sqflite;

import android.os.Build;
import com.beust.jcommander.Parameters;
import java.util.Locale;

/* loaded from: classes5.dex */
public class LocaleUtils {
    static Locale localeForLanguateTag(String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            return localeForLanguageTag21(str);
        }
        return localeForLanguageTagPre21(str);
    }

    static Locale localeForLanguageTag21(String str) {
        return Locale.forLanguageTag(str);
    }

    static Locale localeForLanguageTagPre21(String str) {
        String str2;
        String str3;
        String str4;
        String[] split = str.split(Parameters.DEFAULT_OPTION_PREFIXES);
        str2 = "";
        if (split.length > 0) {
            String str5 = split[0];
            if (split.length > 1) {
                str4 = split[1];
                str3 = split.length > 2 ? split[split.length - 1] : "";
            } else {
                str3 = "";
                str4 = str3;
            }
            str2 = str5;
        } else {
            str3 = "";
            str4 = str3;
        }
        return new Locale(str2, str4, str3);
    }
}
