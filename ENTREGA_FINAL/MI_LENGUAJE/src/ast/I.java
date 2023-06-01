package ast;

import tablasimbolos.TablaSimbolos;

public abstract class I implements ASTNode {
    public abstract KindI kind();
    public I opndI() {throw new UnsupportedOperationException("opndI");} 
    public I opndLista() {throw new UnsupportedOperationException("opndLista");} 
    public I opndDef(){throw new UnsupportedOperationException("opndDef");} 
    public E opnd1() {throw new UnsupportedOperationException("opnd1");} 
    public E opnd2() {throw new UnsupportedOperationException("opnd2");} 
    public NodeKind nodeKind() {return NodeKind.INSTRUCCION;}
    public String toString() {return "";}
    public I opndListaDef(){throw new UnsupportedOperationException("opndListaDef");}
	public int calculaDeltas(int localStart) {
		throw new UnsupportedOperationException("eerror");
		// TODO Auto-generated method stub
		
	}


}
