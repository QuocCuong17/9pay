package io.flutter.plugins.imagepicker;

import android.util.Log;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugins.imagepicker.Messages;
import java.io.ByteArrayOutputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.jmrtd.cbeff.ISO781611;

/* loaded from: classes5.dex */
public class Messages {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes5.dex */
    @interface CanIgnoreReturnValue {
    }

    /* loaded from: classes5.dex */
    public interface NullableResult<T> {
        void error(Throwable th);

        void success(T t);
    }

    /* loaded from: classes5.dex */
    public interface Result<T> {
        void error(Throwable th);

        void success(T t);
    }

    /* loaded from: classes5.dex */
    public interface VoidResult {
        void error(Throwable th);

        void success();
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
    public enum SourceCamera {
        REAR(0),
        FRONT(1);

        final int index;

        SourceCamera(int i) {
            this.index = i;
        }
    }

    /* loaded from: classes5.dex */
    public enum SourceType {
        CAMERA(0),
        GALLERY(1);

        final int index;

        SourceType(int i) {
            this.index = i;
        }
    }

    /* loaded from: classes5.dex */
    public enum CacheRetrievalType {
        IMAGE(0),
        VIDEO(1);

        final int index;

        CacheRetrievalType(int i) {
            this.index = i;
        }
    }

    /* loaded from: classes5.dex */
    public static final class GeneralOptions {
        private Boolean allowMultiple;
        private Long limit;
        private Boolean usePhotoPicker;

        public Boolean getAllowMultiple() {
            return this.allowMultiple;
        }

        public void setAllowMultiple(Boolean bool) {
            if (bool == null) {
                throw new IllegalStateException("Nonnull field \"allowMultiple\" is null.");
            }
            this.allowMultiple = bool;
        }

        public Boolean getUsePhotoPicker() {
            return this.usePhotoPicker;
        }

        public void setUsePhotoPicker(Boolean bool) {
            if (bool == null) {
                throw new IllegalStateException("Nonnull field \"usePhotoPicker\" is null.");
            }
            this.usePhotoPicker = bool;
        }

        public Long getLimit() {
            return this.limit;
        }

        public void setLimit(Long l) {
            this.limit = l;
        }

        GeneralOptions() {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            GeneralOptions generalOptions = (GeneralOptions) obj;
            return this.allowMultiple.equals(generalOptions.allowMultiple) && this.usePhotoPicker.equals(generalOptions.usePhotoPicker) && Objects.equals(this.limit, generalOptions.limit);
        }

        public int hashCode() {
            return Objects.hash(this.allowMultiple, this.usePhotoPicker, this.limit);
        }

        /* loaded from: classes5.dex */
        public static final class Builder {
            private Boolean allowMultiple;
            private Long limit;
            private Boolean usePhotoPicker;

            public Builder setAllowMultiple(Boolean bool) {
                this.allowMultiple = bool;
                return this;
            }

            public Builder setUsePhotoPicker(Boolean bool) {
                this.usePhotoPicker = bool;
                return this;
            }

            public Builder setLimit(Long l) {
                this.limit = l;
                return this;
            }

            public GeneralOptions build() {
                GeneralOptions generalOptions = new GeneralOptions();
                generalOptions.setAllowMultiple(this.allowMultiple);
                generalOptions.setUsePhotoPicker(this.usePhotoPicker);
                generalOptions.setLimit(this.limit);
                return generalOptions;
            }
        }

        ArrayList<Object> toList() {
            ArrayList<Object> arrayList = new ArrayList<>(3);
            arrayList.add(this.allowMultiple);
            arrayList.add(this.usePhotoPicker);
            arrayList.add(this.limit);
            return arrayList;
        }

        static GeneralOptions fromList(ArrayList<Object> arrayList) {
            GeneralOptions generalOptions = new GeneralOptions();
            generalOptions.setAllowMultiple((Boolean) arrayList.get(0));
            generalOptions.setUsePhotoPicker((Boolean) arrayList.get(1));
            generalOptions.setLimit((Long) arrayList.get(2));
            return generalOptions;
        }
    }

    /* loaded from: classes5.dex */
    public static final class ImageSelectionOptions {
        private Double maxHeight;
        private Double maxWidth;
        private Long quality;

        public Double getMaxWidth() {
            return this.maxWidth;
        }

        public void setMaxWidth(Double d) {
            this.maxWidth = d;
        }

        public Double getMaxHeight() {
            return this.maxHeight;
        }

        public void setMaxHeight(Double d) {
            this.maxHeight = d;
        }

        public Long getQuality() {
            return this.quality;
        }

        public void setQuality(Long l) {
            if (l == null) {
                throw new IllegalStateException("Nonnull field \"quality\" is null.");
            }
            this.quality = l;
        }

        ImageSelectionOptions() {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ImageSelectionOptions imageSelectionOptions = (ImageSelectionOptions) obj;
            return Objects.equals(this.maxWidth, imageSelectionOptions.maxWidth) && Objects.equals(this.maxHeight, imageSelectionOptions.maxHeight) && this.quality.equals(imageSelectionOptions.quality);
        }

        public int hashCode() {
            return Objects.hash(this.maxWidth, this.maxHeight, this.quality);
        }

        /* loaded from: classes5.dex */
        public static final class Builder {
            private Double maxHeight;
            private Double maxWidth;
            private Long quality;

            public Builder setMaxWidth(Double d) {
                this.maxWidth = d;
                return this;
            }

            public Builder setMaxHeight(Double d) {
                this.maxHeight = d;
                return this;
            }

            public Builder setQuality(Long l) {
                this.quality = l;
                return this;
            }

            public ImageSelectionOptions build() {
                ImageSelectionOptions imageSelectionOptions = new ImageSelectionOptions();
                imageSelectionOptions.setMaxWidth(this.maxWidth);
                imageSelectionOptions.setMaxHeight(this.maxHeight);
                imageSelectionOptions.setQuality(this.quality);
                return imageSelectionOptions;
            }
        }

        ArrayList<Object> toList() {
            ArrayList<Object> arrayList = new ArrayList<>(3);
            arrayList.add(this.maxWidth);
            arrayList.add(this.maxHeight);
            arrayList.add(this.quality);
            return arrayList;
        }

        static ImageSelectionOptions fromList(ArrayList<Object> arrayList) {
            ImageSelectionOptions imageSelectionOptions = new ImageSelectionOptions();
            imageSelectionOptions.setMaxWidth((Double) arrayList.get(0));
            imageSelectionOptions.setMaxHeight((Double) arrayList.get(1));
            imageSelectionOptions.setQuality((Long) arrayList.get(2));
            return imageSelectionOptions;
        }
    }

    /* loaded from: classes5.dex */
    public static final class MediaSelectionOptions {
        private ImageSelectionOptions imageSelectionOptions;

        public ImageSelectionOptions getImageSelectionOptions() {
            return this.imageSelectionOptions;
        }

        public void setImageSelectionOptions(ImageSelectionOptions imageSelectionOptions) {
            if (imageSelectionOptions == null) {
                throw new IllegalStateException("Nonnull field \"imageSelectionOptions\" is null.");
            }
            this.imageSelectionOptions = imageSelectionOptions;
        }

        MediaSelectionOptions() {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.imageSelectionOptions.equals(((MediaSelectionOptions) obj).imageSelectionOptions);
        }

        public int hashCode() {
            return Objects.hash(this.imageSelectionOptions);
        }

        /* loaded from: classes5.dex */
        public static final class Builder {
            private ImageSelectionOptions imageSelectionOptions;

            public Builder setImageSelectionOptions(ImageSelectionOptions imageSelectionOptions) {
                this.imageSelectionOptions = imageSelectionOptions;
                return this;
            }

            public MediaSelectionOptions build() {
                MediaSelectionOptions mediaSelectionOptions = new MediaSelectionOptions();
                mediaSelectionOptions.setImageSelectionOptions(this.imageSelectionOptions);
                return mediaSelectionOptions;
            }
        }

        ArrayList<Object> toList() {
            ArrayList<Object> arrayList = new ArrayList<>(1);
            arrayList.add(this.imageSelectionOptions);
            return arrayList;
        }

        static MediaSelectionOptions fromList(ArrayList<Object> arrayList) {
            MediaSelectionOptions mediaSelectionOptions = new MediaSelectionOptions();
            mediaSelectionOptions.setImageSelectionOptions((ImageSelectionOptions) arrayList.get(0));
            return mediaSelectionOptions;
        }
    }

    /* loaded from: classes5.dex */
    public static final class VideoSelectionOptions {
        private Long maxDurationSeconds;

        public Long getMaxDurationSeconds() {
            return this.maxDurationSeconds;
        }

        public void setMaxDurationSeconds(Long l) {
            this.maxDurationSeconds = l;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return Objects.equals(this.maxDurationSeconds, ((VideoSelectionOptions) obj).maxDurationSeconds);
        }

        public int hashCode() {
            return Objects.hash(this.maxDurationSeconds);
        }

        /* loaded from: classes5.dex */
        public static final class Builder {
            private Long maxDurationSeconds;

            public Builder setMaxDurationSeconds(Long l) {
                this.maxDurationSeconds = l;
                return this;
            }

            public VideoSelectionOptions build() {
                VideoSelectionOptions videoSelectionOptions = new VideoSelectionOptions();
                videoSelectionOptions.setMaxDurationSeconds(this.maxDurationSeconds);
                return videoSelectionOptions;
            }
        }

        ArrayList<Object> toList() {
            ArrayList<Object> arrayList = new ArrayList<>(1);
            arrayList.add(this.maxDurationSeconds);
            return arrayList;
        }

        static VideoSelectionOptions fromList(ArrayList<Object> arrayList) {
            VideoSelectionOptions videoSelectionOptions = new VideoSelectionOptions();
            videoSelectionOptions.setMaxDurationSeconds((Long) arrayList.get(0));
            return videoSelectionOptions;
        }
    }

    /* loaded from: classes5.dex */
    public static final class SourceSpecification {
        private SourceCamera camera;
        private SourceType type;

        public SourceType getType() {
            return this.type;
        }

        public void setType(SourceType sourceType) {
            if (sourceType == null) {
                throw new IllegalStateException("Nonnull field \"type\" is null.");
            }
            this.type = sourceType;
        }

        public SourceCamera getCamera() {
            return this.camera;
        }

        public void setCamera(SourceCamera sourceCamera) {
            this.camera = sourceCamera;
        }

        SourceSpecification() {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SourceSpecification sourceSpecification = (SourceSpecification) obj;
            return this.type.equals(sourceSpecification.type) && Objects.equals(this.camera, sourceSpecification.camera);
        }

        public int hashCode() {
            return Objects.hash(this.type, this.camera);
        }

        /* loaded from: classes5.dex */
        public static final class Builder {
            private SourceCamera camera;
            private SourceType type;

            public Builder setType(SourceType sourceType) {
                this.type = sourceType;
                return this;
            }

            public Builder setCamera(SourceCamera sourceCamera) {
                this.camera = sourceCamera;
                return this;
            }

            public SourceSpecification build() {
                SourceSpecification sourceSpecification = new SourceSpecification();
                sourceSpecification.setType(this.type);
                sourceSpecification.setCamera(this.camera);
                return sourceSpecification;
            }
        }

        ArrayList<Object> toList() {
            ArrayList<Object> arrayList = new ArrayList<>(2);
            arrayList.add(this.type);
            arrayList.add(this.camera);
            return arrayList;
        }

        static SourceSpecification fromList(ArrayList<Object> arrayList) {
            SourceSpecification sourceSpecification = new SourceSpecification();
            sourceSpecification.setType((SourceType) arrayList.get(0));
            sourceSpecification.setCamera((SourceCamera) arrayList.get(1));
            return sourceSpecification;
        }
    }

    /* loaded from: classes5.dex */
    public static final class CacheRetrievalError {
        private String code;
        private String message;

        public String getCode() {
            return this.code;
        }

        public void setCode(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"code\" is null.");
            }
            this.code = str;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String str) {
            this.message = str;
        }

        CacheRetrievalError() {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            CacheRetrievalError cacheRetrievalError = (CacheRetrievalError) obj;
            return this.code.equals(cacheRetrievalError.code) && Objects.equals(this.message, cacheRetrievalError.message);
        }

        public int hashCode() {
            return Objects.hash(this.code, this.message);
        }

        /* loaded from: classes5.dex */
        public static final class Builder {
            private String code;
            private String message;

            public Builder setCode(String str) {
                this.code = str;
                return this;
            }

            public Builder setMessage(String str) {
                this.message = str;
                return this;
            }

            public CacheRetrievalError build() {
                CacheRetrievalError cacheRetrievalError = new CacheRetrievalError();
                cacheRetrievalError.setCode(this.code);
                cacheRetrievalError.setMessage(this.message);
                return cacheRetrievalError;
            }
        }

        ArrayList<Object> toList() {
            ArrayList<Object> arrayList = new ArrayList<>(2);
            arrayList.add(this.code);
            arrayList.add(this.message);
            return arrayList;
        }

        static CacheRetrievalError fromList(ArrayList<Object> arrayList) {
            CacheRetrievalError cacheRetrievalError = new CacheRetrievalError();
            cacheRetrievalError.setCode((String) arrayList.get(0));
            cacheRetrievalError.setMessage((String) arrayList.get(1));
            return cacheRetrievalError;
        }
    }

    /* loaded from: classes5.dex */
    public static final class CacheRetrievalResult {
        private CacheRetrievalError error;
        private List<String> paths;
        private CacheRetrievalType type;

        public CacheRetrievalType getType() {
            return this.type;
        }

        public void setType(CacheRetrievalType cacheRetrievalType) {
            if (cacheRetrievalType == null) {
                throw new IllegalStateException("Nonnull field \"type\" is null.");
            }
            this.type = cacheRetrievalType;
        }

        public CacheRetrievalError getError() {
            return this.error;
        }

        public void setError(CacheRetrievalError cacheRetrievalError) {
            this.error = cacheRetrievalError;
        }

        public List<String> getPaths() {
            return this.paths;
        }

        public void setPaths(List<String> list) {
            if (list == null) {
                throw new IllegalStateException("Nonnull field \"paths\" is null.");
            }
            this.paths = list;
        }

        CacheRetrievalResult() {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            CacheRetrievalResult cacheRetrievalResult = (CacheRetrievalResult) obj;
            return this.type.equals(cacheRetrievalResult.type) && Objects.equals(this.error, cacheRetrievalResult.error) && this.paths.equals(cacheRetrievalResult.paths);
        }

        public int hashCode() {
            return Objects.hash(this.type, this.error, this.paths);
        }

        /* loaded from: classes5.dex */
        public static final class Builder {
            private CacheRetrievalError error;
            private List<String> paths;
            private CacheRetrievalType type;

            public Builder setType(CacheRetrievalType cacheRetrievalType) {
                this.type = cacheRetrievalType;
                return this;
            }

            public Builder setError(CacheRetrievalError cacheRetrievalError) {
                this.error = cacheRetrievalError;
                return this;
            }

            public Builder setPaths(List<String> list) {
                this.paths = list;
                return this;
            }

            public CacheRetrievalResult build() {
                CacheRetrievalResult cacheRetrievalResult = new CacheRetrievalResult();
                cacheRetrievalResult.setType(this.type);
                cacheRetrievalResult.setError(this.error);
                cacheRetrievalResult.setPaths(this.paths);
                return cacheRetrievalResult;
            }
        }

        ArrayList<Object> toList() {
            ArrayList<Object> arrayList = new ArrayList<>(3);
            arrayList.add(this.type);
            arrayList.add(this.error);
            arrayList.add(this.paths);
            return arrayList;
        }

        static CacheRetrievalResult fromList(ArrayList<Object> arrayList) {
            CacheRetrievalResult cacheRetrievalResult = new CacheRetrievalResult();
            cacheRetrievalResult.setType((CacheRetrievalType) arrayList.get(0));
            cacheRetrievalResult.setError((CacheRetrievalError) arrayList.get(1));
            cacheRetrievalResult.setPaths((List) arrayList.get(2));
            return cacheRetrievalResult;
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
            switch (b) {
                case -127:
                    Object readValue = readValue(byteBuffer);
                    if (readValue == null) {
                        return null;
                    }
                    return SourceCamera.values()[((Long) readValue).intValue()];
                case -126:
                    Object readValue2 = readValue(byteBuffer);
                    if (readValue2 == null) {
                        return null;
                    }
                    return SourceType.values()[((Long) readValue2).intValue()];
                case -125:
                    Object readValue3 = readValue(byteBuffer);
                    if (readValue3 == null) {
                        return null;
                    }
                    return CacheRetrievalType.values()[((Long) readValue3).intValue()];
                case -124:
                    return GeneralOptions.fromList((ArrayList) readValue(byteBuffer));
                case -123:
                    return ImageSelectionOptions.fromList((ArrayList) readValue(byteBuffer));
                case -122:
                    return MediaSelectionOptions.fromList((ArrayList) readValue(byteBuffer));
                case -121:
                    return VideoSelectionOptions.fromList((ArrayList) readValue(byteBuffer));
                case -120:
                    return SourceSpecification.fromList((ArrayList) readValue(byteBuffer));
                case -119:
                    return CacheRetrievalError.fromList((ArrayList) readValue(byteBuffer));
                case -118:
                    return CacheRetrievalResult.fromList((ArrayList) readValue(byteBuffer));
                default:
                    return super.readValueOfType(b, byteBuffer);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public void writeValue(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (obj instanceof SourceCamera) {
                byteArrayOutputStream.write(129);
                writeValue(byteArrayOutputStream, obj != null ? Integer.valueOf(((SourceCamera) obj).index) : null);
                return;
            }
            if (obj instanceof SourceType) {
                byteArrayOutputStream.write(130);
                writeValue(byteArrayOutputStream, obj != null ? Integer.valueOf(((SourceType) obj).index) : null);
                return;
            }
            if (obj instanceof CacheRetrievalType) {
                byteArrayOutputStream.write(ISO781611.CREATION_DATE_AND_TIME_TAG);
                writeValue(byteArrayOutputStream, obj != null ? Integer.valueOf(((CacheRetrievalType) obj).index) : null);
                return;
            }
            if (obj instanceof GeneralOptions) {
                byteArrayOutputStream.write(CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA);
                writeValue(byteArrayOutputStream, ((GeneralOptions) obj).toList());
                return;
            }
            if (obj instanceof ImageSelectionOptions) {
                byteArrayOutputStream.write(133);
                writeValue(byteArrayOutputStream, ((ImageSelectionOptions) obj).toList());
                return;
            }
            if (obj instanceof MediaSelectionOptions) {
                byteArrayOutputStream.write(134);
                writeValue(byteArrayOutputStream, ((MediaSelectionOptions) obj).toList());
                return;
            }
            if (obj instanceof VideoSelectionOptions) {
                byteArrayOutputStream.write(135);
                writeValue(byteArrayOutputStream, ((VideoSelectionOptions) obj).toList());
                return;
            }
            if (obj instanceof SourceSpecification) {
                byteArrayOutputStream.write(136);
                writeValue(byteArrayOutputStream, ((SourceSpecification) obj).toList());
            } else if (obj instanceof CacheRetrievalError) {
                byteArrayOutputStream.write(CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA);
                writeValue(byteArrayOutputStream, ((CacheRetrievalError) obj).toList());
            } else if (obj instanceof CacheRetrievalResult) {
                byteArrayOutputStream.write(138);
                writeValue(byteArrayOutputStream, ((CacheRetrievalResult) obj).toList());
            } else {
                super.writeValue(byteArrayOutputStream, obj);
            }
        }
    }

    /* loaded from: classes5.dex */
    public interface ImagePickerApi {
        void pickImages(SourceSpecification sourceSpecification, ImageSelectionOptions imageSelectionOptions, GeneralOptions generalOptions, Result<List<String>> result);

        void pickMedia(MediaSelectionOptions mediaSelectionOptions, GeneralOptions generalOptions, Result<List<String>> result);

        void pickVideos(SourceSpecification sourceSpecification, VideoSelectionOptions videoSelectionOptions, GeneralOptions generalOptions, Result<List<String>> result);

        CacheRetrievalResult retrieveLostResults();

        /* renamed from: io.flutter.plugins.imagepicker.Messages$ImagePickerApi$-CC, reason: invalid class name */
        /* loaded from: classes5.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return PigeonCodec.INSTANCE;
            }

            public static void setUp(BinaryMessenger binaryMessenger, ImagePickerApi imagePickerApi) {
                setUp(binaryMessenger, "", imagePickerApi);
            }

            public static void setUp(BinaryMessenger binaryMessenger, String str, final ImagePickerApi imagePickerApi) {
                String str2;
                if (str.isEmpty()) {
                    str2 = "";
                } else {
                    str2 = "." + str;
                }
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.image_picker_android.ImagePickerApi.pickImages" + str2, getCodec(), binaryMessenger.makeBackgroundTaskQueue());
                if (imagePickerApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.imagepicker.Messages$ImagePickerApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.ImagePickerApi.CC.lambda$setUp$0(Messages.ImagePickerApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel2 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.image_picker_android.ImagePickerApi.pickVideos" + str2, getCodec(), binaryMessenger.makeBackgroundTaskQueue());
                if (imagePickerApi != null) {
                    basicMessageChannel2.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.imagepicker.Messages$ImagePickerApi$$ExternalSyntheticLambda1
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.ImagePickerApi.CC.lambda$setUp$1(Messages.ImagePickerApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel2.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel3 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.image_picker_android.ImagePickerApi.pickMedia" + str2, getCodec());
                if (imagePickerApi != null) {
                    basicMessageChannel3.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.imagepicker.Messages$ImagePickerApi$$ExternalSyntheticLambda2
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.ImagePickerApi.CC.lambda$setUp$2(Messages.ImagePickerApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel3.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel4 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.image_picker_android.ImagePickerApi.retrieveLostResults" + str2, getCodec(), binaryMessenger.makeBackgroundTaskQueue());
                if (imagePickerApi != null) {
                    basicMessageChannel4.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.imagepicker.Messages$ImagePickerApi$$ExternalSyntheticLambda3
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.ImagePickerApi.CC.lambda$setUp$3(Messages.ImagePickerApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel4.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setUp$0(ImagePickerApi imagePickerApi, Object obj, final BasicMessageChannel.Reply reply) {
                final ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = (ArrayList) obj;
                imagePickerApi.pickImages((SourceSpecification) arrayList2.get(0), (ImageSelectionOptions) arrayList2.get(1), (GeneralOptions) arrayList2.get(2), new Result<List<String>>() { // from class: io.flutter.plugins.imagepicker.Messages.ImagePickerApi.1
                    @Override // io.flutter.plugins.imagepicker.Messages.Result
                    public void success(List<String> list) {
                        arrayList.add(0, list);
                        reply.reply(arrayList);
                    }

                    @Override // io.flutter.plugins.imagepicker.Messages.Result
                    public void error(Throwable th) {
                        reply.reply(Messages.wrapError(th));
                    }
                });
            }

            public static /* synthetic */ void lambda$setUp$1(ImagePickerApi imagePickerApi, Object obj, final BasicMessageChannel.Reply reply) {
                final ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = (ArrayList) obj;
                imagePickerApi.pickVideos((SourceSpecification) arrayList2.get(0), (VideoSelectionOptions) arrayList2.get(1), (GeneralOptions) arrayList2.get(2), new Result<List<String>>() { // from class: io.flutter.plugins.imagepicker.Messages.ImagePickerApi.2
                    @Override // io.flutter.plugins.imagepicker.Messages.Result
                    public void success(List<String> list) {
                        arrayList.add(0, list);
                        reply.reply(arrayList);
                    }

                    @Override // io.flutter.plugins.imagepicker.Messages.Result
                    public void error(Throwable th) {
                        reply.reply(Messages.wrapError(th));
                    }
                });
            }

            public static /* synthetic */ void lambda$setUp$2(ImagePickerApi imagePickerApi, Object obj, final BasicMessageChannel.Reply reply) {
                final ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = (ArrayList) obj;
                imagePickerApi.pickMedia((MediaSelectionOptions) arrayList2.get(0), (GeneralOptions) arrayList2.get(1), new Result<List<String>>() { // from class: io.flutter.plugins.imagepicker.Messages.ImagePickerApi.3
                    @Override // io.flutter.plugins.imagepicker.Messages.Result
                    public void success(List<String> list) {
                        arrayList.add(0, list);
                        reply.reply(arrayList);
                    }

                    @Override // io.flutter.plugins.imagepicker.Messages.Result
                    public void error(Throwable th) {
                        reply.reply(Messages.wrapError(th));
                    }
                });
            }

            public static /* synthetic */ void lambda$setUp$3(ImagePickerApi imagePickerApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList<Object> arrayList = new ArrayList<>();
                try {
                    arrayList.add(0, imagePickerApi.retrieveLostResults());
                } catch (Throwable th) {
                    arrayList = Messages.wrapError(th);
                }
                reply.reply(arrayList);
            }
        }
    }
}
