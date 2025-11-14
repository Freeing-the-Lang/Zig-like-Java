abstract class Expr {}

class IntLiteral extends Expr {
    public final int value;
    IntLiteral(int value) { this.value = value; }
}

class BinaryExpr extends Expr {
    public final Expr left, right;
    public final Token op;
    BinaryExpr(Expr l, Token op, Expr r) {
        this.left = l;
        this.op = op;
        this.right = r;
    }
}
