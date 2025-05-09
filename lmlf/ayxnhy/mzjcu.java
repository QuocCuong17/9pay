package lmlf.ayxnhy;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class mzjcu {
    public static boolean cwppsz = false;

    public static void prwxt() {
        if (cwppsz) {
            return;
        }
        cwppsz = true;
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new mhg(), tfwhgw.ker(), tfwhgw.idvl(), TimeUnit.MILLISECONDS);
    }
}
