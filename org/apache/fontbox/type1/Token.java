package org.apache.fontbox.type1;

import com.facebook.internal.ServerProtocol;

/* loaded from: classes5.dex */
class Token {
    private byte[] data;
    private Kind kind;
    private String text;
    static final Kind STRING = Kind.STRING;
    static final Kind NAME = Kind.NAME;
    static final Kind LITERAL = Kind.LITERAL;
    static final Kind REAL = Kind.REAL;
    static final Kind INTEGER = Kind.INTEGER;
    static final Kind START_ARRAY = Kind.START_ARRAY;
    static final Kind END_ARRAY = Kind.END_ARRAY;
    static final Kind START_PROC = Kind.START_PROC;
    static final Kind END_PROC = Kind.END_PROC;
    static final Kind CHARSTRING = Kind.CHARSTRING;

    /* loaded from: classes5.dex */
    enum Kind {
        NONE,
        STRING,
        NAME,
        LITERAL,
        REAL,
        INTEGER,
        START_ARRAY,
        END_ARRAY,
        START_PROC,
        END_PROC,
        CHARSTRING
    }

    public Token(String str, Kind kind) {
        this.text = str;
        this.kind = kind;
    }

    public Token(char c, Kind kind) {
        this.text = Character.toString(c);
        this.kind = kind;
    }

    public Token(byte[] bArr, Kind kind) {
        this.data = bArr;
        this.kind = kind;
    }

    public String getText() {
        return this.text;
    }

    public Kind getKind() {
        return this.kind;
    }

    public int intValue() {
        return (int) Float.parseFloat(this.text);
    }

    public float floatValue() {
        return Float.parseFloat(this.text);
    }

    public boolean booleanValue() {
        return this.text.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
    }

    public byte[] getData() {
        return this.data;
    }

    public String toString() {
        if (this.kind == CHARSTRING) {
            return "Token[kind=CHARSTRING, data=" + this.data.length + " bytes]";
        }
        return "Token[kind=" + this.kind + ", text=" + this.text + "]";
    }
}
