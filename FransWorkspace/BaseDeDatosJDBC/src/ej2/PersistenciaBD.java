package ej2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class PersistenciaBD {
	static Connection cn;
	static Statement st;
	static ResultSet result;
	static DatabaseMetaData metaDatos;
	public void conectarDB(String IP, String usu, String pass){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
		}
		
		try {
			cn = DriverManager.getConnection("jdbc:mysql://"+IP, usu, pass);
			JOptionPane.showMessageDialog(null,"Se ha conectado correctamente.","Mensaje", 1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	public void desconectarDB() {
		try {
			cn.close();
			JOptionPane.showMessageDialog(null,"Desconectado con éxito.","Mensaje", 1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	
	}
	
	public ArrayList<String> listarBases() throws SQLException{
//		st = cn.createStatement();
//		String sql = "show databases";
		ArrayList<String> p = new ArrayList<String>(); 
//		result = st.executeQuery(sql);		
		metaDatos = cn.getMetaData();
		result = metaDatos.getCatalogs();
		while(result.next()) {
			p.add(result.getString(1));
		}
		return p;
	
	}
	public ArrayList<String> listarTablas(String base) throws SQLException{
		ArrayList<String> al = new ArrayList<String>();
		metaDatos = cn.getMetaData();
		result = metaDatos.getTables(base, null,"%", null);
		while (result.next()) {
			   String tabla = result.getString(3);
			   al.add(tabla);
			}
		return al;
	}
	
	public ArrayList<Columna> listarColumnas(String base, String tabla) throws SQLException{
		ArrayList<Columna> al = new ArrayList<Columna>();
		metaDatos = cn.getMetaData();
		result = metaDatos.getColumns(base, null,tabla, null);
		while (result.next()) {
			   String nombreColumna = result.getString(4);
			   String tipoColumna = result.getString(6);
			   Columna col = new Columna(nombreColumna,tipoColumna);
			   al.add(col);
		}
		return al;
	}
	
	
}
