package co.hyperverge.hypersnapsdk.objects;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.gson.annotations.SerializedName;
import io.sentry.SentryBaseEvent;
import io.sentry.protocol.Geo;
import io.sentry.protocol.ViewHierarchyNode;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class AadhaarQRResponse implements Serializable {

    @SerializedName(alternate = {"n"}, value = "name")
    private String name = "";

    @SerializedName(alternate = {CmcdData.Factory.STREAMING_FORMAT_SS}, value = "signature")
    private String signature = "";

    @SerializedName(alternate = {"a"}, value = "full_address")
    private String fullAddress = "";

    @SerializedName(alternate = {ViewHierarchyNode.JsonKeys.X}, value = "unknown")
    private String unknown = "";

    @SerializedName(alternate = {"g"}, value = "gender")
    private String gender = "";

    @SerializedName(alternate = {"d"}, value = "dob")
    private String dob = "";

    @SerializedName(alternate = {"u", "uid"}, value = "aadhaar")
    private String aadhaar = "";

    @SerializedName(alternate = {SentryBaseEvent.JsonKeys.DIST, "districtName"}, value = "district")
    private String district = "";

    @SerializedName(alternate = {"co"}, value = "care_of")
    private String careOf = "";

    @SerializedName(alternate = {"house"}, value = "house_number")
    private String houseNumber = "";

    @SerializedName(alternate = {"lm"}, value = "landmark")
    private String landmark = "";

    @SerializedName(alternate = {"loc"}, value = "locality")
    private String locality = "";

    @SerializedName(alternate = {"pc", "pincode"}, value = "pin")
    private String pin = "";

    @SerializedName("po")
    private String po = "";

    @SerializedName(alternate = {"stateName"}, value = "state")
    private String state = "";

    @SerializedName("street")
    private String street = "";

    @SerializedName("subdist")
    private String subDistrict = "";

    @SerializedName(alternate = {"vtc", "vtcName"}, value = Geo.JsonKeys.CITY)
    private String city = "";

    @SerializedName("yob")
    private String yob = "";

    @SerializedName("gname")
    private String gName = "";

    @SerializedName("mobile_hash")
    private String mobileHash = "";

    @SerializedName("email_hash")
    private String emailHash = "";

    @SerializedName("photo_jp2000")
    private String photoJP2000 = "";

    @SerializedName("value")
    private String value = "";

    protected boolean canEqual(Object obj) {
        return obj instanceof AadhaarQRResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AadhaarQRResponse)) {
            return false;
        }
        AadhaarQRResponse aadhaarQRResponse = (AadhaarQRResponse) obj;
        if (!aadhaarQRResponse.canEqual(this)) {
            return false;
        }
        String name = getName();
        String name2 = aadhaarQRResponse.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        String signature = getSignature();
        String signature2 = aadhaarQRResponse.getSignature();
        if (signature != null ? !signature.equals(signature2) : signature2 != null) {
            return false;
        }
        String fullAddress = getFullAddress();
        String fullAddress2 = aadhaarQRResponse.getFullAddress();
        if (fullAddress != null ? !fullAddress.equals(fullAddress2) : fullAddress2 != null) {
            return false;
        }
        String unknown = getUnknown();
        String unknown2 = aadhaarQRResponse.getUnknown();
        if (unknown != null ? !unknown.equals(unknown2) : unknown2 != null) {
            return false;
        }
        String gender = getGender();
        String gender2 = aadhaarQRResponse.getGender();
        if (gender != null ? !gender.equals(gender2) : gender2 != null) {
            return false;
        }
        String dob = getDob();
        String dob2 = aadhaarQRResponse.getDob();
        if (dob != null ? !dob.equals(dob2) : dob2 != null) {
            return false;
        }
        String aadhaar = getAadhaar();
        String aadhaar2 = aadhaarQRResponse.getAadhaar();
        if (aadhaar != null ? !aadhaar.equals(aadhaar2) : aadhaar2 != null) {
            return false;
        }
        String district = getDistrict();
        String district2 = aadhaarQRResponse.getDistrict();
        if (district != null ? !district.equals(district2) : district2 != null) {
            return false;
        }
        String careOf = getCareOf();
        String careOf2 = aadhaarQRResponse.getCareOf();
        if (careOf != null ? !careOf.equals(careOf2) : careOf2 != null) {
            return false;
        }
        String houseNumber = getHouseNumber();
        String houseNumber2 = aadhaarQRResponse.getHouseNumber();
        if (houseNumber != null ? !houseNumber.equals(houseNumber2) : houseNumber2 != null) {
            return false;
        }
        String landmark = getLandmark();
        String landmark2 = aadhaarQRResponse.getLandmark();
        if (landmark != null ? !landmark.equals(landmark2) : landmark2 != null) {
            return false;
        }
        String locality = getLocality();
        String locality2 = aadhaarQRResponse.getLocality();
        if (locality != null ? !locality.equals(locality2) : locality2 != null) {
            return false;
        }
        String pin = getPin();
        String pin2 = aadhaarQRResponse.getPin();
        if (pin != null ? !pin.equals(pin2) : pin2 != null) {
            return false;
        }
        String po = getPo();
        String po2 = aadhaarQRResponse.getPo();
        if (po != null ? !po.equals(po2) : po2 != null) {
            return false;
        }
        String state = getState();
        String state2 = aadhaarQRResponse.getState();
        if (state != null ? !state.equals(state2) : state2 != null) {
            return false;
        }
        String street = getStreet();
        String street2 = aadhaarQRResponse.getStreet();
        if (street != null ? !street.equals(street2) : street2 != null) {
            return false;
        }
        String subDistrict = getSubDistrict();
        String subDistrict2 = aadhaarQRResponse.getSubDistrict();
        if (subDistrict != null ? !subDistrict.equals(subDistrict2) : subDistrict2 != null) {
            return false;
        }
        String city = getCity();
        String city2 = aadhaarQRResponse.getCity();
        if (city != null ? !city.equals(city2) : city2 != null) {
            return false;
        }
        String yob = getYob();
        String yob2 = aadhaarQRResponse.getYob();
        if (yob != null ? !yob.equals(yob2) : yob2 != null) {
            return false;
        }
        String gName = getGName();
        String gName2 = aadhaarQRResponse.getGName();
        if (gName != null ? !gName.equals(gName2) : gName2 != null) {
            return false;
        }
        String mobileHash = getMobileHash();
        String mobileHash2 = aadhaarQRResponse.getMobileHash();
        if (mobileHash != null ? !mobileHash.equals(mobileHash2) : mobileHash2 != null) {
            return false;
        }
        String emailHash = getEmailHash();
        String emailHash2 = aadhaarQRResponse.getEmailHash();
        if (emailHash != null ? !emailHash.equals(emailHash2) : emailHash2 != null) {
            return false;
        }
        String photoJP2000 = getPhotoJP2000();
        String photoJP20002 = aadhaarQRResponse.getPhotoJP2000();
        if (photoJP2000 != null ? !photoJP2000.equals(photoJP20002) : photoJP20002 != null) {
            return false;
        }
        String value = getValue();
        String value2 = aadhaarQRResponse.getValue();
        return value != null ? value.equals(value2) : value2 == null;
    }

    public int hashCode() {
        String name = getName();
        int hashCode = name == null ? 43 : name.hashCode();
        String signature = getSignature();
        int hashCode2 = ((hashCode + 59) * 59) + (signature == null ? 43 : signature.hashCode());
        String fullAddress = getFullAddress();
        int hashCode3 = (hashCode2 * 59) + (fullAddress == null ? 43 : fullAddress.hashCode());
        String unknown = getUnknown();
        int hashCode4 = (hashCode3 * 59) + (unknown == null ? 43 : unknown.hashCode());
        String gender = getGender();
        int hashCode5 = (hashCode4 * 59) + (gender == null ? 43 : gender.hashCode());
        String dob = getDob();
        int hashCode6 = (hashCode5 * 59) + (dob == null ? 43 : dob.hashCode());
        String aadhaar = getAadhaar();
        int hashCode7 = (hashCode6 * 59) + (aadhaar == null ? 43 : aadhaar.hashCode());
        String district = getDistrict();
        int hashCode8 = (hashCode7 * 59) + (district == null ? 43 : district.hashCode());
        String careOf = getCareOf();
        int hashCode9 = (hashCode8 * 59) + (careOf == null ? 43 : careOf.hashCode());
        String houseNumber = getHouseNumber();
        int hashCode10 = (hashCode9 * 59) + (houseNumber == null ? 43 : houseNumber.hashCode());
        String landmark = getLandmark();
        int hashCode11 = (hashCode10 * 59) + (landmark == null ? 43 : landmark.hashCode());
        String locality = getLocality();
        int hashCode12 = (hashCode11 * 59) + (locality == null ? 43 : locality.hashCode());
        String pin = getPin();
        int hashCode13 = (hashCode12 * 59) + (pin == null ? 43 : pin.hashCode());
        String po = getPo();
        int hashCode14 = (hashCode13 * 59) + (po == null ? 43 : po.hashCode());
        String state = getState();
        int hashCode15 = (hashCode14 * 59) + (state == null ? 43 : state.hashCode());
        String street = getStreet();
        int hashCode16 = (hashCode15 * 59) + (street == null ? 43 : street.hashCode());
        String subDistrict = getSubDistrict();
        int hashCode17 = (hashCode16 * 59) + (subDistrict == null ? 43 : subDistrict.hashCode());
        String city = getCity();
        int hashCode18 = (hashCode17 * 59) + (city == null ? 43 : city.hashCode());
        String yob = getYob();
        int hashCode19 = (hashCode18 * 59) + (yob == null ? 43 : yob.hashCode());
        String gName = getGName();
        int hashCode20 = (hashCode19 * 59) + (gName == null ? 43 : gName.hashCode());
        String mobileHash = getMobileHash();
        int hashCode21 = (hashCode20 * 59) + (mobileHash == null ? 43 : mobileHash.hashCode());
        String emailHash = getEmailHash();
        int hashCode22 = (hashCode21 * 59) + (emailHash == null ? 43 : emailHash.hashCode());
        String photoJP2000 = getPhotoJP2000();
        int hashCode23 = (hashCode22 * 59) + (photoJP2000 == null ? 43 : photoJP2000.hashCode());
        String value = getValue();
        return (hashCode23 * 59) + (value != null ? value.hashCode() : 43);
    }

    public void setAadhaar(String str) {
        this.aadhaar = str;
    }

    public void setCareOf(String str) {
        this.careOf = str;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setDistrict(String str) {
        this.district = str;
    }

    public void setDob(String str) {
        this.dob = str;
    }

    public void setEmailHash(String str) {
        this.emailHash = str;
    }

    public void setFullAddress(String str) {
        this.fullAddress = str;
    }

    public void setGName(String str) {
        this.gName = str;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public void setHouseNumber(String str) {
        this.houseNumber = str;
    }

    public void setLandmark(String str) {
        this.landmark = str;
    }

    public void setLocality(String str) {
        this.locality = str;
    }

    public void setMobileHash(String str) {
        this.mobileHash = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPhotoJP2000(String str) {
        this.photoJP2000 = str;
    }

    public void setPin(String str) {
        this.pin = str;
    }

    public void setPo(String str) {
        this.po = str;
    }

    public void setSignature(String str) {
        this.signature = str;
    }

    public void setState(String str) {
        this.state = str;
    }

    public void setStreet(String str) {
        this.street = str;
    }

    public void setSubDistrict(String str) {
        this.subDistrict = str;
    }

    public void setUnknown(String str) {
        this.unknown = str;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public void setYob(String str) {
        this.yob = str;
    }

    public String toString() {
        return "AadhaarQRResponse(name=" + getName() + ", signature=" + getSignature() + ", fullAddress=" + getFullAddress() + ", unknown=" + getUnknown() + ", gender=" + getGender() + ", dob=" + getDob() + ", aadhaar=" + getAadhaar() + ", district=" + getDistrict() + ", careOf=" + getCareOf() + ", houseNumber=" + getHouseNumber() + ", landmark=" + getLandmark() + ", locality=" + getLocality() + ", pin=" + getPin() + ", po=" + getPo() + ", state=" + getState() + ", street=" + getStreet() + ", subDistrict=" + getSubDistrict() + ", city=" + getCity() + ", yob=" + getYob() + ", gName=" + getGName() + ", mobileHash=" + getMobileHash() + ", emailHash=" + getEmailHash() + ", photoJP2000=" + getPhotoJP2000() + ", value=" + getValue() + ")";
    }

    public String getName() {
        return this.name;
    }

    public String getSignature() {
        return this.signature;
    }

    public String getFullAddress() {
        return this.fullAddress;
    }

    public String getUnknown() {
        return this.unknown;
    }

    public String getGender() {
        return this.gender;
    }

    public String getDob() {
        return this.dob;
    }

    public String getAadhaar() {
        return this.aadhaar;
    }

    public String getDistrict() {
        return this.district;
    }

    public String getCareOf() {
        return this.careOf;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }

    public String getLandmark() {
        return this.landmark;
    }

    public String getLocality() {
        return this.locality;
    }

    public String getPin() {
        return this.pin;
    }

    public String getPo() {
        return this.po;
    }

    public String getState() {
        return this.state;
    }

    public String getStreet() {
        return this.street;
    }

    public String getSubDistrict() {
        return this.subDistrict;
    }

    public String getCity() {
        return this.city;
    }

    public String getYob() {
        return this.yob;
    }

    public String getGName() {
        return this.gName;
    }

    public String getMobileHash() {
        return this.mobileHash;
    }

    public String getEmailHash() {
        return this.emailHash;
    }

    public String getPhotoJP2000() {
        return this.photoJP2000;
    }

    public String getValue() {
        return this.value;
    }
}
