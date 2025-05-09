package com.getkeepsafe.relinker.elf;

import com.getkeepsafe.relinker.elf.Elf;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.UShort;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: classes3.dex */
public class ElfParser implements Closeable, Elf {
    private final int MAGIC = 1179403647;
    private final FileChannel channel;

    public ElfParser(final File file) throws FileNotFoundException {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is null or does not exist");
        }
        this.channel = new FileInputStream(file).getChannel();
    }

    public Elf.Header parseHeader() throws IOException {
        this.channel.position(0L);
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (readWord(allocate, 0L) != 1179403647) {
            throw new IllegalArgumentException("Invalid ELF Magic!");
        }
        short readByte = readByte(allocate, 4L);
        boolean z = readByte(allocate, 5L) == 2;
        if (readByte == 1) {
            return new Elf32Header(z, this);
        }
        if (readByte == 2) {
            return new Elf64Header(z, this);
        }
        throw new IllegalStateException("Invalid class type!");
    }

    public List<String> parseNeededDependencies() throws IOException {
        long j;
        Elf.DynamicStructure dynamicStructure;
        this.channel.position(0L);
        ArrayList arrayList = new ArrayList();
        Elf.Header parseHeader = parseHeader();
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(parseHeader.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = parseHeader.phnum;
        int i = 0;
        if (j2 == WebSocketProtocol.PAYLOAD_SHORT_MAX) {
            j2 = parseHeader.getSectionHeader(0).info;
        }
        long j3 = 0;
        while (true) {
            if (j3 >= j2) {
                j = 0;
                break;
            }
            Elf.ProgramHeader programHeader = parseHeader.getProgramHeader(j3);
            if (programHeader.type == 2) {
                j = programHeader.offset;
                break;
            }
            j3++;
        }
        if (j == 0) {
            return Collections.unmodifiableList(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        long j4 = 0;
        do {
            dynamicStructure = parseHeader.getDynamicStructure(j, i);
            if (dynamicStructure.tag == 1) {
                arrayList2.add(Long.valueOf(dynamicStructure.val));
            } else if (dynamicStructure.tag == 5) {
                j4 = dynamicStructure.val;
            }
            i++;
        } while (dynamicStructure.tag != 0);
        if (j4 == 0) {
            throw new IllegalStateException("String table offset not found!");
        }
        long offsetFromVma = offsetFromVma(parseHeader, j2, j4);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(readString(allocate, ((Long) it.next()).longValue() + offsetFromVma));
        }
        return arrayList;
    }

    private long offsetFromVma(final Elf.Header header, final long numEntries, final long vma) throws IOException {
        for (long j = 0; j < numEntries; j++) {
            Elf.ProgramHeader programHeader = header.getProgramHeader(j);
            if (programHeader.type == 1 && programHeader.vaddr <= vma && vma <= programHeader.vaddr + programHeader.memsz) {
                return (vma - programHeader.vaddr) + programHeader.offset;
            }
        }
        throw new IllegalStateException("Could not map vma to file offset!");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.channel.close();
    }

    protected String readString(final ByteBuffer buffer, long offset) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j = 1 + offset;
            short readByte = readByte(buffer, offset);
            if (readByte != 0) {
                sb.append((char) readByte);
                offset = j;
            } else {
                return sb.toString();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long readLong(final ByteBuffer buffer, final long offset) throws IOException {
        read(buffer, offset, 8);
        return buffer.getLong();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long readWord(final ByteBuffer buffer, final long offset) throws IOException {
        read(buffer, offset, 4);
        return buffer.getInt() & 4294967295L;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int readHalf(final ByteBuffer buffer, final long offset) throws IOException {
        read(buffer, offset, 2);
        return buffer.getShort() & UShort.MAX_VALUE;
    }

    protected short readByte(final ByteBuffer buffer, final long offset) throws IOException {
        read(buffer, offset, 1);
        return (short) (buffer.get() & 255);
    }

    protected void read(final ByteBuffer buffer, long offset, final int length) throws IOException {
        buffer.position(0);
        buffer.limit(length);
        long j = 0;
        while (j < length) {
            int read = this.channel.read(buffer, offset + j);
            if (read == -1) {
                throw new EOFException();
            }
            j += read;
        }
        buffer.position(0);
    }
}
