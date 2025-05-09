package org.jmrtd;

import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.text.Typography;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* loaded from: classes6.dex */
public class BACKey implements BACKeySpec {
    private static final String SDF = "yyMMdd";
    private static final long serialVersionUID = -1059774581180524710L;
    private String dateOfBirth;
    private String dateOfExpiry;
    private String documentNumber;

    @Override // org.jmrtd.AccessKeySpec
    public String getAlgorithm() {
        return "BAC";
    }

    protected BACKey() {
    }

    public BACKey(String str, Date date, Date date2) {
        this(str, toString(date), toString(date2));
    }

    public BACKey(String str, String str2, String str3) {
        if (str == null) {
            throw new IllegalArgumentException("Illegal document number");
        }
        if (str2 == null || str2.length() != 6) {
            throw new IllegalArgumentException("Illegal date: " + str2);
        }
        if (str3 == null || str3.length() != 6) {
            throw new IllegalArgumentException("Illegal date: " + str3);
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < 9) {
            sb.append(Typography.less);
        }
        this.documentNumber = sb.toString().trim();
        this.dateOfBirth = str2;
        this.dateOfExpiry = str3;
    }

    @Override // org.jmrtd.BACKeySpec
    public String getDocumentNumber() {
        return this.documentNumber;
    }

    @Override // org.jmrtd.BACKeySpec
    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    @Override // org.jmrtd.BACKeySpec
    public String getDateOfExpiry() {
        return this.dateOfExpiry;
    }

    public String toString() {
        return this.documentNumber + ", " + this.dateOfBirth + ", " + this.dateOfExpiry;
    }

    public int hashCode() {
        String str = this.documentNumber;
        int hashCode = (305 + (str == null ? 0 : str.hashCode())) * 61;
        String str2 = this.dateOfBirth;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 61;
        String str3 = this.dateOfExpiry;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        BACKey bACKey = (BACKey) obj;
        return this.documentNumber.equals(bACKey.documentNumber) && this.dateOfBirth.equals(bACKey.dateOfBirth) && this.dateOfExpiry.equals(bACKey.dateOfExpiry);
    }

    @Override // org.jmrtd.AccessKeySpec
    public byte[] getKey() {
        try {
            return Util.computeKeySeed(this.documentNumber, this.dateOfBirth, this.dateOfExpiry, McElieceCCA2KeyGenParameterSpec.SHA1, true);
        } catch (GeneralSecurityException e) {
            throw new IllegalArgumentException("Unexpected exception", e);
        }
    }

    protected void setDocumentNumber(String str) {
        this.documentNumber = str;
    }

    protected void setDateOfBirth(String str) {
        this.dateOfBirth = str;
    }

    protected void setDateOfExpiry(String str) {
        this.dateOfExpiry = str;
    }

    private static synchronized String toString(Date date) {
        String format;
        synchronized (BACKey.class) {
            format = new SimpleDateFormat(SDF).format(date);
        }
        return format;
    }
}
