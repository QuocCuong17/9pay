package vn.ai.faceauth.sdk.camera.core.analyzer;

import android.graphics.Matrix;
import android.util.Size;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlinx.coroutines.CoroutineScope;
import vn.ai.faceauth.sdk.camera.core.processor.FrameProcessor;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001f\u0010\f\u001a\u00020\t2\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u000e\"\u00020\u0007¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lvn/ai/faceauth/sdk/camera/core/analyzer/CameraXAnalyzer;", "Landroidx/camera/core/ImageAnalysis$Analyzer;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlinx/coroutines/CoroutineScope;)V", "processors", "", "Lvn/ai/faceauth/sdk/camera/core/processor/FrameProcessor;", "analyze", "", "image", "Landroidx/camera/core/ImageProxy;", "attachProcessor", "frameProcessor", "", "([Lvn/ai/faceauth/sdk/camera/core/processor/FrameProcessor;)V", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class CameraXAnalyzer implements ImageAnalysis.Analyzer {
    private final CoroutineScope coroutineScope;
    private final List<FrameProcessor> processors = new ArrayList();

    public CameraXAnalyzer(CoroutineScope coroutineScope) {
        this.coroutineScope = coroutineScope;
    }

    @Override // androidx.camera.core.ImageAnalysis.Analyzer
    public void analyze(ImageProxy image) {
        DebounceOperatorsKt.debounce(300L, this.coroutineScope, new CameraXAnalyzer$analyze$1(this, image, null)).invoke();
    }

    public final void attachProcessor(FrameProcessor... frameProcessor) {
        CollectionsKt.addAll(this.processors, frameProcessor);
    }

    @Override // androidx.camera.core.ImageAnalysis.Analyzer
    public /* synthetic */ int getTargetCoordinateSystem() {
        return ImageAnalysis.Analyzer.CC.$default$getTargetCoordinateSystem(this);
    }

    @Override // androidx.camera.core.ImageAnalysis.Analyzer
    public /* synthetic */ Size getTargetResolutionOverride() {
        return ImageAnalysis.Analyzer.CC.$default$getTargetResolutionOverride(this);
    }

    @Override // androidx.camera.core.ImageAnalysis.Analyzer
    public /* synthetic */ void updateTransform(Matrix matrix) {
        ImageAnalysis.Analyzer.CC.$default$updateTransform(this, matrix);
    }
}
