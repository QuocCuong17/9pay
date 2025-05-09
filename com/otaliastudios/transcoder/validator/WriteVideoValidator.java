package com.otaliastudios.transcoder.validator;

import com.otaliastudios.transcoder.common.TrackStatus;

/* loaded from: classes4.dex */
public class WriteVideoValidator implements Validator {

    /* renamed from: com.otaliastudios.transcoder.validator.WriteVideoValidator$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$otaliastudios$transcoder$common$TrackStatus;

        static {
            int[] iArr = new int[TrackStatus.values().length];
            $SwitchMap$com$otaliastudios$transcoder$common$TrackStatus = iArr;
            try {
                iArr[TrackStatus.ABSENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$otaliastudios$transcoder$common$TrackStatus[TrackStatus.REMOVING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$otaliastudios$transcoder$common$TrackStatus[TrackStatus.COMPRESSING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$otaliastudios$transcoder$common$TrackStatus[TrackStatus.PASS_THROUGH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.otaliastudios.transcoder.validator.Validator
    public boolean validate(TrackStatus trackStatus, TrackStatus trackStatus2) {
        int i = AnonymousClass1.$SwitchMap$com$otaliastudios$transcoder$common$TrackStatus[trackStatus.ordinal()];
        return (i == 1 || i == 4) ? false : true;
    }
}
