package com.beust.jcommander;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface Parameters {
    public static final String DEFAULT_OPTION_PREFIXES = "-";

    String commandDescription() default "";

    String commandDescriptionKey() default "";

    String[] commandNames() default {};

    boolean hidden() default false;

    String optionPrefixes() default "-";

    String resourceBundle() default "";

    String separators() default " ";
}
