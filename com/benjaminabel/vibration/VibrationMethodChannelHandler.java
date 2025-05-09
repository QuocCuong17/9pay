package com.benjaminabel.vibration;

import android.os.Build;
import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.List;

/* loaded from: classes2.dex */
class VibrationMethodChannelHandler implements MethodChannel.MethodCallHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Vibration vibration;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VibrationMethodChannelHandler(Vibration vibration) {
        this.vibration = vibration;
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1367724422:
                if (str.equals(Constant.PARAM_CANCEL)) {
                    c = 0;
                    break;
                }
                break;
            case -314771757:
                if (str.equals("hasVibrator")) {
                    c = 1;
                    break;
                }
                break;
            case 86129172:
                if (str.equals("hasAmplitudeControl")) {
                    c = 2;
                    break;
                }
                break;
            case 451310959:
                if (str.equals("vibrate")) {
                    c = 3;
                    break;
                }
                break;
            case 890723587:
                if (str.equals("hasCustomVibrationsSupport")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.vibration.getVibrator().cancel();
                result.success(null);
                return;
            case 1:
                result.success(Boolean.valueOf(this.vibration.getVibrator().hasVibrator()));
                return;
            case 2:
                if (Build.VERSION.SDK_INT >= 26) {
                    result.success(Boolean.valueOf(this.vibration.getVibrator().hasAmplitudeControl()));
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 3:
                int intValue = ((Integer) methodCall.argument("duration")).intValue();
                List<Integer> list = (List) methodCall.argument("pattern");
                int intValue2 = ((Integer) methodCall.argument("repeat")).intValue();
                List<Integer> list2 = (List) methodCall.argument("intensities");
                int intValue3 = ((Integer) methodCall.argument("amplitude")).intValue();
                if (list.size() > 0 && list2.size() > 0) {
                    this.vibration.vibrate(list, intValue2, list2);
                } else if (list.size() > 0) {
                    this.vibration.vibrate(list, intValue2);
                } else {
                    this.vibration.vibrate(intValue, intValue3);
                }
                result.success(null);
                return;
            case 4:
                result.success(true);
                return;
            default:
                result.notImplemented();
                return;
        }
    }
}
