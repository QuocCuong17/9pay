package com.otaliastudios.opengl.geometry;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IndexedSegmentF.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B5\u0012\u0006\u0010\u0002\u001a\u00020\u0006\u0012\u0006\u0010\u0004\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b¢\u0006\u0002\u0010\fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0006J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0001H\u0016R\u0011\u0010\u0002\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/otaliastudios/opengl/geometry/IndexedSegmentF;", "Lcom/otaliastudios/opengl/geometry/SegmentF;", CmcdData.Factory.OBJECT_TYPE_INIT_SEGMENT, "Lcom/otaliastudios/opengl/geometry/IndexedPointF;", "j", "(Lcom/otaliastudios/opengl/geometry/IndexedPointF;Lcom/otaliastudios/opengl/geometry/IndexedPointF;)V", "", "ix", "", "iy", "jx", "jy", "(IIFFFF)V", "getI", "()I", "getJ", "hasIndex", "", FirebaseAnalytics.Param.INDEX, "intersects", "other", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class IndexedSegmentF extends SegmentF {
    private final int i;
    private final int j;

    public final int getI() {
        return this.i;
    }

    public final int getJ() {
        return this.j;
    }

    public IndexedSegmentF(int i, int i2, float f, float f2, float f3, float f4) {
        super(f, f2, f3, f4);
        this.i = i;
        this.j = i2;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public IndexedSegmentF(IndexedPointF i, IndexedPointF j) {
        this(i.getIndex(), j.getIndex(), i.x, i.y, j.x, j.y);
        Intrinsics.checkNotNullParameter(i, "i");
        Intrinsics.checkNotNullParameter(j, "j");
    }

    @Override // com.otaliastudios.opengl.geometry.SegmentF
    public boolean intersects(SegmentF other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (other instanceof IndexedSegmentF) {
            IndexedSegmentF indexedSegmentF = (IndexedSegmentF) other;
            if (indexedSegmentF.hasIndex(this.i) && indexedSegmentF.hasIndex(this.j)) {
                return true;
            }
            if (indexedSegmentF.hasIndex(this.i) || indexedSegmentF.hasIndex(this.j)) {
                return false;
            }
        }
        return super.intersects(other);
    }

    public final boolean hasIndex(int index) {
        return index == this.i || index == this.j;
    }
}
