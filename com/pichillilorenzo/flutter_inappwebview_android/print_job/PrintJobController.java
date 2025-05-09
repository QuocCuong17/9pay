package com.pichillilorenzo.flutter_inappwebview_android.print_job;

import android.print.PrintJob;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.types.Disposable;
import com.pichillilorenzo.flutter_inappwebview_android.types.PrintJobInfoExt;
import io.flutter.plugin.common.MethodChannel;

/* loaded from: classes4.dex */
public class PrintJobController implements Disposable {
    protected static final String LOG_TAG = "PrintJob";
    public static final String METHOD_CHANNEL_NAME_PREFIX = "com.pichillilorenzo/flutter_inappwebview_printjobcontroller_";
    public PrintJobChannelDelegate channelDelegate;

    /* renamed from: id, reason: collision with root package name */
    public String f100id;
    public PrintJob job;
    public InAppWebViewFlutterPlugin plugin;
    public PrintJobSettings settings;

    public PrintJobController(String str, PrintJob printJob, PrintJobSettings printJobSettings, InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        this.f100id = str;
        this.plugin = inAppWebViewFlutterPlugin;
        this.job = printJob;
        this.settings = printJobSettings;
        this.channelDelegate = new PrintJobChannelDelegate(this, new MethodChannel(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME_PREFIX + str));
    }

    public void cancel() {
        PrintJob printJob = this.job;
        if (printJob != null) {
            printJob.cancel();
        }
    }

    public void restart() {
        PrintJob printJob = this.job;
        if (printJob != null) {
            printJob.restart();
        }
    }

    public PrintJobInfoExt getInfo() {
        PrintJob printJob = this.job;
        if (printJob != null) {
            return PrintJobInfoExt.fromPrintJobInfo(printJob.getInfo());
        }
        return null;
    }

    public void disposeNoCancel() {
        PrintJobManager printJobManager;
        PrintJobChannelDelegate printJobChannelDelegate = this.channelDelegate;
        if (printJobChannelDelegate != null) {
            printJobChannelDelegate.dispose();
            this.channelDelegate = null;
        }
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
        if (inAppWebViewFlutterPlugin != null && (printJobManager = inAppWebViewFlutterPlugin.printJobManager) != null && printJobManager.jobs.containsKey(this.f100id)) {
            printJobManager.jobs.put(this.f100id, null);
        }
        if (this.job != null) {
            this.job = null;
        }
        this.plugin = null;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        PrintJobManager printJobManager;
        PrintJobChannelDelegate printJobChannelDelegate = this.channelDelegate;
        if (printJobChannelDelegate != null) {
            printJobChannelDelegate.dispose();
            this.channelDelegate = null;
        }
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
        if (inAppWebViewFlutterPlugin != null && (printJobManager = inAppWebViewFlutterPlugin.printJobManager) != null && printJobManager.jobs.containsKey(this.f100id)) {
            printJobManager.jobs.put(this.f100id, null);
        }
        PrintJob printJob = this.job;
        if (printJob != null) {
            printJob.cancel();
            this.job = null;
        }
        this.plugin = null;
    }
}
