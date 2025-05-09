package com.otaliastudios.transcoder.internal.pipeline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: Step.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\"*\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"name", "", "Lcom/otaliastudios/transcoder/internal/pipeline/Step;", "getName", "(Lcom/otaliastudios/transcoder/internal/pipeline/Step;)Ljava/lang/String;", "lib_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class StepKt {
    public static final String getName(Step<?, ?, ?, ?> step) {
        Intrinsics.checkNotNullParameter(step, "<this>");
        return Reflection.getOrCreateKotlinClass(step.getClass()).getSimpleName();
    }
}
