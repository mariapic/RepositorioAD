package accesoFicherosXML.serializacion;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import accesoFicherosXML.dom.Empleado;

/*import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;*/

public class LeerXMLXStreamEmpleados {

	public static void main(String[] args) {
		
		FileInputStream fis = null; 
		try {
			// Creamos el fichero XML 
			// CODIGO A DESCOMENTAR
			/*XStream xs = new XStream();
			
			// Limpiamos permisos
			xs.addPermission(NoTypePermission.NONE);
			// permitimos lo básico
			xs.addPermission(NullPermission.NULL);
			xs.addPermission(PrimitiveTypePermission.PRIMITIVES);
			
			Class[] clases = {ListaEmpleados.class, Empleado.class};
			xs.allowTypes(clases);
			
			// permitimos cualquier tipo procedente del mismo paquete
			xs.allowTypesByWildcard(new String[] {"com.your.package.**"});
			
			xs.alias("Empleados", ListaEmpleados.class);
			xs.alias("DatosEmpleado", Empleado.class);
			xs.addImplicitCollection(ListaEmpleados.class, "lista");
			fis = new FileInputStream("empleados3.xml");
			ListaEmpleados listaTotal = (ListaEmpleados) xs.fromXML(fis);
			
			ArrayList<Empleado> listaEmp = new ArrayList<Empleado>();
			listaEmp = listaTotal.getListaEmpleados();
				
			for (Empleado emp : listaEmp) {
				System.out.println("ID: " + emp.getId() + " Nombre: " + emp.getNombre() + " Depto: " 
						+ emp.getDep() + " Salario: " + emp.getSalario());
			}*/

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	

}
