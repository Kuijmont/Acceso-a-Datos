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

	void toModifyATeam(Equipo team, String string);

	ArrayList<Equipo> toListTeams() throws SQLException;

	void notifyError(JFrame padre, String titulo, Exception e, String mensaje);

	boolean toSelectATeam(String nombre) throws SQLException;

	boolean confirmQuestion(JFrame padre, String titulo, String mensaje);

	void infoMessage(JFrame padre, String title, String message);

	void toDeleteATeam(Equipo team);
	
	void toRegisterAPlayer(Jugador player);

	ArrayList<Jugador> listPlayersOfTeam(Equipo e);

	ArrayList<Posicion> listPositions();

	void toRegisterAPosition(Posicion pos);

	void toDeleteAPositon(Posicion pos2);

	void toModifyAPosition(Posicion e, String text);

	boolean toSelectAPosition(String item);

	boolean toSelectAPlayer(String item);
}
