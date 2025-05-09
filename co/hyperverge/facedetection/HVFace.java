package co.hyperverge.facedetection;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HVFace {
    public static final String COLUMN_FACE_CROP_PATH = "c_face_crop_path";
    public static final String COLUMN_FACE_LABEL = "c_face_label";
    public static final String COLUMN_FACE_LOCATION = "c_face_location";
    public static final String COLUMN_FILE_LABEL = "c_file_id";
    public static final String COLUMN_FILE_PATH = "c_file_path";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS table_face_objects(c_face_label TEXT PRIMARY KEY,c_file_id TEXT,c_face_location TEXT,c_file_path TEXT,c_face_crop_path TEXT)";
    public static final String HEIGHT = "height";
    public static final String LEFT_TOP_X = "ltx";
    public static final String LEFT_TOP_Y = "lty";
    public static final String LOG_TAG = "co.hyperverge.facedetection.HVFace";
    public static final String RIGHT_BOTTOM_X = "rbx";
    public static final String RIGHT_BOTTOM_Y = "rby";
    public static final String TABLE_NAME = "table_face_objects";
    public static final String WIDTH = "width";
    private String faceCropPath;
    private String faceId;
    private String faceLabel;
    private String faceLocation;
    private String fileLabel;
    private String filePath;

    public ContentValues getCV() {
        ContentValues contentValues = new ContentValues();
        String str = this.faceLabel;
        if (str != null) {
            contentValues.put("c_face_label", str);
        }
        String str2 = this.fileLabel;
        if (str2 != null) {
            contentValues.put("c_file_id", str2);
        }
        String str3 = this.faceLocation;
        if (str3 != null) {
            contentValues.put("c_face_location", str3);
        }
        String str4 = this.filePath;
        if (str4 != null) {
            contentValues.put("c_file_path", str4);
        }
        String str5 = this.faceCropPath;
        if (str5 != null) {
            contentValues.put("c_face_crop_path", str5);
        }
        return contentValues;
    }

    public void parseCV(ContentValues contentValues) {
        this.faceLabel = contentValues.getAsString("c_face_label");
        this.fileLabel = contentValues.getAsString("c_file_id");
        this.faceLocation = contentValues.getAsString("c_face_location");
        this.filePath = contentValues.getAsString("c_file_path");
        this.faceCropPath = contentValues.getAsString("c_face_crop_path");
    }

    public HVFace(String str, String str2) {
        this.faceLabel = str;
        this.filePath = str2;
    }

    public HVFace(Cursor cursor) {
        this.faceLabel = cursor.getString(cursor.getColumnIndex("c_face_label"));
        this.fileLabel = cursor.getString(cursor.getColumnIndex("c_file_id"));
        this.faceLocation = cursor.getString(cursor.getColumnIndex("c_face_location"));
        this.filePath = cursor.getString(cursor.getColumnIndex("c_file_path"));
    }

    public HVFace setFileLabel(String str) {
        this.fileLabel = str;
        return this;
    }

    public HVFace setFaceLocation(JSONObject jSONObject) throws JSONException {
        this.faceLocation = jSONObject.toString();
        return this;
    }

    public void setFaceCropPath(String str) {
        this.faceCropPath = str;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getFaceLabel() {
        return this.faceLabel;
    }

    public String getFaceLocation() {
        return this.faceLocation;
    }

    public String getFileLabel() {
        return this.fileLabel;
    }

    public String getFaceCropPath() {
        return this.faceCropPath;
    }

    public JSONObject getServerJSON() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("faceLabel", this.faceLabel);
        jSONObject.put("faceLocation", this.faceLocation);
        return jSONObject;
    }

    public int getActualLeftTopX(int i, int i2) {
        return (i * i2) / 100;
    }

    public int getActualLeftTopY(int i, int i2) {
        return (i * i2) / 100;
    }

    public int getActualWidth(int i, int i2) {
        return (i * i2) / 100;
    }

    public int getActualHeight(int i, int i2) {
        return (i * i2) / 100;
    }

    public void setFaceLocation(float f, float f2, float f3, float f4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ltx", f);
            jSONObject.put("lty", f2);
            jSONObject.put("width", f3 - f);
            jSONObject.put("height", f4 - f2);
            jSONObject.put("rbx", f3);
            jSONObject.put("rby", f4);
        } catch (JSONException e) {
            Log.d(LOG_TAG, e.getMessage());
        }
        this.faceLocation = jSONObject.toString();
    }

    public String getFaceId() {
        return this.faceId;
    }

    public void setFaceId(String str) {
        this.faceId = str;
    }
}
