package pruebaParcial;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class InsertLibrosAlea {

	static final int TAM_TITULO = 30;
	static final int TAM_AUTOR = 20;
	static final int TAM_REGISTRO = 112; // 4 + 60 + 40 + 4 + 4
	
	public static void main(String[] args) {
		RandomAccessFile raf = null;
		Scanner sc = new Scanner(System.in);
		try {
			raf = new RandomAccessFile("libros.dat", "rw");
			
			int id = 0;
			if (raf.length() > 0) {
				raf.seek(raf.length() - TAM_REGISTRO);
				id = raf.readInt();
			}
			
			raf.seek(raf.length());
			
			raf.writeInt(id + 1);
			
			System.out.println("Introduce el título:");
			StringBuffer sbT = new StringBuffer(sc.nextLine());
			sbT.setLength(TAM_TITULO); 
			raf.writeChars(sbT.toString());
			
			System.out.println("Introduce el autor:");
			StringBuffer sbA = new StringBuffer(sc.nextLine());
			sbA.setLength(TAM_AUTOR); 
			raf.writeChars(sbA.toString());
			
			System.out.println("Introduce el año de edición:");
			raf.writeInt(Integer.parseInt(sc.nextLine()));
			
			System.out.println("Introduce el número de páginas");
			raf.writeInt(Integer.parseInt(sc.nextLine()));
				
			//nos situamos al principio
			raf.seek(0);
			
			char[] cTitulo = new char[TAM_TITULO];
			String titulo;
			char[] cAutor = new char[TAM_AUTOR];
			String autor;
			int anioEd, numPag;
				
			try {
				while (raf.getFilePointer() <= raf.length()) {
					id = raf.readInt();
					
					for (int i = 0; i < TAM_TITULO; i++) {
						cTitulo[i] = raf.readChar();
					}
					titulo = new String(cTitulo);
					
					for (int i = 0; i < TAM_AUTOR; i++) {
						cAutor[i] = raf.readChar();
					}
					autor = new String(cAutor);
					
					anioEd = raf.readInt();
					numPag = raf.readInt();
					
					System.out.println("ID: " + id + " Título: " + titulo + " Autor: " + autor 
							+ "\nAño de edición: " + anioEd + " Número de páginas: " + numPag);
					
				}
			} catch (EOFException e) {
				System.out.println("FICHERO LEIDO");
			} 
			
			
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
			
			sc.close();
		}

	}

}
