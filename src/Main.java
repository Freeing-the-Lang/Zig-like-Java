public class Main {
    public static void main(String[] args) {
        String code = "1 + 2 - 1";

        Lexer lx = new Lexer(code);
        Parser ps = new Parser(lx);
        Expr ast = ps.parseExpr();   // ✔ 고침

        System.out.println("AST OK: " + ast.getClass().getSimpleName());
    }
}
