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

	// Disconnect database
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
	public void toRegisterATeam(Equipo team)throws SQLException {
		st = cn.createStatement();
		// Insert a Team
		sql = "INSERT INTO equipo VALUES("+team.getId()+",'"+team.getNombre()+"')";
		try {
			st.executeUpdate(sql);
			infoMessage(null, "Mensaje", "El equipo "+team.getNombre()+" se ha creado correctamente.");
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
	
	// Modify a Team
	@Override
	public void toModify(Equipo team, String name){
		try {
			st = cn.createStatement();
			sql = "update equipo set nombre = '"+team.getNombre()+"' where nombre ='"+name+"'";
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null,"Se ha modificado correctamente.","Mensaje", 1);
			st.close(); // Close the statement
		} catch (SQLException e) {
			notifyError(null, "ERROR", e, "Error en la modificación.");
		}
	}

	// Delete a Team
	@Override
	public void toDelete(Equipo team){
		try {
			st = cn.createStatement();
			sql = "delete from equipo where nombre = '"+team.getNombre()+"'";
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
	
	// To see if there is a team with this name
	@Override
	public boolean toSelectATeam(String nombre) throws SQLException {
		st = cn.createStatement();
		// Select all Team
		sql = "SELECT * from equipo where nombre='"+nombre+"'";
		result = st.executeQuery(sql);
		if(result.next()) {
 			st.close();// Close the statement
			return true;
 		}else {
 			st.close();// Close the statement
 			return false;
 		}
	}

	// To Confirm a Question
	@Override
	public boolean confirmQuestion(JFrame padre, String titulo, String mensaje) {
		int confirmado = JOptionPane.showConfirmDialog(padre, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
		if (JOptionPane.OK_OPTION == confirmado)
			return true;
		else
			return false;
	}

	// Info Message
	@Override
	public void infoMessage(JFrame padre, String title, String message) {
		JOptionPane.showMessageDialog(padre, message, title, 1);
		
	}

	
}
