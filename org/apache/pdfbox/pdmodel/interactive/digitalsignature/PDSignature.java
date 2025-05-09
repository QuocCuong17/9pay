package org.apache.pdfbox.pdmodel.interactive.digitalsignature;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdfwriter.COSFilterInputStream;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public class PDSignature implements COSObjectable {
    private final COSDictionary dictionary;
    public static final COSName FILTER_ADOBE_PPKLITE = COSName.ADOBE_PPKLITE;
    public static final COSName FILTER_ENTRUST_PPKEF = COSName.ENTRUST_PPKEF;
    public static final COSName FILTER_CICI_SIGNIT = COSName.CICI_SIGNIT;
    public static final COSName FILTER_VERISIGN_PPKVS = COSName.VERISIGN_PPKVS;
    public static final COSName SUBFILTER_ADBE_X509_RSA_SHA1 = COSName.ADBE_X509_RSA_SHA1;
    public static final COSName SUBFILTER_ADBE_PKCS7_DETACHED = COSName.ADBE_PKCS7_DETACHED;
    public static final COSName SUBFILTER_ETSI_CADES_DETACHED = COSName.getPDFName("ETSI.CAdES.detached");
    public static final COSName SUBFILTER_ADBE_PKCS7_SHA1 = COSName.ADBE_PKCS7_SHA1;

    public PDSignature() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.SIG);
    }

    public PDSignature(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return getDictionary();
    }

    public COSDictionary getDictionary() {
        return this.dictionary;
    }

    public void setType(COSName cOSName) {
        this.dictionary.setItem(COSName.TYPE, (COSBase) cOSName);
    }

    public void setFilter(COSName cOSName) {
        this.dictionary.setItem(COSName.FILTER, (COSBase) cOSName);
    }

    public void setSubFilter(COSName cOSName) {
        this.dictionary.setItem(COSName.SUB_FILTER, (COSBase) cOSName);
    }

    public void setName(String str) {
        this.dictionary.setString(COSName.NAME, str);
    }

    public void setLocation(String str) {
        this.dictionary.setString(COSName.LOCATION, str);
    }

    public void setReason(String str) {
        this.dictionary.setString(COSName.REASON, str);
    }

    public void setContactInfo(String str) {
        this.dictionary.setString(COSName.CONTACT_INFO, str);
    }

    public void setSignDate(Calendar calendar) {
        this.dictionary.setDate(COSName.M, calendar);
    }

    public String getFilter() {
        return this.dictionary.getNameAsString(COSName.FILTER);
    }

    public String getSubFilter() {
        return this.dictionary.getNameAsString(COSName.SUB_FILTER);
    }

    public String getName() {
        return this.dictionary.getString(COSName.NAME);
    }

    public String getLocation() {
        return this.dictionary.getString(COSName.LOCATION);
    }

    public String getReason() {
        return this.dictionary.getString(COSName.REASON);
    }

    public String getContactInfo() {
        return this.dictionary.getString(COSName.CONTACT_INFO);
    }

    public Calendar getSignDate() {
        return this.dictionary.getDate(COSName.M);
    }

    public void setByteRange(int[] iArr) {
        if (iArr.length != 4) {
            return;
        }
        COSArray cOSArray = new COSArray();
        for (int i : iArr) {
            cOSArray.add((COSBase) COSInteger.get(i));
        }
        this.dictionary.setItem(COSName.BYTERANGE, (COSBase) cOSArray);
    }

    public int[] getByteRange() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.BYTERANGE);
        int size = cOSArray.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = cOSArray.getInt(i);
        }
        return iArr;
    }

    public byte[] getContents(InputStream inputStream) throws IOException {
        int[] byteRange = getByteRange();
        int i = byteRange[0] + byteRange[1] + 1;
        return getContents(new COSFilterInputStream(inputStream, new int[]{i, byteRange[2] - i}));
    }

    public byte[] getContents(byte[] bArr) throws IOException {
        int[] byteRange = getByteRange();
        int i = byteRange[0] + byteRange[1] + 1;
        return getContents(new COSFilterInputStream(bArr, new int[]{i, byteRange[2] - i}));
    }

    private byte[] getContents(COSFilterInputStream cOSFilterInputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = cOSFilterInputStream.read(bArr);
            if (read != -1) {
                if (bArr[0] == 60 || bArr[0] == 40) {
                    byteArrayOutputStream.write(bArr, 1, read);
                } else {
                    int i = read - 1;
                    if (bArr[i] == 62 || bArr[i] == 41) {
                        byteArrayOutputStream.write(bArr, 0, i);
                    } else {
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                }
            } else {
                cOSFilterInputStream.close();
                return COSString.parseHex(byteArrayOutputStream.toString()).getBytes();
            }
        }
    }

    public void setContents(byte[] bArr) {
        COSString cOSString = new COSString(bArr);
        cOSString.setForceHexForm(true);
        this.dictionary.setItem(COSName.CONTENTS, (COSBase) cOSString);
    }

    public byte[] getSignedContent(InputStream inputStream) throws IOException {
        COSFilterInputStream cOSFilterInputStream = null;
        try {
            COSFilterInputStream cOSFilterInputStream2 = new COSFilterInputStream(inputStream, getByteRange());
            try {
                byte[] byteArray = cOSFilterInputStream2.toByteArray();
                cOSFilterInputStream2.close();
                return byteArray;
            } catch (Throwable th) {
                th = th;
                cOSFilterInputStream = cOSFilterInputStream2;
                if (cOSFilterInputStream != null) {
                    cOSFilterInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public byte[] getSignedContent(byte[] bArr) throws IOException {
        COSFilterInputStream cOSFilterInputStream = null;
        try {
            COSFilterInputStream cOSFilterInputStream2 = new COSFilterInputStream(bArr, getByteRange());
            try {
                byte[] byteArray = cOSFilterInputStream2.toByteArray();
                cOSFilterInputStream2.close();
                return byteArray;
            } catch (Throwable th) {
                th = th;
                cOSFilterInputStream = cOSFilterInputStream2;
                if (cOSFilterInputStream != null) {
                    cOSFilterInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public PDPropBuild getPropBuild() {
        COSDictionary cOSDictionary = (COSDictionary) this.dictionary.getDictionaryObject(COSName.PROP_BUILD);
        if (cOSDictionary != null) {
            return new PDPropBuild(cOSDictionary);
        }
        return null;
    }

    public void setPropBuild(PDPropBuild pDPropBuild) {
        this.dictionary.setItem(COSName.PROP_BUILD, pDPropBuild);
    }
}
