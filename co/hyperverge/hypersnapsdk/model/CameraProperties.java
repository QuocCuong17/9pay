package co.hyperverge.hypersnapsdk.model;

import java.util.Arrays;

/* loaded from: classes2.dex */
public class CameraProperties {
    public byte[] data;
    public int height;
    public boolean isCapturedFramePreviewed;
    public boolean isFrontFacingCam;
    public int orientation;
    public long rgbDataLength;
    public int rotation;
    public int viewHeight;
    public int viewWidth;
    public int width;

    protected boolean canEqual(Object obj) {
        return obj instanceof CameraProperties;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CameraProperties)) {
            return false;
        }
        CameraProperties cameraProperties = (CameraProperties) obj;
        return cameraProperties.canEqual(this) && getWidth() == cameraProperties.getWidth() && getHeight() == cameraProperties.getHeight() && getViewWidth() == cameraProperties.getViewWidth() && getViewHeight() == cameraProperties.getViewHeight() && getRgbDataLength() == cameraProperties.getRgbDataLength() && getOrientation() == cameraProperties.getOrientation() && getRotation() == cameraProperties.getRotation() && isFrontFacingCam() == cameraProperties.isFrontFacingCam() && isCapturedFramePreviewed() == cameraProperties.isCapturedFramePreviewed() && Arrays.equals(getData(), cameraProperties.getData());
    }

    public int hashCode() {
        int width = ((((((getWidth() + 59) * 59) + getHeight()) * 59) + getViewWidth()) * 59) + getViewHeight();
        long rgbDataLength = getRgbDataLength();
        return (((((((((((width * 59) + ((int) (rgbDataLength ^ (rgbDataLength >>> 32)))) * 59) + getOrientation()) * 59) + getRotation()) * 59) + (isFrontFacingCam() ? 79 : 97)) * 59) + (isCapturedFramePreviewed() ? 79 : 97)) * 59) + Arrays.hashCode(getData());
    }

    public void setCapturedFramePreviewed(boolean z) {
        this.isCapturedFramePreviewed = z;
    }

    public void setData(byte[] bArr) {
        this.data = bArr;
    }

    public void setFrontFacingCam(boolean z) {
        this.isFrontFacingCam = z;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void setOrientation(int i) {
        this.orientation = i;
    }

    public void setRgbDataLength(long j) {
        this.rgbDataLength = j;
    }

    public void setRotation(int i) {
        this.rotation = i;
    }

    public void setViewHeight(int i) {
        this.viewHeight = i;
    }

    public void setViewWidth(int i) {
        this.viewWidth = i;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public String toString() {
        return "CameraProperties(width=" + getWidth() + ", height=" + getHeight() + ", viewWidth=" + getViewWidth() + ", viewHeight=" + getViewHeight() + ", rgbDataLength=" + getRgbDataLength() + ", orientation=" + getOrientation() + ", rotation=" + getRotation() + ", data=" + Arrays.toString(getData()) + ", isFrontFacingCam=" + isFrontFacingCam() + ", isCapturedFramePreviewed=" + isCapturedFramePreviewed() + ")";
    }

    public CameraProperties(int i, int i2, int i3, int i4, long j, int i5, int i6, byte[] bArr, boolean z, boolean z2) {
        this.isCapturedFramePreviewed = false;
        this.width = i;
        this.height = i2;
        this.viewWidth = i3;
        this.viewHeight = i4;
        this.rgbDataLength = j;
        this.orientation = i5;
        this.rotation = i6;
        this.data = bArr;
        this.isFrontFacingCam = z;
        this.isCapturedFramePreviewed = z2;
    }

    public CameraProperties() {
        this.isCapturedFramePreviewed = false;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getViewWidth() {
        return this.viewWidth;
    }

    public int getViewHeight() {
        return this.viewHeight;
    }

    public long getRgbDataLength() {
        return this.rgbDataLength;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public int getRotation() {
        return this.rotation;
    }

    public byte[] getData() {
        return this.data;
    }

    public boolean isFrontFacingCam() {
        return this.isFrontFacingCam;
    }

    public boolean isCapturedFramePreviewed() {
        return this.isCapturedFramePreviewed;
    }
}
