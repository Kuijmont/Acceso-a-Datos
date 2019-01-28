package ejUnoAMuchosDesordenada;

import java.io.Serializable;

public class CorreoElectronico implements Serializable {
	private int idCorreo;
	private String direccionCorreo;
	private Profesor profesor;

	public CorreoElectronico() {

	}

	public CorreoElectronico(int idCorreo, String direccionCorreo, Profesor profesor) {
		this.idCorreo = idCorreo;
		this.direccionCorreo = direccionCorreo;
		this.profesor = profesor;
	}
}