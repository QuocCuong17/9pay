package net.sf.scuba.smartcards;

/* loaded from: classes5.dex */
public interface APDUWrapper {
    String getType();

    ResponseAPDU unwrap(ResponseAPDU responseAPDU);

    CommandAPDU wrap(CommandAPDU commandAPDU);
}
