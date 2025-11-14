public class Lexer {
    private final String src;
    int pos = 0;  // position index

    public Lexer(String src) {
        this.src = src;
    }

    private char peek() {
        return pos < src.length() ? src.charAt(pos) : '\0';
    }

    private char next() {
        return pos < src.length() ? src.charAt(pos++) : '\0';
    }

    public Token nextToken() {
        char c = peek();

        // skip whitespace
        while (Character.isWhitespace(c)) {
            next();
            c = peek();
        }

        if (c == '\0')
            return Token.EOF;

        // number
        if (Character.isDigit(c))
            return Token.NUMBER;

        // identifier (basic)
        if (Character.isAlphabetic(c))
            return Token.IDENT;

        // punctuation
        switch (next()) {
            case '{': return Token.LBRACE;
            case '}': return Token.RBRACE;
            case '(': return Token.LPAREN;
            case ')': return Token.RPAREN;
            case ';': return Token.SEMI;
            case '+': return Token.PLUS;
            case '-': return Token.MINUS;
            default:
                throw new RuntimeException("Unknown character: " + c);
        }
    }

    // read full integer literal after seeing NUMBER token
    public int readNumber() {
        int start = pos;

        while (pos < src.length() && Character.isDigit(src.charAt(pos))) {
            pos++;
        }

        return Integer.parseInt(src.substring(start, pos));
    }
}
