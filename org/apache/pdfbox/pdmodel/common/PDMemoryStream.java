package org.apache.pdfbox.pdmodel.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.common.filespecification.PDFileSpecification;

/* loaded from: classes5.dex */
public class PDMemoryStream extends PDStream {
    private final byte[] data;

    @Override // org.apache.pdfbox.pdmodel.common.PDStream
    public void addCompression() {
    }

    public List getDecodeParams() throws IOException {
        return null;
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDStream
    public PDFileSpecification getFile() {
        return null;
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDStream
    public List getFileDecodeParams() throws IOException {
        return null;
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDStream
    public List getFileFilters() {
        return null;
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDStream
    public List getFilters() {
        return null;
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDStream
    public PDMetadata getMetadata() {
        return null;
    }

    public void setDecodeParams(List list) {
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDStream
    public void setFile(PDFileSpecification pDFileSpecification) {
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDStream
    public void setFileDecodeParams(List list) {
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDStream
    public void setFileFilters(List list) {
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDStream
    public void setMetadata(PDMetadata pDMetadata) {
    }

    public PDMemoryStream(byte[] bArr) {
        this.data = bArr;
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDStream, org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        throw new UnsupportedOperationException("not supported for memory stream");
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDStream
    public OutputStream createOutputStream() throws IOException {
        throw new UnsupportedOperationException("not supported for memory stream");
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDStream
    public InputStream createInputStream() throws IOException {
        return new ByteArrayInputStream(this.data);
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDStream
    public InputStream getPartiallyFilteredStream(List list) throws IOException {
        return createInputStream();
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDStream
    public COSStream getStream() {
        throw new UnsupportedOperationException("not supported for memory stream");
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDStream
    public int getLength() {
        return this.data.length;
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDStream
    public void setFilters(List list) {
        throw new UnsupportedOperationException("not supported for memory stream");
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDStream
    public byte[] getByteArray() throws IOException {
        return this.data;
    }
}
