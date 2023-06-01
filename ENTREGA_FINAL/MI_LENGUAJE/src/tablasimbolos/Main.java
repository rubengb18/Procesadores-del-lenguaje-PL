package tablasimbolos;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoTiny;
import ast.Inicio;
import constructorast.ConstructorASTTiny;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;

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
 }
}   
   
