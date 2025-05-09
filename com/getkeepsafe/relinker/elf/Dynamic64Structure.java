package com.getkeepsafe.relinker.elf;

import com.getkeepsafe.relinker.elf.Elf;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes3.dex */
public class Dynamic64Structure extends Elf.DynamicStructure {
    public Dynamic64Structure(final ElfParser parser, final Elf.Header header, long baseOffset, final int index) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(header.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j = baseOffset + (index * 16);
        this.tag = parser.readLong(allocate, j);
        this.val = parser.readLong(allocate, j + 8);
    }
}
