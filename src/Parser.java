public class Parser {
    private final Lexer lexer;
    private Token current;

    public Parser(Lexer lx) {
        this.lexer = lx;
        this.current = lx.nextToken();
    }

    private void eat(Token t) {
        if (current != t)
            throw new RuntimeException("Expected " + t + " but got " + current);
        current = lexer.nextToken();
    }

    public Expr parseExpr() {
        Expr node = parseTerm();
        while (current == Token.PLUS || current == Token.MINUS) {
            Token op = current;
            eat(op);
            node = new BinaryExpr(node, op, parseTerm());
        }
        return node;
    }

    private Expr parseTerm() {
        if (current == Token.NUMBER) {
            int v = lexer.readNumber();     // ★ 여기 중요
            return new IntLiteral(v);
        }
        throw new RuntimeException("Invalid term");
    }
}
