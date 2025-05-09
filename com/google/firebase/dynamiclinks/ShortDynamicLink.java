package com.google.firebase.dynamiclinks;

import android.net.Uri;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* loaded from: classes4.dex */
public interface ShortDynamicLink {

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface Suffix {
        public static final int SHORT = 2;
        public static final int UNGUESSABLE = 1;
    }

    /* loaded from: classes4.dex */
    public interface Warning {
        @Deprecated
        String getCode();

        String getMessage();
    }

    Uri getPreviewLink();

    Uri getShortLink();

    List<? extends Warning> getWarnings();
}
