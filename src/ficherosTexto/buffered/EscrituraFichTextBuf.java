package ficherosTexto.buffered;
import java.io.*;

public class EscrituraFichTextBuf {

	public static void main(String[] args) {
		String nombre = "C:\\eclipse-workspace\\Ficheros\\fichero3.txt";
		BufferedWriter bw = null;
		String[] lineas = { "L�nea 1", "L�nea 2", "L�nea 3", 
				"L�nea 4", "L�nea 5", "L�nea 6"};
		
		try {
			/* el segundo par�metro a true nos indica que la escritura 
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
