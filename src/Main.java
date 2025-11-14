public class Main {
    public static void main(String[] args) {
        String code = "1 + 2 - 1";

        Lexer lx = new Lexer(code);
        Parser ps = new Parser(lx);
        Node ast = ps.parseExpr();

        System.out.println("AST OK: " + ast.getClass().getSimpleName());
    }
}
