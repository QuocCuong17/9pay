package com.beust.jcommander;

import com.beust.jcommander.validators.NoValidator;
import com.beust.jcommander.validators.NoValueValidator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/* loaded from: classes2.dex */
public class ParameterDescription {
    private java.util.ResourceBundle m_bundle;
    private Object m_default;
    private String m_description;
    private DynamicParameter m_dynamicParameterAnnotation;
    private JCommander m_jCommander;
    private Object m_object;
    private Parameter m_parameterAnnotation;
    private Parameterized m_parameterized;
    private WrappedParameter m_wrappedParameter;
    private boolean m_assigned = false;
    private String m_longestName = "";

    public ParameterDescription(Object obj, DynamicParameter dynamicParameter, Parameterized parameterized, java.util.ResourceBundle resourceBundle, JCommander jCommander) {
        if (!Map.class.isAssignableFrom(parameterized.getType())) {
            throw new ParameterException("@DynamicParameter " + parameterized.getName() + " should be of type Map but is " + parameterized.getType().getName());
        }
        this.m_dynamicParameterAnnotation = dynamicParameter;
        this.m_wrappedParameter = new WrappedParameter(dynamicParameter);
        init(obj, parameterized, resourceBundle, jCommander);
    }

    public ParameterDescription(Object obj, Parameter parameter, Parameterized parameterized, java.util.ResourceBundle resourceBundle, JCommander jCommander) {
        this.m_parameterAnnotation = parameter;
        this.m_wrappedParameter = new WrappedParameter(parameter);
        init(obj, parameterized, resourceBundle, jCommander);
    }

    private java.util.ResourceBundle findResourceBundle(Object obj) {
        Parameters parameters = (Parameters) obj.getClass().getAnnotation(Parameters.class);
        if (parameters != null && !isEmpty(parameters.resourceBundle())) {
            return java.util.ResourceBundle.getBundle(parameters.resourceBundle(), Locale.getDefault());
        }
        ResourceBundle resourceBundle = (ResourceBundle) obj.getClass().getAnnotation(ResourceBundle.class);
        if (resourceBundle == null || isEmpty(resourceBundle.value())) {
            return null;
        }
        return java.util.ResourceBundle.getBundle(resourceBundle.value(), Locale.getDefault());
    }

    private boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    private void initDescription(String str, String str2, String[] strArr) {
        java.util.ResourceBundle resourceBundle;
        this.m_description = str;
        if (!"".equals(str2) && (resourceBundle = this.m_bundle) != null) {
            this.m_description = resourceBundle.getString(str2);
        }
        for (String str3 : strArr) {
            if (str3.length() > this.m_longestName.length()) {
                this.m_longestName = str3;
            }
        }
    }

    private void init(Object obj, Parameterized parameterized, java.util.ResourceBundle resourceBundle, JCommander jCommander) {
        Parameter parameter;
        String description;
        this.m_object = obj;
        this.m_parameterized = parameterized;
        this.m_bundle = resourceBundle;
        if (resourceBundle == null) {
            this.m_bundle = findResourceBundle(obj);
        }
        this.m_jCommander = jCommander;
        if (this.m_parameterAnnotation != null) {
            if (Enum.class.isAssignableFrom(parameterized.getType()) && this.m_parameterAnnotation.description().isEmpty()) {
                description = "Options: " + EnumSet.allOf(parameterized.getType());
            } else {
                description = this.m_parameterAnnotation.description();
            }
            initDescription(description, this.m_parameterAnnotation.descriptionKey(), this.m_parameterAnnotation.names());
        } else {
            DynamicParameter dynamicParameter = this.m_dynamicParameterAnnotation;
            if (dynamicParameter != null) {
                initDescription(dynamicParameter.description(), this.m_dynamicParameterAnnotation.descriptionKey(), this.m_dynamicParameterAnnotation.names());
            } else {
                throw new AssertionError("Shound never happen");
            }
        }
        try {
            this.m_default = parameterized.get(obj);
        } catch (Exception unused) {
        }
        if (this.m_default == null || (parameter = this.m_parameterAnnotation) == null) {
            return;
        }
        validateDefaultValues(parameter.names());
    }

    private void validateDefaultValues(String[] strArr) {
        validateValueParameter(strArr.length > 0 ? strArr[0] : "", this.m_default);
    }

    public String getLongestName() {
        return this.m_longestName;
    }

    public Object getDefault() {
        return this.m_default;
    }

    public String getDescription() {
        return this.m_description;
    }

    public Object getObject() {
        return this.m_object;
    }

    public String getNames() {
        StringBuilder sb = new StringBuilder();
        String[] names = this.m_wrappedParameter.names();
        for (int i = 0; i < names.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(names[i]);
        }
        return sb.toString();
    }

    public WrappedParameter getParameter() {
        return this.m_wrappedParameter;
    }

    public Parameterized getParameterized() {
        return this.m_parameterized;
    }

    private boolean isMultiOption() {
        Class<?> type = this.m_parameterized.getType();
        return type.equals(List.class) || type.equals(Set.class) || this.m_parameterized.isDynamicParameter();
    }

    public void addValue(String str) {
        addValue(str, false);
    }

    public boolean isAssigned() {
        return this.m_assigned;
    }

    public void setAssigned(boolean z) {
        this.m_assigned = z;
    }

    public void addValue(String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("Adding ");
        sb.append(z ? "default " : "");
        sb.append("value:");
        sb.append(str);
        sb.append(" to parameter:");
        sb.append(this.m_parameterized.getName());
        p(sb.toString());
        String str2 = this.m_wrappedParameter.names()[0];
        if ((this.m_assigned && !isMultiOption() && !this.m_jCommander.isParameterOverwritingAllowed()) || isNonOverwritableForced()) {
            throw new ParameterException("Can only specify option " + str2 + " once.");
        }
        validateParameter(str2, str);
        Class<?> type = this.m_parameterized.getType();
        Object convertValue = this.m_jCommander.convertValue(this, str);
        validateValueParameter(str2, convertValue);
        if (Collection.class.isAssignableFrom(type)) {
            Collection<Object> collection = (Collection) this.m_parameterized.get(this.m_object);
            if (collection == null || fieldIsSetForTheFirstTime(z)) {
                collection = newCollection(type);
                this.m_parameterized.set(this.m_object, collection);
            }
            if (convertValue instanceof Collection) {
                collection.addAll((Collection) convertValue);
            } else {
                collection.add(convertValue);
            }
        } else {
            this.m_wrappedParameter.addValue(this.m_parameterized, this.m_object, convertValue);
        }
        if (z) {
            return;
        }
        this.m_assigned = true;
    }

    private void validateParameter(String str, String str2) {
        Class<? extends IParameterValidator> validateWith = this.m_wrappedParameter.validateWith();
        if (validateWith != null) {
            validateParameter(this, validateWith, str, str2);
        }
    }

    private void validateValueParameter(String str, Object obj) {
        Class<? extends IValueValidator> validateValueWith = this.m_wrappedParameter.validateValueWith();
        if (validateValueWith != null) {
            validateValueParameter(validateValueWith, str, obj);
        }
    }

    public static void validateValueParameter(Class<? extends IValueValidator> cls, String str, Object obj) {
        if (cls != NoValueValidator.class) {
            try {
                p("Validating value parameter:" + str + " value:" + obj + " validator:" + cls);
            } catch (IllegalAccessException e) {
                throw new ParameterException("Can't instantiate validator:" + e);
            } catch (InstantiationException e2) {
                throw new ParameterException("Can't instantiate validator:" + e2);
            }
        }
        cls.newInstance().validate(str, obj);
    }

    public static void validateParameter(ParameterDescription parameterDescription, Class<? extends IParameterValidator> cls, String str, String str2) {
        if (cls != NoValidator.class) {
            try {
                p("Validating parameter:" + str + " value:" + str2 + " validator:" + cls);
            } catch (ParameterException e) {
                throw e;
            } catch (IllegalAccessException e2) {
                throw new ParameterException("Can't instantiate validator:" + e2);
            } catch (InstantiationException e3) {
                throw new ParameterException("Can't instantiate validator:" + e3);
            } catch (Exception e4) {
                throw new ParameterException(e4);
            }
        }
        cls.newInstance().validate(str, str2);
        if (IParameterValidator2.class.isAssignableFrom(cls)) {
            ((IParameterValidator2) cls.newInstance()).validate(str, str2, parameterDescription);
        }
    }

    private Collection<Object> newCollection(Class<?> cls) {
        if (SortedSet.class.isAssignableFrom(cls)) {
            return new TreeSet();
        }
        if (LinkedHashSet.class.isAssignableFrom(cls)) {
            return new LinkedHashSet();
        }
        if (Set.class.isAssignableFrom(cls)) {
            return new HashSet();
        }
        if (List.class.isAssignableFrom(cls)) {
            return new ArrayList();
        }
        throw new ParameterException("Parameters of Collection type '" + cls.getSimpleName() + "' are not supported. Please use List or Set instead.");
    }

    private boolean fieldIsSetForTheFirstTime(boolean z) {
        return (z || this.m_assigned) ? false : true;
    }

    private static void p(String str) {
        if (System.getProperty(JCommander.DEBUG_PROPERTY) != null) {
            JCommander.getConsole().println("[ParameterDescription] " + str);
        }
    }

    public String toString() {
        return "[ParameterDescription " + this.m_parameterized.getName() + "]";
    }

    public boolean isDynamicParameter() {
        return this.m_dynamicParameterAnnotation != null;
    }

    public boolean isHelp() {
        return this.m_wrappedParameter.isHelp();
    }

    public boolean isNonOverwritableForced() {
        return this.m_wrappedParameter.isNonOverwritableForced();
    }
}
