package lmlf.ayxnhy;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

/* loaded from: classes5.dex */
public class eufab implements DialogInterface.OnClickListener {
    private final Activity activity;

    public eufab(Activity activity) {
        this.activity = activity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent(tfwhgw.rnigpa(381));
        intent.addFlags(335544320);
        this.activity.startActivity(intent);
    }
}
