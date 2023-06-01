package ast;

public abstract class D implements ASTNode {
    public abstract KindD kind();
	public E opndIden() {throw new UnsupportedOperationException("opndIden");}
	public T opndTipo() {throw new UnsupportedOperationException("opndTipo");}
	public I opndBloq() {throw new UnsupportedOperationException("opndBloq");}
	public I opndRet() {throw new UnsupportedOperationException("opndRet");}
	public P opndParam() {throw new UnsupportedOperationException("opndParam");}
	public D opnd() {throw new UnsupportedOperationException("opnd");}
    public D opndLista() {throw new UnsupportedOperationException("opndLista");}
    public NodeKind nodeKind() {return NodeKind.DEF;}
    public String toString() {return "";}

}
