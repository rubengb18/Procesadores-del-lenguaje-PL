package ast;

import tablasimbolos.TablaSimbolos;

public class Lec extends I{
	private E id;
	   public Lec(E id) {
	     this.id = id;
	   }
	   public E opnd1() {return id;}
	   
	   public String toString() {
				return "lec("+opnd1().toString()+")";
	   }
	@Override
	public KindI kind() {
		// TODO Auto-generated method stub
		return KindI.LEC;
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
		String codigo=id.generateCode()+"call $read\r\ni32.store\r\n";
		return codigo;
	}
}
