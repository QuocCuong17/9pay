package com.pichillilorenzo.flutter_inappwebview_android.find_interaction;

import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.FindSession;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class FindInteractionChannelDelegate extends ChannelDelegateImpl {
    private FindInteractionController findInteractionController;

    public FindInteractionChannelDelegate(FindInteractionController findInteractionController, MethodChannel methodChannel) {
        super(methodChannel);
        this.findInteractionController = findInteractionController;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0056, code lost:
    
        if (r0.equals("getSearchText") == false) goto L4;
     */
    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, io.flutter.plugin.common.MethodChannel.MethodCallHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        char c = 0;
        switch (str.hashCode()) {
            case -1008221461:
                break;
            case -853211864:
                if (str.equals("findAll")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -679382964:
                if (str.equals("findNext")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -234090249:
                if (str.equals("setSearchText")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 817048102:
                if (str.equals("clearMatches")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 2137531137:
                if (str.equals("getActiveFindSession")) {
                    c = 5;
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
                FindInteractionController findInteractionController = this.findInteractionController;
                if (findInteractionController != null) {
                    result.success(findInteractionController.searchText);
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 1:
                if (this.findInteractionController != null) {
                    this.findInteractionController.findAll((String) methodCall.argument("find"));
                }
                result.success(true);
                return;
            case 2:
                if (this.findInteractionController != null) {
                    this.findInteractionController.findNext(((Boolean) methodCall.argument("forward")).booleanValue());
                }
                result.success(true);
                return;
            case 3:
                FindInteractionController findInteractionController2 = this.findInteractionController;
                if (findInteractionController2 != null) {
                    findInteractionController2.searchText = (String) methodCall.argument("searchText");
                    result.success(true);
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 4:
                FindInteractionController findInteractionController3 = this.findInteractionController;
                if (findInteractionController3 != null) {
                    findInteractionController3.clearMatches();
                }
                result.success(true);
                return;
            case 5:
                FindInteractionController findInteractionController4 = this.findInteractionController;
                if (findInteractionController4 != null && findInteractionController4.activeFindSession != null) {
                    result.success(this.findInteractionController.activeFindSession.toMap());
                    return;
                } else {
                    result.success(null);
                    return;
                }
            default:
                result.notImplemented();
                return;
        }
    }

    public void onFindResultReceived(int i, int i2, boolean z) {
        FindInteractionController findInteractionController;
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        if (z && (findInteractionController = this.findInteractionController) != null && findInteractionController.webView != null) {
            this.findInteractionController.activeFindSession = new FindSession(i2, i);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("activeMatchOrdinal", Integer.valueOf(i));
        hashMap.put("numberOfMatches", Integer.valueOf(i2));
        hashMap.put("isDoneCounting", Boolean.valueOf(z));
        channel.invokeMethod("onFindResultReceived", hashMap);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.findInteractionController = null;
    }
}
