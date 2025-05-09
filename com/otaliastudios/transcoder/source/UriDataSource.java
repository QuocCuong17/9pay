package com.otaliastudios.transcoder.source;

import android.content.Context;
import android.media.MediaExtractor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes4.dex */
public class UriDataSource extends DefaultDataSource {
    private final Context context;
    private final Uri uri;

    public UriDataSource(Context context, Uri uri) {
        this.context = context.getApplicationContext();
        this.uri = uri;
    }

    @Override // com.otaliastudios.transcoder.source.DefaultDataSource
    protected void initializeExtractor(MediaExtractor mediaExtractor) throws IOException {
        mediaExtractor.setDataSource(this.context, this.uri, (Map<String, String>) null);
    }

    @Override // com.otaliastudios.transcoder.source.DefaultDataSource
    protected void initializeRetriever(MediaMetadataRetriever mediaMetadataRetriever) {
        mediaMetadataRetriever.setDataSource(this.context, this.uri);
    }
}
