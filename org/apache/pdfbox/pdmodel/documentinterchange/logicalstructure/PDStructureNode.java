package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.pdmodel.common.COSArrayList;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public abstract class PDStructureNode implements COSObjectable {
    private final COSDictionary dictionary;

    public static PDStructureNode create(COSDictionary cOSDictionary) {
        String nameAsString = cOSDictionary.getNameAsString(COSName.TYPE);
        if ("StructTreeRoot".equals(nameAsString)) {
            return new PDStructureTreeRoot(cOSDictionary);
        }
        if (nameAsString == null || PDStructureElement.TYPE.equals(nameAsString)) {
            return new PDStructureElement(cOSDictionary);
        }
        throw new IllegalArgumentException("Dictionary must not include a Type entry with a value that is neither StructTreeRoot nor StructElem.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public COSDictionary getCOSDictionary() {
        return this.dictionary;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PDStructureNode(String str) {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setName(COSName.TYPE, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PDStructureNode(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.dictionary;
    }

    public String getType() {
        return getCOSDictionary().getNameAsString(COSName.TYPE);
    }

    public List<Object> getKids() {
        ArrayList arrayList = new ArrayList();
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(COSName.K);
        if (dictionaryObject instanceof COSArray) {
            Iterator<COSBase> it = ((COSArray) dictionaryObject).iterator();
            while (it.hasNext()) {
                Object createObject = createObject(it.next());
                if (createObject != null) {
                    arrayList.add(createObject);
                }
            }
        } else {
            Object createObject2 = createObject(dictionaryObject);
            if (createObject2 != null) {
                arrayList.add(createObject2);
            }
        }
        return arrayList;
    }

    public void setKids(List<Object> list) {
        getCOSDictionary().setItem(COSName.K, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public void appendKid(PDStructureElement pDStructureElement) {
        appendObjectableKid(pDStructureElement);
        pDStructureElement.setParent(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendObjectableKid(COSObjectable cOSObjectable) {
        if (cOSObjectable == null) {
            return;
        }
        appendKid(cOSObjectable.getCOSObject());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendKid(COSBase cOSBase) {
        if (cOSBase == null) {
            return;
        }
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(COSName.K);
        if (dictionaryObject == null) {
            getCOSDictionary().setItem(COSName.K, cOSBase);
            return;
        }
        if (dictionaryObject instanceof COSArray) {
            ((COSArray) dictionaryObject).add(cOSBase);
            return;
        }
        COSArray cOSArray = new COSArray();
        cOSArray.add(dictionaryObject);
        cOSArray.add(cOSBase);
        getCOSDictionary().setItem(COSName.K, (COSBase) cOSArray);
    }

    public void insertBefore(PDStructureElement pDStructureElement, Object obj) {
        insertObjectableBefore(pDStructureElement, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void insertObjectableBefore(COSObjectable cOSObjectable, Object obj) {
        if (cOSObjectable == null) {
            return;
        }
        insertBefore(cOSObjectable.getCOSObject(), obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void insertBefore(COSBase cOSBase, Object obj) {
        COSBase dictionaryObject;
        if (cOSBase == null || obj == null || (dictionaryObject = getCOSDictionary().getDictionaryObject(COSName.K)) == null) {
            return;
        }
        COSBase cOSBase2 = null;
        if (obj instanceof COSObjectable) {
            cOSBase2 = ((COSObjectable) obj).getCOSObject();
        } else if (obj instanceof COSInteger) {
            cOSBase2 = (COSBase) obj;
        }
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            cOSArray.add(cOSArray.indexOfObject(cOSBase2), cOSBase.getCOSObject());
            return;
        }
        boolean equals = dictionaryObject.equals(cOSBase2);
        if (!equals && (dictionaryObject instanceof COSObject)) {
            equals = ((COSObject) dictionaryObject).getObject().equals(cOSBase2);
        }
        if (equals) {
            COSArray cOSArray2 = new COSArray();
            cOSArray2.add(cOSBase);
            cOSArray2.add(cOSBase2);
            getCOSDictionary().setItem(COSName.K, (COSBase) cOSArray2);
        }
    }

    public boolean removeKid(PDStructureElement pDStructureElement) {
        boolean removeObjectableKid = removeObjectableKid(pDStructureElement);
        if (removeObjectableKid) {
            pDStructureElement.setParent(null);
        }
        return removeObjectableKid;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean removeObjectableKid(COSObjectable cOSObjectable) {
        if (cOSObjectable == null) {
            return false;
        }
        return removeKid(cOSObjectable.getCOSObject());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean removeKid(COSBase cOSBase) {
        COSBase dictionaryObject;
        if (cOSBase == null || (dictionaryObject = getCOSDictionary().getDictionaryObject(COSName.K)) == null) {
            return false;
        }
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            boolean removeObject = cOSArray.removeObject(cOSBase);
            if (cOSArray.size() == 1) {
                getCOSDictionary().setItem(COSName.K, cOSArray.getObject(0));
            }
            return removeObject;
        }
        boolean equals = dictionaryObject.equals(cOSBase);
        if (!equals && (dictionaryObject instanceof COSObject)) {
            equals = ((COSObject) dictionaryObject).getObject().equals(cOSBase);
        }
        if (!equals) {
            return false;
        }
        getCOSDictionary().setItem(COSName.K, (COSBase) null);
        return true;
    }

    protected Object createObject(COSBase cOSBase) {
        COSDictionary cOSDictionary;
        if (cOSBase instanceof COSDictionary) {
            cOSDictionary = (COSDictionary) cOSBase;
        } else {
            if (cOSBase instanceof COSObject) {
                COSBase object = ((COSObject) cOSBase).getObject();
                if (object instanceof COSDictionary) {
                    cOSDictionary = (COSDictionary) object;
                }
            }
            cOSDictionary = null;
        }
        if (cOSDictionary != null) {
            String nameAsString = cOSDictionary.getNameAsString(COSName.TYPE);
            if (nameAsString == null || PDStructureElement.TYPE.equals(nameAsString)) {
                return new PDStructureElement(cOSDictionary);
            }
            if (PDObjectReference.TYPE.equals(nameAsString)) {
                return new PDObjectReference(cOSDictionary);
            }
            if (PDMarkedContentReference.TYPE.equals(nameAsString)) {
                return new PDMarkedContentReference(cOSDictionary);
            }
        } else if (cOSBase instanceof COSInteger) {
            return Integer.valueOf(((COSInteger) cOSBase).intValue());
        }
        return null;
    }
}
