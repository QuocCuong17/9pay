package vn.ai.faceauth.sdk.presentation.presentation.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import vn.ai.faceauth.sdk.R;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0014R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/widgets/GifDrawableView;", "Landroid/view/View;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "gifResourceId", "movie", "Landroid/graphics/Movie;", "movieHeight", "movieStart", "", "movieWidth", Session.JsonKeys.INIT, "", "onDraw", "canvas", "Landroid/graphics/Canvas;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class GifDrawableView extends View {
    private int gifResourceId;
    private Movie movie;
    private int movieHeight;
    private long movieStart;
    private int movieWidth;

    public GifDrawableView(Context context) {
        this(context, null, 0, 6, null);
    }

    public GifDrawableView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public GifDrawableView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.gifResourceId = -1;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.GifDrawableView, 0, 0);
            try {
                this.gifResourceId = obtainStyledAttributes.getResourceId(R.styleable.GifDrawableView_gifSrc, -1);
                init();
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    public /* synthetic */ GifDrawableView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final void init() {
        if (this.gifResourceId != -1) {
            Movie decodeStream = Movie.decodeStream(getContext().getResources().openRawResource(this.gifResourceId));
            this.movie = decodeStream;
            if (decodeStream != null) {
                this.movieWidth = decodeStream.width();
                this.movieHeight = decodeStream.height();
            }
            this.movieStart = SystemClock.uptimeMillis();
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Movie movie = this.movie;
        if (movie != null) {
            float width = getWidth();
            float height = getHeight();
            float min = Math.min(width / this.movieWidth, height / this.movieHeight);
            float f = 2;
            float f2 = (width - (this.movieWidth * min)) / f;
            float f3 = (height - (this.movieHeight * min)) / f;
            long uptimeMillis = SystemClock.uptimeMillis();
            Integer valueOf = Integer.valueOf(movie.duration());
            if (!(valueOf.intValue() > 0)) {
                valueOf = null;
            }
            movie.setTime((int) ((uptimeMillis - this.movieStart) % (valueOf != null ? valueOf.intValue() : 1000)));
            canvas.save();
            canvas.translate(f2, f3);
            canvas.scale(min, min);
            movie.draw(canvas, 0.0f, 0.0f);
            canvas.restore();
            invalidate();
        }
    }
}
