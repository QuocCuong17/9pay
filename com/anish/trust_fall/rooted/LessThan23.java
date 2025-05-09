package com.anish.trust_fall.rooted;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: LessThan23.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lcom/anish/trust_fall/rooted/LessThan23;", "Lcom/anish/trust_fall/rooted/CheckApiVersion;", "()V", "checkRooted", "", "Companion", "jailbreak_root_detection_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LessThan23 implements CheckApiVersion {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Override // com.anish.trust_fall.rooted.CheckApiVersion
    public boolean checkRooted() {
        Companion companion = INSTANCE;
        return companion.canExecuteCommand("/system/xbin/which su") || companion.isSuperuserPresent();
    }

    /* compiled from: LessThan23.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005¨\u0006\t"}, d2 = {"Lcom/anish/trust_fall/rooted/LessThan23$Companion;", "", "()V", "isSuperuserPresent", "", "()Z", "canExecuteCommand", "command", "", "jailbreak_root_detection_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean canExecuteCommand(String command) {
            try {
                return Runtime.getRuntime().exec(command).waitFor() == 0;
            } catch (Exception unused) {
                return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isSuperuserPresent() {
            String[] strArr = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"};
            for (int i = 0; i < 9; i++) {
                if (new File(strArr[i]).exists()) {
                    return true;
                }
            }
            return false;
        }
    }
}
