package accesoFicherosXML.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LeerXMLEmpleadosDOM2 {

	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new File("empleados.xml")); 

			Node raiz = document.getFirstChild();
			System.out.println(raiz.getNodeName());
			NodeList elementos = raiz.getChildNodes();
			
			obtenerNodosHijos(elementos);
			
		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void obtenerNodosHijos(NodeList elementos) {
		Node nodo;
		NodeList hijos;
		if (elementos.getLength() > 1) {
			for (int k = 0; k < elementos.getLength(); k++) {
				nodo = elementos.item(k);
				
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					System.out.println("\t" + nodo.getNodeName());
					hijos = nodo.getChildNodes();
					obtenerNodosHijos(hijos);
				}
				
			}
		} else {
			System.out.println("\t" + elementos.item(0).getNodeValue());
		}
	}

}
