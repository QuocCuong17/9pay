package com.dexterous.flutterlocalnotifications.utils;

/* loaded from: classes2.dex */
public class LongUtils {
    public static Long parseLong(Object obj) {
        if (obj instanceof Integer) {
            return Long.valueOf(((Integer) obj).longValue());
        }
        if (obj instanceof Long) {
            return (Long) obj;
        }
        return null;
    }
}
