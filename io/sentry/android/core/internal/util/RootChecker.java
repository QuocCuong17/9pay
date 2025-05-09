package io.sentry.android.core.internal.util;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import io.sentry.ILogger;
import io.sentry.SentryLevel;
import io.sentry.android.core.BuildInfoProvider;
import io.sentry.util.Objects;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/* loaded from: classes5.dex */
public final class RootChecker {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final BuildInfoProvider buildInfoProvider;
    private final Context context;
    private final ILogger logger;
    private final String[] rootFiles;
    private final String[] rootPackages;
    private final Runtime runtime;

    public RootChecker(Context context, BuildInfoProvider buildInfoProvider, ILogger iLogger) {
        this(context, buildInfoProvider, iLogger, new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su", "/su/bin", "/system/xbin/daemonsu"}, new String[]{"com.devadvance.rootcloak", "com.devadvance.rootcloakplus", "com.koushikdutta.superuser", "com.thirdparty.superuser", "eu.chainfire.supersu", "com.noshufou.android.su"}, Runtime.getRuntime());
    }

    RootChecker(Context context, BuildInfoProvider buildInfoProvider, ILogger iLogger, String[] strArr, String[] strArr2, Runtime runtime) {
        this.context = (Context) Objects.requireNonNull(context, "The application context is required.");
        this.buildInfoProvider = (BuildInfoProvider) Objects.requireNonNull(buildInfoProvider, "The BuildInfoProvider is required.");
        this.logger = (ILogger) Objects.requireNonNull(iLogger, "The Logger is required.");
        this.rootFiles = (String[]) Objects.requireNonNull(strArr, "The root Files are required.");
        this.rootPackages = (String[]) Objects.requireNonNull(strArr2, "The root packages are required.");
        this.runtime = (Runtime) Objects.requireNonNull(runtime, "The Runtime is required.");
    }

    public boolean isDeviceRooted() {
        return checkTestKeys() || checkRootFiles() || checkSUExist() || checkRootPackages(this.logger);
    }

    private boolean checkTestKeys() {
        String buildTags = this.buildInfoProvider.getBuildTags();
        return buildTags != null && buildTags.contains("test-keys");
    }

    private boolean checkRootFiles() {
        for (String str : this.rootFiles) {
            try {
            } catch (RuntimeException e) {
                this.logger.log(SentryLevel.ERROR, e, "Error when trying to check if root file %s exists.", str);
            }
            if (new File(str).exists()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0046, code lost:
    
        if (0 == 0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0048, code lost:
    
        r2.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005a, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0057, code lost:
    
        if (0 == 0) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean checkSUExist() {
        Process process = null;
        try {
            try {
                try {
                    Process exec = this.runtime.exec(new String[]{"/system/xbin/which", CmcdConfiguration.KEY_STARTUP});
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream(), UTF_8));
                    try {
                        boolean z = bufferedReader.readLine() != null;
                        bufferedReader.close();
                        if (exec != null) {
                            exec.destroy();
                        }
                        return z;
                    } catch (Throwable th) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    this.logger.log(SentryLevel.DEBUG, "Error when trying to check if SU exists.", th3);
                }
            } catch (IOException unused) {
                this.logger.log(SentryLevel.DEBUG, "SU isn't found on this Device.", new Object[0]);
            }
        } catch (Throwable th4) {
            if (0 != 0) {
                process.destroy();
            }
            throw th4;
        }
    }

    private boolean checkRootPackages(ILogger iLogger) {
        BuildInfoProvider buildInfoProvider = new BuildInfoProvider(iLogger);
        PackageManager packageManager = this.context.getPackageManager();
        if (packageManager != null) {
            for (String str : this.rootPackages) {
                try {
                    if (buildInfoProvider.getSdkInfoVersion() >= 33) {
                        packageManager.getPackageInfo(str, PackageManager.PackageInfoFlags.of(0L));
                        return true;
                    }
                    packageManager.getPackageInfo(str, 0);
                    return true;
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
        }
        return false;
    }
}
