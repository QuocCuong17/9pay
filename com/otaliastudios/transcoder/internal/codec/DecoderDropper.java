package com.otaliastudios.transcoder.internal.codec;

import com.otaliastudios.transcoder.internal.utils.Logger;
import com.tekartik.sqflite.Constant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;

/* compiled from: DecoderDropper.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0003H\u0002J\u0016\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0003J\u0015\u0010\u0019\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0017\u001a\u00020\b¢\u0006\u0002\u0010\u001aR\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0012\u0010\r\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/otaliastudios/transcoder/internal/codec/DecoderDropper;", "", "continuous", "", "(Z)V", "closedDeltas", "", "Lkotlin/ranges/LongRange;", "", "closedRanges", "", "firstInputUs", "Ljava/lang/Long;", "firstOutputUs", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "pendingRange", Constant.METHOD_DEBUG, "", "message", "", "important", "input", "timeUs", "render", "output", "(J)Ljava/lang/Long;", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class DecoderDropper {
    private final boolean continuous;
    private Long firstInputUs;
    private Long firstOutputUs;
    private LongRange pendingRange;
    private final Logger log = new Logger("DecoderDropper");
    private final Map<LongRange, Long> closedDeltas = new LinkedHashMap();
    private final List<LongRange> closedRanges = new ArrayList();

    private final void debug(String message, boolean important) {
    }

    public DecoderDropper(boolean z) {
        this.continuous = z;
    }

    static /* synthetic */ void debug$default(DecoderDropper decoderDropper, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        decoderDropper.debug(str, z);
    }

    public final void input(long timeUs, boolean render) {
        long j;
        if (this.firstInputUs == null) {
            this.firstInputUs = Long.valueOf(timeUs);
        }
        if (render) {
            debug$default(this, Intrinsics.stringPlus("INPUT: inputUs=", Long.valueOf(timeUs)), false, 2, null);
            if (this.pendingRange == null) {
                this.pendingRange = new LongRange(timeUs, Long.MAX_VALUE);
                return;
            }
            LongRange longRange = this.pendingRange;
            Intrinsics.checkNotNull(longRange);
            this.pendingRange = new LongRange(longRange.getFirst(), timeUs);
            return;
        }
        debug$default(this, Intrinsics.stringPlus("INPUT: Got SKIPPING input! inputUs=", Long.valueOf(timeUs)), false, 2, null);
        LongRange longRange2 = this.pendingRange;
        if (longRange2 != null) {
            Intrinsics.checkNotNull(longRange2);
            if (longRange2.getLast() != Long.MAX_VALUE) {
                List<LongRange> list = this.closedRanges;
                LongRange longRange3 = this.pendingRange;
                Intrinsics.checkNotNull(longRange3);
                list.add(longRange3);
                Map<LongRange, Long> map = this.closedDeltas;
                LongRange longRange4 = this.pendingRange;
                Intrinsics.checkNotNull(longRange4);
                if (this.closedRanges.size() >= 2) {
                    LongRange longRange5 = this.pendingRange;
                    Intrinsics.checkNotNull(longRange5);
                    j = longRange5.getFirst() - this.closedRanges.get(CollectionsKt.getLastIndex(r9) - 1).getLast();
                } else {
                    j = 0;
                }
                map.put(longRange4, Long.valueOf(j));
            }
        }
        this.pendingRange = null;
    }

    public final Long output(long timeUs) {
        if (this.firstOutputUs == null) {
            this.firstOutputUs = Long.valueOf(timeUs);
        }
        Long l = this.firstInputUs;
        Intrinsics.checkNotNull(l);
        long longValue = l.longValue();
        Long l2 = this.firstOutputUs;
        Intrinsics.checkNotNull(l2);
        long longValue2 = longValue + (timeUs - l2.longValue());
        long j = 0;
        for (LongRange longRange : this.closedRanges) {
            Long l3 = this.closedDeltas.get(longRange);
            Intrinsics.checkNotNull(l3);
            j += l3.longValue();
            if (longRange.contains(longValue2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("OUTPUT: Rendering! outputTimeUs=");
                sb.append(timeUs);
                sb.append(" newOutputTimeUs=");
                long j2 = timeUs - j;
                sb.append(j2);
                sb.append(" deltaUs=");
                sb.append(j);
                debug$default(this, sb.toString(), false, 2, null);
                return this.continuous ? Long.valueOf(j2) : Long.valueOf(timeUs);
            }
        }
        LongRange longRange2 = this.pendingRange;
        if (longRange2 != null) {
            Intrinsics.checkNotNull(longRange2);
            if (longRange2.contains(longValue2)) {
                if (!this.closedRanges.isEmpty()) {
                    LongRange longRange3 = this.pendingRange;
                    Intrinsics.checkNotNull(longRange3);
                    j += longRange3.getFirst() - ((LongRange) CollectionsKt.last((List) this.closedRanges)).getLast();
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("OUTPUT: Rendering! outputTimeUs=");
                sb2.append(timeUs);
                sb2.append(" newOutputTimeUs=");
                long j3 = timeUs - j;
                sb2.append(j3);
                sb2.append(" deltaUs=");
                sb2.append(j);
                debug$default(this, sb2.toString(), false, 2, null);
                return this.continuous ? Long.valueOf(j3) : Long.valueOf(timeUs);
            }
        }
        debug(Intrinsics.stringPlus("OUTPUT: SKIPPING! outputTimeUs=", Long.valueOf(timeUs)), true);
        return null;
    }
}
