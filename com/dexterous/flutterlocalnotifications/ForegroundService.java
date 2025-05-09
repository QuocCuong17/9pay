package com.dexterous.flutterlocalnotifications;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class ForegroundService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        ForegroundServiceStartParameter foregroundServiceStartParameter;
        if (Build.VERSION.SDK_INT >= 33) {
            foregroundServiceStartParameter = (ForegroundServiceStartParameter) intent.getSerializableExtra(ForegroundServiceStartParameter.EXTRA, ForegroundServiceStartParameter.class);
        } else {
            foregroundServiceStartParameter = (ForegroundServiceStartParameter) intent.getSerializableExtra(ForegroundServiceStartParameter.EXTRA);
        }
        Notification createNotification = FlutterLocalNotificationsPlugin.createNotification(this, foregroundServiceStartParameter.notificationData);
        if (foregroundServiceStartParameter.foregroundServiceTypes != null && Build.VERSION.SDK_INT >= 29) {
            startForeground(foregroundServiceStartParameter.notificationData.f63id.intValue(), createNotification, orCombineFlags(foregroundServiceStartParameter.foregroundServiceTypes));
        } else {
            startForeground(foregroundServiceStartParameter.notificationData.f63id.intValue(), createNotification);
        }
        return foregroundServiceStartParameter.startMode;
    }

    private static int orCombineFlags(ArrayList<Integer> arrayList) {
        int intValue = arrayList.get(0).intValue();
        for (int i = 1; i < arrayList.size(); i++) {
            intValue |= arrayList.get(i).intValue();
        }
        return intValue;
    }
}
