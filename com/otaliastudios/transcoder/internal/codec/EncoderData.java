package com.otaliastudios.transcoder.internal.codec;

import co.hyperverge.hyperkyc.data.models.state.TransactionState$ModuleData$$ExternalSyntheticBackport0;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Encoder.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0080\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J)\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/otaliastudios/transcoder/internal/codec/EncoderData;", "", "buffer", "Ljava/nio/ByteBuffer;", "id", "", "timeUs", "", "(Ljava/nio/ByteBuffer;IJ)V", "getBuffer", "()Ljava/nio/ByteBuffer;", "getId", "()I", "getTimeUs", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "Companion", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class EncoderData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final EncoderData Empty = new EncoderData(null, 0, 0);
    private final ByteBuffer buffer;
    private final int id;
    private final long timeUs;

    public static /* synthetic */ EncoderData copy$default(EncoderData encoderData, ByteBuffer byteBuffer, int i, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            byteBuffer = encoderData.buffer;
        }
        if ((i2 & 2) != 0) {
            i = encoderData.id;
        }
        if ((i2 & 4) != 0) {
            j = encoderData.timeUs;
        }
        return encoderData.copy(byteBuffer, i, j);
    }

    /* renamed from: component1, reason: from getter */
    public final ByteBuffer getBuffer() {
        return this.buffer;
    }

    /* renamed from: component2, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* renamed from: component3, reason: from getter */
    public final long getTimeUs() {
        return this.timeUs;
    }

    public final EncoderData copy(ByteBuffer buffer, int id2, long timeUs) {
        return new EncoderData(buffer, id2, timeUs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EncoderData)) {
            return false;
        }
        EncoderData encoderData = (EncoderData) other;
        return Intrinsics.areEqual(this.buffer, encoderData.buffer) && this.id == encoderData.id && this.timeUs == encoderData.timeUs;
    }

    public int hashCode() {
        ByteBuffer byteBuffer = this.buffer;
        return ((((byteBuffer == null ? 0 : byteBuffer.hashCode()) * 31) + this.id) * 31) + TransactionState$ModuleData$$ExternalSyntheticBackport0.m(this.timeUs);
    }

    public String toString() {
        return "EncoderData(buffer=" + this.buffer + ", id=" + this.id + ", timeUs=" + this.timeUs + ')';
    }

    public EncoderData(ByteBuffer byteBuffer, int i, long j) {
        this.buffer = byteBuffer;
        this.id = i;
        this.timeUs = j;
    }

    public final ByteBuffer getBuffer() {
        return this.buffer;
    }

    public final int getId() {
        return this.id;
    }

    public final long getTimeUs() {
        return this.timeUs;
    }

    /* compiled from: Encoder.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/otaliastudios/transcoder/internal/codec/EncoderData$Companion;", "", "()V", "Empty", "Lcom/otaliastudios/transcoder/internal/codec/EncoderData;", "getEmpty", "()Lcom/otaliastudios/transcoder/internal/codec/EncoderData;", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EncoderData getEmpty() {
            return EncoderData.Empty;
        }
    }
}
