package tablasimbolos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TablaSimbolos<ASTNode> {
	private List<Map<String, ASTNode>> tablaDeSimbolos;
	private int tam;
	
	public void iniciaTS() {
		tablaDeSimbolos = new ArrayList<Map<String, ASTNode>>();	
		tam=0;
	}
	
	public void abreBloque() {
		tablaDeSimbolos.add(new HashMap<String, ASTNode>());	
		tam++;
	}

	public void cierraBloque() {
		tablaDeSimbolos.remove(tam-1);
		tam--;
	}
	
	public boolean insertaID(String id, ASTNode declaracion){
		Map<String, ASTNode> ts = tablaDeSimbolos.get(tam-1);		
		if (ts.get(id) != null){ return false; }
		ts.put(id, declaracion);
		return true;
	}
	
	public Object buscaId(String id) {
		for(int i=tam-1;i>=0;i--) {
			Boolean esta=tablaDeSimbolos.get(i).containsKey(id);
			if(esta) {
				return tablaDeSimbolos.get(i).get(id);
			}
		}
		return null;
	}
	
	public Object buscaArriba(String id) {
		for(int i=0;i<tam;i++) {
			Boolean esta=tablaDeSimbolos.get(i).containsKey(id);
			if(esta) {
				return tablaDeSimbolos.get(i).get(id);
			}
		}
		return null;
	}

	public List<Map<String, ASTNode>> getTablaDeSimbolos() {
		return tablaDeSimbolos;
	}

	public int getTam() {
		return tam;
	}
	public void mostrarTabla() {
		for (int i=0;i<tam;i++) {
			System.out.println("-------------------------------------------------------------------------------------------------------");
			for (String key: tablaDeSimbolos.get(i).keySet()){  
				System.out.println(key+ " = " + tablaDeSimbolos.get(i).get(key));
			} 
		}
	}
	
	
}