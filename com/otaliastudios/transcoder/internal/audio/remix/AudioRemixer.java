package com.otaliastudios.transcoder.internal.audio.remix;

import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import java.nio.ShortBuffer;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioRemixer.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u0000 \n2\u00020\u0001:\u0001\nJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH&¨\u0006\u000b"}, d2 = {"Lcom/otaliastudios/transcoder/internal/audio/remix/AudioRemixer;", "", "getRemixedSize", "", "inputSize", "remix", "", "inputBuffer", "Ljava/nio/ShortBuffer;", "outputBuffer", "Companion", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public interface AudioRemixer {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    int getRemixedSize(int inputSize);

    void remix(ShortBuffer inputBuffer, ShortBuffer outputBuffer);

    /* compiled from: AudioRemixer.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0080\u0002¢\u0006\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/otaliastudios/transcoder/internal/audio/remix/AudioRemixer$Companion;", "", "()V", WorkflowRequestType.Method.GET, "Lcom/otaliastudios/transcoder/internal/audio/remix/AudioRemixer;", "inputChannels", "", "outputChannels", "get$lib_release", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final AudioRemixer get$lib_release(int inputChannels, int outputChannels) {
            if (!SetsKt.setOf((Object[]) new Integer[]{1, 2}).contains(Integer.valueOf(inputChannels))) {
                throw new IllegalStateException(Intrinsics.stringPlus("Input channel count not supported: ", Integer.valueOf(inputChannels)).toString());
            }
            if (!SetsKt.setOf((Object[]) new Integer[]{1, 2}).contains(Integer.valueOf(outputChannels))) {
                throw new IllegalStateException(Intrinsics.stringPlus("Input channel count not supported: ", Integer.valueOf(inputChannels)).toString());
            }
            if (inputChannels < outputChannels) {
                return new UpMixAudioRemixer();
            }
            if (inputChannels > outputChannels) {
                return new DownMixAudioRemixer();
            }
            return new PassThroughAudioRemixer();
        }
    }
}
