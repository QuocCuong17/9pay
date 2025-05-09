package co.hyperverge.hyperkyc.utils.extensions;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ListExts.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\u001a$\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003¨\u0006\u0005"}, d2 = {"compareListsAsString", "", "T", "", "updatedList", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ListExtsKt {
    public static final <T> boolean compareListsAsString(List<? extends T> list, List<? extends T> updatedList) {
        boolean z;
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(updatedList, "updatedList");
        if (list.size() != updatedList.size()) {
            return false;
        }
        List<Pair> zip = CollectionsKt.zip(list, updatedList);
        if (!(zip instanceof Collection) || !zip.isEmpty()) {
            for (Pair pair : zip) {
                if (!Intrinsics.areEqual(String.valueOf(pair.component1()), String.valueOf(pair.component2()))) {
                    z = false;
                    break;
                }
            }
        }
        z = true;
        return z;
    }
}
