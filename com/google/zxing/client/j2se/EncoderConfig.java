package com.google.zxing.client.j2se;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.validators.PositiveInteger;
import com.google.zxing.BarcodeFormat;
import java.util.List;

/* loaded from: classes4.dex */
final class EncoderConfig {
    static final String DEFAULT_OUTPUT_FILE_BASE = "out";

    @Parameter(description = "(Text to encode)", required = true)
    List<String> contents;

    @Parameter(description = "Prints this help message", help = true, names = {"--help"})
    boolean help;

    @Parameter(description = "Format to encode, from BarcodeFormat class. Not all formats are supported", names = {"--barcode_format"})
    BarcodeFormat barcodeFormat = BarcodeFormat.QR_CODE;

    @Parameter(description = "Image output format, such as PNG, JPG, GIF", names = {"--image_format"})
    String imageFormat = "PNG";

    @Parameter(description = "File to write to. Defaults to out.png", names = {"--output"})
    String outputFileBase = DEFAULT_OUTPUT_FILE_BASE;

    @Parameter(description = "Image width", names = {"--width"}, validateWith = PositiveInteger.class)
    int width = 300;

    @Parameter(description = "Image height", names = {"--height"}, validateWith = PositiveInteger.class)
    int height = 300;

    @Parameter(description = "Error correction level for the encoding", names = {"--error_correction_level"})
    String errorCorrectionLevel = null;
}
