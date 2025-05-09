package com.google.common.collect;

import com.google.common.collect.CollectCollectors;
import java.util.function.BinaryOperator;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes3.dex */
public final /* synthetic */ class CollectCollectors$$ExternalSyntheticLambda8 implements BinaryOperator {
    public static final /* synthetic */ CollectCollectors$$ExternalSyntheticLambda8 INSTANCE = new CollectCollectors$$ExternalSyntheticLambda8();

    private /* synthetic */ CollectCollectors$$ExternalSyntheticLambda8() {
    }

    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return ((CollectCollectors.EnumMapAccumulator) obj).combine((CollectCollectors.EnumMapAccumulator) obj2);
    }
}
