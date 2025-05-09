package co.hyperverge.hyperkyc.ui.custom;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import defpackage.WakelockPlusApi$Companion$codec$2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;

/* compiled from: SimpleRvAdapter.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0010\u0018\u0000 :*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0005:\u0001:Bª\u0002\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007\u00126\u0010\t\u001a2\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00028\u00010\n\u0012\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00110\n\u00126\u0010\u0012\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\n\u00128\b\u0002\u0010\u0016\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\n\u0012\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007\u0012%\b\u0002\u0010\u0017\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00180\u0007¢\u0006\u0002\b\u0019\u0012\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00028\u0000¢\u0006\u0002\u0010\u001fJ\u001b\u0010\u001d\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\b2\u0006\u0010\u001e\u001a\u00028\u0000¢\u0006\u0002\u0010!J\u0006\u0010\"\u001a\u00020\u0011J\u0010\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020\bH\u0016J\u001d\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00028\u00012\u0006\u0010$\u001a\u00020\bH\u0016¢\u0006\u0002\u0010'J\u001d\u0010(\u001a\u00028\u00012\u0006\u0010)\u001a\u00020*2\u0006\u0010\u000f\u001a\u00020\bH\u0016¢\u0006\u0002\u0010+J\u0006\u0010,\u001a\u00020\u0011J6\u0010-\u001a\u00020\u00112\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00150\u00072\u001a\b\u0002\u0010/\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0018\u0012\u0004\u0012\u00020\u00110\u0007J/\u0010-\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00028\u00002\u001a\b\u0002\u0010/\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0018\u0012\u0004\u0012\u00020\u00110\u0007¢\u0006\u0002\u00100J-\u00101\u001a\u00020\u00112\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00150\u00072\u0006\u0010\u001e\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0002\u00103JC\u00101\u001a\u00020\u00112\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00150\u00072\u0006\u0010\u001e\u001a\u00028\u00002\u001a\b\u0002\u0010/\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0018\u0012\u0004\u0012\u00020\u00110\u0007¢\u0006\u0002\u00104J7\u00101\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00028\u00002\u001a\b\u0002\u0010/\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0018\u0012\u0004\u0012\u00020\u00110\u0007¢\u0006\u0002\u00105J2\u00101\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\b2\u0017\u00106\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00110\u0007¢\u0006\u0002\b\u0019H\u0086@ø\u0001\u0000¢\u0006\u0002\u00107J0\u00108\u001a\u00020\u00112\f\u00109\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\u001a\b\u0002\u0010/\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0018\u0012\u0004\u0012\u00020\u00110\u0007R>\u0010\u0012\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\nX\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010\u0016\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\nX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00110\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u0017\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00180\u0007¢\u0006\u0002\b\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R>\u0010\t\u001a2\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00028\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006;"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/SimpleRvAdapter;", "M", "", StandardStructureTypes.H, "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroidx/recyclerview/widget/ListAdapter;", "layoutResId", "Lkotlin/Function1;", "", "vhBuilder", "Lkotlin/Function2;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", ViewHierarchyConstants.VIEW_KEY, "viewType", "binder", "", "areContentsSame", "old", "new", "", "areItemsSame", "sort", "", "Lkotlin/ExtensionFunctionType;", "diffCallback", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/recyclerview/widget/DiffUtil$ItemCallback;)V", "addItem", "item", "(Ljava/lang/Object;)V", "pos", "(ILjava/lang/Object;)V", "clear", "getItemViewType", "position", "onBindViewHolder", "holder", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "refresh", "removeItem", "predicate", "onComplete", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "updateItem", "posPredicate", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "(ILjava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "action", "(ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateItems", "itemList", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class SimpleRvAdapter<M, H extends RecyclerView.ViewHolder> extends ListAdapter<M, H> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Function2<M, M, Boolean> areContentsSame;
    private final Function2<M, M, Boolean> areItemsSame;
    private Function2<? super H, ? super M, Unit> binder;
    private final Function1<Integer, Integer> layoutResId;
    private Function1<? super List<? extends M>, ? extends List<? extends M>> sort;
    private final Function2<View, Integer, H> vhBuilder;
    private Function1<? super Integer, Integer> viewType;

    /* compiled from: SimpleRvAdapter.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0005*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "M", "", StandardStructureTypes.H, "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter$3 */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass3 extends Lambda implements Function1<List<? extends M>, List<? extends M>> {
        public static final AnonymousClass3 INSTANCE = ;

        AnonymousClass3() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function1
        public final List<M> invoke(List<? extends M> list) {
            Intrinsics.checkNotNullParameter(list, "$this$null");
            return list;
        }
    }

    /* compiled from: SimpleRvAdapter.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u0002H\u00022\u0006\u0010\u0007\u001a\u0002H\u0002H\n¢\u0006\u0004\b\b\u0010\t"}, d2 = {"<anonymous>", "", "M", "", StandardStructureTypes.H, "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "o", "n", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter$1 */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends Lambda implements Function2<M, M, Boolean> {
        public static final AnonymousClass1 INSTANCE = ;

        AnonymousClass1() {
        }

        @Override // kotlin.jvm.functions.Function2
        public final Boolean invoke(M o, M n) {
            Intrinsics.checkNotNullParameter(o, "o");
            Intrinsics.checkNotNullParameter(n, "n");
            return Boolean.valueOf(Intrinsics.areEqual(o, n));
        }
    }

    /* compiled from: SimpleRvAdapter.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "", "M", "", StandardStructureTypes.H, "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "it", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter$2 */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass2 extends Lambda implements Function1<Integer, Integer> {
        public static final AnonymousClass2 INSTANCE = ;

        AnonymousClass2() {
        }

        public final Integer invoke(int i) {
            return 0;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
            return invoke(num.intValue());
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ SimpleRvAdapter(Function1 function1, Function2 function2, Function2 function22, Function2 function23, Function2 function24, Function1 function12, Function1 function13, DiffUtil.ItemCallback itemCallback, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, function2, function22, function23, r7, (i & 32) != 0 ? AnonymousClass2.INSTANCE : function12, (i & 64) != 0 ? AnonymousClass3.INSTANCE : function13, (i & 128) != 0 ? INSTANCE.getDiffCallback$hyperkyc_release(r7, function23) : itemCallback);
        Function2 function25 = (i & 16) != 0 ? AnonymousClass1.INSTANCE : function24;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SimpleRvAdapter(Function1<? super Integer, Integer> layoutResId, Function2<? super View, ? super Integer, ? extends H> vhBuilder, Function2<? super H, ? super M, Unit> binder, Function2<? super M, ? super M, Boolean> areContentsSame, Function2<? super M, ? super M, Boolean> areItemsSame, Function1<? super Integer, Integer> viewType, Function1<? super List<? extends M>, ? extends List<? extends M>> sort, DiffUtil.ItemCallback<M> diffCallback) {
        super(diffCallback);
        Intrinsics.checkNotNullParameter(layoutResId, "layoutResId");
        Intrinsics.checkNotNullParameter(vhBuilder, "vhBuilder");
        Intrinsics.checkNotNullParameter(binder, "binder");
        Intrinsics.checkNotNullParameter(areContentsSame, "areContentsSame");
        Intrinsics.checkNotNullParameter(areItemsSame, "areItemsSame");
        Intrinsics.checkNotNullParameter(viewType, "viewType");
        Intrinsics.checkNotNullParameter(sort, "sort");
        Intrinsics.checkNotNullParameter(diffCallback, "diffCallback");
        this.layoutResId = layoutResId;
        this.vhBuilder = vhBuilder;
        this.binder = binder;
        this.areContentsSame = areContentsSame;
        this.areItemsSame = areItemsSame;
        this.viewType = viewType;
        this.sort = sort;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(this.layoutResId.invoke(Integer.valueOf(viewType)).intValue(), parent, false);
        Function2<View, Integer, H> function2 = this.vhBuilder;
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return function2.invoke(view, Integer.valueOf(viewType));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(H holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        M item = getItem(position);
        if (item != null) {
            this.binder.invoke(holder, item);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateItems$default(SimpleRvAdapter simpleRvAdapter, List list, Function1 function1, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateItems");
        }
        if ((i & 2) != 0) {
            function1 = new Function1<List<? extends M>, Unit>() { // from class: co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter$updateItems$1
                public final void invoke(List<? extends M> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj2) {
                    invoke((List) obj2);
                    return Unit.INSTANCE;
                }
            };
        }
        simpleRvAdapter.updateItems(list, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateItem$default(SimpleRvAdapter simpleRvAdapter, Function1 function1, Object obj, Function1 function12, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateItem");
        }
        if ((i & 4) != 0) {
            function12 = new Function1<List<? extends M>, Unit>() { // from class: co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter$updateItem$1
                public final void invoke(List<? extends M> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj3) {
                    invoke((List) obj3);
                    return Unit.INSTANCE;
                }
            };
        }
        simpleRvAdapter.updateItem((Function1<? super Function1, Boolean>) function1, (Function1) obj, (Function1<? super List<? extends Function1>, Unit>) function12);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateItem$default(SimpleRvAdapter simpleRvAdapter, int i, Object obj, Function1 function1, int i2, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateItem");
        }
        if ((i2 & 4) != 0) {
            function1 = new Function1<List<? extends M>, Unit>() { // from class: co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter$updateItem$3
                public final void invoke(List<? extends M> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj3) {
                    invoke((List) obj3);
                    return Unit.INSTANCE;
                }
            };
        }
        simpleRvAdapter.updateItem(i, (int) obj, (Function1<? super List<? extends int>, Unit>) function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0230 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x01f1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object updateItem(Function1<? super M, Boolean> function1, M m, Continuation<? super Unit> continuation) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        Matcher matcher;
        int i;
        String str2;
        String className;
        Iterator<M> it;
        int i2;
        Object orThrow;
        String className2;
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        final SafeContinuation safeContinuation2 = safeContinuation;
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
        String str4 = "updateItem() called with: posPredicate = [" + function1 + "], item = [" + m + ']';
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
                            if (str3.length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                i = 0;
                            } else {
                                i = 0;
                                str3 = str3.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb2 = new StringBuilder();
                            str2 = "updateItem() called with: posPredicate = [" + function1 + "], item = [" + m + ']';
                            if (str2 == null) {
                                str2 = "null ";
                            }
                            sb2.append(str2);
                            sb2.append(' ');
                            sb2.append("");
                            Log.println(3, str3, sb2.toString());
                            List<M> currentList = getCurrentList();
                            Intrinsics.checkNotNullExpressionValue(currentList, "currentList");
                            it = currentList.iterator();
                            i2 = i;
                            while (true) {
                                if (!it.hasNext()) {
                                    i2 = -1;
                                    break;
                                }
                                if (function1.invoke(it.next()).booleanValue()) {
                                    break;
                                }
                                i2++;
                            }
                            List<? extends M> list = (List) CollectionsKt.toCollection(new ArrayList(getCurrentList()), new ArrayList());
                            list.set(i2, m);
                            updateItems(list, new Function1<List<? extends M>, Unit>() { // from class: co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter$updateItem$6$2
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                                    invoke((List) obj);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(List<? extends M> it2) {
                                    Intrinsics.checkNotNullParameter(it2, "it");
                                    Continuation<Unit> continuation2 = safeContinuation2;
                                    Result.Companion companion4 = Result.INSTANCE;
                                    continuation2.resumeWith(Result.m1202constructorimpl(Unit.INSTANCE));
                                }
                            });
                            orThrow = safeContinuation.getOrThrow();
                            if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                DebugProbesKt.probeCoroutineSuspended(continuation);
                            }
                            return orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? orThrow : Unit.INSTANCE;
                        }
                    }
                    Class<?> cls2 = getClass();
                    String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : str;
                    if (canonicalName2 != null) {
                        str3 = canonicalName2;
                    }
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                    if (matcher.find()) {
                    }
                    if (str3.length() > 23) {
                    }
                    i = 0;
                    StringBuilder sb22 = new StringBuilder();
                    str2 = "updateItem() called with: posPredicate = [" + function1 + "], item = [" + m + ']';
                    if (str2 == null) {
                    }
                    sb22.append(str2);
                    sb22.append(' ');
                    sb22.append("");
                    Log.println(3, str3, sb22.toString());
                    List<M> currentList2 = getCurrentList();
                    Intrinsics.checkNotNullExpressionValue(currentList2, "currentList");
                    it = currentList2.iterator();
                    i2 = i;
                    while (true) {
                        if (!it.hasNext()) {
                        }
                        i2++;
                    }
                    List<? extends M> list2 = (List) CollectionsKt.toCollection(new ArrayList(getCurrentList()), new ArrayList());
                    list2.set(i2, m);
                    updateItems(list2, new Function1<List<? extends M>, Unit>() { // from class: co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter$updateItem$6$2
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                            invoke((List) obj);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(List<? extends M> it2) {
                            Intrinsics.checkNotNullParameter(it2, "it");
                            Continuation<Unit> continuation2 = safeContinuation2;
                            Result.Companion companion4 = Result.INSTANCE;
                            continuation2.resumeWith(Result.m1202constructorimpl(Unit.INSTANCE));
                        }
                    });
                    orThrow = safeContinuation.getOrThrow();
                    if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    }
                    if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    }
                }
            }
        }
        i = 0;
        List<M> currentList22 = getCurrentList();
        Intrinsics.checkNotNullExpressionValue(currentList22, "currentList");
        it = currentList22.iterator();
        i2 = i;
        while (true) {
            if (!it.hasNext()) {
            }
            i2++;
        }
        List<? extends M> list22 = (List) CollectionsKt.toCollection(new ArrayList(getCurrentList()), new ArrayList());
        list22.set(i2, m);
        updateItems(list22, new Function1<List<? extends M>, Unit>() { // from class: co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter$updateItem$6$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((List) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(List<? extends M> it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                Continuation<Unit> continuation2 = safeContinuation2;
                Result.Companion companion4 = Result.INSTANCE;
                continuation2.resumeWith(Result.m1202constructorimpl(Unit.INSTANCE));
            }
        });
        orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
        }
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object updateItem(int i, Function1<? super M, Unit> function1, Continuation<? super Unit> continuation) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        final SafeContinuation safeContinuation2 = safeContinuation;
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
        String str2 = "updateItem() called with: cont = [" + safeContinuation2 + ']';
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
                    String str3 = "updateItem() called with: cont = [" + safeContinuation2 + ']';
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
        List currentList = getCurrentList();
        WakelockPlusApi$Companion$codec$2 wakelockPlusApi$Companion$codec$2 = (Object) currentList.get(i);
        Intrinsics.checkNotNullExpressionValue(wakelockPlusApi$Companion$codec$2, "get(position)");
        function1.invoke(wakelockPlusApi$Companion$codec$2);
        Intrinsics.checkNotNullExpressionValue(currentList, "currentList.apply { get(position).action() }");
        updateItems(currentList, new Function1<List<? extends M>, Unit>() { // from class: co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter$updateItem$8$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((List) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(List<? extends M> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Continuation<Unit> continuation2 = safeContinuation2;
                Result.Companion companion4 = Result.INSTANCE;
                continuation2.resumeWith(Result.m1202constructorimpl(Unit.INSTANCE));
            }
        });
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? orThrow : Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void removeItem$default(SimpleRvAdapter simpleRvAdapter, Function1 function1, Function1 function12, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: removeItem");
        }
        if ((i & 2) != 0) {
            function12 = new Function1<List<? extends M>, Unit>() { // from class: co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter$removeItem$1
                public final void invoke(List<? extends M> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj2) {
                    invoke((List) obj2);
                    return Unit.INSTANCE;
                }
            };
        }
        simpleRvAdapter.removeItem(function1, function12);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void removeItem$default(SimpleRvAdapter simpleRvAdapter, Object obj, Function1 function1, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: removeItem");
        }
        if ((i & 2) != 0) {
            function1 = new Function1<List<? extends M>, Unit>() { // from class: co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter$removeItem$3
                public final void invoke(List<? extends M> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj3) {
                    invoke((List) obj3);
                    return Unit.INSTANCE;
                }
            };
        }
        simpleRvAdapter.removeItem((SimpleRvAdapter) obj, (Function1<? super List<? extends SimpleRvAdapter>, Unit>) function1);
    }

    public final void refresh() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(getCurrentList());
        submitList(arrayList);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return this.viewType.invoke(Integer.valueOf(position)).intValue();
    }

    public final void clear() {
        submitList(null);
    }

    /* compiled from: SimpleRvAdapter.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u008d\u0001\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\b\b\u0002\u0010\u0005*\u00020\u000126\u0010\u0006\u001a2\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u000726\u0010\r\u001a2\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0007H\u0000¢\u0006\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/SimpleRvAdapter$Companion;", "", "()V", "getDiffCallback", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "T", "areItemsSame", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "old", "new", "", "areContentsSame", "getDiffCallback$hyperkyc_release", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final /* synthetic */ DiffUtil.ItemCallback getDiffCallback$hyperkyc_release(final Function2 areItemsSame, final Function2 areContentsSame) {
            Intrinsics.checkNotNullParameter(areItemsSame, "areItemsSame");
            Intrinsics.checkNotNullParameter(areContentsSame, "areContentsSame");
            return new DiffUtil.ItemCallback<T>() { // from class: co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter$Companion$getDiffCallback$1
                @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
                public boolean areItemsTheSame(T oldItem, T newItem) {
                    Intrinsics.checkNotNullParameter(oldItem, "oldItem");
                    Intrinsics.checkNotNullParameter(newItem, "newItem");
                    return areItemsSame.invoke(oldItem, newItem).booleanValue();
                }

                @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
                public boolean areContentsTheSame(T oldItem, T newItem) {
                    Intrinsics.checkNotNullParameter(oldItem, "oldItem");
                    Intrinsics.checkNotNullParameter(newItem, "newItem");
                    return areContentsSame.invoke(oldItem, newItem).booleanValue();
                }
            };
        }
    }

    public final void addItem(M item) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(item, "item");
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
        String str2 = "addItem() called with: item = [" + item + ']';
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
                    String str3 = "addItem() called with: item = [" + item + ']';
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
        List<M> currentList = getCurrentList();
        Intrinsics.checkNotNullExpressionValue(currentList, "currentList");
        List mutableList = CollectionsKt.toMutableList((Collection) currentList);
        mutableList.add(item);
        submitList(new ArrayList(mutableList), new Runnable() { // from class: co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SimpleRvAdapter.addItem$lambda$4(SimpleRvAdapter.this);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0145, code lost:
    
        if (r0 != null) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0155, code lost:
    
        if (r0 == null) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0159, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0168, code lost:
    
        if (r0.find() == false) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x016a, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0177, code lost:
    
        if (r8.length() <= 23) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x017d, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0180, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0187, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "addItem() called with: pos = [" + r18 + "], item = [" + r19 + ']';
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01a4, code lost:
    
        if (r4 != null) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01a6, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01a8, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0158, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void addItem(int pos, M item) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(item, "item");
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
        String str4 = "addItem() called with: pos = [" + pos + "], item = [" + item + ']';
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
        List<M> currentList = getCurrentList();
        Intrinsics.checkNotNullExpressionValue(currentList, "currentList");
        List mutableList = CollectionsKt.toMutableList((Collection) currentList);
        mutableList.add(pos, item);
        submitList(new ArrayList(mutableList), new Runnable() { // from class: co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SimpleRvAdapter.addItem$lambda$8(SimpleRvAdapter.this);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0149, code lost:
    
        if (r0 != null) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0159, code lost:
    
        if (r0 == null) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x015d, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x016c, code lost:
    
        if (r0.find() == false) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x016e, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0179, code lost:
    
        if (r8.length() <= 23) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x017f, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0182, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0189, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "updateItems() before: \ncurrentList = " + getCurrentList() + "\nitemList    = " + r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01a7, code lost:
    
        if (r4 != null) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01a9, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01ab, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x015c, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void updateItems(final List<? extends M> itemList, final Function1<? super List<? extends M>, Unit> onComplete) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(itemList, "itemList");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
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
        String str4 = "updateItems() before: \ncurrentList = " + getCurrentList() + "\nitemList    = " + itemList;
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
        submitList(new ArrayList(CollectionsKt.toMutableList((Collection) this.sort.invoke(itemList))), new Runnable() { // from class: co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                SimpleRvAdapter.updateItems$lambda$11(SimpleRvAdapter.this, onComplete, itemList);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x014f, code lost:
    
        if (r0 != null) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x015f, code lost:
    
        if (r0 == null) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0163, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0172, code lost:
    
        if (r0.find() == false) goto L142;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0174, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0181, code lost:
    
        if (r8.length() <= 23) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0187, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x018a, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0191, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "updateItem() called with: posPredicate = [" + r18 + "], item = [" + r19 + ']';
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01ae, code lost:
    
        if (r4 != null) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01b0, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01b2, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0162, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void updateItem(Function1<? super M, Boolean> posPredicate, M item, Function1<? super List<? extends M>, Unit> onComplete) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(posPredicate, "posPredicate");
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
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
        int i = 0;
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str4 = "updateItem() called with: posPredicate = [" + posPredicate + "], item = [" + item + ']';
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
        List<M> currentList = getCurrentList();
        Intrinsics.checkNotNullExpressionValue(currentList, "currentList");
        Iterator<M> it = currentList.iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (posPredicate.invoke(it.next()).booleanValue()) {
                break;
            } else {
                i++;
            }
        }
        updateItem(i, (int) item, (Function1<? super List<? extends int>, Unit>) onComplete);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x014a, code lost:
    
        if (r0 != null) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x015a, code lost:
    
        if (r0 == null) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x015e, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x016d, code lost:
    
        if (r0.find() == false) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x016f, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x017c, code lost:
    
        if (r8.length() <= 23) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0182, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0185, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x018c, code lost:
    
        r0 = new java.lang.StringBuilder();
        r4 = "updateItem() called with: position = [" + r18 + "], item = [" + r19 + ']';
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01a9, code lost:
    
        if (r4 != null) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01ab, code lost:
    
        r4 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01ad, code lost:
    
        r0.append(r4);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x015d, code lost:
    
        r8 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void updateItem(int position, M item, Function1<? super List<? extends M>, Unit> onComplete) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
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
        String str4 = "updateItem() called with: position = [" + position + "], item = [" + item + ']';
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
        List<? extends M> list = (List) CollectionsKt.toCollection(new ArrayList(getCurrentList()), new ArrayList());
        list.set(position, item);
        updateItems(list, onComplete);
    }

    public final void removeItem(final Function1<? super M, Boolean> predicate, Function1<? super List<? extends M>, Unit> onComplete) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
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
        String str2 = "removeItem() called with: predicate = [" + predicate + ']';
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
                    String str3 = "removeItem() called with: predicate = [" + predicate + ']';
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
        List<? extends M> list = (List) CollectionsKt.toCollection(new ArrayList(getCurrentList()), new ArrayList());
        CollectionsKt.removeAll((List) list, (Function1) new Function1<M, Boolean>() { // from class: co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter$removeItem$updatedItems$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(M it) {
                Function1<M, Boolean> function1 = predicate;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                return function1.invoke(it);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return invoke((SimpleRvAdapter$removeItem$updatedItems$1$1<M>) obj);
            }
        });
        updateItems(list, onComplete);
    }

    public final void removeItem(M item, Function1<? super List<? extends M>, Unit> onComplete) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
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
        String str2 = "removeItem() called with: item = [" + item + ']';
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
                    String str3 = "removeItem() called with: item = [" + item + ']';
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
        List<? extends M> list = (List) CollectionsKt.toCollection(new ArrayList(getCurrentList()), new ArrayList());
        list.remove(item);
        updateItems(list, onComplete);
    }

    public static final void addItem$lambda$4(SimpleRvAdapter this$0) {
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
        String str2 = "addItem() after: \ncurrentList = " + this$0.getCurrentList();
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
                String str3 = "addItem() after: \ncurrentList = " + this$0.getCurrentList();
                sb2.append(str3 != null ? str3 : "null ");
                sb2.append(' ');
                sb2.append("");
                Log.println(3, str, sb2.toString());
            }
        }
    }

    public static final void addItem$lambda$8(SimpleRvAdapter this$0) {
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
        String str2 = "addItem() after: \ncurrentList = " + this$0.getCurrentList();
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
                String str3 = "addItem() after: \ncurrentList = " + this$0.getCurrentList();
                sb2.append(str3 != null ? str3 : "null ");
                sb2.append(' ');
                sb2.append("");
                Log.println(3, str, sb2.toString());
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x014e, code lost:
    
        if (r0 != null) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x015e, code lost:
    
        if (r0 == null) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0162, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0171, code lost:
    
        if (r0.find() == false) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0173, code lost:
    
        r8 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x017e, code lost:
    
        if (r8.length() <= 23) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0184, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0187, code lost:
    
        r8 = r8.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x018e, code lost:
    
        r0 = new java.lang.StringBuilder();
        r2 = "updateItems() after: \ncurrentList = " + r17.getCurrentList() + "\nitemList    = " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01ac, code lost:
    
        if (r2 != null) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01ae, code lost:
    
        r2 = "null ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01b0, code lost:
    
        r0.append(r2);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r8, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0161, code lost:
    
        r8 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void updateItems$lambda$11(SimpleRvAdapter this$0, Function1 onComplete, List itemList) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(onComplete, "$onComplete");
        Intrinsics.checkNotNullParameter(itemList, "$itemList");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str3 = "N/A";
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
        String str4 = "updateItems() after: \ncurrentList = " + this$0.getCurrentList() + "\nitemList    = " + itemList;
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
                    Class<?> cls2 = this$0.getClass();
                    str2 = cls2 != null ? cls2.getCanonicalName() : str;
                }
            }
        }
        List<M> currentList = this$0.getCurrentList();
        Intrinsics.checkNotNullExpressionValue(currentList, "currentList");
        onComplete.invoke(currentList);
    }
}
