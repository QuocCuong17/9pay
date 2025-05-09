package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.io.file.Counters;

/* loaded from: classes5.dex */
public class CleaningPathVisitor extends CountingPathVisitor {
    private final boolean overrideReadOnly;
    private final String[] skip;

    public static CountingPathVisitor withBigIntegerCounters() {
        return new CleaningPathVisitor(Counters.bigIntegerPathCounters(), new String[0]);
    }

    public static CountingPathVisitor withLongCounters() {
        return new CleaningPathVisitor(Counters.longPathCounters(), new String[0]);
    }

    public CleaningPathVisitor(Counters.PathCounters pathCounters, DeleteOption[] deleteOptionArr, String... strArr) {
        super(pathCounters);
        String[] strArr2 = strArr != null ? (String[]) strArr.clone() : EMPTY_STRING_ARRAY;
        Arrays.sort(strArr2);
        this.skip = strArr2;
        this.overrideReadOnly = StandardDeleteOption.overrideReadOnly(deleteOptionArr);
    }

    public CleaningPathVisitor(Counters.PathCounters pathCounters, String... strArr) {
        this(pathCounters, PathUtils.EMPTY_DELETE_OPTION_ARRAY, strArr);
    }

    private boolean accept(Path path) {
        return Arrays.binarySearch(this.skip, Objects.toString(path.getFileName(), null)) < 0;
    }

    @Override // org.apache.commons.io.file.CountingPathVisitor
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        CleaningPathVisitor cleaningPathVisitor = (CleaningPathVisitor) obj;
        return this.overrideReadOnly == cleaningPathVisitor.overrideReadOnly && Arrays.equals(this.skip, cleaningPathVisitor.skip);
    }

    @Override // org.apache.commons.io.file.CountingPathVisitor
    public int hashCode() {
        return (((super.hashCode() * 31) + Arrays.hashCode(this.skip)) * 31) + Objects.hash(Boolean.valueOf(this.overrideReadOnly));
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        super.preVisitDirectory((Object) path, basicFileAttributes);
        return accept(path) ? FileVisitResult.CONTINUE : FileVisitResult.SKIP_SUBTREE;
    }

    @Override // org.apache.commons.io.file.CountingPathVisitor, java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        if (accept(path) && Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            if (this.overrideReadOnly) {
                PathUtils.setReadOnly(path, false, LinkOption.NOFOLLOW_LINKS);
            }
            Files.deleteIfExists(path);
        }
        updateFileCounters(path, basicFileAttributes);
        return FileVisitResult.CONTINUE;
    }
}
