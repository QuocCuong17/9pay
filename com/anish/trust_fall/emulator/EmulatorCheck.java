package com.anish.trust_fall.emulator;

import android.os.Build;
import com.google.firebase.firestore.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: EmulatorCheck.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/anish/trust_fall/emulator/EmulatorCheck;", "", "()V", "isEmulator", "", "()Z", "jailbreak_root_detection_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EmulatorCheck {
    public static final EmulatorCheck INSTANCE = new EmulatorCheck();

    private EmulatorCheck() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x00ce, code lost:
    
        if (kotlin.text.StringsKt.startsWith$default(r0, "generic", false, 2, (java.lang.Object) null) == false) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean isEmulator() {
        String FINGERPRINT = Build.FINGERPRINT;
        Intrinsics.checkNotNullExpressionValue(FINGERPRINT, "FINGERPRINT");
        if (!StringsKt.startsWith$default(FINGERPRINT, "generic", false, 2, (Object) null)) {
            String FINGERPRINT2 = Build.FINGERPRINT;
            Intrinsics.checkNotNullExpressionValue(FINGERPRINT2, "FINGERPRINT");
            if (!StringsKt.startsWith$default(FINGERPRINT2, "unknown", false, 2, (Object) null)) {
                String HARDWARE = Build.HARDWARE;
                Intrinsics.checkNotNullExpressionValue(HARDWARE, "HARDWARE");
                if (!StringsKt.startsWith$default(HARDWARE, "goldfish", false, 2, (Object) null)) {
                    String MODEL = Build.MODEL;
                    Intrinsics.checkNotNullExpressionValue(MODEL, "MODEL");
                    if (!StringsKt.contains$default((CharSequence) MODEL, (CharSequence) "google_sdk", false, 2, (Object) null)) {
                        String MODEL2 = Build.MODEL;
                        Intrinsics.checkNotNullExpressionValue(MODEL2, "MODEL");
                        if (!StringsKt.contains$default((CharSequence) MODEL2, (CharSequence) "sdk", false, 2, (Object) null)) {
                            String MODEL3 = Build.MODEL;
                            Intrinsics.checkNotNullExpressionValue(MODEL3, "MODEL");
                            if (!StringsKt.contains$default((CharSequence) MODEL3, (CharSequence) "Emulator", false, 2, (Object) null)) {
                                String PRODUCT = Build.PRODUCT;
                                Intrinsics.checkNotNullExpressionValue(PRODUCT, "PRODUCT");
                                if (!StringsKt.contains$default((CharSequence) PRODUCT, (CharSequence) "sdk", false, 2, (Object) null)) {
                                    String MODEL4 = Build.MODEL;
                                    Intrinsics.checkNotNullExpressionValue(MODEL4, "MODEL");
                                    if (!StringsKt.contains$default((CharSequence) MODEL4, (CharSequence) "Android SDK built for x86", false, 2, (Object) null)) {
                                        String MANUFACTURER = Build.MANUFACTURER;
                                        Intrinsics.checkNotNullExpressionValue(MANUFACTURER, "MANUFACTURER");
                                        if (!StringsKt.contains$default((CharSequence) MANUFACTURER, (CharSequence) "Genymotion", false, 2, (Object) null)) {
                                            String MANUFACTURER2 = Build.MANUFACTURER;
                                            Intrinsics.checkNotNullExpressionValue(MANUFACTURER2, "MANUFACTURER");
                                            if (!StringsKt.contains$default((CharSequence) MANUFACTURER2, (CharSequence) "unknown", false, 2, (Object) null)) {
                                                String DEVICE = Build.DEVICE;
                                                Intrinsics.checkNotNullExpressionValue(DEVICE, "DEVICE");
                                                if (!StringsKt.startsWith$default(DEVICE, BuildConfig.TARGET_BACKEND, false, 2, (Object) null)) {
                                                    String BRAND = Build.BRAND;
                                                    Intrinsics.checkNotNullExpressionValue(BRAND, "BRAND");
                                                    if (StringsKt.startsWith$default(BRAND, "generic", false, 2, (Object) null)) {
                                                        String DEVICE2 = Build.DEVICE;
                                                        Intrinsics.checkNotNullExpressionValue(DEVICE2, "DEVICE");
                                                    }
                                                    if (!Intrinsics.areEqual("google_sdk", Build.PRODUCT)) {
                                                        return false;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
