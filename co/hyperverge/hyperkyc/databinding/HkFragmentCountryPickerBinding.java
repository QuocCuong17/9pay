package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.ui.custom.ClickableMotionLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

/* loaded from: classes2.dex */
public final class HkFragmentCountryPickerBinding implements ViewBinding {
    public final MaterialButton btnContinue;
    public final MaterialButton btnCountry;
    public final View divider;
    public final AppCompatEditText etSearch;
    public final ImageView hkClientLogo;
    public final View hkDummyView;
    public final HkLayoutBrandingBinding includeBranding;
    public final ImageFilterView ivBack;
    public final ClickableMotionLayout motionLayout;
    private final ClickableMotionLayout rootView;
    public final RecyclerView rvCountries;
    public final MaterialTextView tvSubTitle;
    public final MaterialTextView tvTitle;

    private HkFragmentCountryPickerBinding(ClickableMotionLayout clickableMotionLayout, MaterialButton materialButton, MaterialButton materialButton2, View view, AppCompatEditText appCompatEditText, ImageView imageView, View view2, HkLayoutBrandingBinding hkLayoutBrandingBinding, ImageFilterView imageFilterView, ClickableMotionLayout clickableMotionLayout2, RecyclerView recyclerView, MaterialTextView materialTextView, MaterialTextView materialTextView2) {
        this.rootView = clickableMotionLayout;
        this.btnContinue = materialButton;
        this.btnCountry = materialButton2;
        this.divider = view;
        this.etSearch = appCompatEditText;
        this.hkClientLogo = imageView;
        this.hkDummyView = view2;
        this.includeBranding = hkLayoutBrandingBinding;
        this.ivBack = imageFilterView;
        this.motionLayout = clickableMotionLayout2;
        this.rvCountries = recyclerView;
        this.tvSubTitle = materialTextView;
        this.tvTitle = materialTextView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ClickableMotionLayout getRoot() {
        return this.rootView;
    }

    public static HkFragmentCountryPickerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkFragmentCountryPickerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_fragment_country_picker, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkFragmentCountryPickerBinding bind(View view) {
        View findChildViewById;
        View findChildViewById2;
        View findChildViewById3;
        int i = R.id.btnContinue;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.btnCountry;
            MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton2 != null && (findChildViewById = ViewBindings.findChildViewById(view, (i = R.id.divider))) != null) {
                i = R.id.etSearch;
                AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, i);
                if (appCompatEditText != null) {
                    i = R.id.hkClientLogo;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView != null && (findChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.hkDummyView))) != null && (findChildViewById3 = ViewBindings.findChildViewById(view, (i = R.id.includeBranding))) != null) {
                        HkLayoutBrandingBinding bind = HkLayoutBrandingBinding.bind(findChildViewById3);
                        i = R.id.ivBack;
                        ImageFilterView imageFilterView = (ImageFilterView) ViewBindings.findChildViewById(view, i);
                        if (imageFilterView != null) {
                            ClickableMotionLayout clickableMotionLayout = (ClickableMotionLayout) view;
                            i = R.id.rvCountries;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                            if (recyclerView != null) {
                                i = R.id.tvSubTitle;
                                MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                                if (materialTextView != null) {
                                    i = R.id.tvTitle;
                                    MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                                    if (materialTextView2 != null) {
                                        return new HkFragmentCountryPickerBinding(clickableMotionLayout, materialButton, materialButton2, findChildViewById, appCompatEditText, imageView, findChildViewById2, bind, imageFilterView, clickableMotionLayout, recyclerView, materialTextView, materialTextView2);
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
