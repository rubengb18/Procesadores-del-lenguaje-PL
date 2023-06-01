package ast;

import tablasimbolos.TablaSimbolos;

public class Typedef extends TDEF {
   private T tipo;
   private E alias;
   private TDEF tdef;
   private TDEF l;
   
   public Typedef( T opnd2,E alias) {
     this.tipo = opnd2;
     this.alias = alias;
   }
   public Typedef( T opnd2,E alias,TDEF l) {
	     this.tipo = opnd2;
	     this.alias = alias;
	     this.l=l;
	   }
   public Typedef(TDEF opnd1, TDEF opnd2) {
	     this.tdef = opnd1;
	     this.l = opnd2;
 }
   
   public TDEF opnd() {return tdef;}
   public T opnd2() {return tipo;}
   public E opnd3() {return alias;}
   public TDEF lista() {return l;}
  
public String toString() {
	if(l==null) {
		return "typedef("+opnd2().toString()+","+opnd3().toString()+")";
	}
	else {
		return opnd().toString()+","+lista().toString();
	}
	
}
@Override
public KindTDEF kind() {
	return KindTDEF.TYPEDEF;
}
@Override
public void bind(TablaSimbolos tabla) {
	if(l==null) {
		tabla.insertaID(alias.id(), tipo);
		alias.bind(tabla);
	}
	else {
		tdef.bind(tabla);
		l.bind(tabla);
	}
}
@Override
public Tipo type() {
	return (Tipo) tipo;
}
@Override
public String generateCode(){
	return "";
}
}
