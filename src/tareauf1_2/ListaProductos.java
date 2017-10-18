package tareauf1_2;
import java.util.ArrayList;

import tareauf1_1.Producto;

public class ListaProductos {
	        
	private ArrayList<Producto> lista = new ArrayList<Producto>();
	
	public void add(Producto prod){
	        lista.add(prod);
	}
	
	public ArrayList<Producto> getListaProductos(){
	        return lista;
	}
	
}
