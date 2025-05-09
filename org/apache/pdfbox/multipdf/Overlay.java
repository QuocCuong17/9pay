package org.apache.pdfbox.multipdf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.util.awt.AffineTransform;

/* loaded from: classes5.dex */
public class Overlay {
    private LayoutPage defaultOverlayPage;
    private LayoutPage evenPageOverlayPage;
    private LayoutPage firstPageOverlayPage;
    private LayoutPage lastPageOverlayPage;
    private LayoutPage oddPageOverlayPage;
    private final Map<Integer, PDDocument> specificPageOverlay = new HashMap();
    private Map<Integer, LayoutPage> specificPageOverlayPage = new HashMap();
    private Position position = Position.BACKGROUND;
    private String inputFileName = null;
    private PDDocument inputPDFDocument = null;
    private String outputFilename = null;
    private String defaultOverlayFilename = null;
    private PDDocument defaultOverlay = null;
    private String firstPageOverlayFilename = null;
    private PDDocument firstPageOverlay = null;
    private String lastPageOverlayFilename = null;
    private PDDocument lastPageOverlay = null;
    private String allPagesOverlayFilename = null;
    private PDDocument allPagesOverlay = null;
    private String oddPageOverlayFilename = null;
    private PDDocument oddPageOverlay = null;
    private String evenPageOverlayFilename = null;
    private PDDocument evenPageOverlay = null;
    private int numberOfOverlayPages = 0;
    private boolean useAllOverlayPages = false;

    /* loaded from: classes5.dex */
    public enum Position {
        FOREGROUND,
        BACKGROUND
    }

    public void overlay(Map<Integer, String> map) throws IOException {
        try {
            loadPDFs();
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                PDDocument loadPDF = loadPDF(entry.getValue());
                this.specificPageOverlay.put(entry.getKey(), loadPDF);
                this.specificPageOverlayPage.put(entry.getKey(), getLayoutPage(loadPDF));
            }
            processPages(this.inputPDFDocument);
            this.inputPDFDocument.save(this.outputFilename);
        } finally {
            PDDocument pDDocument = this.inputPDFDocument;
            if (pDDocument != null) {
                pDDocument.close();
            }
            PDDocument pDDocument2 = this.defaultOverlay;
            if (pDDocument2 != null) {
                pDDocument2.close();
            }
            PDDocument pDDocument3 = this.firstPageOverlay;
            if (pDDocument3 != null) {
                pDDocument3.close();
            }
            PDDocument pDDocument4 = this.lastPageOverlay;
            if (pDDocument4 != null) {
                pDDocument4.close();
            }
            PDDocument pDDocument5 = this.allPagesOverlay;
            if (pDDocument5 != null) {
                pDDocument5.close();
            }
            PDDocument pDDocument6 = this.oddPageOverlay;
            if (pDDocument6 != null) {
                pDDocument6.close();
            }
            PDDocument pDDocument7 = this.evenPageOverlay;
            if (pDDocument7 != null) {
                pDDocument7.close();
            }
            Iterator<Map.Entry<Integer, PDDocument>> it = this.specificPageOverlay.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().close();
            }
            this.specificPageOverlay.clear();
            this.specificPageOverlayPage.clear();
        }
    }

    private void loadPDFs() throws IOException {
        String str = this.inputFileName;
        if (str != null) {
            this.inputPDFDocument = loadPDF(str);
        }
        String str2 = this.defaultOverlayFilename;
        if (str2 != null) {
            this.defaultOverlay = loadPDF(str2);
        }
        PDDocument pDDocument = this.defaultOverlay;
        if (pDDocument != null) {
            this.defaultOverlayPage = getLayoutPage(pDDocument);
        }
        String str3 = this.firstPageOverlayFilename;
        if (str3 != null) {
            this.firstPageOverlay = loadPDF(str3);
        }
        PDDocument pDDocument2 = this.firstPageOverlay;
        if (pDDocument2 != null) {
            this.firstPageOverlayPage = getLayoutPage(pDDocument2);
        }
        String str4 = this.lastPageOverlayFilename;
        if (str4 != null) {
            this.lastPageOverlay = loadPDF(str4);
        }
        PDDocument pDDocument3 = this.lastPageOverlay;
        if (pDDocument3 != null) {
            this.lastPageOverlayPage = getLayoutPage(pDDocument3);
        }
        String str5 = this.oddPageOverlayFilename;
        if (str5 != null) {
            this.oddPageOverlay = loadPDF(str5);
        }
        PDDocument pDDocument4 = this.oddPageOverlay;
        if (pDDocument4 != null) {
            this.oddPageOverlayPage = getLayoutPage(pDDocument4);
        }
        String str6 = this.evenPageOverlayFilename;
        if (str6 != null) {
            this.evenPageOverlay = loadPDF(str6);
        }
        PDDocument pDDocument5 = this.evenPageOverlay;
        if (pDDocument5 != null) {
            this.evenPageOverlayPage = getLayoutPage(pDDocument5);
        }
        String str7 = this.allPagesOverlayFilename;
        if (str7 != null) {
            this.allPagesOverlay = loadPDF(str7);
        }
        PDDocument pDDocument6 = this.allPagesOverlay;
        if (pDDocument6 != null) {
            Map<Integer, LayoutPage> layoutPages = getLayoutPages(pDDocument6);
            this.specificPageOverlayPage = layoutPages;
            this.useAllOverlayPages = true;
            this.numberOfOverlayPages = layoutPages.size();
        }
    }

    private PDDocument loadPDF(String str) throws IOException {
        return PDDocument.load(new File(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class LayoutPage {
        private final COSStream overlayContentStream;
        private final PDRectangle overlayMediaBox;
        private final COSDictionary overlayResources;

        /* synthetic */ LayoutPage(PDRectangle pDRectangle, COSStream cOSStream, COSDictionary cOSDictionary, AnonymousClass1 anonymousClass1) {
            this(pDRectangle, cOSStream, cOSDictionary);
        }

        private LayoutPage(PDRectangle pDRectangle, COSStream cOSStream, COSDictionary cOSDictionary) {
            this.overlayMediaBox = pDRectangle;
            this.overlayContentStream = cOSStream;
            this.overlayResources = cOSDictionary;
        }
    }

    private LayoutPage getLayoutPage(PDDocument pDDocument) throws IOException {
        PDPage page = pDDocument.getPage(0);
        COSBase dictionaryObject = page.getCOSObject().getDictionaryObject(COSName.CONTENTS);
        PDResources resources = page.getResources();
        if (resources == null) {
            resources = new PDResources();
        }
        return new LayoutPage(page.getMediaBox(), createContentStream(dictionaryObject), resources.getCOSObject(), null);
    }

    private Map<Integer, LayoutPage> getLayoutPages(PDDocument pDDocument) throws IOException {
        int numberOfPages = pDDocument.getNumberOfPages();
        HashMap hashMap = new HashMap(numberOfPages);
        for (int i = 0; i < numberOfPages; i++) {
            PDPage page = pDDocument.getPage(i);
            COSBase dictionaryObject = page.getCOSObject().getDictionaryObject(COSName.CONTENTS);
            PDResources resources = page.getResources();
            if (resources == null) {
                resources = new PDResources();
            }
            hashMap.put(Integer.valueOf(i), new LayoutPage(page.getMediaBox(), createContentStream(dictionaryObject), resources.getCOSObject(), null));
        }
        return hashMap;
    }

    private COSStream createContentStream(COSBase cOSBase) throws IOException {
        List<COSStream> createContentStreamList = createContentStreamList(cOSBase);
        COSStream cOSStream = new COSStream();
        OutputStream createUnfilteredStream = cOSStream.createUnfilteredStream();
        Iterator<COSStream> it = createContentStreamList.iterator();
        while (it.hasNext()) {
            InputStream unfilteredStream = it.next().getUnfilteredStream();
            byte[] bArr = new byte[2048];
            while (true) {
                int read = unfilteredStream.read(bArr);
                if (read > 0) {
                    createUnfilteredStream.write(bArr, 0, read);
                }
            }
            createUnfilteredStream.flush();
        }
        createUnfilteredStream.close();
        cOSStream.setFilters(COSName.FLATE_DECODE);
        return cOSStream;
    }

    private List<COSStream> createContentStreamList(COSBase cOSBase) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (cOSBase instanceof COSStream) {
            arrayList.add((COSStream) cOSBase);
        } else if (cOSBase instanceof COSArray) {
            Iterator<COSBase> it = ((COSArray) cOSBase).iterator();
            while (it.hasNext()) {
                arrayList.addAll(createContentStreamList(it.next()));
            }
        } else if (cOSBase instanceof COSObject) {
            arrayList.addAll(createContentStreamList(((COSObject) cOSBase).getObject()));
        } else {
            throw new IOException("Contents are unknown type:" + cOSBase.getClass().getName());
        }
        return arrayList;
    }

    private void processPages(PDDocument pDDocument) throws IOException {
        Iterator<PDPage> it = pDDocument.getPages().iterator();
        int i = 0;
        while (it.hasNext()) {
            PDPage next = it.next();
            COSDictionary cOSObject = next.getCOSObject();
            COSBase dictionaryObject = cOSObject.getDictionaryObject(COSName.CONTENTS);
            COSArray cOSArray = new COSArray();
            int i2 = AnonymousClass1.$SwitchMap$org$apache$pdfbox$multipdf$Overlay$Position[this.position.ordinal()];
            if (i2 == 1) {
                cOSArray.add((COSBase) createStream("q\n"));
                addOriginalContent(dictionaryObject, cOSArray);
                cOSArray.add((COSBase) createStream("Q\n"));
                overlayPage(cOSArray, next, i + 1, pDDocument.getNumberOfPages());
            } else if (i2 == 2) {
                overlayPage(cOSArray, next, i + 1, pDDocument.getNumberOfPages());
                addOriginalContent(dictionaryObject, cOSArray);
            } else {
                throw new IOException("Unknown type of position:" + this.position);
            }
            cOSObject.setItem(COSName.CONTENTS, (COSBase) cOSArray);
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.apache.pdfbox.multipdf.Overlay$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$pdfbox$multipdf$Overlay$Position;

        static {
            int[] iArr = new int[Position.values().length];
            $SwitchMap$org$apache$pdfbox$multipdf$Overlay$Position = iArr;
            try {
                iArr[Position.FOREGROUND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$pdfbox$multipdf$Overlay$Position[Position.BACKGROUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void addOriginalContent(COSBase cOSBase, COSArray cOSArray) throws IOException {
        if (cOSBase instanceof COSStream) {
            cOSArray.add(cOSBase);
        } else {
            if (cOSBase instanceof COSArray) {
                cOSArray.addAll((COSArray) cOSBase);
                return;
            }
            throw new IOException("Unknown content type:" + cOSBase.getClass().getName());
        }
    }

    private void overlayPage(COSArray cOSArray, PDPage pDPage, int i, int i2) throws IOException {
        LayoutPage layoutPage;
        LayoutPage layoutPage2;
        LayoutPage layoutPage3;
        if (this.useAllOverlayPages || !this.specificPageOverlayPage.containsKey(Integer.valueOf(i))) {
            if (i != 1 || (layoutPage3 = this.firstPageOverlayPage) == null) {
                if (i != i2 || (layoutPage = this.lastPageOverlayPage) == null) {
                    int i3 = i % 2;
                    if (i3 != 1 || (layoutPage3 = this.oddPageOverlayPage) == null) {
                        if ((i3 != 0 || (layoutPage = this.evenPageOverlayPage) == null) && (layoutPage = this.defaultOverlayPage) == null) {
                            layoutPage2 = this.useAllOverlayPages ? this.specificPageOverlayPage.get(Integer.valueOf((i - 1) % this.numberOfOverlayPages)) : null;
                        }
                    }
                }
                layoutPage2 = layoutPage;
            }
            layoutPage2 = layoutPage3;
        } else {
            layoutPage2 = this.specificPageOverlayPage.get(Integer.valueOf(i));
        }
        if (layoutPage2 != null) {
            if (pDPage.getResources() == null) {
                pDPage.setResources(new PDResources());
            }
            cOSArray.add((COSBase) createOverlayStream(pDPage, layoutPage2, createOverlayXObject(pDPage, layoutPage2, layoutPage2.overlayContentStream)));
        }
    }

    private COSName createOverlayXObject(PDPage pDPage, LayoutPage layoutPage, COSStream cOSStream) {
        PDFormXObject pDFormXObject = new PDFormXObject(new PDStream(cOSStream));
        pDFormXObject.setResources(new PDResources(layoutPage.overlayResources));
        pDFormXObject.setFormType(1);
        pDFormXObject.setBBox(layoutPage.overlayMediaBox.createRetranslatedRectangle());
        pDFormXObject.setMatrix(new AffineTransform());
        return pDPage.getResources().add(pDFormXObject, "OL");
    }

    private COSStream createOverlayStream(PDPage pDPage, LayoutPage layoutPage, COSName cOSName) throws IOException {
        PDRectangle mediaBox = pDPage.getMediaBox();
        return createStream("q\nq 1 0 0 1 " + float2String((mediaBox.getWidth() - layoutPage.overlayMediaBox.getWidth()) / 2.0f) + " " + float2String((mediaBox.getHeight() - layoutPage.overlayMediaBox.getHeight()) / 2.0f) + " cm /" + cOSName.getName() + " Do Q\nQ\n");
    }

    private String float2String(float f) {
        String plainString = new BigDecimal(String.valueOf(f)).toPlainString();
        if (plainString.indexOf(46) > -1 && !plainString.endsWith(".0")) {
            while (plainString.endsWith("0") && !plainString.endsWith(".0")) {
                plainString = plainString.substring(0, plainString.length() - 1);
            }
        }
        return plainString;
    }

    private COSStream createStream(String str) throws IOException {
        COSStream cOSStream = new COSStream();
        OutputStream createUnfilteredStream = cOSStream.createUnfilteredStream();
        createUnfilteredStream.write(str.getBytes("ISO-8859-1"));
        createUnfilteredStream.close();
        cOSStream.setFilters(COSName.FLATE_DECODE);
        return cOSStream;
    }

    public void setOverlayPosition(Position position) {
        this.position = position;
    }

    public void setInputFile(String str) {
        this.inputFileName = str;
    }

    public void setInputPDF(PDDocument pDDocument) {
        this.inputPDFDocument = pDDocument;
    }

    public String getInputFile() {
        return this.inputFileName;
    }

    public void setOutputFile(String str) {
        this.outputFilename = str;
    }

    public String getOutputFile() {
        return this.outputFilename;
    }

    public void setDefaultOverlayFile(String str) {
        this.defaultOverlayFilename = str;
    }

    public void setDefaultOverlayPDF(PDDocument pDDocument) {
        this.defaultOverlay = pDDocument;
    }

    public String getDefaultOverlayFile() {
        return this.defaultOverlayFilename;
    }

    public void setFirstPageOverlayFile(String str) {
        this.firstPageOverlayFilename = str;
    }

    public void setFirstPageOverlayPDF(PDDocument pDDocument) {
        this.firstPageOverlay = pDDocument;
    }

    public void setLastPageOverlayFile(String str) {
        this.lastPageOverlayFilename = str;
    }

    public void setLastPageOverlayPDF(PDDocument pDDocument) {
        this.lastPageOverlay = pDDocument;
    }

    public void setAllPagesOverlayFile(String str) {
        this.allPagesOverlayFilename = str;
    }

    public void setAllPagesOverlayPDF(PDDocument pDDocument) {
        this.allPagesOverlay = pDDocument;
    }

    public void setOddPageOverlayFile(String str) {
        this.oddPageOverlayFilename = str;
    }

    public void setOddPageOverlayPDF(PDDocument pDDocument) {
        this.oddPageOverlay = pDDocument;
    }

    public void setEvenPageOverlayFile(String str) {
        this.evenPageOverlayFilename = str;
    }

    public void setEvenPageOverlayPDF(PDDocument pDDocument) {
        this.evenPageOverlay = pDDocument;
    }
}
