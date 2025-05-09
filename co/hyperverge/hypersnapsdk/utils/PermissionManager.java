package co.hyperverge.hypersnapsdk.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class PermissionManager {
    private static final String TAG = "co.hyperverge.hypersnapsdk.utils.PermissionManager";
    private Activity activity;

    public List<String> setPermissions(List<String> list) {
        return list;
    }

    public boolean checkAndRequestPermissions(Activity activity) {
        this.activity = activity;
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        List<String> permission = setPermission();
        ArrayList arrayList = new ArrayList();
        for (String str : permission) {
            if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), str) != 0) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        if (arrayList.contains("android.permission.CAMERA") && SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService(activity.getApplicationContext()).logCameraPermissionsRequested();
        }
        ActivityCompat.requestPermissions(activity, (String[]) arrayList.toArray(new String[arrayList.size()]), 1212);
        return false;
    }

    public boolean checkAndRequestPermissions(Activity activity, List<String> list) {
        this.activity = activity;
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), str) != 0) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        if (arrayList.contains("android.permission.CAMERA") && SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService(activity.getApplicationContext()).logCameraPermissionsRequested();
        }
        ActivityCompat.requestPermissions(activity, (String[]) arrayList.toArray(new String[arrayList.size()]), 1212);
        return false;
    }

    public StatusArray getStatus(Activity activity, List<String> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (String str : list) {
            if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), str) == 0) {
                arrayList.add(str);
            } else {
                arrayList2.add(str);
            }
        }
        return new StatusArray(arrayList, arrayList2);
    }

    public List<String> setPermission() {
        ArrayList arrayList = new ArrayList();
        try {
            for (String str : this.activity.getApplicationContext().getPackageManager().getPackageInfo(this.activity.getApplicationContext().getPackageName(), 4096).requestedPermissions) {
                arrayList.add(str);
            }
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        return arrayList;
    }

    public void checkResult(int i, String[] strArr, int[] iArr) {
        boolean z;
        boolean z2;
        if (i != 1212) {
            return;
        }
        List<String> permission = setPermission();
        HashMap hashMap = new HashMap();
        Iterator<String> it = permission.iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            } else {
                hashMap.put(it.next(), 0);
            }
        }
        if (iArr.length > 0) {
            for (int i2 = 0; i2 < strArr.length; i2++) {
                hashMap.put(strArr[i2], Integer.valueOf(iArr[i2]));
            }
            Iterator<String> it2 = permission.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (((Integer) hashMap.get(it2.next())).intValue() == -1) {
                        z2 = false;
                        break;
                    }
                } else {
                    z2 = true;
                    break;
                }
            }
            if (z2) {
                return;
            }
            Iterator<String> it3 = permission.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity, it3.next())) {
                    z = true;
                    break;
                }
            }
            if (z) {
                ifCancelledAndCanRequest(this.activity);
            } else {
                ifCancelledAndCannotRequest(this.activity);
            }
        }
    }

    public void ifCancelledAndCanRequest(final Activity activity) {
        showDialogOK(activity, "Permission required for this app, please grant permission for the same", new DialogInterface.OnClickListener() { // from class: co.hyperverge.hypersnapsdk.utils.PermissionManager.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i != -1) {
                    return;
                }
                PermissionManager.this.checkAndRequestPermissions(activity);
            }
        });
    }

    public void ifCancelledAndCannotRequest(Activity activity) {
        Toast.makeText(activity.getApplicationContext(), "Go to settings and enable permissions", 1).show();
    }

    private void showDialogOK(Activity activity, String str, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(activity).setMessage(str).setPositiveButton("OK", onClickListener).setNegativeButton("Cancel", onClickListener).create().show();
    }

    /* loaded from: classes2.dex */
    public class StatusArray {
        public ArrayList<String> denied;
        public ArrayList<String> granted;

        StatusArray(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
            this.denied = arrayList2;
            this.granted = arrayList;
        }
    }
}
