package com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.MimeTypeMap;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.R;
import androidx.appcompat.app.AlertDialog;
import androidx.browser.trusted.sharing.ShareTarget;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFileProvider;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.in_app_browser.ActivityResultListener;
import com.pichillilorenzo.flutter_inappwebview_android.in_app_browser.InAppBrowserDelegate;
import com.pichillilorenzo.flutter_inappwebview_android.types.CreateWindowAction;
import com.pichillilorenzo.flutter_inappwebview_android.types.GeolocationPermissionShowPromptResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.JsAlertResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.JsBeforeUnloadResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.JsConfirmResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.JsPromptResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.PermissionResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.URLRequest;
import com.pichillilorenzo.flutter_inappwebview_android.webview.WebViewChannelDelegate;
import io.flutter.plugin.common.PluginRegistry;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;

/* loaded from: classes5.dex */
public class InAppWebViewChromeClient extends WebChromeClient implements PluginRegistry.ActivityResultListener, ActivityResultListener {
    protected static final FrameLayout.LayoutParams FULLSCREEN_LAYOUT_PARAMS = new FrameLayout.LayoutParams(-1, -1, 17);
    protected static final int FULLSCREEN_SYSTEM_UI_VISIBILITY = 1798;
    protected static final int FULLSCREEN_SYSTEM_UI_VISIBILITY_KITKAT = 7942;
    protected static final String LOG_TAG = "IABWebChromeClient";
    private static final int PICKER = 1;
    private static final int PICKER_LEGACY = 3;
    final String DEFAULT_MIME_TYPES = "*/*";
    final Map<DialogInterface, JsResult> dialogs = new HashMap();
    private ValueCallback<Uri[]> filePathCallback;
    private ValueCallback<Uri> filePathCallbackLegacy;
    private Uri imageOutputFileUri;
    private InAppBrowserDelegate inAppBrowserDelegate;
    public InAppWebView inAppWebView;
    private View mCustomView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    private int mOriginalOrientation;
    private int mOriginalSystemUiVisibility;
    public InAppWebViewFlutterPlugin plugin;
    private Uri videoOutputFileUri;

    public InAppWebViewChromeClient(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin, InAppWebView inAppWebView, InAppBrowserDelegate inAppBrowserDelegate) {
        this.plugin = inAppWebViewFlutterPlugin;
        this.inAppWebView = inAppWebView;
        this.inAppBrowserDelegate = inAppBrowserDelegate;
        if (inAppBrowserDelegate != null) {
            inAppBrowserDelegate.getActivityResultListeners().add(this);
        }
        if (inAppWebViewFlutterPlugin.activityPluginBinding != null) {
            inAppWebViewFlutterPlugin.activityPluginBinding.addActivityResultListener(this);
        }
    }

    @Override // android.webkit.WebChromeClient
    public Bitmap getDefaultVideoPoster() {
        InAppWebView inAppWebView = this.inAppWebView;
        if (inAppWebView != null && inAppWebView.customSettings.defaultVideoPoster != null) {
            byte[] bArr = this.inAppWebView.customSettings.defaultVideoPoster;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inMutable = true;
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        }
        return Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
    }

    @Override // android.webkit.WebChromeClient
    public void onHideCustomView() {
        ViewGroup rootView;
        Activity activity = getActivity();
        if (activity == null || (rootView = getRootView()) == null) {
            return;
        }
        View view = this.mCustomView;
        if (view != null) {
            ((FrameLayout) rootView).removeView(view);
        }
        this.mCustomView = null;
        rootView.setSystemUiVisibility(this.mOriginalSystemUiVisibility);
        activity.setRequestedOrientation(this.mOriginalOrientation);
        WebChromeClient.CustomViewCallback customViewCallback = this.mCustomViewCallback;
        if (customViewCallback != null) {
            customViewCallback.onCustomViewHidden();
        }
        this.mCustomViewCallback = null;
        activity.getWindow().clearFlags(512);
        InAppWebView inAppWebView = this.inAppWebView;
        if (inAppWebView != null) {
            WebViewChannelDelegate webViewChannelDelegate = inAppWebView.channelDelegate;
            if (webViewChannelDelegate != null) {
                webViewChannelDelegate.onExitFullscreen();
            }
            this.inAppWebView.setInFullscreen(false);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        ViewGroup rootView;
        if (this.mCustomView != null) {
            onHideCustomView();
            return;
        }
        Activity activity = getActivity();
        if (activity == null || (rootView = getRootView()) == null) {
            return;
        }
        this.mCustomView = view;
        this.mOriginalSystemUiVisibility = rootView.getSystemUiVisibility();
        this.mOriginalOrientation = activity.getRequestedOrientation();
        this.mCustomViewCallback = customViewCallback;
        View view2 = this.mCustomView;
        if (view2 != null) {
            view2.setBackgroundColor(-16777216);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            rootView.setSystemUiVisibility(FULLSCREEN_SYSTEM_UI_VISIBILITY_KITKAT);
        } else {
            rootView.setSystemUiVisibility(FULLSCREEN_SYSTEM_UI_VISIBILITY);
        }
        activity.getWindow().setFlags(512, 512);
        ((FrameLayout) rootView).addView(this.mCustomView, FULLSCREEN_LAYOUT_PARAMS);
        InAppWebView inAppWebView = this.inAppWebView;
        if (inAppWebView != null) {
            WebViewChannelDelegate webViewChannelDelegate = inAppWebView.channelDelegate;
            if (webViewChannelDelegate != null) {
                webViewChannelDelegate.onEnterFullscreen();
            }
            this.inAppWebView.setInFullscreen(true);
        }
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsAlert(WebView webView, String str, final String str2, final JsResult jsResult) {
        InAppWebView inAppWebView = this.inAppWebView;
        if (inAppWebView == null || inAppWebView.channelDelegate == null) {
            return false;
        }
        this.inAppWebView.channelDelegate.onJsAlert(str, str2, null, new WebViewChannelDelegate.JsAlertCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.1
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public boolean nonNullSuccess(JsAlertResponse jsAlertResponse) {
                if (!jsAlertResponse.isHandledByClient()) {
                    return true;
                }
                Integer action = jsAlertResponse.getAction();
                if (Integer.valueOf(action != null ? action.intValue() : 1).intValue() == 0) {
                    jsResult.confirm();
                    return false;
                }
                jsResult.cancel();
                return false;
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public void defaultBehaviour(JsAlertResponse jsAlertResponse) {
                String str3;
                String str4 = null;
                if (jsAlertResponse != null) {
                    str4 = jsAlertResponse.getMessage();
                    str3 = jsAlertResponse.getConfirmButtonTitle();
                } else {
                    str3 = null;
                }
                InAppWebViewChromeClient.this.createAlertDialog(str2, jsResult, str4, str3);
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
            public void error(String str3, String str4, Object obj) {
                StringBuilder sb = new StringBuilder();
                sb.append(str3);
                sb.append(", ");
                if (str4 == null) {
                    str4 = "";
                }
                sb.append(str4);
                Log.e(InAppWebViewChromeClient.LOG_TAG, sb.toString());
                jsResult.cancel();
            }
        });
        return true;
    }

    public void createAlertDialog(String str, final JsResult jsResult, String str2, String str3) {
        if (str2 != null && !str2.isEmpty()) {
            str = str2;
        }
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                jsResult.confirm();
                dialogInterface.dismiss();
                InAppWebViewChromeClient.this.dialogs.remove(dialogInterface);
            }
        };
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.Theme_AppCompat_Dialog_Alert);
        builder.setMessage(str);
        if (str3 != null && !str3.isEmpty()) {
            builder.setPositiveButton(str3, onClickListener);
        } else {
            builder.setPositiveButton(android.R.string.ok, onClickListener);
        }
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.3
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                jsResult.cancel();
                dialogInterface.dismiss();
                InAppWebViewChromeClient.this.dialogs.remove(dialogInterface);
            }
        });
        AlertDialog create = builder.create();
        this.dialogs.put(create, jsResult);
        create.show();
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsConfirm(WebView webView, String str, final String str2, final JsResult jsResult) {
        InAppWebView inAppWebView = this.inAppWebView;
        if (inAppWebView == null || inAppWebView.channelDelegate == null) {
            return false;
        }
        this.inAppWebView.channelDelegate.onJsConfirm(str, str2, null, new WebViewChannelDelegate.JsConfirmCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.4
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public boolean nonNullSuccess(JsConfirmResponse jsConfirmResponse) {
                if (!jsConfirmResponse.isHandledByClient()) {
                    return true;
                }
                Integer action = jsConfirmResponse.getAction();
                if (Integer.valueOf(action != null ? action.intValue() : 1).intValue() == 0) {
                    jsResult.confirm();
                    return false;
                }
                jsResult.cancel();
                return false;
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public void defaultBehaviour(JsConfirmResponse jsConfirmResponse) {
                String str3;
                String str4;
                String str5;
                if (jsConfirmResponse != null) {
                    String message = jsConfirmResponse.getMessage();
                    String confirmButtonTitle = jsConfirmResponse.getConfirmButtonTitle();
                    str5 = jsConfirmResponse.getCancelButtonTitle();
                    str3 = message;
                    str4 = confirmButtonTitle;
                } else {
                    str3 = null;
                    str4 = null;
                    str5 = null;
                }
                InAppWebViewChromeClient.this.createConfirmDialog(str2, jsResult, str3, str4, str5);
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
            public void error(String str3, String str4, Object obj) {
                StringBuilder sb = new StringBuilder();
                sb.append(str3);
                sb.append(", ");
                if (str4 == null) {
                    str4 = "";
                }
                sb.append(str4);
                Log.e(InAppWebViewChromeClient.LOG_TAG, sb.toString());
                jsResult.cancel();
            }
        });
        return true;
    }

    public void createConfirmDialog(String str, final JsResult jsResult, String str2, String str3, String str4) {
        if (str2 != null && !str2.isEmpty()) {
            str = str2;
        }
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                jsResult.confirm();
                dialogInterface.dismiss();
                InAppWebViewChromeClient.this.dialogs.remove(dialogInterface);
            }
        };
        DialogInterface.OnClickListener onClickListener2 = new DialogInterface.OnClickListener() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                jsResult.cancel();
                dialogInterface.dismiss();
                InAppWebViewChromeClient.this.dialogs.remove(dialogInterface);
            }
        };
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.Theme_AppCompat_Dialog_Alert);
        builder.setMessage(str);
        if (str3 != null && !str3.isEmpty()) {
            builder.setPositiveButton(str3, onClickListener);
        } else {
            builder.setPositiveButton(android.R.string.ok, onClickListener);
        }
        if (str4 != null && !str4.isEmpty()) {
            builder.setNegativeButton(str4, onClickListener2);
        } else {
            builder.setNegativeButton(android.R.string.cancel, onClickListener2);
        }
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.7
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                jsResult.cancel();
                dialogInterface.dismiss();
                InAppWebViewChromeClient.this.dialogs.remove(dialogInterface);
            }
        });
        AlertDialog create = builder.create();
        this.dialogs.put(create, jsResult);
        create.show();
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsPrompt(final WebView webView, String str, final String str2, final String str3, final JsPromptResult jsPromptResult) {
        InAppWebView inAppWebView = this.inAppWebView;
        if (inAppWebView == null || inAppWebView.channelDelegate == null) {
            return false;
        }
        this.inAppWebView.channelDelegate.onJsPrompt(str, str2, str3, null, new WebViewChannelDelegate.JsPromptCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.8
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public boolean nonNullSuccess(JsPromptResponse jsPromptResponse) {
                if (!jsPromptResponse.isHandledByClient()) {
                    return true;
                }
                Integer action = jsPromptResponse.getAction();
                if (Integer.valueOf(action != null ? action.intValue() : 1).intValue() == 0) {
                    jsPromptResult.confirm(jsPromptResponse.getValue());
                    return false;
                }
                jsPromptResult.cancel();
                return false;
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public void defaultBehaviour(JsPromptResponse jsPromptResponse) {
                String str4;
                String str5;
                String str6;
                String str7;
                String str8;
                if (jsPromptResponse != null) {
                    String message = jsPromptResponse.getMessage();
                    String defaultValue = jsPromptResponse.getDefaultValue();
                    String value = jsPromptResponse.getValue();
                    String confirmButtonTitle = jsPromptResponse.getConfirmButtonTitle();
                    str7 = jsPromptResponse.getCancelButtonTitle();
                    str4 = message;
                    str5 = defaultValue;
                    str6 = value;
                    str8 = confirmButtonTitle;
                } else {
                    str4 = null;
                    str5 = null;
                    str6 = null;
                    str7 = null;
                    str8 = null;
                }
                InAppWebViewChromeClient.this.createPromptDialog(webView, str2, str3, jsPromptResult, str4, str5, str6, str7, str8);
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
            public void error(String str4, String str5, Object obj) {
                StringBuilder sb = new StringBuilder();
                sb.append(str4);
                sb.append(", ");
                if (str5 == null) {
                    str5 = "";
                }
                sb.append(str5);
                Log.e(InAppWebViewChromeClient.LOG_TAG, sb.toString());
                jsPromptResult.cancel();
            }
        });
        return true;
    }

    public void createPromptDialog(WebView webView, String str, String str2, final JsPromptResult jsPromptResult, String str3, String str4, final String str5, String str6, String str7) {
        FrameLayout frameLayout = new FrameLayout(webView.getContext());
        final EditText editText = new EditText(webView.getContext());
        editText.setMaxLines(1);
        if (str4 != null && !str4.isEmpty()) {
            str2 = str4;
        }
        editText.setText(str2);
        editText.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        frameLayout.setPaddingRelative(45, 15, 45, 0);
        frameLayout.addView(editText);
        if (str3 != null && !str3.isEmpty()) {
            str = str3;
        }
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                String obj = editText.getText().toString();
                JsPromptResult jsPromptResult2 = jsPromptResult;
                String str8 = str5;
                if (str8 != null) {
                    obj = str8;
                }
                jsPromptResult2.confirm(obj);
                dialogInterface.dismiss();
                InAppWebViewChromeClient.this.dialogs.remove(dialogInterface);
            }
        };
        DialogInterface.OnClickListener onClickListener2 = new DialogInterface.OnClickListener() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.10
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                jsPromptResult.cancel();
                dialogInterface.dismiss();
                InAppWebViewChromeClient.this.dialogs.remove(dialogInterface);
            }
        };
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.Theme_AppCompat_Dialog_Alert);
        builder.setMessage(str);
        if (str7 != null && !str7.isEmpty()) {
            builder.setPositiveButton(str7, onClickListener);
        } else {
            builder.setPositiveButton(android.R.string.ok, onClickListener);
        }
        if (str6 != null && !str6.isEmpty()) {
            builder.setNegativeButton(str6, onClickListener2);
        } else {
            builder.setNegativeButton(android.R.string.cancel, onClickListener2);
        }
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.11
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                jsPromptResult.cancel();
                dialogInterface.dismiss();
                InAppWebViewChromeClient.this.dialogs.remove(dialogInterface);
            }
        });
        AlertDialog create = builder.create();
        create.setView(frameLayout);
        this.dialogs.put(create, jsPromptResult);
        create.show();
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsBeforeUnload(WebView webView, String str, final String str2, final JsResult jsResult) {
        InAppWebView inAppWebView = this.inAppWebView;
        if (inAppWebView == null || inAppWebView.channelDelegate == null) {
            return false;
        }
        this.inAppWebView.channelDelegate.onJsBeforeUnload(str, str2, new WebViewChannelDelegate.JsBeforeUnloadCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.12
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public boolean nonNullSuccess(JsBeforeUnloadResponse jsBeforeUnloadResponse) {
                if (!jsBeforeUnloadResponse.isHandledByClient()) {
                    return true;
                }
                Integer action = jsBeforeUnloadResponse.getAction();
                if (Integer.valueOf(action != null ? action.intValue() : 1).intValue() == 0) {
                    jsResult.confirm();
                    return false;
                }
                jsResult.cancel();
                return false;
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public void defaultBehaviour(JsBeforeUnloadResponse jsBeforeUnloadResponse) {
                String str3;
                String str4;
                String str5;
                if (jsBeforeUnloadResponse != null) {
                    String message = jsBeforeUnloadResponse.getMessage();
                    String confirmButtonTitle = jsBeforeUnloadResponse.getConfirmButtonTitle();
                    str5 = jsBeforeUnloadResponse.getCancelButtonTitle();
                    str3 = message;
                    str4 = confirmButtonTitle;
                } else {
                    str3 = null;
                    str4 = null;
                    str5 = null;
                }
                InAppWebViewChromeClient.this.createBeforeUnloadDialog(str2, jsResult, str3, str4, str5);
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
            public void error(String str3, String str4, Object obj) {
                StringBuilder sb = new StringBuilder();
                sb.append(str3);
                sb.append(", ");
                if (str4 == null) {
                    str4 = "";
                }
                sb.append(str4);
                Log.e(InAppWebViewChromeClient.LOG_TAG, sb.toString());
                jsResult.cancel();
            }
        });
        return true;
    }

    public void createBeforeUnloadDialog(String str, final JsResult jsResult, String str2, String str3, String str4) {
        if (str2 != null && !str2.isEmpty()) {
            str = str2;
        }
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                jsResult.confirm();
                dialogInterface.dismiss();
                InAppWebViewChromeClient.this.dialogs.remove(dialogInterface);
            }
        };
        DialogInterface.OnClickListener onClickListener2 = new DialogInterface.OnClickListener() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.14
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                jsResult.cancel();
                dialogInterface.dismiss();
                InAppWebViewChromeClient.this.dialogs.remove(dialogInterface);
            }
        };
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.Theme_AppCompat_Dialog_Alert);
        builder.setMessage(str);
        if (str3 != null && !str3.isEmpty()) {
            builder.setPositiveButton(str3, onClickListener);
        } else {
            builder.setPositiveButton(android.R.string.ok, onClickListener);
        }
        if (str4 != null && !str4.isEmpty()) {
            builder.setNegativeButton(str4, onClickListener2);
        } else {
            builder.setNegativeButton(android.R.string.cancel, onClickListener2);
        }
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.15
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                jsResult.cancel();
                dialogInterface.dismiss();
                InAppWebViewChromeClient.this.dialogs.remove(dialogInterface);
            }
        });
        AlertDialog create = builder.create();
        this.dialogs.put(create, jsResult);
        create.show();
    }

    @Override // android.webkit.WebChromeClient
    public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        final int i;
        String string;
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
        if (inAppWebViewFlutterPlugin == null || inAppWebViewFlutterPlugin.inAppWebViewManager == null) {
            i = 0;
        } else {
            this.plugin.inAppWebViewManager.windowAutoincrementId++;
            i = this.plugin.inAppWebViewManager.windowAutoincrementId;
        }
        WebView.HitTestResult hitTestResult = webView.getHitTestResult();
        String extra = hitTestResult.getExtra();
        if (hitTestResult.getType() == 8) {
            Message obtainMessage = webView.getHandler().obtainMessage();
            webView.requestFocusNodeHref(obtainMessage);
            Bundle data = obtainMessage.getData();
            if (data != null && (string = data.getString("url")) != null && !string.isEmpty()) {
                extra = string;
            }
        }
        CreateWindowAction createWindowAction = new CreateWindowAction(new URLRequest(extra, ShareTarget.METHOD_GET, null, null), true, z2, false, i, z);
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin2 = this.plugin;
        if (inAppWebViewFlutterPlugin2 != null && inAppWebViewFlutterPlugin2.inAppWebViewManager != null) {
            this.plugin.inAppWebViewManager.windowWebViewMessages.put(Integer.valueOf(i), message);
        }
        InAppWebView inAppWebView = this.inAppWebView;
        if (inAppWebView == null || inAppWebView.channelDelegate == null) {
            return false;
        }
        this.inAppWebView.channelDelegate.onCreateWindow(createWindowAction, new WebViewChannelDelegate.CreateWindowCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.16
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public boolean nonNullSuccess(Boolean bool) {
                return !bool.booleanValue();
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public void defaultBehaviour(Boolean bool) {
                if (InAppWebViewChromeClient.this.plugin == null || InAppWebViewChromeClient.this.plugin.inAppWebViewManager == null) {
                    return;
                }
                InAppWebViewChromeClient.this.plugin.inAppWebViewManager.windowWebViewMessages.remove(Integer.valueOf(i));
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
            public void error(String str, String str2, Object obj) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(", ");
                if (str2 == null) {
                    str2 = "";
                }
                sb.append(str2);
                Log.e(InAppWebViewChromeClient.LOG_TAG, sb.toString());
                defaultBehaviour((Boolean) null);
            }
        });
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public void onCloseWindow(WebView webView) {
        InAppWebView inAppWebView = this.inAppWebView;
        if (inAppWebView != null && inAppWebView.channelDelegate != null) {
            this.inAppWebView.channelDelegate.onCloseWindow();
        }
        super.onCloseWindow(webView);
    }

    @Override // android.webkit.WebChromeClient
    public void onGeolocationPermissionsShowPrompt(final String str, final GeolocationPermissions.Callback callback) {
        WebViewChannelDelegate.GeolocationPermissionsShowPromptCallback geolocationPermissionsShowPromptCallback = new WebViewChannelDelegate.GeolocationPermissionsShowPromptCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.17
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public boolean nonNullSuccess(GeolocationPermissionShowPromptResponse geolocationPermissionShowPromptResponse) {
                callback.invoke(geolocationPermissionShowPromptResponse.getOrigin(), geolocationPermissionShowPromptResponse.isAllow(), geolocationPermissionShowPromptResponse.isRetain());
                return false;
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public void defaultBehaviour(GeolocationPermissionShowPromptResponse geolocationPermissionShowPromptResponse) {
                callback.invoke(str, false, false);
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
            public void error(String str2, String str3, Object obj) {
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append(", ");
                if (str3 == null) {
                    str3 = "";
                }
                sb.append(str3);
                Log.e(InAppWebViewChromeClient.LOG_TAG, sb.toString());
                defaultBehaviour((GeolocationPermissionShowPromptResponse) null);
            }
        };
        InAppWebView inAppWebView = this.inAppWebView;
        if (inAppWebView != null && inAppWebView.channelDelegate != null) {
            this.inAppWebView.channelDelegate.onGeolocationPermissionsShowPrompt(str, geolocationPermissionsShowPromptCallback);
        } else {
            geolocationPermissionsShowPromptCallback.defaultBehaviour(null);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onGeolocationPermissionsHidePrompt() {
        InAppWebView inAppWebView = this.inAppWebView;
        if (inAppWebView == null || inAppWebView.channelDelegate == null) {
            return;
        }
        this.inAppWebView.channelDelegate.onGeolocationPermissionsHidePrompt();
    }

    @Override // android.webkit.WebChromeClient
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        InAppWebView inAppWebView = this.inAppWebView;
        if (inAppWebView != null && inAppWebView.channelDelegate != null) {
            this.inAppWebView.channelDelegate.onConsoleMessage(consoleMessage.message(), consoleMessage.messageLevel().ordinal());
        }
        return super.onConsoleMessage(consoleMessage);
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        InAppBrowserDelegate inAppBrowserDelegate = this.inAppBrowserDelegate;
        if (inAppBrowserDelegate != null) {
            inAppBrowserDelegate.didChangeProgress(i);
        }
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (inAppWebView.inAppWebViewClientCompat != null) {
            inAppWebView.inAppWebViewClientCompat.loadCustomJavaScriptOnPageStarted(webView);
        } else if (inAppWebView.inAppWebViewClient != null) {
            inAppWebView.inAppWebViewClient.loadCustomJavaScriptOnPageStarted(webView);
        }
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onProgressChanged(i);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedTitle(WebView webView, String str) {
        super.onReceivedTitle(webView, str);
        InAppBrowserDelegate inAppBrowserDelegate = this.inAppBrowserDelegate;
        if (inAppBrowserDelegate != null) {
            inAppBrowserDelegate.didChangeTitle(str);
        }
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onTitleChanged(str);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedIcon(WebView webView, Bitmap bitmap) {
        super.onReceivedIcon(webView, bitmap);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            Log.e(LOG_TAG, "", e);
        }
        bitmap.recycle();
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onReceivedIcon(byteArrayOutputStream.toByteArray());
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedTouchIconUrl(WebView webView, String str, boolean z) {
        super.onReceivedTouchIconUrl(webView, str, z);
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onReceivedTouchIconUrl(str, z);
        }
    }

    protected ViewGroup getRootView() {
        Activity activity = getActivity();
        if (activity == null) {
            return null;
        }
        return (ViewGroup) activity.findViewById(android.R.id.content);
    }

    protected void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
        startPickerIntent(valueCallback, str, null);
    }

    protected void openFileChooser(ValueCallback<Uri> valueCallback) {
        startPickerIntent(valueCallback, "", null);
    }

    protected void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
        startPickerIntent(valueCallback, str, str2);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        return startPickerIntent(valueCallback, fileChooserParams.getAcceptTypes(), fileChooserParams.getMode() == 1, fileChooserParams.isCaptureEnabled());
    }

    @Override // io.flutter.plugin.common.PluginRegistry.ActivityResultListener
    public boolean onActivityResult(int i, int i2, Intent intent) {
        Uri uri;
        if (this.filePathCallback == null && this.filePathCallbackLegacy == null) {
            return true;
        }
        if (i == 1) {
            Uri[] selectedFiles = i2 == -1 ? getSelectedFiles(intent, i2) : null;
            ValueCallback<Uri[]> valueCallback = this.filePathCallback;
            if (valueCallback != null) {
                valueCallback.onReceiveValue(selectedFiles);
            }
        } else if (i == 3) {
            if (i2 == -1) {
                uri = intent != null ? intent.getData() : getCapturedMediaFile();
            } else {
                uri = null;
            }
            ValueCallback<Uri> valueCallback2 = this.filePathCallbackLegacy;
            if (valueCallback2 != null) {
                valueCallback2.onReceiveValue(uri);
            }
        }
        this.filePathCallback = null;
        this.filePathCallbackLegacy = null;
        this.imageOutputFileUri = null;
        this.videoOutputFileUri = null;
        return true;
    }

    private Uri[] getSelectedFiles(Intent intent, int i) {
        if (intent != null && intent.getData() != null) {
            if (i != -1 || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return WebChromeClient.FileChooserParams.parseResult(i, intent);
        }
        if (intent != null && intent.getClipData() != null) {
            int itemCount = intent.getClipData().getItemCount();
            Uri[] uriArr = new Uri[itemCount];
            for (int i2 = 0; i2 < itemCount; i2++) {
                uriArr[i2] = intent.getClipData().getItemAt(i2).getUri();
            }
            return uriArr;
        }
        Uri capturedMediaFile = getCapturedMediaFile();
        if (capturedMediaFile != null) {
            return new Uri[]{capturedMediaFile};
        }
        return null;
    }

    private boolean isFileNotEmpty(Uri uri) {
        Activity activity = getActivity();
        if (activity == null) {
            return false;
        }
        try {
            AssetFileDescriptor openAssetFileDescriptor = activity.getContentResolver().openAssetFileDescriptor(uri, PDPageLabelRange.STYLE_ROMAN_LOWER);
            long length = openAssetFileDescriptor.getLength();
            openAssetFileDescriptor.close();
            return length > 0;
        } catch (IOException unused) {
            return false;
        }
    }

    private Uri getCapturedMediaFile() {
        Uri uri = this.imageOutputFileUri;
        if (uri != null && isFileNotEmpty(uri)) {
            return this.imageOutputFileUri;
        }
        Uri uri2 = this.videoOutputFileUri;
        if (uri2 == null || !isFileNotEmpty(uri2)) {
            return null;
        }
        return this.videoOutputFileUri;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void startPickerIntent(ValueCallback<Uri> valueCallback, String str, String str2) {
        Intent intent;
        Activity activity;
        this.filePathCallbackLegacy = valueCallback;
        boolean booleanValue = acceptsImages(str).booleanValue();
        boolean booleanValue2 = acceptsVideo(str).booleanValue();
        if (str2 != null && !needsCameraPermission()) {
            if (booleanValue) {
                intent = getPhotoIntent();
            } else if (booleanValue2) {
                intent = getVideoIntent();
            }
            if (intent == null) {
                intent = Intent.createChooser(getFileChooserIntent(str), "");
                ArrayList arrayList = new ArrayList();
                if (!needsCameraPermission()) {
                    if (booleanValue) {
                        arrayList.add(getPhotoIntent());
                    }
                    if (booleanValue2) {
                        arrayList.add(getVideoIntent());
                    }
                }
                intent.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
            }
            activity = getActivity();
            if (activity == null && intent.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivityForResult(intent, 3);
                return;
            } else {
                Log.d(LOG_TAG, "there is no Activity to handle this Intent");
            }
        }
        intent = null;
        if (intent == null) {
        }
        activity = getActivity();
        if (activity == null) {
        }
        Log.d(LOG_TAG, "there is no Activity to handle this Intent");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean startPickerIntent(ValueCallback<Uri[]> valueCallback, String[] strArr, boolean z, boolean z2) {
        Intent intent;
        Activity activity;
        this.filePathCallback = valueCallback;
        boolean booleanValue = acceptsImages(strArr).booleanValue();
        boolean booleanValue2 = acceptsVideo(strArr).booleanValue();
        if (z2 && !needsCameraPermission()) {
            if (booleanValue) {
                intent = getPhotoIntent();
            } else if (booleanValue2) {
                intent = getVideoIntent();
            }
            if (intent == null) {
                ArrayList arrayList = new ArrayList();
                if (!needsCameraPermission()) {
                    if (booleanValue) {
                        arrayList.add(getPhotoIntent());
                    }
                    if (booleanValue2) {
                        arrayList.add(getVideoIntent());
                    }
                }
                Intent fileChooserIntent = getFileChooserIntent(strArr, z);
                Intent intent2 = new Intent("android.intent.action.CHOOSER");
                intent2.putExtra("android.intent.extra.INTENT", fileChooserIntent);
                intent2.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
                intent = intent2;
            }
            activity = getActivity();
            if (activity == null && intent.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivityForResult(intent, 1);
            } else {
                Log.d(LOG_TAG, "there is no Activity to handle this Intent");
            }
            return true;
        }
        intent = null;
        if (intent == null) {
        }
        activity = getActivity();
        if (activity == null) {
        }
        Log.d(LOG_TAG, "there is no Activity to handle this Intent");
        return true;
    }

    protected boolean needsCameraPermission() {
        Activity activity = getActivity();
        if (activity == null) {
            return true;
        }
        try {
            if (Arrays.asList(activity.getPackageManager().getPackageInfo(activity.getApplicationContext().getPackageName(), 4096).requestedPermissions).contains("android.permission.CAMERA")) {
                if (ContextCompat.checkSelfPermission(activity, "android.permission.CAMERA") != 0) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return true;
        }
    }

    private Intent getPhotoIntent() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        Uri outputUri = getOutputUri("android.media.action.IMAGE_CAPTURE");
        this.imageOutputFileUri = outputUri;
        intent.putExtra("output", outputUri);
        return intent;
    }

    private Intent getVideoIntent() {
        Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
        Uri outputUri = getOutputUri("android.media.action.VIDEO_CAPTURE");
        this.videoOutputFileUri = outputUri;
        intent.putExtra("output", outputUri);
        return intent;
    }

    private Intent getFileChooserIntent(String str) {
        String str2 = str.isEmpty() ? "*/*" : str;
        if (str.matches("\\.\\w+")) {
            str2 = getMimeTypeFromExtension(str.replace(".", ""));
        }
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType(str2);
        return intent;
    }

    private Intent getFileChooserIntent(String[] strArr, boolean z) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        intent.putExtra("android.intent.extra.MIME_TYPES", getAcceptedMimeType(strArr));
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", z);
        return intent;
    }

    private Boolean acceptsAny(String[] strArr) {
        if (isArrayEmpty(strArr).booleanValue()) {
            return true;
        }
        for (String str : strArr) {
            if (str.equals("*/*")) {
                return true;
            }
        }
        return false;
    }

    private Boolean acceptsImages(String str) {
        if (str.matches("\\.\\w+")) {
            str = getMimeTypeFromExtension(str.replace(".", ""));
        }
        return Boolean.valueOf(str.isEmpty() || str.toLowerCase().contains("image"));
    }

    private Boolean acceptsImages(String[] strArr) {
        return Boolean.valueOf(acceptsAny(strArr).booleanValue() || arrayContainsString(getAcceptedMimeType(strArr), "image").booleanValue());
    }

    private Boolean acceptsVideo(String str) {
        if (str.matches("\\.\\w+")) {
            str = getMimeTypeFromExtension(str.replace(".", ""));
        }
        return Boolean.valueOf(str.isEmpty() || str.toLowerCase().contains("video"));
    }

    private Boolean acceptsVideo(String[] strArr) {
        return Boolean.valueOf(acceptsAny(strArr).booleanValue() || arrayContainsString(getAcceptedMimeType(strArr), "video").booleanValue());
    }

    private Boolean arrayContainsString(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str2 != null && str2.contains(str)) {
                return true;
            }
        }
        return false;
    }

    private String[] getAcceptedMimeType(String[] strArr) {
        if (isArrayEmpty(strArr).booleanValue()) {
            return new String[]{"*/*"};
        }
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (str.matches("\\.\\w+")) {
                strArr2[i] = getMimeTypeFromExtension(str.replace(".", ""));
            } else {
                strArr2[i] = str;
            }
        }
        return strArr2;
    }

    private String getMimeTypeFromExtension(String str) {
        if (str != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
        }
        return null;
    }

    private Uri getOutputUri(String str) {
        File file;
        try {
            file = getCapturedFile(str);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error occurred while creating the File", e);
            file = null;
        }
        if (file == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 23) {
            return Uri.fromFile(file);
        }
        Activity activity = getActivity();
        if (activity == null) {
            return null;
        }
        try {
            return FileProvider.getUriForFile(activity.getApplicationContext(), activity.getApplicationContext().getPackageName() + "." + InAppWebViewFileProvider.fileProviderAuthorityExtension, file);
        } catch (Exception e2) {
            Log.e(LOG_TAG, "", e2);
            return null;
        }
    }

    private File getCapturedFile(String str) throws IOException {
        String str2;
        String str3;
        String str4 = "";
        if (str.equals("android.media.action.IMAGE_CAPTURE")) {
            str4 = Environment.DIRECTORY_PICTURES;
            str2 = "image";
            str3 = ".jpg";
        } else if (str.equals("android.media.action.VIDEO_CAPTURE")) {
            str4 = Environment.DIRECTORY_MOVIES;
            str2 = "video";
            str3 = ".mp4";
        } else {
            str2 = "";
            str3 = str2;
        }
        if (Build.VERSION.SDK_INT < 23) {
            return new File(Environment.getExternalStoragePublicDirectory(str4), String.format("%s-%d%s", str2, Long.valueOf(System.currentTimeMillis()), str3));
        }
        Activity activity = getActivity();
        if (activity == null) {
            return null;
        }
        return File.createTempFile(str2, str3, activity.getApplicationContext().getExternalFilesDir(null));
    }

    private Boolean isArrayEmpty(String[] strArr) {
        boolean z = false;
        if (strArr.length == 0 || (strArr.length == 1 && strArr[0].length() == 0)) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    @Override // android.webkit.WebChromeClient
    public void onPermissionRequest(final PermissionRequest permissionRequest) {
        if (Build.VERSION.SDK_INT >= 21) {
            WebViewChannelDelegate.PermissionRequestCallback permissionRequestCallback = new WebViewChannelDelegate.PermissionRequestCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewChromeClient.18
                @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
                public boolean nonNullSuccess(PermissionResponse permissionResponse) {
                    Integer action = permissionResponse.getAction();
                    if (action == null) {
                        return true;
                    }
                    if (action.intValue() == 1) {
                        permissionRequest.grant((String[]) permissionResponse.getResources().toArray(new String[permissionResponse.getResources().size()]));
                        return false;
                    }
                    permissionRequest.deny();
                    return false;
                }

                @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
                public void defaultBehaviour(PermissionResponse permissionResponse) {
                    permissionRequest.deny();
                }

                @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
                public void error(String str, String str2, Object obj) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(", ");
                    if (str2 == null) {
                        str2 = "";
                    }
                    sb.append(str2);
                    Log.e(InAppWebViewChromeClient.LOG_TAG, sb.toString());
                    defaultBehaviour((PermissionResponse) null);
                }
            };
            InAppWebView inAppWebView = this.inAppWebView;
            if (inAppWebView != null && inAppWebView.channelDelegate != null) {
                this.inAppWebView.channelDelegate.onPermissionRequest(permissionRequest.getOrigin().toString(), Arrays.asList(permissionRequest.getResources()), null, permissionRequestCallback);
            } else {
                permissionRequestCallback.defaultBehaviour(null);
            }
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onRequestFocus(WebView webView) {
        InAppWebView inAppWebView = this.inAppWebView;
        if (inAppWebView == null || inAppWebView.channelDelegate == null) {
            return;
        }
        this.inAppWebView.channelDelegate.onRequestFocus();
    }

    @Override // android.webkit.WebChromeClient
    public void onPermissionRequestCanceled(PermissionRequest permissionRequest) {
        InAppWebView inAppWebView;
        if (Build.VERSION.SDK_INT < 21 || (inAppWebView = this.inAppWebView) == null || inAppWebView.channelDelegate == null) {
            return;
        }
        this.inAppWebView.channelDelegate.onPermissionRequestCanceled(permissionRequest.getOrigin().toString(), Arrays.asList(permissionRequest.getResources()));
    }

    private Activity getActivity() {
        InAppBrowserDelegate inAppBrowserDelegate = this.inAppBrowserDelegate;
        if (inAppBrowserDelegate != null) {
            return inAppBrowserDelegate.getActivity();
        }
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
        if (inAppWebViewFlutterPlugin != null) {
            return inAppWebViewFlutterPlugin.activity;
        }
        return null;
    }

    public void dispose() {
        for (Map.Entry<DialogInterface, JsResult> entry : this.dialogs.entrySet()) {
            entry.getValue().cancel();
            entry.getKey().dismiss();
        }
        this.dialogs.clear();
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
        if (inAppWebViewFlutterPlugin != null && inAppWebViewFlutterPlugin.activityPluginBinding != null) {
            this.plugin.activityPluginBinding.removeActivityResultListener(this);
        }
        InAppBrowserDelegate inAppBrowserDelegate = this.inAppBrowserDelegate;
        if (inAppBrowserDelegate != null) {
            inAppBrowserDelegate.getActivityResultListeners().clear();
            this.inAppBrowserDelegate = null;
        }
        this.filePathCallbackLegacy = null;
        this.filePathCallback = null;
        this.videoOutputFileUri = null;
        this.imageOutputFileUri = null;
        this.inAppWebView = null;
        this.plugin = null;
    }
}
