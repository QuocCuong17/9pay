package co.hyperverge.hypersnapsdk.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import co.hyperverge.hypersnapsdk.R;

/* loaded from: classes2.dex */
public class CrossHairView extends View {
    private Drawable crossHair;
    private Drawable crossHairFocused;
    private Runnable hideCrosshair;
    private boolean isFocused;
    private boolean isShowing;
    private int x;
    private int y;

    public CrossHairView(Context context) {
        super(context);
        this.hideCrosshair = new Runnable() { // from class: co.hyperverge.hypersnapsdk.views.CrossHairView.1
            @Override // java.lang.Runnable
            public void run() {
                CrossHairView.this.isShowing = false;
                CrossHairView.this.invalidate();
            }
        };
        init();
    }

    private void init() {
        this.crossHair = getContext().getResources().getDrawable(R.drawable.ic_camera_focus_white_svg);
        this.crossHairFocused = getContext().getResources().getDrawable(R.drawable.ic_camera_focus_white_svg);
    }

    public void showCrosshair(float f, float f2, boolean z) {
        this.isFocused = z;
        showCrosshair(f, f2);
    }

    public void showCrosshair(float f, float f2) {
        this.x = (int) f;
        this.y = (int) f2;
        removeCallbacks(this.hideCrosshair);
        this.isShowing = true;
        invalidate();
        postDelayed(this.hideCrosshair, 1000L);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.isShowing) {
            int intrinsicWidth = this.crossHair.getIntrinsicWidth();
            int intrinsicHeight = this.crossHair.getIntrinsicHeight();
            if (this.isFocused) {
                Drawable drawable = this.crossHairFocused;
                int i = this.x;
                int i2 = intrinsicWidth / 2;
                int i3 = this.y;
                int i4 = intrinsicHeight / 2;
                drawable.setBounds(i - i2, i3 - i4, i + i2, i3 + i4);
                this.crossHairFocused.draw(canvas);
                return;
            }
            Drawable drawable2 = this.crossHair;
            int i5 = this.x;
            int i6 = intrinsicWidth / 2;
            int i7 = this.y;
            int i8 = intrinsicHeight / 2;
            drawable2.setBounds(i5 - i6, i7 - i8, i5 + i6, i7 + i8);
            this.crossHair.draw(canvas);
        }
    }
}
