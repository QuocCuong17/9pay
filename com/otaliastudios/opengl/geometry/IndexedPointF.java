package com.otaliastudios.opengl.geometry;

import android.graphics.PointF;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;

/* compiled from: IndexedPointF.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/otaliastudios/opengl/geometry/IndexedPointF;", "Landroid/graphics/PointF;", "Lcom/otaliastudios/opengl/geometry/PointF;", FirebaseAnalytics.Param.INDEX, "", ViewHierarchyNode.JsonKeys.X, "", ViewHierarchyNode.JsonKeys.Y, "(IFF)V", "getIndex", "()I", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class IndexedPointF extends PointF {
    private final int index;

    public IndexedPointF(int i, float f, float f2) {
        super(f, f2);
        this.index = i;
    }

    public final int getIndex() {
        return this.index;
    }
}
