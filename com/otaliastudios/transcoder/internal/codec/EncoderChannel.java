package com.otaliastudios.transcoder.internal.codec;

import android.view.Surface;
import com.otaliastudios.transcoder.internal.pipeline.Channel;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Pair;

/* compiled from: Encoder.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\b`\u0018\u00002\u00020\u0001J\u0016\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007H&R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\n"}, d2 = {"Lcom/otaliastudios/transcoder/internal/codec/EncoderChannel;", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "surface", "Landroid/view/Surface;", "getSurface", "()Landroid/view/Surface;", "buffer", "Lkotlin/Pair;", "Ljava/nio/ByteBuffer;", "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public interface EncoderChannel extends Channel {
    Pair<ByteBuffer, Integer> buffer();

    Surface getSurface();
}
