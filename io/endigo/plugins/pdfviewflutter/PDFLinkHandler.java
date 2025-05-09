package io.endigo.plugins.pdfviewflutter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.link.LinkHandler;
import com.github.barteksc.pdfviewer.model.LinkTapEvent;
import io.flutter.plugin.common.MethodChannel;

/* loaded from: classes5.dex */
public class PDFLinkHandler implements LinkHandler {
    Context context;
    MethodChannel methodChannel;
    PDFView pdfView;
    boolean preventLinkNavigation;

    public PDFLinkHandler(Context context, PDFView pDFView, MethodChannel methodChannel, boolean z) {
        this.context = context;
        this.pdfView = pDFView;
        this.methodChannel = methodChannel;
        this.preventLinkNavigation = z;
    }

    @Override // com.github.barteksc.pdfviewer.link.LinkHandler
    public void handleLinkEvent(LinkTapEvent linkTapEvent) {
        String uri = linkTapEvent.getLink().getUri();
        Integer destPageIdx = linkTapEvent.getLink().getDestPageIdx();
        if (uri != null && !uri.isEmpty()) {
            handleUri(uri);
        } else if (destPageIdx != null) {
            handlePage(destPageIdx.intValue());
        }
    }

    private void handleUri(String str) {
        if (!this.preventLinkNavigation) {
            Uri parse = Uri.parse(str);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(parse);
            intent.addFlags(268435456);
            intent.addFlags(32768);
            if (intent.resolveActivity(this.context.getPackageManager()) != null) {
                this.context.startActivity(intent, null);
            }
        }
        onLinkHandler(str);
    }

    private void handlePage(int i) {
        this.pdfView.jumpTo(i);
    }

    private void onLinkHandler(String str) {
        this.methodChannel.invokeMethod("onLinkHandler", str);
    }

    public void setPreventLinkNavigation(boolean z) {
        this.preventLinkNavigation = z;
    }
}
