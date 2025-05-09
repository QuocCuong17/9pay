package co.hyperverge.hvcamera.magicfilter.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import co.hyperverge.hvcamera.HVLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

/* loaded from: classes2.dex */
public class OpenGLUtils {
    public static final int NOT_INIT = -1;
    public static final int NO_TEXTURE = -1;
    public static final int ON_DRAWN = 1;
    private static final String TAG = "OpenGLUtils";

    public static int loadTexture(Bitmap bitmap, int i) {
        return loadTexture(bitmap, i, false);
    }

    public static int loadTexture(Bitmap bitmap, int i, boolean z) {
        HVLog.d(TAG, "loadTexture() called with: img = [" + bitmap + "], usedTexId = [" + i + "], recyled = [" + z + "]");
        if (bitmap == null) {
            return -1;
        }
        int[] iArr = new int[1];
        if (i == -1) {
            GLES20.glGenTextures(1, iArr, 0);
            GLES20.glBindTexture(3553, iArr[0]);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLUtils.texImage2D(3553, 0, bitmap, 0);
        } else {
            GLES20.glBindTexture(3553, i);
            GLUtils.texSubImage2D(3553, 0, 0, 0, bitmap);
            iArr[0] = i;
        }
        if (z) {
            bitmap.recycle();
        }
        return iArr[0];
    }

    public static int loadTexture(Buffer buffer, int i, int i2, int i3) {
        HVLog.d(TAG, "loadTexture() called with: data = [" + buffer + "], width = [" + i + "], height = [" + i2 + "], usedTexId = [" + i3 + "]");
        if (buffer == null) {
            return -1;
        }
        int[] iArr = new int[1];
        HVLog.d("Load Program", "load texture 1 ");
        if (i3 == -1) {
            GLES20.glGenTextures(1, iArr, 0);
            GLES20.glBindTexture(3553, iArr[0]);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, buffer);
        } else {
            GLES20.glBindTexture(3553, i3);
            GLES20.glTexSubImage2D(3553, 0, 0, 0, i, i2, 6408, 5121, buffer);
            iArr[0] = i3;
        }
        return iArr[0];
    }

    public static int loadTexture(Buffer buffer, int i, int i2, int i3, int i4) {
        HVLog.d(TAG, "loadTexture() called with: data = [" + buffer + "], width = [" + i + "], height = [" + i2 + "], usedTexId = [" + i3 + "], type = [" + i4 + "]");
        if (buffer == null) {
            return -1;
        }
        int[] iArr = new int[1];
        HVLog.d("Load Program", "load texture 2");
        if (i3 == -1) {
            GLES20.glGenTextures(1, iArr, 0);
            GLES20.glBindTexture(3553, iArr[0]);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, i4, buffer);
        } else {
            GLES20.glBindTexture(3553, i3);
            GLES20.glTexSubImage2D(3553, 0, 0, 0, i, i2, 6408, i4, buffer);
            iArr[0] = i3;
        }
        return iArr[0];
    }

    public static int loadTexture(Context context, String str) {
        HVLog.d(TAG, "loadTexture() called with: context = [" + context + "], name = [" + str + "]");
        int[] iArr = new int[1];
        HVLog.d("Load Program", "load texture 3");
        GLES20.glGenTextures(1, iArr, 0);
        if (iArr[0] != 0) {
            Bitmap imageFromAssetsFile = getImageFromAssetsFile(context, str);
            GLES20.glBindTexture(3553, iArr[0]);
            GLES20.glTexParameteri(3553, 10240, 9729);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            GLUtils.texImage2D(3553, 0, imageFromAssetsFile, 0);
            imageFromAssetsFile.recycle();
        }
        if (iArr[0] == 0) {
            HVLog.e(TAG, "loadTexture: error loading texture");
            throw new RuntimeException("Error loading texture.");
        }
        return iArr[0];
    }

    private static Bitmap getImageFromAssetsFile(Context context, String str) {
        HVLog.d(TAG, "getImageFromAssetsFile() called with: context = [" + context + "], fileName = [" + str + "]");
        Bitmap bitmap = null;
        try {
            InputStream open = context.getResources().getAssets().open(str);
            bitmap = BitmapFactory.decodeStream(open);
            open.close();
            return bitmap;
        } catch (IOException e) {
            HVLog.e(TAG, "getImageFromAssetsFile: " + e.getMessage());
            return bitmap;
        }
    }

    public static int loadProgram(String str, String str2) {
        HVLog.d(TAG, "loadProgram() called with: strVSource = [" + str + "], strFSource = [" + str2 + "]");
        int[] iArr = new int[1];
        int loadShader = loadShader(str, 35633);
        if (loadShader == 0) {
            HVLog.d("Load Program", "Vertex Shader Failed");
            return 0;
        }
        int loadShader2 = loadShader(str2, 35632);
        if (loadShader2 == 0) {
            HVLog.d("Load Program", "Fragment Shader Failed");
            return 0;
        }
        HVLog.d("Load Program", "before create");
        int glCreateProgram = GLES20.glCreateProgram();
        HVLog.d("Load Program", "Attaching shaders");
        GLES20.glAttachShader(glCreateProgram, loadShader);
        HVLog.d("Load Program", "vertex shader attached");
        GLES20.glAttachShader(glCreateProgram, loadShader2);
        HVLog.d("Load Program", "fragment shader attached");
        GLES20.glLinkProgram(glCreateProgram);
        HVLog.d("Load Program", "linking done");
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] <= 0) {
            HVLog.d("Load Program", "Linking Failed");
            HVLog.e("Load Program", GLES20.glGetProgramInfoLog(glCreateProgram));
            return 0;
        }
        GLES20.glDeleteShader(loadShader);
        GLES20.glDeleteShader(loadShader2);
        return glCreateProgram;
    }

    private static int loadShader(String str, int i) {
        HVLog.d(TAG, "loadShader() called with: strSource = [" + str + "], iType = [" + i + "]");
        int[] iArr = new int[1];
        int glCreateShader = GLES20.glCreateShader(i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        HVLog.e(TAG, "loadShader failed: compilation\n" + GLES20.glGetShaderInfoLog(glCreateShader));
        return 0;
    }

    public static int getExternalOESTextureID() {
        HVLog.d(TAG, "getExternalOESTextureID() called");
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(36197, iArr[0]);
        GLES20.glTexParameterf(36197, 10241, 9729.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        return iArr[0];
    }

    public static String readShaderFromRawResource(Context context, int i) {
        HVLog.d(TAG, "readShaderFromRawResource() called with: context = [" + context + "], resourceId = [" + i + "]");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(i)));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append('\n');
                } else {
                    return sb.toString();
                }
            } catch (IOException e) {
                HVLog.e(TAG, "readShaderFromRawResource: " + e.getMessage());
                return null;
            }
        }
    }
}
