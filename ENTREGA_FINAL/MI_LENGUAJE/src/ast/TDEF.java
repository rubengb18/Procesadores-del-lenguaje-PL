package ast;

public abstract class TDEF implements ASTNode{
    public abstract KindTDEF kind();
    public TDEF opnd() {throw new UnsupportedOperationException("opnd1");} 
    public T opnd2() {throw new UnsupportedOperationException("opnd2");} 
    public E opnd3() {throw new UnsupportedOperationException("opnd3");}
    public TDEF lista() {throw new UnsupportedOperationException("opnd4");}
    public NodeKind nodeKind() {return NodeKind.TYPEDEF;}
    public String toString() {return "";}
}
