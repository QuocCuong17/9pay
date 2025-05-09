package com.otaliastudios.opengl.draw;

import android.graphics.PointF;
import android.opengl.GLES20;
import com.otaliastudios.opengl.core.Egloo;
import com.otaliastudios.opengl.geometry.IndexedSegmentF;
import com.otaliastudios.opengl.internal.GlKt;
import com.otaliastudios.opengl.types.BuffersJvmKt;
import com.otaliastudios.opengl.types.BuffersKt;
import io.sentry.protocol.ViewHierarchyNode;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Gl2dMesh.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u000eH\u0016J\b\u0010\u0013\u001a\u00020\u000eH\u0016J\u0018\u0010\u0014\u001a\u00020\u000e2\u0010\u0010\u0015\u001a\f\u0012\b\u0012\u00060\u0016j\u0002`\u00170\u0010J\"\u0010\u0014\u001a\u00020\u000e2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00102\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0010R\u001e\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/otaliastudios/opengl/draw/Gl2dMesh;", "Lcom/otaliastudios/opengl/draw/Gl2dDrawable;", "()V", "vertexArray", "Ljava/nio/FloatBuffer;", "Lcom/otaliastudios/opengl/types/FloatBuffer;", "getVertexArray", "()Ljava/nio/FloatBuffer;", "setVertexArray", "(Ljava/nio/FloatBuffer;)V", "vertexIndices", "Ljava/nio/ByteBuffer;", "Lcom/otaliastudios/opengl/types/ByteBuffer;", "computeIndicesFromIndexedSegments", "", "segments", "", "Lcom/otaliastudios/opengl/geometry/IndexedSegmentF;", "draw", "release", "setPoints", "points", "Landroid/graphics/PointF;", "Lcom/otaliastudios/opengl/geometry/PointF;", ViewHierarchyNode.JsonKeys.X, "", ViewHierarchyNode.JsonKeys.Y, "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public class Gl2dMesh extends Gl2dDrawable {
    private FloatBuffer vertexArray = BuffersJvmKt.floatBuffer(6);
    private ByteBuffer vertexIndices;

    @Override // com.otaliastudios.opengl.draw.GlDrawable
    public FloatBuffer getVertexArray() {
        return this.vertexArray;
    }

    @Override // com.otaliastudios.opengl.draw.GlDrawable
    public void setVertexArray(FloatBuffer floatBuffer) {
        Intrinsics.checkNotNullParameter(floatBuffer, "<set-?>");
        this.vertexArray = floatBuffer;
    }

    public final void setPoints(List<? extends PointF> points) {
        Intrinsics.checkNotNullParameter(points, "points");
        List<? extends PointF> list = points;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Float.valueOf(((PointF) it.next()).x));
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList3.add(Float.valueOf(((PointF) it2.next()).y));
        }
        setPoints(arrayList2, arrayList3);
    }

    public final void setPoints(List<Float> x, List<Float> y) {
        boolean z;
        List<Float> x2 = x;
        Intrinsics.checkNotNullParameter(x2, "x");
        Intrinsics.checkNotNullParameter(y, "y");
        if (x.size() != y.size()) {
            throw new IllegalArgumentException("x.size != y.size");
        }
        int size = x.size();
        int i = size * 2;
        if (getVertexArray().capacity() < i) {
            BuffersKt.dispose(getVertexArray());
            setVertexArray(BuffersJvmKt.floatBuffer(i));
        } else {
            getVertexArray().clear();
        }
        ArrayList<IndexedSegmentF> arrayList = new ArrayList();
        if (size > 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                float floatValue = x2.get(i2).floatValue();
                float floatValue2 = y.get(i2).floatValue();
                getVertexArray().put(floatValue);
                getVertexArray().put(floatValue2);
                if (i3 < size) {
                    int i4 = i3;
                    while (true) {
                        int i5 = i4 + 1;
                        float f = floatValue2;
                        arrayList.add(new IndexedSegmentF(i2, i4, floatValue, floatValue2, x2.get(i4).floatValue(), y.get(i4).floatValue()));
                        if (i5 >= size) {
                            break;
                        }
                        i4 = i5;
                        floatValue2 = f;
                        x2 = x;
                    }
                }
                if (i3 >= size) {
                    break;
                }
                x2 = x;
                i2 = i3;
            }
        }
        getVertexArray().flip();
        notifyVertexArrayChange();
        if (arrayList.size() > 1) {
            CollectionsKt.sortWith(arrayList, new Comparator<T>() { // from class: com.otaliastudios.opengl.draw.Gl2dMesh$setPoints$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Float.valueOf(((IndexedSegmentF) t).getLength()), Float.valueOf(((IndexedSegmentF) t2).getLength()));
                }
            });
        }
        ArrayList arrayList2 = new ArrayList();
        for (IndexedSegmentF indexedSegmentF : arrayList) {
            ArrayList arrayList3 = arrayList2;
            if (!(arrayList3 instanceof Collection) || !arrayList3.isEmpty()) {
                Iterator it = arrayList3.iterator();
                while (it.hasNext()) {
                    if (((IndexedSegmentF) it.next()).intersects(indexedSegmentF)) {
                        z = false;
                        break;
                    }
                }
            }
            z = true;
            if (z) {
                arrayList2.add(indexedSegmentF);
            }
        }
        computeIndicesFromIndexedSegments(arrayList2);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00cb A[LOOP:1: B:7:0x0025->B:40:0x00cb, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ce A[EDGE_INSN: B:41:0x00ce->B:45:0x00ce BREAK  A[LOOP:1: B:7:0x0025->B:40:0x00cb], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void computeIndicesFromIndexedSegments(List<IndexedSegmentF> segments) {
        int i;
        float ix;
        float iy;
        int size;
        ArrayList arrayList = new ArrayList();
        int size2 = segments.size() - 1;
        if (size2 >= 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                IndexedSegmentF indexedSegmentF = segments.get(i2);
                int size3 = segments.size();
                if (i3 < size3) {
                    int i4 = i3;
                    boolean z = false;
                    boolean z2 = false;
                    while (true) {
                        int i5 = i4 + 1;
                        if (z && z2) {
                            break;
                        }
                        IndexedSegmentF indexedSegmentF2 = segments.get(i4);
                        if (indexedSegmentF.hasIndex(indexedSegmentF2.getI())) {
                            i = indexedSegmentF2.getJ();
                            ix = indexedSegmentF2.getJx();
                            iy = indexedSegmentF2.getJy();
                        } else {
                            if (indexedSegmentF.hasIndex(indexedSegmentF2.getJ())) {
                                i = indexedSegmentF2.getI();
                                ix = indexedSegmentF2.getIx();
                                iy = indexedSegmentF2.getIy();
                            }
                            if (i5 < size3) {
                                break;
                            } else {
                                i4 = i5;
                            }
                        }
                        int orientation = indexedSegmentF.orientation(ix, iy);
                        if (orientation != 0 && ((orientation <= 0 || !z) && ((orientation >= 0 || !z2) && i5 < (size = segments.size())))) {
                            int i6 = i5;
                            while (true) {
                                int i7 = i6 + 1;
                                IndexedSegmentF indexedSegmentF3 = segments.get(i6);
                                if (!indexedSegmentF3.hasIndex(i) || (!indexedSegmentF3.hasIndex(indexedSegmentF.getI()) && !indexedSegmentF3.hasIndex(indexedSegmentF.getJ()))) {
                                    if (i7 >= size) {
                                        break;
                                    } else {
                                        i6 = i7;
                                    }
                                }
                            }
                            arrayList.add(Byte.valueOf((byte) indexedSegmentF.getI()));
                            arrayList.add(Byte.valueOf((byte) indexedSegmentF.getJ()));
                            arrayList.add(Byte.valueOf((byte) i));
                            if (orientation > 0) {
                                z = true;
                            }
                            if (orientation < 0) {
                                z2 = true;
                            }
                        }
                        if (i5 < size3) {
                        }
                    }
                }
                if (i3 > size2) {
                    break;
                } else {
                    i2 = i3;
                }
            }
        }
        ByteBuffer byteBuffer = this.vertexIndices;
        if (byteBuffer != null) {
            BuffersKt.dispose(byteBuffer);
        }
        ByteBuffer byteBuffer2 = BuffersJvmKt.byteBuffer(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            byteBuffer2.put(((Number) it.next()).byteValue());
        }
        byteBuffer2.clear();
        Unit unit = Unit.INSTANCE;
        this.vertexIndices = byteBuffer2;
    }

    @Override // com.otaliastudios.opengl.draw.GlDrawable
    public void draw() {
        ByteBuffer byteBuffer = this.vertexIndices;
        if (byteBuffer == null) {
            return;
        }
        Egloo.checkGlError("glDrawElements start");
        GLES20.glDrawElements(GlKt.getGL_TRIANGLES(), byteBuffer.limit(), GlKt.getGL_UNSIGNED_BYTE(), byteBuffer);
        Egloo.checkGlError("glDrawElements end");
    }

    @Override // com.otaliastudios.opengl.draw.GlDrawable
    public void release() {
        super.release();
        ByteBuffer byteBuffer = this.vertexIndices;
        if (byteBuffer == null) {
            return;
        }
        BuffersKt.dispose(byteBuffer);
    }
}
