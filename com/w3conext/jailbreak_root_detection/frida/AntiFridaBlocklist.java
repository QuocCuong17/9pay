package com.w3conext.jailbreak_root_detection.frida;

import android.util.Log;
import com.tekartik.sqflite.Constant;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AntiFridaBlocklist.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/w3conext/jailbreak_root_detection/frida/AntiFridaBlocklist;", "", "()V", "TAG", "", "TARGETS", "", "check", "", "mapsFileContent", "checkContain", Constant.PARAM_RESULT, "jailbreak_root_detection_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class AntiFridaBlocklist {
    private static final String TAG = "FridaModuleBlocklist";
    public static final AntiFridaBlocklist INSTANCE = new AntiFridaBlocklist();
    private static final List<String> TARGETS = CollectionsKt.listOf((Object[]) new String[]{"frida-agent", "frida-gadget"});

    private AntiFridaBlocklist() {
    }

    public final boolean check(String mapsFileContent) {
        if (mapsFileContent == null) {
            Log.d(TAG, "maps got null");
            return false;
        }
        Iterator<String> it = TARGETS.iterator();
        while (it.hasNext()) {
            if (StringsKt.contains$default((CharSequence) mapsFileContent, (CharSequence) it.next(), false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public final boolean checkContain(String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        Iterator<String> it = TARGETS.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (StringsKt.contains$default((CharSequence) result, (CharSequence) it.next(), false, 2, (Object) null)) {
                z = true;
            }
        }
        return z;
    }
}
