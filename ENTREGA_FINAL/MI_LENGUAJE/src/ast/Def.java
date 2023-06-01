package ast;

import tablasimbolos.TablaSimbolos;

public class Def extends I{
	   private T opnd1;
	   private E opnd2;
	   private T ptr;
	   private I def;
	   private I l;
	   
		public int calculaDeltas(int localStart) {
			int dev=localStart;
			if(l==null) {
				((Id) opnd2).setDelta(dev);
				if(opnd2().type().kind()==KindT.INT_0 || opnd2.type().kind()==KindT.B) {
					dev+=1;
				}
				else if(opnd2().type().kind()==KindT.STRUCT){
					ASTNode nodo=((Id) opnd2()).getNodo();
					dev+=((Struct0) nodo).getDelta();
					
				}
				else if(opnd2().type().kind()==KindT.ARRAY){
					Tipo tipo=(Tipo) opnd1;
					Integer tama=((Tipo) opnd1).calculaTam();
					dev+=tama;
				}
			}
			else {
				dev=def.calculaDeltas(dev);
				dev=l.calculaDeltas(dev);
			}
			return dev;
			
		}
	   
	   public Def(T opnd1, E opnd2) {
	     this.opnd1 = opnd1;
	     this.opnd2 = opnd2;
	     this.opnd2.setTipo((Tipo) opnd1);
	     
	   }

	   public Def(T opnd1, E opnd2,T opnd3) {
		     this.opnd1 = opnd1;
		     this.opnd2 = opnd2;
		     this.ptr=opnd3;
		     this.opnd2.setTipo((Tipo) opnd1);
		   }
	   
	   public Def(I opnd1, I l) {
		     this.def = opnd1;
		     this.l = l;
	 }
	   public I opndListaDef() {return l;}
	   public I opndDef() {return def;}
	   public T opnd() {return opnd1;}
	   public T opndPtr() {return ptr;}
	   public E opnd2() {return opnd2;}

	public String toString() {
		if(l==null){
			if(ptr==null) {
				return "def("+opnd().toString()+","+opnd2().toString()+")";

			}
			else {
				return "def(ptr_"+opnd().toString()+","+opnd2().toString()+")";
			}

		}
		else {
			return opndDef().toString() + "," +opndListaDef().toString();
		}
	}

	@Override
	public KindI kind() {
		return KindI.DEF;
	}
	@Override
	public void bind(TablaSimbolos tabla) {
		if(l==null) {
			tabla.insertaID(opnd2().id(), this.opnd2());
			opnd2.setTipo((Tipo) opnd1);
			if(opnd1.kind()==KindT.STRUCT) {
				((Id) opnd2).setNodo((ASTNode) tabla.buscaArriba(opnd1.toString()));
			}
			else {
			((Id) opnd2).setNodo(this.opnd2);
			}
		}
		else {
			def.bind(tabla);
			l.bind(tabla);
		}			
	}
	@Override
	public Tipo type() {
		return (Tipo) opnd();
	}
	@Override
	public String generateCode() {
		return "";
	}
	public void setTipo(Tipo t) {
		opnd1=t;
	}
}
