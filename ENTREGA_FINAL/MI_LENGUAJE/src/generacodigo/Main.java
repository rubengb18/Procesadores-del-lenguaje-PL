package generacodigo;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import alex.AnalizadorLexicoTiny;
import ast.Inicio;
import constructorast.ConstructorASTTiny;
import errors.GestionErroresTiny;
import tablasimbolos.TablaSimbolos;
import tipado.Chequeo;

public class Main {

	public static void main(String[] args) throws Exception {
		 Reader input = new InputStreamReader(new FileInputStream(args[0]));
		 AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
		 ConstructorASTTiny constructorast = new ConstructorASTTiny(alex);

		 TablaSimbolos tabla=new TablaSimbolos();
		 tabla.iniciaTS();
		 Inicio i=(Inicio) constructorast.parse().value;
		 if(GestionErroresTiny.getErrorsintactico()==true||GestionErroresTiny.getErrorlexico()==true) {
			 System.out.println("Ha habido errores lexicos/sintacticos y no seguimos con la vinculacion ni el tipado ni la generacion de codigo");
			 System.exit(1);
		 }
		 i.bind(tabla);
		 if(GestionErroresTiny.getErrorvinc()==true) {
			 System.out.println("Ha habido errores en la vinculacion y no seguimos con el tipado ni la generacion de codigo");
			 System.exit(1);
		 }
		 //tabla.mostrarTabla();		Para ver la tabla de simbolos
		 Map<Object, Object> tiposSimples=new HashMap<Object, Object>();
		 Chequeo chequeo=new Chequeo(tiposSimples);
		 chequeo.chequea(i);
		 if(GestionErroresTiny.getErrortip()==false) {
			 //chequeo.muestraTablaTipos();	Para ver la tabla de tipos
			 String codigo=i.generateCode();
			 EscribeArchivo f=new EscribeArchivo(codigo);
			 f.escribeCodigo("codigo.wat");
		 }
		 else {
			 System.out.println("Ha habido errores en el tipado y no seguimos con la generacion de codigo");
		 }
	}

}
