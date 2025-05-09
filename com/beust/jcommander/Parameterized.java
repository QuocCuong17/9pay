package com.beust.jcommander;

import com.beust.jcommander.internal.Lists;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/* loaded from: classes2.dex */
public class Parameterized {
    private Field m_field;
    private Method m_getter;
    private Method m_method;
    private ParametersDelegate m_parametersDelegate;
    private WrappedParameter m_wrappedParameter;

    public Parameterized(WrappedParameter wrappedParameter, ParametersDelegate parametersDelegate, Field field, Method method) {
        this.m_wrappedParameter = wrappedParameter;
        this.m_method = method;
        this.m_field = field;
        if (field != null) {
            field.setAccessible(true);
        }
        this.m_parametersDelegate = parametersDelegate;
    }

    public static List<Parameterized> parseArg(Object obj) {
        List<Parameterized> newArrayList = Lists.newArrayList();
        Class<?> cls = obj.getClass();
        while (true) {
            if (Object.class.equals(cls)) {
                break;
            }
            for (Field field : cls.getDeclaredFields()) {
                Annotation annotation = field.getAnnotation(Parameter.class);
                Annotation annotation2 = field.getAnnotation(ParametersDelegate.class);
                Annotation annotation3 = field.getAnnotation(DynamicParameter.class);
                if (annotation != null) {
                    newArrayList.add(new Parameterized(new WrappedParameter((Parameter) annotation), null, field, null));
                } else if (annotation3 != null) {
                    newArrayList.add(new Parameterized(new WrappedParameter((DynamicParameter) annotation3), null, field, null));
                } else if (annotation2 != null) {
                    newArrayList.add(new Parameterized(null, (ParametersDelegate) annotation2, field, null));
                }
            }
            cls = cls.getSuperclass();
        }
        for (Class<?> cls2 = obj.getClass(); !Object.class.equals(cls2); cls2 = cls2.getSuperclass()) {
            for (Method method : cls2.getDeclaredMethods()) {
                Annotation annotation4 = method.getAnnotation(Parameter.class);
                Annotation annotation5 = method.getAnnotation(ParametersDelegate.class);
                Annotation annotation6 = method.getAnnotation(DynamicParameter.class);
                if (annotation4 != null) {
                    newArrayList.add(new Parameterized(new WrappedParameter((Parameter) annotation4), null, null, method));
                } else if (annotation6 != null) {
                    newArrayList.add(new Parameterized(new WrappedParameter((DynamicParameter) annotation4), null, null, method));
                } else if (annotation5 != null) {
                    newArrayList.add(new Parameterized(null, (ParametersDelegate) annotation5, null, method));
                }
            }
        }
        return newArrayList;
    }

    public WrappedParameter getWrappedParameter() {
        return this.m_wrappedParameter;
    }

    public Class<?> getType() {
        Method method = this.m_method;
        if (method != null) {
            return method.getParameterTypes()[0];
        }
        return this.m_field.getType();
    }

    public String getName() {
        Method method = this.m_method;
        if (method != null) {
            return method.getName();
        }
        return this.m_field.getName();
    }

    public Object get(Object obj) {
        try {
            Method method = this.m_method;
            if (method != null) {
                if (this.m_getter == null) {
                    this.m_getter = method.getDeclaringClass().getMethod("g" + this.m_method.getName().substring(1), new Class[0]);
                }
                return this.m_getter.invoke(obj, new Object[0]);
            }
            return this.m_field.get(obj);
        } catch (IllegalAccessException e) {
            throw new ParameterException(e);
        } catch (IllegalArgumentException e2) {
            throw new ParameterException(e2);
        } catch (NoSuchMethodException unused) {
            String name = this.m_method.getName();
            try {
                Field declaredField = this.m_method.getDeclaringClass().getDeclaredField(Character.toLowerCase(name.charAt(3)) + name.substring(4));
                if (declaredField == null) {
                    return null;
                }
                declaredField.setAccessible(true);
                return declaredField.get(obj);
            } catch (IllegalAccessException | NoSuchFieldException unused2) {
                return null;
            }
        } catch (SecurityException e3) {
            throw new ParameterException(e3);
        } catch (InvocationTargetException e4) {
            throw new ParameterException(e4);
        }
    }

    public int hashCode() {
        Field field = this.m_field;
        int hashCode = ((field == null ? 0 : field.hashCode()) + 31) * 31;
        Method method = this.m_method;
        return hashCode + (method != null ? method.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Parameterized parameterized = (Parameterized) obj;
        Field field = this.m_field;
        if (field == null) {
            if (parameterized.m_field != null) {
                return false;
            }
        } else if (!field.equals(parameterized.m_field)) {
            return false;
        }
        Method method = this.m_method;
        if (method == null) {
            if (parameterized.m_method != null) {
                return false;
            }
        } else if (!method.equals(parameterized.m_method)) {
            return false;
        }
        return true;
    }

    public boolean isDynamicParameter(Field field) {
        Method method = this.m_method;
        return method != null ? method.getAnnotation(DynamicParameter.class) != null : this.m_field.getAnnotation(DynamicParameter.class) != null;
    }

    public void set(Object obj, Object obj2) {
        try {
            Method method = this.m_method;
            if (method != null) {
                method.invoke(obj, obj2);
            } else {
                this.m_field.set(obj, obj2);
            }
        } catch (IllegalAccessException e) {
            throw new ParameterException(e);
        } catch (IllegalArgumentException e2) {
            throw new ParameterException(e2);
        } catch (InvocationTargetException e3) {
            if (e3.getTargetException() instanceof ParameterException) {
                throw ((ParameterException) e3.getTargetException());
            }
            throw new ParameterException(e3);
        }
    }

    public ParametersDelegate getDelegateAnnotation() {
        return this.m_parametersDelegate;
    }

    public Type getGenericType() {
        Method method = this.m_method;
        if (method != null) {
            return method.getGenericParameterTypes()[0];
        }
        return this.m_field.getGenericType();
    }

    public Parameter getParameter() {
        return this.m_wrappedParameter.getParameter();
    }

    public Type findFieldGenericType() {
        if (this.m_method == null && (this.m_field.getGenericType() instanceof ParameterizedType)) {
            Type type = ((ParameterizedType) this.m_field.getGenericType()).getActualTypeArguments()[0];
            if (type instanceof Class) {
                return type;
            }
        }
        return null;
    }

    public boolean isDynamicParameter() {
        return this.m_wrappedParameter.getDynamicParameter() != null;
    }
}
