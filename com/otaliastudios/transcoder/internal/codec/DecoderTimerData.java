package com.otaliastudios.transcoder.internal.codec;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DecoderTimer.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001BH\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012!\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\n¢\u0006\u0002\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/otaliastudios/transcoder/internal/codec/DecoderTimerData;", "Lcom/otaliastudios/transcoder/internal/codec/DecoderData;", "buffer", "Ljava/nio/ByteBuffer;", "rawTimeUs", "", "timeUs", "timeStretch", "", "release", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "render", "", "(Ljava/nio/ByteBuffer;JJDLkotlin/jvm/functions/Function1;)V", "getRawTimeUs", "()J", "getTimeStretch", "()D", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class DecoderTimerData extends DecoderData {
    private final long rawTimeUs;
    private final double timeStretch;

    public final long getRawTimeUs() {
        return this.rawTimeUs;
    }

    public final double getTimeStretch() {
        return this.timeStretch;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DecoderTimerData(ByteBuffer buffer, long j, long j2, double d, Function1<? super Boolean, Unit> release) {
        super(buffer, j2, release);
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(release, "release");
        this.rawTimeUs = j;
        this.timeStretch = d;
    }
}
