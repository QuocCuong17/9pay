package vn.ai.faceauth.sdk.presentation.presentation.utils;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.TextView;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.domain.model.Rect;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0006\u001a\u001c\u0010\u0007\u001a\u00020\b*\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r\u001a\u001c\u0010\u0007\u001a\u00020\b*\u00020\u000e2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r¨\u0006\u000f"}, d2 = {"isBitmapBlurryOptimized", "", "bitmap", "Landroid/graphics/Bitmap;", "toDomainRect", "Lvn/ai/faceauth/sdk/domain/model/Rect;", "Landroid/graphics/Rect;", "updateFont", "", "Landroid/widget/Button;", "fontName", "", TtmlNode.ATTR_TTS_FONT_SIZE, "", "Landroid/widget/TextView;", "trueface_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class UtilsKt {
    public static final boolean isBitmapBlurryOptimized(Bitmap bitmap) {
        int i;
        boolean z = true;
        z = true;
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 0.25f), (int) (bitmap.getHeight() * 0.25f), true);
        int width = createScaledBitmap.getWidth();
        int height = createScaledBitmap.getHeight();
        int[] iArr = new int[width * height];
        createScaledBitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int i2 = 2;
        IntProgression step = RangesKt.step(RangesKt.until(1, height - 1), 2);
        int first = step.getFirst();
        int last = step.getLast();
        int step2 = step.getStep();
        double d = 0.0d;
        if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
            i = 0;
            while (true) {
                IntProgression step3 = RangesKt.step(RangesKt.until(z ? 1 : 0, width - 1), i2);
                int first2 = step3.getFirst();
                int last2 = step3.getLast();
                int step4 = step3.getStep();
                if ((step4 > 0 && first2 <= last2) || (step4 < 0 && last2 <= first2)) {
                    while (true) {
                        int i3 = iArr[((first - 1) * width) + first2];
                        int i4 = iArr[((first + 1) * width) + first2];
                        int i5 = first * width;
                        double d2 = (iArr[(first2 + 1) + i5] & 255) - (iArr[(first2 - 1) + i5] & 255);
                        double d3 = (i4 & 255) - (i3 & 255);
                        Double.isNaN(d2);
                        Double.isNaN(d2);
                        Double.isNaN(d3);
                        Double.isNaN(d3);
                        d += Math.sqrt((d3 * d3) + (d2 * d2));
                        z = true;
                        i++;
                        if (first2 == last2) {
                            break;
                        }
                        first2 += step4;
                    }
                }
                if (first == last) {
                    break;
                }
                first += step2;
                i2 = 2;
            }
        } else {
            i = 0;
        }
        double d4 = i;
        Double.isNaN(d4);
        if (d / d4 < 15.0d) {
            return z;
        }
        return false;
    }

    public static final Rect toDomainRect(android.graphics.Rect rect) {
        return new Rect(rect.left, rect.top, rect.right, rect.bottom);
    }

    public static final void updateFont(Button button, String str, float f) {
        String rnigpa = tfwhgw.rnigpa(460);
        if (f > 0.0f) {
            button.setTextSize(2, f);
        }
        if ((str.length() == 0) || StringsKt.isBlank(str)) {
            return;
        }
        try {
            Typeface typeface = button.getTypeface();
            button.setTypeface(Typeface.createFromAsset(button.getContext().getAssets(), rnigpa + str), typeface.getStyle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void updateFont(TextView textView, String str, float f) {
        String rnigpa = tfwhgw.rnigpa(461);
        if (f > 0.0f) {
            textView.setTextSize(2, f);
        }
        if ((str.length() == 0) || StringsKt.isBlank(str)) {
            return;
        }
        try {
            Typeface typeface = textView.getTypeface();
            textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), rnigpa + str), typeface.getStyle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void updateFont$default(Button button, String str, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        updateFont(button, str, f);
    }

    public static /* synthetic */ void updateFont$default(TextView textView, String str, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        updateFont(textView, str, f);
    }
}
