package org.jmrtd;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import net.sf.scuba.smartcards.APDUWrapper;
import net.sf.scuba.smartcards.CardServiceException;
import net.sf.scuba.smartcards.FileInfo;
import net.sf.scuba.smartcards.FileSystemStructured;
import net.sf.scuba.tlv.TLVInputStream;
import net.sf.scuba.util.Hex;
import org.jmrtd.io.FragmentBuffer;
import org.jmrtd.lds.LDSFileUtil;

/* loaded from: classes6.dex */
public class DefaultFileSystem implements FileSystemStructured {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    public static final int NO_SFI = -1;
    private static final int READ_AHEAD_LENGTH = 8;
    private Map<Short, Byte> fidToSFI;
    private Map<Short, DefaultFileInfo> fileInfos;
    private boolean isSFIEnabled;
    private boolean isSelected;
    private short selectedFID;
    private APDULevelReadBinaryCapable service;
    private APDUWrapper wrapper;

    public DefaultFileSystem(APDULevelReadBinaryCapable aPDULevelReadBinaryCapable, boolean z) {
        this(aPDULevelReadBinaryCapable, z, LDSFileUtil.FID_TO_SFI);
    }

    public DefaultFileSystem(APDULevelReadBinaryCapable aPDULevelReadBinaryCapable, boolean z, Map<Short, Byte> map) {
        this.service = aPDULevelReadBinaryCapable;
        this.fileInfos = new HashMap();
        this.selectedFID = (short) 0;
        this.isSelected = false;
        this.isSFIEnabled = z;
        this.fidToSFI = map;
    }

    public void setWrapper(APDUWrapper aPDUWrapper) {
        this.wrapper = aPDUWrapper;
    }

    @Override // net.sf.scuba.smartcards.FileSystemStructured
    public synchronized FileInfo[] getSelectedPath() throws CardServiceException {
        DefaultFileInfo fileInfo = getFileInfo();
        if (fileInfo == null) {
            return null;
        }
        return new DefaultFileInfo[]{fileInfo};
    }

    @Override // net.sf.scuba.smartcards.FileSystemStructured
    public synchronized void selectFile(short s) throws CardServiceException {
        if (this.selectedFID == s) {
            return;
        }
        this.selectedFID = s;
        this.isSelected = false;
    }

    @Override // net.sf.scuba.smartcards.FileSystemStructured
    public synchronized byte[] readBinary(int i, int i2) throws CardServiceException {
        byte[] bArr;
        byte[] sendReadBinary;
        try {
            try {
                if (this.selectedFID <= 0) {
                    throw new CardServiceException("No file selected");
                }
                DefaultFileInfo fileInfo = getFileInfo();
                if (fileInfo == null) {
                    throw new IllegalStateException("Could not get file info");
                }
                FragmentBuffer.Fragment smallestUnbufferedFragment = fileInfo.getSmallestUnbufferedFragment(i, i2);
                if (smallestUnbufferedFragment.getLength() > 0) {
                    boolean z = true;
                    if (this.isSFIEnabled && i < 256) {
                        Byte b = this.fidToSFI.get(Short.valueOf(this.selectedFID));
                        if (b == null) {
                            throw new NumberFormatException("Unknown FID " + Integer.toHexString(this.selectedFID));
                        }
                        sendReadBinary = sendReadBinary((b.byteValue() & 255) | 128, smallestUnbufferedFragment.getOffset(), smallestUnbufferedFragment.getLength(), false);
                        this.isSelected = true;
                    } else {
                        if (!this.isSelected) {
                            sendSelectFile(this.selectedFID);
                            this.isSelected = true;
                        }
                        int offset = smallestUnbufferedFragment.getOffset();
                        int length = smallestUnbufferedFragment.getLength();
                        if (i <= 32767) {
                            z = false;
                        }
                        sendReadBinary = sendReadBinary(offset, length, z);
                    }
                    if (sendReadBinary == null) {
                        throw new IllegalStateException("Could not read bytes");
                    }
                    if (sendReadBinary.length > 0) {
                        fileInfo.addFragment(smallestUnbufferedFragment.getOffset(), sendReadBinary);
                    }
                    i2 = sendReadBinary.length;
                }
                bArr = new byte[i2];
                System.arraycopy(fileInfo.getBuffer(), i, bArr, 0, i2);
            } catch (CardServiceException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Read binary failed on file ");
                sb.append((Object) (0 == 0 ? Integer.toHexString(this.selectedFID) : null));
                throw new CardServiceException(sb.toString(), e, e.getSW());
            }
        } catch (Exception e2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Read binary failed on file ");
            sb2.append((Object) (0 == 0 ? Integer.toHexString(this.selectedFID) : null));
            throw new CardServiceException(sb2.toString(), e2);
        }
        return bArr;
    }

    private synchronized DefaultFileInfo getFileInfo() throws CardServiceException {
        byte[] sendReadBinary;
        int length;
        short s = this.selectedFID;
        if (s <= 0) {
            throw new CardServiceException("No file selected");
        }
        DefaultFileInfo defaultFileInfo = this.fileInfos.get(Short.valueOf(s));
        if (defaultFileInfo != null) {
            return defaultFileInfo;
        }
        try {
            if (this.isSFIEnabled) {
                Byte b = this.fidToSFI.get(Short.valueOf(this.selectedFID));
                if (b == null) {
                    throw new NumberFormatException("Unknown FID " + Integer.toHexString(this.selectedFID));
                }
                sendReadBinary = sendReadBinary((b.byteValue() & 255) | 128, 0, 8, false);
                this.isSelected = true;
            } else {
                if (!this.isSelected) {
                    sendSelectFile(this.selectedFID);
                    this.isSelected = true;
                }
                sendReadBinary = sendReadBinary(0, 8, false);
            }
            if (sendReadBinary != null && sendReadBinary.length != 0) {
                if (sendReadBinary.length < 8) {
                    int length2 = sendReadBinary.length;
                    LOGGER.info("Short file " + Integer.toHexString(this.selectedFID) + " with length: " + length2);
                    return new DefaultFileInfo(this.selectedFID, length2);
                }
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(sendReadBinary);
                TLVInputStream tLVInputStream = new TLVInputStream(byteArrayInputStream);
                try {
                    if (tLVInputStream.readTag() == 66) {
                        length = 36;
                    } else {
                        length = (sendReadBinary.length - byteArrayInputStream.available()) + tLVInputStream.readLength();
                    }
                    DefaultFileInfo defaultFileInfo2 = new DefaultFileInfo(this.selectedFID, length);
                    defaultFileInfo2.addFragment(0, sendReadBinary);
                    this.fileInfos.put(Short.valueOf(this.selectedFID), defaultFileInfo2);
                    return defaultFileInfo2;
                } finally {
                    tLVInputStream.close();
                }
            }
            LOGGER.warning("Something is wrong with prefix, prefix = " + Hex.bytesToHexString(sendReadBinary));
            return null;
        } catch (IOException e) {
            throw new CardServiceException("Error getting file info for " + Integer.toHexString(this.selectedFID), e);
        }
    }

    public synchronized void sendSelectFile(short s) throws CardServiceException {
        this.service.sendSelectFile(this.wrapper, s);
    }

    public synchronized byte[] sendReadBinary(int i, int i2, boolean z) throws CardServiceException {
        return this.service.sendReadBinary(this.wrapper, -1, i, i2, false, z);
    }

    public synchronized byte[] sendReadBinary(int i, int i2, int i3, boolean z) throws CardServiceException {
        return this.service.sendReadBinary(this.wrapper, i, i2, i3, true, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class DefaultFileInfo extends FileInfo implements Serializable {
        private static final long serialVersionUID = 6727369753765119839L;
        private FragmentBuffer buffer;
        private short fid;

        public DefaultFileInfo(short s, int i) {
            this.fid = s;
            this.buffer = new FragmentBuffer(i);
        }

        public byte[] getBuffer() {
            return this.buffer.getBuffer();
        }

        @Override // net.sf.scuba.smartcards.FileInfo
        public short getFID() {
            return this.fid;
        }

        @Override // net.sf.scuba.smartcards.FileInfo
        public int getFileLength() {
            return this.buffer.getLength();
        }

        public String toString() {
            return Integer.toHexString(this.fid);
        }

        public FragmentBuffer.Fragment getSmallestUnbufferedFragment(int i, int i2) {
            return this.buffer.getSmallestUnbufferedFragment(i, i2);
        }

        public void addFragment(int i, byte[] bArr) {
            this.buffer.addFragment(i, bArr);
        }
    }
}
