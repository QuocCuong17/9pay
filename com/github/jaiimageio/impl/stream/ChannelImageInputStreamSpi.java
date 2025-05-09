package com.github.jaiimageio.impl.stream;

import com.github.jaiimageio.impl.common.PackageUtil;
import com.github.jaiimageio.stream.FileChannelImageInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Locale;
import javax.imageio.spi.ImageInputStreamSpi;
import javax.imageio.stream.FileCacheImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;

/* loaded from: classes3.dex */
public class ChannelImageInputStreamSpi extends ImageInputStreamSpi {
    public String getDescription(Locale locale) {
        return "NIO Channel ImageInputStream";
    }

    public ChannelImageInputStreamSpi() {
        super(PackageUtil.getVendor(), PackageUtil.getVersion(), ReadableByteChannel.class);
    }

    public ImageInputStream createInputStreamInstance(Object obj, boolean z, File file) throws IOException {
        if (obj == null || !(obj instanceof ReadableByteChannel)) {
            throw new IllegalArgumentException("XXX");
        }
        ImageInputStream imageInputStream = null;
        if (obj instanceof FileChannel) {
            return new FileChannelImageInputStream((FileChannel) obj);
        }
        InputStream newInputStream = Channels.newInputStream((ReadableByteChannel) obj);
        if (z) {
            try {
                imageInputStream = new FileCacheImageInputStream(newInputStream, file);
            } catch (IOException unused) {
            }
        }
        return imageInputStream == null ? new MemoryCacheImageInputStream(newInputStream) : imageInputStream;
    }
}
