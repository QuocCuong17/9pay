package com.getkeepsafe.relinker.elf;

import com.getkeepsafe.relinker.elf.Elf;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes3.dex */
public class Program64Header extends Elf.ProgramHeader {
    public Program64Header(final ElfParser parser, final Elf.Header header, final long index) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(header.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j = header.phoff + (index * header.phentsize);
        this.type = parser.readWord(allocate, j);
        this.offset = parser.readLong(allocate, 8 + j);
        this.vaddr = parser.readLong(allocate, 16 + j);
        this.memsz = parser.readLong(allocate, j + 40);
    }
}
