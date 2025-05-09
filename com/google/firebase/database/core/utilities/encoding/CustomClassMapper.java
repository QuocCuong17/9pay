package com.google.firebase.database.core.utilities.encoding;

import android.util.Log;
import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.core.utilities.Utilities;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes4.dex */
public class CustomClassMapper {
    private static final String LOG_TAG = "ClassMapper";
    private static final ConcurrentMap<Class<?>, BeanMapper<?>> mappers = new ConcurrentHashMap();

    public static Object convertToPlainJavaTypes(Object obj) {
        return serialize(obj);
    }

    public static Map<String, Object> convertToPlainJavaTypes(Map<String, Object> map) {
        Object serialize = serialize(map);
        Utilities.hardAssert(serialize instanceof Map);
        return (Map) serialize;
    }

    public static <T> T convertToCustomClass(Object obj, Class<T> cls) {
        return (T) deserializeToClass(obj, cls);
    }

    public static <T> T convertToCustomClass(Object obj, GenericTypeIndicator<T> genericTypeIndicator) {
        Type genericSuperclass = genericTypeIndicator.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            if (!parameterizedType.getRawType().equals(GenericTypeIndicator.class)) {
                throw new DatabaseException("Not a direct subclass of GenericTypeIndicator: " + genericSuperclass);
            }
            return (T) deserializeToType(obj, parameterizedType.getActualTypeArguments()[0]);
        }
        throw new DatabaseException("Not a direct subclass of GenericTypeIndicator: " + genericSuperclass);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> Object serialize(T t) {
        if (t == 0) {
            return null;
        }
        if (t instanceof Number) {
            if ((t instanceof Float) || (t instanceof Double)) {
                Number number = (Number) t;
                double doubleValue = number.doubleValue();
                if (doubleValue <= 9.223372036854776E18d && doubleValue >= -9.223372036854776E18d && Math.floor(doubleValue) == doubleValue) {
                    return Long.valueOf(number.longValue());
                }
                return Double.valueOf(doubleValue);
            }
            if ((t instanceof Long) || (t instanceof Integer)) {
                return t;
            }
            throw new DatabaseException(String.format("Numbers of type %s are not supported, please use an int, long, float or double", t.getClass().getSimpleName()));
        }
        if ((t instanceof String) || (t instanceof Boolean)) {
            return t;
        }
        if (t instanceof Character) {
            throw new DatabaseException("Characters are not supported, please use Strings");
        }
        if (t instanceof Map) {
            HashMap hashMap = new HashMap();
            for (Map.Entry entry : ((Map) t).entrySet()) {
                Object key = entry.getKey();
                if (key instanceof String) {
                    hashMap.put((String) key, serialize(entry.getValue()));
                } else {
                    throw new DatabaseException("Maps with non-string keys are not supported");
                }
            }
            return hashMap;
        }
        if (t instanceof Collection) {
            if (t instanceof List) {
                List list = (List) t;
                ArrayList arrayList = new ArrayList(list.size());
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(serialize(it.next()));
                }
                return arrayList;
            }
            throw new DatabaseException("Serializing Collections is not supported, please use Lists instead");
        }
        if (t.getClass().isArray()) {
            throw new DatabaseException("Serializing Arrays is not supported, please use Lists instead");
        }
        if (t instanceof Enum) {
            return ((Enum) t).name();
        }
        return loadOrCreateBeanMapperForClass(t.getClass()).serialize(t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T deserializeToType(Object obj, Type type) {
        if (obj == null) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            return (T) deserializeToParameterizedType(obj, (ParameterizedType) type);
        }
        if (type instanceof Class) {
            return (T) deserializeToClass(obj, (Class) type);
        }
        if (type instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type;
            if (wildcardType.getLowerBounds().length > 0) {
                throw new DatabaseException("Generic lower-bounded wildcard types are not supported");
            }
            Type[] upperBounds = wildcardType.getUpperBounds();
            Utilities.hardAssert(upperBounds.length > 0, "Wildcard type " + type + " is not upper bounded.");
            return (T) deserializeToType(obj, upperBounds[0]);
        }
        if (type instanceof TypeVariable) {
            Type[] bounds = ((TypeVariable) type).getBounds();
            Utilities.hardAssert(bounds.length > 0, "Wildcard type " + type + " is not upper bounded.");
            return (T) deserializeToType(obj, bounds[0]);
        }
        if (type instanceof GenericArrayType) {
            throw new DatabaseException("Generic Arrays are not supported, please use Lists instead");
        }
        throw new IllegalStateException("Unknown type encountered: " + type);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> T deserializeToClass(Object obj, Class<T> cls) {
        if (obj == 0) {
            return null;
        }
        if (cls.isPrimitive() || Number.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls) || Character.class.isAssignableFrom(cls)) {
            return (T) deserializeToPrimitive(obj, cls);
        }
        if (String.class.isAssignableFrom(cls)) {
            return (T) convertString(obj);
        }
        if (cls.isArray()) {
            throw new DatabaseException("Converting to Arrays is not supported, please use Listsinstead");
        }
        if (cls.getTypeParameters().length > 0) {
            throw new DatabaseException("Class " + cls.getName() + " has generic type parameters, please use GenericTypeIndicator instead");
        }
        if (cls.equals(Object.class)) {
            return obj;
        }
        if (cls.isEnum()) {
            return (T) deserializeToEnum(obj, cls);
        }
        return (T) convertBean(obj, cls);
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [T, java.util.HashMap] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.util.List, T, java.util.ArrayList] */
    private static <T> T deserializeToParameterizedType(Object obj, ParameterizedType parameterizedType) {
        Class cls = (Class) parameterizedType.getRawType();
        if (List.class.isAssignableFrom(cls)) {
            Type type = parameterizedType.getActualTypeArguments()[0];
            if (obj instanceof List) {
                List list = (List) obj;
                ?? r0 = (T) new ArrayList(list.size());
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    r0.add(deserializeToType(it.next(), type));
                }
                return r0;
            }
            throw new DatabaseException("Expected a List while deserializing, but got a " + obj.getClass());
        }
        if (Map.class.isAssignableFrom(cls)) {
            Type type2 = parameterizedType.getActualTypeArguments()[0];
            Type type3 = parameterizedType.getActualTypeArguments()[1];
            if (!type2.equals(String.class)) {
                throw new DatabaseException("Only Maps with string keys are supported, but found Map with key type " + type2);
            }
            Map<String, Object> expectMap = expectMap(obj);
            ?? r02 = (T) new HashMap();
            for (Map.Entry<String, Object> entry : expectMap.entrySet()) {
                r02.put(entry.getKey(), deserializeToType(entry.getValue(), type3));
            }
            return r02;
        }
        if (Collection.class.isAssignableFrom(cls)) {
            throw new DatabaseException("Collections are not supported, please use Lists instead");
        }
        Map<String, Object> expectMap2 = expectMap(obj);
        BeanMapper loadOrCreateBeanMapperForClass = loadOrCreateBeanMapperForClass(cls);
        HashMap hashMap = new HashMap();
        TypeVariable<Class<T>>[] typeParameters = loadOrCreateBeanMapperForClass.clazz.getTypeParameters();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (actualTypeArguments.length != typeParameters.length) {
            throw new IllegalStateException("Mismatched lengths for type variables and actual types");
        }
        for (int i = 0; i < typeParameters.length; i++) {
            hashMap.put(typeParameters[i], actualTypeArguments[i]);
        }
        return (T) loadOrCreateBeanMapperForClass.deserialize(expectMap2, hashMap);
    }

    private static <T> T deserializeToPrimitive(Object obj, Class<T> cls) {
        if (Integer.class.isAssignableFrom(cls) || Integer.TYPE.isAssignableFrom(cls)) {
            return (T) convertInteger(obj);
        }
        if (Boolean.class.isAssignableFrom(cls) || Boolean.TYPE.isAssignableFrom(cls)) {
            return (T) convertBoolean(obj);
        }
        if (Double.class.isAssignableFrom(cls) || Double.TYPE.isAssignableFrom(cls)) {
            return (T) convertDouble(obj);
        }
        if (Long.class.isAssignableFrom(cls) || Long.TYPE.isAssignableFrom(cls)) {
            return (T) convertLong(obj);
        }
        if (Float.class.isAssignableFrom(cls) || Float.TYPE.isAssignableFrom(cls)) {
            return (T) Float.valueOf(convertDouble(obj).floatValue());
        }
        throw new DatabaseException(String.format("Deserializing values to %s is not supported", cls.getSimpleName()));
    }

    private static <T> T deserializeToEnum(Object obj, Class<T> cls) {
        if (obj instanceof String) {
            String str = (String) obj;
            try {
                return (T) Enum.valueOf(cls, str);
            } catch (IllegalArgumentException unused) {
                throw new DatabaseException("Could not find enum value of " + cls.getName() + " for value \"" + str + "\"");
            }
        }
        throw new DatabaseException("Expected a String while deserializing to enum " + cls + " but got a " + obj.getClass());
    }

    private static <T> BeanMapper<T> loadOrCreateBeanMapperForClass(Class<T> cls) {
        ConcurrentMap<Class<?>, BeanMapper<?>> concurrentMap = mappers;
        BeanMapper<T> beanMapper = (BeanMapper) concurrentMap.get(cls);
        if (beanMapper != null) {
            return beanMapper;
        }
        BeanMapper<T> beanMapper2 = new BeanMapper<>(cls);
        concurrentMap.put(cls, beanMapper2);
        return beanMapper2;
    }

    private static Map<String, Object> expectMap(Object obj) {
        if (obj instanceof Map) {
            return (Map) obj;
        }
        throw new DatabaseException("Expected a Map while deserializing, but got a " + obj.getClass());
    }

    private static Integer convertInteger(Object obj) {
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            Number number = (Number) obj;
            double doubleValue = number.doubleValue();
            if (doubleValue >= -2.147483648E9d && doubleValue <= 2.147483647E9d) {
                return Integer.valueOf(number.intValue());
            }
            throw new DatabaseException("Numeric value out of 32-bit integer range: " + doubleValue + ". Did you mean to use a long or double instead of an int?");
        }
        throw new DatabaseException("Failed to convert a value of type " + obj.getClass().getName() + " to int");
    }

    private static Long convertLong(Object obj) {
        if (obj instanceof Integer) {
            return Long.valueOf(((Integer) obj).longValue());
        }
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            if (d.doubleValue() >= -9.223372036854776E18d && d.doubleValue() <= 9.223372036854776E18d) {
                return Long.valueOf(d.longValue());
            }
            throw new DatabaseException("Numeric value out of 64-bit long range: " + d + ". Did you mean to use a double instead of a long?");
        }
        throw new DatabaseException("Failed to convert a value of type " + obj.getClass().getName() + " to long");
    }

    private static Double convertDouble(Object obj) {
        if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        }
        if (obj instanceof Long) {
            Long l = (Long) obj;
            Double valueOf = Double.valueOf(l.doubleValue());
            if (valueOf.longValue() == l.longValue()) {
                return valueOf;
            }
            throw new DatabaseException("Loss of precision while converting number to double: " + obj + ". Did you mean to use a 64-bit long instead?");
        }
        if (obj instanceof Double) {
            return (Double) obj;
        }
        throw new DatabaseException("Failed to convert a value of type " + obj.getClass().getName() + " to double");
    }

    private static Boolean convertBoolean(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        throw new DatabaseException("Failed to convert value of type " + obj.getClass().getName() + " to boolean");
    }

    private static String convertString(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        throw new DatabaseException("Failed to convert value of type " + obj.getClass().getName() + " to String");
    }

    private static <T> T convertBean(Object obj, Class<T> cls) {
        BeanMapper loadOrCreateBeanMapperForClass = loadOrCreateBeanMapperForClass(cls);
        if (obj instanceof Map) {
            return (T) loadOrCreateBeanMapperForClass.deserialize(expectMap(obj));
        }
        throw new DatabaseException("Can't convert object of type " + obj.getClass().getName() + " to type " + cls.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class BeanMapper<T> {
        private final Class<T> clazz;
        private final Constructor<T> constructor;
        private final boolean throwOnUnknownProperties;
        private final boolean warnOnUnknownProperties;
        private final Map<String, String> properties = new HashMap();
        private final Map<String, Method> setters = new HashMap();
        private final Map<String, Method> getters = new HashMap();
        private final Map<String, Field> fields = new HashMap();

        public BeanMapper(Class<T> cls) {
            Constructor<T> constructor;
            this.clazz = cls;
            this.throwOnUnknownProperties = cls.isAnnotationPresent(ThrowOnExtraProperties.class);
            this.warnOnUnknownProperties = !cls.isAnnotationPresent(IgnoreExtraProperties.class);
            try {
                constructor = cls.getDeclaredConstructor(new Class[0]);
                constructor.setAccessible(true);
            } catch (NoSuchMethodException unused) {
                constructor = null;
            }
            this.constructor = constructor;
            for (Method method : cls.getMethods()) {
                if (shouldIncludeGetter(method)) {
                    String propertyName = propertyName(method);
                    addProperty(propertyName);
                    method.setAccessible(true);
                    if (this.getters.containsKey(propertyName)) {
                        throw new DatabaseException("Found conflicting getters for name: " + method.getName());
                    }
                    this.getters.put(propertyName, method);
                }
            }
            for (Field field : cls.getFields()) {
                if (shouldIncludeField(field)) {
                    addProperty(propertyName(field));
                }
            }
            Class<T> cls2 = cls;
            do {
                for (Method method2 : cls2.getDeclaredMethods()) {
                    if (shouldIncludeSetter(method2)) {
                        String propertyName2 = propertyName(method2);
                        String str = this.properties.get(propertyName2.toLowerCase(Locale.US));
                        if (str == null) {
                            continue;
                        } else {
                            if (!str.equals(propertyName2)) {
                                throw new DatabaseException("Found setter with invalid case-sensitive name: " + method2.getName());
                            }
                            Method method3 = this.setters.get(propertyName2);
                            if (method3 == null) {
                                method2.setAccessible(true);
                                this.setters.put(propertyName2, method2);
                            } else if (!isSetterOverride(method2, method3)) {
                                throw new DatabaseException("Found a conflicting setters with name: " + method2.getName() + " (conflicts with " + method3.getName() + " defined on " + method3.getDeclaringClass().getName() + ")");
                            }
                        }
                    }
                }
                for (Field field2 : cls2.getDeclaredFields()) {
                    String propertyName3 = propertyName(field2);
                    if (this.properties.containsKey(propertyName3.toLowerCase(Locale.US)) && !this.fields.containsKey(propertyName3)) {
                        field2.setAccessible(true);
                        this.fields.put(propertyName3, field2);
                    }
                }
                cls2 = cls2.getSuperclass();
                if (cls2 == null) {
                    break;
                }
            } while (!cls2.equals(Object.class));
            if (this.properties.isEmpty()) {
                throw new DatabaseException("No properties to serialize found on class " + cls.getName());
            }
        }

        private void addProperty(String str) {
            String put = this.properties.put(str.toLowerCase(Locale.US), str);
            if (put == null || str.equals(put)) {
                return;
            }
            throw new DatabaseException("Found two getters or fields with conflicting case sensitivity for property: " + str.toLowerCase(Locale.US));
        }

        public T deserialize(Map<String, Object> map) {
            return deserialize(map, Collections.emptyMap());
        }

        public T deserialize(Map<String, Object> map, Map<TypeVariable<Class<T>>, Type> map2) {
            Constructor<T> constructor = this.constructor;
            if (constructor == null) {
                throw new DatabaseException("Class " + this.clazz.getName() + " does not define a no-argument constructor. If you are using ProGuard, make sure these constructors are not stripped.");
            }
            try {
                T newInstance = constructor.newInstance(new Object[0]);
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();
                    if (this.setters.containsKey(key)) {
                        Method method = this.setters.get(key);
                        Type[] genericParameterTypes = method.getGenericParameterTypes();
                        if (genericParameterTypes.length != 1) {
                            throw new IllegalStateException("Setter does not have exactly one parameter");
                        }
                        try {
                            method.invoke(newInstance, CustomClassMapper.deserializeToType(entry.getValue(), resolveType(genericParameterTypes[0], map2)));
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e2) {
                            throw new RuntimeException(e2);
                        }
                    } else if (this.fields.containsKey(key)) {
                        Field field = this.fields.get(key);
                        try {
                            field.set(newInstance, CustomClassMapper.deserializeToType(entry.getValue(), resolveType(field.getGenericType(), map2)));
                        } catch (IllegalAccessException e3) {
                            throw new RuntimeException(e3);
                        }
                    } else {
                        String str = "No setter/field for " + key + " found on class " + this.clazz.getName();
                        if (this.properties.containsKey(key.toLowerCase(Locale.US))) {
                            str = str + " (fields/setters are case sensitive!)";
                        }
                        if (this.throwOnUnknownProperties) {
                            throw new DatabaseException(str);
                        }
                        if (this.warnOnUnknownProperties) {
                            Log.w(CustomClassMapper.LOG_TAG, str);
                        }
                    }
                }
                return newInstance;
            } catch (IllegalAccessException e4) {
                throw new RuntimeException(e4);
            } catch (InstantiationException e5) {
                throw new RuntimeException(e5);
            } catch (InvocationTargetException e6) {
                throw new RuntimeException(e6);
            }
        }

        private Type resolveType(Type type, Map<TypeVariable<Class<T>>, Type> map) {
            if (!(type instanceof TypeVariable)) {
                return type;
            }
            Type type2 = map.get(type);
            if (type2 != null) {
                return type2;
            }
            throw new IllegalStateException("Could not resolve type " + type);
        }

        public Map<String, Object> serialize(T t) {
            Object obj;
            if (!this.clazz.isAssignableFrom(t.getClass())) {
                throw new IllegalArgumentException("Can't serialize object of class " + t.getClass() + " with BeanMapper for class " + this.clazz);
            }
            HashMap hashMap = new HashMap();
            for (String str : this.properties.values()) {
                if (this.getters.containsKey(str)) {
                    try {
                        obj = this.getters.get(str).invoke(t, new Object[0]);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e2) {
                        throw new RuntimeException(e2);
                    }
                } else {
                    Field field = this.fields.get(str);
                    if (field == null) {
                        throw new IllegalStateException("Bean property without field or getter:" + str);
                    }
                    try {
                        obj = field.get(t);
                    } catch (IllegalAccessException e3) {
                        throw new RuntimeException(e3);
                    }
                }
                hashMap.put(str, CustomClassMapper.serialize(obj));
            }
            return hashMap;
        }

        private static boolean shouldIncludeGetter(Method method) {
            return ((!method.getName().startsWith(WorkflowRequestType.Method.GET) && !method.getName().startsWith("is")) || method.getDeclaringClass().equals(Object.class) || !Modifier.isPublic(method.getModifiers()) || Modifier.isStatic(method.getModifiers()) || method.getReturnType().equals(Void.TYPE) || method.getParameterTypes().length != 0 || method.isAnnotationPresent(Exclude.class)) ? false : true;
        }

        private static boolean shouldIncludeSetter(Method method) {
            return method.getName().startsWith("set") && !method.getDeclaringClass().equals(Object.class) && !Modifier.isStatic(method.getModifiers()) && method.getReturnType().equals(Void.TYPE) && method.getParameterTypes().length == 1 && !method.isAnnotationPresent(Exclude.class);
        }

        private static boolean shouldIncludeField(Field field) {
            return (field.getDeclaringClass().equals(Object.class) || !Modifier.isPublic(field.getModifiers()) || Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers()) || field.isAnnotationPresent(Exclude.class)) ? false : true;
        }

        private static boolean isSetterOverride(Method method, Method method2) {
            Utilities.hardAssert(method.getDeclaringClass().isAssignableFrom(method2.getDeclaringClass()), "Expected override from a base class");
            Utilities.hardAssert(method.getReturnType().equals(Void.TYPE), "Expected void return type");
            Utilities.hardAssert(method2.getReturnType().equals(Void.TYPE), "Expected void return type");
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?>[] parameterTypes2 = method2.getParameterTypes();
            Utilities.hardAssert(parameterTypes.length == 1, "Expected exactly one parameter");
            Utilities.hardAssert(parameterTypes2.length == 1, "Expected exactly one parameter");
            return method.getName().equals(method2.getName()) && parameterTypes[0].equals(parameterTypes2[0]);
        }

        private static String propertyName(Field field) {
            String annotatedName = annotatedName(field);
            return annotatedName != null ? annotatedName : field.getName();
        }

        private static String propertyName(Method method) {
            String annotatedName = annotatedName(method);
            return annotatedName != null ? annotatedName : serializedName(method.getName());
        }

        private static String annotatedName(AccessibleObject accessibleObject) {
            if (accessibleObject.isAnnotationPresent(PropertyName.class)) {
                return ((PropertyName) accessibleObject.getAnnotation(PropertyName.class)).value();
            }
            return null;
        }

        private static String serializedName(String str) {
            String[] strArr = {WorkflowRequestType.Method.GET, "set", "is"};
            String str2 = null;
            for (int i = 0; i < 3; i++) {
                String str3 = strArr[i];
                if (str.startsWith(str3)) {
                    str2 = str3;
                }
            }
            if (str2 == null) {
                throw new IllegalArgumentException("Unknown Bean prefix for method: " + str);
            }
            char[] charArray = str.substring(str2.length()).toCharArray();
            for (int i2 = 0; i2 < charArray.length && Character.isUpperCase(charArray[i2]); i2++) {
                charArray[i2] = Character.toLowerCase(charArray[i2]);
            }
            return new String(charArray);
        }
    }
}
