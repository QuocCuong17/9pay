package co.hyperverge.hypersnapsdk.views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.media3.extractor.text.ttml.TtmlNode;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.utils.UIUtils;

/* loaded from: classes2.dex */
public class TopBarLayout extends LinearLayout {
    int numberOfButtons;

    public TopBarLayout(Context context) {
        super(context);
        this.numberOfButtons = 3;
        if (isInEditMode()) {
            return;
        }
        init();
    }

    public TopBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.numberOfButtons = 3;
        if (isInEditMode()) {
            return;
        }
        init();
    }

    public TopBarLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.numberOfButtons = 3;
        if (isInEditMode()) {
            return;
        }
        init();
    }

    private void init() {
        int i = 0;
        setOrientation(0);
        while (i < this.numberOfButtons) {
            View inflate = inflate(getContext(), R.layout.view_top_tick, null);
            if (i == 0) {
                setCustomBackground(inflate, R.drawable.circular_dots);
            } else {
                setCustomBackground(inflate, R.drawable.grey_dots);
            }
            int i2 = i + 1;
            inflate.setTag(TtmlNode.TEXT_EMPHASIS_MARK_DOT + i2);
            addView(inflate);
            if (i < 2) {
                View inflate2 = inflate(getContext(), R.layout.view_bar, null);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(UIUtils.dpToPx(getContext(), 40.0f), UIUtils.dpToPx(getContext(), 1.0f));
                layoutParams.gravity = 17;
                inflate2.setTag("bar" + i2);
                inflate2.setLayoutParams(layoutParams);
                addView(inflate2);
            }
            i = i2;
        }
    }

    public void setCustomBackground(View view, int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(getContext().getDrawable(i));
        } else {
            view.setBackgroundDrawable(getContext().getDrawable(i));
        }
    }

    public void updateAttemptView(int i) {
        View findViewWithTag = findViewWithTag(TtmlNode.TEXT_EMPHASIS_MARK_DOT + i);
        if (findViewWithTag != null) {
            setCustomBackground(findViewWithTag, R.drawable.green_dots);
            if (i < 3) {
                setCustomBackground(findViewWithTag(TtmlNode.TEXT_EMPHASIS_MARK_DOT + (i + 1)), R.drawable.circular_dots);
                findViewWithTag("bar" + i).setBackgroundColor(getResources().getColor(R.color.progress_green));
            }
        }
    }

    public void resetViewsToOriginalState() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (i % 2 != 0) {
                childAt.setBackgroundColor(getResources().getColor(R.color.grey_circle));
            } else if (i == 0) {
                setCustomBackground(childAt, R.drawable.circular_dots);
            } else {
                setCustomBackground(childAt, R.drawable.grey_dots);
            }
        }
    }
}
