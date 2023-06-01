package ast;


import java.util.ArrayList;

import tablasimbolos.TablaSimbolos;

public class Param extends P{
	private E iden;
	private T tipo;
	private String ampersand;
	private P param;
	private P l;
	private int cant=8;
	
	public Param (E iden) {
		this.iden=iden;
	}
	
	public Param (T tipo, E iden) {
		this.tipo=tipo;
		this.iden=iden;
	}
	public Param (T tipo,String ampersand, E iden) {
		this.tipo=tipo;
		this.ampersand=ampersand;
		this.iden=iden;
	}
	public Param (P param, P l) {
		this.param=param;
		this.l=l;
	}
	
	public void listaParam(ArrayList<Boolean> porRef) {
		if(l==null) {
			if(ampersand==null) {
				porRef.add(false);
			}
			else {
				porRef.add(true);
			}
		}
		else {
			((Param) param).listaParam(porRef);
			((Param) l).listaParam(porRef);
		}
		
	}
	
	public E opndIden() {return iden;}
	public T opndTipo() {return tipo;}
	public String opndAm() {return ampersand;}
	public P opndParam() {return param;}
	public P opndLista() {return l;}
	
	public String toString() {
		if(l==null) {
			if(tipo != null) {
				if(ampersand==null) {
					return opndTipo().toString()+","+opndIden().toString();
				}
				else {
					return opndTipo().toString()+","+opndAm()+opndIden().toString();
				}
			}
			else {
				return opndIden().toString();
			}
		}
		else {
			return opndParam().toString()+","+opndLista().toString();
		}
		
	}
	
	@Override
	public KindP kind() {
		return KindP.PARAM;
	}

	@Override
	public void bind(TablaSimbolos tabla) {
		if(l==null) {
			tabla.insertaID(iden.id(), this);
			//iden.bind(tabla);
		}
		else {
			param.bind(tabla);
			l.bind(tabla);
		}
		
	}

	public void bindCall(TablaSimbolos tabla) {
		if(l==null) {
			iden.bind(tabla);
		}
		else {
			((Param) param).bindCall(tabla);
			((Param) l).bindCall(tabla);
		}
		
	}
	@Override
	public Tipo type() {
		// TODO Auto-generated method stub
		return (Tipo) tipo;
	}

	public String generateCodeCall(ArrayList<Boolean> porRef,int i) {
		String codigo="";
		if(l==null) {
			if(iden instanceof Id) {
				Tipo tipo=null;
				ASTNode nodo=((Id) iden).getNodo();
				if(nodo instanceof Id) {
					tipo=nodo.type();
				}
				else if(nodo instanceof Param) {
					tipo=nodo.type();
				}
				if(tipo.kind()==KindT.INT_0||tipo.kind()==KindT.B) {
					
					if(porRef.get(i)==false) {
						codigo+="get_global $SP\r\ni32.const "+(cant)+"\r\ni32.add\r\n"+iden.generateCode()+"i32.load\r\ni32.store\r\n";
					}
					else {				
						codigo+="get_global $SP\r\ni32.const "+(cant)+"\r\ni32.add\r\n"+iden.generateCode()+"i32.store\r\n";
					}
					cant+=tipo.getTam();
				}
				else if(tipo.kind()==KindT.ARRAY) {
					if(porRef.get(i)==false) {
						codigo+=iden.generateCode()+"get_global $SP\r\ni32.const "+(cant)+"\r\ni32.add\r\ni32.const "+tipo.calculaTam()+"\r\ncall $copyn\r\n";
					}
					else {				
						codigo+=iden.generateCode()+"get_global $SP\r\ni32.const "+(cant)+"\r\ni32.add\r\ni32.const "+tipo.calculaTam()+"\r\ncall $copyndir\r\n";
					}
					cant+=tipo.calculaTam()*4;
				}
			}
			else if(iden instanceof EBin) {
				if(iden.kind()==KindE.PUNTO) {
					Tipo tipo=iden.type();
					if(porRef.get(i)==false) {
						codigo+="get_global $SP\r\ni32.const "+(cant)+"\r\ni32.add\r\n"+iden.generateCode()+"i32.load\r\ni32.store\r\n";
					}
					else {				
						codigo+="get_global $SP\r\ni32.const "+(cant)+"\r\ni32.add\r\n"+iden.generateCode()+"i32.store\r\n";
					}
					cant+=tipo.getTam();
				}
				else if(iden.kind()==KindE.ACCESO) {
					Tipo tipo=iden.type();
					if(porRef.get(i)==false) {
						codigo+="get_global $SP\r\ni32.const "+(cant)+"\r\ni32.add\r\n"+iden.generateCode()+"i32.load\r\ni32.store\r\n";
					}
					else {				
						codigo+="get_global $SP\r\ni32.const "+(cant)+"\r\ni32.add\r\n"+iden.generateCode()+"i32.store\r\n";
					}
					cant+=tipo.getTam();
				}
				else {
					codigo+="get_global $SP\r\ni32.const "+(cant)+"\r\ni32.add\r\n"+iden.generateCode()+"i32.store\r\n";	
					cant+=4;
				}
			}
			else if(iden instanceof Num) {			
				codigo+="get_global $SP\r\ni32.const "+(cant)+"\r\ni32.add\r\n"+iden.generateCode()+"i32.store\r\n";
				cant+=4;

			}

		}
		else {
			((Param) param).setCant(cant);
			codigo+=((Param) param).generateCodeCall(porRef,i);
			cant=((Param) param).getCant();
			((Param) l).setCant(cant);
			codigo+=((Param) l).generateCodeCall(porRef,i+1);
		}
		return codigo;
	}
	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}

	public String getAmpersand() {
		return ampersand;
	}

	public void setAmpersand(String ampersand) {
		this.ampersand = ampersand;
	}

	@Override
	public String generateCode() {
		String codigo="";
		return codigo;
	}
	
	public int calculaDeltas(int localStart) {
		int dev=localStart;
		if(l==null) {
			((Id) iden).setDelta(dev);
			if(iden.type().kind()==KindT.INT_0 || iden.type().kind()==KindT.B) {
				dev+=1;
			}
			else if(iden.type().kind()==KindT.STRUCT){
				dev+=((Struct0) ((Id) iden).getNodo()).getDelta();
				
			}
			else if(iden.type().kind()==KindT.ARRAY){
				int tama=0;
				tama+=this.type().calculaTam();
				dev+=tama;
			}
		}
		else {
			dev=param.calculaDeltas(dev);
			dev=l.calculaDeltas(dev);
		}
		return dev;
	}
	
	public T getTipo() {
		return tipo;
	}

	public void setTipo(T tipo) {
		this.tipo = tipo;
	}

	public int getDelta() {
		return ((Id) iden).getDelta();
	}
	public void setDelta(int d) {
		((Id) this.iden).setDelta(d);
	}
	
	public int getTam() {
		return ((Tipo) tipo).getTam();
	}

	public void setTam(int i) {
		((Tipo) tipo).setTam(i);
	}
}
