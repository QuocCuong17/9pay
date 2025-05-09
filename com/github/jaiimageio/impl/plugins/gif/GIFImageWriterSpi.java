package com.github.jaiimageio.impl.plugins.gif;

import com.github.jaiimageio.impl.common.ImageUtil;
import com.github.jaiimageio.impl.common.PackageUtil;
import com.github.jaiimageio.impl.common.PaletteBuilder;
import java.awt.image.ColorModel;
import java.awt.image.SampleModel;
import java.util.Locale;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.spi.ServiceRegistry;

/* loaded from: classes3.dex */
public class GIFImageWriterSpi extends ImageWriterSpi {
    private static final String vendorName = "Sun Microsystems, Inc.";
    private static final String version = "1.0";
    private static final String writerClassName = "com.github.jaiimageio.impl.plugins.gif.GIFImageWriter";
    private boolean registered;
    private static final String[] names = {"gif", "GIF"};
    private static final String[] suffixes = {"gif"};
    private static final String[] MIMETypes = {"image/gif"};
    private static final String[] readerSpiNames = {"com.sun.imageio.plugins.gif.GIFImageReaderSpi"};

    public GIFImageWriterSpi() {
        super(vendorName, "1.0", names, suffixes, MIMETypes, writerClassName, STANDARD_OUTPUT_TYPE, readerSpiNames, true, "javax_imageio_gif_stream_1.0", "com.github.jaiimageio.impl.plugins.gif.GIFStreamMetadataFormat", (String[]) null, (String[]) null, true, "javax_imageio_gif_image_1.0", "com.github.jaiimageio.impl.plugins.gif.GIFStreamMetadataFormat", (String[]) null, (String[]) null);
        this.registered = false;
    }

    public boolean canEncodeImage(ImageTypeSpecifier imageTypeSpecifier) {
        if (imageTypeSpecifier == null) {
            throw new IllegalArgumentException("type == null!");
        }
        SampleModel sampleModel = imageTypeSpecifier.getSampleModel();
        ColorModel colorModel = imageTypeSpecifier.getColorModel();
        boolean z = false;
        if (sampleModel.getNumBands() == 1 && sampleModel.getSampleSize(0) <= 8 && sampleModel.getWidth() <= 65535 && sampleModel.getHeight() <= 65535 && (colorModel == null || colorModel.getComponentSize()[0] <= 8)) {
            z = true;
        }
        if (z) {
            return true;
        }
        return PaletteBuilder.canCreatePalette(imageTypeSpecifier);
    }

    public String getDescription(Locale locale) {
        return PackageUtil.getSpecificationTitle() + " GIF Image Writer";
    }

    public void onRegistration(ServiceRegistry serviceRegistry, Class cls) {
        if (this.registered) {
            return;
        }
        this.registered = true;
        ImageUtil.processOnRegistration(serviceRegistry, cls, "GIF", this, 9, 8);
    }

    public ImageWriter createWriterInstance(Object obj) {
        return new GIFImageWriter(this);
    }
}
