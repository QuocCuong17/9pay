package com.baseflow.permissionhandler;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class PermissionUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int parseManifestName(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2062386608:
                if (str.equals("android.permission.READ_SMS")) {
                    c = 0;
                    break;
                }
                break;
            case -1928411001:
                if (str.equals("android.permission.READ_CALENDAR")) {
                    c = 1;
                    break;
                }
                break;
            case -1921431796:
                if (str.equals("android.permission.READ_CALL_LOG")) {
                    c = 2;
                    break;
                }
                break;
            case -1888586689:
                if (str.equals("android.permission.ACCESS_FINE_LOCATION")) {
                    c = 3;
                    break;
                }
                break;
            case -1479758289:
                if (str.equals("android.permission.RECEIVE_WAP_PUSH")) {
                    c = 4;
                    break;
                }
                break;
            case -1238066820:
                if (str.equals("android.permission.BODY_SENSORS")) {
                    c = 5;
                    break;
                }
                break;
            case -1164582768:
                if (str.equals("android.permission.READ_PHONE_NUMBERS")) {
                    c = 6;
                    break;
                }
                break;
            case -901151997:
                if (str.equals("android.permission.BIND_CALL_REDIRECTION_SERVICE")) {
                    c = 7;
                    break;
                }
                break;
            case -895679497:
                if (str.equals("android.permission.RECEIVE_MMS")) {
                    c = '\b';
                    break;
                }
                break;
            case -895673731:
                if (str.equals("android.permission.RECEIVE_SMS")) {
                    c = '\t';
                    break;
                }
                break;
            case -406040016:
                if (str.equals("android.permission.READ_EXTERNAL_STORAGE")) {
                    c = '\n';
                    break;
                }
                break;
            case -63024214:
                if (str.equals("android.permission.ACCESS_COARSE_LOCATION")) {
                    c = 11;
                    break;
                }
                break;
            case -5573545:
                if (str.equals("android.permission.READ_PHONE_STATE")) {
                    c = '\f';
                    break;
                }
                break;
            case 52602690:
                if (str.equals("android.permission.SEND_SMS")) {
                    c = '\r';
                    break;
                }
                break;
            case 112197485:
                if (str.equals("android.permission.CALL_PHONE")) {
                    c = 14;
                    break;
                }
                break;
            case 214526995:
                if (str.equals("android.permission.WRITE_CONTACTS")) {
                    c = 15;
                    break;
                }
                break;
            case 463403621:
                if (str.equals("android.permission.CAMERA")) {
                    c = 16;
                    break;
                }
                break;
            case 603653886:
                if (str.equals("android.permission.WRITE_CALENDAR")) {
                    c = 17;
                    break;
                }
                break;
            case 610633091:
                if (str.equals("android.permission.WRITE_CALL_LOG")) {
                    c = 18;
                    break;
                }
                break;
            case 784519842:
                if (str.equals("android.permission.USE_SIP")) {
                    c = 19;
                    break;
                }
                break;
            case 1271781903:
                if (str.equals("android.permission.GET_ACCOUNTS")) {
                    c = 20;
                    break;
                }
                break;
            case 1365911975:
                if (str.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                    c = 21;
                    break;
                }
                break;
            case 1780337063:
                if (str.equals("android.permission.ACTIVITY_RECOGNITION")) {
                    c = 22;
                    break;
                }
                break;
            case 1831139720:
                if (str.equals("android.permission.RECORD_AUDIO")) {
                    c = 23;
                    break;
                }
                break;
            case 1977429404:
                if (str.equals("android.permission.READ_CONTACTS")) {
                    c = 24;
                    break;
                }
                break;
            case 2024715147:
                if (str.equals("android.permission.ACCESS_BACKGROUND_LOCATION")) {
                    c = 25;
                    break;
                }
                break;
            case 2114579147:
                if (str.equals("android.permission.ACCESS_MEDIA_LOCATION")) {
                    c = 26;
                    break;
                }
                break;
            case 2133799037:
                if (str.equals("com.android.voicemail.permission.ADD_VOICEMAIL")) {
                    c = 27;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 4:
            case '\b':
            case '\t':
            case '\r':
                return 13;
            case 1:
            case 17:
                return 0;
            case 2:
            case 6:
            case 7:
            case '\f':
            case 14:
            case 18:
            case 19:
            case 27:
                return 8;
            case 3:
            case 11:
                return 3;
            case 5:
                return 12;
            case '\n':
            case 21:
                return 15;
            case 15:
            case 20:
            case 24:
                return 2;
            case 16:
                return 1;
            case 22:
                return 19;
            case 23:
                return 7;
            case 25:
                return 4;
            case 26:
                return 18;
            default:
                return 20;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0008. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x016d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static List<String> getManifestNames(Context context, int i) {
        ArrayList arrayList = new ArrayList();
        switch (i) {
            case 0:
                if (hasPermissionInManifest(context, arrayList, "android.permission.READ_CALENDAR")) {
                    arrayList.add("android.permission.READ_CALENDAR");
                }
                if (hasPermissionInManifest(context, arrayList, "android.permission.WRITE_CALENDAR")) {
                    arrayList.add("android.permission.WRITE_CALENDAR");
                }
                return arrayList;
            case 1:
                if (hasPermissionInManifest(context, arrayList, "android.permission.CAMERA")) {
                    arrayList.add("android.permission.CAMERA");
                }
                return arrayList;
            case 2:
                if (hasPermissionInManifest(context, arrayList, "android.permission.READ_CONTACTS")) {
                    arrayList.add("android.permission.READ_CONTACTS");
                }
                if (hasPermissionInManifest(context, arrayList, "android.permission.WRITE_CONTACTS")) {
                    arrayList.add("android.permission.WRITE_CONTACTS");
                }
                if (hasPermissionInManifest(context, arrayList, "android.permission.GET_ACCOUNTS")) {
                    arrayList.add("android.permission.GET_ACCOUNTS");
                }
                return arrayList;
            case 3:
            case 5:
                if (hasPermissionInManifest(context, arrayList, "android.permission.ACCESS_COARSE_LOCATION")) {
                    arrayList.add("android.permission.ACCESS_COARSE_LOCATION");
                }
                if (hasPermissionInManifest(context, arrayList, "android.permission.ACCESS_FINE_LOCATION")) {
                    arrayList.add("android.permission.ACCESS_FINE_LOCATION");
                }
                return arrayList;
            case 4:
                if (Build.VERSION.SDK_INT >= 29 && hasPermissionInManifest(context, arrayList, "android.permission.ACCESS_BACKGROUND_LOCATION")) {
                    arrayList.add("android.permission.ACCESS_BACKGROUND_LOCATION");
                }
                if (hasPermissionInManifest(context, arrayList, "android.permission.ACCESS_COARSE_LOCATION")) {
                }
                if (hasPermissionInManifest(context, arrayList, "android.permission.ACCESS_FINE_LOCATION")) {
                }
                return arrayList;
            case 6:
            case 9:
            case 11:
            case 17:
            case 20:
                return null;
            case 7:
            case 14:
                if (hasPermissionInManifest(context, arrayList, "android.permission.RECORD_AUDIO")) {
                    arrayList.add("android.permission.RECORD_AUDIO");
                }
                return arrayList;
            case 8:
                if (hasPermissionInManifest(context, arrayList, "android.permission.READ_PHONE_STATE")) {
                    arrayList.add("android.permission.READ_PHONE_STATE");
                }
                if (Build.VERSION.SDK_INT > 29 && hasPermissionInManifest(context, arrayList, "android.permission.READ_PHONE_NUMBERS")) {
                    arrayList.add("android.permission.READ_PHONE_NUMBERS");
                }
                if (hasPermissionInManifest(context, arrayList, "android.permission.CALL_PHONE")) {
                    arrayList.add("android.permission.CALL_PHONE");
                }
                if (hasPermissionInManifest(context, arrayList, "android.permission.READ_CALL_LOG")) {
                    arrayList.add("android.permission.READ_CALL_LOG");
                }
                if (hasPermissionInManifest(context, arrayList, "android.permission.WRITE_CALL_LOG")) {
                    arrayList.add("android.permission.WRITE_CALL_LOG");
                }
                if (hasPermissionInManifest(context, arrayList, "com.android.voicemail.permission.ADD_VOICEMAIL")) {
                    arrayList.add("com.android.voicemail.permission.ADD_VOICEMAIL");
                }
                if (hasPermissionInManifest(context, arrayList, "android.permission.USE_SIP")) {
                    arrayList.add("android.permission.USE_SIP");
                }
                if (Build.VERSION.SDK_INT >= 29 && hasPermissionInManifest(context, arrayList, "android.permission.BIND_CALL_REDIRECTION_SERVICE")) {
                    arrayList.add("android.permission.BIND_CALL_REDIRECTION_SERVICE");
                }
                if (Build.VERSION.SDK_INT >= 26 && hasPermissionInManifest(context, arrayList, "android.permission.ANSWER_PHONE_CALLS")) {
                    arrayList.add("android.permission.ANSWER_PHONE_CALLS");
                }
                return arrayList;
            case 10:
            default:
                return arrayList;
            case 12:
                if (Build.VERSION.SDK_INT >= 20 && hasPermissionInManifest(context, arrayList, "android.permission.BODY_SENSORS")) {
                    arrayList.add("android.permission.BODY_SENSORS");
                }
                return arrayList;
            case 13:
                if (hasPermissionInManifest(context, arrayList, "android.permission.SEND_SMS")) {
                    arrayList.add("android.permission.SEND_SMS");
                }
                if (hasPermissionInManifest(context, arrayList, "android.permission.RECEIVE_SMS")) {
                    arrayList.add("android.permission.RECEIVE_SMS");
                }
                if (hasPermissionInManifest(context, arrayList, "android.permission.READ_SMS")) {
                    arrayList.add("android.permission.READ_SMS");
                }
                if (hasPermissionInManifest(context, arrayList, "android.permission.RECEIVE_WAP_PUSH")) {
                    arrayList.add("android.permission.RECEIVE_WAP_PUSH");
                }
                if (hasPermissionInManifest(context, arrayList, "android.permission.RECEIVE_MMS")) {
                    arrayList.add("android.permission.RECEIVE_MMS");
                }
                return arrayList;
            case 15:
                if (hasPermissionInManifest(context, arrayList, "android.permission.READ_EXTERNAL_STORAGE")) {
                    arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
                }
                if (hasPermissionInManifest(context, arrayList, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                    arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
                }
                return arrayList;
            case 16:
                if (Build.VERSION.SDK_INT >= 23 && hasPermissionInManifest(context, arrayList, "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS")) {
                    arrayList.add("android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
                }
                return arrayList;
            case 18:
                if (Build.VERSION.SDK_INT < 29) {
                    return null;
                }
                if (hasPermissionInManifest(context, arrayList, "android.permission.ACCESS_MEDIA_LOCATION")) {
                    arrayList.add("android.permission.ACCESS_MEDIA_LOCATION");
                }
                return arrayList;
            case 19:
                if (Build.VERSION.SDK_INT < 29) {
                    return null;
                }
                if (hasPermissionInManifest(context, arrayList, "android.permission.ACTIVITY_RECOGNITION")) {
                    arrayList.add("android.permission.ACTIVITY_RECOGNITION");
                }
                return arrayList;
            case 21:
                if (hasPermissionInManifest(context, arrayList, "android.permission.BLUETOOTH")) {
                    arrayList.add("android.permission.BLUETOOTH");
                }
                return arrayList;
        }
    }

    private static boolean hasPermissionInManifest(Context context, ArrayList<String> arrayList, String str) {
        if (arrayList != null) {
            try {
                Iterator<String> it = arrayList.iterator();
                while (it.hasNext()) {
                    if (it.next().equals(str)) {
                        return true;
                    }
                }
            } catch (Exception e) {
                Log.d("permissions_handler", "Unable to check manifest for permission: ", e);
            }
        }
        if (context == null) {
            Log.d("permissions_handler", "Unable to detect current Activity or App Context.");
            return false;
        }
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
        if (packageInfo == null) {
            Log.d("permissions_handler", "Unable to get Package info, will not be able to determine permissions to request.");
            return false;
        }
        Iterator it2 = new ArrayList(Arrays.asList(packageInfo.requestedPermissions)).iterator();
        while (it2.hasNext()) {
            if (((String) it2.next()).equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int toPermissionStatus(Activity activity, String str, int i) {
        if (i == -1) {
            return (Build.VERSION.SDK_INT < 23 || !isNeverAskAgainSelected(activity, str)) ? 0 : 4;
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updatePermissionShouldShowStatus(Activity activity, int i) {
        List<String> manifestNames;
        if (activity == null || (manifestNames = getManifestNames(activity, i)) == null) {
            return;
        }
        manifestNames.isEmpty();
    }

    static boolean isNeverAskAgainSelected(Activity activity, String str) {
        if (activity == null) {
            return false;
        }
        return !ActivityCompat.shouldShowRequestPermissionRationale(activity, str);
    }
}
