package vn.ai.faceauth.sdk.domain.repository;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import vn.ai.faceauth.sdk.domain.model.Rect;
import vn.ai.faceauth.sdk.domain.model.StateFaceWithOval;
import vn.ai.faceauth.sdk.presentation.domain.configs.LivenessConfig;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0006\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J+\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00040\bH&¢\u0006\u0002\u0010\nJM\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00040\b2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00040\bH&¢\u0006\u0002\u0010\u0013JU\u0010\u0014\u001a\u00020\u00042\u0006\u0010\f\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00040\b2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00040\bH&¢\u0006\u0002\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\t2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0006H&¢\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\t2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH&J5\u0010 \u001a\u00020\t2\b\u0010!\u001a\u0004\u0018\u00010\u00062\b\u0010\"\u001a\u0004\u0018\u00010\u00062\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00040\bH&¢\u0006\u0002\u0010$¨\u0006%"}, d2 = {"Lvn/ai/faceauth/sdk/domain/repository/LivenessRepository;", "FACE", "", "checkSmile", "", "smilingProbability", "", "callbackSmiled", "Lkotlin/Function1;", "", "(Ljava/lang/Float;Lkotlin/jvm/functions/Function1;)V", "checkStepFistCheck", "face", "rect", "Lvn/ai/faceauth/sdk/domain/model/Rect;", "oval", "callbackStatus", "Lvn/ai/faceauth/sdk/domain/model/StateFaceWithOval;", "callbackZoomIn", "(Ljava/lang/Object;Lvn/ai/faceauth/sdk/domain/model/Rect;Lvn/ai/faceauth/sdk/domain/model/Rect;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "checkStepZoomIn", "faceRect", "ovalRect", "livenessConfig", "Lvn/ai/faceauth/sdk/presentation/domain/configs/LivenessConfig;", "(Ljava/lang/Object;Lvn/ai/faceauth/sdk/domain/model/Rect;Lvn/ai/faceauth/sdk/domain/model/Rect;Lvn/ai/faceauth/sdk/presentation/domain/configs/LivenessConfig;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "isEyeOpened", "eyeOpenedProbabilityValue", "(Ljava/lang/Float;)Z", "isFacesDetectedCorrect", "listFaceResult", "", "validateBlinkedEyes", "leftEyeProbability", "rightEyeProbability", "callbackBlinked", "(Ljava/lang/Float;Ljava/lang/Float;Lkotlin/jvm/functions/Function1;)Z", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface LivenessRepository<FACE> {
    void checkSmile(Float smilingProbability, Function1<? super Boolean, Unit> callbackSmiled);

    void checkStepFistCheck(FACE face, Rect rect, Rect oval, Function1<? super StateFaceWithOval, Unit> callbackStatus, Function1<? super Boolean, Unit> callbackZoomIn);

    void checkStepZoomIn(FACE face, Rect faceRect, Rect ovalRect, LivenessConfig livenessConfig, Function1<? super StateFaceWithOval, Unit> callbackStatus, Function1<? super Boolean, Unit> callbackZoomIn);

    boolean isEyeOpened(Float eyeOpenedProbabilityValue);

    boolean isFacesDetectedCorrect(List<? extends FACE> listFaceResult);

    boolean validateBlinkedEyes(Float leftEyeProbability, Float rightEyeProbability, Function1<? super Boolean, Unit> callbackBlinked);
}
