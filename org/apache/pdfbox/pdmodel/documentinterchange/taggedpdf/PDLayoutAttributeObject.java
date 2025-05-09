package org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.color.PDGamma;

/* loaded from: classes5.dex */
public class PDLayoutAttributeObject extends PDStandardAttributeObject {
    private static final String BACKGROUND_COLOR = "BackgroundColor";
    private static final String BASELINE_SHIFT = "BaselineShift";
    private static final String BBOX = "BBox";
    private static final String BLOCK_ALIGN = "BlockAlign";
    public static final String BLOCK_ALIGN_AFTER = "After";
    public static final String BLOCK_ALIGN_BEFORE = "Before";
    public static final String BLOCK_ALIGN_JUSTIFY = "Justify";
    public static final String BLOCK_ALIGN_MIDDLE = "Middle";
    private static final String BORDER_COLOR = "BorderColor";
    private static final String BORDER_STYLE = "BorderStyle";
    public static final String BORDER_STYLE_DASHED = "Dashed";
    public static final String BORDER_STYLE_DOTTED = "Dotted";
    public static final String BORDER_STYLE_DOUBLE = "Double";
    public static final String BORDER_STYLE_GROOVE = "Groove";
    public static final String BORDER_STYLE_HIDDEN = "Hidden";
    public static final String BORDER_STYLE_INSET = "Inset";
    public static final String BORDER_STYLE_NONE = "None";
    public static final String BORDER_STYLE_OUTSET = "Outset";
    public static final String BORDER_STYLE_RIDGE = "Ridge";
    public static final String BORDER_STYLE_SOLID = "Solid";
    private static final String BORDER_THICKNESS = "BorderThickness";
    private static final String COLOR = "Color";
    private static final String COLUMN_COUNT = "ColumnCount";
    private static final String COLUMN_GAP = "ColumnGap";
    private static final String COLUMN_WIDTHS = "ColumnWidths";
    private static final String END_INDENT = "EndIndent";
    private static final String GLYPH_ORIENTATION_VERTICAL = "GlyphOrientationVertical";
    public static final String GLYPH_ORIENTATION_VERTICAL_180_DEGREES = "180";
    public static final String GLYPH_ORIENTATION_VERTICAL_270_DEGREES = "270";
    public static final String GLYPH_ORIENTATION_VERTICAL_360_DEGREES = "360";
    public static final String GLYPH_ORIENTATION_VERTICAL_90_DEGREES = "90";
    public static final String GLYPH_ORIENTATION_VERTICAL_AUTO = "Auto";
    public static final String GLYPH_ORIENTATION_VERTICAL_MINUS_180_DEGREES = "-180";
    public static final String GLYPH_ORIENTATION_VERTICAL_MINUS_90_DEGREES = "-90";
    public static final String GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES = "0";
    private static final String HEIGHT = "Height";
    public static final String HEIGHT_AUTO = "Auto";
    private static final String INLINE_ALIGN = "InlineAlign";
    public static final String INLINE_ALIGN_CENTER = "Center";
    public static final String INLINE_ALIGN_END = "End";
    public static final String INLINE_ALIGN_START = "Start";
    private static final String LINE_HEIGHT = "LineHeight";
    public static final String LINE_HEIGHT_AUTO = "Auto";
    public static final String LINE_HEIGHT_NORMAL = "Normal";
    public static final String OWNER_LAYOUT = "Layout";
    private static final String PADDING = "Padding";
    private static final String PLACEMENT = "Placement";
    public static final String PLACEMENT_BEFORE = "Before";
    public static final String PLACEMENT_BLOCK = "Block";
    public static final String PLACEMENT_END = "End";
    public static final String PLACEMENT_INLINE = "Inline";
    public static final String PLACEMENT_START = "Start";
    private static final String RUBY_ALIGN = "RubyAlign";
    public static final String RUBY_ALIGN_CENTER = "Center";
    public static final String RUBY_ALIGN_DISTRIBUTE = "Distribute";
    public static final String RUBY_ALIGN_END = "End";
    public static final String RUBY_ALIGN_JUSTIFY = "Justify";
    public static final String RUBY_ALIGN_START = "Start";
    private static final String RUBY_POSITION = "RubyPosition";
    public static final String RUBY_POSITION_AFTER = "After";
    public static final String RUBY_POSITION_BEFORE = "Before";
    public static final String RUBY_POSITION_INLINE = "Inline";
    public static final String RUBY_POSITION_WARICHU = "Warichu";
    private static final String SPACE_AFTER = "SpaceAfter";
    private static final String SPACE_BEFORE = "SpaceBefore";
    private static final String START_INDENT = "StartIndent";
    private static final String TEXT_ALIGN = "TextAlign";
    public static final String TEXT_ALIGN_CENTER = "Center";
    public static final String TEXT_ALIGN_END = "End";
    public static final String TEXT_ALIGN_JUSTIFY = "Justify";
    public static final String TEXT_ALIGN_START = "Start";
    private static final String TEXT_DECORATION_COLOR = "TextDecorationColor";
    private static final String TEXT_DECORATION_THICKNESS = "TextDecorationThickness";
    private static final String TEXT_DECORATION_TYPE = "TextDecorationType";
    public static final String TEXT_DECORATION_TYPE_LINE_THROUGH = "LineThrough";
    public static final String TEXT_DECORATION_TYPE_NONE = "None";
    public static final String TEXT_DECORATION_TYPE_OVERLINE = "Overline";
    public static final String TEXT_DECORATION_TYPE_UNDERLINE = "Underline";
    private static final String TEXT_INDENT = "TextIndent";
    private static final String T_BORDER_STYLE = "TBorderStyle";
    private static final String T_PADDING = "TPadding";
    private static final String WIDTH = "Width";
    public static final String WIDTH_AUTO = "Auto";
    private static final String WRITING_MODE = "WritingMode";
    public static final String WRITING_MODE_LRTB = "LrTb";
    public static final String WRITING_MODE_RLTB = "RlTb";
    public static final String WRITING_MODE_TBRL = "TbRl";

    public PDLayoutAttributeObject() {
        setOwner(OWNER_LAYOUT);
    }

    public PDLayoutAttributeObject(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getPlacement() {
        return getName(PLACEMENT, "Inline");
    }

    public void setPlacement(String str) {
        setName(PLACEMENT, str);
    }

    public String getWritingMode() {
        return getName(WRITING_MODE, WRITING_MODE_LRTB);
    }

    public void setWritingMode(String str) {
        setName(WRITING_MODE, str);
    }

    public PDGamma getBackgroundColor() {
        return getColor(BACKGROUND_COLOR);
    }

    public void setBackgroundColor(PDGamma pDGamma) {
        setColor(BACKGROUND_COLOR, pDGamma);
    }

    public Object getBorderColors() {
        return getColorOrFourColors(BORDER_COLOR);
    }

    public void setAllBorderColors(PDGamma pDGamma) {
        setColor(BORDER_COLOR, pDGamma);
    }

    public void setBorderColors(PDFourColours pDFourColours) {
        setFourColors(BORDER_COLOR, pDFourColours);
    }

    public Object getBorderStyle() {
        return getNameOrArrayOfName(BORDER_STYLE, "None");
    }

    public void setAllBorderStyles(String str) {
        setName(BORDER_STYLE, str);
    }

    public void setBorderStyles(String[] strArr) {
        setArrayOfName(BORDER_STYLE, strArr);
    }

    public Object getBorderThickness() {
        return getNumberOrArrayOfNumber(BORDER_THICKNESS, -1.0f);
    }

    public void setAllBorderThicknesses(float f) {
        setNumber(BORDER_THICKNESS, f);
    }

    public void setAllBorderThicknesses(int i) {
        setNumber(BORDER_THICKNESS, i);
    }

    public void setBorderThicknesses(float[] fArr) {
        setArrayOfNumber(BORDER_THICKNESS, fArr);
    }

    public Object getPadding() {
        return getNumberOrArrayOfNumber(PADDING, 0.0f);
    }

    public void setAllPaddings(float f) {
        setNumber(PADDING, f);
    }

    public void setAllPaddings(int i) {
        setNumber(PADDING, i);
    }

    public void setPaddings(float[] fArr) {
        setArrayOfNumber(PADDING, fArr);
    }

    public PDGamma getColor() {
        return getColor(COLOR);
    }

    public void setColor(PDGamma pDGamma) {
        setColor(COLOR, pDGamma);
    }

    public float getSpaceBefore() {
        return getNumber(SPACE_BEFORE, 0.0f);
    }

    public void setSpaceBefore(float f) {
        setNumber(SPACE_BEFORE, f);
    }

    public void setSpaceBefore(int i) {
        setNumber(SPACE_BEFORE, i);
    }

    public float getSpaceAfter() {
        return getNumber(SPACE_AFTER, 0.0f);
    }

    public void setSpaceAfter(float f) {
        setNumber(SPACE_AFTER, f);
    }

    public void setSpaceAfter(int i) {
        setNumber(SPACE_AFTER, i);
    }

    public float getStartIndent() {
        return getNumber(START_INDENT, 0.0f);
    }

    public void setStartIndent(float f) {
        setNumber(START_INDENT, f);
    }

    public void setStartIndent(int i) {
        setNumber(START_INDENT, i);
    }

    public float getEndIndent() {
        return getNumber(END_INDENT, 0.0f);
    }

    public void setEndIndent(float f) {
        setNumber(END_INDENT, f);
    }

    public void setEndIndent(int i) {
        setNumber(END_INDENT, i);
    }

    public float getTextIndent() {
        return getNumber(TEXT_INDENT, 0.0f);
    }

    public void setTextIndent(float f) {
        setNumber(TEXT_INDENT, f);
    }

    public void setTextIndent(int i) {
        setNumber(TEXT_INDENT, i);
    }

    public String getTextAlign() {
        return getName(TEXT_ALIGN, "Start");
    }

    public void setTextAlign(String str) {
        setName(TEXT_ALIGN, str);
    }

    public PDRectangle getBBox() {
        COSArray cOSArray = (COSArray) getCOSDictionary().getDictionaryObject(BBOX);
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
        }
        return null;
    }

    public void setBBox(PDRectangle pDRectangle) {
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(BBOX);
        getCOSDictionary().setItem(BBOX, pDRectangle);
        potentiallyNotifyChanged(dictionaryObject, pDRectangle == null ? null : pDRectangle.getCOSObject());
    }

    public Object getWidth() {
        return getNumberOrName("Width", "Auto");
    }

    public void setWidthAuto() {
        setName("Width", "Auto");
    }

    public void setWidth(float f) {
        setNumber("Width", f);
    }

    public void setWidth(int i) {
        setNumber("Width", i);
    }

    public Object getHeight() {
        return getNumberOrName(HEIGHT, "Auto");
    }

    public void setHeightAuto() {
        setName(HEIGHT, "Auto");
    }

    public void setHeight(float f) {
        setNumber(HEIGHT, f);
    }

    public void setHeight(int i) {
        setNumber(HEIGHT, i);
    }

    public String getBlockAlign() {
        return getName(BLOCK_ALIGN, "Before");
    }

    public void setBlockAlign(String str) {
        setName(BLOCK_ALIGN, str);
    }

    public String getInlineAlign() {
        return getName(INLINE_ALIGN, "Start");
    }

    public void setInlineAlign(String str) {
        setName(INLINE_ALIGN, str);
    }

    public Object getTBorderStyle() {
        return getNameOrArrayOfName(T_BORDER_STYLE, "None");
    }

    public void setAllTBorderStyles(String str) {
        setName(T_BORDER_STYLE, str);
    }

    public void setTBorderStyles(String[] strArr) {
        setArrayOfName(T_BORDER_STYLE, strArr);
    }

    public Object getTPadding() {
        return getNumberOrArrayOfNumber(T_PADDING, 0.0f);
    }

    public void setAllTPaddings(float f) {
        setNumber(T_PADDING, f);
    }

    public void setAllTPaddings(int i) {
        setNumber(T_PADDING, i);
    }

    public void setTPaddings(float[] fArr) {
        setArrayOfNumber(T_PADDING, fArr);
    }

    public float getBaselineShift() {
        return getNumber(BASELINE_SHIFT, 0.0f);
    }

    public void setBaselineShift(float f) {
        setNumber(BASELINE_SHIFT, f);
    }

    public void setBaselineShift(int i) {
        setNumber(BASELINE_SHIFT, i);
    }

    public Object getLineHeight() {
        return getNumberOrName(LINE_HEIGHT, LINE_HEIGHT_NORMAL);
    }

    public void setLineHeightNormal() {
        setName(LINE_HEIGHT, LINE_HEIGHT_NORMAL);
    }

    public void setLineHeightAuto() {
        setName(LINE_HEIGHT, "Auto");
    }

    public void setLineHeight(float f) {
        setNumber(LINE_HEIGHT, f);
    }

    public void setLineHeight(int i) {
        setNumber(LINE_HEIGHT, i);
    }

    public PDGamma getTextDecorationColor() {
        return getColor(TEXT_DECORATION_COLOR);
    }

    public void setTextDecorationColor(PDGamma pDGamma) {
        setColor(TEXT_DECORATION_COLOR, pDGamma);
    }

    public float getTextDecorationThickness() {
        return getNumber(TEXT_DECORATION_THICKNESS);
    }

    public void setTextDecorationThickness(float f) {
        setNumber(TEXT_DECORATION_THICKNESS, f);
    }

    public void setTextDecorationThickness(int i) {
        setNumber(TEXT_DECORATION_THICKNESS, i);
    }

    public String getTextDecorationType() {
        return getName(TEXT_DECORATION_TYPE, "None");
    }

    public void setTextDecorationType(String str) {
        setName(TEXT_DECORATION_TYPE, str);
    }

    public String getRubyAlign() {
        return getName(RUBY_ALIGN, RUBY_ALIGN_DISTRIBUTE);
    }

    public void setRubyAlign(String str) {
        setName(RUBY_ALIGN, str);
    }

    public String getRubyPosition() {
        return getName(RUBY_POSITION, "Before");
    }

    public void setRubyPosition(String str) {
        setName(RUBY_POSITION, str);
    }

    public String getGlyphOrientationVertical() {
        return getName(GLYPH_ORIENTATION_VERTICAL, "Auto");
    }

    public void setGlyphOrientationVertical(String str) {
        setName(GLYPH_ORIENTATION_VERTICAL, str);
    }

    public int getColumnCount() {
        return getInteger(COLUMN_COUNT, 1);
    }

    public void setColumnCount(int i) {
        setInteger(COLUMN_COUNT, i);
    }

    public Object getColumnGap() {
        return getNumberOrArrayOfNumber(COLUMN_GAP, -1.0f);
    }

    public void setColumnGap(float f) {
        setNumber(COLUMN_GAP, f);
    }

    public void setColumnGap(int i) {
        setNumber(COLUMN_GAP, i);
    }

    public void setColumnGaps(float[] fArr) {
        setArrayOfNumber(COLUMN_GAP, fArr);
    }

    public Object getColumnWidths() {
        return getNumberOrArrayOfNumber(COLUMN_WIDTHS, -1.0f);
    }

    public void setAllColumnWidths(float f) {
        setNumber(COLUMN_WIDTHS, f);
    }

    public void setAllColumnWidths(int i) {
        setNumber(COLUMN_WIDTHS, i);
    }

    public void setColumnWidths(float[] fArr) {
        setArrayOfNumber(COLUMN_WIDTHS, fArr);
    }

    @Override // org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDAttributeObject
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (isSpecified(PLACEMENT)) {
            sb.append(", Placement=");
            sb.append(getPlacement());
        }
        if (isSpecified(WRITING_MODE)) {
            sb.append(", WritingMode=");
            sb.append(getWritingMode());
        }
        if (isSpecified(BACKGROUND_COLOR)) {
            sb.append(", BackgroundColor=");
            sb.append(getBackgroundColor());
        }
        if (isSpecified(BORDER_COLOR)) {
            sb.append(", BorderColor=");
            sb.append(getBorderColors());
        }
        if (isSpecified(BORDER_STYLE)) {
            Object borderStyle = getBorderStyle();
            sb.append(", BorderStyle=");
            if (borderStyle instanceof String[]) {
                sb.append(arrayToString((String[]) borderStyle));
            } else {
                sb.append(borderStyle);
            }
        }
        if (isSpecified(BORDER_THICKNESS)) {
            Object borderThickness = getBorderThickness();
            sb.append(", BorderThickness=");
            if (borderThickness instanceof float[]) {
                sb.append(arrayToString((float[]) borderThickness));
            } else {
                sb.append(String.valueOf(borderThickness));
            }
        }
        if (isSpecified(PADDING)) {
            Object padding = getPadding();
            sb.append(", Padding=");
            if (padding instanceof float[]) {
                sb.append(arrayToString((float[]) padding));
            } else {
                sb.append(String.valueOf(padding));
            }
        }
        if (isSpecified(COLOR)) {
            sb.append(", Color=");
            sb.append(getColor());
        }
        if (isSpecified(SPACE_BEFORE)) {
            sb.append(", SpaceBefore=");
            sb.append(String.valueOf(getSpaceBefore()));
        }
        if (isSpecified(SPACE_AFTER)) {
            sb.append(", SpaceAfter=");
            sb.append(String.valueOf(getSpaceAfter()));
        }
        if (isSpecified(START_INDENT)) {
            sb.append(", StartIndent=");
            sb.append(String.valueOf(getStartIndent()));
        }
        if (isSpecified(END_INDENT)) {
            sb.append(", EndIndent=");
            sb.append(String.valueOf(getEndIndent()));
        }
        if (isSpecified(TEXT_INDENT)) {
            sb.append(", TextIndent=");
            sb.append(String.valueOf(getTextIndent()));
        }
        if (isSpecified(TEXT_ALIGN)) {
            sb.append(", TextAlign=");
            sb.append(getTextAlign());
        }
        if (isSpecified(BBOX)) {
            sb.append(", BBox=");
            sb.append(getBBox());
        }
        if (isSpecified("Width")) {
            Object width = getWidth();
            sb.append(", Width=");
            if (width instanceof Float) {
                sb.append(String.valueOf(width));
            } else {
                sb.append(width);
            }
        }
        if (isSpecified(HEIGHT)) {
            Object height = getHeight();
            sb.append(", Height=");
            if (height instanceof Float) {
                sb.append(String.valueOf(height));
            } else {
                sb.append(height);
            }
        }
        if (isSpecified(BLOCK_ALIGN)) {
            sb.append(", BlockAlign=");
            sb.append(getBlockAlign());
        }
        if (isSpecified(INLINE_ALIGN)) {
            sb.append(", InlineAlign=");
            sb.append(getInlineAlign());
        }
        if (isSpecified(T_BORDER_STYLE)) {
            Object tBorderStyle = getTBorderStyle();
            sb.append(", TBorderStyle=");
            if (tBorderStyle instanceof String[]) {
                sb.append(arrayToString((String[]) tBorderStyle));
            } else {
                sb.append(tBorderStyle);
            }
        }
        if (isSpecified(T_PADDING)) {
            Object tPadding = getTPadding();
            sb.append(", TPadding=");
            if (tPadding instanceof float[]) {
                sb.append(arrayToString((float[]) tPadding));
            } else {
                sb.append(String.valueOf(tPadding));
            }
        }
        if (isSpecified(BASELINE_SHIFT)) {
            sb.append(", BaselineShift=");
            sb.append(String.valueOf(getBaselineShift()));
        }
        if (isSpecified(LINE_HEIGHT)) {
            Object lineHeight = getLineHeight();
            sb.append(", LineHeight=");
            if (lineHeight instanceof Float) {
                sb.append(String.valueOf(lineHeight));
            } else {
                sb.append(lineHeight);
            }
        }
        if (isSpecified(TEXT_DECORATION_COLOR)) {
            sb.append(", TextDecorationColor=");
            sb.append(getTextDecorationColor());
        }
        if (isSpecified(TEXT_DECORATION_THICKNESS)) {
            sb.append(", TextDecorationThickness=");
            sb.append(String.valueOf(getTextDecorationThickness()));
        }
        if (isSpecified(TEXT_DECORATION_TYPE)) {
            sb.append(", TextDecorationType=");
            sb.append(getTextDecorationType());
        }
        if (isSpecified(RUBY_ALIGN)) {
            sb.append(", RubyAlign=");
            sb.append(getRubyAlign());
        }
        if (isSpecified(RUBY_POSITION)) {
            sb.append(", RubyPosition=");
            sb.append(getRubyPosition());
        }
        if (isSpecified(GLYPH_ORIENTATION_VERTICAL)) {
            sb.append(", GlyphOrientationVertical=");
            sb.append(getGlyphOrientationVertical());
        }
        if (isSpecified(COLUMN_COUNT)) {
            sb.append(", ColumnCount=");
            sb.append(String.valueOf(getColumnCount()));
        }
        if (isSpecified(COLUMN_GAP)) {
            Object columnGap = getColumnGap();
            sb.append(", ColumnGap=");
            if (columnGap instanceof float[]) {
                sb.append(arrayToString((float[]) columnGap));
            } else {
                sb.append(String.valueOf(columnGap));
            }
        }
        if (isSpecified(COLUMN_WIDTHS)) {
            Object columnWidths = getColumnWidths();
            sb.append(", ColumnWidths=");
            if (columnWidths instanceof float[]) {
                sb.append(arrayToString((float[]) columnWidths));
            } else {
                sb.append(String.valueOf(columnWidths));
            }
        }
        return sb.toString();
    }
}
