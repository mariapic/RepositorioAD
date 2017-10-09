package tareauf1_1;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class InsertCochesBinAlea {

	public static final int TAM_MODELO = 20;
	public static final int TAM_MARCA = 10;
	
	public static void main(String[] args) {
		String[] modelos = {"modelo1", "Guillermo", "Alejandro", "Ana", "Patricia"};
		String[] marcas = {"Marca1", "Guillermo", "Alejandro", "Ana", "Patricia"};
		int[] anios = {10, 20, 30, 20, 10};
		double[] precios = {2000.00, 1500.50, 3000.40, 2300.60, 1900.10};
		
		RandomAccessFile raf = null;
		
		try {
			raf = new RandomAccessFile("coches.dat", "rw");
			
			raf.seek(raf.length());
			
			StringBuffer sbModelo = null;
			StringBuffer sbMarca = null;
			for (int i = 0; i < precios.length; i++) {
				sbMarca = new StringBuffer(marcas[i]);
				sbMarca.setLength(TAM_MARCA); 
				raf.writeChars(sbMarca.toString());
				sbModelo = new StringBuffer(modelos[i]);
				sbModelo.setLength(TAM_MODELO); 
				raf.writeChars(sbModelo.toString());
				
				raf.writeInt(anios[i]);
				raf.writeDouble(precios[i]);
			}
			
			// leer
			raf.seek(0);
			int anio;
			char[] cModelo = new char[TAM_MODELO];
			char[] cMarca = new char[TAM_MARCA];
			String modelo;
			String marca;
			double precio;
				
			try {
				while (raf.getFilePointer() <= raf.length()) {
					for (int i = 0; i < TAM_MARCA; i++) {
						cMarca[i] = raf.readChar();
					}
					marca = new String(cMarca);
					
					for (int i = 0; i < TAM_MODELO; i++) {
						cModelo[i] = raf.readChar();
					}
					modelo = new String(cModelo);
					
					anio = raf.readInt();
					precio = raf.readDouble();
					
					System.out.println("Marca: " + marca + " Modelo: " + modelo + " Año: " + anio + " Precio: " + precio);
					
				}
			} catch (EOFException e) {
			
			} 
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
