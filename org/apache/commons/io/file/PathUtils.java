package org.apache.commons.io.file;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.file.Counters;

/* loaded from: classes5.dex */
public final class PathUtils {
    public static final DeleteOption[] EMPTY_DELETE_OPTION_ARRAY = new DeleteOption[0];
    public static final FileVisitOption[] EMPTY_FILE_VISIT_OPTION_ARRAY = new FileVisitOption[0];
    public static final LinkOption[] EMPTY_LINK_OPTION_ARRAY = new LinkOption[0];
    public static final OpenOption[] EMPTY_OPEN_OPTION_ARRAY = new OpenOption[0];

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class RelativeSortedPaths {
        final boolean equals;
        final List<Path> relativeFileList1;
        final List<Path> relativeFileList2;

        private RelativeSortedPaths(Path path, Path path2, int i, LinkOption[] linkOptionArr, FileVisitOption[] fileVisitOptionArr) throws IOException {
            List<Path> list;
            List<Path> list2 = null;
            if (path == null && path2 == null) {
                this.equals = true;
            } else {
                if ((path == null) ^ (path2 == null)) {
                    this.equals = false;
                } else {
                    boolean exists = Files.exists(path, linkOptionArr);
                    boolean exists2 = Files.exists(path2, linkOptionArr);
                    if (exists && exists2) {
                        AccumulatorPathVisitor accumulate = PathUtils.accumulate(path, i, fileVisitOptionArr);
                        AccumulatorPathVisitor accumulate2 = PathUtils.accumulate(path2, i, fileVisitOptionArr);
                        if (accumulate.getDirList().size() != accumulate2.getDirList().size() || accumulate.getFileList().size() != accumulate2.getFileList().size()) {
                            this.equals = false;
                        } else if (!accumulate.relativizeDirectories(path, true, null).equals(accumulate2.relativizeDirectories(path2, true, null))) {
                            this.equals = false;
                        } else {
                            List<Path> relativizeFiles = accumulate.relativizeFiles(path, true, null);
                            List<Path> relativizeFiles2 = accumulate2.relativizeFiles(path2, true, null);
                            this.equals = relativizeFiles.equals(relativizeFiles2);
                            list2 = relativizeFiles;
                            list = relativizeFiles2;
                            this.relativeFileList1 = list2;
                            this.relativeFileList2 = list;
                        }
                    } else {
                        this.equals = (exists || exists2) ? false : true;
                    }
                }
            }
            list = null;
            this.relativeFileList1 = list2;
            this.relativeFileList2 = list;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AccumulatorPathVisitor accumulate(Path path, int i, FileVisitOption[] fileVisitOptionArr) throws IOException {
        return (AccumulatorPathVisitor) visitFileTree(AccumulatorPathVisitor.withLongCounters(), path, toFileVisitOptionSet(fileVisitOptionArr), i);
    }

    public static Counters.PathCounters cleanDirectory(Path path) throws IOException {
        return cleanDirectory(path, EMPTY_DELETE_OPTION_ARRAY);
    }

    public static Counters.PathCounters cleanDirectory(Path path, DeleteOption... deleteOptionArr) throws IOException {
        return ((CleaningPathVisitor) visitFileTree(new CleaningPathVisitor(Counters.longPathCounters(), deleteOptionArr, new String[0]), path)).getPathCounters();
    }

    public static Counters.PathCounters copyDirectory(Path path, Path path2, CopyOption... copyOptionArr) throws IOException {
        return ((CopyDirectoryVisitor) visitFileTree(new CopyDirectoryVisitor(Counters.longPathCounters(), path, path2, copyOptionArr), path)).getPathCounters();
    }

    public static Path copyFile(URL url, Path path, CopyOption... copyOptionArr) throws IOException {
        InputStream openStream = url.openStream();
        try {
            Files.copy(openStream, path, copyOptionArr);
            if (openStream != null) {
                openStream.close();
            }
            return path;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (openStream != null) {
                    try {
                        openStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static Path copyFileToDirectory(Path path, Path path2, CopyOption... copyOptionArr) throws IOException {
        return Files.copy(path, path2.resolve(path.getFileName()), copyOptionArr);
    }

    public static Path copyFileToDirectory(URL url, Path path, CopyOption... copyOptionArr) throws IOException {
        InputStream openStream = url.openStream();
        try {
            Files.copy(openStream, path.resolve(url.getFile()), copyOptionArr);
            if (openStream != null) {
                openStream.close();
            }
            return path;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (openStream != null) {
                    try {
                        openStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static Counters.PathCounters countDirectory(Path path) throws IOException {
        return ((CountingPathVisitor) visitFileTree(new CountingPathVisitor(Counters.longPathCounters()), path)).getPathCounters();
    }

    public static Counters.PathCounters delete(Path path) throws IOException {
        return delete(path, EMPTY_DELETE_OPTION_ARRAY);
    }

    public static Counters.PathCounters delete(Path path, DeleteOption... deleteOptionArr) throws IOException {
        return Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS) ? deleteDirectory(path, deleteOptionArr) : deleteFile(path, deleteOptionArr);
    }

    public static Counters.PathCounters deleteDirectory(Path path) throws IOException {
        return deleteDirectory(path, EMPTY_DELETE_OPTION_ARRAY);
    }

    public static Counters.PathCounters deleteDirectory(Path path, DeleteOption... deleteOptionArr) throws IOException {
        return ((DeletingPathVisitor) visitFileTree(new DeletingPathVisitor(Counters.longPathCounters(), deleteOptionArr, new String[0]), path)).getPathCounters();
    }

    public static Counters.PathCounters deleteFile(Path path) throws IOException {
        return deleteFile(path, EMPTY_DELETE_OPTION_ARRAY);
    }

    public static Counters.PathCounters deleteFile(Path path, DeleteOption... deleteOptionArr) throws IOException {
        if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchFileException(path.toString());
        }
        Counters.PathCounters longPathCounters = Counters.longPathCounters();
        boolean exists = Files.exists(path, LinkOption.NOFOLLOW_LINKS);
        long size = exists ? Files.size(path) : 0L;
        if (overrideReadOnly(deleteOptionArr) && exists) {
            setReadOnly(path, false, LinkOption.NOFOLLOW_LINKS);
        }
        if (Files.deleteIfExists(path)) {
            longPathCounters.getFileCounter().increment();
            longPathCounters.getByteCounter().add(size);
        }
        return longPathCounters;
    }

    private static boolean overrideReadOnly(DeleteOption[] deleteOptionArr) {
        if (deleteOptionArr == null) {
            return false;
        }
        for (DeleteOption deleteOption : deleteOptionArr) {
            if (deleteOption == StandardDeleteOption.OVERRIDE_READ_ONLY) {
                return true;
            }
        }
        return false;
    }

    public static boolean directoryAndFileContentEquals(Path path, Path path2) throws IOException {
        return directoryAndFileContentEquals(path, path2, EMPTY_LINK_OPTION_ARRAY, EMPTY_OPEN_OPTION_ARRAY, EMPTY_FILE_VISIT_OPTION_ARRAY);
    }

    public static boolean directoryAndFileContentEquals(Path path, Path path2, LinkOption[] linkOptionArr, OpenOption[] openOptionArr, FileVisitOption[] fileVisitOptionArr) throws IOException {
        if (path == null && path2 == null) {
            return true;
        }
        if ((path == null) ^ (path2 == null)) {
            return false;
        }
        if (!Files.exists(path, new LinkOption[0]) && !Files.exists(path2, new LinkOption[0])) {
            return true;
        }
        RelativeSortedPaths relativeSortedPaths = new RelativeSortedPaths(path, path2, Integer.MAX_VALUE, linkOptionArr, fileVisitOptionArr);
        if (!relativeSortedPaths.equals) {
            return false;
        }
        List<Path> list = relativeSortedPaths.relativeFileList1;
        List<Path> list2 = relativeSortedPaths.relativeFileList2;
        for (Path path3 : list) {
            if (Collections.binarySearch(list2, path3) > -1) {
                if (!fileContentEquals(path.resolve(path3), path2.resolve(path3), linkOptionArr, openOptionArr)) {
                    return false;
                }
            } else {
                throw new IllegalStateException("Unexpected mismatch.");
            }
        }
        return true;
    }

    public static boolean directoryContentEquals(Path path, Path path2) throws IOException {
        return directoryContentEquals(path, path2, Integer.MAX_VALUE, EMPTY_LINK_OPTION_ARRAY, EMPTY_FILE_VISIT_OPTION_ARRAY);
    }

    public static boolean directoryContentEquals(Path path, Path path2, int i, LinkOption[] linkOptionArr, FileVisitOption[] fileVisitOptionArr) throws IOException {
        return new RelativeSortedPaths(path, path2, i, linkOptionArr, fileVisitOptionArr).equals;
    }

    public static boolean fileContentEquals(Path path, Path path2) throws IOException {
        return fileContentEquals(path, path2, EMPTY_LINK_OPTION_ARRAY, EMPTY_OPEN_OPTION_ARRAY);
    }

    public static boolean fileContentEquals(Path path, Path path2, LinkOption[] linkOptionArr, OpenOption[] openOptionArr) throws IOException {
        if (path == null && path2 == null) {
            return true;
        }
        if ((path == null) ^ (path2 == null)) {
            return false;
        }
        Path normalize = path.normalize();
        Path normalize2 = path2.normalize();
        boolean exists = Files.exists(normalize, linkOptionArr);
        if (exists != Files.exists(normalize2, linkOptionArr)) {
            return false;
        }
        if (!exists) {
            return true;
        }
        if (Files.isDirectory(normalize, linkOptionArr)) {
            throw new IOException("Can't compare directories, only files: " + normalize);
        }
        if (Files.isDirectory(normalize2, linkOptionArr)) {
            throw new IOException("Can't compare directories, only files: " + normalize2);
        }
        if (Files.size(normalize) != Files.size(normalize2)) {
            return false;
        }
        if (path.equals(path2)) {
            return true;
        }
        InputStream newInputStream = Files.newInputStream(normalize, openOptionArr);
        try {
            InputStream newInputStream2 = Files.newInputStream(normalize2, openOptionArr);
            try {
                boolean contentEquals = IOUtils.contentEquals(newInputStream, newInputStream2);
                if (newInputStream2 != null) {
                    newInputStream2.close();
                }
                if (newInputStream != null) {
                    newInputStream.close();
                }
                return contentEquals;
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (newInputStream != null) {
                    try {
                        newInputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static List<AclEntry> getAclEntryList(Path path) throws IOException {
        AclFileAttributeView aclFileAttributeView = (AclFileAttributeView) Files.getFileAttributeView(path, AclFileAttributeView.class, new LinkOption[0]);
        if (aclFileAttributeView == null) {
            return null;
        }
        return aclFileAttributeView.getAcl();
    }

    public static boolean isEmpty(Path path) throws IOException {
        return Files.isDirectory(path, new LinkOption[0]) ? isEmptyDirectory(path) : isEmptyFile(path);
    }

    public static boolean isEmptyDirectory(Path path) throws IOException {
        DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(path);
        try {
            if (newDirectoryStream.iterator().hasNext()) {
                if (newDirectoryStream != null) {
                    newDirectoryStream.close();
                }
                return false;
            }
            if (newDirectoryStream == null) {
                return true;
            }
            newDirectoryStream.close();
            return true;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (newDirectoryStream != null) {
                    try {
                        newDirectoryStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static boolean isEmptyFile(Path path) throws IOException {
        return Files.size(path) <= 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<Path> relativize(Collection<Path> collection, final Path path, boolean z, Comparator<? super Path> comparator) {
        Stream<Path> stream = collection.stream();
        path.getClass();
        Stream map = stream.map(new Function() { // from class: org.apache.commons.io.file.PathUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return path.relativize((Path) obj);
            }
        });
        if (z) {
            map = comparator == null ? map.sorted() : map.sorted(comparator);
        }
        return (List) map.collect(Collectors.toList());
    }

    public static Path setReadOnly(Path path, boolean z, LinkOption... linkOptionArr) throws IOException {
        DosFileAttributeView dosFileAttributeView = (DosFileAttributeView) Files.getFileAttributeView(path, DosFileAttributeView.class, linkOptionArr);
        if (dosFileAttributeView != null) {
            dosFileAttributeView.setReadOnly(z);
            return path;
        }
        PosixFileAttributeView posixFileAttributeView = (PosixFileAttributeView) Files.getFileAttributeView(path, PosixFileAttributeView.class, linkOptionArr);
        if (posixFileAttributeView != null) {
            Set<PosixFilePermission> permissions = posixFileAttributeView.readAttributes().permissions();
            permissions.remove(PosixFilePermission.OWNER_WRITE);
            permissions.remove(PosixFilePermission.GROUP_WRITE);
            permissions.remove(PosixFilePermission.OTHERS_WRITE);
            return Files.setPosixFilePermissions(path, permissions);
        }
        throw new IOException("No DosFileAttributeView or PosixFileAttributeView for " + path);
    }

    static Set<FileVisitOption> toFileVisitOptionSet(FileVisitOption... fileVisitOptionArr) {
        if (fileVisitOptionArr == null) {
            return EnumSet.noneOf(FileVisitOption.class);
        }
        return (Set) Arrays.stream(fileVisitOptionArr).collect(Collectors.toSet());
    }

    public static <T extends FileVisitor<? super Path>> T visitFileTree(T t, Path path) throws IOException {
        Files.walkFileTree(path, t);
        return t;
    }

    public static <T extends FileVisitor<? super Path>> T visitFileTree(T t, Path path, Set<FileVisitOption> set, int i) throws IOException {
        Files.walkFileTree(path, set, i, t);
        return t;
    }

    public static <T extends FileVisitor<? super Path>> T visitFileTree(T t, String str, String... strArr) throws IOException {
        return (T) visitFileTree(t, Paths.get(str, strArr));
    }

    public static <T extends FileVisitor<? super Path>> T visitFileTree(T t, URI uri) throws IOException {
        return (T) visitFileTree(t, Paths.get(uri));
    }

    private PathUtils() {
    }
}
