package com.github.jaiimageio.impl.plugins.wbmp;

import com.github.jaiimageio.impl.common.ImageUtil;
import com.github.jaiimageio.impl.common.PackageUtil;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.SampleModel;
import java.util.Locale;
import javax.imageio.IIOException;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.spi.ServiceRegistry;

/* loaded from: classes3.dex */
public class WBMPImageWriterSpi extends ImageWriterSpi {
    private boolean registered;
    private static String[] readerSpiNames = {"com.github.jaiimageio.impl.plugins.wbmp.WBMPImageReaderSpi"};
    private static String[] formatNames = {"wbmp", "WBMP"};
    private static String[] entensions = {"wbmp"};
    private static String[] mimeType = {"image/vnd.wap.wbmp"};

    public WBMPImageWriterSpi() {
        super(PackageUtil.getVendor(), PackageUtil.getVersion(), formatNames, entensions, mimeType, "com.github.jaiimageio.impl.plugins.wbmp.WBMPImageWriter", STANDARD_OUTPUT_TYPE, readerSpiNames, true, (String) null, (String) null, (String[]) null, (String[]) null, true, WBMPMetadata.nativeMetadataFormatName, "com.github.jaiimageio.impl.plugins.wbmp.WBMPMetadataFormat", (String[]) null, (String[]) null);
        this.registered = false;
    }

    public String getDescription(Locale locale) {
        return PackageUtil.getSpecificationTitle() + " WBMP Image Writer";
    }

    public void onRegistration(ServiceRegistry serviceRegistry, Class cls) {
        if (this.registered) {
            return;
        }
        this.registered = true;
        ImageUtil.processOnRegistration(serviceRegistry, cls, "WBMP", this, 8, 7);
    }

    public boolean canEncodeImage(ImageTypeSpecifier imageTypeSpecifier) {
        SampleModel sampleModel = imageTypeSpecifier.getSampleModel();
        return (sampleModel instanceof MultiPixelPackedSampleModel) && sampleModel.getSampleSize(0) == 1;
    }

    public ImageWriter createWriterInstance(Object obj) throws IIOException {
        return new WBMPImageWriter(this);
    }
}
