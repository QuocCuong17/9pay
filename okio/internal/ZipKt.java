package okio.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.IOException;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.BufferedSource;
import okio.FileHandle;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Okio;
import okio.Path;
import okio.ZipFileSystem;

/* compiled from: zip.kt */
@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\"\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00132\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0017H\u0002\u001a\u001f\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010\u001b\u001a.\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020 2\u0014\b\u0002\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020#0\"H\u0000\u001a\f\u0010$\u001a\u00020\u0015*\u00020%H\u0000\u001a\f\u0010&\u001a\u00020'*\u00020%H\u0002\u001a.\u0010(\u001a\u00020)*\u00020%2\u0006\u0010*\u001a\u00020\u00012\u0018\u0010+\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020)0,H\u0002\u001a\u0014\u0010-\u001a\u00020.*\u00020%2\u0006\u0010/\u001a\u00020.H\u0000\u001a\u0018\u00100\u001a\u0004\u0018\u00010.*\u00020%2\b\u0010/\u001a\u0004\u0018\u00010.H\u0002\u001a\u0014\u00101\u001a\u00020'*\u00020%2\u0006\u00102\u001a\u00020'H\u0002\u001a\f\u00103\u001a\u00020)*\u00020%H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u00064"}, d2 = {"BIT_FLAG_ENCRYPTED", "", "BIT_FLAG_UNSUPPORTED_MASK", "CENTRAL_FILE_HEADER_SIGNATURE", "COMPRESSION_METHOD_DEFLATED", "COMPRESSION_METHOD_STORED", "END_OF_CENTRAL_DIRECTORY_SIGNATURE", "HEADER_ID_EXTENDED_TIMESTAMP", "HEADER_ID_ZIP64_EXTENDED_INFO", "LOCAL_FILE_HEADER_SIGNATURE", "MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE", "", "ZIP64_EOCD_RECORD_SIGNATURE", "ZIP64_LOCATOR_SIGNATURE", "hex", "", "getHex", "(I)Ljava/lang/String;", "buildIndex", "", "Lokio/Path;", "Lokio/internal/ZipEntry;", "entries", "", "dosDateTimeToEpochMillis", WorkflowModule.Properties.Section.Component.Type.DATE, "time", "(II)Ljava/lang/Long;", "openZip", "Lokio/ZipFileSystem;", "zipPath", "fileSystem", "Lokio/FileSystem;", "predicate", "Lkotlin/Function1;", "", "readEntry", "Lokio/BufferedSource;", "readEocdRecord", "Lokio/internal/EocdRecord;", "readExtra", "", "extraSize", "block", "Lkotlin/Function2;", "readLocalHeader", "Lokio/FileMetadata;", "basicMetadata", "readOrSkipLocalHeader", "readZip64EocdRecord", "regularRecord", "skipLocalHeader", "okio"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class ZipKt {
    private static final int BIT_FLAG_ENCRYPTED = 1;
    private static final int BIT_FLAG_UNSUPPORTED_MASK = 1;
    private static final int CENTRAL_FILE_HEADER_SIGNATURE = 33639248;
    public static final int COMPRESSION_METHOD_DEFLATED = 8;
    public static final int COMPRESSION_METHOD_STORED = 0;
    private static final int END_OF_CENTRAL_DIRECTORY_SIGNATURE = 101010256;
    private static final int HEADER_ID_EXTENDED_TIMESTAMP = 21589;
    private static final int HEADER_ID_ZIP64_EXTENDED_INFO = 1;
    private static final int LOCAL_FILE_HEADER_SIGNATURE = 67324752;
    private static final long MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE = 4294967295L;
    private static final int ZIP64_EOCD_RECORD_SIGNATURE = 101075792;
    private static final int ZIP64_LOCATOR_SIGNATURE = 117853008;

    public static /* synthetic */ ZipFileSystem openZip$default(Path path, FileSystem fileSystem, Function1 function1, int i, Object obj) throws IOException {
        if ((i & 4) != 0) {
            function1 = new Function1<ZipEntry, Boolean>() { // from class: okio.internal.ZipKt$openZip$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(ZipEntry it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }
            };
        }
        return openZip(path, fileSystem, function1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x009c, code lost:
    
        r6 = readEocdRecord(r15);
        r12 = r15.readUtf8(r6.getCommentByteCount());
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a9, code lost:
    
        r15.close();
        r9 = r9 - 20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00b2, code lost:
    
        if (r9 <= r7) goto L179;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b4, code lost:
    
        r9 = okio.Okio.buffer(r5.source(r9));
        r10 = (java.lang.Throwable) null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00c1, code lost:
    
        r10 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00cb, code lost:
    
        if (r10.readIntLe() != okio.internal.ZipKt.ZIP64_LOCATOR_SIGNATURE) goto L169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00cd, code lost:
    
        r13 = r10.readIntLe();
        r14 = r10.readLongLe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00da, code lost:
    
        if (r10.readIntLe() != 1) goto L167;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00dc, code lost:
    
        if (r13 != 0) goto L167;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00de, code lost:
    
        r7 = okio.Okio.buffer(r5.source(r14));
        r8 = (java.lang.Throwable) null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00eb, code lost:
    
        r8 = r7;
        r10 = r8.readIntLe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00f5, code lost:
    
        if (r10 != okio.internal.ZipKt.ZIP64_EOCD_RECORD_SIGNATURE) goto L158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00f7, code lost:
    
        r6 = readZip64EocdRecord(r8, r6);
        r8 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00fd, code lost:
    
        kotlin.io.CloseableKt.closeFinally(r7, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0125, code lost:
    
        throw new java.io.IOException("bad zip: expected " + getHex(okio.internal.ZipKt.ZIP64_EOCD_RECORD_SIGNATURE) + " but was " + getHex(r10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0126, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0137, code lost:
    
        throw new java.io.IOException("unsupported zip: spanned");
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0138, code lost:
    
        r7 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x013a, code lost:
    
        kotlin.io.CloseableKt.closeFinally(r9, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x013e, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0147, code lost:
    
        r7 = new java.util.ArrayList();
        r5 = okio.Okio.buffer(r5.source(r6.getCentralDirectoryOffset()));
        r8 = (java.lang.Throwable) null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x015f, code lost:
    
        r8 = r5;
        r9 = r6.getEntryCount();
        r16 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x016a, code lost:
    
        if (0 >= r9) goto L192;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x016c, code lost:
    
        r16 = r16 + 1;
        r11 = readEntry(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x017e, code lost:
    
        if (r11.getOffset() >= r6.getCentralDirectoryOffset()) goto L246;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x018a, code lost:
    
        if (r22.invoke(r11).booleanValue() == false) goto L187;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x018c, code lost:
    
        r7.add(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0194, code lost:
    
        if (r16 < r9) goto L247;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x019e, code lost:
    
        throw new java.io.IOException("bad zip: local file header offset >= central directory offset");
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x019f, code lost:
    
        r2 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01a1, code lost:
    
        kotlin.io.CloseableKt.closeFinally(r5, null);
        r5 = new okio.ZipFileSystem(r20, r21, buildIndex(r7), r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01ad, code lost:
    
        kotlin.io.CloseableKt.closeFinally(r3, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01b0, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01b1, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01b3, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01b4, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01b9, code lost:
    
        throw r0;
     */
    /* JADX WARN: Finally extract failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final ZipFileSystem openZip(Path zipPath, FileSystem fileSystem, Function1<? super ZipEntry, Boolean> predicate) throws IOException {
        Intrinsics.checkNotNullParameter(zipPath, "zipPath");
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        BufferedSource openReadOnly = fileSystem.openReadOnly(zipPath);
        try {
            FileHandle fileHandle = openReadOnly;
            long j = 0;
            openReadOnly = Okio.buffer(FileHandle.source$default(fileHandle, 0L, 1, null));
            try {
                int readIntLe = openReadOnly.readIntLe();
                if (readIntLe != LOCAL_FILE_HEADER_SIGNATURE) {
                    if (readIntLe == END_OF_CENTRAL_DIRECTORY_SIGNATURE) {
                        throw new IOException("unsupported zip: empty");
                    }
                    throw new IOException("not a zip: expected " + getHex(LOCAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(readIntLe));
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(openReadOnly, null);
                long size = fileHandle.size() - 22;
                if (size < 0) {
                    throw new IOException(Intrinsics.stringPlus("not a zip: size=", Long.valueOf(fileHandle.size())));
                }
                long max = Math.max(size - PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH, 0L);
                while (true) {
                    BufferedSource buffer = Okio.buffer(fileHandle.source(size));
                    try {
                        if (buffer.readIntLe() == END_OF_CENTRAL_DIRECTORY_SIGNATURE) {
                            break;
                        }
                        long j2 = j;
                        buffer.close();
                        size--;
                        if (size < max) {
                            throw new IOException("not a zip: end of central directory signature not found");
                        }
                        j = j2;
                    } catch (Throwable th) {
                        buffer.close();
                        throw th;
                    }
                }
            } finally {
                try {
                    throw th;
                } finally {
                }
            }
        } finally {
        }
    }

    private static final Map<Path, ZipEntry> buildIndex(List<ZipEntry> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (ZipEntry zipEntry : CollectionsKt.sortedWith(list, new Comparator() { // from class: okio.internal.ZipKt$buildIndex$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((ZipEntry) t).getCanonicalPath(), ((ZipEntry) t2).getCanonicalPath());
            }
        })) {
            if (((ZipEntry) linkedHashMap.put(zipEntry.getCanonicalPath(), zipEntry)) == null) {
                while (true) {
                    Path parent = zipEntry.getCanonicalPath().parent();
                    if (parent != null) {
                        ZipEntry zipEntry2 = (ZipEntry) linkedHashMap.get(parent);
                        if (zipEntry2 != null) {
                            zipEntry2.getChildren().add(zipEntry.getCanonicalPath());
                            break;
                        }
                        ZipEntry zipEntry3 = new ZipEntry(parent, true, null, 0L, 0L, 0L, 0, null, 0L, TypedValues.PositionType.TYPE_CURVE_FIT, null);
                        linkedHashMap.put(parent, zipEntry3);
                        zipEntry3.getChildren().add(zipEntry.getCanonicalPath());
                        zipEntry = zipEntry3;
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public static final ZipEntry readEntry(final BufferedSource bufferedSource) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        int readIntLe = bufferedSource.readIntLe();
        if (readIntLe != CENTRAL_FILE_HEADER_SIGNATURE) {
            throw new IOException("bad zip: expected " + getHex(CENTRAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(readIntLe));
        }
        bufferedSource.skip(4L);
        int readShortLe = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        if ((readShortLe & 1) != 0) {
            throw new IOException(Intrinsics.stringPlus("unsupported zip: general purpose bit flag=", getHex(readShortLe)));
        }
        int readShortLe2 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        Long dosDateTimeToEpochMillis = dosDateTimeToEpochMillis(bufferedSource.readShortLe() & UShort.MAX_VALUE, bufferedSource.readShortLe() & UShort.MAX_VALUE);
        long readIntLe2 = bufferedSource.readIntLe() & 4294967295L;
        final Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = bufferedSource.readIntLe() & 4294967295L;
        final Ref.LongRef longRef2 = new Ref.LongRef();
        longRef2.element = bufferedSource.readIntLe() & 4294967295L;
        int readShortLe3 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        int readShortLe4 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        int readShortLe5 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        bufferedSource.skip(8L);
        final Ref.LongRef longRef3 = new Ref.LongRef();
        longRef3.element = bufferedSource.readIntLe() & 4294967295L;
        String readUtf8 = bufferedSource.readUtf8(readShortLe3);
        if (StringsKt.contains$default((CharSequence) readUtf8, (char) 0, false, 2, (Object) null)) {
            throw new IOException("bad zip: filename contains 0x00");
        }
        long j = longRef2.element == 4294967295L ? 8 + 0 : 0L;
        long j2 = longRef.element == 4294967295L ? j + 8 : j;
        if (longRef3.element == 4294967295L) {
            j2 += 8;
        }
        final long j3 = j2;
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        readExtra(bufferedSource, readShortLe4, new Function2<Integer, Long, Unit>() { // from class: okio.internal.ZipKt$readEntry$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Long l) {
                invoke(num.intValue(), l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, long j4) {
                if (i == 1) {
                    if (Ref.BooleanRef.this.element) {
                        throw new IOException("bad zip: zip64 extra repeated");
                    }
                    Ref.BooleanRef.this.element = true;
                    if (j4 < j3) {
                        throw new IOException("bad zip: zip64 extra too short");
                    }
                    Ref.LongRef longRef4 = longRef2;
                    longRef4.element = longRef4.element == 4294967295L ? bufferedSource.readLongLe() : longRef2.element;
                    Ref.LongRef longRef5 = longRef;
                    longRef5.element = longRef5.element == 4294967295L ? bufferedSource.readLongLe() : 0L;
                    Ref.LongRef longRef6 = longRef3;
                    longRef6.element = longRef6.element == 4294967295L ? bufferedSource.readLongLe() : 0L;
                }
            }
        });
        if (j3 > 0 && !booleanRef.element) {
            throw new IOException("bad zip: zip64 extra required but absent");
        }
        return new ZipEntry(Path.Companion.get$default(Path.INSTANCE, RemoteSettings.FORWARD_SLASH_STRING, false, 1, (Object) null).resolve(readUtf8), StringsKt.endsWith$default(readUtf8, RemoteSettings.FORWARD_SLASH_STRING, false, 2, (Object) null), bufferedSource.readUtf8(readShortLe5), readIntLe2, longRef.element, longRef2.element, readShortLe2, dosDateTimeToEpochMillis, longRef3.element);
    }

    private static final EocdRecord readEocdRecord(BufferedSource bufferedSource) throws IOException {
        int readShortLe = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        int readShortLe2 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        long readShortLe3 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        if (readShortLe3 != (bufferedSource.readShortLe() & UShort.MAX_VALUE) || readShortLe != 0 || readShortLe2 != 0) {
            throw new IOException("unsupported zip: spanned");
        }
        bufferedSource.skip(4L);
        return new EocdRecord(readShortLe3, 4294967295L & bufferedSource.readIntLe(), bufferedSource.readShortLe() & UShort.MAX_VALUE);
    }

    private static final EocdRecord readZip64EocdRecord(BufferedSource bufferedSource, EocdRecord eocdRecord) throws IOException {
        bufferedSource.skip(12L);
        int readIntLe = bufferedSource.readIntLe();
        int readIntLe2 = bufferedSource.readIntLe();
        long readLongLe = bufferedSource.readLongLe();
        if (readLongLe != bufferedSource.readLongLe() || readIntLe != 0 || readIntLe2 != 0) {
            throw new IOException("unsupported zip: spanned");
        }
        bufferedSource.skip(8L);
        return new EocdRecord(readLongLe, bufferedSource.readLongLe(), eocdRecord.getCommentByteCount());
    }

    private static final void readExtra(BufferedSource bufferedSource, int i, Function2<? super Integer, ? super Long, Unit> function2) {
        long j = i;
        while (j != 0) {
            if (j < 4) {
                throw new IOException("bad zip: truncated header in extra field");
            }
            int readShortLe = bufferedSource.readShortLe() & UShort.MAX_VALUE;
            long readShortLe2 = bufferedSource.readShortLe() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
            long j2 = j - 4;
            if (j2 < readShortLe2) {
                throw new IOException("bad zip: truncated value in extra field");
            }
            bufferedSource.require(readShortLe2);
            long size = bufferedSource.getBuffer().size();
            function2.invoke(Integer.valueOf(readShortLe), Long.valueOf(readShortLe2));
            long size2 = (bufferedSource.getBuffer().size() + readShortLe2) - size;
            if (size2 < 0) {
                throw new IOException(Intrinsics.stringPlus("unsupported zip: too many bytes processed for ", Integer.valueOf(readShortLe)));
            }
            if (size2 > 0) {
                bufferedSource.getBuffer().skip(size2);
            }
            j = j2 - readShortLe2;
        }
    }

    public static final void skipLocalHeader(BufferedSource bufferedSource) {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        readOrSkipLocalHeader(bufferedSource, null);
    }

    public static final FileMetadata readLocalHeader(BufferedSource bufferedSource, FileMetadata basicMetadata) {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        Intrinsics.checkNotNullParameter(basicMetadata, "basicMetadata");
        FileMetadata readOrSkipLocalHeader = readOrSkipLocalHeader(bufferedSource, basicMetadata);
        Intrinsics.checkNotNull(readOrSkipLocalHeader);
        return readOrSkipLocalHeader;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final FileMetadata readOrSkipLocalHeader(final BufferedSource bufferedSource, FileMetadata fileMetadata) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = fileMetadata == null ? 0 : fileMetadata.getLastModifiedAtMillis();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        int readIntLe = bufferedSource.readIntLe();
        if (readIntLe != LOCAL_FILE_HEADER_SIGNATURE) {
            throw new IOException("bad zip: expected " + getHex(LOCAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(readIntLe));
        }
        bufferedSource.skip(2L);
        int readShortLe = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        if ((readShortLe & 1) != 0) {
            throw new IOException(Intrinsics.stringPlus("unsupported zip: general purpose bit flag=", getHex(readShortLe)));
        }
        bufferedSource.skip(18L);
        long readShortLe2 = bufferedSource.readShortLe() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
        int readShortLe3 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        bufferedSource.skip(readShortLe2);
        if (fileMetadata == null) {
            bufferedSource.skip(readShortLe3);
            return null;
        }
        readExtra(bufferedSource, readShortLe3, new Function2<Integer, Long, Unit>() { // from class: okio.internal.ZipKt$readOrSkipLocalHeader$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Long l) {
                invoke(num.intValue(), l.longValue());
                return Unit.INSTANCE;
            }

            /* JADX WARN: Type inference failed for: r0v13, types: [T, java.lang.Long] */
            /* JADX WARN: Type inference failed for: r10v12, types: [T, java.lang.Long] */
            /* JADX WARN: Type inference failed for: r11v3, types: [T, java.lang.Long] */
            public final void invoke(int i, long j) {
                if (i == 21589) {
                    if (j < 1) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    int readByte = BufferedSource.this.readByte() & 255;
                    boolean z = (readByte & 1) == 1;
                    boolean z2 = (readByte & 2) == 2;
                    boolean z3 = (readByte & 4) == 4;
                    BufferedSource bufferedSource2 = BufferedSource.this;
                    long j2 = z ? 5L : 1L;
                    if (z2) {
                        j2 += 4;
                    }
                    if (z3) {
                        j2 += 4;
                    }
                    if (j < j2) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    if (z) {
                        objectRef.element = Long.valueOf(bufferedSource2.readIntLe() * 1000);
                    }
                    if (z2) {
                        objectRef2.element = Long.valueOf(BufferedSource.this.readIntLe() * 1000);
                    }
                    if (z3) {
                        objectRef3.element = Long.valueOf(BufferedSource.this.readIntLe() * 1000);
                    }
                }
            }
        });
        return new FileMetadata(fileMetadata.getIsRegularFile(), fileMetadata.getIsDirectory(), null, fileMetadata.getSize(), (Long) objectRef3.element, (Long) objectRef.element, (Long) objectRef2.element, null, 128, null);
    }

    private static final Long dosDateTimeToEpochMillis(int i, int i2) {
        if (i2 == -1) {
            return null;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(14, 0);
        gregorianCalendar.set(((i >> 9) & 127) + 1980, ((i >> 5) & 15) - 1, i & 31, (i2 >> 11) & 31, (i2 >> 5) & 63, (i2 & 31) << 1);
        return Long.valueOf(gregorianCalendar.getTime().getTime());
    }

    private static final String getHex(int i) {
        String num = Integer.toString(i, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return Intrinsics.stringPlus("0x", num);
    }
}
