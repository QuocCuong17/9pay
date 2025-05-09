package androidx.media3.common.text;

import android.os.Bundle;
import com.google.common.base.Function;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class CueGroup$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ CueGroup$$ExternalSyntheticLambda0 INSTANCE = new CueGroup$$ExternalSyntheticLambda0();

    private /* synthetic */ CueGroup$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        return Cue.fromBundle((Bundle) obj);
    }
}
