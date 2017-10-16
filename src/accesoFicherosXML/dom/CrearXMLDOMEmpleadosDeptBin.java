package accesoFicherosXML.dom;

import java.io.*;

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

public class CrearXMLDOMEmpleadosDeptBin {

	public static void main(String[] args) {
		
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream("empleadosDepto.dat"));
			EmpleadoDepto emp = null;
			
			// crearemos una instancia de DocumentBuilderFactory para construir el parser
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			DOMImplementation implementacion = db.getDOMImplementation();
			
			// Creamos un documento vacío de nombre doc con el nodo raíz de nombre Empleados y asignamos la versión del XML
			Document doc = implementacion.createDocument(null, "empleados", null);
			doc.setXmlVersion("1.0");

			// controlamos el final del fichero controlando la excepción EOFException 
			try {
				Element empleado, depto;
				//Recorreremos el fichero con los datos para que al leer cada registro empleado 
				// se cree un nodo empleado con 4 hijos (<id>, <nombre>, <dep> y <salario>) 
				while ((emp = (EmpleadoDepto) ois.readObject()) != null) {
						System.out.println("ID: " + emp.getId() + " Nombre: " + emp.getNombre() + " Depto: " 
											+ emp.getDep().getNombre() + " Salario: " + emp.getSalario());
						
						// Creamos el nodo <empleado> y lo añadimos al documento
						empleado = doc.createElement("empleado");   
						doc.getDocumentElement().appendChild(empleado);
						
						empleado.setAttribute("fec_alta", "12/06/2017");
						
						crearElemento(doc, empleado, "id", Integer.toString(emp.getId()));
						crearElemento(doc, empleado, "nombre", emp.getNombre());
						
						depto = doc.createElement("departamento");
						empleado.appendChild(depto);
						crearElemento(doc, depto, "iddep", Integer.toString(emp.getDep().getId()));
						crearElemento(doc, depto, "nomdep", emp.getDep().getNombre());
						crearElemento(doc, depto, "locdep", emp.getDep().getLocalizacion());
						
						crearElemento(doc, empleado, "salario", Double.toString(emp.getSalario()));
				}
			} catch (EOFException e) {} 
			
			// Creamos la fuente XML a partir del documento
			Source source = new DOMSource(doc);
			// Creamos el resultado en el fichero empleados.xml
			Result result = new StreamResult(new File("empleadosDept.xml"));
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
		}


	}

	/*
	 * Recibe el documento (document), el nodo al que se va a añadir (raíz), el nombre del nodo hijo (id, nombre, dep, salario) y sus textos o valores en formato String 
	 */
	private static void crearElemento(Document doc, Element raiz, String nomElemento, String valor) {
		Element elem = doc.createElement(nomElemento);
		Text text = doc.createTextNode(valor);
		raiz.appendChild(elem);
		elem.appendChild(text);
	}

}
