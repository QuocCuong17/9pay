package com.otaliastudios.transcoder.internal.codec;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Decoder.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\b\u0010\u0018\u00002\u00020\u0001B8\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\u0002\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR,\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/otaliastudios/transcoder/internal/codec/DecoderData;", "", "buffer", "Ljava/nio/ByteBuffer;", "timeUs", "", "release", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "render", "", "(Ljava/nio/ByteBuffer;JLkotlin/jvm/functions/Function1;)V", "getBuffer", "()Ljava/nio/ByteBuffer;", "getRelease", "()Lkotlin/jvm/functions/Function1;", "getTimeUs", "()J", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public class DecoderData {
    private final ByteBuffer buffer;
    private final Function1<Boolean, Unit> release;
    private final long timeUs;

    /* JADX WARN: Multi-variable type inference failed */
    public DecoderData(ByteBuffer buffer, long j, Function1<? super Boolean, Unit> release) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(release, "release");
        this.buffer = buffer;
        this.timeUs = j;
        this.release = release;
    }

    public final ByteBuffer getBuffer() {
        return this.buffer;
    }

    public final long getTimeUs() {
        return this.timeUs;
    }

    public final Function1<Boolean, Unit> getRelease() {
        return this.release;
    }
}
