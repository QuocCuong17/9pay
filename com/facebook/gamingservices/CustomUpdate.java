package com.facebook.gamingservices;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.gamingservices.model.CustomUpdateContent;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomUpdate.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007¨\u0006\t"}, d2 = {"Lcom/facebook/gamingservices/CustomUpdate;", "", "()V", "newCustomUpdateRequest", "Lcom/facebook/GraphRequest;", FirebaseAnalytics.Param.CONTENT, "Lcom/facebook/gamingservices/model/CustomUpdateContent;", "callback", "Lcom/facebook/GraphRequest$Callback;", "facebook-gamingservices_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CustomUpdate {
    public static final CustomUpdate INSTANCE = new CustomUpdate();

    private CustomUpdate() {
    }

    @JvmStatic
    public static final GraphRequest newCustomUpdateRequest(CustomUpdateContent content, GraphRequest.Callback callback) {
        Intrinsics.checkNotNullParameter(content, "content");
        return GraphRequest.INSTANCE.newPostRequest(AccessToken.INSTANCE.getCurrentAccessToken(), "me/custom_update", content.toGraphRequestContent(), callback);
    }
}
