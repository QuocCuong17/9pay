package net.sf.scuba.smartcards;

import java.util.EventListener;

/* loaded from: classes5.dex */
public interface CardTerminalListener extends EventListener {
    void cardInserted(CardEvent cardEvent);

    void cardRemoved(CardEvent cardEvent);
}
