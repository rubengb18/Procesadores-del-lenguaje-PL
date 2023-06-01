package generacodigo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscribeArchivo {
	private FileWriter fw;	
	private String codigo;
	
	public EscribeArchivo(String codigo) {
		this.codigo = codigo;
	}
	
	private void abreArchivo(String nombreArchivo){
		try {
			fw = new FileWriter(new File(nombreArchivo), false);
		} catch (IOException e) { }
	}
	
	private void cierraArchivo(){
		try {
			fw.close();
		} catch (IOException e) { }
	}
	
	private void escribeEnArchivo(String codigo){
		try {
			fw.append(codigo);
		} catch (IOException e) { }
	}

	public void escribeCodigo(String nombreArchivo) {
		abreArchivo(nombreArchivo);
		imprimeCodigo(codigo);	
		cierraArchivo();
	}	

	public void imprimeCodigo(String codigo){
			System.out.println(codigo);
			escribeEnArchivo(codigo);
	}
}
