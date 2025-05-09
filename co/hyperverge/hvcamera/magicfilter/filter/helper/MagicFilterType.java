package co.hyperverge.hvcamera.magicfilter.filter.helper;

import android.content.Context;
import co.hyperverge.hvcamera.HVLog;
import co.hyperverge.hvcamera.R;

/* loaded from: classes2.dex */
public class MagicFilterType {
    public static final int FILTER_COUNT = 1;
    public static final int NONE = 0;
    private static final String TAG = "MagicFilterType";

    public static int filterTypeToName(int i) {
        HVLog.d(TAG, "filterTypeToName() called with: filterType = [" + i + "]");
        if (i == 0) {
            return R.string.filter_none;
        }
        return R.string.filter_none;
    }

    public static String filterTypeToNameString(Context context, int i) {
        HVLog.d(TAG, "filterTypeToNameString() called with: context = [" + context + "], filter = [" + i + "]");
        return context.getResources().getString(filterTypeToName(i));
    }
}
