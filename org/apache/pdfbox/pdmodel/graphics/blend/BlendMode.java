package org.apache.pdfbox.pdmodel.graphics.blend;

import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public abstract class BlendMode {
    private static final Map<COSName, BlendMode> BLEND_MODES;
    public static final SeparableBlendMode COLOR_BURN;
    public static final SeparableBlendMode COLOR_DODGE;
    public static final SeparableBlendMode COMPATIBLE;
    public static final SeparableBlendMode DARKEN;
    public static final SeparableBlendMode DIFFERENCE;
    public static final SeparableBlendMode EXCLUSION;
    public static final SeparableBlendMode HARD_LIGHT;
    public static final SeparableBlendMode LIGHTEN;
    public static final SeparableBlendMode MULTIPLY;
    public static final SeparableBlendMode NORMAL;
    public static final SeparableBlendMode OVERLAY;
    public static final SeparableBlendMode SCREEN;
    public static final SeparableBlendMode SOFT_LIGHT;

    public static BlendMode getInstance(COSBase cOSBase) {
        BlendMode blendMode = null;
        if (cOSBase instanceof COSName) {
            blendMode = BLEND_MODES.get((COSName) cOSBase);
        } else if (cOSBase instanceof COSArray) {
            COSArray cOSArray = (COSArray) cOSBase;
            for (int i = 0; i < cOSArray.size() && (blendMode = BLEND_MODES.get(cOSArray.get(i))) == null; i++) {
            }
        }
        return blendMode != null ? blendMode : COMPATIBLE;
    }

    static {
        SeparableBlendMode separableBlendMode = new SeparableBlendMode() { // from class: org.apache.pdfbox.pdmodel.graphics.blend.BlendMode.1
            @Override // org.apache.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return f;
            }
        };
        NORMAL = separableBlendMode;
        COMPATIBLE = separableBlendMode;
        MULTIPLY = new SeparableBlendMode() { // from class: org.apache.pdfbox.pdmodel.graphics.blend.BlendMode.2
            @Override // org.apache.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return f * f2;
            }
        };
        SCREEN = new SeparableBlendMode() { // from class: org.apache.pdfbox.pdmodel.graphics.blend.BlendMode.3
            @Override // org.apache.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return (f + f2) - (f * f2);
            }
        };
        OVERLAY = new SeparableBlendMode() { // from class: org.apache.pdfbox.pdmodel.graphics.blend.BlendMode.4
            @Override // org.apache.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return ((double) f2) <= 0.5d ? f2 * 2.0f * f : (((f + f2) - (f * f2)) * 2.0f) - 1.0f;
            }
        };
        DARKEN = new SeparableBlendMode() { // from class: org.apache.pdfbox.pdmodel.graphics.blend.BlendMode.5
            @Override // org.apache.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return Math.min(f, f2);
            }
        };
        LIGHTEN = new SeparableBlendMode() { // from class: org.apache.pdfbox.pdmodel.graphics.blend.BlendMode.6
            @Override // org.apache.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return Math.max(f, f2);
            }
        };
        COLOR_DODGE = new SeparableBlendMode() { // from class: org.apache.pdfbox.pdmodel.graphics.blend.BlendMode.7
            @Override // org.apache.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                if (f < 1.0f) {
                    return Math.min(1.0f, f2 / (1.0f - f));
                }
                return 1.0f;
            }
        };
        COLOR_BURN = new SeparableBlendMode() { // from class: org.apache.pdfbox.pdmodel.graphics.blend.BlendMode.8
            @Override // org.apache.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                if (f > 0.0f) {
                    return 1.0f - Math.min(1.0f, (1.0f - f2) / f);
                }
                return 0.0f;
            }
        };
        HARD_LIGHT = new SeparableBlendMode() { // from class: org.apache.pdfbox.pdmodel.graphics.blend.BlendMode.9
            @Override // org.apache.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return ((double) f) <= 0.5d ? f2 * 2.0f * f : (((f + f2) - (f * f2)) * 2.0f) - 1.0f;
            }
        };
        SOFT_LIGHT = new SeparableBlendMode() { // from class: org.apache.pdfbox.pdmodel.graphics.blend.BlendMode.10
            @Override // org.apache.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                if (f <= 0.5d) {
                    return f2 - (((1.0f - (f * 2.0f)) * f2) * (1.0f - f2));
                }
                double d = f2;
                return f2 + (((f * 2.0f) - 1.0f) * ((d <= 0.25d ? ((((16.0f * f2) - 12.0f) * f2) + 4.0f) * f2 : (float) Math.sqrt(d)) - f2));
            }
        };
        DIFFERENCE = new SeparableBlendMode() { // from class: org.apache.pdfbox.pdmodel.graphics.blend.BlendMode.11
            @Override // org.apache.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return Math.abs(f2 - f);
            }
        };
        EXCLUSION = new SeparableBlendMode() { // from class: org.apache.pdfbox.pdmodel.graphics.blend.BlendMode.12
            @Override // org.apache.pdfbox.pdmodel.graphics.blend.SeparableBlendMode
            public float blendChannel(float f, float f2) {
                return (f2 + f) - ((f2 * 2.0f) * f);
            }
        };
        BLEND_MODES = createBlendModeMap();
    }

    private static Map<COSName, BlendMode> createBlendModeMap() {
        HashMap hashMap = new HashMap();
        hashMap.put(COSName.NORMAL, NORMAL);
        hashMap.put(COSName.COMPATIBLE, COMPATIBLE);
        hashMap.put(COSName.MULTIPLY, MULTIPLY);
        hashMap.put(COSName.SCREEN, SCREEN);
        hashMap.put(COSName.OVERLAY, OVERLAY);
        hashMap.put(COSName.DARKEN, DARKEN);
        hashMap.put(COSName.LIGHTEN, LIGHTEN);
        hashMap.put(COSName.COLOR_DODGE, COLOR_DODGE);
        hashMap.put(COSName.COLOR_BURN, COLOR_BURN);
        hashMap.put(COSName.HARD_LIGHT, HARD_LIGHT);
        hashMap.put(COSName.SOFT_LIGHT, SOFT_LIGHT);
        hashMap.put(COSName.DIFFERENCE, DIFFERENCE);
        hashMap.put(COSName.EXCLUSION, EXCLUSION);
        return hashMap;
    }
}
