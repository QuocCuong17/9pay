package net.sf.scuba.smartcards;

/* loaded from: classes5.dex */
public interface FileSystemStructured {
    FileInfo[] getSelectedPath() throws CardServiceException;

    byte[] readBinary(int i, int i2) throws CardServiceException;

    void selectFile(short s) throws CardServiceException;
}
