package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import androidx.media3.common.C;
import com.google.android.gms.internal.vision.zzab;
import com.google.android.gms.internal.vision.zzah;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.3 */
/* loaded from: classes3.dex */
public class TextBlock implements Text {
    private zzah[] zza;
    private Point[] zzb;
    private List<Line> zzc;
    private String zzd;
    private Rect zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextBlock(SparseArray<zzah> sparseArray) {
        this.zza = new zzah[sparseArray.size()];
        int i = 0;
        while (true) {
            zzah[] zzahVarArr = this.zza;
            if (i >= zzahVarArr.length) {
                return;
            }
            zzahVarArr[i] = sparseArray.valueAt(i);
            i++;
        }
    }

    @Override // com.google.android.gms.vision.text.Text
    public String getLanguage() {
        String str = this.zzd;
        if (str != null) {
            return str;
        }
        HashMap hashMap = new HashMap();
        for (zzah zzahVar : this.zza) {
            hashMap.put(zzahVar.zzd, Integer.valueOf((hashMap.containsKey(zzahVar.zzd) ? ((Integer) hashMap.get(zzahVar.zzd)).intValue() : 0) + 1));
        }
        String str2 = (String) ((Map.Entry) Collections.max(hashMap.entrySet(), new zza(this))).getKey();
        this.zzd = str2;
        if (str2 == null || str2.isEmpty()) {
            this.zzd = C.LANGUAGE_UNDETERMINED;
        }
        return this.zzd;
    }

    @Override // com.google.android.gms.vision.text.Text
    public String getValue() {
        zzah[] zzahVarArr = this.zza;
        if (zzahVarArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(zzahVarArr[0].zzc);
        for (int i = 1; i < this.zza.length; i++) {
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            sb.append(this.zza[i].zzc);
        }
        return sb.toString();
    }

    @Override // com.google.android.gms.vision.text.Text
    public Point[] getCornerPoints() {
        TextBlock textBlock;
        zzah[] zzahVarArr;
        TextBlock textBlock2 = this;
        if (textBlock2.zzb == null) {
            char c = 0;
            if (textBlock2.zza.length == 0) {
                textBlock2.zzb = new Point[0];
            } else {
                int i = Integer.MIN_VALUE;
                int i2 = 0;
                int i3 = Integer.MAX_VALUE;
                int i4 = Integer.MAX_VALUE;
                int i5 = Integer.MIN_VALUE;
                while (true) {
                    zzahVarArr = textBlock2.zza;
                    if (i2 >= zzahVarArr.length) {
                        break;
                    }
                    zzab zzabVar = zzahVarArr[i2].zzb;
                    zzab zzabVar2 = textBlock2.zza[c].zzb;
                    int i6 = -zzabVar2.zza;
                    int i7 = -zzabVar2.zzb;
                    double sin = Math.sin(Math.toRadians(zzabVar2.zze));
                    double cos = Math.cos(Math.toRadians(zzabVar2.zze));
                    Point[] pointArr = new Point[4];
                    pointArr[c] = new Point(zzabVar.zza, zzabVar.zzb);
                    pointArr[c].offset(i6, i7);
                    int i8 = i5;
                    int i9 = (int) ((pointArr[c].x * cos) + (pointArr[c].y * sin));
                    int i10 = (int) (((-pointArr[0].x) * sin) + (pointArr[0].y * cos));
                    pointArr[0].x = i9;
                    pointArr[0].y = i10;
                    pointArr[1] = new Point(zzabVar.zzc + i9, i10);
                    pointArr[2] = new Point(zzabVar.zzc + i9, zzabVar.zzd + i10);
                    pointArr[3] = new Point(i9, i10 + zzabVar.zzd);
                    i5 = i8;
                    for (int i11 = 0; i11 < 4; i11++) {
                        Point point = pointArr[i11];
                        i3 = Math.min(i3, point.x);
                        i = Math.max(i, point.x);
                        i4 = Math.min(i4, point.y);
                        i5 = Math.max(i5, point.y);
                    }
                    i2++;
                    c = 0;
                    textBlock2 = this;
                }
                int i12 = i5;
                zzab zzabVar3 = zzahVarArr[c].zzb;
                int i13 = zzabVar3.zza;
                int i14 = zzabVar3.zzb;
                double sin2 = Math.sin(Math.toRadians(zzabVar3.zze));
                double cos2 = Math.cos(Math.toRadians(zzabVar3.zze));
                Point[] pointArr2 = {new Point(i3, i4), new Point(i, i4), new Point(i, i12), new Point(i3, i12)};
                for (int i15 = 0; i15 < 4; i15++) {
                    pointArr2[i15].x = (int) ((pointArr2[i15].x * cos2) - (pointArr2[i15].y * sin2));
                    pointArr2[i15].y = (int) ((pointArr2[i15].x * sin2) + (pointArr2[i15].y * cos2));
                    pointArr2[i15].offset(i13, i14);
                }
                textBlock = this;
                textBlock.zzb = pointArr2;
                return textBlock.zzb;
            }
        }
        textBlock = textBlock2;
        return textBlock.zzb;
    }

    @Override // com.google.android.gms.vision.text.Text
    public List<? extends Text> getComponents() {
        if (this.zza.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzc == null) {
            this.zzc = new ArrayList(this.zza.length);
            for (zzah zzahVar : this.zza) {
                this.zzc.add(new Line(zzahVar));
            }
        }
        return this.zzc;
    }

    @Override // com.google.android.gms.vision.text.Text
    public Rect getBoundingBox() {
        if (this.zze == null) {
            this.zze = zzc.zza(this);
        }
        return this.zze;
    }
}
