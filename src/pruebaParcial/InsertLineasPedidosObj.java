package pruebaParcial;

import java.io.*;

public class InsertLineasPedidosObj {

	public static void main(String[] args) {
		
		int[] idProd = {1, 2, 3, 2, 1};
		String[] productos = {"Producto 1", "Producto 2", "Producto 3", "Producto 2", "Producto 1"};
		double[] precios = {2.10, 7.50, 10.40, 7.50, 2.10};
		int[] cantidad = {2, 6, 12, 4, 10};
		String[] fechas = {"05/02/2017", "18/05/2017", "13/01/2017", "17/08/2017", "03/09/2017"};
		
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream("lineasPedidos.dat"));
			
			Producto prod = null;
			for (int i = 0; i < precios.length; i++) {
				prod = new Producto(idProd[i], productos[i], precios[i]);
				oos.writeObject(new LineaPedido(i+1, prod, cantidad[i], cantidad[i]*precios[i], fechas[i]));
			}
			
			ois = new ObjectInputStream(new FileInputStream("lineasPedidos.dat"));
			LineaPedido lp = null;

			System.out.println("Lista de Empleados");
			try {
				while ((lp = (LineaPedido) ois.readObject()) != null) {
					System.out.println("ID: " + lp.getIdPedido() + " Producto: " + lp.getProducto().getNombre() 
							+ " Cantidad: " + lp.getCantidad() + " Precio Total: " + lp.getPrecioTotal()
							+ " Fecha Pedido: " + lp.getFechaPedido());
				}
			} catch (EOFException e) {} 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


	}

}
