package com.pichillilorenzo.flutter_inappwebview_android.content_blocker;

import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.facebook.share.internal.ShareConstants;

/* loaded from: classes4.dex */
public enum ContentBlockerTriggerResourceType {
    DOCUMENT(WorkflowModule.TYPE_DOCUMENT),
    IMAGE("image"),
    STYLE_SHEET("style-sheet"),
    SCRIPT("script"),
    FONT("font"),
    SVG_DOCUMENT("svg-document"),
    MEDIA(ShareConstants.WEB_DIALOG_PARAM_MEDIA),
    POPUP(WorkflowModule.UI_STYLE_POPUP),
    RAW("raw");

    private final String value;

    ContentBlockerTriggerResourceType(String str) {
        this.value = str;
    }

    public boolean equalsValue(String str) {
        return this.value.equals(str);
    }

    public static ContentBlockerTriggerResourceType fromValue(String str) {
        for (ContentBlockerTriggerResourceType contentBlockerTriggerResourceType : values()) {
            if (str.equals(contentBlockerTriggerResourceType.value)) {
                return contentBlockerTriggerResourceType;
            }
        }
        throw new IllegalArgumentException("No enum constant: " + str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
