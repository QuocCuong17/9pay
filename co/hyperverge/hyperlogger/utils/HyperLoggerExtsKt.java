package co.hyperverge.hyperlogger.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HyperLoggerExts.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0012\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006*\u00020\u0002H\u0000¨\u0006\u0007"}, d2 = {"appendLog", "", "Ljava/io/File;", "log", "", "getAllFilesFromFolder", "", "hyperlogger_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class HyperLoggerExtsKt {
    public static final /* synthetic */ List getAllFilesFromFolder(File file) {
        File[] listFiles;
        Intrinsics.checkNotNullParameter(file, "<this>");
        ArrayList arrayList = new ArrayList();
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            CollectionsKt.addAll(arrayList, listFiles);
        }
        return arrayList;
    }

    public static final /* synthetic */ void appendLog(File file, String log) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(log, "log");
        FilesKt.appendText$default(file, log, null, 2, null);
    }
}
