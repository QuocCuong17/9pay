package co.hyperverge.hypersnapsdk.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import co.hyperverge.hypersnapsdk.R;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class FaceBoxView extends FrameLayout {
    boolean isShowing;
    private TextView messageTV;
    Paint paint;
    boolean showMessage;
    int x1;
    int x2;
    int y1;
    int y2;

    public FaceBoxView(Context context) {
        super(context);
        this.paint = new Paint();
        this.isShowing = false;
        this.showMessage = false;
        initialize();
    }

    public FaceBoxView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.paint = new Paint();
        this.isShowing = false;
        this.showMessage = false;
        initialize();
    }

    public FaceBoxView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.paint = new Paint();
        this.isShowing = false;
        this.showMessage = false;
        initialize();
    }

    private void initialize() {
        inflate(getContext(), R.layout.frame_layout_facebox, this);
        setWillNotDraw(false);
        this.messageTV = (TextView) findViewById(R.id.tv_message);
    }

    public void showMessage(boolean z) {
        this.messageTV.setVisibility(z ? 0 : 8);
        invalidate();
    }

    public void setPoints(int i, int i2, int i3, int i4) {
        this.x1 = i;
        this.x2 = i3;
        this.y1 = i2;
        this.y2 = i4;
        invalidate();
    }

    public ArrayList<Integer> getPoints() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(Integer.valueOf(this.x1));
        arrayList.add(Integer.valueOf(this.y1));
        arrayList.add(Integer.valueOf(this.x2));
        arrayList.add(Integer.valueOf(this.y2));
        return arrayList;
    }

    public void showHideBox(boolean z) {
        if (getParent() == null) {
            return;
        }
        this.isShowing = z;
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (!this.isShowing) {
            this.messageTV.setVisibility(8);
            return;
        }
        this.paint.setColor(Color.parseColor("#99ca3e"));
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(10.0f);
        canvas.drawRect(this.x1, this.y1, this.x2, this.y2, this.paint);
    }
}
