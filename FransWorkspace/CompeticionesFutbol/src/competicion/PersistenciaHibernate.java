package competicion;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;

import tablas.Competicion;
import tablas.Equipo;
import tablas.Posicion;

public class PersistenciaHibernate implements Persistencia{

	public PersistenciaHibernate(String property) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void conectBD() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desconectBD() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void toModify(String table, Equipo team, Posicion position, Competicion competition) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toCancel(String table, Equipo team, Posicion position, Competicion competition) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toRegisterATeam(String table, Equipo team) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Equipo> toListTeams() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notifyError(JFrame padre, String titulo, Exception e, String mensaje) {
		// TODO Auto-generated method stub
		
	}

	

	
 
	
	
}
