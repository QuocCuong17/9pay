package com.otaliastudios.transcoder.internal.audio;

import java.nio.ShortBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: chunks.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005Jf\u0010\t\u001a\u0002H\n\"\u0004\b\u0000\u0010\n2\u0006\u0010\u000b\u001a\u0002H\n2K\u0010\f\u001aG\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u0002H\n0\r¢\u0006\u0002\u0010\u0016J,\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u001aJ\u0006\u0010\u001b\u001a\u00020\u0018J\u0006\u0010\u001c\u001a\u00020\u001dR\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/otaliastudios/transcoder/internal/audio/ChunkQueue;", "", "sampleRate", "", "channels", "(II)V", "queue", "Lkotlin/collections/ArrayDeque;", "Lcom/otaliastudios/transcoder/internal/audio/Chunk;", "drain", "T", "eos", "action", "Lkotlin/Function3;", "Ljava/nio/ShortBuffer;", "Lkotlin/ParameterName;", "name", "buffer", "", "timeUs", "", "timeStretch", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "enqueue", "", "release", "Lkotlin/Function0;", "enqueueEos", "isEmpty", "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class ChunkQueue {
    private final int channels;
    private final ArrayDeque<Chunk> queue = new ArrayDeque<>();
    private final int sampleRate;

    public ChunkQueue(int i, int i2) {
        this.sampleRate = i;
        this.channels = i2;
    }

    public final boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public final void enqueue(ShortBuffer buffer, long timeUs, double timeStretch, Function0<Unit> release) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(release, "release");
        if (buffer.hasRemaining()) {
            this.queue.addLast(new Chunk(buffer, timeUs, timeStretch, release));
        } else {
            release.invoke();
        }
    }

    public final void enqueueEos() {
        this.queue.addLast(Chunk.INSTANCE.getEos());
    }

    public final <T> T drain(T eos, Function3<? super ShortBuffer, ? super Long, ? super Double, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        Chunk removeFirst = this.queue.removeFirst();
        if (removeFirst == Chunk.INSTANCE.getEos()) {
            return eos;
        }
        int remaining = removeFirst.getBuffer().remaining();
        int limit = removeFirst.getBuffer().limit();
        T invoke = action.invoke(removeFirst.getBuffer(), Long.valueOf(removeFirst.getTimeUs()), Double.valueOf(removeFirst.getTimeStretch()));
        removeFirst.getBuffer().limit(limit);
        if (removeFirst.getBuffer().hasRemaining()) {
            this.queue.addFirst(Chunk.copy$default(removeFirst, null, ConversionsKt.shortsToUs(remaining - removeFirst.getBuffer().remaining(), this.sampleRate, this.channels), 0.0d, null, 13, null));
        } else {
            removeFirst.getRelease().invoke();
        }
        return invoke;
    }
}
