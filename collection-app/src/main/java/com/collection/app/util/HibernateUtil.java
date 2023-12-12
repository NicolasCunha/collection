package com.collection.app.util;

import com.collection.app.collection.Collection;
import com.collection.app.tcg.Card;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate configuration class used to open database connections (session).
 */
public final class HibernateUtil {

  private static SessionFactory sessionFactory;

  public HibernateUtil() {
    // noop
  }

  /**
   * Creates a new session factory, allowing to perform database manipulations.
   *
   * @return {@link SessionFactory} instance.
   */
  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        final Properties settings = new Properties();
        settings.put(Environment.DRIVER, "org.h2.Driver");
        settings.put(Environment.URL, "jdbc:h2:~/.collection/collection_data");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        settings.put("connection.username", "sa");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        final Configuration configuration = new Configuration();
        configuration.setProperties(settings);
        configuration.addAnnotatedClass(Collection.class);
        configuration.addAnnotatedClass(Card.class);
        final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
      } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException();
      }
    }
    return sessionFactory;
  }

}
