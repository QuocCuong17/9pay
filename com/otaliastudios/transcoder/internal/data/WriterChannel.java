package com.otaliastudios.transcoder.internal.data;

import android.media.MediaFormat;
import com.otaliastudios.transcoder.internal.pipeline.Channel;
import kotlin.Metadata;

/* compiled from: Writer.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/otaliastudios/transcoder/internal/data/WriterChannel;", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "handleFormat", "", "format", "Landroid/media/MediaFormat;", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public interface WriterChannel extends Channel {
    void handleFormat(MediaFormat format);
}
