package ast;

import tablasimbolos.TablaSimbolos;

public class While extends I{
	   private E exp;
	   private I bloq;
	   
	   public While(E opnd1, I opnd2) {
	     this.exp = opnd1;
	     this.bloq = opnd2;
	   }
	   
	   public I opndBloq() {return bloq;}
	   public E opndExp() {return exp;}

	public String toString() {
			return "while("+opndExp().toString() + "," +opndBloq().toString()+")";
	}

	public KindI kind() {
		
		return KindI.WHILE;
	}

	@Override
	public void bind(TablaSimbolos tabla) {
		tabla.abreBloque();
		exp.bind(tabla);
		bloq.bind(tabla);
		tabla.cierraBloque();
	}

	@Override
	public Tipo type() {
		return bloq.type();
	}

	@Override
	public String generateCode() {
		String codigo="";
		codigo+="block\r\nloop\r\n";
		codigo+=exp.generateCode();
		codigo+="i32.eqz\r\nbr_if 1\r\n";
		codigo+=bloq.generateCode();
		codigo+="br 0\r\nend\r\nend\r\n";
		return codigo;
	}
}
