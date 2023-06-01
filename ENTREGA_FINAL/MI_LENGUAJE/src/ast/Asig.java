package ast;

import tablasimbolos.TablaSimbolos;

public class Asig extends I{
	   private E opnd1;
	   private E opnd2;
	   private I call;
	   private Def def;
	   public Asig(E opnd1, E opnd2) {
	     this.opnd1 = opnd1;
	     this.opnd2 = opnd2;
	   }
	   public Asig(E opnd1, I call) {
		     this.opnd1 = opnd1;
		     this.call = call;
		   }
	   public Asig(Def def,E opnd2) {
		     this.def = def;
		     this.opnd2 = opnd2;
		   }
	   public Asig(Def def,I call) {
		     this.def = def;
		     this.call = call;
		   }

	public E opnd1() {return opnd1;}
	   public E opnd2() {return opnd2;}
		public I opndCall() {return call;}
		public Def opndDef() {return def;}
	   
	   public String toString() {
		   if(def==null){
			   if(call==null) {
					return "asig("+opnd1().toString()+","+opnd2().toString()+")";
			   }
			   else {
				   return "asig("+opnd1().toString()+","+opndCall().toString()+")";
			   }
		   }
		   else {
			   if(call==null) {
				   return "asig("+opndDef().toString()+","+opnd2().toString()+")"; 
			   }
			   else {
				   return "asig("+opndDef().toString()+","+opndCall().toString()+")"; 
			   }
		   }
	   }
	@Override
	public KindI kind() {
		// TODO Auto-generated method stub
		return KindI.ASIG;
	}
	@Override
	public void bind(TablaSimbolos tabla) {
		if(def==null){
			   if(call==null) {
					opnd1().bind(tabla);
					opnd2().bind(tabla);
			   }
			   else {
				   opnd1().bind(tabla);
				   opndCall().bind(tabla);
			   }
		   }
		   else {
			   if(call==null) {
				   opndDef().bind(tabla);
				   opnd2().bind(tabla);
			   }
			   else {
				   opndDef().bind(tabla);
				   opndCall().bind(tabla); 
			   }
		   }
		
	}
	@Override
	public Tipo type() {
		if(def==null) {
			return opnd1.type();
		}
		else {
			return def.type();
		}
	}
	@Override
	public String generateCode() {
		String codigo="";
		if(def==null){
			   if(call==null) {
					codigo+=opnd1().generateCode();
					codigo+=opnd2().generateCode();
					if(opnd2().kind()==KindE.ID||opnd2().kind()==KindE.PUNTO||opnd2().kind()==KindE.ACCESO  ) {
						codigo+="i32.load\r\n";
					}
					codigo+="i32.store\r\n";
			   }
			   else {
				    codigo+=opnd1().generateCode();
					codigo+=opndCall().generateCode();
					codigo+="i32.store\r\n";
			   }
		   }
		   else {
			   if(call==null) {
				    
					if(opnd2().kind()==KindE.ID ||opnd2().kind()==KindE.PUNTO||opnd2().kind()==KindE.ACCESO  ) {
						codigo+=opndDef().opnd2().generateCode();
						codigo+=opnd2().generateCode();
						codigo+="i32.load\r\n";
						codigo+="i32.store\r\n"; 
					}
					else if(opnd2().kind()==KindE.LISTA) {
						codigo+=opnd2().generateCode();
						codigo+="i32.const "+((Lista) opnd2()).getDelta()*4+"\r\n";
						codigo+="get_local $localsStart\r\ni32.add	;;"+((Lista) opnd2()).toString()+"\r\n";
						codigo+=opndDef().opnd2().generateCode();
						codigo+="i32.const "+((Lista) opnd2()).getTam()+"\r\ncall $copyn\r\n";
					}
					else {
						codigo+=opndDef().opnd2().generateCode();
						codigo+=opnd2().generateCode();
						codigo+="i32.store\r\n";
					}
					
			   }
			   else {
				    codigo+=opndDef().opnd2().generateCode();
					codigo+=opndCall().generateCode();
					codigo+="i32.store\r\n"; 
			   }
		   }
		return codigo;
	}

}
