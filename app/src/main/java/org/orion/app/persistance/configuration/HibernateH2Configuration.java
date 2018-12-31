package org.orion.app.persistance.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateH2Configuration {
    private SessionFactory sessionFactory;
    private Configuration configuration;

    public void configure() {
        configuration = new Configuration();
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
                .setProperty("hibernate.connection.url",
                        "jdbc:h2:./GLOBALS;INIT=CREATE SCHEMA IF NOT EXISTS GLOBALS")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.default_schema", "GLOBALS");
    }

    public void addClass(Class type) {
        configuration.addAnnotatedClass(type);
    }

    public void buildSessionFactory() {
        sessionFactory = configuration.buildSessionFactory();
    }

    public SessionFactory sessionFactory() {
        return sessionFactory;
    }
}
