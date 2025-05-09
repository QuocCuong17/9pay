package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.util.concurrent.CountDownLatch;

/* loaded from: classes5.dex */
public class SyncBaseCallbackResultImpl<T> extends BaseCallbackResultImpl<T> {
    public final CountDownLatch latch = new CountDownLatch(1);
    public T result = null;

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
    public void defaultBehaviour(T t) {
        this.latch.countDown();
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
    public void success(Object obj) {
        boolean nonNullSuccess;
        T decodeResult = decodeResult(obj);
        this.result = decodeResult;
        if (decodeResult == null) {
            nonNullSuccess = nullSuccess();
        } else {
            nonNullSuccess = nonNullSuccess(decodeResult);
        }
        if (nonNullSuccess) {
            defaultBehaviour(decodeResult);
        } else {
            this.latch.countDown();
        }
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
    public void error(String str, String str2, Object obj) {
        this.latch.countDown();
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
    public void notImplemented() {
        defaultBehaviour(null);
    }
}
