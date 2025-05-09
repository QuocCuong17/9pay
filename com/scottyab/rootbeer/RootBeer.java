package com.scottyab.rootbeer;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import com.scottyab.rootbeer.util.QLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.apache.commons.io.IOUtils;

/* loaded from: classes5.dex */
public class RootBeer {
    private boolean loggingEnabled = true;
    private final Context mContext;

    public RootBeer(Context context) {
        this.mContext = context;
    }

    public boolean isRooted() {
        return detectRootManagementApps() || detectPotentiallyDangerousApps() || checkForBinary(CmcdConfiguration.KEY_STARTUP) || checkForDangerousProps() || checkForRWPaths() || detectTestKeys() || checkSuExists() || checkForRootNative() || checkForMagiskBinary();
    }

    @Deprecated
    public boolean isRootedWithoutBusyBoxCheck() {
        return isRooted();
    }

    public boolean isRootedWithBusyBoxCheck() {
        return detectRootManagementApps() || detectPotentiallyDangerousApps() || checkForBinary(CmcdConfiguration.KEY_STARTUP) || checkForBinary("busybox") || checkForDangerousProps() || checkForRWPaths() || detectTestKeys() || checkSuExists() || checkForRootNative() || checkForMagiskBinary();
    }

    public boolean detectTestKeys() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    public boolean detectRootManagementApps() {
        return detectRootManagementApps(null);
    }

    public boolean detectRootManagementApps(String[] strArr) {
        ArrayList arrayList = new ArrayList(Arrays.asList(Const.knownRootAppsPackages));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return isAnyPackageFromListInstalled(arrayList);
    }

    public boolean detectPotentiallyDangerousApps() {
        return detectPotentiallyDangerousApps(null);
    }

    public boolean detectPotentiallyDangerousApps(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(Const.knownDangerousAppsPackages));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return isAnyPackageFromListInstalled(arrayList);
    }

    public boolean detectRootCloakingApps() {
        return detectRootCloakingApps(null) || (canLoadNativeLibrary() && !checkForNativeLibraryReadAccess());
    }

    public boolean detectRootCloakingApps(String[] strArr) {
        ArrayList arrayList = new ArrayList(Arrays.asList(Const.knownRootCloakingPackages));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return isAnyPackageFromListInstalled(arrayList);
    }

    public boolean checkForSuBinary() {
        return checkForBinary(CmcdConfiguration.KEY_STARTUP);
    }

    public boolean checkForMagiskBinary() {
        return checkForBinary("magisk");
    }

    public boolean checkForBusyBoxBinary() {
        return checkForBinary("busybox");
    }

    public boolean checkForBinary(String str) {
        boolean z = false;
        for (String str2 : Const.getPaths()) {
            String str3 = str2 + str;
            if (new File(str2, str).exists()) {
                QLog.v(str3 + " binary detected!");
                z = true;
            }
        }
        return z;
    }

    public void setLogging(boolean z) {
        this.loggingEnabled = z;
        QLog.LOGGING_LEVEL = z ? 5 : 0;
    }

    private String[] propsReader() {
        try {
            InputStream inputStream = Runtime.getRuntime().exec("getprop").getInputStream();
            if (inputStream == null) {
                return null;
            }
            return new Scanner(inputStream).useDelimiter("\\A").next().split(IOUtils.LINE_SEPARATOR_UNIX);
        } catch (IOException | NoSuchElementException e) {
            QLog.e(e);
            return null;
        }
    }

    private String[] mountReader() {
        try {
            InputStream inputStream = Runtime.getRuntime().exec("mount").getInputStream();
            if (inputStream == null) {
                return null;
            }
            return new Scanner(inputStream).useDelimiter("\\A").next().split(IOUtils.LINE_SEPARATOR_UNIX);
        } catch (IOException | NoSuchElementException e) {
            QLog.e(e);
            return null;
        }
    }

    private boolean isAnyPackageFromListInstalled(List<String> list) {
        PackageManager packageManager = this.mContext.getPackageManager();
        boolean z = false;
        for (String str : list) {
            try {
                packageManager.getPackageInfo(str, 0);
                QLog.e(str + " ROOT management app detected!");
                z = true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return z;
    }

    public boolean checkForDangerousProps() {
        HashMap hashMap = new HashMap();
        hashMap.put("ro.debuggable", "1");
        hashMap.put("ro.secure", "0");
        String[] propsReader = propsReader();
        if (propsReader == null) {
            return false;
        }
        boolean z = false;
        for (String str : propsReader) {
            for (String str2 : hashMap.keySet()) {
                if (str.contains(str2)) {
                    String str3 = "[" + ((String) hashMap.get(str2)) + "]";
                    if (str.contains(str3)) {
                        QLog.v(str2 + " = " + str3 + " detected!");
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    public boolean checkForRWPaths() {
        String str;
        String str2;
        String[] strArr;
        String[] mountReader = mountReader();
        int i = 0;
        if (mountReader == null) {
            return false;
        }
        int i2 = Build.VERSION.SDK_INT;
        int length = mountReader.length;
        int i3 = 0;
        boolean z = false;
        while (i3 < length) {
            String str3 = mountReader[i3];
            String[] split = str3.split(" ");
            int i4 = 23;
            if ((i2 <= 23 && split.length < 4) || (i2 > 23 && split.length < 6)) {
                QLog.e("Error formatting mount line: " + str3);
            } else {
                if (i2 > 23) {
                    str = split[2];
                    str2 = split[5];
                } else {
                    str = split[1];
                    str2 = split[3];
                }
                String[] strArr2 = Const.pathsThatShouldNotBeWritable;
                int length2 = strArr2.length;
                int i5 = i;
                while (i5 < length2) {
                    String str4 = strArr2[i5];
                    if (str.equalsIgnoreCase(str4)) {
                        if (Build.VERSION.SDK_INT > i4) {
                            str2 = str2.replace("(", "").replace(")", "");
                        }
                        String[] split2 = str2.split(",");
                        int length3 = split2.length;
                        int i6 = 0;
                        while (i6 < length3) {
                            strArr = mountReader;
                            if (split2[i6].equalsIgnoreCase("rw")) {
                                QLog.v(str4 + " path is mounted with rw permissions! " + str3);
                                z = true;
                                break;
                            }
                            i6++;
                            mountReader = strArr;
                        }
                    }
                    strArr = mountReader;
                    i5++;
                    mountReader = strArr;
                    i4 = 23;
                }
            }
            i3++;
            mountReader = mountReader;
            i = 0;
        }
        return z;
    }

    public boolean checkSuExists() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"which", CmcdConfiguration.KEY_STARTUP});
            boolean z = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine() != null;
            if (process != null) {
                process.destroy();
            }
            return z;
        } catch (Throwable unused) {
            if (process != null) {
                process.destroy();
            }
            return false;
        }
    }

    public boolean checkForNativeLibraryReadAccess() {
        try {
            new RootBeerNative().setLogDebugMessages(this.loggingEnabled);
            return true;
        } catch (UnsatisfiedLinkError unused) {
            return false;
        }
    }

    public boolean canLoadNativeLibrary() {
        return new RootBeerNative().wasNativeLibraryLoaded();
    }

    public boolean checkForRootNative() {
        if (!canLoadNativeLibrary()) {
            QLog.e("We could not load the native library to test for root");
            return false;
        }
        String[] paths = Const.getPaths();
        int length = paths.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = paths[i] + CmcdConfiguration.KEY_STARTUP;
        }
        RootBeerNative rootBeerNative = new RootBeerNative();
        try {
            rootBeerNative.setLogDebugMessages(this.loggingEnabled);
            return rootBeerNative.checkForRoot(strArr) > 0;
        } catch (UnsatisfiedLinkError unused) {
            return false;
        }
    }
}
