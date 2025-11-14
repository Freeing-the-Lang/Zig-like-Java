public class Lexer {
    private final String src;
    int pos = 0;  // Parser가 필요하면 public으로 바꿀 수 있음

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

        // whitespace skip
        while (Character.isWhitespace(c)) {
            next();
            c = peek();
        }

        if (c == '\0')
            return Token.EOF;

        // number
        if (Character.isDigit(c))
            return Token.NUMBER;

        // ident (추가할 때 확장하도록 유지)
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
                throw new RuntimeException("Unknown char: " + c);
        }
    }

    // Parser에서 실제 숫자 값 읽을 때 사용하는 함수
    public int readNumber() {
        int start = pos;

        // 첫 nextToken() 단계에서 숫자로 확정 됨 → 실제 숫자 consume
        while (pos < src.length() && Character.isDigit(src.charAt(pos))) {
            pos++;
        }

        return Integer.parseInt(src.substring(start, pos));
    }
}
