package org.ejbca.cvc;

import java.math.BigInteger;
import org.ejbca.cvc.util.StringConverter;

/* loaded from: classes6.dex */
public class ByteField extends AbstractDataField {
    private static final long serialVersionUID = 1;
    private byte[] data;
    private boolean showBitLength;

    ByteField(CVCTagEnum cVCTagEnum) {
        super(cVCTagEnum);
        this.showBitLength = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteField(CVCTagEnum cVCTagEnum, byte[] bArr) {
        this(cVCTagEnum, bArr, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteField(CVCTagEnum cVCTagEnum, byte[] bArr, boolean z) {
        super(cVCTagEnum);
        this.showBitLength = false;
        this.data = bArr;
        this.showBitLength = z;
    }

    public boolean isShowBitLength() {
        return this.showBitLength;
    }

    public void setShowBitLength(boolean z) {
        this.showBitLength = z;
    }

    public byte[] getData() {
        return this.data;
    }

    @Override // org.ejbca.cvc.AbstractDataField
    protected byte[] getEncoded() {
        return this.data;
    }

    @Override // org.ejbca.cvc.AbstractDataField
    protected String valueAsText() {
        String str;
        if (this.showBitLength) {
            str = "[" + (this.data != null ? new BigInteger(1, this.data).bitLength() : 0) + "]  ";
        } else {
            str = "";
        }
        return str + StringConverter.byteToHex(this.data);
    }
}
