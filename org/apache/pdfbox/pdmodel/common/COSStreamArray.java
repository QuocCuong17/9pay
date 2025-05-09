package org.apache.pdfbox.pdmodel.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.util.Vector;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.cos.ICOSVisitor;

/* loaded from: classes5.dex */
public class COSStreamArray extends COSStream {
    private COSStream firstStream;
    private COSArray streams;

    @Override // org.apache.pdfbox.cos.COSDictionary
    public String toString() {
        return "COSStream{}";
    }

    public COSStreamArray(COSArray cOSArray) {
        super(new COSDictionary());
        this.streams = cOSArray;
        if (cOSArray.size() > 0) {
            this.firstStream = (COSStream) cOSArray.getObject(0);
        }
    }

    public COSBase get(int i) {
        return this.streams.get(i);
    }

    public int getStreamCount() {
        return this.streams.size();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public COSBase getItem(COSName cOSName) {
        return this.firstStream.getItem(cOSName);
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public COSBase getDictionaryObject(COSName cOSName) {
        return this.firstStream.getDictionaryObject(cOSName);
    }

    public COSDictionary getDictionary() {
        return this.firstStream;
    }

    @Override // org.apache.pdfbox.cos.COSStream
    public InputStream getFilteredStream() throws IOException {
        throw new IOException("Error: Not allowed to get filtered stream from array of streams.");
    }

    @Override // org.apache.pdfbox.cos.COSStream
    public InputStream getUnfilteredStream() throws IOException {
        Vector vector = new Vector();
        byte[] bytes = IOUtils.LINE_SEPARATOR_UNIX.getBytes("ISO-8859-1");
        for (int i = 0; i < this.streams.size(); i++) {
            vector.add(((COSStream) this.streams.getObject(i)).getUnfilteredStream());
            vector.add(new ByteArrayInputStream(bytes));
        }
        return new SequenceInputStream(vector.elements());
    }

    @Override // org.apache.pdfbox.cos.COSStream, org.apache.pdfbox.cos.COSDictionary, org.apache.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return this.streams.accept(iCOSVisitor);
    }

    @Override // org.apache.pdfbox.cos.COSStream
    public COSBase getFilters() {
        return this.firstStream.getFilters();
    }

    @Override // org.apache.pdfbox.cos.COSStream
    public OutputStream createFilteredStream() throws IOException {
        return this.firstStream.createFilteredStream();
    }

    @Override // org.apache.pdfbox.cos.COSStream
    public OutputStream createFilteredStream(COSBase cOSBase) throws IOException {
        return this.firstStream.createFilteredStream(cOSBase);
    }

    @Override // org.apache.pdfbox.cos.COSStream
    public void setFilters(COSBase cOSBase) throws IOException {
        this.firstStream.setFilters(cOSBase);
    }

    @Override // org.apache.pdfbox.cos.COSStream
    public OutputStream createUnfilteredStream() throws IOException {
        return this.firstStream.createUnfilteredStream();
    }

    public void appendStream(COSStream cOSStream) {
        this.streams.add((COSBase) cOSStream);
    }

    public void insertCOSStream(PDStream pDStream) {
        COSArray cOSArray = new COSArray();
        cOSArray.add(pDStream);
        cOSArray.addAll(this.streams);
        this.streams.clear();
        this.streams = cOSArray;
    }
}
