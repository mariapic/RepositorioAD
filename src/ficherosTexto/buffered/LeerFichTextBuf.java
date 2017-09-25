package ficherosTexto.buffered;
import java.io.*;

public class LeerFichTextBuf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nombre = "C:\\eclipse-workspace\\Ficheros\\fichero2.txt";
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(nombre));
			
			String linea = null;
			
			while((linea = br.readLine()) !=  null){
                System.out.println(linea);
            }

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no ha sido encontrado");
		} catch (IOException e) {
			System.out.println("Error IO: " + e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
