package org.bouncycastle.jcajce.provider.util;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.firebase.sessions.settings.RemoteSettings;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;

/* loaded from: classes6.dex */
public abstract class AsymmetricAlgorithmProvider extends AlgorithmProvider {
    /* JADX INFO: Access modifiers changed from: protected */
    public void addSignatureAlgorithm(ConfigurableProvider configurableProvider, String str, String str2, String str3, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str4 = str + "WITH" + str2;
        String str5 = str + JsonPOJOBuilder.DEFAULT_WITH_PREFIX + str2;
        String str6 = str + "With" + str2;
        String str7 = str + RemoteSettings.FORWARD_SLASH_STRING + str2;
        configurableProvider.addAlgorithm("Signature." + str4, str3);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + str5, str4);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + str6, str4);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + str7, str4);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + aSN1ObjectIdentifier, str4);
        configurableProvider.addAlgorithm("Alg.Alias.Signature.OID." + aSN1ObjectIdentifier, str4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addSignatureAlgorithm(ConfigurableProvider configurableProvider, String str, String str2, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        configurableProvider.addAlgorithm("Signature." + str, str2);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + aSN1ObjectIdentifier, str);
        configurableProvider.addAlgorithm("Alg.Alias.Signature.OID." + aSN1ObjectIdentifier, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void registerOid(ConfigurableProvider configurableProvider, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str, AsymmetricKeyInfoConverter asymmetricKeyInfoConverter) {
        configurableProvider.addAlgorithm("Alg.Alias.KeyFactory." + aSN1ObjectIdentifier, str);
        configurableProvider.addAlgorithm("Alg.Alias.KeyPairGenerator." + aSN1ObjectIdentifier, str);
        configurableProvider.addKeyInfoConverter(aSN1ObjectIdentifier, asymmetricKeyInfoConverter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void registerOidAlgorithmParameterGenerator(ConfigurableProvider configurableProvider, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + aSN1ObjectIdentifier, str);
        configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + aSN1ObjectIdentifier, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void registerOidAlgorithmParameters(ConfigurableProvider configurableProvider, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + aSN1ObjectIdentifier, str);
    }
}
