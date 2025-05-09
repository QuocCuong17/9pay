package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import co.hyperverge.hyperkyc.data.models.KycCountry;
import co.hyperverge.hyperkyc.databinding.HkFragmentCountryPickerBinding;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.DrawableExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.PicassoExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.UIExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CountryPickerFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.CountryPickerFragment$updateSelectedCountry$2$1", f = "CountryPickerFragment.kt", i = {0, 0, 0}, l = {348}, m = "invokeSuspend", n = {"downArrow", "flagDrawable", "$this$invokeSuspend_u24lambda_u244"}, s = {"L$0", "L$1", "L$4"})
/* loaded from: classes2.dex */
public final class CountryPickerFragment$updateSelectedCountry$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ KycCountry $new;
    final /* synthetic */ HkFragmentCountryPickerBinding $this_with;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    final /* synthetic */ CountryPickerFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CountryPickerFragment$updateSelectedCountry$2$1(CountryPickerFragment countryPickerFragment, HkFragmentCountryPickerBinding hkFragmentCountryPickerBinding, KycCountry kycCountry, Continuation<? super CountryPickerFragment$updateSelectedCountry$2$1> continuation) {
        super(2, continuation);
        this.this$0 = countryPickerFragment;
        this.$this_with = hkFragmentCountryPickerBinding;
        this.$new = kycCountry;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CountryPickerFragment$updateSelectedCountry$2$1(this.this$0, this.$this_with, this.$new, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CountryPickerFragment$updateSelectedCountry$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x02bf  */
    /* JADX WARN: Type inference failed for: r12v10, types: [T] */
    /* JADX WARN: Type inference failed for: r12v22, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v24, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v25 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r14v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2, types: [T] */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r3v15, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v18, types: [T, android.graphics.drawable.BitmapDrawable] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Drawable drawableOf;
        Ref.ObjectRef objectRef;
        CountryPickerFragment countryPickerFragment;
        Ref.ObjectRef objectRef2;
        KycCountry kycCountry;
        Object flagBitmap;
        Ref.ObjectRef objectRef3;
        HkFragmentCountryPickerBinding hkFragmentCountryPickerBinding;
        Object m1202constructorimpl;
        Throwable m1205exceptionOrNullimpl;
        Ref.ObjectRef objectRef4;
        Drawable drawable;
        ?? canonicalName;
        Class<?> cls;
        String str;
        String str2;
        Object m1202constructorimpl2;
        Class<?> cls2;
        String str3;
        String className;
        String substringAfterLast$default;
        String className2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FragmentActivity activity = this.this$0.getActivity();
            drawableOf = activity != null ? UIExtsKt.drawableOf(activity, R.drawable.hk_ic_baseline_keyboard_arrow_down_18) : null;
            this.$this_with.btnCountry.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, drawableOf, (Drawable) null);
            if (drawableOf != null) {
                DrawableExtsKt.setIconColor(drawableOf, Color.parseColor(HyperSnapBridgeKt.getUiConfigUtil().getConfig().getColors().getPickerIconColor()));
            }
            objectRef = new Ref.ObjectRef();
            KycCountry kycCountry2 = this.$new;
            if (kycCountry2 != null) {
                HkFragmentCountryPickerBinding hkFragmentCountryPickerBinding2 = this.$this_with;
                countryPickerFragment = this.this$0;
                String nullIfBlank = CoreExtsKt.nullIfBlank(kycCountry2.getName());
                if (nullIfBlank != null) {
                    hkFragmentCountryPickerBinding2.btnCountry.setText(nullIfBlank);
                }
                try {
                    Result.Companion companion = Result.INSTANCE;
                    String id2 = kycCountry2.getId();
                    this.L$0 = drawableOf;
                    this.L$1 = objectRef;
                    this.L$2 = hkFragmentCountryPickerBinding2;
                    this.L$3 = countryPickerFragment;
                    this.L$4 = kycCountry2;
                    this.L$5 = objectRef;
                    this.label = 1;
                    flagBitmap = PicassoExtsKt.getFlagBitmap(id2, this);
                    if (flagBitmap == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef3 = objectRef;
                    kycCountry = kycCountry2;
                    hkFragmentCountryPickerBinding = hkFragmentCountryPickerBinding2;
                    objectRef2 = objectRef3;
                } catch (Throwable th) {
                    th = th;
                    objectRef2 = objectRef;
                    kycCountry = kycCountry2;
                    Result.Companion companion2 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    KycCountry kycCountry3 = kycCountry;
                    Object obj2 = m1202constructorimpl;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj2);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    Result.m1201boximpl(obj2);
                    objectRef = objectRef4;
                    drawableOf = drawable;
                    this.$this_with.btnCountry.setCompoundDrawablesWithIntrinsicBounds((Drawable) objectRef.element, (Drawable) null, drawableOf, (Drawable) null);
                    return Unit.INSTANCE;
                }
            }
            this.$this_with.btnCountry.setCompoundDrawablesWithIntrinsicBounds((Drawable) objectRef.element, (Drawable) null, drawableOf, (Drawable) null);
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        objectRef3 = (Ref.ObjectRef) this.L$5;
        kycCountry = (KycCountry) this.L$4;
        CountryPickerFragment countryPickerFragment2 = (CountryPickerFragment) this.L$3;
        hkFragmentCountryPickerBinding = (HkFragmentCountryPickerBinding) this.L$2;
        objectRef2 = (Ref.ObjectRef) this.L$1;
        drawableOf = (Drawable) this.L$0;
        try {
            ResultKt.throwOnFailure(obj);
            countryPickerFragment = countryPickerFragment2;
            flagBitmap = obj;
        } catch (Throwable th2) {
            th = th2;
            Result.Companion companion22 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            KycCountry kycCountry32 = kycCountry;
            Object obj22 = m1202constructorimpl;
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj22);
            if (m1205exceptionOrNullimpl != null) {
            }
            Result.m1201boximpl(obj22);
            objectRef = objectRef4;
            drawableOf = drawable;
            this.$this_with.btnCountry.setCompoundDrawablesWithIntrinsicBounds((Drawable) objectRef.element, (Drawable) null, drawableOf, (Drawable) null);
            return Unit.INSTANCE;
        }
        Resources resources = countryPickerFragment.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "resources");
        objectRef3.element = new BitmapDrawable(resources, (Bitmap) flagBitmap);
        hkFragmentCountryPickerBinding.btnCountry.setIconPadding(30);
        m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
        KycCountry kycCountry322 = kycCountry;
        Object obj222 = m1202constructorimpl;
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj222);
        if (m1205exceptionOrNullimpl != null) {
            HyperLogger.Level level = HyperLogger.Level.ERROR;
            HyperLogger companion3 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            ?? r14 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                canonicalName = (kycCountry322 == null || (cls = kycCountry322.getClass()) == null) ? 0 : cls.getCanonicalName();
                if (canonicalName == 0) {
                    canonicalName = "N/A";
                }
            }
            objectRef5.element = canonicalName;
            Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef5.element);
            String str4 = "";
            if (matcher.find()) {
                ?? replaceAll = matcher.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                objectRef5.element = replaceAll;
            }
            objectRef4 = objectRef2;
            drawable = drawableOf;
            if (((String) objectRef5.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                str = (String) objectRef5.element;
            } else {
                str = ((String) objectRef5.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(str);
            sb.append(" - ");
            sb.append("failed to set flag to btnCountry");
            sb.append(' ');
            String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
            if (localizedMessage != null) {
                str2 = '\n' + localizedMessage;
            } else {
                str2 = "";
            }
            sb.append(str2);
            companion3.log(level, sb.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion4 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th3) {
                Result.Companion companion5 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                m1202constructorimpl2 = "";
            }
            String packageName = (String) m1202constructorimpl2;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        String canonicalName2 = (kycCountry322 == null || (cls2 = kycCountry322.getClass()) == null) ? null : cls2.getCanonicalName();
                        if (canonicalName2 != null) {
                            r14 = canonicalName2;
                        }
                    } else {
                        r14 = substringAfterLast$default;
                    }
                    objectRef6.element = r14;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef6.element);
                    if (matcher2.find()) {
                        ?? replaceAll2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                        objectRef6.element = replaceAll2;
                    }
                    if (((String) objectRef6.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str3 = (String) objectRef6.element;
                    } else {
                        str3 = ((String) objectRef6.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("failed to set flag to btnCountry");
                    sb2.append(' ');
                    String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                    if (localizedMessage2 != null) {
                        str4 = '\n' + localizedMessage2;
                    }
                    sb2.append(str4);
                    Log.println(6, str3, sb2.toString());
                }
            }
        } else {
            objectRef4 = objectRef2;
            drawable = drawableOf;
        }
        Result.m1201boximpl(obj222);
        objectRef = objectRef4;
        drawableOf = drawable;
        this.$this_with.btnCountry.setCompoundDrawablesWithIntrinsicBounds((Drawable) objectRef.element, (Drawable) null, drawableOf, (Drawable) null);
        return Unit.INSTANCE;
    }
}
