package ejUnoAMuchosDesordenada;

import java.io.Serializable;
import java.util.Set;

public class Profesor implements Serializable {
	private int id;
	private String nombre;
	private String ape1;
	private String ape2;
	private Set<CorreoElectronico> correosElectronicos;

	public Profesor() {
	}

	public Profesor(int id, String nombre, String ape1, String ape2) {
		this.id = id;
		this.nombre = nombre;
		this.ape1 = ape1;
		this.ape2 = ape2;
	}

}
