package ast;

import tablasimbolos.TablaSimbolos;

public abstract class P implements ASTNode{
	public abstract KindP kind();
    public E opndIden() {throw new UnsupportedOperationException("opnd2");}
	public T opndTipo() {throw new UnsupportedOperationException("opnd2");}
	public String opndAm() {throw new UnsupportedOperationException("opnd2");}
	public P opndParam() {throw new UnsupportedOperationException("opnd2");}
	public P opndLista() {throw new UnsupportedOperationException("opnd2");}
    public NodeKind nodeKind() {return NodeKind.TIPO;}
    public String toString() {return "";}
	public int calculaDeltas(int localStart) {
		throw new UnsupportedOperationException("eerror");
		// TODO Auto-generated method stub
		
	}
	public Object getAmpersand() {
		// TODO Auto-generated method stub
		return null;
	}
}
