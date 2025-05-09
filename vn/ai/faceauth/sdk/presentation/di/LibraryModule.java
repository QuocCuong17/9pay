package vn.ai.faceauth.sdk.presentation.di;

import android.app.Application;
import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0012"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/di/LibraryModule;", "", "()V", MimeTypes.BASE_TYPE_APPLICATION, "Landroid/app/Application;", "getApplication", "()Landroid/app/Application;", "setApplication", "(Landroid/app/Application;)V", TtmlNode.RUBY_CONTAINER, "Lvn/ai/faceauth/sdk/presentation/di/LivenessCameraXContainer;", "getContainer", "()Lvn/ai/faceauth/sdk/presentation/di/LivenessCameraXContainer;", "container$delegate", "Lkotlin/Lazy;", "initializeDI", "", "newApplication", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class LibraryModule {
    public static volatile Application application;
    public static final LibraryModule INSTANCE = new LibraryModule();

    /* renamed from: container$delegate, reason: from kotlin metadata */
    private static final Lazy container = LazyKt.lazy(new Function0<LivenessCameraXContainer>() { // from class: vn.ai.faceauth.sdk.presentation.di.LibraryModule$container$2
        @Override // kotlin.jvm.functions.Function0
        public final LivenessCameraXContainer invoke() {
            return new LivenessCameraXContainer(LibraryModule.INSTANCE.getApplication());
        }
    });

    private LibraryModule() {
    }

    public final Application getApplication() {
        Application application2 = application;
        if (application2 != null) {
            return application2;
        }
        Intrinsics.throwUninitializedPropertyAccessException(tfwhgw.rnigpa(51));
        return null;
    }

    public final LivenessCameraXContainer getContainer() {
        return (LivenessCameraXContainer) container.getValue();
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
}
