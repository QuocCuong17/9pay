package co.hyperverge.hyperkyc.ui.form;

import android.app.Application;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.ColorUtils;
import androidx.core.text.HtmlCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupKt;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.core.RuleEvaluatorKt;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import co.hyperverge.hyperkyc.core.hv.models.DynamicFormUIConfig;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.databinding.HkFragmentFormBinding;
import co.hyperverge.hyperkyc.databinding.HkRvItemFormAddFileBinding;
import co.hyperverge.hyperkyc.databinding.HkRvItemFormFileBinding;
import co.hyperverge.hyperkyc.databinding.HkRvItemFormListBinding;
import co.hyperverge.hyperkyc.databinding.HkViewDividerOptionalBinding;
import co.hyperverge.hyperkyc.databinding.HkViewFileUploadClBinding;
import co.hyperverge.hyperkyc.databinding.HkViewImageBinding;
import co.hyperverge.hyperkyc.databinding.HkViewLoaderBinding;
import co.hyperverge.hyperkyc.databinding.HkViewTimerBinding;
import co.hyperverge.hyperkyc.ui.BaseFragment;
import co.hyperverge.hyperkyc.ui.WebViewFragment;
import co.hyperverge.hyperkyc.ui.custom.ClickableLinearLayout;
import co.hyperverge.hyperkyc.ui.custom.DynamicListGridLayout;
import co.hyperverge.hyperkyc.ui.custom.IMEAwareTextInputEditText;
import co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter;
import co.hyperverge.hyperkyc.ui.custom.blocks.BlockEditTextConfig;
import co.hyperverge.hyperkyc.ui.custom.blocks.BlockItemViewConfig;
import co.hyperverge.hyperkyc.ui.custom.blocks.BlocksContainerConfig;
import co.hyperverge.hyperkyc.ui.custom.blocks.BlocksView;
import co.hyperverge.hyperkyc.ui.custom.blocks.BlocksViewConfig;
import co.hyperverge.hyperkyc.ui.custom.blocks.BlocksViewListener;
import co.hyperverge.hyperkyc.ui.custom.blocks.ErrorTextViewConfig;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegate;
import co.hyperverge.hyperkyc.ui.custom.delegates.FragmentViewBindingDelegateKt;
import co.hyperverge.hyperkyc.ui.form.FormFragment;
import co.hyperverge.hyperkyc.ui.form.models.FormFilePickUIEvent;
import co.hyperverge.hyperkyc.ui.form.models.FormFileReviewUIEvent;
import co.hyperverge.hyperkyc.ui.form.models.PickedFile;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.utils.DynamicFormUtils;
import co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.ContextExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoroutineExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.DateExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.FileExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.JSONExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.ListExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.Margin;
import co.hyperverge.hyperkyc.utils.extensions.PicassoExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.UIExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.model.UIConfig;
import co.hyperverge.hypersnapsdk.model.UIFont;
import co.hyperverge.hypersnapsdk.model.UIFontWeight;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import co.hyperverge.hypersnapsdk.utils.InternalToolUtils;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.datepicker.MaterialStyledDatePickerDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;
import io.sentry.protocol.ViewHierarchyNode;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: FormFragment.kt */
@Metadata(d1 = {"\u0000æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u0000\u0018\u0000 \u0099\u00012\u00020\u0001:$\u0095\u0001\u0096\u0001\u0097\u0001\u0098\u0001\u0099\u0001\u009a\u0001\u009b\u0001\u009c\u0001\u009d\u0001\u009e\u0001\u009f\u0001 \u0001¡\u0001¢\u0001£\u0001¤\u0001¥\u0001¦\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010K\u001a\u00020(H\u0002J\u000f\u0010L\u001a\u0004\u0018\u00010MH\u0000¢\u0006\u0002\bNJ<\u0010O\u001a\u0004\u0018\u00010M2\u0017\u0010P\u001a\u0013\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020\u00160\"¢\u0006\u0002\bR2\u0017\u0010S\u001a\u0013\u0012\u0004\u0012\u00020T\u0012\u0004\u0012\u00020\u00160\"¢\u0006\u0002\bRH\u0002J\r\u0010U\u001a\u00020\u001aH\u0000¢\u0006\u0002\bVJ\u000f\u0010W\u001a\u0004\u0018\u00010MH\u0000¢\u0006\u0002\bXJ\r\u0010Y\u001a\u00020\u001aH\u0000¢\u0006\u0002\bZJ\r\u0010[\u001a\u00020\u001aH\u0000¢\u0006\u0002\b\\J\u000f\u0010]\u001a\u0004\u0018\u00010MH\u0000¢\u0006\u0002\b^J\b\u0010_\u001a\u00020(H\u0017J\u0012\u0010`\u001a\u00020(2\b\u0010a\u001a\u0004\u0018\u00010bH\u0016J\b\u0010c\u001a\u00020(H\u0016J\b\u0010d\u001a\u00020(H\u0016J\u001a\u0010e\u001a\u00020(2\u0006\u0010f\u001a\u00020\f2\b\u0010a\u001a\u0004\u0018\u00010bH\u0016J\b\u0010g\u001a\u00020(H\u0002J\b\u0010h\u001a\u00020(H\u0003J\u0013\u0010i\u001a\u0004\u0018\u00010\r*\u00020\u0016H\u0000¢\u0006\u0002\bjJ\u0015\u0010k\u001a\u0004\u0018\u00010\r*\u0004\u0018\u00010\rH\u0000¢\u0006\u0002\blJ\u0019\u0010m\u001a\u00020\u0016*\u00020\u00162\u0006\u0010n\u001a\u00020?H\u0000¢\u0006\u0002\boJ!\u0010p\u001a\u0004\u0018\u00010?*\u0004\u0018\u00010\u00162\b\u0010q\u001a\u0004\u0018\u00010?H\u0000¢\u0006\u0004\br\u0010sJ-\u0010t\u001a\u0016\u0012\u0006\b\u0001\u0012\u00020\f\u0012\u0006\b\u0001\u0012\u00020\r0\u000bR\u00020\u0000*\u00020u2\u0006\u0010v\u001a\u00020wH\u0000¢\u0006\u0002\bxJ9\u0010y\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0006\b\u0001\u0012\u00020\f\u0012\u0006\b\u0001\u0012\u00020\r0\u000bR\u00020\u00000#*\u00020u2\f\u0010z\u001a\b\u0012\u0004\u0012\u00020w0#H\u0000¢\u0006\u0002\b{J\u001a\u0010|\u001a\u00020(*\u00020u2\f\u0010}\u001a\b\u0012\u0004\u0012\u00020~0#H\u0002J\u0014\u0010\u007f\u001a\u0004\u0018\u00010\u0016*\u00020wH\u0000¢\u0006\u0003\b\u0080\u0001J(\u0010\u0081\u0001\u001a\u00020(*\u00030\u0082\u00012\u0007\u0010\u0083\u0001\u001a\u00020?2\t\b\u0001\u0010\u0084\u0001\u001a\u00020\u001aH\u0000¢\u0006\u0003\b\u0085\u0001J/\u0010\u0086\u0001\u001a\u00020(*\u0014\u0012\u0010\u0012\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000bR\u00020\u00000#2\b\u0010\u0087\u0001\u001a\u00030\u0088\u0001H\u0000¢\u0006\u0003\b\u0089\u0001J*\u0010\u008a\u0001\u001a\u00020(*\u00030\u0082\u00012\u0006\u0010v\u001a\u00020w2\f\b\u0002\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u0001H\u0000¢\u0006\u0003\b\u008d\u0001J\u001e\u0010\u008e\u0001\u001a\u00020\u0016*\u00020\u00162\t\b\u0002\u0010\u008f\u0001\u001a\u00020?H\u0000¢\u0006\u0003\b\u0090\u0001J\u001e\u0010\u0091\u0001\u001a\u00020\u0016*\u00020\r2\t\b\u0002\u0010\u008f\u0001\u001a\u00020?H\u0000¢\u0006\u0003\b\u0092\u0001J\u0013\u0010\u0093\u0001\u001a\u00020\u0016*\u00020?H\u0000¢\u0006\u0003\b\u0094\u0001R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R(\u0010\t\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0006\b\u0001\u0012\u00020\f\u0012\u0006\b\u0001\u0012\u00020\r0\u000bR\u00020\u00000\nX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R.\u0010\u0014\u001a\"\u0012\u0004\u0012\u00020\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0015j\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0017`\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001bR\u001b\u0010\u001c\u001a\u00020\u001d8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u0013\u001a\u0004\b\u001e\u0010\u001fR/\u0010!\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020$0#¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0004\u0012\u00020(0\"X\u0082.¢\u0006\u0002\n\u0000R(\u0010)\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0016 ,*\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010+0+0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010-\u001a\u00020.8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b1\u0010\u0013\u001a\u0004\b/\u00100R\u001b\u00102\u001a\u0002038@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\u0013\u001a\u0004\b4\u00105R\u001b\u00107\u001a\u00020\u00168@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b:\u0010\u0013\u001a\u0004\b8\u00109R\u001b\u0010;\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b=\u0010\u0013\u001a\u0004\b<\u00109R\u001b\u0010>\u001a\u00020?8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bB\u0010\u0013\u001a\u0004\b@\u0010AR\u001b\u0010C\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bE\u0010\u0013\u001a\u0004\bD\u00109R'\u0010F\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160G8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bJ\u0010\u0013\u001a\u0004\bH\u0010I¨\u0006§\u0001"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "Lco/hyperverge/hyperkyc/ui/BaseFragment;", "()V", "binding", "Lco/hyperverge/hyperkyc/databinding/HkFragmentFormBinding;", "getBinding", "()Lco/hyperverge/hyperkyc/databinding/HkFragmentFormBinding;", "binding$delegate", "Lco/hyperverge/hyperkyc/ui/custom/delegates/FragmentViewBindingDelegate;", "compViews", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "Landroid/view/View;", "", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver$hyperkyc_release", "()Landroid/content/ContentResolver;", "contentResolver$delegate", "Lkotlin/Lazy;", "countdownTimersMap", "Ljava/util/HashMap;", "", "Landroid/os/CountDownTimer;", "Lkotlin/collections/HashMap;", "defaultSoftInputMode", "", "Ljava/lang/Integer;", "dynamicFormUtils", "Lco/hyperverge/hyperkyc/utils/DynamicFormUtils;", "getDynamicFormUtils$hyperkyc_release", "()Lco/hyperverge/hyperkyc/utils/DynamicFormUtils;", "dynamicFormUtils$delegate", "filePickedCallback", "Lkotlin/Function1;", "", "Landroid/net/Uri;", "Lkotlin/ParameterName;", "name", "uris", "", "filePickerLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "formVM", "Lco/hyperverge/hyperkyc/ui/form/FormVM;", "getFormVM$hyperkyc_release", "()Lco/hyperverge/hyperkyc/ui/form/FormVM;", "formVM$delegate", "mainVM", "Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "getMainVM$hyperkyc_release", "()Lco/hyperverge/hyperkyc/ui/viewmodels/MainVM;", "mainVM$delegate", "moduleId", "getModuleId$hyperkyc_release", "()Ljava/lang/String;", "moduleId$delegate", AnalyticsLogger.Keys.MODULE_TYPE, "getModuleType", "moduleType$delegate", "showBackButton", "", "getShowBackButton", "()Z", "showBackButton$delegate", WebViewFragment.ARG_SUB_TYPE, "getSubType", "subType$delegate", "textConfigs", "", "getTextConfigs", "()Ljava/util/Map;", "textConfigs$delegate", "destroyRunningCountdownTimers", "getDescTextFont", "Landroid/graphics/Typeface;", "getDescTextFont$hyperkyc_release", "getFont", "f", "Lco/hyperverge/hypersnapsdk/model/UIFont;", "Lkotlin/ExtensionFunctionType;", "fw", "Lco/hyperverge/hypersnapsdk/model/UIFontWeight;", "getPrimaryBtnBgColor", "getPrimaryBtnBgColor$hyperkyc_release", "getPrimaryButtonFont", "getPrimaryButtonFont$hyperkyc_release", "getSubtitleTextColor", "getSubtitleTextColor$hyperkyc_release", "getTitleTextColor", "getTitleTextColor$hyperkyc_release", "getTitleTextFont", "getTitleTextFont$hyperkyc_release", "initViews", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDestroyView", "onViewCreated", ViewHierarchyConstants.VIEW_KEY, "showDynamicForm", "showLegacyForm", "anyInjectFromVariables", "anyInjectFromVariables$hyperkyc_release", "anyInjectFromVariablesForAny", "anyInjectFromVariablesForAny$hyperkyc_release", "appendRequired", FormFragment.KEY_REQUIRED, "appendRequired$hyperkyc_release", "asBoolean", "default", "asBoolean$hyperkyc_release", "(Ljava/lang/String;Ljava/lang/Boolean;)Ljava/lang/Boolean;", "createComponent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "createComponent$hyperkyc_release", "createComponents", ViewHierarchyNode.JsonKeys.CHILDREN, "createComponents$hyperkyc_release", "createSections", FormFragment.ARG_SECTIONS, "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section;", "getDateContentWithFormat", "getDateContentWithFormat$hyperkyc_release", "makeSecure", "Lcom/google/android/material/textfield/TextInputLayout;", "secure", "etId", "makeSecure$hyperkyc_release", "renderAll", "margin", "Lco/hyperverge/hyperkyc/utils/extensions/Margin;", "renderAll$hyperkyc_release", "setTitleHintAndHelperText", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "setTitleHintAndHelperText$hyperkyc_release", "stringInjectFromVariables", "addQuotesIfEmpty", "stringInjectFromVariables$hyperkyc_release", "stringInjectFromVariablesForAny", "stringInjectFromVariablesForAny$hyperkyc_release", "toYesNo", "toYesNo$hyperkyc_release", "Button", "Checkbox", "ChipButton", "CompView", "Companion", "Container", "Date", "Divider", "Dropdown", "DynamicList", "DynamicListV2", "FileUpload", "Image", "Label", "Loader", "Radio", "Text", "Timer", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormFragment extends BaseFragment {
    public static final String ARG_KEY_TEXT_CONFIGS = "textConfigs";
    public static final String ARG_MODULE_ID = "moduleId";
    public static final String ARG_SECTIONS = "sections";
    public static final String ARG_SHOW_BACK_BUTTON = "showBackButton";
    public static final String ARG_SUBTYPE = "subtype";
    public static final String ARG_TYPE = "type";
    public static final String DATE_FORMAT = "dd:MM:yyyy";
    public static final String KEY_ENABLED = "enabled";
    public static final String KEY_IS_VALID = "isValid";
    public static final String KEY_NUMBER_OF_FILES = "numberOfFiles";
    public static final String KEY_REQUIRED = "required";
    public static final String KEY_SELECTED = "selected";
    public static final String KEY_STATE = "state";
    public static final String KEY_TOTAL_SIZE = "totalSize";
    public static final String KEY_TOTAL_SIZE_LABEL = "totalSizeLabel";
    public static final String KEY_TYPE_PICKED_SUFFIX = "Picked";
    public static final String KEY_VALUE = "value";
    public static final String KEY_VALUE_HOURS = "valueHours";
    public static final String KEY_VALUE_MINUTES = "valueMinutes";
    public static final String KEY_VALUE_SECONDS = "valueSeconds";
    public static final String KEY_VISIBLE = "visible";
    public static final int VT_ADD_FILES = 10;
    public static final int VT_PICKED_FILE = 11;

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final FragmentViewBindingDelegate binding;
    private List<CompView<? extends View, ? extends Object>> compViews;

    /* renamed from: contentResolver$delegate, reason: from kotlin metadata */
    private final Lazy contentResolver;
    private HashMap<String, CountDownTimer> countdownTimersMap;
    private Integer defaultSoftInputMode;

    /* renamed from: dynamicFormUtils$delegate, reason: from kotlin metadata */
    private final Lazy dynamicFormUtils;
    private Function1<? super List<? extends Uri>, Unit> filePickedCallback;
    private final ActivityResultLauncher<String[]> filePickerLauncher;

    /* renamed from: formVM$delegate, reason: from kotlin metadata */
    private final Lazy formVM;

    /* renamed from: mainVM$delegate, reason: from kotlin metadata */
    private final Lazy mainVM;

    /* renamed from: moduleId$delegate, reason: from kotlin metadata */
    private final Lazy moduleId;

    /* renamed from: moduleType$delegate, reason: from kotlin metadata */
    private final Lazy moduleType;

    /* renamed from: showBackButton$delegate, reason: from kotlin metadata */
    private final Lazy showBackButton;

    /* renamed from: subType$delegate, reason: from kotlin metadata */
    private final Lazy subType;

    /* renamed from: textConfigs$delegate, reason: from kotlin metadata */
    private final Lazy textConfigs;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(FormFragment.class, "binding", "getBinding()Lco/hyperverge/hyperkyc/databinding/HkFragmentFormBinding;", 0))};

    public final String toYesNo$hyperkyc_release(boolean z) {
        return z ? "yes" : "no";
    }

    public FormFragment() {
        super(R.layout.hk_fragment_form);
        final FormFragment formFragment = this;
        this.mainVM = FragmentViewModelLazyKt.createViewModelLazy(formFragment, Reflection.getOrCreateKotlinClass(MainVM.class), new Function0<ViewModelStore>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$special$$inlined$activityViewModels$default$2
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
        this.formVM = FragmentViewModelLazyKt.createViewModelLazy(formFragment, Reflection.getOrCreateKotlinClass(FormVM.class), new Function0<ViewModelStore>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$special$$inlined$activityViewModels$default$3
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$special$$inlined$activityViewModels$default$4
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
        this.binding = FragmentViewBindingDelegateKt.viewBinding(formFragment, FormFragment$binding$2.INSTANCE);
        ActivityResultLauncher<String[]> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.OpenMultipleDocuments(), new ActivityResultCallback() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$$ExternalSyntheticLambda3
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                FormFragment.filePickerLauncher$lambda$2(FormFragment.this, (List) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…picking file\" }\n        }");
        this.filePickerLauncher = registerForActivityResult;
        this.moduleId = LazyKt.lazy(new Function0<String>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$moduleId$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                Bundle arguments = FormFragment.this.getArguments();
                String string = arguments != null ? arguments.getString("moduleId") : null;
                Intrinsics.checkNotNull(string);
                return string;
            }
        });
        this.moduleType = LazyKt.lazy(new Function0<String>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$moduleType$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                Bundle arguments = FormFragment.this.getArguments();
                String string = arguments != null ? arguments.getString("type") : null;
                Intrinsics.checkNotNull(string);
                return string;
            }
        });
        this.subType = LazyKt.lazy(new Function0<String>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$subType$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                Bundle arguments = FormFragment.this.getArguments();
                String string = arguments != null ? arguments.getString(FormFragment.ARG_SUBTYPE) : null;
                Intrinsics.checkNotNull(string);
                return string;
            }
        });
        this.dynamicFormUtils = LazyKt.lazy(new Function0<DynamicFormUtils>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$dynamicFormUtils$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DynamicFormUtils invoke() {
                DisplayMetrics displayMetrics = FormFragment.this.getResources().getDisplayMetrics();
                Intrinsics.checkNotNullExpressionValue(displayMetrics, "resources.displayMetrics");
                return new DynamicFormUtils(displayMetrics);
            }
        });
        this.countdownTimersMap = new HashMap<>();
        this.textConfigs = LazyKt.lazy(new Function0<Map<String, ? extends String>>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$textConfigs$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Map<String, ? extends String> invoke() {
                Bundle arguments = FormFragment.this.getArguments();
                Object obj = arguments != null ? arguments.get("textConfigs") : null;
                Map map = obj instanceof Map ? (Map) obj : null;
                Map<String, ? extends String> map2 = map != null ? MapsKt.toMap(map) : null;
                Intrinsics.checkNotNull(map2);
                return map2;
            }
        });
        this.contentResolver = LazyKt.lazy(new Function0<ContentResolver>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$contentResolver$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ContentResolver invoke() {
                return FormFragment.this.requireContext().getContentResolver();
            }
        });
        this.showBackButton = LazyKt.lazy(new Function0<Boolean>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$showBackButton$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                Bundle arguments = FormFragment.this.getArguments();
                Boolean valueOf = arguments != null ? Boolean.valueOf(arguments.getBoolean("showBackButton")) : null;
                Intrinsics.checkNotNull(valueOf);
                return valueOf;
            }
        });
    }

    public final MainVM getMainVM$hyperkyc_release() {
        return (MainVM) this.mainVM.getValue();
    }

    public final FormVM getFormVM$hyperkyc_release() {
        return (FormVM) this.formVM.getValue();
    }

    private final HkFragmentFormBinding getBinding() {
        return (HkFragmentFormBinding) this.binding.getValue2((Fragment) this, $$delegatedProperties[0]);
    }

    public final String getModuleId$hyperkyc_release() {
        return (String) this.moduleId.getValue();
    }

    private final String getModuleType() {
        return (String) this.moduleType.getValue();
    }

    private final String getSubType() {
        return (String) this.subType.getValue();
    }

    public final DynamicFormUtils getDynamicFormUtils$hyperkyc_release() {
        return (DynamicFormUtils) this.dynamicFormUtils.getValue();
    }

    private final Map<String, String> getTextConfigs() {
        return (Map) this.textConfigs.getValue();
    }

    public final ContentResolver getContentResolver$hyperkyc_release() {
        Object value = this.contentResolver.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-contentResolver>(...)");
        return (ContentResolver) value;
    }

    private final boolean getShowBackButton() {
        return ((Boolean) this.showBackButton.getValue()).booleanValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.defaultSoftInputMode = Integer.valueOf(requireActivity().getWindow().getAttributes().softInputMode);
        requireActivity().getWindow().setSoftInputMode(20);
    }

    @Override // co.hyperverge.hyperkyc.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
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
                Log.println(3, str, sb2.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$10$lambda$8(FormFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed$hyperkyc_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat initViews$lambda$10$lambda$9(FormFragment this$0, HkFragmentFormBinding this_with, View view, WindowInsetsCompat insets) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(insets, "insets");
        boolean z = !insets.isVisible(WindowInsetsCompat.Type.ime()) && this$0.getMainVM$hyperkyc_release().shouldShowBranding();
        LinearLayout linearLayout = this_with.includeBranding.llBrandingRoot;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "includeBranding.llBrandingRoot");
        linearLayout.setVisibility(z ? 0 : 8);
        return ViewCompat.onApplyWindowInsets(view, insets);
    }

    private static final Spanned showLegacyForm$lambda$16$getText(FormFragment formFragment, String str, String str2) {
        String str3 = formFragment.getTextConfigs().get(str);
        if (str3 == null) {
            str3 = str2;
        }
        String str4 = str3;
        if (!(str4.length() == 0)) {
            str2 = str4;
        }
        Spanned fromHtml = HtmlCompat.fromHtml(str2, 0, null, null);
        Intrinsics.checkNotNullExpressionValue(fromHtml, "fromHtml(this, flags, imageGetter, tagHandler)");
        return fromHtml;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showLegacyForm$lambda$16$lambda$14(Function1 tmp0, View view) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showLegacyForm$lambda$16$lambda$15(Function1 tmp0, View view) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(view);
    }

    public final int getPrimaryBtnBgColor$hyperkyc_release() {
        return Color.parseColor(HyperSnapBridgeKt.getUiConfigUtil().getConfig().getColors().getPrimaryButtonBackgroundColor());
    }

    public final int getTitleTextColor$hyperkyc_release() {
        return Color.parseColor(HyperSnapBridgeKt.getUiConfigUtil().getConfig().getColors().getTitleTextColor());
    }

    public final int getSubtitleTextColor$hyperkyc_release() {
        return Color.parseColor(HyperSnapBridgeKt.getUiConfigUtil().getConfig().getColors().getDescriptionTextColor());
    }

    public final Typeface getTitleTextFont$hyperkyc_release() {
        return getFont(new Function1<UIFont, String>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$getTitleTextFont$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(UIFont getFont) {
                Intrinsics.checkNotNullParameter(getFont, "$this$getFont");
                String titleTextFont = getFont.getTitleTextFont();
                Intrinsics.checkNotNullExpressionValue(titleTextFont, "titleTextFont");
                return titleTextFont;
            }
        }, new Function1<UIFontWeight, String>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$getTitleTextFont$2
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(UIFontWeight getFont) {
                Intrinsics.checkNotNullParameter(getFont, "$this$getFont");
                String titleTextWeight = getFont.getTitleTextWeight();
                Intrinsics.checkNotNullExpressionValue(titleTextWeight, "titleTextWeight");
                return titleTextWeight;
            }
        });
    }

    public final Typeface getDescTextFont$hyperkyc_release() {
        return getFont(new Function1<UIFont, String>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$getDescTextFont$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(UIFont getFont) {
                Intrinsics.checkNotNullParameter(getFont, "$this$getFont");
                String descriptionTextFont = getFont.getDescriptionTextFont();
                Intrinsics.checkNotNullExpressionValue(descriptionTextFont, "descriptionTextFont");
                return descriptionTextFont;
            }
        }, new Function1<UIFontWeight, String>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$getDescTextFont$2
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(UIFontWeight getFont) {
                Intrinsics.checkNotNullParameter(getFont, "$this$getFont");
                String descriptionTextWeight = getFont.getDescriptionTextWeight();
                Intrinsics.checkNotNullExpressionValue(descriptionTextWeight, "descriptionTextWeight");
                return descriptionTextWeight;
            }
        });
    }

    public final Typeface getPrimaryButtonFont$hyperkyc_release() {
        return getFont(new Function1<UIFont, String>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$getPrimaryButtonFont$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(UIFont getFont) {
                Intrinsics.checkNotNullParameter(getFont, "$this$getFont");
                String primaryButtonTextFont = getFont.getPrimaryButtonTextFont();
                Intrinsics.checkNotNullExpressionValue(primaryButtonTextFont, "primaryButtonTextFont");
                return primaryButtonTextFont;
            }
        }, new Function1<UIFontWeight, String>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$getPrimaryButtonFont$2
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(UIFontWeight getFont) {
                Intrinsics.checkNotNullParameter(getFont, "$this$getFont");
                String primaryButtonTextWeight = getFont.getPrimaryButtonTextWeight();
                Intrinsics.checkNotNullExpressionValue(primaryButtonTextWeight, "primaryButtonTextWeight");
                return primaryButtonTextWeight;
            }
        });
    }

    private final Typeface getFont(Function1<? super UIFont, String> f, Function1<? super UIFontWeight, String> fw) {
        UIConfig config = HyperSnapBridgeKt.getUiConfigUtil().getConfig();
        HyperSnapUIConfigUtil uiConfigUtil = HyperSnapBridgeKt.getUiConfigUtil();
        UIFont font = config.getFont();
        Intrinsics.checkNotNullExpressionValue(font, "font");
        String invoke = f.invoke(font);
        UIFontWeight fontWeight = config.getFontWeight();
        Intrinsics.checkNotNullExpressionValue(fontWeight, "fontWeight");
        return uiConfigUtil.getFont(invoke, fw.invoke(fontWeight));
    }

    static /* synthetic */ void createSections$lambda$27$render$default(List list, Margin margin, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = ViewExtsKt.getDp(0);
        }
        createSections$lambda$27$render(list, margin, i);
    }

    private static final void createSections$lambda$27$render(List<? extends CompView<?, ?>> list, Margin margin, int i) {
        boolean z;
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CompView compView = (CompView) it.next();
            compView.render(Margin.copy$default(margin, 0, !(compView instanceof Text) ? i : ViewExtsKt.getDp(0), 0, 0, 13, null));
            List<CompView<? extends View, ? extends Object>> childCompViews = compView.getChildCompViews();
            if (childCompViews != null) {
                String type = compView.getComponent().getType();
                if (Intrinsics.areEqual(type, "vertical")) {
                    createSections$lambda$27$render$default(childCompViews, new Margin(0, 0, 0, ViewExtsKt.getDp(12), 7, null), 0, 2, null);
                } else if (Intrinsics.areEqual(type, WorkflowModule.Properties.Section.Component.Type.HORIZONTAL)) {
                    int i2 = compView.topOffset();
                    Margin margin2 = new Margin(0, 0, ViewExtsKt.getDp(12), 0, 11, null);
                    List<CompView<? extends View, ? extends Object>> list2 = childCompViews;
                    if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                        Iterator<T> it2 = list2.iterator();
                        while (it2.hasNext()) {
                            if (((CompView) it2.next()) instanceof Text) {
                                z = true;
                                break;
                            }
                        }
                    }
                    z = false;
                    if (!z) {
                        i2 = ViewExtsKt.getDp(0);
                    }
                    createSections$lambda$27$render(childCompViews, margin2, i2);
                }
            }
        }
    }

    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b \u0004\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ$\u0010:\u001a\u00020;2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00104\u001a\u00020\u00022\n\b\u0002\u0010<\u001a\u0004\u0018\u00010=H\u0002J\b\u0010>\u001a\u00020;H\u0002J\n\u0010?\u001a\u0004\u0018\u00010@H\u0016J\u0011\u0010A\u001a\u0004\u0018\u00018\u0001H\u0000¢\u0006\u0004\bB\u00100J\r\u0010C\u001a\u00028\u0000H&¢\u0006\u0002\u00106J\b\u0010D\u001a\u00020;H\u0016J\u001f\u0010E\u001a\u00020;2\u0006\u0010F\u001a\u00020G2\b\b\u0002\u0010H\u001a\u00020 H\u0000¢\u0006\u0002\bIJ\u000e\u0010J\u001a\u00020;2\u0006\u0010<\u001a\u00020=J\b\u0010K\u001a\u00020LH\u0016J\u0019\u0010M\u001a\u0004\u0018\u00010;2\b\u0010N\u001a\u0004\u0018\u00010OH\u0002¢\u0006\u0002\u0010PJ\u0019\u0010Q\u001a\u00020;2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010OH\u0000¢\u0006\u0002\bRJ'\u0010S\u001a\u00020;\"\b\b\u0002\u0010\u0003*\u00020\u00042\u0006\u0010T\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u0002H\u0003H\u0004¢\u0006\u0002\u0010UJ\u0015\u0010V\u001a\u00020;2\u0006\u0010W\u001a\u00028\u0001H\u0004¢\u0006\u0002\u00102J\b\u0010X\u001a\u00020;H\u0016J\u0010\u0010Y\u001a\u00020;2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0015\u0010Z\u001a\u00020;2\u0006\u0010W\u001a\u00028\u0001H\u0004¢\u0006\u0002\u00102J\u0012\u0010[\u001a\u00020;2\b\u0010N\u001a\u0004\u0018\u00010OH&J*\u0010\\\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0000R\u00020\u000e0\r*\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0000R\u00020\u000eH\u0002J\u0016\u0010]\u001a\u00020L*\u0004\u0018\u00010\u00142\u0006\u0010^\u001a\u00020LH\u0002J\u0018\u0010_\u001a\u00020;*\u00020`2\n\b\u0002\u0010a\u001a\u0004\u0018\u00010bH\u0004J\f\u0010c\u001a\u00020 *\u00020\u0014H\u0002R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R6\u0010\f\u001a\u001e\u0012\u0018\u0012\u0016\u0012\u0006\b\u0001\u0012\u00020\u0002\u0012\u0006\b\u0001\u0012\u00020\u00040\u0000R\u00020\u000e\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\u00020\u00148DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0016\"\u0004\b\u001d\u0010\u001eR$\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020 @FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020 X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\"\"\u0004\b'\u0010$R\u0016\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\"\"\u0004\b.\u0010$R\u001e\u0010\u001f\u001a\u0004\u0018\u00018\u0001X\u0086\u000e¢\u0006\u0010\n\u0002\u00103\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001c\u00104\u001a\u00028\u0000X\u0086.¢\u0006\u0010\n\u0002\u00109\u001a\u0004\b5\u00106\"\u0004\b7\u00108¨\u0006d"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "ViewType", "Landroid/view/View;", "ValueType", "", "parent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment;Landroid/widget/LinearLayout;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;)V", "attachDynamicHandlersJob", "Lkotlinx/coroutines/Job;", "childCompViews", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "getChildCompViews", "()Ljava/util/List;", "setChildCompViews", "(Ljava/util/List;)V", "compLogTag", "", "getCompLogTag", "()Ljava/lang/String;", "compLogTag$delegate", "Lkotlin/Lazy;", "getComponent", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "errorMessage", "getErrorMessage", "setErrorMessage", "(Ljava/lang/String;)V", "value", "", FormFragment.KEY_IS_VALID, "()Z", "setValid", "(Z)V", "onChangeEnabled", "getOnChangeEnabled$hyperkyc_release", "setOnChangeEnabled$hyperkyc_release", "onChangeFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "getParent", "()Landroid/widget/LinearLayout;", FormFragment.KEY_SELECTED, "getSelected", "setSelected", "getValue", "()Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", ViewHierarchyConstants.VIEW_KEY, "getView", "()Landroid/view/View;", "setView", "(Landroid/view/View;)V", "Landroid/view/View;", "addToParent", "", "margin", "Lco/hyperverge/hyperkyc/utils/extensions/Margin;", "attachDynamicHandlers", "defaultLayoutParams", "Landroid/widget/LinearLayout$LayoutParams;", "getComponentValue", "getComponentValue$hyperkyc_release", "inflate", "postInflate", "processHandler", "handler", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler;", "executeNextStep", "processHandler$hyperkyc_release", "render", "topOffset", "", "updateCommonProperties", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "(Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;)Lkotlin/Unit;", "updateComponent", "updateComponent$hyperkyc_release", "updateData", "key", "(Ljava/lang/String;Ljava/lang/Object;)V", "updateDefaultValue", "v", "updateError", "updateIsValid", "updateValue", "updateView", "flatten", "getDimensions", "default", "updateStyling", "Lcom/google/android/material/textfield/TextInputLayout;", "et", "Landroid/widget/EditText;", "validate", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public abstract class CompView<ViewType extends View, ValueType> {
        private Job attachDynamicHandlersJob;
        private List<? extends CompView<? extends View, ? extends Object>> childCompViews;

        /* renamed from: compLogTag$delegate, reason: from kotlin metadata */
        private final Lazy compLogTag;
        private final WorkflowModule.Properties.Section.Component component;
        private String errorMessage;
        private boolean isValid;
        private boolean onChangeEnabled;
        private MutableStateFlow<ValueType> onChangeFlow;
        private final LinearLayout parent;
        private boolean selected;
        final /* synthetic */ FormFragment this$0;
        private ValueType value;
        public ViewType view;

        public LinearLayout.LayoutParams defaultLayoutParams() {
            return null;
        }

        public abstract ViewType inflate();

        public void updateError() {
        }

        public abstract void updateView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps);

        public CompView(FormFragment formFragment, LinearLayout parent, WorkflowModule.Properties.Section.Component component) {
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(component, "component");
            this.this$0 = formFragment;
            this.parent = parent;
            this.component = component;
            this.onChangeEnabled = true;
            this.onChangeFlow = StateFlowKt.MutableStateFlow(null);
            this.compLogTag = LazyKt.lazy(new Function0<String>(this) { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$CompView$compLogTag$2
                final /* synthetic */ FormFragment.CompView<ViewType, ValueType> this$0;

                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                    this.this$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return this.this$0.getComponent().getId() + '(' + this.this$0.getComponent().getType() + ')';
                }
            });
        }

        public LinearLayout getParent() {
            return this.parent;
        }

        public WorkflowModule.Properties.Section.Component getComponent() {
            return this.component;
        }

        public final ViewType getView() {
            ViewType viewtype = this.view;
            if (viewtype != null) {
                return viewtype;
            }
            Intrinsics.throwUninitializedPropertyAccessException(ViewHierarchyConstants.VIEW_KEY);
            return null;
        }

        public final void setView(ViewType viewtype) {
            Intrinsics.checkNotNullParameter(viewtype, "<set-?>");
            this.view = viewtype;
        }

        public final List<CompView<? extends View, ? extends Object>> getChildCompViews() {
            return this.childCompViews;
        }

        public final void setChildCompViews(List<? extends CompView<? extends View, ? extends Object>> list) {
            this.childCompViews = list;
        }

        /* renamed from: isValid, reason: from getter */
        public final boolean getIsValid() {
            return this.isValid;
        }

        public final void setValid(boolean z) {
            updateIsValid(z);
            this.isValid = z;
        }

        public final ValueType getValue() {
            return this.value;
        }

        public final void setValue(ValueType valuetype) {
            this.value = valuetype;
        }

        public final boolean getSelected() {
            return this.selected;
        }

        public final void setSelected(boolean z) {
            this.selected = z;
        }

        public final String getErrorMessage() {
            return this.errorMessage;
        }

        public final void setErrorMessage(String str) {
            this.errorMessage = str;
        }

        /* renamed from: getOnChangeEnabled$hyperkyc_release, reason: from getter */
        public final boolean getOnChangeEnabled() {
            return this.onChangeEnabled;
        }

        public final void setOnChangeEnabled$hyperkyc_release(boolean z) {
            this.onChangeEnabled = z;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final String getCompLogTag() {
            return (String) this.compLogTag.getValue();
        }

        public int topOffset() {
            return ViewExtsKt.getDp(0);
        }

        public final ValueType getComponentValue$hyperkyc_release() {
            String nullIfBlank = CoreExtsKt.nullIfBlank(FormFragment.stringInjectFromVariables$hyperkyc_release$default(this.this$0, this.this$0.getModuleId$hyperkyc_release() + FilenameUtils.EXTENSION_SEPARATOR + getComponent().getId() + ".value", false, 1, null));
            Object value = getComponent().getValue();
            String nullIfBlank2 = CoreExtsKt.nullIfBlank(value != null ? FormFragment.stringInjectFromVariablesForAny$hyperkyc_release$default(this.this$0, value, false, 1, null) : null);
            if (nullIfBlank == null) {
                nullIfBlank = nullIfBlank2;
            }
            if (!Intrinsics.areEqual(getComponent().getType(), WorkflowModule.Properties.Section.Component.Type.CHECKBOX)) {
                return (ValueType) nullIfBlank;
            }
            if (nullIfBlank != null) {
                return (ValueType) this.this$0.asBoolean$hyperkyc_release(nullIfBlank, false);
            }
            return null;
        }

        public static /* synthetic */ void updateComponent$hyperkyc_release$default(CompView compView, WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProperties, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateComponent");
            }
            if ((i & 1) != 0) {
                reloadProperties = null;
            }
            compView.updateComponent$hyperkyc_release(reloadProperties);
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x0055, code lost:
        
            if (r1 != null) goto L18;
         */
        /* JADX WARN: Removed duplicated region for block: B:104:0x0303  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void updateComponent$hyperkyc_release(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
            String str;
            Object m1202constructorimpl;
            String str2;
            String str3;
            Matcher matcher;
            String className;
            String className2;
            CharSequence charSequence;
            String str4;
            String str5;
            String str6;
            Object m1202constructorimpl2;
            String str7;
            String className3;
            String substringAfterLast$default;
            String className4;
            if (this.view != null) {
                HyperLogger.Level level = HyperLogger.Level.DEBUG;
                HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null) {
                    charSequence = "co.hyperverge";
                    str4 = "N/A";
                    str5 = "Throwable().stackTrace";
                } else {
                    charSequence = "co.hyperverge";
                    str4 = "N/A";
                    str5 = "Throwable().stackTrace";
                    str6 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                }
                Class<?> cls = getClass();
                str6 = cls != null ? cls.getCanonicalName() : null;
                if (str6 == null) {
                    str6 = str4;
                }
                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                if (matcher2.find()) {
                    str6 = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
                }
                if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str6 = str6.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb.append(str6);
                sb.append(" - ");
                String str8 = getCompLogTag() + " updateComponent() called, reloadProps: " + reloadProps;
                if (str8 == null) {
                    str8 = "null ";
                }
                sb.append(str8);
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String packageName = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName, charSequence, false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace2, str5);
                            StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                            if (stackTraceElement2 == null || (className3 = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls2 = getClass();
                                String canonicalName = cls2 != null ? cls2.getCanonicalName() : null;
                                str7 = canonicalName == null ? str4 : canonicalName;
                            } else {
                                str7 = substringAfterLast$default;
                            }
                            Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                            if (matcher3.find()) {
                                str7 = matcher3.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str7, "replaceAll(\"\")");
                            }
                            if (str7.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str7 = str7.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb2 = new StringBuilder();
                            String str9 = getCompLogTag() + " updateComponent() called, reloadProps: " + reloadProps;
                            sb2.append(str9 != null ? str9 : "null ");
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(3, str7, sb2.toString());
                        }
                    }
                }
                updateCommonProperties(reloadProps);
                updateView(reloadProps);
                return;
            }
            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
            HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb3 = new StringBuilder();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement3 == null || (className2 = stackTraceElement3.getClassName()) == null || (str = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls3 = getClass();
                String canonicalName2 = cls3 != null ? cls3.getCanonicalName() : null;
                str = canonicalName2 == null ? "N/A" : canonicalName2;
            }
            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
            if (matcher4.find()) {
                str = matcher4.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
            }
            if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str = str.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb3.append(str);
            sb3.append(" - ");
            sb3.append("view is not initialised");
            sb3.append(' ');
            sb3.append("");
            companion4.log(level2, sb3.toString());
            if (CoreExtsKt.isRelease()) {
                return;
            }
            try {
                Result.Companion companion5 = Result.INSTANCE;
                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
            } catch (Throwable th2) {
                Result.Companion companion6 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th2));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName2 = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                    if (stackTraceElement4 == null || (className = stackTraceElement4.getClassName()) == null) {
                        str2 = null;
                    } else {
                        str2 = null;
                        String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default2 != null) {
                            str3 = substringAfterLast$default2;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                            if (matcher.find()) {
                                str3 = matcher.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                            }
                            if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str3 = str3.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            Log.println(5, str3, "view is not initialised ");
                        }
                    }
                    Class<?> cls4 = getClass();
                    String canonicalName3 = cls4 != null ? cls4.getCanonicalName() : str2;
                    str3 = canonicalName3 == null ? "N/A" : canonicalName3;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                    if (matcher.find()) {
                    }
                    if (str3.length() > 23) {
                        str3 = str3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(5, str3, "view is not initialised ");
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:11:0x00a5  */
        /* JADX WARN: Removed duplicated region for block: B:16:0x00c2  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x00d1  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x00e8  */
        /* JADX WARN: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:9:0x008d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final Unit updateCommonProperties(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
            Boolean asBoolean$hyperkyc_release;
            Boolean asBoolean$hyperkyc_release2;
            Boolean asBoolean$hyperkyc_release3;
            Boolean bool;
            Object obj;
            View view = getView();
            FormFragment formFragment = this.this$0;
            if (reloadProps != null) {
                bool = formFragment.asBoolean$hyperkyc_release(reloadProps.getEnabled(), null);
                asBoolean$hyperkyc_release = formFragment.asBoolean$hyperkyc_release(reloadProps.getVisible(), null);
                asBoolean$hyperkyc_release3 = formFragment.asBoolean$hyperkyc_release(reloadProps.getRequired(), null);
                asBoolean$hyperkyc_release2 = formFragment.asBoolean$hyperkyc_release(reloadProps.getSelected(), null);
                if (Intrinsics.areEqual(getComponent().getType(), WorkflowModule.Properties.Section.Component.Type.LIST)) {
                    Object value = reloadProps.getValue();
                    if (value != null) {
                        obj = formFragment.anyInjectFromVariablesForAny$hyperkyc_release(value);
                    }
                } else {
                    Object value2 = reloadProps.getValue();
                    if (value2 != null) {
                        obj = FormFragment.stringInjectFromVariablesForAny$hyperkyc_release$default(formFragment, value2, false, 1, null);
                    }
                }
                if (bool != null) {
                    Boolean valueOf = Boolean.valueOf(bool.booleanValue());
                    view.setEnabled(valueOf.booleanValue());
                    Unit unit = Unit.INSTANCE;
                    updateData("enabled", valueOf);
                }
                if (asBoolean$hyperkyc_release != null) {
                    Boolean valueOf2 = Boolean.valueOf(asBoolean$hyperkyc_release.booleanValue());
                    view.setVisibility(valueOf2.booleanValue() ? 0 : 8);
                    Unit unit2 = Unit.INSTANCE;
                    updateData(FormFragment.KEY_VISIBLE, valueOf2);
                }
                if (asBoolean$hyperkyc_release3 != null) {
                    updateData(FormFragment.KEY_REQUIRED, Boolean.valueOf(asBoolean$hyperkyc_release3.booleanValue()));
                }
                if (asBoolean$hyperkyc_release2 != null) {
                    Boolean valueOf3 = Boolean.valueOf(asBoolean$hyperkyc_release2.booleanValue());
                    this.selected = valueOf3.booleanValue();
                    Unit unit3 = Unit.INSTANCE;
                    updateData(FormFragment.KEY_SELECTED, valueOf3);
                }
                if (obj != null) {
                    return null;
                }
                updateValue(obj);
                return Unit.INSTANCE;
            }
            Boolean asBoolean$hyperkyc_release4 = formFragment.asBoolean$hyperkyc_release(getComponent().getEnabled(), true);
            asBoolean$hyperkyc_release = formFragment.asBoolean$hyperkyc_release(getComponent().getVisible(), true);
            asBoolean$hyperkyc_release2 = formFragment.asBoolean$hyperkyc_release(getComponent().getSelected(), false);
            asBoolean$hyperkyc_release3 = formFragment.asBoolean$hyperkyc_release(getComponent().getRequired(), true);
            bool = asBoolean$hyperkyc_release4;
            obj = null;
            if (bool != null) {
            }
            if (asBoolean$hyperkyc_release != null) {
            }
            if (asBoolean$hyperkyc_release3 != null) {
            }
            if (asBoolean$hyperkyc_release2 != null) {
            }
            if (obj != null) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void attachDynamicHandlers() {
            WorkflowModule.Properties.Section.Component.DynamicHandlers dynamicHandlers;
            if (this.this$0.isAdded() && (dynamicHandlers = getComponent().getDynamicHandlers()) != null) {
                FormFragment formFragment = this.this$0;
                BuildersKt__Builders_commonKt.launch$default(formFragment.getLifecycleScope(), null, null, new FormFragment$CompView$attachDynamicHandlers$1$1(this, dynamicHandlers, formFragment, null), 3, null);
            }
        }

        static /* synthetic */ void addToParent$default(CompView compView, LinearLayout linearLayout, View view, Margin margin, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addToParent");
            }
            if ((i & 4) != 0) {
                margin = null;
            }
            compView.addToParent(linearLayout, view, margin);
        }

        private final int getDimensions(String str, int i) {
            if (Intrinsics.areEqual(str, WorkflowModule.Properties.Section.Component.Dimensions.FILL)) {
                return -1;
            }
            if (Intrinsics.areEqual(str, WorkflowModule.Properties.Section.Component.Dimensions.WRAP)) {
                return -2;
            }
            return str != null && TextUtils.isDigitsOnly(str) ? ViewExtsKt.getDp(Integer.parseInt(str)) : i;
        }

        public static /* synthetic */ void processHandler$hyperkyc_release$default(CompView compView, WorkflowModule.Properties.Section.Component.Handler handler, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: processHandler");
            }
            if ((i & 2) != 0) {
                z = true;
            }
            compView.processHandler$hyperkyc_release(handler, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:158:0x0134  */
        /* JADX WARN: Removed duplicated region for block: B:161:0x013d  */
        /* JADX WARN: Removed duplicated region for block: B:187:0x0208  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0216  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x034d  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x0386  */
        /* JADX WARN: Type inference failed for: r4v14 */
        /* JADX WARN: Type inference failed for: r4v15 */
        /* JADX WARN: Type inference failed for: r4v16, types: [java.lang.Object] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void processHandler$hyperkyc_release(WorkflowModule.Properties.Section.Component.Handler handler, boolean executeNextStep) {
            String canonicalName;
            String json;
            String str;
            Object m1202constructorimpl;
            String str2;
            String str3;
            String canonicalName2;
            String json2;
            String className;
            Collection reloadComponents;
            WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProperties;
            WorkflowModule workflowModule;
            WorkflowModule workflowModule2;
            String canonicalName3;
            Object m1202constructorimpl2;
            String str4;
            String str5;
            Matcher matcher;
            String className2;
            String className3;
            String className4;
            Intrinsics.checkNotNullParameter(handler, "handler");
            FormFragment formFragment = this.this$0;
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
            StringBuilder sb2 = new StringBuilder();
            sb2.append(getCompLogTag());
            sb2.append(" processHandler() called in ");
            sb2.append(formFragment.getModuleId$hyperkyc_release());
            sb2.append(" module with value: ");
            sb2.append(this.value);
            sb2.append(" and handler = [");
            json = new Gson().toJson(handler);
            sb2.append(json);
            sb2.append("], executeNextStep: ");
            sb2.append(executeNextStep);
            String sb3 = sb2.toString();
            if (sb3 == null) {
                sb3 = "null ";
            }
            sb.append(sb3);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
                str2 = "packageName";
                str3 = "getInitialApplication";
                str = " - ";
            } else {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    str = " - ";
                } catch (Throwable th) {
                    th = th;
                    str = " - ";
                }
                try {
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } catch (Throwable th2) {
                    th = th2;
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    }
                    String packageName = (String) m1202constructorimpl;
                    if (CoreExtsKt.isDebug()) {
                    }
                    if (!ActivityExtsKt.isFragmentAdded(this.this$0)) {
                    }
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName2 = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    str2 = "packageName";
                    str3 = "getInitialApplication";
                } else {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    str2 = "packageName";
                    str3 = "getInitialApplication";
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls2 = getClass();
                            canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            if (canonicalName2 == null) {
                                canonicalName2 = "N/A";
                            }
                        }
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                        if (matcher3.find()) {
                            canonicalName2 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                        }
                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName2 = canonicalName2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb4 = new StringBuilder();
                        StringBuilder sb5 = new StringBuilder();
                        sb5.append(getCompLogTag());
                        sb5.append(" processHandler() called in ");
                        sb5.append(formFragment.getModuleId$hyperkyc_release());
                        sb5.append(" module with value: ");
                        sb5.append(this.value);
                        sb5.append(" and handler = [");
                        json2 = new Gson().toJson(handler);
                        sb5.append(json2);
                        sb5.append("], executeNextStep: ");
                        sb5.append(executeNextStep);
                        String sb6 = sb5.toString();
                        if (sb6 == null) {
                            sb6 = "null ";
                        }
                        sb4.append(sb6);
                        sb4.append(' ');
                        sb4.append("");
                        Log.println(3, canonicalName2, sb4.toString());
                    }
                }
            }
            if (!ActivityExtsKt.isFragmentAdded(this.this$0)) {
                if (handler.getNextStep() != null && executeNextStep) {
                    List<WorkflowModule> modules = this.this$0.getMainVM$hyperkyc_release().getHyperKycConfig$hyperkyc_release().getWorkflowConfig$hyperkyc_release().getModules();
                    if (modules != null) {
                        FormFragment formFragment2 = this.this$0;
                        Iterator it = modules.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                workflowModule2 = it.next();
                                if (Intrinsics.areEqual(((WorkflowModule) workflowModule2).getId(), formFragment2.getModuleId$hyperkyc_release())) {
                                    break;
                                }
                            } else {
                                workflowModule2 = 0;
                                break;
                            }
                        }
                        workflowModule = workflowModule2;
                    } else {
                        workflowModule = null;
                    }
                    if (workflowModule != null) {
                        workflowModule.setNext(handler.getNextStep());
                    }
                    this.this$0.getMainVM$hyperkyc_release().flowForward();
                }
                Map<String, WorkflowModule.Properties.Section.Component.Handler.ReloadProperties> reload = handler.getReload();
                if (reload == null || (reloadComponents = reload.keySet()) == null) {
                    reloadComponents = handler.getReloadComponents();
                }
                final Collection collection = reloadComponents;
                if (collection == null || collection.isEmpty()) {
                    return;
                }
                List list = this.this$0.compViews;
                if (list == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("compViews");
                    list = null;
                }
                ArrayList arrayList = new ArrayList();
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    CollectionsKt.addAll(arrayList, flatten((CompView) it2.next()));
                }
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : arrayList) {
                    if (collection.contains(((CompView) obj).getComponent().getId())) {
                        arrayList2.add(obj);
                    }
                }
                for (CompView compView : CollectionsKt.sortedWith(arrayList2, new Comparator() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$CompView$processHandler$$inlined$sortedBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Integer.valueOf(CollectionsKt.indexOf(collection, ((FormFragment.CompView) t).getComponent().getId())), Integer.valueOf(CollectionsKt.indexOf(collection, ((FormFragment.CompView) t2).getComponent().getId())));
                    }
                })) {
                    Map<String, WorkflowModule.Properties.Section.Component.Handler.ReloadProperties> reload2 = handler.getReload();
                    compView.updateComponent$hyperkyc_release((reload2 == null || (reloadProperties = reload2.get(compView.getComponent().getId())) == null) ? null : reloadProperties.nullIfEmpty());
                }
                return;
            }
            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
            HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb7 = new StringBuilder();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls3 = getClass();
                canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                if (canonicalName3 == null) {
                    canonicalName3 = "N/A";
                }
            }
            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
            if (matcher4.find()) {
                canonicalName3 = matcher4.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
            }
            if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                canonicalName3 = canonicalName3.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb7.append(canonicalName3);
            sb7.append(str);
            sb7.append("processHandler: fragment not added, skipping handler");
            sb7.append(' ');
            sb7.append("");
            companion4.log(level2, sb7.toString());
            if (CoreExtsKt.isRelease()) {
                return;
            }
            try {
                Result.Companion companion5 = Result.INSTANCE;
                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod(str3, new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
            } catch (Throwable th3) {
                Result.Companion companion6 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                m1202constructorimpl2 = "";
            }
            String str6 = (String) m1202constructorimpl2;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(str6, str2);
                if (StringsKt.contains$default((CharSequence) str6, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                    if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                        str4 = null;
                    } else {
                        str4 = null;
                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default != null) {
                            str5 = substringAfterLast$default;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                            if (matcher.find()) {
                                str5 = matcher.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                            }
                            if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str5 = str5.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            Log.println(3, str5, "processHandler: fragment not added, skipping handler ");
                        }
                    }
                    Class<?> cls4 = getClass();
                    String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str4;
                    str5 = canonicalName4 == null ? "N/A" : canonicalName4;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                    if (matcher.find()) {
                    }
                    if (str5.length() > 23) {
                        str5 = str5.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str5, "processHandler: fragment not added, skipping handler ");
                }
            }
        }

        public static /* synthetic */ void updateStyling$default(CompView compView, TextInputLayout textInputLayout, EditText editText, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateStyling");
            }
            if ((i & 1) != 0) {
                editText = null;
            }
            compView.updateStyling(textInputLayout, editText);
        }

        private final List<CompView<?, ?>> flatten(CompView<?, ?> compView) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(compView);
            List<? extends CompView<? extends View, ? extends Object>> list = compView.childCompViews;
            if (list != null) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.addAll(compView.flatten((CompView) it.next()));
                }
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x014e, code lost:
        
            if (r0 == null) goto L55;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r17v0, types: [co.hyperverge.hyperkyc.ui.form.FormFragment$CompView<ViewType extends android.view.View, ValueType>, java.lang.Object, co.hyperverge.hyperkyc.ui.form.FormFragment$CompView] */
        /* JADX WARN: Type inference failed for: r2v0, types: [ValueType, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v6 */
        /* JADX WARN: Type inference failed for: r2v8 */
        /* JADX WARN: Type inference failed for: r3v11, types: [java.lang.StringBuilder] */
        /* JADX WARN: Type inference failed for: r5v14, types: [java.lang.StringBuilder] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void updateDefaultValue(ValueType v) {
            String canonicalName;
            Object m1202constructorimpl;
            String canonicalName2;
            String className;
            String className2;
            ValueType v2 = v;
            Intrinsics.checkNotNullParameter(v2, "v");
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
            String str2 = getCompLogTag() + " updateDefaultValue() called with: v = [" + v2 + ']';
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
                        String str3 = getCompLogTag() + " updateDefaultValue() called with: v = [" + v2 + ']';
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
            updateData("value", v2);
            this.value = v2;
            if (v2 instanceof String) {
                if (!(((CharSequence) v2).length() > 0)) {
                    v2 = (ValueType) null;
                }
                if (v2 != 0) {
                    validate((String) v2);
                    return;
                }
                return;
            }
            if (v2 instanceof Boolean) {
                String yesNo$hyperkyc_release = this.this$0.toYesNo$hyperkyc_release(((Boolean) v2).booleanValue());
                String str4 = yesNo$hyperkyc_release.length() > 0 ? yesNo$hyperkyc_release : null;
                if (str4 != null) {
                    validate(str4);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:107:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:115:0x0123  */
        /* JADX WARN: Removed duplicated region for block: B:118:0x012c  */
        /* JADX WARN: Removed duplicated region for block: B:144:0x01ea  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0262  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x02a6  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x02c4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:66:0x0362  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x03a5  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x03a8  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x0245  */
        /* JADX WARN: Removed duplicated region for block: B:92:0x024d  */
        /* JADX WARN: Removed duplicated region for block: B:93:0x0250  */
        /* JADX WARN: Removed duplicated region for block: B:94:0x024a  */
        /* JADX WARN: Removed duplicated region for block: B:97:0x03c9  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void updateValue(ValueType v) {
            String canonicalName;
            String str;
            Object m1202constructorimpl;
            CharSequence charSequence;
            String str2;
            String canonicalName2;
            String className;
            ValueType valuetype;
            StackTraceElement stackTraceElement;
            String str3;
            Matcher matcher;
            String str4;
            Object m1202constructorimpl2;
            String str5;
            String str6;
            Matcher matcher2;
            String className2;
            String className3;
            String className4;
            CompView<ViewType, ValueType> compView = this;
            Intrinsics.checkNotNullParameter(v, "v");
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement2 == null || (className4 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls = getClass();
                canonicalName = cls != null ? cls.getCanonicalName() : null;
                if (canonicalName == null) {
                    canonicalName = "N/A";
                }
            }
            Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
            if (matcher3.find()) {
                canonicalName = matcher3.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
            }
            if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                canonicalName = canonicalName.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(canonicalName);
            sb.append(" - ");
            String str7 = getCompLogTag() + " updateValue() called with: oldValue: " + compView.value + ", newValue = " + v + ", onChangeEnabled: " + compView.onChangeEnabled;
            if (str7 == null) {
                str7 = "null ";
            }
            sb.append(str7);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
                charSequence = "co.hyperverge";
                str2 = "packageName";
                str = "null ";
            } else {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    str = "null ";
                } catch (Throwable th) {
                    th = th;
                    str = "null ";
                }
                try {
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } catch (Throwable th2) {
                    th = th2;
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    }
                    String packageName = (String) m1202constructorimpl;
                    if (CoreExtsKt.isDebug()) {
                    }
                    compView.updateData("value", v);
                    valuetype = compView.value;
                    compView.value = v;
                    if (getComponent().getOnChange() != null) {
                        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb2 = new StringBuilder();
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement != null) {
                        }
                        Class<?> cls2 = getClass();
                        if (cls2 == null) {
                        }
                        if (r1 != null) {
                        }
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                        if (matcher.find()) {
                        }
                        if (str3.length() > 23) {
                            str3 = str3.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb2.append(str3);
                        sb2.append(" - ");
                        str4 = getCompLogTag() + " updateValue(): emitting oldValue: " + valuetype + ", newValue = " + v;
                        if (str4 == null) {
                        }
                        sb2.append(str4);
                        sb2.append(' ');
                        sb2.append("");
                        companion4.log(level2, sb2.toString());
                        if (!CoreExtsKt.isRelease()) {
                        }
                        compView = this;
                        compView.onChangeFlow.tryEmit(v);
                    }
                    if (Intrinsics.areEqual(v, valuetype)) {
                    }
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName2 = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    charSequence = "co.hyperverge";
                    str2 = "packageName";
                } else {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    charSequence = "co.hyperverge";
                    str2 = "packageName";
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls3 = getClass();
                            canonicalName2 = cls3 != null ? cls3.getCanonicalName() : null;
                            if (canonicalName2 == null) {
                                canonicalName2 = "N/A";
                            }
                        }
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                        if (matcher4.find()) {
                            canonicalName2 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                        }
                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName2 = canonicalName2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb3 = new StringBuilder();
                        String str8 = getCompLogTag() + " updateValue() called with: oldValue: " + compView.value + ", newValue = " + v + ", onChangeEnabled: " + compView.onChangeEnabled;
                        if (str8 == null) {
                            str8 = str;
                        }
                        sb3.append(str8);
                        sb3.append(' ');
                        sb3.append("");
                        Log.println(3, canonicalName2, sb3.toString());
                    }
                }
            }
            compView.updateData("value", v);
            valuetype = compView.value;
            compView.value = v;
            if (getComponent().getOnChange() != null && compView.onChangeEnabled && !Intrinsics.areEqual(v, valuetype)) {
                HyperLogger.Level level22 = HyperLogger.Level.DEBUG;
                HyperLogger companion42 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb22 = new StringBuilder();
                StackTraceElement[] stackTrace22 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace22, "Throwable().stackTrace");
                stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace22);
                if (stackTraceElement != null || (className3 = stackTraceElement.getClassName()) == null || (str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls22 = getClass();
                    String canonicalName3 = cls22 == null ? cls22.getCanonicalName() : null;
                    str3 = canonicalName3 != null ? "N/A" : canonicalName3;
                }
                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                if (matcher.find()) {
                    str3 = matcher.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                }
                if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str3 = str3.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb22.append(str3);
                sb22.append(" - ");
                str4 = getCompLogTag() + " updateValue(): emitting oldValue: " + valuetype + ", newValue = " + v;
                if (str4 == null) {
                    str4 = str;
                }
                sb22.append(str4);
                sb22.append(' ');
                sb22.append("");
                companion42.log(level22, sb22.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion5 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th3) {
                        Result.Companion companion6 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String str9 = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(str9, str2);
                        if (StringsKt.contains$default((CharSequence) str9, charSequence, false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                str5 = null;
                            } else {
                                str5 = null;
                                String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default != null) {
                                    str6 = substringAfterLast$default;
                                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                                    if (matcher2.find()) {
                                        str6 = matcher2.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
                                    }
                                    if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str6 = str6.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb4 = new StringBuilder();
                                    String str10 = getCompLogTag() + " updateValue(): emitting oldValue: " + valuetype + ", newValue = " + v;
                                    sb4.append(str10 != null ? str : str10);
                                    sb4.append(' ');
                                    sb4.append("");
                                    Log.println(3, str6, sb4.toString());
                                }
                            }
                            Class<?> cls4 = getClass();
                            String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str5;
                            str6 = canonicalName4 == null ? "N/A" : canonicalName4;
                            matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                            if (matcher2.find()) {
                            }
                            if (str6.length() > 23) {
                                str6 = str6.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb42 = new StringBuilder();
                            String str102 = getCompLogTag() + " updateValue(): emitting oldValue: " + valuetype + ", newValue = " + v;
                            sb42.append(str102 != null ? str : str102);
                            sb42.append(' ');
                            sb42.append("");
                            Log.println(3, str6, sb42.toString());
                        }
                    }
                }
                compView = this;
                compView.onChangeFlow.tryEmit(v);
            }
            if (Intrinsics.areEqual(v, valuetype)) {
                if (v instanceof String) {
                    compView.validate((String) v);
                } else if (v instanceof Boolean) {
                    compView.validate(compView.this$0.toYesNo$hyperkyc_release(((Boolean) v).booleanValue()));
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:54:0x013d, code lost:
        
            if (r0 == null) goto L55;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void postInflate() {
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
            String str2 = getCompLogTag() + " postInflate() called ";
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
                        String str3 = getCompLogTag() + " postInflate() called ";
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
            attachDynamicHandlers();
            BuildersKt__Builders_commonKt.launch$default(this.this$0.getLifecycleScope(), null, null, new FormFragment$CompView$postInflate$2(this, this.this$0, null), 3, null);
        }

        /* JADX WARN: Code restructure failed: missing block: B:54:0x0149, code lost:
        
            if (r0 == null) goto L55;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void render(Margin margin) {
            String canonicalName;
            Object m1202constructorimpl;
            String canonicalName2;
            String className;
            String className2;
            Intrinsics.checkNotNullParameter(margin, "margin");
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
            String str2 = getCompLogTag() + " render() called with margin: " + margin;
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
                        String str3 = getCompLogTag() + " render() called with margin: " + margin;
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
            setView(inflate());
            postInflate();
            updateComponent$hyperkyc_release$default(this, null, 1, null);
            addToParent(getParent(), getView(), margin);
        }

        /* JADX WARN: Code restructure failed: missing block: B:57:0x0151, code lost:
        
            if (r0 != null) goto L55;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:23:0x01e9  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0245  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0271  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0287  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x025b  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0222  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x0178  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x01bf  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final void addToParent(LinearLayout parent, View view, Margin margin) {
            String canonicalName;
            Object m1202constructorimpl;
            String str;
            String str2;
            String str3;
            Matcher matcher;
            String str4;
            String className;
            HSUIConfig uiConfigData;
            DynamicFormUIConfig dynamicFormUIConfig;
            HSUIConfig uiConfigData2;
            DynamicFormUIConfig dynamicFormUIConfig2;
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
            String str5 = getCompLogTag() + " addToParent() called with parent = " + parent + ", view = " + view + ", margin = " + margin;
            if (str5 == null) {
                str5 = "null ";
            }
            sb.append(str5);
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
                        if (str2 == null) {
                            str3 = "N/A";
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
                            str4 = getCompLogTag() + " addToParent() called with parent = " + parent + ", view = " + view + ", margin = " + margin;
                            if (str4 == null) {
                                str4 = "null ";
                            }
                            sb2.append(str4);
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(3, str3, sb2.toString());
                            view.setTag(getComponent().getId());
                            if (getComponent().getWidth() != null) {
                                parent.addView(view, new LinearLayout.LayoutParams(getDimensions(getComponent().getWidth(), -1), getDimensions(getComponent().getHeight(), -2), Intrinsics.areEqual(getComponent().getWidth(), WorkflowModule.Properties.Section.Component.Dimensions.FILL) ? 1.0f : 0.0f));
                            } else {
                                LinearLayout.LayoutParams defaultLayoutParams = defaultLayoutParams();
                                if (defaultLayoutParams != null) {
                                    parent.addView(view, defaultLayoutParams);
                                } else {
                                    parent.addView(view);
                                }
                            }
                            DynamicFormUtils dynamicFormUtils$hyperkyc_release = this.this$0.getDynamicFormUtils$hyperkyc_release();
                            uiConfigData = this.this$0.getMainVM$hyperkyc_release().getUiConfigData();
                            if (uiConfigData != null) {
                                String moduleId = this.this$0.getModuleId$hyperkyc_release();
                                Intrinsics.checkNotNullExpressionValue(moduleId, "moduleId");
                                dynamicFormUIConfig = uiConfigData.getDynamicFormUIConfig(moduleId, getComponent().getId());
                            } else {
                                dynamicFormUIConfig = str;
                            }
                            dynamicFormUtils$hyperkyc_release.applyMargin(view, dynamicFormUIConfig, margin);
                            DynamicFormUtils dynamicFormUtils$hyperkyc_release2 = this.this$0.getDynamicFormUtils$hyperkyc_release();
                            uiConfigData2 = this.this$0.getMainVM$hyperkyc_release().getUiConfigData();
                            if (uiConfigData2 != null) {
                                String moduleId2 = this.this$0.getModuleId$hyperkyc_release();
                                Intrinsics.checkNotNullExpressionValue(moduleId2, "moduleId");
                                dynamicFormUIConfig2 = uiConfigData2.getDynamicFormUIConfig(moduleId2, getComponent().getId());
                            } else {
                                dynamicFormUIConfig2 = str;
                            }
                            dynamicFormUtils$hyperkyc_release2.applyPadding(view, dynamicFormUIConfig2);
                        }
                        str3 = str2;
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                        if (matcher.find()) {
                        }
                        if (str3.length() > 23) {
                            str3 = str3.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb22 = new StringBuilder();
                        str4 = getCompLogTag() + " addToParent() called with parent = " + parent + ", view = " + view + ", margin = " + margin;
                        if (str4 == null) {
                        }
                        sb22.append(str4);
                        sb22.append(' ');
                        sb22.append("");
                        Log.println(3, str3, sb22.toString());
                        view.setTag(getComponent().getId());
                        if (getComponent().getWidth() != null) {
                        }
                        DynamicFormUtils dynamicFormUtils$hyperkyc_release3 = this.this$0.getDynamicFormUtils$hyperkyc_release();
                        uiConfigData = this.this$0.getMainVM$hyperkyc_release().getUiConfigData();
                        if (uiConfigData != null) {
                        }
                        dynamicFormUtils$hyperkyc_release3.applyMargin(view, dynamicFormUIConfig, margin);
                        DynamicFormUtils dynamicFormUtils$hyperkyc_release22 = this.this$0.getDynamicFormUtils$hyperkyc_release();
                        uiConfigData2 = this.this$0.getMainVM$hyperkyc_release().getUiConfigData();
                        if (uiConfigData2 != null) {
                        }
                        dynamicFormUtils$hyperkyc_release22.applyPadding(view, dynamicFormUIConfig2);
                    }
                }
            }
            str = null;
            view.setTag(getComponent().getId());
            if (getComponent().getWidth() != null) {
            }
            DynamicFormUtils dynamicFormUtils$hyperkyc_release32 = this.this$0.getDynamicFormUtils$hyperkyc_release();
            uiConfigData = this.this$0.getMainVM$hyperkyc_release().getUiConfigData();
            if (uiConfigData != null) {
            }
            dynamicFormUtils$hyperkyc_release32.applyMargin(view, dynamicFormUIConfig, margin);
            DynamicFormUtils dynamicFormUtils$hyperkyc_release222 = this.this$0.getDynamicFormUtils$hyperkyc_release();
            uiConfigData2 = this.this$0.getMainVM$hyperkyc_release().getUiConfigData();
            if (uiConfigData2 != null) {
            }
            dynamicFormUtils$hyperkyc_release222.applyPadding(view, dynamicFormUIConfig2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:101:0x03af, code lost:
        
            if (r0 != null) goto L149;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x02a7, code lost:
        
            if (r1 != null) goto L107;
         */
        /* JADX WARN: Removed duplicated region for block: B:136:0x048c  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x01f9  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x04bf  */
        /* JADX WARN: Removed duplicated region for block: B:79:0x0437 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:83:0x0452 A[LOOP:0: B:44:0x021f->B:83:0x0452, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:84:0x0450 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final boolean validate(String str) {
            String canonicalName;
            Class<?> cls;
            Object m1202constructorimpl;
            CharSequence charSequence;
            String str2;
            String canonicalName2;
            Class<?> cls2;
            String className;
            List<WorkflowModule.Properties.Section.Component.Validation> validation;
            CompView<ViewType, ValueType> compView;
            boolean z;
            boolean z2;
            boolean z3;
            WorkflowModule.Properties.Section.Component.Handler onValidated;
            boolean z4;
            boolean eval$default;
            String str3;
            FormFragment formFragment;
            String str4;
            String str5;
            Object m1202constructorimpl2;
            String str6;
            String str7;
            Class<?> cls3;
            String className2;
            FormFragment formFragment2;
            boolean z5;
            String str8;
            Class<?> cls4;
            String className3;
            String className4;
            CompView<ViewType, ValueType> compView2 = this;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            String str9 = "Throwable().stackTrace";
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                canonicalName = (str == null || (cls = str.getClass()) == null) ? null : cls.getCanonicalName();
                if (canonicalName == null) {
                    canonicalName = "N/A";
                }
            }
            Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
            if (matcher.find()) {
                canonicalName = matcher.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
            }
            Unit unit = Unit.INSTANCE;
            if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                canonicalName = canonicalName.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(canonicalName);
            sb.append(" - ");
            String str10 = getCompLogTag() + ' ' + str + ".validate() called";
            if (str10 == null) {
                str10 = "null ";
            }
            sb.append(str10);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            String str11 = "packageName";
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
                    str2 = "N/A";
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            canonicalName2 = (str == null || (cls2 = str.getClass()) == null) ? null : cls2.getCanonicalName();
                            if (canonicalName2 == null) {
                                canonicalName2 = str2;
                            }
                        }
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                        if (matcher2.find()) {
                            canonicalName2 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                        }
                        Unit unit2 = Unit.INSTANCE;
                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName2 = canonicalName2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str12 = getCompLogTag() + ' ' + str + ".validate() called";
                        if (str12 == null) {
                            str12 = "null ";
                        }
                        sb2.append(str12);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    }
                    boolean areEqual = Intrinsics.areEqual((Object) compView2.this$0.asBoolean$hyperkyc_release(getComponent().getRequired(), true), (Object) true);
                    boolean z6 = compView2.isValid;
                    validation = getComponent().getValidation();
                    if (validation != null || validation.isEmpty()) {
                        List<WorkflowModule.Properties.Section.Component.Validation> validation2 = getComponent().getValidation();
                        Intrinsics.checkNotNull(validation2);
                        List<WorkflowModule.Properties.Section.Component.Validation> list = validation2;
                        FormFragment formFragment3 = compView2.this$0;
                        if (!(list instanceof Collection) || !list.isEmpty()) {
                            Iterator it = list.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    compView = compView2;
                                    z = z6;
                                    z4 = true;
                                    break;
                                }
                                WorkflowModule.Properties.Section.Component.Validation validation3 = (WorkflowModule.Properties.Section.Component.Validation) it.next();
                                String type = validation3.getType();
                                String value = validation3.getValue();
                                Iterator it2 = it;
                                String errorMsg = validation3.getErrorMsg();
                                if (Intrinsics.areEqual(type, WorkflowModule.Properties.Section.Component.Validation.Type.REGEX)) {
                                    z = z6;
                                    Pattern compile = Pattern.compile(value, 0);
                                    Intrinsics.checkNotNullExpressionValue(compile, "compile(this, flags)");
                                    eval$default = compile.matcher(str).matches();
                                } else {
                                    z = z6;
                                    if (!Intrinsics.areEqual(type, WorkflowModule.Properties.Section.Component.Validation.Type.RULE)) {
                                        throw new NotImplementedError("An operation is not implemented: " + ("invalid validation type: " + type));
                                    }
                                    eval$default = RuleEvaluatorKt.eval$default(formFragment3.stringInjectFromVariables$hyperkyc_release(value, true), false, 1, null);
                                }
                                boolean z7 = eval$default;
                                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                                StringBuilder sb3 = new StringBuilder();
                                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace3, str9);
                                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                                if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                                    str3 = str9;
                                    formFragment = formFragment3;
                                    str4 = str11;
                                } else {
                                    str3 = str9;
                                    formFragment = formFragment3;
                                    str4 = str11;
                                    str5 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                }
                                str5 = (str == null || (cls4 = str.getClass()) == null) ? null : cls4.getCanonicalName();
                                if (str5 == null) {
                                    str5 = str2;
                                }
                                Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                if (matcher3.find()) {
                                    str5 = matcher3.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                                }
                                Unit unit3 = Unit.INSTANCE;
                                if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str5 = str5.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                sb3.append(str5);
                                sb3.append(" - ");
                                String str13 = "validate: showError valid: " + z7 + ",  errorMsg: " + errorMsg;
                                if (str13 == null) {
                                    str13 = "null ";
                                }
                                sb3.append(str13);
                                sb3.append(' ');
                                sb3.append("");
                                companion4.log(level2, sb3.toString());
                                if (!CoreExtsKt.isRelease()) {
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
                                    String str14 = (String) m1202constructorimpl2;
                                    if (CoreExtsKt.isDebug()) {
                                        str6 = str4;
                                        Intrinsics.checkNotNullExpressionValue(str14, str6);
                                        if (!StringsKt.contains$default((CharSequence) str14, charSequence, false, 2, (Object) null)) {
                                            str9 = str3;
                                            if (errorMsg != null) {
                                            }
                                            formFragment2 = formFragment;
                                            z5 = false;
                                            compView = this;
                                            str8 = null;
                                            compView.errorMessage = str8;
                                            if (!z7) {
                                            }
                                        } else {
                                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                            str9 = str3;
                                            Intrinsics.checkNotNullExpressionValue(stackTrace4, str9);
                                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                            if (stackTraceElement4 != null && (className2 = stackTraceElement4.getClassName()) != null) {
                                                str7 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                            }
                                            str7 = (str == null || (cls3 = str.getClass()) == null) ? null : cls3.getCanonicalName();
                                            if (str7 == null) {
                                                str7 = str2;
                                            }
                                            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                                            if (matcher4.find()) {
                                                str7 = matcher4.replaceAll("");
                                                Intrinsics.checkNotNullExpressionValue(str7, "replaceAll(\"\")");
                                            }
                                            Unit unit4 = Unit.INSTANCE;
                                            if (str7.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                str7 = str7.substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                                            }
                                            StringBuilder sb4 = new StringBuilder();
                                            String str15 = "validate: showError valid: " + z7 + ",  errorMsg: " + errorMsg;
                                            if (str15 == null) {
                                                str15 = "null ";
                                            }
                                            sb4.append(str15);
                                            sb4.append(' ');
                                            sb4.append("");
                                            Log.println(3, str7, sb4.toString());
                                            if (errorMsg != null || z7) {
                                                formFragment2 = formFragment;
                                                z5 = false;
                                                compView = this;
                                                str8 = null;
                                            } else {
                                                formFragment2 = formFragment;
                                                z5 = false;
                                                str8 = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment2, errorMsg, false, 1, null);
                                                compView = this;
                                            }
                                            compView.errorMessage = str8;
                                            if (!z7) {
                                                z4 = z5;
                                                break;
                                            }
                                            str11 = str6;
                                            formFragment3 = formFragment2;
                                            compView2 = compView;
                                            it = it2;
                                            z6 = z;
                                        }
                                    }
                                }
                                str9 = str3;
                                str6 = str4;
                                if (errorMsg != null) {
                                }
                                formFragment2 = formFragment;
                                z5 = false;
                                compView = this;
                                str8 = null;
                                compView.errorMessage = str8;
                                if (!z7) {
                                }
                            }
                        } else {
                            compView = compView2;
                            z = z6;
                            z4 = true;
                        }
                        z2 = z4;
                    } else {
                        compView = compView2;
                        z = z6;
                        if (areEqual) {
                            z2 = !StringsKt.isBlank(str);
                            compView.errorMessage = !z2 ? "This field cannot be blank" : null;
                        } else {
                            z2 = true;
                        }
                    }
                    compView.setValid(z2);
                    z3 = compView.isValid;
                    if (!z3 || (!z3 && z)) {
                        updateError();
                        onValidated = getComponent().getOnValidated();
                        if (onValidated != null) {
                            compView.processHandler$hyperkyc_release(onValidated, compView.isValid);
                            Unit unit5 = Unit.INSTANCE;
                            Unit unit6 = Unit.INSTANCE;
                        }
                    }
                    return compView.isValid;
                }
            }
            charSequence = "co.hyperverge";
            str2 = "N/A";
            boolean areEqual2 = Intrinsics.areEqual((Object) compView2.this$0.asBoolean$hyperkyc_release(getComponent().getRequired(), true), (Object) true);
            boolean z62 = compView2.isValid;
            validation = getComponent().getValidation();
            if (validation != null || validation.isEmpty()) {
            }
            compView.setValid(z2);
            z3 = compView.isValid;
            if (!z3) {
            }
            updateError();
            onValidated = getComponent().getOnValidated();
            if (onValidated != null) {
            }
            return compView.isValid;
        }

        private final void updateIsValid(boolean value) {
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
            boolean z = false;
            if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                canonicalName = canonicalName.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(canonicalName);
            sb.append(" - ");
            String str2 = getCompLogTag() + "  updateIsValid() called with: value = " + value;
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
                        String str3 = getCompLogTag() + "  updateIsValid() called with: value = " + value;
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
            boolean z2 = ((this instanceof FileUpload) && ((FileUpload) this).getHasFileErrors$hyperkyc_release()) ? false : true;
            if (value && z2) {
                z = true;
            }
            updateData(FormFragment.KEY_IS_VALID, Boolean.valueOf(z));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0151, code lost:
        
            if (r0 != null) goto L55;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x0161, code lost:
        
            if (r0 == null) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0165, code lost:
        
            r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x0174, code lost:
        
            if (r0.find() == false) goto L59;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x0176, code lost:
        
            r8 = r0.replaceAll("");
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x0183, code lost:
        
            if (r8.length() <= 23) goto L65;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x0189, code lost:
        
            if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x018c, code lost:
        
            r8 = r8.substring(0, 23);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x0193, code lost:
        
            r0 = new java.lang.StringBuilder();
            r4 = getCompLogTag() + " updateData() called with: key = [" + r18 + "], value = [" + r19 + ']';
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x01b7, code lost:
        
            if (r4 != null) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x01b9, code lost:
        
            r4 = "null ";
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x01bb, code lost:
        
            r0.append(r4);
            r0.append(' ');
            r0.append("");
            android.util.Log.println(3, r8, r0.toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x0164, code lost:
        
            r8 = r0;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final <ValueType> void updateData(String key, ValueType value) {
            String canonicalName;
            Object m1202constructorimpl;
            String str;
            String str2;
            String className;
            String className2;
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
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
            String str4 = getCompLogTag() + " updateData() called with: key = [" + key + "], value = [" + value + ']';
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
            String str5 = value instanceof Boolean ? ((Boolean) value).booleanValue() ? "yes" : "no" : value;
            MainVM.updateFormData$hyperkyc_release$default(this.this$0.getMainVM$hyperkyc_release(), this.this$0.getModuleId$hyperkyc_release(), MapsKt.mapOf(TuplesKt.to(getComponent().getId() + FilenameUtils.EXTENSION_SEPARATOR + key, str5)), false, 4, null);
        }

        /* JADX WARN: Code restructure failed: missing block: B:47:0x0145, code lost:
        
            if (r0 != null) goto L55;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x0155, code lost:
        
            if (r0 == null) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x0159, code lost:
        
            r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x0168, code lost:
        
            if (r0.find() == false) goto L59;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x016a, code lost:
        
            r8 = r0.replaceAll("");
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x0175, code lost:
        
            if (r8.length() <= 23) goto L65;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x017b, code lost:
        
            if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x017e, code lost:
        
            r8 = r8.substring(0, 23);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x0185, code lost:
        
            r0 = new java.lang.StringBuilder();
            r4 = getCompLogTag() + ' ' + r18 + ".updateStyling() called with: et = " + r19;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x01a6, code lost:
        
            if (r4 != null) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x01a8, code lost:
        
            r4 = "null ";
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x01aa, code lost:
        
            r0.append(r4);
            r0.append(' ');
            r0.append("");
            android.util.Log.println(3, r8, r0.toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x0158, code lost:
        
            r8 = r0;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        protected final void updateStyling(TextInputLayout textInputLayout, EditText editText) {
            String canonicalName;
            Object m1202constructorimpl;
            String str;
            String str2;
            String className;
            int alphaComponent;
            int alphaComponent2;
            String className2;
            Intrinsics.checkNotNullParameter(textInputLayout, "<this>");
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str3 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls = textInputLayout.getClass();
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
            String str4 = getCompLogTag() + ' ' + textInputLayout + ".updateStyling() called with: et = " + editText;
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
                        Class<?> cls2 = textInputLayout.getClass();
                        str2 = cls2 != null ? cls2.getCanonicalName() : str;
                    }
                }
            }
            int primaryBtnBgColor$hyperkyc_release = this.this$0.getPrimaryBtnBgColor$hyperkyc_release();
            if (editText instanceof AutoCompleteTextView) {
                textInputLayout.setEndIconMode(Intrinsics.areEqual((Object) this.this$0.asBoolean$hyperkyc_release(getComponent().getEnabled(), true), (Object) true) ? 3 : 0);
            }
            int color = MaterialColors.getColor(textInputLayout, R.attr.colorOnSurface);
            textInputLayout.setDefaultHintTextColor(UIExtsKt.toColorStateList(color));
            int[][] iArr = {new int[]{android.R.attr.state_enabled, -16842908}, new int[]{-16842910, android.R.attr.state_focused}, new int[]{android.R.attr.state_enabled, android.R.attr.state_focused}};
            alphaComponent = ColorUtils.setAlphaComponent(primaryBtnBgColor$hyperkyc_release, 25);
            alphaComponent2 = ColorUtils.setAlphaComponent(color, 0);
            textInputLayout.setBoxStrokeColorStateList(new ColorStateList(iArr, new int[]{alphaComponent, alphaComponent2, primaryBtnBgColor$hyperkyc_release}));
            if (editText != null) {
                Color.parseColor(HyperSnapBridgeKt.getUiConfigUtil().getConfig().getColors().getFormPlaceHolderTextColor());
            }
        }
    }

    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001R\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\n\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0002H\u0016J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0015"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$Label;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "Landroid/view/View;", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "parent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment;Landroid/widget/LinearLayout;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;)V", "getComponent", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "getParent", "()Landroid/widget/LinearLayout;", "defaultLayoutParams", "Landroid/widget/LinearLayout$LayoutParams;", "inflate", "updateView", "", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class Label extends CompView {
        private final WorkflowModule.Properties.Section.Component component;
        private final LinearLayout parent;
        final /* synthetic */ FormFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Label(FormFragment formFragment, LinearLayout parent, WorkflowModule.Properties.Section.Component component) {
            super(formFragment, parent, component);
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(component, "component");
            this.this$0 = formFragment;
            this.parent = parent;
            this.component = component;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout getParent() {
            return this.parent;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public WorkflowModule.Properties.Section.Component getComponent() {
            return this.component;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout.LayoutParams defaultLayoutParams() {
            return Intrinsics.areEqual(getComponent().getSubType(), "hint") ? new LinearLayout.LayoutParams(-2, -2, 0.0f) : super.defaultLayoutParams();
        }

        private static final void updateView$applyUIConfigs(FormFragment formFragment, Label label, MaterialTextView materialTextView) {
            DynamicFormUIConfig dynamicFormUIConfig;
            HSUIConfig uiConfigData = formFragment.getMainVM$hyperkyc_release().getUiConfigData();
            if (uiConfigData != null) {
                String moduleId = formFragment.getModuleId$hyperkyc_release();
                Intrinsics.checkNotNullExpressionValue(moduleId, "moduleId");
                dynamicFormUIConfig = uiConfigData.getDynamicFormUIConfig(moduleId, label.getComponent().getId());
            } else {
                dynamicFormUIConfig = null;
            }
            if (label.getView() instanceof LinearLayout) {
                DynamicFormUtils dynamicFormUtils$hyperkyc_release = formFragment.getDynamicFormUtils$hyperkyc_release();
                View view = label.getView();
                Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.LinearLayout");
                dynamicFormUtils$hyperkyc_release.setLinearLayoutGravity((LinearLayout) view, dynamicFormUIConfig);
            }
            formFragment.getDynamicFormUtils$hyperkyc_release().customiseLabel(materialTextView, dynamicFormUIConfig);
        }

        /* JADX WARN: Code restructure failed: missing block: B:38:0x00c3, code lost:
        
            if (r0.equals(co.hyperverge.hyperkyc.data.models.WorkflowModule.Properties.Section.Component.SubType.Label.TEXT_BLOCK) != false) goto L40;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00ce, code lost:
        
            co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt.getUiConfigUtil().customiseDescriptionTextView(r8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x00cc, code lost:
        
            if (r0.equals(co.hyperverge.hyperkyc.data.models.WorkflowModule.Properties.Section.Component.SubType.Label.SUBTITLE) != false) goto L40;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void updateView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
            String stringInjectFromVariables$hyperkyc_release$default;
            String stringInjectFromVariables$hyperkyc_release$default2;
            MaterialTextView materialTextView;
            String stringInjectFromVariables$hyperkyc_release$default3;
            Spanned spanned = null;
            if (reloadProps != null) {
                if (Intrinsics.areEqual(getComponent().getSubType(), "hint")) {
                    View findViewById = getView().findViewById(R.id.tvHintLabel);
                    Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.tvHintLabel)");
                    materialTextView = (MaterialTextView) findViewById;
                } else {
                    View view = getView();
                    Intrinsics.checkNotNull(view, "null cannot be cast to non-null type com.google.android.material.textview.MaterialTextView");
                    materialTextView = (MaterialTextView) view;
                }
                String text = reloadProps.getText();
                if (text != null && (stringInjectFromVariables$hyperkyc_release$default3 = FormFragment.stringInjectFromVariables$hyperkyc_release$default(this.this$0, text, false, 1, null)) != null) {
                    Spanned fromHtml = HtmlCompat.fromHtml(stringInjectFromVariables$hyperkyc_release$default3, 0, null, null);
                    Intrinsics.checkNotNullExpressionValue(fromHtml, "fromHtml(this, flags, imageGetter, tagHandler)");
                    if (fromHtml != null) {
                        materialTextView.setText(fromHtml);
                    }
                }
                updateView$applyUIConfigs(this.this$0, this, materialTextView);
                return;
            }
            if (getView() instanceof MaterialTextView) {
                View view2 = getView();
                Intrinsics.checkNotNull(view2, "null cannot be cast to non-null type com.google.android.material.textview.MaterialTextView");
                MaterialTextView materialTextView2 = (MaterialTextView) view2;
                String text2 = getComponent().getText();
                if (text2 != null && (stringInjectFromVariables$hyperkyc_release$default2 = FormFragment.stringInjectFromVariables$hyperkyc_release$default(this.this$0, text2, false, 1, null)) != null) {
                    spanned = HtmlCompat.fromHtml(stringInjectFromVariables$hyperkyc_release$default2, 0, null, null);
                    Intrinsics.checkNotNullExpressionValue(spanned, "fromHtml(this, flags, imageGetter, tagHandler)");
                }
                materialTextView2.setText(spanned);
                materialTextView2.setMovementMethod(LinkMovementMethod.getInstance());
                String subType = getComponent().getSubType();
                if (subType != null) {
                    int hashCode = subType.hashCode();
                    if (hashCode != -2090050568) {
                        if (hashCode != -1064582304) {
                            if (hashCode == 110371416 && subType.equals("title")) {
                                HyperSnapBridgeKt.getUiConfigUtil().customiseTitleTextView(materialTextView2);
                            }
                        }
                    }
                    updateView$applyUIConfigs(this.this$0, this, materialTextView2);
                    return;
                }
                throw new NotImplementedError("An operation is not implemented: " + ("Style " + getComponent().getSubType() + " is not supported"));
            }
            if (getView() instanceof LinearLayout) {
                MaterialTextView hintTextView = (MaterialTextView) getView().findViewById(R.id.tvHintLabel);
                String text3 = getComponent().getText();
                if (text3 != null && (stringInjectFromVariables$hyperkyc_release$default = FormFragment.stringInjectFromVariables$hyperkyc_release$default(this.this$0, text3, false, 1, null)) != null) {
                    spanned = HtmlCompat.fromHtml(stringInjectFromVariables$hyperkyc_release$default, 0, null, null);
                    Intrinsics.checkNotNullExpressionValue(spanned, "fromHtml(this, flags, imageGetter, tagHandler)");
                }
                hintTextView.setText(spanned);
                hintTextView.setMovementMethod(LinkMovementMethod.getInstance());
                FormFragment formFragment = this.this$0;
                Intrinsics.checkNotNullExpressionValue(hintTextView, "hintTextView");
                updateView$applyUIConfigs(formFragment, this, hintTextView);
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x013b, code lost:
        
            if (r0 == null) goto L55;
         */
        /* JADX WARN: Failed to find 'out' block for switch in B:24:0x01ae. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:30:0x01f2  */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public View inflate() {
            String canonicalName;
            Object m1202constructorimpl;
            String canonicalName2;
            String className;
            int i;
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
            String str2 = getCompLogTag() + " inflate() called";
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
                        String str3 = getCompLogTag() + " inflate() called";
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
            LayoutInflater layoutInflater = this.this$0.getLayoutInflater();
            String subType = getComponent().getSubType();
            if (subType != null) {
                switch (subType.hashCode()) {
                    case -2090050568:
                        if (subType.equals(WorkflowModule.Properties.Section.Component.SubType.Label.SUBTITLE)) {
                            i = R.layout.hk_view_textview_subtitle;
                            View inflate = layoutInflater.inflate(i, (ViewGroup) null, false);
                            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type android.view.View");
                            if (Intrinsics.areEqual(getComponent().getSubType(), "hint")) {
                                inflate.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            }
                            return inflate;
                        }
                        break;
                    case -1064582304:
                        if (subType.equals(WorkflowModule.Properties.Section.Component.SubType.Label.TEXT_BLOCK)) {
                            i = R.layout.hk_view_textview_textblock;
                            View inflate2 = layoutInflater.inflate(i, (ViewGroup) null, false);
                            Intrinsics.checkNotNull(inflate2, "null cannot be cast to non-null type android.view.View");
                            if (Intrinsics.areEqual(getComponent().getSubType(), "hint")) {
                            }
                            return inflate2;
                        }
                        break;
                    case 3202695:
                        if (subType.equals("hint")) {
                            i = R.layout.hk_view_textview_hint;
                            View inflate22 = layoutInflater.inflate(i, (ViewGroup) null, false);
                            Intrinsics.checkNotNull(inflate22, "null cannot be cast to non-null type android.view.View");
                            if (Intrinsics.areEqual(getComponent().getSubType(), "hint")) {
                            }
                            return inflate22;
                        }
                        break;
                    case 110371416:
                        if (subType.equals("title")) {
                            i = R.layout.hk_view_textview_title;
                            View inflate222 = layoutInflater.inflate(i, (ViewGroup) null, false);
                            Intrinsics.checkNotNull(inflate222, "null cannot be cast to non-null type android.view.View");
                            if (Intrinsics.areEqual(getComponent().getSubType(), "hint")) {
                            }
                            return inflate222;
                        }
                        break;
                }
            }
            throw new NotImplementedError("An operation is not implemented: " + ("Style " + getComponent().getSubType() + " is not supported"));
        }
    }

    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001R\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0002H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0011H\u0016J\u0012\u0010\u0016\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0014H\u0016J\u0012\u0010\u001a\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J \u0010\u001b\u001a\u00020\u0014*\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$Text;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "Landroid/view/View;", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "parent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment;Landroid/widget/LinearLayout;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;)V", "getComponent", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "getParent", "()Landroid/widget/LinearLayout;", "textChangedByUser", "", "getKeyboardInputType", "", "inflate", "postInflate", "", "topOffset", "updateBlocksView", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "updateError", "updateView", "updateTIL", "Lcom/google/android/material/textfield/TextInputLayout;", WebViewFragment.ARG_SUB_TYPE, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class Text extends CompView<View, String> {
        private final WorkflowModule.Properties.Section.Component component;
        private final LinearLayout parent;
        private boolean textChangedByUser;
        final /* synthetic */ FormFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Text(FormFragment formFragment, LinearLayout parent, WorkflowModule.Properties.Section.Component component) {
            super(formFragment, parent, component);
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(component, "component");
            this.this$0 = formFragment;
            this.parent = parent;
            this.component = component;
            this.textChangedByUser = true;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout getParent() {
            return this.parent;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public WorkflowModule.Properties.Section.Component getComponent() {
            return this.component;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void postInflate() {
            super.postInflate();
            String componentValue$hyperkyc_release = getComponentValue$hyperkyc_release();
            if (componentValue$hyperkyc_release == null) {
                componentValue$hyperkyc_release = "";
            }
            updateData("value", componentValue$hyperkyc_release);
            if (Intrinsics.areEqual(getComponent().getSubType(), WorkflowModule.Properties.Section.Component.SubType.Text.BLOCKS)) {
                return;
            }
            View view = getView();
            TextInputLayout textInputLayout = view instanceof TextInputLayout ? (TextInputLayout) view : null;
            if (textInputLayout != null) {
                textInputLayout.setError(null);
                textInputLayout.setErrorEnabled(false);
            }
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public int topOffset() {
            return ViewExtsKt.getDp(5);
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void updateView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
            if (Intrinsics.areEqual(getComponent().getSubType(), WorkflowModule.Properties.Section.Component.SubType.Text.BLOCKS)) {
                updateBlocksView(reloadProps);
                return;
            }
            View view = getView();
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type com.google.android.material.textfield.TextInputLayout");
            updateTIL((TextInputLayout) view, getComponent().getSubType(), reloadProps);
        }

        private static final void updateTIL$applyUIConfigs(Text text, TextInputLayout textInputLayout, IMEAwareTextInputEditText tie, FormFragment formFragment) {
            DynamicFormUIConfig dynamicFormUIConfig;
            IMEAwareTextInputEditText iMEAwareTextInputEditText = tie;
            text.updateStyling(textInputLayout, iMEAwareTextInputEditText);
            DynamicFormUtils dynamicFormUtils$hyperkyc_release = formFragment.getDynamicFormUtils$hyperkyc_release();
            Intrinsics.checkNotNullExpressionValue(tie, "tie");
            HSUIConfig uiConfigData = formFragment.getMainVM$hyperkyc_release().getUiConfigData();
            if (uiConfigData != null) {
                String moduleId = formFragment.getModuleId$hyperkyc_release();
                Intrinsics.checkNotNullExpressionValue(moduleId, "moduleId");
                dynamicFormUIConfig = uiConfigData.getDynamicFormUIConfig(moduleId, text.getComponent().getId());
            } else {
                dynamicFormUIConfig = null;
            }
            dynamicFormUtils$hyperkyc_release.customiseTIL(textInputLayout, iMEAwareTextInputEditText, dynamicFormUIConfig);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void updateTIL$lambda$7$lambda$5(Text this$0, View view, boolean z) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (z || this$0.getErrorMessage() == null) {
                return;
            }
            this$0.updateError();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean updateTIL$lambda$7$lambda$6(IMEAwareTextInputEditText iMEAwareTextInputEditText, TextView textView, int i, KeyEvent keyEvent) {
            if (i != 6) {
                return false;
            }
            iMEAwareTextInputEditText.clearFocus();
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v5, types: [T, java.lang.Object] */
        public static final void updateBlocksView$lambda$11(FormFragment this$0, final Text this$1, Ref.ObjectRef blockTextValue, final BlocksView blocksView) {
            DynamicFormUIConfig dynamicFormUIConfig;
            String str;
            String str2;
            String str3;
            String str4;
            String stringInjectFromVariables$hyperkyc_release$default;
            Integer intOrNull;
            String stringInjectFromVariables$hyperkyc_release$default2;
            Integer intOrNull2;
            String fontSize;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Intrinsics.checkNotNullParameter(blockTextValue, "$blockTextValue");
            Intrinsics.checkNotNullParameter(blocksView, "$blocksView");
            HSUIConfig uiConfigData = this$0.getMainVM$hyperkyc_release().getUiConfigData();
            if (uiConfigData != null) {
                String moduleId = this$0.getModuleId$hyperkyc_release();
                Intrinsics.checkNotNullExpressionValue(moduleId, "moduleId");
                dynamicFormUIConfig = uiConfigData.getDynamicFormUIConfig(moduleId, this$1.getComponent().getId());
            } else {
                dynamicFormUIConfig = null;
            }
            HyperSnapUIConfigUtil uiConfigUtil = HyperSnapBridgeKt.getUiConfigUtil();
            if (dynamicFormUIConfig == null || (str = dynamicFormUIConfig.getFont()) == null) {
                str = "Mulish";
            }
            if (dynamicFormUIConfig == null || (str2 = dynamicFormUIConfig.getFontWeight()) == null) {
                str2 = "Regular";
            }
            Typeface font = uiConfigUtil.getFont(str, str2);
            BlockEditTextConfig blockEditTextConfig = new BlockEditTextConfig(this$1.getKeyboardInputType(), Intrinsics.areEqual((Object) this$0.asBoolean$hyperkyc_release(this$1.getComponent().getEnabled(), true), (Object) true));
            if (dynamicFormUIConfig == null || (str3 = dynamicFormUIConfig.getColor()) == null) {
                str3 = "#050552";
            }
            String str5 = str3;
            int parseInt = (dynamicFormUIConfig == null || (fontSize = dynamicFormUIConfig.getFontSize()) == null) ? 14 : Integer.parseInt(fontSize);
            if (dynamicFormUIConfig == null || (str4 = dynamicFormUIConfig.getBorderColor()) == null) {
                str4 = "#0085FF";
            }
            final int i = 1;
            BlockItemViewConfig blockItemViewConfig = new BlockItemViewConfig(str5, parseInt, font, str4, "#1A050552", "#DD4A46");
            String blockCount = this$1.getComponent().getBlockCount();
            final int intValue = (blockCount == null || (stringInjectFromVariables$hyperkyc_release$default2 = FormFragment.stringInjectFromVariables$hyperkyc_release$default(this$0, blockCount, false, 1, null)) == null || (intOrNull2 = StringsKt.toIntOrNull(stringInjectFromVariables$hyperkyc_release$default2)) == null) ? 6 : intOrNull2.intValue();
            String blockLength = this$1.getComponent().getBlockLength();
            if (blockLength != null && (stringInjectFromVariables$hyperkyc_release$default = FormFragment.stringInjectFromVariables$hyperkyc_release$default(this$0, blockLength, false, 1, null)) != null && (intOrNull = StringsKt.toIntOrNull(stringInjectFromVariables$hyperkyc_release$default)) != null) {
                i = intOrNull.intValue();
            }
            if (blockTextValue.element == 0) {
                blockTextValue.element = this$1.getComponentValue$hyperkyc_release();
            }
            blocksView.generateBlocksView$hyperkyc_release(new BlocksViewConfig(new BlocksContainerConfig(blockEditTextConfig, blockItemViewConfig, intValue, i, ViewExtsKt.getDp(40), ViewExtsKt.getDp(40), (String) blockTextValue.element), new ErrorTextViewConfig("#DD4A46", 10), new BlocksViewListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Text$updateBlocksView$3$blocksViewListener$1
                @Override // co.hyperverge.hyperkyc.ui.custom.blocks.BlocksViewListener
                public void onTextChanged(String value) {
                    Intrinsics.checkNotNullParameter(value, "value");
                    FormFragment.Text.this.updateValue(value);
                    if (value.length() == intValue * i) {
                        String errorMessage = FormFragment.Text.this.getErrorMessage();
                        if (!(errorMessage == null || StringsKt.isBlank(errorMessage))) {
                            FormFragment.Text.this.updateError();
                            return;
                        } else {
                            blocksView.showSuccess$hyperkyc_release();
                            return;
                        }
                    }
                    blocksView.clearError$hyperkyc_release();
                }

                @Override // co.hyperverge.hyperkyc.ui.custom.blocks.BlocksViewListener
                public void onFocusChanged(boolean hasFocus) {
                    if (hasFocus) {
                        return;
                    }
                    String errorMessage = FormFragment.Text.this.getErrorMessage();
                    if (errorMessage == null || StringsKt.isBlank(errorMessage)) {
                        return;
                    }
                    FormFragment.Text.this.updateError();
                }
            }));
            String str6 = (String) blockTextValue.element;
            if (str6 == null || Intrinsics.areEqual(blockTextValue.element, this$1.getValue())) {
                return;
            }
            if (Intrinsics.areEqual(str6, this$1.getComponentValue$hyperkyc_release())) {
                this$1.updateDefaultValue(str6);
            } else {
                this$1.updateValue(str6);
            }
        }

        private final int getKeyboardInputType() {
            String keyboard = getComponent().getKeyboard();
            if (keyboard != null) {
                int hashCode = keyboard.hashCode();
                if (hashCode != -1034364087) {
                    if (hashCode != 96619420) {
                        if (hashCode == 106642798 && keyboard.equals(WorkflowModule.Properties.Section.Component.Keyboard.PHONE)) {
                            return 3;
                        }
                    } else if (keyboard.equals("email")) {
                        return 32;
                    }
                } else if (keyboard.equals("number")) {
                    return 2;
                }
            }
            return 1;
        }

        /* JADX WARN: Code restructure failed: missing block: B:59:0x013b, code lost:
        
            if (r0 == null) goto L55;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public View inflate() {
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
            String str2 = getCompLogTag() + " inflate() called";
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
                        String str3 = getCompLogTag() + " inflate() called";
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
            View inflate = this.this$0.getLayoutInflater().inflate(Intrinsics.areEqual(getComponent().getSubType(), WorkflowModule.Properties.Section.Component.SubType.Text.BLOCKS) ? R.layout.hk_view_text_blocks : R.layout.hk_view_text_input_layout, (ViewGroup) null, false);
            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type android.view.View");
            return inflate;
        }

        private final void updateTIL(TextInputLayout textInputLayout, String str, WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProperties) {
            String canonicalName;
            Class<?> cls;
            String json;
            Object m1202constructorimpl;
            String canonicalName2;
            Class<?> cls2;
            String json2;
            String className;
            String stringInjectFromVariablesForAny$hyperkyc_release$default;
            String className2;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                canonicalName = (textInputLayout == null || (cls = textInputLayout.getClass()) == null) ? null : cls.getCanonicalName();
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
            sb2.append(getCompLogTag());
            sb2.append(" updateTIL() called with: subType = [");
            sb2.append(str);
            sb2.append("], reloadProps = [");
            json = new Gson().toJson(reloadProperties);
            sb2.append(json);
            sb2.append(']');
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
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            canonicalName2 = (textInputLayout == null || (cls2 = textInputLayout.getClass()) == null) ? null : cls2.getCanonicalName();
                            if (canonicalName2 == null) {
                                canonicalName2 = "N/A";
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
                        StringBuilder sb4 = new StringBuilder();
                        StringBuilder sb5 = new StringBuilder();
                        sb5.append(getCompLogTag());
                        sb5.append(" updateTIL() called with: subType = [");
                        sb5.append(str);
                        sb5.append("], reloadProps = [");
                        json2 = new Gson().toJson(reloadProperties);
                        sb5.append(json2);
                        sb5.append(']');
                        String sb6 = sb5.toString();
                        if (sb6 == null) {
                            sb6 = "null ";
                        }
                        sb4.append(sb6);
                        sb4.append(' ');
                        sb4.append("");
                        Log.println(3, canonicalName2, sb4.toString());
                    }
                }
            }
            final IMEAwareTextInputEditText updateTIL$lambda$7 = (IMEAwareTextInputEditText) textInputLayout.findViewById(R.id.textInputEditText);
            if (reloadProperties != null) {
                this.this$0.setTitleHintAndHelperText$hyperkyc_release(textInputLayout, getComponent(), reloadProperties);
                Object value = reloadProperties.getValue();
                if (value != null && (stringInjectFromVariablesForAny$hyperkyc_release$default = FormFragment.stringInjectFromVariablesForAny$hyperkyc_release$default(this.this$0, value, false, 1, null)) != null) {
                    this.textChangedByUser = false;
                    updateTIL$lambda$7.setText(stringInjectFromVariablesForAny$hyperkyc_release$default);
                    updateValue(stringInjectFromVariablesForAny$hyperkyc_release$default);
                }
                updateTIL$applyUIConfigs(this, textInputLayout, updateTIL$lambda$7, this.this$0);
                return;
            }
            FormFragment formFragment = this.this$0;
            formFragment.makeSecure$hyperkyc_release(textInputLayout, Intrinsics.areEqual((Object) formFragment.asBoolean$hyperkyc_release(getComponent().getSecure(), false), (Object) true), R.id.textInputEditText);
            FormFragment.setTitleHintAndHelperText$hyperkyc_release$default(this.this$0, textInputLayout, getComponent(), null, 2, null);
            FormFragment formFragment2 = this.this$0;
            updateTIL$lambda$7.setInputType(getKeyboardInputType());
            if (Intrinsics.areEqual(str, WorkflowModule.Properties.Section.Component.SubType.Text.MULTI_LINE)) {
                updateTIL$lambda$7.setGravity(48);
                updateTIL$lambda$7.setInputType(131072);
                updateTIL$lambda$7.setImeOptions(1);
                updateTIL$lambda$7.setRawInputType(1);
                updateTIL$lambda$7.setHorizontallyScrolling(false);
                updateTIL$lambda$7.setSingleLine(false);
                updateTIL$lambda$7.setLines(getComponent().getLines());
            } else {
                updateTIL$lambda$7.setLines(1);
                updateTIL$lambda$7.setSingleLine(true);
            }
            String componentValue$hyperkyc_release = getComponentValue$hyperkyc_release();
            if (componentValue$hyperkyc_release != null) {
                this.textChangedByUser = false;
                updateTIL$lambda$7.setText(componentValue$hyperkyc_release);
                updateDefaultValue(componentValue$hyperkyc_release);
            }
            Intrinsics.checkNotNullExpressionValue(updateTIL$lambda$7, "updateTIL$lambda$7");
            FlowKt.launchIn(FlowKt.onEach(FlowKt.debounce(FlowKt.conflate(ViewExtsKt.textChangesFlow(updateTIL$lambda$7)), 200L), new FormFragment$Text$updateTIL$3$2(updateTIL$lambda$7, this, null)), formFragment2.getLifecycleScope());
            updateTIL$lambda$7.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Text$$ExternalSyntheticLambda0
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view, boolean z) {
                    FormFragment.Text.updateTIL$lambda$7$lambda$5(FormFragment.Text.this, view, z);
                }
            });
            updateTIL$lambda$7.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Text$$ExternalSyntheticLambda1
                @Override // android.widget.TextView.OnEditorActionListener
                public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    boolean updateTIL$lambda$7$lambda$6;
                    updateTIL$lambda$7$lambda$6 = FormFragment.Text.updateTIL$lambda$7$lambda$6(IMEAwareTextInputEditText.this, textView, i, keyEvent);
                    return updateTIL$lambda$7$lambda$6;
                }
            });
            updateTIL$applyUIConfigs(this, textInputLayout, updateTIL$lambda$7, this.this$0);
        }

        /* JADX WARN: Code restructure failed: missing block: B:61:0x0149, code lost:
        
            if (r0 == null) goto L55;
         */
        /* JADX WARN: Type inference failed for: r2v4, types: [T, java.lang.String] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final void updateBlocksView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
            String canonicalName;
            Object m1202constructorimpl;
            String canonicalName2;
            String className;
            Object value;
            ?? stringInjectFromVariablesForAny$hyperkyc_release$default;
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
            String str2 = getCompLogTag() + " updateBlocksView() called with: reloadProps = [" + reloadProps + ']';
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
                        String str3 = getCompLogTag() + " updateBlocksView() called with: reloadProps = [" + reloadProps + ']';
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
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            if (reloadProps != null && (value = reloadProps.getValue()) != null && (stringInjectFromVariablesForAny$hyperkyc_release$default = FormFragment.stringInjectFromVariablesForAny$hyperkyc_release$default(this.this$0, value, false, 1, null)) != 0) {
                objectRef.element = stringInjectFromVariablesForAny$hyperkyc_release$default;
            }
            View view = getView();
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type co.hyperverge.hyperkyc.ui.custom.blocks.BlocksView");
            final BlocksView blocksView = (BlocksView) view;
            final FormFragment formFragment = this.this$0;
            blocksView.post(new Runnable() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Text$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    FormFragment.Text.updateBlocksView$lambda$11(FormFragment.this, this, objectRef, blocksView);
                }
            });
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void updateError() {
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
            String str2 = getCompLogTag() + " updateError() called with errorMessage = " + getErrorMessage();
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
                        String str3 = getCompLogTag() + " updateError() called with errorMessage = " + getErrorMessage();
                        sb2.append(str3 != null ? str3 : "null ");
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str, sb2.toString());
                    }
                }
            }
            if (Intrinsics.areEqual(getComponent().getSubType(), WorkflowModule.Properties.Section.Component.SubType.Text.BLOCKS)) {
                View view = getView();
                Intrinsics.checkNotNull(view, "null cannot be cast to non-null type co.hyperverge.hyperkyc.ui.custom.blocks.BlocksView");
                ((BlocksView) view).setError(getErrorMessage());
            } else {
                View view2 = getView();
                Intrinsics.checkNotNull(view2, "null cannot be cast to non-null type com.google.android.material.textfield.TextInputLayout");
                TextInputLayout textInputLayout = (TextInputLayout) view2;
                textInputLayout.setError(getErrorMessage());
                String errorMessage = getErrorMessage();
                textInputLayout.setErrorEnabled(!(errorMessage == null || StringsKt.isBlank(errorMessage)));
            }
        }
    }

    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001R\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u000e\u001a\u00020\u0002H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\u0012\u0010\u0012\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0015²\u0006\u0012\u0010\u0016\u001a\n \u0018*\u0004\u0018\u00010\u00170\u0017X\u008a\u0084\u0002"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$Dropdown;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "Lcom/google/android/material/textfield/TextInputLayout;", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "parent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment;Landroid/widget/LinearLayout;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;)V", "getComponent", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "getParent", "()Landroid/widget/LinearLayout;", "inflate", "postInflate", "", "updateError", "updateView", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "hyperkyc_release", "acTv", "Landroid/widget/AutoCompleteTextView;", "kotlin.jvm.PlatformType"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class Dropdown extends CompView<TextInputLayout, String> {
        private final WorkflowModule.Properties.Section.Component component;
        private final LinearLayout parent;
        final /* synthetic */ FormFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Dropdown(FormFragment formFragment, LinearLayout parent, WorkflowModule.Properties.Section.Component component) {
            super(formFragment, parent, component);
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(component, "component");
            this.this$0 = formFragment;
            this.parent = parent;
            this.component = component;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout getParent() {
            return this.parent;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public WorkflowModule.Properties.Section.Component getComponent() {
            return this.component;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void postInflate() {
            super.postInflate();
            String componentValue$hyperkyc_release = getComponentValue$hyperkyc_release();
            if (componentValue$hyperkyc_release == null) {
                componentValue$hyperkyc_release = "";
            }
            updateData("value", componentValue$hyperkyc_release);
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void updateView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
            LinkedHashMap<String, String> linkedHashMap;
            TextInputLayout view = getView();
            FormFragment formFragment = this.this$0;
            final TextInputLayout textInputLayout = view;
            Lazy lazy = LazyKt.lazy(new Function0<AutoCompleteTextView>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Dropdown$updateView$1$acTv$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final AutoCompleteTextView invoke() {
                    return (AutoCompleteTextView) TextInputLayout.this.findViewById(R.id.actvDropdown);
                }
            });
            if (reloadProps != null) {
                formFragment.setTitleHintAndHelperText$hyperkyc_release(textInputLayout, getComponent(), reloadProps);
                Object value = reloadProps.getValue();
                String nullIfBlank = CoreExtsKt.nullIfBlank(value != null ? FormFragment.stringInjectFromVariablesForAny$hyperkyc_release$default(formFragment, value, false, 1, null) : null);
                if (nullIfBlank != null) {
                    updateValue(nullIfBlank);
                }
                List<String> items = reloadProps.getItems();
                if (items == null) {
                    items = getComponent().getItems();
                    Intrinsics.checkNotNull(items);
                }
                LinkedHashMap<String, String> labels = reloadProps.getLabels();
                if (labels == null || (linkedHashMap = MapsKt.toMap(labels)) == null) {
                    LinkedHashMap<String, String> labels2 = getComponent().getLabels();
                    Intrinsics.checkNotNull(labels2);
                    linkedHashMap = labels2;
                }
                updateView$lambda$15$updateItems(lazy, formFragment, this, items, linkedHashMap);
                updateView$lambda$15$applyUIConfigs(this, textInputLayout, formFragment, lazy);
                return;
            }
            FormFragment.setTitleHintAndHelperText$hyperkyc_release$default(formFragment, textInputLayout, getComponent(), null, 2, null);
            AutoCompleteTextView updateView$lambda$15$lambda$1 = updateView$lambda$15$lambda$1(lazy);
            LinkedHashMap<String, String> labels3 = getComponent().getLabels();
            Intrinsics.checkNotNull(labels3);
            List<String> items2 = getComponent().getItems();
            Intrinsics.checkNotNull(items2);
            updateView$lambda$15$updateItems(lazy, formFragment, this, items2, labels3);
            updateView$lambda$15$lambda$1.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Dropdown$$ExternalSyntheticLambda0
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z) {
                    FormFragment.Dropdown.updateView$lambda$15$lambda$14$lambda$13(FormFragment.Dropdown.this, view2, z);
                }
            });
            updateView$lambda$15$applyUIConfigs(this, textInputLayout, formFragment, lazy);
        }

        private static final AutoCompleteTextView updateView$lambda$15$lambda$1(Lazy<? extends AutoCompleteTextView> lazy) {
            return lazy.getValue();
        }

        private static final void updateView$lambda$15$applyUIConfigs(Dropdown dropdown, TextInputLayout textInputLayout, FormFragment formFragment, Lazy<? extends AutoCompleteTextView> lazy) {
            DynamicFormUIConfig dynamicFormUIConfig;
            dropdown.updateStyling(textInputLayout, updateView$lambda$15$lambda$1(lazy));
            DynamicFormUtils dynamicFormUtils$hyperkyc_release = formFragment.getDynamicFormUtils$hyperkyc_release();
            AutoCompleteTextView acTv = updateView$lambda$15$lambda$1(lazy);
            Intrinsics.checkNotNullExpressionValue(acTv, "acTv");
            HSUIConfig uiConfigData = formFragment.getMainVM$hyperkyc_release().getUiConfigData();
            if (uiConfigData != null) {
                String moduleId = formFragment.getModuleId$hyperkyc_release();
                Intrinsics.checkNotNullExpressionValue(moduleId, "moduleId");
                dynamicFormUIConfig = uiConfigData.getDynamicFormUIConfig(moduleId, dropdown.getComponent().getId());
            } else {
                dynamicFormUIConfig = null;
            }
            dynamicFormUtils$hyperkyc_release.customiseDropdown(textInputLayout, acTv, dynamicFormUIConfig);
        }

        private static final void updateView$lambda$15$updateItems(Lazy<? extends AutoCompleteTextView> lazy, final FormFragment formFragment, final Dropdown dropdown, List<String> list, Map<String, String> map) {
            String nullIfBlank;
            String str;
            AutoCompleteTextView updateView$lambda$15$lambda$1 = updateView$lambda$15$lambda$1(lazy);
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (true ^ StringsKt.isBlank((String) obj)) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = arrayList;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while (true) {
                boolean z = false;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<String, String> next = it.next();
                if ((!StringsKt.isBlank(next.getKey())) && (!StringsKt.isBlank(next.getValue()))) {
                    z = true;
                }
                if (z) {
                    linkedHashMap.put(next.getKey(), next.getValue());
                }
            }
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                if (arrayList2.contains((String) entry.getKey())) {
                    linkedHashMap2.put(entry.getKey(), entry.getValue());
                }
            }
            final LinkedHashMap linkedHashMap3 = linkedHashMap2;
            Object[] array = linkedHashMap3.values().toArray(new String[0]);
            ArrayList arrayList3 = new ArrayList(array.length);
            for (Object obj2 : array) {
                arrayList3.add(FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, (String) obj2, false, 1, null));
            }
            ArrayList arrayList4 = new ArrayList();
            for (Object obj3 : arrayList3) {
                String str2 = (String) obj3;
                if ((StringsKt.isBlank(str2) ^ true) && (StringsKt.isBlank(str2) ^ true)) {
                    arrayList4.add(obj3);
                }
            }
            final ArrayList arrayList5 = arrayList4;
            updateView$lambda$15$lambda$1.setAdapter(new ArrayAdapter(formFragment.requireContext(), android.R.layout.simple_list_item_1, arrayList5));
            updateView$lambda$15$lambda$1.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Dropdown$$ExternalSyntheticLambda1
                @Override // android.widget.AdapterView.OnItemClickListener
                public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                    FormFragment.Dropdown.updateView$lambda$15$updateItems$lambda$11$lambda$8(FormFragment.Dropdown.this, formFragment, linkedHashMap3, arrayList5, adapterView, view, i, j);
                }
            });
            String componentValue$hyperkyc_release = dropdown.getComponentValue$hyperkyc_release();
            if (componentValue$hyperkyc_release == null || (nullIfBlank = CoreExtsKt.nullIfBlank(componentValue$hyperkyc_release)) == null) {
                return;
            }
            LinkedHashMap<String, String> labels = dropdown.getComponent().getLabels();
            if (labels != null && (str = labels.get(nullIfBlank)) != null) {
                updateView$lambda$15$lambda$1(lazy).setText((CharSequence) str, false);
            }
            dropdown.updateDefaultValue(nullIfBlank);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void updateView$lambda$15$updateItems$lambda$11$lambda$8(Dropdown this$0, FormFragment this$1, Map filteredLabels, List labelValues, AdapterView adapterView, View view, int i, long j) {
            Object obj;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Intrinsics.checkNotNullParameter(filteredLabels, "$filteredLabels");
            Intrinsics.checkNotNullParameter(labelValues, "$labelValues");
            Iterator it = filteredLabels.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                } else {
                    obj = it.next();
                    if (Intrinsics.areEqual(FormFragment.stringInjectFromVariables$hyperkyc_release$default(this$1, (String) ((Map.Entry) obj).getValue(), false, 1, null), labelValues.get(i))) {
                        break;
                    }
                }
            }
            Intrinsics.checkNotNull(obj);
            this$0.updateValue(FormFragment.stringInjectFromVariables$hyperkyc_release$default(this$1, (String) ((Map.Entry) obj).getKey(), false, 1, null));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void updateView$lambda$15$lambda$14$lambda$13(Dropdown this$0, View view, boolean z) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (z || this$0.getErrorMessage() == null) {
                return;
            }
            this$0.updateError();
        }

        /* JADX WARN: Code restructure failed: missing block: B:55:0x013b, code lost:
        
            if (r0 == null) goto L55;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public TextInputLayout inflate() {
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
            String str2 = getCompLogTag() + " inflate() called";
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
                        String str3 = getCompLogTag() + " inflate() called";
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
            View inflate = this.this$0.getLayoutInflater().inflate(R.layout.hk_view_til_dropdown, (ViewGroup) null, false);
            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type com.google.android.material.textfield.TextInputLayout");
            return (TextInputLayout) inflate;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void updateError() {
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
            String str2 = getCompLogTag() + " updateError() called with: errorMessage: " + getErrorMessage();
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
                        String str3 = getCompLogTag() + " updateError() called with: errorMessage: " + getErrorMessage();
                        sb2.append(str3 != null ? str3 : "null ");
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str, sb2.toString());
                    }
                }
            }
            getView().setError(getErrorMessage());
        }
    }

    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\b\u0080\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001R\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u000e\u001a\u00020\u0002H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\u0012\u0010\u0012\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u001c\u0010\u0015\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016*\u00020\bH\u0002R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$Date;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "Lcom/google/android/material/textfield/TextInputLayout;", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "parent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment;Landroid/widget/LinearLayout;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;)V", "getComponent", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "getParent", "()Landroid/widget/LinearLayout;", "inflate", "postInflate", "", "updateError", "updateView", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "getMinMaxDateMillis", "Lkotlin/Pair;", "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class Date extends CompView<TextInputLayout, String> {
        private final WorkflowModule.Properties.Section.Component component;
        private final LinearLayout parent;
        final /* synthetic */ FormFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Date(FormFragment formFragment, LinearLayout parent, WorkflowModule.Properties.Section.Component component) {
            super(formFragment, parent, component);
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(component, "component");
            this.this$0 = formFragment;
            this.parent = parent;
            this.component = component;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public WorkflowModule.Properties.Section.Component getComponent() {
            return this.component;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout getParent() {
            return this.parent;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void postInflate() {
            super.postInflate();
            String componentValue$hyperkyc_release = getComponentValue$hyperkyc_release();
            if (componentValue$hyperkyc_release == null) {
                componentValue$hyperkyc_release = "";
            }
            updateData("value", componentValue$hyperkyc_release);
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void updateView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
            String stringInjectFromVariablesForAny$hyperkyc_release$default;
            TextInputLayout view = getView();
            final FormFragment formFragment = this.this$0;
            TextInputLayout textInputLayout = view;
            final TextInputEditText textInputEditText = (TextInputEditText) textInputLayout.findViewById(R.id.etDate);
            if (reloadProps != null) {
                formFragment.setTitleHintAndHelperText$hyperkyc_release(textInputLayout, getComponent(), reloadProps);
                Object value = reloadProps.getValue();
                if (value != null && (stringInjectFromVariablesForAny$hyperkyc_release$default = FormFragment.stringInjectFromVariablesForAny$hyperkyc_release$default(formFragment, value, false, 1, null)) != null) {
                    textInputEditText.setText(stringInjectFromVariablesForAny$hyperkyc_release$default);
                    updateValue(stringInjectFromVariablesForAny$hyperkyc_release$default);
                }
                updateView$lambda$11$applyUIConfigs(this, textInputLayout, textInputEditText, formFragment);
                return;
            }
            FormFragment.setTitleHintAndHelperText$hyperkyc_release$default(formFragment, textInputLayout, getComponent(), null, 2, null);
            textInputEditText.setInputType(0);
            String componentValue$hyperkyc_release = getComponentValue$hyperkyc_release();
            if (componentValue$hyperkyc_release != null) {
                textInputEditText.setText(componentValue$hyperkyc_release);
                updateDefaultValue(componentValue$hyperkyc_release);
            }
            textInputEditText.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Date$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    FormFragment.Date.updateView$lambda$11$lambda$10$lambda$8(TextInputEditText.this, formFragment, this, view2);
                }
            });
            textInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Date$$ExternalSyntheticLambda2
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z) {
                    FormFragment.Date.updateView$lambda$11$lambda$10$lambda$9(FormFragment.Date.this, view2, z);
                }
            });
            updateView$lambda$11$applyUIConfigs(this, textInputLayout, textInputEditText, formFragment);
        }

        private static final void updateView$lambda$11$applyUIConfigs(Date date, TextInputLayout textInputLayout, TextInputEditText et, FormFragment formFragment) {
            DynamicFormUIConfig dynamicFormUIConfig;
            TextInputEditText textInputEditText = et;
            date.updateStyling(textInputLayout, textInputEditText);
            DynamicFormUtils dynamicFormUtils$hyperkyc_release = formFragment.getDynamicFormUtils$hyperkyc_release();
            Intrinsics.checkNotNullExpressionValue(et, "et");
            HSUIConfig uiConfigData = formFragment.getMainVM$hyperkyc_release().getUiConfigData();
            if (uiConfigData != null) {
                String moduleId = formFragment.getModuleId$hyperkyc_release();
                Intrinsics.checkNotNullExpressionValue(moduleId, "moduleId");
                dynamicFormUIConfig = uiConfigData.getDynamicFormUIConfig(moduleId, date.getComponent().getId());
            } else {
                dynamicFormUIConfig = null;
            }
            dynamicFormUtils$hyperkyc_release.customiseTIL(textInputLayout, textInputEditText, dynamicFormUIConfig);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void updateView$lambda$11$lambda$10$lambda$8(final TextInputEditText updateView$lambda$11$lambda$10$lambda$8, FormFragment this$0, final Date this$1, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Intrinsics.checkNotNullExpressionValue(updateView$lambda$11$lambda$10$lambda$8, "updateView$lambda$11$lambda$10$lambda$8");
            ViewExtsKt.hideSoftInput(updateView$lambda$11$lambda$10$lambda$8);
            final Calendar calendar = Calendar.getInstance();
            MaterialStyledDatePickerDialog materialStyledDatePickerDialog = new MaterialStyledDatePickerDialog(this$0.requireActivity(), new DatePickerDialog.OnDateSetListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Date$$ExternalSyntheticLambda0
                @Override // android.app.DatePickerDialog.OnDateSetListener
                public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                    FormFragment.Date.updateView$lambda$11$lambda$10$lambda$8$lambda$3(calendar, this$1, updateView$lambda$11$lambda$10$lambda$8, datePicker, i, i2, i3);
                }
            }, calendar.get(1), calendar.get(2), calendar.get(5));
            DatePicker datePicker = materialStyledDatePickerDialog.getDatePicker();
            Pair<Long, Long> minMaxDateMillis = this$1.getMinMaxDateMillis(this$1.getComponent());
            Long component1 = minMaxDateMillis.component1();
            Long component2 = minMaxDateMillis.component2();
            if (component1 != null) {
                datePicker.setMinDate(component1.longValue());
            }
            if (component2 != null) {
                datePicker.setMaxDate(component2.longValue());
            }
            materialStyledDatePickerDialog.show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void updateView$lambda$11$lambda$10$lambda$8$lambda$3(Calendar calendar, Date this$0, TextInputEditText textInputEditText, DatePicker datePicker, int i, int i2, int i3) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            calendar.set(i, i2, i3);
            String format = this$0.getComponent().getFormat();
            if (format == null) {
                format = FormFragment.DATE_FORMAT;
            }
            String selectedDateString = new SimpleDateFormat(format, Locale.getDefault()).format(calendar.getTime());
            textInputEditText.setText(selectedDateString);
            Intrinsics.checkNotNullExpressionValue(selectedDateString, "selectedDateString");
            this$0.updateValue(selectedDateString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void updateView$lambda$11$lambda$10$lambda$9(Date this$0, View view, boolean z) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (z || this$0.getErrorMessage() == null) {
                return;
            }
            this$0.updateError();
        }

        private static final Long getMinMaxDateMillis$toDateMillis(String str, WorkflowModule.Properties.Section.Component component) {
            java.util.Date parse = new SimpleDateFormat(component.getFormat(), Locale.getDefault()).parse(str);
            if (parse != null) {
                return Long.valueOf(parse.getTime());
            }
            return null;
        }

        private static final long getMinMaxDateMillis$lambda$18$getMonthAdjustedDate(int i) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2, calendar.get(2) + i);
            return calendar.getTimeInMillis();
        }

        /* JADX WARN: Code restructure failed: missing block: B:55:0x013b, code lost:
        
            if (r0 == null) goto L55;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public TextInputLayout inflate() {
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
            String str2 = getCompLogTag() + " inflate() called";
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
                        String str3 = getCompLogTag() + " inflate() called";
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
            View inflate = this.this$0.getLayoutInflater().inflate(R.layout.hk_view_til_date, (ViewGroup) null, false);
            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type com.google.android.material.textfield.TextInputLayout");
            return (TextInputLayout) inflate;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void updateError() {
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
            String str2 = getCompLogTag() + " updateError() called with: errorMessage: " + getErrorMessage();
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
                        String str3 = getCompLogTag() + " updateError() called with: errorMessage: " + getErrorMessage();
                        sb2.append(str3 != null ? str3 : "null ");
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str, sb2.toString());
                    }
                }
            }
            getView().setError(getErrorMessage());
        }

        /* JADX WARN: Code restructure failed: missing block: B:121:0x0141, code lost:
        
            if (r0 == null) goto L57;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final Pair<Long, Long> getMinMaxDateMillis(WorkflowModule.Properties.Section.Component component) {
            String canonicalName;
            Class<?> cls;
            Object m1202constructorimpl;
            String canonicalName2;
            Class<?> cls2;
            String className;
            Object obj;
            Character ch;
            String className2;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                canonicalName = (component == null || (cls = component.getClass()) == null) ? null : cls.getCanonicalName();
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
            String str2 = getCompLogTag() + " getMinMaxDateMillis() called";
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
                            canonicalName2 = (component == null || (cls2 = component.getClass()) == null) ? null : cls2.getCanonicalName();
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
                        String str3 = getCompLogTag() + " getMinMaxDateMillis() called";
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
            Calendar calendar = Calendar.getInstance();
            String content = component.getContent();
            if (!(content == null || StringsKt.isBlank(content))) {
                String format = component.getFormat();
                if (!(format == null || StringsKt.isBlank(format))) {
                    String dateContentWithFormat$hyperkyc_release = this.this$0.getDateContentWithFormat$hyperkyc_release(component);
                    String str4 = dateContentWithFormat$hyperkyc_release != null ? dateContentWithFormat$hyperkyc_release : "";
                    String format2 = component.getFormat();
                    int i = 0;
                    while (true) {
                        if (i >= format2.length()) {
                            ch = null;
                            break;
                        }
                        char charAt = format2.charAt(i);
                        if (!Character.isLetterOrDigit(charAt)) {
                            ch = Character.valueOf(charAt);
                            break;
                        }
                        i++;
                    }
                    if (ch == null) {
                        throw new IllegalStateException("no delimiter found in format".toString());
                    }
                    char charValue = ch.charValue();
                    List split$default = StringsKt.split$default((CharSequence) str4, new char[]{charValue}, false, 0, 6, (Object) null);
                    Object obj2 = split$default.get(0);
                    if (!TextUtils.isDigitsOnly((String) obj2)) {
                        obj2 = null;
                    }
                    Object obj3 = (String) obj2;
                    Object obj4 = split$default.get(1);
                    if (!TextUtils.isDigitsOnly((String) obj4)) {
                        obj4 = null;
                    }
                    Object obj5 = (String) obj4;
                    Object obj6 = split$default.get(2);
                    Object obj7 = (String) (TextUtils.isDigitsOnly((String) obj6) ? obj6 : null);
                    Object valueOf = obj3 == null ? Integer.valueOf(calendar.getActualMaximum(5)) : obj3;
                    if (obj3 == null) {
                        obj3 = Integer.valueOf(calendar.getActualMinimum(5));
                    }
                    Object valueOf2 = obj5 == null ? Integer.valueOf(calendar.getActualMaximum(2)) : obj5;
                    if (obj5 == null) {
                        obj5 = Integer.valueOf(calendar.getActualMinimum(2));
                    }
                    Object valueOf3 = obj7 == null ? Integer.valueOf(calendar.getActualMaximum(1)) : obj7;
                    if (obj7 == null) {
                        obj7 = Integer.valueOf(calendar.getActualMinimum(1));
                    }
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(obj3);
                    sb3.append(charValue);
                    sb3.append(obj5);
                    sb3.append(charValue);
                    sb3.append(obj7);
                    String sb4 = sb3.toString();
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(valueOf);
                    sb5.append(charValue);
                    sb5.append(valueOf2);
                    sb5.append(charValue);
                    sb5.append(valueOf3);
                    String sb6 = sb5.toString();
                    r9 = getMinMaxDateMillis$toDateMillis(sb4, component);
                    obj = getMinMaxDateMillis$toDateMillis(sb6, component);
                    return TuplesKt.to(r9, obj);
                }
            }
            WorkflowModule.Properties.Section.Component.DateRange dateRange = component.getDateRange();
            if (dateRange != null) {
                Integer startMonth = dateRange.getStartMonth();
                Integer endMonth = dateRange.getEndMonth();
                Object valueOf4 = startMonth != null ? Long.valueOf(getMinMaxDateMillis$lambda$18$getMonthAdjustedDate(startMonth.intValue())) : null;
                obj = endMonth != null ? Long.valueOf(getMinMaxDateMillis$lambda$18$getMonthAdjustedDate(endMonth.intValue())) : null;
                r9 = valueOf4;
            } else {
                obj = null;
            }
            return TuplesKt.to(r9, obj);
        }
    }

    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001R\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0012\u001a\u00020\u0002H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0016\u0010\u0018\u001a\u00020\u0014*\u00020\u00022\b\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u0002R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001a"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$Button;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "Lcom/google/android/material/button/MaterialButton;", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "parent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment;Landroid/widget/LinearLayout;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;)V", "getComponent", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "isLoading", "", "originalDrawable", "Landroid/graphics/drawable/Drawable;", "getParent", "()Landroid/widget/LinearLayout;", "inflate", "postInflate", "", "updateView", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "updateLoading", "buttonText", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class Button extends CompView<MaterialButton, String> {
        private final WorkflowModule.Properties.Section.Component component;
        private boolean isLoading;
        private Drawable originalDrawable;
        private final LinearLayout parent;
        final /* synthetic */ FormFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Button(FormFragment formFragment, LinearLayout parent, WorkflowModule.Properties.Section.Component component) {
            super(formFragment, parent, component);
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(component, "component");
            this.this$0 = formFragment;
            this.parent = parent;
            this.component = component;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public WorkflowModule.Properties.Section.Component getComponent() {
            return this.component;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout getParent() {
            return this.parent;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void postInflate() {
            super.postInflate();
            updateData("value", "no");
        }

        private static final void updateView$lambda$4$applyUIConfigs(FormFragment formFragment, Button button) {
            DynamicFormUIConfig dynamicFormUIConfig;
            DynamicFormUtils dynamicFormUtils$hyperkyc_release = formFragment.getDynamicFormUtils$hyperkyc_release();
            MaterialButton view = button.getView();
            HSUIConfig uiConfigData = formFragment.getMainVM$hyperkyc_release().getUiConfigData();
            if (uiConfigData != null) {
                String moduleId = formFragment.getModuleId$hyperkyc_release();
                Intrinsics.checkNotNullExpressionValue(moduleId, "moduleId");
                dynamicFormUIConfig = uiConfigData.getDynamicFormUIConfig(moduleId, button.getComponent().getId());
            } else {
                dynamicFormUIConfig = null;
            }
            dynamicFormUtils$hyperkyc_release.customiseButton(view, dynamicFormUIConfig);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final void updateLoading(MaterialButton materialButton, String str) {
            if (this.isLoading) {
                materialButton.setText("");
                Context requireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                Drawable progressBarDrawable = ContextExtsKt.getProgressBarDrawable(requireContext);
                if (progressBarDrawable != 0) {
                    UIExtsKt.setTintColor(progressBarDrawable, materialButton.getCurrentTextColor());
                }
                materialButton.setIconGravity(4);
                materialButton.setIcon(progressBarDrawable);
                Animatable animatable = (Animatable) progressBarDrawable;
                if (animatable != null) {
                    animatable.start();
                    return;
                }
                return;
            }
            materialButton.setText(str != null ? FormFragment.stringInjectFromVariables$hyperkyc_release$default(this.this$0, str, false, 1, null) : null);
            materialButton.setIcon(this.originalDrawable);
        }

        /* JADX WARN: Code restructure failed: missing block: B:76:0x013b, code lost:
        
            if (r0 == null) goto L55;
         */
        /* JADX WARN: Removed duplicated region for block: B:34:0x01f4  */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public MaterialButton inflate() {
            String canonicalName;
            Object m1202constructorimpl;
            String canonicalName2;
            String className;
            int i;
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
            String str2 = getCompLogTag() + " inflate() called";
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
                        String str3 = getCompLogTag() + " inflate() called";
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
            LayoutInflater layoutInflater = this.this$0.getLayoutInflater();
            String subType = getComponent().getSubType();
            if (subType != null) {
                int hashCode = subType.hashCode();
                if (hashCode != -1174796206) {
                    if (hashCode != -817598092) {
                        if (hashCode == -314765822 && subType.equals(WorkflowModule.Properties.Section.Component.SubType.Button.PRIMARY)) {
                            i = R.layout.hk_view_button_primary;
                            View inflate = layoutInflater.inflate(i, (ViewGroup) null, false);
                            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type com.google.android.material.button.MaterialButton");
                            MaterialButton materialButton = (MaterialButton) inflate;
                            if (Intrinsics.areEqual(getComponent().getSubType(), WorkflowModule.Properties.Section.Component.SubType.Button.TERTIARY)) {
                                materialButton.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                            }
                            return materialButton;
                        }
                    } else if (subType.equals(WorkflowModule.Properties.Section.Component.SubType.Button.SECONDARY)) {
                        i = R.layout.hk_view_button_secondary;
                        View inflate2 = layoutInflater.inflate(i, (ViewGroup) null, false);
                        Intrinsics.checkNotNull(inflate2, "null cannot be cast to non-null type com.google.android.material.button.MaterialButton");
                        MaterialButton materialButton2 = (MaterialButton) inflate2;
                        if (Intrinsics.areEqual(getComponent().getSubType(), WorkflowModule.Properties.Section.Component.SubType.Button.TERTIARY)) {
                        }
                        return materialButton2;
                    }
                } else if (subType.equals(WorkflowModule.Properties.Section.Component.SubType.Button.TERTIARY)) {
                    i = R.layout.hk_view_button_tertiary;
                    View inflate22 = layoutInflater.inflate(i, (ViewGroup) null, false);
                    Intrinsics.checkNotNull(inflate22, "null cannot be cast to non-null type com.google.android.material.button.MaterialButton");
                    MaterialButton materialButton22 = (MaterialButton) inflate22;
                    if (Intrinsics.areEqual(getComponent().getSubType(), WorkflowModule.Properties.Section.Component.SubType.Button.TERTIARY)) {
                    }
                    return materialButton22;
                }
            }
            throw new NotImplementedError("An operation is not implemented: " + ("subType " + getComponent().getSubType() + " is not supported"));
        }

        /* JADX WARN: Code restructure failed: missing block: B:62:0x027e, code lost:
        
            if (r2.equals(co.hyperverge.hyperkyc.data.models.WorkflowModule.Properties.Section.Component.SubType.Button.TERTIARY) != false) goto L108;
         */
        /* JADX WARN: Code restructure failed: missing block: B:96:0x0142, code lost:
        
            if (r0 == null) goto L55;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void updateView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
            String canonicalName;
            Object m1202constructorimpl;
            String canonicalName2;
            String className;
            String stringInjectFromVariables$hyperkyc_release$default;
            Long longOrNull;
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
            String str2 = "updateView() called with: reloadProps = [" + reloadProps + ']';
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
                        String str3 = "updateView() called with: reloadProps = [" + reloadProps + ']';
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
            MaterialButton view = getView();
            FormFragment formFragment = this.this$0;
            MaterialButton materialButton = view;
            if (!this.isLoading) {
                this.originalDrawable = materialButton.getIcon();
            }
            if (reloadProps != null) {
                String loading = reloadProps.getLoading();
                if (loading == null) {
                    loading = getComponent().getLoading();
                }
                this.isLoading = Intrinsics.areEqual((Object) formFragment.asBoolean$hyperkyc_release(loading, false), (Object) true);
                String text = reloadProps.getText();
                if (text == null) {
                    text = getComponent().getText();
                }
                updateLoading(materialButton, text);
                updateView$lambda$4$applyUIConfigs(formFragment, this);
                return;
            }
            updateValue("no");
            WorkflowModule.Properties.Section.Component.Handler onClick = getComponent().getOnClick();
            if (onClick != null) {
                Flow buttonClickFlow = ViewExtsKt.buttonClickFlow(materialButton);
                String debounce = onClick.getDebounce();
                FlowKt.launchIn(FlowKt.onEach(FlowKt.debounce(buttonClickFlow, (debounce == null || (stringInjectFromVariables$hyperkyc_release$default = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, debounce, false, 1, null)) == null || (longOrNull = StringsKt.toLongOrNull(stringInjectFromVariables$hyperkyc_release$default)) == null) ? 500L : longOrNull.longValue()), new FormFragment$Button$updateView$2$1$1(this, onClick, null)), formFragment.getLifecycleScope());
            }
            String subType = getComponent().getSubType();
            if (subType != null) {
                int hashCode = subType.hashCode();
                if (hashCode != -1174796206) {
                    if (hashCode != -817598092) {
                        if (hashCode == -314765822 && subType.equals(WorkflowModule.Properties.Section.Component.SubType.Button.PRIMARY)) {
                            HyperSnapBridgeKt.getUiConfigUtil().customisePrimaryButton(getView());
                            this.isLoading = Intrinsics.areEqual((Object) formFragment.asBoolean$hyperkyc_release(getComponent().getLoading(), false), (Object) true);
                            updateLoading(materialButton, getComponent().getText());
                            updateView$lambda$4$applyUIConfigs(formFragment, this);
                            return;
                        }
                    } else if (subType.equals(WorkflowModule.Properties.Section.Component.SubType.Button.SECONDARY)) {
                        HyperSnapBridgeKt.getUiConfigUtil().customiseSecondaryButton((android.widget.Button) getView());
                        this.isLoading = Intrinsics.areEqual((Object) formFragment.asBoolean$hyperkyc_release(getComponent().getLoading(), false), (Object) true);
                        updateLoading(materialButton, getComponent().getText());
                        updateView$lambda$4$applyUIConfigs(formFragment, this);
                        return;
                    }
                }
            }
            throw new NotImplementedError("An operation is not implemented: " + ("subType " + getComponent().getSubType() + " is not supported"));
        }
    }

    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001R\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0010\u001a\u00020\u0002H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$Checkbox;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "Landroid/widget/CheckBox;", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "parent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment;Landroid/widget/LinearLayout;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;)V", "checkedChangeListener", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "getComponent", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "getParent", "()Landroid/widget/LinearLayout;", "inflate", "postInflate", "", "updateView", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class Checkbox extends CompView<CheckBox, Boolean> {
        private final CompoundButton.OnCheckedChangeListener checkedChangeListener;
        private final WorkflowModule.Properties.Section.Component component;
        private final LinearLayout parent;
        final /* synthetic */ FormFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Checkbox(FormFragment formFragment, LinearLayout parent, WorkflowModule.Properties.Section.Component component) {
            super(formFragment, parent, component);
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(component, "component");
            this.this$0 = formFragment;
            this.parent = parent;
            this.component = component;
            this.checkedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Checkbox$$ExternalSyntheticLambda0
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    FormFragment.Checkbox.checkedChangeListener$lambda$1(FormFragment.Checkbox.this, compoundButton, z);
                }
            };
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public WorkflowModule.Properties.Section.Component getComponent() {
            return this.component;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout getParent() {
            return this.parent;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void postInflate() {
            super.postInflate();
            Boolean componentValue$hyperkyc_release = getComponentValue$hyperkyc_release();
            updateData("value", Boolean.valueOf(componentValue$hyperkyc_release != null ? componentValue$hyperkyc_release.booleanValue() : false));
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void updateView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
            String stringInjectFromVariables$hyperkyc_release$default;
            String stringInjectFromVariables$hyperkyc_release$default2;
            CheckBox view = getView();
            FormFragment formFragment = this.this$0;
            CheckBox checkBox = view;
            checkBox.setOnCheckedChangeListener(this.checkedChangeListener);
            if (reloadProps != null) {
                String text = reloadProps.getText();
                if (text != null && (stringInjectFromVariables$hyperkyc_release$default2 = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, text, false, 1, null)) != null) {
                    String required = reloadProps.getRequired();
                    if (required == null) {
                        required = getComponent().getRequired();
                    }
                    Spanned fromHtml = HtmlCompat.fromHtml(formFragment.appendRequired$hyperkyc_release(stringInjectFromVariables$hyperkyc_release$default2, Intrinsics.areEqual((Object) formFragment.asBoolean$hyperkyc_release(required, true), (Object) true)), 0, null, null);
                    Intrinsics.checkNotNullExpressionValue(fromHtml, "fromHtml(this, flags, imageGetter, tagHandler)");
                    checkBox.setText(fromHtml);
                }
                Object value = reloadProps.getValue();
                Boolean asBoolean$hyperkyc_release = formFragment.asBoolean$hyperkyc_release(value instanceof String ? (String) value : null, null);
                if (asBoolean$hyperkyc_release != null) {
                    checkBox.setChecked(asBoolean$hyperkyc_release.booleanValue());
                }
                updateView$lambda$7$applyUIConfigs(formFragment, this);
                return;
            }
            checkBox.setGravity(119);
            String text2 = getComponent().getText();
            if (text2 != null && (stringInjectFromVariables$hyperkyc_release$default = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, text2, false, 1, null)) != null) {
                Spanned fromHtml2 = HtmlCompat.fromHtml(formFragment.appendRequired$hyperkyc_release(stringInjectFromVariables$hyperkyc_release$default, Intrinsics.areEqual((Object) formFragment.asBoolean$hyperkyc_release(getComponent().getRequired(), true), (Object) true)), 0, null, null);
                Intrinsics.checkNotNullExpressionValue(fromHtml2, "fromHtml(this, flags, imageGetter, tagHandler)");
                checkBox.setText(fromHtml2);
            }
            Boolean componentValue$hyperkyc_release = getComponentValue$hyperkyc_release();
            if (componentValue$hyperkyc_release != null) {
                final boolean booleanValue = componentValue$hyperkyc_release.booleanValue();
                ViewExtsKt.setCheckedSilently(checkBox, booleanValue, this.checkedChangeListener, new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Checkbox$updateView$1$4$1
                    /* JADX INFO: Access modifiers changed from: package-private */
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
                        FormFragment.Checkbox.this.updateDefaultValue(Boolean.valueOf(booleanValue));
                    }
                });
            }
            checkBox.setMovementMethod(LinkMovementMethod.getInstance());
            checkBox.setTypeface(formFragment.getDescTextFont$hyperkyc_release());
            checkBox.setButtonTintList(UIExtsKt.toColorStateList(formFragment.getPrimaryBtnBgColor$hyperkyc_release()));
            updateView$lambda$7$applyUIConfigs(formFragment, this);
        }

        private static final void updateView$lambda$7$applyUIConfigs(FormFragment formFragment, Checkbox checkbox) {
            DynamicFormUIConfig dynamicFormUIConfig;
            DynamicFormUtils dynamicFormUtils$hyperkyc_release = formFragment.getDynamicFormUtils$hyperkyc_release();
            CheckBox view = checkbox.getView();
            HSUIConfig uiConfigData = formFragment.getMainVM$hyperkyc_release().getUiConfigData();
            if (uiConfigData != null) {
                String moduleId = formFragment.getModuleId$hyperkyc_release();
                Intrinsics.checkNotNullExpressionValue(moduleId, "moduleId");
                dynamicFormUIConfig = uiConfigData.getDynamicFormUIConfig(moduleId, checkbox.getComponent().getId());
            } else {
                dynamicFormUIConfig = null;
            }
            dynamicFormUtils$hyperkyc_release.customiseCheckBox(view, dynamicFormUIConfig);
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public CheckBox inflate() {
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
            String str2 = getCompLogTag() + " inflate() called";
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
                        String str3 = getCompLogTag() + " inflate() called";
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
            return new CheckBox(this.this$0.requireContext());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void checkedChangeListener$lambda$1(Checkbox this$0, CompoundButton compoundButton, boolean z) {
            String canonicalName;
            Object m1202constructorimpl;
            String className;
            String substringAfterLast$default;
            String className2;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls = this$0.getClass();
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
            String str2 = this$0.getCompLogTag() + " checkedChangeListener() called with: isChecked = [" + z + ']';
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
                            Class<?> cls2 = this$0.getClass();
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
                        String str3 = this$0.getCompLogTag() + " checkedChangeListener() called with: isChecked = [" + z + ']';
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
            this$0.updateValue(Boolean.valueOf(z));
        }
    }

    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0080\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001R\u00020\u0004:\u0002ABB\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u001b\u0010$\u001a\u00020%2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u000eH\u0001¢\u0006\u0002\b(J\b\u0010)\u001a\u00020\u0002H\u0016J\b\u0010*\u001a\u00020%H\u0003J\u0012\u0010+\u001a\u00020\u001a2\b\b\u0002\u0010,\u001a\u00020-H\u0002J\u0010\u0010.\u001a\u00020%2\u0006\u0010/\u001a\u000200H\u0002J\b\u00101\u001a\u00020%H\u0016J\b\u00102\u001a\u00020%H\u0002J\u0015\u00103\u001a\u00020%2\u0006\u00104\u001a\u00020\fH\u0000¢\u0006\u0002\b5J\r\u00106\u001a\u00020%H\u0000¢\u0006\u0002\b7J\b\u00108\u001a\u00020%H\u0016J\b\u00109\u001a\u00020%H\u0002J\u0012\u0010:\u001a\u00020%2\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J(\u0010=\u001a\u00020%*\u00020\u00122\b\u0010>\u001a\u0004\u0018\u00010\u00032\b\u0010?\u001a\u0004\u0018\u00010\u00032\u0006\u0010@\u001a\u00020\u001aH\u0002R6\u0010\n\u001a$\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f \r*\u0010\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f0\u000e0\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0011\u001a\u00020\u00128@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001a8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\"\u0010\u001f\u001a\u0010\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f0\u000e8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0010R\u001a\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020#0\"X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$FileUpload;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "parent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment;Landroid/widget/LinearLayout;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;)V", "adapterCurrentList", "", "Lco/hyperverge/hyperkyc/ui/form/models/PickedFile;", "kotlin.jvm.PlatformType", "", "getAdapterCurrentList", "()Ljava/util/List;", "binding", "Lco/hyperverge/hyperkyc/databinding/HkViewFileUploadClBinding;", "getBinding$hyperkyc_release", "()Lco/hyperverge/hyperkyc/databinding/HkViewFileUploadClBinding;", "binding$delegate", "Lkotlin/Lazy;", "getComponent", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "hasFileErrors", "", "getHasFileErrors$hyperkyc_release", "()Z", "getParent", "()Landroid/widget/LinearLayout;", "pickedFiles", "getPickedFiles$hyperkyc_release", "pickedFilesRvAdapter", "Lco/hyperverge/hyperkyc/ui/custom/SimpleRvAdapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "addPickedFiles", "", "uris", "Landroid/net/Uri;", "addPickedFiles$hyperkyc_release", "inflate", "initEventObservers", "isAtOrAboveFileCountMax", "pickedFilesCount", "", "pickFiles", "formFilePickUIEvent", "Lco/hyperverge/hyperkyc/ui/form/models/FormFilePickUIEvent;", "postInflate", "refreshData", "removePickedFile", "pickedFile", "removePickedFile$hyperkyc_release", "showFileUploadOptionsBS", "showFileUploadOptionsBS$hyperkyc_release", "updateError", "updateInfoText", "updateView", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "updateTitleSubTitle", "title", WorkflowModule.Properties.Section.Component.SubType.Label.SUBTITLE, "shouldAppendRequired", "AddFileVH", "PickedFileVH", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class FileUpload extends CompView<ConstraintLayout, String> {

        /* renamed from: binding$delegate, reason: from kotlin metadata */
        private final Lazy binding;
        private final WorkflowModule.Properties.Section.Component component;
        private final LinearLayout parent;
        private final SimpleRvAdapter<PickedFile, RecyclerView.ViewHolder> pickedFilesRvAdapter;
        final /* synthetic */ FormFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FileUpload(FormFragment formFragment, LinearLayout parent, WorkflowModule.Properties.Section.Component component) {
            super(formFragment, parent, component);
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(component, "component");
            this.this$0 = formFragment;
            this.parent = parent;
            this.component = component;
            this.pickedFilesRvAdapter = new SimpleRvAdapter<>(new Function1<Integer, Integer>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$pickedFilesRvAdapter$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Integer invoke(int i) {
                    int i2;
                    if (i == 10) {
                        i2 = R.layout.hk_rv_item_form_add_file;
                    } else {
                        i2 = R.layout.hk_rv_item_form_file;
                    }
                    return Integer.valueOf(i2);
                }
            }, new Function2<View, Integer, RecyclerView.ViewHolder>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$pickedFilesRvAdapter$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ RecyclerView.ViewHolder invoke(View view, Integer num) {
                    return invoke(view, num.intValue());
                }

                public final RecyclerView.ViewHolder invoke(View view, int i) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    if (i == 10) {
                        FormFragment.FileUpload fileUpload = FormFragment.FileUpload.this;
                        HkRvItemFormAddFileBinding bind = HkRvItemFormAddFileBinding.bind(view);
                        Intrinsics.checkNotNullExpressionValue(bind, "bind(view)");
                        return new FormFragment.FileUpload.AddFileVH(fileUpload, bind);
                    }
                    FormFragment.FileUpload fileUpload2 = FormFragment.FileUpload.this;
                    HkRvItemFormFileBinding bind2 = HkRvItemFormFileBinding.bind(view);
                    Intrinsics.checkNotNullExpressionValue(bind2, "bind(view)");
                    return new FormFragment.FileUpload.PickedFileVH(fileUpload2, bind2);
                }
            }, new Function2<RecyclerView.ViewHolder, PickedFile, Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$pickedFilesRvAdapter$3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(RecyclerView.ViewHolder viewHolder, PickedFile pickedFile) {
                    invoke2(viewHolder, pickedFile);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(RecyclerView.ViewHolder h, PickedFile item) {
                    Intrinsics.checkNotNullParameter(h, "h");
                    Intrinsics.checkNotNullParameter(item, "item");
                    if (h instanceof FormFragment.FileUpload.AddFileVH) {
                        ((FormFragment.FileUpload.AddFileVH) h).bind();
                    } else if (h instanceof FormFragment.FileUpload.PickedFileVH) {
                        ((FormFragment.FileUpload.PickedFileVH) h).bind(item);
                    }
                }
            }, new Function2<PickedFile, PickedFile, Boolean>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$pickedFilesRvAdapter$4
                @Override // kotlin.jvm.functions.Function2
                public final Boolean invoke(PickedFile old, PickedFile pickedFile) {
                    Intrinsics.checkNotNullParameter(old, "old");
                    Intrinsics.checkNotNullParameter(pickedFile, "new");
                    return Boolean.valueOf(Intrinsics.areEqual(old, pickedFile));
                }
            }, new Function2<PickedFile, PickedFile, Boolean>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$pickedFilesRvAdapter$5
                @Override // kotlin.jvm.functions.Function2
                public final Boolean invoke(PickedFile old, PickedFile pickedFile) {
                    Intrinsics.checkNotNullParameter(old, "old");
                    Intrinsics.checkNotNullParameter(pickedFile, "new");
                    return Boolean.valueOf(Intrinsics.areEqual(old.getUri(), pickedFile.getUri()));
                }
            }, new Function1<Integer, Integer>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$pickedFilesRvAdapter$6
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Integer invoke(int i) {
                    List adapterCurrentList;
                    adapterCurrentList = FormFragment.FileUpload.this.getAdapterCurrentList();
                    PickedFile pickedFile = (PickedFile) CollectionsKt.getOrNull(adapterCurrentList, i);
                    return Integer.valueOf((pickedFile != null ? pickedFile.getUri() : null) == null ? 10 : 11);
                }
            }, new Function1<List<? extends PickedFile>, List<? extends PickedFile>>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$pickedFilesRvAdapter$7
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ List<? extends PickedFile> invoke(List<? extends PickedFile> list) {
                    return invoke2((List<PickedFile>) list);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final List<PickedFile> invoke2(List<PickedFile> $receiver) {
                    Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                    return CollectionsKt.sortedWith($receiver, new Comparator() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$pickedFilesRvAdapter$7$invoke$$inlined$sortedBy$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            PickedFile pickedFile = (PickedFile) t;
                            PickedFile pickedFile2 = (PickedFile) t2;
                            return ComparisonsKt.compareValues(Boolean.valueOf((pickedFile.getUri() == null || pickedFile.hasError()) ? false : true), Boolean.valueOf((pickedFile2.getUri() == null || pickedFile2.hasError()) ? false : true));
                        }
                    });
                }
            }, null, 128, null);
            this.binding = LazyKt.lazy(new Function0<HkViewFileUploadClBinding>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$binding$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final HkViewFileUploadClBinding invoke() {
                    return HkViewFileUploadClBinding.bind(FormFragment.FileUpload.this.getView());
                }
            });
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public WorkflowModule.Properties.Section.Component getComponent() {
            return this.component;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout getParent() {
            return this.parent;
        }

        public final HkViewFileUploadClBinding getBinding$hyperkyc_release() {
            return (HkViewFileUploadClBinding) this.binding.getValue();
        }

        public final List<PickedFile> getPickedFiles$hyperkyc_release() {
            List<PickedFile> currentList = this.pickedFilesRvAdapter.getCurrentList();
            Intrinsics.checkNotNullExpressionValue(currentList, "pickedFilesRvAdapter.currentList");
            ArrayList arrayList = new ArrayList();
            for (Object obj : currentList) {
                if (((PickedFile) obj).getUri() != null) {
                    arrayList.add(obj);
                }
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<PickedFile> getAdapterCurrentList() {
            List<PickedFile> currentList = this.pickedFilesRvAdapter.getCurrentList();
            Intrinsics.checkNotNullExpressionValue(currentList, "pickedFilesRvAdapter.currentList");
            return currentList;
        }

        public final boolean getHasFileErrors$hyperkyc_release() {
            List<PickedFile> pickedFiles$hyperkyc_release = getPickedFiles$hyperkyc_release();
            if ((pickedFiles$hyperkyc_release instanceof Collection) && pickedFiles$hyperkyc_release.isEmpty()) {
                return false;
            }
            Iterator<T> it = pickedFiles$hyperkyc_release.iterator();
            while (it.hasNext()) {
                if (((PickedFile) it.next()).hasError()) {
                    return true;
                }
            }
            return false;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void postInflate() {
            super.postInflate();
            String componentValue$hyperkyc_release = getComponentValue$hyperkyc_release();
            updateData("value", componentValue$hyperkyc_release == null ? "" : componentValue$hyperkyc_release);
            if (componentValue$hyperkyc_release != null) {
                setOnChangeEnabled$hyperkyc_release(false);
                List split$default = StringsKt.split$default((CharSequence) componentValue$hyperkyc_release, new String[]{","}, false, 0, 6, (Object) null);
                ArrayList arrayList = new ArrayList();
                Iterator it = split$default.iterator();
                while (it.hasNext()) {
                    File file = new File((String) it.next());
                    Uri uri = null;
                    if (!file.exists()) {
                        file = null;
                    }
                    if (file != null) {
                        uri = Uri.fromFile(file);
                        Intrinsics.checkNotNullExpressionValue(uri, "fromFile(this)");
                    }
                    if (uri != null) {
                        arrayList.add(uri);
                    }
                }
                addPickedFiles$hyperkyc_release(arrayList);
            }
            initEventObservers();
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void updateView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
            HkViewFileUploadClBinding updateView$lambda$9 = getBinding$hyperkyc_release();
            FormFragment formFragment = this.this$0;
            boolean z = false;
            if (reloadProps != null) {
                Intrinsics.checkNotNullExpressionValue(updateView$lambda$9, "updateView$lambda$9");
                String title = reloadProps.getTitle();
                String subTitle = reloadProps.getSubTitle();
                if (Intrinsics.areEqual((Object) formFragment.asBoolean$hyperkyc_release(reloadProps.getRequired(), true), (Object) true) && Intrinsics.areEqual((Object) formFragment.asBoolean$hyperkyc_release(reloadProps.getEnabled(), true), (Object) true)) {
                    z = true;
                }
                updateTitleSubTitle(updateView$lambda$9, title, subTitle, z);
                return;
            }
            Intrinsics.checkNotNullExpressionValue(updateView$lambda$9, "updateView$lambda$9");
            updateTitleSubTitle(updateView$lambda$9, getComponent().getTitle(), getComponent().getSubTitle(), Intrinsics.areEqual((Object) formFragment.asBoolean$hyperkyc_release(getComponent().getRequired(), true), (Object) true) && Intrinsics.areEqual((Object) formFragment.asBoolean$hyperkyc_release(getComponent().getEnabled(), true), (Object) true));
            Iterator<View> it = ViewGroupKt.getChildren(getView()).iterator();
            while (it.hasNext()) {
                it.next().setEnabled(getView().isEnabled());
            }
            RecyclerView updateView$lambda$9$lambda$8 = updateView$lambda$9.rvFiles;
            updateView$lambda$9$lambda$8.setLayoutManager(new LinearLayoutManager(updateView$lambda$9$lambda$8.getContext(), 0, false));
            updateView$lambda$9$lambda$8.setAdapter(this.pickedFilesRvAdapter);
            Intrinsics.checkNotNullExpressionValue(updateView$lambda$9$lambda$8, "updateView$lambda$9$lambda$8");
            UIExtsKt.removeAllItemDecorations(updateView$lambda$9$lambda$8);
            updateView$lambda$9$lambda$8.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$updateView$1$2$1
                @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    Intrinsics.checkNotNullParameter(outRect, "outRect");
                    Intrinsics.checkNotNullParameter(view, "view");
                    Intrinsics.checkNotNullParameter(parent, "parent");
                    Intrinsics.checkNotNullParameter(state, "state");
                    int dp = ViewExtsKt.getDp(6);
                    int childAdapterPosition = parent.getChildAdapterPosition(view);
                    RecyclerView.Adapter adapter = parent.getAdapter();
                    Intrinsics.checkNotNull(adapter);
                    int itemCount = adapter.getItemCount();
                    if (childAdapterPosition == 0 && itemCount == 1) {
                        outRect.set(0, 0, 0, 0);
                    } else if (childAdapterPosition < itemCount - 1) {
                        outRect.set(0, 0, dp, 0);
                    } else {
                        outRect.set(0, 0, 0, 0);
                    }
                }
            });
            RecyclerView.ItemAnimator itemAnimator = updateView$lambda$9$lambda$8.getItemAnimator();
            if (itemAnimator != null) {
                itemAnimator.setChangeDuration(50L);
                itemAnimator.setAddDuration(50L);
                itemAnimator.setRemoveDuration(50L);
            }
        }

        private final void updateTitleSubTitle(HkViewFileUploadClBinding hkViewFileUploadClBinding, String str, String str2, boolean z) {
            String stringInjectFromVariables$hyperkyc_release$default;
            String stringInjectFromVariables$hyperkyc_release$default2;
            MaterialTextView materialTextView = hkViewFileUploadClBinding.tvTitle;
            FormFragment formFragment = this.this$0;
            materialTextView.setTextColor(formFragment.getTitleTextColor$hyperkyc_release());
            materialTextView.setTypeface(formFragment.getTitleTextFont$hyperkyc_release());
            if (str != null && (stringInjectFromVariables$hyperkyc_release$default2 = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, str, false, 1, null)) != null) {
                materialTextView.setText(formFragment.appendRequired$hyperkyc_release(stringInjectFromVariables$hyperkyc_release$default2, z));
            }
            MaterialTextView materialTextView2 = hkViewFileUploadClBinding.tvSubTitle;
            FormFragment formFragment2 = this.this$0;
            materialTextView2.setTextColor(formFragment2.getSubtitleTextColor$hyperkyc_release());
            materialTextView2.setTypeface(formFragment2.getDescTextFont$hyperkyc_release());
            if (str2 == null || (stringInjectFromVariables$hyperkyc_release$default = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment2, str2, false, 1, null)) == null) {
                return;
            }
            Spanned fromHtml = HtmlCompat.fromHtml(stringInjectFromVariables$hyperkyc_release$default, 0, null, null);
            Intrinsics.checkNotNullExpressionValue(fromHtml, "fromHtml(this, flags, imageGetter, tagHandler)");
            materialTextView2.setText(fromHtml);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void initEventObservers$lambda$15(Function1 tmp0, Object obj) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            tmp0.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void addPickedFiles$refreshRV$lambda$20(FileUpload this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.pickedFilesRvAdapter.notifyDataSetChanged();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ boolean isAtOrAboveFileCountMax$default(FileUpload fileUpload, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                List<PickedFile> currentList = fileUpload.pickedFilesRvAdapter.getCurrentList();
                Intrinsics.checkNotNullExpressionValue(currentList, "pickedFilesRvAdapter.currentList");
                List<PickedFile> list = currentList;
                if ((list instanceof Collection) && list.isEmpty()) {
                    i = 0;
                } else {
                    Iterator<T> it = list.iterator();
                    int i3 = 0;
                    while (it.hasNext()) {
                        if ((((PickedFile) it.next()).getUri() != null) && (i3 = i3 + 1) < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                    }
                    i = i3;
                }
            }
            return fileUpload.isAtOrAboveFileCountMax(i);
        }

        private final boolean isAtOrAboveFileCountMax(int pickedFilesCount) {
            String stringInjectFromVariables$hyperkyc_release$default;
            Integer intOrNull;
            String maxNumberOfFiles = getComponent().getMaxNumberOfFiles();
            return pickedFilesCount >= ((maxNumberOfFiles == null || (stringInjectFromVariables$hyperkyc_release$default = FormFragment.stringInjectFromVariables$hyperkyc_release$default(this.this$0, maxNumberOfFiles, false, 1, null)) == null || (intOrNull = StringsKt.toIntOrNull(stringInjectFromVariables$hyperkyc_release$default)) == null) ? Integer.MAX_VALUE : intOrNull.intValue());
        }

        /* compiled from: FormFragment.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$FileUpload$AddFileVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemBinding", "Lco/hyperverge/hyperkyc/databinding/HkRvItemFormAddFileBinding;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment$FileUpload;Lco/hyperverge/hyperkyc/databinding/HkRvItemFormAddFileBinding;)V", "bind", "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public final class AddFileVH extends RecyclerView.ViewHolder {
            private final HkRvItemFormAddFileBinding itemBinding;
            final /* synthetic */ FileUpload this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AddFileVH(FileUpload fileUpload, HkRvItemFormAddFileBinding itemBinding) {
                super(itemBinding.getRoot());
                Intrinsics.checkNotNullParameter(itemBinding, "itemBinding");
                this.this$0 = fileUpload;
                this.itemBinding = itemBinding;
            }

            public final void bind() {
                String canonicalName;
                Object m1202constructorimpl;
                String className;
                String substringAfterLast$default;
                String className2;
                FileUpload fileUpload = this.this$0;
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
                String str2 = fileUpload.getCompLogTag() + " bind() called";
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
                            String str3 = fileUpload.getCompLogTag() + " bind() called";
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
                HkRvItemFormAddFileBinding hkRvItemFormAddFileBinding = this.itemBinding;
                final FileUpload fileUpload2 = this.this$0;
                hkRvItemFormAddFileBinding.getRoot().setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$AddFileVH$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FormFragment.FileUpload.AddFileVH.bind$lambda$2$lambda$1(FormFragment.FileUpload.this, view);
                    }
                });
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void bind$lambda$2$lambda$1(FileUpload this$0, View view) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                this$0.showFileUploadOptionsBS$hyperkyc_release();
            }
        }

        /* compiled from: FormFragment.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$FileUpload$PickedFileVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemBinding", "Lco/hyperverge/hyperkyc/databinding/HkRvItemFormFileBinding;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment$FileUpload;Lco/hyperverge/hyperkyc/databinding/HkRvItemFormFileBinding;)V", "bind", "", "pickedFile", "Lco/hyperverge/hyperkyc/ui/form/models/PickedFile;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public final class PickedFileVH extends RecyclerView.ViewHolder {
            private final HkRvItemFormFileBinding itemBinding;
            final /* synthetic */ FileUpload this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public PickedFileVH(FileUpload fileUpload, HkRvItemFormFileBinding itemBinding) {
                super(itemBinding.getRoot());
                Intrinsics.checkNotNullParameter(itemBinding, "itemBinding");
                this.this$0 = fileUpload;
                this.itemBinding = itemBinding;
            }

            /* JADX WARN: Code restructure failed: missing block: B:79:0x014e, code lost:
            
                if (r0 == null) goto L55;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void bind(final PickedFile pickedFile) {
                String canonicalName;
                Object m1202constructorimpl;
                String canonicalName2;
                String className;
                int i;
                String className2;
                Intrinsics.checkNotNullParameter(pickedFile, "pickedFile");
                FileUpload fileUpload = this.this$0;
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
                String str2 = fileUpload.getCompLogTag() + " bind() called with: pickedFile = " + pickedFile;
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
                            String str3 = fileUpload.getCompLogTag() + " bind() called with: pickedFile = " + pickedFile;
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
                HkRvItemFormFileBinding hkRvItemFormFileBinding = this.itemBinding;
                final FileUpload fileUpload2 = this.this$0;
                final FormFragment formFragment = fileUpload2.this$0;
                boolean isSizeOutOfRange = pickedFile.isSizeOutOfRange();
                if (!pickedFile.isLoading()) {
                    ProgressBar progressBar = hkRvItemFormFileBinding.progressBar;
                    Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
                    progressBar.setVisibility(8);
                    AppCompatImageView ivRemove = hkRvItemFormFileBinding.ivRemove;
                    Intrinsics.checkNotNullExpressionValue(ivRemove, "ivRemove");
                    ivRemove.setVisibility(0);
                    AppCompatImageView ivPreview = hkRvItemFormFileBinding.ivPreview;
                    Intrinsics.checkNotNullExpressionValue(ivPreview, "ivPreview");
                    ivPreview.setVisibility(0);
                    MaterialTextView tvFileSize = hkRvItemFormFileBinding.tvFileSize;
                    Intrinsics.checkNotNullExpressionValue(tvFileSize, "tvFileSize");
                    tvFileSize.setVisibility(0);
                    MaterialTextView tvFileName = hkRvItemFormFileBinding.tvFileName;
                    Intrinsics.checkNotNullExpressionValue(tvFileName, "tvFileName");
                    tvFileName.setVisibility(Intrinsics.areEqual(pickedFile.getType(), "images") ^ true ? 0 : 8);
                    hkRvItemFormFileBinding.tvFileName.setText(pickedFile.getName());
                    hkRvItemFormFileBinding.tvFileSize.setText(pickedFile.getSizeLabel());
                    MaterialTextView materialTextView = hkRvItemFormFileBinding.tvFileSize;
                    if (isSizeOutOfRange) {
                        i = R.drawable.hk_bg_rounded_error_red;
                    } else {
                        i = R.drawable.hk_bg_rounded_black_trans;
                    }
                    materialTextView.setBackgroundResource(i);
                }
                MaterialCardView root = hkRvItemFormFileBinding.getRoot();
                if (pickedFile.isError() || isSizeOutOfRange) {
                    Context context = root.getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "context");
                    root.setStrokeColor(UIExtsKt.colorOf(context, R.color.hk_form_error_color));
                    root.setStrokeWidth(2);
                } else {
                    Context context2 = root.getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "context");
                    root.setStrokeColor(UIExtsKt.colorOf(context2, R.color.hk_form_border_color));
                    root.setStrokeWidth(1);
                }
                hkRvItemFormFileBinding.ivPreview.setImageURI(null);
                String type = pickedFile.getType();
                if (Intrinsics.areEqual(type, "images")) {
                    hkRvItemFormFileBinding.ivPreview.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    AppCompatImageView ivPreview2 = hkRvItemFormFileBinding.ivPreview;
                    Intrinsics.checkNotNullExpressionValue(ivPreview2, "ivPreview");
                    Uri uri = pickedFile.getUri();
                    Intrinsics.checkNotNull(uri);
                    PicassoExtsKt.load$default(ivPreview2, uri, null, 2, null);
                } else if (Intrinsics.areEqual(type, "documents")) {
                    hkRvItemFormFileBinding.ivPreview.setScaleType(ImageView.ScaleType.CENTER);
                    hkRvItemFormFileBinding.ivPreview.setImageResource(R.drawable.hk_ic_pdf_24dp);
                } else {
                    hkRvItemFormFileBinding.ivPreview.setScaleType(ImageView.ScaleType.CENTER);
                    hkRvItemFormFileBinding.ivPreview.setImageResource(R.drawable.hk_ic_document_18);
                }
                if (pickedFile.isError()) {
                    hkRvItemFormFileBinding.ivPreview.setScaleType(ImageView.ScaleType.CENTER);
                    hkRvItemFormFileBinding.ivPreview.setImageResource(R.drawable.hk_ic_file_error);
                }
                hkRvItemFormFileBinding.ivRemove.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$PickedFileVH$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FormFragment.FileUpload.PickedFileVH.bind$lambda$6$lambda$2(FormFragment.FileUpload.this, pickedFile, view);
                    }
                });
                hkRvItemFormFileBinding.getRoot().setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$PickedFileVH$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FormFragment.FileUpload.PickedFileVH.bind$lambda$6$lambda$5(FormFragment.FileUpload.this, this, formFragment, view);
                    }
                });
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void bind$lambda$6$lambda$2(FileUpload this$0, PickedFile pickedFile, View view) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                Intrinsics.checkNotNullParameter(pickedFile, "$pickedFile");
                this$0.removePickedFile$hyperkyc_release(pickedFile);
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX WARN: Multi-variable type inference failed */
            public static final void bind$lambda$6$lambda$5(FileUpload this$0, PickedFileVH this$1, FormFragment this$2, View view) {
                Object[] objArr;
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                Intrinsics.checkNotNullParameter(this$1, "this$1");
                Intrinsics.checkNotNullParameter(this$2, "this$2");
                List<PickedFile> pickedFiles$hyperkyc_release = this$0.getPickedFiles$hyperkyc_release();
                if (!(pickedFiles$hyperkyc_release instanceof Collection) || !pickedFiles$hyperkyc_release.isEmpty()) {
                    Iterator<T> it = pickedFiles$hyperkyc_release.iterator();
                    while (it.hasNext()) {
                        if (((PickedFile) it.next()).isLoading()) {
                            objArr = false;
                            break;
                        }
                    }
                }
                objArr = true;
                if (objArr == true) {
                    int adapterPosition = this$1.getAdapterPosition() - (!FileUpload.isAtOrAboveFileCountMax$default(this$0, 0, 1, null) ? 1 : 0);
                    Iterable currentList = this$0.pickedFilesRvAdapter.getCurrentList();
                    Intrinsics.checkNotNullExpressionValue(currentList, "pickedFilesRvAdapter.currentList");
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : currentList) {
                        if ((((PickedFile) obj).getUri() != null) != false) {
                            arrayList.add(obj);
                        }
                    }
                    new FormFilesReviewFragment(adapterPosition, arrayList).show(this$2.getParentFragmentManager(), Reflection.getOrCreateKotlinClass(FormFileBSDFragment.class).getSimpleName());
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:54:0x013d, code lost:
        
            if (r0 == null) goto L55;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public ConstraintLayout inflate() {
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
            String str2 = getCompLogTag() + " inflate() called";
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
                        String str3 = getCompLogTag() + " inflate() called";
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
            this.pickedFilesRvAdapter.updateItems(CollectionsKt.listOf(new PickedFile(null, null, null, null, null, PickedFile.State.Success.INSTANCE, null, 95, null)), new Function1<List<? extends PickedFile>, Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$inflate$2
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(List<PickedFile> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<? extends PickedFile> list) {
                    invoke2((List<PickedFile>) list);
                    return Unit.INSTANCE;
                }
            });
            View inflate = this.this$0.getLayoutInflater().inflate(R.layout.hk_view_file_upload_cl, (ViewGroup) null, false);
            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
            return (ConstraintLayout) inflate;
        }

        /* JADX WARN: Code restructure failed: missing block: B:54:0x013d, code lost:
        
            if (r0 == null) goto L55;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final void initEventObservers() {
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
            String str2 = getCompLogTag() + " initEventObservers() called";
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
                        String str3 = getCompLogTag() + " initEventObservers() called";
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
            BuildersKt__Builders_commonKt.launch$default(this.this$0.getLifecycleScope(), null, null, new FormFragment$FileUpload$initEventObservers$2(this.this$0, this, null), 3, null);
            LiveData<FormFileReviewUIEvent> fileReviewUIEvent = this.this$0.getFormVM$hyperkyc_release().getFileReviewUIEvent();
            LifecycleOwner viewLifecycleOwner = this.this$0.getViewLifecycleOwner();
            final FormFragment formFragment = this.this$0;
            final Function1<FormFileReviewUIEvent, Unit> function1 = new Function1<FormFileReviewUIEvent, Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$initEventObservers$3
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(FormFileReviewUIEvent formFileReviewUIEvent) {
                    invoke2(formFileReviewUIEvent);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code restructure failed: missing block: B:39:0x0135, code lost:
                
                    if (r0 != null) goto L57;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:44:0x0147, code lost:
                
                    if (r0 == null) goto L58;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:45:0x014b, code lost:
                
                    r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
                 */
                /* JADX WARN: Code restructure failed: missing block: B:46:0x015a, code lost:
                
                    if (r0.find() == false) goto L61;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:47:0x015c, code lost:
                
                    r8 = r0.replaceAll("");
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
                 */
                /* JADX WARN: Code restructure failed: missing block: B:49:0x0167, code lost:
                
                    if (r8.length() <= 23) goto L67;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:51:0x016b, code lost:
                
                    if (android.os.Build.VERSION.SDK_INT < 26) goto L66;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:52:0x016e, code lost:
                
                    r8 = r8.substring(0, 23);
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
                 */
                /* JADX WARN: Code restructure failed: missing block: B:53:0x0175, code lost:
                
                    r0 = new java.lang.StringBuilder();
                    r3 = "initEventObservers().fileReviewLD.observe called - " + r18;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:54:0x0189, code lost:
                
                    if (r3 != null) goto L70;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:55:0x018b, code lost:
                
                    r3 = "null ";
                 */
                /* JADX WARN: Code restructure failed: missing block: B:56:0x018d, code lost:
                
                    r0.append(r3);
                    r0.append(' ');
                    r0.append("");
                    android.util.Log.println(3, r8, r0.toString());
                 */
                /* JADX WARN: Code restructure failed: missing block: B:58:0x014a, code lost:
                
                    r8 = r0;
                 */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final void invoke2(FormFileReviewUIEvent formFileReviewUIEvent) {
                    String canonicalName3;
                    Class<?> cls3;
                    Object m1202constructorimpl2;
                    String str4;
                    String str5;
                    Class<?> cls4;
                    String className3;
                    String className4;
                    FormFragment.FileUpload fileUpload = FormFragment.FileUpload.this;
                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                    HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    String str6 = "N/A";
                    if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        canonicalName3 = (fileUpload == null || (cls3 = fileUpload.getClass()) == null) ? null : cls3.getCanonicalName();
                        if (canonicalName3 == null) {
                            canonicalName3 = "N/A";
                        }
                    }
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                    if (matcher3.find()) {
                        canonicalName3 = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                    }
                    if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName3 = canonicalName3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb3.append(canonicalName3);
                    sb3.append(" - ");
                    String str7 = "initEventObservers().fileReviewLD.observe called - " + formFileReviewUIEvent;
                    if (str7 == null) {
                        str7 = "null ";
                    }
                    sb3.append(str7);
                    sb3.append(' ');
                    sb3.append("");
                    companion4.log(level2, sb3.toString());
                    if (!CoreExtsKt.isRelease()) {
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
                            if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null) {
                                    str4 = null;
                                } else {
                                    str4 = null;
                                    str5 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                }
                                str5 = (fileUpload == null || (cls4 = fileUpload.getClass()) == null) ? str4 : cls4.getCanonicalName();
                            }
                        }
                    }
                    if (formFileReviewUIEvent != null) {
                        FormFragment.FileUpload.this.removePickedFile$hyperkyc_release(formFileReviewUIEvent.getPickedFile());
                        formFragment.getFormVM$hyperkyc_release().resetFileReviewUIEvent();
                    }
                }
            };
            fileReviewUIEvent.observe(viewLifecycleOwner, new Observer() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FormFragment.FileUpload.initEventObservers$lambda$15(Function1.this, obj);
                }
            });
        }

        /* JADX WARN: Code restructure failed: missing block: B:110:0x014e, code lost:
        
            if (r0 == null) goto L55;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void addPickedFiles$hyperkyc_release(List<? extends Uri> uris) {
            String canonicalName;
            Object m1202constructorimpl;
            String canonicalName2;
            String className;
            String className2;
            Intrinsics.checkNotNullParameter(uris, "uris");
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
            String str2 = getCompLogTag() + " addPickedFiles() called with: uris = [" + uris + ']';
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
                        String str3 = getCompLogTag() + " addPickedFiles() called with: uris = [" + uris + ']';
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
            Context context = this.this$0.getContext();
            final File file = new File(context != null ? context.getFilesDir() : null, "hv/formFiles/");
            if (!file.exists()) {
                file.mkdirs();
            }
            FormFragment formFragment = this.this$0;
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = uris.iterator();
            while (true) {
                boolean z = true;
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                Uri uri = (Uri) next;
                List<PickedFile> currentList = this.pickedFilesRvAdapter.getCurrentList();
                Intrinsics.checkNotNullExpressionValue(currentList, "pickedFilesRvAdapter.currentList");
                List<PickedFile> list = currentList;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator<T> it2 = list.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        } else if (Intrinsics.areEqual(FileExtsKt.getFileName(((PickedFile) it2.next()).getUri(), formFragment.getContentResolver$hyperkyc_release()), FileExtsKt.getFileName(uri, formFragment.getContentResolver$hyperkyc_release()))) {
                            z = false;
                            break;
                        }
                    }
                }
                if (z) {
                    arrayList.add(next);
                }
            }
            List list2 = (List) CoreExtsKt.nullIf(arrayList, new Function1<List<? extends Uri>, Boolean>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$addPickedFiles$3
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(List<? extends Uri> it3) {
                    Intrinsics.checkNotNullParameter(it3, "it");
                    return Boolean.valueOf(it3.isEmpty());
                }
            });
            if (list2 == null) {
                addPickedFiles$refreshRV(this);
                return;
            }
            List list3 = list2;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            Iterator it3 = list3.iterator();
            while (it3.hasNext()) {
                arrayList2.add(new PickedFile((Uri) it3.next(), null, null, null, null, null, null, 126, null));
            }
            final ArrayList arrayList3 = arrayList2;
            final FormFragment formFragment2 = this.this$0;
            SimpleRvAdapter<PickedFile, RecyclerView.ViewHolder> simpleRvAdapter = this.pickedFilesRvAdapter;
            List<PickedFile> currentList2 = simpleRvAdapter.getCurrentList();
            Intrinsics.checkNotNullExpressionValue(currentList2, "pickedFilesRvAdapter.currentList");
            List<? extends PickedFile> mutableList = CollectionsKt.toMutableList((Collection) currentList2);
            ArrayList arrayList4 = arrayList3;
            ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
            Iterator it4 = arrayList4.iterator();
            while (it4.hasNext()) {
                arrayList5.add(((PickedFile) it4.next()).deepCopy());
            }
            mutableList.addAll(arrayList5);
            if (isAtOrAboveFileCountMax(mutableList.size() - 1)) {
                Iterator<? extends PickedFile> it5 = mutableList.iterator();
                while (it5.hasNext()) {
                    if (it5.next().getUri() == null) {
                        it5.remove();
                    }
                }
            }
            simpleRvAdapter.updateItems(mutableList, new Function1<List<? extends PickedFile>, Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$addPickedFiles$5$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<? extends PickedFile> list4) {
                    invoke2((List<PickedFile>) list4);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: FormFragment.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$addPickedFiles$5$2$1", f = "FormFragment.kt", i = {0, 0}, l = {2090}, m = "invokeSuspend", n = {"destination$iv$iv", "index$iv$iv"}, s = {"L$3", "I$0"})
                /* renamed from: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$addPickedFiles$5$2$1, reason: invalid class name */
                /* loaded from: classes2.dex */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ File $formFilesDir;
                    final /* synthetic */ List<PickedFile> $pickedFiles;
                    int I$0;
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    Object L$5;
                    int label;
                    final /* synthetic */ FormFragment.FileUpload this$0;
                    final /* synthetic */ FormFragment this$1;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(List<PickedFile> list, FormFragment.FileUpload fileUpload, FormFragment formFragment, File file, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$pickedFiles = list;
                        this.this$0 = fileUpload;
                        this.this$1 = formFragment;
                        this.$formFilesDir = file;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$pickedFiles, this.this$0, this.this$1, this.$formFilesDir, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Removed duplicated region for block: B:16:0x00a2  */
                    /* JADX WARN: Removed duplicated region for block: B:8:0x0064  */
                    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0099 -> B:5:0x009b). Please report as a decompilation issue!!! */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    public final Object invokeSuspend(Object obj) {
                        int i;
                        AnonymousClass1 anonymousClass1;
                        FormFragment formFragment;
                        FormFragment.FileUpload fileUpload;
                        Collection arrayList;
                        Iterator it;
                        File file;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i2 = this.label;
                        if (i2 == 0) {
                            ResultKt.throwOnFailure(obj);
                            List<PickedFile> list = this.$pickedFiles;
                            FormFragment formFragment2 = this.this$1;
                            FormFragment.FileUpload fileUpload2 = this.this$0;
                            File file2 = this.$formFilesDir;
                            i = 0;
                            anonymousClass1 = this;
                            formFragment = formFragment2;
                            fileUpload = fileUpload2;
                            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                            it = list.iterator();
                            file = file2;
                            if (it.hasNext()) {
                            }
                        } else {
                            if (i2 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            int i3 = this.I$0;
                            arrayList = (Collection) this.L$5;
                            it = (Iterator) this.L$4;
                            Collection collection = (Collection) this.L$3;
                            File file3 = (File) this.L$2;
                            FormFragment.FileUpload fileUpload3 = (FormFragment.FileUpload) this.L$1;
                            FormFragment formFragment3 = (FormFragment) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            anonymousClass1 = this;
                            fileUpload = fileUpload3;
                            formFragment = formFragment3;
                            i = i3;
                            file = file3;
                            arrayList.add(Unit.INSTANCE);
                            arrayList = collection;
                            if (it.hasNext()) {
                                Object next = it.next();
                                int i4 = i + 1;
                                if (i < 0) {
                                    CollectionsKt.throwIndexOverflow();
                                }
                                FormFragment$FileUpload$addPickedFiles$5$2$1$1$1 formFragment$FileUpload$addPickedFiles$5$2$1$1$1 = new FormFragment$FileUpload$addPickedFiles$5$2$1$1$1((PickedFile) next, formFragment, fileUpload, file, null);
                                anonymousClass1.L$0 = formFragment;
                                anonymousClass1.L$1 = fileUpload;
                                anonymousClass1.L$2 = file;
                                anonymousClass1.L$3 = arrayList;
                                anonymousClass1.L$4 = it;
                                anonymousClass1.L$5 = arrayList;
                                anonymousClass1.I$0 = i4;
                                anonymousClass1.label = 1;
                                if (CoroutineExtsKt.onDefault$default(null, formFragment$FileUpload$addPickedFiles$5$2$1$1$1, anonymousClass1, 1, null) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                collection = arrayList;
                                i = i4;
                                arrayList.add(Unit.INSTANCE);
                                arrayList = collection;
                                if (it.hasNext()) {
                                    FormFragment.FileUpload.addPickedFiles$refreshRV(anonymousClass1.this$0);
                                    anonymousClass1.this$0.refreshData();
                                    anonymousClass1.this$0.setOnChangeEnabled$hyperkyc_release(true);
                                    return Unit.INSTANCE;
                                }
                            }
                        }
                    }
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(List<PickedFile> it6) {
                    Intrinsics.checkNotNullParameter(it6, "it");
                    FormFragment.this.getLifecycleScope().launchWhenResumed(new AnonymousClass1(arrayList3, this, FormFragment.this, file, null));
                }
            });
        }

        /* JADX WARN: Code restructure failed: missing block: B:104:0x013d, code lost:
        
            if (r0 == null) goto L55;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void showFileUploadOptionsBS$hyperkyc_release() {
            String canonicalName;
            Object m1202constructorimpl;
            String canonicalName2;
            String className;
            LinkedHashSet linkedHashSet;
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
            String str2 = getCompLogTag() + " showFileUploadOptionsBS() called";
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
                        String str3 = getCompLogTag() + " showFileUploadOptionsBS() called";
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
            List<WorkflowModule.Properties.Section.Component.SupportedFile> supportedFiles = getComponent().getSupportedFiles();
            Intrinsics.checkNotNull(supportedFiles);
            boolean areEqual = Intrinsics.areEqual((Object) this.this$0.asBoolean$hyperkyc_release(getComponent().getAllowMultipleTypes(), false), (Object) true);
            List<PickedFile> currentList = this.pickedFilesRvAdapter.getCurrentList();
            Intrinsics.checkNotNullExpressionValue(currentList, "pickedFilesRvAdapter.currentList");
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = currentList.iterator();
            while (it.hasNext()) {
                String extension = ((PickedFile) it.next()).getExtension();
                if (extension != null) {
                    arrayList.add(extension);
                }
            }
            Set set = CollectionsKt.toSet(arrayList);
            List<WorkflowModule.Properties.Section.Component.SupportedFile> list = supportedFiles;
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : list) {
                if (!CollectionsKt.intersect(((WorkflowModule.Properties.Section.Component.SupportedFile) obj).getExtensions(), set).isEmpty()) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = arrayList2;
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
            Iterator it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                arrayList4.add(((WorkflowModule.Properties.Section.Component.SupportedFile) it2.next()).getType());
            }
            Set set2 = CollectionsKt.toSet(arrayList4);
            ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (WorkflowModule.Properties.Section.Component.SupportedFile supportedFile : list) {
                List<String> overrideAllowedTypes = supportedFile.getOverrideAllowedTypes();
                if (overrideAllowedTypes == null || (linkedHashSet = CollectionsKt.toMutableSet(overrideAllowedTypes)) == null) {
                    linkedHashSet = new LinkedHashSet();
                }
                linkedHashSet.add(supportedFile.getType());
                boolean contains = set2.contains(supportedFile.getType());
                if (set2.isEmpty() || areEqual) {
                    contains = true;
                } else if (supportedFile.getOverrideAllowedTypes() != null) {
                    contains = CollectionsKt.subtract(set2, linkedHashSet).isEmpty();
                }
                supportedFile.setEnabled(contains);
                arrayList5.add(Unit.INSTANCE);
            }
            String id2 = getComponent().getId();
            String pickerTitle = getComponent().getPickerTitle();
            new FormFileBSDFragment(id2, pickerTitle != null ? FormFragment.stringInjectFromVariables$hyperkyc_release$default(this.this$0, pickerTitle, false, 1, null) : null, supportedFiles).show(this.this$0.getParentFragmentManager(), Reflection.getOrCreateKotlinClass(FormFileBSDFragment.class).getSimpleName());
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x0144, code lost:
        
            if (r0 == null) goto L55;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void pickFiles(FormFilePickUIEvent formFilePickUIEvent) {
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
            Function1 function1 = null;
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
            String str2 = getCompLogTag() + " pickFiles() called with: formFilePickUIEvent = " + formFilePickUIEvent;
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
                        String str3 = getCompLogTag() + " pickFiles() called with: formFilePickUIEvent = " + formFilePickUIEvent;
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
            List<String> extensions = formFilePickUIEvent.getSupportedFile().getExtensions();
            HashMap hashMapOf = MapsKt.hashMapOf(TuplesKt.to("jpg", "image/jpg"), TuplesKt.to("jpeg", "image/jpeg"), TuplesKt.to("png", MimeTypes.IMAGE_PNG), TuplesKt.to("pdf", "application/pdf"), TuplesKt.to("mp3", MimeTypes.AUDIO_MPEG), TuplesKt.to("ogg", MimeTypes.AUDIO_OGG), TuplesKt.to("mp4", MimeTypes.VIDEO_MP4));
            ArrayList arrayList = new ArrayList();
            for (Map.Entry entry : hashMapOf.entrySet()) {
                String str4 = (String) entry.getKey();
                String str5 = (String) entry.getValue();
                if (!extensions.contains(str4)) {
                    str5 = null;
                }
                if (str5 != null) {
                    arrayList.add(str5);
                }
            }
            ArrayList arrayList2 = arrayList;
            if (ActivityExtsKt.isFragmentAdded(this.this$0)) {
                if (InternalToolUtils.isTestMode(this.this$0.requireContext())) {
                    List listOf = CollectionsKt.listOf(Uri.fromFile(InternalToolUtils.getDynamicFormFile(formFilePickUIEvent.getSupportedFile().getType())));
                    Function1 function12 = this.this$0.filePickedCallback;
                    if (function12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("filePickedCallback");
                    } else {
                        function1 = function12;
                    }
                    function1.invoke(listOf);
                    return;
                }
                this.this$0.filePickerLauncher.launch(arrayList2.toArray(new String[0]));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x01e8, code lost:
        
            if (new java.io.File(r6).exists() != false) goto L79;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void refreshData() {
            String canonicalName;
            Object m1202constructorimpl;
            String className;
            String substringAfterLast$default;
            boolean z;
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
            String str2 = getCompLogTag() + " refreshData() called onChangeEnabled: " + getOnChangeEnabled();
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
                        String str3 = getCompLogTag() + " refreshData() called onChangeEnabled: " + getOnChangeEnabled();
                        sb2.append(str3 != null ? str3 : "null ");
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str, sb2.toString());
                    }
                }
            }
            List<PickedFile> currentList = this.pickedFilesRvAdapter.getCurrentList();
            Intrinsics.checkNotNullExpressionValue(currentList, "pickedFilesRvAdapter.currentList");
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = currentList.iterator();
            while (true) {
                boolean z2 = true;
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                PickedFile pickedFile = (PickedFile) next;
                if (pickedFile.isSuccess() && pickedFile.getLocalPath() != null) {
                    String localPath = pickedFile.getLocalPath();
                    Intrinsics.checkNotNull(localPath);
                }
                z2 = false;
                if (z2) {
                    arrayList.add(next);
                }
            }
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = arrayList2;
            Iterator it2 = arrayList3.iterator();
            long j = 0;
            while (it2.hasNext()) {
                Long sizeKB = ((PickedFile) it2.next()).getSizeKB();
                j += sizeKB != null ? sizeKB.longValue() : 0L;
            }
            updateData(FormFragment.KEY_TOTAL_SIZE, Long.valueOf(j));
            updateData(FormFragment.KEY_TOTAL_SIZE_LABEL, FileExtsKt.asSizeLabel(Long.valueOf(j)));
            updateData(FormFragment.KEY_NUMBER_OF_FILES, Integer.valueOf(arrayList2.size()));
            List<WorkflowModule.Properties.Section.Component.SupportedFile> supportedFiles = getComponent().getSupportedFiles();
            if (supportedFiles != null) {
                for (WorkflowModule.Properties.Section.Component.SupportedFile supportedFile : supportedFiles) {
                    String type = supportedFile.getType();
                    List<PickedFile> currentList2 = this.pickedFilesRvAdapter.getCurrentList();
                    Intrinsics.checkNotNullExpressionValue(currentList2, "pickedFilesRvAdapter.currentList");
                    List<PickedFile> list = currentList2;
                    if (!(list instanceof Collection) || !list.isEmpty()) {
                        Iterator<T> it3 = list.iterator();
                        while (it3.hasNext()) {
                            if (Intrinsics.areEqual(((PickedFile) it3.next()).getType(), type)) {
                                z = true;
                                break;
                            }
                        }
                    }
                    z = false;
                    updateData(supportedFile.getType() + FormFragment.KEY_TYPE_PICKED_SUFFIX, Boolean.valueOf(z));
                }
            }
            updateData(FormFragment.KEY_NUMBER_OF_FILES, Integer.valueOf(arrayList2.size()));
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
            Iterator it4 = arrayList3.iterator();
            while (it4.hasNext()) {
                arrayList4.add(((PickedFile) it4.next()).getLocalPath());
            }
            String joinToString$default = CollectionsKt.joinToString$default(arrayList4, ",", null, null, 0, null, null, 62, null);
            if (getOnChangeEnabled()) {
                updateValue(joinToString$default);
            } else {
                updateDefaultValue(joinToString$default);
            }
            updateError();
        }

        /* JADX WARN: Code restructure failed: missing block: B:151:0x0139, code lost:
        
            if (r0 == null) goto L55;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final void updateInfoText() {
            String canonicalName;
            Object m1202constructorimpl;
            String canonicalName2;
            String className;
            boolean z;
            Long sizeKB;
            boolean z2;
            boolean z3;
            String errorMessage;
            String str;
            int i;
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
            String str3 = getCompLogTag() + " updateInfoText() called";
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
                        StringBuilder sb2 = new StringBuilder();
                        String str4 = getCompLogTag() + " updateInfoText() called";
                        if (str4 == null) {
                            str4 = "null ";
                        }
                        sb2.append(str4);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str2, sb2.toString());
                    }
                }
            }
            HkViewFileUploadClBinding binding$hyperkyc_release = getBinding$hyperkyc_release();
            FormFragment formFragment = this.this$0;
            AppCompatTextView appCompatTextView = binding$hyperkyc_release.tvInfo;
            List<PickedFile> pickedFiles$hyperkyc_release = getPickedFiles$hyperkyc_release();
            if (!(pickedFiles$hyperkyc_release instanceof Collection) || !pickedFiles$hyperkyc_release.isEmpty()) {
                for (PickedFile pickedFile : pickedFiles$hyperkyc_release) {
                    if (pickedFile.isError() || ((sizeKB = pickedFile.getSizeKB()) != null && sizeKB.longValue() == 0)) {
                        z = true;
                        break;
                    }
                }
            }
            z = false;
            if (z) {
                String errorTextFile = getComponent().getErrorTextFile();
                if (errorTextFile != null) {
                    errorMessage = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, errorTextFile, false, 1, null);
                }
                errorMessage = null;
            } else {
                List<PickedFile> pickedFiles$hyperkyc_release2 = getPickedFiles$hyperkyc_release();
                if (!(pickedFiles$hyperkyc_release2 instanceof Collection) || !pickedFiles$hyperkyc_release2.isEmpty()) {
                    Iterator<T> it = pickedFiles$hyperkyc_release2.iterator();
                    while (it.hasNext()) {
                        if (((PickedFile) it.next()).isSizeAboveMax()) {
                            z2 = true;
                            break;
                        }
                    }
                }
                z2 = false;
                if (z2) {
                    String errorTextSizeMax = getComponent().getErrorTextSizeMax();
                    if (errorTextSizeMax != null) {
                        errorMessage = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, errorTextSizeMax, false, 1, null);
                    }
                    errorMessage = null;
                } else {
                    List<PickedFile> pickedFiles$hyperkyc_release3 = getPickedFiles$hyperkyc_release();
                    if (!(pickedFiles$hyperkyc_release3 instanceof Collection) || !pickedFiles$hyperkyc_release3.isEmpty()) {
                        Iterator<T> it2 = pickedFiles$hyperkyc_release3.iterator();
                        while (it2.hasNext()) {
                            if (((PickedFile) it2.next()).isSizeBelowMin()) {
                                z3 = true;
                                break;
                            }
                        }
                    }
                    z3 = false;
                    if (z3) {
                        String errorTextSizeMin = getComponent().getErrorTextSizeMin();
                        if (errorTextSizeMin != null) {
                            errorMessage = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, errorTextSizeMin, false, 1, null);
                        }
                        errorMessage = null;
                    } else {
                        errorMessage = getErrorMessage();
                    }
                }
            }
            if (getPickedFiles$hyperkyc_release().isEmpty()) {
                String helperTextIdle = getComponent().getHelperTextIdle();
                str = helperTextIdle != null ? FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, helperTextIdle, false, 1, null) : null;
            } else {
                String str5 = errorMessage;
                if (str5 == null || StringsKt.isBlank(str5)) {
                    String helperTextActive = getComponent().getHelperTextActive();
                    str = helperTextActive != null ? FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, helperTextActive, false, 1, null) : null;
                } else {
                    str = str5;
                }
            }
            appCompatTextView.setText(str);
            Context context = appCompatTextView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            String str6 = errorMessage;
            appCompatTextView.setTextColor(UIExtsKt.colorOf(context, str6 == null || StringsKt.isBlank(str6) ? R.color.hk_subtitle_text_color : R.color.hk_form_error_color));
            RecyclerView recyclerView = binding$hyperkyc_release.rvFiles;
            if (getHasFileErrors$hyperkyc_release() || getIsValid()) {
                i = R.drawable.hk_bg_rect_rounded_night_blue;
            } else {
                i = R.drawable.hk_bg_rect_rounded_red;
            }
            recyclerView.setBackgroundResource(i);
        }

        public final void removePickedFile$hyperkyc_release(final PickedFile pickedFile) {
            String canonicalName;
            Object m1202constructorimpl;
            String className;
            String substringAfterLast$default;
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
            String str2 = getCompLogTag() + " removePickedFile() called with: pickedFile = " + pickedFile;
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
                        String str3 = getCompLogTag() + " removePickedFile() called with: pickedFile = " + pickedFile;
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
            this.pickedFilesRvAdapter.removeItem(new Function1<PickedFile, Boolean>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$removePickedFile$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(PickedFile it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Boolean.valueOf(Intrinsics.areEqual(it.getUri(), PickedFile.this.getUri()));
                }
            }, new Function1<List<? extends PickedFile>, Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$removePickedFile$3
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
                    Intrinsics.checkNotNullParameter(it, "it");
                    FormFragment.FileUpload.this.getBinding$hyperkyc_release().rvFiles.invalidateItemDecorations();
                    boolean z = true;
                    if (!FormFragment.FileUpload.isAtOrAboveFileCountMax$default(FormFragment.FileUpload.this, 0, 1, null)) {
                        Iterable currentList = FormFragment.FileUpload.this.pickedFilesRvAdapter.getCurrentList();
                        Intrinsics.checkNotNullExpressionValue(currentList, "pickedFilesRvAdapter.currentList");
                        Iterable iterable = currentList;
                        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                            Iterator it2 = iterable.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                if (((PickedFile) it2.next()).getUri() == null) {
                                    z = false;
                                    break;
                                }
                            }
                        }
                        if (z) {
                            FormFragment.FileUpload.this.pickedFilesRvAdapter.addItem(0, new PickedFile(null, null, null, null, null, PickedFile.State.Success.INSTANCE, null, 95, null));
                        }
                    }
                    FormFragment.FileUpload.this.refreshData();
                }
            });
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void updateError() {
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
            String str2 = getCompLogTag() + " updateError() called with errorMessage: " + getErrorMessage();
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
                        String str3 = getCompLogTag() + " updateError() called with errorMessage: " + getErrorMessage();
                        sb2.append(str3 != null ? str3 : "null ");
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str, sb2.toString());
                    }
                }
            }
            updateInfoText();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x01ef, code lost:
        
            if (r9 != null) goto L91;
         */
        /* JADX WARN: Removed duplicated region for block: B:100:0x019f  */
        /* JADX WARN: Removed duplicated region for block: B:105:0x01ac A[EDGE_INSN: B:105:0x01ac->B:22:0x01ac BREAK  A[LOOP:0: B:98:0x0199->B:104:?], SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:23:0x01ae  */
        /* JADX WARN: Removed duplicated region for block: B:68:0x0311  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x0350  */
        /* JADX WARN: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static final void addPickedFiles$refreshRV(final FileUpload fileUpload) {
            String canonicalName;
            Class<?> cls;
            Object m1202constructorimpl;
            String str;
            String canonicalName2;
            Class<?> cls2;
            String className;
            List<PickedFile> list;
            boolean z;
            Iterator<T> it;
            String str2;
            String str3;
            Object m1202constructorimpl2;
            String str4;
            String str5;
            Class<?> cls3;
            Matcher matcher;
            String str6;
            String className2;
            Class<?> cls4;
            String className3;
            String className4;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                canonicalName = (fileUpload == null || (cls = fileUpload.getClass()) == null) ? null : cls.getCanonicalName();
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
            sb.append("refreshRV() called");
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
                            canonicalName2 = (fileUpload == null || (cls2 = fileUpload.getClass()) == null) ? null : cls2.getCanonicalName();
                            if (canonicalName2 == null) {
                                canonicalName2 = str;
                            }
                        }
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                        if (matcher3.find()) {
                            canonicalName2 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                        }
                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName2 = canonicalName2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        Log.println(3, canonicalName2, "refreshRV() called ");
                    }
                    List<PickedFile> currentList = fileUpload.pickedFilesRvAdapter.getCurrentList();
                    Intrinsics.checkNotNullExpressionValue(currentList, "pickedFilesRvAdapter.currentList");
                    list = currentList;
                    z = true;
                    if ((list instanceof Collection) || !list.isEmpty()) {
                        it = list.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            } else if (!((PickedFile) it.next()).isSuccess()) {
                                z = false;
                                break;
                            }
                        }
                    }
                    if (z) {
                        return;
                    }
                    fileUpload.getBinding$hyperkyc_release().rvFiles.post(new Runnable() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            FormFragment.FileUpload.addPickedFiles$refreshRV$lambda$20(FormFragment.FileUpload.this);
                        }
                    });
                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                    HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb2 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                        str2 = "Throwable().stackTrace";
                    } else {
                        str2 = "Throwable().stackTrace";
                        str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    String canonicalName3 = (fileUpload == null || (cls4 = fileUpload.getClass()) == null) ? null : cls4.getCanonicalName();
                    str3 = canonicalName3 == null ? str : canonicalName3;
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                    if (matcher4.find()) {
                        str3 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                    }
                    if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str3 = str3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb2.append(str3);
                    sb2.append(" - ");
                    String str7 = "addPickedFiles: notifyDataSetChanged count - " + fileUpload.pickedFilesRvAdapter.getItemCount() + ' ';
                    if (str7 == null) {
                        str7 = "null ";
                    }
                    sb2.append(str7);
                    sb2.append(' ');
                    sb2.append("");
                    companion4.log(level2, sb2.toString());
                    if (CoreExtsKt.isRelease()) {
                        return;
                    }
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
                        if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, str2);
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                str4 = null;
                            } else {
                                str4 = null;
                                String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default != null) {
                                    str5 = substringAfterLast$default;
                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                    if (matcher.find()) {
                                        str5 = matcher.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                                    }
                                    if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str5 = str5.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb3 = new StringBuilder();
                                    str6 = "addPickedFiles: notifyDataSetChanged count - " + fileUpload.pickedFilesRvAdapter.getItemCount() + ' ';
                                    if (str6 == null) {
                                        str6 = "null ";
                                    }
                                    sb3.append(str6);
                                    sb3.append(' ');
                                    sb3.append("");
                                    Log.println(3, str5, sb3.toString());
                                    return;
                                }
                            }
                            String canonicalName4 = (fileUpload == null || (cls3 = fileUpload.getClass()) == null) ? str4 : cls3.getCanonicalName();
                            str5 = canonicalName4 == null ? str : canonicalName4;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                            if (matcher.find()) {
                            }
                            if (str5.length() > 23) {
                                str5 = str5.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb32 = new StringBuilder();
                            str6 = "addPickedFiles: notifyDataSetChanged count - " + fileUpload.pickedFilesRvAdapter.getItemCount() + ' ';
                            if (str6 == null) {
                            }
                            sb32.append(str6);
                            sb32.append(' ');
                            sb32.append("");
                            Log.println(3, str5, sb32.toString());
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
            str = "N/A";
            List<PickedFile> currentList2 = fileUpload.pickedFilesRvAdapter.getCurrentList();
            Intrinsics.checkNotNullExpressionValue(currentList2, "pickedFilesRvAdapter.currentList");
            list = currentList2;
            z = true;
            if (list instanceof Collection) {
            }
            it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                }
            }
            if (z) {
            }
        }
    }

    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001R\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0014\u001a\u00020\u0002H\u0016J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016R\u001b\u0010\n\u001a\u00020\u000b8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0019"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$Divider;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "Landroid/widget/FrameLayout;", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "parent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment;Landroid/widget/LinearLayout;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;)V", "binding", "Lco/hyperverge/hyperkyc/databinding/HkViewDividerOptionalBinding;", "getBinding$hyperkyc_release", "()Lco/hyperverge/hyperkyc/databinding/HkViewDividerOptionalBinding;", "binding$delegate", "Lkotlin/Lazy;", "getComponent", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "getParent", "()Landroid/widget/LinearLayout;", "inflate", "updateView", "", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class Divider extends CompView {

        /* renamed from: binding$delegate, reason: from kotlin metadata */
        private final Lazy binding;
        private final WorkflowModule.Properties.Section.Component component;
        private final LinearLayout parent;
        final /* synthetic */ FormFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Divider(FormFragment formFragment, LinearLayout parent, WorkflowModule.Properties.Section.Component component) {
            super(formFragment, parent, component);
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(component, "component");
            this.this$0 = formFragment;
            this.parent = parent;
            this.component = component;
            this.binding = LazyKt.lazy(new Function0<HkViewDividerOptionalBinding>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Divider$binding$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final HkViewDividerOptionalBinding invoke() {
                    return HkViewDividerOptionalBinding.bind(FormFragment.Divider.this.getView());
                }
            });
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout getParent() {
            return this.parent;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public WorkflowModule.Properties.Section.Component getComponent() {
            return this.component;
        }

        public final HkViewDividerOptionalBinding getBinding$hyperkyc_release() {
            return (HkViewDividerOptionalBinding) this.binding.getValue();
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void updateView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
            String stringInjectFromVariables$hyperkyc_release$default;
            String stringInjectFromVariables$hyperkyc_release$default2;
            HkViewDividerOptionalBinding binding$hyperkyc_release = getBinding$hyperkyc_release();
            FormFragment formFragment = this.this$0;
            Spanned spanned = null;
            if (reloadProps != null) {
                String text = reloadProps.getText();
                if (text != null && (stringInjectFromVariables$hyperkyc_release$default2 = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, text, false, 1, null)) != null) {
                    Spanned fromHtml = HtmlCompat.fromHtml(stringInjectFromVariables$hyperkyc_release$default2, 0, null, null);
                    Intrinsics.checkNotNullExpressionValue(fromHtml, "fromHtml(this, flags, imageGetter, tagHandler)");
                    if (fromHtml != null) {
                        binding$hyperkyc_release.tvLabel.setText(fromHtml);
                    }
                }
                updateView$lambda$2$applyUIConfigs(formFragment, binding$hyperkyc_release, this);
                return;
            }
            TextView textView = binding$hyperkyc_release.tvLabel;
            String text2 = getComponent().getText();
            if (text2 != null && (stringInjectFromVariables$hyperkyc_release$default = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, text2, false, 1, null)) != null) {
                spanned = HtmlCompat.fromHtml(stringInjectFromVariables$hyperkyc_release$default, 0, null, null);
                Intrinsics.checkNotNullExpressionValue(spanned, "fromHtml(this, flags, imageGetter, tagHandler)");
            }
            textView.setText(spanned);
            updateView$lambda$2$applyUIConfigs(formFragment, binding$hyperkyc_release, this);
        }

        private static final void updateView$lambda$2$applyUIConfigs(FormFragment formFragment, HkViewDividerOptionalBinding hkViewDividerOptionalBinding, Divider divider) {
            DynamicFormUIConfig dynamicFormUIConfig;
            DynamicFormUtils dynamicFormUtils$hyperkyc_release = formFragment.getDynamicFormUtils$hyperkyc_release();
            FrameLayout hkDividerLayout = hkViewDividerOptionalBinding.hkDividerLayout;
            Intrinsics.checkNotNullExpressionValue(hkDividerLayout, "hkDividerLayout");
            FrameLayout frameLayout = hkDividerLayout;
            TextView tvLabel = hkViewDividerOptionalBinding.tvLabel;
            Intrinsics.checkNotNullExpressionValue(tvLabel, "tvLabel");
            HSUIConfig uiConfigData = formFragment.getMainVM$hyperkyc_release().getUiConfigData();
            if (uiConfigData != null) {
                String moduleId = formFragment.getModuleId$hyperkyc_release();
                Intrinsics.checkNotNullExpressionValue(moduleId, "moduleId");
                dynamicFormUIConfig = uiConfigData.getDynamicFormUIConfig(moduleId, divider.getComponent().getId());
            } else {
                dynamicFormUIConfig = null;
            }
            dynamicFormUtils$hyperkyc_release.customiseDivider(frameLayout, tvLabel, dynamicFormUIConfig);
        }

        /* JADX WARN: Code restructure failed: missing block: B:59:0x013b, code lost:
        
            if (r0 == null) goto L55;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public FrameLayout inflate() {
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
            String str2 = getCompLogTag() + " inflate() called";
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
                        String str3 = getCompLogTag() + " inflate() called";
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
            LayoutInflater layoutInflater = this.this$0.getLayoutInflater();
            if (Intrinsics.areEqual(getComponent().getSubType(), WorkflowModule.Properties.Section.Component.SubType.Divider.OPTIONAL)) {
                View inflate = layoutInflater.inflate(R.layout.hk_view_divider_optional, (ViewGroup) null, false);
                Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type android.widget.FrameLayout");
                return (FrameLayout) inflate;
            }
            throw new NotImplementedError("An operation is not implemented: " + ("subType " + getComponent().getSubType() + " is not supported"));
        }
    }

    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001R\u00020\u0004B\u001d\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0017\u001a\u00020\u0002H\u0016J\b\u0010\u0018\u001a\u00020\u0006H\u0016J\u0012\u0010\u0019\u001a\u00020\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0017R \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001c"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$Container;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "Lco/hyperverge/hyperkyc/ui/custom/ClickableLinearLayout;", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "mode", "", "parent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment;ILandroid/widget/LinearLayout;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;)V", "childOnClickAction", "Lkotlin/Function0;", "", "getChildOnClickAction$hyperkyc_release", "()Lkotlin/jvm/functions/Function0;", "setChildOnClickAction$hyperkyc_release", "(Lkotlin/jvm/functions/Function0;)V", "getComponent", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "getParent", "()Landroid/widget/LinearLayout;", "inflate", "topOffset", "updateView", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class Container extends CompView {
        private Function0<Unit> childOnClickAction;
        private final WorkflowModule.Properties.Section.Component component;
        private final int mode;
        private final LinearLayout parent;
        final /* synthetic */ FormFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Container(FormFragment formFragment, int i, LinearLayout parent, WorkflowModule.Properties.Section.Component component) {
            super(formFragment, parent, component);
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(component, "component");
            this.this$0 = formFragment;
            this.mode = i;
            this.parent = parent;
            this.component = component;
            this.childOnClickAction = new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Container$childOnClickAction$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout getParent() {
            return this.parent;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public WorkflowModule.Properties.Section.Component getComponent() {
            return this.component;
        }

        public final Function0<Unit> getChildOnClickAction$hyperkyc_release() {
            return this.childOnClickAction;
        }

        public final void setChildOnClickAction$hyperkyc_release(Function0<Unit> function0) {
            Intrinsics.checkNotNullParameter(function0, "<set-?>");
            this.childOnClickAction = function0;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void updateView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
            DynamicFormUIConfig dynamicFormUIConfig;
            View view = getView();
            FormFragment formFragment = this.this$0;
            final ClickableLinearLayout clickableLinearLayout = (ClickableLinearLayout) view;
            final WorkflowModule.Properties.Section.Component.Handler onClick = getComponent().getOnClick();
            if (onClick != null) {
                clickableLinearLayout.setInterceptTouch(true);
                clickableLinearLayout.setOnTouchListener(new View.OnTouchListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Container$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnTouchListener
                    public final boolean onTouch(View view2, MotionEvent motionEvent) {
                        boolean updateView$lambda$5$lambda$4$lambda$3;
                        updateView$lambda$5$lambda$4$lambda$3 = FormFragment.Container.updateView$lambda$5$lambda$4$lambda$3(FormFragment.Container.this, onClick, clickableLinearLayout, view2, motionEvent);
                        return updateView$lambda$5$lambda$4$lambda$3;
                    }
                });
            }
            DynamicFormUtils dynamicFormUtils$hyperkyc_release = formFragment.getDynamicFormUtils$hyperkyc_release();
            View view2 = getView();
            HSUIConfig uiConfigData = formFragment.getMainVM$hyperkyc_release().getUiConfigData();
            if (uiConfigData != null) {
                String moduleId = formFragment.getModuleId$hyperkyc_release();
                Intrinsics.checkNotNullExpressionValue(moduleId, "moduleId");
                dynamicFormUIConfig = uiConfigData.getDynamicFormUIConfig(moduleId, getComponent().getId());
            } else {
                dynamicFormUIConfig = null;
            }
            dynamicFormUtils$hyperkyc_release.customiseContainer(view2, dynamicFormUIConfig);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean updateView$lambda$5$lambda$4$lambda$3(Container this$0, WorkflowModule.Properties.Section.Component.Handler onClick, ClickableLinearLayout this_with, View view, MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(onClick, "$onClick");
            Intrinsics.checkNotNullParameter(this_with, "$this_with");
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action == 1) {
                    this$0.childOnClickAction.invoke();
                    CompView.processHandler$hyperkyc_release$default(this$0, onClick, false, 2, null);
                    return false;
                }
                this_with.setPressed(false);
            }
            return true;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public int topOffset() {
            List<CompView<? extends View, ? extends Object>> childCompViews = getChildCompViews();
            if (childCompViews == null) {
                return super.topOffset();
            }
            Iterator<T> it = childCompViews.iterator();
            if (!it.hasNext()) {
                throw new NoSuchElementException();
            }
            int i = ((CompView) it.next()).topOffset();
            while (it.hasNext()) {
                int i2 = ((CompView) it.next()).topOffset();
                if (i < i2) {
                    i = i2;
                }
            }
            return i;
        }

        /* JADX WARN: Code restructure failed: missing block: B:62:0x013d, code lost:
        
            if (r0 == null) goto L55;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public ClickableLinearLayout inflate() {
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
            String str2 = getCompLogTag() + " inflate() called";
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
                        String str3 = getCompLogTag() + " inflate() called";
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
            View inflate = this.this$0.getLayoutInflater().inflate(R.layout.hk_view_container, (ViewGroup) null, false);
            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type co.hyperverge.hyperkyc.ui.custom.ClickableLinearLayout");
            ClickableLinearLayout clickableLinearLayout = (ClickableLinearLayout) inflate;
            FormFragment formFragment = this.this$0;
            clickableLinearLayout.setOrientation(this.mode);
            int i = this.mode;
            if (i == 0) {
                clickableLinearLayout.setPadding(ViewExtsKt.getDp(16), ViewExtsKt.getDp(16) - topOffset(), ViewExtsKt.getDp(4), ViewExtsKt.getDp(16));
            } else if (i == 1) {
                clickableLinearLayout.setPadding(ViewExtsKt.getDp(16), ViewExtsKt.getDp(16), ViewExtsKt.getDp(16), ViewExtsKt.getDp(4));
            }
            clickableLinearLayout.setBackgroundResource(R.drawable.hk_bg_rect_rounded_lt_night_blue);
            List<WorkflowModule.Properties.Section.Component> subComponents = getComponent().getSubComponents();
            if (subComponents != null) {
                setChildCompViews(formFragment.createComponents$hyperkyc_release(clickableLinearLayout, subComponents));
            }
            return clickableLinearLayout;
        }
    }

    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001R\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0014\u001a\u00020\u0002H\u0016J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016R\u001b\u0010\n\u001a\u00020\u000b8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0019"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$Image;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "Landroid/widget/ImageView;", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "parent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment;Landroid/widget/LinearLayout;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;)V", "binding", "Lco/hyperverge/hyperkyc/databinding/HkViewImageBinding;", "getBinding$hyperkyc_release", "()Lco/hyperverge/hyperkyc/databinding/HkViewImageBinding;", "binding$delegate", "Lkotlin/Lazy;", "getComponent", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "getParent", "()Landroid/widget/LinearLayout;", "inflate", "updateView", "", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class Image extends CompView {

        /* renamed from: binding$delegate, reason: from kotlin metadata */
        private final Lazy binding;
        private final WorkflowModule.Properties.Section.Component component;
        private final LinearLayout parent;
        final /* synthetic */ FormFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Image(FormFragment formFragment, LinearLayout parent, WorkflowModule.Properties.Section.Component component) {
            super(formFragment, parent, component);
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(component, "component");
            this.this$0 = formFragment;
            this.parent = parent;
            this.component = component;
            this.binding = LazyKt.lazy(new Function0<HkViewImageBinding>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Image$binding$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final HkViewImageBinding invoke() {
                    return HkViewImageBinding.bind(FormFragment.Image.this.getView());
                }
            });
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout getParent() {
            return this.parent;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public WorkflowModule.Properties.Section.Component getComponent() {
            return this.component;
        }

        public final HkViewImageBinding getBinding$hyperkyc_release() {
            return (HkViewImageBinding) this.binding.getValue();
        }

        /* JADX WARN: Code restructure failed: missing block: B:55:0x013b, code lost:
        
            if (r0 == null) goto L55;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public ImageView inflate() {
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
            String str2 = getCompLogTag() + " inflate() called";
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
                        String str3 = getCompLogTag() + " inflate() called";
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
            View inflate = this.this$0.getLayoutInflater().inflate(R.layout.hk_view_image, (ViewGroup) null, false);
            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type android.widget.ImageView");
            return (ImageView) inflate;
        }

        /* JADX WARN: Code restructure failed: missing block: B:66:0x0142, code lost:
        
            if (r0 == null) goto L55;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void updateView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
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
            DynamicFormUIConfig dynamicFormUIConfig = null;
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
            String str2 = "updateView() called with: reloadProps = [" + reloadProps + ']';
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
                        String str3 = "updateView() called with: reloadProps = [" + reloadProps + ']';
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
            if (reloadProps != null) {
                Object value = reloadProps.getValue();
                if (value != null) {
                    updateView$loadImage(this, this.this$0, (String) value);
                    return;
                }
                return;
            }
            HkViewImageBinding binding$hyperkyc_release = getBinding$hyperkyc_release();
            FormFragment formFragment = this.this$0;
            binding$hyperkyc_release.hkFormImageView.setClickable(false);
            Object value2 = getComponent().getValue();
            if (value2 != null) {
                Intrinsics.checkNotNull(value2, "null cannot be cast to non-null type kotlin.String");
                updateView$loadImage(this, formFragment, (String) value2);
            }
            DynamicFormUtils dynamicFormUtils$hyperkyc_release = formFragment.getDynamicFormUtils$hyperkyc_release();
            ImageView hkFormImageView = binding$hyperkyc_release.hkFormImageView;
            Intrinsics.checkNotNullExpressionValue(hkFormImageView, "hkFormImageView");
            HSUIConfig uiConfigData = formFragment.getMainVM$hyperkyc_release().getUiConfigData();
            if (uiConfigData != null) {
                String moduleId = formFragment.getModuleId$hyperkyc_release();
                Intrinsics.checkNotNullExpressionValue(moduleId, "moduleId");
                dynamicFormUIConfig = uiConfigData.getDynamicFormUIConfig(moduleId, getComponent().getId());
            }
            dynamicFormUtils$hyperkyc_release.customiseImage(hkFormImageView, dynamicFormUIConfig);
        }

        /* JADX WARN: Code restructure failed: missing block: B:63:0x0142, code lost:
        
            if (r0 == null) goto L57;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private static final void updateView$loadImage(Image image, FormFragment formFragment, String str) {
            String canonicalName;
            Class<?> cls;
            Object m1202constructorimpl;
            String canonicalName2;
            Class<?> cls2;
            String className;
            String stringInjectFromVariablesForAny$hyperkyc_release$default;
            String nullIfBlank;
            String className2;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str2 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                canonicalName = (image == null || (cls = image.getClass()) == null) ? null : cls.getCanonicalName();
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
            String str3 = "loadImage() called with: url = [" + str + ']';
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
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            canonicalName2 = (image == null || (cls2 = image.getClass()) == null) ? null : cls2.getCanonicalName();
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
                        StringBuilder sb2 = new StringBuilder();
                        String str4 = "loadImage() called with: url = [" + str + ']';
                        if (str4 == null) {
                            str4 = "null ";
                        }
                        sb2.append(str4);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str2, sb2.toString());
                    }
                }
            }
            if (str == null || (stringInjectFromVariablesForAny$hyperkyc_release$default = FormFragment.stringInjectFromVariablesForAny$hyperkyc_release$default(formFragment, str, false, 1, null)) == null || (nullIfBlank = CoreExtsKt.nullIfBlank(stringInjectFromVariablesForAny$hyperkyc_release$default)) == null) {
                return;
            }
            ImageView imageView = image.getBinding$hyperkyc_release().hkFormImageView;
            Intrinsics.checkNotNullExpressionValue(imageView, "binding.hkFormImageView");
            PicassoExtsKt.load(imageView, nullIfBlank);
        }
    }

    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001R\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0014\u001a\u00020\u0002H\u0016J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016R\u001b\u0010\n\u001a\u00020\u000b8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0019"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$Loader;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "Landroid/widget/ProgressBar;", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "parent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment;Landroid/widget/LinearLayout;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;)V", "binding", "Lco/hyperverge/hyperkyc/databinding/HkViewLoaderBinding;", "getBinding$hyperkyc_release", "()Lco/hyperverge/hyperkyc/databinding/HkViewLoaderBinding;", "binding$delegate", "Lkotlin/Lazy;", "getComponent", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "getParent", "()Landroid/widget/LinearLayout;", "inflate", "updateView", "", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class Loader extends CompView {

        /* renamed from: binding$delegate, reason: from kotlin metadata */
        private final Lazy binding;
        private final WorkflowModule.Properties.Section.Component component;
        private final LinearLayout parent;
        final /* synthetic */ FormFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Loader(FormFragment formFragment, LinearLayout parent, WorkflowModule.Properties.Section.Component component) {
            super(formFragment, parent, component);
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(component, "component");
            this.this$0 = formFragment;
            this.parent = parent;
            this.component = component;
            this.binding = LazyKt.lazy(new Function0<HkViewLoaderBinding>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Loader$binding$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final HkViewLoaderBinding invoke() {
                    return HkViewLoaderBinding.bind(FormFragment.Loader.this.getView());
                }
            });
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout getParent() {
            return this.parent;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public WorkflowModule.Properties.Section.Component getComponent() {
            return this.component;
        }

        public final HkViewLoaderBinding getBinding$hyperkyc_release() {
            return (HkViewLoaderBinding) this.binding.getValue();
        }

        /* JADX WARN: Code restructure failed: missing block: B:55:0x013b, code lost:
        
            if (r0 == null) goto L55;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public ProgressBar inflate() {
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
            String str2 = getCompLogTag() + " inflate() called";
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
                        String str3 = getCompLogTag() + " inflate() called";
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
            View inflate = this.this$0.getLayoutInflater().inflate(R.layout.hk_view_loader, (ViewGroup) null, false);
            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type android.widget.ProgressBar");
            return (ProgressBar) inflate;
        }

        /* JADX WARN: Code restructure failed: missing block: B:51:0x0120, code lost:
        
            if (r0 == null) goto L52;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void updateView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
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
            DynamicFormUIConfig dynamicFormUIConfig = null;
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
            sb.append("updateView() called");
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
                        Log.println(3, str, "updateView() called ");
                    }
                }
            }
            HkViewLoaderBinding binding$hyperkyc_release = getBinding$hyperkyc_release();
            FormFragment formFragment = this.this$0;
            binding$hyperkyc_release.hkFormLoader.setClickable(false);
            DynamicFormUtils dynamicFormUtils$hyperkyc_release = formFragment.getDynamicFormUtils$hyperkyc_release();
            ProgressBar hkFormLoader = binding$hyperkyc_release.hkFormLoader;
            Intrinsics.checkNotNullExpressionValue(hkFormLoader, "hkFormLoader");
            HSUIConfig uiConfigData = formFragment.getMainVM$hyperkyc_release().getUiConfigData();
            if (uiConfigData != null) {
                String moduleId = formFragment.getModuleId$hyperkyc_release();
                Intrinsics.checkNotNullExpressionValue(moduleId, "moduleId");
                dynamicFormUIConfig = uiConfigData.getDynamicFormUIConfig(moduleId, getComponent().getId());
            }
            dynamicFormUtils$hyperkyc_release.customiseLoader(hkFormLoader, dynamicFormUIConfig);
        }
    }

    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001R\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0017\u001a\u00020\u0002H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u0015\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001dJ\u0012\u0010\u001e\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016R\u001b\u0010\n\u001a\u00020\u000b8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$Timer;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "Lcom/google/android/material/textview/MaterialTextView;", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "parent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment;Landroid/widget/LinearLayout;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;)V", "binding", "Lco/hyperverge/hyperkyc/databinding/HkViewTimerBinding;", "getBinding$hyperkyc_release", "()Lco/hyperverge/hyperkyc/databinding/HkViewTimerBinding;", "binding$delegate", "Lkotlin/Lazy;", "getComponent", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "countdownTimer", "Landroid/os/CountDownTimer;", "getParent", "()Landroid/widget/LinearLayout;", "timerText", "inflate", "updateText", "", "updateValueAndData", "duration", "", "updateValueAndData$hyperkyc_release", "updateView", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class Timer extends CompView<MaterialTextView, String> {

        /* renamed from: binding$delegate, reason: from kotlin metadata */
        private final Lazy binding;
        private final WorkflowModule.Properties.Section.Component component;
        private CountDownTimer countdownTimer;
        private final LinearLayout parent;
        final /* synthetic */ FormFragment this$0;
        private String timerText;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Timer(FormFragment formFragment, LinearLayout parent, WorkflowModule.Properties.Section.Component component) {
            super(formFragment, parent, component);
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(component, "component");
            this.this$0 = formFragment;
            this.parent = parent;
            this.component = component;
            this.binding = LazyKt.lazy(new Function0<HkViewTimerBinding>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Timer$binding$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final HkViewTimerBinding invoke() {
                    return HkViewTimerBinding.bind(FormFragment.Timer.this.getView());
                }
            });
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout getParent() {
            return this.parent;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public WorkflowModule.Properties.Section.Component getComponent() {
            return this.component;
        }

        public final HkViewTimerBinding getBinding$hyperkyc_release() {
            return (HkViewTimerBinding) this.binding.getValue();
        }

        private static final void updateView$applyUIConfigs(FormFragment formFragment, Timer timer) {
            DynamicFormUIConfig dynamicFormUIConfig;
            DynamicFormUtils dynamicFormUtils$hyperkyc_release = formFragment.getDynamicFormUtils$hyperkyc_release();
            MaterialTextView view = timer.getView();
            HSUIConfig uiConfigData = formFragment.getMainVM$hyperkyc_release().getUiConfigData();
            if (uiConfigData != null) {
                String moduleId = formFragment.getModuleId$hyperkyc_release();
                Intrinsics.checkNotNullExpressionValue(moduleId, "moduleId");
                dynamicFormUIConfig = uiConfigData.getDynamicFormUIConfig(moduleId, timer.getComponent().getId());
            } else {
                dynamicFormUIConfig = null;
            }
            dynamicFormUtils$hyperkyc_release.customiseTimerLabel(view, dynamicFormUIConfig);
        }

        private static final void updateView$runTimer(final Timer timer, FormFragment formFragment, String str) {
            String stringInjectFromVariables$hyperkyc_release$default;
            String stringInjectFromVariables$hyperkyc_release$default2;
            timer.updateData("state", WorkflowModule.Properties.Section.Component.State.STATE_IDLE);
            if (Intrinsics.areEqual(timer.getComponent().getSubType(), WorkflowModule.Properties.Section.Component.SubType.Timer.COUNTDOWN)) {
                CountDownTimer countDownTimer = (CountDownTimer) formFragment.countdownTimersMap.get(timer.getComponent().getId());
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                int i = 0;
                if (Intrinsics.areEqual((Object) formFragment.asBoolean$hyperkyc_release(str, true), (Object) true)) {
                    String duration = timer.getComponent().getDuration();
                    if (duration != null && (stringInjectFromVariables$hyperkyc_release$default2 = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, duration, false, 1, null)) != null) {
                        i = Integer.parseInt(stringInjectFromVariables$hyperkyc_release$default2);
                    }
                    timer.updateValueAndData$hyperkyc_release(i);
                    if (i > 0) {
                        timer.updateData("state", WorkflowModule.Properties.Section.Component.State.STATE_RUNNING);
                        final long j = i * 1000;
                        timer.countdownTimer = new CountDownTimer(j) { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Timer$updateView$runTimer$1
                            @Override // android.os.CountDownTimer
                            public void onTick(long millisUntilFinished) {
                                FormFragment.Timer.this.updateValueAndData$hyperkyc_release(millisUntilFinished / 1000);
                            }

                            @Override // android.os.CountDownTimer
                            public void onFinish() {
                                FormFragment.Timer.this.updateData("state", WorkflowModule.Properties.Section.Component.State.STATE_COMPLETED);
                                WorkflowModule.Properties.Section.Component.Handler onComplete = FormFragment.Timer.this.getComponent().getOnComplete();
                                if (onComplete != null) {
                                    FormFragment.CompView.processHandler$hyperkyc_release$default(FormFragment.Timer.this, onComplete, false, 2, null);
                                }
                            }
                        };
                        formFragment.countdownTimersMap.put(timer.getComponent().getId(), timer.countdownTimer);
                        CountDownTimer countDownTimer2 = timer.countdownTimer;
                        if (countDownTimer2 != null) {
                            countDownTimer2.start();
                            return;
                        }
                        return;
                    }
                    return;
                }
                String duration2 = timer.getComponent().getDuration();
                if (duration2 != null && (stringInjectFromVariables$hyperkyc_release$default = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, duration2, false, 1, null)) != null) {
                    i = Integer.parseInt(stringInjectFromVariables$hyperkyc_release$default);
                }
                timer.updateValueAndData$hyperkyc_release(i);
                return;
            }
            throw new NotImplementedError("An operation is not implemented: " + ("subType " + timer.getComponent().getSubType() + " is not supported"));
        }

        public final void updateValueAndData$hyperkyc_release(long duration) {
            String convertSecondsToHhMmSs = DateExtsKt.convertSecondsToHhMmSs(duration);
            updateValue(convertSecondsToHhMmSs);
            List split$default = StringsKt.split$default((CharSequence) convertSecondsToHhMmSs, new String[]{":"}, false, 0, 6, (Object) null);
            String str = (String) split$default.get(0);
            String str2 = (String) split$default.get(1);
            String str3 = (String) split$default.get(2);
            updateData(FormFragment.KEY_VALUE_HOURS, str);
            updateData(FormFragment.KEY_VALUE_MINUTES, str2);
            updateData(FormFragment.KEY_VALUE_SECONDS, str3);
            updateText();
        }

        private final void updateText() {
            String str = this.timerText;
            getView().setText(CoreExtsKt.nullIfBlank(str != null ? FormFragment.stringInjectFromVariables$hyperkyc_release$default(this.this$0, str, false, 1, null) : null));
        }

        /* JADX WARN: Code restructure failed: missing block: B:55:0x013b, code lost:
        
            if (r0 == null) goto L55;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public MaterialTextView inflate() {
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
            String str2 = getCompLogTag() + " inflate() called";
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
                        String str3 = getCompLogTag() + " inflate() called";
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
            View inflate = this.this$0.getLayoutInflater().inflate(R.layout.hk_view_timer, (ViewGroup) null, false);
            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type com.google.android.material.textview.MaterialTextView");
            return (MaterialTextView) inflate;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void updateView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
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
            String str2 = "updateView() called with: reloadProps = [" + reloadProps + ']';
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
                        String str3 = "updateView() called with: reloadProps = [" + reloadProps + ']';
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
            this.timerText = getComponent().getText();
            if (reloadProps != null) {
                String text = reloadProps.getText();
                if (text != null) {
                    this.timerText = text;
                    updateText();
                }
                String enabled = reloadProps.getEnabled();
                if (enabled != null) {
                    updateView$runTimer(this, this.this$0, enabled);
                }
                updateView$applyUIConfigs(this.this$0, this);
                return;
            }
            updateView$runTimer(this, this.this$0, getComponent().getEnabled());
            updateView$applyUIConfigs(this.this$0, this);
        }
    }

    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0080\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001R\u00020\u0004:\u0002\u001b\u001cB\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0012\u001a\u00020\u0002H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0003H\u0002J\u0012\u0010\u0016\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0017J\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u001a*\u0004\u0018\u00010\u001a2\u0006\u0010\u0015\u001a\u00020\u0003H\u0002R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$DynamicListV2;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "Landroidx/recyclerview/widget/RecyclerView;", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "parent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment;Landroid/widget/LinearLayout;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;)V", "getComponent", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "dataList", "", "itemOnClickHandler", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler;", "getParent", "()Landroid/widget/LinearLayout;", "inflate", "loadData", "", "data", "updateView", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "injectFromData", "", "ItemVH", "RVAdapter", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class DynamicListV2 extends CompView<RecyclerView, Object> {
        private final WorkflowModule.Properties.Section.Component component;
        private final List<Object> dataList;
        private WorkflowModule.Properties.Section.Component.Handler itemOnClickHandler;
        private final LinearLayout parent;
        final /* synthetic */ FormFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DynamicListV2(FormFragment formFragment, LinearLayout parent, WorkflowModule.Properties.Section.Component component) {
            super(formFragment, parent, component);
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(component, "component");
            this.this$0 = formFragment;
            this.parent = parent;
            this.component = component;
            this.dataList = new ArrayList();
            WorkflowModule.Properties.Section.Component itemsGenerator = getComponent().getItemsGenerator();
            this.itemOnClickHandler = itemsGenerator != null ? itemsGenerator.getOnClick() : null;
            WorkflowModule.Properties.Section.Component itemsGenerator2 = getComponent().getItemsGenerator();
            if (itemsGenerator2 == null) {
                return;
            }
            itemsGenerator2.setOnClick(null);
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout getParent() {
            return this.parent;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public WorkflowModule.Properties.Section.Component getComponent() {
            return this.component;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void loadData$lambda$11(final DynamicListV2 this$0, final FormFragment this$1, JSONArray dataJSONArray) {
            Object m1202constructorimpl;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Intrinsics.checkNotNullParameter(dataJSONArray, "$dataJSONArray");
            RecyclerView view = this$0.getView();
            SimpleRvAdapter simpleRvAdapter = new SimpleRvAdapter(new Function1<Integer, Integer>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$DynamicListV2$loadData$3$1
                public final Integer invoke(int i) {
                    return Integer.valueOf(R.layout.hk_rv_item_form_list);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            }, new Function2<View, Integer, ItemVH>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$DynamicListV2$loadData$3$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                /* JADX WARN: Code restructure failed: missing block: B:34:0x0125, code lost:
                
                    if (r0 != null) goto L54;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:39:0x0137, code lost:
                
                    if (r0 == null) goto L55;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:40:0x013b, code lost:
                
                    r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
                 */
                /* JADX WARN: Code restructure failed: missing block: B:41:0x014a, code lost:
                
                    if (r0.find() == false) goto L58;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:42:0x014c, code lost:
                
                    r8 = r0.replaceAll("");
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
                 */
                /* JADX WARN: Code restructure failed: missing block: B:44:0x0157, code lost:
                
                    if (r8.length() <= 23) goto L64;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:46:0x015b, code lost:
                
                    if (android.os.Build.VERSION.SDK_INT < 26) goto L63;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:47:0x015e, code lost:
                
                    r8 = r8.substring(0, 23);
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
                 */
                /* JADX WARN: Code restructure failed: missing block: B:48:0x0165, code lost:
                
                    android.util.Log.println(3, r8, "loadData: vhBuilder called ");
                 */
                /* JADX WARN: Code restructure failed: missing block: B:50:0x013a, code lost:
                
                    r8 = r0;
                 */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final FormFragment.DynamicListV2.ItemVH invoke(View v, int i) {
                    String canonicalName;
                    Class<?> cls;
                    Object m1202constructorimpl2;
                    String str;
                    String str2;
                    Class<?> cls2;
                    String className;
                    String className2;
                    Intrinsics.checkNotNullParameter(v, "v");
                    FormFragment.DynamicListV2 dynamicListV2 = FormFragment.DynamicListV2.this;
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    String str3 = "N/A";
                    if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        canonicalName = (dynamicListV2 == null || (cls = dynamicListV2.getClass()) == null) ? null : cls.getCanonicalName();
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
                    sb.append("loadData: vhBuilder called");
                    sb.append(' ');
                    sb.append("");
                    companion.log(level, sb.toString());
                    if (!CoreExtsKt.isRelease()) {
                        try {
                            Result.Companion companion2 = Result.INSTANCE;
                            Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                        } catch (Throwable th) {
                            Result.Companion companion3 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                            m1202constructorimpl2 = "";
                        }
                        String packageName = (String) m1202constructorimpl2;
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
                                str2 = (dynamicListV2 == null || (cls2 = dynamicListV2.getClass()) == null) ? str : cls2.getCanonicalName();
                            }
                        }
                    }
                    FormFragment.DynamicListV2 dynamicListV22 = FormFragment.DynamicListV2.this;
                    HkRvItemFormListBinding bind = HkRvItemFormListBinding.bind(v);
                    FormFragment formFragment = this$1;
                    FormFragment.DynamicListV2 dynamicListV23 = FormFragment.DynamicListV2.this;
                    ClickableLinearLayout root = bind.getRoot();
                    Intrinsics.checkNotNullExpressionValue(root, "root");
                    WorkflowModule.Properties.Section.Component itemsGenerator = dynamicListV23.getComponent().getItemsGenerator();
                    Intrinsics.checkNotNull(itemsGenerator);
                    FormFragment.CompView<? extends View, ? extends Object> createComponent$hyperkyc_release = formFragment.createComponent$hyperkyc_release(root, itemsGenerator);
                    createComponent$hyperkyc_release.render(new Margin(0, 0, 0, ViewExtsKt.getDp(12), 7, null));
                    View view2 = createComponent$hyperkyc_release.getView();
                    Intrinsics.checkNotNull(view2, "null cannot be cast to non-null type android.widget.LinearLayout");
                    List<WorkflowModule.Properties.Section.Component> subComponents = dynamicListV23.getComponent().getItemsGenerator().getSubComponents();
                    Intrinsics.checkNotNull(subComponents);
                    List<FormFragment.CompView<? extends View, ? extends Object>> createComponents$hyperkyc_release = formFragment.createComponents$hyperkyc_release((LinearLayout) view2, subComponents);
                    formFragment.renderAll$hyperkyc_release(createComponents$hyperkyc_release, new Margin(0, 0, 0, ViewExtsKt.getDp(12), 7, null));
                    createComponent$hyperkyc_release.setChildCompViews(createComponents$hyperkyc_release);
                    Intrinsics.checkNotNullExpressionValue(bind, "bind(v).apply {\n        …                        }");
                    return new FormFragment.DynamicListV2.ItemVH(dynamicListV22, bind);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ FormFragment.DynamicListV2.ItemVH invoke(View view2, Integer num) {
                    return invoke(view2, num.intValue());
                }
            }, new Function2<ItemVH, Object, Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$DynamicListV2$loadData$3$3
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final FormFragment.DynamicListV2.ItemVH h, Object item) {
                    Intrinsics.checkNotNullParameter(h, "h");
                    Intrinsics.checkNotNullParameter(item, "item");
                    final FormFragment.DynamicListV2 dynamicListV2 = FormFragment.DynamicListV2.this;
                    h.bind(item, new Function1<Object, Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$DynamicListV2$loadData$3$3.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                            invoke2(obj);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Object it) {
                            List list;
                            WorkflowModule.Properties.Section.Component.Handler handler;
                            List list2;
                            Intrinsics.checkNotNullParameter(it, "it");
                            int adapterPosition = FormFragment.DynamicListV2.ItemVH.this.getAdapterPosition();
                            String selectionMode = dynamicListV2.getComponent().getSelectionMode();
                            if (Intrinsics.areEqual(selectionMode, WorkflowModule.Properties.Section.Component.SelectionMode.SINGLE)) {
                                FormFragment.DynamicListV2 dynamicListV22 = dynamicListV2;
                                list2 = dynamicListV22.dataList;
                                dynamicListV22.updateValue(list2.get(adapterPosition));
                            } else if (Intrinsics.areEqual(selectionMode, WorkflowModule.Properties.Section.Component.SelectionMode.MULTIPLE)) {
                                FormFragment.DynamicListV2 dynamicListV23 = dynamicListV2;
                                Object value = dynamicListV23.getValue();
                                ArrayList arrayList = TypeIntrinsics.isMutableList(value) ? (List) value : null;
                                if (arrayList == null) {
                                    arrayList = new ArrayList();
                                }
                                list = dynamicListV2.dataList;
                                arrayList.add(list.get(adapterPosition));
                                dynamicListV23.updateValue(arrayList);
                            }
                            handler = dynamicListV2.itemOnClickHandler;
                            if (handler != null) {
                                FormFragment.CompView.processHandler$hyperkyc_release$default(dynamicListV2, handler, false, 2, null);
                            }
                        }
                    });
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(FormFragment.DynamicListV2.ItemVH itemVH, Object obj) {
                    invoke2(itemVH, obj);
                    return Unit.INSTANCE;
                }
            }, new Function2<Object, Object, Boolean>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$DynamicListV2$loadData$3$4
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function2
                public final Boolean invoke(Object o, Object n) {
                    Intrinsics.checkNotNullParameter(o, "o");
                    Intrinsics.checkNotNullParameter(n, "n");
                    return Boolean.valueOf(o.hashCode() == n.hashCode());
                }
            }, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null);
            try {
                Result.Companion companion = Result.INSTANCE;
                ArrayList arrayList = new ArrayList();
                int length = dataJSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject = dataJSONArray.getJSONObject(i);
                    Intrinsics.checkNotNullExpressionValue(jSONObject, "dataJSONArray.getJSONObject(i)");
                    arrayList.add(jSONObject);
                }
                simpleRvAdapter.submitList(arrayList);
                m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1205exceptionOrNullimpl(m1202constructorimpl) != null) {
                ArrayList arrayList2 = new ArrayList();
                int length2 = dataJSONArray.length();
                for (int i2 = 0; i2 < length2; i2++) {
                    String string = dataJSONArray.getString(i2);
                    Intrinsics.checkNotNullExpressionValue(string, "dataJSONArray.getString(i)");
                    arrayList2.add(string);
                }
                simpleRvAdapter.submitList(arrayList2);
            }
            view.setAdapter(simpleRvAdapter);
        }

        /* JADX WARN: Code restructure failed: missing block: B:74:0x0157, code lost:
        
            if (r0 == null) goto L60;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final String injectFromData(String str, Object obj) {
            String canonicalName;
            Object m1202constructorimpl;
            String canonicalName2;
            String className;
            String valueOf;
            String className2;
            Regex regex = new Regex("\\$item(\\.\\w+)*");
            if (str == null) {
                return null;
            }
            String str2 = str;
            if (!regex.containsMatchIn(str2)) {
                return str;
            }
            Sequence<MatchResult> findAll$default = Regex.findAll$default(regex, str2, 0, 2, null);
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str3 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls = str.getClass();
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
            String str4 = "injectFromData: count: " + SequencesKt.count(findAll$default);
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
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls2 = str.getClass();
                            canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        }
                        str3 = canonicalName2;
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                        if (matcher2.find()) {
                            str3 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                        }
                        if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str3 = str3.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str5 = "injectFromData: count: " + SequencesKt.count(findAll$default);
                        sb2.append(str5 != null ? str5 : "null ");
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str3, sb2.toString());
                    }
                }
            }
            String str6 = str;
            for (MatchResult matchResult : findAll$default) {
                if (Intrinsics.areEqual(matchResult.getValue(), "$item")) {
                    valueOf = obj.toString();
                } else {
                    String substringAfter$default = StringsKt.substringAfter$default(matchResult.getValue(), "$item" + FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                    valueOf = String.valueOf(JSONExtsKt.recursiveGet((JSONObject) obj, substringAfter$default));
                }
                str6 = str6 != null ? StringsKt.replace$default(str6, matchResult.getValue(), valueOf, false, 4, (Object) null) : null;
            }
            return str6;
        }

        /* compiled from: FormFragment.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J1\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\b0\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$DynamicListV2$ItemVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lco/hyperverge/hyperkyc/databinding/HkRvItemFormListBinding;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment$DynamicListV2;Lco/hyperverge/hyperkyc/databinding/HkRvItemFormListBinding;)V", "getBinding", "()Lco/hyperverge/hyperkyc/databinding/HkRvItemFormListBinding;", "bind", "", "data", "", "onClick", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public final class ItemVH extends RecyclerView.ViewHolder {
            private final HkRvItemFormListBinding binding;
            final /* synthetic */ DynamicListV2 this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ItemVH(DynamicListV2 dynamicListV2, HkRvItemFormListBinding binding) {
                super(binding.getRoot());
                Intrinsics.checkNotNullParameter(binding, "binding");
                this.this$0 = dynamicListV2;
                this.binding = binding;
            }

            public final HkRvItemFormListBinding getBinding() {
                return this.binding;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void bind$lambda$1(Function1 onClick, Object data, View view) {
                Intrinsics.checkNotNullParameter(onClick, "$onClick");
                Intrinsics.checkNotNullParameter(data, "$data");
                onClick.invoke(data);
            }

            public final void bind(final Object data, final Function1<Object, Unit> onClick) {
                String canonicalName;
                Object m1202constructorimpl;
                String className;
                String substringAfterLast$default;
                String className2;
                Intrinsics.checkNotNullParameter(data, "data");
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
                String str2 = "bind() called with: data = [" + data + ']';
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
                            String str3 = "bind() called with: data = [" + data + ']';
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
                this.itemView.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$DynamicListV2$ItemVH$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FormFragment.DynamicListV2.ItemVH.bind$lambda$1(Function1.this, data, view);
                    }
                });
            }
        }

        /* compiled from: FormFragment.kt */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u0010\u0012\f\u0012\n0\u0002R\u00060\u0003R\u00020\u00040\u0001B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J \u0010\b\u001a\u00020\t2\u000e\u0010\n\u001a\n0\u0002R\u00060\u0003R\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0007H\u0016J \u0010\f\u001a\n0\u0002R\u00060\u0003R\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0016¨\u0006\u0010"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$DynamicListV2$RVAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$DynamicListV2$ItemVH;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$DynamicListV2;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment$DynamicListV2;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public final class RVAdapter extends RecyclerView.Adapter<ItemVH> {
            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(ItemVH holder, int position) {
                Intrinsics.checkNotNullParameter(holder, "holder");
            }

            public RVAdapter() {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public ItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                DynamicListV2.this.getComponent().getItemsGenerator();
                throw new NotImplementedError(null, 1, null);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                throw new NotImplementedError("An operation is not implemented: Not yet implemented");
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:55:0x013b, code lost:
        
            if (r0 == null) goto L55;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public RecyclerView inflate() {
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
            String str2 = getCompLogTag() + " inflate() called";
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
                        String str3 = getCompLogTag() + " inflate() called";
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
            View inflate = this.this$0.getLayoutInflater().inflate(R.layout.hk_view_dynamic_list_v2, (ViewGroup) null, false);
            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
            RecyclerView recyclerView = (RecyclerView) inflate;
            recyclerView.setNestedScrollingEnabled(true);
            recyclerView.setHasFixedSize(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, false));
            return recyclerView;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(24:1|(3:160|(1:162)(1:165)|(1:164))|7|(1:9)|10|(1:14)|15|(1:17)|18|(6:124|125|126|(1:128)|129|(15:131|(9:133|(3:151|(1:153)(1:156)|(1:155))|139|(1:141)|142|(1:146)|147|(1:149)|150)|21|(1:23)(2:114|(3:116|(1:118)(1:121)|(1:120))(2:122|123))|24|25|26|27|(1:29)|30|31|32|(13:34|(1:109)(1:38)|(1:47)(1:43)|(1:45)(1:46)|48|(1:50)|51|(1:55)|56|(1:58)(1:108)|(1:60)(1:107)|61|(6:66|67|68|(1:70)|71|(2:73|(12:75|(1:103)(1:79)|(1:87)(1:84)|(1:86)|88|(1:90)|91|(1:95)|96|(1:98)(1:102)|(1:100)|101))))(1:110)|63|64))|20|21|(0)(0)|24|25|26|27|(0)|30|31|32|(0)(0)|63|64) */
        /* JADX WARN: Code restructure failed: missing block: B:112:0x01fc, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:113:0x01fd, code lost:
        
            r7 = kotlin.Result.INSTANCE;
            r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0242, code lost:
        
            if (r9 != null) goto L105;
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x033f, code lost:
        
            if (r0 != null) goto L150;
         */
        /* JADX WARN: Removed duplicated region for block: B:110:0x03b8  */
        /* JADX WARN: Removed duplicated region for block: B:114:0x01bf  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x01b6  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x01e6 A[Catch: all -> 0x01fc, LOOP:0: B:28:0x01e4->B:29:0x01e6, LOOP_END, TryCatch #0 {all -> 0x01fc, blocks: (B:27:0x01e1, B:29:0x01e6, B:31:0x01f5), top: B:26:0x01e1 }] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x020d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final void loadData(Object data) {
            String canonicalName;
            Object m1202constructorimpl;
            CharSequence charSequence;
            String str;
            String canonicalName2;
            String className;
            JSONArray jSONArray;
            int length;
            Throwable m1205exceptionOrNullimpl;
            JSONArray jSONArray2;
            String str2;
            String str3;
            Object m1202constructorimpl2;
            String str4;
            String str5;
            Class<?> cls;
            String className2;
            Class<?> cls2;
            String className3;
            int i;
            String className4;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls3 = getClass();
                canonicalName = cls3 != null ? cls3.getCanonicalName() : null;
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
            String str6 = "loadData() called with: data = [" + data + ']';
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
                    charSequence = "co.hyperverge";
                    str = "N/A";
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls4 = getClass();
                            canonicalName2 = cls4 != null ? cls4.getCanonicalName() : null;
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
                        String str7 = "loadData() called with: data = [" + data + ']';
                        if (str7 == null) {
                            str7 = "null ";
                        }
                        sb2.append(str7);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    }
                    if (!(data instanceof ArrayList)) {
                        jSONArray = new JSONArray((Collection) data);
                    } else {
                        if (!(data instanceof String)) {
                            throw new NotImplementedError("An operation is not implemented: Unsupported type passed for data");
                        }
                        Object anyInjectFromVariables$hyperkyc_release = this.this$0.anyInjectFromVariables$hyperkyc_release((String) data);
                        jSONArray = anyInjectFromVariables$hyperkyc_release instanceof JSONArray ? (JSONArray) anyInjectFromVariables$hyperkyc_release : null;
                        if (jSONArray == null) {
                            jSONArray = new JSONArray();
                        }
                    }
                    JSONArray jSONArray3 = jSONArray;
                    length = jSONArray3.length();
                    List<Object> list = this.dataList;
                    Result.Companion companion4 = Result.INSTANCE;
                    for (i = 0; i < length; i++) {
                        Object obj = jSONArray3.get(i);
                        Intrinsics.checkNotNullExpressionValue(obj, "dataJSONArray.get(i)");
                        list.add(obj);
                    }
                    Object m1202constructorimpl3 = Result.m1202constructorimpl(Unit.INSTANCE);
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                    if (m1205exceptionOrNullimpl == null) {
                        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                        HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb3 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                            jSONArray2 = jSONArray3;
                            str2 = "Throwable().stackTrace";
                        } else {
                            jSONArray2 = jSONArray3;
                            str2 = "Throwable().stackTrace";
                            str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        String canonicalName3 = (list == null || (cls2 = list.getClass()) == null) ? null : cls2.getCanonicalName();
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
                        sb3.append("loadData: failed");
                        sb3.append(' ');
                        String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                        sb3.append(localizedMessage != null ? '\n' + localizedMessage : "");
                        companion5.log(level2, sb3.toString());
                        if (!CoreExtsKt.isRelease()) {
                            try {
                                Result.Companion companion6 = Result.INSTANCE;
                                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                            } catch (Throwable th2) {
                                Result.Companion companion7 = Result.INSTANCE;
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
                                    Intrinsics.checkNotNullExpressionValue(stackTrace4, str2);
                                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                    if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                        str4 = null;
                                    } else {
                                        str4 = null;
                                        str5 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    }
                                    str5 = (list == null || (cls = list.getClass()) == null) ? str4 : cls.getCanonicalName();
                                    if (str5 == null) {
                                        str5 = str;
                                    }
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
                                    sb4.append("loadData: failed");
                                    sb4.append(' ');
                                    String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str4;
                                    sb4.append(localizedMessage2 != null ? '\n' + localizedMessage2 : "");
                                    Log.println(5, str5, sb4.toString());
                                }
                            }
                        }
                    } else {
                        jSONArray2 = jSONArray3;
                    }
                    RecyclerView view = getView();
                    final FormFragment formFragment = this.this$0;
                    final JSONArray jSONArray4 = jSONArray2;
                    view.post(new Runnable() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$DynamicListV2$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            FormFragment.DynamicListV2.loadData$lambda$11(FormFragment.DynamicListV2.this, formFragment, jSONArray4);
                        }
                    });
                }
            }
            charSequence = "co.hyperverge";
            str = "N/A";
            if (!(data instanceof ArrayList)) {
            }
            JSONArray jSONArray32 = jSONArray;
            length = jSONArray32.length();
            List<Object> list2 = this.dataList;
            Result.Companion companion42 = Result.INSTANCE;
            while (i < length) {
            }
            Object m1202constructorimpl32 = Result.m1202constructorimpl(Unit.INSTANCE);
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl32);
            if (m1205exceptionOrNullimpl == null) {
            }
            RecyclerView view2 = getView();
            final FormFragment formFragment2 = this.this$0;
            final JSONArray jSONArray42 = jSONArray2;
            view2.post(new Runnable() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$DynamicListV2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    FormFragment.DynamicListV2.loadData$lambda$11(FormFragment.DynamicListV2.this, formFragment2, jSONArray42);
                }
            });
        }

        /* JADX WARN: Code restructure failed: missing block: B:42:0x016e, code lost:
        
            if (r0 != null) goto L61;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x017e, code lost:
        
            if (r0 == null) goto L62;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x0182, code lost:
        
            r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r9);
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x0191, code lost:
        
            if (r0.find() == false) goto L65;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0193, code lost:
        
            r9 = r0.replaceAll("");
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "replaceAll(\"\")");
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x019e, code lost:
        
            if (r9.length() <= 23) goto L71;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x01a2, code lost:
        
            if (android.os.Build.VERSION.SDK_INT < 26) goto L70;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x01a5, code lost:
        
            r9 = r9.substring(0, 23);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "this as java.lang.String…ing(startIndex, endIndex)");
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x01ac, code lost:
        
            r0 = new java.lang.StringBuilder();
            r3 = "updateView: timeTaken: " + r4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x01c0, code lost:
        
            if (r3 != null) goto L74;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x01c2, code lost:
        
            r3 = "null ";
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x01c4, code lost:
        
            r0.append(r3);
            r0.append(' ');
            r0.append("");
            android.util.Log.println(3, r9, r0.toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x01d7, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x0181, code lost:
        
            r9 = r0;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void updateView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
            String canonicalName;
            Object m1202constructorimpl;
            String str;
            String str2;
            String className;
            String className2;
            long currentTimeMillis = System.currentTimeMillis();
            if (reloadProps != null) {
                Object data = reloadProps.getData();
                if (data != null) {
                    loadData(data);
                    return;
                }
                return;
            }
            getView().setPadding(ViewExtsKt.getDp(16), ViewExtsKt.getDp(16), ViewExtsKt.getDp(16), ViewExtsKt.getDp(4));
            Object data2 = getComponent().getData();
            Intrinsics.checkNotNull(data2);
            loadData(data2);
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
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
            String str4 = "updateView: timeTaken: " + currentTimeMillis2;
            if (str4 == null) {
                str4 = "null ";
            }
            sb.append(str4);
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
            if (!CoreExtsKt.isDebug()) {
                return;
            }
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            if (!StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                return;
            }
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

    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001R\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0018\u001a\u00020\u0002H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0003H\u0002J\b\u0010\u001c\u001a\u00020\u001aH\u0016J\b\u0010\u001d\u001a\u00020\u001aH\u0002J5\u0010\u001e\u001a\u00020\u001a2\u001a\u0010\u001f\u001a\u0016\u0012\u0006\b\u0001\u0012\u00020\u0014\u0012\u0006\b\u0001\u0012\u00020\u00030\u0001R\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0003H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010 J\u0016\u0010!\u001a\u00020\u001a2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030\rH\u0002J,\u0010#\u001a\u00020\u001a2\u001a\u0010\u001f\u001a\u0016\u0012\u0006\b\u0001\u0012\u00020\u0014\u0012\u0006\b\u0001\u0012\u00020\u00030\u0001R\u00020\u00042\u0006\u0010$\u001a\u00020\u0003H\u0002J\u0012\u0010%\u001a\u00020\u001a2\b\u0010&\u001a\u0004\u0018\u00010'H\u0017J\u0018\u0010(\u001a\u0004\u0018\u00010)*\u0004\u0018\u00010)2\u0006\u0010\u001b\u001a\u00020\u0003H\u0002R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u0012\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0006\b\u0001\u0012\u00020\u0014\u0012\u0006\b\u0001\u0012\u00020\u00030\u0001R\u00020\u00040\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$DynamicList;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "Lco/hyperverge/hyperkyc/ui/custom/DynamicListGridLayout;", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "parent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment;Landroid/widget/LinearLayout;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;)V", "getComponent", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "dataList", "", "itemOnClickHandler", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler;", "loadJob", "Lkotlinx/coroutines/Job;", "localCompList", "", "Landroid/view/View;", "getParent", "()Landroid/widget/LinearLayout;", "reloadJob", "inflate", "loadData", "", "data", "postInflate", "reloadAllChildren", "reloadComp", "compView", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setData", "updatedDataList", "setItemGeneratorClickHandler", "cellData", "updateView", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "injectFromData", "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class DynamicList extends CompView<DynamicListGridLayout, Object> {
        private final WorkflowModule.Properties.Section.Component component;
        private List<? extends Object> dataList;
        private WorkflowModule.Properties.Section.Component.Handler itemOnClickHandler;
        private Job loadJob;
        private List<CompView<? extends View, ? extends Object>> localCompList;
        private final LinearLayout parent;
        private Job reloadJob;
        final /* synthetic */ FormFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DynamicList(FormFragment formFragment, LinearLayout parent, WorkflowModule.Properties.Section.Component component) {
            super(formFragment, parent, component);
            WorkflowModule.Properties.Section.Component.Handler onClick;
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(component, "component");
            this.this$0 = formFragment;
            this.parent = parent;
            this.component = component;
            this.dataList = CollectionsKt.emptyList();
            this.localCompList = new ArrayList();
            WorkflowModule.Properties.Section.Component itemsGenerator = getComponent().getItemsGenerator();
            this.itemOnClickHandler = (itemsGenerator == null || (onClick = itemsGenerator.getOnClick()) == null) ? null : WorkflowModule.Properties.Section.Component.Handler.copy$default(onClick, null, null, null, null, null, 31, null);
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout getParent() {
            return this.parent;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public WorkflowModule.Properties.Section.Component getComponent() {
            return this.component;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void postInflate() {
            super.postInflate();
            Object componentValue$hyperkyc_release = getComponentValue$hyperkyc_release();
            if (componentValue$hyperkyc_release != null) {
                updateDefaultValue(componentValue$hyperkyc_release);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:37:0x025b, code lost:
        
            if (r1 != null) goto L100;
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x036b, code lost:
        
            if (r0 != null) goto L141;
         */
        /* JADX WARN: Removed duplicated region for block: B:118:0x011c  */
        /* JADX WARN: Removed duplicated region for block: B:121:0x0125  */
        /* JADX WARN: Removed duplicated region for block: B:147:0x01d0  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x01e0  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x03fc  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x040f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final String injectFromData(String str, Object obj) {
            String canonicalName;
            String str2;
            Object m1202constructorimpl;
            CharSequence charSequence;
            String str3;
            String canonicalName2;
            String className;
            Iterator it;
            String valueOf;
            Iterator it2;
            String str4;
            String str5;
            String str6;
            Object m1202constructorimpl2;
            String str7;
            String str8;
            String str9;
            String str10;
            String className2;
            String className3;
            String className4;
            Object obj2 = obj;
            Regex regex = new Regex("\\$item(\\.\\w+)*");
            if (str == null) {
                return null;
            }
            String str11 = str;
            if (!regex.containsMatchIn(str11)) {
                return str;
            }
            Sequence findAll$default = Regex.findAll$default(regex, str11, 0, 2, null);
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            String str12 = "Throwable().stackTrace";
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls = str.getClass();
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
            String str13 = "injectFromData: count: " + SequencesKt.count(findAll$default);
            if (str13 == null) {
                str13 = "null ";
            }
            sb.append(str13);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            String str14 = "packageName";
            if (CoreExtsKt.isRelease()) {
                charSequence = "co.hyperverge";
                str2 = "N/A";
            } else {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    str2 = "N/A";
                    try {
                        Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th) {
                        th = th;
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        }
                        String packageName = (String) m1202constructorimpl;
                        if (!CoreExtsKt.isDebug()) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    str2 = "N/A";
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName2 = (String) m1202constructorimpl;
                if (!CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    charSequence = "co.hyperverge";
                    str3 = "null ";
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls2 = str.getClass();
                            canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            if (canonicalName2 == null) {
                                canonicalName2 = str2;
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
                        String str15 = "injectFromData: count: " + SequencesKt.count(findAll$default);
                        if (str15 == null) {
                            str15 = str3;
                        }
                        sb2.append(str15);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    }
                    it = findAll$default.iterator();
                    String str16 = str;
                    while (it.hasNext()) {
                        MatchResult matchResult = (MatchResult) it.next();
                        if (Intrinsics.areEqual(matchResult.getValue(), "$item")) {
                            valueOf = obj.toString();
                        } else {
                            String substringAfter$default = StringsKt.substringAfter$default(matchResult.getValue(), "$item" + FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type org.json.JSONObject");
                            valueOf = String.valueOf(JSONExtsKt.recursiveGet((JSONObject) obj2, substringAfter$default));
                        }
                        String str17 = valueOf;
                        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb3 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, str12);
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                            it2 = it;
                            str4 = str14;
                            str5 = str12;
                        } else {
                            it2 = it;
                            str4 = str14;
                            str5 = str12;
                            str6 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        Class<?> cls3 = str.getClass();
                        str6 = cls3 != null ? cls3.getCanonicalName() : null;
                        if (str6 == null) {
                            str6 = str2;
                        }
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                        if (matcher3.find()) {
                            str6 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
                        }
                        if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str6 = str6.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb3.append(str6);
                        sb3.append(" - ");
                        String str18 = "injectFromData: replacing " + matchResult.getValue() + " with " + str17 + " for " + str16;
                        if (str18 == null) {
                            str18 = str3;
                        }
                        sb3.append(str18);
                        sb3.append(' ');
                        sb3.append("");
                        companion4.log(level2, sb3.toString());
                        if (!CoreExtsKt.isRelease()) {
                            try {
                                Result.Companion companion5 = Result.INSTANCE;
                                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                            } catch (Throwable th3) {
                                Result.Companion companion6 = Result.INSTANCE;
                                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                m1202constructorimpl2 = "";
                            }
                            String str19 = (String) m1202constructorimpl2;
                            if (CoreExtsKt.isDebug()) {
                                str7 = str4;
                                Intrinsics.checkNotNullExpressionValue(str19, str7);
                                if (!StringsKt.contains$default((CharSequence) str19, charSequence, false, 2, (Object) null)) {
                                    str8 = str5;
                                    if (str16 != null) {
                                    }
                                    str12 = str8;
                                    it = it2;
                                    str14 = str7;
                                    obj2 = obj;
                                } else {
                                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                    str8 = str5;
                                    Intrinsics.checkNotNullExpressionValue(stackTrace4, str8);
                                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                    if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                        str9 = null;
                                    } else {
                                        str9 = null;
                                        str10 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    }
                                    Class<?> cls4 = str.getClass();
                                    str10 = cls4 != null ? cls4.getCanonicalName() : str9;
                                    if (str10 == null) {
                                        str10 = str2;
                                    }
                                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str10);
                                    if (matcher4.find()) {
                                        str10 = matcher4.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str10, "replaceAll(\"\")");
                                    }
                                    if (str10.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str10 = str10.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str10, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb4 = new StringBuilder();
                                    String str20 = "injectFromData: replacing " + matchResult.getValue() + " with " + str17 + " for " + str16;
                                    if (str20 == null) {
                                        str20 = str3;
                                    }
                                    sb4.append(str20);
                                    sb4.append(' ');
                                    sb4.append("");
                                    Log.println(3, str10, sb4.toString());
                                    str16 = str16 != null ? StringsKt.replace$default(str16, matchResult.getValue(), str17, false, 4, (Object) null) : null;
                                    str12 = str8;
                                    it = it2;
                                    str14 = str7;
                                    obj2 = obj;
                                }
                            }
                        }
                        str8 = str5;
                        str7 = str4;
                        if (str16 != null) {
                        }
                        str12 = str8;
                        it = it2;
                        str14 = str7;
                        obj2 = obj;
                    }
                    return str16;
                }
                charSequence = "co.hyperverge";
            }
            str3 = "null ";
            it = findAll$default.iterator();
            String str162 = str;
            while (it.hasNext()) {
            }
            return str162;
        }

        /* JADX WARN: Removed duplicated region for block: B:48:0x018c  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x01c1  */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void updateView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
            String canonicalName;
            Class<?> cls;
            Object m1202constructorimpl;
            String str;
            Class<?> cls2;
            Matcher matcher;
            String className;
            String className2;
            DynamicListGridLayout view = getView();
            FormFragment formFragment = this.this$0;
            DynamicListGridLayout dynamicListGridLayout = view;
            long currentTimeMillis = System.currentTimeMillis();
            if (reloadProps != null && formFragment.isAdded()) {
                Object data = reloadProps.getData();
                if (data != null) {
                    loadData(data);
                    return;
                }
                return;
            }
            Object data2 = getComponent().getData();
            Intrinsics.checkNotNull(data2);
            loadData(data2);
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str2 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                canonicalName = (dynamicListGridLayout == null || (cls = dynamicListGridLayout.getClass()) == null) ? null : cls.getCanonicalName();
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
            String str3 = "updateView: timeTaken: " + currentTimeMillis2;
            if (str3 == null) {
                str3 = "null ";
            }
            sb.append(str3);
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
                            String str4 = "updateView: timeTaken: " + currentTimeMillis2;
                            sb2.append(str4 != null ? str4 : "null ");
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(3, str2, sb2.toString());
                        }
                    }
                    String canonicalName2 = (dynamicListGridLayout == null || (cls2 = dynamicListGridLayout.getClass()) == null) ? str : cls2.getCanonicalName();
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
                    String str42 = "updateView: timeTaken: " + currentTimeMillis2;
                    sb22.append(str42 != null ? str42 : "null ");
                    sb22.append(' ');
                    sb22.append("");
                    Log.println(3, str2, sb22.toString());
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void setItemGeneratorClickHandler$updateComponentValue(DynamicList dynamicList, Object obj) {
            String selectionMode = dynamicList.getComponent().getSelectionMode();
            if (Intrinsics.areEqual(selectionMode, WorkflowModule.Properties.Section.Component.SelectionMode.SINGLE)) {
                dynamicList.updateValue(obj);
            } else {
                Intrinsics.areEqual(selectionMode, WorkflowModule.Properties.Section.Component.SelectionMode.MULTIPLE);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setItemGeneratorClickHandler(CompView<? extends View, ? extends Object> compView, final Object cellData) {
            if (compView instanceof Container) {
                ((Container) compView).setChildOnClickAction$hyperkyc_release(new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$DynamicList$setItemGeneratorClickHandler$1
                    /* JADX INFO: Access modifiers changed from: package-private */
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
                        FormFragment.DynamicList.setItemGeneratorClickHandler$updateComponentValue(FormFragment.DynamicList.this, cellData);
                    }
                });
            } else {
                compView.getView().setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$DynamicList$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FormFragment.DynamicList.setItemGeneratorClickHandler$lambda$18(FormFragment.DynamicList.this, cellData, view);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void setItemGeneratorClickHandler$lambda$18(DynamicList this$0, Object cellData, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(cellData, "$cellData");
            setItemGeneratorClickHandler$updateComponentValue(this$0, cellData);
            DynamicList dynamicList = this$0;
            WorkflowModule.Properties.Section.Component.Handler handler = this$0.itemOnClickHandler;
            Intrinsics.checkNotNull(handler);
            CompView.processHandler$hyperkyc_release$default(dynamicList, handler, false, 2, null);
        }

        private final void reloadAllChildren() {
            Job launch$default;
            Job job = this.reloadJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            launch$default = BuildersKt__Builders_commonKt.launch$default(this.this$0.getLifecycleScope(), null, null, new FormFragment$DynamicList$reloadAllChildren$1(this, null), 3, null);
            this.reloadJob = launch$default;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:14:0x00e4  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object reloadComp(CompView<? extends View, ? extends Object> compView, Object obj, Continuation<? super Unit> continuation) {
            FormFragment$DynamicList$reloadComp$1 formFragment$DynamicList$reloadComp$1;
            int i;
            DynamicList dynamicList;
            List<CompView<? extends View, ? extends Object>> childCompViews;
            Iterator it;
            CompView<? extends View, ? extends Object> compView2 = compView;
            Object obj2 = obj;
            if (continuation instanceof FormFragment$DynamicList$reloadComp$1) {
                formFragment$DynamicList$reloadComp$1 = (FormFragment$DynamicList$reloadComp$1) continuation;
                if ((formFragment$DynamicList$reloadComp$1.label & Integer.MIN_VALUE) != 0) {
                    formFragment$DynamicList$reloadComp$1.label -= Integer.MIN_VALUE;
                    Object obj3 = formFragment$DynamicList$reloadComp$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = formFragment$DynamicList$reloadComp$1.label;
                    if (i != 0) {
                        ResultKt.throwOnFailure(obj3);
                        WorkflowModule.Properties.Section.Component component = compView.getComponent();
                        FormFragment formFragment = this.this$0;
                        String injectFromData = injectFromData(component.getEnabled(), obj2);
                        String injectFromData2 = injectFromData(component.getVisible(), obj2);
                        String injectFromData3 = injectFromData(component.getSelected(), obj2);
                        Object value = component.getValue();
                        FormFragment$DynamicList$reloadComp$2$1 formFragment$DynamicList$reloadComp$2$1 = new FormFragment$DynamicList$reloadComp$2$1(compView2, new WorkflowModule.Properties.Section.Component.Handler.ReloadProperties(injectFromData, null, injectFromData2, injectFromData3, injectFromData(value != null ? FormFragment.stringInjectFromVariablesForAny$hyperkyc_release$default(formFragment, value, false, 1, null) : null, obj2), injectFromData(component.getText(), obj2), null, null, null, null, null, null, null, null, 16322, null), null);
                        formFragment$DynamicList$reloadComp$1.L$0 = this;
                        formFragment$DynamicList$reloadComp$1.L$1 = compView2;
                        formFragment$DynamicList$reloadComp$1.L$2 = obj2;
                        formFragment$DynamicList$reloadComp$1.label = 1;
                        if (CoroutineExtsKt.onUI$default(null, formFragment$DynamicList$reloadComp$2$1, formFragment$DynamicList$reloadComp$1, 1, null) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        dynamicList = this;
                    } else {
                        if (i != 1) {
                            if (i != 2) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            it = (Iterator) formFragment$DynamicList$reloadComp$1.L$2;
                            obj2 = formFragment$DynamicList$reloadComp$1.L$1;
                            dynamicList = (DynamicList) formFragment$DynamicList$reloadComp$1.L$0;
                            ResultKt.throwOnFailure(obj3);
                            while (it.hasNext()) {
                                CompView<? extends View, ? extends Object> compView3 = (CompView) it.next();
                                formFragment$DynamicList$reloadComp$1.L$0 = dynamicList;
                                formFragment$DynamicList$reloadComp$1.L$1 = obj2;
                                formFragment$DynamicList$reloadComp$1.L$2 = it;
                                formFragment$DynamicList$reloadComp$1.label = 2;
                                if (dynamicList.reloadComp(compView3, obj2, formFragment$DynamicList$reloadComp$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                            return Unit.INSTANCE;
                        }
                        Object obj4 = formFragment$DynamicList$reloadComp$1.L$2;
                        CompView<? extends View, ? extends Object> compView4 = (CompView) formFragment$DynamicList$reloadComp$1.L$1;
                        dynamicList = (DynamicList) formFragment$DynamicList$reloadComp$1.L$0;
                        ResultKt.throwOnFailure(obj3);
                        obj2 = obj4;
                        compView2 = compView4;
                    }
                    if ((compView2 instanceof Container) && (childCompViews = compView2.getChildCompViews()) != null) {
                        it = childCompViews.iterator();
                        while (it.hasNext()) {
                        }
                    }
                    return Unit.INSTANCE;
                }
            }
            formFragment$DynamicList$reloadComp$1 = new FormFragment$DynamicList$reloadComp$1(this, continuation);
            Object obj32 = formFragment$DynamicList$reloadComp$1.result;
            Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            i = formFragment$DynamicList$reloadComp$1.label;
            if (i != 0) {
            }
            if (compView2 instanceof Container) {
                it = childCompViews.iterator();
                while (it.hasNext()) {
                }
            }
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code restructure failed: missing block: B:61:0x013d, code lost:
        
            if (r0 == null) goto L55;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public DynamicListGridLayout inflate() {
            String canonicalName;
            Object m1202constructorimpl;
            String canonicalName2;
            String className;
            String numberOfColumns;
            String stringInjectFromVariables$hyperkyc_release$default;
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
            String str2 = getCompLogTag() + " inflate() called";
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
                        String str3 = getCompLogTag() + " inflate() called";
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
            View inflate = this.this$0.getLayoutInflater().inflate(R.layout.hk_view_dynamic_list, (ViewGroup) null, false);
            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type co.hyperverge.hyperkyc.ui.custom.DynamicListGridLayout");
            DynamicListGridLayout dynamicListGridLayout = (DynamicListGridLayout) inflate;
            FormFragment formFragment = this.this$0;
            int i = 1;
            if (Intrinsics.areEqual(getComponent().getSubType(), WorkflowModule.Properties.Section.Component.SubType.List.GRID) && (numberOfColumns = getComponent().getNumberOfColumns()) != null && (stringInjectFromVariables$hyperkyc_release$default = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, numberOfColumns, false, 1, null)) != null) {
                i = Integer.parseInt(stringInjectFromVariables$hyperkyc_release$default);
            }
            dynamicListGridLayout.setNumberOfColumns(i);
            return dynamicListGridLayout;
        }

        /* JADX WARN: Code restructure failed: missing block: B:62:0x03e3, code lost:
        
            if (r10 != null) goto L175;
         */
        /* JADX WARN: Code restructure failed: missing block: B:99:0x04de, code lost:
        
            if (r0 != null) goto L219;
         */
        /* JADX WARN: Removed duplicated region for block: B:107:0x0505  */
        /* JADX WARN: Removed duplicated region for block: B:115:0x0536  */
        /* JADX WARN: Removed duplicated region for block: B:117:0x053e  */
        /* JADX WARN: Removed duplicated region for block: B:119:0x053b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final void loadData(Object data) {
            String canonicalName;
            Object m1202constructorimpl;
            String str;
            String canonicalName2;
            String className;
            JSONArray jSONArray;
            String str2;
            Object m1202constructorimpl2;
            String canonicalName3;
            String className2;
            Object m1202constructorimpl3;
            String str3;
            String str4;
            Object m1202constructorimpl4;
            String str5;
            String str6;
            String str7;
            Matcher matcher;
            String className3;
            String className4;
            String className5;
            String className6;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className6 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls = getClass();
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
            Unit unit = Unit.INSTANCE;
            if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                canonicalName = canonicalName.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(canonicalName);
            sb.append(" - ");
            String str8 = "loadData() called with: data = [" + data + ']';
            if (str8 == null) {
                str8 = "null ";
            }
            sb.append(str8);
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
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                        if (matcher3.find()) {
                            canonicalName2 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                        }
                        Unit unit2 = Unit.INSTANCE;
                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName2 = canonicalName2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str9 = "loadData() called with: data = [" + data + ']';
                        if (str9 == null) {
                            str9 = "null ";
                        }
                        sb2.append(str9);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    }
                    if (this.this$0.isAdded() || this.this$0.isDetached()) {
                    }
                    if (data instanceof ArrayList) {
                        jSONArray = new JSONArray((Collection) data);
                    } else {
                        if (!(data instanceof String)) {
                            throw new NotImplementedError("An operation is not implemented: Unsupported type passed for data");
                        }
                        Object anyInjectFromVariables$hyperkyc_release = this.this$0.anyInjectFromVariables$hyperkyc_release((String) data);
                        jSONArray = anyInjectFromVariables$hyperkyc_release instanceof JSONArray ? (JSONArray) anyInjectFromVariables$hyperkyc_release : null;
                        if (jSONArray == null) {
                            jSONArray = new JSONArray();
                        }
                    }
                    JSONArray jSONArray2 = jSONArray;
                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                    HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className5 = stackTraceElement3.getClassName()) == null || (str2 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls3 = getClass();
                        String canonicalName4 = cls3 != null ? cls3.getCanonicalName() : null;
                        str2 = canonicalName4 == null ? str : canonicalName4;
                    }
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher4.find()) {
                        str2 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    Unit unit3 = Unit.INSTANCE;
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb3.append(str2);
                    sb3.append(" - ");
                    String str10 = "loadData: dataJSONArray: " + jSONArray2;
                    if (str10 == null) {
                        str10 = "null ";
                    }
                    sb3.append(str10);
                    sb3.append(' ');
                    sb3.append("");
                    companion4.log(level2, sb3.toString());
                    if (!CoreExtsKt.isRelease()) {
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
                            if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls4 = getClass();
                                    canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                                    if (canonicalName3 == null) {
                                        canonicalName3 = str;
                                    }
                                }
                                Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                                if (matcher5.find()) {
                                    canonicalName3 = matcher5.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                                }
                                Unit unit4 = Unit.INSTANCE;
                                if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    canonicalName3 = canonicalName3.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb4 = new StringBuilder();
                                String str11 = "loadData: dataJSONArray: " + jSONArray2;
                                if (str11 == null) {
                                    str11 = "null ";
                                }
                                sb4.append(str11);
                                sb4.append(' ');
                                sb4.append("");
                                Log.println(3, canonicalName3, sb4.toString());
                            }
                        }
                    }
                    ArrayList arrayList = new ArrayList();
                    try {
                        Result.Companion companion7 = Result.INSTANCE;
                        int length = jSONArray2.length();
                        for (int i = 0; i < length; i++) {
                            Object obj = jSONArray2.get(i);
                            Intrinsics.checkNotNullExpressionValue(obj, "dataJSONArray.get(i)");
                            arrayList.add(obj);
                        }
                        m1202constructorimpl3 = Result.m1202constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th3) {
                        Result.Companion companion8 = Result.INSTANCE;
                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                    }
                    Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                    if (m1205exceptionOrNullimpl != null) {
                        HyperLogger.Level level3 = HyperLogger.Level.DEBUG;
                        HyperLogger companion9 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb5 = new StringBuilder();
                        StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                        if (stackTraceElement5 == null || (className4 = stackTraceElement5.getClassName()) == null) {
                            str3 = "Throwable().stackTrace";
                        } else {
                            str3 = "Throwable().stackTrace";
                            str4 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        Class<?> cls5 = arrayList.getClass();
                        String canonicalName5 = cls5 != null ? cls5.getCanonicalName() : null;
                        str4 = canonicalName5 == null ? str : canonicalName5;
                        Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                        if (matcher6.find()) {
                            str4 = matcher6.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                        }
                        Unit unit5 = Unit.INSTANCE;
                        if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str4 = str4.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb5.append(str4);
                        sb5.append(" - ");
                        sb5.append("loadData: failed");
                        sb5.append(' ');
                        String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                        sb5.append(localizedMessage != null ? '\n' + localizedMessage : "");
                        companion9.log(level3, sb5.toString());
                        if (!CoreExtsKt.isRelease()) {
                            try {
                                Result.Companion companion10 = Result.INSTANCE;
                                Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type android.app.Application");
                                m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                            } catch (Throwable th4) {
                                Result.Companion companion11 = Result.INSTANCE;
                                m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th4));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                m1202constructorimpl4 = "";
                            }
                            String packageName3 = (String) m1202constructorimpl4;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                                if (StringsKt.contains$default((CharSequence) packageName3, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                    StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace6, str3);
                                    StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                    if (stackTraceElement6 == null || (className3 = stackTraceElement6.getClassName()) == null) {
                                        str5 = null;
                                    } else {
                                        str5 = null;
                                        str6 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    }
                                    Class<?> cls6 = arrayList.getClass();
                                    str6 = cls6 != null ? cls6.getCanonicalName() : str5;
                                    if (str6 == null) {
                                        str7 = str;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                                        if (matcher.find()) {
                                            str7 = matcher.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(str7, "replaceAll(\"\")");
                                        }
                                        Unit unit6 = Unit.INSTANCE;
                                        if (str7.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            str7 = str7.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb6 = new StringBuilder();
                                        sb6.append("loadData: failed");
                                        sb6.append(' ');
                                        String localizedMessage2 = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str5;
                                        sb6.append(localizedMessage2 != null ? '\n' + localizedMessage2 : "");
                                        Log.println(5, str7, sb6.toString());
                                    }
                                    str7 = str6;
                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                                    if (matcher.find()) {
                                    }
                                    Unit unit62 = Unit.INSTANCE;
                                    if (str7.length() > 23) {
                                        str7 = str7.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb62 = new StringBuilder();
                                    sb62.append("loadData: failed");
                                    sb62.append(' ');
                                    if (m1205exceptionOrNullimpl == null) {
                                    }
                                    if (localizedMessage2 != null) {
                                    }
                                    sb62.append(localizedMessage2 != null ? '\n' + localizedMessage2 : "");
                                    Log.println(5, str7, sb62.toString());
                                }
                            }
                        }
                    }
                    setData(arrayList);
                    return;
                }
            }
            str = "N/A";
            if (this.this$0.isAdded()) {
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:67:0x013d, code lost:
        
            if (r0 == null) goto L55;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final void setData(final List<? extends Object> updatedDataList) {
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
            String str2 = "setData called with newDataList: " + updatedDataList;
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
                        String str3 = "setData called with newDataList: " + updatedDataList;
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
            Job job = this.loadJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            if (!ListExtsKt.compareListsAsString(this.dataList, updatedDataList)) {
                List list = this.this$0.compViews;
                if (list == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("compViews");
                    list = null;
                }
                CollectionsKt.removeAll(list, (Function1) new Function1<CompView<? extends View, ? extends Object>, Boolean>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$DynamicList$setData$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(FormFragment.CompView<? extends View, ? extends Object> it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        String id2 = it.getComponent().getId();
                        WorkflowModule.Properties.Section.Component itemsGenerator = FormFragment.DynamicList.this.getComponent().getItemsGenerator();
                        return Boolean.valueOf(Intrinsics.areEqual(id2, itemsGenerator != null ? itemsGenerator.getId() : null));
                    }
                });
                getView().removeAllViews();
                this.localCompList.clear();
                Job launchWhenResumed = this.this$0.getLifecycleScope().launchWhenResumed(new FormFragment$DynamicList$setData$3(updatedDataList, this.this$0, this, null));
                this.loadJob = launchWhenResumed;
                if (launchWhenResumed != null) {
                    launchWhenResumed.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$DynamicList$setData$4
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Throwable th2) {
                            invoke2(th2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Throwable th2) {
                            FormFragment.DynamicList.this.dataList = updatedDataList;
                        }
                    });
                    return;
                }
                return;
            }
            reloadAllChildren();
        }
    }

    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001R\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0002H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$Radio;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "Landroid/widget/RadioButton;", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "parent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment;Landroid/widget/LinearLayout;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;)V", "getComponent", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "getParent", "()Landroid/widget/LinearLayout;", "defaultLayoutParams", "Landroid/widget/LinearLayout$LayoutParams;", "inflate", "postInflate", "", "updateView", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class Radio extends CompView<RadioButton, String> {
        private final WorkflowModule.Properties.Section.Component component;
        private final LinearLayout parent;
        final /* synthetic */ FormFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Radio(FormFragment formFragment, LinearLayout parent, WorkflowModule.Properties.Section.Component component) {
            super(formFragment, parent, component);
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(component, "component");
            this.this$0 = formFragment;
            this.parent = parent;
            this.component = component;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public WorkflowModule.Properties.Section.Component getComponent() {
            return this.component;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout getParent() {
            return this.parent;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void postInflate() {
            super.postInflate();
            String componentValue$hyperkyc_release = getComponentValue$hyperkyc_release();
            if (componentValue$hyperkyc_release == null) {
                componentValue$hyperkyc_release = "";
            }
            updateData("value", componentValue$hyperkyc_release);
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout.LayoutParams defaultLayoutParams() {
            return new LinearLayout.LayoutParams(-2, -2);
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void updateView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
            String stringInjectFromVariables$hyperkyc_release$default;
            int i;
            int i2;
            String stringInjectFromVariables$hyperkyc_release$default2;
            RadioButton view = getView();
            FormFragment formFragment = this.this$0;
            RadioButton radioButton = view;
            if (reloadProps != null) {
                String text = reloadProps.getText();
                if (text != null && (stringInjectFromVariables$hyperkyc_release$default2 = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, text, false, 1, null)) != null) {
                    String required = reloadProps.getRequired();
                    if (required == null) {
                        required = getComponent().getRequired();
                    }
                    Spanned fromHtml = HtmlCompat.fromHtml(formFragment.appendRequired$hyperkyc_release(stringInjectFromVariables$hyperkyc_release$default2, Intrinsics.areEqual((Object) formFragment.asBoolean$hyperkyc_release(required, true), (Object) true)), 0, null, null);
                    Intrinsics.checkNotNullExpressionValue(fromHtml, "fromHtml(this, flags, imageGetter, tagHandler)");
                    radioButton.setText(fromHtml);
                }
                radioButton.setChecked(getSelected());
                CharSequence text2 = radioButton.getText();
                Intrinsics.checkNotNullExpressionValue(text2, "text");
                if (text2.length() > 0) {
                    Context requireContext = formFragment.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    i2 = UIExtsKt.dpToPx(requireContext, 8.0f);
                } else {
                    i2 = 0;
                }
                radioButton.setPadding(i2, 0, 0, 0);
                updateView$lambda$6$applyUIConfigs(formFragment, this);
                return;
            }
            final WorkflowModule.Properties.Section.Component.Handler onClick = getComponent().getOnClick();
            if (onClick != null) {
                radioButton.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Radio$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        FormFragment.Radio.updateView$lambda$6$lambda$3$lambda$2(FormFragment.Radio.this, onClick, view2);
                    }
                });
            }
            radioButton.setGravity(17);
            String text3 = getComponent().getText();
            if (text3 != null && (stringInjectFromVariables$hyperkyc_release$default = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, text3, false, 1, null)) != null) {
                Spanned fromHtml2 = HtmlCompat.fromHtml(formFragment.appendRequired$hyperkyc_release(stringInjectFromVariables$hyperkyc_release$default, Intrinsics.areEqual((Object) formFragment.asBoolean$hyperkyc_release(getComponent().getRequired(), true), (Object) true)), 0, null, null);
                Intrinsics.checkNotNullExpressionValue(fromHtml2, "fromHtml(this, flags, imageGetter, tagHandler)");
                radioButton.setText(fromHtml2);
                CharSequence text4 = radioButton.getText();
                Intrinsics.checkNotNullExpressionValue(text4, "text");
                if (text4.length() > 0) {
                    Context requireContext2 = formFragment.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    i = UIExtsKt.dpToPx(requireContext2, 8.0f);
                } else {
                    i = 0;
                }
                radioButton.setPadding(i, 0, 0, 0);
            }
            radioButton.setChecked(getSelected());
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$Radio$$ExternalSyntheticLambda1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    FormFragment.Radio.updateView$lambda$6$lambda$5(FormFragment.Radio.this, compoundButton, z);
                }
            });
            radioButton.setTypeface(formFragment.getDescTextFont$hyperkyc_release());
            updateView$lambda$6$applyUIConfigs(formFragment, this);
        }

        private static final void updateView$lambda$6$applyUIConfigs(FormFragment formFragment, Radio radio) {
            DynamicFormUIConfig dynamicFormUIConfig;
            DynamicFormUtils dynamicFormUtils$hyperkyc_release = formFragment.getDynamicFormUtils$hyperkyc_release();
            RadioButton view = radio.getView();
            HSUIConfig uiConfigData = formFragment.getMainVM$hyperkyc_release().getUiConfigData();
            if (uiConfigData != null) {
                String moduleId = formFragment.getModuleId$hyperkyc_release();
                Intrinsics.checkNotNullExpressionValue(moduleId, "moduleId");
                dynamicFormUIConfig = uiConfigData.getDynamicFormUIConfig(moduleId, radio.getComponent().getId());
            } else {
                dynamicFormUIConfig = null;
            }
            dynamicFormUtils$hyperkyc_release.customiseRadioButton(view, dynamicFormUIConfig);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void updateView$lambda$6$lambda$3$lambda$2(Radio this$0, WorkflowModule.Properties.Section.Component.Handler handler, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(handler, "$handler");
            CompView.processHandler$hyperkyc_release$default(this$0, handler, false, 2, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void updateView$lambda$6$lambda$5(Radio this$0, CompoundButton compoundButton, boolean z) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.setSelected(z);
        }

        /* JADX WARN: Code restructure failed: missing block: B:57:0x013d, code lost:
        
            if (r0 == null) goto L55;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public RadioButton inflate() {
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
            DynamicFormUIConfig dynamicFormUIConfig = null;
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
            String str2 = getCompLogTag() + " inflate() called";
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
                        String str3 = getCompLogTag() + " inflate() called";
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
            View inflate = this.this$0.getLayoutInflater().inflate(R.layout.hk_view_radio_button, (ViewGroup) null, false);
            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type android.widget.RadioButton");
            RadioButton radioButton = (RadioButton) inflate;
            DynamicFormUtils dynamicFormUtils$hyperkyc_release = this.this$0.getDynamicFormUtils$hyperkyc_release();
            Context requireContext = this.this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            HSUIConfig uiConfigData = this.this$0.getMainVM$hyperkyc_release().getUiConfigData();
            if (uiConfigData != null) {
                String moduleId = this.this$0.getModuleId$hyperkyc_release();
                Intrinsics.checkNotNullExpressionValue(moduleId, "moduleId");
                dynamicFormUIConfig = uiConfigData.getDynamicFormUIConfig(moduleId, getComponent().getId());
            }
            radioButton.setButtonDrawable(dynamicFormUtils$hyperkyc_release.createCustomRadioButtonDrawable(requireContext, dynamicFormUIConfig));
            return radioButton;
        }
    }

    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001R\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0002H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormFragment$ChipButton;", "Lco/hyperverge/hyperkyc/ui/form/FormFragment$CompView;", "Lcom/google/android/material/chip/Chip;", "", "Lco/hyperverge/hyperkyc/ui/form/FormFragment;", "parent", "Landroid/widget/LinearLayout;", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Lco/hyperverge/hyperkyc/ui/form/FormFragment;Landroid/widget/LinearLayout;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;)V", "getComponent", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "getParent", "()Landroid/widget/LinearLayout;", "defaultLayoutParams", "Landroid/widget/LinearLayout$LayoutParams;", "inflate", "postInflate", "", "updateView", "reloadProps", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class ChipButton extends CompView<Chip, String> {
        private final WorkflowModule.Properties.Section.Component component;
        private final LinearLayout parent;
        final /* synthetic */ FormFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChipButton(FormFragment formFragment, LinearLayout parent, WorkflowModule.Properties.Section.Component component) {
            super(formFragment, parent, component);
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(component, "component");
            this.this$0 = formFragment;
            this.parent = parent;
            this.component = component;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public WorkflowModule.Properties.Section.Component getComponent() {
            return this.component;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout getParent() {
            return this.parent;
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void postInflate() {
            super.postInflate();
            String componentValue$hyperkyc_release = getComponentValue$hyperkyc_release();
            if (componentValue$hyperkyc_release == null) {
                componentValue$hyperkyc_release = "";
            }
            updateData("value", componentValue$hyperkyc_release);
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public LinearLayout.LayoutParams defaultLayoutParams() {
            return new LinearLayout.LayoutParams(-2, -2);
        }

        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        public void updateView(WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProps) {
            String stringInjectFromVariables$hyperkyc_release$default;
            String stringInjectFromVariables$hyperkyc_release$default2;
            Chip view = getView();
            FormFragment formFragment = this.this$0;
            Chip chip = view;
            if (reloadProps != null) {
                String text = reloadProps.getText();
                if (text != null && (stringInjectFromVariables$hyperkyc_release$default2 = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, text, false, 1, null)) != null) {
                    String required = reloadProps.getRequired();
                    if (required == null) {
                        required = getComponent().getRequired();
                    }
                    Spanned fromHtml = HtmlCompat.fromHtml(formFragment.appendRequired$hyperkyc_release(stringInjectFromVariables$hyperkyc_release$default2, Intrinsics.areEqual((Object) formFragment.asBoolean$hyperkyc_release(required, true), (Object) true)), 0, null, null);
                    Intrinsics.checkNotNullExpressionValue(fromHtml, "fromHtml(this, flags, imageGetter, tagHandler)");
                    chip.setText(fromHtml);
                }
                updateView$lambda$5$applyUIConfigs(formFragment, this, chip);
                return;
            }
            final WorkflowModule.Properties.Section.Component.Handler onClick = getComponent().getOnClick();
            if (onClick != null) {
                chip.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$ChipButton$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        FormFragment.ChipButton.updateView$lambda$5$lambda$3$lambda$2(FormFragment.ChipButton.this, onClick, view2);
                    }
                });
            }
            String text2 = getComponent().getText();
            if (text2 != null && (stringInjectFromVariables$hyperkyc_release$default = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, text2, false, 1, null)) != null) {
                Spanned fromHtml2 = HtmlCompat.fromHtml(formFragment.appendRequired$hyperkyc_release(stringInjectFromVariables$hyperkyc_release$default, Intrinsics.areEqual((Object) formFragment.asBoolean$hyperkyc_release(getComponent().getRequired(), true), (Object) true)), 0, null, null);
                Intrinsics.checkNotNullExpressionValue(fromHtml2, "fromHtml(this, flags, imageGetter, tagHandler)");
                chip.setText(fromHtml2);
            }
            chip.setTypeface(formFragment.getDescTextFont$hyperkyc_release());
            updateView$lambda$5$applyUIConfigs(formFragment, this, chip);
        }

        private static final void updateView$lambda$5$applyUIConfigs(FormFragment formFragment, ChipButton chipButton, Chip chip) {
            DynamicFormUIConfig dynamicFormUIConfig;
            DynamicFormUtils dynamicFormUtils$hyperkyc_release = formFragment.getDynamicFormUtils$hyperkyc_release();
            Context requireContext = formFragment.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            Chip view = chipButton.getView();
            boolean selected = chipButton.getSelected();
            boolean isEnabled = chip.isEnabled();
            HSUIConfig uiConfigData = formFragment.getMainVM$hyperkyc_release().getUiConfigData();
            if (uiConfigData != null) {
                String moduleId = formFragment.getModuleId$hyperkyc_release();
                Intrinsics.checkNotNullExpressionValue(moduleId, "moduleId");
                dynamicFormUIConfig = uiConfigData.getDynamicFormUIConfig(moduleId, chipButton.getComponent().getId());
            } else {
                dynamicFormUIConfig = null;
            }
            dynamicFormUtils$hyperkyc_release.customiseChip(requireContext, view, selected, isEnabled, dynamicFormUIConfig);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void updateView$lambda$5$lambda$3$lambda$2(ChipButton this$0, WorkflowModule.Properties.Section.Component.Handler handler, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(handler, "$handler");
            CompView.processHandler$hyperkyc_release$default(this$0, handler, false, 2, null);
        }

        /* JADX WARN: Code restructure failed: missing block: B:55:0x013b, code lost:
        
            if (r0 == null) goto L55;
         */
        @Override // co.hyperverge.hyperkyc.ui.form.FormFragment.CompView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Chip inflate() {
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
            String str2 = getCompLogTag() + " inflate() called";
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
                        String str3 = getCompLogTag() + " inflate() called";
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
            View inflate = this.this$0.getLayoutInflater().inflate(R.layout.hk_view_chip, (ViewGroup) null, false);
            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type com.google.android.material.chip.Chip");
            return (Chip) inflate;
        }
    }

    public static /* synthetic */ String stringInjectFromVariables$hyperkyc_release$default(FormFragment formFragment, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return formFragment.stringInjectFromVariables$hyperkyc_release(str, z);
    }

    public static /* synthetic */ String stringInjectFromVariablesForAny$hyperkyc_release$default(FormFragment formFragment, Object obj, boolean z, int i, Object obj2) {
        if ((i & 1) != 0) {
            z = false;
        }
        return formFragment.stringInjectFromVariablesForAny$hyperkyc_release(obj, z);
    }

    public final String stringInjectFromVariablesForAny$hyperkyc_release(Object obj, boolean z) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        return obj instanceof String ? stringInjectFromVariables$hyperkyc_release((String) obj, z) : "";
    }

    public final Boolean asBoolean$hyperkyc_release(String str, Boolean bool) {
        Boolean asBoolean$hyperkyc_release = getMainVM$hyperkyc_release().asBoolean$hyperkyc_release(str, bool);
        return asBoolean$hyperkyc_release == null ? bool : asBoolean$hyperkyc_release;
    }

    public final /* synthetic */ String appendRequired$hyperkyc_release(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(z ? " *" : "");
        return sb.toString();
    }

    public static /* synthetic */ void setTitleHintAndHelperText$hyperkyc_release$default(FormFragment formFragment, TextInputLayout textInputLayout, WorkflowModule.Properties.Section.Component component, WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProperties, int i, Object obj) {
        if ((i & 2) != 0) {
            reloadProperties = null;
        }
        formFragment.setTitleHintAndHelperText$hyperkyc_release(textInputLayout, component, reloadProperties);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a3, code lost:
    
        if ((r12 == null || kotlin.text.StringsKt.isBlank(r12)) == false) goto L42;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ void setTitleHintAndHelperText$hyperkyc_release(TextInputLayout textInputLayout, WorkflowModule.Properties.Section.Component component, WorkflowModule.Properties.Section.Component.Handler.ReloadProperties reloadProperties) {
        String hint;
        String helperText;
        String content;
        String str;
        String stringInjectFromVariables$hyperkyc_release$default;
        Intrinsics.checkNotNullParameter(textInputLayout, "<this>");
        Intrinsics.checkNotNullParameter(component, "component");
        String required = component.getRequired();
        String enabled = component.getEnabled();
        if (reloadProperties != null) {
            str = reloadProperties.getTitle();
            hint = reloadProperties.getHint();
            helperText = reloadProperties.getHelperText();
            String required2 = reloadProperties.getRequired();
            if (required2 != null) {
                required = required2;
            }
            String enabled2 = reloadProperties.getEnabled();
            if (enabled2 != null) {
                enabled = enabled2;
            }
            content = null;
        } else {
            String title = component.getTitle();
            hint = component.getHint();
            if (hint == null) {
                hint = getDateContentWithFormat$hyperkyc_release(component);
            }
            helperText = component.getHelperText();
            content = component.getContent();
            str = title;
        }
        if (str != null && (stringInjectFromVariables$hyperkyc_release$default = stringInjectFromVariables$hyperkyc_release$default(this, str, false, 1, null)) != null) {
            textInputLayout.setHint(appendRequired$hyperkyc_release(stringInjectFromVariables$hyperkyc_release$default, Intrinsics.areEqual((Object) asBoolean$hyperkyc_release(required, true), (Object) true) && Intrinsics.areEqual((Object) asBoolean$hyperkyc_release(enabled, true), (Object) true)));
        }
        String str2 = hint;
        if (str2 == null || StringsKt.isBlank(str2)) {
            String str3 = content;
        }
        textInputLayout.setPlaceholderText(hint != null ? stringInjectFromVariables$hyperkyc_release$default(this, hint, false, 1, null) : null);
        CharSequence placeholderText = textInputLayout.getPlaceholderText();
        textInputLayout.setExpandedHintEnabled(!(placeholderText == null || StringsKt.isBlank(placeholderText)));
        if (helperText != null) {
            Spanned fromHtml = HtmlCompat.fromHtml(stringInjectFromVariables$hyperkyc_release$default(this, helperText, false, 1, null), 0, null, null);
            Intrinsics.checkNotNullExpressionValue(fromHtml, "fromHtml(this, flags, imageGetter, tagHandler)");
            textInputLayout.setHelperText(fromHtml);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
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
        sb.append("onDestroyView() called");
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
                    Log.println(3, str, "onDestroyView() called ");
                }
            }
        }
        destroyRunningCountdownTimers();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
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
        sb.append("onDestroy() called");
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
                    Log.println(3, str, "onDestroy() called ");
                }
            }
        }
        destroyRunningCountdownTimers();
        super.onDestroy();
    }

    private final void destroyRunningCountdownTimers() {
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
        sb.append("destroyRunningCountdownTimers() called");
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
                    Log.println(3, str, "destroyRunningCountdownTimers() called ");
                }
            }
        }
        Iterator<Map.Entry<String, CountDownTimer>> it = this.countdownTimersMap.entrySet().iterator();
        while (it.hasNext()) {
            CountDownTimer value = it.next().getValue();
            if (value != null) {
                value.cancel();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:78:0x0124, code lost:
    
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
        String str2 = null;
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
        final HkFragmentFormBinding binding = getBinding();
        LinearLayout linearLayout = binding.includeBranding.llBrandingRoot;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "includeBranding.llBrandingRoot");
        linearLayout.setVisibility(getMainVM$hyperkyc_release().shouldShowBranding() ? 0 : 8);
        ImageView imageView = binding.hkLayoutTopBar.ivBack;
        Intrinsics.checkNotNullExpressionValue(imageView, "hkLayoutTopBar.ivBack");
        imageView.setVisibility(getShowBackButton() ? 0 : 8);
        binding.hkLayoutTopBar.ivBack.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormFragment.initViews$lambda$10$lambda$8(FormFragment.this, view);
            }
        });
        String moduleType = getModuleType();
        int hashCode = moduleType.hashCode();
        if (hashCode != -259085917) {
            if (hashCode != 3148996) {
                if (hashCode == 126539647 && moduleType.equals(WorkflowModule.TYPE_DYNAMIC_FORM_V2)) {
                    BaseFragment.finishWithResult$default(this, "error", null, 104, "Please enable useWebForm to load dynamicFormV2", false, false, 50, null);
                }
            } else if (moduleType.equals(WorkflowModule.TYPE_FORM)) {
                showLegacyForm();
            }
        } else if (moduleType.equals(WorkflowModule.TYPE_DYNAMIC_FORM)) {
            showDynamicForm();
        }
        HyperSnapBridgeKt.getUiConfigUtil().customiseBackButtonIcon(binding.hkLayoutTopBar.ivBack);
        HyperSnapBridgeKt.getUiConfigUtil().customiseClientLogo(binding.hkLayoutTopBar.hkClientLogo);
        LinearLayout linearLayout2 = getBinding().llFooter;
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "binding.llFooter");
        if (linearLayout2.getVisibility() == 0) {
            ViewCompat.setOnApplyWindowInsetsListener(requireActivity().getWindow().getDecorView(), new OnApplyWindowInsetsListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$$ExternalSyntheticLambda4
                @Override // androidx.core.view.OnApplyWindowInsetsListener
                public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                    WindowInsetsCompat initViews$lambda$10$lambda$9;
                    initViews$lambda$10$lambda$9 = FormFragment.initViews$lambda$10$lambda$9(FormFragment.this, binding, view, windowInsetsCompat);
                    return initViews$lambda$10$lambda$9;
                }
            });
        }
        HSUIConfig uiConfigData = getMainVM$hyperkyc_release().getUiConfigData();
        if (uiConfigData != null) {
            String moduleId = getModuleId$hyperkyc_release();
            Intrinsics.checkNotNullExpressionValue(moduleId, "moduleId");
            str2 = uiConfigData.getModuleBackgroundImageUrl(moduleId);
        }
        loadBackground$hyperkyc_release(str2);
    }

    @Deprecated(message = "use dynamicForms")
    private final void showLegacyForm() {
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
        sb.append("showLegacyForm() called");
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
                    Log.println(3, str, "showLegacyForm() called ");
                }
            }
        }
        final HkFragmentFormBinding binding = getBinding();
        ConstraintLayout clLegacyUI = binding.clLegacyUI;
        Intrinsics.checkNotNullExpressionValue(clLegacyUI, "clLegacyUI");
        clLegacyUI.setVisibility(0);
        LinearLayout llContent = binding.llContent;
        Intrinsics.checkNotNullExpressionValue(llContent, "llContent");
        llContent.setVisibility(8);
        String subType = getSubType();
        int hashCode = subType.hashCode();
        if (hashCode == -1130292259) {
            if (subType.equals("aadhaar_xml_form")) {
                binding.tvTitle.setText(showLegacyForm$lambda$16$getText(this, "form_titleText", "Is your Aadhaar linked with your phone number?"));
                binding.tvSubTitle.setText(showLegacyForm$lambda$16$getText(this, "form_descriptionText", "If they are linked, you can use the Aadhaar OKYC flow for faster approval"));
                binding.btnPositive.setText(showLegacyForm$lambda$16$getText(this, "form_positiveButtonText", "Yes, take me to Aadhaar OKYC flow"));
                binding.btnNegative.setText(showLegacyForm$lambda$16$getText(this, "form_negativeButtonText", "No they are not linked"));
                final Function1<View, Unit> function1 = new Function1<View, Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$showLegacyForm$2$clickListener$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(View view) {
                        invoke2(view);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:43:0x01ef  */
                    /* JADX WARN: Removed duplicated region for block: B:51:0x022d  */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    public final void invoke2(View view) {
                        String canonicalName3;
                        Class<?> cls3;
                        Object m1202constructorimpl2;
                        String str2;
                        Class<?> cls4;
                        Matcher matcher3;
                        String str3;
                        String className3;
                        String className4;
                        Intrinsics.checkNotNullParameter(view, "view");
                        MainVM mainVM$hyperkyc_release = FormFragment.this.getMainVM$hyperkyc_release();
                        String moduleId$hyperkyc_release = FormFragment.this.getModuleId$hyperkyc_release();
                        List<String> listOf = CollectionsKt.listOf((Object[]) new String[]{"btnPositive", "btnNegative"});
                        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listOf, 10)), 16));
                        for (String str4 : listOf) {
                            Pair pair = TuplesKt.to(str4 + "Clicked", String.valueOf(Intrinsics.areEqual(str4, ViewExtsKt.resName(view))));
                            linkedHashMap.put(pair.getFirst(), pair.getSecond());
                        }
                        MainVM.updateFormData$hyperkyc_release$default(mainVM$hyperkyc_release, moduleId$hyperkyc_release, linkedHashMap, false, 4, null);
                        boolean flowForward = FormFragment.this.getMainVM$hyperkyc_release().flowForward();
                        HkFragmentFormBinding hkFragmentFormBinding = binding;
                        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb2 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        String str5 = "N/A";
                        if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            canonicalName3 = (hkFragmentFormBinding == null || (cls3 = hkFragmentFormBinding.getClass()) == null) ? null : cls3.getCanonicalName();
                            if (canonicalName3 == null) {
                                canonicalName3 = "N/A";
                            }
                        }
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                        if (matcher4.find()) {
                            canonicalName3 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                        }
                        if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName3 = canonicalName3.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb2.append(canonicalName3);
                        sb2.append(" - ");
                        String str6 = "initViews: clickListener for " + ViewExtsKt.resName(view) + " | flowForward handled: " + flowForward;
                        if (str6 == null) {
                            str6 = "null ";
                        }
                        sb2.append(str6);
                        sb2.append(' ');
                        sb2.append("");
                        companion4.log(level2, sb2.toString());
                        if (CoreExtsKt.isRelease()) {
                            return;
                        }
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
                            if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null) {
                                    str2 = null;
                                } else {
                                    str2 = null;
                                    String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default2 != null) {
                                        str5 = substringAfterLast$default2;
                                        matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                        if (matcher3.find()) {
                                            str5 = matcher3.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                                        }
                                        if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            str5 = str5.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb3 = new StringBuilder();
                                        str3 = "initViews: clickListener for " + ViewExtsKt.resName(view) + " | flowForward handled: " + flowForward;
                                        if (str3 == null) {
                                            str3 = "null ";
                                        }
                                        sb3.append(str3);
                                        sb3.append(' ');
                                        sb3.append("");
                                        Log.println(3, str5, sb3.toString());
                                    }
                                }
                                String canonicalName4 = (hkFragmentFormBinding == null || (cls4 = hkFragmentFormBinding.getClass()) == null) ? str2 : cls4.getCanonicalName();
                                if (canonicalName4 != null) {
                                    str5 = canonicalName4;
                                }
                                matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                if (matcher3.find()) {
                                }
                                if (str5.length() > 23) {
                                    str5 = str5.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb32 = new StringBuilder();
                                str3 = "initViews: clickListener for " + ViewExtsKt.resName(view) + " | flowForward handled: " + flowForward;
                                if (str3 == null) {
                                }
                                sb32.append(str3);
                                sb32.append(' ');
                                sb32.append("");
                                Log.println(3, str5, sb32.toString());
                            }
                        }
                    }
                };
                binding.btnPositive.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FormFragment.showLegacyForm$lambda$16$lambda$14(Function1.this, view);
                    }
                });
                binding.btnNegative.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FormFragment.showLegacyForm$lambda$16$lambda$15(Function1.this, view);
                    }
                });
                return;
            }
            throw new NotImplementedError("An operation is not implemented: " + ("not implemented yet for " + getSubType()));
        }
        if (hashCode == 15886084 && subType.equals("digilocker_form")) {
            binding.tvTitle.setText(showLegacyForm$lambda$16$getText(this, "form_titleText", "Is your Aadhaar linked with your phone number?"));
            binding.tvSubTitle.setText(showLegacyForm$lambda$16$getText(this, "form_descriptionText", "If they are linked, you can use the Digilocker flow for faster approval"));
            binding.btnPositive.setText(showLegacyForm$lambda$16$getText(this, "form_positiveButtonText", "Yes, take me to Digilocker flow"));
            binding.btnNegative.setText(showLegacyForm$lambda$16$getText(this, "form_negativeButtonText", "No they are not linked"));
            final Function1 function12 = new Function1<View, Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$showLegacyForm$2$clickListener$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:43:0x01ef  */
                /* JADX WARN: Removed duplicated region for block: B:51:0x022d  */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final void invoke2(View view) {
                    String canonicalName3;
                    Class<?> cls3;
                    Object m1202constructorimpl2;
                    String str2;
                    Class<?> cls4;
                    Matcher matcher3;
                    String str3;
                    String className3;
                    String className4;
                    Intrinsics.checkNotNullParameter(view, "view");
                    MainVM mainVM$hyperkyc_release = FormFragment.this.getMainVM$hyperkyc_release();
                    String moduleId$hyperkyc_release = FormFragment.this.getModuleId$hyperkyc_release();
                    List<String> listOf = CollectionsKt.listOf((Object[]) new String[]{"btnPositive", "btnNegative"});
                    LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listOf, 10)), 16));
                    for (String str4 : listOf) {
                        Pair pair = TuplesKt.to(str4 + "Clicked", String.valueOf(Intrinsics.areEqual(str4, ViewExtsKt.resName(view))));
                        linkedHashMap.put(pair.getFirst(), pair.getSecond());
                    }
                    MainVM.updateFormData$hyperkyc_release$default(mainVM$hyperkyc_release, moduleId$hyperkyc_release, linkedHashMap, false, 4, null);
                    boolean flowForward = FormFragment.this.getMainVM$hyperkyc_release().flowForward();
                    HkFragmentFormBinding hkFragmentFormBinding = binding;
                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                    HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb2 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    String str5 = "N/A";
                    if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        canonicalName3 = (hkFragmentFormBinding == null || (cls3 = hkFragmentFormBinding.getClass()) == null) ? null : cls3.getCanonicalName();
                        if (canonicalName3 == null) {
                            canonicalName3 = "N/A";
                        }
                    }
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                    if (matcher4.find()) {
                        canonicalName3 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                    }
                    if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName3 = canonicalName3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb2.append(canonicalName3);
                    sb2.append(" - ");
                    String str6 = "initViews: clickListener for " + ViewExtsKt.resName(view) + " | flowForward handled: " + flowForward;
                    if (str6 == null) {
                        str6 = "null ";
                    }
                    sb2.append(str6);
                    sb2.append(' ');
                    sb2.append("");
                    companion4.log(level2, sb2.toString());
                    if (CoreExtsKt.isRelease()) {
                        return;
                    }
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
                        if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null) {
                                str2 = null;
                            } else {
                                str2 = null;
                                String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default2 != null) {
                                    str5 = substringAfterLast$default2;
                                    matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                    if (matcher3.find()) {
                                        str5 = matcher3.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                                    }
                                    if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str5 = str5.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb32 = new StringBuilder();
                                    str3 = "initViews: clickListener for " + ViewExtsKt.resName(view) + " | flowForward handled: " + flowForward;
                                    if (str3 == null) {
                                        str3 = "null ";
                                    }
                                    sb32.append(str3);
                                    sb32.append(' ');
                                    sb32.append("");
                                    Log.println(3, str5, sb32.toString());
                                }
                            }
                            String canonicalName4 = (hkFragmentFormBinding == null || (cls4 = hkFragmentFormBinding.getClass()) == null) ? str2 : cls4.getCanonicalName();
                            if (canonicalName4 != null) {
                                str5 = canonicalName4;
                            }
                            matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                            if (matcher3.find()) {
                            }
                            if (str5.length() > 23) {
                                str5 = str5.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb322 = new StringBuilder();
                            str3 = "initViews: clickListener for " + ViewExtsKt.resName(view) + " | flowForward handled: " + flowForward;
                            if (str3 == null) {
                            }
                            sb322.append(str3);
                            sb322.append(' ');
                            sb322.append("");
                            Log.println(3, str5, sb322.toString());
                        }
                    }
                }
            };
            binding.btnPositive.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FormFragment.showLegacyForm$lambda$16$lambda$14(Function1.this, view);
                }
            });
            binding.btnNegative.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FormFragment.showLegacyForm$lambda$16$lambda$15(Function1.this, view);
                }
            });
            return;
        }
        throw new NotImplementedError("An operation is not implemented: " + ("not implemented yet for " + getSubType()));
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0120, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void showDynamicForm() {
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
        sb.append("showDynamicForm() called");
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
                    Log.println(3, str, "showDynamicForm() called ");
                }
            }
        }
        Bundle arguments = getArguments();
        Serializable serializable = arguments != null ? arguments.getSerializable(ARG_SECTIONS) : null;
        Intrinsics.checkNotNull(serializable);
        LinearLayout linearLayout = getBinding().llContent;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.llContent");
        createSections(linearLayout, (List) serializable);
    }

    public final void renderAll$hyperkyc_release(List<? extends CompView<?, ?>> list, Margin margin) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(margin, "margin");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = list.getClass();
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
        String str2 = "renderAll() called with: margin = [" + margin + "] for " + list.size() + " compViews";
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
                        Class<?> cls2 = list.getClass();
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
                    String str3 = "renderAll() called with: margin = [" + margin + "] for " + list.size() + " compViews";
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
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CompView compView = (CompView) it.next();
            compView.render(margin);
            List<CompView<? extends View, ? extends Object>> childCompViews = compView.getChildCompViews();
            if (childCompViews != null) {
                renderAll$hyperkyc_release(childCompViews, new Margin(0, 0, 0, ViewExtsKt.getDp(12), 7, null));
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0136, code lost:
    
        if (r0 != 0) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0148, code lost:
    
        if (r0 == 0) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x014c, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x015b, code lost:
    
        if (r0.find() == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x015d, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0168, code lost:
    
        if (r8.length() <= 23) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x016c, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x016f, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0176, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "createSections() called with: sections = " + r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x018a, code lost:
    
        if (r4 != null) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x018c, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x018e, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x014b, code lost:
    
        r8 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v27, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v40 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void createSections(LinearLayout linearLayout, List<WorkflowModule.Properties.Section> list) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        List<CompView<? extends View, ? extends Object>> list2;
        ?? r0;
        Class<?> cls2;
        String className;
        List<CompView<? extends View, ? extends Object>> list3;
        List<WorkflowModule.Properties.Section.Component> components;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (linearLayout == null || (cls = linearLayout.getClass()) == null) ? null : cls.getCanonicalName();
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
        String str2 = "createSections() called with: sections = " + list;
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                        list2 = null;
                    } else {
                        list2 = null;
                        r0 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    r0 = (linearLayout == null || (cls2 = linearLayout.getClass()) == null) ? list2 : cls2.getCanonicalName();
                }
            }
        }
        list2 = null;
        for (WorkflowModule.Properties.Section section : list) {
            List<WorkflowModule.Properties.Section.Component> component2 = section.component2();
            WorkflowModule.Properties.Section.Footer footer = section.getFooter();
            this.compViews = CollectionsKt.toMutableList((Collection) createComponents$hyperkyc_release(linearLayout, component2));
            if (footer == null || (components = footer.getComponents()) == null) {
                list3 = list2;
            } else {
                LinearLayout linearLayout2 = getBinding().llFooter;
                Intrinsics.checkNotNullExpressionValue(linearLayout2, "binding.llFooter");
                linearLayout2.setVisibility(0);
                LinearLayout linearLayout3 = getBinding().llFooter;
                Intrinsics.checkNotNullExpressionValue(linearLayout3, "binding.llFooter");
                list3 = CollectionsKt.toMutableList((Collection) createComponents$hyperkyc_release(linearLayout3, components));
            }
            List<CompView<? extends View, ? extends Object>> list4 = this.compViews;
            if (list4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("compViews");
                list4 = list2;
            }
            renderAll$hyperkyc_release(list4, new Margin(0, 0, 0, ViewExtsKt.getDp(24), 7, null));
            if (list3 != null) {
                renderAll$hyperkyc_release(list3, new Margin(0, 0, 0, ViewExtsKt.getDp(12), 7, null));
                List<CompView<? extends View, ? extends Object>> list5 = this.compViews;
                if (list5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("compViews");
                    list5 = list2;
                }
                this.compViews = CollectionsKt.toMutableList((Collection) CollectionsKt.plus((Collection) list5, (Iterable) list3));
            }
        }
    }

    public final List<CompView<? extends View, ? extends Object>> createComponents$hyperkyc_release(LinearLayout linearLayout, List<WorkflowModule.Properties.Section.Component> children) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(linearLayout, "<this>");
        Intrinsics.checkNotNullParameter(children, "children");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = linearLayout.getClass();
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
        String str2 = "createComponents() called with: children = " + children;
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
                        Class<?> cls2 = linearLayout.getClass();
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
                    String str3 = "createComponents() called with: children = " + children;
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
        ArrayList arrayList = new ArrayList();
        for (Object obj : children) {
            if (linearLayout.findViewWithTag(((WorkflowModule.Properties.Section.Component) obj).getId()) == null) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(createComponent$hyperkyc_release(linearLayout, (WorkflowModule.Properties.Section.Component) it.next()));
        }
        return arrayList3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x0142, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0152, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0156, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0165, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0167, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0172, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0178, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x017b, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0182, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "createComponent() called with: component = [" + r19 + ']';
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0199, code lost:
    
        if (r4 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x019b, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x019d, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x0155, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final CompView<? extends View, ? extends Object> createComponent$hyperkyc_release(LinearLayout linearLayout, WorkflowModule.Properties.Section.Component component) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(linearLayout, "<this>");
        Intrinsics.checkNotNullParameter(component, "component");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str3 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = linearLayout.getClass();
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
        String str4 = "createComponent() called with: component = [" + component + ']';
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
                    Class<?> cls2 = linearLayout.getClass();
                    str2 = cls2 != null ? cls2.getCanonicalName() : str;
                }
            }
        }
        String type = component.getType();
        switch (type.hashCode()) {
            case -1984141450:
                if (type.equals("vertical")) {
                    return new Container(this, 1, linearLayout, component);
                }
                break;
            case -1377687758:
                if (type.equals(WorkflowModule.Properties.Section.Component.Type.BUTTON)) {
                    return new Button(this, linearLayout, component);
                }
                break;
            case -1097519085:
                if (type.equals(WorkflowModule.Properties.Section.Component.Type.LOADER)) {
                    return new Loader(this, linearLayout, component);
                }
                break;
            case -432061423:
                if (type.equals(WorkflowModule.Properties.Section.Component.Type.DROPDOWN)) {
                    return new Dropdown(this, linearLayout, component);
                }
                break;
            case 3052620:
                if (type.equals(WorkflowModule.Properties.Section.Component.Type.CHIP)) {
                    return new ChipButton(this, linearLayout, component);
                }
                break;
            case 3076014:
                if (type.equals(WorkflowModule.Properties.Section.Component.Type.DATE)) {
                    return new Date(this, linearLayout, component);
                }
                break;
            case 3143036:
                if (type.equals("file")) {
                    return new FileUpload(this, linearLayout, component);
                }
                break;
            case 3322014:
                if (type.equals(WorkflowModule.Properties.Section.Component.Type.LIST)) {
                    return new DynamicList(this, linearLayout, component);
                }
                break;
            case 3556653:
                if (type.equals("text")) {
                    return new Text(this, linearLayout, component);
                }
                break;
            case 100313435:
                if (type.equals("image")) {
                    return new Image(this, linearLayout, component);
                }
                break;
            case 102727412:
                if (type.equals("label")) {
                    return new Label(this, linearLayout, component);
                }
                break;
            case 108270587:
                if (type.equals(WorkflowModule.Properties.Section.Component.Type.RADIO)) {
                    return new Radio(this, linearLayout, component);
                }
                break;
            case 110364485:
                if (type.equals(WorkflowModule.Properties.Section.Component.Type.TIMER)) {
                    return new Timer(this, linearLayout, component);
                }
                break;
            case 1387629604:
                if (type.equals(WorkflowModule.Properties.Section.Component.Type.HORIZONTAL)) {
                    return new Container(this, 0, linearLayout, component);
                }
                break;
            case 1536891843:
                if (type.equals(WorkflowModule.Properties.Section.Component.Type.CHECKBOX)) {
                    return new Checkbox(this, linearLayout, component);
                }
                break;
            case 1674318617:
                if (type.equals(WorkflowModule.Properties.Section.Component.Type.DIVIDER)) {
                    return new Divider(this, linearLayout, component);
                }
                break;
        }
        throw new NotImplementedError("An operation is not implemented: " + ("Type " + component.getType() + " is not a supported component"));
    }

    public final String stringInjectFromVariables$hyperkyc_release(String str, boolean z) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(str, "<this>");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str2 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = str.getClass();
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
        String str3 = str + ".stringInjectFromVariables() called with: addQuotesIfEmpty = " + z;
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
                        Class<?> cls2 = str.getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str2 = canonicalName2;
                        }
                    } else {
                        str2 = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher2.find()) {
                        str2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str4 = str + ".stringInjectFromVariables() called with: addQuotesIfEmpty = " + z;
                    if (str4 == null) {
                        str4 = "null ";
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(2, str2, sb2.toString());
                }
            }
        }
        return getMainVM$hyperkyc_release().stringInjectFromVariables$hyperkyc_release(str, z);
    }

    public final Object anyInjectFromVariables$hyperkyc_release(String str) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(str, "<this>");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str2 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = str.getClass();
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
        String str3 = str + ".anyInjectFromVariables() called";
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
                        Class<?> cls2 = str.getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str2 = canonicalName2;
                        }
                    } else {
                        str2 = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher2.find()) {
                        str2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str4 = str + ".anyInjectFromVariables() called";
                    if (str4 == null) {
                        str4 = "null ";
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(2, str2, sb2.toString());
                }
            }
        }
        return getMainVM$hyperkyc_release().anyInjectFromVariables$hyperkyc_release(str);
    }

    public final Object anyInjectFromVariablesForAny$hyperkyc_release(Object obj) {
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
            canonicalName = (obj == null || (cls = obj.getClass()) == null) ? null : cls.getCanonicalName();
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
        String str3 = obj + ".anyInjectFromVariablesForAny() called";
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
                        if (obj != null && (cls2 = obj.getClass()) != null) {
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
                    String str4 = obj + ".anyInjectFromVariablesForAny() called";
                    if (str4 == null) {
                        str4 = "null ";
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(2, str, sb2.toString());
                }
            }
        }
        return obj instanceof String ? anyInjectFromVariables$hyperkyc_release((String) obj) : obj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0143, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0153, code lost:
    
        if (r0 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0157, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0166, code lost:
    
        if (r0.find() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0168, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0173, code lost:
    
        if (r8.length() <= 23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0179, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x017c, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0183, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = r18 + ".makeSecure() called with: secure = " + r19 + ", etId = " + r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01a0, code lost:
    
        if (r4 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01a2, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01a4, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0156, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ void makeSecure$hyperkyc_release(TextInputLayout textInputLayout, boolean z, int i) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(textInputLayout, "<this>");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str3 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = textInputLayout.getClass();
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
        String str4 = textInputLayout + ".makeSecure() called with: secure = " + z + ", etId = " + i;
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
                    Class<?> cls2 = textInputLayout.getClass();
                    str2 = cls2 != null ? cls2.getCanonicalName() : str;
                }
            }
        }
        if (z) {
            ((TextInputEditText) textInputLayout.findViewById(i)).setTransformationMethod(PasswordTransformationMethod.getInstance());
            textInputLayout.setEndIconMode(1);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:86:0x0142, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String getDateContentWithFormat$hyperkyc_release(WorkflowModule.Properties.Section.Component component) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        List<String> groupValues;
        String str;
        String value;
        String className2;
        Intrinsics.checkNotNullParameter(component, "<this>");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str2 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = component.getClass();
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
        String str4 = component.getId() + ".getDateContentWithFormat() called";
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = component.getClass();
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
                    StringBuilder sb2 = new StringBuilder();
                    String str5 = component.getId() + ".getDateContentWithFormat() called";
                    if (str5 == null) {
                        str5 = "null ";
                    }
                    sb2.append(str5);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str2, sb2.toString());
                }
            }
        }
        if (component.getContent() == null || component.getFormat() == null) {
            return null;
        }
        String stringInjectFromVariables$hyperkyc_release$default = stringInjectFromVariables$hyperkyc_release$default(this, component.getContent(), false, 1, null);
        MatchResult find$default = Regex.find$default(new Regex("[^\\w{}]+"), stringInjectFromVariables$hyperkyc_release$default, 0, 2, null);
        if (find$default != null && (value = find$default.getValue()) != null) {
            str3 = value;
        }
        List split$default = StringsKt.split$default((CharSequence) stringInjectFromVariables$hyperkyc_release$default, new String[]{str3}, false, 0, 6, (Object) null);
        List split$default2 = StringsKt.split$default((CharSequence) component.getFormat(), new String[]{str3}, false, 0, 6, (Object) null);
        List list = split$default;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str6 = (String) obj;
            if (StringsKt.startsWith$default(str6, "{{", false, 2, (Object) null)) {
                MatchResult find$default2 = Regex.find$default(new Regex("\\{\\{(\\d+)\\}\\}"), str6, 0, 2, null);
                str6 = ((String) split$default2.get(i)).substring(0, RangesKt.coerceAtMost((find$default2 == null || (groupValues = find$default2.getGroupValues()) == null || (str = groupValues.get(1)) == null) ? 0 : Integer.parseInt(str), ((String) split$default2.get(i)).length()));
                Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            arrayList.add(str6);
            i = i2;
        }
        return CollectionsKt.joinToString$default(arrayList, str3, null, null, 0, null, null, 62, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:22:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x02ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void filePickerLauncher$lambda$2(FormFragment this$0, List list) {
        String canonicalName;
        Object m1202constructorimpl;
        CharSequence charSequence;
        String str;
        String canonicalName2;
        String className;
        String str2;
        Object m1202constructorimpl2;
        String str3;
        String str4;
        Matcher matcher;
        String className2;
        String className3;
        Function1<? super List<? extends Uri>, Unit> function1;
        String className4;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = this$0.getClass();
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
        String str5 = "filePickerLauncher callback() called with " + list;
        if (str5 == null) {
            str5 = "null ";
        }
        sb.append(str5);
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
                        Class<?> cls2 = this$0.getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = str;
                        }
                    }
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher3.find()) {
                        canonicalName2 = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str6 = "filePickerLauncher callback() called with " + list;
                    if (str6 == null) {
                        str6 = "null ";
                    }
                    sb2.append(str6);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                }
                if (list == null) {
                    Function1<? super List<? extends Uri>, Unit> function12 = this$0.filePickedCallback;
                    if (function12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("filePickedCallback");
                        function1 = null;
                    } else {
                        function1 = function12;
                    }
                    function1.invoke(list);
                    return;
                }
                HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb3 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (str2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls3 = this$0.getClass();
                    String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                    str2 = canonicalName3 == null ? str : canonicalName3;
                }
                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                if (matcher4.find()) {
                    str2 = matcher4.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                }
                if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str2 = str2.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb3.append(str2);
                sb3.append(" - ");
                sb3.append("error picking file");
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
                        if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                            str3 = null;
                        } else {
                            str3 = null;
                            String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            if (substringAfterLast$default != null) {
                                str4 = substringAfterLast$default;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                                if (matcher.find()) {
                                    str4 = matcher.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                                }
                                if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str4 = str4.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                Log.println(6, str4, "error picking file ");
                                return;
                            }
                        }
                        Class<?> cls4 = this$0.getClass();
                        String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str3;
                        str4 = canonicalName4 == null ? str : canonicalName4;
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                        if (matcher.find()) {
                        }
                        if (str4.length() > 23) {
                            str4 = str4.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        Log.println(6, str4, "error picking file ");
                        return;
                    }
                    return;
                }
                return;
            }
        }
        charSequence = "co.hyperverge";
        str = "N/A";
        if (list == null) {
        }
    }
}
