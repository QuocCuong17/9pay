package com.pichillilorenzo.flutter_inappwebview_android.types;

import io.flutter.plugin.common.MethodChannel;

/* loaded from: classes5.dex */
public interface ICallbackResult<T> extends MethodChannel.Result {
    T decodeResult(Object obj);

    void defaultBehaviour(T t);

    boolean nonNullSuccess(T t);

    boolean nullSuccess();
}
