package vn.ai.faceauth.sdk.camera;

import androidx.camera.view.PreviewView;
import androidx.lifecycle.LifecycleObserver;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.Flow;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.camera.domain.model.FaceResult;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&J\b\u0010\t\u001a\u00020\nH&J\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00070\fH&J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H&J\u001a\u0010\u0011\u001a\u00020\u00052\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0013H&¨\u0006\u0014"}, d2 = {"Lvn/ai/faceauth/sdk/camera/CameraX;", "", "deleteAllPictures", "", "finishStep", "", "getAllPictures", "", "", "getLifecycleObserver", "Landroidx/lifecycle/LifecycleObserver;", "observeFaceList", "Lkotlinx/coroutines/flow/Flow;", "Lvn/ai/faceauth/sdk/camera/domain/model/FaceResult;", "startCamera", "cameraPreviewView", "Landroidx/camera/view/PreviewView;", "takePicture", "myCallback", "Lkotlin/Function0;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface CameraX {

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void takePicture$default(CameraX cameraX, Function0 function0, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException(tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_PLANAR_CONFIGURATION));
            }
            if ((i & 1) != 0) {
                function0 = null;
            }
            cameraX.takePicture(function0);
        }
    }

    boolean deleteAllPictures();

    void finishStep();

    List<String> getAllPictures();

    LifecycleObserver getLifecycleObserver();

    Flow<List<FaceResult>> observeFaceList();

    void startCamera(PreviewView cameraPreviewView);

    void takePicture(Function0<Unit> myCallback);
}
