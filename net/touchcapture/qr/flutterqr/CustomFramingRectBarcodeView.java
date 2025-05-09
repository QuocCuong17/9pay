package net.touchcapture.qr.flutterqr;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.journeyapps.barcodescanner.BarcodeView;
import com.journeyapps.barcodescanner.Size;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomFramingRectBarcodeView.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0014J\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tR\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lnet/touchcapture/qr/flutterqr/CustomFramingRectBarcodeView;", "Lcom/journeyapps/barcodescanner/BarcodeView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "bottomOffset", "calculateFramingRect", "Landroid/graphics/Rect;", TtmlNode.RUBY_CONTAINER, "surface", "setFramingRect", "", "rectWidth", "rectHeight", "Companion", "qr_code_scanner_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class CustomFramingRectBarcodeView extends BarcodeView {
    private static final int BOTTOM_OFFSET_NOT_SET_VALUE = -1;
    private int bottomOffset;

    public CustomFramingRectBarcodeView(Context context) {
        super(context);
        this.bottomOffset = -1;
    }

    public CustomFramingRectBarcodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.bottomOffset = -1;
    }

    public CustomFramingRectBarcodeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.bottomOffset = -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.journeyapps.barcodescanner.CameraPreview
    public Rect calculateFramingRect(Rect container, Rect surface) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(surface, "surface");
        Rect rect = new Rect(container);
        rect.intersect(surface);
        Rect scanAreaRect = super.calculateFramingRect(container, surface);
        if (this.bottomOffset != -1) {
            Rect rect2 = new Rect(scanAreaRect);
            rect2.bottom -= this.bottomOffset;
            rect2.top -= this.bottomOffset;
            if (rect2.intersect(rect)) {
                return rect2;
            }
        }
        Intrinsics.checkNotNullExpressionValue(scanAreaRect, "scanAreaRect");
        return scanAreaRect;
    }

    public final void setFramingRect(int rectWidth, int rectHeight, int bottomOffset) {
        this.bottomOffset = bottomOffset;
        setFramingRectSize(new Size(rectWidth, rectHeight));
    }
}
