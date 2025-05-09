package com.otaliastudios.opengl.core;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlBindable.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a-\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\u0010\u0007\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006¨\u0006\b"}, d2 = {"use", "", "bindables", "", "Lcom/otaliastudios/opengl/core/GlBindable;", "block", "Lkotlin/Function0;", "([Lcom/otaliastudios/opengl/core/GlBindable;Lkotlin/jvm/functions/Function0;)V", "library_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class GlBindableKt {
    public static final void use(GlBindable glBindable, Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(glBindable, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        glBindable.bind();
        block.invoke();
        glBindable.unbind();
    }

    public static final void use(GlBindable[] bindables, Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(bindables, "bindables");
        Intrinsics.checkNotNullParameter(block, "block");
        for (GlBindable glBindable : bindables) {
            glBindable.bind();
        }
        block.invoke();
        for (GlBindable glBindable2 : bindables) {
            glBindable2.unbind();
        }
    }
}
