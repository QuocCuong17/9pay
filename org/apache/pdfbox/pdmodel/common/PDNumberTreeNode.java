package org.apache.pdfbox.pdmodel.common;

import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public class PDNumberTreeNode implements COSObjectable {
    private final COSDictionary node;
    private Class<? extends COSObjectable> valueType;

    public PDNumberTreeNode(Class<? extends COSObjectable> cls) {
        this.valueType = null;
        this.node = new COSDictionary();
        this.valueType = cls;
    }

    public PDNumberTreeNode(COSDictionary cOSDictionary, Class<? extends COSObjectable> cls) {
        this.valueType = null;
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

    public List<PDNumberTreeNode> getKids() {
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

    public void setKids(List<? extends PDNumberTreeNode> list) {
        if (list != null && list.size() > 0) {
            PDNumberTreeNode pDNumberTreeNode = list.get(0);
            PDNumberTreeNode pDNumberTreeNode2 = list.get(list.size() - 1);
            setLowerLimit(pDNumberTreeNode.getLowerLimit());
            setUpperLimit(pDNumberTreeNode2.getUpperLimit());
        } else if (this.node.getDictionaryObject(COSName.NUMS) == null) {
            this.node.setItem(COSName.LIMITS, (COSBase) null);
        }
        this.node.setItem(COSName.KIDS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public Object getValue(Integer num) throws IOException {
        Map<Integer, COSObjectable> numbers = getNumbers();
        Object obj = null;
        if (numbers != null) {
            return numbers.get(num);
        }
        List<PDNumberTreeNode> kids = getKids();
        if (kids != null) {
            for (int i = 0; i < kids.size() && obj == null; i++) {
                PDNumberTreeNode pDNumberTreeNode = kids.get(i);
                if (pDNumberTreeNode.getLowerLimit().compareTo(num) <= 0 && pDNumberTreeNode.getUpperLimit().compareTo(num) >= 0) {
                    obj = pDNumberTreeNode.getValue(num);
                }
            }
            return obj;
        }
        Log.w("PdfBoxAndroid", "NumberTreeNode does not have \"nums\" nor \"kids\" objects.");
        return null;
    }

    public Map<Integer, COSObjectable> getNumbers() throws IOException {
        COSArray cOSArray = (COSArray) this.node.getDictionaryObject(COSName.NUMS);
        if (cOSArray == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < cOSArray.size(); i += 2) {
            COSInteger cOSInteger = (COSInteger) cOSArray.getObject(i);
            hashMap.put(Integer.valueOf(cOSInteger.intValue()), convertCOSToPD(cOSArray.getObject(i + 1)));
        }
        return Collections.unmodifiableMap(hashMap);
    }

    protected COSObjectable convertCOSToPD(COSBase cOSBase) throws IOException {
        try {
            return this.valueType.getConstructor(cOSBase.getClass()).newInstance(cOSBase);
        } catch (Throwable th) {
            throw new IOException("Error while trying to create value in number tree:" + th.getMessage(), th);
        }
    }

    protected PDNumberTreeNode createChildNode(COSDictionary cOSDictionary) {
        return new PDNumberTreeNode(cOSDictionary, this.valueType);
    }

    public void setNumbers(Map<Integer, ? extends COSObjectable> map) {
        Integer num;
        Integer num2 = null;
        if (map == null) {
            this.node.setItem(COSName.NUMS, (COSObjectable) null);
            this.node.setItem(COSName.LIMITS, (COSObjectable) null);
            return;
        }
        ArrayList<Integer> arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        COSArray cOSArray = new COSArray();
        for (Integer num3 : arrayList) {
            cOSArray.add((COSBase) COSInteger.get(num3.intValue()));
            cOSArray.add(map.get(num3));
        }
        if (arrayList.size() > 0) {
            Integer num4 = (Integer) arrayList.get(0);
            num2 = (Integer) arrayList.get(arrayList.size() - 1);
            num = num4;
        } else {
            num = null;
        }
        setUpperLimit(num2);
        setLowerLimit(num);
        this.node.setItem(COSName.NUMS, (COSBase) cOSArray);
    }

    public Integer getUpperLimit() {
        COSArray cOSArray = (COSArray) this.node.getDictionaryObject(COSName.LIMITS);
        if (cOSArray == null || cOSArray.get(0) == null) {
            return null;
        }
        return Integer.valueOf(cOSArray.getInt(1));
    }

    private void setUpperLimit(Integer num) {
        COSArray cOSArray = (COSArray) this.node.getDictionaryObject(COSName.LIMITS);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            cOSArray.add((COSBase) null);
            cOSArray.add((COSBase) null);
            this.node.setItem(COSName.LIMITS, (COSBase) cOSArray);
        }
        if (num != null) {
            cOSArray.setInt(1, num.intValue());
        } else {
            cOSArray.set(1, (COSBase) null);
        }
    }

    public Integer getLowerLimit() {
        COSArray cOSArray = (COSArray) this.node.getDictionaryObject(COSName.LIMITS);
        if (cOSArray == null || cOSArray.get(0) == null) {
            return null;
        }
        return Integer.valueOf(cOSArray.getInt(0));
    }

    private void setLowerLimit(Integer num) {
        COSArray cOSArray = (COSArray) this.node.getDictionaryObject(COSName.LIMITS);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            cOSArray.add((COSBase) null);
            cOSArray.add((COSBase) null);
            this.node.setItem(COSName.LIMITS, (COSBase) cOSArray);
        }
        if (num != null) {
            cOSArray.setInt(0, num.intValue());
        } else {
            cOSArray.set(0, (COSBase) null);
        }
    }
}
