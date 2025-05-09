package com.facebook.appevents;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.internal.Utility;
import java.io.ObjectStreamException;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AccessTokenAppIdPair.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0002\u0013\u0014B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0002R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/facebook/appevents/AccessTokenAppIdPair;", "Ljava/io/Serializable;", "accessToken", "Lcom/facebook/AccessToken;", "(Lcom/facebook/AccessToken;)V", "accessTokenString", "", "applicationId", "(Ljava/lang/String;Ljava/lang/String;)V", "getAccessTokenString", "()Ljava/lang/String;", "getApplicationId", "equals", "", "o", "", "hashCode", "", "writeReplace", "Companion", "SerializationProxyV1", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AccessTokenAppIdPair implements Serializable {
    private static final long serialVersionUID = 1;
    private final String accessTokenString;
    private final String applicationId;

    public AccessTokenAppIdPair(String str, String applicationId) {
        Intrinsics.checkNotNullParameter(applicationId, "applicationId");
        this.applicationId = applicationId;
        this.accessTokenString = Utility.isNullOrEmpty(str) ? null : str;
    }

    public final String getApplicationId() {
        return this.applicationId;
    }

    public final String getAccessTokenString() {
        return this.accessTokenString;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AccessTokenAppIdPair(AccessToken accessToken) {
        this(accessToken.getToken(), FacebookSdk.getApplicationId());
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
    }

    public int hashCode() {
        String str = this.accessTokenString;
        return (str != null ? str.hashCode() : 0) ^ this.applicationId.hashCode();
    }

    public boolean equals(Object o) {
        if (!(o instanceof AccessTokenAppIdPair)) {
            return false;
        }
        AccessTokenAppIdPair accessTokenAppIdPair = (AccessTokenAppIdPair) o;
        return Utility.areObjectsEqual(accessTokenAppIdPair.accessTokenString, this.accessTokenString) && Utility.areObjectsEqual(accessTokenAppIdPair.applicationId, this.applicationId);
    }

    /* compiled from: AccessTokenAppIdPair.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/facebook/appevents/AccessTokenAppIdPair$SerializationProxyV1;", "Ljava/io/Serializable;", "accessTokenString", "", "appId", "(Ljava/lang/String;Ljava/lang/String;)V", "readResolve", "", "Companion", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class SerializationProxyV1 implements Serializable {
        private static final long serialVersionUID = -2488473066578201069L;
        private final String accessTokenString;
        private final String appId;

        public SerializationProxyV1(String str, String appId) {
            Intrinsics.checkNotNullParameter(appId, "appId");
            this.accessTokenString = str;
            this.appId = appId;
        }

        private final Object readResolve() throws ObjectStreamException {
            return new AccessTokenAppIdPair(this.accessTokenString, this.appId);
        }
    }

    private final Object writeReplace() throws ObjectStreamException {
        return new SerializationProxyV1(this.accessTokenString, this.applicationId);
    }
}
