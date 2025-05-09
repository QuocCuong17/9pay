package co.hyperverge.hypersnapsdk.providers;

import co.hyperverge.hypersnapsdk.listeners.BrandingCompletionCallback;
import co.hyperverge.hypersnapsdk.listeners.DocCaptureCompletionHandler;
import co.hyperverge.hypersnapsdk.listeners.FaceCaptureCompletionHandler;
import co.hyperverge.hypersnapsdk.listeners.QRCodeCompletionHandler;
import co.hyperverge.hypersnapsdk.listeners.QRScannerCompletionHandler;

/* loaded from: classes2.dex */
public class CallbackProvider {
    private static CallbackProvider provider;
    private BrandingCompletionCallback brandingCompletionCallback;
    private DocCaptureCompletionHandler documentImageListener;
    private FaceCaptureCompletionHandler faceCaptureCompletionHandler;
    private QRCodeCompletionHandler qrCodeCompletionHandler;
    private QRScannerCompletionHandler qrScannerCompletionHandler;

    private CallbackProvider() {
    }

    public static CallbackProvider get() {
        if (provider == null) {
            provider = new CallbackProvider();
        }
        return provider;
    }

    public void setCallback(DocCaptureCompletionHandler docCaptureCompletionHandler) {
        this.documentImageListener = docCaptureCompletionHandler;
    }

    public void setCallback(FaceCaptureCompletionHandler faceCaptureCompletionHandler) {
        this.faceCaptureCompletionHandler = faceCaptureCompletionHandler;
    }

    public void setCallback(QRCodeCompletionHandler qRCodeCompletionHandler) {
        this.qrCodeCompletionHandler = qRCodeCompletionHandler;
    }

    public void setCallback(QRScannerCompletionHandler qRScannerCompletionHandler) {
        this.qrScannerCompletionHandler = qRScannerCompletionHandler;
    }

    public void setCallback(BrandingCompletionCallback brandingCompletionCallback) {
        this.brandingCompletionCallback = brandingCompletionCallback;
    }

    public DocCaptureCompletionHandler injectDocCaptureCallback() {
        return this.documentImageListener;
    }

    public FaceCaptureCompletionHandler injectFaceCaptureCallback() {
        return this.faceCaptureCompletionHandler;
    }

    public QRCodeCompletionHandler injectQRCodeCallback() {
        return this.qrCodeCompletionHandler;
    }

    public QRScannerCompletionHandler injectQRScannerCallback() {
        return this.qrScannerCompletionHandler;
    }

    public BrandingCompletionCallback injectBrandingCallback() {
        return this.brandingCompletionCallback;
    }
}
