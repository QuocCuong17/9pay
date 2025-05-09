package vn.ai.faceauth.sdk.camera.domain.usecase;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import androidx.exifinterface.media.ExifInterface;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.math.MathKt;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.domain.EditPhotoUseCase;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0013\u001a\u00020\u0014J\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lvn/ai/faceauth/sdk/camera/domain/usecase/EditPhotoUseCaseImpl;", "Lvn/ai/faceauth/sdk/domain/EditPhotoUseCase;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "calculateInSampleSize", "", "options", "Landroid/graphics/BitmapFactory$Options;", "reqWidth", "reqHeight", "editPhotoFile", "", "photoFile", "Ljava/io/File;", "getExifInterface", "Landroidx/exifinterface/media/ExifInterface;", "input", "Ljava/io/InputStream;", "selectedImage", "Landroid/net/Uri;", "handleSamplingAndRotationBitmap", "Landroid/graphics/Bitmap;", "rotateImage", "img", "degree", "", "rotateImageIfRequired", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class EditPhotoUseCaseImpl implements EditPhotoUseCase {
    private final Context context;

    public EditPhotoUseCaseImpl(Context context) {
        this.context = context;
    }

    private final int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int i = options.outHeight;
        int i2 = options.outWidth;
        if (i <= reqHeight && i2 <= reqWidth) {
            return 1;
        }
        int roundToInt = MathKt.roundToInt(i / reqHeight);
        int roundToInt2 = MathKt.roundToInt(i2 / reqWidth);
        if (roundToInt >= roundToInt2) {
            roundToInt = roundToInt2;
        }
        while ((i2 * i) / (roundToInt * roundToInt) > reqWidth * reqHeight * 2) {
            roundToInt++;
        }
        return roundToInt;
    }

    private final ExifInterface getExifInterface(InputStream input, Uri selectedImage) {
        if (!(input != null && Build.VERSION.SDK_INT > 23)) {
            input = null;
        }
        if (input != null) {
            return new ExifInterface(input);
        }
        String path = selectedImage.getPath();
        if (path != null) {
            return new ExifInterface(path);
        }
        return null;
    }

    private final Bitmap rotateImage(Bitmap img, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap createBitmap = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return createBitmap;
    }

    @Override // vn.ai.faceauth.sdk.domain.EditPhotoUseCase
    public void editPhotoFile(File photoFile) {
        try {
            Result.Companion companion = Result.INSTANCE;
            Bitmap handleSamplingAndRotationBitmap = handleSamplingAndRotationBitmap(Uri.fromFile(photoFile));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (handleSamplingAndRotationBitmap != null) {
                handleSamplingAndRotationBitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
            }
            FilesKt.writeBytes(photoFile, byteArrayOutputStream.toByteArray());
            Result.m1202constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
    }

    public final Bitmap handleSamplingAndRotationBitmap(Uri selectedImage) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        InputStream openInputStream = this.context.getContentResolver().openInputStream(selectedImage);
        BitmapFactory.decodeStream(openInputStream, null, options);
        if (openInputStream != null) {
            openInputStream.close();
        }
        if (options.outWidth < 100 || options.outHeight < 100) {
            return null;
        }
        options.inSampleSize = calculateInSampleSize(options, 1024, 1024);
        options.inJustDecodeBounds = false;
        Bitmap decodeStream = BitmapFactory.decodeStream(this.context.getContentResolver().openInputStream(selectedImage), null, options);
        if (decodeStream != null) {
            return rotateImageIfRequired(decodeStream, selectedImage);
        }
        return null;
    }

    public final Bitmap rotateImageIfRequired(Bitmap img, Uri selectedImage) {
        float f;
        ExifInterface exifInterface = getExifInterface(this.context.getContentResolver().openInputStream(selectedImage), selectedImage);
        if (exifInterface == null) {
            return null;
        }
        int attributeInt = exifInterface.getAttributeInt(tfwhgw.rnigpa(188), 1);
        if (attributeInt == 3) {
            f = 180.0f;
        } else if (attributeInt == 6) {
            f = 90.0f;
        } else {
            if (attributeInt != 8) {
                return img;
            }
            f = 270.0f;
        }
        return rotateImage(img, f);
    }
}
