package com.google.android.gms.internal.vision;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
public final class zzu {
    public static boolean zza(Context context, String str) {
        return DynamiteModule.getLocalVersion(context, str) > DynamiteModule.getRemoteVersion(context, OptionalModuleUtils.DEPRECATED_DYNAMITE_MODULE_ID);
    }
}
