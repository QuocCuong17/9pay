package org.apache.pdfbox.pdfparser;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObjectKey;

/* loaded from: classes5.dex */
public class XrefTrailerResolver {
    private final Map<Long, XrefTrailerObj> bytePosToXrefMap = new HashMap();
    private XrefTrailerObj curXrefTrailerObj = null;
    private XrefTrailerObj resolvedXrefTrailer = null;

    /* loaded from: classes5.dex */
    public enum XRefType {
        TABLE,
        STREAM
    }

    /* loaded from: classes5.dex */
    private class XrefTrailerObj {
        protected COSDictionary trailer;
        private final Map<COSObjectKey, Long> xrefTable;
        private XRefType xrefType;

        private XrefTrailerObj() {
            this.trailer = null;
            this.xrefTable = new HashMap();
            this.xrefType = XRefType.TABLE;
        }
    }

    public final COSDictionary getFirstTrailer() {
        if (this.bytePosToXrefMap.isEmpty()) {
            return null;
        }
        return this.bytePosToXrefMap.get(new TreeSet(this.bytePosToXrefMap.keySet()).first()).trailer;
    }

    public final COSDictionary getLastTrailer() {
        if (this.bytePosToXrefMap.isEmpty()) {
            return null;
        }
        return this.bytePosToXrefMap.get(new TreeSet(this.bytePosToXrefMap.keySet()).last()).trailer;
    }

    public void nextXrefObj(long j, XRefType xRefType) {
        Map<Long, XrefTrailerObj> map = this.bytePosToXrefMap;
        Long valueOf = Long.valueOf(j);
        XrefTrailerObj xrefTrailerObj = new XrefTrailerObj();
        this.curXrefTrailerObj = xrefTrailerObj;
        map.put(valueOf, xrefTrailerObj);
        this.curXrefTrailerObj.xrefType = xRefType;
    }

    public XRefType getXrefType() {
        XrefTrailerObj xrefTrailerObj = this.resolvedXrefTrailer;
        if (xrefTrailerObj == null) {
            return null;
        }
        return xrefTrailerObj.xrefType;
    }

    public void setXRef(COSObjectKey cOSObjectKey, long j) {
        XrefTrailerObj xrefTrailerObj = this.curXrefTrailerObj;
        if (xrefTrailerObj != null) {
            xrefTrailerObj.xrefTable.put(cOSObjectKey, Long.valueOf(j));
            return;
        }
        Log.w("PdfBoxAndroid", "Cannot add XRef entry for '" + cOSObjectKey.getNumber() + "' because XRef start was not signalled.");
    }

    public void setTrailer(COSDictionary cOSDictionary) {
        XrefTrailerObj xrefTrailerObj = this.curXrefTrailerObj;
        if (xrefTrailerObj == null) {
            Log.w("PdfBoxAndroid", "Cannot add trailer because XRef start was not signalled.");
        } else {
            xrefTrailerObj.trailer = cOSDictionary;
        }
    }

    public COSDictionary getCurrentTrailer() {
        return this.curXrefTrailerObj.trailer;
    }

    public void setStartxref(long j) {
        if (this.resolvedXrefTrailer != null) {
            Log.w("PdfBoxAndroid", "Method must be called only ones with last startxref value.");
            return;
        }
        XrefTrailerObj xrefTrailerObj = new XrefTrailerObj();
        this.resolvedXrefTrailer = xrefTrailerObj;
        xrefTrailerObj.trailer = new COSDictionary();
        XrefTrailerObj xrefTrailerObj2 = this.bytePosToXrefMap.get(Long.valueOf(j));
        ArrayList arrayList = new ArrayList();
        if (xrefTrailerObj2 == null) {
            Log.w("PdfBoxAndroid", "Did not found XRef object at specified startxref position " + j);
            arrayList.addAll(this.bytePosToXrefMap.keySet());
            Collections.sort(arrayList);
        } else {
            this.resolvedXrefTrailer.xrefType = xrefTrailerObj2.xrefType;
            arrayList.add(Long.valueOf(j));
            while (true) {
                if (xrefTrailerObj2.trailer == null) {
                    break;
                }
                long j2 = xrefTrailerObj2.trailer.getLong(COSName.PREV, -1L);
                if (j2 == -1) {
                    break;
                }
                xrefTrailerObj2 = this.bytePosToXrefMap.get(Long.valueOf(j2));
                if (xrefTrailerObj2 == null) {
                    Log.w("PdfBoxAndroid", "Did not found XRef object pointed to by 'Prev' key at position " + j2);
                    break;
                }
                arrayList.add(Long.valueOf(j2));
                if (arrayList.size() >= this.bytePosToXrefMap.size()) {
                    break;
                }
            }
            Collections.reverse(arrayList);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            XrefTrailerObj xrefTrailerObj3 = this.bytePosToXrefMap.get((Long) it.next());
            if (xrefTrailerObj3.trailer != null) {
                this.resolvedXrefTrailer.trailer.addAll(xrefTrailerObj3.trailer);
            }
            this.resolvedXrefTrailer.xrefTable.putAll(xrefTrailerObj3.xrefTable);
        }
    }

    public COSDictionary getTrailer() {
        XrefTrailerObj xrefTrailerObj = this.resolvedXrefTrailer;
        if (xrefTrailerObj == null) {
            return null;
        }
        return xrefTrailerObj.trailer;
    }

    public Map<COSObjectKey, Long> getXrefTable() {
        XrefTrailerObj xrefTrailerObj = this.resolvedXrefTrailer;
        if (xrefTrailerObj == null) {
            return null;
        }
        return xrefTrailerObj.xrefTable;
    }

    public Set<Long> getContainedObjectNumbers(int i) {
        if (this.resolvedXrefTrailer == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        long j = -i;
        for (Map.Entry entry : this.resolvedXrefTrailer.xrefTable.entrySet()) {
            if (((Long) entry.getValue()).longValue() == j) {
                hashSet.add(Long.valueOf(((COSObjectKey) entry.getKey()).getNumber()));
            }
        }
        return hashSet;
    }
}
