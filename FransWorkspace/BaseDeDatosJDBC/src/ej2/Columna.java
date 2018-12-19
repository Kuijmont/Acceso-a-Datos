package ej2;

public class Columna {
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	String nombre;
	String tipo;
	
	public Columna(String nombre, String tipo) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
	}
	public String getTipo() {
		return tipo;
	}
	@Override
	public String toString() {
		return "Columna [nombre=" + nombre + ", tipo=" + tipo + "]";
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
