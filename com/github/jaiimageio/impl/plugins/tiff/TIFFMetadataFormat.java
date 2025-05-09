package com.github.jaiimageio.impl.plugins.tiff;

import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.imageio.metadata.IIOMetadataFormat;

/* loaded from: classes3.dex */
public abstract class TIFFMetadataFormat implements IIOMetadataFormat {
    protected String resourceBaseName;
    protected String rootName;
    protected Map elementInfoMap = new HashMap();
    protected Map attrInfoMap = new HashMap();

    public String getRootName() {
        return this.rootName;
    }

    private String getResource(String str, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        try {
            return ResourceBundle.getBundle(this.resourceBaseName, locale).getString(str);
        } catch (MissingResourceException unused) {
            return null;
        }
    }

    private TIFFElementInfo getElementInfo(String str) {
        if (str == null) {
            throw new IllegalArgumentException("elementName == null!");
        }
        TIFFElementInfo tIFFElementInfo = (TIFFElementInfo) this.elementInfoMap.get(str);
        if (tIFFElementInfo != null) {
            return tIFFElementInfo;
        }
        throw new IllegalArgumentException("No such element: " + str);
    }

    private TIFFAttrInfo getAttrInfo(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("elementName == null!");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("attrName == null!");
        }
        String str3 = str + RemoteSettings.FORWARD_SLASH_STRING + str2;
        TIFFAttrInfo tIFFAttrInfo = (TIFFAttrInfo) this.attrInfoMap.get(str3);
        if (tIFFAttrInfo != null) {
            return tIFFAttrInfo;
        }
        throw new IllegalArgumentException("No such attribute: " + str3);
    }

    public int getElementMinChildren(String str) {
        return getElementInfo(str).minChildren;
    }

    public int getElementMaxChildren(String str) {
        return getElementInfo(str).maxChildren;
    }

    public String getElementDescription(String str, Locale locale) {
        if (!this.elementInfoMap.containsKey(str)) {
            throw new IllegalArgumentException("No such element: " + str);
        }
        return getResource(str, locale);
    }

    public int getChildPolicy(String str) {
        return getElementInfo(str).childPolicy;
    }

    public String[] getChildNames(String str) {
        return getElementInfo(str).childNames;
    }

    public String[] getAttributeNames(String str) {
        return getElementInfo(str).attributeNames;
    }

    public int getAttributeValueType(String str, String str2) {
        return getAttrInfo(str, str2).valueType;
    }

    public int getAttributeDataType(String str, String str2) {
        return getAttrInfo(str, str2).dataType;
    }

    public boolean isAttributeRequired(String str, String str2) {
        return getAttrInfo(str, str2).isRequired;
    }

    public String getAttributeDefaultValue(String str, String str2) {
        return getAttrInfo(str, str2).defaultValue;
    }

    public String[] getAttributeEnumerations(String str, String str2) {
        return getAttrInfo(str, str2).enumerations;
    }

    public String getAttributeMinValue(String str, String str2) {
        return getAttrInfo(str, str2).minValue;
    }

    public String getAttributeMaxValue(String str, String str2) {
        return getAttrInfo(str, str2).maxValue;
    }

    public int getAttributeListMinLength(String str, String str2) {
        return getAttrInfo(str, str2).listMinLength;
    }

    public int getAttributeListMaxLength(String str, String str2) {
        return getAttrInfo(str, str2).listMaxLength;
    }

    public String getAttributeDescription(String str, String str2, Locale locale) {
        String str3 = str + RemoteSettings.FORWARD_SLASH_STRING + str2;
        if (!this.attrInfoMap.containsKey(str3)) {
            throw new IllegalArgumentException("No such attribute: " + str3);
        }
        return getResource(str3, locale);
    }

    public int getObjectValueType(String str) {
        return getElementInfo(str).objectValueType;
    }

    public Class getObjectClass(String str) {
        TIFFElementInfo elementInfo = getElementInfo(str);
        if (elementInfo.objectValueType == 0) {
            throw new IllegalArgumentException("Element cannot contain an object value: " + str);
        }
        return elementInfo.objectClass;
    }

    public Object getObjectDefaultValue(String str) {
        TIFFElementInfo elementInfo = getElementInfo(str);
        if (elementInfo.objectValueType == 0) {
            throw new IllegalArgumentException("Element cannot contain an object value: " + str);
        }
        return elementInfo.objectDefaultValue;
    }

    public Object[] getObjectEnumerations(String str) {
        TIFFElementInfo elementInfo = getElementInfo(str);
        if (elementInfo.objectValueType == 0) {
            throw new IllegalArgumentException("Element cannot contain an object value: " + str);
        }
        return elementInfo.objectEnumerations;
    }

    public Comparable getObjectMinValue(String str) {
        TIFFElementInfo elementInfo = getElementInfo(str);
        if (elementInfo.objectValueType == 0) {
            throw new IllegalArgumentException("Element cannot contain an object value: " + str);
        }
        return elementInfo.objectMinValue;
    }

    public Comparable getObjectMaxValue(String str) {
        TIFFElementInfo elementInfo = getElementInfo(str);
        if (elementInfo.objectValueType == 0) {
            throw new IllegalArgumentException("Element cannot contain an object value: " + str);
        }
        return elementInfo.objectMaxValue;
    }

    public int getObjectArrayMinLength(String str) {
        TIFFElementInfo elementInfo = getElementInfo(str);
        if (elementInfo.objectValueType == 0) {
            throw new IllegalArgumentException("Element cannot contain an object value: " + str);
        }
        return elementInfo.objectArrayMinLength;
    }

    public int getObjectArrayMaxLength(String str) {
        TIFFElementInfo elementInfo = getElementInfo(str);
        if (elementInfo.objectValueType == 0) {
            throw new IllegalArgumentException("Element cannot contain an object value: " + str);
        }
        return elementInfo.objectArrayMaxLength;
    }
}
