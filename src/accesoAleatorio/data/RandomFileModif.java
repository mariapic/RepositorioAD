package accesoAleatorio.data;
import java.io.*;
import java.util.Scanner;

public class RandomFileModif {

	public static void main(String[] args) {
		
		RandomAccessFile raf = null;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce la posición que ocupa el entero a modificar: ");
		int pos = Integer.parseInt(sc.nextLine()) - 1;
		
		try {
			raf = new RandomAccessFile("enteros.dat", "rw");
			
			// tamaño del fichero en bytes
			long tam = raf.length();
			// ***** cada int en java ocupa 4 bytes *****
			// al divivir entre cuatro obtenemos el número de enteros que tiene el fichero
			tam = tam / 4;
			
			// controlamos que la pos introducida es correcta
			while (pos < 0 || pos > tam - 1) {
				System.out.println("Introduce una posición >= 1 y <= " + tam + ":");
				pos = Integer.parseInt(sc.nextLine()) - 1;
			}
			
			// posicionarnos en el entero correspondiente a la posición
			raf.seek(pos * 4);
			
			// mostramos el valor que se va a modificar
			System.out.println("El entero en la posición " + (pos+1) + " es " + raf.readInt());
			
			// solicitamos el nuevo valor
			System.out.println("");
			System.out.println("Introduce el nuevo valor del entero: ");
			int nuevoInt = Integer.parseInt(sc.nextLine());
			
			// nos volvemos a posicionar tras haber leído
			raf.seek(pos * 4);
			// escribimos el nuevo entero en la posición indicada
			raf.writeInt(nuevoInt);
			
			// ahora leemos todo el contenido del fichero
			// nos posicionamos al inicio
			raf.seek(0);
			System.out.println("El contenido del fichero es el siguiente:");
			int nLeido;
			while (true) {
				nLeido = raf.readInt();
				System.out.println(nLeido);
			}
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			System.out.println("Fin del fichero");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			sc.close();
		}

	}

}
