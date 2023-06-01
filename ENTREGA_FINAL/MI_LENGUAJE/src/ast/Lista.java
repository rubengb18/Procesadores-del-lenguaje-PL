package ast;


import tablasimbolos.TablaSimbolos;

public class Lista extends E{
	private E elem;
	private E l;
	private String CORAP,CORCI;
	private T tipo;
	private int tam=0;
	private int delta;
	
	public Lista(E elem, E l,Tipo tipo) {
		this.l=l;
		this.elem=elem;
		this.tipo=tipo;
		tam=calculaTam();
		((Tipo) this.tipo).setTam(tam);
	}
	
	public int calculaTam() {
		if(CORCI==null) {
			if(l==null) {
				if(elem.kind()==KindE.NUM || elem.kind()==KindE.BOOL) {
					return 1;
				}
				else {
					return ((Lista) elem).calculaTam();
				}
			}
			else {
				if(elem.kind()==KindE.NUM|| elem.kind()==KindE.BOOL) {
					return 1+((Lista) l).calculaTam();
				}
				else {
					return ((Lista) elem).calculaTam() +((Lista) l).calculaTam();
				}
				
			}
		}
		else {
			return ((Lista) l).calculaTam();
		}
	}

	public Lista(E elem,T tipo2) {
		this.elem=elem;
		this.tipo=tipo2;
		tam=calculaTam();
		((Tipo) this.tipo).setTam(tam);
	}
	
	public Lista(E l,String CORAP, String CORCI,Tipo tipo) {
		this.l=l;
		this.CORAP=CORAP;
		this.CORCI=CORCI;
		this.tipo=tipo;
		tam=calculaTam();
		((Tipo) this.tipo).setTam(tam);
	}
	
	public E opndElem() {return elem;}
	public E opndLista() {return l;}


	
	public String toString() {
		if(CORCI==null) {
			if(l==null) {
				return opndElem().toString();
	
			}
			else {
				return opndElem().toString()+","+opndLista().toString();
	
			}
		}
		else {
			return "["+opndLista().toString()+"]";
		}
		
	}
	
	@Override
	public KindE kind() {
		// TODO Auto-generated method stub
		return KindE.LISTA;
	}

	@Override
	public void bind(TablaSimbolos tabla) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tipo type() {
		return (Tipo) tipo;
	}

	public String generateCodeLista(int i,int delta) {
		if(CORCI==null) {
			if(l==null) {
				if(elem.kind()==KindE.NUM) {
					return "i32.const "+((delta+i)*4)+ "\r\nget_local $localsStart\r\ni32.add\r\n"+elem.generateCode()+"i32.store\r\n";
				}
				else {
					return ((Lista) elem).generateCodeLista(i, delta);
				}
			}
			else {
					return "i32.const "+((delta+i)*4)+ "\r\nget_local $localsStart\r\ni32.add\r\n"+elem.generateCode()+"i32.store\r\n"+((Lista) l).generateCodeLista(i+1, delta);
			}
		}
		else {
			return ((Lista) l).generateCodeLista(i, delta);
		}
		
	}

	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}

	public int getDelta() {
		return delta;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}

	@Override
	public String generateCode() {
		return generateCodeLista(0,delta);
	}

}
