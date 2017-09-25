package ficherosBinarios.fileIOStream;
import java.io.*;

public class EscrituraFichBin {

	public static void main(String[] args) {
		File fich = new File("fichero.dat");
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream(fich);
			fos = new FileOutputStream(fich, true);
			
			for (int i = 0; i < 100; i++) { 
				fos.write(i); 
			}
			
			fos.flush();
			
			int n = 0;
			while ((n = fis.read()) != -1) { 
				System.out.println(n); 
			}

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		

	}

}
