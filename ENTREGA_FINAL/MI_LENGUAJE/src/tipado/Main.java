package tipado;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import alex.AnalizadorLexicoTiny;
import ast.Inicio;
import constructorast.ConstructorASTTiny;
import tablasimbolos.TablaSimbolos;

public class Main {

	public static void main(String[] args) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
		 AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
		 ConstructorASTTiny constructorast = new ConstructorASTTiny(alex);
		 TablaSimbolos tabla=new TablaSimbolos();
		 tabla.iniciaTS();
		 Inicio i=(Inicio) constructorast.parse().value;
		 i.bind(tabla);
		 tabla.mostrarTabla();
		 Map<Object, Object> tiposSimples=new HashMap();
		 Chequeo chequeo=new Chequeo(tiposSimples);
		 chequeo.chequea(i);
		 chequeo.muestraTablaTipos();
	}
	
	

}
