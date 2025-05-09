package com.github.jaiimageio.plugins.tiff;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class EXIFInteroperabilityTagSet extends TIFFTagSet {
    public static final String INTEROPERABILITY_INDEX_R98 = "R98";
    public static final String INTEROPERABILITY_INDEX_THM = "THM";
    public static final int TAG_INTEROPERABILITY_INDEX = 1;
    private static List tags;
    private static EXIFInteroperabilityTagSet theInstance;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class InteroperabilityIndex extends TIFFTag {
        public InteroperabilityIndex() {
            super(ExifInterface.TAG_INTEROPERABILITY_INDEX, 1, 4);
        }
    }

    private static void initTags() {
        ArrayList arrayList = new ArrayList(42);
        tags = arrayList;
        arrayList.add(new InteroperabilityIndex());
    }

    private EXIFInteroperabilityTagSet() {
        super(tags);
    }

    public static synchronized EXIFInteroperabilityTagSet getInstance() {
        EXIFInteroperabilityTagSet eXIFInteroperabilityTagSet;
        synchronized (EXIFInteroperabilityTagSet.class) {
            if (theInstance == null) {
                initTags();
                theInstance = new EXIFInteroperabilityTagSet();
                tags = null;
            }
            eXIFInteroperabilityTagSet = theInstance;
        }
        return eXIFInteroperabilityTagSet;
    }
}
