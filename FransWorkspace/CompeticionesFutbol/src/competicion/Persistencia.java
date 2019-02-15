package competicion;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;

import tablas.Competicion;
import tablas.Equipo;
import tablas.Jugador;
import tablas.Posicion;

public interface Persistencia {

	void conectBD();

	void desconectBD();
	
	void toRegisterATeam(Equipo team) throws SQLException;

	void toModify(Equipo team, String string);

	ArrayList<Equipo> toListTeams() throws SQLException;

	void notifyError(JFrame padre, String titulo, Exception e, String mensaje);

	boolean toSelectATeam(String nombre) throws SQLException;

	boolean confirmQuestion(JFrame padre, String titulo, String mensaje);

	void infoMessage(JFrame padre, String title, String message);

	void toDelete(Equipo team);
	
	void toRegisterAPlayer(Jugador player);
}
