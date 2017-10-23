package pruebaParcial;


import java.io.Serializable;

public class LineaPedido implements Serializable{
	
	private static final long serialVersionUID = -2621930855385763215L;
	
	private int idPedido;
	private Producto producto;
	private int cantidad;
	private double precioTotal;
	private String fechaPedido;
	
	public LineaPedido(int idPedido, Producto producto, int cantidad, double precioTotal, String fechaPedido) {
		this.idPedido = idPedido;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioTotal = precioTotal;
		this.fechaPedido = fechaPedido;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public String getFechaPedido() {
		return fechaPedido;
	}
	
	
}
