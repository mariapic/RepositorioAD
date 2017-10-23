package pruebaParcial;
import java.util.ArrayList;

public class ListaLineasPedidos {
	        
	private ArrayList<LineaPedido> lista = new ArrayList<LineaPedido>();
	
	public void add(LineaPedido lp){
	        lista.add(lp);
	}
	
	public ArrayList<LineaPedido> getLista(){
	        return lista;
	}
	
}
