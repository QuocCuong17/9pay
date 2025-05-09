package com.github.jaiimageio.impl.plugins.pnm;

import com.github.jaiimageio.impl.common.PackageUtil;
import java.util.Locale;
import javax.imageio.IIOException;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.spi.ServiceRegistry;

/* loaded from: classes3.dex */
public class PNMImageWriterSpi extends ImageWriterSpi {
    private boolean registered;
    private static String[] readerSpiNames = {"com.github.jaiimageio.impl.plugins.pnm.PNMImageReaderSpi"};
    private static String[] formatNames = {"pnm", "PNM"};
    private static String[] entensions = {"pbm", "pgm", "ppm"};
    private static String[] mimeType = {"image/x-portable-anymap", "image/x-portable-bitmap", "image/x-portable-graymap", "image/x-portable-pixmap"};

    public PNMImageWriterSpi() {
        super(PackageUtil.getVendor(), PackageUtil.getVersion(), formatNames, entensions, mimeType, "com.github.jaiimageio.impl.plugins.pnm.PNMImageWriter", STANDARD_OUTPUT_TYPE, readerSpiNames, true, (String) null, (String) null, (String[]) null, (String[]) null, true, (String) null, (String) null, (String[]) null, (String[]) null);
        this.registered = false;
    }

    public String getDescription(Locale locale) {
        return PackageUtil.getSpecificationTitle() + " PNM Image Writer";
    }

    public void onRegistration(ServiceRegistry serviceRegistry, Class cls) {
        if (this.registered) {
            return;
        }
        this.registered = true;
    }

    public boolean canEncodeImage(ImageTypeSpecifier imageTypeSpecifier) {
        int dataType = imageTypeSpecifier.getSampleModel().getDataType();
        if (dataType < 0 || dataType > 3) {
            return false;
        }
        int numBands = imageTypeSpecifier.getSampleModel().getNumBands();
        return numBands == 1 || numBands == 3;
    }

    public ImageWriter createWriterInstance(Object obj) throws IIOException {
        return new PNMImageWriter(this);
    }
}
