package org.apache.pdfbox.pdmodel.common.function.type4;

/* loaded from: classes5.dex */
public final class Parser {

    /* loaded from: classes5.dex */
    public static abstract class AbstractSyntaxHandler implements SyntaxHandler {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Parser.SyntaxHandler
        public void comment(CharSequence charSequence) {
        }

        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Parser.SyntaxHandler
        public void newLine(CharSequence charSequence) {
        }

        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Parser.SyntaxHandler
        public void whitespace(CharSequence charSequence) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public enum State {
        NEWLINE,
        WHITESPACE,
        COMMENT,
        TOKEN
    }

    /* loaded from: classes5.dex */
    public interface SyntaxHandler {
        void comment(CharSequence charSequence);

        void newLine(CharSequence charSequence);

        void token(CharSequence charSequence);

        void whitespace(CharSequence charSequence);
    }

    private Parser() {
    }

    public static void parse(CharSequence charSequence, SyntaxHandler syntaxHandler) {
        new Tokenizer(charSequence, syntaxHandler, null).tokenize();
    }

    /* loaded from: classes5.dex */
    private static final class Tokenizer {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final char CR = '\r';
        private static final char EOT = 4;
        private static final char FF = '\f';
        private static final char LF = '\n';
        private static final char NUL = 0;
        private static final char SPACE = ' ';
        private static final char TAB = '\t';
        private final StringBuilder buffer;
        private final SyntaxHandler handler;
        private int index;
        private final CharSequence input;
        private State state;

        /* synthetic */ Tokenizer(CharSequence charSequence, SyntaxHandler syntaxHandler, AnonymousClass1 anonymousClass1) {
            this(charSequence, syntaxHandler);
        }

        private Tokenizer(CharSequence charSequence, SyntaxHandler syntaxHandler) {
            this.state = State.WHITESPACE;
            this.buffer = new StringBuilder();
            this.input = charSequence;
            this.handler = syntaxHandler;
        }

        private boolean hasMore() {
            return this.index < this.input.length();
        }

        private char currentChar() {
            return this.input.charAt(this.index);
        }

        private char nextChar() {
            this.index++;
            return !hasMore() ? EOT : currentChar();
        }

        private char peek() {
            return this.index < this.input.length() + (-1) ? this.input.charAt(this.index + 1) : EOT;
        }

        private State nextState() {
            char currentChar = currentChar();
            if (currentChar != 0 && currentChar != ' ') {
                if (currentChar == '%') {
                    this.state = State.COMMENT;
                } else if (currentChar != '\t') {
                    if (currentChar == '\n' || currentChar == '\f' || currentChar == '\r') {
                        this.state = State.NEWLINE;
                    } else {
                        this.state = State.TOKEN;
                    }
                }
                return this.state;
            }
            this.state = State.WHITESPACE;
            return this.state;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void tokenize() {
            while (hasMore()) {
                this.buffer.setLength(0);
                nextState();
                int i = AnonymousClass1.$SwitchMap$org$apache$pdfbox$pdmodel$common$function$type4$Parser$State[this.state.ordinal()];
                if (i == 1) {
                    scanNewLine();
                } else if (i == 2) {
                    scanWhitespace();
                } else if (i == 3) {
                    scanComment();
                } else {
                    scanToken();
                }
            }
        }

        private void scanNewLine() {
            char currentChar = currentChar();
            this.buffer.append(currentChar);
            if (currentChar == '\r' && peek() == '\n') {
                this.buffer.append(nextChar());
            }
            this.handler.newLine(this.buffer);
            nextChar();
        }

        private void scanWhitespace() {
            char nextChar;
            this.buffer.append(currentChar());
            while (hasMore() && ((nextChar = nextChar()) == 0 || nextChar == '\t' || nextChar == ' ')) {
                this.buffer.append(nextChar);
            }
            this.handler.whitespace(this.buffer);
        }

        private void scanComment() {
            char nextChar;
            this.buffer.append(currentChar());
            while (hasMore() && (nextChar = nextChar()) != '\n' && nextChar != '\f' && nextChar != '\r') {
                this.buffer.append(nextChar);
            }
            this.handler.comment(this.buffer);
        }

        private void scanToken() {
            char nextChar;
            char currentChar = currentChar();
            this.buffer.append(currentChar);
            if (currentChar == '{' || currentChar == '}') {
                this.handler.token(this.buffer);
                nextChar();
                return;
            }
            while (hasMore() && (nextChar = nextChar()) != 0 && nextChar != 4 && nextChar != ' ' && nextChar != '{' && nextChar != '}' && nextChar != '\t' && nextChar != '\n' && nextChar != '\f' && nextChar != '\r') {
                this.buffer.append(nextChar);
            }
            this.handler.token(this.buffer);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.apache.pdfbox.pdmodel.common.function.type4.Parser$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$pdfbox$pdmodel$common$function$type4$Parser$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$org$apache$pdfbox$pdmodel$common$function$type4$Parser$State = iArr;
            try {
                iArr[State.NEWLINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$pdfbox$pdmodel$common$function$type4$Parser$State[State.WHITESPACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$pdfbox$pdmodel$common$function$type4$Parser$State[State.COMMENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
