package com.pichillilorenzo.flutter_inappwebview_android.types;

/* loaded from: classes5.dex */
public class ServerTrustChallenge extends URLAuthenticationChallenge {
    public ServerTrustChallenge(URLProtectionSpace uRLProtectionSpace) {
        super(uRLProtectionSpace);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.URLAuthenticationChallenge
    public String toString() {
        return "ServerTrustChallenge{} " + super.toString();
    }
}
