package ast;

import tablasimbolos.TablaSimbolos;

public class Bool extends E {
  private String v;
  public Bool(String v) {
   this.v = v;   
  }
  public String bool() {return v;}
  public KindE kind() {return KindE.BOOL;}   
  public String toString() {return v;}
@Override
public void bind(TablaSimbolos tabla) {
	// TODO Auto-generated method stub
	
}
@Override
public Tipo type() {
	// TODO Auto-generated method stub
	return new Tipo("bool");
}
@Override
public String generateCode() {
	String codigo="i32.const ";
	if(v.contentEquals("true")) {
		codigo+="1\r\n";
	}
	else {
		codigo+="0\r\n";
	}
	return codigo;
}  
}
