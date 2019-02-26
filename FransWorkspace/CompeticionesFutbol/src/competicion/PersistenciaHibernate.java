package competicion;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import tablas.Competicion;
import tablas.Equipo;
import tablas.Jugador;
import tablas.Posicion;

public class PersistenciaHibernate implements Persistencia {

	private String cfg;
	Session sesion;
	SessionFactory sessionFactory;

	public PersistenciaHibernate(String cfg) {
		this.cfg = cfg;
	}

	@Override
	public void conectBD() {
		Configuration configuration = new Configuration();
		configuration.configure(cfg);
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		sesion = sessionFactory.openSession();
		infoMessage(null, "Conexión", "Se ha conectado correctamente.");
	}

	@Override
	public void desconectBD() {
		sessionFactory.close();
		sessionFactory = null;
		infoMessage(null, "Desconexión", "Se ha desconectado correctamente.");
	}

	@Override
	public void toRegisterATeam(Equipo team) throws SQLException {
		sesion.beginTransaction();
		sesion.saveOrUpdate(team);
		sesion.getTransaction().commit();
		infoMessage(null, "Crear Equipo", "Se ha creado correctamente.");
	}

	// List all teams
	@Override
	public ArrayList<Equipo> toListTeams() throws SQLException {
		ArrayList<Equipo> al = new ArrayList<Equipo>();

		al = (ArrayList<Equipo>) sesion.createQuery("from Equipo").list();

		return al;
	}

	@Override
	public void notifyError(JFrame padre, String titulo, Exception e, String mensaje) {
		String contenido = "";
		if (mensaje != null)
			contenido += mensaje + "\n";
		if (e != null) {
			e.printStackTrace();
			contenido += e.getClass().getName() + "\n" + e.getMessage();
		}
		JOptionPane.showMessageDialog(padre, contenido, titulo, JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public boolean toSelectATeam(String nombre) throws SQLException {

		Query q = sesion.createQuery("FROM Equipo WHERE nombre=?");
		q.setString(0, nombre);
		Equipo team = (Equipo) q.uniqueResult();
		if (team != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean confirmQuestion(JFrame padre, String titulo, String mensaje) {
		int confirmado = JOptionPane.showConfirmDialog(padre, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
		if (JOptionPane.OK_OPTION == confirmado)
			return true;
		else
			return false;

	}

	@Override
	public void infoMessage(JFrame padre, String title, String message) {
		JOptionPane.showMessageDialog(padre, message, title, 1);
	}

	@Override
	public void toDeleteATeam(Equipo team) {

		// I check the team making a selection of it to get the ID of this same team
		// Because before the ID was worth null
		Query q = sesion.createQuery("FROM Equipo WHERE nombre=?");
		q.setString(0, team.getNombre());
		Equipo team2 = (Equipo) q.uniqueResult();
		System.out.println(team2.getNombre() + " , " + team2.getId());
		sesion.beginTransaction();
		sesion.delete(team2);
		sesion.getTransaction().commit();
		infoMessage(null, "Borrar Equipo", "Se ha borrado correctamente.");
	}

	@Override
	public void toModifyATeam(Equipo team, String name) {

		Equipo team2 = (Equipo) sesion.get(Equipo.class, team.getId());
		team2.setNombre(name);

		sesion.beginTransaction();
		sesion.update(team2);
		sesion.getTransaction().commit();
		infoMessage(null, "Modificación Equipo", "Se ha modificado correctamente.");
	}

	@Override
	public void toRegisterAPlayer(Jugador player) {
		sesion.beginTransaction();
		sesion.saveOrUpdate(player);
		sesion.getTransaction().commit();
		infoMessage(null, "Crear Jugador", "Se ha creado correctamente.");
	}

	// List all players of a team
	@Override
	public ArrayList<Jugador> listPlayersOfTeam(Equipo e) {
		ArrayList<Jugador> al = new ArrayList<Jugador>();
		al = (ArrayList<Jugador>) sesion.createQuery("from Jugador where idEquipo = " + e.getId()).list();
		return al;
	}

	@Override
	public ArrayList<Posicion> listPositions() {
		ArrayList<Posicion> al = new ArrayList<Posicion>();
		al = (ArrayList<Posicion>) sesion.createQuery("from Posicion").list();
		return al;
	}

	@Override
	public void toRegisterAPosition(Posicion pos) {
		sesion.beginTransaction();
		sesion.saveOrUpdate(pos);
		sesion.getTransaction().commit();
		infoMessage(null, "Crear Posición", "Se ha creado correctamente.");
	}

	@Override
	public void toDeleteAPositon(Posicion pos2) {
		// I check the team making a selection of it to get the ID of this same team
		// Because before the ID was worth null
		Query q = sesion.createQuery("FROM Posicion WHERE descripcion=?");
		q.setString(0, pos2.getDescripcion());
		Posicion pos = (Posicion) q.uniqueResult();
		System.out.println(pos.getDescripcion() + " , " + pos.getId());
		sesion.beginTransaction();
		sesion.delete(pos);
		sesion.getTransaction().commit();
		infoMessage(null, "Borrar Posición", "Se ha borrado correctamente.");
	}

	@Override
	public void toModifyAPosition(Posicion e, String text) {
		Posicion pos = (Posicion) sesion.get(Posicion.class, e.getId());
		pos.setDescripcion(text);

		sesion.beginTransaction();
		sesion.update(pos);
		sesion.getTransaction().commit();
		infoMessage(null, "Modificación Posición", "Se ha modificado correctamente.");
	}

	@Override
	public boolean toSelectAPosition(String item) {
		Query q = sesion.createQuery("FROM Posicion WHERE descripcion=?");
		q.setString(0, item);
		Posicion pos = (Posicion) q.uniqueResult();
		if (pos != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean toSelectAPlayer(String item) {
		Query q = sesion.createQuery("FROM Jugador WHERE nombre=?");
		q.setString(0, item);
		Jugador team = (Jugador) q.uniqueResult();
		if (team != null)
			return true;
		else
			return false;
	}

}
