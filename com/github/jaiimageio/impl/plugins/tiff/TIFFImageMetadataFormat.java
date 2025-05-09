package com.github.jaiimageio.impl.plugins.tiff;

import androidx.media3.exoplayer.upstream.CmcdData;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadataFormat;

/* loaded from: classes3.dex */
public class TIFFImageMetadataFormat extends TIFFMetadataFormat {
    private static TIFFImageMetadataFormat theInstance;

    public boolean canNodeAppear(String str, ImageTypeSpecifier imageTypeSpecifier) {
        return false;
    }

    private TIFFImageMetadataFormat() {
        this.resourceBaseName = "com.github.jaiimageio.impl.plugins.tiff.TIFFImageMetadataFormatResources";
        this.rootName = TIFFImageMetadata.nativeMetadataFormatName;
        String[] strArr = new String[0];
        int i = 4;
        this.elementInfoMap.put(TIFFImageMetadata.nativeMetadataFormatName, new TIFFElementInfo(new String[]{"TIFFIFD"}, strArr, 4));
        this.elementInfoMap.put("TIFFIFD", new TIFFElementInfo(new String[]{"TIFFField", "TIFFIFD"}, new String[]{"tagSets", "parentTagNumber", "parentTagName"}, 4));
        TIFFAttrInfo tIFFAttrInfo = new TIFFAttrInfo();
        tIFFAttrInfo.dataType = 0;
        int i2 = 1;
        tIFFAttrInfo.isRequired = true;
        this.attrInfoMap.put("TIFFIFD/tagSets", tIFFAttrInfo);
        TIFFAttrInfo tIFFAttrInfo2 = new TIFFAttrInfo();
        tIFFAttrInfo2.dataType = 2;
        tIFFAttrInfo2.isRequired = false;
        this.attrInfoMap.put("TIFFIFD/parentTagNumber", tIFFAttrInfo2);
        TIFFAttrInfo tIFFAttrInfo3 = new TIFFAttrInfo();
        tIFFAttrInfo3.dataType = 0;
        tIFFAttrInfo3.isRequired = false;
        this.attrInfoMap.put("TIFFIFD/parentTagName", tIFFAttrInfo3);
        String[] strArr2 = {"TIFFByte", "TIFFAscii", "TIFFShort", "TIFFSShort", "TIFFLong", "TIFFSLong", "TIFFRational", "TIFFSRational", "TIFFFloat", "TIFFDouble", "TIFFUndefined"};
        String[] strArr3 = {"value", "description"};
        String[] strArr4 = {"value"};
        TIFFAttrInfo tIFFAttrInfo4 = new TIFFAttrInfo();
        TIFFAttrInfo tIFFAttrInfo5 = new TIFFAttrInfo();
        int i3 = 0;
        while (i3 < 11) {
            if (!strArr2[i3].equals("TIFFUndefined")) {
                String[] strArr5 = new String[i2];
                strArr5[0] = strArr2[i3];
                TIFFElementInfo tIFFElementInfo = new TIFFElementInfo(strArr5, strArr, i);
                this.elementInfoMap.put(strArr2[i3] + CmcdData.Factory.STREAMING_FORMAT_SS, tIFFElementInfo);
            }
            boolean z = (strArr2[i3].equals("TIFFUndefined") || strArr2[i3].equals("TIFFAscii") || strArr2[i3].equals("TIFFRational") || strArr2[i3].equals("TIFFSRational") || strArr2[i3].equals("TIFFFloat") || strArr2[i3].equals("TIFFDouble")) ? false : true;
            this.elementInfoMap.put(strArr2[i3], new TIFFElementInfo(strArr, z ? strArr3 : strArr4, 0));
            this.attrInfoMap.put(strArr2[i3] + "/value", tIFFAttrInfo4);
            if (z) {
                this.attrInfoMap.put(strArr2[i3] + "/description", tIFFAttrInfo5);
            }
            i3++;
            i2 = 1;
            i = 4;
        }
        String[] strArr6 = new String[21];
        for (int i4 = 0; i4 < 11; i4++) {
            int i5 = i4 * 2;
            strArr6[i5] = strArr2[i4];
            if (!strArr2[i4].equals("TIFFUndefined")) {
                strArr6[i5 + 1] = strArr2[i4] + CmcdData.Factory.STREAMING_FORMAT_SS;
            }
        }
        this.elementInfoMap.put("TIFFField", new TIFFElementInfo(strArr6, new String[]{"number", "name"}, 3));
        TIFFAttrInfo tIFFAttrInfo6 = new TIFFAttrInfo();
        tIFFAttrInfo6.isRequired = true;
        this.attrInfoMap.put("TIFFField/number", tIFFAttrInfo6);
        this.attrInfoMap.put("TIFFField/name", new TIFFAttrInfo());
    }

    public static synchronized IIOMetadataFormat getInstance() {
        TIFFImageMetadataFormat tIFFImageMetadataFormat;
        synchronized (TIFFImageMetadataFormat.class) {
            if (theInstance == null) {
                theInstance = new TIFFImageMetadataFormat();
            }
            tIFFImageMetadataFormat = theInstance;
        }
        return tIFFImageMetadataFormat;
    }
}
