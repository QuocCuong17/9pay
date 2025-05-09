package vn.ai.faceauth.sdk.opencv;

import android.content.Context;
import android.graphics.Bitmap;
import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.ByteStreamsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.R;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0086 R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001f"}, d2 = {"Lvn/ai/faceauth/sdk/opencv/VFaceLib;", "", "input", "Landroid/graphics/Bitmap;", "(Landroid/graphics/Bitmap;)V", "faceRect", "", "getFaceRect", "()[I", "setFaceRect", "([I)V", "failReason", "", "getFailReason", "()Ljava/lang/String;", "setFailReason", "(Ljava/lang/String;)V", "isSuccessful", "", "()Z", "landmarks", "", "getLandmarks", "()[F", "setLandmarks", "([F)V", "getFaces", SDKConstants.PARAM_CONTEXT_MIN_SIZE, "", SDKConstants.PARAM_CONTEXT_MAX_SIZE, "Companion", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class VFaceLib {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int[] faceRect;
    private String failReason = "";
    private final Bitmap input;
    private float[] landmarks;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0086 J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ\t\u0010\u000e\u001a\u00020\u000fH\u0086 J\u0019\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0082 ¨\u0006\u0014"}, d2 = {"Lvn/ai/faceauth/sdk/opencv/VFaceLib$Companion;", "", "()V", "faceAlignment", "", "resultBitmap", "Landroid/graphics/Bitmap;", "inputBitmap", "landmarks", "", "alignmentPoints", "initialize", "context", "Landroid/content/Context;", "nativeDestroy", "", "nativeInit", "haarcascadePath", "", "lbfmodelPath", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final native boolean nativeInit(String haarcascadePath, String lbfmodelPath);

        public final native boolean faceAlignment(Bitmap resultBitmap, Bitmap inputBitmap, float[] landmarks, float[] alignmentPoints);

        public final boolean initialize(Context context) {
            File file = new File(context.getFilesDir(), tfwhgw.rnigpa(54));
            FilesKt.writeBytes(file, ByteStreamsKt.readBytes(context.getResources().openRawResource(R.raw.haarcascade_frontalface_default)));
            File file2 = new File(context.getFilesDir(), tfwhgw.rnigpa(55));
            FilesKt.writeBytes(file2, ByteStreamsKt.readBytes(context.getResources().openRawResource(R.raw.lbfmodel)));
            return nativeInit(file.getAbsolutePath(), file2.getAbsolutePath());
        }

        public final native void nativeDestroy();
    }

    static {
        System.loadLibrary(tfwhgw.rnigpa(255));
    }

    public VFaceLib(Bitmap bitmap) {
        this.input = bitmap;
    }

    public final int[] getFaceRect() {
        return this.faceRect;
    }

    public final native boolean getFaces(int minSize, int maxSize);

    public final String getFailReason() {
        return this.failReason;
    }

    public final float[] getLandmarks() {
        return this.landmarks;
    }

    public final boolean isSuccessful() {
        return this.landmarks != null;
    }

    public final void setFaceRect(int[] iArr) {
        this.faceRect = iArr;
    }

    public final void setFailReason(String str) {
        this.failReason = str;
    }

    public final void setLandmarks(float[] fArr) {
        this.landmarks = fArr;
    }
}
