package ficherosBinarios.objectIOStream;
import java.io.*;

import modelo.Persona;

public class EscribirFichBinObj {

	public static void main(String[] args) {
		String[] nombres = { "Juan", "Miguel", "Carlos", "Javier", "Pedro"};
		int[] edades = { 20, 19, 22, 23, 19};
		
		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream("alumnos.dat"));
			
			Persona p = null;
			for (int i = 0; i < edades.length; i++) {
				p = new Persona(nombres[i], edades[i]);
				
				oos.writeObject(p);
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
