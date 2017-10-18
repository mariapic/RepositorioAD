package tareauf1_2;


import java.io.*;

import com.thoughtworks.xstream.XStream;

import tareauf1_1.Producto;

public class CrearXMLXStreamProductosBin {

	public static void main(String[] args) {
		
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream("productos.dat"));
			Producto prod = null;
			ListaProductos listaProd = new ListaProductos();			
			// controlamos el final del fichero controlando la excepción EOFException 
			try {
				//Recorreremos el fichero con los datos para que al leer cada registro empleado 
				// se cree un nodo empleado con 4 hijos (<id>, <nombre>, <dep> y <salario>) 
				while ((prod = (Producto) ois.readObject()) != null) {
						System.out.println("ID: " + prod.getId() + " Nombre: " + prod.getNombre() + " Medidas: " 
											+ prod.getMedidas() + " Precio: " + prod.getPrecio());
						
						// Creamos el nodo <empleado> y lo añadimos al documento
						listaProd.add(prod);
				}
			} catch (EOFException e) {} 
			
			// Creamos el fichero XML 
			XStream xs = new XStream();
			xs.alias("ListaProductos", ListaProductos.class);
			xs.alias("Producto", Producto.class);
			xs.addImplicitCollection(ListaProductos.class, "lista");
			xs.toXML(listaProd, new FileOutputStream("productos.xml"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
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
