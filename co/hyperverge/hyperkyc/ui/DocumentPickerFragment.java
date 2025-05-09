package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
import co.hyperverge.hyperkyc.data.models.KycDocument;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.databinding.HkFragmentDocumentPickerBinding;
import co.hyperverge.hyperkyc.databinding.HkRvItemDocBinding;
import co.hyperverge.hyperkyc.ui.DocumentPickerFragment;
import co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegate;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegateKt;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.DrawableExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.UIExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: DocumentPickerFragment.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 92\u00020\u0001:\u00029:B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020'H\u0002J\b\u0010-\u001a\u00020.H\u0016J\u0010\u0010/\u001a\u00020.2\u0006\u00100\u001a\u000201H\u0016J\u001a\u00102\u001a\u00020.2\u0006\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u000101H\u0016J\u001c\u00106\u001a\u00020.2\b\u00107\u001a\u0004\u0018\u00010\u000b2\b\u00108\u001a\u0004\u0018\u00010\u000bH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R*\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nj\n\u0012\u0004\u0012\u00020\u000b\u0018\u0001`\f8BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u000b\u0012\b\u0012\u00060\u0017R\u00020\u00000\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010\u0019\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u000b8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u0014\u001a\u0004\b\"\u0010#R \u0010%\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020'0&8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)¨\u0006;"}, d2 = {"Lco/hyperverge/hyperkyc/ui/DocumentPickerFragment;", "Lco/hyperverge/hyperkyc/ui/BaseFragment;", "()V", "binding", "Lco/hyperverge/hyperkyc/databinding/HkFragmentDocumentPickerBinding;", "getBinding", "()Lco/hyperverge/hyperkyc/databinding/HkFragmentDocumentPickerBinding;", "binding$delegate", "Lco/hyperverge/hyperkyc/ui/custom/delegates/FragmentViewBindingDelegate;", "documents", "Ljava/util/ArrayList;", "Lco/hyperverge/hyperkyc/data/models/KycDocument;", "Lkotlin/collections/ArrayList;", "getDocuments", "()Ljava/util/ArrayList;", "mainVM", "Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "getMainVM", "()Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "mainVM$delegate", "Lkotlin/Lazy;", "rvDocumentsAdapter", "Lco/hyperverge/hyperkyc/ui/custom/SimpleRvAdapter;", "Lco/hyperverge/hyperkyc/ui/DocumentPickerFragment$DocumentVH;", "<set-?>", "selectedDocument", "getSelectedDocument", "()Lco/hyperverge/hyperkyc/data/models/KycDocument;", "setSelectedDocument", "(Lco/hyperverge/hyperkyc/data/models/KycDocument;)V", "selectedDocument$delegate", "Lkotlin/properties/ReadWriteProperty;", "showBackButton", "", "getShowBackButton", "()Z", "showBackButton$delegate", "textConfigs", "", "", "getTextConfigs", "()Ljava/util/Map;", "getFontIdentifier", "", "fontFileName", "initViews", "", "onSaveInstanceState", "outState", "Landroid/os/Bundle;", "onViewCreated", ViewHierarchyConstants.VIEW_KEY, "Landroid/view/View;", "savedInstanceState", "updateSelectedDocument", "old", "new", "Companion", "DocumentVH", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentPickerFragment extends BaseFragment {
    public static final /* synthetic */ String ARG_KEY_DOCUMENTS = "documents";
    public static final /* synthetic */ String ARG_KEY_TEXT_CONFIGS = "textConfigs";
    public static final /* synthetic */ String ARG_SHOW_BACK_BUTTON = "showBackButton";

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final FragmentViewBindingDelegate binding;
    private ArrayList<KycDocument> documents;

    /* renamed from: mainVM$delegate, reason: from kotlin metadata */
    private final Lazy mainVM;
    private final SimpleRvAdapter<KycDocument, DocumentVH> rvDocumentsAdapter;

    /* renamed from: selectedDocument$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty selectedDocument;

    /* renamed from: showBackButton$delegate, reason: from kotlin metadata */
    private final Lazy showBackButton;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(DocumentPickerFragment.class, "binding", "getBinding()Lco/hyperverge/hyperkyc/databinding/HkFragmentDocumentPickerBinding;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DocumentPickerFragment.class, "selectedDocument", "getSelectedDocument()Lco/hyperverge/hyperkyc/data/models/KycDocument;", 0))};

    public DocumentPickerFragment() {
        super(R.layout.hk_fragment_document_picker);
        final DocumentPickerFragment documentPickerFragment = this;
        this.mainVM = FragmentViewModelLazyKt.createViewModelLazy(documentPickerFragment, Reflection.getOrCreateKotlinClass(MainVM.class), new Function0<ViewModelStore>() { // from class: co.hyperverge.hyperkyc.ui.DocumentPickerFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: co.hyperverge.hyperkyc.ui.DocumentPickerFragment$special$$inlined$activityViewModels$default$2
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
        this.binding = FragmentViewBindingDelegateKt.viewBinding(documentPickerFragment, DocumentPickerFragment$binding$2.INSTANCE);
        this.showBackButton = LazyKt.lazy(new Function0<Boolean>() { // from class: co.hyperverge.hyperkyc.ui.DocumentPickerFragment$showBackButton$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                Bundle arguments = DocumentPickerFragment.this.getArguments();
                Boolean valueOf = arguments != null ? Boolean.valueOf(arguments.getBoolean("showBackButton")) : null;
                Intrinsics.checkNotNull(valueOf);
                return valueOf;
            }
        });
        this.rvDocumentsAdapter = new SimpleRvAdapter<>(new Function1<Integer, Integer>() { // from class: co.hyperverge.hyperkyc.ui.DocumentPickerFragment$rvDocumentsAdapter$1
            public final Integer invoke(int i) {
                return Integer.valueOf(R.layout.hk_rv_item_doc);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                return invoke(num.intValue());
            }
        }, new Function2<View, Integer, DocumentVH>() { // from class: co.hyperverge.hyperkyc.ui.DocumentPickerFragment$rvDocumentsAdapter$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            public final DocumentPickerFragment.DocumentVH invoke(View view, int i) {
                Intrinsics.checkNotNullParameter(view, "view");
                DocumentPickerFragment documentPickerFragment2 = DocumentPickerFragment.this;
                HkRvItemDocBinding bind = HkRvItemDocBinding.bind(view);
                Intrinsics.checkNotNullExpressionValue(bind, "bind(view)");
                return new DocumentPickerFragment.DocumentVH(documentPickerFragment2, bind);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ DocumentPickerFragment.DocumentVH invoke(View view, Integer num) {
                return invoke(view, num.intValue());
            }
        }, new Function2<DocumentVH, KycDocument, Unit>() { // from class: co.hyperverge.hyperkyc.ui.DocumentPickerFragment$rvDocumentsAdapter$3
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(DocumentPickerFragment.DocumentVH documentVH, KycDocument kycDocument) {
                invoke2(documentVH, kycDocument);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DocumentPickerFragment.DocumentVH h, KycDocument item) {
                Intrinsics.checkNotNullParameter(h, "h");
                Intrinsics.checkNotNullParameter(item, "item");
                h.bind(item);
            }
        }, new Function2<KycDocument, KycDocument, Boolean>() { // from class: co.hyperverge.hyperkyc.ui.DocumentPickerFragment$rvDocumentsAdapter$4
            @Override // kotlin.jvm.functions.Function2
            public final Boolean invoke(KycDocument old, KycDocument kycDocument) {
                Intrinsics.checkNotNullParameter(old, "old");
                Intrinsics.checkNotNullParameter(kycDocument, "new");
                return Boolean.valueOf(Intrinsics.areEqual(old.getId(), kycDocument.getId()));
            }
        }, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null);
        Delegates delegates = Delegates.INSTANCE;
        final Object obj = null;
        this.selectedDocument = new ObservableProperty<KycDocument>(obj) { // from class: co.hyperverge.hyperkyc.ui.DocumentPickerFragment$special$$inlined$observable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, KycDocument oldValue, KycDocument newValue) {
                MainVM mainVM;
                MainVM mainVM2;
                Intrinsics.checkNotNullParameter(property, "property");
                KycDocument kycDocument = newValue;
                KycDocument kycDocument2 = oldValue;
                if (Intrinsics.areEqual(kycDocument2 != null ? kycDocument2.getId() : null, kycDocument != null ? kycDocument.getId() : null)) {
                    return;
                }
                this.updateSelectedDocument(kycDocument2, kycDocument);
                if (kycDocument != null) {
                    AnalyticsLogger analyticsLogger = AnalyticsLogger.INSTANCE;
                    String id2 = kycDocument.getId();
                    mainVM2 = this.getMainVM();
                    AnalyticsLogger.logDocumentPickEvent$hyperkyc_release$default(analyticsLogger, id2, mainVM2.getCurrentModuleId$hyperkyc_release(), null, 4, null);
                }
                mainVM = this.getMainVM();
                mainVM.flowForward();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MainVM getMainVM() {
        return (MainVM) this.mainVM.getValue();
    }

    private final HkFragmentDocumentPickerBinding getBinding() {
        return (HkFragmentDocumentPickerBinding) this.binding.getValue2((Fragment) this, $$delegatedProperties[0]);
    }

    private final ArrayList<KycDocument> getDocuments() {
        Parcelable[] parcelableArray;
        Bundle arguments = getArguments();
        List mutableList = (arguments == null || (parcelableArray = arguments.getParcelableArray("documents")) == null) ? null : ArraysKt.toMutableList(parcelableArray);
        if (mutableList instanceof ArrayList) {
            return (ArrayList) mutableList;
        }
        return null;
    }

    private final Map<String, String> getTextConfigs() {
        Bundle arguments = getArguments();
        Object obj = arguments != null ? arguments.get("textConfigs") : null;
        Map map = obj instanceof Map ? (Map) obj : null;
        Map<String, String> map2 = map != null ? MapsKt.toMap(map) : null;
        Intrinsics.checkNotNull(map2);
        return map2;
    }

    private final boolean getShowBackButton() {
        return ((Boolean) this.showBackButton.getValue()).booleanValue();
    }

    private final KycDocument getSelectedDocument() {
        return (KycDocument) this.selectedDocument.getValue(this, $$delegatedProperties[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSelectedDocument(KycDocument kycDocument) {
        this.selectedDocument.setValue(this, $$delegatedProperties[1], kycDocument);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
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
        String str2 = "onSaveInstanceState() called with: outState = " + outState;
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
                    String str3 = "onSaveInstanceState() called with: outState = " + outState;
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
        ArrayList<KycDocument> documents = getDocuments();
        if (documents != null) {
            outState.putParcelableArrayList("documents", new ArrayList<>(documents));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$10$lambda$7(DocumentPickerFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed$hyperkyc_release();
    }

    private static final void updateSelectedDocument$notifyChange(KycDocument kycDocument, DocumentPickerFragment documentPickerFragment, boolean z) {
        if (kycDocument != null) {
            kycDocument.setSelected$hyperkyc_release(z);
        }
        int indexOf = documentPickerFragment.rvDocumentsAdapter.getCurrentList().indexOf(kycDocument);
        boolean z2 = false;
        if (-1 <= indexOf && indexOf <= documentPickerFragment.rvDocumentsAdapter.getItemCount()) {
            z2 = true;
        }
        if (z2) {
            documentPickerFragment.rvDocumentsAdapter.notifyItemChanged(indexOf);
        }
    }

    /* compiled from: DocumentPickerFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lco/hyperverge/hyperkyc/ui/DocumentPickerFragment$DocumentVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemBinding", "Lco/hyperverge/hyperkyc/databinding/HkRvItemDocBinding;", "(Lco/hyperverge/hyperkyc/ui/DocumentPickerFragment;Lco/hyperverge/hyperkyc/databinding/HkRvItemDocBinding;)V", "bind", "", WorkflowModule.TYPE_DOCUMENT, "Lco/hyperverge/hyperkyc/data/models/KycDocument;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class DocumentVH extends RecyclerView.ViewHolder {
        private final HkRvItemDocBinding itemBinding;
        final /* synthetic */ DocumentPickerFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DocumentVH(DocumentPickerFragment documentPickerFragment, HkRvItemDocBinding itemBinding) {
            super(itemBinding.getRoot());
            Intrinsics.checkNotNullParameter(itemBinding, "itemBinding");
            this.this$0 = documentPickerFragment;
            this.itemBinding = itemBinding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$3$lambda$2(DocumentPickerFragment this$0, KycDocument document, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(document, "$document");
            this$0.setSelectedDocument(document);
        }

        /* JADX WARN: Code restructure failed: missing block: B:37:0x021d, code lost:
        
            if (r9 != null) goto L91;
         */
        /* JADX WARN: Removed duplicated region for block: B:23:0x01bd  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x01c9  */
        /* JADX WARN: Removed duplicated region for block: B:96:0x0375  */
        /* JADX WARN: Removed duplicated region for block: B:97:0x01c2  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void bind(final KycDocument document) {
            String canonicalName;
            Object m1202constructorimpl;
            CharSequence charSequence;
            String str;
            String canonicalName2;
            String className;
            Drawable drawable;
            Drawable drawable2;
            String str2;
            Object m1202constructorimpl2;
            String str3;
            String className2;
            String className3;
            String className4;
            DocumentVH documentVH = this;
            Intrinsics.checkNotNullParameter(document, "document");
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
            String str4 = "bind() called with: document = " + document;
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
                    charSequence = "co.hyperverge";
                    str = "N/A";
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls2 = getClass();
                            canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            if (canonicalName2 == null) {
                                canonicalName2 = str;
                            }
                        }
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                        if (matcher2.find()) {
                            canonicalName2 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                        }
                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName2 = canonicalName2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str5 = "bind() called with: document = " + document;
                        if (str5 == null) {
                            str5 = "null ";
                        }
                        sb2.append(str5);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    }
                    Resources resources = documentVH.this$0.getResources();
                    int i = R.drawable.hs_ic_baseline_chevron_right_18;
                    FragmentActivity activity = documentVH.this$0.getActivity();
                    drawable = ResourcesCompat.getDrawable(resources, i, activity == null ? activity.getTheme() : null);
                    if (drawable == null) {
                        String pickerIconColor = HyperSnapBridgeKt.getUiConfigUtil().getConfig().getColors().getPickerIconColor();
                        if (UIExtsKt.isValidHexColor(pickerIconColor)) {
                            DrawableExtsKt.setIconColor(drawable, Color.parseColor(pickerIconColor));
                            drawable2 = drawable;
                            HkRvItemDocBinding hkRvItemDocBinding = documentVH.itemBinding;
                            final DocumentPickerFragment documentPickerFragment = documentVH.this$0;
                            hkRvItemDocBinding.btnDocument.setText(document.getName());
                            HyperSnapBridgeKt.getUiConfigUtil().customisePickerButton(hkRvItemDocBinding.btnDocument);
                            hkRvItemDocBinding.btnDocument.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, drawable2, (Drawable) null);
                            hkRvItemDocBinding.btnDocument.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.DocumentPickerFragment$DocumentVH$$ExternalSyntheticLambda0
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view) {
                                    DocumentPickerFragment.DocumentVH.bind$lambda$3$lambda$2(DocumentPickerFragment.this, document, view);
                                }
                            });
                        }
                        HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb3 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                            drawable2 = drawable;
                        } else {
                            drawable2 = drawable;
                            str2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        Class<?> cls3 = getClass();
                        String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                        str2 = canonicalName3 == null ? str : canonicalName3;
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                        if (matcher3.find()) {
                            str2 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                        }
                        if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str2 = str2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb3.append(str2);
                        sb3.append(" - ");
                        String str6 = "updateColor() invalid color value for animationPrimaryColor : " + pickerIconColor;
                        if (str6 == null) {
                            str6 = "null ";
                        }
                        sb3.append(str6);
                        sb3.append(' ');
                        sb3.append("");
                        companion4.log(level2, sb3.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion5 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th2) {
                            Result.Companion companion6 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                            m1202constructorimpl2 = "";
                        }
                        String packageName2 = (String) m1202constructorimpl2;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null || (str3 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls4 = getClass();
                                    String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                                    str3 = canonicalName4 == null ? str : canonicalName4;
                                }
                                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                                if (matcher4.find()) {
                                    str3 = matcher4.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                                }
                                if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str3 = str3.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb4 = new StringBuilder();
                                String str7 = "updateColor() invalid color value for animationPrimaryColor : " + pickerIconColor;
                                if (str7 == null) {
                                    str7 = "null ";
                                }
                                sb4.append(str7);
                                sb4.append(' ');
                                sb4.append("");
                                Log.println(6, str3, sb4.toString());
                            }
                        }
                    } else {
                        drawable2 = drawable;
                    }
                    documentVH = this;
                    HkRvItemDocBinding hkRvItemDocBinding2 = documentVH.itemBinding;
                    final DocumentPickerFragment documentPickerFragment2 = documentVH.this$0;
                    hkRvItemDocBinding2.btnDocument.setText(document.getName());
                    HyperSnapBridgeKt.getUiConfigUtil().customisePickerButton(hkRvItemDocBinding2.btnDocument);
                    hkRvItemDocBinding2.btnDocument.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, drawable2, (Drawable) null);
                    hkRvItemDocBinding2.btnDocument.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.DocumentPickerFragment$DocumentVH$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            DocumentPickerFragment.DocumentVH.bind$lambda$3$lambda$2(DocumentPickerFragment.this, document, view);
                        }
                    });
                }
            }
            charSequence = "co.hyperverge";
            str = "N/A";
            Resources resources2 = documentVH.this$0.getResources();
            int i2 = R.drawable.hs_ic_baseline_chevron_right_18;
            FragmentActivity activity2 = documentVH.this$0.getActivity();
            drawable = ResourcesCompat.getDrawable(resources2, i2, activity2 == null ? activity2.getTheme() : null);
            if (drawable == null) {
            }
            documentVH = this;
            HkRvItemDocBinding hkRvItemDocBinding22 = documentVH.itemBinding;
            final DocumentPickerFragment documentPickerFragment22 = documentVH.this$0;
            hkRvItemDocBinding22.btnDocument.setText(document.getName());
            HyperSnapBridgeKt.getUiConfigUtil().customisePickerButton(hkRvItemDocBinding22.btnDocument);
            hkRvItemDocBinding22.btnDocument.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, drawable2, (Drawable) null);
            hkRvItemDocBinding22.btnDocument.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.DocumentPickerFragment$DocumentVH$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DocumentPickerFragment.DocumentVH.bind$lambda$3$lambda$2(DocumentPickerFragment.this, document, view);
                }
            });
        }
    }

    @Override // co.hyperverge.hyperkyc.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(view, "view");
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
        String str2 = "onViewCreated() called with: view = " + view + ", savedInstanceState = " + savedInstanceState;
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
                    String str3 = "onViewCreated() called with: view = " + view + ", savedInstanceState = " + savedInstanceState;
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(2, str, sb2.toString());
                }
            }
        }
        if (savedInstanceState != null) {
            this.documents = savedInstanceState.getParcelableArrayList("documents");
        }
        super.onViewCreated(view, savedInstanceState);
    }

    /* JADX WARN: Code restructure failed: missing block: B:80:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    @Override // co.hyperverge.hyperkyc.ui.BaseFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void initViews() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
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
        sb.append("initViews() called");
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
                    Log.println(3, str, "initViews() called ");
                }
            }
        }
        HkFragmentDocumentPickerBinding binding = getBinding();
        RecyclerView recyclerView = binding.rvDocuments;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(this.rvDocumentsAdapter);
        RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.setChangeDuration(400L);
        }
        ImageView imageView = binding.hkLayoutTopBar.ivBack;
        Intrinsics.checkNotNullExpressionValue(imageView, "hkLayoutTopBar.ivBack");
        imageView.setVisibility(getShowBackButton() ? 0 : 8);
        binding.hkLayoutTopBar.ivBack.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.DocumentPickerFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DocumentPickerFragment.initViews$lambda$10$lambda$7(DocumentPickerFragment.this, view);
            }
        });
        String str2 = getTextConfigs().get("docPicker_title");
        if (str2 != null) {
            if (str2.length() > 0) {
                binding.tvTitle.setText(UIExtsKt.fromHTML(str2));
            }
        }
        String str3 = getTextConfigs().get("docPicker_desc");
        if (str3 != null) {
            if (str3.length() > 0) {
                binding.tvSubTitle.setText(UIExtsKt.fromHTML(str3));
            }
        }
        LinearLayout linearLayout = binding.includeBranding.llBrandingRoot;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "includeBranding.llBrandingRoot");
        linearLayout.setVisibility(getMainVM().shouldShowBranding() ? 0 : 8);
        HyperSnapBridgeKt.getUiConfigUtil().customiseTitleTextView(binding.tvTitle);
        HyperSnapBridgeKt.getUiConfigUtil().customiseDescriptionTextView(binding.tvSubTitle);
        HyperSnapBridgeKt.getUiConfigUtil().customiseBackButtonIcon(binding.hkLayoutTopBar.ivBack);
        HyperSnapBridgeKt.getUiConfigUtil().customiseClientLogo(binding.hkLayoutTopBar.hkClientLogo);
        HSUIConfig uiConfigData = getMainVM().getUiConfigData();
        loadBackground$hyperkyc_release(uiConfigData != null ? uiConfigData.getBackgroundImageUrl() : null);
        ArrayList<KycDocument> documents = getDocuments();
        if (documents != null) {
            SimpleRvAdapter.updateItems$default(this.rvDocumentsAdapter, documents, null, 2, null);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x01ea, code lost:
    
        if (r8 != null) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x02d9, code lost:
    
        if (r0 != null) goto L123;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x034d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int getFontIdentifier(String fontFileName) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String canonicalName2;
        String className;
        int identifier;
        String str2;
        int i;
        String str3;
        Object m1202constructorimpl2;
        String str4;
        String str5;
        String className2;
        String className3;
        String className4;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
        String str6 = "getFontIdentifier() called with: fontFileName = " + fontFileName;
        if (str6 == null) {
            str6 = "null ";
        }
        sb.append(str6);
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
                str = "N/A";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = str;
                        }
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher2.find()) {
                        canonicalName2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str7 = "getFontIdentifier() called with: fontFileName = " + fontFileName;
                    if (str7 == null) {
                        str7 = "null ";
                    }
                    sb2.append(str7);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                }
                identifier = requireContext().getResources().getIdentifier(fontFileName, "font", requireContext().getPackageName());
                if (identifier == 0) {
                    return identifier;
                }
                HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb3 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                    str2 = "Throwable().stackTrace";
                    i = identifier;
                } else {
                    str2 = "Throwable().stackTrace";
                    i = identifier;
                    str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                }
                Class<?> cls3 = getClass();
                String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                str3 = canonicalName3 == null ? str : canonicalName3;
                Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                if (matcher3.find()) {
                    str3 = matcher3.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                }
                if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str3 = str3.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb3.append(str3);
                sb3.append(" - ");
                String str8 = "Could not find font " + fontFileName + ". Using default font instead";
                if (str8 == null) {
                    str8 = "null ";
                }
                sb3.append(str8);
                sb3.append(' ');
                sb3.append("");
                companion4.log(level2, sb3.toString());
                CoreExtsKt.isRelease();
                try {
                    Result.Companion companion5 = Result.INSTANCE;
                    Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                } catch (Throwable th2) {
                    Result.Companion companion6 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                    m1202constructorimpl2 = "";
                }
                String packageName2 = (String) m1202constructorimpl2;
                if (!CoreExtsKt.isDebug()) {
                    return i;
                }
                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                if (!StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    return i;
                }
                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace4, str2);
                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                    str4 = null;
                } else {
                    str4 = null;
                    str5 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                }
                Class<?> cls4 = getClass();
                String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str4;
                str5 = canonicalName4 == null ? str : canonicalName4;
                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                if (matcher4.find()) {
                    str5 = matcher4.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                }
                if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str5 = str5.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                StringBuilder sb4 = new StringBuilder();
                String str9 = "Could not find font " + fontFileName + ". Using default font instead";
                if (str9 == null) {
                    str9 = "null ";
                }
                sb4.append(str9);
                sb4.append(' ');
                sb4.append("");
                Log.println(6, str5, sb4.toString());
                return i;
            }
        }
        str = "N/A";
        identifier = requireContext().getResources().getIdentifier(fontFileName, "font", requireContext().getPackageName());
        if (identifier == 0) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x013b, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x014b, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x014f, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x015e, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0160, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x016b, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0171, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0174, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x017b, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "updateSelectedDocument() called with: old = " + r18 + ", new = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0195, code lost:
    
        if (r4 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0197, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0199, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x014e, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void updateSelectedDocument(KycDocument old, KycDocument r19) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
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
        String str4 = "updateSelectedDocument() called with: old = " + old + ", new = " + r19;
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
        if (old != null) {
            updateSelectedDocument$notifyChange(old, this, false);
        }
        if (r19 != null) {
            updateSelectedDocument$notifyChange(r19, this, true);
        }
        if (r19 != null) {
            getMainVM().updateDocCaptureUiStates(r19);
        }
    }
}
