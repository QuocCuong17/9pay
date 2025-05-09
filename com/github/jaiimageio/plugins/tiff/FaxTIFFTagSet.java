package com.github.jaiimageio.plugins.tiff;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class FaxTIFFTagSet extends TIFFTagSet {
    public static final int CLEAN_FAX_DATA_ERRORS_CORRECTED = 1;
    public static final int CLEAN_FAX_DATA_ERRORS_UNCORRECTED = 2;
    public static final int CLEAN_FAX_DATA_NO_ERRORS = 0;
    public static final int TAG_BAD_FAX_LINES = 326;
    public static final int TAG_CLEAN_FAX_DATA = 327;
    public static final int TAG_CONSECUTIVE_BAD_LINES = 328;
    private static List tags;
    private static FaxTIFFTagSet theInstance;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class BadFaxLines extends TIFFTag {
        public BadFaxLines() {
            super("BadFaxLines", FaxTIFFTagSet.TAG_BAD_FAX_LINES, 24);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class CleanFaxData extends TIFFTag {
        public CleanFaxData() {
            super("CleanFaxData", FaxTIFFTagSet.TAG_CLEAN_FAX_DATA, 8);
            addValueName(0, "No errors");
            addValueName(1, "Errors corrected");
            addValueName(2, "Errors uncorrected");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ConsecutiveBadFaxLines extends TIFFTag {
        public ConsecutiveBadFaxLines() {
            super("ConsecutiveBadFaxLines", FaxTIFFTagSet.TAG_CONSECUTIVE_BAD_LINES, 24);
        }
    }

    private static void initTags() {
        ArrayList arrayList = new ArrayList(42);
        tags = arrayList;
        arrayList.add(new BadFaxLines());
        tags.add(new CleanFaxData());
        tags.add(new ConsecutiveBadFaxLines());
    }

    private FaxTIFFTagSet() {
        super(tags);
    }

    public static synchronized FaxTIFFTagSet getInstance() {
        FaxTIFFTagSet faxTIFFTagSet;
        synchronized (FaxTIFFTagSet.class) {
            if (theInstance == null) {
                initTags();
                theInstance = new FaxTIFFTagSet();
                tags = null;
            }
            faxTIFFTagSet = theInstance;
        }
        return faxTIFFTagSet;
    }
}
