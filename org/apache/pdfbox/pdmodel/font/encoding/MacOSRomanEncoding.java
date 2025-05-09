package org.apache.pdfbox.pdmodel.font.encoding;

import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.FaxTIFFTagSet;
import org.apache.pdfbox.cos.COSBase;

/* loaded from: classes5.dex */
public class MacOSRomanEncoding extends MacRomanEncoding {
    public static final MacOSRomanEncoding INSTANCE = new MacOSRomanEncoding();

    @Override // org.apache.pdfbox.pdmodel.font.encoding.MacRomanEncoding, org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return null;
    }

    public MacOSRomanEncoding() {
        add(255, "notequal");
        add(260, "infinity");
        add(BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION, "lessequal");
        add(BaselineTIFFTagSet.TAG_THRESHHOLDING, "greaterequal");
        add(BaselineTIFFTagSet.TAG_FILL_ORDER, "partialdiff");
        add(267, "summation");
        add(270, "product");
        add(BaselineTIFFTagSet.TAG_MAKE, "pi");
        add(BaselineTIFFTagSet.TAG_MODEL, "integral");
        add(275, "Omega");
        add(303, "radical");
        add(305, "approxequal");
        add(306, "Delta");
        add(FaxTIFFTagSet.TAG_CLEAN_FAX_DATA, "lozenge");
        add(BaselineTIFFTagSet.TAG_INK_NAMES, "Euro");
        add(360, "apple");
    }
}
