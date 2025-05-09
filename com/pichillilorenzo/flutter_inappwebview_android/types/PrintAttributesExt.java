package com.pichillilorenzo.flutter_inappwebview_android.types;

import android.os.Build;
import android.print.PrintAttributes;
import io.sentry.protocol.Device;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class PrintAttributesExt {
    private int colorMode;
    private Integer duplex;
    private MarginsExt margins;
    private MediaSizeExt mediaSize;
    private Integer orientation;
    private ResolutionExt resolution;

    public static PrintAttributesExt fromPrintAttributes(PrintAttributes printAttributes) {
        if (printAttributes == null) {
            return null;
        }
        PrintAttributesExt printAttributesExt = new PrintAttributesExt();
        printAttributesExt.colorMode = printAttributes.getColorMode();
        if (Build.VERSION.SDK_INT >= 23) {
            printAttributesExt.duplex = Integer.valueOf(printAttributes.getDuplexMode());
        }
        PrintAttributes.MediaSize mediaSize = printAttributes.getMediaSize();
        if (mediaSize != null) {
            printAttributesExt.mediaSize = MediaSizeExt.fromMediaSize(mediaSize);
            printAttributesExt.orientation = Integer.valueOf(!mediaSize.isPortrait() ? 1 : 0);
        }
        printAttributesExt.resolution = ResolutionExt.fromResolution(printAttributes.getResolution());
        printAttributesExt.margins = MarginsExt.fromMargins(printAttributes.getMinMargins());
        return printAttributesExt;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("colorMode", Integer.valueOf(this.colorMode));
        hashMap.put("duplex", this.duplex);
        hashMap.put(Device.JsonKeys.ORIENTATION, this.orientation);
        MediaSizeExt mediaSizeExt = this.mediaSize;
        hashMap.put("mediaSize", mediaSizeExt != null ? mediaSizeExt.toMap() : null);
        ResolutionExt resolutionExt = this.resolution;
        hashMap.put("resolution", resolutionExt != null ? resolutionExt.toMap() : null);
        MarginsExt marginsExt = this.margins;
        hashMap.put("margins", marginsExt != null ? marginsExt.toMap() : null);
        return hashMap;
    }
}
