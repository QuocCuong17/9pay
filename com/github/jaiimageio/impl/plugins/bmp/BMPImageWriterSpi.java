package com.github.jaiimageio.impl.plugins.bmp;

import androidx.media3.common.MimeTypes;
import com.github.jaiimageio.impl.common.ImageUtil;
import com.github.jaiimageio.impl.common.PackageUtil;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.util.Locale;
import javax.imageio.IIOException;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.spi.ServiceRegistry;

/* loaded from: classes3.dex */
public class BMPImageWriterSpi extends ImageWriterSpi {
    private boolean registered;
    private static String[] readerSpiNames = {"com.github.jaiimageio.impl.plugins.bmp.BMPImageReaderSpi"};
    private static String[] formatNames = {"bmp", "BMP"};
    private static String[] extensions = {"bmp"};
    private static String[] mimeTypes = {MimeTypes.IMAGE_BMP, "image/x-bmp", "image/x-windows-bmp"};

    public BMPImageWriterSpi() {
        super(PackageUtil.getVendor(), PackageUtil.getVersion(), formatNames, extensions, mimeTypes, "com.github.jaiimageio.impl.plugins.bmp.BMPImageWriter", STANDARD_OUTPUT_TYPE, readerSpiNames, false, (String) null, (String) null, (String[]) null, (String[]) null, true, BMPMetadata.nativeMetadataFormatName, "com.github.jaiimageio.impl.plugins.bmp.BMPMetadataFormat", (String[]) null, (String[]) null);
        this.registered = false;
    }

    public String getDescription(Locale locale) {
        return PackageUtil.getSpecificationTitle() + " BMP Image Writer";
    }

    public void onRegistration(ServiceRegistry serviceRegistry, Class cls) {
        if (this.registered) {
            return;
        }
        this.registered = true;
        ImageUtil.processOnRegistration(serviceRegistry, cls, "BMP", this, 8, 7);
    }

    public boolean canEncodeImage(ImageTypeSpecifier imageTypeSpecifier) {
        int dataType = imageTypeSpecifier.getSampleModel().getDataType();
        if (dataType < 0 || dataType > 3) {
            return false;
        }
        SampleModel sampleModel = imageTypeSpecifier.getSampleModel();
        int numBands = sampleModel.getNumBands();
        if (numBands != 1 && numBands != 3) {
            return false;
        }
        if (numBands != 1 || dataType == 0) {
            return dataType <= 0 || (sampleModel instanceof SinglePixelPackedSampleModel);
        }
        return false;
    }

    public ImageWriter createWriterInstance(Object obj) throws IIOException {
        return new BMPImageWriter(this);
    }
}
