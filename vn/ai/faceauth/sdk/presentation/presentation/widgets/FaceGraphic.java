package vn.ai.faceauth.sdk.presentation.presentation.widgets;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceContour;
import com.google.mlkit.vision.face.FaceLandmark;
import io.grpc.internal.GrpcUtil;
import java.util.Iterator;
import java.util.Locale;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.presentation.presentation.widgets.GraphicOverlay;

/* loaded from: classes6.dex */
public class FaceGraphic extends GraphicOverlay.Graphic {
    private static float BOX_STROKE_WIDTH;
    private static final int[][] COLORS;
    private static float FACE_POSITION_RADIUS;
    private static float ID_TEXT_SIZE;
    private static float ID_Y_OFFSET;
    private static int NUM_COLORS;
    private final Paint[] boxPaints;
    private volatile Face face;
    private final Paint facePositionPaint;
    private final Paint[] idPaints;
    private final Paint[] labelPaints;

    static {
        tfwhgw.rnl(FaceGraphic.class, 536, 540);
        COLORS = new int[][]{new int[]{-16777216, -1}, new int[]{-1, -65281}, new int[]{-16777216, -3355444}, new int[]{-1, SupportMenu.CATEGORY_MASK}, new int[]{-1, -16776961}, new int[]{-1, -12303292}, new int[]{-16777216, -16711681}, new int[]{-16777216, InputDeviceCompat.SOURCE_ANY}, new int[]{-1, -16777216}, new int[]{-16777216, -16711936}};
    }

    public FaceGraphic(GraphicOverlay graphicOverlay, Face face) {
        super(graphicOverlay);
        this.face = face;
        Paint paint = new Paint();
        this.facePositionPaint = paint;
        paint.setColor(-1);
        int length = COLORS.length;
        this.idPaints = new Paint[length];
        this.boxPaints = new Paint[length];
        this.labelPaints = new Paint[length];
        for (int i = 0; i < length; i++) {
            this.idPaints[i] = new Paint();
            Paint paint2 = this.idPaints[i];
            int[][] iArr = COLORS;
            paint2.setColor(iArr[i][0]);
            this.idPaints[i].setTextSize(30.0f);
            this.boxPaints[i] = new Paint();
            this.boxPaints[i].setColor(iArr[i][1]);
            this.boxPaints[i].setStyle(Paint.Style.STROKE);
            this.boxPaints[i].setStrokeWidth(5.0f);
            this.labelPaints[i] = new Paint();
            this.labelPaints[i].setColor(iArr[i][1]);
            this.labelPaints[i].setStyle(Paint.Style.FILL);
        }
    }

    private void drawFaceLandmark(Canvas canvas, int i) {
        FaceLandmark landmark = this.face.getLandmark(i);
        if (landmark != null) {
            canvas.drawCircle(translateX(landmark.getPosition().x), translateY(landmark.getPosition().y), 8.0f, this.facePositionPaint);
        }
    }

    @Override // vn.ai.faceauth.sdk.presentation.presentation.widgets.GraphicOverlay.Graphic
    public void draw(Canvas canvas) {
        Canvas canvas2;
        Face face = this.face;
        if (face == null) {
            return;
        }
        float translateX = translateX(face.getBoundingBox().centerX());
        float translateY = translateY(face.getBoundingBox().centerY());
        canvas.drawCircle(translateX, translateY, 8.0f, this.facePositionPaint);
        float scale = translateX - scale(face.getBoundingBox().width() / 2.0f);
        float scale2 = translateY - scale(face.getBoundingBox().height() / 2.0f);
        float scale3 = scale(face.getBoundingBox().width() / 2.0f);
        float scale4 = scale(face.getBoundingBox().height() / 2.0f);
        float f = face.getTrackingId() == null ? 0.0f : -35.0f;
        int abs = face.getTrackingId() == null ? 0 : Math.abs(face.getTrackingId().intValue() % 10);
        Paint paint = this.idPaints[abs];
        String rnigpa = tfwhgw.rnigpa(440);
        float measureText = paint.measureText(rnigpa + face.getTrackingId());
        if (face.getSmilingProbability() != null) {
            f -= 35.0f;
            measureText = Math.max(measureText, this.idPaints[abs].measureText(String.format(Locale.US, tfwhgw.rnigpa(441), face.getSmilingProbability())));
        }
        if (face.getLeftEyeOpenProbability() != null) {
            measureText = Math.max(measureText, this.idPaints[abs].measureText(String.format(Locale.US, tfwhgw.rnigpa(442), face.getLeftEyeOpenProbability())));
            f -= 35.0f;
        }
        if (face.getRightEyeOpenProbability() != null) {
            measureText = Math.max(measureText, this.idPaints[abs].measureText(String.format(Locale.US, tfwhgw.rnigpa(GrpcUtil.DEFAULT_PORT_SSL), face.getRightEyeOpenProbability())));
            f -= 35.0f;
        }
        float f2 = f - 105.0f;
        Paint paint2 = this.idPaints[abs];
        Locale locale = Locale.US;
        canvas.drawRect(scale - 5.0f, scale2 + f2, Math.max(Math.max(Math.max(measureText, paint2.measureText(String.format(locale, tfwhgw.rnigpa(444), Float.valueOf(face.getHeadEulerAngleX())))), this.idPaints[abs].measureText(String.format(locale, tfwhgw.rnigpa(445), Float.valueOf(face.getHeadEulerAngleY())))), this.idPaints[abs].measureText(String.format(locale, tfwhgw.rnigpa(446), Float.valueOf(face.getHeadEulerAngleZ())))) + scale + 10.0f, scale2, this.labelPaints[abs]);
        float f3 = f2 + 30.0f;
        canvas.drawRect(scale, scale2, scale3 + translateX, scale4 + translateY, this.boxPaints[abs]);
        if (face.getTrackingId() != null) {
            canvas2 = canvas;
            canvas2.drawText(rnigpa + face.getTrackingId(), scale, scale2 + f3, this.idPaints[abs]);
            f3 += 35.0f;
        } else {
            canvas2 = canvas;
        }
        Iterator<FaceContour> it = face.getAllContours().iterator();
        while (it.hasNext()) {
            for (PointF pointF : it.next().getPoints()) {
                canvas2.drawCircle(translateX(pointF.x), translateY(pointF.y), 8.0f, this.facePositionPaint);
            }
        }
        Float smilingProbability = face.getSmilingProbability();
        String rnigpa2 = tfwhgw.rnigpa(447);
        if (smilingProbability != null) {
            canvas2.drawText(tfwhgw.rnigpa(448) + String.format(Locale.US, rnigpa2, face.getSmilingProbability()), scale, scale2 + f3, this.idPaints[abs]);
            f3 += 35.0f;
        }
        FaceLandmark landmark = face.getLandmark(4);
        if (face.getLeftEyeOpenProbability() != null) {
            canvas2.drawText(tfwhgw.rnigpa(449) + String.format(Locale.US, rnigpa2, face.getLeftEyeOpenProbability()), scale, scale2 + f3, this.idPaints[abs]);
            f3 += 35.0f;
        }
        if (landmark != null) {
            float translateX2 = translateX(landmark.getPosition().x);
            Paint paint3 = this.idPaints[abs];
            String rnigpa3 = tfwhgw.rnigpa(450);
            float measureText2 = translateX2 - (paint3.measureText(rnigpa3) / 2.0f);
            canvas.drawRect(measureText2 - 5.0f, (translateY(landmark.getPosition().y) + 40.0f) - 30.0f, this.idPaints[abs].measureText(rnigpa3) + measureText2 + 5.0f, translateY(landmark.getPosition().y) + 40.0f + 5.0f, this.labelPaints[abs]);
            canvas2.drawText(rnigpa3, measureText2, translateY(landmark.getPosition().y) + 40.0f, this.idPaints[abs]);
        }
        FaceLandmark landmark2 = face.getLandmark(10);
        if (face.getRightEyeOpenProbability() != null) {
            canvas2.drawText(tfwhgw.rnigpa(451) + String.format(Locale.US, rnigpa2, face.getRightEyeOpenProbability()), scale, scale2 + f3, this.idPaints[abs]);
            f3 += 35.0f;
        }
        if (landmark2 != null) {
            float translateX3 = translateX(landmark2.getPosition().x);
            Paint paint4 = this.idPaints[abs];
            String rnigpa4 = tfwhgw.rnigpa(452);
            float measureText3 = translateX3 - (paint4.measureText(rnigpa4) / 2.0f);
            canvas.drawRect(measureText3 - 5.0f, (translateY(landmark2.getPosition().y) + 40.0f) - 30.0f, this.idPaints[abs].measureText(rnigpa4) + measureText3 + 5.0f, translateY(landmark2.getPosition().y) + 40.0f + 5.0f, this.labelPaints[abs]);
            canvas2.drawText(rnigpa4, measureText3, translateY(landmark2.getPosition().y) + 40.0f, this.idPaints[abs]);
        }
        canvas2.drawText(tfwhgw.rnigpa(453) + face.getHeadEulerAngleX(), scale, scale2 + f3, this.idPaints[abs]);
        float f4 = f3 + 35.0f;
        canvas2.drawText(tfwhgw.rnigpa(454) + face.getHeadEulerAngleY(), scale, scale2 + f4, this.idPaints[abs]);
        canvas2.drawText(tfwhgw.rnigpa(455) + face.getHeadEulerAngleZ(), scale, scale2 + f4 + 35.0f, this.idPaints[abs]);
        drawFaceLandmark(canvas2, 4);
        drawFaceLandmark(canvas2, 10);
        drawFaceLandmark(canvas2, 1);
        drawFaceLandmark(canvas2, 7);
    }
}
