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

    public Node parseExpr() {
        Node node = parseTerm();
        while (current == Token.PLUS || current == Token.MINUS) {
            Token op = current;
            eat(op);
            node = new BinaryNode(node, op, parseTerm());
        }
        return node;
    }

    private Node parseTerm() {
        if (current == Token.NUMBER) {
            int v = Integer.parseInt(lexer.src.substring(lexer.pos - 1, lexer.pos));
            eat(Token.NUMBER);
            return new NumberNode(v);
        }
        throw new RuntimeException("Invalid term");
    }
}
