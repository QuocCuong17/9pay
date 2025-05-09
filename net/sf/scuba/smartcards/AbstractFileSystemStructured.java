package net.sf.scuba.smartcards;

/* loaded from: classes5.dex */
public abstract class AbstractFileSystemStructured implements FileSystemStructured {
    public static final short MF_ID = 16128;
    private ISOFileInfo fileInfo;
    private int length;
    private int p2;
    private int selectLe;
    private short selectedFID;
    private CardService service;

    @Override // net.sf.scuba.smartcards.FileSystemStructured
    public abstract byte[] readBinary(int i, int i2);

    public AbstractFileSystemStructured(CardService cardService) {
        this.service = null;
        this.selectedFID = (short) 0;
        this.length = -1;
        this.p2 = 0;
        this.selectLe = 256;
        this.fileInfo = null;
        this.service = cardService;
    }

    public AbstractFileSystemStructured(CardService cardService, boolean z) {
        this.service = null;
        this.selectedFID = (short) 0;
        this.length = -1;
        this.p2 = 0;
        this.selectLe = 256;
        this.fileInfo = null;
        this.service = cardService;
        this.p2 = z ? 0 : 12;
        this.selectLe = z ? 256 : 0;
    }

    public int getFileLength() throws CardServiceException {
        return this.length;
    }

    public short getSelectedFID() {
        return this.selectedFID;
    }

    private void selectFile(byte[] bArr, int i) throws CardServiceException {
        ResponseAPDU transmit = this.service.transmit(createSelectFileAPDU(i, this.p2, bArr, this.selectLe));
        int sw = transmit.getSW();
        byte[] data = transmit.getData();
        if (sw != -28672) {
            throw new CardServiceException("File could not be selected.", sw);
        }
        ISOFileInfo iSOFileInfo = new ISOFileInfo(data);
        this.fileInfo = iSOFileInfo;
        if (iSOFileInfo.fid != -1) {
            this.selectedFID = this.fileInfo.fid;
        }
        if (this.fileInfo.fileLength != -1) {
            this.length = this.fileInfo.fileLength;
        }
    }

    private void selectFile(short s, int i) throws CardServiceException {
        selectFile(s == 0 ? new byte[0] : new byte[]{(byte) ((s >> 8) & 255), (byte) (s & 255)}, i);
    }

    @Override // net.sf.scuba.smartcards.FileSystemStructured
    public void selectFile(short s) throws CardServiceException {
        selectFile(s, 0);
    }

    public void selectMF() throws CardServiceException {
        selectFile((short) 0, 0);
    }

    public void selectParent() throws CardServiceException {
        selectFile((short) 0, 3);
    }

    public void selectEFRelative(short s) throws CardServiceException {
        selectFile(s, 2);
    }

    public void selectDFRelative(short s) throws CardServiceException {
        selectFile(s, 1);
    }

    public void selectAID(byte[] bArr) throws CardServiceException {
        selectFile(bArr, 4);
    }

    public void selectPath(byte[] bArr) throws CardServiceException {
        selectFile(bArr, 8);
    }

    public void selectPathRelative(byte[] bArr) throws CardServiceException {
        selectFile(bArr, 9);
    }

    private CommandAPDU createSelectFileAPDU(int i, int i2, byte[] bArr, int i3) {
        if (i3 == 0) {
            return new CommandAPDU(0, -92, i, i2, bArr);
        }
        return new CommandAPDU(0, -92, i, i2, bArr, i3);
    }
}
