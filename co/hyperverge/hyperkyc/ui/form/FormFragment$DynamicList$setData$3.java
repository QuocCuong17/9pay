package co.hyperverge.hyperkyc.ui.form;

import android.view.View;
import android.widget.LinearLayout;
import androidx.media3.exoplayer.upstream.CmcdData;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.ui.form.FormFragment;
import co.hyperverge.hyperkyc.utils.extensions.Margin;
import co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FormFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.form.FormFragment$DynamicList$setData$3", f = "FormFragment.kt", i = {0, 0}, l = {3136}, m = "invokeSuspend", n = {"$this$launchWhenResumed", CmcdData.Factory.OBJECT_TYPE_INIT_SEGMENT}, s = {"L$0", "I$0"})
/* loaded from: classes2.dex */
public final class FormFragment$DynamicList$setData$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<Object> $updatedDataList;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ FormFragment this$0;
    final /* synthetic */ FormFragment.DynamicList this$1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormFragment$DynamicList$setData$3(List<? extends Object> list, FormFragment formFragment, FormFragment.DynamicList dynamicList, Continuation<? super FormFragment$DynamicList$setData$3> continuation) {
        super(2, continuation);
        this.$updatedDataList = list;
        this.this$0 = formFragment;
        this.this$1 = dynamicList;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FormFragment$DynamicList$setData$3 formFragment$DynamicList$setData$3 = new FormFragment$DynamicList$setData$3(this.$updatedDataList, this.this$0, this.this$1, continuation);
        formFragment$DynamicList$setData$3.L$0 = obj;
        return formFragment$DynamicList$setData$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FormFragment$DynamicList$setData$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0038  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0073 -> B:5:0x0075). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:9:0x0060 -> B:6:0x0077). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        FormFragment$DynamicList$setData$3 formFragment$DynamicList$setData$3;
        CoroutineScope coroutineScope;
        int i;
        int size;
        Job launch$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            formFragment$DynamicList$setData$3 = this;
            coroutineScope = (CoroutineScope) this.L$0;
            i = 0;
            size = this.$updatedDataList.size();
            if (i < size) {
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            size = this.I$1;
            int i3 = this.I$0;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            formFragment$DynamicList$setData$3 = this;
            i = i3;
            coroutineScope = coroutineScope2;
            i++;
            if (i < size) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getMain(), null, new AnonymousClass1(formFragment$DynamicList$setData$3.this$0, formFragment$DynamicList$setData$3.this$1, i, formFragment$DynamicList$setData$3.$updatedDataList, null), 2, null);
                if (formFragment$DynamicList$setData$3.$updatedDataList.size() > 6) {
                    formFragment$DynamicList$setData$3.L$0 = coroutineScope;
                    formFragment$DynamicList$setData$3.L$1 = launch$default;
                    formFragment$DynamicList$setData$3.I$0 = i;
                    formFragment$DynamicList$setData$3.I$1 = size;
                    formFragment$DynamicList$setData$3.label = 1;
                    if (launch$default.join(formFragment$DynamicList$setData$3) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    i3 = i;
                    coroutineScope2 = coroutineScope;
                    i = i3;
                    coroutineScope = coroutineScope2;
                }
                i++;
                if (i < size) {
                    return Unit.INSTANCE;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.form.FormFragment$DynamicList$setData$3$1", f = "FormFragment.kt", i = {0, 0}, l = {3129}, m = "invokeSuspend", n = {"it", "cellData"}, s = {"L$2", "L$3"})
    /* renamed from: co.hyperverge.hyperkyc.ui.form.FormFragment$DynamicList$setData$3$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $i;
        final /* synthetic */ List<Object> $updatedDataList;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ FormFragment this$0;
        final /* synthetic */ FormFragment.DynamicList this$1;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(FormFragment formFragment, FormFragment.DynamicList dynamicList, int i, List<? extends Object> list, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = formFragment;
            this.this$1 = dynamicList;
            this.$i = i;
            this.$updatedDataList = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.this$1, this.$i, this.$updatedDataList, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            FormFragment.CompView<? extends View, ? extends Object> createComponent$hyperkyc_release;
            List list;
            Object reloadComp;
            FormFragment.DynamicList dynamicList;
            Object obj2;
            List<WorkflowModule.Properties.Section.Component> subComponents;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FormFragment formFragment = this.this$0;
                LinearLayout addCell = this.this$1.getView().addCell(this.$i);
                WorkflowModule.Properties.Section.Component itemsGenerator = this.this$1.getComponent().getItemsGenerator();
                Intrinsics.checkNotNull(itemsGenerator);
                createComponent$hyperkyc_release = formFragment.createComponent$hyperkyc_release(addCell, itemsGenerator);
                FormFragment.DynamicList dynamicList2 = this.this$1;
                FormFragment formFragment2 = this.this$0;
                List<Object> list2 = this.$updatedDataList;
                int i2 = this.$i;
                list = dynamicList2.localCompList;
                list.add(createComponent$hyperkyc_release);
                List list3 = formFragment2.compViews;
                if (list3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("compViews");
                    list3 = null;
                }
                list3.add(createComponent$hyperkyc_release);
                Object obj3 = list2.get(i2);
                try {
                    Result.Companion companion = Result.INSTANCE;
                    createComponent$hyperkyc_release.render(new Margin(0, ViewExtsKt.getDp(6), 0, ViewExtsKt.getDp(6), 5, null));
                    Result.m1202constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    Result.m1202constructorimpl(ResultKt.createFailure(th));
                }
                if ((createComponent$hyperkyc_release instanceof FormFragment.Container) && (subComponents = createComponent$hyperkyc_release.getComponent().getSubComponents()) != null) {
                    List<FormFragment.CompView<? extends View, ? extends Object>> createComponents$hyperkyc_release = formFragment2.createComponents$hyperkyc_release((LinearLayout) ((FormFragment.Container) createComponent$hyperkyc_release).getView(), subComponents);
                    formFragment2.renderAll$hyperkyc_release(createComponents$hyperkyc_release, new Margin(0, 0, 0, ViewExtsKt.getDp(12), 7, null));
                    createComponent$hyperkyc_release.setChildCompViews(createComponents$hyperkyc_release);
                }
                this.L$0 = createComponent$hyperkyc_release;
                this.L$1 = dynamicList2;
                this.L$2 = createComponent$hyperkyc_release;
                this.L$3 = obj3;
                this.label = 1;
                reloadComp = dynamicList2.reloadComp(createComponent$hyperkyc_release, obj3, this);
                if (reloadComp == coroutine_suspended) {
                    return coroutine_suspended;
                }
                dynamicList = dynamicList2;
                obj2 = obj3;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                obj2 = this.L$3;
                createComponent$hyperkyc_release = (FormFragment.CompView) this.L$2;
                dynamicList = (FormFragment.DynamicList) this.L$1;
                ResultKt.throwOnFailure(obj);
            }
            dynamicList.setItemGeneratorClickHandler(createComponent$hyperkyc_release, obj2);
            return Unit.INSTANCE;
        }
    }
}
