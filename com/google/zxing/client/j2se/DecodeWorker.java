package com.google.zxing.client.j2se;

import com.google.firebase.sessions.settings.RemoteSettings;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ResultParser;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.GenericMultipleBarcodeReader;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Callable;
import javax.imageio.ImageIO;
import org.apache.commons.io.IOUtils;

/* loaded from: classes4.dex */
final class DecodeWorker implements Callable<Integer> {
    private static final int BLACK = -16777216;
    private static final int RED = -65536;
    private static final int WHITE = -1;
    private final DecoderConfig config;
    private final Map<DecodeHintType, ?> hints;
    private final Queue<URI> inputs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DecodeWorker(DecoderConfig decoderConfig, Queue<URI> queue) {
        this.config = decoderConfig;
        this.inputs = queue;
        this.hints = decoderConfig.buildHints();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Integer call() throws IOException {
        int i = 0;
        while (true) {
            URI poll = this.inputs.poll();
            if (poll != null) {
                Result[] decode = decode(poll, this.hints);
                if (decode != null) {
                    i++;
                    if (this.config.dumpResults) {
                        dumpResult(poll, decode);
                    }
                }
            } else {
                return Integer.valueOf(i);
            }
        }
    }

    private static Path buildOutputPath(URI uri, String str) throws IOException {
        Path realPath;
        String str2;
        String str3;
        if ("file".equals(uri.getScheme())) {
            Path path = Paths.get(uri);
            realPath = path.getParent();
            str2 = path.getFileName().toString();
        } else {
            realPath = Paths.get(".", new String[0]).toRealPath(new LinkOption[0]);
            str2 = uri.getPath().split(RemoteSettings.FORWARD_SLASH_STRING)[r4.length - 1];
        }
        int lastIndexOf = str2.lastIndexOf(46);
        if (lastIndexOf > 0) {
            str3 = str2.substring(0, lastIndexOf) + str;
        } else {
            str3 = str2 + str;
        }
        return realPath.resolve(str3);
    }

    private static void dumpResult(URI uri, Result... resultArr) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (Result result : resultArr) {
            arrayList.add(result.getText());
        }
        Files.write(buildOutputPath(uri, ".txt"), arrayList, StandardCharsets.UTF_8, new OpenOption[0]);
    }

    private Result[] decode(URI uri, Map<DecodeHintType, ?> map) throws IOException {
        BufferedImageLuminanceSource bufferedImageLuminanceSource;
        BufferedImage readImage = ImageReader.readImage(uri);
        if (this.config.crop == null) {
            bufferedImageLuminanceSource = new BufferedImageLuminanceSource(readImage);
        } else {
            List<Integer> list = this.config.crop;
            bufferedImageLuminanceSource = new BufferedImageLuminanceSource(readImage, list.get(0).intValue(), list.get(1).intValue(), list.get(2).intValue(), list.get(3).intValue());
        }
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(bufferedImageLuminanceSource));
        if (this.config.dumpBlackPoint) {
            dumpBlackPoint(uri, readImage, binaryBitmap);
        }
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        try {
            Result[] decodeMultiple = this.config.multi ? new GenericMultipleBarcodeReader(multiFormatReader).decodeMultiple(binaryBitmap, map) : new Result[]{multiFormatReader.decode(binaryBitmap, map)};
            if (this.config.brief) {
                System.out.println(uri + ": Success");
            } else {
                StringWriter stringWriter = new StringWriter();
                for (int i = 0; i < decodeMultiple.length; i++) {
                    Result result = decodeMultiple[i];
                    ParsedResult parseResult = ResultParser.parseResult(result);
                    stringWriter.write(uri + " (format: " + result.getBarcodeFormat() + ", type: " + parseResult.getType() + "):\nRaw result:\n" + result.getText() + "\nParsed result:\n" + parseResult.getDisplayResult() + IOUtils.LINE_SEPARATOR_UNIX);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Found ");
                    sb.append(result.getResultPoints().length);
                    sb.append(" result points.\n");
                    stringWriter.write(sb.toString());
                    for (int i2 = 0; i2 < result.getResultPoints().length; i2++) {
                        ResultPoint resultPoint = result.getResultPoints()[i2];
                        stringWriter.write("  Point " + i2 + ": (" + resultPoint.getX() + ',' + resultPoint.getY() + ')');
                        if (i2 != result.getResultPoints().length - 1) {
                            stringWriter.write(10);
                        }
                    }
                    if (i != decodeMultiple.length - 1) {
                        stringWriter.write(10);
                    }
                }
                System.out.println(stringWriter);
            }
            return decodeMultiple;
        } catch (NotFoundException unused) {
            System.out.println(uri + ": No barcode found");
            return null;
        }
    }

    private static void dumpBlackPoint(URI uri, BufferedImage bufferedImage, BinaryBitmap binaryBitmap) throws IOException {
        int width = binaryBitmap.getWidth();
        int height = binaryBitmap.getHeight();
        int i = width * 3;
        int[] iArr = new int[i * height];
        int[] iArr2 = new int[width];
        for (int i2 = 0; i2 < height; i2++) {
            bufferedImage.getRGB(0, i2, width, 1, iArr2, 0, width);
            System.arraycopy(iArr2, 0, iArr, i2 * i, width);
        }
        BitArray bitArray = new BitArray(width);
        for (int i3 = 0; i3 < height; i3++) {
            try {
                bitArray = binaryBitmap.getBlackRow(i3, bitArray);
                int i4 = (i3 * i) + width;
                for (int i5 = 0; i5 < width; i5++) {
                    iArr[i4 + i5] = bitArray.get(i5) ? -16777216 : -1;
                }
            } catch (NotFoundException unused) {
                int i6 = (i3 * i) + width;
                Arrays.fill(iArr, i6, i6 + width, -65536);
            }
        }
        for (int i7 = 0; i7 < height; i7++) {
            try {
                BitMatrix blackMatrix = binaryBitmap.getBlackMatrix();
                int i8 = (i7 * i) + (width * 2);
                for (int i9 = 0; i9 < width; i9++) {
                    iArr[i8 + i9] = blackMatrix.get(i9, i7) ? -16777216 : -1;
                }
            } catch (NotFoundException unused2) {
            }
        }
        writeResultImage(i, height, iArr, uri, ".mono.png");
    }

    private static void writeResultImage(int i, int i2, int[] iArr, URI uri, String str) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(i, i2, 2);
        bufferedImage.setRGB(0, 0, i, i2, iArr, 0, i);
        Path buildOutputPath = buildOutputPath(uri, str);
        try {
            if (ImageIO.write(bufferedImage, "png", buildOutputPath.toFile())) {
                return;
            }
            System.err.println("Could not encode an image to " + buildOutputPath);
        } catch (IOException unused) {
            System.err.println("Could not write to " + buildOutputPath);
        }
    }
}
