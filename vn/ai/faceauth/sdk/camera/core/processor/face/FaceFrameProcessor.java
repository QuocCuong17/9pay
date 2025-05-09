package vn.ai.faceauth.sdk.camera.core.processor.face;

import java.util.List;
import kotlin.Metadata;
import kotlinx.coroutines.flow.Flow;
import vn.ai.faceauth.sdk.camera.core.processor.FrameProcessor;
import vn.ai.faceauth.sdk.camera.domain.model.FaceResult;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H&¨\u0006\u0006"}, d2 = {"Lvn/ai/faceauth/sdk/camera/core/processor/face/FaceFrameProcessor;", "Lvn/ai/faceauth/sdk/camera/core/processor/FrameProcessor;", "observeFaceList", "Lkotlinx/coroutines/flow/Flow;", "", "Lvn/ai/faceauth/sdk/camera/domain/model/FaceResult;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface FaceFrameProcessor extends FrameProcessor {
    Flow<List<FaceResult>> observeFaceList();
}
