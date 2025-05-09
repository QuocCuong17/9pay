package io.flutter.plugins.nfcmanager;

import android.app.Activity;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.nfc.tech.TagTechnology;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.tekartik.sqflite.Constant;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NfcManagerPlugin.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fH\u0002J\u0018\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010 \u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010!\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\"\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010#\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010$\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010%\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010&\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010'\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010(\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010)\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010*\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010+\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010,\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010-\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010.\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010/\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u00100\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u00101\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u00102\u001a\u00020\u00122\u0006\u00103\u001a\u000204H\u0016J\u0010\u00105\u001a\u00020\u00122\u0006\u00103\u001a\u000206H\u0016J\b\u00107\u001a\u00020\u0012H\u0016J\b\u00108\u001a\u00020\u0012H\u0016J\u0010\u00109\u001a\u00020\u00122\u0006\u00103\u001a\u000206H\u0016J\u0018\u0010:\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010;\u001a\u00020\u00122\u0006\u00103\u001a\u000204H\u0016JL\u0010<\u001a\u00020\u0012\"\b\b\u0000\u0010=*\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0014\u0010>\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u0001H=0?2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u0002H=\u0012\u0004\u0012\u00020\u00120?H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082.¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lio/flutter/plugins/nfcmanager/NfcManagerPlugin;", "Lio/flutter/embedding/engine/plugins/FlutterPlugin;", "Lio/flutter/plugin/common/MethodChannel$MethodCallHandler;", "Lio/flutter/embedding/engine/plugins/activity/ActivityAware;", "()V", "activity", "Landroid/app/Activity;", "adapter", "Landroid/nfc/NfcAdapter;", "channel", "Lio/flutter/plugin/common/MethodChannel;", "connectedTech", "Landroid/nfc/tech/TagTechnology;", "tags", "", "", "Landroid/nfc/Tag;", "forceConnect", "", "tech", "handleIsoDepTransceive", NotificationCompat.CATEGORY_CALL, "Lio/flutter/plugin/common/MethodCall;", Constant.PARAM_RESULT, "Lio/flutter/plugin/common/MethodChannel$Result;", "handleMifareClassicAuthenticateSectorWithKeyA", "handleMifareClassicAuthenticateSectorWithKeyB", "handleMifareClassicDecrement", "handleMifareClassicIncrement", "handleMifareClassicReadBlock", "handleMifareClassicRestore", "handleMifareClassicTransceive", "handleMifareClassicTransfer", "handleMifareClassicWriteBlock", "handleMifareUltralightReadPages", "handleMifareUltralightTransceive", "handleMifareUltralightWritePage", "handleNdefFormatableFormat", "handleNdefFormatableFormatReadOnly", "handleNdefRead", "handleNdefWrite", "handleNdefWriteLock", "handleNfcATransceive", "handleNfcBTransceive", "handleNfcDisposeTag", "handleNfcFTransceive", "handleNfcIsAvailable", "handleNfcStartSession", "handleNfcStopSession", "handleNfcVTransceive", "onAttachedToActivity", "binding", "Lio/flutter/embedding/engine/plugins/activity/ActivityPluginBinding;", "onAttachedToEngine", "Lio/flutter/embedding/engine/plugins/FlutterPlugin$FlutterPluginBinding;", "onDetachedFromActivity", "onDetachedFromActivityForConfigChanges", "onDetachedFromEngine", "onMethodCall", "onReattachedToActivityForConfigChanges", "tagHandler", "T", "getMethod", "Lkotlin/Function1;", "callback", "nfc_manager_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class NfcManagerPlugin implements FlutterPlugin, MethodChannel.MethodCallHandler, ActivityAware {
    private Activity activity;
    private NfcAdapter adapter;
    private MethodChannel channel;
    private TagTechnology connectedTech;
    private Map<String, Tag> tags;

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        MethodChannel methodChannel = new MethodChannel(binding.getBinaryMessenger(), "plugins.flutter.io/nfc_manager");
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
        this.adapter = NfcAdapter.getDefaultAdapter(binding.getApplicationContext());
        this.tags = new LinkedHashMap();
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        MethodChannel methodChannel = this.channel;
        if (methodChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("channel");
            methodChannel = null;
        }
        methodChannel.setMethodCallHandler(null);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        Activity activity = binding.getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "binding.activity");
        this.activity = activity;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        Activity activity = binding.getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "binding.activity");
        this.activity = activity;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0012. Please report as an issue. */
    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(result, "result");
        String str = call.method;
        if (str != null) {
            switch (str.hashCode()) {
                case -2089796738:
                    if (str.equals("MifareClassic#authenticateSectorWithKeyA")) {
                        handleMifareClassicAuthenticateSectorWithKeyA(call, result);
                        return;
                    }
                    break;
                case -2089796737:
                    if (str.equals("MifareClassic#authenticateSectorWithKeyB")) {
                        handleMifareClassicAuthenticateSectorWithKeyB(call, result);
                        return;
                    }
                    break;
                case -2078034405:
                    if (str.equals("NfcA#transceive")) {
                        handleNfcATransceive(call, result);
                        return;
                    }
                    break;
                case -1948951686:
                    if (str.equals("NfcB#transceive")) {
                        handleNfcBTransceive(call, result);
                        return;
                    }
                    break;
                case -1946397057:
                    if (str.equals("MifareUltralight#readPages")) {
                        handleMifareUltralightReadPages(call, result);
                        return;
                    }
                    break;
                case -1638655208:
                    if (str.equals("MifareClassic#decrement")) {
                        handleMifareClassicDecrement(call, result);
                        return;
                    }
                    break;
                case -1514865069:
                    if (str.equals("MifareClassic#restore")) {
                        handleMifareClassicRestore(call, result);
                        return;
                    }
                    break;
                case -1432620810:
                    if (str.equals("NfcF#transceive")) {
                        handleNfcFTransceive(call, result);
                        return;
                    }
                    break;
                case -1423304365:
                    if (str.equals("Ndef#write")) {
                        handleNdefWrite(call, result);
                        return;
                    }
                    break;
                case -1412099575:
                    if (str.equals("MifareClassic#writeBlock")) {
                        handleMifareClassicWriteBlock(call, result);
                        return;
                    }
                    break;
                case -1334951171:
                    if (str.equals("MifareUltralight#transceive")) {
                        handleMifareUltralightTransceive(call, result);
                        return;
                    }
                    break;
                case -1248721849:
                    if (str.equals("IsoDep#transceive")) {
                        handleIsoDepTransceive(call, result);
                        return;
                    }
                    break;
                case -1090251980:
                    if (str.equals("NdefFormatable#formatReadOnly")) {
                        handleNdefFormatableFormatReadOnly(call, result);
                        return;
                    }
                    break;
                case -1012096569:
                    if (str.equals("Nfc#isAvailable")) {
                        handleNfcIsAvailable(call, result);
                        return;
                    }
                    break;
                case -756399812:
                    if (str.equals("Nfc#stopSession")) {
                        handleNfcStopSession(call, result);
                        return;
                    }
                    break;
                case -354824244:
                    if (str.equals("Nfc#startSession")) {
                        handleNfcStartSession(call, result);
                        return;
                    }
                    break;
                case -28318989:
                    if (str.equals("Nfc#disposeTag")) {
                        handleNfcDisposeTag(call, result);
                        return;
                    }
                    break;
                case 393891506:
                    if (str.equals("NdefFormatable#format")) {
                        handleNdefFormatableFormat(call, result);
                        return;
                    }
                    break;
                case 632702694:
                    if (str.equals("NfcV#transceive")) {
                        handleNfcVTransceive(call, result);
                        return;
                    }
                    break;
                case 714398196:
                    if (str.equals("MifareClassic#increment")) {
                        handleMifareClassicIncrement(call, result);
                        return;
                    }
                    break;
                case 913594140:
                    if (str.equals("MifareClassic#readBlock")) {
                        handleMifareClassicReadBlock(call, result);
                        return;
                    }
                    break;
                case 1339398562:
                    if (str.equals("Ndef#read")) {
                        handleNdefRead(call, result);
                        return;
                    }
                    break;
                case 1798009118:
                    if (str.equals("Ndef#writeLock")) {
                        handleNdefWriteLock(call, result);
                        return;
                    }
                    break;
                case 1815843241:
                    if (str.equals("MifareClassic#transceive")) {
                        handleMifareClassicTransceive(call, result);
                        return;
                    }
                    break;
                case 1901331654:
                    if (str.equals("MifareClassic#transfer")) {
                        handleMifareClassicTransfer(call, result);
                        return;
                    }
                    break;
                case 2069973439:
                    if (str.equals("MifareUltralight#writePage")) {
                        handleMifareUltralightWritePage(call, result);
                        return;
                    }
                    break;
            }
        }
        result.notImplemented();
    }

    private final void handleNfcIsAvailable(MethodCall call, MethodChannel.Result result) {
        NfcAdapter nfcAdapter = this.adapter;
        result.success(Boolean.valueOf(nfcAdapter != null && nfcAdapter.isEnabled()));
    }

    private final void handleNfcStartSession(MethodCall call, MethodChannel.Result result) {
        if (Build.VERSION.SDK_INT < 19) {
            result.error("unavailable", "Requires API level 19.", null);
            return;
        }
        NfcAdapter nfcAdapter = this.adapter;
        if (nfcAdapter == null) {
            result.error("unavailable", "NFC is not available for device.", null);
            return;
        }
        Activity activity = this.activity;
        if (activity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activity");
            activity = null;
        }
        NfcAdapter.ReaderCallback readerCallback = new NfcAdapter.ReaderCallback() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$$ExternalSyntheticLambda0
            @Override // android.nfc.NfcAdapter.ReaderCallback
            public final void onTagDiscovered(Tag tag) {
                NfcManagerPlugin.handleNfcStartSession$lambda$3(NfcManagerPlugin.this, tag);
            }
        };
        Object argument = call.argument("pollingOptions");
        Intrinsics.checkNotNull(argument);
        nfcAdapter.enableReaderMode(activity, readerCallback, TranslatorKt.getFlags((List) argument), null);
        result.success(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleNfcStartSession$lambda$3(final NfcManagerPlugin this$0, final Tag it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        final String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
        Map<String, Tag> map = this$0.tags;
        Activity activity = null;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tags");
            map = null;
        }
        Intrinsics.checkNotNullExpressionValue(it, "it");
        map.put(uuid, it);
        Activity activity2 = this$0.activity;
        if (activity2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activity");
        } else {
            activity = activity2;
        }
        activity.runOnUiThread(new Runnable() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                NfcManagerPlugin.handleNfcStartSession$lambda$3$lambda$2(NfcManagerPlugin.this, it, uuid);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleNfcStartSession$lambda$3$lambda$2(NfcManagerPlugin this$0, Tag it, String handle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(handle, "$handle");
        MethodChannel methodChannel = this$0.channel;
        if (methodChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("channel");
            methodChannel = null;
        }
        Intrinsics.checkNotNullExpressionValue(it, "it");
        Map mutableMap = MapsKt.toMutableMap(TranslatorKt.getTagMap(it));
        mutableMap.put("handle", handle);
        Unit unit = Unit.INSTANCE;
        methodChannel.invokeMethod("onDiscovered", mutableMap);
    }

    private final void handleNfcStopSession(MethodCall call, MethodChannel.Result result) {
        if (Build.VERSION.SDK_INT < 19) {
            result.error("unavailable", "Requires API level 19.", null);
            return;
        }
        NfcAdapter nfcAdapter = this.adapter;
        if (nfcAdapter == null) {
            result.error("unavailable", "NFC is not available for device.", null);
            return;
        }
        Activity activity = this.activity;
        if (activity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activity");
            activity = null;
        }
        nfcAdapter.disableReaderMode(activity);
        result.success(null);
    }

    private final void handleNfcDisposeTag(MethodCall call, MethodChannel.Result result) {
        Map<String, Tag> map = this.tags;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tags");
            map = null;
        }
        Object argument = call.argument("handle");
        Intrinsics.checkNotNull(argument);
        Tag remove = map.remove(argument);
        if (remove == null) {
            result.success(null);
            return;
        }
        TagTechnology tagTechnology = this.connectedTech;
        if (tagTechnology == null) {
            result.success(null);
            return;
        }
        if (Intrinsics.areEqual(tagTechnology.getTag(), remove) && tagTechnology.isConnected()) {
            try {
                tagTechnology.close();
            } catch (IOException unused) {
            }
        }
        this.connectedTech = null;
        result.success(null);
    }

    private final void handleNdefRead(MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, Ndef>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNdefRead$1
            @Override // kotlin.jvm.functions.Function1
            public final Ndef invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Ndef.get(it);
            }
        }, new Function1<Ndef, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNdefRead$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Ndef ndef) {
                invoke2(ndef);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Ndef it) {
                Intrinsics.checkNotNullParameter(it, "it");
                NdefMessage ndefMessage = it.getNdefMessage();
                MethodChannel.Result.this.success(ndefMessage == null ? null : TranslatorKt.getNdefMessageMap(ndefMessage));
            }
        });
    }

    private final void handleNdefWrite(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, Ndef>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNdefWrite$1
            @Override // kotlin.jvm.functions.Function1
            public final Ndef invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Ndef.get(it);
            }
        }, new Function1<Ndef, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNdefWrite$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Ndef ndef) {
                invoke2(ndef);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Ndef it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("message");
                Intrinsics.checkNotNull(argument);
                it.writeNdefMessage(TranslatorKt.getNdefMessage((Map) argument));
                result.success(null);
            }
        });
    }

    private final void handleNdefWriteLock(MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, Ndef>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNdefWriteLock$1
            @Override // kotlin.jvm.functions.Function1
            public final Ndef invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Ndef.get(it);
            }
        }, new Function1<Ndef, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNdefWriteLock$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Ndef ndef) {
                invoke2(ndef);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Ndef it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.makeReadOnly();
                MethodChannel.Result.this.success(null);
            }
        });
    }

    private final void handleNfcATransceive(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, NfcA>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNfcATransceive$1
            @Override // kotlin.jvm.functions.Function1
            public final NfcA invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return NfcA.get(it);
            }
        }, new Function1<NfcA, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNfcATransceive$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(NfcA nfcA) {
                invoke2(nfcA);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(NfcA it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("data");
                Intrinsics.checkNotNull(argument);
                result.success(it.transceive((byte[]) argument));
            }
        });
    }

    private final void handleNfcBTransceive(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, NfcB>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNfcBTransceive$1
            @Override // kotlin.jvm.functions.Function1
            public final NfcB invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return NfcB.get(it);
            }
        }, new Function1<NfcB, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNfcBTransceive$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(NfcB nfcB) {
                invoke2(nfcB);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(NfcB it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("data");
                Intrinsics.checkNotNull(argument);
                result.success(it.transceive((byte[]) argument));
            }
        });
    }

    private final void handleNfcFTransceive(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, NfcF>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNfcFTransceive$1
            @Override // kotlin.jvm.functions.Function1
            public final NfcF invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return NfcF.get(it);
            }
        }, new Function1<NfcF, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNfcFTransceive$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(NfcF nfcF) {
                invoke2(nfcF);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(NfcF it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("data");
                Intrinsics.checkNotNull(argument);
                result.success(it.transceive((byte[]) argument));
            }
        });
    }

    private final void handleNfcVTransceive(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, NfcV>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNfcVTransceive$1
            @Override // kotlin.jvm.functions.Function1
            public final NfcV invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return NfcV.get(it);
            }
        }, new Function1<NfcV, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNfcVTransceive$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(NfcV nfcV) {
                invoke2(nfcV);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(NfcV it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("data");
                Intrinsics.checkNotNull(argument);
                result.success(it.transceive((byte[]) argument));
            }
        });
    }

    private final void handleIsoDepTransceive(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, IsoDep>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleIsoDepTransceive$1
            @Override // kotlin.jvm.functions.Function1
            public final IsoDep invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return IsoDep.get(it);
            }
        }, new Function1<IsoDep, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleIsoDepTransceive$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(IsoDep isoDep) {
                invoke2(isoDep);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IsoDep it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("data");
                Intrinsics.checkNotNull(argument);
                result.success(it.transceive((byte[]) argument));
            }
        });
    }

    private final void handleMifareClassicAuthenticateSectorWithKeyA(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, MifareClassic>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicAuthenticateSectorWithKeyA$1
            @Override // kotlin.jvm.functions.Function1
            public final MifareClassic invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return MifareClassic.get(it);
            }
        }, new Function1<MifareClassic, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicAuthenticateSectorWithKeyA$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MifareClassic mifareClassic) {
                invoke2(mifareClassic);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MifareClassic it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("sectorIndex");
                Intrinsics.checkNotNull(argument);
                int intValue = ((Number) argument).intValue();
                Object argument2 = MethodCall.this.argument("key");
                Intrinsics.checkNotNull(argument2);
                result.success(Boolean.valueOf(it.authenticateSectorWithKeyA(intValue, (byte[]) argument2)));
            }
        });
    }

    private final void handleMifareClassicAuthenticateSectorWithKeyB(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, MifareClassic>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicAuthenticateSectorWithKeyB$1
            @Override // kotlin.jvm.functions.Function1
            public final MifareClassic invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return MifareClassic.get(it);
            }
        }, new Function1<MifareClassic, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicAuthenticateSectorWithKeyB$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MifareClassic mifareClassic) {
                invoke2(mifareClassic);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MifareClassic it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("sectorIndex");
                Intrinsics.checkNotNull(argument);
                int intValue = ((Number) argument).intValue();
                Object argument2 = MethodCall.this.argument("key");
                Intrinsics.checkNotNull(argument2);
                result.success(Boolean.valueOf(it.authenticateSectorWithKeyB(intValue, (byte[]) argument2)));
            }
        });
    }

    private final void handleMifareClassicIncrement(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, MifareClassic>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicIncrement$1
            @Override // kotlin.jvm.functions.Function1
            public final MifareClassic invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return MifareClassic.get(it);
            }
        }, new Function1<MifareClassic, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicIncrement$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MifareClassic mifareClassic) {
                invoke2(mifareClassic);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MifareClassic it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("blockIndex");
                Intrinsics.checkNotNull(argument);
                int intValue = ((Number) argument).intValue();
                Object argument2 = MethodCall.this.argument("value");
                Intrinsics.checkNotNull(argument2);
                it.increment(intValue, ((Number) argument2).intValue());
                result.success(null);
            }
        });
    }

    private final void handleMifareClassicDecrement(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, MifareClassic>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicDecrement$1
            @Override // kotlin.jvm.functions.Function1
            public final MifareClassic invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return MifareClassic.get(it);
            }
        }, new Function1<MifareClassic, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicDecrement$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MifareClassic mifareClassic) {
                invoke2(mifareClassic);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MifareClassic it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("blockIndex");
                Intrinsics.checkNotNull(argument);
                int intValue = ((Number) argument).intValue();
                Object argument2 = MethodCall.this.argument("value");
                Intrinsics.checkNotNull(argument2);
                it.decrement(intValue, ((Number) argument2).intValue());
                result.success(null);
            }
        });
    }

    private final void handleMifareClassicReadBlock(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, MifareClassic>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicReadBlock$1
            @Override // kotlin.jvm.functions.Function1
            public final MifareClassic invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return MifareClassic.get(it);
            }
        }, new Function1<MifareClassic, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicReadBlock$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MifareClassic mifareClassic) {
                invoke2(mifareClassic);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MifareClassic it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("blockIndex");
                Intrinsics.checkNotNull(argument);
                result.success(it.readBlock(((Number) argument).intValue()));
            }
        });
    }

    private final void handleMifareClassicWriteBlock(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, MifareClassic>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicWriteBlock$1
            @Override // kotlin.jvm.functions.Function1
            public final MifareClassic invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return MifareClassic.get(it);
            }
        }, new Function1<MifareClassic, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicWriteBlock$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MifareClassic mifareClassic) {
                invoke2(mifareClassic);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MifareClassic it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("blockIndex");
                Intrinsics.checkNotNull(argument);
                int intValue = ((Number) argument).intValue();
                Object argument2 = MethodCall.this.argument("data");
                Intrinsics.checkNotNull(argument2);
                it.writeBlock(intValue, (byte[]) argument2);
                result.success(null);
            }
        });
    }

    private final void handleMifareClassicRestore(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, MifareClassic>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicRestore$1
            @Override // kotlin.jvm.functions.Function1
            public final MifareClassic invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return MifareClassic.get(it);
            }
        }, new Function1<MifareClassic, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicRestore$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MifareClassic mifareClassic) {
                invoke2(mifareClassic);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MifareClassic it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("blockIndex");
                Intrinsics.checkNotNull(argument);
                it.restore(((Number) argument).intValue());
                result.success(null);
            }
        });
    }

    private final void handleMifareClassicTransfer(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, MifareClassic>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicTransfer$1
            @Override // kotlin.jvm.functions.Function1
            public final MifareClassic invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return MifareClassic.get(it);
            }
        }, new Function1<MifareClassic, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicTransfer$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MifareClassic mifareClassic) {
                invoke2(mifareClassic);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MifareClassic it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("blockIndex");
                Intrinsics.checkNotNull(argument);
                it.transfer(((Number) argument).intValue());
                result.success(null);
            }
        });
    }

    private final void handleMifareClassicTransceive(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, MifareClassic>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicTransceive$1
            @Override // kotlin.jvm.functions.Function1
            public final MifareClassic invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return MifareClassic.get(it);
            }
        }, new Function1<MifareClassic, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareClassicTransceive$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MifareClassic mifareClassic) {
                invoke2(mifareClassic);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MifareClassic it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("data");
                Intrinsics.checkNotNull(argument);
                result.success(it.transceive((byte[]) argument));
            }
        });
    }

    private final void handleMifareUltralightReadPages(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, MifareUltralight>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareUltralightReadPages$1
            @Override // kotlin.jvm.functions.Function1
            public final MifareUltralight invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return MifareUltralight.get(it);
            }
        }, new Function1<MifareUltralight, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareUltralightReadPages$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MifareUltralight mifareUltralight) {
                invoke2(mifareUltralight);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MifareUltralight it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("pageOffset");
                Intrinsics.checkNotNull(argument);
                result.success(it.readPages(((Number) argument).intValue()));
            }
        });
    }

    private final void handleMifareUltralightWritePage(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, MifareUltralight>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareUltralightWritePage$1
            @Override // kotlin.jvm.functions.Function1
            public final MifareUltralight invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return MifareUltralight.get(it);
            }
        }, new Function1<MifareUltralight, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareUltralightWritePage$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MifareUltralight mifareUltralight) {
                invoke2(mifareUltralight);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MifareUltralight it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("pageOffset");
                Intrinsics.checkNotNull(argument);
                int intValue = ((Number) argument).intValue();
                Object argument2 = MethodCall.this.argument("data");
                Intrinsics.checkNotNull(argument2);
                it.writePage(intValue, (byte[]) argument2);
                result.success(null);
            }
        });
    }

    private final void handleMifareUltralightTransceive(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, MifareUltralight>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareUltralightTransceive$1
            @Override // kotlin.jvm.functions.Function1
            public final MifareUltralight invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return MifareUltralight.get(it);
            }
        }, new Function1<MifareUltralight, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleMifareUltralightTransceive$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MifareUltralight mifareUltralight) {
                invoke2(mifareUltralight);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MifareUltralight it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("data");
                Intrinsics.checkNotNull(argument);
                result.success(it.transceive((byte[]) argument));
            }
        });
    }

    private final void handleNdefFormatableFormat(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, NdefFormatable>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNdefFormatableFormat$1
            @Override // kotlin.jvm.functions.Function1
            public final NdefFormatable invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return NdefFormatable.get(it);
            }
        }, new Function1<NdefFormatable, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNdefFormatableFormat$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(NdefFormatable ndefFormatable) {
                invoke2(ndefFormatable);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(NdefFormatable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("firstMessage");
                Intrinsics.checkNotNull(argument);
                it.format(TranslatorKt.getNdefMessage((Map) argument));
                result.success(null);
            }
        });
    }

    private final void handleNdefFormatableFormatReadOnly(final MethodCall call, final MethodChannel.Result result) {
        tagHandler(call, result, new Function1<Tag, NdefFormatable>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNdefFormatableFormatReadOnly$1
            @Override // kotlin.jvm.functions.Function1
            public final NdefFormatable invoke(Tag it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return NdefFormatable.get(it);
            }
        }, new Function1<NdefFormatable, Unit>() { // from class: io.flutter.plugins.nfcmanager.NfcManagerPlugin$handleNdefFormatableFormatReadOnly$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(NdefFormatable ndefFormatable) {
                invoke2(ndefFormatable);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(NdefFormatable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Object argument = MethodCall.this.argument("firstMessage");
                Intrinsics.checkNotNull(argument);
                it.formatReadOnly(TranslatorKt.getNdefMessage((Map) argument));
                result.success(null);
            }
        });
    }

    private final <T extends TagTechnology> void tagHandler(MethodCall call, MethodChannel.Result result, Function1<? super Tag, ? extends T> getMethod, Function1<? super T, Unit> callback) {
        Map<String, Tag> map = this.tags;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tags");
            map = null;
        }
        Object argument = call.argument("handle");
        Intrinsics.checkNotNull(argument);
        Tag tag = map.get(argument);
        if (tag == null) {
            result.error("invalid_parameter", "Tag is not found", null);
            return;
        }
        T invoke = getMethod.invoke(tag);
        if (invoke == null) {
            result.error("invalid_parameter", "Tech is not supported", null);
            return;
        }
        try {
            forceConnect(invoke);
            callback.invoke(invoke);
        } catch (Exception e) {
            result.error("io_exception", e.getLocalizedMessage(), null);
        }
    }

    private final void forceConnect(TagTechnology tech) throws IOException {
        Unit unit;
        TagTechnology tagTechnology = this.connectedTech;
        if (tagTechnology == null) {
            unit = null;
        } else {
            if (Intrinsics.areEqual(tagTechnology.getTag(), tech.getTag()) && Intrinsics.areEqual(tagTechnology.getClass().getName(), tech.getClass().getName())) {
                return;
            }
            try {
                tech.close();
            } catch (IOException unused) {
            }
            tech.connect();
            this.connectedTech = tech;
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            tech.connect();
            this.connectedTech = tech;
        }
    }
}
