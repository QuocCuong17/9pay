package co.hyperverge.hyperkyc.ui.form;

import android.app.Application;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.databinding.HkFragmentBsFormFileBinding;
import co.hyperverge.hyperkyc.databinding.HkRvItemFileTypePickerBsBinding;
import co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegate;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegateKt;
import co.hyperverge.hyperkyc.ui.form.FormFileBSDFragment;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.List;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: FormFileBSDFragment.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0001 B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ&\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u001a\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFileBSDFragment;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "componentId", "", "title", "supportedFiles", "", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SupportedFile;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "binding", "Lco/hyperverge/hyperkyc/databinding/HkFragmentBsFormFileBinding;", "getBinding", "()Lco/hyperverge/hyperkyc/databinding/HkFragmentBsFormFileBinding;", "binding$delegate", "Lco/hyperverge/hyperkyc/ui/custom/delegates/FragmentViewBindingDelegate;", "formVM", "Lco/hyperverge/hyperkyc/ui/form/FormVM;", "getFormVM", "()Lco/hyperverge/hyperkyc/ui/form/FormVM;", "formVM$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", ViewHierarchyConstants.VIEW_KEY, "FileTypeVH", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormFileBSDFragment extends BottomSheetDialogFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(FormFileBSDFragment.class, "binding", "getBinding()Lco/hyperverge/hyperkyc/databinding/HkFragmentBsFormFileBinding;", 0))};

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final FragmentViewBindingDelegate binding;
    private final String componentId;

    /* renamed from: formVM$delegate, reason: from kotlin metadata */
    private final Lazy formVM;
    private final List<WorkflowModule.Properties.Section.Component.SupportedFile> supportedFiles;
    private final String title;

    public /* synthetic */ FormFileBSDFragment(String str, String str2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, list);
    }

    public FormFileBSDFragment(String componentId, String str, List<WorkflowModule.Properties.Section.Component.SupportedFile> supportedFiles) {
        Intrinsics.checkNotNullParameter(componentId, "componentId");
        Intrinsics.checkNotNullParameter(supportedFiles, "supportedFiles");
        this.componentId = componentId;
        this.title = str;
        this.supportedFiles = supportedFiles;
        final FormFileBSDFragment formFileBSDFragment = this;
        this.binding = FragmentViewBindingDelegateKt.viewBinding(formFileBSDFragment, FormFileBSDFragment$binding$2.INSTANCE);
        this.formVM = FragmentViewModelLazyKt.createViewModelLazy(formFileBSDFragment, Reflection.getOrCreateKotlinClass(FormVM.class), new Function0<ViewModelStore>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFileBSDFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFileBSDFragment$special$$inlined$activityViewModels$default$2
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
    }

    private final HkFragmentBsFormFileBinding getBinding() {
        return (HkFragmentBsFormFileBinding) this.binding.getValue2((Fragment) this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FormVM getFormVM() {
        return (FormVM) this.formVM.getValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0143, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0153, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0157, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0166, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0168, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0173, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0179, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x017c, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0183, code lost:
    
        r0 = new java.lang.StringBuilder();
        r2 = "onViewCreated() called with: view = " + r18 + ", savedInstanceState = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x019d, code lost:
    
        if (r2 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x019f, code lost:
    
        r2 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01a1, code lost:
    
        r0.append(r2);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0156, code lost:
    
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
        behavior.setDraggable(true);
        behavior.setFitToContents(true);
        behavior.disableShapeAnimations();
        HkFragmentBsFormFileBinding binding = getBinding();
        String str5 = this.title;
        if (str5 != null) {
            binding.tvTitle.setText(str5);
        }
        SimpleRvAdapter simpleRvAdapter = new SimpleRvAdapter(new Function1<Integer, Integer>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFileBSDFragment$onViewCreated$3$adapter$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Integer invoke(int i) {
                return Integer.valueOf(R.layout.hk_rv_item_file_type_picker_bs);
            }
        }, new Function2<View, Integer, FileTypeVH>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFileBSDFragment$onViewCreated$3$adapter$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ FormFileBSDFragment.FileTypeVH invoke(View view2, Integer num) {
                return invoke(view2, num.intValue());
            }

            public final FormFileBSDFragment.FileTypeVH invoke(View v, int i) {
                Intrinsics.checkNotNullParameter(v, "v");
                HkRvItemFileTypePickerBsBinding bind = HkRvItemFileTypePickerBsBinding.bind(v);
                Intrinsics.checkNotNullExpressionValue(bind, "bind(v)");
                return new FormFileBSDFragment.FileTypeVH(bind);
            }
        }, new Function2<FileTypeVH, WorkflowModule.Properties.Section.Component.SupportedFile, Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFileBSDFragment$onViewCreated$3$adapter$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(FormFileBSDFragment.FileTypeVH fileTypeVH, WorkflowModule.Properties.Section.Component.SupportedFile supportedFile) {
                invoke2(fileTypeVH, supportedFile);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(FormFileBSDFragment.FileTypeVH h, final WorkflowModule.Properties.Section.Component.SupportedFile item) {
                Intrinsics.checkNotNullParameter(h, "h");
                Intrinsics.checkNotNullParameter(item, "item");
                final FormFileBSDFragment formFileBSDFragment = FormFileBSDFragment.this;
                h.bind(item, new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFileBSDFragment$onViewCreated$3$adapter$3.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        FormVM formVM;
                        String str6;
                        formVM = FormFileBSDFragment.this.getFormVM();
                        str6 = FormFileBSDFragment.this.componentId;
                        formVM.performFileTypePick(str6, item);
                        FormFileBSDFragment.this.dismissAllowingStateLoss();
                    }
                });
            }
        }, new Function2<WorkflowModule.Properties.Section.Component.SupportedFile, WorkflowModule.Properties.Section.Component.SupportedFile, Boolean>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFileBSDFragment$onViewCreated$3$adapter$4
            @Override // kotlin.jvm.functions.Function2
            public final Boolean invoke(WorkflowModule.Properties.Section.Component.SupportedFile old, WorkflowModule.Properties.Section.Component.SupportedFile supportedFile) {
                Intrinsics.checkNotNullParameter(old, "old");
                Intrinsics.checkNotNullParameter(supportedFile, "new");
                return Boolean.valueOf(Intrinsics.areEqual(old, supportedFile));
            }
        }, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null);
        binding.rvFileTypePicker.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvFileTypePicker.setAdapter(simpleRvAdapter);
        simpleRvAdapter.submitList(this.supportedFiles);
    }

    /* compiled from: FormFileBSDFragment.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFileBSDFragment$FileTypeVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lco/hyperverge/hyperkyc/databinding/HkRvItemFileTypePickerBsBinding;", "(Lco/hyperverge/hyperkyc/databinding/HkRvItemFileTypePickerBsBinding;)V", "getBinding", "()Lco/hyperverge/hyperkyc/databinding/HkRvItemFileTypePickerBsBinding;", "bind", "", "supportedFile", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SupportedFile;", "onClick", "Lkotlin/Function0;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class FileTypeVH extends RecyclerView.ViewHolder {
        private final HkRvItemFileTypePickerBsBinding binding;

        public final HkRvItemFileTypePickerBsBinding getBinding() {
            return this.binding;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FileTypeVH(HkRvItemFileTypePickerBsBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$2$lambda$1(Function0 onClick, View view) {
            Intrinsics.checkNotNullParameter(onClick, "$onClick");
            onClick.invoke();
        }

        public final void bind(WorkflowModule.Properties.Section.Component.SupportedFile supportedFile, final Function0<Unit> onClick) {
            String canonicalName;
            Object m1202constructorimpl;
            String className;
            String substringAfterLast$default;
            int i;
            String className2;
            Intrinsics.checkNotNullParameter(supportedFile, "supportedFile");
            Intrinsics.checkNotNullParameter(onClick, "onClick");
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
            String str2 = "bind() called with: supportedFile = " + supportedFile + ", onClick = " + onClick;
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
                        StringBuilder sb2 = new StringBuilder();
                        String str3 = "bind() called with: supportedFile = " + supportedFile + ", onClick = " + onClick;
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
            HkRvItemFileTypePickerBsBinding hkRvItemFileTypePickerBsBinding = this.binding;
            AppCompatImageView appCompatImageView = hkRvItemFileTypePickerBsBinding.ivIcon;
            String type = supportedFile.getType();
            if (Intrinsics.areEqual(type, "images")) {
                i = R.drawable.hk_ic_photo_18;
            } else {
                i = Intrinsics.areEqual(type, "documents") ? R.drawable.hk_ic_document_18 : R.drawable.hk_ic_document_18;
            }
            appCompatImageView.setImageResource(i);
            hkRvItemFileTypePickerBsBinding.ivIcon.setImageAlpha(supportedFile.getEnabled() ? 255 : 50);
            hkRvItemFileTypePickerBsBinding.tvTitle.setEnabled(supportedFile.getEnabled());
            hkRvItemFileTypePickerBsBinding.tvTitle.setText(supportedFile.getTitle());
            hkRvItemFileTypePickerBsBinding.getRoot().setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFileBSDFragment$FileTypeVH$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FormFileBSDFragment.FileTypeVH.bind$lambda$2$lambda$1(Function0.this, view);
                }
            });
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
        return inflater.inflate(R.layout.hk_fragment_bs_form_file, container, false);
    }
}
