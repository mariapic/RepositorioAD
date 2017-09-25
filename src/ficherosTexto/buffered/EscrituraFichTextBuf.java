package ficherosTexto.buffered;
import java.io.*;

public class EscrituraFichTextBuf {

	public static void main(String[] args) {
		String nombre = "C:\\eclipse-workspace\\Ficheros\\fichero3.txt";
		BufferedWriter bw = null;
		String[] lineas = { "Línea 1", "Línea 2", "Línea 3", 
				"Línea 4", "Línea 5", "Línea 6"};
		
		try {
			/* el segundo parámetro a true nos indica que la escritura 
			   se realiza al final del fichero sin machacar lo anterior*/
			bw = new BufferedWriter(new FileWriter(nombre,true));
			
			for (int i = 0; i < lineas.length; i++) {
				bw.write(lineas[i]);
				bw.newLine();
			}
			
		} catch (IOException e) {
			System.out.println("Error IO: " + e.getMessage());
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
