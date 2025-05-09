package co.hyperverge.hyperkyc.ui.custom.blocks;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.widget.AppCompatTextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CursorTextView.kt */
@Metadata(d1 = {"\u0000?\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\b\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\u0015\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\rH\u0000¢\u0006\u0002\b\u0016R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/blocks/CursorTextView;", "Landroidx/appcompat/widget/AppCompatTextView;", "context", "Landroid/content/Context;", "cursorTextViewConfig", "Lco/hyperverge/hyperkyc/ui/custom/blocks/CursorTextViewConfig;", "(Landroid/content/Context;Lco/hyperverge/hyperkyc/ui/custom/blocks/CursorTextViewConfig;)V", "blinkRunnable", "co/hyperverge/hyperkyc/ui/custom/blocks/CursorTextView$blinkRunnable$1", "Lco/hyperverge/hyperkyc/ui/custom/blocks/CursorTextView$blinkRunnable$1;", "cursorPaint", "Landroid/graphics/Paint;", "cursorVisible", "", "handler", "Landroid/os/Handler;", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "showCursor", "shouldShow", "showCursor$hyperkyc_release", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CursorTextView extends AppCompatTextView {
    private final CursorTextView$blinkRunnable$1 blinkRunnable;
    private final Paint cursorPaint;
    private final CursorTextViewConfig cursorTextViewConfig;
    private boolean cursorVisible;
    private final Handler handler;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v3, types: [co.hyperverge.hyperkyc.ui.custom.blocks.CursorTextView$blinkRunnable$1] */
    public CursorTextView(Context context, CursorTextViewConfig cursorTextViewConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cursorTextViewConfig, "cursorTextViewConfig");
        this.cursorTextViewConfig = cursorTextViewConfig;
        Paint paint = new Paint();
        paint.setColor(Color.parseColor(cursorTextViewConfig.getCursorColor()));
        paint.setStrokeWidth(5.0f);
        paint.setAntiAlias(true);
        this.cursorPaint = paint;
        this.cursorVisible = true;
        this.handler = new Handler(Looper.getMainLooper());
        this.blinkRunnable = new Runnable() { // from class: co.hyperverge.hyperkyc.ui.custom.blocks.CursorTextView$blinkRunnable$1
            @Override // java.lang.Runnable
            public void run() {
                boolean z;
                Handler handler;
                CursorTextView cursorTextView = CursorTextView.this;
                z = cursorTextView.cursorVisible;
                cursorTextView.cursorVisible = !z;
                CursorTextView.this.invalidate();
                handler = CursorTextView.this.handler;
                handler.postDelayed(this, 500L);
            }
        };
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        float width = (getWidth() / 2) + (getLayout().getLineWidth(0) / 2) + this.cursorPaint.getStrokeWidth();
        float height = (getHeight() - this.cursorTextViewConfig.getCursorHeight()) / 2;
        float cursorHeight = height + this.cursorTextViewConfig.getCursorHeight();
        if (this.cursorVisible) {
            canvas.drawLine(width, height, width, cursorHeight, this.cursorPaint);
        }
    }

    public final void showCursor$hyperkyc_release(boolean shouldShow) {
        this.handler.removeCallbacks(this.blinkRunnable);
        this.cursorVisible = shouldShow;
        if (shouldShow) {
            this.handler.postDelayed(this.blinkRunnable, 500L);
        } else {
            this.handler.removeCallbacks(this.blinkRunnable);
            invalidate();
        }
    }
}
