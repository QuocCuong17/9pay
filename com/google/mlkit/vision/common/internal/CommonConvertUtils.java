package com.google.mlkit.vision.common.internal;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.Image;
import android.os.SystemClock;
import com.google.android.gms.internal.mlkit_vision_common.zzlg;
import com.google.android.gms.internal.mlkit_vision_common.zzli;
import com.google.android.gms.internal.mlkit_vision_common.zzlk;
import com.google.android.odml.image.BitmapExtractor;
import com.google.android.odml.image.ByteBufferExtractor;
import com.google.android.odml.image.ImageProperties;
import com.google.android.odml.image.MediaImageExtractor;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.vision.common.InputImage;
import java.nio.ByteBuffer;
import java.util.List;

/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes4.dex */
public class CommonConvertUtils {
    public static int convertToAndroidImageFormat(int i) {
        int i2 = 17;
        if (i != 17) {
            i2 = 35;
            if (i != 35) {
                i2 = InputImage.IMAGE_FORMAT_YV12;
                if (i != 842094169) {
                    return 0;
                }
            }
        }
        return i2;
    }

    public static int convertToMVRotation(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 90) {
            return 1;
        }
        if (i == 180) {
            return 2;
        }
        if (i == 270) {
            return 3;
        }
        throw new IllegalArgumentException("Invalid rotation: " + i);
    }

    public static void transformPointArray(Point[] pointArr, Matrix matrix) {
        int length = pointArr.length;
        float[] fArr = new float[length + length];
        for (int i = 0; i < pointArr.length; i++) {
            int i2 = i + i;
            fArr[i2] = pointArr[i].x;
            fArr[i2 + 1] = pointArr[i].y;
        }
        matrix.mapPoints(fArr);
        for (int i3 = 0; i3 < pointArr.length; i3++) {
            int i4 = i3 + i3;
            pointArr[i3].set((int) fArr[i4], (int) fArr[i4 + 1]);
        }
    }

    public static void transformPointF(PointF pointF, Matrix matrix) {
        float[] fArr = {pointF.x, pointF.y};
        matrix.mapPoints(fArr);
        pointF.set(fArr[0], fArr[1]);
    }

    public static void transformPointList(List<PointF> list, Matrix matrix) {
        int size = list.size();
        float[] fArr = new float[size + size];
        for (int i = 0; i < list.size(); i++) {
            int i2 = i + i;
            fArr[i2] = list.get(i).x;
            fArr[i2 + 1] = list.get(i).y;
        }
        matrix.mapPoints(fArr);
        for (int i3 = 0; i3 < list.size(); i3++) {
            int i4 = i3 + i3;
            list.get(i3).set(fArr[i4], fArr[i4 + 1]);
        }
    }

    public static void transformRect(Rect rect, Matrix matrix) {
        RectF rectF = new RectF(rect);
        matrix.mapRect(rectF);
        rect.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InputImage zza(MlImage mlImage) {
        Integer num;
        int limit;
        ImageProperties imageProperties = mlImage.getContainedImageProperties().get(0);
        int storageType = imageProperties.getStorageType();
        InputImage inputImage = null;
        if (storageType == 1) {
            Bitmap extract = BitmapExtractor.extract(mlImage);
            zzb(-1, 1, SystemClock.elapsedRealtime(), mlImage.getHeight(), mlImage.getWidth(), extract.getAllocationByteCount(), mlImage.getRotation());
            inputImage = InputImage.fromBitmap(extract, mlImage.getRotation());
        } else if (storageType == 2) {
            ByteBuffer extract2 = ByteBufferExtractor.extract(mlImage);
            int imageFormat = imageProperties.getImageFormat();
            if (imageFormat != 4) {
                num = imageFormat != 5 ? null : Integer.valueOf(InputImage.IMAGE_FORMAT_YV12);
            } else {
                num = 17;
            }
            if (num != null) {
                zzb(num.intValue(), 3, SystemClock.elapsedRealtime(), mlImage.getHeight(), mlImage.getWidth(), extract2.limit(), mlImage.getRotation());
                inputImage = InputImage.fromByteBuffer(extract2, mlImage.getWidth(), mlImage.getHeight(), mlImage.getRotation(), num.intValue());
            }
        } else if (storageType == 3) {
            Image extract3 = MediaImageExtractor.extract(mlImage);
            if (extract3.getFormat() == 256) {
                limit = extract3.getPlanes()[0].getBuffer().limit();
            } else {
                limit = (extract3.getPlanes()[0].getBuffer().limit() * 3) / 2;
            }
            zzb(extract3.getFormat(), 5, SystemClock.elapsedRealtime(), mlImage.getHeight(), mlImage.getWidth(), limit, mlImage.getRotation());
            inputImage = InputImage.fromMediaImage(extract3, mlImage.getRotation());
        }
        if (inputImage != null) {
            zzlk.zza();
        }
        return inputImage;
    }

    private static void zzb(int i, int i2, long j, int i3, int i4, int i5, int i6) {
        zzli.zzb(zzlg.zzb("vision-common"), i, i2, j, i3, i4, i5, i6);
    }
}
