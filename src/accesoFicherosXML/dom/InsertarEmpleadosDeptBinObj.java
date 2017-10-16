package accesoFicherosXML.dom;

import java.io.*;

public class InsertarEmpleadosDeptBinObj {

	public static void main(String[] args) {
		
		String[] nombres = {"Alberto", "Guillermo", "Alejandro", "Ana", "Patricia"};
		int[] departamentos = {10, 20, 30, 20, 10};
		String[] nomDeptos = {"Contabilidad", "Marketing", "Comercial", "Marketing", "Contabilidad"};
		String[] locDeptos = {"Madrid", "Sevilla", "Valladolid", "Sevilla", "Madrid"};
		double[] salarios = {2000.00, 1500.50, 3000.40, 2300.60, 1900.10};
		
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream("empleadosDepto.dat"));
			
			Depto dep = null;
			for (int i = 0; i < salarios.length; i++) {
				dep = new Depto(departamentos[i], nomDeptos[i], locDeptos[i]);
				oos.writeObject(new EmpleadoDepto(i+1, nombres[i], dep, salarios[i]));
			}
			
			ois = new ObjectInputStream(new FileInputStream("empleadosDepto.dat"));
			EmpleadoDepto emp = null;

			System.out.println("Lista de Empleados");
			try {
				while ((emp = (EmpleadoDepto) ois.readObject()) != null) {
					System.out.println("ID: " + emp.getId() + " Nombre: " + emp.getNombre() + " Depto: " 
										+ emp.getDep().getNombre() + " Salario: " + emp.getSalario());
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
