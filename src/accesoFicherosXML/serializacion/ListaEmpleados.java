package accesoFicherosXML.serializacion;

import java.util.ArrayList;

import accesoFicherosXML.dom.Empleado;

public class ListaEmpleados {
	        
	private ArrayList<Empleado> lista = new ArrayList<Empleado>();
	
	public void add(Empleado per){
	        lista.add(per);
	}
	
	public ArrayList<Empleado> getListaEmpleados(){
	        return lista;
	}
	
}
