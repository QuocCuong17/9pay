package com.otaliastudios.transcoder.common;

/* loaded from: classes4.dex */
public enum TrackStatus {
    ABSENT,
    REMOVING,
    PASS_THROUGH,
    COMPRESSING;

    /* renamed from: com.otaliastudios.transcoder.common.TrackStatus$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$otaliastudios$transcoder$common$TrackStatus;

        static {
            int[] iArr = new int[TrackStatus.values().length];
            $SwitchMap$com$otaliastudios$transcoder$common$TrackStatus = iArr;
            try {
                iArr[TrackStatus.PASS_THROUGH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$otaliastudios$transcoder$common$TrackStatus[TrackStatus.COMPRESSING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$otaliastudios$transcoder$common$TrackStatus[TrackStatus.REMOVING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$otaliastudios$transcoder$common$TrackStatus[TrackStatus.ABSENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public boolean isTranscoding() {
        int i = AnonymousClass1.$SwitchMap$com$otaliastudios$transcoder$common$TrackStatus[ordinal()];
        if (i == 1 || i == 2) {
            return true;
        }
        if (i == 3 || i == 4) {
            return false;
        }
        throw new RuntimeException("Unexpected track status: " + this);
    }
}
