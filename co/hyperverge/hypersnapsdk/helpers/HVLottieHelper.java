package co.hyperverge.hypersnapsdk.helpers;

import android.animation.Animator;
import android.graphics.Color;
import android.text.TextUtils;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import co.hyperverge.hypersnapsdk.utils.StringUtils;
import co.hyperverge.hypersnapsdk.utils.UIUtils;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.SimpleLottieValueCallback;

/* loaded from: classes2.dex */
public class HVLottieHelper {
    public static final String DOC_FAILURE = "doc_failure.lottie";
    public static final String DOC_INSTRUCTION = "hv_doc_instruction_front.lottie";
    public static final String DOC_INSTRUCTION_BACK = "hv_doc_instruction_back.lottie";
    public static final String DOC_INSTRUCTION_FRONT = "hv_doc_instruction_front.lottie";
    public static final String DOC_PROCESSING = "doc_processing.lottie";
    public static final String DOC_SUCCESS = "doc_success.lottie";
    public static final String END_STATE_FAILURE = "end_state_failure.lottie";
    public static final String END_STATE_PROCESSING = "end_state_processing.lottie";
    public static final String END_STATE_SUCCESS = "end_state_success.lottie";
    public static final String FACE_FAILURE = "face_failure.lottie";
    public static final String FACE_INSTRUCTION = "hv_face_instruction.lottie";
    public static final String FACE_PROCESSING = "face_processing.lottie";
    public static final String FACE_SUCCESS = "face_success.lottie";
    public static final String LOADER_LOTTIE = "hv_processing.lottie";
    public static final String NFC_LOTTIE = "hv_nfc.lottie";
    public static final String QR_INSTRUCTION = "hv_qr_instruction.lottie";
    private static final String TAG = "co.hyperverge.hypersnapsdk.helpers.HVLottieHelper";
    public static final String UPLOAD_FAILURE = "upload_failure.lottie";

    /* loaded from: classes2.dex */
    public interface Listener {
        void onEnd();
    }

    public static void load(final LottieAnimationView lottieAnimationView, String str, State state, final Listener listener) {
        lottieAnimationView.setApplyingOpacityToLayersEnabled(true);
        lottieAnimationView.invalidate();
        lottieAnimationView.setImageAssetsFolder("images");
        loadAnimations(str, lottieAnimationView);
        lottieAnimationView.setSpeed(state.speed);
        lottieAnimationView.setRepeatCount(state.repeatCount);
        if (state.progress != 0.0f) {
            lottieAnimationView.setMinAndMaxProgress(state.progress, 1.0f);
        }
        lottieAnimationView.invalidate();
        if (listener != null) {
            lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() { // from class: co.hyperverge.hypersnapsdk.helpers.HVLottieHelper.1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    LottieAnimationView.this.removeAnimatorListener(this);
                    listener.onEnd();
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    LottieAnimationView.this.removeAnimatorListener(this);
                }
            });
        }
        if (lottieAnimationView.isAnimating()) {
            return;
        }
        lottieAnimationView.playAnimation();
    }

    private static void loadAnimations(String str, LottieAnimationView lottieAnimationView) {
        if (str != null) {
            String documentInstructionLottie = HyperSnapUIConfigUtil.getInstance().getDocumentInstructionLottie();
            if (str.equalsIgnoreCase("hv_doc_instruction_front.lottie")) {
                String docInstructionFrontSideAnimation = HyperSnapUIConfigUtil.getInstance().getDocInstructionFrontSideAnimation();
                if (StringUtils.isEmptyOrNull(docInstructionFrontSideAnimation)) {
                    docInstructionFrontSideAnimation = documentInstructionLottie;
                }
                setAnimation(docInstructionFrontSideAnimation, lottieAnimationView, str);
            }
            if (str.equalsIgnoreCase(DOC_INSTRUCTION_BACK)) {
                String documentInstructionBackSideAnimation = HyperSnapUIConfigUtil.getInstance().getDocumentInstructionBackSideAnimation();
                if (!StringUtils.isEmptyOrNull(documentInstructionBackSideAnimation)) {
                    documentInstructionLottie = documentInstructionBackSideAnimation;
                }
                setAnimation(documentInstructionLottie, lottieAnimationView, str);
            }
            if (str.equalsIgnoreCase(DOC_PROCESSING)) {
                setAnimation(HyperSnapUIConfigUtil.getInstance().getDocProcessingAnimation(), lottieAnimationView, str);
            }
            if (str.equalsIgnoreCase(DOC_SUCCESS)) {
                setAnimation(HyperSnapUIConfigUtil.getInstance().getDocumentSuccessAnimation(), lottieAnimationView, str);
            }
            if (str.equalsIgnoreCase(DOC_FAILURE)) {
                setAnimation(HyperSnapUIConfigUtil.getInstance().getDocumentFailureAnimation(), lottieAnimationView, str);
            }
            if (str.equalsIgnoreCase(FACE_INSTRUCTION)) {
                setAnimation(HyperSnapUIConfigUtil.getInstance().getFaceInstructionAnimation(), lottieAnimationView, str);
            }
            if (str.equalsIgnoreCase(FACE_PROCESSING)) {
                setAnimation(HyperSnapUIConfigUtil.getInstance().getFaceProcessingAnimation(), lottieAnimationView, str);
            }
            if (str.equalsIgnoreCase(FACE_SUCCESS)) {
                setAnimation(HyperSnapUIConfigUtil.getInstance().getFaceSuccessAnimation(), lottieAnimationView, str);
            }
            if (str.equalsIgnoreCase(FACE_FAILURE)) {
                setAnimation(HyperSnapUIConfigUtil.getInstance().getFaceFailureAnimation(), lottieAnimationView, str);
            }
            if (str.equalsIgnoreCase(END_STATE_PROCESSING)) {
                setAnimation(HyperSnapUIConfigUtil.getInstance().getEndStateProcessingAnimation(), lottieAnimationView, str);
            }
            if (str.equalsIgnoreCase(END_STATE_SUCCESS)) {
                setAnimation(HyperSnapUIConfigUtil.getInstance().getEndStateSuccessAnimation(), lottieAnimationView, str);
            }
            if (str.equalsIgnoreCase(END_STATE_FAILURE)) {
                setAnimation(HyperSnapUIConfigUtil.getInstance().getEndStateFailureAnimation(), lottieAnimationView, str);
            }
            if (str.equalsIgnoreCase(UPLOAD_FAILURE)) {
                setAnimation(HyperSnapUIConfigUtil.getInstance().getUploadFailureAnimation(), lottieAnimationView, str);
            }
            if (str.equalsIgnoreCase(QR_INSTRUCTION)) {
                setAnimation(HyperSnapUIConfigUtil.getInstance().getQrInstructionAnimation(), lottieAnimationView, str);
            }
            if (str.equalsIgnoreCase("hv_processing.lottie")) {
                setAnimation(HyperSnapUIConfigUtil.getInstance().getLoaderAnimation(), lottieAnimationView, str);
            }
            if (str.equalsIgnoreCase(NFC_LOTTIE)) {
                setAnimation(HyperSnapUIConfigUtil.getInstance().getNfcAnimation(), lottieAnimationView, str);
            }
        }
    }

    private static void setAnimation(String str, LottieAnimationView lottieAnimationView, String str2) {
        if (isValidAnimUrl(str)) {
            lottieAnimationView.setAnimationFromUrl(str);
        } else {
            lottieAnimationView.setAnimation(str2);
        }
        String animationPrimaryColor = HyperSnapUIConfigUtil.getInstance().getConfig().getColors().getAnimationPrimaryColor();
        if (HyperSnapUIConfigUtil.getInstance().isValidHexColor(animationPrimaryColor)) {
            final int parseColor = Color.parseColor(animationPrimaryColor);
            lottieAnimationView.addValueCallback(new KeyPath("**", "hvLottiePrimaryColor"), (KeyPath) LottieProperty.COLOR, (SimpleLottieValueCallback<KeyPath>) new SimpleLottieValueCallback() { // from class: co.hyperverge.hypersnapsdk.helpers.HVLottieHelper$$ExternalSyntheticLambda0
                @Override // com.airbnb.lottie.value.SimpleLottieValueCallback
                public final Object getValue(LottieFrameInfo lottieFrameInfo) {
                    Integer valueOf;
                    valueOf = Integer.valueOf(parseColor);
                    return valueOf;
                }
            });
            lottieAnimationView.addValueCallback(new KeyPath("**", "hvLottiePrimaryColor"), (KeyPath) LottieProperty.STROKE_COLOR, (SimpleLottieValueCallback<KeyPath>) new SimpleLottieValueCallback() { // from class: co.hyperverge.hypersnapsdk.helpers.HVLottieHelper$$ExternalSyntheticLambda1
                @Override // com.airbnb.lottie.value.SimpleLottieValueCallback
                public final Object getValue(LottieFrameInfo lottieFrameInfo) {
                    Integer valueOf;
                    valueOf = Integer.valueOf(parseColor);
                    return valueOf;
                }
            });
            if (UIUtils.hasAlpha(parseColor)) {
                lottieAnimationView.addValueCallback(new KeyPath("**", "hvLottiePrimaryColor"), (KeyPath) LottieProperty.OPACITY, (SimpleLottieValueCallback<KeyPath>) new SimpleLottieValueCallback() { // from class: co.hyperverge.hypersnapsdk.helpers.HVLottieHelper$$ExternalSyntheticLambda2
                    @Override // com.airbnb.lottie.value.SimpleLottieValueCallback
                    public final Object getValue(LottieFrameInfo lottieFrameInfo) {
                        Integer valueOf;
                        valueOf = Integer.valueOf(UIUtils.alphaOf(parseColor));
                        return valueOf;
                    }
                });
            }
        }
    }

    private static boolean isValidAnimUrl(String str) {
        return !TextUtils.isEmpty(str) && StringUtils.isURL(str);
    }

    public static void reset(LottieAnimationView lottieAnimationView) {
        lottieAnimationView.invalidate();
        lottieAnimationView.removeAllAnimatorListeners();
    }

    /* loaded from: classes2.dex */
    public enum State {
        START(1.5f, 0.0f, -1),
        TRANSITION(2.0f, 0.0f, 0),
        END(2.0f, 0.5f, 0);

        final float progress;
        final int repeatCount;
        final float speed;

        State(float f, float f2, int i) {
            this.speed = f;
            this.progress = f2;
            this.repeatCount = i;
        }

        @Override // java.lang.Enum
        public String toString() {
            return name() + " {speed=" + this.speed + ", progress=" + this.progress + ", repeatCount=" + this.repeatCount + '}';
        }
    }
}
