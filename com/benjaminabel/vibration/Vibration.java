package com.benjaminabel.vibration;

import android.media.AudioAttributes;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import java.util.List;

/* loaded from: classes2.dex */
public class Vibration {
    private final Vibrator vibrator;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Vibration(Vibrator vibrator) {
        this.vibrator = vibrator;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void vibrate(long j, int i) {
        if (this.vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= 26) {
                if (this.vibrator.hasAmplitudeControl()) {
                    this.vibrator.vibrate(VibrationEffect.createOneShot(j, i), new AudioAttributes.Builder().setContentType(4).setUsage(4).build());
                    return;
                } else {
                    this.vibrator.vibrate(VibrationEffect.createOneShot(j, -1), new AudioAttributes.Builder().setContentType(4).setUsage(4).build());
                    return;
                }
            }
            this.vibrator.vibrate(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void vibrate(List<Integer> list, int i) {
        int size = list.size();
        long[] jArr = new long[size];
        for (int i2 = 0; i2 < size; i2++) {
            jArr[i2] = list.get(i2).intValue();
        }
        if (this.vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.vibrator.vibrate(VibrationEffect.createWaveform(jArr, i), new AudioAttributes.Builder().setContentType(4).setUsage(4).build());
            } else {
                this.vibrator.vibrate(jArr, i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void vibrate(List<Integer> list, int i, List<Integer> list2) {
        int size = list.size();
        long[] jArr = new long[size];
        int size2 = list2.size();
        int[] iArr = new int[size2];
        for (int i2 = 0; i2 < size; i2++) {
            jArr[i2] = list.get(i2).intValue();
        }
        for (int i3 = 0; i3 < size2; i3++) {
            iArr[i3] = list2.get(i3).intValue();
        }
        if (this.vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= 26) {
                if (this.vibrator.hasAmplitudeControl()) {
                    this.vibrator.vibrate(VibrationEffect.createWaveform(jArr, iArr, i), new AudioAttributes.Builder().setContentType(4).setUsage(4).build());
                    return;
                } else {
                    this.vibrator.vibrate(VibrationEffect.createWaveform(jArr, i), new AudioAttributes.Builder().setContentType(4).setUsage(4).build());
                    return;
                }
            }
            this.vibrator.vibrate(jArr, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Vibrator getVibrator() {
        return this.vibrator;
    }
}
