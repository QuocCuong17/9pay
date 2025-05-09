package com.github.jaiimageio.impl.common;

import java.util.PropertyResourceBundle;

/* loaded from: classes3.dex */
public class I18NImpl {
    /* JADX INFO: Access modifiers changed from: protected */
    public static final String getString(String str, String str2) {
        try {
            return (String) new PropertyResourceBundle(Class.forName(str).getResourceAsStream("properties")).handleGetObject(str2);
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }
}
