package vn.ai.faceauth.sdk.camera.core.callback;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import vn.ai.faceauth.sdk.core.factory.Factory;
import vn.ai.faceauth.sdk.domain.model.PhotoResultDomain;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0017\u001a\u00020\u0002H\u0016R*\u0010\u0004\u001a\u0012\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR(\u0010\r\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0004\u0012\u00020\b0\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Lvn/ai/faceauth/sdk/camera/core/callback/CameraXCallbackFactory;", "Lvn/ai/faceauth/sdk/core/factory/Factory;", "Lvn/ai/faceauth/sdk/camera/core/callback/CameraXCallback;", "()V", "onErrorAction", "Lkotlin/Function1;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "", "getOnErrorAction", "()Lkotlin/jvm/functions/Function1;", "setOnErrorAction", "(Lkotlin/jvm/functions/Function1;)V", "onImageSavedAction", "Lvn/ai/faceauth/sdk/domain/model/PhotoResultDomain;", "getOnImageSavedAction", "setOnImageSavedAction", "onSuccessFinished", "Lkotlin/Function0;", "getOnSuccessFinished", "()Lkotlin/jvm/functions/Function0;", "setOnSuccessFinished", "(Lkotlin/jvm/functions/Function0;)V", "create", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class CameraXCallbackFactory implements Factory<CameraXCallback> {
    public static final CameraXCallbackFactory INSTANCE = new CameraXCallbackFactory();
    private static Function1<? super PhotoResultDomain, Unit> onImageSavedAction = new Function1<PhotoResultDomain, Unit>() { // from class: vn.ai.faceauth.sdk.camera.core.callback.CameraXCallbackFactory$onImageSavedAction$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(PhotoResultDomain photoResultDomain) {
            invoke2(photoResultDomain);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(PhotoResultDomain photoResultDomain) {
        }
    };
    private static Function1<? super Exception, Unit> onErrorAction = new Function1<Exception, Unit>() { // from class: vn.ai.faceauth.sdk.camera.core.callback.CameraXCallbackFactory$onErrorAction$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
            invoke2(exc);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Exception exc) {
        }
    };
    private static Function0<Unit> onSuccessFinished = new Function0<Unit>() { // from class: vn.ai.faceauth.sdk.camera.core.callback.CameraXCallbackFactory$onSuccessFinished$1
        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
        }
    };

    private CameraXCallbackFactory() {
    }

    @Override // vn.ai.faceauth.sdk.core.factory.Factory
    public CameraXCallback create() {
        return new CameraXCallbackImpl(onImageSavedAction, onErrorAction, onSuccessFinished, null, 8, null);
    }

    public final Function1<Exception, Unit> getOnErrorAction() {
        return onErrorAction;
    }

    public final Function1<PhotoResultDomain, Unit> getOnImageSavedAction() {
        return onImageSavedAction;
    }

    public final Function0<Unit> getOnSuccessFinished() {
        return onSuccessFinished;
    }

    public final void setOnErrorAction(Function1<? super Exception, Unit> function1) {
        onErrorAction = function1;
    }

    public final void setOnImageSavedAction(Function1<? super PhotoResultDomain, Unit> function1) {
        onImageSavedAction = function1;
    }

    public final void setOnSuccessFinished(Function0<Unit> function0) {
        onSuccessFinished = function0;
    }
}
