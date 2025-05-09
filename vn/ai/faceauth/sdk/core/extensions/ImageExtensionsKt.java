package vn.ai.faceauth.sdk.core.extensions;

import android.media.Image;
import android.view.View;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t\u001a\f\u0010\n\u001a\u00020\u0003*\u0004\u0018\u00010\u000b\u001a\n\u0010\f\u001a\u00020\r*\u00020\u000e\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"INTEGER_255", "", "LUMINOSITY_ZERO", "", "PLANE_Y", "setColorFilter", "", "color", ViewHierarchyConstants.VIEW_KEY, "Landroid/view/View;", "getLuminosity", "Landroid/media/Image;", "toByteArray", "", "Ljava/nio/ByteBuffer;", "trueface_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ImageExtensionsKt {
    private static int INTEGER_255;
    private static final float LUMINOSITY_ZERO = 0.0f;
    private static final int PLANE_Y = 0;

    static {
        tfwhgw.rnl(ImageExtensionsKt.class, 511, 511);
    }

    public static final float getLuminosity(Image image) {
        if (image == null) {
            return 0.0f;
        }
        byte[] byteArray = toByteArray(image.getPlanes()[0].getBuffer());
        ArrayList arrayList = new ArrayList(byteArray.length);
        for (byte b : byteArray) {
            arrayList.add(Integer.valueOf(b & 255));
        }
        return (float) CollectionsKt.averageOfInt(arrayList);
    }

    public static final void setColorFilter(int i, View view) {
        view.getBackground().setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(i, BlendModeCompat.SRC_ATOP));
    }

    public static final byte[] toByteArray(ByteBuffer byteBuffer) {
        byteBuffer.rewind();
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return bArr;
    }
}
