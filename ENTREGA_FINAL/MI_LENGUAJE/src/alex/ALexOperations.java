package alex;

import constructorast.ClaseLexica;
//import asint.ClaseLexica;

public class ALexOperations {
  private AnalizadorLexicoTiny alex;
  public ALexOperations(AnalizadorLexicoTiny alex) {
   this.alex = alex;   
  }
  public UnidadLexica unidadId() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IDEN,
                                         alex.lexema()); 
  } 
  public UnidadLexica unidadEnt() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ENT,alex.lexema()); 
  }  
  public UnidadLexica unidadSuma() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAS); 
  } 
  public UnidadLexica unidadResta() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENOS); 
  } 
  public UnidadLexica unidadMul() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.POR); 
  } 
  public UnidadLexica unidadDiv() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DIV); 
  } 
  public UnidadLexica unidadPAp() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PAP,alex.lexema()); 
  } 
  public UnidadLexica unidadPCierre() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PCIERRE,alex.lexema()); 
  } 
  public UnidadLexica unidadIgual() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IGUAL); 
  } 
  public UnidadLexica unidadComa() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.COMA); 
  } 
  public UnidadLexica unidadCorcheteApertura() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CORAP,alex.lexema()); 
  } 
  public UnidadLexica unidadCorcheteCierre() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CORCI,alex.lexema()); 
  } 
  public UnidadLexica unidadOperadorMayor() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAYOR,alex.lexema()); 
  } 
  public UnidadLexica unidadOperadorMenor() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENOR,alex.lexema()); 
  } 
  public UnidadLexica unidadOperadorIgualComp() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IGUALCOMP,alex.lexema()); 
  } 
  public UnidadLexica unidadIf() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IF,alex.lexema()); 
  }
  public UnidadLexica unidadWhile() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.WHILE,alex.lexema()); 
  }
  public UnidadLexica unidadOperadorDist() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DIST,alex.lexema()); 
  }
  public UnidadLexica unidadPuntoComa() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PUNTOCOMA,alex.lexema()); 
	  }
  public UnidadLexica unidadPunto() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PUNTO,alex.lexema()); 
	  }
  public UnidadLexica unidadLlaveAp() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.LAP,alex.lexema()); 
  } 
  public UnidadLexica unidadLlaveCierre() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.LCI,alex.lexema()); 
  } 
  public UnidadLexica unidadBool() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.B,alex.lexema()); 
	  } 
  public UnidadLexica unidadTrue() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.T,alex.lexema()); 
	  } 
  public UnidadLexica unidadFalse() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.F,alex.lexema()); 
	  } 
  public UnidadLexica unidadInt() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.INT_0,alex.lexema()); 
	  } 
  public UnidadLexica unidadArray() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ARRAY,alex.lexema()); 
	  }
  public UnidadLexica unidadOperadorAnd() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.AND,alex.lexema()); 
	  } 
  public UnidadLexica unidadOperadorOr() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.OR,alex.lexema()); 
	  } 
  public UnidadLexica unidadElse() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ELSE,alex.lexema()); 
	  } 
  public UnidadLexica unidadDef() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DEF,alex.lexema()); 
	  }
  public UnidadLexica unidadVoid() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.VOID,alex.lexema()); 
	  }
  public UnidadLexica unidadReturn() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.RET,alex.lexema()); 
	  }
  public UnidadLexica unidadRead() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.READ,alex.lexema()); 
	  }
  public UnidadLexica unidadPrint() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PRINT,alex.lexema()); 
	  }
  public UnidadLexica unidadParam() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ASP,alex.lexema()); 
	  }
  public UnidadLexica unidadStruct() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.STRUCT,alex.lexema()); 
	  }
  public UnidadLexica unidadTypedef() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.TYPEDEF,alex.lexema()); 
	  }
  public UnidadLexica unidadNew() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.NEW,alex.lexema()); 
	  }
  public UnidadLexica unidadFor() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FOR,alex.lexema()); 
	  }
  public UnidadLexica unidadEof() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.EOF); 
  }
}
