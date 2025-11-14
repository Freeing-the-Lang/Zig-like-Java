public class Lexer {
    private final String src;
    private int pos = 0;

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

        while (Character.isWhitespace(c)) {
            next();
            c = peek();
        }

        if (c == '\0') return Token.EOF;
        if (Character.isDigit(c)) return scanNumber();
        if (Character.isAlphabetic(c)) return scanIdent();

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

    private Token scanNumber() {
        while (Character.isDigit(peek())) next();
        return Token.NUMBER;
    }

    private Token scanIdent() {
        while (Character.isAlphabetic(peek())) next();
        return Token.IDENT;
    }
}
