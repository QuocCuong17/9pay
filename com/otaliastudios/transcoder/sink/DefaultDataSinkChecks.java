package com.otaliastudios.transcoder.sink;

import android.media.MediaFormat;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.utils.AvcCsdUtils;
import com.otaliastudios.transcoder.internal.utils.AvcSpsUtils;
import com.otaliastudios.transcoder.internal.utils.Logger;

/* loaded from: classes4.dex */
class DefaultDataSinkChecks {
    private static final Logger LOG = new Logger("DefaultDataSinkChecks");

    /* JADX INFO: Access modifiers changed from: package-private */
    public void checkOutputFormat(TrackType trackType, MediaFormat mediaFormat) {
        if (trackType == TrackType.VIDEO) {
            checkVideoOutputFormat(mediaFormat);
        } else if (trackType == TrackType.AUDIO) {
            checkAudioOutputFormat(mediaFormat);
        }
    }

    private void checkVideoOutputFormat(MediaFormat mediaFormat) {
        String string = mediaFormat.getString("mime");
        if (!"video/avc".equals(string)) {
            throw new InvalidOutputFormatException("Video codecs other than AVC is not supported, actual mime type: " + string);
        }
        byte profileIdc = AvcSpsUtils.getProfileIdc(AvcCsdUtils.getSpsBuffer(mediaFormat));
        String profileName = AvcSpsUtils.getProfileName(profileIdc);
        if (profileIdc == 66) {
            LOG.i("Output H.264 profile: " + profileName);
            return;
        }
        LOG.w("Output H.264 profile: " + profileName + ". This might not be supported.");
    }

    private void checkAudioOutputFormat(MediaFormat mediaFormat) {
        String string = mediaFormat.getString("mime");
        if ("audio/mp4a-latm".equals(string)) {
            return;
        }
        throw new InvalidOutputFormatException("Audio codecs other than AAC is not supported, actual mime type: " + string);
    }
}
