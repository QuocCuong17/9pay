package com.facebook.messenger;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.common.net.HttpHeaders;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MessengerThreadParams.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\n\u0018\u00002\u00020\u0001:\u0001\u0011B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/facebook/messenger/MessengerThreadParams;", "", "origin", "Lcom/facebook/messenger/MessengerThreadParams$Origin;", "threadToken", "", TtmlNode.TAG_METADATA, "participants", "", "(Lcom/facebook/messenger/MessengerThreadParams$Origin;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getMetadata", "()Ljava/lang/String;", "getOrigin", "()Lcom/facebook/messenger/MessengerThreadParams$Origin;", "getParticipants", "()Ljava/util/List;", "getThreadToken", HttpHeaders.ORIGIN, "facebook-messenger_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MessengerThreadParams {
    private final String metadata;
    private final Origin origin;
    private final List<String> participants;
    private final String threadToken;

    /* compiled from: MessengerThreadParams.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/facebook/messenger/MessengerThreadParams$Origin;", "", "(Ljava/lang/String;I)V", "REPLY_FLOW", "COMPOSE_FLOW", "UNKNOWN", "facebook-messenger_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public enum Origin {
        REPLY_FLOW,
        COMPOSE_FLOW,
        UNKNOWN
    }

    public MessengerThreadParams(Origin origin, String threadToken, String metadata, List<String> participants) {
        Intrinsics.checkNotNullParameter(origin, "origin");
        Intrinsics.checkNotNullParameter(threadToken, "threadToken");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(participants, "participants");
        this.origin = origin;
        this.threadToken = threadToken;
        this.metadata = metadata;
        this.participants = participants;
    }

    public final Origin getOrigin() {
        return this.origin;
    }

    public final String getThreadToken() {
        return this.threadToken;
    }

    public final String getMetadata() {
        return this.metadata;
    }

    public final List<String> getParticipants() {
        return this.participants;
    }
}
