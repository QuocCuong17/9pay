package lmlf.ayxnhy;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

/* loaded from: classes5.dex */
public class zpy extends ContentObserver {
    public zpy(Handler handler) {
        super(handler);
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri, int i) {
        tfwhgw.cimidz();
    }
}
