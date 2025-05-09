package com.getkeepsafe.relinker.elf;

import com.getkeepsafe.relinker.elf.Elf;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes3.dex */
public class Dynamic32Structure extends Elf.DynamicStructure {
    public Dynamic32Structure(final ElfParser parser, final Elf.Header header, long baseOffset, final int index) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(header.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j = baseOffset + (index * 8);
        this.tag = parser.readWord(allocate, j);
        this.val = parser.readWord(allocate, j + 4);
    }
}
