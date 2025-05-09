package co.hyperverge.hyperkyc.data.network.progress;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/* compiled from: ProgressRequestBody.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0002\u0010\u0011B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\n\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0006\u001a\b\u0018\u00010\u0007R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/progress/ProgressRequestBody;", "Lokhttp3/RequestBody;", "mDelegate", "mListener", "Lco/hyperverge/hyperkyc/data/network/progress/ProgressRequestBody$Listener;", "(Lokhttp3/RequestBody;Lco/hyperverge/hyperkyc/data/network/progress/ProgressRequestBody$Listener;)V", "mCountingSink", "Lco/hyperverge/hyperkyc/data/network/progress/ProgressRequestBody$CountingSink;", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "writeTo", "", "sink", "Lokio/BufferedSink;", "CountingSink", "Listener", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ProgressRequestBody extends RequestBody {
    private CountingSink mCountingSink;
    private RequestBody mDelegate;
    private Listener mListener;

    /* compiled from: ProgressRequestBody.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/progress/ProgressRequestBody$Listener;", "", "onProgress", "", "progress", "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface Listener {
        void onProgress(int progress);
    }

    public ProgressRequestBody(RequestBody mDelegate, Listener mListener) {
        Intrinsics.checkNotNullParameter(mDelegate, "mDelegate");
        Intrinsics.checkNotNullParameter(mListener, "mListener");
        this.mDelegate = mDelegate;
        this.mListener = mListener;
    }

    @Override // okhttp3.RequestBody
    /* renamed from: contentType */
    public MediaType getContentType() {
        return this.mDelegate.getContentType();
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        try {
            return this.mDelegate.contentLength();
        } catch (IOException unused) {
            return -1L;
        }
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink sink) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        CountingSink countingSink = new CountingSink(this, sink);
        this.mCountingSink = countingSink;
        Intrinsics.checkNotNull(countingSink);
        BufferedSink buffer = Okio.buffer(countingSink);
        this.mDelegate.writeTo(buffer);
        buffer.flush();
    }

    /* compiled from: ProgressRequestBody.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/progress/ProgressRequestBody$CountingSink;", "Lokio/ForwardingSink;", "delegate", "Lokio/Sink;", "(Lco/hyperverge/hyperkyc/data/network/progress/ProgressRequestBody;Lokio/Sink;)V", "bytesWritten", "", "write", "", "source", "Lokio/Buffer;", "byteCount", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    private final class CountingSink extends ForwardingSink {
        private long bytesWritten;
        final /* synthetic */ ProgressRequestBody this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CountingSink(ProgressRequestBody progressRequestBody, Sink delegate) {
            super(delegate);
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.this$0 = progressRequestBody;
        }

        @Override // okio.ForwardingSink, okio.Sink
        public void write(Buffer source, long byteCount) throws IOException {
            Intrinsics.checkNotNullParameter(source, "source");
            super.write(source, byteCount);
            this.bytesWritten += byteCount;
            this.this$0.mListener.onProgress((int) ((((float) this.bytesWritten) * 100.0f) / ((float) this.this$0.contentLength())));
        }
    }
}
