package ast;

import tablasimbolos.TablaSimbolos;

public class If extends I{
	   private E exp;
	   private I bloq;
	   private I bloq2;
	   
	   public If(E opnd1, I opnd2) {
	     this.exp = opnd1;
	     this.bloq = opnd2;
	   }
	   
	   public If(E opnd1, I opnd2,I opnd3) {
		     this.exp = opnd1;
		     this.bloq = opnd2;
		     this.bloq2 = opnd3;
		   }
	   
	   public I opndBloq() {return bloq;}
	   public E opndExp() {return exp;}
	   public I opndBloqElse() {return bloq2;}


	public String toString() {
		if(bloq2==null) {
			return "if("+opndExp().toString() + "," +opndBloq().toString()+")";
		}
		else {
			return "if("+opndExp().toString() + "," +opndBloq().toString()+"), else("+opndBloqElse().toString()+")";
		}
	}

	public KindI kind() {
		return KindI.IF;
	}

	@Override
	public void bind(TablaSimbolos tabla) {
		exp.bind(tabla);
		tabla.abreBloque();
		bloq.bind(tabla);
		if(bloq2!=null) {
			tabla.abreBloque();
			bloq2.bind(tabla);
			tabla.cierraBloque();
		}
		tabla.cierraBloque();
	}

	@Override
	public Tipo type() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateCode() {
		String codigo="";
		codigo+=exp.generateCode();
		codigo+="if\r\n"+bloq.generateCode();
		if(bloq2!=null) {
			codigo+="else\r\n"+bloq2.generateCode();
		}
		codigo+="end\r\n";
		return codigo;
	}

}
