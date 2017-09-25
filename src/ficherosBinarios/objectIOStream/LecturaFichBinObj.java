package ficherosBinarios.objectIOStream;
import java.io.*;

import modelo.Persona;

public class LecturaFichBinObj {

	public static void main(String[] args) {
		// declaramos el stream de entrada
		ObjectInputStream ois = null;
		
		// abrimos un try para la controlar las excepciones
		try {
			// instanciamos el stream de entrada apuntando al ficharo binario alumnos.dat
			ois = new ObjectInputStream(new FileInputStream("alumnos.dat"));
			
			// creamos una objeto persona y le asignamos el valor del primer objeto leído
			Persona p = (Persona) ois.readObject();
			// mientras el objeto sea distinto de null entramos en el bucle
			while (p != null) {
				// mostramos por consola los valores de los atributos del objeto de tipo Persona
				System.out.println("Nombre: "+ p.getNombre() + " Edad: "+ p.getEdad());
				// leemos el siquiente objeto
				p = (Persona) ois.readObject();
			}
			
			/* este código sustituiría al anterior
			Persona p = null;
			while ((p = (Persona) ois.readObject()) != null) {
				System.out.println("Nombre: "+ p.getNombre() + " Edad: "+ p.getEdad());				
			}*/
			
		// BLOQUES catch
		// controlamos las posibles excepciones que se pueden producir en el código
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally { // BLOQUE finally que siempre se ejecuta
			// si se ha creado el stream
			if (ois != null) {
				try {
					// lo cerramos
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
