package co.hyperverge.hyperkyc.ui.custom.blocks;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import co.hyperverge.hyperkyc.utils.extensions.DrawableExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BlockItemView.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0000\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0015\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013J\u0015\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/blocks/BlockItemView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "blockItemViewConfig", "Lco/hyperverge/hyperkyc/ui/custom/blocks/BlockItemViewConfig;", "(Landroid/content/Context;Lco/hyperverge/hyperkyc/ui/custom/blocks/BlockItemViewConfig;)V", "boxBackground", "Landroid/graphics/drawable/Drawable;", "textView", "Lco/hyperverge/hyperkyc/ui/custom/blocks/CursorTextView;", "generateBlockItemView", "", "getBackgroundDrawable", "drawableResId", "", "setText", "value", "", "setText$hyperkyc_release", "setViewState", "state", "setViewState$hyperkyc_release", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BlockItemView extends FrameLayout {
    public static final int ACTIVE = 1;
    public static final int ERROR = -1;
    public static final int INACTIVE = 0;
    private final BlockItemViewConfig blockItemViewConfig;
    private Drawable boxBackground;
    private CursorTextView textView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BlockItemView(Context context, BlockItemViewConfig blockItemViewConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(blockItemViewConfig, "blockItemViewConfig");
        this.blockItemViewConfig = blockItemViewConfig;
        generateBlockItemView();
    }

    private final void generateBlockItemView() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        CursorTextView cursorTextView = new CursorTextView(context, new CursorTextViewConfig(this.blockItemViewConfig.getActiveBorderColor(), ViewExtsKt.getSp(this.blockItemViewConfig.getBlockTextSize()) + ViewExtsKt.getSp(10)));
        cursorTextView.setGravity(17);
        cursorTextView.setTextColor(ColorStateList.valueOf(Color.parseColor(this.blockItemViewConfig.getTextColor())));
        cursorTextView.setTextSize(2, this.blockItemViewConfig.getBlockTextSize());
        cursorTextView.setTypeface(this.blockItemViewConfig.getBlockTextTypeFace());
        addView(cursorTextView, layoutParams);
        this.textView = cursorTextView;
        setBackgroundResource(R.color.transparent);
        Drawable backgroundDrawable = getBackgroundDrawable(co.hyperverge.hyperkyc.R.drawable.hk_bg_block_box);
        this.boxBackground = backgroundDrawable;
        if (backgroundDrawable != null) {
            DrawableExtsKt.setStroke(backgroundDrawable, 1, Color.parseColor(this.blockItemViewConfig.getInactiveBorderColor()));
        }
        setBackground(this.boxBackground);
    }

    private final Drawable getBackgroundDrawable(int drawableResId) {
        return ContextCompat.getDrawable(getContext(), drawableResId);
    }

    public final void setText$hyperkyc_release(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        CursorTextView cursorTextView = this.textView;
        if (cursorTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textView");
            cursorTextView = null;
        }
        cursorTextView.setText(value);
    }

    public final void setViewState$hyperkyc_release(int state) {
        int parseColor;
        CursorTextView cursorTextView = this.textView;
        if (cursorTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textView");
            cursorTextView = null;
        }
        cursorTextView.showCursor$hyperkyc_release(state == 1);
        if (state == -1) {
            parseColor = Color.parseColor(this.blockItemViewConfig.getErrorBorderColor());
        } else if (state == 1) {
            parseColor = Color.parseColor(this.blockItemViewConfig.getActiveBorderColor());
        } else {
            parseColor = Color.parseColor(this.blockItemViewConfig.getInactiveBorderColor());
        }
        Drawable drawable = this.boxBackground;
        if (drawable != null) {
            DrawableExtsKt.setStroke(drawable, 1, parseColor);
        }
    }
}
