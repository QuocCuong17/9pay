package vn.ai.faceauth.sdk.core.resourceprovider;

import android.content.Context;
import kotlin.Metadata;
import vn.ai.faceauth.sdk.core.factory.Factory;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lvn/ai/faceauth/sdk/core/resourceprovider/ResourcesProviderFactory;", "Lvn/ai/faceauth/sdk/core/factory/Factory;", "Lvn/ai/faceauth/sdk/core/resourceprovider/ResourcesProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "create", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ResourcesProviderFactory implements Factory<ResourcesProvider> {
    private final Context context;

    public ResourcesProviderFactory(Context context) {
        this.context = context;
    }

    @Override // vn.ai.faceauth.sdk.core.factory.Factory
    public ResourcesProvider create() {
        return new ResourcesProviderImpl(this.context);
    }
}
