package com.otaliastudios.transcoder.internal.pipeline;

import com.otaliastudios.transcoder.internal.pipeline.Channel;
import com.otaliastudios.transcoder.internal.pipeline.Step;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: steps.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\b \u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0004*\b\b\u0002\u0010\u0005*\u00020\u0002*\b\b\u0003\u0010\u0006*\u00020\u00042\u001a\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u00060\u0007B\u0005¢\u0006\u0002\u0010\bJ\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00028\u0003H\u0016¢\u0006\u0002\u0010\u0010R \u0010\n\u001a\u00028\u00032\u0006\u0010\t\u001a\u00028\u0003@BX\u0084.¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/otaliastudios/transcoder/internal/pipeline/BaseStep;", "Input", "", "InputChannel", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "Output", "OutputChannel", "Lcom/otaliastudios/transcoder/internal/pipeline/Step;", "()V", "<set-?>", "next", "getNext", "()Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "initialize", "", "(Lcom/otaliastudios/transcoder/internal/pipeline/Channel;)V", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public abstract class BaseStep<Input, InputChannel extends Channel, Output, OutputChannel extends Channel> implements Step<Input, InputChannel, Output, OutputChannel> {
    private OutputChannel next;

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public void release() {
        Step.DefaultImpls.release(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final OutputChannel getNext() {
        OutputChannel outputchannel = this.next;
        if (outputchannel != null) {
            return outputchannel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("next");
        return null;
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public void initialize(OutputChannel next) {
        Intrinsics.checkNotNullParameter(next, "next");
        this.next = next;
    }
}
