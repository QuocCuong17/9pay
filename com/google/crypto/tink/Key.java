package com.google.crypto.tink;

import com.google.errorprone.annotations.Immutable;
import javax.annotation.Nullable;

@Immutable
/* loaded from: classes4.dex */
public abstract class Key {
    public abstract boolean equalsKey(Key other);

    @Nullable
    public abstract Integer getIdRequirementOrNull();

    public abstract Parameters getParameters();
}
