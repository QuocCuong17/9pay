package com.github.jaiimageio.plugins.tiff;

import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageReadParam;

/* loaded from: classes3.dex */
public class TIFFImageReadParam extends ImageReadParam {
    List allowedTagSets = new ArrayList(4);
    TIFFDecompressor decompressor = null;
    TIFFColorConverter colorConverter = null;

    public TIFFImageReadParam() {
        addAllowedTagSet(BaselineTIFFTagSet.getInstance());
        addAllowedTagSet(FaxTIFFTagSet.getInstance());
        addAllowedTagSet(EXIFParentTIFFTagSet.getInstance());
        addAllowedTagSet(GeoTIFFTagSet.getInstance());
    }

    public void addAllowedTagSet(TIFFTagSet tIFFTagSet) {
        if (tIFFTagSet == null) {
            throw new IllegalArgumentException("tagSet == null!");
        }
        this.allowedTagSets.add(tIFFTagSet);
    }

    public void removeAllowedTagSet(TIFFTagSet tIFFTagSet) {
        if (tIFFTagSet == null) {
            throw new IllegalArgumentException("tagSet == null!");
        }
        this.allowedTagSets.remove(tIFFTagSet);
    }

    public List getAllowedTagSets() {
        return this.allowedTagSets;
    }

    public void setTIFFDecompressor(TIFFDecompressor tIFFDecompressor) {
        this.decompressor = tIFFDecompressor;
    }

    public TIFFDecompressor getTIFFDecompressor() {
        return this.decompressor;
    }

    public void setColorConverter(TIFFColorConverter tIFFColorConverter) {
        this.colorConverter = tIFFColorConverter;
    }

    public TIFFColorConverter getColorConverter() {
        return this.colorConverter;
    }
}
