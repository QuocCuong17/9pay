package com.beust.jcommander;

import com.beust.jcommander.validators.NoValidator;
import com.beust.jcommander.validators.NoValueValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface DynamicParameter {
    String assignment() default "=";

    String description() default "";

    String descriptionKey() default "";

    boolean hidden() default false;

    String[] names() default {};

    boolean required() default false;

    Class<? extends IValueValidator> validateValueWith() default NoValueValidator.class;

    Class<? extends IParameterValidator> validateWith() default NoValidator.class;
}
