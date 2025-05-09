package org.apache.pdfbox.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class PDFBoxResourceLoader {
    private static AssetManager ASSET_MANAGER = null;
    private static Context CONTEXT = null;
    private static boolean hasWarned = false;

    public static void init(Context context) {
        if (CONTEXT == null) {
            Context applicationContext = context.getApplicationContext();
            CONTEXT = applicationContext;
            ASSET_MANAGER = applicationContext.getAssets();
        }
    }

    public static boolean isReady() {
        if (ASSET_MANAGER == null && !hasWarned) {
            Log.w("PdfBoxAndroid", "Call PDFBoxResourceLoader.init() first to decrease resource load time");
            hasWarned = true;
        }
        return ASSET_MANAGER != null;
    }

    public static InputStream getStream(String str) throws IOException {
        return ASSET_MANAGER.open(str);
    }
}
