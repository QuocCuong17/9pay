package co.hyperverge.hyperkyc.ui.models;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: LoadingUIState.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u000b\f\r\u000eB\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0001\u0004\u000f\u0010\u0011\u0012¨\u0006\u0013"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/LoadingUIState;", "", "lottieFileName", "", "lottieRepeatCount", "", "(Ljava/lang/String;I)V", "getLottieFileName", "()Ljava/lang/String;", "getLottieRepeatCount", "()I", "Failure", "Idle", "Processing", "Success", "Lco/hyperverge/hyperkyc/ui/models/LoadingUIState$Failure;", "Lco/hyperverge/hyperkyc/ui/models/LoadingUIState$Idle;", "Lco/hyperverge/hyperkyc/ui/models/LoadingUIState$Processing;", "Lco/hyperverge/hyperkyc/ui/models/LoadingUIState$Success;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class LoadingUIState {
    private final String lottieFileName;
    private final int lottieRepeatCount;

    public /* synthetic */ LoadingUIState(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i);
    }

    /* compiled from: LoadingUIState.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/LoadingUIState$Idle;", "Lco/hyperverge/hyperkyc/ui/models/LoadingUIState;", "()V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Idle extends LoadingUIState {
        public static final Idle INSTANCE = new Idle();

        private Idle() {
            super("", 0, null);
        }
    }

    private LoadingUIState(String str, int i) {
        this.lottieFileName = str;
        this.lottieRepeatCount = i;
    }

    public final String getLottieFileName() {
        return this.lottieFileName;
    }

    public final int getLottieRepeatCount() {
        return this.lottieRepeatCount;
    }

    /* compiled from: LoadingUIState.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/LoadingUIState$Processing;", "Lco/hyperverge/hyperkyc/ui/models/LoadingUIState;", "()V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Processing extends LoadingUIState {
        public static final Processing INSTANCE = new Processing();

        private Processing() {
            super("hv_processing.lottie", -1, null);
        }
    }

    /* compiled from: LoadingUIState.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/LoadingUIState$Success;", "Lco/hyperverge/hyperkyc/ui/models/LoadingUIState;", "()V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Success extends LoadingUIState {
        public static final Success INSTANCE = new Success();

        private Success() {
            super("hv_success.lottie", 1, null);
        }
    }

    /* compiled from: LoadingUIState.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/LoadingUIState$Failure;", "Lco/hyperverge/hyperkyc/ui/models/LoadingUIState;", "()V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Failure extends LoadingUIState {
        public static final Failure INSTANCE = new Failure();

        private Failure() {
            super("hv_failure.lottie", 1, null);
        }
    }
}
