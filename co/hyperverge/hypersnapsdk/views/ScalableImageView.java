package co.hyperverge.hypersnapsdk.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import co.hyperverge.hypersnapsdk.utils.UIUtils;

/* loaded from: classes2.dex */
public class ScalableImageView extends ImageView {
    public ScalableImageView(Context context) {
        super(context);
    }

    public ScalableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ScalableImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        if (getDrawable() != null) {
            int dpToPx = UIUtils.dpToPx(getContext(), 120.0f);
            if (UIUtils.hasNavBar(getContext())) {
                dpToPx = UIUtils.dpToPx(getContext(), 100.0f);
            }
            setMeasuredDimension(dpToPx, dpToPx);
            return;
        }
        super.onMeasure(i, i2);
    }
}
