package org.ejbca.cvc;

import com.google.firebase.sessions.settings.RemoteSettings;

/* loaded from: classes6.dex */
public abstract class ReferenceField extends AbstractDataField {
    private static final long serialVersionUID = 1;
    private String country;
    private String mnemonic;
    private String sequence;

    private boolean okChar(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public ReferenceField(CVCTagEnum cVCTagEnum, String str, String str2, String str3) {
        super(cVCTagEnum);
        this.country = null;
        this.mnemonic = null;
        this.sequence = null;
        if (str.length() != 2) {
            throw new IllegalArgumentException("Country code length must be 2, was " + str.length());
        }
        if (!isValidCountry(str)) {
            throw new IllegalArgumentException("Unknown country code: " + str);
        }
        if (str2.length() == 0) {
            throw new IllegalArgumentException("Holder mnemonic too short, must have at least one character");
        }
        if (str2.length() > 9) {
            throw new IllegalArgumentException("Holder mnemonic too long, max=9, was " + str2.length());
        }
        if (str3.length() != 5) {
            throw new IllegalArgumentException("Sequence number must have length 5, was " + str3.length());
        }
        for (int i = 0; i < str3.length(); i++) {
            if (!Character.isLetterOrDigit(str3.charAt(i))) {
                throw new IllegalArgumentException("Sequence number can only contain alphanumerics: " + str3);
            }
        }
        this.country = str;
        this.mnemonic = str2;
        this.sequence = str3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ReferenceField(CVCTagEnum cVCTagEnum, byte[] bArr) {
        super(cVCTagEnum);
        this.country = null;
        this.mnemonic = null;
        this.sequence = null;
        String str = new String(bArr);
        this.country = str.substring(0, 2);
        this.mnemonic = str.substring(2, str.length() - 5);
        this.sequence = str.substring(str.length() - 5);
    }

    public String getConcatenated() {
        return this.country + this.mnemonic + this.sequence;
    }

    public String getCountry() {
        return this.country;
    }

    public String getMnemonic() {
        return this.mnemonic;
    }

    public String getSequence() {
        return this.sequence;
    }

    @Override // org.ejbca.cvc.AbstractDataField
    protected byte[] getEncoded() {
        return getConcatenated().getBytes();
    }

    private boolean isValidCountry(String str) {
        return okChar(str.charAt(0)) && okChar(str.charAt(1));
    }

    @Override // org.ejbca.cvc.AbstractDataField
    public String valueAsText() {
        return this.country + RemoteSettings.FORWARD_SLASH_STRING + this.mnemonic + RemoteSettings.FORWARD_SLASH_STRING + this.sequence;
    }
}
