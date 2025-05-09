package co.hyperverge.hypersnapsdk.utils;

/* loaded from: classes2.dex */
public interface BaseView<T> {
    T getPresenter();

    void onBackPress();

    void setPresenter(T t);
}
