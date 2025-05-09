package com.pichillilorenzo.flutter_inappwebview_android.in_app_browser;

import android.app.Activity;
import java.util.List;

/* loaded from: classes4.dex */
public interface InAppBrowserDelegate {
    void didChangeProgress(int i);

    void didChangeTitle(String str);

    void didFailNavigation(String str, int i, String str2);

    void didFinishNavigation(String str);

    void didStartNavigation(String str);

    void didUpdateVisitedHistory(String str);

    Activity getActivity();

    List<ActivityResultListener> getActivityResultListeners();
}
