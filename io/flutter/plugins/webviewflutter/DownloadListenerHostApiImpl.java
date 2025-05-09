package io.flutter.plugins.webviewflutter;

import android.webkit.DownloadListener;
import io.flutter.plugins.webviewflutter.DownloadListenerHostApiImpl;
import io.flutter.plugins.webviewflutter.GeneratedAndroidWebView;

/* loaded from: classes5.dex */
public class DownloadListenerHostApiImpl implements GeneratedAndroidWebView.DownloadListenerHostApi {
    private final DownloadListenerCreator downloadListenerCreator;
    private final DownloadListenerFlutterApiImpl flutterApi;
    private final InstanceManager instanceManager;

    /* loaded from: classes5.dex */
    public static class DownloadListenerImpl implements DownloadListener, Releasable {
        private DownloadListenerFlutterApiImpl flutterApi;

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onDownloadStart$0(Void r0) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$release$1(Void r0) {
        }

        public DownloadListenerImpl(DownloadListenerFlutterApiImpl downloadListenerFlutterApiImpl) {
            this.flutterApi = downloadListenerFlutterApiImpl;
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            DownloadListenerFlutterApiImpl downloadListenerFlutterApiImpl = this.flutterApi;
            if (downloadListenerFlutterApiImpl != null) {
                downloadListenerFlutterApiImpl.onDownloadStart(this, str, str2, str3, str4, j, new GeneratedAndroidWebView.DownloadListenerFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.DownloadListenerHostApiImpl$DownloadListenerImpl$$ExternalSyntheticLambda0
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.DownloadListenerFlutterApi.Reply
                    public final void reply(Object obj) {
                        DownloadListenerHostApiImpl.DownloadListenerImpl.lambda$onDownloadStart$0((Void) obj);
                    }
                });
            }
        }

        @Override // io.flutter.plugins.webviewflutter.Releasable
        public void release() {
            DownloadListenerFlutterApiImpl downloadListenerFlutterApiImpl = this.flutterApi;
            if (downloadListenerFlutterApiImpl != null) {
                downloadListenerFlutterApiImpl.dispose(this, new GeneratedAndroidWebView.DownloadListenerFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.DownloadListenerHostApiImpl$DownloadListenerImpl$$ExternalSyntheticLambda1
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.DownloadListenerFlutterApi.Reply
                    public final void reply(Object obj) {
                        DownloadListenerHostApiImpl.DownloadListenerImpl.lambda$release$1((Void) obj);
                    }
                });
            }
            this.flutterApi = null;
        }
    }

    /* loaded from: classes5.dex */
    public static class DownloadListenerCreator {
        public DownloadListenerImpl createDownloadListener(DownloadListenerFlutterApiImpl downloadListenerFlutterApiImpl) {
            return new DownloadListenerImpl(downloadListenerFlutterApiImpl);
        }
    }

    public DownloadListenerHostApiImpl(InstanceManager instanceManager, DownloadListenerCreator downloadListenerCreator, DownloadListenerFlutterApiImpl downloadListenerFlutterApiImpl) {
        this.instanceManager = instanceManager;
        this.downloadListenerCreator = downloadListenerCreator;
        this.flutterApi = downloadListenerFlutterApiImpl;
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.DownloadListenerHostApi
    public void create(Long l) {
        this.instanceManager.addDartCreatedInstance(this.downloadListenerCreator.createDownloadListener(this.flutterApi), l.longValue());
    }
}
