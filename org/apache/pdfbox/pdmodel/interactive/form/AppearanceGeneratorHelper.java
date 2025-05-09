package org.apache.pdfbox.pdmodel.interactive.form;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdfwriter.ContentStreamWriter;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDFontDescriptor;
import org.apache.pdfbox.pdmodel.interactive.action.PDFormFieldAdditionalActions;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceEntry;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import org.apache.pdfbox.pdmodel.interactive.form.PlainTextFormatter;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class AppearanceGeneratorHelper {
    private static final float GLYPH_TO_PDF_SCALE = 1000.0f;
    private final PDAcroForm acroForm;
    private final DefaultAppearanceHandler defaultAppearanceHandler;
    private final PDVariableText parent;
    private String value;
    private List<COSObjectable> widgets;

    public AppearanceGeneratorHelper(PDAcroForm pDAcroForm, PDVariableText pDVariableText) throws IOException {
        this.widgets = new ArrayList();
        this.acroForm = pDAcroForm;
        this.parent = pDVariableText;
        List<COSObjectable> kids = pDVariableText.getKids();
        this.widgets = kids;
        if (kids == null) {
            ArrayList arrayList = new ArrayList();
            this.widgets = arrayList;
            arrayList.add(pDVariableText.getWidget());
        }
        this.defaultAppearanceHandler = new DefaultAppearanceHandler(getDefaultAppearance());
    }

    private String getDefaultAppearance() {
        return this.parent.getDefaultAppearance();
    }

    private List<Object> getStreamTokens(PDAppearanceStream pDAppearanceStream) throws IOException {
        return pDAppearanceStream != null ? getStreamTokens(pDAppearanceStream.getCOSStream()) : new ArrayList();
    }

    private List<Object> getStreamTokens(COSStream cOSStream) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (cOSStream == null) {
            return arrayList;
        }
        PDFStreamParser pDFStreamParser = new PDFStreamParser(cOSStream);
        pDFStreamParser.parse();
        List<Object> tokens = pDFStreamParser.getTokens();
        pDFStreamParser.close();
        return tokens;
    }

    private boolean containsMarkedContent(List<Object> list) {
        return list.contains(Operator.getOperator("BMC"));
    }

    public void setAppearanceValue(String str) throws IOException {
        PDAnnotationWidget pDAnnotationWidget;
        PDField pDField;
        this.value = str;
        for (COSObjectable cOSObjectable : this.widgets) {
            if (cOSObjectable instanceof PDField) {
                pDField = (PDField) cOSObjectable;
                pDAnnotationWidget = pDField.getWidget();
            } else {
                pDAnnotationWidget = (PDAnnotationWidget) cOSObjectable;
                pDField = null;
            }
            PDFormFieldAdditionalActions actions = pDField != null ? pDField.getActions() : null;
            if (actions == null || actions.getF() == null || pDAnnotationWidget.getDictionary().getDictionaryObject(COSName.AP) != null) {
                PDAppearanceDictionary appearance = pDAnnotationWidget.getAppearance();
                if (appearance == null) {
                    appearance = new PDAppearanceDictionary();
                    pDAnnotationWidget.setAppearance(appearance);
                }
                PDAppearanceEntry normalAppearance = appearance.getNormalAppearance();
                PDAppearanceStream appearanceStream = normalAppearance.isStream() ? normalAppearance.getAppearanceStream() : null;
                if (appearanceStream == null) {
                    PDAppearanceStream pDAppearanceStream = new PDAppearanceStream(this.acroForm.getDocument().getDocument().createCOSStream());
                    pDAppearanceStream.setBBox(pDAnnotationWidget.getRectangle().createRetranslatedRectangle());
                    appearance.setNormalAppearance(pDAppearanceStream);
                    appearanceStream = pDAppearanceStream;
                }
                List<Object> streamTokens = getStreamTokens(appearanceStream);
                PDFont fontAndUpdateResources = getFontAndUpdateResources(appearanceStream);
                if (!containsMarkedContent(streamTokens)) {
                    createAppearanceContent(streamTokens, pDAnnotationWidget, fontAndUpdateResources, appearanceStream);
                } else {
                    updateAppearanceContent(streamTokens, pDAnnotationWidget, fontAndUpdateResources, appearanceStream);
                }
            }
        }
    }

    private void createAppearanceContent(List<Object> list, PDAnnotationWidget pDAnnotationWidget, PDFont pDFont, PDAppearanceStream pDAppearanceStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new ContentStreamWriter(byteArrayOutputStream).writeTokens(list);
        byteArrayOutputStream.write("/Tx BMC\n".getBytes("ISO-8859-1"));
        insertGeneratedAppearance(resolveBoundingBox(pDAnnotationWidget, pDAppearanceStream), byteArrayOutputStream, pDFont, list);
        byteArrayOutputStream.write("EMC".getBytes("ISO-8859-1"));
        byteArrayOutputStream.close();
        writeToStream(byteArrayOutputStream.toByteArray(), pDAppearanceStream);
    }

    private void updateAppearanceContent(List<Object> list, PDAnnotationWidget pDAnnotationWidget, PDFont pDFont, PDAppearanceStream pDAppearanceStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ContentStreamWriter contentStreamWriter = new ContentStreamWriter(byteArrayOutputStream);
        PDRectangle resolveBoundingBox = resolveBoundingBox(pDAnnotationWidget, pDAppearanceStream);
        int indexOf = list.indexOf(Operator.getOperator("BMC"));
        int indexOf2 = list.indexOf(Operator.getOperator("EMC"));
        contentStreamWriter.writeTokens(list, 0, indexOf + 1);
        byteArrayOutputStream.write(IOUtils.LINE_SEPARATOR_UNIX.getBytes("ISO-8859-1"));
        insertGeneratedAppearance(resolveBoundingBox, byteArrayOutputStream, pDFont, list);
        if (indexOf2 != -1) {
            contentStreamWriter.writeTokens(list, indexOf2, list.size());
        }
        byteArrayOutputStream.close();
        writeToStream(byteArrayOutputStream.toByteArray(), pDAppearanceStream);
    }

    private void insertGeneratedAppearance(PDRectangle pDRectangle, OutputStream outputStream, PDFont pDFont, List<Object> list) throws IOException {
        AppearancePrimitivesComposer appearancePrimitivesComposer = new AppearancePrimitivesComposer(outputStream);
        float lineWidth = getLineWidth(list);
        PDRectangle applyPadding = applyPadding(pDRectangle, Math.max(1.0f, lineWidth));
        PDRectangle applyPadding2 = applyPadding(applyPadding, Math.max(1.0f, lineWidth));
        appearancePrimitivesComposer.addRect(applyPadding);
        appearancePrimitivesComposer.clip();
        appearancePrimitivesComposer.beginText();
        float calculateFontSize = calculateFontSize(pDFont, applyPadding2);
        if (!this.defaultAppearanceHandler.getTokens().isEmpty()) {
            this.defaultAppearanceHandler.setFontSize(calculateFontSize);
            new ContentStreamWriter(outputStream).writeTokens(this.defaultAppearanceHandler.getTokens());
        }
        float calculateVerticalOffset = calculateVerticalOffset(applyPadding, applyPadding2, pDFont, calculateFontSize);
        if (!isMultiLine()) {
            appearancePrimitivesComposer.newLineAtOffset(calculateHorizontalOffset(applyPadding2, pDFont, calculateFontSize), calculateVerticalOffset);
            appearancePrimitivesComposer.showText(this.value, pDFont);
        } else {
            float lowerLeftX = applyPadding2.getLowerLeftX();
            PlainText plainText = new PlainText(this.value);
            AppearanceStyle appearanceStyle = new AppearanceStyle();
            appearanceStyle.setFont(pDFont);
            appearanceStyle.setFontSize(calculateFontSize);
            appearanceStyle.setLeading((pDFont.getBoundingBox().getHeight() / GLYPH_TO_PDF_SCALE) * calculateFontSize);
            new PlainTextFormatter.Builder(appearancePrimitivesComposer).style(appearanceStyle).text(plainText).width(applyPadding2.getWidth()).wrapLines(true).initialOffset(lowerLeftX, calculateVerticalOffset).textAlign(this.parent.getQ()).build().format();
        }
        appearancePrimitivesComposer.endText();
    }

    private PDFont getFontAndUpdateResources(PDAppearanceStream pDAppearanceStream) throws IOException {
        PDFont font;
        PDResources resources = pDAppearanceStream.getResources();
        PDResources defaultResources = this.acroForm.getDefaultResources();
        if (resources == null && defaultResources == null) {
            throw new IOException("Unable to generate field appearance - missing required resources");
        }
        COSName fontName = this.defaultAppearanceHandler.getFontName();
        if (resources != null) {
            PDFont font2 = resources.getFont(fontName);
            if (font2 != null) {
                return font2;
            }
        } else {
            resources = new PDResources();
            pDAppearanceStream.setResources(resources);
        }
        if (defaultResources != null && (font = defaultResources.getFont(fontName)) != null) {
            resources.put(fontName, font);
            return font;
        }
        PDFont resolveFont = resolveFont(resources, defaultResources, fontName);
        if (resolveFont != null) {
            resources.put(fontName, resolveFont);
            return resolveFont;
        }
        throw new IOException("Unable to generate field appearance - missing required font resources: " + fontName);
    }

    private PDFont resolveFont(PDResources pDResources, PDResources pDResources2, COSName cOSName) throws IOException {
        if (pDResources != null) {
            Iterator<COSName> it = pDResources.getFontNames().iterator();
            while (it.hasNext()) {
                PDFont font = pDResources.getFont(it.next());
                if (font.getName().equals(cOSName.getName())) {
                    return font;
                }
            }
        }
        if (pDResources2 == null) {
            return null;
        }
        Iterator<COSName> it2 = pDResources2.getFontNames().iterator();
        while (it2.hasNext()) {
            PDFont font2 = pDResources2.getFont(it2.next());
            if (font2.getName().equals(cOSName.getName())) {
                return font2;
            }
        }
        return null;
    }

    private boolean isMultiLine() {
        PDVariableText pDVariableText = this.parent;
        return (pDVariableText instanceof PDTextField) && ((PDTextField) pDVariableText).isMultiline();
    }

    private void writeToStream(byte[] bArr, PDAppearanceStream pDAppearanceStream) throws IOException {
        OutputStream createUnfilteredStream = pDAppearanceStream.getCOSStream().createUnfilteredStream();
        createUnfilteredStream.write(bArr);
        createUnfilteredStream.flush();
    }

    private float getLineWidth(List<Object> list) {
        if (list != null) {
            int indexOf = list.indexOf(Operator.getOperator("BT"));
            int indexOf2 = list.indexOf(Operator.getOperator("w"));
            if (indexOf2 > 0 && (indexOf2 < indexOf || indexOf == -1)) {
                return ((COSNumber) list.get(indexOf2 - 1)).floatValue();
            }
        }
        return 0.0f;
    }

    private float calculateFontSize(PDFont pDFont, PDRectangle pDRectangle) throws IOException {
        float fontSize = !this.defaultAppearanceHandler.getTokens().isEmpty() ? this.defaultAppearanceHandler.getFontSize() : 12.0f;
        if (fontSize == 0.0f && !isMultiLine()) {
            fontSize = Math.min(pDRectangle.getHeight() / (pDFont.getFontDescriptor().getFontBoundingBox().getHeight() / GLYPH_TO_PDF_SCALE), pDRectangle.getWidth() / (pDFont.getStringWidth(this.value) / GLYPH_TO_PDF_SCALE));
        }
        if (fontSize == 0.0f) {
            return 12.0f;
        }
        return fontSize;
    }

    private float calculateVerticalOffset(PDRectangle pDRectangle, PDRectangle pDRectangle2, PDFont pDFont, float f) throws IOException {
        float capHeight = getCapHeight(pDFont, f);
        float height = (pDFont.getBoundingBox().getHeight() / GLYPH_TO_PDF_SCALE) * f;
        PDVariableText pDVariableText = this.parent;
        if ((pDVariableText instanceof PDTextField) && ((PDTextField) pDVariableText).isMultiline()) {
            return pDRectangle2.getUpperRightY() + height;
        }
        if (capHeight > pDRectangle.getHeight()) {
            return pDRectangle.getLowerLeftX() - ((pDFont.getFontDescriptor().getDescent() / GLYPH_TO_PDF_SCALE) * f);
        }
        return pDRectangle.getLowerLeftX() + ((pDRectangle.getHeight() - capHeight) / 2.0f);
    }

    private float calculateHorizontalOffset(PDRectangle pDRectangle, PDFont pDFont, float f) throws IOException {
        float stringWidth = (pDFont.getStringWidth(this.value) / GLYPH_TO_PDF_SCALE) * f;
        int q = this.parent.getQ();
        if (q == 0 || stringWidth > pDRectangle.getWidth()) {
            return pDRectangle.getLowerLeftX();
        }
        if (q == 1) {
            return pDRectangle.getLowerLeftX() + ((pDRectangle.getWidth() - stringWidth) / 2.0f);
        }
        if (q == 2) {
            return (pDRectangle.getLowerLeftX() + pDRectangle.getWidth()) - stringWidth;
        }
        float lowerLeftX = pDRectangle.getLowerLeftX();
        Log.d("PdfBoxAndroid", "Unknown justification value, defaulting to left: " + q);
        return lowerLeftX;
    }

    private float getCapHeight(PDFont pDFont, float f) throws IOException {
        float height;
        PDFontDescriptor fontDescriptor = pDFont.getFontDescriptor();
        if (fontDescriptor == null || fontDescriptor.getCapHeight() == 0.0f) {
            height = (pDFont.getBoundingBox().getHeight() / GLYPH_TO_PDF_SCALE) * f;
            f = 0.7f;
        } else {
            height = pDFont.getFontDescriptor().getCapHeight() / GLYPH_TO_PDF_SCALE;
        }
        return height * f;
    }

    private PDRectangle resolveBoundingBox(PDAnnotationWidget pDAnnotationWidget, PDAppearanceStream pDAppearanceStream) {
        PDRectangle bBox = pDAppearanceStream.getBBox();
        return bBox == null ? pDAnnotationWidget.getRectangle().createRetranslatedRectangle() : bBox;
    }

    private PDRectangle applyPadding(PDRectangle pDRectangle, float f) {
        float lowerLeftX = pDRectangle.getLowerLeftX() + f;
        float lowerLeftY = pDRectangle.getLowerLeftY() + f;
        float f2 = f * 2.0f;
        return new PDRectangle(lowerLeftX, lowerLeftY, pDRectangle.getWidth() - f2, pDRectangle.getHeight() - f2);
    }
}
