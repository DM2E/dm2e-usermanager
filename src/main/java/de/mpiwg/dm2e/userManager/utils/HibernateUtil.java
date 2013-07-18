package de.mpiwg.dm2e.userManager.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	AnnotationConfiguration cfg = new AnnotationConfiguration().configure("hibernate.cfg.xml");
            if(System.getProperty("hibernate.hbm2ddl.auto") != null && System.getProperty("hibernate.hbm2ddl.auto").equals("create")){
            	cfg.setProperty("hibernate.hbm2ddl.auto", "create");
            }
            sessionFactory = cfg.buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

