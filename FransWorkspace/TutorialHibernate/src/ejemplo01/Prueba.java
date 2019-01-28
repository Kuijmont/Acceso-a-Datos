package ejemplo01;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Prueba {
	public static void main(String[] args) {
		SessionFactory sessionFactory;
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		// SAVE

		Profesor profesor = new Profesor(101, "Juan", "Perez", "García"); // Creamos el objeto

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(profesor); // <|--- Aqui guardamos el objeto en la base de datos.

		session.getTransaction().commit();

		// READ
		profesor = (Profesor) session.get(Profesor.class, 101);

		// UPDATE

		session.beginTransaction();

		session.update(profesor);

		session.getTransaction().commit();

		// DELETE

		profesor = (Profesor) session.get(Profesor.class, 101);

		session.beginTransaction();
		session.delete(profesor);
		session.getTransaction().commit();

		// SAVE and UPDATE

		profesor = new Profesor(101, "Juan", "Perez", "García");

		session.beginTransaction();

		session.saveOrUpdate(profesor);

		session.getTransaction().commit();

		session.close();
		sessionFactory.close();
	}
}
