package tareauf1_2;
import java.util.ArrayList;

import tareauf1_1.Asignatura;

public class ListaAsignaturas {
	        
	private ArrayList<Asignatura> lista = new ArrayList<Asignatura>();
	
	public void add(Asignatura asig){
	        lista.add(asig);
	}
	
	public ArrayList<Asignatura> getLista(){
	        return lista;
	}
	
}
