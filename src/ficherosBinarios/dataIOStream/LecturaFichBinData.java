package ficherosBinarios.dataIOStream;
import java.io.*;

public class LecturaFichBinData {

	public static void main(String[] args) {
		DataInputStream dis = null;
		
		try {
			dis = new DataInputStream(new FileInputStream("clase.dat"));
			
			String nombre = "";
			int edad = 0;
			
			while (true) {
				nombre = dis.readUTF(); 
				edad = dis.readInt(); 
				System.out.println("Nombre: "+ nombre + " Edad: "+ edad);
			}	
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
