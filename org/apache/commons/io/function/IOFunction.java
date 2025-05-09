package org.apache.commons.io.function;

import com.android.tools.r8.annotations.SynthesizedClass;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.function.IOFunction;

@FunctionalInterface
/* loaded from: classes5.dex */
public interface IOFunction<T, R> {
    IOConsumer<T> andThen(Consumer<? super R> consumer);

    IOConsumer<T> andThen(IOConsumer<? super R> iOConsumer);

    <V> IOFunction<T, V> andThen(Function<? super R, ? extends V> function);

    <V> IOFunction<T, V> andThen(IOFunction<? super R, ? extends V> iOFunction);

    R apply(T t) throws IOException;

    <V> IOFunction<V, R> compose(Function<? super V, ? extends T> function);

    <V> IOFunction<V, R> compose(IOFunction<? super V, ? extends T> iOFunction);

    IOSupplier<R> compose(Supplier<? extends T> supplier);

    IOSupplier<R> compose(IOSupplier<? extends T> iOSupplier);

    @SynthesizedClass(kind = "$-CC")
    /* renamed from: org.apache.commons.io.function.IOFunction$-CC, reason: invalid class name */
    /* loaded from: classes5.dex */
    public final /* synthetic */ class CC<T, R> {
        public static /* synthetic */ Object lambda$identity$8(Object obj) throws IOException {
            return obj;
        }

        public static IOFunction $default$compose(final IOFunction _this, final IOFunction iOFunction) {
            Objects.requireNonNull(iOFunction);
            return new IOFunction() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda5
                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOConsumer andThen(Consumer consumer) {
                    return IOFunction.CC.$default$andThen(this, consumer);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOConsumer andThen(IOConsumer iOConsumer) {
                    return IOFunction.CC.$default$andThen(this, iOConsumer);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction andThen(Function function) {
                    return IOFunction.CC.$default$andThen(this, function);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction andThen(IOFunction iOFunction2) {
                    return IOFunction.CC.$default$andThen(this, iOFunction2);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public final Object apply(Object obj) {
                    Object apply;
                    apply = IOFunction.this.apply(iOFunction.apply(obj));
                    return apply;
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction compose(Function function) {
                    return IOFunction.CC.$default$compose(this, function);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction compose(IOFunction iOFunction2) {
                    return IOFunction.CC.$default$compose(this, iOFunction2);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOSupplier compose(Supplier supplier) {
                    return IOFunction.CC.$default$compose(this, supplier);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOSupplier compose(IOSupplier iOSupplier) {
                    return IOFunction.CC.$default$compose(this, iOSupplier);
                }
            };
        }

        public static IOFunction $default$compose(final IOFunction _this, final Function function) {
            Objects.requireNonNull(function);
            return new IOFunction() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda3
                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOConsumer andThen(Consumer consumer) {
                    return IOFunction.CC.$default$andThen(this, consumer);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOConsumer andThen(IOConsumer iOConsumer) {
                    return IOFunction.CC.$default$andThen(this, iOConsumer);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction andThen(Function function2) {
                    return IOFunction.CC.$default$andThen(this, function2);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction andThen(IOFunction iOFunction) {
                    return IOFunction.CC.$default$andThen(this, iOFunction);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public final Object apply(Object obj) {
                    Object apply;
                    apply = IOFunction.this.apply(function.apply(obj));
                    return apply;
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction compose(Function function2) {
                    return IOFunction.CC.$default$compose(this, function2);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction compose(IOFunction iOFunction) {
                    return IOFunction.CC.$default$compose(this, iOFunction);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOSupplier compose(Supplier supplier) {
                    return IOFunction.CC.$default$compose(this, supplier);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOSupplier compose(IOSupplier iOSupplier) {
                    return IOFunction.CC.$default$compose(this, iOSupplier);
                }
            };
        }

        public static IOSupplier $default$compose(final IOFunction _this, final IOSupplier iOSupplier) {
            Objects.requireNonNull(iOSupplier);
            return new IOSupplier() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda8
                @Override // org.apache.commons.io.function.IOSupplier
                public final Object get() {
                    Object apply;
                    apply = IOFunction.this.apply(iOSupplier.get());
                    return apply;
                }
            };
        }

        public static IOSupplier $default$compose(final IOFunction _this, final Supplier supplier) {
            Objects.requireNonNull(supplier);
            return new IOSupplier() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda7
                @Override // org.apache.commons.io.function.IOSupplier
                public final Object get() {
                    Object apply;
                    apply = IOFunction.this.apply(supplier.get());
                    return apply;
                }
            };
        }

        public static IOFunction $default$andThen(final IOFunction _this, final IOFunction iOFunction) {
            Objects.requireNonNull(iOFunction);
            return new IOFunction() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda4
                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOConsumer andThen(Consumer consumer) {
                    return IOFunction.CC.$default$andThen(this, consumer);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOConsumer andThen(IOConsumer iOConsumer) {
                    return IOFunction.CC.$default$andThen(this, iOConsumer);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction andThen(Function function) {
                    return IOFunction.CC.$default$andThen(this, function);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction andThen(IOFunction iOFunction2) {
                    return IOFunction.CC.$default$andThen(this, iOFunction2);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public final Object apply(Object obj) {
                    Object apply;
                    apply = iOFunction.apply(IOFunction.this.apply(obj));
                    return apply;
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction compose(Function function) {
                    return IOFunction.CC.$default$compose(this, function);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction compose(IOFunction iOFunction2) {
                    return IOFunction.CC.$default$compose(this, iOFunction2);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOSupplier compose(Supplier supplier) {
                    return IOFunction.CC.$default$compose(this, supplier);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOSupplier compose(IOSupplier iOSupplier) {
                    return IOFunction.CC.$default$compose(this, iOSupplier);
                }
            };
        }

        public static IOFunction $default$andThen(final IOFunction _this, final Function function) {
            Objects.requireNonNull(function);
            return new IOFunction() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda2
                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOConsumer andThen(Consumer consumer) {
                    return IOFunction.CC.$default$andThen(this, consumer);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOConsumer andThen(IOConsumer iOConsumer) {
                    return IOFunction.CC.$default$andThen(this, iOConsumer);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction andThen(Function function2) {
                    return IOFunction.CC.$default$andThen(this, function2);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction andThen(IOFunction iOFunction) {
                    return IOFunction.CC.$default$andThen(this, iOFunction);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public final Object apply(Object obj) {
                    Object apply;
                    apply = function.apply(IOFunction.this.apply(obj));
                    return apply;
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction compose(Function function2) {
                    return IOFunction.CC.$default$compose(this, function2);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction compose(IOFunction iOFunction) {
                    return IOFunction.CC.$default$compose(this, iOFunction);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOSupplier compose(Supplier supplier) {
                    return IOFunction.CC.$default$compose(this, supplier);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOSupplier compose(IOSupplier iOSupplier) {
                    return IOFunction.CC.$default$compose(this, iOSupplier);
                }
            };
        }

        public static IOConsumer $default$andThen(final IOFunction _this, final IOConsumer iOConsumer) {
            Objects.requireNonNull(iOConsumer);
            return new IOConsumer() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda1
                @Override // org.apache.commons.io.function.IOConsumer
                public final void accept(Object obj) {
                    iOConsumer.accept(IOFunction.this.apply(obj));
                }

                @Override // org.apache.commons.io.function.IOConsumer
                public /* synthetic */ IOConsumer andThen(IOConsumer iOConsumer2) {
                    return IOConsumer.CC.$default$andThen(this, iOConsumer2);
                }
            };
        }

        public static IOConsumer $default$andThen(final IOFunction _this, final Consumer consumer) {
            Objects.requireNonNull(consumer);
            return new IOConsumer() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda0
                @Override // org.apache.commons.io.function.IOConsumer
                public final void accept(Object obj) {
                    consumer.accept(IOFunction.this.apply(obj));
                }

                @Override // org.apache.commons.io.function.IOConsumer
                public /* synthetic */ IOConsumer andThen(IOConsumer iOConsumer) {
                    return IOConsumer.CC.$default$andThen(this, iOConsumer);
                }
            };
        }

        public static <T> IOFunction<T, T> identity() {
            return new IOFunction() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda6
                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOConsumer andThen(Consumer consumer) {
                    return IOFunction.CC.$default$andThen(this, consumer);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOConsumer andThen(IOConsumer iOConsumer) {
                    return IOFunction.CC.$default$andThen(this, iOConsumer);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction andThen(Function function) {
                    return IOFunction.CC.$default$andThen(this, function);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction andThen(IOFunction iOFunction) {
                    return IOFunction.CC.$default$andThen(this, iOFunction);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public final Object apply(Object obj) {
                    return IOFunction.CC.lambda$identity$8(obj);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction compose(Function function) {
                    return IOFunction.CC.$default$compose(this, function);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOFunction compose(IOFunction iOFunction) {
                    return IOFunction.CC.$default$compose(this, iOFunction);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOSupplier compose(Supplier supplier) {
                    return IOFunction.CC.$default$compose(this, supplier);
                }

                @Override // org.apache.commons.io.function.IOFunction
                public /* synthetic */ IOSupplier compose(IOSupplier iOSupplier) {
                    return IOFunction.CC.$default$compose(this, iOSupplier);
                }
            };
        }
    }
}
