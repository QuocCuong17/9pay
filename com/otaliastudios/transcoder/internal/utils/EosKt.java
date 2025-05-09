package com.otaliastudios.transcoder.internal.utils;

import com.otaliastudios.transcoder.sink.DataSink;
import com.otaliastudios.transcoder.source.DataSource;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: eos.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0000\u001a\u001a\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0000¨\u0006\b"}, d2 = {"forcingEos", "Lcom/otaliastudios/transcoder/source/DataSource;", "force", "Lkotlin/Function0;", "", "ignoringEos", "Lcom/otaliastudios/transcoder/sink/DataSink;", "ignore", "lib_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class EosKt {
    public static final DataSink ignoringEos(DataSink dataSink, Function0<Boolean> ignore) {
        Intrinsics.checkNotNullParameter(dataSink, "<this>");
        Intrinsics.checkNotNullParameter(ignore, "ignore");
        return new EosIgnoringDataSink(dataSink, ignore);
    }

    public static final DataSource forcingEos(DataSource dataSource, Function0<Boolean> force) {
        Intrinsics.checkNotNullParameter(dataSource, "<this>");
        Intrinsics.checkNotNullParameter(force, "force");
        return new EosForcingDataSource(dataSource, force);
    }
}
