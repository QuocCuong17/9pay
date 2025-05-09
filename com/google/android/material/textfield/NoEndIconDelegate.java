package com.google.android.material.textfield;

import android.graphics.drawable.Drawable;

/* loaded from: classes3.dex */
class NoEndIconDelegate extends EndIconDelegate {
    /* JADX INFO: Access modifiers changed from: package-private */
    public NoEndIconDelegate(TextInputLayout textInputLayout) {
        super(textInputLayout, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.textfield.EndIconDelegate
    public void initialize() {
        this.textInputLayout.setEndIconOnClickListener(null);
        this.textInputLayout.setEndIconDrawable((Drawable) null);
        this.textInputLayout.setEndIconContentDescription((CharSequence) null);
    }
}
