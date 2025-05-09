package org.apache.pdfbox.cos;

import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.pdfparser.PDFObjectStreamParser;

/* loaded from: classes5.dex */
public class COSDocument extends COSBase implements Closeable {
    private boolean closed;
    private boolean isDecrypted;
    private boolean isXRefStream;
    private final Map<COSObjectKey, COSObject> objectPool;
    private final File scratchDirectory;
    private long startXref;
    private COSDictionary trailer;
    private final boolean useScratchFile;
    private float version;
    private boolean warnMissingClose;
    private final Map<COSObjectKey, Long> xrefTable;

    public COSDocument(boolean z) {
        this(null, z);
    }

    public COSDocument(File file, boolean z) {
        this.version = 1.4f;
        this.objectPool = new HashMap();
        this.xrefTable = new HashMap();
        this.warnMissingClose = true;
        this.isDecrypted = false;
        this.closed = false;
        this.scratchDirectory = file;
        this.useScratchFile = z;
    }

    public COSDocument() {
        this(false);
    }

    public COSStream createCOSStream() {
        return new COSStream(this.useScratchFile, this.scratchDirectory);
    }

    public COSStream createCOSStream(COSDictionary cOSDictionary) {
        return new COSStream(cOSDictionary, this.useScratchFile, this.scratchDirectory);
    }

    public COSObject getObjectByType(COSName cOSName) throws IOException {
        for (COSObject cOSObject : this.objectPool.values()) {
            COSBase object = cOSObject.getObject();
            if (object instanceof COSDictionary) {
                try {
                    COSBase item = ((COSDictionary) object).getItem(COSName.TYPE);
                    if (item instanceof COSName) {
                        if (((COSName) item).equals(cOSName)) {
                            return cOSObject;
                        }
                    } else if (item != null) {
                        Log.d("PdfBoxAndroid", "Expected a /Name object after /Type, got '" + item + "' instead");
                    }
                } catch (ClassCastException e) {
                    Log.w("PdfBoxAndroid", e.getMessage(), e);
                }
            }
        }
        return null;
    }

    public List<COSObject> getObjectsByType(String str) throws IOException {
        return getObjectsByType(COSName.getPDFName(str));
    }

    public List<COSObject> getObjectsByType(COSName cOSName) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (COSObject cOSObject : this.objectPool.values()) {
            COSBase object = cOSObject.getObject();
            if (object instanceof COSDictionary) {
                try {
                    COSBase item = ((COSDictionary) object).getItem(COSName.TYPE);
                    if (item instanceof COSName) {
                        if (((COSName) item).equals(cOSName)) {
                            arrayList.add(cOSObject);
                        }
                    } else if (item != null) {
                        Log.d("PdfBoxAndroid", "Expected a /Name object after /Type, got '" + item + "' instead");
                    }
                } catch (ClassCastException e) {
                    Log.w("PdfBoxAndroid", e.getMessage(), e);
                }
            }
        }
        return arrayList;
    }

    public void print() {
        Iterator<COSObject> it = this.objectPool.values().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public void setVersion(float f) {
        this.version = f;
    }

    public float getVersion() {
        return this.version;
    }

    public void setDecrypted() {
        this.isDecrypted = true;
    }

    public boolean isDecrypted() {
        return this.isDecrypted;
    }

    public boolean isEncrypted() {
        COSDictionary cOSDictionary = this.trailer;
        return (cOSDictionary == null || cOSDictionary.getDictionaryObject(COSName.ENCRYPT) == null) ? false : true;
    }

    public COSDictionary getEncryptionDictionary() {
        return (COSDictionary) this.trailer.getDictionaryObject(COSName.ENCRYPT);
    }

    public void setEncryptionDictionary(COSDictionary cOSDictionary) {
        this.trailer.setItem(COSName.ENCRYPT, (COSBase) cOSDictionary);
    }

    public List<COSDictionary> getSignatureDictionaries() throws IOException {
        List<COSDictionary> signatureFields = getSignatureFields(false);
        LinkedList linkedList = new LinkedList();
        Iterator<COSDictionary> it = signatureFields.iterator();
        while (it.hasNext()) {
            COSBase dictionaryObject = it.next().getDictionaryObject(COSName.V);
            if (dictionaryObject != null) {
                linkedList.add((COSDictionary) dictionaryObject);
            }
        }
        return linkedList;
    }

    public List<COSDictionary> getSignatureFields(boolean z) throws IOException {
        COSDictionary cOSDictionary;
        COSArray cOSArray;
        COSObject catalog = getCatalog();
        if (catalog != null && (cOSDictionary = (COSDictionary) catalog.getDictionaryObject(COSName.ACRO_FORM)) != null && (cOSArray = (COSArray) cOSDictionary.getDictionaryObject(COSName.FIELDS)) != null) {
            HashMap hashMap = new HashMap();
            Iterator<COSBase> it = cOSArray.iterator();
            while (it.hasNext()) {
                COSObject cOSObject = (COSObject) it.next();
                if (COSName.SIG.equals(cOSObject.getItem(COSName.FT)) && (cOSObject.getDictionaryObject(COSName.V) == null || !z)) {
                    hashMap.put(new COSObjectKey(cOSObject), (COSDictionary) cOSObject.getObject());
                }
            }
            return new LinkedList(hashMap.values());
        }
        return Collections.emptyList();
    }

    public COSArray getDocumentID() {
        return (COSArray) getTrailer().getDictionaryObject(COSName.ID);
    }

    public void setDocumentID(COSArray cOSArray) {
        getTrailer().setItem(COSName.ID, (COSBase) cOSArray);
    }

    public COSObject getCatalog() throws IOException {
        COSObject objectByType = getObjectByType(COSName.CATALOG);
        if (objectByType != null) {
            return objectByType;
        }
        throw new IOException("Catalog cannot be found");
    }

    public List<COSObject> getObjects() {
        return new ArrayList(this.objectPool.values());
    }

    public COSDictionary getTrailer() {
        return this.trailer;
    }

    public void setTrailer(COSDictionary cOSDictionary) {
        this.trailer = cOSDictionary;
    }

    @Override // org.apache.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromDocument(this);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        List<COSObject> objects = getObjects();
        if (objects != null) {
            Iterator<COSObject> it = objects.iterator();
            while (it.hasNext()) {
                COSBase object = it.next().getObject();
                if (object instanceof COSStream) {
                    ((COSStream) object).close();
                }
            }
        }
        this.closed = true;
    }

    public boolean isClosed() {
        return this.closed;
    }

    protected void finalize() throws IOException {
        if (this.closed) {
            return;
        }
        if (this.warnMissingClose) {
            Log.w("PdfBoxAndroid", "Warning: You did not close a PDF Document");
        }
        close();
    }

    public void setWarnMissingClose(boolean z) {
        this.warnMissingClose = z;
    }

    public void dereferenceObjectStreams() throws IOException {
        for (COSObject cOSObject : getObjectsByType(COSName.OBJ_STM)) {
            PDFObjectStreamParser pDFObjectStreamParser = new PDFObjectStreamParser((COSStream) cOSObject.getObject(), this);
            try {
                pDFObjectStreamParser.parse();
                for (COSObject cOSObject2 : pDFObjectStreamParser.getObjects()) {
                    COSObjectKey cOSObjectKey = new COSObjectKey(cOSObject2);
                    if (this.objectPool.get(cOSObjectKey) == null || this.objectPool.get(cOSObjectKey).getObject() == null || (this.xrefTable.containsKey(cOSObjectKey) && this.xrefTable.get(cOSObjectKey).longValue() == (-cOSObject.getObjectNumber()))) {
                        getObjectFromPool(cOSObjectKey).setObject(cOSObject2.getObject());
                    }
                }
            } finally {
                pDFObjectStreamParser.close();
            }
        }
    }

    public COSObject getObjectFromPool(COSObjectKey cOSObjectKey) throws IOException {
        COSObject cOSObject = cOSObjectKey != null ? this.objectPool.get(cOSObjectKey) : null;
        if (cOSObject == null) {
            cOSObject = new COSObject(null);
            if (cOSObjectKey != null) {
                cOSObject.setObjectNumber(cOSObjectKey.getNumber());
                cOSObject.setGenerationNumber(cOSObjectKey.getGeneration());
                this.objectPool.put(cOSObjectKey, cOSObject);
            }
        }
        return cOSObject;
    }

    public COSObject removeObject(COSObjectKey cOSObjectKey) {
        return this.objectPool.remove(cOSObjectKey);
    }

    public void addXRefTable(Map<COSObjectKey, Long> map) {
        this.xrefTable.putAll(map);
    }

    public Map<COSObjectKey, Long> getXrefTable() {
        return this.xrefTable;
    }

    public void setStartXref(long j) {
        this.startXref = j;
    }

    public long getStartXref() {
        return this.startXref;
    }

    public boolean isXRefStream() {
        return this.isXRefStream;
    }

    public void setIsXRefStream(boolean z) {
        this.isXRefStream = z;
    }
}
