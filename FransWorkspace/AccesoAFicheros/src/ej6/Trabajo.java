package ej6;

public class Trabajo {
	int tipo;
	String descripcion;

	public Trabajo(int tipo, String descripcion) {
		this.tipo = tipo;
		this.descripcion = descripcion;
	}

	public String toString() {
		return "Trabajo [tipo=" + tipo + ", descripcion=" + descripcion + "]";
	}
}
