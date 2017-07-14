package com.modetransportation.batch.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtil {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private static SessionFactory buildSessionFactory(){
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Throwable ex) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			
			System.err.println("Initial SessionFactory creation failed." + ex);
			StandardServiceRegistryBuilder.destroy( registry );
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void shutdown() {
		// Close caches and connection pools
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}


}
