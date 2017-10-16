package accesoFicherosXML.dom;

import java.io.Serializable;

public class Depto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private String localizacion;
	
	public Depto(int id, String nombre, String localizacion) {
		this.id = id;
		this.nombre = nombre;
		this.localizacion = localizacion;
	}

	public Depto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	

}
