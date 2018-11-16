package ej1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class PersistenciaBD implements Persistencia{

	static Connection cn;
	static Statement st;
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
		
		
		return p;
	}
	@Override
	public void guardarPersona(String tabla, Persona p) throws Exception {
		
		st = cn.createStatement();
		String sql = "INSERT INTO "+tabla+" VALUES('"+p.getNombre()+"','"+p.getCP()+"','"+p.getPais()+"','"+p.getEmail()+"')";
		st.executeUpdate(sql);
		JOptionPane.showMessageDialog(null,"Persona insertada correctamente.","Mensaje", 1);
		st.close(); // Cierra el statement
  	
	}
	@Override
	public void borrarPersona(String tabla, Persona p) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Persona consultarPersona(String tabla, String email) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
