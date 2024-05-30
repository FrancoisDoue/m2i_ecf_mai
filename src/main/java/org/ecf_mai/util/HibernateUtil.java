package org.ecf_mai.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public final class HibernateUtil {

    private static SessionFactory factory;

    private HibernateUtil() {}

    public static synchronized SessionFactory getFactory() {
        if (factory == null) {
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
            factory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        }
        return factory;
    }



    public static void close() {
        if (factory == null) return;
        factory.close();
        factory = null;
    }

}
