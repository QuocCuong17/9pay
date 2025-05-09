package com.beust.jcommander.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/* loaded from: classes2.dex */
public class PositiveInteger implements IParameterValidator {
    @Override // com.beust.jcommander.IParameterValidator
    public void validate(String str, String str2) throws ParameterException {
        if (Integer.parseInt(str2) >= 0) {
            return;
        }
        throw new ParameterException("Parameter " + str + " should be positive (found " + str2 + ")");
    }
}
