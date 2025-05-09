package co.hyperverge.hyperkyc.ui.form.models;

import android.app.Application;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.FileExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.facebook.share.internal.ShareConstants;
import java.util.Locale;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: PickedFile.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0081\b\u0018\u00002\u00020\u0001:\u0001HBW\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\rJ\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u000b\u0010/\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u00101\u001a\u00020\u000bHÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0005HÆ\u0003J`\u00103\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u00104J\u0006\u00105\u001a\u00020\u0000J\u0013\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\b\u00109\u001a\u0004\u0018\u00010\u0005J\u0006\u0010:\u001a\u000207J\t\u0010;\u001a\u00020<HÖ\u0001J/\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@2\u0006\u0010\u000e\u001a\u00020\u000f2\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0015¢\u0006\u0002\b\u0016J\u0006\u0010A\u001a\u000207J\u0006\u0010B\u001a\u000207J\u0006\u0010C\u001a\u000207J\u0006\u0010D\u001a\u000207J\u0006\u0010E\u001a\u000207J\u0006\u0010F\u001a\u000207J\t\u0010G\u001a\u00020\u0005HÖ\u0001R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001f\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0015¢\u0006\u0002\b\u0016X\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0011\"\u0004\b\u0018\u0010\u0013R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0011\"\u0004\b\u001a\u0010\u0013R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001b\u0010 \u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b!\u0010\u0011R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0011\"\u0004\b)\u0010\u0013R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+¨\u0006I"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/models/PickedFile;", "", ShareConstants.MEDIA_URI, "Landroid/net/Uri;", "name", "", "sizeKB", "", ShareConstants.MEDIA_EXTENSION, "type", "state", "Lco/hyperverge/hyperkyc/ui/form/models/PickedFile$State;", "localPath", "(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Lco/hyperverge/hyperkyc/ui/form/models/PickedFile$State;Ljava/lang/String;)V", "component", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "getExtension", "()Ljava/lang/String;", "setExtension", "(Ljava/lang/String;)V", "injector", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "getLocalPath", "setLocalPath", "getName", "setName", "getSizeKB", "()Ljava/lang/Long;", "setSizeKB", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "sizeLabel", "getSizeLabel", "sizeLabel$delegate", "Lkotlin/Lazy;", "getState", "()Lco/hyperverge/hyperkyc/ui/form/models/PickedFile$State;", "setState", "(Lco/hyperverge/hyperkyc/ui/form/models/PickedFile$State;)V", "getType", "setType", "getUri", "()Landroid/net/Uri;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Lco/hyperverge/hyperkyc/ui/form/models/PickedFile$State;Ljava/lang/String;)Lco/hyperverge/hyperkyc/ui/form/models/PickedFile;", "deepCopy", "equals", "", "other", "getErrorMsg", "hasError", "hashCode", "", "initMetadata", "", "contentResolver", "Landroid/content/ContentResolver;", "isError", "isLoading", "isSizeAboveMax", "isSizeBelowMin", "isSizeOutOfRange", "isSuccess", InAppPurchaseConstants.METHOD_TO_STRING, "State", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class PickedFile {
    private WorkflowModule.Properties.Section.Component component;
    private String extension;
    private Function1<? super String, String> injector;
    private String localPath;
    private String name;
    private Long sizeKB;

    /* renamed from: sizeLabel$delegate, reason: from kotlin metadata */
    private final Lazy sizeLabel;
    private State state;
    private String type;
    private final Uri uri;

    public PickedFile() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public static /* synthetic */ PickedFile copy$default(PickedFile pickedFile, Uri uri, String str, Long l, String str2, String str3, State state, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            uri = pickedFile.uri;
        }
        if ((i & 2) != 0) {
            str = pickedFile.name;
        }
        String str5 = str;
        if ((i & 4) != 0) {
            l = pickedFile.sizeKB;
        }
        Long l2 = l;
        if ((i & 8) != 0) {
            str2 = pickedFile.extension;
        }
        String str6 = str2;
        if ((i & 16) != 0) {
            str3 = pickedFile.type;
        }
        String str7 = str3;
        if ((i & 32) != 0) {
            state = pickedFile.state;
        }
        State state2 = state;
        if ((i & 64) != 0) {
            str4 = pickedFile.localPath;
        }
        return pickedFile.copy(uri, str5, l2, str6, str7, state2, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final Uri getUri() {
        return this.uri;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final Long getSizeKB() {
        return this.sizeKB;
    }

    /* renamed from: component4, reason: from getter */
    public final String getExtension() {
        return this.extension;
    }

    /* renamed from: component5, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component6, reason: from getter */
    public final State getState() {
        return this.state;
    }

    /* renamed from: component7, reason: from getter */
    public final String getLocalPath() {
        return this.localPath;
    }

    public final PickedFile copy(Uri uri, String name, Long sizeKB, String extension, String type, State state, String localPath) {
        Intrinsics.checkNotNullParameter(state, "state");
        return new PickedFile(uri, name, sizeKB, extension, type, state, localPath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PickedFile)) {
            return false;
        }
        PickedFile pickedFile = (PickedFile) other;
        return Intrinsics.areEqual(this.uri, pickedFile.uri) && Intrinsics.areEqual(this.name, pickedFile.name) && Intrinsics.areEqual(this.sizeKB, pickedFile.sizeKB) && Intrinsics.areEqual(this.extension, pickedFile.extension) && Intrinsics.areEqual(this.type, pickedFile.type) && Intrinsics.areEqual(this.state, pickedFile.state) && Intrinsics.areEqual(this.localPath, pickedFile.localPath);
    }

    public int hashCode() {
        Uri uri = this.uri;
        int hashCode = (uri == null ? 0 : uri.hashCode()) * 31;
        String str = this.name;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Long l = this.sizeKB;
        int hashCode3 = (hashCode2 + (l == null ? 0 : l.hashCode())) * 31;
        String str2 = this.extension;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.type;
        int hashCode5 = (((hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.state.hashCode()) * 31;
        String str4 = this.localPath;
        return hashCode5 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "PickedFile(uri=" + this.uri + ", name=" + this.name + ", sizeKB=" + this.sizeKB + ", extension=" + this.extension + ", type=" + this.type + ", state=" + this.state + ", localPath=" + this.localPath + ')';
    }

    public PickedFile(Uri uri, String str, Long l, String str2, String str3, State state, String str4) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.uri = uri;
        this.name = str;
        this.sizeKB = l;
        this.extension = str2;
        this.type = str3;
        this.state = state;
        this.localPath = str4;
        this.sizeLabel = LazyKt.lazy(new Function0<String>() { // from class: co.hyperverge.hyperkyc.ui.form.models.PickedFile$sizeLabel$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return FileExtsKt.asSizeLabel(PickedFile.this.getSizeKB());
            }
        });
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final Long getSizeKB() {
        return this.sizeKB;
    }

    public final void setSizeKB(Long l) {
        this.sizeKB = l;
    }

    public final String getExtension() {
        return this.extension;
    }

    public final void setExtension(String str) {
        this.extension = str;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public /* synthetic */ PickedFile(Uri uri, String str, Long l, String str2, String str3, State.Loading loading, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : uri, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : l, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? State.Loading.INSTANCE : loading, (i & 64) != 0 ? null : str4);
    }

    public final State getState() {
        return this.state;
    }

    public final void setState(State state) {
        Intrinsics.checkNotNullParameter(state, "<set-?>");
        this.state = state;
    }

    public final String getLocalPath() {
        return this.localPath;
    }

    public final void setLocalPath(String str) {
        this.localPath = str;
    }

    public final String getSizeLabel() {
        return (String) this.sizeLabel.getValue();
    }

    public final boolean isLoading() {
        return this.state instanceof State.Loading;
    }

    public final boolean isSuccess() {
        return this.state instanceof State.Success;
    }

    public final boolean isError() {
        return this.state instanceof State.Error;
    }

    public final boolean isSizeAboveMax() {
        Long l = this.sizeKB;
        if (l != null) {
            long longValue = l.longValue();
            WorkflowModule.Properties.Section.Component component = this.component;
            if (component == null) {
                Intrinsics.throwUninitializedPropertyAccessException("component");
                component = null;
            }
            Long maxFileSize = component.getMaxFileSize();
            if (Intrinsics.compare(longValue, maxFileSize != null ? maxFileSize.longValue() : Long.MAX_VALUE) == 1) {
                return true;
            }
        }
        return false;
    }

    public final boolean isSizeBelowMin() {
        Long l = this.sizeKB;
        if (l == null) {
            return false;
        }
        long longValue = l.longValue();
        WorkflowModule.Properties.Section.Component component = this.component;
        if (component == null) {
            Intrinsics.throwUninitializedPropertyAccessException("component");
            component = null;
        }
        Long minFileSize = component.getMinFileSize();
        return Intrinsics.compare(longValue, minFileSize != null ? minFileSize.longValue() : Long.MIN_VALUE) == -1;
    }

    public final boolean isSizeOutOfRange() {
        return isSizeAboveMax() || isSizeBelowMin();
    }

    public final boolean hasError() {
        Long l;
        return isSizeOutOfRange() || isError() || ((l = this.sizeKB) != null && l.longValue() == 0);
    }

    public final String getErrorMsg() {
        Function1<? super String, String> function1 = null;
        if (isError()) {
            WorkflowModule.Properties.Section.Component component = this.component;
            if (component == null) {
                Intrinsics.throwUninitializedPropertyAccessException("component");
                component = null;
            }
            String errorTextFile = component.getErrorTextFile();
            if (errorTextFile == null) {
                return null;
            }
            Function1<? super String, String> function12 = this.injector;
            if (function12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("injector");
            } else {
                function1 = function12;
            }
            return function1.invoke(errorTextFile);
        }
        if (isSizeAboveMax()) {
            WorkflowModule.Properties.Section.Component component2 = this.component;
            if (component2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("component");
                component2 = null;
            }
            String errorTextSizeMax = component2.getErrorTextSizeMax();
            if (errorTextSizeMax == null) {
                return null;
            }
            Function1<? super String, String> function13 = this.injector;
            if (function13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("injector");
            } else {
                function1 = function13;
            }
            return function1.invoke(errorTextSizeMax);
        }
        if (!isSizeBelowMin()) {
            return null;
        }
        WorkflowModule.Properties.Section.Component component3 = this.component;
        if (component3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("component");
            component3 = null;
        }
        String errorTextSizeMin = component3.getErrorTextSizeMin();
        if (errorTextSizeMin == null) {
            return null;
        }
        Function1<? super String, String> function14 = this.injector;
        if (function14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injector");
        } else {
            function1 = function14;
        }
        return function1.invoke(errorTextSizeMin);
    }

    /* compiled from: PickedFile.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0005\u0006\u0007B\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016\u0082\u0001\u0003\b\t\n¨\u0006\u000b"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/models/PickedFile$State;", "", "()V", InAppPurchaseConstants.METHOD_TO_STRING, "", "Error", "Loading", "Success", "Lco/hyperverge/hyperkyc/ui/form/models/PickedFile$State$Error;", "Lco/hyperverge/hyperkyc/ui/form/models/PickedFile$State$Loading;", "Lco/hyperverge/hyperkyc/ui/form/models/PickedFile$State$Success;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static abstract class State {
        public /* synthetic */ State(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private State() {
        }

        /* compiled from: PickedFile.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/models/PickedFile$State$Loading;", "Lco/hyperverge/hyperkyc/ui/form/models/PickedFile$State;", "()V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Loading extends State {
            public static final Loading INSTANCE = new Loading();

            private Loading() {
                super(null);
            }
        }

        /* compiled from: PickedFile.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/models/PickedFile$State$Error;", "Lco/hyperverge/hyperkyc/ui/form/models/PickedFile$State;", "errorMsg", "", "(Ljava/lang/String;)V", "getErrorMsg", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final /* data */ class Error extends State {
            private final String errorMsg;

            public static /* synthetic */ Error copy$default(Error error, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = error.errorMsg;
                }
                return error.copy(str);
            }

            /* renamed from: component1, reason: from getter */
            public final String getErrorMsg() {
                return this.errorMsg;
            }

            public final Error copy(String errorMsg) {
                return new Error(errorMsg);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Error) && Intrinsics.areEqual(this.errorMsg, ((Error) other).errorMsg);
            }

            public int hashCode() {
                String str = this.errorMsg;
                if (str == null) {
                    return 0;
                }
                return str.hashCode();
            }

            @Override // co.hyperverge.hyperkyc.ui.form.models.PickedFile.State
            public String toString() {
                return "Error(errorMsg=" + this.errorMsg + ')';
            }

            public Error(String str) {
                super(null);
                this.errorMsg = str;
            }

            public final String getErrorMsg() {
                return this.errorMsg;
            }
        }

        /* compiled from: PickedFile.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lco/hyperverge/hyperkyc/ui/form/models/PickedFile$State$Success;", "Lco/hyperverge/hyperkyc/ui/form/models/PickedFile$State;", "()V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Success extends State {
            public static final Success INSTANCE = new Success();

            private Success() {
                super(null);
            }
        }

        public String toString() {
            String simpleName = getClass().getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName, "javaClass.simpleName");
            return simpleName;
        }
    }

    public final PickedFile deepCopy() {
        return new PickedFile(this.uri, this.name, this.sizeKB, this.extension, this.type, this.state, null, 64, null);
    }

    public final void initMetadata(ContentResolver contentResolver, WorkflowModule.Properties.Section.Component component, Function1<? super String, String> injector) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String className;
        String substringAfterLast$default;
        String str2;
        String str3;
        String className2;
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        Intrinsics.checkNotNullParameter(component, "component");
        Intrinsics.checkNotNullParameter(injector, "injector");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str4 = "initMetadata() called with: contentResolver = " + contentResolver + ", component = " + component + ", injector = " + injector;
        if (str4 == null) {
            str4 = "null ";
        }
        sb.append(str4);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        str = canonicalName2 == null ? "N/A" : canonicalName2;
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str5 = "initMetadata() called with: contentResolver = " + contentResolver + ", component = " + component + ", injector = " + injector;
                    if (str5 == null) {
                        str5 = "null ";
                    }
                    sb2.append(str5);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        this.component = component;
        this.injector = injector;
        this.name = FileExtsKt.getFileName(this.uri, contentResolver);
        this.sizeKB = Long.valueOf(FileExtsKt.getFileSizeKB(this.uri, contentResolver));
        String str6 = this.name;
        if (str6 != null) {
            str2 = null;
            String substringAfterLast$default2 = StringsKt.substringAfterLast$default(str6, ".", (String) null, 2, (Object) null);
            if (substringAfterLast$default2 != null) {
                str3 = substringAfterLast$default2.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                this.extension = str3;
            }
        } else {
            str2 = null;
        }
        str3 = str2;
        this.extension = str3;
    }
}
