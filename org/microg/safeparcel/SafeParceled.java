package org.microg.safeparcel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
/* loaded from: classes6.dex */
public @interface SafeParceled {
    boolean mayNull() default false;

    Class subClass() default SafeParceled.class;

    @Deprecated
    String subType() default "undefined";

    boolean useClassLoader() default false;

    int value();
}
