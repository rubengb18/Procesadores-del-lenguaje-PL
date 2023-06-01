package ast;

import java.util.HashMap;

import errors.GestionErroresTiny;
import errors.TipoError;
import tablasimbolos.TablaSimbolos;

public class Struct0 extends STRUCT{
	   private I bloq;
	   private E iden;
	   private STRUCT st;
	   private STRUCT l;
	   private HashMap<String,Def> listaCampos;
	   private int delta;
	   private int tam;
	   private GestionErroresTiny gestion=new GestionErroresTiny();
	   
	   public Struct0(I l, E iden) {
		     this.bloq = l;
		     this.iden = iden;
		     this.listaCampos=new HashMap<String,Def>();
		     guardaCampos(bloq);
		     calculaTam();
		   }
	   
	   public void calculaTam() {
		   for(Def campo : listaCampos.values()) {
			   tam+=((Tipo) campo.opnd()).getTam();
		   }
	   }
	public void guardaCampos(I bloq) {
		if(bloq.opndListaDef()==null) {
			listaCampos.put(bloq.opnd2().id(), (Def) bloq);
		}
		else {
			guardaCampos(bloq.opndDef());
			guardaCampos(bloq.opndListaDef());
		}
	}
	public HashMap<String,Def> getListaCampos() {
		return listaCampos;
	}

	public void setListaCampos(HashMap<String,Def> listaCampos) {
		this.listaCampos = listaCampos;
	}

	public Struct0(STRUCT opnd1, STRUCT opnd2) {
		     this.st = opnd1;
		     this.l = opnd2;
	 }
	   public STRUCT opndLista() {return l;}
	   public STRUCT opndSt() {return st;}
	   public E opnd2() {return iden;}
	   public I opnd3() {return bloq;}
	  
	public String toString() {
		if(l==null) {
			return "struct("+opnd2().toString()+","+opnd3().toString()+")";
		}
		else {
			return opndSt().toString() + "," +opndLista().toString();

		}
		
	}
	
	public KindSTRUCT kind() {
		return KindSTRUCT.STRUCT;
	}
	@Override
	public void bind(TablaSimbolos tabla) {
		if(l==null) {
			if(tabla.buscaId(iden.id())==null) {
				tabla.insertaID(iden.id(), this);
				tabla.abreBloque();
				bloq.bind(tabla);
			}
			else {
				gestion.errorVinc(TipoError.REPTIPO);
				System.out.println("El tipo "+iden+" ya existe");
			}
		}
		else {
			st.bind(tabla);
			l.bind(tabla);
		}
	}
	@Override
	public Tipo type() {
		// TODO Auto-generated method stub
		return new Tipo(this.iden.id());
	}

	@Override
	public String generateCode() {
		return "";
	}

	public void calculaDeltas(int localStart) {
		if(l==null) {
			this.setDelta(bloq.calculaDeltas(localStart));
		}
		else {
			((Struct0) st).calculaDeltas(localStart);
			((Struct0) l).calculaDeltas(localStart);
		}
	}

	public int getDelta() {
		return delta;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}

	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}

}
