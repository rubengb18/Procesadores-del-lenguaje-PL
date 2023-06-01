package ast;

import tablasimbolos.TablaSimbolos;

public class Ret extends I{
	private E id;
	public Ret(E id) {
    this.id = id;
  }
  public E opnd1() {return id;}
  
  public String toString() {
			return "ret("+opnd1().toString()+")";
  }
@Override
public KindI kind() {
	// TODO Auto-generated method stub
	return KindI.RET;
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
	return id.generateCode()+"i32.load\r\nreturn\r\n";
}
}
