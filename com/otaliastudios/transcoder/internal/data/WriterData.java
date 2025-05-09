package com.otaliastudios.transcoder.internal.data;

import co.hyperverge.hyperkyc.data.models.state.TransactionState$ModuleData$$ExternalSyntheticBackport0;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Writer.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J7\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/otaliastudios/transcoder/internal/data/WriterData;", "", "buffer", "Ljava/nio/ByteBuffer;", "timeUs", "", "flags", "", "release", "Lkotlin/Function0;", "", "(Ljava/nio/ByteBuffer;JILkotlin/jvm/functions/Function0;)V", "getBuffer", "()Ljava/nio/ByteBuffer;", "getFlags", "()I", "getRelease", "()Lkotlin/jvm/functions/Function0;", "getTimeUs", "()J", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class WriterData {
    private final ByteBuffer buffer;
    private final int flags;
    private final Function0<Unit> release;
    private final long timeUs;

    public static /* synthetic */ WriterData copy$default(WriterData writerData, ByteBuffer byteBuffer, long j, int i, Function0 function0, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            byteBuffer = writerData.buffer;
        }
        if ((i2 & 2) != 0) {
            j = writerData.timeUs;
        }
        long j2 = j;
        if ((i2 & 4) != 0) {
            i = writerData.flags;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            function0 = writerData.release;
        }
        return writerData.copy(byteBuffer, j2, i3, function0);
    }

    /* renamed from: component1, reason: from getter */
    public final ByteBuffer getBuffer() {
        return this.buffer;
    }

    /* renamed from: component2, reason: from getter */
    public final long getTimeUs() {
        return this.timeUs;
    }

    /* renamed from: component3, reason: from getter */
    public final int getFlags() {
        return this.flags;
    }

    public final Function0<Unit> component4() {
        return this.release;
    }

    public final WriterData copy(ByteBuffer buffer, long timeUs, int flags, Function0<Unit> release) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(release, "release");
        return new WriterData(buffer, timeUs, flags, release);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WriterData)) {
            return false;
        }
        WriterData writerData = (WriterData) other;
        return Intrinsics.areEqual(this.buffer, writerData.buffer) && this.timeUs == writerData.timeUs && this.flags == writerData.flags && Intrinsics.areEqual(this.release, writerData.release);
    }

    public int hashCode() {
        return (((((this.buffer.hashCode() * 31) + TransactionState$ModuleData$$ExternalSyntheticBackport0.m(this.timeUs)) * 31) + this.flags) * 31) + this.release.hashCode();
    }

    public String toString() {
        return "WriterData(buffer=" + this.buffer + ", timeUs=" + this.timeUs + ", flags=" + this.flags + ", release=" + this.release + ')';
    }

    public WriterData(ByteBuffer buffer, long j, int i, Function0<Unit> release) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(release, "release");
        this.buffer = buffer;
        this.timeUs = j;
        this.flags = i;
        this.release = release;
    }

    public final ByteBuffer getBuffer() {
        return this.buffer;
    }

    public final long getTimeUs() {
        return this.timeUs;
    }

    public final int getFlags() {
        return this.flags;
    }

    public final Function0<Unit> getRelease() {
        return this.release;
    }
}
