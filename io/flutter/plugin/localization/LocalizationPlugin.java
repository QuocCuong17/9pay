package io.flutter.plugin.localization;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import com.beust.jcommander.Parameters;
import io.flutter.embedding.engine.systemchannels.LocalizationChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class LocalizationPlugin {
    private final Context context;
    private final LocalizationChannel localizationChannel;
    final LocalizationChannel.LocalizationMessageHandler localizationMessageHandler;

    public LocalizationPlugin(Context context, LocalizationChannel localizationChannel) {
        LocalizationChannel.LocalizationMessageHandler localizationMessageHandler = new LocalizationChannel.LocalizationMessageHandler() { // from class: io.flutter.plugin.localization.LocalizationPlugin.1
            @Override // io.flutter.embedding.engine.systemchannels.LocalizationChannel.LocalizationMessageHandler
            public String getStringResource(String str, String str2) {
                Context context2 = LocalizationPlugin.this.context;
                if (str2 != null) {
                    Locale localeFromString = LocalizationPlugin.localeFromString(str2);
                    Configuration configuration = new Configuration(LocalizationPlugin.this.context.getResources().getConfiguration());
                    configuration.setLocale(localeFromString);
                    context2 = LocalizationPlugin.this.context.createConfigurationContext(configuration);
                }
                int identifier = context2.getResources().getIdentifier(str, "string", LocalizationPlugin.this.context.getPackageName());
                if (identifier != 0) {
                    return context2.getResources().getString(identifier);
                }
                return null;
            }
        };
        this.localizationMessageHandler = localizationMessageHandler;
        this.context = context;
        this.localizationChannel = localizationChannel;
        localizationChannel.setLocalizationMessageHandler(localizationMessageHandler);
    }

    public Locale resolveNativeLocale(List<Locale> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            ArrayList arrayList = new ArrayList();
            LocaleList locales = this.context.getResources().getConfiguration().getLocales();
            int size = locales.size();
            for (int i = 0; i < size; i++) {
                Locale locale = locales.get(i);
                String language = locale.getLanguage();
                if (!locale.getScript().isEmpty()) {
                    language = language + Parameters.DEFAULT_OPTION_PREFIXES + locale.getScript();
                }
                if (!locale.getCountry().isEmpty()) {
                    language = language + Parameters.DEFAULT_OPTION_PREFIXES + locale.getCountry();
                }
                arrayList.add(new Locale.LanguageRange(language));
                arrayList.add(new Locale.LanguageRange(locale.getLanguage()));
                arrayList.add(new Locale.LanguageRange(locale.getLanguage() + "-*"));
            }
            Locale lookup = Locale.lookup(arrayList, list);
            return lookup != null ? lookup : list.get(0);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleList locales2 = this.context.getResources().getConfiguration().getLocales();
            for (int i2 = 0; i2 < locales2.size(); i2++) {
                Locale locale2 = locales2.get(i2);
                for (Locale locale3 : list) {
                    if (locale2.equals(locale3)) {
                        return locale3;
                    }
                }
                for (Locale locale4 : list) {
                    if (locale2.getLanguage().equals(locale4.toLanguageTag())) {
                        return locale4;
                    }
                }
                for (Locale locale5 : list) {
                    if (locale2.getLanguage().equals(locale5.getLanguage())) {
                        return locale5;
                    }
                }
            }
            return list.get(0);
        }
        Locale locale6 = this.context.getResources().getConfiguration().locale;
        if (locale6 != null) {
            for (Locale locale7 : list) {
                if (locale6.equals(locale7)) {
                    return locale7;
                }
            }
            for (Locale locale8 : list) {
                if (locale6.getLanguage().equals(locale8.toString())) {
                    return locale8;
                }
            }
        }
        return list.get(0);
    }

    public void sendLocalesToFlutter(Configuration configuration) {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleList locales = configuration.getLocales();
            int size = locales.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(locales.get(i));
            }
        } else {
            arrayList.add(configuration.locale);
        }
        this.localizationChannel.sendLocales(arrayList);
    }

    public static Locale localeFromString(String str) {
        String str2;
        String[] split = str.replace('_', '-').split(Parameters.DEFAULT_OPTION_PREFIXES, -1);
        String str3 = split[0];
        String str4 = "";
        int i = 1;
        if (split.length <= 1 || split[1].length() != 4) {
            str2 = "";
        } else {
            str2 = split[1];
            i = 2;
        }
        if (split.length > i && split[i].length() >= 2 && split[i].length() <= 3) {
            str4 = split[i];
        }
        return new Locale(str3, str4, str2);
    }
}
