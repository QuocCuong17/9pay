package com.otaliastudios.transcoder.common;

import android.media.MediaFormat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: TrackType.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\u0007"}, d2 = {"trackType", "Lcom/otaliastudios/transcoder/common/TrackType;", "Landroid/media/MediaFormat;", "getTrackType", "(Landroid/media/MediaFormat;)Lcom/otaliastudios/transcoder/common/TrackType;", "trackTypeOrNull", "getTrackTypeOrNull", "lib_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class TrackTypeKt {
    public static final TrackType getTrackType(MediaFormat mediaFormat) {
        Intrinsics.checkNotNullParameter(mediaFormat, "<this>");
        TrackType trackTypeOrNull = getTrackTypeOrNull(mediaFormat);
        if (trackTypeOrNull != null) {
            return trackTypeOrNull;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("Unexpected mime type: ", mediaFormat.getString("mime")).toString());
    }

    public static final TrackType getTrackTypeOrNull(MediaFormat mediaFormat) {
        Intrinsics.checkNotNullParameter(mediaFormat, "<this>");
        String string = mediaFormat.getString("mime");
        Intrinsics.checkNotNull(string);
        Intrinsics.checkNotNullExpressionValue(string, "getString(MediaFormat.KEY_MIME)!!");
        if (StringsKt.startsWith$default(string, "audio/", false, 2, (Object) null)) {
            return TrackType.AUDIO;
        }
        String string2 = mediaFormat.getString("mime");
        Intrinsics.checkNotNull(string2);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(MediaFormat.KEY_MIME)!!");
        if (StringsKt.startsWith$default(string2, "video/", false, 2, (Object) null)) {
            return TrackType.VIDEO;
        }
        return null;
    }
}
