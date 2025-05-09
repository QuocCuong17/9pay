package com.getkeepsafe.relinker.elf;

import com.getkeepsafe.relinker.elf.Elf;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes3.dex */
public class Program32Header extends Elf.ProgramHeader {
    public Program32Header(final ElfParser parser, final Elf.Header header, final long index) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(header.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j = header.phoff + (index * header.phentsize);
        this.type = parser.readWord(allocate, j);
        this.offset = parser.readWord(allocate, 4 + j);
        this.vaddr = parser.readWord(allocate, 8 + j);
        this.memsz = parser.readWord(allocate, j + 20);
    }
}
