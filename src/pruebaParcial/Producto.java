package pruebaParcial;


import java.io.Serializable;

public class Producto implements Serializable{
	
	private static final long serialVersionUID = 1426111633987671954L;
	private int idProducto;
	private String nombre;
	private double precio;
	
	public Producto(int idProducto, String nombre, double precio) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public double getPrecio() {
		return precio;
	}
	
}
