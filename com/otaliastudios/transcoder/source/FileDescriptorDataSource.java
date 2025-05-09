package com.otaliastudios.transcoder.source;

import android.media.MediaExtractor;
import android.media.MediaMetadataRetriever;
import java.io.FileDescriptor;
import java.io.IOException;

/* loaded from: classes4.dex */
public class FileDescriptorDataSource extends DefaultDataSource {
    private final FileDescriptor descriptor;
    private final long length;
    private final long offset;

    public FileDescriptorDataSource(FileDescriptor fileDescriptor) {
        this(fileDescriptor, 0L, 576460752303423487L);
    }

    public FileDescriptorDataSource(FileDescriptor fileDescriptor, long j, long j2) {
        this.descriptor = fileDescriptor;
        this.offset = j;
        this.length = j2 <= 0 ? 576460752303423487L : j2;
    }

    @Override // com.otaliastudios.transcoder.source.DefaultDataSource
    protected void initializeExtractor(MediaExtractor mediaExtractor) throws IOException {
        mediaExtractor.setDataSource(this.descriptor, this.offset, this.length);
    }

    @Override // com.otaliastudios.transcoder.source.DefaultDataSource
    protected void initializeRetriever(MediaMetadataRetriever mediaMetadataRetriever) {
        mediaMetadataRetriever.setDataSource(this.descriptor, this.offset, this.length);
    }
}
