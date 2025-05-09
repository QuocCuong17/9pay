package com.facebook.share.internal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Pair;
import androidx.media3.common.MimeTypes;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphResponseException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.AppCall;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.NativeAppCallAttachmentStore;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.share.Sharer;
import com.facebook.share.model.CameraEffectTextures;
import com.facebook.share.model.ShareCameraEffectContent;
import com.facebook.share.model.ShareMedia;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareStoryContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.tekartik.sqflite.Constant;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ShareInternalUtility.kt */
@Metadata(d1 = {"\u0000î\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J&\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\"\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u000e\u0010\u0015\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0016H\u0002J\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u0010H\u0007J\u001e\u0010\u001c\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u00040\u001d2\u0006\u0010\u001e\u001a\u00020\u0004H\u0007J\"\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\u001b\u001a\u00020\u0010H\u0007J\u0012\u0010#\u001a\u0004\u0018\u00010\u00042\u0006\u0010$\u001a\u00020\u0018H\u0007J\"\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010 2\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010\u001b\u001a\u00020\u0010H\u0007J\u0012\u0010(\u001a\u0004\u0018\u00010\u00042\u0006\u0010$\u001a\u00020\u0018H\u0007J\u0018\u0010)\u001a\u00020*2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,H\u0007J\u001c\u0010.\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u0010H\u0007J\u001c\u0010/\u001a\u0004\u0018\u00010\u00182\b\u00100\u001a\u0004\u0018\u0001012\u0006\u0010\u001b\u001a\u00020\u0010H\u0007J\u0014\u00102\u001a\u0004\u0018\u00010\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007J\u001c\u00103\u001a\u0004\u0018\u00010\u00042\b\u00104\u001a\u0004\u0018\u0001052\u0006\u0010\u001b\u001a\u00020\u0010H\u0007J,\u00106\u001a\u0002072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u00108\u001a\u0004\u0018\u00010*H\u0007J\"\u00109\u001a\u00020:2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,2\b\u0010;\u001a\u0004\u0018\u00010\u0004H\u0007J \u0010<\u001a\u00020:2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,2\u0006\u0010=\u001a\u00020>H\u0007J*\u0010?\u001a\u00020:2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,2\b\u0010@\u001a\u0004\u0018\u00010\u00042\u0006\u0010A\u001a\u00020BH\u0007J\u0018\u0010C\u001a\u00020:2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,H\u0007J \u0010D\u001a\u00020:2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,2\u0006\u0010E\u001a\u00020FH\u0007J,\u0010D\u001a\u00020:2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,2\b\u0010G\u001a\u0004\u0018\u00010B2\b\u0010H\u001a\u0004\u0018\u00010\u0004H\u0007J\"\u0010D\u001a\u00020:2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,2\b\u0010H\u001a\u0004\u0018\u00010\u0004H\u0007J\"\u0010I\u001a\u00020:2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,2\b\u0010@\u001a\u0004\u0018\u00010\u0004H\u0007J\u001a\u0010J\u001a\u00020:2\u0006\u0010K\u001a\u00020\u00042\b\u0010L\u001a\u0004\u0018\u00010\u0004H\u0002J&\u0010M\u001a\u00020N2\b\u0010O\u001a\u0004\u0018\u00010P2\b\u0010Q\u001a\u0004\u0018\u00010\u00142\b\u0010+\u001a\u0004\u0018\u00010RH\u0007J$\u0010M\u001a\u00020N2\b\u0010O\u001a\u0004\u0018\u00010P2\u0006\u0010S\u001a\u00020\u00122\b\u0010+\u001a\u0004\u0018\u00010RH\u0007J&\u0010M\u001a\u00020N2\b\u0010O\u001a\u0004\u0018\u00010P2\b\u0010T\u001a\u0004\u0018\u00010U2\b\u0010+\u001a\u0004\u0018\u00010RH\u0007J*\u0010V\u001a\u00020:2\u0006\u0010\b\u001a\u00020\t2\b\u0010W\u001a\u0004\u0018\u00010X2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,H\u0007J\u0010\u0010Y\u001a\u00020:2\u0006\u0010\b\u001a\u00020\tH\u0007J\u0018\u0010Z\u001a\u00020[2\u0006\u0010\\\u001a\u00020[2\u0006\u0010]\u001a\u000207H\u0007J\u001c\u0010^\u001a\u0004\u0018\u00010_2\b\u0010`\u001a\u0004\u0018\u00010_2\u0006\u0010]\u001a\u000207H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006a"}, d2 = {"Lcom/facebook/share/internal/ShareInternalUtility;", "", "()V", "MY_STAGING_RESOURCES", "", "STAGING_PARAM", "getAppCallFromActivityResult", "Lcom/facebook/internal/AppCall;", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "getAttachment", "Lcom/facebook/internal/NativeAppCallAttachmentStore$Attachment;", "callId", "Ljava/util/UUID;", ShareConstants.MEDIA_URI, "Landroid/net/Uri;", "bitmap", "Landroid/graphics/Bitmap;", "medium", "Lcom/facebook/share/model/ShareMedia;", "getBackgroundAssetMediaInfo", "Landroid/os/Bundle;", "storyContent", "Lcom/facebook/share/model/ShareStoryContent;", "appCallId", "getFieldNameAndNamespaceFromFullName", "Landroid/util/Pair;", "fullName", "getMediaInfos", "", "mediaContent", "Lcom/facebook/share/model/ShareMediaContent;", "getNativeDialogCompletionGesture", Constant.PARAM_RESULT, "getPhotoUrls", "photoContent", "Lcom/facebook/share/model/SharePhotoContent;", "getShareDialogPostId", "getShareResultProcessor", "Lcom/facebook/share/internal/ResultProcessor;", "callback", "Lcom/facebook/FacebookCallback;", "Lcom/facebook/share/Sharer$Result;", "getStickerUrl", "getTextureUrlBundle", "cameraEffectContent", "Lcom/facebook/share/model/ShareCameraEffectContent;", "getUriExtension", "getVideoUrl", "videoContent", "Lcom/facebook/share/model/ShareVideoContent;", "handleActivityResult", "", "resultProcessor", "invokeCallbackWithError", "", "error", "invokeCallbackWithException", "exception", "Ljava/lang/Exception;", "invokeCallbackWithResults", ShareConstants.RESULT_POST_ID, "graphResponse", "Lcom/facebook/GraphResponse;", "invokeOnCancelCallback", "invokeOnErrorCallback", "ex", "Lcom/facebook/FacebookException;", "response", "message", "invokeOnSuccessCallback", "logShareResult", "shareOutcome", "errorMessage", "newUploadStagingResourceWithImageRequest", "Lcom/facebook/GraphRequest;", "accessToken", "Lcom/facebook/AccessToken;", "image", "Lcom/facebook/GraphRequest$Callback;", HVRetakeActivity.IMAGE_URI_TAG, "file", "Ljava/io/File;", "registerSharerCallback", "callbackManager", "Lcom/facebook/CallbackManager;", "registerStaticShareCallback", "removeNamespacesFromOGJsonArray", "Lorg/json/JSONArray;", "jsonArray", "requireNamespace", "removeNamespacesFromOGJsonObject", "Lorg/json/JSONObject;", "jsonObject", "facebook-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ShareInternalUtility {
    public static final ShareInternalUtility INSTANCE = new ShareInternalUtility();
    public static final String MY_STAGING_RESOURCES = "me/staging_resources";
    public static final String STAGING_PARAM = "file";

    private ShareInternalUtility() {
    }

    @JvmStatic
    public static final void invokeCallbackWithException(FacebookCallback<Sharer.Result> callback, Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        if (exception instanceof FacebookException) {
            invokeOnErrorCallback(callback, (FacebookException) exception);
            return;
        }
        invokeCallbackWithError(callback, "Error preparing share content: " + exception.getLocalizedMessage());
    }

    @JvmStatic
    public static final void invokeCallbackWithError(FacebookCallback<Sharer.Result> callback, String error) {
        invokeOnErrorCallback(callback, error);
    }

    @JvmStatic
    public static final void invokeCallbackWithResults(FacebookCallback<Sharer.Result> callback, String postId, GraphResponse graphResponse) {
        Intrinsics.checkNotNullParameter(graphResponse, "graphResponse");
        FacebookRequestError error = graphResponse.getError();
        if (error != null) {
            String errorMessage = error.getErrorMessage();
            if (Utility.isNullOrEmpty(errorMessage)) {
                errorMessage = "Unexpected error sharing.";
            }
            invokeOnErrorCallback(callback, graphResponse, errorMessage);
            return;
        }
        invokeOnSuccessCallback(callback, postId);
    }

    @JvmStatic
    public static final String getNativeDialogCompletionGesture(Bundle result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.containsKey(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY)) {
            return result.getString(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY);
        }
        return result.getString(NativeProtocol.EXTRA_DIALOG_COMPLETION_GESTURE_KEY);
    }

    @JvmStatic
    public static final String getShareDialogPostId(Bundle result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.containsKey(ShareConstants.RESULT_POST_ID)) {
            return result.getString(ShareConstants.RESULT_POST_ID);
        }
        if (result.containsKey(ShareConstants.EXTRA_RESULT_POST_ID)) {
            return result.getString(ShareConstants.EXTRA_RESULT_POST_ID);
        }
        return result.getString(ShareConstants.WEB_DIALOG_RESULT_PARAM_POST_ID);
    }

    @JvmStatic
    public static final boolean handleActivityResult(int requestCode, int resultCode, Intent data, ResultProcessor resultProcessor) {
        AppCall appCallFromActivityResult = INSTANCE.getAppCallFromActivityResult(requestCode, resultCode, data);
        if (appCallFromActivityResult == null) {
            return false;
        }
        NativeAppCallAttachmentStore.cleanupAttachmentsForCall(appCallFromActivityResult.getCallId());
        if (resultProcessor == null) {
            return true;
        }
        FacebookException exceptionFromErrorData = data != null ? NativeProtocol.getExceptionFromErrorData(NativeProtocol.getErrorDataFromResultIntent(data)) : null;
        if (exceptionFromErrorData != null) {
            if (exceptionFromErrorData instanceof FacebookOperationCanceledException) {
                resultProcessor.onCancel(appCallFromActivityResult);
            } else {
                resultProcessor.onError(appCallFromActivityResult, exceptionFromErrorData);
            }
        } else {
            resultProcessor.onSuccess(appCallFromActivityResult, data != null ? NativeProtocol.getSuccessResultsFromIntent(data) : null);
        }
        return true;
    }

    @JvmStatic
    public static final ResultProcessor getShareResultProcessor(final FacebookCallback<Sharer.Result> callback) {
        return new ResultProcessor(callback) { // from class: com.facebook.share.internal.ShareInternalUtility$getShareResultProcessor$1
            final /* synthetic */ FacebookCallback<Sharer.Result> $callback;

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
                    String nativeDialogCompletionGesture = ShareInternalUtility.getNativeDialogCompletionGesture(results);
                    if (nativeDialogCompletionGesture == null || StringsKt.equals("post", nativeDialogCompletionGesture, true)) {
                        ShareInternalUtility.invokeOnSuccessCallback(this.$callback, ShareInternalUtility.getShareDialogPostId(results));
                    } else if (StringsKt.equals(Constant.PARAM_CANCEL, nativeDialogCompletionGesture, true)) {
                        ShareInternalUtility.invokeOnCancelCallback(this.$callback);
                    } else {
                        ShareInternalUtility.invokeOnErrorCallback(this.$callback, new FacebookException(NativeProtocol.ERROR_UNKNOWN_ERROR));
                    }
                }
            }

            @Override // com.facebook.share.internal.ResultProcessor
            public void onCancel(AppCall appCall) {
                Intrinsics.checkNotNullParameter(appCall, "appCall");
                ShareInternalUtility.invokeOnCancelCallback(this.$callback);
            }

            @Override // com.facebook.share.internal.ResultProcessor
            public void onError(AppCall appCall, FacebookException error) {
                Intrinsics.checkNotNullParameter(appCall, "appCall");
                Intrinsics.checkNotNullParameter(error, "error");
                ShareInternalUtility.invokeOnErrorCallback(this.$callback, error);
            }
        };
    }

    private final AppCall getAppCallFromActivityResult(int requestCode, int resultCode, Intent data) {
        UUID callIdFromIntent = NativeProtocol.getCallIdFromIntent(data);
        if (callIdFromIntent == null) {
            return null;
        }
        return AppCall.INSTANCE.finishPendingCall(callIdFromIntent, requestCode);
    }

    @JvmStatic
    public static final void registerStaticShareCallback(final int requestCode) {
        CallbackManagerImpl.INSTANCE.registerStaticCallback(requestCode, new CallbackManagerImpl.Callback() { // from class: com.facebook.share.internal.ShareInternalUtility$$ExternalSyntheticLambda0
            @Override // com.facebook.internal.CallbackManagerImpl.Callback
            public final boolean onActivityResult(int i, Intent intent) {
                boolean registerStaticShareCallback$lambda$0;
                registerStaticShareCallback$lambda$0 = ShareInternalUtility.registerStaticShareCallback$lambda$0(requestCode, i, intent);
                return registerStaticShareCallback$lambda$0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean registerStaticShareCallback$lambda$0(int i, int i2, Intent intent) {
        return handleActivityResult(i, i2, intent, getShareResultProcessor(null));
    }

    @JvmStatic
    public static final void registerSharerCallback(final int requestCode, CallbackManager callbackManager, final FacebookCallback<Sharer.Result> callback) {
        if (!(callbackManager instanceof CallbackManagerImpl)) {
            throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
        }
        ((CallbackManagerImpl) callbackManager).registerCallback(requestCode, new CallbackManagerImpl.Callback() { // from class: com.facebook.share.internal.ShareInternalUtility$$ExternalSyntheticLambda1
            @Override // com.facebook.internal.CallbackManagerImpl.Callback
            public final boolean onActivityResult(int i, Intent intent) {
                boolean registerSharerCallback$lambda$1;
                registerSharerCallback$lambda$1 = ShareInternalUtility.registerSharerCallback$lambda$1(requestCode, callback, i, intent);
                return registerSharerCallback$lambda$1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean registerSharerCallback$lambda$1(int i, FacebookCallback facebookCallback, int i2, Intent intent) {
        return handleActivityResult(i, i2, intent, getShareResultProcessor(facebookCallback));
    }

    @JvmStatic
    public static final List<String> getPhotoUrls(SharePhotoContent photoContent, UUID appCallId) {
        List<SharePhoto> photos;
        Intrinsics.checkNotNullParameter(appCallId, "appCallId");
        if (photoContent == null || (photos = photoContent.getPhotos()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = photos.iterator();
        while (it.hasNext()) {
            NativeAppCallAttachmentStore.Attachment attachment = INSTANCE.getAttachment(appCallId, (SharePhoto) it.next());
            if (attachment != null) {
                arrayList.add(attachment);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = arrayList2;
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            arrayList4.add(((NativeAppCallAttachmentStore.Attachment) it2.next()).getAttachmentUrl());
        }
        ArrayList arrayList5 = arrayList4;
        NativeAppCallAttachmentStore.addAttachments(arrayList2);
        return arrayList5;
    }

    @JvmStatic
    public static final String getVideoUrl(ShareVideoContent videoContent, UUID appCallId) {
        ShareVideo video;
        Uri localUrl;
        Intrinsics.checkNotNullParameter(appCallId, "appCallId");
        if (videoContent == null || (video = videoContent.getVideo()) == null || (localUrl = video.getLocalUrl()) == null) {
            return null;
        }
        NativeAppCallAttachmentStore.Attachment createAttachment = NativeAppCallAttachmentStore.createAttachment(appCallId, localUrl);
        NativeAppCallAttachmentStore.addAttachments(CollectionsKt.listOf(createAttachment));
        return createAttachment.getAttachmentUrl();
    }

    @JvmStatic
    public static final List<Bundle> getMediaInfos(ShareMediaContent mediaContent, UUID appCallId) {
        List<ShareMedia<?, ?>> media;
        Bundle bundle;
        Intrinsics.checkNotNullParameter(appCallId, "appCallId");
        if (mediaContent == null || (media = mediaContent.getMedia()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (ShareMedia<?, ?> shareMedia : media) {
            NativeAppCallAttachmentStore.Attachment attachment = INSTANCE.getAttachment(appCallId, shareMedia);
            if (attachment == null) {
                bundle = null;
            } else {
                arrayList.add(attachment);
                bundle = new Bundle();
                bundle.putString("type", shareMedia.getMediaType().name());
                bundle.putString(ShareConstants.MEDIA_URI, attachment.getAttachmentUrl());
            }
            if (bundle != null) {
                arrayList2.add(bundle);
            }
        }
        ArrayList arrayList3 = arrayList2;
        NativeAppCallAttachmentStore.addAttachments(arrayList);
        return arrayList3;
    }

    @JvmStatic
    public static final Bundle getTextureUrlBundle(ShareCameraEffectContent cameraEffectContent, UUID appCallId) {
        CameraEffectTextures textures;
        Intrinsics.checkNotNullParameter(appCallId, "appCallId");
        if (cameraEffectContent == null || (textures = cameraEffectContent.getTextures()) == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        ArrayList arrayList = new ArrayList();
        for (String str : textures.keySet()) {
            NativeAppCallAttachmentStore.Attachment attachment = INSTANCE.getAttachment(appCallId, textures.getTextureUri(str), textures.getTextureBitmap(str));
            if (attachment != null) {
                arrayList.add(attachment);
                bundle.putString(str, attachment.getAttachmentUrl());
            }
        }
        NativeAppCallAttachmentStore.addAttachments(arrayList);
        return bundle;
    }

    @JvmStatic
    public static final JSONArray removeNamespacesFromOGJsonArray(JSONArray jsonArray, boolean requireNamespace) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
        JSONArray jSONArray = new JSONArray();
        int length = jsonArray.length();
        for (int i = 0; i < length; i++) {
            Object obj = jsonArray.get(i);
            if (obj instanceof JSONArray) {
                obj = removeNamespacesFromOGJsonArray((JSONArray) obj, requireNamespace);
            } else if (obj instanceof JSONObject) {
                obj = removeNamespacesFromOGJsonObject((JSONObject) obj, requireNamespace);
            }
            jSONArray.put(obj);
        }
        return jSONArray;
    }

    @JvmStatic
    public static final JSONObject removeNamespacesFromOGJsonObject(JSONObject jsonObject, boolean requireNamespace) {
        if (jsonObject == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            JSONArray names = jsonObject.names();
            if (names == null) {
                return null;
            }
            int length = names.length();
            for (int i = 0; i < length; i++) {
                String key = names.getString(i);
                Object obj = jsonObject.get(key);
                if (obj instanceof JSONObject) {
                    obj = removeNamespacesFromOGJsonObject((JSONObject) obj, true);
                } else if (obj instanceof JSONArray) {
                    obj = removeNamespacesFromOGJsonArray((JSONArray) obj, true);
                }
                Intrinsics.checkNotNullExpressionValue(key, "key");
                Pair<String, String> fieldNameAndNamespaceFromFullName = getFieldNameAndNamespaceFromFullName(key);
                String str = (String) fieldNameAndNamespaceFromFullName.first;
                String str2 = (String) fieldNameAndNamespaceFromFullName.second;
                if (requireNamespace) {
                    if (str != null && Intrinsics.areEqual(str, DeviceRequestsHelper.SDK_HEADER)) {
                        jSONObject.put(key, obj);
                    } else {
                        if (str != null && !Intrinsics.areEqual(str, "og")) {
                            jSONObject2.put(str2, obj);
                        }
                        jSONObject.put(str2, obj);
                    }
                } else if (str != null && Intrinsics.areEqual(str, "fb")) {
                    jSONObject.put(key, obj);
                } else {
                    jSONObject.put(str2, obj);
                }
            }
            if (jSONObject2.length() > 0) {
                jSONObject.put("data", jSONObject2);
            }
            return jSONObject;
        } catch (JSONException unused) {
            throw new FacebookException("Failed to create json object from share content");
        }
    }

    @JvmStatic
    public static final Pair<String, String> getFieldNameAndNamespaceFromFullName(String fullName) {
        String str;
        int i;
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        int indexOf$default = StringsKt.indexOf$default((CharSequence) fullName, ':', 0, false, 6, (Object) null);
        if (indexOf$default == -1 || fullName.length() <= (i = indexOf$default + 1)) {
            str = null;
        } else {
            str = fullName.substring(0, indexOf$default);
            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
            fullName = fullName.substring(i);
            Intrinsics.checkNotNullExpressionValue(fullName, "this as java.lang.String).substring(startIndex)");
        }
        return new Pair<>(str, fullName);
    }

    private final NativeAppCallAttachmentStore.Attachment getAttachment(UUID callId, ShareMedia<?, ?> medium) {
        Bitmap bitmap;
        Uri localUrl;
        Uri uri = null;
        Bitmap bitmap2 = null;
        if (medium instanceof SharePhoto) {
            SharePhoto sharePhoto = (SharePhoto) medium;
            bitmap2 = sharePhoto.getBitmap();
            localUrl = sharePhoto.getImageUrl();
        } else if (medium instanceof ShareVideo) {
            localUrl = ((ShareVideo) medium).getLocalUrl();
        } else {
            bitmap = null;
            return getAttachment(callId, uri, bitmap);
        }
        Bitmap bitmap3 = bitmap2;
        uri = localUrl;
        bitmap = bitmap3;
        return getAttachment(callId, uri, bitmap);
    }

    private final NativeAppCallAttachmentStore.Attachment getAttachment(UUID callId, Uri uri, Bitmap bitmap) {
        if (bitmap != null) {
            return NativeAppCallAttachmentStore.createAttachment(callId, bitmap);
        }
        if (uri != null) {
            return NativeAppCallAttachmentStore.createAttachment(callId, uri);
        }
        return null;
    }

    @JvmStatic
    public static final void invokeOnCancelCallback(FacebookCallback<Sharer.Result> callback) {
        INSTANCE.logShareResult(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED, null);
        if (callback != null) {
            callback.onCancel();
        }
    }

    @JvmStatic
    public static final void invokeOnSuccessCallback(FacebookCallback<Sharer.Result> callback, String postId) {
        INSTANCE.logShareResult(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED, null);
        if (callback != null) {
            callback.onSuccess(new Sharer.Result(postId));
        }
    }

    @JvmStatic
    public static final void invokeOnErrorCallback(FacebookCallback<Sharer.Result> callback, GraphResponse response, String message) {
        INSTANCE.logShareResult("error", message);
        if (callback != null) {
            callback.onError(new FacebookGraphResponseException(response, message));
        }
    }

    @JvmStatic
    public static final void invokeOnErrorCallback(FacebookCallback<Sharer.Result> callback, String message) {
        INSTANCE.logShareResult("error", message);
        if (callback != null) {
            callback.onError(new FacebookException(message));
        }
    }

    @JvmStatic
    public static final void invokeOnErrorCallback(FacebookCallback<Sharer.Result> callback, FacebookException ex) {
        Intrinsics.checkNotNullParameter(ex, "ex");
        INSTANCE.logShareResult("error", ex.getMessage());
        if (callback != null) {
            callback.onError(ex);
        }
    }

    private final void logShareResult(String shareOutcome, String errorMessage) {
        InternalAppEventsLogger internalAppEventsLogger = new InternalAppEventsLogger(FacebookSdk.getApplicationContext());
        Bundle bundle = new Bundle();
        bundle.putString(AnalyticsEvents.PARAMETER_SHARE_OUTCOME, shareOutcome);
        if (errorMessage != null) {
            bundle.putString("error_message", errorMessage);
        }
        internalAppEventsLogger.logEventImplicitly(AnalyticsEvents.EVENT_SHARE_RESULT, bundle);
    }

    @JvmStatic
    public static final GraphRequest newUploadStagingResourceWithImageRequest(AccessToken accessToken, Bitmap image, GraphRequest.Callback callback) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("file", image);
        return new GraphRequest(accessToken, MY_STAGING_RESOURCES, bundle, HttpMethod.POST, callback, null, 32, null);
    }

    @JvmStatic
    public static final GraphRequest newUploadStagingResourceWithImageRequest(AccessToken accessToken, File file, GraphRequest.Callback callback) throws FileNotFoundException {
        GraphRequest.ParcelableResourceWithMimeType parcelableResourceWithMimeType = new GraphRequest.ParcelableResourceWithMimeType(ParcelFileDescriptor.open(file, 268435456), MimeTypes.IMAGE_PNG);
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("file", parcelableResourceWithMimeType);
        return new GraphRequest(accessToken, MY_STAGING_RESOURCES, bundle, HttpMethod.POST, callback, null, 32, null);
    }

    @JvmStatic
    public static final GraphRequest newUploadStagingResourceWithImageRequest(AccessToken accessToken, Uri imageUri, GraphRequest.Callback callback) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(imageUri, "imageUri");
        String path = imageUri.getPath();
        if (Utility.isFileUri(imageUri) && path != null) {
            return newUploadStagingResourceWithImageRequest(accessToken, new File(path), callback);
        }
        if (!Utility.isContentUri(imageUri)) {
            throw new FacebookException("The image Uri must be either a file:// or content:// Uri");
        }
        GraphRequest.ParcelableResourceWithMimeType parcelableResourceWithMimeType = new GraphRequest.ParcelableResourceWithMimeType(imageUri, MimeTypes.IMAGE_PNG);
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("file", parcelableResourceWithMimeType);
        return new GraphRequest(accessToken, MY_STAGING_RESOURCES, bundle, HttpMethod.POST, callback, null, 32, null);
    }

    @JvmStatic
    public static final Bundle getStickerUrl(ShareStoryContent storyContent, UUID appCallId) {
        Intrinsics.checkNotNullParameter(appCallId, "appCallId");
        if (storyContent == null || storyContent.getStickerAsset() == null) {
            return null;
        }
        new ArrayList().add(storyContent.getStickerAsset());
        NativeAppCallAttachmentStore.Attachment attachment = INSTANCE.getAttachment(appCallId, storyContent.getStickerAsset());
        if (attachment == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(ShareConstants.MEDIA_URI, attachment.getAttachmentUrl());
        String uriExtension = getUriExtension(attachment.getOriginalUri());
        if (uriExtension != null) {
            Utility.putNonEmptyString(bundle, ShareConstants.MEDIA_EXTENSION, uriExtension);
        }
        NativeAppCallAttachmentStore.addAttachments(CollectionsKt.listOf(attachment));
        return bundle;
    }

    @JvmStatic
    public static final Bundle getBackgroundAssetMediaInfo(ShareStoryContent storyContent, UUID appCallId) {
        Intrinsics.checkNotNullParameter(appCallId, "appCallId");
        Bundle bundle = null;
        if (storyContent != null && storyContent.getBackgroundAsset() != null) {
            ShareMedia<?, ?> backgroundAsset = storyContent.getBackgroundAsset();
            NativeAppCallAttachmentStore.Attachment attachment = INSTANCE.getAttachment(appCallId, backgroundAsset);
            if (attachment == null) {
                return null;
            }
            bundle = new Bundle();
            bundle.putString("type", backgroundAsset.getMediaType().name());
            bundle.putString(ShareConstants.MEDIA_URI, attachment.getAttachmentUrl());
            String uriExtension = getUriExtension(attachment.getOriginalUri());
            if (uriExtension != null) {
                Utility.putNonEmptyString(bundle, ShareConstants.MEDIA_EXTENSION, uriExtension);
            }
            NativeAppCallAttachmentStore.addAttachments(CollectionsKt.listOf(attachment));
        }
        return bundle;
    }

    @JvmStatic
    public static final String getUriExtension(Uri uri) {
        if (uri == null) {
            return null;
        }
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "uri.toString()");
        int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) uri2, FilenameUtils.EXTENSION_SEPARATOR, 0, false, 6, (Object) null);
        if (lastIndexOf$default == -1) {
            return null;
        }
        String substring = uri2.substring(lastIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
        return substring;
    }
}
