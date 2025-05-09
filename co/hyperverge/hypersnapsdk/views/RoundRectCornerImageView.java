package co.hyperverge.hypersnapsdk.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: classes2.dex */
public class RoundRectCornerImageView extends ImageView {
    private Path path;
    private float radius;
    private RectF rect;

    public RoundRectCornerImageView(Context context) {
        super(context);
        this.radius = 30.0f;
        init();
    }

    public RoundRectCornerImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.radius = 30.0f;
        init();
    }

    public RoundRectCornerImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.radius = 30.0f;
        init();
    }

    private void init() {
        this.path = new Path();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        RectF rectF = new RectF(0.0f, 0.0f, getWidth(), getHeight());
        this.rect = rectF;
        Path path = this.path;
        float f = this.radius;
        path.addRoundRect(rectF, f, f, Path.Direction.CW);
        canvas.clipPath(this.path);
        super.onDraw(canvas);
    }
}
