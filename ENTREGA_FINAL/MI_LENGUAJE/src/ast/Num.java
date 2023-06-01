package ast;

import tablasimbolos.TablaSimbolos;

public class Num extends E {
  private String v;
  
  public Num(String v) {
   this.v = v;   
  }
  public String num() {return v;}
  public KindE kind() {return KindE.NUM;}   
  public String toString() {return v;}
@Override
public void bind(TablaSimbolos tabla) {
	// TODO Auto-generated method stub
	
}
@Override
public Tipo type() {
	// TODO Auto-generated method stub
	return new Tipo("int");
}
@Override
public String generateCode() {
	String codigo="i32.const "+v+"\r\n";
	return codigo;
}  
}
