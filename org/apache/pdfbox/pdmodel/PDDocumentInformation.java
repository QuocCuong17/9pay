package org.apache.pdfbox.pdmodel;

import com.facebook.internal.AnalyticsEvents;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public final class PDDocumentInformation implements COSObjectable {
    private COSDictionary info;

    public PDDocumentInformation() {
        this.info = new COSDictionary();
    }

    public PDDocumentInformation(COSDictionary cOSDictionary) {
        this.info = cOSDictionary;
    }

    public COSDictionary getDictionary() {
        return this.info;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.info;
    }

    public Object getPropertyStringValue(String str) {
        return this.info.getString(str);
    }

    public String getTitle() {
        return this.info.getString(COSName.TITLE);
    }

    public void setTitle(String str) {
        this.info.setString(COSName.TITLE, str);
    }

    public String getAuthor() {
        return this.info.getString(COSName.AUTHOR);
    }

    public void setAuthor(String str) {
        this.info.setString(COSName.AUTHOR, str);
    }

    public String getSubject() {
        return this.info.getString(COSName.SUBJECT);
    }

    public void setSubject(String str) {
        this.info.setString(COSName.SUBJECT, str);
    }

    public String getKeywords() {
        return this.info.getString(COSName.KEYWORDS);
    }

    public void setKeywords(String str) {
        this.info.setString(COSName.KEYWORDS, str);
    }

    public String getCreator() {
        return this.info.getString(COSName.CREATOR);
    }

    public void setCreator(String str) {
        this.info.setString(COSName.CREATOR, str);
    }

    public String getProducer() {
        return this.info.getString(COSName.PRODUCER);
    }

    public void setProducer(String str) {
        this.info.setString(COSName.PRODUCER, str);
    }

    public Calendar getCreationDate() {
        return this.info.getDate(COSName.CREATION_DATE);
    }

    public void setCreationDate(Calendar calendar) {
        this.info.setDate(COSName.CREATION_DATE, calendar);
    }

    public Calendar getModificationDate() {
        return this.info.getDate(COSName.MOD_DATE);
    }

    public void setModificationDate(Calendar calendar) {
        this.info.setDate(COSName.MOD_DATE, calendar);
    }

    public String getTrapped() {
        return this.info.getNameAsString(COSName.TRAPPED);
    }

    public Set<String> getMetadataKeys() {
        TreeSet treeSet = new TreeSet();
        Iterator<COSName> it = this.info.keySet().iterator();
        while (it.hasNext()) {
            treeSet.add(it.next().getName());
        }
        return treeSet;
    }

    public String getCustomMetadataValue(String str) {
        return this.info.getString(str);
    }

    public void setCustomMetadataValue(String str, String str2) {
        this.info.setString(str, str2);
    }

    public void setTrapped(String str) {
        if (str != null && !str.equals("True") && !str.equals("False") && !str.equals(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN)) {
            throw new RuntimeException("Valid values for trapped are 'True', 'False', or 'Unknown'");
        }
        this.info.setName(COSName.TRAPPED, str);
    }
}
