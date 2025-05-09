package com.github.jaiimageio.impl.plugins.wbmp;

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
public class WBMPImageReaderSpi extends ImageReaderSpi {
    private boolean registered;
    private static String[] writerSpiNames = {"com.github.jaiimageio.impl.plugins.wbmp.WBMPImageWriterSpi"};
    private static String[] formatNames = {"wbmp", "WBMP"};
    private static String[] entensions = {"wbmp"};
    private static String[] mimeType = {"image/vnd.wap.wbmp"};

    public WBMPImageReaderSpi() {
        super(PackageUtil.getVendor(), PackageUtil.getVersion(), formatNames, entensions, mimeType, "com.github.jaiimageio.impl.plugins.wbmp.WBMPImageReader", STANDARD_INPUT_TYPE, writerSpiNames, true, (String) null, (String) null, (String[]) null, (String[]) null, true, WBMPMetadata.nativeMetadataFormatName, "com.github.jaiimageio.impl.plugins.wbmp.WBMPMetadataFormat", (String[]) null, (String[]) null);
        this.registered = false;
    }

    public void onRegistration(ServiceRegistry serviceRegistry, Class cls) {
        if (this.registered) {
            return;
        }
        this.registered = true;
        ImageUtil.processOnRegistration(serviceRegistry, cls, "WBMP", this, 8, 7);
    }

    public String getDescription(Locale locale) {
        return PackageUtil.getSpecificationTitle() + " WBMP Image Reader";
    }

    public boolean canDecodeInput(Object obj) throws IOException {
        if (!(obj instanceof ImageInputStream)) {
            return false;
        }
        ImageInputStream imageInputStream = (ImageInputStream) obj;
        imageInputStream.mark();
        byte readByte = imageInputStream.readByte();
        byte readByte2 = imageInputStream.readByte();
        int readMultiByteInteger = ImageUtil.readMultiByteInteger(imageInputStream);
        int readMultiByteInteger2 = ImageUtil.readMultiByteInteger(imageInputStream);
        long length = imageInputStream.length() - imageInputStream.getStreamPosition();
        imageInputStream.reset();
        if (readByte != 0 || readByte2 != 0 || readMultiByteInteger <= 0 || readMultiByteInteger2 <= 0) {
            return false;
        }
        return length == ((long) ((readMultiByteInteger / 8) + (readMultiByteInteger % 8 == 0 ? 0 : 1))) * ((long) readMultiByteInteger2);
    }

    public ImageReader createReaderInstance(Object obj) throws IIOException {
        return new WBMPImageReader(this);
    }
}
