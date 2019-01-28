package ejercicio02;



import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Main {

	public static void main(String[] args) {

		SessionFactory sessionFactory;
		Configuration configuration = new Configuration();
		configuration.configure("hibernate2.cfg.xml");
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		// SAVE | INSERT

		
		  Seguro2 seguro = new Seguro2(52, "70422680S", "Frans", "Kuijper", "Montero",
		  21, 0, new Date()); // Creamos el objeto
		 
		  Session session = sessionFactory.openSession();
		  
		  session.beginTransaction();
		 
		  session.save(seguro); // <|--- Aqui guardamos el objeto en la base de datos.
		 
		  session.getTransaction().commit();
		 

	}

}
