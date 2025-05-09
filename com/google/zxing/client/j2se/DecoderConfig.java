package com.google.zxing.client.j2se;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.validators.PositiveInteger;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
final class DecoderConfig {

    @Parameter(description = "Only output one line per file, omitting the contents", names = {"--brief"})
    boolean brief;

    @Parameter(arity = 4, description = " Only examine cropped region of input image(s)", names = {"--crop"}, validateWith = PositiveInteger.class)
    List<Integer> crop;

    @Parameter(description = "Compare black point algorithms with dump as input.mono.png", names = {"--dump_black_point"})
    boolean dumpBlackPoint;

    @Parameter(description = "Write the decoded contents to input.txt", names = {"--dump_results"})
    boolean dumpResults;

    @Parameter(description = "Prints this help message", help = true, names = {"--help"})
    boolean help;

    @Parameter(description = "(URIs to decode)", required = true, variableArity = true)
    List<String> inputPaths;

    @Parameter(description = "Scans image for multiple barcodes", names = {"--multi"})
    boolean multi;

    @Parameter(description = "Formats to decode, where format is any value in BarcodeFormat", names = {"--possible_formats"}, variableArity = true)
    List<BarcodeFormat> possibleFormats;

    @Parameter(description = "Only decode the UPC and EAN families of barcodes", names = {"--products_only"})
    boolean productsOnly;

    @Parameter(description = "Input image is a pure monochrome barcode image, not a photo", names = {"--pure_barcode"})
    boolean pureBarcode;

    @Parameter(description = "Descend into subdirectories", names = {"--recursive"})
    boolean recursive;

    @Parameter(description = "Use the TRY_HARDER hint, default is normal mode", names = {"--try_harder"})
    boolean tryHarder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<DecodeHintType, ?> buildHints() {
        List list = this.possibleFormats;
        if (list == null || list.isEmpty()) {
            list = new ArrayList();
            list.addAll(Arrays.asList(BarcodeFormat.UPC_A, BarcodeFormat.UPC_E, BarcodeFormat.EAN_13, BarcodeFormat.EAN_8, BarcodeFormat.RSS_14, BarcodeFormat.RSS_EXPANDED));
            if (!this.productsOnly) {
                list.addAll(Arrays.asList(BarcodeFormat.CODE_39, BarcodeFormat.CODE_93, BarcodeFormat.CODE_128, BarcodeFormat.ITF, BarcodeFormat.QR_CODE, BarcodeFormat.DATA_MATRIX, BarcodeFormat.AZTEC, BarcodeFormat.PDF_417, BarcodeFormat.CODABAR, BarcodeFormat.MAXICODE));
            }
        }
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        enumMap.put((EnumMap) DecodeHintType.POSSIBLE_FORMATS, (DecodeHintType) list);
        if (this.tryHarder) {
            enumMap.put((EnumMap) DecodeHintType.TRY_HARDER, (DecodeHintType) Boolean.TRUE);
        }
        if (this.pureBarcode) {
            enumMap.put((EnumMap) DecodeHintType.PURE_BARCODE, (DecodeHintType) Boolean.TRUE);
        }
        return Collections.unmodifiableMap(enumMap);
    }
}
