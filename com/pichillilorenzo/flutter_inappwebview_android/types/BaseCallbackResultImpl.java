package com.pichillilorenzo.flutter_inappwebview_android.types;

/* loaded from: classes5.dex */
public class BaseCallbackResultImpl<T> implements ICallbackResult<T> {
    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
    public T decodeResult(Object obj) {
        return null;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
    public void defaultBehaviour(T t) {
    }

    @Override // io.flutter.plugin.common.MethodChannel.Result
    public void error(String str, String str2, Object obj) {
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
    public boolean nonNullSuccess(T t) {
        return true;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
    public boolean nullSuccess() {
        return true;
    }

    @Override // io.flutter.plugin.common.MethodChannel.Result
    public void success(Object obj) {
        boolean nonNullSuccess;
        T decodeResult = decodeResult(obj);
        if (decodeResult == null) {
            nonNullSuccess = nullSuccess();
        } else {
            nonNullSuccess = nonNullSuccess(decodeResult);
        }
        if (nonNullSuccess) {
            defaultBehaviour(decodeResult);
        }
    }

    @Override // io.flutter.plugin.common.MethodChannel.Result
    public void notImplemented() {
        defaultBehaviour(null);
    }
}
