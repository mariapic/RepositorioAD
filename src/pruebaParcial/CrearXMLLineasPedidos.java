package pruebaParcial;


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

public class CrearXMLLineasPedidos {

	public static void main(String[] args) {
		
		ObjectInputStream ois = null;
		FileInputStream fis = null; 
		
		try {
			ois = new ObjectInputStream(new FileInputStream("lineasPedidos.dat"));
			LineaPedido lp = null;
			
			// crearemos una instancia de DocumentBuilderFactory para construir el parser
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			DOMImplementation implementacion = db.getDOMImplementation();
			
			// Creamos un documento vacío de nombre doc con el nodo raíz de nombre Empleados y asignamos la versión del XML
			Document doc = implementacion.createDocument(null, "lineasPedidos", null);
			doc.setXmlVersion("1.0");

			// controlamos el final del fichero controlando la excepción EOFException 
			try {
				Element pedido, prod;
				//Recorreremos el fichero con los datos para que al leer cada registro pedido 
				while ((lp = (LineaPedido) ois.readObject()) != null) {
					System.out.println("ID: " + lp.getIdPedido() + " Producto: " + lp.getProducto().getNombre() 
							+ " Cantidad: " + lp.getCantidad() + " Precio Total: " + lp.getPrecioTotal()
							+ " Fecha Pedido: " + lp.getFechaPedido());
						
						// Creamos el nodo <empleado> y lo añadimos al documento
						pedido = doc.createElement("pedido");   
						doc.getDocumentElement().appendChild(pedido);
						
						crearElemento(doc, pedido, "idPedido", Integer.toString(lp.getIdPedido()));
						
						prod = doc.createElement("producto");
						pedido.appendChild(prod);
						crearElemento(doc, prod, "idProducto", Integer.toString(lp.getProducto().getIdProducto()));
						crearElemento(doc, prod, "nombre", lp.getProducto().getNombre());
						crearElemento(doc, prod, "precio", Double.toString(lp.getProducto().getPrecio()));
						
						crearElemento(doc, pedido, "cantidad", Integer.toString(lp.getCantidad()));
						crearElemento(doc, pedido, "precioTotal", Double.toString(lp.getPrecioTotal()));
						crearElemento(doc, pedido, "fechaPedido", lp.getFechaPedido());
				}
			} catch (EOFException e) {} 
			
			// Creamos la fuente XML a partir del documento
			Source source = new DOMSource(doc);
			// Creamos el resultado en el fichero empleados.xml
			Result result = new StreamResult(new File("lineasPedidos.xml"));
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
			
			// XStream
			XStream xs = new XStream();
			
			// Limpiamos permisos
			xs.addPermission(NoTypePermission.NONE);
			// permitimos lo básico
			xs.addPermission(NullPermission.NULL);
			xs.addPermission(PrimitiveTypePermission.PRIMITIVES);
			
			Class[] clases = {ListaLineasPedidos.class, LineaPedido.class, Producto.class};
			xs.allowTypes(clases);
			
			// permitimos cualquier tipo procedente del mismo paquete
			xs.allowTypesByWildcard(new String[] {"com.your.package.**"});
			
			xs.alias("lineasPedidos", ListaLineasPedidos.class);
			xs.alias("pedido", LineaPedido.class);
			xs.alias("producto", Producto.class);
			xs.addImplicitCollection(ListaLineasPedidos.class, "lista");
			fis = new FileInputStream("lineasPedidos.xml");
			ListaLineasPedidos listaTotal = (ListaLineasPedidos) xs.fromXML(fis);
			
			ArrayList<LineaPedido> listaLp = new ArrayList<LineaPedido>();
			listaLp = listaTotal.getLista();
				
			for (LineaPedido lped : listaLp) {
				System.out.println("ID: " + lped.getIdPedido() + " Producto: " + lped.getProducto().getNombre() 
						+ " Precio Producto: " + lped.getProducto().getPrecio() 
						+ " Cantidad: " + lped.getCantidad() + " Precio Total: " + lped.getPrecioTotal()
						+ " Fecha Pedido: " + lped.getFechaPedido());
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
	 * Recibe el documento (document), el nodo al que se va a añadir (raíz), el nombre del nodo hijo (id, nombre, dep, salario) y sus textos o valores en formato String 
	 */
	private static void crearElemento(Document doc, Element raiz, String nomElemento, String valor) {
		Element elem = doc.createElement(nomElemento);
		Text text = doc.createTextNode(valor);
		raiz.appendChild(elem);
		elem.appendChild(text);
	}

}
