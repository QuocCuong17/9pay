package org.jmrtd.lds;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import net.sf.scuba.tlv.TLVInputStream;
import net.sf.scuba.tlv.TLVOutputStream;

/* loaded from: classes6.dex */
public abstract class DisplayedImageDataGroup extends DataGroup {
    private static final int DISPLAYED_IMAGE_COUNT_TAG = 2;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = 5994136177872308962L;
    private int displayedImageTagToUse;
    private List<DisplayedImageInfo> imageInfos;

    public DisplayedImageDataGroup(int i, List<DisplayedImageInfo> list, int i2) {
        super(i);
        if (list == null) {
            throw new IllegalArgumentException("imageInfos cannot be null");
        }
        this.displayedImageTagToUse = i2;
        this.imageInfos = new ArrayList(list);
        checkTypesConsistentWithTag();
    }

    public DisplayedImageDataGroup(int i, InputStream inputStream) throws IOException {
        super(i, inputStream);
        if (this.imageInfos == null) {
            this.imageInfos = new ArrayList();
        }
        checkTypesConsistentWithTag();
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    protected void readContent(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        int readTag = tLVInputStream.readTag();
        if (readTag != 2) {
            throw new IllegalArgumentException("Expected tag 0x02 in displayed image structure, found " + Integer.toHexString(readTag));
        }
        if (tLVInputStream.readLength() != 1) {
            throw new IllegalArgumentException("DISPLAYED_IMAGE_COUNT should have length 1");
        }
        int i = tLVInputStream.readValue()[0] & 255;
        for (int i2 = 0; i2 < i; i2++) {
            DisplayedImageInfo displayedImageInfo = new DisplayedImageInfo(tLVInputStream);
            if (i2 == 0) {
                this.displayedImageTagToUse = displayedImageInfo.getDisplayedImageTag();
            } else if (displayedImageInfo.getDisplayedImageTag() != this.displayedImageTagToUse) {
                throw new IOException("Found images with different displayed image tags inside displayed image datagroup");
            }
            add(displayedImageInfo);
        }
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    protected void writeContent(OutputStream outputStream) throws IOException {
        TLVOutputStream tLVOutputStream = outputStream instanceof TLVOutputStream ? (TLVOutputStream) outputStream : new TLVOutputStream(outputStream);
        tLVOutputStream.writeTag(2);
        tLVOutputStream.writeValue(new byte[]{(byte) this.imageInfos.size()});
        Iterator<DisplayedImageInfo> it = this.imageInfos.iterator();
        while (it.hasNext()) {
            it.next().writeObject(tLVOutputStream);
        }
    }

    @Override // org.jmrtd.lds.DataGroup, org.jmrtd.lds.AbstractTaggedLDSFile
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        List<DisplayedImageInfo> list = this.imageInfos;
        if (list == null) {
            throw new IllegalStateException("imageInfos cannot be null");
        }
        boolean z = true;
        for (DisplayedImageInfo displayedImageInfo : list) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(displayedImageInfo.toString());
        }
        sb.append("]");
        return sb.toString();
    }

    public int hashCode() {
        List<DisplayedImageInfo> list = this.imageInfos;
        return (list == null ? 1 : list.hashCode()) + 1337 + 31337;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        List<DisplayedImageInfo> list = this.imageInfos;
        List<DisplayedImageInfo> list2 = ((DisplayedImageDataGroup) obj).imageInfos;
        return list == list2 || (list != null && list.equals(list2));
    }

    public List<DisplayedImageInfo> getImages() {
        return new ArrayList(this.imageInfos);
    }

    private void add(DisplayedImageInfo displayedImageInfo) {
        if (this.imageInfos == null) {
            this.imageInfos = new ArrayList();
        }
        this.imageInfos.add(displayedImageInfo);
    }

    private void checkTypesConsistentWithTag() {
        for (DisplayedImageInfo displayedImageInfo : this.imageInfos) {
            if (displayedImageInfo == null) {
                throw new IllegalArgumentException("Found a null image info");
            }
            int type = displayedImageInfo.getType();
            if (type == 0) {
                if (this.displayedImageTagToUse != 24384) {
                    throw new IllegalArgumentException("'Signature or usual mark' image cannot be part of a 'Portrait' displayed image datagroup");
                }
            } else if (type == 1) {
                if (this.displayedImageTagToUse != 24387) {
                    throw new IllegalArgumentException("'Portrait' image cannot be part of a 'Signature or usual mark' displayed image datagroup");
                }
            } else {
                LOGGER.warning("Unsupported image type");
            }
        }
    }
}
