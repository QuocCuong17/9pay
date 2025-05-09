package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.widget.AppCompatEditText;
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
import co.hyperverge.hyperkyc.data.models.KycCountry;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.databinding.HkFragmentCountryPickerBinding;
import co.hyperverge.hyperkyc.databinding.HkRvItemCountryBinding;
import co.hyperverge.hyperkyc.ui.CountryPickerFragment;
import co.hyperverge.hyperkyc.ui.CountryPickerFragment$backPressedCallback$2;
import co.hyperverge.hyperkyc.ui.custom.ClickableMotionLayout;
import co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegate;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegateKt;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.PicassoExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.UIExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.model.UIConfig;
import com.facebook.appevents.UserDataStore;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
import kotlin.collections.CollectionsKt;
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

/* compiled from: CountryPickerFragment.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\n\b\u0000\u0018\u0000 K2\u00020\u0001:\u0002KLB\u0005¢\u0006\u0002\u0010\u0002J\b\u00100\u001a\u000201H\u0016J\u0012\u00102\u001a\u0002012\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0010\u00105\u001a\u0002012\u0006\u00106\u001a\u000204H\u0016J\u001a\u00107\u001a\u0002012\u0006\u00108\u001a\u0002092\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0015\u0010:\u001a\u0002012\u0006\u0010;\u001a\u00020<H\u0000¢\u0006\u0002\b=J\u0010\u0010>\u001a\u0002012\u0006\u0010;\u001a\u00020<H\u0002J\u0010\u0010?\u001a\u0002012\u0006\u0010@\u001a\u00020AH\u0002J\u0016\u0010B\u001a\u0002012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110CH\u0002J&\u0010D\u001a\u0002012\b\u0010E\u001a\u0004\u0018\u00010\u00112\b\u0010F\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010G\u001a\u00020&H\u0002J\u0016\u0010%\u001a\u000201*\u00020\n2\b\b\u0002\u0010H\u001a\u00020&H\u0002J\u0014\u0010I\u001a\u000201*\u00020\n2\u0006\u0010J\u001a\u00020&H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR*\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010j\n\u0012\u0004\u0012\u00020\u0011\u0018\u0001`\u00128BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\b\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u0011\u0012\b\u0012\u00060\u001cR\u00020\u00000\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010\u001e\u001a\u0004\u0018\u00010\u00112\b\u0010\u001d\u001a\u0004\u0018\u00010\u00118@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001b\u0010%\u001a\u00020&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\b\u001a\u0004\b'\u0010(R'\u0010*\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020,0+8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b/\u0010\b\u001a\u0004\b-\u0010.¨\u0006M"}, d2 = {"Lco/hyperverge/hyperkyc/ui/CountryPickerFragment;", "Lco/hyperverge/hyperkyc/ui/BaseFragment;", "()V", "backPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "getBackPressedCallback$hyperkyc_release", "()Landroidx/activity/OnBackPressedCallback;", "backPressedCallback$delegate", "Lkotlin/Lazy;", "binding", "Lco/hyperverge/hyperkyc/databinding/HkFragmentCountryPickerBinding;", "getBinding$hyperkyc_release", "()Lco/hyperverge/hyperkyc/databinding/HkFragmentCountryPickerBinding;", "binding$delegate", "Lco/hyperverge/hyperkyc/ui/custom/delegates/FragmentViewBindingDelegate;", "countries", "Ljava/util/ArrayList;", "Lco/hyperverge/hyperkyc/data/models/KycCountry;", "Lkotlin/collections/ArrayList;", "getCountries", "()Ljava/util/ArrayList;", "mainVM", "Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "getMainVM", "()Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "mainVM$delegate", "rvCountryAdapter", "Lco/hyperverge/hyperkyc/ui/custom/SimpleRvAdapter;", "Lco/hyperverge/hyperkyc/ui/CountryPickerFragment$CountryVH;", "<set-?>", "selectedCountry", "getSelectedCountry$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/models/KycCountry;", "setSelectedCountry$hyperkyc_release", "(Lco/hyperverge/hyperkyc/data/models/KycCountry;)V", "selectedCountry$delegate", "Lkotlin/properties/ReadWriteProperty;", "showBackButton", "", "getShowBackButton", "()Z", "showBackButton$delegate", "textConfigs", "", "", "getTextConfigs", "()Ljava/util/Map;", "textConfigs$delegate", "initViews", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "onViewCreated", ViewHierarchyConstants.VIEW_KEY, "Landroid/view/View;", "setPrimaryButtonBackgroundColor", WorkflowModule.Properties.Section.Component.Type.BUTTON, "Lcom/google/android/material/button/MaterialButton;", "setPrimaryButtonBackgroundColor$hyperkyc_release", "setSecondaryButtonFont", "setSecondaryTextViewFont", "textView", "Lcom/google/android/material/textview/MaterialTextView;", "updateCountries", "", "updateSelectedCountry", "old", "new", "submit", "shouldShow", "showClientLogo", "shouldShowLogo", "Companion", "CountryVH", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CountryPickerFragment extends BaseFragment {
    public static final /* synthetic */ String ARG_KEY_COUNTRIES = "countries";
    public static final /* synthetic */ String ARG_KEY_TEXT_CONFIGS = "textConfigs";
    public static final /* synthetic */ String ARG_SHOW_BACK_BUTTON = "showBackButton";

    /* renamed from: backPressedCallback$delegate, reason: from kotlin metadata */
    private final Lazy backPressedCallback;

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final FragmentViewBindingDelegate binding;
    private ArrayList<KycCountry> countries;

    /* renamed from: mainVM$delegate, reason: from kotlin metadata */
    private final Lazy mainVM;
    private final SimpleRvAdapter<KycCountry, CountryVH> rvCountryAdapter;

    /* renamed from: selectedCountry$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty selectedCountry;

    /* renamed from: showBackButton$delegate, reason: from kotlin metadata */
    private final Lazy showBackButton;

    /* renamed from: textConfigs$delegate, reason: from kotlin metadata */
    private final Lazy textConfigs;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(CountryPickerFragment.class, "binding", "getBinding$hyperkyc_release()Lco/hyperverge/hyperkyc/databinding/HkFragmentCountryPickerBinding;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CountryPickerFragment.class, "selectedCountry", "getSelectedCountry$hyperkyc_release()Lco/hyperverge/hyperkyc/data/models/KycCountry;", 0))};

    public CountryPickerFragment() {
        super(R.layout.hk_fragment_country_picker);
        final CountryPickerFragment countryPickerFragment = this;
        this.mainVM = FragmentViewModelLazyKt.createViewModelLazy(countryPickerFragment, Reflection.getOrCreateKotlinClass(MainVM.class), new Function0<ViewModelStore>() { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$special$$inlined$activityViewModels$default$2
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
        this.binding = FragmentViewBindingDelegateKt.viewBinding(countryPickerFragment, CountryPickerFragment$binding$2.INSTANCE);
        this.textConfigs = LazyKt.lazy(new Function0<Map<String, ? extends String>>() { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$textConfigs$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Map<String, ? extends String> invoke() {
                Bundle arguments = CountryPickerFragment.this.getArguments();
                Object obj = arguments != null ? arguments.get("textConfigs") : null;
                Map map = obj instanceof Map ? (Map) obj : null;
                Map<String, ? extends String> map2 = map != null ? MapsKt.toMap(map) : null;
                Intrinsics.checkNotNull(map2);
                return map2;
            }
        });
        this.showBackButton = LazyKt.lazy(new Function0<Boolean>() { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$showBackButton$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                Bundle arguments = CountryPickerFragment.this.getArguments();
                Boolean valueOf = arguments != null ? Boolean.valueOf(arguments.getBoolean("showBackButton")) : null;
                Intrinsics.checkNotNull(valueOf);
                return valueOf;
            }
        });
        this.rvCountryAdapter = new SimpleRvAdapter<>(new Function1<Integer, Integer>() { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$rvCountryAdapter$1
            public final Integer invoke(int i) {
                return Integer.valueOf(R.layout.hk_rv_item_country);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                return invoke(num.intValue());
            }
        }, new Function2<View, Integer, CountryVH>() { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$rvCountryAdapter$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            public final CountryPickerFragment.CountryVH invoke(View view, int i) {
                Intrinsics.checkNotNullParameter(view, "view");
                CountryPickerFragment countryPickerFragment2 = CountryPickerFragment.this;
                HkRvItemCountryBinding bind = HkRvItemCountryBinding.bind(view);
                Intrinsics.checkNotNullExpressionValue(bind, "bind(view)");
                return new CountryPickerFragment.CountryVH(countryPickerFragment2, bind);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ CountryPickerFragment.CountryVH invoke(View view, Integer num) {
                return invoke(view, num.intValue());
            }
        }, new Function2<CountryVH, KycCountry, Unit>() { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$rvCountryAdapter$3
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CountryPickerFragment.CountryVH countryVH, KycCountry kycCountry) {
                invoke2(countryVH, kycCountry);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(CountryPickerFragment.CountryVH h, KycCountry item) {
                Intrinsics.checkNotNullParameter(h, "h");
                Intrinsics.checkNotNullParameter(item, "item");
                h.bind(item);
            }
        }, new Function2<KycCountry, KycCountry, Boolean>() { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$rvCountryAdapter$4
            @Override // kotlin.jvm.functions.Function2
            public final Boolean invoke(KycCountry old, KycCountry kycCountry) {
                Intrinsics.checkNotNullParameter(old, "old");
                Intrinsics.checkNotNullParameter(kycCountry, "new");
                return Boolean.valueOf(Intrinsics.areEqual(old.getId(), kycCountry.getId()) && old.getSelected() == kycCountry.getSelected());
            }
        }, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null);
        this.backPressedCallback = LazyKt.lazy(new Function0<CountryPickerFragment$backPressedCallback$2.AnonymousClass1>() { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$backPressedCallback$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v0, types: [co.hyperverge.hyperkyc.ui.CountryPickerFragment$backPressedCallback$2$1] */
            @Override // kotlin.jvm.functions.Function0
            public final AnonymousClass1 invoke() {
                final CountryPickerFragment countryPickerFragment2 = CountryPickerFragment.this;
                return new OnBackPressedCallback() { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$backPressedCallback$2.1
                    {
                        super(false);
                    }

                    @Override // androidx.activity.OnBackPressedCallback
                    public void handleOnBackPressed() {
                        CountryPickerFragment.this.getBackPressedCallback$hyperkyc_release().setEnabled(false);
                        CountryPickerFragment.this.getBinding$hyperkyc_release().motionLayout.transitionToStart();
                    }
                };
            }
        });
        Delegates delegates = Delegates.INSTANCE;
        final Object obj = null;
        this.selectedCountry = new ObservableProperty<KycCountry>(obj) { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$special$$inlined$observable$1
            /* JADX WARN: Removed duplicated region for block: B:41:0x0179  */
            /* JADX WARN: Removed duplicated region for block: B:49:0x01b8  */
            @Override // kotlin.properties.ObservableProperty
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            protected void afterChange(KProperty<?> property, KycCountry oldValue, KycCountry newValue) {
                String canonicalName;
                Class<?> cls;
                Object m1202constructorimpl;
                String str;
                Class<?> cls2;
                Matcher matcher;
                String str2;
                String className;
                String className2;
                Intrinsics.checkNotNullParameter(property, "property");
                KycCountry kycCountry = newValue;
                KycCountry kycCountry2 = oldValue;
                CountryPickerFragment countryPickerFragment2 = this;
                HyperLogger.Level level = HyperLogger.Level.DEBUG;
                HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                String str3 = "N/A";
                if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    canonicalName = (countryPickerFragment2 == null || (cls = countryPickerFragment2.getClass()) == null) ? null : cls.getCanonicalName();
                    if (canonicalName == null) {
                        canonicalName = "N/A";
                    }
                }
                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
                if (matcher2.find()) {
                    canonicalName = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
                }
                if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    canonicalName = canonicalName.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb.append(canonicalName);
                sb.append(" - ");
                String str4 = "selectedCountry Delegates.observable() called with: old = [" + kycCountry2 + "],\n new = [" + kycCountry + ']';
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
                                String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default != null) {
                                    str3 = substringAfterLast$default;
                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                                    if (matcher.find()) {
                                        str3 = matcher.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                                    }
                                    if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str3 = str3.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb2 = new StringBuilder();
                                    str2 = "selectedCountry Delegates.observable() called with: old = [" + kycCountry2 + "],\n new = [" + kycCountry + ']';
                                    if (str2 == null) {
                                        str2 = "null ";
                                    }
                                    sb2.append(str2);
                                    sb2.append(' ');
                                    sb2.append("");
                                    Log.println(4, str3, sb2.toString());
                                }
                            }
                            String canonicalName2 = (countryPickerFragment2 == null || (cls2 = countryPickerFragment2.getClass()) == null) ? str : cls2.getCanonicalName();
                            if (canonicalName2 != null) {
                                str3 = canonicalName2;
                            }
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                            if (matcher.find()) {
                            }
                            if (str3.length() > 23) {
                                str3 = str3.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb22 = new StringBuilder();
                            str2 = "selectedCountry Delegates.observable() called with: old = [" + kycCountry2 + "],\n new = [" + kycCountry + ']';
                            if (str2 == null) {
                            }
                            sb22.append(str2);
                            sb22.append(' ');
                            sb22.append("");
                            Log.println(4, str3, sb22.toString());
                        }
                    }
                }
                this.updateSelectedCountry(kycCountry2, kycCountry, true);
            }
        };
    }

    private final MainVM getMainVM() {
        return (MainVM) this.mainVM.getValue();
    }

    public final HkFragmentCountryPickerBinding getBinding$hyperkyc_release() {
        return (HkFragmentCountryPickerBinding) this.binding.getValue2((Fragment) this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ArrayList<KycCountry> getCountries() {
        Parcelable[] parcelableArray;
        Bundle arguments = getArguments();
        List mutableList = (arguments == null || (parcelableArray = arguments.getParcelableArray("countries")) == null) ? null : ArraysKt.toMutableList(parcelableArray);
        if (mutableList instanceof ArrayList) {
            return (ArrayList) mutableList;
        }
        return null;
    }

    private final Map<String, String> getTextConfigs() {
        return (Map) this.textConfigs.getValue();
    }

    private final boolean getShowBackButton() {
        return ((Boolean) this.showBackButton.getValue()).booleanValue();
    }

    public final OnBackPressedCallback getBackPressedCallback$hyperkyc_release() {
        return (OnBackPressedCallback) this.backPressedCallback.getValue();
    }

    public final KycCountry getSelectedCountry$hyperkyc_release() {
        return (KycCountry) this.selectedCountry.getValue(this, $$delegatedProperties[1]);
    }

    public final void setSelectedCountry$hyperkyc_release(KycCountry kycCountry) {
        this.selectedCountry.setValue(this, $$delegatedProperties[1], kycCountry);
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
        ArrayList<KycCountry> countries = getCountries();
        if (countries != null) {
            outState.putParcelableArrayList("countries", new ArrayList<>(countries));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        super.onCreate(savedInstanceState);
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
        String str2 = "onCreate() called with: savedInstanceState = " + savedInstanceState;
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
                    String str3 = "onCreate() called with: savedInstanceState = " + savedInstanceState;
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
        requireActivity().getOnBackPressedDispatcher().addCallback(this, getBackPressedCallback$hyperkyc_release());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$24$lambda$9(HkFragmentCountryPickerBinding this_with, CountryPickerFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ClickableMotionLayout root = this_with.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "root");
        ViewExtsKt.hideSoftInput(root);
        this$0.showClientLogo(this_with, true);
        showBackButton$default(this$0, this_with, false, 1, null);
        this$0.onBackPressed$hyperkyc_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$24$lambda$10(CountryPickerFragment this$0, HkFragmentCountryPickerBinding this_with, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        this$0.getBackPressedCallback$hyperkyc_release().setEnabled(true);
        this$0.showClientLogo(this_with, false);
        this$0.showBackButton(this_with, true);
        this_with.motionLayout.transitionToEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$24$lambda$12(CountryPickerFragment this$0, HkFragmentCountryPickerBinding this_with, View view) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        AnalyticsLogger analyticsLogger = AnalyticsLogger.INSTANCE;
        KycCountry selectedCountry$hyperkyc_release = this$0.getSelectedCountry$hyperkyc_release();
        Intrinsics.checkNotNull(selectedCountry$hyperkyc_release);
        analyticsLogger.logCountryPickEvent$hyperkyc_release(selectedCountry$hyperkyc_release.getId());
        boolean flowForward = this$0.getMainVM().flowForward();
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = this_with.getClass();
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
        String str2 = "onViewCreated: handled: " + flowForward;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            return;
        }
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
                    Class<?> cls2 = this_with.getClass();
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
                String str3 = "onViewCreated: handled: " + flowForward;
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

    static /* synthetic */ void showBackButton$default(CountryPickerFragment countryPickerFragment, HkFragmentCountryPickerBinding hkFragmentCountryPickerBinding, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = countryPickerFragment.getShowBackButton();
        }
        countryPickerFragment.showBackButton(hkFragmentCountryPickerBinding, z);
    }

    static /* synthetic */ void updateSelectedCountry$default(CountryPickerFragment countryPickerFragment, KycCountry kycCountry, KycCountry kycCountry2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        countryPickerFragment.updateSelectedCountry(kycCountry, kycCountry2, z);
    }

    private static final void updateSelectedCountry$notifyChange(KycCountry kycCountry, CountryPickerFragment countryPickerFragment, boolean z) {
        if (kycCountry != null) {
            kycCountry.setSelected(z);
        }
        int indexOf = countryPickerFragment.rvCountryAdapter.getCurrentList().indexOf(kycCountry);
        boolean z2 = false;
        if (-1 <= indexOf && indexOf <= countryPickerFragment.rvCountryAdapter.getItemCount()) {
            z2 = true;
        }
        if (z2) {
            countryPickerFragment.rvCountryAdapter.notifyItemChanged(indexOf);
        }
    }

    /* compiled from: CountryPickerFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lco/hyperverge/hyperkyc/ui/CountryPickerFragment$CountryVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemBinding", "Lco/hyperverge/hyperkyc/databinding/HkRvItemCountryBinding;", "(Lco/hyperverge/hyperkyc/ui/CountryPickerFragment;Lco/hyperverge/hyperkyc/databinding/HkRvItemCountryBinding;)V", "bind", "", UserDataStore.COUNTRY, "Lco/hyperverge/hyperkyc/data/models/KycCountry;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class CountryVH extends RecyclerView.ViewHolder {
        private final HkRvItemCountryBinding itemBinding;
        final /* synthetic */ CountryPickerFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CountryVH(CountryPickerFragment countryPickerFragment, HkRvItemCountryBinding itemBinding) {
            super(itemBinding.getRoot());
            Intrinsics.checkNotNullParameter(itemBinding, "itemBinding");
            this.this$0 = countryPickerFragment;
            this.itemBinding = itemBinding;
        }

        /* JADX WARN: Code restructure failed: missing block: B:69:0x0142, code lost:
        
            if (r0 == null) goto L55;
         */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0202  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x020d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void bind(final KycCountry country) {
            String canonicalName;
            Object m1202constructorimpl;
            String canonicalName2;
            String className;
            boolean z;
            String className2;
            Intrinsics.checkNotNullParameter(country, "country");
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
            String str2 = "bind() called with: country = " + country;
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
                        String str3 = "bind() called with: country = " + country;
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
            final HkRvItemCountryBinding hkRvItemCountryBinding = this.itemBinding;
            final CountryPickerFragment countryPickerFragment = this.this$0;
            MaterialTextView materialTextView = hkRvItemCountryBinding.tvName;
            materialTextView.setText(country.getName());
            Intrinsics.checkNotNullExpressionValue(materialTextView, "this");
            countryPickerFragment.setSecondaryTextViewFont(materialTextView);
            HyperSnapBridgeKt.getUiConfigUtil().customiseCountryListItem(materialTextView, country.getSelected());
            ImageView ivFlag = hkRvItemCountryBinding.ivFlag;
            Intrinsics.checkNotNullExpressionValue(ivFlag, "ivFlag");
            PicassoExtsKt.loadFlag$default(ivFlag, country.getId(), 0.0f, 0.0f, 6, null);
            if (!country.getSelected()) {
                KycCountry selectedCountry$hyperkyc_release = countryPickerFragment.getSelectedCountry$hyperkyc_release();
                if (!Intrinsics.areEqual(selectedCountry$hyperkyc_release != null ? selectedCountry$hyperkyc_release.getId() : null, country.getId())) {
                    z = false;
                    country.setSelected(z);
                    ImageView ivCheck = hkRvItemCountryBinding.ivCheck;
                    Intrinsics.checkNotNullExpressionValue(ivCheck, "ivCheck");
                    ivCheck.setVisibility(country.getSelected() ? 0 : 8);
                    if (country.getSelected()) {
                        countryPickerFragment.getBinding$hyperkyc_release().btnContinue.setEnabled(true);
                        MaterialButton materialButton = countryPickerFragment.getBinding$hyperkyc_release().btnContinue;
                        Intrinsics.checkNotNullExpressionValue(materialButton, "binding.btnContinue");
                        countryPickerFragment.setPrimaryButtonBackgroundColor$hyperkyc_release(materialButton);
                        HyperSnapBridgeKt.getUiConfigUtil().customisePrimaryButton(countryPickerFragment.getBinding$hyperkyc_release().btnContinue);
                    }
                    this.itemView.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$CountryVH$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            CountryPickerFragment.CountryVH.bind$lambda$4$lambda$3(HkRvItemCountryBinding.this, countryPickerFragment, country, this, view);
                        }
                    });
                }
            }
            z = true;
            country.setSelected(z);
            ImageView ivCheck2 = hkRvItemCountryBinding.ivCheck;
            Intrinsics.checkNotNullExpressionValue(ivCheck2, "ivCheck");
            ivCheck2.setVisibility(country.getSelected() ? 0 : 8);
            if (country.getSelected()) {
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$CountryVH$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CountryPickerFragment.CountryVH.bind$lambda$4$lambda$3(HkRvItemCountryBinding.this, countryPickerFragment, country, this, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:41:0x0174  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x01ad  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static final void bind$lambda$4$lambda$3(HkRvItemCountryBinding this_with, CountryPickerFragment this$0, KycCountry country, CountryVH this$1, View view) {
            String canonicalName;
            Object m1202constructorimpl;
            String str;
            Matcher matcher;
            String className;
            String className2;
            Intrinsics.checkNotNullParameter(this_with, "$this_with");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(country, "$country");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str2 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls = this_with.getClass();
                canonicalName = cls != null ? cls.getCanonicalName() : null;
                if (canonicalName == null) {
                    canonicalName = "N/A";
                }
            }
            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
            if (matcher2.find()) {
                canonicalName = matcher2.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
            }
            if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                canonicalName = canonicalName.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(canonicalName);
            sb.append(" - ");
            String str3 = "bind() itemView.setOnClickListener called at pos " + this$1.getAdapterPosition();
            if (str3 == null) {
                str3 = "null ";
            }
            sb.append(str3);
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
                            String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            if (substringAfterLast$default != null) {
                                str2 = substringAfterLast$default;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                                if (matcher.find()) {
                                    str2 = matcher.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                                }
                                if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str2 = str2.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb2 = new StringBuilder();
                                String str4 = "bind() itemView.setOnClickListener called at pos " + this$1.getAdapterPosition();
                                sb2.append(str4 != null ? str4 : "null ");
                                sb2.append(' ');
                                sb2.append("");
                                Log.println(4, str2, sb2.toString());
                            }
                        }
                        Class<?> cls2 = this_with.getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : str;
                        if (canonicalName2 != null) {
                            str2 = canonicalName2;
                        }
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                        if (matcher.find()) {
                        }
                        if (str2.length() > 23) {
                            str2 = str2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb22 = new StringBuilder();
                        String str42 = "bind() itemView.setOnClickListener called at pos " + this$1.getAdapterPosition();
                        sb22.append(str42 != null ? str42 : "null ");
                        sb22.append(' ');
                        sb22.append("");
                        Log.println(4, str2, sb22.toString());
                    }
                }
            }
            this$0.setSelectedCountry$hyperkyc_release(country);
            this$0.getBinding$hyperkyc_release().btnContinue.setEnabled(true);
            MaterialButton materialButton = this$0.getBinding$hyperkyc_release().btnContinue;
            Intrinsics.checkNotNullExpressionValue(materialButton, "binding.btnContinue");
            this$0.setPrimaryButtonBackgroundColor$hyperkyc_release(materialButton);
            HyperSnapBridgeKt.getUiConfigUtil().customisePrimaryButton(this$0.getBinding$hyperkyc_release().btnContinue);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0140, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0150, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0154, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0163, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0165, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0170, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0176, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0179, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0180, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "onViewCreated() called with: view = " + r18 + ", savedInstanceState = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x019a, code lost:
    
        if (r4 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x019c, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x019e, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(2, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0153, code lost:
    
        r8 = r0;
     */
    @Override // co.hyperverge.hyperkyc.ui.BaseFragment, androidx.fragment.app.Fragment
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
        if (savedInstanceState != null) {
            this.countries = savedInstanceState.getParcelableArrayList("countries");
        }
        super.onViewCreated(view, savedInstanceState);
        ArrayList<KycCountry> countries = getCountries();
        if (countries != null) {
            updateCountries(countries);
        }
        setSelectedCountry$hyperkyc_release(getMainVM().getSelectedCountry());
    }

    /* JADX WARN: Code restructure failed: missing block: B:97:0x0124, code lost:
    
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
        final HkFragmentCountryPickerBinding initViews$lambda$24 = getBinding$hyperkyc_release();
        RecyclerView recyclerView = initViews$lambda$24.rvCountries;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(this.rvCountryAdapter);
        RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.setChangeDuration(400L);
        }
        ClickableMotionLayout motionLayout = initViews$lambda$24.motionLayout;
        Intrinsics.checkNotNullExpressionValue(motionLayout, "motionLayout");
        ViewExtsKt.onTransition$default(motionLayout, null, new Function1<Integer, Unit>() { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$initViews$2$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                if (i == R.id.end) {
                    AppCompatEditText etSearch = HkFragmentCountryPickerBinding.this.etSearch;
                    Intrinsics.checkNotNullExpressionValue(etSearch, "etSearch");
                    ViewExtsKt.showSoftInput(etSearch);
                }
            }
        }, 1, null);
        Intrinsics.checkNotNullExpressionValue(initViews$lambda$24, "initViews$lambda$24");
        showBackButton(initViews$lambda$24, getShowBackButton());
        initViews$lambda$24.ivBack.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CountryPickerFragment.initViews$lambda$24$lambda$9(HkFragmentCountryPickerBinding.this, this, view);
            }
        });
        initViews$lambda$24.btnCountry.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CountryPickerFragment.initViews$lambda$24$lambda$10(CountryPickerFragment.this, initViews$lambda$24, view);
            }
        });
        initViews$lambda$24.btnContinue.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CountryPickerFragment.initViews$lambda$24$lambda$12(CountryPickerFragment.this, initViews$lambda$24, view);
            }
        });
        AppCompatEditText etSearch = initViews$lambda$24.etSearch;
        Intrinsics.checkNotNullExpressionValue(etSearch, "etSearch");
        etSearch.addTextChangedListener(new TextWatcher() { // from class: co.hyperverge.hyperkyc.ui.CountryPickerFragment$initViews$lambda$24$$inlined$doOnTextChanged$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                ArrayList countries;
                countries = CountryPickerFragment.this.getCountries();
                if (countries != null) {
                    CountryPickerFragment countryPickerFragment = CountryPickerFragment.this;
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : countries) {
                        if (StringsKt.contains((CharSequence) ((KycCountry) obj).getName(), text == null ? "" : text, true)) {
                            arrayList.add(obj);
                        }
                    }
                    countryPickerFragment.updateCountries(arrayList);
                }
            }
        });
        int i = getMainVM().shouldShowBranding() ? 0 : 4;
        ClickableMotionLayout clickableMotionLayout = initViews$lambda$24.motionLayout;
        int[] constraintSetIds = clickableMotionLayout.getConstraintSetIds();
        Intrinsics.checkNotNullExpressionValue(constraintSetIds, "constraintSetIds");
        for (int i2 : constraintSetIds) {
            clickableMotionLayout.getConstraintSet(i2).setVisibility(R.id.includeBranding, i);
        }
        String str2 = getTextConfigs().get("countries_title");
        if (str2 != null) {
            if (str2.length() > 0) {
                initViews$lambda$24.tvTitle.setText(UIExtsKt.fromHTML(str2));
            }
        }
        String str3 = getTextConfigs().get("countries_desc");
        if (str3 != null) {
            if (str3.length() > 0) {
                initViews$lambda$24.tvSubTitle.setText(UIExtsKt.fromHTML(str3));
            }
        }
        String str4 = getTextConfigs().get("countries_button");
        if (str4 != null) {
            if (str4.length() > 0) {
                initViews$lambda$24.btnContinue.setText(UIExtsKt.fromHTML(str4));
            }
        }
        String str5 = getTextConfigs().get("countries_searchText");
        if (str5 != null) {
            if (str5.length() > 0) {
                initViews$lambda$24.etSearch.setHint(UIExtsKt.fromHTML(str5));
            }
        }
        HyperSnapBridgeKt.getUiConfigUtil().customiseTitleTextView(initViews$lambda$24.tvTitle);
        HyperSnapBridgeKt.getUiConfigUtil().customiseDescriptionTextView(initViews$lambda$24.tvSubTitle);
        HyperSnapBridgeKt.getUiConfigUtil().customisePrimaryButton(initViews$lambda$24.btnContinue);
        HyperSnapBridgeKt.getUiConfigUtil().customisePickerButton(initViews$lambda$24.btnCountry);
        HyperSnapBridgeKt.getUiConfigUtil().customiseBackButtonIcon(initViews$lambda$24.ivBack);
        ClickableMotionLayout clickableMotionLayout2 = initViews$lambda$24.motionLayout;
        int[] constraintSetIds2 = clickableMotionLayout2.getConstraintSetIds();
        Intrinsics.checkNotNullExpressionValue(constraintSetIds2, "constraintSetIds");
        for (int i3 : constraintSetIds2) {
            clickableMotionLayout2.getConstraintSet(i3).constrainWidth(R.id.ivBack, ViewExtsKt.getDp(32));
            clickableMotionLayout2.getConstraintSet(i3).constrainHeight(R.id.ivBack, ViewExtsKt.getDp(32));
        }
        HyperSnapBridgeKt.getUiConfigUtil().customiseClientLogo(initViews$lambda$24.hkClientLogo);
        HyperSnapBridgeKt.getUiConfigUtil().customiseCountrySearchText(initViews$lambda$24.etSearch);
        HSUIConfig uiConfigData = getMainVM().getUiConfigData();
        loadBackground$hyperkyc_release(uiConfigData != null ? uiConfigData.getBackgroundImageUrl() : null);
    }

    private final void showBackButton(HkFragmentCountryPickerBinding hkFragmentCountryPickerBinding, boolean z) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        Class<?> cls2;
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
        String str2 = null;
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (hkFragmentCountryPickerBinding == null || (cls = hkFragmentCountryPickerBinding.getClass()) == null) ? null : cls.getCanonicalName();
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
        String str3 = "showBackButton() called with: shouldShow = " + z;
        if (str3 == null) {
            str3 = "null ";
        }
        sb.append(str3);
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
                        if (hkFragmentCountryPickerBinding != null && (cls2 = hkFragmentCountryPickerBinding.getClass()) != null) {
                            str2 = cls2.getCanonicalName();
                        }
                        if (str2 != null) {
                            str = str2;
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
                    String str4 = "showBackButton() called with: shouldShow = " + z;
                    if (str4 == null) {
                        str4 = "null ";
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        ClickableMotionLayout clickableMotionLayout = hkFragmentCountryPickerBinding.motionLayout;
        int[] constraintSetIds = clickableMotionLayout.getConstraintSetIds();
        Intrinsics.checkNotNullExpressionValue(constraintSetIds, "constraintSetIds");
        for (int i : constraintSetIds) {
            clickableMotionLayout.getConstraintSet(i).setVisibility(R.id.ivBack, z ? 0 : 4);
        }
    }

    private final void showClientLogo(HkFragmentCountryPickerBinding hkFragmentCountryPickerBinding, boolean z) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        Class<?> cls2;
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
        String str2 = null;
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (hkFragmentCountryPickerBinding == null || (cls = hkFragmentCountryPickerBinding.getClass()) == null) ? null : cls.getCanonicalName();
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
        String str3 = "showClientLogo() called with: shouldShowLogo = " + z;
        if (str3 == null) {
            str3 = "null ";
        }
        sb.append(str3);
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
                        if (hkFragmentCountryPickerBinding != null && (cls2 = hkFragmentCountryPickerBinding.getClass()) != null) {
                            str2 = cls2.getCanonicalName();
                        }
                        if (str2 != null) {
                            str = str2;
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
                    String str4 = "showClientLogo() called with: shouldShowLogo = " + z;
                    if (str4 == null) {
                        str4 = "null ";
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        int i = z ? 0 : 4;
        ClickableMotionLayout clickableMotionLayout = hkFragmentCountryPickerBinding.motionLayout;
        int[] constraintSetIds = clickableMotionLayout.getConstraintSetIds();
        Intrinsics.checkNotNullExpressionValue(constraintSetIds, "constraintSetIds");
        for (int i2 : constraintSetIds) {
            clickableMotionLayout.getConstraintSet(i2).setVisibility(R.id.hkClientLogo, i);
        }
    }

    private final void setSecondaryButtonFont(MaterialButton button) {
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
        String str2 = "setSecondaryButtonFont() called with: button = " + button;
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
                    String str3 = "setSecondaryButtonFont() called with: button = " + button;
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
        UIConfig config = HyperSnapBridgeKt.getUiConfigUtil().getConfig();
        HyperSnapBridgeKt.getUiConfigUtil().setFont((Button) button, config.getFont().getSecondaryButtonTextFont(), config.getFontWeight().getSecondaryButtonTextWeight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSecondaryTextViewFont(MaterialTextView textView) {
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
        String str2 = "setSecondaryTextViewFont() called with: textView = " + textView;
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
                    String str3 = "setSecondaryTextViewFont() called with: textView = " + textView;
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
        UIConfig config = HyperSnapBridgeKt.getUiConfigUtil().getConfig();
        HyperSnapBridgeKt.getUiConfigUtil().setFont(textView, config.getFont().getSecondaryButtonTextFont(), config.getFontWeight().getSecondaryButtonTextWeight());
    }

    public final void setPrimaryButtonBackgroundColor$hyperkyc_release(MaterialButton button) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(button, "button");
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
        String str2 = "setPrimaryButtonBackgroundColor() called with: button = " + button;
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
                    String str3 = "setPrimaryButtonBackgroundColor() called with: button = " + button;
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
        HyperSnapBridgeKt.getUiConfigUtil().setBackgroundColor(button, HyperSnapBridgeKt.getUiConfigUtil().getConfig().getColors().getPrimaryButtonBackgroundColor());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0242  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void updateCountries(List<KycCountry> countries) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        int i;
        String className;
        Iterator<T> it;
        int i2;
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
        StringBuilder sb2 = new StringBuilder();
        sb2.append("updateCountries() called with: countries = ");
        List<KycCountry> list = countries;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (KycCountry kycCountry : list) {
            arrayList.add(kycCountry.getId() + ':' + kycCountry.getSelected());
            str2 = str2;
        }
        String str3 = str2;
        sb2.append(arrayList);
        String sb3 = sb2.toString();
        if (sb3 == null) {
            sb3 = "null ";
        }
        sb.append(sb3);
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (str = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        str = canonicalName2 == null ? str3 : canonicalName2;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        i = 0;
                    } else {
                        i = 0;
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb4 = new StringBuilder();
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("updateCountries() called with: countries = ");
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    for (KycCountry kycCountry2 : list) {
                        arrayList2.add(kycCountry2.getId() + ':' + kycCountry2.getSelected());
                    }
                    sb5.append(arrayList2);
                    String sb6 = sb5.toString();
                    sb4.append(sb6 != null ? sb6 : "null ");
                    sb4.append(' ');
                    sb4.append("");
                    Log.println(3, str, sb4.toString());
                    if ((list instanceof Collection) || !list.isEmpty()) {
                        it = list.iterator();
                        while (it.hasNext()) {
                            if (((KycCountry) it.next()).getSelected()) {
                                i2 = i;
                                break;
                            }
                        }
                    }
                    i2 = 1;
                    if (i2 != 0) {
                        Editable text = getBinding$hyperkyc_release().etSearch.getText();
                        if (((text == null || StringsKt.isBlank(text)) ? 1 : i) != 0) {
                            FragmentActivity requireActivity = requireActivity();
                            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                            String countryId = CoreExtsKt.getCountryId(requireActivity);
                            Iterator<KycCountry> it2 = countries.iterator();
                            int i3 = i;
                            while (true) {
                                if (!it2.hasNext()) {
                                    i3 = -1;
                                    break;
                                } else if (Intrinsics.areEqual(it2.next().getId(), countryId)) {
                                    break;
                                } else {
                                    i3++;
                                }
                            }
                            if (i3 > -1) {
                                countries.get(i3).setSelected(true);
                                updateSelectedCountry$default(this, null, countries.get(i3), false, 4, null);
                                getBinding$hyperkyc_release().rvCountries.scrollToPosition(i3);
                            }
                        }
                    }
                    SimpleRvAdapter.updateItems$default(this.rvCountryAdapter, countries, null, 2, null);
                }
            }
        }
        i = 0;
        if (list instanceof Collection) {
        }
        it = list.iterator();
        while (it.hasNext()) {
        }
        i2 = 1;
        if (i2 != 0) {
        }
        SimpleRvAdapter.updateItems$default(this.rvCountryAdapter, countries, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSelectedCountry(KycCountry old, KycCountry r20, boolean submit) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
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
        String str2 = "updateSelectedCountry() called with: old = " + old + ", new = " + r20 + ", submit = " + submit;
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
                        str = canonicalName2 == null ? "N/A" : canonicalName2;
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
                    String str3 = "updateSelectedCountry() called with: old = " + old + ", new = " + r20 + ", submit = " + submit;
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(4, str, sb2.toString());
                }
            }
        }
        if (old != null) {
            updateSelectedCountry$notifyChange(old, this, false);
        }
        if (r20 != null) {
            updateSelectedCountry$notifyChange(r20, this, true);
        }
        if (old == null && r20 != null) {
            getBinding$hyperkyc_release().rvCountries.scrollToPosition(this.rvCountryAdapter.getCurrentList().indexOf(r20));
        }
        getMainVM().setSelectedCountry(r20);
        if (submit) {
            HkFragmentCountryPickerBinding binding$hyperkyc_release = getBinding$hyperkyc_release();
            getBackPressedCallback$hyperkyc_release().setEnabled(false);
            ClickableMotionLayout root = binding$hyperkyc_release.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "root");
            ViewExtsKt.hideSoftInput(root);
            binding$hyperkyc_release.etSearch.setText("");
            binding$hyperkyc_release.motionLayout.transitionToStart();
            getLifecycleScope().launchWhenResumed(new CountryPickerFragment$updateSelectedCountry$2$1(this, binding$hyperkyc_release, r20, null));
        }
    }
}
