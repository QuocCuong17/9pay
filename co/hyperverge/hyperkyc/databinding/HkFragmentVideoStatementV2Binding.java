package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.ui.custom.shimmeringViews.ShimmerTextView;
import co.hyperverge.hyperkyc.ui.custom.shimmeringViews.ShimmeringMaterialButton;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

/* loaded from: classes2.dex */
public final class HkFragmentVideoStatementV2Binding implements ViewBinding {
    public final ConstraintLayout bottomContainer;
    public final ShimmeringMaterialButton btnPrimary;
    public final MaterialButton btnSecondary;
    public final ConstraintLayout buttonBlock;
    public final ConstraintLayout camView;
    public final HkLayoutBrandingBinding cardLayoutBranding;
    public final CardView cvError;
    public final HkLayoutTopBarBinding hkLayoutTopBar;
    public final HkLayoutBrandingBinding includeBranding;
    public final MaterialCardView otpCard;
    public final ImageView progressSpinnerImageView;
    public final LinearLayout recIndicator;
    private final ConstraintLayout rootView;
    public final ShimmerTextView statementHelpText;
    public final TextView statementText;
    public final ConstraintLayout statementTextLayout;
    public final ShimmerTextView statusText;
    public final ConstraintLayout topContainer;
    public final TextView tvError;
    public final TextView tvLoadingMsg;
    public final ConstraintLayout videoContainer;
    public final ConstraintLayout vvContainer;
    public final VideoView vvReview;

    private HkFragmentVideoStatementV2Binding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ShimmeringMaterialButton shimmeringMaterialButton, MaterialButton materialButton, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, HkLayoutBrandingBinding hkLayoutBrandingBinding, CardView cardView, HkLayoutTopBarBinding hkLayoutTopBarBinding, HkLayoutBrandingBinding hkLayoutBrandingBinding2, MaterialCardView materialCardView, ImageView imageView, LinearLayout linearLayout, ShimmerTextView shimmerTextView, TextView textView, ConstraintLayout constraintLayout5, ShimmerTextView shimmerTextView2, ConstraintLayout constraintLayout6, TextView textView2, TextView textView3, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, VideoView videoView) {
        this.rootView = constraintLayout;
        this.bottomContainer = constraintLayout2;
        this.btnPrimary = shimmeringMaterialButton;
        this.btnSecondary = materialButton;
        this.buttonBlock = constraintLayout3;
        this.camView = constraintLayout4;
        this.cardLayoutBranding = hkLayoutBrandingBinding;
        this.cvError = cardView;
        this.hkLayoutTopBar = hkLayoutTopBarBinding;
        this.includeBranding = hkLayoutBrandingBinding2;
        this.otpCard = materialCardView;
        this.progressSpinnerImageView = imageView;
        this.recIndicator = linearLayout;
        this.statementHelpText = shimmerTextView;
        this.statementText = textView;
        this.statementTextLayout = constraintLayout5;
        this.statusText = shimmerTextView2;
        this.topContainer = constraintLayout6;
        this.tvError = textView2;
        this.tvLoadingMsg = textView3;
        this.videoContainer = constraintLayout7;
        this.vvContainer = constraintLayout8;
        this.vvReview = videoView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkFragmentVideoStatementV2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkFragmentVideoStatementV2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_fragment_video_statement_v2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkFragmentVideoStatementV2Binding bind(View view) {
        View findChildViewById;
        View findChildViewById2;
        int i = R.id.bottomContainer;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
        if (constraintLayout != null) {
            i = R.id.btnPrimary;
            ShimmeringMaterialButton shimmeringMaterialButton = (ShimmeringMaterialButton) ViewBindings.findChildViewById(view, i);
            if (shimmeringMaterialButton != null) {
                i = R.id.btnSecondary;
                MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
                if (materialButton != null) {
                    i = R.id.buttonBlock;
                    ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                    if (constraintLayout2 != null) {
                        i = R.id.camView;
                        ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                        if (constraintLayout3 != null && (findChildViewById = ViewBindings.findChildViewById(view, (i = R.id.card_layout_branding))) != null) {
                            HkLayoutBrandingBinding bind = HkLayoutBrandingBinding.bind(findChildViewById);
                            i = R.id.cvError;
                            CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
                            if (cardView != null && (findChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.hk_layout_top_bar))) != null) {
                                HkLayoutTopBarBinding bind2 = HkLayoutTopBarBinding.bind(findChildViewById2);
                                i = R.id.includeBranding;
                                View findChildViewById3 = ViewBindings.findChildViewById(view, i);
                                if (findChildViewById3 != null) {
                                    HkLayoutBrandingBinding bind3 = HkLayoutBrandingBinding.bind(findChildViewById3);
                                    i = R.id.otpCard;
                                    MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(view, i);
                                    if (materialCardView != null) {
                                        i = R.id.progressSpinnerImageView;
                                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                                        if (imageView != null) {
                                            i = R.id.recIndicator;
                                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                            if (linearLayout != null) {
                                                i = R.id.statementHelpText;
                                                ShimmerTextView shimmerTextView = (ShimmerTextView) ViewBindings.findChildViewById(view, i);
                                                if (shimmerTextView != null) {
                                                    i = R.id.statementText;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                                    if (textView != null) {
                                                        i = R.id.statementTextLayout;
                                                        ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                                        if (constraintLayout4 != null) {
                                                            i = R.id.statusText;
                                                            ShimmerTextView shimmerTextView2 = (ShimmerTextView) ViewBindings.findChildViewById(view, i);
                                                            if (shimmerTextView2 != null) {
                                                                i = R.id.topContainer;
                                                                ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                                                if (constraintLayout5 != null) {
                                                                    i = R.id.tvError;
                                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                    if (textView2 != null) {
                                                                        i = R.id.tvLoadingMsg;
                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                        if (textView3 != null) {
                                                                            i = R.id.videoContainer;
                                                                            ConstraintLayout constraintLayout6 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                                                            if (constraintLayout6 != null) {
                                                                                i = R.id.vvContainer;
                                                                                ConstraintLayout constraintLayout7 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                                                                if (constraintLayout7 != null) {
                                                                                    i = R.id.vvReview;
                                                                                    VideoView videoView = (VideoView) ViewBindings.findChildViewById(view, i);
                                                                                    if (videoView != null) {
                                                                                        return new HkFragmentVideoStatementV2Binding((ConstraintLayout) view, constraintLayout, shimmeringMaterialButton, materialButton, constraintLayout2, constraintLayout3, bind, cardView, bind2, bind3, materialCardView, imageView, linearLayout, shimmerTextView, textView, constraintLayout4, shimmerTextView2, constraintLayout5, textView2, textView3, constraintLayout6, constraintLayout7, videoView);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
