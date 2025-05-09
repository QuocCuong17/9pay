package com.otaliastudios.transcoder.source;

/* loaded from: classes4.dex */
public class ClipDataSource extends DataSourceWrapper {
    public ClipDataSource(DataSource dataSource, long j) {
        super(new TrimDataSource(dataSource, j));
    }

    public ClipDataSource(DataSource dataSource, long j, long j2) {
        super(new TrimDataSource(dataSource, j, getSourceDurationUs(dataSource) - j2));
    }

    private static long getSourceDurationUs(DataSource dataSource) {
        if (!dataSource.isInitialized()) {
            dataSource.initialize();
        }
        return dataSource.getDurationUs();
    }
}
