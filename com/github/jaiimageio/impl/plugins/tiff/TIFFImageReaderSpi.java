package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.impl.common.PackageUtil;
import java.io.IOException;
import java.util.Locale;
import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ServiceRegistry;
import javax.imageio.stream.ImageInputStream;

/* loaded from: classes3.dex */
public class TIFFImageReaderSpi extends ImageReaderSpi {
    private static final String readerClassName = "com.github.jaiimageio.impl.plugins.tiff.TIFFImageReader";
    private boolean registered;
    private static final String[] names = {"tif", "TIF", "tiff", "TIFF"};
    private static final String[] suffixes = {"tif", "tiff"};
    private static final String[] MIMETypes = {"image/tiff"};
    private static final String[] writerSpiNames = {"com.github.jaiimageio.impl.plugins.tiff.TIFFImageWriterSpi"};

    public TIFFImageReaderSpi() {
        super(PackageUtil.getVendor(), PackageUtil.getVersion(), names, suffixes, MIMETypes, readerClassName, STANDARD_INPUT_TYPE, writerSpiNames, false, "com_sun_media_imageio_plugins_tiff_stream_1.0", "com.github.jaiimageio.impl.plugins.tiff.TIFFStreamMetadataFormat", (String[]) null, (String[]) null, true, TIFFImageMetadata.nativeMetadataFormatName, TIFFImageMetadata.nativeMetadataFormatClassName, (String[]) null, (String[]) null);
        this.registered = false;
    }

    public String getDescription(Locale locale) {
        return PackageUtil.getSpecificationTitle() + " TIFF Image Reader";
    }

    public boolean canDecodeInput(Object obj) throws IOException {
        if (!(obj instanceof ImageInputStream)) {
            return false;
        }
        ImageInputStream imageInputStream = (ImageInputStream) obj;
        byte[] bArr = new byte[4];
        imageInputStream.mark();
        imageInputStream.readFully(bArr);
        imageInputStream.reset();
        return (bArr[0] == 73 && bArr[1] == 73 && bArr[2] == 42 && bArr[3] == 0) || (bArr[0] == 77 && bArr[1] == 77 && bArr[2] == 0 && bArr[3] == 42);
    }

    public ImageReader createReaderInstance(Object obj) {
        return new TIFFImageReader(this);
    }

    public void onRegistration(ServiceRegistry serviceRegistry, Class cls) {
        if (this.registered) {
            return;
        }
        this.registered = true;
    }
}
