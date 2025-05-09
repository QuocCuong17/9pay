package co.hyperverge.hypersnapsdk.helpers;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import androidx.media3.exoplayer.DefaultLoadControl;
import co.hyperverge.hypersnapsdk.analytics.mixpanel.Keys;
import co.hyperverge.hypersnapsdk.model.ImageComparisonObj;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import co.hyperverge.hypersnapsdk.utils.threading.ThreadExecutor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ImageComparisonHelper {
    private static final int BLOCK_SIZE = 8;
    public static final int RESIZE_CONSTANT = 4;
    private static final String TAG = "ImageComparisonHelper";
    private static ImageComparisonHelper imageComparisonInstance;
    long totalPixels;
    private final int PATCH_SIZE = 50;
    private final int END_TIMER = 10000;
    private final int FRAME_INTERVAL = 10;
    private final int FRAME_DATA_LENGTH_COUNTER = 10;
    int frameNumber = 0;
    private byte[] previousFrame = new byte[DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS];
    private byte[] currentFrame = new byte[DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS];
    ImageComparisonObj imageComparisonObj = new ImageComparisonObj();
    AtomicBoolean jobDone = new AtomicBoolean(false);

    ImageComparisonHelper() {
    }

    public static ImageComparisonHelper get() {
        if (imageComparisonInstance == null) {
            imageComparisonInstance = new ImageComparisonHelper();
        }
        return imageComparisonInstance;
    }

    private static double performChiSquared(int[] iArr, int[] iArr2) {
        double d = 0.0d;
        for (int i = 0; i < iArr.length; i++) {
            double pow = Math.pow(iArr[i] - iArr2[i], 2.0d);
            double d2 = iArr[i] + iArr2[i];
            if (d2 > 0.0d) {
                d += pow / d2;
            }
        }
        return d * 0.5d;
    }

    public void destroy() {
        HVLogUtils.d(TAG, "destroy() called");
        imageComparisonInstance = null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x00ad, code lost:
    
        if (r9.jobDone.get() != false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x00b8, code lost:
    
        if ((java.lang.System.currentTimeMillis() - r3) <= androidx.media3.exoplayer.Renderer.DEFAULT_DURATION_TO_PROGRESS_US) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x00ba, code lost:
    
        r0 = new org.json.JSONArray();
        r0.put(round(r9.imageComparisonObj.getRedChannelDistance()));
        r0.put(round(r9.imageComparisonObj.getGreenChannelDistance()));
        r0.put(round(r9.imageComparisonObj.getBlueChannelDistance()));
        r1.put("frameDiffs", new org.json.JSONArray((java.util.Collection) r9.imageComparisonObj.getFrameDistanceValue()));
        r1.put("frameDataLength", new org.json.JSONArray((java.util.Collection) r9.imageComparisonObj.getFrameDataLength()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x010c, code lost:
    
        if (r9.jobDone.get() == false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x010e, code lost:
    
        r1.put("channelDiffs", r0);
        r1.put("blocksDiff", r9.imageComparisonObj.getSimilarityScore());
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x011f, code lost:
    
        okhttp3.RequestBody.create(okhttp3.MediaType.parse(androidx.webkit.internal.AssetHelper.DEFAULT_MIME_TYPE), r1.toString());
        r10.put("captureData", r1.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x00a5, code lost:
    
        if (co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig.getInstance().isFaceDetectionOn() != false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void addRequestBodyParams(JSONObject jSONObject, HVFaceConfig hVFaceConfig) {
        HVLogUtils.d(TAG, "addRequestBodyParams() called with: params = [" + jSONObject + "], faceConfig = [" + hVFaceConfig + "]");
        if (SDKInternalConfig.getInstance() == null || !SDKInternalConfig.getInstance().isShouldDoImageInjectionChecks()) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("isEmulator", Utils.isEmulator());
                jSONObject2.put(Keys.IMAGE_SIZE, String.valueOf(this.imageComparisonObj.getImageWidth()) + "X" + String.valueOf(this.imageComparisonObj.getImageHeight()));
                jSONObject2.put("cameraCaptureSize", String.valueOf(this.imageComparisonObj.getCaptureWidth()) + "X" + String.valueOf(this.imageComparisonObj.getCaptureHeight()));
            } catch (Exception e) {
                HVLogUtils.e(TAG, "addRequestBodyParams(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        } finally {
            resetVariables();
        }
    }

    private void resetVariables() {
        HVLogUtils.d(TAG, "resetVariables() called");
        this.imageComparisonObj.getFrameDistanceValue().clear();
        this.imageComparisonObj.getFrameDataLength().clear();
        this.jobDone.set(false);
        this.frameNumber = 0;
    }

    public double round(float f) {
        HVLogUtils.d(TAG, "round() called with: value = [" + f + "]");
        double round = ((double) Math.round(((double) f) * 100.0d)) / 100.0d;
        HVLogUtils.d(TAG, "round() returned: " + round);
        return round;
    }

    public void setPictureSize(int i, int i2) {
        HVLogUtils.d(TAG, "setPictureSize() called with: width = [" + i + "], height = [" + i2 + "]");
        this.imageComparisonObj.setCaptureWidth(i);
        this.imageComparisonObj.setCaptureHeight(i2);
    }

    public void setImageSize(int i, int i2) {
        HVLogUtils.d(TAG, "setImageSize() called with: width = [" + i + "], height = [" + i2 + "]");
        this.imageComparisonObj.setImageWidth(i);
        this.imageComparisonObj.setImageHeight(i2);
    }

    public void performHistogramComparison(Bitmap bitmap, Bitmap bitmap2) {
        HVLogUtils.d(TAG, "performHistogramComparison() called with: previousFrameBitmap = [" + bitmap + "], resizedSavePictureBitmap = [" + bitmap2 + "]");
        try {
            List<Float> calculateDistance = calculateDistance(getHistogram(bitmap), getHistogram(bitmap2));
            if (calculateDistance == null || calculateDistance.isEmpty()) {
                return;
            }
            this.imageComparisonObj.setRedChannelDistance(calculateDistance.get(0).floatValue());
            this.imageComparisonObj.setGreenChannelDistance(calculateDistance.get(1).floatValue());
            this.imageComparisonObj.setBlueChannelDistance(calculateDistance.get(2).floatValue());
        } catch (Exception e) {
            HVLogUtils.e(TAG, "performHistogramComparison(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    public List<Integer[]> getHistogram(Bitmap bitmap) {
        HVLogUtils.d(TAG, "getHistogram() called with: bitmap = [" + bitmap + "]");
        ArrayList arrayList = new ArrayList();
        try {
            Integer[] numArr = new Integer[256];
            Arrays.fill((Object[]) numArr, (Object) 0);
            Integer[] numArr2 = new Integer[256];
            Arrays.fill((Object[]) numArr2, (Object) 0);
            Integer[] numArr3 = new Integer[256];
            Arrays.fill((Object[]) numArr3, (Object) 0);
            this.totalPixels = bitmap.getWidth() * bitmap.getHeight();
            int width = bitmap.getWidth() * bitmap.getHeight();
            int[] iArr = new int[width];
            bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
            for (int i = 0; i < width; i++) {
                int i2 = iArr[i];
                int red = Color.red(i2);
                int green = Color.green(i2);
                int blue = Color.blue(i2);
                Integer num = numArr[red];
                numArr[red] = Integer.valueOf(numArr[red].intValue() + 1);
                Integer num2 = numArr2[green];
                numArr2[green] = Integer.valueOf(numArr2[green].intValue() + 1);
                Integer num3 = numArr3[blue];
                numArr3[blue] = Integer.valueOf(numArr3[blue].intValue() + 1);
            }
            arrayList.add(normalizeBins(numArr));
            arrayList.add(normalizeBins(numArr2));
            arrayList.add(normalizeBins(numArr3));
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        return arrayList;
    }

    private Integer[] normalizeBins(Integer[] numArr) {
        try {
            Integer[] numArr2 = new Integer[256];
            Arrays.fill((Object[]) numArr2, (Object) 0);
            float floatValue = Float.valueOf(((Integer) Collections.min(Arrays.asList(numArr))).intValue()).floatValue();
            float floatValue2 = Float.valueOf(((Integer) Collections.max(Arrays.asList(numArr))).intValue()).floatValue();
            for (int i = 0; i < numArr.length; i++) {
                numArr2[i] = Integer.valueOf(Math.round((((numArr[i].intValue() - floatValue) * 255.0f) / (floatValue2 - floatValue)) + 0.0f));
            }
            return numArr2;
        } catch (Exception e) {
            HVLogUtils.e(TAG, "normalizeBins(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
            return numArr;
        }
    }

    public List<Float> calculateDistance(List<Integer[]> list, List<Integer[]> list2) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            try {
                arrayList.add(Float.valueOf(performHellinger(list.get(i), list2.get(i))));
            } catch (Exception e) {
                HVLogUtils.e(TAG, "calculateDistance(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
        return arrayList;
    }

    private float performHellinger(Integer[] numArr, Integer[] numArr2) {
        float f = 0.0f;
        for (int i = 0; i < numArr.length; i++) {
            try {
                f = (float) (f + Math.pow(sqrt(numArr[i].intValue()) - sqrt(numArr2[i].intValue()), 2.0d));
            } catch (Exception e) {
                HVLogUtils.e(TAG, "performHellinger(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
                return 0.0f;
            }
        }
        return (1.0f / sqrt(2.0f)) * sqrt(f);
    }

    public float sqrt(float f) {
        if (f > 0.0f) {
            return (float) Math.sqrt(f);
        }
        return 0.0f;
    }

    private double calculateAverageDistance(List<Double> list) {
        double d = 0.0d;
        if (list == null || list.size() <= 0) {
            return 0.0d;
        }
        Iterator<Double> it = list.iterator();
        while (it.hasNext()) {
            d += it.next().doubleValue();
        }
        return d / list.size();
    }

    private int calculateEuclideanDistance(byte[] bArr, byte[] bArr2) {
        double d = 0.0d;
        for (int i = 0; i < bArr.length; i++) {
            d += Math.pow(bArr[i] - bArr2[i], 2.0d);
        }
        return (int) Math.sqrt(d);
    }

    public void compareConsecutiveFrames(byte[] bArr, long j) {
        if (SDKInternalConfig.getInstance() == null || !SDKInternalConfig.getInstance().isShouldDoImageInjectionChecks()) {
            return;
        }
        try {
            if (this.frameNumber == 0) {
                this.previousFrame = Arrays.copyOfRange(bArr, 0, 50);
            }
            this.imageComparisonObj.getFrameDataLength().set(this.frameNumber % 10, Long.valueOf(j));
            int i = this.frameNumber + 1;
            this.frameNumber = i;
            if (i % 10 == 0) {
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 50);
                this.currentFrame = copyOfRange;
                this.imageComparisonObj.getFrameDistanceValue().add(Integer.valueOf(calculateEuclideanDistance(copyOfRange, this.previousFrame)));
                byte[] bArr2 = this.currentFrame;
                this.previousFrame = Arrays.copyOf(bArr2, bArr2.length);
            }
        } catch (Exception e) {
            HVLogUtils.e(TAG, "compareConsecutiveFrames(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    public void runComparisonMethods(final Bitmap bitmap, final Bitmap bitmap2) {
        HVLogUtils.d(TAG, "runComparisonMethods() called with: previousFrameBitmap = [" + bitmap + "], savePictureBitmap = [" + bitmap2 + "]");
        ThreadExecutor.getInstance().execute(new Runnable() { // from class: co.hyperverge.hypersnapsdk.helpers.ImageComparisonHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ImageComparisonHelper.this.m515x927d3415(bitmap, bitmap2);
            }
        });
    }

    /* renamed from: lambda$runComparisonMethods$0$co-hyperverge-hypersnapsdk-helpers-ImageComparisonHelper, reason: not valid java name */
    public /* synthetic */ void m515x927d3415(Bitmap bitmap, Bitmap bitmap2) {
        Bitmap makeBlocks;
        Bitmap makeBlocks2;
        try {
            try {
                get().performHistogramComparison(bitmap, bitmap2);
                makeBlocks = get().makeBlocks(bitmap);
                makeBlocks2 = get().makeBlocks(bitmap2);
            } catch (Exception e) {
                HVLogUtils.e(TAG, "runComparisonMethods(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
            if (makeBlocks != null && makeBlocks2 != null) {
                get().performSimilarity(makeBlocks, makeBlocks2);
                makeBlocks.recycle();
                makeBlocks2.recycle();
                bitmap2.recycle();
                bitmap.recycle();
            }
        } finally {
            this.jobDone.set(true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00a7 A[Catch: Exception -> 0x0101, OutOfMemoryError -> 0x0103, LOOP:2: B:23:0x00a5->B:24:0x00a7, LOOP_END, TryCatch #2 {OutOfMemoryError -> 0x0103, blocks: (B:3:0x001d, B:10:0x004b, B:13:0x0052, B:15:0x0058, B:18:0x0065, B:21:0x0079, B:24:0x00a7, B:26:0x00bb, B:29:0x00cc, B:30:0x00de, B:34:0x00d5, B:35:0x00da, B:38:0x0085, B:40:0x0096), top: B:2:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Bitmap makeBlocks(Bitmap bitmap) {
        int[] iArr;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int rgb;
        HVLogUtils.d(TAG, "makeBlocks() called with: inputBitmap = [" + bitmap + "]");
        try {
            try {
                int width = bitmap.getWidth();
                get();
                int height = bitmap.getHeight();
                get();
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, width / 4, height / 4, false);
                Bitmap createBitmap = Bitmap.createBitmap(createScaledBitmap.getWidth(), createScaledBitmap.getHeight(), createScaledBitmap.getConfig());
                if (createScaledBitmap != null && createBitmap != null) {
                    int i6 = 0;
                    while (i6 < createScaledBitmap.getWidth()) {
                        int i7 = 0;
                        while (i7 < createScaledBitmap.getHeight()) {
                            int width2 = createScaledBitmap.getWidth() * createScaledBitmap.getHeight();
                            int[] iArr2 = new int[width2];
                            try {
                                iArr = iArr2;
                                i = width2;
                                i2 = i7;
                            } catch (Exception e) {
                                e = e;
                                iArr = iArr2;
                                i = width2;
                                i2 = i7;
                            }
                            try {
                                createScaledBitmap.getPixels(iArr2, 0, createScaledBitmap.getWidth(), i6, i7, 8, 8);
                            } catch (Exception e2) {
                                e = e2;
                                Log.e(TAG, Utils.getErrorMessage(e));
                                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                                }
                                i4 = 0;
                                i5 = 0;
                                int i8 = 0;
                                while (i3 < i) {
                                }
                                int[] iArr3 = new int[createScaledBitmap.getWidth() * createScaledBitmap.getHeight()];
                                if (i4 < i5) {
                                }
                                if (i5 < i4) {
                                }
                                rgb = Color.rgb(0, 0, 255);
                                Arrays.fill(iArr3, rgb);
                                int i9 = i6;
                                createBitmap.setPixels(iArr3, 0, createBitmap.getWidth(), i6, i2, 8, 8);
                                i7 = i2 + 8;
                                i6 = i9;
                            }
                            i4 = 0;
                            i5 = 0;
                            int i82 = 0;
                            for (i3 = 0; i3 < i; i3++) {
                                int i10 = iArr[i3];
                                i4 += Color.red(i10);
                                i5 += Color.green(i10);
                                i82 += Color.blue(i10);
                            }
                            int[] iArr32 = new int[createScaledBitmap.getWidth() * createScaledBitmap.getHeight()];
                            if (i4 < i5 && i4 >= i82) {
                                rgb = Color.rgb(255, 0, 0);
                            } else if (i5 < i4 && i5 >= i82) {
                                rgb = Color.rgb(0, 255, 0);
                            } else {
                                rgb = Color.rgb(0, 0, 255);
                            }
                            Arrays.fill(iArr32, rgb);
                            int i92 = i6;
                            createBitmap.setPixels(iArr32, 0, createBitmap.getWidth(), i6, i2, 8, 8);
                            i7 = i2 + 8;
                            i6 = i92;
                        }
                        i6 += 8;
                    }
                    return createBitmap;
                }
                return null;
            } catch (OutOfMemoryError e3) {
                e = e3;
                HVLogUtils.e(TAG, "makeBlocks(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    return null;
                }
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                return null;
            }
        } catch (Exception e4) {
            e = e4;
            HVLogUtils.e(TAG, "makeBlocks(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
            }
        }
    }

    public float performSimilarity(Bitmap bitmap, Bitmap bitmap2) {
        HVLogUtils.d(TAG, "performSimilarity() called with: finalFrameBlockBitmap = [" + bitmap + "], saveBlockBitmap = [" + bitmap2 + "]");
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < bitmap.getWidth(); i3 += 8) {
            try {
                for (int i4 = 0; i4 < bitmap.getHeight(); i4 += 8) {
                    i2++;
                    if (bitmap.getPixel(i3, i4) == bitmap2.getPixel(i3, i4)) {
                        i++;
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
        float f = (i * 100) / i2;
        this.imageComparisonObj.setSimilarityScore(f);
        return f;
    }
}
