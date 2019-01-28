package miBanco;
// Generated 22-ene-2019 13:56:57 by Hibernate Tools 4.0.1.Final

import java.util.Date;

/**
 * Movimiento generated by hbm2java
 */
public class Movimiento implements java.io.Serializable {

	private Integer id;
	private Cuenta cuenta;
	private Date FH;
	private int importe;

	public Movimiento() {
	}

	public Movimiento(Cuenta cuenta, Date FH, int importe) {
		this.cuenta = cuenta;
		this.FH = FH;
		this.importe = importe;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFH() {
		return this.FH;
	}

	public void setFH(Date FH) {
		this.FH = FH;
	}

	public int getImporte() {
		return this.importe;
	}

	public void setImporte(int importe) {
		this.importe = importe;
	}

}
