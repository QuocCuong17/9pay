package co.hyperverge.hyperkyc.data.models;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.exoplayer.DefaultLoadControl;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import co.hyperverge.hyperkyc.data.models.KycDocument;
import co.hyperverge.hyperkyc.data.network.ResponsesKt;
import co.hyperverge.hyperkyc.ui.UploadingFragment;
import co.hyperverge.hyperkyc.ui.WebViewFragment;
import co.hyperverge.hyperkyc.ui.form.FormFragment;
import co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import io.flutter.plugins.firebase.database.Constants;
import io.sentry.protocol.ViewHierarchyNode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDListAttributeObject;
import org.apache.pdfbox.pdmodel.interactive.form.PDButton;

/* compiled from: WorkflowConfig.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0081\b\u0018\u0000 /2\u00020\u0001:\u0003/01B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f¢\u0006\u0002\u0010\u000eJ\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010'\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fHÆ\u0003Ji\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fHÆ\u0001J\u0013\u0010)\u001a\u00020\u00122\b\u0010*\u001a\u0004\u0018\u00010+HÖ\u0003J\t\u0010,\u001a\u00020-HÖ\u0001J\t\u0010.\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0010R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0010R\u001e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u00062"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule;", "Ljava/io/Serializable;", "id", "", "type", WebViewFragment.ARG_SUB_TYPE, "next", "previous", "properties", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties;", "uiStyle", "variables", "", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Variable;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties;Ljava/lang/String;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "isChild", "", "isChild$hyperkyc_release", "()Z", "getNext", "setNext", "(Ljava/lang/String;)V", "getPrevious", "getProperties", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties;", "getSubType", "getType", "getUiStyle", "getVariables", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "Companion", "Properties", "Variable", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class WorkflowModule implements Serializable {
    private static final String DEPRECATED_MSG = "use HyperKycResult.Status, remove once clients start using new workflow config statuses";
    public static final String DISMISS_TO_PARENT = "dismissToParent";
    public static final String END_STATE_MANUAL_REVIEW = "manualReview";
    public static final String PREFIX_CONDITION = "condition_";
    public static final String PREFIX_CONDITIONAL_VARS = "conditionalVariables";
    public static final String PREFIX_INPUTS = "inputs";
    public static final String PREFIX_MODULE = "module_";
    public static final String PREFIX_OUTPUTS = "outputs";
    public static final String PREFIX_SDK = "sdk";
    public static final String TYPE_API = "api";
    public static final String TYPE_BARCODE = "barcode";
    public static final String TYPE_COUNTRY = "countries";
    public static final String TYPE_DOCUMENT = "document";
    public static final String TYPE_DYNAMIC_FORM = "dynamicForm";
    public static final String TYPE_DYNAMIC_FORM_V2 = "dynamicFormV2";
    public static final String TYPE_FACE = "face";
    public static final String TYPE_FORM = "form";
    public static final String TYPE_NFC = "nfc";
    public static final String TYPE_START = "start";
    public static final String TYPE_START_SESSION_RECORDING = "startSession";
    public static final String TYPE_STOP_SESSION_RECORDING = "stopSession";
    public static final String TYPE_VIDEO_STATEMENT = "videoStatement";
    public static final String TYPE_VIDEO_STATEMENT_V2 = "videoStatementV2";
    public static final String TYPE_WEBVIEW = "webview";
    public static final String UI_STYLE_ACTION_SHEET = "actionSheet";
    public static final String UI_STYLE_NONE = "none";
    public static final String UI_STYLE_POPUP = "popup";

    @SerializedName("id")
    private final String id;

    @SerializedName(AnalyticsLogger.Keys.NEXTSTEP)
    private String next;

    @SerializedName("previousStep")
    private final String previous;

    @SerializedName("properties")
    private final Properties properties;

    @SerializedName(WebViewFragment.ARG_SUB_TYPE)
    private final String subType;

    @SerializedName("type")
    private final String type;

    @SerializedName("uiStyle")
    private final String uiStyle;

    @SerializedName("variables")
    private final List<Variable> variables;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String END_STATE_APPROVE = "approve";
    public static final String END_STATE_DECLINE = "decline";
    private static final String[] END_STATES = {END_STATE_APPROVE, END_STATE_DECLINE, "manualReview"};
    private static final List<Properties.Section.Component.SupportedFile> DEFAULT_SUPPORTED_FILES = CollectionsKt.listOf((Object[]) new Properties.Section.Component.SupportedFile[]{new Properties.Section.Component.SupportedFile("images", "Pictures or Images", CollectionsKt.listOf((Object[]) new String[]{"jpg", "jpeg", "png"}), null, false, 24, null), new Properties.Section.Component.SupportedFile("documents", "PDF document", CollectionsKt.listOf("pdf"), null, false, 24, null)});

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component3, reason: from getter */
    public final String getSubType() {
        return this.subType;
    }

    /* renamed from: component4, reason: from getter */
    public final String getNext() {
        return this.next;
    }

    /* renamed from: component5, reason: from getter */
    public final String getPrevious() {
        return this.previous;
    }

    /* renamed from: component6, reason: from getter */
    public final Properties getProperties() {
        return this.properties;
    }

    /* renamed from: component7, reason: from getter */
    public final String getUiStyle() {
        return this.uiStyle;
    }

    public final List<Variable> component8() {
        return this.variables;
    }

    public final WorkflowModule copy(String id2, String type, String subType, String next, String previous, Properties properties, String uiStyle, List<Variable> variables) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(subType, "subType");
        return new WorkflowModule(id2, type, subType, next, previous, properties, uiStyle, variables);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WorkflowModule)) {
            return false;
        }
        WorkflowModule workflowModule = (WorkflowModule) other;
        return Intrinsics.areEqual(this.id, workflowModule.id) && Intrinsics.areEqual(this.type, workflowModule.type) && Intrinsics.areEqual(this.subType, workflowModule.subType) && Intrinsics.areEqual(this.next, workflowModule.next) && Intrinsics.areEqual(this.previous, workflowModule.previous) && Intrinsics.areEqual(this.properties, workflowModule.properties) && Intrinsics.areEqual(this.uiStyle, workflowModule.uiStyle) && Intrinsics.areEqual(this.variables, workflowModule.variables);
    }

    public int hashCode() {
        int hashCode = ((((this.id.hashCode() * 31) + this.type.hashCode()) * 31) + this.subType.hashCode()) * 31;
        String str = this.next;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.previous;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Properties properties = this.properties;
        int hashCode4 = (hashCode3 + (properties == null ? 0 : properties.hashCode())) * 31;
        String str3 = this.uiStyle;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        List<Variable> list = this.variables;
        return hashCode5 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "WorkflowModule(id=" + this.id + ", type=" + this.type + ", subType=" + this.subType + ", next=" + this.next + ", previous=" + this.previous + ", properties=" + this.properties + ", uiStyle=" + this.uiStyle + ", variables=" + this.variables + ')';
    }

    public WorkflowModule(String id2, String type, String subType, String str, String str2, Properties properties, String str3, List<Variable> list) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(subType, "subType");
        this.id = id2;
        this.type = type;
        this.subType = subType;
        this.next = str;
        this.previous = str2;
        this.properties = properties;
        this.uiStyle = str3;
        this.variables = list;
    }

    public /* synthetic */ WorkflowModule(String str, String str2, String str3, String str4, String str5, Properties properties, String str6, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : properties, (i & 64) != 0 ? null : str6, (i & 128) != 0 ? null : list);
    }

    public final String getId() {
        return this.id;
    }

    public final String getType() {
        return this.type;
    }

    public final String getSubType() {
        return this.subType;
    }

    public final String getNext() {
        return this.next;
    }

    public final void setNext(String str) {
        this.next = str;
    }

    public final String getPrevious() {
        return this.previous;
    }

    public final Properties getProperties() {
        return this.properties;
    }

    public final String getUiStyle() {
        return this.uiStyle;
    }

    public final List<Variable> getVariables() {
        return this.variables;
    }

    /* compiled from: WorkflowConfig.kt */
    @Metadata(d1 = {"\u0000\u0087\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0003\b \u0001\b\u0087\b\u0018\u00002\u00020\u0001:\u0012é\u0001ê\u0001ë\u0001ì\u0001í\u0001î\u0001ï\u0001ð\u0001ñ\u0001BÍ\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\t\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0006\u00124\b\u0002\u0010\u000f\u001a.\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t\u0018\u00010\u0010j\u0016\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t\u0018\u0001`\u0011\u0012P\b\u0002\u0010\u0012\u001aJ\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00140\u0010\u0018\u00010\u0013j,\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00140\u0010j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0014`\u0011\u0018\u0001`\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001f\u0012\b\b\u0002\u0010!\u001a\u00020\u0003\u0012\b\b\u0002\u0010\"\u001a\u00020\u0003\u0012\b\b\u0002\u0010#\u001a\u00020\u001a\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0006\u0012(\b\u0002\u0010&\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0015\u0012\b\b\u0002\u0010'\u001a\u00020\u0003\u0012\b\b\u0002\u0010(\u001a\u00020\u0003\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u001a\u0012\u0010\b\u0002\u0010*\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010\t\u0012\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001a0\t\u0012\b\b\u0002\u0010-\u001a\u00020\u0006\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u0006\u0012(\b\u0002\u0010/\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u000200\u0018\u00010\u0013j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u000200\u0018\u0001`\u0015\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u000102\u0012\b\b\u0002\u00103\u001a\u00020\u0003\u0012\b\b\u0002\u00104\u001a\u00020\u0003\u0012\b\b\u0002\u00105\u001a\u00020\u0003\u0012\u0010\b\u0002\u00106\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010\t\u0012\b\b\u0002\u00108\u001a\u00020\u0003\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u001a\u0012\b\b\u0002\u0010:\u001a\u00020\u0003\u0012\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u001f\u0012(\b\u0002\u0010<\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0015\u0012\b\b\u0002\u0010=\u001a\u00020\u0003\u0012\b\b\u0002\u0010>\u001a\u00020\u0003\u0012\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010C\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010\t\u0012\b\b\u0002\u0010D\u001a\u00020\u0006\u0012\u0016\b\u0002\u0010E\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020G\u0018\u00010F\u0012\b\b\u0002\u0010H\u001a\u00020\u001a\u0012\b\b\u0002\u0010I\u001a\u00020\u001a\u0012\u0014\b\u0002\u0010J\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060F\u0012\n\b\u0002\u0010K\u001a\u0004\u0018\u00010L\u0012\n\b\u0002\u0010M\u001a\u0004\u0018\u00010L\u0012\n\b\u0002\u0010N\u001a\u0004\u0018\u00010L\u0012\n\b\u0002\u0010O\u001a\u0004\u0018\u00010L\u0012\n\b\u0002\u0010P\u001a\u0004\u0018\u00010L\u0012\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010R\u0012\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010TJ\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001a0\tH\u0000¢\u0006\u0003\b¢\u0001J\n\u0010£\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¤\u0001\u001a\u00020\u0006HÆ\u0003J6\u0010¥\u0001\u001a.\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t\u0018\u00010\u0010j\u0016\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t\u0018\u0001`\u0011HÆ\u0003JR\u0010¦\u0001\u001aJ\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00140\u0010\u0018\u00010\u0013j,\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00140\u0010j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0014`\u0011\u0018\u0001`\u0015HÆ\u0003J\n\u0010§\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¨\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010©\u0001\u001a\u00020\u0003HÆ\u0003J\u0011\u0010ª\u0001\u001a\u0004\u0018\u00010\u001aHÆ\u0003¢\u0006\u0002\u0010aJ\n\u0010«\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010¬\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\n\u0010\u00ad\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010®\u0001\u001a\u00020\u0003HÆ\u0003J\u0011\u0010¯\u0001\u001a\u0004\u0018\u00010\u001fHÆ\u0003¢\u0006\u0002\u0010fJ\u0011\u0010°\u0001\u001a\u0004\u0018\u00010\u001fHÆ\u0003¢\u0006\u0002\u0010fJ\n\u0010±\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010²\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010³\u0001\u001a\u00020\u001aHÆ\u0003J\f\u0010´\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\f\u0010µ\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003J*\u0010¶\u0001\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0015HÆ\u0003J\n\u0010·\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¸\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¹\u0001\u001a\u00020\u0006HÆ\u0003J\u0011\u0010º\u0001\u001a\u0004\u0018\u00010\u001aHÆ\u0003¢\u0006\u0002\u0010aJ\u0012\u0010»\u0001\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010\tHÆ\u0003J\u0010\u0010¼\u0001\u001a\b\u0012\u0004\u0012\u00020\u001a0\tHÆ\u0003J\n\u0010½\u0001\u001a\u00020\u0006HÆ\u0003J\f\u0010¾\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003J*\u0010¿\u0001\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u000200\u0018\u00010\u0013j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u000200\u0018\u0001`\u0015HÆ\u0003J\f\u0010À\u0001\u001a\u0004\u0018\u000102HÆ\u0003J\n\u0010Á\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Â\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ã\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ä\u0001\u001a\u00020\u0003HÆ\u0003J\u0012\u0010Å\u0001\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010\tHÆ\u0003J\n\u0010Æ\u0001\u001a\u00020\u0003HÆ\u0003J\u0011\u0010Ç\u0001\u001a\u0004\u0018\u00010\u001aHÆ\u0003¢\u0006\u0002\u0010aJ\n\u0010È\u0001\u001a\u00020\u0003HÆ\u0003J\u0011\u0010É\u0001\u001a\u0004\u0018\u00010\u001fHÆ\u0003¢\u0006\u0002\u0010fJ*\u0010Ê\u0001\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0015HÆ\u0003J\n\u0010Ë\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010Ì\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010Í\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\f\u0010Î\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0012\u0010Ï\u0001\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\tHÆ\u0003J\f\u0010Ð\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\f\u0010Ñ\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0012\u0010Ò\u0001\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010\tHÆ\u0003J\n\u0010Ó\u0001\u001a\u00020\u0006HÆ\u0003J\u0018\u0010Ô\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020G\u0018\u00010FHÆ\u0003J\n\u0010Õ\u0001\u001a\u00020\u001aHÆ\u0003J\n\u0010Ö\u0001\u001a\u00020\u001aHÆ\u0003J\u0016\u0010×\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060FHÆ\u0003J\f\u0010Ø\u0001\u001a\u0004\u0018\u00010LHÆ\u0003J\f\u0010Ù\u0001\u001a\u0004\u0018\u00010LHÆ\u0003J\n\u0010Ú\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010Û\u0001\u001a\u0004\u0018\u00010LHÆ\u0003J\f\u0010Ü\u0001\u001a\u0004\u0018\u00010LHÆ\u0003J\f\u0010Ý\u0001\u001a\u0004\u0018\u00010LHÆ\u0003J\f\u0010Þ\u0001\u001a\u0004\u0018\u00010RHÆ\u0003J\f\u0010ß\u0001\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0012\u0010à\u0001\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\tHÆ\u0003J\n\u0010á\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010â\u0001\u001a\u00020\u0003HÆ\u0003JØ\u0007\u0010ã\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000624\b\u0002\u0010\u000f\u001a.\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t\u0018\u00010\u0010j\u0016\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t\u0018\u0001`\u00112P\b\u0002\u0010\u0012\u001aJ\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00140\u0010\u0018\u00010\u0013j,\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00140\u0010j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0014`\u0011\u0018\u0001`\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00032\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u001d\u001a\u00020\u00032\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\u00032\b\b\u0002\u0010#\u001a\u00020\u001a2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00062(\b\u0002\u0010&\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u00152\b\b\u0002\u0010'\u001a\u00020\u00032\b\b\u0002\u0010(\u001a\u00020\u00032\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u001a2\u0010\b\u0002\u0010*\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010\t2\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001a0\t2\b\b\u0002\u0010-\u001a\u00020\u00062\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u00062(\b\u0002\u0010/\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u000200\u0018\u00010\u0013j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u000200\u0018\u0001`\u00152\n\b\u0002\u00101\u001a\u0004\u0018\u0001022\b\b\u0002\u00103\u001a\u00020\u00032\b\b\u0002\u00104\u001a\u00020\u00032\b\b\u0002\u00105\u001a\u00020\u00032\u0010\b\u0002\u00106\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010\t2\b\b\u0002\u00108\u001a\u00020\u00032\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010:\u001a\u00020\u00032\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u001f2(\b\u0002\u0010<\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u00152\b\b\u0002\u0010=\u001a\u00020\u00032\b\b\u0002\u0010>\u001a\u00020\u00032\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010C\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010\t2\b\b\u0002\u0010D\u001a\u00020\u00062\u0016\b\u0002\u0010E\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020G\u0018\u00010F2\b\b\u0002\u0010H\u001a\u00020\u001a2\b\b\u0002\u0010I\u001a\u00020\u001a2\u0014\b\u0002\u0010J\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060F2\n\b\u0002\u0010K\u001a\u0004\u0018\u00010L2\n\b\u0002\u0010M\u001a\u0004\u0018\u00010L2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010L2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010L2\n\b\u0002\u0010P\u001a\u0004\u0018\u00010L2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010R2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0003\u0010ä\u0001J\u0015\u0010å\u0001\u001a\u00020\u00032\t\u0010æ\u0001\u001a\u0004\u0018\u000100HÖ\u0003J\n\u0010ç\u0001\u001a\u00020\u001aHÖ\u0001J\n\u0010è\u0001\u001a\u00020\u0006HÖ\u0001R\u0016\u0010\u001b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bU\u0010VR\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bW\u0010VR\u0016\u0010I\u001a\u00020\u001a8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bX\u0010YR\u0016\u0010H\u001a\u00020\u001a8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010YR\u001c\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001a0\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b[\u0010\\R\u0018\u0010%\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b]\u0010^R\u0016\u0010(\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b_\u0010VR\u001a\u0010)\u001a\u0004\u0018\u00010\u001a8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010b\u001a\u0004\b`\u0010aR\u001a\u00109\u001a\u0004\u0018\u00010\u001a8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010b\u001a\u0004\bc\u0010aR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bd\u0010VR\u001a\u0010\u001e\u001a\u0004\u0018\u00010\u001f8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010g\u001a\u0004\be\u0010fR\u001e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u0010\\R\u0016\u0010\u000e\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bi\u0010^R\u0018\u00101\u001a\u0004\u0018\u0001028\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bj\u0010kR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bl\u0010^R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bm\u0010VR\u0016\u0010\u0017\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bn\u0010VR\u0016\u0010\f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bo\u0010VRB\u0010\u000f\u001a.\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t\u0018\u00010\u0010j\u0016\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t\u0018\u0001`\u00118\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bp\u0010qR^\u0010\u0012\u001aJ\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00140\u0010\u0018\u00010\u0013j,\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00140\u0010j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0014`\u0011\u0018\u0001`\u00158\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\br\u0010sR\u0016\u0010\u001d\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bt\u0010VR\u0016\u0010=\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bu\u0010VR\u0018\u0010S\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bv\u0010^R\u001a\u0010 \u001a\u0004\u0018\u00010\u001f8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010g\u001a\u0004\bw\u0010fR\u0018\u0010M\u001a\u0004\u0018\u00010L8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bx\u0010yR\u001e\u0010C\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bz\u0010\\R\u0018\u0010@\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b{\u0010^R6\u0010&\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u00158\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b|\u0010sR\u0018\u0010.\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010^R\u0018\u0010K\u001a\u0004\u0018\u00010L8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b}\u0010yR\u0018\u0010?\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b~\u0010^R\u0018\u0010O\u001a\u0004\u0018\u00010L8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u007f\u0010yR\u0019\u0010B\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0080\u0001\u0010^R\u0017\u0010#\u001a\u00020\u001a8\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0081\u0001\u0010YR7\u0010<\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0013j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u00158\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0082\u0001\u0010sR\u0017\u0010:\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0083\u0001\u0010VR\u001b\u0010;\u001a\u0004\u0018\u00010\u001f8\u0006X\u0087\u0004¢\u0006\u000b\n\u0002\u0010g\u001a\u0005\b\u0084\u0001\u0010fR\u0017\u00105\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0085\u0001\u0010VR\u0017\u00104\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0086\u0001\u0010VR7\u0010/\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u000200\u0018\u00010\u0013j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u000200\u0018\u0001`\u00158\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0087\u0001\u0010sR\u001f\u0010*\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0088\u0001\u0010\\R\u0017\u0010\"\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0089\u0001\u0010VR\u001f\u00106\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u008a\u0001\u0010\\R\u0017\u00103\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u008b\u0001\u0010VR\u0017\u0010-\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u008c\u0001\u0010^R\u0017\u0010'\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u008d\u0001\u0010VR\u0017\u0010\u0016\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u008e\u0001\u0010VR\u0019\u0010N\u001a\u0004\u0018\u00010L8\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u008f\u0001\u0010yR\u0019\u0010A\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0090\u0001\u0010^R\u0017\u0010D\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0091\u0001\u0010^R\u001a\u0010Q\u001a\u0004\u0018\u00010R8\u0006X\u0087\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0092\u0001\u0010\u0093\u0001R&\u0010E\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020G\u0018\u00010F8\u0006X\u0087\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001R\u0017\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0096\u0001\u0010^R\u001f\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0097\u0001\u0010\\R\u0017\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0098\u0001\u0010VR\u0017\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0099\u0001\u0010VR\u0019\u0010$\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u009a\u0001\u0010^R\u0017\u00108\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u009b\u0001\u0010VR$\u0010J\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060F8\u0006X\u0087\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u009c\u0001\u0010\u0095\u0001R\u0017\u0010>\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u009d\u0001\u0010VR\u0017\u0010\u0018\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u009e\u0001\u0010VR\u001b\u0010\u0019\u001a\u0004\u0018\u00010\u001a8\u0006X\u0087\u0004¢\u0006\u000b\n\u0002\u0010b\u001a\u0005\b\u009f\u0001\u0010aR\u0019\u0010P\u001a\u0004\u0018\u00010L8\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b \u0001\u0010yR\u0017\u0010!\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\t\n\u0000\u001a\u0005\b¡\u0001\u0010V¨\u0006ò\u0001"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties;", "Ljava/io/Serializable;", "captureAudio", "", "uploadSession", UploadingFragment.ARG_KEY_STOP_MODULE_ID, "", "uploadIfFailed", "countries", "", "allowUpload", "supportedUploadFileTypes", "disableOCR", "disableBarcodeSkip", AnalyticsLogger.Keys.COUNTRY_ID, "documents", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "documentsOverride", "Ljava/util/HashMap;", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$DocumentsOverride;", "Lkotlin/collections/HashMap;", "showReview", "disableLiveness", "videoRecording", "videoRecordingDuration", "", "alertTextBox", "defaultCamera", "enableLookStraight", "captureTimeout", "", "faceDetectorTimeout", "zoomByDefault", "retryIfFaceNotPresent", "maxAttemptsForFaceNotPresent", "url", "apiType", "headers", "showInstruction", "autoCapture", "autoCaptureDuration", "requestParams", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$RequestParam;", "allowedStatusCodes", "showEndState", "isSuccess", "requestBody", "", "data", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Data;", "showBackButton", WebViewFragment.ARG_RELOAD_ON_REDIRECT_FAILURE, "openInAppBrowser", FormFragment.ARG_SECTIONS, "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section;", "useWebForm", "barcodeSkipDelay", "nfcShowSkipButton", "nfcSkipDelay", "nfcAuthentication", "enableOverlay", "validateSignature", "livenessUrl", "faceMatchUrl", "speechToTextMatchUrl", "logVideoStatementUrl", "faceMatchParams", "start", "statements", "", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Statement;", "allowedRestarts", "allowedAttempts", "userData", "liveness", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;", "faceMatch", "speechToTextMatch", "logVideoStatement", "videoUpload", "statement", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2;", "expiresAfter", "(ZZLjava/lang/String;ZLjava/util/List;ZLjava/util/List;ZZLjava/lang/String;Ljava/util/LinkedHashMap;Ljava/util/HashMap;ZZZLjava/lang/Integer;ZLjava/lang/String;ZLjava/lang/Long;Ljava/lang/Long;ZZILjava/lang/String;Ljava/lang/String;Ljava/util/HashMap;ZZLjava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/HashMap;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Data;ZZZLjava/util/List;ZLjava/lang/Integer;ZLjava/lang/Long;Ljava/util/HashMap;ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/Map;IILjava/util/Map;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2;Ljava/lang/String;)V", "getAlertTextBox", "()Z", "getAllowUpload", "getAllowedAttempts", "()I", "getAllowedRestarts", "getAllowedStatusCodes", "()Ljava/util/List;", "getApiType", "()Ljava/lang/String;", "getAutoCapture", "getAutoCaptureDuration", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getBarcodeSkipDelay", "getCaptureAudio", "getCaptureTimeout", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getCountries", "getCountryId", "getData", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Data;", "getDefaultCamera", "getDisableBarcodeSkip", "getDisableLiveness", "getDisableOCR", "getDocuments", "()Ljava/util/LinkedHashMap;", "getDocumentsOverride", "()Ljava/util/HashMap;", "getEnableLookStraight", "getEnableOverlay", "getExpiresAfter", "getFaceDetectorTimeout", "getFaceMatch", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;", "getFaceMatchParams", "getFaceMatchUrl", "getHeaders", "getLiveness", "getLivenessUrl", "getLogVideoStatement", "getLogVideoStatementUrl", "getMaxAttemptsForFaceNotPresent", "getNfcAuthentication", "getNfcShowSkipButton", "getNfcSkipDelay", "getOpenInAppBrowser", "getReloadOnRedirectFailure", "getRequestBody", "getRequestParams", "getRetryIfFaceNotPresent", "getSections", "getShowBackButton", "getShowEndState", "getShowInstruction", "getShowReview", "getSpeechToTextMatch", "getSpeechToTextMatchUrl", "getStart", "getStatement", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2;", "getStatements", "()Ljava/util/Map;", "getStopModuleId", "getSupportedUploadFileTypes", "getUploadIfFailed", "getUploadSession", "getUrl", "getUseWebForm", "getUserData", "getValidateSignature", "getVideoRecording", "getVideoRecordingDuration", "getVideoUpload", "getZoomByDefault", "allowedStatusCodes$hyperkyc_release", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component5", "component50", "component51", "component52", "component53", "component54", "component55", "component56", "component57", "component58", "component59", "component6", "component60", "component61", "component62", "component63", "component64", "component7", "component8", "component9", "copy", "(ZZLjava/lang/String;ZLjava/util/List;ZLjava/util/List;ZZLjava/lang/String;Ljava/util/LinkedHashMap;Ljava/util/HashMap;ZZZLjava/lang/Integer;ZLjava/lang/String;ZLjava/lang/Long;Ljava/lang/Long;ZZILjava/lang/String;Ljava/lang/String;Ljava/util/HashMap;ZZLjava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/HashMap;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Data;ZZZLjava/util/List;ZLjava/lang/Integer;ZLjava/lang/Long;Ljava/util/HashMap;ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/Map;IILjava/util/Map;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2;Ljava/lang/String;)Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties;", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "Check", "Checks", "Data", "DocumentsOverride", "RequestParam", "Section", "Statement", "StatementV2", "VideoStatementV2API", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class Properties implements Serializable {

        @SerializedName("alertTextBox")
        private final boolean alertTextBox;

        @SerializedName("allowUpload")
        private final boolean allowUpload;

        @SerializedName("allowedAttempts")
        private final int allowedAttempts;

        @SerializedName("allowedRestarts")
        private final int allowedRestarts;

        @SerializedName("allowedStatusCodes")
        private final List<Integer> allowedStatusCodes;

        @SerializedName("apiType")
        private final String apiType;

        @SerializedName("autoCapture")
        private final boolean autoCapture;

        @SerializedName("autoCaptureDuration")
        private final Integer autoCaptureDuration;

        @SerializedName("barcodeSkipDelay")
        private final Integer barcodeSkipDelay;

        @SerializedName("captureAudio")
        private final boolean captureAudio;

        @SerializedName("captureTimeout")
        private final Long captureTimeout;

        @SerializedName("countriesSupported")
        private final List<String> countries;

        @SerializedName(AnalyticsLogger.Keys.COUNTRY_ID)
        private final String countryId;

        @SerializedName("data")
        private final Data data;

        @SerializedName("defaultCamera")
        private final String defaultCamera;

        @SerializedName("disableBarcodeSkip")
        private final boolean disableBarcodeSkip;

        @SerializedName("disableLiveness")
        private final boolean disableLiveness;

        @SerializedName("disableOCR")
        private final boolean disableOCR;

        @SerializedName("documentsSupported")
        private final LinkedHashMap<String, List<String>> documents;

        @SerializedName("documentsOverride")
        private final HashMap<String, LinkedHashMap<String, DocumentsOverride>> documentsOverride;

        @SerializedName("enableLookStraight")
        private final boolean enableLookStraight;

        @SerializedName("enableOverlay")
        private final boolean enableOverlay;

        @SerializedName("expiresAfter")
        private final String expiresAfter;

        @SerializedName("faceDetectorTimeout")
        private final Long faceDetectorTimeout;

        @SerializedName("faceMatch")
        private final VideoStatementV2API faceMatch;

        @SerializedName("faceMatchParams")
        private final List<RequestParam> faceMatchParams;

        @SerializedName("faceMatchUrl")
        private final String faceMatchUrl;

        @SerializedName("headers")
        private final HashMap<String, String> headers;

        @SerializedName("isSuccess")
        private final String isSuccess;

        @SerializedName("liveness")
        private final VideoStatementV2API liveness;

        @SerializedName("livenessUrl")
        private final String livenessUrl;

        @SerializedName("logVideoStatement")
        private final VideoStatementV2API logVideoStatement;

        @SerializedName("logVideoStatementUrl")
        private final String logVideoStatementUrl;

        @SerializedName("maxAttemptsForFaceNotPresent")
        private final int maxAttemptsForFaceNotPresent;

        @SerializedName("nfcAuthentication")
        private final HashMap<String, String> nfcAuthentication;

        @SerializedName("nfcShowSkipButton")
        private final boolean nfcShowSkipButton;

        @SerializedName("nfcSkipDelay")
        private final Long nfcSkipDelay;

        @SerializedName("openInAppBrowser")
        private final boolean openInAppBrowser;

        @SerializedName(WebViewFragment.ARG_RELOAD_ON_REDIRECT_FAILURE)
        private final boolean reloadOnRedirectFailure;

        @SerializedName("requestBody")
        private final HashMap<String, Object> requestBody;

        @SerializedName("requestParameters")
        private final List<RequestParam> requestParams;

        @SerializedName("retryIfFaceNotPresent")
        private final boolean retryIfFaceNotPresent;

        @SerializedName(FormFragment.ARG_SECTIONS)
        private final List<Section> sections;

        @SerializedName("showBackButton")
        private final boolean showBackButton;

        @SerializedName("showEndState")
        private final String showEndState;

        @SerializedName("showInstruction")
        private final boolean showInstruction;

        @SerializedName("showReview")
        private final boolean showReview;

        @SerializedName("speechToTextMatch")
        private final VideoStatementV2API speechToTextMatch;

        @SerializedName("speechToTextMatchUrl")
        private final String speechToTextMatchUrl;

        @SerializedName("start")
        private final String start;

        @SerializedName("statement")
        private final StatementV2 statement;

        @SerializedName("statements")
        private final Map<String, Statement> statements;

        @SerializedName(UploadingFragment.ARG_KEY_STOP_MODULE_ID)
        private final String stopModuleId;

        @SerializedName("supportedUploadFileTypes")
        private final List<String> supportedUploadFileTypes;

        @SerializedName("uploadIfFailed")
        private final boolean uploadIfFailed;

        @SerializedName("uploadSession")
        private final boolean uploadSession;

        @SerializedName("url")
        private final String url;

        @SerializedName("useWebForm")
        private final boolean useWebForm;

        @SerializedName("userData")
        private final Map<String, String> userData;

        @SerializedName("validateSignature")
        private final boolean validateSignature;

        @SerializedName("videoRecording")
        private final boolean videoRecording;

        @SerializedName("videoRecordingDuration")
        private final Integer videoRecordingDuration;

        @SerializedName("videoUpload")
        private final VideoStatementV2API videoUpload;

        @SerializedName("zoomByDefault")
        private final boolean zoomByDefault;

        public Properties() {
            this(false, false, null, false, null, false, null, false, false, null, null, null, false, false, false, null, false, null, false, null, null, false, false, 0, null, null, null, false, false, null, null, null, null, null, null, null, false, false, false, null, false, null, false, null, null, false, false, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, null, -1, -1, null);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getCaptureAudio() {
            return this.captureAudio;
        }

        /* renamed from: component10, reason: from getter */
        public final String getCountryId() {
            return this.countryId;
        }

        public final LinkedHashMap<String, List<String>> component11() {
            return this.documents;
        }

        public final HashMap<String, LinkedHashMap<String, DocumentsOverride>> component12() {
            return this.documentsOverride;
        }

        /* renamed from: component13, reason: from getter */
        public final boolean getShowReview() {
            return this.showReview;
        }

        /* renamed from: component14, reason: from getter */
        public final boolean getDisableLiveness() {
            return this.disableLiveness;
        }

        /* renamed from: component15, reason: from getter */
        public final boolean getVideoRecording() {
            return this.videoRecording;
        }

        /* renamed from: component16, reason: from getter */
        public final Integer getVideoRecordingDuration() {
            return this.videoRecordingDuration;
        }

        /* renamed from: component17, reason: from getter */
        public final boolean getAlertTextBox() {
            return this.alertTextBox;
        }

        /* renamed from: component18, reason: from getter */
        public final String getDefaultCamera() {
            return this.defaultCamera;
        }

        /* renamed from: component19, reason: from getter */
        public final boolean getEnableLookStraight() {
            return this.enableLookStraight;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getUploadSession() {
            return this.uploadSession;
        }

        /* renamed from: component20, reason: from getter */
        public final Long getCaptureTimeout() {
            return this.captureTimeout;
        }

        /* renamed from: component21, reason: from getter */
        public final Long getFaceDetectorTimeout() {
            return this.faceDetectorTimeout;
        }

        /* renamed from: component22, reason: from getter */
        public final boolean getZoomByDefault() {
            return this.zoomByDefault;
        }

        /* renamed from: component23, reason: from getter */
        public final boolean getRetryIfFaceNotPresent() {
            return this.retryIfFaceNotPresent;
        }

        /* renamed from: component24, reason: from getter */
        public final int getMaxAttemptsForFaceNotPresent() {
            return this.maxAttemptsForFaceNotPresent;
        }

        /* renamed from: component25, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* renamed from: component26, reason: from getter */
        public final String getApiType() {
            return this.apiType;
        }

        public final HashMap<String, String> component27() {
            return this.headers;
        }

        /* renamed from: component28, reason: from getter */
        public final boolean getShowInstruction() {
            return this.showInstruction;
        }

        /* renamed from: component29, reason: from getter */
        public final boolean getAutoCapture() {
            return this.autoCapture;
        }

        /* renamed from: component3, reason: from getter */
        public final String getStopModuleId() {
            return this.stopModuleId;
        }

        /* renamed from: component30, reason: from getter */
        public final Integer getAutoCaptureDuration() {
            return this.autoCaptureDuration;
        }

        public final List<RequestParam> component31() {
            return this.requestParams;
        }

        public final List<Integer> component32() {
            return this.allowedStatusCodes;
        }

        /* renamed from: component33, reason: from getter */
        public final String getShowEndState() {
            return this.showEndState;
        }

        /* renamed from: component34, reason: from getter */
        public final String getIsSuccess() {
            return this.isSuccess;
        }

        public final HashMap<String, Object> component35() {
            return this.requestBody;
        }

        /* renamed from: component36, reason: from getter */
        public final Data getData() {
            return this.data;
        }

        /* renamed from: component37, reason: from getter */
        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        /* renamed from: component38, reason: from getter */
        public final boolean getReloadOnRedirectFailure() {
            return this.reloadOnRedirectFailure;
        }

        /* renamed from: component39, reason: from getter */
        public final boolean getOpenInAppBrowser() {
            return this.openInAppBrowser;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getUploadIfFailed() {
            return this.uploadIfFailed;
        }

        public final List<Section> component40() {
            return this.sections;
        }

        /* renamed from: component41, reason: from getter */
        public final boolean getUseWebForm() {
            return this.useWebForm;
        }

        /* renamed from: component42, reason: from getter */
        public final Integer getBarcodeSkipDelay() {
            return this.barcodeSkipDelay;
        }

        /* renamed from: component43, reason: from getter */
        public final boolean getNfcShowSkipButton() {
            return this.nfcShowSkipButton;
        }

        /* renamed from: component44, reason: from getter */
        public final Long getNfcSkipDelay() {
            return this.nfcSkipDelay;
        }

        public final HashMap<String, String> component45() {
            return this.nfcAuthentication;
        }

        /* renamed from: component46, reason: from getter */
        public final boolean getEnableOverlay() {
            return this.enableOverlay;
        }

        /* renamed from: component47, reason: from getter */
        public final boolean getValidateSignature() {
            return this.validateSignature;
        }

        /* renamed from: component48, reason: from getter */
        public final String getLivenessUrl() {
            return this.livenessUrl;
        }

        /* renamed from: component49, reason: from getter */
        public final String getFaceMatchUrl() {
            return this.faceMatchUrl;
        }

        public final List<String> component5() {
            return this.countries;
        }

        /* renamed from: component50, reason: from getter */
        public final String getSpeechToTextMatchUrl() {
            return this.speechToTextMatchUrl;
        }

        /* renamed from: component51, reason: from getter */
        public final String getLogVideoStatementUrl() {
            return this.logVideoStatementUrl;
        }

        public final List<RequestParam> component52() {
            return this.faceMatchParams;
        }

        /* renamed from: component53, reason: from getter */
        public final String getStart() {
            return this.start;
        }

        public final Map<String, Statement> component54() {
            return this.statements;
        }

        /* renamed from: component55, reason: from getter */
        public final int getAllowedRestarts() {
            return this.allowedRestarts;
        }

        /* renamed from: component56, reason: from getter */
        public final int getAllowedAttempts() {
            return this.allowedAttempts;
        }

        public final Map<String, String> component57() {
            return this.userData;
        }

        /* renamed from: component58, reason: from getter */
        public final VideoStatementV2API getLiveness() {
            return this.liveness;
        }

        /* renamed from: component59, reason: from getter */
        public final VideoStatementV2API getFaceMatch() {
            return this.faceMatch;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getAllowUpload() {
            return this.allowUpload;
        }

        /* renamed from: component60, reason: from getter */
        public final VideoStatementV2API getSpeechToTextMatch() {
            return this.speechToTextMatch;
        }

        /* renamed from: component61, reason: from getter */
        public final VideoStatementV2API getLogVideoStatement() {
            return this.logVideoStatement;
        }

        /* renamed from: component62, reason: from getter */
        public final VideoStatementV2API getVideoUpload() {
            return this.videoUpload;
        }

        /* renamed from: component63, reason: from getter */
        public final StatementV2 getStatement() {
            return this.statement;
        }

        /* renamed from: component64, reason: from getter */
        public final String getExpiresAfter() {
            return this.expiresAfter;
        }

        public final List<String> component7() {
            return this.supportedUploadFileTypes;
        }

        /* renamed from: component8, reason: from getter */
        public final boolean getDisableOCR() {
            return this.disableOCR;
        }

        /* renamed from: component9, reason: from getter */
        public final boolean getDisableBarcodeSkip() {
            return this.disableBarcodeSkip;
        }

        public final Properties copy(boolean captureAudio, boolean uploadSession, String stopModuleId, boolean uploadIfFailed, List<String> countries, boolean allowUpload, List<String> supportedUploadFileTypes, boolean disableOCR, boolean disableBarcodeSkip, String countryId, LinkedHashMap<String, List<String>> documents, HashMap<String, LinkedHashMap<String, DocumentsOverride>> documentsOverride, boolean showReview, boolean disableLiveness, boolean videoRecording, Integer videoRecordingDuration, boolean alertTextBox, String defaultCamera, boolean enableLookStraight, Long captureTimeout, Long faceDetectorTimeout, boolean zoomByDefault, boolean retryIfFaceNotPresent, int maxAttemptsForFaceNotPresent, String url, String apiType, HashMap<String, String> headers, boolean showInstruction, boolean autoCapture, Integer autoCaptureDuration, List<RequestParam> requestParams, List<Integer> allowedStatusCodes, String showEndState, String isSuccess, HashMap<String, Object> requestBody, Data data, boolean showBackButton, boolean reloadOnRedirectFailure, boolean openInAppBrowser, List<Section> sections, boolean useWebForm, Integer barcodeSkipDelay, boolean nfcShowSkipButton, Long nfcSkipDelay, HashMap<String, String> nfcAuthentication, boolean enableOverlay, boolean validateSignature, String livenessUrl, String faceMatchUrl, String speechToTextMatchUrl, String logVideoStatementUrl, List<RequestParam> faceMatchParams, String start, Map<String, Statement> statements, int allowedRestarts, int allowedAttempts, Map<String, String> userData, VideoStatementV2API liveness, VideoStatementV2API faceMatch, VideoStatementV2API speechToTextMatch, VideoStatementV2API logVideoStatement, VideoStatementV2API videoUpload, StatementV2 statement, String expiresAfter) {
            Intrinsics.checkNotNullParameter(stopModuleId, "stopModuleId");
            Intrinsics.checkNotNullParameter(countryId, "countryId");
            Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
            Intrinsics.checkNotNullParameter(showEndState, "showEndState");
            Intrinsics.checkNotNullParameter(start, "start");
            Intrinsics.checkNotNullParameter(userData, "userData");
            return new Properties(captureAudio, uploadSession, stopModuleId, uploadIfFailed, countries, allowUpload, supportedUploadFileTypes, disableOCR, disableBarcodeSkip, countryId, documents, documentsOverride, showReview, disableLiveness, videoRecording, videoRecordingDuration, alertTextBox, defaultCamera, enableLookStraight, captureTimeout, faceDetectorTimeout, zoomByDefault, retryIfFaceNotPresent, maxAttemptsForFaceNotPresent, url, apiType, headers, showInstruction, autoCapture, autoCaptureDuration, requestParams, allowedStatusCodes, showEndState, isSuccess, requestBody, data, showBackButton, reloadOnRedirectFailure, openInAppBrowser, sections, useWebForm, barcodeSkipDelay, nfcShowSkipButton, nfcSkipDelay, nfcAuthentication, enableOverlay, validateSignature, livenessUrl, faceMatchUrl, speechToTextMatchUrl, logVideoStatementUrl, faceMatchParams, start, statements, allowedRestarts, allowedAttempts, userData, liveness, faceMatch, speechToTextMatch, logVideoStatement, videoUpload, statement, expiresAfter);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Properties)) {
                return false;
            }
            Properties properties = (Properties) other;
            return this.captureAudio == properties.captureAudio && this.uploadSession == properties.uploadSession && Intrinsics.areEqual(this.stopModuleId, properties.stopModuleId) && this.uploadIfFailed == properties.uploadIfFailed && Intrinsics.areEqual(this.countries, properties.countries) && this.allowUpload == properties.allowUpload && Intrinsics.areEqual(this.supportedUploadFileTypes, properties.supportedUploadFileTypes) && this.disableOCR == properties.disableOCR && this.disableBarcodeSkip == properties.disableBarcodeSkip && Intrinsics.areEqual(this.countryId, properties.countryId) && Intrinsics.areEqual(this.documents, properties.documents) && Intrinsics.areEqual(this.documentsOverride, properties.documentsOverride) && this.showReview == properties.showReview && this.disableLiveness == properties.disableLiveness && this.videoRecording == properties.videoRecording && Intrinsics.areEqual(this.videoRecordingDuration, properties.videoRecordingDuration) && this.alertTextBox == properties.alertTextBox && Intrinsics.areEqual(this.defaultCamera, properties.defaultCamera) && this.enableLookStraight == properties.enableLookStraight && Intrinsics.areEqual(this.captureTimeout, properties.captureTimeout) && Intrinsics.areEqual(this.faceDetectorTimeout, properties.faceDetectorTimeout) && this.zoomByDefault == properties.zoomByDefault && this.retryIfFaceNotPresent == properties.retryIfFaceNotPresent && this.maxAttemptsForFaceNotPresent == properties.maxAttemptsForFaceNotPresent && Intrinsics.areEqual(this.url, properties.url) && Intrinsics.areEqual(this.apiType, properties.apiType) && Intrinsics.areEqual(this.headers, properties.headers) && this.showInstruction == properties.showInstruction && this.autoCapture == properties.autoCapture && Intrinsics.areEqual(this.autoCaptureDuration, properties.autoCaptureDuration) && Intrinsics.areEqual(this.requestParams, properties.requestParams) && Intrinsics.areEqual(this.allowedStatusCodes, properties.allowedStatusCodes) && Intrinsics.areEqual(this.showEndState, properties.showEndState) && Intrinsics.areEqual(this.isSuccess, properties.isSuccess) && Intrinsics.areEqual(this.requestBody, properties.requestBody) && Intrinsics.areEqual(this.data, properties.data) && this.showBackButton == properties.showBackButton && this.reloadOnRedirectFailure == properties.reloadOnRedirectFailure && this.openInAppBrowser == properties.openInAppBrowser && Intrinsics.areEqual(this.sections, properties.sections) && this.useWebForm == properties.useWebForm && Intrinsics.areEqual(this.barcodeSkipDelay, properties.barcodeSkipDelay) && this.nfcShowSkipButton == properties.nfcShowSkipButton && Intrinsics.areEqual(this.nfcSkipDelay, properties.nfcSkipDelay) && Intrinsics.areEqual(this.nfcAuthentication, properties.nfcAuthentication) && this.enableOverlay == properties.enableOverlay && this.validateSignature == properties.validateSignature && Intrinsics.areEqual(this.livenessUrl, properties.livenessUrl) && Intrinsics.areEqual(this.faceMatchUrl, properties.faceMatchUrl) && Intrinsics.areEqual(this.speechToTextMatchUrl, properties.speechToTextMatchUrl) && Intrinsics.areEqual(this.logVideoStatementUrl, properties.logVideoStatementUrl) && Intrinsics.areEqual(this.faceMatchParams, properties.faceMatchParams) && Intrinsics.areEqual(this.start, properties.start) && Intrinsics.areEqual(this.statements, properties.statements) && this.allowedRestarts == properties.allowedRestarts && this.allowedAttempts == properties.allowedAttempts && Intrinsics.areEqual(this.userData, properties.userData) && Intrinsics.areEqual(this.liveness, properties.liveness) && Intrinsics.areEqual(this.faceMatch, properties.faceMatch) && Intrinsics.areEqual(this.speechToTextMatch, properties.speechToTextMatch) && Intrinsics.areEqual(this.logVideoStatement, properties.logVideoStatement) && Intrinsics.areEqual(this.videoUpload, properties.videoUpload) && Intrinsics.areEqual(this.statement, properties.statement) && Intrinsics.areEqual(this.expiresAfter, properties.expiresAfter);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v128 */
        /* JADX WARN: Type inference failed for: r0v129 */
        /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v107, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v14, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v16, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v26, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v28, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v30, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v35, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v4, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v40, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v48, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v50, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v62, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v64, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v85, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v87, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v89, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v9, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v94, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v99, types: [boolean] */
        public int hashCode() {
            boolean z = this.captureAudio;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            ?? r2 = this.uploadSession;
            int i2 = r2;
            if (r2 != 0) {
                i2 = 1;
            }
            int hashCode = (((i + i2) * 31) + this.stopModuleId.hashCode()) * 31;
            ?? r22 = this.uploadIfFailed;
            int i3 = r22;
            if (r22 != 0) {
                i3 = 1;
            }
            int i4 = (hashCode + i3) * 31;
            List<String> list = this.countries;
            int hashCode2 = (i4 + (list == null ? 0 : list.hashCode())) * 31;
            ?? r23 = this.allowUpload;
            int i5 = r23;
            if (r23 != 0) {
                i5 = 1;
            }
            int i6 = (hashCode2 + i5) * 31;
            List<String> list2 = this.supportedUploadFileTypes;
            int hashCode3 = (i6 + (list2 == null ? 0 : list2.hashCode())) * 31;
            ?? r24 = this.disableOCR;
            int i7 = r24;
            if (r24 != 0) {
                i7 = 1;
            }
            int i8 = (hashCode3 + i7) * 31;
            ?? r25 = this.disableBarcodeSkip;
            int i9 = r25;
            if (r25 != 0) {
                i9 = 1;
            }
            int hashCode4 = (((i8 + i9) * 31) + this.countryId.hashCode()) * 31;
            LinkedHashMap<String, List<String>> linkedHashMap = this.documents;
            int hashCode5 = (hashCode4 + (linkedHashMap == null ? 0 : linkedHashMap.hashCode())) * 31;
            HashMap<String, LinkedHashMap<String, DocumentsOverride>> hashMap = this.documentsOverride;
            int hashCode6 = (hashCode5 + (hashMap == null ? 0 : hashMap.hashCode())) * 31;
            ?? r26 = this.showReview;
            int i10 = r26;
            if (r26 != 0) {
                i10 = 1;
            }
            int i11 = (hashCode6 + i10) * 31;
            ?? r27 = this.disableLiveness;
            int i12 = r27;
            if (r27 != 0) {
                i12 = 1;
            }
            int i13 = (i11 + i12) * 31;
            ?? r28 = this.videoRecording;
            int i14 = r28;
            if (r28 != 0) {
                i14 = 1;
            }
            int i15 = (i13 + i14) * 31;
            Integer num = this.videoRecordingDuration;
            int hashCode7 = (i15 + (num == null ? 0 : num.hashCode())) * 31;
            ?? r29 = this.alertTextBox;
            int i16 = r29;
            if (r29 != 0) {
                i16 = 1;
            }
            int i17 = (hashCode7 + i16) * 31;
            String str = this.defaultCamera;
            int hashCode8 = (i17 + (str == null ? 0 : str.hashCode())) * 31;
            ?? r210 = this.enableLookStraight;
            int i18 = r210;
            if (r210 != 0) {
                i18 = 1;
            }
            int i19 = (hashCode8 + i18) * 31;
            Long l = this.captureTimeout;
            int hashCode9 = (i19 + (l == null ? 0 : l.hashCode())) * 31;
            Long l2 = this.faceDetectorTimeout;
            int hashCode10 = (hashCode9 + (l2 == null ? 0 : l2.hashCode())) * 31;
            ?? r211 = this.zoomByDefault;
            int i20 = r211;
            if (r211 != 0) {
                i20 = 1;
            }
            int i21 = (hashCode10 + i20) * 31;
            ?? r212 = this.retryIfFaceNotPresent;
            int i22 = r212;
            if (r212 != 0) {
                i22 = 1;
            }
            int i23 = (((i21 + i22) * 31) + this.maxAttemptsForFaceNotPresent) * 31;
            String str2 = this.url;
            int hashCode11 = (i23 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.apiType;
            int hashCode12 = (hashCode11 + (str3 == null ? 0 : str3.hashCode())) * 31;
            HashMap<String, String> hashMap2 = this.headers;
            int hashCode13 = (hashCode12 + (hashMap2 == null ? 0 : hashMap2.hashCode())) * 31;
            ?? r213 = this.showInstruction;
            int i24 = r213;
            if (r213 != 0) {
                i24 = 1;
            }
            int i25 = (hashCode13 + i24) * 31;
            ?? r214 = this.autoCapture;
            int i26 = r214;
            if (r214 != 0) {
                i26 = 1;
            }
            int i27 = (i25 + i26) * 31;
            Integer num2 = this.autoCaptureDuration;
            int hashCode14 = (i27 + (num2 == null ? 0 : num2.hashCode())) * 31;
            List<RequestParam> list3 = this.requestParams;
            int hashCode15 = (((((hashCode14 + (list3 == null ? 0 : list3.hashCode())) * 31) + this.allowedStatusCodes.hashCode()) * 31) + this.showEndState.hashCode()) * 31;
            String str4 = this.isSuccess;
            int hashCode16 = (hashCode15 + (str4 == null ? 0 : str4.hashCode())) * 31;
            HashMap<String, Object> hashMap3 = this.requestBody;
            int hashCode17 = (hashCode16 + (hashMap3 == null ? 0 : hashMap3.hashCode())) * 31;
            Data data = this.data;
            int hashCode18 = (hashCode17 + (data == null ? 0 : data.hashCode())) * 31;
            ?? r215 = this.showBackButton;
            int i28 = r215;
            if (r215 != 0) {
                i28 = 1;
            }
            int i29 = (hashCode18 + i28) * 31;
            ?? r216 = this.reloadOnRedirectFailure;
            int i30 = r216;
            if (r216 != 0) {
                i30 = 1;
            }
            int i31 = (i29 + i30) * 31;
            ?? r217 = this.openInAppBrowser;
            int i32 = r217;
            if (r217 != 0) {
                i32 = 1;
            }
            int i33 = (i31 + i32) * 31;
            List<Section> list4 = this.sections;
            int hashCode19 = (i33 + (list4 == null ? 0 : list4.hashCode())) * 31;
            ?? r218 = this.useWebForm;
            int i34 = r218;
            if (r218 != 0) {
                i34 = 1;
            }
            int i35 = (hashCode19 + i34) * 31;
            Integer num3 = this.barcodeSkipDelay;
            int hashCode20 = (i35 + (num3 == null ? 0 : num3.hashCode())) * 31;
            ?? r219 = this.nfcShowSkipButton;
            int i36 = r219;
            if (r219 != 0) {
                i36 = 1;
            }
            int i37 = (hashCode20 + i36) * 31;
            Long l3 = this.nfcSkipDelay;
            int hashCode21 = (i37 + (l3 == null ? 0 : l3.hashCode())) * 31;
            HashMap<String, String> hashMap4 = this.nfcAuthentication;
            int hashCode22 = (hashCode21 + (hashMap4 == null ? 0 : hashMap4.hashCode())) * 31;
            ?? r220 = this.enableOverlay;
            int i38 = r220;
            if (r220 != 0) {
                i38 = 1;
            }
            int i39 = (hashCode22 + i38) * 31;
            boolean z2 = this.validateSignature;
            int i40 = (i39 + (z2 ? 1 : z2 ? 1 : 0)) * 31;
            String str5 = this.livenessUrl;
            int hashCode23 = (i40 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.faceMatchUrl;
            int hashCode24 = (hashCode23 + (str6 == null ? 0 : str6.hashCode())) * 31;
            String str7 = this.speechToTextMatchUrl;
            int hashCode25 = (hashCode24 + (str7 == null ? 0 : str7.hashCode())) * 31;
            String str8 = this.logVideoStatementUrl;
            int hashCode26 = (hashCode25 + (str8 == null ? 0 : str8.hashCode())) * 31;
            List<RequestParam> list5 = this.faceMatchParams;
            int hashCode27 = (((hashCode26 + (list5 == null ? 0 : list5.hashCode())) * 31) + this.start.hashCode()) * 31;
            Map<String, Statement> map = this.statements;
            int hashCode28 = (((((((hashCode27 + (map == null ? 0 : map.hashCode())) * 31) + this.allowedRestarts) * 31) + this.allowedAttempts) * 31) + this.userData.hashCode()) * 31;
            VideoStatementV2API videoStatementV2API = this.liveness;
            int hashCode29 = (hashCode28 + (videoStatementV2API == null ? 0 : videoStatementV2API.hashCode())) * 31;
            VideoStatementV2API videoStatementV2API2 = this.faceMatch;
            int hashCode30 = (hashCode29 + (videoStatementV2API2 == null ? 0 : videoStatementV2API2.hashCode())) * 31;
            VideoStatementV2API videoStatementV2API3 = this.speechToTextMatch;
            int hashCode31 = (hashCode30 + (videoStatementV2API3 == null ? 0 : videoStatementV2API3.hashCode())) * 31;
            VideoStatementV2API videoStatementV2API4 = this.logVideoStatement;
            int hashCode32 = (hashCode31 + (videoStatementV2API4 == null ? 0 : videoStatementV2API4.hashCode())) * 31;
            VideoStatementV2API videoStatementV2API5 = this.videoUpload;
            int hashCode33 = (hashCode32 + (videoStatementV2API5 == null ? 0 : videoStatementV2API5.hashCode())) * 31;
            StatementV2 statementV2 = this.statement;
            int hashCode34 = (hashCode33 + (statementV2 == null ? 0 : statementV2.hashCode())) * 31;
            String str9 = this.expiresAfter;
            return hashCode34 + (str9 != null ? str9.hashCode() : 0);
        }

        public String toString() {
            return "Properties(captureAudio=" + this.captureAudio + ", uploadSession=" + this.uploadSession + ", stopModuleId=" + this.stopModuleId + ", uploadIfFailed=" + this.uploadIfFailed + ", countries=" + this.countries + ", allowUpload=" + this.allowUpload + ", supportedUploadFileTypes=" + this.supportedUploadFileTypes + ", disableOCR=" + this.disableOCR + ", disableBarcodeSkip=" + this.disableBarcodeSkip + ", countryId=" + this.countryId + ", documents=" + this.documents + ", documentsOverride=" + this.documentsOverride + ", showReview=" + this.showReview + ", disableLiveness=" + this.disableLiveness + ", videoRecording=" + this.videoRecording + ", videoRecordingDuration=" + this.videoRecordingDuration + ", alertTextBox=" + this.alertTextBox + ", defaultCamera=" + this.defaultCamera + ", enableLookStraight=" + this.enableLookStraight + ", captureTimeout=" + this.captureTimeout + ", faceDetectorTimeout=" + this.faceDetectorTimeout + ", zoomByDefault=" + this.zoomByDefault + ", retryIfFaceNotPresent=" + this.retryIfFaceNotPresent + ", maxAttemptsForFaceNotPresent=" + this.maxAttemptsForFaceNotPresent + ", url=" + this.url + ", apiType=" + this.apiType + ", headers=" + this.headers + ", showInstruction=" + this.showInstruction + ", autoCapture=" + this.autoCapture + ", autoCaptureDuration=" + this.autoCaptureDuration + ", requestParams=" + this.requestParams + ", allowedStatusCodes=" + this.allowedStatusCodes + ", showEndState=" + this.showEndState + ", isSuccess=" + this.isSuccess + ", requestBody=" + this.requestBody + ", data=" + this.data + ", showBackButton=" + this.showBackButton + ", reloadOnRedirectFailure=" + this.reloadOnRedirectFailure + ", openInAppBrowser=" + this.openInAppBrowser + ", sections=" + this.sections + ", useWebForm=" + this.useWebForm + ", barcodeSkipDelay=" + this.barcodeSkipDelay + ", nfcShowSkipButton=" + this.nfcShowSkipButton + ", nfcSkipDelay=" + this.nfcSkipDelay + ", nfcAuthentication=" + this.nfcAuthentication + ", enableOverlay=" + this.enableOverlay + ", validateSignature=" + this.validateSignature + ", livenessUrl=" + this.livenessUrl + ", faceMatchUrl=" + this.faceMatchUrl + ", speechToTextMatchUrl=" + this.speechToTextMatchUrl + ", logVideoStatementUrl=" + this.logVideoStatementUrl + ", faceMatchParams=" + this.faceMatchParams + ", start=" + this.start + ", statements=" + this.statements + ", allowedRestarts=" + this.allowedRestarts + ", allowedAttempts=" + this.allowedAttempts + ", userData=" + this.userData + ", liveness=" + this.liveness + ", faceMatch=" + this.faceMatch + ", speechToTextMatch=" + this.speechToTextMatch + ", logVideoStatement=" + this.logVideoStatement + ", videoUpload=" + this.videoUpload + ", statement=" + this.statement + ", expiresAfter=" + this.expiresAfter + ')';
        }

        public Properties(boolean z, boolean z2, String stopModuleId, boolean z3, List<String> list, boolean z4, List<String> list2, boolean z5, boolean z6, String countryId, LinkedHashMap<String, List<String>> linkedHashMap, HashMap<String, LinkedHashMap<String, DocumentsOverride>> hashMap, boolean z7, boolean z8, boolean z9, Integer num, boolean z10, String str, boolean z11, Long l, Long l2, boolean z12, boolean z13, int i, String str2, String str3, HashMap<String, String> hashMap2, boolean z14, boolean z15, Integer num2, List<RequestParam> list3, List<Integer> allowedStatusCodes, String showEndState, String str4, HashMap<String, Object> hashMap3, Data data, boolean z16, boolean z17, boolean z18, List<Section> list4, boolean z19, Integer num3, boolean z20, Long l3, HashMap<String, String> hashMap4, boolean z21, boolean z22, String str5, String str6, String str7, String str8, List<RequestParam> list5, String start, Map<String, Statement> map, int i2, int i3, Map<String, String> userData, VideoStatementV2API videoStatementV2API, VideoStatementV2API videoStatementV2API2, VideoStatementV2API videoStatementV2API3, VideoStatementV2API videoStatementV2API4, VideoStatementV2API videoStatementV2API5, StatementV2 statementV2, String str9) {
            Intrinsics.checkNotNullParameter(stopModuleId, "stopModuleId");
            Intrinsics.checkNotNullParameter(countryId, "countryId");
            Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
            Intrinsics.checkNotNullParameter(showEndState, "showEndState");
            Intrinsics.checkNotNullParameter(start, "start");
            Intrinsics.checkNotNullParameter(userData, "userData");
            this.captureAudio = z;
            this.uploadSession = z2;
            this.stopModuleId = stopModuleId;
            this.uploadIfFailed = z3;
            this.countries = list;
            this.allowUpload = z4;
            this.supportedUploadFileTypes = list2;
            this.disableOCR = z5;
            this.disableBarcodeSkip = z6;
            this.countryId = countryId;
            this.documents = linkedHashMap;
            this.documentsOverride = hashMap;
            this.showReview = z7;
            this.disableLiveness = z8;
            this.videoRecording = z9;
            this.videoRecordingDuration = num;
            this.alertTextBox = z10;
            this.defaultCamera = str;
            this.enableLookStraight = z11;
            this.captureTimeout = l;
            this.faceDetectorTimeout = l2;
            this.zoomByDefault = z12;
            this.retryIfFaceNotPresent = z13;
            this.maxAttemptsForFaceNotPresent = i;
            this.url = str2;
            this.apiType = str3;
            this.headers = hashMap2;
            this.showInstruction = z14;
            this.autoCapture = z15;
            this.autoCaptureDuration = num2;
            this.requestParams = list3;
            this.allowedStatusCodes = allowedStatusCodes;
            this.showEndState = showEndState;
            this.isSuccess = str4;
            this.requestBody = hashMap3;
            this.data = data;
            this.showBackButton = z16;
            this.reloadOnRedirectFailure = z17;
            this.openInAppBrowser = z18;
            this.sections = list4;
            this.useWebForm = z19;
            this.barcodeSkipDelay = num3;
            this.nfcShowSkipButton = z20;
            this.nfcSkipDelay = l3;
            this.nfcAuthentication = hashMap4;
            this.enableOverlay = z21;
            this.validateSignature = z22;
            this.livenessUrl = str5;
            this.faceMatchUrl = str6;
            this.speechToTextMatchUrl = str7;
            this.logVideoStatementUrl = str8;
            this.faceMatchParams = list5;
            this.start = start;
            this.statements = map;
            this.allowedRestarts = i2;
            this.allowedAttempts = i3;
            this.userData = userData;
            this.liveness = videoStatementV2API;
            this.faceMatch = videoStatementV2API2;
            this.speechToTextMatch = videoStatementV2API3;
            this.logVideoStatement = videoStatementV2API4;
            this.videoUpload = videoStatementV2API5;
            this.statement = statementV2;
            this.expiresAfter = str9;
        }

        public final boolean getCaptureAudio() {
            return this.captureAudio;
        }

        public final boolean getUploadSession() {
            return this.uploadSession;
        }

        public final String getStopModuleId() {
            return this.stopModuleId;
        }

        public final boolean getUploadIfFailed() {
            return this.uploadIfFailed;
        }

        public final List<String> getCountries() {
            return this.countries;
        }

        public final boolean getAllowUpload() {
            return this.allowUpload;
        }

        public final List<String> getSupportedUploadFileTypes() {
            return this.supportedUploadFileTypes;
        }

        public final boolean getDisableOCR() {
            return this.disableOCR;
        }

        public final boolean getDisableBarcodeSkip() {
            return this.disableBarcodeSkip;
        }

        public final String getCountryId() {
            return this.countryId;
        }

        public final LinkedHashMap<String, List<String>> getDocuments() {
            return this.documents;
        }

        public final HashMap<String, LinkedHashMap<String, DocumentsOverride>> getDocumentsOverride() {
            return this.documentsOverride;
        }

        public final boolean getShowReview() {
            return this.showReview;
        }

        public final boolean getDisableLiveness() {
            return this.disableLiveness;
        }

        public final boolean getVideoRecording() {
            return this.videoRecording;
        }

        public final Integer getVideoRecordingDuration() {
            return this.videoRecordingDuration;
        }

        public final boolean getAlertTextBox() {
            return this.alertTextBox;
        }

        public final String getDefaultCamera() {
            return this.defaultCamera;
        }

        public final boolean getEnableLookStraight() {
            return this.enableLookStraight;
        }

        public final Long getCaptureTimeout() {
            return this.captureTimeout;
        }

        public final Long getFaceDetectorTimeout() {
            return this.faceDetectorTimeout;
        }

        public final boolean getZoomByDefault() {
            return this.zoomByDefault;
        }

        public final boolean getRetryIfFaceNotPresent() {
            return this.retryIfFaceNotPresent;
        }

        public final int getMaxAttemptsForFaceNotPresent() {
            return this.maxAttemptsForFaceNotPresent;
        }

        public final String getUrl() {
            return this.url;
        }

        public final String getApiType() {
            return this.apiType;
        }

        public final HashMap<String, String> getHeaders() {
            return this.headers;
        }

        public final boolean getShowInstruction() {
            return this.showInstruction;
        }

        public final boolean getAutoCapture() {
            return this.autoCapture;
        }

        public final Integer getAutoCaptureDuration() {
            return this.autoCaptureDuration;
        }

        public final List<RequestParam> getRequestParams() {
            return this.requestParams;
        }

        public /* synthetic */ Properties(boolean z, boolean z2, String str, boolean z3, List list, boolean z4, List list2, boolean z5, boolean z6, String str2, LinkedHashMap linkedHashMap, HashMap hashMap, boolean z7, boolean z8, boolean z9, Integer num, boolean z10, String str3, boolean z11, Long l, Long l2, boolean z12, boolean z13, int i, String str4, String str5, HashMap hashMap2, boolean z14, boolean z15, Integer num2, List list3, List list4, String str6, String str7, HashMap hashMap3, Data data, boolean z16, boolean z17, boolean z18, List list5, boolean z19, Integer num3, boolean z20, Long l3, HashMap hashMap4, boolean z21, boolean z22, String str8, String str9, String str10, String str11, List list6, String str12, Map map, int i2, int i3, Map map2, VideoStatementV2API videoStatementV2API, VideoStatementV2API videoStatementV2API2, VideoStatementV2API videoStatementV2API3, VideoStatementV2API videoStatementV2API4, VideoStatementV2API videoStatementV2API5, StatementV2 statementV2, String str13, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
            this((i4 & 1) != 0 ? true : z, (i4 & 2) != 0 ? false : z2, (i4 & 4) != 0 ? "" : str, (i4 & 8) != 0 ? true : z3, (i4 & 16) != 0 ? null : list, (i4 & 32) != 0 ? false : z4, (i4 & 64) != 0 ? null : list2, (i4 & 128) != 0 ? false : z5, (i4 & 256) != 0 ? false : z6, (i4 & 512) != 0 ? "" : str2, (i4 & 1024) != 0 ? null : linkedHashMap, (i4 & 2048) != 0 ? null : hashMap, (i4 & 4096) != 0 ? false : z7, (i4 & 8192) != 0 ? false : z8, (i4 & 16384) != 0 ? false : z9, (i4 & 32768) != 0 ? null : num, (i4 & 65536) != 0 ? false : z10, (i4 & 131072) != 0 ? null : str3, (i4 & 262144) != 0 ? false : z11, (i4 & 524288) != 0 ? null : l, (i4 & 1048576) != 0 ? null : l2, (i4 & 2097152) != 0 ? false : z12, (i4 & 4194304) != 0 ? false : z13, (i4 & 8388608) != 0 ? 3 : i, (i4 & 16777216) != 0 ? null : str4, (i4 & PDButton.FLAG_RADIOS_IN_UNISON) != 0 ? null : str5, (i4 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? null : hashMap2, (i4 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? true : z14, (i4 & 268435456) != 0 ? false : z15, (i4 & 536870912) != 0 ? null : num2, (i4 & 1073741824) != 0 ? null : list3, (i4 & Integer.MIN_VALUE) != 0 ? ResponsesKt.getALLOWED_FAILURE_STATUS_CODES() : list4, (i5 & 1) != 0 ? "no" : str6, (i5 & 2) != 0 ? null : str7, (i5 & 4) != 0 ? null : hashMap3, (i5 & 8) != 0 ? null : data, (i5 & 16) != 0 ? false : z16, (i5 & 32) != 0 ? false : z17, (i5 & 64) != 0 ? false : z18, (i5 & 128) != 0 ? null : list5, (i5 & 256) != 0 ? true : z19, (i5 & 512) != 0 ? null : num3, (i5 & 1024) != 0 ? true : z20, (i5 & 2048) != 0 ? null : l3, (i5 & 4096) != 0 ? null : hashMap4, (i5 & 8192) != 0 ? true : z21, (i5 & 16384) != 0 ? false : z22, (i5 & 32768) != 0 ? null : str8, (i5 & 65536) != 0 ? null : str9, (i5 & 131072) != 0 ? null : str10, (i5 & 262144) != 0 ? null : str11, (i5 & 524288) != 0 ? null : list6, (i5 & 1048576) == 0 ? str12 : "", (i5 & 2097152) != 0 ? null : map, (i5 & 4194304) != 0 ? 3 : i2, (i5 & 8388608) == 0 ? i3 : 3, (i5 & 16777216) != 0 ? MapsKt.emptyMap() : map2, (i5 & PDButton.FLAG_RADIOS_IN_UNISON) != 0 ? null : videoStatementV2API, (i5 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? null : videoStatementV2API2, (i5 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? null : videoStatementV2API3, (i5 & 268435456) != 0 ? null : videoStatementV2API4, (i5 & 536870912) != 0 ? null : videoStatementV2API5, (i5 & 1073741824) != 0 ? null : statementV2, (i5 & Integer.MIN_VALUE) != 0 ? null : str13);
        }

        public final List<Integer> getAllowedStatusCodes() {
            return this.allowedStatusCodes;
        }

        public final String getShowEndState() {
            return this.showEndState;
        }

        public final String isSuccess() {
            return this.isSuccess;
        }

        public final HashMap<String, Object> getRequestBody() {
            return this.requestBody;
        }

        public final Data getData() {
            return this.data;
        }

        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        public final boolean getReloadOnRedirectFailure() {
            return this.reloadOnRedirectFailure;
        }

        public final boolean getOpenInAppBrowser() {
            return this.openInAppBrowser;
        }

        public final List<Section> getSections() {
            return this.sections;
        }

        public final boolean getUseWebForm() {
            return this.useWebForm;
        }

        public final Integer getBarcodeSkipDelay() {
            return this.barcodeSkipDelay;
        }

        public final boolean getNfcShowSkipButton() {
            return this.nfcShowSkipButton;
        }

        public final Long getNfcSkipDelay() {
            return this.nfcSkipDelay;
        }

        public final HashMap<String, String> getNfcAuthentication() {
            return this.nfcAuthentication;
        }

        public final boolean getEnableOverlay() {
            return this.enableOverlay;
        }

        public final boolean getValidateSignature() {
            return this.validateSignature;
        }

        public final String getLivenessUrl() {
            return this.livenessUrl;
        }

        public final String getFaceMatchUrl() {
            return this.faceMatchUrl;
        }

        public final String getSpeechToTextMatchUrl() {
            return this.speechToTextMatchUrl;
        }

        public final String getLogVideoStatementUrl() {
            return this.logVideoStatementUrl;
        }

        public final List<RequestParam> getFaceMatchParams() {
            return this.faceMatchParams;
        }

        public final String getStart() {
            return this.start;
        }

        public final Map<String, Statement> getStatements() {
            return this.statements;
        }

        public final int getAllowedRestarts() {
            return this.allowedRestarts;
        }

        public final int getAllowedAttempts() {
            return this.allowedAttempts;
        }

        public final Map<String, String> getUserData() {
            return this.userData;
        }

        public final VideoStatementV2API getLiveness() {
            return this.liveness;
        }

        public final VideoStatementV2API getFaceMatch() {
            return this.faceMatch;
        }

        public final VideoStatementV2API getSpeechToTextMatch() {
            return this.speechToTextMatch;
        }

        public final VideoStatementV2API getLogVideoStatement() {
            return this.logVideoStatement;
        }

        public final VideoStatementV2API getVideoUpload() {
            return this.videoUpload;
        }

        public final StatementV2 getStatement() {
            return this.statement;
        }

        public final String getExpiresAfter() {
            return this.expiresAfter;
        }

        public final List<Integer> allowedStatusCodes$hyperkyc_release() {
            return CollectionsKt.plus((Collection) this.allowedStatusCodes, (Iterable) ResponsesKt.getALLOWED_SUCCESS_STATUS_CODES());
        }

        /* compiled from: WorkflowConfig.kt */
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001BE\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u000bHÆ\u0003JT\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\u00032\b\u0010\"\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020\u0005HÖ\u0001J\t\u0010%\u001a\u00020\bHÖ\u0001R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0015\u0010\u000eR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017¨\u0006&"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Statement;", "Ljava/io/Serializable;", "displayTimer", "", "duration", "", "captureImageAt", "enable", "", "next", "checks", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Checks;", "(Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Checks;)V", "getCaptureImageAt", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getChecks", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Checks;", "getDisplayTimer", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getDuration", "getEnable", "()Ljava/lang/String;", "getNext", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Checks;)Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Statement;", "equals", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final /* data */ class Statement implements Serializable {

            @SerializedName("captureImageAt")
            private final Integer captureImageAt;

            @SerializedName("checks")
            private final Checks checks;

            @SerializedName("displayTimer")
            private final Boolean displayTimer;

            @SerializedName("duration")
            private final Integer duration;

            @SerializedName("enable")
            private final String enable;

            @SerializedName("next")
            private final String next;

            public static /* synthetic */ Statement copy$default(Statement statement, Boolean bool, Integer num, Integer num2, String str, String str2, Checks checks, int i, Object obj) {
                if ((i & 1) != 0) {
                    bool = statement.displayTimer;
                }
                if ((i & 2) != 0) {
                    num = statement.duration;
                }
                Integer num3 = num;
                if ((i & 4) != 0) {
                    num2 = statement.captureImageAt;
                }
                Integer num4 = num2;
                if ((i & 8) != 0) {
                    str = statement.enable;
                }
                String str3 = str;
                if ((i & 16) != 0) {
                    str2 = statement.next;
                }
                String str4 = str2;
                if ((i & 32) != 0) {
                    checks = statement.checks;
                }
                return statement.copy(bool, num3, num4, str3, str4, checks);
            }

            /* renamed from: component1, reason: from getter */
            public final Boolean getDisplayTimer() {
                return this.displayTimer;
            }

            /* renamed from: component2, reason: from getter */
            public final Integer getDuration() {
                return this.duration;
            }

            /* renamed from: component3, reason: from getter */
            public final Integer getCaptureImageAt() {
                return this.captureImageAt;
            }

            /* renamed from: component4, reason: from getter */
            public final String getEnable() {
                return this.enable;
            }

            /* renamed from: component5, reason: from getter */
            public final String getNext() {
                return this.next;
            }

            /* renamed from: component6, reason: from getter */
            public final Checks getChecks() {
                return this.checks;
            }

            public final Statement copy(Boolean displayTimer, Integer duration, Integer captureImageAt, String enable, String next, Checks checks) {
                Intrinsics.checkNotNullParameter(next, "next");
                return new Statement(displayTimer, duration, captureImageAt, enable, next, checks);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Statement)) {
                    return false;
                }
                Statement statement = (Statement) other;
                return Intrinsics.areEqual(this.displayTimer, statement.displayTimer) && Intrinsics.areEqual(this.duration, statement.duration) && Intrinsics.areEqual(this.captureImageAt, statement.captureImageAt) && Intrinsics.areEqual(this.enable, statement.enable) && Intrinsics.areEqual(this.next, statement.next) && Intrinsics.areEqual(this.checks, statement.checks);
            }

            public int hashCode() {
                Boolean bool = this.displayTimer;
                int hashCode = (bool == null ? 0 : bool.hashCode()) * 31;
                Integer num = this.duration;
                int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
                Integer num2 = this.captureImageAt;
                int hashCode3 = (hashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
                String str = this.enable;
                int hashCode4 = (((hashCode3 + (str == null ? 0 : str.hashCode())) * 31) + this.next.hashCode()) * 31;
                Checks checks = this.checks;
                return hashCode4 + (checks != null ? checks.hashCode() : 0);
            }

            public String toString() {
                return "Statement(displayTimer=" + this.displayTimer + ", duration=" + this.duration + ", captureImageAt=" + this.captureImageAt + ", enable=" + this.enable + ", next=" + this.next + ", checks=" + this.checks + ')';
            }

            public Statement(Boolean bool, Integer num, Integer num2, String str, String next, Checks checks) {
                Intrinsics.checkNotNullParameter(next, "next");
                this.displayTimer = bool;
                this.duration = num;
                this.captureImageAt = num2;
                this.enable = str;
                this.next = next;
                this.checks = checks;
            }

            public /* synthetic */ Statement(Boolean bool, Integer num, Integer num2, String str, String str2, Checks checks, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? true : bool, (i & 2) != 0 ? Integer.valueOf(DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS) : num, num2, str, (i & 16) != 0 ? VideoStatementVM.END_OF_STATEMENTS : str2, checks);
            }

            public final Boolean getDisplayTimer() {
                return this.displayTimer;
            }

            public final Integer getDuration() {
                return this.duration;
            }

            public final Integer getCaptureImageAt() {
                return this.captureImageAt;
            }

            public final String getEnable() {
                return this.enable;
            }

            public final String getNext() {
                return this.next;
            }

            public final Checks getChecks() {
                return this.checks;
            }
        }

        /* compiled from: WorkflowConfig.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003JE\u0010\u0016\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001e"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Checks;", "Ljava/io/Serializable;", "restartFrom", "", "liveness", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Check;", "faceMatch", "faceDetection", "speechToTextMatching", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Check;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Check;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Check;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Check;)V", "getFaceDetection", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Check;", "getFaceMatch", "getLiveness", "getRestartFrom", "()Ljava/lang/String;", "getSpeechToTextMatching", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final /* data */ class Checks implements Serializable {

            @SerializedName("faceDetection")
            private final Check faceDetection;

            @SerializedName("faceMatch")
            private final Check faceMatch;

            @SerializedName("liveness")
            private final Check liveness;

            @SerializedName("restartFrom")
            private final String restartFrom;

            @SerializedName("speechToTextMatching")
            private final Check speechToTextMatching;

            public static /* synthetic */ Checks copy$default(Checks checks, String str, Check check, Check check2, Check check3, Check check4, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = checks.restartFrom;
                }
                if ((i & 2) != 0) {
                    check = checks.liveness;
                }
                Check check5 = check;
                if ((i & 4) != 0) {
                    check2 = checks.faceMatch;
                }
                Check check6 = check2;
                if ((i & 8) != 0) {
                    check3 = checks.faceDetection;
                }
                Check check7 = check3;
                if ((i & 16) != 0) {
                    check4 = checks.speechToTextMatching;
                }
                return checks.copy(str, check5, check6, check7, check4);
            }

            /* renamed from: component1, reason: from getter */
            public final String getRestartFrom() {
                return this.restartFrom;
            }

            /* renamed from: component2, reason: from getter */
            public final Check getLiveness() {
                return this.liveness;
            }

            /* renamed from: component3, reason: from getter */
            public final Check getFaceMatch() {
                return this.faceMatch;
            }

            /* renamed from: component4, reason: from getter */
            public final Check getFaceDetection() {
                return this.faceDetection;
            }

            /* renamed from: component5, reason: from getter */
            public final Check getSpeechToTextMatching() {
                return this.speechToTextMatching;
            }

            public final Checks copy(String restartFrom, Check liveness, Check faceMatch, Check faceDetection, Check speechToTextMatching) {
                return new Checks(restartFrom, liveness, faceMatch, faceDetection, speechToTextMatching);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Checks)) {
                    return false;
                }
                Checks checks = (Checks) other;
                return Intrinsics.areEqual(this.restartFrom, checks.restartFrom) && Intrinsics.areEqual(this.liveness, checks.liveness) && Intrinsics.areEqual(this.faceMatch, checks.faceMatch) && Intrinsics.areEqual(this.faceDetection, checks.faceDetection) && Intrinsics.areEqual(this.speechToTextMatching, checks.speechToTextMatching);
            }

            public int hashCode() {
                String str = this.restartFrom;
                int hashCode = (str == null ? 0 : str.hashCode()) * 31;
                Check check = this.liveness;
                int hashCode2 = (hashCode + (check == null ? 0 : check.hashCode())) * 31;
                Check check2 = this.faceMatch;
                int hashCode3 = (hashCode2 + (check2 == null ? 0 : check2.hashCode())) * 31;
                Check check3 = this.faceDetection;
                int hashCode4 = (hashCode3 + (check3 == null ? 0 : check3.hashCode())) * 31;
                Check check4 = this.speechToTextMatching;
                return hashCode4 + (check4 != null ? check4.hashCode() : 0);
            }

            public String toString() {
                return "Checks(restartFrom=" + this.restartFrom + ", liveness=" + this.liveness + ", faceMatch=" + this.faceMatch + ", faceDetection=" + this.faceDetection + ", speechToTextMatching=" + this.speechToTextMatching + ')';
            }

            public Checks(String str, Check check, Check check2, Check check3, Check check4) {
                this.restartFrom = str;
                this.liveness = check;
                this.faceMatch = check2;
                this.faceDetection = check3;
                this.speechToTextMatching = check4;
            }

            public final String getRestartFrom() {
                return this.restartFrom;
            }

            public final Check getLiveness() {
                return this.liveness;
            }

            public final Check getFaceMatch() {
                return this.faceMatch;
            }

            public final Check getFaceDetection() {
                return this.faceDetection;
            }

            public final Check getSpeechToTextMatching() {
                return this.speechToTextMatching;
            }
        }

        /* compiled from: WorkflowConfig.kt */
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\nR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Check;", "Ljava/io/Serializable;", "isSync", "", "enable", "maxOutOfFrameTime", "", "allowIfCheckFailed", "(ZZIZ)V", "getAllowIfCheckFailed", "()Z", "getEnable", "getMaxOutOfFrameTime", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final /* data */ class Check implements Serializable {

            @SerializedName("allowIfCheckFailed")
            private final boolean allowIfCheckFailed;

            @SerializedName("enable")
            private final boolean enable;

            @SerializedName("isSync")
            private final boolean isSync;

            @SerializedName("maxOutOfFrameTime")
            private final int maxOutOfFrameTime;

            public Check() {
                this(false, false, 0, false, 15, null);
            }

            public static /* synthetic */ Check copy$default(Check check, boolean z, boolean z2, int i, boolean z3, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    z = check.isSync;
                }
                if ((i2 & 2) != 0) {
                    z2 = check.enable;
                }
                if ((i2 & 4) != 0) {
                    i = check.maxOutOfFrameTime;
                }
                if ((i2 & 8) != 0) {
                    z3 = check.allowIfCheckFailed;
                }
                return check.copy(z, z2, i, z3);
            }

            /* renamed from: component1, reason: from getter */
            public final boolean getIsSync() {
                return this.isSync;
            }

            /* renamed from: component2, reason: from getter */
            public final boolean getEnable() {
                return this.enable;
            }

            /* renamed from: component3, reason: from getter */
            public final int getMaxOutOfFrameTime() {
                return this.maxOutOfFrameTime;
            }

            /* renamed from: component4, reason: from getter */
            public final boolean getAllowIfCheckFailed() {
                return this.allowIfCheckFailed;
            }

            public final Check copy(boolean isSync, boolean enable, int maxOutOfFrameTime, boolean allowIfCheckFailed) {
                return new Check(isSync, enable, maxOutOfFrameTime, allowIfCheckFailed);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Check)) {
                    return false;
                }
                Check check = (Check) other;
                return this.isSync == check.isSync && this.enable == check.enable && this.maxOutOfFrameTime == check.maxOutOfFrameTime && this.allowIfCheckFailed == check.allowIfCheckFailed;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v1, types: [int] */
            /* JADX WARN: Type inference failed for: r0v8 */
            /* JADX WARN: Type inference failed for: r0v9 */
            /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
            public int hashCode() {
                boolean z = this.isSync;
                ?? r0 = z;
                if (z) {
                    r0 = 1;
                }
                int i = r0 * 31;
                ?? r2 = this.enable;
                int i2 = r2;
                if (r2 != 0) {
                    i2 = 1;
                }
                int i3 = (((i + i2) * 31) + this.maxOutOfFrameTime) * 31;
                boolean z2 = this.allowIfCheckFailed;
                return i3 + (z2 ? 1 : z2 ? 1 : 0);
            }

            public String toString() {
                return "Check(isSync=" + this.isSync + ", enable=" + this.enable + ", maxOutOfFrameTime=" + this.maxOutOfFrameTime + ", allowIfCheckFailed=" + this.allowIfCheckFailed + ')';
            }

            public Check(boolean z, boolean z2, int i, boolean z3) {
                this.isSync = z;
                this.enable = z2;
                this.maxOutOfFrameTime = i;
                this.allowIfCheckFailed = z3;
            }

            public /* synthetic */ Check(boolean z, boolean z2, int i, boolean z3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
                this((i2 & 1) != 0 ? true : z, (i2 & 2) != 0 ? false : z2, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? false : z3);
            }

            public final boolean isSync() {
                return this.isSync;
            }

            public final boolean getEnable() {
                return this.enable;
            }

            public final int getMaxOutOfFrameTime() {
                return this.maxOutOfFrameTime;
            }

            public final boolean getAllowIfCheckFailed() {
                return this.allowIfCheckFailed;
            }
        }

        /* compiled from: WorkflowConfig.kt */
        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0001&BU\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012$\b\u0002\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b¢\u0006\u0002\u0010\fJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J%\u0010\u001c\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u0006HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\bHÆ\u0003JY\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032$\b\u0002\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020\u000bHÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R$\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R:\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006'"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$VideoStatementV2API;", "Ljava/io/Serializable;", "url", "", "headers", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "parameters", "", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$RequestParam;", "allowedStatusCodes", "", "(Ljava/lang/String;Ljava/util/HashMap;Ljava/util/List;Ljava/util/List;)V", "getAllowedStatusCodes", "()Ljava/util/List;", "setAllowedStatusCodes", "(Ljava/util/List;)V", "getHeaders", "()Ljava/util/HashMap;", "setHeaders", "(Ljava/util/HashMap;)V", "getParameters", "setParameters", "getUrl", "()Ljava/lang/String;", "setUrl", "(Ljava/lang/String;)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final /* data */ class VideoStatementV2API implements Serializable {
            public static final String COUNTRY_CODE = "countryCode";
            public static final String PERFORM_TEXT_MATCH = "performTextMatch";
            public static final String TEXT_LANGUAGE_CODE = "textLanguageCode";

            @SerializedName("allowedStatusCodes")
            private List<Integer> allowedStatusCodes;

            @SerializedName("headers")
            private HashMap<String, String> headers;

            @SerializedName("parameters")
            private List<RequestParam> parameters;

            @SerializedName("url")
            private String url;

            public VideoStatementV2API() {
                this(null, null, null, null, 15, null);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ VideoStatementV2API copy$default(VideoStatementV2API videoStatementV2API, String str, HashMap hashMap, List list, List list2, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = videoStatementV2API.url;
                }
                if ((i & 2) != 0) {
                    hashMap = videoStatementV2API.headers;
                }
                if ((i & 4) != 0) {
                    list = videoStatementV2API.parameters;
                }
                if ((i & 8) != 0) {
                    list2 = videoStatementV2API.allowedStatusCodes;
                }
                return videoStatementV2API.copy(str, hashMap, list, list2);
            }

            /* renamed from: component1, reason: from getter */
            public final String getUrl() {
                return this.url;
            }

            public final HashMap<String, String> component2() {
                return this.headers;
            }

            public final List<RequestParam> component3() {
                return this.parameters;
            }

            public final List<Integer> component4() {
                return this.allowedStatusCodes;
            }

            public final VideoStatementV2API copy(String url, HashMap<String, String> headers, List<RequestParam> parameters, List<Integer> allowedStatusCodes) {
                Intrinsics.checkNotNullParameter(url, "url");
                Intrinsics.checkNotNullParameter(headers, "headers");
                Intrinsics.checkNotNullParameter(parameters, "parameters");
                Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
                return new VideoStatementV2API(url, headers, parameters, allowedStatusCodes);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof VideoStatementV2API)) {
                    return false;
                }
                VideoStatementV2API videoStatementV2API = (VideoStatementV2API) other;
                return Intrinsics.areEqual(this.url, videoStatementV2API.url) && Intrinsics.areEqual(this.headers, videoStatementV2API.headers) && Intrinsics.areEqual(this.parameters, videoStatementV2API.parameters) && Intrinsics.areEqual(this.allowedStatusCodes, videoStatementV2API.allowedStatusCodes);
            }

            public int hashCode() {
                return (((((this.url.hashCode() * 31) + this.headers.hashCode()) * 31) + this.parameters.hashCode()) * 31) + this.allowedStatusCodes.hashCode();
            }

            public String toString() {
                return "VideoStatementV2API(url=" + this.url + ", headers=" + this.headers + ", parameters=" + this.parameters + ", allowedStatusCodes=" + this.allowedStatusCodes + ')';
            }

            public VideoStatementV2API(String url, HashMap<String, String> headers, List<RequestParam> parameters, List<Integer> allowedStatusCodes) {
                Intrinsics.checkNotNullParameter(url, "url");
                Intrinsics.checkNotNullParameter(headers, "headers");
                Intrinsics.checkNotNullParameter(parameters, "parameters");
                Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
                this.url = url;
                this.headers = headers;
                this.parameters = parameters;
                this.allowedStatusCodes = allowedStatusCodes;
            }

            public final String getUrl() {
                return this.url;
            }

            public final void setUrl(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.url = str;
            }

            public /* synthetic */ VideoStatementV2API(String str, HashMap hashMap, ArrayList arrayList, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? new HashMap() : hashMap, (i & 4) != 0 ? new ArrayList() : arrayList, (i & 8) != 0 ? ResponsesKt.getALLOWED_FAILURE_STATUS_CODES() : list);
            }

            public final HashMap<String, String> getHeaders() {
                return this.headers;
            }

            public final void setHeaders(HashMap<String, String> hashMap) {
                Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
                this.headers = hashMap;
            }

            public final List<RequestParam> getParameters() {
                return this.parameters;
            }

            public final void setParameters(List<RequestParam> list) {
                Intrinsics.checkNotNullParameter(list, "<set-?>");
                this.parameters = list;
            }

            public final List<Integer> getAllowedStatusCodes() {
                return this.allowedStatusCodes;
            }

            public final void setAllowedStatusCodes(List<Integer> list) {
                Intrinsics.checkNotNullParameter(list, "<set-?>");
                this.allowedStatusCodes = list;
            }
        }

        /* compiled from: WorkflowConfig.kt */
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\"B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0012J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\tHÆ\u0003JD\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u0006HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000e¨\u0006#"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2;", "Ljava/io/Serializable;", "type", "", "displayTimer", "duration", "", "minimumDuration", "checks", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2$ChecksV2;", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2$ChecksV2;)V", "getChecks", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2$ChecksV2;", "getDisplayTimer", "()Ljava/lang/String;", "getDuration", "()I", "getMinimumDuration", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getType", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2$ChecksV2;)Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2;", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "ChecksV2", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final /* data */ class StatementV2 implements Serializable {

            @SerializedName("checks")
            private final ChecksV2 checks;

            @SerializedName("displayTimer")
            private final String displayTimer;

            @SerializedName("duration")
            private final int duration;

            @SerializedName("minimumDuration")
            private final Integer minimumDuration;

            @SerializedName("type")
            private final String type;

            public static /* synthetic */ StatementV2 copy$default(StatementV2 statementV2, String str, String str2, int i, Integer num, ChecksV2 checksV2, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    str = statementV2.type;
                }
                if ((i2 & 2) != 0) {
                    str2 = statementV2.displayTimer;
                }
                String str3 = str2;
                if ((i2 & 4) != 0) {
                    i = statementV2.duration;
                }
                int i3 = i;
                if ((i2 & 8) != 0) {
                    num = statementV2.minimumDuration;
                }
                Integer num2 = num;
                if ((i2 & 16) != 0) {
                    checksV2 = statementV2.checks;
                }
                return statementV2.copy(str, str3, i3, num2, checksV2);
            }

            /* renamed from: component1, reason: from getter */
            public final String getType() {
                return this.type;
            }

            /* renamed from: component2, reason: from getter */
            public final String getDisplayTimer() {
                return this.displayTimer;
            }

            /* renamed from: component3, reason: from getter */
            public final int getDuration() {
                return this.duration;
            }

            /* renamed from: component4, reason: from getter */
            public final Integer getMinimumDuration() {
                return this.minimumDuration;
            }

            /* renamed from: component5, reason: from getter */
            public final ChecksV2 getChecks() {
                return this.checks;
            }

            public final StatementV2 copy(String type, String displayTimer, int duration, Integer minimumDuration, ChecksV2 checks) {
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(displayTimer, "displayTimer");
                return new StatementV2(type, displayTimer, duration, minimumDuration, checks);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof StatementV2)) {
                    return false;
                }
                StatementV2 statementV2 = (StatementV2) other;
                return Intrinsics.areEqual(this.type, statementV2.type) && Intrinsics.areEqual(this.displayTimer, statementV2.displayTimer) && this.duration == statementV2.duration && Intrinsics.areEqual(this.minimumDuration, statementV2.minimumDuration) && Intrinsics.areEqual(this.checks, statementV2.checks);
            }

            public int hashCode() {
                int hashCode = ((((this.type.hashCode() * 31) + this.displayTimer.hashCode()) * 31) + this.duration) * 31;
                Integer num = this.minimumDuration;
                int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
                ChecksV2 checksV2 = this.checks;
                return hashCode2 + (checksV2 != null ? checksV2.hashCode() : 0);
            }

            public String toString() {
                return "StatementV2(type=" + this.type + ", displayTimer=" + this.displayTimer + ", duration=" + this.duration + ", minimumDuration=" + this.minimumDuration + ", checks=" + this.checks + ')';
            }

            public StatementV2(String type, String displayTimer, int i, Integer num, ChecksV2 checksV2) {
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(displayTimer, "displayTimer");
                this.type = type;
                this.displayTimer = displayTimer;
                this.duration = i;
                this.minimumDuration = num;
                this.checks = checksV2;
            }

            public /* synthetic */ StatementV2(String str, String str2, int i, Integer num, ChecksV2 checksV2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
                this((i2 & 1) != 0 ? "text" : str, (i2 & 2) != 0 ? "no" : str2, (i2 & 4) != 0 ? DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS : i, num, checksV2);
            }

            public final String getType() {
                return this.type;
            }

            public final String getDisplayTimer() {
                return this.displayTimer;
            }

            public final int getDuration() {
                return this.duration;
            }

            public final Integer getMinimumDuration() {
                return this.minimumDuration;
            }

            public final ChecksV2 getChecks() {
                return this.checks;
            }

            /* compiled from: WorkflowConfig.kt */
            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u001aB-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J9\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u001b"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2$ChecksV2;", "Ljava/io/Serializable;", "liveness", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2$ChecksV2$CheckV2;", "faceMatch", "faceDetection", "speechToTextMatch", "(Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2$ChecksV2$CheckV2;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2$ChecksV2$CheckV2;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2$ChecksV2$CheckV2;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2$ChecksV2$CheckV2;)V", "getFaceDetection", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2$ChecksV2$CheckV2;", "getFaceMatch", "getLiveness", "getSpeechToTextMatch", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "", "CheckV2", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
            /* loaded from: classes2.dex */
            public static final /* data */ class ChecksV2 implements Serializable {

                @SerializedName("faceDetection")
                private final CheckV2 faceDetection;

                @SerializedName("faceMatch")
                private final CheckV2 faceMatch;

                @SerializedName("liveness")
                private final CheckV2 liveness;

                @SerializedName("speechToTextMatch")
                private final CheckV2 speechToTextMatch;

                public static /* synthetic */ ChecksV2 copy$default(ChecksV2 checksV2, CheckV2 checkV2, CheckV2 checkV22, CheckV2 checkV23, CheckV2 checkV24, int i, Object obj) {
                    if ((i & 1) != 0) {
                        checkV2 = checksV2.liveness;
                    }
                    if ((i & 2) != 0) {
                        checkV22 = checksV2.faceMatch;
                    }
                    if ((i & 4) != 0) {
                        checkV23 = checksV2.faceDetection;
                    }
                    if ((i & 8) != 0) {
                        checkV24 = checksV2.speechToTextMatch;
                    }
                    return checksV2.copy(checkV2, checkV22, checkV23, checkV24);
                }

                /* renamed from: component1, reason: from getter */
                public final CheckV2 getLiveness() {
                    return this.liveness;
                }

                /* renamed from: component2, reason: from getter */
                public final CheckV2 getFaceMatch() {
                    return this.faceMatch;
                }

                /* renamed from: component3, reason: from getter */
                public final CheckV2 getFaceDetection() {
                    return this.faceDetection;
                }

                /* renamed from: component4, reason: from getter */
                public final CheckV2 getSpeechToTextMatch() {
                    return this.speechToTextMatch;
                }

                public final ChecksV2 copy(CheckV2 liveness, CheckV2 faceMatch, CheckV2 faceDetection, CheckV2 speechToTextMatch) {
                    return new ChecksV2(liveness, faceMatch, faceDetection, speechToTextMatch);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof ChecksV2)) {
                        return false;
                    }
                    ChecksV2 checksV2 = (ChecksV2) other;
                    return Intrinsics.areEqual(this.liveness, checksV2.liveness) && Intrinsics.areEqual(this.faceMatch, checksV2.faceMatch) && Intrinsics.areEqual(this.faceDetection, checksV2.faceDetection) && Intrinsics.areEqual(this.speechToTextMatch, checksV2.speechToTextMatch);
                }

                public int hashCode() {
                    CheckV2 checkV2 = this.liveness;
                    int hashCode = (checkV2 == null ? 0 : checkV2.hashCode()) * 31;
                    CheckV2 checkV22 = this.faceMatch;
                    int hashCode2 = (hashCode + (checkV22 == null ? 0 : checkV22.hashCode())) * 31;
                    CheckV2 checkV23 = this.faceDetection;
                    int hashCode3 = (hashCode2 + (checkV23 == null ? 0 : checkV23.hashCode())) * 31;
                    CheckV2 checkV24 = this.speechToTextMatch;
                    return hashCode3 + (checkV24 != null ? checkV24.hashCode() : 0);
                }

                public String toString() {
                    return "ChecksV2(liveness=" + this.liveness + ", faceMatch=" + this.faceMatch + ", faceDetection=" + this.faceDetection + ", speechToTextMatch=" + this.speechToTextMatch + ')';
                }

                public ChecksV2(CheckV2 checkV2, CheckV2 checkV22, CheckV2 checkV23, CheckV2 checkV24) {
                    this.liveness = checkV2;
                    this.faceMatch = checkV22;
                    this.faceDetection = checkV23;
                    this.speechToTextMatch = checkV24;
                }

                public final CheckV2 getLiveness() {
                    return this.liveness;
                }

                public final CheckV2 getFaceMatch() {
                    return this.faceMatch;
                }

                public final CheckV2 getFaceDetection() {
                    return this.faceDetection;
                }

                public final CheckV2 getSpeechToTextMatch() {
                    return this.speechToTextMatch;
                }

                /* compiled from: WorkflowConfig.kt */
                @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001a"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$StatementV2$ChecksV2$CheckV2;", "Ljava/io/Serializable;", "enable", "", "allowIfCheckFailed", Section.Component.Validation.Type.RULE, "maxOutOfFrameTime", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getAllowIfCheckFailed", "()Ljava/lang/String;", "getEnable", "getMaxOutOfFrameTime", "()I", "getRule", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                /* loaded from: classes2.dex */
                public static final /* data */ class CheckV2 implements Serializable {

                    @SerializedName("allowIfCheckFailed")
                    private final String allowIfCheckFailed;

                    @SerializedName("enable")
                    private final String enable;

                    @SerializedName("maxOutOfFrameTime")
                    private final int maxOutOfFrameTime;

                    @SerializedName(Section.Component.Validation.Type.RULE)
                    private final String rule;

                    public CheckV2() {
                        this(null, null, null, 0, 15, null);
                    }

                    public static /* synthetic */ CheckV2 copy$default(CheckV2 checkV2, String str, String str2, String str3, int i, int i2, Object obj) {
                        if ((i2 & 1) != 0) {
                            str = checkV2.enable;
                        }
                        if ((i2 & 2) != 0) {
                            str2 = checkV2.allowIfCheckFailed;
                        }
                        if ((i2 & 4) != 0) {
                            str3 = checkV2.rule;
                        }
                        if ((i2 & 8) != 0) {
                            i = checkV2.maxOutOfFrameTime;
                        }
                        return checkV2.copy(str, str2, str3, i);
                    }

                    /* renamed from: component1, reason: from getter */
                    public final String getEnable() {
                        return this.enable;
                    }

                    /* renamed from: component2, reason: from getter */
                    public final String getAllowIfCheckFailed() {
                        return this.allowIfCheckFailed;
                    }

                    /* renamed from: component3, reason: from getter */
                    public final String getRule() {
                        return this.rule;
                    }

                    /* renamed from: component4, reason: from getter */
                    public final int getMaxOutOfFrameTime() {
                        return this.maxOutOfFrameTime;
                    }

                    public final CheckV2 copy(String enable, String allowIfCheckFailed, String rule, int maxOutOfFrameTime) {
                        Intrinsics.checkNotNullParameter(enable, "enable");
                        Intrinsics.checkNotNullParameter(allowIfCheckFailed, "allowIfCheckFailed");
                        Intrinsics.checkNotNullParameter(rule, "rule");
                        return new CheckV2(enable, allowIfCheckFailed, rule, maxOutOfFrameTime);
                    }

                    public boolean equals(Object other) {
                        if (this == other) {
                            return true;
                        }
                        if (!(other instanceof CheckV2)) {
                            return false;
                        }
                        CheckV2 checkV2 = (CheckV2) other;
                        return Intrinsics.areEqual(this.enable, checkV2.enable) && Intrinsics.areEqual(this.allowIfCheckFailed, checkV2.allowIfCheckFailed) && Intrinsics.areEqual(this.rule, checkV2.rule) && this.maxOutOfFrameTime == checkV2.maxOutOfFrameTime;
                    }

                    public int hashCode() {
                        return (((((this.enable.hashCode() * 31) + this.allowIfCheckFailed.hashCode()) * 31) + this.rule.hashCode()) * 31) + this.maxOutOfFrameTime;
                    }

                    public String toString() {
                        return "CheckV2(enable=" + this.enable + ", allowIfCheckFailed=" + this.allowIfCheckFailed + ", rule=" + this.rule + ", maxOutOfFrameTime=" + this.maxOutOfFrameTime + ')';
                    }

                    public CheckV2(String enable, String allowIfCheckFailed, String rule, int i) {
                        Intrinsics.checkNotNullParameter(enable, "enable");
                        Intrinsics.checkNotNullParameter(allowIfCheckFailed, "allowIfCheckFailed");
                        Intrinsics.checkNotNullParameter(rule, "rule");
                        this.enable = enable;
                        this.allowIfCheckFailed = allowIfCheckFailed;
                        this.rule = rule;
                        this.maxOutOfFrameTime = i;
                    }

                    public /* synthetic */ CheckV2(String str, String str2, String str3, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
                        this((i2 & 1) != 0 ? "yes" : str, (i2 & 2) != 0 ? "no" : str2, (i2 & 4) != 0 ? "yes" : str3, (i2 & 8) != 0 ? 2000 : i);
                    }

                    public final String getEnable() {
                        return this.enable;
                    }

                    public final String getAllowIfCheckFailed() {
                        return this.allowIfCheckFailed;
                    }

                    public final String getRule() {
                        return this.rule;
                    }

                    public final int getMaxOutOfFrameTime() {
                        return this.maxOutOfFrameTime;
                    }
                }
            }
        }

        /* compiled from: WorkflowConfig.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$RequestParam;", "Ljava/io/Serializable;", "name", "", "value", "type", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getType", "getValue", "setValue", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final /* data */ class RequestParam implements Serializable {

            @SerializedName("name")
            private String name;

            @SerializedName("type")
            private final String type;

            @SerializedName("value")
            private String value;

            public static /* synthetic */ RequestParam copy$default(RequestParam requestParam, String str, String str2, String str3, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = requestParam.name;
                }
                if ((i & 2) != 0) {
                    str2 = requestParam.value;
                }
                if ((i & 4) != 0) {
                    str3 = requestParam.type;
                }
                return requestParam.copy(str, str2, str3);
            }

            /* renamed from: component1, reason: from getter */
            public final String getName() {
                return this.name;
            }

            /* renamed from: component2, reason: from getter */
            public final String getValue() {
                return this.value;
            }

            /* renamed from: component3, reason: from getter */
            public final String getType() {
                return this.type;
            }

            public final RequestParam copy(String name, String value, String type) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(value, "value");
                return new RequestParam(name, value, type);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof RequestParam)) {
                    return false;
                }
                RequestParam requestParam = (RequestParam) other;
                return Intrinsics.areEqual(this.name, requestParam.name) && Intrinsics.areEqual(this.value, requestParam.value) && Intrinsics.areEqual(this.type, requestParam.type);
            }

            public int hashCode() {
                int hashCode = ((this.name.hashCode() * 31) + this.value.hashCode()) * 31;
                String str = this.type;
                return hashCode + (str == null ? 0 : str.hashCode());
            }

            public String toString() {
                return "RequestParam(name=" + this.name + ", value=" + this.value + ", type=" + this.type + ')';
            }

            public RequestParam(String name, String value, String str) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(value, "value");
                this.name = name;
                this.value = value;
                this.type = str;
            }

            public /* synthetic */ RequestParam(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, str2, (i & 4) != 0 ? null : str3);
            }

            public final String getName() {
                return this.name;
            }

            public final void setName(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.name = str;
            }

            public final String getValue() {
                return this.value;
            }

            public final void setValue(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.value = str;
            }

            public final String getType() {
                return this.type;
            }
        }

        /* compiled from: WorkflowConfig.kt */
        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001B;\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0011\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bHÆ\u0003J?\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J!\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00032\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001dH\u0000¢\u0006\u0002\b J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\""}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$DocumentsOverride;", "Ljava/io/Serializable;", "name", "", "type", "sidesConfig", "Lco/hyperverge/hyperkyc/data/models/KycDocument$SidesConfig;", "sides", "", "(Ljava/lang/String;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/KycDocument$SidesConfig;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "getSides", "()Ljava/util/List;", "getSidesConfig", "()Lco/hyperverge/hyperkyc/data/models/KycDocument$SidesConfig;", "getType", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toKycDocument", "Lco/hyperverge/hyperkyc/data/models/KycDocument;", "id", "oldKycDoc", "toKycDocument$hyperkyc_release", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final /* data */ class DocumentsOverride implements Serializable {

            @SerializedName("name")
            private final String name;

            @SerializedName("sides")
            private final List<String> sides;

            @SerializedName("sidesConfig")
            private final KycDocument.SidesConfig sidesConfig;

            @SerializedName("type")
            private final String type;

            public DocumentsOverride() {
                this(null, null, null, null, 15, null);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ DocumentsOverride copy$default(DocumentsOverride documentsOverride, String str, String str2, KycDocument.SidesConfig sidesConfig, List list, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = documentsOverride.name;
                }
                if ((i & 2) != 0) {
                    str2 = documentsOverride.type;
                }
                if ((i & 4) != 0) {
                    sidesConfig = documentsOverride.sidesConfig;
                }
                if ((i & 8) != 0) {
                    list = documentsOverride.sides;
                }
                return documentsOverride.copy(str, str2, sidesConfig, list);
            }

            /* renamed from: component1, reason: from getter */
            public final String getName() {
                return this.name;
            }

            /* renamed from: component2, reason: from getter */
            public final String getType() {
                return this.type;
            }

            /* renamed from: component3, reason: from getter */
            public final KycDocument.SidesConfig getSidesConfig() {
                return this.sidesConfig;
            }

            public final List<String> component4() {
                return this.sides;
            }

            public final DocumentsOverride copy(String name, String type, KycDocument.SidesConfig sidesConfig, List<String> sides) {
                return new DocumentsOverride(name, type, sidesConfig, sides);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof DocumentsOverride)) {
                    return false;
                }
                DocumentsOverride documentsOverride = (DocumentsOverride) other;
                return Intrinsics.areEqual(this.name, documentsOverride.name) && Intrinsics.areEqual(this.type, documentsOverride.type) && Intrinsics.areEqual(this.sidesConfig, documentsOverride.sidesConfig) && Intrinsics.areEqual(this.sides, documentsOverride.sides);
            }

            public int hashCode() {
                String str = this.name;
                int hashCode = (str == null ? 0 : str.hashCode()) * 31;
                String str2 = this.type;
                int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
                KycDocument.SidesConfig sidesConfig = this.sidesConfig;
                int hashCode3 = (hashCode2 + (sidesConfig == null ? 0 : sidesConfig.hashCode())) * 31;
                List<String> list = this.sides;
                return hashCode3 + (list != null ? list.hashCode() : 0);
            }

            public String toString() {
                return "DocumentsOverride(name=" + this.name + ", type=" + this.type + ", sidesConfig=" + this.sidesConfig + ", sides=" + this.sides + ')';
            }

            public DocumentsOverride(String str, String str2, KycDocument.SidesConfig sidesConfig, List<String> list) {
                this.name = str;
                this.type = str2;
                this.sidesConfig = sidesConfig;
                this.sides = list;
            }

            public /* synthetic */ DocumentsOverride(String str, String str2, KycDocument.SidesConfig sidesConfig, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : sidesConfig, (i & 8) != 0 ? null : list);
            }

            public final String getName() {
                return this.name;
            }

            public final String getType() {
                return this.type;
            }

            public final KycDocument.SidesConfig getSidesConfig() {
                return this.sidesConfig;
            }

            public final List<String> getSides() {
                return this.sides;
            }

            public static /* synthetic */ KycDocument toKycDocument$hyperkyc_release$default(DocumentsOverride documentsOverride, String str, KycDocument kycDocument, int i, Object obj) {
                if ((i & 2) != 0) {
                    kycDocument = null;
                }
                return documentsOverride.toKycDocument$hyperkyc_release(str, kycDocument);
            }

            /* JADX WARN: Removed duplicated region for block: B:20:0x003a  */
            /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final KycDocument toKycDocument$hyperkyc_release(String id2, KycDocument oldKycDoc) {
                KycDocument.SidesConfig sidesConfig;
                String str;
                String str2;
                Intrinsics.checkNotNullParameter(id2, "id");
                String str3 = this.name;
                if (str3 == null) {
                    str3 = oldKycDoc != null ? oldKycDoc.getName() : null;
                    Intrinsics.checkNotNull(str3);
                }
                String str4 = str3;
                List<String> list = this.sides;
                if (list == null) {
                    list = oldKycDoc != null ? oldKycDoc.getSides() : null;
                    Intrinsics.checkNotNull(list);
                }
                List<String> list2 = list;
                KycDocument.SidesConfig sidesConfig2 = this.sidesConfig;
                if (sidesConfig2 == null) {
                    if (oldKycDoc == null) {
                        sidesConfig = null;
                        str = this.type;
                        if (str != null) {
                            String type = oldKycDoc != null ? oldKycDoc.getType() : null;
                            Intrinsics.checkNotNull(type);
                            str2 = type;
                        } else {
                            str2 = str;
                        }
                        return new KycDocument(id2, str4, list2, sidesConfig, str2, false, 32, null);
                    }
                    sidesConfig2 = oldKycDoc.getSidesConfig();
                }
                sidesConfig = sidesConfig2;
                str = this.type;
                if (str != null) {
                }
                return new KycDocument(id2, str4, list2, sidesConfig, str2, false, 32, null);
            }
        }

        /* compiled from: WorkflowConfig.kt */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012(\b\u0002\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005¢\u0006\u0002\u0010\u0006J)\u0010\n\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0003J3\u0010\u000b\u001a\u00020\u00002(\b\u0002\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0004HÖ\u0001R>\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\u0013"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Data;", "Ljava/io/Serializable;", "queryParams", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", "(Ljava/util/LinkedHashMap;)V", "getQueryParams", "()Ljava/util/LinkedHashMap;", "setQueryParams", "component1", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final /* data */ class Data implements Serializable {

            @SerializedName("queryParams")
            private LinkedHashMap<String, String> queryParams;

            /* JADX WARN: Multi-variable type inference failed */
            public Data() {
                this(null, 1, 0 == true ? 1 : 0);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Data copy$default(Data data, LinkedHashMap linkedHashMap, int i, Object obj) {
                if ((i & 1) != 0) {
                    linkedHashMap = data.queryParams;
                }
                return data.copy(linkedHashMap);
            }

            public final LinkedHashMap<String, String> component1() {
                return this.queryParams;
            }

            public final Data copy(LinkedHashMap<String, String> queryParams) {
                return new Data(queryParams);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Data) && Intrinsics.areEqual(this.queryParams, ((Data) other).queryParams);
            }

            public int hashCode() {
                LinkedHashMap<String, String> linkedHashMap = this.queryParams;
                if (linkedHashMap == null) {
                    return 0;
                }
                return linkedHashMap.hashCode();
            }

            public String toString() {
                return "Data(queryParams=" + this.queryParams + ')';
            }

            public Data(LinkedHashMap<String, String> linkedHashMap) {
                this.queryParams = linkedHashMap;
            }

            public /* synthetic */ Data(LinkedHashMap linkedHashMap, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : linkedHashMap);
            }

            public final LinkedHashMap<String, String> getQueryParams() {
                return this.queryParams;
            }

            public final void setQueryParams(LinkedHashMap<String, String> linkedHashMap) {
                this.queryParams = linkedHashMap;
            }
        }

        /* compiled from: WorkflowConfig.kt */
        @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001:\u0002\u001b\u001cB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\bHÆ\u0003J/\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section;", "Ljava/io/Serializable;", "id", "", "components", "", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "footer", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Footer;", "(Ljava/lang/String;Ljava/util/List;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Footer;)V", "getComponents", "()Ljava/util/List;", "getFooter", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Footer;", "getId", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "Component", "Footer", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final /* data */ class Section implements Serializable {

            @SerializedName("components")
            private final List<Component> components;

            @SerializedName("footer")
            private final Footer footer;

            @SerializedName("id")
            private final String id;

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Section copy$default(Section section, String str, List list, Footer footer, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = section.id;
                }
                if ((i & 2) != 0) {
                    list = section.components;
                }
                if ((i & 4) != 0) {
                    footer = section.footer;
                }
                return section.copy(str, list, footer);
            }

            /* renamed from: component1, reason: from getter */
            public final String getId() {
                return this.id;
            }

            public final List<Component> component2() {
                return this.components;
            }

            /* renamed from: component3, reason: from getter */
            public final Footer getFooter() {
                return this.footer;
            }

            public final Section copy(String id2, List<Component> components, Footer footer) {
                Intrinsics.checkNotNullParameter(id2, "id");
                Intrinsics.checkNotNullParameter(components, "components");
                return new Section(id2, components, footer);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Section)) {
                    return false;
                }
                Section section = (Section) other;
                return Intrinsics.areEqual(this.id, section.id) && Intrinsics.areEqual(this.components, section.components) && Intrinsics.areEqual(this.footer, section.footer);
            }

            public int hashCode() {
                int hashCode = ((this.id.hashCode() * 31) + this.components.hashCode()) * 31;
                Footer footer = this.footer;
                return hashCode + (footer == null ? 0 : footer.hashCode());
            }

            public String toString() {
                return "Section(id=" + this.id + ", components=" + this.components + ", footer=" + this.footer + ')';
            }

            public Section(String id2, List<Component> components, Footer footer) {
                Intrinsics.checkNotNullParameter(id2, "id");
                Intrinsics.checkNotNullParameter(components, "components");
                this.id = id2;
                this.components = components;
                this.footer = footer;
            }

            public /* synthetic */ Section(String str, List list, Footer footer, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, list, (i & 4) != 0 ? null : footer);
            }

            public final String getId() {
                return this.id;
            }

            public final List<Component> getComponents() {
                return this.components;
            }

            public final Footer getFooter() {
                return this.footer;
            }

            /* compiled from: WorkflowConfig.kt */
            @Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b|\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0087\b\u0018\u00002\u00020\u0001:\u0016¸\u0001¹\u0001º\u0001»\u0001¼\u0001½\u0001¾\u0001¿\u0001À\u0001Á\u0001Â\u0001Bó\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u001a\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"\u0012(\b\u0002\u0010#\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010$j\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u0001`%\u0012\u0010\b\u0002\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0017\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010-\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u00106\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010\u0017\u0012\u0010\b\u0002\u00108\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0017\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\"\u0012\b\b\u0002\u0010<\u001a\u00020\u0003\u0012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u0000\u0012\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010?J\n\u0010\u0080\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010\u0082\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\f\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\f\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\f\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\n\u0010\u0087\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0012\u0010\u0089\u0001\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017HÆ\u0003J\n\u0010\u008a\u0001\u001a\u00020\u001aHÆ\u0003J\n\u0010\u008b\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0090\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0092\u0001\u001a\u0004\u0018\u00010\"HÆ\u0003J*\u0010\u0093\u0001\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010$j\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u0001`%HÆ\u0003J\u0012\u0010\u0094\u0001\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0017HÆ\u0003J\f\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010\u0096\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010\u0097\u0001\u001a\u0004\u0018\u00010)HÆ\u0003J\f\u0010\u0098\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u0099\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u009a\u0001\u001a\u0004\u0018\u00010-HÆ\u0003¢\u0006\u0002\u0010dJ\u0011\u0010\u009b\u0001\u001a\u0004\u0018\u00010-HÆ\u0003¢\u0006\u0002\u0010dJ\f\u0010\u009c\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u009d\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010 \u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010¡\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010¢\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010£\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0012\u0010¤\u0001\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010\u0017HÆ\u0003J\u0012\u0010¥\u0001\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0017HÆ\u0003J\f\u0010¦\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010§\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010¨\u0001\u001a\u0004\u0018\u00010\"HÆ\u0003J\n\u0010©\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010ª\u0001\u001a\u0004\u0018\u00010\u0000HÆ\u0003J\f\u0010«\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010¬\u0001\u001a\u00020\u0003HÆ\u0003J\f\u0010\u00ad\u0001\u001a\u0004\u0018\u00010\tHÆ\u0003J\f\u0010®\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010¯\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010°\u0001\u001a\u00020\u0003HÆ\u0003Jþ\u0004\u0010±\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0014\u001a\u00020\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2(\b\u0002\u0010#\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010$j\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u0001`%2\u0010\b\u0002\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00172\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010,\u001a\u0004\u0018\u00010-2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u00106\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010\u00172\u0010\b\u0002\u00108\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u00172\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\"2\b\b\u0002\u0010<\u001a\u00020\u00032\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0003\u0010²\u0001J\u0016\u0010³\u0001\u001a\u00030´\u00012\t\u0010µ\u0001\u001a\u0004\u0018\u00010\"HÖ\u0003J\n\u0010¶\u0001\u001a\u00020\u001aHÖ\u0001J\n\u0010·\u0001\u001a\u00020\u0003HÖ\u0001R\u0018\u00105\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010AR\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010AR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u0010AR\u0018\u0010*\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010AR\u0018\u0010;\u001a\u0004\u0018\u00010\"8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u0010FR\u0018\u0010(\u001a\u0004\u0018\u00010)8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u0018\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010AR\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010KR\u001e\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010A\"\u0004\bM\u0010NR\u0018\u00102\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bO\u0010AR\u0018\u00104\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010AR\u0018\u00103\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010AR\u0018\u0010'\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u0010AR\u0018\u0010:\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bS\u0010AR\u0018\u0010 \u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bT\u0010AR\u0018\u00101\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bU\u0010AR\u0018\u00100\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010AR\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bW\u0010AR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bX\u0010AR\u001e\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00178\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bY\u0010ZR\u0018\u0010=\u001a\u0004\u0018\u00010\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b[\u0010\\R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b]\u0010AR6\u0010#\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010$j\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u0001`%8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010_R\u0016\u0010\u0019\u001a\u00020\u001a8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b`\u0010aR\u0016\u0010\f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bb\u0010AR\u001a\u0010.\u001a\u0004\u0018\u00010-8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010e\u001a\u0004\bc\u0010dR\u0018\u0010+\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bf\u0010AR\u001a\u0010,\u001a\u0004\u0018\u00010-8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010e\u001a\u0004\bg\u0010dR\u0018\u0010>\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u0010AR\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bi\u0010jR \u0010\u000f\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010j\"\u0004\bl\u0010mR\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00108\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bn\u0010jR\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00108\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bo\u0010jR\u0018\u0010/\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bp\u0010AR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bq\u0010AR\u0016\u0010\u0014\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\br\u0010AR\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bs\u0010AR\u0016\u0010<\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bt\u0010AR\u001e\u00108\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u00178\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bu\u0010ZR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bv\u0010AR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bw\u0010AR\u001e\u00106\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010\u00178\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bx\u0010ZR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\by\u0010AR\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bz\u0010AR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b{\u0010AR\u001e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00178\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b|\u0010ZR\u0018\u0010!\u001a\u0004\u0018\u00010\"8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b}\u0010FR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b~\u0010AR\u0018\u00109\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u007f\u0010A¨\u0006Ã\u0001"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "Ljava/io/Serializable;", "id", "", "type", FormFragment.KEY_REQUIRED, "enabled", FormFragment.KEY_VISIBLE, "dynamicHandlers", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$DynamicHandlers;", WebViewFragment.ARG_SUB_TYPE, "text", "loading", "duration", FormFragment.KEY_SELECTED, "onClick", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler;", "onChange", "onValidated", "onComplete", "secure", "keyboard", "validation", "", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Validation;", "lines", "", "blockCount", "blockLength", "title", SubType.Label.SUBTITLE, "hint", "helperText", "value", "", "labels", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", FirebaseAnalytics.Param.ITEMS, "format", "dateRange", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$DateRange;", FirebaseAnalytics.Param.CONTENT, "maxNumberOfFiles", "minFileSize", "", "maxFileSize", "pickerTitle", "helperTextIdle", "helperTextActive", "errorTextFile", "errorTextSizeMin", "errorTextSizeMax", "allowMultipleTypes", "supportedFiles", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SupportedFile;", "subComponents", "width", "height", "data", "selectionMode", "itemsGenerator", "numberOfColumns", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$DynamicHandlers;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/util/LinkedHashMap;Ljava/util/List;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$DateRange;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;Ljava/lang/String;)V", "getAllowMultipleTypes", "()Ljava/lang/String;", "getBlockCount", "getBlockLength", "getContent", "getData", "()Ljava/lang/Object;", "getDateRange", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$DateRange;", "getDuration", "getDynamicHandlers", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$DynamicHandlers;", "getEnabled", "setEnabled", "(Ljava/lang/String;)V", "getErrorTextFile", "getErrorTextSizeMax", "getErrorTextSizeMin", "getFormat", "getHeight", "getHelperText", "getHelperTextActive", "getHelperTextIdle", "getHint", "getId", "getItems", "()Ljava/util/List;", "getItemsGenerator", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "getKeyboard", "getLabels", "()Ljava/util/LinkedHashMap;", "getLines", "()I", "getLoading", "getMaxFileSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getMaxNumberOfFiles", "getMinFileSize", "getNumberOfColumns", "getOnChange", "()Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler;", "getOnClick", "setOnClick", "(Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler;)V", "getOnComplete", "getOnValidated", "getPickerTitle", "getRequired", "getSecure", "getSelected", "getSelectionMode", "getSubComponents", "getSubTitle", "getSubType", "getSupportedFiles", "getText", "getTitle", "getType", "getValidation", "getValue", "getVisible", "getWidth", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$DynamicHandlers;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/util/LinkedHashMap;Ljava/util/List;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$DateRange;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;Ljava/lang/String;)Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "DateRange", "Dimensions", "DynamicHandlers", "Handler", "Keyboard", "SelectionMode", "State", "SubType", "SupportedFile", "Type", "Validation", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
            /* loaded from: classes2.dex */
            public static final /* data */ class Component implements Serializable {

                @SerializedName("allowMultipleTypes")
                private final String allowMultipleTypes;

                @SerializedName("blockCount")
                private final String blockCount;

                @SerializedName("blockLength")
                private final String blockLength;

                @SerializedName(FirebaseAnalytics.Param.CONTENT)
                private final String content;

                @SerializedName("data")
                private final Object data;

                @SerializedName("dateRange")
                private final DateRange dateRange;

                @SerializedName("duration")
                private final String duration;

                @SerializedName("dynamicHandlers")
                private final DynamicHandlers dynamicHandlers;

                @SerializedName("enabled")
                private String enabled;

                @SerializedName("errorTextFile")
                private final String errorTextFile;

                @SerializedName("errorTextSizeMax")
                private final String errorTextSizeMax;

                @SerializedName("errorTextSizeMin")
                private final String errorTextSizeMin;

                @SerializedName("format")
                private final String format;

                @SerializedName("height")
                private final String height;

                @SerializedName("helperText")
                private final String helperText;

                @SerializedName("helperTextActive")
                private final String helperTextActive;

                @SerializedName("helperTextIdle")
                private final String helperTextIdle;

                @SerializedName("hint")
                private final String hint;

                @SerializedName("id")
                private final String id;

                @SerializedName(FirebaseAnalytics.Param.ITEMS)
                private final List<String> items;

                @SerializedName("itemsGenerator")
                private final Component itemsGenerator;

                @SerializedName("keyboard")
                private final String keyboard;

                @SerializedName("labels")
                private final LinkedHashMap<String, String> labels;

                @SerializedName("lines")
                private final int lines;

                @SerializedName("loading")
                private final String loading;

                @SerializedName("maxFileSize")
                private final Long maxFileSize;

                @SerializedName("maxNumberOfFiles")
                private final String maxNumberOfFiles;

                @SerializedName("minFileSize")
                private final Long minFileSize;

                @SerializedName("numberOfColumns")
                private final String numberOfColumns;

                @SerializedName("onChange")
                private final Handler onChange;

                @SerializedName("onClick")
                private Handler onClick;

                @SerializedName("onComplete")
                private final Handler onComplete;

                @SerializedName("onValidated")
                private final Handler onValidated;

                @SerializedName("pickerTitle")
                private final String pickerTitle;

                @SerializedName(FormFragment.KEY_REQUIRED)
                private final String required;

                @SerializedName("secure")
                private final String secure;

                @SerializedName(FormFragment.KEY_SELECTED)
                private final String selected;

                @SerializedName("selectionMode")
                private final String selectionMode;

                @SerializedName("subComponents")
                private final List<Component> subComponents;

                @SerializedName(SubType.Label.SUBTITLE)
                private final String subTitle;

                @SerializedName(WebViewFragment.ARG_SUB_TYPE)
                private final String subType;

                @SerializedName("supportedFiles")
                private final List<SupportedFile> supportedFiles;

                @SerializedName("text")
                private final String text;

                @SerializedName("title")
                private final String title;

                @SerializedName("type")
                private final String type;

                @SerializedName("validation")
                private final List<Validation> validation;

                @SerializedName("value")
                private final Object value;

                @SerializedName(FormFragment.KEY_VISIBLE)
                private final String visible;

                @SerializedName("width")
                private final String width;

                public Component() {
                    this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, -1, 131071, null);
                }

                /* renamed from: component1, reason: from getter */
                public final String getId() {
                    return this.id;
                }

                /* renamed from: component10, reason: from getter */
                public final String getDuration() {
                    return this.duration;
                }

                /* renamed from: component11, reason: from getter */
                public final String getSelected() {
                    return this.selected;
                }

                /* renamed from: component12, reason: from getter */
                public final Handler getOnClick() {
                    return this.onClick;
                }

                /* renamed from: component13, reason: from getter */
                public final Handler getOnChange() {
                    return this.onChange;
                }

                /* renamed from: component14, reason: from getter */
                public final Handler getOnValidated() {
                    return this.onValidated;
                }

                /* renamed from: component15, reason: from getter */
                public final Handler getOnComplete() {
                    return this.onComplete;
                }

                /* renamed from: component16, reason: from getter */
                public final String getSecure() {
                    return this.secure;
                }

                /* renamed from: component17, reason: from getter */
                public final String getKeyboard() {
                    return this.keyboard;
                }

                public final List<Validation> component18() {
                    return this.validation;
                }

                /* renamed from: component19, reason: from getter */
                public final int getLines() {
                    return this.lines;
                }

                /* renamed from: component2, reason: from getter */
                public final String getType() {
                    return this.type;
                }

                /* renamed from: component20, reason: from getter */
                public final String getBlockCount() {
                    return this.blockCount;
                }

                /* renamed from: component21, reason: from getter */
                public final String getBlockLength() {
                    return this.blockLength;
                }

                /* renamed from: component22, reason: from getter */
                public final String getTitle() {
                    return this.title;
                }

                /* renamed from: component23, reason: from getter */
                public final String getSubTitle() {
                    return this.subTitle;
                }

                /* renamed from: component24, reason: from getter */
                public final String getHint() {
                    return this.hint;
                }

                /* renamed from: component25, reason: from getter */
                public final String getHelperText() {
                    return this.helperText;
                }

                /* renamed from: component26, reason: from getter */
                public final Object getValue() {
                    return this.value;
                }

                public final LinkedHashMap<String, String> component27() {
                    return this.labels;
                }

                public final List<String> component28() {
                    return this.items;
                }

                /* renamed from: component29, reason: from getter */
                public final String getFormat() {
                    return this.format;
                }

                /* renamed from: component3, reason: from getter */
                public final String getRequired() {
                    return this.required;
                }

                /* renamed from: component30, reason: from getter */
                public final DateRange getDateRange() {
                    return this.dateRange;
                }

                /* renamed from: component31, reason: from getter */
                public final String getContent() {
                    return this.content;
                }

                /* renamed from: component32, reason: from getter */
                public final String getMaxNumberOfFiles() {
                    return this.maxNumberOfFiles;
                }

                /* renamed from: component33, reason: from getter */
                public final Long getMinFileSize() {
                    return this.minFileSize;
                }

                /* renamed from: component34, reason: from getter */
                public final Long getMaxFileSize() {
                    return this.maxFileSize;
                }

                /* renamed from: component35, reason: from getter */
                public final String getPickerTitle() {
                    return this.pickerTitle;
                }

                /* renamed from: component36, reason: from getter */
                public final String getHelperTextIdle() {
                    return this.helperTextIdle;
                }

                /* renamed from: component37, reason: from getter */
                public final String getHelperTextActive() {
                    return this.helperTextActive;
                }

                /* renamed from: component38, reason: from getter */
                public final String getErrorTextFile() {
                    return this.errorTextFile;
                }

                /* renamed from: component39, reason: from getter */
                public final String getErrorTextSizeMin() {
                    return this.errorTextSizeMin;
                }

                /* renamed from: component4, reason: from getter */
                public final String getEnabled() {
                    return this.enabled;
                }

                /* renamed from: component40, reason: from getter */
                public final String getErrorTextSizeMax() {
                    return this.errorTextSizeMax;
                }

                /* renamed from: component41, reason: from getter */
                public final String getAllowMultipleTypes() {
                    return this.allowMultipleTypes;
                }

                public final List<SupportedFile> component42() {
                    return this.supportedFiles;
                }

                public final List<Component> component43() {
                    return this.subComponents;
                }

                /* renamed from: component44, reason: from getter */
                public final String getWidth() {
                    return this.width;
                }

                /* renamed from: component45, reason: from getter */
                public final String getHeight() {
                    return this.height;
                }

                /* renamed from: component46, reason: from getter */
                public final Object getData() {
                    return this.data;
                }

                /* renamed from: component47, reason: from getter */
                public final String getSelectionMode() {
                    return this.selectionMode;
                }

                /* renamed from: component48, reason: from getter */
                public final Component getItemsGenerator() {
                    return this.itemsGenerator;
                }

                /* renamed from: component49, reason: from getter */
                public final String getNumberOfColumns() {
                    return this.numberOfColumns;
                }

                /* renamed from: component5, reason: from getter */
                public final String getVisible() {
                    return this.visible;
                }

                /* renamed from: component6, reason: from getter */
                public final DynamicHandlers getDynamicHandlers() {
                    return this.dynamicHandlers;
                }

                /* renamed from: component7, reason: from getter */
                public final String getSubType() {
                    return this.subType;
                }

                /* renamed from: component8, reason: from getter */
                public final String getText() {
                    return this.text;
                }

                /* renamed from: component9, reason: from getter */
                public final String getLoading() {
                    return this.loading;
                }

                public final Component copy(String id2, String type, String required, String enabled, String visible, DynamicHandlers dynamicHandlers, String subType, String text, String loading, String duration, String selected, Handler onClick, Handler onChange, Handler onValidated, Handler onComplete, String secure, String keyboard, List<Validation> validation, int lines, String blockCount, String blockLength, String title, String subTitle, String hint, String helperText, Object value, LinkedHashMap<String, String> labels, List<String> items, String format, DateRange dateRange, String content, String maxNumberOfFiles, Long minFileSize, Long maxFileSize, String pickerTitle, String helperTextIdle, String helperTextActive, String errorTextFile, String errorTextSizeMin, String errorTextSizeMax, String allowMultipleTypes, List<SupportedFile> supportedFiles, List<Component> subComponents, String width, String height, Object data, String selectionMode, Component itemsGenerator, String numberOfColumns) {
                    Intrinsics.checkNotNullParameter(id2, "id");
                    Intrinsics.checkNotNullParameter(type, "type");
                    Intrinsics.checkNotNullParameter(required, "required");
                    Intrinsics.checkNotNullParameter(enabled, "enabled");
                    Intrinsics.checkNotNullParameter(visible, "visible");
                    Intrinsics.checkNotNullParameter(loading, "loading");
                    Intrinsics.checkNotNullParameter(selected, "selected");
                    Intrinsics.checkNotNullParameter(secure, "secure");
                    Intrinsics.checkNotNullParameter(selectionMode, "selectionMode");
                    return new Component(id2, type, required, enabled, visible, dynamicHandlers, subType, text, loading, duration, selected, onClick, onChange, onValidated, onComplete, secure, keyboard, validation, lines, blockCount, blockLength, title, subTitle, hint, helperText, value, labels, items, format, dateRange, content, maxNumberOfFiles, minFileSize, maxFileSize, pickerTitle, helperTextIdle, helperTextActive, errorTextFile, errorTextSizeMin, errorTextSizeMax, allowMultipleTypes, supportedFiles, subComponents, width, height, data, selectionMode, itemsGenerator, numberOfColumns);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof Component)) {
                        return false;
                    }
                    Component component = (Component) other;
                    return Intrinsics.areEqual(this.id, component.id) && Intrinsics.areEqual(this.type, component.type) && Intrinsics.areEqual(this.required, component.required) && Intrinsics.areEqual(this.enabled, component.enabled) && Intrinsics.areEqual(this.visible, component.visible) && Intrinsics.areEqual(this.dynamicHandlers, component.dynamicHandlers) && Intrinsics.areEqual(this.subType, component.subType) && Intrinsics.areEqual(this.text, component.text) && Intrinsics.areEqual(this.loading, component.loading) && Intrinsics.areEqual(this.duration, component.duration) && Intrinsics.areEqual(this.selected, component.selected) && Intrinsics.areEqual(this.onClick, component.onClick) && Intrinsics.areEqual(this.onChange, component.onChange) && Intrinsics.areEqual(this.onValidated, component.onValidated) && Intrinsics.areEqual(this.onComplete, component.onComplete) && Intrinsics.areEqual(this.secure, component.secure) && Intrinsics.areEqual(this.keyboard, component.keyboard) && Intrinsics.areEqual(this.validation, component.validation) && this.lines == component.lines && Intrinsics.areEqual(this.blockCount, component.blockCount) && Intrinsics.areEqual(this.blockLength, component.blockLength) && Intrinsics.areEqual(this.title, component.title) && Intrinsics.areEqual(this.subTitle, component.subTitle) && Intrinsics.areEqual(this.hint, component.hint) && Intrinsics.areEqual(this.helperText, component.helperText) && Intrinsics.areEqual(this.value, component.value) && Intrinsics.areEqual(this.labels, component.labels) && Intrinsics.areEqual(this.items, component.items) && Intrinsics.areEqual(this.format, component.format) && Intrinsics.areEqual(this.dateRange, component.dateRange) && Intrinsics.areEqual(this.content, component.content) && Intrinsics.areEqual(this.maxNumberOfFiles, component.maxNumberOfFiles) && Intrinsics.areEqual(this.minFileSize, component.minFileSize) && Intrinsics.areEqual(this.maxFileSize, component.maxFileSize) && Intrinsics.areEqual(this.pickerTitle, component.pickerTitle) && Intrinsics.areEqual(this.helperTextIdle, component.helperTextIdle) && Intrinsics.areEqual(this.helperTextActive, component.helperTextActive) && Intrinsics.areEqual(this.errorTextFile, component.errorTextFile) && Intrinsics.areEqual(this.errorTextSizeMin, component.errorTextSizeMin) && Intrinsics.areEqual(this.errorTextSizeMax, component.errorTextSizeMax) && Intrinsics.areEqual(this.allowMultipleTypes, component.allowMultipleTypes) && Intrinsics.areEqual(this.supportedFiles, component.supportedFiles) && Intrinsics.areEqual(this.subComponents, component.subComponents) && Intrinsics.areEqual(this.width, component.width) && Intrinsics.areEqual(this.height, component.height) && Intrinsics.areEqual(this.data, component.data) && Intrinsics.areEqual(this.selectionMode, component.selectionMode) && Intrinsics.areEqual(this.itemsGenerator, component.itemsGenerator) && Intrinsics.areEqual(this.numberOfColumns, component.numberOfColumns);
                }

                public int hashCode() {
                    int hashCode = ((((((((this.id.hashCode() * 31) + this.type.hashCode()) * 31) + this.required.hashCode()) * 31) + this.enabled.hashCode()) * 31) + this.visible.hashCode()) * 31;
                    DynamicHandlers dynamicHandlers = this.dynamicHandlers;
                    int hashCode2 = (hashCode + (dynamicHandlers == null ? 0 : dynamicHandlers.hashCode())) * 31;
                    String str = this.subType;
                    int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
                    String str2 = this.text;
                    int hashCode4 = (((hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.loading.hashCode()) * 31;
                    String str3 = this.duration;
                    int hashCode5 = (((hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.selected.hashCode()) * 31;
                    Handler handler = this.onClick;
                    int hashCode6 = (hashCode5 + (handler == null ? 0 : handler.hashCode())) * 31;
                    Handler handler2 = this.onChange;
                    int hashCode7 = (hashCode6 + (handler2 == null ? 0 : handler2.hashCode())) * 31;
                    Handler handler3 = this.onValidated;
                    int hashCode8 = (hashCode7 + (handler3 == null ? 0 : handler3.hashCode())) * 31;
                    Handler handler4 = this.onComplete;
                    int hashCode9 = (((hashCode8 + (handler4 == null ? 0 : handler4.hashCode())) * 31) + this.secure.hashCode()) * 31;
                    String str4 = this.keyboard;
                    int hashCode10 = (hashCode9 + (str4 == null ? 0 : str4.hashCode())) * 31;
                    List<Validation> list = this.validation;
                    int hashCode11 = (((hashCode10 + (list == null ? 0 : list.hashCode())) * 31) + this.lines) * 31;
                    String str5 = this.blockCount;
                    int hashCode12 = (hashCode11 + (str5 == null ? 0 : str5.hashCode())) * 31;
                    String str6 = this.blockLength;
                    int hashCode13 = (hashCode12 + (str6 == null ? 0 : str6.hashCode())) * 31;
                    String str7 = this.title;
                    int hashCode14 = (hashCode13 + (str7 == null ? 0 : str7.hashCode())) * 31;
                    String str8 = this.subTitle;
                    int hashCode15 = (hashCode14 + (str8 == null ? 0 : str8.hashCode())) * 31;
                    String str9 = this.hint;
                    int hashCode16 = (hashCode15 + (str9 == null ? 0 : str9.hashCode())) * 31;
                    String str10 = this.helperText;
                    int hashCode17 = (hashCode16 + (str10 == null ? 0 : str10.hashCode())) * 31;
                    Object obj = this.value;
                    int hashCode18 = (hashCode17 + (obj == null ? 0 : obj.hashCode())) * 31;
                    LinkedHashMap<String, String> linkedHashMap = this.labels;
                    int hashCode19 = (hashCode18 + (linkedHashMap == null ? 0 : linkedHashMap.hashCode())) * 31;
                    List<String> list2 = this.items;
                    int hashCode20 = (hashCode19 + (list2 == null ? 0 : list2.hashCode())) * 31;
                    String str11 = this.format;
                    int hashCode21 = (hashCode20 + (str11 == null ? 0 : str11.hashCode())) * 31;
                    DateRange dateRange = this.dateRange;
                    int hashCode22 = (hashCode21 + (dateRange == null ? 0 : dateRange.hashCode())) * 31;
                    String str12 = this.content;
                    int hashCode23 = (hashCode22 + (str12 == null ? 0 : str12.hashCode())) * 31;
                    String str13 = this.maxNumberOfFiles;
                    int hashCode24 = (hashCode23 + (str13 == null ? 0 : str13.hashCode())) * 31;
                    Long l = this.minFileSize;
                    int hashCode25 = (hashCode24 + (l == null ? 0 : l.hashCode())) * 31;
                    Long l2 = this.maxFileSize;
                    int hashCode26 = (hashCode25 + (l2 == null ? 0 : l2.hashCode())) * 31;
                    String str14 = this.pickerTitle;
                    int hashCode27 = (hashCode26 + (str14 == null ? 0 : str14.hashCode())) * 31;
                    String str15 = this.helperTextIdle;
                    int hashCode28 = (hashCode27 + (str15 == null ? 0 : str15.hashCode())) * 31;
                    String str16 = this.helperTextActive;
                    int hashCode29 = (hashCode28 + (str16 == null ? 0 : str16.hashCode())) * 31;
                    String str17 = this.errorTextFile;
                    int hashCode30 = (hashCode29 + (str17 == null ? 0 : str17.hashCode())) * 31;
                    String str18 = this.errorTextSizeMin;
                    int hashCode31 = (hashCode30 + (str18 == null ? 0 : str18.hashCode())) * 31;
                    String str19 = this.errorTextSizeMax;
                    int hashCode32 = (hashCode31 + (str19 == null ? 0 : str19.hashCode())) * 31;
                    String str20 = this.allowMultipleTypes;
                    int hashCode33 = (hashCode32 + (str20 == null ? 0 : str20.hashCode())) * 31;
                    List<SupportedFile> list3 = this.supportedFiles;
                    int hashCode34 = (hashCode33 + (list3 == null ? 0 : list3.hashCode())) * 31;
                    List<Component> list4 = this.subComponents;
                    int hashCode35 = (hashCode34 + (list4 == null ? 0 : list4.hashCode())) * 31;
                    String str21 = this.width;
                    int hashCode36 = (hashCode35 + (str21 == null ? 0 : str21.hashCode())) * 31;
                    String str22 = this.height;
                    int hashCode37 = (hashCode36 + (str22 == null ? 0 : str22.hashCode())) * 31;
                    Object obj2 = this.data;
                    int hashCode38 = (((hashCode37 + (obj2 == null ? 0 : obj2.hashCode())) * 31) + this.selectionMode.hashCode()) * 31;
                    Component component = this.itemsGenerator;
                    int hashCode39 = (hashCode38 + (component == null ? 0 : component.hashCode())) * 31;
                    String str23 = this.numberOfColumns;
                    return hashCode39 + (str23 != null ? str23.hashCode() : 0);
                }

                public String toString() {
                    return "Component(id=" + this.id + ", type=" + this.type + ", required=" + this.required + ", enabled=" + this.enabled + ", visible=" + this.visible + ", dynamicHandlers=" + this.dynamicHandlers + ", subType=" + this.subType + ", text=" + this.text + ", loading=" + this.loading + ", duration=" + this.duration + ", selected=" + this.selected + ", onClick=" + this.onClick + ", onChange=" + this.onChange + ", onValidated=" + this.onValidated + ", onComplete=" + this.onComplete + ", secure=" + this.secure + ", keyboard=" + this.keyboard + ", validation=" + this.validation + ", lines=" + this.lines + ", blockCount=" + this.blockCount + ", blockLength=" + this.blockLength + ", title=" + this.title + ", subTitle=" + this.subTitle + ", hint=" + this.hint + ", helperText=" + this.helperText + ", value=" + this.value + ", labels=" + this.labels + ", items=" + this.items + ", format=" + this.format + ", dateRange=" + this.dateRange + ", content=" + this.content + ", maxNumberOfFiles=" + this.maxNumberOfFiles + ", minFileSize=" + this.minFileSize + ", maxFileSize=" + this.maxFileSize + ", pickerTitle=" + this.pickerTitle + ", helperTextIdle=" + this.helperTextIdle + ", helperTextActive=" + this.helperTextActive + ", errorTextFile=" + this.errorTextFile + ", errorTextSizeMin=" + this.errorTextSizeMin + ", errorTextSizeMax=" + this.errorTextSizeMax + ", allowMultipleTypes=" + this.allowMultipleTypes + ", supportedFiles=" + this.supportedFiles + ", subComponents=" + this.subComponents + ", width=" + this.width + ", height=" + this.height + ", data=" + this.data + ", selectionMode=" + this.selectionMode + ", itemsGenerator=" + this.itemsGenerator + ", numberOfColumns=" + this.numberOfColumns + ')';
                }

                public Component(String id2, String type, String required, String enabled, String visible, DynamicHandlers dynamicHandlers, String str, String str2, String loading, String str3, String selected, Handler handler, Handler handler2, Handler handler3, Handler handler4, String secure, String str4, List<Validation> list, int i, String str5, String str6, String str7, String str8, String str9, String str10, Object obj, LinkedHashMap<String, String> linkedHashMap, List<String> list2, String str11, DateRange dateRange, String str12, String str13, Long l, Long l2, String str14, String str15, String str16, String str17, String str18, String str19, String str20, List<SupportedFile> list3, List<Component> list4, String str21, String str22, Object obj2, String selectionMode, Component component, String str23) {
                    Intrinsics.checkNotNullParameter(id2, "id");
                    Intrinsics.checkNotNullParameter(type, "type");
                    Intrinsics.checkNotNullParameter(required, "required");
                    Intrinsics.checkNotNullParameter(enabled, "enabled");
                    Intrinsics.checkNotNullParameter(visible, "visible");
                    Intrinsics.checkNotNullParameter(loading, "loading");
                    Intrinsics.checkNotNullParameter(selected, "selected");
                    Intrinsics.checkNotNullParameter(secure, "secure");
                    Intrinsics.checkNotNullParameter(selectionMode, "selectionMode");
                    this.id = id2;
                    this.type = type;
                    this.required = required;
                    this.enabled = enabled;
                    this.visible = visible;
                    this.dynamicHandlers = dynamicHandlers;
                    this.subType = str;
                    this.text = str2;
                    this.loading = loading;
                    this.duration = str3;
                    this.selected = selected;
                    this.onClick = handler;
                    this.onChange = handler2;
                    this.onValidated = handler3;
                    this.onComplete = handler4;
                    this.secure = secure;
                    this.keyboard = str4;
                    this.validation = list;
                    this.lines = i;
                    this.blockCount = str5;
                    this.blockLength = str6;
                    this.title = str7;
                    this.subTitle = str8;
                    this.hint = str9;
                    this.helperText = str10;
                    this.value = obj;
                    this.labels = linkedHashMap;
                    this.items = list2;
                    this.format = str11;
                    this.dateRange = dateRange;
                    this.content = str12;
                    this.maxNumberOfFiles = str13;
                    this.minFileSize = l;
                    this.maxFileSize = l2;
                    this.pickerTitle = str14;
                    this.helperTextIdle = str15;
                    this.helperTextActive = str16;
                    this.errorTextFile = str17;
                    this.errorTextSizeMin = str18;
                    this.errorTextSizeMax = str19;
                    this.allowMultipleTypes = str20;
                    this.supportedFiles = list3;
                    this.subComponents = list4;
                    this.width = str21;
                    this.height = str22;
                    this.data = obj2;
                    this.selectionMode = selectionMode;
                    this.itemsGenerator = component;
                    this.numberOfColumns = str23;
                }

                public final String getId() {
                    return this.id;
                }

                public final String getType() {
                    return this.type;
                }

                public final String getRequired() {
                    return this.required;
                }

                public final String getEnabled() {
                    return this.enabled;
                }

                public final void setEnabled(String str) {
                    Intrinsics.checkNotNullParameter(str, "<set-?>");
                    this.enabled = str;
                }

                public final String getVisible() {
                    return this.visible;
                }

                public final DynamicHandlers getDynamicHandlers() {
                    return this.dynamicHandlers;
                }

                public final String getSubType() {
                    return this.subType;
                }

                public final String getText() {
                    return this.text;
                }

                public final String getLoading() {
                    return this.loading;
                }

                public final String getDuration() {
                    return this.duration;
                }

                public final String getSelected() {
                    return this.selected;
                }

                public final Handler getOnClick() {
                    return this.onClick;
                }

                public final void setOnClick(Handler handler) {
                    this.onClick = handler;
                }

                public final Handler getOnChange() {
                    return this.onChange;
                }

                public final Handler getOnValidated() {
                    return this.onValidated;
                }

                public final Handler getOnComplete() {
                    return this.onComplete;
                }

                public final String getSecure() {
                    return this.secure;
                }

                public final String getKeyboard() {
                    return this.keyboard;
                }

                public final List<Validation> getValidation() {
                    return this.validation;
                }

                public final int getLines() {
                    return this.lines;
                }

                public final String getBlockCount() {
                    return this.blockCount;
                }

                public final String getBlockLength() {
                    return this.blockLength;
                }

                public final String getTitle() {
                    return this.title;
                }

                public final String getSubTitle() {
                    return this.subTitle;
                }

                public final String getHint() {
                    return this.hint;
                }

                public final String getHelperText() {
                    return this.helperText;
                }

                public final Object getValue() {
                    return this.value;
                }

                public final LinkedHashMap<String, String> getLabels() {
                    return this.labels;
                }

                public final List<String> getItems() {
                    return this.items;
                }

                public final String getFormat() {
                    return this.format;
                }

                public final DateRange getDateRange() {
                    return this.dateRange;
                }

                public final String getContent() {
                    return this.content;
                }

                public final String getMaxNumberOfFiles() {
                    return this.maxNumberOfFiles;
                }

                public final Long getMinFileSize() {
                    return this.minFileSize;
                }

                public final Long getMaxFileSize() {
                    return this.maxFileSize;
                }

                public final String getPickerTitle() {
                    return this.pickerTitle;
                }

                public final String getHelperTextIdle() {
                    return this.helperTextIdle;
                }

                public final String getHelperTextActive() {
                    return this.helperTextActive;
                }

                public final String getErrorTextFile() {
                    return this.errorTextFile;
                }

                public final String getErrorTextSizeMin() {
                    return this.errorTextSizeMin;
                }

                public final String getErrorTextSizeMax() {
                    return this.errorTextSizeMax;
                }

                public final String getAllowMultipleTypes() {
                    return this.allowMultipleTypes;
                }

                public /* synthetic */ Component(String str, String str2, String str3, String str4, String str5, DynamicHandlers dynamicHandlers, String str6, String str7, String str8, String str9, String str10, Handler handler, Handler handler2, Handler handler3, Handler handler4, String str11, String str12, List list, int i, String str13, String str14, String str15, String str16, String str17, String str18, Object obj, LinkedHashMap linkedHashMap, List list2, String str19, DateRange dateRange, String str20, String str21, Long l, Long l2, String str22, String str23, String str24, String str25, String str26, String str27, String str28, List list3, List list4, String str29, String str30, Object obj2, String str31, Component component, String str32, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i2 & 1) != 0 ? "" : str, (i2 & 2) == 0 ? str2 : "", (i2 & 4) != 0 ? "no" : str3, (i2 & 8) != 0 ? "yes" : str4, (i2 & 16) == 0 ? str5 : "yes", (i2 & 32) != 0 ? null : dynamicHandlers, (i2 & 64) != 0 ? null : str6, (i2 & 128) != 0 ? null : str7, (i2 & 256) != 0 ? "no" : str8, (i2 & 512) != 0 ? null : str9, (i2 & 1024) != 0 ? "no" : str10, (i2 & 2048) != 0 ? null : handler, (i2 & 4096) != 0 ? null : handler2, (i2 & 8192) != 0 ? null : handler3, (i2 & 16384) != 0 ? null : handler4, (i2 & 32768) != 0 ? "no" : str11, (i2 & 65536) != 0 ? null : str12, (i2 & 131072) != 0 ? null : list, (i2 & 262144) != 0 ? 5 : i, (i2 & 524288) != 0 ? "6" : str13, (i2 & 1048576) != 0 ? "1" : str14, (i2 & 2097152) != 0 ? null : str15, (i2 & 4194304) != 0 ? null : str16, (i2 & 8388608) != 0 ? null : str17, (i2 & 16777216) != 0 ? null : str18, (i2 & PDButton.FLAG_RADIOS_IN_UNISON) != 0 ? null : obj, (i2 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? null : linkedHashMap, (i2 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? null : list2, (i2 & 268435456) != 0 ? null : str19, (i2 & 536870912) != 0 ? null : dateRange, (i2 & 1073741824) != 0 ? null : str20, (i2 & Integer.MIN_VALUE) != 0 ? null : str21, (i3 & 1) != 0 ? null : l, (i3 & 2) != 0 ? null : l2, (i3 & 4) != 0 ? null : str22, (i3 & 8) != 0 ? null : str23, (i3 & 16) != 0 ? null : str24, (i3 & 32) != 0 ? null : str25, (i3 & 64) != 0 ? null : str26, (i3 & 128) != 0 ? null : str27, (i3 & 256) != 0 ? "no" : str28, (i3 & 512) != 0 ? WorkflowModule.INSTANCE.getDEFAULT_SUPPORTED_FILES() : list3, (i3 & 1024) != 0 ? null : list4, (i3 & 2048) != 0 ? null : str29, (i3 & 4096) != 0 ? null : str30, (i3 & 8192) != 0 ? null : obj2, (i3 & 16384) != 0 ? SelectionMode.SINGLE : str31, (i3 & 32768) != 0 ? null : component, (i3 & 65536) == 0 ? str32 : "1");
                }

                public final List<SupportedFile> getSupportedFiles() {
                    return this.supportedFiles;
                }

                public final List<Component> getSubComponents() {
                    return this.subComponents;
                }

                public final String getWidth() {
                    return this.width;
                }

                public final String getHeight() {
                    return this.height;
                }

                public final Object getData() {
                    return this.data;
                }

                public final String getSelectionMode() {
                    return this.selectionMode;
                }

                public final Component getItemsGenerator() {
                    return this.itemsGenerator;
                }

                public final String getNumberOfColumns() {
                    return this.numberOfColumns;
                }

                /* compiled from: WorkflowConfig.kt */
                @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007J\u0011\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J+\u0010\r\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0004HÖ\u0001R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$DynamicHandlers;", "Ljava/io/Serializable;", ViewHierarchyNode.JsonKeys.CHILDREN, "", "", "handlers", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler;", "(Ljava/util/List;Ljava/util/List;)V", "getChildren", "()Ljava/util/List;", "getHandlers", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                /* loaded from: classes2.dex */
                public static final /* data */ class DynamicHandlers implements Serializable {

                    @SerializedName(ViewHierarchyNode.JsonKeys.CHILDREN)
                    private final List<String> children;

                    @SerializedName("handlers")
                    private final List<Handler> handlers;

                    /* JADX WARN: Multi-variable type inference failed */
                    public static /* synthetic */ DynamicHandlers copy$default(DynamicHandlers dynamicHandlers, List list, List list2, int i, Object obj) {
                        if ((i & 1) != 0) {
                            list = dynamicHandlers.children;
                        }
                        if ((i & 2) != 0) {
                            list2 = dynamicHandlers.handlers;
                        }
                        return dynamicHandlers.copy(list, list2);
                    }

                    public final List<String> component1() {
                        return this.children;
                    }

                    public final List<Handler> component2() {
                        return this.handlers;
                    }

                    public final DynamicHandlers copy(List<String> children, List<Handler> handlers) {
                        Intrinsics.checkNotNullParameter(handlers, "handlers");
                        return new DynamicHandlers(children, handlers);
                    }

                    public boolean equals(Object other) {
                        if (this == other) {
                            return true;
                        }
                        if (!(other instanceof DynamicHandlers)) {
                            return false;
                        }
                        DynamicHandlers dynamicHandlers = (DynamicHandlers) other;
                        return Intrinsics.areEqual(this.children, dynamicHandlers.children) && Intrinsics.areEqual(this.handlers, dynamicHandlers.handlers);
                    }

                    public int hashCode() {
                        List<String> list = this.children;
                        return ((list == null ? 0 : list.hashCode()) * 31) + this.handlers.hashCode();
                    }

                    public String toString() {
                        return "DynamicHandlers(children=" + this.children + ", handlers=" + this.handlers + ')';
                    }

                    public DynamicHandlers(List<String> list, List<Handler> handlers) {
                        Intrinsics.checkNotNullParameter(handlers, "handlers");
                        this.children = list;
                        this.handlers = handlers;
                    }

                    public /* synthetic */ DynamicHandlers(List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
                        this((i & 1) != 0 ? null : list, list2);
                    }

                    public final List<String> getChildren() {
                        return this.children;
                    }

                    public final List<Handler> getHandlers() {
                        return this.handlers;
                    }
                }

                /* compiled from: WorkflowConfig.kt */
                @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u0016B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J)\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0017"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Validation;", "Ljava/io/Serializable;", "type", "", "value", "errorMsg", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getErrorMsg", "()Ljava/lang/String;", "getType", "getValue", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "Type", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                /* loaded from: classes2.dex */
                public static final /* data */ class Validation implements Serializable {

                    @SerializedName("errorMsg")
                    private final String errorMsg;

                    @SerializedName("type")
                    private final String type;

                    @SerializedName("value")
                    private final String value;

                    public static /* synthetic */ Validation copy$default(Validation validation, String str, String str2, String str3, int i, Object obj) {
                        if ((i & 1) != 0) {
                            str = validation.type;
                        }
                        if ((i & 2) != 0) {
                            str2 = validation.value;
                        }
                        if ((i & 4) != 0) {
                            str3 = validation.errorMsg;
                        }
                        return validation.copy(str, str2, str3);
                    }

                    /* renamed from: component1, reason: from getter */
                    public final String getType() {
                        return this.type;
                    }

                    /* renamed from: component2, reason: from getter */
                    public final String getValue() {
                        return this.value;
                    }

                    /* renamed from: component3, reason: from getter */
                    public final String getErrorMsg() {
                        return this.errorMsg;
                    }

                    public final Validation copy(String type, String value, String errorMsg) {
                        Intrinsics.checkNotNullParameter(type, "type");
                        Intrinsics.checkNotNullParameter(value, "value");
                        return new Validation(type, value, errorMsg);
                    }

                    public boolean equals(Object other) {
                        if (this == other) {
                            return true;
                        }
                        if (!(other instanceof Validation)) {
                            return false;
                        }
                        Validation validation = (Validation) other;
                        return Intrinsics.areEqual(this.type, validation.type) && Intrinsics.areEqual(this.value, validation.value) && Intrinsics.areEqual(this.errorMsg, validation.errorMsg);
                    }

                    public int hashCode() {
                        int hashCode = ((this.type.hashCode() * 31) + this.value.hashCode()) * 31;
                        String str = this.errorMsg;
                        return hashCode + (str == null ? 0 : str.hashCode());
                    }

                    public String toString() {
                        return "Validation(type=" + this.type + ", value=" + this.value + ", errorMsg=" + this.errorMsg + ')';
                    }

                    public Validation(String type, String value, String str) {
                        Intrinsics.checkNotNullParameter(type, "type");
                        Intrinsics.checkNotNullParameter(value, "value");
                        this.type = type;
                        this.value = value;
                        this.errorMsg = str;
                    }

                    public /* synthetic */ Validation(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
                        this(str, str2, (i & 4) != 0 ? null : str3);
                    }

                    public final String getType() {
                        return this.type;
                    }

                    public final String getValue() {
                        return this.value;
                    }

                    public final String getErrorMsg() {
                        return this.errorMsg;
                    }

                    /* compiled from: WorkflowConfig.kt */
                    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Validation$Type;", "", "()V", "REGEX", "", "RULE", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                    /* loaded from: classes2.dex */
                    public static final class Type {
                        public static final Type INSTANCE = new Type();
                        public static final String REGEX = "regex";
                        public static final String RULE = "rule";

                        private Type() {
                        }
                    }
                }

                /* compiled from: WorkflowConfig.kt */
                @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001#BS\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006\u0012\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006HÆ\u0003J\u0017\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003JW\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00062\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR$\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R$\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\r¨\u0006$"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler;", "Ljava/io/Serializable;", Validation.Type.RULE, "", AnalyticsLogger.Keys.NEXTSTEP, "reloadComponents", "", "reload", "", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "debounce", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/Map;Ljava/lang/String;)V", "getDebounce", "()Ljava/lang/String;", "getNextStep", "getReload", "()Ljava/util/Map;", "getReloadComponents$annotations", "()V", "getReloadComponents", "()Ljava/util/List;", "getRule", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "ReloadProperties", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                /* loaded from: classes2.dex */
                public static final /* data */ class Handler implements Serializable {

                    @SerializedName("debounce")
                    private final String debounce;

                    @SerializedName(AnalyticsLogger.Keys.NEXTSTEP)
                    private final String nextStep;

                    @SerializedName("reload")
                    private final Map<String, ReloadProperties> reload;

                    @SerializedName("reloadComponents")
                    private final List<String> reloadComponents;

                    @SerializedName(Validation.Type.RULE)
                    private final String rule;

                    public Handler() {
                        this(null, null, null, null, null, 31, null);
                    }

                    public static /* synthetic */ Handler copy$default(Handler handler, String str, String str2, List list, Map map, String str3, int i, Object obj) {
                        if ((i & 1) != 0) {
                            str = handler.rule;
                        }
                        if ((i & 2) != 0) {
                            str2 = handler.nextStep;
                        }
                        String str4 = str2;
                        if ((i & 4) != 0) {
                            list = handler.reloadComponents;
                        }
                        List list2 = list;
                        if ((i & 8) != 0) {
                            map = handler.reload;
                        }
                        Map map2 = map;
                        if ((i & 16) != 0) {
                            str3 = handler.debounce;
                        }
                        return handler.copy(str, str4, list2, map2, str3);
                    }

                    @Deprecated(message = "use [reload] instead")
                    public static /* synthetic */ void getReloadComponents$annotations() {
                    }

                    /* renamed from: component1, reason: from getter */
                    public final String getRule() {
                        return this.rule;
                    }

                    /* renamed from: component2, reason: from getter */
                    public final String getNextStep() {
                        return this.nextStep;
                    }

                    public final List<String> component3() {
                        return this.reloadComponents;
                    }

                    public final Map<String, ReloadProperties> component4() {
                        return this.reload;
                    }

                    /* renamed from: component5, reason: from getter */
                    public final String getDebounce() {
                        return this.debounce;
                    }

                    public final Handler copy(String rule, String nextStep, List<String> reloadComponents, Map<String, ReloadProperties> reload, String debounce) {
                        return new Handler(rule, nextStep, reloadComponents, reload, debounce);
                    }

                    public boolean equals(Object other) {
                        if (this == other) {
                            return true;
                        }
                        if (!(other instanceof Handler)) {
                            return false;
                        }
                        Handler handler = (Handler) other;
                        return Intrinsics.areEqual(this.rule, handler.rule) && Intrinsics.areEqual(this.nextStep, handler.nextStep) && Intrinsics.areEqual(this.reloadComponents, handler.reloadComponents) && Intrinsics.areEqual(this.reload, handler.reload) && Intrinsics.areEqual(this.debounce, handler.debounce);
                    }

                    public int hashCode() {
                        String str = this.rule;
                        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
                        String str2 = this.nextStep;
                        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
                        List<String> list = this.reloadComponents;
                        int hashCode3 = (hashCode2 + (list == null ? 0 : list.hashCode())) * 31;
                        Map<String, ReloadProperties> map = this.reload;
                        int hashCode4 = (hashCode3 + (map == null ? 0 : map.hashCode())) * 31;
                        String str3 = this.debounce;
                        return hashCode4 + (str3 != null ? str3.hashCode() : 0);
                    }

                    public String toString() {
                        return "Handler(rule=" + this.rule + ", nextStep=" + this.nextStep + ", reloadComponents=" + this.reloadComponents + ", reload=" + this.reload + ", debounce=" + this.debounce + ')';
                    }

                    public Handler(String str, String str2, List<String> list, Map<String, ReloadProperties> map, String str3) {
                        this.rule = str;
                        this.nextStep = str2;
                        this.reloadComponents = list;
                        this.reload = map;
                        this.debounce = str3;
                    }

                    public /* synthetic */ Handler(String str, String str2, List list, Map map, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
                        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : map, (i & 16) != 0 ? null : str3);
                    }

                    public final String getRule() {
                        return this.rule;
                    }

                    public final String getNextStep() {
                        return this.nextStep;
                    }

                    public final List<String> getReloadComponents() {
                        return this.reloadComponents;
                    }

                    public final Map<String, ReloadProperties> getReload() {
                        return this.reload;
                    }

                    public final String getDebounce() {
                        return this.debounce;
                    }

                    /* compiled from: WorkflowConfig.kt */
                    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001BÑ\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e\u0012(\b\u0002\u0010\u000f\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010j\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0015J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÆ\u0003J)\u0010*\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010j\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u0011HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003JÕ\u0001\u00106\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e2(\b\u0002\u0010\u000f\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010j\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010:\u001a\u00020;HÖ\u0001J\b\u0010<\u001a\u0004\u0018\u00010\u0000J\t\u0010=\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u001e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR6\u0010\u000f\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010j\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u00118\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0017R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0019¨\u0006>"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Handler$ReloadProperties;", "Ljava/io/Serializable;", "enabled", "", FormFragment.KEY_REQUIRED, FormFragment.KEY_VISIBLE, FormFragment.KEY_SELECTED, "value", "", "text", "title", "hint", "helperText", FirebaseAnalytics.Param.ITEMS, "", "labels", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "loading", SubType.Label.SUBTITLE, "data", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/LinkedHashMap;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "getEnabled", "()Ljava/lang/String;", "getHelperText", "getHint", "getItems", "()Ljava/util/List;", "getLabels", "()Ljava/util/LinkedHashMap;", "getLoading", "getRequired", "getSelected", "getSubTitle", "getText", "getTitle", "getValue", "getVisible", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "nullIfEmpty", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                    /* loaded from: classes2.dex */
                    public static final /* data */ class ReloadProperties implements Serializable {

                        @SerializedName("data")
                        private final Object data;

                        @SerializedName("enabled")
                        private final String enabled;

                        @SerializedName("helperText")
                        private final String helperText;

                        @SerializedName("hint")
                        private final String hint;

                        @SerializedName(FirebaseAnalytics.Param.ITEMS)
                        private final List<String> items;

                        @SerializedName("labels")
                        private final LinkedHashMap<String, String> labels;

                        @SerializedName("loading")
                        private final String loading;

                        @SerializedName(FormFragment.KEY_REQUIRED)
                        private final String required;

                        @SerializedName(FormFragment.KEY_SELECTED)
                        private final String selected;

                        @SerializedName(SubType.Label.SUBTITLE)
                        private final String subTitle;

                        @SerializedName("text")
                        private final String text;

                        @SerializedName("title")
                        private final String title;

                        @SerializedName("value")
                        private final Object value;

                        @SerializedName(FormFragment.KEY_VISIBLE)
                        private final String visible;

                        public ReloadProperties() {
                            this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, 16383, null);
                        }

                        /* renamed from: component1, reason: from getter */
                        public final String getEnabled() {
                            return this.enabled;
                        }

                        public final List<String> component10() {
                            return this.items;
                        }

                        public final LinkedHashMap<String, String> component11() {
                            return this.labels;
                        }

                        /* renamed from: component12, reason: from getter */
                        public final String getLoading() {
                            return this.loading;
                        }

                        /* renamed from: component13, reason: from getter */
                        public final String getSubTitle() {
                            return this.subTitle;
                        }

                        /* renamed from: component14, reason: from getter */
                        public final Object getData() {
                            return this.data;
                        }

                        /* renamed from: component2, reason: from getter */
                        public final String getRequired() {
                            return this.required;
                        }

                        /* renamed from: component3, reason: from getter */
                        public final String getVisible() {
                            return this.visible;
                        }

                        /* renamed from: component4, reason: from getter */
                        public final String getSelected() {
                            return this.selected;
                        }

                        /* renamed from: component5, reason: from getter */
                        public final Object getValue() {
                            return this.value;
                        }

                        /* renamed from: component6, reason: from getter */
                        public final String getText() {
                            return this.text;
                        }

                        /* renamed from: component7, reason: from getter */
                        public final String getTitle() {
                            return this.title;
                        }

                        /* renamed from: component8, reason: from getter */
                        public final String getHint() {
                            return this.hint;
                        }

                        /* renamed from: component9, reason: from getter */
                        public final String getHelperText() {
                            return this.helperText;
                        }

                        public final ReloadProperties copy(String enabled, String required, String visible, String selected, Object value, String text, String title, String hint, String helperText, List<String> items, LinkedHashMap<String, String> labels, String loading, String subTitle, Object data) {
                            return new ReloadProperties(enabled, required, visible, selected, value, text, title, hint, helperText, items, labels, loading, subTitle, data);
                        }

                        public boolean equals(Object other) {
                            if (this == other) {
                                return true;
                            }
                            if (!(other instanceof ReloadProperties)) {
                                return false;
                            }
                            ReloadProperties reloadProperties = (ReloadProperties) other;
                            return Intrinsics.areEqual(this.enabled, reloadProperties.enabled) && Intrinsics.areEqual(this.required, reloadProperties.required) && Intrinsics.areEqual(this.visible, reloadProperties.visible) && Intrinsics.areEqual(this.selected, reloadProperties.selected) && Intrinsics.areEqual(this.value, reloadProperties.value) && Intrinsics.areEqual(this.text, reloadProperties.text) && Intrinsics.areEqual(this.title, reloadProperties.title) && Intrinsics.areEqual(this.hint, reloadProperties.hint) && Intrinsics.areEqual(this.helperText, reloadProperties.helperText) && Intrinsics.areEqual(this.items, reloadProperties.items) && Intrinsics.areEqual(this.labels, reloadProperties.labels) && Intrinsics.areEqual(this.loading, reloadProperties.loading) && Intrinsics.areEqual(this.subTitle, reloadProperties.subTitle) && Intrinsics.areEqual(this.data, reloadProperties.data);
                        }

                        public int hashCode() {
                            String str = this.enabled;
                            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
                            String str2 = this.required;
                            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
                            String str3 = this.visible;
                            int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
                            String str4 = this.selected;
                            int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
                            Object obj = this.value;
                            int hashCode5 = (hashCode4 + (obj == null ? 0 : obj.hashCode())) * 31;
                            String str5 = this.text;
                            int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
                            String str6 = this.title;
                            int hashCode7 = (hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
                            String str7 = this.hint;
                            int hashCode8 = (hashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
                            String str8 = this.helperText;
                            int hashCode9 = (hashCode8 + (str8 == null ? 0 : str8.hashCode())) * 31;
                            List<String> list = this.items;
                            int hashCode10 = (hashCode9 + (list == null ? 0 : list.hashCode())) * 31;
                            LinkedHashMap<String, String> linkedHashMap = this.labels;
                            int hashCode11 = (hashCode10 + (linkedHashMap == null ? 0 : linkedHashMap.hashCode())) * 31;
                            String str9 = this.loading;
                            int hashCode12 = (hashCode11 + (str9 == null ? 0 : str9.hashCode())) * 31;
                            String str10 = this.subTitle;
                            int hashCode13 = (hashCode12 + (str10 == null ? 0 : str10.hashCode())) * 31;
                            Object obj2 = this.data;
                            return hashCode13 + (obj2 != null ? obj2.hashCode() : 0);
                        }

                        public String toString() {
                            return "ReloadProperties(enabled=" + this.enabled + ", required=" + this.required + ", visible=" + this.visible + ", selected=" + this.selected + ", value=" + this.value + ", text=" + this.text + ", title=" + this.title + ", hint=" + this.hint + ", helperText=" + this.helperText + ", items=" + this.items + ", labels=" + this.labels + ", loading=" + this.loading + ", subTitle=" + this.subTitle + ", data=" + this.data + ')';
                        }

                        public ReloadProperties(String str, String str2, String str3, String str4, Object obj, String str5, String str6, String str7, String str8, List<String> list, LinkedHashMap<String, String> linkedHashMap, String str9, String str10, Object obj2) {
                            this.enabled = str;
                            this.required = str2;
                            this.visible = str3;
                            this.selected = str4;
                            this.value = obj;
                            this.text = str5;
                            this.title = str6;
                            this.hint = str7;
                            this.helperText = str8;
                            this.items = list;
                            this.labels = linkedHashMap;
                            this.loading = str9;
                            this.subTitle = str10;
                            this.data = obj2;
                        }

                        public /* synthetic */ ReloadProperties(String str, String str2, String str3, String str4, Object obj, String str5, String str6, String str7, String str8, List list, LinkedHashMap linkedHashMap, String str9, String str10, Object obj2, int i, DefaultConstructorMarker defaultConstructorMarker) {
                            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : obj, (i & 32) != 0 ? null : str5, (i & 64) != 0 ? null : str6, (i & 128) != 0 ? null : str7, (i & 256) != 0 ? null : str8, (i & 512) != 0 ? null : list, (i & 1024) != 0 ? null : linkedHashMap, (i & 2048) != 0 ? null : str9, (i & 4096) != 0 ? null : str10, (i & 8192) == 0 ? obj2 : null);
                        }

                        public final String getEnabled() {
                            return this.enabled;
                        }

                        public final String getRequired() {
                            return this.required;
                        }

                        public final String getVisible() {
                            return this.visible;
                        }

                        public final String getSelected() {
                            return this.selected;
                        }

                        public final Object getValue() {
                            return this.value;
                        }

                        public final String getText() {
                            return this.text;
                        }

                        public final String getTitle() {
                            return this.title;
                        }

                        public final String getHint() {
                            return this.hint;
                        }

                        public final String getHelperText() {
                            return this.helperText;
                        }

                        public final List<String> getItems() {
                            return this.items;
                        }

                        public final LinkedHashMap<String, String> getLabels() {
                            return this.labels;
                        }

                        public final String getLoading() {
                            return this.loading;
                        }

                        public final String getSubTitle() {
                            return this.subTitle;
                        }

                        public final Object getData() {
                            return this.data;
                        }

                        public final ReloadProperties nullIfEmpty() {
                            boolean z = false;
                            List listOf = CollectionsKt.listOf(this.enabled, this.required, this.visible, this.selected, this.value, this.text, this.title, this.hint, this.helperText, this.items, this.labels, this.loading, this.subTitle);
                            if (!(listOf instanceof Collection) || !listOf.isEmpty()) {
                                Iterator it = listOf.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    if (it.next() != null) {
                                        z = true;
                                        break;
                                    }
                                }
                            }
                            if (z) {
                                return this;
                            }
                            return null;
                        }
                    }
                }

                /* compiled from: WorkflowConfig.kt */
                @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0007J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0007J&\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\t\u0010\u0007¨\u0006\u0015"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$DateRange;", "Ljava/io/Serializable;", "startMonth", "", "endMonth", "(Ljava/lang/Integer;Ljava/lang/Integer;)V", "getEndMonth", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getStartMonth", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;)Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$DateRange;", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                /* loaded from: classes2.dex */
                public static final /* data */ class DateRange implements Serializable {

                    @SerializedName("endMonth")
                    private final Integer endMonth;

                    @SerializedName("startMonth")
                    private final Integer startMonth;

                    /* JADX WARN: Multi-variable type inference failed */
                    public DateRange() {
                        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
                    }

                    public static /* synthetic */ DateRange copy$default(DateRange dateRange, Integer num, Integer num2, int i, Object obj) {
                        if ((i & 1) != 0) {
                            num = dateRange.startMonth;
                        }
                        if ((i & 2) != 0) {
                            num2 = dateRange.endMonth;
                        }
                        return dateRange.copy(num, num2);
                    }

                    /* renamed from: component1, reason: from getter */
                    public final Integer getStartMonth() {
                        return this.startMonth;
                    }

                    /* renamed from: component2, reason: from getter */
                    public final Integer getEndMonth() {
                        return this.endMonth;
                    }

                    public final DateRange copy(Integer startMonth, Integer endMonth) {
                        return new DateRange(startMonth, endMonth);
                    }

                    public boolean equals(Object other) {
                        if (this == other) {
                            return true;
                        }
                        if (!(other instanceof DateRange)) {
                            return false;
                        }
                        DateRange dateRange = (DateRange) other;
                        return Intrinsics.areEqual(this.startMonth, dateRange.startMonth) && Intrinsics.areEqual(this.endMonth, dateRange.endMonth);
                    }

                    public int hashCode() {
                        Integer num = this.startMonth;
                        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
                        Integer num2 = this.endMonth;
                        return hashCode + (num2 != null ? num2.hashCode() : 0);
                    }

                    public String toString() {
                        return "DateRange(startMonth=" + this.startMonth + ", endMonth=" + this.endMonth + ')';
                    }

                    public DateRange(Integer num, Integer num2) {
                        this.startMonth = num;
                        this.endMonth = num2;
                    }

                    public /* synthetic */ DateRange(Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
                        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2);
                    }

                    public final Integer getStartMonth() {
                        return this.startMonth;
                    }

                    public final Integer getEndMonth() {
                        return this.endMonth;
                    }
                }

                /* compiled from: WorkflowConfig.kt */
                @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001!B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\u0011\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003JI\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\t2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013¨\u0006\""}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SupportedFile;", "Ljava/io/Serializable;", "type", "", "title", "extensions", "", "overrideAllowedTypes", "enabled", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Z)V", "getEnabled", "()Z", "setEnabled", "(Z)V", "getExtensions", "()Ljava/util/List;", "getOverrideAllowedTypes", "getTitle", "()Ljava/lang/String;", "getType", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "Type", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                /* loaded from: classes2.dex */
                public static final /* data */ class SupportedFile implements Serializable {
                    private transient boolean enabled;

                    @SerializedName("extensions")
                    private final List<String> extensions;

                    @SerializedName("overrideAllowedTypes")
                    private final List<String> overrideAllowedTypes;

                    @SerializedName("title")
                    private final String title;

                    @SerializedName("type")
                    private final String type;

                    public static /* synthetic */ SupportedFile copy$default(SupportedFile supportedFile, String str, String str2, List list, List list2, boolean z, int i, Object obj) {
                        if ((i & 1) != 0) {
                            str = supportedFile.type;
                        }
                        if ((i & 2) != 0) {
                            str2 = supportedFile.title;
                        }
                        String str3 = str2;
                        if ((i & 4) != 0) {
                            list = supportedFile.extensions;
                        }
                        List list3 = list;
                        if ((i & 8) != 0) {
                            list2 = supportedFile.overrideAllowedTypes;
                        }
                        List list4 = list2;
                        if ((i & 16) != 0) {
                            z = supportedFile.enabled;
                        }
                        return supportedFile.copy(str, str3, list3, list4, z);
                    }

                    /* renamed from: component1, reason: from getter */
                    public final String getType() {
                        return this.type;
                    }

                    /* renamed from: component2, reason: from getter */
                    public final String getTitle() {
                        return this.title;
                    }

                    public final List<String> component3() {
                        return this.extensions;
                    }

                    public final List<String> component4() {
                        return this.overrideAllowedTypes;
                    }

                    /* renamed from: component5, reason: from getter */
                    public final boolean getEnabled() {
                        return this.enabled;
                    }

                    public final SupportedFile copy(String type, String title, List<String> extensions, List<String> overrideAllowedTypes, boolean enabled) {
                        Intrinsics.checkNotNullParameter(type, "type");
                        Intrinsics.checkNotNullParameter(title, "title");
                        Intrinsics.checkNotNullParameter(extensions, "extensions");
                        return new SupportedFile(type, title, extensions, overrideAllowedTypes, enabled);
                    }

                    public boolean equals(Object other) {
                        if (this == other) {
                            return true;
                        }
                        if (!(other instanceof SupportedFile)) {
                            return false;
                        }
                        SupportedFile supportedFile = (SupportedFile) other;
                        return Intrinsics.areEqual(this.type, supportedFile.type) && Intrinsics.areEqual(this.title, supportedFile.title) && Intrinsics.areEqual(this.extensions, supportedFile.extensions) && Intrinsics.areEqual(this.overrideAllowedTypes, supportedFile.overrideAllowedTypes) && this.enabled == supportedFile.enabled;
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    public int hashCode() {
                        int hashCode = ((((this.type.hashCode() * 31) + this.title.hashCode()) * 31) + this.extensions.hashCode()) * 31;
                        List<String> list = this.overrideAllowedTypes;
                        int hashCode2 = (hashCode + (list == null ? 0 : list.hashCode())) * 31;
                        boolean z = this.enabled;
                        int i = z;
                        if (z != 0) {
                            i = 1;
                        }
                        return hashCode2 + i;
                    }

                    public String toString() {
                        return "SupportedFile(type=" + this.type + ", title=" + this.title + ", extensions=" + this.extensions + ", overrideAllowedTypes=" + this.overrideAllowedTypes + ", enabled=" + this.enabled + ')';
                    }

                    public SupportedFile(String type, String title, List<String> extensions, List<String> list, boolean z) {
                        Intrinsics.checkNotNullParameter(type, "type");
                        Intrinsics.checkNotNullParameter(title, "title");
                        Intrinsics.checkNotNullParameter(extensions, "extensions");
                        this.type = type;
                        this.title = title;
                        this.extensions = extensions;
                        this.overrideAllowedTypes = list;
                        this.enabled = z;
                    }

                    public /* synthetic */ SupportedFile(String str, String str2, List list, List list2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
                        this(str, str2, list, (i & 8) != 0 ? null : list2, (i & 16) != 0 ? true : z);
                    }

                    public final String getType() {
                        return this.type;
                    }

                    public final String getTitle() {
                        return this.title;
                    }

                    public final List<String> getExtensions() {
                        return this.extensions;
                    }

                    public final List<String> getOverrideAllowedTypes() {
                        return this.overrideAllowedTypes;
                    }

                    public final boolean getEnabled() {
                        return this.enabled;
                    }

                    public final void setEnabled(boolean z) {
                        this.enabled = z;
                    }

                    /* compiled from: WorkflowConfig.kt */
                    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SupportedFile$Type;", "", "()V", "AUDIOS", "", "DOCUMENTS", "IMAGES", "VIDEOS", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                    /* loaded from: classes2.dex */
                    public static final class Type {
                        public static final String AUDIOS = "audios";
                        public static final String DOCUMENTS = "documents";
                        public static final String IMAGES = "images";
                        public static final Type INSTANCE = new Type();
                        public static final String VIDEOS = "videos";

                        private Type() {
                        }
                    }
                }

                /* compiled from: WorkflowConfig.kt */
                @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Type;", "", "()V", "BUTTON", "", "CHECKBOX", "CHIP", "DATE", "DIVIDER", "DROPDOWN", "FILE", "HORIZONTAL", ShareConstants.IMAGE_URL, "LABEL", "LIST", "LOADER", "RADIO", "TEXT", "TIMER", "VERTICAL", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                /* loaded from: classes2.dex */
                public static final class Type {
                    public static final String BUTTON = "button";
                    public static final String CHECKBOX = "checkbox";
                    public static final String CHIP = "chip";
                    public static final String DATE = "date";
                    public static final String DIVIDER = "divider";
                    public static final String DROPDOWN = "dropdown";
                    public static final String FILE = "file";
                    public static final String HORIZONTAL = "horizontal";
                    public static final String IMAGE = "image";
                    public static final Type INSTANCE = new Type();
                    public static final String LABEL = "label";
                    public static final String LIST = "list";
                    public static final String LOADER = "loader";
                    public static final String RADIO = "radio";
                    public static final String TEXT = "text";
                    public static final String TIMER = "timer";
                    public static final String VERTICAL = "vertical";

                    private Type() {
                    }
                }

                /* compiled from: WorkflowConfig.kt */
                @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0006\u0003\u0004\u0005\u0006\u0007\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\t"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SubType;", "", "()V", "Button", "Divider", "Label", PDListAttributeObject.OWNER_LIST, "Text", "Timer", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                /* loaded from: classes2.dex */
                public static final class SubType {
                    public static final SubType INSTANCE = new SubType();

                    /* compiled from: WorkflowConfig.kt */
                    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SubType$Label;", "", "()V", "HINT", "", ShareConstants.SUBTITLE, "TEXT_BLOCK", ShareConstants.TITLE, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                    /* loaded from: classes2.dex */
                    public static final class Label {
                        public static final String HINT = "hint";
                        public static final Label INSTANCE = new Label();
                        public static final String SUBTITLE = "subTitle";
                        public static final String TEXT_BLOCK = "textBlock";
                        public static final String TITLE = "title";

                        private Label() {
                        }
                    }

                    private SubType() {
                    }

                    /* compiled from: WorkflowConfig.kt */
                    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SubType$Text;", "", "()V", "BLOCKS", "", "MULTI_LINE", "SINGLE_LINE", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                    /* loaded from: classes2.dex */
                    public static final class Text {
                        public static final String BLOCKS = "blocks";
                        public static final Text INSTANCE = new Text();
                        public static final String MULTI_LINE = "multiLine";
                        public static final String SINGLE_LINE = "singleLine";

                        private Text() {
                        }
                    }

                    /* compiled from: WorkflowConfig.kt */
                    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SubType$Button;", "", "()V", "PRIMARY", "", "SECONDARY", "TERTIARY", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                    /* loaded from: classes2.dex */
                    public static final class Button {
                        public static final Button INSTANCE = new Button();
                        public static final String PRIMARY = "primary";
                        public static final String SECONDARY = "secondary";
                        public static final String TERTIARY = "tertiary";

                        private Button() {
                        }
                    }

                    /* compiled from: WorkflowConfig.kt */
                    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SubType$Divider;", "", "()V", "OPTIONAL", "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                    /* loaded from: classes2.dex */
                    public static final class Divider {
                        public static final Divider INSTANCE = new Divider();
                        public static final String OPTIONAL = "optional";

                        private Divider() {
                        }
                    }

                    /* compiled from: WorkflowConfig.kt */
                    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SubType$Timer;", "", "()V", "COUNTDOWN", "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                    /* loaded from: classes2.dex */
                    public static final class Timer {
                        public static final String COUNTDOWN = "countdown";
                        public static final Timer INSTANCE = new Timer();

                        private Timer() {
                        }
                    }

                    /* compiled from: WorkflowConfig.kt */
                    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SubType$List;", "", "()V", "GRID", "", "VERTICAL", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                    /* loaded from: classes2.dex */
                    public static final class List {
                        public static final String GRID = "grid";
                        public static final List INSTANCE = new List();
                        public static final String VERTICAL = "vertical";

                        private List() {
                        }
                    }
                }

                /* compiled from: WorkflowConfig.kt */
                @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Keyboard;", "", "()V", "EMAIL", "", "NUMBER", "PHONE", "TEXT", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                /* loaded from: classes2.dex */
                public static final class Keyboard {
                    public static final String EMAIL = "email";
                    public static final Keyboard INSTANCE = new Keyboard();
                    public static final String NUMBER = "number";
                    public static final String PHONE = "phone";
                    public static final String TEXT = "text";

                    private Keyboard() {
                    }
                }

                /* compiled from: WorkflowConfig.kt */
                @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$State;", "", "()V", "STATE_COMPLETED", "", "STATE_IDLE", "STATE_RUNNING", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                /* loaded from: classes2.dex */
                public static final class State {
                    public static final State INSTANCE = new State();
                    public static final String STATE_COMPLETED = "completed";
                    public static final String STATE_IDLE = "idle";
                    public static final String STATE_RUNNING = "running";

                    private State() {
                    }
                }

                /* compiled from: WorkflowConfig.kt */
                @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$Dimensions;", "", "()V", "FILL", "", "WRAP", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                /* loaded from: classes2.dex */
                public static final class Dimensions {
                    public static final String FILL = "fill";
                    public static final Dimensions INSTANCE = new Dimensions();
                    public static final String WRAP = "wrap";

                    private Dimensions() {
                    }
                }

                /* compiled from: WorkflowConfig.kt */
                @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SelectionMode;", "", "()V", "MULTIPLE", "", "SINGLE", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
                /* loaded from: classes2.dex */
                public static final class SelectionMode {
                    public static final SelectionMode INSTANCE = new SelectionMode();
                    public static final String MULTIPLE = "multiple";
                    public static final String SINGLE = "single";

                    private SelectionMode() {
                    }
                }
            }

            /* compiled from: WorkflowConfig.kt */
            @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Footer;", "Ljava/io/Serializable;", "components", "", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component;", "(Ljava/util/List;)V", "getComponents", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
            /* loaded from: classes2.dex */
            public static final /* data */ class Footer implements Serializable {

                @SerializedName("components")
                private final List<Component> components;

                /* JADX WARN: Multi-variable type inference failed */
                public static /* synthetic */ Footer copy$default(Footer footer, List list, int i, Object obj) {
                    if ((i & 1) != 0) {
                        list = footer.components;
                    }
                    return footer.copy(list);
                }

                public final List<Component> component1() {
                    return this.components;
                }

                public final Footer copy(List<Component> components) {
                    Intrinsics.checkNotNullParameter(components, "components");
                    return new Footer(components);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    return (other instanceof Footer) && Intrinsics.areEqual(this.components, ((Footer) other).components);
                }

                public int hashCode() {
                    return this.components.hashCode();
                }

                public String toString() {
                    return "Footer(components=" + this.components + ')';
                }

                public Footer(List<Component> components) {
                    Intrinsics.checkNotNullParameter(components, "components");
                    this.components = components;
                }

                public final List<Component> getComponents() {
                    return this.components;
                }
            }
        }
    }

    /* compiled from: WorkflowConfig.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0014"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Variable;", "Ljava/io/Serializable;", "name", "", Constants.PATH, "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getPath", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class Variable implements Serializable {
        public static final String PREFIX_HEADERS = "headers";
        public static final String PREFIX_RESPONSE = "response";

        @SerializedName("name")
        private final String name;

        @SerializedName(Constants.PATH)
        private final String path;

        public static /* synthetic */ Variable copy$default(Variable variable, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = variable.name;
            }
            if ((i & 2) != 0) {
                str2 = variable.path;
            }
            return variable.copy(str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component2, reason: from getter */
        public final String getPath() {
            return this.path;
        }

        public final Variable copy(String name, String path) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(path, "path");
            return new Variable(name, path);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Variable)) {
                return false;
            }
            Variable variable = (Variable) other;
            return Intrinsics.areEqual(this.name, variable.name) && Intrinsics.areEqual(this.path, variable.path);
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.path.hashCode();
        }

        public String toString() {
            return "Variable(name=" + this.name + ", path=" + this.path + ')';
        }

        public Variable(String name, String path) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(path, "path");
            this.name = name;
            this.path = path;
        }

        public final String getName() {
            return this.name;
        }

        public final String getPath() {
            return this.path;
        }
    }

    public final boolean isChild$hyperkyc_release() {
        String str = this.uiStyle;
        return !(str == null || StringsKt.isBlank(str));
    }

    /* compiled from: WorkflowConfig.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b$\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\f8\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0010\u0012\u0004\b\r\u0010\u0002\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0011\u001a\u00020\t8\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0002R\u0016\u0010\u0013\u001a\u00020\t8\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\u0002R\u0016\u0010\u0015\u001a\u00020\t8\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\u0002R\u000e\u0010\u0017\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010$\u001a\u00020\t8\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b%\u0010\u0002R\u000e\u0010&\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Companion;", "", "()V", "DEFAULT_SUPPORTED_FILES", "", "Lco/hyperverge/hyperkyc/data/models/WorkflowModule$Properties$Section$Component$SupportedFile;", "getDEFAULT_SUPPORTED_FILES", "()Ljava/util/List;", "DEPRECATED_MSG", "", "DISMISS_TO_PARENT", "END_STATES", "", "getEND_STATES$hyperkyc_release$annotations", "getEND_STATES$hyperkyc_release", "()[Ljava/lang/String;", "[Ljava/lang/String;", "END_STATE_APPROVE", "getEND_STATE_APPROVE$annotations", "END_STATE_DECLINE", "getEND_STATE_DECLINE$annotations", "END_STATE_MANUAL_REVIEW", "getEND_STATE_MANUAL_REVIEW$annotations", "PREFIX_CONDITION", "PREFIX_CONDITIONAL_VARS", "PREFIX_INPUTS", "PREFIX_MODULE", "PREFIX_OUTPUTS", "PREFIX_SDK", "TYPE_API", "TYPE_BARCODE", "TYPE_COUNTRY", "TYPE_DOCUMENT", "TYPE_DYNAMIC_FORM", "TYPE_DYNAMIC_FORM_V2", "TYPE_FACE", "TYPE_FORM", "getTYPE_FORM$annotations", "TYPE_NFC", "TYPE_START", "TYPE_START_SESSION_RECORDING", "TYPE_STOP_SESSION_RECORDING", "TYPE_VIDEO_STATEMENT", "TYPE_VIDEO_STATEMENT_V2", "TYPE_WEBVIEW", "UI_STYLE_ACTION_SHEET", "UI_STYLE_NONE", "UI_STYLE_POPUP", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Deprecated(message = WorkflowModule.DEPRECATED_MSG)
        public static /* synthetic */ void getEND_STATES$hyperkyc_release$annotations() {
        }

        @Deprecated(message = WorkflowModule.DEPRECATED_MSG)
        public static /* synthetic */ void getEND_STATE_APPROVE$annotations() {
        }

        @Deprecated(message = WorkflowModule.DEPRECATED_MSG)
        public static /* synthetic */ void getEND_STATE_DECLINE$annotations() {
        }

        @Deprecated(message = WorkflowModule.DEPRECATED_MSG)
        public static /* synthetic */ void getEND_STATE_MANUAL_REVIEW$annotations() {
        }

        @Deprecated(message = "use dynamicForm")
        public static /* synthetic */ void getTYPE_FORM$annotations() {
        }

        private Companion() {
        }

        public final String[] getEND_STATES$hyperkyc_release() {
            return WorkflowModule.END_STATES;
        }

        public final List<Properties.Section.Component.SupportedFile> getDEFAULT_SUPPORTED_FILES() {
            return WorkflowModule.DEFAULT_SUPPORTED_FILES;
        }
    }
}
