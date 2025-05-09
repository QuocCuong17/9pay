package com.beust.jcommander;

import com.beust.jcommander.converters.CommaParameterSplitter;
import com.beust.jcommander.converters.IParameterSplitter;
import com.beust.jcommander.converters.NoConverter;
import com.beust.jcommander.validators.NoValidator;
import com.beust.jcommander.validators.NoValueValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface Parameter {
    int arity() default -1;

    Class<? extends IStringConverter<?>> converter() default NoConverter.class;

    String description() default "";

    String descriptionKey() default "";

    boolean echoInput() default false;

    boolean forceNonOverwritable() default false;

    boolean help() default false;

    boolean hidden() default false;

    Class<? extends IStringConverter<?>> listConverter() default NoConverter.class;

    String[] names() default {};

    boolean password() default false;

    boolean required() default false;

    Class<? extends IParameterSplitter> splitter() default CommaParameterSplitter.class;

    Class<? extends IValueValidator> validateValueWith() default NoValueValidator.class;

    Class<? extends IParameterValidator> validateWith() default NoValidator.class;

    boolean variableArity() default false;
}
