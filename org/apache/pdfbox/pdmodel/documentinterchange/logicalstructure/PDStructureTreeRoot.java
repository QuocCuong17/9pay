package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;

import android.util.Log;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSDictionaryMap;
import org.apache.pdfbox.pdmodel.common.PDNameTreeNode;
import org.apache.pdfbox.pdmodel.common.PDNumberTreeNode;

/* loaded from: classes5.dex */
public class PDStructureTreeRoot extends PDStructureNode {
    private static final String TYPE = "StructTreeRoot";

    public PDStructureTreeRoot() {
        super(TYPE);
    }

    public PDStructureTreeRoot(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public COSArray getKArray() {
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(COSName.K);
        if (dictionaryObject == null) {
            return null;
        }
        if (dictionaryObject instanceof COSDictionary) {
            COSBase dictionaryObject2 = ((COSDictionary) dictionaryObject).getDictionaryObject(COSName.K);
            if (dictionaryObject2 instanceof COSArray) {
                return (COSArray) dictionaryObject2;
            }
            return null;
        }
        return (COSArray) dictionaryObject;
    }

    public COSBase getK() {
        return getCOSDictionary().getDictionaryObject(COSName.K);
    }

    public void setK(COSBase cOSBase) {
        getCOSDictionary().setItem(COSName.K, cOSBase);
    }

    public PDNameTreeNode getIDTree() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSDictionary().getDictionaryObject(COSName.ID_TREE);
        if (cOSDictionary != null) {
            return new PDNameTreeNode(cOSDictionary, PDStructureElement.class);
        }
        return null;
    }

    public void setIDTree(PDNameTreeNode pDNameTreeNode) {
        getCOSDictionary().setItem(COSName.ID_TREE, pDNameTreeNode);
    }

    public PDNumberTreeNode getParentTree() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSDictionary().getDictionaryObject(COSName.PARENT_TREE);
        if (cOSDictionary != null) {
            return new PDNumberTreeNode(cOSDictionary, COSBase.class);
        }
        return null;
    }

    public void setParentTree(PDNumberTreeNode pDNumberTreeNode) {
        getCOSDictionary().setItem(COSName.PARENT_TREE, pDNumberTreeNode);
    }

    public int getParentTreeNextKey() {
        return getCOSDictionary().getInt(COSName.PARENT_TREE_NEXT_KEY);
    }

    public void setParentTreeNextKey(int i) {
        getCOSDictionary().setInt(COSName.PARENT_TREE_NEXT_KEY, i);
    }

    public Map<String, Object> getRoleMap() {
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(COSName.ROLE_MAP);
        if (dictionaryObject instanceof COSDictionary) {
            try {
                return COSDictionaryMap.convertBasicTypesToMap((COSDictionary) dictionaryObject);
            } catch (IOException e) {
                Log.e("PdfBoxAndroid", e.getMessage(), e);
            }
        }
        return new Hashtable();
    }

    public void setRoleMap(Map<String, String> map) {
        COSDictionary cOSDictionary = new COSDictionary();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            cOSDictionary.setName(entry.getKey(), entry.getValue());
        }
        getCOSDictionary().setItem(COSName.ROLE_MAP, (COSBase) cOSDictionary);
    }
}
