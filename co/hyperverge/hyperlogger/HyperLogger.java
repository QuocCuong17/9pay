package co.hyperverge.hyperlogger;

import android.util.Log;
import co.hyperverge.hyperlogger.data.models.HyperLoggerConfig;
import co.hyperverge.hyperlogger.data.source.local.HyperLoggerFile;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import java.io.File;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: HyperLogger.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u00162\u00020\u0001:\u0002\u0016\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\nJ\b\u0010\u000e\u001a\u0004\u0018\u00010\fJ\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lco/hyperverge/hyperlogger/HyperLogger;", "", "()V", "hyperLoggerConfig", "Lco/hyperverge/hyperlogger/data/models/HyperLoggerConfig;", "isHyperLoggerInitialised", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "deleteLogFolder", "", "rootFolderPath", "Ljava/io/File;", "flush", "getCurrentLogZipFile", "log", "level", "Lco/hyperverge/hyperlogger/HyperLogger$Level;", "message", "", "setup", HVRetakeActivity.CONFIG_TAG, "Companion", "Level", "hyperlogger_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class HyperLogger {
    private static HyperLogger INSTANCE = null;
    private static final String logFolderPath = "hv/logData/";
    private HyperLoggerConfig hyperLoggerConfig;
    private boolean isHyperLoggerInitialised;
    private final CoroutineScope scope;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = Reflection.getOrCreateKotlinClass(HyperLogger.class).getQualifiedName();

    /* compiled from: HyperLogger.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lco/hyperverge/hyperlogger/HyperLogger$Level;", "", "(Ljava/lang/String;I)V", "DEBUG", "ERROR", "hyperlogger_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes2.dex */
    public enum Level {
        DEBUG,
        ERROR
    }

    public /* synthetic */ HyperLogger(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @JvmStatic
    public static final HyperLogger getInstance() {
        return INSTANCE.getInstance();
    }

    private HyperLogger() {
        this.scope = CoroutineScopeKt.MainScope();
    }

    public final void setup(File rootFolderPath, HyperLoggerConfig config) {
        Object m1202constructorimpl;
        Intrinsics.checkNotNullParameter(rootFolderPath, "rootFolderPath");
        Intrinsics.checkNotNullParameter(config, "config");
        Log.d(TAG, Intrinsics.stringPlus("setup() called with: config = ", config));
        this.hyperLoggerConfig = config;
        try {
            Result.Companion companion = Result.INSTANCE;
            HyperLogger hyperLogger = this;
            m1202constructorimpl = Result.m1202constructorimpl(BuildersKt.launch$default(this.scope, Dispatchers.getIO(), null, new HyperLogger$setup$1$1(rootFolderPath, config, this, null), 2, null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null) {
            if (m1205exceptionOrNullimpl instanceof InternalError) {
                Log.w(TAG, m1205exceptionOrNullimpl.getMessage(), m1205exceptionOrNullimpl);
                return;
            }
            throw m1205exceptionOrNullimpl;
        }
    }

    public final void log(Level level, String message) {
        Object m1202constructorimpl;
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(message, "message");
        String str = System.currentTimeMillis() + ";;;" + level + ";;;" + message + '\n';
        try {
            Result.Companion companion = Result.INSTANCE;
            HyperLogger hyperLogger = this;
            m1202constructorimpl = Result.m1202constructorimpl(BuildersKt.launch$default(this.scope, Dispatchers.getIO(), null, new HyperLogger$log$1$1(this, str, null), 2, null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null) {
            if (m1205exceptionOrNullimpl instanceof InternalError) {
                Log.w(TAG, m1205exceptionOrNullimpl.getMessage(), m1205exceptionOrNullimpl);
                return;
            }
            throw m1205exceptionOrNullimpl;
        }
    }

    public final File getCurrentLogZipFile() {
        if (this.isHyperLoggerInitialised) {
            return HyperLoggerFile.INSTANCE.getInstance().createLogZipFile$hyperlogger_release();
        }
        return null;
    }

    /* renamed from: isHyperLoggerInitialised, reason: from getter */
    public final boolean getIsHyperLoggerInitialised() {
        return this.isHyperLoggerInitialised;
    }

    public final void flush() {
        String str = TAG;
        Log.d(str, "flush() called");
        if (this.isHyperLoggerInitialised) {
            this.isHyperLoggerInitialised = false;
        } else {
            Log.d(str, "HyperLogger not initialised. Call HyperLogger.setup function to initialise");
        }
    }

    public final void deleteLogFolder(File rootFolderPath) {
        Object m1202constructorimpl;
        Intrinsics.checkNotNullParameter(rootFolderPath, "rootFolderPath");
        Log.d(TAG, "deleteLogFolder() called");
        try {
            Result.Companion companion = Result.INSTANCE;
            HyperLogger hyperLogger = this;
            m1202constructorimpl = Result.m1202constructorimpl(BuildersKt.launch$default(this.scope, Dispatchers.getIO(), null, new HyperLogger$deleteLogFolder$1$1(rootFolderPath, null), 2, null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null) {
            if (m1205exceptionOrNullimpl instanceof InternalError) {
                Log.w(TAG, m1205exceptionOrNullimpl.getMessage(), m1205exceptionOrNullimpl);
                return;
            }
            throw m1205exceptionOrNullimpl;
        }
    }

    /* compiled from: HyperLogger.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\u0004H\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lco/hyperverge/hyperlogger/HyperLogger$Companion;", "", "()V", "INSTANCE", "Lco/hyperverge/hyperlogger/HyperLogger;", "TAG", "", "logFolderPath", "getInstance", "hyperlogger_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final HyperLogger getInstance() {
            HyperLogger hyperLogger = HyperLogger.INSTANCE;
            if (hyperLogger != null) {
                return hyperLogger;
            }
            HyperLogger hyperLogger2 = new HyperLogger(null);
            Companion companion = HyperLogger.INSTANCE;
            HyperLogger.INSTANCE = hyperLogger2;
            return hyperLogger2;
        }
    }
}
