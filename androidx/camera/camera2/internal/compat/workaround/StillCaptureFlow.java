package androidx.camera.camera2.internal.compat.workaround;

import android.hardware.camera2.CaptureRequest;
import androidx.camera.camera2.internal.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.internal.compat.quirk.StillCaptureFlashStopRepeatingQuirk;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class StillCaptureFlow {
    private final boolean mShouldStopRepeatingBeforeStillCapture;

    public StillCaptureFlow() {
        this.mShouldStopRepeatingBeforeStillCapture = ((StillCaptureFlashStopRepeatingQuirk) DeviceQuirks.get(StillCaptureFlashStopRepeatingQuirk.class)) != null;
    }

    public boolean shouldStopRepeatingBeforeCapture(List<CaptureRequest> list, boolean z) {
        if (this.mShouldStopRepeatingBeforeStillCapture && z) {
            Iterator<CaptureRequest> it = list.iterator();
            while (it.hasNext()) {
                int intValue = ((Integer) it.next().get(CaptureRequest.CONTROL_AE_MODE)).intValue();
                if (intValue == 2 || intValue == 3) {
                    return true;
                }
            }
        }
        return false;
    }
}
