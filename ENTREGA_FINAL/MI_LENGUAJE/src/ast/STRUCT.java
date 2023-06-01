package ast;

public abstract class STRUCT implements ASTNode{
	public abstract KindSTRUCT kind();
	public STRUCT opndLista() {	throw new UnsupportedOperationException("opndLista");}
	public STRUCT opndSt() {throw new UnsupportedOperationException("opndSt");}
	public E opnd2() {throw new UnsupportedOperationException("opnd2");} 
	public I opnd3() {throw new UnsupportedOperationException("opnd3");}
	public NodeKind nodeKind() {return NodeKind.STRUCT;}
    public String toString() {return "";}
}
