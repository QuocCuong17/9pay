package org.jmrtd.lds.iso19794;

import com.google.firebase.ktx.BuildConfig;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;
import net.sf.scuba.data.Gender;
import org.jmrtd.lds.AbstractImageInfo;
import org.jmrtd.lds.ImageInfo;

/* loaded from: classes6.dex */
public class FaceImageInfo extends AbstractImageInfo {
    public static final short EXPRESSION_EYES_LOOKING_AWAY = 5;
    public static final short EXPRESSION_FROWNING = 7;
    public static final short EXPRESSION_NEUTRAL = 1;
    public static final short EXPRESSION_RAISED_EYEBROWS = 4;
    public static final short EXPRESSION_SMILE_CLOSED = 2;
    public static final short EXPRESSION_SMILE_OPEN = 3;
    public static final short EXPRESSION_SQUINTING = 6;
    public static final short EXPRESSION_UNSPECIFIED = 0;
    public static final int EYE_COLOR_BLACK = 1;
    public static final int EYE_COLOR_BLUE = 2;
    public static final int EYE_COLOR_BROWN = 3;
    public static final int EYE_COLOR_GRAY = 4;
    public static final int EYE_COLOR_GREEN = 5;
    public static final int EYE_COLOR_MULTI_COLORED = 6;
    public static final int EYE_COLOR_PINK = 7;
    public static final int EYE_COLOR_UNKNOWN = 255;
    public static final int EYE_COLOR_UNSPECIFIED = 0;
    public static final int FACE_IMAGE_TYPE_BASIC = 0;
    public static final int FACE_IMAGE_TYPE_FULL_FRONTAL = 1;
    public static final int FACE_IMAGE_TYPE_TOKEN_FRONTAL = 2;
    private static final int FEATURE_BEARD_FLAG = 8;
    private static final int FEATURE_BLINK_FLAG = 32;
    private static final int FEATURE_DARK_GLASSES = 512;
    private static final int FEATURE_DISTORTING_MEDICAL_CONDITION = 1024;
    private static final int FEATURE_FEATURES_ARE_SPECIFIED_FLAG = 1;
    private static final int FEATURE_GLASSES_FLAG = 2;
    private static final int FEATURE_LEFT_EYE_PATCH_FLAG = 128;
    private static final int FEATURE_MOUSTACHE_FLAG = 4;
    private static final int FEATURE_MOUTH_OPEN_FLAG = 64;
    private static final int FEATURE_RIGHT_EYE_PATCH = 256;
    private static final int FEATURE_TEETH_VISIBLE_FLAG = 16;
    public static final int HAIR_COLOR_BALD = 1;
    public static final int HAIR_COLOR_BLACK = 2;
    public static final int HAIR_COLOR_BLONDE = 3;
    public static final int HAIR_COLOR_BLUE = 9;
    public static final int HAIR_COLOR_BROWN = 4;
    public static final int HAIR_COLOR_GRAY = 5;
    public static final int HAIR_COLOR_GREEN = 8;
    public static final int HAIR_COLOR_RED = 7;
    public static final int HAIR_COLOR_UNKNOWN = 255;
    public static final int HAIR_COLOR_UNSPECIFIED = 0;
    public static final int HAIR_COLOR_WHITE = 6;
    public static final int IMAGE_COLOR_SPACE_GRAY8 = 3;
    public static final int IMAGE_COLOR_SPACE_OTHER = 4;
    public static final int IMAGE_COLOR_SPACE_RGB24 = 1;
    public static final int IMAGE_COLOR_SPACE_UNSPECIFIED = 0;
    public static final int IMAGE_COLOR_SPACE_YUV422 = 2;
    public static final int IMAGE_DATA_TYPE_JPEG = 0;
    public static final int IMAGE_DATA_TYPE_JPEG2000 = 1;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final int PITCH = 1;
    private static final int ROLL = 2;
    public static final int SOURCE_TYPE_STATIC_PHOTO_DIGITAL_CAM = 2;
    public static final int SOURCE_TYPE_STATIC_PHOTO_SCANNER = 3;
    public static final int SOURCE_TYPE_STATIC_PHOTO_UNKNOWN_SOURCE = 1;
    public static final int SOURCE_TYPE_UNKNOWN = 7;
    public static final int SOURCE_TYPE_UNSPECIFIED = 0;
    public static final int SOURCE_TYPE_VIDEO_FRAME_ANALOG_CAM = 5;
    public static final int SOURCE_TYPE_VIDEO_FRAME_DIGITAL_CAM = 6;
    public static final int SOURCE_TYPE_VIDEO_FRAME_UNKNOWN_SOURCE = 4;
    private static final int YAW = 0;
    private static final long serialVersionUID = -1751069410327594067L;
    private int colorSpace;
    private int deviceType;
    private int expression;
    private EyeColor eyeColor;
    private int faceImageType;
    private int featureMask;
    private FeaturePoint[] featurePoints;
    private Gender gender;
    private int hairColor;
    private int imageDataType;
    private int[] poseAngle;
    private int[] poseAngleUncertainty;
    private int quality;
    private long recordLength;
    private int sourceType;

    /* loaded from: classes6.dex */
    public enum Expression {
        UNSPECIFIED,
        NEUTRAL,
        SMILE_CLOSED,
        SMILE_OPEN,
        RAISED_EYEBROWS,
        EYES_LOOKING_AWAY,
        SQUINTING,
        FROWNING
    }

    /* loaded from: classes6.dex */
    public enum FaceImageType {
        BASIC,
        FULL_FRONTAL,
        TOKEN_FRONTAL
    }

    /* loaded from: classes6.dex */
    public enum Features {
        FEATURES_ARE_SPECIFIED,
        GLASSES,
        MOUSTACHE,
        BEARD,
        TEETH_VISIBLE,
        BLINK,
        MOUTH_OPEN,
        LEFT_EYE_PATCH,
        RIGHT_EYE_PATCH,
        DARK_GLASSES,
        DISTORTING_MEDICAL_CONDITION
    }

    /* loaded from: classes6.dex */
    public enum ImageColorSpace {
        UNSPECIFIED,
        RGB24,
        YUV422,
        GRAY8,
        OTHER
    }

    /* loaded from: classes6.dex */
    public enum ImageDataType {
        TYPE_JPEG,
        TYPE_JPEG2000
    }

    /* loaded from: classes6.dex */
    public enum SourceType {
        UNSPECIFIED,
        STATIC_PHOTO_UNKNOWN_SOURCE,
        STATIC_PHOTO_DIGITAL_CAM,
        STATIC_PHOTO_SCANNER,
        VIDEO_FRAME_UNKNOWN_SOURCE,
        VIDEO_FRAME_ANALOG_CAM,
        VIDEO_FRAME_DIGITAL_CAM,
        UNKNOWN
    }

    /* loaded from: classes6.dex */
    public enum EyeColor {
        UNSPECIFIED(0),
        BLACK(1),
        BLUE(2),
        BROWN(3),
        GRAY(4),
        GREEN(5),
        MULTI_COLORED(6),
        PINK(7),
        UNKNOWN(255);

        private int code;

        EyeColor(int i) {
            this.code = i;
        }

        public int toInt() {
            return this.code;
        }

        static EyeColor toEyeColor(int i) {
            for (EyeColor eyeColor : values()) {
                if (eyeColor.toInt() == i) {
                    return eyeColor;
                }
            }
            return UNKNOWN;
        }
    }

    /* loaded from: classes6.dex */
    public enum HairColor {
        UNSPECIFIED(0),
        BALD(1),
        BLACK(2),
        BLONDE(3),
        BROWN(4),
        GRAY(5),
        WHITE(6),
        RED(7),
        GREEN(8),
        BLUE(9),
        UNKNOWN(255);

        private int code;

        HairColor(int i) {
            this.code = i;
        }

        public int toInt() {
            return this.code;
        }

        static HairColor toHairColor(int i) {
            for (HairColor hairColor : values()) {
                if (hairColor.toInt() == i) {
                    return hairColor;
                }
            }
            return UNKNOWN;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public FaceImageInfo(Gender gender, EyeColor eyeColor, int i, int i2, int i3, int[] iArr, int[] iArr2, int i4, int i5, int i6, int i7, int i8, FeaturePoint[] featurePointArr, int i9, int i10, InputStream inputStream, int i11, int i12) throws IOException {
        super(0, i9, i10, inputStream, r13, toMimeType(i12));
        long j = i11;
        if (inputStream == null) {
            throw new IllegalArgumentException("Null image");
        }
        this.gender = gender == null ? Gender.UNSPECIFIED : gender;
        this.eyeColor = eyeColor == null ? EyeColor.UNSPECIFIED : eyeColor;
        this.featureMask = i;
        this.hairColor = i2;
        this.expression = i3;
        this.colorSpace = i5;
        this.sourceType = i6;
        this.deviceType = i7;
        int length = featurePointArr == null ? 0 : featurePointArr.length;
        FeaturePoint[] featurePointArr2 = new FeaturePoint[length];
        this.featurePoints = featurePointArr2;
        if (length > 0) {
            System.arraycopy(featurePointArr, 0, featurePointArr2, 0, length);
        }
        int[] iArr3 = new int[3];
        this.poseAngle = iArr3;
        System.arraycopy(iArr, 0, iArr3, 0, 3);
        int[] iArr4 = new int[3];
        this.poseAngleUncertainty = iArr4;
        System.arraycopy(iArr2, 0, iArr4, 0, 3);
        this.imageDataType = i12;
        this.recordLength = (length * 8) + 20 + 12 + j;
        this.faceImageType = i4;
        this.colorSpace = i5;
        this.sourceType = i6;
        this.deviceType = i7;
        this.quality = i8;
    }

    public FaceImageInfo(InputStream inputStream) throws IOException {
        super(0);
        readObject(inputStream);
    }

    @Override // org.jmrtd.lds.AbstractImageInfo
    protected void readObject(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = inputStream instanceof DataInputStream ? (DataInputStream) inputStream : new DataInputStream(inputStream);
        this.recordLength = dataInputStream.readInt() & 4294967295L;
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        this.gender = Gender.getInstance(dataInputStream.readUnsignedByte());
        this.eyeColor = EyeColor.toEyeColor(dataInputStream.readUnsignedByte());
        this.hairColor = dataInputStream.readUnsignedByte();
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        this.featureMask = readUnsignedByte;
        this.featureMask = (readUnsignedByte << 16) | dataInputStream.readUnsignedShort();
        this.expression = dataInputStream.readShort();
        this.poseAngle = new int[3];
        this.poseAngle[0] = dataInputStream.readUnsignedByte();
        this.poseAngle[1] = dataInputStream.readUnsignedByte();
        this.poseAngle[2] = dataInputStream.readUnsignedByte();
        this.poseAngleUncertainty = r2;
        int[] iArr = {dataInputStream.readUnsignedByte()};
        this.poseAngleUncertainty[1] = dataInputStream.readUnsignedByte();
        this.poseAngleUncertainty[2] = dataInputStream.readUnsignedByte();
        this.featurePoints = new FeaturePoint[readUnsignedShort];
        for (int i = 0; i < readUnsignedShort; i++) {
            int readUnsignedByte2 = dataInputStream.readUnsignedByte();
            byte readByte = dataInputStream.readByte();
            int readUnsignedShort2 = dataInputStream.readUnsignedShort();
            int readUnsignedShort3 = dataInputStream.readUnsignedShort();
            for (long j = 0; j < 2; j += dataInputStream.skip(2L)) {
            }
            this.featurePoints[i] = new FeaturePoint(readUnsignedByte2, readByte, readUnsignedShort2, readUnsignedShort3);
        }
        this.faceImageType = dataInputStream.readUnsignedByte();
        this.imageDataType = dataInputStream.readUnsignedByte();
        setWidth(dataInputStream.readUnsignedShort());
        setHeight(dataInputStream.readUnsignedShort());
        this.colorSpace = dataInputStream.readUnsignedByte();
        this.sourceType = dataInputStream.readUnsignedByte();
        this.deviceType = dataInputStream.readUnsignedShort();
        this.quality = dataInputStream.readUnsignedShort();
        if (getWidth() <= 0) {
            setWidth(800);
        }
        if (getHeight() <= 0) {
            setHeight(600);
        }
        setMimeType(toMimeType(this.imageDataType));
        readImage(inputStream, ((this.recordLength - 20) - (readUnsignedShort * 8)) - 12);
    }

    @Override // org.jmrtd.lds.AbstractImageInfo
    public void writeObject(OutputStream outputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        writeFacialRecordData(byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt((int) (byteArray.length + 4));
        dataOutputStream.write(byteArray);
        dataOutputStream.flush();
    }

    @Override // org.jmrtd.lds.ImageInfo
    public long getRecordLength() {
        return this.recordLength;
    }

    public FeaturePoint[] getFeaturePoints() {
        return this.featurePoints;
    }

    public int getExpression() {
        return this.expression;
    }

    public EyeColor getEyeColor() {
        return this.eyeColor;
    }

    public Gender getGender() {
        return this.gender;
    }

    public int getHairColor() {
        return this.hairColor;
    }

    public int getFaceImageType() {
        return this.faceImageType;
    }

    public int getFeatureMask() {
        return this.featureMask;
    }

    public int getQuality() {
        return this.quality;
    }

    public int getSourceType() {
        return this.sourceType;
    }

    public int getImageDataType() {
        return this.imageDataType;
    }

    public int getColorSpace() {
        return this.colorSpace;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public int[] getPoseAngle() {
        int[] iArr = new int[3];
        System.arraycopy(this.poseAngle, 0, iArr, 0, 3);
        return iArr;
    }

    public int[] getPoseAngleUncertainty() {
        int[] iArr = new int[3];
        System.arraycopy(this.poseAngleUncertainty, 0, iArr, 0, 3);
        return iArr;
    }

    @Override // org.jmrtd.lds.AbstractImageInfo
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FaceImageInfo [");
        sb.append("Image size: ");
        sb.append(getWidth());
        sb.append(" x ");
        sb.append(getHeight());
        sb.append(", ");
        sb.append("Gender: ");
        Gender gender = this.gender;
        if (gender == null) {
            gender = Gender.UNSPECIFIED;
        }
        sb.append(gender);
        sb.append(", ");
        sb.append("Eye color: ");
        EyeColor eyeColor = this.eyeColor;
        if (eyeColor == null) {
            eyeColor = EyeColor.UNSPECIFIED;
        }
        sb.append(eyeColor);
        sb.append(", ");
        sb.append("Hair color: ");
        sb.append(hairColorToString());
        sb.append(", ");
        sb.append("Feature mask: ");
        sb.append(featureMaskToString());
        sb.append(", ");
        sb.append("Expression: ");
        sb.append(expressionToString());
        sb.append(", ");
        sb.append("Pose angle: ");
        sb.append(poseAngleToString());
        sb.append(", ");
        sb.append("Face image type: ");
        sb.append(faceImageTypeToString());
        sb.append(", ");
        sb.append("Source type: ");
        sb.append(sourceTypeToString());
        sb.append(", ");
        sb.append("FeaturePoints [");
        FeaturePoint[] featurePointArr = this.featurePoints;
        if (featurePointArr != null && featurePointArr.length > 0) {
            boolean z = true;
            for (FeaturePoint featurePoint : featurePointArr) {
                if (z) {
                    z = false;
                } else {
                    sb.append(", ");
                }
                sb.append(featurePoint.toString());
            }
        }
        sb.append("]");
        sb.append("]");
        return sb.toString();
    }

    @Override // org.jmrtd.lds.AbstractImageInfo
    public int hashCode() {
        int hashCode = ((((((super.hashCode() * 31) + this.colorSpace) * 31) + this.deviceType) * 31) + this.expression) * 31;
        EyeColor eyeColor = this.eyeColor;
        int hashCode2 = (((((((hashCode + (eyeColor == null ? 0 : eyeColor.hashCode())) * 31) + this.faceImageType) * 31) + this.featureMask) * 31) + Arrays.hashCode(this.featurePoints)) * 31;
        Gender gender = this.gender;
        int hashCode3 = (((((((((((hashCode2 + (gender != null ? gender.hashCode() : 0)) * 31) + this.hairColor) * 31) + this.imageDataType) * 31) + Arrays.hashCode(this.poseAngle)) * 31) + Arrays.hashCode(this.poseAngleUncertainty)) * 31) + this.quality) * 31;
        long j = this.recordLength;
        return ((hashCode3 + ((int) (j ^ (j >>> 32)))) * 31) + this.sourceType;
    }

    @Override // org.jmrtd.lds.AbstractImageInfo
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        FaceImageInfo faceImageInfo = (FaceImageInfo) obj;
        return this.colorSpace == faceImageInfo.colorSpace && this.deviceType == faceImageInfo.deviceType && this.expression == faceImageInfo.expression && this.eyeColor == faceImageInfo.eyeColor && this.faceImageType == faceImageInfo.faceImageType && this.featureMask == faceImageInfo.featureMask && Arrays.equals(this.featurePoints, faceImageInfo.featurePoints) && this.gender == faceImageInfo.gender && this.hairColor == faceImageInfo.hairColor && this.imageDataType == faceImageInfo.imageDataType && Arrays.equals(this.poseAngle, faceImageInfo.poseAngle) && Arrays.equals(this.poseAngleUncertainty, faceImageInfo.poseAngleUncertainty) && this.quality == faceImageInfo.quality && this.recordLength == faceImageInfo.recordLength && this.sourceType == faceImageInfo.sourceType;
    }

    private void writeFacialRecordData(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeShort(this.featurePoints.length);
        Gender gender = this.gender;
        if (gender == null) {
            gender = Gender.UNSPECIFIED;
        }
        dataOutputStream.writeByte(gender.toInt());
        EyeColor eyeColor = this.eyeColor;
        if (eyeColor == null) {
            eyeColor = EyeColor.UNSPECIFIED;
        }
        dataOutputStream.writeByte(eyeColor.toInt());
        dataOutputStream.writeByte(this.hairColor);
        dataOutputStream.writeByte((byte) ((this.featureMask & 16711680) >> 16));
        dataOutputStream.writeByte((byte) ((this.featureMask & 65280) >> 8));
        dataOutputStream.writeByte((byte) (this.featureMask & 255));
        dataOutputStream.writeShort(this.expression);
        for (int i = 0; i < 3; i++) {
            dataOutputStream.writeByte(this.poseAngle[i]);
        }
        for (int i2 = 0; i2 < 3; i2++) {
            dataOutputStream.writeByte(this.poseAngleUncertainty[i2]);
        }
        for (FeaturePoint featurePoint : this.featurePoints) {
            dataOutputStream.writeByte(featurePoint.getType());
            dataOutputStream.writeByte((featurePoint.getMajorCode() << 4) | featurePoint.getMinorCode());
            dataOutputStream.writeShort(featurePoint.getX());
            dataOutputStream.writeShort(featurePoint.getY());
            dataOutputStream.writeShort(0);
        }
        dataOutputStream.writeByte(this.faceImageType);
        dataOutputStream.writeByte(this.imageDataType);
        dataOutputStream.writeShort(getWidth());
        dataOutputStream.writeShort(getHeight());
        dataOutputStream.writeByte(this.colorSpace);
        dataOutputStream.writeByte(this.sourceType);
        dataOutputStream.writeShort(this.deviceType);
        dataOutputStream.writeShort(this.quality);
        writeImage(dataOutputStream);
        dataOutputStream.flush();
        dataOutputStream.close();
    }

    private String hairColorToString() {
        switch (this.hairColor) {
            case 0:
                return BuildConfig.VERSION_NAME;
            case 1:
                return "bald";
            case 2:
                return "black";
            case 3:
                return "blonde";
            case 4:
                return "brown";
            case 5:
                return "gray";
            case 6:
                return "white";
            case 7:
                return "red";
            case 8:
                return "green";
            case 9:
                return "blue";
            default:
                return "unknown";
        }
    }

    private String featureMaskToString() {
        if ((this.featureMask & 1) == 0) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        if ((this.featureMask & 2) != 0) {
            arrayList.add("glasses");
        }
        if ((this.featureMask & 4) != 0) {
            arrayList.add("moustache");
        }
        if ((this.featureMask & 8) != 0) {
            arrayList.add("beard");
        }
        if ((this.featureMask & 16) != 0) {
            arrayList.add("teeth visible");
        }
        if ((this.featureMask & 32) != 0) {
            arrayList.add("blink");
        }
        if ((this.featureMask & 64) != 0) {
            arrayList.add("mouth open");
        }
        if ((this.featureMask & 128) != 0) {
            arrayList.add("left eye patch");
        }
        if ((this.featureMask & 256) != 0) {
            arrayList.add("right eye patch");
        }
        if ((this.featureMask & 512) != 0) {
            arrayList.add("dark glasses");
        }
        if ((this.featureMask & 1024) != 0) {
            arrayList.add("distorting medical condition (which could impact feature point detection)");
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    private String expressionToString() {
        switch (this.expression) {
            case 0:
                return BuildConfig.VERSION_NAME;
            case 1:
                return "neutral (non-smiling) with both eyes open and mouth closed";
            case 2:
                return "a smile where the inside of the mouth and/or teeth is not exposed (closed jaw)";
            case 3:
                return "a smile where the inside of the mouth and/or teeth is exposed";
            case 4:
                return "raised eyebrows";
            case 5:
                return "eyes looking away from the camera";
            case 6:
                return "squinting";
            case 7:
                return "frowning";
            default:
                return "unknown";
        }
    }

    private String poseAngleToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append("y: ");
        sb.append(this.poseAngle[0]);
        if (this.poseAngleUncertainty[0] != 0) {
            sb.append(" (");
            sb.append(this.poseAngleUncertainty[0]);
            sb.append(")");
        }
        sb.append(", ");
        sb.append("p:");
        sb.append(this.poseAngle[1]);
        if (this.poseAngleUncertainty[1] != 0) {
            sb.append(" (");
            sb.append(this.poseAngleUncertainty[1]);
            sb.append(")");
        }
        sb.append(", ");
        sb.append("r: ");
        sb.append(this.poseAngle[2]);
        if (this.poseAngleUncertainty[2] != 0) {
            sb.append(" (");
            sb.append(this.poseAngleUncertainty[2]);
            sb.append(")");
        }
        sb.append(")");
        return sb.toString();
    }

    private String faceImageTypeToString() {
        int i = this.faceImageType;
        return i != 0 ? i != 1 ? i != 2 ? "unknown" : "token frontal" : "full frontal" : "basic";
    }

    private String sourceTypeToString() {
        switch (this.sourceType) {
            case 0:
                return BuildConfig.VERSION_NAME;
            case 1:
                return "static photograph from an unknown source";
            case 2:
                return "static photograph from a digital still-image camera";
            case 3:
                return "static photograph from a scanner";
            case 4:
                return "single video frame from an unknown source";
            case 5:
                return "single video frame from an analogue camera";
            case 6:
                return "single video frame from a digital camera";
            default:
                return "unknown";
        }
    }

    private static String toMimeType(int i) {
        if (i == 0) {
            return "image/jpeg";
        }
        if (i == 1) {
            return ImageInfo.JPEG2000_MIME_TYPE;
        }
        LOGGER.warning("Unknown image type: " + i);
        return null;
    }

    /* loaded from: classes6.dex */
    public static class FeaturePoint implements Serializable {
        private static final long serialVersionUID = -4209679423938065215L;
        private int majorCode;
        private int minorCode;
        private int type;
        private int x;
        private int y;

        public FeaturePoint(int i, int i2, int i3, int i4, int i5) {
            this.type = i;
            this.majorCode = i2;
            this.minorCode = i3;
            this.x = i4;
            this.y = i5;
        }

        FeaturePoint(int i, byte b, int i2, int i3) {
            this(i, (b & 240) >> 4, b & 15, i2, i3);
        }

        public int getMajorCode() {
            return this.majorCode;
        }

        public int getMinorCode() {
            return this.minorCode;
        }

        public int getType() {
            return this.type;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public String toString() {
            return "( point: " + getMajorCode() + "." + getMinorCode() + ", type: " + Integer.toHexString(this.type) + ", (" + this.x + ", " + this.y + "))";
        }
    }
}
