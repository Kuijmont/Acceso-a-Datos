package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PruebaDeConexion {
	static Connection cn;
	public static void main(String[] args) {

		try {
		    Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		    System.out.println("No se encontro el Driver MySQL para JDBC.");
		}
		//Connection cn = DriverManager.getConnection("jdbc:mysql://servidor_bd:puerto/nombre_bd", "usuario", "contraseña");
		try {
			 cn = DriverManager.getConnection("jdbc:mysql://localhost/ventas", "root", "manager");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Statement st = cn.createStatement();
			//String sql = "CREATE TABLE VENTAS(Cod int,Cliente Varchar (20))";	
			String sql = "INSERT INTO VENTAS VALUES(1,'Frans')";
			st.executeUpdate(sql);
			System.out.println("Columna creada correctamente");
			st.close(); // Cierra el statement
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	    try {
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
