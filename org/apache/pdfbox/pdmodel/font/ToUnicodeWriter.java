package org.apache.pdfbox.pdmodel.font;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import org.apache.pdfbox.util.Charsets;

/* loaded from: classes5.dex */
final class ToUnicodeWriter {
    private final Map<Integer, String> cidToUnicode = new TreeMap();
    private int wMode = 0;

    public void setWMode(int i) {
        this.wMode = i;
    }

    public void add(int i, String str) {
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("CID is not valid");
        }
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Text is null or empty");
        }
        this.cidToUnicode.put(Integer.valueOf(i), str);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, Charsets.US_ASCII));
        writeLine(bufferedWriter, "/CIDInit /ProcSet findresource begin");
        writeLine(bufferedWriter, "12 dict begin\n");
        writeLine(bufferedWriter, "begincmap");
        writeLine(bufferedWriter, "/CIDSystemInfo");
        writeLine(bufferedWriter, "<< /Registry ()");
        writeLine(bufferedWriter, "/Ordering ()");
        writeLine(bufferedWriter, "/Supplement ");
        writeLine(bufferedWriter, ">> def\n");
        writeLine(bufferedWriter, "/CMapName /Adobe-Identity-UCS def");
        writeLine(bufferedWriter, "/CMapType 2 def\n");
        if (this.wMode != 0) {
            writeLine(bufferedWriter, "/WMode /" + this.wMode + " def");
        }
        writeLine(bufferedWriter, "1 begincodespacerange");
        writeLine(bufferedWriter, "<0000> <FFFF>");
        writeLine(bufferedWriter, "endcodespacerange\n");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        String str = null;
        int i = -1;
        int i2 = -1;
        for (Map.Entry<Integer, String> entry : this.cidToUnicode.entrySet()) {
            int intValue = entry.getKey().intValue();
            String value = entry.getValue();
            if (intValue == i + 1 && str.codePointCount(0, str.length()) == 1 && value.codePointAt(0) == str.codePointAt(0) + 1 && str.codePointAt(0) + 1 <= 255 - (intValue - i2)) {
                arrayList2.set(arrayList2.size() - 1, Integer.valueOf(intValue));
            } else {
                arrayList.add(Integer.valueOf(intValue));
                arrayList2.add(Integer.valueOf(intValue));
                arrayList3.add(value);
                i2 = intValue;
            }
            str = value;
            i = intValue;
        }
        int ceil = (int) Math.ceil(arrayList.size() / 100.0d);
        int i3 = 0;
        while (i3 < ceil) {
            int size = i3 == ceil + (-1) ? arrayList.size() % 100 : 100;
            bufferedWriter.write(size + " beginbfrange\n");
            for (int i4 = 0; i4 < size; i4++) {
                int i5 = (i3 * 100) + i4;
                bufferedWriter.write(60);
                bufferedWriter.write(toHex(((Integer) arrayList.get(i5)).intValue()));
                bufferedWriter.write("> ");
                bufferedWriter.write(60);
                bufferedWriter.write(toHex(((Integer) arrayList2.get(i5)).intValue()));
                bufferedWriter.write("> ");
                bufferedWriter.write("<");
                bufferedWriter.write(stringToHex((String) arrayList3.get(i5)));
                bufferedWriter.write(">\n");
            }
            writeLine(bufferedWriter, "endbfrange\n");
            i3++;
        }
        writeLine(bufferedWriter, "endcmap");
        writeLine(bufferedWriter, "CMapName currentdict /CMap defineresource pop");
        writeLine(bufferedWriter, TtmlNode.END);
        writeLine(bufferedWriter, TtmlNode.END);
        bufferedWriter.flush();
    }

    private void writeLine(BufferedWriter bufferedWriter, String str) throws IOException {
        bufferedWriter.write(str);
        bufferedWriter.write(10);
    }

    private String toHex(int i) {
        return String.format("%04X", Integer.valueOf(i));
    }

    private String stringToHex(String str) {
        StringBuilder sb = new StringBuilder();
        for (byte b : str.getBytes(Charsets.UTF_16BE)) {
            sb.append(String.format("%02X", Byte.valueOf(b)));
        }
        return sb.toString();
    }
}
