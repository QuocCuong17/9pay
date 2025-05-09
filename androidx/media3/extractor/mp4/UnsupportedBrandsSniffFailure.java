package androidx.media3.extractor.mp4;

import androidx.media3.extractor.SniffFailure;
import com.google.common.primitives.ImmutableIntArray;

/* loaded from: classes.dex */
public final class UnsupportedBrandsSniffFailure implements SniffFailure {
    public final ImmutableIntArray compatibleBrands;
    public final int majorBrand;

    public UnsupportedBrandsSniffFailure(int i, int[] iArr) {
        ImmutableIntArray of;
        this.majorBrand = i;
        if (iArr != null) {
            of = ImmutableIntArray.copyOf(iArr);
        } else {
            of = ImmutableIntArray.of();
        }
        this.compatibleBrands = of;
    }
}
