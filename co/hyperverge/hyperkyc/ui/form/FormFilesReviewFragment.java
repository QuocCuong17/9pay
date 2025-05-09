package co.hyperverge.hyperkyc.ui.form;

import android.app.Application;
import android.app.Dialog;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.databinding.HkFragmentFormFilesReviewBinding;
import co.hyperverge.hyperkyc.databinding.HkRvItemFormFileReviewBinding;
import co.hyperverge.hyperkyc.databinding.HkRvItemFormFileReviewDocumentBinding;
import co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegate;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegateKt;
import co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment;
import co.hyperverge.hyperkyc.ui.form.models.PickedFile;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textview.MaterialTextView;
import java.util.List;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: FormFilesReviewFragment.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0001&B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u0010\u0018\u001a\u00020\u0003H\u0002J&\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u001a\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\r\u0010$\u001a\u00020\"H\u0000¢\u0006\u0002\b%R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFilesReviewFragment;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "pos", "", "pickedFiles", "", "Lco/hyperverge/hyperkyc/ui/form/models/PickedFile;", "(ILjava/util/List;)V", "binding", "Lco/hyperverge/hyperkyc/databinding/HkFragmentFormFilesReviewBinding;", "getBinding", "()Lco/hyperverge/hyperkyc/databinding/HkFragmentFormFilesReviewBinding;", "binding$delegate", "Lco/hyperverge/hyperkyc/ui/custom/delegates/FragmentViewBindingDelegate;", "formVM", "Lco/hyperverge/hyperkyc/ui/form/FormVM;", "getFormVM", "()Lco/hyperverge/hyperkyc/ui/form/FormVM;", "formVM$delegate", "Lkotlin/Lazy;", "pickedFilesRvAdapter", "Lco/hyperverge/hyperkyc/ui/custom/SimpleRvAdapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "getVisiblePickedFile", "getVisiblePos", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", ViewHierarchyConstants.VIEW_KEY, "updateUI", "updateUI$hyperkyc_release", "PickedFileReviewVH", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormFilesReviewFragment extends BottomSheetDialogFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(FormFilesReviewFragment.class, "binding", "getBinding()Lco/hyperverge/hyperkyc/databinding/HkFragmentFormFilesReviewBinding;", 0))};

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final FragmentViewBindingDelegate binding;

    /* renamed from: formVM$delegate, reason: from kotlin metadata */
    private final Lazy formVM;
    private final List<PickedFile> pickedFiles;
    private final SimpleRvAdapter<PickedFile, RecyclerView.ViewHolder> pickedFilesRvAdapter;
    private final int pos;

    public FormFilesReviewFragment(int i, List<PickedFile> pickedFiles) {
        Intrinsics.checkNotNullParameter(pickedFiles, "pickedFiles");
        this.pos = i;
        this.pickedFiles = pickedFiles;
        final FormFilesReviewFragment formFilesReviewFragment = this;
        this.binding = FragmentViewBindingDelegateKt.viewBinding(formFilesReviewFragment, FormFilesReviewFragment$binding$2.INSTANCE);
        this.formVM = FragmentViewModelLazyKt.createViewModelLazy(formFilesReviewFragment, Reflection.getOrCreateKotlinClass(FormVM.class), new Function0<ViewModelStore>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$special$$inlined$activityViewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
        this.pickedFilesRvAdapter = new SimpleRvAdapter<>(new Function1<Integer, Integer>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$pickedFilesRvAdapter$1
            public final Integer invoke(int i2) {
                return Integer.valueOf(R.layout.hk_rv_item_form_file_review);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                return invoke(num.intValue());
            }
        }, new Function2<View, Integer, RecyclerView.ViewHolder>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$pickedFilesRvAdapter$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            public final RecyclerView.ViewHolder invoke(View view, int i2) {
                Intrinsics.checkNotNullParameter(view, "view");
                FormFilesReviewFragment formFilesReviewFragment2 = FormFilesReviewFragment.this;
                HkRvItemFormFileReviewBinding bind = HkRvItemFormFileReviewBinding.bind(view);
                Intrinsics.checkNotNullExpressionValue(bind, "bind(view)");
                return new FormFilesReviewFragment.PickedFileReviewVH(formFilesReviewFragment2, bind);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ RecyclerView.ViewHolder invoke(View view, Integer num) {
                return invoke(view, num.intValue());
            }
        }, new Function2<RecyclerView.ViewHolder, PickedFile, Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$pickedFilesRvAdapter$3
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(RecyclerView.ViewHolder viewHolder, PickedFile pickedFile) {
                invoke2(viewHolder, pickedFile);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RecyclerView.ViewHolder h, PickedFile item) {
                Intrinsics.checkNotNullParameter(h, "h");
                Intrinsics.checkNotNullParameter(item, "item");
                ((FormFilesReviewFragment.PickedFileReviewVH) h).bind(item);
            }
        }, new Function2<PickedFile, PickedFile, Boolean>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$pickedFilesRvAdapter$4
            @Override // kotlin.jvm.functions.Function2
            public final Boolean invoke(PickedFile old, PickedFile pickedFile) {
                Intrinsics.checkNotNullParameter(old, "old");
                Intrinsics.checkNotNullParameter(pickedFile, "new");
                return Boolean.valueOf(Intrinsics.areEqual(old, pickedFile));
            }
        }, new Function2<PickedFile, PickedFile, Boolean>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$pickedFilesRvAdapter$5
            @Override // kotlin.jvm.functions.Function2
            public final Boolean invoke(PickedFile old, PickedFile pickedFile) {
                Intrinsics.checkNotNullParameter(old, "old");
                Intrinsics.checkNotNullParameter(pickedFile, "new");
                return Boolean.valueOf(Intrinsics.areEqual(old.getUri(), pickedFile.getUri()));
            }
        }, new Function1<Integer, Integer>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$pickedFilesRvAdapter$6
            public final Integer invoke(int i2) {
                return Integer.valueOf(i2 == 0 ? 10 : 11);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                return invoke(num.intValue());
            }
        }, null, null, 192, null);
    }

    private final HkFragmentFormFilesReviewBinding getBinding() {
        return (HkFragmentFormFilesReviewBinding) this.binding.getValue2((Fragment) this, $$delegatedProperties[0]);
    }

    private final FormVM getFormVM() {
        return (FormVM) this.formVM.getValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0143, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0153, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0157, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0166, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0168, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0173, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0179, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x017c, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0183, code lost:
    
        r0 = new java.lang.StringBuilder();
        r2 = "onViewCreated() called with: view = " + r18 + ", savedInstanceState = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x019d, code lost:
    
        if (r2 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x019f, code lost:
    
        r2 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01a1, code lost:
    
        r0.append(r2);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0156, code lost:
    
        r8 = r0;
     */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str3 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str4 = "onViewCreated() called with: view = " + view + ", savedInstanceState = " + savedInstanceState;
        if (str4 == null) {
            str4 = "null ";
        }
        sb.append(str4);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                        str = null;
                    } else {
                        str = null;
                        str2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls2 = getClass();
                    str2 = cls2 != null ? cls2.getCanonicalName() : str;
                }
            }
        }
        Dialog dialog = getDialog();
        Intrinsics.checkNotNull(dialog, "null cannot be cast to non-null type com.google.android.material.bottomsheet.BottomSheetDialog");
        BottomSheetBehavior<FrameLayout> behavior = ((BottomSheetDialog) dialog).getBehavior();
        behavior.setDraggable(false);
        behavior.setFitToContents(true);
        behavior.setState(3);
        final HkFragmentFormFilesReviewBinding binding = getBinding();
        RecyclerView recyclerView = binding.rvFiles;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 0, false));
        recyclerView.setAdapter(this.pickedFilesRvAdapter);
        new PagerSnapHelper().attachToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$onViewCreated$3$1$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int dx, int dy) {
                Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                super.onScrolled(recyclerView2, dx, dy);
                FormFilesReviewFragment.this.updateUI$hyperkyc_release();
            }
        });
        binding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FormFilesReviewFragment.onViewCreated$lambda$7$lambda$4(FormFilesReviewFragment.this, view2);
            }
        });
        binding.ivRemove.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FormFilesReviewFragment.onViewCreated$lambda$7$lambda$6(FormFilesReviewFragment.this, view2);
            }
        });
        this.pickedFilesRvAdapter.updateItems(this.pickedFiles, new Function1<List<? extends PickedFile>, Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$onViewCreated$3$4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends PickedFile> list) {
                invoke2((List<PickedFile>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<PickedFile> it) {
                int i;
                Intrinsics.checkNotNullParameter(it, "it");
                RecyclerView recyclerView2 = HkFragmentFormFilesReviewBinding.this.rvFiles;
                i = this.pos;
                recyclerView2.scrollToPosition(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$7$lambda$4(FormFilesReviewFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$7$lambda$6(final FormFilesReviewFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PickedFile visiblePickedFile = this$0.getVisiblePickedFile();
        if (visiblePickedFile != null) {
            this$0.getFormVM().removePickedFile(visiblePickedFile);
            this$0.pickedFilesRvAdapter.removeItem((SimpleRvAdapter<PickedFile, RecyclerView.ViewHolder>) visiblePickedFile, new Function1<List<? extends PickedFile>, Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$onViewCreated$3$3$1$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<? extends PickedFile> list) {
                    invoke2((List<PickedFile>) list);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(List<PickedFile> it) {
                    SimpleRvAdapter simpleRvAdapter;
                    Intrinsics.checkNotNullParameter(it, "it");
                    FormFilesReviewFragment.this.updateUI$hyperkyc_release();
                    simpleRvAdapter = FormFilesReviewFragment.this.pickedFilesRvAdapter;
                    if (simpleRvAdapter.getItemCount() == 0) {
                        FormFilesReviewFragment.this.dismissAllowingStateLoss();
                    }
                }
            });
        }
    }

    private final int getVisiblePos() {
        RecyclerView.LayoutManager layoutManager = getBinding().rvFiles.getLayoutManager();
        Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
    }

    /* compiled from: FormFilesReviewFragment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001:\u0001\tB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFilesReviewFragment$PickedFileReviewVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemBinding", "Lco/hyperverge/hyperkyc/databinding/HkRvItemFormFileReviewBinding;", "(Lco/hyperverge/hyperkyc/ui/form/FormFilesReviewFragment;Lco/hyperverge/hyperkyc/databinding/HkRvItemFormFileReviewBinding;)V", "bind", "", "pickedFile", "Lco/hyperverge/hyperkyc/ui/form/models/PickedFile;", "DocumentPageVH", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class PickedFileReviewVH extends RecyclerView.ViewHolder {
        private final HkRvItemFormFileReviewBinding itemBinding;
        final /* synthetic */ FormFilesReviewFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PickedFileReviewVH(FormFilesReviewFragment formFilesReviewFragment, HkRvItemFormFileReviewBinding itemBinding) {
            super(itemBinding.getRoot());
            Intrinsics.checkNotNullParameter(itemBinding, "itemBinding");
            this.this$0 = formFilesReviewFragment;
            this.itemBinding = itemBinding;
        }

        /* compiled from: FormFilesReviewFragment.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFilesReviewFragment$PickedFileReviewVH$DocumentPageVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemBinding", "Lco/hyperverge/hyperkyc/databinding/HkRvItemFormFileReviewDocumentBinding;", "(Lco/hyperverge/hyperkyc/ui/form/FormFilesReviewFragment$PickedFileReviewVH;Lco/hyperverge/hyperkyc/databinding/HkRvItemFormFileReviewDocumentBinding;)V", "bind", "", "pickedFile", "Lco/hyperverge/hyperkyc/ui/form/models/PickedFile;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public final class DocumentPageVH extends RecyclerView.ViewHolder {
            private final HkRvItemFormFileReviewDocumentBinding itemBinding;
            final /* synthetic */ PickedFileReviewVH this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public DocumentPageVH(PickedFileReviewVH pickedFileReviewVH, HkRvItemFormFileReviewDocumentBinding itemBinding) {
                super(itemBinding.getRoot());
                Intrinsics.checkNotNullParameter(itemBinding, "itemBinding");
                this.this$0 = pickedFileReviewVH;
                this.itemBinding = itemBinding;
            }

            /* JADX WARN: Code restructure failed: missing block: B:54:0x0142, code lost:
            
                if (r0 == null) goto L55;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void bind(PickedFile pickedFile) {
                String canonicalName;
                Object m1202constructorimpl;
                String canonicalName2;
                String className;
                String className2;
                Intrinsics.checkNotNullParameter(pickedFile, "pickedFile");
                HyperLogger.Level level = HyperLogger.Level.DEBUG;
                HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                String str = "N/A";
                if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls = getClass();
                    canonicalName = cls != null ? cls.getCanonicalName() : null;
                    if (canonicalName == null) {
                        canonicalName = "N/A";
                    }
                }
                Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
                if (matcher.find()) {
                    canonicalName = matcher.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
                }
                if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    canonicalName = canonicalName.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb.append(canonicalName);
                sb.append(" - ");
                String str2 = "bind() called with: pickedFile = " + pickedFile;
                if (str2 == null) {
                    str2 = "null ";
                }
                sb.append(str2);
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        m1202constructorimpl = "";
                    }
                    String packageName = (String) m1202constructorimpl;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                            if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls2 = getClass();
                                canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            }
                            str = canonicalName2;
                            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                            if (matcher2.find()) {
                                str = matcher2.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                            }
                            if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str = str.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb2 = new StringBuilder();
                            String str3 = "bind() called with: pickedFile = " + pickedFile;
                            if (str3 == null) {
                                str3 = "null ";
                            }
                            sb2.append(str3);
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(3, str, sb2.toString());
                        }
                    }
                }
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0.this$0), null, null, new FormFilesReviewFragment$PickedFileReviewVH$DocumentPageVH$bind$2$1(pickedFile, this, this.itemBinding, null), 3, null);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:65:0x0142, code lost:
        
            if (r0 == null) goto L55;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void bind(PickedFile pickedFile) {
            String canonicalName;
            Object m1202constructorimpl;
            String canonicalName2;
            String className;
            String className2;
            Intrinsics.checkNotNullParameter(pickedFile, "pickedFile");
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls = getClass();
                canonicalName = cls != null ? cls.getCanonicalName() : null;
                if (canonicalName == null) {
                    canonicalName = "N/A";
                }
            }
            Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
            if (matcher.find()) {
                canonicalName = matcher.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
            }
            if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                canonicalName = canonicalName.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(canonicalName);
            sb.append(" - ");
            String str2 = "bind() called with: pickedFile = " + pickedFile;
            if (str2 == null) {
                str2 = "null ";
            }
            sb.append(str2);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } catch (Throwable th) {
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls2 = getClass();
                            canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        }
                        str = canonicalName2;
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                        if (matcher2.find()) {
                            str = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                        }
                        if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str = str.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str3 = "bind() called with: pickedFile = " + pickedFile;
                        if (str3 == null) {
                            str3 = "null ";
                        }
                        sb2.append(str3);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str, sb2.toString());
                    }
                }
            }
            HkRvItemFormFileReviewBinding hkRvItemFormFileReviewBinding = this.itemBinding;
            FormFilesReviewFragment formFilesReviewFragment = this.this$0;
            formFilesReviewFragment.updateUI$hyperkyc_release();
            String type = pickedFile.getType();
            if (Intrinsics.areEqual(type, "images")) {
                SubsamplingScaleImageView ivImage = hkRvItemFormFileReviewBinding.ivImage;
                Intrinsics.checkNotNullExpressionValue(ivImage, "ivImage");
                ivImage.setVisibility(0);
                RecyclerView rvDocuments = hkRvItemFormFileReviewBinding.rvDocuments;
                Intrinsics.checkNotNullExpressionValue(rvDocuments, "rvDocuments");
                rvDocuments.setVisibility(8);
                SubsamplingScaleImageView subsamplingScaleImageView = hkRvItemFormFileReviewBinding.ivImage;
                Uri uri = pickedFile.getUri();
                Intrinsics.checkNotNull(uri);
                subsamplingScaleImageView.setImage(ImageSource.uri(uri));
            } else if (Intrinsics.areEqual(type, "documents")) {
                RecyclerView bind$lambda$3$lambda$2 = hkRvItemFormFileReviewBinding.rvDocuments;
                Intrinsics.checkNotNullExpressionValue(bind$lambda$3$lambda$2, "bind$lambda$3$lambda$2");
                bind$lambda$3$lambda$2.setVisibility(0);
                bind$lambda$3$lambda$2.setLayoutManager(new LinearLayoutManager(bind$lambda$3$lambda$2.getContext(), 1, false));
                SimpleRvAdapter simpleRvAdapter = new SimpleRvAdapter(new Function1<Integer, Integer>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$PickedFileReviewVH$bind$2$1$1
                    public final Integer invoke(int i) {
                        return Integer.valueOf(R.layout.hk_rv_item_form_file_review_document);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                        return invoke(num.intValue());
                    }
                }, new Function2<View, Integer, DocumentPageVH>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$PickedFileReviewVH$bind$2$1$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(2);
                    }

                    public final FormFilesReviewFragment.PickedFileReviewVH.DocumentPageVH invoke(View v, int i) {
                        Intrinsics.checkNotNullParameter(v, "v");
                        FormFilesReviewFragment.PickedFileReviewVH pickedFileReviewVH = FormFilesReviewFragment.PickedFileReviewVH.this;
                        HkRvItemFormFileReviewDocumentBinding bind = HkRvItemFormFileReviewDocumentBinding.bind(v);
                        Intrinsics.checkNotNullExpressionValue(bind, "bind(v)");
                        return new FormFilesReviewFragment.PickedFileReviewVH.DocumentPageVH(pickedFileReviewVH, bind);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ FormFilesReviewFragment.PickedFileReviewVH.DocumentPageVH invoke(View view, Integer num) {
                        return invoke(view, num.intValue());
                    }
                }, new Function2<DocumentPageVH, PickedFile, Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$PickedFileReviewVH$bind$2$1$3
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FormFilesReviewFragment.PickedFileReviewVH.DocumentPageVH h, PickedFile item) {
                        Intrinsics.checkNotNullParameter(h, "h");
                        Intrinsics.checkNotNullParameter(item, "item");
                        h.bind(item);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(FormFilesReviewFragment.PickedFileReviewVH.DocumentPageVH documentPageVH, PickedFile pickedFile2) {
                        invoke2(documentPageVH, pickedFile2);
                        return Unit.INSTANCE;
                    }
                }, new Function2<PickedFile, PickedFile, Boolean>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$PickedFileReviewVH$bind$2$1$4
                    @Override // kotlin.jvm.functions.Function2
                    public final Boolean invoke(PickedFile old, PickedFile pickedFile2) {
                        Intrinsics.checkNotNullParameter(old, "old");
                        Intrinsics.checkNotNullParameter(pickedFile2, "new");
                        return Boolean.valueOf(old.hashCode() == pickedFile2.hashCode());
                    }
                }, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null);
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(formFilesReviewFragment), null, null, new FormFilesReviewFragment$PickedFileReviewVH$bind$2$1$5$1(pickedFile, simpleRvAdapter, null), 3, null);
                bind$lambda$3$lambda$2.setAdapter(simpleRvAdapter);
            } else {
                throw new NotImplementedError("An operation is not implemented: " + ("Preview for " + pickedFile.getType() + " are not supported in this version"));
            }
            if (pickedFile.isError()) {
                hkRvItemFormFileReviewBinding.ivImage.setImage(ImageSource.resource(R.drawable.hk_ic_file_error));
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0148, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0158, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x015c, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x016b, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x016d, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x017a, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0180, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0183, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x018a, code lost:
    
        r0 = new java.lang.StringBuilder();
        r3 = "onCreateView() called with: inflater = " + r18 + ", container = " + r19 + ", savedInstanceState = " + r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01aa, code lost:
    
        if (r3 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01ac, code lost:
    
        r3 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01ae, code lost:
    
        r0.append(r3);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x015b, code lost:
    
        r8 = r0;
     */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str3 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str4 = "onCreateView() called with: inflater = " + inflater + ", container = " + container + ", savedInstanceState = " + savedInstanceState;
        if (str4 == null) {
            str4 = "null ";
        }
        sb.append(str4);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                        str = null;
                    } else {
                        str = null;
                        str2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls2 = getClass();
                    str2 = cls2 != null ? cls2.getCanonicalName() : str;
                }
            }
        }
        return inflater.inflate(R.layout.hk_fragment_form_files_review, container, false);
    }

    private final PickedFile getVisiblePickedFile() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("getVisiblePickedFile() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "getVisiblePickedFile() called ");
                }
            }
        }
        int visiblePos = getVisiblePos();
        List<PickedFile> currentList = this.pickedFilesRvAdapter.getCurrentList();
        Intrinsics.checkNotNullExpressionValue(currentList, "pickedFilesRvAdapter.currentList");
        return (PickedFile) CollectionsKt.getOrNull(currentList, visiblePos);
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x0120, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void updateUI$hyperkyc_release() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String str;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str2 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        String str3 = "";
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("updateUI() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str2 = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher2.find()) {
                        str2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str2, "updateUI() called ");
                }
            }
        }
        int visiblePos = getVisiblePos();
        if (visiblePos < 0) {
            return;
        }
        PickedFile visiblePickedFile = getVisiblePickedFile();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(visiblePos + 1);
        sb2.append('/');
        sb2.append(this.pickedFilesRvAdapter.getItemCount());
        if (visiblePickedFile != null) {
            str3 = " &#8226; " + visiblePickedFile.getSizeLabel();
        }
        sb2.append(str3);
        String sb3 = sb2.toString();
        HkFragmentFormFilesReviewBinding binding = getBinding();
        MaterialTextView materialTextView = binding.tvTitle;
        if (visiblePickedFile == null || (str = visiblePickedFile.getName()) == null) {
            str = "File error";
        }
        materialTextView.setText(str);
        MaterialTextView materialTextView2 = binding.tvSubtitle;
        Spanned fromHtml = HtmlCompat.fromHtml(sb3, 0, null, null);
        Intrinsics.checkNotNullExpressionValue(fromHtml, "fromHtml(this, flags, imageGetter, tagHandler)");
        materialTextView2.setText(fromHtml);
        binding.tvError.setText(visiblePickedFile != null ? visiblePickedFile.getErrorMsg() : null);
    }
}
