package com.journeyapps.barcodescanner;

import com.google.zxing.ResultPoint;
import java.util.List;

/* loaded from: classes4.dex */
public interface BarcodeCallback {

    /* renamed from: com.journeyapps.barcodescanner.BarcodeCallback$-CC, reason: invalid class name */
    /* loaded from: classes4.dex */
    public final /* synthetic */ class CC {
        public static void $default$possibleResultPoints(BarcodeCallback _this, List list) {
        }
    }

    void barcodeResult(BarcodeResult barcodeResult);

    void possibleResultPoints(List<ResultPoint> list);
}
