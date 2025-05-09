package com.google.common.collect;

import com.android.tools.r8.annotations.SynthesizedClass;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public interface ListMultimap<K, V> extends Multimap<K, V> {
    Map<K, Collection<V>> asMap();

    boolean equals(@CheckForNull Object obj);

    @Override // com.google.common.collect.Multimap
    List<V> get(@ParametricNullness K key);

    @Override // com.google.common.collect.Multimap
    List<V> removeAll(@CheckForNull Object key);

    @Override // com.google.common.collect.Multimap
    List<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values);

    @SynthesizedClass(kind = "$-CC")
    /* renamed from: com.google.common.collect.ListMultimap$-CC, reason: invalid class name */
    /* loaded from: classes3.dex */
    public final /* synthetic */ class CC<K, V> {
    }
}
