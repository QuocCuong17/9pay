package com.google.firebase.database.logging;

import com.google.firebase.database.logging.Logger;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public class DefaultLogger implements Logger {
    private final Set<String> enabledComponents;
    private final Logger.Level minLevel;

    public DefaultLogger(Logger.Level level, List<String> list) {
        if (list != null) {
            this.enabledComponents = new HashSet(list);
        } else {
            this.enabledComponents = null;
        }
        this.minLevel = level;
    }

    @Override // com.google.firebase.database.logging.Logger
    public Logger.Level getLogLevel() {
        return this.minLevel;
    }

    @Override // com.google.firebase.database.logging.Logger
    public void onLogMessage(Logger.Level level, String str, String str2, long j) {
        if (shouldLog(level, str)) {
            String buildLogMessage = buildLogMessage(level, str, str2, j);
            int i = AnonymousClass1.$SwitchMap$com$google$firebase$database$logging$Logger$Level[level.ordinal()];
            if (i == 1) {
                error(str, buildLogMessage);
                return;
            }
            if (i == 2) {
                warn(str, buildLogMessage);
            } else if (i == 3) {
                info(str, buildLogMessage);
            } else {
                if (i == 4) {
                    debug(str, buildLogMessage);
                    return;
                }
                throw new RuntimeException("Should not reach here!");
            }
        }
    }

    /* renamed from: com.google.firebase.database.logging.DefaultLogger$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$database$logging$Logger$Level;

        static {
            int[] iArr = new int[Logger.Level.values().length];
            $SwitchMap$com$google$firebase$database$logging$Logger$Level = iArr;
            try {
                iArr[Logger.Level.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$database$logging$Logger$Level[Logger.Level.WARN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$database$logging$Logger$Level[Logger.Level.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$database$logging$Logger$Level[Logger.Level.DEBUG.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    protected String buildLogMessage(Logger.Level level, String str, String str2, long j) {
        return new Date(j).toString() + " [" + level + "] " + str + ": " + str2;
    }

    protected void error(String str, String str2) {
        System.err.println(str2);
    }

    protected void warn(String str, String str2) {
        System.out.println(str2);
    }

    protected void info(String str, String str2) {
        System.out.println(str2);
    }

    protected void debug(String str, String str2) {
        System.out.println(str2);
    }

    protected boolean shouldLog(Logger.Level level, String str) {
        return level.ordinal() >= this.minLevel.ordinal() && (this.enabledComponents == null || level.ordinal() > Logger.Level.DEBUG.ordinal() || this.enabledComponents.contains(str));
    }
}
