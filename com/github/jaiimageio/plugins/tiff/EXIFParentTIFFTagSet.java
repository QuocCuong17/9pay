package com.github.jaiimageio.plugins.tiff;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class EXIFParentTIFFTagSet extends TIFFTagSet {
    public static final int TAG_EXIF_IFD_POINTER = 34665;
    public static final int TAG_GPS_INFO_IFD_POINTER = 34853;
    private static List tags;
    private static EXIFParentTIFFTagSet theInstance;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class EXIFIFDPointer extends TIFFTag {
        public EXIFIFDPointer() {
            super("EXIFIFDPointer", EXIFParentTIFFTagSet.TAG_EXIF_IFD_POINTER, 16, EXIFTIFFTagSet.getInstance());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSInfoIFDPointer extends TIFFTag {
        public GPSInfoIFDPointer() {
            super("GPSInfoIFDPointer", 34853, 16, EXIFGPSTagSet.getInstance());
        }
    }

    private static void initTags() {
        ArrayList arrayList = new ArrayList(1);
        tags = arrayList;
        arrayList.add(new EXIFIFDPointer());
        tags.add(new GPSInfoIFDPointer());
    }

    private EXIFParentTIFFTagSet() {
        super(tags);
    }

    public static synchronized EXIFParentTIFFTagSet getInstance() {
        EXIFParentTIFFTagSet eXIFParentTIFFTagSet;
        synchronized (EXIFParentTIFFTagSet.class) {
            if (theInstance == null) {
                initTags();
                theInstance = new EXIFParentTIFFTagSet();
                tags = null;
            }
            eXIFParentTIFFTagSet = theInstance;
        }
        return eXIFParentTIFFTagSet;
    }
}
