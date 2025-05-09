package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;

/* loaded from: classes5.dex */
public class CertPolicyId extends ASN1Object {

    /* renamed from: id, reason: collision with root package name */
    private ASN1ObjectIdentifier f143id;

    private CertPolicyId(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.f143id = aSN1ObjectIdentifier;
    }

    public static CertPolicyId getInstance(Object obj) {
        if (obj instanceof CertPolicyId) {
            return (CertPolicyId) obj;
        }
        if (obj != null) {
            return new CertPolicyId(ASN1ObjectIdentifier.getInstance(obj));
        }
        return null;
    }

    public String getId() {
        return this.f143id.getId();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.f143id;
    }
}
