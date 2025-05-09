package org.apache.pdfbox.pdmodel.font;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class PDCIDFontType2Embedder extends TrueTypeEmbedder {
    private final COSDictionary cidFont;
    private final COSDictionary dict;
    private final PDDocument document;
    private final Map<Integer, Integer> gidToUni;
    private final PDType0Font parent;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public enum State {
        FIRST,
        BRACKET,
        SERIAL
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PDCIDFontType2Embedder(PDDocument pDDocument, COSDictionary cOSDictionary, InputStream inputStream, boolean z, PDType0Font pDType0Font) throws IOException {
        super(pDDocument, cOSDictionary, inputStream, z);
        this.document = pDDocument;
        this.dict = cOSDictionary;
        this.parent = pDType0Font;
        cOSDictionary.setItem(COSName.SUBTYPE, COSName.TYPE0);
        cOSDictionary.setName(COSName.BASE_FONT, this.fontDescriptor.getFontName());
        cOSDictionary.setItem(COSName.ENCODING, COSName.IDENTITY_H);
        COSDictionary createCIDFont = createCIDFont();
        this.cidFont = createCIDFont;
        COSArray cOSArray = new COSArray();
        cOSArray.add((COSBase) createCIDFont);
        cOSDictionary.setItem(COSName.DESCENDANT_FONTS, (COSBase) cOSArray);
        this.gidToUni = new HashMap();
        int numGlyphs = this.ttf.getMaximumProfile().getNumGlyphs();
        for (int i = 1; i <= numGlyphs; i++) {
            Integer characterCode = this.cmap.getCharacterCode(i);
            if (characterCode != null) {
                this.gidToUni.put(Integer.valueOf(i), characterCode);
            }
        }
        buildToUnicodeCMap(null);
    }

    @Override // org.apache.pdfbox.pdmodel.font.TrueTypeEmbedder
    protected void buildSubset(InputStream inputStream, String str, Map<Integer, Integer> map) throws IOException {
        HashMap hashMap = new HashMap();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            hashMap.put(Integer.valueOf(entry.getValue().intValue()), Integer.valueOf(entry.getKey().intValue()));
        }
        buildFontFile2(inputStream);
        addNameTag(str);
        buildWidths(hashMap);
        buildCIDToGIDMap(hashMap);
        buildCIDSet(hashMap);
        buildToUnicodeCMap(map);
    }

    private void buildToUnicodeCMap(Map<Integer, Integer> map) throws IOException {
        int i;
        ToUnicodeWriter toUnicodeWriter = new ToUnicodeWriter();
        int numGlyphs = this.ttf.getMaximumProfile().getNumGlyphs();
        boolean z = false;
        for (int i2 = 1; i2 <= numGlyphs; i2++) {
            if (map == null) {
                i = i2;
            } else if (map.containsKey(Integer.valueOf(i2))) {
                i = map.get(Integer.valueOf(i2)).intValue();
            }
            Integer num = this.gidToUni.get(Integer.valueOf(i));
            if (num != null) {
                if (num.intValue() > 65535) {
                    z = true;
                }
                toUnicodeWriter.add(i, new String(new int[]{num.intValue()}, 0, 1));
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        toUnicodeWriter.writeTo(byteArrayOutputStream);
        PDStream pDStream = new PDStream(this.document, new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), false);
        pDStream.addCompression();
        if (z && this.document.getVersion() < 1.5d) {
            this.document.setVersion(1.5f);
        }
        this.dict.setItem(COSName.TO_UNICODE, pDStream);
    }

    private COSDictionary toCIDSystemInfo(String str, String str2, int i) {
        COSDictionary cOSDictionary = new COSDictionary();
        cOSDictionary.setString(COSName.REGISTRY, str);
        cOSDictionary.setString(COSName.ORDERING, str2);
        cOSDictionary.setInt(COSName.SUPPLEMENT, i);
        return cOSDictionary;
    }

    private COSDictionary createCIDFont() throws IOException {
        COSDictionary cOSDictionary = new COSDictionary();
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.FONT);
        cOSDictionary.setItem(COSName.SUBTYPE, (COSBase) COSName.CID_FONT_TYPE2);
        cOSDictionary.setName(COSName.BASE_FONT, this.fontDescriptor.getFontName());
        cOSDictionary.setItem(COSName.CIDSYSTEMINFO, (COSBase) toCIDSystemInfo("Adobe", "Identity", 0));
        cOSDictionary.setItem(COSName.FONT_DESC, (COSBase) this.fontDescriptor.getCOSObject());
        buildWidths(cOSDictionary);
        cOSDictionary.setItem(COSName.CID_TO_GID_MAP, (COSBase) COSName.IDENTITY);
        return cOSDictionary;
    }

    private void addNameTag(String str) throws IOException {
        String str2 = str + this.fontDescriptor.getFontName();
        this.dict.setName(COSName.BASE_FONT, str2);
        this.fontDescriptor.setFontName(str2);
        this.cidFont.setName(COSName.BASE_FONT, str2);
    }

    private void buildCIDToGIDMap(Map<Integer, Integer> map) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int intValue = ((Integer) Collections.max(map.keySet())).intValue();
        for (int i = 0; i <= intValue; i++) {
            int intValue2 = map.containsKey(Integer.valueOf(i)) ? map.get(Integer.valueOf(i)).intValue() : 0;
            byteArrayOutputStream.write(new byte[]{(byte) ((intValue2 >> 8) & 255), (byte) (intValue2 & 255)});
        }
        PDStream pDStream = new PDStream(this.document, new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), false);
        pDStream.getStream().setInt(COSName.LENGTH1, pDStream.getByteArray().length);
        pDStream.addCompression();
        this.cidFont.setItem(COSName.CID_TO_GID_MAP, pDStream);
    }

    private void buildCIDSet(Map<Integer, Integer> map) throws IOException {
        byte[] bArr = new byte[(((Integer) Collections.max(map.keySet())).intValue() / 8) + 1];
        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            int i = 1 << (7 - (intValue % 8));
            int i2 = intValue / 8;
            bArr[i2] = (byte) (i | bArr[i2]);
        }
        PDStream pDStream = new PDStream(this.document, new ByteArrayInputStream(bArr));
        pDStream.addCompression();
        this.fontDescriptor.setCIDSet(pDStream);
    }

    private void buildWidths(Map<Integer, Integer> map) throws IOException {
        float unitsPerEm = 1000.0f / this.ttf.getHeader().getUnitsPerEm();
        COSArray cOSArray = new COSArray();
        COSArray cOSArray2 = new COSArray();
        Iterator<Integer> it = map.keySet().iterator();
        int i = -1;
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (map.containsKey(Integer.valueOf(intValue))) {
                float advanceWidth = this.ttf.getHorizontalMetrics().getAdvanceWidth(map.get(Integer.valueOf(intValue)).intValue()) * unitsPerEm;
                if (i != intValue - 1) {
                    cOSArray2 = new COSArray();
                    cOSArray.add((COSBase) COSInteger.get(intValue));
                    cOSArray.add((COSBase) cOSArray2);
                }
                cOSArray2.add((COSBase) COSInteger.get(Math.round(advanceWidth)));
                i = intValue;
            }
        }
        this.cidFont.setItem(COSName.W, (COSBase) cOSArray);
    }

    private void buildWidths(COSDictionary cOSDictionary) throws IOException {
        int numberOfGlyphs = this.ttf.getNumberOfGlyphs();
        int[] iArr = new int[numberOfGlyphs * 2];
        for (int i = 0; i < numberOfGlyphs; i++) {
            int i2 = i * 2;
            iArr[i2] = i;
            iArr[i2 + 1] = this.ttf.getHorizontalMetrics().getAdvanceWidth(i);
        }
        cOSDictionary.setItem(COSName.W, (COSBase) getWidths(iArr));
    }

    private COSArray getWidths(int[] iArr) throws IOException {
        State state;
        if (iArr.length == 0) {
            throw new IllegalArgumentException("length of widths must be > 0");
        }
        float unitsPerEm = 1000.0f / this.ttf.getHeader().getUnitsPerEm();
        long j = iArr[0];
        int i = 1;
        long round = Math.round(iArr[1] * unitsPerEm);
        COSArray cOSArray = null;
        COSArray cOSArray2 = new COSArray();
        cOSArray2.add((COSBase) COSInteger.get(j));
        State state2 = State.FIRST;
        int i2 = 2;
        while (i2 < iArr.length) {
            long j2 = iArr[i2];
            int i3 = i2;
            long round2 = Math.round(iArr[i2 + 1] * unitsPerEm);
            int i4 = AnonymousClass1.$SwitchMap$org$apache$pdfbox$pdmodel$font$PDCIDFontType2Embedder$State[state2.ordinal()];
            if (i4 == i) {
                long j3 = j + 1;
                if (j2 == j3 && round2 == round) {
                    state = State.SERIAL;
                    state2 = state;
                } else if (j2 == j3) {
                    State state3 = State.BRACKET;
                    COSArray cOSArray3 = new COSArray();
                    cOSArray3.add((COSBase) COSInteger.get(round));
                    state2 = state3;
                    cOSArray = cOSArray3;
                } else {
                    COSArray cOSArray4 = new COSArray();
                    cOSArray4.add((COSBase) COSInteger.get(round));
                    cOSArray2.add((COSBase) cOSArray4);
                    cOSArray2.add((COSBase) COSInteger.get(j2));
                    cOSArray = cOSArray4;
                }
            } else if (i4 == 2) {
                long j4 = j + 1;
                if (j2 == j4 && round2 == round) {
                    State state4 = State.SERIAL;
                    cOSArray2.add((COSBase) cOSArray);
                    cOSArray2.add((COSBase) COSInteger.get(j));
                    state2 = state4;
                } else if (j2 == j4) {
                    cOSArray.add((COSBase) COSInteger.get(round));
                } else {
                    state = State.FIRST;
                    cOSArray.add((COSBase) COSInteger.get(round));
                    cOSArray2.add((COSBase) cOSArray);
                    cOSArray2.add((COSBase) COSInteger.get(j2));
                    state2 = state;
                }
            } else if (i4 == 3 && (j2 != j + 1 || round2 != round)) {
                cOSArray2.add((COSBase) COSInteger.get(j));
                cOSArray2.add((COSBase) COSInteger.get(round));
                cOSArray2.add((COSBase) COSInteger.get(j2));
                state = State.FIRST;
                state2 = state;
            }
            round = round2;
            i = 1;
            i2 = i3 + 2;
            j = j2;
        }
        int i5 = AnonymousClass1.$SwitchMap$org$apache$pdfbox$pdmodel$font$PDCIDFontType2Embedder$State[state2.ordinal()];
        if (i5 == 1) {
            COSArray cOSArray5 = new COSArray();
            cOSArray5.add((COSBase) COSInteger.get(round));
            cOSArray2.add((COSBase) cOSArray5);
        } else if (i5 == 2) {
            cOSArray.add((COSBase) COSInteger.get(round));
            cOSArray2.add((COSBase) cOSArray);
        } else if (i5 == 3) {
            cOSArray2.add((COSBase) COSInteger.get(j));
            cOSArray2.add((COSBase) COSInteger.get(round));
        }
        return cOSArray2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.apache.pdfbox.pdmodel.font.PDCIDFontType2Embedder$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$pdfbox$pdmodel$font$PDCIDFontType2Embedder$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$org$apache$pdfbox$pdmodel$font$PDCIDFontType2Embedder$State = iArr;
            try {
                iArr[State.FIRST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$pdfbox$pdmodel$font$PDCIDFontType2Embedder$State[State.BRACKET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$pdfbox$pdmodel$font$PDCIDFontType2Embedder$State[State.SERIAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public PDCIDFont getCIDFont() throws IOException {
        return PDFontFactory.createDescendantFont(this.cidFont, this.parent);
    }
}
