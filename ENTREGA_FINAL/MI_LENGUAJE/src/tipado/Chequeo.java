package tipado;

import java.util.Map;

import ast.ASTNode;
import ast.Asig;
import ast.Bloq_D;
import ast.Bloq_I;
import ast.Bool;
import ast.Call_Fun;
import ast.D;
import ast.Def;
import ast.Def_Fun;
import ast.E;
import ast.EBin;
import ast.Esc;
import ast.For;
import ast.I;
import ast.Id;
import ast.If;
import ast.Inicio;
import ast.KindE;
import ast.KindI;
import ast.KindT;
import ast.Lec;
import ast.Lista;
import ast.Num;
import ast.P;
import ast.Param;
import ast.Ret;
import ast.STRUCT;
import ast.Struct0;
import ast.TDEF;
import ast.Tipo;
import ast.While;
import errors.GestionErroresTiny;
import errors.TipoError;
import tablasimbolos.TablaSimbolos;

public class Chequeo {
		private Map<Object, Object> tablaTipos;
		private Boolean errors=false;
		private GestionErroresTiny gestion;
		
		public Chequeo(Map<Object, Object> tablaTipos) {
			this.tablaTipos=tablaTipos;
			this.gestion=new GestionErroresTiny();
		}

		private void insertaTipo(Object nodo, Object tipo) {
			this.tablaTipos.put(nodo, tipo);
		}
		
		private Tipo getTipo(Object nodo) {
			return (Tipo) this.tablaTipos.get(nodo);
		}
		
		private Boolean esLegible(Tipo t) {
			return t.kind()==KindT.INT_0 || t.kind()==KindT.B;
		}

		public void chequea(Inicio ini) throws Exception {
			TDEF bloqueTdef=ini.bloq1();
			STRUCT bloqueSt=ini.bloq2();
			Bloq_D bloqueDefFun=(Bloq_D)ini.bloq3();
			Bloq_I inst=(Bloq_I) ini.bloq4();
			if(bloqueTdef!=null) {
				chequea(bloqueTdef);
			}
			if(bloqueSt!=null) {
				chequea(bloqueSt);
			}
			if(bloqueDefFun!=null) {
				chequea(bloqueDefFun);
			}
			chequea(inst);
		}

		private void chequea(TDEF bloq1) {
			if(bloq1.lista()==null) {
				insertaTipo(bloq1.opnd3().toString(),bloq1.type());
			}
			else {
				chequea(bloq1.opnd());
				chequea(bloq1.lista());
			}
		}
		

		private void chequea(Bloq_D bloqueDefFun) throws Exception {
			chequea((Def_Fun)bloqueDefFun.opnd());
			if(bloqueDefFun.opndLista()!=null) {
				chequea((Bloq_D)bloqueDefFun.opndLista());
			}
			
		}
		
		private void chequea(Def_Fun defFun) throws Exception {
			Tipo t = defFun.type();
			defFun.opndIden().setTipo(t);
		
			insertaTipo(defFun.opndIden(),t);
			if(defFun.opndParam()!=null) {
				chequea((Param) defFun.opndParam());
			}
			chequea((Bloq_I) defFun.opndBloq());
			
			if(defFun.opndRet()!=null) {
				chequea(defFun.opndRet());
				if(!(t.toString().equals(((Id) defFun.opndRet().opnd1()).getNodo().type().toString()))) {
					gestion.errorTipado(TipoError.RET);
					errors=true;
					//throw new Exception("No coincide el tipo de la funcion y el return");
				}
			}
		}

		private void chequea(P opndParam) throws Exception {
			if(opndParam.opndLista()==null) {
				if(opndParam.opndTipo()!=null) {
					opndParam.opndIden().setTipo((Tipo) opndParam.opndTipo());
					insertaTipo(opndParam.opndIden(),opndParam.opndTipo());
				}
			}
			else {
				chequea(opndParam.opndParam());
				chequea(opndParam.opndLista());
			}
		}

		private void chequea(STRUCT bloqueSt) throws Exception {
			if(bloqueSt.opndLista()==null) {
				chequea(bloqueSt.opnd3());
			}
			else {
				chequea(bloqueSt.opndSt());
				chequea(bloqueSt.opndLista());
			}
			
			
		}
		
		private void chequea(Bloq_I bloq) throws Exception {
			chequea(bloq.opndI());
			if(bloq.opndLista()!=null) {
				chequea((Bloq_I)bloq.opndLista());
			}
			
		}
		
		private void chequea(I inst) throws Exception {
			KindI tipo=inst.kind();
			switch(tipo){
				case ASIG: {				
					chequea((Asig) inst);
				};  break;
				case DEF: {
					chequea((Def) inst);
				}; break;
				case WHILE: {
					chequea((While) inst);
				}; break;
				case FOR: {
					chequea((For) inst);
				}; break;
				case IF: {
					chequea((If) inst);
				}; break;
				case CALL: {
					chequea((Call_Fun) inst);
				}; break;
				case LEC: {
					chequea((Lec) inst);
				}; break;
				case ESC: {
					chequea((Esc) inst);
				}; break;
				case RET: {
					chequea((Ret) inst);
				}; break;
				default: break;
			}			
		}
		
		public void chequea(Asig asig) throws Exception {
			Tipo t1,t2;
			if(asig.opndDef()==null){
			   if(asig.opndCall()==null) {
				   chequea(asig.opnd1());
				   chequea(asig.opnd2());
				   t1=getTipo(asig.opnd1());
				   t2=getTipo(asig.opnd2());
			   }
			   else {
				   chequea(asig.opnd1());
				   chequea(asig.opndCall());
				   t1=getTipo(asig.opnd1());
				   t2=getTipo(asig.opndCall());
			   }
		   }
		   else {
			   if(asig.opndCall()==null) {
				   chequea(asig.opndDef());
				   chequea(asig.opnd2());
				   //if(asig.opnd2().kind()==KindE.ID ||asig.opnd2().kind()==KindE.PUNTO||asig.opnd2().kind()==KindE.ACCESO  ) {
					   t1=asig.opndDef().type();
					   t2=getTipo(asig.opnd2());
				   /*}
				   else if(asig.opnd2().kind()==KindE.LISTA) {
					   
				   }*/
			   }
			   else {
				   chequea(asig.opndDef());
				   chequea(asig.opndCall());
				   t1=getTipo(asig.opndDef());
				   t2=getTipo(asig.opndCall());
			   }
		   }
			if (t1 == null || t2 == null || !compatibles(t1, t2)){
				gestion.errorTipado(TipoError.INCOMP);
				System.out.println("Incompatibilidad de tipos en asignación con "+ "Tipo1: " + t1 + " Tipo2: " + t2);
				errors=true;
				//throw new UnsupportedOperationException("Incompatibilidad de tipos en asignación con "+ "Tipo1: " + t1 + " Tipo2: " + t2);
			}
		}

		private void chequea(E expresion) throws Exception {
			if (expresion == null) return;
			KindE tipo = expresion.kind();
			switch(tipo){
				case SUMA: {
					chequea((EBin)expresion);
				} break;
				case MUL: {
					chequea((EBin)expresion);
				} break;
				case RES: {
					chequea((EBin)expresion);
				} break;
				case DIV: {
					chequea((EBin)expresion);
				} break;
				case NUM: {
					chequea((Num)expresion);
				} break;
				case BOOL: {
					chequea((Bool)expresion);				
				} break;
				case IGUALCOMP: {
					chequea((EBin)expresion);					
				} break;
				case MAYOR: {
					chequea((EBin)expresion);
				} break;
				case MENOR: {
					chequea((EBin)expresion);
				} break;
				case DIST: {
					chequea((EBin)expresion);
				} break;
				case ID: {
					chequea((Id)expresion);
				} break;
				case AND: {
					chequea((EBin)expresion);
				} break;
				case OR: {
					chequea((EBin)expresion);
				} break;
				case LISTA: {
					chequea((Lista)expresion);
				} break;
				case PUNTO: {
					chequea((EBin)expresion);
				} break;
				case ACCESO: {
					chequea((EBin)expresion);
				} break;
				
				default: break;		
			}		
		}
		
		public void chequea (EBin e) throws Exception {
			E e1=e.opnd1();
			E e2=e.opnd2();	
			if(e.kind()!=KindE.PUNTO &&e.kind()!=KindE.ACCESO) {
				chequea(e1);
				chequea(e2);
				Tipo t1 =  e1.type();
				Tipo t2 =  e2.type();
				if (t1 == null || t2 == null || !compatibles(t1, t2)){
					gestion.errorTipado(TipoError.INCOMP);
					System.out.println("Incompatibilidad de tipos en asignación con "+ "Tipo1: " + t1 + " Tipo2: " + t2);
					errors=true;
					//throw new UnsupportedOperationException("Incompatibilidad de tipos en op. binaria con "+ "Tipo1: " + t1 + " Tipo2: " + t2);
				}
				else {
					if(e.kind()==KindE.IGUALCOMP ||e.kind()==KindE.DIST ||e.kind()==KindE.AND ||e.kind()==KindE.MAYOR ||e.kind()==KindE.MENOR) {
						insertaTipo(e,new Tipo("bool"));
						e.setTipo(new Tipo("bool"));
					}
					else {
						insertaTipo(e,e1.type());
						e.setTipo(e1.type());
					}
				}
			}
			else if(e.kind()==KindE.ACCESO) {
					chequea(e1);
					chequea(e2);
					Tipo t2 =  e2.type();
					if(t2!=null) {
						if(t2.kind()!=KindT.INT_0) {
							gestion.errorTipado(TipoError.ACCESO);
							System.out.println("El acceso a la posicion de un array debe ser a traves de un entero, no de un "+t2);
							//throw new Exception();
						}
						if(getTipo(e1)!=null) {
							insertaTipo(e,getTipo(e1).type());
							e.setTipo(getTipo(e1).type());
						}
						else {
							gestion.errorTipado(TipoError.INDEF);
							System.out.println("El tipo de "+e1+" no esta definido");
						}
					}
					else {
						gestion.errorTipado(TipoError.INDEF);
						System.out.println("El tipo de "+e1+" no esta definido");
					}
			}
			else {
				chequea(e1);
				chequea(e2);
				if(((Id) e1).getNodo()!=null) {
					Struct0 st=(Struct0) ((Id) ((Id) e1).getNodo()).getNodo();
					Boolean b=st.getListaCampos().containsKey(e2.id());
					Tipo t2 =  e2.type();
					insertaTipo(e,t2);
					e.setTipo(t2);
					if(!b) {
						gestion.errorTipado(TipoError.ST);
						errors=true;
						System.out.println("El struct "+st+ " no tiene el campo "+e2);
						//throw new Exception("El struct "+st+ " no tiene el campo "+st.getListaCampos().get(e2.id()));
					}
				}
				else {
					gestion.errorVinc(TipoError.INDEF_VAR);
					errors=true;
					System.out.println("El identificador "+e1+" no esta definido");
				}
			}
		}
		

		public Boolean getErrors() {
			return errors;
		}

		public void setErrors(Boolean errors) {
			this.errors = errors;
		}

		public void chequea(Num n) {
			insertaTipo(n,new Tipo("int"));
		}
		public void chequea(Bool b) {
			insertaTipo(b,new Tipo("bool"));
		}
		
		public void chequea(Id id) {
			/*if (not validoComoDesignador(id.vinculo)) {
				error id debe ser una variable, parámetro o campo;
				t=null;
			} else {
			*/
			ASTNode nodo=((Id) id).getNodo();
			if(nodo instanceof Id) {
				Tipo t = nodo.type();
				id.setTipo(t);
				insertaTipo(id,t);
			}
			else if(nodo instanceof Param) {
				Tipo t = nodo.type();
				id.setTipo(t);
				insertaTipo(id,t);
			}
			//}

		}
		
		public void chequea(Lista lista) {
			insertaTipo(lista,lista.type());
		}
		
		public void chequea (While w) throws Exception {
			chequea(w.opndExp());
			Tipo t=getTipo(w.opndExp());
			if(t.kind()!=KindT.B) {
				throw new Exception();
			}
			chequea((Bloq_I)w.opndBloq());
		}
		
		public void chequea (For f) throws Exception {
			chequea(f.opndAsig());
			Tipo tipoAsig=f.opndAsig().type();
			if(tipoAsig.kind()!=KindT.INT_0) {
				throw new Exception();
			}
			chequea(f.opndCond());
			Tipo tipCond=getTipo(f.opndCond());
			if(tipCond.kind()!=KindT.B) {
				throw new Exception();
			}
			chequea(f.opndIt());
			chequea((Bloq_I)f.opndBloq());
		}
		
		public void chequea (If i) throws Exception {
			chequea(i.opndExp());
			Tipo t=getTipo(i.opndExp());
			if(t.kind()!=KindT.B) {
				throw new Exception();
			}
			chequea(i.opndBloq());
			if(i.opndBloqElse()!=null) {
				chequea(i.opndBloqElse());
			}
		}
		
		public void chequea (Lec l) throws Exception {
			chequea(l.opnd1());
			Tipo t=getTipo(l.opnd1());
			if(!esLegible(t)) {
				throw new Exception();
			}
		}
		public void chequea (Esc l) throws Exception {
			chequea(l.opnd1());
			Tipo t=getTipo(l.opnd1());
			if(!esLegible(t)) {
				throw new Exception();
			}
		}
		
		public void chequea (Def d) throws Exception {
			if(d.opndListaDef()==null) {
				if(tablaTipos.containsKey(d.opnd().toString())) {
					Tipo t=(Tipo) tablaTipos.get(d.opnd().toString());
					chequea(d.opnd2());
					d.opnd2().setTipo(t);
					insertaTipo(d,t);
					d.setTipo(t);
				}
				else {
					chequea(d.opnd2());
					insertaTipo(d,d.type());
				}
				
			}
			else {
				chequea(d.opndDef());
				chequea(d.opndListaDef());
			}
		}
		
		public void chequea (Ret ret) throws Exception {
			chequea(ret.opnd1());
		}
		
		public void chequea (Call_Fun call) throws Exception {
			
			Def_Fun nodoDef=(Def_Fun) ((Id) call.opndIden()).getNodo();
			chequeaTiposParametros(nodoDef.opndParam(),call.opndParam());
			insertaTipo(call,nodoDef.type());
		}
		
		public void chequeaTiposParametros(P parametrosDef,P parametrosCall) throws Exception {
			if(parametrosDef.opndLista()!=null) {
				chequeaTiposParametros(parametrosDef.opndParam(),parametrosCall.opndParam());
				chequeaTiposParametros(parametrosDef.opndLista(),parametrosCall.opndLista());
			}
			else {
				chequea(parametrosCall.opndIden());
				((Param) parametrosCall).setTipo(parametrosCall.opndIden().type());
				if(parametrosCall.opndIden() instanceof Id) {
					ASTNode nodo=((Id) parametrosCall.opndIden()).getNodo();
					if(nodo instanceof Id) {
						Tipo t1=(Tipo) parametrosDef.opndTipo();
						Tipo t2=nodo.type();
						if(!t1.toString().contentEquals(t2.toString())){
							gestion.errorTipado(TipoError.INCOMP);
							System.out.println("Incompatibilidad de tipos en asignación con "+ "Tipo1: " + t1 + " Tipo2: " + t2);
							errors=true;
							//throw new Exception();
						}
					}
					if(nodo instanceof Param) {
						Tipo t1=(Tipo) parametrosDef.opndTipo();
						Tipo t2=nodo.type();
						if(!t1.toString().contentEquals(t2.toString())){
							gestion.errorTipado(TipoError.INCOMP);
							System.out.println("Incompatibilidad de tipos en asignación con "+ "Tipo1: " + t1 + " Tipo2: " + t2);
							errors=true;
							//throw new Exception();
						}
					}
					((Param) parametrosDef).setTipo(((Param) parametrosCall).getTipo());
					parametrosDef.opndIden().setTipo((Tipo) ((Param) parametrosCall).getTipo());
					/*((Param) parametrosCall).setTipo()
					((Param) parametrosDef).setTam(parametrosCall.opndIden().type().getTam());
					Tipo t=(Tipo) parametrosDef.type().opnd();
					while(t!=null) {
						t.setTam(((Tipo) parametrosCall.type().opnd()).getTam());
						t=(Tipo) t.opnd();
					}*/
				}
				else if(parametrosCall.opndIden() instanceof EBin){
					chequea(parametrosCall.opndIden());
					Tipo t1=(Tipo) parametrosDef.opndTipo();
					Tipo t2=((EBin) parametrosCall.opndIden()).type();
					if(!compatibles(t1,t2)){
						System.out.println("Incompatibilidad de tipos en asignación con "+ "Tipo1: " + t1 + " Tipo2: " + t2);
						errors=true;
						//throw new Exception();
					}
				}
				else if(parametrosCall.opndIden() instanceof Num){
					Tipo t1=(Tipo) parametrosDef.opndTipo();
					Tipo t2=((Num) parametrosCall.opndIden()).type();
					if(!compatibles((Tipo) parametrosDef.opndTipo(),((Num) parametrosCall.opndIden()).type())){
						gestion.errorTipado(TipoError.INCOMP);
						System.out.println("Incompatibilidad de tipos en asignación con "+ "Tipo1: " + t1 + " Tipo2: " + t2);
						errors=true;
						//throw new Exception();
					}
				}
				else if(parametrosCall.opndIden() instanceof Bool){
					Tipo t1=(Tipo) parametrosDef.opndTipo();
					Tipo t2=((Bool) parametrosCall.opndIden()).type();
					if(!compatibles((Tipo) parametrosDef.opndTipo(),((Bool) parametrosCall.opndIden()).type())){
						gestion.errorTipado(TipoError.INCOMP);
						System.out.println("Incompatibilidad de tipos en asignación con "+ "Tipo1: " + t1 + " Tipo2: " + t2);
						errors=true;
						//throw new Exception();
					}
				}
			}
		}
		
		private boolean compatibles(Tipo tipoA, Tipo tipoB) {
			if(tipoA.kind()!=KindT.ARRAY) {
				return tipoA.toString().equals(tipoB.toString());
			}
			else {
				return tipoA.toString().equals(tipoB.toString()) && coincidenTamaños(tipoA,tipoB);
			}
		}
		
		public boolean coincidenTamaños(Tipo tipoA, Tipo tipoB) {
			if(tipoA.kind()==KindT.INT_0 ||tipoA.kind()==KindT.B) {
				return true;
			}
			return tipoA.getTam()==tipoB.getTam() && coincidenTamaños((Tipo)tipoA.opnd(), (Tipo)tipoB.opnd());
		}

		public void muestraTablaTipos() {
			for (Object key: tablaTipos.keySet()){  
					System.out.println(key+ " => " + tablaTipos.get(key));
				}
			
		}


}
