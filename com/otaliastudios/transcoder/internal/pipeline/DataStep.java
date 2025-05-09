package com.otaliastudios.transcoder.internal.pipeline;

import com.otaliastudios.transcoder.internal.pipeline.Channel;
import com.otaliastudios.transcoder.internal.pipeline.Step;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: steps.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\b \u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u001a\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0005B\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u000bR\u001c\u0010\u0007\u001a\u00028\u0001X\u0096.¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/otaliastudios/transcoder/internal/pipeline/DataStep;", "D", "", "C", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "Lcom/otaliastudios/transcoder/internal/pipeline/Step;", "()V", "channel", "getChannel", "()Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "setChannel", "(Lcom/otaliastudios/transcoder/internal/pipeline/Channel;)V", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "initialize", "", "next", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public abstract class DataStep<D, C extends Channel> implements Step<D, C, D, C> {
    public C channel;

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public void release() {
        Step.DefaultImpls.release(this);
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public C getChannel() {
        C c = this.channel;
        if (c != null) {
            return c;
        }
        Intrinsics.throwUninitializedPropertyAccessException("channel");
        return null;
    }

    public void setChannel(C c) {
        Intrinsics.checkNotNullParameter(c, "<set-?>");
        this.channel = c;
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public void initialize(C next) {
        Intrinsics.checkNotNullParameter(next, "next");
        setChannel(next);
    }
}
