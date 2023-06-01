package ast;

import tablasimbolos.TablaSimbolos;

public class For extends I{
	   private I asig;
	   private E cond;
	   private I it;
	   private I bloq;
	   
	   public For(I asig, E cond,I it,I bloq) {
	     this.asig = asig;
	     this.cond = cond;
	     this.it=it;
	     this.bloq=bloq;
	   }
	   
	   public I opndAsig() {return asig;}
	   public E opndCond() {return cond;}
	   public I opndIt() {return it;}
	   public I opndBloq() {return bloq;}

	public String toString() {
			return "for("+opndAsig().toString() + ";" +opndCond().toString()+ ";" +opndIt().toString()+";"+opndBloq()+")";
	}

	public KindI kind() {	
		return KindI.FOR;
	}

	@Override
	public void bind(TablaSimbolos tabla) {
		tabla.abreBloque();
		asig.bind(tabla);
		cond.bind(tabla);
		it.bind(tabla);
		bloq.bind(tabla);
		tabla.cierraBloque();
	}

	@Override
	public Tipo type() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateCode() {
		String codigo="";/*"   get_local $localsStart\r\n"+
		"	set_local $temp"+
		"	get_global $SP\r\n"+
		"   set_local $localsStart\r\n" + 
		"	get_global $SP\r\n"+
		"   i32.const "+ (localStart+2)*4+"  ;; let this be the stack size needed (params+locals+2)*4\r\n"+
		"	i32.add\r\n"+
		"	set_global $SP\r\n"; */ 
		codigo+=asig.generateCode();
		codigo+="block\r\nloop\r\n";
		codigo+=cond.generateCode();
		codigo+="i32.eqz\r\nbr_if 1\r\n";
		codigo+=bloq.generateCode();
		codigo+=it.generateCode();
		codigo+="br 0\r\nend\r\nend\r\n";
		//codigo+="get_local $temp\r\nset_local $localsStart\r\nget_global $SP\r\ni32.const "+ (localStart+2)*4+"\r\ni32.sub\r\nset_global $SP\r\n";
		return codigo;
	}

}
