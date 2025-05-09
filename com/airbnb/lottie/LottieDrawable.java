package com.airbnb.lottie;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.manager.FontAssetManager;
import com.airbnb.lottie.manager.ImageAssetManager;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.Marker;
import com.airbnb.lottie.model.layer.CompositionLayer;
import com.airbnb.lottie.parser.LayerParser;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.LottieValueAnimator;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class LottieDrawable extends Drawable implements Drawable.Callback, Animatable {
    public static final int INFINITE = -1;
    public static final int RESTART = 1;
    public static final int REVERSE = 2;
    private int alpha;
    private final LottieValueAnimator animator;
    private Rect canvasClipBounds;
    private RectF canvasClipBoundsRectF;
    private boolean clipToCompositionBounds;
    private LottieComposition composition;
    private CompositionLayer compositionLayer;
    private boolean enableMergePaths;
    FontAssetDelegate fontAssetDelegate;
    private FontAssetManager fontAssetManager;
    private boolean ignoreSystemAnimationsDisabled;
    private ImageAssetDelegate imageAssetDelegate;
    private ImageAssetManager imageAssetManager;
    private String imageAssetsFolder;
    private boolean isApplyingOpacityToLayersEnabled;
    private boolean isDirty;
    private final ArrayList<LazyCompositionTask> lazyCompositionTasks;
    private boolean maintainOriginalImageBounds;
    private OnVisibleAction onVisibleAction;
    private boolean outlineMasksAndMattes;
    private boolean performanceTrackingEnabled;
    private final ValueAnimator.AnimatorUpdateListener progressUpdateListener;
    private RenderMode renderMode;
    private final Matrix renderingMatrix;
    private boolean safeMode;
    private Bitmap softwareRenderingBitmap;
    private Canvas softwareRenderingCanvas;
    private Rect softwareRenderingDstBoundsRect;
    private RectF softwareRenderingDstBoundsRectF;
    private Matrix softwareRenderingOriginalCanvasMatrix;
    private Matrix softwareRenderingOriginalCanvasMatrixInverse;
    private Paint softwareRenderingPaint;
    private Rect softwareRenderingSrcBoundsRect;
    private RectF softwareRenderingTransformedBounds;
    private boolean systemAnimationsEnabled;
    TextDelegate textDelegate;
    private boolean useSoftwareRendering;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface LazyCompositionTask {
        void run(LottieComposition lottieComposition);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum OnVisibleAction {
        NONE,
        PLAY,
        RESUME
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface RepeatMode {
    }

    @Deprecated
    public void disableExtraScaleModeInFitXY() {
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public LottieDrawable() {
        LottieValueAnimator lottieValueAnimator = new LottieValueAnimator();
        this.animator = lottieValueAnimator;
        this.systemAnimationsEnabled = true;
        this.ignoreSystemAnimationsDisabled = false;
        this.safeMode = false;
        this.onVisibleAction = OnVisibleAction.NONE;
        this.lazyCompositionTasks = new ArrayList<>();
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.airbnb.lottie.LottieDrawable.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (LottieDrawable.this.compositionLayer != null) {
                    LottieDrawable.this.compositionLayer.setProgress(LottieDrawable.this.animator.getAnimatedValueAbsolute());
                }
            }
        };
        this.progressUpdateListener = animatorUpdateListener;
        this.maintainOriginalImageBounds = false;
        this.clipToCompositionBounds = true;
        this.alpha = 255;
        this.renderMode = RenderMode.AUTOMATIC;
        this.useSoftwareRendering = false;
        this.renderingMatrix = new Matrix();
        this.isDirty = false;
        lottieValueAnimator.addUpdateListener(animatorUpdateListener);
    }

    public boolean hasMasks() {
        CompositionLayer compositionLayer = this.compositionLayer;
        return compositionLayer != null && compositionLayer.hasMasks();
    }

    public boolean hasMatte() {
        CompositionLayer compositionLayer = this.compositionLayer;
        return compositionLayer != null && compositionLayer.hasMatte();
    }

    public boolean enableMergePathsForKitKatAndAbove() {
        return this.enableMergePaths;
    }

    public void enableMergePathsForKitKatAndAbove(boolean z) {
        if (this.enableMergePaths == z) {
            return;
        }
        if (Build.VERSION.SDK_INT < 19) {
            Logger.warning("Merge paths are not supported pre-Kit Kat.");
            return;
        }
        this.enableMergePaths = z;
        if (this.composition != null) {
            buildCompositionLayer();
        }
    }

    public boolean isMergePathsEnabledForKitKatAndAbove() {
        return this.enableMergePaths;
    }

    public void setClipToCompositionBounds(boolean z) {
        if (z != this.clipToCompositionBounds) {
            this.clipToCompositionBounds = z;
            CompositionLayer compositionLayer = this.compositionLayer;
            if (compositionLayer != null) {
                compositionLayer.setClipToCompositionBounds(z);
            }
            invalidateSelf();
        }
    }

    public boolean getClipToCompositionBounds() {
        return this.clipToCompositionBounds;
    }

    public void setImagesAssetsFolder(String str) {
        this.imageAssetsFolder = str;
    }

    public String getImageAssetsFolder() {
        return this.imageAssetsFolder;
    }

    public void setMaintainOriginalImageBounds(boolean z) {
        this.maintainOriginalImageBounds = z;
    }

    public boolean getMaintainOriginalImageBounds() {
        return this.maintainOriginalImageBounds;
    }

    public boolean setComposition(LottieComposition lottieComposition) {
        if (this.composition == lottieComposition) {
            return false;
        }
        this.isDirty = true;
        clearComposition();
        this.composition = lottieComposition;
        buildCompositionLayer();
        this.animator.setComposition(lottieComposition);
        setProgress(this.animator.getAnimatedFraction());
        Iterator it = new ArrayList(this.lazyCompositionTasks).iterator();
        while (it.hasNext()) {
            LazyCompositionTask lazyCompositionTask = (LazyCompositionTask) it.next();
            if (lazyCompositionTask != null) {
                lazyCompositionTask.run(lottieComposition);
            }
            it.remove();
        }
        this.lazyCompositionTasks.clear();
        lottieComposition.setPerformanceTrackingEnabled(this.performanceTrackingEnabled);
        computeRenderMode();
        Drawable.Callback callback = getCallback();
        if (callback instanceof ImageView) {
            ImageView imageView = (ImageView) callback;
            imageView.setImageDrawable(null);
            imageView.setImageDrawable(this);
        }
        return true;
    }

    public void setRenderMode(RenderMode renderMode) {
        this.renderMode = renderMode;
        computeRenderMode();
    }

    public RenderMode getRenderMode() {
        return this.useSoftwareRendering ? RenderMode.SOFTWARE : RenderMode.HARDWARE;
    }

    private void computeRenderMode() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return;
        }
        this.useSoftwareRendering = this.renderMode.useSoftwareRendering(Build.VERSION.SDK_INT, lottieComposition.hasDashPattern(), lottieComposition.getMaskAndMatteCount());
    }

    public void setPerformanceTrackingEnabled(boolean z) {
        this.performanceTrackingEnabled = z;
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition != null) {
            lottieComposition.setPerformanceTrackingEnabled(z);
        }
    }

    public void setOutlineMasksAndMattes(boolean z) {
        if (this.outlineMasksAndMattes == z) {
            return;
        }
        this.outlineMasksAndMattes = z;
        CompositionLayer compositionLayer = this.compositionLayer;
        if (compositionLayer != null) {
            compositionLayer.setOutlineMasksAndMattes(z);
        }
    }

    public PerformanceTracker getPerformanceTracker() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition != null) {
            return lottieComposition.getPerformanceTracker();
        }
        return null;
    }

    public void setApplyingOpacityToLayersEnabled(boolean z) {
        this.isApplyingOpacityToLayersEnabled = z;
    }

    public boolean isApplyingOpacityToLayersEnabled() {
        return this.isApplyingOpacityToLayersEnabled;
    }

    private void buildCompositionLayer() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return;
        }
        CompositionLayer compositionLayer = new CompositionLayer(this, LayerParser.parse(lottieComposition), lottieComposition.getLayers(), lottieComposition);
        this.compositionLayer = compositionLayer;
        if (this.outlineMasksAndMattes) {
            compositionLayer.setOutlineMasksAndMattes(true);
        }
        this.compositionLayer.setClipToCompositionBounds(this.clipToCompositionBounds);
    }

    public void clearComposition() {
        if (this.animator.isRunning()) {
            this.animator.cancel();
            if (!isVisible()) {
                this.onVisibleAction = OnVisibleAction.NONE;
            }
        }
        this.composition = null;
        this.compositionLayer = null;
        this.imageAssetManager = null;
        this.animator.clearComposition();
        invalidateSelf();
    }

    public void setSafeMode(boolean z) {
        this.safeMode = z;
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        if (this.isDirty) {
            return;
        }
        this.isDirty = true;
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.alpha = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.alpha;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Logger.warning("Use addColorFilter instead.");
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        L.beginSection("Drawable#draw");
        if (this.safeMode) {
            try {
                if (this.useSoftwareRendering) {
                    renderAndDrawAsBitmap(canvas, this.compositionLayer);
                } else {
                    drawDirectlyToCanvas(canvas);
                }
            } catch (Throwable th) {
                Logger.error("Lottie crashed in draw!", th);
            }
        } else if (this.useSoftwareRendering) {
            renderAndDrawAsBitmap(canvas, this.compositionLayer);
        } else {
            drawDirectlyToCanvas(canvas);
        }
        this.isDirty = false;
        L.endSection("Drawable#draw");
    }

    public void draw(Canvas canvas, Matrix matrix) {
        CompositionLayer compositionLayer = this.compositionLayer;
        LottieComposition lottieComposition = this.composition;
        if (compositionLayer == null || lottieComposition == null) {
            return;
        }
        if (this.useSoftwareRendering) {
            canvas.save();
            canvas.concat(matrix);
            renderAndDrawAsBitmap(canvas, compositionLayer);
            canvas.restore();
        } else {
            compositionLayer.draw(canvas, matrix, this.alpha);
        }
        this.isDirty = false;
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable.Callback callback = getCallback();
        if ((callback instanceof View) && ((View) callback).isInEditMode()) {
            return;
        }
        playAnimation();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        endAnimation();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return isAnimating();
    }

    public void playAnimation() {
        if (this.compositionLayer == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda0
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m553lambda$playAnimation$0$comairbnblottieLottieDrawable(lottieComposition);
                }
            });
            return;
        }
        computeRenderMode();
        if (animationsEnabled() || getRepeatCount() == 0) {
            if (isVisible()) {
                this.animator.playAnimation();
                this.onVisibleAction = OnVisibleAction.NONE;
            } else {
                this.onVisibleAction = OnVisibleAction.PLAY;
            }
        }
        if (animationsEnabled()) {
            return;
        }
        setFrame((int) (getSpeed() < 0.0f ? getMinFrame() : getMaxFrame()));
        this.animator.endAnimation();
        if (isVisible()) {
            return;
        }
        this.onVisibleAction = OnVisibleAction.NONE;
    }

    /* renamed from: lambda$playAnimation$0$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    public /* synthetic */ void m553lambda$playAnimation$0$comairbnblottieLottieDrawable(LottieComposition lottieComposition) {
        playAnimation();
    }

    public void endAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.endAnimation();
        if (isVisible()) {
            return;
        }
        this.onVisibleAction = OnVisibleAction.NONE;
    }

    public void resumeAnimation() {
        if (this.compositionLayer == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda6
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m554lambda$resumeAnimation$1$comairbnblottieLottieDrawable(lottieComposition);
                }
            });
            return;
        }
        computeRenderMode();
        if (animationsEnabled() || getRepeatCount() == 0) {
            if (isVisible()) {
                this.animator.resumeAnimation();
                this.onVisibleAction = OnVisibleAction.NONE;
            } else {
                this.onVisibleAction = OnVisibleAction.RESUME;
            }
        }
        if (animationsEnabled()) {
            return;
        }
        setFrame((int) (getSpeed() < 0.0f ? getMinFrame() : getMaxFrame()));
        this.animator.endAnimation();
        if (isVisible()) {
            return;
        }
        this.onVisibleAction = OnVisibleAction.NONE;
    }

    /* renamed from: lambda$resumeAnimation$1$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    public /* synthetic */ void m554lambda$resumeAnimation$1$comairbnblottieLottieDrawable(LottieComposition lottieComposition) {
        resumeAnimation();
    }

    public void setMinFrame(final int i) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda13
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m563lambda$setMinFrame$2$comairbnblottieLottieDrawable(i, lottieComposition);
                }
            });
        } else {
            this.animator.setMinFrame(i);
        }
    }

    /* renamed from: lambda$setMinFrame$2$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    public /* synthetic */ void m563lambda$setMinFrame$2$comairbnblottieLottieDrawable(int i, LottieComposition lottieComposition) {
        setMinFrame(i);
    }

    public float getMinFrame() {
        return this.animator.getMinFrame();
    }

    public void setMinProgress(final float f) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda8
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition2) {
                    LottieDrawable.this.m565lambda$setMinProgress$3$comairbnblottieLottieDrawable(f, lottieComposition2);
                }
            });
        } else {
            setMinFrame((int) MiscUtils.lerp(lottieComposition.getStartFrame(), this.composition.getEndFrame(), f));
        }
    }

    /* renamed from: lambda$setMinProgress$3$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    public /* synthetic */ void m565lambda$setMinProgress$3$comairbnblottieLottieDrawable(float f, LottieComposition lottieComposition) {
        setMinProgress(f);
    }

    public void setMaxFrame(final int i) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda12
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m556lambda$setMaxFrame$4$comairbnblottieLottieDrawable(i, lottieComposition);
                }
            });
        } else {
            this.animator.setMaxFrame(i + 0.99f);
        }
    }

    /* renamed from: lambda$setMaxFrame$4$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    public /* synthetic */ void m556lambda$setMaxFrame$4$comairbnblottieLottieDrawable(int i, LottieComposition lottieComposition) {
        setMaxFrame(i);
    }

    public float getMaxFrame() {
        return this.animator.getMaxFrame();
    }

    public void setMaxProgress(final float f) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda7
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition2) {
                    LottieDrawable.this.m558lambda$setMaxProgress$5$comairbnblottieLottieDrawable(f, lottieComposition2);
                }
            });
        } else {
            this.animator.setMaxFrame(MiscUtils.lerp(lottieComposition.getStartFrame(), this.composition.getEndFrame(), f));
        }
    }

    /* renamed from: lambda$setMaxProgress$5$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    public /* synthetic */ void m558lambda$setMaxProgress$5$comairbnblottieLottieDrawable(float f, LottieComposition lottieComposition) {
        setMaxProgress(f);
    }

    public void setMinFrame(final String str) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda4
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition2) {
                    LottieDrawable.this.m564lambda$setMinFrame$6$comairbnblottieLottieDrawable(str, lottieComposition2);
                }
            });
            return;
        }
        Marker marker = lottieComposition.getMarker(str);
        if (marker == null) {
            throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
        }
        setMinFrame((int) marker.startFrame);
    }

    /* renamed from: lambda$setMinFrame$6$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    public /* synthetic */ void m564lambda$setMinFrame$6$comairbnblottieLottieDrawable(String str, LottieComposition lottieComposition) {
        setMinFrame(str);
    }

    public void setMaxFrame(final String str) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda2
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition2) {
                    LottieDrawable.this.m557lambda$setMaxFrame$7$comairbnblottieLottieDrawable(str, lottieComposition2);
                }
            });
            return;
        }
        Marker marker = lottieComposition.getMarker(str);
        if (marker == null) {
            throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
        }
        setMaxFrame((int) (marker.startFrame + marker.durationFrames));
    }

    /* renamed from: lambda$setMaxFrame$7$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    public /* synthetic */ void m557lambda$setMaxFrame$7$comairbnblottieLottieDrawable(String str, LottieComposition lottieComposition) {
        setMaxFrame(str);
    }

    public void setMinAndMaxFrame(final String str) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda3
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition2) {
                    LottieDrawable.this.m560lambda$setMinAndMaxFrame$8$comairbnblottieLottieDrawable(str, lottieComposition2);
                }
            });
            return;
        }
        Marker marker = lottieComposition.getMarker(str);
        if (marker == null) {
            throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
        }
        int i = (int) marker.startFrame;
        setMinAndMaxFrame(i, ((int) marker.durationFrames) + i);
    }

    /* renamed from: lambda$setMinAndMaxFrame$8$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    public /* synthetic */ void m560lambda$setMinAndMaxFrame$8$comairbnblottieLottieDrawable(String str, LottieComposition lottieComposition) {
        setMinAndMaxFrame(str);
    }

    public void setMinAndMaxFrame(final String str, final String str2, final boolean z) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda5
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition2) {
                    LottieDrawable.this.m561lambda$setMinAndMaxFrame$9$comairbnblottieLottieDrawable(str, str2, z, lottieComposition2);
                }
            });
            return;
        }
        Marker marker = lottieComposition.getMarker(str);
        if (marker == null) {
            throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
        }
        int i = (int) marker.startFrame;
        Marker marker2 = this.composition.getMarker(str2);
        if (marker2 == null) {
            throw new IllegalArgumentException("Cannot find marker with name " + str2 + ".");
        }
        setMinAndMaxFrame(i, (int) (marker2.startFrame + (z ? 1.0f : 0.0f)));
    }

    /* renamed from: lambda$setMinAndMaxFrame$9$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    public /* synthetic */ void m561lambda$setMinAndMaxFrame$9$comairbnblottieLottieDrawable(String str, String str2, boolean z, LottieComposition lottieComposition) {
        setMinAndMaxFrame(str, str2, z);
    }

    public void setMinAndMaxFrame(final int i, final int i2) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda14
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m559lambda$setMinAndMaxFrame$10$comairbnblottieLottieDrawable(i, i2, lottieComposition);
                }
            });
        } else {
            this.animator.setMinAndMaxFrames(i, i2 + 0.99f);
        }
    }

    /* renamed from: lambda$setMinAndMaxFrame$10$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    public /* synthetic */ void m559lambda$setMinAndMaxFrame$10$comairbnblottieLottieDrawable(int i, int i2, LottieComposition lottieComposition) {
        setMinAndMaxFrame(i, i2);
    }

    public void setMinAndMaxProgress(final float f, final float f2) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda10
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition2) {
                    LottieDrawable.this.m562lambda$setMinAndMaxProgress$11$comairbnblottieLottieDrawable(f, f2, lottieComposition2);
                }
            });
        } else {
            setMinAndMaxFrame((int) MiscUtils.lerp(lottieComposition.getStartFrame(), this.composition.getEndFrame(), f), (int) MiscUtils.lerp(this.composition.getStartFrame(), this.composition.getEndFrame(), f2));
        }
    }

    /* renamed from: lambda$setMinAndMaxProgress$11$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    public /* synthetic */ void m562lambda$setMinAndMaxProgress$11$comairbnblottieLottieDrawable(float f, float f2, LottieComposition lottieComposition) {
        setMinAndMaxProgress(f, f2);
    }

    public void reverseAnimationSpeed() {
        this.animator.reverseAnimationSpeed();
    }

    public void setSpeed(float f) {
        this.animator.setSpeed(f);
    }

    public float getSpeed() {
        return this.animator.getSpeed();
    }

    public void addAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.animator.addUpdateListener(animatorUpdateListener);
    }

    public void removeAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.animator.removeUpdateListener(animatorUpdateListener);
    }

    public void removeAllUpdateListeners() {
        this.animator.removeAllUpdateListeners();
        this.animator.addUpdateListener(this.progressUpdateListener);
    }

    public void addAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.animator.addListener(animatorListener);
    }

    public void removeAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.animator.removeListener(animatorListener);
    }

    public void removeAllAnimatorListeners() {
        this.animator.removeAllListeners();
    }

    public void addAnimatorPauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.animator.addPauseListener(animatorPauseListener);
    }

    public void removeAnimatorPauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.animator.removePauseListener(animatorPauseListener);
    }

    public void setFrame(final int i) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda11
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m555lambda$setFrame$12$comairbnblottieLottieDrawable(i, lottieComposition);
                }
            });
        } else {
            this.animator.setFrame(i);
        }
    }

    /* renamed from: lambda$setFrame$12$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    public /* synthetic */ void m555lambda$setFrame$12$comairbnblottieLottieDrawable(int i, LottieComposition lottieComposition) {
        setFrame(i);
    }

    public int getFrame() {
        return (int) this.animator.getFrame();
    }

    public void setProgress(final float f) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda9
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m566lambda$setProgress$13$comairbnblottieLottieDrawable(f, lottieComposition);
                }
            });
            return;
        }
        L.beginSection("Drawable#setProgress");
        this.animator.setFrame(this.composition.getFrameForProgress(f));
        L.endSection("Drawable#setProgress");
    }

    /* renamed from: lambda$setProgress$13$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    public /* synthetic */ void m566lambda$setProgress$13$comairbnblottieLottieDrawable(float f, LottieComposition lottieComposition) {
        setProgress(f);
    }

    @Deprecated
    public void loop(boolean z) {
        this.animator.setRepeatCount(z ? -1 : 0);
    }

    public void setRepeatMode(int i) {
        this.animator.setRepeatMode(i);
    }

    public int getRepeatMode() {
        return this.animator.getRepeatMode();
    }

    public void setRepeatCount(int i) {
        this.animator.setRepeatCount(i);
    }

    public int getRepeatCount() {
        return this.animator.getRepeatCount();
    }

    public boolean isLooping() {
        return this.animator.getRepeatCount() == -1;
    }

    public boolean isAnimating() {
        LottieValueAnimator lottieValueAnimator = this.animator;
        if (lottieValueAnimator == null) {
            return false;
        }
        return lottieValueAnimator.isRunning();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isAnimatingOrWillAnimateOnVisible() {
        if (isVisible()) {
            return this.animator.isRunning();
        }
        return this.onVisibleAction == OnVisibleAction.PLAY || this.onVisibleAction == OnVisibleAction.RESUME;
    }

    private boolean animationsEnabled() {
        return this.systemAnimationsEnabled || this.ignoreSystemAnimationsDisabled;
    }

    public void setSystemAnimationsAreEnabled(Boolean bool) {
        this.systemAnimationsEnabled = bool.booleanValue();
    }

    public void setIgnoreDisabledSystemAnimations(boolean z) {
        this.ignoreSystemAnimationsDisabled = z;
    }

    public void setImageAssetDelegate(ImageAssetDelegate imageAssetDelegate) {
        this.imageAssetDelegate = imageAssetDelegate;
        ImageAssetManager imageAssetManager = this.imageAssetManager;
        if (imageAssetManager != null) {
            imageAssetManager.setDelegate(imageAssetDelegate);
        }
    }

    public void setFontAssetDelegate(FontAssetDelegate fontAssetDelegate) {
        this.fontAssetDelegate = fontAssetDelegate;
        FontAssetManager fontAssetManager = this.fontAssetManager;
        if (fontAssetManager != null) {
            fontAssetManager.setDelegate(fontAssetDelegate);
        }
    }

    public void setTextDelegate(TextDelegate textDelegate) {
        this.textDelegate = textDelegate;
    }

    public TextDelegate getTextDelegate() {
        return this.textDelegate;
    }

    public boolean useTextGlyphs() {
        return this.textDelegate == null && this.composition.getCharacters().size() > 0;
    }

    public LottieComposition getComposition() {
        return this.composition;
    }

    public void cancelAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.cancel();
        if (isVisible()) {
            return;
        }
        this.onVisibleAction = OnVisibleAction.NONE;
    }

    public void pauseAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.pauseAnimation();
        if (isVisible()) {
            return;
        }
        this.onVisibleAction = OnVisibleAction.NONE;
    }

    public float getProgress() {
        return this.animator.getAnimatedValueAbsolute();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return -1;
        }
        return lottieComposition.getBounds().width();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return -1;
        }
        return lottieComposition.getBounds().height();
    }

    public List<KeyPath> resolveKeyPath(KeyPath keyPath) {
        if (this.compositionLayer == null) {
            Logger.warning("Cannot resolve KeyPath. Composition is not set yet.");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        this.compositionLayer.resolveKeyPath(keyPath, 0, arrayList, new KeyPath(new String[0]));
        return arrayList;
    }

    public <T> void addValueCallback(final KeyPath keyPath, final T t, final LottieValueCallback<T> lottieValueCallback) {
        if (this.compositionLayer == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda1
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m552lambda$addValueCallback$14$comairbnblottieLottieDrawable(keyPath, t, lottieValueCallback, lottieComposition);
                }
            });
            return;
        }
        boolean z = true;
        if (keyPath == KeyPath.COMPOSITION) {
            this.compositionLayer.addValueCallback(t, lottieValueCallback);
        } else if (keyPath.getResolvedElement() != null) {
            keyPath.getResolvedElement().addValueCallback(t, lottieValueCallback);
        } else {
            List<KeyPath> resolveKeyPath = resolveKeyPath(keyPath);
            for (int i = 0; i < resolveKeyPath.size(); i++) {
                resolveKeyPath.get(i).getResolvedElement().addValueCallback(t, lottieValueCallback);
            }
            z = true ^ resolveKeyPath.isEmpty();
        }
        if (z) {
            invalidateSelf();
            if (t == LottieProperty.TIME_REMAP) {
                setProgress(getProgress());
            }
        }
    }

    /* renamed from: lambda$addValueCallback$14$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    public /* synthetic */ void m552lambda$addValueCallback$14$comairbnblottieLottieDrawable(KeyPath keyPath, Object obj, LottieValueCallback lottieValueCallback, LottieComposition lottieComposition) {
        addValueCallback(keyPath, (KeyPath) obj, (LottieValueCallback<KeyPath>) lottieValueCallback);
    }

    public <T> void addValueCallback(KeyPath keyPath, T t, final SimpleLottieValueCallback<T> simpleLottieValueCallback) {
        addValueCallback(keyPath, (KeyPath) t, (LottieValueCallback<KeyPath>) new LottieValueCallback<T>() { // from class: com.airbnb.lottie.LottieDrawable.2
            @Override // com.airbnb.lottie.value.LottieValueCallback
            public T getValue(LottieFrameInfo<T> lottieFrameInfo) {
                return (T) simpleLottieValueCallback.getValue(lottieFrameInfo);
            }
        });
    }

    public Bitmap updateBitmap(String str, Bitmap bitmap) {
        ImageAssetManager imageAssetManager = getImageAssetManager();
        if (imageAssetManager == null) {
            Logger.warning("Cannot update bitmap. Most likely the drawable is not added to a View which prevents Lottie from getting a Context.");
            return null;
        }
        Bitmap updateBitmap = imageAssetManager.updateBitmap(str, bitmap);
        invalidateSelf();
        return updateBitmap;
    }

    @Deprecated
    public Bitmap getImageAsset(String str) {
        ImageAssetManager imageAssetManager = getImageAssetManager();
        if (imageAssetManager != null) {
            return imageAssetManager.bitmapForId(str);
        }
        LottieComposition lottieComposition = this.composition;
        LottieImageAsset lottieImageAsset = lottieComposition == null ? null : lottieComposition.getImages().get(str);
        if (lottieImageAsset != null) {
            return lottieImageAsset.getBitmap();
        }
        return null;
    }

    public Bitmap getBitmapForId(String str) {
        ImageAssetManager imageAssetManager = getImageAssetManager();
        if (imageAssetManager != null) {
            return imageAssetManager.bitmapForId(str);
        }
        return null;
    }

    public LottieImageAsset getLottieImageAssetForId(String str) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return null;
        }
        return lottieComposition.getImages().get(str);
    }

    private ImageAssetManager getImageAssetManager() {
        if (getCallback() == null) {
            return null;
        }
        ImageAssetManager imageAssetManager = this.imageAssetManager;
        if (imageAssetManager != null && !imageAssetManager.hasSameContext(getContext())) {
            this.imageAssetManager = null;
        }
        if (this.imageAssetManager == null) {
            this.imageAssetManager = new ImageAssetManager(getCallback(), this.imageAssetsFolder, this.imageAssetDelegate, this.composition.getImages());
        }
        return this.imageAssetManager;
    }

    public Typeface getTypeface(String str, String str2) {
        FontAssetManager fontAssetManager = getFontAssetManager();
        if (fontAssetManager != null) {
            return fontAssetManager.getTypeface(str, str2);
        }
        return null;
    }

    private FontAssetManager getFontAssetManager() {
        if (getCallback() == null) {
            return null;
        }
        if (this.fontAssetManager == null) {
            this.fontAssetManager = new FontAssetManager(getCallback(), this.fontAssetDelegate);
        }
        return this.fontAssetManager;
    }

    private Context getContext() {
        Drawable.Callback callback = getCallback();
        if (callback != null && (callback instanceof View)) {
            return ((View) callback).getContext();
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean z3 = !isVisible();
        boolean visible = super.setVisible(z, z2);
        if (z) {
            if (this.onVisibleAction == OnVisibleAction.PLAY) {
                playAnimation();
            } else if (this.onVisibleAction == OnVisibleAction.RESUME) {
                resumeAnimation();
            }
        } else if (this.animator.isRunning()) {
            pauseAnimation();
            this.onVisibleAction = OnVisibleAction.RESUME;
        } else if (!z3) {
            this.onVisibleAction = OnVisibleAction.NONE;
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.scheduleDrawable(this, runnable, j);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.unscheduleDrawable(this, runnable);
    }

    private void drawDirectlyToCanvas(Canvas canvas) {
        CompositionLayer compositionLayer = this.compositionLayer;
        LottieComposition lottieComposition = this.composition;
        if (compositionLayer == null || lottieComposition == null) {
            return;
        }
        this.renderingMatrix.reset();
        if (!getBounds().isEmpty()) {
            this.renderingMatrix.preScale(r2.width() / lottieComposition.getBounds().width(), r2.height() / lottieComposition.getBounds().height());
        }
        compositionLayer.draw(canvas, this.renderingMatrix, this.alpha);
    }

    private void renderAndDrawAsBitmap(Canvas canvas, CompositionLayer compositionLayer) {
        if (this.composition == null || compositionLayer == null) {
            return;
        }
        ensureSoftwareRenderingObjectsInitialized();
        canvas.getMatrix(this.softwareRenderingOriginalCanvasMatrix);
        canvas.getClipBounds(this.canvasClipBounds);
        convertRect(this.canvasClipBounds, this.canvasClipBoundsRectF);
        this.softwareRenderingOriginalCanvasMatrix.mapRect(this.canvasClipBoundsRectF);
        convertRect(this.canvasClipBoundsRectF, this.canvasClipBounds);
        if (this.clipToCompositionBounds) {
            this.softwareRenderingTransformedBounds.set(0.0f, 0.0f, getIntrinsicWidth(), getIntrinsicHeight());
        } else {
            compositionLayer.getBounds(this.softwareRenderingTransformedBounds, null, false);
        }
        this.softwareRenderingOriginalCanvasMatrix.mapRect(this.softwareRenderingTransformedBounds);
        Rect bounds = getBounds();
        float width = bounds.width() / getIntrinsicWidth();
        float height = bounds.height() / getIntrinsicHeight();
        scaleRect(this.softwareRenderingTransformedBounds, width, height);
        if (!ignoreCanvasClipBounds()) {
            this.softwareRenderingTransformedBounds.intersect(this.canvasClipBounds.left, this.canvasClipBounds.top, this.canvasClipBounds.right, this.canvasClipBounds.bottom);
        }
        int ceil = (int) Math.ceil(this.softwareRenderingTransformedBounds.width());
        int ceil2 = (int) Math.ceil(this.softwareRenderingTransformedBounds.height());
        if (ceil == 0 || ceil2 == 0) {
            return;
        }
        ensureSoftwareRenderingBitmap(ceil, ceil2);
        if (this.isDirty) {
            this.renderingMatrix.set(this.softwareRenderingOriginalCanvasMatrix);
            this.renderingMatrix.preScale(width, height);
            this.renderingMatrix.postTranslate(-this.softwareRenderingTransformedBounds.left, -this.softwareRenderingTransformedBounds.top);
            this.softwareRenderingBitmap.eraseColor(0);
            compositionLayer.draw(this.softwareRenderingCanvas, this.renderingMatrix, this.alpha);
            this.softwareRenderingOriginalCanvasMatrix.invert(this.softwareRenderingOriginalCanvasMatrixInverse);
            this.softwareRenderingOriginalCanvasMatrixInverse.mapRect(this.softwareRenderingDstBoundsRectF, this.softwareRenderingTransformedBounds);
            convertRect(this.softwareRenderingDstBoundsRectF, this.softwareRenderingDstBoundsRect);
        }
        this.softwareRenderingSrcBoundsRect.set(0, 0, ceil, ceil2);
        canvas.drawBitmap(this.softwareRenderingBitmap, this.softwareRenderingSrcBoundsRect, this.softwareRenderingDstBoundsRect, this.softwareRenderingPaint);
    }

    private void ensureSoftwareRenderingObjectsInitialized() {
        if (this.softwareRenderingCanvas != null) {
            return;
        }
        this.softwareRenderingCanvas = new Canvas();
        this.softwareRenderingTransformedBounds = new RectF();
        this.softwareRenderingOriginalCanvasMatrix = new Matrix();
        this.softwareRenderingOriginalCanvasMatrixInverse = new Matrix();
        this.canvasClipBounds = new Rect();
        this.canvasClipBoundsRectF = new RectF();
        this.softwareRenderingPaint = new LPaint();
        this.softwareRenderingSrcBoundsRect = new Rect();
        this.softwareRenderingDstBoundsRect = new Rect();
        this.softwareRenderingDstBoundsRectF = new RectF();
    }

    private void ensureSoftwareRenderingBitmap(int i, int i2) {
        Bitmap bitmap = this.softwareRenderingBitmap;
        if (bitmap == null || bitmap.getWidth() < i || this.softwareRenderingBitmap.getHeight() < i2) {
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            this.softwareRenderingBitmap = createBitmap;
            this.softwareRenderingCanvas.setBitmap(createBitmap);
            this.isDirty = true;
            return;
        }
        if (this.softwareRenderingBitmap.getWidth() > i || this.softwareRenderingBitmap.getHeight() > i2) {
            Bitmap createBitmap2 = Bitmap.createBitmap(this.softwareRenderingBitmap, 0, 0, i, i2);
            this.softwareRenderingBitmap = createBitmap2;
            this.softwareRenderingCanvas.setBitmap(createBitmap2);
            this.isDirty = true;
        }
    }

    private void convertRect(RectF rectF, Rect rect) {
        rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
    }

    private void convertRect(Rect rect, RectF rectF) {
        rectF.set(rect.left, rect.top, rect.right, rect.bottom);
    }

    private void scaleRect(RectF rectF, float f, float f2) {
        rectF.set(rectF.left * f, rectF.top * f2, rectF.right * f, rectF.bottom * f2);
    }

    private boolean ignoreCanvasClipBounds() {
        Drawable.Callback callback = getCallback();
        if (!(callback instanceof View)) {
            return false;
        }
        ViewParent parent = ((View) callback).getParent();
        if (Build.VERSION.SDK_INT < 18 || !(parent instanceof ViewGroup)) {
            return false;
        }
        return !((ViewGroup) parent).getClipChildren();
    }
}
