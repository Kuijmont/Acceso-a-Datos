package tablas;
// Generated 31-ene-2019 8:56:52 by Hibernate Tools 4.0.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Competicion generated by hbm2java
 */
public class Competicion implements java.io.Serializable {

	private Integer id;
	private String nombre;
	private Date fechaComienzo;
	private Date fechaFin;
	private Set partidos = new HashSet(0);

	public Competicion() {
	}

	public Competicion(String nombre, Date fechaComienzo, Date fechaFin) {
		this.nombre = nombre;
		this.fechaComienzo = fechaComienzo;
		this.fechaFin = fechaFin;
	}

	public Competicion(String nombre, Date fechaComienzo, Date fechaFin, Set partidos) {
		this.nombre = nombre;
		this.fechaComienzo = fechaComienzo;
		this.fechaFin = fechaFin;
		this.partidos = partidos;
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

	public Date getFechaComienzo() {
		return this.fechaComienzo;
	}

	public void setFechaComienzo(Date fechaComienzo) {
		this.fechaComienzo = fechaComienzo;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Set getPartidos() {
		return this.partidos;
	}

	public void setPartidos(Set partidos) {
		this.partidos = partidos;
	}

}
