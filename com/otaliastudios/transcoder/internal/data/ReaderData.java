package com.otaliastudios.transcoder.internal.data;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.otaliastudios.transcoder.source.DataSource;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Reader.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/otaliastudios/transcoder/internal/data/ReaderData;", "", "chunk", "Lcom/otaliastudios/transcoder/source/DataSource$Chunk;", "id", "", "(Lcom/otaliastudios/transcoder/source/DataSource$Chunk;I)V", "getChunk", "()Lcom/otaliastudios/transcoder/source/DataSource$Chunk;", "getId", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class ReaderData {
    private final DataSource.Chunk chunk;
    private final int id;

    public static /* synthetic */ ReaderData copy$default(ReaderData readerData, DataSource.Chunk chunk, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            chunk = readerData.chunk;
        }
        if ((i2 & 2) != 0) {
            i = readerData.id;
        }
        return readerData.copy(chunk, i);
    }

    /* renamed from: component1, reason: from getter */
    public final DataSource.Chunk getChunk() {
        return this.chunk;
    }

    /* renamed from: component2, reason: from getter */
    public final int getId() {
        return this.id;
    }

    public final ReaderData copy(DataSource.Chunk chunk, int id2) {
        Intrinsics.checkNotNullParameter(chunk, "chunk");
        return new ReaderData(chunk, id2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReaderData)) {
            return false;
        }
        ReaderData readerData = (ReaderData) other;
        return Intrinsics.areEqual(this.chunk, readerData.chunk) && this.id == readerData.id;
    }

    public int hashCode() {
        return (this.chunk.hashCode() * 31) + this.id;
    }

    public String toString() {
        return "ReaderData(chunk=" + this.chunk + ", id=" + this.id + ')';
    }

    public ReaderData(DataSource.Chunk chunk, int i) {
        Intrinsics.checkNotNullParameter(chunk, "chunk");
        this.chunk = chunk;
        this.id = i;
    }

    public final DataSource.Chunk getChunk() {
        return this.chunk;
    }

    public final int getId() {
        return this.id;
    }
}
