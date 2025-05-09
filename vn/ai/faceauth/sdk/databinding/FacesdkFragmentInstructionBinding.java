package vn.ai.faceauth.sdk.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.R;
import vn.ai.faceauth.sdk.presentation.presentation.widgets.GifDrawableView;

/* loaded from: classes6.dex */
public final class FacesdkFragmentInstructionBinding implements ViewBinding {
    public final Button btnAccept;
    public final RelativeLayout clRoot;
    public final GifDrawableView imageInstruction;
    private final RelativeLayout rootView;
    public final TextView textInstruction;
    public final TextView textInstruction1;
    public final TextView textInstruction2;
    public final TextView textInstruction3;
    public final TextView textInstruction4;
    public final TextView toolbarText;

    private FacesdkFragmentInstructionBinding(RelativeLayout relativeLayout, Button button, RelativeLayout relativeLayout2, GifDrawableView gifDrawableView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = relativeLayout;
        this.btnAccept = button;
        this.clRoot = relativeLayout2;
        this.imageInstruction = gifDrawableView;
        this.textInstruction = textView;
        this.textInstruction1 = textView2;
        this.textInstruction2 = textView3;
        this.textInstruction3 = textView4;
        this.textInstruction4 = textView5;
        this.toolbarText = textView6;
    }

    public static FacesdkFragmentInstructionBinding bind(View view) {
        int i = R.id.btnAccept;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.imageInstruction;
            GifDrawableView gifDrawableView = (GifDrawableView) ViewBindings.findChildViewById(view, i);
            if (gifDrawableView != null) {
                i = R.id.textInstruction;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.textInstruction1;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null) {
                        i = R.id.textInstruction2;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView3 != null) {
                            i = R.id.textInstruction3;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView4 != null) {
                                i = R.id.textInstruction4;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView5 != null) {
                                    i = R.id.toolbarText;
                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView6 != null) {
                                        return new FacesdkFragmentInstructionBinding(relativeLayout, button, relativeLayout, gifDrawableView, textView, textView2, textView3, textView4, textView5, textView6);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException(tfwhgw.rnigpa(50).concat(view.getResources().getResourceName(i)));
    }

    public static FacesdkFragmentInstructionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FacesdkFragmentInstructionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.facesdk_fragment_instruction, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
