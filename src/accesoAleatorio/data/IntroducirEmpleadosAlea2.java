package accesoAleatorio.data;

import java.io.*;

/*
 * insertarán empleados en un fichero binario:
 *  un identificador(entero 4b)
 * 	el nombre (String 10 caracteres 20b, 2b cada caracter), 
 *  departamento (entero 4b) 
 *  y salario (double 8b) 
 *  obtenidos de unos arrays, se introducen de forma secuencial
 */

public class IntroducirEmpleadosAlea2 {
	
	final static int TAM_NOMBRE = 10;
	final static int TAM_REGISTRO = 36; // 4 + 20 + 4 + 8 ID, NOMBRE, DEPT Y SAL
	
	public static void main(String[] args) {
		RandomAccessFile raf = null;
	
		String[] nombres = {"Alberto", "Guillermo", "Alejandro", "Ana", "Patricia"};
		int[] departamentos = {10, 20, 30, 20, 10};
		double[] salarios = {2000.00, 1500.50, 3000.40, 2300.60, 1900.10};
		
		try {
			raf = new RandomAccessFile("empleados2.dat", "rw");
			
			int id = 0;
			if (raf.length() > 0) {
			//nos situamos en el último registro
				raf.seek(raf.length() - TAM_REGISTRO);
				
				// leemos el id
				id = raf.readInt();
			}
			
			//nos situamos al final del fichero
			raf.seek(raf.length());
			
			// definimos un StringBuffer para almacenar el nombre y que siempre tenga tamaño 10, rellenando con espacios en blanco
			StringBuffer sb = null;
			for (int i = 0; i < salarios.length; i++) {
				// id
				raf.writeInt(i + id + 1);
				// nombre
				sb = new StringBuffer(nombres[i]);
				sb.setLength(TAM_NOMBRE); 
				raf.writeChars(sb.toString());
				// depto
				raf.writeInt(departamentos[i]);
				// salario
				raf.writeDouble(salarios[i]);
				
			}
			
			//nos situamos al principio
			raf.seek(0);
			
			int dept;
			char[] cNombre = new char[TAM_NOMBRE];
			String nombre;
			double salario;
				
			try {
				while (raf.getFilePointer() <= raf.length()) {
					id = raf.readInt();
					
					for (int i = 0; i < TAM_NOMBRE; i++) {
						cNombre[i] = raf.readChar();
					}
					nombre = new String(cNombre);
					
					dept = raf.readInt();
					salario = raf.readDouble();
					
					System.out.println("ID: " + id + " Nombre: " + nombre + " Depto: " + dept + " Salario: " + salario);
					
				}
			} catch (EOFException e) {
			
			} 
			
			System.out.println("FICHERO LEIDO");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
