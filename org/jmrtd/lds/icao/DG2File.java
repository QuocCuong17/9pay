package org.jmrtd.lds.icao;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import org.jmrtd.cbeff.BiometricDataBlock;
import org.jmrtd.cbeff.BiometricDataBlockDecoder;
import org.jmrtd.cbeff.BiometricDataBlockEncoder;
import org.jmrtd.cbeff.CBEFFInfo;
import org.jmrtd.cbeff.ComplexCBEFFInfo;
import org.jmrtd.cbeff.ISO781611Decoder;
import org.jmrtd.cbeff.ISO781611Encoder;
import org.jmrtd.cbeff.SimpleCBEFFInfo;
import org.jmrtd.cbeff.StandardBiometricHeader;
import org.jmrtd.lds.CBEFFDataGroup;
import org.jmrtd.lds.iso19794.FaceInfo;

/* loaded from: classes6.dex */
public class DG2File extends CBEFFDataGroup<FaceInfo> {
    private static final ISO781611Decoder DECODER = new ISO781611Decoder(new BiometricDataBlockDecoder<FaceInfo>() { // from class: org.jmrtd.lds.icao.DG2File.1
        @Override // org.jmrtd.cbeff.BiometricDataBlockDecoder
        public FaceInfo decode(InputStream inputStream, StandardBiometricHeader standardBiometricHeader, int i, int i2) throws IOException {
            return new FaceInfo(standardBiometricHeader, inputStream);
        }
    });
    private static final ISO781611Encoder<FaceInfo> ENCODER = new ISO781611Encoder<>(new BiometricDataBlockEncoder<FaceInfo>() { // from class: org.jmrtd.lds.icao.DG2File.2
        @Override // org.jmrtd.cbeff.BiometricDataBlockEncoder
        public void encode(FaceInfo faceInfo, OutputStream outputStream) throws IOException {
            faceInfo.writeObject(outputStream);
        }
    });
    private static final long serialVersionUID = 414300652684010416L;

    public DG2File(List<FaceInfo> list) {
        super(117, list);
    }

    public DG2File(InputStream inputStream) throws IOException {
        super(117, inputStream);
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    protected void readContent(InputStream inputStream) throws IOException {
        for (CBEFFInfo cBEFFInfo : DECODER.decode(inputStream).getSubRecords()) {
            if (!(cBEFFInfo instanceof SimpleCBEFFInfo)) {
                throw new IOException("Was expecting a SimpleCBEFFInfo, found " + cBEFFInfo.getClass().getSimpleName());
            }
            BiometricDataBlock biometricDataBlock = ((SimpleCBEFFInfo) cBEFFInfo).getBiometricDataBlock();
            if (!(biometricDataBlock instanceof FaceInfo)) {
                throw new IOException("Was expecting a FaceInfo, found " + biometricDataBlock.getClass().getSimpleName());
            }
            add((FaceInfo) biometricDataBlock);
        }
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    protected void writeContent(OutputStream outputStream) throws IOException {
        ComplexCBEFFInfo complexCBEFFInfo = new ComplexCBEFFInfo();
        Iterator<FaceInfo> it = getSubRecords().iterator();
        while (it.hasNext()) {
            complexCBEFFInfo.add(new SimpleCBEFFInfo(it.next()));
        }
        ENCODER.encode(complexCBEFFInfo, outputStream);
    }

    @Override // org.jmrtd.lds.CBEFFDataGroup, org.jmrtd.lds.DataGroup, org.jmrtd.lds.AbstractTaggedLDSFile
    public String toString() {
        return "DG2File [" + super.toString() + "]";
    }

    public List<FaceInfo> getFaceInfos() {
        return getSubRecords();
    }

    public void addFaceInfo(FaceInfo faceInfo) {
        add(faceInfo);
    }

    public void removeFaceInfo(int i) {
        remove(i);
    }
}
