package org.apache.pdfbox.text;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.util.Matrix;
import org.bouncycastle.crypto.tls.CipherSuite;

/* loaded from: classes5.dex */
public final class TextPosition {
    private static final Map<Integer, String> DIACRITICS = createDiacritics();
    private final int[] charCodes;
    private final float endX;
    private final float endY;
    private final PDFont font;
    private final float fontSize;
    private final int fontSizePt;
    private final float maxHeight;
    private final float pageHeight;
    private final float pageWidth;
    private final int rotation;
    private final Matrix textMatrix;
    private String unicode;
    private final float widthOfSpace;
    private float[] widths;
    private final float x;
    private final float y;

    private static Map<Integer, String> createDiacritics() {
        HashMap hashMap = new HashMap();
        hashMap.put(96, "̀");
        hashMap.put(715, "̀");
        hashMap.put(39, "́");
        hashMap.put(697, "́");
        hashMap.put(714, "́");
        hashMap.put(94, "̂");
        hashMap.put(710, "̂");
        hashMap.put(126, "̃");
        hashMap.put(713, "̄");
        hashMap.put(Integer.valueOf(CipherSuite.TLS_PSK_WITH_NULL_SHA256), "̊");
        hashMap.put(698, "̋");
        hashMap.put(711, "̌");
        hashMap.put(712, "̍");
        hashMap.put(34, "̎");
        hashMap.put(699, "̒");
        hashMap.put(700, "̓");
        hashMap.put(1158, "̓");
        hashMap.put(1370, "̓");
        hashMap.put(Integer.valueOf(TypedValues.TransitionType.TYPE_FROM), "̔");
        hashMap.put(1157, "̔");
        hashMap.put(1369, "̔");
        hashMap.put(724, "̝");
        hashMap.put(725, "̞");
        hashMap.put(726, "̟");
        hashMap.put(727, "̠");
        hashMap.put(690, "̡");
        hashMap.put(716, "̩");
        hashMap.put(695, "̫");
        hashMap.put(717, "̱");
        hashMap.put(95, "̲");
        hashMap.put(8270, "͙");
        return hashMap;
    }

    public TextPosition(int i, float f, float f2, Matrix matrix, float f3, float f4, float f5, float f6, float f7, String str, int[] iArr, PDFont pDFont, float f8, int i2) {
        this.textMatrix = matrix;
        this.endX = f3;
        this.endY = f4;
        this.rotation = i;
        this.maxHeight = f5;
        this.pageHeight = f2;
        this.pageWidth = f;
        this.widths = new float[]{f6};
        this.widthOfSpace = f7;
        this.unicode = str;
        this.charCodes = iArr;
        this.font = pDFont;
        this.fontSize = f8;
        this.fontSizePt = i2;
        float f9 = i;
        this.x = getXRot(f9);
        if (i == 0 || i == 180) {
            this.y = f2 - getYLowerLeftRot(f9);
        } else {
            this.y = f - getYLowerLeftRot(f9);
        }
    }

    public String getUnicode() {
        return this.unicode;
    }

    public int[] getCharacterCodes() {
        return this.charCodes;
    }

    public Matrix getTextMatrix() {
        return this.textMatrix;
    }

    public float getDir() {
        float scaleY = this.textMatrix.getScaleY();
        float shearY = this.textMatrix.getShearY();
        float scaleX = this.textMatrix.getScaleX();
        float shearX = this.textMatrix.getShearX();
        if (scaleY > 0.0f && Math.abs(shearY) < shearX && Math.abs(scaleX) < scaleY && shearX > 0.0f) {
            return 0.0f;
        }
        if (scaleY < 0.0f && Math.abs(shearY) < Math.abs(shearX) && Math.abs(scaleX) < Math.abs(scaleY) && shearX < 0.0f) {
            return 180.0f;
        }
        if (Math.abs(scaleY) >= Math.abs(scaleX) || shearY <= 0.0f || scaleX >= 0.0f || Math.abs(shearX) >= shearY) {
            return (Math.abs(scaleY) >= scaleX || shearY >= 0.0f || scaleX <= 0.0f || Math.abs(shearX) >= Math.abs(shearY)) ? 0.0f : 270.0f;
        }
        return 90.0f;
    }

    private float getXRot(float f) {
        if (f == 0.0f) {
            return this.textMatrix.getTranslateX();
        }
        if (f == 90.0f) {
            return this.textMatrix.getTranslateY();
        }
        if (f == 180.0f) {
            return this.pageWidth - this.textMatrix.getTranslateX();
        }
        if (f == 270.0f) {
            return this.pageHeight - this.textMatrix.getTranslateX();
        }
        return 0.0f;
    }

    public float getX() {
        return this.x;
    }

    public float getXDirAdj() {
        return getXRot(getDir());
    }

    private float getYLowerLeftRot(float f) {
        if (f == 0.0f) {
            return this.textMatrix.getTranslateY();
        }
        if (f == 90.0f) {
            return this.pageWidth - this.textMatrix.getTranslateX();
        }
        if (f == 180.0f) {
            return this.pageHeight - this.textMatrix.getTranslateY();
        }
        if (f == 270.0f) {
            return this.textMatrix.getTranslateX();
        }
        return 0.0f;
    }

    public float getY() {
        return this.y;
    }

    public float getYDirAdj() {
        float f;
        float yLowerLeftRot;
        float dir = getDir();
        if (dir == 0.0f || dir == 180.0f) {
            f = this.pageHeight;
            yLowerLeftRot = getYLowerLeftRot(dir);
        } else {
            f = this.pageWidth;
            yLowerLeftRot = getYLowerLeftRot(dir);
        }
        return f - yLowerLeftRot;
    }

    private float getWidthRot(float f) {
        if (f == 90.0f || f == 270.0f) {
            return Math.abs(this.endY - this.textMatrix.getTranslateY());
        }
        return Math.abs(this.endX - this.textMatrix.getTranslateX());
    }

    public float getWidth() {
        return getWidthRot(this.rotation);
    }

    public float getWidthDirAdj() {
        return getWidthRot(getDir());
    }

    public float getHeight() {
        return this.maxHeight;
    }

    public float getHeightDir() {
        return this.maxHeight;
    }

    public float getFontSize() {
        return this.fontSize;
    }

    public float getFontSizeInPt() {
        return this.fontSizePt;
    }

    public PDFont getFont() {
        return this.font;
    }

    public float getWidthOfSpace() {
        return this.widthOfSpace;
    }

    public float getXScale() {
        return this.textMatrix.getScalingFactorX();
    }

    public float getYScale() {
        return this.textMatrix.getScalingFactorY();
    }

    public float[] getIndividualWidths() {
        return this.widths;
    }

    public boolean contains(TextPosition textPosition) {
        double xDirAdj = getXDirAdj();
        double xDirAdj2 = getXDirAdj() + getWidthDirAdj();
        double xDirAdj3 = textPosition.getXDirAdj();
        double xDirAdj4 = textPosition.getXDirAdj() + textPosition.getWidthDirAdj();
        if (xDirAdj4 <= xDirAdj || xDirAdj3 >= xDirAdj2 || textPosition.getYDirAdj() + textPosition.getHeightDir() < getYDirAdj() || textPosition.getYDirAdj() > getYDirAdj() + getHeightDir()) {
            return false;
        }
        return (xDirAdj3 <= xDirAdj || xDirAdj4 <= xDirAdj2) ? xDirAdj3 >= xDirAdj || xDirAdj4 >= xDirAdj2 || (xDirAdj4 - xDirAdj) / ((double) getWidthDirAdj()) > 0.15d : (xDirAdj2 - xDirAdj3) / ((double) getWidthDirAdj()) > 0.15d;
    }

    public void mergeDiacritic(TextPosition textPosition) {
        if (textPosition.getUnicode().length() > 1) {
            return;
        }
        float xDirAdj = textPosition.getXDirAdj();
        float f = textPosition.widths[0] + xDirAdj;
        float xDirAdj2 = getXDirAdj();
        int length = this.unicode.length();
        float f2 = xDirAdj2;
        boolean z = false;
        for (int i = 0; i < length && !z; i++) {
            float[] fArr = this.widths;
            float f3 = fArr[i] + f2;
            if (xDirAdj >= f2 || f > f3) {
                if (xDirAdj < f2 && f > f3) {
                    insertDiacritic(i, textPosition);
                } else if (xDirAdj >= f2 && f <= f3) {
                    insertDiacritic(i, textPosition);
                } else {
                    if (xDirAdj >= f2 && f > f3 && i == length - 1) {
                        insertDiacritic(i, textPosition);
                    }
                    f2 += this.widths[i];
                }
            } else if (i == 0) {
                insertDiacritic(i, textPosition);
            } else {
                int i2 = i - 1;
                if ((f - f2) / fArr[i] >= (f2 - xDirAdj) / fArr[i2]) {
                    insertDiacritic(i, textPosition);
                } else {
                    insertDiacritic(i2, textPosition);
                }
            }
            z = true;
            f2 += this.widths[i];
        }
    }

    private void insertDiacritic(int i, TextPosition textPosition) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.unicode.substring(0, i));
        float[] fArr = this.widths;
        float[] fArr2 = new float[fArr.length + 1];
        System.arraycopy(fArr, 0, fArr2, 0, i);
        sb.append(this.unicode.charAt(i));
        fArr2[i] = this.widths[i];
        sb.append(combineDiacritic(textPosition.getUnicode()));
        int i2 = i + 1;
        fArr2[i2] = 0.0f;
        String str = this.unicode;
        sb.append(str.substring(i2, str.length()));
        System.arraycopy(this.widths, i2, fArr2, i + 2, (r1.length - i) - 1);
        this.unicode = sb.toString();
        this.widths = fArr2;
    }

    private String combineDiacritic(String str) {
        int codePointAt = str.codePointAt(0);
        Map<Integer, String> map = DIACRITICS;
        if (map.containsKey(Integer.valueOf(codePointAt))) {
            return map.get(Integer.valueOf(codePointAt));
        }
        return Normalizer.normalize(str, Normalizer.Form.NFKC).trim();
    }

    public boolean isDiacritic() {
        String unicode = getUnicode();
        if (unicode.length() != 1) {
            return false;
        }
        int type = Character.getType(unicode.charAt(0));
        return type == 6 || type == 27 || type == 4;
    }

    public String toString() {
        return getUnicode();
    }
}
