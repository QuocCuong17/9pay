package com.github.jaiimageio.impl.common;

import com.facebook.internal.ServerProtocol;
import java.security.AccessController;
import java.security.PrivilegedAction;

/* loaded from: classes3.dex */
public class PackageUtil {
    private static boolean isCodecLibAvailable = false;
    private static String specTitle = "Java Advanced Imaging Image I/O Tools";
    private static String vendor = "Sun Microsystems, Inc.";
    private static String version = "1.0";

    static {
        try {
            Package r0 = Class.forName("com.github.jaiimageio.impl.common.PackageUtil").getPackage();
            if (r0.getImplementationVersion() == null || r0.getImplementationVendor() == null) {
                return;
            }
            version = r0.getImplementationVersion();
            vendor = r0.getImplementationVendor();
            specTitle = r0.getSpecificationTitle();
        } catch (ClassNotFoundException unused) {
        }
    }

    public static final boolean isCodecLibAvailable() {
        return isCodecLibAvailable && !((Boolean) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.github.jaiimageio.impl.common.PackageUtil.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                String str;
                try {
                    str = System.getProperty("com.github.jaiimageio.disableCodecLib");
                } catch (SecurityException unused) {
                    str = null;
                }
                return (str == null || !str.equalsIgnoreCase(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) ? Boolean.FALSE : Boolean.TRUE;
            }
        })).booleanValue();
    }

    public static final String getVersion() {
        return version;
    }

    public static final String getVendor() {
        return vendor;
    }

    public static final String getSpecificationTitle() {
        return specTitle;
    }
}
