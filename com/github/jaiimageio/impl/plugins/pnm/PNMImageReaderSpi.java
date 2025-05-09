package com.github.jaiimageio.impl.plugins.pnm;

import com.github.jaiimageio.impl.common.PackageUtil;
import java.io.IOException;
import java.util.Locale;
import javax.imageio.IIOException;
import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ServiceRegistry;
import javax.imageio.stream.ImageInputStream;

/* loaded from: classes3.dex */
public class PNMImageReaderSpi extends ImageReaderSpi {
    private boolean registered;
    private static String[] writerSpiNames = {"com.github.jaiimageio.impl.plugins.pnm.PNMImageWriterSpi"};
    private static String[] formatNames = {"pnm", "PNM"};
    private static String[] entensions = {"pbm", "pgm", "ppm"};
    private static String[] mimeType = {"image/x-portable-anymap", "image/x-portable-bitmap", "image/x-portable-graymap", "image/x-portable-pixmap"};

    public PNMImageReaderSpi() {
        super(PackageUtil.getVendor(), PackageUtil.getVersion(), formatNames, entensions, mimeType, "com.github.jaiimageio.impl.plugins.pnm.PNMImageReader", STANDARD_INPUT_TYPE, writerSpiNames, true, (String) null, (String) null, (String[]) null, (String[]) null, true, (String) null, (String) null, (String[]) null, (String[]) null);
        this.registered = false;
    }

    public void onRegistration(ServiceRegistry serviceRegistry, Class cls) {
        if (this.registered) {
            return;
        }
        this.registered = true;
    }

    public String getDescription(Locale locale) {
        return PackageUtil.getSpecificationTitle() + " PNM Image Reader";
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
        return bArr[0] == 80 && bArr[1] >= 49 && bArr[1] <= 54;
    }

    public ImageReader createReaderInstance(Object obj) throws IIOException {
        return new PNMImageReader(this);
    }
}
