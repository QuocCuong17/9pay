package vn.ai.faceauth.sdk.core.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004J\u0013\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00018\u00008\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lvn/ai/faceauth/sdk/core/viewmodel/StateViewModel;", "State", "Landroidx/lifecycle/ViewModel;", "initialState", "(Ljava/lang/Object;)V", "mutableState", "Landroidx/lifecycle/MutableLiveData;", "kotlin.jvm.PlatformType", "state", "Landroidx/lifecycle/LiveData;", "getState", "()Landroidx/lifecycle/LiveData;", "setState", "", "newState", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public class StateViewModel<State> extends ViewModel {
    private final MutableLiveData<State> mutableState;
    private final LiveData<State> state;

    public StateViewModel(State state) {
        MutableLiveData<State> mutableLiveData = new MutableLiveData<>(state);
        this.mutableState = mutableLiveData;
        this.state = mutableLiveData;
    }

    public final LiveData<State> getState() {
        return this.state;
    }

    public final void setState(State newState) {
        this.mutableState.setValue(newState);
    }
}
