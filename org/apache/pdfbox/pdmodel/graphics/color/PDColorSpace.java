package org.apache.pdfbox.pdmodel.graphics.color;

import java.io.IOException;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.pdmodel.MissingResourceException;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public abstract class PDColorSpace implements COSObjectable {
    protected COSArray array;

    public abstract float[] getDefaultDecode(int i);

    public abstract PDColor getInitialColor();

    public abstract String getName();

    public abstract int getNumberOfComponents();

    public abstract float[] toRGB(float[] fArr) throws IOException;

    public static PDColorSpace create(COSBase cOSBase) throws IOException {
        return create(cOSBase, null);
    }

    public static PDColorSpace create(COSBase cOSBase, PDResources pDResources) throws IOException {
        if (cOSBase instanceof COSObject) {
            return create(((COSObject) cOSBase).getObject(), pDResources);
        }
        if (cOSBase instanceof COSName) {
            COSName cOSName = (COSName) cOSBase;
            if (pDResources != null) {
                COSName cOSName2 = null;
                if (cOSName.equals(COSName.DEVICECMYK) && pDResources.hasColorSpace(COSName.DEFAULT_CMYK)) {
                    cOSName2 = COSName.DEFAULT_CMYK;
                } else if (cOSName.equals(COSName.DEVICERGB) && pDResources.hasColorSpace(COSName.DEFAULT_RGB)) {
                    cOSName2 = COSName.DEFAULT_RGB;
                } else if (cOSName.equals(COSName.DEVICEGRAY) && pDResources.hasColorSpace(COSName.DEFAULT_GRAY)) {
                    cOSName2 = COSName.DEFAULT_GRAY;
                }
                if (pDResources.hasColorSpace(cOSName2)) {
                    return pDResources.getColorSpace(cOSName2);
                }
            }
            if (cOSName == COSName.DEVICERGB || cOSName == COSName.RGB) {
                return PDDeviceRGB.INSTANCE;
            }
            if (cOSName == COSName.DEVICEGRAY || cOSName == COSName.G) {
                return PDDeviceGray.INSTANCE;
            }
            if (pDResources != null) {
                if (!pDResources.hasColorSpace(cOSName)) {
                    throw new MissingResourceException("Missing color space: " + cOSName.getName());
                }
                return pDResources.getColorSpace(cOSName);
            }
            throw new MissingResourceException("Unknown color space: " + cOSName.getName());
        }
        if (cOSBase instanceof COSArray) {
            throw new IOException("Invalid color space kind: " + ((COSName) ((COSArray) cOSBase).get(0)));
        }
        throw new IOException("Expected a name or array but got: " + cOSBase);
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.array;
    }
}
