package org.apache.pdfbox.cos;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import org.apache.pdfbox.util.Charsets;
import org.apache.pdfbox.util.Hex;

/* loaded from: classes5.dex */
public final class COSString extends COSBase {
    public static final boolean FORCE_PARSING = Boolean.getBoolean("org.apache.pdfbox.forceParsing");
    private byte[] bytes;
    private boolean forceHexForm;

    public static COSString parseHex(String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        StringBuilder sb = new StringBuilder(str.trim());
        if (sb.length() % 2 != 0) {
            sb.append('0');
        }
        int length = sb.length();
        int i = 0;
        while (i < length) {
            int i2 = i + 2;
            try {
                byteArrayOutputStream.write(Integer.parseInt(sb.substring(i, i2), 16));
            } catch (NumberFormatException e) {
                if (FORCE_PARSING) {
                    Log.w("PdfBoxAndroid", "Encountered a malformed hex string");
                    byteArrayOutputStream.write(63);
                } else {
                    throw new IOException("Invalid hex string: " + str, e);
                }
            }
            i = i2;
        }
        return new COSString(byteArrayOutputStream.toByteArray());
    }

    public COSString(byte[] bArr) {
        setValue(bArr);
    }

    public COSString(String str) {
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            } else if (!PDFDocEncoding.containsChar(charArray[i])) {
                break;
            } else {
                i++;
            }
        }
        if (z) {
            this.bytes = PDFDocEncoding.getBytes(str);
            return;
        }
        byte[] bytes = str.getBytes(Charsets.UTF_16BE);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bytes.length + 2);
        byteArrayOutputStream.write(254);
        byteArrayOutputStream.write(255);
        try {
            byteArrayOutputStream.write(bytes);
            this.bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setValue(byte[] bArr) {
        this.bytes = (byte[]) bArr.clone();
    }

    public void setForceHexForm(boolean z) {
        this.forceHexForm = z;
    }

    public boolean getForceHexForm() {
        return this.forceHexForm;
    }

    public String getString() {
        byte[] bArr = this.bytes;
        if (bArr.length > 2) {
            if ((bArr[0] & 255) == 254 && (bArr[1] & 255) == 255) {
                return new String(bArr, 2, bArr.length - 2, Charsets.UTF_16BE);
            }
            if ((bArr[0] & 255) == 255 && (bArr[1] & 255) == 254) {
                return new String(bArr, 2, bArr.length - 2, Charsets.UTF_16LE);
            }
        }
        return PDFDocEncoding.toString(bArr);
    }

    public String getASCII() {
        return new String(this.bytes, Charsets.US_ASCII);
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public String toHexString() {
        StringBuilder sb = new StringBuilder(this.bytes.length * 2);
        for (byte b : this.bytes) {
            sb.append(Hex.getString(b));
        }
        return sb.toString();
    }

    @Override // org.apache.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromString(this);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof COSString)) {
            return false;
        }
        COSString cOSString = (COSString) obj;
        return getString().equals(cOSString.getString()) && this.forceHexForm == cOSString.forceHexForm;
    }

    public int hashCode() {
        return Arrays.hashCode(this.bytes) + (this.forceHexForm ? 17 : 0);
    }

    public String toString() {
        return "COSString{" + getString() + "}";
    }
}
