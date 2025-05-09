package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.impl.common.PackageUtil;
import java.util.Locale;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.spi.ServiceRegistry;

/* loaded from: classes3.dex */
public class TIFFImageWriterSpi extends ImageWriterSpi {
    private static final String writerClassName = "com.github.jaiimageio.impl.plugins.tiff.TIFFImageWriter";
    private boolean registered;
    private static final String[] names = {"tif", "TIF", "tiff", "TIFF"};
    private static final String[] suffixes = {"tif", "tiff"};
    private static final String[] MIMETypes = {"image/tiff"};
    private static final String[] readerSpiNames = {"com.github.jaiimageio.impl.plugins.tiff.TIFFImageReaderSpi"};

    public boolean canEncodeImage(ImageTypeSpecifier imageTypeSpecifier) {
        return true;
    }

    public TIFFImageWriterSpi() {
        super(PackageUtil.getVendor(), PackageUtil.getVersion(), names, suffixes, MIMETypes, writerClassName, STANDARD_OUTPUT_TYPE, readerSpiNames, false, "com_sun_media_imageio_plugins_tiff_stream_1.0", "com.github.jaiimageio.impl.plugins.tiff.TIFFStreamMetadataFormat", (String[]) null, (String[]) null, false, TIFFImageMetadata.nativeMetadataFormatName, TIFFImageMetadata.nativeMetadataFormatClassName, (String[]) null, (String[]) null);
        this.registered = false;
    }

    public String getDescription(Locale locale) {
        return PackageUtil.getSpecificationTitle() + " TIFF Image Writer";
    }

    public ImageWriter createWriterInstance(Object obj) {
        return new TIFFImageWriter(this);
    }

    public void onRegistration(ServiceRegistry serviceRegistry, Class cls) {
        if (this.registered) {
            return;
        }
        this.registered = true;
    }
}
