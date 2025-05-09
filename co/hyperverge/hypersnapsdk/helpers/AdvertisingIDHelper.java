package co.hyperverge.hypersnapsdk.helpers;

import android.content.Context;
import co.hyperverge.hypersnapsdk.utils.threading.ThreadExecutor;

@Deprecated
/* loaded from: classes2.dex */
public class AdvertisingIDHelper {
    public static final String TAG = "co.hyperverge.hypersnapsdk.helpers.AdvertisingIDHelper";
    static AdvertisingIDHelper idHelper;
    String advertisingID = null;
    ThreadExecutor mExecutor = ThreadExecutor.getInstance();

    @Deprecated
    public void generateAdvertisingID(Context context) {
    }

    public static AdvertisingIDHelper get() {
        if (idHelper == null) {
            idHelper = new AdvertisingIDHelper();
        }
        return idHelper;
    }

    @Deprecated
    public AdvertisingIDHelper() {
    }

    @Deprecated
    public String getAdvertisingID() {
        return this.advertisingID;
    }
}
