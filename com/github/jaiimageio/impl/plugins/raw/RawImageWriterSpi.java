package com.github.jaiimageio.impl.plugins.raw;

import com.github.jaiimageio.impl.common.PackageUtil;
import java.util.Locale;
import javax.imageio.IIOException;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.spi.ServiceRegistry;

/* loaded from: classes3.dex */
public class RawImageWriterSpi extends ImageWriterSpi {
    private boolean registered;
    private static String[] readerSpiNames = {"com.github.jaiimageio.impl.plugins.raw.RawImageReaderSpi"};
    private static String[] formatNames = {"raw", "RAW"};
    private static String[] entensions = {""};
    private static String[] mimeType = {""};

    public boolean canEncodeImage(ImageTypeSpecifier imageTypeSpecifier) {
        return true;
    }

    public RawImageWriterSpi() {
        super(PackageUtil.getVendor(), PackageUtil.getVersion(), formatNames, entensions, mimeType, "com.github.jaiimageio.impl.plugins.raw.RawImageWriter", STANDARD_OUTPUT_TYPE, readerSpiNames, true, (String) null, (String) null, (String[]) null, (String[]) null, true, (String) null, (String) null, (String[]) null, (String[]) null);
        this.registered = false;
    }

    public String getDescription(Locale locale) {
        return PackageUtil.getSpecificationTitle() + " Raw Image Writer";
    }

    public void onRegistration(ServiceRegistry serviceRegistry, Class cls) {
        if (this.registered) {
            return;
        }
        this.registered = true;
    }

    public ImageWriter createWriterInstance(Object obj) throws IIOException {
        return new RawImageWriter(this);
    }
}
