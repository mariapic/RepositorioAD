package accesoAleatorio.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class ConsultarEmpleadoBinAlea {
	
	final static int TAM_NOMBRE = 10;
	final static int TAM_REGISTRO = 36; // 4 + 20 + 4 + 8 ID, NOMBRE, DEPT Y SAL

	public static void main(String[] args) {
		RandomAccessFile raf = null;
		Scanner sc = new Scanner(System.in);
		
		try {
			raf = new RandomAccessFile("empleados2.dat", "r");
			
			System.out.println("Introduce el id del empleado que deseas consultar");
			int id = Integer.parseInt(sc.nextLine());
			
			// con el id calculamos la posición en el registro
			int pos = (id - 1) * TAM_REGISTRO;
			
			if (pos < raf.length()) {
				//nos situamos en la posicion
				raf.seek(pos);
				
				id = raf.readInt();
				char[] cNombre = new char[TAM_NOMBRE];
				for (int i = 0; i < TAM_NOMBRE; i++) {
					cNombre[i] = raf.readChar();
				}
				String nombre = new String(cNombre);
				
				int dept = raf.readInt();
				double salario = raf.readDouble();
				
				System.out.println("ID: " + id + " Nombre: " + nombre + " Depto: " + dept + " Salario: " + salario);
				
			} else {
				System.out.println("No existe ningún empleado para el id especificado");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			
			sc.close();
		}

	}

}
