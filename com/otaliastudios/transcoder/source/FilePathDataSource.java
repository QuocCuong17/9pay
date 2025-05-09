package com.otaliastudios.transcoder.source;

import java.io.FileInputStream;
import java.io.IOException;

/* loaded from: classes4.dex */
public class FilePathDataSource extends DataSourceWrapper {
    private final String mPath;
    private FileInputStream mStream;

    public FilePathDataSource(String str) {
        this.mPath = str;
    }

    @Override // com.otaliastudios.transcoder.source.DataSourceWrapper, com.otaliastudios.transcoder.source.DataSource
    public void initialize() {
        try {
            this.mStream = new FileInputStream(this.mPath);
            setSource(new FileDescriptorDataSource(this.mStream.getFD()));
            super.initialize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.otaliastudios.transcoder.source.DataSourceWrapper, com.otaliastudios.transcoder.source.DataSource
    public void deinitialize() {
        try {
            this.mStream.close();
        } catch (IOException unused) {
        }
        super.deinitialize();
    }
}
