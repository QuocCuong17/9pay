package org.apache.pdfbox.pdmodel.fdf;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.common.COSArrayList;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDTextStream;
import org.apache.pdfbox.pdmodel.interactive.action.PDAction;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionFactory;
import org.apache.pdfbox.pdmodel.interactive.action.PDAdditionalActions;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: classes5.dex */
public class FDFField implements COSObjectable {
    private COSDictionary field;

    public FDFField() {
        this.field = new COSDictionary();
    }

    public FDFField(COSDictionary cOSDictionary) {
        this.field = cOSDictionary;
    }

    public FDFField(Element element) throws IOException {
        this();
        setPartialFieldName(element.getAttribute("name"));
        NodeList childNodes = element.getChildNodes();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item instanceof Element) {
                Element element2 = (Element) item;
                if (element2.getTagName().equals("value")) {
                    setValue(XMLUtil.getNodeValue(element2));
                } else if (element2.getTagName().equals("value-richtext")) {
                    setRichText(new PDTextStream(XMLUtil.getNodeValue(element2)));
                } else if (element2.getTagName().equals("field")) {
                    arrayList.add(new FDFField(element2));
                }
            }
        }
        if (arrayList.size() > 0) {
            setKids(arrayList);
        }
    }

    public void writeXML(Writer writer) throws IOException {
        writer.write("<field name=\"" + getPartialFieldName() + "\">\n");
        Object value = getValue();
        if (value != null) {
            if (value instanceof String) {
                writer.write("<value>" + escapeXML((String) value) + "</value>\n");
            } else if (value instanceof PDTextStream) {
                writer.write("<value>" + escapeXML(((PDTextStream) value).getAsString()) + "</value>\n");
            }
        }
        PDTextStream richText = getRichText();
        if (richText != null) {
            writer.write("<value-richtext>" + escapeXML(richText.getAsString()) + "</value-richtext>\n");
        }
        List<FDFField> kids = getKids();
        if (kids != null) {
            Iterator<FDFField> it = kids.iterator();
            while (it.hasNext()) {
                it.next().writeXML(writer);
            }
        }
        writer.write("</field>\n");
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.field;
    }

    public COSDictionary getCOSDictionary() {
        return this.field;
    }

    public List<FDFField> getKids() {
        COSArray cOSArray = (COSArray) this.field.getDictionaryObject(COSName.KIDS);
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(new FDFField((COSDictionary) cOSArray.getObject(i)));
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setKids(List<FDFField> list) {
        this.field.setItem(COSName.KIDS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public String getPartialFieldName() {
        return this.field.getString(COSName.T);
    }

    public void setPartialFieldName(String str) {
        this.field.setString(COSName.T, str);
    }

    public Object getValue() throws IOException {
        COSBase dictionaryObject = this.field.getDictionaryObject(COSName.V);
        if (dictionaryObject instanceof COSName) {
            return ((COSName) dictionaryObject).getName();
        }
        if (dictionaryObject instanceof COSArray) {
            return COSArrayList.convertCOSStringCOSArrayToList((COSArray) dictionaryObject);
        }
        if ((dictionaryObject instanceof COSString) || (dictionaryObject instanceof COSStream)) {
            return PDTextStream.createTextStream(dictionaryObject);
        }
        if (dictionaryObject == null) {
            return null;
        }
        throw new IOException("Error:Unknown type for field import" + dictionaryObject);
    }

    public void setValue(Object obj) throws IOException {
        COSBase cOSBase;
        if (obj instanceof List) {
            cOSBase = COSArrayList.convertStringListToCOSStringCOSArray((List) obj);
        } else if (obj instanceof String) {
            cOSBase = COSName.getPDFName((String) obj);
        } else if (obj instanceof COSObjectable) {
            cOSBase = ((COSObjectable) obj).getCOSObject();
        } else {
            if (obj != null) {
                throw new IOException("Error:Unknown type for field import" + obj);
            }
            cOSBase = null;
        }
        this.field.setItem(COSName.V, cOSBase);
    }

    public Integer getFieldFlags() {
        COSNumber cOSNumber = (COSNumber) this.field.getDictionaryObject(COSName.FF);
        if (cOSNumber != null) {
            return Integer.valueOf(cOSNumber.intValue());
        }
        return null;
    }

    public void setFieldFlags(Integer num) {
        this.field.setItem(COSName.FF, (COSBase) (num != null ? COSInteger.get(num.intValue()) : null));
    }

    public void setFieldFlags(int i) {
        this.field.setInt(COSName.FF, i);
    }

    public Integer getSetFieldFlags() {
        COSNumber cOSNumber = (COSNumber) this.field.getDictionaryObject(COSName.SET_FF);
        if (cOSNumber != null) {
            return Integer.valueOf(cOSNumber.intValue());
        }
        return null;
    }

    public void setSetFieldFlags(Integer num) {
        this.field.setItem(COSName.SET_FF, (COSBase) (num != null ? COSInteger.get(num.intValue()) : null));
    }

    public void setSetFieldFlags(int i) {
        this.field.setInt(COSName.SET_FF, i);
    }

    public Integer getClearFieldFlags() {
        COSNumber cOSNumber = (COSNumber) this.field.getDictionaryObject(COSName.CLR_FF);
        if (cOSNumber != null) {
            return Integer.valueOf(cOSNumber.intValue());
        }
        return null;
    }

    public void setClearFieldFlags(Integer num) {
        this.field.setItem(COSName.CLR_FF, (COSBase) (num != null ? COSInteger.get(num.intValue()) : null));
    }

    public void setClearFieldFlags(int i) {
        this.field.setInt(COSName.CLR_FF, i);
    }

    public Integer getWidgetFieldFlags() {
        COSNumber cOSNumber = (COSNumber) this.field.getDictionaryObject(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION);
        if (cOSNumber != null) {
            return Integer.valueOf(cOSNumber.intValue());
        }
        return null;
    }

    public void setWidgetFieldFlags(Integer num) {
        this.field.setItem(COSName.F, (COSBase) (num != null ? COSInteger.get(num.intValue()) : null));
    }

    public void setWidgetFieldFlags(int i) {
        this.field.setInt(COSName.F, i);
    }

    public Integer getSetWidgetFieldFlags() {
        COSNumber cOSNumber = (COSNumber) this.field.getDictionaryObject(COSName.SET_F);
        if (cOSNumber != null) {
            return Integer.valueOf(cOSNumber.intValue());
        }
        return null;
    }

    public void setSetWidgetFieldFlags(Integer num) {
        this.field.setItem(COSName.SET_F, (COSBase) (num != null ? COSInteger.get(num.intValue()) : null));
    }

    public void setSetWidgetFieldFlags(int i) {
        this.field.setInt(COSName.SET_F, i);
    }

    public Integer getClearWidgetFieldFlags() {
        COSNumber cOSNumber = (COSNumber) this.field.getDictionaryObject(COSName.CLR_F);
        if (cOSNumber != null) {
            return Integer.valueOf(cOSNumber.intValue());
        }
        return null;
    }

    public void setClearWidgetFieldFlags(Integer num) {
        this.field.setItem(COSName.CLR_F, (COSBase) (num != null ? COSInteger.get(num.intValue()) : null));
    }

    public void setClearWidgetFieldFlags(int i) {
        this.field.setInt(COSName.CLR_F, i);
    }

    public PDAppearanceDictionary getAppearanceDictionary() {
        COSDictionary cOSDictionary = (COSDictionary) this.field.getDictionaryObject(COSName.AP);
        if (cOSDictionary != null) {
            return new PDAppearanceDictionary(cOSDictionary);
        }
        return null;
    }

    public void setAppearanceDictionary(PDAppearanceDictionary pDAppearanceDictionary) {
        this.field.setItem(COSName.AP, pDAppearanceDictionary);
    }

    public FDFNamedPageReference getAppearanceStreamReference() {
        COSDictionary cOSDictionary = (COSDictionary) this.field.getDictionaryObject(COSName.AP_REF);
        if (cOSDictionary != null) {
            return new FDFNamedPageReference(cOSDictionary);
        }
        return null;
    }

    public void setAppearanceStreamReference(FDFNamedPageReference fDFNamedPageReference) {
        this.field.setItem(COSName.AP_REF, fDFNamedPageReference);
    }

    public FDFIconFit getIconFit() {
        COSDictionary cOSDictionary = (COSDictionary) this.field.getDictionaryObject("IF");
        if (cOSDictionary != null) {
            return new FDFIconFit(cOSDictionary);
        }
        return null;
    }

    public void setIconFit(FDFIconFit fDFIconFit) {
        this.field.setItem(COSName.IF, fDFIconFit);
    }

    public List<Object> getOptions() {
        COSArray cOSArray = (COSArray) this.field.getDictionaryObject(COSName.OPT);
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            COSBase object = cOSArray.getObject(i);
            if (object instanceof COSString) {
                arrayList.add(((COSString) object).getString());
            } else {
                arrayList.add(new FDFOptionElement((COSArray) object));
            }
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setOptions(List list) {
        this.field.setItem(COSName.OPT, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public PDAction getAction() {
        return PDActionFactory.createAction((COSDictionary) this.field.getDictionaryObject(COSName.A));
    }

    public void setAction(PDAction pDAction) {
        this.field.setItem(COSName.A, pDAction);
    }

    public PDAdditionalActions getAdditionalActions() {
        COSDictionary cOSDictionary = (COSDictionary) this.field.getDictionaryObject(COSName.AA);
        if (cOSDictionary != null) {
            return new PDAdditionalActions(cOSDictionary);
        }
        return null;
    }

    public void setAdditionalActions(PDAdditionalActions pDAdditionalActions) {
        this.field.setItem(COSName.AA, pDAdditionalActions);
    }

    public PDTextStream getRichText() {
        return PDTextStream.createTextStream(this.field.getDictionaryObject(COSName.RV));
    }

    public void setRichText(PDTextStream pDTextStream) {
        this.field.setItem(COSName.RV, pDTextStream);
    }

    private String escapeXML(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '\"') {
                sb.append("&quot;");
            } else if (charAt == '<') {
                sb.append("&lt;");
            } else if (charAt == '>') {
                sb.append("&gt;");
            } else if (charAt == '&') {
                sb.append("&amp;");
            } else if (charAt == '\'') {
                sb.append("&apos;");
            } else if (charAt > '~') {
                sb.append("&#" + ((int) charAt) + ";");
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }
}
