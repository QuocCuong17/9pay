package com.github.jaiimageio.impl.common;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/* loaded from: classes3.dex */
public abstract class SimpleRenderedImage implements RenderedImage {
    protected ColorModel colorModel;
    protected int height;
    protected int minX;
    protected int minY;
    protected SampleModel sampleModel;
    protected int tileHeight;
    protected int tileWidth;
    protected int width;
    protected int tileGridXOffset = 0;
    protected int tileGridYOffset = 0;
    protected Vector sources = new Vector();
    protected Hashtable properties = new Hashtable();

    public static int tileXToX(int i, int i2, int i3) {
        return (i * i3) + i2;
    }

    public static int tileYToY(int i, int i2, int i3) {
        return (i * i3) + i2;
    }

    public Vector getSources() {
        return null;
    }

    public int getMinX() {
        return this.minX;
    }

    public final int getMaxX() {
        return getMinX() + getWidth();
    }

    public int getMinY() {
        return this.minY;
    }

    public final int getMaxY() {
        return getMinY() + getHeight();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Rectangle getBounds() {
        return new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
    }

    public int getTileWidth() {
        return this.tileWidth;
    }

    public int getTileHeight() {
        return this.tileHeight;
    }

    public int getTileGridXOffset() {
        return this.tileGridXOffset;
    }

    public int getTileGridYOffset() {
        return this.tileGridYOffset;
    }

    public int getMinTileX() {
        return XToTileX(getMinX());
    }

    public int getMaxTileX() {
        return XToTileX(getMaxX() - 1);
    }

    public int getNumXTiles() {
        return (getMaxTileX() - getMinTileX()) + 1;
    }

    public int getMinTileY() {
        return YToTileY(getMinY());
    }

    public int getMaxTileY() {
        return YToTileY(getMaxY() - 1);
    }

    public int getNumYTiles() {
        return (getMaxTileY() - getMinTileY()) + 1;
    }

    public SampleModel getSampleModel() {
        return this.sampleModel;
    }

    public ColorModel getColorModel() {
        return this.colorModel;
    }

    public Object getProperty(String str) {
        Object obj = this.properties.get(str.toLowerCase());
        return obj != null ? obj : Image.UndefinedProperty;
    }

    public String[] getPropertyNames() {
        if (this.properties.size() <= 0) {
            return null;
        }
        String[] strArr = new String[this.properties.size()];
        int i = 0;
        Enumeration keys = this.properties.keys();
        while (keys.hasMoreElements()) {
            strArr[i] = (String) keys.nextElement();
            i++;
        }
        return strArr;
    }

    public String[] getPropertyNames(String str) {
        String[] propertyNames = getPropertyNames();
        if (propertyNames == null) {
            return null;
        }
        String lowerCase = str.toLowerCase();
        Vector vector = new Vector();
        int i = 0;
        for (int i2 = 0; i2 < propertyNames.length; i2++) {
            if (propertyNames[i2].startsWith(lowerCase)) {
                vector.addElement(propertyNames[i2]);
            }
        }
        if (vector.size() == 0) {
            return null;
        }
        String[] strArr = new String[vector.size()];
        Iterator it = vector.iterator();
        while (it.hasNext()) {
            strArr[i] = (String) it.next();
            i++;
        }
        return strArr;
    }

    public static int XToTileX(int i, int i2, int i3) {
        int i4 = i - i2;
        if (i4 < 0) {
            i4 += 1 - i3;
        }
        return i4 / i3;
    }

    public static int YToTileY(int i, int i2, int i3) {
        int i4 = i - i2;
        if (i4 < 0) {
            i4 += 1 - i3;
        }
        return i4 / i3;
    }

    public int XToTileX(int i) {
        return XToTileX(i, getTileGridXOffset(), getTileWidth());
    }

    public int YToTileY(int i) {
        return YToTileY(i, getTileGridYOffset(), getTileHeight());
    }

    public int tileXToX(int i) {
        return (i * this.tileWidth) + this.tileGridXOffset;
    }

    public int tileYToY(int i) {
        return (i * this.tileHeight) + this.tileGridYOffset;
    }

    public Raster getData() {
        return getData(new Rectangle(getMinX(), getMinY(), getWidth(), getHeight()));
    }

    public Raster getData(Rectangle rectangle) {
        Rectangle rectangle2 = rectangle;
        Rectangle bounds = getBounds();
        if (rectangle2 == null) {
            rectangle2 = bounds;
        } else if (!rectangle2.intersects(bounds)) {
            throw new IllegalArgumentException(I18N.getString("SimpleRenderedImage0"));
        }
        int XToTileX = XToTileX(rectangle2.x);
        int YToTileY = YToTileY(rectangle2.y);
        int XToTileX2 = XToTileX((rectangle2.x + rectangle2.width) - 1);
        int YToTileY2 = YToTileY((rectangle2.y + rectangle2.height) - 1);
        if (XToTileX == XToTileX2 && YToTileY == YToTileY2) {
            return getTile(XToTileX, YToTileY).createChild(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height, rectangle2.x, rectangle2.y, (int[]) null);
        }
        if (!bounds.contains(rectangle2)) {
            Rectangle intersection = rectangle2.intersection(bounds);
            XToTileX = XToTileX(intersection.x);
            YToTileY = YToTileY(intersection.y);
            XToTileX2 = XToTileX((intersection.x + intersection.width) - 1);
            YToTileY2 = YToTileY((intersection.y + intersection.height) - 1);
        }
        WritableRaster createWritableRaster = Raster.createWritableRaster(this.sampleModel.createCompatibleSampleModel(rectangle2.width, rectangle2.height), rectangle2.getLocation());
        while (YToTileY <= YToTileY2) {
            for (int i = XToTileX; i <= XToTileX2; i++) {
                Raster tile = getTile(i, YToTileY);
                tile.getBounds();
                Rectangle intersection2 = rectangle2.intersection(tile.getBounds());
                createWritableRaster.setRect(tile.createChild(intersection2.x, intersection2.y, intersection2.width, intersection2.height, intersection2.x, intersection2.y, (int[]) null));
            }
            YToTileY++;
        }
        return createWritableRaster;
    }

    public WritableRaster copyData(WritableRaster writableRaster) {
        Rectangle bounds;
        WritableRaster writableRaster2;
        Rectangle bounds2 = getBounds();
        if (writableRaster == null) {
            writableRaster2 = Raster.createWritableRaster(this.sampleModel.createCompatibleSampleModel(this.width, this.height), new Point(this.minX, this.minY));
            bounds = bounds2;
        } else {
            bounds = writableRaster.getBounds();
            writableRaster2 = writableRaster;
        }
        Rectangle intersection = bounds2.contains(bounds) ? bounds : bounds.intersection(bounds2);
        int XToTileX = XToTileX(intersection.x);
        int XToTileX2 = XToTileX((intersection.x + intersection.width) - 1);
        int YToTileY = YToTileY((intersection.y + intersection.height) - 1);
        for (int YToTileY2 = YToTileY(intersection.y); YToTileY2 <= YToTileY; YToTileY2++) {
            for (int i = XToTileX; i <= XToTileX2; i++) {
                Raster tile = getTile(i, YToTileY2);
                tile.getBounds();
                Rectangle intersection2 = bounds.intersection(tile.getBounds());
                writableRaster2.setRect(tile.createChild(intersection2.x, intersection2.y, intersection2.width, intersection2.height, intersection2.x, intersection2.y, (int[]) null));
            }
        }
        return writableRaster2;
    }
}
