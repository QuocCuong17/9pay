package org.apache.pdfbox.pdmodel.graphics.state;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.PDLineDashPattern;
import org.apache.pdfbox.pdmodel.graphics.blend.BlendMode;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import org.apache.pdfbox.util.Matrix;

/* loaded from: classes5.dex */
public class PDGraphicsState implements Cloneable {
    private Region clippingPath;
    private boolean isClippingPathDirty;
    private Paint.Cap lineCap;
    private Paint.Join lineJoin;
    private RenderingIntent renderingIntent;
    private PDSoftMask softMask;
    private Matrix currentTransformationMatrix = new Matrix();
    private PDColor strokingColor = PDDeviceGray.INSTANCE.getInitialColor();
    private PDColor nonStrokingColor = PDDeviceGray.INSTANCE.getInitialColor();
    private PDColorSpace strokingColorSpace = PDDeviceGray.INSTANCE;
    private PDColorSpace nonStrokingColorSpace = PDDeviceGray.INSTANCE;
    private PDTextState textState = new PDTextState();
    private float lineWidth = 1.0f;
    private float miterLimit = 10.0f;
    private PDLineDashPattern lineDashPattern = new PDLineDashPattern();
    private boolean strokeAdjustment = false;
    private BlendMode blendMode = BlendMode.COMPATIBLE;
    private double alphaConstant = 1.0d;
    private double nonStrokingAlphaConstant = 1.0d;
    private boolean alphaSource = false;
    private boolean overprint = false;
    private double overprintMode = 0.0d;
    private double flatness = 1.0d;
    private double smoothness = 0.0d;

    public PDGraphicsState(PDRectangle pDRectangle) {
        RectF rectF = new RectF();
        pDRectangle.toGeneralPath().computeBounds(rectF, true);
        this.clippingPath = new Region();
        Rect rect = new Rect();
        rectF.round(rect);
        this.clippingPath.setPath(pDRectangle.toGeneralPath(), new Region(rect));
    }

    public Matrix getCurrentTransformationMatrix() {
        return this.currentTransformationMatrix;
    }

    public void setCurrentTransformationMatrix(Matrix matrix) {
        this.currentTransformationMatrix = matrix;
    }

    public float getLineWidth() {
        return this.lineWidth;
    }

    public void setLineWidth(float f) {
        this.lineWidth = f;
    }

    public Paint.Cap getLineCap() {
        return this.lineCap;
    }

    public void setLineCap(Paint.Cap cap) {
        this.lineCap = cap;
    }

    public Paint.Join getLineJoin() {
        return this.lineJoin;
    }

    public void setLineJoin(Paint.Join join) {
        this.lineJoin = join;
    }

    public float getMiterLimit() {
        return this.miterLimit;
    }

    public void setMiterLimit(float f) {
        this.miterLimit = f;
    }

    public boolean isStrokeAdjustment() {
        return this.strokeAdjustment;
    }

    public void setStrokeAdjustment(boolean z) {
        this.strokeAdjustment = z;
    }

    public double getAlphaConstant() {
        return this.alphaConstant;
    }

    public void setAlphaConstant(double d) {
        this.alphaConstant = d;
    }

    public double getNonStrokeAlphaConstant() {
        return this.nonStrokingAlphaConstant;
    }

    public void setNonStrokeAlphaConstant(double d) {
        this.nonStrokingAlphaConstant = d;
    }

    public boolean isAlphaSource() {
        return this.alphaSource;
    }

    public void setAlphaSource(boolean z) {
        this.alphaSource = z;
    }

    public PDSoftMask getSoftMask() {
        return this.softMask;
    }

    public void setSoftMask(PDSoftMask pDSoftMask) {
        this.softMask = pDSoftMask;
    }

    public BlendMode getBlendMode() {
        return this.blendMode;
    }

    public void setBlendMode(BlendMode blendMode) {
        this.blendMode = blendMode;
    }

    public boolean isOverprint() {
        return this.overprint;
    }

    public void setOverprint(boolean z) {
        this.overprint = z;
    }

    public double getOverprintMode() {
        return this.overprintMode;
    }

    public void setOverprintMode(double d) {
        this.overprintMode = d;
    }

    public double getFlatness() {
        return this.flatness;
    }

    public void setFlatness(double d) {
        this.flatness = d;
    }

    public double getSmoothness() {
        return this.smoothness;
    }

    public void setSmoothness(double d) {
        this.smoothness = d;
    }

    public PDTextState getTextState() {
        return this.textState;
    }

    public void setTextState(PDTextState pDTextState) {
        this.textState = pDTextState;
    }

    public PDLineDashPattern getLineDashPattern() {
        return this.lineDashPattern;
    }

    public void setLineDashPattern(PDLineDashPattern pDLineDashPattern) {
        this.lineDashPattern = pDLineDashPattern;
    }

    public RenderingIntent getRenderingIntent() {
        return this.renderingIntent;
    }

    public void setRenderingIntent(RenderingIntent renderingIntent) {
        this.renderingIntent = renderingIntent;
    }

    public PDGraphicsState clone() {
        try {
            PDGraphicsState pDGraphicsState = (PDGraphicsState) super.clone();
            pDGraphicsState.textState = this.textState.clone();
            pDGraphicsState.currentTransformationMatrix = this.currentTransformationMatrix.clone();
            pDGraphicsState.strokingColor = this.strokingColor;
            pDGraphicsState.nonStrokingColor = this.nonStrokingColor;
            pDGraphicsState.lineDashPattern = this.lineDashPattern;
            pDGraphicsState.clippingPath = this.clippingPath;
            pDGraphicsState.isClippingPathDirty = false;
            return pDGraphicsState;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public PDColor getStrokingColor() {
        return this.strokingColor;
    }

    public void setStrokingColor(PDColor pDColor) {
        this.strokingColor = pDColor;
    }

    public PDColor getNonStrokingColor() {
        return this.nonStrokingColor;
    }

    public void setNonStrokingColor(PDColor pDColor) {
        this.nonStrokingColor = pDColor;
    }

    public PDColorSpace getStrokingColorSpace() {
        return this.strokingColorSpace;
    }

    public void setStrokingColorSpace(PDColorSpace pDColorSpace) {
        this.strokingColorSpace = pDColorSpace;
    }

    public PDColorSpace getNonStrokingColorSpace() {
        return this.nonStrokingColorSpace;
    }

    public void setNonStrokingColorSpace(PDColorSpace pDColorSpace) {
        this.nonStrokingColorSpace = pDColorSpace;
    }

    public void intersectClippingPath(Path path) {
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        Region region = new Region();
        Rect rect = new Rect();
        rectF.round(rect);
        region.setPath(path, new Region(rect));
        intersectClippingPath(region);
    }

    public void intersectClippingPath(Region region) {
        if (!this.isClippingPathDirty) {
            this.clippingPath = new Region(region);
            this.isClippingPathDirty = true;
        }
        this.clippingPath.op(region, Region.Op.INTERSECT);
    }

    public Region getCurrentClippingPath() {
        return this.clippingPath;
    }
}
