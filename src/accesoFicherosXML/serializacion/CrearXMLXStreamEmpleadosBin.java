package accesoFicherosXML.serializacion;

import java.io.*;

import com.thoughtworks.xstream.XStream;

//import com.thoughtworks.xstream.XStream;

import accesoFicherosXML.dom.Empleado;

public class CrearXMLXStreamEmpleadosBin {

	public static void main(String[] args) {
		
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream("empleados.dat"));
			Empleado emp = null;
			ListaEmpleados listaEmp = new ListaEmpleados();			
			// controlamos el final del fichero controlando la excepción EOFException 
			try {
				//Recorreremos el fichero con los datos para que al leer cada registro empleado 
				// se cree un nodo empleado con 4 hijos (<id>, <nombre>, <dep> y <salario>) 
				while ((emp = (Empleado) ois.readObject()) != null) {
						System.out.println("ID: " + emp.getId() + " Nombre: " + emp.getNombre() + " Depto: " 
											+ emp.getDep() + " Salario: " + emp.getSalario());
						
						// Creamos el nodo <empleado> y lo añadimos al documento
						listaEmp.add(emp);
				}
			} catch (EOFException e) {} 
			
			// Creamos el fichero XML 
			XStream xs = new XStream();
			xs.alias("Empleados", ListaEmpleados.class);
			xs.alias("DatosEmpleado", Empleado.class);
			xs.addImplicitCollection(ListaEmpleados.class, "lista");
			xs.toXML(listaEmp, new FileOutputStream("empleadosXStream.xml"));
			
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
