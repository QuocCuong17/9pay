package com.facebook.share.widget;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.AppCall;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.DialogFeature;
import com.facebook.internal.DialogPresenter;
import com.facebook.internal.FacebookDialogBase;
import com.facebook.internal.FragmentWrapper;
import com.facebook.internal.NativeAppCallAttachmentStore;
import com.facebook.share.Sharer;
import com.facebook.share.internal.CameraEffectFeature;
import com.facebook.share.internal.LegacyNativeDialogParameters;
import com.facebook.share.internal.NativeDialogParameters;
import com.facebook.share.internal.ShareContentValidation;
import com.facebook.share.internal.ShareDialogFeature;
import com.facebook.share.internal.ShareFeedContent;
import com.facebook.share.internal.ShareInternalUtility;
import com.facebook.share.internal.ShareStoryFeature;
import com.facebook.share.internal.WebDialogParameters;
import com.facebook.share.model.ShareCameraEffectContent;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareStoryContent;
import com.facebook.share.model.ShareVideoContent;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.protocol.Request;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShareDialog.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u0000 12\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004:\u00070123456B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u0011\b\u0016\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB\u000f\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rB\u000f\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fB\u0017\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\u0010B\u0017\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\u0011B\u0017\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\u000e\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\u0012B\u0019\b\u0016\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\u0015J \u0010\u001e\u001a\u00020\u00172\u000e\u0010\u001f\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020#H\u0014J\b\u0010$\u001a\u00020\u0017H\u0016J*\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(2\u000e\u0010\u001f\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010 \u001a\u00020!H\u0002J\u001e\u0010)\u001a\u00020&2\u0006\u0010*\u001a\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00030-H\u0014J\u0010\u0010.\u001a\u00020&2\u0006\u0010\u001d\u001a\u00020\u0017H\u0016J \u0010/\u001a\u00020&2\u000e\u0010\u001f\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010 \u001a\u00020!H\u0016R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010\u0018\u001a \u0012\u001c\u0012\u001a0\u001aR\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0002\u0012\u0004\u0012\u00020\u00030\u00010\u0019X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/facebook/share/widget/ShareDialog;", "Lcom/facebook/internal/FacebookDialogBase;", "Lcom/facebook/share/model/ShareContent;", "Lcom/facebook/share/Sharer$Result;", "Lcom/facebook/share/Sharer;", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "requestCode", "", "(I)V", Request.JsonKeys.FRAGMENT, "Landroidx/fragment/app/Fragment;", "(Landroidx/fragment/app/Fragment;)V", "Landroid/app/Fragment;", "(Landroid/app/Fragment;)V", "(Landroid/app/Activity;I)V", "(Landroidx/fragment/app/Fragment;I)V", "(Landroid/app/Fragment;I)V", "fragmentWrapper", "Lcom/facebook/internal/FragmentWrapper;", "(Lcom/facebook/internal/FragmentWrapper;I)V", "isAutomaticMode", "", "orderedModeHandlers", "", "Lcom/facebook/internal/FacebookDialogBase$ModeHandler;", "getOrderedModeHandlers", "()Ljava/util/List;", "shouldFailOnDataError", "canShow", FirebaseAnalytics.Param.CONTENT, "mode", "Lcom/facebook/share/widget/ShareDialog$Mode;", "createBaseAppCall", "Lcom/facebook/internal/AppCall;", "getShouldFailOnDataError", "logDialogShare", "", "context", "Landroid/content/Context;", "registerCallbackImpl", "callbackManager", "Lcom/facebook/internal/CallbackManagerImpl;", "callback", "Lcom/facebook/FacebookCallback;", "setShouldFailOnDataError", "show", "CameraEffectHandler", "Companion", "FeedHandler", "Mode", "NativeHandler", "ShareStoryHandler", "WebShareHandler", "facebook-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public class ShareDialog extends FacebookDialogBase<ShareContent<?, ?>, Sharer.Result> implements Sharer {
    private static final String FEED_DIALOG = "feed";
    private static final String WEB_OG_SHARE_DIALOG = "share_open_graph";
    public static final String WEB_SHARE_DIALOG = "share";
    private boolean isAutomaticMode;
    private final List<FacebookDialogBase<ShareContent<?, ?>, Sharer.Result>.ModeHandler> orderedModeHandlers;
    private boolean shouldFailOnDataError;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "ShareDialog";
    private static final int DEFAULT_REQUEST_CODE = CallbackManagerImpl.RequestCodeOffset.Share.toRequestCode();

    /* compiled from: ShareDialog.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/facebook/share/widget/ShareDialog$Mode;", "", "(Ljava/lang/String;I)V", "AUTOMATIC", "NATIVE", "WEB", "FEED", "facebook-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public enum Mode {
        AUTOMATIC,
        NATIVE,
        WEB,
        FEED
    }

    /* compiled from: ShareDialog.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Mode.values().length];
            try {
                iArr[Mode.AUTOMATIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Mode.WEB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Mode.NATIVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static boolean canShow(Class<? extends ShareContent<?, ?>> cls) {
        return INSTANCE.canShow(cls);
    }

    @JvmStatic
    public static void show(Activity activity, ShareContent<?, ?> shareContent) {
        INSTANCE.show(activity, shareContent);
    }

    @JvmStatic
    public static void show(Fragment fragment, ShareContent<?, ?> shareContent) {
        INSTANCE.show(fragment, shareContent);
    }

    @JvmStatic
    public static void show(androidx.fragment.app.Fragment fragment, ShareContent<?, ?> shareContent) {
        INSTANCE.show(fragment, shareContent);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ShareDialog(Activity activity) {
        this(activity, DEFAULT_REQUEST_CODE);
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public ShareDialog(int i) {
        super(i);
        this.isAutomaticMode = true;
        this.orderedModeHandlers = CollectionsKt.arrayListOf(new NativeHandler(), new FeedHandler(), new WebShareHandler(), new CameraEffectHandler(), new ShareStoryHandler());
        ShareInternalUtility.registerStaticShareCallback(i);
    }

    public /* synthetic */ ShareDialog(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? DEFAULT_REQUEST_CODE : i);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ShareDialog(androidx.fragment.app.Fragment fragment) {
        this(new FragmentWrapper(fragment), 0, 2, null);
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ShareDialog(Fragment fragment) {
        this(new FragmentWrapper(fragment), 0, 2, null);
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShareDialog(Activity activity, int i) {
        super(activity, i);
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.isAutomaticMode = true;
        this.orderedModeHandlers = CollectionsKt.arrayListOf(new NativeHandler(), new FeedHandler(), new WebShareHandler(), new CameraEffectHandler(), new ShareStoryHandler());
        ShareInternalUtility.registerStaticShareCallback(i);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ShareDialog(androidx.fragment.app.Fragment fragment, int i) {
        this(new FragmentWrapper(fragment), i);
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ShareDialog(Fragment fragment, int i) {
        this(new FragmentWrapper(fragment), i);
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }

    public /* synthetic */ ShareDialog(FragmentWrapper fragmentWrapper, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(fragmentWrapper, (i2 & 2) != 0 ? DEFAULT_REQUEST_CODE : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShareDialog(FragmentWrapper fragmentWrapper, int i) {
        super(fragmentWrapper, i);
        Intrinsics.checkNotNullParameter(fragmentWrapper, "fragmentWrapper");
        this.isAutomaticMode = true;
        this.orderedModeHandlers = CollectionsKt.arrayListOf(new NativeHandler(), new FeedHandler(), new WebShareHandler(), new CameraEffectHandler(), new ShareStoryHandler());
        ShareInternalUtility.registerStaticShareCallback(i);
    }

    @Override // com.facebook.internal.FacebookDialogBase
    protected void registerCallbackImpl(CallbackManagerImpl callbackManager, FacebookCallback<Sharer.Result> callback) {
        Intrinsics.checkNotNullParameter(callbackManager, "callbackManager");
        Intrinsics.checkNotNullParameter(callback, "callback");
        ShareInternalUtility.registerSharerCallback(getRequestCodeField(), callbackManager, callback);
    }

    public boolean getShouldFailOnDataError() {
        return this.shouldFailOnDataError;
    }

    public void setShouldFailOnDataError(boolean shouldFailOnDataError) {
        this.shouldFailOnDataError = shouldFailOnDataError;
    }

    public boolean canShow(ShareContent<?, ?> content, Mode mode) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Object obj = mode;
        if (mode == Mode.AUTOMATIC) {
            obj = FacebookDialogBase.BASE_AUTOMATIC_MODE;
        }
        return canShowImpl(content, obj);
    }

    public void show(ShareContent<?, ?> content, Mode mode) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(mode, "mode");
        boolean z = mode == Mode.AUTOMATIC;
        this.isAutomaticMode = z;
        Object obj = mode;
        if (z) {
            obj = FacebookDialogBase.BASE_AUTOMATIC_MODE;
        }
        showImpl(content, obj);
    }

    @Override // com.facebook.internal.FacebookDialogBase
    protected AppCall createBaseAppCall() {
        return new AppCall(getRequestCodeField(), null, 2, null);
    }

    @Override // com.facebook.internal.FacebookDialogBase
    protected List<FacebookDialogBase<ShareContent<?, ?>, Sharer.Result>.ModeHandler> getOrderedModeHandlers() {
        return this.orderedModeHandlers;
    }

    /* compiled from: ShareDialog.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u001a0\u0001R\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00040\u0002B\u0005¢\u0006\u0002\u0010\u0005J \u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00032\u0006\u0010\u000f\u001a\u00020\rH\u0016J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u000e\u0010\u000e\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/facebook/share/widget/ShareDialog$NativeHandler;", "Lcom/facebook/internal/FacebookDialogBase$ModeHandler;", "Lcom/facebook/internal/FacebookDialogBase;", "Lcom/facebook/share/model/ShareContent;", "Lcom/facebook/share/Sharer$Result;", "(Lcom/facebook/share/widget/ShareDialog;)V", "mode", "", "getMode", "()Ljava/lang/Object;", "setMode", "(Ljava/lang/Object;)V", "canShow", "", FirebaseAnalytics.Param.CONTENT, "isBestEffort", "createAppCall", "Lcom/facebook/internal/AppCall;", "facebook-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    private final class NativeHandler extends FacebookDialogBase<ShareContent<?, ?>, Sharer.Result>.ModeHandler {
        private Object mode;

        public NativeHandler() {
            super();
            this.mode = Mode.NATIVE;
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public Object getMode() {
            return this.mode;
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public void setMode(Object obj) {
            Intrinsics.checkNotNullParameter(obj, "<set-?>");
            this.mode = obj;
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public boolean canShow(ShareContent<?, ?> content, boolean isBestEffort) {
            boolean z;
            Intrinsics.checkNotNullParameter(content, "content");
            if ((content instanceof ShareCameraEffectContent) || (content instanceof ShareStoryContent)) {
                return false;
            }
            if (!isBestEffort) {
                z = content.getShareHashtag() != null ? DialogPresenter.canPresentNativeDialogWithFeature(ShareDialogFeature.HASHTAG) : true;
                if (content instanceof ShareLinkContent) {
                    String quote = ((ShareLinkContent) content).getQuote();
                    if (!(quote == null || quote.length() == 0)) {
                        if (!z || !DialogPresenter.canPresentNativeDialogWithFeature(ShareDialogFeature.LINK_SHARE_QUOTES)) {
                            z = false;
                        }
                    }
                }
                return z && ShareDialog.INSTANCE.canShowNative(content.getClass());
            }
            z = true;
            if (z) {
                return false;
            }
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public AppCall createAppCall(final ShareContent<?, ?> content) {
            Intrinsics.checkNotNullParameter(content, "content");
            ShareDialog shareDialog = ShareDialog.this;
            shareDialog.logDialogShare(shareDialog.getActivityContext(), content, Mode.NATIVE);
            ShareContentValidation.validateForNativeShare(content);
            final AppCall createBaseAppCall = ShareDialog.this.createBaseAppCall();
            final boolean shouldFailOnDataError = ShareDialog.this.getShouldFailOnDataError();
            DialogFeature feature = ShareDialog.INSTANCE.getFeature(content.getClass());
            if (feature == null) {
                return null;
            }
            DialogPresenter.setupAppCallForNativeDialog(createBaseAppCall, new DialogPresenter.ParameterProvider() { // from class: com.facebook.share.widget.ShareDialog$NativeHandler$createAppCall$1
                @Override // com.facebook.internal.DialogPresenter.ParameterProvider
                public Bundle getParameters() {
                    return NativeDialogParameters.create(AppCall.this.getCallId(), content, shouldFailOnDataError);
                }

                @Override // com.facebook.internal.DialogPresenter.ParameterProvider
                public Bundle getLegacyParameters() {
                    return LegacyNativeDialogParameters.create(AppCall.this.getCallId(), content, shouldFailOnDataError);
                }
            }, feature);
            return createBaseAppCall;
        }
    }

    /* compiled from: ShareDialog.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u001a0\u0001R\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00040\u0002B\u0005¢\u0006\u0002\u0010\u0005J \u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00032\u0006\u0010\u000f\u001a\u00020\rH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u000e\u0010\u000e\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003H\u0016J\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\u0018\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003H\u0002R\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/facebook/share/widget/ShareDialog$WebShareHandler;", "Lcom/facebook/internal/FacebookDialogBase$ModeHandler;", "Lcom/facebook/internal/FacebookDialogBase;", "Lcom/facebook/share/model/ShareContent;", "Lcom/facebook/share/Sharer$Result;", "(Lcom/facebook/share/widget/ShareDialog;)V", "mode", "", "getMode", "()Ljava/lang/Object;", "setMode", "(Ljava/lang/Object;)V", "canShow", "", FirebaseAnalytics.Param.CONTENT, "isBestEffort", "createAndMapAttachments", "Lcom/facebook/share/model/SharePhotoContent;", "callId", "Ljava/util/UUID;", "createAppCall", "Lcom/facebook/internal/AppCall;", "getActionName", "", "shareContent", "facebook-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    private final class WebShareHandler extends FacebookDialogBase<ShareContent<?, ?>, Sharer.Result>.ModeHandler {
        private Object mode;

        public WebShareHandler() {
            super();
            this.mode = Mode.WEB;
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public Object getMode() {
            return this.mode;
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public void setMode(Object obj) {
            Intrinsics.checkNotNullParameter(obj, "<set-?>");
            this.mode = obj;
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public boolean canShow(ShareContent<?, ?> content, boolean isBestEffort) {
            Intrinsics.checkNotNullParameter(content, "content");
            return ShareDialog.INSTANCE.canShowWebCheck(content);
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public AppCall createAppCall(ShareContent<?, ?> content) {
            Bundle create;
            Intrinsics.checkNotNullParameter(content, "content");
            ShareDialog shareDialog = ShareDialog.this;
            shareDialog.logDialogShare(shareDialog.getActivityContext(), content, Mode.WEB);
            AppCall createBaseAppCall = ShareDialog.this.createBaseAppCall();
            ShareContentValidation.validateForWebShare(content);
            if (content instanceof ShareLinkContent) {
                create = WebDialogParameters.create((ShareLinkContent) content);
            } else {
                if (!(content instanceof SharePhotoContent)) {
                    return null;
                }
                create = WebDialogParameters.create(createAndMapAttachments((SharePhotoContent) content, createBaseAppCall.getCallId()));
            }
            DialogPresenter.setupAppCallForWebDialog(createBaseAppCall, getActionName(content), create);
            return createBaseAppCall;
        }

        private final String getActionName(ShareContent<?, ?> shareContent) {
            if ((shareContent instanceof ShareLinkContent) || (shareContent instanceof SharePhotoContent)) {
                return "share";
            }
            return null;
        }

        private final SharePhotoContent createAndMapAttachments(SharePhotoContent content, UUID callId) {
            SharePhotoContent.Builder readFrom = new SharePhotoContent.Builder().readFrom(content);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = content.getPhotos().size();
            for (int i = 0; i < size; i++) {
                SharePhoto sharePhoto = content.getPhotos().get(i);
                Bitmap bitmap = sharePhoto.getBitmap();
                if (bitmap != null) {
                    NativeAppCallAttachmentStore.Attachment createAttachment = NativeAppCallAttachmentStore.createAttachment(callId, bitmap);
                    sharePhoto = new SharePhoto.Builder().readFrom(sharePhoto).setImageUrl(Uri.parse(createAttachment.getAttachmentUrl())).setBitmap(null).build();
                    arrayList2.add(createAttachment);
                }
                arrayList.add(sharePhoto);
            }
            readFrom.setPhotos(arrayList);
            NativeAppCallAttachmentStore.addAttachments(arrayList2);
            return readFrom.build();
        }
    }

    /* compiled from: ShareDialog.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u001a0\u0001R\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00040\u0002B\u0005¢\u0006\u0002\u0010\u0005J \u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00032\u0006\u0010\u000f\u001a\u00020\rH\u0016J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u000e\u0010\u000e\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/facebook/share/widget/ShareDialog$FeedHandler;", "Lcom/facebook/internal/FacebookDialogBase$ModeHandler;", "Lcom/facebook/internal/FacebookDialogBase;", "Lcom/facebook/share/model/ShareContent;", "Lcom/facebook/share/Sharer$Result;", "(Lcom/facebook/share/widget/ShareDialog;)V", "mode", "", "getMode", "()Ljava/lang/Object;", "setMode", "(Ljava/lang/Object;)V", "canShow", "", FirebaseAnalytics.Param.CONTENT, "isBestEffort", "createAppCall", "Lcom/facebook/internal/AppCall;", "facebook-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    private final class FeedHandler extends FacebookDialogBase<ShareContent<?, ?>, Sharer.Result>.ModeHandler {
        private Object mode;

        public FeedHandler() {
            super();
            this.mode = Mode.FEED;
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public Object getMode() {
            return this.mode;
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public void setMode(Object obj) {
            Intrinsics.checkNotNullParameter(obj, "<set-?>");
            this.mode = obj;
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public boolean canShow(ShareContent<?, ?> content, boolean isBestEffort) {
            Intrinsics.checkNotNullParameter(content, "content");
            return (content instanceof ShareLinkContent) || (content instanceof ShareFeedContent);
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public AppCall createAppCall(ShareContent<?, ?> content) {
            Bundle createForFeed;
            Intrinsics.checkNotNullParameter(content, "content");
            ShareDialog shareDialog = ShareDialog.this;
            shareDialog.logDialogShare(shareDialog.getActivityContext(), content, Mode.FEED);
            AppCall createBaseAppCall = ShareDialog.this.createBaseAppCall();
            if (content instanceof ShareLinkContent) {
                ShareContentValidation.validateForWebShare(content);
                createForFeed = WebDialogParameters.createForFeed((ShareLinkContent) content);
            } else {
                if (!(content instanceof ShareFeedContent)) {
                    return null;
                }
                createForFeed = WebDialogParameters.createForFeed((ShareFeedContent) content);
            }
            DialogPresenter.setupAppCallForWebDialog(createBaseAppCall, ShareDialog.FEED_DIALOG, createForFeed);
            return createBaseAppCall;
        }
    }

    /* compiled from: ShareDialog.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u001a0\u0001R\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00040\u0002B\u0005¢\u0006\u0002\u0010\u0005J \u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00032\u0006\u0010\u000f\u001a\u00020\rH\u0016J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u000e\u0010\u000e\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/facebook/share/widget/ShareDialog$CameraEffectHandler;", "Lcom/facebook/internal/FacebookDialogBase$ModeHandler;", "Lcom/facebook/internal/FacebookDialogBase;", "Lcom/facebook/share/model/ShareContent;", "Lcom/facebook/share/Sharer$Result;", "(Lcom/facebook/share/widget/ShareDialog;)V", "mode", "", "getMode", "()Ljava/lang/Object;", "setMode", "(Ljava/lang/Object;)V", "canShow", "", FirebaseAnalytics.Param.CONTENT, "isBestEffort", "createAppCall", "Lcom/facebook/internal/AppCall;", "facebook-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    private final class CameraEffectHandler extends FacebookDialogBase<ShareContent<?, ?>, Sharer.Result>.ModeHandler {
        private Object mode;

        public CameraEffectHandler() {
            super();
            this.mode = Mode.NATIVE;
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public Object getMode() {
            return this.mode;
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public void setMode(Object obj) {
            Intrinsics.checkNotNullParameter(obj, "<set-?>");
            this.mode = obj;
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public boolean canShow(ShareContent<?, ?> content, boolean isBestEffort) {
            Intrinsics.checkNotNullParameter(content, "content");
            return (content instanceof ShareCameraEffectContent) && ShareDialog.INSTANCE.canShowNative(content.getClass());
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public AppCall createAppCall(final ShareContent<?, ?> content) {
            Intrinsics.checkNotNullParameter(content, "content");
            ShareContentValidation.validateForNativeShare(content);
            final AppCall createBaseAppCall = ShareDialog.this.createBaseAppCall();
            final boolean shouldFailOnDataError = ShareDialog.this.getShouldFailOnDataError();
            DialogFeature feature = ShareDialog.INSTANCE.getFeature(content.getClass());
            if (feature == null) {
                return null;
            }
            DialogPresenter.setupAppCallForNativeDialog(createBaseAppCall, new DialogPresenter.ParameterProvider() { // from class: com.facebook.share.widget.ShareDialog$CameraEffectHandler$createAppCall$1
                @Override // com.facebook.internal.DialogPresenter.ParameterProvider
                public Bundle getParameters() {
                    return NativeDialogParameters.create(AppCall.this.getCallId(), content, shouldFailOnDataError);
                }

                @Override // com.facebook.internal.DialogPresenter.ParameterProvider
                public Bundle getLegacyParameters() {
                    return LegacyNativeDialogParameters.create(AppCall.this.getCallId(), content, shouldFailOnDataError);
                }
            }, feature);
            return createBaseAppCall;
        }
    }

    /* compiled from: ShareDialog.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u001a0\u0001R\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00040\u0002B\u0005¢\u0006\u0002\u0010\u0005J \u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00032\u0006\u0010\u000f\u001a\u00020\rH\u0016J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u000e\u0010\u000e\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/facebook/share/widget/ShareDialog$ShareStoryHandler;", "Lcom/facebook/internal/FacebookDialogBase$ModeHandler;", "Lcom/facebook/internal/FacebookDialogBase;", "Lcom/facebook/share/model/ShareContent;", "Lcom/facebook/share/Sharer$Result;", "(Lcom/facebook/share/widget/ShareDialog;)V", "mode", "", "getMode", "()Ljava/lang/Object;", "setMode", "(Ljava/lang/Object;)V", "canShow", "", FirebaseAnalytics.Param.CONTENT, "isBestEffort", "createAppCall", "Lcom/facebook/internal/AppCall;", "facebook-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    private final class ShareStoryHandler extends FacebookDialogBase<ShareContent<?, ?>, Sharer.Result>.ModeHandler {
        private Object mode;

        public ShareStoryHandler() {
            super();
            this.mode = Mode.NATIVE;
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public Object getMode() {
            return this.mode;
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public void setMode(Object obj) {
            Intrinsics.checkNotNullParameter(obj, "<set-?>");
            this.mode = obj;
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public boolean canShow(ShareContent<?, ?> content, boolean isBestEffort) {
            Intrinsics.checkNotNullParameter(content, "content");
            return (content instanceof ShareStoryContent) && ShareDialog.INSTANCE.canShowNative(content.getClass());
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public AppCall createAppCall(final ShareContent<?, ?> content) {
            Intrinsics.checkNotNullParameter(content, "content");
            ShareContentValidation.validateForStoryShare(content);
            final AppCall createBaseAppCall = ShareDialog.this.createBaseAppCall();
            final boolean shouldFailOnDataError = ShareDialog.this.getShouldFailOnDataError();
            DialogFeature feature = ShareDialog.INSTANCE.getFeature(content.getClass());
            if (feature == null) {
                return null;
            }
            DialogPresenter.setupAppCallForNativeDialog(createBaseAppCall, new DialogPresenter.ParameterProvider() { // from class: com.facebook.share.widget.ShareDialog$ShareStoryHandler$createAppCall$1
                @Override // com.facebook.internal.DialogPresenter.ParameterProvider
                public Bundle getParameters() {
                    return NativeDialogParameters.create(AppCall.this.getCallId(), content, shouldFailOnDataError);
                }

                @Override // com.facebook.internal.DialogPresenter.ParameterProvider
                public Bundle getLegacyParameters() {
                    return LegacyNativeDialogParameters.create(AppCall.this.getCallId(), content, shouldFailOnDataError);
                }
            }, feature);
            return createBaseAppCall;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logDialogShare(Context context, ShareContent<?, ?> content, Mode mode) {
        if (this.isAutomaticMode) {
            mode = Mode.AUTOMATIC;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[mode.ordinal()];
        String str = "unknown";
        String str2 = i != 1 ? i != 2 ? i != 3 ? "unknown" : "native" : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_AUTOMATIC;
        DialogFeature feature = INSTANCE.getFeature(content.getClass());
        if (feature == ShareDialogFeature.SHARE_DIALOG) {
            str = "status";
        } else if (feature == ShareDialogFeature.PHOTOS) {
            str = AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO;
        } else if (feature == ShareDialogFeature.VIDEO) {
            str = "video";
        }
        InternalAppEventsLogger createInstance = InternalAppEventsLogger.INSTANCE.createInstance(context, FacebookSdk.getApplicationId());
        Bundle bundle = new Bundle();
        bundle.putString("fb_share_dialog_show", str2);
        bundle.putString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_TYPE, str);
        createInstance.logEventImplicitly("fb_share_dialog_show", bundle);
    }

    /* compiled from: ShareDialog.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u000b\u001a\u00020\f2\u0016\u0010\r\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000f0\u000eH\u0017J \u0010\u0010\u001a\u00020\f2\u0016\u0010\r\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000f0\u000eH\u0002J\u0018\u0010\u0011\u001a\u00020\f2\u000e\u0010\u0012\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000fH\u0002J \u0010\u0013\u001a\u00020\f2\u0016\u0010\r\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000f0\u000eH\u0002J\"\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0016\u0010\r\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000f0\u000eH\u0002J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u000e\u0010\u001a\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000fH\u0017J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001c2\u000e\u0010\u001a\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000fH\u0017J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001d2\u000e\u0010\u001a\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000fH\u0017J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u000e\u0010\u001a\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/facebook/share/widget/ShareDialog$Companion;", "", "()V", "DEFAULT_REQUEST_CODE", "", "FEED_DIALOG", "", "TAG", "kotlin.jvm.PlatformType", "WEB_OG_SHARE_DIALOG", "WEB_SHARE_DIALOG", "canShow", "", "contentType", "Ljava/lang/Class;", "Lcom/facebook/share/model/ShareContent;", "canShowNative", "canShowWebCheck", FirebaseAnalytics.Param.CONTENT, "canShowWebTypeCheck", "getFeature", "Lcom/facebook/internal/DialogFeature;", "show", "", "activity", "Landroid/app/Activity;", "shareContent", Request.JsonKeys.FRAGMENT, "Landroid/app/Fragment;", "Landroidx/fragment/app/Fragment;", "fragmentWrapper", "Lcom/facebook/internal/FragmentWrapper;", "facebook-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public void show(Activity activity, ShareContent<?, ?> shareContent) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(shareContent, "shareContent");
            new ShareDialog(activity).show(shareContent);
        }

        @JvmStatic
        public void show(androidx.fragment.app.Fragment fragment, ShareContent<?, ?> shareContent) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            Intrinsics.checkNotNullParameter(shareContent, "shareContent");
            show(new FragmentWrapper(fragment), shareContent);
        }

        @JvmStatic
        public void show(Fragment fragment, ShareContent<?, ?> shareContent) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            Intrinsics.checkNotNullParameter(shareContent, "shareContent");
            show(new FragmentWrapper(fragment), shareContent);
        }

        private final void show(FragmentWrapper fragmentWrapper, ShareContent<?, ?> shareContent) {
            new ShareDialog(fragmentWrapper, 0, 2, null).show(shareContent);
        }

        @JvmStatic
        public boolean canShow(Class<? extends ShareContent<?, ?>> contentType) {
            Intrinsics.checkNotNullParameter(contentType, "contentType");
            return canShowWebTypeCheck(contentType) || canShowNative(contentType);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean canShowNative(Class<? extends ShareContent<?, ?>> contentType) {
            DialogFeature feature = getFeature(contentType);
            return feature != null && DialogPresenter.canPresentNativeDialogWithFeature(feature);
        }

        private final boolean canShowWebTypeCheck(Class<? extends ShareContent<?, ?>> contentType) {
            return ShareLinkContent.class.isAssignableFrom(contentType) || (SharePhotoContent.class.isAssignableFrom(contentType) && AccessToken.INSTANCE.isCurrentAccessTokenActive());
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public final boolean canShowWebCheck(ShareContent<?, ?> content) {
            return canShowWebTypeCheck(content.getClass());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final DialogFeature getFeature(Class<? extends ShareContent<?, ?>> contentType) {
            if (ShareLinkContent.class.isAssignableFrom(contentType)) {
                return ShareDialogFeature.SHARE_DIALOG;
            }
            if (SharePhotoContent.class.isAssignableFrom(contentType)) {
                return ShareDialogFeature.PHOTOS;
            }
            if (ShareVideoContent.class.isAssignableFrom(contentType)) {
                return ShareDialogFeature.VIDEO;
            }
            if (ShareMediaContent.class.isAssignableFrom(contentType)) {
                return ShareDialogFeature.MULTIMEDIA;
            }
            if (ShareCameraEffectContent.class.isAssignableFrom(contentType)) {
                return CameraEffectFeature.SHARE_CAMERA_EFFECT;
            }
            if (ShareStoryContent.class.isAssignableFrom(contentType)) {
                return ShareStoryFeature.SHARE_STORY_ASSET;
            }
            return null;
        }
    }
}
