package co.hyperverge.hypersnapsdk.helpers;

import android.graphics.Bitmap;
import android.util.Log;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;

/* loaded from: classes2.dex */
public class ImageToPDFConverter {
    private static final String TAG = "co.hyperverge.hypersnapsdk.helpers.ImageToPDFConverter";

    public static String convertImageToPDF(Bitmap bitmap, String str) {
        HVLogUtils.d(TAG, "convertImageToPDF() called with: bitmap = [" + bitmap + "], destFilePath = [" + str + "]");
        File file = new File(str);
        try {
            PDDocument pDDocument = new PDDocument();
            PDPage pDPage = new PDPage(new PDRectangle(bitmap.getWidth(), bitmap.getHeight()));
            pDDocument.addPage(pDPage);
            PDPageContentStream pDPageContentStream = new PDPageContentStream(pDDocument, pDPage);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
            pDPageContentStream.drawImage(JPEGFactory.createFromStream(pDDocument, new ByteArrayInputStream(byteArrayOutputStream.toByteArray())), 0.0f, 0.0f);
            pDPageContentStream.close();
            pDDocument.save(file);
            pDDocument.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            String str2 = TAG;
            HVLogUtils.e(str2, "convertImageToPDF(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str2, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        }
    }
}
