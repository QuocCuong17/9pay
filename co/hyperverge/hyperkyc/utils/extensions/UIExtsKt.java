package co.hyperverge.hyperkyc.utils.extensions;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;
import co.hyperverge.hypersnapsdk.utils.UIUtils;
import java.util.Iterator;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: UIExts.kt */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0000\u001a\u001c\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0003H\u0000\u001a\u0016\u0010\u0007\u001a\u00020\u0001*\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\n\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0003H\u0000\u001a\u0018\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\b2\b\b\u0001\u0010\u000e\u001a\u00020\u0001H\u0000\u001a\f\u0010\u000f\u001a\u00020\u0010*\u00020\u0011H\u0000\u001a\f\u0010\u0012\u001a\u00020\u0013*\u00020\u0001H\u0000\u001a\u000e\u0010\u0014\u001a\u00020\u0013*\u0004\u0018\u00010\u0011H\u0000\u001a\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0011*\u0004\u0018\u00010\u0011H\u0000\u001a\f\u0010\u0016\u001a\u00020\u0017*\u00020\u0018H\u0000\u001a\u0016\u0010\u0019\u001a\u00020\u0001*\u00020\b2\b\b\u0001\u0010\u001a\u001a\u00020\u0001H\u0000\u001a\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0001*\u00020\u0011H\u0000¢\u0006\u0002\u0010\u001c\u001a\u0014\u0010\u001d\u001a\u00020\u0017*\u00020\u001e2\u0006\u0010\u000e\u001a\u00020\u0001H\u0000\u001a\u0018\u0010\u001f\u001a\u00020\u0017*\u0004\u0018\u00010\r2\b\b\u0001\u0010 \u001a\u00020\u0001H\u0000\u001a\f\u0010!\u001a\u00020\u0011*\u00020\u0011H\u0000\u001a\f\u0010\"\u001a\u00020\u0001*\u00020\u0011H\u0000\u001a\f\u0010#\u001a\u00020$*\u00020\u0001H\u0000\u001a\f\u0010#\u001a\u00020$*\u00020\u0011H\u0000\u001a\u0014\u0010%\u001a\u00020\u0003*\u00020\u00042\u0006\u0010&\u001a\u00020\u0003H\u0000\u001a\u0016\u0010'\u001a\u00020\u0001*\u00020\u00012\b\b\u0001\u0010\u0000\u001a\u00020\u0001H\u0000¨\u0006("}, d2 = {"alpha", "", "applyDimension", "", "Landroid/util/DisplayMetrics;", "unit", "value", "colorOf", "Landroid/content/Context;", "colorResId", "dpToPx", "dp", "drawableOf", "Landroid/graphics/drawable/Drawable;", "drawableResId", "fromHTML", "Landroid/text/Spanned;", "", "hasAlpha", "", "isValidHexColor", "nullIfInvalidHexColor", "removeAllItemDecorations", "", "Landroidx/recyclerview/widget/RecyclerView;", "resolveResId", "resId", "rgbaToColorRes", "(Ljava/lang/String;)Ljava/lang/Integer;", "setImage", "Landroid/widget/ImageView;", "setTintColor", "color", "toArgb", "toColorRes", "toColorStateList", "Landroid/content/res/ColorStateList;", "toEm", "px", "withAlpha", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UIExtsKt {
    public static final /* synthetic */ boolean hasAlpha(int i) {
        return (i >>> 24) != 255;
    }

    public static final /* synthetic */ void setImage(ImageView imageView, int i) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        imageView.setImageDrawable(ResourcesCompat.getDrawable(imageView.getResources(), i, null));
    }

    public static final /* synthetic */ int dpToPx(Context context, float f) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (int) (f * (context.getResources().getDisplayMetrics().densityDpi / 160));
    }

    public static final /* synthetic */ float toEm(DisplayMetrics displayMetrics, float f) {
        Intrinsics.checkNotNullParameter(displayMetrics, "<this>");
        return f > 0.0f ? f / displayMetrics.densityDpi : f;
    }

    public static final /* synthetic */ float applyDimension(DisplayMetrics displayMetrics, int i, float f) {
        Intrinsics.checkNotNullParameter(displayMetrics, "<this>");
        return TypedValue.applyDimension(i, f, displayMetrics);
    }

    public static final /* synthetic */ Spanned fromHTML(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Spanned fromHtml = HtmlCompat.fromHtml(str, 0);
        Intrinsics.checkNotNullExpressionValue(fromHtml, "fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)");
        return fromHtml;
    }

    public static final /* synthetic */ String nullIfInvalidHexColor(String str) {
        if (isValidHexColor(str)) {
            return str;
        }
        return null;
    }

    public static final /* synthetic */ boolean isValidHexColor(String str) {
        String str2 = str;
        if (str2 == null || StringsKt.isBlank(str2)) {
            return false;
        }
        return Pattern.compile("^#(?:[\\da-fA-F]{6}|[\\da-fA-F]{8})$").matcher(str2).matches();
    }

    public static final /* synthetic */ int colorOf(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return ContextCompat.getColor(context, i);
    }

    public static final /* synthetic */ Drawable drawableOf(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return ContextCompat.getDrawable(context, i);
    }

    public static final /* synthetic */ void removeAllItemDecorations(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<this>");
        Iterator<Integer> it = RangesKt.until(0, recyclerView.getItemDecorationCount()).iterator();
        while (it.hasNext()) {
            recyclerView.removeItemDecorationAt(((IntIterator) it).nextInt());
        }
    }

    public static final /* synthetic */ void setTintColor(Drawable drawable, int i) {
        if (drawable == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            drawable.setColorFilter(new BlendModeColorFilter(i, BlendMode.SRC_ATOP));
        } else {
            drawable.setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static final /* synthetic */ ColorStateList toColorStateList(int i) {
        ColorStateList valueOf = ColorStateList.valueOf(i);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this)");
        return valueOf;
    }

    public static final /* synthetic */ ColorStateList toColorStateList(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        ColorStateList valueOf = ColorStateList.valueOf(Color.parseColor(str));
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(Color.parseColor(this))");
        return valueOf;
    }

    public static final /* synthetic */ String toArgb(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        String argb = UIUtils.toARGB(str);
        return argb == null ? str : argb;
    }

    public static final /* synthetic */ int toColorRes(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return Color.parseColor(str);
    }

    public static final /* synthetic */ Integer rgbaToColorRes(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (isValidHexColor(str)) {
            return Integer.valueOf(Color.parseColor(str));
        }
        return null;
    }

    public static final /* synthetic */ int alpha(int i) {
        return (int) ((hasAlpha(i) ? ((i >>> 24) & 255) / 255.0f : 1.0f) * 100);
    }

    public static final /* synthetic */ int resolveResId(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId;
    }
}
