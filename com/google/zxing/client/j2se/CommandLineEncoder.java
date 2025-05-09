package com.google.zxing.client.j2se;

import com.beust.jcommander.JCommander;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Locale;
import org.apache.commons.io.FilenameUtils;

/* loaded from: classes4.dex */
public final class CommandLineEncoder {
    private CommandLineEncoder() {
    }

    public static void main(String[] strArr) throws Exception {
        EncoderConfig encoderConfig = new EncoderConfig();
        JCommander jCommander = new JCommander(encoderConfig, strArr);
        jCommander.setProgramName("CommandLineEncoder");
        if (encoderConfig.help) {
            jCommander.usage();
            return;
        }
        String str = encoderConfig.outputFileBase;
        if ("out".equals(str)) {
            str = str + FilenameUtils.EXTENSION_SEPARATOR + encoderConfig.imageFormat.toLowerCase(Locale.ENGLISH);
        }
        EnumMap enumMap = new EnumMap(EncodeHintType.class);
        if (encoderConfig.errorCorrectionLevel != null) {
            enumMap.put((EnumMap) EncodeHintType.ERROR_CORRECTION, (EncodeHintType) encoderConfig.errorCorrectionLevel);
        }
        MatrixToImageWriter.writeToPath(new MultiFormatWriter().encode(encoderConfig.contents.get(0), encoderConfig.barcodeFormat, encoderConfig.width, encoderConfig.height, enumMap), encoderConfig.imageFormat, Paths.get(str, new String[0]));
    }
}
