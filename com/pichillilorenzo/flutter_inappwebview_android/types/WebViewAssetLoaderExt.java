package com.pichillilorenzo.flutter_inappwebview_android.types;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceResponse;
import androidx.webkit.WebViewAssetLoader;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.Util;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.firebase.database.Constants;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class WebViewAssetLoaderExt implements Disposable {
    public List<PathHandlerExt> customPathHandlers;
    public WebViewAssetLoader loader;

    public WebViewAssetLoaderExt(WebViewAssetLoader webViewAssetLoader, List<PathHandlerExt> list) {
        this.loader = webViewAssetLoader;
        this.customPathHandlers = list;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x006a. Please report as an issue. */
    public static WebViewAssetLoaderExt fromMap(Map<String, Object> map, InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin, Context context) {
        if (map == null) {
            return null;
        }
        WebViewAssetLoader.Builder builder = new WebViewAssetLoader.Builder();
        String str = (String) map.get(DynamicLink.Builder.KEY_DOMAIN);
        Boolean bool = (Boolean) map.get("httpAllowed");
        List<Map> list = (List) map.get("pathHandlers");
        ArrayList arrayList = new ArrayList();
        if (str != null && !str.isEmpty()) {
            builder.setDomain(str);
        }
        if (bool != null) {
            builder.setHttpAllowed(bool.booleanValue());
        }
        if (list != null) {
            for (Map map2 : list) {
                String str2 = (String) map2.get("type");
                String str3 = (String) map2.get(Constants.PATH);
                if (str2 != null && str3 != null) {
                    str2.hashCode();
                    char c = 65535;
                    switch (str2.hashCode()) {
                        case -1506882528:
                            if (str2.equals("ResourcesPathHandler")) {
                                c = 0;
                                break;
                            }
                            break;
                        case -667829310:
                            if (str2.equals("AssetsPathHandler")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 1490480039:
                            if (str2.equals("InternalStoragePathHandler")) {
                                c = 2;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            builder.addPathHandler(str3, new WebViewAssetLoader.ResourcesPathHandler(context));
                            break;
                        case 1:
                            builder.addPathHandler(str3, new WebViewAssetLoader.AssetsPathHandler(context));
                            break;
                        case 2:
                            String str4 = (String) map2.get("directory");
                            if (str4 == null) {
                                break;
                            } else {
                                builder.addPathHandler(str3, new WebViewAssetLoader.InternalStoragePathHandler(context, new File(str4)));
                                break;
                            }
                        default:
                            String str5 = (String) map2.get("id");
                            if (str5 == null) {
                                break;
                            } else {
                                PathHandlerExt pathHandlerExt = new PathHandlerExt(str5, inAppWebViewFlutterPlugin);
                                builder.addPathHandler(str3, pathHandlerExt);
                                arrayList.add(pathHandlerExt);
                                break;
                            }
                    }
                }
            }
        }
        return new WebViewAssetLoaderExt(builder.build(), arrayList);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        Iterator<PathHandlerExt> it = this.customPathHandlers.iterator();
        while (it.hasNext()) {
            it.next().dispose();
        }
        this.customPathHandlers.clear();
    }

    /* loaded from: classes5.dex */
    public static class PathHandlerExt implements WebViewAssetLoader.PathHandler, Disposable {
        protected static final String LOG_TAG = "PathHandlerExt";
        public static final String METHOD_CHANNEL_NAME_PREFIX = "com.pichillilorenzo/flutter_inappwebview_custompathhandler_";
        public PathHandlerExtChannelDelegate channelDelegate;

        /* renamed from: id, reason: collision with root package name */
        public String f108id;

        public PathHandlerExt(String str, InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
            this.f108id = str;
            this.channelDelegate = new PathHandlerExtChannelDelegate(this, new MethodChannel(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME_PREFIX + str));
        }

        @Override // androidx.webkit.WebViewAssetLoader.PathHandler
        public WebResourceResponse handle(String str) {
            PathHandlerExtChannelDelegate pathHandlerExtChannelDelegate = this.channelDelegate;
            if (pathHandlerExtChannelDelegate != null) {
                try {
                    WebResourceResponseExt handle = pathHandlerExtChannelDelegate.handle(str);
                    if (handle != null) {
                        String contentType = handle.getContentType();
                        String contentEncoding = handle.getContentEncoding();
                        byte[] data = handle.getData();
                        Map<String, String> headers = handle.getHeaders();
                        Integer statusCode = handle.getStatusCode();
                        String reasonPhrase = handle.getReasonPhrase();
                        ByteArrayInputStream byteArrayInputStream = data != null ? new ByteArrayInputStream(data) : null;
                        if (Build.VERSION.SDK_INT >= 21 && statusCode != null && reasonPhrase != null) {
                            return new WebResourceResponse(contentType, contentEncoding, statusCode.intValue(), reasonPhrase, headers, byteArrayInputStream);
                        }
                        return new WebResourceResponse(contentType, contentEncoding, byteArrayInputStream);
                    }
                } catch (InterruptedException e) {
                    Log.e(LOG_TAG, "", e);
                }
            }
            return null;
        }

        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
        public void dispose() {
            PathHandlerExtChannelDelegate pathHandlerExtChannelDelegate = this.channelDelegate;
            if (pathHandlerExtChannelDelegate != null) {
                pathHandlerExtChannelDelegate.dispose();
                this.channelDelegate = null;
            }
        }
    }

    /* loaded from: classes5.dex */
    public static class PathHandlerExtChannelDelegate extends ChannelDelegateImpl {
        private PathHandlerExt pathHandler;

        public PathHandlerExtChannelDelegate(PathHandlerExt pathHandlerExt, MethodChannel methodChannel) {
            super(methodChannel);
            this.pathHandler = pathHandlerExt;
        }

        /* loaded from: classes5.dex */
        public static class HandleCallback extends BaseCallbackResultImpl<WebResourceResponseExt> {
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public WebResourceResponseExt decodeResult(Object obj) {
                return WebResourceResponseExt.fromMap((Map) obj);
            }
        }

        public void handle(String str, HandleCallback handleCallback) {
            MethodChannel channel = getChannel();
            if (channel == null) {
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put(Constants.PATH, str);
            channel.invokeMethod("handle", hashMap, handleCallback);
        }

        /* loaded from: classes5.dex */
        public static class SyncHandleCallback extends SyncBaseCallbackResultImpl<WebResourceResponseExt> {
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public WebResourceResponseExt decodeResult(Object obj) {
                return new HandleCallback().decodeResult(obj);
            }
        }

        public WebResourceResponseExt handle(String str) throws InterruptedException {
            MethodChannel channel = getChannel();
            if (channel == null) {
                return null;
            }
            SyncHandleCallback syncHandleCallback = new SyncHandleCallback();
            HashMap hashMap = new HashMap();
            hashMap.put(Constants.PATH, str);
            return (WebResourceResponseExt) Util.invokeMethodAndWaitResult(channel, "handle", hashMap, syncHandleCallback);
        }

        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
        public void dispose() {
            super.dispose();
            this.pathHandler = null;
        }
    }
}
