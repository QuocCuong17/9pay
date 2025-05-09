package org.jmrtd.lds.icao;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import net.sf.scuba.tlv.TLVInputStream;
import net.sf.scuba.tlv.TLVOutputStream;
import org.apache.commons.io.IOUtils;
import org.jmrtd.lds.DataGroup;

/* loaded from: classes6.dex */
public class DG1File extends DataGroup {
    private static final short MRZ_INFO_TAG = 24351;
    private static final long serialVersionUID = 5091606125728809058L;
    private MRZInfo mrzInfo;

    public DG1File(MRZInfo mRZInfo) {
        super(97);
        this.mrzInfo = mRZInfo;
    }

    public DG1File(InputStream inputStream) throws IOException {
        super(97, inputStream);
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    protected void readContent(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        tLVInputStream.skipToTag(24351);
        this.mrzInfo = new MRZInfo(tLVInputStream, tLVInputStream.readLength());
    }

    public MRZInfo getMRZInfo() {
        return this.mrzInfo;
    }

    @Override // org.jmrtd.lds.DataGroup, org.jmrtd.lds.AbstractTaggedLDSFile
    public String toString() {
        return "DG1File " + this.mrzInfo.toString().replaceAll(IOUtils.LINE_SEPARATOR_UNIX, "").trim();
    }

    public boolean equals(Object obj) {
        if (obj != null && obj.getClass().equals(getClass())) {
            return this.mrzInfo.equals(((DG1File) obj).mrzInfo);
        }
        return false;
    }

    public int hashCode() {
        return (this.mrzInfo.hashCode() * 3) + 57;
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    protected void writeContent(OutputStream outputStream) throws IOException {
        TLVOutputStream tLVOutputStream = outputStream instanceof TLVOutputStream ? (TLVOutputStream) outputStream : new TLVOutputStream(outputStream);
        tLVOutputStream.writeTag(24351);
        tLVOutputStream.writeValue(this.mrzInfo.getEncoded());
    }
}
