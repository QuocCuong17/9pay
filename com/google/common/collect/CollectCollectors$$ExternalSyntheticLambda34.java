package com.google.common.collect;

import com.google.common.collect.CollectCollectors;
import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes3.dex */
public final /* synthetic */ class CollectCollectors$$ExternalSyntheticLambda34 implements Function {
    public static final /* synthetic */ CollectCollectors$$ExternalSyntheticLambda34 INSTANCE = new CollectCollectors$$ExternalSyntheticLambda34();

    private /* synthetic */ CollectCollectors$$ExternalSyntheticLambda34() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((CollectCollectors.EnumMapAccumulator) obj).toImmutableMap();
    }
}
