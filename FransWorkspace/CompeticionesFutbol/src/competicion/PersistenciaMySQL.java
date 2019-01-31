package competicion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class PersistenciaMySQL implements Persistencia {

	static Connection cn;
	static Statement st;
	static ResultSet result;

	@Override
	public void conectBD(String IP, String user, String pass, String bd) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
		}
		try {
			cn = DriverManager.getConnection("jdbc:mysql://" + IP + "/" + bd, user, pass);
			JOptionPane.showMessageDialog(null, "Se ha conectado correctamente.", "Mensaje", 1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void desconectBD() {
		try {
			cn.close();
			JOptionPane.showMessageDialog(null, "Desconectado con éxito.", "Mensaje", 1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}
