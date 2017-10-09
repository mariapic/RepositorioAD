package tareauf1_1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeerYCambiarVocales {

	public static void main(String[] args) {
		FileReader fr = null;
		
		try {
			fr = new FileReader("enunciado.txt");
			
			// leemos los códigos de los caracteres
			int code;
			// para almacenar el caracter correspondiente a code
			char c;
			String enunciado = "";
			while ((code = fr.read()) != -1) {
				// tenemos que sustituir la e,i,o,u,E,I,O,U por a o A
				if (code == 69 || code == 73 || code == 79 || code == 85) {
					c = 'A';
				} else if (code == 101 || code == 105 || code == 111 || code == 117) {
					c = 'a';
				} else {
					c = (char) code;
				}
				enunciado = enunciado + c;
				System.out.println("----" + code + " - " + (char) code);
				
			}
			System.out.print(enunciado);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
