package competicion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tablas.Competicion;
import tablas.Equipo;
import tablas.Posicion;

public class PersistenciaMySQL implements Persistencia {

	// Variables
	static Connection cn;
	static Statement st;
	static ResultSet result;
	String server,user,pass,bd;
	Equipo team;
	String sql;
	
	// Constructor
	public PersistenciaMySQL(String server, String user, String pass, String bd) {
		this.server = server;
		this.user = user;
		this.pass = pass;
		this.bd = bd;
	}

	// Conect database
	@Override
	public void conectBD() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			notifyError(null, "ERROR", e1, "Error en encontrar el driver.");
		}
		try {
			cn = DriverManager.getConnection("jdbc:mysql://" + server + "/" + bd, user, pass);
			JOptionPane.showMessageDialog(null, "Se ha conectado correctamente.", "Mensaje", 1);
		} catch (SQLException e) {
			notifyError(null, "ERROR", e, "Error en la conexión.");
		}

	}

	// Desconect database
	@Override
	public void desconectBD() {
		try {
			cn.close();
			JOptionPane.showMessageDialog(null, "Desconectado con éxito.", "Mensaje", 1);
		} catch (SQLException e) {
			notifyError(null, "ERROR", e, "Error en la desconexión.");
		}

	}
	
	// Register a Team
	@Override
	public void toRegisterATeam(String table, Equipo team)throws SQLException {
		st = cn.createStatement();
		// Insert a Team
		sql = "INSERT INTO "+table+" VALUES("+team.getId()+",'"+team.getNombre()+"')";
		try {
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null,"El equipo "+team.getNombre()+" se ha creado correctamente.","Mensaje", 1);
			st.close(); // Close the statement
		} catch (SQLException e) {
			notifyError(null, "ERROR", e, "Error en la creación.");
		}
	}
	
	// List all Teams
	@Override
	public ArrayList<Equipo> toListTeams() throws SQLException{
		ArrayList<Equipo> al = new ArrayList<>();
		st = cn.createStatement();
		// Select all Team
		sql = "SELECT * from equipo";
		result = st.executeQuery(sql);
		while(result.next()) {
			Equipo e = new Equipo(result.getString(2));
			al.add(e);
 		}
		st.close();// Close the statement
		return al;
	}
	
	// Modify 
	@Override
	public void toModify(String table, Equipo team, Posicion position, Competicion competition)
			throws SQLException {
		st = cn.createStatement();
		switch (table) {
			case "equipo":
				sql = "update "+table+" set id="+team.getId()+", nombre = '"+team.getNombre()+"'";
				break;
			case "posicion":
				sql = "update "+table+" set id="+position.getId()+", descripcion = '"+
				position.getDescripcion()+"'";
				break;
			case "competiciones":
				sql = "update "+table+" set id="+competition.getId()+", nombre = '"+
				competition.getNombre()+"', fechaComienzo="+competition.getFechaComienzo()
						+ ", fechaFin="+competition.getFechaFin();
				break;
			default:
				break;
			}
		try {
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null,"Se ha modificado correctamente.","Mensaje", 1);
			st.close(); // Close the statement
		} catch (SQLException e) {
			notifyError(null, "ERROR", e, "Error en la modificación.");
		}
	}

	// Cancel
	@Override
	public void toCancel(String table, Equipo team, Posicion position, Competicion competition)
			throws SQLException {
		st = cn.createStatement();
		switch (table) {
			case "equipo":
				sql = "delete from "+table+" where id = "+team.getId();
				break;
			case "posicion":
				sql = "delete from "+table+" where id = "+position.getId();
				break;
			case "competiciones":
				sql = "delete from "+table+" where id = "+competition.getId();
				break;
			default:
				break;
			}
		try {
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null,"Se ha borrado correctamente.","Mensaje", 1);
			st.close(); // Close the statement
		} catch (SQLException e) {
			notifyError(null, "ERROR", e, "Error en el borrado.");
		}
	}
	
	// Notify ERROR
	@Override
	public  void notifyError(JFrame padre, String titulo, Exception e, String mensaje) {
		String contenido = "";
		if (mensaje != null)
			contenido += mensaje + "\n";
		if (e != null) {
			e.printStackTrace();
			contenido += e.getClass().getName() + "\n" + e.getMessage(); 
		}
		JOptionPane.showMessageDialog(padre, contenido, titulo, JOptionPane.ERROR_MESSAGE);
	}
	
	
	
	
}
