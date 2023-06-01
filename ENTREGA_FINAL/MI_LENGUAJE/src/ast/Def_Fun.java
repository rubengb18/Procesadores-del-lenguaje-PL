package ast;

import java.util.ArrayList;

import tablasimbolos.TablaSimbolos;

public class Def_Fun extends D{
	private E iden;
	private T tipo;
	private I bloq;
	private P param;
	private I ret;
	private ArrayList<Boolean> porRef=new ArrayList<Boolean>();
	
	public Def_Fun (T tipo, E iden, I bloq,I ret) {
		this.tipo=tipo;
		this.iden=iden;
		this.bloq=bloq;
		this.ret=ret;
	}
	
	public Def_Fun (T tipo, E iden, I bloq) {
		this.tipo=tipo;
		this.iden=iden;
		this.bloq=bloq;
	}
	
	public Def_Fun (T tipo, E iden, I bloq, P param,I ret) {
		this.tipo=tipo;
		this.iden=iden;
		this.bloq=bloq;
		this.param=param;
		this.ret=ret;
	}
	public Def_Fun (T tipo, E iden, I bloq, P param) {
		this.tipo=tipo;
		this.iden=iden;
		this.bloq=bloq;
		this.param=param;
	}
	
	public E opndIden() {return iden;}
	public T opndTipo() {return tipo;}
	public I opndBloq() {return bloq;}
	public P opndParam() {return param;}
	public I opndRet() {return ret;}
	
	public String toString() {
		if(param==null) {
			if(ret==null) {
				return "fun(" + opndTipo().toString()+","+opndIden().toString()+",bloq("+opndBloq().toString()+"))";
			}
			else {
				return "fun(" + opndTipo().toString()+","+opndIden().toString()+",bloq("+opndBloq().toString()+"),"+opndRet().toString()+")";

			}
		}
		else {
			if(ret==null) {
				return "fun(" + opndTipo().toString()+","+opndIden().toString()+",param("+opndParam().toString()+"),bloq("+opndBloq().toString()+"))";
			}
			else {
				return "fun(" + opndTipo().toString()+","+opndIden().toString()+",param("+opndParam().toString()+"),bloq("+opndBloq().toString()+"),"+opndRet().toString()+")";
			}
		}
		
	}
	@Override
	public KindD kind() {
		return KindD.DEF;
	}

	@Override
	public void bind(TablaSimbolos tabla) {
		tabla.insertaID(iden.id(), this);
		tabla.abreBloque();
		if(param!=null) {
			param.bind(tabla);
		}
		bloq.bind(tabla);
		if(ret!=null) {
			ret.bind(tabla);
		}
		tabla.cierraBloque();
	}

	@Override
	public Tipo type() {
		// TODO Auto-generated method stub
		return (Tipo) tipo;
	}

	@Override
	public String generateCode() {
		int localStart=this.calculaDeltas(0);
		
		((Param) param).listaParam(porRef);
		String codigo="(func $";
		codigo+=iden.id()+"\r\n";
		if(tipo.kind()!=KindT.VOID) {
			if(tipo.kind()!=KindT.INT_0) {
				codigo+="	(result i32)\r\n";
			}
			else if(tipo.kind()!=KindT.B) {
				codigo+="	(result i32)\r\n";
			}
			else {
				//FALTAN LOS DEMAS CASOS QUE NO TRATARE
			}
		}
		codigo+="	(local $localsStart i32)\r\n" + 
				"   (local $temp i32)\r\n" + 
				"   i32.const "+ (localStart+2)*4+"  ;; let this be the stack size needed (params+locals+2)*4\r\n" + 
				"   call $reserveStack  ;; returns old MP (dynamic link)\r\n" + 
				"   set_local $temp\r\n" + 
				"   get_global $MP\r\n" + 
				"   get_local $temp\r\n" + 
				"   i32.store\r\n" + 
				"   get_global $MP\r\n" + 
				"   get_global $SP\r\n" + 
				"   i32.store offset=4\r\n" + 
				"   get_global $MP\r\n" + 
				"   i32.const 8\r\n" + 
				"   i32.add\r\n" + 
				"   set_local $localsStart\r\n";
		if(param!=null) {
			codigo+=param.generateCode();
		}
		
		codigo+=bloq.generateCode();
		if(ret!=null) {
			codigo+=ret.generateCode();
		}
		return codigo +"call $freeStack\r\n)\r\n";
	}
	
	public int calculaDeltas(int localStart) {
		int dev=localStart;
		if(param!=null) {
			dev=param.calculaDeltas(dev);
		}
		dev=this.bloq.calculaDeltas(dev);
		return dev;
	}

	public ArrayList<Boolean> getPorRef() {
		return porRef;
	}

	public void setPorRef(ArrayList<Boolean> porRef) {
		this.porRef = porRef;
	}

	
}