package co.hyperverge.hyperkyc.ui.custom.delegates;

import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FragmentViewBindingDelegate.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u00020\u0006¨\u0006\b"}, d2 = {"viewBinding", "Lco/hyperverge/hyperkyc/ui/custom/delegates/FragmentViewBindingDelegate;", "T", "Landroidx/viewbinding/ViewBinding;", "Landroidx/fragment/app/Fragment;", "viewBindingFactory", "Lkotlin/Function1;", "Landroid/view/View;", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FragmentViewBindingDelegateKt {
    public static final <T extends ViewBinding> FragmentViewBindingDelegate<T> viewBinding(Fragment fragment, Function1<? super View, ? extends T> viewBindingFactory) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(viewBindingFactory, "viewBindingFactory");
        return new FragmentViewBindingDelegate<>(fragment, viewBindingFactory);
    }
}
