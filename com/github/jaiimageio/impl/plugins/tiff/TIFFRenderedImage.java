package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.TIFFImageReadParam;
import com.github.jaiimageio.plugins.tiff.TIFFTagSet;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageTypeSpecifier;

/* loaded from: classes3.dex */
public class TIFFRenderedImage implements RenderedImage {
    int height;
    int imageIndex;
    boolean isSubsampling;
    ImageTypeSpecifier its;
    TIFFImageReader reader;
    int subsampleX;
    int subsampleY;
    int tileHeight;
    ImageReadParam tileParam;
    int tileWidth;
    int width;

    public int getMinTileX() {
        return 0;
    }

    public int getMinTileY() {
        return 0;
    }

    public int getMinX() {
        return 0;
    }

    public int getMinY() {
        return 0;
    }

    public String[] getPropertyNames() {
        return null;
    }

    public Vector getSources() {
        return null;
    }

    public int getTileGridXOffset() {
        return 0;
    }

    public int getTileGridYOffset() {
        return 0;
    }

    public TIFFRenderedImage(TIFFImageReader tIFFImageReader, int i, ImageReadParam imageReadParam, int i2, int i3) throws IOException {
        this.reader = tIFFImageReader;
        this.imageIndex = i;
        ImageReadParam cloneImageReadParam = cloneImageReadParam(imageReadParam, false);
        this.tileParam = cloneImageReadParam;
        this.subsampleX = cloneImageReadParam.getSourceXSubsampling();
        int sourceYSubsampling = this.tileParam.getSourceYSubsampling();
        this.subsampleY = sourceYSubsampling;
        int i4 = this.subsampleX;
        this.isSubsampling = (i4 == 1 && sourceYSubsampling == 1) ? false : true;
        this.width = i2 / i4;
        this.height = i3 / sourceYSubsampling;
        this.tileWidth = tIFFImageReader.getTileWidth(i) / this.subsampleX;
        this.tileHeight = tIFFImageReader.getTileHeight(i) / this.subsampleY;
        ImageTypeSpecifier imageTypeSpecifier = (ImageTypeSpecifier) tIFFImageReader.getImageTypes(i).next();
        this.its = imageTypeSpecifier;
        this.tileParam.setDestinationType(imageTypeSpecifier);
    }

    private ImageReadParam cloneImageReadParam(ImageReadParam imageReadParam, boolean z) {
        List allowedTagSets;
        Iterator it;
        TIFFImageReadParam tIFFImageReadParam = new TIFFImageReadParam();
        tIFFImageReadParam.setSourceSubsampling(imageReadParam.getSourceXSubsampling(), imageReadParam.getSourceYSubsampling(), imageReadParam.getSubsamplingXOffset(), imageReadParam.getSubsamplingYOffset());
        tIFFImageReadParam.setSourceBands(imageReadParam.getSourceBands());
        tIFFImageReadParam.setDestinationBands(imageReadParam.getDestinationBands());
        tIFFImageReadParam.setDestinationOffset(imageReadParam.getDestinationOffset());
        if (imageReadParam instanceof TIFFImageReadParam) {
            TIFFImageReadParam tIFFImageReadParam2 = (TIFFImageReadParam) imageReadParam;
            tIFFImageReadParam.setTIFFDecompressor(tIFFImageReadParam2.getTIFFDecompressor());
            tIFFImageReadParam.setColorConverter(tIFFImageReadParam2.getColorConverter());
            if (z && (allowedTagSets = tIFFImageReadParam2.getAllowedTagSets()) != null && (it = allowedTagSets.iterator()) != null) {
                while (it.hasNext()) {
                    tIFFImageReadParam.addAllowedTagSet((TIFFTagSet) it.next());
                }
            }
        } else {
            tIFFImageReadParam.setTIFFDecompressor(null);
            tIFFImageReadParam.setColorConverter(null);
        }
        return tIFFImageReadParam;
    }

    public Object getProperty(String str) {
        return Image.UndefinedProperty;
    }

    public ColorModel getColorModel() {
        return this.its.getColorModel();
    }

    public SampleModel getSampleModel() {
        return this.its.getSampleModel();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getNumXTiles() {
        int i = this.width;
        return ((i + r1) - 1) / this.tileWidth;
    }

    public int getNumYTiles() {
        int i = this.height;
        return ((i + r1) - 1) / this.tileHeight;
    }

    public int getTileWidth() {
        return this.tileWidth;
    }

    public int getTileHeight() {
        return this.tileHeight;
    }

    public Raster getTile(int i, int i2) {
        int i3 = this.tileWidth;
        int i4 = this.tileHeight;
        return getData(new Rectangle(i * i3, i2 * i4, i3, i4));
    }

    public Raster getData() {
        return read(new Rectangle(0, 0, getWidth(), getHeight()));
    }

    public Raster getData(Rectangle rectangle) {
        return read(rectangle);
    }

    public synchronized WritableRaster read(Rectangle rectangle) {
        WritableRaster raster;
        this.tileParam.setSourceRegion(this.isSubsampling ? new Rectangle(this.subsampleX * rectangle.x, this.subsampleY * rectangle.y, this.subsampleX * rectangle.width, this.subsampleY * rectangle.height) : rectangle);
        try {
            raster = this.reader.read(this.imageIndex, this.tileParam).getRaster();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return raster.createWritableChild(0, 0, raster.getWidth(), raster.getHeight(), rectangle.x, rectangle.y, (int[]) null);
    }

    public WritableRaster copyData(WritableRaster writableRaster) {
        if (writableRaster == null) {
            return read(new Rectangle(0, 0, getWidth(), getHeight()));
        }
        writableRaster.setRect(read(writableRaster.getBounds()));
        return writableRaster;
    }
}
