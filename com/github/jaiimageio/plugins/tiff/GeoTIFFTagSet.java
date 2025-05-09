package com.github.jaiimageio.plugins.tiff;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class GeoTIFFTagSet extends TIFFTagSet {
    public static final int TAG_GEO_ASCII_PARAMS = 34737;
    public static final int TAG_GEO_DOUBLE_PARAMS = 34736;
    public static final int TAG_GEO_KEY_DIRECTORY = 34735;
    public static final int TAG_MODEL_PIXEL_SCALE = 33550;
    public static final int TAG_MODEL_TIE_POINT = 33922;
    public static final int TAG_MODEL_TRANSFORMATION = 34264;
    private static List tags;
    private static GeoTIFFTagSet theInstance;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ModelPixelScale extends TIFFTag {
        public ModelPixelScale() {
            super("ModelPixelScaleTag", GeoTIFFTagSet.TAG_MODEL_PIXEL_SCALE, 4096);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ModelTransformation extends TIFFTag {
        public ModelTransformation() {
            super("ModelTransformationTag", GeoTIFFTagSet.TAG_MODEL_TRANSFORMATION, 4096);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ModelTiePoint extends TIFFTag {
        public ModelTiePoint() {
            super("ModelTiePointTag", GeoTIFFTagSet.TAG_MODEL_TIE_POINT, 4096);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GeoKeyDirectory extends TIFFTag {
        public GeoKeyDirectory() {
            super("GeoKeyDirectory", GeoTIFFTagSet.TAG_GEO_KEY_DIRECTORY, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GeoDoubleParams extends TIFFTag {
        public GeoDoubleParams() {
            super("GeoDoubleParams", GeoTIFFTagSet.TAG_GEO_DOUBLE_PARAMS, 4096);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GeoAsciiParams extends TIFFTag {
        public GeoAsciiParams() {
            super("GeoAsciiParams", GeoTIFFTagSet.TAG_GEO_ASCII_PARAMS, 4);
        }
    }

    private static void initTags() {
        ArrayList arrayList = new ArrayList(42);
        tags = arrayList;
        arrayList.add(new ModelPixelScale());
        tags.add(new ModelTransformation());
        tags.add(new ModelTiePoint());
        tags.add(new GeoKeyDirectory());
        tags.add(new GeoDoubleParams());
        tags.add(new GeoAsciiParams());
    }

    private GeoTIFFTagSet() {
        super(tags);
    }

    public static synchronized GeoTIFFTagSet getInstance() {
        GeoTIFFTagSet geoTIFFTagSet;
        synchronized (GeoTIFFTagSet.class) {
            if (theInstance == null) {
                initTags();
                theInstance = new GeoTIFFTagSet();
                tags = null;
            }
            geoTIFFTagSet = theInstance;
        }
        return geoTIFFTagSet;
    }
}
