package androidx.media3.common;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda23 implements ListenerSet.Event {
    public static final /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda23 INSTANCE = new SimpleBasePlayer$$ExternalSyntheticLambda23();

    private /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda23() {
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        ((Player.Listener) obj).onRenderedFirstFrame();
    }
}
