package com.google.firebase.firestore.util;

import android.os.Build;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/* loaded from: classes4.dex */
public class FileUtil {
    public static void delete(File file) throws IOException {
        if (Build.VERSION.SDK_INT >= 26) {
            DefaultFileDeleter.delete(file);
        } else {
            LegacyFileDeleter.delete(file);
        }
    }

    /* loaded from: classes4.dex */
    private static class DefaultFileDeleter {
        private DefaultFileDeleter() {
        }

        public static void delete(File file) throws IOException {
            try {
                Files.deleteIfExists(file.toPath());
            } catch (IOException e) {
                throw new IOException("Failed to delete file " + file + ": " + e);
            }
        }
    }

    /* loaded from: classes4.dex */
    private static class LegacyFileDeleter {
        private LegacyFileDeleter() {
        }

        public static void delete(File file) throws IOException {
            if (file.delete() || !file.exists()) {
                return;
            }
            throw new IOException("Failed to delete file " + file);
        }
    }
}
