package ast;

import tablasimbolos.TablaSimbolos;

public class EBin extends E {
   private E opnd1;
   private E opnd2;
   private KindE k;
   private Tipo tipo;
   
   public Tipo getTipo() {
	return tipo;
}
public void setTipo(Tipo tipo) {
	this.tipo = tipo;
}
public EBin(KindE k,E opnd1, E opnd2) {
     this.opnd1 = opnd1;
     this.opnd2 = opnd2;
     this.k = k;
   }
   public E opnd1() {return opnd1;}
   public E opnd2() {return opnd2;}
@Override
public KindE kind() {
	return k;
}    
public String toString() {
	if(k==KindE.SUMA) {
		return "suma("+opnd1().toString()+","+opnd2().toString()+")";
	}
	else if(k==KindE.MUL) {
		return "mul("+opnd1().toString()+","+opnd2().toString()+")";
	}
	else if(k==KindE.RES) {
		return "res("+opnd1().toString()+","+opnd2().toString()+")";
	}
	else if(k==KindE.DIV) {
		return "div("+opnd1().toString()+","+opnd2().toString()+")";
	}
	else if(k==KindE.MAYOR) {
		return ">("+opnd1().toString()+","+opnd2().toString()+")";
	}
	else if(k==KindE.MENOR) {
		return "<("+opnd1().toString()+","+opnd2().toString()+")";
	}
	else if(k==KindE.IGUALCOMP) {
		return "==("+opnd1().toString()+","+opnd2().toString()+")";
	}
	else if(k==KindE.DIST) {
		return "!=("+opnd1().toString()+","+opnd2().toString()+")";
	}
	else if(k==KindE.AND) {
		return "AND("+opnd1().toString()+","+opnd2().toString()+")";
	}
	else if(k==KindE.OR) {
		return "OR("+opnd1().toString()+","+opnd2().toString()+")";
	}
	else if(k==KindE.PUNTO) {
		return ".("+opnd1().toString()+","+opnd2().toString()+")";
	}
	else if(k==KindE.ACCESO) {
		return "acceso("+opnd1().toString()+","+opnd2().toString()+")";
	}
	return null;
	}
@Override
public void bind(TablaSimbolos tabla ) {
	opnd1.bind(tabla);
	opnd2.bind(tabla);
}
@Override
public Tipo type() {
	if(k==KindE.SUMA) {
		return new Tipo("int");
	}
	else if(k==KindE.MUL) {
		return new Tipo("int");
	}
	else if(k==KindE.RES) {
		return new Tipo("int");
	}
	else if(k==KindE.DIV) {
		return new Tipo("int");
	}
	else if(k==KindE.MAYOR) {
		return new Tipo("bool");
	}
	else if(k==KindE.MENOR) {
		return new Tipo("bool");
	}
	else if(k==KindE.IGUALCOMP) {
		return new Tipo("bool");
	}
	else if(k==KindE.DIST) {
		return new Tipo("bool");
	}
	else if(k==KindE.AND) {
		return new Tipo("bool");
	}
	else if(k==KindE.OR) {
		return new Tipo("bool");
	}
	else if(k==KindE.PUNTO) {
		return opnd2().type();
	}
	else if(k==KindE.ACCESO) {
		return tipo;
	}
	return null;
}
@Override
public String generateCode() {
	String codigo="";
	if(k==KindE.SUMA) {
		codigo+=opnd1().generateCode();
		if(opnd1().kind()==KindE.ID|| opnd1().kind()==KindE.PUNTO|| opnd1().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+=opnd2().generateCode();
		if(opnd2().kind()==KindE.ID|| opnd2().kind()==KindE.PUNTO|| opnd2().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+="i32.add\r\n";

	}
	else if(k==KindE.MUL) {
		codigo+=opnd1().generateCode();
		if(opnd1().kind()==KindE.ID|| opnd1().kind()==KindE.PUNTO|| opnd1().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+=opnd2().generateCode();
		if(opnd2().kind()==KindE.ID|| opnd2().kind()==KindE.PUNTO|| opnd2().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+="i32.mul\r\n";

	}
	else if(k==KindE.RES) {
		codigo+=opnd1().generateCode();
		if(opnd1().kind()==KindE.ID|| opnd1().kind()==KindE.PUNTO|| opnd1().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+=opnd2().generateCode();
		if(opnd2().kind()==KindE.ID|| opnd2().kind()==KindE.PUNTO|| opnd2().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+="i32.sub\r\n";
	}
	else if(k==KindE.DIV) {
		codigo+=opnd1().generateCode();
		if(opnd1().kind()==KindE.ID|| opnd1().kind()==KindE.PUNTO|| opnd1().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+=opnd2().generateCode();
		if(opnd2().kind()==KindE.ID|| opnd2().kind()==KindE.PUNTO|| opnd2().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+="i32.div_s\r\n";
	}
	else if(k==KindE.MAYOR) {
		codigo+=opnd1().generateCode();
		if(opnd1().kind()==KindE.ID|| opnd1().kind()==KindE.PUNTO|| opnd1().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+=opnd2().generateCode();
		if(opnd2().kind()==KindE.ID|| opnd2().kind()==KindE.PUNTO|| opnd2().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+="i32.gt_s\r\n";
	}
	else if(k==KindE.MENOR) {
		codigo+=opnd1().generateCode();
		if(opnd1().kind()==KindE.ID|| opnd1().kind()==KindE.PUNTO|| opnd1().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+=opnd2().generateCode();
		if(opnd2().kind()==KindE.ID|| opnd2().kind()==KindE.PUNTO|| opnd2().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+="i32.lt_s\r\n";

	}
	else if(k==KindE.IGUALCOMP) {
		codigo+=opnd1().generateCode();
		if(opnd1().kind()==KindE.ID|| opnd1().kind()==KindE.PUNTO|| opnd1().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+=opnd2().generateCode();
		if(opnd2().kind()==KindE.ID|| opnd2().kind()==KindE.PUNTO|| opnd2().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+="i32.eq\r\n";
	}
	else if(k==KindE.DIST) {
		codigo+=opnd1().generateCode();
		if(opnd1().kind()==KindE.ID|| opnd1().kind()==KindE.PUNTO|| opnd1().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+=opnd2().generateCode();
		if(opnd2().kind()==KindE.ID|| opnd2().kind()==KindE.PUNTO|| opnd2().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+="i32.ne\r\n";
	}
	else if(k==KindE.AND) {
		codigo+=opnd1().generateCode();
		if(opnd1().kind()==KindE.ID|| opnd1().kind()==KindE.PUNTO|| opnd1().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+=opnd2().generateCode();
		if(opnd2().kind()==KindE.ID|| opnd2().kind()==KindE.PUNTO|| opnd2().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+="i32.and\r\n";
	}
	else if(k==KindE.OR) {
		codigo+=opnd1().generateCode();
		if(opnd1().kind()==KindE.ID|| opnd1().kind()==KindE.PUNTO|| opnd1().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+=opnd2().generateCode();
		if(opnd2().kind()==KindE.ID|| opnd2().kind()==KindE.PUNTO|| opnd2().kind()==KindE.ACCESO) {
			codigo+="i32.load\r\n";
		}
		codigo+="i32.or\r\n";
	}
	else if(k==KindE.PUNTO) {
		Struct0 st=	(Struct0) ((Id) ((Id) opnd1()).getNodo()).getNodo();
		int delta=((Id) st.getListaCampos().get(opnd2().id()).opnd2()).getDelta();
		String code="";
		code+=opnd1().generateCode();
		code+="i32.const "+delta*4+"\r\n";
		code+="i32.add\r\n";
		return code;
	}
	else if(k==KindE.ACCESO) {
		String code="";
		if(opnd1().kind()!=KindE.ACCESO) {
			code+=opnd1().generateCode();
			code+="i32.const "+this.getTipo().getTam()+"\r\n";
			if(opnd2().kind()==KindE.ID || opnd2().kind()==KindE.PUNTO || opnd2().kind()==KindE.ACCESO) {
				code+=opnd2().generateCode()+"i32.load\r\n";
			}
			else {
				code+=opnd2().generateCode();

			}
			code+="i32.mul\r\ni32.add\r\n";
		}
		else {
			code+=generateCodeAux(code)+"i32.add\r\n";
		}
		
		return code;
	}
	return codigo;
	}

public String generateCodeAux(String code) {
	if(opnd1() instanceof EBin) {
	
		code+=((EBin) opnd1()).generateCodeAux(code);
		code+="i32.const "+this.opnd1().type().getTam()+"\r\n";
		
		code+="i32.mul\r\n";
		if(opnd2().kind()==KindE.ID || opnd2().kind()==KindE.PUNTO || opnd2().kind()==KindE.ACCESO) {
			code+=opnd2().generateCode()+"i32.load\r\n";
		}
		else {
			code+=opnd2().generateCode();
	
		}
		code+="i32.add\r\ni32.const 4\r\ni32.mul\r\n";
	}
	else {
		code+=opnd1().generateCode();
		if(opnd2().kind()==KindE.ID || opnd2().kind()==KindE.PUNTO || opnd2().kind()==KindE.ACCESO) {
			code+=opnd2().generateCode()+"i32.load\r\n";
		}
		else {
			code+=opnd2().generateCode();
		}
	}
	return code;
	
}
}



