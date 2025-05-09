package io.flutter.plugins.nfcmanager;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;
import io.sentry.ProfilingTraceData;
import io.sentry.protocol.Device;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: Translator.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0007\u001a\u001c\u0010\u0005\u001a\u00020\u00062\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\t0\b\u001a\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\t0\b2\u0006\u0010\u0007\u001a\u00020\u0006\u001a\u001c\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\t0\b2\u0006\u0010\u0007\u001a\u00020\f¨\u0006\r"}, d2 = {"getFlags", "", "options", "", "", "getNdefMessage", "Landroid/nfc/NdefMessage;", "arg", "", "", "getNdefMessageMap", "getTagMap", "Landroid/nfc/Tag;", "nfc_manager_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class TranslatorKt {
    public static /* synthetic */ int getFlags$default(List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = CollectionsKt.emptyList();
        }
        return getFlags(list);
    }

    public static final int getFlags(List<String> options) {
        Intrinsics.checkNotNullParameter(options, "options");
        int i = options.contains("iso14443") ? 3 : 0;
        if (options.contains("iso15693")) {
            i |= 8;
        }
        return options.contains("iso18092") ? i | 4 : i;
    }

    public static final Map<String, Object> getTagMap(Tag arg) {
        Map mapOf;
        Map<String, Object> ndefMessageMap;
        Intrinsics.checkNotNullParameter(arg, "arg");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String[] techList = arg.getTechList();
        Intrinsics.checkNotNullExpressionValue(techList, "arg.techList");
        for (String tech : techList) {
            Intrinsics.checkNotNullExpressionValue(tech, "tech");
            Locale ROOT = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
            String lowerCase = tech.toLowerCase(ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            Object last = CollectionsKt.last((List<? extends Object>) StringsKt.split$default((CharSequence) lowerCase, new String[]{"."}, false, 0, 6, (Object) null));
            if (Intrinsics.areEqual(tech, NfcA.class.getName())) {
                NfcA nfcA = NfcA.get(arg);
                mapOf = MapsKt.mapOf(TuplesKt.to("identifier", arg.getId()), TuplesKt.to("atqa", nfcA.getAtqa()), TuplesKt.to("maxTransceiveLength", Integer.valueOf(nfcA.getMaxTransceiveLength())), TuplesKt.to("sak", Short.valueOf(nfcA.getSak())), TuplesKt.to(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT, Integer.valueOf(nfcA.getTimeout())));
            } else if (Intrinsics.areEqual(tech, NfcB.class.getName())) {
                NfcB nfcB = NfcB.get(arg);
                mapOf = MapsKt.mapOf(TuplesKt.to("identifier", arg.getId()), TuplesKt.to("applicationData", nfcB.getApplicationData()), TuplesKt.to("maxTransceiveLength", Integer.valueOf(nfcB.getMaxTransceiveLength())), TuplesKt.to("protocolInfo", nfcB.getProtocolInfo()));
            } else if (Intrinsics.areEqual(tech, NfcF.class.getName())) {
                NfcF nfcF = NfcF.get(arg);
                mapOf = MapsKt.mapOf(TuplesKt.to("identifier", arg.getId()), TuplesKt.to(Device.JsonKeys.MANUFACTURER, nfcF.getManufacturer()), TuplesKt.to("maxTransceiveLength", Integer.valueOf(nfcF.getMaxTransceiveLength())), TuplesKt.to("systemCode", nfcF.getSystemCode()), TuplesKt.to(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT, Integer.valueOf(nfcF.getTimeout())));
            } else if (Intrinsics.areEqual(tech, NfcV.class.getName())) {
                NfcV nfcV = NfcV.get(arg);
                mapOf = MapsKt.mapOf(TuplesKt.to("identifier", arg.getId()), TuplesKt.to("dsfId", Byte.valueOf(nfcV.getDsfId())), TuplesKt.to("responseFlags", Byte.valueOf(nfcV.getResponseFlags())), TuplesKt.to("maxTransceiveLength", Integer.valueOf(nfcV.getMaxTransceiveLength())));
            } else if (Intrinsics.areEqual(tech, IsoDep.class.getName())) {
                IsoDep isoDep = IsoDep.get(arg);
                mapOf = MapsKt.mapOf(TuplesKt.to("identifier", arg.getId()), TuplesKt.to("hiLayerResponse", isoDep.getHiLayerResponse()), TuplesKt.to("historicalBytes", isoDep.getHistoricalBytes()), TuplesKt.to("isExtendedLengthApduSupported", Boolean.valueOf(isoDep.isExtendedLengthApduSupported())), TuplesKt.to("maxTransceiveLength", Integer.valueOf(isoDep.getMaxTransceiveLength())), TuplesKt.to(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT, Integer.valueOf(isoDep.getTimeout())));
            } else if (Intrinsics.areEqual(tech, MifareClassic.class.getName())) {
                MifareClassic mifareClassic = MifareClassic.get(arg);
                mapOf = MapsKt.mapOf(TuplesKt.to("identifier", arg.getId()), TuplesKt.to("blockCount", Integer.valueOf(mifareClassic.getBlockCount())), TuplesKt.to("maxTransceiveLength", Integer.valueOf(mifareClassic.getMaxTransceiveLength())), TuplesKt.to("sectorCount", Integer.valueOf(mifareClassic.getSectorCount())), TuplesKt.to("size", Integer.valueOf(mifareClassic.getSize())), TuplesKt.to(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT, Integer.valueOf(mifareClassic.getTimeout())), TuplesKt.to("type", Integer.valueOf(mifareClassic.getType())));
            } else if (Intrinsics.areEqual(tech, MifareUltralight.class.getName())) {
                MifareUltralight mifareUltralight = MifareUltralight.get(arg);
                mapOf = MapsKt.mapOf(TuplesKt.to("identifier", arg.getId()), TuplesKt.to("maxTransceiveLength", Integer.valueOf(mifareUltralight.getMaxTransceiveLength())), TuplesKt.to(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT, Integer.valueOf(mifareUltralight.getTimeout())), TuplesKt.to("type", Integer.valueOf(mifareUltralight.getType())));
            } else if (Intrinsics.areEqual(tech, Ndef.class.getName())) {
                Ndef ndef = Ndef.get(arg);
                Pair[] pairArr = new Pair[6];
                pairArr[0] = TuplesKt.to("identifier", arg.getId());
                pairArr[1] = TuplesKt.to("isWritable", Boolean.valueOf(ndef.isWritable()));
                pairArr[2] = TuplesKt.to(SDKConstants.PARAM_CONTEXT_MAX_SIZE, Integer.valueOf(ndef.getMaxSize()));
                pairArr[3] = TuplesKt.to("canMakeReadOnly", Boolean.valueOf(ndef.canMakeReadOnly()));
                if (ndef.getCachedNdefMessage() == null) {
                    ndefMessageMap = null;
                } else {
                    NdefMessage cachedNdefMessage = ndef.getCachedNdefMessage();
                    Intrinsics.checkNotNullExpressionValue(cachedNdefMessage, "it.cachedNdefMessage");
                    ndefMessageMap = getNdefMessageMap(cachedNdefMessage);
                }
                pairArr[4] = TuplesKt.to("cachedMessage", ndefMessageMap);
                pairArr[5] = TuplesKt.to("type", ndef.getType());
                mapOf = MapsKt.mapOf(pairArr);
            } else {
                mapOf = MapsKt.mapOf(TuplesKt.to("identifier", arg.getId()));
            }
            linkedHashMap.put(last, mapOf);
        }
        return linkedHashMap;
    }

    public static final NdefMessage getNdefMessage(Map<String, ? extends Object> arg) {
        Intrinsics.checkNotNullParameter(arg, "arg");
        Object obj = arg.get("records");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<*>");
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : (List) obj) {
            if (obj2 instanceof Map) {
                arrayList.add(obj2);
            }
        }
        ArrayList<Map> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        for (Map map : arrayList2) {
            Object obj3 = map.get("typeNameFormat");
            Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Int");
            short intValue = (short) ((Integer) obj3).intValue();
            Object obj4 = map.get("type");
            Intrinsics.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.ByteArray");
            byte[] bArr = (byte[]) obj4;
            Object obj5 = map.get("identifier");
            byte[] bArr2 = obj5 instanceof byte[] ? (byte[]) obj5 : null;
            Object obj6 = map.get("payload");
            Intrinsics.checkNotNull(obj6, "null cannot be cast to non-null type kotlin.ByteArray");
            arrayList3.add(new NdefRecord(intValue, bArr, bArr2, (byte[]) obj6));
        }
        return new NdefMessage((NdefRecord[]) arrayList3.toArray(new NdefRecord[0]));
    }

    public static final Map<String, Object> getNdefMessageMap(NdefMessage arg) {
        Intrinsics.checkNotNullParameter(arg, "arg");
        NdefRecord[] records = arg.getRecords();
        Intrinsics.checkNotNullExpressionValue(records, "arg.records");
        NdefRecord[] ndefRecordArr = records;
        ArrayList arrayList = new ArrayList(ndefRecordArr.length);
        for (NdefRecord ndefRecord : ndefRecordArr) {
            arrayList.add(MapsKt.mapOf(TuplesKt.to("typeNameFormat", Short.valueOf(ndefRecord.getTnf())), TuplesKt.to("type", ndefRecord.getType()), TuplesKt.to("identifier", ndefRecord.getId()), TuplesKt.to("payload", ndefRecord.getPayload())));
        }
        return MapsKt.mapOf(TuplesKt.to("records", CollectionsKt.toList(arrayList)));
    }
}
