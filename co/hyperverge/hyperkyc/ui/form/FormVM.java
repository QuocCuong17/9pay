package co.hyperverge.hyperkyc.ui.form;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.ui.form.models.FormFilePickUIEvent;
import co.hyperverge.hyperkyc.ui.form.models.FormFileReviewUIEvent;
import co.hyperverge.hyperkyc.ui.form.models.PickedFile;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: FormVM.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u0018R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/FormVM;", "Landroidx/lifecycle/ViewModel;", "()V", "_fileReviewUIEvent", "Landroidx/lifecycle/MutableLiveData;", "Lco/hyperverge/hyperkyc/ui/form/models/FormFileReviewUIEvent;", "_fileTypePickUiEvent", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lco/hyperverge/hyperkyc/ui/form/models/FormFilePickUIEvent;", "fileReviewUIEvent", "Landroidx/lifecycle/LiveData;", "getFileReviewUIEvent", "()Landroidx/lifecycle/LiveData;", "fileTypePickUiEvent", "Lkotlinx/coroutines/flow/SharedFlow;", "getFileTypePickUiEvent", "()Lkotlinx/coroutines/flow/SharedFlow;", "performFileTypePick", "Lkotlinx/coroutines/Job;", "componentId", "", "supportedFile", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SupportedFile;", "removePickedFile", "", "pickedFile", "Lco/hyperverge/hyperkyc/ui/form/models/PickedFile;", "resetFileReviewUIEvent", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormVM extends ViewModel {
    private final MutableLiveData<FormFileReviewUIEvent> _fileReviewUIEvent;
    private final MutableSharedFlow<FormFilePickUIEvent> _fileTypePickUiEvent;
    private final LiveData<FormFileReviewUIEvent> fileReviewUIEvent;
    private final SharedFlow<FormFilePickUIEvent> fileTypePickUiEvent;

    public FormVM() {
        MutableSharedFlow<FormFilePickUIEvent> MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this._fileTypePickUiEvent = MutableSharedFlow$default;
        this.fileTypePickUiEvent = MutableSharedFlow$default;
        MutableLiveData<FormFileReviewUIEvent> mutableLiveData = new MutableLiveData<>();
        this._fileReviewUIEvent = mutableLiveData;
        this.fileReviewUIEvent = mutableLiveData;
    }

    public final SharedFlow<FormFilePickUIEvent> getFileTypePickUiEvent() {
        return this.fileTypePickUiEvent;
    }

    public final LiveData<FormFileReviewUIEvent> getFileReviewUIEvent() {
        return this.fileReviewUIEvent;
    }

    public final Job performFileTypePick(String componentId, WorkflowModule.Properties.Section.Component.SupportedFile supportedFile) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(componentId, "componentId");
        Intrinsics.checkNotNullParameter(supportedFile, "supportedFile");
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new FormVM$performFileTypePick$1(this, componentId, supportedFile, null), 3, null);
        return launch$default;
    }

    public final void removePickedFile(PickedFile pickedFile) {
        Intrinsics.checkNotNullParameter(pickedFile, "pickedFile");
        this._fileReviewUIEvent.postValue(new FormFileReviewUIEvent(pickedFile));
    }

    public final void resetFileReviewUIEvent() {
        this._fileReviewUIEvent.setValue(null);
    }
}
