package miBanco;
// Generated 22-ene-2019 13:56:57 by Hibernate Tools 4.0.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Cuenta generated by hbm2java
 */
public class Cuenta implements java.io.Serializable {

	private Integer id;
	private Integer saldo;
	private Set clientes = new HashSet(0);
	private Set movimientos = new HashSet(0);

	public Cuenta() {
	}

	public Cuenta(Integer saldo, Set clientes, Set movimientos) {
		this.saldo = saldo;
		this.clientes = clientes;
		this.movimientos = movimientos;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSaldo() {
		return this.saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	public Set getClientes() {
		return this.clientes;
	}

	public void setClientes(Set clientes) {
		this.clientes = clientes;
	}

	public Set getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(Set movimientos) {
		this.movimientos = movimientos;
	}

}
