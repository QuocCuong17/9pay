package org.apache.fontbox.cff;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.ts.PsExtractor;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.FaxTIFFTagSet;
import org.bouncycastle.crypto.tls.CipherSuite;

/* loaded from: classes5.dex */
public final class CFFExpertSubsetCharset extends CFFCharset {
    private static final CFFExpertSubsetCharset INSTANCE;

    private CFFExpertSubsetCharset() {
        super(false);
    }

    public static CFFExpertSubsetCharset getInstance() {
        return INSTANCE;
    }

    static {
        CFFExpertSubsetCharset cFFExpertSubsetCharset = new CFFExpertSubsetCharset();
        INSTANCE = cFFExpertSubsetCharset;
        cFFExpertSubsetCharset.addSID(0, 0, ".notdef");
        cFFExpertSubsetCharset.addSID(1, 1, "space");
        cFFExpertSubsetCharset.addSID(2, 231, "dollaroldstyle");
        cFFExpertSubsetCharset.addSID(3, 232, "dollarsuperior");
        cFFExpertSubsetCharset.addSID(4, 235, "parenleftsuperior");
        cFFExpertSubsetCharset.addSID(5, 236, "parenrightsuperior");
        cFFExpertSubsetCharset.addSID(6, 237, "twodotenleader");
        cFFExpertSubsetCharset.addSID(7, 238, "onedotenleader");
        cFFExpertSubsetCharset.addSID(8, 13, "comma");
        cFFExpertSubsetCharset.addSID(9, 14, "hyphen");
        cFFExpertSubsetCharset.addSID(10, 15, TypedValues.CycleType.S_WAVE_PERIOD);
        cFFExpertSubsetCharset.addSID(11, 99, "fraction");
        cFFExpertSubsetCharset.addSID(12, 239, "zerooldstyle");
        cFFExpertSubsetCharset.addSID(13, PsExtractor.VIDEO_STREAM_MASK, "oneoldstyle");
        cFFExpertSubsetCharset.addSID(14, 241, "twooldstyle");
        cFFExpertSubsetCharset.addSID(15, 242, "threeoldstyle");
        cFFExpertSubsetCharset.addSID(16, 243, "fouroldstyle");
        cFFExpertSubsetCharset.addSID(17, 244, "fiveoldstyle");
        cFFExpertSubsetCharset.addSID(18, 245, "sixoldstyle");
        cFFExpertSubsetCharset.addSID(19, 246, "sevenoldstyle");
        cFFExpertSubsetCharset.addSID(20, 247, "eightoldstyle");
        cFFExpertSubsetCharset.addSID(21, 248, "nineoldstyle");
        cFFExpertSubsetCharset.addSID(22, 27, "colon");
        cFFExpertSubsetCharset.addSID(23, 28, "semicolon");
        cFFExpertSubsetCharset.addSID(24, 249, "commasuperior");
        cFFExpertSubsetCharset.addSID(25, 250, "threequartersemdash");
        cFFExpertSubsetCharset.addSID(26, 251, "periodsuperior");
        cFFExpertSubsetCharset.addSID(27, 253, "asuperior");
        cFFExpertSubsetCharset.addSID(28, 254, "bsuperior");
        cFFExpertSubsetCharset.addSID(29, 255, "centsuperior");
        cFFExpertSubsetCharset.addSID(30, 256, "dsuperior");
        cFFExpertSubsetCharset.addSID(31, 257, "esuperior");
        cFFExpertSubsetCharset.addSID(32, 258, "isuperior");
        cFFExpertSubsetCharset.addSID(33, BaselineTIFFTagSet.TAG_COMPRESSION, "lsuperior");
        cFFExpertSubsetCharset.addSID(34, 260, "msuperior");
        cFFExpertSubsetCharset.addSID(35, 261, "nsuperior");
        cFFExpertSubsetCharset.addSID(36, BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION, "osuperior");
        cFFExpertSubsetCharset.addSID(37, BaselineTIFFTagSet.TAG_THRESHHOLDING, "rsuperior");
        cFFExpertSubsetCharset.addSID(38, BaselineTIFFTagSet.TAG_CELL_WIDTH, "ssuperior");
        cFFExpertSubsetCharset.addSID(39, BaselineTIFFTagSet.TAG_CELL_LENGTH, "tsuperior");
        cFFExpertSubsetCharset.addSID(40, BaselineTIFFTagSet.TAG_FILL_ORDER, "ff");
        cFFExpertSubsetCharset.addSID(41, 109, "fi");
        cFFExpertSubsetCharset.addSID(42, 110, "fl");
        cFFExpertSubsetCharset.addSID(43, 267, "ffi");
        cFFExpertSubsetCharset.addSID(44, 268, "ffl");
        cFFExpertSubsetCharset.addSID(45, BaselineTIFFTagSet.TAG_DOCUMENT_NAME, "parenleftinferior");
        cFFExpertSubsetCharset.addSID(46, 270, "parenrightinferior");
        cFFExpertSubsetCharset.addSID(47, BaselineTIFFTagSet.TAG_MODEL, "hyphensuperior");
        cFFExpertSubsetCharset.addSID(48, 300, "colonmonetary");
        cFFExpertSubsetCharset.addSID(49, 301, "onefitted");
        cFFExpertSubsetCharset.addSID(50, 302, "rupiah");
        cFFExpertSubsetCharset.addSID(51, 305, "centoldstyle");
        cFFExpertSubsetCharset.addSID(52, 314, "figuredash");
        cFFExpertSubsetCharset.addSID(53, 315, "hypheninferior");
        cFFExpertSubsetCharset.addSID(54, 158, "onequarter");
        cFFExpertSubsetCharset.addSID(55, CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA, "onehalf");
        cFFExpertSubsetCharset.addSID(56, CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384, "threequarters");
        cFFExpertSubsetCharset.addSID(57, BaselineTIFFTagSet.TAG_COLOR_MAP, "oneeighth");
        cFFExpertSubsetCharset.addSID(58, BaselineTIFFTagSet.TAG_HALFTONE_HINTS, "threeeighths");
        cFFExpertSubsetCharset.addSID(59, BaselineTIFFTagSet.TAG_TILE_WIDTH, "fiveeighths");
        cFFExpertSubsetCharset.addSID(60, BaselineTIFFTagSet.TAG_TILE_LENGTH, "seveneighths");
        cFFExpertSubsetCharset.addSID(61, BaselineTIFFTagSet.TAG_TILE_OFFSETS, "onethird");
        cFFExpertSubsetCharset.addSID(62, BaselineTIFFTagSet.TAG_TILE_BYTE_COUNTS, "twothirds");
        cFFExpertSubsetCharset.addSID(63, FaxTIFFTagSet.TAG_BAD_FAX_LINES, "zerosuperior");
        cFFExpertSubsetCharset.addSID(64, CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA, "onesuperior");
        cFFExpertSubsetCharset.addSID(65, CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256, "twosuperior");
        cFFExpertSubsetCharset.addSID(66, CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384, "threesuperior");
        cFFExpertSubsetCharset.addSID(67, FaxTIFFTagSet.TAG_CLEAN_FAX_DATA, "foursuperior");
        cFFExpertSubsetCharset.addSID(68, FaxTIFFTagSet.TAG_CONSECUTIVE_BAD_LINES, "fivesuperior");
        cFFExpertSubsetCharset.addSID(69, 329, "sixsuperior");
        cFFExpertSubsetCharset.addSID(70, 330, "sevensuperior");
        cFFExpertSubsetCharset.addSID(71, 331, "eightsuperior");
        cFFExpertSubsetCharset.addSID(72, BaselineTIFFTagSet.TAG_INK_SET, "ninesuperior");
        cFFExpertSubsetCharset.addSID(73, BaselineTIFFTagSet.TAG_INK_NAMES, "zeroinferior");
        cFFExpertSubsetCharset.addSID(74, BaselineTIFFTagSet.TAG_NUMBER_OF_INKS, "oneinferior");
        cFFExpertSubsetCharset.addSID(75, 335, "twoinferior");
        cFFExpertSubsetCharset.addSID(76, BaselineTIFFTagSet.TAG_DOT_RANGE, "threeinferior");
        cFFExpertSubsetCharset.addSID(77, BaselineTIFFTagSet.TAG_TARGET_PRINTER, "fourinferior");
        cFFExpertSubsetCharset.addSID(78, BaselineTIFFTagSet.TAG_EXTRA_SAMPLES, "fiveinferior");
        cFFExpertSubsetCharset.addSID(79, BaselineTIFFTagSet.TAG_SAMPLE_FORMAT, "sixinferior");
        cFFExpertSubsetCharset.addSID(80, BaselineTIFFTagSet.TAG_S_MIN_SAMPLE_VALUE, "seveninferior");
        cFFExpertSubsetCharset.addSID(81, 341, "eightinferior");
        cFFExpertSubsetCharset.addSID(82, BaselineTIFFTagSet.TAG_TRANSFER_RANGE, "nineinferior");
        cFFExpertSubsetCharset.addSID(83, 343, "centinferior");
        cFFExpertSubsetCharset.addSID(84, 344, "dollarinferior");
        cFFExpertSubsetCharset.addSID(85, 345, "periodinferior");
        cFFExpertSubsetCharset.addSID(86, 346, "commainferior");
    }
}
