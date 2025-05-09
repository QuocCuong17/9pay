package co.hyperverge.hyperkyc_flutter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import co.hyperverge.hyperkyc.HyperKyc;
import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
import co.hyperverge.hyperkyc.data.models.result.HyperKycResult;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class HyperkycFlutterPlugin implements FlutterPlugin, ActivityAware, PluginRegistry.ActivityResultListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "co.hyperverge.hyperkyc_flutter.HyperkycFlutterPlugin";
    protected Activity activity;
    protected Context context;
    private MethodChannel hyperKycChannel;
    private HyperKycConfig hyperKycConfig;
    private MethodChannel.Result pendingResult;
    private String transactionId;
    private final FLHyperKyc flHyperKyc = new FLHyperKyc();
    private final HyperKyc.Contract contract = new HyperKyc.Contract();
    private final MethodChannel.MethodCallHandler hyperKycMethodCallHandler = new MethodChannel.MethodCallHandler() { // from class: co.hyperverge.hyperkyc_flutter.HyperkycFlutterPlugin$$ExternalSyntheticLambda0
        @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
        public final void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
            HyperkycFlutterPlugin.this.m445xfb95836b(methodCall, result);
        }
    };

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
    }

    /* renamed from: lambda$new$0$co-hyperverge-hyperkyc_flutter-HyperkycFlutterPlugin, reason: not valid java name */
    public /* synthetic */ void m445xfb95836b(MethodCall methodCall, MethodChannel.Result result) {
        this.pendingResult = result;
        if (methodCall.method.equalsIgnoreCase("prefetch")) {
            this.flHyperKyc.prefetch(this.context, (HashMap) methodCall.arguments);
            result.success(1);
        } else {
            if (methodCall.method.equalsIgnoreCase("createUniqueId")) {
                result.success(this.flHyperKyc.createUniqueId());
                return;
            }
            if (methodCall.method.equalsIgnoreCase("launch")) {
                HashMap hashMap = (HashMap) methodCall.argument(HyperKycConfig.ARG_KEY);
                if (hashMap.containsKey("transactionId")) {
                    this.transactionId = (String) hashMap.get("transactionId");
                }
                HyperKycConfig hyperKycConfigFromMap = this.flHyperKyc.getHyperKycConfigFromMap(hashMap);
                this.hyperKycConfig = hyperKycConfigFromMap;
                this.activity.startActivityForResult(this.contract.createIntent((Context) this.activity, hyperKycConfigFromMap), 1234);
                return;
            }
            result.notImplemented();
        }
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.context = flutterPluginBinding.getApplicationContext();
        MethodChannel methodChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "hyperKyc");
        this.hyperKycChannel = methodChannel;
        methodChannel.setMethodCallHandler(this.hyperKycMethodCallHandler);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.hyperKycChannel.setMethodCallHandler(null);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        this.activity = activityPluginBinding.getActivity();
        activityPluginBinding.addActivityResultListener(this);
    }

    @Override // io.flutter.plugin.common.PluginRegistry.ActivityResultListener
    public boolean onActivityResult(int i, int i2, Intent intent) {
        if (i != 1234) {
            return false;
        }
        if (intent == null) {
            return true;
        }
        HyperKycResult parseResult = this.contract.parseResult(i2, intent);
        if (this.transactionId == null || !parseResult.getTransactionId().equalsIgnoreCase(this.transactionId)) {
            return true;
        }
        handleHyperKycResultData(parseResult);
        return true;
    }

    private void handleHyperKycResultData(HyperKycResult hyperKycResult) {
        this.pendingResult.success(processHyperKycResult(hyperKycResult));
    }

    private Map<String, Object> processHyperKycResult(HyperKycResult hyperKycResult) {
        HashMap hashMap = new HashMap();
        String status = hyperKycResult.getStatus();
        String transactionId = hyperKycResult.getTransactionId();
        Map<String, String> details = hyperKycResult.getDetails();
        int intValue = hyperKycResult.getErrorCode() != null ? hyperKycResult.getErrorCode().intValue() : -1000;
        String errorMessage = hyperKycResult.getErrorMessage() != null ? hyperKycResult.getErrorMessage() : null;
        String latestModule = hyperKycResult.getLatestModule() != null ? hyperKycResult.getLatestModule() : null;
        String rawDataJsonString = hyperKycResult.getRawDataJsonString() != null ? hyperKycResult.getRawDataJsonString() : null;
        hashMap.put("status", status);
        hashMap.put("transactionId", transactionId);
        hashMap.put("details", details);
        hashMap.put("errorCode", Integer.valueOf(intValue));
        hashMap.put("errorMessage", errorMessage);
        hashMap.put("latestModule", latestModule);
        hashMap.put("rawDataJsonString", rawDataJsonString);
        return hashMap;
    }
}
