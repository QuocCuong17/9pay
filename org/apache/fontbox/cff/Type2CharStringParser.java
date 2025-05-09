package org.apache.fontbox.cff;

import androidx.media3.common.PlaybackException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class Type2CharStringParser {
    private final String fontName;
    private final String glyphName;
    private int hstemCount = 0;
    private int vstemCount = 0;
    private List<Object> sequence = null;

    public Type2CharStringParser(String str, String str2) {
        this.fontName = str;
        this.glyphName = str2;
    }

    public Type2CharStringParser(String str, int i) {
        this.fontName = str;
        this.glyphName = String.format("%04x", Integer.valueOf(i));
    }

    public List<Object> parse(byte[] bArr, IndexData indexData, IndexData indexData2) throws IOException {
        return parse(bArr, indexData, indexData2, true);
    }

    private List<Object> parse(byte[] bArr, IndexData indexData, IndexData indexData2, boolean z) throws IOException {
        if (z) {
            this.hstemCount = 0;
            this.vstemCount = 0;
            this.sequence = new ArrayList();
        }
        DataInput dataInput = new DataInput(bArr);
        boolean z2 = indexData2 != null && indexData2.getCount() > 0;
        boolean z3 = indexData != null && indexData.getCount() > 0;
        while (dataInput.hasRemaining()) {
            int readUnsignedByte = dataInput.readUnsignedByte();
            int i = 1131;
            if (readUnsignedByte == 10 && z2) {
                List<Object> list = this.sequence;
                Integer num = (Integer) list.remove(list.size() - 1);
                int count = indexData2.getCount();
                if (count < 1240) {
                    i = 107;
                } else if (count >= 33900) {
                    i = 32768;
                }
                int intValue = i + num.intValue();
                if (intValue < indexData2.getCount()) {
                    parse(indexData2.getBytes(intValue), indexData, indexData2, false);
                    List<Object> list2 = this.sequence;
                    Object obj = list2.get(list2.size() - 1);
                    if ((obj instanceof CharStringCommand) && ((CharStringCommand) obj).getKey().getValue()[0] == 11) {
                        List<Object> list3 = this.sequence;
                        list3.remove(list3.size() - 1);
                    }
                }
            } else if (readUnsignedByte == 29 && z3) {
                List<Object> list4 = this.sequence;
                Integer num2 = (Integer) list4.remove(list4.size() - 1);
                int count2 = indexData.getCount();
                if (count2 < 1240) {
                    i = 107;
                } else if (count2 >= 33900) {
                    i = 32768;
                }
                int intValue2 = i + num2.intValue();
                if (intValue2 < indexData.getCount()) {
                    parse(indexData.getBytes(intValue2), indexData, indexData2, false);
                    List<Object> list5 = this.sequence;
                    Object obj2 = list5.get(list5.size() - 1);
                    if ((obj2 instanceof CharStringCommand) && ((CharStringCommand) obj2).getKey().getValue()[0] == 11) {
                        List<Object> list6 = this.sequence;
                        list6.remove(list6.size() - 1);
                    }
                }
            } else if (readUnsignedByte >= 0 && readUnsignedByte <= 27) {
                this.sequence.add(readCommand(readUnsignedByte, dataInput));
            } else if (readUnsignedByte == 28) {
                this.sequence.add(readNumber(readUnsignedByte, dataInput));
            } else if (readUnsignedByte >= 29 && readUnsignedByte <= 31) {
                this.sequence.add(readCommand(readUnsignedByte, dataInput));
            } else if (readUnsignedByte >= 32 && readUnsignedByte <= 255) {
                this.sequence.add(readNumber(readUnsignedByte, dataInput));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return this.sequence;
    }

    private CharStringCommand readCommand(int i, DataInput dataInput) throws IOException {
        if (i == 1 || i == 18) {
            this.hstemCount += peekNumbers().size() / 2;
        } else if (i == 3 || i == 19 || i == 20 || i == 23) {
            this.vstemCount += peekNumbers().size() / 2;
        }
        if (i == 12) {
            return new CharStringCommand(i, dataInput.readUnsignedByte());
        }
        if (i == 19 || i == 20) {
            int maskLength = getMaskLength() + 1;
            int[] iArr = new int[maskLength];
            iArr[0] = i;
            for (int i2 = 1; i2 < maskLength; i2++) {
                iArr[i2] = dataInput.readUnsignedByte();
            }
            return new CharStringCommand(iArr);
        }
        return new CharStringCommand(i);
    }

    private static Integer readNumber(int i, DataInput dataInput) throws IOException {
        if (i == 28) {
            return Integer.valueOf((short) ((dataInput.readUnsignedByte() << 8) | dataInput.readUnsignedByte()));
        }
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
            int readUnsignedByte = dataInput.readUnsignedByte();
            int readUnsignedByte2 = dataInput.readUnsignedByte();
            dataInput.readUnsignedByte();
            dataInput.readUnsignedByte();
            return Integer.valueOf((short) ((readUnsignedByte << 8) | readUnsignedByte2));
        }
        throw new IllegalArgumentException();
    }

    private int getMaskLength() {
        int i = this.hstemCount + this.vstemCount;
        int i2 = 1;
        while (true) {
            i -= 8;
            if (i <= 0) {
                return i2;
            }
            i2++;
        }
    }

    private List<Number> peekNumbers() {
        ArrayList arrayList = new ArrayList();
        int size = this.sequence.size();
        while (true) {
            size--;
            if (size <= -1) {
                break;
            }
            Object obj = this.sequence.get(size);
            if (!(obj instanceof Number)) {
                break;
            }
            arrayList.add(0, (Number) obj);
        }
        return arrayList;
    }
}
