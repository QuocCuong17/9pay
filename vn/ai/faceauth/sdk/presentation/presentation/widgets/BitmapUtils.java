package vn.ai.faceauth.sdk.presentation.presentation.widgets;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import androidx.camera.core.ImageProxy;
import androidx.exifinterface.media.ExifInterface;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.presentation.presentation.widgets.FrameMetadata;

/* loaded from: classes6.dex */
public class BitmapUtils {
    private static String TAG;

    static {
        tfwhgw.rnl(BitmapUtils.class, BaselineTIFFTagSet.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, BaselineTIFFTagSet.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
    }

    private static boolean areUVPlanesNV21(Image.Plane[] planeArr, int i, int i2) {
        ByteBuffer buffer = planeArr[1].getBuffer();
        ByteBuffer buffer2 = planeArr[2].getBuffer();
        int position = buffer2.position();
        int limit = buffer.limit();
        buffer2.position(position + 1);
        buffer.limit(limit - 1);
        boolean z = buffer2.remaining() == (((i * i2) * 2) / 4) - 2 && buffer2.compareTo(buffer) == 0;
        buffer2.position(position);
        buffer.limit(limit);
        return z;
    }

    public static Bitmap getBitmap(ImageProxy imageProxy) {
        return getBitmap(yuv420ThreePlanesToNV21(imageProxy.getImage().getPlanes(), imageProxy.getWidth(), imageProxy.getHeight()), new FrameMetadata.Builder().setWidth(imageProxy.getWidth()).setHeight(imageProxy.getHeight()).setRotation(imageProxy.getImageInfo().getRotationDegrees()).build());
    }

    public static Bitmap getBitmap(ByteBuffer byteBuffer, FrameMetadata frameMetadata) {
        byteBuffer.rewind();
        int limit = byteBuffer.limit();
        byte[] bArr = new byte[limit];
        byteBuffer.get(bArr, 0, limit);
        try {
            YuvImage yuvImage = new YuvImage(bArr, 17, frameMetadata.getWidth(), frameMetadata.getHeight(), null);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            yuvImage.compressToJpeg(new Rect(0, 0, frameMetadata.getWidth(), frameMetadata.getHeight()), 80, byteArrayOutputStream);
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
            byteArrayOutputStream.close();
            return rotateBitmap(decodeByteArray, frameMetadata.getRotation(), false, false);
        } catch (Exception e) {
            Log.e(tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_MAKE), tfwhgw.rnigpa(270) + e.getMessage());
            return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static Bitmap getBitmapFromContentUri(ContentResolver contentResolver, Uri uri) {
        boolean z;
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri);
        if (bitmap == null) {
            return null;
        }
        int exifOrientationTag = getExifOrientationTag(contentResolver, uri);
        int i = -90;
        boolean z2 = true;
        int i2 = 0;
        switch (exifOrientationTag) {
            case 2:
                i = 0;
                z = z2;
                z2 = false;
                i2 = i;
                break;
            case 3:
                i = 180;
                z2 = false;
                z = z2;
                z2 = false;
                i2 = i;
                break;
            case 4:
                z = false;
                break;
            case 5:
                i = 90;
                z = z2;
                z2 = false;
                i2 = i;
                break;
            case 6:
                i = 90;
                z2 = false;
                z = z2;
                z2 = false;
                i2 = i;
                break;
            case 7:
                z = z2;
                z2 = false;
                i2 = i;
                break;
            case 8:
                z2 = false;
                z = z2;
                z2 = false;
                i2 = i;
                break;
            default:
                i = 0;
                z2 = false;
                z = z2;
                z2 = false;
                i2 = i;
                break;
        }
        return rotateBitmap(bitmap, i2, z, z2);
    }

    private static int getExifOrientationTag(ContentResolver contentResolver, Uri uri) {
        if (!tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_MODEL).equals(uri.getScheme())) {
            if (!tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_STRIP_OFFSETS).equals(uri.getScheme())) {
                return 0;
            }
        }
        try {
            InputStream openInputStream = contentResolver.openInputStream(uri);
            if (openInputStream == null) {
                if (openInputStream != null) {
                    openInputStream.close();
                }
                return 0;
            }
            try {
                ExifInterface exifInterface = new ExifInterface(openInputStream);
                openInputStream.close();
                return exifInterface.getAttributeInt(tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_ORIENTATION), 1);
            } finally {
            }
        } catch (IOException e) {
            Log.e(tfwhgw.rnigpa(276), tfwhgw.rnigpa(275) + uri, e);
            return 0;
        }
    }

    private static Bitmap rotateBitmap(Bitmap bitmap, int i, boolean z, boolean z2) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        matrix.postScale(z ? -1.0f : 1.0f, z2 ? -1.0f : 1.0f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    private static void unpackPlane(Image.Plane plane, int i, int i2, byte[] bArr, int i3, int i4) {
        ByteBuffer buffer = plane.getBuffer();
        buffer.rewind();
        int rowStride = ((plane.getRowStride() + buffer.limit()) - 1) / plane.getRowStride();
        if (rowStride == 0) {
            return;
        }
        int i5 = i / (i2 / rowStride);
        int i6 = 0;
        for (int i7 = 0; i7 < rowStride; i7++) {
            int i8 = i6;
            for (int i9 = 0; i9 < i5; i9++) {
                bArr[i3] = buffer.get(i8);
                i3 += i4;
                i8 += plane.getPixelStride();
            }
            i6 += plane.getRowStride();
        }
    }

    private static ByteBuffer yuv420ThreePlanesToNV21(Image.Plane[] planeArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr = new byte[((i3 / 4) * 2) + i3];
        if (areUVPlanesNV21(planeArr, i, i2)) {
            planeArr[0].getBuffer().get(bArr, 0, i3);
            ByteBuffer buffer = planeArr[1].getBuffer();
            planeArr[2].getBuffer().get(bArr, i3, 1);
            buffer.get(bArr, i3 + 1, ((i3 * 2) / 4) - 1);
        } else {
            unpackPlane(planeArr[0], i, i2, bArr, 0, 1);
            unpackPlane(planeArr[1], i, i2, bArr, i3 + 1, 2);
            unpackPlane(planeArr[2], i, i2, bArr, i3, 2);
        }
        return ByteBuffer.wrap(bArr);
    }
}
