package org.apache.pdfbox.pdmodel.interactive.form;

import android.util.Log;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.common.PDTextStream;

/* loaded from: classes5.dex */
public final class AppearanceGenerator {
    private AppearanceGenerator() {
    }

    public static void generateFieldAppearances(PDField pDField) throws IOException {
        if (pDField instanceof PDVariableText) {
            AppearanceGeneratorHelper appearanceGeneratorHelper = new AppearanceGeneratorHelper(pDField.getAcroForm(), (PDVariableText) pDField);
            Object value = pDField.getValue();
            if (value instanceof String) {
                appearanceGeneratorHelper.setAppearanceValue((String) value);
                return;
            }
            if (value instanceof PDTextStream) {
                appearanceGeneratorHelper.setAppearanceValue(((PDTextStream) value).getAsString());
                return;
            }
            if (value != null) {
                Log.d("PdfBoxAndroid", "Can't generate the appearance for values typed " + value.getClass().getName() + ".");
                return;
            }
            return;
        }
        Log.d("PdfBoxAndroid", "Unable to generate the field appearance.");
    }
}
