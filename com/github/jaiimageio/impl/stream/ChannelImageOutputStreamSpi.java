package com.github.jaiimageio.impl.stream;

import com.github.jaiimageio.impl.common.PackageUtil;
import com.github.jaiimageio.stream.FileChannelImageOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.NonReadableChannelException;
import java.nio.channels.WritableByteChannel;
import java.util.Locale;
import javax.imageio.spi.ImageOutputStreamSpi;
import javax.imageio.stream.FileCacheImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;

/* loaded from: classes3.dex */
public class ChannelImageOutputStreamSpi extends ImageOutputStreamSpi {
    public String getDescription(Locale locale) {
        return "NIO Channel ImageOutputStream";
    }

    public ChannelImageOutputStreamSpi() {
        super(PackageUtil.getVendor(), PackageUtil.getVersion(), WritableByteChannel.class);
    }

    public ImageOutputStream createOutputStreamInstance(Object obj, boolean z, File file) throws IOException {
        if (obj == null || !(obj instanceof WritableByteChannel)) {
            throw new IllegalArgumentException("Cannot create ImageOutputStream from " + obj.getClass().getName());
        }
        ImageOutputStream imageOutputStream = null;
        if (obj instanceof FileChannel) {
            FileChannel fileChannel = (FileChannel) obj;
            try {
                fileChannel.map(FileChannel.MapMode.READ_ONLY, fileChannel.position(), 1L);
                imageOutputStream = new FileChannelImageOutputStream((FileChannel) obj);
            } catch (NonReadableChannelException unused) {
            }
        }
        if (imageOutputStream != null) {
            return imageOutputStream;
        }
        OutputStream newOutputStream = Channels.newOutputStream((WritableByteChannel) obj);
        if (z) {
            try {
                imageOutputStream = new FileCacheImageOutputStream(newOutputStream, file);
            } catch (IOException unused2) {
            }
        }
        return imageOutputStream == null ? new MemoryCacheImageOutputStream(newOutputStream) : imageOutputStream;
    }
}
