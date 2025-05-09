package co.hyperverge.hyperkyc.ui.form.models;

import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FormFilePickUIEvent.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/models/FormFilePickUIEvent;", "", "componentId", "", "supportedFile", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SupportedFile;", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SupportedFile;)V", "getComponentId", "()Ljava/lang/String;", "getSupportedFile", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SupportedFile;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class FormFilePickUIEvent {
    private final String componentId;
    private final WorkflowModule.Properties.Section.Component.SupportedFile supportedFile;

    public static /* synthetic */ FormFilePickUIEvent copy$default(FormFilePickUIEvent formFilePickUIEvent, String str, WorkflowModule.Properties.Section.Component.SupportedFile supportedFile, int i, Object obj) {
        if ((i & 1) != 0) {
            str = formFilePickUIEvent.componentId;
        }
        if ((i & 2) != 0) {
            supportedFile = formFilePickUIEvent.supportedFile;
        }
        return formFilePickUIEvent.copy(str, supportedFile);
    }

    /* renamed from: component1, reason: from getter */
    public final String getComponentId() {
        return this.componentId;
    }

    /* renamed from: component2, reason: from getter */
    public final WorkflowModule.Properties.Section.Component.SupportedFile getSupportedFile() {
        return this.supportedFile;
    }

    public final FormFilePickUIEvent copy(String componentId, WorkflowModule.Properties.Section.Component.SupportedFile supportedFile) {
        Intrinsics.checkNotNullParameter(componentId, "componentId");
        Intrinsics.checkNotNullParameter(supportedFile, "supportedFile");
        return new FormFilePickUIEvent(componentId, supportedFile);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FormFilePickUIEvent)) {
            return false;
        }
        FormFilePickUIEvent formFilePickUIEvent = (FormFilePickUIEvent) other;
        return Intrinsics.areEqual(this.componentId, formFilePickUIEvent.componentId) && Intrinsics.areEqual(this.supportedFile, formFilePickUIEvent.supportedFile);
    }

    public int hashCode() {
        return (this.componentId.hashCode() * 31) + this.supportedFile.hashCode();
    }

    public String toString() {
        return "FormFilePickUIEvent(componentId=" + this.componentId + ", supportedFile=" + this.supportedFile + ')';
    }

    public FormFilePickUIEvent(String componentId, WorkflowModule.Properties.Section.Component.SupportedFile supportedFile) {
        Intrinsics.checkNotNullParameter(componentId, "componentId");
        Intrinsics.checkNotNullParameter(supportedFile, "supportedFile");
        this.componentId = componentId;
        this.supportedFile = supportedFile;
    }

    public final String getComponentId() {
        return this.componentId;
    }

    public final WorkflowModule.Properties.Section.Component.SupportedFile getSupportedFile() {
        return this.supportedFile;
    }
}
