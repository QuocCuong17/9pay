package org.apache.commons.io.function;

import com.android.tools.r8.annotations.SynthesizedClass;
import java.io.IOException;
import java.util.Objects;
import org.apache.commons.io.function.IOConsumer;

@FunctionalInterface
/* loaded from: classes5.dex */
public interface IOConsumer<T> {
    void accept(T t) throws IOException;

    IOConsumer<T> andThen(IOConsumer<? super T> iOConsumer);

    @SynthesizedClass(kind = "$-CC")
    /* renamed from: org.apache.commons.io.function.IOConsumer$-CC, reason: invalid class name */
    /* loaded from: classes5.dex */
    public final /* synthetic */ class CC<T> {
        public static IOConsumer $default$andThen(final IOConsumer _this, final IOConsumer iOConsumer) {
            Objects.requireNonNull(iOConsumer);
            return new IOConsumer() { // from class: org.apache.commons.io.function.IOConsumer$$ExternalSyntheticLambda0
                @Override // org.apache.commons.io.function.IOConsumer
                public final void accept(Object obj) {
                    IOConsumer.CC.lambda$andThen$0(IOConsumer.this, iOConsumer, obj);
                }

                @Override // org.apache.commons.io.function.IOConsumer
                public /* synthetic */ IOConsumer andThen(IOConsumer iOConsumer2) {
                    return IOConsumer.CC.$default$andThen(this, iOConsumer2);
                }
            };
        }

        public static /* synthetic */ void lambda$andThen$0(IOConsumer _this, IOConsumer iOConsumer, Object obj) throws IOException {
            _this.accept(obj);
            iOConsumer.accept(obj);
        }
    }
}
