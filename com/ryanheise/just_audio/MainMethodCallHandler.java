package com.ryanheise.just_audio;

import android.content.Context;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.sentry.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class MainMethodCallHandler implements MethodChannel.MethodCallHandler {
    private final Context applicationContext;
    private final BinaryMessenger messenger;
    private final Map<String, AudioPlayer> players = new HashMap();

    public MainMethodCallHandler(Context context, BinaryMessenger binaryMessenger) {
        this.applicationContext = context;
        this.messenger = binaryMessenger;
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 3237136:
                if (str.equals(Session.JsonKeys.INIT)) {
                    c = 0;
                    break;
                }
                break;
            case 1999985120:
                if (str.equals("disposePlayer")) {
                    c = 1;
                    break;
                }
                break;
            case 2146443344:
                if (str.equals("disposeAllPlayers")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                String str2 = (String) methodCall.argument("id");
                if (this.players.containsKey(str2)) {
                    result.error("Platform player " + str2 + " already exists", null, null);
                    return;
                }
                this.players.put(str2, new AudioPlayer(this.applicationContext, this.messenger, str2, (Map) methodCall.argument("audioLoadConfiguration"), (List) methodCall.argument("androidAudioEffects"), (Boolean) methodCall.argument("androidOffloadSchedulingEnabled")));
                result.success(null);
                return;
            case 1:
                String str3 = (String) methodCall.argument("id");
                AudioPlayer audioPlayer = this.players.get(str3);
                if (audioPlayer != null) {
                    audioPlayer.dispose();
                    this.players.remove(str3);
                }
                result.success(new HashMap());
                return;
            case 2:
                dispose();
                result.success(new HashMap());
                return;
            default:
                result.notImplemented();
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispose() {
        Iterator it = new ArrayList(this.players.values()).iterator();
        while (it.hasNext()) {
            ((AudioPlayer) it.next()).dispose();
        }
        this.players.clear();
    }
}
