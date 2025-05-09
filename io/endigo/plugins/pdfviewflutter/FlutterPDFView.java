package io.endigo.plugins.pdfviewflutter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.link.LinkHandler;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.pichillilorenzo.flutter_inappwebview_android.credential_database.URLCredentialContract;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.platform.PlatformView;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class FlutterPDFView implements PlatformView, MethodChannel.MethodCallHandler {
    private final LinkHandler linkHandler;
    private final MethodChannel methodChannel;
    private final PDFView pdfView;

    @Override // io.flutter.plugin.platform.PlatformView
    public /* synthetic */ void onFlutterViewAttached(View view) {
        PlatformView.CC.$default$onFlutterViewAttached(this, view);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public /* synthetic */ void onFlutterViewDetached() {
        PlatformView.CC.$default$onFlutterViewDetached(this);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public /* synthetic */ void onInputConnectionLocked() {
        PlatformView.CC.$default$onInputConnectionLocked(this);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public /* synthetic */ void onInputConnectionUnlocked() {
        PlatformView.CC.$default$onInputConnectionUnlocked(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlutterPDFView(Context context, BinaryMessenger binaryMessenger, int i, Map<String, Object> map) {
        PDFView.Configurator configurator = null;
        PDFView pDFView = new PDFView(context, null);
        this.pdfView = pDFView;
        boolean z = getBoolean(map, "preventLinkNavigation");
        MethodChannel methodChannel = new MethodChannel(binaryMessenger, "plugins.endigo.io/pdfview_" + i);
        this.methodChannel = methodChannel;
        methodChannel.setMethodCallHandler(this);
        this.linkHandler = new PDFLinkHandler(context, pDFView, methodChannel, z);
        if (map.get("filePath") != null) {
            configurator = pDFView.fromUri(getURI((String) map.get("filePath")));
        } else if (map.get("pdfData") != null) {
            configurator = pDFView.fromBytes((byte[]) map.get("pdfData"));
        }
        Object obj = map.get("hexBackgroundColor");
        if (obj != null && (obj instanceof String)) {
            try {
                pDFView.setBackgroundColor(Color.parseColor((String) obj));
            } catch (IllegalArgumentException unused) {
            }
        }
        if (configurator != null) {
            configurator.enableSwipe(getBoolean(map, "enableSwipe")).swipeHorizontal(getBoolean(map, "swipeHorizontal")).password(getString(map, URLCredentialContract.FeedEntry.COLUMN_NAME_PASSWORD)).nightMode(getBoolean(map, "nightMode")).autoSpacing(getBoolean(map, "autoSpacing")).pageFling(getBoolean(map, "pageFling")).pageSnap(getBoolean(map, "pageSnap")).pageFitPolicy(getFitPolicy(map)).enableAnnotationRendering(true).linkHandler(this.linkHandler).enableAntialiasing(false).enableDoubletap(true).defaultPage(getInt(map, "defaultPage")).onPageChange(new OnPageChangeListener() { // from class: io.endigo.plugins.pdfviewflutter.FlutterPDFView.4
                @Override // com.github.barteksc.pdfviewer.listener.OnPageChangeListener
                public void onPageChanged(int i2, int i3) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("page", Integer.valueOf(i2));
                    hashMap.put("total", Integer.valueOf(i3));
                    FlutterPDFView.this.methodChannel.invokeMethod("onPageChanged", hashMap);
                }
            }).onError(new OnErrorListener() { // from class: io.endigo.plugins.pdfviewflutter.FlutterPDFView.3
                @Override // com.github.barteksc.pdfviewer.listener.OnErrorListener
                public void onError(Throwable th) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("error", th.toString());
                    FlutterPDFView.this.methodChannel.invokeMethod("onError", hashMap);
                }
            }).onPageError(new OnPageErrorListener() { // from class: io.endigo.plugins.pdfviewflutter.FlutterPDFView.2
                @Override // com.github.barteksc.pdfviewer.listener.OnPageErrorListener
                public void onPageError(int i2, Throwable th) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("page", Integer.valueOf(i2));
                    hashMap.put("error", th.toString());
                    FlutterPDFView.this.methodChannel.invokeMethod("onPageError", hashMap);
                }
            }).onRender(new OnRenderListener() { // from class: io.endigo.plugins.pdfviewflutter.FlutterPDFView.1
                @Override // com.github.barteksc.pdfviewer.listener.OnRenderListener
                public void onInitiallyRendered(int i2) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("pages", Integer.valueOf(i2));
                    FlutterPDFView.this.methodChannel.invokeMethod("onRender", hashMap);
                }
            }).load();
        }
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public View getView() {
        return this.pdfView;
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1990164468:
                if (str.equals("updateSettings")) {
                    c = 0;
                    break;
                }
                break;
            case 601108392:
                if (str.equals("currentPage")) {
                    c = 1;
                    break;
                }
                break;
            case 857882560:
                if (str.equals("pageCount")) {
                    c = 2;
                    break;
                }
                break;
            case 1984860689:
                if (str.equals("setPage")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                updateSettings(methodCall, result);
                return;
            case 1:
                getCurrentPage(result);
                return;
            case 2:
                getPageCount(result);
                return;
            case 3:
                setPage(methodCall, result);
                return;
            default:
                result.notImplemented();
                return;
        }
    }

    void getPageCount(MethodChannel.Result result) {
        result.success(Integer.valueOf(this.pdfView.getPageCount()));
    }

    void getCurrentPage(MethodChannel.Result result) {
        result.success(Integer.valueOf(this.pdfView.getCurrentPage()));
    }

    void setPage(MethodCall methodCall, MethodChannel.Result result) {
        if (methodCall.argument("page") != null) {
            this.pdfView.jumpTo(((Integer) methodCall.argument("page")).intValue());
        }
        result.success(true);
    }

    private void updateSettings(MethodCall methodCall, MethodChannel.Result result) {
        applySettings((Map) methodCall.arguments);
        result.success(null);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0059 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0084 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x009c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0070 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void applySettings(Map<String, Object> map) {
        for (String str : map.keySet()) {
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1439816841:
                    if (str.equals("enableSwipe")) {
                        c = 0;
                    }
                    switch (c) {
                        case 0:
                            this.pdfView.setSwipeEnabled(getBoolean(map, str));
                            break;
                        case 1:
                            ((PDFLinkHandler) this.linkHandler).setPreventLinkNavigation(getBoolean(map, str));
                            break;
                        case 2:
                            this.pdfView.setPageSnap(getBoolean(map, str));
                            break;
                        case 3:
                            this.pdfView.setPageFling(getBoolean(map, str));
                            break;
                        case 4:
                            this.pdfView.setNightMode(getBoolean(map, str));
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown PDFView setting: " + str);
                    }
                case 820702630:
                    if (str.equals("preventLinkNavigation")) {
                        c = 1;
                    }
                    switch (c) {
                    }
                    break;
                case 859432697:
                    if (str.equals("pageSnap")) {
                        c = 2;
                    }
                    switch (c) {
                    }
                    break;
                case 860552205:
                    if (str.equals("pageFling")) {
                        c = 3;
                    }
                    switch (c) {
                    }
                    break;
                case 1365525979:
                    if (str.equals("nightMode")) {
                        c = 4;
                    }
                    switch (c) {
                    }
                    break;
                default:
                    switch (c) {
                    }
                    break;
            }
        }
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public void dispose() {
        this.methodChannel.setMethodCallHandler(null);
    }

    private boolean getBoolean(Map<String, Object> map, String str) {
        if (map.containsKey(str)) {
            return ((Boolean) map.get(str)).booleanValue();
        }
        return false;
    }

    private String getString(Map<String, Object> map, String str) {
        return map.containsKey(str) ? (String) map.get(str) : "";
    }

    private int getInt(Map<String, Object> map, String str) {
        if (map.containsKey(str)) {
            return ((Integer) map.get(str)).intValue();
        }
        return 0;
    }

    private FitPolicy getFitPolicy(Map<String, Object> map) {
        char c;
        String string = getString(map, "fitPolicy");
        int hashCode = string.hashCode();
        if (hashCode == -1620991877) {
            if (string.equals("FitPolicy.WIDTH")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -191456756) {
            if (hashCode == 855864562 && string.equals("FitPolicy.HEIGHT")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (string.equals("FitPolicy.BOTH")) {
                c = 2;
            }
            c = 65535;
        }
        if (c == 0) {
            return FitPolicy.WIDTH;
        }
        if (c == 1) {
            return FitPolicy.HEIGHT;
        }
        return FitPolicy.BOTH;
    }

    private Uri getURI(String str) {
        Uri parse = Uri.parse(str);
        return (parse.getScheme() == null || parse.getScheme().isEmpty()) ? Uri.fromFile(new File(str)) : parse;
    }
}
