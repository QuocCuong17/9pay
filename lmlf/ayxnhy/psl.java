package lmlf.ayxnhy;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/* loaded from: classes5.dex */
public class psl {
    public static void tshp(Activity activity, String str, String str2) {
        new AlertDialog.Builder(activity).setTitle(str).setMessage(str2).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null).setCancelable(false).show();
    }
}
