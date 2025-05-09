package vn.ai.faceauth.sdk.presentation.presentation.fragment;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.mlkit.vision.face.Face;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.databinding.FacesdkFragmentCameraxBinding;
import vn.ai.faceauth.sdk.domain.model.PhotoResultDomain;
import vn.ai.faceauth.sdk.presentation.presentation.opencv.Point;
import vn.ai.faceauth.sdk.presentation.presentation.widgets.FaceImageProcessor;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tH\u0016¨\u0006\n"}, d2 = {"vn/ai/faceauth/sdk/presentation/presentation/fragment/CameraXFragment$tryReloadAndDetectInImage$1", "Lvn/ai/faceauth/sdk/presentation/presentation/widgets/FaceImageProcessor;", "processBitmap", "", "face", "Lcom/google/mlkit/vision/face/Face;", "landmarkPoints", "Ljava/util/ArrayList;", "Lvn/ai/faceauth/sdk/presentation/presentation/opencv/Point;", "Lkotlin/collections/ArrayList;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class CameraXFragment$tryReloadAndDetectInImage$1 implements FaceImageProcessor {
    final /* synthetic */ PhotoResultDomain $it;
    final /* synthetic */ CameraXFragment this$0;

    public CameraXFragment$tryReloadAndDetectInImage$1(CameraXFragment cameraXFragment, PhotoResultDomain photoResultDomain) {
        this.this$0 = cameraXFragment;
        this.$it = photoResultDomain;
    }

    @Override // vn.ai.faceauth.sdk.presentation.presentation.widgets.FaceImageProcessor
    public void processBitmap(Face face, ArrayList<Point> landmarkPoints) {
        String str;
        String str2;
        Bitmap faceAlignment;
        Bitmap scaleBitmapWithCanvas;
        Bitmap compressBitmap;
        String str3;
        String str4;
        FacesdkFragmentCameraxBinding facesdkFragmentCameraxBinding;
        String rnigpa = tfwhgw.rnigpa(433);
        String rnigpa2 = tfwhgw.rnigpa(434);
        String rnigpa3 = tfwhgw.rnigpa(435);
        String rnigpa4 = tfwhgw.rnigpa(436);
        if (face != null) {
            try {
                str2 = this.this$0.TAG;
                Log.e(str2, rnigpa4 + this.this$0.getRawImage().size());
                if (this.this$0.getRawImage().size() == 1) {
                    facesdkFragmentCameraxBinding = this.this$0.get_binding();
                    if (facesdkFragmentCameraxBinding.overlayView.isStep1()) {
                        return;
                    }
                }
                faceAlignment = this.this$0.faceAlignment(this.$it.getBitMap(), landmarkPoints);
                CameraXFragment cameraXFragment = this.this$0;
                scaleBitmapWithCanvas = cameraXFragment.scaleBitmapWithCanvas(this.$it.getBitMap(), 512, 512);
                compressBitmap = cameraXFragment.compressBitmap(scaleBitmapWithCanvas, Bitmap.CompressFormat.JPEG, 80);
                this.this$0.getMapDataUploadAPI().put(rnigpa3 + this.$it.getCreatedAt(), this.this$0.bitmapToBase64(compressBitmap));
                this.this$0.getMapDataUploadAPI().put(rnigpa2 + this.$it.getCreatedAt(), this.this$0.jsonObjectToBase64(face.getBoundingBox(), faceAlignment, landmarkPoints));
                str3 = this.this$0.TAG;
                Log.e(str3, rnigpa + this.$it.getCreatedAt());
                List<String> list = CollectionsKt.toList(this.this$0.getMapDataUploadAPI().keySet());
                CameraXFragment cameraXFragment2 = this.this$0;
                for (String str5 : list) {
                    str4 = cameraXFragment2.TAG;
                    Log.e(str4, tfwhgw.rnigpa(437) + str5 + tfwhgw.rnigpa(438) + cameraXFragment2.getMapDataUploadAPI().get(str5));
                }
                this.this$0.getRawImage().add(this.$it);
                Handler handler = new Handler(Looper.getMainLooper());
                final CameraXFragment cameraXFragment3 = this.this$0;
                handler.postDelayed(new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.presentation.fragment.CameraXFragment$tryReloadAndDetectInImage$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        CameraXFragment.access$nextStep(CameraXFragment.this);
                    }
                }, 50L);
            } catch (Exception e) {
                e.printStackTrace();
                str = this.this$0.TAG;
                Log.e(str, tfwhgw.rnigpa(439) + this.$it.getCreatedAt());
            }
        }
    }
}
