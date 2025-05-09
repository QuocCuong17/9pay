package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.UUID;
import org.apache.commons.io.TaggedIOException;

/* loaded from: classes5.dex */
public class TaggedReader extends ProxyReader {
    private final Serializable tag;

    public TaggedReader(Reader reader) {
        super(reader);
        this.tag = UUID.randomUUID();
    }

    public boolean isCauseOf(Throwable th) {
        return TaggedIOException.isTaggedWith(th, this.tag);
    }

    public void throwIfCauseOf(Throwable th) throws IOException {
        TaggedIOException.throwCauseIfTaggedWith(th, this.tag);
    }

    @Override // org.apache.commons.io.input.ProxyReader
    protected void handleIOException(IOException iOException) throws IOException {
        throw new TaggedIOException(iOException, this.tag);
    }
}
