package com.otaliastudios.transcoder.validator;

import com.otaliastudios.transcoder.common.TrackStatus;

/* loaded from: classes4.dex */
public class DefaultValidator implements Validator {
    @Override // com.otaliastudios.transcoder.validator.Validator
    public boolean validate(TrackStatus trackStatus, TrackStatus trackStatus2) {
        return trackStatus == TrackStatus.COMPRESSING || trackStatus2 == TrackStatus.COMPRESSING || trackStatus == TrackStatus.REMOVING || trackStatus2 == TrackStatus.REMOVING;
    }
}
