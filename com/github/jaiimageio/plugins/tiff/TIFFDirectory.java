package com.github.jaiimageio.plugins.tiff;

import com.github.jaiimageio.impl.plugins.tiff.TIFFIFD;
import com.github.jaiimageio.impl.plugins.tiff.TIFFImageMetadata;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;

/* loaded from: classes3.dex */
public class TIFFDirectory implements Cloneable {
    private static final int MAX_LOW_FIELD_TAG_NUM = 532;
    private TIFFTag parentTag;
    private List tagSets;
    private TIFFField[] lowFields = new TIFFField[533];
    private int numLowFields = 0;
    private Map highFields = new TreeMap();

    public static TIFFDirectory createFromMetadata(IIOMetadata iIOMetadata) throws IIOInvalidTreeException {
        TIFFImageMetadata tIFFImageMetadata;
        if (iIOMetadata == null) {
            throw new IllegalArgumentException("tiffImageMetadata == null");
        }
        if (iIOMetadata instanceof TIFFImageMetadata) {
            tIFFImageMetadata = (TIFFImageMetadata) iIOMetadata;
        } else {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(BaselineTIFFTagSet.getInstance());
            TIFFImageMetadata tIFFImageMetadata2 = new TIFFImageMetadata(arrayList);
            String str = null;
            String nativeMetadataFormatName = iIOMetadata.getNativeMetadataFormatName();
            String str2 = TIFFImageMetadata.nativeMetadataFormatName;
            if (!TIFFImageMetadata.nativeMetadataFormatName.equals(nativeMetadataFormatName)) {
                String[] extraMetadataFormatNames = iIOMetadata.getExtraMetadataFormatNames();
                if (extraMetadataFormatNames != null) {
                    int i = 0;
                    while (true) {
                        if (i >= extraMetadataFormatNames.length) {
                            break;
                        }
                        if (TIFFImageMetadata.nativeMetadataFormatName.equals(extraMetadataFormatNames[i])) {
                            str = extraMetadataFormatNames[i];
                            break;
                        }
                        i++;
                    }
                }
                if (str != null) {
                    str2 = str;
                } else {
                    if (!iIOMetadata.isStandardMetadataFormatSupported()) {
                        throw new IllegalArgumentException("Parameter does not support required metadata format!");
                    }
                    str2 = "javax_imageio_1.0";
                }
            }
            tIFFImageMetadata2.setFromTree(str2, iIOMetadata.getAsTree(str2));
            tIFFImageMetadata = tIFFImageMetadata2;
        }
        return tIFFImageMetadata.getRootIFD();
    }

    private static TIFFIFD getDirectoryAsIFD(TIFFDirectory tIFFDirectory) {
        if (tIFFDirectory instanceof TIFFIFD) {
            return (TIFFIFD) tIFFDirectory;
        }
        TIFFIFD tiffifd = new TIFFIFD(Arrays.asList(tIFFDirectory.getTagSets()), tIFFDirectory.getParentTag());
        for (TIFFField tIFFField : tIFFDirectory.getTIFFFields()) {
            TIFFTag tag = tIFFField.getTag();
            if (tag.isIFDPointer()) {
                tIFFField = new TIFFField(tag, tIFFField.getType(), tIFFField.getCount(), getDirectoryAsIFD((TIFFDirectory) tIFFField.getData()));
            }
            tiffifd.addTIFFField(tIFFField);
        }
        return tiffifd;
    }

    public TIFFDirectory(TIFFTagSet[] tIFFTagSetArr, TIFFTag tIFFTag) {
        if (tIFFTagSetArr == null) {
            throw new IllegalArgumentException("tagSets == null!");
        }
        this.tagSets = new ArrayList(tIFFTagSetArr.length);
        for (TIFFTagSet tIFFTagSet : tIFFTagSetArr) {
            this.tagSets.add(tIFFTagSet);
        }
        this.parentTag = tIFFTag;
    }

    public TIFFTagSet[] getTagSets() {
        List list = this.tagSets;
        return (TIFFTagSet[]) list.toArray(new TIFFTagSet[list.size()]);
    }

    public void addTagSet(TIFFTagSet tIFFTagSet) {
        if (tIFFTagSet == null) {
            throw new IllegalArgumentException("tagSet == null");
        }
        if (this.tagSets.contains(tIFFTagSet)) {
            return;
        }
        this.tagSets.add(tIFFTagSet);
    }

    public void removeTagSet(TIFFTagSet tIFFTagSet) {
        if (tIFFTagSet == null) {
            throw new IllegalArgumentException("tagSet == null");
        }
        if (this.tagSets.contains(tIFFTagSet)) {
            this.tagSets.remove(tIFFTagSet);
        }
    }

    public TIFFTag getParentTag() {
        return this.parentTag;
    }

    public TIFFTag getTag(int i) {
        return TIFFIFD.getTag(i, this.tagSets);
    }

    public int getNumTIFFFields() {
        return this.numLowFields + this.highFields.size();
    }

    public boolean containsTIFFField(int i) {
        return (i >= 0 && i <= 532 && this.lowFields[i] != null) || this.highFields.containsKey(new Integer(i));
    }

    public void addTIFFField(TIFFField tIFFField) {
        if (tIFFField == null) {
            throw new IllegalArgumentException("f == null");
        }
        int tagNumber = tIFFField.getTagNumber();
        if (tagNumber >= 0 && tagNumber <= 532) {
            TIFFField[] tIFFFieldArr = this.lowFields;
            if (tIFFFieldArr[tagNumber] == null) {
                this.numLowFields++;
            }
            tIFFFieldArr[tagNumber] = tIFFField;
            return;
        }
        this.highFields.put(new Integer(tagNumber), tIFFField);
    }

    public TIFFField getTIFFField(int i) {
        if (i >= 0 && i <= 532) {
            return this.lowFields[i];
        }
        return (TIFFField) this.highFields.get(new Integer(i));
    }

    public void removeTIFFField(int i) {
        if (i >= 0 && i <= 532) {
            TIFFField[] tIFFFieldArr = this.lowFields;
            if (tIFFFieldArr[i] != null) {
                this.numLowFields--;
                tIFFFieldArr[i] = null;
                return;
            }
            return;
        }
        this.highFields.remove(new Integer(i));
    }

    public TIFFField[] getTIFFFields() {
        TIFFField[] tIFFFieldArr = new TIFFField[this.numLowFields + this.highFields.size()];
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i > 532) {
                break;
            }
            TIFFField[] tIFFFieldArr2 = this.lowFields;
            if (tIFFFieldArr2[i] != null) {
                int i3 = i2 + 1;
                tIFFFieldArr[i2] = tIFFFieldArr2[i];
                if (i3 == this.numLowFields) {
                    i2 = i3;
                    break;
                }
                i2 = i3;
            }
            i++;
        }
        if (!this.highFields.isEmpty()) {
            Iterator it = this.highFields.keySet().iterator();
            while (it.hasNext()) {
                tIFFFieldArr[i2] = (TIFFField) this.highFields.get(it.next());
                i2++;
            }
        }
        return tIFFFieldArr;
    }

    public void removeTIFFFields() {
        Arrays.fill(this.lowFields, (Object) null);
        this.numLowFields = 0;
        this.highFields.clear();
    }

    public IIOMetadata getAsMetadata() {
        return new TIFFImageMetadata(getDirectoryAsIFD(this));
    }

    public Object clone() {
        TIFFDirectory tIFFDirectory = new TIFFDirectory(getTagSets(), getParentTag());
        for (TIFFField tIFFField : getTIFFFields()) {
            tIFFDirectory.addTIFFField(tIFFField);
        }
        return tIFFDirectory;
    }
}
