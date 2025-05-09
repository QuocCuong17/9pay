package com.otaliastudios.transcoder.internal.pipeline;

import com.otaliastudios.transcoder.internal.pipeline.Channel;
import com.otaliastudios.transcoder.internal.pipeline.State;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Step.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b`\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0004*\b\b\u0002\u0010\u0005*\u00020\u0002*\b\b\u0003\u0010\u0006*\u00020\u00042\u00020\u0002J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0003H\u0016¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u000bH\u0016J$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00122\u0006\u0010\u0013\u001a\u00020\u0014H&R\u0012\u0010\u0007\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/otaliastudios/transcoder/internal/pipeline/Step;", "Input", "", "InputChannel", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "Output", "OutputChannel", "channel", "getChannel", "()Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "initialize", "", "next", "(Lcom/otaliastudios/transcoder/internal/pipeline/Channel;)V", "release", "step", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "state", "Lcom/otaliastudios/transcoder/internal/pipeline/State$Ok;", "fresh", "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public interface Step<Input, InputChannel extends Channel, Output, OutputChannel extends Channel> {

    /* compiled from: Step.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static <Input, InputChannel extends Channel, Output, OutputChannel extends Channel> void initialize(Step<Input, InputChannel, Output, OutputChannel> step, OutputChannel next) {
            Intrinsics.checkNotNullParameter(step, "this");
            Intrinsics.checkNotNullParameter(next, "next");
        }

        public static <Input, InputChannel extends Channel, Output, OutputChannel extends Channel> void release(Step<Input, InputChannel, Output, OutputChannel> step) {
            Intrinsics.checkNotNullParameter(step, "this");
        }
    }

    InputChannel getChannel();

    void initialize(OutputChannel next);

    void release();

    State<Output> step(State.Ok<Input> state, boolean fresh);
}
