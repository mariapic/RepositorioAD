package utilidadesFile;
import java.io.File;

public class EjFileList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Ficheros del directorio actual");
		
		File f = new File(".");
		String[] ficheros = f.list();
		
		for (int i = 0; i < ficheros.length; i++) {
			System.out.println(ficheros[i]);
		}
		
		System.out.println("");
		System.out.println("Ficheros del directorio Eclipse");
		
		File f2 = new File("C:\\Eclipse");
		ficheros = f2.list();
		
		for (int i = 0; i < ficheros.length; i++) {
			System.out.println(ficheros[i]);
		}

	}

}
