package errors;

import alex.UnidadLexica;

public class GestionErroresTiny {
	 static Boolean errorlexico=false;
	 static Boolean errorsintactico=false;
	 static Boolean errorvinc=false;
	 static Boolean errortip=false;
	 
   public void errorLexico(int fila, int columna, String lexema) {
	 errorlexico=true;
     System.out.println("ERROR LEXICO fila "+fila+" columna "+columna+": Caracter inesperado: "+lexema); 
   }  
   public static Boolean getErrorlexico() {
	return errorlexico;
}
public static void setErrorlexico(Boolean errorlexico) {
	GestionErroresTiny.errorlexico = errorlexico;
}
public static Boolean getErrorsintactico() {
	return errorsintactico;
}
public static void setErrorsintactico(Boolean errorsintactico) {
	GestionErroresTiny.errorsintactico = errorsintactico;
}
public static Boolean getErrorvinc() {
	return errorvinc;
}
public static void setErrorvinc(Boolean errorvinc) {
	GestionErroresTiny.errorvinc = errorvinc;
}
public static Boolean getErrortip() {
	return errortip;
}
public static void setErrortip(Boolean errortip) {
	GestionErroresTiny.errortip = errortip;
}
public void errorSintactico(UnidadLexica unidadLexica) {
     if (unidadLexica.lexema() != null) {
       System.out.println("ERROR SINTACTICO fila "+unidadLexica.fila()+" columna "+unidadLexica.columna()+": Elemento inesperado \""+unidadLexica.lexema()+"\"");
     } else {
       System.out.println("ERROR SINTACTICO fila "+unidadLexica.fila()+" columna "+unidadLexica.columna()+": Elemento inesperado");
     }
	 errorsintactico=true;

   }
   public void errorTipado(TipoError error) {
	   errortip=true;
	   System.out.print("Error en tipado. ");
		switch(error){
			case RET: {				
				System.out.println("No coincide el tipo de la funcion y el return");
			};  break;
			case INCOMP: {				
				System.out.println("Incompatibilidad de tipos:");
			};  break;
			case ACCESO: {				
				System.out.println("Error al acceder a la posicion de un array:");
			};  break;
			case INDEF: {				
				System.out.println("Error de tipo indefinido:");
			};  break;
			case ST: {				
				System.out.println("Error al acceder a los campos de un struct:");
			};  break;
			default: break;
		}	

	}
   public void errorVinc(TipoError error) {
	   errorvinc=true;
	   System.out.print("Error en vinculacion. ");
		switch(error){
			case INDEF_VAR: {				
				System.out.println("Variable no definida:");
			};  break;
			case REPTIPO: {				
				System.out.println("Tipo repetido:");
			};  break;
			default: break;
		}	

	}
}
