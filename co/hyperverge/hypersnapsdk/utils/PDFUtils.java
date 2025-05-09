package co.hyperverge.hypersnapsdk.utils;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;

/* loaded from: classes2.dex */
public class PDFUtils {
    private static final String TAG = "co.hyperverge.hypersnapsdk.utils.PDFUtils";

    private PDFUtils() {
    }

    public static List<Bitmap> asBitmaps(File file) {
        HVLogUtils.d(TAG, "asBitmaps() called with: file = [" + file + "]");
        ArrayList arrayList = new ArrayList();
        try {
            PdfRenderer pdfRenderer = new PdfRenderer(ParcelFileDescriptor.open(file, 268435456));
            int pageCount = pdfRenderer.getPageCount();
            for (int i = 0; i < pageCount; i++) {
                PdfRenderer.Page openPage = pdfRenderer.openPage(i);
                Bitmap createBitmap = Bitmap.createBitmap(openPage.getWidth(), openPage.getHeight(), Bitmap.Config.ARGB_8888);
                openPage.render(createBitmap, null, null, 1);
                arrayList.add(createBitmap);
                openPage.close();
            }
            pdfRenderer.close();
        } catch (Exception | OutOfMemoryError e) {
            String str = TAG;
            HVLogUtils.e(str, "asBitmaps() with: file = [" + file + "]: exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
        }
        return arrayList;
    }

    public static void checkIfPDFIsReadable(Uri uri, ContentResolver contentResolver) throws Exception {
        try {
            ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(uri, PDPageLabelRange.STYLE_ROMAN_LOWER);
            try {
                if (openFileDescriptor != null) {
                    new PdfRenderer(openFileDescriptor).close();
                    if (openFileDescriptor != null) {
                        openFileDescriptor.close();
                        return;
                    }
                    return;
                }
                throw new NullPointerException("parcelFileDescriptor is null");
            } finally {
            }
        } catch (Exception e) {
            Log.e(TAG, "exception: " + e.getMessage());
            throw e;
        }
    }
}
