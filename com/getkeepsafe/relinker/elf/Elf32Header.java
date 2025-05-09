package com.getkeepsafe.relinker.elf;

import com.getkeepsafe.relinker.elf.Elf;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes3.dex */
public class Elf32Header extends Elf.Header {
    private final ElfParser parser;

    public Elf32Header(final boolean bigEndian, final ElfParser parser) throws IOException {
        this.bigEndian = bigEndian;
        this.parser = parser;
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.type = parser.readHalf(allocate, 16L);
        this.phoff = parser.readWord(allocate, 28L);
        this.shoff = parser.readWord(allocate, 32L);
        this.phentsize = parser.readHalf(allocate, 42L);
        this.phnum = parser.readHalf(allocate, 44L);
        this.shentsize = parser.readHalf(allocate, 46L);
        this.shnum = parser.readHalf(allocate, 48L);
        this.shstrndx = parser.readHalf(allocate, 50L);
    }

    @Override // com.getkeepsafe.relinker.elf.Elf.Header
    public Elf.SectionHeader getSectionHeader(final int index) throws IOException {
        return new Section32Header(this.parser, this, index);
    }

    @Override // com.getkeepsafe.relinker.elf.Elf.Header
    public Elf.ProgramHeader getProgramHeader(final long index) throws IOException {
        return new Program32Header(this.parser, this, index);
    }

    @Override // com.getkeepsafe.relinker.elf.Elf.Header
    public Elf.DynamicStructure getDynamicStructure(final long baseOffset, final int index) throws IOException {
        return new Dynamic32Structure(this.parser, this, baseOffset, index);
    }
}
