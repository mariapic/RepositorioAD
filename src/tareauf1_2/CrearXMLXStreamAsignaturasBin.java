package tareauf1_2;


import java.io.*;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.thoughtworks.xstream.XStream;

import tareauf1_1.Asignatura;

public class CrearXMLXStreamAsignaturasBin {

	public static void main(String[] args) {
		
		ObjectInputStream ois = null;
		XMLReader procesadorXML;
		
		try {
			ois = new ObjectInputStream(new FileInputStream("asignaturas.dat"));
			Asignatura asig = null;
			ListaAsignaturas listaAsig = new ListaAsignaturas();			
			// controlamos el final del fichero controlando la excepción EOFException 
			try {
				//Recorreremos el fichero con los datos para que al leer cada registro asignatura 
				while ((asig = (Asignatura) ois.readObject()) != null) {
					System.out.println("ID: " + asig.getId() + " Nombre: " + asig.getNombre() + " Profesor: " 
							+ asig.getProfesor() + " Horas: " + asig.getHoras());
						
						// Creamos el nodo <empleado> y lo añadimos al documento
						listaAsig.add(asig);
				}
			} catch (EOFException e) {} 
			
			// Creamos el fichero XML 
			XStream xs = new XStream();
			xs.alias("listaAsignaturas", ListaAsignaturas.class);
			xs.alias("asignatura", Asignatura.class);
			xs.addImplicitCollection(ListaAsignaturas.class, "lista");
			xs.toXML(listaAsig, new FileOutputStream("asignaturasXS.xml"));
			
			procesadorXML = XMLReaderFactory.createXMLReader();
			GestionContenido gestor = new GestionContenido();
			procesadorXML.setContentHandler(gestor);
			InputSource fileXML = new InputSource("asignaturasXS.xml");
			procesadorXML.parse(fileXML);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
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
