package org.apache.pdfbox.pdmodel.graphics.image;

import android.graphics.Bitmap;
import java.io.IOException;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;

/* loaded from: classes5.dex */
public interface PDImage extends COSObjectable {
    int getBitsPerComponent();

    PDColorSpace getColorSpace() throws IOException;

    COSArray getDecode();

    int getHeight();

    Bitmap getImage() throws IOException;

    boolean getInterpolate();

    PDStream getStream() throws IOException;

    String getSuffix();

    int getWidth();

    boolean isStencil();

    void setBitsPerComponent(int i);

    void setColorSpace(PDColorSpace pDColorSpace);

    void setDecode(COSArray cOSArray);

    void setHeight(int i);

    void setInterpolate(boolean z);

    void setStencil(boolean z);

    void setWidth(int i);
}
