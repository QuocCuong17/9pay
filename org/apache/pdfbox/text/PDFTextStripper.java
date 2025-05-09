package org.apache.pdfbox.text;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.Pattern;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.pdmodel.interactive.pagenavigation.PDThreadBead;
import org.apache.pdfbox.util.QuickSort;

/* loaded from: classes5.dex */
public class PDFTextStripper extends PDFTextStreamEngine {
    private static final float END_OF_LAST_TEXT_X_RESET_VALUE = -1.0f;
    private static final float EXPECTED_START_OF_NEXT_WORD_X_RESET_VALUE = -3.4028235E38f;
    private static final float LAST_WORD_SPACING_RESET_VALUE = -1.0f;
    private static final String[] LIST_ITEM_EXPRESSIONS;
    private static final float MAX_HEIGHT_FOR_LINE_RESET_VALUE = -1.0f;
    private static final float MAX_Y_FOR_LINE_RESET_VALUE = -3.4028235E38f;
    private static final float MIN_Y_TOP_FOR_LINE_RESET_VALUE = Float.MAX_VALUE;
    private static float defaultDropThreshold = 2.5f;
    private static float defaultIndentThreshold = 2.0f;
    private static final boolean useCustomQuickSort;
    protected final String LINE_SEPARATOR;
    private boolean addMoreFormatting;
    private String articleEnd;
    private String articleStart;
    private float averageCharTolerance;
    private Map<String, TreeMap<Float, TreeSet<Float>>> characterListMapping;
    protected Vector<List<TextPosition>> charactersByArticle;
    private int currentPageNo;
    protected PDDocument document;
    private float dropThreshold;
    private PDOutlineItem endBookmark;
    private int endBookmarkPageNumber;
    private int endPage;
    private boolean inParagraph;
    private float indentThreshold;
    private String lineSeparator;
    private List<Pattern> listOfPatterns;
    protected Writer output;
    private List<PDThreadBead> pageArticles;
    private String pageEnd;
    private String pageStart;
    private String paragraphEnd;
    private String paragraphStart;
    private boolean shouldSeparateByBeads;
    private boolean sortByPosition;
    private float spacingTolerance;
    private PDOutlineItem startBookmark;
    private int startBookmarkPageNumber;
    private int startPage;
    private boolean suppressDuplicateOverlappingText;
    private String wordSeparator;

    private boolean within(float f, float f2, float f3) {
        return f2 < f + f3 && f2 > f - f3;
    }

    protected void endDocument(PDDocument pDDocument) throws IOException {
    }

    protected void endPage(PDPage pDPage) throws IOException {
    }

    protected void startDocument(PDDocument pDDocument) throws IOException {
    }

    protected void startPage(PDPage pDPage) throws IOException {
    }

    static {
        String str;
        String str2 = null;
        try {
            String lowerCase = "PDFTextStripper".toLowerCase();
            str = System.getProperty(lowerCase + ".indent");
            try {
                str2 = System.getProperty(lowerCase + ".drop");
            } catch (SecurityException unused) {
            }
        } catch (SecurityException unused2) {
            str = null;
        }
        if (str != null && str.length() > 0) {
            try {
                defaultIndentThreshold = Float.parseFloat(str);
            } catch (NumberFormatException unused3) {
            }
        }
        if (str2 != null && str2.length() > 0) {
            try {
                defaultDropThreshold = Float.parseFloat(str2);
            } catch (NumberFormatException unused4) {
            }
        }
        useCustomQuickSort = true;
        LIST_ITEM_EXPRESSIONS = new String[]{"\\.", "\\d+\\.", "\\[\\d+\\]", "\\d+\\)", "[A-Z]\\.", "[a-z]\\.", "[A-Z]\\)", "[a-z]\\)", "[IVXL]+\\.", "[ivxl]+\\."};
    }

    public PDFTextStripper() throws IOException {
        String property = System.getProperty("line.separator");
        this.LINE_SEPARATOR = property;
        this.lineSeparator = property;
        this.wordSeparator = " ";
        this.paragraphStart = "";
        this.paragraphEnd = "";
        this.pageStart = "";
        this.pageEnd = property;
        this.articleStart = "";
        this.articleEnd = "";
        this.currentPageNo = 0;
        this.startPage = 1;
        this.endPage = Integer.MAX_VALUE;
        this.startBookmark = null;
        this.startBookmarkPageNumber = -1;
        this.endBookmark = null;
        this.endBookmarkPageNumber = -1;
        this.suppressDuplicateOverlappingText = true;
        this.shouldSeparateByBeads = true;
        this.sortByPosition = false;
        this.addMoreFormatting = false;
        this.indentThreshold = defaultIndentThreshold;
        this.dropThreshold = defaultDropThreshold;
        this.spacingTolerance = 0.5f;
        this.averageCharTolerance = 0.3f;
        this.pageArticles = null;
        this.charactersByArticle = new Vector<>();
        this.characterListMapping = new HashMap();
        this.listOfPatterns = null;
    }

    public String getText(PDDocument pDDocument) throws IOException {
        StringWriter stringWriter = new StringWriter();
        writeText(pDDocument, stringWriter);
        return stringWriter.toString();
    }

    private void resetEngine() {
        this.currentPageNo = 0;
        this.document = null;
        Vector<List<TextPosition>> vector = this.charactersByArticle;
        if (vector != null) {
            vector.clear();
        }
        Map<String, TreeMap<Float, TreeSet<Float>>> map = this.characterListMapping;
        if (map != null) {
            map.clear();
        }
        this.startBookmark = null;
        this.endBookmark = null;
    }

    public void writeText(PDDocument pDDocument, Writer writer) throws IOException {
        resetEngine();
        this.document = pDDocument;
        this.output = writer;
        if (getAddMoreFormatting()) {
            String str = this.lineSeparator;
            this.paragraphEnd = str;
            this.pageStart = str;
            this.articleStart = str;
            this.articleEnd = str;
        }
        startDocument(this.document);
        processPages(this.document.getPages());
        endDocument(this.document);
    }

    protected void processPages(PDPageTree pDPageTree) throws IOException {
        PDOutlineItem pDOutlineItem = this.startBookmark;
        PDPage findDestinationPage = pDOutlineItem == null ? null : pDOutlineItem.findDestinationPage(this.document);
        PDOutlineItem pDOutlineItem2 = this.endBookmark;
        PDPage findDestinationPage2 = pDOutlineItem2 != null ? pDOutlineItem2.findDestinationPage(this.document) : null;
        if (findDestinationPage != null && findDestinationPage2 != null && this.startBookmark.getCOSObject() == this.endBookmark.getCOSObject()) {
            this.startBookmarkPageNumber = 0;
            this.endBookmarkPageNumber = 0;
        }
        Iterator<PDPage> it = pDPageTree.iterator();
        while (it.hasNext()) {
            PDPage next = it.next();
            PDStream stream = next.getStream();
            this.currentPageNo++;
            if (stream != null) {
                processPage(next);
            }
        }
    }

    @Override // org.apache.pdfbox.text.PDFTextStreamEngine, org.apache.pdfbox.contentstream.PDFStreamEngine
    public void processPage(PDPage pDPage) throws IOException {
        int i = this.currentPageNo;
        if (i < this.startPage || i > this.endPage) {
            return;
        }
        int i2 = this.startBookmarkPageNumber;
        if (i2 == -1 || i >= i2) {
            int i3 = this.endBookmarkPageNumber;
            if (i3 == -1 || i <= i3) {
                startPage(pDPage);
                List<PDThreadBead> threadBeads = pDPage.getThreadBeads();
                this.pageArticles = threadBeads;
                int size = this.shouldSeparateByBeads ? (threadBeads.size() * 2) + 1 : 1;
                int size2 = this.charactersByArticle.size();
                this.charactersByArticle.setSize(size);
                for (int i4 = 0; i4 < size; i4++) {
                    if (size < size2) {
                        this.charactersByArticle.get(i4).clear();
                    } else {
                        this.charactersByArticle.set(i4, new ArrayList());
                    }
                }
                this.characterListMapping.clear();
                super.processPage(pDPage);
                writePage();
                endPage(pDPage);
            }
        }
    }

    protected void startArticle() throws IOException {
        startArticle(true);
    }

    protected void startArticle(boolean z) throws IOException {
        this.output.write(getArticleStart());
    }

    protected void endArticle() throws IOException {
        this.output.write(getArticleEnd());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:102:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01f4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void writePage() throws IOException {
        float f;
        float f2;
        float x;
        float y;
        float width;
        float height;
        float f3;
        boolean z;
        float widthOfSpace;
        float f4;
        PositionWrapper positionWrapper;
        float f5;
        if (this.charactersByArticle.size() > 0) {
            writePageStart();
        }
        Iterator<List<TextPosition>> it = this.charactersByArticle.iterator();
        PositionWrapper positionWrapper2 = null;
        PositionWrapper positionWrapper3 = null;
        float f6 = -3.4028235E38f;
        float f7 = -1.0f;
        float f8 = -1.0f;
        float f9 = -1.0f;
        float f10 = Float.MAX_VALUE;
        boolean z2 = true;
        while (it.hasNext()) {
            List<TextPosition> next = it.next();
            if (getSortByPosition()) {
                TextPositionComparator textPositionComparator = new TextPositionComparator();
                if (useCustomQuickSort) {
                    QuickSort.sort(next, textPositionComparator);
                } else {
                    Collections.sort(next, textPositionComparator);
                }
            }
            Iterator<TextPosition> it2 = next.iterator();
            int i = 0;
            int i2 = 0;
            while (it2.hasNext()) {
                String unicode = it2.next().getUnicode();
                Iterator<List<TextPosition>> it3 = it;
                int i3 = 0;
                while (i3 < unicode.length()) {
                    byte directionality = Character.getDirectionality(unicode.charAt(i3));
                    PositionWrapper positionWrapper4 = positionWrapper2;
                    if (directionality == 0 || directionality == 14 || directionality == 15) {
                        i2++;
                    } else if (directionality == 1 || directionality == 2 || directionality == 16 || directionality == 17) {
                        i++;
                    }
                    i3++;
                    positionWrapper2 = positionWrapper4;
                }
                it = it3;
            }
            Iterator<List<TextPosition>> it4 = it;
            PositionWrapper positionWrapper5 = positionWrapper2;
            boolean z3 = i > i2;
            startArticle(!z3);
            boolean z4 = i > 0;
            ArrayList arrayList = new ArrayList();
            Iterator<TextPosition> it5 = next.iterator();
            PositionWrapper positionWrapper6 = positionWrapper5;
            float f11 = -1.0f;
            boolean z5 = true;
            while (it5.hasNext()) {
                TextPosition next2 = it5.next();
                Iterator<TextPosition> it6 = it5;
                PositionWrapper positionWrapper7 = new PositionWrapper(next2);
                String unicode2 = next2.getUnicode();
                float f12 = f11;
                if (positionWrapper6 != null) {
                    f = f10;
                    if (next2.getFont() != positionWrapper6.getTextPosition().getFont() || next2.getFontSize() != positionWrapper6.getTextPosition().getFontSize()) {
                        f2 = -1.0f;
                        if (!getSortByPosition()) {
                            x = next2.getXDirAdj();
                            y = next2.getYDirAdj();
                            width = next2.getWidthDirAdj();
                            height = next2.getHeightDir();
                        } else {
                            x = next2.getX();
                            y = next2.getY();
                            width = next2.getWidth();
                            height = next2.getHeight();
                        }
                        float f13 = height;
                        float f14 = x;
                        f3 = y;
                        z = z2;
                        int length = next2.getIndividualWidths().length;
                        widthOfSpace = next2.getWidthOfSpace();
                        if (widthOfSpace != 0.0f || widthOfSpace == Float.NaN) {
                            f4 = Float.MAX_VALUE;
                        } else if (f8 < 0.0f) {
                            f4 = getSpacingTolerance() * widthOfSpace;
                        } else {
                            f4 = ((widthOfSpace + f8) / 2.0f) * getSpacingTolerance();
                        }
                        f11 = f2 >= 0.0f ? width / length : (f2 + (width / length)) / 2.0f;
                        float averageCharTolerance = getAverageCharTolerance() * f11;
                        float f15 = f7 == -1.0f ? averageCharTolerance > f4 ? f7 + f4 : f7 + averageCharTolerance : -3.4028235E38f;
                        if (positionWrapper6 == null) {
                            if (z5) {
                                positionWrapper6.setArticleStart();
                                z5 = false;
                            }
                            if (overlap(f3, f13, f6, f9)) {
                                positionWrapper = positionWrapper7;
                                f5 = -3.4028235E38f;
                            } else {
                                writeLine(normalize(arrayList, z3, z4), z3);
                                arrayList.clear();
                                positionWrapper = positionWrapper7;
                                positionWrapper3 = handleLineSeparation(positionWrapper, positionWrapper6, positionWrapper3, f9);
                                f9 = -1.0f;
                                f6 = -3.4028235E38f;
                                f15 = -3.4028235E38f;
                                f5 = -3.4028235E38f;
                                f = Float.MAX_VALUE;
                            }
                            if (f15 != f5 && f15 < f14 && positionWrapper6.getTextPosition().getUnicode() != null && !positionWrapper6.getTextPosition().getUnicode().endsWith(" ")) {
                                arrayList.add(LineItem.getWordSeparator());
                            }
                        } else {
                            positionWrapper = positionWrapper7;
                        }
                        float f16 = f;
                        if (f3 >= f6) {
                            f6 = f3;
                        }
                        float f17 = f14 + width;
                        if (unicode2 != null) {
                            if (z && positionWrapper6 == null) {
                                writeParagraphStart();
                            }
                            arrayList.add(new LineItem(next2));
                        }
                        f9 = Math.max(f9, f13);
                        f10 = Math.min(f16, f3 - f13);
                        if (z) {
                            z2 = z;
                        } else {
                            positionWrapper.setParagraphStart();
                            positionWrapper.setLineStart();
                            positionWrapper3 = positionWrapper;
                            z2 = false;
                        }
                        positionWrapper6 = positionWrapper;
                        f7 = f17;
                        it5 = it6;
                        f8 = widthOfSpace;
                    }
                } else {
                    f = f10;
                }
                f2 = f12;
                if (!getSortByPosition()) {
                }
                float f132 = height;
                float f142 = x;
                f3 = y;
                z = z2;
                int length2 = next2.getIndividualWidths().length;
                widthOfSpace = next2.getWidthOfSpace();
                if (widthOfSpace != 0.0f) {
                }
                f4 = Float.MAX_VALUE;
                if (f2 >= 0.0f) {
                }
                float averageCharTolerance2 = getAverageCharTolerance() * f11;
                if (f7 == -1.0f) {
                }
                if (positionWrapper6 == null) {
                }
                float f162 = f;
                if (f3 >= f6) {
                }
                float f172 = f142 + width;
                if (unicode2 != null) {
                }
                f9 = Math.max(f9, f132);
                f10 = Math.min(f162, f3 - f132);
                if (z) {
                }
                positionWrapper6 = positionWrapper;
                f7 = f172;
                it5 = it6;
                f8 = widthOfSpace;
            }
            float f18 = f10;
            boolean z6 = z2;
            if (arrayList.size() > 0) {
                writeLine(normalize(arrayList, z3, z4), z3);
                writeParagraphEnd();
            }
            endArticle();
            positionWrapper2 = positionWrapper6;
            it = it4;
            z2 = z6;
            f10 = f18;
        }
        writePageEnd();
    }

    private boolean overlap(float f, float f2, float f3, float f4) {
        return within(f, f3, 0.1f) || (f3 <= f && f3 >= f - f2) || (f <= f3 && f >= f3 - f4);
    }

    protected void writeLineSeparator() throws IOException {
        this.output.write(getLineSeparator());
    }

    protected void writeWordSeparator() throws IOException {
        this.output.write(getWordSeparator());
    }

    protected void writeCharacters(TextPosition textPosition) throws IOException {
        this.output.write(textPosition.getUnicode());
    }

    protected void writeString(String str, List<TextPosition> list) throws IOException {
        writeString(str);
    }

    protected void writeString(String str) throws IOException {
        this.output.write(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:85:? A[RETURN, SYNTHETIC] */
    @Override // org.apache.pdfbox.text.PDFTextStreamEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void processTextPosition(TextPosition textPosition) {
        boolean z;
        int i;
        int i2;
        int i3;
        boolean z2;
        int i4 = 0;
        if (this.suppressDuplicateOverlappingText) {
            String unicode = textPosition.getUnicode();
            float x = textPosition.getX();
            float y = textPosition.getY();
            TreeMap<Float, TreeSet<Float>> treeMap = this.characterListMapping.get(unicode);
            if (treeMap == null) {
                treeMap = new TreeMap<>();
                this.characterListMapping.put(unicode, treeMap);
            }
            float width = (textPosition.getWidth() / unicode.length()) / 3.0f;
            Iterator<TreeSet<Float>> it = treeMap.subMap(Float.valueOf(x - width), Float.valueOf(x + width)).values().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (!it.next().subSet(Float.valueOf(y - width), Float.valueOf(y + width)).isEmpty()) {
                        z2 = true;
                        break;
                    }
                } else {
                    z2 = false;
                    break;
                }
            }
            if (z2) {
                z = false;
                if (z) {
                    return;
                }
                float x2 = textPosition.getX();
                float y2 = textPosition.getY();
                if (this.shouldSeparateByBeads) {
                    int i5 = -1;
                    i = -1;
                    i2 = -1;
                    i3 = -1;
                    for (int i6 = 0; i6 < this.pageArticles.size() && i5 == -1; i6++) {
                        PDThreadBead pDThreadBead = this.pageArticles.get(i6);
                        if (pDThreadBead != null) {
                            PDRectangle rectangle = pDThreadBead.getRectangle();
                            if (rectangle.contains(x2, y2)) {
                                i5 = (i6 * 2) + 1;
                            } else if ((x2 < rectangle.getLowerLeftX() || y2 < rectangle.getUpperRightY()) && i == -1) {
                                i = i6 * 2;
                            } else if (x2 < rectangle.getLowerLeftX() && i2 == -1) {
                                i2 = i6 * 2;
                            } else if (y2 < rectangle.getUpperRightY() && i3 == -1) {
                                i3 = i6 * 2;
                            }
                        } else {
                            i5 = 0;
                        }
                    }
                    i4 = i5;
                } else {
                    i = -1;
                    i2 = -1;
                    i3 = -1;
                }
                if (i4 == -1) {
                    if (i != -1) {
                        i4 = i;
                    } else if (i2 != -1) {
                        i4 = i2;
                    } else {
                        i4 = i3 != -1 ? i3 : this.charactersByArticle.size() - 1;
                    }
                }
                List<TextPosition> list = this.charactersByArticle.get(i4);
                if (list.isEmpty()) {
                    list.add(textPosition);
                    return;
                }
                TextPosition textPosition2 = list.get(list.size() - 1);
                if (textPosition.isDiacritic() && textPosition2.contains(textPosition)) {
                    textPosition2.mergeDiacritic(textPosition);
                    return;
                }
                if (textPosition2.isDiacritic() && textPosition.contains(textPosition2)) {
                    textPosition.mergeDiacritic(textPosition2);
                    list.remove(list.size() - 1);
                    list.add(textPosition);
                    return;
                }
                list.add(textPosition);
                return;
            }
            TreeSet<Float> treeSet = treeMap.get(Float.valueOf(x));
            if (treeSet == null) {
                treeSet = new TreeSet<>();
                treeMap.put(Float.valueOf(x), treeSet);
            }
            treeSet.add(Float.valueOf(y));
        }
        z = true;
        if (z) {
        }
    }

    public int getStartPage() {
        return this.startPage;
    }

    public void setStartPage(int i) {
        this.startPage = i;
    }

    public int getEndPage() {
        return this.endPage;
    }

    public void setEndPage(int i) {
        this.endPage = i;
    }

    public void setLineSeparator(String str) {
        this.lineSeparator = str;
    }

    public String getLineSeparator() {
        return this.lineSeparator;
    }

    public String getWordSeparator() {
        return this.wordSeparator;
    }

    public void setWordSeparator(String str) {
        this.wordSeparator = str;
    }

    public boolean getSuppressDuplicateOverlappingText() {
        return this.suppressDuplicateOverlappingText;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getCurrentPageNo() {
        return this.currentPageNo;
    }

    protected Writer getOutput() {
        return this.output;
    }

    protected List<List<TextPosition>> getCharactersByArticle() {
        return this.charactersByArticle;
    }

    public void setSuppressDuplicateOverlappingText(boolean z) {
        this.suppressDuplicateOverlappingText = z;
    }

    public boolean getSeparateByBeads() {
        return this.shouldSeparateByBeads;
    }

    public void setShouldSeparateByBeads(boolean z) {
        this.shouldSeparateByBeads = z;
    }

    public PDOutlineItem getEndBookmark() {
        return this.endBookmark;
    }

    public void setEndBookmark(PDOutlineItem pDOutlineItem) {
        this.endBookmark = pDOutlineItem;
    }

    public PDOutlineItem getStartBookmark() {
        return this.startBookmark;
    }

    public void setStartBookmark(PDOutlineItem pDOutlineItem) {
        this.startBookmark = pDOutlineItem;
    }

    public boolean getAddMoreFormatting() {
        return this.addMoreFormatting;
    }

    public void setAddMoreFormatting(boolean z) {
        this.addMoreFormatting = z;
    }

    public boolean getSortByPosition() {
        return this.sortByPosition;
    }

    public void setSortByPosition(boolean z) {
        this.sortByPosition = z;
    }

    public float getSpacingTolerance() {
        return this.spacingTolerance;
    }

    public void setSpacingTolerance(float f) {
        this.spacingTolerance = f;
    }

    public float getAverageCharTolerance() {
        return this.averageCharTolerance;
    }

    public void setAverageCharTolerance(float f) {
        this.averageCharTolerance = f;
    }

    public float getIndentThreshold() {
        return this.indentThreshold;
    }

    public void setIndentThreshold(float f) {
        this.indentThreshold = f;
    }

    public float getDropThreshold() {
        return this.dropThreshold;
    }

    public void setDropThreshold(float f) {
        this.dropThreshold = f;
    }

    public String getParagraphStart() {
        return this.paragraphStart;
    }

    public void setParagraphStart(String str) {
        this.paragraphStart = str;
    }

    public String getParagraphEnd() {
        return this.paragraphEnd;
    }

    public void setParagraphEnd(String str) {
        this.paragraphEnd = str;
    }

    public String getPageStart() {
        return this.pageStart;
    }

    public void setPageStart(String str) {
        this.pageStart = str;
    }

    public String getPageEnd() {
        return this.pageEnd;
    }

    public void setPageEnd(String str) {
        this.pageEnd = str;
    }

    public String getArticleStart() {
        return this.articleStart;
    }

    public void setArticleStart(String str) {
        this.articleStart = str;
    }

    public String getArticleEnd() {
        return this.articleEnd;
    }

    public void setArticleEnd(String str) {
        this.articleEnd = str;
    }

    private PositionWrapper handleLineSeparation(PositionWrapper positionWrapper, PositionWrapper positionWrapper2, PositionWrapper positionWrapper3, float f) throws IOException {
        positionWrapper.setLineStart();
        isParagraphSeparation(positionWrapper, positionWrapper2, positionWrapper3, f);
        if (positionWrapper.isParagraphStart()) {
            if (positionWrapper2.isArticleStart()) {
                writeParagraphStart();
            } else {
                writeLineSeparator();
                writeParagraphSeparator();
            }
        } else {
            writeLineSeparator();
        }
        return positionWrapper;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0076, code lost:
    
        if (r8.isParagraphStart() == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x009b, code lost:
    
        if (r7 == matchListItemPattern(r6)) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void isParagraphSeparation(PositionWrapper positionWrapper, PositionWrapper positionWrapper2, PositionWrapper positionWrapper3, float f) {
        boolean z = true;
        if (positionWrapper3 != null) {
            float abs = Math.abs(positionWrapper.getTextPosition().getYDirAdj() - positionWrapper2.getTextPosition().getYDirAdj());
            float multiplyFloat = multiplyFloat(getDropThreshold(), f);
            float xDirAdj = positionWrapper.getTextPosition().getXDirAdj() - positionWrapper3.getTextPosition().getXDirAdj();
            float multiplyFloat2 = multiplyFloat(getIndentThreshold(), positionWrapper.getTextPosition().getWidthOfSpace());
            float multiplyFloat3 = multiplyFloat(0.25f, positionWrapper.getTextPosition().getWidth());
            if (abs <= multiplyFloat) {
                if (xDirAdj > multiplyFloat2) {
                    if (positionWrapper3.isParagraphStart()) {
                        positionWrapper.setHangingIndent();
                        z = false;
                    }
                } else if (xDirAdj >= (-positionWrapper.getTextPosition().getWidthOfSpace())) {
                    if (Math.abs(xDirAdj) < multiplyFloat3) {
                        if (positionWrapper3.isHangingIndent()) {
                            positionWrapper.setHangingIndent();
                        } else if (positionWrapper3.isParagraphStart()) {
                            Pattern matchListItemPattern = matchListItemPattern(positionWrapper3);
                            if (matchListItemPattern != null) {
                            }
                        }
                    }
                    z = false;
                }
            }
        }
        if (z) {
            positionWrapper.setParagraphStart();
        }
    }

    private float multiplyFloat(float f, float f2) {
        return Math.round((f * f2) * 1000.0f) / 1000.0f;
    }

    protected void writeParagraphSeparator() throws IOException {
        writeParagraphEnd();
        writeParagraphStart();
    }

    protected void writeParagraphStart() throws IOException {
        if (this.inParagraph) {
            writeParagraphEnd();
            this.inParagraph = false;
        }
        this.output.write(getParagraphStart());
        this.inParagraph = true;
    }

    protected void writeParagraphEnd() throws IOException {
        if (!this.inParagraph) {
            writeParagraphStart();
        }
        this.output.write(getParagraphEnd());
        this.inParagraph = false;
    }

    protected void writePageStart() throws IOException {
        this.output.write(getPageStart());
    }

    protected void writePageEnd() throws IOException {
        this.output.write(getPageEnd());
    }

    private Pattern matchListItemPattern(PositionWrapper positionWrapper) {
        return matchPattern(positionWrapper.getTextPosition().getUnicode(), getListItemPatterns());
    }

    protected void setListItemPatterns(List<Pattern> list) {
        this.listOfPatterns = list;
    }

    protected List<Pattern> getListItemPatterns() {
        if (this.listOfPatterns == null) {
            this.listOfPatterns = new ArrayList();
            for (String str : LIST_ITEM_EXPRESSIONS) {
                this.listOfPatterns.add(Pattern.compile(str));
            }
        }
        return this.listOfPatterns;
    }

    protected static Pattern matchPattern(String str, List<Pattern> list) {
        for (Pattern pattern : list) {
            if (pattern.matcher(str).matches()) {
                return pattern;
            }
        }
        return null;
    }

    private void writeLine(List<WordWithTextPositions> list, boolean z) throws IOException {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            WordWithTextPositions wordWithTextPositions = list.get(i);
            writeString(wordWithTextPositions.getText(), wordWithTextPositions.getTextPositions());
            if (i < size - 1) {
                writeWordSeparator();
            }
        }
    }

    private List<WordWithTextPositions> normalize(List<LineItem> list, boolean z, boolean z2) {
        LinkedList linkedList = new LinkedList();
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        if (z) {
            for (int size = list.size() - 1; size >= 0; size--) {
                sb = normalizeAdd(linkedList, sb, arrayList, list.get(size));
            }
        } else {
            Iterator<LineItem> it = list.iterator();
            while (it.hasNext()) {
                sb = normalizeAdd(linkedList, sb, arrayList, it.next());
            }
        }
        if (sb.length() > 0) {
            linkedList.add(createWord(sb.toString(), arrayList));
        }
        return linkedList;
    }

    private WordWithTextPositions createWord(String str, List<TextPosition> list) {
        return new WordWithTextPositions(normalizeWord(str), list);
    }

    private String normalizeWord(String str) {
        int length = str.length();
        int i = 0;
        StringBuilder sb = null;
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if ((64256 <= charAt && charAt <= 65023) || (65136 <= charAt && charAt <= 65279)) {
                if (sb == null) {
                    sb = new StringBuilder(length * 2);
                }
                sb.append(str.substring(i2, i));
                if (charAt == 65010 && i > 0) {
                    int i3 = i - 1;
                    if (str.charAt(i3) == 1575 || str.charAt(i3) == 65165) {
                        sb.append("لله");
                        i2 = i + 1;
                    }
                }
                sb.append(Normalizer.normalize(str.substring(i, i + 1), Normalizer.Form.NFKC).trim());
                i2 = i + 1;
            }
            i++;
        }
        if (sb == null) {
            return str;
        }
        sb.append(str.substring(i2, i));
        return sb.toString();
    }

    private StringBuilder normalizeAdd(List<WordWithTextPositions> list, StringBuilder sb, List<TextPosition> list2, LineItem lineItem) {
        if (lineItem.isWordSeparator()) {
            list.add(createWord(sb.toString(), new ArrayList(list2)));
            StringBuilder sb2 = new StringBuilder();
            list2.clear();
            return sb2;
        }
        TextPosition textPosition = lineItem.getTextPosition();
        sb.append(textPosition.getUnicode());
        list2.add(textPosition);
        return sb;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class LineItem {
        public static LineItem WORD_SEPARATOR = new LineItem();
        private final TextPosition textPosition;

        public static LineItem getWordSeparator() {
            return WORD_SEPARATOR;
        }

        private LineItem() {
            this.textPosition = null;
        }

        LineItem(TextPosition textPosition) {
            this.textPosition = textPosition;
        }

        public TextPosition getTextPosition() {
            return this.textPosition;
        }

        public boolean isWordSeparator() {
            return this.textPosition == null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class WordWithTextPositions {
        protected String text;
        protected List<TextPosition> textPositions;

        WordWithTextPositions(String str, List<TextPosition> list) {
            this.text = str;
            this.textPositions = list;
        }

        public String getText() {
            return this.text;
        }

        public List<TextPosition> getTextPositions() {
            return this.textPositions;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class PositionWrapper {
        private TextPosition position;
        private boolean isLineStart = false;
        private boolean isParagraphStart = false;
        private boolean isPageBreak = false;
        private boolean isHangingIndent = false;
        private boolean isArticleStart = false;

        public TextPosition getTextPosition() {
            return this.position;
        }

        public boolean isLineStart() {
            return this.isLineStart;
        }

        public void setLineStart() {
            this.isLineStart = true;
        }

        public boolean isParagraphStart() {
            return this.isParagraphStart;
        }

        public void setParagraphStart() {
            this.isParagraphStart = true;
        }

        public boolean isArticleStart() {
            return this.isArticleStart;
        }

        public void setArticleStart() {
            this.isArticleStart = true;
        }

        public boolean isPageBreak() {
            return this.isPageBreak;
        }

        public void setPageBreak() {
            this.isPageBreak = true;
        }

        public boolean isHangingIndent() {
            return this.isHangingIndent;
        }

        public void setHangingIndent() {
            this.isHangingIndent = true;
        }

        public PositionWrapper(TextPosition textPosition) {
            this.position = null;
            this.position = textPosition;
        }
    }
}
