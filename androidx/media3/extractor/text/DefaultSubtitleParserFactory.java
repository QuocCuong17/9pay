package androidx.media3.extractor.text;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.dvb.DvbParser;
import androidx.media3.extractor.text.pgs.PgsParser;
import androidx.media3.extractor.text.ssa.SsaParser;
import androidx.media3.extractor.text.subrip.SubripParser;
import androidx.media3.extractor.text.ttml.TtmlParser;
import androidx.media3.extractor.text.tx3g.Tx3gParser;
import androidx.media3.extractor.text.webvtt.Mp4WebvttParser;
import androidx.media3.extractor.text.webvtt.WebvttParser;
import java.util.Objects;

/* loaded from: classes.dex */
public final class DefaultSubtitleParserFactory implements SubtitleParser.Factory {
    @Override // androidx.media3.extractor.text.SubtitleParser.Factory
    public boolean supportsFormat(Format format) {
        String str = format.sampleMimeType;
        return Objects.equals(str, MimeTypes.TEXT_SSA) || Objects.equals(str, MimeTypes.TEXT_VTT) || Objects.equals(str, MimeTypes.APPLICATION_MP4VTT) || Objects.equals(str, MimeTypes.APPLICATION_SUBRIP) || Objects.equals(str, MimeTypes.APPLICATION_TX3G) || Objects.equals(str, MimeTypes.APPLICATION_PGS) || Objects.equals(str, MimeTypes.APPLICATION_DVBSUBS) || Objects.equals(str, MimeTypes.APPLICATION_TTML);
    }

    @Override // androidx.media3.extractor.text.SubtitleParser.Factory
    public int getCueReplacementBehavior(Format format) {
        String str = format.sampleMimeType;
        if (str != null) {
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1351681404:
                    if (str.equals(MimeTypes.APPLICATION_DVBSUBS)) {
                        c = 0;
                        break;
                    }
                    break;
                case -1248334819:
                    if (str.equals(MimeTypes.APPLICATION_PGS)) {
                        c = 1;
                        break;
                    }
                    break;
                case -1026075066:
                    if (str.equals(MimeTypes.APPLICATION_MP4VTT)) {
                        c = 2;
                        break;
                    }
                    break;
                case -1004728940:
                    if (str.equals(MimeTypes.TEXT_VTT)) {
                        c = 3;
                        break;
                    }
                    break;
                case 691401887:
                    if (str.equals(MimeTypes.APPLICATION_TX3G)) {
                        c = 4;
                        break;
                    }
                    break;
                case 822864842:
                    if (str.equals(MimeTypes.TEXT_SSA)) {
                        c = 5;
                        break;
                    }
                    break;
                case 1668750253:
                    if (str.equals(MimeTypes.APPLICATION_SUBRIP)) {
                        c = 6;
                        break;
                    }
                    break;
                case 1693976202:
                    if (str.equals(MimeTypes.APPLICATION_TTML)) {
                        c = 7;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                case 2:
                    return 2;
                case 3:
                    return 1;
                case 4:
                    return 2;
                case 5:
                case 6:
                case 7:
                    return 1;
            }
        }
        throw new IllegalArgumentException("Unsupported MIME type: " + str);
    }

    @Override // androidx.media3.extractor.text.SubtitleParser.Factory
    public SubtitleParser create(Format format) {
        String str = format.sampleMimeType;
        if (str != null) {
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1351681404:
                    if (str.equals(MimeTypes.APPLICATION_DVBSUBS)) {
                        c = 0;
                        break;
                    }
                    break;
                case -1248334819:
                    if (str.equals(MimeTypes.APPLICATION_PGS)) {
                        c = 1;
                        break;
                    }
                    break;
                case -1026075066:
                    if (str.equals(MimeTypes.APPLICATION_MP4VTT)) {
                        c = 2;
                        break;
                    }
                    break;
                case -1004728940:
                    if (str.equals(MimeTypes.TEXT_VTT)) {
                        c = 3;
                        break;
                    }
                    break;
                case 691401887:
                    if (str.equals(MimeTypes.APPLICATION_TX3G)) {
                        c = 4;
                        break;
                    }
                    break;
                case 822864842:
                    if (str.equals(MimeTypes.TEXT_SSA)) {
                        c = 5;
                        break;
                    }
                    break;
                case 1668750253:
                    if (str.equals(MimeTypes.APPLICATION_SUBRIP)) {
                        c = 6;
                        break;
                    }
                    break;
                case 1693976202:
                    if (str.equals(MimeTypes.APPLICATION_TTML)) {
                        c = 7;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    return new DvbParser(format.initializationData);
                case 1:
                    return new PgsParser();
                case 2:
                    return new Mp4WebvttParser();
                case 3:
                    return new WebvttParser();
                case 4:
                    return new Tx3gParser(format.initializationData);
                case 5:
                    return new SsaParser(format.initializationData);
                case 6:
                    return new SubripParser();
                case 7:
                    return new TtmlParser();
            }
        }
        throw new IllegalArgumentException("Unsupported MIME type: " + str);
    }
}
