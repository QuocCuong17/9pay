package com.mr.flutter.plugin.filepicker;

import android.net.Uri;
import io.flutter.plugins.firebase.database.Constants;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class FileInfo {
    final byte[] bytes;
    final String name;
    final String path;
    final long size;
    final Uri uri;

    public FileInfo(String str, String str2, Uri uri, long j, byte[] bArr) {
        this.path = str;
        this.name = str2;
        this.size = j;
        this.bytes = bArr;
        this.uri = uri;
    }

    /* loaded from: classes4.dex */
    public static class Builder {
        private byte[] bytes;
        private String name;
        private String path;
        private long size;
        private Uri uri;

        public Builder withPath(String str) {
            this.path = str;
            return this;
        }

        public Builder withName(String str) {
            this.name = str;
            return this;
        }

        public Builder withSize(long j) {
            this.size = j;
            return this;
        }

        public Builder withData(byte[] bArr) {
            this.bytes = bArr;
            return this;
        }

        public Builder withUri(Uri uri) {
            this.uri = uri;
            return this;
        }

        public FileInfo build() {
            return new FileInfo(this.path, this.name, this.uri, this.size, this.bytes);
        }
    }

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(Constants.PATH, this.path);
        hashMap.put("name", this.name);
        hashMap.put("size", Long.valueOf(this.size));
        hashMap.put("bytes", this.bytes);
        hashMap.put("identifier", this.uri.toString());
        return hashMap;
    }
}
