package competicion;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;

import tablas.Competicion;
import tablas.Equipo;
import tablas.Posicion;

public interface Persistencia {

	void conectBD();

	void desconectBD();
	
	void toRegisterATeam(String table, Equipo team) throws SQLException;

	void toModify(String table, Equipo team, Posicion position, Competicion competition) throws SQLException;

	void toCancel(String table, Equipo team, Posicion position, Competicion competition) throws SQLException;

	ArrayList<Equipo> toListTeams() throws SQLException;

	void notifyError(JFrame padre, String titulo, Exception e, String mensaje);

}
