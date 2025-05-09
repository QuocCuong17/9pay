package org.tensorflow.lite.support.common.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Objects;

/* loaded from: classes6.dex */
public final class SupportPreconditions {
    public static <T> T checkNotNull(T reference) {
        Objects.requireNonNull(reference, "The object reference is null.");
        return reference;
    }

    public static <T> T checkNotNull(T reference, Object errorMessage) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(String.valueOf(errorMessage));
    }

    public static String checkNotEmpty(String string) {
        if (string == null || string.length() == 0) {
            throw new IllegalArgumentException("Given String is empty or null.");
        }
        return string;
    }

    public static String checkNotEmpty(String string, Object errorMessage) {
        if (string == null || string.length() == 0) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
        return string;
    }

    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    public static int checkElementIndex(int index, int size) {
        return checkElementIndex(index, size, FirebaseAnalytics.Param.INDEX);
    }

    public static int checkElementIndex(int index, int size, String desc) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(badElementIndex(index, size, desc));
        }
        return index;
    }

    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }

    private static String badElementIndex(int index, int size, String desc) {
        if (index < 0) {
            return String.format("%s (%s) must not be negative", desc, Integer.valueOf(index));
        }
        if (size >= 0) {
            return String.format("%s (%s) must be less than size (%s)", desc, Integer.valueOf(index), Integer.valueOf(size));
        }
        throw new IllegalArgumentException("negative size: " + size);
    }

    private SupportPreconditions() {
        throw new AssertionError("SupportPreconditions is Uninstantiable.");
    }
}
