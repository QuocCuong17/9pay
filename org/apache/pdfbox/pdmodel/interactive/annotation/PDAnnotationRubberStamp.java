package org.apache.pdfbox.pdmodel.interactive.annotation;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public class PDAnnotationRubberStamp extends PDAnnotationMarkup {
    public static final String NAME_APPROVED = "Approved";
    public static final String NAME_AS_IS = "AsIs";
    public static final String NAME_CONFIDENTIAL = "Confidential";
    public static final String NAME_DEPARTMENTAL = "Departmental";
    public static final String NAME_DRAFT = "Draft";
    public static final String NAME_EXPERIMENTAL = "Experimental";
    public static final String NAME_EXPIRED = "Expired";
    public static final String NAME_FINAL = "Final";
    public static final String NAME_FOR_COMMENT = "ForComment";
    public static final String NAME_FOR_PUBLIC_RELEASE = "ForPublicRelease";
    public static final String NAME_NOT_APPROVED = "NotApproved";
    public static final String NAME_NOT_FOR_PUBLIC_RELEASE = "NotForPublicRelease";
    public static final String NAME_SOLD = "Sold";
    public static final String NAME_TOP_SECRET = "TopSecret";
    public static final String SUB_TYPE = "Stamp";

    public PDAnnotationRubberStamp() {
        getDictionary().setItem(COSName.SUBTYPE, (COSBase) COSName.getPDFName("Stamp"));
    }

    public PDAnnotationRubberStamp(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public void setName(String str) {
        getDictionary().setName(COSName.NAME, str);
    }

    public String getName() {
        return getDictionary().getNameAsString(COSName.NAME, NAME_DRAFT);
    }
}
