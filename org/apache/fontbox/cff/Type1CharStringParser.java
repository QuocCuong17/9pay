package org.apache.fontbox.cff;

import android.util.Log;
import androidx.media3.common.PlaybackException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* loaded from: classes5.dex */
public class Type1CharStringParser {
    static final int CALLOTHERSUBR = 16;
    static final int CALLSUBR = 10;
    static final int POP = 17;
    static final int RETURN = 11;
    static final int TWO_BYTE = 12;
    private final String fontName;
    private final String glyphName;

    public Type1CharStringParser(String str, String str2) {
        this.fontName = str;
        this.glyphName = str2;
    }

    public List<Object> parse(byte[] bArr, List<byte[]> list) throws IOException {
        return parse(bArr, list, new ArrayList());
    }

    private List<Object> parse(byte[] bArr, List<byte[]> list, List<Object> list2) throws IOException {
        DataInput dataInput = new DataInput(bArr);
        while (dataInput.hasRemaining()) {
            int readUnsignedByte = dataInput.readUnsignedByte();
            if (readUnsignedByte == 10) {
                Integer num = (Integer) list2.remove(list2.size() - 1);
                if (num.intValue() < list.size()) {
                    parse(list.get(num.intValue()), list, list2);
                    Object obj = list2.get(list2.size() - 1);
                    if ((obj instanceof CharStringCommand) && ((CharStringCommand) obj).getKey().getValue()[0] == 11) {
                        list2.remove(list2.size() - 1);
                    }
                }
            } else if (readUnsignedByte == 12 && dataInput.peekUnsignedByte(0) == 16) {
                dataInput.readByte();
                Integer num2 = (Integer) list2.remove(list2.size() - 1);
                Integer num3 = (Integer) list2.remove(list2.size() - 1);
                Stack stack = new Stack();
                if (num2.intValue() == 0) {
                    stack.push(removeInteger(list2));
                    stack.push(removeInteger(list2));
                    list2.remove(list2.size() - 1);
                    list2.add(0);
                    list2.add(new CharStringCommand(12, 16));
                } else if (num2.intValue() == 1) {
                    list2.add(1);
                    list2.add(new CharStringCommand(12, 16));
                } else if (num2.intValue() == 3) {
                    stack.push(removeInteger(list2));
                } else {
                    for (int i = 0; i < num3.intValue(); i++) {
                        stack.push(removeInteger(list2));
                    }
                }
                while (dataInput.peekUnsignedByte(0) == 12 && dataInput.peekUnsignedByte(1) == 17) {
                    dataInput.readByte();
                    dataInput.readByte();
                    list2.add((Integer) stack.pop());
                }
                if (stack.size() > 0) {
                    Log.w("PdfBoxAndroid", "Value left on the PostScript stack in glyph " + this.glyphName + " of font " + this.fontName);
                }
            } else if (readUnsignedByte >= 0 && readUnsignedByte <= 31) {
                list2.add(readCommand(dataInput, readUnsignedByte));
            } else if (readUnsignedByte >= 32 && readUnsignedByte <= 255) {
                list2.add(readNumber(dataInput, readUnsignedByte));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return list2;
    }

    private Integer removeInteger(List<Object> list) throws IOException {
        Object remove = list.remove(list.size() - 1);
        if (remove instanceof Integer) {
            return (Integer) remove;
        }
        CharStringCommand charStringCommand = (CharStringCommand) remove;
        if (charStringCommand.getKey().getValue()[0] == 12 && charStringCommand.getKey().getValue()[0] == 12) {
            return Integer.valueOf(((Integer) list.remove(list.size() - 1)).intValue() / ((Integer) list.remove(list.size() - 1)).intValue());
        }
        throw new IOException("Unexpected char string command: " + charStringCommand.getKey());
    }

    private CharStringCommand readCommand(DataInput dataInput, int i) throws IOException {
        if (i == 12) {
            return new CharStringCommand(i, dataInput.readUnsignedByte());
        }
        return new CharStringCommand(i);
    }

    private Integer readNumber(DataInput dataInput, int i) throws IOException {
        if (i >= 32 && i <= 246) {
            return Integer.valueOf(i - 139);
        }
        if (i >= 247 && i <= 250) {
            return Integer.valueOf(((i - 247) * 256) + dataInput.readUnsignedByte() + 108);
        }
        if (i >= 251 && i <= 254) {
            return Integer.valueOf((((-(i - 251)) * 256) - dataInput.readUnsignedByte()) + PlaybackException.ERROR_CODE_SETUP_REQUIRED);
        }
        if (i == 255) {
            return Integer.valueOf(dataInput.readUnsignedByte() | (dataInput.readUnsignedByte() << 24) | (dataInput.readUnsignedByte() << 16) | (dataInput.readUnsignedByte() << 8));
        }
        throw new IllegalArgumentException();
    }
}
