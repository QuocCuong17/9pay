package com.anish.trust_fall.rooted;

import android.content.Context;
import android.os.Build;
import com.scottyab.rootbeer.RootBeer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: RootedCheck.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0012\u0010\u000b\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/anish/trust_fall/rooted/RootedCheck;", "", "()V", "MOTO", "", "ONEPLUS", "XIAOMI", "isJailBroken", "", "context", "Landroid/content/Context;", "rootBeerCheck", "jailbreak_root_detection_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RootedCheck {
    public static final RootedCheck INSTANCE = new RootedCheck();
    private static final String MOTO = "moto";
    private static final String ONEPLUS = "oneplus";
    private static final String XIAOMI = "Xiaomi";

    private RootedCheck() {
    }

    public final boolean isJailBroken(Context context) {
        LessThan23 lessThan23;
        if (Build.VERSION.SDK_INT >= 23) {
            lessThan23 = new GreaterThan23();
        } else {
            lessThan23 = new LessThan23();
        }
        return lessThan23.checkRooted() || rootBeerCheck(context);
    }

    private final boolean rootBeerCheck(Context context) {
        RootBeer rootBeer = new RootBeer(context);
        String BRAND = Build.BRAND;
        Intrinsics.checkNotNullExpressionValue(BRAND, "BRAND");
        if (!StringsKt.contains$default((CharSequence) BRAND, (CharSequence) ONEPLUS, false, 2, (Object) null)) {
            String BRAND2 = Build.BRAND;
            Intrinsics.checkNotNullExpressionValue(BRAND2, "BRAND");
            if (!StringsKt.contains$default((CharSequence) BRAND2, (CharSequence) MOTO, false, 2, (Object) null)) {
                String BRAND3 = Build.BRAND;
                Intrinsics.checkNotNullExpressionValue(BRAND3, "BRAND");
                if (!StringsKt.contains$default((CharSequence) BRAND3, (CharSequence) XIAOMI, false, 2, (Object) null)) {
                    return rootBeer.isRooted();
                }
            }
        }
        return rootBeer.isRootedWithoutBusyBoxCheck();
    }
}
