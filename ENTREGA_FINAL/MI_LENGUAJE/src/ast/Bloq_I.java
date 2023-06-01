package ast;

import tablasimbolos.TablaSimbolos;

public class Bloq_I extends I{
	private I inst;
	private I l;
	
	public Bloq_I(I inst) {
		this.inst=inst;
	}
	
	public Bloq_I(I inst,I l) {
		this.inst=inst;
		this.l=l;
	}
	
	 public I opndI() {return inst;}
	 public I opndLista() {return l;}
	   
	 public String toString() {
		 if(l==null) {
			return opndI().toString();
		 }
		 else{
			return opndI().toString()+","+opndLista().toString();

		 }
	 }
	
	@Override
	public KindI kind() {
		// TODO Auto-generated method stub
		return KindI.BLOQ;
	}

	@Override
	public void bind(TablaSimbolos tabla) {
		inst.bind(tabla);
		if(l!=null) {
			l.bind(tabla);
		}

	}

	@Override
	public Tipo type() {
		return inst.type();
	}

	@Override
	public String generateCode() {
		String codigo=inst.generateCode();
		if(l!=null) {
			codigo+=l.generateCode();
		}
		return codigo;
	}
	

	public int calculaDeltas(int localStart) {
		int dev=localStart;
		if(inst instanceof Def) {
			dev=inst.calculaDeltas(dev);
		}
		else if(inst instanceof Asig) {
			if(inst.opndDef()!=null) {
				((Id) inst.opndDef().opnd2()).setDelta(dev);
				if(((Asig) inst).opndCall()==null) {
					if(((Id) inst.opndDef().opnd2()).type().kind()==KindT.INT_0 || ((Id) inst.opndDef().opnd2()).type().kind()==KindT.B) {
						dev+=1;
					}
					else if(((Id) inst.opndDef().opnd2()).type().kind()==KindT.ARRAY) {
						dev+=((Id) inst.opndDef().opnd2()).type().calculaTam();
					}
					else if(inst.opnd2().kind()==KindE.LISTA) {
						dev+=((Tipo) inst.opndDef().opnd2().type()).getTam();
						((Lista) inst.opnd2()).setDelta(dev);
						dev+=dev;
					}
				}
				else {	//Llamada a funcion
					if( inst.opndDef().opnd2().type().kind()==KindT.B||inst.opndDef().opnd2().type().kind()==KindT.INT_0) {
						dev+=1;
					}
					else if(((Id) inst.opndDef().opnd2()).type().kind()==KindT.ARRAY) {
						dev+=((Id) inst.opndDef().opnd2()).type().calculaTam();
					}
				}
			}
		}
		else if(inst instanceof For) {
			((Id) ((For) inst).opndAsig().opndDef().opnd2()).setDelta(dev);
			dev+=1;
			dev=((For) inst).opndBloq().calculaDeltas(dev);
		}
		else if(inst instanceof While) {
			dev=((While) inst).opndBloq().calculaDeltas(dev);
		}
		else if(inst instanceof If) {
			dev=((If) inst).opndBloq().calculaDeltas(dev);
			if(((If) inst).opndBloqElse()!=null) {
				dev=((If) inst).opndBloqElse().calculaDeltas(dev);
			}
		}
		if(l!=null) {
			dev=l.calculaDeltas(dev);
		}
		return dev;
		
	
	}

}
