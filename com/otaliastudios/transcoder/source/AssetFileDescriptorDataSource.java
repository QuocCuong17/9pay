package com.otaliastudios.transcoder.source;

import android.content.res.AssetFileDescriptor;

/* loaded from: classes4.dex */
public class AssetFileDescriptorDataSource extends DataSourceWrapper {
    public AssetFileDescriptorDataSource(AssetFileDescriptor assetFileDescriptor) {
        super(new FileDescriptorDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getDeclaredLength()));
    }
}
