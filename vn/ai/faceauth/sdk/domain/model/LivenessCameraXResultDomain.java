package vn.ai.faceauth.sdk.domain.model;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\b\u0016\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005BG\u0012\u001c\b\u0002\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\t\u0012\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u001d\u0010\u0015\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\tHÆ\u0003J\u0017\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000bHÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\rHÆ\u0003JK\u0010\u0018\u001a\u00020\u00002\u001c\b\u0002\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\t2\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\bHÖ\u0001R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R%\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001f\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001f"}, d2 = {"Lvn/ai/faceauth/sdk/domain/model/LivenessCameraXResultDomain;", "", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/Exception;)V", "rawImage", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "response", "", "error", "Lvn/ai/faceauth/sdk/domain/model/LivenessCameraXErrorDomain;", "(Ljava/util/ArrayList;Ljava/util/Map;Lvn/ai/faceauth/sdk/domain/model/LivenessCameraXErrorDomain;)V", "getError", "()Lvn/ai/faceauth/sdk/domain/model/LivenessCameraXErrorDomain;", "getRawImage", "()Ljava/util/ArrayList;", "getResponse", "()Ljava/util/Map;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class LivenessCameraXResultDomain {
    private final LivenessCameraXErrorDomain error;
    private final ArrayList<String> rawImage;
    private final Map<String, Object> response;

    public LivenessCameraXResultDomain() {
        this(null, null, null, 7, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public LivenessCameraXResultDomain(Exception exc) {
        this(null, null, new LivenessCameraXErrorDomain(r0 == null ? "" : r0, String.valueOf(exc.getCause()), exc), 3, null);
        String message = exc.getMessage();
    }

    public LivenessCameraXResultDomain(ArrayList<String> arrayList, Map<String, ? extends Object> map, LivenessCameraXErrorDomain livenessCameraXErrorDomain) {
        this.rawImage = arrayList;
        this.response = map;
        this.error = livenessCameraXErrorDomain;
    }

    public /* synthetic */ LivenessCameraXResultDomain(ArrayList arrayList, Map map, LivenessCameraXErrorDomain livenessCameraXErrorDomain, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : arrayList, (i & 2) != 0 ? null : map, (i & 4) != 0 ? null : livenessCameraXErrorDomain);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LivenessCameraXResultDomain copy$default(LivenessCameraXResultDomain livenessCameraXResultDomain, ArrayList arrayList, Map map, LivenessCameraXErrorDomain livenessCameraXErrorDomain, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = livenessCameraXResultDomain.rawImage;
        }
        if ((i & 2) != 0) {
            map = livenessCameraXResultDomain.response;
        }
        if ((i & 4) != 0) {
            livenessCameraXErrorDomain = livenessCameraXResultDomain.error;
        }
        return livenessCameraXResultDomain.copy(arrayList, map, livenessCameraXErrorDomain);
    }

    public final ArrayList<String> component1() {
        return this.rawImage;
    }

    public final Map<String, Object> component2() {
        return this.response;
    }

    /* renamed from: component3, reason: from getter */
    public final LivenessCameraXErrorDomain getError() {
        return this.error;
    }

    public final LivenessCameraXResultDomain copy(ArrayList<String> rawImage, Map<String, ? extends Object> response, LivenessCameraXErrorDomain error) {
        return new LivenessCameraXResultDomain(rawImage, response, error);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LivenessCameraXResultDomain)) {
            return false;
        }
        LivenessCameraXResultDomain livenessCameraXResultDomain = (LivenessCameraXResultDomain) other;
        return Intrinsics.areEqual(this.rawImage, livenessCameraXResultDomain.rawImage) && Intrinsics.areEqual(this.response, livenessCameraXResultDomain.response) && Intrinsics.areEqual(this.error, livenessCameraXResultDomain.error);
    }

    public final LivenessCameraXErrorDomain getError() {
        return this.error;
    }

    public final ArrayList<String> getRawImage() {
        return this.rawImage;
    }

    public final Map<String, Object> getResponse() {
        return this.response;
    }

    public int hashCode() {
        ArrayList<String> arrayList = this.rawImage;
        int hashCode = arrayList == null ? 0 : arrayList.hashCode();
        Map<String, Object> map = this.response;
        int hashCode2 = map == null ? 0 : map.hashCode();
        LivenessCameraXErrorDomain livenessCameraXErrorDomain = this.error;
        return (((hashCode * 31) + hashCode2) * 31) + (livenessCameraXErrorDomain != null ? livenessCameraXErrorDomain.hashCode() : 0);
    }

    public String toString() {
        return tfwhgw.rnigpa(316) + this.rawImage + tfwhgw.rnigpa(317) + this.response + tfwhgw.rnigpa(318) + this.error + ')';
    }
}
