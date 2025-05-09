package androidx.media3.exoplayer.text;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleDecoder;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.cea.Cea608Decoder;
import androidx.media3.extractor.text.cea.Cea708Decoder;
import java.util.Objects;

/* loaded from: classes.dex */
public interface SubtitleDecoderFactory {
    public static final SubtitleDecoderFactory DEFAULT = new SubtitleDecoderFactory() { // from class: androidx.media3.exoplayer.text.SubtitleDecoderFactory.1
        private final DefaultSubtitleParserFactory delegate = new DefaultSubtitleParserFactory();

        @Override // androidx.media3.exoplayer.text.SubtitleDecoderFactory
        public boolean supportsFormat(Format format) {
            String str = format.sampleMimeType;
            return this.delegate.supportsFormat(format) || Objects.equals(str, MimeTypes.APPLICATION_CEA608) || Objects.equals(str, MimeTypes.APPLICATION_MP4CEA608) || Objects.equals(str, MimeTypes.APPLICATION_CEA708);
        }

        @Override // androidx.media3.exoplayer.text.SubtitleDecoderFactory
        public SubtitleDecoder createDecoder(Format format) {
            String str = format.sampleMimeType;
            if (str != null) {
                str.hashCode();
                char c = 65535;
                switch (str.hashCode()) {
                    case 930165504:
                        if (str.equals(MimeTypes.APPLICATION_MP4CEA608)) {
                            c = 0;
                            break;
                        }
                        break;
                    case 1566015601:
                        if (str.equals(MimeTypes.APPLICATION_CEA608)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 1566016562:
                        if (str.equals(MimeTypes.APPLICATION_CEA708)) {
                            c = 2;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                    case 1:
                        return new Cea608Decoder(str, format.accessibilityChannel, Cea608Decoder.MIN_DATA_CHANNEL_TIMEOUT_MS);
                    case 2:
                        return new Cea708Decoder(format.accessibilityChannel, format.initializationData);
                }
            }
            if (this.delegate.supportsFormat(format)) {
                SubtitleParser create = this.delegate.create(format);
                return new DelegatingSubtitleDecoder(create.getClass().getSimpleName() + "Decoder", create);
            }
            throw new IllegalArgumentException("Attempted to create decoder for unsupported MIME type: " + str);
        }
    };

    SubtitleDecoder createDecoder(Format format);

    boolean supportsFormat(Format format);
}
