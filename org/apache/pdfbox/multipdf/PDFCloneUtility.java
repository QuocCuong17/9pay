package org.apache.pdfbox.multipdf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.COSStreamArray;
import org.apache.pdfbox.pdmodel.common.PDStream;

/* loaded from: classes5.dex */
class PDFCloneUtility {
    private final Map<Object, COSBase> clonedVersion = new HashMap();
    private final PDDocument destination;

    public PDFCloneUtility(PDDocument pDDocument) {
        this.destination = pDDocument;
    }

    public PDDocument getDestination() {
        return this.destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [org.apache.pdfbox.cos.COSBase] */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v2, types: [org.apache.pdfbox.cos.COSBase] */
    /* JADX WARN: Type inference failed for: r0v22, types: [org.apache.pdfbox.cos.COSStream] */
    /* JADX WARN: Type inference failed for: r0v26, types: [org.apache.pdfbox.pdmodel.common.COSStreamArray, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v27, types: [org.apache.pdfbox.cos.COSArray, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v3, types: [org.apache.pdfbox.cos.COSBase, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v31, types: [org.apache.pdfbox.cos.COSBase, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v36, types: [org.apache.pdfbox.cos.COSBase, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v37, types: [org.apache.pdfbox.cos.COSArray] */
    public COSBase cloneForNewDocument(Object obj) throws IOException {
        if (obj == null) {
            return null;
        }
        COSBase cOSBase = this.clonedVersion.get(obj);
        if (cOSBase == 0) {
            if (obj instanceof List) {
                cOSBase = new COSArray();
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    cOSBase.add(cloneForNewDocument(it.next()));
                }
            } else if ((obj instanceof COSObjectable) && !(obj instanceof COSBase)) {
                cOSBase = cloneForNewDocument(((COSObjectable) obj).getCOSObject());
                this.clonedVersion.put(obj, cOSBase);
            } else if (obj instanceof COSObject) {
                cOSBase = cloneForNewDocument(((COSObject) obj).getObject());
                this.clonedVersion.put(obj, cOSBase);
            } else {
                int i = 0;
                if (obj instanceof COSArray) {
                    cOSBase = new COSArray();
                    COSArray cOSArray = (COSArray) obj;
                    while (i < cOSArray.size()) {
                        cOSBase.add(cloneForNewDocument(cOSArray.get(i)));
                        i++;
                    }
                    this.clonedVersion.put(obj, cOSBase);
                } else if (obj instanceof COSStreamArray) {
                    COSStreamArray cOSStreamArray = (COSStreamArray) obj;
                    if (cOSStreamArray.size() > 0) {
                        throw new IllegalStateException("Cannot close stream array with items next to the streams.");
                    }
                    COSArray cOSArray2 = new COSArray();
                    while (i < cOSStreamArray.getStreamCount()) {
                        cOSArray2.add(cloneForNewDocument(cOSStreamArray.get(i)));
                        i++;
                    }
                    cOSBase = new COSStreamArray(cOSArray2);
                    this.clonedVersion.put(obj, cOSBase);
                } else if (obj instanceof COSStream) {
                    COSStream cOSStream = (COSStream) obj;
                    PDStream pDStream = new PDStream(this.destination, cOSStream.getFilteredStream(), true);
                    this.clonedVersion.put(obj, pDStream.getStream());
                    for (Map.Entry<COSName, COSBase> entry : cOSStream.entrySet()) {
                        pDStream.getStream().setItem(entry.getKey(), cloneForNewDocument(entry.getValue()));
                    }
                    cOSBase = pDStream.getStream();
                } else if (obj instanceof COSDictionary) {
                    COSDictionary cOSDictionary = new COSDictionary();
                    this.clonedVersion.put(obj, cOSDictionary);
                    for (Map.Entry<COSName, COSBase> entry2 : ((COSDictionary) obj).entrySet()) {
                        cOSDictionary.setItem(entry2.getKey(), cloneForNewDocument(entry2.getValue()));
                    }
                    cOSBase = cOSDictionary;
                } else {
                    cOSBase = (COSBase) obj;
                }
            }
        }
        this.clonedVersion.put(obj, cOSBase);
        return cOSBase;
    }

    public void cloneMerge(COSObjectable cOSObjectable, COSObjectable cOSObjectable2) throws IOException {
        COSBase cOSBase;
        if (cOSObjectable != null && (cOSBase = this.clonedVersion.get(cOSObjectable)) == null) {
            if (cOSObjectable instanceof List) {
                COSArray cOSArray = new COSArray();
                Iterator it = ((List) cOSObjectable).iterator();
                while (it.hasNext()) {
                    cOSArray.add(cloneForNewDocument(it.next()));
                }
                ((List) cOSObjectable2).add(cOSArray);
            } else if ((cOSObjectable instanceof COSObjectable) && !(cOSObjectable instanceof COSBase)) {
                cloneMerge(cOSObjectable.getCOSObject(), cOSObjectable2.getCOSObject());
                this.clonedVersion.put(cOSObjectable, cOSBase);
            } else if (cOSObjectable instanceof COSObject) {
                if (cOSObjectable2 instanceof COSObject) {
                    cloneMerge(((COSObject) cOSObjectable).getObject(), ((COSObject) cOSObjectable2).getObject());
                } else if (cOSObjectable2 instanceof COSDictionary) {
                    cloneMerge(((COSObject) cOSObjectable).getObject(), cOSObjectable2);
                }
                this.clonedVersion.put(cOSObjectable, cOSBase);
            } else if (cOSObjectable instanceof COSArray) {
                COSArray cOSArray2 = (COSArray) cOSObjectable;
                for (int i = 0; i < cOSArray2.size(); i++) {
                    ((COSArray) cOSObjectable2).add(cloneForNewDocument(cOSArray2.get(i)));
                }
                this.clonedVersion.put(cOSObjectable, cOSBase);
            } else if (cOSObjectable instanceof COSStream) {
                COSStream cOSStream = (COSStream) cOSObjectable;
                PDStream pDStream = new PDStream(this.destination, cOSStream.getFilteredStream(), true);
                this.clonedVersion.put(cOSObjectable, pDStream.getStream());
                for (Map.Entry<COSName, COSBase> entry : cOSStream.entrySet()) {
                    pDStream.getStream().setItem(entry.getKey(), cloneForNewDocument(entry.getValue()));
                }
                cOSBase = pDStream.getStream();
            } else if (cOSObjectable instanceof COSDictionary) {
                this.clonedVersion.put(cOSObjectable, cOSBase);
                for (Map.Entry<COSName, COSBase> entry2 : ((COSDictionary) cOSObjectable).entrySet()) {
                    COSName key = entry2.getKey();
                    COSBase value = entry2.getValue();
                    COSDictionary cOSDictionary = (COSDictionary) cOSObjectable2;
                    if (cOSDictionary.getItem(key) != null) {
                        cloneMerge(value, cOSDictionary.getItem(key));
                    } else {
                        cOSDictionary.setItem(key, cloneForNewDocument(value));
                    }
                }
            } else {
                cOSBase = (COSBase) cOSObjectable;
            }
            this.clonedVersion.put(cOSObjectable, cOSBase);
        }
    }
}
