package org.apache.pdfbox.pdmodel.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNull;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.filter.FilterFactory;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.filespecification.PDFileSpecification;

/* loaded from: classes5.dex */
public class PDStream implements COSObjectable {
    private COSStream stream;

    /* JADX INFO: Access modifiers changed from: protected */
    public PDStream() {
    }

    public PDStream(PDDocument pDDocument) {
        this.stream = pDDocument.getDocument().createCOSStream();
    }

    public PDStream(COSStream cOSStream) {
        this.stream = cOSStream;
    }

    public PDStream(PDDocument pDDocument, InputStream inputStream) throws IOException {
        this(pDDocument, inputStream, false);
    }

    public PDStream(PDDocument pDDocument, InputStream inputStream, boolean z) throws IOException {
        OutputStream createUnfilteredStream;
        OutputStream outputStream = null;
        try {
            COSStream createCOSStream = pDDocument.getDocument().createCOSStream();
            this.stream = createCOSStream;
            if (z) {
                createUnfilteredStream = createCOSStream.createFilteredStream();
            } else {
                createUnfilteredStream = createCOSStream.createUnfilteredStream();
            }
            outputStream = createUnfilteredStream;
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                } else {
                    outputStream.write(bArr, 0, read);
                }
            }
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public void addCompression() {
        if (getFilters() == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(COSName.FLATE_DECODE);
            setFilters(arrayList);
        }
    }

    public static PDStream createFromCOS(COSBase cOSBase) throws IOException {
        if (cOSBase instanceof COSStream) {
            return new PDStream((COSStream) cOSBase);
        }
        if (cOSBase instanceof COSArray) {
            COSArray cOSArray = (COSArray) cOSBase;
            if (cOSArray.size() > 0) {
                return new PDStream(new COSStreamArray(cOSArray));
            }
        } else if (cOSBase != null) {
            throw new IOException("Contents are unknown type:" + cOSBase.getClass().getName());
        }
        return null;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.stream;
    }

    public OutputStream createOutputStream() throws IOException {
        return this.stream.createUnfilteredStream();
    }

    public InputStream createInputStream() throws IOException {
        return this.stream.getUnfilteredStream();
    }

    public InputStream getPartiallyFilteredStream(List<String> list) throws IOException {
        InputStream filteredStream = this.stream.getFilteredStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        List<COSName> filters = getFilters();
        boolean z = false;
        for (int i = 0; i < filters.size() && !z; i++) {
            COSName cOSName = filters.get(i);
            if (list.contains(cOSName.getName())) {
                z = true;
            } else {
                FilterFactory.INSTANCE.getFilter(cOSName).decode(filteredStream, byteArrayOutputStream, this.stream, i);
                IOUtils.closeQuietly(filteredStream);
                filteredStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.reset();
            }
        }
        return filteredStream;
    }

    public COSStream getStream() {
        return this.stream;
    }

    public int getLength() {
        return this.stream.getInt(COSName.LENGTH, 0);
    }

    public List<COSName> getFilters() {
        COSBase filters = this.stream.getFilters();
        if (filters instanceof COSName) {
            COSName cOSName = (COSName) filters;
            return new COSArrayList(cOSName, cOSName, this.stream, COSName.FILTER);
        }
        if (filters instanceof COSArray) {
            return ((COSArray) filters).toList();
        }
        return null;
    }

    public void setFilters(List<COSName> list) {
        this.stream.setItem(COSName.FILTER, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public List<Object> getDecodeParms() throws IOException {
        COSBase dictionaryObject = this.stream.getDictionaryObject(COSName.DECODE_PARMS);
        if (dictionaryObject == null) {
            dictionaryObject = this.stream.getDictionaryObject(COSName.DP);
        }
        if (dictionaryObject instanceof COSDictionary) {
            return new COSArrayList(COSDictionaryMap.convertBasicTypesToMap((COSDictionary) dictionaryObject), dictionaryObject, this.stream, COSName.DECODE_PARMS);
        }
        if (!(dictionaryObject instanceof COSArray)) {
            return null;
        }
        COSArray cOSArray = (COSArray) dictionaryObject;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(COSDictionaryMap.convertBasicTypesToMap((COSDictionary) cOSArray.getObject(i)));
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setDecodeParms(List<?> list) {
        this.stream.setItem(COSName.DECODE_PARMS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public PDFileSpecification getFile() throws IOException {
        return PDFileSpecification.createFS(this.stream.getDictionaryObject(COSName.F));
    }

    public void setFile(PDFileSpecification pDFileSpecification) {
        this.stream.setItem(COSName.F, pDFileSpecification);
    }

    public List<String> getFileFilters() {
        COSBase dictionaryObject = this.stream.getDictionaryObject(COSName.F_FILTER);
        if (dictionaryObject instanceof COSName) {
            COSName cOSName = (COSName) dictionaryObject;
            return new COSArrayList(cOSName.getName(), cOSName, this.stream, COSName.F_FILTER);
        }
        if (dictionaryObject instanceof COSArray) {
            return COSArrayList.convertCOSNameCOSArrayToList((COSArray) dictionaryObject);
        }
        return null;
    }

    public void setFileFilters(List<String> list) {
        this.stream.setItem(COSName.F_FILTER, (COSBase) COSArrayList.convertStringListToCOSNameCOSArray(list));
    }

    public List<Object> getFileDecodeParams() throws IOException {
        COSBase dictionaryObject = this.stream.getDictionaryObject(COSName.F_DECODE_PARMS);
        if (dictionaryObject instanceof COSDictionary) {
            return new COSArrayList(COSDictionaryMap.convertBasicTypesToMap((COSDictionary) dictionaryObject), dictionaryObject, this.stream, COSName.F_DECODE_PARMS);
        }
        if (!(dictionaryObject instanceof COSArray)) {
            return null;
        }
        COSArray cOSArray = (COSArray) dictionaryObject;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(COSDictionaryMap.convertBasicTypesToMap((COSDictionary) cOSArray.getObject(i)));
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setFileDecodeParams(List<?> list) {
        this.stream.setItem("FDecodeParams", (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public byte[] getByteArray() throws IOException {
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        try {
            inputStream = createInputStream();
            while (true) {
                try {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                } catch (Throwable th) {
                    th = th;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    throw th;
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
    }

    public String getInputStreamAsString() throws IOException {
        return new String(getByteArray(), "ISO-8859-1");
    }

    public PDMetadata getMetadata() {
        COSBase dictionaryObject = this.stream.getDictionaryObject(COSName.METADATA);
        if (dictionaryObject != null) {
            if (dictionaryObject instanceof COSStream) {
                return new PDMetadata((COSStream) dictionaryObject);
            }
            if (!(dictionaryObject instanceof COSNull)) {
                throw new IllegalStateException("Expected a COSStream but was a " + dictionaryObject.getClass().getSimpleName());
            }
        }
        return null;
    }

    public void setMetadata(PDMetadata pDMetadata) {
        this.stream.setItem(COSName.METADATA, pDMetadata);
    }

    public int getDecodedStreamLength() {
        return this.stream.getInt(COSName.DL);
    }

    public void setDecodedStreamLength(int i) {
        this.stream.setInt(COSName.DL, i);
    }
}
