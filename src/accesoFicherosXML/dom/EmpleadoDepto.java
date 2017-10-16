package accesoFicherosXML.dom;

import java.io.Serializable;

public class EmpleadoDepto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private Depto dep;
	private double salario;
	
	public EmpleadoDepto(int id, String nombre, Depto dep, double salario) {
		this.id = id;
		this.nombre = nombre;
		this.dep = dep;
		this.salario = salario;
	}

	public EmpleadoDepto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Depto getDep() {
		return dep;
	}

	public void setDep(Depto dep) {
		this.dep = dep;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

		

}
