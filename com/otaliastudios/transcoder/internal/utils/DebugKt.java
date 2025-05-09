package com.otaliastudios.transcoder.internal.utils;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.io.IOUtils;

/* compiled from: debug.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"stackTrace", "", "lib_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class DebugKt {
    public static final String stackTrace() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "currentThread().stackTrace");
        return CollectionsKt.joinToString$default(CollectionsKt.take(ArraysKt.drop(stackTrace, 2), 10), IOUtils.LINE_SEPARATOR_UNIX, null, null, 0, null, null, 62, null);
    }
}
