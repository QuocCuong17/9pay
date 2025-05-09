package com.otaliastudios.transcoder;

import com.otaliastudios.transcoder.TranscoderOptions;
import com.otaliastudios.transcoder.internal.transcode.TranscodeEngine;
import com.otaliastudios.transcoder.internal.utils.ThreadPool;
import com.otaliastudios.transcoder.sink.DataSink;
import java.io.FileDescriptor;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* loaded from: classes4.dex */
public class Transcoder {
    public static final int SUCCESS_NOT_NEEDED = 1;
    public static final int SUCCESS_TRANSCODED = 0;

    public static Transcoder getInstance() {
        return new Transcoder();
    }

    private Transcoder() {
    }

    public static TranscoderOptions.Builder into(String str) {
        return new TranscoderOptions.Builder(str);
    }

    public static TranscoderOptions.Builder into(FileDescriptor fileDescriptor) {
        return new TranscoderOptions.Builder(fileDescriptor);
    }

    public static TranscoderOptions.Builder into(DataSink dataSink) {
        return new TranscoderOptions.Builder(dataSink);
    }

    public Future<Void> transcode(final TranscoderOptions transcoderOptions) {
        return ThreadPool.getExecutor().submit(new Callable<Void>() { // from class: com.otaliastudios.transcoder.Transcoder.1
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                TranscodeEngine.transcode(transcoderOptions);
                return null;
            }
        });
    }
}
