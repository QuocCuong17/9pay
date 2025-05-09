package co.hyperverge.facedetection;

/* loaded from: classes2.dex */
public class ImageHolder implements FileInterface {
    private String bucketName;
    private String date;

    /* renamed from: id, reason: collision with root package name */
    private String f47id;
    private String label;
    private String pathOriginal;

    @Override // co.hyperverge.facedetection.FileInterface
    public String getPathOriginal() {
        return this.pathOriginal;
    }

    @Override // co.hyperverge.facedetection.FileInterface
    public String getLabel() {
        return this.label;
    }

    public ImageHolder(String str, String str2, String str3, String str4) {
        this.f47id = str;
        this.pathOriginal = str2;
        this.bucketName = str3;
        this.date = str4;
        this.label = str;
    }

    public long getTimeStamp() throws NumberFormatException {
        return Long.parseLong(this.date);
    }
}
