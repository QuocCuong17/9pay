package org.jmrtd.lds.icao;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import kotlin.text.Typography;
import net.sf.scuba.data.Gender;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jmrtd.lds.AbstractLDSInfo;

/* loaded from: classes6.dex */
public class MRZInfo extends AbstractLDSInfo {
    public static final int DOC_TYPE_ID1 = 1;
    public static final int DOC_TYPE_ID2 = 2;
    public static final int DOC_TYPE_ID3 = 3;
    public static final int DOC_TYPE_UNSPECIFIED = 0;
    private static final String MRZ_CHARS = "<0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final long serialVersionUID = 7054965914471297804L;
    private char compositeCheckDigit;
    private String dateOfBirth;
    private char dateOfBirthCheckDigit;
    private String dateOfExpiry;
    private char dateOfExpiryCheckDigit;
    private String documentCode;
    private String documentNumber;
    private char documentNumberCheckDigit;

    @Deprecated
    private int documentType;
    private Gender gender;
    private String issuingState;
    private String nationality;
    private String optionalData1;
    private String optionalData2;
    private String primaryIdentifier;
    private String secondaryIdentifier;

    public MRZInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, Gender gender, String str8, String str9) {
        if (str == null || str.length() < 1 || str.length() > 2 || (!str.startsWith("P") && !str.startsWith("V"))) {
            throw new IllegalArgumentException("Wrong document code: " + str);
        }
        this.documentType = getDocumentTypeFromDocumentCode(str);
        this.documentCode = trimFillerChars(str);
        this.issuingState = str2;
        this.primaryIdentifier = str3;
        this.secondaryIdentifier = str4;
        this.documentNumber = trimFillerChars(str5);
        this.nationality = str6;
        this.dateOfBirth = str7;
        this.gender = gender;
        this.dateOfExpiry = str8;
        if (str9 == null || equalsModuloFillerChars(str9, "")) {
            this.optionalData1 = "";
        } else if (str9.length() == 15) {
            this.optionalData1 = str9;
        } else if (str9.length() <= 14) {
            this.optionalData1 = mrzFormat(str9, 14) + checkDigit(str9, true);
        } else {
            throw new IllegalArgumentException("Wrong personal number: " + str9);
        }
        checkDigit();
    }

    public MRZInfo(String str, String str2, String str3, String str4, String str5, Gender gender, String str6, String str7, String str8, String str9, String str10) {
        String str11;
        if (str == null || str.length() < 1 || str.length() > 2 || (!str.startsWith("C") && !str.startsWith("I") && !str.startsWith("A"))) {
            throw new IllegalArgumentException("Wrong document code: " + str);
        }
        this.documentType = getDocumentTypeFromDocumentCode(str);
        this.documentCode = trimFillerChars(str);
        this.issuingState = str2;
        this.primaryIdentifier = str9;
        this.secondaryIdentifier = str10;
        this.documentNumber = trimFillerChars(str3);
        this.nationality = str7;
        this.dateOfBirth = str5;
        this.gender = gender;
        this.dateOfExpiry = str6;
        if (str4 == null || str4.length() > 15) {
            StringBuilder sb = new StringBuilder();
            sb.append("Wrong optional data 1: ");
            if (str4 == null) {
                str11 = "null";
            } else {
                str11 = "\"" + str4 + "\"";
            }
            sb.append(str11);
            throw new IllegalArgumentException(sb.toString());
        }
        this.optionalData1 = str4;
        this.optionalData2 = str8;
        checkDigit();
    }

    public MRZInfo(InputStream inputStream, int i) {
        try {
            readObject(inputStream, i);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public MRZInfo(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Null string");
        }
        String replace = str.trim().replace(IOUtils.LINE_SEPARATOR_UNIX, "");
        try {
            readObject(new ByteArrayInputStream(replace.getBytes("UTF-8")), replace.length());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Exception", e);
        } catch (IOException e2) {
            throw new IllegalArgumentException("Exception", e2);
        }
    }

    private void readObject(InputStream inputStream, int i) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String readStringWithFillers = readStringWithFillers(dataInputStream, 2);
        this.documentCode = readStringWithFillers;
        this.documentType = getDocumentTypeFromDocumentCode(readStringWithFillers);
        if (i == 88) {
            this.documentType = 3;
        } else if (i == 90) {
            this.documentType = 1;
        } else {
            this.documentType = getDocumentTypeFromDocumentCode(this.documentCode);
        }
        if (this.documentType == 1) {
            this.issuingState = readCountry(dataInputStream);
            this.documentNumber = readString(dataInputStream, 9);
            this.documentNumberCheckDigit = (char) dataInputStream.readUnsignedByte();
            String readStringWithFillers2 = readStringWithFillers(dataInputStream, 15);
            this.optionalData1 = readStringWithFillers2;
            if (this.documentNumberCheckDigit == '<' && !readStringWithFillers2.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.documentNumber);
                String str = this.optionalData1;
                sb.append(str.substring(0, str.length() - 1));
                this.documentNumber = sb.toString();
                String str2 = this.optionalData1;
                this.documentNumberCheckDigit = str2.charAt(str2.length() - 1);
                this.optionalData1 = null;
            }
            this.documentNumber = trimFillerChars(this.documentNumber);
            this.dateOfBirth = readDateOfBirth(dataInputStream);
            this.dateOfBirthCheckDigit = (char) dataInputStream.readUnsignedByte();
            this.gender = readGender(dataInputStream);
            this.dateOfExpiry = readDateOfExpiry(dataInputStream);
            this.dateOfExpiryCheckDigit = (char) dataInputStream.readUnsignedByte();
            this.nationality = readCountry(dataInputStream);
            this.optionalData2 = readString(dataInputStream, 11);
            this.compositeCheckDigit = (char) dataInputStream.readUnsignedByte();
            readNameIdentifiers(readString(dataInputStream, 30));
            return;
        }
        this.issuingState = readCountry(dataInputStream);
        readNameIdentifiers(readString(dataInputStream, 39));
        this.documentNumber = trimFillerChars(readString(dataInputStream, 9));
        this.documentNumberCheckDigit = (char) dataInputStream.readUnsignedByte();
        this.nationality = readCountry(dataInputStream);
        this.dateOfBirth = readDateOfBirth(dataInputStream);
        this.dateOfBirthCheckDigit = (char) dataInputStream.readUnsignedByte();
        this.gender = readGender(dataInputStream);
        this.dateOfExpiry = readDateOfExpiry(dataInputStream);
        this.dateOfExpiryCheckDigit = (char) dataInputStream.readUnsignedByte();
        String readStringWithFillers3 = readStringWithFillers(dataInputStream, 14);
        this.optionalData1 = mrzFormat(readStringWithFillers3, 14) + ((char) dataInputStream.readUnsignedByte());
        this.compositeCheckDigit = (char) dataInputStream.readUnsignedByte();
    }

    @Override // org.jmrtd.lds.AbstractLDSInfo
    public void writeObject(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        writeDocumentType(dataOutputStream);
        if (this.documentType == 1) {
            writeIssuingState(dataOutputStream);
            if (this.documentNumber.length() > 9 && equalsModuloFillerChars(this.optionalData1, "")) {
                writeString(this.documentNumber.substring(0, 9), dataOutputStream, 9);
                dataOutputStream.write(60);
                StringBuilder sb = new StringBuilder();
                String str = this.documentNumber;
                sb.append(str.substring(9, str.length()));
                sb.append(this.documentNumberCheckDigit);
                sb.append("<");
                writeString(sb.toString(), dataOutputStream, 15);
            } else {
                writeString(this.documentNumber, dataOutputStream, 9);
                dataOutputStream.write(this.documentNumberCheckDigit);
                writeString(this.optionalData1, dataOutputStream, 15);
            }
            writeDateOfBirth(dataOutputStream);
            dataOutputStream.write(this.dateOfBirthCheckDigit);
            writeGender(dataOutputStream);
            writeDateOfExpiry(dataOutputStream);
            dataOutputStream.write(this.dateOfExpiryCheckDigit);
            writeNationality(dataOutputStream);
            writeString(this.optionalData2, dataOutputStream, 11);
            dataOutputStream.write(this.compositeCheckDigit);
            writeName(dataOutputStream, 30);
            return;
        }
        writeIssuingState(dataOutputStream);
        writeName(dataOutputStream, 39);
        writeString(this.documentNumber, dataOutputStream, 9);
        dataOutputStream.write(this.documentNumberCheckDigit);
        writeNationality(dataOutputStream);
        writeDateOfBirth(dataOutputStream);
        dataOutputStream.write(this.dateOfBirthCheckDigit);
        writeGender(dataOutputStream);
        writeDateOfExpiry(dataOutputStream);
        dataOutputStream.write(this.dateOfExpiryCheckDigit);
        writeString(this.optionalData1, dataOutputStream, 15);
        dataOutputStream.write(this.compositeCheckDigit);
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(String str) {
        this.dateOfBirth = str;
        checkDigit();
    }

    public String getDateOfExpiry() {
        return this.dateOfExpiry;
    }

    public void setDateOfExpiry(String str) {
        this.dateOfExpiry = str;
        checkDigit();
    }

    public String getDocumentNumber() {
        return this.documentNumber;
    }

    public void setDocumentNumber(String str) {
        this.documentNumber = str.trim();
        checkDigit();
    }

    public int getDocumentType() {
        return this.documentType;
    }

    public String getDocumentCode() {
        return this.documentCode;
    }

    public void setDocumentCode(String str) {
        this.documentCode = str;
        int documentTypeFromDocumentCode = getDocumentTypeFromDocumentCode(str);
        this.documentType = documentTypeFromDocumentCode;
        if (documentTypeFromDocumentCode == 1 && this.optionalData2 == null) {
            this.optionalData2 = "";
        }
    }

    public String getIssuingState() {
        return this.issuingState;
    }

    public void setIssuingState(String str) {
        this.issuingState = str;
        checkDigit();
    }

    public String getPrimaryIdentifier() {
        return this.primaryIdentifier;
    }

    public void setPrimaryIdentifier(String str) {
        this.primaryIdentifier = str.trim();
        checkDigit();
    }

    public String getSecondaryIdentifier() {
        return this.secondaryIdentifier;
    }

    public String[] getSecondaryIdentifierComponents() {
        return this.secondaryIdentifier.split(" |<");
    }

    public void setSecondaryIdentifierComponents(String[] strArr) {
        if (strArr == null) {
            this.secondaryIdentifier = null;
        } else {
            for (String str : strArr) {
                int length = strArr.length;
            }
        }
        checkDigit();
    }

    public void setSecondaryIdentifiers(String str) {
        readSecondaryIdentifiers(str.trim());
        checkDigit();
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String str) {
        this.nationality = str;
        checkDigit();
    }

    public String getPersonalNumber() {
        String str = this.optionalData1;
        if (str == null) {
            return null;
        }
        if (str.length() > 14) {
            return trimFillerChars(this.optionalData1.substring(0, 14));
        }
        return trimFillerChars(this.optionalData1);
    }

    public void setPersonalNumber(String str) {
        if (str == null || str.length() > 14) {
            throw new IllegalArgumentException("Wrong personal number");
        }
        this.optionalData1 = mrzFormat(str, 14) + checkDigit(str, true);
    }

    public String getOptionalData1() {
        return this.optionalData1;
    }

    public String getOptionalData2() {
        return this.optionalData2;
    }

    public void setOptionalData2(String str) {
        this.optionalData2 = trimFillerChars(str);
        checkDigit();
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
        checkDigit();
    }

    public String toString() {
        try {
            String str = new String(getEncoded(), "UTF-8");
            int length = str.length();
            if (length == 88) {
                return str.substring(0, 44) + IOUtils.LINE_SEPARATOR_UNIX + str.substring(44, 88) + IOUtils.LINE_SEPARATOR_UNIX;
            }
            if (length != 90) {
                return str;
            }
            return str.substring(0, 30) + IOUtils.LINE_SEPARATOR_UNIX + str.substring(30, 60) + IOUtils.LINE_SEPARATOR_UNIX + str.substring(60, 90) + IOUtils.LINE_SEPARATOR_UNIX;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public int hashCode() {
        return (toString().hashCode() * 2) + 53;
    }

    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        MRZInfo mRZInfo = (MRZInfo) obj;
        String str = this.documentCode;
        if (!(str == null && mRZInfo.documentCode == null) && (str == null || !str.equals(mRZInfo.documentCode))) {
            return false;
        }
        String str2 = this.issuingState;
        if (!(str2 == null && mRZInfo.issuingState == null) && (str2 == null || !str2.equals(mRZInfo.issuingState))) {
            return false;
        }
        String str3 = this.primaryIdentifier;
        if (!(str3 == null && mRZInfo.primaryIdentifier == null) && (str3 == null || !str3.equals(mRZInfo.primaryIdentifier))) {
            return false;
        }
        String str4 = this.secondaryIdentifier;
        if ((str4 != null || mRZInfo.secondaryIdentifier != null) && !equalsModuloFillerChars(str4, mRZInfo.secondaryIdentifier)) {
            return false;
        }
        String str5 = this.nationality;
        if (!(str5 == null && mRZInfo.nationality == null) && (str5 == null || !str5.equals(mRZInfo.nationality))) {
            return false;
        }
        String str6 = this.documentNumber;
        if (!(str6 == null && mRZInfo.documentNumber == null) && (str6 == null || !str6.equals(mRZInfo.documentNumber))) {
            return false;
        }
        String str7 = this.optionalData1;
        if (!(str7 == null && mRZInfo.optionalData1 == null) && ((str7 == null || !str7.equals(mRZInfo.optionalData1)) && !getPersonalNumber().equals(mRZInfo.getPersonalNumber()))) {
            return false;
        }
        String str8 = this.dateOfBirth;
        if (!(str8 == null && mRZInfo.dateOfBirth == null) && (str8 == null || !str8.equals(mRZInfo.dateOfBirth))) {
            return false;
        }
        Gender gender = this.gender;
        if (!(gender == null && mRZInfo.gender == null) && (gender == null || !gender.equals(mRZInfo.gender))) {
            return false;
        }
        String str9 = this.dateOfExpiry;
        if (!(str9 == null && mRZInfo.dateOfExpiry == null) && (str9 == null || !str9.equals(mRZInfo.dateOfExpiry))) {
            return false;
        }
        String str10 = this.optionalData2;
        return (str10 == null && mRZInfo.optionalData2 == null) || (str10 != null && equalsModuloFillerChars(str10, mRZInfo.optionalData2));
    }

    public static char checkDigit(String str) {
        return checkDigit(str, false);
    }

    private void readNameIdentifiers(String str) {
        int indexOf = str.indexOf("<<");
        if (indexOf < 0) {
            this.primaryIdentifier = trimFillerChars(str);
            this.secondaryIdentifier = "";
        } else {
            this.primaryIdentifier = trimFillerChars(str.substring(0, indexOf));
            readSecondaryIdentifiers(str.substring(str.indexOf("<<") + 2));
        }
    }

    private void readSecondaryIdentifiers(String str) {
        this.secondaryIdentifier = str;
    }

    private void writeString(String str, DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.write(mrzFormat(str, i).getBytes("UTF-8"));
    }

    private void writeIssuingState(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.write(this.issuingState.getBytes("UTF-8"));
    }

    private void writeDateOfExpiry(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.write(this.dateOfExpiry.getBytes("UTF-8"));
    }

    private void writeGender(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.write(genderToString(this.gender).getBytes("UTF-8"));
    }

    private void writeDateOfBirth(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.write(this.dateOfBirth.getBytes("UTF-8"));
    }

    private void writeNationality(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.write(this.nationality.getBytes("UTF-8"));
    }

    private void writeName(DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.write(nameToString(this.primaryIdentifier, this.secondaryIdentifier, i).getBytes("UTF-8"));
    }

    private void writeDocumentType(DataOutputStream dataOutputStream) throws IOException {
        writeString(this.documentCode, dataOutputStream, 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.jmrtd.lds.icao.MRZInfo$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$sf$scuba$data$Gender;

        static {
            int[] iArr = new int[Gender.values().length];
            $SwitchMap$net$sf$scuba$data$Gender = iArr;
            try {
                iArr[Gender.MALE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$sf$scuba$data$Gender[Gender.FEMALE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static String genderToString(Gender gender) {
        int i = AnonymousClass1.$SwitchMap$net$sf$scuba$data$Gender[gender.ordinal()];
        return i != 1 ? i != 2 ? "<" : PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION : "M";
    }

    private static String nameToString(String str, String str2, int i) {
        String[] split = str.split(" |<");
        String[] split2 = (str2 == null || str2.trim().isEmpty()) ? new String[0] : str2.split(" |<");
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        boolean z2 = true;
        for (String str3 : split) {
            if (z2) {
                z2 = false;
            } else {
                sb.append(Typography.less);
            }
            sb.append(str3);
        }
        if (str2 != null && !str2.trim().isEmpty()) {
            sb.append("<<");
            for (String str4 : split2) {
                if (z) {
                    z = false;
                } else {
                    sb.append(Typography.less);
                }
                sb.append(str4);
            }
        }
        return mrzFormat(sb.toString(), i);
    }

    private String readStringWithFillers(DataInputStream dataInputStream, int i) throws IOException {
        return trimFillerChars(readString(dataInputStream, i));
    }

    private String readCountry(DataInputStream dataInputStream) throws IOException {
        return readString(dataInputStream, 3);
    }

    private Gender readGender(DataInputStream dataInputStream) throws IOException {
        String readString = readString(dataInputStream, 1);
        if ("M".equalsIgnoreCase(readString)) {
            return Gender.MALE;
        }
        if (PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION.equalsIgnoreCase(readString)) {
            return Gender.FEMALE;
        }
        return Gender.UNKNOWN;
    }

    private String readDateOfBirth(DataInputStream dataInputStream) throws IOException, NumberFormatException {
        return readString(dataInputStream, 6);
    }

    private String readDateOfExpiry(DataInputStream dataInputStream) throws IOException {
        return readString(dataInputStream, 6);
    }

    private String readString(DataInputStream dataInputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        dataInputStream.readFully(bArr);
        return new String(bArr).trim();
    }

    private static String mrzFormat(String str, int i) {
        if (str == null) {
            return "";
        }
        if (str.length() > i) {
            throw new IllegalArgumentException("Argument too wide (" + str.length() + " > " + i + ")");
        }
        String trim = str.toUpperCase().trim();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < trim.length(); i2++) {
            char charAt = trim.charAt(i2);
            if (MRZ_CHARS.indexOf(charAt) == -1) {
                sb.append(Typography.less);
            } else {
                sb.append(charAt);
            }
        }
        while (sb.length() < i) {
            sb.append("<");
        }
        return sb.toString();
    }

    public static boolean equalsModuloFillerChars(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        int max = Math.max(str.length(), str2.length());
        return mrzFormat(str, max).equals(mrzFormat(str2, max));
    }

    private static int getDocumentTypeFromDocumentCode(String str) {
        if (str == null || str.length() < 1 || str.length() > 2) {
            throw new IllegalArgumentException("Was expecting 1 or 2 digit document code, got " + str);
        }
        if (str.startsWith("A") || str.startsWith("C") || str.startsWith("I") || str.startsWith("V")) {
            return 1;
        }
        return str.startsWith("P") ? 3 : 0;
    }

    private static String trimFillerChars(String str) {
        byte[] bytes = str.trim().getBytes();
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == 60) {
                bytes[i] = 32;
            }
        }
        return new String(bytes).trim();
    }

    private void checkDigit() {
        this.documentNumberCheckDigit = checkDigit(this.documentNumber);
        this.dateOfBirthCheckDigit = checkDigit(this.dateOfBirth);
        this.dateOfExpiryCheckDigit = checkDigit(this.dateOfExpiry);
        if (this.optionalData1.length() < 15) {
            this.optionalData1 = mrzFormat(this.optionalData1, 14) + checkDigit(mrzFormat(this.optionalData1, 14), true);
        }
        this.compositeCheckDigit = checkDigit(getComposite(this.documentType));
    }

    private String getComposite(int i) {
        StringBuilder sb = new StringBuilder();
        if (i == 1) {
            if (this.documentNumber.length() <= 9) {
                sb.append(mrzFormat(this.documentNumber, 9));
                sb.append(this.documentNumberCheckDigit);
                sb.append(mrzFormat(this.optionalData1, 15));
            } else {
                sb.append(this.documentNumber.substring(0, 9));
                sb.append("<");
                String substring = this.documentNumber.substring(9);
                sb.append(substring);
                sb.append(this.documentNumberCheckDigit);
                String substring2 = mrzFormat(this.optionalData1, 15).substring(substring.length() + 1);
                sb.append(mrzFormat(substring2, substring2.length()));
            }
            sb.append(this.dateOfBirth);
            sb.append(this.dateOfBirthCheckDigit);
            sb.append(this.dateOfExpiry);
            sb.append(this.dateOfExpiryCheckDigit);
            sb.append(mrzFormat(this.optionalData2, 11));
        } else {
            sb.append(this.documentNumber);
            sb.append(this.documentNumberCheckDigit);
            sb.append(this.dateOfBirth);
            sb.append(this.dateOfBirthCheckDigit);
            sb.append(this.dateOfExpiry);
            sb.append(this.dateOfExpiryCheckDigit);
            sb.append(mrzFormat(this.optionalData1, 15));
        }
        return sb.toString();
    }

    private static char checkDigit(String str, boolean z) {
        try {
            byte[] bytes = str == null ? new byte[0] : str.getBytes("UTF-8");
            int[] iArr = {7, 3, 1};
            int i = 0;
            for (int i2 = 0; i2 < bytes.length; i2++) {
                i = (i + (iArr[i2 % 3] * decodeMRZDigit(bytes[i2]))) % 10;
            }
            String num = Integer.toString(i);
            if (num.length() != 1) {
                throw new IllegalStateException("Error in computing check digit.");
            }
            char c = (char) num.getBytes("UTF-8")[0];
            return (z && c == '0') ? Typography.less : c;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Error in computing check digit", e);
        } catch (NumberFormatException e2) {
            throw new IllegalStateException("Error in computing check digit", e2);
        } catch (Exception e3) {
            throw new IllegalArgumentException("Error in computing check digit", e3);
        }
    }

    private static int decodeMRZDigit(byte b) {
        if (b == 60) {
            return 0;
        }
        switch (b) {
            case 48:
                return 0;
            case 49:
                return 1;
            case 50:
                return 2;
            case 51:
                return 3;
            case 52:
                return 4;
            case 53:
                return 5;
            case 54:
                return 6;
            case 55:
                return 7;
            case 56:
                return 8;
            case 57:
                return 9;
            default:
                switch (b) {
                    case 65:
                        return 10;
                    case 66:
                        return 11;
                    case 67:
                        return 12;
                    case 68:
                        return 13;
                    case 69:
                        return 14;
                    case 70:
                        return 15;
                    case 71:
                        return 16;
                    case 72:
                        return 17;
                    case 73:
                        return 18;
                    case 74:
                        return 19;
                    case 75:
                        return 20;
                    case 76:
                        return 21;
                    case 77:
                        return 22;
                    case 78:
                        return 23;
                    case 79:
                        return 24;
                    case 80:
                        return 25;
                    case 81:
                        return 26;
                    case 82:
                        return 27;
                    case 83:
                        return 28;
                    case 84:
                        return 29;
                    case 85:
                        return 30;
                    case 86:
                        return 31;
                    case 87:
                        return 32;
                    case 88:
                        return 33;
                    case 89:
                        return 34;
                    case 90:
                        return 35;
                    default:
                        switch (b) {
                            case 97:
                                return 10;
                            case 98:
                                return 11;
                            case 99:
                                return 12;
                            case 100:
                                return 13;
                            case 101:
                                return 14;
                            case 102:
                                return 15;
                            case 103:
                                return 16;
                            case 104:
                                return 17;
                            case 105:
                                return 18;
                            case 106:
                                return 19;
                            case 107:
                                return 20;
                            case 108:
                                return 21;
                            case 109:
                                return 22;
                            case 110:
                                return 23;
                            case 111:
                                return 24;
                            case 112:
                                return 25;
                            case 113:
                                return 26;
                            case 114:
                                return 27;
                            case 115:
                                return 28;
                            case 116:
                                return 29;
                            case 117:
                                return 30;
                            case 118:
                                return 31;
                            case 119:
                                return 32;
                            case 120:
                                return 33;
                            case 121:
                                return 34;
                            case 122:
                                return 35;
                            default:
                                throw new NumberFormatException("Could not decode MRZ character " + ((int) b) + " ('" + Character.toString((char) b) + "')");
                        }
                }
        }
    }
}
