package co.hyperverge.hyperlogger.data.source.local;

import android.util.Log;
import co.hyperverge.hyperlogger.data.models.HyperLoggerConfig;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import io.flutter.plugins.firebase.database.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPOutputStream;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: HyperLoggerFile.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0000\u0018\u0000  2\u00020\u0001:\u0001 B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006H\u0080@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ#\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0004H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u0004\u0018\u00010\bH\u0000¢\u0006\u0002\b\u0015J\u001b\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0006H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u000eJ\u001b\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\bH\u0080@ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u001b\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lco/hyperverge/hyperlogger/data/source/local/HyperLoggerFile;", "", "()V", "hyperLoggerConfig", "Lco/hyperverge/hyperlogger/data/models/HyperLoggerConfig;", "logEventsFilePath", "", "logEventsFolder", "Ljava/io/File;", "logZipFile", "appendData", "", "hyperLogEvent", "appendData$hyperlogger_release", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createFile", "", HVRetakeActivity.CONFIG_TAG, "createFile$hyperlogger_release", "(Ljava/io/File;Lco/hyperverge/hyperlogger/data/models/HyperLoggerConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createLogZipFile", "createLogZipFile$hyperlogger_release", "deleteFile", Constants.PATH, "deleteFile$hyperlogger_release", "deleteLogFolder", "folderToDelete", "deleteLogFolder$hyperlogger_release", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isFileExists", "isFileExists$hyperlogger_release", "(Lco/hyperverge/hyperlogger/data/models/HyperLoggerConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "hyperlogger_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class HyperLoggerFile {
    private static HyperLoggerFile INSTANCE;
    private HyperLoggerConfig hyperLoggerConfig;
    private String logEventsFilePath;
    private File logEventsFolder;
    private File logZipFile;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = Reflection.getOrCreateKotlinClass(HyperLoggerFile.class).getQualifiedName();

    public /* synthetic */ HyperLoggerFile(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private HyperLoggerFile() {
    }

    public final Object createFile$hyperlogger_release(File file, HyperLoggerConfig hyperLoggerConfig, Continuation<? super Boolean> continuation) {
        return CoroutineScopeKt.coroutineScope(new HyperLoggerFile$createFile$2(this, hyperLoggerConfig, file, null), continuation);
    }

    public final Object isFileExists$hyperlogger_release(HyperLoggerConfig hyperLoggerConfig, Continuation<? super Boolean> continuation) {
        return CoroutineScopeKt.coroutineScope(new HyperLoggerFile$isFileExists$2(this, hyperLoggerConfig, null), continuation);
    }

    public final Object deleteFile$hyperlogger_release(String str, Continuation<? super Boolean> continuation) {
        return CoroutineScopeKt.coroutineScope(new HyperLoggerFile$deleteFile$2(str, null), continuation);
    }

    public final Object deleteLogFolder$hyperlogger_release(File file, Continuation<? super Boolean> continuation) {
        return CoroutineScopeKt.coroutineScope(new HyperLoggerFile$deleteLogFolder$2(file, null), continuation);
    }

    public final Object appendData$hyperlogger_release(String str, Continuation<? super Unit> continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new HyperLoggerFile$appendData$2(this, str, null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    public final File createLogZipFile$hyperlogger_release() {
        Object m1202constructorimpl;
        String str;
        String str2 = TAG;
        Log.d(str2, "createLogZipFile() called");
        try {
            Result.Companion companion = Result.INSTANCE;
            HyperLoggerFile hyperLoggerFile = this;
            File file = this.logEventsFolder;
            if (file == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logEventsFolder");
                file = null;
            }
            HyperLoggerConfig hyperLoggerConfig = this.hyperLoggerConfig;
            if (hyperLoggerConfig == null) {
                Intrinsics.throwUninitializedPropertyAccessException("hyperLoggerConfig");
                hyperLoggerConfig = null;
            }
            this.logZipFile = new File(file, Intrinsics.stringPlus(hyperLoggerConfig.getFileName(), ".gz"));
            str = this.logEventsFilePath;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logEventsFilePath");
                str = null;
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (!new File(str).exists()) {
            String str3 = this.logEventsFilePath;
            if (str3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logEventsFilePath");
                str3 = null;
            }
            Log.e(str2, Intrinsics.stringPlus("Log file does not exist at path: ", str3));
            return null;
        }
        String str4 = this.logEventsFilePath;
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logEventsFilePath");
            str4 = null;
        }
        GZIPOutputStream fileInputStream = new FileInputStream(str4);
        try {
            FileInputStream fileInputStream2 = fileInputStream;
            File file2 = this.logZipFile;
            fileInputStream = new FileOutputStream(file2 == null ? null : file2.getPath());
            try {
                fileInputStream = new GZIPOutputStream(fileInputStream);
                try {
                    GZIPOutputStream gZIPOutputStream = fileInputStream;
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream2.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        gZIPOutputStream.write(bArr, 0, read);
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileInputStream, null);
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileInputStream, null);
                    Unit unit3 = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileInputStream, null);
                    m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                    Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                    if (m1205exceptionOrNullimpl != null) {
                        Log.e(TAG, Intrinsics.stringPlus("createLogZipFile: ", m1205exceptionOrNullimpl.getMessage()));
                    }
                    return this.logZipFile;
                } finally {
                }
            } finally {
            }
        } finally {
        }
    }

    /* compiled from: HyperLoggerFile.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lco/hyperverge/hyperlogger/data/source/local/HyperLoggerFile$Companion;", "", "()V", "INSTANCE", "Lco/hyperverge/hyperlogger/data/source/local/HyperLoggerFile;", "TAG", "", "getInstance", "hyperlogger_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HyperLoggerFile getInstance() {
            HyperLoggerFile hyperLoggerFile = HyperLoggerFile.INSTANCE;
            if (hyperLoggerFile != null) {
                return hyperLoggerFile;
            }
            HyperLoggerFile hyperLoggerFile2 = new HyperLoggerFile(null);
            Companion companion = HyperLoggerFile.INSTANCE;
            HyperLoggerFile.INSTANCE = hyperLoggerFile2;
            return hyperLoggerFile2;
        }
    }
}
