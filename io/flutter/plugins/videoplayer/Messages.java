package io.flutter.plugins.videoplayer;

import android.util.Log;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugins.videoplayer.Messages;
import java.io.ByteArrayOutputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
public class Messages {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes5.dex */
    @interface CanIgnoreReturnValue {
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
    public static final class CreateMessage {
        private String asset;
        private String formatHint;
        private Map<String, String> httpHeaders;
        private String packageName;
        private String uri;

        public String getAsset() {
            return this.asset;
        }

        public void setAsset(String str) {
            this.asset = str;
        }

        public String getUri() {
            return this.uri;
        }

        public void setUri(String str) {
            this.uri = str;
        }

        public String getPackageName() {
            return this.packageName;
        }

        public void setPackageName(String str) {
            this.packageName = str;
        }

        public String getFormatHint() {
            return this.formatHint;
        }

        public void setFormatHint(String str) {
            this.formatHint = str;
        }

        public Map<String, String> getHttpHeaders() {
            return this.httpHeaders;
        }

        public void setHttpHeaders(Map<String, String> map) {
            if (map == null) {
                throw new IllegalStateException("Nonnull field \"httpHeaders\" is null.");
            }
            this.httpHeaders = map;
        }

        CreateMessage() {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            CreateMessage createMessage = (CreateMessage) obj;
            return Objects.equals(this.asset, createMessage.asset) && Objects.equals(this.uri, createMessage.uri) && Objects.equals(this.packageName, createMessage.packageName) && Objects.equals(this.formatHint, createMessage.formatHint) && this.httpHeaders.equals(createMessage.httpHeaders);
        }

        public int hashCode() {
            return Objects.hash(this.asset, this.uri, this.packageName, this.formatHint, this.httpHeaders);
        }

        /* loaded from: classes5.dex */
        public static final class Builder {
            private String asset;
            private String formatHint;
            private Map<String, String> httpHeaders;
            private String packageName;
            private String uri;

            public Builder setAsset(String str) {
                this.asset = str;
                return this;
            }

            public Builder setUri(String str) {
                this.uri = str;
                return this;
            }

            public Builder setPackageName(String str) {
                this.packageName = str;
                return this;
            }

            public Builder setFormatHint(String str) {
                this.formatHint = str;
                return this;
            }

            public Builder setHttpHeaders(Map<String, String> map) {
                this.httpHeaders = map;
                return this;
            }

            public CreateMessage build() {
                CreateMessage createMessage = new CreateMessage();
                createMessage.setAsset(this.asset);
                createMessage.setUri(this.uri);
                createMessage.setPackageName(this.packageName);
                createMessage.setFormatHint(this.formatHint);
                createMessage.setHttpHeaders(this.httpHeaders);
                return createMessage;
            }
        }

        ArrayList<Object> toList() {
            ArrayList<Object> arrayList = new ArrayList<>(5);
            arrayList.add(this.asset);
            arrayList.add(this.uri);
            arrayList.add(this.packageName);
            arrayList.add(this.formatHint);
            arrayList.add(this.httpHeaders);
            return arrayList;
        }

        static CreateMessage fromList(ArrayList<Object> arrayList) {
            CreateMessage createMessage = new CreateMessage();
            createMessage.setAsset((String) arrayList.get(0));
            createMessage.setUri((String) arrayList.get(1));
            createMessage.setPackageName((String) arrayList.get(2));
            createMessage.setFormatHint((String) arrayList.get(3));
            createMessage.setHttpHeaders((Map) arrayList.get(4));
            return createMessage;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class PigeonCodec extends StandardMessageCodec {
        public static final PigeonCodec INSTANCE = new PigeonCodec();

        private PigeonCodec() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public Object readValueOfType(byte b, ByteBuffer byteBuffer) {
            if (b == -127) {
                return CreateMessage.fromList((ArrayList) readValue(byteBuffer));
            }
            return super.readValueOfType(b, byteBuffer);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public void writeValue(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (obj instanceof CreateMessage) {
                byteArrayOutputStream.write(129);
                writeValue(byteArrayOutputStream, ((CreateMessage) obj).toList());
            } else {
                super.writeValue(byteArrayOutputStream, obj);
            }
        }
    }

    /* loaded from: classes5.dex */
    public interface AndroidVideoPlayerApi {
        Long create(CreateMessage createMessage);

        void dispose(Long l);

        void initialize();

        void pause(Long l);

        void play(Long l);

        Long position(Long l);

        void seekTo(Long l, Long l2);

        void setLooping(Long l, Boolean bool);

        void setMixWithOthers(Boolean bool);

        void setPlaybackSpeed(Long l, Double d);

        void setVolume(Long l, Double d);

        /* renamed from: io.flutter.plugins.videoplayer.Messages$AndroidVideoPlayerApi$-CC, reason: invalid class name */
        /* loaded from: classes5.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return PigeonCodec.INSTANCE;
            }

            public static void setUp(BinaryMessenger binaryMessenger, AndroidVideoPlayerApi androidVideoPlayerApi) {
                setUp(binaryMessenger, "", androidVideoPlayerApi);
            }

            public static void setUp(BinaryMessenger binaryMessenger, String str, final AndroidVideoPlayerApi androidVideoPlayerApi) {
                String str2;
                if (str.isEmpty()) {
                    str2 = "";
                } else {
                    str2 = "." + str;
                }
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.video_player_android.AndroidVideoPlayerApi.initialize" + str2, getCodec());
                if (androidVideoPlayerApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.videoplayer.Messages$AndroidVideoPlayerApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.AndroidVideoPlayerApi.CC.lambda$setUp$0(Messages.AndroidVideoPlayerApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel2 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.video_player_android.AndroidVideoPlayerApi.create" + str2, getCodec());
                if (androidVideoPlayerApi != null) {
                    basicMessageChannel2.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.videoplayer.Messages$AndroidVideoPlayerApi$$ExternalSyntheticLambda2
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.AndroidVideoPlayerApi.CC.lambda$setUp$1(Messages.AndroidVideoPlayerApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel2.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel3 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.video_player_android.AndroidVideoPlayerApi.dispose" + str2, getCodec());
                if (androidVideoPlayerApi != null) {
                    basicMessageChannel3.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.videoplayer.Messages$AndroidVideoPlayerApi$$ExternalSyntheticLambda4
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.AndroidVideoPlayerApi.CC.lambda$setUp$2(Messages.AndroidVideoPlayerApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel3.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel4 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.video_player_android.AndroidVideoPlayerApi.setLooping" + str2, getCodec());
                if (androidVideoPlayerApi != null) {
                    basicMessageChannel4.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.videoplayer.Messages$AndroidVideoPlayerApi$$ExternalSyntheticLambda5
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.AndroidVideoPlayerApi.CC.lambda$setUp$3(Messages.AndroidVideoPlayerApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel4.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel5 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.video_player_android.AndroidVideoPlayerApi.setVolume" + str2, getCodec());
                if (androidVideoPlayerApi != null) {
                    basicMessageChannel5.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.videoplayer.Messages$AndroidVideoPlayerApi$$ExternalSyntheticLambda6
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.AndroidVideoPlayerApi.CC.lambda$setUp$4(Messages.AndroidVideoPlayerApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel5.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel6 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.video_player_android.AndroidVideoPlayerApi.setPlaybackSpeed" + str2, getCodec());
                if (androidVideoPlayerApi != null) {
                    basicMessageChannel6.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.videoplayer.Messages$AndroidVideoPlayerApi$$ExternalSyntheticLambda7
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.AndroidVideoPlayerApi.CC.lambda$setUp$5(Messages.AndroidVideoPlayerApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel6.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel7 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.video_player_android.AndroidVideoPlayerApi.play" + str2, getCodec());
                if (androidVideoPlayerApi != null) {
                    basicMessageChannel7.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.videoplayer.Messages$AndroidVideoPlayerApi$$ExternalSyntheticLambda8
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.AndroidVideoPlayerApi.CC.lambda$setUp$6(Messages.AndroidVideoPlayerApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel7.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel8 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.video_player_android.AndroidVideoPlayerApi.position" + str2, getCodec());
                if (androidVideoPlayerApi != null) {
                    basicMessageChannel8.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.videoplayer.Messages$AndroidVideoPlayerApi$$ExternalSyntheticLambda9
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.AndroidVideoPlayerApi.CC.lambda$setUp$7(Messages.AndroidVideoPlayerApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel8.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel9 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.video_player_android.AndroidVideoPlayerApi.seekTo" + str2, getCodec());
                if (androidVideoPlayerApi != null) {
                    basicMessageChannel9.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.videoplayer.Messages$AndroidVideoPlayerApi$$ExternalSyntheticLambda10
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.AndroidVideoPlayerApi.CC.lambda$setUp$8(Messages.AndroidVideoPlayerApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel9.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel10 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.video_player_android.AndroidVideoPlayerApi.pause" + str2, getCodec());
                if (androidVideoPlayerApi != null) {
                    basicMessageChannel10.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.videoplayer.Messages$AndroidVideoPlayerApi$$ExternalSyntheticLambda1
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.AndroidVideoPlayerApi.CC.lambda$setUp$9(Messages.AndroidVideoPlayerApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel10.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel11 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.video_player_android.AndroidVideoPlayerApi.setMixWithOthers" + str2, getCodec());
                if (androidVideoPlayerApi != null) {
                    basicMessageChannel11.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.videoplayer.Messages$AndroidVideoPlayerApi$$ExternalSyntheticLambda3
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.AndroidVideoPlayerApi.CC.lambda$setUp$10(Messages.AndroidVideoPlayerApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel11.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setUp$0(AndroidVideoPlayerApi androidVideoPlayerApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList<Object> arrayList = new ArrayList<>();
                try {
                    androidVideoPlayerApi.initialize();
                    arrayList.add(0, null);
                } catch (Throwable th) {
                    arrayList = Messages.wrapError(th);
                }
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setUp$1(AndroidVideoPlayerApi androidVideoPlayerApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList<Object> arrayList = new ArrayList<>();
                try {
                    arrayList.add(0, androidVideoPlayerApi.create((CreateMessage) ((ArrayList) obj).get(0)));
                } catch (Throwable th) {
                    arrayList = Messages.wrapError(th);
                }
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setUp$2(AndroidVideoPlayerApi androidVideoPlayerApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList<Object> arrayList = new ArrayList<>();
                try {
                    androidVideoPlayerApi.dispose((Long) ((ArrayList) obj).get(0));
                    arrayList.add(0, null);
                } catch (Throwable th) {
                    arrayList = Messages.wrapError(th);
                }
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setUp$3(AndroidVideoPlayerApi androidVideoPlayerApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList<Object> arrayList = new ArrayList<>();
                ArrayList arrayList2 = (ArrayList) obj;
                try {
                    androidVideoPlayerApi.setLooping((Long) arrayList2.get(0), (Boolean) arrayList2.get(1));
                    arrayList.add(0, null);
                } catch (Throwable th) {
                    arrayList = Messages.wrapError(th);
                }
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setUp$4(AndroidVideoPlayerApi androidVideoPlayerApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList<Object> arrayList = new ArrayList<>();
                ArrayList arrayList2 = (ArrayList) obj;
                try {
                    androidVideoPlayerApi.setVolume((Long) arrayList2.get(0), (Double) arrayList2.get(1));
                    arrayList.add(0, null);
                } catch (Throwable th) {
                    arrayList = Messages.wrapError(th);
                }
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setUp$5(AndroidVideoPlayerApi androidVideoPlayerApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList<Object> arrayList = new ArrayList<>();
                ArrayList arrayList2 = (ArrayList) obj;
                try {
                    androidVideoPlayerApi.setPlaybackSpeed((Long) arrayList2.get(0), (Double) arrayList2.get(1));
                    arrayList.add(0, null);
                } catch (Throwable th) {
                    arrayList = Messages.wrapError(th);
                }
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setUp$6(AndroidVideoPlayerApi androidVideoPlayerApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList<Object> arrayList = new ArrayList<>();
                try {
                    androidVideoPlayerApi.play((Long) ((ArrayList) obj).get(0));
                    arrayList.add(0, null);
                } catch (Throwable th) {
                    arrayList = Messages.wrapError(th);
                }
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setUp$7(AndroidVideoPlayerApi androidVideoPlayerApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList<Object> arrayList = new ArrayList<>();
                try {
                    arrayList.add(0, androidVideoPlayerApi.position((Long) ((ArrayList) obj).get(0)));
                } catch (Throwable th) {
                    arrayList = Messages.wrapError(th);
                }
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setUp$8(AndroidVideoPlayerApi androidVideoPlayerApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList<Object> arrayList = new ArrayList<>();
                ArrayList arrayList2 = (ArrayList) obj;
                try {
                    androidVideoPlayerApi.seekTo((Long) arrayList2.get(0), (Long) arrayList2.get(1));
                    arrayList.add(0, null);
                } catch (Throwable th) {
                    arrayList = Messages.wrapError(th);
                }
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setUp$9(AndroidVideoPlayerApi androidVideoPlayerApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList<Object> arrayList = new ArrayList<>();
                try {
                    androidVideoPlayerApi.pause((Long) ((ArrayList) obj).get(0));
                    arrayList.add(0, null);
                } catch (Throwable th) {
                    arrayList = Messages.wrapError(th);
                }
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setUp$10(AndroidVideoPlayerApi androidVideoPlayerApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList<Object> arrayList = new ArrayList<>();
                try {
                    androidVideoPlayerApi.setMixWithOthers((Boolean) ((ArrayList) obj).get(0));
                    arrayList.add(0, null);
                } catch (Throwable th) {
                    arrayList = Messages.wrapError(th);
                }
                reply.reply(arrayList);
            }
        }
    }
}
