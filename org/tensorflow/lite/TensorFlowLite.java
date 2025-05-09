package org.tensorflow.lite;

import androidx.media3.common.MimeTypes;
import java.lang.reflect.Constructor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import org.tensorflow.lite.InterpreterApi;

/* loaded from: classes6.dex */
public final class TensorFlowLite {
    private static final Throwable LOAD_LIBRARY_EXCEPTION;
    private static final String[][] TFLITE_RUNTIME_LIBNAMES;
    private static AtomicBoolean[] haveLogged;
    private static final Logger logger = Logger.getLogger(InterpreterApi.class.getName());
    private static volatile boolean isInit = false;

    private static native void nativeDoNothing();

    static {
        String[][] strArr = {new String[]{"tensorflowlite_jni", "tensorflowlite_jni_stable"}, new String[]{"tensorflowlite_jni_gms_client"}};
        TFLITE_RUNTIME_LIBNAMES = strArr;
        UnsatisfiedLinkError unsatisfiedLinkError = null;
        for (String[] strArr2 : strArr) {
            for (String str : strArr2) {
                try {
                    System.loadLibrary(str);
                    logger.info("Loaded native library: " + str);
                    break;
                } catch (UnsatisfiedLinkError e) {
                    logger.info("Didn't load native library: " + str);
                    if (unsatisfiedLinkError == null) {
                        unsatisfiedLinkError = e;
                    } else {
                        unsatisfiedLinkError.addSuppressed(e);
                    }
                }
            }
        }
        LOAD_LIBRARY_EXCEPTION = unsatisfiedLinkError;
        haveLogged = new AtomicBoolean[InterpreterApi.Options.TfLiteRuntime.values().length];
        for (int i = 0; i < InterpreterApi.Options.TfLiteRuntime.values().length; i++) {
            haveLogged[i] = new AtomicBoolean();
        }
    }

    private TensorFlowLite() {
    }

    @Deprecated
    public static String version() {
        return schemaVersion();
    }

    public static String runtimeVersion(InterpreterApi.Options.TfLiteRuntime runtime) {
        return getFactory(runtime, "org.tensorflow.lite.TensorFlowLite", "runtimeVersion").runtimeVersion();
    }

    public static String runtimeVersion() {
        return runtimeVersion(null);
    }

    public static String schemaVersion(InterpreterApi.Options.TfLiteRuntime runtime) {
        return getFactory(runtime, "org.tensorflow.lite.TensorFlowLite", "schemaVersion").schemaVersion();
    }

    public static String schemaVersion() {
        return schemaVersion(null);
    }

    public static void init() {
        if (isInit) {
            return;
        }
        try {
            nativeDoNothing();
            isInit = true;
        } catch (UnsatisfiedLinkError e) {
            Throwable th = LOAD_LIBRARY_EXCEPTION;
            if (th == null) {
                th = e;
            }
            UnsatisfiedLinkError unsatisfiedLinkError = new UnsatisfiedLinkError("Failed to load native TensorFlow Lite methods. Check that the correct native libraries are present, and, if using a custom native library, have been properly loaded via System.loadLibrary():\n  " + th);
            unsatisfiedLinkError.initCause(e);
            throw unsatisfiedLinkError;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class PossiblyAvailableRuntime {
        private final Exception exception;
        private final InterpreterFactoryApi factory;

        public PossiblyAvailableRuntime(String namespace, String category) {
            InterpreterFactoryApi interpreterFactoryApi;
            Exception e = null;
            try {
                Constructor<?> declaredConstructor = Class.forName(namespace + ".InterpreterFactoryImpl").getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                interpreterFactoryApi = (InterpreterFactoryApi) declaredConstructor.newInstance(new Object[0]);
            } catch (Exception e2) {
                interpreterFactoryApi = null;
                e = e2;
            }
            try {
                if (interpreterFactoryApi != null) {
                    TensorFlowLite.logger.info(String.format("Found %s TF Lite runtime client in %s", category, namespace));
                } else {
                    TensorFlowLite.logger.warning(String.format("Failed to construct TF Lite runtime client from %s", namespace));
                }
            } catch (Exception e3) {
                e = e3;
                TensorFlowLite.logger.info(String.format("Didn't find %s TF Lite runtime client in %s", category, namespace));
                this.exception = e;
                this.factory = interpreterFactoryApi;
            }
            this.exception = e;
            this.factory = interpreterFactoryApi;
        }

        public InterpreterFactoryApi getFactory() {
            return this.factory;
        }

        public Exception getException() {
            return this.exception;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class RuntimeFromSystem {
        static final PossiblyAvailableRuntime TFLITE = new PossiblyAvailableRuntime("com.google.android.gms.tflite", "system");

        private RuntimeFromSystem() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class RuntimeFromApplication {
        static final PossiblyAvailableRuntime TFLITE = new PossiblyAvailableRuntime("org.tensorflow.lite", MimeTypes.BASE_TYPE_APPLICATION);

        private RuntimeFromApplication() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InterpreterFactoryApi getFactory(InterpreterApi.Options.TfLiteRuntime runtime) {
        return getFactory(runtime, "org.tensorflow.lite.InterpreterApi.Options", "setRuntime");
    }

    static InterpreterFactoryApi getFactory(InterpreterApi.Options.TfLiteRuntime runtime, String className, String methodName) {
        Exception exception;
        String format;
        if (runtime == null) {
            runtime = InterpreterApi.Options.TfLiteRuntime.FROM_APPLICATION_ONLY;
        }
        if (runtime != InterpreterApi.Options.TfLiteRuntime.PREFER_SYSTEM_OVER_APPLICATION && runtime != InterpreterApi.Options.TfLiteRuntime.FROM_SYSTEM_ONLY) {
            exception = null;
        } else {
            if (RuntimeFromSystem.TFLITE.getFactory() != null) {
                if (!haveLogged[runtime.ordinal()].getAndSet(true)) {
                    logger.info(String.format("TfLiteRuntime.%s: Using system TF Lite runtime client from com.google.android.gms", runtime.name()));
                }
                return RuntimeFromSystem.TFLITE.getFactory();
            }
            exception = RuntimeFromSystem.TFLITE.getException();
        }
        if (runtime == InterpreterApi.Options.TfLiteRuntime.PREFER_SYSTEM_OVER_APPLICATION || runtime == InterpreterApi.Options.TfLiteRuntime.FROM_APPLICATION_ONLY) {
            if (RuntimeFromApplication.TFLITE.getFactory() != null) {
                if (!haveLogged[runtime.ordinal()].getAndSet(true)) {
                    logger.info(String.format("TfLiteRuntime.%s: Using application TF Lite runtime client from org.tensorflow.lite", runtime.name()));
                }
                return RuntimeFromApplication.TFLITE.getFactory();
            }
            if (exception == null) {
                exception = RuntimeFromApplication.TFLITE.getException();
            } else if (exception.getSuppressed().length == 0) {
                exception.addSuppressed(RuntimeFromApplication.TFLITE.getException());
            }
        }
        if (runtime == InterpreterApi.Options.TfLiteRuntime.FROM_APPLICATION_ONLY) {
            format = String.format("You should declare a build dependency on org.tensorflow.lite:tensorflow-lite, or call .%s with a value other than TfLiteRuntime.FROM_APPLICATION_ONLY (see docs for %s#%s(TfLiteRuntime)).", methodName, className, methodName);
        } else {
            format = runtime == InterpreterApi.Options.TfLiteRuntime.FROM_SYSTEM_ONLY ? String.format("You should declare a build dependency on com.google.android.gms:play-services-tflite-java, or call .%s with a value other than TfLiteRuntime.FROM_SYSTEM_ONLY  (see docs for %s#%s).", methodName, className, methodName) : "You should declare a build dependency on org.tensorflow.lite:tensorflow-lite or com.google.android.gms:play-services-tflite-java";
        }
        throw new IllegalStateException("Couldn't find TensorFlow Lite runtime's InterpreterFactoryImpl class -- make sure your app links in the right TensorFlow Lite runtime. " + format, exception);
    }
}
