package ast;

import tablasimbolos.TablaSimbolos;

public class Tipo extends T{
	  private String v;
	  private T opnd;
	  private int tam=0;
	  public Tipo(String v) {
		  this.v = v; 
		  if(v.equals("int")) {
			  tam=4;
		  }
		  else if(v.equals("bool")) {
			  tam=4;
		  }
	  }
	  
	  
	  public Tipo(String v, T opnd) {
		   this.v = v;   
		   this.opnd=opnd;
		  }

	  
	  public Tipo(String v, T opnd,E e) {
		   this.v = v;   
		   this.opnd=opnd;
		   this.tam=Integer.parseInt(e.toString());
	  }
	  public int getTam() {
		  return tam;
	  }
	  
	  public int calculaTam() {
		  if(opnd()!=null) {
			  if(opnd().kind()!=KindT.B && opnd().kind()!=KindT.INT_0) {
				  return tam*((Tipo) opnd()).getTam();
			  }
		  }
		  return tam;
	  }

		public void setTam(int i) {
			this.tam=i;
		} 
	  public String tipo() {return v;}
	  public T opnd() {return opnd;}
	  public KindT kind() {
		  if(v.equals("int")) {
			  return KindT.INT_0;
		  }
		  else if(v.equals("bool")) {
			  return KindT.B;
		  }
		  else if(v.equals("void")) {
			  return KindT.VOID;
		  }
		  else if(v.equals("array")){
			  return KindT.ARRAY;
		  }
		  else {
			  return KindT.STRUCT;
		  }
	  }   
	  public String toString() {
		  if(opnd==null) {
			  return v;
		  }
		  else {
			  return "array("+opnd().toString()+")";
		  }
	  }
	@Override
	public void bind(TablaSimbolos tabla) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Tipo type() {
		return (Tipo) opnd;
	}  
	
	public Tipo getTipoSimple() {
		  if(v.equals("array")){
			  return ((Tipo) opnd).getTipoSimple();
		  }
		  else {
			  return this;
		  }
	}
	@Override
	public String generateCode() {
		// TODO Auto-generated method stub
		return null;
	}


}
