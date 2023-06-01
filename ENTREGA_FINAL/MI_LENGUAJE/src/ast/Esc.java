package ast;

import tablasimbolos.TablaSimbolos;

public class Esc extends I{
	   private E id;
	   public Esc(E id) {
	     this.id = id;
	   }
	   public E opnd1() {return id;}
	   
	   public String toString() {
				return "esc("+opnd1().toString()+")";
	   }
	@Override
	public KindI kind() {
		// TODO Auto-generated method stub
		return KindI.ESC;
	}
	@Override
	public void bind(TablaSimbolos tabla) {
		id.bind(tabla);
	}
	@Override
	public Tipo type() {
		// TODO Auto-generated method stub
		return id.type();
	}
	@Override
	public String generateCode() {
		String codigo=id.generateCode()+"i32.load\r\ncall $print\r\n";
		return codigo;
	}

}
