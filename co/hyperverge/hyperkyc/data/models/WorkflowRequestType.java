package co.hyperverge.hyperkyc.data.models;

import androidx.browser.trusted.sharing.ShareTarget;
import com.fasterxml.jackson.core.JsonFactory;
import kotlin.Metadata;

/* compiled from: WorkflowConfig.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowRequestType;", "", "()V", JsonFactory.FORMAT_NAME_JSON, "", "MULTIPART", "PLAIN_TEXT", "Method", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WorkflowRequestType {
    public static final WorkflowRequestType INSTANCE = new WorkflowRequestType();
    public static final String JSON = "json";
    public static final String MULTIPART = "multipart";
    public static final String PLAIN_TEXT = "plainText";

    private WorkflowRequestType() {
    }

    /* compiled from: WorkflowConfig.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/WorkflowRequestType$Method;", "", "()V", ShareTarget.METHOD_GET, "", "POST", "PUT", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Method {
        public static final String GET = "get";
        public static final Method INSTANCE = new Method();
        public static final String POST = "post";
        public static final String PUT = "put";

        private Method() {
        }
    }
}
