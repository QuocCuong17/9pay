package org.apache.pdfbox.cos;

import java.util.Calendar;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UnmodifibleCOSDictionary.java */
/* loaded from: classes5.dex */
public final class UnmodifiableCOSDictionary extends COSDictionary {
    /* JADX INFO: Access modifiers changed from: package-private */
    public UnmodifiableCOSDictionary(COSDictionary cOSDictionary) {
        this.items = cOSDictionary.items;
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setItem(COSName cOSName, COSBase cOSBase) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setItem(COSName cOSName, COSObjectable cOSObjectable) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setItem(String str, COSObjectable cOSObjectable) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setBoolean(String str, boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setBoolean(COSName cOSName, boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setItem(String str, COSBase cOSBase) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setName(String str, String str2) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setName(COSName cOSName, String str) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setDate(String str, Calendar calendar) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setDate(COSName cOSName, Calendar calendar) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setEmbeddedDate(String str, String str2, Calendar calendar) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setEmbeddedDate(String str, COSName cOSName, Calendar calendar) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setString(String str, String str2) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setString(COSName cOSName, String str) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setEmbeddedString(String str, String str2, String str3) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setEmbeddedString(String str, COSName cOSName, String str2) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setInt(String str, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setInt(COSName cOSName, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setLong(String str, long j) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setLong(COSName cOSName, long j) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setEmbeddedInt(String str, String str2, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setEmbeddedInt(String str, COSName cOSName, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setFloat(String str, float f) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void setFloat(COSName cOSName, float f) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void removeItem(COSName cOSName) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void addAll(COSDictionary cOSDictionary) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void mergeInto(COSDictionary cOSDictionary) {
        throw new UnsupportedOperationException();
    }
}
