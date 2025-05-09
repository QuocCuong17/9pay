package com.otaliastudios.transcoder.internal.audio;

import co.hyperverge.hyperkyc.data.models.state.TransactionState$ModuleData$$ExternalSyntheticBackport0;
import com.facebook.appevents.iap.InAppPurchase$$ExternalSyntheticBackport0;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.nio.ShortBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: chunks.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\b\u0018\u0000  2\u00020\u0001:\u0001 B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J7\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006!"}, d2 = {"Lcom/otaliastudios/transcoder/internal/audio/Chunk;", "", "buffer", "Ljava/nio/ShortBuffer;", "timeUs", "", "timeStretch", "", "release", "Lkotlin/Function0;", "", "(Ljava/nio/ShortBuffer;JDLkotlin/jvm/functions/Function0;)V", "getBuffer", "()Ljava/nio/ShortBuffer;", "getRelease", "()Lkotlin/jvm/functions/Function0;", "getTimeStretch", "()D", "getTimeUs", "()J", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "", "Companion", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
final /* data */ class Chunk {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Chunk Eos;
    private final ShortBuffer buffer;
    private final Function0<Unit> release;
    private final double timeStretch;
    private final long timeUs;

    public static /* synthetic */ Chunk copy$default(Chunk chunk, ShortBuffer shortBuffer, long j, double d, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            shortBuffer = chunk.buffer;
        }
        if ((i & 2) != 0) {
            j = chunk.timeUs;
        }
        long j2 = j;
        if ((i & 4) != 0) {
            d = chunk.timeStretch;
        }
        double d2 = d;
        if ((i & 8) != 0) {
            function0 = chunk.release;
        }
        return chunk.copy(shortBuffer, j2, d2, function0);
    }

    /* renamed from: component1, reason: from getter */
    public final ShortBuffer getBuffer() {
        return this.buffer;
    }

    /* renamed from: component2, reason: from getter */
    public final long getTimeUs() {
        return this.timeUs;
    }

    /* renamed from: component3, reason: from getter */
    public final double getTimeStretch() {
        return this.timeStretch;
    }

    public final Function0<Unit> component4() {
        return this.release;
    }

    public final Chunk copy(ShortBuffer buffer, long timeUs, double timeStretch, Function0<Unit> release) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(release, "release");
        return new Chunk(buffer, timeUs, timeStretch, release);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Chunk)) {
            return false;
        }
        Chunk chunk = (Chunk) other;
        return Intrinsics.areEqual(this.buffer, chunk.buffer) && this.timeUs == chunk.timeUs && Intrinsics.areEqual((Object) Double.valueOf(this.timeStretch), (Object) Double.valueOf(chunk.timeStretch)) && Intrinsics.areEqual(this.release, chunk.release);
    }

    public int hashCode() {
        return (((((this.buffer.hashCode() * 31) + TransactionState$ModuleData$$ExternalSyntheticBackport0.m(this.timeUs)) * 31) + InAppPurchase$$ExternalSyntheticBackport0.m(this.timeStretch)) * 31) + this.release.hashCode();
    }

    public String toString() {
        return "Chunk(buffer=" + this.buffer + ", timeUs=" + this.timeUs + ", timeStretch=" + this.timeStretch + ", release=" + this.release + ')';
    }

    public Chunk(ShortBuffer buffer, long j, double d, Function0<Unit> release) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(release, "release");
        this.buffer = buffer;
        this.timeUs = j;
        this.timeStretch = d;
        this.release = release;
    }

    public final ShortBuffer getBuffer() {
        return this.buffer;
    }

    public final long getTimeUs() {
        return this.timeUs;
    }

    public final double getTimeStretch() {
        return this.timeStretch;
    }

    public final Function0<Unit> getRelease() {
        return this.release;
    }

    /* compiled from: chunks.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/otaliastudios/transcoder/internal/audio/Chunk$Companion;", "", "()V", "Eos", "Lcom/otaliastudios/transcoder/internal/audio/Chunk;", "getEos", "()Lcom/otaliastudios/transcoder/internal/audio/Chunk;", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Chunk getEos() {
            return Chunk.Eos;
        }
    }

    static {
        ShortBuffer allocate = ShortBuffer.allocate(0);
        Intrinsics.checkNotNullExpressionValue(allocate, "allocate(0)");
        Eos = new Chunk(allocate, 0L, 0.0d, new Function0<Unit>() { // from class: com.otaliastudios.transcoder.internal.audio.Chunk$Companion$Eos$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        });
    }
}
