package com.google.zxing.aztec;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.aztec.decoder.Decoder;
import com.google.zxing.aztec.detector.Detector;
import com.google.zxing.common.DecoderResult;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class AztecReader implements Reader {
    @Override // com.google.zxing.Reader
    public void reset() {
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005e A[LOOP:0: B:24:0x005c->B:25:0x005e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0031  */
    @Override // com.google.zxing.Reader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        ResultPoint[] resultPointArr;
        ResultPoint[] resultPointArr2;
        FormatException formatException;
        ResultPoint[] resultPointArr3;
        List<byte[]> byteSegments;
        String eCLevel;
        ResultPointCallback resultPointCallback;
        Detector detector = new Detector(binaryBitmap.getBlackMatrix());
        DecoderResult decoderResult = null;
        try {
            AztecDetectorResult detect = detector.detect(false);
            resultPointArr = detect.getPoints();
            try {
                resultPointArr2 = resultPointArr;
                formatException = null;
                decoderResult = new Decoder().decode(detect);
                e = null;
            } catch (FormatException e) {
                e = e;
                resultPointArr2 = resultPointArr;
                formatException = e;
                e = null;
                if (decoderResult == null) {
                }
                resultPointArr3 = resultPointArr2;
                if (map != null) {
                    while (r11 < r0) {
                    }
                }
                Result result = new Result(decoderResult.getText(), decoderResult.getRawBytes(), decoderResult.getNumBits(), resultPointArr3, BarcodeFormat.AZTEC, System.currentTimeMillis());
                byteSegments = decoderResult.getByteSegments();
                if (byteSegments != null) {
                }
                eCLevel = decoderResult.getECLevel();
                if (eCLevel != null) {
                }
                result.putMetadata(ResultMetadataType.SYMBOLOGY_IDENTIFIER, "]z" + decoderResult.getSymbologyModifier());
                return result;
            } catch (NotFoundException e2) {
                e = e2;
                resultPointArr2 = resultPointArr;
                formatException = null;
                if (decoderResult == null) {
                }
                resultPointArr3 = resultPointArr2;
                if (map != null) {
                }
                Result result2 = new Result(decoderResult.getText(), decoderResult.getRawBytes(), decoderResult.getNumBits(), resultPointArr3, BarcodeFormat.AZTEC, System.currentTimeMillis());
                byteSegments = decoderResult.getByteSegments();
                if (byteSegments != null) {
                }
                eCLevel = decoderResult.getECLevel();
                if (eCLevel != null) {
                }
                result2.putMetadata(ResultMetadataType.SYMBOLOGY_IDENTIFIER, "]z" + decoderResult.getSymbologyModifier());
                return result2;
            }
        } catch (FormatException e3) {
            e = e3;
            resultPointArr = null;
        } catch (NotFoundException e4) {
            e = e4;
            resultPointArr = null;
        }
        if (decoderResult == null) {
            try {
                AztecDetectorResult detect2 = detector.detect(true);
                resultPointArr2 = detect2.getPoints();
                decoderResult = new Decoder().decode(detect2);
            } catch (FormatException | NotFoundException e5) {
                if (e != null) {
                    throw e;
                }
                if (formatException != null) {
                    throw formatException;
                }
                throw e5;
            }
        }
        resultPointArr3 = resultPointArr2;
        if (map != null && (resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK)) != null) {
            for (ResultPoint resultPoint : resultPointArr3) {
                resultPointCallback.foundPossibleResultPoint(resultPoint);
            }
        }
        Result result22 = new Result(decoderResult.getText(), decoderResult.getRawBytes(), decoderResult.getNumBits(), resultPointArr3, BarcodeFormat.AZTEC, System.currentTimeMillis());
        byteSegments = decoderResult.getByteSegments();
        if (byteSegments != null) {
            result22.putMetadata(ResultMetadataType.BYTE_SEGMENTS, byteSegments);
        }
        eCLevel = decoderResult.getECLevel();
        if (eCLevel != null) {
            result22.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, eCLevel);
        }
        result22.putMetadata(ResultMetadataType.SYMBOLOGY_IDENTIFIER, "]z" + decoderResult.getSymbologyModifier());
        return result22;
    }
}
