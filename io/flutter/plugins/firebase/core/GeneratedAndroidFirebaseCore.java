package io.flutter.plugins.firebase.core;

import android.util.Log;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class GeneratedAndroidFirebaseCore {

    /* loaded from: classes5.dex */
    public interface Result<T> {
        void error(Throwable th);

        void success(T t);
    }

    /* loaded from: classes5.dex */
    public static class FlutterError extends RuntimeException {
        public final String code;
        public final Object details;

        public FlutterError(String str, String str2, Object obj) {
            super(str2);
            this.code = str;
            this.details = obj;
        }
    }

    protected static ArrayList<Object> wrapError(Throwable th) {
        ArrayList<Object> arrayList = new ArrayList<>(3);
        if (th instanceof FlutterError) {
            FlutterError flutterError = (FlutterError) th;
            arrayList.add(flutterError.code);
            arrayList.add(flutterError.getMessage());
            arrayList.add(flutterError.details);
        } else {
            arrayList.add(th.toString());
            arrayList.add(th.getClass().getSimpleName());
            arrayList.add("Cause: " + th.getCause() + ", Stacktrace: " + Log.getStackTraceString(th));
        }
        return arrayList;
    }

    /* loaded from: classes5.dex */
    public static final class PigeonFirebaseOptions {
        private String androidClientId;
        private String apiKey;
        private String appGroupId;
        private String appId;
        private String authDomain;
        private String databaseURL;
        private String deepLinkURLScheme;
        private String iosBundleId;
        private String iosClientId;
        private String measurementId;
        private String messagingSenderId;
        private String projectId;
        private String storageBucket;
        private String trackingId;

        public String getApiKey() {
            return this.apiKey;
        }

        public void setApiKey(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"apiKey\" is null.");
            }
            this.apiKey = str;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"appId\" is null.");
            }
            this.appId = str;
        }

        public String getMessagingSenderId() {
            return this.messagingSenderId;
        }

        public void setMessagingSenderId(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"messagingSenderId\" is null.");
            }
            this.messagingSenderId = str;
        }

        public String getProjectId() {
            return this.projectId;
        }

        public void setProjectId(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"projectId\" is null.");
            }
            this.projectId = str;
        }

        public String getAuthDomain() {
            return this.authDomain;
        }

        public void setAuthDomain(String str) {
            this.authDomain = str;
        }

        public String getDatabaseURL() {
            return this.databaseURL;
        }

        public void setDatabaseURL(String str) {
            this.databaseURL = str;
        }

        public String getStorageBucket() {
            return this.storageBucket;
        }

        public void setStorageBucket(String str) {
            this.storageBucket = str;
        }

        public String getMeasurementId() {
            return this.measurementId;
        }

        public void setMeasurementId(String str) {
            this.measurementId = str;
        }

        public String getTrackingId() {
            return this.trackingId;
        }

        public void setTrackingId(String str) {
            this.trackingId = str;
        }

        public String getDeepLinkURLScheme() {
            return this.deepLinkURLScheme;
        }

        public void setDeepLinkURLScheme(String str) {
            this.deepLinkURLScheme = str;
        }

        public String getAndroidClientId() {
            return this.androidClientId;
        }

        public void setAndroidClientId(String str) {
            this.androidClientId = str;
        }

        public String getIosClientId() {
            return this.iosClientId;
        }

        public void setIosClientId(String str) {
            this.iosClientId = str;
        }

        public String getIosBundleId() {
            return this.iosBundleId;
        }

        public void setIosBundleId(String str) {
            this.iosBundleId = str;
        }

        public String getAppGroupId() {
            return this.appGroupId;
        }

        public void setAppGroupId(String str) {
            this.appGroupId = str;
        }

        PigeonFirebaseOptions() {
        }

        /* loaded from: classes5.dex */
        public static final class Builder {
            private String androidClientId;
            private String apiKey;
            private String appGroupId;
            private String appId;
            private String authDomain;
            private String databaseURL;
            private String deepLinkURLScheme;
            private String iosBundleId;
            private String iosClientId;
            private String measurementId;
            private String messagingSenderId;
            private String projectId;
            private String storageBucket;
            private String trackingId;

            public Builder setApiKey(String str) {
                this.apiKey = str;
                return this;
            }

            public Builder setAppId(String str) {
                this.appId = str;
                return this;
            }

            public Builder setMessagingSenderId(String str) {
                this.messagingSenderId = str;
                return this;
            }

            public Builder setProjectId(String str) {
                this.projectId = str;
                return this;
            }

            public Builder setAuthDomain(String str) {
                this.authDomain = str;
                return this;
            }

            public Builder setDatabaseURL(String str) {
                this.databaseURL = str;
                return this;
            }

            public Builder setStorageBucket(String str) {
                this.storageBucket = str;
                return this;
            }

            public Builder setMeasurementId(String str) {
                this.measurementId = str;
                return this;
            }

            public Builder setTrackingId(String str) {
                this.trackingId = str;
                return this;
            }

            public Builder setDeepLinkURLScheme(String str) {
                this.deepLinkURLScheme = str;
                return this;
            }

            public Builder setAndroidClientId(String str) {
                this.androidClientId = str;
                return this;
            }

            public Builder setIosClientId(String str) {
                this.iosClientId = str;
                return this;
            }

            public Builder setIosBundleId(String str) {
                this.iosBundleId = str;
                return this;
            }

            public Builder setAppGroupId(String str) {
                this.appGroupId = str;
                return this;
            }

            public PigeonFirebaseOptions build() {
                PigeonFirebaseOptions pigeonFirebaseOptions = new PigeonFirebaseOptions();
                pigeonFirebaseOptions.setApiKey(this.apiKey);
                pigeonFirebaseOptions.setAppId(this.appId);
                pigeonFirebaseOptions.setMessagingSenderId(this.messagingSenderId);
                pigeonFirebaseOptions.setProjectId(this.projectId);
                pigeonFirebaseOptions.setAuthDomain(this.authDomain);
                pigeonFirebaseOptions.setDatabaseURL(this.databaseURL);
                pigeonFirebaseOptions.setStorageBucket(this.storageBucket);
                pigeonFirebaseOptions.setMeasurementId(this.measurementId);
                pigeonFirebaseOptions.setTrackingId(this.trackingId);
                pigeonFirebaseOptions.setDeepLinkURLScheme(this.deepLinkURLScheme);
                pigeonFirebaseOptions.setAndroidClientId(this.androidClientId);
                pigeonFirebaseOptions.setIosClientId(this.iosClientId);
                pigeonFirebaseOptions.setIosBundleId(this.iosBundleId);
                pigeonFirebaseOptions.setAppGroupId(this.appGroupId);
                return pigeonFirebaseOptions;
            }
        }

        public ArrayList<Object> toList() {
            ArrayList<Object> arrayList = new ArrayList<>(14);
            arrayList.add(this.apiKey);
            arrayList.add(this.appId);
            arrayList.add(this.messagingSenderId);
            arrayList.add(this.projectId);
            arrayList.add(this.authDomain);
            arrayList.add(this.databaseURL);
            arrayList.add(this.storageBucket);
            arrayList.add(this.measurementId);
            arrayList.add(this.trackingId);
            arrayList.add(this.deepLinkURLScheme);
            arrayList.add(this.androidClientId);
            arrayList.add(this.iosClientId);
            arrayList.add(this.iosBundleId);
            arrayList.add(this.appGroupId);
            return arrayList;
        }

        static PigeonFirebaseOptions fromList(ArrayList<Object> arrayList) {
            PigeonFirebaseOptions pigeonFirebaseOptions = new PigeonFirebaseOptions();
            pigeonFirebaseOptions.setApiKey((String) arrayList.get(0));
            pigeonFirebaseOptions.setAppId((String) arrayList.get(1));
            pigeonFirebaseOptions.setMessagingSenderId((String) arrayList.get(2));
            pigeonFirebaseOptions.setProjectId((String) arrayList.get(3));
            pigeonFirebaseOptions.setAuthDomain((String) arrayList.get(4));
            pigeonFirebaseOptions.setDatabaseURL((String) arrayList.get(5));
            pigeonFirebaseOptions.setStorageBucket((String) arrayList.get(6));
            pigeonFirebaseOptions.setMeasurementId((String) arrayList.get(7));
            pigeonFirebaseOptions.setTrackingId((String) arrayList.get(8));
            pigeonFirebaseOptions.setDeepLinkURLScheme((String) arrayList.get(9));
            pigeonFirebaseOptions.setAndroidClientId((String) arrayList.get(10));
            pigeonFirebaseOptions.setIosClientId((String) arrayList.get(11));
            pigeonFirebaseOptions.setIosBundleId((String) arrayList.get(12));
            pigeonFirebaseOptions.setAppGroupId((String) arrayList.get(13));
            return pigeonFirebaseOptions;
        }
    }

    /* loaded from: classes5.dex */
    public static final class PigeonInitializeResponse {
        private Boolean isAutomaticDataCollectionEnabled;
        private String name;
        private PigeonFirebaseOptions options;
        private Map<String, Object> pluginConstants;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"name\" is null.");
            }
            this.name = str;
        }

        public PigeonFirebaseOptions getOptions() {
            return this.options;
        }

        public void setOptions(PigeonFirebaseOptions pigeonFirebaseOptions) {
            if (pigeonFirebaseOptions == null) {
                throw new IllegalStateException("Nonnull field \"options\" is null.");
            }
            this.options = pigeonFirebaseOptions;
        }

        public Boolean getIsAutomaticDataCollectionEnabled() {
            return this.isAutomaticDataCollectionEnabled;
        }

        public void setIsAutomaticDataCollectionEnabled(Boolean bool) {
            this.isAutomaticDataCollectionEnabled = bool;
        }

        public Map<String, Object> getPluginConstants() {
            return this.pluginConstants;
        }

        public void setPluginConstants(Map<String, Object> map) {
            if (map == null) {
                throw new IllegalStateException("Nonnull field \"pluginConstants\" is null.");
            }
            this.pluginConstants = map;
        }

        PigeonInitializeResponse() {
        }

        /* loaded from: classes5.dex */
        public static final class Builder {
            private Boolean isAutomaticDataCollectionEnabled;
            private String name;
            private PigeonFirebaseOptions options;
            private Map<String, Object> pluginConstants;

            public Builder setName(String str) {
                this.name = str;
                return this;
            }

            public Builder setOptions(PigeonFirebaseOptions pigeonFirebaseOptions) {
                this.options = pigeonFirebaseOptions;
                return this;
            }

            public Builder setIsAutomaticDataCollectionEnabled(Boolean bool) {
                this.isAutomaticDataCollectionEnabled = bool;
                return this;
            }

            public Builder setPluginConstants(Map<String, Object> map) {
                this.pluginConstants = map;
                return this;
            }

            public PigeonInitializeResponse build() {
                PigeonInitializeResponse pigeonInitializeResponse = new PigeonInitializeResponse();
                pigeonInitializeResponse.setName(this.name);
                pigeonInitializeResponse.setOptions(this.options);
                pigeonInitializeResponse.setIsAutomaticDataCollectionEnabled(this.isAutomaticDataCollectionEnabled);
                pigeonInitializeResponse.setPluginConstants(this.pluginConstants);
                return pigeonInitializeResponse;
            }
        }

        public ArrayList<Object> toList() {
            ArrayList<Object> arrayList = new ArrayList<>(4);
            arrayList.add(this.name);
            PigeonFirebaseOptions pigeonFirebaseOptions = this.options;
            arrayList.add(pigeonFirebaseOptions == null ? null : pigeonFirebaseOptions.toList());
            arrayList.add(this.isAutomaticDataCollectionEnabled);
            arrayList.add(this.pluginConstants);
            return arrayList;
        }

        static PigeonInitializeResponse fromList(ArrayList<Object> arrayList) {
            PigeonInitializeResponse pigeonInitializeResponse = new PigeonInitializeResponse();
            pigeonInitializeResponse.setName((String) arrayList.get(0));
            Object obj = arrayList.get(1);
            pigeonInitializeResponse.setOptions(obj == null ? null : PigeonFirebaseOptions.fromList((ArrayList) obj));
            pigeonInitializeResponse.setIsAutomaticDataCollectionEnabled((Boolean) arrayList.get(2));
            pigeonInitializeResponse.setPluginConstants((Map) arrayList.get(3));
            return pigeonInitializeResponse;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class FirebaseCoreHostApiCodec extends StandardMessageCodec {
        public static final FirebaseCoreHostApiCodec INSTANCE = new FirebaseCoreHostApiCodec();

        private FirebaseCoreHostApiCodec() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public Object readValueOfType(byte b, ByteBuffer byteBuffer) {
            if (b == Byte.MIN_VALUE) {
                return PigeonFirebaseOptions.fromList((ArrayList) readValue(byteBuffer));
            }
            if (b == -127) {
                return PigeonInitializeResponse.fromList((ArrayList) readValue(byteBuffer));
            }
            return super.readValueOfType(b, byteBuffer);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public void writeValue(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (obj instanceof PigeonFirebaseOptions) {
                byteArrayOutputStream.write(128);
                writeValue(byteArrayOutputStream, ((PigeonFirebaseOptions) obj).toList());
            } else if (obj instanceof PigeonInitializeResponse) {
                byteArrayOutputStream.write(129);
                writeValue(byteArrayOutputStream, ((PigeonInitializeResponse) obj).toList());
            } else {
                super.writeValue(byteArrayOutputStream, obj);
            }
        }
    }

    /* loaded from: classes5.dex */
    public interface FirebaseCoreHostApi {
        void initializeApp(String str, PigeonFirebaseOptions pigeonFirebaseOptions, Result<PigeonInitializeResponse> result);

        void initializeCore(Result<List<PigeonInitializeResponse>> result);

        void optionsFromResource(Result<PigeonFirebaseOptions> result);

        /* renamed from: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore$FirebaseCoreHostApi$-CC, reason: invalid class name */
        /* loaded from: classes5.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return FirebaseCoreHostApiCodec.INSTANCE;
            }

            public static void setup(BinaryMessenger binaryMessenger, final FirebaseCoreHostApi firebaseCoreHostApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.FirebaseCoreHostApi.initializeApp", getCodec());
                if (firebaseCoreHostApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore$FirebaseCoreHostApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.CC.lambda$setup$0(GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel2 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.FirebaseCoreHostApi.initializeCore", getCodec());
                if (firebaseCoreHostApi != null) {
                    basicMessageChannel2.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore$FirebaseCoreHostApi$$ExternalSyntheticLambda1
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.CC.lambda$setup$1(GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel2.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel3 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.FirebaseCoreHostApi.optionsFromResource", getCodec());
                if (firebaseCoreHostApi != null) {
                    basicMessageChannel3.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore$FirebaseCoreHostApi$$ExternalSyntheticLambda2
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.CC.lambda$setup$2(GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel3.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(FirebaseCoreHostApi firebaseCoreHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = (ArrayList) obj;
                firebaseCoreHostApi.initializeApp((String) arrayList2.get(0), (PigeonFirebaseOptions) arrayList2.get(1), new Result<PigeonInitializeResponse>() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.1
                    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                    public void success(PigeonInitializeResponse pigeonInitializeResponse) {
                        arrayList.add(0, pigeonInitializeResponse);
                        reply.reply(arrayList);
                    }

                    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                    public void error(Throwable th) {
                        reply.reply(GeneratedAndroidFirebaseCore.wrapError(th));
                    }
                });
            }

            public static /* synthetic */ void lambda$setup$1(FirebaseCoreHostApi firebaseCoreHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final ArrayList arrayList = new ArrayList();
                firebaseCoreHostApi.initializeCore(new Result<List<PigeonInitializeResponse>>() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.2
                    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                    public void success(List<PigeonInitializeResponse> list) {
                        arrayList.add(0, list);
                        reply.reply(arrayList);
                    }

                    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                    public void error(Throwable th) {
                        reply.reply(GeneratedAndroidFirebaseCore.wrapError(th));
                    }
                });
            }

            public static /* synthetic */ void lambda$setup$2(FirebaseCoreHostApi firebaseCoreHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final ArrayList arrayList = new ArrayList();
                firebaseCoreHostApi.optionsFromResource(new Result<PigeonFirebaseOptions>() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.3
                    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                    public void success(PigeonFirebaseOptions pigeonFirebaseOptions) {
                        arrayList.add(0, pigeonFirebaseOptions);
                        reply.reply(arrayList);
                    }

                    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                    public void error(Throwable th) {
                        reply.reply(GeneratedAndroidFirebaseCore.wrapError(th));
                    }
                });
            }
        }
    }

    /* loaded from: classes5.dex */
    public interface FirebaseAppHostApi {
        void delete(String str, Result<Void> result);

        void setAutomaticDataCollectionEnabled(String str, Boolean bool, Result<Void> result);

        void setAutomaticResourceManagementEnabled(String str, Boolean bool, Result<Void> result);

        /* renamed from: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore$FirebaseAppHostApi$-CC, reason: invalid class name */
        /* loaded from: classes5.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return new StandardMessageCodec();
            }

            public static void setup(BinaryMessenger binaryMessenger, final FirebaseAppHostApi firebaseAppHostApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.FirebaseAppHostApi.setAutomaticDataCollectionEnabled", getCodec());
                if (firebaseAppHostApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore$FirebaseAppHostApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseCore.FirebaseAppHostApi.CC.lambda$setup$0(GeneratedAndroidFirebaseCore.FirebaseAppHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel2 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.FirebaseAppHostApi.setAutomaticResourceManagementEnabled", getCodec());
                if (firebaseAppHostApi != null) {
                    basicMessageChannel2.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore$FirebaseAppHostApi$$ExternalSyntheticLambda1
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseCore.FirebaseAppHostApi.CC.lambda$setup$1(GeneratedAndroidFirebaseCore.FirebaseAppHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel2.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel3 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.FirebaseAppHostApi.delete", getCodec());
                if (firebaseAppHostApi != null) {
                    basicMessageChannel3.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore$FirebaseAppHostApi$$ExternalSyntheticLambda2
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseCore.FirebaseAppHostApi.CC.lambda$setup$2(GeneratedAndroidFirebaseCore.FirebaseAppHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel3.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(FirebaseAppHostApi firebaseAppHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = (ArrayList) obj;
                firebaseAppHostApi.setAutomaticDataCollectionEnabled((String) arrayList2.get(0), (Boolean) arrayList2.get(1), new Result<Void>() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseAppHostApi.1
                    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                    public void success(Void r3) {
                        arrayList.add(0, null);
                        reply.reply(arrayList);
                    }

                    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                    public void error(Throwable th) {
                        reply.reply(GeneratedAndroidFirebaseCore.wrapError(th));
                    }
                });
            }

            public static /* synthetic */ void lambda$setup$1(FirebaseAppHostApi firebaseAppHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = (ArrayList) obj;
                firebaseAppHostApi.setAutomaticResourceManagementEnabled((String) arrayList2.get(0), (Boolean) arrayList2.get(1), new Result<Void>() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseAppHostApi.2
                    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                    public void success(Void r3) {
                        arrayList.add(0, null);
                        reply.reply(arrayList);
                    }

                    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                    public void error(Throwable th) {
                        reply.reply(GeneratedAndroidFirebaseCore.wrapError(th));
                    }
                });
            }

            public static /* synthetic */ void lambda$setup$2(FirebaseAppHostApi firebaseAppHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final ArrayList arrayList = new ArrayList();
                firebaseAppHostApi.delete((String) ((ArrayList) obj).get(0), new Result<Void>() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseAppHostApi.3
                    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                    public void success(Void r3) {
                        arrayList.add(0, null);
                        reply.reply(arrayList);
                    }

                    @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                    public void error(Throwable th) {
                        reply.reply(GeneratedAndroidFirebaseCore.wrapError(th));
                    }
                });
            }
        }
    }
}
