package com.otaliastudios.transcoder.internal.pipeline;

import com.otaliastudios.transcoder.internal.pipeline.Pipeline;
import com.otaliastudios.transcoder.internal.pipeline.State;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.tekartik.sqflite.Constant;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Pipeline.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0002\u0019\u001aB9\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012(\u0010\u0004\u001a$\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u0005¢\u0006\u0002\u0010\tJ\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011JJ\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\r2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\"\u0010\u0015\u001a\u001e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0006\u0010\u0018\u001a\u00020\u0012R0\u0010\u0004\u001a$\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/otaliastudios/transcoder/internal/pipeline/Pipeline;", "", "name", "", "chain", "", "Lcom/otaliastudios/transcoder/internal/pipeline/Step;", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "Lcom/otaliastudios/transcoder/internal/pipeline/AnyStep;", "(Ljava/lang/String;Ljava/util/List;)V", "headIndex", "", "headState", "Lcom/otaliastudios/transcoder/internal/pipeline/State$Ok;", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", Constant.METHOD_EXECUTE, "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "", "executeStep", "previous", "step", "fresh", "", "release", "Builder", "Companion", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class Pipeline {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<Step<Object, Channel, Object, Channel>> chain;
    private int headIndex;
    private State.Ok<Object> headState;
    private final Logger log;

    public /* synthetic */ Pipeline(String str, List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Pipeline(String str, List<? extends Step<Object, Channel, Object, Channel>> list) {
        this.chain = list;
        this.log = new Logger("Pipeline(" + str + ')');
        this.headState = new State.Ok<>(Unit.INSTANCE);
        for (Pair pair : CollectionsKt.reversed(CollectionsKt.zipWithNext(list))) {
            ((Step) pair.component1()).initialize(((Step) pair.component2()).getChannel());
        }
    }

    public final State<Unit> execute() {
        this.log.v("execute(): starting. head=" + this.headIndex + " steps=" + this.chain.size() + " remaining=" + (this.chain.size() - this.headIndex));
        int i = this.headIndex;
        State.Ok<Object> ok = this.headState;
        int i2 = 0;
        for (Object obj : this.chain) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Step<Object, Channel, Object, Channel> step = (Step) obj;
            if (i2 >= i) {
                ok = executeStep(ok, step, i == 0 || i2 != i);
                if (ok == null) {
                    this.log.v("execute(): step " + ((Object) StepKt.getName(step)) + " (#" + i2 + '/' + this.chain.size() + ") is waiting. headState=" + this.headState + " headIndex=" + this.headIndex);
                    return State.Wait.INSTANCE;
                }
                if (ok instanceof State.Eos) {
                    this.log.i("execute(): EOS from " + ((Object) StepKt.getName(step)) + " (#" + i2 + '/' + this.chain.size() + ").");
                    this.headState = ok;
                    this.headIndex = i3;
                }
            }
            i2 = i3;
        }
        if (!this.chain.isEmpty() && !(ok instanceof State.Eos)) {
            return new State.Ok(Unit.INSTANCE);
        }
        return new State.Eos(Unit.INSTANCE);
    }

    public final void release() {
        Iterator<T> it = this.chain.iterator();
        while (it.hasNext()) {
            ((Step) it.next()).release();
        }
    }

    private final State.Ok<Object> executeStep(State.Ok<Object> previous, Step<Object, Channel, Object, Channel> step, boolean fresh) {
        State<Object> step2 = step.step(previous, fresh);
        if (step2 instanceof State.Ok) {
            return (State.Ok) step2;
        }
        if (step2 instanceof State.Retry) {
            return executeStep(previous, step, false);
        }
        if (step2 instanceof State.Wait) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* compiled from: Pipeline.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J/\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0018\b\u0002\u0010\u0007\u001a\u0012\u0012\u000e\u0012\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\n0\t0\bH\u0000¢\u0006\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/otaliastudios/transcoder/internal/pipeline/Pipeline$Companion;", "", "()V", "build", "Lcom/otaliastudios/transcoder/internal/pipeline/Pipeline;", "name", "", "builder", "Lkotlin/Function0;", "Lcom/otaliastudios/transcoder/internal/pipeline/Pipeline$Builder;", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "build$lib_release", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Pipeline build$lib_release$default(Companion companion, String str, Function0 function0, int i, Object obj) {
            if ((i & 2) != 0) {
                function0 = new Function0<Builder<Unit, Channel>>() { // from class: com.otaliastudios.transcoder.internal.pipeline.Pipeline$Companion$build$1
                    /* JADX WARN: Can't rename method to resolve collision */
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // kotlin.jvm.functions.Function0
                    public final Pipeline.Builder<Unit, Channel> invoke() {
                        return new Pipeline.Builder<>(null, 1, 0 == true ? 1 : 0);
                    }
                };
            }
            return companion.build$lib_release(str, function0);
        }

        public final Pipeline build$lib_release(String name, Function0<? extends Builder<?, Channel>> builder) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(builder, "builder");
            return new Pipeline(name, builder.invoke().getSteps$lib_release(), null);
        }
    }

    /* compiled from: Pipeline.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0002B'\b\u0000\u0012\u001e\b\u0002\u0010\u0005\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJI\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\r0\u0000\"\b\b\u0002\u0010\f*\u00020\u0002\"\b\b\u0003\u0010\r*\u00020\u00042\u001e\u0010\u000e\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\r0\u0007H\u0086\u0002R*\u0010\u0005\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00070\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/otaliastudios/transcoder/internal/pipeline/Pipeline$Builder;", "D", "", "C", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "steps", "", "Lcom/otaliastudios/transcoder/internal/pipeline/Step;", "(Ljava/util/List;)V", "getSteps$lib_release", "()Ljava/util/List;", "plus", "NewData", "NewChannel", "step", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Builder<D, C extends Channel> {
        private final List<Step<?, ?, ?, ?>> steps;

        /* JADX WARN: Multi-variable type inference failed */
        public Builder() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder(List<? extends Step<?, ?, ?, ?>> steps) {
            Intrinsics.checkNotNullParameter(steps, "steps");
            this.steps = steps;
        }

        public /* synthetic */ Builder(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? CollectionsKt.emptyList() : list);
        }

        public final List<Step<?, ?, ?, ?>> getSteps$lib_release() {
            return this.steps;
        }

        public final <NewData, NewChannel extends Channel> Builder<NewData, NewChannel> plus(Step<D, C, NewData, NewChannel> step) {
            Intrinsics.checkNotNullParameter(step, "step");
            return new Builder<>(CollectionsKt.plus((Collection<? extends Step<D, C, NewData, NewChannel>>) this.steps, step));
        }
    }
}
