package ej1;

import java.sql.Connection;
import java.sql.DriverManager;

public class PersistenciaBD {

	static Connection cn;
	
	public void conectarDB(String IP, String usu, String pass, String bd) throws Exception{
		
		cn = DriverManager.getConnection("jdbc:mysql://"+IP+"/"+bd+", "+usu+", "+pass);
		
	}
	
}
