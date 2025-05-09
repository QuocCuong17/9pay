package com.facebook.gamingservices;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.webkit.internal.AssetHelper;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphResponse;
import com.facebook.gamingservices.ContextSwitchDialog;
import com.facebook.gamingservices.cloudgaming.CloudGameLoginHandler;
import com.facebook.gamingservices.cloudgaming.DaemonRequest;
import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;
import com.facebook.gamingservices.cloudgaming.internal.SDKMessageEnum;
import com.facebook.gamingservices.model.ContextSwitchContent;
import com.facebook.internal.AppCall;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.DialogPresenter;
import com.facebook.internal.FacebookDialogBase;
import com.facebook.internal.FragmentWrapper;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ResultProcessor;
import com.facebook.share.internal.ShareInternalUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.protocol.Request;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ContextSwitchDialog.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 #2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0004#$%&B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u000f\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\n¢\u0006\u0002\u0010\u000bB\u000f\b\u0012\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0002H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0014J\u001e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010H\u0014J\u0010\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u0002H\u0002J\u0018\u0010 \u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\"H\u0014R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0011\u001a\u0018\u0012\u0014\u0012\u00120\u0013R\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00010\u00128TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006'"}, d2 = {"Lcom/facebook/gamingservices/ContextSwitchDialog;", "Lcom/facebook/internal/FacebookDialogBase;", "Lcom/facebook/gamingservices/model/ContextSwitchContent;", "Lcom/facebook/gamingservices/ContextSwitchDialog$Result;", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", Request.JsonKeys.FRAGMENT, "Landroidx/fragment/app/Fragment;", "(Landroidx/fragment/app/Fragment;)V", "Landroid/app/Fragment;", "(Landroid/app/Fragment;)V", "fragmentWrapper", "Lcom/facebook/internal/FragmentWrapper;", "(Lcom/facebook/internal/FragmentWrapper;)V", "callback", "Lcom/facebook/FacebookCallback;", "orderedModeHandlers", "", "Lcom/facebook/internal/FacebookDialogBase$ModeHandler;", "getOrderedModeHandlers", "()Ljava/util/List;", "canShow", "", FirebaseAnalytics.Param.CONTENT, "createBaseAppCall", "Lcom/facebook/internal/AppCall;", "registerCallbackImpl", "", "callbackManager", "Lcom/facebook/internal/CallbackManagerImpl;", "showForCloud", "showImpl", "mode", "", "Companion", "FacebookAppHandler", "Result", "WebHandler", "facebook-gamingservices_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ContextSwitchDialog extends FacebookDialogBase<ContextSwitchContent, Result> {
    private static final int DEFAULT_REQUEST_CODE = CallbackManagerImpl.RequestCodeOffset.GamingContextSwitch.toRequestCode();
    private FacebookCallback<Result> callback;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContextSwitchDialog(Activity activity) {
        super(activity, DEFAULT_REQUEST_CODE);
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ContextSwitchDialog(Fragment fragment) {
        this(new FragmentWrapper(fragment));
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ContextSwitchDialog(android.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    private ContextSwitchDialog(FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, DEFAULT_REQUEST_CODE);
    }

    @Override // com.facebook.internal.FacebookDialogBase, com.facebook.FacebookDialog
    public boolean canShow(ContextSwitchContent content) {
        Intrinsics.checkNotNullParameter(content, "content");
        return CloudGameLoginHandler.isRunningInCloud() || new FacebookAppHandler().canShow(content, true) || new WebHandler().canShow(content, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.internal.FacebookDialogBase
    public void showImpl(ContextSwitchContent content, Object mode) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(mode, "mode");
        if (CloudGameLoginHandler.isRunningInCloud()) {
            showForCloud(content);
        } else {
            super.showImpl((ContextSwitchDialog) content, mode);
        }
    }

    private final void showForCloud(ContextSwitchContent content) {
        AccessToken currentAccessToken = AccessToken.INSTANCE.getCurrentAccessToken();
        if (currentAccessToken == null || currentAccessToken.isExpired()) {
            throw new FacebookException("Attempted to open ContextSwitchContent with an invalid access token");
        }
        DaemonRequest.Callback callback = new DaemonRequest.Callback() { // from class: com.facebook.gamingservices.ContextSwitchDialog$$ExternalSyntheticLambda0
            @Override // com.facebook.gamingservices.cloudgaming.DaemonRequest.Callback
            public final void onCompleted(GraphResponse graphResponse) {
                ContextSwitchDialog.showForCloud$lambda$2(ContextSwitchDialog.this, graphResponse);
            }
        };
        String contextID = content.getContextID();
        if (contextID == null) {
            FacebookCallback<Result> facebookCallback = this.callback;
            if (facebookCallback != null) {
                facebookCallback.onError(new FacebookException("Required string contextID not provided."));
                return;
            }
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", contextID);
            DaemonRequest.executeAsync(getActivityContext(), jSONObject, callback, SDKMessageEnum.CONTEXT_SWITCH);
        } catch (JSONException unused) {
            FacebookCallback<Result> facebookCallback2 = this.callback;
            if (facebookCallback2 != null) {
                facebookCallback2.onError(new FacebookException("Couldn't prepare Context Switch Dialog"));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showForCloud$lambda$2(ContextSwitchDialog this$0, GraphResponse response) {
        Unit unit;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FacebookCallback<Result> facebookCallback = this$0.callback;
        if (facebookCallback != null) {
            FacebookRequestError error = response.getError();
            if (error != null) {
                facebookCallback.onError(new FacebookException(error.getErrorMessage()));
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                Intrinsics.checkNotNullExpressionValue(response, "response");
                facebookCallback.onSuccess(new Result(response));
            }
        }
    }

    @Override // com.facebook.internal.FacebookDialogBase
    protected void registerCallbackImpl(CallbackManagerImpl callbackManager, final FacebookCallback<Result> callback) {
        Intrinsics.checkNotNullParameter(callbackManager, "callbackManager");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
        final ResultProcessor resultProcessor = new ResultProcessor(callback) { // from class: com.facebook.gamingservices.ContextSwitchDialog$registerCallbackImpl$resultProcessor$1
            final /* synthetic */ FacebookCallback<ContextSwitchDialog.Result> $callback;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(callback);
                this.$callback = callback;
            }

            @Override // com.facebook.share.internal.ResultProcessor
            public void onSuccess(AppCall appCall, Bundle results) {
                Intrinsics.checkNotNullParameter(appCall, "appCall");
                if (results != null) {
                    if (results.getString("error_message") != null) {
                        this.$callback.onError(new FacebookException(results.getString("error_message")));
                        return;
                    }
                    String string = results.getString("id");
                    String string2 = results.getString(SDKConstants.PARAM_CONTEXT_CONTEXT_ID);
                    if (string != null) {
                        GamingContext.INSTANCE.setCurrentGamingContext(new GamingContext(string));
                        this.$callback.onSuccess(new ContextSwitchDialog.Result(string));
                    } else if (string2 != null) {
                        GamingContext.INSTANCE.setCurrentGamingContext(new GamingContext(string2));
                        this.$callback.onSuccess(new ContextSwitchDialog.Result(string2));
                    }
                    this.$callback.onError(new FacebookException(results.getString("Invalid response received from server.")));
                    return;
                }
                onCancel(appCall);
            }
        };
        callbackManager.registerCallback(getRequestCodeField(), new CallbackManagerImpl.Callback() { // from class: com.facebook.gamingservices.ContextSwitchDialog$$ExternalSyntheticLambda1
            @Override // com.facebook.internal.CallbackManagerImpl.Callback
            public final boolean onActivityResult(int i, Intent intent) {
                boolean registerCallbackImpl$lambda$3;
                registerCallbackImpl$lambda$3 = ContextSwitchDialog.registerCallbackImpl$lambda$3(ContextSwitchDialog.this, resultProcessor, i, intent);
                return registerCallbackImpl$lambda$3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean registerCallbackImpl$lambda$3(ContextSwitchDialog this$0, ResultProcessor resultProcessor, int i, Intent intent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(resultProcessor, "$resultProcessor");
        return ShareInternalUtility.handleActivityResult(this$0.getRequestCodeField(), i, intent, resultProcessor);
    }

    @Override // com.facebook.internal.FacebookDialogBase
    protected List<FacebookDialogBase<ContextSwitchContent, Result>.ModeHandler> getOrderedModeHandlers() {
        return CollectionsKt.listOf((Object[]) new FacebookDialogBase.ModeHandler[]{new FacebookAppHandler(), new WebHandler()});
    }

    @Override // com.facebook.internal.FacebookDialogBase
    protected AppCall createBaseAppCall() {
        return new AppCall(getRequestCodeField(), null, 2, null);
    }

    /* compiled from: ContextSwitchDialog.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0010\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0004¨\u0006\u000b"}, d2 = {"Lcom/facebook/gamingservices/ContextSwitchDialog$Result;", "", "contextID", "", "(Ljava/lang/String;)V", "response", "Lcom/facebook/GraphResponse;", "(Lcom/facebook/GraphResponse;)V", "getContextID", "()Ljava/lang/String;", "setContextID", "facebook-gamingservices_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Result {
        private String contextID;

        public final String getContextID() {
            return this.contextID;
        }

        public final void setContextID(String str) {
            this.contextID = str;
        }

        public Result(String contextID) {
            Intrinsics.checkNotNullParameter(contextID, "contextID");
            this.contextID = contextID;
        }

        public Result(GraphResponse response) {
            JSONObject optJSONObject;
            Intrinsics.checkNotNullParameter(response, "response");
            try {
                JSONObject graphObject = response.getGraphObject();
                if (graphObject == null || (optJSONObject = graphObject.optJSONObject("data")) == null) {
                    return;
                }
                Intrinsics.checkNotNullExpressionValue(optJSONObject, "optJSONObject(\"data\")");
                this.contextID = optJSONObject.getString("id");
            } catch (JSONException unused) {
                this.contextID = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ContextSwitchDialog.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00120\u0001R\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002B\u0005¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0003H\u0016¨\u0006\f"}, d2 = {"Lcom/facebook/gamingservices/ContextSwitchDialog$WebHandler;", "Lcom/facebook/internal/FacebookDialogBase$ModeHandler;", "Lcom/facebook/internal/FacebookDialogBase;", "Lcom/facebook/gamingservices/model/ContextSwitchContent;", "Lcom/facebook/gamingservices/ContextSwitchDialog$Result;", "(Lcom/facebook/gamingservices/ContextSwitchDialog;)V", "canShow", "", FirebaseAnalytics.Param.CONTENT, "isBestEffort", "createAppCall", "Lcom/facebook/internal/AppCall;", "facebook-gamingservices_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class WebHandler extends FacebookDialogBase<ContextSwitchContent, Result>.ModeHandler {
        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public boolean canShow(ContextSwitchContent content, boolean isBestEffort) {
            Intrinsics.checkNotNullParameter(content, "content");
            return true;
        }

        public WebHandler() {
            super();
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public AppCall createAppCall(ContextSwitchContent content) {
            Intrinsics.checkNotNullParameter(content, "content");
            AppCall createBaseAppCall = ContextSwitchDialog.this.createBaseAppCall();
            Bundle bundle = new Bundle();
            bundle.putString(SDKConstants.PARAM_CONTEXT_CONTEXT_ID, content.getContextID());
            AccessToken currentAccessToken = AccessToken.INSTANCE.getCurrentAccessToken();
            if (currentAccessToken != null) {
                bundle.putString("dialog_access_token", currentAccessToken.getToken());
            }
            DialogPresenter.setupAppCallForWebDialog(createBaseAppCall, "context", bundle);
            return createBaseAppCall;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ContextSwitchDialog.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00120\u0001R\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002B\u0005¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0003H\u0016¨\u0006\f"}, d2 = {"Lcom/facebook/gamingservices/ContextSwitchDialog$FacebookAppHandler;", "Lcom/facebook/internal/FacebookDialogBase$ModeHandler;", "Lcom/facebook/internal/FacebookDialogBase;", "Lcom/facebook/gamingservices/model/ContextSwitchContent;", "Lcom/facebook/gamingservices/ContextSwitchDialog$Result;", "(Lcom/facebook/gamingservices/ContextSwitchDialog;)V", "canShow", "", FirebaseAnalytics.Param.CONTENT, "isBestEffort", "createAppCall", "Lcom/facebook/internal/AppCall;", "facebook-gamingservices_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class FacebookAppHandler extends FacebookDialogBase<ContextSwitchContent, Result>.ModeHandler {
        public FacebookAppHandler() {
            super();
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public boolean canShow(ContextSwitchContent content, boolean isBestEffort) {
            Intrinsics.checkNotNullParameter(content, "content");
            Activity activityContext = ContextSwitchDialog.this.getActivityContext();
            PackageManager packageManager = activityContext != null ? activityContext.getPackageManager() : null;
            Intent intent = new Intent("com.facebook.games.gaming_services.DEEPLINK");
            intent.setType(AssetHelper.DEFAULT_MIME_TYPE);
            boolean z = (packageManager != null ? intent.resolveActivity(packageManager) : null) != null;
            AccessToken currentAccessToken = AccessToken.INSTANCE.getCurrentAccessToken();
            return z && ((currentAccessToken != null ? currentAccessToken.getGraphDomain() : null) != null && Intrinsics.areEqual(FacebookSdk.GAMING, currentAccessToken.getGraphDomain()));
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public AppCall createAppCall(ContextSwitchContent content) {
            Intrinsics.checkNotNullParameter(content, "content");
            AppCall createBaseAppCall = ContextSwitchDialog.this.createBaseAppCall();
            Intent intent = new Intent("com.facebook.games.gaming_services.DEEPLINK");
            intent.setType(AssetHelper.DEFAULT_MIME_TYPE);
            AccessToken currentAccessToken = AccessToken.INSTANCE.getCurrentAccessToken();
            Bundle bundle = new Bundle();
            bundle.putString(SDKConstants.PARAM_TOURNAMENTS_DEEPLINK, "CONTEXT_SWITCH");
            if (currentAccessToken != null) {
                bundle.putString("game_id", currentAccessToken.getApplicationId());
            } else {
                bundle.putString("game_id", FacebookSdk.getApplicationId());
            }
            if (content.getContextID() != null) {
                bundle.putString("context_token_id", content.getContextID());
            }
            NativeProtocol.setupProtocolRequestIntent(intent, createBaseAppCall.getCallId().toString(), "", NativeProtocol.getLatestKnownVersion(), bundle);
            createBaseAppCall.setRequestIntent(intent);
            return createBaseAppCall;
        }
    }
}
