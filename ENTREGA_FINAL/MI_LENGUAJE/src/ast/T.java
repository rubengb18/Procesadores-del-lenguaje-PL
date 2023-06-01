package ast;

public abstract class T implements ASTNode {
    public abstract KindT kind();
    public T opnd1() {throw new UnsupportedOperationException("opnd1");} 
    public T opnd2() {throw new UnsupportedOperationException("opnd2");} 
    public NodeKind nodeKind() {return NodeKind.TIPO;}
    public String toString() {return "";}

}
