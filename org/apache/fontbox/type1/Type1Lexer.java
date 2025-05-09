package org.apache.fontbox.type1;

import android.util.Log;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.commons.io.IOUtils;

/* loaded from: classes5.dex */
class Type1Lexer {
    private final ByteBuffer buffer;
    private int openParens = 0;
    private Token aheadToken = readToken(null);

    public Type1Lexer(byte[] bArr) throws IOException {
        this.buffer = ByteBuffer.wrap(bArr);
    }

    public Token nextToken() throws IOException {
        Token token = this.aheadToken;
        this.aheadToken = readToken(token);
        return token;
    }

    public Token peekToken() {
        return this.aheadToken;
    }

    private char getChar() {
        return (char) this.buffer.get();
    }

    private Token readToken(Token token) throws IOException {
        boolean z;
        do {
            z = false;
            while (this.buffer.hasRemaining()) {
                char c = getChar();
                if (c == '%') {
                    readComment();
                } else {
                    if (c == '(') {
                        return readString();
                    }
                    if (c == ')') {
                        throw new IOException("unexpected closing parenthesis");
                    }
                    if (c == '[') {
                        return new Token(c, Token.START_ARRAY);
                    }
                    if (c == '{') {
                        return new Token(c, Token.START_PROC);
                    }
                    if (c == ']') {
                        return new Token(c, Token.END_ARRAY);
                    }
                    if (c == '}') {
                        return new Token(c, Token.END_PROC);
                    }
                    if (c == '/') {
                        return new Token(readRegular(), Token.LITERAL);
                    }
                    if (!Character.isWhitespace(c)) {
                        if (c == 0) {
                            Log.w("PdfBoxAndroid", "NULL byte in font, skipped");
                        } else {
                            ByteBuffer byteBuffer = this.buffer;
                            byteBuffer.position(byteBuffer.position() - 1);
                            Token tryReadNumber = tryReadNumber();
                            if (tryReadNumber != null) {
                                return tryReadNumber;
                            }
                            String readRegular = readRegular();
                            if (readRegular == null) {
                                throw new DamagedFontException("Could not read token at position " + this.buffer.position());
                            }
                            if (readRegular.equals("RD") || readRegular.equals("-|")) {
                                if (token.getKind() == Token.INTEGER) {
                                    return readCharString(token.intValue());
                                }
                                throw new IOException("expected INTEGER before -| or RD");
                            }
                            return new Token(readRegular, Token.NAME);
                        }
                    }
                    z = true;
                }
            }
        } while (z);
        return null;
    }

    private Token tryReadNumber() {
        char c;
        StringBuilder sb;
        this.buffer.mark();
        StringBuilder sb2 = new StringBuilder();
        char c2 = getChar();
        boolean z = false;
        if (c2 == '+' || c2 == '-') {
            sb2.append(c2);
            c2 = getChar();
        }
        while (Character.isDigit(c2)) {
            sb2.append(c2);
            c2 = getChar();
            z = true;
        }
        if (c2 == '.') {
            sb2.append(c2);
            c = getChar();
            sb = null;
        } else if (c2 == '#') {
            StringBuilder sb3 = new StringBuilder();
            c = getChar();
            sb = sb2;
            sb2 = sb3;
        } else {
            if (sb2.length() == 0 || !z) {
                this.buffer.reset();
                return null;
            }
            ByteBuffer byteBuffer = this.buffer;
            byteBuffer.position(byteBuffer.position() - 1);
            return new Token(sb2.toString(), Token.INTEGER);
        }
        if (Character.isDigit(c)) {
            sb2.append(c);
            char c3 = getChar();
            while (Character.isDigit(c3)) {
                sb2.append(c3);
                c3 = getChar();
            }
            if (c3 == 'E') {
                sb2.append(c3);
                char c4 = getChar();
                if (c4 == '-') {
                    sb2.append(c4);
                    c4 = getChar();
                }
                if (Character.isDigit(c4)) {
                    sb2.append(c4);
                    char c5 = getChar();
                    while (Character.isDigit(c5)) {
                        sb2.append(c5);
                        c5 = getChar();
                    }
                } else {
                    this.buffer.reset();
                    return null;
                }
            }
            ByteBuffer byteBuffer2 = this.buffer;
            byteBuffer2.position(byteBuffer2.position() - 1);
            if (sb != null) {
                return new Token(Integer.valueOf(Integer.parseInt(sb2.toString(), Integer.parseInt(sb.toString()))).toString(), Token.INTEGER);
            }
            return new Token(sb2.toString(), Token.REAL);
        }
        this.buffer.reset();
        return null;
    }

    private String readRegular() {
        StringBuilder sb = new StringBuilder();
        while (this.buffer.hasRemaining()) {
            this.buffer.mark();
            char c = getChar();
            if (Character.isWhitespace(c) || c == '(' || c == ')' || c == '<' || c == '>' || c == '[' || c == ']' || c == '{' || c == '}' || c == '/' || c == '%') {
                this.buffer.reset();
                break;
            }
            sb.append(c);
        }
        String sb2 = sb.toString();
        if (sb2.length() == 0) {
            return null;
        }
        return sb2;
    }

    private String readComment() {
        char c;
        StringBuilder sb = new StringBuilder();
        while (this.buffer.hasRemaining() && (c = getChar()) != '\r' && c != '\n') {
            sb.append(c);
        }
        return sb.toString();
    }

    private Token readString() {
        StringBuilder sb = new StringBuilder();
        while (this.buffer.hasRemaining()) {
            char c = getChar();
            if (c == '(') {
                this.openParens++;
                sb.append('(');
            } else if (c == ')') {
                if (this.openParens == 0) {
                    return new Token(sb.toString(), Token.STRING);
                }
                sb.append(')');
                this.openParens--;
            } else if (c == '\\') {
                char c2 = getChar();
                if (c2 == '(') {
                    sb.append('(');
                } else if (c2 == ')') {
                    sb.append(')');
                } else if (c2 == '\\') {
                    sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                } else if (c2 == 'b') {
                    sb.append('\b');
                } else if (c2 == 'f') {
                    sb.append('\f');
                } else if (c2 == 'n' || c2 == 'r') {
                    sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                } else if (c2 == 't') {
                    sb.append('\t');
                }
                if (Character.isDigit(c2)) {
                    sb.append((char) Integer.valueOf(Integer.parseInt(String.valueOf(new char[]{c2, getChar(), getChar()}), 8)).intValue());
                }
            } else if (c == '\r' || c == '\n') {
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            } else {
                sb.append(c);
            }
        }
        return null;
    }

    private Token readCharString(int i) {
        this.buffer.get();
        byte[] bArr = new byte[i];
        this.buffer.get(bArr);
        return new Token(bArr, Token.CHARSTRING);
    }
}
