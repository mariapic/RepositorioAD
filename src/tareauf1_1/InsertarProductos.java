package tareauf1_1;
import java.io.*;

public class InsertarProductos {

	public static void main(String[] args) {
		String[] nombres = { "producto1", "producto2", "producto3", "producto4", "producto5", 
				"producto6", "producto7", "producto8", "producto9", "producto10"};
		String[] medidas = { "10x20x12", "10x20x12", "10x20x12", "10x20x12", "10x20x12", 
				"10x20x12", "10x20x12", "10x20x12", "10x20x12", "10x20x12"};
		float[] precio = {5, (float) 8.65, 10, (float) 12.4, 6, 21, (float) 80.30, 8, 1, 54};
		
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream("productos.dat"));
			
			Producto p = null;
			for (int i = 0; i < nombres.length; i++) {
				p = new Producto(i + 1, nombres[i], medidas[i], precio[i]);
				
				oos.writeObject(p);
				
			}
			
			ois = new ObjectInputStream(new FileInputStream("productos.dat"));
			
			try {
				p = (Producto) ois.readObject();
				while (p != null) {
					System.out.println("Nombre: "+ p.getNombre() + 
							" Medidas: "+ p.getMedidas() + 
							" Precio: "+ p.getPrecio());
					// leemos el siquiente objeto
					p = (Producto) ois.readObject();
				}
			} catch (EOFException e) {
				System.out.println("Fin del fichero");
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error IO");
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					System.out.println("Error IO");
				}
			}
			
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					System.out.println("Error IO");
				}
			}
		}

	}

}
