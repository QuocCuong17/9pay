package com.getkeepsafe.relinker.elf;

import com.getkeepsafe.relinker.elf.Elf;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes3.dex */
public class Section32Header extends Elf.SectionHeader {
    public Section32Header(final ElfParser parser, final Elf.Header header, final int index) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(header.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.info = parser.readWord(allocate, header.shoff + (index * header.shentsize) + 28);
    }
}
