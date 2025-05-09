package com.google.zxing;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.flutter.plugins.firebase.crashlytics.Constants;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.xml.sax.SAXException;

/* loaded from: classes4.dex */
public final class HtmlAssetTranslator {
    private static final Pattern COMMA = Pattern.compile(",");

    private HtmlAssetTranslator() {
    }

    public static void main(String[] strArr) throws IOException {
        if (strArr.length < 3) {
            System.err.println("Usage: HtmlAssetTranslator android/assets/ (all|lang1[,lang2 ...]) (all|file1.html[ file2.html ...])");
            return;
        }
        Path path = Paths.get(strArr[0], new String[0]);
        Collection<String> parseLanguagesToTranslate = parseLanguagesToTranslate(path, strArr[1]);
        Collection<String> parseFileNamesToTranslate = parseFileNamesToTranslate(path, Arrays.asList(strArr).subList(2, strArr.length));
        Iterator<String> it = parseLanguagesToTranslate.iterator();
        while (it.hasNext()) {
            translateOneLanguage(path, it.next(), parseFileNamesToTranslate);
        }
    }

    private static Collection<String> parseLanguagesToTranslate(Path path, CharSequence charSequence) throws IOException {
        if (TtmlNode.COMBINE_ALL.equals(charSequence)) {
            ArrayList arrayList = new ArrayList();
            DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(path, new DirectoryStream.Filter<Path>() { // from class: com.google.zxing.HtmlAssetTranslator.1
                @Override // java.nio.file.DirectoryStream.Filter
                public boolean accept(Path path2) {
                    String path3 = path2.getFileName().toString();
                    return Files.isDirectory(path2, new LinkOption[0]) && !Files.isSymbolicLink(path2) && path3.startsWith("html-") && !"html-en".equals(path3);
                }
            });
            try {
                Iterator<Path> it = newDirectoryStream.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().getFileName().toString().substring(5));
                }
                if (newDirectoryStream != null) {
                    newDirectoryStream.close();
                }
                return arrayList;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (newDirectoryStream != null) {
                        try {
                            newDirectoryStream.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        return Arrays.asList(COMMA.split(charSequence));
    }

    private static Collection<String> parseFileNamesToTranslate(Path path, List<String> list) throws IOException {
        if (!TtmlNode.COMBINE_ALL.equals(list.get(0))) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(path.resolve("html-en"), "*.html");
        try {
            Iterator<Path> it = newDirectoryStream.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getFileName().toString());
            }
            if (newDirectoryStream != null) {
                newDirectoryStream.close();
            }
            return arrayList;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (newDirectoryStream != null) {
                    try {
                        newDirectoryStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private static void translateOneLanguage(Path path, String str, final Collection<String> collection) throws IOException {
        Path resolve = path.resolve("html-" + str);
        Files.createDirectories(resolve, new FileAttribute[0]);
        Path resolve2 = path.resolve("html-en");
        String translateString = StringsResourceTranslator.translateString("Translated by Google Translate.", str);
        DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(resolve2, new DirectoryStream.Filter<Path>() { // from class: com.google.zxing.HtmlAssetTranslator.2
            @Override // java.nio.file.DirectoryStream.Filter
            public boolean accept(Path path2) {
                String path3 = path2.getFileName().toString();
                return path3.endsWith(".html") && (collection.isEmpty() || collection.contains(path3));
            }
        });
        try {
            Iterator<Path> it = newDirectoryStream.iterator();
            while (it.hasNext()) {
                translateOneFile(str, resolve, it.next(), translateString);
            }
            if (newDirectoryStream != null) {
                newDirectoryStream.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (newDirectoryStream != null) {
                    try {
                        newDirectoryStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private static void translateOneFile(String str, Path path, Path path2, String str2) throws IOException {
        Path resolve = path.resolve(path2.getFileName());
        try {
            Document parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(path2.toFile());
            Element documentElement = parse.getDocumentElement();
            documentElement.normalize();
            LinkedList linkedList = new LinkedList();
            linkedList.add(documentElement);
            while (true) {
                if (!linkedList.isEmpty()) {
                    Node node = (Node) linkedList.poll();
                    if (shouldTranslate(node)) {
                        NodeList childNodes = node.getChildNodes();
                        for (int i = 0; i < childNodes.getLength(); i++) {
                            linkedList.add(childNodes.item(i));
                        }
                    }
                    if (node.getNodeType() == 3) {
                        String textContent = node.getTextContent();
                        if (!textContent.trim().isEmpty()) {
                            node.setTextContent(' ' + StringsResourceTranslator.translateString(textContent, str) + ' ');
                        }
                    }
                } else {
                    Text createTextNode = parse.createTextNode(str2);
                    Element createElement = parse.createElement(TtmlNode.TAG_P);
                    createElement.appendChild(createTextNode);
                    documentElement.getElementsByTagName("body").item(0).appendChild(createElement);
                    try {
                        Files.write(resolve, Collections.singleton(((DOMImplementationLS) DOMImplementationRegistry.newInstance().getDOMImplementation("LS")).createLSSerializer().writeToString(parse).replaceAll("<\\?xml[^>]+>", "<!DOCTYPE HTML>")), StandardCharsets.UTF_8, new OpenOption[0]);
                        return;
                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                        throw new IllegalStateException(e);
                    }
                }
            }
        } catch (ParserConfigurationException e2) {
            throw new IllegalStateException(e2);
        } catch (SAXException e3) {
            throw new IOException(e3);
        }
    }

    private static boolean shouldTranslate(Node node) {
        String textContent;
        Node namedItem;
        String textContent2;
        NamedNodeMap attributes = node.getAttributes();
        if ((attributes == null || (namedItem = attributes.getNamedItem(Constants.CLASS)) == null || (textContent2 = namedItem.getTextContent()) == null || !textContent2.contains("notranslate")) && !"script".equalsIgnoreCase(node.getNodeName()) && (textContent = node.getTextContent()) != null) {
            for (int i = 0; i < textContent.length(); i++) {
                if (Character.isLetter(textContent.charAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }
}
