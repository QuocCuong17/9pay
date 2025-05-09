package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzab;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.3 */
/* loaded from: classes3.dex */
final class zzc {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Rect zza(Text text) {
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        for (Point point : text.getCornerPoints()) {
            i4 = Math.min(i4, point.x);
            i = Math.max(i, point.x);
            i2 = Math.min(i2, point.y);
            i3 = Math.max(i3, point.y);
        }
        return new Rect(i4, i2, i, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Point[] zza(zzab zzabVar) {
        double sin = Math.sin(Math.toRadians(zzabVar.zze));
        double cos = Math.cos(Math.toRadians(zzabVar.zze));
        Point[] pointArr = {new Point(zzabVar.zza, zzabVar.zzb), new Point((int) (zzabVar.zza + (zzabVar.zzc * cos)), (int) (zzabVar.zzb + (zzabVar.zzc * sin))), new Point((int) (pointArr[1].x - (zzabVar.zzd * sin)), (int) (pointArr[1].y + (zzabVar.zzd * cos))), new Point(pointArr[0].x + (pointArr[2].x - pointArr[1].x), pointArr[0].y + (pointArr[2].y - pointArr[1].y))};
        return pointArr;
    }
}
