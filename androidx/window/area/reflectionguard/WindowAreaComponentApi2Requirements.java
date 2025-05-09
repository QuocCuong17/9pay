package androidx.window.area.reflectionguard;

import android.app.Activity;
import androidx.window.extensions.core.util.function.Consumer;

/* loaded from: classes2.dex */
public interface WindowAreaComponentApi2Requirements {
    void addRearDisplayStatusListener(Consumer<Integer> consumer);

    void endRearDisplaySession();

    void removeRearDisplayStatusListener(Consumer<Integer> consumer);

    void startRearDisplaySession(Activity activity, Consumer<Integer> consumer);
}
