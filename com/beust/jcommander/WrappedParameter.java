package com.beust.jcommander;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class WrappedParameter {
    private DynamicParameter m_dynamicParameter;
    private Parameter m_parameter;

    public WrappedParameter(Parameter parameter) {
        this.m_parameter = parameter;
    }

    public WrappedParameter(DynamicParameter dynamicParameter) {
        this.m_dynamicParameter = dynamicParameter;
    }

    public Parameter getParameter() {
        return this.m_parameter;
    }

    public DynamicParameter getDynamicParameter() {
        return this.m_dynamicParameter;
    }

    public int arity() {
        Parameter parameter = this.m_parameter;
        if (parameter != null) {
            return parameter.arity();
        }
        return 1;
    }

    public boolean hidden() {
        Parameter parameter = this.m_parameter;
        return parameter != null ? parameter.hidden() : this.m_dynamicParameter.hidden();
    }

    public boolean required() {
        Parameter parameter = this.m_parameter;
        return parameter != null ? parameter.required() : this.m_dynamicParameter.required();
    }

    public boolean password() {
        Parameter parameter = this.m_parameter;
        if (parameter != null) {
            return parameter.password();
        }
        return false;
    }

    public String[] names() {
        Parameter parameter = this.m_parameter;
        return parameter != null ? parameter.names() : this.m_dynamicParameter.names();
    }

    public boolean variableArity() {
        Parameter parameter = this.m_parameter;
        if (parameter != null) {
            return parameter.variableArity();
        }
        return false;
    }

    public Class<? extends IParameterValidator> validateWith() {
        Parameter parameter = this.m_parameter;
        return parameter != null ? parameter.validateWith() : this.m_dynamicParameter.validateWith();
    }

    public Class<? extends IValueValidator> validateValueWith() {
        Parameter parameter = this.m_parameter;
        return parameter != null ? parameter.validateValueWith() : this.m_dynamicParameter.validateValueWith();
    }

    public boolean echoInput() {
        Parameter parameter = this.m_parameter;
        if (parameter != null) {
            return parameter.echoInput();
        }
        return false;
    }

    public void addValue(Parameterized parameterized, Object obj, Object obj2) {
        if (this.m_parameter != null) {
            parameterized.set(obj, obj2);
            return;
        }
        String assignment = this.m_dynamicParameter.assignment();
        String obj3 = obj2.toString();
        int indexOf = obj3.indexOf(assignment);
        if (indexOf == -1) {
            throw new ParameterException("Dynamic parameter expected a value of the form a" + assignment + "b but got:" + obj3);
        }
        callPut(obj, parameterized, obj3.substring(0, indexOf), obj3.substring(indexOf + 1));
    }

    private void callPut(Object obj, Parameterized parameterized, String str, String str2) {
        try {
            findPut(parameterized.getType()).invoke(parameterized.get(obj), str, str2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (SecurityException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }

    private Method findPut(Class<?> cls) throws SecurityException, NoSuchMethodException {
        return cls.getMethod("put", Object.class, Object.class);
    }

    public String getAssignment() {
        DynamicParameter dynamicParameter = this.m_dynamicParameter;
        return dynamicParameter != null ? dynamicParameter.assignment() : "";
    }

    public boolean isHelp() {
        Parameter parameter = this.m_parameter;
        return parameter != null && parameter.help();
    }

    public boolean isNonOverwritableForced() {
        Parameter parameter = this.m_parameter;
        return parameter != null && parameter.forceNonOverwritable();
    }
}
