package com.google.firebase.firestore.remote;

import com.google.firebase.FirebaseOptions;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.inject.Provider;
import com.google.firebase.platforminfo.UserAgentPublisher;
import io.grpc.Metadata;

/* loaded from: classes4.dex */
public class FirebaseClientGrpcMetadataProvider implements GrpcMetadataProvider {
    private static final String HEART_BEAT_TAG = "fire-fst";
    private final FirebaseOptions firebaseOptions;
    private final Provider<HeartBeatInfo> heartBeatInfoProvider;
    private final Provider<UserAgentPublisher> userAgentPublisherProvider;
    private static final Metadata.Key<String> HEART_BEAT_HEADER = Metadata.Key.of("x-firebase-client-log-type", Metadata.ASCII_STRING_MARSHALLER);
    private static final Metadata.Key<String> USER_AGENT_HEADER = Metadata.Key.of("x-firebase-client", Metadata.ASCII_STRING_MARSHALLER);
    private static final Metadata.Key<String> GMP_APP_ID_HEADER = Metadata.Key.of("x-firebase-gmpid", Metadata.ASCII_STRING_MARSHALLER);

    public FirebaseClientGrpcMetadataProvider(Provider<UserAgentPublisher> provider, Provider<HeartBeatInfo> provider2, FirebaseOptions firebaseOptions) {
        this.userAgentPublisherProvider = provider;
        this.heartBeatInfoProvider = provider2;
        this.firebaseOptions = firebaseOptions;
    }

    @Override // com.google.firebase.firestore.remote.GrpcMetadataProvider
    public void updateMetadata(Metadata metadata) {
        if (this.heartBeatInfoProvider.get() == null || this.userAgentPublisherProvider.get() == null) {
            return;
        }
        int code = this.heartBeatInfoProvider.get().getHeartBeatCode(HEART_BEAT_TAG).getCode();
        if (code != 0) {
            metadata.put(HEART_BEAT_HEADER, Integer.toString(code));
        }
        metadata.put(USER_AGENT_HEADER, this.userAgentPublisherProvider.get().getUserAgent());
        maybeAddGmpAppId(metadata);
    }

    private void maybeAddGmpAppId(Metadata metadata) {
        FirebaseOptions firebaseOptions = this.firebaseOptions;
        if (firebaseOptions == null) {
            return;
        }
        String applicationId = firebaseOptions.getApplicationId();
        if (applicationId.length() != 0) {
            metadata.put(GMP_APP_ID_HEADER, applicationId);
        }
    }
}
