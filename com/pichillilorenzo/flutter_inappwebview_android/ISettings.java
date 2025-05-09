package com.pichillilorenzo.flutter_inappwebview_android;

import java.util.Map;

/* loaded from: classes4.dex */
public interface ISettings<T> {
    Map<String, Object> getRealSettings(T t);

    ISettings<T> parse(Map<String, Object> map);

    Map<String, Object> toMap();
}
