package org.apache.pdfbox.contentstream.operator.state;

import java.io.IOException;

/* loaded from: classes5.dex */
public final class EmptyGraphicsStackException extends IOException {
    private static final long serialVersionUID = 1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EmptyGraphicsStackException() {
        super("Cannot execute restore, the graphics stack is empty");
    }
}
