package co.hyperverge.hyperkyc.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ClickableLinearLayout.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/ClickableLinearLayout;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "interceptTouch", "", "getInterceptTouch", "()Z", "setInterceptTouch", "(Z)V", "onInterceptTouchEvent", "ev", "Landroid/view/MotionEvent;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class ClickableLinearLayout extends LinearLayout {
    private boolean interceptTouch;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClickableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(attrs, "attrs");
    }

    public final boolean getInterceptTouch() {
        return this.interceptTouch;
    }

    public final void setInterceptTouch(boolean z) {
        this.interceptTouch = z;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.interceptTouch) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
