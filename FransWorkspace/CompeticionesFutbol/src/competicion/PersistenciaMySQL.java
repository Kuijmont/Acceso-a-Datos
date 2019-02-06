package competicion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tablas.Equipo;

public class PersistenciaMySQL implements Persistencia {

	// Variables
	static Connection cn;
	static Statement st;
	static ResultSet result;
	String iP,user,pass,bd;
	Equipo team;
	
	// Constructor
	public PersistenciaMySQL(String iP, String user, String pass, String bd) {
		this.iP = iP;
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
			notificaError(null, "ERROR", e1, "Error en encontrar el driver.");
		}
		try {
			cn = DriverManager.getConnection("jdbc:mysql://" + iP + "/" + bd, user, pass);
			JOptionPane.showMessageDialog(null, "Se ha conectado correctamente.", "Mensaje", 1);
		} catch (SQLException e) {
			notificaError(null, "ERROR", e, "Error en la conexión.");
		}

	}

	// Desconect database
	@Override
	public void desconectBD() {
		try {
			cn.close();
			JOptionPane.showMessageDialog(null, "Desconectado con éxito.", "Mensaje", 1);
		} catch (SQLException e) {
			notificaError(null, "ERROR", e, "Error en la desconexión.");
		}

	}
	
	// Register
	@Override
	public void toRegister(String table) throws SQLException {
		switch (table) {
			case "Equipo":
				team = new Equipo();
				st = cn.createStatement();
				String sql = "INSERT INTO "+table+" VALUES("+team.getId()+"+,'"+team.getNombre()+"')";
				try {
					st.executeUpdate(sql);
					JOptionPane.showMessageDialog(null,"Se ha creado correctamente.","Mensaje", 1);
					
					st.close(); // Close the statement
				} catch (SQLException e) {
					notificaError(null, "ERROR", e, "Error en la creación.");
				}
				break;
	
			default:
				break;
			}
		
	}
	
	// Modify 
	@Override
	public void toModify(String table) throws SQLException {
		switch (table) {
			case "Equipo":
				team = new Equipo();
				st = cn.createStatement();
				String sql = "update "+table+" set Id="+team.getId()+", Nombre = '"+team.getNombre()+"')";
				try {
					st.executeUpdate(sql);
					JOptionPane.showMessageDialog(null,"Se ha modificado correctamente.","Mensaje", 1);
					
					st.close(); // Close the statement
				} catch (SQLException e) {
					notificaError(null, "ERROR", e, "Error en la modificación.");
				}
				break;
	
			default:
				break;
			}
		
	}

	// Cancel
	@Override
	public void toCancel(String table) throws SQLException {
		switch (table) {
			case "Equipo":
				team = new Equipo();
				st = cn.createStatement();
				String sql = "delete from "+table+" where Nombre = '"+team.getNombre()+"'";
				try {
					st.executeUpdate(sql);
					JOptionPane.showMessageDialog(null,"Se ha borrado correctamente.","Mensaje", 1);
					st.close(); // Close the statement
				} catch (SQLException e) {
					notificaError(null, "ERROR", e, "Error en el borrado.");
				}
				break;
	
			default:
				break;
			}
	}
	
	// Notify ERROR
	private static void notificaError(JFrame padre, String titulo, Exception e, String mensaje) {
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
