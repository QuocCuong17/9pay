package com.google.common.io;

import java.io.IOException;
import org.apache.commons.io.IOUtils;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
abstract class LineBuffer {
    private StringBuilder line = new StringBuilder();
    private boolean sawReturn;

    protected abstract void handleLine(String line, String end) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void add(char[] cbuf, int off, int len) throws IOException {
        int i;
        int i2;
        if (this.sawReturn && len > 0) {
            if (finishLine(cbuf[off] == '\n')) {
                i = off + 1;
                i2 = off + len;
                int i3 = i;
                while (i < i2) {
                    char c = cbuf[i];
                    if (c == '\n') {
                        this.line.append(cbuf, i3, i - i3);
                        finishLine(true);
                    } else if (c != '\r') {
                        i++;
                    } else {
                        this.line.append(cbuf, i3, i - i3);
                        this.sawReturn = true;
                        int i4 = i + 1;
                        if (i4 < i2) {
                            if (finishLine(cbuf[i4] == '\n')) {
                                i = i4;
                            }
                        }
                    }
                    i3 = i + 1;
                    i++;
                }
                this.line.append(cbuf, i3, i2 - i3);
            }
        }
        i = off;
        i2 = off + len;
        int i32 = i;
        while (i < i2) {
        }
        this.line.append(cbuf, i32, i2 - i32);
    }

    private boolean finishLine(boolean sawNewline) throws IOException {
        handleLine(this.line.toString(), this.sawReturn ? sawNewline ? IOUtils.LINE_SEPARATOR_WINDOWS : "\r" : sawNewline ? IOUtils.LINE_SEPARATOR_UNIX : "");
        this.line = new StringBuilder();
        this.sawReturn = false;
        return sawNewline;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void finish() throws IOException {
        if (this.sawReturn || this.line.length() > 0) {
            finishLine(false);
        }
    }
}
