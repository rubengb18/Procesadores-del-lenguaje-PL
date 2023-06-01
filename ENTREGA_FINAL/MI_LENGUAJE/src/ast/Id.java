
package ast;

import errors.GestionErroresTiny;
import errors.TipoError;
import tablasimbolos.TablaSimbolos;

public class Id extends E {
	  private String v;
	  private Tipo tipo;
	  private int delta;
	  private ASTNode nodo;
	  private GestionErroresTiny gestion;

	  public Id(String v) {
	   this.v = v;  
	   this.gestion=new GestionErroresTiny();
	  }
	  
	  public String id() {return v;}
	  public KindE kind() {return KindE.ID;}   
	  public String toString() {return v;}
	@Override
	public void bind(TablaSimbolos tabla) {
		if(tabla.buscaId(v)==null) {
			gestion.errorVinc(TipoError.INDEF_VAR);
			System.out.println("La variable "+v +" no esta definida");
		}
		else {
			//this.delta=((Id) tabla.buscaId(v)).getDelta();
			nodo=(ASTNode) tabla.buscaId(v);
		}
		
	}
	public ASTNode getNodo() {
		return nodo;
	}
	public void setNodo(ASTNode nodo) {
		this.nodo = nodo;
	}
	@Override
	public Tipo type() {
		return tipo;
	}  
	public void setTipo(Tipo t) {
		tipo=t;
	}
	@Override
	public String generateCode() {
		String codigo="";
		if(nodo instanceof Param){
			if(((Param) nodo).opndAm()!=null) {
				codigo+="i32.const "+((Param) nodo).getDelta()*4+"\r\n";
				codigo+="get_local $localsStart\r\ni32.add	;;"+v+"\r\n"+"i32.load\r\n";
			}
			else {
				codigo+="i32.const "+((Param) nodo).getDelta()*4+"\r\n";
				codigo+="get_local $localsStart\r\ni32.add	;;"+v+"\r\n";//+"i32.load\r\n";
			}
		}
		else if(nodo instanceof Id) {
			codigo+="i32.const "+((Id) nodo).getDelta()*4+"\r\n";
			codigo+="get_local $localsStart\r\ni32.add	;;"+v+"\r\n";//+"i32.load\r\n";
		}
		else if(nodo instanceof Tipo) {
			codigo+="i32.const "+4+"\r\n";
			codigo+="get_local $localsStart\r\ni32.add	;;"+v+"\r\n";//+"i32.load\r\n";
		}
		
		return codigo;
	}
	
	public int getDelta() {
		return delta;
	}
	public void setDelta(int d) {
		this.delta=d;
	}

	}