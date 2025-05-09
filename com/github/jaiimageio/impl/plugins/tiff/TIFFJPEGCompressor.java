package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.TIFFField;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.IIORegistry;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ServiceRegistry;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;

/* loaded from: classes3.dex */
public class TIFFJPEGCompressor extends TIFFBaseJPEGCompressor {
    static final int CHROMA_SUBSAMPLING = 2;
    private static final boolean DEBUG = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class JPEGSPIFilter implements ServiceRegistry.Filter {
        JPEGSPIFilter() {
        }

        public boolean filter(Object obj) {
            String nativeStreamMetadataFormatName;
            ImageReaderSpi imageReaderSpi = (ImageReaderSpi) obj;
            if (imageReaderSpi == null || (nativeStreamMetadataFormatName = imageReaderSpi.getNativeStreamMetadataFormatName()) == null) {
                return false;
            }
            return nativeStreamMetadataFormatName.equals("javax_imageio_jpeg_stream_1.0");
        }
    }

    private static ImageReader getJPEGTablesReader() {
        try {
            Iterator serviceProviders = IIORegistry.getDefaultInstance().getServiceProviders(Class.forName("javax.imageio.spi.ImageReaderSpi"), new JPEGSPIFilter(), true);
            if (serviceProviders.hasNext()) {
                return ((ImageReaderSpi) serviceProviders.next()).createReaderInstance();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public TIFFJPEGCompressor(ImageWriteParam imageWriteParam) {
        super("JPEG", 7, false, imageWriteParam);
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFCompressor
    public void setMetadata(IIOMetadata iIOMetadata) {
        super.setMetadata(iIOMetadata);
        if (iIOMetadata instanceof TIFFImageMetadata) {
            TIFFImageMetadata tIFFImageMetadata = (TIFFImageMetadata) iIOMetadata;
            TIFFIFD rootIFD = tIFFImageMetadata.getRootIFD();
            BaselineTIFFTagSet baselineTIFFTagSet = BaselineTIFFTagSet.getInstance();
            if (tIFFImageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_SAMPLES_PER_PIXEL).getAsInt(0) == 1) {
                rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_Y_CB_CR_SUBSAMPLING);
                rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_Y_CB_CR_POSITIONING);
                rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_REFERENCE_BLACK_WHITE);
            } else {
                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_Y_CB_CR_SUBSAMPLING), 3, 2, new char[]{2, 2}));
                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_Y_CB_CR_POSITIONING), 3, 1, new char[]{1}));
                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_REFERENCE_BLACK_WHITE), 5, 6, new long[][]{new long[]{0, 1}, new long[]{255, 1}, new long[]{128, 1}, new long[]{255, 1}, new long[]{128, 1}, new long[]{255, 1}}));
            }
            TIFFField tIFFField = tIFFImageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_JPEG_TABLES);
            if (tIFFField != null) {
                initJPEGWriter(true, false);
            }
            if (tIFFField != null && this.JPEGWriter != null) {
                this.writeAbbreviatedStream = true;
                if (tIFFField.getCount() > 0) {
                    MemoryCacheImageInputStream memoryCacheImageInputStream = new MemoryCacheImageInputStream(new ByteArrayInputStream(tIFFField.getAsBytes()));
                    ImageReader jPEGTablesReader = getJPEGTablesReader();
                    jPEGTablesReader.setInput(memoryCacheImageInputStream);
                    try {
                        try {
                            this.JPEGStreamMetadata = jPEGTablesReader.getStreamMetadata();
                        } catch (Exception unused) {
                            this.JPEGStreamMetadata = null;
                        }
                    } finally {
                        jPEGTablesReader.reset();
                    }
                }
                if (this.JPEGStreamMetadata == null) {
                    this.JPEGStreamMetadata = this.JPEGWriter.getDefaultStreamMetadata(this.JPEGParam);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    MemoryCacheImageOutputStream memoryCacheImageOutputStream = new MemoryCacheImageOutputStream(byteArrayOutputStream);
                    this.JPEGWriter.setOutput(memoryCacheImageOutputStream);
                    try {
                        this.JPEGWriter.prepareWriteSequence(this.JPEGStreamMetadata);
                        memoryCacheImageOutputStream.flush();
                        this.JPEGWriter.endWriteSequence();
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_JPEG_TABLES), 7, byteArray.length, byteArray));
                        return;
                    } catch (Exception unused2) {
                        rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_JPEG_TABLES);
                        this.writeAbbreviatedStream = false;
                        return;
                    }
                }
                return;
            }
            rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_JPEG_TABLES);
            initJPEGWriter(false, false);
        }
    }
}
