package lmlf.ayxnhy;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.text.Html;

/* loaded from: classes5.dex */
public class trodh implements Html.ImageGetter {
    private final Activity activity;

    public trodh(Activity activity) {
        this.activity = activity;
    }

    @Override // android.text.Html.ImageGetter
    public Drawable getDrawable(String str) {
        try {
            Drawable applicationIcon = this.activity.getPackageManager().getApplicationIcon(str);
            applicationIcon.setBounds(0, 0, 70, 70);
            return applicationIcon;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }
}
