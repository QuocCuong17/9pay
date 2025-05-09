package vn.ai.faceauth.sdk.core.extensions;

import android.app.Activity;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import vn.ai.faceauth.sdk.domain.model.PermissionsType;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a,\u0010\u0005\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\f\u001a\u0012\u0010\r\u001a\u00020\u000e*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0004¨\u0006\u0010"}, d2 = {"getPermissionStatus", "Lvn/ai/faceauth/sdk/domain/model/PermissionsType;", "Landroid/app/Activity;", "androidPermissionName", "", "observeOnce", "", "T", "Landroidx/lifecycle/LiveData;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "observer", "Landroidx/lifecycle/Observer;", "shouldShowRequest", "", "permission", "trueface_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ActivityExtensionsKt {

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PermissionsType.values().length];
            iArr[PermissionsType.GRANTED.ordinal()] = 1;
            iArr[PermissionsType.DENIED.ordinal()] = 2;
            iArr[PermissionsType.BLOCKED_OR_NEVER_ASKED.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final PermissionsType getPermissionStatus(Activity activity, String str) {
        return ContextCompat.checkSelfPermission(activity, str) != 0 ? !ActivityCompat.shouldShowRequestPermissionRationale(activity, str) ? PermissionsType.BLOCKED_OR_NEVER_ASKED : PermissionsType.DENIED : PermissionsType.GRANTED;
    }

    public static final <T> void observeOnce(LiveData<T> liveData, LifecycleOwner lifecycleOwner, final Observer<T> observer) {
        liveData.observe(lifecycleOwner, new Observer<T>() { // from class: vn.ai.faceauth.sdk.core.extensions.ActivityExtensionsKt$observeOnce$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(T t) {
                observer.onChanged(t);
            }
        });
    }

    public static final boolean shouldShowRequest(Activity activity, String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
        int i = WhenMappings.$EnumSwitchMapping$0[getPermissionStatus(activity, str).ordinal()];
        if (i == 1) {
            return false;
        }
        if (i == 2 || i == 3) {
            return true;
        }
        throw new NoWhenBranchMatchedException();
    }
}
