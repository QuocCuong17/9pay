package org.bouncycastle.pqc.crypto.xmss;

import com.beust.jcommander.Parameters;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.jmrtd.cbeff.ISO781611;

/* loaded from: classes6.dex */
final class WOTSPlusOid implements XMSSOid {
    private static final Map<String, WOTSPlusOid> oidLookupTable;
    private final int oid;
    private final String stringRepresentation;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(createKey("SHA-256", 32, 16, 67), new WOTSPlusOid(16777217, "WOTSP_SHA2-256_W16"));
        hashMap.put(createKey("SHA-512", 64, 16, ISO781611.CREATION_DATE_AND_TIME_TAG), new WOTSPlusOid(33554434, "WOTSP_SHA2-512_W16"));
        hashMap.put(createKey("SHAKE128", 32, 16, 67), new WOTSPlusOid(50331651, "WOTSP_SHAKE128_W16"));
        hashMap.put(createKey("SHAKE256", 64, 16, ISO781611.CREATION_DATE_AND_TIME_TAG), new WOTSPlusOid(67108868, "WOTSP_SHAKE256_W16"));
        oidLookupTable = Collections.unmodifiableMap(hashMap);
    }

    private WOTSPlusOid(int i, String str) {
        this.oid = i;
        this.stringRepresentation = str;
    }

    private static String createKey(String str, int i, int i2, int i3) {
        Objects.requireNonNull(str, "algorithmName == null");
        return str + Parameters.DEFAULT_OPTION_PREFIXES + i + Parameters.DEFAULT_OPTION_PREFIXES + i2 + Parameters.DEFAULT_OPTION_PREFIXES + i3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static WOTSPlusOid lookup(String str, int i, int i2, int i3) {
        Objects.requireNonNull(str, "algorithmName == null");
        return oidLookupTable.get(createKey(str, i, i2, i3));
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSOid
    public int getOid() {
        return this.oid;
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSOid
    public String toString() {
        return this.stringRepresentation;
    }
}
