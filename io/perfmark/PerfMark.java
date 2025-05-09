package io.perfmark;

/* loaded from: classes5.dex */
public final class PerfMark {
    private static final Impl impl;

    public static void setEnabled(boolean z) {
        impl.setEnabled(z);
    }

    public static void startTask(String str, Tag tag) {
        impl.startTask(str, tag);
    }

    public static void startTask(String str) {
        impl.startTask(str);
    }

    public static <T> void startTask(T t, StringFunction<? super T> stringFunction) {
        impl.startTask((Impl) t, (StringFunction<? super Impl>) stringFunction);
    }

    public static void startTask(String str, String str2) {
        impl.startTask(str, str2);
    }

    public static TaskCloseable traceTask(String str) {
        impl.startTask(str);
        return TaskCloseable.INSTANCE;
    }

    public static <T> TaskCloseable traceTask(T t, StringFunction<? super T> stringFunction) {
        impl.startTask((Impl) t, (StringFunction<? super Impl>) stringFunction);
        return TaskCloseable.INSTANCE;
    }

    public static void event(String str, Tag tag) {
        impl.event(str, tag);
    }

    public static void event(String str) {
        impl.event(str);
    }

    public static void event(String str, String str2) {
        impl.event(str, str2);
    }

    public static void stopTask() {
        impl.stopTask();
    }

    public static void stopTask(String str, Tag tag) {
        impl.stopTask(str, tag);
    }

    public static void stopTask(String str) {
        impl.stopTask(str);
    }

    public static void stopTask(String str, String str2) {
        impl.stopTask(str, str2);
    }

    public static Tag createTag() {
        return Impl.NO_TAG;
    }

    public static Tag createTag(long j) {
        return impl.createTag("", j);
    }

    public static Tag createTag(String str) {
        return impl.createTag(str, Long.MIN_VALUE);
    }

    public static Tag createTag(String str, long j) {
        return impl.createTag(str, j);
    }

    @Deprecated
    public static Link link() {
        return Impl.NO_LINK;
    }

    public static Link linkOut() {
        return impl.linkOut();
    }

    public static void linkIn(Link link) {
        impl.linkIn(link);
    }

    public static void attachTag(Tag tag) {
        impl.attachTag(tag);
    }

    public static void attachTag(String str, String str2) {
        impl.attachTag(str, str2);
    }

    public static void attachTag(String str, long j) {
        impl.attachTag(str, j);
    }

    public static void attachTag(String str, long j, long j2) {
        impl.attachTag(str, j, j2);
    }

    public static <T> void attachTag(String str, T t, StringFunction<? super T> stringFunction) {
        impl.attachTag(str, (String) t, (StringFunction<? super String>) stringFunction);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0040 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0032  */
    static {
        Object obj;
        Class<?> cls;
        Impl impl2;
        try {
            cls = Class.forName("io.perfmark.impl.SecretPerfMarkImpl$PerfMarkImpl");
            obj = null;
        } catch (Throwable th) {
            obj = th;
            cls = null;
        }
        if (cls != null) {
            try {
                impl2 = (Impl) cls.asSubclass(Impl.class).getConstructor(Tag.class).newInstance(Impl.NO_TAG);
            } catch (Throwable th2) {
                obj = th2;
            }
            if (impl2 == null) {
                impl = impl2;
            } else {
                impl = new Impl(Impl.NO_TAG);
            }
            if (obj == null) {
                try {
                    if (Boolean.getBoolean("io.perfmark.PerfMark.debug")) {
                        Class<?> cls2 = Class.forName("java.util.logging.Logger");
                        Object invoke = cls2.getMethod("getLogger", String.class).invoke(null, PerfMark.class.getName());
                        Class<?> cls3 = Class.forName("java.util.logging.Level");
                        cls2.getMethod("log", cls3, String.class, Throwable.class).invoke(invoke, cls3.getField("FINE").get(null), "Error during PerfMark.<clinit>", obj);
                        return;
                    }
                    return;
                } catch (Throwable unused) {
                    return;
                }
            }
            return;
        }
        impl2 = null;
        if (impl2 == null) {
        }
        if (obj == null) {
        }
    }

    private PerfMark() {
    }
}
