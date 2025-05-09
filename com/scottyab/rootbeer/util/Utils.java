package com.scottyab.rootbeer.util;

import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;

/* loaded from: classes5.dex */
public final class Utils {
    private Utils() throws InstantiationException {
        throw new InstantiationException("This class is not for instantiation");
    }

    public static boolean isSelinuxFlagInEnabled() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return "1".equals((String) cls.getMethod(WorkflowRequestType.Method.GET, String.class).invoke(cls, "ro.build.selinux"));
        } catch (Exception unused) {
            return false;
        }
    }
}
