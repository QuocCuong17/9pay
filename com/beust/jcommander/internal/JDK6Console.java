package com.beust.jcommander.internal;

import com.beust.jcommander.ParameterException;
import java.io.PrintWriter;

/* loaded from: classes2.dex */
public class JDK6Console implements Console {
    private Object console;
    private PrintWriter writer;

    public JDK6Console(Object obj) throws Exception {
        this.console = obj;
        this.writer = (PrintWriter) obj.getClass().getDeclaredMethod("writer", new Class[0]).invoke(obj, new Object[0]);
    }

    @Override // com.beust.jcommander.internal.Console
    public void print(String str) {
        this.writer.print(str);
    }

    @Override // com.beust.jcommander.internal.Console
    public void println(String str) {
        this.writer.println(str);
    }

    @Override // com.beust.jcommander.internal.Console
    public char[] readPassword(boolean z) {
        try {
            this.writer.flush();
            if (z) {
                return ((String) this.console.getClass().getDeclaredMethod("readLine", new Class[0]).invoke(this.console, new Object[0])).toCharArray();
            }
            return (char[]) this.console.getClass().getDeclaredMethod("readPassword", new Class[0]).invoke(this.console, new Object[0]);
        } catch (Exception e) {
            throw new ParameterException(e);
        }
    }
}
