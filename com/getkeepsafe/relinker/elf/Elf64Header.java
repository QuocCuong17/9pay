package com.getkeepsafe.relinker.elf;

import com.getkeepsafe.relinker.elf.Elf;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes3.dex */
public class Elf64Header extends Elf.Header {
    private final ElfParser parser;

    public Elf64Header(final boolean bigEndian, final ElfParser parser) throws IOException {
        this.bigEndian = bigEndian;
        this.parser = parser;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.type = parser.readHalf(allocate, 16L);
        this.phoff = parser.readLong(allocate, 32L);
        this.shoff = parser.readLong(allocate, 40L);
        this.phentsize = parser.readHalf(allocate, 54L);
        this.phnum = parser.readHalf(allocate, 56L);
        this.shentsize = parser.readHalf(allocate, 58L);
        this.shnum = parser.readHalf(allocate, 60L);
        this.shstrndx = parser.readHalf(allocate, 62L);
    }

    @Override // com.getkeepsafe.relinker.elf.Elf.Header
    public Elf.SectionHeader getSectionHeader(final int index) throws IOException {
        return new Section64Header(this.parser, this, index);
    }

    @Override // com.getkeepsafe.relinker.elf.Elf.Header
    public Elf.ProgramHeader getProgramHeader(final long index) throws IOException {
        return new Program64Header(this.parser, this, index);
    }

    @Override // com.getkeepsafe.relinker.elf.Elf.Header
    public Elf.DynamicStructure getDynamicStructure(final long baseOffset, final int index) throws IOException {
        return new Dynamic64Structure(this.parser, this, baseOffset, index);
    }
}
