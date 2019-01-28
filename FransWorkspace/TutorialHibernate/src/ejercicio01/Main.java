package ejercicio01;

import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import ejemplo01.Profesor;

public class Main {

	public static void main(String[] args) {
		SessionFactory sessionFactory;
		Configuration configuration = new Configuration();
		configuration.configure("hibernate2.cfg.xml");
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		// SAVE | INSERT

		/*
		 * Seguro seguro = new Seguro(51, "70422680S", "Frans", "Kuijper", "Montero",
		 * 21, 0, new Date()); // Creamos el objeto
		 *
		 * Session session = sessionFactory.openSession(); session.beginTransaction();
		 *
		 * session.save(seguro); // <|--- Aqui guardamos el objeto en la base de datos.
		 *
		 * session.getTransaction().commit();
		 */

		// READ
		Seguro seguro = new Seguro(51, "70422680S", "Frans", "Kuijper", "Montero", 21, 0, new Date());
		Session session = sessionFactory.openSession();
		// seguro = (Seguro) session.get(Seguro.class, 51);

		// UPDATE
		/*
		 * session.beginTransaction();
		 *
		 * session.update(seguro);
	 	 *
		 * session.getTransaction().commit();
		 */	
		// DELETE
		/*
		 * seguro = (Seguro) session.get(Seguro.class, 51);
		 * 
		 * session.beginTransaction();
		 * session.delete(seguro);
		 * session.getTransaction().commit();
 		 */
		// SAVE and UPDATE
		
		seguro = new Seguro(51, "12345678V","Pepe", "Perez", "García",21, 0, new Date());

		session.beginTransaction();

		session.saveOrUpdate(seguro);

		session.getTransaction().commit();
		
		session.close();
		sessionFactory.close();
	}

}
