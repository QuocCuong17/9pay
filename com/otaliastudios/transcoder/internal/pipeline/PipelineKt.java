package com.otaliastudios.transcoder.internal.pipeline;

import com.otaliastudios.transcoder.internal.pipeline.Pipeline;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Pipeline.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001ay\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0004*\u00020\u0005\"\b\b\u0001\u0010\u0006*\u00020\u0007\"\b\b\u0002\u0010\u0002*\u00020\u0005\"\b\b\u0003\u0010\u0003*\u00020\u0007*\u001a\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00060\b2\u001e\u0010\n\u001a\u001a\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\bH\u0080\u0002*<\b\u0002\u0010\u000b\"\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00070\b2\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00070\b¨\u0006\f"}, d2 = {"plus", "Lcom/otaliastudios/transcoder/internal/pipeline/Pipeline$Builder;", "NewData", "NewChannel", "CurrData", "", "CurrChannel", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "Lcom/otaliastudios/transcoder/internal/pipeline/Step;", "", "other", "AnyStep", "lib_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class PipelineKt {
    public static final <CurrData, CurrChannel extends Channel, NewData, NewChannel extends Channel> Pipeline.Builder<NewData, NewChannel> plus(Step<Unit, Channel, CurrData, CurrChannel> step, Step<CurrData, CurrChannel, NewData, NewChannel> other) {
        Intrinsics.checkNotNullParameter(step, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return new Pipeline.Builder(CollectionsKt.listOf(step)).plus(other);
    }
}
