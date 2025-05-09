package co.hyperverge.hvcamera.magicfilter.utils;

import co.hyperverge.hvcamera.HVLog;

/* loaded from: classes2.dex */
public enum Rotation {
    NORMAL,
    ROTATION_90,
    ROTATION_180,
    ROTATION_270;

    public static Rotation fromInt(int i) {
        HVLog.d("Rotation", "fromInt() called with: rotation = [" + i + "]");
        if (i == 0) {
            return NORMAL;
        }
        if (i == 90) {
            return ROTATION_90;
        }
        if (i == 180) {
            return ROTATION_180;
        }
        if (i == 270) {
            return ROTATION_270;
        }
        if (i == 360) {
            return NORMAL;
        }
        throw new IllegalStateException(i + " is an unknown rotation. Needs to be either 0, 90, 180 or 270!");
    }

    /* renamed from: co.hyperverge.hvcamera.magicfilter.utils.Rotation$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$co$hyperverge$hvcamera$magicfilter$utils$Rotation;

        static {
            int[] iArr = new int[Rotation.values().length];
            $SwitchMap$co$hyperverge$hvcamera$magicfilter$utils$Rotation = iArr;
            try {
                iArr[Rotation.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$co$hyperverge$hvcamera$magicfilter$utils$Rotation[Rotation.ROTATION_90.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$co$hyperverge$hvcamera$magicfilter$utils$Rotation[Rotation.ROTATION_180.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$co$hyperverge$hvcamera$magicfilter$utils$Rotation[Rotation.ROTATION_270.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public int asInt() {
        HVLog.d("Rotation", "asInt() called");
        int i = AnonymousClass1.$SwitchMap$co$hyperverge$hvcamera$magicfilter$utils$Rotation[ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 90;
        }
        if (i == 3) {
            return 180;
        }
        if (i == 4) {
            return 270;
        }
        throw new IllegalStateException("Unknown Rotation!");
    }
}
