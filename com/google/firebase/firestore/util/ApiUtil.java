package com.google.firebase.firestore.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes4.dex */
public class ApiUtil {
    public static AssertionError newAssertionError(String str, Throwable th) {
        AssertionError assertionError = new AssertionError(str);
        assertionError.initCause(th);
        return assertionError;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T newInstance(Constructor<T> constructor) {
        try {
            return constructor.newInstance(new Object[0]);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object invoke(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }
}
