package com.otaliastudios.transcoder.internal.pipeline;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: State.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0004\u0004\u0005\u0006\u0007B\u0007\b\u0004¢\u0006\u0002\u0010\u0003\u0082\u0001\u0003\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/otaliastudios/transcoder/internal/pipeline/State;", "T", "", "()V", "Eos", "Ok", "Retry", "Wait", "Lcom/otaliastudios/transcoder/internal/pipeline/State$Ok;", "Lcom/otaliastudios/transcoder/internal/pipeline/State$Wait;", "Lcom/otaliastudios/transcoder/internal/pipeline/State$Retry;", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public abstract class State<T> {
    public /* synthetic */ State(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private State() {
    }

    /* compiled from: State.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/otaliastudios/transcoder/internal/pipeline/State$Ok;", "T", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "value", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", InAppPurchaseConstants.METHOD_TO_STRING, "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static class Ok<T> extends State<T> {
        private final T value;

        public Ok(T t) {
            super(null);
            this.value = t;
        }

        public final T getValue() {
            return this.value;
        }

        public String toString() {
            return "State.Ok(" + this.value + ')';
        }
    }

    /* compiled from: State.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/otaliastudios/transcoder/internal/pipeline/State$Eos;", "T", "Lcom/otaliastudios/transcoder/internal/pipeline/State$Ok;", "value", "(Ljava/lang/Object;)V", InAppPurchaseConstants.METHOD_TO_STRING, "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Eos<T> extends Ok<T> {
        public Eos(T t) {
            super(t);
        }

        @Override // com.otaliastudios.transcoder.internal.pipeline.State.Ok
        public String toString() {
            return "State.Eos(" + getValue() + ')';
        }
    }

    /* compiled from: State.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/otaliastudios/transcoder/internal/pipeline/State$Wait;", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "", "()V", InAppPurchaseConstants.METHOD_TO_STRING, "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Wait extends State {
        public static final Wait INSTANCE = new Wait();

        public String toString() {
            return "State.Wait";
        }

        private Wait() {
            super(null);
        }
    }

    /* compiled from: State.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/otaliastudios/transcoder/internal/pipeline/State$Retry;", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "", "()V", InAppPurchaseConstants.METHOD_TO_STRING, "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Retry extends State {
        public static final Retry INSTANCE = new Retry();

        public String toString() {
            return "State.Retry";
        }

        private Retry() {
            super(null);
        }
    }
}
