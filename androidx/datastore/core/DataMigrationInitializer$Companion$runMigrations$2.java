package androidx.datastore.core;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: DataMigrationInitializer.kt */
@Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0003\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u0001H\u008a@"}, d2 = {"<anonymous>", "T", "startingData"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "androidx.datastore.core.DataMigrationInitializer$Companion$runMigrations$2", f = "DataMigrationInitializer.kt", i = {0, 0}, l = {44, 46}, m = "invokeSuspend", n = {"migration", "data"}, s = {"L$2", "L$3"})
/* loaded from: classes.dex */
final class DataMigrationInitializer$Companion$runMigrations$2<T> extends SuspendLambda implements Function2<T, Continuation<? super T>, Object> {
    final /* synthetic */ List<Function1<Continuation<? super Unit>, Object>> $cleanUps;
    final /* synthetic */ List<DataMigration<T>> $migrations;
    /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DataMigrationInitializer$Companion$runMigrations$2(List<? extends DataMigration<T>> list, List<Function1<Continuation<? super Unit>, Object>> list2, Continuation<? super DataMigrationInitializer$Companion$runMigrations$2> continuation) {
        super(2, continuation);
        this.$migrations = list;
        this.$cleanUps = list2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DataMigrationInitializer$Companion$runMigrations$2 dataMigrationInitializer$Companion$runMigrations$2 = new DataMigrationInitializer$Companion$runMigrations$2(this.$migrations, this.$cleanUps, continuation);
        dataMigrationInitializer$Companion$runMigrations$2.L$0 = obj;
        return dataMigrationInitializer$Companion$runMigrations$2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((DataMigrationInitializer$Companion$runMigrations$2<T>) obj, (Continuation<? super DataMigrationInitializer$Companion$runMigrations$2<T>>) obj2);
    }

    public final Object invoke(T t, Continuation<? super T> continuation) {
        return ((DataMigrationInitializer$Companion$runMigrations$2) create(t, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0093 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0050  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        List<Function1<Continuation<? super Unit>, Object>> list;
        Iterator<T> it;
        DataMigrationInitializer$Companion$runMigrations$2<T> dataMigrationInitializer$Companion$runMigrations$2;
        DataMigrationInitializer$Companion$runMigrations$2<T> dataMigrationInitializer$Companion$runMigrations$22;
        DataMigration dataMigration;
        Iterator<T> it2;
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            obj = this.L$0;
            List<DataMigration<T>> list2 = this.$migrations;
            list = this.$cleanUps;
            it = list2.iterator();
            dataMigrationInitializer$Companion$runMigrations$2 = this;
            if (!it.hasNext()) {
            }
        } else if (i == 1) {
            obj2 = this.L$3;
            DataMigration dataMigration2 = (DataMigration) this.L$2;
            it2 = (Iterator) this.L$1;
            List<Function1<Continuation<? super Unit>, Object>> list3 = (List) this.L$0;
            ResultKt.throwOnFailure(obj);
            dataMigrationInitializer$Companion$runMigrations$22 = this;
            dataMigration = dataMigration2;
            list = list3;
            if (((Boolean) obj).booleanValue()) {
            }
            dataMigrationInitializer$Companion$runMigrations$2 = dataMigrationInitializer$Companion$runMigrations$22;
            if (!it.hasNext()) {
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (Iterator) this.L$1;
            list = (List) this.L$0;
            ResultKt.throwOnFailure(obj);
            dataMigrationInitializer$Companion$runMigrations$22 = this;
            dataMigrationInitializer$Companion$runMigrations$2 = dataMigrationInitializer$Companion$runMigrations$22;
            if (!it.hasNext()) {
                DataMigration dataMigration3 = (DataMigration) it.next();
                dataMigrationInitializer$Companion$runMigrations$2.L$0 = list;
                dataMigrationInitializer$Companion$runMigrations$2.L$1 = it;
                dataMigrationInitializer$Companion$runMigrations$2.L$2 = dataMigration3;
                dataMigrationInitializer$Companion$runMigrations$2.L$3 = obj;
                dataMigrationInitializer$Companion$runMigrations$2.label = 1;
                Object shouldMigrate = dataMigration3.shouldMigrate(obj, dataMigrationInitializer$Companion$runMigrations$2);
                if (shouldMigrate == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Iterator<T> it3 = it;
                obj2 = obj;
                obj = shouldMigrate;
                dataMigrationInitializer$Companion$runMigrations$22 = dataMigrationInitializer$Companion$runMigrations$2;
                it2 = it3;
                dataMigration = dataMigration3;
                if (((Boolean) obj).booleanValue()) {
                    obj = obj2;
                    it = it2;
                } else {
                    list.add(new DataMigrationInitializer$Companion$runMigrations$2$1$1(dataMigration, null));
                    dataMigrationInitializer$Companion$runMigrations$22.L$0 = list;
                    dataMigrationInitializer$Companion$runMigrations$22.L$1 = it2;
                    dataMigrationInitializer$Companion$runMigrations$22.L$2 = null;
                    dataMigrationInitializer$Companion$runMigrations$22.L$3 = null;
                    dataMigrationInitializer$Companion$runMigrations$22.label = 2;
                    obj = dataMigration.migrate(obj2, dataMigrationInitializer$Companion$runMigrations$22);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    it = it2;
                }
                dataMigrationInitializer$Companion$runMigrations$2 = dataMigrationInitializer$Companion$runMigrations$22;
                if (!it.hasNext()) {
                    return obj;
                }
            }
        }
    }
}
