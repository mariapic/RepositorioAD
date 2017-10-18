package tareauf1_2;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LeerProductosXMLDOM {

	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new File("productos.xml")); 

			Node raiz = document.getFirstChild();
			System.out.println(raiz.getNodeName());
			NodeList elementos = raiz.getChildNodes();
			
			obtenerNodosHijos(elementos, "");
			
		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void obtenerNodosHijos(NodeList elementos, String tab) {
		Node nodo;
		NodeList hijos;
		if (elementos.getLength() > 1) {
			for (int k = 0; k < elementos.getLength(); k++) {
				nodo = elementos.item(k);
				
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					System.out.println(tab + "\t" + nodo.getNodeName());
					hijos = nodo.getChildNodes();
					obtenerNodosHijos(hijos, tab + "\t");
				}
				
			}
		} else {
			System.out.println(tab + "\t" + elementos.item(0).getNodeValue());
		}
	}

}
