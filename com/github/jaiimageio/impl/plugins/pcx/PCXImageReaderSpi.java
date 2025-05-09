package com.github.jaiimageio.impl.plugins.pcx;

import com.github.jaiimageio.impl.common.PackageUtil;
import java.io.IOException;
import java.util.Locale;
import javax.imageio.IIOException;
import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ServiceRegistry;
import javax.imageio.stream.ImageInputStream;

/* loaded from: classes3.dex */
public class PCXImageReaderSpi extends ImageReaderSpi {
    private boolean registered;
    private static String[] writerSpiNames = {"com.github.jaiimageio.impl.plugins.pcx.PCXImageWriterSpi"};
    private static String[] formatNames = {"pcx", "PCX"};
    private static String[] extensions = {"pcx"};
    private static String[] mimeTypes = {"image/pcx", "image/x-pcx", "image/x-windows-pcx", "image/x-pc-paintbrush"};

    public PCXImageReaderSpi() {
        super(PackageUtil.getVendor(), PackageUtil.getVersion(), formatNames, extensions, mimeTypes, "com.github.jaiimageio.impl.plugins.pcx.PCXImageReader", STANDARD_INPUT_TYPE, writerSpiNames, false, (String) null, (String) null, (String[]) null, (String[]) null, true, (String) null, (String) null, (String[]) null, (String[]) null);
        this.registered = false;
    }

    public void onRegistration(ServiceRegistry serviceRegistry, Class cls) {
        if (this.registered) {
            return;
        }
        this.registered = true;
    }

    public String getDescription(Locale locale) {
        return PackageUtil.getSpecificationTitle() + " PCX Image Reader";
    }

    public boolean canDecodeInput(Object obj) throws IOException {
        if (!(obj instanceof ImageInputStream)) {
            return false;
        }
        ImageInputStream imageInputStream = (ImageInputStream) obj;
        imageInputStream.mark();
        byte readByte = imageInputStream.readByte();
        imageInputStream.reset();
        return readByte == 10;
    }

    public ImageReader createReaderInstance(Object obj) throws IIOException {
        return new PCXImageReader(this);
    }
}
