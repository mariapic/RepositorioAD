package ficherosBinarios.buffered;
import java.io.*;

public class CopiaFicherosBin {

	public static void main(String[] args) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			bis = new BufferedInputStream(
					new FileInputStream("C:\\eclipse-workspace\\Ficheros\\bin\\CopiaFicherosBin.class"));
			bos = new BufferedOutputStream(
					new FileOutputStream("CopiaFicherosBin.class"));
			
			// creamos un array de bytes para la lectura
			byte [] array = new byte[1000];
			// utilizamos el método read para leer, volcando lo que lea en el 
			// array de bytes
			int leidos = bis.read(array);
			
			while (leidos > 0) {
				// 
				bos.write(array,0,leidos);
				leidos = bis.read(array);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// código para cerrar el flujo
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// fin del código para cerrar el flujo
			
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}

	}

}
