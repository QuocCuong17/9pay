package io.flutter.plugins.webviewflutter;

import android.hardware.display.DisplayManager;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
class DisplayListenerProxy {
    private static final String TAG = "DisplayListenerProxy";
    private ArrayList<DisplayManager.DisplayListener> listenersBeforeWebView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onPreWebViewInitialization(DisplayManager displayManager) {
        this.listenersBeforeWebView = yoinkDisplayListeners(displayManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onPostWebViewInitialization(final DisplayManager displayManager) {
        final ArrayList<DisplayManager.DisplayListener> yoinkDisplayListeners = yoinkDisplayListeners(displayManager);
        yoinkDisplayListeners.removeAll(this.listenersBeforeWebView);
        if (yoinkDisplayListeners.isEmpty()) {
            return;
        }
        Iterator<DisplayManager.DisplayListener> it = yoinkDisplayListeners.iterator();
        while (it.hasNext()) {
            displayManager.unregisterDisplayListener(it.next());
            displayManager.registerDisplayListener(new DisplayManager.DisplayListener() { // from class: io.flutter.plugins.webviewflutter.DisplayListenerProxy.1
                @Override // android.hardware.display.DisplayManager.DisplayListener
                public void onDisplayAdded(int i) {
                    Iterator it2 = yoinkDisplayListeners.iterator();
                    while (it2.hasNext()) {
                        ((DisplayManager.DisplayListener) it2.next()).onDisplayAdded(i);
                    }
                }

                @Override // android.hardware.display.DisplayManager.DisplayListener
                public void onDisplayRemoved(int i) {
                    Iterator it2 = yoinkDisplayListeners.iterator();
                    while (it2.hasNext()) {
                        ((DisplayManager.DisplayListener) it2.next()).onDisplayRemoved(i);
                    }
                }

                @Override // android.hardware.display.DisplayManager.DisplayListener
                public void onDisplayChanged(int i) {
                    if (displayManager.getDisplay(i) == null) {
                        return;
                    }
                    Iterator it2 = yoinkDisplayListeners.iterator();
                    while (it2.hasNext()) {
                        ((DisplayManager.DisplayListener) it2.next()).onDisplayChanged(i);
                    }
                }
            }, null);
        }
    }

    private static ArrayList<DisplayManager.DisplayListener> yoinkDisplayListeners(DisplayManager displayManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return new ArrayList<>();
        }
        try {
            Field declaredField = DisplayManager.class.getDeclaredField("mGlobal");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(displayManager);
            Field declaredField2 = obj.getClass().getDeclaredField("mDisplayListeners");
            declaredField2.setAccessible(true);
            ArrayList arrayList = (ArrayList) declaredField2.get(obj);
            Field field = null;
            ArrayList<DisplayManager.DisplayListener> arrayList2 = new ArrayList<>();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (field == null) {
                    field = next.getClass().getField("mListener");
                    field.setAccessible(true);
                }
                arrayList2.add((DisplayManager.DisplayListener) field.get(next));
            }
            return arrayList2;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Log.w(TAG, "Could not extract WebView's display listeners. " + e);
            return new ArrayList<>();
        }
    }
}
