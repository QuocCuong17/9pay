package lmlf.ayxnhy;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import java.util.Locale;

/* loaded from: classes5.dex */
public class cjnqnw implements Application.ActivityLifecycleCallbacks {
    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        tfwhgw.cjj(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        if (tfwhgw.zxehne()) {
            activity.finish();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        tfwhgw.cimidz();
        if (!tfwhgw.zxehne() || tfwhgw.jkpkoz() == null) {
            return;
        }
        TextView textView = (TextView) new AlertDialog.Builder(activity).setMessage(Html.fromHtml(tfwhgw.jkpkoz(), new trodh(activity), null)).setPositiveButton(tfwhgw.rnigpa(Locale.getDefault().getLanguage() == tfwhgw.rnigpa(304) ? 305 : 306), new eufab(activity)).setCancelable(false).show().findViewById(R.id.message);
        if (textView != null) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }
}
