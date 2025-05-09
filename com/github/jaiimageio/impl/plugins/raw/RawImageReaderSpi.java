package com.github.jaiimageio.impl.plugins.raw;

import com.github.jaiimageio.impl.common.PackageUtil;
import com.github.jaiimageio.stream.RawImageInputStream;
import java.io.IOException;
import java.util.Locale;
import javax.imageio.IIOException;
import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ServiceRegistry;

/* loaded from: classes3.dex */
public class RawImageReaderSpi extends ImageReaderSpi {
    private boolean registered;
    private static String[] writerSpiNames = {"com.github.jaiimageio.impl.plugins.raw.RawImageWriterSpi"};
    private static String[] formatNames = {"raw", "RAW"};
    private static String[] entensions = {""};
    private static String[] mimeType = {""};

    public RawImageReaderSpi() {
        super(PackageUtil.getVendor(), PackageUtil.getVersion(), formatNames, entensions, mimeType, "com.github.jaiimageio.impl.plugins.raw.RawImageReader", STANDARD_INPUT_TYPE, writerSpiNames, true, (String) null, (String) null, (String[]) null, (String[]) null, true, (String) null, (String) null, (String[]) null, (String[]) null);
        this.registered = false;
    }

    public void onRegistration(ServiceRegistry serviceRegistry, Class cls) {
        if (this.registered) {
            return;
        }
        this.registered = true;
    }

    public String getDescription(Locale locale) {
        return PackageUtil.getSpecificationTitle() + " Raw Image Reader";
    }

    public boolean canDecodeInput(Object obj) throws IOException {
        return obj instanceof RawImageInputStream;
    }

    public ImageReader createReaderInstance(Object obj) throws IIOException {
        return new RawImageReader(this);
    }
}
