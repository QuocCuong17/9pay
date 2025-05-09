package co.hyperverge.hyperkyc.ui.custom.delegates;

import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.viewbinding.ViewBinding;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegate;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;

/* compiled from: FragmentViewBindingDelegate.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00010\u0003B!\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\tJ\"\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00020\u00042\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0096\u0002¢\u0006\u0002\u0010\u0014R\u0012\u0010\n\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0015"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/delegates/FragmentViewBindingDelegate;", "T", "Landroidx/viewbinding/ViewBinding;", "Lkotlin/properties/ReadOnlyProperty;", "Landroidx/fragment/app/Fragment;", Request.JsonKeys.FRAGMENT, "viewBindingFactory", "Lkotlin/Function1;", "Landroid/view/View;", "(Landroidx/fragment/app/Fragment;Lkotlin/jvm/functions/Function1;)V", "binding", "Landroidx/viewbinding/ViewBinding;", "getFragment", "()Landroidx/fragment/app/Fragment;", "getViewBindingFactory", "()Lkotlin/jvm/functions/Function1;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Landroidx/fragment/app/Fragment;Lkotlin/reflect/KProperty;)Landroidx/viewbinding/ViewBinding;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FragmentViewBindingDelegate<T extends ViewBinding> implements ReadOnlyProperty<Fragment, T> {
    private T binding;
    private final Fragment fragment;
    private final Function1<View, T> viewBindingFactory;

    /* JADX WARN: Multi-variable type inference failed */
    public FragmentViewBindingDelegate(Fragment fragment, Function1<? super View, ? extends T> viewBindingFactory) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(viewBindingFactory, "viewBindingFactory");
        this.fragment = fragment;
        this.viewBindingFactory = viewBindingFactory;
        fragment.getLifecycle().addObserver(new AnonymousClass1(this));
    }

    @Override // kotlin.properties.ReadOnlyProperty
    public /* bridge */ /* synthetic */ Object getValue(Fragment fragment, KProperty kProperty) {
        return getValue2(fragment, (KProperty<?>) kProperty);
    }

    public final Fragment getFragment() {
        return this.fragment;
    }

    public final Function1<View, T> getViewBindingFactory() {
        return this.viewBindingFactory;
    }

    /* compiled from: FragmentViewBindingDelegate.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0016R\u0019\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"co/hyperverge/hyperkyc/ui/custom/delegates/FragmentViewBindingDelegate$1", "Landroidx/lifecycle/DefaultLifecycleObserver;", "viewLifecycleOwnerLiveDataObserver", "Landroidx/lifecycle/Observer;", "Landroidx/lifecycle/LifecycleOwner;", "getViewLifecycleOwnerLiveDataObserver", "()Landroidx/lifecycle/Observer;", "onCreate", "", "owner", "onDestroy", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegate$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 implements DefaultLifecycleObserver {
        final /* synthetic */ FragmentViewBindingDelegate<T> this$0;
        private final Observer<LifecycleOwner> viewLifecycleOwnerLiveDataObserver;

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
            Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
            Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public /* synthetic */ void onStart(LifecycleOwner lifecycleOwner) {
            Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public /* synthetic */ void onStop(LifecycleOwner lifecycleOwner) {
            Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        }

        AnonymousClass1(final FragmentViewBindingDelegate<T> fragmentViewBindingDelegate) {
            this.this$0 = fragmentViewBindingDelegate;
            this.viewLifecycleOwnerLiveDataObserver = new Observer() { // from class: co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegate$1$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FragmentViewBindingDelegate.AnonymousClass1.viewLifecycleOwnerLiveDataObserver$lambda$0(FragmentViewBindingDelegate.this, (LifecycleOwner) obj);
                }
            };
        }

        public final Observer<LifecycleOwner> getViewLifecycleOwnerLiveDataObserver() {
            return this.viewLifecycleOwnerLiveDataObserver;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void viewLifecycleOwnerLiveDataObserver$lambda$0(final FragmentViewBindingDelegate this$0, LifecycleOwner lifecycleOwner) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (lifecycleOwner == null) {
                return;
            }
            lifecycleOwner.getLifecycle().addObserver(new DefaultLifecycleObserver() { // from class: co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegate$1$viewLifecycleOwnerLiveDataObserver$1$1
                @Override // androidx.lifecycle.DefaultLifecycleObserver
                public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner2) {
                    Intrinsics.checkNotNullParameter(lifecycleOwner2, "owner");
                }

                @Override // androidx.lifecycle.DefaultLifecycleObserver
                public /* synthetic */ void onPause(LifecycleOwner lifecycleOwner2) {
                    Intrinsics.checkNotNullParameter(lifecycleOwner2, "owner");
                }

                @Override // androidx.lifecycle.DefaultLifecycleObserver
                public /* synthetic */ void onResume(LifecycleOwner lifecycleOwner2) {
                    Intrinsics.checkNotNullParameter(lifecycleOwner2, "owner");
                }

                @Override // androidx.lifecycle.DefaultLifecycleObserver
                public /* synthetic */ void onStart(LifecycleOwner lifecycleOwner2) {
                    Intrinsics.checkNotNullParameter(lifecycleOwner2, "owner");
                }

                @Override // androidx.lifecycle.DefaultLifecycleObserver
                public /* synthetic */ void onStop(LifecycleOwner lifecycleOwner2) {
                    Intrinsics.checkNotNullParameter(lifecycleOwner2, "owner");
                }

                @Override // androidx.lifecycle.DefaultLifecycleObserver
                public void onDestroy(LifecycleOwner owner) {
                    Intrinsics.checkNotNullParameter(owner, "owner");
                    ((FragmentViewBindingDelegate) this$0).binding = null;
                }
            });
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onCreate(LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            this.this$0.getFragment().getViewLifecycleOwnerLiveData().observeForever(this.viewLifecycleOwnerLiveDataObserver);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onDestroy(LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            this.this$0.getFragment().getViewLifecycleOwnerLiveData().removeObserver(this.viewLifecycleOwnerLiveDataObserver);
        }
    }

    /* renamed from: getValue, reason: avoid collision after fix types in other method */
    public T getValue2(Fragment thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(thisRef, "thisRef");
        Intrinsics.checkNotNullParameter(property, "property");
        T t = this.binding;
        if (t != null) {
            return t;
        }
        Lifecycle lifecycle = this.fragment.getViewLifecycleOwner().getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "fragment.viewLifecycleOwner.lifecycle");
        if (!lifecycle.getState().isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw new IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.");
        }
        Function1<View, T> function1 = this.viewBindingFactory;
        View requireView = thisRef.requireView();
        Intrinsics.checkNotNullExpressionValue(requireView, "thisRef.requireView()");
        T invoke = function1.invoke(requireView);
        this.binding = invoke;
        return invoke;
    }
}
