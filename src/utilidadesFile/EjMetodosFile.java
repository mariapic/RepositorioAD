package utilidadesFile;
import java.io.File;

public class EjMetodosFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("C:\\eclipse-workspace\\Ficheros\\primerFichero.txt");

		if (f.exists()) {
			System.out.println("Nombre del fichero: " + f.getName());
			System.out.println("Ruta: " + f.getPath());
			System.out.println("Ruta absoluta: " + f.getAbsolutePath());
			System.out.println("¿Se puede leer?: " + f.canRead());
			System.out.println("¿Se puede escribir?: " + f.canWrite());
			System.out.println("Tamaño: " + f.length());
			
			if (f.isDirectory()) {
				System.out.println("Es directorio");
			} else if (f.isFile()) {
				System.out.println("Es archivo");
			}
			
		}
	}

}
