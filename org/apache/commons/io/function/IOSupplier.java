package org.apache.commons.io.function;

import java.io.IOException;

@FunctionalInterface
/* loaded from: classes5.dex */
public interface IOSupplier<T> {
    T get() throws IOException;
}
