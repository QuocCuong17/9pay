package com.google.zxing.client.j2se;

import com.google.zxing.common.BitMatrix;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import javax.imageio.ImageIO;

/* loaded from: classes4.dex */
public final class MatrixToImageWriter {
    private static final MatrixToImageConfig DEFAULT_CONFIG = new MatrixToImageConfig();

    private MatrixToImageWriter() {
    }

    public static BufferedImage toBufferedImage(BitMatrix bitMatrix) {
        return toBufferedImage(bitMatrix, DEFAULT_CONFIG);
    }

    public static BufferedImage toBufferedImage(BitMatrix bitMatrix, MatrixToImageConfig matrixToImageConfig) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, matrixToImageConfig.getBufferedImageColorModel());
        int pixelOnColor = matrixToImageConfig.getPixelOnColor();
        int pixelOffColor = matrixToImageConfig.getPixelOffColor();
        int[] iArr = new int[width * height];
        int i = 0;
        for (int i2 = 0; i2 < height; i2++) {
            int i3 = 0;
            while (i3 < width) {
                int i4 = i + 1;
                iArr[i] = bitMatrix.get(i3, i2) ? pixelOnColor : pixelOffColor;
                i3++;
                i = i4;
            }
        }
        bufferedImage.setRGB(0, 0, width, height, iArr, 0, width);
        return bufferedImage;
    }

    @Deprecated
    public static void writeToFile(BitMatrix bitMatrix, String str, File file) throws IOException {
        writeToPath(bitMatrix, str, file.toPath());
    }

    public static void writeToPath(BitMatrix bitMatrix, String str, Path path) throws IOException {
        writeToPath(bitMatrix, str, path, DEFAULT_CONFIG);
    }

    @Deprecated
    public static void writeToFile(BitMatrix bitMatrix, String str, File file, MatrixToImageConfig matrixToImageConfig) throws IOException {
        writeToPath(bitMatrix, str, file.toPath(), matrixToImageConfig);
    }

    public static void writeToPath(BitMatrix bitMatrix, String str, Path path, MatrixToImageConfig matrixToImageConfig) throws IOException {
        if (ImageIO.write(toBufferedImage(bitMatrix, matrixToImageConfig), str, path.toFile())) {
            return;
        }
        throw new IOException("Could not write an image of format " + str + " to " + path);
    }

    public static void writeToStream(BitMatrix bitMatrix, String str, OutputStream outputStream) throws IOException {
        writeToStream(bitMatrix, str, outputStream, DEFAULT_CONFIG);
    }

    public static void writeToStream(BitMatrix bitMatrix, String str, OutputStream outputStream, MatrixToImageConfig matrixToImageConfig) throws IOException {
        if (ImageIO.write(toBufferedImage(bitMatrix, matrixToImageConfig), str, outputStream)) {
            return;
        }
        throw new IOException("Could not write an image of format " + str);
    }
}
