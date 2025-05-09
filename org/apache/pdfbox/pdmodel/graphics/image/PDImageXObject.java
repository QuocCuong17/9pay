package org.apache.pdfbox.pdmodel.graphics.image;

import android.graphics.Bitmap;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.filter.DecodeResult;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;

/* loaded from: classes5.dex */
public final class PDImageXObject extends PDXObject implements PDImage {
    private Bitmap cachedImage;
    private PDColorSpace colorSpace;
    private PDResources resources;

    public static PDImageXObject createThumbnail(COSStream cOSStream) throws IOException {
        return new PDImageXObject(new PDStream(cOSStream), null);
    }

    public PDImageXObject(PDDocument pDDocument) throws IOException {
        this(new PDStream(pDDocument), null);
    }

    public PDImageXObject(PDDocument pDDocument, InputStream inputStream, COSBase cOSBase, int i, int i2, int i3, PDColorSpace pDColorSpace) throws IOException {
        super(new PDStream(pDDocument, inputStream, true), COSName.IMAGE);
        getCOSStream().setItem(COSName.FILTER, cOSBase);
        this.resources = null;
        this.colorSpace = null;
        setBitsPerComponent(i3);
        setWidth(i);
        setHeight(i2);
        setColorSpace(pDColorSpace);
    }

    public PDImageXObject(PDStream pDStream, PDResources pDResources) throws IOException {
        this(pDStream, pDResources, pDStream.getStream().getDecodeResult());
    }

    private PDImageXObject(PDStream pDStream, PDResources pDResources, DecodeResult decodeResult) {
        super(repair(pDStream, decodeResult), COSName.IMAGE);
        this.resources = pDResources;
    }

    private static PDStream repair(PDStream pDStream, DecodeResult decodeResult) {
        pDStream.getStream().addAll(decodeResult.getParameters());
        return pDStream;
    }

    public PDMetadata getMetadata() {
        COSStream cOSStream = (COSStream) getCOSStream().getDictionaryObject(COSName.METADATA);
        if (cOSStream != null) {
            return new PDMetadata(cOSStream);
        }
        return null;
    }

    public void setMetadata(PDMetadata pDMetadata) {
        getCOSStream().setItem(COSName.METADATA, pDMetadata);
    }

    public int getStructParent() {
        return getCOSStream().getInt(COSName.STRUCT_PARENT, 0);
    }

    public void setStructParent(int i) {
        getCOSStream().setInt(COSName.STRUCT_PARENT, i);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.image.PDImage
    public Bitmap getImage() throws IOException {
        Bitmap bitmap = this.cachedImage;
        if (bitmap != null) {
            return bitmap;
        }
        Bitmap rGBImage = SampledImageReader.getRGBImage(this, getColorKeyMask());
        if (getSoftMask() == null) {
            getMask();
        }
        this.cachedImage = rGBImage;
        return rGBImage;
    }

    public Bitmap getOpaqueImage() throws IOException {
        return SampledImageReader.getRGBImage(this, null);
    }

    public PDImageXObject getMask() throws IOException {
        COSStream cOSStream;
        if ((getCOSStream().getDictionaryObject(COSName.MASK) instanceof COSArray) || (cOSStream = (COSStream) getCOSStream().getDictionaryObject(COSName.MASK)) == null) {
            return null;
        }
        return new PDImageXObject(new PDStream(cOSStream), null);
    }

    public COSArray getColorKeyMask() {
        COSBase dictionaryObject = getCOSStream().getDictionaryObject(COSName.MASK);
        if (dictionaryObject instanceof COSArray) {
            return (COSArray) dictionaryObject;
        }
        return null;
    }

    public PDImageXObject getSoftMask() throws IOException {
        COSStream cOSStream = (COSStream) getCOSStream().getDictionaryObject(COSName.SMASK);
        if (cOSStream != null) {
            return new PDImageXObject(new PDStream(cOSStream), null);
        }
        return null;
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.image.PDImage
    public int getBitsPerComponent() {
        if (isStencil()) {
            return 1;
        }
        Log.d("PdfBoxAndroid", getCOSStream().getInt(COSName.BITS_PER_COMPONENT, COSName.BPC) + "");
        return getCOSStream().getInt(COSName.BITS_PER_COMPONENT, COSName.BPC);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.image.PDImage
    public void setBitsPerComponent(int i) {
        getCOSStream().setInt(COSName.BITS_PER_COMPONENT, i);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.image.PDImage
    public PDColorSpace getColorSpace() throws IOException {
        if (this.colorSpace == null) {
            COSBase dictionaryObject = getCOSStream().getDictionaryObject(COSName.COLORSPACE, COSName.CS);
            if (dictionaryObject != null) {
                this.colorSpace = PDColorSpace.create(dictionaryObject, this.resources);
            } else {
                if (isStencil()) {
                    return PDDeviceGray.INSTANCE;
                }
                throw new IOException("could not determine color space");
            }
        }
        return this.colorSpace;
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.image.PDImage
    public PDStream getStream() throws IOException {
        return getPDStream();
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.image.PDImage
    public void setColorSpace(PDColorSpace pDColorSpace) {
        getCOSStream().setItem(COSName.COLORSPACE, pDColorSpace != null ? pDColorSpace.getCOSObject() : null);
        getCOSStream().setItem(COSName.COLORSPACE, (COSBase) COSName.DEVICERGB);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.image.PDImage
    public int getHeight() {
        return getCOSStream().getInt(COSName.HEIGHT);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.image.PDImage
    public void setHeight(int i) {
        getCOSStream().setInt(COSName.HEIGHT, i);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.image.PDImage
    public int getWidth() {
        return getCOSStream().getInt(COSName.WIDTH);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.image.PDImage
    public void setWidth(int i) {
        getCOSStream().setInt(COSName.WIDTH, i);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.image.PDImage
    public boolean getInterpolate() {
        return getCOSStream().getBoolean(COSName.INTERPOLATE, false);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.image.PDImage
    public void setInterpolate(boolean z) {
        getCOSStream().setBoolean(COSName.INTERPOLATE, z);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.image.PDImage
    public void setDecode(COSArray cOSArray) {
        getCOSStream().setItem(COSName.DECODE, (COSBase) cOSArray);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.image.PDImage
    public COSArray getDecode() {
        COSBase dictionaryObject = getCOSStream().getDictionaryObject(COSName.DECODE);
        if (dictionaryObject instanceof COSArray) {
            return (COSArray) dictionaryObject;
        }
        return null;
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.image.PDImage
    public boolean isStencil() {
        return getCOSStream().getBoolean(COSName.IMAGE_MASK, false);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.image.PDImage
    public void setStencil(boolean z) {
        getCOSStream().setBoolean(COSName.IMAGE_MASK, z);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.image.PDImage
    public String getSuffix() {
        List<COSName> filters = getPDStream().getFilters();
        if (filters == null) {
            return "png";
        }
        if (filters.contains(COSName.DCT_DECODE)) {
            return "jpg";
        }
        if (filters.contains(COSName.JPX_DECODE)) {
            return "jpx";
        }
        if (filters.contains(COSName.CCITTFAX_DECODE)) {
            return "tiff";
        }
        if (filters.contains(COSName.FLATE_DECODE) || filters.contains(COSName.LZW_DECODE) || filters.contains(COSName.RUN_LENGTH_DECODE)) {
            return "png";
        }
        Log.w("PdfBoxAndroid", "getSuffix() returns null, filters: " + filters);
        return null;
    }
}
