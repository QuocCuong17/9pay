package androidx.media3.exoplayer.metadata;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.metadata.MetadataDecoder;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.media3.extractor.metadata.emsg.EventMessageDecoder;
import androidx.media3.extractor.metadata.icy.IcyDecoder;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import androidx.media3.extractor.metadata.scte35.SpliceInfoDecoder;

/* loaded from: classes.dex */
public interface MetadataDecoderFactory {
    public static final MetadataDecoderFactory DEFAULT = new MetadataDecoderFactory() { // from class: androidx.media3.exoplayer.metadata.MetadataDecoderFactory.1
        @Override // androidx.media3.exoplayer.metadata.MetadataDecoderFactory
        public boolean supportsFormat(Format format) {
            String str = format.sampleMimeType;
            return MimeTypes.APPLICATION_ID3.equals(str) || MimeTypes.APPLICATION_EMSG.equals(str) || MimeTypes.APPLICATION_SCTE35.equals(str) || MimeTypes.APPLICATION_ICY.equals(str) || MimeTypes.APPLICATION_AIT.equals(str);
        }

        @Override // androidx.media3.exoplayer.metadata.MetadataDecoderFactory
        public MetadataDecoder createDecoder(Format format) {
            String str = format.sampleMimeType;
            if (str != null) {
                str.hashCode();
                char c = 65535;
                switch (str.hashCode()) {
                    case -1354451219:
                        if (str.equals(MimeTypes.APPLICATION_AIT)) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1348231605:
                        if (str.equals(MimeTypes.APPLICATION_ICY)) {
                            c = 1;
                            break;
                        }
                        break;
                    case -1248341703:
                        if (str.equals(MimeTypes.APPLICATION_ID3)) {
                            c = 2;
                            break;
                        }
                        break;
                    case 1154383568:
                        if (str.equals(MimeTypes.APPLICATION_EMSG)) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1652648887:
                        if (str.equals(MimeTypes.APPLICATION_SCTE35)) {
                            c = 4;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        return new AppInfoTableDecoder();
                    case 1:
                        return new IcyDecoder();
                    case 2:
                        return new Id3Decoder();
                    case 3:
                        return new EventMessageDecoder();
                    case 4:
                        return new SpliceInfoDecoder();
                }
            }
            throw new IllegalArgumentException("Attempted to create decoder for unsupported MIME type: " + str);
        }
    };

    MetadataDecoder createDecoder(Format format);

    boolean supportsFormat(Format format);
}
