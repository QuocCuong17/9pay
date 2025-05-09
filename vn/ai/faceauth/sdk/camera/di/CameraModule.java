package vn.ai.faceauth.sdk.camera.di;

import android.app.Application;
import androidx.lifecycle.LifecycleOwner;
import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;
import org.bouncycastle.crypto.tls.CipherSuite;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u000f\u001a\u00020\u0010X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0018"}, d2 = {"Lvn/ai/faceauth/sdk/camera/di/CameraModule;", "", "()V", MimeTypes.BASE_TYPE_APPLICATION, "Landroid/app/Application;", "getApplication", "()Landroid/app/Application;", "setApplication", "(Landroid/app/Application;)V", TtmlNode.RUBY_CONTAINER, "Lvn/ai/faceauth/sdk/camera/di/CameraContainer;", "getContainer$trueface_release", "()Lvn/ai/faceauth/sdk/camera/di/CameraContainer;", "container$delegate", "Lkotlin/Lazy;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "getLifecycleOwner$trueface_release", "()Landroidx/lifecycle/LifecycleOwner;", "setLifecycleOwner$trueface_release", "(Landroidx/lifecycle/LifecycleOwner;)V", "initializeDI", "", "newApplication", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class CameraModule {
    public static volatile Application application;
    public static LifecycleOwner lifecycleOwner;
    public static final CameraModule INSTANCE = new CameraModule();

    /* renamed from: container$delegate, reason: from kotlin metadata */
    private static final Lazy container = LazyKt.lazy(new Function0<CameraContainer>() { // from class: vn.ai.faceauth.sdk.camera.di.CameraModule$container$2
        @Override // kotlin.jvm.functions.Function0
        public final CameraContainer invoke() {
            return new CameraContainer();
        }
    });

    private CameraModule() {
    }

    public final Application getApplication() {
        Application application2 = application;
        if (application2 != null) {
            return application2;
        }
        Intrinsics.throwUninitializedPropertyAccessException(tfwhgw.rnigpa(153));
        return null;
    }

    public final CameraContainer getContainer$trueface_release() {
        return (CameraContainer) container.getValue();
    }

    public final LifecycleOwner getLifecycleOwner$trueface_release() {
        LifecycleOwner lifecycleOwner2 = lifecycleOwner;
        if (lifecycleOwner2 != null) {
            return lifecycleOwner2;
        }
        Intrinsics.throwUninitializedPropertyAccessException(tfwhgw.rnigpa(CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA));
        return null;
    }

    public final void initializeDI(Application newApplication) {
        if (application == null) {
            synchronized (this) {
                INSTANCE.setApplication(newApplication);
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final void setApplication(Application application2) {
        application = application2;
    }

    public final void setLifecycleOwner$trueface_release(LifecycleOwner lifecycleOwner2) {
        lifecycleOwner = lifecycleOwner2;
    }
}
