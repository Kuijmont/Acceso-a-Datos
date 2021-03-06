package tablas;
// Generated 31-ene-2019 8:56:52 by Hibernate Tools 4.0.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Equipo generated by hbm2java
 */
public class Equipo implements java.io.Serializable {

	private  Integer id;
	private  String nombre;
	private Set jugadors = new HashSet(0);
	private Set partidosForIdVisitante = new HashSet(0);
	private Set partidosForIdLocal = new HashSet(0);

	public Equipo() {
	}

	public Equipo(String nombre) {
		this.nombre = nombre;
	}

	public Equipo(String nombre, Set jugadors, Set partidosForIdVisitante, Set partidosForIdLocal) {
		this.nombre = nombre;
		this.jugadors = jugadors;
		this.partidosForIdVisitante = partidosForIdVisitante;
		this.partidosForIdLocal = partidosForIdLocal;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set getJugadors() {
		return this.jugadors;
	}

	public void setJugadors(Set jugadors) {
		this.jugadors = jugadors;
	}

	public Set getPartidosForIdVisitante() {
		return this.partidosForIdVisitante;
	}

	public void setPartidosForIdVisitante(Set partidosForIdVisitante) {
		this.partidosForIdVisitante = partidosForIdVisitante;
	}

	public Set getPartidosForIdLocal() {
		return this.partidosForIdLocal;
	}

	public void setPartidosForIdLocal(Set partidosForIdLocal) {
		this.partidosForIdLocal = partidosForIdLocal;
	}

}
