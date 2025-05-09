package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.TIFFDirectory;
import com.github.jaiimageio.plugins.tiff.TIFFField;
import com.github.jaiimageio.plugins.tiff.TIFFTag;
import com.github.jaiimageio.plugins.tiff.TIFFTagSet;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import javax.imageio.stream.ImageOutputStream;

/* loaded from: classes3.dex */
public class TIFFIFD extends TIFFDirectory {
    private long lastPosition;
    private long stripOrTileByteCountsPosition;
    private long stripOrTileOffsetsPosition;

    public static TIFFTag getTag(int i, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            TIFFTag tag = ((TIFFTagSet) it.next()).getTag(i);
            if (tag != null) {
                return tag;
            }
        }
        return null;
    }

    public static TIFFTag getTag(String str, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            TIFFTag tag = ((TIFFTagSet) it.next()).getTag(str);
            if (tag != null) {
                return tag;
            }
        }
        return null;
    }

    private static void writeTIFFFieldToStream(TIFFField tIFFField, ImageOutputStream imageOutputStream) throws IOException {
        int count = tIFFField.getCount();
        Object data = tIFFField.getData();
        switch (tIFFField.getType()) {
            case 1:
            case 6:
            case 7:
                imageOutputStream.write((byte[]) data);
                return;
            case 2:
                for (int i = 0; i < count; i++) {
                    String str = ((String[]) data)[i];
                    int length = str.length();
                    for (int i2 = 0; i2 < length; i2++) {
                        imageOutputStream.writeByte(str.charAt(i2) & 255);
                    }
                    imageOutputStream.writeByte(0);
                }
                return;
            case 3:
                char[] cArr = (char[]) data;
                imageOutputStream.writeChars(cArr, 0, cArr.length);
                return;
            case 4:
                break;
            case 5:
                for (int i3 = 0; i3 < count; i3++) {
                    long[][] jArr = (long[][]) data;
                    long j = jArr[i3][0];
                    long j2 = jArr[i3][1];
                    imageOutputStream.writeInt((int) j);
                    imageOutputStream.writeInt((int) j2);
                }
                return;
            case 8:
                short[] sArr = (short[]) data;
                imageOutputStream.writeShorts(sArr, 0, sArr.length);
                return;
            case 9:
                int[] iArr = (int[]) data;
                imageOutputStream.writeInts(iArr, 0, iArr.length);
                return;
            case 10:
                for (int i4 = 0; i4 < count; i4++) {
                    int[][] iArr2 = (int[][]) data;
                    imageOutputStream.writeInt(iArr2[i4][0]);
                    imageOutputStream.writeInt(iArr2[i4][1]);
                }
                return;
            case 11:
                float[] fArr = (float[]) data;
                imageOutputStream.writeFloats(fArr, 0, fArr.length);
                return;
            case 12:
                double[] dArr = (double[]) data;
                imageOutputStream.writeDoubles(dArr, 0, dArr.length);
                return;
            case 13:
                imageOutputStream.writeInt(0);
                return;
            default:
                return;
        }
        for (int i5 = 0; i5 < count; i5++) {
            imageOutputStream.writeInt((int) ((long[]) data)[i5]);
        }
    }

    public TIFFIFD(List list, TIFFTag tIFFTag) {
        super((TIFFTagSet[]) list.toArray(new TIFFTagSet[list.size()]), tIFFTag);
        this.stripOrTileByteCountsPosition = -1L;
        this.stripOrTileOffsetsPosition = -1L;
        this.lastPosition = -1L;
    }

    public TIFFIFD(List list) {
        this(list, null);
    }

    public List getTagSetList() {
        return Arrays.asList(getTagSets());
    }

    public Iterator iterator() {
        return Arrays.asList(getTIFFFields()).iterator();
    }

    /*  JADX ERROR: Types fix failed
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [char[], short[]], vars: [r0v4 ??, r0v25 ??, r0v15 char[], r0v16 ??, r0v19 ??, r0v20 short[], r0v21 ??, r0v24 ??, r0v26 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.rerun(InitCodeVariables.java:36)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryInsertAdditionalMove(FixTypesVisitor.java:553)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
        */
    public void initialize(javax.imageio.stream.ImageInputStream r21, boolean r22) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 532
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.jaiimageio.impl.plugins.tiff.TIFFIFD.initialize(javax.imageio.stream.ImageInputStream, boolean):void");
    }

    public void writeToStream(ImageOutputStream imageOutputStream) throws IOException {
        long streamPosition;
        long j;
        imageOutputStream.writeShort(getNumTIFFFields());
        long streamPosition2 = imageOutputStream.getStreamPosition() + (r0 * 12) + 4;
        Iterator it = iterator();
        while (it.hasNext()) {
            TIFFField tIFFField = (TIFFField) it.next();
            TIFFTag tag = tIFFField.getTag();
            int type = tIFFField.getType();
            int count = tIFFField.getCount();
            if (type == 0) {
                type = 7;
            }
            int sizeOfType = TIFFTag.getSizeOfType(type) * count;
            if (type == 2) {
                sizeOfType = 0;
                for (int i = 0; i < count; i++) {
                    sizeOfType += tIFFField.getAsString(i).length() + 1;
                }
                count = sizeOfType;
            }
            int tagNumber = tIFFField.getTagNumber();
            imageOutputStream.writeShort(tagNumber);
            imageOutputStream.writeShort(type);
            imageOutputStream.writeInt(count);
            imageOutputStream.writeInt(0);
            imageOutputStream.mark();
            imageOutputStream.skipBytes(-4);
            if (sizeOfType > 4 || tag.isIFDPointer()) {
                long j2 = (streamPosition2 + 3) & (-4);
                imageOutputStream.writeInt((int) j2);
                imageOutputStream.seek(j2);
                if (tag.isIFDPointer()) {
                    TIFFIFD tiffifd = (TIFFIFD) tIFFField.getData();
                    tiffifd.writeToStream(imageOutputStream);
                    streamPosition = tiffifd.lastPosition;
                } else {
                    writeTIFFFieldToStream(tIFFField, imageOutputStream);
                    streamPosition = imageOutputStream.getStreamPosition();
                }
                streamPosition2 = streamPosition;
                j = j2;
            } else {
                j = imageOutputStream.getStreamPosition();
                writeTIFFFieldToStream(tIFFField, imageOutputStream);
            }
            if (tagNumber == 279 || tagNumber == 325 || tagNumber == 514) {
                this.stripOrTileByteCountsPosition = j;
            } else if (tagNumber == 273 || tagNumber == 324 || tagNumber == 513) {
                this.stripOrTileOffsetsPosition = j;
            }
            imageOutputStream.reset();
        }
        this.lastPosition = streamPosition2;
    }

    public long getStripOrTileByteCountsPosition() {
        return this.stripOrTileByteCountsPosition;
    }

    public long getStripOrTileOffsetsPosition() {
        return this.stripOrTileOffsetsPosition;
    }

    public long getLastPosition() {
        return this.lastPosition;
    }

    void setPositions(long j, long j2, long j3) {
        this.stripOrTileOffsetsPosition = j;
        this.stripOrTileByteCountsPosition = j2;
        this.lastPosition = j3;
    }

    public TIFFIFD getShallowClone() {
        BaselineTIFFTagSet baselineTIFFTagSet = BaselineTIFFTagSet.getInstance();
        List tagSetList = getTagSetList();
        if (!tagSetList.contains(baselineTIFFTagSet)) {
            return this;
        }
        TIFFIFD tiffifd = new TIFFIFD(tagSetList, getParentTag());
        SortedSet tagNumbers = baselineTIFFTagSet.getTagNumbers();
        Iterator it = iterator();
        while (it.hasNext()) {
            TIFFField tIFFField = (TIFFField) it.next();
            if (tagNumbers.contains(new Integer(tIFFField.getTagNumber()))) {
                Object data = tIFFField.getData();
                int type = tIFFField.getType();
                switch (type) {
                    case 1:
                    case 6:
                    case 7:
                        data = ((byte[]) data).clone();
                        break;
                    case 2:
                        data = ((String[]) data).clone();
                        break;
                    case 3:
                        data = ((char[]) data).clone();
                        break;
                    case 4:
                    case 13:
                        data = ((long[]) data).clone();
                        break;
                    case 5:
                        data = ((long[][]) data).clone();
                        break;
                    case 8:
                        data = ((short[]) data).clone();
                        break;
                    case 9:
                        data = ((int[]) data).clone();
                        break;
                    case 10:
                        data = ((int[][]) data).clone();
                        break;
                    case 11:
                        data = ((float[]) data).clone();
                        break;
                    case 12:
                        try {
                            data = ((double[]) data).clone();
                            break;
                        } catch (Exception unused) {
                            break;
                        }
                }
                tIFFField = new TIFFField(tIFFField.getTag(), type, tIFFField.getCount(), data);
            }
            tiffifd.addTIFFField(tIFFField);
        }
        tiffifd.setPositions(this.stripOrTileOffsetsPosition, this.stripOrTileByteCountsPosition, this.lastPosition);
        return tiffifd;
    }
}
