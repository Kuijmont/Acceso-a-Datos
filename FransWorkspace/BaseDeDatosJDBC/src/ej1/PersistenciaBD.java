package ej1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class PersistenciaBD implements Persistencia{

	static Connection cn;
	static Statement st;
	static ResultSet result;
	private ArrayList<Persona> p; 
	@Override
	public void conectarDB(String IP, String usu, String pass, String bd){
		
		try {
			cn = DriverManager.getConnection("jdbc:mysql://"+IP+"/"+bd, usu, pass);
			JOptionPane.showMessageDialog(null,"Se ha conectado correctamente.","Mensaje", 1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	@Override
	public void desconectarDB() {
		try {
			cn.close();
			JOptionPane.showMessageDialog(null,"Desconectado con éxito.","Mensaje", 1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	@Override
	public ArrayList<Persona> listadoPersonas(String tabla, String orderBy) {
		
		try {
			st = cn.createStatement();
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}
		String sql = "select * FROM "+tabla+" order by "+orderBy;
		Persona pers = null;
		try {
			result = st.executeQuery(sql);
			
			while(result.next()) {
				pers = new Persona(result.getString(1), result.getString(2), result.getString(3), result.getString(4));
				p.add(pers);
			}
			result.close();
			st.close(); // Cierra el statement
			return p;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,"Error al sacar la consulta.","Mensaje", 0);
		}

		return p;
	}
	@Override
	public void guardarPersona(String tabla, Persona p) throws SQLException  {
		
		st = cn.createStatement();
		String sql = "INSERT INTO "+tabla+" VALUES('"+p.getNombre()+"','"+p.getCP()+"','"+p.getPais()+"','"+p.getEmail()+"')";
		try {
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null,"Persona insertada correctamente.","Mensaje", 1);
			
			st.close(); // Cierra el statement
		} catch (SQLException e) {
			sql = "update "+tabla+" set nombre='"+p.getNombre()+"', CP='"+p.getCP()+"', pais='"+p.getPais()+"', email='"+p.getEmail()+"'where email = '"+p.getEmail()+"'";
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null,"Persona modificada correctamente.","Mensaje", 1);
			st.close();
		}
		
  	
	}
	@Override
	public void borrarPersona(String tabla, String email) throws Exception {
		st = cn.createStatement();
		String sql = "DELETE FROM "+tabla+" where email='"+email+"'";
		try {
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null,"Persona borrada correctamente.","Mensaje", 1);
			
			st.close(); // Cierra el statement
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Correo no existe","Mensaje", 0);
		}
		
		
	}
	@Override
	public Persona consultarPersona(String tabla, String email) throws Exception {

		st = cn.createStatement();
		String sql = "select * FROM "+tabla+" where email='"+email+"'";
		Persona pers = null;
		try {
			result = st.executeQuery(sql);
			result.next();
			pers = new Persona(result.getString(1), result.getString(2), result.getString(3), result.getString(4));
			result.close();
			st.close(); // Cierra el statement
			return pers;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,"Error al sacar la consulta.","Mensaje", 0);
		}
		return pers;
		
		
	}
}
