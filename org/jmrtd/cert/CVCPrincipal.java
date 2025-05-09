package org.jmrtd.cert;

import com.facebook.internal.AnalyticsEvents;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.scuba.data.Country;

/* loaded from: classes6.dex */
public class CVCPrincipal implements Principal, Serializable {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = -4905647207367309688L;
    private Country country;
    private String mnemonic;
    private String seqNumber;

    public CVCPrincipal(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Name should be <Country (2F)><Mnemonic (9V)><SeqNum (5F)> formatted, found null");
        }
        if (str.length() < 7 || str.length() > 16) {
            throw new IllegalArgumentException("Name should be <Country (2F)><Mnemonic (9V)><SeqNum (5F)> formatted, found \"" + str + "\"");
        }
        final String upperCase = str.substring(0, 2).toUpperCase();
        try {
            this.country = Country.getInstance(upperCase);
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.FINE, "Could not find country for " + upperCase, (Throwable) e);
            this.country = new Country() { // from class: org.jmrtd.cert.CVCPrincipal.1
                private static final long serialVersionUID = 345841304964161797L;

                @Override // net.sf.scuba.data.Country
                public String getName() {
                    return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                }

                @Override // net.sf.scuba.data.Country
                public String getNationality() {
                    return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                }

                @Override // net.sf.scuba.data.Country
                public String toAlpha3Code() {
                    return "XXX";
                }

                @Override // net.sf.scuba.data.Country
                public int valueOf() {
                    return -1;
                }

                @Override // net.sf.scuba.data.Country
                public String toAlpha2Code() {
                    return upperCase;
                }
            };
        }
        this.mnemonic = str.substring(2, str.length() - 5);
        this.seqNumber = str.substring(str.length() - 5, str.length());
    }

    public CVCPrincipal(Country country, String str, String str2) {
        if (str == null || str.length() > 9) {
            throw new IllegalArgumentException("Wrong length mnemonic");
        }
        if (str2 == null || str2.length() != 5) {
            throw new IllegalArgumentException("Wrong length seqNumber");
        }
        this.country = country;
        this.mnemonic = str;
        this.seqNumber = str2;
    }

    @Override // java.security.Principal
    public String getName() {
        return this.country.toAlpha2Code() + this.mnemonic + this.seqNumber;
    }

    @Override // java.security.Principal
    public String toString() {
        return this.country.toAlpha2Code() + RemoteSettings.FORWARD_SLASH_STRING + this.mnemonic + RemoteSettings.FORWARD_SLASH_STRING + this.seqNumber;
    }

    public Country getCountry() {
        return this.country;
    }

    public String getMnemonic() {
        return this.mnemonic;
    }

    public String getSeqNumber() {
        return this.seqNumber;
    }

    @Override // java.security.Principal
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!obj.getClass().equals(getClass())) {
            return false;
        }
        CVCPrincipal cVCPrincipal = (CVCPrincipal) obj;
        return cVCPrincipal.country.equals(this.country) && cVCPrincipal.mnemonic.equals(this.mnemonic) && cVCPrincipal.seqNumber.equals(this.seqNumber);
    }

    @Override // java.security.Principal
    public int hashCode() {
        return (getName().hashCode() * 2) + 1231211;
    }
}
