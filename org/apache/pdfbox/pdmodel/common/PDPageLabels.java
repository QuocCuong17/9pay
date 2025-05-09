package org.apache.pdfbox.pdmodel.common;

import androidx.media3.exoplayer.upstream.CmcdData;
import io.sentry.protocol.ViewHierarchyNode;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;

/* loaded from: classes5.dex */
public class PDPageLabels implements COSObjectable {
    private PDDocument doc;
    private Map<Integer, PDPageLabelRange> labels;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public interface LabelHandler {
        void newLabel(int i, String str);
    }

    public PDPageLabels(PDDocument pDDocument) {
        this.labels = new TreeMap();
        this.doc = pDDocument;
        PDPageLabelRange pDPageLabelRange = new PDPageLabelRange();
        pDPageLabelRange.setStyle("D");
        this.labels.put(0, pDPageLabelRange);
    }

    public PDPageLabels(PDDocument pDDocument, COSDictionary cOSDictionary) throws IOException {
        this(pDDocument);
        if (cOSDictionary == null) {
            return;
        }
        findLabels(new PDNumberTreeNode(cOSDictionary, COSDictionary.class));
    }

    private void findLabels(PDNumberTreeNode pDNumberTreeNode) throws IOException {
        if (pDNumberTreeNode.getKids() != null) {
            Iterator<PDNumberTreeNode> it = pDNumberTreeNode.getKids().iterator();
            while (it.hasNext()) {
                findLabels(it.next());
            }
        } else if (pDNumberTreeNode.getNumbers() != null) {
            for (Map.Entry<Integer, COSObjectable> entry : pDNumberTreeNode.getNumbers().entrySet()) {
                if (entry.getKey().intValue() >= 0) {
                    this.labels.put(entry.getKey(), new PDPageLabelRange((COSDictionary) entry.getValue()));
                }
            }
        }
    }

    public int getPageRangeCount() {
        return this.labels.size();
    }

    public PDPageLabelRange getPageLabelRange(int i) {
        return this.labels.get(Integer.valueOf(i));
    }

    public void setLabelItem(int i, PDPageLabelRange pDPageLabelRange) {
        if (i < 0) {
            throw new IllegalArgumentException("startPage parameter of setLabelItem may not be < 0");
        }
        this.labels.put(Integer.valueOf(i), pDPageLabelRange);
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        COSDictionary cOSDictionary = new COSDictionary();
        COSArray cOSArray = new COSArray();
        for (Map.Entry<Integer, PDPageLabelRange> entry : this.labels.entrySet()) {
            cOSArray.add((COSBase) COSInteger.get(entry.getKey().intValue()));
            cOSArray.add(entry.getValue());
        }
        cOSDictionary.setItem(COSName.NUMS, (COSBase) cOSArray);
        return cOSDictionary;
    }

    public Map<String, Integer> getPageIndicesByLabels() {
        final HashMap hashMap = new HashMap(this.doc.getNumberOfPages());
        computeLabels(new LabelHandler() { // from class: org.apache.pdfbox.pdmodel.common.PDPageLabels.1
            @Override // org.apache.pdfbox.pdmodel.common.PDPageLabels.LabelHandler
            public void newLabel(int i, String str) {
                hashMap.put(str, Integer.valueOf(i));
            }
        });
        return hashMap;
    }

    public String[] getLabelsByPageIndices() {
        final String[] strArr = new String[this.doc.getNumberOfPages()];
        computeLabels(new LabelHandler() { // from class: org.apache.pdfbox.pdmodel.common.PDPageLabels.2
            @Override // org.apache.pdfbox.pdmodel.common.PDPageLabels.LabelHandler
            public void newLabel(int i, String str) {
                if (i < PDPageLabels.this.doc.getNumberOfPages()) {
                    strArr[i] = str;
                }
            }
        });
        return strArr;
    }

    private void computeLabels(LabelHandler labelHandler) {
        Iterator<Map.Entry<Integer, PDPageLabelRange>> it = this.labels.entrySet().iterator();
        if (it.hasNext()) {
            int i = 0;
            Map.Entry<Integer, PDPageLabelRange> next = it.next();
            while (it.hasNext()) {
                Map.Entry<Integer, PDPageLabelRange> next2 = it.next();
                LabelGenerator labelGenerator = new LabelGenerator(next.getValue(), next2.getKey().intValue() - next.getKey().intValue());
                while (labelGenerator.hasNext()) {
                    labelHandler.newLabel(i, labelGenerator.next());
                    i++;
                }
                next = next2;
            }
            LabelGenerator labelGenerator2 = new LabelGenerator(next.getValue(), this.doc.getNumberOfPages() - next.getKey().intValue());
            while (labelGenerator2.hasNext()) {
                labelHandler.newLabel(i, labelGenerator2.next());
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class LabelGenerator implements Iterator<String> {
        private static final String[][] ROMANS = {new String[]{"", CmcdData.Factory.OBJECT_TYPE_INIT_SEGMENT, "ii", "iii", "iv", "v", "vi", "vii", "viii", "ix"}, new String[]{"", ViewHierarchyNode.JsonKeys.X, "xx", "xxx", "xl", CmcdData.Factory.STREAM_TYPE_LIVE, "lx", "lxx", "lxxx", "xc"}, new String[]{"", "c", "cc", "ccc", "cd", "d", "dc", "dcc", "dccc", "cm"}};
        private int currentPage = 0;
        private final PDPageLabelRange labelInfo;
        private final int numPages;

        LabelGenerator(PDPageLabelRange pDPageLabelRange, int i) {
            this.labelInfo = pDPageLabelRange;
            this.numPages = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.currentPage < this.numPages;
        }

        @Override // java.util.Iterator
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            StringBuilder sb = new StringBuilder();
            if (this.labelInfo.getPrefix() != null) {
                String prefix = this.labelInfo.getPrefix();
                while (prefix.lastIndexOf(0) != -1) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                }
                sb.append(prefix);
            }
            if (this.labelInfo.getStyle() != null) {
                sb.append(getNumber(this.labelInfo.getStart() + this.currentPage, this.labelInfo.getStyle()));
            }
            this.currentPage++;
            return sb.toString();
        }

        private String getNumber(int i, String str) {
            if ("D".equals(str)) {
                return Integer.toString(i);
            }
            if ("a".equals(str)) {
                return makeLetterLabel(i);
            }
            if ("A".equals(str)) {
                return makeLetterLabel(i).toUpperCase();
            }
            if (PDPageLabelRange.STYLE_ROMAN_LOWER.equals(str)) {
                return makeRomanLabel(i);
            }
            if ("R".equals(str)) {
                return makeRomanLabel(i).toUpperCase();
            }
            return Integer.toString(i);
        }

        private static String makeRomanLabel(int i) {
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < 3 && i > 0; i2++) {
                sb.insert(0, ROMANS[i2][i % 10]);
                i /= 10;
            }
            for (int i3 = 0; i3 < i; i3++) {
                sb.insert(0, 'm');
            }
            return sb.toString();
        }

        private static String makeLetterLabel(int i) {
            StringBuilder sb = new StringBuilder();
            int i2 = i / 26;
            int i3 = i % 26;
            int signum = i2 + Integer.signum(i3);
            int signum2 = i3 + ((1 - Integer.signum(i3)) * 26) + 64;
            for (int i4 = 0; i4 < signum; i4++) {
                sb.appendCodePoint(signum2);
            }
            return sb.toString();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
