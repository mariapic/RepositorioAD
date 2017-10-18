package tareauf1_2;


import java.io.*;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import tareauf1_1.Asignatura;

public class CrearXMLDOMAsignaturasBin {

	public static void main(String[] args) {
		
		ObjectInputStream ois = null;
		FileInputStream fis = null; 
		
		try {
			ois = new ObjectInputStream(new FileInputStream("asignaturas.dat"));
			Asignatura asig = null;
			
			// crearemos una instancia de DocumentBuilderFactory para construir el parser
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			DOMImplementation implementacion = db.getDOMImplementation();
			
			// Creamos un documento vacío de nombre doc con el nodo raíz de nombre Empleados y asignamos la versión del XML
			Document doc = implementacion.createDocument(null, "asignaturas", null);
			doc.setXmlVersion("1.0");

			// controlamos el final del fichero controlando la excepción EOFException 
			try {
				Element asignatura = null;
				//Recorreremos el fichero con los datos para que al leer cada registro asignatura 
				System.out.println("---LEYENDO DEL FICHERO BINARIO---");
				while ((asig = (Asignatura) ois.readObject()) != null) {
						System.out.println("ID: " + asig.getId() + " Nombre: " + asig.getNombre() + " Profesor: " 
											+ asig.getProfesor() + " Horas: " + asig.getHoras());
						
						// Creamos el nodo <empleado> y lo añadimos al documento
						asignatura = doc.createElement("asignatura");   
						doc.getDocumentElement().appendChild(asignatura);
						
						crearElemento(doc, asignatura, "id", Integer.toString(asig.getId()));
						crearElemento(doc, asignatura, "nombre", asig.getNombre());
						crearElemento(doc, asignatura, "profesor", asig.getProfesor());
						crearElemento(doc, asignatura, "horas", Integer.toString(asig.getHoras()));
				}
			} catch (EOFException e) {} 
			
			// Creamos la fuente XML a partir del documento
			Source source = new DOMSource(doc);
			// Creamos el resultado en el fichero empleados.xml
			Result result = new StreamResult(new File("asignaturas.xml"));
			// Obtenemos un TransformerFactory
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			// Le damos formato y realizamos la transformación del documento a fichero
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml"); 
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(source,  result); 
			
			// Mostramos el documento por pantalla especificando como canal de salida el System.out
			Result console = new StreamResult(System.out);
			transformer.transform(source, console);
			
			// ahora leemos usando XStream
			// Creamos el fichero XML 
			XStream xs = new XStream();
			
			// Limpiamos permisos
			xs.addPermission(NoTypePermission.NONE);
			// permitimos lo básico
			xs.addPermission(NullPermission.NULL);
			xs.addPermission(PrimitiveTypePermission.PRIMITIVES);
			
			Class[] clases = {ListaAsignaturas.class, Asignatura.class};
			xs.allowTypes(clases);
			
			// permitimos cualquier tipo procedente del mismo paquete
			xs.allowTypesByWildcard(new String[] {"com.your.package.**"});
			
			xs.alias("asignaturas", ListaAsignaturas.class);
			xs.alias("asignatura", Asignatura.class);
			xs.addImplicitCollection(ListaAsignaturas.class, "lista");
			fis = new FileInputStream("asignaturas.xml");
			ListaAsignaturas listaTotal = (ListaAsignaturas) xs.fromXML(fis);
			
			ArrayList<Asignatura> listaAsig = new ArrayList<Asignatura>();
			listaAsig = listaTotal.getLista();
				
			System.out.println("---LEYENDO DEL FICHERO XML---");
			for (Asignatura asignatura : listaAsig) {
				System.out.println("ID: " + asignatura.getId() + " Nombre: " + asignatura.getNombre() + " Profesor: " 
						+ asignatura.getProfesor() + " Horas: " + asignatura.getHoras());
			}

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}


	}

	/*
	 * Recibe el documento (document), el nodo al que se va a añadir (elemPadre), el nombre del nodo hijo y sus textos o valores en formato String 
	 */
	private static void crearElemento(Document doc, Element elemPadre, String nomElemento, String valor) {
		Element elemHijo = doc.createElement(nomElemento);
		Text text = doc.createTextNode(valor);
		elemPadre.appendChild(elemHijo);
		elemHijo.appendChild(text);
	}

}
