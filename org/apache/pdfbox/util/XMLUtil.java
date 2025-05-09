package org.apache.pdfbox.util;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/* loaded from: classes5.dex */
public class XMLUtil {
    private XMLUtil() {
    }

    public static Document parse(InputStream inputStream) throws IOException {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public static String getNodeValue(Element element) {
        NodeList childNodes = element.getChildNodes();
        String str = "";
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item instanceof Text) {
                str = item.getNodeValue();
            }
        }
        return str;
    }
}
