package androidx.window.embedding;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EmbeddingBackend.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class EmbeddingBackend$Companion$overrideDecorator$1 extends FunctionReferenceImpl implements Function1<EmbeddingBackend, EmbeddingBackend> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public EmbeddingBackend$Companion$overrideDecorator$1(Object obj) {
        super(1, obj, EmbeddingBackendDecorator.class, "decorate", "decorate(Landroidx/window/embedding/EmbeddingBackend;)Landroidx/window/embedding/EmbeddingBackend;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final EmbeddingBackend invoke(EmbeddingBackend p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return ((EmbeddingBackendDecorator) this.receiver).decorate(p0);
    }
}
