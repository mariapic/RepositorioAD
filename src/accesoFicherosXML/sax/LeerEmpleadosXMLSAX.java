package accesoFicherosXML.sax;

import java.io.IOException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class LeerEmpleadosXMLSAX {

	public static void main(String[] args) {
		XMLReader procesadorXML;
		try {
			procesadorXML = XMLReaderFactory.createXMLReader();
			GestionContenido gestor = new GestionContenido();
			procesadorXML.setContentHandler(gestor);
			InputSource fileXML = new InputSource("empleados.xml");
			procesadorXML.parse(fileXML);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
			
	
}
