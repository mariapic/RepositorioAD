package accesoAleatorio.data;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class IntroducirEmpleadosAlea {
	private final static int TAM_NOMBRE = 10;

	public static void main(String[] args) {
		String[] nombres = {"Alberto", "Guillermo", "Alejandro", "Ana", "Patricia"};
     	int[] departamentos = {10, 20, 30, 20, 10};
     	double[] salarios = {2000.00, 1500.50, 3000.40, 2300.60, 1900.10};
     	
     	RandomAccessFile raf = null;
     	
     	try {
			raf = new RandomAccessFile("empleados.dat", "rw");
			
			//nos situamos al final del fichero
			raf.seek(raf.length());
			
			long tam = raf.length();
			// cada registro ocupa 36 bytes
			int numReg = (int) tam / 36; // numero de registros
			
			// ** empezamos a escribir **
			
			// definimos un StringBuffer para almacenar el nombre y 
			// que siempre tenga tamaño 10, rellenando con espacios en blanco
			StringBuffer sb = null;
			for (int i = 0; i < salarios.length; i++) {
				// id
				raf.writeInt(numReg + i + 1);

				sb = new StringBuffer(nombres[i]);
				sb.setLength(TAM_NOMBRE);
				
				
			}

		     
		     
		     
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
