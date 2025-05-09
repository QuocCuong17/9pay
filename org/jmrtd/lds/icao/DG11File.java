package org.jmrtd.lds.icao;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.text.Typography;
import net.sf.scuba.tlv.TLVInputStream;
import net.sf.scuba.tlv.TLVOutputStream;
import net.sf.scuba.tlv.TLVUtil;
import net.sf.scuba.util.Hex;
import okhttp3.HttpUrl;
import org.jmrtd.lds.DataGroup;

/* loaded from: classes6.dex */
public class DG11File extends DataGroup {
    public static final int CONTENT_SPECIFIC_CONSTRUCTED_TAG = 160;
    public static final int COUNT_TAG = 2;
    public static final int CUSTODY_INFORMATION_TAG = 24344;
    public static final int FULL_DATE_OF_BIRTH_TAG = 24363;
    public static final int FULL_NAME_TAG = 24334;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    public static final int OTHER_NAME_TAG = 24335;
    public static final int OTHER_VALID_TD_NUMBERS_TAG = 24343;
    public static final int PERMANENT_ADDRESS_TAG = 24386;
    public static final int PERSONAL_NUMBER_TAG = 24336;
    public static final int PERSONAL_SUMMARY_TAG = 24341;
    public static final int PLACE_OF_BIRTH_TAG = 24337;
    public static final int PROFESSION_TAG = 24339;
    public static final int PROOF_OF_CITIZENSHIP_TAG = 24342;
    private static final String SDF = "yyyyMMdd";
    public static final int TAG_LIST_TAG = 92;
    public static final int TELEPHONE_TAG = 24338;
    public static final int TITLE_TAG = 24340;
    private static final long serialVersionUID = 8566312538928662937L;
    private String custodyInformation;
    private String fullDateOfBirth;
    private String nameOfHolder;
    private List<String> otherNames;
    private List<String> otherValidTDNumbers;
    private List<String> permanentAddress;
    private String personalNumber;
    private String personalSummary;
    private List<String> placeOfBirth;
    private String profession;
    private byte[] proofOfCitizenship;
    private List<Integer> tagPresenceList;
    private String telephone;
    private String title;

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    public int getTag() {
        return 107;
    }

    public DG11File(InputStream inputStream) throws IOException {
        super(107, inputStream);
    }

    public DG11File(String str, List<String> list, String str2, Date date, List<String> list2, List<String> list3, String str3, String str4, String str5, String str6, byte[] bArr, List<String> list4, String str7) {
        this(str, list, str2, new SimpleDateFormat(SDF).format(date), list2, list3, str3, str4, str5, str6, bArr, list4, str7);
    }

    public DG11File(String str, List<String> list, String str2, String str3, List<String> list2, List<String> list3, String str4, String str5, String str6, String str7, byte[] bArr, List<String> list4, String str8) {
        super(107);
        this.nameOfHolder = str;
        this.otherNames = list == null ? new ArrayList() : new ArrayList(list);
        this.personalNumber = str2;
        this.fullDateOfBirth = str3;
        this.placeOfBirth = list2 == null ? new ArrayList() : new ArrayList(list2);
        this.permanentAddress = list3;
        this.telephone = str4;
        this.profession = str5;
        this.title = str6;
        this.personalSummary = str7;
        this.proofOfCitizenship = bArr;
        this.otherValidTDNumbers = list4 == null ? new ArrayList() : new ArrayList(list4);
        this.custodyInformation = str8;
    }

    public List<Integer> getTagPresenceList() {
        List<Integer> list = this.tagPresenceList;
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList(12);
        this.tagPresenceList = arrayList;
        if (this.nameOfHolder != null) {
            arrayList.add(Integer.valueOf(FULL_NAME_TAG));
        }
        List<String> list2 = this.otherNames;
        if (list2 != null && !list2.isEmpty()) {
            this.tagPresenceList.add(Integer.valueOf(OTHER_NAME_TAG));
        }
        if (this.personalNumber != null) {
            this.tagPresenceList.add(Integer.valueOf(PERSONAL_NUMBER_TAG));
        }
        if (this.fullDateOfBirth != null) {
            this.tagPresenceList.add(Integer.valueOf(FULL_DATE_OF_BIRTH_TAG));
        }
        List<String> list3 = this.placeOfBirth;
        if (list3 != null && !list3.isEmpty()) {
            this.tagPresenceList.add(Integer.valueOf(PLACE_OF_BIRTH_TAG));
        }
        List<String> list4 = this.permanentAddress;
        if (list4 != null && !list4.isEmpty()) {
            this.tagPresenceList.add(Integer.valueOf(PERMANENT_ADDRESS_TAG));
        }
        if (this.telephone != null) {
            this.tagPresenceList.add(Integer.valueOf(TELEPHONE_TAG));
        }
        if (this.profession != null) {
            this.tagPresenceList.add(Integer.valueOf(PROFESSION_TAG));
        }
        if (this.title != null) {
            this.tagPresenceList.add(Integer.valueOf(TITLE_TAG));
        }
        if (this.personalSummary != null) {
            this.tagPresenceList.add(Integer.valueOf(PERSONAL_SUMMARY_TAG));
        }
        if (this.proofOfCitizenship != null) {
            this.tagPresenceList.add(Integer.valueOf(PROOF_OF_CITIZENSHIP_TAG));
        }
        List<String> list5 = this.otherValidTDNumbers;
        if (list5 != null && !list5.isEmpty()) {
            this.tagPresenceList.add(Integer.valueOf(OTHER_VALID_TD_NUMBERS_TAG));
        }
        if (this.custodyInformation != null) {
            this.tagPresenceList.add(Integer.valueOf(CUSTODY_INFORMATION_TAG));
        }
        return this.tagPresenceList;
    }

    public String getNameOfHolder() {
        return this.nameOfHolder;
    }

    public List<String> getOtherNames() {
        return this.otherNames == null ? new ArrayList() : new ArrayList(this.otherNames);
    }

    public String getPersonalNumber() {
        return this.personalNumber;
    }

    public String getFullDateOfBirth() {
        return this.fullDateOfBirth;
    }

    public List<String> getPlaceOfBirth() {
        return this.placeOfBirth;
    }

    public List<String> getPermanentAddress() {
        return this.permanentAddress;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public String getProfession() {
        return this.profession;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPersonalSummary() {
        return this.personalSummary;
    }

    public byte[] getProofOfCitizenship() {
        return this.proofOfCitizenship;
    }

    public List<String> getOtherValidTDNumbers() {
        return this.otherValidTDNumbers;
    }

    public String getCustodyInformation() {
        return this.custodyInformation;
    }

    @Override // org.jmrtd.lds.DataGroup, org.jmrtd.lds.AbstractTaggedLDSFile
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("DG11File [");
        String str2 = this.nameOfHolder;
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append(", ");
        List<String> list = this.otherNames;
        String str3 = HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        sb.append((list == null || list.isEmpty()) ? HttpUrl.PATH_SEGMENT_ENCODE_SET_URI : this.otherNames);
        sb.append(", ");
        String str4 = this.personalNumber;
        if (str4 == null) {
            str4 = "";
        }
        sb.append(str4);
        sb.append(", ");
        String str5 = this.fullDateOfBirth;
        if (str5 == null) {
            str5 = "";
        }
        sb.append(str5);
        sb.append(", ");
        List<String> list2 = this.placeOfBirth;
        sb.append((list2 == null || list2.isEmpty()) ? HttpUrl.PATH_SEGMENT_ENCODE_SET_URI : this.placeOfBirth.toString());
        sb.append(", ");
        List<String> list3 = this.permanentAddress;
        sb.append((list3 == null || list3.isEmpty()) ? HttpUrl.PATH_SEGMENT_ENCODE_SET_URI : this.permanentAddress.toString());
        sb.append(", ");
        String str6 = this.telephone;
        if (str6 == null) {
            str6 = "";
        }
        sb.append(str6);
        sb.append(", ");
        String str7 = this.profession;
        if (str7 == null) {
            str7 = "";
        }
        sb.append(str7);
        sb.append(", ");
        String str8 = this.title;
        if (str8 == null) {
            str8 = "";
        }
        sb.append(str8);
        sb.append(", ");
        String str9 = this.personalSummary;
        if (str9 == null) {
            str9 = "";
        }
        sb.append(str9);
        sb.append(", ");
        if (this.proofOfCitizenship == null) {
            str = "";
        } else {
            str = "image (" + this.proofOfCitizenship.length + ")";
        }
        sb.append(str);
        sb.append(", ");
        List<String> list4 = this.otherValidTDNumbers;
        if (list4 != null && !list4.isEmpty()) {
            str3 = this.otherValidTDNumbers.toString();
        }
        sb.append(str3);
        sb.append(", ");
        String str10 = this.custodyInformation;
        sb.append(str10 != null ? str10 : "");
        sb.append("]");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass().equals(getClass())) {
            return toString().equals(((DG11File) obj).toString());
        }
        return false;
    }

    public int hashCode() {
        return (toString().hashCode() * 13) + 111;
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    protected void readContent(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        if (tLVInputStream.readTag() != 92) {
            throw new IllegalArgumentException("Expected tag list in DG11");
        }
        int readLength = tLVInputStream.readLength();
        int i = 0;
        int i2 = readLength / 2;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(tLVInputStream.readValue());
        try {
            ArrayList arrayList = new ArrayList(i2 + 1);
            while (i < readLength) {
                int readTag = new TLVInputStream(byteArrayInputStream).readTag();
                i += TLVUtil.getTagLength(readTag);
                arrayList.add(Integer.valueOf(readTag));
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                readField(((Integer) it.next()).intValue(), tLVInputStream);
            }
        } finally {
            byteArrayInputStream.close();
        }
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    protected void writeContent(OutputStream outputStream) throws IOException {
        TLVOutputStream tLVOutputStream = outputStream instanceof TLVOutputStream ? (TLVOutputStream) outputStream : new TLVOutputStream(outputStream);
        tLVOutputStream.writeTag(92);
        DataOutputStream dataOutputStream = new DataOutputStream(tLVOutputStream);
        List<Integer> tagPresenceList = getTagPresenceList();
        Iterator<Integer> it = tagPresenceList.iterator();
        while (it.hasNext()) {
            dataOutputStream.writeShort(it.next().intValue());
        }
        dataOutputStream.flush();
        tLVOutputStream.writeValueEnd();
        Iterator<Integer> it2 = tagPresenceList.iterator();
        while (it2.hasNext()) {
            int intValue = it2.next().intValue();
            if (intValue != 24363) {
                boolean z = true;
                if (intValue != 24386) {
                    switch (intValue) {
                        case FULL_NAME_TAG /* 24334 */:
                            tLVOutputStream.writeTag(intValue);
                            tLVOutputStream.writeValue(this.nameOfHolder.trim().getBytes("UTF-8"));
                            break;
                        case OTHER_NAME_TAG /* 24335 */:
                            if (this.otherNames == null) {
                                this.otherNames = new ArrayList();
                            }
                            tLVOutputStream.writeTag(160);
                            tLVOutputStream.writeTag(2);
                            tLVOutputStream.write(this.otherNames.size());
                            tLVOutputStream.writeValueEnd();
                            for (String str : this.otherNames) {
                                tLVOutputStream.writeTag(OTHER_NAME_TAG);
                                tLVOutputStream.writeValue(str.trim().getBytes("UTF-8"));
                            }
                            tLVOutputStream.writeValueEnd();
                            break;
                        case PERSONAL_NUMBER_TAG /* 24336 */:
                            tLVOutputStream.writeTag(intValue);
                            tLVOutputStream.writeValue(this.personalNumber.trim().getBytes("UTF-8"));
                            break;
                        case PLACE_OF_BIRTH_TAG /* 24337 */:
                            tLVOutputStream.writeTag(intValue);
                            for (String str2 : this.placeOfBirth) {
                                if (str2 != null) {
                                    if (z) {
                                        z = false;
                                    } else {
                                        tLVOutputStream.write(60);
                                    }
                                    tLVOutputStream.write(str2.trim().getBytes("UTF-8"));
                                }
                            }
                            tLVOutputStream.writeValueEnd();
                            break;
                        case TELEPHONE_TAG /* 24338 */:
                            tLVOutputStream.writeTag(intValue);
                            tLVOutputStream.writeValue(this.telephone.trim().replace(' ', Typography.less).getBytes("UTF-8"));
                            break;
                        case PROFESSION_TAG /* 24339 */:
                            tLVOutputStream.writeTag(intValue);
                            tLVOutputStream.writeValue(this.profession.trim().replace(' ', Typography.less).getBytes("UTF-8"));
                            break;
                        case TITLE_TAG /* 24340 */:
                            tLVOutputStream.writeTag(intValue);
                            tLVOutputStream.writeValue(this.title.trim().replace(' ', Typography.less).getBytes("UTF-8"));
                            break;
                        case PERSONAL_SUMMARY_TAG /* 24341 */:
                            tLVOutputStream.writeTag(intValue);
                            tLVOutputStream.writeValue(this.personalSummary.trim().replace(' ', Typography.less).getBytes("UTF-8"));
                            break;
                        case PROOF_OF_CITIZENSHIP_TAG /* 24342 */:
                            tLVOutputStream.writeTag(intValue);
                            tLVOutputStream.writeValue(this.proofOfCitizenship);
                            break;
                        case OTHER_VALID_TD_NUMBERS_TAG /* 24343 */:
                            tLVOutputStream.writeTag(intValue);
                            for (String str3 : this.otherValidTDNumbers) {
                                if (str3 != null) {
                                    if (z) {
                                        z = false;
                                    } else {
                                        tLVOutputStream.write(60);
                                    }
                                    tLVOutputStream.write(str3.trim().replace(' ', Typography.less).getBytes("UTF-8"));
                                }
                            }
                            tLVOutputStream.writeValueEnd();
                            break;
                        case CUSTODY_INFORMATION_TAG /* 24344 */:
                            tLVOutputStream.writeTag(intValue);
                            tLVOutputStream.writeValue(this.custodyInformation.trim().replace(' ', Typography.less).getBytes("UTF-8"));
                            break;
                        default:
                            throw new IllegalStateException("Unknown tag in DG11: " + Integer.toHexString(intValue));
                    }
                } else {
                    tLVOutputStream.writeTag(intValue);
                    for (String str4 : this.permanentAddress) {
                        if (str4 != null) {
                            if (z) {
                                z = false;
                            } else {
                                tLVOutputStream.write(60);
                            }
                            tLVOutputStream.write(str4.trim().getBytes("UTF-8"));
                        }
                    }
                    tLVOutputStream.writeValueEnd();
                }
            } else {
                tLVOutputStream.writeTag(intValue);
                tLVOutputStream.writeValue(this.fullDateOfBirth.getBytes("UTF-8"));
            }
        }
    }

    private void parseCustodyInformation(byte[] bArr) {
        try {
            this.custodyInformation = new String(bArr, "UTF-8").trim();
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
            this.custodyInformation = new String(bArr).trim();
        }
    }

    private void parseOtherValidTDNumbers(byte[] bArr) {
        String trim = new String(bArr).trim();
        try {
            trim = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
        }
        this.otherValidTDNumbers = new ArrayList();
        StringTokenizer stringTokenizer = new StringTokenizer(trim, "<");
        while (stringTokenizer.hasMoreTokens()) {
            this.otherValidTDNumbers.add(stringTokenizer.nextToken().trim());
        }
    }

    private void parseProofOfCitizenShip(byte[] bArr) {
        this.proofOfCitizenship = bArr;
    }

    private void parsePersonalSummary(byte[] bArr) {
        try {
            this.personalSummary = new String(bArr, "UTF-8").trim();
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
            this.personalSummary = new String(bArr).trim();
        }
    }

    private void parseTitle(byte[] bArr) {
        try {
            this.title = new String(bArr, "UTF-8").trim();
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
            this.title = new String(bArr).trim();
        }
    }

    private void parseProfession(byte[] bArr) {
        String str = new String(bArr);
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
        }
        this.profession = str.trim();
    }

    private void parseTelephone(byte[] bArr) {
        String str = new String(bArr);
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
        }
        this.telephone = str.replace("<", " ").trim();
    }

    private void parsePermanentAddress(byte[] bArr) {
        String str = new String(bArr);
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, "<");
        this.permanentAddress = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            this.permanentAddress.add(stringTokenizer.nextToken().trim());
        }
    }

    private void parsePlaceOfBirth(byte[] bArr) {
        String str = new String(bArr);
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, "<");
        this.placeOfBirth = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            this.placeOfBirth.add(stringTokenizer.nextToken().trim());
        }
    }

    private void parseFullDateOfBirth(byte[] bArr) {
        String str;
        if (bArr.length == 4) {
            str = Hex.bytesToHexString(bArr);
        } else {
            String str2 = new String(bArr);
            try {
                str = new String(bArr, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
                str = str2;
            }
        }
        this.fullDateOfBirth = str;
    }

    private synchronized void parseOtherName(byte[] bArr) {
        if (this.otherNames == null) {
            this.otherNames = new ArrayList();
        }
        try {
            this.otherNames.add(new String(bArr, "UTF-8").trim());
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
            this.otherNames.add(new String(bArr).trim());
        }
    }

    private void parsePersonalNumber(byte[] bArr) {
        String str = new String(bArr);
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
        }
        this.personalNumber = str.trim();
    }

    private void parseNameOfHolder(byte[] bArr) {
        String str = new String(bArr);
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
        }
        this.nameOfHolder = str.trim();
    }

    private void readField(int i, TLVInputStream tLVInputStream) throws IOException {
        int readTag = tLVInputStream.readTag();
        if (readTag == 160) {
            tLVInputStream.readLength();
            int readTag2 = tLVInputStream.readTag();
            if (readTag2 != 2) {
                throw new IllegalArgumentException("Expected " + Integer.toHexString(2) + ", found " + Integer.toHexString(readTag2));
            }
            int readLength = tLVInputStream.readLength();
            if (readLength != 1) {
                throw new IllegalArgumentException("Expected length 1 count length, found " + readLength);
            }
            byte[] readValue = tLVInputStream.readValue();
            if (readValue == null || readValue.length != 1) {
                throw new IllegalArgumentException("Number of content specific fields should be encoded in single byte, found " + Arrays.toString(readValue));
            }
            int i2 = readValue[0] & 255;
            for (int i3 = 0; i3 < i2; i3++) {
                int readTag3 = tLVInputStream.readTag();
                if (readTag3 != 24335) {
                    throw new IllegalArgumentException("Expected " + Integer.toHexString(OTHER_NAME_TAG) + ", found " + Integer.toHexString(readTag3));
                }
                tLVInputStream.readLength();
                parseOtherName(tLVInputStream.readValue());
            }
            return;
        }
        if (readTag != i) {
            throw new IllegalArgumentException("Expected " + Integer.toHexString(i) + ", but found " + Integer.toHexString(readTag));
        }
        tLVInputStream.readLength();
        byte[] readValue2 = tLVInputStream.readValue();
        if (readTag == 24363) {
            parseFullDateOfBirth(readValue2);
            return;
        }
        if (readTag != 24386) {
            switch (readTag) {
                case FULL_NAME_TAG /* 24334 */:
                    parseNameOfHolder(readValue2);
                    return;
                case OTHER_NAME_TAG /* 24335 */:
                    parseOtherName(readValue2);
                    return;
                case PERSONAL_NUMBER_TAG /* 24336 */:
                    parsePersonalNumber(readValue2);
                    return;
                case PLACE_OF_BIRTH_TAG /* 24337 */:
                    parsePlaceOfBirth(readValue2);
                    return;
                case TELEPHONE_TAG /* 24338 */:
                    parseTelephone(readValue2);
                    return;
                case PROFESSION_TAG /* 24339 */:
                    parseProfession(readValue2);
                    return;
                case TITLE_TAG /* 24340 */:
                    parseTitle(readValue2);
                    return;
                case PERSONAL_SUMMARY_TAG /* 24341 */:
                    parsePersonalSummary(readValue2);
                    return;
                case PROOF_OF_CITIZENSHIP_TAG /* 24342 */:
                    parseProofOfCitizenShip(readValue2);
                    return;
                case OTHER_VALID_TD_NUMBERS_TAG /* 24343 */:
                    parseOtherValidTDNumbers(readValue2);
                    return;
                case CUSTODY_INFORMATION_TAG /* 24344 */:
                    parseCustodyInformation(readValue2);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown field tag in DG11: " + Integer.toHexString(readTag));
            }
        }
        parsePermanentAddress(readValue2);
    }
}
