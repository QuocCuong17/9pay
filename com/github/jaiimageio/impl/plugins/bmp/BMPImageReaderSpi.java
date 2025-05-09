package com.github.jaiimageio.impl.plugins.bmp;

import androidx.media3.common.MimeTypes;
import com.github.jaiimageio.impl.common.ImageUtil;
import com.github.jaiimageio.impl.common.PackageUtil;
import java.io.IOException;
import java.util.Locale;
import javax.imageio.IIOException;
import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ServiceRegistry;
import javax.imageio.stream.ImageInputStream;

/* loaded from: classes3.dex */
public class BMPImageReaderSpi extends ImageReaderSpi {
    private boolean registered;
    private static String[] writerSpiNames = {"com.github.jaiimageio.impl.plugins.bmp.BMPImageWriterSpi"};
    private static String[] formatNames = {"bmp", "BMP"};
    private static String[] extensions = {"bmp"};
    private static String[] mimeTypes = {MimeTypes.IMAGE_BMP, "image/x-bmp", "image/x-windows-bmp"};

    public BMPImageReaderSpi() {
        super(PackageUtil.getVendor(), PackageUtil.getVersion(), formatNames, extensions, mimeTypes, "com.github.jaiimageio.impl.plugins.bmp.BMPImageReader", STANDARD_INPUT_TYPE, writerSpiNames, false, (String) null, (String) null, (String[]) null, (String[]) null, true, BMPMetadata.nativeMetadataFormatName, "com.github.jaiimageio.impl.plugins.bmp.BMPMetadataFormat", (String[]) null, (String[]) null);
        this.registered = false;
    }

    public void onRegistration(ServiceRegistry serviceRegistry, Class cls) {
        if (this.registered) {
            return;
        }
        this.registered = true;
        ImageUtil.processOnRegistration(serviceRegistry, cls, "BMP", this, 8, 7);
    }

    public String getDescription(Locale locale) {
        return PackageUtil.getSpecificationTitle() + " BMP Image Reader";
    }

    public boolean canDecodeInput(Object obj) throws IOException {
        if (!(obj instanceof ImageInputStream)) {
            return false;
        }
        ImageInputStream imageInputStream = (ImageInputStream) obj;
        byte[] bArr = new byte[2];
        imageInputStream.mark();
        imageInputStream.readFully(bArr);
        imageInputStream.reset();
        return bArr[0] == 66 && bArr[1] == 77;
    }

    public ImageReader createReaderInstance(Object obj) throws IIOException {
        return new BMPImageReader(this);
    }
}
