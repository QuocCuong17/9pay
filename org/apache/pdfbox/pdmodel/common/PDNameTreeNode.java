package org.apache.pdfbox.pdmodel.common;

import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;

/* loaded from: classes5.dex */
public class PDNameTreeNode implements COSObjectable {
    private final COSDictionary node;
    private PDNameTreeNode parent;
    private Class<? extends COSObjectable> valueType;

    protected COSObjectable convertCOSToPD(COSBase cOSBase) throws IOException {
        return cOSBase;
    }

    public PDNameTreeNode(Class<? extends COSObjectable> cls) {
        this.valueType = null;
        this.parent = null;
        this.node = new COSDictionary();
        this.valueType = cls;
    }

    public PDNameTreeNode(COSDictionary cOSDictionary, Class<? extends COSObjectable> cls) {
        this.valueType = null;
        this.parent = null;
        this.node = cOSDictionary;
        this.valueType = cls;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.node;
    }

    public COSDictionary getCOSDictionary() {
        return this.node;
    }

    public PDNameTreeNode getParent() {
        return this.parent;
    }

    public void setParent(PDNameTreeNode pDNameTreeNode) {
        this.parent = pDNameTreeNode;
        calculateLimits();
    }

    public boolean isRootNode() {
        return this.parent == null;
    }

    public List<PDNameTreeNode> getKids() {
        COSArray cOSArray = (COSArray) this.node.getDictionaryObject(COSName.KIDS);
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(createChildNode((COSDictionary) cOSArray.getObject(i)));
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setKids(List<? extends PDNameTreeNode> list) {
        if (list != null && list.size() > 0) {
            Iterator<? extends PDNameTreeNode> it = list.iterator();
            while (it.hasNext()) {
                it.next().setParent(this);
            }
            this.node.setItem(COSName.KIDS, (COSBase) COSArrayList.converterToCOSArray(list));
            if (isRootNode()) {
                this.node.setItem(COSName.NAMES, (COSBase) null);
            }
        } else {
            this.node.setItem(COSName.KIDS, (COSBase) null);
            this.node.setItem(COSName.LIMITS, (COSBase) null);
        }
        calculateLimits();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.String] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x006c -> B:17:0x007a). Please report as a decompilation issue!!! */
    private void calculateLimits() {
        COSBase cOSBase = null;
        cOSBase = null;
        cOSBase = null;
        if (isRootNode()) {
            this.node.setItem(COSName.LIMITS, (COSBase) null);
            return;
        }
        List<PDNameTreeNode> kids = getKids();
        if (kids != null && kids.size() > 0) {
            PDNameTreeNode pDNameTreeNode = kids.get(0);
            PDNameTreeNode pDNameTreeNode2 = kids.get(kids.size() - 1);
            setLowerLimit(pDNameTreeNode.getLowerLimit());
            setUpperLimit(pDNameTreeNode2.getUpperLimit());
            return;
        }
        try {
            Map<String, COSObjectable> names = getNames();
            if (names != null && names.size() > 0) {
                Object[] array = names.keySet().toArray();
                setLowerLimit((String) array[0]);
                setUpperLimit((String) array[array.length - 1]);
            } else {
                this.node.setItem(COSName.LIMITS, (COSBase) null);
            }
        } catch (IOException e) {
            this.node.setItem(COSName.LIMITS, cOSBase);
            Log.e("PdfBoxAndroid", "Error while calculating the Limits of a PageNameTreeNode:", e);
            cOSBase = "PdfBoxAndroid";
        }
    }

    public Object getValue(String str) throws IOException {
        Map<String, COSObjectable> names = getNames();
        Object obj = null;
        if (names != null) {
            return names.get(str);
        }
        List<PDNameTreeNode> kids = getKids();
        if (kids != null) {
            for (int i = 0; i < kids.size() && obj == null; i++) {
                PDNameTreeNode pDNameTreeNode = kids.get(i);
                if (pDNameTreeNode.getLowerLimit().compareTo(str) <= 0 && pDNameTreeNode.getUpperLimit().compareTo(str) >= 0) {
                    obj = pDNameTreeNode.getValue(str);
                }
            }
            return obj;
        }
        Log.e("PdfBoxAndroid", "NameTreeNode does not have \"names\" nor \"kids\" objects.");
        return null;
    }

    public Map<String, COSObjectable> getNames() throws IOException {
        COSArray cOSArray = (COSArray) this.node.getDictionaryObject(COSName.NAMES);
        if (cOSArray == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < cOSArray.size(); i += 2) {
            linkedHashMap.put(((COSString) cOSArray.getObject(i)).getString(), convertCOSToPD(cOSArray.getObject(i + 1)));
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    protected PDNameTreeNode createChildNode(COSDictionary cOSDictionary) {
        return new PDNameTreeNode(cOSDictionary, this.valueType);
    }

    public void setNames(Map<String, ? extends COSObjectable> map) {
        if (map == null) {
            this.node.setItem(COSName.NAMES, (COSObjectable) null);
            this.node.setItem(COSName.LIMITS, (COSObjectable) null);
            return;
        }
        COSArray cOSArray = new COSArray();
        ArrayList<String> arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        for (String str : arrayList) {
            cOSArray.add((COSBase) new COSString(str));
            cOSArray.add(map.get(str));
        }
        this.node.setItem(COSName.NAMES, (COSBase) cOSArray);
        calculateLimits();
    }

    public String getUpperLimit() {
        COSArray cOSArray = (COSArray) this.node.getDictionaryObject(COSName.LIMITS);
        if (cOSArray != null) {
            return cOSArray.getString(1);
        }
        return null;
    }

    private void setUpperLimit(String str) {
        COSArray cOSArray = (COSArray) this.node.getDictionaryObject(COSName.LIMITS);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            cOSArray.add((COSBase) null);
            cOSArray.add((COSBase) null);
            this.node.setItem(COSName.LIMITS, (COSBase) cOSArray);
        }
        cOSArray.setString(1, str);
    }

    public String getLowerLimit() {
        COSArray cOSArray = (COSArray) this.node.getDictionaryObject(COSName.LIMITS);
        if (cOSArray != null) {
            return cOSArray.getString(0);
        }
        return null;
    }

    private void setLowerLimit(String str) {
        COSArray cOSArray = (COSArray) this.node.getDictionaryObject(COSName.LIMITS);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            cOSArray.add((COSBase) null);
            cOSArray.add((COSBase) null);
            this.node.setItem(COSName.LIMITS, (COSBase) cOSArray);
        }
        cOSArray.setString(0, str);
    }
}
