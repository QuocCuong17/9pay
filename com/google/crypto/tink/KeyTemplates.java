package com.google.crypto.tink;

import java.security.GeneralSecurityException;

/* loaded from: classes4.dex */
public final class KeyTemplates {
    public static KeyTemplate get(String name) throws GeneralSecurityException {
        KeyTemplate keyTemplate = Registry.keyTemplateMap().get(name);
        if (keyTemplate != null) {
            return keyTemplate;
        }
        throw new GeneralSecurityException("cannot find key template: " + name);
    }

    private KeyTemplates() {
    }
}
