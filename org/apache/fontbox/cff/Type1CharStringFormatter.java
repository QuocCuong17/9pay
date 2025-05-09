package org.apache.fontbox.cff;

import java.io.ByteArrayOutputStream;
import java.util.List;

/* loaded from: classes5.dex */
public class Type1CharStringFormatter {
    private ByteArrayOutputStream output = null;

    public byte[] format(List<Object> list) {
        this.output = new ByteArrayOutputStream();
        for (Object obj : list) {
            if (obj instanceof CharStringCommand) {
                writeCommand((CharStringCommand) obj);
            } else if (obj instanceof Integer) {
                writeNumber((Integer) obj);
            } else {
                throw new IllegalArgumentException();
            }
        }
        return this.output.toByteArray();
    }

    private void writeCommand(CharStringCommand charStringCommand) {
        for (int i : charStringCommand.getKey().getValue()) {
            this.output.write(i);
        }
    }

    private void writeNumber(Integer num) {
        int intValue = num.intValue();
        if (intValue >= -107 && intValue <= 107) {
            this.output.write(intValue + 139);
            return;
        }
        if (intValue >= 108 && intValue <= 1131) {
            int i = intValue - 108;
            int i2 = i % 256;
            this.output.write(((i - i2) / 256) + 247);
            this.output.write(i2);
            return;
        }
        if (intValue >= -1131 && intValue <= -108) {
            this.output.write(-(((r6 + r0) / 256) - 251));
            this.output.write(-((intValue + 108) % 256));
            return;
        }
        this.output.write(255);
        this.output.write((intValue >>> 24) & 255);
        this.output.write((intValue >>> 16) & 255);
        this.output.write((intValue >>> 8) & 255);
        this.output.write((intValue >>> 0) & 255);
    }
}
