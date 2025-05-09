package co.hyperverge.hyperkyc.ui.form;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter;
import co.hyperverge.hyperkyc.ui.form.FormFragment;
import co.hyperverge.hyperkyc.ui.form.models.PickedFile;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoroutineExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FormFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$addPickedFiles$5$2$1$1$1", f = "FormFragment.kt", i = {0, 1}, l = {2108, 2120, 2127, 2137}, m = "invokeSuspend", n = {"$this$onDefault", "$this$onDefault"}, s = {"L$0", "L$0"})
/* loaded from: classes2.dex */
public final class FormFragment$FileUpload$addPickedFiles$5$2$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ File $formFilesDir;
    final /* synthetic */ PickedFile $pickedFile;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ FormFragment this$0;
    final /* synthetic */ FormFragment.FileUpload this$1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormFragment$FileUpload$addPickedFiles$5$2$1$1$1(PickedFile pickedFile, FormFragment formFragment, FormFragment.FileUpload fileUpload, File file, Continuation<? super FormFragment$FileUpload$addPickedFiles$5$2$1$1$1> continuation) {
        super(2, continuation);
        this.$pickedFile = pickedFile;
        this.this$0 = formFragment;
        this.this$1 = fileUpload;
        this.$formFilesDir = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FormFragment$FileUpload$addPickedFiles$5$2$1$1$1 formFragment$FileUpload$addPickedFiles$5$2$1$1$1 = new FormFragment$FileUpload$addPickedFiles$5$2$1$1$1(this.$pickedFile, this.this$0, this.this$1, this.$formFilesDir, continuation);
        formFragment$FileUpload$addPickedFiles$5$2$1$1$1.L$0 = obj;
        return formFragment$FileUpload$addPickedFiles$5$2$1$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FormFragment$FileUpload$addPickedFiles$5$2$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x0229, code lost:
    
        if (r2 != null) goto L100;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x030c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b3  */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v28, types: [T] */
    /* JADX WARN: Type inference failed for: r2v38, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v51, types: [kotlin.coroutines.Continuation, java.lang.Object, kotlinx.coroutines.CoroutineDispatcher] */
    /* JADX WARN: Type inference failed for: r2v52 */
    /* JADX WARN: Type inference failed for: r2v53 */
    /* JADX WARN: Type inference failed for: r3v10, types: [T] */
    /* JADX WARN: Type inference failed for: r3v29, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v31, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v34 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object m1202constructorimpl;
        Throwable m1205exceptionOrNullimpl;
        ?? canonicalName;
        Class<?> cls;
        String str;
        String str2;
        Object m1202constructorimpl2;
        ?? r2;
        Object obj2;
        String str3;
        Class<?> cls2;
        String str4;
        String className;
        String className2;
        CoroutineScope coroutineScope;
        PickedFile pickedFile;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Throwable th) {
            Result.Companion companion = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.L$0;
            pickedFile = this.$pickedFile;
            final FormFragment formFragment = this.this$0;
            FormFragment.FileUpload fileUpload = this.this$1;
            File file = this.$formFilesDir;
            Result.Companion companion2 = Result.INSTANCE;
            pickedFile.initMetadata(formFragment.getContentResolver$hyperkyc_release(), fileUpload.getComponent(), new Function1<String, String>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$1$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final String invoke(String initMetadata) {
                    Intrinsics.checkNotNullParameter(initMetadata, "$this$initMetadata");
                    return FormFragment.stringInjectFromVariables$hyperkyc_release$default(FormFragment.this, initMetadata, false, 1, null);
                }
            });
            String extension = pickedFile.getExtension();
            if (extension != null) {
                pickedFile.setType(invokeSuspend$lambda$2$getFileType(fileUpload, extension));
            }
            FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$1$3 formFragment$FileUpload$addPickedFiles$5$2$1$1$1$1$3 = new FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$1$3(file, pickedFile, formFragment, null);
            this.L$0 = coroutineScope;
            this.L$1 = pickedFile;
            this.label = 1;
            if (CoroutineExtsKt.onIO$default(null, formFragment$FileUpload$addPickedFiles$5$2$1$1$1$1$3, this, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    ResultKt.throwOnFailure(obj);
                    obj2 = coroutine_suspended;
                    r2 = 0;
                    this.L$0 = r2;
                    this.L$1 = r2;
                    this.label = 4;
                    if (CoroutineExtsKt.onUI$default(r2, new AnonymousClass3(this.this$1, this.$pickedFile, r2), this, 1, r2) == obj2) {
                        return obj2;
                    }
                    return Unit.INSTANCE;
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                CoroutineScope coroutineScope2 = coroutineScope;
                Object obj3 = m1202constructorimpl;
                PickedFile pickedFile2 = this.$pickedFile;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj3);
                if (m1205exceptionOrNullimpl != null) {
                    HyperLogger.Level level = HyperLogger.Level.ERROR;
                    HyperLogger companion3 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                        canonicalName = (coroutineScope2 == null || (cls = coroutineScope2.getClass()) == null) ? 0 : cls.getCanonicalName();
                        if (canonicalName == 0) {
                            canonicalName = "N/A";
                        }
                    }
                    objectRef.element = canonicalName;
                    Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                    String str5 = "";
                    if (matcher.find()) {
                        ?? replaceAll = matcher.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                        objectRef.element = replaceAll;
                    }
                    if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str = (String) objectRef.element;
                    } else {
                        str = ((String) objectRef.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(str);
                    sb.append(" - ");
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Error loading file: ");
                    Comparable name = pickedFile2.getName();
                    if (name == null) {
                        name = pickedFile2.getUri();
                    }
                    sb2.append(name);
                    sb2.append(' ');
                    String sb3 = sb2.toString();
                    if (sb3 == null) {
                        sb3 = "null ";
                    }
                    sb.append(sb3);
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
                    } catch (Throwable th2) {
                        Result.Companion companion5 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String packageName = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                            StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                            if (stackTraceElement2 != null && (className = stackTraceElement2.getClassName()) != null) {
                                String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                str3 = substringAfterLast$default;
                            }
                            String canonicalName2 = (coroutineScope2 == null || (cls2 = coroutineScope2.getClass()) == null) ? null : cls2.getCanonicalName();
                            str3 = canonicalName2 == null ? "N/A" : canonicalName2;
                            objectRef2.element = str3;
                            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                            if (matcher2.find()) {
                                ?? replaceAll2 = matcher2.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                objectRef2.element = replaceAll2;
                            }
                            if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                str4 = (String) objectRef2.element;
                            } else {
                                str4 = ((String) objectRef2.element).substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb4 = new StringBuilder();
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append("Error loading file: ");
                            Comparable name2 = pickedFile2.getName();
                            if (name2 == null) {
                                name2 = pickedFile2.getUri();
                            }
                            sb5.append(name2);
                            sb5.append(' ');
                            String sb6 = sb5.toString();
                            if (sb6 == null) {
                                sb6 = "null ";
                            }
                            sb4.append(sb6);
                            sb4.append(' ');
                            String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                            if (localizedMessage2 != null) {
                                str5 = '\n' + localizedMessage2;
                            }
                            sb4.append(str5);
                            Log.println(6, str4, sb4.toString());
                        }
                    }
                    r2 = 0;
                    FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$2$2 formFragment$FileUpload$addPickedFiles$5$2$1$1$1$2$2 = new FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$2$2(pickedFile2, m1205exceptionOrNullimpl, null);
                    this.L$0 = obj3;
                    this.L$1 = null;
                    this.label = 3;
                    obj2 = coroutine_suspended;
                    if (CoroutineExtsKt.onUI$default(null, formFragment$FileUpload$addPickedFiles$5$2$1$1$1$2$2, this, 1, null) == obj2) {
                        return obj2;
                    }
                    this.L$0 = r2;
                    this.L$1 = r2;
                    this.label = 4;
                    if (CoroutineExtsKt.onUI$default(r2, new AnonymousClass3(this.this$1, this.$pickedFile, r2), this, 1, r2) == obj2) {
                    }
                    return Unit.INSTANCE;
                }
                obj2 = coroutine_suspended;
                r2 = 0;
                this.L$0 = r2;
                this.L$1 = r2;
                this.label = 4;
                if (CoroutineExtsKt.onUI$default(r2, new AnonymousClass3(this.this$1, this.$pickedFile, r2), this, 1, r2) == obj2) {
                }
                return Unit.INSTANCE;
            }
            pickedFile = (PickedFile) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$1$4 formFragment$FileUpload$addPickedFiles$5$2$1$1$1$1$4 = new FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$1$4(pickedFile, null);
        this.L$0 = coroutineScope;
        this.L$1 = null;
        this.label = 2;
        if (CoroutineExtsKt.onUI$default(null, formFragment$FileUpload$addPickedFiles$5$2$1$1$1$1$4, this, 1, null) == coroutine_suspended) {
            return coroutine_suspended;
        }
        m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
        CoroutineScope coroutineScope22 = coroutineScope;
        Object obj32 = m1202constructorimpl;
        PickedFile pickedFile22 = this.$pickedFile;
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj32);
        if (m1205exceptionOrNullimpl != null) {
        }
        obj2 = coroutine_suspended;
        r2 = 0;
        this.L$0 = r2;
        this.L$1 = r2;
        this.label = 4;
        if (CoroutineExtsKt.onUI$default(r2, new AnonymousClass3(this.this$1, this.$pickedFile, r2), this, 1, r2) == obj2) {
        }
        return Unit.INSTANCE;
    }

    private static final String invokeSuspend$lambda$2$getFileType(FormFragment.FileUpload fileUpload, String str) {
        List<WorkflowModule.Properties.Section.Component.SupportedFile> supportedFiles = fileUpload.getComponent().getSupportedFiles();
        if (supportedFiles != null) {
            for (WorkflowModule.Properties.Section.Component.SupportedFile supportedFile : supportedFiles) {
                if (supportedFile.getExtensions().contains(str)) {
                    if (supportedFile != null) {
                        return supportedFile.getType();
                    }
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$3", f = "FormFragment.kt", i = {}, l = {2138}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$3, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PickedFile $pickedFile;
        int label;
        final /* synthetic */ FormFragment.FileUpload this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(FormFragment.FileUpload fileUpload, PickedFile pickedFile, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.this$0 = fileUpload;
            this.$pickedFile = pickedFile;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(this.this$0, this.$pickedFile, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SimpleRvAdapter simpleRvAdapter = this.this$0.pickedFilesRvAdapter;
                final PickedFile pickedFile = this.$pickedFile;
                this.label = 1;
                if (simpleRvAdapter.updateItem(new Function1<PickedFile, Boolean>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment.FileUpload.addPickedFiles.5.2.1.1.1.3.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(PickedFile it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return Boolean.valueOf(Intrinsics.areEqual(it.getUri(), PickedFile.this.getUri()));
                    }
                }, (Function1<PickedFile, Boolean>) this.$pickedFile, (Continuation<? super Unit>) this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }
}
