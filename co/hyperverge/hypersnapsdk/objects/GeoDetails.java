package co.hyperverge.hypersnapsdk.objects;

import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.facebook.appevents.UserDataStore;
import com.google.gson.annotations.SerializedName;
import io.sentry.protocol.Geo;

/* loaded from: classes2.dex */
public class GeoDetails {

    @SerializedName("as")
    private String as;

    @SerializedName(Geo.JsonKeys.CITY)
    private String city;

    @SerializedName(UserDataStore.COUNTRY)
    private String country;

    @SerializedName(WorkflowModule.Properties.VideoStatementV2API.COUNTRY_CODE)
    private String countryCode;

    @SerializedName("isp")
    private String isp;

    @SerializedName("lat")
    private String latitude;

    @SerializedName("long")
    private String longitude;

    /* renamed from: org, reason: collision with root package name */
    @SerializedName("org")
    private String f58org;

    @SerializedName("query")
    private String query;

    @SerializedName("region")
    private String region;

    @SerializedName("regionName")
    private String regionName;

    @SerializedName("status")
    private String status;

    @SerializedName("timezone")
    private String timezone;

    @SerializedName("zip")
    private String zip;

    protected boolean canEqual(Object obj) {
        return obj instanceof GeoDetails;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GeoDetails)) {
            return false;
        }
        GeoDetails geoDetails = (GeoDetails) obj;
        if (!geoDetails.canEqual(this)) {
            return false;
        }
        String as = getAs();
        String as2 = geoDetails.getAs();
        if (as != null ? !as.equals(as2) : as2 != null) {
            return false;
        }
        String city = getCity();
        String city2 = geoDetails.getCity();
        if (city != null ? !city.equals(city2) : city2 != null) {
            return false;
        }
        String country = getCountry();
        String country2 = geoDetails.getCountry();
        if (country != null ? !country.equals(country2) : country2 != null) {
            return false;
        }
        String countryCode = getCountryCode();
        String countryCode2 = geoDetails.getCountryCode();
        if (countryCode != null ? !countryCode.equals(countryCode2) : countryCode2 != null) {
            return false;
        }
        String isp = getIsp();
        String isp2 = geoDetails.getIsp();
        if (isp != null ? !isp.equals(isp2) : isp2 != null) {
            return false;
        }
        String latitude = getLatitude();
        String latitude2 = geoDetails.getLatitude();
        if (latitude != null ? !latitude.equals(latitude2) : latitude2 != null) {
            return false;
        }
        String longitude = getLongitude();
        String longitude2 = geoDetails.getLongitude();
        if (longitude != null ? !longitude.equals(longitude2) : longitude2 != null) {
            return false;
        }
        String org2 = getOrg();
        String org3 = geoDetails.getOrg();
        if (org2 != null ? !org2.equals(org3) : org3 != null) {
            return false;
        }
        String query = getQuery();
        String query2 = geoDetails.getQuery();
        if (query != null ? !query.equals(query2) : query2 != null) {
            return false;
        }
        String region = getRegion();
        String region2 = geoDetails.getRegion();
        if (region != null ? !region.equals(region2) : region2 != null) {
            return false;
        }
        String regionName = getRegionName();
        String regionName2 = geoDetails.getRegionName();
        if (regionName != null ? !regionName.equals(regionName2) : regionName2 != null) {
            return false;
        }
        String status = getStatus();
        String status2 = geoDetails.getStatus();
        if (status != null ? !status.equals(status2) : status2 != null) {
            return false;
        }
        String timezone = getTimezone();
        String timezone2 = geoDetails.getTimezone();
        if (timezone != null ? !timezone.equals(timezone2) : timezone2 != null) {
            return false;
        }
        String zip = getZip();
        String zip2 = geoDetails.getZip();
        return zip != null ? zip.equals(zip2) : zip2 == null;
    }

    public int hashCode() {
        String as = getAs();
        int hashCode = as == null ? 43 : as.hashCode();
        String city = getCity();
        int hashCode2 = ((hashCode + 59) * 59) + (city == null ? 43 : city.hashCode());
        String country = getCountry();
        int hashCode3 = (hashCode2 * 59) + (country == null ? 43 : country.hashCode());
        String countryCode = getCountryCode();
        int hashCode4 = (hashCode3 * 59) + (countryCode == null ? 43 : countryCode.hashCode());
        String isp = getIsp();
        int hashCode5 = (hashCode4 * 59) + (isp == null ? 43 : isp.hashCode());
        String latitude = getLatitude();
        int hashCode6 = (hashCode5 * 59) + (latitude == null ? 43 : latitude.hashCode());
        String longitude = getLongitude();
        int hashCode7 = (hashCode6 * 59) + (longitude == null ? 43 : longitude.hashCode());
        String org2 = getOrg();
        int hashCode8 = (hashCode7 * 59) + (org2 == null ? 43 : org2.hashCode());
        String query = getQuery();
        int hashCode9 = (hashCode8 * 59) + (query == null ? 43 : query.hashCode());
        String region = getRegion();
        int hashCode10 = (hashCode9 * 59) + (region == null ? 43 : region.hashCode());
        String regionName = getRegionName();
        int hashCode11 = (hashCode10 * 59) + (regionName == null ? 43 : regionName.hashCode());
        String status = getStatus();
        int hashCode12 = (hashCode11 * 59) + (status == null ? 43 : status.hashCode());
        String timezone = getTimezone();
        int hashCode13 = (hashCode12 * 59) + (timezone == null ? 43 : timezone.hashCode());
        String zip = getZip();
        return (hashCode13 * 59) + (zip != null ? zip.hashCode() : 43);
    }

    public void setAs(String str) {
        this.as = str;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public void setIsp(String str) {
        this.isp = str;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public void setOrg(String str) {
        this.f58org = str;
    }

    public void setQuery(String str) {
        this.query = str;
    }

    public void setRegion(String str) {
        this.region = str;
    }

    public void setRegionName(String str) {
        this.regionName = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setTimezone(String str) {
        this.timezone = str;
    }

    public void setZip(String str) {
        this.zip = str;
    }

    public String toString() {
        return "GeoDetails(as=" + getAs() + ", city=" + getCity() + ", country=" + getCountry() + ", countryCode=" + getCountryCode() + ", isp=" + getIsp() + ", latitude=" + getLatitude() + ", longitude=" + getLongitude() + ", org=" + getOrg() + ", query=" + getQuery() + ", region=" + getRegion() + ", regionName=" + getRegionName() + ", status=" + getStatus() + ", timezone=" + getTimezone() + ", zip=" + getZip() + ")";
    }

    public String getAs() {
        return this.as;
    }

    public String getCity() {
        return this.city;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getIsp() {
        return this.isp;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public String getOrg() {
        return this.f58org;
    }

    public String getQuery() {
        return this.query;
    }

    public String getRegion() {
        return this.region;
    }

    public String getRegionName() {
        return this.regionName;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTimezone() {
        return this.timezone;
    }

    public String getZip() {
        return this.zip;
    }
}
