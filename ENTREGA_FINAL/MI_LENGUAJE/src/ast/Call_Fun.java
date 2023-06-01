package ast;

import java.util.ArrayList;

import tablasimbolos.TablaSimbolos;

public class Call_Fun extends I{
	private E iden;
	private P param;
	public Call_Fun (E iden) {
		this.iden=iden;
	}
	
	public Call_Fun (E iden, P param) {
		this.iden=iden;
		this.param=param;
	}
	
	public E opndIden() {return iden;}
	public P opndParam() {return param;}
	
	public String toString() {
		if(param==null) {
			return "call(" + opndIden().toString()+")";
		}
		else {
			return "call(" + opndIden().toString()+",param("+opndParam().toString()+"))";
		}
		
	}
	
	@Override
	public KindI kind() {
		return KindI.CALL;
	}

	@Override
	public void bind(TablaSimbolos tabla) {
		if(tabla.buscaId(iden.id())!=null) {
			((Id) iden).setNodo((ASTNode) tabla.buscaArriba(iden.id()));
			((Param) param).bindCall(tabla);
		}
		else {
			System.out.println("La funcion "+iden.id() +" no esta definida");
		}
		
	}

	@Override
	public Tipo type() {
		// TODO Auto-generated method stub
		return iden.type();
	}

	@Override
	public String generateCode() {
		String codigo="";
		ArrayList<Boolean> porRef=new ArrayList<Boolean>();
		porRef=((Def_Fun) ((Id) iden).getNodo()).getPorRef();
		codigo+=((Param) param).generateCodeCall(porRef,0);
		codigo+="call $"+iden.id()+"\r\n";
		return codigo;
	}

}