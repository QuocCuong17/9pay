package co.hyperverge.hvnfcsdk.adapters;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import co.hyperverge.hvnfcsdk.helpers.DGParser;
import co.hyperverge.hvnfcsdk.helpers.LogUtils;
import co.hyperverge.hvnfcsdk.listeners.NFCParserStatus;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import java.util.Arrays;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.form.PDButton;

/* loaded from: classes2.dex */
public class HVNFCAdapter implements DefaultLifecycleObserver {
    private final String TAG = "HVNFCAdapter";
    private Context context;
    private String[][] filter;
    private NfcAdapter nativeAdapter;
    private PendingIntent pendingIntent;

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onDestroy(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onStart(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onStop(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    public boolean deviceHasNfc(Context context) {
        LogUtils.d("HVNFCAdapter", "deviceHasNfc() called");
        NfcManager nfcManager = (NfcManager) context.getSystemService(WorkflowModule.TYPE_NFC);
        return (nfcManager == null || nfcManager.getDefaultAdapter() == null) ? false : true;
    }

    public void register(Context context, Lifecycle lifecycle) {
        LogUtils.d("HVNFCAdapter", "register() called");
        this.context = context;
        NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(context);
        this.nativeAdapter = defaultAdapter;
        if (defaultAdapter != null) {
            Intent intent = new Intent(context.getApplicationContext(), context.getClass());
            intent.setFlags(536870912);
            this.pendingIntent = PendingIntent.getActivity(context, 0, intent, PDButton.FLAG_RADIOS_IN_UNISON);
            this.filter = new String[][]{new String[]{"android.nfc.tech.IsoDep"}};
            lifecycle.addObserver(this);
        }
    }

    public boolean isEnabled() {
        NfcAdapter nfcAdapter = this.nativeAdapter;
        if (nfcAdapter != null) {
            return nfcAdapter.isEnabled();
        }
        return false;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onResume(LifecycleOwner lifecycleOwner) {
        LogUtils.d("HVNFCAdapter", "onResume() called");
        resumeNFCAdapter();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onPause(LifecycleOwner lifecycleOwner) {
        LogUtils.d("HVNFCAdapter", "onPause() called");
        pauseNFCAdapter();
    }

    public void pauseNFCAdapter() {
        NfcAdapter nfcAdapter = this.nativeAdapter;
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch((Activity) this.context);
        }
    }

    public void resumeNFCAdapter() {
        NfcAdapter nfcAdapter = this.nativeAdapter;
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch((Activity) this.context, this.pendingIntent, null, this.filter);
        }
    }

    public void parseIntent(Intent intent, Map<String, String> map, NFCParserStatus nFCParserStatus) {
        Tag tag;
        LogUtils.d("HVNFCAdapter", "parseIntent() called");
        if (!"android.nfc.action.TECH_DISCOVERED".equals(intent.getAction()) || intent.getExtras() == null || (tag = (Tag) intent.getExtras().getParcelable("android.nfc.extra.TAG")) == null || !Arrays.asList(tag.getTechList()).contains("android.nfc.tech.IsoDep")) {
            return;
        }
        DGParser dGParser = new DGParser(this.context, IsoDep.get(tag), map);
        dGParser.setDelegate(nFCParserStatus);
        dGParser.parseData();
    }
}
