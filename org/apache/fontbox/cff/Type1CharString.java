package org.apache.fontbox.cff;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.fontbox.encoding.StandardEncoding;
import org.apache.fontbox.type1.Type1CharStringReader;
import org.apache.pdfbox.util.awt.AffineTransform;

/* loaded from: classes5.dex */
public class Type1CharString {
    protected int commandCount;
    private PointF current;
    private List<PointF> flexPoints;
    private Type1CharStringReader font;
    private String fontName;
    private String glyphName;
    private boolean isFlex;
    private PointF leftSideBearing;
    private Path path;
    protected List<Object> type1Sequence;
    private int width;

    public Type1CharString(Type1CharStringReader type1CharStringReader, String str, String str2, List<Object> list) {
        this(type1CharStringReader, str, str2);
        this.type1Sequence = list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Type1CharString(Type1CharStringReader type1CharStringReader, String str, String str2) {
        this.path = null;
        this.width = 0;
        this.leftSideBearing = null;
        this.current = null;
        this.isFlex = false;
        this.flexPoints = new ArrayList();
        this.font = type1CharStringReader;
        this.fontName = str;
        this.glyphName = str2;
        this.current = new PointF(0.0f, 0.0f);
    }

    public String getName() {
        return this.glyphName;
    }

    public RectF getBounds() {
        if (this.path == null) {
            render();
        }
        this.path.computeBounds(null, true);
        return null;
    }

    public int getWidth() {
        if (this.path == null) {
            render();
        }
        return this.width;
    }

    public Path getPath() {
        if (this.path == null) {
            render();
        }
        return this.path;
    }

    public List<Object> getType1Sequence() {
        return this.type1Sequence;
    }

    private void render() {
        this.path = new Path();
        this.leftSideBearing = new PointF(0.0f, 0.0f);
        this.width = 0;
        new CharStringHandler() { // from class: org.apache.fontbox.cff.Type1CharString.1
            @Override // org.apache.fontbox.cff.CharStringHandler
            public List<Integer> handleCommand(List<Integer> list, CharStringCommand charStringCommand) {
                return Type1CharString.this.handleCommand(list, charStringCommand);
            }
        }.handleSequence(this.type1Sequence);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Integer> handleCommand(List<Integer> list, CharStringCommand charStringCommand) {
        this.commandCount++;
        String str = CharStringCommand.TYPE1_VOCABULARY.get(charStringCommand.getKey());
        if ("rmoveto".equals(str)) {
            if (this.isFlex) {
                this.flexPoints.add(new PointF(list.get(0).intValue(), list.get(1).intValue()));
                return null;
            }
            rmoveTo(list.get(0), list.get(1));
            return null;
        }
        if ("vmoveto".equals(str)) {
            if (this.isFlex) {
                this.flexPoints.add(new PointF(0.0f, list.get(0).intValue()));
                return null;
            }
            rmoveTo(0, list.get(0));
            return null;
        }
        if ("hmoveto".equals(str)) {
            if (!this.isFlex) {
                rmoveTo(list.get(0), 0);
                return null;
            }
            this.flexPoints.add(new PointF(list.get(0).intValue(), 0.0f));
            return null;
        }
        if ("rlineto".equals(str)) {
            rlineTo(list.get(0), list.get(1));
            return null;
        }
        if ("hlineto".equals(str)) {
            rlineTo(list.get(0), 0);
            return null;
        }
        if ("vlineto".equals(str)) {
            rlineTo(0, list.get(0));
            return null;
        }
        if ("rrcurveto".equals(str)) {
            rrcurveTo(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
            return null;
        }
        if ("closepath".equals(str)) {
            closepath();
            return null;
        }
        if ("sbw".equals(str)) {
            this.leftSideBearing = new PointF(list.get(0).intValue(), list.get(1).intValue());
            this.width = list.get(2).intValue();
            this.current.set(this.leftSideBearing);
            return null;
        }
        if ("hsbw".equals(str)) {
            this.leftSideBearing = new PointF(list.get(0).intValue(), 0.0f);
            this.width = list.get(1).intValue();
            this.current.set(this.leftSideBearing);
            return null;
        }
        if ("vhcurveto".equals(str)) {
            rrcurveTo(0, list.get(0), list.get(1), list.get(2), list.get(3), 0);
            return null;
        }
        if ("hvcurveto".equals(str)) {
            rrcurveTo(list.get(0), 0, list.get(1), list.get(2), 0, list.get(3));
            return null;
        }
        if ("seac".equals(str)) {
            seac(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4));
            return null;
        }
        if ("setcurrentpoint".equals(str)) {
            setcurrentpoint(list.get(0).intValue(), list.get(1).intValue());
            return null;
        }
        if ("callothersubr".equals(str)) {
            callothersubr(list.get(0).intValue());
            return null;
        }
        if (TtmlNode.TAG_DIV.equals(str)) {
            int intValue = list.get(list.size() - 2).intValue() / list.get(list.size() - 1).intValue();
            ArrayList arrayList = new ArrayList(list);
            arrayList.remove(arrayList.size() - 1);
            arrayList.remove(arrayList.size() - 1);
            arrayList.add(Integer.valueOf(intValue));
            return arrayList;
        }
        if ("hstem".equals(str) || "vstem".equals(str) || "hstem3".equals(str) || "vstem3".equals(str) || "dotsection".equals(str) || "endchar".equals(str)) {
            return null;
        }
        if (str != null) {
            throw new IllegalArgumentException("Unhandled command: " + str);
        }
        Log.w("PdfBoxAndroid", "Unknown charstring command: " + charStringCommand.getKey());
        return null;
    }

    private void setcurrentpoint(int i, int i2) {
        this.current.set(i, i2);
    }

    private void callothersubr(int i) {
        if (i != 0) {
            if (i == 1) {
                this.isFlex = true;
                return;
            }
            throw new IllegalArgumentException("Unexpected other subroutine: " + i);
        }
        this.isFlex = false;
        if (this.flexPoints.size() < 7) {
            Log.w("PdfBoxAndroid", "flex without moveTo in font " + this.fontName + ", glyph " + this.glyphName + ", command " + this.commandCount);
            return;
        }
        PointF pointF = this.flexPoints.get(0);
        pointF.set(this.current.x + pointF.x, this.current.y + pointF.y);
        PointF pointF2 = this.flexPoints.get(1);
        pointF2.set(pointF.x + pointF2.x, pointF.y + pointF2.y);
        pointF2.set(pointF2.x - this.current.x, pointF2.y - this.current.y);
        rrcurveTo(Float.valueOf(this.flexPoints.get(1).x), Float.valueOf(this.flexPoints.get(1).y), Float.valueOf(this.flexPoints.get(2).x), Float.valueOf(this.flexPoints.get(2).y), Float.valueOf(this.flexPoints.get(3).x), Float.valueOf(this.flexPoints.get(3).y));
        rrcurveTo(Float.valueOf(this.flexPoints.get(4).x), Float.valueOf(this.flexPoints.get(4).y), Float.valueOf(this.flexPoints.get(5).x), Float.valueOf(this.flexPoints.get(5).y), Float.valueOf(this.flexPoints.get(6).x), Float.valueOf(this.flexPoints.get(6).y));
        this.flexPoints.clear();
    }

    private void rmoveTo(Number number, Number number2) {
        float floatValue = this.current.x + number.floatValue();
        float floatValue2 = this.current.y + number2.floatValue();
        this.path.moveTo(floatValue, floatValue2);
        this.current.set(floatValue, floatValue2);
    }

    private void rlineTo(Number number, Number number2) {
        float floatValue = this.current.x + number.floatValue();
        float floatValue2 = this.current.y + number2.floatValue();
        if (this.path.isEmpty()) {
            Log.w("PdfBoxAndroid", "rlineTo without initial moveTo in font " + this.fontName + ", glyph " + this.glyphName);
            this.path.moveTo(floatValue, floatValue2);
        } else {
            this.path.lineTo(floatValue, floatValue2);
        }
        this.current.set(floatValue, floatValue2);
    }

    private void rrcurveTo(Number number, Number number2, Number number3, Number number4, Number number5, Number number6) {
        float floatValue = this.current.x + number.floatValue();
        float floatValue2 = this.current.y + number2.floatValue();
        float floatValue3 = floatValue + number3.floatValue();
        float floatValue4 = floatValue2 + number4.floatValue();
        float floatValue5 = number5.floatValue() + floatValue3;
        float floatValue6 = number6.floatValue() + floatValue4;
        if (this.path.isEmpty()) {
            Log.w("PdfBoxAndroid", "rrcurveTo without initial moveTo in font " + this.fontName + ", glyph " + this.glyphName);
            this.path.moveTo(floatValue5, floatValue6);
        } else {
            this.path.cubicTo(floatValue, floatValue2, floatValue3, floatValue4, floatValue5, floatValue6);
        }
        this.current.set(floatValue5, floatValue6);
    }

    private void closepath() {
        if (this.path.isEmpty()) {
            Log.w("PdfBoxAndroid", "closepath without initial moveTo in font " + this.fontName + ", glyph " + this.glyphName);
        } else {
            this.path.close();
        }
        this.path.moveTo(this.current.x, this.current.y);
    }

    private void seac(Number number, Number number2, Number number3, Number number4, Number number5) {
        String name = StandardEncoding.INSTANCE.getName(number4.intValue());
        if (name != null) {
            try {
                this.font.getType1CharString(name);
            } catch (IOException unused) {
                Log.w("PdfBoxAndroid", "invalid seac character in glyph " + this.glyphName + " of font " + this.fontName);
            }
        }
        String name2 = StandardEncoding.INSTANCE.getName(number5.intValue());
        if (name2 != null) {
            try {
                this.font.getType1CharString(name2);
                AffineTransform.getTranslateInstance(this.leftSideBearing.x + number2.floatValue(), this.leftSideBearing.y + number3.floatValue());
            } catch (IOException unused2) {
                Log.w("PdfBoxAndroid", "invalid seac character in glyph " + this.glyphName + " of font " + this.fontName);
            }
        }
    }

    public String toString() {
        return this.type1Sequence.toString().replace("|", IOUtils.LINE_SEPARATOR_UNIX).replace(",", " ");
    }
}
