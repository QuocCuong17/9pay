package org.apache.pdfbox.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

/* loaded from: classes5.dex */
public class PDFHighlighter extends PDFTextStripper {
    private static final String ENCODING = "UTF-16";
    private String[] searchedWords;
    private Writer highlighterOutput = null;
    private ByteArrayOutputStream textOS = null;
    private Writer textWriter = null;

    public PDFHighlighter() throws IOException {
        super.setLineSeparator("");
        super.setWordSeparator("");
        super.setShouldSeparateByBeads(false);
        super.setSuppressDuplicateOverlappingText(false);
    }

    public void generateXMLHighlight(PDDocument pDDocument, String str, Writer writer) throws IOException {
        generateXMLHighlight(pDDocument, new String[]{str}, writer);
    }

    public void generateXMLHighlight(PDDocument pDDocument, String[] strArr, Writer writer) throws IOException {
        this.highlighterOutput = writer;
        this.searchedWords = strArr;
        writer.write("<XML>\n<Body units=characters  version=2>\n<Highlight>\n");
        this.textOS = new ByteArrayOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.textOS, ENCODING);
        this.textWriter = outputStreamWriter;
        writeText(pDDocument, outputStreamWriter);
        this.highlighterOutput.write("</Highlight>\n</Body>\n</XML>");
        this.highlighterOutput.flush();
    }

    @Override // org.apache.pdfbox.text.PDFTextStripper
    protected void endPage(PDPage pDPage) throws IOException {
        this.textWriter.flush();
        String str = new String(this.textOS.toByteArray(), ENCODING);
        this.textOS.reset();
        if (str.indexOf(97) != -1) {
            str = str.replaceAll("a[0-9]{1,3}", ".");
        }
        for (String str2 : this.searchedWords) {
            Matcher matcher = Pattern.compile(str2, 2).matcher(str);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                Writer writer = this.highlighterOutput;
                StringBuilder sb = new StringBuilder();
                sb.append("    <loc pg=");
                sb.append(getCurrentPageNo() - 1);
                sb.append(" pos=");
                sb.append(start);
                sb.append(" len=");
                sb.append(end - start);
                sb.append(">\n");
                writer.write(sb.toString());
            }
        }
    }

    public static void main(String[] strArr) throws IOException {
        PDFHighlighter pDFHighlighter = new PDFHighlighter();
        PDDocument pDDocument = null;
        try {
            if (strArr.length < 2) {
                usage();
            }
            int length = strArr.length - 1;
            String[] strArr2 = new String[length];
            System.arraycopy(strArr, 1, strArr2, 0, length);
            pDDocument = PDDocument.load(new File(strArr[0]));
            pDFHighlighter.generateXMLHighlight(pDDocument, strArr2, new OutputStreamWriter(System.out));
        } finally {
            if (pDDocument != null) {
                pDDocument.close();
            }
        }
    }

    private static void usage() {
        System.err.println("usage: java " + PDFHighlighter.class.getName() + " <pdf file> word1 word2 word3 ...");
        System.exit(1);
    }
}
