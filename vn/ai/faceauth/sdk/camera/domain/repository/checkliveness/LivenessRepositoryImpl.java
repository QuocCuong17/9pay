package vn.ai.faceauth.sdk.camera.domain.repository.checkliveness;

import android.util.Log;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.camera.domain.model.FaceResult;
import vn.ai.faceauth.sdk.core.extensions.PrimitiveExtensionsKt;
import vn.ai.faceauth.sdk.domain.model.Rect;
import vn.ai.faceauth.sdk.domain.model.StateFaceWithOval;
import vn.ai.faceauth.sdk.domain.repository.LivenessRepository;
import vn.ai.faceauth.sdk.presentation.domain.configs.LivenessConfig;
import vn.ai.faceauth.sdk.presentation.domain.model.Constants;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010 \n\u0002\b\t\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J@\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00070\f2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\fH\u0002J@\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00070\f2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\fH\u0002J+\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\fH\u0016¢\u0006\u0002\u0010\u0018J@\u0010\u0019\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00070\f2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\fH\u0002JH\u0010\u001a\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u00122\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00070\f2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\fH\u0016JP\u0010\u001d\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00070\f2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\fH\u0016J\u0017\u0010\u001e\u001a\u00020\u000f2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0002\u0010 J\u0018\u0010!\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\u0016\u0010\"\u001a\u00020\u000f2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00020$H\u0016J\u001f\u0010%\u001a\u00020\u000f2\b\u0010&\u001a\u0004\u0018\u00010\u00162\u0006\u0010\t\u001a\u00020\nH\u0002¢\u0006\u0002\u0010'J5\u0010(\u001a\u00020\u000f2\b\u0010)\u001a\u0004\u0018\u00010\u00162\b\u0010*\u001a\u0004\u0018\u00010\u00162\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\fH\u0016¢\u0006\u0002\u0010,R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lvn/ai/faceauth/sdk/camera/domain/repository/checkliveness/LivenessRepositoryImpl;", "Lvn/ai/faceauth/sdk/domain/repository/LivenessRepository;", "Lvn/ai/faceauth/sdk/camera/domain/model/FaceResult;", "()V", "TAG", "", "checkLuminosity", "", "face", "livenessConfig", "Lvn/ai/faceauth/sdk/presentation/domain/configs/LivenessConfig;", "callbackStatus", "Lkotlin/Function1;", "Lvn/ai/faceauth/sdk/domain/model/StateFaceWithOval;", "callbackZoomIn", "", "checkMoveClose", "faceRect", "Lvn/ai/faceauth/sdk/domain/model/Rect;", "ovalRect", "checkSmile", "smilingProbability", "", "callbackSmiled", "(Ljava/lang/Float;Lkotlin/jvm/functions/Function1;)V", "checkSmile1", "checkStepFistCheck", "innerRect", "oval", "checkStepZoomIn", "isEyeOpened", "eyeOpenedProbabilityValue", "(Ljava/lang/Float;)Z", "isFaceRectAreaLessThanPercentOfOvalRect", "isFacesDetectedCorrect", "listFaceResult", "", "isLuminosityGood", "luminosity", "(Ljava/lang/Float;Lvn/ai/faceauth/sdk/presentation/domain/configs/LivenessConfig;)Z", "validateBlinkedEyes", "leftEyeProbability", "rightEyeProbability", "callbackBlinked", "(Ljava/lang/Float;Ljava/lang/Float;Lkotlin/jvm/functions/Function1;)Z", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class LivenessRepositoryImpl implements LivenessRepository<FaceResult> {
    private final String TAG = tfwhgw.rnigpa(242);

    private final void checkLuminosity(FaceResult face, LivenessConfig livenessConfig, Function1<? super StateFaceWithOval, Unit> callbackStatus, Function1<? super Boolean, Unit> callbackZoomIn) {
        if (isLuminosityGood(face.getLuminosity(), livenessConfig)) {
            return;
        }
        callbackStatus.invoke(StateFaceWithOval.LUMINOSITY);
        callbackZoomIn.invoke(Boolean.FALSE);
    }

    private final void checkMoveClose(Rect faceRect, Rect ovalRect, Function1<? super StateFaceWithOval, Unit> callbackStatus, Function1<? super Boolean, Unit> callbackZoomIn) {
        Boolean bool;
        boolean isFaceRectAreaLessThanPercentOfOvalRect = isFaceRectAreaLessThanPercentOfOvalRect(faceRect, ovalRect);
        Log.e(this.TAG, tfwhgw.rnigpa(243) + isFaceRectAreaLessThanPercentOfOvalRect);
        if (isFaceRectAreaLessThanPercentOfOvalRect) {
            callbackStatus.invoke(StateFaceWithOval.MOVE_CLOSER);
            bool = Boolean.FALSE;
        } else {
            callbackStatus.invoke(StateFaceWithOval.WITHIN);
            bool = Boolean.TRUE;
        }
        callbackZoomIn.invoke(bool);
    }

    private final void checkSmile1(FaceResult face, LivenessConfig livenessConfig, Function1<? super StateFaceWithOval, Unit> callbackStatus, Function1<? super Boolean, Unit> callbackZoomIn) {
        Float smilingProbability = face.getSmilingProbability();
        if (smilingProbability != null) {
            float floatValue = smilingProbability.floatValue();
            if (livenessConfig.getSmileProbability() == 0.0f) {
                return;
            }
            if (floatValue <= livenessConfig.getSmileProbability()) {
                return;
            }
            callbackStatus.invoke(StateFaceWithOval.NO_SMILE);
            callbackZoomIn.invoke(Boolean.FALSE);
        }
    }

    private final boolean isFaceRectAreaLessThanPercentOfOvalRect(Rect faceRect, Rect ovalRect) {
        float right = faceRect.getRight();
        float left = faceRect.getLeft();
        float bottom = faceRect.getBottom();
        float top = faceRect.getTop();
        double bottom2 = (ovalRect.getBottom() - ovalRect.getTop()) * (ovalRect.getRight() - ovalRect.getLeft());
        Double.isNaN(bottom2);
        return ((double) ((bottom - top) * (right - left))) < bottom2 * 0.5d;
    }

    private final boolean isLuminosityGood(Float luminosity, LivenessConfig livenessConfig) {
        Boolean bool;
        if (luminosity != null) {
            bool = Boolean.valueOf(luminosity.floatValue() > livenessConfig.getLuminosityProbability() * ((float) 255));
        } else {
            bool = null;
        }
        return PrimitiveExtensionsKt.orFalse(bool);
    }

    @Override // vn.ai.faceauth.sdk.domain.repository.LivenessRepository
    public void checkSmile(Float smilingProbability, Function1<? super Boolean, Unit> callbackSmiled) {
        Boolean bool;
        if (smilingProbability != null) {
            float floatValue = smilingProbability.floatValue();
            System.out.println(floatValue);
            bool = Boolean.valueOf(floatValue <= 0.3f);
        } else {
            bool = null;
        }
        boolean orFalse = PrimitiveExtensionsKt.orFalse(bool);
        if (orFalse) {
            callbackSmiled.invoke(Boolean.valueOf(orFalse));
        }
    }

    @Override // vn.ai.faceauth.sdk.domain.repository.LivenessRepository
    public /* bridge */ /* synthetic */ void checkStepFistCheck(FaceResult faceResult, Rect rect, Rect rect2, Function1 function1, Function1 function12) {
        checkStepFistCheck2(faceResult, rect, rect2, (Function1<? super StateFaceWithOval, Unit>) function1, (Function1<? super Boolean, Unit>) function12);
    }

    /* renamed from: checkStepFistCheck, reason: avoid collision after fix types in other method */
    public void checkStepFistCheck2(FaceResult face, Rect innerRect, Rect oval, Function1<? super StateFaceWithOval, Unit> callbackStatus, Function1<? super Boolean, Unit> callbackZoomIn) {
        StateFaceWithOval stateFaceWithOval;
        android.graphics.Rect rect = new android.graphics.Rect((int) oval.getLeft(), (int) oval.getTop(), (int) oval.getRight(), (int) oval.getBottom());
        boolean z = innerRect.getLeft() >= ((float) rect.left) && innerRect.getTop() >= ((float) rect.top) && innerRect.getRight() <= ((float) rect.right) && innerRect.getBottom() <= ((float) rect.bottom);
        Log.e(this.TAG, tfwhgw.rnigpa(244) + z);
        if (z) {
            callbackZoomIn.invoke(Boolean.TRUE);
            stateFaceWithOval = StateFaceWithOval.WITHIN;
        } else {
            callbackZoomIn.invoke(Boolean.FALSE);
            stateFaceWithOval = StateFaceWithOval.NO_FACE;
        }
        callbackStatus.invoke(stateFaceWithOval);
    }

    @Override // vn.ai.faceauth.sdk.domain.repository.LivenessRepository
    public /* bridge */ /* synthetic */ void checkStepZoomIn(FaceResult faceResult, Rect rect, Rect rect2, LivenessConfig livenessConfig, Function1 function1, Function1 function12) {
        checkStepZoomIn2(faceResult, rect, rect2, livenessConfig, (Function1<? super StateFaceWithOval, Unit>) function1, (Function1<? super Boolean, Unit>) function12);
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x0159, code lost:
    
        if ((-8.0f <= r4 && r4 <= 8.0f) == false) goto L48;
     */
    /* renamed from: checkStepZoomIn, reason: avoid collision after fix types in other method */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void checkStepZoomIn2(FaceResult face, Rect faceRect, Rect ovalRect, LivenessConfig livenessConfig, Function1<? super StateFaceWithOval, Unit> callbackStatus, Function1<? super Boolean, Unit> callbackZoomIn) {
        android.graphics.Rect rect = new android.graphics.Rect((int) ovalRect.getLeft(), (int) ovalRect.getTop(), (int) ovalRect.getRight(), (int) ovalRect.getBottom());
        boolean z = faceRect.getLeft() >= ((float) rect.left) && faceRect.getTop() >= ((float) rect.top) && faceRect.getRight() <= ((float) rect.right) && faceRect.getBottom() <= ((float) rect.bottom);
        Log.d(this.TAG, tfwhgw.rnigpa(245) + z);
        if (!z) {
            if (android.graphics.Rect.intersects(rect, faceRect.toAndroidRect())) {
                Log.d(this.TAG, tfwhgw.rnigpa(251));
                r2 = ((float) rect.left) >= faceRect.getLeft() || ((float) rect.top) >= faceRect.getTop() || ((float) rect.right) <= faceRect.getRight() || ((float) rect.bottom) <= faceRect.getBottom();
                Log.d(this.TAG, tfwhgw.rnigpa(252) + z + tfwhgw.rnigpa(253) + r2);
                callbackZoomIn.invoke(Boolean.FALSE);
                if (r2) {
                    callbackStatus.invoke(StateFaceWithOval.MOVE_AWAY);
                    return;
                }
            } else {
                Log.d(this.TAG, tfwhgw.rnigpa(254));
                callbackZoomIn.invoke(Boolean.FALSE);
            }
            callbackStatus.invoke(StateFaceWithOval.FRAME_YOUR_FACE_IN_OVAL);
            return;
        }
        Float leftEyeOpenProbability = face.getLeftEyeOpenProbability();
        if (leftEyeOpenProbability != null) {
            float floatValue = leftEyeOpenProbability.floatValue();
            Float rightEyeOpenProbability = face.getRightEyeOpenProbability();
            if (rightEyeOpenProbability != null) {
                float floatValue2 = rightEyeOpenProbability.floatValue();
                Log.d(this.TAG, tfwhgw.rnigpa(246) + face.getLeftEyeOpenProbability());
                Log.d(this.TAG, tfwhgw.rnigpa(247) + face.getRightEyeOpenProbability());
                Constants.Companion companion = Constants.INSTANCE;
                if (floatValue < companion.getEYE_OPENED_PROBABILITY() && floatValue2 < companion.getEYE_OPENED_PROBABILITY()) {
                    callbackStatus.invoke(StateFaceWithOval.EYES_CLOSED);
                    callbackZoomIn.invoke(Boolean.FALSE);
                    return;
                }
            }
        }
        float headEulerAngleX = face.getHeadEulerAngleX();
        float headEulerAngleY = face.getHeadEulerAngleY();
        float headEulerAngleZ = face.getHeadEulerAngleZ();
        Log.e(this.TAG, tfwhgw.rnigpa(248) + face.getHeadEulerAngleX());
        Log.e(this.TAG, tfwhgw.rnigpa(249) + face.getHeadEulerAngleY());
        Log.e(this.TAG, tfwhgw.rnigpa(250) + face.getHeadEulerAngleZ());
        if (-12.0f <= headEulerAngleX && headEulerAngleX <= 12.0f) {
            if (-8.0f <= headEulerAngleY && headEulerAngleY <= 8.0f) {
            }
        }
        r2 = true;
        if (r2) {
            callbackStatus.invoke(StateFaceWithOval.FACE_NOT_ALIGN);
            callbackZoomIn.invoke(Boolean.FALSE);
        } else {
            checkSmile1(face, livenessConfig, callbackStatus, callbackZoomIn);
            checkLuminosity(face, livenessConfig, callbackStatus, callbackZoomIn);
            checkMoveClose(faceRect, ovalRect, callbackStatus, callbackZoomIn);
        }
    }

    @Override // vn.ai.faceauth.sdk.domain.repository.LivenessRepository
    public boolean isEyeOpened(Float eyeOpenedProbabilityValue) {
        Boolean bool;
        if (eyeOpenedProbabilityValue != null) {
            bool = Boolean.valueOf(eyeOpenedProbabilityValue.floatValue() > Constants.INSTANCE.getEYE_OPENED_PROBABILITY());
        } else {
            bool = null;
        }
        return PrimitiveExtensionsKt.orFalse(bool);
    }

    @Override // vn.ai.faceauth.sdk.domain.repository.LivenessRepository
    public boolean isFacesDetectedCorrect(List<? extends FaceResult> listFaceResult) {
        return (listFaceResult.isEmpty() ^ true) && listFaceResult.size() > 4;
    }

    @Override // vn.ai.faceauth.sdk.domain.repository.LivenessRepository
    public boolean validateBlinkedEyes(Float leftEyeProbability, Float rightEyeProbability, Function1<? super Boolean, Unit> callbackBlinked) {
        Boolean bool;
        Boolean bool2 = null;
        boolean z = false;
        if (leftEyeProbability != null) {
            bool = Boolean.valueOf(leftEyeProbability.floatValue() > Constants.INSTANCE.getEYE_OPENED_PROBABILITY());
        } else {
            bool = null;
        }
        boolean orFalse = PrimitiveExtensionsKt.orFalse(bool);
        if (rightEyeProbability != null) {
            bool2 = Boolean.valueOf(rightEyeProbability.floatValue() > Constants.INSTANCE.getEYE_OPENED_PROBABILITY());
        }
        boolean orFalse2 = PrimitiveExtensionsKt.orFalse(bool2);
        if (orFalse && orFalse2) {
            z = true;
        }
        if (!z) {
            callbackBlinked.invoke(Boolean.TRUE);
        }
        return z;
    }
}
