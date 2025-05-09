package com.pichillilorenzo.flutter_inappwebview_android.print_job;

import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.types.Disposable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class PrintJobManager implements Disposable {
    protected static final String LOG_TAG = "PrintJobManager";
    public final Map<String, PrintJobController> jobs = new HashMap();
    public InAppWebViewFlutterPlugin plugin;

    public PrintJobManager(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        this.plugin = inAppWebViewFlutterPlugin;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        for (PrintJobController printJobController : this.jobs.values()) {
            if (printJobController != null) {
                printJobController.dispose();
            }
        }
        this.jobs.clear();
        this.plugin = null;
    }
}
