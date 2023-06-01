package ast;

import tablasimbolos.TablaSimbolos;

public class Bloq_D extends D{
	private D inst;
	private D l;
	
	public Bloq_D(D inst) {
		this.inst=inst;
	}
	
	public Bloq_D(D inst,D l) {
		this.inst=inst;
		this.l=l;
	}
	
	 public D opnd() {return inst;}
	 public D opndLista() {return l;}
	   
	 public String toString() {
		 if(l==null) {
			return opnd().toString();
		 }
		 else{
			return opnd().toString()+","+opndLista().toString();

		 }
	 }
	
	@Override
	public KindD kind() {
		// TODO Auto-generated method stub
		return KindD.BLOQ;
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
		// TODO Auto-generated method stub
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

}
