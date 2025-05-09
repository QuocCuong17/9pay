package com.pichillilorenzo.flutter_inappwebview_android.print_job;

import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.PrintJobInfoExt;
import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/* loaded from: classes4.dex */
public class PrintJobChannelDelegate extends ChannelDelegateImpl {
    private PrintJobController printJobController;

    public PrintJobChannelDelegate(PrintJobController printJobController, MethodChannel methodChannel) {
        super(methodChannel);
        this.printJobController = printJobController;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0035, code lost:
    
        if (r7.equals("getInfo") == false) goto L4;
     */
    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, io.flutter.plugin.common.MethodChannel.MethodCallHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        char c = 1;
        switch (str.hashCode()) {
            case -1367724422:
                if (str.equals(Constant.PARAM_CANCEL)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -75444956:
                break;
            case 1097506319:
                if (str.equals("restart")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1671767583:
                if (str.equals("dispose")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                PrintJobController printJobController = this.printJobController;
                if (printJobController != null) {
                    printJobController.cancel();
                    result.success(true);
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 1:
                PrintJobController printJobController2 = this.printJobController;
                if (printJobController2 != null) {
                    PrintJobInfoExt info = printJobController2.getInfo();
                    result.success(info != null ? info.toMap() : null);
                    return;
                } else {
                    result.success(null);
                    return;
                }
            case 2:
                PrintJobController printJobController3 = this.printJobController;
                if (printJobController3 != null) {
                    printJobController3.restart();
                    result.success(true);
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 3:
                PrintJobController printJobController4 = this.printJobController;
                if (printJobController4 != null) {
                    printJobController4.dispose();
                    result.success(true);
                    return;
                } else {
                    result.success(false);
                    return;
                }
            default:
                result.notImplemented();
                return;
        }
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.printJobController = null;
    }
}
