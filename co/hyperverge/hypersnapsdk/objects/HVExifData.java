package co.hyperverge.hypersnapsdk.objects;

/* loaded from: classes2.dex */
public class HVExifData {
    private String aperture;
    private String datetime;
    private String exposureTime;
    private String flash;
    private String focalLength;
    private String iso;
    private float latitude;
    private float longitude;
    private String make;
    private String model;
    private String userComment;
    private String whiteBalance;

    public float getLongitude() {
        return this.longitude;
    }

    public void setLongitude(float f) {
        this.longitude = f;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public void setLatitude(float f) {
        this.latitude = f;
    }

    public String getAperture() {
        return this.aperture;
    }

    public void setAperture(String str) {
        this.aperture = str;
    }

    public String getDatetime() {
        return this.datetime;
    }

    public void setDatetime(String str) {
        this.datetime = str;
    }

    public String getExposureTime() {
        return this.exposureTime;
    }

    public void setExposureTime(String str) {
        this.exposureTime = str;
    }

    public String getFlash() {
        return this.flash;
    }

    public void setFlash(String str) {
        this.flash = str;
    }

    public String getFocalLength() {
        return this.focalLength;
    }

    public void setFocalLength(String str) {
        this.focalLength = str;
    }

    public String getIso() {
        return this.iso;
    }

    public void setIso(String str) {
        this.iso = str;
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String str) {
        this.make = str;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public String getWhiteBalance() {
        return this.whiteBalance;
    }

    public void setWhiteBalance(String str) {
        this.whiteBalance = str;
    }

    public String getUserComment() {
        return this.userComment;
    }

    public void setUserComment(String str) {
        this.userComment = str;
    }

    public String toString() {
        return "HVExifData{aperture='" + this.aperture + "', datetime='" + this.datetime + "', exposureTime='" + this.exposureTime + "', flash='" + this.flash + "', focalLength='" + this.focalLength + "', iso='" + this.iso + "', make='" + this.make + "', model='" + this.model + "', whiteBalance='" + this.whiteBalance + "', longitude=" + this.longitude + ", latitude=" + this.latitude + ", userComment=" + this.userComment + '}';
    }
}
