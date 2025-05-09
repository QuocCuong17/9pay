package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;

import java.util.Iterator;
import java.util.Map;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDMarkedContent;

/* loaded from: classes5.dex */
public class PDStructureElement extends PDStructureNode {
    public static final String TYPE = "StructElem";

    public PDStructureElement(String str, PDStructureNode pDStructureNode) {
        super(TYPE);
        setStructureType(str);
        setParent(pDStructureNode);
    }

    public PDStructureElement(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getStructureType() {
        return getCOSDictionary().getNameAsString(COSName.S);
    }

    public void setStructureType(String str) {
        getCOSDictionary().setName(COSName.S, str);
    }

    public PDStructureNode getParent() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSDictionary().getDictionaryObject(COSName.P);
        if (cOSDictionary == null) {
            return null;
        }
        return PDStructureNode.create(cOSDictionary);
    }

    public void setParent(PDStructureNode pDStructureNode) {
        getCOSDictionary().setItem(COSName.P, pDStructureNode);
    }

    public String getElementIdentifier() {
        return getCOSDictionary().getString(COSName.ID);
    }

    public void setElementIdentifier(String str) {
        getCOSDictionary().setString(COSName.ID, str);
    }

    public PDPage getPage() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSDictionary().getDictionaryObject(COSName.PG);
        if (cOSDictionary == null) {
            return null;
        }
        return new PDPage(cOSDictionary);
    }

    public void setPage(PDPage pDPage) {
        getCOSDictionary().setItem(COSName.PG, pDPage);
    }

    public Revisions<PDAttributeObject> getAttributes() {
        Revisions<PDAttributeObject> revisions = new Revisions<>();
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(COSName.A);
        if (dictionaryObject instanceof COSArray) {
            Iterator<COSBase> it = ((COSArray) dictionaryObject).iterator();
            PDAttributeObject pDAttributeObject = null;
            while (it.hasNext()) {
                COSBase next = it.next();
                if (next instanceof COSDictionary) {
                    pDAttributeObject = PDAttributeObject.create((COSDictionary) next);
                    pDAttributeObject.setStructureElement(this);
                    revisions.addObject(pDAttributeObject, 0);
                } else if (next instanceof COSInteger) {
                    revisions.setRevisionNumber(pDAttributeObject, ((COSInteger) next).intValue());
                }
            }
        }
        if (dictionaryObject instanceof COSDictionary) {
            PDAttributeObject create = PDAttributeObject.create((COSDictionary) dictionaryObject);
            create.setStructureElement(this);
            revisions.addObject(create, 0);
        }
        return revisions;
    }

    public void setAttributes(Revisions<PDAttributeObject> revisions) {
        COSName cOSName = COSName.A;
        if (revisions.size() == 1 && revisions.getRevisionNumber(0) == 0) {
            PDAttributeObject object = revisions.getObject(0);
            object.setStructureElement(this);
            getCOSDictionary().setItem(cOSName, object);
            return;
        }
        COSArray cOSArray = new COSArray();
        for (int i = 0; i < revisions.size(); i++) {
            PDAttributeObject object2 = revisions.getObject(i);
            object2.setStructureElement(this);
            int revisionNumber = revisions.getRevisionNumber(i);
            if (revisionNumber < 0) {
                throw new IllegalArgumentException("The revision number shall be > -1");
            }
            cOSArray.add(object2);
            cOSArray.add((COSBase) COSInteger.get(revisionNumber));
        }
        getCOSDictionary().setItem(cOSName, (COSBase) cOSArray);
    }

    public void addAttribute(PDAttributeObject pDAttributeObject) {
        COSArray cOSArray;
        COSName cOSName = COSName.A;
        pDAttributeObject.setStructureElement(this);
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(cOSName);
        if (dictionaryObject instanceof COSArray) {
            cOSArray = (COSArray) dictionaryObject;
        } else {
            COSArray cOSArray2 = new COSArray();
            if (dictionaryObject != null) {
                cOSArray2.add(dictionaryObject);
                cOSArray2.add((COSBase) COSInteger.get(0L));
            }
            cOSArray = cOSArray2;
        }
        getCOSDictionary().setItem(cOSName, (COSBase) cOSArray);
        cOSArray.add(pDAttributeObject);
        cOSArray.add((COSBase) COSInteger.get(getRevisionNumber()));
    }

    public void removeAttribute(PDAttributeObject pDAttributeObject) {
        COSName cOSName = COSName.A;
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(cOSName);
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            cOSArray.remove(pDAttributeObject.getCOSObject());
            if (cOSArray.size() == 2 && cOSArray.getInt(1) == 0) {
                getCOSDictionary().setItem(cOSName, cOSArray.getObject(0));
            }
        } else {
            if (dictionaryObject instanceof COSObject) {
                dictionaryObject = ((COSObject) dictionaryObject).getObject();
            }
            if (pDAttributeObject.getCOSObject().equals(dictionaryObject)) {
                getCOSDictionary().setItem(cOSName, (COSBase) null);
            }
        }
        pDAttributeObject.setStructureElement(null);
    }

    public void attributeChanged(PDAttributeObject pDAttributeObject) {
        COSName cOSName = COSName.A;
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(cOSName);
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            for (int i = 0; i < cOSArray.size(); i++) {
                if (cOSArray.getObject(i).equals(pDAttributeObject.getCOSObject())) {
                    int i2 = i + 1;
                    if (cOSArray.get(i2) instanceof COSInteger) {
                        cOSArray.set(i2, (COSBase) COSInteger.get(getRevisionNumber()));
                    }
                }
            }
            return;
        }
        COSArray cOSArray2 = new COSArray();
        cOSArray2.add(dictionaryObject);
        cOSArray2.add((COSBase) COSInteger.get(getRevisionNumber()));
        getCOSDictionary().setItem(cOSName, (COSBase) cOSArray2);
    }

    public Revisions<String> getClassNames() {
        COSName cOSName = COSName.C;
        Revisions<String> revisions = new Revisions<>();
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(cOSName);
        if (dictionaryObject instanceof COSName) {
            revisions.addObject(((COSName) dictionaryObject).getName(), 0);
        }
        if (dictionaryObject instanceof COSArray) {
            Iterator<COSBase> it = ((COSArray) dictionaryObject).iterator();
            String str = null;
            while (it.hasNext()) {
                COSBase next = it.next();
                if (next instanceof COSName) {
                    str = ((COSName) next).getName();
                    revisions.addObject(str, 0);
                } else if (next instanceof COSInteger) {
                    revisions.setRevisionNumber(str, ((COSInteger) next).intValue());
                }
            }
        }
        return revisions;
    }

    public void setClassNames(Revisions<String> revisions) {
        if (revisions == null) {
            return;
        }
        COSName cOSName = COSName.C;
        if (revisions.size() == 1 && revisions.getRevisionNumber(0) == 0) {
            getCOSDictionary().setName(cOSName, revisions.getObject(0));
            return;
        }
        COSArray cOSArray = new COSArray();
        for (int i = 0; i < revisions.size(); i++) {
            String object = revisions.getObject(i);
            int revisionNumber = revisions.getRevisionNumber(i);
            if (revisionNumber < 0) {
                throw new IllegalArgumentException("The revision number shall be > -1");
            }
            cOSArray.add((COSBase) COSName.getPDFName(object));
            cOSArray.add((COSBase) COSInteger.get(revisionNumber));
        }
        getCOSDictionary().setItem(cOSName, (COSBase) cOSArray);
    }

    public void addClassName(String str) {
        COSArray cOSArray;
        if (str == null) {
            return;
        }
        COSName cOSName = COSName.C;
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(cOSName);
        if (dictionaryObject instanceof COSArray) {
            cOSArray = (COSArray) dictionaryObject;
        } else {
            COSArray cOSArray2 = new COSArray();
            if (dictionaryObject != null) {
                cOSArray2.add(dictionaryObject);
                cOSArray2.add((COSBase) COSInteger.get(0L));
            }
            cOSArray = cOSArray2;
        }
        getCOSDictionary().setItem(cOSName, (COSBase) cOSArray);
        cOSArray.add((COSBase) COSName.getPDFName(str));
        cOSArray.add((COSBase) COSInteger.get(getRevisionNumber()));
    }

    public void removeClassName(String str) {
        if (str == null) {
            return;
        }
        COSName cOSName = COSName.C;
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(cOSName);
        COSName pDFName = COSName.getPDFName(str);
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            cOSArray.remove(pDFName);
            if (cOSArray.size() == 2 && cOSArray.getInt(1) == 0) {
                getCOSDictionary().setItem(cOSName, cOSArray.getObject(0));
                return;
            }
            return;
        }
        if (dictionaryObject instanceof COSObject) {
            dictionaryObject = ((COSObject) dictionaryObject).getObject();
        }
        if (pDFName.equals(dictionaryObject)) {
            getCOSDictionary().setItem(cOSName, (COSBase) null);
        }
    }

    public int getRevisionNumber() {
        return getCOSDictionary().getInt(COSName.R, 0);
    }

    public void setRevisionNumber(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("The revision number shall be > -1");
        }
        getCOSDictionary().setInt(COSName.R, i);
    }

    public void incrementRevisionNumber() {
        setRevisionNumber(getRevisionNumber() + 1);
    }

    public String getTitle() {
        return getCOSDictionary().getString(COSName.T);
    }

    public void setTitle(String str) {
        getCOSDictionary().setString(COSName.T, str);
    }

    public String getLanguage() {
        return getCOSDictionary().getString(COSName.LANG);
    }

    public void setLanguage(String str) {
        getCOSDictionary().setString(COSName.LANG, str);
    }

    public String getAlternateDescription() {
        return getCOSDictionary().getString(COSName.ALT);
    }

    public void setAlternateDescription(String str) {
        getCOSDictionary().setString(COSName.ALT, str);
    }

    public String getExpandedForm() {
        return getCOSDictionary().getString(COSName.E);
    }

    public void setExpandedForm(String str) {
        getCOSDictionary().setString(COSName.E, str);
    }

    public String getActualText() {
        return getCOSDictionary().getString(COSName.ACTUAL_TEXT);
    }

    public void setActualText(String str) {
        getCOSDictionary().setString(COSName.ACTUAL_TEXT, str);
    }

    public String getStandardStructureType() {
        String structureType = getStructureType();
        if (!getRoleMap().containsKey(structureType)) {
            return structureType;
        }
        Object obj = getRoleMap().get(structureType);
        return obj instanceof String ? (String) obj : structureType;
    }

    public void appendKid(PDMarkedContent pDMarkedContent) {
        if (pDMarkedContent == null) {
            return;
        }
        appendKid(COSInteger.get(pDMarkedContent.getMCID()));
    }

    public void appendKid(PDMarkedContentReference pDMarkedContentReference) {
        appendObjectableKid(pDMarkedContentReference);
    }

    public void appendKid(PDObjectReference pDObjectReference) {
        appendObjectableKid(pDObjectReference);
    }

    public void insertBefore(COSInteger cOSInteger, Object obj) {
        insertBefore((COSBase) cOSInteger, obj);
    }

    public void insertBefore(PDMarkedContentReference pDMarkedContentReference, Object obj) {
        insertObjectableBefore(pDMarkedContentReference, obj);
    }

    public void insertBefore(PDObjectReference pDObjectReference, Object obj) {
        insertObjectableBefore(pDObjectReference, obj);
    }

    public void removeKid(COSInteger cOSInteger) {
        removeKid((COSBase) cOSInteger);
    }

    public void removeKid(PDMarkedContentReference pDMarkedContentReference) {
        removeObjectableKid(pDMarkedContentReference);
    }

    public void removeKid(PDObjectReference pDObjectReference) {
        removeObjectableKid(pDObjectReference);
    }

    private PDStructureTreeRoot getStructureTreeRoot() {
        PDStructureNode parent = getParent();
        while (parent instanceof PDStructureElement) {
            parent = ((PDStructureElement) parent).getParent();
        }
        if (parent instanceof PDStructureTreeRoot) {
            return (PDStructureTreeRoot) parent;
        }
        return null;
    }

    private Map<String, Object> getRoleMap() {
        PDStructureTreeRoot structureTreeRoot = getStructureTreeRoot();
        if (structureTreeRoot != null) {
            return structureTreeRoot.getRoleMap();
        }
        return null;
    }
}
