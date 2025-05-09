package com.it_nomads.fluttersecurestorage;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.state.TransactionState;
import com.it_nomads.fluttersecurestorage.FlutterSecureStoragePlugin;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes4.dex */
public class FlutterSecureStoragePlugin implements MethodChannel.MethodCallHandler, FlutterPlugin {
    private static final String TAG = "FlutterSecureStoragePl";
    private MethodChannel channel;
    private FlutterSecureStorage secureStorage;
    private HandlerThread workerThread;
    private Handler workerThreadHandler;

    public void initInstance(BinaryMessenger binaryMessenger, Context context) {
        try {
            this.secureStorage = new FlutterSecureStorage(context);
            HandlerThread handlerThread = new HandlerThread("com.it_nomads.fluttersecurestorage.worker");
            this.workerThread = handlerThread;
            handlerThread.start();
            this.workerThreadHandler = new Handler(this.workerThread.getLooper());
            MethodChannel methodChannel = new MethodChannel(binaryMessenger, "plugins.it_nomads.com/flutter_secure_storage");
            this.channel = methodChannel;
            methodChannel.setMethodCallHandler(this);
        } catch (Exception e) {
            Log.e(TAG, "Registration failed", e);
        }
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        initInstance(flutterPluginBinding.getBinaryMessenger(), flutterPluginBinding.getApplicationContext());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        if (this.channel != null) {
            this.workerThread.quitSafely();
            this.workerThread = null;
            this.channel.setMethodCallHandler(null);
            this.channel = null;
        }
        this.secureStorage = null;
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        this.workerThreadHandler.post(new MethodRunner(methodCall, new MethodResultWrapper(result)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getKeyFromCall(MethodCall methodCall) {
        return addPrefixToKey((String) ((Map) methodCall.arguments).get("key"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getValueFromCall(MethodCall methodCall) {
        return (String) ((Map) methodCall.arguments).get("value");
    }

    private String addPrefixToKey(String str) {
        return this.secureStorage.ELEMENT_PREFERENCES_KEY_PREFIX + "_" + str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class MethodResultWrapper implements MethodChannel.Result {
        private final Handler handler = new Handler(Looper.getMainLooper());
        private final MethodChannel.Result methodResult;

        MethodResultWrapper(MethodChannel.Result result) {
            this.methodResult = result;
        }

        /* renamed from: lambda$success$0$com-it_nomads-fluttersecurestorage-FlutterSecureStoragePlugin$MethodResultWrapper, reason: not valid java name */
        public /* synthetic */ void m931x9ec9761e(Object obj) {
            this.methodResult.success(obj);
        }

        @Override // io.flutter.plugin.common.MethodChannel.Result
        public void success(final Object obj) {
            this.handler.post(new Runnable() { // from class: com.it_nomads.fluttersecurestorage.FlutterSecureStoragePlugin$MethodResultWrapper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    FlutterSecureStoragePlugin.MethodResultWrapper.this.m931x9ec9761e(obj);
                }
            });
        }

        @Override // io.flutter.plugin.common.MethodChannel.Result
        public void error(final String str, final String str2, final Object obj) {
            this.handler.post(new Runnable() { // from class: com.it_nomads.fluttersecurestorage.FlutterSecureStoragePlugin$MethodResultWrapper$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    FlutterSecureStoragePlugin.MethodResultWrapper.this.m930xdfccd224(str, str2, obj);
                }
            });
        }

        /* renamed from: lambda$error$1$com-it_nomads-fluttersecurestorage-FlutterSecureStoragePlugin$MethodResultWrapper, reason: not valid java name */
        public /* synthetic */ void m930xdfccd224(String str, String str2, Object obj) {
            this.methodResult.error(str, str2, obj);
        }

        @Override // io.flutter.plugin.common.MethodChannel.Result
        public void notImplemented() {
            Handler handler = this.handler;
            final MethodChannel.Result result = this.methodResult;
            Objects.requireNonNull(result);
            handler.post(new Runnable() { // from class: com.it_nomads.fluttersecurestorage.FlutterSecureStoragePlugin$MethodResultWrapper$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    MethodChannel.Result.this.notImplemented();
                }
            });
        }
    }

    /* loaded from: classes4.dex */
    class MethodRunner implements Runnable {
        private final MethodCall call;
        private final MethodChannel.Result result;

        MethodRunner(MethodCall methodCall, MethodChannel.Result result) {
            this.call = methodCall;
            this.result = result;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            Exception e;
            char c = 0;
            try {
                try {
                    FlutterSecureStoragePlugin.this.secureStorage.options = (Map) ((Map) this.call.arguments).get("options");
                    z = FlutterSecureStoragePlugin.this.secureStorage.getResetOnError();
                } catch (FileNotFoundException e2) {
                    Log.i("Creating sharedPrefs", e2.getLocalizedMessage());
                    return;
                }
            } catch (Exception e3) {
                z = false;
                e = e3;
            }
            try {
                String str = this.call.method;
                switch (str.hashCode()) {
                    case -1335458389:
                        if (str.equals(TransactionState.ModuleData.ACTION_DELETE)) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case -358737930:
                        if (str.equals("deleteAll")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case 3496342:
                        if (str.equals("read")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 113399775:
                        if (str.equals("write")) {
                            break;
                        }
                        c = 65535;
                        break;
                    case 208013248:
                        if (str.equals("containsKey")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1080375339:
                        if (str.equals("readAll")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                if (c == 0) {
                    String keyFromCall = FlutterSecureStoragePlugin.this.getKeyFromCall(this.call);
                    String valueFromCall = FlutterSecureStoragePlugin.this.getValueFromCall(this.call);
                    if (valueFromCall != null) {
                        FlutterSecureStoragePlugin.this.secureStorage.write(keyFromCall, valueFromCall);
                        this.result.success(null);
                        return;
                    } else {
                        this.result.error("null", null, null);
                        return;
                    }
                }
                if (c == 1) {
                    String keyFromCall2 = FlutterSecureStoragePlugin.this.getKeyFromCall(this.call);
                    if (FlutterSecureStoragePlugin.this.secureStorage.containsKey(keyFromCall2)) {
                        this.result.success(FlutterSecureStoragePlugin.this.secureStorage.read(keyFromCall2));
                        return;
                    } else {
                        this.result.success(null);
                        return;
                    }
                }
                if (c == 2) {
                    this.result.success(FlutterSecureStoragePlugin.this.secureStorage.readAll());
                    return;
                }
                if (c == 3) {
                    this.result.success(Boolean.valueOf(FlutterSecureStoragePlugin.this.secureStorage.containsKey(FlutterSecureStoragePlugin.this.getKeyFromCall(this.call))));
                } else if (c == 4) {
                    FlutterSecureStoragePlugin.this.secureStorage.delete(FlutterSecureStoragePlugin.this.getKeyFromCall(this.call));
                    this.result.success(null);
                } else if (c == 5) {
                    FlutterSecureStoragePlugin.this.secureStorage.deleteAll();
                    this.result.success(null);
                } else {
                    this.result.notImplemented();
                }
            } catch (Exception e4) {
                e = e4;
                if (z) {
                    try {
                        FlutterSecureStoragePlugin.this.secureStorage.deleteAll();
                        this.result.success("Data has been reset");
                        return;
                    } catch (Exception e5) {
                        handleException(e5);
                        return;
                    }
                }
                handleException(e);
            }
        }

        private void handleException(Exception exc) {
            StringWriter stringWriter = new StringWriter();
            exc.printStackTrace(new PrintWriter(stringWriter));
            this.result.error("Exception encountered", this.call.method, stringWriter.toString());
        }
    }
}
