package io.sentry.internal.modules;

import io.sentry.ILogger;
import io.sentry.SentryLevel;
import io.sentry.util.ClassLoaderUtils;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes5.dex */
public final class ResourcesModulesLoader extends ModulesLoader {
    private final ClassLoader classLoader;

    public ResourcesModulesLoader(ILogger iLogger) {
        this(iLogger, ResourcesModulesLoader.class.getClassLoader());
    }

    ResourcesModulesLoader(ILogger iLogger, ClassLoader classLoader) {
        super(iLogger);
        this.classLoader = ClassLoaderUtils.classLoaderOrDefault(classLoader);
    }

    @Override // io.sentry.internal.modules.ModulesLoader
    protected Map<String, String> loadModules() {
        TreeMap treeMap = new TreeMap();
        try {
            InputStream resourceAsStream = this.classLoader.getResourceAsStream(ModulesLoader.EXTERNAL_MODULES_FILENAME);
            if (resourceAsStream == null) {
                this.logger.log(SentryLevel.INFO, "%s file was not found.", ModulesLoader.EXTERNAL_MODULES_FILENAME);
                return treeMap;
            }
            return parseStream(resourceAsStream);
        } catch (SecurityException e) {
            this.logger.log(SentryLevel.INFO, "Access to resources denied.", e);
            return treeMap;
        }
    }
}
