package modelo;
import java.io.Serializable;

public class Persona implements Serializable {

	private static final long serialVersionUID = 284796237982514963L;

	private String nombre;
	private int edad;
	
	public Persona(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

}
